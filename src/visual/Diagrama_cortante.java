/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import static demo.MinMaxCategoryPlotDemo1.createChart;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Alba Proyecto
 */
public class Diagrama_cortante extends JFrame{
    
    private double[] eje_x;
    private double[] eje_y;

    /**
     * Creates new form Frame
     */
    public Diagrama_cortante(double [] eje_x, double [] eje_y) {
        
        this.eje_x=eje_x;
        this.eje_y=eje_y;
        
        setBounds(700, 300, 500, 700);
        String SITIO_1 = "PRIMERO";
        String SITIO_2 = "SEGUNDO";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        XYSeriesCollection xy=new XYSeriesCollection();
        XYSeries serie1=new XYSeries("Curva de Momentos");
//        XYSeries serie2=new XYSeries("Acero corrido");
//        XYSeries serie3=new XYSeries("Bastón");
        
        //aqui trazamos la curva
        for(int i=0; i<eje_x.length; i++){
            serie1.add(eje_x[i],eje_y[i]);
        }
        
//        for(int i=0; i<eje_x.length; i++){
//            serie2.add(eje_y[i],eje_x[i]);
//        }
        
        //aqui trazamos el acero corrido
//        serie2.add(4,0);
//        serie2.add(4,2.9);
        
        //aqui trazamos baston
//        serie3.add(8,0);
//        serie3.add(8,1.0);
        
        
//        serie2.add(5, 1);
//        serie2.add(6, 2);
//        serie2.add(7, 3);
//        serie2.add(8, 4);
        
        xy.addSeries(serie1);
//        xy.addSeries(serie2);
//        xy.addSeries(serie3);

        
        
        //mi example
        //final JFreeChart mi_gr=ChartFactory.createXYLineChart("Diagrama de Momentos", "datos_x", "datos_y", xy );
        final JFreeChart mi_gr1=ChartFactory.createXYLineChart("Diagrama de Cortante", "datos_y", "datos_x", xy, PlotOrientation.HORIZONTAL, true, true, true);
        
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        //final ChartPanel chartPanel1 = new ChartPanel(mi_gr);
        final ChartPanel chartPanel1 = new ChartPanel(mi_gr1);
        //chartPanel.setPreferredSize(new Dimension(500, 270));
        //chartPanel.setEnforceFileExtensions(false);
        chartPanel1.setPreferredSize(new Dimension(500, 270));
        chartPanel1.setEnforceFileExtensions(false);
        
        

        //setContentPane(chartPanel);
        setContentPane(chartPanel1);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
    
}
