package osAsdrubal.componentes;

import osAsdrubal.interfaces.AbstractOsAsdrubal;
import osAsdrubal.interfaces.IClassificador;
import osAsdrubal.interfaces.IGrafico;
import osAsdrubal.interfaces.IZombieWEB;

public class CriadorGrafico implements AbstractOsAsdrubal{
	public IGrafico crieGraficoDeBarra() {
        return new GraficoDeBarra();
    }
	public IGrafico crieGraficoDePizza() {
		return new GraficoDePizza();
    }
	public IClassificador crieClassificador() {
		return null;
	}
	public IZombieWEB crieZombieWEB() {
		return null;
	}
}
