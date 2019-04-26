package br.unicamp.mc322.zombieHealth;
import br.unicamp.mc322.zombieHealth.interfaces.*;

import java.lang.Math;

public class Patient implements IPatient {
    
	private int patientN = 0;
    private ITableProducer producer;
    private String attributes[];
    private String patientInstance[];
    
    public void connect(ITableProducer producer) {
        this.producer = producer;

        attributes = this.producer.requestAttributes();
        String instances[][] = producer.requestInstances();

        patientN = (int)(Math.random() * instances.length);
        patientInstance = instances[patientN];
        
        System.out.println("Patient selected: " + patientN);
        System.out.println("Patient's disease: " + patientInstance[attributes.length - 1]);
    }
    
    public String ask(String question) {
        //Procura pelo índice daquela pergunta/sintoma
        for (int a = 0; a < attributes.length - 1; a++)
            if (question.equalsIgnoreCase(attributes[a]))
                return (patientInstance[a].equals("t")) ? "yes" : "no";
        
        return "unknown";
    }

    public boolean finalAnswer(String answer) {
        if (answer.equalsIgnoreCase(patientInstance[attributes.length - 1]))
            return true;
        return false;
    }
}
