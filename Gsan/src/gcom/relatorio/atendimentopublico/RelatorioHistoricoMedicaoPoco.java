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
package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Arthur Carvalho
 * @date 09/10/2009
 * 
 */
public class RelatorioHistoricoMedicaoPoco extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioHistoricoMedicaoPoco(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_HISTORICO_MEDICAO_POCO);
	}
	
	@Deprecated
	public RelatorioHistoricoMedicaoPoco() {
		super(null, "");
	}

	private Collection<RelatorioHistoricoMedicaoPocoBean> inicializarBeanRelatorio(Collection dadosRelatorio2) {

		Collection<RelatorioHistoricoMedicaoPocoBean> retorno = new ArrayList();
		
		if (dadosRelatorio2 != null && !dadosRelatorio2.isEmpty()){
			//Hist�rico de Medi��o do Po�o
			Iterator iterator = dadosRelatorio2.iterator();
			while (iterator.hasNext()) {
				
				RelatorioHistoricoMedicaoPocoHelper helper = (RelatorioHistoricoMedicaoPocoHelper)iterator.next();
				
				
				String mesAnoMedicao = "" + helper.getMedicaoHistorico().getMesAno();
				
				String dataLeituraInformada =  "";
				if(helper.getMedicaoHistorico().getDataLeituraAtualInformada() != null){
					dataLeituraInformada = Util.formatarData(
							helper.getMedicaoHistorico().getDataLeituraAtualInformada());
				}
				
				String leituraInformada = "";
				if (helper.getMedicaoHistorico().getLeituraAtualInformada() != null){
					leituraInformada = ""+ helper.getMedicaoHistorico().getLeituraAtualInformada();
				}
				
				String dataLeituraFaturada = "" ;
				if (helper.getMedicaoHistorico().getDataLeituraAtualFaturamento() != null){
					dataLeituraFaturada = Util.formatarData(
							helper.getMedicaoHistorico().getDataLeituraAtualFaturamento());
				}
				
				String leituraFaturada = "";
				if (helper.getMedicaoHistorico().getLeituraAtualFaturamento() != 0){
					leituraFaturada = "" + helper.getMedicaoHistorico().getLeituraAtualFaturamento();
				}
				
				String consumo = "";
				if ( helper.getConsumoHistorico() != null && helper.getConsumoHistorico().getConsumoMedio() != null ) {
					consumo = helper.getConsumoHistorico().getConsumoMedio().toString();
				}
				
				String media = "";
				if(helper.getMedicaoHistorico().getConsumoMedioHidrometro() != null){
					media= "" + helper.getMedicaoHistorico().getConsumoMedioHidrometro();
				}
				
				String anormalidadeConsumo = "";
				if ( helper.getConsumoAnormalidade() != null && 
						helper.getConsumoAnormalidade().getDescricaoAbreviada() != null ) {
					anormalidadeConsumo = helper.getConsumoAnormalidade().getDescricaoAbreviada();
					
				}
				
				String anormalidadeLeitura = "";
				if(helper.getMedicaoHistorico().getLeituraAnormalidadeFaturamento() != null 
						&&helper.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId() != null){
					anormalidadeLeitura = "" + helper.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId();
				}
				
				String sitLeituraAtual = "";
				
				if(helper.getMedicaoHistorico().getLeituraSituacaoAtual() != null 
						&& helper.getMedicaoHistorico().getLeituraSituacaoAtual().getDescricao() != null){
					sitLeituraAtual = "" + helper.getMedicaoHistorico().getLeituraSituacaoAtual().getDescricao();
				}
				
				RelatorioHistoricoMedicaoPocoBean bean = new RelatorioHistoricoMedicaoPocoBean(mesAnoMedicao, dataLeituraInformada,
						leituraInformada, dataLeituraFaturada, leituraFaturada, consumo, media, anormalidadeConsumo, anormalidadeLeitura, sitLeituraAtual);
				 
				retorno.add(bean);

							
			}	
		}
		
		return retorno;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String matricula = (String) getParametro("matricula");
		
		Collection<MedicaoHistorico> medicoesHistoricoPoco = new ArrayList<MedicaoHistorico>();
		
		//imoveisMicromedicaoEsgoto  = (Collection) getParametro("imoveisMicromedicaoEsgoto");
		if ( matricula != null && !matricula.equals("") ) {
			medicoesHistoricoPoco   = Fachada.getInstancia().
				carregarDadosMedicaoConsumoHistoricoLigacaoEsgoto(new Integer(matricula));
		}
		
		if ( medicoesHistoricoPoco == null || medicoesHistoricoPoco.isEmpty() ) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String matriculaFormatada = (matricula).substring(0, (matricula).length() - 1) + "." + (matricula).substring((matricula).length() - 1);
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("matricula", matriculaFormatada);
		parametros.put("inscricao", (String) getParametro("inscricao"));
		parametros.put("sitLigacaoAgua", (String) getParametro("sitLigacaoAgua"));
		parametros.put("sitLigacaoEsgoto", (String) getParametro("sitLigacaoEsgoto"));
		parametros.put("endereco", (String) getParametro("endereco"));
		parametros.put("clienteUsuario", (String) getParametro("clienteUsuario"));
		parametros.put("numeroHidrometro",(String) getParametro("numeroHidrometro"));

		Collection<RelatorioHistoricoMedicaoPocoBean> colecaoBean = null;
		
		if ( medicoesHistoricoPoco != null && !medicoesHistoricoPoco.isEmpty() ) {
			colecaoBean = this
				.inicializarBeanRelatorio(medicoesHistoricoPoco);
		} else {
			colecaoBean = new ArrayList<RelatorioHistoricoMedicaoPocoBean>();
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_HISTORICO_MEDICAO_POCO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_HISTORICO_MEDICAO_POCO,
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
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioHistoricoMedicaoPoco", this);
	}
}