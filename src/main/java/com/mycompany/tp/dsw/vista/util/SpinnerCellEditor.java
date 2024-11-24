package com.mycompany.tp.dsw.vista.util;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellEditor;

public class SpinnerCellEditor extends AbstractCellEditor implements TableCellEditor {
    private final JSpinner spinner;

    public SpinnerCellEditor() {
        // Configurar el modelo con valores mínimos, máximos y paso
        spinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        spinner.setValue(value); // Establecer el valor actual de la celda
        return spinner;
    }
}