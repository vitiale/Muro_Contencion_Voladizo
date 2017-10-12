/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import clases.Proyecto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Alba Proyecto
 */
public class Py_Serializable {

    private Proyecto py;
    private String dir;
    private String nombre;

    public Py_Serializable(Proyecto py, String dir, String nombre) {
        this.py = py;
        this.dir = dir;
        this.nombre = nombre;
    }

    public void serializar_py() {
        try {
            System.out.println("serial");
//            System.out.println(dir);
//            System.out.println(dir.indexOf('.'));
//            System.out.println(dir.substring(0, dir.indexOf('.')));            
            if (dir.indexOf('.') != -1) {
                dir = dir.substring(0, dir.indexOf('.'));
            }
            FileOutputStream of = new FileOutputStream(dir + "(" +nombre + ")" + ".mcv");
            ObjectOutputStream oof = new ObjectOutputStream(of);
            //escribir en el fichero los objetos serializados
            oof.writeObject(py);
            of.close();
            oof.close();
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void recuperacion_py() {
        try {
            FileInputStream inf = new FileInputStream(dir + "(" +nombre + ")" + ".mvc");
            ObjectInputStream oinf = new ObjectInputStream(inf);
            
            Proyecto py1 = (Proyecto) oinf.readObject();
            System.out.println("nombre del proyecto sacado**************************************************************************************");
            System.out.println(py.getLista().get(0));
            inf.close();
            oinf.close();
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
