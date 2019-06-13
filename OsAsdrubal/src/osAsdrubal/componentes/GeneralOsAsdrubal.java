package osAsdrubal.componentes;

import osAsdrubal.interfaces.*;

/**
 * Criador de fábricas de componentes da equipe osAsdrubal.
 * @author Marco Aurélio
 */
public class GeneralOsAsdrubal {
	/**
	 * Recebe como parâmetro um nome de componente: "ZombieWEB", "Classificador" ou "Grafico".
	 * @param String nome do componente
	 * @return AbstractOsAsdrubal
	 */
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