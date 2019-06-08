# Equipe *osAsdrubal*
## Integrantes: Alexandre Tamaoki, Jhonatas Santos e Marco Aurélio Martins.

<p align="center">
  <img src="Arquivos/DivulgacaoOsAsdrubal.png?raw=true" title="Divulgação">
</p>

----
# Componente `ZombieWEB`

Item | Descrição
----- | -----
Classe | `osAsdrubal.interfaces.IZombieWEB`
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
	
	//Métodos da criação de um classificador:
	public void crieClassificador(String nomeArvore);
	public void removaClassificador(String nomeArvore);
	
	//Métodos de criação da página:
	public void setNomeArquivo(String nomeArquivo);
	public void setNomeSite(String nomeSite);
	public boolean criePaginaHTML();
	public void abraPagina();
	
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
`insiraElementoArvore` | Insere na árvore um elemento (nó). Recebe por parâmetro o nome da árvore em que você quer inserir o elemento, o ID (`int`) do elemento pai (**-1** se raiz), o ID (`int`) deste elemento novo, uma `String` que será o texto do elemento.
`insiraElementoArvore` | Sobrecarga de `insiraElementoArvore`. Recebe também um vetor de legendas que ficarão na parte inferior do nó na exibição. Nesse vetor, você deve passar um valor e na posição seguinte um valor de cor de fundo (`{"Sim", "#5cbd79", "Não", "#5b87d4"}`).
`criePaginaHTML` | Cria no diretório do programa uma página HTML se nome do arquivo já estiver definido.
`abraPagina` | Abre no navegador a página criada.
`setNomeArquivo` | Recebe uma `String` com um nome de identificação do arquivo e o registra.
`setNomeSite` | Recebe uma `String` com um nome para o site e o registra.
`crieClassificador` | Recebe uma `String` com um nome de identificação da árvore que quer aplicar a função de classificador.
`removaClassificador` | Recebe uma `String` com um nome de identificação da árvore que quer remover a função de classificador.

## Exemplos de Implementações

### Interface `IZombieWEB`
~~~java
import osAsdrubal.componentes.*;
import osAsdrubal.interfaces.*;

//Usando AbstractOsAsdrubal
AbstractOsAsdrubal zw = GeneralOsAsdrubal.crieOsAsdrubal("ZombieWEB");

//ZombieWEB
IZombieWEB ZW = zw.crieZombieWEB();

ZW.setNomeArquivo("UmExemplo");
ZW.crieArvore("Nome da Árvore");
ZW.insiraElementoArvore("Nome da Árvore", -1, 1, "Raiz", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
ZW.insiraElementoArvore("Nome da Árvore", 1, 11, "Filho 1.1", new String[] { "Legenda", "#5cbd79" });
ZW.insiraElementoArvore("Nome da Árvore", 11, 111, "Filho 1.1.1", null);
ZW.insiraElementoArvore("Nome da Árvore", 1, 12, "Filho 1.2",
		new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
ZW.insiraElementoArvore("Nome da Árvore", 12, 121, "Filho 1.2.1",
		new String[] { "Sim", "#5cbd79", "Não", "#5b87d4", "Talvez", "blue" });
ZW.insiraElementoArvore("Nome da Árvore", 121, 1211, "Filho 1.2.1.1");
ZW.insiraElementoArvore("Nome da Árvore", 121, 1212, "Filho 1.2.1.2");
ZW.insiraElementoArvore("Nome da Árvore", 121, 1213, "Filho 1.2.1.3", null);
ZW.insiraElementoArvore("Nome da Árvore", 12, 122, "Filho 1.2.2");
ZW.crieClassificador("Nome da Árvore");
ZW.criePaginaHTML();
ZW.abraPagina();
~~~
#### Visualização
A árvore interage com o cursor, alterando a cor do nó e subnós. As folhas possuem a mesma cor.
<p align="center">
  <img src="Arquivos/arvore3.png?raw=true" width="250" title="Cursor fora da árvore">
  <img src="Arquivos/arvore2.png?raw=true" width="250" title="Árvore interagindo com o cursor">
  <img src="Arquivos/arvore1.png?raw=true" width="250" title="Árvore interagindo com o cursor">
</p>
<br>
Quando a função de classificador é aplicada a uma árvore, campos do lado esquerdo permitem que usuário adicione informações e, através de JavaScript, encontramos um nó, que pode ou ser de decisão ou uma folha, ambos chamados de "Classificação" para uma aplicação mais ampla.
<br><br>
<p align="center">
  <img src="Arquivos/classificacao1.png?raw=true" title="Árvore com classificador">
</p>
<br>
Para o componente `ZombieWEB` não existem limites de criação de árvores, nós e filhos de único nó; quando é criada mais de uma árvore, a página `HTML` mostra uma seguida da outra. O classificador que vemos acima tenta classificar no momento que um campo está sendo preenchido, e não é necessário que se preencha todos. Os campos, assim como a árvore, possuem interação com o movimento e clique do mouse. No arquivo `HTML` gerado são utilizados também `JavaScript` e `CSS`.
