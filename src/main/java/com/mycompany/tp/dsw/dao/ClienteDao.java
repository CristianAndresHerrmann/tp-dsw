package com.mycompany.tp.dsw.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.tp.dsw.exception.ClienteNoEncontradoException;
import com.mycompany.tp.dsw.model.Cliente;
import com.mycompany.tp.dsw.model.Coordenada;

public class ClienteDao {

    private static List<Cliente> clientes = new ArrayList<>();
    private static int currentID = 0;

    static {
        valoresInciales();
    }

    public static void valoresInciales() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(101);
        cliente1.setNombre("Juan Perez");
        cliente1.setCuit("20-12345678-9");
        cliente1.setDireccion("Calle Falsa 123");
        cliente1.setEmail("juan.perez@example.com");
        cliente1.setCoordenada(new Coordenada(-34.603722, -58.381592)); // Buenos Aires

        Cliente cliente2 = new Cliente();
        cliente2.setId(102);
        cliente2.setNombre("Maria Lopez");
        cliente2.setCuit("27-87654321-4");
        cliente2.setDireccion("Av. Siempre Viva 742");
        cliente2.setEmail("maria.lopez@example.com");
        cliente2.setCoordenada(new Coordenada(-34.615803, -58.432345)); // Buenos Aires Oeste

        Cliente cliente3 = new Cliente();
        cliente3.setId(103);
        cliente3.setNombre("Carlos Gomez");
        cliente3.setCuit("23-11223344-5");
        cliente3.setDireccion("San Martin 500");
        cliente3.setEmail("carlos.gomez@example.com");
        cliente3.setCoordenada(new Coordenada(-34.620123, -58.390876)); // Sur de Buenos Aires

        Cliente cliente4 = new Cliente();
        cliente4.setId(104);
        cliente4.setNombre("Laura Fernandez");
        cliente4.setCuit("25-55667788-1");
        cliente4.setDireccion("Av. Corrientes 800");
        cliente4.setEmail("laura.fernandez@example.com");
        cliente4.setCoordenada(new Coordenada(-34.601234, -58.381345)); // Centro de Buenos Aires

        Cliente cliente5 = new Cliente();
        cliente5.setId(105);
        cliente5.setNombre("Pedro Martinez");
        cliente5.setCuit("21-33445566-0");
        cliente5.setDireccion("Belgrano 300");
        cliente5.setEmail("pedro.martinez@example.com");
        cliente5.setCoordenada(new Coordenada(-34.582345, -58.402678)); // Norte de Buenos Aires

        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        clientes.add(cliente4);
        clientes.add(cliente5);
    }

    public void add(Cliente cliente) {
        cliente.setId(currentID++);
        clientes.add(cliente);
    }

    public List<Cliente> findByNombre(String nombre) {
        List<Cliente> clienteBuscado = clientes.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
        return clienteBuscado;
    }

    public void update(Cliente cliente) throws ClienteNoEncontradoException {

        Cliente existeCliente = findById(cliente.getId());

        if (existeCliente == null) {
            throw new ClienteNoEncontradoException("Cliente con ID " + cliente.getId() + " no encontrado.");
        }

        String nombreModificado = cliente.getNombre().trim();
        String cuitModificado = cliente.getCuit();
        String emailModificado = cliente.getEmail();

        if (nombreModificado != null)
            existeCliente.setNombre(nombreModificado);
        if (cuitModificado != null)
            existeCliente.setCuit(cuitModificado);
        if (emailModificado != null)
            existeCliente.setEmail(emailModificado);
    }

    public void delete(Integer id) throws ClienteNoEncontradoException {
        boolean clienteEliminado = clientes.removeIf(c -> c.getId().equals(id));

        if (!clienteEliminado) {
            throw new ClienteNoEncontradoException("No se encontro el cliente con ID: " + id);
        } else {
            System.out.println("Cliente con ID: " + id + " borrado correctamente.");
        }
    }

    public List<Cliente> findAll() {
        return clientes;
    }

    public Cliente findById(Integer id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
