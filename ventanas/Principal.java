package ventanas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringReader;

/**
 *   @author Kalasni
 *   @version 1.0
 */

public class Principal {
    private RandomAccessFile salidaEscrit;
    private BufferedReader entradaLectura;
    private String lecturaFinal = new String();

    public void escribir(String cad, String eleccionFileChoo, String nombreFiche) {
        try {
            if (cad != "Editar") {
                String s;
                int c;
                String cadenaFin = String.valueOf(cad) + " ";
                this.entradaLectura = new BufferedReader(new FileReader(eleccionFileChoo));
                String s2 = new String();
                while ((s = this.entradaLectura.readLine()) != null) {
                    if (s.length() <= 3) continue;
                    s2 = String.valueOf(s2) + cadenaFin + s + "\n";
                }
                this.entradaLectura.close();
                StringReader entrada = new StringReader(s2);
                this.salidaEscrit = new RandomAccessFile(String.valueOf(nombreFiche) + "-2nd.txt", "rw");
                while ((c = entrada.read()) != -1) {
                    if (c == 58) {
                        this.salidaEscrit.writeBytes(" ");
                        continue;
                    }
                    this.salidaEscrit.write(c);
                }
                this.salidaEscrit.close();
            }
        }
        catch (IOException o) {
            System.out.println("IOException en escribir(): ");
            o.printStackTrace();
        }
    }

    public void lectura(String elegido) {
        try {
            this.entradaLectura = new BufferedReader(new FileReader(elegido));
            String s = new String();
            while ((s = this.entradaLectura.readLine()) != null) {
                this.lecturaFinal = String.valueOf(this.lecturaFinal) + s + "\n";
            }
            this.entradaLectura.close();
        }
        catch (IOException ex) {
            System.out.println("IOException en lectura() :");
            ex.printStackTrace();
        }
    }

    public String lectorFinal() {
        return this.lecturaFinal;
    }
}

