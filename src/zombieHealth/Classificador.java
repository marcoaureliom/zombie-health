package zombieHealth;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.attributeSelection.CorrelationAttributeEval;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

public class Classificador {

	private Instances instancias;
	private J48 tree;
	private Evaluation evaluation;

	public Classificador(Instances instancias) {
		this.instancias = instancias;
	}

	public void construaArvore() {

		if (instancias == null)
			return;

		try {

			String[] options = {"-U"}; // unpruned tree
			tree = new J48(); // new instance of tree
			tree.setOptions(options); // set the options
			tree.buildClassifier(instancias); // build classifier
			
			avalie();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}

	public void treine() {

		if (instancias == null)
			return;

		try {

			// label instances
			for (int i = 0; i < instancias.numInstances(); i++) {
				double clsLabel = tree.classifyInstance(instancias.instance(i));
				instancias.instance(i).setClassValue(clsLabel);
			}
		
			avalie();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void avalie() {

		if (instancias == null)
			return;

		try {
			
			evaluation = new Evaluation(instancias);
			evaluation.crossValidateModel(tree, instancias, 10, new Random(1));

			/*
			CorrelationAttributeEval cor = new CorrelationAttributeEval();
			cor.buildEvaluator(instancias);
			for (int i = 0; i < instancias.numAttributes() - 1; i++)
				System.out.println(instancias.attribute(i) + ", " + cor.evaluateAttribute(i));
			*/
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void imprimaAvaliacao() {
		
		System.out.println(evaluation.toSummaryString("\nResults\n======\n", false));
		System.out.println("NumFolds: "+tree.getNumFolds());
		
	}
	
	public String classifique(int i) {

		if (tree == null || instancias == null)
			return null;

		try {
			
			int a = (int) tree.classifyInstance(instancias.instance(i));
			return instancias.attribute(i).name();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void imprimaClassificador() {
		if(tree != null)
			System.out.println(tree);
	}

}
