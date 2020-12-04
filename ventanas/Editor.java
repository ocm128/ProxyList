package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import ventanas.Principal;


/**
 *   @author ocm128
 *   @version 1.0
 */

public class Editor
extends JFrame {
    private final Toolkit kit = Toolkit.getDefaultToolkit();
    final Clipboard clipboard = this.kit.getSystemClipboard();
    final JTextArea jtex = new JTextArea();
    private JScrollPane pane;
    private JButton copiar = new JButton("COPIAR");
    private JButton pegar = new JButton("PEGAR");
    private String ediSelec;
    private Border borde1;
    private Border borde2;
    private Principal principal = new Principal();

    public Editor(String elec) {
        super("   EDITOR");
        this.ediSelec = elec;
        try {
            this.crearWidgets();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearWidgets() throws Exception {
        this.getContentPane().setLayout(null);
        this.borde1 = new BevelBorder(1, Color.GRAY, Color.LIGHT_GRAY);
        this.borde2 = new LineBorder(Color.BLUE, 1);
        this.principal.lectura(this.ediSelec);
        this.jtex.setBackground(Color.white);
        this.jtex.setFont(new Font("Dialog", 1, 16));
        this.jtex.setText(this.principal.lectorFinal());
        JScrollPane pane = new JScrollPane(this.jtex, 22, 31);
        pane.setBounds(new Rectangle(15, 20, 370, 460));
        pane.setBorder(this.borde2);
        ActionListener copiarListener = new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                String seleccion = Editor.this.jtex.getSelectedText();
                StringSelection datos = new StringSelection(seleccion);
                Editor.this.clipboard.setContents(datos, datos);
            }
        };
        this.copiar.addActionListener(copiarListener);
        this.copiar.setBounds(new Rectangle(80, 500, 110, 30));
        this.copiar.setFont(new Font("Dialog", 1, 16));
        this.copiar.setBorder(this.borde1);
        ActionListener pegarListener = new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                Transferable clipDatos = Editor.this.clipboard.getContents(Editor.this.clipboard);
                if (clipDatos != null) {
                    try {
                        if (clipDatos.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                            String str = (String)clipDatos.getTransferData(DataFlavor.stringFlavor);
                            Editor.this.jtex.replaceSelection(str);
                        } else {
                            Editor.this.kit.beep();
                        }
                    }
                    catch (Exception ex) {
                        System.err.println("Problema obteniendo los datos" + ex);
                    }
                }
            }
        };
        this.pegar.addActionListener(pegarListener);
        this.pegar.setBounds(new Rectangle(210, 500, 110, 30));
        this.pegar.setFont(new Font("Dialog", 1, 16));
        this.pegar.setBorder(this.borde1);
        this.getContentPane().add((Component)pane, (Object)null);
        this.getContentPane().add((Component)this.copiar, (Object)null);
        this.getContentPane().add((Component)this.pegar, (Object)null);
        this.setResizable(false);
        this.setSize(new Dimension(400, 570));
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e) {
                Editor.this.dispose();
            }
        });
    }

}

