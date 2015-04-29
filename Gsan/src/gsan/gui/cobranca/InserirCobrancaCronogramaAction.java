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
package gsan.gui.cobranca;

import gsan.cadastro.sistemaparametro.FiltroSistemaParametro;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaAcaoAtividadeCronograma;
import gsan.cobranca.CobrancaAcaoCronograma;
import gsan.cobranca.CobrancaAtividade;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.CobrancaGrupoCronogramaMes;
import gsan.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gsan.cobranca.FiltroCobrancaAcaoCronograma;
import gsan.cobranca.FiltroCobrancaAtividade;
import gsan.cobranca.FiltroCobrancaGrupo;
import gsan.cobranca.FiltroCobrancaGrupoCronogramaMes;
import gsan.cobranca.bean.AcaoEAtividadeCobrancaHelper;
import gsan.cobranca.bean.CobrancaCronogramaHelper;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesColecao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Administrador
 */
public class InserirCobrancaCronogramaAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String confirmacao = httpServletRequest.getParameter("confirmado");

		
//		Collection acoesCobranca = (Collection) sessao.getAttribute("acoesCobranca");
		Collection atividadesCobranca = (Collection) sessao.getAttribute("atividadesCobranca");
//		Collection atividadesCobrancaObrigatoriedadeAtivo = (Collection) sessao.getAttribute("atividadesCobrancaObrigatoriedadeAtivo");

		Collection colecaoAcaoEAtividadeCobranca = (Collection)sessao.getAttribute("colecaoAcaoEAtividadeCobranca");
		Iterator iterAcaoEAtividadeCobranca = colecaoAcaoEAtividadeCobranca.iterator();
		
		Collection cobrancasAtividadesParaInsercao = new ArrayList();
		Collection colecaoCobrancaCronogramaHelper = new ArrayList();

		CobrancaAcao cobrancaAcao = null;
		CobrancaAtividade cobrancaAtividade = null;
		CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes = null;
		CobrancaAcaoCronograma cobrancaAcaoCronograma = null;
		CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
		CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
		CobrancaCronogramaHelper cobrancaCronogramaHelper = null;

		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, 
			new Integer(cobrancaActionForm.getIdGrupoCobranca())));
		Collection colecaoCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());	
		
		if(!Util.isVazioOrNulo(colecaoCobrancaGrupo)){
			cobrancaGrupo = (CobrancaGrupo) Util.retonarObjetoDeColecao(colecaoCobrancaGrupo);
		}
		
		//cobrancaGrupo.setId(new Integer(cobrancaActionForm.getIdGrupoCobranca()));

//		Iterator iteratorAcaoCobranca = acoesCobranca.iterator();

		String idAcaoCobranca = "";
		String qtdMaximaDocumentos = "";
		String dataPrevista = "";
		String anoMes = "";
		String mesAno = "";

		//se n�o vem da p�gina de confirma��o
		if (confirmacao == null || !confirmacao.trim().equalsIgnoreCase("ok")){
			int contadorAtividades = 0;

			
			while (iterAcaoEAtividadeCobranca.hasNext()) {
				AcaoEAtividadeCobrancaHelper helper = (AcaoEAtividadeCobrancaHelper) iterAcaoEAtividadeCobranca.next();
				
				cobrancaAcao = helper.getAcaoCobranca();
				atividadesCobranca = helper.getAtividadesCobranca();
//			}
//			while (iteratorAcaoCobranca.hasNext()) {
				Iterator iteratorAtividadesCobranca = atividadesCobranca.iterator();
				cobrancaCronogramaHelper = new CobrancaCronogramaHelper();
				cobrancasAtividadesParaInsercao = new ArrayList();

//				cobrancaAcao = (CobrancaAcao) iteratorAcaoCobranca.next();

				// ----seta os valores no objeto CobrancaGrupoCronogramaMes
				cobrancaGrupoCronogramaMes = new CobrancaGrupoCronogramaMes();
				cobrancaGrupoCronogramaMes.setCobrancaGrupo(cobrancaGrupo);
				mesAno = cobrancaActionForm.getMesAno();
				String mes = mesAno.substring(0, 2);
				String ano = mesAno.substring(3, 7);
				anoMes = ano + "" + mes;
				cobrancaGrupoCronogramaMes.setAnoMesReferencia(Integer.parseInt(anoMes));
				// cobrancaGrupoCronogramaMes.setUltimaAlteracao(new Date());

				cobrancaCronogramaHelper.setCobrancaGrupoCronogramaMes(cobrancaGrupoCronogramaMes);

				// ----contador utilizado para verificar se ha alguma atividade com
				// data preenchida quando comandaer for nulo
				int verificaDataPreenchida = 0;

				// ----seta os valores no objeto CobrancaAcaoCronograma
				cobrancaAcaoCronograma = new CobrancaAcaoCronograma();
				cobrancaAcaoCronograma.setCobrancaAcao(cobrancaAcao);
				cobrancaCronogramaHelper.setCobrancaAcaoCronograma(cobrancaAcaoCronograma);

				// ------ se o indicador de obrigatoriedade for igual a sim(1)
				/**
				 * Caso o usu�rio n�o informe data prevista para todas as atividades
				 * das a��es que obrigatoriamente devem constar no
				 * cronograma(CBAC_ICOBRIGATORIEDADE=1), exibir a mensagem "�
				 * necess�rio informar a data prevista para as atividades das a��es
				 * que obrigatoriamente devem constar no cronograma" e retornar para
				 * o passo correspodente no fluxo principal.
				 */
				if (cobrancaAcao.getIndicadorObrigatoriedade().intValue() == 1) {
					while (iteratorAtividadesCobranca.hasNext()) {
						contadorAtividades += 1;

						cobrancaAtividade = (CobrancaAtividade) iteratorAtividadesCobranca.next();
						
						qtdMaximaDocumentos = (String) httpServletRequest.getParameter("qtd_a"
								+ cobrancaAcao.getId().toString() + "n"
								+ cobrancaAtividade.getId().toString());
						
						dataPrevista = "";
						dataPrevista = (String) httpServletRequest.getParameter("a"
								+ cobrancaAcao.getId().toString() + "n"
								+ cobrancaAtividade.getId().toString());
						
						
						if (dataPrevista.trim().equals("") && cobrancaAtividade.getIndicadorObrigatoriedade() == 1) {
							throw new ActionServletException("atencao.cobranca.data_prevista_acao_obrigatoria");
						} else {
							// cobrancaAcao =
							// (CobrancaAcao)iteratorAcaoCobranca.next();
							// --------pega o valor de comandar.Ex: comandar2
							idAcaoCobranca = (String) httpServletRequest.getParameter("comandar"
											+ cobrancaAcao.getId().toString()
											+ "atividade" + cobrancaAtividade.getId());
							// -------- separa o id da string comandar
							// ----seta os valores no objeto
							// CobrancaAcaoAtividadeCronograma
							cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
							cobrancaAcaoAtividadeCronograma.setCobrancaAtividade(cobrancaAtividade);

							// Verifica se foi preenchido o campo de quantidade maxima de documento e
							// seta no objeto 
							if (qtdMaximaDocumentos != null && !qtdMaximaDocumentos.equals("")){
								cobrancaAcaoAtividadeCronograma.setQuantidadeMaximaDocumentos(new Integer(qtdMaximaDocumentos));
							}
							
							if (!dataPrevista.trim().equals("")) {
								
								Date DataPrevistaAcao = Util.converteStringParaDate(dataPrevista);
								//  [FS0004]  Validar Datas Previstas
								//	Caso o usu�rio informe uma data prevista para qualquer atividade de qualquer a��o 
								//	com o m�s/ano menor que o m�s/ano da data atual, exibir a mensagem 
								//	'Data Prevista deve ser maior do que a data atual.' e retornar para o passo 
								//  correspondente no fluxo principal
								if(DataPrevistaAcao.compareTo(Util.formatarDataSemHora(new Date()))<0){
									throw new ActionServletException("atencao.data_prevista_menor_data_atual");
								}
								
								/**
								 * Caso a data informada tenha o m�s/ano informado igual ao m�s/ano informado da 
								 * primeira atividade de outro cronograma do mesmo grupo (m�s/ano informado igual a
								 * m�s/ano de CAAC_DTPREVISTA da tabela COBRANCA_ACAO_ATIVIDADE_CRONOG com 
								 * CBGR_ID = CBGR_ID no grupo selecionado para o menor CBCR_ID do mesmo CBCM_ID).
								 */
								
								if (cobrancaGrupo.getIndicadorExecucaoAutomatica().equals(ConstantesSistema.SIM)){
								
									String mesPrevisto = dataPrevista.substring(3, 5);
									String anoPrevisto = dataPrevista.substring(6, 10);
									String anoMesPrevisto = anoPrevisto + "" + mesPrevisto;
									
									Collection colecaoDataPrevista = fachada.pesquisarDataPrevista(cobrancaGrupo);
									
									if(!Util.isVazioOrNulo(colecaoDataPrevista)){
										
										Iterator it = colecaoDataPrevista.iterator();
										
										while(it.hasNext()){
											String dataPrevistaComparar = (String) it.next();
											String mesPrevistoComparar = dataPrevistaComparar.substring(5, 7);
											String anoPrevistoComparar = dataPrevistaComparar.substring(0, 4);
											String anoMesPrevistoComparar = anoPrevistoComparar + "" + mesPrevistoComparar;
											
											if(Util.compararAnoMesReferencia(anoMesPrevisto, anoMesPrevistoComparar, "=")){
												throw new ActionServletException("atencao.data_prevista_j�_inserida_no_cronograma", null, 
													cobrancaGrupo.getDescricao());											
											}
										}								
									}

								}
								
								cobrancaAcaoAtividadeCronograma.setDataPrevista(Util.converteStringParaDate(dataPrevista));
								if (idAcaoCobranca != null	&& idAcaoCobranca.trim().equals("1")) {
									cobrancaAcaoAtividadeCronograma.setComando(Util
										.converteStringParaDateHora(dataPrevista
										+ " " + Util.formatarHoraSemData(new Date())));
								} else {
									cobrancaAcaoAtividadeCronograma.setComando(null);
								}
								
								cobrancasAtividadesParaInsercao.add(cobrancaAcaoAtividadeCronograma);
							}
						}
					}
				} else {
					verificaDataPreenchida = 0;
					while (iteratorAtividadesCobranca.hasNext()) {
						contadorAtividades += 1;

						cobrancaAtividade = (CobrancaAtividade) iteratorAtividadesCobranca.next();

						qtdMaximaDocumentos = (String) httpServletRequest.getParameter("qtd_a"
								+ cobrancaAcao.getId().toString() + "n"
								+ cobrancaAtividade.getId().toString());
						
						dataPrevista = "";
						dataPrevista = (String) httpServletRequest.getParameter("a"
								+ cobrancaAcao.getId().toString() + "n"
								+ cobrancaAtividade.getId().toString());
						
						

						cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
						cobrancaAcaoAtividadeCronograma.setCobrancaAtividade(cobrancaAtividade);
						
						// Verifica se foi preenchido o campo de quantidade maxima de documento e seta no objeto 
						if (qtdMaximaDocumentos != null && !qtdMaximaDocumentos.equals("")){
							cobrancaAcaoAtividadeCronograma.setQuantidadeMaximaDocumentos(new Integer(qtdMaximaDocumentos));
						}					

						if (!dataPrevista.trim().equals("")
								|| cobrancaAtividade.getIndicadorObrigatoriedade()
										.equals(CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO)) {
							verificaDataPreenchida += 1;

							if (!dataPrevista.trim().equals("")) {
								
								Date DataPrevistaAcao = Util.converteStringParaDate(dataPrevista);
								//  [FS0004]  Validar Datas Previstas
								//	Caso o usu�rio informe uma data prevista para qualquer atividade de qualquer a��o 
								//	com o m�s/ano menor que o m�s/ano da data atual, exibir a mensagem 
								//	'Data Prevista deve ser maior do que a data atual.' e retornar para o passo 
								//  correspondente no fluxo principal
								if(DataPrevistaAcao.compareTo(Util.formatarDataSemHora(new Date()))<0){
									throw new ActionServletException("atencao.data_prevista_menor_data_atual");
								}
								
								// --------pega o valor de comandar.Ex: comandar2
								idAcaoCobranca = (String) httpServletRequest.getParameter("comandar"
												+ cobrancaAcao.getId().toString()
												+ "atividade"	+ cobrancaAtividade.getId());
								// -------- separa o id da string comandar

								// ----seta os valores no objeto
								// CobrancaAcaoAtividadeCronograma
								cobrancaAcaoAtividadeCronograma.setDataPrevista(Util.converteStringParaDate(dataPrevista));
								if (idAcaoCobranca != null && idAcaoCobranca.trim().equals("1")) {
									cobrancaAcaoAtividadeCronograma
											.setComando(Util.converteStringParaDateHora(dataPrevista
											+ " " + Util.formatarHoraSemData(new Date())));
								} else {
									cobrancaAcaoAtividadeCronograma.setComando(null);
								}
							}
						} else {
							cobrancaAcaoAtividadeCronograma.setDataPrevista(null);
							cobrancaAcaoAtividadeCronograma.setComando(null);
						}
						if (cobrancaAcaoAtividadeCronograma.getDataPrevista() != null
								&& !cobrancaAcaoAtividadeCronograma.getDataPrevista().equals("")) {
							
							cobrancasAtividadesParaInsercao.add(cobrancaAcaoAtividadeCronograma);
						}
					}
					/**
					 * Caso o usuario informe a data prevista somente para algumas
					 * atividades da acao, exibir a mensagem "� necess�rio informar
					 * a data prevista para todas as atividades da a��o."
					 */
					if (verificaDataPreenchida > 0) {
						
						FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
						filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
						filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
						filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_OBRIGATORIEDADE, CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO));
						filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
						filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
								FiltroCobrancaAtividade.ID_COBRANCA_ACAO, cobrancaAcao.getId()));
						Collection atividadesCobrancaObrigatoriedadeAtivo  = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());

						if(atividadesCobrancaObrigatoriedadeAtivo == null || atividadesCobrancaObrigatoriedadeAtivo.isEmpty()){
			
							filtroCobrancaAtividade = new FiltroCobrancaAtividade();
							filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
							filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
							filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_OBRIGATORIEDADE, CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO));
							filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
							filtroCobrancaAtividade.adicionarParametro(new ParametroNulo(FiltroCobrancaAtividade.ID_COBRANCA_ACAO));
							
							atividadesCobrancaObrigatoriedadeAtivo = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
						}
						
						if(verificaDataPreenchida < atividadesCobrancaObrigatoriedadeAtivo.size()){
							throw new ActionServletException("atencao.cobranca.data_prevista_algumas_atividades");
						}
						
						
					}
				}
				
				cobrancaCronogramaHelper.setCritica1(false);
				cobrancaCronogramaHelper.setCritica2(false);
				cobrancaCronogramaHelper.setCritica3(false);
				cobrancaCronogramaHelper.setCobrancasAtividadesParaInsercao(cobrancasAtividadesParaInsercao);
				colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelper);
			}

			if (contadorAtividades == 0) {
				throw new ActionServletException("atencao.cobranca.nenhuma_atividade");
			}

		}else{
			colecaoCobrancaCronogramaHelper = (Collection) sessao.getAttribute("colecaoCobrancaCronogramaHelper");
			cobrancaGrupo = (CobrancaGrupo) sessao.getAttribute("cobrancaGrupo");
			mesAno = (String) sessao.getAttribute("mesAno");
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
									//colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
									cobrancaCronogramaHelperAux.setCritica1(true);
									//colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
									sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
									sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
									sessao.setAttribute("mesAno", mesAno);
									sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
									sessao.setAttribute("reexibirCritica", "true");
									
									// mapear para p�gina de confirma��o
									httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/inserirCobrancaCronogramaAction.do");
									
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
								if (cobrancaAcaoPredecessora.getNumeroDiasMinimoAcaoPrecedente() != null){
									Date dataVencimentoAcaoPredecessora = Util.adicionarNumeroDiasDeUmaData(cobrancaAcaoAtividadeCronograma.getDataPrevista(),
										cobrancaAcaoPredecessora.getNumeroDiasMinimoAcaoPrecedente());
								
									if (!(cobrancaAcaoAtividadeCronograma2.getDataPrevista().compareTo(dataVencimentoAcaoPredecessora) >= 0)){
										
										//seta os atributos na sess�o para uso posterior
										//colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
										cobrancaCronogramaHelperAux.setCritica2(true);
										//colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
										sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
										sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
										sessao.setAttribute("mesAno", mesAno);
										sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
										sessao.setAttribute("reexibirCritica", "true");
										
										//mapear para p�gina de confirma��o
										httpServletRequest.setAttribute("caminhoActionConclusao",
										"/gsan/inserirCobrancaCronogramaAction.do");
										
										// Monta a p�gina de confirma��o
										// Exibe a pergunta: Confirma data da atividade para a a��o <<descri��o da a��o>> ? 
										// Se o usu�rio confirmar prosseguir, caso contr�rio aguardar a 
										// informa��o da nova data
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
										
//										colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
										cobrancaCronogramaHelperAux.setCritica3(true);
//										colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
										sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
										sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
										sessao.setAttribute("mesAno", mesAno);
										sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
										sessao.setAttribute("reexibirCritica", "true");
										
										// mapear para p�gina de confirma��o
										httpServletRequest.setAttribute("caminhoActionConclusao",
										"/gsan/inserirCobrancaCronogramaAction.do");
										
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
		
		
		
		
		// -----Chama o metodo inserirCobrancaCronograma da fachada
		fachada.inserirCobrancaCronograma(colecaoCobrancaCronogramaHelper, this
				.getUsuarioLogado(httpServletRequest));

		

	
		CobrancaGrupo cobrancaGrupoExibicao = (CobrancaGrupo) colecaoCobrancaGrupo.iterator().next();

		FiltroCobrancaGrupoCronogramaMes filtroCobrancaGrupoCronogramaMes = new FiltroCobrancaGrupoCronogramaMes();
		filtroCobrancaGrupoCronogramaMes.adicionarParametro(new ParametroSimples(
				FiltroCobrancaGrupoCronogramaMes.ID_COBRANCA_GRUPO,	cobrancaGrupo.getId()));
		filtroCobrancaGrupoCronogramaMes.adicionarParametro(new ParametroSimples(
						FiltroCobrancaGrupoCronogramaMes.ANO_MES_REFERENCIA,
						Util.formatarMesAnoParaAnoMesSemBarra(mesAno)));

		Collection colecaoCobrancaGrupoCronogramaMes = fachada.pesquisar(
				filtroCobrancaGrupoCronogramaMes, CobrancaGrupoCronogramaMes.class.getName());
		CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMesAtualizacao = null;
		cobrancaGrupoCronogramaMesAtualizacao = (CobrancaGrupoCronogramaMes) colecaoCobrancaGrupoCronogramaMes.iterator().next();

		//remove da sess�o objetos n�o mais utiliz�veis
		sessao.removeAttribute("colecaoCobrancaCronogramaHelper");
		sessao.removeAttribute("cobrancaGrupo");
		sessao.removeAttribute("mesAno");
		sessao.removeAttribute("cobrancaGrupoCronogramaMes");
		sessao.removeAttribute("reexibirCritica");
		
		montarPaginaSucesso(httpServletRequest,
			"Cronograma de Cobran�a do " + cobrancaGrupoExibicao.getDescricao()	+ " referente a "
			+ Util.formatarAnoMesParaMesAno(cobrancaGrupoCronogramaMes.getAnoMesReferencia())
			+ " inserido com sucesso.",	"Inserir outro Cronograma de Cobran�a",
			"exibirInserirCronogramaCobrancaAction.do?menu=sim",
			"exibirAtualizarCobrancaCronogramaAction.do?filtro=S&menu=sim&idRegistroAtualizacao="
			+ cobrancaGrupoCronogramaMesAtualizacao.getId().toString(),
			"Atualizar o Cronograma de Cobran�a inserido");

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
