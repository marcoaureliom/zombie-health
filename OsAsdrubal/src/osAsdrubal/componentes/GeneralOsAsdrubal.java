package osAsdrubal.componentes;

import osAsdrubal.interfaces.*;

public class GeneralOsAsdrubal {
	public static AbstractOsAsdrubal crieOsAsdrubal(String ftype) {
		AbstractOsAsdrubal f = null;
		if (ftype.equals("ZombieWEB"))
			f = new CriadorZombieWEB();
		else if (ftype.equals("Classificador"))
			f = new CriadorClassificador();
		else if (ftype.equals("Grafico"))
			f = new CriadorGrafico();
		return f;
	}
}