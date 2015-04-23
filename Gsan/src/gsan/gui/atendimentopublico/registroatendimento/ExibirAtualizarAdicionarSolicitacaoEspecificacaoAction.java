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
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 13/11/2006
 */
public class ExibirAtualizarAdicionarSolicitacaoEspecificacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAdicionarSolicitacaoEspecificacao");

		AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm = (AtualizarAdicionarSolicitacaoEspecificacaoActionForm) actionForm;
		//
		// String idSolicitacaoTipo = (String) sessao
		// .getAttribute("idSolicitacaoTipo");
		
		/* Inserido por Amelia Pessoa em 14/12/2011
		 * RM 5924 Adicionar arquivo de procedimentos operacionais padr�es POPs */
		String adicionar = httpServletRequest.getParameter("adicionar");
		String remover = httpServletRequest.getParameter("remover");
		String visualizar = httpServletRequest.getParameter("visualizar");
		Collection<ArquivoProcedimentoOperacionalPadrao> colecaoArquivos = null;
		Collection<SolicitacaoEspecificacaoHelper> colecaoHelpers = null;
		//Adicionando arquivo
		if (adicionar != null && !adicionar.equals("")){
	        ArquivoProcedimentoOperacionalPadrao arquivoInformado = new ArquivoProcedimentoOperacionalPadrao();
	        arquivoInformado.setArquivo(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getArquivo());
			arquivoInformado.setNomeArquivo(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getArquivo().getFileName());
			try {
				arquivoInformado.setBytesArquivo(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getArquivo().getFileData());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			arquivoInformado.setNomeExtensaoArquivo(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getArquivo().getContentType());
			
	        //Inserindo o arquivo na cole��o de visualiza��o
			
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
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdHelper(prox);
				colecaoArquivos = new ArrayList<ArquivoProcedimentoOperacionalPadrao>();
				
				sessao.setAttribute("proxId", ++prox);
				
				helper.setColecaoArquivos(colecaoArquivos);
				colecaoHelpers.add(helper);
				sessao.setAttribute("colecaoHelpers", colecaoHelpers);
				sessao.setAttribute("colecaoArquivos", colecaoArquivos);
			}
			
			arquivoInformado.setPosicao(colecaoArquivos.size());
			colecaoArquivos.add(arquivoInformado);
			
			return retorno;
			
		} else if (remover != null && !remover.equals("")){ //Removendo arquivo
			colecaoArquivos = (ArrayList<ArquivoProcedimentoOperacionalPadrao>) sessao.getAttribute("colecaoArquivos");
			ArquivoProcedimentoOperacionalPadrao itemRemover = obterArquivo(colecaoArquivos, remover);	
			colecaoArquivos.remove(itemRemover);	
			
			//Atualizar dados da sessao
			sessao.removeAttribute("colecaoArquivos");
			sessao.setAttribute("colecaoArquivos", colecaoArquivos);
			
			return retorno;
		} 
		//Fim RM 5924
		
		if (sessao.getAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm") != null) {

			atualizarAdicionarSolicitacaoEspecificacaoActionForm = 
				(AtualizarAdicionarSolicitacaoEspecificacaoActionForm) sessao
					.getAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm");

		}

		String posicao = null;

		Set colecaoEspecificacaoServicoTipo = null;

		if (httpServletRequest.getParameter("posicao") != null) {
			posicao = (String) httpServletRequest.getParameter("posicao");

			sessao.setAttribute("posicao", posicao);
			sessao.setAttribute("posicaoComponente", new Integer(posicao));

			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");

		}

		Integer posicaoComponente = null;

		Collection colecaoSolicitacaoTipoEspecificacao = 
			(Collection) sessao.getAttribute("colecaoSolicitacaoTipoEspecificacao");

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("inserir") != null) {
			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
			sessao.removeAttribute("atualizar");
			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");

		}

		if (httpServletRequest.getParameter("atualizar") != null) {

			httpServletRequest.setAttribute("atualizar", true);
			sessao.setAttribute("atualizar", true);
		}
		
		
		if (sessao.getAttribute("atualizar") != null && 
			httpServletRequest.getParameter("objetoConsulta") == null) {

			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setCabecalho("Atualizar");
			httpServletRequest.setAttribute("atualizar", true);

			sessao.removeAttribute("inserir");

			if (colecaoSolicitacaoTipoEspecificacao != null
					&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {

				if (sessao.getAttribute("posicaoComponente") != null) {
					posicaoComponente = (Integer) sessao.getAttribute("posicaoComponente");
				} else {
					posicaoComponente = 0;
				}

				sessao.removeAttribute("posicao");
				sessao.setAttribute("posicaoComponente", posicaoComponente);

				int index = 0;

				Iterator colecaoSolicitacaoTipoEspecificacaoIterator = colecaoSolicitacaoTipoEspecificacao.iterator();

				while (colecaoSolicitacaoTipoEspecificacaoIterator.hasNext()) {

					index++;

					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoIterator.next();

					if (index == posicaoComponente) {

						if (sessao.getAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm") == null) {

							sessao.setAttribute("idSolicitacaoEspecificacao",solicitacaoTipoEspecificacao.getId());

							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoSolicitacao(solicitacaoTipoEspecificacao.getDescricao());
							if (solicitacaoTipoEspecificacao.getDiasPrazo() == null) {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento("");
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento(""	+ solicitacaoTipoEspecificacao.getDiasPrazo());
							}
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoCalcada("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoCalcada());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoRua("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoRua());							
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoAgua(""	+ solicitacaoTipoEspecificacao.getIndicadorLigacaoAgua());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPoco(""	+ solicitacaoTipoEspecificacao.getIndicadorPoco());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoEsgoto(""	+ solicitacaoTipoEspecificacao.getIndicadorLigacaoEsgoto());
							//RM 5759
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteCancelarDebito(""	+ solicitacaoTipoEspecificacao.getIndicadorPermiteCancelarDebito());
							
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrancaMaterial("" + solicitacaoTipoEspecificacao.getIndicadorCobrancaMaterial());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorParecerEncerramento(""	+ solicitacaoTipoEspecificacao.getIndicadorParecerEncerramento());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarDebito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoDebito());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarCredito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoCredito());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCliente("" + solicitacaoTipoEspecificacao.getIndicadorCliente());
							
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorVerificarDebito("" + solicitacaoTipoEspecificacao.getIndicadorVerificarDebito());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorMatriculaImovel("" + solicitacaoTipoEspecificacao.getIndicadorMatricula());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorUrgencia("" + solicitacaoTipoEspecificacao.getIndicadorUrgencia());
							
							//RM 5924 - Am�lia Pessoa - lista de arquivos POP
							String primeiraVez = httpServletRequest.getParameter("first");
							if (primeiraVez != null && !primeiraVez.equals("")){
								//Collection<SolicitacaoEspecificacaoHelper> colecaoHelpers;
								colecaoArquivos = new ArrayList<ArquivoProcedimentoOperacionalPadrao>();
								Collection<ArquivoProcedimentoOperacionalPadrao> colecaoArquivosAux = null;
								colecaoHelpers = (Collection<SolicitacaoEspecificacaoHelper>) sessao.getAttribute("colecaoHelpers");
								Integer idHelper= null;
								if (colecaoHelpers!=null){
									for(SolicitacaoEspecificacaoHelper helper: colecaoHelpers){
										if(helper.getIdHelper()==solicitacaoTipoEspecificacao.getId()){
											colecaoArquivosAux = helper.getColecaoArquivos();
											idHelper = helper.getIdHelper();
										}
									}
									if (colecaoArquivosAux!=null){
										for (ArquivoProcedimentoOperacionalPadrao aux: colecaoArquivosAux){
											colecaoArquivos.add(aux);
										}
									}
								}
								sessao.removeAttribute("colecaoArquivos");
								sessao.removeAttribute("idHelper");
								sessao.setAttribute("colecaoArquivos", colecaoArquivos);
								sessao.setAttribute("idHelper", idHelper);
							}							
							if (solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao() != null
									&& !solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().equals("")) {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("" + solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().getId());
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("");
							}

							//Colocado por Raphael Rossiter em 26/02/2008
							if (solicitacaoTipoEspecificacao.getDebitoTipo() != null) {
								
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setIdDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
								
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setDescricaoDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
								
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setIdDebitoTipo("");
								
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setDescricaoDebitoTipo("");
							}
							
							//Colocado por Raphael Rossiter em 26/02/2008
							if (solicitacaoTipoEspecificacao.getValorDebito() != null){
								
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setValorDebito(Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getValorDebito()));
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setValorDebito("");
							}

							//Colocado por Rafael Corr�a em 14/08/2008
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorEncerramentoAutomatico()));
							//Colocado por Raphael Rossiter em 14/03/2008
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor()));							
							//Colocado por Raphael Rossiter em 14/03/2008
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorCobrarJuros()));
							// Colocado por Mariana Victor em 10/01/2011
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorInformarContaRA()));

							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarPagamentoDP(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorInformarPagamentoDuplicidade()));
							
							// colocado por Nathalia Santos em 27/04/2011
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorAlterarVencimentoAlternativo(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorAlterarVencimentoAlternativo()));
							
							// colocado por Davi Menezes em 30/08/2011
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLojaVirtual(
									String.valueOf(solicitacaoTipoEspecificacao.getIndicadorLojaVirtual()));
							
							/*RM 7642 - Flavio Ferreira - 20/11/203
							indicador Tipo de Especifica��o Listado Consultar Imovel */
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorTipoEspecificacaoListadoPopupConsultarImovel(
								String.valueOf(solicitacaoTipoEspecificacao.getIndicadorTipoEspecificacaoListadoPopupConsultarImovel()));
							
							//RM 5759 - Am�lia Pessoa - 05/12/2011
							//indicador Permite Cancelar Debito
							if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorPermiteCancelarDebito())){
								solicitacaoTipoEspecificacao.setIndicadorPermiteCancelarDebito(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorPermiteCancelarDebito()));
							}
							
							if (solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
									&& !solicitacaoTipoEspecificacao.getUnidadeOrganizacional().equals("")) {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("" + solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getId());
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getDescricao());
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("");
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao("");
							}
														
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGeraOrdemServico("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoOrdemServico());
							
							if (solicitacaoTipoEspecificacao.getServicoTipo() != null
									&& solicitacaoTipoEspecificacao.getServicoTipo().getId() != null) {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getId().toString());
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getDescricao());
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("");
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("");
							}
							
							if(solicitacaoTipoEspecificacao.getObservacao() != null 
									&& !solicitacaoTipoEspecificacao.getObservacao().equals("")){
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setObservacao(solicitacaoTipoEspecificacao.getObservacao());
							}
							
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setIndicadorDocumentoObrigatorio("" + solicitacaoTipoEspecificacao.getIndicadorDocumentoObrigatorio());
							
                            Boolean trocou = false;
                            
                            if ( httpServletRequest.getParameter("trocou") != null ){
                                trocou = (Boolean) httpServletRequest.getParameter("trocou").equals("sim");
                            }
                            
                            // Colocado por Bruno Barros
                            adicionarEspecificacao( 
                                    atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                                    solicitacaoTipoEspecificacao,
                                    sessao,
                                    fachada,
                                    trocou );
						}
						
						if (sessao.getAttribute("colecaoEspecificacaoServicoTipo") != null) {
							colecaoEspecificacaoServicoTipo = (Set) sessao.getAttribute("colecaoEspecificacaoServicoTipo");
							solicitacaoTipoEspecificacao.setEspecificacaoServicoTipos(colecaoEspecificacaoServicoTipo);
						}

						colecaoEspecificacaoServicoTipo = solicitacaoTipoEspecificacao.getEspecificacaoServicoTipos();

						httpServletRequest.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

						sessao.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

						FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
						Collection colecaoImovelSituacao = fachada.pesquisar(filtroEspecificacaoImovelSituacao,EspecificacaoImovelSituacao.class.getName());
						httpServletRequest.setAttribute("colecaoImovelSituacao", colecaoImovelSituacao);
						
						sessao.setAttribute( "atualizarAdicionarSolicitacaoEspecificacaoActionForm", atualizarAdicionarSolicitacaoEspecificacaoActionForm );
					}
					
				}

			}
		} else if (httpServletRequest.getParameter("objetoConsulta") == null){
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setCabecalho("Inserir");

			sessao.removeAttribute("atualizar");

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;

			if (sessao.getAttribute("solicitacaoTipoEspecificacao") == null) {

				solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();

			} else {
				solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) sessao.getAttribute("solicitacaoTipoEspecificacao");
			}
			if (sessao.getAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm") == null) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoSolicitacao(solicitacaoTipoEspecificacao.getDescricao());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoCalcada("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoCalcada());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoAgua(""	+ solicitacaoTipoEspecificacao.getIndicadorLigacaoAgua());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPoco(""	+ solicitacaoTipoEspecificacao.getIndicadorPoco());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoEsgoto(""	+ solicitacaoTipoEspecificacao.getIndicadorLigacaoEsgoto());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoRua("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoRua());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrancaMaterial("" + solicitacaoTipoEspecificacao.getIndicadorCobrancaMaterial());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorParecerEncerramento(""	+ solicitacaoTipoEspecificacao.getIndicadorParecerEncerramento());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarDebito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoDebito());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarCredito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoCredito());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCliente("" + solicitacaoTipoEspecificacao.getIndicadorCliente());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorVerificarDebito("" + solicitacaoTipoEspecificacao.getIndicadorVerificarDebito());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico("" + solicitacaoTipoEspecificacao.getIndicadorEncerramentoAutomatico());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorUrgencia("" + solicitacaoTipoEspecificacao.getIndicadorUrgencia());

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorMatriculaImovel("" + solicitacaoTipoEspecificacao.getIndicadorMatricula());
				if (solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao() != null
						&& !solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().equals("")) {
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("" + solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().getId());
				}

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGeraOrdemServico("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoOrdemServico());

				if (solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
						&& !solicitacaoTipoEspecificacao.getUnidadeOrganizacional().equals("")) {
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("" + solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getId());
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getDescricao());
				}
				if (solicitacaoTipoEspecificacao.getServicoTipo() != null
						&& !solicitacaoTipoEspecificacao.getServicoTipo().equals("")) {
					
					if (solicitacaoTipoEspecificacao.getServicoTipo().getId() != null){
						atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getId().toString());
						atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getDescricao());
					
					}
				}
				
				//Colocado por Raphael Rossiter em 26/02/2008
				if (solicitacaoTipoEspecificacao.getDebitoTipo() != null) {
					
					atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setIdDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
					
					atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
					
				}

				//Colocado por Raphael Rossiter em 26/02/2008
				if (solicitacaoTipoEspecificacao.getValorDebito() != null){
					
					atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setValorDebito(Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getValorDebito()));
				}
				
				if(solicitacaoTipoEspecificacao.getObservacao() != null 
						&& !solicitacaoTipoEspecificacao.getObservacao().equals("")){
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setObservacao(solicitacaoTipoEspecificacao.getObservacao());
				}
				
				//Colocado por Raphael Rossiter em 14/03/2008
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor(
				String.valueOf(solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor()));

				//Colocado por Raphael Rossiter em 14/03/2008
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros(
				String.valueOf(solicitacaoTipoEspecificacao.getIndicadorCobrarJuros()));

				//Colocado por Mariana Victor em 10/01/2011
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA(
				String.valueOf(solicitacaoTipoEspecificacao.getIndicadorInformarContaRA()));
				
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento( 
                        ( solicitacaoTipoEspecificacao.getDiasPrazo() == null ? "" : solicitacaoTipoEspecificacao.getDiasPrazo()+"" ) );
                
                // Colocado por Nathalia Santos em 27/04/2011
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorAlterarVencimentoAlternativo(
        				String.valueOf(solicitacaoTipoEspecificacao.getIndicadorAlterarVencimentoAlternativo()));
                
                // Colocado por Bruno Barros
                adicionarEspecificacao( 
                        atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                        solicitacaoTipoEspecificacao,
                        sessao,
                        fachada,
                        false );
				
			}
			
			// Colocado por Bruno Barros
            adicionarEspecificacao( 
                    atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                    solicitacaoTipoEspecificacao,
                    sessao,
                    fachada,
                    false );
            
			if (sessao.getAttribute("colecaoEspecificacaoServicoTipo") != null) {

				colecaoEspecificacaoServicoTipo = (Set) sessao.getAttribute("colecaoEspecificacaoServicoTipo");
				solicitacaoTipoEspecificacao.setEspecificacaoServicoTipos(colecaoEspecificacaoServicoTipo);

			}
			colecaoEspecificacaoServicoTipo = solicitacaoTipoEspecificacao.getEspecificacaoServicoTipos();

			if (httpServletRequest.getParameter("adicionar") != null) {

				colecaoEspecificacaoServicoTipo = null;
			}
			httpServletRequest.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

		} else {
		    sessao.setAttribute( "atualizarAdicionarSolicitacaoEspecificacaoActionForm", atualizarAdicionarSolicitacaoEspecificacaoActionForm );
            
            // Colocado por Bruno Barros
            adicionarEspecificacao( 
                    atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                    new SolicitacaoTipoEspecificacao(),
                    sessao,
                    fachada,
                    false );            
        }

		if (httpServletRequest.getParameter("desfazer") != null	&& !httpServletRequest.getParameter("desfazer").equals("")) {

			Integer idSolicitacaoEspecificacao = (Integer) sessao.getAttribute("idSolicitacaoEspecificacao");

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA);
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,idSolicitacaoEspecificacao.toString()));
			Collection colecaoSolicitacaoTipoEspecificacaoDesfazer = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao,SolicitacaoTipoEspecificacao.class.getName());

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoDesfazer.iterator().next();

			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoSolicitacao(solicitacaoTipoEspecificacao.getDescricao());
			if (solicitacaoTipoEspecificacao.getDiasPrazo() == null) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento("");
			} else {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento("" + solicitacaoTipoEspecificacao.getDiasPrazo());
			}
			atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setIndicadorPavimentoCalcada("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoCalcada());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoAgua("" + solicitacaoTipoEspecificacao.getIndicadorLigacaoAgua());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPoco("" + solicitacaoTipoEspecificacao.getIndicadorPoco());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoEsgoto("" + solicitacaoTipoEspecificacao.getIndicadorLigacaoEsgoto());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoRua("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoRua());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrancaMaterial("" + solicitacaoTipoEspecificacao.getIndicadorCobrancaMaterial());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorParecerEncerramento("" + solicitacaoTipoEspecificacao.getIndicadorParecerEncerramento());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarDebito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoDebito());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarCredito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoCredito());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCliente("" + solicitacaoTipoEspecificacao.getIndicadorCliente());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorVerificarDebito("" + solicitacaoTipoEspecificacao.getIndicadorVerificarDebito());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorMatriculaImovel("" + solicitacaoTipoEspecificacao.getIndicadorMatricula());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorUrgencia("" + solicitacaoTipoEspecificacao.getIndicadorUrgencia());
			if (solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao() != null
					&& !solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().equals("")) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("" + solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().getId());
			}

			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGeraOrdemServico("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoOrdemServico());

			if (solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
					&& !solicitacaoTipoEspecificacao.getUnidadeOrganizacional().equals("")) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("" + solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getId());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getDescricao());
			}
			if (solicitacaoTipoEspecificacao.getServicoTipo() != null
					&& !solicitacaoTipoEspecificacao.getServicoTipo().equals("")) {
				
				if (solicitacaoTipoEspecificacao.getServicoTipo().getId() != null){
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getId().toString());
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getDescricao());
				
				}
			
			}
			
			//Colocado por Raphael Rossiter em 26/02/2008
			if (solicitacaoTipoEspecificacao.getDebitoTipo() != null) {
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIdDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setDescricaoDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
				
			}

			//Colocado por Raphael Rossiter em 26/02/2008
			if (solicitacaoTipoEspecificacao.getValorDebito() != null){
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setValorDebito(Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getValorDebito()));
			}
			
			//Colocado por Raphael Rossiter em 14/03/2008
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor(
			String.valueOf(solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor()));
			
			//Colocado por Raphael Rossiter em 14/03/2008
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros(
			String.valueOf(solicitacaoTipoEspecificacao.getIndicadorCobrarJuros()));
			
			//Colocado por Rafael Corr�a em 14/08/2008
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico(
			String.valueOf(solicitacaoTipoEspecificacao.getIndicadorEncerramentoAutomatico()));

			//Colocado por Mariana Victor em 10/01/2011
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA(
			String.valueOf(solicitacaoTipoEspecificacao.getIndicadorInformarContaRA()));
			
			//Colocado por Nathalia Santos em 27/04/2011
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorAlterarVencimentoAlternativo(
					String.valueOf(solicitacaoTipoEspecificacao.getIndicadorAlterarVencimentoAlternativo()));
			
			if(solicitacaoTipoEspecificacao.getObservacao() != null 
					&& !solicitacaoTipoEspecificacao.getObservacao().equals("")){
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setObservacao(solicitacaoTipoEspecificacao.getObservacao());
			}
			
			atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIndicadorDocumentoObrigatorio("" + solicitacaoTipoEspecificacao.getIndicadorDocumentoObrigatorio());
			
			colecaoEspecificacaoServicoTipo = solicitacaoTipoEspecificacao.getEspecificacaoServicoTipos();

			httpServletRequest.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

			sessao.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

			FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
			Collection colecaoImovelSituacao = fachada.pesquisar(filtroEspecificacaoImovelSituacao,EspecificacaoImovelSituacao.class.getName());
			httpServletRequest.setAttribute("colecaoImovelSituacao",colecaoImovelSituacao);
            
            // Colocado por Bruno Barros
            adicionarEspecificacao( 
                    atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                    solicitacaoTipoEspecificacao,
                    sessao,
                    fachada,
                    false);
		}
		// caso exista o parametro ent�o limpa a sess�o e o form
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {

			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoSolicitacao("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoCalcada("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoAgua("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPoco("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoEsgoto("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoRua("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrancaMaterial("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorParecerEncerramento("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarDebito("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarCredito("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCliente("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorMatriculaImovel("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGeraOrdemServico("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("");
			
			//Colocado por Raphael Rossiter em 26/02/2008
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdDebitoTipo("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoDebitoTipo("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setValorDebito("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico("");
            
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorUrgencia("2");
			
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA("");
			
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setObservacao("");
			
			atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIndicadorDocumentoObrigatorio(ConstantesSistema.NAO.toString());
			
			// Colocado por Bruno Barros
	        adicionarEspecificacao( 
	                atualizarAdicionarSolicitacaoEspecificacaoActionForm,
	                new SolicitacaoTipoEspecificacao(),
	                sessao,
	                fachada,
	                true);          
            
			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
		}
		
		
        
		// Verifica se o tipoConsulta � diferente de nulo ou vazio.
		// Nesse caso � quando um o retorno da consulta vem para o action ao inves de ir
		// direto para o jsp
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// verifica se retornou da pesquisa de unidade de tramite
			if (httpServletRequest.getParameter("tipoConsulta").equals("unidadeAtendimento")) {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao(httpServletRequest.getParameter("idCampoEnviarDados"));
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(httpServletRequest.getParameter("descricaoCampoEnviarDados"));

			}
			// verifica se retornou da pesquisa de tipo de servi�o
			if (httpServletRequest.getParameter("tipoConsulta").equals("tipoServico")) {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS(httpServletRequest.getParameter("idCampoEnviarDados"));
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS(httpServletRequest.getParameter("descricaoCampoEnviarDados"));

			}
			
			/*
			 * Colocado por Raphael Rossiter em 25/02/2008
			 * Verifica se retornou da pesquisa de tipo de d�bito
			 */
			if (httpServletRequest.getParameter("tipoConsulta").equals("tipoDebito")) {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIdDebitoTipo(httpServletRequest.getParameter("idCampoEnviarDados"));

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setDescricaoDebitoTipo(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
		}
		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		String idUnidadeInicialTramitacao = atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdUnidadeTramitacao();
		String descricaoInicialTramitacao = atualizarAdicionarSolicitacaoEspecificacaoActionForm.getDescricaoUnidadeTramitacao();
		String idTipoServicoOS = (String) atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdServicoOS();
		String descricaoServicoOS = atualizarAdicionarSolicitacaoEspecificacaoActionForm.getDescricaoServicoOS();

		//Colocado por Raphael Rossiter em 26/02/2008
		String idDebitoTipo = (String) atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdDebitoTipo();
		String descricaoDebitoTipo = atualizarAdicionarSolicitacaoEspecificacaoActionForm.getDescricaoDebitoTipo();
		
		// Verifica se o c�digo foi digitado pela primeira vez ou se foi modificado
		if (idUnidadeInicialTramitacao != null
				&& !idUnidadeInicialTramitacao.trim().equals("")
				&& (descricaoInicialTramitacao == null || descricaoInicialTramitacao.trim().equals(""))) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,idUnidadeInicialTramitacao));
//			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection unidadeOrganizacionalEncontrado = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			if (unidadeOrganizacionalEncontrado != null && !unidadeOrganizacionalEncontrado.isEmpty()) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("" + ((UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado).get(0)).getId());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(((UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado).get(0)).getDescricao());
				httpServletRequest.setAttribute("idUnidadeTramitacaoNaoEncontrado", "true");
				httpServletRequest.setAttribute("nomeCampo","indicadorGeraOrdemServico");

			} else {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("");
				httpServletRequest.setAttribute("nomeCampo","idUnidadeTramitacao");
				httpServletRequest.setAttribute("idUnidadeTramitacaoNaoEncontrado", "exception");
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao("Unidade Organizacional Inexistente");

			}

		}

		// Verifica se o c�digo foi digitado pela primeira vez ou se foi modificado
		if (idTipoServicoOS != null	&& !idTipoServicoOS.trim().equals("")
				&& (descricaoServicoOS == null || descricaoServicoOS.trim().equals(""))) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idTipoServicoOS));
			Collection servicoTipoEncontrado = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

			if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {

				// [SF0003] - Validar Tipo Servi�o
				fachada.verificarServicoTipoReferencia(new Integer(idTipoServicoOS));

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("" + ((ServicoTipo) ((List) servicoTipoEncontrado).get(0)).getId());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS(((ServicoTipo) ((List) servicoTipoEncontrado).get(0)).getDescricao());
				httpServletRequest.setAttribute("idServicoOSNaoEncontrado",	"true");
				httpServletRequest.setAttribute("nomeCampo","adicionarTipoServico");

			} else {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("");
				httpServletRequest.setAttribute("nomeCampo", "idServicoOS");
				httpServletRequest.setAttribute("idServicoOSNaoEncontrado","exception");
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("Tipo Servi�o Inexistente");

			}

		}
		
		
		
		/*
		 * Colocado por Raphael Rossiter em 26/02/2008
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

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIdDebitoTipo(idDebitoTipo);
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setDescricaoDebitoTipo(((DebitoTipo) ((List) debitoTipoEncontrado)
				.get(0)).getDescricao());
				
				httpServletRequest.setAttribute("nomeCampo", "valorDebito");

			} else {

				//[FS0008] - Validar Tipo de d�bito
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIdDebitoTipo("");
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setDescricaoDebitoTipo("Tipo de D�bito Inexistente");
				
				httpServletRequest.setAttribute("corDebitoTipo", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idDebitoTipo");

			}

		}

		FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
		Collection colecaoImovelSituacao = fachada.pesquisar(filtroEspecificacaoImovelSituacao,EspecificacaoImovelSituacao.class.getName());
		sessao.setAttribute("colecaoImovelSituacao", colecaoImovelSituacao);

		// -------Fim da Parte que trata do c�digo quando o usu�rio
		// tecla
		// enter

		// remove o retorno da
		// solicita��o_especifica��o_tipo_servico_adicionar_popup.jsp
		sessao.removeAttribute("retornarTelaPopup");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaUnidadeOrganizacional");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoServico");
		
		sessao.removeAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm");
		
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
            AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm,
            SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao,
            HttpSession sessao,
            Fachada fachada,
            boolean trocou ){
        
        if ( trocou ){
            if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" + solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getId() );
            } else {
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" );
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( "" );
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
           atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( solicitacaoTipo.getId()+"" );
       }
       
       Collection<SolicitacaoTipoEspecificacao> colSolTipEsp = new ArrayList();
       
       if ( !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao().equals( "" ) ){
           FiltroSolicitacaoTipoEspecificacao filtro2 = new FiltroSolicitacaoTipoEspecificacao();
           filtro2.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao() ) );
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
