package com.mycompany.tp.dsw.service;

import java.util.List;

import com.mycompany.tp.dsw.dao.ClienteDao;
import com.mycompany.tp.dsw.dao.DAOFactory;
import com.mycompany.tp.dsw.dto.ClienteDto;
import com.mycompany.tp.dsw.model.Cliente;
import com.mycompany.tp.dsw.model.Coordenada;

public class ClienteService {

    private DAOFactory factory;
    private ClienteDao clienteDao;

    public ClienteService() {
        factory = DAOFactory.getInstance();
        clienteDao = factory.getClienteDao();
    }

    /**
     * Registra y persiste un nuevo cliente en el sistema.
     * 
     * @param clienteDto DTO con la informacion del cliente a registrar.
     */
    public void registrarCliente(ClienteDto clientedto) {
        Cliente cliente = mapToModel(clientedto);
        clienteDao.save(cliente);
    }

    /**
     * Busca clientes activos cuyo nombre coincida parcialmente con el criterio.
     * - Ignora mayusculas y minusculas.
     * 
     * @param nombre Nombre o parte del nombre a buscar.
     * @return Lista de clientes que cumplen con el criterio de busqueda.
     */
    public List<Cliente> buscarClientePorNombre(String nombre) {
        return clienteDao.findActiveByNombre(nombre);
    }

    /**
     * Actualiza la informacion de un cliente existente.
     * - Sobrescribe los datos del cliente con los valores proporcionados en el DTO.
     * 
     * @param clienteDto DTO con los datos actualizados del cliente.
     */
    public void modificarCliente(ClienteDto clienteDto) {
        Cliente cliente = mapToModel(clienteDto);
        clienteDao.update(cliente);
    }

    /**
     * Busca un cliente activo segun su identificador unico.
     * 
     * @param id Identificador unico del cliente.
     * @return Cliente encontrado o null si no existe.
     */
    public Cliente buscarClientePorId(Integer id) {
        return clienteDao.findByIdAndActive(id);
    }

    /**
     * Elimina logicamente un cliente estableciendo su estado como inactivo.
     * 
     * @param id Identificador unico del cliente a eliminar.
     */
    public void eliminarCliente(Integer id) {
        Cliente cliente = clienteDao.findById(id);
        clienteDao.deleteLogico(cliente);
    }

    /**
     * Obtiene todos los clientes activos en el sistema.
     * 
     * @return Lista de clientes activos.
     */
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDao.findAllActive();
    }

    /**
     * Convierte un objeto ClienteDto en un modelo Cliente.
     * 
     * @param clienteDto DTO con la informacion a convertir.
     * @return Objeto Cliente listo para persistir o manipular.
     */
    private Cliente mapToModel(ClienteDto clienteDto) {

        Coordenada coordenada = Coordenada.builder()
                .latitud(clienteDto.getCoordenadaDto().getLatitud())
                .longitud(clienteDto.getCoordenadaDto().getLongitud())
                .build();

        return Cliente.builder()
                .id(clienteDto.getId())
                .nombre(clienteDto.getNombre())
                .cuit(clienteDto.getCuit())
                .direccion(clienteDto.getDireccion())
                .email(clienteDto.getEmail())
                .coordenada(coordenada)
                .build();
    }

}
