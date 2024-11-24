/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.dsw.vista.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Usuario
 */
public class HeaderFormatter extends DefaultTableCellRenderer {
    
    public HeaderFormatter() {
        setHorizontalAlignment(CENTER); // Centrar texto
        setOpaque(true); // Asegura que el fondo se pinte
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Aplica las configuraciones al componente
        c.setFont(new Font("Dialog", Font.BOLD, 14)); // Negrita y tamaño
        c.setBackground(Color.LIGHT_GRAY);           // Color de fondo
        c.setForeground(Color.BLACK);                // Color de texto
        
        setBorder(new MatteBorder(0, 1, 1, 0, Color.BLACK));

        return c;
    }
}