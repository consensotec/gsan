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
package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Classe respons�vel por criar o relat�rio de imoveis com consumo medido
 * 
 * @author Bruno Barros
 * @created 17/12/2007
 */
public class RelatorioImoveisConsumoMedio extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioImoveisConsumoMedio(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_CONSUMO_MEDIO);
	}

	@Deprecated
	public RelatorioImoveisConsumoMedio() {
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

		FiltrarRelatorioImoveisConsumoMedioHelper filtro = (FiltrarRelatorioImoveisConsumoMedioHelper) getParametro("filtrarRelatorioImoveisConsumoMedioHelper");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisConsumoMedioBean relatorioImoveisConsumoMedioBean = null;

		Collection<RelatorioImoveisConsumoMedioHelper> colecao = fachada
				.pesquisarRelatorioImoveisConsumoMedio(filtro);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioImoveisConsumoMedioHelper helper = (RelatorioImoveisConsumoMedioHelper) colecaoIterator
						.next();

				relatorioImoveisConsumoMedioBean = new RelatorioImoveisConsumoMedioBean(
						helper);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioImoveisConsumoMedioBean);
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise

		// verifica os par�mtros informados
		String gerenciaRegional = "";
		if (filtro.getGerenciaRegional() != null
				&& !filtro.getGerenciaRegional().toString().equals("")
				&& !filtro.getGerenciaRegional().toString().equals(
						ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, filtro.getGerenciaRegional()));

			Collection colecaoGerencia = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			GerenciaRegional gerencia = (GerenciaRegional) Util
					.retonarObjetoDeColecao(colecaoGerencia);
			gerenciaRegional = gerencia.getNomeAbreviado() + " - "
					+ gerencia.getNome();
		}

		String unidadeNegocio = "";
		if (filtro.getUnidadeNegocio() != null
				&& !filtro.getUnidadeNegocio().toString().equals("")
				&& !filtro.getUnidadeNegocio().toString().equals(
						ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, filtro.getUnidadeNegocio()));

			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeNegocio,
					UnidadeNegocio.class.getName());

			UnidadeNegocio unidade = (UnidadeNegocio) Util
					.retonarObjetoDeColecao(colecaoUnidade);
			unidadeNegocio = unidade.getNome();
		}

		String localidadeInicial = "";
		String localidadeFinal = "";
		if (filtro.getLocalidadeInicial() != null
				&& !filtro.getLocalidadeInicial().toString().equals("")) {
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, filtro.getLocalidadeInicial()));

			Collection colecaoLocalidadeInicial = fachada.pesquisar(
					filtroLocalidadeInicial, Localidade.class.getName());

			Localidade localidadeInicialEncontrada = (Localidade) Util
					.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			localidadeInicial = localidadeInicialEncontrada.getId() + " - "
					+ localidadeInicialEncontrada.getDescricao();

			if (filtro.getLocalidadeFinal() != null
					&& !filtro.getLocalidadeFinal().toString().equals("")) {
				FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
				filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, filtro.getLocalidadeFinal()));

				Collection colecaoLocalidadeFinal = fachada.pesquisar(
						filtroLocalidadeFinal, Localidade.class.getName());

				Localidade localidadeFinalEncontrada = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidadeFinal);
				localidadeFinal = localidadeFinalEncontrada.getId() + " - "
						+ localidadeFinalEncontrada.getDescricao();
			} else {
				localidadeFinal = localidadeInicial;
			}
		}

		String setorComercialInicial = "";
		String setorComercialFinal = "";
		if (filtro.getSetorComercialInicial() != null
				&& !filtro.getSetorComercialInicial().toString().equals("")) {

			FiltroSetorComercial filtroSetorInicial = new FiltroSetorComercial();
			filtroSetorInicial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.LOCALIDADE_ID, filtro
							.getLocalidadeInicial()));
			filtroSetorInicial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, filtro
							.getSetorComercialInicial()));

			Collection colecaoSetorInicial = fachada.pesquisar(
					filtroSetorInicial, SetorComercial.class.getName());

			SetorComercial setorInicialEncontrado = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoSetorInicial);
			setorComercialInicial = setorInicialEncontrado.getCodigo() + " - "
					+ setorInicialEncontrado.getDescricao();

			if (filtro.getSetorComercialFinal() != null
					&& !filtro.getSetorComercialFinal().toString().equals("")) {
				FiltroSetorComercial filtroSetorFinal = new FiltroSetorComercial();
				filtroSetorFinal.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.LOCALIDADE_ID, filtro
								.getLocalidadeFinal()));
				filtroSetorFinal.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, filtro
								.getSetorComercialFinal()));

				Collection colecaoSetorFinal = fachada.pesquisar(
						filtroSetorFinal, SetorComercial.class.getName());

				SetorComercial setorFinalEncontrado = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoSetorFinal);
				setorComercialFinal = setorFinalEncontrado.getCodigo() + " - "
						+ setorFinalEncontrado.getDescricao();
			} else {
				setorComercialFinal = setorComercialInicial;
			}
		}

		String consumoMedioInicial = "";
		String consumoMedioFinal = "";
		if (filtro.getConsumoMedioAguaInicial() != null
				&& !filtro.getConsumoMedioAguaInicial().toString().equals("")) {
			consumoMedioInicial = filtro.getConsumoMedioAguaInicial()
					.toString();

			if (filtro.getConsumoMedioAguaFinal() != null
					&& !filtro.getConsumoMedioAguaFinal().toString().equals("")) {
				consumoMedioFinal = filtro.getConsumoMedioAguaFinal()
						.toString();
			} else {
				consumoMedioFinal = consumoMedioInicial;
			}
		}

		String perfisImovel = "";
		if (filtro.getColecaoPerfisImovel() != null
				&& filtro.getColecaoPerfisImovel().size() > 0) {

			Iterator perfis = filtro.getColecaoPerfisImovel().iterator();
			Collection collectionImovelPerfil = null;
			
			while (perfis.hasNext()) {
				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(
						FiltroImovelPerfil.ID, perfis.next()));
				collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
						ImovelPerfil.class.getName());

				perfisImovel += ((ImovelPerfil) collectionImovelPerfil
						.iterator().next()).getDescricao();

				if (perfis.hasNext()) {
					perfisImovel += ", ";
				}
			}
		}
		
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("gerenciaRegional", gerenciaRegional);
		parametros.put("unidadeNegocio", unidadeNegocio);
		parametros.put("localidadeInicial", localidadeInicial);
		parametros.put("localidadeFinal", localidadeFinal);
		parametros.put("setorComercialInicial", setorComercialInicial);
		parametros.put("setorComercialFinal", setorComercialFinal);
		parametros.put("consumoMedioInicial", consumoMedioInicial);
		parametros.put("consumoMedioFinal", consumoMedioFinal);
		parametros.put("perfisImovel", perfisImovel);
		parametros.put("mesAnoReferencia", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferencia()));
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_IMOVEIS_CONSUMO_MEDIO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.IMOVEIS_CONSUMO_MEDIO, idFuncionalidadeIniciada);
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

		FiltrarRelatorioImoveisConsumoMedioHelper filtro = (FiltrarRelatorioImoveisConsumoMedioHelper) getParametro("filtrarRelatorioImoveisConsumoMedioHelper");

		// verifica se contem algum imovel para gerar o relatorio.
		retorno = Fachada.getInstancia()
				.pesquisarTotalRegistroRelatorioImoveisConsumoMedio(filtro);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisConsumoMedio", this);

	}

}