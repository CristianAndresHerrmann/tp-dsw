/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.dsw.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.mycompany.tp.dsw.dto.ClienteDto;
import com.mycompany.tp.dsw.patronObserver.Observer;

/**
 *
 * @author Cristian
 */
public class Cliente implements Observer<Pedido> {

    private Integer id;
    private String nombre;
    private String cuit;
    private String direccion;
    private String email;
    private Coordenada coordenada;

    private LocalDate fechaRegistro = LocalDate.now();
    private Boolean activo = true;
    private LocalDate fechaEliminacion = null;

    public Cliente() {

    }

    public Cliente(ClienteDto clienteDto) {
        this.id = clienteDto.getId();
        this.nombre = clienteDto.getNombre();
        this.cuit = clienteDto.getCuit();
        this.direccion = clienteDto.getDireccion();
        this.coordenada = clienteDto.getCoordenada();
        this.email = clienteDto.getEmail();
    }

    public Cliente(int id, String nombre, String cuit, String direccion, String email, Coordenada coordenada) {
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
        this.direccion = direccion;
        this.email = email;
        this.coordenada = coordenada;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public LocalDate getFechaEliminacion() {
        return fechaEliminacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nommbre) {
        this.nombre = nommbre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    /**
     * Metodo que actua como observador del Cliente
     * Ejecutado cuando se cambia el estado del pedido del cliente
     * - Si el estado cambia a ENVIADO, se genera el PAGO correspondiente
     * 
     * @param pedido El pedido cuyo estado ha cambiado
     */

    @Override
    public void evento(Pedido pedido) {

        System.out.println("Se le notifica a " + nombre + ": El estado de su pedido (ID: " + pedido.getId()
                + ") ha cambiado a " + pedido.getEstado());

        if (pedido.getEstado().equals(Estado.ENVIADO)) {
            generarPago(pedido);
        }
    }

    /**
     * Metodo que genera y configura el pago para el pedido del cliente
     * - Dependiendo del metodo de pago, crea y lo establece al pedido
     * - Soporta pagos MercadoPago y Transferencia
     * 
     * @param pedido El pedido por el cual se debera generar el pago
     * @throws IllegalArgumentException Si el monto es negativo o si el tipo de pago
     *                                  no es soportado
     */

    private void generarPago(Pedido pedido) {

        BigDecimal monto = pedido.total();

        if (monto.compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalArgumentException("Monto negativo");
        }

        if (pedido.getFormaPago() instanceof MercadoPago) {
            MercadoPago mercadoPago = new MercadoPago("miAlias");
            mercadoPago.setMonto(monto);
            mercadoPago.setFecha(LocalDate.now());
            pedido.setFormaPago(mercadoPago);

        } else if (pedido.getFormaPago() instanceof Transferencia) {
            Transferencia transferencia = new Transferencia("cbu123", "20-123456-3");
            transferencia.setMonto(monto);
            transferencia.setFecha(LocalDate.now());
            pedido.setFormaPago(transferencia);

        } else {
            throw new IllegalArgumentException("Tipo de pago no seteado o no soportado");
        }
    }

}
