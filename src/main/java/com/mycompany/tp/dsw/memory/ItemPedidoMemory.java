/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.dsw.memory;

import java.math.BigDecimal;
import java.util.List;

import com.mycompany.tp.dsw.dao.ItemsPedidoDao;
import com.mycompany.tp.dsw.dto.ItemPedidoDto;
import com.mycompany.tp.dsw.exception.ItemNoEncontradoException;
import com.mycompany.tp.dsw.exception.PedidoNoEncontradoException;
import com.mycompany.tp.dsw.exception.VendedorNoEncontradoException;
import com.mycompany.tp.dsw.model.ItemMenu;
import com.mycompany.tp.dsw.model.ItemPedido;
import com.mycompany.tp.dsw.model.Pedido;
import com.mycompany.tp.dsw.service.MemoryManager;

/**
 *
 * @author User
 */
public class ItemPedidoMemory {
    private ItemsPedidoDao itemPedidoDao;
    private MemoryManager memoryManager;
    private ItemMenuMemory itemMenuMemory;
    private PedidoMemory pedidoMemory;

    public ItemPedidoMemory() {
        itemPedidoDao = new ItemsPedidoDao();
        memoryManager = MemoryManager.getInstance();
        itemMenuMemory = memoryManager.getItemMenuMemory();
        pedidoMemory = memoryManager.getPedidoMemory();
    }

    /**
     * Busca los item ya pedidos, segun un restaurante
     * - Restaurante = Vendedor
     * 
     * @param id Id del Restaurante
     * @return Lista de los item pedidos del restaurante
     * @throws ItemNoEncontradoException     Si no se encontro items en el sistema
     * @throws VendedorNoEncontradoException
     */
    public List<ItemPedido> buscarPorRestaurante(Integer id)
            throws ItemNoEncontradoException, VendedorNoEncontradoException {
        return itemPedidoDao.findByIdRestaurante(id);
    }

    /**
     * Obtiene una lista de los item, ordenados por precio
     * - Ordenar teniendo en cuenta el precio del ItemMenu,
     * - no del ItemPedido (cantidad * precio)
     * 
     * @return Lista de los item del pedido, ordenados
     * @throws ItemNoEncontradoException Si no encuentra el item
     */
    public List<ItemPedido> ordenarPorPrecio() throws ItemNoEncontradoException {
        return itemPedidoDao.sortedByPrecio();
    }

    /**
     * Obtiene una lista con los itemPedido filtrado por precio
     * - En un rango [min, max]
     * 
     * @param min Precio minimo a filtrar
     * @param max Precio maximo a filtrar
     * @return Lista de item pedidos cuyo precio este en el rango
     * @throws ItemNoEncontradoException Si no encuenta items
     */

    public List<ItemPedido> buscarPorPrecios(BigDecimal min, BigDecimal max) throws ItemNoEncontradoException {
        return itemPedidoDao.findBetweenPrecios(min, max);
    }

    /**
     * Obtiene una lista de los item pedidos, filtrado por restaurante
     * - El filtrado se realiza mediante el nombre del restaurante
     * 
     * @param nombreVendedor El nombre del restaurante a buscar los item pedidos
     * @return Lista de los items pedidos del restaurante
     * @throws ItemNoEncontradoException Si no encuentra item pedidos
     */

    public List<ItemPedido> filtrarPorVendedor(String nombreVendedor)
            throws ItemNoEncontradoException, VendedorNoEncontradoException {
        return itemPedidoDao.FindByNombreVendedor(nombreVendedor);
    }

    /*
     * public void setItemsPedido(List<ItemPedido> itemsPedido) {
     * this.itemsPedido = itemsPedido;
     * }
     */

    /**
     * Obtiene una lista con todos los item pedido del sistema
     * - OJO que no es de un restaurante en especifico
     * - Mirar filtrarPorVendeor()
     */

    public List<ItemPedido> getAllItemsPedido() {
        return itemPedidoDao.findAll();
    }

    /**
     * Crea y persiste un item pedido
     * - Manejo de id unicos con currentID
     * 
     * @param itemPedido El item pedido a persistir
     * @throws PedidoNoEncontradoException
     */

    public ItemPedido registrarItemPedido(ItemPedidoDto itemPedidoDto) {
        ItemPedido itemPedido = parseItemPedido(itemPedidoDto);
        return itemPedidoDao.add(itemPedido);
    }

    private ItemPedido parseItemPedido(ItemPedidoDto itemPedidoDto) {
        ItemMenu itemMenu = itemMenuMemory.buscarItemMenuPorNombre(itemPedidoDto.getItemMenuText()).get(0);
        itemPedidoDto.setItemMenu(itemMenu);
        ItemPedido itemPedido =  new ItemPedido(itemPedidoDto);
        return itemPedido;
    }

}
