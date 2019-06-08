package osAsdrubal;

import osAsdrubal.componentes.*;
import osAsdrubal.interfaces.*;

public class OsAsdrubal {

	public static void main(String[] args) {

		/*
		 * 
		 *  
		 * Classificador 
		 *  
		 *  
		 *  */
		//Código usando AbstractOsAsdrubal
		AbstractOsAsdrubal classificadorFabrica = GeneralOsAsdrubal.crieOsAsdrubal("Classificador");

		//Classificador
		IClassificador classificador = classificadorFabrica.crieClassificador();
		
		classificador.setInstances("data/zombie-health-new-cases500.csv");
		classificador.construaClassificador();
		classificador.fit();
		
		String[] diag = classificador.predict(classificador.requestInstanciasArvore());
		String[] y_val = new String[classificador.requestInstances().length];
		for(int i=0;i<classificador.requestInstanciasArvore().numInstances();i++) {
			y_val[i] = classificador.requestInstances()[i][classificador.requestAttributes().length-1];
			System.out.println("Pac"+i+":  "+diag[i]+"  "+classificador.requestInstances()[i][classificador.requestAttributes().length-1]);
		}		
	
		classificador.imprimaClassificador();
		
		System.out.println("accuracy = "+classificador.accuracy(y_val,diag));
		/*Fim do teste do classificador*/
		
		/*
		 * 
		 *  
		 * Grafico 
		 *  
		 *  
		 *  */
		//Código usando AbstractOsAsdrubal
		AbstractOsAsdrubal graficoFabrica = GeneralOsAsdrubal.crieOsAsdrubal("Grafico");

		//Graficos
		IGrafico graficoPizza = graficoFabrica.crieGraficoDePizza();
		IGrafico graficoBarra = graficoFabrica.crieGraficoDeBarra();
		
		graficoPizza.crieGrafico(new String[] {"x1","x2","x3"}, new int[] {1,2,3}, "t1", "t2", "t3");
		graficoBarra.crieGrafico(new String[] {"x1","x2","x3"}, new int[] {1,2,3}, "t1", "t2", "t3");
		/*Fim do teste do grafico*/
		
		/*
		 * 
		 *  
		 * ZombieWEB 
		 *  
		 *  
		 *  */
		//Código usando AbstractOsAsdrubal
		AbstractOsAsdrubal zw = GeneralOsAsdrubal.crieOsAsdrubal("ZombieWEB");

		//ZombieWEB
		IZombieWEB ZW = zw.crieZombieWEB();

		ZW.setNomeArquivo("UmExemplo");
		String tituloArv = "Zombie Health 500 casos";
		ZW.crieArvore(tituloArv);
		ZW.insiraElementoArvore(tituloArv, -1, 1, "Raiva severa?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
			ZW.insiraElementoArvore(tituloArv, 1, 11, "Pele azul?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
				ZW.insiraElementoArvore(tituloArv, 11, 111, "Perda de membro?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
					ZW.insiraElementoArvore(tituloArv, 111, 1111, "Briga", null);
					ZW.insiraElementoArvore(tituloArv, 111, 1112, "Dor no peito?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
						ZW.insiraElementoArvore(tituloArv, 1112, 11121, "Briga", null);
						ZW.insiraElementoArvore(tituloArv, 1112, 11122, "T-Virus", null);
				ZW.insiraElementoArvore(tituloArv, 11, 112, "Dedo tremendo?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
					ZW.insiraElementoArvore(tituloArv, 112, 1121, "T-Virus", null);
					ZW.insiraElementoArvore(tituloArv, 112, 1122, "Briga", null);
			ZW.insiraElementoArvore(tituloArv, 1, 12, "Dedo tremendo?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
				ZW.insiraElementoArvore(tituloArv, 12, 121, "Pele azul?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
					ZW.insiraElementoArvore(tituloArv, 121, 1211, "T-Virus", null);
					ZW.insiraElementoArvore(tituloArv, 121, 1212, "Zulombriga", null);
				ZW.insiraElementoArvore(tituloArv, 12, 122, "Dor no peito?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
					ZW.insiraElementoArvore(tituloArv, 122, 1221, "Infecção viral", null);
					ZW.insiraElementoArvore(tituloArv, 122, 1222, "Olho vermelho?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
						ZW.insiraElementoArvore(tituloArv, 1222, 12221, "Perda de membro?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
							ZW.insiraElementoArvore(tituloArv, 12221, 122211, "Infecção bacteriana", null);
							ZW.insiraElementoArvore(tituloArv, 12221, 122212, "Língua amarela?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
								ZW.insiraElementoArvore(tituloArv, 122212, 1222121, "Paralisia?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
									ZW.insiraElementoArvore(tituloArv, 1222121, 12221211, "Infecção bacteriana", null);
									ZW.insiraElementoArvore(tituloArv, 1222121, 12221212, "Gripe Aviária", null);
								ZW.insiraElementoArvore(tituloArv, 122212, 1222122, "Gripe Aviária", null);
						ZW.insiraElementoArvore(tituloArv, 1222, 12222, "Língua amarela?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
							ZW.insiraElementoArvore(tituloArv, 12222, 122221, "Infecção bacteriana", null);
							ZW.insiraElementoArvore(tituloArv, 12222, 122222, "Pele azul?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
								ZW.insiraElementoArvore(tituloArv, 122222, 1222221, "Paralisia?", new String[] { "Sim", "#5cbd79", "Não", "#5b87d4" });
									ZW.insiraElementoArvore(tituloArv, 1222221, 12222211, "Infecção bacteriana", null);
									ZW.insiraElementoArvore(tituloArv, 1222221, 12222212, "Gripe Aviária", null);
								ZW.insiraElementoArvore(tituloArv, 122222, 1222222, "Infecção viral", null);
							
		ZW.crieClassificador(tituloArv);
		ZW.criePaginaHTML();
		ZW.abraPagina();
		/*Fim do teste ZombieWEB */
		
	}

}

