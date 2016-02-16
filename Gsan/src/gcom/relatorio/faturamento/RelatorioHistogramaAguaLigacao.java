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
import gcom.faturamento.bean.EmitirHistogramaAguaDetalheHelper;
import gcom.faturamento.bean.EmitirHistogramaAguaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
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
 * classe respons�vel por criar o relat�rio de histograma de liga��o de agua
 * 
 * @author Rafael Pinto / Rafael Correa
 * @created 07/06/2007
 */
public class RelatorioHistogramaAguaLigacao extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioHistogramaAguaLigacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO);
	}

	@Deprecated
	public RelatorioHistogramaAguaLigacao() {
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

		FiltrarEmitirHistogramaAguaHelper filtrarEmitirHistogramaAguaHelper = 
			(FiltrarEmitirHistogramaAguaHelper) getParametro("filtrarEmitirHistogramaAguaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioHistogramaAguaLigacaoBean relatorioHistogramaAguaLigacaoBean = null;

		Collection<EmitirHistogramaAguaHelper> colecao = 
			fachada.pesquisarEmitirHistogramaAgua(filtrarEmitirHistogramaAguaHelper);

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

				EmitirHistogramaAguaHelper emitirHistogramaAguaHelper = 
					(EmitirHistogramaAguaHelper) colecaoIterator.next();
				
				String opcaoTotalizacao = emitirHistogramaAguaHelper.getOpcaoTotalizacao();
				String descricao = emitirHistogramaAguaHelper.getDescricaoTitulo();
				
				Collection colecaoDetalhe = emitirHistogramaAguaHelper.getColecaoEmitirHistogramaAguaDetalhe();
				
				String numeroLigacoes = null;
				String percentualParcialLigacao = null;
				String percentualAcumuladoLigacao = null;
				String economias = null;
				String volumeEstimado = null;
				String volumeMedido = null;
				String volumeTotal = null;
				String percentualParcialConsumo = null;
				String percentualAcumuladoConsumo = null;
				String mediaConsumo = null;
				String valorFaturamento = null;
				String percentualParcialFaturamento = null;
				String percentualAcumuladoFaturamento = null;
				
				NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));
				
				if (colecaoDetalhe != null && !colecaoDetalhe.isEmpty()) {
				
					contadorDetalhes++;
					
					Iterator colecaoDetalheIterator = colecaoDetalhe.iterator();
					
					while (colecaoDetalheIterator.hasNext()) {
						
						EmitirHistogramaAguaDetalheHelper detalhe = 
							(EmitirHistogramaAguaDetalheHelper) colecaoDetalheIterator.next();
						
						String categoria = detalhe.getDescricaoCategoria();
						String subcategoria = detalhe.getDescricaoSubcategoria();
						numeroLigacoes = formato.format(detalhe.getQuantidadeLigacoes());
						percentualParcialLigacao = (""+detalhe.getPercentualParcialLigacao()).replace(".",",");
						
						percentualAcumuladoLigacao = "";
						if(!descricao.contains("TOTAL")){
							percentualAcumuladoLigacao = (""+detalhe.getPercentualAcumuladoLigacao()).replace(".",",");
						}
						
						economias = formato.format(detalhe.getQuantidadeEconomias());
						volumeEstimado = "";
						if(detalhe.getQuantidadeVolumeEstimado() != 0){
							volumeEstimado = formato.format(detalhe.getQuantidadeVolumeEstimado());
						}
						
						volumeMedido = "";
						if(detalhe.getQuantidadeVolumeMedido() != 0){
							volumeMedido = formato.format(detalhe.getQuantidadeVolumeMedido());
						}
						
						volumeTotal = formato.format(detalhe.getQuantidadeVolumeTotal());
						
						percentualParcialConsumo = (""+detalhe.getPercentualParcialConsumo()).replace(".",",");
						
						percentualAcumuladoConsumo = "";
						if(!descricao.contains("TOTAL")){
							percentualAcumuladoConsumo = (""+detalhe.getPercentualAcumuladoConsumo()).replace(".",",");
						}
						
						mediaConsumo = ""+detalhe.getMediaConsumo();
						
						valorFaturamento = Util.formatarMoedaReal(detalhe.getValorFaturado());
						
						percentualParcialFaturamento = 
							(""+detalhe.getPercentualParcialFaturamento()).replace(".",",");
						
						percentualAcumuladoFaturamento = "";
						if(!descricao.contains("TOTAL")){
							percentualAcumuladoFaturamento = 
								(""+detalhe.getPercentualAcumuladoFaturamento()).replace(".",",");
						}
						
						valoresDetalhes += detalhe.getQuantidadeEconomias() + detalhe.getQuantidadeLigacoes() 
								+ detalhe.getQuantidadeVolumeMedido();
						
						relatorioHistogramaAguaLigacaoBean = 
							new RelatorioHistogramaAguaLigacaoBean(
								// Op��o de Totaliza��o
								opcaoTotalizacao,
								// Descri��o
								descricao,
								// Categoria
								categoria,
								// Subcategoria
								subcategoria,
								// N�mero de Liga��es
								numeroLigacoes,
								// Percentual Parcial da Liga��o
								percentualParcialLigacao,
								// Percentual Acumulado da Liga��o
								percentualAcumuladoLigacao,						
								// Economias
								economias,						
								// Volume Medido
								volumeMedido,						
								// Volume Estimado
								volumeEstimado,						
								// Volume Total
								volumeTotal,						
								// Percentual Parcial do Consumo
								percentualParcialConsumo,						
								// Percentual Acumulado do Consumo
								percentualAcumuladoConsumo,						
								// M�dia de Consumo
								mediaConsumo,						
								// Valor do Faturamento
								valorFaturamento,					
								// Percentual Parcial do Faturamento
								percentualParcialFaturamento,						
								// Percentual Acumulado do Faturamento
								percentualAcumuladoFaturamento );
		
						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioHistogramaAguaLigacaoBean);
					
					}
				
				}
				
				numeroLigacoes = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeLigacoes());
				percentualParcialLigacao = (""+emitirHistogramaAguaHelper.getTotalPercentualParcialLigacao()).replace(".",",");
				
				percentualAcumuladoLigacao = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoLigacao = (""+emitirHistogramaAguaHelper.getTotalPercentualAcumuladoLigacao()).replace(".",",");
				}
				
				economias = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeEconomias());
				volumeEstimado = "";
				if(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeEstimado() != 0){
					volumeEstimado = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeEstimado());
				}
				
				volumeMedido = "";
				if(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeMedido() != 0){
					volumeMedido = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeMedido());
				}
				
				volumeTotal = formato.format(emitirHistogramaAguaHelper.getTotalQuantidadeVolumeTotal());
				
				percentualParcialConsumo = 
					(""+emitirHistogramaAguaHelper.getTotalPercentualParcialConsumo()).replace(".",",");
				
				percentualAcumuladoConsumo = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoConsumo = 
						(""+emitirHistogramaAguaHelper.getTotalPercentualAcumuladoConsumo()).replace(".",",");
				}
				
				mediaConsumo = ""+emitirHistogramaAguaHelper.getTotalMediaConsumo();
				
				valorFaturamento = Util.formatarMoedaReal(emitirHistogramaAguaHelper.getTotalValorFaturado());
				
				percentualParcialFaturamento = 
					(""+emitirHistogramaAguaHelper.getTotalPercentualParcialFaturamento()).replace(".",",");
				
				percentualAcumuladoFaturamento = "";
				if(!descricao.contains("TOTAL")){
					percentualAcumuladoFaturamento = 
						(""+emitirHistogramaAguaHelper.getTotalPercentualAcumuladoFaturamento()).replace(".",",");
				}				
				
				relatorioHistogramaAguaLigacaoBean = 
					new RelatorioHistogramaAguaLigacaoBean(
						// Op��o de Totaliza��o
						opcaoTotalizacao,
						// Descri��o
						descricao,
						// Categoria
						"TOTAL",
						// Subcategoria
						"TOTAL",
						// N�mero de Liga��es
						numeroLigacoes,
						// Percentual Parcial da Liga��o
						percentualParcialLigacao,
						// Percentual Acumulado da Liga��o
						percentualAcumuladoLigacao,						
						// Economias
						economias,						
						// Volume Medido
						volumeMedido,						
						// Volume Estimado
						volumeEstimado,						
						// Volume Total
						volumeTotal,						
						// Percentual Parcial do Consumo
						percentualParcialConsumo,						
						// Percentual Acumulado do Consumo
						percentualAcumuladoConsumo,						
						// M�dia de Consumo
						mediaConsumo,						
						// Valor do Faturamento
						valorFaturamento,					
						// Percentual Parcial do Faturamento
						percentualParcialFaturamento,						
						// Percentual Acumulado do Faturamento
						percentualAcumuladoFaturamento );

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioHistogramaAguaLigacaoBean);
				
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
		parametros.put("anoMes", Util.formatarAnoMesParaMesAno(filtrarEmitirHistogramaAguaHelper.getMesAnoFaturamento()));
		parametros.put("tipoFormatoRelatorio", "R0600");
		
		

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if ( filtrarEmitirHistogramaAguaHelper.getIndicadorTarifaCategoria() == 1 ){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO,
				parametros, ds, tipoFormatoRelatorio);
		} else {
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO_SUBCATEGORIA,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.HISTOGRAMA_AGUA_LIGACAO,
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

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioHistogramaAguaLigacao", this);

	}

}