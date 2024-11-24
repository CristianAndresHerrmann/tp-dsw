/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp.dsw.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mycompany.tp.dsw.exception.ItemNoEncontradoException;
import com.mycompany.tp.dsw.exception.VendedorNoEncontradoException;
import com.mycompany.tp.dsw.model.ItemMenu;
import com.mycompany.tp.dsw.model.ItemPedido;
import com.mycompany.tp.dsw.model.Vendedor;

/**
 *
 * @author User
 */
public class ItemsPedidoDao {

    private static List<ItemPedido> itemsPedido;
    private static int currentID;
    private static final VendedorDao vendedorDao;

    static {
        itemsPedido = new ArrayList<>();
        currentID = 0;
        vendedorDao = new VendedorDao();
    }

    public ItemsPedidoDao() {

    }

    public List<ItemPedido> findByIdRestaurante(Integer id)
            throws ItemNoEncontradoException, VendedorNoEncontradoException {
        List<ItemPedido> result = new ArrayList<>();
        Vendedor vendedor = vendedorDao.findById(id);
        if (vendedor != null) {
            // Filtrar los items de pedido por los ítems de menú del vendedor
            result = itemsPedido.stream()
                    .filter(item -> vendedor.getItemsMenu().contains(item.getItemMenu()))
                    .toList();
        } else {
            throw new VendedorNoEncontradoException(
                    "No se ha encontrado el restaurante con ID: " + id);
        }

        if (result.isEmpty()) {
            throw new ItemNoEncontradoException(
                    "No se encontraron items de pedido para el restaurante con id: " + id);
        }
        return result;
    }

    public List<ItemPedido> sortedByPrecio() throws ItemNoEncontradoException {
        List<ItemPedido> result = itemsPedido.stream()
                .sorted((item1, item2) -> item1.getItemMenu().getPrecio().compareTo(item2.getItemMenu().getPrecio()))
                .toList();
        if (result.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items de pedido para ordenar por precio");
        }
        return result;
    }

    public List<ItemPedido> findBetweenPrecios(BigDecimal min, BigDecimal max) throws ItemNoEncontradoException {
        List<ItemPedido> result = itemsPedido.stream().filter(item -> item.getItemMenu().getPrecio().compareTo(min) >= 0
                && item.getItemMenu().getPrecio().compareTo(max) <= 0).toList();
        if (result.isEmpty()) {
            throw new ItemNoEncontradoException(
                    "No se encontraron items de pedido para el rango de precios: " + min + " - " + max);
        }
        return result;
    }

    public List<ItemPedido> FindByNombreVendedor(String nombreVendedor)
            throws ItemNoEncontradoException, VendedorNoEncontradoException {
        List<ItemPedido> result = new ArrayList<>();
        List<Vendedor> vendedores = vendedorDao.findByNombre(nombreVendedor);

        // Verifica si se encontraron vendedores
        if (vendedores.isEmpty()) {
            throw new VendedorNoEncontradoException("No se encontraron vendedores con el nombre: " + nombreVendedor);
        }

        // Obtiene los IDs de los itemsMenu de todos los vendedores encontrados
        Set<ItemMenu> itemsMenuVendedores = vendedores.stream()
                .flatMap(v -> v.getItemsMenu().stream())
                .collect(Collectors.toSet());

        // Filtra los items de pedido que están en el menú de los vendedores
        result = itemsPedido.stream()
                .filter(item -> itemsMenuVendedores.contains(item.getItemMenu()))
                .toList();

        if (result.isEmpty()) {
            throw new ItemNoEncontradoException(
                    "No se encontraron items de pedido para el vendedor: " + nombreVendedor);
        }

        return result;
    }

    /*
     * public void setItemsPedido(List<ItemPedido> itemsPedido) {
     * 
     * }
     */

    public List<ItemPedido> findAll() {
        return itemsPedido;
    }

    public ItemPedido add(ItemPedido itemPedido) {
        itemPedido.setId(currentID++);
        itemsPedido.add(itemPedido);
        return itemPedido;
    }
}
