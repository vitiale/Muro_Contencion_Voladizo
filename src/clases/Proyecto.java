/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Alba Proyecto
 */
public class Proyecto implements Serializable{

    private String nombre_py;
    private ArrayList<Muro> lista = new ArrayList<>();

//    public Proyecto(ArrayList<Muro> lista) {
//        this.lista = lista;
//    }

    public String getNombre_py() {
        return nombre_py;
    }

    public void setNombre_py(String nombre_py) {
        this.nombre_py = nombre_py;
    }
        
    public ArrayList<Muro> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Muro> lista) {
        this.lista = lista;
    }

    public void dibujo() {
        for (Muro muro : lista) {
            muro.trazado_muro();
        }
    }

    public void eliminar_muro(int number) {
        lista.remove(number);
    }

    public void añdir_muro(Muro m) {
        lista.add(m);
    }

    public void trazado_muro(String dir) {
        double x = 8;//8
        double y = 4;//4dis        
        try {
            FileWriter fichero = new FileWriter(dir+".scr");
            PrintWriter pw = new PrintWriter(fichero);
            //Esto es para crear 
            pw.println("-layer n ALBA_TRABE_D c t 223,255,127 ALBA_TRABE_D l Continuous ALBA_TRABE_D ");
            pw.println("-layer n ALBA_ARMADO c t 76,0,38 ALBA_ARMADO l Continuous ALBA_ARMADO ");
            pw.println("-layer n ALBA_HATCH c t 128,128,128 ALBA_HATCH l Continuous ALBA_HATCH ");
            pw.println("-layer n ALBA_MURO_DE_CARGA_P c t 255,0,255 ALBA_MURO_DE_CARGA_P l Continuous ALBA_MURO_DE_CARGA_P ");
            pw.println("-layer n ALBA_MURO_DE_CONCRETO_D c t 255,0,255 ALBA_MURO_DE_CONCRETO_D l Continuous ALBA_MURO_DE_CONCRETO_D ");
            pw.println("-layer n ALBA_COTAS c t 0,255,0 ALBA_COTAS l Continuous ALBA_COTAS ");
            pw.println("-layer n ALBA_TEXTO_TITULOS c t 255,255,0 ALBA_TEXTO_TITULOS l Continuous ALBA_TEXTO_TITULOS ");
            pw.println("-layer n ALBA_PROYECCION c t 0,255,255 ALBA_PROYECCION l Continuous ALBA_PROYECCION ");
            pw.println("-layer n ALBA_TX2 c t 0,191,255 ALBA_TX2 l Continuous ALBA_TX2 ");
            pw.println("-layer n ALBA_SECCIONES c t 255,0,0 ALBA_SECCIONES l Continuous ALBA_SECCIONES ");
            pw.println("-osnap off");
            for (int j = 0; j < lista.size(); j++) {

                //private int cont;
                String name_muro=lista.get(j).getLista_muro().get(0);
                double scala = 20;
                double altura = ( Double.parseDouble(lista.get(j).getLista_muro().get(11)) + Double.parseDouble(lista.get(j).getLista_muro().get(12)) - Double.parseDouble(lista.get(j).getLista_muro().get(14)))/scala*100;
                double ancho = Double.parseDouble(lista.get(j).getLista_muro().get(15))/scala*100;
                double altura_zapata = Double.parseDouble(lista.get(j).getLista_muro().get(14))/scala*100;
                double largo_zapata = ( Double.parseDouble(lista.get(j).getLista_muro().get(17)) + Double.parseDouble(lista.get(j).getLista_muro().get(18)) + Double.parseDouble(lista.get(j).getLista_muro().get(19)) )/scala*100;
                double bajo_zapata = Double.parseDouble(lista.get(j).getLista_muro().get(13))/scala*100;
                double izq_zapata = Double.parseDouble(lista.get(j).getLista_muro().get(17))/scala*100;
                double drcha_zapata = Double.parseDouble(lista.get(j).getLista_muro().get(19))/scala*100;
                double recubrimiento = Double.parseDouble(lista.get(j).getLista_muro().get(37))/scala*100;
                double zolapamiento = 2.0 / scala*100;
                double num_varilla_temperatura = 3.0;
                double num_varilla_armado_baston = 5.0;
                double num_varilla_armado_corrido = 6.0;
                double diametro_varillas = ((num_varilla_temperatura / 8.0) * 2.54) / scala*100;
                double separacion_varillas_temperatura = 30.0;
                double separacion_varillas_profundidad = 50.0;
                double dist_baston = 100;
                double alto_losa = 15.0 / scala*100;
                double largo_losa = 105.96 / scala*100;
                double dist_losa_zapata = ( Double.parseDouble(lista.get(j).getLista_muro().get(13))-Double.parseDouble(lista.get(j).getLista_muro().get(14)) )/scala*100;
                double dist_losa_fondo = ( Double.parseDouble(lista.get(j).getLista_muro().get(12))+Double.parseDouble(lista.get(j).getLista_muro().get(13)) )/scala*100;
                double fc = Double.parseDouble(lista.get(j).getLista_muro().get(35));;
                double fc_plantilla = 100.0;
                double block_num1 = -2.2365;
                double block_num2 = +0.1635;
                boolean baston = true;

                double pto_medio_ancho_x;
                double pto_medio_ancho_y;
                double pto_medio_zapata_x;
                double pto_medio_zapata_y;
                double cont_pos_varillas_horiz = 0;
                double cont_pos_varillas_vert = 0;
                double diametro_señal_varillas = ((16.0 / 8.0) * 2.54) / scala*100;

                pw.println("zoom window " + ((x - izq_zapata - 1) + "," + (y - 1) + " " + (x + ancho + drcha_zapata + 1) + "," + (y + altura + 1)));
                pw.println("-layer s ALBA_MURO_DE_CONCRETO_D ");
                pw.println("pline " + x + "," + y + " " + (x + ancho) + "," + y + " " + (x + ancho) + "," + (y + bajo_zapata) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " "
                        + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho) + "," + (y + altura) + " " + (x) + "," + (y + altura) + " "
                        + x + "," + (y + bajo_zapata + altura_zapata) + " " + (x - izq_zapata) + "," + (y + bajo_zapata + altura_zapata) + " " + (x - izq_zapata) + "," + (y + bajo_zapata) + " " + x + "," + (y + bajo_zapata) + " " + x + "," + y + " ");

                pw.println("-layer s ALBA_ARMADO ");
                pw.println("pline " + (x - izq_zapata + recubrimiento) + "," + (y + bajo_zapata + recubrimiento + zolapamiento) + " " + (x - izq_zapata + recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " "
                        + (x + drcha_zapata - recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " " + (x + ancho + drcha_zapata - recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " "
                        + (x + ancho + drcha_zapata - recubrimiento) + "," + (y + bajo_zapata + recubrimiento + zolapamiento) + " ");

                pw.println("pline " + (x - izq_zapata + 2 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - zolapamiento) + " " + (x - izq_zapata + 2 * recubrimiento) + "," + (y + bajo_zapata + recubrimiento) + " "
                        + (x + ancho + drcha_zapata - 2 * recubrimiento) + "," + (y + bajo_zapata + recubrimiento) + " " + (x + ancho + drcha_zapata - 2 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - zolapamiento) + " ");

                pw.println("pline " + (x + recubrimiento + zolapamiento) + "," + (y + recubrimiento) + " " + (x + ancho - recubrimiento) + "," + (y + recubrimiento) + " " + (x + ancho - recubrimiento) + "," + (y + altura - recubrimiento / 2) + " "
                        + (x + recubrimiento + zolapamiento) + "," + (y + altura - recubrimiento / 2) + " ");

                pw.println("pline " + (x + ancho - recubrimiento - zolapamiento) + "," + (y + recubrimiento + zolapamiento) + " " + (x + recubrimiento) + "," + (y + recubrimiento + zolapamiento) + " "
                        + (x + recubrimiento) + "," + (y + altura - recubrimiento / 2 - zolapamiento) + " " + (x + ancho - recubrimiento - zolapamiento) + "," + (y + altura - recubrimiento / 2 - zolapamiento) + " ");

                pto_medio_ancho_x = ((x + x + ancho) / 2);
                pto_medio_ancho_y = ((y + y + altura) / 2);
                for (double i = (y + recubrimiento + zolapamiento + diametro_varillas / 2); i < (y + altura - recubrimiento - 2 * zolapamiento); i += separacion_varillas_temperatura) {
                    pw.println("circle " + (x + recubrimiento + diametro_varillas / 2) + "," + (i) + " " + diametro_varillas / 2);
                    pw.println("mirror " + (x + recubrimiento + diametro_varillas) + "," + (i + diametro_varillas / 2) + "  " + (pto_medio_ancho_x) + "," + (pto_medio_ancho_y) + " " + (pto_medio_ancho_x) + "," + (pto_medio_ancho_y + 0.5) + " N  ");
                    cont_pos_varillas_vert = i;
                }
                pto_medio_zapata_x = ((x - izq_zapata) + (x + drcha_zapata)) / 2;
                pto_medio_zapata_y = ((y + bajo_zapata) + (y + bajo_zapata + altura_zapata)) / 2;
                for (double h = (x - izq_zapata + 2 * recubrimiento); h < (x + ancho + drcha_zapata - 2 * recubrimiento); h += separacion_varillas_temperatura) {
                    pw.println("circle " + (h + diametro_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + diametro_varillas / 2);
                    pw.println("mirror " + (h + diametro_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas) + "  " + (pto_medio_zapata_x) + "," + (pto_medio_zapata_y) + " " + (pto_medio_zapata_x + 0.5) + "," + (pto_medio_zapata_y) + " N  ");
                    cont_pos_varillas_horiz = h;
                }
                //baston
                if (baston) {
                    pw.println("pline " + (x + ancho - recubrimiento - diametro_varillas) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho - recubrimiento - diametro_varillas) + "," + (y + bajo_zapata + altura_zapata + dist_baston) + " ");
                }

                pw.println("-layer s 0 ");
                pw.println("pline " + (x) + "," + (y + dist_losa_fondo) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo) + " ");
                pw.println("pline " + (x) + "," + (y + dist_losa_fondo - alto_losa) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo - alto_losa) + " ");

                pw.println("-layer s ALBA_HATCH ");
                pw.println("breakline " + (x - largo_losa) + "," + (y + dist_losa_fondo) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo - alto_losa) + " ");
                pw.println("pline " + (x - izq_zapata) + "," + (y + bajo_zapata) + " " + (x - izq_zapata) + "," + (y + bajo_zapata - recubrimiento) + " " + (x - recubrimiento) + "," + (y + bajo_zapata - recubrimiento) + " " + (x - recubrimiento) + "," + (y - recubrimiento) + " "
                        + (x + ancho + recubrimiento) + "," + (y - recubrimiento) + " " + (x + ancho + recubrimiento) + "," + (y + bajo_zapata - recubrimiento) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata - recubrimiento) + " "
                        + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " ");

                double pasos_altura = ((x + altura) - (x + bajo_zapata + altura_zapata)) / 4;
//            pw.println("spline "+(x+ancho)+","+(y+altura)+" "+(x+ancho+2*ancho)+","+(y+altura)+" "+(x+ancho+2*ancho)+","+(y+altura-altura/2)+" "+(x+ancho+2*ancho)+","+(y+altura-altura/2)+" "
//                    +(x+ancho+2*ancho)+","+(y+dist_losa_fondo)+" "+(x+ancho+drcha_zapata+ancho)+","+(y+dist_losa_fondo)+" "+(x+ancho+drcha_zapata+ancho)+","+(y+bajo_zapata)
//                    +" "+(x+ancho+drcha_zapata+ancho)+","+(y-ancho)+" "+(x+ancho)+","+(y-ancho)+" "+(x-izq_zapata)+","+(y-ancho)+" "+(x-largo_losa)+","+(y+dist_losa_fondo-alto_losa)+"   ");
                pw.println("spline " + (x + ancho) + "," + (y + altura) + " " + (x + ancho + ancho) + "," + (y + altura - pasos_altura) + " " + (x + ancho + ancho) + "," + (y + altura - pasos_altura) + " " + (x + ancho + ancho) + "," + (y + altura - 2 * pasos_altura) + " "
                        + (x + ancho + ancho) + "," + (y + altura - 3 * pasos_altura) + " " + (x + ancho + ancho) + "," + (y + dist_losa_fondo) + " " + (x + ancho + drcha_zapata + ancho) + "," + (y + dist_losa_fondo) + " " + (x + ancho + drcha_zapata + ancho) + "," + (y + bajo_zapata)
                        + " " + (x + ancho + drcha_zapata + ancho) + "," + (y - ancho) + " " + (x + ancho) + "," + (y - ancho) + " " + (x - izq_zapata) + "," + (y - ancho) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo - alto_losa) + "   ");

                pw.println("-hatch p ANSI31 0.8 0 " + (x) + "," + (y - recubrimiento / 2) + " ");
                pw.println("-hatch p EARTH 3.2 0 " + (x) + "," + (y - 2 * recubrimiento) + " ");
                pw.println("erase " + (x + ancho + ancho) + "," + (y + altura - pasos_altura));
                pw.println();
                System.out.println("" + altura);
                pw.println("-layer s ALBA_COTAS ");
                pw.println("dimlinear " + (x + ancho) + "," + (y) + " " + (x + ancho) + "," + (y + altura) + " v t " + Math.round(altura * scala));
                pw.println((x + ancho + drcha_zapata + ancho + 1.5) + "," + (y + 0.2));
                pw.println("dimlinear " + (x) + "," + (y + altura) + " " + (x + ancho) + "," + (y + altura) + " h t " + Math.round(ancho * scala));
                pw.println((x + ancho / 2) + "," + (y + altura + 0.6));
                pw.println("dimlinear " + (x - izq_zapata) + "," + (y + bajo_zapata) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " h t " + Math.round(largo_zapata * scala));
                pw.println((x + largo_zapata / 2) + "," + (y - ancho - 1.5));
                pw.println("dimlinear " + (x) + "," + (y) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo) + " v t " + Math.round(dist_losa_fondo * scala));
                pw.println((x - largo_losa - 1.5) + "," + (y + dist_losa_fondo / 2));
                pw.println("dimlinear " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata + altura_zapata) + " v t " + Math.round(altura_zapata * scala));
                pw.println((x + ancho + drcha_zapata + ancho + 1.0) + "," + (y + 0.2));
                pw.println("dimlinear " + (x + ancho) + "," + (y + bajo_zapata) + " " + (x + ancho) + "," + (y) + " v t " + Math.round(bajo_zapata * scala));
                pw.println((x + ancho + drcha_zapata + ancho + 1.0) + "," + (y + 0.2));
                pw.println("dimlinear " + (x + ancho) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho) + "," + (y + altura) + " v t " + Math.round((altura - (bajo_zapata + altura_zapata)) * scala));
                pw.println((x + ancho + drcha_zapata + ancho + 1.0) + "," + (y + 0.2));
                pw.println("dimlinear " + (x + ancho) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho) + "," + (y + bajo_zapata + altura_zapata + dist_baston) + " v t " + Math.round(dist_baston * scala));
                pw.println((x + 2 * ancho + 1.5) + "," + (y + bajo_zapata + altura_zapata + 0.2));
                pw.println("dimlinear " + (x - izq_zapata) + "," + (y + bajo_zapata) + " " + (x) + "," + (y + bajo_zapata) + " h t " + Math.round(izq_zapata * scala));
                pw.println((x - ancho) + "," + (y - ancho - 1.0));
                pw.println("dimlinear " + (x + ancho) + "," + (y + bajo_zapata) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " h t " + Math.round(drcha_zapata * scala));
                pw.println((x + largo_zapata / 2) + "," + (y - ancho - 1.0));
                pw.println("dimlinear " + (x) + "," + (y + bajo_zapata) + " " + (x + ancho) + "," + (y + bajo_zapata) + " h t " + Math.round(ancho * scala));
                pw.println((x + largo_zapata / 2) + "," + (y - ancho - 1.0));
                pw.println("dimlinear " + (x) + "," + (y + altura - (altura / 5)) + " " + (x + recubrimiento) + "," + (y + altura - (altura / 5)) + " h t " + Math.round(recubrimiento * scala));
                pw.println((x + recubrimiento / 2) + "," + (y + altura - (altura / 5)));
                pw.println("dimlinear " + (x + ancho) + "," + (y + altura - (altura / 7)) + " " + (x + ancho - recubrimiento) + "," + (y + altura - (altura / 7)) + " h t " + Math.round(recubrimiento * scala));
                pw.println((x + ancho + recubrimiento / 2) + "," + (y + altura - (altura / 7)));

                pw.println("-layer s ALBA_TX2 ");
                pw.println("circle " + (x + ancho - recubrimiento - diametro_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + diametro_señal_varillas / 2);
                pw.println("qleader " + (x + ancho - recubrimiento - diametro_varillas / 2 + diametro_señal_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + (x + 2 * ancho) + "," + (cont_pos_varillas_vert) + " " + (x + 2 * ancho + 1) + "," + (cont_pos_varillas_vert) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_temperatura) + "@" + Math.round(separacion_varillas_temperatura * scala));
                pw.println();
                pw.println("circle " + (x + recubrimiento + diametro_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + diametro_señal_varillas / 2);
                pw.println("qleader " + (x + recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + (x - ancho) + "," + (cont_pos_varillas_vert) + " " + (x - 2 * ancho + 1) + "," + (cont_pos_varillas_vert) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_temperatura) + "@" + Math.round(separacion_varillas_temperatura * scala));
                pw.println();
                pw.println("circle " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + diametro_señal_varillas / 2);
                pw.println("qleader " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_temperatura) + "@" + Math.round(separacion_varillas_temperatura * scala));
                pw.println();
                pw.println("circle " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas / 2) + " " + diametro_señal_varillas / 2);
                pw.println("qleader " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_temperatura) + "@" + Math.round(separacion_varillas_temperatura * scala));
                pw.println();

                pw.println("qleader " + (x + ancho - recubrimiento) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + (x + 2 * ancho) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + (x + 2 * ancho + 1) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_armado_corrido) + "@" + Math.round(separacion_varillas_profundidad * scala));
                pw.println();
                pw.println("qleader " + (x + recubrimiento) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + (x - ancho) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + (x - 2 * ancho + 1) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_armado_corrido) + "@" + Math.round(separacion_varillas_profundidad * scala));
                pw.println();

                if (baston) {
                    pw.println("qleader " + (x + ancho - recubrimiento - diametro_varillas) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + (x + ancho - recubrimiento - diametro_varillas + 2 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + (x + ancho - recubrimiento - diametro_varillas + 8 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + 2.0 + " " + "2#" + Math.round(num_varilla_armado_baston) + "@" + Math.round(separacion_varillas_profundidad * scala));
                    pw.println();
                }

                pw.println("qleader " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + recubrimiento) + " " + (x - izq_zapata / 2) + "," + (y + bajo_zapata / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_armado_corrido) + "@" + Math.round(separacion_varillas_profundidad * scala));
                pw.println();
                pw.println("qleader " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + altura_zapata + bajo_zapata / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + altura_zapata + bajo_zapata / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_armado_corrido) + "@" + Math.round(separacion_varillas_profundidad * scala));
                pw.println();

                pw.println("qleader " + (x + ancho + drcha_zapata / 2) + "," + (y + bajo_zapata - recubrimiento) + " " + (x + ancho + drcha_zapata / 2) + "," + (y - ancho) + " " + (x + ancho + drcha_zapata + ancho + 1.0) + "," + (y - ancho) + " " + 4.0 + " " + "PLANTILLA DE CONCRETO\ne = 5 cm\nf'c = " + fc_plantilla);
                pw.println();

                pw.println("-layer s ALBA_TEXTO_TITULOS ");
                pw.println("-text " + (x - izq_zapata) + "," + (y - ancho - 2.5) + " 0.3 0 " + "MURO MCN-1");//el numero es un cosecutivo

                pw.println("-layer s ALBA_TX2 ");
                pw.println("-text " + (x - izq_zapata) + "," + (y - ancho - 2.8) + " 0.2 0 " + "ESC. 1:50.          [cm]");
                pw.println("-text " + (x - izq_zapata) + "," + (y - ancho - 3.2) + " 0.2 0 f'c = " + Math.round(fc) + " kg/cm²");

                //block
                pw.println("pline " + (x - largo_losa / 2) + "," + (y + dist_losa_fondo) + " " + (x - largo_losa / 2 - 0.2) + "," + (y + dist_losa_fondo + 0.3) + " "
                        + (x - largo_losa / 2 + 0.1) + "," + (y + dist_losa_fondo + 0.3) + " " + (x - largo_losa / 2) + "," + (y + dist_losa_fondo + 0.3) + " " + (x - largo_losa / 2) + "," + (y + dist_losa_fondo) + " "
                        + (x - largo_losa / 2 + 0.2) + "," + (y + dist_losa_fondo + 0.3) + " " + (x - largo_losa / 2 + 0.1) + "," + (y + dist_losa_fondo + 0.3) + " ");
                pw.println("-text " + (x - largo_losa / 2 - 0.2) + "," + (y + dist_losa_fondo + 0.4) + " 0.2 0 " + "NTE. " + block_num1 + " m");

                pw.println("pline " + (x + drcha_zapata / 2) + "," + (y + altura) + " " + (x + drcha_zapata / 2 - 0.2) + "," + (y + altura + 0.3) + " "
                        + (x + drcha_zapata / 2 + 0.1) + "," + (y + altura + 0.3) + " " + (x + drcha_zapata / 2) + "," + (y + altura + 0.3) + " " + (x + drcha_zapata / 2) + "," + (y + altura) + " "
                        + (x + drcha_zapata / 2 + 0.2) + "," + (y + altura + 0.3) + " " + (x + drcha_zapata / 2 + 0.1) + "," + (y + altura + 0.3) + " ");
                pw.println("-text " + (x + drcha_zapata / 2 - 0.2) + "," + (y + altura + 0.4) + " 0.2 0 " + "NTE. " + (block_num1) + " m");

                pw.print("-osnap endpoint,midpoint,center,node,quadrant,intersection,extension,perpendicular,tangent,nearest,parallel ");
                
                x+=10;            
                y+=10;            
            }
            
            pw.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }
    
    //public 
    
    public String tipo_separacion(int valor){
        String res="";
        switch(valor){
            case 0: res="2.5";break;
            case 1: res="5";break;
            case 2: res="7.5";break;
            case 3: res="10";break;
            case 4: res="12.5";break;
            case 5: res="15";break;
            case 6: res="17.5";break;
            case 7: res="20";break;
            case 8: res="22.5";break;
            case 9: res="25";break;
            case 10: res="27.5";break;
            case 11: res="30";break;
        }
        return res;
    }
    
    public double separacion_temp(int valor, double scala){
        double res=2.5;
        switch(valor){
            case 0: res=2.5;break;
            case 1: res=5;break;
            case 2: res=7.5;break;
            case 3: res=10;break;
            case 4: res=12.5;break;
            case 5: res=15;break;
            case 6: res=17.5;break;
            case 7: res=20;break;
            case 8: res=22.5;break;
            case 9: res=25;break;
            case 10: res=27.5;break;
            case 11: res=30;break;
        }
        return res/scala;
    }
    
    public String tipo_varillas(int valor){
        String cad="";
        switch(valor){
            case 0: cad="1#3@";break;
            case 1: cad="1#4@";break;
            case 2: cad="1#5@";break;
            case 3: cad="1#6@";break;
            case 4: cad="1#8@";break;
            case 5: cad="1#10@";break;
            case 6: cad="1#12@";break;
        }
        return cad;
    }
    
    public double tipo_diametro_varilla(int valor, double scala){
        double diametro=((3.0 / 8.0) * 2.54) / scala;
        switch(valor){
            case 0: diametro=((3.0 / 8.0) * 2.54);break;
            case 1: diametro=((4.0 / 8.0) * 2.54);break;
            case 2: diametro=((5.0 / 8.0) * 2.54);break;
            case 3: diametro=((6.0 / 8.0) * 2.54);break;
            case 4: diametro=((8.0 / 8.0) * 2.54);break;
            case 5: diametro=((10.0 / 8.0) * 2.54);break;
            case 6: diametro=((12.0 / 8.0) * 2.54);break;
        }
        return diametro/scala;
    }
    
    public void trazado_muro1(String dir) {
        double x = 8;//8
        double y = 4;//4dis        
        try {
            FileWriter fichero = new FileWriter(dir+".scr");
            PrintWriter pw = new PrintWriter(fichero);
            //Esto es para crear 
            pw.println("-layer n ALBA_TRABE_D c t 223,255,127 ALBA_TRABE_D l Continuous ALBA_TRABE_D ");
            pw.println("-layer n ALBA_ARMADO c t 76,0,38 ALBA_ARMADO l Continuous ALBA_ARMADO ");
            pw.println("-layer n ALBA_HATCH c t 128,128,128 ALBA_HATCH l Continuous ALBA_HATCH ");
            pw.println("-layer n ALBA_MURO_DE_CARGA_P c t 255,0,255 ALBA_MURO_DE_CARGA_P l Continuous ALBA_MURO_DE_CARGA_P ");
            pw.println("-layer n ALBA_MURO_DE_CONCRETO_D c t 255,0,255 ALBA_MURO_DE_CONCRETO_D l Continuous ALBA_MURO_DE_CONCRETO_D ");
            pw.println("-layer n ALBA_COTAS c t 0,255,0 ALBA_COTAS l Continuous ALBA_COTAS ");
            pw.println("-layer n ALBA_TEXTO_TITULOS c t 255,255,0 ALBA_TEXTO_TITULOS l Continuous ALBA_TEXTO_TITULOS ");
            pw.println("-layer n ALBA_PROYECCION c t 0,255,255 ALBA_PROYECCION l Continuous ALBA_PROYECCION ");
            pw.println("-layer n ALBA_TX2 c t 0,191,255 ALBA_TX2 l Continuous ALBA_TX2 ");
            pw.println("-layer n ALBA_SECCIONES c t 255,0,0 ALBA_SECCIONES l Continuous ALBA_SECCIONES ");
            pw.println("-osnap off");
            for (int j = 0; j < lista.size(); j++) {
                //private int cont;
                String name_muro=lista.get(j).getLista_muro().get(0);
                double scala = 20;
                double altura = ( Double.parseDouble(lista.get(j).getLista_muro().get(10)) + Double.parseDouble(lista.get(j).getLista_muro().get(11)) + Double.parseDouble(lista.get(j).getLista_muro().get(12)))/scala*100;
                double ancho = Double.parseDouble(lista.get(j).getLista_muro().get(14))/scala*100;
                double altura_zapata = Double.parseDouble(lista.get(j).getLista_muro().get(13))/scala*100;
                double largo_zapata = ( Double.parseDouble(lista.get(j).getLista_muro().get(16)) + Double.parseDouble(lista.get(j).getLista_muro().get(17)) + Double.parseDouble(lista.get(j).getLista_muro().get(18)) )/scala*100;
                double bajo_zapata = Double.parseDouble(lista.get(j).getLista_muro().get(12))/scala*100;
                double izq_zapata = Double.parseDouble(lista.get(j).getLista_muro().get(16))/scala*100;
                double drcha_zapata = Double.parseDouble(lista.get(j).getLista_muro().get(18))/scala*100;
                double recubrimiento=2.5/* = Double.parseDouble(lista.get(j).getLista_muro().get(36))/scala*/;
                switch(Integer.parseInt(lista.get(j).getLista_muro().get(36)) ){
                    case 0: recubrimiento=2.5/scala;break;
                    case 1: recubrimiento=5.0/scala;break;
                    case 2: recubrimiento=7.5/scala;break;
                }
                double zolapamiento = 2.0 / scala;
                //double num_varilla_temperatura = 3.0;
                String num_varilla_temperatura = tipo_varillas(Integer.parseInt(lista.get(j).getLista_muro().get(71)));
                double num_varilla_armado_baston = 5.0;
                String num_varilla_armado_corrido = tipo_varillas(Integer.parseInt(lista.get(j).getLista_muro().get(49)));
                
                boolean baston=true;
                String num_varilla_baston1 = tipo_varillas(Integer.parseInt(lista.get(j).getLista_muro().get(55)));
                String num_varilla_baston2 = tipo_varillas(Integer.parseInt(lista.get(j).getLista_muro().get(61)));
                if(Integer.parseInt(lista.get(j).getLista_muro().get(55))==0){
                    baston=false;
                }else{
                    baston=true;
                }
                String separacion_varillas_profundidad_baston1 = tipo_separacion(Integer.parseInt(lista.get(j).getLista_muro().get(59)));
                String separacion_varillas_profundidad_baston2 = tipo_separacion(Integer.parseInt(lista.get(j).getLista_muro().get(65)));
                
                
                double diametro_varillas_temperatura = tipo_diametro_varilla( Integer.parseInt(lista.get(j).getLista_muro().get(71)), scala );
                double separacion_varillas_temperatura = separacion_temp(Integer.parseInt(lista.get(j).getLista_muro().get(72)), scala);//30.0/scala;
                String separacion_varillas_profundidad = tipo_separacion(Integer.parseInt(lista.get(j).getLista_muro().get(52)));
                
                //double dist_baston = 100/scala;
                double dist_baston = Double.parseDouble(lista.get(j).getLista_muro().get(60))/scala;
                double alto_losa = 15.0 / scala;
                double largo_losa;
                if(izq_zapata<=0)
                    largo_losa = 105.96 / scala;
                else
                    largo_losa = (izq_zapata+0.5);
                System.out.println("largo de los "+largo_losa);
                //double dist_losa_zapata = ( Double.parseDouble(lista.get(j).getLista_muro().get(14))-Double.parseDouble(lista.get(j).getLista_muro().get(15)) )/scala*100;
                double dist_losa_fondo = ( Double.parseDouble(lista.get(j).getLista_muro().get(11))+Double.parseDouble(lista.get(j).getLista_muro().get(12)) )/scala*100;
                double fc = Double.parseDouble(lista.get(j).getLista_muro().get(35));
                double fc_plantilla = 100.0;
                double block_num1 = -2.2365;
                double block_num2 = +0.1635;
                

                double pto_medio_ancho_x;
                double pto_medio_ancho_y;
                double pto_medio_zapata_x;
                double pto_medio_zapata_y;
                double cont_pos_varillas_horiz = 0;
                double cont_pos_varillas_vert = 0;
                double diametro_señal_varillas = ((16.0 / 8.0) * 2.54) / scala;

                pw.println("zoom window " + ((x - izq_zapata - 1) + "," + (y - 1) + " " + (x + ancho + drcha_zapata + 1) + "," + (y + altura + 1)));
                pw.println("-layer s ALBA_MURO_DE_CONCRETO_D ");
                pw.println("pline " + x + "," + y + " " + (x + ancho) + "," + y + " " + (x + ancho) + "," + (y + bajo_zapata) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " "
                        + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho) + "," + (y + altura) + " " + (x) + "," + (y + altura) + " "
                        + x + "," + (y + bajo_zapata + altura_zapata) + " " + (x - izq_zapata) + "," + (y + bajo_zapata + altura_zapata) + " " + (x - izq_zapata) + "," + (y + bajo_zapata) + " " + x + "," + (y + bajo_zapata) + " " + x + "," + y + " ");

                pw.println("-layer s ALBA_ARMADO ");
                pw.println("pline " + (x - izq_zapata + recubrimiento) + "," + (y + bajo_zapata + recubrimiento + zolapamiento) + " " + (x - izq_zapata + recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " "
                        + (x + drcha_zapata - recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " " + (x + ancho + drcha_zapata - recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " "
                        + (x + ancho + drcha_zapata - recubrimiento) + "," + (y + bajo_zapata + recubrimiento + zolapamiento) + " ");

                pw.println("pline " + (x - izq_zapata + 2 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - zolapamiento) + " " + (x - izq_zapata + 2 * recubrimiento) + "," + (y + bajo_zapata + recubrimiento) + " "
                        + (x + ancho + drcha_zapata - 2 * recubrimiento) + "," + (y + bajo_zapata + recubrimiento) + " " + (x + ancho + drcha_zapata - 2 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - zolapamiento) + " ");

                pw.println("pline " + (x + recubrimiento + zolapamiento) + "," + (y + recubrimiento) + " " + (x + ancho - recubrimiento) + "," + (y + recubrimiento) + " " + (x + ancho - recubrimiento) + "," + (y + altura - recubrimiento / 2) + " "
                        + (x + recubrimiento + zolapamiento) + "," + (y + altura - recubrimiento / 2) + " ");

                pw.println("pline " + (x + ancho - recubrimiento - zolapamiento) + "," + (y + recubrimiento + zolapamiento) + " " + (x + recubrimiento) + "," + (y + recubrimiento + zolapamiento) + " "
                        + (x + recubrimiento) + "," + (y + altura - recubrimiento / 2 - zolapamiento) + " " + (x + ancho - recubrimiento - zolapamiento) + "," + (y + altura - recubrimiento / 2 - zolapamiento) + " ");

                pto_medio_ancho_x = ((x + x + ancho) / 2);
                pto_medio_ancho_y = ((y + y + altura) / 2);
                for (double i = (y + recubrimiento + zolapamiento + diametro_varillas_temperatura / 2); i < (y + altura - recubrimiento - 2 * zolapamiento); i += separacion_varillas_temperatura) {
                    pw.println("circle " + (x + recubrimiento + diametro_varillas_temperatura / 2) + "," + (i) + " " + diametro_varillas_temperatura / 2);
                    pw.println("mirror " + (x + recubrimiento + diametro_varillas_temperatura) + "," + (i + diametro_varillas_temperatura / 2) + "  " + (pto_medio_ancho_x) + "," + (pto_medio_ancho_y) + " " + (pto_medio_ancho_x) + "," + (pto_medio_ancho_y + 0.5) + " N  ");
                    cont_pos_varillas_vert = i;
                }
                pto_medio_zapata_x = ((x - izq_zapata) + (x + drcha_zapata)) / 2;
                pto_medio_zapata_y = ((y + bajo_zapata) + (y + bajo_zapata + altura_zapata)) / 2;
                for (double h = (x - izq_zapata + 2 * recubrimiento); h < (x + ancho + drcha_zapata - 2 * recubrimiento); h += separacion_varillas_temperatura) {
                    pw.println("circle " + (h + diametro_varillas_temperatura / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas_temperatura / 2) + " " + diametro_varillas_temperatura / 2);
                    pw.println("mirror " + (h + diametro_varillas_temperatura / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas_temperatura) + "  " + (pto_medio_zapata_x) + "," + (pto_medio_zapata_y) + " " + (pto_medio_zapata_x + 0.5) + "," + (pto_medio_zapata_y) + " N  ");
                    cont_pos_varillas_horiz = h;
                }
                //baston
                if (baston) {
                    pw.println("pline " + (x + ancho - recubrimiento - diametro_varillas_temperatura) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho - recubrimiento - diametro_varillas_temperatura) + "," + (y + bajo_zapata + altura_zapata + dist_baston) + " ");
                }

                pw.println("-layer s 0 ");
                //losa
                pw.println("pline " + (x) + "," + (y + dist_losa_fondo) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo) + " ");
                pw.println("pline " + (x) + "," + (y + dist_losa_fondo - alto_losa) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo - alto_losa) + " ");

                pw.println("-layer s ALBA_HATCH ");
                pw.println("breakline " + (x - largo_losa) + "," + (y + dist_losa_fondo) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo - alto_losa) + " ");
                pw.println("pline " + (x - izq_zapata) + "," + (y + bajo_zapata) + " " + (x - izq_zapata) + "," + (y + bajo_zapata - recubrimiento) + " " + (x - recubrimiento) + "," + (y + bajo_zapata - recubrimiento) + " " + (x - recubrimiento) + "," + (y - recubrimiento) + " "
                        + (x + ancho + recubrimiento) + "," + (y - recubrimiento) + " " + (x + ancho + recubrimiento) + "," + (y + bajo_zapata - recubrimiento) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata - recubrimiento) + " "
                        + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " ");

                double pasos_altura = ((x + altura) - (x + bajo_zapata + altura_zapata)) / 4;
//            pw.println("spline "+(x+ancho)+","+(y+altura)+" "+(x+ancho+2*ancho)+","+(y+altura)+" "+(x+ancho+2*ancho)+","+(y+altura-altura/2)+" "+(x+ancho+2*ancho)+","+(y+altura-altura/2)+" "
//                    +(x+ancho+2*ancho)+","+(y+dist_losa_fondo)+" "+(x+ancho+drcha_zapata+ancho)+","+(y+dist_losa_fondo)+" "+(x+ancho+drcha_zapata+ancho)+","+(y+bajo_zapata)
//                    +" "+(x+ancho+drcha_zapata+ancho)+","+(y-ancho)+" "+(x+ancho)+","+(y-ancho)+" "+(x-izq_zapata)+","+(y-ancho)+" "+(x-largo_losa)+","+(y+dist_losa_fondo-alto_losa)+"   ");
                pw.println("spline " + (x + ancho) + "," + (y + altura) + " " + (x + ancho + ancho) + "," + (y + altura - pasos_altura) + " " + (x + ancho + ancho) + "," + (y + altura - pasos_altura) + " " + (x + ancho + ancho) + "," + (y + altura - 2 * pasos_altura) + " "
                        + (x + ancho + ancho) + "," + (y + altura - 3 * pasos_altura) + " " + (x + ancho + ancho) + "," + (y + dist_losa_fondo) + " " + (x + ancho + drcha_zapata + ancho) + "," + (y + dist_losa_fondo) + " " + (x + ancho + drcha_zapata + ancho) + "," + (y + bajo_zapata)
                        + " " + (x + ancho + drcha_zapata + ancho) + "," + (y - ancho) + " " + (x + ancho) + "," + (y - ancho) + " " + (x - izq_zapata) + "," + (y - ancho) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo - alto_losa) + "   ");

                pw.println("-hatch p ANSI31 0.8 0 " + (x) + "," + (y - recubrimiento / 2) + " ");
                pw.println("-hatch p EARTH 3.2 0 " + (x) + "," + (y - 2 * recubrimiento) + " ");
                pw.println("erase " + (x + ancho + ancho) + "," + (y + altura - pasos_altura));
                pw.println();
                System.out.println("" + altura);
                pw.println("-layer s ALBA_COTAS ");
                //escala de altura completa
                pw.println("dimlinear " + (x + ancho) + "," + (y) + " " + (x + ancho) + "," + (y + altura) + " v t " + Math.round(altura * scala));
                pw.println((x + ancho + drcha_zapata + ancho + 1.5) + "," + (y + 0.2));
                //escala ancho la de arriba
                pw.println("dimlinear " + (x) + "," + (y + altura) + " " + (x + ancho) + "," + (y + altura) + " h t " + Math.round(ancho * scala));
                pw.println((x + ancho / 2) + "," + (y + altura + 0.6));
                //escala 
                pw.println("dimlinear " + (x - izq_zapata) + "," + (y + bajo_zapata) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " h t " + Math.round(largo_zapata * scala));
                pw.println((x + largo_zapata / 2) + "," + (y - ancho - 1.5));
                pw.println("dimlinear " + (x) + "," + (y) + " " + (x - largo_losa) + "," + (y + dist_losa_fondo) + " v t " + Math.round(dist_losa_fondo * scala));
                pw.println((x - largo_losa - 1.5) + "," + (y + dist_losa_fondo / 2));
                pw.println("dimlinear " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata + altura_zapata) + " v t " + Math.round(altura_zapata * scala));
                pw.println((x + ancho + drcha_zapata + ancho + 1.0) + "," + (y + 0.2));
                pw.println("dimlinear " + (x + ancho) + "," + (y + bajo_zapata) + " " + (x + ancho) + "," + (y) + " v t " + Math.round(bajo_zapata * scala));
                pw.println((x + ancho + drcha_zapata + ancho + 1.0) + "," + (y + 0.2));
                pw.println("dimlinear " + (x + ancho) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho) + "," + (y + altura) + " v t " + Math.round((altura - (bajo_zapata + altura_zapata)) * scala));
                pw.println((x + ancho + drcha_zapata + ancho + 1.0) + "," + (y + 0.2));
                //cota de baston
                if(baston){
                    pw.println("dimlinear " + (x + ancho) + "," + (y + bajo_zapata + altura_zapata) + " " + (x + ancho) + "," + (y + bajo_zapata + altura_zapata + dist_baston) + " v t " + Math.round(dist_baston * scala));
                pw.println((x + 2 * ancho + 1.5) + "," + (y + bajo_zapata + altura_zapata + 0.2));
                }
                
                pw.println("dimlinear " + (x - izq_zapata) + "," + (y + bajo_zapata) + " " + (x) + "," + (y + bajo_zapata) + " h t " + Math.round(izq_zapata * scala));
                pw.println((x - ancho) + "," + (y - ancho - 1.0));
                pw.println("dimlinear " + (x + ancho) + "," + (y + bajo_zapata) + " " + (x + ancho + drcha_zapata) + "," + (y + bajo_zapata) + " h t " + Math.round(drcha_zapata * scala));
                pw.println((x + largo_zapata / 2) + "," + (y - ancho - 1.0));
                pw.println("dimlinear " + (x) + "," + (y + bajo_zapata) + " " + (x + ancho) + "," + (y + bajo_zapata) + " h t " + Math.round(ancho * scala));
                pw.println((x + largo_zapata / 2) + "," + (y - ancho - 1.0));
                pw.println("dimlinear " + (x) + "," + (y + altura - (altura / 5)) + " " + (x + recubrimiento) + "," + (y + altura - (altura / 5)) + " h t " + (recubrimiento * scala));
                pw.println((x + recubrimiento / 2) + "," + (y + altura - (altura / 5)));
                pw.println("dimlinear " + (x + ancho) + "," + (y + altura - (altura / 7)) + " " + (x + ancho - recubrimiento) + "," + (y + altura - (altura / 7)) + " h t " + (recubrimiento * scala));
                pw.println((x + ancho + recubrimiento / 2) + "," + (y + altura - (altura / 7)));
                
                //escala en temperatura
                pw.println("-layer s ALBA_TX2 ");
                pw.println("circle " + (x + ancho - recubrimiento - diametro_varillas_temperatura / 2) + "," + (cont_pos_varillas_vert) + " " + diametro_señal_varillas / 2);
                pw.println("qleader " + (x + ancho - recubrimiento - diametro_varillas_temperatura / 2 + diametro_señal_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + (x + 2 * ancho) + "," + (cont_pos_varillas_vert) + " " + (x + 2 * ancho + 1) + "," + (cont_pos_varillas_vert) + " " + 2.0 + " " + num_varilla_temperatura + (separacion_varillas_temperatura*scala));
                pw.println();
                pw.println("circle " + (x + recubrimiento + diametro_varillas_temperatura / 2) + "," + (cont_pos_varillas_vert) + " " + diametro_señal_varillas / 2);
                pw.println("qleader " + (x + recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + (x - ancho) + "," + (cont_pos_varillas_vert) + " " + (x - 2 * ancho + 1) + "," + (cont_pos_varillas_vert) + " " + 2.0 + " " + num_varilla_temperatura + (separacion_varillas_temperatura*scala));
                pw.println();
                pw.println("circle " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas_temperatura / 2) + " " + diametro_señal_varillas / 2);
                pw.println("qleader " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas_temperatura / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2 - ancho) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas_temperatura / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas_temperatura / 2) + " " + 2.0 + " " + num_varilla_temperatura + (separacion_varillas_temperatura*scala));
                pw.println();
                pw.println("circle " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas_temperatura / 2) + " " + diametro_señal_varillas / 2);
                pw.println("qleader " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas_temperatura / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2 - ancho) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas_temperatura / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas_temperatura / 2) + " " + 2.0 + " " + num_varilla_temperatura + (separacion_varillas_temperatura*scala));
                pw.println();
                
                //cotas de armado corrido en muro
                pw.println("qleader " + (x + ancho - recubrimiento) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + (x + 2 * ancho) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + (x + 2 * ancho + 1) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + 2.0 + " " + num_varilla_armado_corrido + separacion_varillas_profundidad);
                pw.println();
                pw.println("qleader " + (x + recubrimiento) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + (x - ancho) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + (x - 2 * ancho + 1) + "," + (cont_pos_varillas_vert - separacion_varillas_temperatura / 2) + " " + 2.0 + " " + num_varilla_armado_corrido + separacion_varillas_profundidad);
                pw.println();
                if (baston) {
                    pw.println("qleader " + (x + ancho - recubrimiento - diametro_varillas_temperatura) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + (x + ancho - recubrimiento - diametro_varillas_temperatura + 2 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + (x + ancho - recubrimiento - diametro_varillas_temperatura + 8 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + 2.0 + " " + num_varilla_baston1 + separacion_varillas_profundidad_baston1);
                    pw.println();
                }
                pw.println("qleader " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + recubrimiento) + " " + (x - izq_zapata / 2) + "," + (y + bajo_zapata / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata / 2) + " " + 2.0 + " " +num_varilla_armado_corrido + separacion_varillas_profundidad);
                pw.println();
                pw.println("qleader " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + altura_zapata + bajo_zapata / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas_temperatura / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + altura_zapata + bajo_zapata / 2) + " " + 2.0 + " " + num_varilla_armado_corrido + separacion_varillas_profundidad);
                pw.println();

                pw.println("qleader " + (x + ancho + drcha_zapata / 2) + "," + (y + bajo_zapata - recubrimiento) + " " + (x + ancho + drcha_zapata / 2) + "," + (y - ancho) + " " + (x + ancho + drcha_zapata + ancho + 1.0) + "," + (y - ancho) + " " + 4.0 + " " + "PLANTILLA DE CONCRETO\ne = 5 cm\nf'c = " + fc_plantilla);
                pw.println();

                pw.println("-layer s ALBA_TEXTO_TITULOS ");
                pw.println("-text " + (x - izq_zapata) + "," + (y - ancho - 2.5) + " 0.3 0 " + "MURO MCN-"+(j+1)+" ("+name_muro+")");//el numero es un consecutivo

                pw.println("-layer s ALBA_TX2 ");
                pw.println("-text " + (x - izq_zapata) + "," + (y - ancho - 2.8) + " 0.2 0 " + "ESC. 1:20.          [cm]");
                pw.println("-text " + (x - izq_zapata) + "," + (y - ancho - 3.2) + " 0.2 0 f'c = " + Math.round(fc) + " kg/cm²");

                //block*****************************
//                pw.println("pline " + (x - largo_losa / 2) + "," + (y + dist_losa_fondo) + " " + (x - largo_losa / 2 - 0.2) + "," + (y + dist_losa_fondo + 0.3) + " "
//                        + (x - largo_losa / 2 + 0.1) + "," + (y + dist_losa_fondo + 0.3) + " " + (x - largo_losa / 2) + "," + (y + dist_losa_fondo + 0.3) + " " + (x - largo_losa / 2) + "," + (y + dist_losa_fondo) + " "
//                        + (x - largo_losa / 2 + 0.2) + "," + (y + dist_losa_fondo + 0.3) + " " + (x - largo_losa / 2 + 0.1) + "," + (y + dist_losa_fondo + 0.3) + " ");
//                pw.println("-text " + (x - largo_losa / 2 - 0.2) + "," + (y + dist_losa_fondo + 0.4) + " 0.2 0 " + "NTE. " + block_num1 + " m");
//
//                pw.println("pline " + (x + drcha_zapata / 2) + "," + (y + altura) + " " + (x + drcha_zapata / 2 - 0.2) + "," + (y + altura + 0.3) + " "
//                        + (x + drcha_zapata / 2 + 0.1) + "," + (y + altura + 0.3) + " " + (x + drcha_zapata / 2) + "," + (y + altura + 0.3) + " " + (x + drcha_zapata / 2) + "," + (y + altura) + " "
//                        + (x + drcha_zapata / 2 + 0.2) + "," + (y + altura + 0.3) + " " + (x + drcha_zapata / 2 + 0.1) + "," + (y + altura + 0.3) + " ");
//                pw.println("-text " + (x + drcha_zapata / 2 - 0.2) + "," + (y + altura + 0.4) + " 0.2 0 " + "NTE. " + (block_num1) + " m");
                
                x+=(altura*2);            
                //y+=(altura*2);            
            }
            pw.print("-osnap endpoint,midpoint,center,node,quadrant,intersection,extension,perpendicular,tangent,nearest,parallel ");
            pw.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }

}
