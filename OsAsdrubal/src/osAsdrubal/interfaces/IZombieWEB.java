package osAsdrubal.interfaces;

public interface IZombieWEB {

	//Métodos da criação de uma árvore:
	public boolean crieArvore(String nomeArvore);
	public boolean removaArvore(String nomeArvore);
	public boolean insiraElementoArvore(String nomeArvore, int idPai, int idNovoElemento, String textoNovoElemento);
	public boolean insiraElementoArvore(String nomeArvore, int idPai, int idNovoElemento, String textoNovoElemento, String[] legendasInferiores);
	
	//Métodos da criação de um classificador
	public void crieClassificador(String nomeArvore);
	public void removaClassificador(String nomeArvore);
	
	//Métodos de criação da página
	public void setNomeArquivo(String nomeArquivo);
	public void setNomeSite(String nomeSite);
	public boolean criePaginaHTML();
	public void abraPagina();
	
	//Obter a árvore
	public INoArvore getArvore(String nomeArvore);
}
