package edu.CodePad.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities;
import javax.swing.undo.UndoManager;

import edu.CodePad.model.contracts.UIManagerSetter;
import edu.CodePad.view.actions.Redo;
import edu.CodePad.view.actions.Undo;
import edu.CodePad.view.listeners.actions.CargarArchivo;
import edu.CodePad.view.listeners.actions.ExportResult;
import edu.CodePad.view.listeners.actions.GuardarArchivo;
import edu.CodePad.view.listeners.actions.GuardarComoArchivo;
import edu.CodePad.view.listeners.actions.NuevoArchivo;
import edu.CodePad.view.listeners.actions.Reporte;
import edu.CodePad.view.listeners.changes.Analizar;
import edu.CodePad.view.listeners.changes.AutoClose;
import edu.CodePad.view.listeners.changes.CambiosArchivo;
import edu.CodePad.view.listeners.changes.NumberCounter;
import edu.CodePad.view.listeners.edit.Undoable;

public class Principal extends JFrame implements UIManagerSetter {

    private JPanel panelPrincipal;

    private JPanel panelTexto;

    private JScrollPane principalScroll;
    private JTextPane textoPrincipal;
    private UndoManager undoManager;

    private JScrollPane contadorScroll;
    private JTextPane textoContador;
    
    private JScrollPane logScroll;
    private JTextPane textoLog;

    private JMenuBar menuBar;

    private JMenuItem nuevoBtn;
    private JMenuItem cargarBtn;
    private JMenuItem guardarBtn;
    private JMenuItem guardarComoBtn;

    private JMenuItem reporteBtn;

    private String[] textos = { "Deshacer", "Rehacer", "Copiar", "Pegar" };
    private Action[] actions = { null, null, new DefaultEditorKit.CopyAction(), new DefaultEditorKit.PasteAction() };

    public Principal() {
        super("CodePad");
        this.setLayout(new BorderLayout(10, 5));
        ((JPanel) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        runUIManager();
        /* Componentes para la parte de texto, numero de linea y demas */

        this.panelPrincipal= new JPanel(new BorderLayout(0, 3));
        this.add(this.panelPrincipal, BorderLayout.CENTER);

        this.panelTexto = new JPanel(new BorderLayout(3, 0));
        this.panelPrincipal.add(this.panelTexto, BorderLayout.CENTER);

        this.textoContador = new JTextPane();
        this.textoContador.setText("    ");
        this.textoContador.setEditable(false);

        StyledDocument doc = this.textoContador.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        DefaultCaret caret = (DefaultCaret) textoContador.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        this.textoPrincipal = new JTextPane();
        this.textoLog = new JTextPane();
        
        Analizar analizarEvent = new Analizar(this.textoPrincipal, textoLog);
        Reporte reporteEvent = new Reporte(analizarEvent);

        JPanel tmpPanel = new JPanel(new BorderLayout());

        this.textoPrincipal.getDocument().addDocumentListener(analizarEvent);
        this.textoPrincipal.getDocument().addDocumentListener(new AutoClose(reporteEvent));
        this.textoPrincipal.getDocument().addDocumentListener(new NumberCounter(this));
        this.textoPrincipal.getDocument().addDocumentListener(new CambiosArchivo());
        tmpPanel.add(this.textoPrincipal);

        this.contadorScroll = new JScrollPane(this.textoContador);
        this.contadorScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.contadorScroll.setHorizontalScrollBar(null);
        this.panelTexto.add(this.contadorScroll, BorderLayout.LINE_START);

        this.principalScroll = new JScrollPane(tmpPanel);
        this.panelTexto.add(this.principalScroll, BorderLayout.CENTER);

        this.principalScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.principalScroll.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        this.contadorScroll.getVerticalScrollBar().setModel(this.principalScroll.getVerticalScrollBar().getModel());

        this.undoManager = new UndoManager();
        Document document = textoPrincipal.getDocument();

        document.addUndoableEditListener(new Undoable(undoManager));
        
        JPanel logPanel = new JPanel(new BorderLayout());
        this.panelPrincipal.add(logPanel, BorderLayout.PAGE_END);

        JLabel logLabel = new JLabel("Problemas en el analisis");
        logLabel.setHorizontalAlignment(JLabel.CENTER);
        logLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        logLabel.setBackground(Color.DARK_GRAY);
        logLabel.setForeground(Color.WHITE);
        logLabel.setOpaque(true);
        logLabel.setFont(JB_BOLDER);
        logPanel.add(logLabel, BorderLayout.PAGE_START);

        this.textoLog.setEditable(false);
        this.textoLog.setText("\n\n\n");
        JPanel logTmpPanel = new JPanel(new BorderLayout());
        logTmpPanel.add(this.textoLog);

        this.logScroll = new JScrollPane(logTmpPanel);
        this.logScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.logScroll.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        logPanel.add(this.logScroll, BorderLayout.CENTER);

        this.actions[0] = new Undo(undoManager);
        this.actions[1] = new Redo(undoManager);
        this.textoPrincipal.getActionMap().put("Undo", this.actions[0]);
        this.textoPrincipal.getActionMap().put("Redo", this.actions[1]);

        textoPrincipal.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
        textoPrincipal.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");

        /* Componentes de la parte de botones */

        this.menuBar = new JMenuBar();
        this.setJMenuBar(this.menuBar);

        JMenu archivoMenu = new JMenu("Archivo");
        this.menuBar.add(archivoMenu);

        /* Boton para cargar un archivo con un panel que lo coloque */

        this.nuevoBtn = new JMenuItem("Nuevo...");
        this.nuevoBtn.addActionListener(new NuevoArchivo(this, this.textoPrincipal));
        archivoMenu.add(nuevoBtn);
 
        this.cargarBtn = new JMenuItem("Abrir...");
        this.cargarBtn.addActionListener(new CargarArchivo(this, this.textoPrincipal));
        archivoMenu.add(cargarBtn);
 
        archivoMenu.addSeparator();
        /* Boton para guardar los cambios del archivo */

        this.guardarBtn = new JMenuItem("Guardar");
        this.guardarBtn.addActionListener(new GuardarArchivo(this, this.textoPrincipal));
        archivoMenu.add(guardarBtn);

        this.guardarComoBtn = new JMenuItem("Guardar Como...");
        this.guardarComoBtn.addActionListener(new GuardarComoArchivo(this, this.textoPrincipal));
        archivoMenu.add(guardarComoBtn);

        JMenu analisisMenu = new JMenu("Analisis");
        this.menuBar.add(analisisMenu);

        /* Boton para generar un reporte de tokens */

        this.reporteBtn = new JMenuItem("Generar Reporte");
        this.reporteBtn.addActionListener(reporteEvent);
        analisisMenu.add(reporteBtn);

        this.reporteBtn = new JMenuItem("Exportar Resultado");
        this.reporteBtn.addActionListener(new ExportResult(this, analizarEvent));
        analisisMenu.add(reporteBtn);

        JMenu editarMenu = new JMenu("Editar");
        this.menuBar.add(editarMenu);

        for (int i = 0; i < actions.length; i++) {
            if (i % 2 == 0)
                editarMenu.addSeparator();
            JMenuItem item = new JMenuItem(actions[i]);
            item.setText(textos[i]);
            editarMenu.add(item);
        }

        /* Seteo de la informacion de la ventana */

        this.setBounds(0, 0, 750, 600);
        this.setMinimumSize(new Dimension(700, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void updateNumbers() {
        StringBuilder sb = new StringBuilder();
        int lines = 1;
        int offset = textoPrincipal.getDocument().getLength();

        try {
            while (offset > 0) {
                offset = Utilities.getRowStart(textoPrincipal, offset) - 1;
                sb.append(lines++ + "\n");
            }
        } catch (BadLocationException | NullPointerException e1) {
            sb = new StringBuilder();
            int max = textoPrincipal.getText().split("\n").length;

            for (int i = 1; i <= max; i++)
                sb.append(i + "\n");
        }
        sb.append("    \n");

        textoContador.setText(sb.toString());
    }

}
