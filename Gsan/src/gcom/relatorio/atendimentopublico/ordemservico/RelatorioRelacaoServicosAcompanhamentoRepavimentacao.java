
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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane, Hugo Leonardo
 * @created 28/02/2008, 13/12/2010
 * @version 1.0
 */

public class RelatorioRelacaoServicosAcompanhamentoRepavimentacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorExclusaoMotivo object
	 */
	public RelatorioRelacaoServicosAcompanhamentoRepavimentacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO_NOVO);
	}

	@Deprecated
	public RelatorioRelacaoServicosAcompanhamentoRepavimentacao() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param NegativadorExclusaoMotivo Parametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os par�metros que ser�o utilizados no relat�rio
		//FiltroNegativadorExclusaoMotivo  filtroNegativadorExclusaoMotivo  = (FiltroNegativadorExclusaoMotivo) getParametro("filtroNegativadorExclusaoMotivo");
		OSPavimentoHelper oSPavimentoHelperParametros = (OSPavimentoHelper) getParametro("parametros");
		String escolhaRelatorio = (String) getParametro("escolhaRelatorio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String indicadorOsObservacaoRetorno = (String) getParametro("indicadorOsObservacaoRetorno");
		
		if(indicadorOsObservacaoRetorno != null && 
			new Short(indicadorOsObservacaoRetorno).shortValue() == ConstantesSistema.SIM.shortValue()){
			
			oSPavimentoHelperParametros.setIndicadorOsObservacaoRetorno(ConstantesSistema.SIM);
		}else{
			oSPavimentoHelperParametros.setIndicadorOsObservacaoRetorno(ConstantesSistema.NAO);
		}
		
		
		// Flag's
		Boolean achouPrimeiraOcorrenciaPendente = false;
		Boolean indicadorPrimeiraOcorrenciaPendente = false;
		
		Boolean indicadorUltimaOcorrenciaRejeitada = false;
		
		Boolean achouPrimeiraOcorrenciaConcluida = false;
		Boolean indicadorPrimeiraOcorrenciaConcluida = false;
		
		Boolean achouPrimeiraOcorrenciaRejeitada = false;
		Boolean indicadorPrimeiraOcorrenciaRejeitada = false;
			
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio

		ArrayList relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean relatorioBean = null;
		
		Collection<OSPavimentoRetornoHelper> collOrdemServicoPavimentoHelper = fachada.
				pesquisarRelatorioOrdemProcessoRepavimentacao(oSPavimentoHelperParametros, escolhaRelatorio);
	

		if (collOrdemServicoPavimentoHelper != null && !collOrdemServicoPavimentoHelper.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio
				
				// Situa��o Retorno
				String situacaoRetorno = "";
				
				// numeroOS
				String numeroOS = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico() != null) {
					numeroOS = oSPavimentoRetornoHelper.getOrdemServico().getId().toString();
				}
				
				// matricula do im�vel
				String matricula = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico()!= null && oSPavimentoRetornoHelper.getOrdemServico().getImovel() != null) {
					matricula = oSPavimentoRetornoHelper.getOrdemServico().getImovel().getId().toString();
				}
				
				// endereco
				String endereco = "";				
				if (oSPavimentoRetornoHelper.getEndereco() != null) {
					endereco = oSPavimentoRetornoHelper.getEndereco();
				}
									
				 // data encerramento da OS
				String dataEncerramento = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico() != null && oSPavimentoRetornoHelper.getOrdemServico().getDataEncerramento()!= null) {
					dataEncerramento = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServico().getDataEncerramento());
				}
				
				
				// tipo pvto rua
				String tipoPvtoRua = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada()!= null) {
					tipoPvtoRua = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada();
				}
				
				// Metr. (m�) indicada.
				BigDecimal metragem = null;				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
					metragem =oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua();
				}
				
				//...............................................................................................
				// data da conclus�o
				String dataConclusao = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataExecucao()!= null) {
					dataConclusao = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataExecucao());
				}
				//...............................................................................................
								
				//tipo pvto rua retorno.
				String tipoPvtoRuaRetorno = "";
				
				// flag usada para agrupar as ordens de servi�os entre: Pendentes e Conclu�das	
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() == null ) {
					
					tipoPvtoRuaRetorno = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada();
				
					situacaoRetorno = "2"; // CONCLUIDAS
					
					if(indicadorPrimeiraOcorrenciaConcluida == false && achouPrimeiraOcorrenciaConcluida == false){
						indicadorPrimeiraOcorrenciaConcluida = true;
						achouPrimeiraOcorrenciaConcluida = true;
						
					}else{
						indicadorPrimeiraOcorrenciaConcluida = false;
					}
				}else if(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() == null){
					
					situacaoRetorno = "1"; // PENDENTES
					
					if(indicadorPrimeiraOcorrenciaPendente == false && achouPrimeiraOcorrenciaPendente == false){
						indicadorPrimeiraOcorrenciaPendente = true;
						achouPrimeiraOcorrenciaPendente = true;
						
					}else{
						indicadorPrimeiraOcorrenciaPendente = false;
					}
				}
				
				// Metr. (m�) indicada retorno.
				BigDecimal metragemRetorno = null;				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
					metragemRetorno = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno();
				}
				
				
				// Motivo Rejei��o
				String motivoRejeicao = "";
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() != null){
					
					situacaoRetorno = "4"; // REJEITADAS
					motivoRejeicao = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao().getDescricao();
					
					if(indicadorPrimeiraOcorrenciaRejeitada == false && achouPrimeiraOcorrenciaRejeitada == false){
						indicadorPrimeiraOcorrenciaRejeitada = true;
						achouPrimeiraOcorrenciaRejeitada = true;
						
					}else{
						indicadorPrimeiraOcorrenciaRejeitada = false;
					}
				}else{
					
					if(indicadorPrimeiraOcorrenciaRejeitada == true && indicadorUltimaOcorrenciaRejeitada == false ){
						indicadorUltimaOcorrenciaRejeitada = true;
					
					}else{
						indicadorUltimaOcorrenciaRejeitada = false;
					}
				}
				
				String observacao = "";
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento().getObservacao() != null){
					observacao = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getObservacao();
				}
				
				// data da rejei��o
				String dataRejeicao = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataRejeicao()!= null) {
					dataRejeicao = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataRejeicao());
				}
				
				//Verifica o tipo de relatorio a ser gerado � COMPLETO
				if (escolhaRelatorio != null && escolhaRelatorio.equals("1")){
					
					//Inicializa o construtor constitu�do dos campos
					// necess�rios para a impress�o do relatorio
					relatorioBean = 
						new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
							numeroOS,
							matricula,
							endereco,
							dataEncerramento,
							tipoPvtoRua,
							metragem,
							dataConclusao,
							tipoPvtoRuaRetorno,
							metragemRetorno,
							oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_Rua(),
							oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_RuaRet()
					);
					
					relatorioBean.setDataRejeicao(dataRejeicao);
					relatorioBean.setSituacaoRetorno(situacaoRetorno);
					relatorioBean.setIndicadorPrimeiraOcorrenciaConcluida(indicadorPrimeiraOcorrenciaConcluida);
					relatorioBean.setIndicadorPrimeiraOcorrenciaPendente(indicadorPrimeiraOcorrenciaPendente);
					relatorioBean.setIndicadorPrimeiraOcorrenciaRejeitada(indicadorPrimeiraOcorrenciaRejeitada);
					relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(indicadorUltimaOcorrenciaRejeitada);
					relatorioBean.setMotivoRejeicao(motivoRejeicao);
					relatorioBean.setObservacao(observacao);
					
					
					//adiciona o bean a cole��o
					relatorioBeans.add(relatorioBean);
					
				//Verifica se o Tipo do relatorio a ser gerado � DIVERGENTE
				} else if (escolhaRelatorio != null && escolhaRelatorio.equals("2") ) {
					Integer validacaoAceite = calculaPercentualMetragemEValidaRetorno( metragem, metragemRetorno ) ;
					//Validar se oSPavimentoRetorno � divergente, pois esse relatorio � divergente! 
					if ( ( tipoPvtoRuaRetorno != null && !tipoPvtoRuaRetorno.equals("") && !tipoPvtoRuaRetorno.equals(tipoPvtoRua) ) ||
							 ( validacaoAceite != null && validacaoAceite.toString().equals("2") ) ) {
						
						//Inicializa o construtor constitu�do dos campos
						// necess�rios para a impress�o do relatorio
						relatorioBean = 
							new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
								numeroOS,
								matricula,
								endereco,
								dataEncerramento,
								tipoPvtoRua,
								metragem,
								dataConclusao,
								tipoPvtoRuaRetorno,
								metragemRetorno,
								oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_Rua(),
								oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_RuaRet()
					   );
						
						relatorioBean.setDataRejeicao(dataRejeicao);
						relatorioBean.setSituacaoRetorno(situacaoRetorno);
						relatorioBean.setIndicadorPrimeiraOcorrenciaConcluida(indicadorPrimeiraOcorrenciaConcluida);
						relatorioBean.setIndicadorPrimeiraOcorrenciaPendente(indicadorPrimeiraOcorrenciaPendente);
						relatorioBean.setIndicadorPrimeiraOcorrenciaRejeitada(indicadorPrimeiraOcorrenciaRejeitada);
						relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(indicadorUltimaOcorrenciaRejeitada);
						relatorioBean.setMotivoRejeicao(motivoRejeicao);
						relatorioBean.setObservacao(observacao);
						
						//adiciona o bean a cole��o
						relatorioBeans.add(relatorioBean);
					}
				// Caso o Tipo do relat�rio seja CONVERGENTE
				} else  {
					Integer validacaoAceite = calculaPercentualMetragemEValidaRetorno( metragem, metragemRetorno ) ;
					//Validar se oSPavimentoRetorno � divergente, pois esse relatorio � divergente! 
					if ( ( tipoPvtoRuaRetorno != null && !tipoPvtoRuaRetorno.equals("") && tipoPvtoRuaRetorno.equals(tipoPvtoRua) ) ||
							( validacaoAceite != null && validacaoAceite.toString().equals("1"))) {
						
						//Inicializa o construtor constitu�do dos campos
						// necess�rios para a impress�o do relatorio
						relatorioBean = new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
								numeroOS,
								matricula,
								endereco,
								dataEncerramento,
								tipoPvtoRua,
								metragem,
								dataConclusao,
								tipoPvtoRuaRetorno,
								metragemRetorno,
								oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_Rua(),
								oSPavimentoRetornoHelper.getCollOSTipoPavimentoHelper_RuaRet()
						);
					
						relatorioBean.setDataRejeicao(dataRejeicao);
						relatorioBean.setSituacaoRetorno(situacaoRetorno);
						relatorioBean.setIndicadorPrimeiraOcorrenciaConcluida(indicadorPrimeiraOcorrenciaConcluida);
						relatorioBean.setIndicadorPrimeiraOcorrenciaPendente(indicadorPrimeiraOcorrenciaPendente);
						relatorioBean.setIndicadorPrimeiraOcorrenciaRejeitada(indicadorPrimeiraOcorrenciaRejeitada);
						relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(indicadorUltimaOcorrenciaRejeitada);
						relatorioBean.setMotivoRejeicao(motivoRejeicao);
						relatorioBean.setObservacao(observacao);
						
						//adiciona o bean a cole��o
						relatorioBeans.add(relatorioBean);
					}
				}
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		UnidadeOrganizacional unidadeOrganizacional = null;
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
	    filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,oSPavimentoHelperParametros.getIdUnidadeResponsavel()));		    
	    filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");	
	    
		Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		Iterator it = colecaoUnidadeOrganizacional.iterator();
		while(it.hasNext()){
			unidadeOrganizacional = (UnidadeOrganizacional)it.next();
		}		
		if(unidadeOrganizacional != null){
			parametros.put("unidadeResponsavel", unidadeOrganizacional.getDescricao());				

		}
		
		if(unidadeOrganizacional != null && unidadeOrganizacional.getUnidadeSuperior() != null){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacionalSup = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacionalSup.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,unidadeOrganizacional.getUnidadeSuperior().getId()));		 
			filtroUnidadeOrganizacionalSup.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			UnidadeOrganizacional unidadeOrganizacionalSup = null;
			Collection colecaoUnidadeOrganizacionalSup = fachada.pesquisar(filtroUnidadeOrganizacionalSup, UnidadeOrganizacional.class.getName());
			Iterator itt = colecaoUnidadeOrganizacionalSup.iterator();
			while(itt.hasNext()){
				unidadeOrganizacionalSup = (UnidadeOrganizacional)itt.next();
			}
			
			if(unidadeOrganizacionalSup!= null && unidadeOrganizacionalSup.getGerenciaRegional()!= null){
				parametros.put("gerenciaRegional", unidadeOrganizacionalSup.getGerenciaRegional().getId() + "-" + unidadeOrganizacionalSup.getGerenciaRegional().getNomeAbreviado());	
				
			}
		}
		
		String situacao = "";
		if(oSPavimentoHelperParametros.getSituacaoRetorno() == 1){
			situacao = "PENDENTE";
		}else if(oSPavimentoHelperParametros.getSituacaoRetorno() == 2){
			situacao = "CONCLU�DA";
		
		}else if(oSPavimentoHelperParametros.getSituacaoRetorno() == 4){
			situacao = "REJEITADAS";
		// 3
		}else{
			situacao = "TODAS";
		}		
		parametros.put("situacao", situacao);
		
		parametros.put("escolhaRelatorio", escolhaRelatorio);
		
		String periodoEncerramentoOs = "";
		if(oSPavimentoHelperParametros.getPeriodoEncerramentoOsInicial()!= null && oSPavimentoHelperParametros.getPeriodoEncerramentoOsFinal()!= null){
			periodoEncerramentoOs = oSPavimentoHelperParametros.getPeriodoEncerramentoOsInicial() + "-" +
			oSPavimentoHelperParametros.getPeriodoEncerramentoOsFinal();
			parametros.put("periodoEncerramentoOs", periodoEncerramentoOs) ;
		}
		
		String periodoRetornoServico = "";
		if(oSPavimentoHelperParametros.getPeriodoRetornoServicoInicial()!=null && oSPavimentoHelperParametros.getPeriodoRetornoServicoFinal()!= null ){
			periodoRetornoServico = oSPavimentoHelperParametros.getPeriodoRetornoServicoInicial() + "-" +
			oSPavimentoHelperParametros.getPeriodoRetornoServicoFinal();
			parametros.put("periodoRetornoServico", periodoRetornoServico);
		}
		
		String periodoRejeicaoServico = "";
		if(oSPavimentoHelperParametros.getPeriodoRejeicaoInicial()!=null && oSPavimentoHelperParametros.getPeriodoRejeicaoFinal()!= null ){
			periodoRejeicaoServico = oSPavimentoHelperParametros.getPeriodoRejeicaoInicial() + "-" +
			oSPavimentoHelperParametros.getPeriodoRejeicaoFinal();
			parametros.put("periodoRejeicaoServico", periodoRejeicaoServico);
		}
		
		// teste
		ArrayList colecaoBeans = (ArrayList) relatorioBeans;
		
		//colecaoBeans.ge
		Iterator itBean = colecaoBeans.iterator();
		
		int indice = 0;
		int ultimoIndice = colecaoBeans.size();
		
		while(itBean.hasNext()){
			relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)itBean.next();
			
			if(relatorioBean.getIndicadorUltimaOcorrenciaRejeitada() == true){
				
				relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(false);
				
				if(indice > 0){
					relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)colecaoBeans.get(indice-1);
					
					relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(true);
				}
				
				
				break;
			}
			
			indice = indice + 1;
		}
		
		if(indice > 0 && indice == ultimoIndice){
			relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)colecaoBeans.get(indice-1);
			
			relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(true);
		}
		
		// classifica a lista por situa��o de retorno
		Collections.sort((List) colecaoBeans,
				new Comparator() {
					public int compare(Object a, Object b) {
						String codigo1 = ((RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean) a)
								.getSituacaoRetorno();
						String codigo2 = ((RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean) b)
							.getSituacaoRetorno();
						if (codigo1 == null || codigo1.equals("")) {
							return -1;
						} else {
							return codigo1.compareTo(codigo2);
						}
					}
				});
		
		

		// classifica a lista por situa��o de retorno
		Collections.sort((List) colecaoBeans,
				new Comparator() {
					public int compare(Object a, Object b) {
						String codigo1 = ((RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean) a)
								.getSituacaoRetorno();
						String codigo2 = ((RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean) b)
							.getSituacaoRetorno();
						if (codigo1 == null || codigo1.equals("")) {
							return -1;
						} else {
							return codigo1.compareTo(codigo2);
						}
					}
				});
		
		while(itBean.hasNext()){
			relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)itBean.next();
			
			if(relatorioBean.getIndicadorUltimaOcorrenciaRejeitada() == true){
				
				relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(false);
				
				if(indice > 0){
					relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)colecaoBeans.get(indice-1);
					
					relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(true);
				}
				
				
				break;
			}
			
			indice = indice + 1;
		}
		
		if(indice > 0 && indice == ultimoIndice){
			relatorioBean = (RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean)colecaoBeans.get(indice-1);
			
			relatorioBean.setIndicadorUltimaOcorrenciaRejeitada(true);
		}

		// cria uma inst�ncia do dataSource do relat�rio
		if ( relatorioBeans.size() > 0) {
			RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO_NOVO, parametros, ds,
					tipoFormatoRelatorio);
		} else {

			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			
		}
		

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		
		OSPavimentoHelper osPavimentoHelper = (OSPavimentoHelper) getParametro("parametros");
		retorno = Fachada.getInstancia().pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoServicosAcompanhamentoRepavimentacao", this);
	}

	
	
	/**
	 *  [SB0003] - Imprimir rela��o das ordens
	 *  1.331
	 * Metodo responsavel por verificar se a metragem informada no retorno esta compreendida
	 * entre o percentual de varia��o permitido.
	 * 
	 * @author Arthur Carvalho
	 * @date 26/07/2010
	 * @param metragem
	 * @param metragemRetono
	 * @return
	 */
	public Integer calculaPercentualMetragemEValidaRetorno(BigDecimal metragem, BigDecimal metragemRetono ) {
		Integer indicadorAceiteComCalculoPercentualConvergencia = null;
		BigDecimal percentualConvergenciaRepavimentacao = new BigDecimal(0);
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		percentualConvergenciaRepavimentacao = sistemaParametro.getPercentualConvergenciaRepavimentacao();
		
		//1.3.3.    Total Ordens Aceitas 
		if ( percentualConvergenciaRepavimentacao != null ) { 
			 
			if ( metragem.add(metragem.multiply(percentualConvergenciaRepavimentacao).divide(
							new BigDecimal(100))).compareTo(metragemRetono) >= 0 &&
								metragem.subtract(metragem.multiply(
									percentualConvergenciaRepavimentacao).divide(
											new BigDecimal(100))).compareTo(metragemRetono) <= 0 ) {
				
				indicadorAceiteComCalculoPercentualConvergencia = 1;
			} else {
				indicadorAceiteComCalculoPercentualConvergencia = 2;
			}
		} 

		return indicadorAceiteComCalculoPercentualConvergencia;
	}
}
