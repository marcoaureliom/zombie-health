package br.unicamp.mc322.zombieHealth;
import br.unicamp.mc322.zombieHealth.interfaces.*;

public class ZombieHealth {

	public static void main(String[] args) {
	
		// instanciando o componente DataSet
		//IDataSet dataset = new DataSetComponent();
		//dataset.setDataSource("data/zombie-health-spreadsheet-ml-training.csv");
		
		IDataSet dataset = new DataSetComponentArvore();
		dataset.setDataSource("data/zombie-health-spreadsheet-ml-training.csv");

		//System.out.println(dataset);
		// instanciando o componente paciente
		IPatient aPatient = new Patient();

		// conectando-o no componente DataSet
		aPatient.connect(dataset);

		// instanciando o componente doutor
		IDoctor doctor = new Doctor();

		// conectando-o ao componente DataSet
		doctor.connect(dataset);

		// conectando-o ao componente paciente
		doctor.connect(aPatient);
		
		// executando a entrevista
		doctor.startInterview();

	}

}

