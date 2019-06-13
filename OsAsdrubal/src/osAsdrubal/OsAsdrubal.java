package osAsdrubal;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import osAsdrubal.componentes.*;
import osAsdrubal.interfaces.*;

//Componentes de outras equipes
//Clube do Hardware
import pt.clubedohardware.userinterface.*;

/**
 * Classe principal.
 *
 * @author Marco Aurélio
 * Janela com acesso aos principais componentes desenvolvidos.
 */
public class OsAsdrubal {

	public static void main(String[] args) {

		//Instância necessária para que usuário possa rodar a entrevista posteriormente
		IZombieWEB ZW = rodeZombieWEB();		
		try {
			
			Image image = ImageIO.read(new File("imagens/background.png"));
			JPanel j = new JPanelWithBackground(image); 
			j.setLayout(new FlowLayout());
			
			//Botões
			JButton btnC = new JButton("Classificador");
			btnC.addActionListener(new ActionListener(){
				   public void actionPerformed(ActionEvent e){
				        rodeClassificador();
				   }
				});
			btnC.setIcon(new ImageIcon(ImageIO.read(new File("imagens/btnClassificador.png"))));
			
			
			JButton btnZ = new JButton("ZombieWEB");
			btnZ.addActionListener(new ActionListener(){
				   public void actionPerformed(ActionEvent e){
					   ZW.abraPagina();
				   }
				});
			btnZ.setIcon(new ImageIcon(ImageIO.read(new File("imagens/btnZombieWEB.png"))));
			
			JButton btnG = new JButton("Graficos");
			btnG.addActionListener(new ActionListener(){
				   public void actionPerformed(ActionEvent e){
				        rodeGraficos();
				   }
				});
			btnG.setIcon(new ImageIcon(ImageIO.read(new File("imagens/btnGraficos.png"))));
			
			JButton btnE = new JButton("Entrevista");
			btnE.addActionListener(new ActionListener(){
				   public void actionPerformed(ActionEvent e){
					  rodeAnimacao(ZW);
				   }
				});
			btnE.setIcon(new ImageIcon(ImageIO.read(new File("imagens/btnEntrevista.png"))));
			
			//Aplique para todos os botões
			JButton [] botoes = {btnC, btnZ, btnG, btnE};
			for(int i = 0; i< botoes.length; i++) {
				botoes[i].setMargin(new Insets(0, 0, 0, 0));
				botoes[i].setBorder(null);
				botoes[i].setBorderPainted(false); 
				botoes[i].setContentAreaFilled(false); 
				botoes[i].setFocusPainted(false); 
				botoes[i].setOpaque(false);
				botoes[i].setPreferredSize(new Dimension(95, 95));
				botoes[i].setVerticalAlignment(SwingConstants.CENTER);
				botoes[i].setHorizontalAlignment(SwingConstants.CENTER);
				j.add(botoes[i]);
			}
			
			//Relativo à janela
			JFrame f = new JFrame("osAsdrubal"); 
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(500, 500);
			f.setResizable(false);
			f.add(j); 
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			
		}catch(Exception e) {
			System.out.println("Erro ao criar tela com botões."); 
		}
	}

	public static int procureStringVetor(String[] strArray, String str) {
		for (int j = 0; j < strArray.length; j += 2) {
			if (strArray[j].equalsIgnoreCase(str))
				return j / 2;
		}

		return -1;
	}

	public static INoArvore procureArvore(INoArvore elemento, String resp) {

		if (elemento.getFilhos().size() > 0 && elemento.getLegInf().length > 0) {
			int po = procureStringVetor(elemento.getLegInf(), resp);
			if (po != -1)
				return elemento.getFilhos().get(po);
		}

		return elemento;

	}
	
	public static void rodeAnimacao(IZombieWEB ZW) {
		
		if(ZW == null) {
			System.out.println("Árvore de ZombieWEB não criada.");
			return;
		}
		
		INoArvore arvore = ZW.getArvore("Zombie Health 500 casos");
		ArrayList<String> instanciaLista = new ArrayList<String>();
		ArrayList<String> falasLista = new ArrayList<String>();
		ArrayList<String> personagemLista = new ArrayList<String>();
		
		IDataSet dataset = new DataSetComponentArvore();
		dataset.setDataSource("data/zombie-health-new-cases500.csv");
		
		int sorteio = (int) (Math.random()*dataset.requestInstances().length);
		String [] instanciaSorteada = dataset.requestInstances()[sorteio];
		String[] perguntas = dataset.requestAttributes();
		
		for (int u = 0; u < perguntas.length-1; u++) {
			instanciaLista.add(perguntas[u].toUpperCase());

			if (instanciaSorteada[u].equals("1.0"))
				instanciaLista.add("Sim");
			else 
				instanciaLista.add("Não");
			
		}	
		//System.out.println(instanciaLista.toString());
		
		// Instanciando a animação
		IAnimationC animacao = new AnimationC();

		// Configurações
		String nomePaciente = "Shallow", nomeMedico = "Asdrubal Socio";
		animacao.setWindowName("Doutores Asdrubal");
		animacao.setDocName(nomeMedico);
		animacao.setPacientName(nomePaciente);
		animacao.setTempo("fast");

		//Diálogo
		falasLista.add("Olá novamente, doutor!");
		personagemLista.add("pacient");
		falasLista.add("Olá, " + nomePaciente + "! O que faz estar aqui now?");
		personagemLista.add("doctor");
		falasLista.add("Sempre a mesma piada. Não estou bem e não sei o que há comigo.");
		personagemLista.add("pacient");

		String resultado = "?", resposta = "010101";
		INoArvore re = arvore;
		while (resultado.contains("?")) {

			if(re.getFilhos()==null) return;
			
			re = procureArvore(re, resposta);
			resultado = re.getTexto();

			if (resultado.endsWith("?")) {
				falasLista.add("Você está com ou tem " + resultado);
				personagemLista.add("doctor");

				if (instanciaLista.contains(resultado.substring(0, resultado.length() - 1).toUpperCase())) {
					resposta = instanciaLista.get(instanciaLista.indexOf(resultado.substring(0, resultado.length() - 1).toUpperCase()) + 1);
					falasLista.add(resposta + ".");
					personagemLista.add("pacient");
				} else {
					System.out.println("Erro. Não há na instancia: " + resultado.substring(0, resultado.length() - 1));
					return;
				}
			} else {
				falasLista.add("Seu diagnóstico é " + resultado + ".");
				personagemLista.add("doctor");
			}
		}
		falasLista.add("Obrigado, doutor.");
		personagemLista.add("pacient");

		// Vetores de falas e das personagens
		String[] falas = new String[personagemLista.size()];
		String[] personagem = new String[personagemLista.size()];
		for (int i = 0; i < falasLista.size(); i++) {
			falas[i] = falasLista.get(i);
			personagem[i] = personagemLista.get(i);
		}
		
		/*Ver diálogo sem rodar a aplicação
		 * for (int i = 0; i < falas.length; i++)
			System.out.println(falas[i]);
		for (int i = 0; i < personagem.length; i++)
			System.out.println(personagem[i]);*/
		
		// Rode a animação
		animacao.story(falas, personagem);
		
	}

	public static IZombieWEB rodeZombieWEB() {
		// Código usando AbstractOsAsdrubal
		AbstractOsAsdrubal zw = GeneralOsAsdrubal.crieOsAsdrubal("ZombieWEB");

		// ZombieWEB
		IZombieWEB ZW = zw.crieZombieWEB();
		
		ZW.setNomeArquivo("osAsdrubal");
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
		return ZW;
	}

	public static void rodeGraficos() {
		// Código usando AbstractOsAsdrubal
		AbstractOsAsdrubal graficoFabrica = GeneralOsAsdrubal.crieOsAsdrubal("Grafico");

		// Graficos
		IGrafico graficoPizza = graficoFabrica.crieGraficoDePizza();
		IGrafico graficoBarra = graficoFabrica.crieGraficoDeBarra();

		graficoPizza.crieGrafico(new String[] { "x1", "x2", "x3" }, new int[] { 1, 2, 3 }, "t1", "t2", "t3");
		graficoBarra.crieGrafico(new String[] { "x1", "x2", "x3" }, new int[] { 1, 2, 3 }, "t1", "t2", "t3");
		
	}
	
	public static void rodeClassificador() {
		// Código usando AbstractOsAsdrubal
		AbstractOsAsdrubal classificadorFabrica = GeneralOsAsdrubal.crieOsAsdrubal("Classificador");

		// Classificador
		IClassificador classificador = classificadorFabrica.crieClassificador();

		classificador.setInstances("data/zombie-health-new-cases500.csv");
		classificador.construaClassificador();
		classificador.fit();

		String[] diag = classificador.predict(classificador.requestInstanciasArvore());
		String[] y_val = new String[classificador.requestInstances().length];
		for (int i = 0; i < classificador.requestInstanciasArvore().numInstances(); i++) {
			y_val[i] = classificador.requestInstances()[i][classificador.requestAttributes().length - 1];
			System.out.println("Pac" + i + ":  " + diag[i] + "  "
					+ classificador.requestInstances()[i][classificador.requestAttributes().length - 1]);
		}

		classificador.imprimaClassificador();

		System.out.println("accuracy = " + classificador.accuracy(y_val, diag));
		
	}
}
