package com.mycompany.tp.dsw.service;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.tp.dsw.dao.DAOFactory;
import com.mycompany.tp.dsw.dao.PlatoDao;
import com.mycompany.tp.dsw.dto.PlatoDto;
import com.mycompany.tp.dsw.model.ItemMenu;
import com.mycompany.tp.dsw.model.Plato;

public class PlatoService extends ItemMenuService {

    private DAOFactory factory;
    private PlatoDao platoDao;

    public PlatoService() {
        factory = DAOFactory.getInstance();
        platoDao = factory.getPlatoDao();
    }

    /**
     * Obtiene una lista de todos los platos activos del sistema.
     * 
     * @return Lista de objetos Plato.
     */
    public List<Plato> obtenerTodosLosPlatos() {
        List<ItemMenu> items = platoDao.findAllActive();

        List<Plato> platos = new ArrayList<>();
        for (ItemMenu item : items) {
            if (item instanceof Plato) {
                platos.add((Plato) item);
            }
        }

        return platos;
    }

    /**
     * Registra un nuevo plato en el sistema.
     * 
     * @param platoDto El objeto DTO que contiene los datos del plato a registrar.
     */
    public void registrarPlato(PlatoDto platoDto) {
        super.registrarItemMenu(platoDto);
    }

    /**
     * Obtiene todos los platos activos asociados a un vendedor especifico.
     * 
     * @param id El ID del vendedor.
     * @return Lista de platos pertenecientes al vendedor especificado.
     */
    public List<Plato> obtenerPlatoPorIdVendedor(Integer id) {
        List<Plato> platos = platoDao.findActiveByIdVendedor(id);
        return platos;
    }

    /**
     * Busca platos activos que coincidan con un nombre especifico para un vendedor.
     * Filtra los items de menu para devolver solo platos.
     * 
     * @param nombre El nombre del plato a buscar.
     * @param id     El ID del vendedor al que pertenece el plato.
     * @return Lista de platos que coinciden con los criterios de busqueda.
     */
    public List<Plato> buscarPlatoPorNombreYVendedor(String nombre, Integer id) {
        List<ItemMenu> items = buscarItemMenuPorNombreYVendedor(nombre, id);

        List<Plato> platos = new ArrayList<>();
        for (ItemMenu itemMenu : items) {
            if (itemMenu instanceof Plato) {
                platos.add((Plato) itemMenu);
            }
        }

        return platos;
    }

    /**
     * Busca un plato especifico por su ID y el ID del vendedor.
     * 
     * @param idPlato    El ID del plato.
     * @param idVendedor El ID del vendedor propietario del plato.
     * @return El plato encontrado o null si no existe.
     */
    public Plato buscarPlatoPorId(Integer idPlato, Integer idVendedor) {
        List<Plato> platos = obtenerPlatoPorIdVendedor(idVendedor);

        for (Plato plato : platos) {
            if (plato.getId().equals(idPlato)) {
                return plato;
            }
        }
        return null;

    }

    /**
     * Modifica/Actualiza los datos de un plato existente.
     * 
     * @param platoDto El DTO con los datos actualizados del plato.
     */
    public void modificarPlato(PlatoDto platoDto) {
        super.modificarItemMenu(platoDto);
    }

}
