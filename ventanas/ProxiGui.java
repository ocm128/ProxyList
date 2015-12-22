package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import ventanas.Editor;
import ventanas.Principal;

/**
 *   @author Kalasni
 *   @version 1.0
 */

public class ProxiGui
extends JFrame {
    private String eleccionJcombo;
    private JComboBox combo;
    private Border borde1;
    private Border borde2;
    private JFileChooser jfileChoo = new JFileChooser();
    private JTextArea textArea = new JTextArea();
    private JButton botonAbrir = new JButton();
    private JButton botonModificar = new JButton();
    private String[] cadena = new String[]{"Editar", "Http", "Socks4", "Socks5"};
    private Principal miPrincipal = new Principal();

    public ProxiGui() {
        try {
            this.addWidgets();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWidgets() throws Exception {
        this.getContentPane().setLayout(null);
        this.borde1 = new BevelBorder(1, Color.GRAY, Color.LIGHT_GRAY);
        this.borde2 = new LineBorder(Color.BLUE, 1);
        this.combo = new JComboBox<String>(this.cadena);
        this.combo.setFont(new Font("Dialog", 1, 15));
        this.combo.setSelectedIndex(0);
        this.combo.setToolTipText("Cadena a insertar en el archivo");
        this.combo.setBounds(new Rectangle(155, 10, 150, 30));
        this.combo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                e.getSource();
                ProxiGui.access$1(ProxiGui.this, (String)ProxiGui.this.combo.getSelectedItem());
                ProxiGui.this.textArea.append("Se ha seleccionado: " + ProxiGui.this.eleccionJcombo + "\n\n");
            }
        });
        this.botonAbrir.setBorder(this.borde1);
        this.botonAbrir.setBounds(new Rectangle(90, 210, 110, 30));
        this.botonAbrir.setFont(new Font("Dialog", 1, 15));
        this.botonAbrir.setText("ABRIR");
        this.botonAbrir.setToolTipText("Abrir fichero a modificar");
        this.jfileChoo.setFont(new Font("Dialog", 1, 15));
        this.botonAbrir.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                int eleccionFileChoo = ProxiGui.this.jfileChoo.showOpenDialog(ProxiGui.this);
                if (eleccionFileChoo == 0) {
                    try {
                        if (ProxiGui.this.eleccionJcombo != "Editar") {
                            String entra = ProxiGui.this.jfileChoo.getSelectedFile().getAbsolutePath();
                            ProxiGui.this.textArea.append("Archivo seleccionado: " + entra + "." + "\n\n");
                        } else {
                            String lect = ProxiGui.this.jfileChoo.getSelectedFile().getAbsolutePath();
                            ProxiGui.this.textArea.append("Archivo seleccionado para lectura: " + lect + "." + "\n\n");
                            Editor edi = new Editor(lect);
                            edi.setVisible(true);
                        }
                    }
                    catch (Exception o) {
                        System.out.println("IOException botonAbrir:");
                        o.printStackTrace();
                    }
                }
            }
        });
        this.botonModificar.setBorder(this.borde1);
        this.botonModificar.setBounds(new Rectangle(255, 210, 110, 30));
        this.botonModificar.setFont(new Font("Dialog", 1, 15));
        this.botonModificar.setToolTipText("Modificar el fichero");
        this.botonModificar.setText("MODIFICAR");
        this.botonModificar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                ProxiGui.this.miPrincipal.escribir(ProxiGui.this.eleccionJcombo, ProxiGui.this.jfileChoo.getSelectedFile().getAbsolutePath(), ProxiGui.this.jfileChoo.getName(ProxiGui.this.jfileChoo.getSelectedFile()));
            }
        });
        this.textArea.setBackground(Color.white);
        this.textArea.setFont(new Font("Dialog", 1, 15));
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(this.textArea, 22, 31);
        scrollPane.setBorder(this.borde2);
        scrollPane.setBounds(new Rectangle(25, 50, 410, 150));
        this.textArea.setEditable(false);
        this.textArea.setLineWrap(true);
        this.getContentPane().add((Component)this.combo, (Object)null);
        this.getContentPane().add((Component)scrollPane, (Object)null);
        this.getContentPane().add((Component)this.botonAbrir, (Object)null);
        this.getContentPane().add((Component)this.botonModificar, (Object)null);
        this.setResizable(false);
        this.setSize(new Dimension(460, 290));
        this.setTitle("   PROXYLIST v1.0 by Kalasni");
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e) {
                ProxiGui.this.dispose();
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception var1_1) {
            // empty catch block
        }
        ProxiGui modi = new ProxiGui();
        modi.setVisible(true);
    }

    static /* synthetic */ void access$1(ProxiGui proxiGui, String string) {
        proxiGui.eleccionJcombo = string;
    }

}

