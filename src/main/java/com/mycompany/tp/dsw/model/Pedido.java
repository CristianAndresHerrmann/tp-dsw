/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.dsw.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.tp.dsw.patronObserver.Observable;
import com.mycompany.tp.dsw.patronObserver.Observer;

/**
 *
 * @author User
 */
public class Pedido implements Observable<Pedido> { // Pedido pedido por un cliente

    private Integer id;
    private List<ItemPedido> items;
    private Estado estado;
    private Cliente cliente;
    private Pago formaPago;
    private List<Observer<Pedido>> observadores = new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.estado = Estado.ACEPTADO;
        this.items = new ArrayList<>();
    }

    // ver luego en siguiente etapa el constructor
    public Pedido(Integer id, Estado estado, Cliente cliente) {
        this.id = id;
        this.items = new ArrayList<>();
        this.estado = estado;
        this.cliente = cliente;
    }

    public Pedido(Integer id, Estado estado, Cliente cliente, Pago formaPago) {
        this.id = id;
        this.items = new ArrayList<>();
        this.estado = estado;
        this.cliente = cliente;
        this.formaPago = formaPago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public Estado getEstado() {
        return estado;
    }

    /**
     * Setea nuevo estado del pedido y notifica a los observadores sobre el cambio.
     * 
     * 
     * @param estado El nuevo estado a asignar
     */

    public void setEstado(Estado estado) {
        this.estado = estado;
        notificarObservadores();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFormaPago(Pago formaPago) {
        this.formaPago = formaPago;
    }

    public Pago getFormaPago() {
        return formaPago;
    }

    /**
     * Calcula el costo total del pedido, segun el precio y la cantidad
     * Aplica el recargo dependiendo la forma de pago.
     * 
     * @return El monto total una vez aplicado el recargo de la forma de pago.
     */

    public BigDecimal total() {
        BigDecimal total = totalSinRecargo();
        return formaPago.pagar(total);
    }

    /**
     * Calcula el costo total del pedico, sin aplicar el recargo
     * 
     * @return El monto total sin recargo.
     */
    public BigDecimal totalSinRecargo() {
        return items.stream()
                .map(item -> item.getItemMenu().getPrecio().multiply(new BigDecimal(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Agrega un observador a la lista de observadores del pedido.
     * 
     * @param observer Es el observador que sera notificado
     */
    @Override
    public void addObserver(Observer<Pedido> observer) {
        this.observadores.add(observer);
    }

    /**
     * Notifica a todos los observadores sobre el cambio en el estado
     * Llama al metodo 'updateEstado' establecido en cada observador
     */
    @Override
    public void notificarObservadores() {

        for (Observer<Pedido> observer : observadores) {
            observer.evento(this);
        }
    }

    /**
     * Devuelve el mismo pedido
     */
    @Override
    public Pedido get() {
        return this;
    }

    public void agregarItemPedido(ItemPedido itemPedido) {
        items.add(itemPedido);
    }

    public Vendedor obtenerVendedor() {
        Vendedor vendedor = null;
        if (items != null && !items.isEmpty()) {
            vendedor = items.get(0).getItemMenu().getVendedor();
        }
        return vendedor;
    }

    public Integer cantidadItems() {
        return items.stream()
                .mapToInt(ItemPedido::getCantidad)
                .sum();
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", items=" + items + ", estado=" + estado + ", cliente=" + cliente + ", formaPago="
                + formaPago + "]";
    }

}
