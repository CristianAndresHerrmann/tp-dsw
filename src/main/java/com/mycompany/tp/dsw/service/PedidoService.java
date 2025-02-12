package com.mycompany.tp.dsw.service;

import java.util.List;

import com.mycompany.tp.dsw.dao.DAOFactory;
import com.mycompany.tp.dsw.dao.PedidoDao;
import com.mycompany.tp.dsw.dto.ItemMenuDto;
import com.mycompany.tp.dsw.dto.ItemPedidoDto;
import com.mycompany.tp.dsw.dto.MercadoPagoDto;
import com.mycompany.tp.dsw.dto.PagoDto;
import com.mycompany.tp.dsw.dto.PedidoDto;
import com.mycompany.tp.dsw.dto.PedidoItemPedidoDto;
import com.mycompany.tp.dsw.dto.TransferenciaDto;
import com.mycompany.tp.dsw.exception.ClienteNoEncontradoException;
import com.mycompany.tp.dsw.exception.PedidoNoEncontradoException;
import com.mycompany.tp.dsw.model.Cliente;
import com.mycompany.tp.dsw.model.Estado;
import com.mycompany.tp.dsw.model.ItemMenu;
import com.mycompany.tp.dsw.model.ItemPedido;
import com.mycompany.tp.dsw.model.MercadoPago;
import com.mycompany.tp.dsw.model.Pago;
import com.mycompany.tp.dsw.model.Pedido;
import com.mycompany.tp.dsw.model.Transferencia;
import com.mycompany.tp.dsw.model.relacion.PedidoItemPedido;

public class PedidoService {

    private DAOFactory factory;

    private PedidoDao pedidoDao;
    private ClienteService clienteService;
    private ServiceManager serviceManager;
    private ItemMenuService itemMenuService;

    public PedidoService() {
        factory = DAOFactory.getInstance();
        serviceManager = ServiceManager.getInstance();
        clienteService = serviceManager.getClienteService();
        itemMenuService = serviceManager.getItemMenuService();
        pedidoDao = factory.getPedidoDao();
    }

    /**
     * Crea y persiste un pedido a partir de un PedidoDto.
     * Realiza conversion del DTO de un pedido en un modelo de Pedido y lo persiste.
     * 
     * @param pedidoDto El DTO del pedido a persistir.
     * @return El objeto Pedido guardado.
     */
    public Pedido registrarPedido(PedidoDto pedidoDto) {
        Pedido pedido = mapToModel(pedidoDto);
        pedidoDao.save(pedido);
        return pedido;
    }

    /**
     * Persiste el modelo Pedido a partir de un DTO de Pedido.
     * 
     * @param pedidoDto            El DTO del pedido a guardar.
     * @param pedidoItemPedidoDtos Lista de DTOs de los items del pedido.
     */
    public void guardarPedido(PedidoDto pedidoDto, List<PedidoItemPedidoDto> pedidoItemPedidoDtos) {
        Pedido pedido = mapToPedidoModel(pedidoDto, pedidoItemPedidoDtos);
        pedidoDao.save(pedido);
    }

    /**
     * Mapea un PedidoDto y una lista de PedidoItemPedidoDto a un modelo Pedido,
     * luego persiste el pedido en la base de datos.
     * 
     * @param pedidoDto            El DTO del pedido.
     * @param pedidoItemPedidoDtos Lista de DTOs de los items del pedido.
     * @return El modelo Pedido mapeado.
     */
    private Pedido mapToPedidoModel(PedidoDto pedidoDto, List<PedidoItemPedidoDto> pedidoItemPedidoDtos) {
        Pedido pedido = Pedido.builder()
                .id(pedidoDto.getId())
                .estado(pedidoDto.getEstado())
                .cliente(mapToCliente(pedidoDto.getClienteId()))
                .formaPago(mapToPago(pedidoDto.getFormaPagoDto()))
                .build();

        List<PedidoItemPedido> pedidoItemPedidos = pedidoItemPedidoDtos.stream()
                .map(this::mapToPedidoItemPedidoModel)
                .peek(pedidoItem -> pedidoItem.setPedido(pedido)) // Establecer la relacion bidireccional
                .toList();

        pedido.setPedidoItemPedidos(pedidoItemPedidos);
        return pedido;
    }

    /**
     * Busca un cliente por su ID y lo devuelve.
     * Utiliza Cliente service para la busqueda
     * 
     * @param id El ID del cliente.
     * @return El cliente correspondiente al ID proporcionado.
     */
    private Cliente mapToCliente(Integer id) {
        return clienteService.buscarClientePorId(id);
    }

    /**
     * Mapea un PagoDto a un modelo de pago, que puede ser de tipo MercadoPago o
     * Transferencia.
     * 
     * @param pagoDto El DTO de pago.
     * @return Un objeto de tipo Pago, que puede ser MercadoPago o Transferencia.
     */
    private Pago mapToPago(PagoDto pagoDto) {
        if (pagoDto == null) {
            return null;
        }
        switch (pagoDto.getClass().getSimpleName().toLowerCase()) {
            case "mercadopago":
                MercadoPagoDto mp = (MercadoPagoDto) pagoDto;
                return MercadoPago.builder()
                        .alias(mp.getAlias())
                        .id(mp.getId())
                        .fechaPago(mp.getFechaPago())
                        .monto(mp.getMonto())
                        .build();
            case "transferencia":
                TransferenciaDto t = (TransferenciaDto) pagoDto;
                return Transferencia.builder()
                        .cbu(t.getCbu())
                        .cuit(t.getCuit())
                        .id(t.getId())
                        .fechaPago(t.getFechaPago())
                        .monto(t.getMonto())
                        .build();

            default:
                return null;
        }
    }

    /**
     * Mapea un PedidoItemPedidoDto a un modelo PedidoItemPedido.
     * 
     * @param pedidoItemPedidoDto El DTO de PedidoItemPedido.
     * @return El modelo PedidoItemPedido mapeado.
     */
    private PedidoItemPedido mapToPedidoItemPedidoModel(PedidoItemPedidoDto pedidoItemPedidoDto) {
        return PedidoItemPedido.builder()
                .id(pedidoItemPedidoDto.getId())
                .itemPedido(mapToItemPedidoModel(pedidoItemPedidoDto.getItemPedidoDto()))
                .build();
    }

    /**
     * Mapea un ItemPedidoDto a un modelo ItemPedido.
     * 
     * @param itemPedidoDto El DTO de ItemPedido.
     * @return El modelo ItemPedido mapeado.
     */
    private ItemPedido mapToItemPedidoModel(ItemPedidoDto itemPedidoDto) {
        return ItemPedido.builder()
                .cantidad(itemPedidoDto.getCantidad())
                .itemMenu(mapToItemMenuModel(itemPedidoDto.getItemMenuDto()))
                .build();
    }

    /**
     * Mapea un ItemMenuDto a un modelo ItemMenu.
     * Busca el ItemMenu en la base de datos o crea uno nuevo si no lo encuentra.
     * 
     * @param itemMenuDto El DTO de ItemMenu.
     * @return El modelo ItemMenu mapeado.
     */
    private ItemMenu mapToItemMenuModel(ItemMenuDto itemMenuDto) {

        List<ItemMenu> itemMenus = itemMenuService.buscarItemMenuPorNombreYVendedor(itemMenuDto.getNombre(),
                itemMenuDto.getIdVendedor());

        if (itemMenus != null) {
            return itemMenus.get(0);
        } else {
            return null;
        }
    }

    /**
     * Filtra los pedidos por el ID del cliente.
     * 
     * @param clienteId El ID del cliente.
     * @return Lista de pedidos asociados al cliente.
     * @throws ClienteNoEncontradoException Si no se encuentra el cliente.
     */
    public List<Pedido> filtrarPedidosPorCliente(Integer clienteId) throws ClienteNoEncontradoException {
        return pedidoDao.findByCliente(clienteId);
    }

    /**
     * Filtra los pedidos por su estado.
     * 
     * @param estado El estado para filtrar los pedidos.
     * @return Lista de pedidos en el estado especificado.
     */
    public List<Pedido> filtrarPedidoPorEstado(Estado estado) {
        return pedidoDao.findByEstado(estado);
    }

    /**
     * Modifica los datos de un pedido.
     * Solo los campos no nulos del pedido pasado como parametro.
     * 
     * @param pedido El pedido con los datos modificados.
     * @throws PedidoNoEncontradoException Si el pedido no se encuentra.
     */

    public void modificarPedido(Pedido pedido) throws PedidoNoEncontradoException {
        pedidoDao.update(pedido);
    }

    /**
     * Elimina un pedido de acuerdo a su ID.
     * 
     * @param id El ID del pedido a eliminar.
     */
    public void eliminarPedido(Integer id) {
        Pedido pedido = pedidoDao.findById(id);
        pedidoDao.delete(pedido);
    }

    /**
     * Obtiene una lista de todos los pedidos del sistema.
     * 
     * @return Lista de todos los pedidos.
     */
    public List<Pedido> getAllPedido() {
        return pedidoDao.findAll();
    }

    /**
     * Filtra los pedidos por su ID y devuelve el pedido correspondiente.
     * 
     * @param id El ID del pedido.
     * @return El pedido correspondiente al ID.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public Pedido buscarPedidoPorId(Integer id) throws PedidoNoEncontradoException {
        return pedidoDao.findPedidoByIdWithItem(id);
    }

    /**
     * Filtra los pedidos por el ID del vendedor (restaurante).
     * 
     * @param idVendedor El ID del restaurante.
     * @return Lista de pedidos del restaurante.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public List<Pedido> buscarPedidoPorVendedor(Integer idVendedor) throws PedidoNoEncontradoException {
        try {
            return pedidoDao.findByIdVendedor(idVendedor);
        } catch (PedidoNoEncontradoException e) {
            throw e;
        }
    }

    /**
     * Convierte un PedidoDto en un modelo Pedido.
     * 
     * @param pedidoDto El DTO del pedido.
     * @return El modelo Pedido mapeado.
     */
    private Pedido mapToModel(PedidoDto pedidoDto) {

        Cliente cliente = clienteService.buscarClientePorId(pedidoDto.getClienteId());

        return Pedido.builder()
                .cliente(cliente)
                .estado(Estado.RECIBIDO)
                .build();
    }
}
