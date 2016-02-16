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
 * R�mulo Aur�lio de Melo Souza Filho
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
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio
 * 
 * @author R�mulo Aur�lio
 * @created 23/03/2009
 */
public class RelatorioImoveisComAcordo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioImoveisComAcordo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_COM_ACORDO);
	}

	@Deprecated
	public RelatorioImoveisComAcordo() {
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
	@SuppressWarnings("static-access")
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) getParametro("unidadeNegocio");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegional");
		Localidade localidadeInicial = (Localidade) getParametro("localidadeInicial");
		Localidade localidadeFinal = (Localidade) getParametro("localidadeFinal");
		SetorComercial setorComercialInicial = (SetorComercial) getParametro("setorComercialInicial");
		SetorComercial setorComercialFinal = (SetorComercial) getParametro("setorComercialFinal");
		String rotaInicial = (String) getParametro("rotaInicial");
		String rotaFinal = (String) getParametro("rotaFinal");
		String sequencialRotaInicial = (String) getParametro("sequencialRotaInicial");
		String sequencialRotaFinal = (String) getParametro("sequencialRotaFinal");
		Date dataInicial = (Date) getParametro("dataInicial");
		Date dataFinal = (Date) getParametro("dataFinal");

		@SuppressWarnings("unused")
		String opcaoEmissao = (String) getParametro("opcaoRelatorio");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Collection colecaoRelatorioImoveisComAcordoBean = Fachada
				.getInstancia().pesquisarDadosGerarRelatorioImoveisComAcordo(
						unidadeNegocio == null ? null : unidadeNegocio.getId(),
						localidadeInicial == null ? null : localidadeInicial
								.getId(),
						localidadeFinal == null ? null : localidadeFinal
								.getId(),
						gerenciaRegional == null ? null : gerenciaRegional
								.getId(),
						dataInicial,
						dataFinal,
						rotaInicial == null || rotaInicial.equals("") ? null
								: Integer.parseInt(rotaInicial),
						rotaFinal == null || rotaFinal.equals("") ? null
								: Integer.parseInt(rotaFinal),
						sequencialRotaInicial == null
								|| sequencialRotaInicial.equals("") ? null
								: Integer.parseInt(sequencialRotaInicial),
						sequencialRotaFinal == null
								|| sequencialRotaFinal.equals("") ? null
								: Integer.parseInt(sequencialRotaFinal),
						setorComercialInicial == null ? null
								: setorComercialInicial.getCodigo(),
						setorComercialFinal == null ? null
								: setorComercialFinal.getCodigo());

		if (colecaoRelatorioImoveisComAcordoBean != null
				&& !colecaoRelatorioImoveisComAcordoBean.isEmpty()) {

			Iterator it = colecaoRelatorioImoveisComAcordoBean.iterator();

			while (it.hasNext()) {
				RelatorioImoveisComAcordoBean relatorioImoveisComAcordoBean = (RelatorioImoveisComAcordoBean) it
						.next();

				try {
					relatorioImoveisComAcordoBean.setEndereco(Fachada
							.getInstancia().pesquisarEnderecoAbreviadoCAER(
									Integer.parseInt(relatorioImoveisComAcordoBean
											.getIdImovel())));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

				relatorioImoveisComAcordoBean.setNomeCliente(Fachada
						.getInstancia().pesquisarNomeClientePorImovel(
								Integer.parseInt(relatorioImoveisComAcordoBean
										.getIdImovel())));

				relatorioImoveisComAcordoBean.setInscricao(Fachada
						.getInstancia().pesquisarInscricaoImovel(
								Integer.parseInt(relatorioImoveisComAcordoBean
										.getIdImovel())));

			}

			relatorioBeans.addAll(colecaoRelatorioImoveisComAcordoBean);
		} else {
			throw new ActionServletException("");
		}

		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = Fachada.getInstancia()
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (unidadeNegocio != null) {

			parametros.put("idUnidadeNegocio", unidadeNegocio.getId());
			parametros.put("nomeUnidadeNegocio", unidadeNegocio.getNome());

		} else {

			parametros.put("nomeUnidadeNegocio", "");
		}

		if (gerenciaRegional != null) {

			parametros.put("idGerenciaRegional", gerenciaRegional.getId());
			parametros.put("nomeGerenciaRegional", gerenciaRegional.getNome());

		} else {

			parametros.put("nomeGerenciaRegional", "");
		}

		if (sequencialRotaInicial != null && !sequencialRotaInicial.equals("")) {

			parametros.put("sequencialRotaInicial", sequencialRotaInicial);

		} else {
			parametros.put("sequencialRotaInicial", "");
		}

		if (sequencialRotaFinal != null && !sequencialRotaFinal.equals("")) {

			parametros.put("sequencialRotaFinal", sequencialRotaFinal);

		} else {
			parametros.put("sequencialRotaFinal", "");
		}

		if (rotaInicial != null && !rotaInicial.equals("")) {

			parametros.put("rotaInicial", rotaInicial);

		} else {
			parametros.put("rotaInicial", "");
		}

		if (rotaInicial != null && !rotaInicial.equals("")) {

			parametros.put("rotaFinal", rotaFinal);
		} else {
			parametros.put("rotaFinal", "");
		}

		if (localidadeInicial != null) {

			parametros.put("idLocalidadeInicial", localidadeInicial.getId());
			parametros.put("nomeLocalidadeInicial", localidadeInicial
					.getDescricao());
		} else {

			parametros.put("nomeLocalidadeInicial", "");
		}

		if (localidadeFinal != null) {

			parametros.put("idLocalidadeFinal", localidadeFinal.getId());
			parametros.put("nomeLocalidadeFinal", localidadeFinal
					.getDescricao());
		} else {

			parametros.put("nomeLocalidadeFinal", "");
		}

		parametros.put("dataInicial", Util.formatarData(dataInicial));

		parametros.put("dataFinal", Util.formatarData(dataFinal));
		parametros.put("tipoRelatorio", "R0891");

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_IMOVEIS_COM_ACORDO, parametros,
				ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_IMOVEIS_COM_ACORDO,
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

		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) getParametro("unidadeNegocio");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegional");
		Localidade localidadeInicial = (Localidade) getParametro("localidadeInicial");
		Localidade localidadeFinal = (Localidade) getParametro("localidadeFinal");
		SetorComercial setorComercialInicial = (SetorComercial) getParametro("setorComercialInicial");
		SetorComercial setorComercialFinal = (SetorComercial) getParametro("setorComercialFinal");
		String rotaInicial = (String) getParametro("rotaInicial");
		String rotaFinal = (String) getParametro("rotaFinal");
		String sequencialRotaInicial = (String) getParametro("sequencialRotaInicial");
		String sequencialRotaFinal = (String) getParametro("sequencialRotaFinal");
		Date dataInicial = (Date) getParametro("dataInicial");
		Date dataFinal = (Date) getParametro("dataFinal");

		retorno = Fachada.getInstancia()
				.pesquisarDadosGerarRelatorioImoveisComAcordoCount(
						unidadeNegocio == null ? null : unidadeNegocio.getId(),
						localidadeInicial == null ? null
								: localidadeInicial.getId(),
						localidadeFinal == null ? null : localidadeFinal.getId(),
						gerenciaRegional == null ? null : gerenciaRegional.getId(),
						dataInicial,
						dataFinal,
						rotaInicial == null || rotaInicial.equals("") ? null
								: Integer.parseInt(rotaInicial),
						rotaFinal == null || rotaFinal.equals("") ? null
								: Integer.parseInt(rotaFinal),
						sequencialRotaInicial == null
								|| sequencialRotaInicial.equals("") ? null
								: Integer.parseInt(sequencialRotaInicial),
						sequencialRotaFinal == null
								|| sequencialRotaFinal.equals("") ? null
								: Integer.parseInt(sequencialRotaFinal),
						setorComercialInicial == null ? null
								: setorComercialInicial.getCodigo(),
						setorComercialFinal == null ? null
								: setorComercialFinal.getCodigo());

		if (retorno == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao
			// usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImovelComAcordo", this);
	}
}