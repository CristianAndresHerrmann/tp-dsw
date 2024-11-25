/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.dsw.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mycompany.tp.dsw.dto.ItemPedidoDto;
import com.mycompany.tp.dsw.dto.PedidoDto;
import com.mycompany.tp.dsw.exception.ClienteNoEncontradoException;
import com.mycompany.tp.dsw.memory.ClienteMemory;
import com.mycompany.tp.dsw.memory.ItemPedidoMemory;
import com.mycompany.tp.dsw.memory.PedidoMemory;
import com.mycompany.tp.dsw.model.Pedido;
import com.mycompany.tp.dsw.service.MemoryManager;
import com.mycompany.tp.dsw.model.ItemPedido;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class PedidoController {
    MemoryManager memoryManager;
    PedidoMemory pedidoMemory;
    ClienteMemory clienteMemory;
    ItemMenuController itemMenuController;
    ItemPedidoMemory itemPedidoMemory;

    public PedidoController() {
        memoryManager = MemoryManager.getInstance();
        pedidoMemory = memoryManager.getPedidoMemory();
        clienteMemory = memoryManager.getClienteMemory();
        itemMenuController = new ItemMenuController();
        itemPedidoMemory = memoryManager.getItemPedidoMemory();
    }

    public Pedido generarPedido(String idCliente) {

        PedidoDto pedidoDto = new PedidoDto(idCliente);
        return pedidoMemory.registrarPedido(pedidoDto);
    }

    public List<ItemPedido> generarItemPedido(List<ItemPedidoDto> listaItemPedidoDto, Integer idPedido) {
        List<ItemPedido> itemsPedidos = new ArrayList<>();
        Pedido pedido = pedidoMemory.buscarPedidoPorId(idPedido);
        List<ItemPedido> items = pedido.getItems();
        for (ItemPedidoDto itemPedidoDto : listaItemPedidoDto) {
            ItemPedido itemPedido = itemPedidoMemory.registrarItemPedido(itemPedidoDto);
            itemPedido.setPedido(pedido);
            items.add(itemPedido);
            itemsPedidos.add(itemPedido);
        }
        return itemsPedidos;

    }

    public List<ItemPedidoDto> generarItemPedidoDto(Map<String, List<Double>> productosYCantidad) {
        return productosYCantidad.entrySet().stream().map(entry -> {
            ItemPedidoDto itemPedidoDto = new ItemPedidoDto();
            double cantidad = entry.getValue().get(0); // Tomamos el primer valor de la lista
            itemPedidoDto.setCantidad((int) cantidad);
            itemPedidoDto.setItemMenuText(entry.getKey());

            return itemPedidoDto;
        }).collect(Collectors.toList()); // Usamos collect para recolectar los resultados en una lista
    }

    public List<Pedido> obtenerPedidosPorCliente(String idCliente) throws ClienteNoEncontradoException {
        Integer id = Integer.parseInt(idCliente);
        return pedidoMemory.filtrarPedidosPorCliente(id);
    }

    public Pedido obtenerPedidoPorId(String idPedido) {
        Integer id = Integer.parseInt(idPedido);

        return pedidoMemory.buscarPedidoPorId(id);
    }

    public List<Pedido> obtenerPedidoPorIdVendedor(String idVendedor) {
        Integer id = Integer.parseInt(idVendedor);
        return pedidoMemory.buscarPedidoPorVendedor(id);
    }
}
