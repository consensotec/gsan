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
package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterTipoDebito extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterTipoDebito(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_TIPO_DEBITO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterTipoDebito() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroDebitoTipo filtroDebitoTipo = (FiltroDebitoTipo) getParametro("filtroDebitoTipo");
		
		BigDecimal valorLimiteInicialparametros = (BigDecimal) getParametro("valorLimiteDebitoInicial");
		BigDecimal valorLimiteFinalparametros = (BigDecimal) getParametro("valorLimiteDebitoFinal");
		String valorSugeridoParametros = (String) getParametro("valorSugerido");
		DebitoTipo debitoTipoParametros = (DebitoTipo) getParametro("debitoTipoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterTipoDebitoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
				DebitoTipo.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator debitoTipoIterator = colecaoDebitoTipo.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (debitoTipoIterator.hasNext()) {

				DebitoTipo debitoTipo = (DebitoTipo) debitoTipoIterator.next();

				String indicadorGeracaoAutomatica = "";
				
				if(debitoTipo.getIndicadorGeracaoAutomatica().equals(ConstantesSistema.SIM)){
					indicadorGeracaoAutomatica = "SIM";
				} else {
					indicadorGeracaoAutomatica = "N�O";
				}
			
				String indicadorGeracaoConta = "";
				
				if(debitoTipo.getIndicadorGeracaoConta().equals(ConstantesSistema.SIM)){
					indicadorGeracaoConta = "SIM";
				} else {
					indicadorGeracaoConta = "N�O";
				}
				

				String lancamentoItemContabil = "";
				
				if (debitoTipo.getLancamentoItemContabil() != null) {
					lancamentoItemContabil = debitoTipo.getLancamentoItemContabil().getDescricao();
				}
				
				String financiamentoTipo = "";
				
				if (debitoTipo.getFinanciamentoTipo() != null) {
					financiamentoTipo = debitoTipo.getFinanciamentoTipo().getDescricao();
				}
				
				String valorSugerido = "";
				
				if (debitoTipo.getValorSugerido() != null) {
					valorSugerido = debitoTipo.getValorSugerido().toString();
				}
				
				String valorLimite = "";
				
				if (debitoTipo.getValorLimite() != null) {
					valorLimite = debitoTipo.getValorLimite().toString();
				}
				
				relatorioBean = new RelatorioManterTipoDebitoBean(
						//ID
						debitoTipo.getId().toString(), 
						
						//Descri��o
						debitoTipo.getDescricao(), 
						
						//Descri��o Abreviada
						debitoTipo.getDescricaoAbreviada(),
						
						//Tipo de Lan�amento do Tipo Contabil
						lancamentoItemContabil,

						//Tipo de Financiamento
						financiamentoTipo,
						
						//Indicador Geracao do d�bito Automatica
						indicadorGeracaoAutomatica,
						
						//Indicador Geracao do D�bito em Conta
						indicadorGeracaoConta,
						
						//Indicador de Uso	
						debitoTipo.getIndicadorUso().toString(),
						
						//Valor limite
						valorLimite,
						
						//Valor Sugerido
						valorSugerido);
				
						
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id",
				debitoTipoParametros.getId() == null ? "" : ""
						+ debitoTipoParametros.getId());

		parametros.put("descricao", debitoTipoParametros.getDescricao());
		
		//Descricao Abreviada
		if (debitoTipoParametros.getDescricaoAbreviada() != null 
				&& !debitoTipoParametros.getDescricaoAbreviada().equals("")){
			parametros.put("descricaoAbreviada", debitoTipoParametros.getDescricaoAbreviada());
		} else {
			parametros.put("descricaoAbreviada", "");
		}
		
		//valor limite debito inicial
		if(valorLimiteInicialparametros != null){
		parametros.put("valorLimiteDebitoInicial", valorLimiteInicialparametros);
		}
		
		//valor limite debito inicial
		if(valorLimiteFinalparametros != null){
		parametros.put("valorLimiteDebitoFinal", valorLimiteFinalparametros);
		}
		String indicadorUso = "";

		if (debitoTipoParametros.getIndicadorUso() != null
				&& !debitoTipoParametros.getIndicadorUso().equals("")) {
			if (debitoTipoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (debitoTipoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		
		String indicadorGeracaoDebitoAutomatica = "";

		if (debitoTipoParametros.getIndicadorGeracaoAutomatica() != null
				&& !debitoTipoParametros.getIndicadorGeracaoAutomatica().equals("")) {
			if (debitoTipoParametros.getIndicadorGeracaoAutomatica().equals(new Short("1"))) {
				indicadorGeracaoDebitoAutomatica = "Sim";
			} else if (debitoTipoParametros.getIndicadorGeracaoAutomatica().equals(new Short("2"))) {
				indicadorGeracaoDebitoAutomatica = "N�o";
			}
		}
		parametros.put("indicadorGeracaoDebitoAutomatica", indicadorGeracaoDebitoAutomatica);
		
		String indicadorGeracaoDebitoConta = "";

		if (debitoTipoParametros.getIndicadorGeracaoConta() != null
				&& !debitoTipoParametros.getIndicadorGeracaoConta().equals("")) {
			if (debitoTipoParametros.getIndicadorGeracaoConta().equals(new Short("1"))) {
				indicadorGeracaoDebitoConta = "Sim";
			} else if (debitoTipoParametros.getIndicadorGeracaoConta().equals(new Short("2"))) {
				indicadorGeracaoDebitoConta = "N�o";
			}
		}
		parametros.put("indicadorGeracaoDebitoConta", indicadorGeracaoDebitoConta);
		
		//Financiamento Tipo
		if (debitoTipoParametros.getFinanciamentoTipo() != null) {
			parametros.put("financiamentoTipo", debitoTipoParametros.getFinanciamentoTipo().getDescricao());
		} else {
			parametros.put("financiamentoTipo", "");	
		}
		
		//Lancamento Item Contabil
		if (debitoTipoParametros.getLancamentoItemContabil() != null) {
			parametros.put("lancamentoItemContabil", debitoTipoParametros.getLancamentoItemContabil().getDescricao());
		} else {
			parametros.put("lancamentoItemContabil", "");	
		}
		//valor sugerido
		String valorSugerido = "";

		if (valorSugeridoParametros!= null
				&& !valorSugeridoParametros.equals("")) {
			if (valorSugeridoParametros.equals("1")) {
				valorSugerido = "Sim";
			} else if (valorSugeridoParametros.equals("2")) {
				valorSugerido = "N�o";
			}
		}

		parametros.put("valorSugerido", valorSugerido);
		
		
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_TIPO_DEBITO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
//		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterTipoDebito", this);
	}

}