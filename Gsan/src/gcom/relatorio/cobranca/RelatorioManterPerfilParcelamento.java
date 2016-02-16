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
package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoRelatorioHelper;
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
 * @author not attributable
 * @version 1.0
 */

public class RelatorioManterPerfilParcelamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterPerfilParcelamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PERFIL_PARCELAMENTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterPerfilParcelamento() {
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

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroParcelamentoPerfil filtroParcelamentoPerfil = (FiltroParcelamentoPerfil) getParametro("filtroParcelamentoPerfil");
		ParcelamentoPerfil parcelamentoPerfilParametros = (ParcelamentoPerfil) getParametro("parcelamentoPerfilParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterPerfilParcelamentoBean relatorioBean = null;

		filtroParcelamentoPerfil.setConsultaSemLimites(true);

		Collection colecaoParcelamentoPerfil = fachada.pesquisar(
				filtroParcelamentoPerfil, ParcelamentoPerfil.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoParcelamentoPerfil != null
				&& !colecaoParcelamentoPerfil.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoParcelamentoPerfilIterator = colecaoParcelamentoPerfil
					.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoParcelamentoPerfilIterator.hasNext()) {

				ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil) colecaoParcelamentoPerfilIterator
						.next();

				// Pesquisa a cole��o de reparcelamentos consecutivos referente
				// ao perfil de parcelamento com os dados do reparcelamento e da
				// presta��o
				Collection colecaoReparcelamentosConsecutivos = fachada
						.pesquisarReparcelamentoConsecutivo(parcelamentoPerfil
								.getId());

				if (colecaoReparcelamentosConsecutivos != null
						&& !colecaoReparcelamentosConsecutivos.isEmpty()) {

					Iterator colecaoReparcelamentosConsecutivosIterator = colecaoReparcelamentosConsecutivos
							.iterator();

					while (colecaoReparcelamentosConsecutivosIterator.hasNext()) {

						ParcelamentoQuantidadeReparcelamentoRelatorioHelper parcelamentoQuantidadeReparcelamentoRelatorioHelper = (ParcelamentoQuantidadeReparcelamentoRelatorioHelper) colecaoReparcelamentosConsecutivosIterator
								.next();

						// Cria o Bean setando para nulo os campos que n�o s�o
						// referentes a pesquisa de reparcelamento para que o
						// agrupamento fique de maneira correta
						relatorioBean = new RelatorioManterPerfilParcelamentoBean(

								// Dados do Perfil de Parcelamento:

								// Id Parcelamento Perfil
								parcelamentoPerfil.getId().toString(),

								// RD
								parcelamentoPerfil.getResolucaoDiretoria()
										.getNumeroResolucaoDiretoria(),

								// Tipo da Situa��o do Im�vel
								parcelamentoPerfil.getImovelSituacaoTipo() == null ? ""
										: parcelamentoPerfil
												.getImovelSituacaoTipo()
												.getDescricaoImovelSituacaoTipo(),

								// Perfil do Im�vel
								parcelamentoPerfil.getImovelPerfil() == null ? ""
										: parcelamentoPerfil.getImovelPerfil()
												.getDescricao(),

								// Subcategoria
								parcelamentoPerfil.getSubcategoria() == null ? ""
										: parcelamentoPerfil.getSubcategoria()
												.getDescricao(),

								// Percentual Desconto Acr�scimo Impontualidade
								Util.formatarMoedaReal(parcelamentoPerfil
										.getPercentualDescontoAcrescimo()),

								// Percentual da Tarifa M�nima para C�lculo do
								// Valor M�nimo da Presta��o
								Util.formatarMoedaReal(parcelamentoPerfil
										.getPercentualDescontoAcrescimo()),

								// Dados de Reparcelamento Consecutivos e da
								// Presta��o

								// Id Reparcelamento
								parcelamentoQuantidadeReparcelamentoRelatorioHelper
										.getIdReparcelamento().toString(),

								// Quantidade M�xima de Reparcelamentos
								// Consecutivos
								parcelamentoQuantidadeReparcelamentoRelatorioHelper
										.getQuantidadeMaximaReparcelamento()
										.toString(),

								// Id Presta��o
								parcelamentoQuantidadeReparcelamentoRelatorioHelper
										.getIdPrestacao().toString(),

								// Quantidade M�xima de Presta��es do
								// Parcelamento
								parcelamentoQuantidadeReparcelamentoRelatorioHelper
										.getQuantidadeMaximaPrestacoes()
										.toString(),

								// Taxa de Juros
								Util
										.formatarMoedaReal(parcelamentoQuantidadeReparcelamentoRelatorioHelper
												.getTaxaJuros()),

								// Percentual M�nimo de Entrada
								Util
										.formatarMoedaReal(parcelamentoQuantidadeReparcelamentoRelatorioHelper
												.getPercentualMinimoEntrada()),

								// Dados do Desconto por Antiguidade do D�bito

								// Id Desconto Antiguidade
								null,

								// Quantidade M�nima Meses de D�bito p/ Desconto
								null,

								// Percentual de Desconto Sem Restabelecimento
								null,

								// Percentual de Desconto Com Restabelecimento
								null,

								// Percentual de Desconto Ativo
								null,

								// Dados do Desconto por Inatividade do D�bito

								// Id Desconto Inatividade
								null,

								// Quantidade M�xima Meses de Inatividade
								null,

								// Percentual de Desconto Sem Restabelecimento
								null,

								// Percentual de Desconto Com Restabelecimento
								null

						);

						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioBean);
						
					}

				}
				
				// Pesquisa a cole��o de parcelamento desconto antiguidade referente
				// ao perfil de parcelamento
				Collection colecaoDescontosAntiguidade = fachada
						.pesquisarParcelamentoDescontoAntiguidade(parcelamentoPerfil
								.getId());
				
				if (colecaoDescontosAntiguidade != null
						&& !colecaoDescontosAntiguidade.isEmpty()) {

					Iterator colecaoDescontosAntiguidadeIterator = colecaoDescontosAntiguidade
							.iterator();

					while (colecaoDescontosAntiguidadeIterator.hasNext()) {

						ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) colecaoDescontosAntiguidadeIterator
								.next();

						// Cria o Bean setando para nulo os campos que n�o s�o
						// referentes a pesquisa de desconto antiguidade para que o
						// agrupamento fique de maneira correta
						relatorioBean = new RelatorioManterPerfilParcelamentoBean(

								// Dados do Perfil de Parcelamento:

								// Id Parcelamento Perfil
								parcelamentoPerfil.getId().toString(),

								// RD
								parcelamentoPerfil.getResolucaoDiretoria()
										.getNumeroResolucaoDiretoria(),

								// Tipo da Situa��o do Im�vel
								parcelamentoPerfil.getImovelSituacaoTipo() == null ? ""
										: parcelamentoPerfil
												.getImovelSituacaoTipo()
												.getDescricaoImovelSituacaoTipo(),

								// Perfil do Im�vel
								parcelamentoPerfil.getImovelPerfil() == null ? ""
										: parcelamentoPerfil.getImovelPerfil()
												.getDescricao(),

								// Subcategoria
								parcelamentoPerfil.getSubcategoria() == null ? ""
										: parcelamentoPerfil.getSubcategoria()
												.getDescricao(),

								// Percentual Desconto Acr�scimo Impontualidade
								Util.formatarMoedaReal(parcelamentoPerfil
										.getPercentualDescontoAcrescimo()),

								// Percentual da Tarifa M�nima para C�lculo do
								// Valor M�nimo da Presta��o
								Util.formatarMoedaReal(parcelamentoPerfil
										.getPercentualDescontoAcrescimo()),

								// Dados de Reparcelamento Consecutivos e da
								// Presta��o

								// Id Reparcelamento
								null,

								// Quantidade M�xima de Reparcelamentos
								// Consecutivos
								null,

								// Id Presta��o
								null,

								// Quantidade M�xima de Presta��es do
								// Parcelamento
								null,

								// Taxa de Juros
								null,

								// Percentual M�nimo de Entrada
								null,

								// Dados do Desconto por Antiguidade do D�bito

								// Id Desconto Antiguidade
								parcelamentoDescontoAntiguidade.getId().toString(),

								// Quantidade M�nima Meses de D�bito p/ Desconto
								parcelamentoDescontoAntiguidade.getQuantidadeMinimaMesesDebito().toString(),

								// Percentual de Desconto Sem Restabelecimento
								Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento()),

								// Percentual de Desconto Com Restabelecimento
								Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento()),

								// Percentual de Desconto Ativo
								Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo()),

								// Dados do Desconto por Inatividade do D�bito

								// Id Desconto Inatividade
								null,

								// Quantidade M�xima Meses de Inatividade
								null,

								// Percentual de Desconto Sem Restabelecimento
								null,

								// Percentual de Desconto Com Restabelecimento
								null

						);

						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioBean);
						
					}

				}

				// Pesquisa a cole��o de parcelamento desconto antiguidade referente
				// ao perfil de parcelamento
				Collection colecaoDescontosInatividade = fachada
						.pesquisarParcelamentoDescontoInatividade(parcelamentoPerfil
								.getId());
				
				if (colecaoDescontosInatividade != null
						&& !colecaoDescontosInatividade.isEmpty()) {

					Iterator colecaoDescontosInatividadeIterator = colecaoDescontosInatividade
							.iterator();

					while (colecaoDescontosInatividadeIterator.hasNext()) {

						ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = (ParcelamentoDescontoInatividade) colecaoDescontosInatividadeIterator
								.next();

						// Cria o Bean setando para nulo os campos que n�o s�o
						// referentes a pesquisa de desconto antiguidade para que o
						// agrupamento fique de maneira correta
						relatorioBean = new RelatorioManterPerfilParcelamentoBean(

								// Dados do Perfil de Parcelamento:

								// Id Parcelamento Perfil
								parcelamentoPerfil.getId().toString(),

								// RD
								parcelamentoPerfil.getResolucaoDiretoria()
										.getNumeroResolucaoDiretoria(),

								// Tipo da Situa��o do Im�vel
								parcelamentoPerfil.getImovelSituacaoTipo() == null ? ""
										: parcelamentoPerfil
												.getImovelSituacaoTipo()
												.getDescricaoImovelSituacaoTipo(),

								// Perfil do Im�vel
								parcelamentoPerfil.getImovelPerfil() == null ? ""
										: parcelamentoPerfil.getImovelPerfil()
												.getDescricao(),

								// Subcategoria
								parcelamentoPerfil.getSubcategoria() == null ? ""
										: parcelamentoPerfil.getSubcategoria()
												.getDescricao(),

								// Percentual Desconto Acr�scimo Impontualidade
								Util.formatarMoedaReal(parcelamentoPerfil
										.getPercentualDescontoAcrescimo()),

								// Percentual da Tarifa M�nima para C�lculo do
								// Valor M�nimo da Presta��o
								Util.formatarMoedaReal(parcelamentoPerfil
										.getPercentualDescontoAcrescimo()),

								// Dados de Reparcelamento Consecutivos e da
								// Presta��o

								// Id Reparcelamento
								null,

								// Quantidade M�xima de Reparcelamentos
								// Consecutivos
								null,

								// Id Presta��o
								null,

								// Quantidade M�xima de Presta��es do
								// Parcelamento
								null,

								// Taxa de Juros
								null,

								// Percentual M�nimo de Entrada
								null,

								// Dados do Desconto por Antiguidade do D�bito

								// Id Desconto Antiguidade
								null,

								// Quantidade M�nima Meses de D�bito p/ Desconto
								null,

								// Percentual de Desconto Sem Restabelecimento
								null,

								// Percentual de Desconto Com Restabelecimento
								null,

								// Percentual de Desconto Ativo
								null,

								// Dados do Desconto por Inatividade do D�bito

								// Id Desconto Inatividade
								parcelamentoDescontoInatividade.getId().toString(),

								// Quantidade M�xima Meses de Inatividade
								parcelamentoDescontoInatividade.getQuantidadeMaximaMesesInatividade().toString(),

								// Percentual de Desconto Sem Restabelecimento
								Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento()),

								// Percentual de Desconto Com Restabelecimento
								Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento())

						);

						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioBean);
						
					}
				}
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// RD
		if (parcelamentoPerfilParametros.getResolucaoDiretoria()
				.getNumeroResolucaoDiretoria() != null) {
			parametros.put("rd", parcelamentoPerfilParametros
					.getResolucaoDiretoria().getNumeroResolucaoDiretoria());
		} else {
			parametros.put("rd", "");
		}

		// Tipo da Situa��o do Im�vel
		if (parcelamentoPerfilParametros.getImovelSituacaoTipo()
				.getDescricaoImovelSituacaoTipo() != null) {
			parametros.put("imovelSituacaoTipo", parcelamentoPerfilParametros
					.getImovelSituacaoTipo().getDescricaoImovelSituacaoTipo());
		} else {
			parametros.put("imovelSituacaoTipo", "");
		}

		// Perfil do Im�vel
		if (parcelamentoPerfilParametros.getImovelPerfil().getDescricao() != null) {
			parametros.put("imovelPerfil", parcelamentoPerfilParametros
					.getImovelPerfil().getDescricao());
		} else {
			parametros.put("imovelPerfil", "");
		}

		// Subcategoria
		if (parcelamentoPerfilParametros.getSubcategoria().getDescricao() != null) {
			parametros.put("subcategoria", parcelamentoPerfilParametros
					.getSubcategoria().getDescricao());
		} else {
			parametros.put("subcategoria", "");
		}

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PERFIL_PARCELAMENTO_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_PERFIL_PARCELAMENTO,
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

		retorno = Fachada
				.getInstancia()
				.totalRegistrosPesquisa(
						(FiltroParcelamentoPerfil) getParametro("filtroParcelamentoPerfil"),
						ParcelamentoPerfil.class.getName());

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterPerfilParcelamento",
				this);
	}

}