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
package gcom.relatorio;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.bean.MovimentoArrecadadoresRelatorioHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioAcompanhamentoMovimentoArrecadadores extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAcompanhamentoMovimentoArrecadadores(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES);
	}

	@Deprecated
	public RelatorioAcompanhamentoMovimentoArrecadadores() {
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

		String mesAnoReferencia = (String) getParametro("mesAnoReferencia");
		Arrecadador arrecadador = (Arrecadador) getParametro("arrecadador");
		ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) getParametro("arrecadacaoForma");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAcompanhamentoMovimentoArrecadadoresBean relatorioBean = null;
		
		Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(mesAnoReferencia);
		
		String dataPagamentoInicial = "01/" + mesAnoReferencia; 
		
		Date dataPagamentoInicialFormatada = Util.converteStringParaDate(dataPagamentoInicial);
		
		Integer anoReferencia = Util.obterAno(anoMesReferencia);
		Integer mesReferencia = Util.obterMes(anoMesReferencia);
		
		String ultimoDia = Util.obterUltimoDiaMes(mesReferencia, anoReferencia);
		
		String dataPagamentoFinal = ultimoDia + "/" + mesAnoReferencia;
		
		Date dataPagamentoFinalFormatada = Util.converteStringParaDate(dataPagamentoFinal);

		Collection colecaoMovimentoArrecadadoresRelatorioHelper = fachada
				.pesquisarMovimentoArrecadadoresRelatorio(anoMesReferencia,
						arrecadador.getId(), arrecadacaoForma.getId(),
						dataPagamentoInicialFormatada,
						dataPagamentoFinalFormatada);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoMovimentoArrecadadoresRelatorioHelper != null && !colecaoMovimentoArrecadadoresRelatorioHelper.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoMovimentoArrecadadoresRelatorioHelperIterator = colecaoMovimentoArrecadadoresRelatorioHelper.iterator();
			
			BigDecimal valorAteDia = new BigDecimal("0.00");
			Integer qtdePagamentosAteDia = 0;
			Integer qtdeDocumentosAteDia = 0;
			
			MovimentoArrecadadoresRelatorioHelper movimentoArrecadadoresRelatorioHelperAnterior = null;

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoMovimentoArrecadadoresRelatorioHelperIterator.hasNext()) {

				MovimentoArrecadadoresRelatorioHelper movimentoArrecadadoresRelatorioHelper = (MovimentoArrecadadoresRelatorioHelper) colecaoMovimentoArrecadadoresRelatorioHelperIterator
						.next();
				
				if (movimentoArrecadadoresRelatorioHelperAnterior != null
						&& (!movimentoArrecadadoresRelatorioHelperAnterior
								.getArrecadador().equals(
										movimentoArrecadadoresRelatorioHelper
												.getArrecadador())
						|| !movimentoArrecadadoresRelatorioHelperAnterior
								.getDescricaoArrecadacaoForma()
								.equals(
										movimentoArrecadadoresRelatorioHelper
												.getDescricaoArrecadacaoForma()))) {
					
					// Zera os valores, pois a totaliza��o � feita em fun��o da forma de arrecada��o
					valorAteDia = new BigDecimal("0.00");
					qtdePagamentosAteDia = 0;
					qtdeDocumentosAteDia = 0;
					
				}

				String banco = movimentoArrecadadoresRelatorioHelper.getArrecadador();
				String formaArrecadacao = movimentoArrecadadoresRelatorioHelper.getDescricaoArrecadacaoForma();
				String valorPagamento = Util.formatarMoedaReal(movimentoArrecadadoresRelatorioHelper.getValorPagamento());
				
				Integer anoMesDataPagamento = Util.recuperaAnoMesDaData(movimentoArrecadadoresRelatorioHelper.getDataPagamento());
				Integer ano = Util.obterAno(anoMesDataPagamento);
				
				String dia = "";
				
				// Adiciona os valores atuais ao total
				qtdePagamentosAteDia = qtdePagamentosAteDia + movimentoArrecadadoresRelatorioHelper.getQtdePagamentos();
				qtdeDocumentosAteDia = qtdeDocumentosAteDia + movimentoArrecadadoresRelatorioHelper.getQtdeDocumentos();
				valorAteDia = valorAteDia.add(movimentoArrecadadoresRelatorioHelper.getValorPagamento());
				
				// Transforma em String os valores para ser setado no relat�rio
				String qtdePagamentosAteDiaFormatado = qtdePagamentosAteDia.toString();
				String qtdeDocumentosAteDiaFormatado = qtdeDocumentosAteDia.toString();
				String valorAteDiaFormatado = Util.formatarMoedaReal(valorAteDia);
				
				// Verifica se � o �ltimo movimento daquela forma de arrecada��o
				if (ano == 9999) {
					dia = "AA";
				} else {
					String dataPagamentoFormatada = Util.formatarData(movimentoArrecadadoresRelatorioHelper.getDataPagamento());
					dia = dataPagamentoFormatada.substring(0, 2);
				}

				relatorioBean = new RelatorioAcompanhamentoMovimentoArrecadadoresBean(banco, formaArrecadacao, dia,
						valorPagamento, movimentoArrecadadoresRelatorioHelper.getQtdePagamentos().toString(),
						movimentoArrecadadoresRelatorioHelper.getQtdeDocumentos().toString(), valorAteDiaFormatado, qtdePagamentosAteDiaFormatado, qtdeDocumentosAteDiaFormatado);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
				
				movimentoArrecadadoresRelatorioHelperAnterior = movimentoArrecadadoresRelatorioHelper;
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("mesAnoReferencia", mesAnoReferencia);

		if (arrecadador != null && arrecadador.getId() != null) {
			parametros.put("idArrecadador", arrecadador.getId().toString());
			parametros.put("nomeArrecadador", arrecadador.getCliente()
					.getNome());
		}
		
		if (arrecadacaoForma != null && arrecadacaoForma.getId() != null) {
			parametros.put("formaArrecadacao", arrecadacaoForma.getDescricao());
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES,
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

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoMovimentoArrecadadores", this);

	}

}