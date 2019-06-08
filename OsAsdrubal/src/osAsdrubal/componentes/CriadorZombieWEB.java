package osAsdrubal.componentes;

import osAsdrubal.interfaces.*;

public class CriadorZombieWEB implements AbstractOsAsdrubal {
	public IZombieWEB crieZombieWEB() {
		return new ZombieWEB();
	}
	public IClassificador crieClassificador() {
		return null;
	}
	public IGrafico crieGraficoDeBarra(String x[], int y[],String t1, String t2, String t3) {
        return null;
    }
	public IGrafico crieGraficoDePizza(String x[], int y[],String t1, String t2, String t3) {
		return null;
    }
}
