package osAsdrubal.componentes;
import osAsdrubal.interfaces.*;

public class CriadorClassificador implements AbstractOsAsdrubal{
	public IClassificador crieClassificador() {
		return new Classificador();
	}
	public IZombieWEB crieZombieWEB() {
		return null;
	}
	public IGrafico crieGraficoDeBarra(String x[], int y[],String t1, String t2, String t3) {
        return null;
    }
	public IGrafico crieGraficoDePizza(String x[], int y[],String t1, String t2, String t3) {
		return null;
    }
}
