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
public class Muro implements Serializable{

    double x = 8;//8
    double y = 4;//4dis
    ArrayList<String> lista_muro;
    private int cont;
    private String name_muro;
    double scala = 20;
    double altura;
    double ancho;
    double altura_zapata;
    double largo_zapata;
    double bajo_zapata;
    double izq_zapata;
    double drcha_zapata;
    double recubrimiento;
    double zolapamiento=2.0/scala;
    double num_varilla=8.0;
    double num_varilla_armado_vert=8.0;
    double num_varilla_armado_horiz=8.0;
    double diametro_varillas=((num_varilla / 8.0) * 2.54) / scala;
    double separacion_varillas=30.0;
    double separacion_varillas_profundidad=30.0;
    double dist_baston=100;
    double alto_losa=15.0/scala;
    double largo_losa=105.96 / scala;;
    double dist_losa_zapata;
    double dist_losa_fondo;
    double fc;
    double fc_plantilla=100.0;
    double block_num1 = -2.2365;
    double block_num2 = +0.1635;
    boolean baston=true;
//deben ir en el cuerpo del metodo    
//    double pto_medio_ancho_x;
//    double pto_medio_ancho_y;
//    double pto_medio_zapata_x;
//    double pto_medio_zapata_y;
//    double cont_pos_varillas_horiz = 0;
//    double cont_pos_varillas_vert = 0;
//    double diametro_señal_varillas = ((16.0 / 8.0) * 2.54) / scala;

//    public Muro(/*int cont, */String name_muro, double altura, double ancho, double altura_zapata, double largo_zapata, double bajo_zapata, double izq_zapata, double drcha_zapata, double recubrimiento, double zolapamiento, double num_varilla, double num_varilla_armado_vert, double num_varilla_armado_horiz, double diametro_varillas, double separacion_varillas, double separacion_varillas_profundidad, double dist_baston, double alto_losa, double largo_losa, double dist_losa_zapata, double dist_losa_fondo, double fc, double fc_plantilla, boolean baston) {
    public Muro(ArrayList<String> lista_muro) {
        //this.cont = cont;
        this.lista_muro=lista_muro;
//        this.name_muro = lista_muro.get(0);
//        this.altura = ( Double.parseDouble(lista_muro.get(11)) + Double.parseDouble(lista_muro.get(12)) - Double.parseDouble(lista_muro.get(14)))/scala;
//        this.ancho = Double.parseDouble(lista_muro.get(15))/scala;
//        this.altura_zapata = Double.parseDouble(lista_muro.get(14))/scala;
//        this.largo_zapata = ( Double.parseDouble(lista_muro.get(17)) + Double.parseDouble(lista_muro.get(18)) + Double.parseDouble(lista_muro.get(19)) )/scala;
//        this.bajo_zapata = Double.parseDouble(lista_muro.get(13))/scala;
//        //comienzan los problemas
//        this.izq_zapata = Double.parseDouble(lista_muro.get(17))/scala;
//        this.drcha_zapata = Double.parseDouble(lista_muro.get(19))/scala;
//        this.recubrimiento = Double.parseDouble(lista_muro.get(37))/scala;
//        //this.zolapamiento = zolapamiento/scala;
//        this.num_varilla = num_varilla;
//        this.num_varilla_armado_vert = num_varilla_armado_vert;
//        this.num_varilla_armado_horiz = num_varilla_armado_horiz;
//        this.diametro_varillas = ((num_varilla / 8.0) * 2.54) / scala;
//        this.separacion_varillas = separacion_varillas/scala;
//        this.separacion_varillas_profundidad = separacion_varillas_profundidad/scala;
//        this.dist_baston = dist_baston/scala;
//        //this.alto_losa = alto_losa/scala;
//        //this.largo_losa = largo_losa/scala;
//        this.dist_losa_zapata = ( Double.parseDouble(lista_muro.get(13))-Double.parseDouble(lista_muro.get(14)) )/scala;
//        this.dist_losa_fondo = ( Double.parseDouble(lista_muro.get(12))+Double.parseDouble(lista_muro.get(13)) )/scala;
//        this.fc = Double.parseDouble(lista_muro.get(36));
//        //this.fc_plantilla = fc_plantilla;
//        this.baston = baston;
    }
    
    

    public String getName_muro() {
        return name_muro;
    }

    public void setName_muro(String name_muro) {
        this.name_muro = name_muro;
    }

    public ArrayList<String> getLista_muro() {
        return lista_muro;
    }

    public void setLista_muro(ArrayList<String> lista_muro) {
        this.lista_muro = lista_muro;
    }
    
    
        

    public void trazado_muro() {
        try {
            double pto_medio_ancho_x;
            double pto_medio_ancho_y;
            double pto_medio_zapata_x;
            double pto_medio_zapata_y;
            double cont_pos_varillas_horiz = 0;
            double cont_pos_varillas_vert = 0;
            double diametro_señal_varillas = ((16.0 / 8.0) * 2.54) / scala;
            FileWriter fichero = new FileWriter("muro.scr");
            PrintWriter pw = new PrintWriter(fichero);
            //Esto es para crear los
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
            for (double i = (y + recubrimiento + zolapamiento + diametro_varillas / 2); i < (y + altura - recubrimiento - 2 * zolapamiento); i += separacion_varillas) {
                pw.println("circle " + (x + recubrimiento + diametro_varillas / 2) + "," + (i) + " " + diametro_varillas / 2);
                pw.println("mirror " + (x + recubrimiento + diametro_varillas) + "," + (i + diametro_varillas / 2) + "  " + (pto_medio_ancho_x) + "," + (pto_medio_ancho_y) + " " + (pto_medio_ancho_x) + "," + (pto_medio_ancho_y + 0.5) + " N  ");
                cont_pos_varillas_vert = i;
            }
            pto_medio_zapata_x = ((x - izq_zapata) + (x + drcha_zapata)) / 2;
            pto_medio_zapata_y = ((y + bajo_zapata) + (y + bajo_zapata + altura_zapata)) / 2;
            for (double j = (x - izq_zapata + 2 * recubrimiento); j < (x + ancho + drcha_zapata - 2 * recubrimiento); j += separacion_varillas) {
                pw.println("circle " + (j + diametro_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + diametro_varillas / 2);
                pw.println("mirror " + (j + diametro_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas) + "  " + (pto_medio_zapata_x) + "," + (pto_medio_zapata_y) + " " + (pto_medio_zapata_x + 0.5) + "," + (pto_medio_zapata_y) + " N  ");
                cont_pos_varillas_horiz = j;
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
            pw.println("qleader " + (x + ancho - recubrimiento - diametro_varillas / 2 + diametro_señal_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + (x + 2 * ancho) + "," + (cont_pos_varillas_vert) + " " + (x + 2 * ancho + 1) + "," + (cont_pos_varillas_vert) + " " + 2.0 + " " + "1#" + Math.round(num_varilla) + "@" + Math.round(separacion_varillas * scala));
            pw.println();
            pw.println("circle " + (x + recubrimiento + diametro_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + diametro_señal_varillas / 2);
            pw.println("qleader " + (x + recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2) + "," + (cont_pos_varillas_vert) + " " + (x - ancho) + "," + (cont_pos_varillas_vert) + " " + (x - 2 * ancho + 1) + "," + (cont_pos_varillas_vert) + " " + 2.0 + " " + "1#" + Math.round(num_varilla) + "@" + Math.round(separacion_varillas * scala));
            pw.println();
            pw.println("circle " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + diametro_señal_varillas / 2);
            pw.println("qleader " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + recubrimiento + diametro_varillas / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla) + "@" + Math.round(separacion_varillas * scala));
            pw.println();
            pw.println("circle " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas / 2) + " " + diametro_señal_varillas / 2);
            pw.println("qleader " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + altura_zapata - recubrimiento - diametro_varillas / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla) + "@" + Math.round(separacion_varillas * scala));
            pw.println();

            pw.println("qleader " + (x + ancho - recubrimiento) + "," + (cont_pos_varillas_vert - separacion_varillas / 2) + " " + (x + 2 * ancho) + "," + (cont_pos_varillas_vert - separacion_varillas / 2) + " " + (x + 2 * ancho + 1) + "," + (cont_pos_varillas_vert - separacion_varillas / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla) + "@" + Math.round(separacion_varillas * scala));
            pw.println();
            pw.println("qleader " + (x + recubrimiento) + "," + (cont_pos_varillas_vert - separacion_varillas / 2) + " " + (x - ancho) + "," + (cont_pos_varillas_vert - separacion_varillas / 2) + " " + (x - 2 * ancho + 1) + "," + (cont_pos_varillas_vert - separacion_varillas / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla) + "@" + Math.round(separacion_varillas * scala));
            pw.println();

            if (baston) {
                pw.println("qleader " + (x + ancho - recubrimiento - diametro_varillas) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + (x + ancho - recubrimiento - diametro_varillas + 2 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + (x + ancho - recubrimiento - diametro_varillas + 8 * recubrimiento) + "," + (y + bajo_zapata + altura_zapata + dist_baston / 2) + " " + 2.0 + " " + "2#" + Math.round(num_varilla_armado_vert) + "@" + Math.round(separacion_varillas_profundidad * scala));
                pw.println();
            }

            pw.println("qleader " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + recubrimiento) + " " + (x - izq_zapata / 2) + "," + (y + bajo_zapata / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_armado_horiz) + "@" + Math.round(separacion_varillas_profundidad * scala));
            pw.println();
            pw.println("qleader " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + altura_zapata - recubrimiento) + " " + (x - izq_zapata / 2) + "," + (y + bajo_zapata + altura_zapata + bajo_zapata / 2) + " " + (x - izq_zapata + 2 * recubrimiento + diametro_varillas / 2 - diametro_señal_varillas / 2 - ancho - 1.5) + "," + (y + bajo_zapata + altura_zapata + bajo_zapata / 2) + " " + 2.0 + " " + "1#" + Math.round(num_varilla_armado_horiz) + "@" + Math.round(separacion_varillas_profundidad * scala));
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
            pw.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }

}
