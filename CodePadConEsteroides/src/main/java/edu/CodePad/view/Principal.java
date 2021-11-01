package edu.CodePad.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import edu.CodePad.view.listeners.actions.CargarArchivo;
import edu.CodePad.view.listeners.actions.GuardarArchivo;
import edu.CodePad.view.listeners.actions.Reporte;
import edu.CodePad.view.listeners.focus.Analizar;
import edu.CodePad.view.listeners.keys.AutoClose;
import edu.CodePad.view.listeners.keys.NumberCounter;

public class Principal extends JFrame {
    public static final Font JB_BOLD = new Font("JetBrainsMono Nerd Font Mono", 1, 15);
    public static final Font JBRAINS = new Font("JetBrainsMono NF", 1, 13);

    private JPanel panelTexto;
    private JScrollPane contadorScroll;
    private JScrollPane principalScroll;
    private JTextPane textoContador;
    private JTextPane textoPrincipal;

    private JMenuBar menuBar;
    private JMenuItem cargarBtn;
    private JMenuItem guardarBtn;
    private JMenuItem reporteBtn;

    static {
        UIManager.put("Table.font", JBRAINS);
        UIManager.put("Label.font", JBRAINS);
        UIManager.put("Button.font", JB_BOLD);
        UIManager.put("TextPane.font", JBRAINS);
        UIManager.put("MenuItem.font", JBRAINS);
        UIManager.put("TableHeader.font", JB_BOLD);
        UIManager.put("ToggleButton.font", JB_BOLD);
        
        UIManager.put("Label.background", Color.DARK_GRAY);
        UIManager.put("Label.foreground", Color.WHITE);

        UIManager.put("ScrollBar.thumbDarkShadow", Color.LIGHT_GRAY);
        UIManager.put("ScrollBar.thumbHighlight", Color.DARK_GRAY);
        UIManager.put("ScrollBar.thumbShadow", Color.DARK_GRAY);
        UIManager.put("ScrollBar.background", Color.GRAY);
        UIManager.put("ScrollBar.thumb", Color.DARK_GRAY);
        UIManager.put("ScrollBar.track", Color.GRAY);
        UIManager.put("ScrollBar.width", 12);

        UIManager.put("ComboBox.font", JBRAINS);
        UIManager.put("ComboBox.background", Color.GRAY);
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("ComboBox.buttonBackground", Color.DARK_GRAY);
        UIManager.put("ComboBox.border", BorderFactory.createEmptyBorder());
        UIManager.put("ComboBox.focus", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", Color.BLACK);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.selectionFocus", Color.WHITE);

        UIManager.put("ScrollPane.background", Color.LIGHT_GRAY);
        UIManager.put("TextPane.background", Color.LIGHT_GRAY);

        UIManager.put("TableHeader.background", Color.DARK_GRAY);
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("Table.background", Color.LIGHT_GRAY);
        UIManager.put("Table.gridColor", Color.DARK_GRAY);

        UIManager.put("Button.background", Color.DARK_GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.select", Color.BLACK);
        UIManager.put("Button.focus", Color.LIGHT_GRAY);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));

        UIManager.put("MenuBar.background", Color.DARK_GRAY);

        UIManager.put("Menu.selectionBackground", Color.GRAY);
        UIManager.put("Menu.selectionForeground", Color.WHITE);
        UIManager.put("Menu.background", Color.DARK_GRAY);
        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("Menu.border", BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 2, 1, Color.GRAY),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));

        UIManager.put("MenuItem.selectionBackground", Color.GRAY);
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        UIManager.put("MenuItem.background", Color.DARK_GRAY);
        UIManager.put("MenuItem.foreground", Color.WHITE);
        UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder(3, 5, 3, 5));

    }

    public Principal() {
        super("CodePad");
        this.setLayout(new BorderLayout(10, 5));
        ((JPanel) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        /* Componentes para la parte de texto, numero de linea y demas */

        this.panelTexto = new JPanel(new BorderLayout(3, 0));
        this.add(this.panelTexto, BorderLayout.CENTER);

        this.textoContador = new JTextPane();
        this.textoContador.setText("    ");
        this.textoContador.setEditable(false);

        StyledDocument doc = this.textoContador.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        DefaultCaret caret = (DefaultCaret) textoContador.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        Analizar analizarEvent = new Analizar();
        Reporte reporteEvent = new Reporte(analizarEvent);

        this.textoPrincipal = new JTextPane();
        this.textoPrincipal.addFocusListener(analizarEvent);
        this.textoPrincipal.addKeyListener(new AutoClose(reporteEvent));
        this.textoPrincipal.addKeyListener(new NumberCounter(this.textoContador));

        this.contadorScroll = new JScrollPane(this.textoContador);
        this.contadorScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.contadorScroll.setHorizontalScrollBar(null);
        this.panelTexto.add(this.contadorScroll, BorderLayout.LINE_START);

        this.principalScroll = new JScrollPane(this.textoPrincipal);
        this.panelTexto.add(this.principalScroll, BorderLayout.CENTER);

        this.principalScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.principalScroll.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        this.contadorScroll.getVerticalScrollBar().setModel(this.principalScroll.getVerticalScrollBar().getModel());

        /* Componentes de la parte de botones */

        this.menuBar = new JMenuBar();
        this.setJMenuBar(this.menuBar);

        JMenu menu = new JMenu("Menu");
        this.menuBar.add(menu);
        /* Boton para cargar un archivo con un panel que lo coloque */

        this.cargarBtn = new JMenuItem("Cargar Archivo");
        this.cargarBtn.addActionListener(new CargarArchivo(this));
        menu.add(cargarBtn);

        /* Boton para guardar los cambios del archivo */

        this.guardarBtn = new JMenuItem("Guardar Archivo");
        this.guardarBtn.addActionListener(new GuardarArchivo(this));
        menu.add(guardarBtn);

        /* Boton para generar un reporte de tokens */

        this.reporteBtn = new JMenuItem("Generar Reporte");
        this.reporteBtn.addActionListener(reporteEvent);
        menu.add(reporteBtn);

        /* Seteo de la informacion de la ventana */

        this.setBounds(0, 0, 750, 600);
        this.setMinimumSize(new Dimension(700, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
