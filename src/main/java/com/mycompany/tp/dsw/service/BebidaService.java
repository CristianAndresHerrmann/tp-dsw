package com.mycompany.tp.dsw.service;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.tp.dsw.dao.BebidaDao;
import com.mycompany.tp.dsw.dao.DAOFactory;
import com.mycompany.tp.dsw.dto.BebidaDto;
import com.mycompany.tp.dsw.model.Bebida;
import com.mycompany.tp.dsw.model.ItemMenu;

public class BebidaService extends ItemMenuService {

    private DAOFactory factory;

    private BebidaDao bebidaDao;

    public BebidaService() {
        factory = DAOFactory.getInstance();
        bebidaDao = factory.getBebidaDao();
    }

    /**
     * Obtiene una lista de todas las bebidas disponibles del sistema.
     * 
     * @return Lista de bebidas
     */
    public List<Bebida> obtenerTodasLasBebidas() {
        List<ItemMenu> items = bebidaDao.findAllActive();

        List<Bebida> bebidas = new ArrayList<>();
        for (ItemMenu item : items) {
            if (item instanceof Bebida) {
                bebidas.add((Bebida) item);
            }
        }

        return bebidas;
    }

    /**
     * Obtiene una lista de bebidas filtradas por el id del vendedor.
     * 
     * @param id Id del vendedor
     * @return Lista de bebidas del vendedor
     */
    public List<Bebida> obtenerBebidaPorIdVendedor(Integer id) {
        return bebidaDao.findActiveByIdVendedor(id);
    }

    /**
     * Busca bebidas filtradas por su nombre y el id del vendedor.
     * 
     * @param nombre Nombre de la bebida
     * @param id     Id del vendedor
     * @return Lista de bebidas que coinciden con el nombre y el vendedor
     */
    public List<Bebida> buscarBebidaPorNombreYVendedor(String nombre, Integer id) {
        List<ItemMenu> items = buscarItemMenuPorNombreYVendedor(nombre, id);
        List<Bebida> bebidas = new ArrayList<>();
        for (ItemMenu itemMenu : items) {
            if (itemMenu instanceof Bebida) {
                bebidas.add((Bebida) itemMenu);
            }
        }
        return bebidas;
    }

    /**
     * Busca una bebida especifica por su id y el id del vendedor.
     * 
     * @param idBebida   Id de la bebida
     * @param idVendedor Id del vendedor
     * @return Bebida correspondiente o null si no se encuentra
     */
    public Bebida buscarBebidaPorId(Integer idBebida, Integer idVendedor) {
        List<Bebida> bebidas = obtenerBebidaPorIdVendedor(idVendedor);
        for (Bebida bebida : bebidas) {
            if (bebida.getId().equals(idBebida)) {
                return bebida;
            }
        }
        return null;
    }

    /**
     * Registra una nueva bebida en el sistema.
     * 
     * @param bebidaDto DTO de la bebida a registrar
     */
    public void registrarBebida(BebidaDto bebidaDto) {
        super.registrarItemMenu(bebidaDto);
    }

    /**
     * Modifica los datos de una bebida existente.
     * 
     * @param bebidaDto DTO de la bebida con los datos actualizados
     */
    public void modificarBebida(BebidaDto bebidaDto) {
        super.modificarItemMenu(bebidaDto);
    }

}
