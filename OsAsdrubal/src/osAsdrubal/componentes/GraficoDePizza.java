package osAsdrubal.componentes;
import osAsdrubal.interfaces.*;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class GraficoDePizza extends JFrame implements IGrafico{
 
	private static final long serialVersionUID = 1L;
 
	public GraficoDePizza() {
		
		//Se isto setado, ao fechar a janela do gráfico a aplicação toda se encerra
    	//setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Gráfico");
        setSize(950,700);
        setLocationRelativeTo(null);
		
	}
	
	public void crieGrafico(String x[], int y[],String t1, String t2, String t3) {
		PieDataset dataset = createDataset(x,y);
		JFreeChart chart = ChartFactory.createPieChart3D(t1, dataset, true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		ChartPanel painel = new ChartPanel(chart);
        add(painel);
        
        setVisible(true);
		
	}
	private PieDataset createDataset(String x[],int y[]) {		
		DefaultPieDataset data = new DefaultPieDataset();
		for(int i=0; i< x.length; i++) {
    		data.setValue(x[i],y[i]);
    	}
		return data;
 
	}
	
}