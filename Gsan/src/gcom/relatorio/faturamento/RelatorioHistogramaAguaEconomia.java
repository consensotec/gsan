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

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirHistogramaAguaEconomiaDetalheHelper;
import gcom.faturamento.bean.EmitirHistogramaAguaEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaEconomiaHelper;
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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de histograma de liga��o de agua por economia
 * 
 * @author Rafael Pinto / Rafael Correa
 * @created 18/06/2007
 */
public class RelatorioHistogramaAguaEconomia extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioHistogramaAguaEconomia(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA);
	}

	@Deprecated
	public RelatorioHistogramaAguaEconomia() {
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

		FiltrarEmitirHistogramaAguaEconomiaHelper filtrarEmitirHistogramaAguaEconomiaHelper = 
			(FiltrarEmitirHistogramaAguaEconomiaHelper) getParametro("filtrarEmitirHistogramaAguaEconomiaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		int indicadorTarifaSimulacao = (Integer) getParametro("indicadorTarifaSimulacao");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioHistogramaAguaEconomiaBean relatorioHistogramaAguaEconomiaBean = null;

		boolean indicadorSimulacao = indicadorTarifaSimulacao==ConstantesSistema.SIM.intValue();
		
		if(indicadorSimulacao){
			fachada
				.calcularValorSimuladoHistogramaAguaEconomiaSemQuadra(filtrarEmitirHistogramaAguaEconomiaHelper.getMesAnoFaturamento());
		}
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecao =  
			fachada.pesquisarEmitirHistogramaAguaEconomia(filtrarEmitirHistogramaAguaEconomiaHelper);
		
		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();
			
			// contador de detalhes
			Integer contadorDetalhes = 0;
			
			// valores dos detalhes
			Integer valoresDetalhes = 0;

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {

				EmitirHistogramaAguaEconomiaHelper emitirHistogramaAguaEconomiaHelper = 
					(EmitirHistogramaAguaEconomiaHelper) colecaoIterator.next();
				
				String opcaoTotalizacao = emitirHistogramaAguaEconomiaHelper.getOpcaoTotalizacao();
				String descricao = emitirHistogramaAguaEconomiaHelper.getDescricaoCategoria();
				String descricaoSubcategoria = emitirHistogramaAguaEconomiaHelper.getDescricaoSubcategoria();				
				String descricaoTarifa = emitirHistogramaAguaEconomiaHelper.getDescricaoTarifa();
				
				Collection colecaoDetalhe = 
					emitirHistogramaAguaEconomiaHelper.getColecaoEmitirHistogramaAguaEconomiaDetalhe();
				
				String economiasMedido = null;
				String consumoMedioMedido = null;
				String consumoExcedenteMedido = null;
				String volumeConsumoMedido = null;
				String volumeFaturadoMedido = null;
				String receitaMedido = null;
				String ligacoesMedido = null;
				
				String economiasNaoMedido = null;
				String consumoMedioNaoMedido = null;
				String consumoExcedenteNaoMedido = null;
				String volumeConsumoNaoMedido = null;
				String volumeFaturadoNaoMedido = null;
				String receitaNaoMedido = null;
				String ligacoesNaoMedido = null;
				
				NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));
				
				if (colecaoDetalhe != null && !colecaoDetalhe.isEmpty()) {
					
					contadorDetalhes++;
				
					Iterator colecaoDetalheIterator = colecaoDetalhe.iterator();
					
					while (colecaoDetalheIterator.hasNext()) {
						
						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							(EmitirHistogramaAguaEconomiaDetalheHelper) colecaoDetalheIterator.next();
						
						String faixa = detalhe.getDescricaoFaixa();
						
						economiasMedido = formato.format(detalhe.getEconomiasMedido());
						
						if(detalhe.getConsumoMedioMedido() != null){
							consumoMedioMedido = Util.formataBigDecimal( detalhe.getConsumoMedioMedido(), 2, true ); //(""+detalhe.getConsumoMedioMedido()).replace(".",",");	
						} else {
							consumoMedioMedido = null;
						}	
						if(detalhe.getConsumoExcedenteMedido() != null){
							consumoExcedenteMedido = Util.formataBigDecimal( detalhe.getConsumoExcedenteMedido(), 2, true );
						} else {
							consumoExcedenteMedido = null;
						}
						
						volumeConsumoMedido = formato.format(detalhe.getVolumeConsumoFaixaMedido());
						volumeFaturadoMedido = formato.format(detalhe.getVolumeFaturadoFaixaMedido());
						receitaMedido = Util.formatarMoedaReal(detalhe.getReceitaMedido());
						
						
						ligacoesMedido = formato.format(detalhe.getLigacoesMedido());
						
						economiasNaoMedido = formato.format(detalhe.getEconomiasNaoMedido());

						if(detalhe.getConsumoMedioNaoMedido() != null){
							consumoMedioNaoMedido = Util.formataBigDecimal( detalhe.getConsumoMedioNaoMedido(), 2, true);	
						} else {
							consumoMedioNaoMedido = null;
						}
						if(detalhe.getConsumoExcedenteNaoMedido() != null){
							consumoExcedenteNaoMedido = Util.formataBigDecimal( detalhe.getConsumoExcedenteNaoMedido(), 2, true);	
						} else {
							consumoExcedenteNaoMedido = null;
						}
						
						volumeConsumoNaoMedido = formato.format(detalhe.getVolumeConsumoFaixaNaoMedido());
						volumeFaturadoNaoMedido = formato.format(detalhe.getVolumeFaturadoFaixaNaoMedido());
						receitaNaoMedido = Util.formatarMoedaReal(detalhe.getReceitaNaoMedido());
						
						ligacoesNaoMedido = formato.format(detalhe.getLigacoesNaoMedido());
						descricaoSubcategoria = detalhe.getDescricaoSubcategoria();
						
						valoresDetalhes += detalhe.getLigacoesMedido() + detalhe.getLigacoesNaoMedido() 
								+ detalhe.getEconomiasMedido() + detalhe.getEconomiasNaoMedido();
						
						relatorioHistogramaAguaEconomiaBean = 
							new RelatorioHistogramaAguaEconomiaBean(
								// Op��o de Totaliza��o
								opcaoTotalizacao,
								// Categoria
								descricao,
								// Subcategoria
								descricaoSubcategoria,
								// Descri��o da Faixa
								faixa,
								// N�mero de Economias
								economiasMedido,
								// Consumo Medio para Medido
								consumoMedioMedido,
								// Consumo Excedente para Medido
								consumoExcedenteMedido,						
								// Volume Consumo para Medido
								volumeConsumoMedido,						
								// Volume Faturado para Medido
								volumeFaturadoMedido,						
								// Receita para Medido
								receitaMedido,						
								// N�mero de Economias para N�o Medido
								economiasNaoMedido,						
								// Consumo Medio para N�o Medido
								consumoMedioNaoMedido,						
								// Consumo Excedente para N�o Medido
								consumoExcedenteNaoMedido,						
								// Volume Consumo para N�o Medido
								volumeConsumoNaoMedido,						
								// Volume Faturado para N�o Medido
								volumeFaturadoNaoMedido,					
								// Receita para N�o Medido 
								receitaNaoMedido,
								// Descricao Tarifa 
								descricaoTarifa,
								// Ligacoes Medido
								ligacoesMedido,
								// Ligacoes Nao Medido
								ligacoesNaoMedido);
		
						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioHistogramaAguaEconomiaBean);
					
					}
				
				}
				
				String faixa = emitirHistogramaAguaEconomiaHelper.getDescricaoFaixa();
				
				economiasMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalEconomiasMedido());
				volumeConsumoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalVolumeConsumoMedido());
				volumeFaturadoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalVolumeFaturadoMedido());
				receitaMedido = Util.formatarMoedaReal(emitirHistogramaAguaEconomiaHelper.getTotalReceitaMedido());
				ligacoesMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalLigacoesMedido());
				
				economiasNaoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalEconomiasNaoMedido());
				volumeConsumoNaoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalVolumeConsumoNaoMedido());
				volumeFaturadoNaoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalVolumeFaturadoNaoMedido());
				receitaNaoMedido = Util.formatarMoedaReal(emitirHistogramaAguaEconomiaHelper.getTotalReceitaNaoMedido());
				ligacoesNaoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalLigacoesNaoMedido());
				
				relatorioHistogramaAguaEconomiaBean = 
					new RelatorioHistogramaAguaEconomiaBean(
						// Op��o de Totaliza��o
						opcaoTotalizacao,
						// Categoria
						descricao,
						// Subcategoria
						descricaoSubcategoria,
						// Descri��o da Faixa
						faixa,
						// N�mero de Economias
						economiasMedido,
						// Consumo Medio para Medido
						null,
						// Consumo Excedente para Medido
						null,						
						// Volume Consumo para Medido
						volumeConsumoMedido,						
						// Volume Faturado para Medido
						volumeFaturadoMedido,						
						// Receita para Medido
						receitaMedido,						
						// N�mero de Economias para N�o Medido
						economiasNaoMedido,						
						// Consumo Medio para N�o Medido
						null,						
						// Consumo Excedente para N�o Medido
						null,						
						// Volume Consumo para N�o Medido
						volumeConsumoNaoMedido,						
						// Volume Faturado para N�o Medido
						volumeFaturadoNaoMedido,					
						// Receita para N�o Medido 
						receitaNaoMedido,
						// Descricao Tarifa 
						descricaoTarifa,
						// Ligacoes Medido								
						ligacoesMedido,
						// Ligacoes Nao Medido								
						ligacoesNaoMedido);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioHistogramaAguaEconomiaBean);

				
			}
			
			if(contadorDetalhes.equals(0) || valoresDetalhes.equals(0)){
				throw new ActionServletException("atencao.relatorio.vazio");
			}
			
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMes", Util.formatarAnoMesParaMesAno(filtrarEmitirHistogramaAguaEconomiaHelper.getMesAnoFaturamento()));
		
		parametros.put("tipoFormatoRelatorio", "R0604");
		
		if(indicadorSimulacao){
			parametros.put("tituloRelatorio", "AN�LISE DE CONSUMO DE �GUA POR ECONOMIA COM TARIFA DE SIMULA��O PARA REFER�NCIA " 
					+ Util.formatarAnoMesParaMesAno(filtrarEmitirHistogramaAguaEconomiaHelper.getMesAnoFaturamento()));
		}else{
			parametros.put("tituloRelatorio", "AN�LISE DE CONSUMO DE �GUA POR ECONOMIA PARA REFER�NCIA " 
					+ Util.formatarAnoMesParaMesAno(filtrarEmitirHistogramaAguaEconomiaHelper.getMesAnoFaturamento()));
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if ( filtrarEmitirHistogramaAguaEconomiaHelper.getIndicadorTarifaCategoria() == 1 ){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA,
				parametros, ds, tipoFormatoRelatorio);
		} else {
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA_SUBCATEGORIA,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.HISTOGRAMA_AGUA_POR_ECONOMIA,
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
		
		FiltrarEmitirHistogramaAguaEconomiaHelper filtrarEmitirHistogramaAguaEconomiaHelper = 
				(FiltrarEmitirHistogramaAguaEconomiaHelper) getParametro("filtrarEmitirHistogramaAguaEconomiaHelper");
		int indicadorTarifaSimulacao = (Integer) getParametro("indicadorTarifaSimulacao");

		if(indicadorTarifaSimulacao == ConstantesSistema.SIM.intValue()){
			//Para ir para batch
			retorno = 2;
		}else if(filtrarEmitirHistogramaAguaEconomiaHelper.getOpcaoTotalizacao() == 4
				|| filtrarEmitirHistogramaAguaEconomiaHelper.getOpcaoTotalizacao() == 5){
			// 4 ESTADO POR ELO POLO
			// 5 ESTADO POR LOCALIDADE
			//Para ir para batch
			retorno = 2;
		}else if(filtrarEmitirHistogramaAguaEconomiaHelper.getIndicadorTarifaCategoria() == 1 
				&& filtrarEmitirHistogramaAguaEconomiaHelper.getVolumoFixoAgua() != null 
				&& !filtrarEmitirHistogramaAguaEconomiaHelper.getVolumoFixoAgua().equals("")){
			//Categoria - Volume Fixo de �gua
			retorno = 2;
		}
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioHistogramaAguaEconomia", this);

	}

}