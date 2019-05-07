# Em edição

# Equipe *Os Asdrubal*

A documentação será feita em Markdown, vide detalhes sobre ele em: https://guides.github.com/features/mastering-markdown/

E mais especificamente sobre tabelas em: https://help.github.com/en/articles/organizing-information-with-tables

Segue abaixo o modelo de como deve ser documentado um componente. Tudo o que for indicado entre `<...>` indica algo que deve ser substituído pelo indicado.

# Componente `EvaluationNAttributes`

Campo | Valor
----- | -----
Classe | `zombieHealth.interfaces.IEvaluationNAttributes`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | `Probabilidade de *n* classificadores serem verdadeiros dado os valores de *i* atributos aplicados simultaneamente nos **n** classificadores.`
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

## Detalhamento das Interfaces

### Interface `<nome da interface>`
`<papel da interface>`.

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Exemplo:

### Interface `ITableProducer`
Interface provida por qualquer fonte de dados que os forneça na forma de uma tabela.

Método | Objetivo
-------| --------
`requestAttributes` | Retorna um vetor com o nome de todos os atributos (colunas) da tabela.
`requestInstances` | Retorna uma matriz em que cada linha representa uma instância e cada coluna o valor do respectivo atributo (a ordem dos atributos é a mesma daquela fornecida por `requestAttributes`.

### Interface `IDataSource`
Define o recurso (usualmente o caminho para um arquivo em disco) que é a fonte de dados.

Método | Objetivo
-------| --------
`getDataSource` | Retorna o caminho da fonte de dados.
`setDataSource` | Define o caminho da fonte de dados, informado através do parâmetro `dataSource`.
