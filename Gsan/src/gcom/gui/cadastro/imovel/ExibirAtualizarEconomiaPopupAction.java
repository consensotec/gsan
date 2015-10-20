/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para exibir a p�gina de economia popup
 * 
 * @author S�vio Luiz
 * @created 19 de Maio de 2004
 */
public class ExibirAtualizarEconomiaPopupAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da A��o
		ActionForward retorno = actionMapping
				.findForward("atualizarEconomiaPopup");

		// Cria a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoImovelSubCategoriasCadastradas = null;

		// Cole��o vinda do exibirInserirEconomiaAcion
		// nessa cole��o est�o todos os imoveis sub categorias que foi
		// pesquisado no economia_inserir_jsp
		if (sessao.getAttribute("colecaoImovelSubCategoriasCadastradas") != null) {
			colecaoImovelSubCategoriasCadastradas = (Collection) sessao
					.getAttribute("colecaoImovelSubCategoriasCadastradas");

		} else {
			colecaoImovelSubCategoriasCadastradas = new ArrayList();
		}

		// Obt�m o action form
		EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

		// Obt�m a fachada
		Fachada fachada = Fachada.getInstancia();

		ImovelEconomia imovelEconomia = null;

		// incicializa o achou para false e caso entre no loop do while ele
		// passa a ser true
		boolean achou = false;

		// Verifica se veio algum parametro no economia_inserir.jsp
		// caso tenha vindo pega o parametro e procura na cole��o um objeto
		// que tenha um hashCode igual ao do parametro
		if (httpServletRequest.getParameter("codigoImovelEconomia") != null
				&& !httpServletRequest.getParameter("codigoImovelEconomia")
						.equals("")) {

			// para incluir mais rela��es entre cliente e imoveis, se preciso
			sessao.setAttribute("colecaoClientesImoveisEconomia",
					new ArrayList());

			Iterator imovelSubCategoriaIterator = colecaoImovelSubCategoriasCadastradas
					.iterator();

			String codigoImovelEconomia = (String) httpServletRequest
					.getParameter("codigoImovelEconomia");

			while (imovelSubCategoriaIterator.hasNext()) {

				if (!achou) {

					ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) imovelSubCategoriaIterator
							.next();
					Iterator imovelEconomiaIterator = imovelSubcategoria
							.getImovelEconomias().iterator();
					while (imovelEconomiaIterator.hasNext()) {
						imovelEconomia = (ImovelEconomia) imovelEconomiaIterator
								.next();
						if (imovelEconomia.getUltimaAlteracao().getTime() == Long
								.parseLong(codigoImovelEconomia)) {

							sessao.setAttribute("imovelEconomia",
									imovelEconomia);
							// manda os parametros para o form
							economiaPopupActionForm
									.setComplementoEndereco(formatarResultado(imovelEconomia
											.getComplementoEndereco()));
							economiaPopupActionForm
									.setNumeroPontosUtilizacao(formatarResultado(""
											+ imovelEconomia
													.getNumeroPontosUtilizacao()));
							economiaPopupActionForm
									.setNumeroMorador(formatarResultado(""
											+ imovelEconomia.getNumeroMorador()));
							economiaPopupActionForm
									.setNumeroIptu(formatarResultado(""
											+ imovelEconomia.getNumeroIptu()));
							economiaPopupActionForm
									.setNumeroCelpe(formatarResultado(""
											+ imovelEconomia.getNumeroCelpe()));
							economiaPopupActionForm.setAreaConstruida(Util
									.formatarMoedaReal(imovelEconomia
											.getAreaConstruida()));
							economiaPopupActionForm.setIdCliente("");
							economiaPopupActionForm.setNomeCliente("");

							SimpleDateFormat dataFormatoAtual = new SimpleDateFormat(
									"dd/MM/yyyy");
							// joga em dataInicial a parte da data
							String dataAtual = dataFormatoAtual
									.format(new Date());

							economiaPopupActionForm
									.setDataInicioClienteImovelRelacao(dataAtual);

							// verifica se o im�vel � de tarifa social caso seja
							// desabilita alguns campos.
							if (imovelEconomia.getImovelSubcategoria()
									.getComp_id().getImovel().getImovelPerfil()
									.getId().equals(ImovelPerfil.TARIFA_SOCIAL)) {
								sessao.setAttribute("tarifaSocial", "1");
							} else {
								sessao.removeAttribute("tarifaSocial");
							}

							achou = true;
							break;
						}
					}
				} else {
					break;
				}
			}

		}

		// parametro que testa se dar� um reload(true) ou nao(false)
		httpServletRequest.setAttribute("testeInserir", new Boolean(false));
		Collection colecaoClientesImoveisEconomia = null;
		// HashSet verifica se existe objeto igual na collection
		if (sessao.getAttribute("colecaoClientesImoveisEconomia") != null) {
			colecaoClientesImoveisEconomia = (Collection) sessao
					.getAttribute("colecaoClientesImoveisEconomia");

		} else {
			colecaoClientesImoveisEconomia = new HashSet();
		}

		// caso o parametro de pesquisa enter que � colocado no jsp de
		// atualizar_economia_popup estiver nulo ent�o
		// n�o foi feita a pesquisa de enter e entra no if.
		if (httpServletRequest.getParameter("pesquisaEnter") == null
				|| httpServletRequest.getParameter("pesquisaEnter")
						.equalsIgnoreCase("")) {

			// Cria��o das cole��es
			Collection areasConstruidasFaixas = null;
			Collection clientesRelacoesTipos = null;

			// caso venha do jsp imovel_economia_fim_relacao_cliente e n�o entre
			// do if do codigoImovelEconomia que � onde o achou
			// fica true.
			if (!achou) {

				if (sessao.getAttribute("imovelEconomia") != null
						&& !sessao.getAttribute("imovelEconomia").equals("")) {
					imovelEconomia = (ImovelEconomia) sessao
							.getAttribute("imovelEconomia");
					// manda os parametros para o form
					economiaPopupActionForm
							.setComplementoEndereco(formatarResultado(imovelEconomia
									.getComplementoEndereco()));
					economiaPopupActionForm
							.setNumeroPontosUtilizacao(formatarResultado(""
									+ imovelEconomia
											.getNumeroPontosUtilizacao()));
					economiaPopupActionForm
							.setNumeroMorador(formatarResultado(""
									+ imovelEconomia.getNumeroMorador()));
					economiaPopupActionForm.setNumeroIptu(formatarResultado(""
							+ imovelEconomia.getNumeroIptu()));
					economiaPopupActionForm.setNumeroCelpe(formatarResultado(""
							+ imovelEconomia.getNumeroCelpe()));
					economiaPopupActionForm
							.setAreaConstruida(formatarResultado(""
									+ imovelEconomia.getAreaConstruida()));

					SimpleDateFormat dataFormatoAtual = new SimpleDateFormat(
							"dd/MM/yyyy");
					// joga em dataInicial a parte da data
					String dataAtual = dataFormatoAtual.format(new Date());

					economiaPopupActionForm
							.setDataInicioClienteImovelRelacao(dataAtual);

				}
			}

			// Verifica se o tipoConsulta � diferente de nulo ou vazio esse tipo
			// consulta vem do
			// cliente_resultado_pesquisa.jsp.
			// � feita essa verifica��o pois pode ser que ainda n�o tenha
			// feito a pesquisa de cliente.
			if (httpServletRequest.getParameter("tipoConsulta") == null
					|| httpServletRequest.getParameter("tipoConsulta").equals(
							"")) {

				// Filtro AreaConstruidaFaixa
				FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa(
						FiltroAreaConstruidaFaixa.MENOR_FAIXA);

				filtroAreaConstruidaFaixa
						.adicionarParametro(new ParametroSimples(
								FiltroAreaConstruidaFaixa.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				areasConstruidasFaixas = fachada.pesquisar(
						filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class
								.getName());

				// Filtro TipoClienteImovel
				FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo(
						FiltroClienteTipo.DESCRICAO);
				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
								ClienteRelacaoTipo.USUARIO,
								ParametroSimples.CONECTOR_OR));
				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
								ClienteRelacaoTipo.PROPRIETARIO));
				clientesRelacoesTipos = fachada.pesquisar(
						filtroClienteRelacaoTipo, ClienteRelacaoTipo.class
								.getName());
				// a cole��o de clientesImoveisTipos � obrigat�rio
				if (clientesRelacoesTipos == null
						|| clientesRelacoesTipos.equals("")) {

					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null,
							"cliente tipo");
				} else {

					if (economiaPopupActionForm.getTextoSelecionadoEconomia() == null
							|| economiaPopupActionForm
									.getTextoSelecionadoEconomia().equals("")) {
						economiaPopupActionForm
								.setTextoSelecionadoEconomia(((ClienteRelacaoTipo) ((List) clientesRelacoesTipos)
										.get(0)).getDescricao());
					}
				}

				if (imovelEconomia.getAreaConstruidaFaixa() != null
						&& !imovelEconomia.getAreaConstruidaFaixa().equals("")) {
					economiaPopupActionForm
							.setIdAreaConstruidaFaixa(imovelEconomia
									.getAreaConstruidaFaixa().getId()
									.toString());
				}
				// Envia os objetos no request
				sessao.setAttribute("areasConstruidasFaixas",
						areasConstruidasFaixas);

				sessao.setAttribute("clientesRelacoesTipos",
						clientesRelacoesTipos);
				// caso venha algum parametro do tipoConsulta ent�o
			} else {
				// Verifica se o tipo da consulta � de cliente
				// se for os parametros de enviarDadosParametros ser�o mandados
				// para
				// a pagina atualizar_economia_popup.jsp
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"cliente")) {

					economiaPopupActionForm.setIdCliente(httpServletRequest
							.getParameter("idCampoEnviarDados"));

					economiaPopupActionForm.setNomeCliente(httpServletRequest
							.getParameter("descricaoCampoEnviarDados"));

				}
			}

			// Realiza a pesquisa de Cliente se necess�rio (caso o usu�rio
			// informou um c�digo do cliente e teclou <enter>)
		} else {

			Collection clientes;
			// Cria��o dos objetos
			String idCliente = null;
			// Cliente cliente = null;

			idCliente = economiaPopupActionForm.getIdCliente();
			FiltroCliente filtroCliente = new FiltroCliente();

			// Adiciona parametro
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));
			// Realiza a pesquisa de cliente
			clientes = fachada
					.pesquisar(filtroCliente, Cliente.class.getName());
			if (clientes != null && !clientes.isEmpty()) {
				// O cliente foi encontrado
				economiaPopupActionForm
						.setIdCliente(((Cliente) ((List) clientes).get(0))
								.getId().toString());
				economiaPopupActionForm
						.setNomeCliente(((Cliente) ((List) clientes).get(0))
								.getNome());
				// cliente = new Cliente();
				/* cliente = (Cliente) */clientes.iterator().next();

			} else {
				httpServletRequest.setAttribute("codigoClienteNaoEncontrado",
						"true");
				economiaPopupActionForm.setNomeCliente("");
			}
		}

		sessao.setAttribute("colecaoClientesImoveisEconomia",
				colecaoClientesImoveisEconomia);

		if (httpServletRequest.getParameter("limpa") != null) {
			economiaPopupActionForm
					.setClienteRelacaoTipo(ConstantesSistema.NUMERO_NAO_INFORMADO
							+ "");
		}

		return retorno;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param parametro
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}

}
