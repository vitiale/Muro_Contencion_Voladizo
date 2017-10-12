/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.Muro;
import clases.Proyecto;
import com.sun.javafx.css.CalculatedValue;
import comunes.ChecarErrores;
import comunes.Py_Serializable;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import visual.Ventana_calculo;
import visual.Ventana_calculo_edicion;

/**
 *
 * @author Alba Proyecto
 */
public class Controladora_edicion implements KeyListener, FocusListener, ActionListener {

    private Proyecto py = new Proyecto();
    //private ArrayList<String> datos_muro = new ArrayList<>();
    private Ventana_calculo_edicion vce;
    private ArrayList<String> elementos = new ArrayList<>();
    private ArrayList<String> elementos_mombre = new ArrayList<>();
    private final double conversion = 3.14159265 / 180;
    private final double k1 = 0.667/*2.0 / 3.0*/;
    private final double k2 = 0.667/*2.0 / 3.0*/;
    private double sigma_a;
    private double sigma_b;
    private double sigma_p;
    private double w1;
    private double w2;
    private double w3;
    private double mw1;
    private double mw2;
    private double mw3;
    private double sum_mr;
    private double sum_m0;
    private double MPp;
    private double mpa;
    //variables de revision por capacidad
    private double m_neto;
    private double x;
    private double ee;
    private double ma;
    private double A;
    private double c;
    private double i;
    //varibles de diseño de muro
    private double r = 2.5;//luego toma otros valores
    private double b = 1;//100.0;
    private final double fi_f = 0.9;
    private final double fi_v = 0.75;
    private double h;
    private double m1;
    private double d;
    private final double es = 2039000.0;
    private final double gamma_c = 2.4;
    private double theta;
    private double kae;
    private double ka;
    private double area_steel1;
    private double area_steel2;
    private double area_steel3;
    private double area_steel4;
    private double separacion1;
    private double separacion2;
    private double separacion3;
    private double separacion4;
    private double fi_mr1;
    private double fi_mr2;
    private double fi_mr3;

    public Controladora_edicion(Ventana_calculo_edicion vc) {
        this.vce = vc;
        vc.jLabel_ka2.setVisible(false);
        vc.jLabel_ka1.setToolTipText("pasando por aquí");
        vc.ka2.setVisible(false);
        vc.jLabel_kp1.setVisible(false);
        //vc.jLabel_kp2.setVisible(false);
        vc.kp1.setVisible(false);
        //para agregar el evento keylistener A los elementos
        vc.fi1.addKeyListener(this);
        vc.gamma1.addKeyListener(this);
        vc.sc.addKeyListener(this);
        vc.fi2.addKeyListener(this);
        vc.gamma2.addKeyListener(this);
        vc.c.addKeyListener(this);
        vc.qad.addKeyListener(this);
        vc.h1.addKeyListener(this);
        vc.h2.addKeyListener(this);
        vc.d1.addKeyListener(this);
        vc.var_e.addKeyListener(this);
        vc.a1.addKeyListener(this);
        vc.d2.addKeyListener(this);
        vc.l1.addKeyListener(this);
        vc.l2.addKeyListener(this);
        vc.l3.addKeyListener(this);
        vc.fc.addKeyListener(this);
        vc.r.addKeyListener(this);
        vc.fy.addKeyListener(this);
        vc.kv.addKeyListener(this);
        vc.kh.addKeyListener(this);
        vc.beta.addKeyListener(this);
        vc.delta.addKeyListener(this);
        vc.varillas1.addKeyListener(this);
        vc.varillas2.addKeyListener(this);
        vc.varillas3.addKeyListener(this);
        vc.varillas4.addKeyListener(this);
        vc.separacion1.addKeyListener(this);
        vc.separacion2.addKeyListener(this);
        vc.separacion3.addKeyListener(this);
        vc.separacion4.addKeyListener(this);
        vc.ld1.addKeyListener(this);
        vc.ld2.addKeyListener(this);
        vc.name.addKeyListener(this);
        vc.alpha.addKeyListener(this);
        vc.aceptar1.addKeyListener(this);

        //para agregar el evento focuslistener A los elementos
        vc.fi1.addFocusListener(this);
        vc.gamma1.addFocusListener(this);
        vc.sc.addFocusListener(this);
        vc.fi2.addFocusListener(this);
        vc.gamma2.addFocusListener(this);
        vc.c.addFocusListener(this);
        vc.qad.addFocusListener(this);
        vc.h1.addFocusListener(this);
        vc.h2.addFocusListener(this);
        vc.d1.addFocusListener(this);
        vc.var_e.addFocusListener(this);
        vc.a1.addFocusListener(this);
        vc.d2.addFocusListener(this);
        vc.l1.addFocusListener(this);
        vc.l2.addFocusListener(this);
        vc.l3.addFocusListener(this);
        vc.fc.addFocusListener(this);
        vc.r.addFocusListener(this);
        vc.fy.addFocusListener(this);
        vc.kv.addFocusListener(this);
        vc.kh.addFocusListener(this);
        vc.beta.addFocusListener(this);
        vc.delta.addFocusListener(this);
        vc.varillas1.addFocusListener(this);
        vc.varillas2.addFocusListener(this);
        vc.varillas3.addFocusListener(this);
        vc.varillas4.addFocusListener(this);
        vc.separacion1.addFocusListener(this);
        vc.separacion2.addFocusListener(this);
        vc.separacion3.addFocusListener(this);
        vc.separacion4.addFocusListener(this);
        vc.ld1.addFocusListener(this);
        vc.ld2.addFocusListener(this);
        vc.alpha.addFocusListener(this);
        vc.alpha.addFocusListener(this);

        //para agregar el evento actionlistener A los elementos
        vc.aceptar1.addActionListener(this);
        vc.cancelar.addActionListener(this);
        vc.guardar.addActionListener(this);
        vc.editar.addActionListener(this);
        vc.eliminar.addActionListener(this);
        vc.cerrar.addActionListener(this);

//        //Aquí podemos cargar la lista elemento y elemento_nombre, así nos ahorramos el tener que borrar e insertar en cada llamada.
//        elementos.add(vce.fi1.getText());
//        elementos_mombre.add("fi1");
//        elementos.add(vce.fi2.getText());
//        elementos_mombre.add("fi2");
//        elementos.add(vce.gamma1.getText());
//        elementos_mombre.add("gamma1");
//        elementos.add(vce.gamma2.getText());
//        elementos_mombre.add("gamma2");
//        
//        elementos.add(vce.ka1.getText());
//        elementos_mombre.add("ka1");
//        elementos.add(vce.kp1.getText());
//        elementos_mombre.add("kp1");
//        elementos.add(vce.sc.getText());
//        elementos_mombre.add("sc");
//        elementos.add(vce.h1.getText());
//        elementos_mombre.add("h1");
//        elementos.add(vce.h2.getText());
//        elementos_mombre.add("h2");
//        
//        elementos.add(vce.qa.getText());
//        elementos_mombre.add("qa");
//        elementos.add(vce.d1.getText());
//        elementos_mombre.add("d1");
//        elementos.add(vce.h5.getText());
//        elementos_mombre.add("h5");
//        elementos.add(vce.h6.getText());
//        elementos_mombre.add("h6");
//        elementos.add(vce.h7.getText());
//        elementos_mombre.add("h7");
//        elementos.add(vce.a1.getText());
//        elementos_mombre.add("a1");        
//        elementos.add(vce.d2.getText());
//        elementos_mombre.add("d2");
//        elementos.add(vce.l1.getText());
//        elementos_mombre.add("l1");
//        elementos.add(vce.l2.getText());
//        elementos_mombre.add("l2");
//        elementos.add(vce.l3.getText());
//        elementos_mombre.add("l3");
//        elementos.add(vce.ld1.getText());
//        elementos_mombre.add("ld1");
//        elementos.add(vce.ld2.getText());
//        elementos_mombre.add("ld2");
//        elementos.add(vce.a6.getText());
//        elementos_mombre.add("a6");
//        
//        elementos.add(vce.fc.getText());
//        elementos_mombre.add("fc");
//        elementos.add(vce.fy.getText());
//        elementos_mombre.add("fy");
//        
//        elementos.add(vce.kh.getText());
//        elementos_mombre.add("kh");
//        elementos.add(vce.kv.getText());
//        elementos_mombre.add("kv");
//        elementos.add(vce.ro.getText());
//        elementos_mombre.add("ro");
//        elementos.add(vce.beta.getText());
//        elementos_mombre.add("beta");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            if (e.getSource() == vce.fi1) {
                vce.gamma1.requestFocus();
            } else if (e.getSource() == vce.gamma1) {
                vce.sc.requestFocus();
            } else if (e.getSource() == vce.sc) {
                vce.fi2.requestFocus();
            } else if (e.getSource() == vce.fi2) {
                vce.gamma2.requestFocus();
            } else if (e.getSource() == vce.gamma2) {
                vce.c.requestFocus();
            } else if (e.getSource() == vce.c) {
                vce.qad.requestFocus();
            } else if (e.getSource() == vce.qad) {
                vce.h1.requestFocus();
            } else if (e.getSource() == vce.h1) {
                vce.h2.requestFocus();
            } else if (e.getSource() == vce.h2) {
                vce.d1.requestFocus();
            } else if (e.getSource() == vce.d1) {
                vce.var_e.requestFocus();
            } else if (e.getSource() == vce.var_e) {
                vce.a1.requestFocus();
            } else if (e.getSource() == vce.a1) {
                vce.d2.requestFocus();
            } else if (e.getSource() == vce.d2) {
                vce.l1.requestFocus();
            } else if (e.getSource() == vce.l1) {
                vce.l2.requestFocus();
            } else if (e.getSource() == vce.l2) {
                vce.l3.requestFocus();
            } else if (e.getSource() == vce.l3) {
                vce.alpha.requestFocus();
            } else if (e.getSource() == vce.alpha) {
                vce.fc.requestFocus();
            } else if (e.getSource() == vce.fc) {
                vce.r.requestFocus();
            } else if (e.getSource() == vce.r) {
                vce.fy.requestFocus();
            } else if (e.getSource() == vce.fy) {
                vce.kv.requestFocus();
            } else if (e.getSource() == vce.kv) {
                vce.kh.requestFocus();
            } else if (e.getSource() == vce.kh) {
                vce.beta.requestFocus();
            } else if (e.getSource() == vce.beta) {
                vce.delta.requestFocus();
            } else if (e.getSource() == vce.delta) {
                vce.varillas1.requestFocus();
            } else if (e.getSource() == vce.varillas1) {
                vce.separacion1.requestFocus();
            } else if (e.getSource() == vce.separacion1) {
                vce.varillas2.requestFocus();
            } else if (e.getSource() == vce.varillas2) {
                vce.separacion2.requestFocus();
            } else if (e.getSource() == vce.separacion2) {
                vce.ld1.requestFocus();
            } else if (e.getSource() == vce.ld1) {
                vce.varillas3.requestFocus();
            } else if (e.getSource() == vce.varillas3) {
                vce.separacion3.requestFocus();
            } else if (e.getSource() == vce.separacion3) {
                vce.ld2.requestFocus();
            } else if (e.getSource() == vce.ld2) {
                vce.varillas4.requestFocus();
            } else if (e.getSource() == vce.varillas4) {
                vce.separacion4.requestFocus();
            } else if (e.getSource() == vce.separacion4) {
                vce.name.requestFocus();            
            } else if (e.getSource() == vce.name) {
                vce.aceptar1.requestFocus();
            } else if (e.getSource() == vce.aceptar1) {
                almacenar_muro();
            } else if (e.getSource() == vce.cancelar) {
                cancelar();
            } else if (e.getSource() == vce.eliminar) {
                eliminar();
            } else if (e.getSource() == vce.editar) {
                editar();
            } else if (e.getSource() == vce.cerrar) {
                editar();
            } else if (e.getSource() == vce.guardar) {
                guardar();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusGained(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == vce.fi1) {
            vce.fi1.selectAll();
            vce.fi1.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.fi2) {
            vce.fi2.selectAll();
            vce.fi2.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.sc) {
            vce.sc.selectAll();
            vce.sc.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.gamma1) {
            vce.gamma1.selectAll();
            vce.gamma1.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.gamma2) {
            vce.gamma2.selectAll();
            vce.gamma2.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.c) {
            vce.c.selectAll();
            vce.c.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.qad) {
            vce.qad.selectAll();
            vce.qad.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.h1) {
            vce.h1.selectAll();
            vce.h1.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.h2) {
            vce.h2.selectAll();
            vce.h2.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.d1) {
            vce.d1.selectAll();
            vce.d1.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.var_e) {
            vce.var_e.selectAll();
            vce.var_e.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.alpha) {
            vce.alpha.selectAll();
            vce.alpha.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.a1) {
            vce.a1.selectAll();
            vce.a1.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.d2) {
            vce.d2.selectAll();
            vce.d2.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.l1) {
            vce.l1.selectAll();
            vce.l1.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.l2) {
            vce.l2.selectAll();
            vce.l2.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.l3) {
            vce.l3.selectAll();
            vce.l3.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.fc) {
            vce.fc.selectAll();
            vce.fc.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.fy) {
            vce.fy.selectAll();
            vce.fy.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.kv) {
            vce.kv.selectAll();
            vce.kv.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.kh) {
            vce.kh.selectAll();
            vce.kh.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.beta) {
            vce.beta.selectAll();
            vce.beta.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.delta) {
            vce.delta.selectAll();
            vce.delta.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.ld1) {
            vce.ld1.selectAll();
            vce.ld1.setBackground(Color.WHITE);
        } else if (e.getSource() == vce.ld2) {
            vce.ld2.selectAll();
            vce.ld2.setBackground(Color.WHITE);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == vce.fi1) {
            ejecutor();
        } else if (e.getSource() == vce.fi2) {
            ejecutor();
        } else if (e.getSource() == vce.sc) {
            ejecutor();
        } else if (e.getSource() == vce.gamma1) {
            ejecutor();
        } else if (e.getSource() == vce.gamma2) {
            ejecutor();
        } else if (e.getSource() == vce.c) {
            ejecutor();
        } else if (e.getSource() == vce.qad) {
            vce.qad_espejo.setText(vce.qad.getText());
            ejecutor();
        } else if (e.getSource() == vce.h1) {
            ejecutor();
        } else if (e.getSource() == vce.h2) {
            ejecutor();
        } else if (e.getSource() == vce.d1) {
            ejecutor();
        } else if (e.getSource() == vce.var_e) {
            ejecutor();
        } else if (e.getSource() == vce.a1) {
            ejecutor();
        } else if (e.getSource() == vce.d2) {
            ejecutor();
        } else if (e.getSource() == vce.l1) {
            ejecutor();
        } else if (e.getSource() == vce.l2) {
            ejecutor();
        } else if (e.getSource() == vce.l3) {
            ejecutor();
        } else if (e.getSource() == vce.alpha) {
            ejecutor();
        } else if (e.getSource() == vce.fc) {
            ejecutor();
        } else if (e.getSource() == vce.r) {
            ejecutor();
        } else if (e.getSource() == vce.fy) {
            ejecutor();
        } else if (e.getSource() == vce.kv) {
            ejecutor();
        } else if (e.getSource() == vce.kh) {
            ejecutor();
        } else if (e.getSource() == vce.beta) {
            ejecutor();
        } else if (e.getSource() == vce.delta) {
            ejecutor();
        } else if (e.getSource() == vce.varillas1) {
            ejecutor();
        } else if (e.getSource() == vce.separacion1) {
            ejecutor();
        } else if (e.getSource() == vce.varillas2) {
            ejecutor();
        } else if (e.getSource() == vce.separacion2) {
            ejecutor();
        } else if (e.getSource() == vce.varillas3) {
            ejecutor();
        } else if (e.getSource() == vce.separacion3) {
            ejecutor();
        } else if (e.getSource() == vce.ld1) {
            ejecutor();
        } else if (e.getSource() == vce.ld2) {
            ejecutor();
        } else if (e.getSource() == vce.varillas4) {
            ejecutor();
        } else if (e.getSource() == vce.separacion4) {
            ejecutor();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == vce.aceptar1) {
            almacenar_muro();
        } else if (e.getSource() == vce.cancelar) {
            cancelar();
        } else if (e.getSource() == vce.eliminar) {
            eliminar();
        } else if (e.getSource() == vce.editar) {
            editar();
        } else if (e.getSource() == vce.cerrar) {
            cerrar();
        } else if (e.getSource() == vce.guardar) {
            guardar();
        }
    }

//metodos separadas
    public void gps_error(String comp) {
        switch (comp) {
            case "a1":
                vce.a1.setText("0.0");
                vce.a1.setBackground(Color.ORANGE);
                break;
            case "a6":
            case "beta":
                vce.beta.setText("0.0");
                vce.beta.setBackground(Color.ORANGE);
                break;
            case "c":
                vce.c.setText("0.0");
                vce.c.setBackground(Color.ORANGE);
                break;
            case "d1":
                vce.d1.setText("0.0");
                vce.d1.setBackground(Color.ORANGE);
                break;
            case "d2":
                vce.d2.setText("0.0");
                vce.d2.setBackground(Color.ORANGE);
                break;
            case "fc":
                vce.fc.setText("0.0");
                vce.fc.setBackground(Color.ORANGE);
                break;
            case "fi1":
                vce.fi1.setText("0.0");
                vce.fi1.setBackground(Color.ORANGE);
                break;
            case "fi2":
                vce.fi2.setText("0.0");
                vce.fi2.setBackground(Color.ORANGE);
                break;
            case "fs_v":
                vce.fs_v.setText("0.0");
                vce.fs_v.setBackground(Color.ORANGE);
                break;
            case "fy":
                vce.fy.setText("0.0");
                vce.fy.setBackground(Color.ORANGE);
                break;
            case "gamma1":
                vce.gamma1.setText("0.0");
                vce.gamma1.setBackground(Color.ORANGE);
                break;
            case "gamma2":
                vce.gamma2.setText("0.0");
                vce.gamma2.setBackground(Color.ORANGE);
                break;
            case "h1":
                vce.h1.setText("0.0");
                vce.h1.setBackground(Color.ORANGE);
                break;
            case "h2":
                vce.h2.setText("0.0");
                vce.h2.setBackground(Color.ORANGE);
                break;
            case "jTextField22":
                vce.constante1.setText("0.0");
                vce.constante1.setBackground(Color.ORANGE);
                break;
            case "jTextField23":
                vce.constante2.setText("0.0");
                vce.constante2.setBackground(Color.ORANGE);
                break;
            case "jTextField24":
                vce.sum_v.setText("0.0");
                vce.sum_v.setBackground(Color.ORANGE);
                break;
            case "jTextField28":
                vce.alpha.setText("0.0");
                vce.alpha.setBackground(Color.ORANGE);
                break;
            case "jTextField36":
                vce.fs_desliz.setText("0.0");
                vce.fs_desliz.setBackground(Color.ORANGE);
                break;
            case "jTextField37":
                vce.q_min.setText("0.0");
                vce.q_min.setBackground(Color.ORANGE);
                break;
            case "jTextField39":
                vce.qad_espejo.setText("0.0");
                vce.qad_espejo.setBackground(Color.ORANGE);
                break;
            case "jTextField40":
                vce.next_to_qmin.setText("0.0");
                vce.next_to_qmin.setBackground(Color.ORANGE);
                break;
            case "jTextField42":
                vce.as2.setText("0.0");
                vce.as2.setBackground(Color.ORANGE);
                break;
            case "jTextField43":
                vce.fi_f.setText("0.0");
                vce.fi_f.setBackground(Color.ORANGE);
                break;
            case "jTextField44":
                vce.fi_v.setText("0.0");
                vce.fi_v.setBackground(Color.ORANGE);
                break;
            case "jTextField45":
                vce.fi_mr1.setText("0.0");
                vce.fi_mr1.setBackground(Color.ORANGE);
                break;
            case "jTextField46":
                vce.as1.setText("0.0");
                vce.as1.setBackground(Color.ORANGE);
                break;
            case "jTextField48":
                vce.fi_mr2.setText("0.0");
                vce.fi_mr2.setBackground(Color.ORANGE);
                break;
            case "jTextField49":
                vce.ld_propuesto1.setText("0.0");
                vce.ld_propuesto1.setBackground(Color.ORANGE);
                break;
            case "jTextField50":
                vce.ld_propuesto2.setText("0.0");
                vce.ld_propuesto2.setBackground(Color.ORANGE);
                break;
            case "jTextField52":
                vce.as3.setText("0.0");
                vce.as3.setBackground(Color.ORANGE);
                break;
            case "jTextField54":
                vce.beta1.setText("0.0");
                vce.beta1.setBackground(Color.ORANGE);
                break;
            case "jTextField58":
                vce.rho_bal.setText("0.0");
                vce.rho_bal.setBackground(Color.ORANGE);
                break;
            case "jTextField59":
                vce.rho_max.setText("0.0");
                vce.rho_max.setBackground(Color.ORANGE);
                break;
            case "jTextField61":
                vce.pae.setText("0.0");
                vce.pae.setBackground(Color.ORANGE);
                break;
            case "jTextField63":
                vce.pa.setText("0.0");
                vce.pa.setBackground(Color.ORANGE);
                break;
            case "jTextField65":
                vce.variacion_pae.setText("0.0");
                vce.variacion_pae.setBackground(Color.ORANGE);
                break;
            case "jTextField67":
                vce.m.setText("0.0");
                vce.m.setBackground(Color.ORANGE);
                break;
            case "jTextField68":
                vce.sum_fi_mr.setText("0.0");
                vce.sum_fi_mr.setBackground(Color.ORANGE);
                break;
            case "jTextField69":
                vce.m_max.setText("0.0");
                vce.m_max.setBackground(Color.ORANGE);
                break;
            case "jTextField70":
                vce.fi_vc.setText("0.0");
                vce.fi_vc.setBackground(Color.ORANGE);
                break;
            case "jTextField71":
                vce.v_max.setText("0.0");
                vce.v_max.setBackground(Color.ORANGE);
                break;
            case "ka1":
                vce.ka1.setText("0.0");
                vce.ka1.setBackground(Color.ORANGE);
                break;
            case "ka2":
                vce.ka2.setText("0.0");
                vce.ka2.setBackground(Color.ORANGE);
                break;
            case "kh":
                vce.kh.setText("0.0");
                vce.kh.setBackground(Color.ORANGE);
                break;
            case "kp1":
                vce.kp1.setText("0.0");
                vce.kp1.setBackground(Color.ORANGE);
                break;
            case "kp2":
                vce.kp2.setText("0.0");
                vce.kp2.setBackground(Color.ORANGE);
                break;
            case "kv":
                vce.kv.setText("0.0");
                vce.kv.setBackground(Color.ORANGE);
                break;
            case "l1":
                vce.l1.setText("0.0");
                vce.l1.setBackground(Color.ORANGE);
                break;
            case "l2":
                vce.l2.setText("0.0");
                vce.l2.setBackground(Color.ORANGE);
                break;
            case "l3":
                vce.l3.setText("0.0");
                vce.l3.setBackground(Color.ORANGE);
                break;
            case "ld1":
                vce.ld1.setText("0.0");
                vce.ld1.setBackground(Color.ORANGE);
                break;
            case "ld2":
                vce.ld2.setText("0.0");
                vce.ld2.setBackground(Color.ORANGE);
                break;
            case "mpp":
                vce.mpp.setText("0.0");
                vce.mpp.setBackground(Color.ORANGE);
                break;
            case "mwa":
                vce.mwa.setText("0.0");
                vce.mwa.setBackground(Color.ORANGE);
                break;
            case "mwp":
                vce.mwp.setText("0.0");
                vce.mwp.setBackground(Color.ORANGE);
                break;
            case "p_propio":
                vce.p_propio.setText("0.0");
                vce.p_propio.setBackground(Color.ORANGE);
                break;
            case "qa":
                vce.qad.setText("0.0");
                vce.qad.setBackground(Color.ORANGE);
                break;
            case "ro":
                vce.delta.setText("0.0");
                vce.delta.setBackground(Color.ORANGE);
                break;
            case "sc":
                vce.sc.setText("0.0");
                vce.sc.setBackground(Color.ORANGE);
                break;
            case "var_e":
                vce.var_e.setText("0.0");
                vce.var_e.setBackground(Color.ORANGE);
                break;
            case "wa":
                vce.wa.setText("0.0");
                vce.wa.setBackground(Color.ORANGE);
                break;
            case "wp":
                vce.wp.setText("0.0");
                vce.wp.setBackground(Color.ORANGE);
                break;
            case "alpha":
                vce.alpha.setText("0.0");
                vce.alpha.setBackground(Color.ORANGE);
                break;
        }
    }

    public double redondeo(double num, int cifras) {
        int aux;
        if (cifras == 2) {
            num = num + 0.005;
            num = num * 100;
            aux = (int) num;
            num = (double) aux / 100;
        } else if (cifras == 1) {
            num = num + 0.05;
            num = num * 10;
            aux = (int) num;
            num = (double) aux / 10;
        }
        return num;
    }

    public void llenado_elementos() {
        elementos.clear();
        elementos_mombre.clear();
        elementos.add(vce.fi1.getText());
        elementos_mombre.add("fi1");
        elementos.add(vce.fi2.getText());
        elementos_mombre.add("fi2");
        elementos.add(vce.gamma1.getText());
        elementos_mombre.add("gamma1");
        elementos.add(vce.gamma2.getText());
        elementos_mombre.add("gamma2");

        elementos.add(vce.ka1.getText());
        elementos_mombre.add("ka1");
        elementos.add(vce.kp1.getText());
        elementos_mombre.add("kp1");
        elementos.add(vce.sc.getText());
        elementos_mombre.add("sc");
        elementos.add(vce.h1.getText());
        elementos_mombre.add("h1");
        elementos.add(vce.h2.getText());
        elementos_mombre.add("h2");

        elementos.add(vce.qad.getText());
        elementos_mombre.add("qa");
        elementos.add(vce.d1.getText());
        elementos_mombre.add("d1");
        elementos_mombre.add("h5");
        elementos_mombre.add("h6");
        elementos_mombre.add("h7");
        elementos.add(vce.a1.getText());
        elementos_mombre.add("a1");
        elementos.add(vce.d2.getText());
        elementos_mombre.add("d2");
        elementos.add(vce.l1.getText());
        elementos_mombre.add("l1");
        elementos.add(vce.l2.getText());
        elementos_mombre.add("l2");
        elementos.add(vce.l3.getText());
        elementos_mombre.add("l3");
        elementos.add(vce.ld1.getText());
        elementos_mombre.add("ld1");
        elementos.add(vce.ld2.getText());
        elementos_mombre.add("ld2");
        elementos_mombre.add("a6");

        elementos.add(vce.fc.getText());
        elementos_mombre.add("fc");
        elementos.add(vce.fy.getText());
        elementos_mombre.add("fy");

        elementos.add(vce.kh.getText());
        elementos_mombre.add("kh");
        elementos.add(vce.kv.getText());
        elementos_mombre.add("kv");
        elementos.add(vce.delta.getText());
        elementos_mombre.add("ro");
        elementos.add(vce.beta.getText());
        elementos_mombre.add("beta");
        elementos.add(vce.c.getText());
        elementos_mombre.add("c");
        elementos.add(vce.var_e.getText());
        elementos_mombre.add("var_e");
        elementos.add(vce.alpha.getText());
        elementos_mombre.add("alpha");
    }

    public void ejecutor() {
//        ka_kp(1);
//        ka_kp(2);
        ka_kp();
        sigma();
        peso_pasivo_wp();
        peso_activo_wa();
        momento_activo_mwa();
        momento_pasivo_mwp();
        sum_w1_w2_w3();
        sum_mw1_mw2_mw3();
        sumatoria_mr();
        fs_volteo();
        sumatoria_v();
        fs_deslizamiento();
        revision_capacidad();
        diseño_muro();
        empuje_activo_sismico();
        acero_corrido();
        baston1();
        baston2();
        acero_temperatura();
        revision();
    }

    public void almacenar_muro() {
        if (vce.name.getText() == null || vce.name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe poner un nombre que identifique al muro");
        } else {
            //invento
            ArrayList<String> data = new ArrayList<>();
            double altura = (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText()));
            data.add(String.valueOf(altura));
            double ancho = Double.parseDouble(vce.a1.getText());//puede ser d2
            data.add(String.valueOf(ancho));
            double altura_zapata = Double.parseDouble(vce.var_e.getText());
            data.add(String.valueOf(altura_zapata));
            double largo_zapata = (Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText()));
            data.add(String.valueOf(largo_zapata));
            double bajo_zapata = Double.parseDouble(vce.d1.getText());
            data.add(String.valueOf(bajo_zapata));

            //datos_muro.clear();
            ArrayList<String> datos_muro = new ArrayList<>();
            datos_muro.add(vce.name.getText());
            datos_muro.add(vce.fi1.getText());
            datos_muro.add(vce.gamma1.getText());
            datos_muro.add(vce.sc.getText());
            datos_muro.add(vce.ka1.getText());
            datos_muro.add(vce.fi2.getText());
            datos_muro.add(vce.gamma2.getText());
            datos_muro.add(vce.c.getText());
            datos_muro.add(vce.qad.getText());
            datos_muro.add(vce.kp2.getText());
            //inicio geometria indice 10
            datos_muro.add(vce.h1.getText());
            datos_muro.add(vce.h2.getText());
            datos_muro.add(vce.d1.getText());
            datos_muro.add(vce.var_e.getText());
            datos_muro.add(vce.a1.getText());
            datos_muro.add(vce.d2.getText());
            datos_muro.add(vce.l1.getText());
            datos_muro.add(vce.l2.getText());
            datos_muro.add(vce.l3.getText());
            //fin geometria indice 18
            datos_muro.add(vce.wp.getText());
            datos_muro.add(vce.wa.getText());
            datos_muro.add(vce.p_propio.getText());
            datos_muro.add(vce.fs_v.getText());
            datos_muro.add(vce.mwp.getText());
            datos_muro.add(vce.mwa.getText());
            datos_muro.add(vce.mpp.getText());
            datos_muro.add(vce.constante1.getText());
            datos_muro.add(vce.alpha.getText());
            datos_muro.add(vce.sum_v.getText());
            datos_muro.add(vce.fs_desliz.getText());
            datos_muro.add(vce.constante2.getText());
            datos_muro.add(vce.q_max.getText());
            datos_muro.add(vce.q_min.getText());
            datos_muro.add(vce.qad_espejo.getText());
            datos_muro.add(vce.next_to_qmin.getText());
            datos_muro.add(vce.fc.getText());
            //36
            datos_muro.add(String.valueOf(vce.r.getSelectedIndex()));
//            switch (vce.r.getSelectedIndex()) {
//                case 0:
//                    datos_muro.add("2.5");
//                    break;
//                case 1:
//                    datos_muro.add("5.0");
//                    break;
//                case 2:
//                    datos_muro.add("7.5");
//                    break;
//            }
            datos_muro.add(vce.fy.getText());
            datos_muro.add(vce.beta1.getText());
            datos_muro.add(vce.rho_bal.getText());
            datos_muro.add(vce.rho_max.getText());
            datos_muro.add(vce.kv.getText());
            datos_muro.add(vce.kh.getText());
            datos_muro.add(vce.beta.getText());
            datos_muro.add(vce.delta.getText());
            datos_muro.add(vce.pae.getText());
            datos_muro.add(vce.pa.getText());
            datos_muro.add(vce.variacion_pae.getText());
            datos_muro.add(vce.m.getText());
            datos_muro.add(String.valueOf(vce.varillas1.getSelectedIndex()));
            datos_muro.add(vce.fi_f.getText());
            datos_muro.add(vce.fi_v.getText());
            datos_muro.add(String.valueOf(vce.separacion1.getSelectedIndex()));
            datos_muro.add(vce.as1.getText());
            datos_muro.add(vce.fi_mr1.getText());
            datos_muro.add(String.valueOf(vce.varillas2.getSelectedIndex()));
            datos_muro.add(vce.as2.getText());
            datos_muro.add(vce.ld_propuesto1.getText());
            datos_muro.add(vce.fi_mr2.getText());
            datos_muro.add(String.valueOf(vce.separacion2.getSelectedIndex()));
            datos_muro.add(vce.ld1.getText());
            datos_muro.add(String.valueOf(vce.varillas3.getSelectedIndex()));
            datos_muro.add(vce.as3.getText());
            datos_muro.add(vce.ld_propuesto2.getText());
            datos_muro.add(vce.fi_mr3.getText());
            datos_muro.add(String.valueOf(vce.separacion3.getSelectedIndex()));
            datos_muro.add(vce.ld2.getText());
            datos_muro.add(vce.sum_fi_mr.getText());
            datos_muro.add(vce.m_max.getText());
            datos_muro.add(vce.fi_vc.getText());
            datos_muro.add(vce.v_max.getText());
            datos_muro.add(String.valueOf(vce.varillas4.getSelectedIndex()));
            datos_muro.add(String.valueOf(vce.separacion4.getSelectedIndex()));
            datos_muro.add(vce.as4.getText());
            Muro mu = new Muro(datos_muro);
            py.añdir_muro(mu);
            vce.combo_almacen.addItem(datos_muro.get(0));
            mu = null;
            if (py.getLista().size() > 1) {
                System.out.println("");
                System.out.println(py.getLista().get(0).getLista_muro().get(0));
                System.out.println(py.getLista().get(1).getLista_muro().get(0));
                System.out.println("");
            }
        }

    }

    public void eliminar() {
        try {
            if (vce.combo_almacen.getItemCount() != 0) {
                py.eliminar_muro(vce.combo_almacen.getSelectedIndex());
                System.out.println(vce.combo_almacen.getSelectedIndex());
                vce.combo_almacen.removeItemAt(vce.combo_almacen.getSelectedIndex());
            } else {
                //contador_viga = 1;
                JOptionPane.showMessageDialog(null, "No tiene muros disponibles para eliminar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique, puede que este elemento ya ha sido eliminado");
        }
    }

    public void cancelar() {
        //se reinician los valores aq 1.0 por el momento
        vce.fi1.setText("0.1");
        vce.fi1.setBackground(Color.WHITE);
        vce.gamma1.setText("0.1");
        vce.gamma1.setBackground(Color.WHITE);
        vce.sc.setText("0.1");
        vce.sc.setBackground(Color.WHITE);
        vce.ka1.setText("0.1");
        vce.ka1.setBackground(Color.WHITE);
        vce.kp1.setText("0.1");
        vce.kp1.setBackground(Color.WHITE);
        vce.fi2.setText("0.1");
        vce.fi2.setBackground(Color.WHITE);
        vce.gamma2.setText("0.1");
        vce.gamma2.setBackground(Color.WHITE);
        vce.c.setText("0.1");
        vce.c.setBackground(Color.WHITE);
        vce.qad.setText("0.1");
        vce.qad.setBackground(Color.WHITE);
        vce.kp2.setText("0.1");
        vce.kp2.setBackground(Color.WHITE);
        vce.ka2.setText("0.1");
        vce.ka2.setBackground(Color.WHITE);
        vce.h1.setText("0.1");
        vce.h1.setBackground(Color.WHITE);
        vce.h2.setText("0.1");
        vce.h2.setBackground(Color.WHITE);
        vce.d1.setText("0.1");
        vce.d1.setBackground(Color.WHITE);
        vce.var_e.setText("0.1");
        vce.var_e.setBackground(Color.WHITE);
        vce.a1.setText("0.1");
        vce.a1.setBackground(Color.WHITE);
        vce.d2.setText("0.1");
        vce.d2.setBackground(Color.WHITE);
        vce.l1.setText("0.1");
        vce.l1.setBackground(Color.WHITE);
        vce.l2.setText("0.1");
        vce.l2.setBackground(Color.WHITE);
        vce.l3.setText("0.1");
        vce.l3.setBackground(Color.WHITE);
        vce.wp.setText("0.1");
        vce.wp.setBackground(Color.WHITE);
        vce.wa.setText("0.1");
        vce.wa.setBackground(Color.WHITE);
        vce.p_propio.setText("0.1");
        vce.p_propio.setBackground(Color.WHITE);
        vce.fs_v.setText("0.1");
        vce.fs_v.setBackground(Color.WHITE);
        vce.mwp.setText("0.1");
        vce.mwp.setBackground(Color.WHITE);
        vce.mwa.setText("0.1");
        vce.mwa.setBackground(Color.WHITE);
        vce.mpp.setText("0.1");
        vce.mpp.setBackground(Color.WHITE);
        vce.alpha.setText("0.1");
        vce.alpha.setBackground(Color.WHITE);
        vce.sum_v.setText("0.1");
        vce.sum_v.setBackground(Color.WHITE);
        vce.fs_desliz.setText("0.1");
        vce.fs_desliz.setBackground(Color.WHITE);
        vce.q_max.setText("0.1");
        vce.q_max.setBackground(Color.WHITE);
        vce.q_min.setText("0.1");
        vce.q_min.setBackground(Color.WHITE);
        vce.qad_espejo.setText("0.1");
        vce.qad_espejo.setBackground(Color.WHITE);
        vce.fc.setText("0.1");
        vce.fc.setBackground(Color.WHITE);
        vce.r.setSelectedIndex(0);
        vce.fy.setText("0.1");
        vce.fy.setBackground(Color.WHITE);
        vce.beta1.setText("0.1");
        vce.beta1.setBackground(Color.WHITE);
        vce.rho_bal.setText("0.1");
        vce.rho_bal.setBackground(Color.WHITE);
        vce.rho_max.setText("0.1");
        vce.rho_max.setBackground(Color.WHITE);
        vce.kv.setText("0.1");
        vce.kv.setBackground(Color.WHITE);
        vce.kh.setText("0.1");
        vce.kh.setBackground(Color.WHITE);
        vce.beta.setText("0.1");
        vce.beta.setBackground(Color.WHITE);
        vce.delta.setText("0.1");
        vce.delta.setBackground(Color.WHITE);
        vce.pae.setText("0.1");
        vce.pae.setBackground(Color.WHITE);
        vce.pa.setText("0.1");
        vce.pa.setBackground(Color.WHITE);
        vce.variacion_pae.setText("0.1");
        vce.variacion_pae.setBackground(Color.WHITE);
        vce.m.setText("0.1");
        vce.m.setBackground(Color.WHITE);
        vce.varillas1.setSelectedIndex(0);
        vce.fi_f.setText("0.1");
        vce.fi_f.setBackground(Color.WHITE);
        vce.fi_v.setText("0.1");
        vce.fi_v.setBackground(Color.WHITE);
        vce.separacion1.setSelectedIndex(0);
        vce.as1.setText("0.1");
        vce.as1.setBackground(Color.WHITE);
        vce.fi_mr1.setText("0.1");
        vce.fi_mr1.setBackground(Color.WHITE);
        vce.varillas2.setSelectedIndex(0);
        vce.as2.setText("0.1");
        vce.as2.setBackground(Color.WHITE);
        vce.ld_propuesto1.setText("0.1");
        vce.ld_propuesto1.setBackground(Color.WHITE);
        vce.fi_mr2.setText("0.1");
        vce.fi_mr2.setBackground(Color.WHITE);
        vce.separacion2.setSelectedIndex(0);
        vce.l1.setText("0.1");
        vce.l1.setBackground(Color.WHITE);
        vce.varillas3.setSelectedIndex(0);
        vce.as3.setText("0.1");
        vce.as3.setBackground(Color.WHITE);
        vce.ld_propuesto2.setText("0.1");
        vce.ld_propuesto2.setBackground(Color.WHITE);
        vce.ld1.setText("0.1");
        vce.ld1.setBackground(Color.WHITE);
        vce.fi_mr3.setText("0.1");
        vce.fi_mr3.setBackground(Color.WHITE);
        vce.separacion3.setSelectedIndex(0);
        vce.ld2.setText("0.1");
        vce.ld2.setBackground(Color.WHITE);
        vce.sum_fi_mr.setText("0.1");
        vce.sum_fi_mr.setBackground(Color.WHITE);
        vce.m_max.setText("0.1");
        vce.m_max.setBackground(Color.WHITE);
        vce.fi_vc.setText("0.1");
        vce.fi_vc.setBackground(Color.WHITE);
        vce.v_max.setText("0.1");
        vce.v_max.setBackground(Color.WHITE);
        vce.name.setText(null);
        vce.varillas4.setSelectedIndex(0);
        vce.separacion4.setSelectedIndex(0);
        vce.as4.setText("1.0");

        //vc.fi1.requestFocus();
    }

    public void editar() {
        if (vce.combo_almacen.getComponentCount() != 0) {
            int estado = JOptionPane.showConfirmDialog(null, "Está seguro que quiere editar?\nTenga en cuenta que si no ha guardado el elemento en el que está trabajando perderá los datos.");
            if (estado == JOptionPane.YES_OPTION) {
                cancelar();
                Muro muro_edit = new Muro(elementos);
                ArrayList<String> lista_edit = py.getLista().get(vce.combo_almacen.getSelectedIndex()).getLista_muro();
                //0-10
                vce.name.setText(lista_edit.get(0));
                vce.fi1.setText(lista_edit.get(1));
                vce.gamma1.setText(lista_edit.get(2));
                vce.sc.setText(lista_edit.get(3));
                vce.ka1.setText(lista_edit.get(4));
                vce.fi2.setText(lista_edit.get(5));
                vce.gamma2.setText(lista_edit.get(6));
                vce.c.setText(lista_edit.get(7));
                vce.qad.setText(lista_edit.get(8));
                vce.kp2.setText(lista_edit.get(9));
                vce.h1.setText(lista_edit.get(10));
                //11-20
                vce.h2.setText(lista_edit.get(11));
                vce.d1.setText(lista_edit.get(12));
                vce.var_e.setText(lista_edit.get(13));
                vce.a1.setText(lista_edit.get(14));
                vce.d2.setText(lista_edit.get(15));
                vce.l1.setText(lista_edit.get(16));
                vce.l2.setText(lista_edit.get(17));
                vce.l3.setText(lista_edit.get(18));
                vce.wp.setText(lista_edit.get(19));
                vce.wa.setText(lista_edit.get(20));
                //21-30
                vce.p_propio.setText(lista_edit.get(21));
                vce.fs_v.setText(lista_edit.get(22));
                vce.mwp.setText(lista_edit.get(23));
                vce.mwa.setText(lista_edit.get(24));
                vce.mpp.setText(lista_edit.get(25));
                vce.constante1.setText(lista_edit.get(26));
                vce.alpha.setText(lista_edit.get(27));
                vce.sum_v.setText(lista_edit.get(28));
                vce.fs_desliz.setText(lista_edit.get(29));
                vce.constante2.setText(lista_edit.get(30));
                //31-40
                vce.q_max.setText(lista_edit.get(31));
                vce.q_min.setText(lista_edit.get(32));
                vce.qad_espejo.setText(lista_edit.get(33));
                vce.next_to_qmin.setText(lista_edit.get(34));
                vce.fc.setText(lista_edit.get(35));
                vce.r.setSelectedIndex(Integer.parseInt(lista_edit.get(36)));
                vce.fy.setText(lista_edit.get(37));
                vce.beta1.setText(lista_edit.get(38));
                vce.rho_bal.setText(lista_edit.get(39));
                vce.rho_max.setText(lista_edit.get(40));
                vce.kv.setText(lista_edit.get(41));
                //42-50
                vce.kh.setText(lista_edit.get(42));
                vce.beta.setText(lista_edit.get(43));
                vce.delta.setText(lista_edit.get(44));
                vce.pae.setText(lista_edit.get(45));
                vce.pa.setText(lista_edit.get(46));
                vce.variacion_pae.setText(lista_edit.get(47));
                vce.m.setText(lista_edit.get(48));
                vce.varillas1.setSelectedIndex(Integer.parseInt(lista_edit.get(49)));
                vce.fi_f.setText(lista_edit.get(50));
                vce.fi_v.setText(lista_edit.get(51));
                //52-70
                vce.separacion1.setSelectedIndex(Integer.parseInt(lista_edit.get(52)));
                vce.as1.setText(lista_edit.get(53));
                vce.fi_mr1.setText(lista_edit.get(54));
                vce.varillas2.setSelectedIndex(Integer.parseInt(lista_edit.get(55)));
                vce.as2.setText(lista_edit.get(56));
                vce.ld_propuesto1.setText(lista_edit.get(57));
                vce.fi_mr2.setText(lista_edit.get(58));
                vce.separacion2.setSelectedIndex(Integer.parseInt(lista_edit.get(59)));
                vce.ld1.setText(lista_edit.get(60));
                vce.varillas3.setSelectedIndex(Integer.parseInt(lista_edit.get(61)));
                vce.as3.setText(lista_edit.get(62));
                vce.ld_propuesto2.setText(lista_edit.get(63));
                vce.fi_mr3.setText(lista_edit.get(64));
                vce.separacion3.setSelectedIndex(Integer.parseInt(lista_edit.get(65)));
                vce.ld2.setText(lista_edit.get(66));
                vce.sum_fi_mr.setText(lista_edit.get(67));
                vce.m_max.setText(lista_edit.get(68));
                vce.fi_vc.setText(lista_edit.get(69));
                vce.v_max.setText(lista_edit.get(70));
                //71-73
                vce.varillas4.setSelectedIndex(Integer.parseInt(lista_edit.get(71)));
                vce.separacion4.setSelectedIndex(Integer.parseInt(lista_edit.get(72)));
                vce.as4.setText(lista_edit.get(73));

                py.eliminar_muro(vce.combo_almacen.getSelectedIndex());
                vce.combo_almacen.removeItemAt(vce.combo_almacen.getSelectedIndex());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tiene elementos para editar");
        }
    }

    public void cerrar() {
        if (vce.combo_almacen.getItemCount() != 0) {
            int estado = JOptionPane.showConfirmDialog(null, "Está seguro que quiere cancelar?\nSi cancela perderá todo el progreso alcanzado.");
            switch (estado) {
                case JOptionPane.YES_OPTION:
                    py = null;
                    vce.dispose();
                    break;
            }
        } else {
            py = null;
            vce.dispose();
        }
    }

    public void guardar() {
        if (vce.combo_almacen.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tiene que tener al menos un elemento procesado para poder guardar el proyecto");
        } else {
//            
            //py.trazado_muro1();

            JFileChooser jf = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo archivos con extension .scr", "scr");
            jf.setFileFilter(filtro);
            String ruta = "";
            try {
                int returnval = jf.showSaveDialog(null);
                if (returnval == jf.APPROVE_OPTION) {
                    ObjectOutputStream out = null;
                    //ObjectOutputStream out1=null;
                    ruta = jf.getSelectedFile().getAbsolutePath();
                    //se crea el fichero .va para una futura recuperacion de datos
                    Py_Serializable ps = new Py_Serializable(py, ruta, vce.getNombre_py());
                    ps.serializar_py();
                    //ps.recuperacion_py();
                    //System.out.println(jf.getSelectedFile().getAbsolutePath());
                    if (jf.getSelectedFile().getAbsolutePath().substring(jf.getSelectedFile().getAbsolutePath().length() - 4, jf.getSelectedFile().getAbsolutePath().length()).equals(".scr")) {
                        int marca = jf.getSelectedFile().getAbsolutePath().lastIndexOf("\\");
                        jf.getSelectedFile().getAbsolutePath().substring(marca + 1); //para quedarme solo con el nombre del archivo
                        File fo = new File(jf.getSelectedFile().getAbsolutePath().substring(marca + 1));//se elimina el fichero para evitar corromper el archivo

                        fo.delete();

                        out = new ObjectOutputStream(new FileOutputStream(jf.getSelectedFile().getAbsolutePath().substring(marca + 1)));
                        py.trazado_muro1(jf.getSelectedFile().getAbsolutePath().substring(0, jf.getSelectedFile().getAbsolutePath().length() - 4));
                        //trazado.fichero_dibujo(py, jf.getSelectedFile().getAbsolutePath().substring(0, jf.getSelectedFile().getAbsolutePath().length() - 4));
                        out.close();

                    } else {
                        py.trazado_muro1(jf.getSelectedFile().getAbsolutePath() + "(" + vce.getNombre_py() + ")");
                        //trazado.fichero_dibujo(py, jf.getSelectedFile().getAbsolutePath() + "(" + vce.getNombre_py() + ")");
                    }
                    //contador_viga = 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ka_kp(int tipo_suelo) {
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            double res;
            double res1;
            if (tipo_suelo == 1) {
                res = redondeo(Math.pow(Math.tan((45 * conversion) - Double.parseDouble(vce.fi1.getText()) / 2), 2), 2);
                vce.ka1.setText(String.valueOf(res));
                res1 = Math.pow(Math.tan((45 * conversion) + Double.parseDouble(vce.fi1.getText())), 2);
                vce.kp1.setText(String.valueOf(res1));
            } else {
                res = Math.pow(Math.tan((45 * conversion) - Double.parseDouble(vce.fi2.getText()) / 2), 2);
                vce.ka2.setText(String.valueOf(res));
                res1 = Math.pow(Math.tan((45 * conversion) + Double.parseDouble(vce.fi2.getText())), 2);
                vce.kp2.setText(String.valueOf(res1));
            }
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda ϕ1 o ϕ2");
        }
    }

    public void ka_kp() {
        System.out.println("estoy en ka_kp");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            double delta = Double.parseDouble(vce.delta.getText());
            double beta = Double.parseDouble(vce.beta.getText());
            double alpha = Double.parseDouble(vce.alpha.getText());
            double fi1 = Double.parseDouble(vce.fi1.getText());
            theta = Math.atan(Double.parseDouble(vce.kh.getText()) / (1 - Double.parseDouble(vce.kv.getText())));
            double ka;//es el ka de la memoria
            double res1;
            res1 = redondeo(Math.pow(Math.tan((45 + (Double.parseDouble(vce.fi2.getText())) / 2) * conversion), 2), 2);
            vce.kp2.setText(String.valueOf(res1));
            //res = redondeo(Math.pow(Math.tan((45 - Double.parseDouble(vce.fi1.getText()) / 2) * conversion), 2), 2);

            ka = Math.pow(Math.sin((beta + fi1) * conversion), 2)
                    / (Math.pow(Math.sin(beta * conversion), 2) * Math.sin((beta - delta) * conversion)
                    * (Math.pow(1 + Math.sqrt((Math.sin((delta + fi1) * conversion) * Math.sin((fi1 - alpha) * conversion)) / Math.sin(((beta - delta) * conversion - theta)) * Math.sin((beta + alpha) * conversion)), 2)));

            vce.ka1.setText(String.valueOf(ka));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda ϕ1 o ϕ2");
        }
    }

    public void sigma() {
        System.out.println("estoy en sigma");
        llenado_elementos();
        sigma_a = 0;
        sigma_b = 0;
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            sigma_a = redondeo(Double.parseDouble(vce.ka2.getText()) * Double.parseDouble(vce.sc.getText()), 2);
            sigma_b = redondeo(Double.parseDouble(vce.ka2.getText()) * Double.parseDouble(vce.gamma1.getText()) * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) + Double.parseDouble(vce.sc.getText()) * Double.parseDouble(vce.ka2.getText()), 2);
            sigma_p = redondeo(Double.parseDouble(vce.kp1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Double.parseDouble(vce.h2.getText()), 2);
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    //tener en cuenta que estamos tomando el texfield h2 como la altura en los calculos pero hay que revisar que vamos hacer con esta situacion
    public void empuje_activo() {
        System.out.println("estoy en empuje_activo");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            //no se ubica aqui
//            vce.wa.setText(String.valueOf(redondeo((0.5 * Double.parseDouble(vce.ka1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()), 2)) ,2)));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    public void empuje_pasivo() {
        System.out.println("estoy en empuje_pasivo");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            //no se ubica aqui
//           vce.wp.setText(String.valueOf(redondeo(0.5 * Double.parseDouble(vce.kp1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText()), 2) + 2 * Double.parseDouble(vce.c.getText())
//                    * Math.sqrt(Double.parseDouble(vce.kp1.getText())) * (Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText())) ,2)));
//            System.out.println("Mi Pp "+(redondeo(0.5 * Double.parseDouble(vce.kp1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText()), 2) + 2 * Double.parseDouble(vce.c.getText())
//                    * Math.sqrt(Double.parseDouble(vce.kp1.getText())) * (Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText())) ,2)));
        } else {
            elementos_mombre.get(estado);
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    //no tenemos donde ponerlo en la ventana 
    public void momento_activo_mpa() {
        System.out.println("estoy en momento_activo_mpa");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            //no se hubica aqui, debe ser en mpa pero nno tenemos esta variable aun creada
            //vc.mwa.setText(String.valueOf(0.5 * Double.parseDouble(vce.ka2.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()), 2) * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) / 3));
            mpa = redondeo((0.5 * Double.parseDouble(vce.ka1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()), 2) * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) / 3), 2);
            System.out.println("mpa " + mpa);
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    //no tenemos donde ponerlo en la ventana
    public void momento_pasivo_mpp() {
        System.out.println("estoy en momento_pasivo_mpp");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            //no se hubica aqui, debe ser en mpp pero nno tenemos esta variable aun creada
            vce.mwp.setText(String.valueOf(redondeo(0.5 * Double.parseDouble(vce.kp1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText()), 2) + 2 * Double.parseDouble(vce.c.getText())
                    * Math.sqrt(Double.parseDouble(vce.kp1.getText())) * (Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText()))
                    * Double.parseDouble(vce.h2.getText()) / 3, 2)));
            MPp = redondeo((0.5 * Double.parseDouble(vce.kp1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText()), 2) + 2 * Double.parseDouble(vce.c.getText())
                    * Math.sqrt(Double.parseDouble(vce.kp1.getText())) * (Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText()))
                    * Double.parseDouble(vce.h2.getText()) / 3), 2);
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    //Revision por volteo
    public void peso_pasivo_wp() {
        System.out.println("estoy en peso_pasivo_wp");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            vce.wp.setText(String.valueOf(redondeo(Double.parseDouble(vce.l1.getText()) * (Double.parseDouble(vce.h2.getText()) - Double.parseDouble(vce.var_e.getText())) * Double.parseDouble(vce.gamma2.getText()), 2)));
//            System.out.println("Valor esperado de wp");
//            System.out.println((Double.parseDouble(vce.l1.getText()) * (Double.parseDouble(vce.h2.getText()) - Double.parseDouble(vce.var_e.getText())) * Double.parseDouble(vce.gamma1.getText())));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    public void momento_pasivo_mwp() {
        System.out.println("estoy en momento_pasivo_mwp");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            vce.mwp.setText(String.valueOf(redondeo(Double.parseDouble(vce.wp.getText()) * (Double.parseDouble(vce.l1.getText()) / 2), 2)));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    public void peso_activo_wa() {
        System.out.println("estoy en peso_activo_wa");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            vce.wa.setText(String.valueOf(redondeo(Double.parseDouble(vce.l3.getText()) * ((Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) - Double.parseDouble(vce.var_e.getText())) * Double.parseDouble(vce.gamma1.getText()), 2)));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

    public void momento_activo_mwa() {
        System.out.println("estoy en momento_activo_mwa");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            vce.mwa.setText(String.valueOf(redondeo(Double.parseDouble(vce.wa.getText()) * (Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText()) / 2), 2)));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    public void sum_w1_w2_w3() {
        System.out.println("estoy en sum_w1_w2_w3");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
//            w3 = redondeo((Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText())) * Double.parseDouble(vce.var_e.getText()) * gamma_c ,2);
//            w2 = redondeo(((Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()) - Double.parseDouble(vce.var_e.getText()))) / 2 * Double.parseDouble(vce.var_e.getText()) * gamma_c ,2);
//            w1 = redondeo(Double.parseDouble(vce.a1.getText()) * (Double.parseDouble(vce.h1.getText()) - Double.parseDouble(vce.var_e.getText())) * gamma_c ,2);
//            vce.p_propio.setText(String.valueOf(redondeo(w3 + w2 + w1 ,2)));
            w3 = (Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText())) * Double.parseDouble(vce.var_e.getText()) * gamma_c;
            w2 = ((Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()) - Double.parseDouble(vce.var_e.getText()))) / 2 * Double.parseDouble(vce.var_e.getText()) * gamma_c;
            w1 = Double.parseDouble(vce.a1.getText()) * ((Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()) - Double.parseDouble(vce.var_e.getText()))) * gamma_c;
            vce.p_propio.setText(String.valueOf(redondeo(w3 + w2 + w1, 2)));
            System.out.println("w3 " + w3);
            System.out.println("w2 " + w2);
            System.out.println("w1 " + w1);
            System.out.println("pp " + (w1 + w2 + w3));
        } else {
            elementos_mombre.get(estado);
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

//    public void sum_w1_w2_w3() {
//        elementos.clear();
//        elementos.add(vce.l1.getText());
//        elementos.add(vce.l2.getText());
//        elementos.add(vce.l3.getText());
//        elementos.add(vce.h1.getText());
//        elementos.add(vce.h2.getText());
//        elementos.add(vce.var_e.getText());
//        elementos.add(vce.gamma1.getText());
//        elementos.add(vce.a1.getText());
//        if (ChecarErrores.Dobles(elementos) == 0) {
//            double sumWs = (w1 + w2 + w3);
//        } else {
//            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
//        }        
//    }
    public void sum_mw1_mw2_mw3() {
        System.out.println("estoy en sum_mw1_mw2_mw3");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            mw3 = redondeo((w3 * (Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText())) / 2), 2);
            mw2 = redondeo((w2 * (Double.parseDouble(vce.l1.getText()) + (Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) * 2 / 3)), 2);
            mw1 = redondeo((w1 * (Double.parseDouble(vce.l1.getText()) + (Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) + (Double.parseDouble(vce.a1.getText())) / 2)), 2);
            vce.mpp.setText(String.valueOf(redondeo((mw3 + mw2 + mw1), 2)));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    public void sumatoria_mr() {
        System.out.println("estoy en sumatoria_mr");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            sum_mr = redondeo((MPp + mw3 + mw2 + mw1 + Double.parseDouble(vce.mwp.getText()) + Double.parseDouble(vce.mwa.getText())), 2);
            System.out.println("sum_mr " + sum_mr);
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    public void fs_volteo() {
        System.out.println("estoy en fs_volteo");
        llenado_elementos();
        mpa = redondeo((0.5 * Double.parseDouble(vce.ka1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()), 2) * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) / 3), 2);
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            vce.fs_v.setText(String.valueOf(redondeo((sum_mr / mpa), 2)));
            System.out.println("mpa " + mpa);
            System.out.println("fs_volteo " + sum_mr / mpa);
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    //modulo revision por desplazamiento
    public void sumatoria_v() {
        System.out.println("estoy en sumatoria_v");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            vce.sum_v.setText(String.valueOf(redondeo(Double.parseDouble(vce.p_propio.getText()) + Double.parseDouble(vce.wp.getText()) + Double.parseDouble(vce.wa.getText()), 2)));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    public void fs_deslizamiento() {
        System.out.println("estoy en fs_deslizamiento");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        double delta = Double.parseDouble(vce.delta.getText());
        double beta = Double.parseDouble(vce.beta.getText());
        double alpha = Double.parseDouble(vce.alpha.getText());
        double fi1 = Double.parseDouble(vce.fi1.getText());

        double h1 = Double.parseDouble(vce.h1.getText());
        double h2 = Double.parseDouble(vce.h2.getText());
        double gamma1 = Double.parseDouble(vce.gamma1.getText());
        double sc = Double.parseDouble(vce.sc.getText());
        //en la memoria hay dos formulas distintas para pa, aqui se va A aplicar la mas larga para que esto de**************************************************
        //double pa = redondeo((0.5 * Double.parseDouble(vce.ka1.getText()) * Double.parseDouble(vce.gamma2.getText()) * Math.pow(Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()), 2)), 2);
        double pa = 0.5 * ka * gamma1 * Math.pow(h1 + h2, 2) + ka * (h1 + h2) * sc * Math.sin(beta * conversion) / Math.sin((beta + alpha) * conversion);
        double Pp = redondeo(0.5 * Double.parseDouble(vce.kp2.getText()) * Double.parseDouble(vce.gamma2.getText()) * Math.pow(Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText()), 2) + 2 * Double.parseDouble(vce.c.getText())
                * Math.sqrt(Double.parseDouble(vce.kp2.getText())) * (Double.parseDouble(vce.h2.getText()) + Double.parseDouble(vce.d1.getText())), 2);
        if (estado == -20) {
            vce.fs_desliz.setText(String.valueOf(redondeo((((Double.parseDouble(vce.sum_v.getText())) * Math.tan(k1 * Double.parseDouble(vce.fi2.getText()) * conversion) + ((Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText())) * k2 * Double.parseDouble(vce.c.getText()))
                    + Pp)
                    / (pa * Math.cos(Double.parseDouble(vce.alpha.getText()) * conversion))), 2)));
            System.out.println("");

            System.out.println("tan que buscamos " + (Math.tan(k1 * Double.parseDouble(vce.fi2.getText()) * conversion)));
            System.out.println("pa que buscamos " + pa);
            System.out.println("cociente que buscamos " + (Math.tan(k1 * Double.parseDouble(vce.fi2.getText()) * conversion)));
            System.out.println("");
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    //revision por capacidad
    public void revision_capacidad() {
        System.out.println("estoy en revision_capacidad");
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            mpa = redondeo((0.5 * Double.parseDouble(vce.ka1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()), 2) * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) / 3), 2);
            sum_mr = redondeo(MPp + mw3 + mw2 + mw1 + Double.parseDouble(vce.mwp.getText()) + Double.parseDouble(vce.mwa.getText()), 2);
            m_neto = redondeo((sum_mr - mpa), 2);
            x = redondeo(m_neto / Double.parseDouble(vce.sum_v.getText()), 2);
            ee = redondeo(((Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText()) / 2) - x), 2);
            ma = redondeo((Double.parseDouble(vce.sum_v.getText()) * ee), 2);
            A = redondeo(Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText()), 2);
            c = redondeo(((Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText())) / 2), 2);
            i = redondeo((Math.pow(Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText()), 3) / 12), 2);
            double q_max = redondeo((Double.parseDouble(vce.sum_v.getText()) / A) + (ma * c) / i, 2);
            double q_min = redondeo((Double.parseDouble(vce.sum_v.getText()) / A) - (ma * c) / i, 2);
            vce.q_max.setText(String.valueOf(q_max));
            vce.q_min.setText(String.valueOf(q_min));
            //preguntar por la estructura correcta de las comparaciones
            if (q_max <= Double.parseDouble(vce.qad.getText())) {
                vce.q_max.setBackground(Color.WHITE);
            } else {
                vce.q_max.setBackground(Color.red);
            }
            if (q_min >= 0) {
                vce.q_min.setBackground(Color.WHITE);
            } else {
                vce.q_min.setBackground(Color.red);
            }
            System.out.println("mpa " + mpa);
            System.out.println("sum_mr " + sum_mr);
            System.out.println("m_neto " + m_neto);
            System.out.println("x " + x);
            System.out.println("ee " + ee);
            System.out.println("ma " + ma);
            System.out.println("a " + A);
            System.out.println("c " + c);
            System.out.println("i " + i);
            System.out.println("q_max " + q_max);
            System.out.println("q_min " + q_min);
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

    //diseño del muro
    public void diseño_muro() {
        System.out.println("estoy en diseño_muro");
        switch (vce.r.getSelectedIndex()) {
            case 0:
                r = 2.5;
                break;
            case 1:
                r = 5.0;
                break;
            case 2:
                r = 7.5;
                break;
        }
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            //A = redondeo(Double.parseDouble(vce.l1.getText()) + Double.parseDouble(vce.l2.getText()) + Double.parseDouble(vce.l3.getText()), 2);
            //h = redondeo(Double.parseDouble(vce.l2.getText()) - (Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) / (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) * Double.parseDouble(vce.h2.getText()), 2);
            System.out.println("r " + r);
            h = (Double.parseDouble(vce.l2.getText()) - (Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) / (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) * Double.parseDouble(vce.h2.getText())) * 100;
            m1 = redondeo(Double.parseDouble(vce.fy.getText()) / (0.85 * Double.parseDouble(vce.fc.getText())), 2);
            d = redondeo((h - (r)), 2);
            System.out.println("");
            //System.out.println("a "+A);
            System.out.println("h " + h);
            System.out.println("m1 " + m1);
            System.out.println("d " + d);
            double beta1_tem = redondeo(0.85 - ((0.05 * (Double.parseDouble(vce.fc.getText()) - 280)) / 70), 2);
            if (beta1_tem >= 0.65 && beta1_tem <= 0.85) {
                vce.beta1.setText(String.valueOf(beta1_tem));
            } else if (beta1_tem <= 0.65) {
                vce.beta1.setText("0.65");
            } else if (beta1_tem >= 0.85) {
                vce.beta1.setText("0.85");
            }
            vce.rho_bal.setText(String.valueOf(redondeo((Double.parseDouble(vce.beta1.getText()) / m1) * (es * 0.003 / (Double.parseDouble(vce.fy.getText()) + (es * 0.003))), 2)));
            System.out.println(redondeo((Double.parseDouble(vce.beta1.getText()) / m1) * (es * 0.003 / (Double.parseDouble(vce.fy.getText()) + (es * 0.003))), 2));;
            vce.rho_max.setText(String.valueOf(redondeo(0.75 * Double.parseDouble(vce.rho_bal.getText()), 2)));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

    //Empuje activo ****Este es el módulo "Empuje activo" como tal****
    public void empuje_activo_sismico() {
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            double delta = Double.parseDouble(vce.delta.getText());
            double beta = Double.parseDouble(vce.beta.getText());
            double alpha = Double.parseDouble(vce.alpha.getText());
            double fi1 = Double.parseDouble(vce.fi1.getText());

            double h1 = Double.parseDouble(vce.h1.getText());
            double h2 = Double.parseDouble(vce.h2.getText());
            double gamma1 = Double.parseDouble(vce.gamma1.getText());
            double sc = Double.parseDouble(vce.sc.getText());

            theta = Math.atan(Double.parseDouble(vce.kh.getText()) / (1 - Double.parseDouble(vce.kv.getText())));

            double kae = Math.pow(Math.sin((fi1 - beta) * conversion - theta), 2)
                    / (Math.cos(theta) * Math.pow(Math.sin(beta * conversion), 2) * Math.sin((delta + beta) * conversion + theta)
                    * (Math.pow(1 + Math.sqrt((Math.sin((delta + fi1) * conversion) * Math.sin((fi1 * conversion - theta - alpha * conversion))) / Math.sin(((beta - delta) * conversion - theta)) * Math.sin((beta + alpha) * conversion)), 2)));

            double ka = Math.pow(Math.sin((beta + fi1) * conversion), 2)
                    / (Math.pow(Math.sin(beta * conversion), 2) * Math.sin((beta - delta) * conversion)
                    * (Math.pow(1 + Math.sqrt((Math.sin((delta + fi1) * conversion) * Math.sin((fi1 - alpha) * conversion)) / Math.sin(((beta - delta) * conversion - theta)) * Math.sin((beta + alpha) * conversion)), 2)));

            double pae = 0.5 * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()), 2) * (1 - Double.parseDouble(vce.kv.getText())) * kae;
            //double pae = redondeo(0.5 * gamma1 * Math.pow(h1 + h2, 2) * (1 - kv) * kae, 2);

            double pa = 0.5 * ka * gamma1 * Math.pow(h1 + h2, 2) + ka * (h1 + h2) * sc * Math.sin(beta * conversion) / Math.sin((beta + alpha) * conversion);
//            double pa = redondeo((0.5 * Double.parseDouble(vce.ka1.getText()) * Double.parseDouble(vce.gamma1.getText()) * Math.pow(Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText()), 2)
//                    + Double.parseDouble(vce.ka1.getText()) * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) * Double.parseDouble(vce.sc.getText()) 
//                    * (Math.sin(beta*conversion))/Math.sin((beta-alpha)*conversion)  ), 2);

            double variacion_pae = pae - pa;

            double m = pa * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) / 3 + variacion_pae * (0.6 * (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())));

            vce.ka1.setText(String.valueOf(ka));

            vce.pae.setText(String.valueOf(pae));
            vce.pa.setText(String.valueOf(pa));
            vce.variacion_pae.setText(String.valueOf(variacion_pae));
            vce.m.setText(String.valueOf(m));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

    //acero corrido
    public void acero_corrido() {
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            switch (vce.varillas1.getSelectedIndex()) {
                case 0:
                    area_steel1 = 0.71;
                    break;
                case 1:
                    area_steel1 = 1.27;
                    break;
                case 2:
                    area_steel1 = 1.98;
                    break;
                case 3:
                    area_steel1 = 2.85;
                    break;
                case 4:
                    area_steel1 = 5.07;
                    break;
                case 5:
                    area_steel1 = 7.92;
                    break;
                case 6:
                    area_steel1 = 11.40;
                    break;
            }
            switch (vce.separacion1.getSelectedIndex()) {
                case 0:
                    separacion1 = 2.5;
                    break;
                case 1:
                    separacion1 = 5.0;
                    break;
                case 2:
                    separacion1 = 7.5;
                    break;
                case 3:
                    separacion1 = 10.0;
                    break;
                case 4:
                    separacion1 = 12.5;
                    break;
                case 5:
                    separacion1 = 15.0;
                    break;
                case 6:
                    separacion1 = 17.5;
                    break;
                case 7:
                    separacion1 = 20.0;
                    break;
                case 8:
                    separacion1 = 22.5;
                    break;
                case 9:
                    separacion1 = 25.0;
                    break;
                case 10:
                    separacion1 = 27.5;
                    break;
                case 11:
                    separacion1 = 30.0;
                    break;
            }

//            double d = 0.275;
//            double b = 1;
//            double m1 = 19.859;
//            double as1 = 0.50671;
//            double fi_f = 0.9;
//            double fy = 4220;
//            double fc = 250;
            System.out.println("separacion1 " + vce.separacion1.getSelectedIndex());
            System.out.println("separacion1 " + separacion1);
            
            double fi_f=Double.parseDouble(vce.fi_f.getText());
            double fy=Double.parseDouble(vce.fy.getText());

            h = (Double.parseDouble(vce.l2.getText()) - (Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) / (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) * Double.parseDouble(vce.h2.getText())) * 100;
            d = redondeo((h - (r)), 2) / 100;
            m1 = redondeo(Double.parseDouble(vce.fy.getText()) / (0.85 * Double.parseDouble(vce.fc.getText())), 2);
            System.out.println("esta carajo es m1 " + m1);
            System.out.println("esta carajo es h " + h);
            System.out.println("esta carajo es d " + d);
            double as1 = (100 / separacion1) * area_steel1;
            as1/=100;
            System.out.println("esta carajo es as1 " + as1);
            System.out.println("esta carajo es as1 " + as1/100);
            System.out.println("esta carajo es as1 " + as1/(100*100));
            fi_mr1 = redondeo(fi_f * ((as1/100) / (b * d) * fy * (1 - ((as1/100) / (b * d)) * m1 * 0.5) * (b * d * d)) ,2);
            vce.as1.setText(String.valueOf(as1*100));
            vce.fi_mr1.setText(String.valueOf(fi_mr1));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

    //bastón 1
    public void baston1() {
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            switch (vce.varillas2.getSelectedIndex()) {
                case 0:
                    area_steel2 = 0.71;
                    break;
                case 1:
                    area_steel2 = 1.27;
                    break;
                case 2:
                    area_steel2 = 1.98;
                    break;
                case 3:
                    area_steel2 = 2.85;
                    break;
                case 4:
                    area_steel2 = 5.07;
                    break;
                case 5:
                    area_steel2 = 7.92;
                    break;
                case 6:
                    area_steel2 = 11.40;
                    break;
            }
            switch (vce.separacion2.getSelectedIndex()) {
                case 0:
                    separacion2 = 2.5;
                    break;
                case 1:
                    separacion2 = 5.0;
                    break;
                case 2:
                    separacion2 = 7.5;
                    break;
                case 3:
                    separacion2 = 10.0;
                    break;
                case 4:
                    separacion2 = 12.5;
                    break;
                case 5:
                    separacion2 = 15.0;
                    break;
                case 6:
                    separacion2 = 17.5;
                    break;
                case 7:
                    separacion2 = 20.0;
                    break;
                case 8:
                    separacion2 = 22.5;
                    break;
                case 9:
                    separacion2 = 25.0;
                    break;
                case 10:
                    separacion2 = 27.5;
                    break;
                case 11:
                    separacion2 = 30.0;
                    break;
            }
            
            double fi_f=Double.parseDouble(vce.fi_f.getText());
            double fy=Double.parseDouble(vce.fy.getText());

            h = (Double.parseDouble(vce.l2.getText()) - (Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) / (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) * Double.parseDouble(vce.h2.getText())) * 100;
            d = redondeo((h - (r)), 2) / 100;
            m1 = redondeo(Double.parseDouble(vce.fy.getText()) / (0.85 * Double.parseDouble(vce.fc.getText())), 2);
            System.out.println("esta carajo es m1 " + m1);
            System.out.println("esta carajo es h " + h);
            System.out.println("esta carajo es d " + d);
            double as2 = (100 / separacion2) * area_steel2;
            as2/=100;
            System.out.println("esta carajo es as2 " + as2);
            System.out.println("esta carajo es as2 " + as2/100);
            System.out.println("esta carajo es as2 " + as2/(100*100));
            fi_mr2 = redondeo(fi_f * ((as2/100) / (b * d) * fy * (1 - ((as2/100) / (b * d)) * m1 * 0.5) * (b * d * d)) ,2);
            vce.as2.setText(String.valueOf(as2*100));
            vce.fi_mr2.setText(String.valueOf(fi_mr2));
            

//            double as2 = (100 / separacion2) * area_steel2;
//            fi_mr2 = Double.parseDouble(vce.fi_f.getText()) * (as2 / (b * d) * Double.parseDouble(vce.fy.getText()) * (1 - (as2 / (b * d)) * m1 * 0.5) * (b * d * d));
//            vce.as2.setText(String.valueOf(as2));
//            vce.fi_mr2.setText(String.valueOf(fi_mr2));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

    //bastón 2
    public void baston2() {
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            switch (vce.varillas3.getSelectedIndex()) {
                case 0:
                    area_steel3 = 0.71;
                    break;
                case 1:
                    area_steel3 = 1.27;
                    break;
                case 2:
                    area_steel3 = 1.98;
                    break;
                case 3:
                    area_steel3 = 2.85;
                    break;
                case 4:
                    area_steel3 = 5.07;
                    break;
                case 5:
                    area_steel3 = 7.92;
                    break;
                case 6:
                    area_steel3 = 11.40;
                    break;
            }
            switch (vce.separacion3.getSelectedIndex()) {
                case 0:
                    separacion3 = 2.5;
                    break;
                case 1:
                    separacion3 = 5.0;
                    break;
                case 2:
                    separacion3 = 7.5;
                    break;
                case 3:
                    separacion3 = 10.0;
                    break;
                case 4:
                    separacion3 = 12.5;
                    break;
                case 5:
                    separacion3 = 15.0;
                    break;
                case 6:
                    separacion3 = 17.5;
                    break;
                case 7:
                    separacion3 = 20.0;
                    break;
                case 8:
                    separacion3 = 22.5;
                    break;
                case 9:
                    separacion3 = 25.0;
                    break;
                case 10:
                    separacion3 = 27.5;
                    break;
                case 11:
                    separacion3 = 30.0;
                    break;
            }
            
            double fi_f=Double.parseDouble(vce.fi_f.getText());
            double fy=Double.parseDouble(vce.fy.getText());
            
            h = (Double.parseDouble(vce.l2.getText()) - (Double.parseDouble(vce.l2.getText()) - Double.parseDouble(vce.a1.getText())) / (Double.parseDouble(vce.h1.getText()) + Double.parseDouble(vce.h2.getText())) * Double.parseDouble(vce.h2.getText())) * 100;
            d = redondeo((h - (r)), 2) / 100;
            m1 = redondeo(Double.parseDouble(vce.fy.getText()) / (0.85 * Double.parseDouble(vce.fc.getText())), 2);
            System.out.println("esta carajo es m1 " + m1);
            System.out.println("esta carajo es h " + h);
            System.out.println("esta carajo es d " + d);
            double as3 = (100 / separacion3) * area_steel3;
            as3/=100;
            System.out.println("esta carajo es as3 " + as3);
            System.out.println("esta carajo es as3 " + as3/100);
            System.out.println("esta carajo es as3 " + as3/(100*100));
            fi_mr3 = redondeo(fi_f * ((as3/100) / (b * d) * fy * (1 - ((as3/100) / (b * d)) * m1 * 0.5) * (b * d * d)) ,2);
            vce.as3.setText(String.valueOf(as3*100));
            vce.fi_mr3.setText(String.valueOf(fi_mr3));
            

//            double as3 = (100 / separacion3) * area_steel3;
//            fi_mr3 = Double.parseDouble(vce.fi_f.getText()) * (as3 / (b * d) * Double.parseDouble(vce.fy.getText()) * (1 - (as3 / (b * d)) * m1 * 0.5) * (b * d * d));
//            vce.as3.setText(String.valueOf(as3));
//            vce.fi_mr3.setText(String.valueOf(fi_mr3));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

    //acero en temperatura
    public void acero_temperatura() {
        double espesor_muro = 30;
        double num_lechos = 2;
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            switch (vce.varillas4.getSelectedIndex()) {
                case 0:
                    area_steel4 = 0.71;
                    break;
                case 1:
                    area_steel4 = 1.27;
                    break;
                case 2:
                    area_steel4 = 1.98;
                    break;
                case 3:
                    area_steel4 = 2.85;
                    break;
                case 4:
                    area_steel4 = 5.07;
                    break;
                case 5:
                    area_steel4 = 7.92;
                    break;
                case 6:
                    area_steel4 = 11.40;
                    break;
            }
            switch (vce.separacion4.getSelectedIndex()) {
                case 0:
                    separacion4 = 2.5;
                    break;
                case 1:
                    separacion4 = 5.0;
                    break;
                case 2:
                    separacion4 = 7.5;
                    break;
                case 3:
                    separacion4 = 10.0;
                    break;
                case 4:
                    separacion4 = 12.5;
                    break;
                case 5:
                    separacion4 = 15.0;
                    break;
                case 6:
                    separacion4 = 17.5;
                    break;
                case 7:
                    separacion4 = 20.0;
                    break;
                case 8:
                    separacion4 = 22.5;
                    break;
                case 9:
                    separacion4 = 25.0;
                    break;
                case 10:
                    separacion4 = 27.5;
                    break;
                case 11:
                    separacion4 = 30.0;
                    break;
            }
            
            //double Ash_min=******************aclarar formulas
            //double As_temp=
            
            double As = area_steel4*(1/separacion4)*100;
            vce.as4.setText(String.valueOf(As));
            
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }
    }

    //revision
    public void revision() {
        llenado_elementos();
        int estado = ChecarErrores.Dobles(elementos);
        if (estado == -20) {
            double sum_fi_mr = fi_mr1 + fi_mr2 + fi_mr3;
            vce.sum_fi_mr.setText(String.valueOf(sum_fi_mr));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

}
