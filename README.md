# Equipe *osAsdrubal*
## Integrantes: Alexandre Tamaoki, Jhonatas Santos e Marco Aurélio Martins.

<p align="center">
  <img src="Arquivos/DivulgacaoOsAsdrubal.png?raw=true" title="Divulgação">
</p>

----
# Arquivo JAR
[BAIXE AQUI](https://github.com/marcoaureliom/zombie-health/tree/master/JAR)

# Componente `GeneralOsAsdrubal`

Item | Descrição
----- | -----
Classe | `osAsdrubal.componentes.GeneralOsAsdrubal`
Objetivo | Cria fábricas dos componentes da equipe.
Único método (**estático**): `crieOsAsdrubal` | Recebe uma `String` com um nome de identificação do componente ("ZombieWEB", "Classificador" ou "Grafico") e retorna um `AbstractOsAsdrubal`
~~~ java
public interface AbstractOsAsdrubal {
	public IZombieWEB crieZombieWEB();
	public IClassificador crieClassificador();
	public IGrafico crieGraficoDeBarra();
	public IGrafico crieGraficoDePizza();
}
~~~
## Exemplo de implementação
~~~java
import osAsdrubal.componentes.*;
import osAsdrubal.interfaces.*;

AbstractOsAsdrubal zwFabrica = GeneralOsAsdrubal.crieOsAsdrubal("ZombieWEB");
AbstractOsAsdrubal classificadorFabrica = GeneralOsAsdrubal.crieOsAsdrubal("Classificador");
AbstractOsAsdrubal gFabrica = GeneralOsAsdrubal.crieOsAsdrubal("Grafico");

IZombieWEB zw = zwFabrica.crieZombieWEB();
IClassificador classificador = classificadorFabrica.crieClassificador();
IGrafico gBarra = gFabrica.crieGraficoDeBarra();
IGrafico gPizza = gFabrica.crieGraficoDePizza();
~~~

----
# Componentes
# Componente `ZombieWEB`

Item | Descrição
----- | -----
Classe | `osAsdrubal.componentes.ZombieWEB`
Autor | Marco Aurélio
Objetivo | Criar gráficos de dados usando ferramentas de Desenvolvimento WEB dos principais elementos do Zombie Health.
Interface | `osAsdrubal.interfaces.IZombieWEB`
~~~ java
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

# Componentes Gráfico de Barra e Gráfico de Pizza

|   Item   |                          Descrição                          |
|----------|-------------------------------------------------------------|
| Classe   | `osAsdrubal.componentes.GraficoDeBarra` e `osAsdrubal.componentes.GraficoDePizza`|
| Autor    | Jhonatas Santos                                             |
| Objetivo | Criar gráficos de barra e de pizza para ter representação visual de alguns resultados.|
| Interface| `osAsdrubal.interfaces.IGrafico`|
| **Pacotes externos requeridos** | [jcommon-1.0.23.jar](https://github.com/marcoaureliom/zombie-health/blob/master/JAR/Externos/jcommon-1.0.23.jar) e [jfreechart-1.0.19.jar](https://github.com/marcoaureliom/zombie-health/blob/master/JAR/Externos/jfreechart-1.0.19.jar)|
~~~ java
public interface IGrafico {
	public void crieGrafico(String x[], int y[],String t1, String t2, String t3);
}
~~~

# Componentes Classificador por Árvore de Decisão (Decision Tree Classifier)

|   Item   |                          Descrição                          |
|----------|-------------------------------------------------------------|
| Classe   | `osAsdrubal.componentes.Classificador`|
| Autor    | Alexandre Tamaoki                                           |
| Objetivo | Criar uma árvore de decisão para classificar o diagnóstico do paciente.|
| Interface| `osAsdrubal.interfaces.IClassificador`|
| **Pacotes externos requeridos** | [weka-3.9.3.jar](https://github.com/marcoaureliom/zombie-health/blob/master/JAR/Externos/weka-3.9.3.jar)|
~~~ java
public interface IClassificador {
	public void construaClassificador();
	public void fit();
	public String[] predict(Instances test_data);
	public String predict(Instance test_data);
	public void setInstances(String file);
	public void imprimaClassificador();
	public abstract float accuracy (String[] y_val, String[] y_pred);
	public String[] requestAttributes();
	public String[][] requestInstances();
	public Instances requestInstanciasArvore();
}
~~~

## Detalhamento das Interfaces

### Interface `IZombieWEB`
Acesso aos métodos de criação de uma página.

Método | Objetivo
-------| --------
`crieArvore` | Recebe uma `String` com um nome de identificação de árvore e o registra.
`removaArvore` | Recebe uma `String` com um nome de identificação de árvore e a remove.
`insiraElementoArvore` | Insere na árvore um elemento (nó). Recebe por parâmetro o nome da árvore em que você quer inserir o elemento, o ID (`int`) do elemento pai (**-1** se raiz), o ID (`int`) deste elemento novo, uma `String` que será o texto do elemento.
`insiraElementoArvore` | Sobrecarga de `insiraElementoArvore`. Recebe também um vetor de legendas que ficarão na parte inferior do nó na exibição. Nesse vetor, você deve passar um valor e na posição seguinte um valor de cor de fundo (`{"Sim", "#5cbd79", "Não", "#5b87d4"}`).
`crieClassificador` | Recebe uma `String` com um nome de identificação da árvore que quer aplicar a função de classificador.
`removaClassificador` | Recebe uma `String` com um nome de identificação da árvore que quer remover a função de classificador.
`setNomeArquivo` | Recebe uma `String` com um nome de identificação do arquivo e o registra.
`setNomeSite` | Recebe uma `String` com um nome para o site e o registra.
`criePaginaHTML` | Cria no diretório do programa uma página HTML se nome do arquivo já estiver definido.
`abraPagina` | Abre no navegador a página criada.
`getArvore` | (Funcionalidade extra: necessário ler implementação [aqui](https://github.com/marcoaureliom/zombie-health/blob/master/OsAsdrubal/src/osAsdrubal/interfaces/INoArvore.java) e [aqui](https://github.com/marcoaureliom/zombie-health/blob/master/OsAsdrubal/src/osAsdrubal/componentes/NoArvore.java).) Recebe uma `String` com um nome de identificação da árvore e retorna sua raiz (```INoArvore```).

## Interface IGrafico

|   Método            |   Objetivo   |
|---------------------|--------------|
| ```crieGrafico``` | Recebe um vetor de ```Strings``` para ser o eixo *x* no gráfico de barras ou a descrição de um intervalo do círculo no gráfico de pizza. Recebe também um vetor de ```int``` que será o eixo *y* para o gráfico de barras e será o tamanho do intervalo do círculo no gráfico de pizza. Por último, recebe três parametros ```Strings``` que serão título do gráfico, título do eixo x e título do eixo y respectivamente, para o gráfico de barras, mas para o gráfico de pizza só será usado o título do gráfico, e então os dois outros campos podem ser ```Strings``` vazias.|

## Interface IClassificador
|   Método            |   Objetivo   |
|---------------------|--------------|
| ```construaClassificador``` | Método de inicialização do classificador.|
|  ```fit```| Treina o modelo com as instâncias passadas pelo construtor ou setadas com o método setInstances.|
|```String [] predict```| Após ter o modelo treinado, prediz o target para cada uma das instâncias passadas como parâmetro e retorna um vetor de Strings com estes targets (última coluna das instancias).|
|```String predict```| Prediz o target de uma única instância. Este método é utilizado iterativamente pelo método anterior para predizer para várias instâncias.|
|```imprimaClassificador```| Imprime a árvore de decisão detalhada utilizada como modelo para a predição.|
|```setInstances```| Altera as instâncias utilizadas para criar o modelo com base no caminho do arquivo passado. Se as instâncias mudarem, é necessário chamar os métodos construaClassificador e fit para predizer corretamente as instâncias.|
|```requestAttributes```| Retorna os atributos do arquivo .csv passado como parâmetro para o classificador.|
|```requestIntances```| Retorna os dados do arquivo .csv passado como parâmetro em formato de String[][].|
|```requestInstanciasArvore```| Retorna os dados do arquivo .csv passado como parâmetro em formato de Instances.|

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
Para o componente ZombieWEB não existem limites de criação de árvores, nós e filhos de único nó; quando é criada mais de uma árvore, a página HTML mostra uma seguida da outra. O classificador que vemos acima tenta classificar no momento que um campo está sendo preenchido, e não é necessário que se preencha todos. Os campos, assim como a árvore, possuem interação com o movimento e clique do mouse. No arquivo HTML gerado são utilizados também JavaScript e CSS.

### Interface ```IGrafico```

~~~ java
import osAsdrubal.componentes.*;
import osAsdrubal.interfaces.*;

//Usando AbstractOsAsdrubal
AbstractOsAsdrubal graficoFabrica = GeneralOsAsdrubal.crieOsAsdrubal("Grafico");

//Graficos
IGrafico graficoPizza = graficoFabrica.crieGraficoDePizza();
IGrafico graficoBarra = graficoFabrica.crieGraficoDeBarra();

graficoPizza.crieGrafico(new String[] {"x1","x2","x3"}, new int[] {1,2,3}, "t1", "t2", "t3");
graficoBarra.crieGrafico(new String[] {"x1","x2","x3"}, new int[] {1,2,3}, "t1", "t2", "t3");
~~~       
#### Visualização
<p align="center">
  <img src="Arquivos/graficoBarra.png?raw=true" width="250" title="Gráfico de Barra">
  <img src="Arquivos/graficoPizza.png?raw=true" width="250" title="Gráfico de Pizza">
</p>

### Interface ```IClassificador```

~~~ java
import osAsdrubal.componentes.*;
import osAsdrubal.interfaces.*;

//Usando AbstractOsAsdrubal
AbstractOsAsdrubal classificadorFabrica = GeneralOsAsdrubal.crieOsAsdrubal("Classificador");

dataset.setDataSource("C:\\Users\\massa\\Documents\\MC322\\Trabalho\\zombie-health-new-cases500.csv");

IClassificador classificador = classificadorFabrica.crieClassificador();

classificador.setInstances("zombie-health-new-cases500.csv")		
classificador.construaClassificador();
		
classificador.fit();
String[] diag = classificador.predict(classificador.requestInstanciasArvore());

String[] y_val = new String[classificador.requestInstances().length];
System.out.println("      Predict      Real")
for(int i=0;i<classificador.requestInstanciasArvore().numInstances();i++) {
	y_val[i] = classificador.requestInstances()[i][classificador.requestAttributes().length-1];
	System.out.println("Pac"+i+": "+diag[i]+"  "+classificador.requestInstances()[i][classificador.requestAttributes().length-1]);
}
			
classificador.imprimaClassificador();
		
System.out.println("accuracy = "+classificador.accuracy(y_val,diag));
~~~    

# Diagrama UML
<p align="center">
  <img src="Arquivos/DiagramaUML.png?raw=true" title="UML">
</p>
