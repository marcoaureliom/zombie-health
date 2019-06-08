package osAsdrubal.componentes;

import osAsdrubal.interfaces.AbstractOsAsdrubal;
import osAsdrubal.interfaces.IClassificador;
import osAsdrubal.interfaces.IGrafico;
import osAsdrubal.interfaces.IZombieWEB;

public class CriadorGrafico implements AbstractOsAsdrubal{
	public IGrafico crieGraficoDeBarra(String x[], int y[],String t1, String t2, String t3) {
        return new GraficoDeBarra(x, y, t1, t2, t3);
    }
	public IGrafico crieGraficoDePizza(String x[], int y[],String t1, String t2, String t3) {
		return new GraficoDePizza(x, y, t1, t2, t3);
    }
	public IClassificador crieClassificador() {
		return null;
	}
	public IZombieWEB crieZombieWEB() {
		return null;
	}
}
