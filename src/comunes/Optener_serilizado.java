/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import clases.Proyecto;
import controlador.Controladora_edicion;
import visual.Ventana_calculo;
import visual.Ventana_calculo_edicion;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alba Proyecto
 */
public class Optener_serilizado {

    public Optener_serilizado() {
        abrir_fichero_mcv();
    }

    public void abrir_fichero_mcv() {
        try {
            JFileChooser jf = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo archivos con extensión .mcv", "mcv");
            jf.setFileFilter(filtro);
            int returnval = jf.showOpenDialog(null);
            if (returnval == JFileChooser.APPROVE_OPTION) {
                System.out.println("se tomo el archivo " + jf.getSelectedFile().getName());
                FileInputStream in = new FileInputStream(jf.getSelectedFile().getAbsolutePath());
                ObjectInputStream oin = new ObjectInputStream(in);

                if (jf.getSelectedFile().getAbsoluteFile().exists()/* && jf.getSelectedFile().getName().substring(jf.getSelectedFile().getName().length() - 3, jf.getSelectedFile().getName().length()).equals(".xlsx")*/) {
                    Proyecto py=(Proyecto)oin.readObject();
                    Ventana_calculo_edicion vce = new Ventana_calculo_edicion(py.getNombre_py());
                    Controladora_edicion ce=new Controladora_edicion(vce);
                    Comun.nm.desktopPane.add(vce, CENTER_ALIGNMENT);
                    //vce.jComboBox2.requestFocus();
                    vce.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El sistema no puede encontrar o no reconoce el archivo especificado.\nSi este fichero realmente existe, garantice que tenga la extensión y la estructura correcta.");
                }
                oin.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar procesar el fichero");
        }

    }

}
