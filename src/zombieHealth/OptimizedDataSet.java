package zombieHealth;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import zombieHealth.interfaces.IOptimizedDataSet;

public class OptimizedDataSet implements IOptimizedDataSet{

	private String dataSource = null;
	private String[] attributes = null;
	private String[][] instances = null;
	  
	private Instances instancesTree = null;
	private int classifierIndex;
	
	public OptimizedDataSet() {
	
	}
	
	public void setClassifierIndex(int cI) {
		classifierIndex = cI;
	}
	
	@Override
	public String getDataSource() {
		return dataSource;
	}

	@Override
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	    
	    if (dataSource == null) {
	    	
	    	attributes = null;
	    	instances = null;
	    	instancesTree = null;
	    	
	    	return;
	    }
	    
	    readDS();		
	}

	@Override
	public String[] requestAttributes() {
		return attributes;
	}

	@Override
	public String[][] requestInstances() {
		return instances;
	}
	
	private void readDS() {
	    try {
	    	
	      attributes = null;
	      instances = null;
	      
	      DataSource weka = new DataSource(dataSource);
	      instancesTree = weka.getDataSet();
	      
	      //Obtendo o índice do atributo de diagnóstico ou de classificação
	      if (instancesTree.classIndex() == -1)
	    	  instancesTree.setClassIndex(instancesTree.numAttributes() - 1);
	      classifierIndex = instancesTree.classIndex();
	       
	      //Convertendo valores de atributos e instâncias
	      int atributos = instancesTree.numAttributes(),
	    	  instancias = instancesTree.size();
	      
	      attributes = new String[atributos];
	      instances = new String[instancias][atributos];
	      
	      for (int i = 0; i < atributos; i++)
	    	  attributes[i] = instancesTree.attribute(i).name();
	      
	      for (int i = 0; i < instancias; i++){
	        for (int a = 0; a < atributos; a++)
	        	if(instancesTree.attribute(a).isNominal())
	        		instances[i][a] = instancesTree.get(i).stringValue(a);
	        	else
	        		instances[i][a] = Double.toString(instancesTree.get(i).value(a));
	      }
	      //
	      
	    } catch (Exception erro) {
	      erro.printStackTrace();
	    }
	  }


	@Override
	public void optimizeAttribute(String attribute, double minAccuracy) {
		
		int attIndex = attibuteIndex(attribute);
		if(attIndex == -1)
			return;
		
		int nClassifiers = 0, maxClassifiers = 20;
		String classifiers[] = new String[maxClassifiers];
		for(int j = 0; j < maxClassifiers; j++) 
			classifiers[j] = "";
			
		
		for(int j = 0; j < instances.length; j++) {
			int k = 0;
			for(; k < nClassifiers && !(classifiers[k].equalsIgnoreCase(instances[classifierIndex][j])); k++);
			if(k > nClassifiers) 
				classifiers[nClassifiers++] = instances[classifierIndex][j];
		}
		
		for(int j = 0; j < nClassifiers; j++) {
			//umvetor = optimizeAttributeClassifier(classifiers[j], attIndex);
			//copie a instancia ou já mantenha sempre uma cópia
			//analise o Umvetor
				//se há ex 90% de unico valor
					//substitua os outros 10%
						//analise a eficiência da arvore
							//se acima da acuracia mínima, 
								//realize a mudança de outro att na instancia alterada
								//é necessario um array de atts já visitados para funcao recursiva
								//a melhor instancia final será a que tiver maior n. de atts modificados e está acima da acuracia minima
		}
		
	}

	private void optimizeAttributeClassifier(String classifier, int attIndex) {
		int nValues = 0, maxValues = 10;
		String values[] = new String[maxValues];
		int valuesCount[] = new int[maxValues];
		for(int j = 0; j < maxValues; j++) {
			values[j] = "";
			valuesCount[j] = 0;
		}
		for(int j = 0; j < instances.length; j++)
			if(instances[attIndex][classifierIndex].equalsIgnoreCase(classifier)) {
				int k = 0;
				for(; k < nValues && !(values[k].equalsIgnoreCase(instances[attIndex][j])); k++);
				if(k > nValues) {
					values[nValues] = instances[attIndex][j];
					valuesCount[nValues++]++;
				}
				else 
					valuesCount[k-1]++;
			}
		//retorne o vetor com a prim. pos sendo o número de elementos (editar isto);
	}
	
	@Override
	public void optimizeAllAttributes(double minAccuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaceValue(String attribute, String search, String replacement) {
		
		//Encontre a posição do atributo
		int i = attibuteIndex(attribute);
		
		//Substitua, naquele atributo, um valor por outro
		if(i != -1)
			for(int j = 0; j < instances.length; j++)
				if(instances[i][j].equalsIgnoreCase(search))
					instances[i][j] = replacement;
		
	}

	@Override
	public void classifiersReplaceValue(String search, String replacement) {
		//Substitua, naquele atributo, um valor por outro
		for(int j = 0; j < instances.length; j++)
			if(instances[classifierIndex][j].equalsIgnoreCase(search))
				instances[classifierIndex][j] = replacement;
	}

	@Override
	public void attributeReplaceLimits(String attribute, double lessThan, double moreThan, String replacement) {
		
		//Encontre a posição do atributo
		int i = attibuteIndex(attribute);
		
		//Substitua, naquele atributo, um valor por outro
		if(i != -1)
			for(int j = 0; j < instances.length; j++)
				if(Double.parseDouble(instances[i][j]) < lessThan
				&& Double.parseDouble(instances[i][j]) > moreThan)
					instances[i][j] = replacement;
		
	}

	@Override
	public void classifiersReplaceLimits(double lessThan, double moreThan, String replacement) {
		for(int j = 0; j < instances.length; j++)
			if(Double.parseDouble(instances[classifierIndex][j]) < lessThan
			&& Double.parseDouble(instances[classifierIndex][j]) > moreThan)
				instances[classifierIndex][j] = replacement;
	}

	@Override
	public void attributesReplaceValue(String search, String replacement) {
		for(int j = 0; j < instances.length; j++)
			for(int k = 0; k < instances[0].length && k != classifierIndex; k++)
				if(instances[j][k].equalsIgnoreCase(search))
					instances[j][k] = replacement;
	}

	@Override
	public void attributesReplaceLimits(double lessThan, double moreThan, String replacement) {
		for(int j = 0; j < instances.length; j++)
			for(int k = 0; k < instances[0].length && k != classifierIndex; k++)
				if(Double.parseDouble(instances[j][k]) < lessThan
				&& Double.parseDouble(instances[j][k]) > moreThan)
					instances[j][k] = replacement;
	}

	private int attibuteIndex(String attribute) {
		//Encontre a posição do atributo
		int i = 0;
		for(; !(attributes[i].equalsIgnoreCase(attribute) && i < attributes.length); i++);
		
		if(attributes[i-1].equalsIgnoreCase(attribute) && (i-1) != classifierIndex)
			return i-1;
		else 
			return -1;
	}
	
	
	
}
