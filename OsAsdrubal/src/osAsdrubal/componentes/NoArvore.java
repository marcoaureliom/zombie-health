package osAsdrubal.componentes;

import java.util.ArrayList;
import java.util.List;

import osAsdrubal.interfaces.INoArvore;

public class NoArvore implements INoArvore{

	private int id;
	private String texto;
	private String[] legendasInferiores;

	private INoArvore pai;
	private List<INoArvore> filhos;

	public NoArvore(int id, String texto, String[] legendasInferiores) {

		this.id = id;
		this.texto = texto;
		if (legendasInferiores != null) {
			this.legendasInferiores = new String[legendasInferiores.length];
			for (int i = 0; i < legendasInferiores.length; i++) {
				this.legendasInferiores[i] = legendasInferiores[i];
			}
		}
		filhos = new ArrayList<INoArvore>();
	}

	public void addFilho(int id, String texto, String[] legendasInferiores) {
		INoArvore filho = new NoArvore(id, texto, legendasInferiores);
		filho.setPai(this);
		this.filhos.add(filho);
	}

	public INoArvore encontreNoPorId(int id) {

		if (this.id == id)
			return this;

		for (INoArvore filho : filhos) {
			INoArvore f = filho.encontreNoPorId(id);
			if (f != null)
				return f;
		}

		return null;

	}

	public void setPai(INoArvore pai) {
		this.pai = pai;
	}

	public int getId() {
		return id;
	}

	public int getIdPai() {
		if (pai == null)
			return -1;
		return pai.getId();
	}

	public String getTexto() {
		return texto;
	}

	public boolean temPai() {
		return (pai != null);
	}

	public List<INoArvore> getFilhos() {
		return filhos;
	}

	public String[] getLegInf() {
		return legendasInferiores;
	}

}

