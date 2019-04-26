package zombieHealth;
import zombieHealth.interfaces.*;


public class Doctor implements IDoctor {    

    private ITableProducer producer;
    private IResponder responder;
    
    private Classificador classificador;
    
    @Override
    public void connect(ITableProducer producer) {
        this.producer = producer;
    }
    
    @Override
    public void connect(IResponder responder) {
        this.responder = responder;
    }
    
    @Override
    public void startInterview() {
        
        if(responder == null)
            return;
        
        String attributes[] = producer.requestAttributes(),
               instances[][] = producer.requestInstances();
        
        classificador = new Classificador(producer.requestInstanciasArvore());
        classificador.construaArvore();
        classificador.treine();
        classificador.imprimaAvaliacao();
        classificador.imprimaClassificador();
   
       
        String diagn = classificador.classifique(3);
        System.out.println("Disease guess: " +diagn);
        boolean resultado = responder.finalAnswer(diagn);
        System.out.println("Result: " + ((resultado) ? "I am right =)" : "I am wrong =("));
  
        
        /*//Respostas do paciente.
        //Nenhuma pergunta é feita mais de uma vez.
        String[] respostas = new String[attributes.length-1];
        for(int i = 0; i < attributes.length-1; i++){
            respostas[i] = responder.ask(attributes[i]);
            System.out.println("Question: " + respostas[i]);
        }
        
        //Para cada instância no CSV:
        for (int i = 0; i < instances.length; i++)
            //Para cada atributo/pergunta:
            for (int j= 0; j < attributes.length; j++)
                //Se d for o índice da última coluna, chegamos a um diagnóstico
                if(j == attributes.length-1){

                    //Verifica se este diagnóstico já foi dado antes
                    for(int u = 0; u < diagnosticosN; u++)
                        if(instances[i][j].equalsIgnoreCase(diagnosticos[u])){
                            diagnosticoNovo = false;
                            break;
                        }
                            
                    //Registre o novo diagnóstico
                    if(diagnosticoNovo && diagnosticosN < maxDiagnosticos)
                        diagnosticos[diagnosticosN++] = instances[i][j];
                  
                }else               
                    //Confira se paciente tem a resposta diferente da instância i,j à resposta j:
                    //Se respostas diferem, vamos para próxima instância. 
                    //Porém, se a resposta for "unknown", a pergunta não elimina possível diagnóstico
                    //e então continuamos na mesma instância.
                    if(instances[i][j].equalsIgnoreCase("t") != respostas[j].equalsIgnoreCase("yes") 
                       && !respostas[j].equalsIgnoreCase("unknown"))
                        break;
        
        //Resultado
        //Problema: responder.finalAnswer() deve ser acionado uma única vez ao final da entrevista,
        //porém, no caso do arquivo CSV disponibilizado, 
        //(I) existem diferentes diagnósticos para um mesmo conjunto de respostas.
        for(int i = 0; i < diagnosticosN; i++){
            System.out.println("Disease guess: " + diagnosticos[i]);
            resultado = responder.finalAnswer(diagnosticos[i]);
            System.out.println("Result: " + ((resultado) ? "I am right =)" : "I am wrong =("));
        }*/
        
        //Se o arquivo CSV usado na avaliação não permite (I),
        //o código acima deve ser mudado para:
        /*
        System.out.println("Disease guess: " + diagnosticos[0]);
        System.out.println("Result: " + ((responder.finalAnswer(diagnosticos[0])) ? "I am right =)" : "I am wrong =("));
        */
    }
    
}
