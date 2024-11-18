package com.mycompany.tp.dsw.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mycompany.tp.dsw.dto.VendedorDto;
import com.mycompany.tp.dsw.exception.VendedorNoEncontradoException;
import com.mycompany.tp.dsw.model.Coordenada;
import com.mycompany.tp.dsw.model.Vendedor;
import com.mycompany.tp.dsw.service.ValidarVendedor;

public class VendedorDao {

    private static List<Vendedor> vendedores = new ArrayList<>();
    private int currentId = 0;

    static {
        valoresInciales();
    }

    public VendedorDao() {

    }

    public static void valoresInciales() {
        VendedorDto vendedorDto1 = new VendedorDto("La Tienda de Ana", "Av. San Martín 1234", null, null);
        vendedorDto1.setCoordenada(new Coordenada(-34.6037, -58.3816));
        vendedorDto1.setId(101);

        VendedorDto vendedorDto2 = new VendedorDto("El Rincón Gourmet", "Calle Falsa 123", null, null);
        vendedorDto2.setCoordenada(new Coordenada(40.7128, -74.0060));
        vendedorDto2.setId(102);

        VendedorDto vendedorDto3 = new VendedorDto("Mercado Central", "Plaza Mayor, Madrid", null, null);
        vendedorDto3.setCoordenada(new Coordenada(40.4168, -3.7038));
        vendedorDto3.setId(103);

        VendedorDto vendedorDto4 = new VendedorDto("Sabores del Mundo", "Av. Córdoba 5555", null, null);
        vendedorDto4.setCoordenada(new Coordenada(-34.5885, -58.4333));
        vendedorDto4.setId(104);

        VendedorDto vendedorDto5 = new VendedorDto("Bazar de Antonia", "Las Heras 980", null, null);
        vendedorDto5.setCoordenada(new Coordenada(-33.4691, -70.641));
        vendedorDto5.setId(105);

        vendedores.add(new Vendedor(vendedorDto1));
        vendedores.add(new Vendedor(vendedorDto2));
        vendedores.add(new Vendedor(vendedorDto3));
        vendedores.add(new Vendedor(vendedorDto4));
        vendedores.add(new Vendedor(vendedorDto5));

    }

    public void add(Vendedor vendedor) {
        vendedor.setId(currentId++);
        vendedores.add(vendedor);
    }

    public List<Vendedor> findByNombre(String nombre) {
        List<Vendedor> vendedor = vendedores.stream()
                .filter(v -> v.getNombre().toLowerCase().contains(nombre.toLowerCase()) && v.getActivo().equals(true))
                .toList();
        return vendedor;
    }

    public void update(Vendedor vendedor) throws VendedorNoEncontradoException {
        Vendedor existeVendedor = findById(vendedor.getId());
        if (existeVendedor == null) {
            throw new VendedorNoEncontradoException("No se ha encontrado un vendedor con el ID:" + vendedor.getId());
        }
        String direccion = vendedor.getDireccion().trim();
        if (!ValidarVendedor.esNullOrEmpty(direccion)) {
            // Si la direccion no se modifico, no se modifica la Coordenada Y visceversa
            // Precondicion al metodo:
            // - Coordenada (latitud y longitud) no nulo
            // - Y Direccion no nulo
            existeVendedor.setDireccion(direccion);
            existeVendedor.setCoordenada(vendedor.getCoordenada());
        }

        String nombre = vendedor.getNombre().trim();
        if (nombre != null) {
            existeVendedor.setNombre(nombre);
        }

    }

    public void delete(Integer id) throws VendedorNoEncontradoException {
        Vendedor vendedor = findById(id);

        if (vendedor != null) {
            vendedor.setActivo(false);
            vendedor.setFechaEliminacion(LocalDate.now());
        } else {
            throw new VendedorNoEncontradoException("No se ha encontrado un vendedor con el ID: " + id);
        }
    }

    public List<Vendedor> findAll() {
        if (!vendedores.isEmpty()) {
            return vendedores.stream()
                    .filter(v -> v.getActivo().equals(true))
                    .toList();
        }
        return null;
    }

    public Vendedor findById(Integer id) {
        return vendedores.stream()
                .filter(v -> Objects.equals(v.getId(), id) && v.getActivo().equals(true))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        for (Vendedor vendedor : vendedores) {
            ret.append(vendedor.toString()).append("\n");
        }

        return ret.toString();
    }
}
