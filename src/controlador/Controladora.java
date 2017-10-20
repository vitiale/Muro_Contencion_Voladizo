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
import static demo.MinMaxCategoryPlotDemo1.createChart;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import visual.Diagrama_cortante;
import visual.Diagrama_momento;
import visual.Ventana_calculo;

/**
 *
 * @author Alba Proyecto
 */
public class Controladora implements KeyListener, FocusListener, ActionListener {

    private Proyecto py = new Proyecto();
    //private ArrayList<String> datos_muro = new ArrayList<>();
    private Ventana_calculo vc;
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
    //temporales para capturar el valor de cada entrada antes de ser modificada
    private String temp_fi1;
    private String temp_fi2;
    private String temp_sc;
    private String temp_gamma1;
    private String temp_gamma2;
    private String temp_c;
    private String temp_qad;
    private String temp_h1;
    private String temp_h2;
    private String temp_d1;
    private String temp_e;
    private String temp_a1;
    private String temp_d2;
    private String temp_l1;
    private String temp_l2;
    private String temp_l3;
    private String temp_alpha;
    private String temp_fc;
    private String temp_fy;
    private String temp_kv;
    private String temp_kh;
    private String temp_beta;
    private String temp_delta;
    private String temp_ld1;
    private String temp_ld2;
    //necesario para graficos
    private static Diagrama_momento dm;
    private static Diagrama_cortante dc;
    double factor = 100.0;
    int aux = (int) factor;
    double[] sum = new double[aux];
    double[] long2 = new double[aux];
    //private Diag diag=new Diag();

    public Controladora(Ventana_calculo vc) {
        this.vc = vc;
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
        vc.mostrar.addKeyListener(this);

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
        vc.mostrar.addActionListener(this);

//        //Aquí podemos cargar la lista elemento y elemento_nombre, así nos ahorramos el tener que borrar e insertar en cada llamada.
//        elementos.add(vc.fi1.getText());
//        elementos_mombre.add("fi1");
//        elementos.add(vc.fi2.getText());
//        elementos_mombre.add("fi2");
//        elementos.add(vc.gamma1.getText());
//        elementos_mombre.add("gamma1");
//        elementos.add(vc.gamma2.getText());
//        elementos_mombre.add("gamma2");
//        
//        elementos.add(vc.ka1.getText());
//        elementos_mombre.add("ka1");
//        elementos.add(vc.kp1.getText());
//        elementos_mombre.add("kp1");
//        elementos.add(vc.sc.getText());
//        elementos_mombre.add("sc");
//        elementos.add(vc.h1.getText());
//        elementos_mombre.add("h1");
//        elementos.add(vc.h2.getText());
//        elementos_mombre.add("h2");
//        
//        elementos.add(vc.qa.getText());
//        elementos_mombre.add("qa");
//        elementos.add(vc.d1.getText());
//        elementos_mombre.add("d1");
//        elementos.add(vc.h5.getText());
//        elementos_mombre.add("h5");
//        elementos.add(vc.h6.getText());
//        elementos_mombre.add("h6");
//        elementos.add(vc.h7.getText());
//        elementos_mombre.add("h7");
//        elementos.add(vc.a1.getText());
//        elementos_mombre.add("a1");        
//        elementos.add(vc.d2.getText());
//        elementos_mombre.add("d2");
//        elementos.add(vc.l1.getText());
//        elementos_mombre.add("l1");
//        elementos.add(vc.l2.getText());
//        elementos_mombre.add("l2");
//        elementos.add(vc.l3.getText());
//        elementos_mombre.add("l3");
//        elementos.add(vc.ld1.getText());
//        elementos_mombre.add("ld1");
//        elementos.add(vc.ld2.getText());
//        elementos_mombre.add("ld2");
//        elementos.add(vc.a6.getText());
//        elementos_mombre.add("a6");
//        
//        elementos.add(vc.fc.getText());
//        elementos_mombre.add("fc");
//        elementos.add(vc.fy.getText());
//        elementos_mombre.add("fy");
//        
//        elementos.add(vc.kh.getText());
//        elementos_mombre.add("kh");
//        elementos.add(vc.kv.getText());
//        elementos_mombre.add("kv");
//        elementos.add(vc.ro.getText());
//        elementos_mombre.add("ro");
//        elementos.add(vc.beta.getText());
//        elementos_mombre.add("beta");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            if (e.getSource() == vc.fi1) {
                vc.gamma1.requestFocus();
            } else if (e.getSource() == vc.gamma1) {
                vc.sc.requestFocus();
            } else if (e.getSource() == vc.sc) {
                vc.fi2.requestFocus();
            } else if (e.getSource() == vc.fi2) {
                vc.gamma2.requestFocus();
            } else if (e.getSource() == vc.gamma2) {
                vc.c.requestFocus();
            } else if (e.getSource() == vc.c) {
                vc.qad.requestFocus();
            } else if (e.getSource() == vc.qad) {
                vc.h1.requestFocus();
            } else if (e.getSource() == vc.h1) {
                vc.h2.requestFocus();
            } else if (e.getSource() == vc.h2) {
                vc.d1.requestFocus();
            } else if (e.getSource() == vc.d1) {
                vc.var_e.requestFocus();
            } else if (e.getSource() == vc.var_e) {
                vc.a1.requestFocus();
            } else if (e.getSource() == vc.a1) {
                vc.d2.requestFocus();
            } else if (e.getSource() == vc.d2) {
                vc.l1.requestFocus();
            } else if (e.getSource() == vc.l1) {
                vc.l2.requestFocus();
            } else if (e.getSource() == vc.l2) {
                vc.l3.requestFocus();
            } else if (e.getSource() == vc.l3) {
                vc.alpha.requestFocus();
            } else if (e.getSource() == vc.alpha) {
                vc.fc.requestFocus();
            } else if (e.getSource() == vc.fc) {
                vc.r.requestFocus();
            } else if (e.getSource() == vc.r) {
                vc.fy.requestFocus();
            } else if (e.getSource() == vc.fy) {
                vc.kv.requestFocus();
            } else if (e.getSource() == vc.kv) {
                vc.kh.requestFocus();
            } else if (e.getSource() == vc.kh) {
                vc.beta.requestFocus();
            } else if (e.getSource() == vc.beta) {
                vc.delta.requestFocus();
            } else if (e.getSource() == vc.delta) {
                vc.varillas1.requestFocus();
            } else if (e.getSource() == vc.varillas1) {
                vc.separacion1.requestFocus();
            } else if (e.getSource() == vc.separacion1) {
                vc.varillas2.requestFocus();
            } else if (e.getSource() == vc.varillas2) {
                vc.separacion2.requestFocus();
            } else if (e.getSource() == vc.separacion2) {
                vc.ld1.requestFocus();
            } else if (e.getSource() == vc.ld1) {
                vc.varillas3.requestFocus();
            } else if (e.getSource() == vc.varillas3) {
                vc.separacion3.requestFocus();
            } else if (e.getSource() == vc.separacion3) {
                vc.ld2.requestFocus();
            } else if (e.getSource() == vc.ld2) {
                vc.varillas4.requestFocus();
            } else if (e.getSource() == vc.varillas4) {
                vc.separacion4.requestFocus();
            } else if (e.getSource() == vc.separacion4) {
                vc.name.requestFocus();
            } else if (e.getSource() == vc.name) {
                vc.aceptar1.requestFocus();
            } else if (e.getSource() == vc.aceptar1) {
                almacenar_muro();
            } else if (e.getSource() == vc.cancelar) {
                cancelar();
            } else if (e.getSource() == vc.eliminar) {
                eliminar();
            } else if (e.getSource() == vc.editar) {
                editar();
            } else if (e.getSource() == vc.cerrar) {
                editar();
            } else if (e.getSource() == vc.guardar) {
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
        if (e.getSource() == vc.fi1) {
            temp_fi1 = vc.fi1.getText();
            vc.fi1.selectAll();
            vc.fi1.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.fi2) {
            temp_fi2 = vc.fi2.getText();
            vc.fi2.selectAll();
            vc.fi2.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.sc) {
            temp_sc = vc.sc.getText();
            vc.sc.selectAll();
            vc.sc.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.gamma1) {
            temp_gamma1 = vc.gamma1.getText();
            vc.gamma1.selectAll();
            vc.gamma1.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.gamma2) {
            temp_gamma2 = vc.gamma2.getText();
            vc.gamma2.selectAll();
            vc.gamma2.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.c) {
            temp_c = vc.c.getText();
            vc.c.selectAll();
            vc.c.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.qad) {
            temp_qad = vc.qad.getText();
            vc.qad.selectAll();
            vc.qad.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.h1) {
            temp_h1 = vc.h1.getText();
            vc.h1.selectAll();
            vc.h1.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.h2) {
            temp_h2 = vc.h2.getText();
            vc.h2.selectAll();
            vc.h2.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.d1) {
            temp_d1 = vc.d1.getText();
            vc.d1.selectAll();
            vc.d1.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.var_e) {
            temp_e = vc.var_e.getText();
            vc.var_e.selectAll();
            vc.var_e.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.alpha) {
            temp_alpha = vc.alpha.getText();
            vc.alpha.selectAll();
            vc.alpha.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.a1) {
            temp_a1 = vc.a1.getText();
            vc.a1.selectAll();
            vc.a1.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.d2) {
            temp_d2 = vc.d2.getText();
            vc.d2.selectAll();
            vc.d2.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.l1) {
            temp_l1 = vc.l1.getText();
            vc.l1.selectAll();
            vc.l1.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.l2) {
            temp_l2 = vc.l2.getText();
            vc.l2.selectAll();
            vc.l2.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.l3) {
            temp_l3 = vc.l3.getText();
            vc.l3.selectAll();
            vc.l3.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.fc) {
            temp_fc = vc.fc.getText();
            vc.fc.selectAll();
            vc.fc.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.fy) {
            temp_fy = vc.fy.getText();
            vc.fy.selectAll();
            vc.fy.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.kv) {
            temp_kv = vc.kv.getText();
            vc.kv.selectAll();
            vc.kv.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.kh) {
            temp_kh = vc.kh.getText();
            vc.kh.selectAll();
            vc.kh.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.beta) {
            temp_beta = vc.beta.getText();
            vc.beta.selectAll();
            vc.beta.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.delta) {
            temp_delta = vc.delta.getText();
            vc.delta.selectAll();
            vc.delta.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.ld1) {
            temp_ld1 = vc.ld1.getText();
            vc.ld1.selectAll();
            vc.ld1.setBackground(Color.WHITE);
        } else if (e.getSource() == vc.ld2) {
            temp_ld2 = vc.ld2.getText();
            vc.ld2.selectAll();
            vc.ld2.setBackground(Color.WHITE);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == vc.fi1) {
            if (ChecarErrores.Dobles_menor_igual(vc.fi1.getText()) == 1) {
                vc.fi1.setText(temp_fi1);
                vc.fi1.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.fi2) {
            if (ChecarErrores.Dobles_menor_igual(vc.fi2.getText()) == 1) {
                vc.fi2.setText(temp_fi2);
                vc.fi2.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.sc) {
            if (ChecarErrores.Dobles_menor_igual(vc.sc.getText()) == 1) {
                vc.sc.setText(temp_sc);
                vc.sc.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.gamma1) {
            if (ChecarErrores.Dobles_menor_igual(vc.gamma1.getText()) == 1) {
                vc.gamma1.setText(temp_gamma1);
                vc.gamma1.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.gamma2) {
            if (ChecarErrores.Dobles_menor_igual(vc.gamma2.getText()) == 1) {
                vc.gamma2.setText(temp_gamma2);
                vc.gamma2.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.c) {
            if (ChecarErrores.Dobles_menor(vc.c.getText()) == 1) {
                vc.c.setText(temp_c);
                vc.c.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.qad) {
            if (ChecarErrores.Dobles_menor_igual(vc.qad.getText()) == 1) {
                vc.qad.setText(temp_qad);
                vc.qad.setBackground(Color.ORANGE);
            } else {
                ejecutor();
                vc.qad_espejo.setText(vc.qad.getText());
            }
        } else if (e.getSource() == vc.h1) {
            if (ChecarErrores.Dobles_menor_igual(vc.h1.getText()) == 1) {
                vc.h1.setText(temp_h1);
                vc.h1.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.h2) {
            if (ChecarErrores.Dobles_menor_igual(vc.h2.getText()) == 1) {
                vc.h2.setText(temp_h2);
                vc.h2.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.d1) {
            if (ChecarErrores.Dobles_menor(vc.d1.getText()) == 1) {
                vc.d1.setText(temp_d1);
                vc.d1.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.var_e) {
            if (ChecarErrores.Dobles_menor_igual(vc.var_e.getText()) == 1) {
                vc.var_e.setText(temp_e);
                vc.var_e.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.a1) {
            if (ChecarErrores.Dobles_menor_igual(vc.a1.getText()) == 1) {
                vc.a1.setText(temp_a1);
                vc.a1.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.d2) {
            if (ChecarErrores.Dobles_menor_igual(vc.d2.getText()) == 1) {
                vc.d2.setText(temp_d2);
                vc.d2.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.l1) {
            if (ChecarErrores.Dobles_menor_igual(vc.l1.getText()) == 1) {
                vc.l1.setText(temp_l1);
                vc.l1.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.l2) {
            if (ChecarErrores.Dobles_menor_igual(vc.l2.getText()) == 1) {
                vc.l2.setText(temp_l2);
                vc.l2.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.l3) {
            if (ChecarErrores.Dobles_menor_igual(vc.l3.getText()) == 1) {
                vc.l3.setText(temp_l3);
                vc.l3.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.alpha) {
            if (ChecarErrores.Dobles_menor(vc.alpha.getText()) == 1) {
                vc.alpha.setText(temp_alpha);
                vc.alpha.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.fc) {
            if (ChecarErrores.Dobles_menor_igual(vc.fc.getText()) == 1) {
                vc.fc.setText(temp_fc);
                vc.fc.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.r) {
            ejecutor();
        } else if (e.getSource() == vc.fy) {
            if (ChecarErrores.Dobles_menor_igual(vc.fy.getText()) == 1) {
                vc.fy.setText(temp_fy);
                vc.fy.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.kv) {
            if (ChecarErrores.Dobles_menor(vc.kv.getText()) == 1) {
                vc.kv.setText(temp_kv);
                vc.kv.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.kh) {
            if (ChecarErrores.Dobles_menor_igual(vc.kh.getText()) == 1) {
                vc.kh.setText(temp_kh);
                vc.kh.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.beta) {
            if (ChecarErrores.Dobles_menor_igual(vc.beta.getText()) == 1) {
                vc.beta.setText(temp_beta);
                vc.beta.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.delta) {
            if (ChecarErrores.Dobles_menor(vc.delta.getText()) == 1) {
                vc.delta.setText(temp_delta);
                vc.delta.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.varillas1) {
            ejecutor();
        } else if (e.getSource() == vc.separacion1) {
            ejecutor();
        } else if (e.getSource() == vc.varillas2) {
            ejecutor();
        } else if (e.getSource() == vc.separacion2) {
            ejecutor();
        } else if (e.getSource() == vc.varillas3) {
            ejecutor();
        } else if (e.getSource() == vc.separacion3) {
            ejecutor();
        } else if (e.getSource() == vc.ld1) {
            if (ChecarErrores.Dobles_menor_igual(vc.ld1.getText()) == 1) {
                vc.ld1.setText(temp_ld1);
                vc.ld1.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.ld2) {
            if (ChecarErrores.Dobles_menor_igual(vc.ld2.getText()) == 1) {
                vc.ld2.setText(temp_ld2);
                vc.ld2.setBackground(Color.ORANGE);
            } else {
                ejecutor();
            }
        } else if (e.getSource() == vc.varillas4) {
            ejecutor();
        } else if (e.getSource() == vc.separacion4) {
            ejecutor();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == vc.aceptar1) {
            almacenar_muro();
        } else if (e.getSource() == vc.cancelar) {
            cancelar();
        } else if (e.getSource() == vc.eliminar) {
            eliminar();
        } else if (e.getSource() == vc.editar) {
            editar();
        } else if (e.getSource() == vc.cerrar) {
            cerrar();
        } else if (e.getSource() == vc.guardar) {
            guardar();
        } else if (e.getSource() == vc.mostrar) {
            mostrar();
        }
    }

//metodos separadas
    public void gps_error(String comp) {
        switch (comp) {
            case "a1":
                vc.a1.setText("0.0");
                vc.a1.setBackground(Color.ORANGE);
                break;
            case "a6":
            case "beta":
                vc.beta.setText("0.0");
                vc.beta.setBackground(Color.ORANGE);
                break;
            case "c":
                vc.c.setText("0.0");
                vc.c.setBackground(Color.ORANGE);
                break;
            case "d1":
                vc.d1.setText("0.0");
                vc.d1.setBackground(Color.ORANGE);
                break;
            case "d2":
                vc.d2.setText("0.0");
                vc.d2.setBackground(Color.ORANGE);
                break;
            case "fc":
                vc.fc.setText("0.0");
                vc.fc.setBackground(Color.ORANGE);
                break;
            case "fi1":
                vc.fi1.setText("0.0");
                vc.fi1.setBackground(Color.ORANGE);
                break;
            case "fi2":
                vc.fi2.setText("0.0");
                vc.fi2.setBackground(Color.ORANGE);
                break;
            case "fs_v":
                vc.fs_v.setText("0.0");
                vc.fs_v.setBackground(Color.ORANGE);
                break;
            case "fy":
                vc.fy.setText("0.0");
                vc.fy.setBackground(Color.ORANGE);
                break;
            case "gamma1":
                vc.gamma1.setText("0.0");
                vc.gamma1.setBackground(Color.ORANGE);
                break;
            case "gamma2":
                vc.gamma2.setText("0.0");
                vc.gamma2.setBackground(Color.ORANGE);
                break;
            case "h1":
                vc.h1.setText("0.0");
                vc.h1.setBackground(Color.ORANGE);
                break;
            case "h2":
                vc.h2.setText("0.0");
                vc.h2.setBackground(Color.ORANGE);
                break;
            case "jTextField22":
                vc.constante1.setText("0.0");
                vc.constante1.setBackground(Color.ORANGE);
                break;
            case "jTextField23":
                vc.constante2.setText("0.0");
                vc.constante2.setBackground(Color.ORANGE);
                break;
            case "jTextField24":
                vc.sum_v.setText("0.0");
                vc.sum_v.setBackground(Color.ORANGE);
                break;
            case "jTextField28":
                vc.alpha.setText("0.0");
                vc.alpha.setBackground(Color.ORANGE);
                break;
            case "jTextField36":
                vc.fs_desliz.setText("0.0");
                vc.fs_desliz.setBackground(Color.ORANGE);
                break;
            case "jTextField37":
                vc.q_min.setText("0.0");
                vc.q_min.setBackground(Color.ORANGE);
                break;
            case "jTextField39":
                vc.qad_espejo.setText("0.0");
                vc.qad_espejo.setBackground(Color.ORANGE);
                break;
            case "jTextField40":
                vc.next_to_qmin.setText("0.0");
                vc.next_to_qmin.setBackground(Color.ORANGE);
                break;
            case "jTextField42":
                vc.as2.setText("0.0");
                vc.as2.setBackground(Color.ORANGE);
                break;
            case "jTextField43":
                vc.fi_f.setText("0.0");
                vc.fi_f.setBackground(Color.ORANGE);
                break;
            case "jTextField44":
                vc.fi_v.setText("0.0");
                vc.fi_v.setBackground(Color.ORANGE);
                break;
            case "jTextField45":
                vc.fi_mr1.setText("0.0");
                vc.fi_mr1.setBackground(Color.ORANGE);
                break;
            case "jTextField46":
                vc.as1.setText("0.0");
                vc.as1.setBackground(Color.ORANGE);
                break;
            case "jTextField48":
                vc.fi_mr2.setText("0.0");
                vc.fi_mr2.setBackground(Color.ORANGE);
                break;
            case "jTextField49":
                vc.ld_propuesto1.setText("0.0");
                vc.ld_propuesto1.setBackground(Color.ORANGE);
                break;
            case "jTextField50":
                vc.ld_propuesto2.setText("0.0");
                vc.ld_propuesto2.setBackground(Color.ORANGE);
                break;
            case "jTextField52":
                vc.as3.setText("0.0");
                vc.as3.setBackground(Color.ORANGE);
                break;
            case "jTextField54":
                vc.beta1.setText("0.0");
                vc.beta1.setBackground(Color.ORANGE);
                break;
            case "jTextField58":
                vc.rho_bal.setText("0.0");
                vc.rho_bal.setBackground(Color.ORANGE);
                break;
            case "jTextField59":
                vc.rho_max.setText("0.0");
                vc.rho_max.setBackground(Color.ORANGE);
                break;
            case "jTextField61":
                vc.pae.setText("0.0");
                vc.pae.setBackground(Color.ORANGE);
                break;
            case "jTextField63":
                vc.pa.setText("0.0");
                vc.pa.setBackground(Color.ORANGE);
                break;
            case "jTextField65":
                vc.variacion_pae.setText("0.0");
                vc.variacion_pae.setBackground(Color.ORANGE);
                break;
            case "jTextField67":
                vc.m.setText("0.0");
                vc.m.setBackground(Color.ORANGE);
                break;
            case "jTextField68":
                vc.sum_fi_mr.setText("0.0");
                vc.sum_fi_mr.setBackground(Color.ORANGE);
                break;
            case "jTextField69":
                vc.m_max.setText("0.0");
                vc.m_max.setBackground(Color.ORANGE);
                break;
            case "jTextField70":
                vc.fi_vc.setText("0.0");
                vc.fi_vc.setBackground(Color.ORANGE);
                break;
            case "jTextField71":
                vc.v_max.setText("0.0");
                vc.v_max.setBackground(Color.ORANGE);
                break;
            case "ka1":
                vc.ka1.setText("0.0");
                vc.ka1.setBackground(Color.ORANGE);
                break;
            case "ka2":
                vc.ka2.setText("0.0");
                vc.ka2.setBackground(Color.ORANGE);
                break;
            case "kh":
                vc.kh.setText("0.0");
                vc.kh.setBackground(Color.ORANGE);
                break;
            case "kp1":
                vc.kp1.setText("0.0");
                vc.kp1.setBackground(Color.ORANGE);
                break;
            case "kp2":
                vc.kp2.setText("0.0");
                vc.kp2.setBackground(Color.ORANGE);
                break;
            case "kv":
                vc.kv.setText("0.0");
                vc.kv.setBackground(Color.ORANGE);
                break;
            case "l1":
                vc.l1.setText("0.0");
                vc.l1.setBackground(Color.ORANGE);
                break;
            case "l2":
                vc.l2.setText("0.0");
                vc.l2.setBackground(Color.ORANGE);
                break;
            case "l3":
                vc.l3.setText("0.0");
                vc.l3.setBackground(Color.ORANGE);
                break;
            case "ld1":
                vc.ld1.setText("0.0");
                vc.ld1.setBackground(Color.ORANGE);
                break;
            case "ld2":
                vc.ld2.setText("0.0");
                vc.ld2.setBackground(Color.ORANGE);
                break;
            case "mpp":
                vc.mpp.setText("0.0");
                vc.mpp.setBackground(Color.ORANGE);
                break;
            case "mwa":
                vc.mwa.setText("0.0");
                vc.mwa.setBackground(Color.ORANGE);
                break;
            case "mwp":
                vc.mwp.setText("0.0");
                vc.mwp.setBackground(Color.ORANGE);
                break;
            case "p_propio":
                vc.p_propio.setText("0.0");
                vc.p_propio.setBackground(Color.ORANGE);
                break;
            case "qa":
                vc.qad.setText("0.0");
                vc.qad.setBackground(Color.ORANGE);
                break;
            case "ro":
                vc.delta.setText("0.0");
                vc.delta.setBackground(Color.ORANGE);
                break;
            case "sc":
                vc.sc.setText("0.0");
                vc.sc.setBackground(Color.ORANGE);
                break;
            case "var_e":
                vc.var_e.setText("0.0");
                vc.var_e.setBackground(Color.ORANGE);
                break;
            case "wa":
                vc.wa.setText("0.0");
                vc.wa.setBackground(Color.ORANGE);
                break;
            case "wp":
                vc.wp.setText("0.0");
                vc.wp.setBackground(Color.ORANGE);
                break;
            case "alpha":
                vc.alpha.setText("0.0");
                vc.alpha.setBackground(Color.ORANGE);
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
        elementos.add(vc.fi1.getText());
        elementos_mombre.add("fi1");
        elementos.add(vc.fi2.getText());
        elementos_mombre.add("fi2");
        elementos.add(vc.gamma1.getText());
        elementos_mombre.add("gamma1");
        elementos.add(vc.gamma2.getText());
        elementos_mombre.add("gamma2");

        elementos.add(vc.ka1.getText());
        elementos_mombre.add("ka1");
        elementos.add(vc.kp1.getText());
        elementos_mombre.add("kp1");
        elementos.add(vc.sc.getText());
        elementos_mombre.add("sc");
        elementos.add(vc.h1.getText());
        elementos_mombre.add("h1");
        elementos.add(vc.h2.getText());
        elementos_mombre.add("h2");

        elementos.add(vc.qad.getText());
        elementos_mombre.add("qa");
        elementos.add(vc.d1.getText());
        elementos_mombre.add("d1");
        elementos_mombre.add("h5");
        elementos_mombre.add("h6");
        elementos_mombre.add("h7");
        elementos.add(vc.a1.getText());
        elementos_mombre.add("a1");
        elementos.add(vc.d2.getText());
        elementos_mombre.add("d2");
        elementos.add(vc.l1.getText());
        elementos_mombre.add("l1");
        elementos.add(vc.l2.getText());
        elementos_mombre.add("l2");
        elementos.add(vc.l3.getText());
        elementos_mombre.add("l3");
        elementos.add(vc.ld1.getText());
        elementos_mombre.add("ld1");
        elementos.add(vc.ld2.getText());
        elementos_mombre.add("ld2");
        elementos_mombre.add("a6");

        elementos.add(vc.fc.getText());
        elementos_mombre.add("fc");
        elementos.add(vc.fy.getText());
        elementos_mombre.add("fy");

        elementos.add(vc.kh.getText());
        elementos_mombre.add("kh");
        elementos.add(vc.kv.getText());
        elementos_mombre.add("kv");
        elementos.add(vc.delta.getText());
        elementos_mombre.add("ro");
        elementos.add(vc.beta.getText());
        elementos_mombre.add("beta");
        elementos.add(vc.c.getText());
        elementos_mombre.add("c");
        elementos.add(vc.var_e.getText());
        elementos_mombre.add("var_e");
        elementos.add(vc.alpha.getText());
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
        if (vc.name.getText() == null || vc.name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe poner un nombre que identifique al muro");
        } else {
            //invento
//            ArrayList<String> data = new ArrayList<>();
//            double altura = (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()));
//            data.add(String.valueOf(altura));
//            double ancho = Double.parseDouble(vc.a1.getText());//puede ser d2
//            data.add(String.valueOf(ancho));
//            double altura_zapata = Double.parseDouble(vc.var_e.getText());
//            data.add(String.valueOf(altura_zapata));
//            double largo_zapata = (Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText()));
//            data.add(String.valueOf(largo_zapata));
//            double bajo_zapata = Double.parseDouble(vc.d1.getText());
//            data.add(String.valueOf(bajo_zapata));
            System.out.println("");
            System.out.println("lo que tiene el combo " + vc.combo_almacen.getComponentCount());
            System.out.println("");

            //datos_muro.clear();
            ArrayList<String> datos_muro = new ArrayList<>();
            datos_muro.add(vc.name.getText());
            datos_muro.add(vc.fi1.getText());
            datos_muro.add(vc.gamma1.getText());
            datos_muro.add(vc.sc.getText());
            datos_muro.add(vc.ka1.getText());
            datos_muro.add(vc.fi2.getText());
            datos_muro.add(vc.gamma2.getText());
            datos_muro.add(vc.c.getText());
            datos_muro.add(vc.qad.getText());
            datos_muro.add(vc.kp2.getText());
            //inicio geometria indice 10
            datos_muro.add(vc.h1.getText());
            datos_muro.add(vc.h2.getText());
            datos_muro.add(vc.d1.getText());
            datos_muro.add(vc.var_e.getText());
            datos_muro.add(vc.a1.getText());
            datos_muro.add(vc.d2.getText());
            datos_muro.add(vc.l1.getText());
            datos_muro.add(vc.l2.getText());
            datos_muro.add(vc.l3.getText());
            //fin geometria indice 19
            datos_muro.add(vc.wp.getText());
            datos_muro.add(vc.wa.getText());
            datos_muro.add(vc.p_propio.getText());
            datos_muro.add(vc.fs_v.getText());
            datos_muro.add(vc.mwp.getText());
            datos_muro.add(vc.mwa.getText());
            datos_muro.add(vc.mpp.getText());
            datos_muro.add(vc.constante1.getText());
            datos_muro.add(vc.alpha.getText());
            datos_muro.add(vc.sum_v.getText());
            datos_muro.add(vc.fs_desliz.getText());
            //30
            datos_muro.add(vc.constante2.getText());
            datos_muro.add(vc.q_max.getText());
            datos_muro.add(vc.q_min.getText());
            datos_muro.add(vc.qad_espejo.getText());
            datos_muro.add(vc.next_to_qmin.getText());
            datos_muro.add(vc.fc.getText());
            //36
            datos_muro.add(String.valueOf(vc.r.getSelectedIndex()));
//            switch (vc.r.getSelectedIndex()) {
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
            datos_muro.add(vc.fy.getText());
            datos_muro.add(vc.beta1.getText());
            datos_muro.add(vc.rho_bal.getText());
            //40
            datos_muro.add(vc.rho_max.getText());
            datos_muro.add(vc.kv.getText());
            datos_muro.add(vc.kh.getText());
            datos_muro.add(vc.beta.getText());
            datos_muro.add(vc.delta.getText());
            datos_muro.add(vc.pae.getText());
            datos_muro.add(vc.pa.getText());
            datos_muro.add(vc.variacion_pae.getText());
            datos_muro.add(vc.m.getText());
            datos_muro.add(String.valueOf(vc.varillas1.getSelectedIndex()));
            //50
            datos_muro.add(vc.fi_f.getText());
            datos_muro.add(vc.fi_v.getText());
            datos_muro.add(String.valueOf(vc.separacion1.getSelectedIndex()));
            datos_muro.add(vc.as1.getText());
            datos_muro.add(vc.fi_mr1.getText());
            datos_muro.add(String.valueOf(vc.varillas2.getSelectedIndex()));
            datos_muro.add(vc.as2.getText());
            datos_muro.add(vc.ld_propuesto1.getText());
            datos_muro.add(vc.fi_mr2.getText());
            datos_muro.add(String.valueOf(vc.separacion2.getSelectedIndex()));
            //60
            datos_muro.add(vc.ld1.getText());
            datos_muro.add(String.valueOf(vc.varillas3.getSelectedIndex()));
            datos_muro.add(vc.as3.getText());
            datos_muro.add(vc.ld_propuesto2.getText());
            datos_muro.add(vc.fi_mr3.getText());
            datos_muro.add(String.valueOf(vc.separacion3.getSelectedIndex()));
            datos_muro.add(vc.ld2.getText());
            datos_muro.add(vc.sum_fi_mr.getText());
            datos_muro.add(vc.m_max.getText());
            datos_muro.add(vc.fi_vc.getText());
            //70
            datos_muro.add(vc.v_max.getText());
            datos_muro.add(String.valueOf(vc.varillas4.getSelectedIndex()));
            datos_muro.add(String.valueOf(vc.separacion4.getSelectedIndex()));
            datos_muro.add(vc.as4.getText());
            Muro mu = new Muro(datos_muro);
            py.añdir_muro(mu);
            vc.combo_almacen.addItem(datos_muro.get(0));
            mu = null;

            System.out.println("");
            System.out.println("lo que tiene el combo " + vc.combo_almacen.getComponentCount());
            System.out.println("");

//            if (py.getLista().size() > 1) {
//                System.out.println("");
//                System.out.println(py.getLista().get(0).getLista_muro().get(0));
//                System.out.println(py.getLista().get(1).getLista_muro().get(0));
//                System.out.println("");
//            }
            for (int i = 0; i < py.getLista().size(); i++) {
                System.out.println(py.getLista().get(i).getLista_muro().get(0));
            }
        }

    }

    public void eliminar() {
        try {
            if (vc.combo_almacen.getItemCount() != 0) {
                py.eliminar_muro(vc.combo_almacen.getSelectedIndex());
                System.out.println(vc.combo_almacen.getSelectedIndex());
                vc.combo_almacen.removeItemAt(vc.combo_almacen.getSelectedIndex());
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
        vc.fi1.setText("30.0");
        vc.fi1.setBackground(Color.WHITE);
        vc.gamma1.setText("1.75");
        vc.gamma1.setBackground(Color.WHITE);
        vc.sc.setText("5.0");
        vc.sc.setBackground(Color.WHITE);
        vc.fi2.setText("30.0");
        vc.fi2.setBackground(Color.WHITE);
        vc.gamma2.setText("1.75");
        vc.gamma2.setBackground(Color.WHITE);
        vc.c.setText("0.0");
        vc.c.setBackground(Color.WHITE);
        vc.qad.setText("20.7");
        vc.qad.setBackground(Color.WHITE);
        vc.h1.setText("3.0");
        vc.h1.setBackground(Color.WHITE);
        vc.h2.setText("0.5");
        vc.h2.setBackground(Color.WHITE);
        vc.d1.setText("0.0");
        vc.d1.setBackground(Color.WHITE);
        vc.var_e.setText("0.1");
        vc.var_e.setBackground(Color.WHITE);
        vc.a1.setText("0.35");
        vc.a1.setBackground(Color.WHITE);
        vc.d2.setText("0.35");
        vc.d2.setBackground(Color.WHITE);
        vc.l1.setText("1.20");
        vc.l1.setBackground(Color.WHITE);
        vc.l2.setText("0.35");
        vc.l2.setBackground(Color.WHITE);
        vc.l3.setText("0.5");
        vc.l3.setBackground(Color.WHITE);
        vc.alpha.setText("0");
        vc.alpha.setBackground(Color.WHITE);
        vc.fc.setText("250");
        vc.fc.setBackground(Color.WHITE);
        vc.r.setSelectedIndex(0);
        vc.fy.setText("4220");
        vc.fy.setBackground(Color.WHITE);
        vc.kv.setText("0.0");
        vc.kv.setBackground(Color.WHITE);
        vc.kh.setText("0.2");
        vc.kh.setBackground(Color.WHITE);
        vc.beta.setText("90");
        vc.beta.setBackground(Color.WHITE);
        vc.delta.setText("0.0");
        vc.delta.setBackground(Color.WHITE);
        vc.varillas1.setSelectedIndex(0);
        vc.separacion1.setSelectedIndex(0);
        vc.varillas2.setSelectedIndex(0);
        vc.separacion2.setSelectedIndex(0);
        vc.varillas3.setSelectedIndex(0);
        vc.ld1.setText("1.0");
        vc.ld1.setBackground(Color.WHITE);
        vc.separacion3.setSelectedIndex(0);
        vc.ld2.setText("1.0");
        vc.ld2.setBackground(Color.WHITE);
        vc.name.setText(null);
        vc.varillas4.setSelectedIndex(0);
        vc.separacion4.setSelectedIndex(0);

        ejecutor();

        //vc.fi1.requestFocus();
    }

    public void editar() {
        System.out.println("");
        System.out.println("combo_almacen tiene " + vc.combo_almacen.getComponentCount());
        System.out.println("");
        if (vc.combo_almacen.getComponentCount() != 0) {
            int estado = JOptionPane.showConfirmDialog(null, "Está seguro que quiere editar?\nTenga en cuenta que si no ha guardado el elemento en el que está trabajando perderá los datos.");
            if (estado == JOptionPane.YES_OPTION) {
                cancelar();
                Muro muro_edit = new Muro(elementos);
                ArrayList<String> lista_edit = py.getLista().get(vc.combo_almacen.getSelectedIndex()).getLista_muro();
                //0-10
                vc.name.setText(lista_edit.get(0));
                vc.fi1.setText(lista_edit.get(1));
                vc.gamma1.setText(lista_edit.get(2));
                vc.sc.setText(lista_edit.get(3));
                vc.ka1.setText(lista_edit.get(4));
                vc.fi2.setText(lista_edit.get(5));
                vc.gamma2.setText(lista_edit.get(6));
                vc.c.setText(lista_edit.get(7));
                vc.qad.setText(lista_edit.get(8));
                vc.kp2.setText(lista_edit.get(9));
                vc.h1.setText(lista_edit.get(10));
                //11-20
                vc.h2.setText(lista_edit.get(11));
                vc.d1.setText(lista_edit.get(12));
                vc.var_e.setText(lista_edit.get(13));
                vc.a1.setText(lista_edit.get(14));
                vc.d2.setText(lista_edit.get(15));
                vc.l1.setText(lista_edit.get(16));
                vc.l2.setText(lista_edit.get(17));
                vc.l3.setText(lista_edit.get(18));
                vc.wp.setText(lista_edit.get(19));
                vc.wa.setText(lista_edit.get(20));
                //21-30
                vc.p_propio.setText(lista_edit.get(21));
                vc.fs_v.setText(lista_edit.get(22));
                vc.mwp.setText(lista_edit.get(23));
                vc.mwa.setText(lista_edit.get(24));
                vc.mpp.setText(lista_edit.get(25));
                vc.constante1.setText(lista_edit.get(26));
                vc.alpha.setText(lista_edit.get(27));
                vc.sum_v.setText(lista_edit.get(28));
                vc.fs_desliz.setText(lista_edit.get(29));
                vc.constante2.setText(lista_edit.get(30));
                //31-40
                vc.q_max.setText(lista_edit.get(31));
                vc.q_min.setText(lista_edit.get(32));
                vc.qad_espejo.setText(lista_edit.get(33));
                vc.next_to_qmin.setText(lista_edit.get(34));
                vc.fc.setText(lista_edit.get(35));
                vc.r.setSelectedIndex(Integer.parseInt(lista_edit.get(36)));
                vc.fy.setText(lista_edit.get(37));
                vc.beta1.setText(lista_edit.get(38));
                vc.rho_bal.setText(lista_edit.get(39));
                vc.rho_max.setText(lista_edit.get(40));
                vc.kv.setText(lista_edit.get(41));
                //42-50
                vc.kh.setText(lista_edit.get(42));
                vc.beta.setText(lista_edit.get(43));
                vc.delta.setText(lista_edit.get(44));
                vc.pae.setText(lista_edit.get(45));
                vc.pa.setText(lista_edit.get(46));
                vc.variacion_pae.setText(lista_edit.get(47));
                vc.m.setText(lista_edit.get(48));
                vc.varillas1.setSelectedIndex(Integer.parseInt(lista_edit.get(49)));
                vc.fi_f.setText(lista_edit.get(50));
                vc.fi_v.setText(lista_edit.get(51));
                //52-70
                vc.separacion1.setSelectedIndex(Integer.parseInt(lista_edit.get(52)));
                vc.as1.setText(lista_edit.get(53));
                vc.fi_mr1.setText(lista_edit.get(54));
                vc.varillas2.setSelectedIndex(Integer.parseInt(lista_edit.get(55)));
                vc.as2.setText(lista_edit.get(56));
                vc.ld_propuesto1.setText(lista_edit.get(57));
                vc.fi_mr2.setText(lista_edit.get(58));
                vc.separacion2.setSelectedIndex(Integer.parseInt(lista_edit.get(59)));
                vc.ld1.setText(lista_edit.get(60));
                vc.varillas3.setSelectedIndex(Integer.parseInt(lista_edit.get(61)));
                vc.as3.setText(lista_edit.get(62));
                vc.ld_propuesto2.setText(lista_edit.get(63));
                vc.fi_mr3.setText(lista_edit.get(64));
                vc.separacion3.setSelectedIndex(Integer.parseInt(lista_edit.get(65)));
                vc.ld2.setText(lista_edit.get(66));
                vc.sum_fi_mr.setText(lista_edit.get(67));
                vc.m_max.setText(lista_edit.get(68));
                vc.fi_vc.setText(lista_edit.get(69));
                vc.v_max.setText(lista_edit.get(70));
                //71-73
                vc.varillas4.setSelectedIndex(Integer.parseInt(lista_edit.get(71)));
                vc.separacion4.setSelectedIndex(Integer.parseInt(lista_edit.get(72)));
                vc.as4.setText(lista_edit.get(73));

                py.eliminar_muro(vc.combo_almacen.getSelectedIndex());
                vc.combo_almacen.removeItemAt(vc.combo_almacen.getSelectedIndex());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tiene elementos para editar");
        }
    }

    public void cerrar() {
        if (vc.combo_almacen.getItemCount() != 0) {
            int estado = JOptionPane.showConfirmDialog(null, "Está seguro que quiere cancelar?\nSi cancela perderá todo el progreso alcanzado.");
            switch (estado) {
                case JOptionPane.YES_OPTION:
                    py = null;
                    vc.dispose();
                    break;
            }
        } else {
            py = null;
            vc.dispose();
        }
    }

    public void mostrar() {
        double h1 = Double.parseDouble(vc.h1.getText());
        double h2 = Double.parseDouble(vc.h2.getText());
        double altura_muro = h1 + h2;
        double P = (sigma_b - sigma_a) / altura_muro;
        double fs = 1.0;
        double fe = 1.6;
        double xs = altura_muro * 0.6;
        double pae = Double.parseDouble(vc.pae.getText());
        double pa = Double.parseDouble(vc.pa.getText());
        //****variacion_pae y Ps del excel son lo mismo
        double variacion_pae = pae - pa;
//        double factor = 100.0;
//        int aux = (int) factor;
        double fijo = altura_muro / factor;
        long2 = new double[aux];
        double[] long1 = new double[aux];
        //**** sigma_a es lo mismo que w1 en el excel 
        double[] w2 = new double[aux];
        double[] m1 = new double[aux];
        double[] m2 = new double[aux];
        double[] mrb = new double[aux];
        double[] ms = new double[aux];
        sum = new double[aux];
        double[] v1 = new double[aux];
        double[] v2 = new double[aux];
        double[] vrb = new double[aux];
        double[] vs = new double[aux];
        double[] sum_mcv = new double[aux];

        double Vmax = Math.abs(((v1[0] + v2[0]) * fe) + (vs[0] * fs) + vrb[0]);

        for (int i = 0; i < long2.length; i++) {
            long2[i] = altura_muro - (fijo * i);
            long1[i] = (fijo * i);
            w2[i] = (P * long1[i]) + sigma_a;
            m1[i] = (sigma_a * long1[i]) * (long1[i] * 0.5);
            m2[i] = ((w2[i] - sigma_a) * long1[i]) / 2 * (1.0 / 3.0) * long1[i];
            mrb[i] = ((long1[i] - altura_muro) > 0) ? -(((sigma_a * Math.pow(altura_muro, 2) * 0.5 * fe) + ((sigma_b - sigma_a) * Math.pow(altura_muro, 2)) * ((1.0 / 6.0) * fe) + (xs * variacion_pae * fs)) / h2) : 0;
            ms[i] = ((long1[i] > (altura_muro - xs)) ? variacion_pae * (long1[i] - (altura_muro - xs)) : 0);
            sum[i] = ((m1[i] + m2[i]) * fe) + (ms[i] * fs) + mrb[i];
            v1[i] = long1[i] * sigma_a;
            v2[i] = (((w2[i] - sigma_a) * long1[i]) / 2);
            vrb[i] = ((long1[i] - h1) > 0) ? -(((sigma_a * Math.pow(altura_muro, 2) * 0.5 * fe) + ((sigma_b - sigma_a) * Math.pow(altura_muro, 2)) * ((1.0 / 6.0) * fe) + (xs * variacion_pae * fs)) / h2) : 0;
            vs[i] = (long1[i] > (altura_muro - xs)) ? variacion_pae : 0;
            sum_mcv[i] = ((v1[i] + v2[i]) * fe) + (vs[i] * fs) + vrb[i];
            if (Vmax < Math.abs(sum_mcv[i])) {
                Vmax = Math.abs(sum_mcv[i]);
            }
        }

//        for (int j = 0; j < long2.length; j++) {
//            System.out.println((j + 1) + "                      " + long1[j] + "                      " + long2[j] + "                      " + sigma_a + "                      " + w2[j] + "                      " + m1[j] + "                      " + m2[j] + "                      " + mrb[j] + "                              " + ms[j] + "                           " + sum[j]);
//            System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//        }
//        System.out.println("");
//        System.out.println("");
//        for (int j = 0; j < v1.length; j++) {
//            System.out.println((j + 1) + "                      " + v1[j] + "                      " + v2[j] + "                      " + vrb[j] + "                      " + vs[j] + "                      " + sum_mcv[j]);
//            System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//        }

        vc.m_max.setText(String.valueOf(sum[sum.length - 1]));
        vc.v_max.setText(String.valueOf(Vmax));
        if (vc.momento.isSelected()) {
            dm = new Diagrama_momento(sum, long2);
            dm.setVisible(true);
        } else {
            dc = new Diagrama_cortante(long2, sum_mcv);
            dc.setVisible(true);
        }

//        dm = new Diagrama_momento(sum, long2);
//        dm.setVisible(true);
    }

    public void guardar() {
        if (vc.combo_almacen.getItemCount() == 0) {
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
                    Py_Serializable ps = new Py_Serializable(py, ruta, vc.getNombre_py());
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
                        py.trazado_muro1(jf.getSelectedFile().getAbsolutePath() + "(" + vc.getNombre_py() + ")");
                        //trazado.fichero_dibujo(py, jf.getSelectedFile().getAbsolutePath() + "(" + vc.getNombre_py() + ")");
                    }
                    //contador_viga = 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ka_kp(int tipo_suelo) {

        double res;
        double res1;
        if (tipo_suelo == 1) {
            res = redondeo(Math.pow(Math.tan((45 * conversion) - Double.parseDouble(vc.fi1.getText()) / 2), 2), 2);
            vc.ka1.setText(String.valueOf(res));
            res1 = Math.pow(Math.tan((45 * conversion) + Double.parseDouble(vc.fi1.getText())), 2);
            vc.kp1.setText(String.valueOf(res1));
        } else {
            res = Math.pow(Math.tan((45 * conversion) - Double.parseDouble(vc.fi2.getText()) / 2), 2);
            vc.ka2.setText(String.valueOf(res));
            res1 = Math.pow(Math.tan((45 * conversion) + Double.parseDouble(vc.fi2.getText())), 2);
            vc.kp2.setText(String.valueOf(res1));
        }

    }

    public void ka_kp() {

        double delta = Double.parseDouble(vc.delta.getText());
        double beta = Double.parseDouble(vc.beta.getText());
        double alpha = Double.parseDouble(vc.alpha.getText());
        double fi1 = Double.parseDouble(vc.fi1.getText());
        theta = Math.atan(Double.parseDouble(vc.kh.getText()) / (1 - Double.parseDouble(vc.kv.getText())));
        double ka;//es el ka de la memoria
        double res1;
        res1 = redondeo(Math.pow(Math.tan((45 + (Double.parseDouble(vc.fi2.getText())) / 2) * conversion), 2), 2);
        vc.kp2.setText(String.valueOf(res1));
        //res = redondeo(Math.pow(Math.tan((45 - Double.parseDouble(vc.fi1.getText()) / 2) * conversion), 2), 2);

        ka = Math.pow(Math.sin((beta + fi1) * conversion), 2)
                / (Math.pow(Math.sin(beta * conversion), 2) * Math.sin((beta - delta) * conversion)
                * (Math.pow(1 + Math.sqrt((Math.sin((delta + fi1) * conversion) * Math.sin((fi1 - alpha) * conversion)) / Math.sin(((beta - delta) * conversion - theta)) * Math.sin((beta + alpha) * conversion)), 2)));

        vc.ka1.setText(String.valueOf(ka));

    }

    public void sigma() {
        double ka1 = Double.parseDouble(vc.ka1.getText());
        double sc = Double.parseDouble(vc.sc.getText());
        double gamma1 = Double.parseDouble(vc.gamma1.getText());
        double gamma2 = Double.parseDouble(vc.gamma2.getText());
        double H = (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()));
        sigma_a = redondeo(ka1 * sc, 2);
        sigma_b = redondeo(ka1 * gamma1 * H + sc * ka1, 2);
        sigma_p = redondeo(Double.parseDouble(vc.kp2.getText()) * gamma2 * Double.parseDouble(vc.h2.getText()), 2);

    }

    //tener en cuenta que estamos tomando el texfield h2 como la altura en los calculos pero hay que revisar que vamos hacer con esta situacion
    public void empuje_activo() {
//        System.out.println("estoy en empuje_activo");
//        llenado_elementos();
//        int estado = ChecarErrores.Dobles(elementos);
//        if (estado == -20) {
        //no se ubica aqui
//            vc.wa.setText(String.valueOf(redondeo((0.5 * Double.parseDouble(vc.ka1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()), 2)) ,2)));
//        } else {
//            gps_error(elementos_mombre.get(estado));
//            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
//        }
    }

    public void empuje_pasivo() {
//        System.out.println("estoy en empuje_pasivo");
//        llenado_elementos();
//        int estado = ChecarErrores.Dobles(elementos);
//        if (estado == -20) {
//            //no se ubica aqui
////           vc.wp.setText(String.valueOf(redondeo(0.5 * Double.parseDouble(vc.kp1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()), 2) + 2 * Double.parseDouble(vc.c.getText())
////                    * Math.sqrt(Double.parseDouble(vc.kp1.getText())) * (Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText())) ,2)));
////            System.out.println("Mi Pp "+(redondeo(0.5 * Double.parseDouble(vc.kp1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()), 2) + 2 * Double.parseDouble(vc.c.getText())
////                    * Math.sqrt(Double.parseDouble(vc.kp1.getText())) * (Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText())) ,2)));
//        } else {
//            elementos_mombre.get(estado);
//            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
//        }
    }

    //no tenemos donde ponerlo en la ventana 
    public void momento_activo_mpa() {
        double ka = Double.parseDouble(vc.ka1.getText());
        double gamma1 = Double.parseDouble(vc.gamma1.getText());
        double H = Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText());
        double sc = Double.parseDouble(vc.c.getText());
        double beta = Double.parseDouble(vc.beta.getText());
        double alpha = Double.parseDouble(vc.alpha.getText());
        double paz = 0.5 * ka * gamma1 * Math.pow(H, 2) + ka * H * sc * (Math.sin(beta * conversion) / (Math.sin((beta + alpha) * conversion)));
        double mpaz = paz * H / 3;
        System.out.println("paz " + paz);
        System.out.println("Mpaz " + mpaz);

        //no se hubica aqui, debe ser en mpa pero nno tenemos esta variable aun creada
        //vc.mwa.setText(String.valueOf(0.5 * Double.parseDouble(vc.ka2.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()), 2) * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) / 3));
        mpa = redondeo((0.5 * Double.parseDouble(vc.ka1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()), 2) * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) / 3), 2);
        System.out.println("mpa " + mpa);

    }

    //no tenemos donde ponerlo en la ventana
    public void momento_pasivo_mpp() {
        System.out.println("estoy en momento_pasivo_mpp");

        //no se hubica aqui, debe ser en mpp pero nno tenemos esta variable aun creada
        vc.mwp.setText(String.valueOf(redondeo(0.5 * Double.parseDouble(vc.kp1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()), 2) + 2 * Double.parseDouble(vc.c.getText())
                * Math.sqrt(Double.parseDouble(vc.kp1.getText())) * (Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()))
                * Double.parseDouble(vc.h2.getText()) / 3, 2)));
        MPp = redondeo((0.5 * Double.parseDouble(vc.kp1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()), 2) + 2 * Double.parseDouble(vc.c.getText())
                * Math.sqrt(Double.parseDouble(vc.kp1.getText())) * (Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()))
                * Double.parseDouble(vc.h2.getText()) / 3), 2);
        System.out.println("MPp " + MPp);

    }

    //Revision por volteo
    public void peso_pasivo_wp() {
        System.out.println("estoy en peso_pasivo_wp");

        vc.wp.setText(String.valueOf(redondeo(Double.parseDouble(vc.l1.getText()) * (Double.parseDouble(vc.h2.getText()) - Double.parseDouble(vc.var_e.getText())) * Double.parseDouble(vc.gamma2.getText()), 2)));
//            System.out.println("Valor esperado de wp");
//            System.out.println((Double.parseDouble(vc.l1.getText()) * (Double.parseDouble(vc.h2.getText()) - Double.parseDouble(vc.var_e.getText())) * Double.parseDouble(vc.gamma1.getText())));

    }

    public void momento_pasivo_mwp() {
        System.out.println("estoy en momento_pasivo_mwp");

        vc.mwp.setText(String.valueOf(redondeo(Double.parseDouble(vc.wp.getText()) * (Double.parseDouble(vc.l1.getText()) / 2), 2)));

    }

    public void peso_activo_wa() {

        vc.wa.setText(String.valueOf(redondeo(Double.parseDouble(vc.l3.getText()) * ((Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) - Double.parseDouble(vc.var_e.getText())) * Double.parseDouble(vc.gamma1.getText()), 2)));

    }

    public void momento_activo_mwa() {
        System.out.println("estoy en momento_activo_mwa");

        vc.mwa.setText(String.valueOf(redondeo(Double.parseDouble(vc.wa.getText()) * (Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText()) / 2), 2)));

    }

    public void sum_w1_w2_w3() {
        System.out.println("estoy en sum_w1_w2_w3");

//            w3 = redondeo((Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText())) * Double.parseDouble(vc.var_e.getText()) * gamma_c ,2);
//            w2 = redondeo(((Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()) - Double.parseDouble(vc.var_e.getText()))) / 2 * Double.parseDouble(vc.var_e.getText()) * gamma_c ,2);
//            w1 = redondeo(Double.parseDouble(vc.a1.getText()) * (Double.parseDouble(vc.h1.getText()) - Double.parseDouble(vc.var_e.getText())) * gamma_c ,2);
//            vc.p_propio.setText(String.valueOf(redondeo(w3 + w2 + w1 ,2)));
        w3 = (Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText())) * Double.parseDouble(vc.var_e.getText()) * gamma_c;
        w2 = ((Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()) - Double.parseDouble(vc.var_e.getText()))) / 2 * Double.parseDouble(vc.var_e.getText()) * gamma_c;
        w1 = Double.parseDouble(vc.a1.getText()) * ((Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()) - Double.parseDouble(vc.var_e.getText()))) * gamma_c;
        vc.p_propio.setText(String.valueOf(redondeo(w3 + w2 + w1, 2)));
        System.out.println("w3 " + w3);
        System.out.println("w2 " + w2);
        System.out.println("w1 " + w1);
        System.out.println("pp " + (w1 + w2 + w3));

    }

//    public void sum_w1_w2_w3() {
//        elementos.clear();
//        elementos.add(vc.l1.getText());
//        elementos.add(vc.l2.getText());
//        elementos.add(vc.l3.getText());
//        elementos.add(vc.h1.getText());
//        elementos.add(vc.h2.getText());
//        elementos.add(vc.var_e.getText());
//        elementos.add(vc.gamma1.getText());
//        elementos.add(vc.a1.getText());
//        if (ChecarErrores.Dobles(elementos) == 0) {
//            double sumWs = (w1 + w2 + w3);
//        } else {
//            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
//        }        
//    }
    public void sum_mw1_mw2_mw3() {
        System.out.println("estoy en sum_mw1_mw2_mw3");

        mw3 = redondeo((w3 * (Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText())) / 2), 2);
        mw2 = redondeo((w2 * (Double.parseDouble(vc.l1.getText()) + (Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) * 2 / 3)), 2);
        mw1 = redondeo((w1 * (Double.parseDouble(vc.l1.getText()) + (Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) + (Double.parseDouble(vc.a1.getText())) / 2)), 2);
        vc.mpp.setText(String.valueOf(redondeo((mw3 + mw2 + mw1), 2)));
        System.out.println("mw3 " + mw3);
        System.out.println("mw2 " + mw2);
        System.out.println("mw1 " + mw1);
        System.out.println("mpp " + (mw1 + mw2 + mw3));

    }

    public void sumatoria_mr() {
        System.out.println("estoy en sumatoria_mr");
        double kp = Double.parseDouble(vc.kp2.getText());
        double gamma2 = Double.parseDouble(vc.gamma2.getText());
        double h2 = Double.parseDouble(vc.h2.getText());
        double d1 = Double.parseDouble(vc.d1.getText());
        double c = Double.parseDouble(vc.c.getText());
        double pp = 0.5 * kp * gamma2 * Math.pow((h2 + d1), 2) + 2 * c * Math.sqrt(kp) * (h2 + d1);
        MPp = pp * h2 / 3.0;
        /*double MPp = redondeo((0.5 * Double.parseDouble(vc.kp1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()), 2) + 2 * Double.parseDouble(vc.c.getText())
                * Math.sqrt(Double.parseDouble(vc.kp1.getText())) * (Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()))
                * Double.parseDouble(vc.h2.getText()) / 3), 2);*/

        sum_mr = (MPp + mw3 + mw2 + mw1 + Double.parseDouble(vc.mwp.getText()) + Double.parseDouble(vc.mwa.getText()));

        System.out.println("pp " + pp);
        System.out.println("MPp " + MPp);
        System.out.println("mw3 " + mw3);
        System.out.println("mw2 " + mw2);
        System.out.println("mw1 " + mw1);
        System.out.println("sum_mr " + sum_mr);

    }

    public void fs_volteo() {
        System.out.println("");
        System.out.println("estoy en fs_volteo");

        double delta = Double.parseDouble(vc.delta.getText());
        double beta = Double.parseDouble(vc.beta.getText());
        double alpha = Double.parseDouble(vc.alpha.getText());
        double fi1 = Double.parseDouble(vc.fi1.getText());

        double h1 = Double.parseDouble(vc.h1.getText());
        double h2 = Double.parseDouble(vc.h2.getText());
        double gamma1 = Double.parseDouble(vc.gamma1.getText());
        double sc = Double.parseDouble(vc.sc.getText());
        double ka = Double.parseDouble(vc.ka1.getText());

        double pa = 0.5 * ka * gamma1 * Math.pow(h1 + h2, 2)/* + ka * (h1 + h2) * sc * Math.sin(beta * conversion) / Math.sin((beta + alpha) * conversion)*/;
        mpa = pa * (h1 + h2) / 3;
        //mpa = redondeo((0.5 * Double.parseDouble(vc.ka1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()), 2) * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) / 3), 2);
        System.out.println("ka " + ka);
        System.out.println("gamma1 " + gamma1);
        System.out.println("Math.pow(h1 + h2, 2) " + Math.pow(h1 + h2, 2));
        System.out.println("(h1 + h2) " + (h1 + h2));
        System.out.println("sc " + sc);
        System.out.println("Math.sin(beta * conversion) " + Math.sin(beta * conversion));
        System.out.println("Math.sin((beta + alpha) * conversion) " + Math.sin((beta + alpha) * conversion));
        System.out.println("Math.sin(beta * conversion) / Math.sin((beta + alpha) * conversion) " + Math.sin(beta * conversion) / Math.sin((beta + alpha) * conversion));
        System.out.println("");
        System.out.println("sum_mr " + sum_mr);
        System.out.println("pa " + pa);
        System.out.println("mpa " + mpa);
        System.out.println("");
        vc.fs_v.setText(String.valueOf(redondeo((sum_mr / mpa), 2)));

        if (redondeo((sum_mr / mpa), 2) >= Double.parseDouble(vc.constante1.getText())) {
            vc.q_max.setBackground(Color.lightGray);
        } else {
            vc.q_max.setBackground(Color.red);
        }

        //System.out.println("mpa " + mpa);
        //System.out.println("fs_volteo " + sum_mr / mpa);
    }

    //modulo revision por desplazamiento
    public void sumatoria_v() {
        System.out.println("estoy en sumatoria_v");

        vc.sum_v.setText(String.valueOf(redondeo(Double.parseDouble(vc.p_propio.getText()) + Double.parseDouble(vc.wp.getText()) + Double.parseDouble(vc.wa.getText()), 2)));

    }

    public void fs_deslizamiento() {
        System.out.println("estoy en fs_deslizamiento");

        double delta = Double.parseDouble(vc.delta.getText());
        double beta = Double.parseDouble(vc.beta.getText());
        double alpha = Double.parseDouble(vc.alpha.getText());
        double fi1 = Double.parseDouble(vc.fi1.getText());

        double h1 = Double.parseDouble(vc.h1.getText());
        double h2 = Double.parseDouble(vc.h2.getText());
        double gamma1 = Double.parseDouble(vc.gamma1.getText());
        double sc = Double.parseDouble(vc.sc.getText());
        double ka = Double.parseDouble(vc.ka1.getText());
        //en la memoria hay dos formulas distintas para pa, aqui se va A aplicar la mas larga para que esto de**************************************************
        //double pa = redondeo((0.5 * Double.parseDouble(vc.ka1.getText()) * Double.parseDouble(vc.gamma2.getText()) * Math.pow(Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()), 2)), 2);
        double pa = 0.5 * ka * gamma1 * Math.pow(h1 + h2, 2)/* + ka * (h1 + h2) * sc * Math.sin(beta * conversion) / Math.sin((beta + alpha) * conversion)*/;
        double Pp = redondeo(0.5 * Double.parseDouble(vc.kp2.getText()) * Double.parseDouble(vc.gamma2.getText()) * Math.pow(Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText()), 2) + 2 * Double.parseDouble(vc.c.getText())
                * Math.sqrt(Double.parseDouble(vc.kp2.getText())) * (Double.parseDouble(vc.h2.getText()) + Double.parseDouble(vc.d1.getText())), 2);

        vc.fs_desliz.setText(String.valueOf(redondeo((((Double.parseDouble(vc.sum_v.getText())) * Math.tan(k1 * Double.parseDouble(vc.fi2.getText()) * conversion) + ((Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText())) * k2 * Double.parseDouble(vc.c.getText()))
                + Pp)
                / (pa * Math.cos(Double.parseDouble(vc.alpha.getText()) * conversion))), 2)));
        System.out.println("");

        if (Double.parseDouble(vc.fs_desliz.getText()) >= Double.parseDouble(vc.constante2.getText())) {
            vc.q_max.setBackground(Color.lightGray);
        } else {
            vc.q_max.setBackground(Color.red);
        }

//        System.out.println("tan que buscamos " + (Math.tan(k1 * Double.parseDouble(vc.fi2.getText()) * conversion)));
//        System.out.println("pa que buscamos " + pa);
//        System.out.println("cociente que buscamos " + (Math.tan(k1 * Double.parseDouble(vc.fi2.getText()) * conversion)));
//        System.out.println("");
    }

    //revision por capacidad
    public void revision_capacidad() {
        System.out.println("estoy en revision_capacidad");
        double delta = Double.parseDouble(vc.delta.getText());
        double beta = Double.parseDouble(vc.beta.getText());
        double alpha = Double.parseDouble(vc.alpha.getText());
        double fi1 = Double.parseDouble(vc.fi1.getText());

        double h1 = Double.parseDouble(vc.h1.getText());
        double h2 = Double.parseDouble(vc.h2.getText());
        double gamma1 = Double.parseDouble(vc.gamma1.getText());
        double sc = Double.parseDouble(vc.sc.getText());
        double ka = Double.parseDouble(vc.ka1.getText());
        double l1 = Double.parseDouble(vc.l1.getText());
        double l2 = Double.parseDouble(vc.l2.getText());
        double l3 = Double.parseDouble(vc.l3.getText());

        double pa = 0.5 * ka * gamma1 * Math.pow(h1 + h2, 2)/* + ka * (h1 + h2) * sc * Math.sin(beta * conversion) / Math.sin((beta + alpha) * conversion)*/;
        mpa = pa * (h1 + h2) / 3;

        //mpa = redondeo((0.5 * Double.parseDouble(vc.ka1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()), 2) * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) / 3), 2);
        //sum_mr = redondeo(MPp + mw3 + mw2 + mw1 + Double.parseDouble(vc.mwp.getText()) + Double.parseDouble(vc.mwa.getText()), 2);
        m_neto = (sum_mr - mpa);
        x = m_neto / Double.parseDouble(vc.sum_v.getText());
        ee = ((l1 + l2 + l3) / 2) - x;
        ma = (Double.parseDouble(vc.sum_v.getText()) * ee);
        A = Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText());
        c = ((Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText())) / 2);
        i = redondeo((Math.pow(Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText()), 3) / 12), 2);
        double q_max = (Double.parseDouble(vc.sum_v.getText()) / A) + (ma * c) / i;
        double q_min = (Double.parseDouble(vc.sum_v.getText()) / A) - (ma * c) / i;
        vc.q_max.setText(String.valueOf(q_max));
        vc.q_min.setText(String.valueOf(q_min));
        //preguntar por la estructura correcta de las comparaciones
        if (q_max <= Double.parseDouble(vc.qad.getText())) {
            vc.q_max.setBackground(Color.lightGray);
        } else {
            vc.q_max.setBackground(Color.red);
        }
        if (q_min >= 0) {
            vc.q_min.setBackground(Color.lightGray);
        } else {
            vc.q_min.setBackground(Color.red);
        }
        //System.out.println("m_neto "+m_neto);
        System.out.println("sum_mr " + sum_mr);
        System.out.println("mpa " + mpa);
        System.out.println("m_neto " + m_neto);
        System.out.println("x " + x);
        System.out.println("ee " + ee);
        System.out.println("ma " + ma);
        System.out.println("a " + A);
        System.out.println("c " + c);
        System.out.println("i " + i);
        System.out.println("q_max " + q_max);
        System.out.println("q_min " + q_min);

    }

    //diseño del muro
    public void diseño_muro() {
        System.out.println("estoy en diseño_muro");
        switch (vc.r.getSelectedIndex()) {
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
            //A = redondeo(Double.parseDouble(vc.l1.getText()) + Double.parseDouble(vc.l2.getText()) + Double.parseDouble(vc.l3.getText()), 2);
            //h = redondeo(Double.parseDouble(vc.l2.getText()) - (Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) / (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) * Double.parseDouble(vc.h2.getText()), 2);
            System.out.println("r " + r);
            h = (Double.parseDouble(vc.l2.getText()) - (Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) / (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) * Double.parseDouble(vc.h2.getText())) * 100;
            m1 = redondeo(Double.parseDouble(vc.fy.getText()) / (0.85 * Double.parseDouble(vc.fc.getText())), 2);
            d = redondeo((h - (r)), 2);
            System.out.println("");
            //System.out.println("a "+A);
            System.out.println("h " + h);
            System.out.println("m1 " + m1);
            System.out.println("d " + d);
            double beta1_tem = redondeo(0.85 - ((0.05 * (Double.parseDouble(vc.fc.getText()) - 280)) / 70), 2);
            if (beta1_tem >= 0.65 && beta1_tem <= 0.85) {
                vc.beta1.setText(String.valueOf(beta1_tem));
            } else if (beta1_tem <= 0.65) {
                vc.beta1.setText("0.65");
            } else if (beta1_tem >= 0.85) {
                vc.beta1.setText("0.85");
            }
            vc.rho_bal.setText(String.valueOf(redondeo((Double.parseDouble(vc.beta1.getText()) / m1) * (es * 0.003 / (Double.parseDouble(vc.fy.getText()) + (es * 0.003))), 2)));
            System.out.println(redondeo((Double.parseDouble(vc.beta1.getText()) / m1) * (es * 0.003 / (Double.parseDouble(vc.fy.getText()) + (es * 0.003))), 2));;
            vc.rho_max.setText(String.valueOf(redondeo(0.75 * Double.parseDouble(vc.rho_bal.getText()), 2)));
        } else {
            gps_error(elementos_mombre.get(estado));
            JOptionPane.showMessageDialog(null, "Verifique por favor, está introduciendo valores incorrectos en la celda Sc o Ka");
        }

    }

    //Empuje activo ****Este es el módulo "Empuje activo" como tal****
    public void empuje_activo_sismico() {

        double delta = Double.parseDouble(vc.delta.getText());
        double beta = Double.parseDouble(vc.beta.getText());
        double alpha = Double.parseDouble(vc.alpha.getText());
        double fi1 = Double.parseDouble(vc.fi1.getText());

        double h1 = Double.parseDouble(vc.h1.getText());
        double h2 = Double.parseDouble(vc.h2.getText());
        double gamma1 = Double.parseDouble(vc.gamma1.getText());
        double sc = Double.parseDouble(vc.sc.getText());

        theta = Math.atan(Double.parseDouble(vc.kh.getText()) / (1 - Double.parseDouble(vc.kv.getText())));

        double kae = Math.pow(Math.sin((fi1 - beta) * conversion - theta), 2)
                / (Math.cos(theta) * Math.pow(Math.sin(beta * conversion), 2) * Math.sin((delta + beta) * conversion + theta)
                * (Math.pow(1 + Math.sqrt((Math.sin((delta + fi1) * conversion) * Math.sin((fi1 * conversion - theta - alpha * conversion))) / Math.sin(((beta - delta) * conversion - theta)) * Math.sin((beta + alpha) * conversion)), 2)));

        double ka = Math.pow(Math.sin((beta + fi1) * conversion), 2)
                / (Math.pow(Math.sin(beta * conversion), 2) * Math.sin((beta - delta) * conversion)
                * (Math.pow(1 + Math.sqrt((Math.sin((delta + fi1) * conversion) * Math.sin((fi1 - alpha) * conversion)) / Math.sin(((beta - delta) * conversion - theta)) * Math.sin((beta + alpha) * conversion)), 2)));

        double pae = 0.5 * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()), 2) * (1 - Double.parseDouble(vc.kv.getText())) * kae;
        //double pae = redondeo(0.5 * gamma1 * Math.pow(h1 + h2, 2) * (1 - kv) * kae, 2);

        double pa = 0.5 * ka * gamma1 * Math.pow(h1 + h2, 2)/* + ka * (h1 + h2) * sc * Math.sin(beta * conversion) / Math.sin((beta + alpha) * conversion)*/;
//            double pa = redondeo((0.5 * Double.parseDouble(vc.ka1.getText()) * Double.parseDouble(vc.gamma1.getText()) * Math.pow(Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText()), 2)
//                    + Double.parseDouble(vc.ka1.getText()) * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) * Double.parseDouble(vc.sc.getText()) 
//                    * (Math.sin(beta*conversion))/Math.sin((beta-alpha)*conversion)  ), 2);

        double variacion_pae = pae - pa;

        double m = pa * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) / 3 + variacion_pae * (0.6 * (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())));

        vc.ka1.setText(String.valueOf(ka));

        vc.pae.setText(String.valueOf(pae));
        vc.pa.setText(String.valueOf(pa));
        vc.variacion_pae.setText(String.valueOf(variacion_pae));
        vc.m.setText(String.valueOf(m));

    }

    //acero corrido
    public void acero_corrido() {

        switch (vc.varillas1.getSelectedIndex()) {
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
        switch (vc.separacion1.getSelectedIndex()) {
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
        System.out.println("separacion1 " + vc.separacion1.getSelectedIndex());
        System.out.println("separacion1 " + separacion1);

        double fi_f = Double.parseDouble(vc.fi_f.getText());
        double fy = Double.parseDouble(vc.fy.getText());

        h = (Double.parseDouble(vc.l2.getText()) - (Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) / (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) * Double.parseDouble(vc.h2.getText())) * 100;
        d = redondeo((h - (r)), 2) / 100;
        m1 = redondeo(Double.parseDouble(vc.fy.getText()) / (0.85 * Double.parseDouble(vc.fc.getText())), 2);
        System.out.println("esta carajo es m1 " + m1);
        System.out.println("esta carajo es h " + h);
        System.out.println("esta carajo es d " + d);
        double as1 = (100 / separacion1) * area_steel1;
        as1 /= 100;
        System.out.println("esta carajo es as1 " + as1);
        System.out.println("esta carajo es as1 " + as1 / 100);
        System.out.println("esta carajo es as1 " + as1 / (100 * 100));
        fi_mr1 = redondeo(fi_f * ((as1 / 100) / (b * d) * fy * (1 - ((as1 / 100) / (b * d)) * m1 * 0.5) * (b * d * d)), 2);
        fi_mr1 *= 10;
        vc.as1.setText(String.valueOf(as1 * 100));
        vc.fi_mr1.setText(String.valueOf(fi_mr1));

    }

    //bastón 1
    public void baston1() {

        switch (vc.varillas2.getSelectedIndex()) {
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
        switch (vc.separacion2.getSelectedIndex()) {
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

        double fi_f = Double.parseDouble(vc.fi_f.getText());
        double fy = Double.parseDouble(vc.fy.getText());

        h = (Double.parseDouble(vc.l2.getText()) - (Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) / (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) * Double.parseDouble(vc.h2.getText())) * 100;
        d = redondeo((h - (r)), 2) / 100;
        m1 = redondeo(Double.parseDouble(vc.fy.getText()) / (0.85 * Double.parseDouble(vc.fc.getText())), 2);
        System.out.println("esta carajo es m1 " + m1);
        System.out.println("esta carajo es h " + h);
        System.out.println("esta carajo es d " + d);
        double as2 = (100 / separacion2) * area_steel2;
        as2 /= 100;
        System.out.println("esta carajo es as2 " + as2);
        System.out.println("esta carajo es as2 " + as2 / 100);
        System.out.println("esta carajo es as2 " + as2 / (100 * 100));
        fi_mr2 = redondeo(fi_f * ((as2 / 100) / (b * d) * fy * (1 - ((as2 / 100) / (b * d)) * m1 * 0.5) * (b * d * d)), 2);
        fi_mr2 *= 10;
        vc.as2.setText(String.valueOf(as2 * 100));
        vc.fi_mr2.setText(String.valueOf(fi_mr2));

//            double as2 = (100 / separacion2) * area_steel2;
//            fi_mr2 = Double.parseDouble(vc.fi_f.getText()) * (as2 / (b * d) * Double.parseDouble(vc.fy.getText()) * (1 - (as2 / (b * d)) * m1 * 0.5) * (b * d * d));
//            vc.as2.setText(String.valueOf(as2));
//            vc.fi_mr2.setText(String.valueOf(fi_mr2));
    }

    //bastón 2
    public void baston2() {

        switch (vc.varillas3.getSelectedIndex()) {
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
        switch (vc.separacion3.getSelectedIndex()) {
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

        double fi_f = Double.parseDouble(vc.fi_f.getText());
        double fy = Double.parseDouble(vc.fy.getText());

        h = (Double.parseDouble(vc.l2.getText()) - (Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) / (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) * Double.parseDouble(vc.h2.getText())) * 100;
        d = redondeo((h - (r)), 2) / 100;
        m1 = redondeo(Double.parseDouble(vc.fy.getText()) / (0.85 * Double.parseDouble(vc.fc.getText())), 2);
        System.out.println("esta carajo es m1 " + m1);
        System.out.println("esta carajo es h " + h);
        System.out.println("esta carajo es d " + d);
        double as3 = (100 / separacion3) * area_steel3;
        as3 /= 100;
        System.out.println("esta carajo es as3 " + as3);
        System.out.println("esta carajo es as3 " + as3 / 100);
        System.out.println("esta carajo es as3 " + as3 / (100 * 100));
        fi_mr3 = redondeo(fi_f * ((as3 / 100) / (b * d) * fy * (1 - ((as3 / 100) / (b * d)) * m1 * 0.5) * (b * d * d)), 2);
        fi_mr3 *= 10;
        vc.as3.setText(String.valueOf(as3 * 100));
        vc.fi_mr3.setText(String.valueOf(fi_mr3));

//            double as3 = (100 / separacion3) * area_steel3;
//            fi_mr3 = Double.parseDouble(vc.fi_f.getText()) * (as3 / (b * d) * Double.parseDouble(vc.fy.getText()) * (1 - (as3 / (b * d)) * m1 * 0.5) * (b * d * d));
//            vc.as3.setText(String.valueOf(as3));
//            vc.fi_mr3.setText(String.valueOf(fi_mr3));
    }

    //acero en temperatura
    public void acero_temperatura() {
        double espesor_muro = 30;
        double num_lechos = 2;

        switch (vc.varillas4.getSelectedIndex()) {
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
        switch (vc.separacion4.getSelectedIndex()) {
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

        double As_h_min = (0.002) * espesor_muro;
        double As_temp = As_h_min / num_lechos;
        As_h_min *= 100;
        As_temp *= 100;
        System.out.println("As_h_min " + As_h_min);
        System.out.println("As_temp " + As_temp);

        double As = area_steel4 * (1 / separacion4) * 100;

        if (As <= As_temp) {
            vc.as4.setBackground(Color.red);
        } else {
            vc.as4.setBackground(Color.lightGray);
        }

        vc.as4.setText(String.valueOf(As));

    }

    //revision
    public void revision() {

        h = (Double.parseDouble(vc.l2.getText()) - (Double.parseDouble(vc.l2.getText()) - Double.parseDouble(vc.a1.getText())) / (Double.parseDouble(vc.h1.getText()) + Double.parseDouble(vc.h2.getText())) * Double.parseDouble(vc.h2.getText())) * 100;
        d = redondeo((h - (r)), 2) / 100;
        double fi_v = Double.parseDouble(vc.fi_v.getText());
        double fi_fc = Double.parseDouble(vc.fc.getText());
        double sum_fi_mr = fi_mr1 + fi_mr2 + fi_mr3;
        double fi_vc = fi_v * 0.53 * Math.sqrt(fi_fc) * b * d;
        fi_vc *= 10;
        vc.fi_vc.setText(String.valueOf(fi_vc));
        System.out.println("fi_vc " + fi_vc);
        vc.sum_fi_mr.setText(String.valueOf(sum_fi_mr));

        //Aquí coenzamos los calculos de momento
        System.out.println("sigma_a " + sigma_a);
        System.out.println("sigma_b " + sigma_b);
        System.out.println("sigma_p " + sigma_p);
        double h1 = Double.parseDouble(vc.h1.getText());
        double h2 = Double.parseDouble(vc.h2.getText());
        double altura_muro = h1 + h2;
        double P = (sigma_b - sigma_a) / altura_muro;
        double fs = 1.0;
        double fe = 1.6;
        double xs = altura_muro * 0.6;
        double pae = Double.parseDouble(vc.pae.getText());
        double pa = Double.parseDouble(vc.pa.getText());
        //****variacion_pae y Ps del excel son lo mismo
        double variacion_pae = pae - pa;
//        double factor = 100.0;
//        int aux = (int) factor;
        double fijo = altura_muro / factor;
        long2 = new double[aux];
        double[] long1 = new double[aux];
        //**** sigma_a es lo mismo que w1 en el excel 
        double[] w2 = new double[aux];
        double[] m1 = new double[aux];
        double[] m2 = new double[aux];
        double[] mrb = new double[aux];
        double[] ms = new double[aux];
        sum = new double[aux];
        double[] v1 = new double[aux];
        double[] v2 = new double[aux];
        double[] vrb = new double[aux];
        double[] vs = new double[aux];
        double[] sum_mcv = new double[aux];

        double Vmax = Math.abs(((v1[0] + v2[0]) * fe) + (vs[0] * fs) + vrb[0]);

        for (int i = 0; i < long2.length; i++) {
            long2[i] = altura_muro - (fijo * i);
            long1[i] = (fijo * i);
            w2[i] = (P * long1[i]) + sigma_a;
            m1[i] = (sigma_a * long1[i]) * (long1[i] * 0.5);
            m2[i] = ((w2[i] - sigma_a) * long1[i]) / 2 * (1.0 / 3.0) * long1[i];
            mrb[i] = ((long1[i] - altura_muro) > 0) ? -(((sigma_a * Math.pow(altura_muro, 2) * 0.5 * fe) + ((sigma_b - sigma_a) * Math.pow(altura_muro, 2)) * ((1.0 / 6.0) * fe) + (xs * variacion_pae * fs)) / h2) : 0;
            ms[i] = ((long1[i] > (altura_muro - xs)) ? variacion_pae * (long1[i] - (altura_muro - xs)) : 0);
            sum[i] = ((m1[i] + m2[i]) * fe) + (ms[i] * fs) + mrb[i];
            v1[i] = long1[i] * sigma_a;
            v2[i] = (((w2[i] - sigma_a) * long1[i]) / 2);
            vrb[i] = ((long1[i] - h1) > 0) ? -(((sigma_a * Math.pow(altura_muro, 2) * 0.5 * fe) + ((sigma_b - sigma_a) * Math.pow(altura_muro, 2)) * ((1.0 / 6.0) * fe) + (xs * variacion_pae * fs)) / h2) : 0;
            vs[i] = (long1[i] > (altura_muro - xs)) ? variacion_pae : 0;
            sum_mcv[i] = ((v1[i] + v2[i]) * fe) + (vs[i] * fs) + vrb[i];
            if (Vmax < Math.abs(sum_mcv[i])) {
                Vmax = Math.abs(sum_mcv[i]);
            }
        }

        vc.m_max.setText(String.valueOf(sum[sum.length - 1]));
        vc.v_max.setText(String.valueOf(Vmax));

        //dm = new Diagrama_momento(sum, long2);
        //dm.setVisible(true);
    }

    public class Diag extends JFrame {

        private double[] eje_x;
        private double[] eje_y;

        /**
         * Creates new form Frame
         */
        public Diag(/*double[] eje_x, double[] eje_y*/) {

            this.eje_x = eje_x;
            this.eje_y = eje_y;

            setBounds(700, 200, 700, 500);
            String SITIO_1 = "PRIMERO";
            String SITIO_2 = "SEGUNDO";

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            XYSeriesCollection xy = new XYSeriesCollection();
            XYSeries serie1 = new XYSeries("Curva de Momentos");
            XYSeries serie2 = new XYSeries("Acero corrido");
            XYSeries serie3 = new XYSeries("Bastón");

            //aqui trazamos la curva
            for (int i = 0; i < sum.length; i++) {
                serie1.add(sum[i], long2[i]);
            }

//        for(int i=0; i<eje_x.length; i++){
//            serie2.add(eje_y[i],eje_x[i]);
//        }
            //aqui trazamos el acero corrido
            serie2.add(4, 0);
            serie2.add(4, 2.9);

            //aqui trazamos baston
            serie3.add(sum[sum.length - 1], 0);
            serie3.add(sum[sum.length - 1], 1.0);

//        serie2.add(5, 1);
//        serie2.add(6, 2);
//        serie2.add(7, 3);
//        serie2.add(8, 4);
            xy.addSeries(serie1);
            xy.addSeries(serie2);
            xy.addSeries(serie3);

            //mi example
            final JFreeChart mi_gr = ChartFactory.createXYLineChart("Diagrama de Momentos", "datos_x", "datos_y", xy);
            //final JFreeChart mi_gr1=ChartFactory.createXYLineChart("Otra", "en_x", "en_y", xy, PlotOrientation.HORIZONTAL, true, true, true);

            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            final ChartPanel chartPanel1 = new ChartPanel(mi_gr);
            //chartPanel.setPreferredSize(new Dimension(500, 270));
            //chartPanel.setEnforceFileExtensions(false);
            chartPanel1.setPreferredSize(new Dimension(500, 270));
            chartPanel1.setEnforceFileExtensions(false);

            //setContentPane(chartPanel);
            setContentPane(chartPanel1);

            setDefaultCloseOperation(EXIT_ON_CLOSE);

        }

    }

}
