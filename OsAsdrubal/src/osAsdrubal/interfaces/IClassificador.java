package osAsdrubal.interfaces;

import weka.core.Instances;
import weka.core.Instance;

/*  Interface de modelo de Machine Learning para classificacao
 *	Nessa Interface, temos metodos padrao de Machine Learning, utilizados para treinar o modelo sobre uma base de dados
 *  e predizer o resultado para alguns dados inseridos.
 * @author Alexandre 
 */

public interface IClassificador {
	public void construaClassificador();/*Metodo de Inicializacao do classificador (Recomenda-se a arvore weka.classifiers.trees.J48)*/
	public void fit();/*Metodo para Treinar o modelo com os dados da base*/
	public String[] predict(Instances test_data);/*Metodo para predizer n dados entrados na forma de uma matriz - Retorna os targets de cada um dos dados em um vetor*/
	public String predict(Instance test_data);/*Metodo para predizer 1 dado - Retorna o target do dado inserido*/
	public void setInstances(String dataCSV);/*Metodo utilizado para definir os dados de treino do classificador*/
	public abstract float accuracy (String[] y_val, String[] y_pred);
	
	public void imprimaClassificador();/*Método para imprimir o classificador*/
	public Instances requestInstanciasArvore();
	public String[][] requestInstances();
	public String[] requestAttributes();
	
}

