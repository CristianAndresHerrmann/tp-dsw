/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.dsw.model;

import java.math.BigDecimal;

import com.mycompany.tp.dsw.dto.PlatoDto;

/**
 *
 * @author Cristian
 */
public class Plato extends ItemMenu {

    private Double calorias;
    private Boolean aptoCeliaco;
    private Boolean aptoVegetariano;
    private Boolean aptoVegano;
    private Double peso;

    // Constructor para agregar Plato
    /*
     * public Plato(PlatoDto platoDto) {
     * super(null,
     * platoDto.getNombre(),
     * platoDto.getDescripcion(),
     * platoDto.getPrecio(),
     * platoDto.getCategoria(),
     * platoDto.getVendedor());
     * this.calorias = platoDto.getCalorias();
     * this.aptoCeliaco = platoDto.getAptoCeliaco();
     * this.aptoVegetariano = platoDto.getAptoVegetariano();
     * this.aptoVegano = platoDto.getAptoVegano();
     * this.peso = platoDto.getPeso();
     * }
     */

    // Constructor para modificar Plato
    public Plato(PlatoDto platoDto) {
        super(platoDto.getId(),
                platoDto.getNombre(),
                platoDto.getDescripcion(),
                platoDto.getPrecio(),
                platoDto.getCategoria(),
                platoDto.getVendedor());
        this.calorias = platoDto.getCalorias();
        this.aptoCeliaco = platoDto.getAptoCeliaco();
        this.aptoVegetariano = platoDto.getAptoVegetariano();
        this.aptoVegano = platoDto.getAptoVegano();
        this.peso = platoDto.getPeso();
    }

    public Plato(String nombre, Double calorias, Boolean aptoCeliaco, Boolean aptoVegetariano, Boolean aptoVegano,
            Double peso,
            Integer id, BigDecimal precio, String descripcion, Categoria categoria, Vendedor vendedor) {
        super(id, nombre, descripcion, precio, categoria, vendedor);
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
        this.aptoVegetariano = aptoVegetariano;
        this.aptoVegano = aptoVegano;
        this.peso = peso;
    }

    public Plato(String nombre, Double calorias, Boolean aptoCeliaco, Boolean aptoVegetariano, Boolean aptoVegano,
            Double peso,
            Integer id, BigDecimal precio, String descripcion, Categoria categoria) {
        super(id, nombre, descripcion, precio, categoria);
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
        this.aptoVegetariano = aptoVegetariano;
        this.aptoVegano = aptoVegano;
        this.peso = peso;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public Boolean getAptoVegetariano() {
        return aptoVegetariano;
    }

    /**
     * Metodo que setea si el Plato es apto vegetariano
     * - Plato que no es apto vegetariano, tampoco es apto vegano
     * 
     * @param aptoVegetariano
     */
    public void setAptoVegetariano(Boolean aptoVegetariano) {
        this.aptoVegetariano = aptoVegetariano;
        if (!aptoVegetariano) {
            this.aptoVegano = false;
        }
    }

    /**
     * Es el valor del atributo, peso sin envase
     * - NO CONFUNDIR CON LA REGLA DE NEGOCIO DE PESO ( metodo peso() )
     * 
     * @return
     */
    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Boolean getAptoCeliaco() {
        return aptoCeliaco;
    }

    public void setAptoCeliaco(Boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    /**
     * Metodo que setea si el Plato es apto vegano
     * - Plato que es apto vegano, tambien es apto vegetariano
     * 
     * @param aptoVegano
     */
    public void setAptoVegano(Boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
        if (aptoVegano) {
            this.aptoVegetariano = true;
        }
    }

    /**
     * Metodo de la regla de negocio
     * - Tiene en cuenta el peso del envase, 10%
     * Formula -> <peso del plato> * 1.1
     * 
     * @return Valor del peso real, teniendo en cuenta el envase
     */
    @Override
    public Double peso() {
        return this.peso * 1.1;
    }

    /**
     * Indica si el item es de tipo Comida
     * 
     * @return 'true' siempre ya que es plato
     */
    @Override
    public boolean esComida() {
        return true;
    }

    /**
     * Indica si el item es de tipo Bebida
     * 
     * @return 'false' siempre ya que es plato
     */
    @Override
    public boolean esBebida() {
        return false;
    }

    /**
     * Regla de negocio
     * - Igual a un getAptoVegano
     */
    @Override
    public boolean aptoVegano() {
        return aptoVegano;
    }

    @Override
    public String toString() {
        return "ItemMenu [id=" + this.getId() + ", nombre=" + this.getNombre() + ", vendedor="
                + this.getVendedor().getId() + "]";
    }

}
