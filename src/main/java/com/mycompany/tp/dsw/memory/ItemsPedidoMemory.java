/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.dsw.memory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.tp.dsw.dao.ItemsPedidoDao;
import com.mycompany.tp.dsw.dao.VendedorDao;
import com.mycompany.tp.dsw.exception.ItemNoEncontradoException;
import com.mycompany.tp.dsw.exception.VendedorNoEncontradoException;
import com.mycompany.tp.dsw.model.ItemPedido;
import com.mycompany.tp.dsw.model.Vendedor;

/**
 *
 * @author User
 */
public class ItemsPedidoMemory implements ItemsPedidoDao {
    //Implementacion en memoria de itemPedido, luego hay que hacer la conexion a la base de datos y sacar la variable
    private List<ItemPedido> itemsPedido;
    private final VendedorDao vendedorDao; // Inyeccion del dao de vendedor
    private int currentID = 0;

    public ItemsPedidoMemory(VendedorDao vendedorDao) {
        this.itemsPedido = new ArrayList<>();
        this.vendedorDao = vendedorDao;
    }

    // se utiliza la API de Stream para realizar las busquedas

    @Override
    public List<ItemPedido> buscarPorRestaurante(Integer id) throws ItemNoEncontradoException {
        List<ItemPedido> result = new ArrayList<>();
        try {
            // Busca el vendedor usando el DAO
            Vendedor vendedor = vendedorDao.getAllVendedor().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new VendedorNoEncontradoException("No se encontró el vendedor con ID: " + id));
            
            // Filtra los items de pedido por los ítems de menú del vendedor
            result = itemsPedido.stream()
                .filter(item -> vendedor.getItemsMenu().contains(item.getItemMenu()))
                .toList();
        } catch (VendedorNoEncontradoException e) {
            throw new ItemNoEncontradoException("No se encontraron items de pedido para el restaurante con id: " + id);
        }

        if (result.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items de pedido para el restaurante con id: " + id);
        }
        return result;
    }

    @Override
    public List<ItemPedido> ordenPorPrecio() throws ItemNoEncontradoException {
        //ordena teniendo en cuenta el precio del ITEMMENU, NO DEL ITEM PEDIDO
        List<ItemPedido> result = itemsPedido.stream()
                .sorted((item1, item2) -> item1.getItemMenu().getPrecio().compareTo(item2.getItemMenu().getPrecio()))
                .toList();
        if (result.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items de pedido para ordenar por precio");
        }
        return result;
    }

    @Override
    public List<ItemPedido> buscarPorPrecios(BigDecimal min, BigDecimal max) throws ItemNoEncontradoException {
        List<ItemPedido> result = itemsPedido.stream().filter(item -> item.getItemMenu().getPrecio().compareTo(min) >= 0
                && item.getItemMenu().getPrecio().compareTo(max) <= 0).toList();
        if (result.isEmpty()) {
            throw new ItemNoEncontradoException(
                    "No se encontraron items de pedido para el rango de precios: " + min + " - " + max);
        }
        return result;

    }

    @Override
    public List<ItemPedido> filtrarPorVendedor(String nombreVendedor) throws ItemNoEncontradoException {
        List<ItemPedido> result = new ArrayList<>();
        try {
            // Buscar vendedor por nombre usando el DAO
            Vendedor vendedor = vendedorDao.buscarVendedorPorNombre(nombreVendedor);
            
            // Obtener los ítems de pedido que pertenecen a los ítems del vendedor
            result = itemsPedido.stream()
                .filter(item -> vendedor.getItemsMenu().contains(item.getItemMenu()))
                .toList();
            
        } catch (VendedorNoEncontradoException e) {
            throw new ItemNoEncontradoException("No se encontraron items de pedido para el vendedor: " + nombreVendedor);
        }

        if (result.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items de pedido para el vendedor: " + nombreVendedor);
        }
        return result;
    }

    @Override
    public void setItemsPedido(List<ItemPedido> itemsPedido) {
        this.itemsPedido = itemsPedido;
    }

    @Override
    public List<ItemPedido> getAllItemsPedido() {
        return new ArrayList<>(itemsPedido);
    }

    @Override
    public void crearItemPedido(ItemPedido itemPedido) {
        itemPedido.setId(currentID++);
        itemsPedido.add(itemPedido);
    }

    

}
