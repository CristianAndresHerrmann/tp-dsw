package com.mycompany.tp.dsw.memory;

import java.util.List;

import com.mycompany.tp.dsw.dao.PedidoDao;
import com.mycompany.tp.dsw.dto.PedidoDto;
import com.mycompany.tp.dsw.exception.ClienteNoEncontradoException;
import com.mycompany.tp.dsw.exception.PedidoNoEncontradoException;
import com.mycompany.tp.dsw.model.Cliente;
import com.mycompany.tp.dsw.model.Estado;
import com.mycompany.tp.dsw.model.Pedido;
import com.mycompany.tp.dsw.service.MemoryManager;

public class PedidoMemory {

    private PedidoDao pedidoDao;
    private MemoryManager memoryManager;
    private ClienteMemory clienteMemory;

    public PedidoMemory() {
        pedidoDao = new PedidoDao();
        memoryManager = MemoryManager.getInstance();
        clienteMemory = memoryManager.getClienteMemory();
    }

    /**
     * Crea y persiste un pedido
     * - Manejo de id unicos con currentID
     * 
     * @param pedido El pedido a persistir
     */
    public Pedido registrarPedido(PedidoDto pedidoDto) {
        Pedido pedido = parsePedido(pedidoDto);
        return pedidoDao.add(pedido);
    }

    /**
     * Obtiene los pedidos, filtrados por cliente
     * 
     * @param clienteId El cliente al que se quiere buscar los pedidos
     * @return Lista de los pedidos del cliente del @param
     * @throws ClienteNoEncontradoException Si no encuentra el cliente
     */

    public List<Pedido> filtrarPedidosPorCliente(Integer clienteId) throws ClienteNoEncontradoException {
        return pedidoDao.findByCliente(clienteId);
    }

    /**
     * Obtiene los pedidos, filtrados por estado
     * - Estados: RECIBIDO, ACEPTADO, PREPARADO, ENVIADO, ENTREGADO
     * 
     * @param estado El estado a por filtrar
     * @return Lista de pedidos que permanecen en el estado del @param
     */

    public List<Pedido> filtrarPedidoPorEstado(Estado estado) {
        return pedidoDao.findByEstado(estado);
    }

    /**
     * Modifica los datos de un pedido
     * - Del objeto pedido pasado como parametro
     * - Solo los datos a modificar permanecen no nulos
     * 
     * @param pedido El objeto pedido con los datos modificados
     * @throws PedidoNoEncontradoException
     */

    public void modificarPedido(Pedido pedido) throws PedidoNoEncontradoException {
        pedidoDao.update(pedido);
    }

    /**
     * Elimina un pedido, segun el id
     * 
     * @param id
     */

    public void eliminarPedido(Integer id) {
        pedidoDao.delete(id);
    }

    /**
     * Obtiene una lista de todos los pedidos del sistema
     * 
     * @return Lista de los pedidos del sistema
     */

    public List<Pedido> getAllPedido() {
        return pedidoDao.findAll();
    }

    /**
     * Obtiene los pedidos, filtrados por id
     * 
     * @param id El id del pedido a buscar
     * @return El pedido correspondiente al id
     * @throws PedidoNoEncontradoException Si no encuentra el pedido
     */

    public Pedido buscarPedidoPorId(Integer id) {
        return pedidoDao.findById(id);
    }

    /**
     * Obtiene los pedidos, filtrados por restaurante
     * 
     * @param idVendedor El id del restaurante a buscar sus pedidos
     * @return Lista de los pedidos del restaurante del @param
     * @throws PedidoNoEncontradoException
     */

    public List<Pedido> buscarPedidoPorVendedor(Integer idVendedor) {
        return pedidoDao.findByVendedor(idVendedor);
    }

    private Pedido parsePedido(PedidoDto pedidoDto) {

        Integer idCliente = Integer.parseInt(pedidoDto.getIdCliente());
        Cliente cliente = clienteMemory.buscarClientePorId(idCliente);

        return new Pedido(cliente);
    }
}
