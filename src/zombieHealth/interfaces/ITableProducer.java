package zombieHealth.interfaces;
import weka.core.Instances;

public interface ITableProducer {
	String[] requestAttributes();
	String[][] requestInstances();
	Instances requestInstanciasArvore();
}
