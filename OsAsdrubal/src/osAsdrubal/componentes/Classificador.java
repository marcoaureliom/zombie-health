package osAsdrubal.componentes;
import osAsdrubal.interfaces.*;

import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Componente que recebe uma instância e é capaz de classificar usando a biblioteca externa weka.
 * @author Alexandre
 */
public class Classificador implements IClassificador {

	private Instances instancias;
	private J48 tree;
	private IDataSet dataset;

	public Classificador() {
		
	}

	public void setInstances(String dataCSV) {
		
		dataset = new DataSetComponentArvore();
		dataset.setDataSource(dataCSV);
		
		this.instancias = dataset.requestInstanciasArvore();
	}

	public void construaClassificador() {

		if (instancias == null)
			return;

		try {

			String[] options = { "-U" }; // unpruned tree
			tree = new J48(); // new instance of tree
			tree.setOptions(options); // set the options
			tree.buildClassifier(instancias); // build classifier

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void fit() {

		if (instancias == null)
			return;

		try {

			// label instances
			for (int i = 0; i < instancias.numInstances(); i++) {
				double clsLabel = tree.classifyInstance(instancias.instance(i));
				instancias.instance(i).setClassValue(clsLabel);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public String[] predict(Instances test_data) {
		
		if (instancias == null)
			return null;
		
		String[] diag = new String[test_data.numInstances()];
		for (int i = 0; i < test_data.numInstances(); i++) {
			diag[i] = predict(test_data.instance(i));
		}

		return diag;
	}

	public Instances requestInstanciasArvore() {
		if(dataset == null)
			return null;
		return dataset.requestInstanciasArvore();
	}
	
	public String[][] requestInstances() {
		if(dataset == null)
			return null;
		return dataset.requestInstances();
	}
	
	public String[] requestAttributes() {
		if(dataset == null)
			return null;
		return dataset.requestAttributes();
	}
	
	
	public String predict(Instance test_data) {

		if (tree == null || instancias == null)
			return null;

		try {

			int a = (int) tree.classifyInstance(test_data);
			return test_data.attribute(test_data.numAttributes() - 1).value(a);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void imprimaClassificador() {
		
		if (tree != null)
			System.out.println(tree);
	}

	public float accuracy(String[] y_val, String[] y_pred) {
		
		if (instancias == null)
			return -1f;
		
		float a = 0;
		for (int i = 0; i < y_val.length; i++) {
			if (y_val[i].equals(y_pred[i])) {
				a = a + 1;
			}
		}
		return (float) a / y_val.length;
	}

}