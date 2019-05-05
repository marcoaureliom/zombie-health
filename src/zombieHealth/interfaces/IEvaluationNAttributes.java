package zombieHealth.interfaces;

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
