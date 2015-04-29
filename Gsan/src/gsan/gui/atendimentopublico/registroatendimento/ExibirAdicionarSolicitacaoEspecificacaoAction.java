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
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.registroatendimento.ArquivoProcedimentoOperacionalPadrao;
import gsan.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gsan.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacao;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.SolicitacaoEspecificacaoHelper;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.tools.jxc.apt.Const;

/**
 * Action respons�vel pela pre-exibi��o
 * 
 * 
 * @author S�vio Luiz
 * @created 28 de Julho de 2006
 */
public class ExibirAdicionarSolicitacaoEspecificacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("adicionarSolicitacaoEspecificacao");

		String consultarUltimaAlteracao = httpServletRequest
				.getParameter("ultimaAlteracao");

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		AdicionarSolicitacaoEspecificacaoActionForm adicionarSolicitacaoEspecificacaoActionForm = (AdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		/*RM 5924
		Adicionar arquivo de procedimentos operacionais padr�es POPs
		Am�lia Pessoa 12/12/2011 */
		
		String adicionar = httpServletRequest.getParameter("adicionar");
		String remover = httpServletRequest.getParameter("remover");
		String visualizar = httpServletRequest.getParameter("visualizar");
		String editar = httpServletRequest.getParameter("editar");
		Collection<ArquivoProcedimentoOperacionalPadrao> colecaoArquivos = null;
		//adicionarSolicitacaoEspecificacaoActionForm.setIndicadorTipoEspecifica��oListadoPopupConsultarImovel(ConstantesSistema.NAO.toString());
		
		//Adicionando arquivo
		if (adicionar != null && !adicionar.equals("")){
	        ArquivoProcedimentoOperacionalPadrao arquivoInformado = new ArquivoProcedimentoOperacionalPadrao();
	        arquivoInformado.setArquivo(adicionarSolicitacaoEspecificacaoActionForm.getArquivo());
			arquivoInformado.setNomeArquivo(adicionarSolicitacaoEspecificacaoActionForm.getArquivo().getFileName());
			try {
				arquivoInformado.setBytesArquivo(adicionarSolicitacaoEspecificacaoActionForm.getArquivo().getFileData());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			arquivoInformado.setNomeExtensaoArquivo(adicionarSolicitacaoEspecificacaoActionForm.getArquivo().getContentType());
			
	        //Inserindo o arquivo na cole��o de visualiza��o
			Collection<SolicitacaoEspecificacaoHelper> colecaoHelpers;
			if (sessao.getAttribute("colecaoArquivos") != null){
				colecaoArquivos = (Collection<ArquivoProcedimentoOperacionalPadrao>) sessao.getAttribute("colecaoArquivos");
					
			} else {
				if (sessao.getAttribute("colecaoHelpers") != null){
					colecaoHelpers = (Collection<SolicitacaoEspecificacaoHelper>) sessao.getAttribute("colecaoHelpers");
				} else {
					colecaoHelpers = new ArrayList<SolicitacaoEspecificacaoHelper>();
					
				}
				SolicitacaoEspecificacaoHelper helper = new SolicitacaoEspecificacaoHelper();
				Integer prox = (Integer) sessao.getAttribute("proxId");
				sessao.removeAttribute("proxId");
				if (prox==null){
					prox = new Integer(0);
				} 
				helper.setIdHelper(prox);
				adicionarSolicitacaoEspecificacaoActionForm.setIdHelper(prox);
				colecaoArquivos = new ArrayList<ArquivoProcedimentoOperacionalPadrao>();
				
				sessao.setAttribute("proxId", ++prox);
				
				helper.setColecaoArquivos(colecaoArquivos);
				colecaoHelpers.add(helper);
				sessao.setAttribute("colecaoHelpers", colecaoHelpers);
				
			}
			
			arquivoInformado.setPosicao(colecaoArquivos.size());
			colecaoArquivos.add(arquivoInformado);
			sessao.setAttribute("colecaoArquivos", colecaoArquivos);
			
		} else if (remover != null && !remover.equals("")){ //Removendo arquivo
			colecaoArquivos = (ArrayList<ArquivoProcedimentoOperacionalPadrao>) sessao.getAttribute("colecaoArquivos");
			ArquivoProcedimentoOperacionalPadrao itemRemover = obterArquivo(colecaoArquivos, remover);
			colecaoArquivos.remove(itemRemover);			
		} else if (editar != null && !editar.equals("")){ 
			sessao.removeAttribute("colecaoArquivos");
			Collection<SolicitacaoEspecificacaoHelper> colecaoHelpers = (Collection<SolicitacaoEspecificacaoHelper>) sessao.getAttribute("colecaoHelpers");
			if (colecaoHelpers!=null){
				for (SolicitacaoEspecificacaoHelper helper: colecaoHelpers){
					if (helper.getIdHelper()==Integer.parseInt(editar)){
						sessao.setAttribute("colecaoArquivos", helper.getColecaoArquivos());
					}
				}
			}
		}
		
		
		//Fim RM 5924
		if (consultarUltimaAlteracao != null
				&& !consultarUltimaAlteracao.equals("")) {
			long ultimaAlteracaoTime = Long.parseLong(consultarUltimaAlteracao);
			Collection colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacao");
			Iterator iteEspecificacaoServicoTipo = colecaoSolicitacaoTipoEspecificacao
					.iterator();
			while (iteEspecificacaoServicoTipo.hasNext()) {
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) iteEspecificacaoServicoTipo
						.next();
				if (solicitacaoTipoEspecificacao.getUltimaAlteracao().getTime() == ultimaAlteracaoTime) {
					// recupera os dados do objeto da cole��o
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoSolicitacao(solicitacaoTipoEspecificacao
									.getDescricao());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setPrazoPrevisaoAtendimento(""
									+ solicitacaoTipoEspecificacao
											.getDiasPrazo());
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorPavimentoCalcada(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorPavimentoCalcada());
					adicionarSolicitacaoEspecificacaoActionForm
					.setIndicadorPavimentoRua(""
							+ solicitacaoTipoEspecificacao
									.getIndicadorPavimentoRua());
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorLigacaoAgua(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorLigacaoAgua());	
					adicionarSolicitacaoEspecificacaoActionForm
					.setIndicadorPoco(""
							+ solicitacaoTipoEspecificacao
									.getIndicadorPoco());					
					adicionarSolicitacaoEspecificacaoActionForm
					.setIndicadorLigacaoEsgoto(""
							+ solicitacaoTipoEspecificacao
									.getIndicadorLigacaoEsgoto());
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorCobrancaMaterial(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorCobrancaMaterial());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorParecerEncerramento(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorParecerEncerramento());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorGerarDebito(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorGeracaoDebito());
					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorGerarCredito(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorGeracaoCredito());
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorCliente(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorCliente());
					adicionarSolicitacaoEspecificacaoActionForm
					        .setIndicadorVerificarDebito(""
							        + solicitacaoTipoEspecificacao
									        .getIndicadorVerificarDebito());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorMatriculaImovel(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorMatricula());
					adicionarSolicitacaoEspecificacaoActionForm
					        .setIndicadorUrgencia(""
							        + solicitacaoTipoEspecificacao
									        .getIndicadorUrgencia());
					
					if (solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao() != null
							&& !solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().equals("")) {
						adicionarSolicitacaoEspecificacaoActionForm
								.setIdSituacaoImovel(""+ solicitacaoTipoEspecificacao
												.getEspecificacaoImovelSituacao().getId());
					}else{
						adicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("");
					}
					
					//Colocado por Raphael Rossiter em 25/02/2008
					if (solicitacaoTipoEspecificacao.getDebitoTipo() != null) {
						
						adicionarSolicitacaoEspecificacaoActionForm
						.setIdDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
						
						adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
						
					}else{
						adicionarSolicitacaoEspecificacaoActionForm
						.setIdDebitoTipo("");
						
						adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoDebitoTipo("");
					}
					
					
					//Colocado por Raphael Rossiter em 25/02/2008
					if (solicitacaoTipoEspecificacao.getValorDebito() != null){
						
						adicionarSolicitacaoEspecificacaoActionForm
						.setValorDebito(Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getValorDebito()));
					}else{
						adicionarSolicitacaoEspecificacaoActionForm.setValorDebito("");
					}

					//Colocado por Rafael Corr�a em 14/08/2008
					adicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico(
					        String.valueOf(solicitacaoTipoEspecificacao.getIndicadorEncerramentoAutomatico()));
					
					//Colocado por Raphael Rossiter em 14/03/2008
					adicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor(
					         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor()));
					
					//Colocado por Raphael Rossiter em 14/03/2008
					adicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros(
					         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorCobrarJuros()));					
					
					if (solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
							&& !solicitacaoTipoEspecificacao.getUnidadeOrganizacional().equals("")) {
						adicionarSolicitacaoEspecificacaoActionForm
								.setIdUnidadeTramitacao(""+ solicitacaoTipoEspecificacao
												.getUnidadeOrganizacional().getId());
						adicionarSolicitacaoEspecificacaoActionForm
								.setDescricaoUnidadeTramitacao(solicitacaoTipoEspecificacao
										.getUnidadeOrganizacional().getDescricao());
					}else{
						adicionarSolicitacaoEspecificacaoActionForm
						        .setIdUnidadeTramitacao("");
				        adicionarSolicitacaoEspecificacaoActionForm
						        .setDescricaoUnidadeTramitacao("");
					}

					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorGeraOrdemServico(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorGeracaoOrdemServico());

					if (solicitacaoTipoEspecificacao.getServicoTipo() != null
							&& !solicitacaoTipoEspecificacao.getServicoTipo().equals("")) {
						adicionarSolicitacaoEspecificacaoActionForm
								.setIdServicoOS(""+ solicitacaoTipoEspecificacao
												.getServicoTipo().getId());
						adicionarSolicitacaoEspecificacaoActionForm
								.setDescricaoServicoOS(solicitacaoTipoEspecificacao
										.getServicoTipo().getDescricao());
					}else{
						adicionarSolicitacaoEspecificacaoActionForm
						        .setIdServicoOS("");
				        adicionarSolicitacaoEspecificacaoActionForm
						        .setDescricaoServicoOS("");
					}

					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorInformarContaRA(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorInformarContaRA());
					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorAlterarVencimentoAlternativo(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorAlterarVencimentoAlternativo());
					
					adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorDocumentoObrigatorio("" + 
					solicitacaoTipoEspecificacao.getIndicadorDocumentoObrigatorio());
					
                    adicionarEspecificacao( 
                            adicionarSolicitacaoEspecificacaoActionForm,
                            solicitacaoTipoEspecificacao,
                            sessao,
                            fachada,
                            true );				
					
					httpServletRequest.setAttribute(
							"colecaoEspecificacaoServicoTipo",
							solicitacaoTipoEspecificacao
									.getEspecificacaoServicoTipos());

					FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
					Collection colecaoImovelSituacao = fachada.pesquisar(
							filtroEspecificacaoImovelSituacao,
							EspecificacaoImovelSituacao.class.getName());
					httpServletRequest.setAttribute("colecaoImovelSituacao",
							colecaoImovelSituacao);
					httpServletRequest.setAttribute("consultaDados", "SIM");
					if (httpServletRequest.getParameter("atualizar") != null) {
						httpServletRequest.removeAttribute("consultaDados");
						sessao.setAttribute("atualizar", true);
					}

				}
			}
		} else {

			// caso exista o parametro ent�o limpa a sess�o e o form
			if (httpServletRequest.getParameter("limpaSessao") != null
					&& !httpServletRequest.getParameter("limpaSessao").equals(
							"")) {

				adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoSolicitacao("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setPrazoPrevisaoAtendimento("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorPavimentoCalcada("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorLigacaoAgua("");
				adicionarSolicitacaoEspecificacaoActionForm
				.setIndicadorPoco("");
				adicionarSolicitacaoEspecificacaoActionForm
				.setIndicadorLigacaoEsgoto("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorPavimentoRua("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorCobrancaMaterial("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorParecerEncerramento("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorGerarDebito("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorGerarCredito("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorCliente("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorVerificarDebito("");				
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorMatriculaImovel("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIdSituacaoImovel("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIdUnidadeTramitacao("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoUnidadeTramitacao("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorGeraOrdemServico("");
				adicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoServicoOS("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorTipoEspecificacaoListadoPopupConsultarImovel("");
				
				//Colocado por Raphael Rossiter em 25/02/2008
				adicionarSolicitacaoEspecificacaoActionForm.setIdDebitoTipo("");
				adicionarSolicitacaoEspecificacaoActionForm.setDescricaoDebitoTipo("");
				adicionarSolicitacaoEspecificacaoActionForm.setValorDebito("");
				adicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor("");
				adicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros("");
                adicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao("");
                adicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao("");                
                
                adicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA("");
                adicionarSolicitacaoEspecificacaoActionForm
                	.setIndicadorDocumentoObrigatorio(ConstantesSistema.NAO.toString());

                sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
                
                //TODO
                sessao.removeAttribute("colecaoArquivos");
			}

			// Verifica se o tipoConsulta � diferente de nulo ou vazio.Nesse
			// caso �
			// quando um o retorno da consulta vem para o action ao inves de
			// ir
			// direto para o jsp
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals(
							"")) {
				// verifica se retornou da pesquisa de unidade de tramite
				if (httpServletRequest.getParameter("tipoConsulta").equals("unidadeAtual")) {

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdUnidadeTramitacao(httpServletRequest
									.getParameter("idCampoEnviarDados"));

					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoUnidadeTramitacao(httpServletRequest
									.getParameter("descricaoCampoEnviarDados"));

				}
				// verifica se retornou da pesquisa de tipo de servi�o
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"tipoServico")) {

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdServicoOS(httpServletRequest
									.getParameter("idCampoEnviarDados"));

					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoServicoOS(httpServletRequest
									.getParameter("descricaoCampoEnviarDados"));

				}
				
				/*
				 * Colocado por Raphael Rossiter em 25/02/2008
				 * Verifica se retornou da pesquisa de tipo de d�bito
				 */
				if (httpServletRequest.getParameter("tipoConsulta").equals("tipoDebito")) {

					adicionarSolicitacaoEspecificacaoActionForm
					.setIdDebitoTipo(httpServletRequest.getParameter("idCampoEnviarDados"));

					adicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoDebitoTipo(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
				}
			}

			// -------Parte que trata do c�digo quando o usu�rio tecla enter
			String idUnidadeInicialTramitacao = adicionarSolicitacaoEspecificacaoActionForm
					.getIdUnidadeTramitacao();
			String descricaoInicialTramitacao = adicionarSolicitacaoEspecificacaoActionForm
					.getDescricaoUnidadeTramitacao();
			String idTipoServicoOS = (String) adicionarSolicitacaoEspecificacaoActionForm
					.getIdServicoOS();
			String descricaoServicoOS = adicionarSolicitacaoEspecificacaoActionForm
					.getDescricaoServicoOS();
			
			//Colocado por Raphael Rossiter em 25/02/2008
			String idDebitoTipo = (String) adicionarSolicitacaoEspecificacaoActionForm
			.getIdDebitoTipo();
			String descricaoDebitoTipo = adicionarSolicitacaoEspecificacaoActionForm
			.getDescricaoDebitoTipo();

			
			// Verifica se o c�digo foi digitado pela primeira vez ou se foi
			// modificado
			if (idUnidadeInicialTramitacao != null
					&& !idUnidadeInicialTramitacao.trim().equals("")
					&& (descricaoInicialTramitacao == null || descricaoInicialTramitacao
							.trim().equals(""))) {

				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID,
								idUnidadeInicialTramitacao));

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection unidadeOrganizacionalEncontrado = fachada.pesquisar(
						filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

				if (unidadeOrganizacionalEncontrado != null
						&& !unidadeOrganizacionalEncontrado.isEmpty()) {
					adicionarSolicitacaoEspecificacaoActionForm
							.setIdUnidadeTramitacao(""
									+ ((UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
											.get(0)).getId());
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoUnidadeTramitacao(((UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
									.get(0)).getDescricao());
					httpServletRequest.setAttribute(
							"idUnidadeTramitacaoNaoEncontrado", "true");

					httpServletRequest.setAttribute("nomeCampo",
							"indicadorGeraOrdemServico");

				} else {

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdUnidadeTramitacao("");
					httpServletRequest.setAttribute("nomeCampo",
							"idUnidadeTramitacao");
					httpServletRequest.setAttribute(
							"idUnidadeTramitacaoNaoEncontrado", "exception");
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoUnidadeTramitacao("Unidade Organizacional Inexistente");

				}

			}

			// Verifica se o c�digo foi digitado pela primeira vez ou se foi
			// modificado
			if (idTipoServicoOS != null
					&& !idTipoServicoOS.trim().equals("")
					&& (descricaoServicoOS == null || descricaoServicoOS.trim()
							.equals(""))) {

				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, idTipoServicoOS));

				Collection servicoTipoEncontrado = fachada.pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());

				if (servicoTipoEncontrado != null
						&& !servicoTipoEncontrado.isEmpty()) {

					// [SF0003] - Validar Tipo Servi�o
					fachada.verificarServicoTipoReferencia(new Integer(
							idTipoServicoOS));

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdServicoOS(""
									+ ((ServicoTipo) ((List) servicoTipoEncontrado)
											.get(0)).getId());
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoServicoOS(((ServicoTipo) ((List) servicoTipoEncontrado)
									.get(0)).getDescricao());
					httpServletRequest.setAttribute("idServicoOSNaoEncontrado",
							"true");

					httpServletRequest.setAttribute("nomeCampo",
							"adicionarTipoServico");

				} else {

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdServicoOS("");
					httpServletRequest.setAttribute("nomeCampo", "idServicoOS");
					httpServletRequest.setAttribute("idServicoOSNaoEncontrado",
							"exception");
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoServicoOS("Tipo Servi�o Inexistente");

				}

			}
			
			/*
			 * Colocado por Raphael Rossiter em 25/02/2008
			 * Verifica se o c�digo foi digitado pela primeira vez ou se foi  modificado
			 */
			if (idDebitoTipo != null && !idDebitoTipo.trim().equals("") && 
				(descricaoDebitoTipo == null || descricaoDebitoTipo.trim().equals(""))) {

				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.ID, idDebitoTipo));

				Collection debitoTipoEncontrado = fachada.pesquisar(
				filtroDebitoTipo, DebitoTipo.class.getName());

				if (debitoTipoEncontrado != null && !debitoTipoEncontrado.isEmpty()) {

					adicionarSolicitacaoEspecificacaoActionForm
					.setIdDebitoTipo(idDebitoTipo);
					
					adicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoDebitoTipo(((DebitoTipo) ((List) debitoTipoEncontrado)
					.get(0)).getDescricao());
					
					httpServletRequest.setAttribute("nomeCampo", "valorDebito");

				} else {

					//[FS0007] - Validar Tipo de d�bito
					adicionarSolicitacaoEspecificacaoActionForm
					.setIdDebitoTipo("");
					
					adicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoDebitoTipo("Tipo de D�bito Inexistente");
					
					httpServletRequest.setAttribute("corDebitoTipo", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idDebitoTipo");

				}

			}
            
			Boolean trocou = false;
            
            if ( httpServletRequest.getParameter("trocou") != null ){
                trocou = (Boolean) httpServletRequest.getParameter("trocou").equals("sim");
            }            
            
            
            
            adicionarEspecificacao( 
                    adicionarSolicitacaoEspecificacaoActionForm,
                    new SolicitacaoTipoEspecificacao(),
                    sessao,
                    fachada,
                    trocou);            

			FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
			Collection colecaoImovelSituacao = fachada.pesquisar(
					filtroEspecificacaoImovelSituacao,
					EspecificacaoImovelSituacao.class.getName());
			sessao.setAttribute("colecaoImovelSituacao", colecaoImovelSituacao);

			// -------Fim da Parte que trata do c�digo quando o usu�rio
			// tecla
			// enter

			// remove o retorno da
			// solicita��o_especifica��o_tipo_servico_adicionar_popup.jsp
			sessao.removeAttribute("retornarTelaPopup");
			sessao
					.removeAttribute("caminhoRetornoTelaPesquisaUnidadeOrganizacional");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoServico");            
		}
        
		return retorno;
	}

	/**
	 * 
	 * [UC0401] Manter tipo de solicitacao com especifica��es
	 * Mostra os dados necess�rios para a inclus�o do novo RA
	 *
	 * @author bruno
	 * @date 13/04/2009
	 *
	 * @param atualizarAdicionarSolicitacaoEspecificacaoActionForm
	 * @param solicitacaoTipoEspecificacao
	 * @param sessao
	 */
	private void adicionarEspecificacao( 
			AdicionarSolicitacaoEspecificacaoActionForm adicionarSolicitacaoEspecificacaoActionForm,
	        SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao,
	        HttpSession sessao,
	        Fachada fachada,
	        boolean trocou ){
	    
	    if ( trocou ){
	        if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
	        	adicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" + solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getId() );
	        } else {
	        	adicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" );
	        	adicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( "" );
	        }
	    }
	    
	   FiltroSolicitacaoTipo filtro = new FiltroSolicitacaoTipo();
	   filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA, 2 ) );
	   filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO, 1 ) );
	   filtro.setCampoOrderBy( "descricao" );
	   Collection<SolicitacaoTipo> colSolTip = fachada.pesquisar( filtro, SolicitacaoTipo.class.getName() );
	   
	   sessao.setAttribute( "colecaoTipoSolicitacao", colSolTip );                            
	
	   // Verificamos se o tipo de especifica��o j� foi informado
	   if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
	       
	       // Pesquisamos qual o tipo de solicitacao desta especifica��o
	       filtro.limparCamposOrderBy();
	       filtro.limparListaParametros();
	       filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.ID, solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getSolicitacaoTipo().getId() ) );
	       colSolTip = fachada.pesquisar( filtro, SolicitacaoTipo.class.getName() );
	       
	       SolicitacaoTipo solicitacaoTipo = ( SolicitacaoTipo ) Util.retonarObjetoDeColecao( colSolTip );                                
	       adicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( solicitacaoTipo.getId()+"" );
	   }
	   
	   Collection<SolicitacaoTipoEspecificacao> colSolTipEsp = new ArrayList();
	   
	   if ( !adicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao().equals( "" ) ){
	       FiltroSolicitacaoTipoEspecificacao filtro2 = new FiltroSolicitacaoTipoEspecificacao();
	       filtro2.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, adicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao() ) );
	       filtro2.setCampoOrderBy( "descricao" );
	       colSolTipEsp = fachada.pesquisar( filtro2, SolicitacaoTipoEspecificacao.class.getName() );
	   }
	   
	   sessao.setAttribute( "colecaoEspecificacao", colSolTipEsp );        
	}
    
    /**
	 * M�todo auxiliar para obter objeto de uma cole��o de "ArquivoProcedimentoOperacionalPadrao" a partir de um identificador
	 * @author Amelia Pessoa
     * @date 13/12/2011
	 * @param colecao
	 * @param identificador
	 * @return ArquivoProcedimentoOperacionalPadrao
	 */
	public ArquivoProcedimentoOperacionalPadrao obterArquivo(Collection<ArquivoProcedimentoOperacionalPadrao> colecao, String identificador){
		ArquivoProcedimentoOperacionalPadrao retorno=null;
		if (colecao!=null){
			for (ArquivoProcedimentoOperacionalPadrao pop: colecao){
				if (pop.getPosicao()==Integer.parseInt(identificador)){
					retorno = pop;
				}
			}
		}
		return retorno;
	}
}
