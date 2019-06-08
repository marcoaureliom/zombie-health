package osAsdrubal.componentes;

import java.util.ArrayList;
import java.util.List;

public class NoArvore {

	private int id;
	private String texto;
	private String[] legendasInferiores;

	private NoArvore pai;
	private List<NoArvore> filhos;

	public NoArvore(int id, String texto, String[] legendasInferiores) {

		this.id = id;
		this.texto = texto;
		if (legendasInferiores != null) {
			this.legendasInferiores = new String[legendasInferiores.length];
			for (int i = 0; i < legendasInferiores.length; i++) {
				this.legendasInferiores[i] = legendasInferiores[i];
			}
		}
		filhos = new ArrayList<NoArvore>();
	}

	public void addFilho(int id, String texto, String[] legendasInferiores) {
		NoArvore filho = new NoArvore(id, texto, legendasInferiores);
		filho.setPai(this);
		this.filhos.add(filho);
	}

	public NoArvore encontreNoPorId(int id) {

		if (this.id == id)
			return this;

		for (NoArvore filho : filhos) {
			NoArvore f = filho.encontreNoPorId(id);
			if (f != null)
				return f;
		}

		return null;

	}

	public void setPai(NoArvore pai) {
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

	public List<NoArvore> getFilhos() {
		return filhos;
	}

	public String[] getLegInf() {
		return legendasInferiores;
	}

}

