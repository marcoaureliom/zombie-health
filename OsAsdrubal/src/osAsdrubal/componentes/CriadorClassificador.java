package osAsdrubal.componentes;
import osAsdrubal.interfaces.*;

public class CriadorClassificador implements AbstractOsAsdrubal{
	public IClassificador crieClassificador() {
		return new Classificador();
	}
	public IZombieWEB crieZombieWEB() {
		return null;
	}
	public IGrafico crieGraficoDeBarra() {
        return null;
    }
	public IGrafico crieGraficoDePizza() {
		return null;
    }
}
