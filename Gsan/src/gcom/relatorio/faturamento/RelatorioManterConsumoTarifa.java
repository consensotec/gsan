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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de manter consumo tarifa
 * 
 * @author Rafael Corr�a
 * @created 11/05/2007
 */
public class RelatorioManterConsumoTarifa extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterConsumoTarifa(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSUMO_TARIFA_MANTER);
	}

	@Deprecated
	public RelatorioManterConsumoTarifa() {
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

		String descricao = (String) getParametro("descricao");
		Date dataVigenciaInicial = (Date) getParametro("dataVigenciaInicial");
		Date dataVigenciaFinal = (Date) getParametro("dataVigenciaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterConsumoTarifaBean relatorioBean = null;

		Collection colecaoConsumoTarifaRelatorioHelper = fachada
				.pesquisarConsumoTarifaRelatorio(descricao,
						dataVigenciaInicial, dataVigenciaFinal);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoConsumoTarifaRelatorioHelper != null && !colecaoConsumoTarifaRelatorioHelper.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoConsumoTarifaRelatorioHelperIterator = colecaoConsumoTarifaRelatorioHelper.iterator();

			// la�o para criar a cole��o de par�metros da analise
			
			String categoriaAnterior = "";
			String idConsumoTarifaAnterior = "";
			Date dataVigenciaInicialAnterior = null;
			
			ConsumoTarifaRelatorioHelper consumoTarifaRelatorioHelper = null;
			
			while (colecaoConsumoTarifaRelatorioHelperIterator.hasNext()) {

				if (consumoTarifaRelatorioHelper != null) {
					idConsumoTarifaAnterior = consumoTarifaRelatorioHelper.getIdConsumoTarifa().toString(); 
				}
				
				consumoTarifaRelatorioHelper = (ConsumoTarifaRelatorioHelper) colecaoConsumoTarifaRelatorioHelperIterator
						.next();
				
				// Validade
				String validade = "";
				
				if (consumoTarifaRelatorioHelper.getDataValidadeInicial() != null
						&& ((!consumoTarifaRelatorioHelper
								.getDataValidadeInicial().equals(
										dataVigenciaInicialAnterior)) || (!idConsumoTarifaAnterior
								.equals(consumoTarifaRelatorioHelper
										.getIdConsumoTarifa().toString())))) {
					validade = validade + Util.formatarData(consumoTarifaRelatorioHelper.getDataValidadeInicial());
					dataVigenciaInicialAnterior = consumoTarifaRelatorioHelper.getDataValidadeInicial();
					
					Date dataValidadeFinal = fachada.pesquisarDataFinalValidadeConsumoTarifa(consumoTarifaRelatorioHelper.getIdConsumoTarifa(), consumoTarifaRelatorioHelper.getDataValidadeInicial());
					
					if (dataValidadeFinal != null) {
						validade = validade + " A " + Util.formatarData(dataValidadeFinal);
					}

				}
				
				// Categoria
				String categoria = "";
				
				if (consumoTarifaRelatorioHelper.getCategoria() != null ) {
					categoria = consumoTarifaRelatorioHelper.getCategoria();
					categoriaAnterior = consumoTarifaRelatorioHelper.getCategoria();
				}
				
				// Faixa de Consumo
				String faixa = "";
				
				if (consumoTarifaRelatorioHelper.getFaixaInicial() != null) {
					faixa = faixa + consumoTarifaRelatorioHelper.getFaixaInicial();
				}
				
				if (consumoTarifaRelatorioHelper.getFaixaFinal() != null) {
					faixa = faixa + " A " + consumoTarifaRelatorioHelper.getFaixaFinal();
				}
					
				// Custo	
				String custo = "";
				
				if (consumoTarifaRelatorioHelper.getCusto() != null) {
					custo = Util.formatarMoedaReal(consumoTarifaRelatorioHelper.getCusto());
				}
					
				// Tarifa M�nima	
				String tarifaMinima = "";
				
				if (consumoTarifaRelatorioHelper.getTarifaMinima() != null && !categoria.equals("")) {
					tarifaMinima = Util.formatarMoedaReal(consumoTarifaRelatorioHelper.getTarifaMinima());
				}
				
				// Consumo M�nimo
				// Inclui mais um bean com o valor do consumo minimo, a data de validade e a categoria
				if (consumoTarifaRelatorioHelper.getConsumoMinimo() != null && !categoria.equals("")) {
					String faixaMinima = "ATE " + consumoTarifaRelatorioHelper.getConsumoMinimo(); 
					
					relatorioBean = new RelatorioManterConsumoTarifaBean(

							// Tarifa de Consumo
							consumoTarifaRelatorioHelper.getDescricaoConsumoTarifa(),
							
							// Validade
							validade,
							
							// Categoria
							categoria,
							
							// Faixa de Consumo
							faixaMinima,
							
							// Custo do M�
							"",
							
							// Tarifa M�nima
							tarifaMinima);
					
					// adiciona o bean a cole��o
					relatorioBeans.add(relatorioBean);
					
					// Seta os valores para vazio para n�o serem impressos novamente
					validade = "";
					categoria = "";
					tarifaMinima = "";
				}

				relatorioBean = new RelatorioManterConsumoTarifaBean(

						// Tarifa de Consumo
						consumoTarifaRelatorioHelper.getDescricaoConsumoTarifa(),
						
						// Validade
						validade,
						
						// Categoria
						categoria,
						
						// Faixa de Consumo
						faixa,
						
						// Custo do M�
						custo,
						
						// Tarifa M�nima
						tarifaMinima);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// Descri��o 
		if (descricao != null) {
			parametros.put("descricao", descricao);
		} else {
			parametros.put("descricao", "");
		}
		
		// Data Vig�ncia
		if (dataVigenciaInicial != null) {
			parametros.put("dataVigencia", Util.formatarData(dataVigenciaInicial) + " a " + Util.formatarData(dataVigenciaFinal));
		} else {
			parametros.put("dataVigencia", "");
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSUMO_TARIFA_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_BAIRRO,
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

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroBairro) getParametro("filtroBairro"),
//				Bairro.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterBairro", this);

	}

}