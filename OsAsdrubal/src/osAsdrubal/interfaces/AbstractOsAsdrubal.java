package osAsdrubal.interfaces;

/**
 * Interface dos componentes CriadorZombieWEB, CriadorClassificador e CriadorGrafico.
 * @author Marco Aurélio
 */
public interface AbstractOsAsdrubal {
	public IZombieWEB crieZombieWEB();
	public IClassificador crieClassificador();
	public IGrafico crieGraficoDeBarra();
	public IGrafico crieGraficoDePizza();
}
