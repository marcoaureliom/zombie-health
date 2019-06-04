# Equipe *Os Asdrubal*

Autores: Alexandre Tamaoki, Jhonatas Santos e Marco Aurélio Martins.
----
# Componente `ZombieWEB`

Item | Descrição
----- | -----
Classe | `zombieHealth.ZombieWEB`
Autor | Marco Aurélio
Objetivo | Criar gráficos de dados usando ferramentas de Desenvolvimento WEB dos principais elementos do Zombie Health.
Interface | `IZombieWEB`

~~~
public interface IZombieWEB {

	//Métodos da criação de uma árvore:
	public boolean crieArvore(String nomeArvore);
	public boolean removaArvore(String nomeArvore);
	public boolean insiraElementoArvore(String nomeArvore, int idPai, int idNovoElemento, 
					    String textoNovoElemento);
	public boolean insiraElementoArvore(String nomeArvore, int idPai, int idNovoElemento, 
					    String textoNovoElemento, String[] legendasInferiores);
	public boolean criePaginaHTML();
	public void abraPagina();
	
	public void setNomeArquivo(String nomeArquivo);
	public void setNomeSite(String nomeSite);
	
}
~~~

## Detalhamento das Interfaces

### Interface `IZombieWEB`
Acesso aos métodos de criação de uma página.
(Atualmente finalizada a criação da árvore. Em breve, novas funcionalidades!)

Método | Objetivo
-------| --------
`crieArvore` | Recebe uma `String` com um nome de identificação de árvore e o registra.
`removaArvore` | Recebe uma `String` com um nome de identificação de árvore e a remove.
`insiraElementoArvore` | Insere na árvore um elemento (nó). Recebe por parâmetro o nome da árvore em que você quer inserir o elemento, o ID (`int`) do elemento pai (`-1` se raiz), o ID (`int`) deste elemento novo, uma `String` que será o texto do elemento.
`insiraElementoArvore` | Sobrecarga de `insiraElementoArvore`. Recebe também um vetor de legendas que ficarão na parte inferior do nó na exibição. Nesse vetor, você deve passar um valor e na posição seguinte um valor de cor de fundo (`{"Sim", "#5cbd79", "Não", "#5b87d4"}`).
`criePaginaHTML` | Cria no diretório do programa uma página HTML se nome do arquivo já estiver definido.
`abraPagina` | Abre no navegador a página criada.
`setNomeArquivo` | Recebe uma `String` com um nome de identificação do arquivo e o registra.
`setNomeSite` | Recebe uma `String` com um nome para o site e o registra.

## Exemplos de Implementações

### Interface `IZombieWEB`
~~~java
IZombieWEB zW = new ZombieWEB();
zW.setNomeArquivo("UmExemplo");

zW.crieArvore("Nome da Árvore");
zW.insiraElementoArvore("Nome da Árvore", -1, 1, "Raiz", new String[] {"Sim", "#5cbd79", "Não", "#5b87d4"} );
zW.insiraElementoArvore("Nome da Árvore", 1, 11, "Filho 1.1", new String[] {"Legenda", "#5cbd79"});
zW.insiraElementoArvore("Nome da Árvore", 11, 111, "Filho 1.1.1", null );
zW.insiraElementoArvore("Nome da Árvore", 1, 12, "Filho 1.2", new String[] {"Sim", "#5cbd79", "Não", "#5b87d4"});
zW.insiraElementoArvore("Nome da Árvore", 12, 121, "Filho 1.2.1", new String[] {"Sim", "#5cbd79", "Não", "#5b87d4", "Talvez", "blue"});
zW.insiraElementoArvore("Nome da Árvore", 121, 1211, "Filho 1.2.1.1");
zW.insiraElementoArvore("Nome da Árvore", 121, 1212, "Filho 1.2.1.2");
zW.insiraElementoArvore("Nome da Árvore", 121, 1213, "Filho 1.2.1.3", null );
zW.insiraElementoArvore("Nome da Árvore", 12, 122, "Filho 1.2.2");

zW.criePaginaHTML();
zW.abraPagina();
~~~
#### Visualização
A árvore interage com o cursor, alterando a cor do nó e subnós. As folhas possuem a mesma cor.
<p align="center">
  <img src="Arquivos/arvore3.png?raw=true" width="250" title="Cursor fora da árvore">
  <img src="Arquivos/arvore2.png?raw=true" width="250" title="Árvore interagindo com o cursor">
  <img src="Arquivos/arvore1.png?raw=true" width="250" title="Árvore interagindo com o cursor">
</p>
