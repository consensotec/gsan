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
package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pesquisar Guia Devolucao - Exibir
 * 
 *  Action para Exibir a p�gina de consulta de guias de devolu��o
 * 
 * @author Fernanda Paiva - 02/03/2006
 */
public class ExibirPesquisarGuiaDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarGuiaDevolucao");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarGuiaDevolucaoActionForm pesquisarGuiaDevolucaoActionForm = (PesquisarGuiaDevolucaoActionForm) actionForm;
		
		// Recupera os parametros
		String tela = httpServletRequest.getParameter("tela");
		String tipo = httpServletRequest.getParameter("tipo");
		
		if ((tipo != null && !tipo.equals("")) || (tela != null && !tela.equals("")))  {
			pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
			pesquisarGuiaDevolucaoActionForm.setNomeCliente("");
			pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
			pesquisarGuiaDevolucaoActionForm.setInscricaoImovel("");
			pesquisarGuiaDevolucaoActionForm.reset(actionMapping,httpServletRequest);

		}

		if(httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null
				&& httpServletRequest.getParameter("voltarPesquisa") == null
				&& sessao.getAttribute("flag") == null)
		{
			pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
			pesquisarGuiaDevolucaoActionForm.setNomeCliente("");
			pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
			pesquisarGuiaDevolucaoActionForm.setInscricaoImovel("");
			pesquisarGuiaDevolucaoActionForm.reset(actionMapping,httpServletRequest);

			sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaImovel");
			if (httpServletRequest.getParameter("novaPesquisa") == null
					|| httpServletRequest.getParameter("novaPesquisa").equals(
							"")) {
				sessao.removeAttribute("caminhoRetorno");
			}
			sessao.setAttribute("flag","1");
		}
		// Recupera os dados do formul�rio 
		String codigoCliente = (String) pesquisarGuiaDevolucaoActionForm.getCodigoCliente();
		String codigoImovel = (String) pesquisarGuiaDevolucaoActionForm.getCodigoImovel();
		
		//Verifica se o tipoConsulta � diferente de nulo ou vazio esse tipo
		// consulta vem do
		// localidade_resultado_pesquisa.jsp ou do
		// cliente_resultado_pesquisa.jsp ou do imovel_resultado_pesquisa.jsp.
		// � feita essa verifica��o pois pode ser que ainda n�o tenha
		// feito a pesquisa de cliente ou imovel.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// Verifica se o tipo da consulta de guia devolucao � de imovel
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina guia_devolucao_pesquisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("imovel")) {

				pesquisarGuiaDevolucaoActionForm.setCodigoImovel(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarGuiaDevolucaoActionForm
						.setInscricaoImovel(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
				
				pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");

				pesquisarGuiaDevolucaoActionForm
						.setNomeCliente("");

			}

			// Verifica se o tipo da consulta de arrecadador � de cliente
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina arrecadador_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cliente")) {

				pesquisarGuiaDevolucaoActionForm.setCodigoCliente(httpServletRequest
						.getParameter("idCampoEnviarDados"));
				pesquisarGuiaDevolucaoActionForm
						.setNomeCliente(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
				
				pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");

				pesquisarGuiaDevolucaoActionForm
						.setInscricaoImovel("");

			}

		} else {
			// Verifica se o c�digo do im�vel foi digitado
			if (codigoImovel != null
					&& !codigoImovel.trim().equals("")
					&& Integer.parseInt(codigoImovel) > 0) {
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("quadra");
				
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, codigoImovel));
	
				Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
	
				if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {
					// O imovel foi encontrado
					pesquisarGuiaDevolucaoActionForm.setCodigoImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());
					pesquisarGuiaDevolucaoActionForm.setInscricaoImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getInscricaoFormatada());
				} else {
					pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
					httpServletRequest.setAttribute(
							"codigoImovelNaoEncontrado", "exception");
					pesquisarGuiaDevolucaoActionForm
							.setInscricaoImovel("MATR�CULA INEXISTENTE");
					httpServletRequest.setAttribute("nomeCampo","codigoImovel");
				}
			}
			// Verifica se o c�digo do cliente foi digitado
			if (codigoCliente != null
					&& !codigoCliente.trim().equals("")
					&& Integer.parseInt(codigoCliente) > 0) {
				
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
	
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(
						FiltroClienteEndereco.CLIENTE_ID, codigoCliente));
	
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.profissao");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.ramoAtividade");
	
				Collection clienteEncontrado = fachada.pesquisar(filtroClienteEndereco,
						ClienteEndereco.class.getName());
	
				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				
					ClienteEndereco clienteEndereco = (ClienteEndereco) ((List) clienteEncontrado).get(0);
					// O endere�o do cliente foi encontrado
					if (clienteEndereco.getCliente().getId() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setCodigoCliente(""
								+ clienteEndereco.getCliente().getId());
					}
					if (clienteEndereco.getCliente().getNome() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setNomeCliente(""
								+ clienteEndereco.getCliente().getNome());
					}
					if (clienteEndereco.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1 ){
						if (clienteEndereco.getCliente().getCpfFormatado() != null) 
						{
							pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
									+ clienteEndereco.getCliente().getCpfFormatado());
						}
					}
					else
					{
						if (clienteEndereco.getCliente().getCnpjFormatado() != null) 
						{
							pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
									+ clienteEndereco.getCliente().getCnpjFormatado());
						}
					}
					if (clienteEndereco.getCliente().getProfissao() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setProfissao(""
								+ clienteEndereco.getCliente().getProfissao().getDescricao());
					}
					if (clienteEndereco.getCliente().getRamoAtividade() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setRamoAtividade(""
								+ clienteEndereco.getCliente().getRamoAtividade().getDescricao());
					}
					pesquisarGuiaDevolucaoActionForm.setEnderecoCliente(""
							+ ((ClienteEndereco) ((List) clienteEncontrado)
									.get(0)).getEnderecoFormatado());
					
					FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
	
					filtroClienteFone.adicionarParametro(new ParametroSimples(
							FiltroClienteFone.CLIENTE_ID, codigoCliente));
	
					
					Collection colecaoClienteFone = fachada.pesquisar(
							filtroClienteFone, ClienteFone.class.getName());
	
					if (colecaoClienteFone != null
							&& !colecaoClienteFone.isEmpty()) {
						// O telefone foi encontrado
						pesquisarGuiaDevolucaoActionForm.setClienteFone(""
								+ ((ClienteFone) ((List) colecaoClienteFone)
										.get(0)).getTelefone());
					}
	
				} else {
					pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
					pesquisarGuiaDevolucaoActionForm
							.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
					httpServletRequest.setAttribute(
							"idClienteNaoEncontrado", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoCliente");
				}
			}
		}		
		// Cole��o de Tipo de Cr�dito
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		Collection<CreditoTipo> collectionTipoCredito = fachada.pesquisar(
				filtroCreditoTipo, CreditoTipo.class.getName());

		httpServletRequest.setAttribute("collectionTipoCredito",
				collectionTipoCredito);

		// Cole��o de Tipo de Documento
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		Collection<DocumentoTipo> collectionTipoDocumento = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		httpServletRequest.setAttribute("collectionTipoDocumento",
				collectionTipoDocumento);

		// Cole��o de Debito Cr�dito Situacao
		FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new FiltroDebitoCreditoSituacao();
		Collection<DebitoCreditoSituacao> collectionSituacaoGuia = fachada.pesquisar(
				filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.getName());

		httpServletRequest.setAttribute("collectionSituacaoGuia",
				collectionSituacaoGuia);
		
		return retorno;

	}
}
