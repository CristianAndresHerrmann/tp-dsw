package com.mycompany.tp.dsw.service;

import java.util.List;

import com.mycompany.tp.dsw.dao.DAOFactory;
import com.mycompany.tp.dsw.dao.VendedorDao;
import com.mycompany.tp.dsw.dto.VendedorDto;
import com.mycompany.tp.dsw.exception.VendedorNoEncontradoException;
import com.mycompany.tp.dsw.model.Coordenada;
import com.mycompany.tp.dsw.model.Vendedor;

public class VendedorService {

    private DAOFactory factory;
    private VendedorDao vendedorDao;

    public VendedorService() {
        factory = DAOFactory.getInstance();
        vendedorDao = factory.getVendedorDao();
    }

    /**
     * Registra un nuevo vendedor en el sistema.
     * Convierte el DTO recibido en objeto dominio y se persiste.
     * 
     * @param vendedorDto El objeto DTO que contiene los datos del vendedor a
     *                    registrar.
     */
    public void registrarVendedor(VendedorDto vendedorDto) {
        Vendedor vendedor = parseVendedor(vendedorDto);
        vendedorDao.save(vendedor);
    }

    /**
     * Busca vendedores activos por nombre.
     * 
     * @param nombre El texto a buscar dentro de los nombres de los vendedores.
     * @return Una lista de vendedores activos que coincidan con el criterio de
     *         busqueda.
     */
    public List<Vendedor> buscarVendedorPorNombre(String nombre) {
        return vendedorDao.findActiveByNombre(nombre);
    }

    /**
     * Busca un vendedor activo por su ID e incluye su lista de items del menu.
     * 
     * @param id El ID del vendedor a buscar.
     * @return El objeto Vendedor que contiene la informacion del vendedor y sus
     *         items.
     */
    public Vendedor buscarPorIdConListaItem(Integer id) {
        return vendedorDao.findActiveByIdWithItemsMenu(id);
    }

    /**
     * Modifica los datos de un vendedor especifico.
     * Solo actualiza los campos no nulos proporcionados en el DTO.
     * 
     * @param vendedorDto El objeto DTO con los datos del vendedor a modificar.
     */
    public void modificarVendedor(VendedorDto vendedorDto) {

        Vendedor vendedor = parseVendedor(vendedorDto);
        vendedorDao.update(vendedor);
    }

    /**
     * Busca un vendedor activo por su ID.
     * 
     * @param id El ID del vendedor a buscar.
     * @return El objeto Vendedor encontrado, o null si no existe un vendedor con
     *         ese ID.
     */
    public Vendedor buscarVendedorPorId(Integer id) {
        return vendedorDao.findByIdAndActive(id);
    }

    /**
     * Realiza la eliminacion logica de un vendedor.
     * Marca el vendedor como inactivo en lugar de eliminarlo fisicamente.
     * 
     * @param id El ID del vendedor a eliminar.
     * @throws VendedorNoEncontradoException Si el vendedor no existe o ya esta
     *                                       eliminado.
     */
    public void eliminarVendedor(Integer id) {
        Vendedor vendedor = buscarVendedorPorId(id);
        vendedorDao.deleteLogico(vendedor);
    }

    /**
     * Obtiene una lista de todos los vendedores activos en el sistema.
     * 
     * @return Una lista con todos los vendedores activos.
     */
    public List<Vendedor> obtenerTodosLosVendedores() {
        return vendedorDao.findAllActive();
    }

    /**
     * Convierte un objeto DTO de Vendedor a un objeto de dominio Vendedor.
     * 
     * @param vendedorDto El DTO que contiene los datos del vendedor.
     * @return Un objeto de dominio Vendedor creado a partir del DTO.
     */
    public static Vendedor parseVendedor(VendedorDto vendedorDto) {

        Coordenada coordenada = Coordenada.builder()
                .latitud(vendedorDto.getCoordenadaDto().getLatitud())
                .longitud(vendedorDto.getCoordenadaDto().getLongitud())
                .build();

        return Vendedor.builder()
                .id(vendedorDto.getId())
                .nombre(vendedorDto.getNombre())
                .direccion(vendedorDto.getDireccion())
                .coordenada(coordenada)
                .build();
    }

}
