package gsan.gui.atendimentopublico;

import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cobranca.bean.ContaValoresHelper;
import gsan.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * Recebe a matrícula do imóvel e retorne todas as Contas em aberto, com a
 * Referência, a Data de Vencimento, o Valor Original e os Acréscimos Calculados
 * até a presente data
 * 
 * Autor: Fábio Aguiar
 * 
 * Data: 20/11/2014
 */
public class ContasAtrasoWebService extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		HttpSession sessao = httpServletRequest.getSession(false);
		String matricula = (String) httpServletRequest
				.getParameter("matricula");

		Fachada fachada = Fachada.getInstancia();
		ObterDebitoImovelOuClienteHelper obterDebitoImovel = new ObterDebitoImovelOuClienteHelper();
		Imovel imovel = new Imovel();

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				matricula));
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, ConstantesSistema.NAO));

		Collection colecaoImovel = (Collection<Imovel>) fachada.pesquisar(
				filtroImovel, Imovel.class.getName());
		if (colecaoImovel.size() != 0) {
			imovel = (Imovel) colecaoImovel.iterator().next();

			obterDebitoImovel = fachada.obterDebitosImovel(Integer
					.parseInt(matricula));
		}

		PrintWriter out = httpServletResponse.getWriter();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		if ((colecaoImovel == null || colecaoImovel.isEmpty())
				|| imovel.getIndicadorExclusao() == 1
				|| imovel.getIndicadorExclusao() == null
				|| soContemNumeros(matricula) == false) {
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.newDocument();
				Element rootElement = doc.createElementNS("", "mensagem");

				doc.appendChild(rootElement);

				rootElement
						.appendChild(getMsgErro(doc, "matricula inexistente"));

				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();

				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);

				StreamResult console = new StreamResult(System.out);
				StreamResult file = new StreamResult(out);

				transformer.transform(source, console);
				transformer.transform(source, file);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (obterDebitoImovel.getColecaoContasValores().isEmpty()) {
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.newDocument();
				Element rootElement = doc.createElementNS("", "mensagem");

				doc.appendChild(rootElement);

				rootElement.appendChild(getMsgErro(doc,
						"imóvel sem débitos de conta"));

				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();

				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);

				StreamResult console = new StreamResult(System.out);
				StreamResult file = new StreamResult(out);

				transformer.transform(source, console);
				transformer.transform(source, file);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.newDocument();
				Element rootElement = doc.createElementNS("", "contas");

				doc.appendChild(rootElement);

				for (ContaValoresHelper contas : obterDebitoImovel
						.getColecaoContasValores()) {

					BigDecimal juros;
					juros = contas.getValorMulta();
					juros = juros.add(contas.getValorJurosMora());
					juros = juros.add(contas.getValorAtualizacaoMonetaria());

					rootElement.appendChild(getDebitos(doc, String
							.valueOf(contas.getConta().getReferencia()),
							String.valueOf(contas.getConta()
									.getDataVencimentoConta()), contas
									.getConta().getValorTotalConta(), String
									.valueOf(juros)));
				}

				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();

				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);

				StreamResult console = new StreamResult(System.out);
				StreamResult file = new StreamResult(out);

				transformer.transform(source, console);
				transformer.transform(source, file);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private static Node getDebitos(Document doc, String referencia,
			String vencimento, String valor, String adicionais) {
		Element debitos = doc.createElement("conta");

		debitos.appendChild(getElementos(doc, debitos, "referencia", referencia));

		debitos.appendChild(getElementos(doc, debitos, "vencimento", vencimento));

		debitos.appendChild(getElementos(doc, debitos, "valor", valor));

		debitos.appendChild(getElementos(doc, debitos, "adicionais", adicionais));

		return debitos;
	}

	private static Node getMsgErro(Document doc, String mensagemDeErro) {
		Element msg = doc.createElement("erro");

		msg.appendChild(getElementos(doc, msg, "mensagemerro", mensagemDeErro));

		return msg;
	}

	private static Node getElementos(Document doc, Element element,
			String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

	private static boolean soContemNumeros(String matricula) {
		if (matricula == null) {
			return false;
		}
		for (char letra : matricula.toCharArray())
			if (letra < '0' || letra > '9') {
				return false;
			}
		return true;
	}

}
