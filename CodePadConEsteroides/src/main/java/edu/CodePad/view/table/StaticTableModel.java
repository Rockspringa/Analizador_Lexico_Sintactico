package edu.CodePad.view.table;

import javax.swing.table.DefaultTableModel;

public class StaticTableModel extends DefaultTableModel {
    
    public StaticTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
