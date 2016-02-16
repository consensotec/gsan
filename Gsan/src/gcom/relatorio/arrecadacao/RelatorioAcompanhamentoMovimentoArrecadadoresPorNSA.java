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
package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.bean.MovimentoArrecadadoresPorNSAHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Descri��o da classe 
 *
 * @author Pedro Alexandre
 * @date 04/07/2007
 */
public class RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA extends TarefaRelatorio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES_POR_NSA);
	}
	
	@Deprecated
	public RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA() {
		super(null, "");
	}

	private Collection<RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean> inicializarBeanRelatorio(
			Collection<MovimentoArrecadadoresPorNSAHelper> dadosRelatorio) {

		Collection<RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean> retorno = new ArrayList();
		
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			MovimentoArrecadadoresPorNSAHelper helper = (MovimentoArrecadadoresPorNSAHelper)iterator.next();
			
			String banco = "";
			if (helper.getBanco() != null) {
				banco = helper.getBanco();
			}
			
			String formaArrecadacao = "";
			if (helper.getFormaArrecadacao() != null) {
				formaArrecadacao = helper.getFormaArrecadacao();
			}
			
			String nsa = "";
			if (helper.getNsa() != null) {
				nsa = helper.getNsa().toString();
			}
			
			String dataGeracao = "";
			if (helper.getDataGeracao() != null) {
				dataGeracao = Util.formatarData(helper.getDataGeracao());
			}
			
			Integer qtdeRegistros = 0;
			if (helper.getFormaArrecadacao() != null) {
				qtdeRegistros = helper.getQtdeRegistros();
			}
			
			BigDecimal valor = new BigDecimal("0.00");
			if (helper.getValor() != null && !helper.getValor().equals(0) ) {
				//BigDecimal valor = new BigDecimal("0.00");
                String valorString = "00" +  helper.getValor().toString();
				Integer tamanho = valorString.length();
				int inteiro = tamanho - 2;
				valor = new BigDecimal(valorString.substring(0, inteiro) +"."+ 
						valorString.substring(inteiro, inteiro + 2));
			}
			
			String tarifa = "";
			if (helper.getTarifa() != null) {
				tarifa = Util.formatarMoedaReal(helper.getTarifa());
			}
			
			BigDecimal valorAPagar = new BigDecimal("0.00");
			if (helper.getTarifa() != null) {
				valorAPagar = helper.getTarifa().multiply(new BigDecimal(helper.getQtdeRegistros()));
			}
			
			String mesAnoArrecadacao = "";
			if(helper.getDataGeracao() != null){
				String mes = ""+Util.getMes(helper.getDataGeracao());
				if(mes.length() == 1){
				  mesAnoArrecadacao = "0"+Util.getMes(helper.getDataGeracao())+"/"+Util.getAno(helper.getDataGeracao());
				}else{
				  mesAnoArrecadacao = Util.getMes(helper.getDataGeracao())+"/"+Util.getAno(helper.getDataGeracao());	
				}
			}
			
			RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean bean = new 
			RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean(banco, formaArrecadacao,
			nsa, dataGeracao, qtdeRegistros, valor, tarifa, valorAPagar, mesAnoArrecadacao);
			
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
		
		Collection dadosRelatorio = (Collection)getParametro("colecaoMovimentoArrecadadoresPorNSA");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		//String periodo = (String)getParametro("periodo");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			
		//parametros.put("periodo", periodo);
		
		parametros.put("tipoFormatoRelatorio", "R0619");

		Collection<RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES_POR_NSA,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES_POR_NSA,
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
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA", this);
	}
}