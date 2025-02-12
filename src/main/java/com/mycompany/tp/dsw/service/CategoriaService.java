package com.mycompany.tp.dsw.service;

import java.util.List;

import com.mycompany.tp.dsw.dao.CategoriaDao;
import com.mycompany.tp.dsw.dao.DAOFactory;
import com.mycompany.tp.dsw.exception.CategoriaNoEncontradaException;
import com.mycompany.tp.dsw.model.Categoria;

public class CategoriaService {

        private DAOFactory factory;

        private CategoriaDao categoriaDao;

        public CategoriaService() {
                factory = DAOFactory.getInstance();
                categoriaDao = factory.getCategoriaDao();
        }

        /**
         * Busca una categoria basada en su nombre.
         * 
         * @param nombre Nombre de la categoria a buscar.
         * @return Categoria correspondiente al nombre proporcionado.
         */
        public Categoria obtenerCategoriaPorNombre(String nombre) {
                return categoriaDao.findByNombre(nombre);
        }

        /**
         * Obtiene la lista de todas las categorias persistidas en el sistema.
         * 
         * @return Lista de todas las categorias disponibles.
         */
        public List<Categoria> obtenerTodasLasCategorias() {
                return categoriaDao.findAll();
        }

        /**
         * Busca categorias filtradas por su tipo.
         * 
         * @param tipo Tipo de categoria a buscar.
         * @return Lista de categorias que coinciden con el tipo especificado.
         * @throws CategoriaNoEncontradaException Si no se encuentran categorias.
         */
        public List<Categoria> buscarPorTipoCategoria(String tipo) throws CategoriaNoEncontradaException {
                return categoriaDao.findByTipoCategoria(tipo);
        }

}
