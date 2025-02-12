package com.mycompany.tp.dsw.dao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final BebidaDao bebidaDao = new BebidaDao();
    private final CategoriaDao categoriaDao = new CategoriaDao();
    private final ClienteDao clienteDao = new ClienteDao();
    private final ItemMenuDao itemMenuDao = new ItemMenuDao();
    private final ItemsPedidoDao itemsPedidoDao = new ItemsPedidoDao();
    private final PedidoDao pedidoDao = new PedidoDao();
    private final PlatoDao platoDao = new PlatoDao();
    private final VendedorDao vendedorDao = new VendedorDao();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public BebidaDao getBebidaDao() {
        return bebidaDao;
    }

    public CategoriaDao getCategoriaDao() {
        return categoriaDao;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public ItemMenuDao getItemMenuDao() {
        return itemMenuDao;
    }

    public ItemsPedidoDao getItemsPedidoDao() {
        return itemsPedidoDao;
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public PlatoDao getPlatoDao() {
        return platoDao;
    }

    public VendedorDao getVendedorDao() {
        return vendedorDao;
    }
}
