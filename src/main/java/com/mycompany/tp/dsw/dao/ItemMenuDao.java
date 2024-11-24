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

        Plato plato1 = new Plato(
                "Pizza Margarita",
                1000.0,
                true,
                false,
                true,
                700.0,
                101,
                new BigDecimal("15.00"),
                "Pizza clásica con queso, tomate y albahaca",
                categoriaMemory.obtenerCategoriaPorNombre("Comida Clasica"),
                vendedor);

        Plato plato2 = new Plato(
                "Hamburguesa Vegana",
                900.0,
                true,
                true,
                true,
                400.0,
                101,
                new BigDecimal("18.50"),
                "Hamburguesa elaborada con ingredientes 100% veganos",
                categoriaMemory.obtenerCategoriaPorNombre("Comida Vegana"),
                vendedor);

        Plato plato3 = new Plato(
                "Ensalada César",
                750.0,
                false,
                true,
                true,
                300.0,
                101,
                new BigDecimal("12.00"),
                "Ensalada con lechuga, croutons y aderezo César",
                categoriaMemory.obtenerCategoriaPorNombre("Comida Vegetariana"),
                vendedor);

        Plato plato4 = new Plato(
                "Limonada Clásica",
                500.0,
                true,
                false,
                true,
                250.0,
                101,
                new BigDecimal("8.50"),
                "Refrescante bebida sin alcohol con limón",
                categoriaMemory.obtenerCategoriaPorNombre("Comida Vegana"),
                vendedor);

        Plato plato5 = new Plato(
                "Cerveza Artesanal",
                1200.0,
                true,
                false,
                false,
                600.0,
                101,
                new BigDecimal("25.00"),
                "Cerveza artesanal con notas de malta y lúpulo",
                categoriaMemory.obtenerCategoriaPorNombre("Comida Vegana"),
                vendedor);

        // Agregar los ítems a la lista general
        items.add(plato1);
        items.add(plato2);
        items.add(plato3);
        items.add(plato4);
        items.add(plato5);

        // Actualizar la lista de ítems del vendedor
        List<ItemMenu> listaVendedor = vendedor.getItemsMenu();
        listaVendedor.add(plato1);
        listaVendedor.add(plato2);
        listaVendedor.add(plato3);
        listaVendedor.add(plato4);
        listaVendedor.add(plato5);
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
