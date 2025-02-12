package com.mycompany.tp.dsw.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mycompany.tp.dsw.dto.ClienteDto;
import com.mycompany.tp.dsw.dto.ItemMenuDto;
import com.mycompany.tp.dsw.dto.ItemPedidoDto;
import com.mycompany.tp.dsw.dto.MercadoPagoDto;
import com.mycompany.tp.dsw.dto.PagoDto;
import com.mycompany.tp.dsw.dto.PedidoDto;
import com.mycompany.tp.dsw.dto.PedidoItemPedidoDto;
import com.mycompany.tp.dsw.dto.TransferenciaDto;
import com.mycompany.tp.dsw.exception.ClienteNoEncontradoException;
import com.mycompany.tp.dsw.exception.NoValidarException;
import com.mycompany.tp.dsw.exception.PedidoNoEncontradoException;
import com.mycompany.tp.dsw.service.ClienteService;
import com.mycompany.tp.dsw.service.PedidoService;
import com.mycompany.tp.dsw.model.Cliente;
import com.mycompany.tp.dsw.model.Estado;
import com.mycompany.tp.dsw.model.ItemPedido;
import com.mycompany.tp.dsw.model.MercadoPago;
import com.mycompany.tp.dsw.model.Pago;
import com.mycompany.tp.dsw.model.Pedido;
import com.mycompany.tp.dsw.model.Transferencia;
import com.mycompany.tp.dsw.service.ServiceManager;

public class PedidoController {
    private ServiceManager serviceManager;
    private PedidoService pedidoService;
    private ClienteService clienteService;
    private ItemMenuController itemMenuController;

    public PedidoController() {
        serviceManager = ServiceManager.getInstance();
        pedidoService = serviceManager.getPedidoService();
        clienteService = serviceManager.getClienteService();
        itemMenuController = new ItemMenuController();
    }

    /**
     * Persiste un pedido en el sistema.
     *
     * @param pedidoDto       El objeto PedidoDto con la informacion del pedido.
     * @param listaItemPedido La lista de items del pedido a guardar.
     */
    public void guardarPedido(PedidoDto pedidoDto, List<ItemPedidoDto> listaItemPedido) {
        List<PedidoItemPedidoDto> pedidoItemPedidoDtos = new ArrayList<>();

        for (ItemPedidoDto itemPedidoDto : listaItemPedido) {
            PedidoItemPedidoDto pedidoItemPedidoDto = PedidoItemPedidoDto.builder()
                    .pedidoDto(pedidoDto)
                    .itemPedidoDto(itemPedidoDto)
                    .build();

            pedidoItemPedidoDtos.add(pedidoItemPedidoDto);
        }

        pedidoService.guardarPedido(pedidoDto, pedidoItemPedidoDtos);
    }

    /**
     * Actualiza el estado de un pedido.
     *
     * @param pedidoDto El objeto PedidoDto con la nueva informacion del estado.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public void actualizarEstadoPedido(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoDto.getId());
        pedido.setEstado(pedidoDto.getEstado());

        pedidoService.modificarPedido(pedido);
    }

    /**
     * Genera un pedido para un cliente basado en el id de cliente.
     *
     * @param idCliente El id del cliente para generar el pedido.
     * @return El objeto PedidoDto generado.
     * @throws NoValidarException Si el id de cliente no es valido.
     */
    public PedidoDto generarPedido(String idCliente) throws NoValidarException {
        Integer id = null;
        if (idCliente != null) {
            id = idValido(idCliente);
        }

        PedidoDto pedidoDto = PedidoDto.builder().clienteId(id).build();
        Pedido pedido = pedidoService.registrarPedido(pedidoDto);
        return mapToDto(pedido);
    }

    /**
     * Calcula el total de un pedido sin el recargo.
     *
     * @param pedidoDto El objeto PedidoDto con la informacion del pedido.
     * @return El total sin recargo del pedido.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public BigDecimal calcularTotalSinRecargo(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoDto.getId());
        return pedido.totalSinRecargo();
    }

    /**
     * Instancia los items del pedido a partir de un mapa de productos y cantidades.
     *
     * @param productosYCantidad El mapa con el nombre del producto y su cantidad.
     * @return Una lista de objetos ItemPedidoDto generados.
     */
    public List<ItemPedidoDto> generarItemPedidoDto(Map<String, List<Integer>> productosYCantidad) {
        return productosYCantidad.entrySet().stream().map(entry -> {
            ItemPedidoDto itemPedido = ItemPedidoDto.builder().build();
            Integer cantidad = entry.getValue().get(0);
            itemPedido.setCantidad(cantidad);
            ItemMenuDto itemMenu = itemMenuController.buscarPorNombre(entry.getKey()).get(0);
            itemPedido.setItemMenuDto(itemMenu);
            return itemPedido;
        }).collect(Collectors.toList());
    }

    /**
     * Obtiene los pedidos realizados por un cliente.
     *
     * @param idCliente El id del cliente para filtrar los pedidos.
     * @return Una lista de objetos PedidoDto asociados al cliente.
     * @throws ClienteNoEncontradoException Si no se encuentra el cliente.
     * @throws NoValidarException           Si el id de cliente no es valido.
     */
    public List<PedidoDto> obtenerPedidosPorCliente(String idCliente)
            throws ClienteNoEncontradoException, NoValidarException {
        Integer id = null;
        if (idCliente != null) {
            id = idValido(idCliente);
        }
        List<Pedido> pedidos = pedidoService.filtrarPedidosPorCliente(id);
        return pedidos.stream().map(this::mapToDto).toList();
    }

    /**
     * Obtiene un pedido basado en el id del pedido.
     *
     * @param idPedido El id del pedido a buscar.
     * @return El objeto PedidoDto correspondiente al id.
     * @throws NoValidarException Si el id del pedido no es valido.
     */
    public PedidoDto obtenerPedidoPorId(String idPedido) throws NoValidarException {
        Integer id = null;
        if (idPedido != null) {
            id = idValido(idPedido);
        }

        try {
            Pedido pedido = pedidoService.buscarPedidoPorId(id);
            return mapToDto(pedido);
        } catch (PedidoNoEncontradoException e) {
            throw null;
        }
    }

    /**
     * Obtiene los pedidos realizados por un vendedor.
     *
     * @param idVendedor El id del vendedor para filtrar los pedidos.
     * @return Una lista de objetos PedidoDto asociados al vendedor.
     * @throws NoValidarException Si el id del vendedor no es valido.
     */
    public List<PedidoDto> obtenerPedidoPorIdVendedor(String idVendedor) throws NoValidarException {
        Integer id = null;
        if (idVendedor != null) {
            id = idValido(idVendedor);
        }
        try {
            List<Pedido> pedidos = pedidoService.buscarPedidoPorVendedor(id);
            return pedidos.stream().map(this::mapToDto).toList();
        } catch (PedidoNoEncontradoException e) {
            return null;
        }
    }

    /**
     * Mapea un objeto Pedido a un objeto PedidoDto.
     *
     * @param pedido El objeto Pedido a convertir.
     * @return El objeto PedidoDto resultante.
     */
    private PedidoDto mapToDto(Pedido pedido) {
        return PedidoDto.builder()
                .clienteId(pedido.getCliente().getId())
                .id(pedido.getId())
                .estado(pedido.getEstado())
                .formaPagoDto(mapToDto(pedido.getFormaPago()))
                .build();
    }

    /**
     * Mapea un objeto Pago a un objeto PagoDto.
     *
     * @param pago El objeto Pago a convertir.
     * @return El objeto PagoDto resultante.
     */
    private PagoDto mapToDto(Pago pago) {
        if (pago == null) {
            return null;
        }
        switch (pago.getClass().getName().toLowerCase()) {
            case "mercadopago":
                MercadoPago mp = (MercadoPago) pago;
                return MercadoPagoDto.builder()
                        .alias(mp.getAlias())
                        .id(mp.getId())
                        .fechaPago(mp.getFechaPago())
                        .monto(mp.getMonto())
                        .build();
            case "transferencia":
                Transferencia t = (Transferencia) pago;
                return TransferenciaDto.builder()
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

    // Validaciones de los atributos para realizar la creacion de un Pago.
    private Integer idValido(String idText) throws NoValidarException {
        try {
            if (isEmpty(idText)) {
                throw new NoValidarException("Error: El id no puede estar vacío");
            }
            return Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            throw new NoValidarException("Error: El id solo puede contener números");
        }
    }

    public Boolean isEmpty(String palabra) {
        return (palabra == null || palabra.trim().isEmpty());
    }

    /**
     * Actualiza un pedido con la informacion de un cliente.
     *
     * @param pedidoDto  El objeto PedidoDto con la informacion del pedido.
     * @param clienteDto El objeto ClienteDto con la informacion del cliente.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public void actualizarPedido(PedidoDto pedidoDto, ClienteDto clienteDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoDto.getId());
        pedido.setFormaPago(mapToDto(pedidoDto.getFormaPagoDto()));
        Cliente cliente = clienteService.buscarClientePorId(clienteDto.getId());
        pedido.addObserver(cliente);
        pedido.setEstado(Estado.PAGADO);
        pedidoService.modificarPedido(pedido);

    }

    /**
     * Calcula el total a pagar de un pedido.
     * Teniendo el cuenta el recargo por el tipo de pago.
     * Total sin recargo mirar: calcularTotalAPagarSinRecargo()
     *
     * @param pedidoDto El objeto PedidoDto con la informacion del pedido.
     * @return El total a pagar del pedido.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public BigDecimal calcularTotalAPagar(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoDto.getId());
        pedido.setFormaPago(mapToDto(pedidoDto.getFormaPagoDto()));
        return pedido.total();
    }

    /**
     * Calcula el total a pagar de un pedido sin recargo.
     *
     * @param pedidoDto El objeto PedidoDto con la informacion del pedido.
     * @return El total a pagar sin recargo del pedido.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public BigDecimal calcularTotalAPagarSinRecargo(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoDto.getId());
        return pedido.totalSinRecargo();
    }

    /**
     * Obtiene los items de un pedido.
     *
     * @param pedidoDto El objeto PedidoDto con la informacion del pedido.
     * @return Una lista de objetos ItemPedidoDto asociados al pedido.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public List<ItemPedidoDto> obtenerItemPedidosPorPedido(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoDto.getId());
        List<ItemPedido> itemPedidos = pedido.getItems();

        return itemPedidos.stream().map(this::mapToDto).toList();
    }

    /**
     * Mapea un objeto ItemPedido a un objeto ItemPedidoDto.
     *
     * @param itemPedido El objeto ItemPedido a convertir.
     * @return El objeto ItemPedidoDto resultante.
     */
    private ItemPedidoDto mapToDto(ItemPedido itemPedido) {
        return ItemPedidoDto.builder()
                .id(itemPedido.getId())
                .cantidad(itemPedido.getCantidad())
                .itemMenuDto(itemMenuController.mapToDto(itemPedido.getItemMenu()))
                .build();

    }

    private Pago mapToDto(PagoDto pagoDto) {
        switch (pagoDto.getClass().getSimpleName().toLowerCase()) {
            case "transferenciadto":
                TransferenciaDto t = (TransferenciaDto) pagoDto;
                return Transferencia.builder()
                        .id(t.getId())
                        .fechaPago(t.getFechaPago())
                        .monto(t.getMonto())
                        .cbu(t.getCbu())
                        .cuit(t.getCuit())
                        .build();
            case "mercadopagodto":
                MercadoPagoDto mp = (MercadoPagoDto) pagoDto;
                return MercadoPago.builder()
                        .id(mp.getId())
                        .fechaPago(mp.getFechaPago())
                        .monto(mp.getMonto())
                        .alias(mp.getAlias())
                        .build();
            default:
                return null;
        }
    }

    /**
     * Obtiene el nombre del vendedor asociado a un pedido.
     *
     * @param pedidoDto El objeto PedidoDto con la informacion del pedido.
     * @return El nombre del vendedor asociado al pedido.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public Object obtenerNombreVendedor(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoDto.getId());
        return pedido.obtenerVendedor().getNombre();
    }

    /**
     * Obtiene la cantidad de items en un pedido.
     *
     * @param pedidoDto El objeto PedidoDto con la informacion del pedido.
     * @return La cantidad de items en el pedido.
     * @throws PedidoNoEncontradoException Si no se encuentra el pedido.
     */
    public Object obtenerCantidadItems(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoDto.getId());
        return pedido.cantidadItems();
    }
}
