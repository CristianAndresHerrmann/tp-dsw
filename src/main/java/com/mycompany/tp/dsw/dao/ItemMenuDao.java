package com.mycompany.tp.dsw.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.tp.dsw.exception.VendedorNoEncontradoException;
import com.mycompany.tp.dsw.memory.CategoriaMemory;
import com.mycompany.tp.dsw.memory.VendedorMemory;
import com.mycompany.tp.dsw.model.Bebida;
import com.mycompany.tp.dsw.model.ItemMenu;
import com.mycompany.tp.dsw.model.Plato;
import com.mycompany.tp.dsw.model.Vendedor;
import com.mycompany.tp.dsw.service.MemoryManager;

public class ItemMenuDao {

    protected static List<ItemMenu> items;
    private static int currentID = 0;
    private static final MemoryManager memoryManager;
    private final static VendedorMemory vendedorMemory;
    private final static CategoriaMemory categoriaMemory;

    static {
        items = new ArrayList<>();
        memoryManager = MemoryManager.getInstance();
        vendedorMemory = memoryManager.getVendedorMemory();
        categoriaMemory = memoryManager.getCategoriaMemory();
        valoresInciales();
    }

    public ItemMenuDao() {

    }

    public static void valoresInciales() {
        Vendedor vendedor = vendedorMemory.buscarVendedorPorId(101);
        Plato platoEjemplo = new Plato(
                "Milanesa con Papas Fritas",
                850.0,
                true,
                true,
                false,
                500.0,
                101,
                new BigDecimal("12.50"),
                "Clásico plato argentino",
                categoriaMemory.obtenerCategoriaPorNombre("Comida Clasica"),
                vendedor);
        items.add(platoEjemplo);

        // Actualizar la lista del vendedor
        List<ItemMenu> listaVendedor = vendedor.getItemsMenu();

        listaVendedor.add(platoEjemplo);
        vendedor.setItemsMenu(listaVendedor);

    }

    public void add(ItemMenu itemMenu) {
        itemMenu.setId(currentID++);
        items.add(itemMenu);

        // Actualizar la lista del vendedor
        Vendedor vendedor = itemMenu.getVendedor();
        List<ItemMenu> listaVendedor = itemMenu.getVendedor().getItemsMenu();
        listaVendedor.add(itemMenu);
        vendedor.setItemsMenu(listaVendedor);
    }

    public List<ItemMenu> findByNombre(String nombre) {
        return items.stream().filter(i -> i.getNombre().contains(nombre))
                .toList();
    }

    public void update(ItemMenu itemMenu) {
        switch (itemMenu.getClass().getSimpleName()) {
            case "Plato":
                PlatoDao platoDao = new PlatoDao();
                platoDao.update((Plato) itemMenu);
                break;
            case "Bebida":
                BebidaDao bebidaDao = new BebidaDao();
                bebidaDao.update((Bebida) itemMenu);
                break;
            default:
                break;
        }

    }

    public void delete(Integer id) {
        items.removeIf(i -> i.getId().equals(id));
    }

    /**
     * OJO, no es obtener los item de un restaurante
     * - Obtiene todos los items del sistema
     * - Mirar filtrarPorVendedor()
     * 
     * @return Lista de todos los item del sistema
     */
    public List<ItemMenu> findAll() {
        return items;
    }

    /**
     * Obtiene los item de un restaurante
     * 
     * @param vendedor Restaurante a obtener sus items
     * @return Lista de items de un estaurante
     * @throws VendedorNoEncontradoException Si no encuentra el restaurante
     */
    public List<ItemMenu> findByVendedor(Vendedor vendedor) throws VendedorNoEncontradoException {

        Integer id = vendedor.getId();
        List<ItemMenu> itemsVendedor = items.stream()
                .filter(i -> i.getVendedor().getId().equals(id))
                .toList();

        if (itemsVendedor.isEmpty()) {
            throw new VendedorNoEncontradoException(
                    "No se ah encontrado un vendedor con ID: " + vendedor.getId() + " .O la lista esta vacia");
        }

        return itemsVendedor;
    }

    /**
     * Filtra por el id del itemMenu
     * 
     * @param id Id a buscar
     * @return El objeto cuyo id es el 'id', si no existe null
     */
    public ItemMenu findById(Integer id) {
        return items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        StringBuilder listaItemString = new StringBuilder();

        for (ItemMenu itemMenu : items) {
            listaItemString.append(itemMenu.toString()).append(" \n");
        }

        return listaItemString.toString();
    }
}
