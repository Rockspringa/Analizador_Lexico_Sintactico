package edu.CodePad.view.table;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ThreeColumnTable extends JPanel {

    private JTable col1;
    private JTable col2;
    private JTable col3;

    private JScrollPane scroll1;
    private JScrollPane scroll2;
    private JScrollPane scroll3;

    public ThreeColumnTable() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        cons.gridheight = GridBagConstraints.REMAINDER;
        cons.gridy = 0;
        cons.gridx = 0;
        cons.weightx = 3;
        cons.weighty = 0.5;

        this.col1 = new JTable();
        this.col1.setPreferredScrollableViewportSize(new Dimension(420, 350));
        this.col1.setFillsViewportHeight(true);

        this.scroll1 = new JScrollPane(this.col1);
        this.scroll1.setSize(new Dimension(420, 350));
        this.scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.add(this.scroll1, cons);

        cons.gridx = 4;
        cons.weightx = 1;

        this.col2 = new JTable();
        this.col2.setPreferredScrollableViewportSize(new Dimension(140, 350));
        this.col2.setFillsViewportHeight(true);

        this.scroll2 = new JScrollPane(this.col2);
        this.scroll2.setSize(new Dimension(140, 350));
        this.scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.add(this.scroll2, cons);

        cons.gridx = 5;

        this.col3 = new JTable();
        this.col3.setPreferredScrollableViewportSize(new Dimension(140, 350));
        this.col3.setFillsViewportHeight(true);

        this.scroll3 = new JScrollPane(this.col3);
        this.scroll3.setSize(new Dimension(140, 350));
        this.scroll3.setHorizontalScrollBar(null);
        this.add(this.scroll3, cons);

        this.col1.setSelectionModel(this.col3.getSelectionModel());
        this.col2.setSelectionModel(this.col3.getSelectionModel());
        this.scroll3.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.scroll1.getVerticalScrollBar().setModel(this.scroll3.getVerticalScrollBar().getModel());
        this.scroll2.getVerticalScrollBar().setModel(this.scroll3.getVerticalScrollBar().getModel());
    }

    public void setModel(Object[][] data, String[] columnNames) {
        Object[][] data1 = new Object[data.length][1];
        Object[][] data2 = new Object[data.length][1];
        Object[][] data3 = new Object[data.length][1];

        String[] columnName1 = new String[1];
        String[] columnName2 = new String[1];
        String[] columnName3 = new String[1];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                switch (j) {
                    case 0:
                        data1[i][0] = data[i][j];
                        break;
                    case 1:
                        data2[i][0] = data[i][j];
                        break;
                    default:
                        data3[i][0] = data[i][j];
                }
            }
        }

        for (int i = 0; i < columnNames.length; i++) {
            switch (i) {
                case 0:
                    columnName1[0] = columnNames[i];
                    break;
                case 1:
                    columnName2[0] = columnNames[i];
                    break;
                default:
                    columnName3[0] = columnNames[i];
            }
        }

        this.col1.setModel(new StaticTableModel(data1, columnName1));
        this.col2.setModel(new StaticTableModel(data2, columnName2));
        this.col3.setModel(new StaticTableModel(data3, columnName3));
    }
}
