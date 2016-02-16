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
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
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
 * classe respons�vel por criar o relat�rio de Hist�rico Medi��o e Consumo da Liga��o de �gua
 * 
 * @author Ana Maria
 * @date 14/02/2007
 * 
 */
public class RelatorioMedicaoConsumoLigacaoAgua extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioMedicaoConsumoLigacaoAgua(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MEDICAO_CONSUMO_LIGACAO_AGUA);
	}
	
	@Deprecated
	public RelatorioMedicaoConsumoLigacaoAgua() {
		super(null, "");
	}

	private Collection<RelatorioMedicaoConsumoLigacaoAguaBean> inicializarBeanRelatorio(
			Collection<MedicaoHistorico> dadosRelatorio, Collection<ImovelMicromedicao> dadosRelatorio2) {

		Collection<RelatorioMedicaoConsumoLigacaoAguaBean> retorno = new ArrayList();
		
		//Hist�rico Medi��o
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			MedicaoHistorico medicaoHistorico = (MedicaoHistorico)iterator.next();
			
			String mesAnoMedicao = ""+medicaoHistorico.getMesAno();
			
			String dataLeituraInformada =  "";
			if(medicaoHistorico.getDataLeituraAtualInformada() != null){
				dataLeituraInformada = Util.formatarData(medicaoHistorico.getDataLeituraAtualInformada());
			}
			String leituraInformada = "";
			if (medicaoHistorico.getLeituraAtualInformada() != null){
				leituraInformada = ""+ medicaoHistorico.getLeituraAtualInformada();
			}
			
			String dataLeituraFaturada = "" ;
			if (medicaoHistorico.getDataLeituraAtualFaturamento() != null){
				dataLeituraFaturada = Util.formatarData(medicaoHistorico.getDataLeituraAtualFaturamento());
			}
			
			String leituraFaturada = "";
			if (medicaoHistorico.getLeituraAtualFaturamento() != 0){
				leituraFaturada = "" + medicaoHistorico.getLeituraAtualFaturamento();
			}
			String anormalidadeInformada = "";
			if(medicaoHistorico.getLeituraAnormalidadeInformada().getDescricao() != null){
				anormalidadeInformada = "" + medicaoHistorico.getLeituraAnormalidadeInformada().getDescricao();
			}
			
			String anormalidadeFaturada = "";
			if(medicaoHistorico.getLeituraAnormalidadeFaturamento().getDescricao() != null){
				anormalidadeFaturada = "" + medicaoHistorico.getLeituraAnormalidadeFaturamento().getDescricao();
			}
			
			String leituraAtual = "";
			if(medicaoHistorico.getLeituraSituacaoAtual().getDescricao() != null){
				leituraAtual = "" + medicaoHistorico.getLeituraSituacaoAtual().getDescricao();
			}
			
			String mesAnoConsumo = null;
			String consumoMedido = null;
			String consumoFaturado = null;
			String anormalidadeConsumo = null;
			String diasConsumo = null;
			String tipoConsumo = null;
			
			RelatorioMedicaoConsumoLigacaoAguaBean bean = new RelatorioMedicaoConsumoLigacaoAguaBean(mesAnoMedicao, dataLeituraInformada,
					leituraInformada, dataLeituraFaturada, leituraFaturada, anormalidadeInformada, anormalidadeFaturada,
					leituraAtual, mesAnoConsumo, consumoMedido, consumoFaturado, anormalidadeConsumo, diasConsumo, tipoConsumo);
			 
			retorno.add(bean);
						
		}
		
		//Hist�rico Consumo
		Iterator iterator2 = dadosRelatorio2.iterator();
		while (iterator2.hasNext()) {
			
			ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao)iterator2.next();
			
			String mesAnoMedicao = null;
			String dataLeituraInformada = null;
			String leituraInformada = null;
			String dataLeituraFaturada = null;
			String leituraFaturada = null;
			String anormalidadeInformada = null;
			String anormalidadeFaturada = null;
			String leituraAtual = null;
			
			String  mesAnoConsumo = ""+imovelMicromedicao.getConsumoHistorico().getMesAno();
			
			String consumoMedido =  "";
			if(imovelMicromedicao.getMedicaoHistorico() != null){
				consumoMedido = ""+imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes();
			}
			
			String consumoFaturado = "";
			if (imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes() != null){
				consumoFaturado = ""+ imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes();				
			}
			
			String anormalidadeConsumo = "" ;
			if (imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade().getDescricao() != null){
				anormalidadeConsumo = "" + imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade().getDescricaoAbreviada();
			}
			
			String tipoConsumo = "";
			if(imovelMicromedicao.getConsumoHistorico().getConsumoTipo().getDescricao() != null){
				tipoConsumo = "" + imovelMicromedicao.getConsumoHistorico().getConsumoTipo().getDescricao();
			}
			
			String diasConsumo = "";
			if (imovelMicromedicao.getQtdDias() != null){
				diasConsumo = "" + imovelMicromedicao.getQtdDias();
			}
		
			RelatorioMedicaoConsumoLigacaoAguaBean bean = new RelatorioMedicaoConsumoLigacaoAguaBean(mesAnoMedicao, dataLeituraInformada,
					leituraInformada, dataLeituraFaturada, leituraFaturada, anormalidadeInformada, anormalidadeFaturada,
					leituraAtual, mesAnoConsumo, consumoMedido, consumoFaturado, anormalidadeConsumo, diasConsumo, tipoConsumo);
			 
			retorno.add(bean);
						
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
		
		Collection colecaoMedicaoHistorico = (Collection)getParametro("colecaoMedicaoHistorico");
		Collection colecaoimoveisMicromedicao = (Collection)getParametro("colecaoimoveisMicromedicao");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("matricula", (String) getParametro("matricula"));
		parametros.put("sitLigacaoAgua", (String) getParametro("sitLigacaoAgua"));
		parametros.put("sitLigacaoEsgoto", (String) getParametro("sitLigacaoEsgoto"));
		parametros.put("endereco", (String) getParametro("endereco"));
		parametros.put("clienteUsuario", (String) getParametro("clienteUsuario"));
		
		//Collection dadosRelatorio = fachada.pesquisarGuiaDevolucaoRelatorio(ids);

		Collection<RelatorioMedicaoConsumoLigacaoAguaBean> colecaoBean = this
				.inicializarBeanRelatorio(colecaoMedicaoHistorico, colecaoimoveisMicromedicao);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MEDICAO_CONSUMO_LIGACAO_AGUA, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MEDICAO_CONSUMO_LIGACAO_AGUA,
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

		//retorno = ((Collection) getParametro("idsGuiaDevolucao")).size();

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMedicaoConsumoLigacaoAgua", this);
	}
}