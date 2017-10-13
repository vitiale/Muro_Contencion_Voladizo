/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alba Proyecto
 */
public class ChecarErrores {

    //private tabla_relacion
    public static int Dobles(ArrayList<String> elementos) {
        int i = 0;
        try {
            for (; i < elementos.size(); i++) {
                //System.out.println("elemento "+elementos.get(i));
                Double.parseDouble(elementos.get(i));
            }
            return -20;
        } catch (Exception e) {
            return i;
        }
    }

    public static int Double_bidimension() {
        try {
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    public static int Dobles_menor_igual(String cad) {
        int i = 0;
        try {
            double valor = Double.parseDouble(cad);
            if (valor <= 0) {
                i = 1;
            } else {
                i = 0;
            }
        } catch (Exception e) {
            i = 1;
        }
        return i;
    }
    
    public static int Dobles_menor(String cad) {
        int i = 0;
        try {
            double valor = Double.parseDouble(cad);
            if (valor < 0) {
                i = 1;
            } else {
                i = 0;
            }
        } catch (Exception e) {
            i = 1;
        }
        return i;
    }

}
