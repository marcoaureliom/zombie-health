package br.unicamp.mc322.zombieHealth;
import br.unicamp.mc322.zombieHealth.interfaces.*;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class DataSetComponentArvore implements IDataSet {

  private String dataSource = null;
  private String[] attributes = null;
  private String[][] instances = null;
  
  private Instances instanciasArvore = null;
  
  public DataSetComponentArvore() {
    /* nothing */
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
    
    if (dataSource == null) {
    	
    	attributes = null;
    	instances = null;
    	instanciasArvore = null;
    	
    	return;
    }
    
    readDS();    
  }
  
  public String[] requestAttributes() {
    return attributes;
  }
  
  public String[][] requestInstances() {
	 return instances;
  }
  
  
  public Instances requestInstanciasArvore() {
    return instanciasArvore;
  }
  
  private void readDS() {
    try {
    	
      attributes = null;
      instances = null;
      
      DataSource weka = new DataSource(dataSource);
      instanciasArvore = weka.getDataSet();
      
      //Obtendo o índice do atributo de diagnóstico ou de classificação
      if (instanciasArvore.classIndex() == -1)
    	  instanciasArvore.setClassIndex(instanciasArvore.numAttributes() - 1);
      
      //Convertendo valores de atributos e instâncias
      int atributos = instanciasArvore.numAttributes(),
    	  instancias = instanciasArvore.size();
      
      attributes = new String[atributos];
      instances = new String[instancias][atributos];
      
      for (int i = 0; i < atributos; i++)
    	  attributes[i] = instanciasArvore.attribute(i).name();
      
      for (int i = 0; i < instancias; i++){
        for (int a = 0; a < atributos; a++)
        	if(instanciasArvore.attribute(a).isNominal())
        		instances[i][a] = instanciasArvore.get(i).stringValue(a);
        	else
        		instances[i][a] = Double.toString(instanciasArvore.get(i).value(a));
      }
      //
      
    } catch (Exception erro) {
      erro.printStackTrace();
    }
  }

}
