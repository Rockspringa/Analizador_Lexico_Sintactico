package edu.CodePad.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import edu.CodePad.model.contracts.UIManagerSetter;
import edu.CodePad.model.viewSupps.TablaData;
import edu.CodePad.view.table.ThreeColumnTable;

public class Report extends JFrame implements UIManagerSetter {

    private ThreeColumnTable contenidoTable;
    private JLabel contLabel;

    private JScrollPane logScroll;
    private JTextPane logText;
    private JLabel logLabel;

    public Report() {
        this.setLayout(new BorderLayout(10, 5));
        ((JPanel) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel contenidoPanel = new JPanel(new BorderLayout());
        this.add(contenidoPanel, BorderLayout.CENTER);
        
        this.contenidoTable = new ThreeColumnTable();
        this.contenidoTable.setPreferredSize(new Dimension(700, 350));
        contenidoPanel.add(this.contenidoTable, BorderLayout.CENTER);

        this.contLabel = new JLabel();
        this.contLabel.setHorizontalAlignment(JLabel.CENTER);
        this.contLabel.setBackground(Color.DARK_GRAY);
        this.contLabel.setForeground(Color.WHITE);
        this.contLabel.setOpaque(true);
        this.contLabel.setFont(JB_BOLDER);
        this.contLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        contenidoPanel.add(this.contLabel, BorderLayout.PAGE_START);

        JPanel logPanel = new JPanel(new BorderLayout());
        this.add(logPanel, BorderLayout.PAGE_END);
        
        this.logText = new JTextPane();
        this.logText.setSize(850, 200);
        this.logText.setEditable(false);

        JPanel tmpPanel = new JPanel(new BorderLayout());
        tmpPanel.add(logText);

        this.logScroll = new JScrollPane(tmpPanel);
        this.logScroll.setPreferredSize(new Dimension(100, 150));
        this.logScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.logScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.logScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.logScroll.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        logPanel.add(this.logScroll, BorderLayout.CENTER);

        this.logLabel = new JLabel("Historial de Transiciones");
        this.logLabel.setHorizontalAlignment(JLabel.CENTER);
        this.logLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        this.logLabel.setBackground(Color.DARK_GRAY);
        this.logLabel.setForeground(Color.WHITE);
        this.logLabel.setOpaque(true);
        this.logLabel.setFont(JB_BOLDER);
        logPanel.add(this.logLabel, BorderLayout.PAGE_START);

        this.setBounds(50, 50, 850, 600);
        this.setMinimumSize(new Dimension(500, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setContent(TablaData table) {
        Object[][] data = table.getData();
        String[] columnNames = table.getHeaders();
        String log = table.getLog();

        this.logText.setText(log);
        this.contenidoTable.setModel(data, columnNames);
        this.setTitle(table.getTitle());
        this.contLabel.setText("Tabla de Simbolos");
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            this.setLocationRelativeTo(null);
        }
    }

}
