# Em edição

# Equipe *Os Asdrubal*

A documentação será feita em Markdown, vide detalhes sobre ele em: https://guides.github.com/features/mastering-markdown/

E mais especificamente sobre tabelas em: https://help.github.com/en/articles/organizing-information-with-tables

Segue abaixo o modelo de como deve ser documentado um componente. Tudo o que for indicado entre `<...>` indica algo que deve ser substituído pelo indicado.

# Componente `EvaluationNAttributes`

Campo | Valor
----- | -----
Classe | `zombieHealth.interfaces.EvaluationNAttributes`
Autor | `Marco Aurélio`
Objetivo | `Probabilidade de n classificadores serem verdadeiros dado os valores de i atributos aplicados simultaneamente nos n classificadores.`
Interface | `IEvaluationNAttributes`

~~~
public interface IEvaluationNAttributes {

    public void setInstances(String[][] instances);
    public void setAttibutes(String[] attributes);
    public void insertClassifierEval(String classifier);
    public void removeClassifierEval(String classifier);
    public void insertAttributeEval(String attribute, String value);
    public void removeAttributeEval(String attribute);
    public String[][] eval();
    public String[][] evalAttribute(String attribute, String value);
    public String[][] evalWithNewAttribute(String attribute, String value);

}
~~~

# Componente `OptimizedDataSet`

Campo | Valor
----- | -----
Classe | `zombieHealth.OptimizedDataSet`
Autores | `Marco Aurélio`
Objetivo | `Substituição de valores nas instâncias: analisa um conjunto de instâncias e realiza automaticamente substituições de valores mantendo um mínimo definido de precisão na classificação.`
Interface | `IOptimizedDataSet`

~~~
public interface IOptimizedDataSet extends IDataSource, ITableProducer {
	public void optimizeAttribute(String attribute, double minAccuracy);
	public void optimizeAllAttributes(double minAccuracy);
	
	public void attributeReplaceValue(String attribute, String search, String replacement);
	public void classifiersReplaceValue(String search, String replacement);
	public void attributeReplaceLimits(String attribute, double lessThan, double moreThan, String replacement);
	public void classifiersReplaceLimits(double lessThan, double moreThan, String replacement);
	public void attributesReplaceValue(String search, String replacement);
	public void attributesReplaceLimits(double lessThan, double moreThan, String replacement);
}
~~~

## Detalhamento das Interfaces

### Interface `IEvaluationNAttributes`
Avalia a chance de n classificadores dados os valores de i atributos.

Método | Objetivo
-------| --------
`setInstances` | Recebe uma matriz de String com as instâncias.
`setAttibutes` | Recebe um vetor de String com os atributos das instâncias.
`insertClassifierEval` |  Insere na avaliação um classificador informado pelo parâmetro `classifier`.
`removeClassifierEval` | Remove da avaliação um classificador informado pelo parâmetro `classifier`.
`insertAttributeEval` | Insere na avaliação um atributo informado pelos parâmetros `attribute` e `value`.
`removeAttributeEval` | Remove da avaliação um atributo informado pelo parâmetro `attribute`.
`eval` | Retorna uma matriz de String com 2 linhas e n colunas: cada coluna o nome de um classificador e um valor relerente a probabilidade daquele classificador ser verdadeiro.
`evalAttribute` | Recebe como parâmetro um `attribute` e um `value` e não os adiciona na avaliação posterior; retorna a chamada do método `eval` com o atributo passado por parâmetro.
`evalWithNewAttribute` | Recebe como parâmetro um `attribute` e um `value` e os adiciona na avaliação; retorna a chamada do método `eval`.


### Interface `ITableProducer`
Interface provida por qualquer fonte de dados que os forneça na forma de uma tabela.

Método | Objetivo
-------| --------
`requestAttributes` | Retorna um vetor com o nome de todos os atributos (colunas) da tabela.
`requestInstances` | Retorna uma matriz em que cada linha representa uma instância e cada coluna o valor do respectivo atributo (a ordem dos atributos é a mesma daquela fornecida por `requestAttributes`.

### Interface `IOptimizedDataSet`
Define o recurso (usualmente o caminho para um arquivo em disco) que é a fonte de dados e o otimiza, substituindo valores.

Método | Objetivo
-------| --------
`optimizeAllAttributes` | Otimiza os valores automaticamente até o mínimo de acurácia informada pelo parâmetro `minAccuracy`.
`attributeReplaceValue` | Substitui os valores de um atributo, informados através dos parâmetros `attribute`, `search` e `replacement`.
`classifiersReplaceValue` | Substitui os valores entre os classificadores, informado através dos parâmetros `search` e `replacement`.
`attributeReplaceLimits` | Substitui os valores numéricos de um atributo, informados através dos parâmetros `attribute`, `lessThan`, `moreThan` e `replacement`.
`classifiersReplaceLimits` | Substitui os valores numéricos entre classificadores, informados através dos parâmetros `lessThan`, `moreThan` e `replacement`.
`attributesReplaceValue` | Substitui os valores entre todos os atributs, informado através dos parâmetros `search` e `replacement`.
`attributesReplaceLimits` | Substitui os valores entre todos os atributs numéricos, informado através dos parâmetros `lessThan`, `moreThan` e `replacement`.
