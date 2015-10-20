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
package gcom.gui.cobranca;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.bean.CobrancaCronogramaHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarCobrancaCronogramaAction extends GcomAction {

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
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		String confirmacao = httpServletRequest.getParameter("confirmado");

		Collection atividadesCobrancaObrigatoriedadeAtivo = (Collection) sessao
				.getAttribute("atividadesCobrancaObrigatoriedadeAtivo");

		// Collection acoesCobranca =
		// (Collection)sessao.getAttribute("acoesCobranca");
		// Collection atividadesCobranca =
		// (Collection)sessao.getAttribute("atividadesCobranca");
		Collection cobrancasAtividadesParaInsercao = new ArrayList();
		Collection colecaoCobrancaCronogramaHelper = new ArrayList();
		Collection colecaoCobrancaCronogramaHelperSessao = (Collection) sessao
				.getAttribute("colecaoCobrancaCronogramaHelper");

		// CobrancaAcao cobrancaAcao = null;
		// CobrancaAtividade cobrancaAtividade = null;
		CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes = null;
		// CobrancaAcaoCronograma cobrancaAcaoCronograma = null;
		CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
		CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
		CobrancaCronogramaHelper cobrancaCronogramaHelper = null;
		Collection cronogramasPraRemocao = new ArrayList();

		cobrancaGrupo
				.setId(new Integer(cobrancaActionForm.getIdGrupoCobranca()));

		Iterator iteratorCobrancaCronogramaHelperSessao = colecaoCobrancaCronogramaHelperSessao
				.iterator();

		String idAcaoCobranca = "";
		String qtdMaximaDocumentos = "";
		String dataPrevista = "";
		// String anoMes = "";
		// String mesAno = "";

		//se n�o vem da p�gina de confirma��o
		if (confirmacao == null || !confirmacao.trim().equalsIgnoreCase("ok")){
			int contadorAtividades = 0;
	
			while (iteratorCobrancaCronogramaHelperSessao.hasNext()) {
				cobrancaCronogramaHelper = (CobrancaCronogramaHelper) iteratorCobrancaCronogramaHelperSessao
						.next();
	
				Iterator iteratorCobrancaAcaoAtividadeCronograma = cobrancaCronogramaHelper
						.getCobrancasAtividadesParaInsercao().iterator();
	
				// cobrancaAcao =
				// cobrancaCronogramaHelper.getCobrancaAcaoCronograma().getCobrancaAcao();
	
				// ----seta os valores no objeto CobrancaGrupoCronogramaMes
				if (cobrancaCronogramaHelper.getCobrancaGrupoCronogramaMes() != null) {
					cobrancaGrupoCronogramaMes = cobrancaCronogramaHelper
							.getCobrancaGrupoCronogramaMes();
				} else {
					cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes) sessao
							.getAttribute("cobrancaGrupoCronogramaMes");
				}
				// mesAno = cobrancaActionForm.getMesAno();
				// String mes = mesAno.substring(0, 2);
				// String ano = mesAno.substring(3, 7);
				// anoMes = ano + "" + mes;
	
				// cobrancaGrupoCronogramaMes.setUltimaAlteracao(new Date());
	
				// cobrancaCronogramaHelper.setCobrancaGrupoCronogramaMes(cobrancaGrupoCronogramaMes);
	
				// ----contador utilizado para verificar se ha alguma atividade com
				// data preenchida quando comandaer for nulo
				int verificaDataPreenchida = 0;
	
				// ----seta os valores no objeto CobrancaAcaoCronograma
				// cobrancaAcaoCronograma =
				// cobrancaCronogramaHelper.getCobrancaAcaoCronograma();
	
				// ------ se o indicador de obrigatoriedade for igual a sim(1)
				/**
				 * Caso o usu�rio n�o informe data prevista para todas as atividades
				 * das a��es que obrigatoriamente devem constar no
				 * cronograma(CBAC_ICOBRIGATORIEDADE=1), exibir a mensagem "�
				 * necess�rio informar a data prevista para as atividades das a��es
				 * que obrigatoriamente devem constar no cronograma" e retornar para
				 * o passo correspodente no fluxo principal.
				 */
				if (cobrancaCronogramaHelper.getCobrancaAcaoCronograma()
						.getCobrancaAcao().getIndicadorObrigatoriedade().intValue() == 1) {
					while (iteratorCobrancaAcaoAtividadeCronograma.hasNext()) {
						contadorAtividades += 1;
	
						cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iteratorCobrancaAcaoAtividadeCronograma
								.next();
	
						// --------pega o valor de comandar.Ex: comandar2
						idAcaoCobranca = httpServletRequest
								.getParameter("comandar"
										+ cobrancaCronogramaHelper
												.getCobrancaAcaoCronograma()
												.getCobrancaAcao().getId()
												.toString()
										+ "atividade"
										+ cobrancaAcaoAtividadeCronograma
												.getCobrancaAtividade().getId());
						// -------- separa o id da string comandar
	
						qtdMaximaDocumentos = httpServletRequest.getParameter("qtd_a"
								+ cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao().getId() + "n"
								+ cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getId());
	
						// Verifica se foi preenchido o campo de quantidade maxima de documento e
						// seta no objeto 
						if (qtdMaximaDocumentos != null && !qtdMaximaDocumentos.equals("")){
							cobrancaAcaoAtividadeCronograma.setQuantidadeMaximaDocumentos(
								new Integer(qtdMaximaDocumentos));
						}
						
						dataPrevista = "";
						dataPrevista = httpServletRequest.getParameter("a"
								+ cobrancaAcaoAtividadeCronograma
										.getCobrancaAcaoCronograma()
										.getCobrancaAcao().getId()
								+ "n"
								+ cobrancaAcaoAtividadeCronograma
										.getCobrancaAtividade().getId());
	
						if (dataPrevista != null
								&& dataPrevista.trim().equals("")
								&& cobrancaAcaoAtividadeCronograma
										.getCobrancaAtividade()
										.getIndicadorObrigatoriedade() == 1) {
							throw new ActionServletException(
									"atencao.cobranca.data_prevista_acao_obrigatoria");
						}
						if (dataPrevista != null
								&& !dataPrevista.trim().equals("")) {
							cobrancaAcaoAtividadeCronograma
									.setDataPrevista(Util
											.converteStringParaDate(dataPrevista));
							if (idAcaoCobranca != null
									&& idAcaoCobranca.trim().equals("1")) {
								cobrancaAcaoAtividadeCronograma
										.setComando(Util
												.converteStringParaDateHora(dataPrevista
														+ " "
														+ Util
																.formatarHoraSemData(new Date())));
							} else {
								cobrancaAcaoAtividadeCronograma
										.setComando(null);
							}
	
							cobrancasAtividadesParaInsercao
									.add(cobrancaAcaoAtividadeCronograma);
						}
					}
				} else {
					verificaDataPreenchida = 0;
					while (iteratorCobrancaAcaoAtividadeCronograma.hasNext()) {
						contadorAtividades += 1;
	
						cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iteratorCobrancaAcaoAtividadeCronograma
								.next();
	
						CobrancaAtividade cobrancaAtividade = cobrancaAcaoAtividadeCronograma
								.getCobrancaAtividade();
	
						// --------pega o valor de comandar.Ex: comandar2
						idAcaoCobranca = httpServletRequest
								.getParameter("comandar"
										+ cobrancaCronogramaHelper
												.getCobrancaAcaoCronograma()
												.getCobrancaAcao().getId()
												.toString()
										+ "atividade"
										+ cobrancaAcaoAtividadeCronograma
												.getCobrancaAtividade().getId());
						// -------- separa o id da string comandar
	
						qtdMaximaDocumentos = httpServletRequest.getParameter("qtd_a"
								+ cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao().getId() + "n"
								+ cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getId());
						
						// Verifica se foi preenchido o campo de quantidade maxima de documento e
						// seta no objeto 
						if (qtdMaximaDocumentos != null && !qtdMaximaDocumentos.equals("")){
							cobrancaAcaoAtividadeCronograma.setQuantidadeMaximaDocumentos(
								new Integer(qtdMaximaDocumentos));
						}
						
						dataPrevista = "";
						dataPrevista = httpServletRequest.getParameter("a"
								+ cobrancaAcaoAtividadeCronograma
										.getCobrancaAcaoCronograma()
										.getCobrancaAcao().getId()
								+ "n"
								+ cobrancaAcaoAtividadeCronograma
										.getCobrancaAtividade().getId());
	
						if ((dataPrevista != null && !dataPrevista.trim()
								.equals(""))
								|| cobrancaAtividade
										.getIndicadorObrigatoriedade()
										.equals(
												CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO)) {
							verificaDataPreenchida += 1;
							// ----seta os valores no objeto
							// CobrancaAcaoAtividadeCronograma
							cobrancaAcaoAtividadeCronograma.setDataPrevista(Util
									.converteStringParaDate(dataPrevista));
							if (idAcaoCobranca != null
									&& idAcaoCobranca.trim().equals("1")) {
								cobrancaAcaoAtividadeCronograma
										.setComando(Util
												.converteStringParaDateHora(dataPrevista
														+ " "
														+ Util
																.formatarHoraSemData(new Date())));
							} else {
								cobrancaAcaoAtividadeCronograma.setComando(null);
							}
						} else {
							cobrancaAcaoAtividadeCronograma.setDataPrevista(null);
							cobrancaAcaoAtividadeCronograma.setComando(null);
						}
						if (cobrancaAcaoAtividadeCronograma.getDataPrevista() != null
								&& !cobrancaAcaoAtividadeCronograma
										.getDataPrevista().equals("")) {
							cobrancasAtividadesParaInsercao
									.add(cobrancaAcaoAtividadeCronograma);
						}
					}
					/**
					 * Caso o usuario informe a data prevista somente para algumas
					 * atividades da acao, exibir a mensagem "� necess�rio informar
					 * a data prevista para todas as atividades da a��o."
					 */
					if ((verificaDataPreenchida > 0)
							&& (verificaDataPreenchida < atividadesCobrancaObrigatoriedadeAtivo
									.size())) {
						throw new ActionServletException(
								"atencao.cobranca.data_prevista_algumas_atividades");
					}
				}
				cobrancaCronogramaHelper
						.setCobrancasAtividadesParaInsercao(cobrancasAtividadesParaInsercao);
				if (cobrancaCronogramaHelper.getCobrancaGrupoCronogramaMes() == null) {
					cobrancaCronogramaHelper
							.setCobrancaGrupoCronogramaMes(cobrancaGrupoCronogramaMes);
				}

				cobrancaCronogramaHelper.setCritica1(false);
				cobrancaCronogramaHelper.setCritica2(false);
				cobrancaCronogramaHelper.setCritica3(false);
				colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelper);
				cobrancasAtividadesParaInsercao = new ArrayList();
			}
			
			if (contadorAtividades == 0) {
				throw new ActionServletException(
						"atencao.cobranca.nenhuma_atividade");
			}
		}else{
			colecaoCobrancaCronogramaHelper = (Collection) sessao.getAttribute("colecaoCobrancaCronogramaHelper");
			cobrancaGrupo = (CobrancaGrupo) sessao.getAttribute("cobrancaGrupo");
			cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes) sessao.getAttribute("cobrancaGrupoCronogramaMes");
		}
		
		
		for (Iterator iterCobrancacronogramaHelper = colecaoCobrancaCronogramaHelper.iterator(); iterCobrancacronogramaHelper
		.hasNext();) {
			CobrancaCronogramaHelper cobrancaCronogramaHelperAux = (CobrancaCronogramaHelper) iterCobrancacronogramaHelper.next();
			
			
			//[FS0006] - Verificar a��o predecessora
			// se a��o tem predecessora
			if (cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getCobrancaAcaoPredecessora() != null){
				
				for (Iterator iterCobrancaAcaoAtividadeCronograma = cobrancaCronogramaHelperAux.getCobrancasAtividadesParaInsercao()
						.iterator(); iterCobrancaAcaoAtividadeCronograma.hasNext();) {
					
					CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma2 = (CobrancaAcaoAtividadeCronograma) iterCobrancaAcaoAtividadeCronograma.next();
					
					//se a atividade for EMITIR
					if (cobrancaAcaoAtividadeCronograma2.getCobrancaAtividade().getId().intValue() == CobrancaAtividade.EMITIR){
						
						
						//Caso a atividade EMITIR da a��o predecessora j� tenha perdido a validade
						CobrancaAcao cobrancaAcaoPredecessora = cobrancaCronogramaHelperAux
						.getCobrancaAcaoCronograma().getCobrancaAcao().getCobrancaAcaoPredecessora();
						
						//recupera cobrancaAcaoAtividadeCronograma referente a a��o predecessora da a��o em quest�o
						cobrancaAcaoAtividadeCronograma =  recuperaAcaoPredecessora(cobrancaAcaoPredecessora.getId(), colecaoCobrancaCronogramaHelper);
						
						if (cobrancaCronogramaHelperAux.getCritica1() == false){
							if (cobrancaAcaoAtividadeCronograma != null){
								//data de vencimento da a��o predecessora
								Date dataVencimentoAcaoPredecessora = Util.adicionarNumeroDiasDeUmaData(cobrancaAcaoAtividadeCronograma.getDataPrevista(),
										cobrancaAcaoPredecessora.getNumeroDiasValidade().intValue());
								
								if (!(cobrancaAcaoAtividadeCronograma2.getDataPrevista().compareTo(dataVencimentoAcaoPredecessora) <= 0)){
									
									//seta os atributos na sess�o para uso posterior
									colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
									cobrancaCronogramaHelperAux.setCritica1(true);
									colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
									sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
									sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
									sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
									sessao.setAttribute("reexibirCritica", "true");
									
									//mapear para p�gina de confirma��o
									sessao.setAttribute("caminhoActionConclusao",
									"/gsan/atualizarCobrancaCronogramaAction.do");
									
									// Monta a p�gina de confirma��o
									// Exibe a pergunta: Confirma data da atividade para a a��o <<descri��o da a��o>> ? 
									// Se o usu�rio confirmar prosseguir, caso contr�rio aguardar a 
									// informa��o da nova data
									return montarPaginaConfirmacao(
											"atencao.data_validade_acao_predecessora_vencido",
											httpServletRequest, actionMapping, new String[] {cobrancaAcaoPredecessora.getDescricaoCobrancaAcao(),
											cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao()});
			
								}
							}
						}
						
						if (cobrancaCronogramaHelperAux.getCritica2() == false){
							//Caso a data informada para a atividade EMITIR n�o seja maior que NN dias da atividade EMITIR da a��o predecessora
							if (cobrancaAcaoAtividadeCronograma != null){
								
								//data de vencimento da a��o predecessora
								//if colocado pois bases de algumas empresas esse campo 
								//est� nulo para a��es que s�o predecessoras de outra
								if (cobrancaAcaoPredecessora.getNumeroDiasMinimoAcaoPrecedente() != null ){
									Date dataVencimentoAcaoPredecessora = Util.adicionarNumeroDiasDeUmaData(cobrancaAcaoAtividadeCronograma.getDataPrevista(),
											cobrancaAcaoPredecessora.getNumeroDiasMinimoAcaoPrecedente());
									
									if (!(cobrancaAcaoAtividadeCronograma2.getDataPrevista().compareTo(dataVencimentoAcaoPredecessora) >= 0)){
										
										//seta os atributos na sess�o para uso posterior
										colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
										cobrancaCronogramaHelperAux.setCritica2(true);
										colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
										sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
										sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
										sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
										sessao.setAttribute("reexibirCritica", "true");
										
										// Monta a p�gina de confirma��o
										// Exibe a pergunta: Confirma data da atividade para a a��o <<descri��o da a��o>> ? 
										// Se o usu�rio confirmar prosseguir, caso contr�rio aguardar a 
										// informa��o da nova data
										sessao.setAttribute("caminhoActionConclusao",
										"/gsan/atualizarCobrancaCronogramaAction.do");
										
										// Monta a p�gina de confirma��o
										return montarPaginaConfirmacao(
												"atencao.intervalo_dias_minimo_entre_acao_e_predecessora",
												httpServletRequest, actionMapping,
												new String[] { cobrancaAcaoPredecessora.getDescricaoCobrancaAcao(), cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao()
													,cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao()});
									}
								}
							}
						}
						
						
						break;
					}
					
				}
			}else{
				//[FS0010] - Verificar a��o sem predecessora
				// Para cada A��o de Cobran�a que n�o tenha a��o predecessora (CBAC_IDACAOPRECEDENTE com valor igual a nulo):
				
				for (Iterator iterCobrancaAcaoAtividadeCronograma = cobrancaCronogramaHelperAux.getCobrancasAtividadesParaInsercao()
						.iterator(); iterCobrancaAcaoAtividadeCronograma.hasNext();) {
					
					CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma2 = (CobrancaAcaoAtividadeCronograma) iterCobrancaAcaoAtividadeCronograma.next();
					
					if (cobrancaAcaoAtividadeCronograma2.getCobrancaAtividade().getId().intValue() == CobrancaAtividade.EMITIR
							|| cobrancaAcaoAtividadeCronograma2.getCobrancaAtividade().getId().intValue() == CobrancaAtividade.SIMULAR){
						
						FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
						filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoGrupo.ID, new Integer(cobrancaActionForm.getIdGrupoCobranca())));
						
						Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
						FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
						
						/*Caso exista grupo de faturamento, ou seja, possua registro (na tabela FATURAMENTO_GRUPO 
						 * onde FTGR_ID = CBGR_ID da tabela da tabela COBRANCA_GRUPO e FTGR_AMREFERENCIA <> NULL 
						 * e FTGR_NNDIAVENCIMENTO <> NULL) */
						if (faturamentoGrupo != null
								&& faturamentoGrupo.getAnoMesReferencia() != null
								&& faturamentoGrupo.getDiaVencimento() != null){
							
							/*Caso a data de vencimento das contas do �ltimo ciclo de faturamento do grupo de 
							 * faturamento correspondente ao grupo de cobran�a para o qual est� sendo informado 
							 * o cronograma (compor a data de vencimento com o dia de vencimento + m�s/ano de 
							 * refer�ncia da tabela FATURAMENT_GRUPO) somada ao n�mero de dias para considerar 
							 * d�bito, obtido na tabela SISTEMA_PARAMETROS, maior ou igual a data informada 
							 * para a atividade EMITIR OU SIMULAR da a��o em quest�o*/
							int diaVencimentoGrupo = faturamentoGrupo.getDiaVencimento();
							int mesVencimentoGrupo = new Integer(faturamentoGrupo.getAnoMesReferencia().toString().substring(4)).intValue();
							int anoVencimentoGrupo = new Integer(faturamentoGrupo.getAnoMesReferencia().toString().substring(0,4)).intValue();
							
							Date dateVencimentoGrupo = Util.criarData(diaVencimentoGrupo, mesVencimentoGrupo, anoVencimentoGrupo);
							
							FiltroSistemaParametro filtroSistemaParametro= new FiltroSistemaParametro();
							Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
							
							if (colecaoSistemaParametro != null && !colecaoSistemaParametro.isEmpty()) {
								
								SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
								
								dateVencimentoGrupo = Util.adicionarNumeroDiasDeUmaData(dateVencimentoGrupo, sistemaParametro.getNumeroDiasVencimentoCobranca().intValue());
								
								if (cobrancaCronogramaHelperAux.getCritica3() == false){
									if (cobrancaAcaoAtividadeCronograma2.getDataPrevista().compareTo(dateVencimentoGrupo) < 0){
						
										colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
										cobrancaCronogramaHelperAux.setCritica3(true);
										colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
										sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
										sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
										sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
										sessao.setAttribute("reexibirCritica", "true");
										
										// mapear para p�gina de confirma��o
										httpServletRequest.setAttribute("caminhoActionConclusao",
										"/gsan/atualizarCobrancaCronogramaAction.do");
										
										// Monta a p�gina de confirma��o
										// Exibe a pergunta: Confirma data da atividade para a a��o <<descri��o da a��o>> ? 
										// Se o usu�rio confirmar prosseguir, caso contr�rio aguardar a 
										// informa��o da nova data
										return montarPaginaConfirmacao(
												"atencao.acao_nao_contemplara_contas",
												httpServletRequest, actionMapping, new String[] {cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao(),
														Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia())});
									}
								}
							}
						}
						
					}
				}
			}
			
		}
		
		

		Collection colecaoCronogramaHelperErroAtualizacao = (Collection) sessao
				.getAttribute("colecaoCobrancaCronogramaHelperErroAtualizacao");

		Collections.sort((List) colecaoCobrancaCronogramaHelper,
				new Comparator() {
					public int compare(Object a, Object b) {
						Short posicao1 = ((CobrancaCronogramaHelper) a)
								.getCobrancaAcaoCronograma().getCobrancaAcao()
								.getOrdemRealizacao();
						Short posicao2 = ((CobrancaCronogramaHelper) b)
								.getCobrancaAcaoCronograma().getCobrancaAcao()
								.getOrdemRealizacao();

						return posicao1.compareTo(posicao2);
					}
				});

		// -----Chama o metodo atualizarCobrancaCronograma da fachada
		fachada.atualizarCobrancaCronograma(colecaoCobrancaCronogramaHelper,
				colecaoCronogramaHelperErroAtualizacao, this
						.getUsuarioLogado(httpServletRequest));

		// **************************************************************
		// ******************* REMO��O **********************************
		cobrancaActionForm.getIdRegistrosRemocao();

		// metodo q remove as atividades
		if (cobrancaActionForm.getIdRegistrosRemocao() != null) {
			String[] ids = cobrancaActionForm.getIdRegistrosRemocao();

			String[] arraySucessora = new String[ids.length];
			int contadorSucessora = 0;

			// testa se uma atividade tem sucessora se tiver nao pode ser
			// removida
			Iterator iteratorTesteAcaoSucessora = colecaoCobrancaCronogramaHelperSessao
					.iterator();
			CobrancaCronogramaHelper cobrancaCronogramaHelperSucessora = null;
			while (iteratorTesteAcaoSucessora.hasNext()) {
				cobrancaCronogramaHelperSucessora = (CobrancaCronogramaHelper) iteratorTesteAcaoSucessora
						.next();
				for (int i = 0; i < ids.length; i++) {
					if (cobrancaCronogramaHelperSucessora
							.getCobrancaAcaoCronograma().getCobrancaAcao()
							.getCobrancaAcaoPredecessora() != null
							&& cobrancaCronogramaHelperSucessora
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao()
									.getCobrancaAcaoPredecessora().getId()
									.toString().equalsIgnoreCase(ids[i])) {

						arraySucessora[contadorSucessora] = cobrancaCronogramaHelperSucessora
								.getCobrancaAcaoCronograma().getCobrancaAcao()
								.getCobrancaAcaoPredecessora().getId()
								.toString();

						contadorSucessora += 1;
					}
				}
				//
				if (arraySucessora != null && contadorSucessora > 0) {
					boolean contemIdNaRemocao = false;
					for (int y = 0; y < arraySucessora.length; y++) {
						contemIdNaRemocao = false;
						for (int x = 0; x < ids.length; x++) {
							if (arraySucessora[y] != null
									&& arraySucessora[y]
											.equalsIgnoreCase(ids[x])) {
								contemIdNaRemocao = true;
							}
						}
					}
					if (contemIdNaRemocao) {
						throw new ActionServletException(
								"atencao.dependencias.nao_remover_com_acao_sucessora");
					}
				}

			}

			CobrancaCronogramaHelper cobrancaCronogramaHelperRemover = null;
			for (int i = 0; i < ids.length; i++) {

				iteratorCobrancaCronogramaHelperSessao = colecaoCobrancaCronogramaHelper
						.iterator();
				while (iteratorCobrancaCronogramaHelperSessao.hasNext()) {

					cobrancaCronogramaHelperRemover = (CobrancaCronogramaHelper) iteratorCobrancaCronogramaHelperSessao
							.next();
					if (cobrancaCronogramaHelperRemover
							.getCobrancaAcaoCronograma() != null
							&& cobrancaCronogramaHelperRemover
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao() != null
							&& cobrancaCronogramaHelperRemover
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao().getId().toString()
									.equalsIgnoreCase(ids[i])) {

						cronogramasPraRemocao
								.add(cobrancaCronogramaHelperRemover);

					}
				}
			}

		}

		if (cobrancaActionForm.getIdRegistrosRemocao() != null) {
			Iterator iterarRemocao = cronogramasPraRemocao.iterator();
			Iterator iteratorRemoverDaColecao = colecaoCobrancaCronogramaHelper
					.iterator();
			String[] idsRemocao = new String[cronogramasPraRemocao.size()];
			CobrancaCronogramaHelper cobrancaCronogramaHelperRemocao = null;
			int i = 0;
			while (iterarRemocao.hasNext()) {
				cobrancaCronogramaHelperRemocao = (CobrancaCronogramaHelper) iterarRemocao
						.next();
				idsRemocao[i] = cobrancaCronogramaHelperRemocao
						.getCobrancaAcaoCronograma().getCobrancaAcao().getId()
						.toString();
				iteratorRemoverDaColecao = colecaoCobrancaCronogramaHelper
						.iterator();
				while (iteratorRemoverDaColecao.hasNext()) {
					cobrancaCronogramaHelperRemocao = (CobrancaCronogramaHelper) iteratorRemoverDaColecao
							.next();
					if (idsRemocao[i]
							.equalsIgnoreCase(cobrancaCronogramaHelperRemocao
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao().getId().toString())) {
						iteratorRemoverDaColecao.remove();
					}

				}
				i += 1;
			}
			i = 0;
			if (!cronogramasPraRemocao.isEmpty()) {
				fachada.removerCobrancaCronograma(cronogramasPraRemocao);
			}
		}

		// **************************************************************

		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
				FiltroCobrancaGrupo.ID, cobrancaGrupo.getId()));

		Collection colecaoCobrancaGrupo = fachada.pesquisar(
				filtroCobrancaGrupo, CobrancaGrupo.class.getName());
		CobrancaGrupo cobrancaGrupoExibicao = (CobrancaGrupo) colecaoCobrancaGrupo
				.iterator().next();

		//remove da sess�o objetos n�o mais utiliz�veis
		sessao.removeAttribute("colecaoCobrancaCronogramaHelper");
		sessao.removeAttribute("cobrancaGrupo");
		sessao.removeAttribute("cobrancaGrupoCronogramaMes");
		sessao.removeAttribute("reexibirCritica");
		
		montarPaginaSucesso(httpServletRequest, "Cronograma de Cobran�a do "
				+ cobrancaGrupoExibicao.getDescricao()
				+ " referente a "
				+ Util.formatarAnoMesParaMesAno(cobrancaGrupoCronogramaMes
						.getAnoMesReferencia()) + " atualizado com sucesso.",
				"Atualizar outro Cronograma de Cobran�a",
				"exibirFiltrarCobrancaCronogramaAction.do?menu=sim");

		return retorno;
	}
	
	private CobrancaAcaoAtividadeCronograma recuperaAcaoPredecessora(int idCobrancaAcao, Collection colecaoCobrancaCronogramaHelper){
		CobrancaAcaoAtividadeCronograma retorno = null;
		
		for (Iterator iter = colecaoCobrancaCronogramaHelper.iterator(); iter
				.hasNext();) {
			CobrancaCronogramaHelper cobrancaCronogramaHelper = (CobrancaCronogramaHelper) iter.next();
			
			if (cobrancaCronogramaHelper.getCobrancaAcaoCronograma() != null){
				if (cobrancaCronogramaHelper.getCobrancaAcaoCronograma().getCobrancaAcao().getId().intValue() == idCobrancaAcao){
					for (Iterator iterator = cobrancaCronogramaHelper.getCobrancasAtividadesParaInsercao()
							.iterator(); iterator.hasNext();) {
						CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iterator.next();
						
						if (cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getId().intValue() == CobrancaAtividade.EMITIR){
							retorno = cobrancaAcaoAtividadeCronograma;
							break;
						}
						
					}
					break;
				}
			}
			
		}
		
		return retorno;
	}

}
