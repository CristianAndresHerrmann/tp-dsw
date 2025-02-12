package com.mycompany.tp.dsw.service;

import java.util.List;

import com.mycompany.tp.dsw.dao.CategoriaDao;
import com.mycompany.tp.dsw.dao.DAOFactory;
import com.mycompany.tp.dsw.dao.ItemMenuDao;
import com.mycompany.tp.dsw.dto.BebidaDto;
import com.mycompany.tp.dsw.dto.ItemMenuDto;
import com.mycompany.tp.dsw.dto.PlatoDto;
import com.mycompany.tp.dsw.dto.VendedorDto;
import com.mycompany.tp.dsw.exception.VendedorNoEncontradoException;
import com.mycompany.tp.dsw.model.Bebida;
import com.mycompany.tp.dsw.model.Categoria;
import com.mycompany.tp.dsw.model.ItemMenu;
import com.mycompany.tp.dsw.model.Plato;
import com.mycompany.tp.dsw.model.Vendedor;

public class ItemMenuService {

    private DAOFactory factory;

    private CategoriaDao categoriaDao;
    private ItemMenuDao itemMenuDao;
    private VendedorService vendedorService;
    private ServiceManager serviceManager;

    public ItemMenuService() {
        factory = DAOFactory.getInstance();

        itemMenuDao = factory.getItemMenuDao();
        categoriaDao = factory.getCategoriaDao();
        serviceManager = ServiceManager.getInstance();
        vendedorService = serviceManager.getVendedorService();

    }

    /**
     * Crea y persiste un ItemMenu.
     * - ItemMenu = Plato o Bebida.
     * - Convierte solo los atributos de ItemMenu.
     * - Los de la subclase en su respectivo service.
     * 
     * @param itemMenuDto Objeto DTO del ItemMenu
     */
    protected void registrarItemMenu(ItemMenuDto itemMenuDto) {

        Vendedor vendedor = vendedorService.buscarPorIdConListaItem(itemMenuDto.getIdVendedor());
        ItemMenu itemMenu = mapToModel(itemMenuDto, vendedor);
        vendedor.getItemsMenu().add(itemMenu);
        itemMenuDao.save(itemMenu);
    }

    /**
     * Busca el item filtrado por el nombre.
     * - Ignora mayusculas y minusculas.
     * 
     * @param nombre Nombre del item
     * @param id     ID del vendedor
     * @return Lista de los items que coinciden con el nombre
     */
    public List<ItemMenu> buscarItemMenuPorNombreYVendedor(String nombre, Integer id) {
        return itemMenuDao.findActiveByNombreAndVendedor(nombre, id);
    }

    /**
     * Modifica los datos de un item especifico.
     * - Del objeto itemMenu pasado como parametro.
     * - Solo los datos a modificar permanecen no nulos.
     * - Convierte solo los atributos de ItemMenu.
     * - Los de la subclase en su respectivo service.
     * 
     * @param itemMenuDto Objeto DTO del ItemMenu con los datos modificados
     */
    protected void modificarItemMenu(ItemMenuDto itemMenuDto) {
        Vendedor vendedor = vendedorService.buscarPorIdConListaItem(itemMenuDto.getIdVendedor());
        ItemMenu itemMenu = mapToModel(itemMenuDto, vendedor); // ver si puede ser null el vendedor
        vendedor.getItemsMenu().add(itemMenu);
        itemMenuDao.update(itemMenu);
    }

    /**
     * Elimina logicamente un item del restaurante, segun el id.
     * - Eliminar = setear atributo activo a false.
     * 
     * @param id ID del item a eliminar
     */
    public void eliminarItemMenu(Integer id) {
        ItemMenu item = itemMenuDao.findByIdAndActive(id);
        itemMenuDao.deleteLogico(item);
    }

    /**
     * Obtiene una lista de todos los items activos del sistema.
     * - No son los items de un restaurante especifico.
     * - Mirar metodo filtrarPorVendedor().
     * 
     * @return Lista con los items activos
     */
    public List<ItemMenu> obtenerTodosLosItemMenu() {
        return itemMenuDao.findAllActive();
    }

    /**
     * Busca los items segun un restaurante.
     * 
     * @param vendedorDto DTO del restaurante
     * @return Lista de items que tiene el restaurante
     * @throws VendedorNoEncontradoException Si no se encuentra el restaurante
     */
    public List<ItemMenu> filtrarPorVendedor(VendedorDto vendedorDto) throws VendedorNoEncontradoException {
        return itemMenuDao.findByVendedorId(vendedorDto.getId());
    }

    /**
     * Busca un item especifico por su ID.
     * 
     * @param idItem ID del item a buscar
     * @return ItemMenu correspondiente al ID
     */
    public ItemMenu buscarPorId(Integer idItem) {
        return itemMenuDao.findById(idItem);
    }

    /**
     * Convierte un ItemMenuDto en un objeto ItemMenu.
     * - Incluye la asignacion de categoria y vendedor.
     * - Maneja subtipos como Plato y Bebida.
     * 
     * @param itemMenuDto DTO del ItemMenu
     * @param vendedor    Objeto Vendedor asociado
     * @return Objeto ItemMenu correspondiente al DTO
     */
    private ItemMenu mapToModel(ItemMenuDto itemMenuDto, Vendedor vendedor) {
        Categoria categoria = categoriaDao.findByNombre(itemMenuDto.getCategoria());

        switch (itemMenuDto.getClass().getSimpleName()) {
            case "PlatoDto":
                PlatoDto platoDto = (PlatoDto) itemMenuDto;
                return Plato.builder()
                        .calorias(platoDto.getCalorias())
                        .aptoCeliaco(platoDto.getAptoCeliaco())
                        .aptoVegetariano(platoDto.getAptoVegetariano())
                        .aptoVegano(platoDto.getAptoVegano())
                        .peso(platoDto.getPeso())
                        .id(platoDto.getId())
                        .nombre(platoDto.getNombre())
                        .descripcion(platoDto.getDescripcion())
                        .precio(platoDto.getPrecio())
                        .categoria(categoria)
                        .vendedor(vendedor)
                        .build();
            case "BebidaDto":
                BebidaDto bebidaDto = (BebidaDto) itemMenuDto;
                return Bebida.builder()
                        .graduacionAlcoholica(bebidaDto.getGraduacionAlcoholica())
                        .tamano(bebidaDto.getTamano())
                        .volumen(bebidaDto.getVolumen())
                        .id(bebidaDto.getId())
                        .nombre(bebidaDto.getNombre())
                        .descripcion(bebidaDto.getDescripcion())
                        .precio(bebidaDto.getPrecio())
                        .categoria(categoria)
                        .vendedor(vendedor)
                        .build();

            default:
                return null;
        }
    }
}
