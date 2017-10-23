/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import clases.Proyecto;
import controlador.Controladora1;
import controlador.Controladora_edicion;
import visual.Ventana_calculo;
import visual.Ventana_calculo_edicion;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import visual.Ventana_calculo1;

/**
 *
 * @author Alba Proyecto
 */
public class Obtener_serializado {

    public Obtener_serializado() {
        //abrir_fichero_mcv();
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
                    System.out.println(py.getNombre_py());
                    for(int i=0; i<py.getLista().size(); i++){
                        System.out.println(i+" "+py.getLista().get(i).getLista_muro().get(0));
                    }
                    Ventana_calculo1 vc1 = new Ventana_calculo1(py);
                    Controladora1 ce=new Controladora1(vc1, py);
                    Comun.nm.desktopPane.add(vc1, CENTER_ALIGNMENT);
                    //vce.jComboBox2.requestFocus();
                    vc1.setVisible(true);
                    
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
