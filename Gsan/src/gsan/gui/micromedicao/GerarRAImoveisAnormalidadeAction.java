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
package gsan.gui.micromedicao;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.MeioSolicitacao;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gsan.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.operacional.DivisaoEsgoto;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir uma resolu��o de diretoria no banco
 * 
 * [UC0217] Inserir Resolu��o de Diretoria Permite inserir uma
 * 
 * @author Rafael Corr�a
 * @since 08/06/2008
 */
public class GerarRAImoveisAnormalidadeAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		GerarRAImoveisAnormalidadeActionForm gerarRAImoveisAnormalidadeActionForm = (GerarRAImoveisAnormalidadeActionForm) actionForm;
		
		// Recupera os im�veis selecionados pelo usu�rio
		Collection<Imovel> colecaoImoveisGerarOS = (Collection) sessao.getAttribute("colecaoImoveisGerarOS");
		HashMap<Integer, String> colecaoObservacaoOS = (HashMap<Integer, String>) sessao.getAttribute("colecaoObservacaoOS");
		
		int qtde = 0;
		

		if (colecaoImoveisGerarOS != null && !colecaoImoveisGerarOS.isEmpty()) {
			
			Integer idMeioSolicitacao = MeioSolicitacao.INTERNO;
			
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO);
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipoEspecificacao()));
			
			Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
			UnidadeOrganizacional unidadeAtendimento = fachada.pesquisarUnidadeUsuario(usuarioLogado.getId());
			
			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(new Date(),
							solicitacaoTipoEspecificacao.getId()); 
			
			for (Imovel imovel : colecaoImoveisGerarOS) {
				
				// Verifica se j� existe uma RA com esse tipo de especifica��o para este im�vel, caso exista descarta o im�vel
				FiltroRegistroAtendimento filtroRA = new FiltroRegistroAtendimento();
				filtroRA.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.IMOVEL_ID, imovel.getId()));
				filtroRA.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO_ESPECIFICACAO, solicitacaoTipoEspecificacao.getId()));
				filtroRA.adicionarParametro(new ParametroNulo(FiltroRegistroAtendimento.ID_ATENDIMENTO_MOTIVO_ENCERRAMENTO));
				
				Collection colecaoRA = fachada.pesquisar(filtroRA, RegistroAtendimento.class.getName());
				String observacao = colecaoObservacaoOS.get(imovel.getId());
				
				if (colecaoRA == null || colecaoRA.isEmpty()) {
					
					Date dataAtual = new Date();
					String dataAtendimento = Util.formatarData(dataAtual);
					String horaAtendimento = Util.formatarHoraSemData(dataAtual);

					Collection colecaoEnderecos = new ArrayList();
				
					Imovel imovelEndereco = fachada.pesquisarImovelParaEndereco(imovel.getId());
				
					colecaoEnderecos.add(imovelEndereco);
				
					qtde++;
					
					//Obter a unidade Destino
					ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto = fachada
					.habilitarGeograficoDivisaoEsgoto(new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipo()));
					
					// [SB0006] - Obt�m Divis�o de Esgoto
					DivisaoEsgoto divisaoEsgoto = fachada.obterDivisaoEsgoto(
							imovel.getQuadra().getId(),
							habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());

					UnidadeOrganizacional unidadeDestino = null;
					
					if (divisaoEsgoto != null) {
						
						unidadeDestino = fachada.definirUnidadeDestinoDivisaoEsgoto(
								new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipoEspecificacao()), 
								divisaoEsgoto.getId(),
								habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto(),
								imovel.getLocalidade().getId(), 
								new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipo()));
					}
					
					if ( unidadeDestino == null || unidadeDestino.equals("") ) {
					
						unidadeDestino = fachada.definirUnidadeDestinoLocalidade(
								new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipoEspecificacao()),
								imovel.getLocalidade().getId(),
								new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipo()),
								habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());
					}
					
					if(unidadeDestino == null){
						
						fachada.inserirRegistroAtendimento(
								
								// Indicador Atendimento OnLine
								new Short("1"), 
							
								// Data Atendimento / Hora Atendimento
								dataAtendimento, horaAtendimento,
							
								// Tempo Espera Inicial / Final
								null, null, 
							
								// Meio Solicita��o / Solicita��o Tipo Especifica��o
								idMeioSolicitacao, solicitacaoTipoEspecificacao.getId(), 
							
								// Data Prevista / Observa��o
								Util.formatarData(definirDataPrevistaUnidadeDestinoEspecificacaoHelper.getDataPrevista()), null,
							
								// Im�vel / Descri��o do Local da Ocorr�ncia / Solicita��o Tipo
								imovel.getId(), null, solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId(),
							
								// Cole��o de Endere�os / Ponto Refer�ncia Local Ocorr�ncia
								colecaoEnderecos, null, 
							
								// Bairro �rea
								null,
									
								// Localidade		
								imovel.getLocalidade().getId(), 
							
								// Setor Comercial
								imovel.getSetorComercial().getId(), 
									
								// Quadra		
								imovel.getQuadra().getId(),
							
								// Divis�o Esgoto / Local Ocorr�ncia
								null, null, 
							
								// Pavimento Rua / Pavimento Cal�ada
								imovel.getPavimentoRua().getId(), imovel.getPavimentoCalcada().getId(),
							
								// Unidade Atendimento / Usu�rio Logado
								unidadeAtendimento.getId(), usuarioLogado.getId(),
							
								// Cliente / Ponto Refer�ncia Solicitante
								null, null, 
							
								// Nome Solicitante / Novo Solicitante
								null, false,
							
								// Unidade Solicitante / Funcion�rio
								unidadeAtendimento.getId(), null, 
							
								// Cole��o Telefones / Cole��o Endere�os Solicitante
								null, null, 
							
								// Unidade Destino / Parecer Unidade Destino
								null, null, 
							
								// Servi�o Tipo / N�mero RA Manual / RA Gerado / Observa��o OS Fiscaliza��o
								solicitacaoTipoEspecificacao.getServicoTipo().getId(), null, null,null,null,ConstantesSistema.NAO, null, 
								null, null, observacao,null, null, null,null,null);
					
					}else{
					
					fachada.inserirRegistroAtendimento(
						
							// Indicador Atendimento OnLine
							new Short("1"), 
						
							// Data Atendimento / Hora Atendimento
							dataAtendimento, horaAtendimento,
						
							// Tempo Espera Inicial / Final
							null, null, 
						
							// Meio Solicita��o / Solicita��o Tipo Especifica��o
							idMeioSolicitacao, solicitacaoTipoEspecificacao.getId(), 
						
							// Data Prevista / Observa��o
							Util.formatarData(definirDataPrevistaUnidadeDestinoEspecificacaoHelper.getDataPrevista()), observacao,
						
							// Im�vel / Descri��o do Local da Ocorr�ncia / Solicita��o Tipo
							imovel.getId(), null, solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId(),
						
							// Cole��o de Endere�os / Ponto Refer�ncia Local Ocorr�ncia
							colecaoEnderecos, null, 
						
							// Bairro �rea
							null,
								
							// Localidade		
							imovel.getLocalidade().getId(), 
						
							// Setor Comercial
							imovel.getSetorComercial().getId(), 
								
							// Quadra		
							imovel.getQuadra().getId(),
						
							// Divis�o Esgoto / Local Ocorr�ncia
							null, null, 
						
							// Pavimento Rua / Pavimento Cal�ada
							imovel.getPavimentoRua().getId(), imovel.getPavimentoCalcada().getId(),
						
							// Unidade Atendimento / Usu�rio Logado
							unidadeAtendimento.getId(), usuarioLogado.getId(),
						
							// Cliente / Ponto Refer�ncia Solicitante
							null, null, 
						
							// Nome Solicitante / Novo Solicitante
							null, false,
						
							// Unidade Solicitante / Funcion�rio
							unidadeAtendimento.getId(), null, 
						
							// Cole��o Telefones / Cole��o Endere�os Solicitante
							null, null, 
						
							// Unidade Destino / Parecer Unidade Destino
							unidadeDestino.getId(),	null, 
						
							// Servi�o Tipo / N�mero RA Manual / RA Gerado / Observa��o OS Fiscaliza��o
							solicitacaoTipoEspecificacao.getServicoTipo().getId(), null, null,null,null,ConstantesSistema.NAO, null, null, null, observacao,
							null,null, null,null,null);
				
					}
					
					
				}else{
					fachada.verificarExistenciaRAImovelMesmaEspecificacao(imovel.getId(), solicitacaoTipoEspecificacao.getId());
				}
				
				
				if(observacao != null){
					Iterator<RegistroAtendimento> iterator = colecaoRA.iterator();
					StringBuilder sb = new StringBuilder();
					RegistroAtendimento ra = null;
					
					while (iterator.hasNext()){
						ra = iterator.next();
						OrdemServico os = fachada.pesquisarOrdemServicoRegistroAtendimento(ra);
						
						/*if(os.getObservacao() != null){
							sb.append(os.getObservacao());
							sb.append("; ");
						}*/
						
						sb.append(observacao);
						os.setObservacao(sb.toString());
						
						ra.setObservacao(observacao);
						fachada.atualizar(ra);
						
						fachada.atualizar(os);
						qtde++;
						
					}
					
					
				}
			}
		}

		// Monta a p�gina de sucesso de acordo com o padr�o do sistema.
		montarPaginaSucessoComVoltarJavascript(httpServletRequest, qtde
				+ " Registro(s) Atendimento(s) inclu�do(s) com sucesso.",
				"Efetuar Outra An�lise de Exce��es e Consumo",
				"exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos&menu=sim",
				"exibirFiltrarOrdemServicoAction.do?menu=sim",
				"Manter Ordem de Servi�o",
				"Voltar",
				"javascript:history.back();");

		return retorno;

	}

}