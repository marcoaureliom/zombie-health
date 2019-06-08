package osAsdrubal.componentes;

import osAsdrubal.interfaces.*;

public class CriadorZombieWEB implements AbstractOsAsdrubal {
	public IZombieWEB crieZombieWEB() {
		return new ZombieWEB();
	}
	public IClassificador crieClassificador() {
		return null;
	}
	public IGrafico crieGraficoDeBarra() {
        return null;
    }
	public IGrafico crieGraficoDePizza() {
		return null;
    }
}
