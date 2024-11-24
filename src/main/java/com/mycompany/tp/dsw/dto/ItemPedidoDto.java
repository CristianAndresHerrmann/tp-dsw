package com.mycompany.tp.dsw.dto;

import com.mycompany.tp.dsw.model.ItemMenu;

public class ItemPedidoDto {

    private Integer cantidad;
    private ItemMenu itemMenu;

    private String itemMenuText;

    public String getItemMenuText() {
        return itemMenuText;
    }

    public void setItemMenuText(String itemMenuText) {
        this.itemMenuText = itemMenuText;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ItemMenu getItemMenu() {
        return itemMenu;
    }

    public void setItemMenu(ItemMenu itemMenu) {
        this.itemMenu = itemMenu;
    }
}
