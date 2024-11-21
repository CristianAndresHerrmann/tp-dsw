package com.mycompany.tp.dsw.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.mycompany.tp.dsw.dto.ClienteDto;
import com.mycompany.tp.dsw.exception.ClienteNoEncontradoException;
import com.mycompany.tp.dsw.memory.ClienteMemory;
import com.mycompany.tp.dsw.model.Cliente;
import com.mycompany.tp.dsw.service.MemoryManager;

public class ClienteController {

    MemoryManager memoryManager;
    ClienteMemory clienteMemory;

    public ClienteController() {
        memoryManager = MemoryManager.getInstance();
        clienteMemory = memoryManager.getClienteMemory();
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteMemory.obtenerTodosLosClientes();
    }

    public void guardarCliente(ClienteDto clienteDto) {
        clienteMemory.registrarCliente(clienteDto);
        JOptionPane.showMessageDialog(null, "Cliente creado exitosamente");
    }

    public void modificarCliente(ClienteDto clienteDto) {
        try {
            clienteMemory.modificarCliente(clienteDto);
        } catch (ClienteNoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void eliminarCliente(String idText) {
        Integer id = Integer.parseInt(idText);
        try {
            clienteMemory.eliminarCliente(id);
        } catch (ClienteNoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public List<Cliente> buscarClientesPorNombre(String nombre) {
        List<Cliente> clientes = clienteMemory.buscarClientePorNombre(nombre);
        return clientes;
    }

    public Cliente buscarClientePorId(String idText) {
        Integer id = Integer.parseInt(idText);
        return clienteMemory.buscarClientePorId(id);
    }

}
