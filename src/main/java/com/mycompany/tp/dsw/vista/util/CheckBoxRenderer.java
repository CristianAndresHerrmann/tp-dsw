package com.mycompany.tp.dsw.vista.util;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
    private final JLabel textLabel; // Etiqueta para mostrar texto en lugar de un checkbox

    public CheckBoxRenderer() {
        setHorizontalAlignment(CENTER);
        textLabel = new JLabel();
        textLabel.setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (value instanceof Boolean) {
            // Mostrar el checkbox
            setSelected((boolean) value);
            return this;
        } else {
            // Mostrar el texto
            textLabel.setText(value != null ? value.toString() : "");
            return textLabel;
        }
    }
}
