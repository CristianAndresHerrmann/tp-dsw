package com.mycompany.tp.dsw.vista.util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellRenderer;

public class SpinnerCellRenderer extends JSpinner implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1)); // Modelo ajustable
        spinner.setValue(value); // Setear el valor actual de la celda

        // Ajustar tamaño y alineación
        spinner.setPreferredSize(new Dimension(50, spinner.getPreferredSize().height));

        if (isSelected) {
            spinner.setBackground(table.getSelectionBackground());
        } else {
            spinner.setBackground(table.getBackground());
        }

        return spinner;
    }
}
