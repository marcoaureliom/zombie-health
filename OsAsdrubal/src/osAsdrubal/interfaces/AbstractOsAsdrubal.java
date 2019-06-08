package osAsdrubal.interfaces;

public interface AbstractOsAsdrubal {
	public IZombieWEB crieZombieWEB();
	public IClassificador crieClassificador();
	public IGrafico crieGraficoDeBarra(String x[], int y[],String t1, String t2, String t3);
	public IGrafico crieGraficoDePizza(String x[], int y[],String t1, String t2, String t3);
}
