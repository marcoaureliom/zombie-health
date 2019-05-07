package zombieHealth.interfaces;

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
