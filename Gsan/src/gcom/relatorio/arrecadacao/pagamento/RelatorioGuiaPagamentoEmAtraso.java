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
package gcom.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
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

/**
 * classe respons�vel por criar o relat�rio de certidao negativa
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioGuiaPagamentoEmAtraso extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioGuiaPagamentoEmAtraso(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA);
	}

	public RelatorioGuiaPagamentoEmAtraso() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		FiltroGuiaPagamento filtroGuiaPagamento = (FiltroGuiaPagamento) getParametro("filtroGuiaPagamento");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioGuiaPagamentoEmAtrasoBean relatorioGuiaPagamentoEmAtrasoBean = null;

		Collection colecao =  
			fachada.pesquisarDadosRelatorioGuiaPagamentoEmAtraso(filtroGuiaPagamento);
		
		Collection<RelatorioGuiaPagamentoEmAtrasoHelper> colecaoGuiaImovel = null;
		Collection<RelatorioGuiaPagamentoEmAtrasoHelper> colecaoGuiaCliente = null;
		Iterator iteratorColecao = colecao.iterator();
		while(iteratorColecao.hasNext()){
			colecaoGuiaImovel = (Collection<RelatorioGuiaPagamentoEmAtrasoHelper>)iteratorColecao.next();
			colecaoGuiaCliente = (Collection<RelatorioGuiaPagamentoEmAtrasoHelper>)iteratorColecao.next();
		}

		
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorTotalImovel = BigDecimal.ZERO;
		BigDecimal valorTotalCliente = BigDecimal.ZERO;
		
		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoGuiaImovel != null && !colecaoGuiaImovel.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecaoGuiaImovel.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioGuiaPagamentoEmAtrasoHelper helper = 
					(RelatorioGuiaPagamentoEmAtrasoHelper) colecaoIterator.next();
				
				relatorioGuiaPagamentoEmAtrasoBean = 
					new RelatorioGuiaPagamentoEmAtrasoBean(helper);
				
				valorTotalImovel = valorTotalImovel.add(Util.formatarMoedaRealparaBigDecimal(helper.getValor()));
				
				valorTotal = valorTotal.add(Util.formatarMoedaRealparaBigDecimal(helper.getValor()));

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioGuiaPagamentoEmAtrasoBean);
			}
			
		}
		
		//se a cole��o de par�metros da analise n�o for vazia
		if (colecaoGuiaCliente != null && !colecaoGuiaCliente.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecaoGuiaCliente.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioGuiaPagamentoEmAtrasoHelper helper = 
					(RelatorioGuiaPagamentoEmAtrasoHelper) colecaoIterator.next();
				
				relatorioGuiaPagamentoEmAtrasoBean = 
					new RelatorioGuiaPagamentoEmAtrasoBean(helper);
				
				valorTotalCliente = valorTotalCliente.add(Util.formatarMoedaRealparaBigDecimal(helper.getValor()));
				
				valorTotal = valorTotal.add(Util.formatarMoedaRealparaBigDecimal(helper.getValor()));

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioGuiaPagamentoEmAtrasoBean);
			}
			
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("valorTotal", Util.formatarMoedaReal(valorTotal));
		parametros.put("valorTotalImovel", Util.formatarMoedaReal(valorTotalImovel));
		parametros.put("valorTotalCliente", Util.formatarMoedaReal(valorTotalCliente));
		
		parametros.put("qtdImoveis", colecaoGuiaImovel.size() + "");
		parametros.put("qtdClientes", colecaoGuiaCliente.size() + "");
		
		String financiamentoTipo = (String) getParametro("financiamentoTipo");
		String vencimentoInicial = (String) getParametro("vencimentoInicial");
		String vencimentoFinal = (String) getParametro("vencimentoFinal");
		String referenciaInicial = (String) getParametro("referenciaInicial");
		String referenciaFinal = (String) getParametro("referenciaFinal");
		String gerenciaRegional = (String)getParametro("gerenciaRegional");
		String unidadeNegocio = (String)getParametro("unidadeNegocio");
		String localidadeInicial = (String)getParametro("localidadeInicial");
		String localidadeFinal = (String)getParametro("localidadeFinal");
		
		FinanciamentoTipo financiamento = null;
		
		if(financiamentoTipo != null && !financiamentoTipo.trim().equals("")){
			FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
			filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.ID, financiamentoTipo));
			Collection colecaoFinanciamento = fachada.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
			
			if(!colecaoFinanciamento.isEmpty()){
				financiamento = (FinanciamentoTipo)Util.retonarObjetoDeColecao(colecaoFinanciamento);
			}
		}
		if(financiamento != null){
			parametros.put("financiamentoTipo", financiamento.getDescricaoAbreviada());
		}else{
			parametros.put("financiamentoTipo", "");
		}
		parametros.put("vencimentoInicial", vencimentoInicial);
		parametros.put("vencimentoFinal", vencimentoFinal);
		parametros.put("referenciaInicial", referenciaInicial);
		parametros.put("referenciaFinal", referenciaFinal);
		parametros.put("gerenciaRegional", gerenciaRegional);
		parametros.put("unidadeNegocio", unidadeNegocio);
		parametros.put("localidadeInicial", localidadeInicial);
		parametros.put("localidadeFinal", localidadeFinal);
		
		parametros.put("tipoFormatoRelatorio", "R0877");
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_GUIA_PAGAMENTO_EM_ATRASO;

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_GUIA_PAGAMENTO_EM_ATRASO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGuiaPagamentoEmAtraso", this);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
}