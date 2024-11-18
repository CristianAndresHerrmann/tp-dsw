package com.mycompany.tp.dsw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mycompany.tp.dsw.dto.VendedorDto;
import com.mycompany.tp.dsw.exception.VendedorNoEncontradoException;
import com.mycompany.tp.dsw.memory.VendedorMemory;
import com.mycompany.tp.dsw.model.Vendedor;
import com.mycompany.tp.dsw.service.MemoryManager;
import com.mycompany.tp.dsw.service.ValidarVendedor;

public class VendedorController {

    MemoryManager memoryManager;
    VendedorMemory vendedorMemory;

    public VendedorController() {
        memoryManager = MemoryManager.getInstance();
        vendedorMemory = memoryManager.getVendedorMemory();
    }

    public void guardarVendedor(VendedorDto vendedorDto) {
        Map<String, String> errores = ValidarVendedor.esGuardarValido(vendedorDto);

        if (errores.isEmpty()) {
            vendedorMemory.registrarVendedor(vendedorDto);
            JOptionPane.showMessageDialog(null, "Vendedor creado exitosamente");
        } else {
            errores.forEach((campo, mensaje) -> JOptionPane.showMessageDialog(null, mensaje, "Error en " + campo,
                    JOptionPane.ERROR_MESSAGE));
        }
    }

    public Map<String, String> modificarVendedor(VendedorDto vendedorDto) {
        Map<String, String> errores = ValidarVendedor.esModificarValido(vendedorDto);

        if (errores.isEmpty()) {
            try {
                vendedorMemory.modificarVendedor(vendedorDto);
            } catch (VendedorNoEncontradoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return errores;
    }

    public Map<String, String> eliminarVendedor(String idText) {
        Map<String, String> errores = ValidarVendedor.esEliminarValido(idText);
        if (errores.isEmpty()) {
            try {
                Integer id = Integer.parseInt(idText);
                vendedorMemory.eliminarVendedor(id);
            } catch (VendedorNoEncontradoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return errores;
    }

    public List<Vendedor> buscarVendedorPorId(String idText) {
        List<Vendedor> listaVendedores = new ArrayList<>();
        try {
            Integer id = Integer.parseInt(idText);
            Vendedor vendedor = vendedorMemory.buscarVendedorPorId(id);

            if (vendedor != null) {
                listaVendedores.add(vendedor);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número entero válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return listaVendedores;
    }

    public List<Vendedor> buscarVendedorPorNombre(String nombre) {

        List<Vendedor> listaVendedores = vendedorMemory.buscarVendedorPorNombre(nombre);
        return listaVendedores;
    }

    public List<Vendedor> obtenerTodosLosVendedores() {
        return vendedorMemory.obtenerTodosLosVendedores();
    }
}
