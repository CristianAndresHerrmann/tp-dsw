package com.mycompany.tp.dsw.controller;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.tp.dsw.memory.CategoriaMemory;
import com.mycompany.tp.dsw.model.Categoria;
import com.mycompany.tp.dsw.service.MemoryManager;

public class ItemMenuController {

    private final MemoryManager memoryManager;
    private final CategoriaMemory categoriaMemory;

    public ItemMenuController() {
        memoryManager = MemoryManager.getInstance();
        categoriaMemory = memoryManager.getCategoriaMemory();
    }

    public void guardarItemMenu(String tipoCategoria) {
        switch (tipoCategoria) {
            case "Plato":

                break;
            case "Bebida":

                break;

            default:
                break;
        }
    }

    public List<String> getValoresComboBoxCategoria(String tipoCategoria) {
        if (tipoCategoria.equals("Plato")){
            tipoCategoria = "Comida";
        }
        List<Categoria> categorias = categoriaMemory.buscarPorTipoCategoria(tipoCategoria);
        List<String> categoriasString = new ArrayList<>();
        for (Categoria cat : categorias) {
            String nombre = cat.getNombre();
            categoriasString.add(nombre);
        }
        return categoriasString;
    }
}
