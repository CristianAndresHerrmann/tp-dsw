/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.dsw.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Cristian
 */
public abstract class ItemMenu { // Items que hay en un restaurante/vendedor
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Categoria categoria;
    private Vendedor vendedor;
    private Integer stock;

    private Boolean activo = true;
    private LocalDateTime fechaEliminacion = null;

    public abstract Double peso();

    public abstract boolean esComida();

    public abstract boolean esBebida();

    public abstract boolean aptoVegano();

    public ItemMenu(Integer id, String nombre, String descripcion, BigDecimal precio, Categoria categoria,
            Vendedor vendedor) { //
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.vendedor = vendedor;
    }

    public ItemMenu(Integer id, String nombre, String descripcion, BigDecimal precio, Categoria categoria) { //
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(LocalDateTime fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
