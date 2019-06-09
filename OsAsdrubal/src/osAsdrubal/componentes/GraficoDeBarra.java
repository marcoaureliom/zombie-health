package osAsdrubal.componentes;
import osAsdrubal.interfaces.*;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;

public class GraficoDeBarra extends JFrame implements IGrafico{

    private static final long serialVersionUID = 1L;

    public GraficoDeBarra() {
    	
    	//Se isto setado, ao fechar a janela do gráfico a aplicação toda se encerra
    	//setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Gráfico");
        setSize(950,700);
        setLocationRelativeTo(null);
    	
    }
    
    public void crieGrafico(String x[], int y[],String t1, String t2, String t3) { 
    	
    	DefaultCategoryDataset dataset;
    	dataset = createDataset(x, y);
        JFreeChart grafico = ChartFactory.createBarChart3D(t1,t2,t3, dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel painel = new ChartPanel(grafico);
        add(painel);
        
        setVisible(true);
    }
    private DefaultCategoryDataset createDataset(String x[],int y[]) {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i=0; i< x.length; i++) {
    		dataset.setValue(y[i], x[i], "");
    	}
    	return dataset;
    }
	
}