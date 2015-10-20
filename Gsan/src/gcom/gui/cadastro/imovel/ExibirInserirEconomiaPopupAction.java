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
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class ExibirInserirEconomiaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da A��o
		ActionForward retorno = actionMapping.findForward("inserirEconomiaPopup");

		// Cria a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoClientesImoveisEconomia = null;

		// HashSet verifica se existe objeto igual na collection
		if (sessao.getAttribute("colecaoClientesImoveisEconomia") != null) {
			colecaoClientesImoveisEconomia = (Collection) sessao.getAttribute("colecaoClientesImoveisEconomia");
		} else {
			colecaoClientesImoveisEconomia = new ArrayList();
		}

		Collection colecaoImovelSubCategoriasCadastradas = null;

		// Cole��o vinda do exibirInserirEconomiaAcion
		// nessa cole��o est�o todos os imoveis sub categorias que foi
		// pesquisado no economia_inserir_jsp
		if (sessao.getAttribute("colecaoImovelSubCategoriasCadastradas") != null) {
			colecaoImovelSubCategoriasCadastradas = (Collection) sessao.getAttribute("colecaoImovelSubCategoriasCadastradas");
		} else {
			colecaoImovelSubCategoriasCadastradas = new ArrayList();
		}

		// Obt�m o action form
		EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

		// Obt�m a fachada
		Fachada fachada = Fachada.getInstancia();

		// Verifica se veio algum parametro no economia_inserir.jsp
		// caso tenha vindo pega o parametro e procura na cole��o um objeto
		// que tenha um hashCode igual ao do parametro
		if (httpServletRequest.getParameter("codigoImovelSubCategoria") != null && 
			!httpServletRequest.getParameter("codigoImovelSubCategoria").equals("")) {
			
			String codigoImovelSubCategoria = (String) httpServletRequest.getParameter("codigoImovelSubCategoria");

			Iterator imovelSubCategoriaIterator = colecaoImovelSubCategoriasCadastradas.iterator();
			
			while (imovelSubCategoriaIterator.hasNext()) {
				ImovelSubcategoria imovelSubCategoria = 
					(ImovelSubcategoria) imovelSubCategoriaIterator.next();
				
				if (imovelSubCategoria.getUltimaAlteracao().getTime() == Long.parseLong(codigoImovelSubCategoria)) {

					sessao.setAttribute("imovelSubCategoria",imovelSubCategoria);
					
					if (imovelSubCategoria.getImovelEconomias() != null && 
						!imovelSubCategoria.getImovelEconomias().equals("")) {
						
						if (imovelSubCategoria.getImovelEconomias().size() == imovelSubCategoria.getQuantidadeEconomias()) {
							throw new ActionServletException("atencao.ja_existe_dados_economia");
						} else {
							sessao.setAttribute("contIdentificadorTemp",
								new Integer(imovelSubCategoria.getImovelEconomias().size() + 1));
						}
					} else {
						sessao.setAttribute("contIdentificadorTemp",new Integer(1));
					}

					colecaoClientesImoveisEconomia = new ArrayList();

					break;
				}
			}

			economiaPopupActionForm.setComplementoEndereco("");
			economiaPopupActionForm.setNumeroPontosUtilizacao("");
			economiaPopupActionForm.setNumeroMorador("");
			economiaPopupActionForm.setNumeroIptu("");
			economiaPopupActionForm.setNumeroCelpe("");
			economiaPopupActionForm.setAreaConstruida("");
			economiaPopupActionForm.setIdCliente("");
			economiaPopupActionForm.setNomeCliente("");
			economiaPopupActionForm.setIdClienteImovelUsuario("");
		}

		// Cria��o das cole��es
		Collection areasConstruidasFaixas = null;
		Collection clientesRelacoesTipos = null;
		Collection clientes;

		// parametro que testa se dar� um reload(true) ou nao(false)
		httpServletRequest.setAttribute("testeInserir", "false");

		// Cria��o dos objetos
		String idCliente = null;

		if (economiaPopupActionForm.getIdCliente() == null || 
			economiaPopupActionForm.getIdCliente().toString().trim().equalsIgnoreCase("")) {

			// Filtro AreaConstruidaFaixa
			FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = 
				new FiltroAreaConstruidaFaixa(FiltroAreaConstruidaFaixa.MENOR_FAIXA);

			filtroAreaConstruidaFaixa.adicionarParametro(
				new ParametroSimples(FiltroAreaConstruidaFaixa.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			areasConstruidasFaixas = 
				fachada.pesquisar(filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class.getName());

			// Filtro cleintesRelacoesTipos
			FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = 
				new FiltroClienteRelacaoTipo(FiltroClienteRelacaoTipo.DESCRICAO);
			
			filtroClienteRelacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroClienteRelacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
					ClienteRelacaoTipo.PROPRIETARIO,ParametroSimples.CONECTOR_OR));
			
			filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,ClienteRelacaoTipo.USUARIO));
			
			clientesRelacoesTipos = 
				fachada.pesquisar(filtroClienteRelacaoTipo,ClienteRelacaoTipo.class.getName());

			// a cole��o de clientesImoveisTipos � obrigat�rio
			if (clientesRelacoesTipos == null || clientesRelacoesTipos.equals("")) {

				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"cliente rela��o tipo");
			} else {

				if (economiaPopupActionForm.getTextoSelecionadoEconomia() == null || 
					economiaPopupActionForm.getTextoSelecionadoEconomia().equals("")) {
					
					economiaPopupActionForm.setTextoSelecionadoEconomia(((ClienteRelacaoTipo) 
						((List) clientesRelacoesTipos).get(0)).getDescricao());
				}
			}
			
			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			// joga em dataInicial a parte da data
			String dataInicial = dataFormato.format(new Date());

			economiaPopupActionForm.setDataInicioClienteImovelRelacao(dataInicial);

			// Envia os objetos no request
			sessao.setAttribute("areasConstruidasFaixas",areasConstruidasFaixas);
			sessao.setAttribute("clientesRelacoesTipos", clientesRelacoesTipos);

			// Realiza a pesquisa de Cliente se necess�rio (caso o usu�rio
			// informou um c�digo do cliente e teclou <enter>)
		} else {

			idCliente = economiaPopupActionForm.getIdCliente();
			FiltroCliente filtroCliente = new FiltroCliente();

			// Adiciona parametro
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));

			// Realiza a pesquisa de cliente
			clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			if (clientes != null && !clientes.isEmpty()) {

				// O cliente foi encontrado
				economiaPopupActionForm.setIdCliente(((Cliente) ((List) clientes).get(0)).getId().toString());
				economiaPopupActionForm.setNomeCliente(((Cliente) ((List) clientes).get(0)).getNome());

				//cliente = new Cliente();
				/*cliente = (Cliente)*/ 
				clientes.iterator().next();

			} else {
				httpServletRequest.setAttribute("codigoClienteNaoEncontrado","true");
				economiaPopupActionForm.setNomeCliente("C�digo Inexistente");
			}
		}

		// Verifica se o tipoConsulta � diferente de nulo ou vazio esse tipo
		// consulta vem do
		// municipio_resultado_pesquisa.jsp ou do bairro_resultado_pesquisa.jsp.
		// � feita essa verifica��o pois pode ser que ainda n�o tenha
		// feito a pesquisa de municipio ou bairro.
		if (httpServletRequest.getParameter("tipoConsulta") != null &&
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// Verifica se o tipo da consulta de cliente � de municipio
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals("cliente")) {

				economiaPopupActionForm.setIdCliente(httpServletRequest.getParameter("idCampoEnviarDados"));
				economiaPopupActionForm.setNomeCliente(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
		}

		sessao.setAttribute("colecaoClientesImoveisEconomia",colecaoClientesImoveisEconomia);
		
		if (!colecaoClientesImoveisEconomia.isEmpty()){
			economiaPopupActionForm.setColecaoCliente("SIM");
		} else {
			economiaPopupActionForm.setColecaoCliente(null);
		}
		
		if (httpServletRequest.getAttribute("i") == null){
			economiaPopupActionForm.setClienteRelacaoTipo("-1");
		}

		return retorno;
	}

}
