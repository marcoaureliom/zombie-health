package zombieHealth;

import zombieHealth.interfaces.IEvaluationNAttributes;

public class EvaluationNAttributes implements IEvaluationNAttributes{

	private String[] attributes, 
					 classifiersEval;
	private String[][] instances, 
					   attributesEval;
	private int nClassifiersEval,
				nAttributesEval,
				maxClassifiersEval,
				maxAttributesEval;
	
	//Construtor com nenhum parâmetro e suas inicializações
	public EvaluationNAttributes() {
		nClassifiersEval = 0;
		nAttributesEval = 0;
		maxClassifiersEval = 10;
		maxAttributesEval = 50;
		instances = null;
		attributes = null;
		classifiersEval = new String[maxClassifiersEval];
		attributesEval = new String[maxAttributesEval][2];
	}
	
	//Instâncias que serão consideradas
	public void setInstances(String[][] instances) {
		this.instances = instances;
	}
	//Atributos das instâncias que serão consideradas
	public void setAttibutes(String[] attributes) {
		this.attributes = attributes;
	}
	
	//Insira um novo classificador
	public void insertClassifierEval(String classifierEval){
		
		if(classifierEval == null || nClassifiersEval > maxClassifiersEval-1)
			return;
		
		//Verifique se classificador já não está no vetor
		for(int i = 0; i < nClassifiersEval; i++)
			if(classifiersEval[i].equalsIgnoreCase(classifierEval))
				return;
		
		classifiersEval[nClassifiersEval++] = classifierEval;
	}
	
	//Remova um classificador da avaliação
	public void removeClassifierEval(String classifier){
		
		if(nClassifiersEval<1 || classifier == null)
			return;
		
		for(int i = 0; i < nClassifiersEval; i++)
			if(classifiersEval[i].equalsIgnoreCase(classifier)) {
				for(int j = i; j < nClassifiersEval-1; j++)
					classifiersEval[j] = classifiersEval[j+1];
				
				nClassifiersEval--;
				break;
			}
	}
	
	//Insira um novo atributo ou altere o valor de um já existente
	public void insertAttributeEval(String attribute, String value){
		
		if(attribute == null || value == null)
			return;
		
		//Verifique se atributo já não está no vetor.
		//Caso esteja, o novo valor é dado a ele.
		for(int i = 0; i < nAttributesEval; i++)
			if(attributesEval[i][0].equalsIgnoreCase(attribute)) {
				attributesEval[i][1] = value;
				return;
			}
		
		//Caso não esteja, verifique possibilidade de adicionar novo atributo.
		if(nAttributesEval > maxAttributesEval-1)
			return;
				
		attributesEval[nAttributesEval][0] = attribute;
		attributesEval[nAttributesEval][1] = value;
		nAttributesEval++;
	}
	
	//Remova um atributo
	public void removeAttributeEval(String attribute){
		
		if(nAttributesEval<1 || attribute == null)
			return;
		
		for(int i = 0; i < nAttributesEval; i++)
			if(attributesEval[i][0].equalsIgnoreCase(attribute)) {
				for(int j = i; j < nAttributesEval-1; j++) {
					attributesEval[j][0] = attributesEval[j+1][0];
					attributesEval[j][1] = attributesEval[j+1][1];
				}
				
				nAttributesEval--;
				break;
			}
	}
	
	//Avalie após inserir novo atributo
	public String[][] evalWithNewAttribute(String attribute, String value){
		insertAttributeEval(attribute, value);
		return eval();
	}
	
	public String[][] eval() {

		if (instances == null || attributes == null || nClassifiersEval < 1 || nAttributesEval < 1)
			return null;

		String result[][] = new String[nClassifiersEval][2];
		
		boolean found = true;
		int atts = 0,
			attsAndClass = 0;
		
		//Para cada classificador classifiersEval[]
		for(int k = 0; k < nClassifiersEval; k++) {
			//Zere os contadores.
			atts = attsAndClass = 0;
			//Para cada instância:
			for(int i = 0; i < instances.length; i++) {
				found = true;
				//Para cada atributo em attributes[]
				for(int j = 0; j < attributes.length-1; j++) {
					//Para cada atributo que queremos avaliar em attributesEval[][]
					for(int l = 0; l < nAttributesEval; l++) 
						if((attributes[j]).equalsIgnoreCase(attributesEval[l][0])){
							if(!((instances[i][j]).equalsIgnoreCase(attributesEval[l][1])))
								found = false;
	
							break;
						}
					if(!found)
						break;
				}
				if(found) {
					//Encontramos instância com todos os atributos iguais.
					atts++;
					
					//Se, além dos atributos iguais o classificador da instânci for igual ao classifiersEval[k]:
					if((instances[i][instances[0].length-1]).equalsIgnoreCase(classifiersEval[k]))
						//Encontramos uma ocorrência que todos os atributos de attributes na instância i
						//são iguais a attributesEval[][] e possuem a classificação classifiersEval[k]
						attsAndClass++;
				}
			}
			
			result[k][0] = classifiersEval[k];
			
			if(atts == 0)
				result[k][1] = Double.toString(-1.0);
			else
				result[k][1] = Double.toString((double) attsAndClass/atts);

		}				

		return result;
	}
	public String[][] evalAttribute(String attribute, String value){
		
		if (instances == null || attributes == null || attribute == null || value == null || 
			nClassifiersEval < 1)
			return null;

		String result[][] = new String[nClassifiersEval][2];
		
		int atts = 0,
			attsAndClass = 0,
			indexAttributeEval = -1;
		
		//Para cada atributo em attributes[]
		for(int j = 0; j < attributes.length-1; j++)
			if((attributes[j]).equalsIgnoreCase(attribute)) {
				indexAttributeEval = j;
				break;
			}
		
		if(indexAttributeEval == -1)
			return null;		
		
		//Para cada classificador classifiersEval[]
		for(int k = 0; k < nClassifiersEval; k++) {
			//Zere os contadores.
			atts = attsAndClass = 0;
			//Para cada instância:
			for(int i = 0; i < instances.length; i++)
				if((instances[i][indexAttributeEval]).equalsIgnoreCase(value)) {
					//Encontramos instância com atributo igual a attribute.
					atts++;
					if((instances[i][instances[0].length-1]).equalsIgnoreCase(classifiersEval[k]))
						//Encontramos uma ocorrência que o atributo na instância i
						//é igual a attribute e possui a classificação classifiersEval[k]
						attsAndClass++;
					
				}
			result[k][0] = classifiersEval[k];
			
			if(atts == 0)
				result[k][1] = Double.toString(-1.0);
			else
				result[k][1] = Double.toString((double) attsAndClass/atts);
			
		}				

		return result;
	}

}
