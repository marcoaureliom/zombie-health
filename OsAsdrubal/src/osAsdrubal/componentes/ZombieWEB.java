package osAsdrubal.componentes;

import osAsdrubal.interfaces.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class ZombieWEB implements IZombieWEB {

	// Atributos
	private List<String> arvores = new ArrayList<String>();
	private List<INoArvore> raizArvores = new ArrayList<INoArvore>();
	private List<String> classificadores = new ArrayList<String>();
	private String nomeArquivo, nomeSite = "ZombieWEB", corPadrao1 = "#5cbd79", corPadrao2 = "#5b87d4",
			corPadrao3 = "#0366d6";

	public ZombieWEB() {

	}

	// Relativo à árvore

	/**
	 * crieArvore: Registra uma nova árvore no ZombieWEB. Retorna true caso nome
	 * inserido ainda não exista e false caso contrário.
	 * 
	 */
	public boolean crieArvore(String nomeArvore) {

		if (!arvores.contains(nomeArvore)) {
			arvores.add(nomeArvore);
			// raizArvores.add(null);
			return true;
		}

		return false;
	}

	public boolean removaArvore(String nomeArvore) {
		if (arvores.contains(nomeArvore)) {
			int indexA = arvores.indexOf(nomeArvore);
			arvores.remove(indexA);
			raizArvores.remove(indexA);
			return true;
		}

		return false;
	}

	private boolean crieNoRaiz(int i, int id, String texto, String[] legendasInferiores) {
		// raizArvores[i] = new INoArvore(id, texto, legendasInferiores);
		raizArvores.add(i, new NoArvore(id, texto, legendasInferiores));

		return true;
	}

	private boolean insiraNo(int i, int idPai, int idElemento, String texto, String[] legendasInferiores) {

		INoArvore no = raizArvores.get(i).encontreNoPorId(idPai);

		if (no != null) {
			no.addFilho(idElemento, texto, legendasInferiores);
			return true;
		}
		System.out.println("Errinho, nó " + idPai + " não encontrado.");
		return false;
	}

	public boolean insiraElementoArvore(String nomeArvore, int idPai, int idElemento, String textoElemento) {
		return insiraElementoArvore(nomeArvore, idPai, idElemento, textoElemento, null);
	}

	public boolean insiraElementoArvore(String nomeArvore, int idPai, int idElemento, String textoElemento,
			String[] legendasInferiores) {

		// Procure pelo nome da árvore
		int i = arvores.indexOf(nomeArvore);
		if (i == -1)
			return false;

		if (idPai == -1)
			return crieNoRaiz(i, idElemento, textoElemento, legendasInferiores);

		return insiraNo(i, idPai, idElemento, textoElemento, legendasInferiores);

	}

	private String imprimaArvoreHTML(INoArvore no) {

		String texto;
		List<INoArvore> filhos = no.getFilhos();
		boolean temFilhos;

		temFilhos = !filhos.isEmpty();

		if (temFilhos)
			texto = "<a href=\"#\">" + no.getTexto() + " ";
		else
			texto = "<a href=\"#\" class=\"folha\">" + no.getTexto() + " ";

		String[] legendasInf = no.getLegInf();
		if (legendasInf != null) {
			int nLegendas = legendasInf.length;
			if (nLegendas > 0 && nLegendas % 2 == 0) {
				texto += "<div class=\"legInf\">";
				for (int i = 0; i < legendasInf.length - 1; i += 2) {
					texto += "<div ";
					if (i == 0) {
						if (i + 2 == nLegendas)
							texto += " class=\"unic\"";
						else
							texto += " class=\"esq\"";
					} else if (i + 2 == nLegendas)
						texto += " class=\"dir\"";
					texto += " style=\"background-color: " + legendasInf[i + 1] + "; width: "
							+ ((float) 200 / nLegendas) + "%;\">" + legendasInf[i] + "</div>";
				}
				texto += "</div>";
			}
		}

		texto += "</a>";

		if (temFilhos) {

			texto += "<ul>";
			for (INoArvore filho : filhos) {
				texto += "<li>";
				texto += imprimaArvoreHTML(filho);
				texto += "</li>";
			}
			texto += "</ul>";
		}

		return texto;

	}

	private String imprimaArvoreEstruturaDadosJS(INoArvore no) {

		String texto;
		List<INoArvore> filhos = no.getFilhos();
		boolean temFilhos;

		temFilhos = !filhos.isEmpty();

		texto = "{";
		texto += "descricao: '" + no.getTexto() + "',";

		String[] leg = no.getLegInf();
		if (leg != null && leg.length > 0) {
			texto += "arestas: [";

			for (int l = 0; l < leg.length; l += 2) {
				texto += "'" + leg[l] + "'";
				if (l < leg.length - 2)
					texto += ",";
			}
			texto += "],";
		} else
			texto += "arestas: null,";

		if (temFilhos) {
			texto += "filhos: [";
			for (int l = 0; l < filhos.size(); l++) {
				texto += imprimaArvoreEstruturaDadosJS(filhos.get(l));
				if (l < filhos.size() - 1)
					texto += ",";
			}
			texto += "]";
		} else
			texto += "filhos: null";

		texto += "}";
		return texto;

	}

	
	ArrayList<String> inputsAdicionados; 
	private String imprimaArvoreEstruturaInput(INoArvore no, int iArv) {

		String texto = "";
		List<INoArvore> filhos = no.getFilhos();
		boolean temFilhos;

		temFilhos = !filhos.isEmpty();

		if(!inputsAdicionados.contains(no.getTexto())) {
			texto = "<label class=\"campo a-campo a-campo\">";
			texto += "<input titulo=\"" + no.getTexto() + "\" id=\"campoInput" + iArv + "\" class=\"campoInput" + iArv
					+ " a-campoInput\">";
			texto += "<span class=\"a-campoLabel-wrap\"><span class=\"a-campoLabel\">" + no.getTexto()
					+ "</span></span></label>";
			
			inputsAdicionados.add(no.getTexto());
		}
		
		if (temFilhos)
			for (INoArvore filho : filhos)
				if (!filho.getFilhos().isEmpty())
					texto += imprimaArvoreEstruturaInput(filho, iArv);

		return texto;

	}

	// Relativo à construção da página
	public boolean criePaginaHTML() {

		if (nomeArquivo == null)
			return false;

		FileWriter arquivo;
		PrintWriter formatado;

		try {
			arquivo = new FileWriter(nomeArquivo);
			formatado = new PrintWriter(arquivo);

			// HTML e cabeçalho
			formatado.println("<!DOCTYPE html>" + "<html lang='en'>" + "<head>" + "<meta charset='UTF-8'>" + "<title>"
					+ nomeSite + "</title>");

			// CSS da árvore pt1
			formatado.println(
					"<style>body{font-family:sans-serif;font-size:15px}.arvore ul{position:relative;padding:1em 0;white-space:nowrap;margin:0 auto;text-align:center}.arvore ul::after{content:'';display:table;clear:both}.arvore li{display:inline-block;vertical-align:top;text-align:center;list-style-type:none;position:relative;padding:1em .5em 0 .5em}.arvore li::before, .arvore li::after{content:'';position:absolute;top:0;right:50%;border-top:1px solid #ccc;width:50%;height:1em}.arvore li::after{right:auto;left:50%;border-left:1px solid #ccc}.arvore li:only-child::after, .arvore li:only-child::before{display:none}.arvore li:only-child{padding-top:0}.arvore li:first-child::before, .arvore li:last-child::after{border:0 none}.arvore li:last-child::before{border-right:1px solid #ccc;border-radius:0 5px 0 0}.arvore li:first-child::after{border-radius:5px 0 0 0}.arvore ul ul::before{content:'';position:absolute;top:0;left:50%;border-left:1px solid #ccc;width:0;height:1em}.arvore li a{border:1px solid #ccc;padding:0.75em 0.5em 0.75em 0.5em;text-decoration:none;display:inline-block;border-radius:5px;color:#333;position:relative;top:1px}.arvore li a:hover, .arvore li a:hover + ul li a{background:#e9453f;color:#fff;border:1px solid #e9453f}.arvore li a.folha:hover, .arvore li a:hover + ul li a.folha{background:#0366d6;color:#fff;border:1px solid #e9453f}.arvore li a:hover + ul li::after, .arvore li a:hover + ul li::before, .arvore li a:hover + ul::before, .arvore li a:hover + ul ul::before{border-color:#e9453f}</style>");

			// CSS da árvore pt2
			formatado.println(
					"<style>.legInf{width:100%;position:relative;top:0.9em;color:white;font-size:13px}.legInf div{float:left;text-align:center;border-radius:0 0 0 0;width:50%}.legInf div.esq{border-radius:5px 0 0 0 !important}.legInf div.dir{border-radius:0 5px 0 0 !important}.legInf div.unic{border-radius:5px 5px 0 0 !important}.nomeArvore{text-align:center;font-weight:bold;font-size:23px;color:#333}</style>");

			// Fechamento cabeçalho e início corpo
			formatado.println("</head><body>");

			// Escrevendo árvores no arquivo
			int nArvores = arvores.size();
			for (int i = 0; i < nArvores; i++) {
				if (classificadores.contains(arvores.get(i))) {
					formatado.println(
							"<div class=\"CEA\"><div class=\"divInpClassificacao\"><div class=\"resultadoClassif" + i
									+ "\">Classificação<div id=\"resultadoClassif" + i + "\">indefinido</div></div>");
					
					inputsAdicionados = new ArrayList<String>();
					formatado.println(imprimaArvoreEstruturaInput(raizArvores.get(i), i));
					formatado.println("</div>");

					formatado.println("<style>\n" + ".CEA{\n" + "    float: left;\n" + "    margin: 0 5% 1.5% 5%;\n"
							+ "}\n" + ".CEA div.arvore{\n" + "    width: 73%;\n" + "    float: left;\n"
							+ "    padding: 0 0.5% 0 0.5%;\n" + "}\n" + ".divInpClassificacao {\n" + "    width: 15%;\n"
							+ "    float: left;\n" + "    padding: 0 0.5% 0 0.5%;\n" + "}\n" + "\n" + ".campo{\n"
							+ "  --uicampoPlaceholderColor: var(--campoPlaceholderColor, #767676);\n" + "}\n" + "\n"
							+ ".campoInput" + i + "{ \n" + "  background-color: transparent;\n"
							+ "  border-radius: 0;\n" + "  border: none;\n" + "\n" + "  -webkit-appearance: none;\n"
							+ "  -moz-appearance: none;\n" + "\n" + "  font-family: inherit;\n" + "  font-size: 1em;\n"
							+ "}\n" + "\n" + ".campoInput" + i + ":focus::-webkit-input-placeholder{\n"
							+ "  color: var(--uicampoPlaceholderColor);\n" + "}\n" + "\n" + ".campoInput" + i
							+ ":focus::-moz-placeholder{\n" + "  color: var(--uicampoPlaceholderColor);\n"
							+ "  opacity: 1;\n" + "}\n" + "\n" + ".a-campo{\n" + "  display: inline-block;\n" + "}\n"
							+ "\n" + ".a-campoInput{ \n" + "  display: block;\n" + "  box-sizing: border-box;\n"
							+ "  width: 100%;\n" + "}\n" + "\n" + ".a-campoInput:focus{\n" + "  outline: none;\n"
							+ "}\n" + "\n" + "/* a-campo */\n" + ".a-campo{\n"
							+ "  --uicampoHeight: var(--campoHeight, 40px);  \n"
							+ "  --uicampoBorderWidth: var(--campoBorderWidth, 2px);\n"
							+ "  --uicampoBorderColor: var(--campoBorderColor);\n" + "\n"
							+ "  --uicampoFontSize: var(--campoFontSize, 1em);\n"
							+ "  --uicampoHintFontSize: var(--campoHintFontSize, 1em);\n" + "\n"
							+ "  --uicampoPaddingRight: var(--campoPaddingRight, 15px);\n"
							+ "  --uicampoPaddingBottom: var(--campoPaddingBottom, 40px);\n"
							+ "  --uicampoPaddingLeft: var(--campoPaddingLeft, 15px);   \n" + "\n"
							+ "  position: relative;\n" + "  box-sizing: border-box;\n"
							+ "  font-size: var(--uicampoFontSize);\n" + "  padding-top: 1em;  \n" + "}\n" + "\n"
							+ ".a-campo .a-campoInput{\n" + "  height: var(--uicampoHeight);\n"
							+ "  padding: 0 var(--uicampoPaddingRight) 0 var(--uicampoPaddingLeft);\n"
							+ "  border-bottom: var(--uicampoBorderWidth) solid var(--uicampoBorderColor);  \n" + "}\n"
							+ "\n" + ".a-campo .a-campoInput::-webkit-input-placeholder{\n" + "  opacity: 0;\n"
							+ "  transition: opacity .2s ease-out;\n" + "}\n" + "\n"
							+ ".a-campo .a-campoInput::-moz-placeholder{\n" + "  opacity: 0;\n"
							+ "  transition: opacity .2s ease-out;\n" + "}\n" + "\n"
							+ ".a-campo .a-campoInput:not(:placeholder-shown) ~ .a-campoLabel-wrap .a-campoLabel{\n"
							+ "  opacity: 0.5;\n" + "  bottom: var(--uicampoPaddingBottom);\n" + "  /*bottom: 40px;*/\n"
							+ "}\n" + "\n" + ".a-campo .a-campoInput:focus::-webkit-input-placeholder{\n"
							+ "  opacity: 1;\n" + "  transition-delay: .2s;\n" + "}\n" + "\n"
							+ ".a-campo .a-campoInput:focus::-moz-placeholder{\n" + "  opacity: 1;\n"
							+ "  transition-delay: .2s;\n" + "}\n" + "\n" + ".a-campo .a-campoLabel-wrap{\n"
							+ "  box-sizing: border-box;\n" + "  width: 100%;\n" + "  height: var(--uicampoHeight);	\n"
							+ "\n" + "  pointer-events: none;\n" + "  cursor: text;\n" + "\n"
							+ "  position: absolute;\n" + "  bottom: 0;\n" + "  left: 0;\n" + "}\n" + "\n"
							+ ".a-campo .a-campoLabel{\n" + "  position: absolute;\n"
							+ "  left: var(--uicampoPaddingLeft);\n" + "  bottom: calc(50% - .5em);\n" + "\n"
							+ "  line-height: 1;\n" + "  font-size: var(--uicampoHintFontSize);\n" + "\n"
							+ "  pointer-events: none;\n"
							+ "  transition: bottom .2s cubic-bezier(0.9,-0.15, 0.1, 1.15), opacity .2s ease-out;\n"
							+ "  will-change: bottom, opacity;\n" + "}\n" + "\n"
							+ ".a-campo .a-campoInput:focus ~ .a-campoLabel-wrap .a-campoLabel{\n" + "  opacity: 1;\n"
							+ "  bottom: var(--uicampoHeight);\n" + "}\n" + "\n" + ".a-campo{\n"
							+ "  padding-top: 2.5em;\n" + "}\n" + "\n" + ".a-campo .a-campoLabel-wrap:after{\n"
							+ "  content: \"\";\n" + "  box-sizing: border-box;\n" + "  width: 100%;\n"
							+ "  height: 0;\n" + "\n" + "  opacity: 0;\n"
							+ "  border: var(--uicampoBorderWidth) solid var(--campoBorderColorActive);\n" + "\n"
							+ "  position: absolute;\n" + "  bottom: 0;\n" + "  left: 0;\n" + "\n"
							+ "  will-change: opacity, height;\n"
							+ "  transition: height .2s ease-out, opacity .2s ease-out;\n" + "}\n" + "\n"
							+ ".a-campo .a-campoInput:focus ~ .a-campoLabel-wrap:after{\n" + "  height: 100%;\n"
							+ "  opacity: 1;\n" + "}\n" + "\n"
							+ ".a-campo .a-campoInput:focus ~ .a-campoLabel-wrap .a-campoLabel{\n"
							+ "  bottom: calc(var(--uicampoHeight) + .5em);\n" + "}\n" + "\n" + ".campo{\n"
							+ "  --campoBorderColor: #D1C4E9;\n" + "  --campoBorderColorActive: #673AB7;\n"
							+ "  width: 100%;\n" + "}\n" + "\n" + ".divInpClassificacao{\n" + "	width:15%;\n" + "}\n"
							+ "\n .resultadoClassif" + i + " {\n" + "    text-align: center;\n"
							+ "    font-weight: bold;\n" + "    font-size: 23px;\n" + "    color: #333;\n" + "}"
							+ "\n #resultadoClassif" + i + " {\n" + "    color: #673AB7;\n" + "}" + "</style>");

					formatado.println("<script> var arvore" + i + " = [");
					formatado.println(imprimaArvoreEstruturaDadosJS(raizArvores.get(i)));
					formatado.println("];");

					formatado.println("//Variáveis principais\n" + "var campoInput" + i
							+ " = document.querySelectorAll('input.campoInput" + i + "');\n" + "var divResultado" + i
							+ " = document.getElementById('resultadoClassif" + i + "');\n" + "var resultado" + i + ";\n"
							+ "var elemento" + i + " = arvore" + i + "[0];\n" + "var instancia" + i
							+ " = ['0101010101'];\n" + "\n"
							+ "//Para cada campoInput coletado, adicione o seguinte evento\n"
							+ "for (var i = 0; i < campoInput" + i + ".length; i++) {\n" + "    campoInput" + i
							+ "[i].addEventListener('input', classifique" + i + ");\n" + "}\n" + "\n"
							+ "function classifique" + i + "() {\n" + "\n"
							+ "    //É necessário ao menos um elemento no vetor. Serve também como reinicio.\n"
							+ "    instancia" + i + " = ['0101010101'];\n" + "    var valorInp;\n"
							+ "    for (var j = 0; j < campoInput" + i + ".length; j++) {\n" + "        instancia" + i
							+ ".push(campoInput" + i + "[j].getAttribute(\"titulo\"));\n"
							+ "        valorInp = campoInput" + i + "[j].value;\n" + "        if (!valorInp){\n"
							+ "            valorInp = null;\n" + "	}\n" + "        instancia" + i
							+ ".push(valorInp);\n" + "    }\n" + "\n" + "    resultado" + i
							+ " = procureArvore(elemento" + i + ", instancia" + i + ");\n" + "    if (resultado" + i
							+ " != null) {\n" + "        divResultado" + i + ".innerHTML = \"\" + resultado" + i
							+ ".descricao;\n" + "    } else {\n" + "        divResultado" + i
							+ ".innerHTML = \"Inconclusivo\";\n" + "    }\n" + "}\n" + "\n"
							+ "function procureStringVetor(strArray, str) {\n"
							+ "    for (var j = 0; j < strArray.length; j++) {\n"
							+ "        if (strArray[j] != null && (strArray[j].toUpperCase() == str.toUpperCase())) return j;\n"
							+ "    }\n" + "    return -1;\n" + "}\n" + "\n"
							+ "function procureArvore(elemento, instancia" + i + ") {\n" + "\n"
							+ "    if (elemento == null) return null;\n" + "\n"
							+ "    var pos = procureStringVetor(instancia" + i + ", elemento.descricao);\n"
							+ "    if (pos != -1 && elemento.filhos == null) {\n" + "        return elemento;\n"
							+ "    } else if (elemento.filhos != null && instancia" + i
							+ "[pos + 1] != null && elemento.arestas != null) {\n" + "\n"
							+ "        if (elemento.filhos != null && elemento.arestas != null) {\n"
							+ "            var result = procureArvore(elemento.filhos[procureStringVetor(elemento.arestas, instancia"
							+ i + "[pos + 1])], instancia" + i + ");\n" + "            if (result == null) {\n"
							+ "                result = elemento.filhos[procureStringVetor(elemento.arestas, instancia"
							+ i + "[pos + 1])];\n" + "            }\n" + "            return result;\n" + "        }\n"
							+ "    }\n" + "    return null;\n" + "}\n" + "</script>");

				}

				formatado.println(
						"<div class=\"arvore\"><div class=\"nomeArvore\">" + arvores.get(i) + "</div><ul><li>");
				formatado.println(imprimaArvoreHTML(raizArvores.get(i)));
				formatado.println("</li></ul></div>");

				if (classificadores.contains(arvores.get(i))) {
					formatado.println("</div>");
				}

			}

			// Fechamento corpo e HTML
			formatado.println("</body></html>");

			formatado.close();

			return true;
		} catch (IOException erro) {
			return false;
		}
	}

	public void abraPagina() {

		if (nomeArquivo == null)
			return;

		try {
			File file = new java.io.File(nomeArquivo).getAbsoluteFile();
			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			System.out.println("gg" + e);
		}

	}

	public void crieClassificador(String nomeArvore) {
		classificadores.add(nomeArvore);
	}

	public void removaClassificador(String nomeArvore) {
		classificadores.remove(nomeArvore);
	}

	// Set
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo + ".html";
	}

	public void setNomeSite(String nomeSite) {
		this.nomeSite = nomeSite;
	}
	//get
	public INoArvore getArvore(String nomeArvore) {
		
		int i = arvores.indexOf(nomeArvore);
		if(i != -1) {
			return raizArvores.get(i);
		}
		
		return null;
	}
}


