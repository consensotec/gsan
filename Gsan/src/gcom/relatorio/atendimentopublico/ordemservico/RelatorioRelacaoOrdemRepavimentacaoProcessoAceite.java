
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

import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoSomatorioPorTipoPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Relatorio de Relacao das Ordem de Repavimentacao em Processo de Aceite.
 * 
 * @author Hugo Leonardo
 * @created 20/05/2010 
 */

public class RelatorioRelacaoOrdemRepavimentacaoProcessoAceite extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorExclusaoMotivo object
	 */
	public RelatorioRelacaoOrdemRepavimentacaoProcessoAceite(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE);
	}

	@Deprecated
	public RelatorioRelacaoOrdemRepavimentacaoProcessoAceite() {
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
		OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoHelperParametros = (OrdemRepavimentacaoProcessoAceiteHelper) getParametro("osPavimentoAceiteHelper");
		
		Collection<OSPavimentoRetornoHelper> collOrdemServicoPavimentoHelper = null;

		String escolhaRelatorio = (String) getParametro("escolhaRelatorio");
		String periodoAceiteServicoInicial = (String) getParametro("periodoAceiteServicoInicial");
		String periodoAceiteServicoFinal = (String) getParametro("periodoAceiteServicoFinal");
		
		String retornoServicoInicial = (String) getParametro("retornoServicoInicial");
		String retornoServicoFinal = (String) getParametro("retornoServicoFinal");
		
		String situacaoAceiteDescricao = (String) getParametro("situacaoAceiteDescricao");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String descricaoUnidadeOrganizacional = (String) getParametro("descricaoUnidadeOrganizacional");
		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		
		collOrdemServicoPavimentoHelper = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(oSPavimentoHelperParametros, null);

		RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean relatorioBean = null;
		
		if (collOrdemServicoPavimentoHelper != null && !collOrdemServicoPavimentoHelper.isEmpty()) {
			// totaliza metragem por tipo de pavimento
			
			// Variaveis totaliza��o
			String descricaoPvtRua = "Total:";
			BigDecimal valorPvtRua = BigDecimal.ZERO;
			String descricaoPvtRuaRetorno = "Total:";
			BigDecimal valorPvtRuaRetorno = BigDecimal.ZERO;
			
			Collection<OSPavimentoSomatorioPorTipoPavimentoHelper> 
				colecaoSomatorioPorPavimento = new ArrayList<OSPavimentoSomatorioPorTipoPavimentoHelper>();
			
			Collection<OSPavimentoSomatorioPorTipoPavimentoHelper> 
			colecaoSomatorioPorPavimentoRua = new ArrayList<OSPavimentoSomatorioPorTipoPavimentoHelper>();
			
			Collection<OSPavimentoSomatorioPorTipoPavimentoHelper> 
			colecaoSomatorioPorPavimentoRetorno = new ArrayList<OSPavimentoSomatorioPorTipoPavimentoHelper>();
			/**
			 * Codigo comentado quebra o total por ocorrencia
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
					new OSPavimentoSomatorioPorTipoPavimentoHelper();
				
				// tipo pvto rua			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada()!= null) {
					
					helper.setDescricaoPvtRua(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada());
				}
				// Metr. (m�) indicada.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
					
					helper.setValorPvtRua(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua());
				}
				//tipo pvto rua retorno.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null) {
					helper.setDescricaoPvtRuaRetorno(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada());
				}
				// Metr. (m�) indicada retorno.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
					helper.setValorPvtRuaRetorno(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno());
				}
				if(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()!=null &&
						oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()
							.compareTo(ConstantesSistema.SIM)==0){
					if(colecaoSomatorioPorPavimento.contains(helper)){
						
						for (OSPavimentoSomatorioPorTipoPavimentoHelper 
								iteracao : colecaoSomatorioPorPavimento) {
							
							if(iteracao.equals(helper)){
								
								iteracao.setValorPvtRua(
										Util.somaBigDecimal(
												iteracao.getValorPvtRua(), helper.getValorPvtRua()));
								iteracao.setValorPvtRuaRetorno(
										Util.somaBigDecimal(
												iteracao.getValorPvtRuaRetorno(), helper.getValorPvtRuaRetorno()));
								
								valorPvtRua = Util.somaBigDecimal(valorPvtRua,helper.getValorPvtRua());
								valorPvtRuaRetorno = Util.somaBigDecimal(valorPvtRuaRetorno,helper.getValorPvtRuaRetorno());
								
							}
						}
						
					}else{					
						valorPvtRua = Util.somaBigDecimal(valorPvtRua,helper.getValorPvtRua());
						valorPvtRuaRetorno = Util.somaBigDecimal(valorPvtRuaRetorno,helper.getValorPvtRuaRetorno());
						colecaoSomatorioPorPavimento.add(helper);
					}
				}
				
			}
			//Cria objeto com totaliza��o
			OSPavimentoSomatorioPorTipoPavimentoHelper helperTotalizacao = 
				new OSPavimentoSomatorioPorTipoPavimentoHelper(descricaoPvtRua,
						valorPvtRua,descricaoPvtRuaRetorno,valorPvtRuaRetorno);
			
			colecaoSomatorioPorPavimento.add(helperTotalizacao);
			**/
			////////////////////////
			
			Collection colecaoPavimentoRuaExiste = new ArrayList();
			Collection colecaoPavimentoRetornoExiste = new ArrayList();
			
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
					new OSPavimentoSomatorioPorTipoPavimentoHelper();
				
				// tipo pvto rua			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada()!= null) {
					
					helper.setDescricaoPvtRua(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada());
				}
				// Metr. (m�) indicada.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
					
					helper.setValorPvtRua(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua());
				}
				
				if(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()!=null &&
						oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()
							.compareTo(ConstantesSistema.SIM)==0){
					if(colecaoPavimentoRuaExiste.contains(
							oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada())){
						
						for (OSPavimentoSomatorioPorTipoPavimentoHelper 
								iteracao : colecaoSomatorioPorPavimentoRua) {
							
							if(iteracao.getDescricaoPvtRua().equals(helper.getDescricaoPvtRua())){
								
								iteracao.setValorPvtRua(
										Util.somaBigDecimal(
												iteracao.getValorPvtRua(), helper.getValorPvtRua()));
								
								valorPvtRua = Util.somaBigDecimal(valorPvtRua,helper.getValorPvtRua());
								
							}
						}
						
					}else{					
						valorPvtRua = Util.somaBigDecimal(valorPvtRua,helper.getValorPvtRua());
						colecaoPavimentoRuaExiste.add(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada());
						colecaoSomatorioPorPavimentoRua.add(helper);
					}
					
				}
				
			}
			
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
					new OSPavimentoSomatorioPorTipoPavimentoHelper();
				
//				tipo pvto rua retorno.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null) {
					helper.setDescricaoPvtRuaRetorno(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada());
				}
				// Metr. (m�) indicada retorno.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
					helper.setValorPvtRuaRetorno(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno());
				}
				
				if(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()!=null &&
						oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()
							.compareTo(ConstantesSistema.SIM)==0){
					if(colecaoPavimentoRetornoExiste.contains(
							oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada())){
						
						for (OSPavimentoSomatorioPorTipoPavimentoHelper 
								iteracao : colecaoSomatorioPorPavimentoRetorno) {
							
							if(iteracao.getDescricaoPvtRuaRetorno().equals(helper.getDescricaoPvtRuaRetorno())){
								
								iteracao.setValorPvtRuaRetorno(
										Util.somaBigDecimal(
												iteracao.getValorPvtRuaRetorno(), helper.getValorPvtRuaRetorno()));
								
								valorPvtRuaRetorno = Util.somaBigDecimal(valorPvtRuaRetorno,helper.getValorPvtRuaRetorno());
								
							}
						}
						
					}else{					
						valorPvtRuaRetorno = Util.somaBigDecimal(valorPvtRuaRetorno,helper.getValorPvtRuaRetorno());
						colecaoPavimentoRetornoExiste.add(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada());
						colecaoSomatorioPorPavimentoRetorno.add(helper);
					}
					
				}
				
			}
			
			
			int size = 0;
			if (colecaoSomatorioPorPavimentoRua.size() > colecaoSomatorioPorPavimentoRetorno.size()){
				size = colecaoSomatorioPorPavimentoRua.size();
			} else {
				size = colecaoSomatorioPorPavimentoRetorno.size();
			}
			
			Iterator iColecaoSomatorioPorPavimentoRua = colecaoSomatorioPorPavimentoRua.iterator();
			Iterator iColecaoSomatorioPorPavimentoRetorno = colecaoSomatorioPorPavimentoRetorno.iterator();
			
			for (int i = 0; size > i; i++){
				
				OSPavimentoSomatorioPorTipoPavimentoHelper pavRua = null; 
				OSPavimentoSomatorioPorTipoPavimentoHelper pavRetorno = null;
				
				if (iColecaoSomatorioPorPavimentoRua.hasNext()){
					pavRua = (OSPavimentoSomatorioPorTipoPavimentoHelper) iColecaoSomatorioPorPavimentoRua.next();
				}
				
				if (iColecaoSomatorioPorPavimentoRetorno.hasNext()){
					pavRetorno = (OSPavimentoSomatorioPorTipoPavimentoHelper) iColecaoSomatorioPorPavimentoRetorno.next();
				}
				
				OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
					new OSPavimentoSomatorioPorTipoPavimentoHelper();
				if (pavRua.getDescricaoPvtRua() != null){
					helper.setDescricaoPvtRua(pavRua.getDescricaoPvtRua());
				}
				if (pavRua.getValorPvtRua() != null){
					helper.setValorPvtRua(pavRua.getValorPvtRua());
				}
				if (pavRetorno.getDescricaoPvtRuaRetorno() != null){
					helper.setDescricaoPvtRuaRetorno(pavRetorno.getDescricaoPvtRuaRetorno());
				}
				if (pavRetorno.getValorPvtRuaRetorno() != null){
					helper.setValorPvtRuaRetorno(pavRetorno.getValorPvtRuaRetorno());
				}
				
				
				colecaoSomatorioPorPavimento.add(helper);
				
				
			}
				
				
				
				
			
			
			OSPavimentoSomatorioPorTipoPavimentoHelper helperTotalizacao = 
				new OSPavimentoSomatorioPorTipoPavimentoHelper(descricaoPvtRua,
						valorPvtRua,descricaoPvtRuaRetorno,valorPvtRuaRetorno);
			
			colecaoSomatorioPorPavimento.add(helperTotalizacao);
			
			////////////////////////
			
			
			// coloca a cole��o de par�metros da analise no iterator
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio
				
				// numeroOS
				String numeroOS = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico() != null) {
					
					numeroOS = oSPavimentoRetornoHelper.getOrdemServico().getId().toString();
				}
				
				// matricula do im�vel
				String matricula = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico()!= null 
						&& oSPavimentoRetornoHelper.getOrdemServico().getImovel() != null) {
					
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
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
					
					metragem =oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua();
				}
				
				// data de Retorno
				String dataRetorno = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataExecucao()!= null) {
					
					dataRetorno = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataExecucao());
				}

				//  data de Aceite
				String dataAceite = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataAceite()!= null) {
					
					dataAceite = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataAceite());
				}
				

				//  data da Rejei��o
				String dataRejeicao = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataRejeicao()!= null) {
					
					dataRejeicao = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataRejeicao());
				}
				
				//tipo pvto rua retorno.
				String tipoPvtoRuaRetorno = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null) {
					tipoPvtoRuaRetorno = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada();
				}
				
				// Metr. (m�) indicada retorno.
				BigDecimal metragemRetorno = null;				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
					metragemRetorno = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno();
				}
				
				//Indicador de Aceite
				String indicadorAceite = "";
				if(oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite() != null){
					
					indicadorAceite = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite().toString();
				}
				
				String motivo = "";
				if ( oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null  &&
						oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDescricaoMotivoAceite() != null ) {
					motivo = "MOTIVO: " +oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDescricaoMotivoAceite();
					
					if ( motivo.length() > 200 ) {
						motivo = motivo.substring(0, 200);
					}
				}
				
			

				//Inicializa o construtor constitu�do dos campos
				// necess�rios para a impress�o do relatorio
				relatorioBean = new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
																					numeroOS,
																					matricula,
																					endereco,
																					dataEncerramento,
																					tipoPvtoRua,
																					metragem,
																					null,
																					tipoPvtoRuaRetorno,
																					metragemRetorno,
																					null,
																					null,
																					motivo );
				relatorioBean.setDataAceite(dataAceite);
				relatorioBean.setDataRetorno(dataRetorno);
				relatorioBean.setIndicadorAceite(indicadorAceite);
				relatorioBean.setArrayJRSubValoresPorTipoPavimento(
					new JRBeanCollectionDataSource(colecaoSomatorioPorPavimento));
				
				relatorioBean.setDataRejeicao(dataRejeicao);
				
				//O indicador pode mudar, caso o retorno n�o seja igual, mas a diferenca esteja 
				//entre o percentual permitido
				Integer indicadorAceiteComPercentualConvergencia = 
					calculaPercentualMetragemEValidaRetorno(metragem, metragemRetorno, indicadorAceite);
				
				if ( indicadorAceiteComPercentualConvergencia != null ) { 
					relatorioBean.setIndicadorAceiteComPercentualConvergencia(indicadorAceiteComPercentualConvergencia.toString());
				}
				//adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);

			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
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
		
		parametros.put("situacao", situacaoAceiteDescricao);
		
		parametros.put("escolhaRelatorio", escolhaRelatorio);
		
		parametros.put("unidadeOrganizacional", descricaoUnidadeOrganizacional);
		
		String periodoAceiteServico = "";
		if(periodoAceiteServicoInicial != null && !periodoAceiteServicoInicial.equals("") 
				&& periodoAceiteServicoFinal != null && !periodoAceiteServicoFinal.equals("")){
			
			periodoAceiteServico = periodoAceiteServicoInicial + " a " + periodoAceiteServicoFinal;
			parametros.put("periodoAceiteServico", periodoAceiteServico) ;
		}
		
		String periodoRetornoServico = "";
		if(retornoServicoInicial !=null && !retornoServicoInicial.equals("") 
				&& retornoServicoFinal != null && !retornoServicoFinal.equals("")){
			
			periodoRetornoServico = retornoServicoInicial + " a " + retornoServicoFinal;
			parametros.put("periodoRetornoServico", periodoRetornoServico);
		}
	
		// cria uma inst�ncia do dataSource do relat�rio
		if ( relatorioBeans.size() > 0) {
			RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_RELACAO_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE, parametros, ds,
					tipoFormatoRelatorio);
		} else {

			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			
		}

		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RELACAO_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		
		OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoHelperParametros = (OrdemRepavimentacaoProcessoAceiteHelper) getParametro("osPavimentoAceiteHelper");
		
		retorno = Fachada.getInstancia().pesquisarOrdemRepavimentacaoProcessoAceiteCount(oSPavimentoHelperParametros);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoOrdemRepavimentacaoProcessoAceite", this);
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
	public Integer calculaPercentualMetragemEValidaRetorno(BigDecimal metragem, BigDecimal metragemRetono, String indicadorAceite) {
		Integer indicadorAceiteComCalculoPercentualConvergencia = null;
		BigDecimal percentualConvergenciaRepavimentacao = new BigDecimal(0);
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		percentualConvergenciaRepavimentacao = sistemaParametro.getPercentualConvergenciaRepavimentacao();
		
		//1.3.3.    Total Ordens Aceitas 
		if ( indicadorAceite.equals("1") ) {
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
		//1.3.4.	Total Ordens N�o Aceitas 
		} else if ( indicadorAceite.equals("2") ) {
			
			if ( percentualConvergenciaRepavimentacao != null ) {
				if ( metragem.add(metragem.multiply(percentualConvergenciaRepavimentacao).divide(
						new BigDecimal(100))).compareTo(metragemRetono) <= 0 &&
							metragem.subtract(metragem.multiply(
								percentualConvergenciaRepavimentacao).divide(
										new BigDecimal(100))).compareTo(metragemRetono) >= 0 ) {
			
					indicadorAceiteComCalculoPercentualConvergencia = 1;
				} else {
					indicadorAceiteComCalculoPercentualConvergencia = 2;
				}
			}
		}
		return indicadorAceiteComCalculoPercentualConvergencia;
	}

}
