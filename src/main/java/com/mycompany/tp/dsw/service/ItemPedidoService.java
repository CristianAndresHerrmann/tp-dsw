package com.mycompany.tp.dsw.service;

import java.math.BigDecimal;
import java.util.List;

import com.mycompany.tp.dsw.dao.DAOFactory;
import com.mycompany.tp.dsw.dao.ItemsPedidoDao;
import com.mycompany.tp.dsw.exception.ItemNoEncontradoException;
import com.mycompany.tp.dsw.model.ItemPedido;

public class ItemPedidoService {
    private DAOFactory factory;
    private ItemsPedidoDao itemPedidoDao;

    public ItemPedidoService() {
        factory = DAOFactory.getInstance();
        itemPedidoDao = factory.getItemsPedidoDao();
    }

    /**
     * Busca los items ya pedidos segun un restaurante
     * - Restaurante = Vendedor
     * 
     * @param id Id del Restaurante
     * @return Lista de los items pedidos del restaurante
     * @throws ItemNoEncontradoException Si no se encontraron items.
     */
    public List<ItemPedido> buscarPorRestaurante(Integer id) throws ItemNoEncontradoException {
        return itemPedidoDao.findByIdRestaurante(id);
    }

    /**
     * Obtiene una lista de los items, ordenados por precio
     * - Ordenar teniendo en cuenta el precio del ItemMenu,
     * - No del ItemPedido (cantidad * precio)
     * 
     * @return Lista de los items del pedido, ordenados
     * @throws ItemNoEncontradoException Si no se encuentran los items
     */
    public List<ItemPedido> ordenarPorPrecio() throws ItemNoEncontradoException {
        return itemPedidoDao.findSortedByPrecio();
    }

    /**
     * Obtiene una lista con los items pedidos filtrados por precio
     * - En un rango [min, max]
     * 
     * @param min Precio minimo a filtrar
     * @param max Precio maximo a filtrar
     * @return Lista de items pedidos cuyo precio este en el rango
     * @throws ItemNoEncontradoException Si no se encuentran los items
     */
    public List<ItemPedido> buscarPorPrecios(BigDecimal min, BigDecimal max) throws ItemNoEncontradoException {
        return itemPedidoDao.findBetweenPrecios(min, max);
    }

    /**
     * Obtiene una lista de los items pedidos, filtrados por restaurante
     * - El filtrado se realiza mediante el nombre del restaurante
     * 
     * @param nombreVendedor El nombre del restaurante para buscar los items pedidos
     * @return Lista de los items pedidos del restaurante
     * @throws ItemNoEncontradoException Si no se encuentran items pedidos
     */
    public List<ItemPedido> filtrarPorVendedor(String nombreVendedor) throws ItemNoEncontradoException {
        return itemPedidoDao.findByNombreRestaurante(nombreVendedor);
    }

    /**
     * Obtiene una lista con todos los items pedidos del sistema
     * - Nota: No es especifico de un restaurante.
     * - Ver filtrarPorVendedor()
     * 
     * @return Lista de todos los items pedidos
     */
    public List<ItemPedido> getAllItemsPedido() {
        return itemPedidoDao.findAll();
    }

    /**
     * Crea y persiste un item pedido
     * 
     * @param itemPedido El item pedido a persistir
     */
    public void registrarItemPedido(ItemPedido itemPedido) {
        itemPedidoDao.save(itemPedido);
    }

}
