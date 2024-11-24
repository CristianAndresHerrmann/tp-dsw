package com.mycompany.tp.dsw.dto;

import com.mycompany.tp.dsw.model.Cliente;

public class PedidoDto {

    private String idCliente;

    private Cliente cliente;

    public PedidoDto(String idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

}
