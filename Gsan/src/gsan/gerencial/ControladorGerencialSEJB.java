/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gerencial;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.batch.ControladorBatchLocal;
import gsan.batch.ControladorBatchLocalHome;
import gsan.batch.UnidadeProcessamento;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FiltroEsferaPoder;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.FiltroCobrancaGrupo;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gerencial.bean.FiltrarRelatorioOrcamentoSINPHelper;
import gsan.gerencial.bean.FiltrarRelatorioQuadroMetasAcumuladoHelper;
import gsan.gerencial.bean.FiltrarRelatorioQuadroMetasExercicioHelper;
import gsan.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gsan.gerencial.bean.InformarDadosGeracaoRelatorioConsultaPeriodoHelper;
import gsan.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gsan.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gsan.gerencial.bean.OrcamentoSINPHelper;
import gsan.gerencial.bean.QuadroMetasAcumuladoHelper;
import gsan.gerencial.bean.QuadroMetasExercicioHelper;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ErroRepositorioException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.ZipUtil;
import gsan.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * 
 * 
 * @author Raphael Rossiter
 * @created 20/05/2006
 */
public class ControladorGerencialSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;

	private IRepositorioGerencial repositorioGerencial = null;

	// private IRepositorioUtil repositorioUtil = null;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		// repositorioUtil = RepositorioUtilHBM.getInstancia();
		repositorioGerencial = RepositorioGerencialHBM.getInstancia();
	}

	/**
	 * Author: Vivianne Sousa Data: 1804/03/2006
	 * 
	 * Retorna o valor do Controlador Util
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Esta funcionalidade permite informar dados para geração de relatórios ou
	 * consultas
	 * 
	 * [UC0304] - Informar Dados para Geração de Relatório ou Consulta
	 * 
	 * @author Raphael Rossiter
	 * @date 22/05/2006
	 * 
	 * @param mesAnoFaturamento
	 * @param opcaoTotalizacao
	 * @param idFauramentoGrupo
	 * @param idGerenciaRegional
	 * @param idEloPolo
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param nmQuadra
	 * @param idsImovelPerfil
	 * @param idsLigacaoAguaSituacao
	 * @param idsLigacaoEsgotoSituacao
	 * @param idsCategoria
	 * @param idsEsferaPoder
	 * @param tipoAnaliseFaturamento
	 * @param tipoRelatorio
	 * @param idUnidadeNegocio
	 * @param idMunicipio
	 * @return InformarDadosGeracaoRelatorioConsultaHelper
	 * @throws ControladorException
	 */
	public InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsulta(
			String mesAnoFaturamento, Integer opcaoTotalizacao,
			Integer idFauramentoGrupo, Integer idCobrancaGrupo,
			Integer idGerenciaRegional, Integer idEloPolo,
			Integer idLocalidade, Integer idSetorComercial, Integer nmQuadra,
			String[] idsImovelPerfil, String[] idsLigacaoAguaSituacao,
			String[] idsLigacaoEsgotoSituacao, String[] idsCategoria,
			String[] idsEsferaPoder, Integer tipoAnaliseFaturamento,
			Integer tipoRelatorio, Integer idUnidadeNegocio, Integer idMunicipio, Integer idRota) throws ControladorException {

		InformarDadosGeracaoRelatorioConsultaHelper retorno = new InformarDadosGeracaoRelatorioConsultaHelper();

		if (!Util.validarMesAno(mesAnoFaturamento)) {
			throw new ControladorException("atencao.ano_mes.invalido");
		}

		Integer anoMesReferencia = new Integer(Util
				.formatarMesAnoParaAnoMesSemBarra(mesAnoFaturamento));
		// SistemaParametro sistemaParametro =
		// this.getControladorUtil().pesquisarParametrosDoSistema();

		// if
		// (anoMesReferencia.compareTo(sistemaParametro.getAnoMesFaturamento())
		// ==
		// 1){
		// throw new
		// ControladorException("atencao.ano.mes.referencia.posterior.ano.mes.faturamento",
		// null,
		// String.valueOf(sistemaParametro.getAnoMesFaturamento()));
		// }

		retorno.setAnoMesReferencia(anoMesReferencia);
		retorno.setOpcaoTotalizacao(opcaoTotalizacao);
		retorno.setDescricaoOpcaoTotalizacao(this
				.obterDescricaoOpcaoTotalizacao(opcaoTotalizacao));

		Collection colecaoPesquisa = null;
		if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GRUPO_FATURAMENTO)
				&& idFauramentoGrupo != null) {

			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, idFauramentoGrupo));

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Grupo de Faturamento");
			}

			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setFaturamentoGrupo(faturamentoGrupo);

		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_GRUPO_COBRANCA)
				&& idCobrancaGrupo != null) {
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.ID, idCobrancaGrupo));

			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Grupo de Cobrança");
			}

			CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setCobrancaGrupo(cobrancaGrupo);
		} else if ((opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL)
				|| opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_UNIDADE_NEGOCIO) 
				|| opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_LOCALIDADE))
				&& idGerenciaRegional != null) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Gerência Regional");
			}

			GerenciaRegional gerenciaRegional = (GerenciaRegional) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setGerenciaRegional(gerenciaRegional);

		} else if ((opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO)
				|| opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_LOCALIDADE) 
				|| opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_SETOR_COMERCIAL))
				&& idUnidadeNegocio != null) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			
			filtroUnidadeNegocio.adicionarParametro(
    				new ParametroSimples(FiltroUnidadeNegocio.ID, 
    						idUnidadeNegocio));
			
    		filtroUnidadeNegocio.adicionarParametro(
    				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
    				ConstantesSistema.INDICADOR_USO_ATIVO));		

    		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
    			this.getControladorUtil().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

    		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
    			throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Unidade Negócio");
    		}else{
    			
    			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util
					.retonarObjetoDeColecao(colecaoUnidadeNegocio);

    			retorno.setUnidadeNegocio(unidadeNegocio);
    			
    		}
	
		} else if ((opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_LOCALIDADE)
				|| opcaoTotalizacao
						.equals(ConstantesSistema.CODIGO_LOCALIDADE_SETOR_COMERCIAL) || opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_LOCALIDADE_QUADRA))
				&& idLocalidade != null) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Localidade");
			}

			Localidade localidade = (Localidade) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setLocalidade(localidade);
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_MUNICIPIO) && idMunicipio != null) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroMunicipio, Municipio.class.getName());

			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
				Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
				retorno.setMunicipio(municipio);
			}else{
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Município");
			}
		
		} else if ((opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL) || opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA))
				&& idSetorComercial != null) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial
					.adicionarCaminhoParaCarregamentoEntidade("localidade");

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID, idSetorComercial));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Setor Comercial");
			}

			SetorComercial setorComercial = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setLocalidade(setorComercial.getLocalidade());
			retorno.setSetorComercial(setorComercial);

		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_QUADRA)
				&& idSetorComercial != null && nmQuadra != null) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, nmQuadra));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Quadra");
			}

			Quadra quadra = (Quadra) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setLocalidade(quadra.getSetorComercial().getLocalidade());
			retorno.setSetorComercial(quadra.getSetorComercial());
			retorno.setQuadra(quadra);

		/** [RR2011071026]
		 * 	Autor: Paulo Diniz
		 *  Data: 21/07/2011
		 *  Resumo da Análise do Faturamento 
		 */
		}else if ( opcaoTotalizacao.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_ROTA)
				&& idRota != null) {

			FiltroRota filtroRota = new FiltroRota();
			
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroRota, Rota.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Rota");
			}

			Rota rota = (Rota) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setRota(rota);

		}

		if (idsImovelPerfil != null && idsImovelPerfil.length > 0) {

			Collection colecaoImovelPerfil = new ArrayList();
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			ImovelPerfil imovelPerfil = null;

			for (int index = 0; idsImovelPerfil.length > index; index++) {

				if (idsImovelPerfil[index] != null
						&& idsImovelPerfil[index].length() > 0) {

					filtroImovelPerfil.adicionarParametro(new ParametroSimples(
							FiltroImovelPerfil.ID, idsImovelPerfil[index]));

					filtroImovelPerfil.adicionarParametro(new ParametroSimples(
							FiltroImovelPerfil.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroImovelPerfil, ImovelPerfil.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Perfil do Imóvel");
					}

					imovelPerfil = (ImovelPerfil) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroImovelPerfil.limparListaParametros();

					colecaoImovelPerfil.add(imovelPerfil);
				}
			}

			if (!colecaoImovelPerfil.isEmpty()) {
				retorno.setColecaoImovelPerfil(colecaoImovelPerfil);
			}

		}

		if (idsLigacaoAguaSituacao != null && idsLigacaoAguaSituacao.length > 0) {

			Collection colecaoLigacaoAguaSituacao = new ArrayList();
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;

			for (int index = 0; idsLigacaoAguaSituacao.length > index; index++) {

				if (idsLigacaoAguaSituacao[index] != null
						&& idsLigacaoAguaSituacao[index].length() > 0) {
					filtroLigacaoAguaSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroLigacaoAguaSituacao.ID,
									idsLigacaoAguaSituacao[index]));

					filtroLigacaoAguaSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroLigacaoAguaSituacao.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroLigacaoAguaSituacao,
							LigacaoAguaSituacao.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Ligação de Água");
					}

					ligacaoAguaSituacao = (LigacaoAguaSituacao) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroLigacaoAguaSituacao.limparListaParametros();

					colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacao);
				}
			}

			if (!colecaoLigacaoAguaSituacao.isEmpty()) {
				retorno
						.setColecaoLigacaoAguaSituacao(colecaoLigacaoAguaSituacao);
			}

		}

		if (idsLigacaoEsgotoSituacao != null
				&& idsLigacaoEsgotoSituacao.length > 0) {

			Collection colecaoLigacaoEsgotoSituacao = new ArrayList();
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

			for (int index = 0; idsLigacaoEsgotoSituacao.length > index; index++) {

				if (idsLigacaoEsgotoSituacao[index] != null
						&& idsLigacaoEsgotoSituacao[index].length() > 0) {

					filtroLigacaoEsgotoSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroLigacaoEsgotoSituacao.ID,
									idsLigacaoEsgotoSituacao[index]));

					filtroLigacaoEsgotoSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroLigacaoEsgotoSituacao,
							LigacaoEsgotoSituacao.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Ligação de Esgoto");
					}

					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroLigacaoEsgotoSituacao.limparListaParametros();

					colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacao);
				}
			}

			if (!colecaoLigacaoEsgotoSituacao.isEmpty()) {
				retorno
						.setColecaoLigacaoEsgotoSituacao(colecaoLigacaoEsgotoSituacao);
			}

		}

		if (idsCategoria != null && idsCategoria.length > 0) {

			Collection colecaoCategoria = new ArrayList();
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			Categoria categoria = null;

			for (int index = 0; idsCategoria.length > index; index++) {

				if (idsCategoria[index] != null
						&& idsCategoria[index].length() > 0) {

					filtroCategoria.adicionarParametro(new ParametroSimples(
							FiltroCategoria.CODIGO, idsCategoria[index]));

					filtroCategoria.adicionarParametro(new ParametroSimples(
							FiltroCategoria.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroCategoria, Categoria.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Categoria");
					}

					categoria = (Categoria) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroCategoria.limparListaParametros();

					colecaoCategoria.add(categoria);
				}
			}

			if (!colecaoCategoria.isEmpty()) {
				retorno.setColecaoCategoria(colecaoCategoria);
			}
		}

		if (idsEsferaPoder != null && idsEsferaPoder.length > 0) {

			Collection colecaoEsferaPoder = new ArrayList();
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			EsferaPoder esferaPoder = null;

			for (int index = 0; idsEsferaPoder.length > index; index++) {

				if (idsEsferaPoder[index] != null
						&& idsEsferaPoder[index].length() > 0) {

					filtroEsferaPoder.adicionarParametro(new ParametroSimples(
							FiltroEsferaPoder.ID, idsEsferaPoder[index]));

					filtroEsferaPoder.adicionarParametro(new ParametroSimples(
							FiltroEsferaPoder.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroEsferaPoder, EsferaPoder.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Esfera de Poder");
					}

					esferaPoder = (EsferaPoder) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroEsferaPoder.limparListaParametros();

					colecaoEsferaPoder.add(esferaPoder);
				}
			}

			if (!colecaoEsferaPoder.isEmpty()) {
				retorno.setColecaoEsferaPoder(colecaoEsferaPoder);
			}

		}

		if (tipoAnaliseFaturamento != null) {
			retorno.setTipoAnaliseFaturamento(tipoAnaliseFaturamento);
		}

		if (tipoRelatorio != null) {
			retorno.setGerarRelatorio(true);
			retorno.setTipoRelatorio(tipoRelatorio);
		}

		return retorno;
	}

	/**
	 * Obtém a descrição da opção de totalização que está localizada nas
	 * constantes
	 * 
	 * [UC0304] - Informar Dados para Geração de Relatório ou Consulta
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2006
	 * 
	 * @param opcaoTotalizacao
	 * @return String
	 */
	public String obterDescricaoOpcaoTotalizacao(Integer opcaoTotalizacao) {
		String retorno = null;

		if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO)) {
			retorno = ConstantesSistema.ESTADO;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_ESTADO_ELO_POLO)) {
			retorno = ConstantesSistema.ESTADO_ELO_POLO;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_ESTADO_GERENCIA_REGIONAL)) {
			retorno = ConstantesSistema.ESTADO_GERENCIA_REGIONAL;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_ESTADO_GRUPO_FATURAMENTO)) {
			retorno = ConstantesSistema.ESTADO_GRUPO_FATURAMENTO;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_ESTADO_GRUPO_COBRANCA)) {
			retorno = ConstantesSistema.ESTADO_GRUPO_COBRANCA;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_ESTADO_LOCALIDADE)) {
			retorno = ConstantesSistema.ESTADO_LOCALIDADE;
		} else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_MUNICIPIO)){
			retorno = ConstantesSistema.ESTADO_MUNICIPIO;
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ELO_POLO)) {
			retorno = ConstantesSistema.ELO_POLO;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_ELO_POLO_LOCALIDADE)) {
			retorno = ConstantesSistema.ELO_POLO_LOCALIDADE;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_ELO_POLO_SETOR_COMERCIAL)) {
			retorno = ConstantesSistema.ELO_POLO_SETOR_COMERCIAL;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL)) {
			retorno = ConstantesSistema.GERENCIA_REGIONAL;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_ELO_POLO)) {
			retorno = ConstantesSistema.GERENCIA_REGIONAL_ELO_POLO;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_LOCALIDADE)) {
			retorno = ConstantesSistema.GERENCIA_REGIONAL_LOCALIDADE;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_GRUPO_FATURAMENTO)) {
			retorno = ConstantesSistema.GRUPO_FATURAMENTO;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_GRUPO_COBRANCA)) {
			retorno = ConstantesSistema.GRUPO_COBRANCA;
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_LOCALIDADE)) {
			retorno = ConstantesSistema.LOCALIDADE;
		} else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_MUNICIPIO)){
			retorno = ConstantesSistema.MUNICIPIO;
		}else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_LOCALIDADE_QUADRA)) {
			retorno = ConstantesSistema.LOCALIDADE_QUADRA;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_LOCALIDADE_SETOR_COMERCIAL)) {
			retorno = ConstantesSistema.LOCALIDADE_SETOR_COMERCIAL;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL)) {
			retorno = ConstantesSistema.SETOR_COMERCIAL;
		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA)) {
			retorno = ConstantesSistema.SETOR_COMERCIAL_QUADRA;
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_QUADRA)) {
			retorno = ConstantesSistema.QUADRA;
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO)) {
			retorno = ConstantesSistema.UNIDADE_NEGOCIO;
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_UNIDADE_NEGOCIO)) {
			retorno = ConstantesSistema.ESTADO_UNIDADE_NEGOCIO;
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_UNIDADE_NEGOCIO)) {
			retorno = ConstantesSistema.GERENCIA_REGIONAL_UNIDADE_NEGOCIO;
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_LOCALIDADE)) {
			retorno = ConstantesSistema.UNIDADE_NEGOCIO_LOCALIDADE;
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_SETOR_COMERCIAL)) {
			retorno = ConstantesSistema.UNIDADE_NEGOCIO_SETOR_COMERCIAL;
		}

		return retorno;
	}

	/**
	 * Método para auxilio de Casos de Uso de resumos
	 */

	public Collection criarColecaoAgrupamentoResumos(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		/**
		 * Este caso de uso serve para montar uma coleção de Object[] para
		 * montagem da parte Tabela Dados da Geração da Consulta q se refere as
		 * opções de agrupamento(no jsp).
		 * 
		 * No Action q chamar esse método, mandar o retorno deste método para o
		 * jsp atraves do request com o nome de colecaoAgrupamento.
		 * 
		 * ver Action ExibirResultadoConsultaResumoAnormalidadeAction.java
		 */

		Collection colecaoLocalidade = null;
		Collection colecaoAgrupamento = new ArrayList();
		FiltroLocalidade filtroLocalidade = null;
		Localidade localidade = null;
		Object[] objeto = null;
		switch (informarDadosGeracaoRelatorioConsultaHelper
				.getOpcaoTotalizacao().intValue()) {
		case ConstantesSistema.CODIGO_GRUPO_FATURAMENTO:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.GRUPO_FATURAMENTO;
			objeto[1] = informarDadosGeracaoRelatorioConsultaHelper
					.getFaturamentoGrupo().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaHelper
					.getFaturamentoGrupo().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_GRUPO_COBRANCA:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.GRUPO_COBRANCA;
			objeto[1] = informarDadosGeracaoRelatorioConsultaHelper
					.getCobrancaGrupo().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaHelper
					.getCobrancaGrupo().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_GERENCIA_REGIONAL:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.GERENCIA_REGIONAL;
			objeto[1] = informarDadosGeracaoRelatorioConsultaHelper
					.getGerenciaRegional().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaHelper
					.getGerenciaRegional().getNomeAbreviado();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_ELO_POLO:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.ELO_POLO;
			objeto[1] = informarDadosGeracaoRelatorioConsultaHelper
					.getEloPolo().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaHelper
					.getEloPolo().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_LOCALIDADE:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.LOCALIDADE;
			objeto[1] = informarDadosGeracaoRelatorioConsultaHelper
					.getLocalidade().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaHelper
					.getLocalidade().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_SETOR_COMERCIAL:
			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID,
					informarDadosGeracaoRelatorioConsultaHelper
							.getSetorComercial().getLocalidade().getId()));
			colecaoLocalidade = getControladorUtil().pesquisar(
					filtroLocalidade, Localidade.class.getName());
			localidade = (Localidade) colecaoLocalidade.iterator().next();

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.LOCALIDADE;
			objeto[1] = localidade.getId().toString();
			objeto[2] = localidade.getDescricao();
			colecaoAgrupamento.add(objeto);

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.SETOR_COMERCIAL;
			objeto[1] = informarDadosGeracaoRelatorioConsultaHelper
					.getSetorComercial().getCodigo()
					+ "";
			objeto[2] = informarDadosGeracaoRelatorioConsultaHelper
					.getSetorComercial().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_QUADRA:
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID,
					informarDadosGeracaoRelatorioConsultaHelper.getQuadra()
							.getSetorComercial().getId()));
			Collection colecaoSetorComercial = getControladorUtil().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercial = (SetorComercial) colecaoSetorComercial
					.iterator().next();

			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade
					.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, setorComercial.getLocalidade()
									.getId()));
			colecaoLocalidade = getControladorUtil().pesquisar(
					filtroLocalidade, Localidade.class.getName());
			localidade = (Localidade) colecaoLocalidade.iterator().next();

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.LOCALIDADE;
			objeto[1] = localidade.getId().toString();
			objeto[2] = localidade.getDescricao();
			colecaoAgrupamento.add(objeto);

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.SETOR_COMERCIAL;
			objeto[1] = setorComercial.getCodigo() + "";
			objeto[2] = setorComercial.getDescricao();
			colecaoAgrupamento.add(objeto);

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.QUADRA;
			objeto[1] = informarDadosGeracaoRelatorioConsultaHelper.getQuadra()
					.getNumeroQuadra()
					+ "";
			objeto[2] = "";
			colecaoAgrupamento.add(objeto);
			break;

		case ConstantesSistema.CODIGO_ESTADO:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.ESTADO;
			objeto[1] = "1";
			objeto[2] = "Estado";
			colecaoAgrupamento.add(objeto);
			break;

		}
		return colecaoAgrupamento;
	}

	/**
	 * Método para auxilio de Casos de Uso de resumos
	 */

	public Collection criarColecaoAgrupamentoResumosCobrancaAcao(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ControladorException {
		/**
		 * Este caso de uso serve para montar uma coleção de Object[] para
		 * montagem da parte Tabela Dados da Geração da Consulta q se refere as
		 * opções de agrupamento(no jsp).
		 * 
		 * No Action q chamar esse método, mandar o retorno deste método para o
		 * jsp atraves do request com o nome de colecaoAgrupamento.
		 * 
		 * ver Action ExibirResultadoConsultaResumoAnormalidadeAction.java
		 */

		Collection colecaoAgrupamento = new ArrayList();
		Object[] objeto = null;
		if (informarDadosGeracaoResumoAcaoConsultaHelper.getEloPolo() != null) {
			objeto = new Object[3];
			objeto[0] = "Elo Pólo";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaHelper
					.getEloPolo().getId().toString();
			objeto[2] = informarDadosGeracaoResumoAcaoConsultaHelper
					.getEloPolo().getDescricao();
			colecaoAgrupamento.add(objeto);
		}
		if (informarDadosGeracaoResumoAcaoConsultaHelper.getLocalidade() != null) {
			objeto = new Object[3];
			objeto[0] = "Localidade";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaHelper
					.getLocalidade().getId().toString();
			objeto[2] = informarDadosGeracaoResumoAcaoConsultaHelper
					.getLocalidade().getDescricao();
			colecaoAgrupamento.add(objeto);
		}
		if (informarDadosGeracaoResumoAcaoConsultaHelper.getSetorComercial() != null) {

			objeto = new Object[3];
			objeto[0] = "Setor Comercial";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaHelper
					.getSetorComercial().getCodigo()
					+ "";
			objeto[2] = informarDadosGeracaoResumoAcaoConsultaHelper
					.getSetorComercial().getDescricao();
			colecaoAgrupamento.add(objeto);
		}
		if (informarDadosGeracaoResumoAcaoConsultaHelper.getQuadra() != null) {

			objeto = new Object[3];
			objeto[0] = "Quadra";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaHelper
					.getQuadra().getNumeroQuadra()
					+ "";
			objeto[2] = "";
			colecaoAgrupamento.add(objeto);
		}

		return colecaoAgrupamento;
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection criarColecaoAgrupamentoResumosCobrancaAcaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException {

		Collection colecaoAgrupamento = new ArrayList();
		Object[] objeto = null;
		if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
				.getDataInicialEmissao() != null
				&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getDataFinalEmissao() != null) {
			objeto = new Object[3];
			objeto[0] = "Período de Emissão";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getFormatarDataEmissaoInicial();
			objeto[2] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getFormatarDataEmissaoFinal();
			colecaoAgrupamento.add(objeto);
		}
		if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
				.getIdCobrancaAcaoAtividadeComando() != null) {
			objeto = new Object[3];
			objeto[0] = "Comando";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getTituloCobrancaAcaoAtividadeComando();
			objeto[2] = "";
			colecaoAgrupamento.add(objeto);
		}
		if (informarDadosGeracaoResumoAcaoConsultaEventualHelper.getEloPolo() != null) {
			objeto = new Object[3];
			objeto[0] = "Elo Pólo";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getEloPolo().getId().toString();
			objeto[2] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getEloPolo().getDescricao();
			colecaoAgrupamento.add(objeto);
		}
		if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
				.getLocalidade() != null) {
			objeto = new Object[3];
			objeto[0] = "Localidade";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getLocalidade().getId().toString();
			objeto[2] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getLocalidade().getDescricao();
			colecaoAgrupamento.add(objeto);
		}
		if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
				.getSetorComercial() != null) {

			objeto = new Object[3];
			objeto[0] = "Setor Comercial";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getSetorComercial().getCodigo()
					+ "";
			objeto[2] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getSetorComercial().getDescricao();
			colecaoAgrupamento.add(objeto);
		}
		if (informarDadosGeracaoResumoAcaoConsultaEventualHelper.getQuadra() != null) {

			objeto = new Object[3];
			objeto[0] = "Quadra";
			objeto[1] = informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getQuadra().getNumeroQuadra()
					+ "";
			objeto[2] = "";
			colecaoAgrupamento.add(objeto);
		}

		return colecaoAgrupamento;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * [UC0350 - Consultar Comparativo entre os Resumos do Faturamento,
	 * Arrecadação e da Pendência]
	 * 
	 * @author Pedro Alexandre
	 * @date 09/06/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarComparativoResumosFaturamentoArrecadacaoPendencia(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		List retorno = null;
		try {
			retorno = repositorioGerencial
					.consultarComparativoResumosFaturamentoArrecadacaoPendencia(informarDadosGeracaoRelatorioConsultaHelper);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}

	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da faturamento
	 * 
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento,
	 * Arrecadação e da Pendência.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/06/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoFaturamento(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		List retorno = null;
		try {
			retorno = repositorioGerencial
					.consultarResumoFaturamento(informarDadosGeracaoRelatorioConsultaHelper);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}

	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da arrecadação
	 * 
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento,
	 * Arrecadação e da Pendência.
	 * 
	 * @author Pedro Alexandre
	 * @date 10/06/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoArrecadacao(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		List retorno = null;
		try {
			retorno = repositorioGerencial
					.consultarResumoArrecadacao(informarDadosGeracaoRelatorioConsultaHelper);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}

	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da pendência.
	 * 
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento,
	 * Arrecadação e da Pendência.
	 * 
	 * @author Pedro Alexandre
	 * @date 10/06/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoComparativoPendencia(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		List retorno = null;
		try {
			retorno = repositorioGerencial
					.consultarComparativoResumoPendencia(informarDadosGeracaoRelatorioConsultaHelper);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_faturamento
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 20/11/2006
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Collection<OrcamentoSINPHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection<OrcamentoSINPHelper> pesquisarRelatorioOrcamentoSINP(
		FiltrarRelatorioOrcamentoSINPHelper filtro) 
		throws ControladorException {
		
		Collection<OrcamentoSINPHelper> colecaoOrcamentoSINP = 
			new ArrayList<OrcamentoSINPHelper>();
		
		try {
		
			int opcaoTotalizacao = filtro.getOpcaoTotalizacao();
	
			Collection<Integer> chavesLocalidade = null;
			Collection<Integer> chavesUnidade = null;
			Collection<Integer> chavesGerencia = null;
			Collection<Integer> chavesMunicipio = null;
			
			//Pega todas os campos
			if((opcaoTotalizacao >=1 && opcaoTotalizacao <= 5)){
				chavesGerencia = this.repositorioGerencial.pesquisarGerenciasRegionais();
			}else if(opcaoTotalizacao == 26){
				chavesMunicipio = this.repositorioGerencial.pesquisarMunicipiosAssociadosLocalidade();
			}else{
				chavesLocalidade = filtro.getChavesLocalidade();
				chavesUnidade = filtro.getChavesUnidade();
				chavesGerencia = filtro.getChavesGerencia();
				chavesMunicipio = filtro.getChavesLocalidadesMunicipio();
			}
			
			switch (opcaoTotalizacao) {
			
				//Estado
				case 1:
					filtro.setOpcaoAgrupamento("");
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,0,null));
					break;
	
				// Estado por Gerencia Regional				
				case 2:
	
					filtro.setOpcaoAgrupamento("");
					//Gerencia Regional
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,3,chavesGerencia));
	
					//Estado
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,0,null));
					break;
	
				// Estado por Unidade Negocio
				case 3:
					
					if(chavesGerencia != null && !chavesGerencia.isEmpty()){
						
						Iterator ite = chavesGerencia.iterator();
						Collection chavesGerenciaAux = null;
						
						while(ite.hasNext()){
							
							Integer idGerencia = (Integer)ite.next();
							
							//pesquisa a gerencia regional para recuperar a descrição
							FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
							filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,idGerencia));
							Collection colecaoGerenciaBase = getControladorUtil().pesquisar(filtroGerencia,GerenciaRegional.class.getName());
							GerenciaRegional gerenciaBase = (GerenciaRegional)Util.retonarObjetoDeColecao(colecaoGerenciaBase);
							filtro.setOpcaoAgrupamento("GERÊNCIA:"+gerenciaBase.getNome());
							
							chavesUnidade = this.repositorioGerencial.pesquisarUnidadesNegocios(idGerencia);
			
							//Unidade de Negocio
							colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,2,chavesUnidade));
			
							//Unidade de negocio
							filtro.setUnidadeNegocio(null);
							filtro.setOpcaoAgrupamento("");
							
							//manda de uma em uma gerência para ficar ordenado
							chavesGerenciaAux = new ArrayList();
							chavesGerenciaAux.add(idGerencia);
							colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,3,chavesGerenciaAux));
							chavesGerenciaAux = null;
							
							//Gerencia Regional
							filtro.setGerenciaRegional(null);
							
						}
					}
	
					//Estado
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,0,null));
					break;
	
				// Estado por Localidade	
				case 5:
					if(chavesGerencia != null && !chavesGerencia.isEmpty()){
						Iterator ite = chavesGerencia.iterator();
						Collection chavesGerenciaAux = null;
						Collection chavesNegocioAux = null;
						while(ite.hasNext()){
							Integer idGerencia = (Integer)ite.next();
							//pesquisa a gerencia regional para recuperar a descrição
							FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
							filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,idGerencia));
							Collection colecaoGerenciaBase = getControladorUtil().pesquisar(filtroGerencia,GerenciaRegional.class.getName());
							GerenciaRegional gerenciaBase = (GerenciaRegional)Util.retonarObjetoDeColecao(colecaoGerenciaBase);
							chavesUnidade = this.repositorioGerencial.pesquisarUnidadesNegocios(idGerencia);
							if(chavesUnidade != null && !chavesUnidade.isEmpty()){
								Iterator iteNegocio = chavesUnidade.iterator();
								while(iteNegocio.hasNext()){
									Integer idNegocio = (Integer)iteNegocio.next();
									//pesquisa a gerencia regional para recuperar a descrição
									FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio();
									filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,idNegocio));
									Collection colecaoBaseBase = getControladorUtil().pesquisar(filtroUnidade,UnidadeNegocio.class.getName());
									UnidadeNegocio unidadeBase = (UnidadeNegocio)Util.retonarObjetoDeColecao(colecaoBaseBase);
									chavesLocalidade = this.repositorioGerencial.pesquisarLocalidades(idNegocio);
									//caso não tenha localidade para a unidade
									if(chavesLocalidade != null && !chavesLocalidade.isEmpty()){
										filtro.setOpcaoAgrupamento("GERÊNCIA:"+gerenciaBase.getNome()+" /UNIDADE:"+unidadeBase.getNome());
										//Localidade
										colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,1,chavesLocalidade)) ;	
									}
									
									if(gerenciaBase != null){
										//manda de uma em uma gerência para ficar ordenado
										chavesNegocioAux = new ArrayList();
										chavesNegocioAux.add(idNegocio);
										//Unidade de Negocio
										filtro.setLocalidade(null);
										filtro.setOpcaoAgrupamento("GERÊNCIA:"+gerenciaBase.getNome());
										colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,2,chavesNegocioAux));
										chavesNegocioAux = null;
										//Unidade de Negocio
										filtro.setUnidadeNegocio(null);
									}
								}
							}
							//manda de uma em uma gerência para ficar ordenado
							chavesGerenciaAux = new ArrayList();
							chavesGerenciaAux.add(idGerencia);
							//Unidade de Negocio
							filtro.setUnidadeNegocio(null);
							filtro.setOpcaoAgrupamento("");
							colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,3,chavesGerenciaAux));
							chavesGerenciaAux = null;
							//Gerencia Regional
							filtro.setGerenciaRegional(null);
						}
					}
					filtro.setOpcaoAgrupamento("");
					//Estado
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,0,null));
					
					break;
	            

				//Estado por Município	
				case 26:
					if(chavesMunicipio != null && !chavesMunicipio.isEmpty()){
						filtro.setOpcaoAgrupamento("");
						//Município Associado à Localidade
						colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,4,chavesMunicipio));
						
						//Estado
						colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,0,null));
					}
					break;	
					
				// Gerencia Regional
				case 6:
					filtro.setOpcaoAgrupamento("");
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,3,chavesGerencia));
					break;
				
				// Unidade de Negocio				
				case 10:
					Integer keyUnidade = (Integer)Util.retonarObjetoDeColecao(chavesUnidade);
					//pesquisa a gerencia regional para recuperar a descrição
					FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio();
					filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,keyUnidade));
					filtroUnidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
					Collection colecaoUnidadeBase = getControladorUtil().pesquisar(filtroUnidade,UnidadeNegocio.class.getName());
					UnidadeNegocio unidadeBase = (UnidadeNegocio)Util.retonarObjetoDeColecao(colecaoUnidadeBase);
					
					filtro.setOpcaoAgrupamento("GERÊNCIA:"+unidadeBase.getGerenciaRegional().getNome());
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,2,chavesUnidade));
					break;
				
				// Localidade
				case 16:
					Integer keyLocalidade = (Integer)Util.retonarObjetoDeColecao(chavesLocalidade);
					Object[] nomeGerenciaUnidade = repositorioGerencial.pesquisarNomesUnidadeGerencia(keyLocalidade);
					if(nomeGerenciaUnidade != null){
						filtro.setOpcaoAgrupamento("GERÊNCIA:"+nomeGerenciaUnidade[0]+" /UNIDADE:"+nomeGerenciaUnidade[1]);
					}else{
						filtro.setOpcaoAgrupamento("");
					}
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,1,chavesLocalidade)) ;
					break;
					
                // Município
				case 19:
					
					Integer keyMunicipio = (Integer)Util.retonarObjetoDeColecao(chavesMunicipio);
					//pesquisa a gerencia regional para recuperar a descrição
					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
					filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID,keyMunicipio));
					Collection colecaoMunicipio = getControladorUtil().pesquisar(filtroMunicipio,Municipio.class.getName());
					
					Municipio municipio = (Municipio)Util.retonarObjetoDeColecao(colecaoMunicipio);
					
					filtro.setOpcaoAgrupamento("");
					colecaoOrcamentoSINP.addAll(this.montarColecaoOrcamentoSINPHelper(filtro,4,chavesMunicipio));
					break;
		
				default:
					break;
			}
		
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoOrcamentoSINP;
	}	
	
	
	/**
	 * Verifica se existe dados nas tabelas de resumo
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 11/01/2007
	 *
	 * @param anoMesReferencia
	 * 
	 * @throws ControladorException
	 */
	public void validarDadosOrcamentoSINP(int anoMesReferencia) 
		throws ControladorException {
		
		try {
			boolean existeDados = this.repositorioGerencial.existeDadosUnResumoParaOrcamentoSINP(anoMesReferencia);

			if(!existeDados){
				throw new ControladorException("atencao.sem_registros_gerencias");
			}
			
			existeDados = this.repositorioGerencial.existeDadosComercialParaOrcamentoSINP(anoMesReferencia);

			if(!existeDados){
				throw new ControladorException("atencao.sem_registros_comerciais");
			}

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_faturamento e financeiro.resumo_faturamento
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto, Hugo Leonardo
	 * @date 20/11/2006, 24/02/2011
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Collection<OrcamentoSINPHelper>
	 * 
	 * @throws ControladorException
	 */
	private Collection<OrcamentoSINPHelper> montarColecaoOrcamentoSINPHelper(
		FiltrarRelatorioOrcamentoSINPHelper filtro,
		int tipoGroupBy,
		Collection<Integer> chaves) throws ControladorException {

		Collection<OrcamentoSINPHelper> colecaoOrcamentoSINP = 
			new ArrayList<OrcamentoSINPHelper>();
		
		String descricaoTotalizacao = "";
		
		
		//Vai montar o relatorio agrupado por Localidade
		String groupBy = "";
		
		switch (tipoGroupBy) {

			// Localidade
			case 1:
				groupBy = ",rfat.loca_id";
				break;

			// UnidadeNegocio
			case 2:
				groupBy = ",rfat.uneg_id";
				break;
				
			// Gerencia Regional
			case 3:
				groupBy = ",rfat.greg_id";
				break;
			
			// Município
			case 4:
				groupBy = ",loc.muni_idprincipal";
				break;
				
			default:
				groupBy = "";
				break;
		}

		if(chaves != null && !chaves.isEmpty()){

			Collection colecaoFiltro = null;
			
			Localidade localidade = null;
			GerenciaRegional gerencia = null;
			UnidadeNegocio unidade = null;
			Municipio municipio = null;
			
			filtro.setGroupBy(groupBy);
			
			Iterator itera = chaves.iterator();
			
			while (itera.hasNext()) {
				
				Integer key = (Integer) itera.next();
				
				switch (tipoGroupBy) {
				
				// Localidade
				case 1:
					filtro.setLocalidade(key);
					
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(
						new ParametroSimples(FiltroLocalidade.ID,key));
							
					colecaoFiltro = 
						this.getControladorUtil().pesquisar(filtroLocalidade,Localidade.class.getName());
							
					localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoFiltro);
					
					String centroCusto = localidade.getCodigoCentroCusto()!= null?localidade.getCodigoCentroCusto():"";
						
					//caso n exista o centro de custo na localidade, então coloca vazio.
					descricaoTotalizacao = "LOCALIDADE:"+localidade.getDescricao().trim()+" - "+centroCusto;

					break;

				// UnidadeNegocio
				case 2:
					
					filtro.setUnidadeNegocio(key);
					
					FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio();
					filtroUnidade.adicionarParametro(
						new ParametroSimples(FiltroUnidadeNegocio.ID,key));
								
					colecaoFiltro = 
						this.getControladorUtil().pesquisar(filtroUnidade,UnidadeNegocio.class.getName());
							
					if (colecaoFiltro != null && !colecaoFiltro.isEmpty()) {
						unidade = (UnidadeNegocio) Util
								.retonarObjetoDeColecao(colecaoFiltro);

						descricaoTotalizacao = "UNIDADE:"
								+ unidade.getNome().trim();
					}
					break;
					
				// Gerencia Regional
				case 3:

					filtro.setGerenciaRegional(key);
					
					FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
					filtroGerencia.adicionarParametro(
						new ParametroSimples(FiltroGerenciaRegional.ID,key));
									
					colecaoFiltro = 
						this.getControladorUtil().pesquisar(filtroGerencia,GerenciaRegional.class.getName());
								
					if (colecaoFiltro != null && !colecaoFiltro.isEmpty()) {
						gerencia = (GerenciaRegional) Util
								.retonarObjetoDeColecao(colecaoFiltro);

						descricaoTotalizacao = "GERÊNCIA:"
								+ gerencia.getNome().trim();
					}
					break;
			
					
				//Municipio
				case 4:
					filtro.setMunicipio(key);	
					
					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
					filtroMunicipio.adicionarParametro(
						new ParametroSimples(FiltroMunicipio.ID,key));
									
					colecaoFiltro = 
						this.getControladorUtil().pesquisar(filtroMunicipio,Municipio.class.getName());
								
					municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoFiltro);
							
					descricaoTotalizacao = "MUNICÍPIO:"+municipio.getNome().trim();
					break;
				}
				
				OrcamentoSINPHelper orcamento = 
					this.montarOrcamentoSINPHelper(filtro);
				
				if (orcamento != null) {
					orcamento.setOpcaoTotalizacao(descricaoTotalizacao);
					orcamento.setOpcaoAgrupamento(filtro.getOpcaoAgrupamento());
					colecaoOrcamentoSINP.add(orcamento);
				}
				
			}
		} else { //fim do if chave

			// Estado
			filtro.setLocalidade(null);
			filtro.setUnidadeNegocio(null);
			filtro.setGerenciaRegional(null);
			filtro.setMunicipio(null);
			filtro.setGroupBy(groupBy);

			//descricaoTotalizacao = "TOTAL GERAL PARA ESTADO";
			
			OrcamentoSINPHelper orcamento = 
				this.montarOrcamentoSINPHelper(filtro);
			
			if (orcamento != null) {
				orcamento.setOpcaoTotalizacao(descricaoTotalizacao);
				colecaoOrcamentoSINP.add(orcamento);
			}
			
		}
		return colecaoOrcamentoSINP;
	}
	
	
	private OrcamentoSINPHelper montarOrcamentoSINPHelper(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper)
		throws ControladorException {
		
		OrcamentoSINPHelper orcamento = new OrcamentoSINPHelper();
		
		try {
			Object[] resumoLigacaoEconomia = 
				this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPResumoLigacaoEconomia(
					filtrarRelatorioOrcamentoSINPHelper);
			
			boolean temDados = false;
			
			if(resumoLigacaoEconomia != null){
	
				if (resumoLigacaoEconomia[0] != null && ((Integer) resumoLigacaoEconomia[0]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesCadastradas((Integer) resumoLigacaoEconomia[0]);
				} else {
					orcamento.setAguaTotalLigacoesCadastradas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[1] != null && ((Integer) resumoLigacaoEconomia[1]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesCadastradas((Integer) resumoLigacaoEconomia[1]);
				} else {
					orcamento.setEsgotoTotalLigacoesCadastradas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[2] != null && ((Integer) resumoLigacaoEconomia[2]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesCadastradasConvencional((Integer) resumoLigacaoEconomia[2]);
				} else {
					orcamento.setEsgotoTotalLigacoesCadastradasConvencional(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[3] != null && ((Integer) resumoLigacaoEconomia[3]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesAtivas((Integer) resumoLigacaoEconomia[3]);
				} else {
					orcamento.setAguaTotalLigacoesAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[4] != null && ((Integer) resumoLigacaoEconomia[4]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesCadastradasCondominial((Integer) resumoLigacaoEconomia[4]);
				} else {
					orcamento.setEsgotoTotalLigacoesCadastradasCondominial(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[5] != null && ((Integer) resumoLigacaoEconomia[5]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesMedidas((Integer) resumoLigacaoEconomia[5]);
				} else {
					orcamento.setAguaTotalLigacoesMedidas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[6] != null && ((Integer) resumoLigacaoEconomia[6]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesAtivasConvencional((Integer) resumoLigacaoEconomia[6]);
				} else {
					orcamento.setEsgotoTotalLigacoesAtivasConvencional(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[7] != null && ((Integer) resumoLigacaoEconomia[7]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesComHidrometro((Integer) resumoLigacaoEconomia[7]);
				} else {
					orcamento.setAguaTotalLigacoesComHidrometro(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[8] != null && ((Integer) resumoLigacaoEconomia[8]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesAtivasCondominial((Integer) resumoLigacaoEconomia[8]);
				} else {
					orcamento.setEsgotoTotalLigacoesAtivasCondominial(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[9] != null && ((Integer) resumoLigacaoEconomia[9]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesResidencialCadastradas((Integer) resumoLigacaoEconomia[9]);
				} else {
					orcamento.setAguaTotalLigacoesResidencialCadastradas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[10] != null && ((Integer) resumoLigacaoEconomia[10]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesResidencialCadastradas((Integer) resumoLigacaoEconomia[10]);
				} else {
					orcamento.setEsgotoTotalLigacoesResidencialCadastradas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[11] != null && ((Integer) resumoLigacaoEconomia[11]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesDesligadas((Integer) resumoLigacaoEconomia[11]);
				} else {
					orcamento.setAguaTotalLigacoesDesligadas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[12] != null && ((Integer) resumoLigacaoEconomia[12]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasCadastradas((Integer) resumoLigacaoEconomia[12]);
				} else {
					orcamento.setAguaTotalEconomiasCadastradas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[13] != null && ((Integer) resumoLigacaoEconomia[13]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasCadastradasConvencional((Integer) resumoLigacaoEconomia[13]);
				} else {
					orcamento.setEsgotoTotalEconomiasCadastradasConvencional(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[14] != null && ((Integer) resumoLigacaoEconomia[14]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasAtivas((Integer) resumoLigacaoEconomia[14]);
				} else {
					orcamento.setAguaTotalEconomiasAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[15] != null && ((Integer) resumoLigacaoEconomia[15]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasCadastradasCondominial((Integer) resumoLigacaoEconomia[15]);
				} else {
					orcamento.setEsgotoTotalEconomiasCadastradasCondominial(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[16] != null && ((Integer) resumoLigacaoEconomia[16]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasAtivasMedidas((Integer) resumoLigacaoEconomia[16]);
				} else {
					orcamento.setAguaTotalEconomiasAtivasMedidas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[17] != null && ((Integer) resumoLigacaoEconomia[17]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasAtivasConvencional((Integer) resumoLigacaoEconomia[17]);
				} else {
					orcamento.setEsgotoTotalEconomiasAtivasConvencional(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[18] != null && ((Integer) resumoLigacaoEconomia[18]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasResidencialCadastradas((Integer) resumoLigacaoEconomia[18]);
				} else {
					orcamento.setAguaTotalEconomiasResidencialCadastradas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[19] != null && ((Integer) resumoLigacaoEconomia[19]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasAtivasCondominial((Integer) resumoLigacaoEconomia[19]);
				} else {
					orcamento.setEsgotoTotalEconomiasAtivasCondominial(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[20] != null && ((Integer) resumoLigacaoEconomia[20]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasResidencialAtivasMicromedidas((Integer) resumoLigacaoEconomia[20]);
				} else {
					orcamento.setAguaTotalEconomiasResidencialAtivasMicromedidas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[21] != null && ((Integer) resumoLigacaoEconomia[21]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasResidencialCadastradas((Integer) resumoLigacaoEconomia[21]);
				} else {
					orcamento.setEsgotoTotalEconomiasResidencialCadastradas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[22] != null && ((Integer) resumoLigacaoEconomia[22]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasResidencialAtivas((Integer) resumoLigacaoEconomia[22]);
				} else {
					orcamento.setAguaTotalEconomiasResidencialAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[23] != null && ((Integer) resumoLigacaoEconomia[23]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasResidencialAtivas((Integer) resumoLigacaoEconomia[23]);
				} else {
					orcamento.setEsgotoTotalEconomiasResidencialAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[24] != null && ((Integer) resumoLigacaoEconomia[24]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasComercialAtivas((Integer) resumoLigacaoEconomia[24]);
				} else {
					orcamento.setAguaTotalEconomiasComercialAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[25] != null && ((Integer) resumoLigacaoEconomia[25]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasComercialAtivas((Integer) resumoLigacaoEconomia[25]);
				} else {
					orcamento.setEsgotoTotalEconomiasComercialAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[26] != null && ((Integer) resumoLigacaoEconomia[26]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasIndustrialAtivas((Integer) resumoLigacaoEconomia[26]);
				} else {
					orcamento.setAguaTotalEconomiasIndustrialAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[27] != null && ((Integer) resumoLigacaoEconomia[27]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasIndustrialAtivas((Integer) resumoLigacaoEconomia[27]);
				} else {
					orcamento.setEsgotoTotalEconomiasIndustrialAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[28] != null && ((Integer) resumoLigacaoEconomia[28]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasPublicoAtivas((Integer) resumoLigacaoEconomia[28]);
				} else {
					orcamento.setAguaTotalEconomiasPublicoAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[29] != null && ((Integer) resumoLigacaoEconomia[29]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasPublicoAtivas((Integer) resumoLigacaoEconomia[29]);
				} else {
					orcamento.setEsgotoTotalEconomiasPublicoAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[30] != null && ((Integer) resumoLigacaoEconomia[30]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasRuralAtivas((Integer) resumoLigacaoEconomia[30]);
				} else {
					orcamento.setAguaTotalEconomiasRuralAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[31] != null && ((Integer) resumoLigacaoEconomia[31]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesSuprimidas((Integer) resumoLigacaoEconomia[31]);
				} else {
					orcamento.setAguaTotalLigacoesSuprimidas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[32] != null && ((Integer) resumoLigacaoEconomia[32]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasRuralAtivas((Integer) resumoLigacaoEconomia[32]);
				} else {
					orcamento.setEsgotoTotalEconomiasRuralAtivas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[33] != null && ((Integer) resumoLigacaoEconomia[33]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesNovas((Integer) resumoLigacaoEconomia[33]);
				} else {
					orcamento.setAguaTotalLigacoesNovas(new Integer(0));
				}
				
				if (resumoLigacaoEconomia[34] != null && ((Integer) resumoLigacaoEconomia[34]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesNovas((Integer) resumoLigacaoEconomia[34]);
				} else {
					orcamento.setEsgotoTotalLigacoesNovas(new Integer(0));
				}
                
/*                Object[] totalLigacoes = 
                    this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPTotalLigacoesResumoLigacaoEconomia(
                        filtrarRelatorioOrcamentoSINPHelper);                
				
				if(totalLigacoes != null){
					
					int quantidadeLigacoesAgua = 0;
					int quantidadeLigacoesEsgoto = 0;
					
					if(totalLigacoes[0] != null){
						quantidadeLigacoesAgua = (Integer)totalLigacoes[0];
					}

					if(totalLigacoes[1] != null){
						quantidadeLigacoesEsgoto = (Integer)totalLigacoes[1];
					}
					
					Integer valorAguaNova = 
						orcamento.getAguaTotalLigacoesCadastradas().intValue() - quantidadeLigacoesAgua;
					
					Integer valorEsgotoNova =
						orcamento.getEsgotoTotalLigacoesCadastradas().intValue() - quantidadeLigacoesEsgoto;
					
					orcamento.setAguaTotalLigacoesNovas(valorAguaNova);
					orcamento.setEsgotoTotalLigacoesNovas(valorEsgotoNova);
				}*/
				
			}
	
			Object[] financeiroResumoFaturamento = 
				this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPFinanceiroResumoFaturamento(
					filtrarRelatorioOrcamentoSINPHelper);
			
			if(financeiroResumoFaturamento != null){
				
				if (financeiroResumoFaturamento[0] != null && ((BigDecimal) financeiroResumoFaturamento[0]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setEsgotoTotalFaturadoResidencial((BigDecimal) financeiroResumoFaturamento[0]);
				} else {
					orcamento.setEsgotoTotalFaturadoResidencial(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[1] != null && ((BigDecimal) financeiroResumoFaturamento[1]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setEsgotoTotalFaturadoComercial((BigDecimal) financeiroResumoFaturamento[1]);
				} else {
					orcamento.setEsgotoTotalFaturadoComercial(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[2] != null && ((BigDecimal) financeiroResumoFaturamento[2]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setEsgotoTotalFaturadoIndustrial((BigDecimal) financeiroResumoFaturamento[2]);
				} else {
					orcamento.setEsgotoTotalFaturadoIndustrial(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[3] != null && ((BigDecimal) financeiroResumoFaturamento[3]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setEsgotoTotalFaturadoPublico((BigDecimal) financeiroResumoFaturamento[3]);
				} else {
					orcamento.setEsgotoTotalFaturadoPublico(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[4] != null && ((BigDecimal) financeiroResumoFaturamento[4]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setEsgotoTotalFaturadoDireto((BigDecimal) financeiroResumoFaturamento[4]);
				} else {
					orcamento.setEsgotoTotalFaturadoDireto(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[5] != null && ((BigDecimal) financeiroResumoFaturamento[5]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setAguaTotalFaturadoResidencial((BigDecimal) financeiroResumoFaturamento[5]);
				} else {
					orcamento.setAguaTotalFaturadoResidencial(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[6] != null && ((BigDecimal) financeiroResumoFaturamento[6]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setAguaTotalFaturadoComercial((BigDecimal) financeiroResumoFaturamento[6]);
				} else {
					orcamento.setAguaTotalFaturadoComercial(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[7] != null && ((BigDecimal) financeiroResumoFaturamento[7]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setAguaTotalFaturadoIndustrial((BigDecimal) financeiroResumoFaturamento[7]);
				} else {
					orcamento.setAguaTotalFaturadoIndustrial(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[8] != null && ((BigDecimal) financeiroResumoFaturamento[8]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setAguaTotalFaturadoPublico((BigDecimal) financeiroResumoFaturamento[8]);
				} else {
					orcamento.setAguaTotalFaturadoPublico(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[9] != null && ((BigDecimal) financeiroResumoFaturamento[9]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setAguaTotalFaturadoDireto((BigDecimal) financeiroResumoFaturamento[9]);
				} else {
					orcamento.setAguaTotalFaturadoDireto(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[10] != null && ((BigDecimal) financeiroResumoFaturamento[10]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setAguaTotalFaturadoIndireto((BigDecimal) financeiroResumoFaturamento[10]);
				} else {
					orcamento.setAguaTotalFaturadoIndireto(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[11] != null && ((BigDecimal) financeiroResumoFaturamento[11]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setReceitaOperacionalTotal((BigDecimal) financeiroResumoFaturamento[11]);
				} else {
					orcamento.setReceitaOperacionalTotal(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[12] != null && ((BigDecimal) financeiroResumoFaturamento[12]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setReceitaOperacionalDireta((BigDecimal) financeiroResumoFaturamento[12]);
				} else {
					orcamento.setReceitaOperacionalDireta(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[13] != null && ((BigDecimal) financeiroResumoFaturamento[13]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setReceitaOperacionalIndireta((BigDecimal) financeiroResumoFaturamento[13]);
				} else {
					orcamento.setReceitaOperacionalIndireta(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[14] != null && ((BigDecimal) financeiroResumoFaturamento[14]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setAguaTotalFaturamentoGeralDI((BigDecimal) financeiroResumoFaturamento[14]);
				} else {
					orcamento.setAguaTotalFaturamentoGeralDI(new BigDecimal(0));
				}
				
				if (financeiroResumoFaturamento[15] != null && ((BigDecimal) financeiroResumoFaturamento[15]).compareTo(BigDecimal.ZERO) != 0) {
					temDados = true;
					orcamento.setTotalFaturamentoLiquido((BigDecimal) financeiroResumoFaturamento[15]);
				} else {
					orcamento.setTotalFaturamentoLiquido(new BigDecimal(0));
				}
				
			}
			
			Object[] resumoFaturamento = 
				this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPResumoFaturamento(
					filtrarRelatorioOrcamentoSINPHelper);
	
			if(resumoFaturamento != null){
				
				if (resumoFaturamento[26] != null && ((Integer) resumoFaturamento[26]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesFaturadasMedidas((Integer) resumoFaturamento[26]);
				} else {
					orcamento.setAguaTotalLigacoesFaturadasMedidas(new Integer(0));
				}
				
				if (resumoFaturamento[28] != null && ((Integer) resumoFaturamento[28]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalLigacoesFaturadasNaoMedidas((Integer) resumoFaturamento[28]);
				} else {
					orcamento.setAguaTotalLigacoesFaturadasNaoMedidas(new Integer(0));
				}
				
				if (resumoFaturamento[30] != null && ((Integer) resumoFaturamento[30]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasFaturadasMedidas((Integer) resumoFaturamento[30]);
				} else {
					orcamento.setAguaTotalEconomiasFaturadasMedidas(new Integer(0));
				}
				
				if (resumoFaturamento[32] != null && ((Integer) resumoFaturamento[32]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalEconomiasFaturadasNaoMedidas((Integer) resumoFaturamento[32]);
				} else {
					orcamento.setAguaTotalEconomiasFaturadasNaoMedidas(new Integer(0));
				}
				
				if (Util.compararAnoMesReferencia(filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferencia(), new Integer("201012"), ">")){
					
					Object[] totalVolumeFaturadoAgua = 
							this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPTotalVolumeFaturadoAgua(
								filtrarRelatorioOrcamentoSINPHelper);

					if (totalVolumeFaturadoAgua != null) {
						
						if (totalVolumeFaturadoAgua[0] != null && ((Integer) totalVolumeFaturadoAgua[0]).intValue() != 0) {
							temDados = true;
							orcamento.setAguaTotalVolumeFaturadoMedido((Integer) totalVolumeFaturadoAgua[0]);
						} else {
							orcamento.setAguaTotalVolumeFaturadoMedido(new Integer(0));
						}
						
						if (totalVolumeFaturadoAgua[1] != null && ((Integer) totalVolumeFaturadoAgua[1]).intValue() != 0) {
							temDados = true;
							orcamento.setAguaTotalVolumeFaturadoEstimado((Integer) totalVolumeFaturadoAgua[1]);
						} else {
							orcamento.setAguaTotalVolumeFaturadoEstimado(new Integer(0));
						}
						
						if (totalVolumeFaturadoAgua[2] != null && ((Integer) totalVolumeFaturadoAgua[2]).intValue() != 0) {
							temDados = true;
							orcamento.setAguaTotalVolumeFaturadoResidencial((Integer) totalVolumeFaturadoAgua[2]);
						} else {
							orcamento.setAguaTotalVolumeFaturadoResidencial(new Integer(0));
						}
						
						if (totalVolumeFaturadoAgua[3] != null && ((Integer) totalVolumeFaturadoAgua[3]).intValue() != 0) {
							temDados = true;
							orcamento.setAguaTotalVolumeFaturadoComercial((Integer) totalVolumeFaturadoAgua[3]);
						} else {
							orcamento.setAguaTotalVolumeFaturadoComercial(new Integer(0));
						}
						
						if (totalVolumeFaturadoAgua[4] != null && ((Integer) totalVolumeFaturadoAgua[4]).intValue() != 0) {
							temDados = true;
							orcamento.setAguaTotalVolumeFaturadoIndustrial((Integer) totalVolumeFaturadoAgua[4]);
						} else {
							orcamento.setAguaTotalVolumeFaturadoIndustrial(new Integer(0));
						}
						
						if (totalVolumeFaturadoAgua[5] != null && ((Integer) totalVolumeFaturadoAgua[5]).intValue() != 0) {
							temDados = true;
							orcamento.setAguaTotalVolumeFaturadoPublico((Integer) totalVolumeFaturadoAgua[5]);
						} else {
							orcamento.setAguaTotalVolumeFaturadoPublico(new Integer(0));
						}
						
						if (totalVolumeFaturadoAgua[6] != null && ((Integer) totalVolumeFaturadoAgua[6]).intValue() != 0) {
							temDados = true;
							orcamento.setAguaTotalVolumeFaturadoRural((Integer) totalVolumeFaturadoAgua[6]);
						} else {
							orcamento.setAguaTotalVolumeFaturadoRural(new Integer(0));
						}
						
						if (totalVolumeFaturadoAgua[7] != null && ((Integer) totalVolumeFaturadoAgua[7]).intValue() != 0) {
							temDados = true;
							orcamento.setAguaTotalVolumeFaturadoGeral((Integer) totalVolumeFaturadoAgua[7]);
						} else {
							orcamento.setAguaTotalVolumeFaturadoGeral(new Integer(0));
						}
						
					}
					
					Object[] totalVolumeFaturadoEsgoto = 
							this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPTotalVolumeFaturadoEsgoto(
								filtrarRelatorioOrcamentoSINPHelper);
					
					if (totalVolumeFaturadoEsgoto != null) {
						
						if (totalVolumeFaturadoEsgoto[0] != null && ((Integer) totalVolumeFaturadoEsgoto[0]).intValue() != 0) {
							temDados = true;
							orcamento.setEsgotoTotalVolumeFaturadoResidencial((Integer) totalVolumeFaturadoEsgoto[0]);
						} else {
							orcamento.setEsgotoTotalVolumeFaturadoResidencial(new Integer(0));
						}
						
						if (totalVolumeFaturadoEsgoto[1] != null && ((Integer) totalVolumeFaturadoEsgoto[1]).intValue() != 0) {
							temDados = true;
							orcamento.setEsgotoTotalVolumeFaturadoComercial((Integer) totalVolumeFaturadoEsgoto[1]);
						} else {
							orcamento.setEsgotoTotalVolumeFaturadoComercial(new Integer(0));
						}
						
						if (totalVolumeFaturadoEsgoto[2] != null && ((Integer) totalVolumeFaturadoEsgoto[2]).intValue() != 0) {
							temDados = true;
							orcamento.setEsgotoTotalVolumeFaturadoIndustrial((Integer) totalVolumeFaturadoEsgoto[2]);
						} else {
							orcamento.setEsgotoTotalVolumeFaturadoIndustrial(new Integer(0));
						}
						
						if (totalVolumeFaturadoEsgoto[3] != null && ((Integer) totalVolumeFaturadoEsgoto[3]).intValue() != 0) {
							temDados = true;
							orcamento.setEsgotoTotalVolumeFaturadoPublico((Integer) totalVolumeFaturadoEsgoto[3]);
						} else {
							orcamento.setEsgotoTotalVolumeFaturadoPublico(new Integer(0));
						}
						
						if (totalVolumeFaturadoEsgoto[4] != null && ((Integer) totalVolumeFaturadoEsgoto[4]).intValue() != 0) {
							temDados = true;
							orcamento.setEsgotoTotalVolumeFaturadoGeral((Integer) totalVolumeFaturadoEsgoto[4]);
						} else {
							orcamento.setEsgotoTotalVolumeFaturadoGeral(new Integer(0));
						}
						
					}
					
					
				} else {
					
					if (resumoFaturamento[0] != null && ((Integer) resumoFaturamento[0]).intValue() != 0) {
						temDados = true;
						orcamento.setAguaTotalVolumeFaturadoMedido((Integer) resumoFaturamento[0]);
					} else {
						orcamento.setAguaTotalVolumeFaturadoMedido(new Integer(0));
					}
					
					if (resumoFaturamento[1] != null && ((Integer) resumoFaturamento[1]).intValue() != 0) {
						temDados = true;
						orcamento.setEsgotoTotalVolumeFaturadoResidencial((Integer) resumoFaturamento[1]);
					} else {
						orcamento.setEsgotoTotalVolumeFaturadoResidencial(new Integer(0));
					}
					
					if (resumoFaturamento[2] != null && ((Integer) resumoFaturamento[2]).intValue() != 0) {
						temDados = true;
						orcamento.setEsgotoTotalVolumeFaturadoComercial((Integer) resumoFaturamento[2]);
					} else {
						orcamento.setEsgotoTotalVolumeFaturadoComercial(new Integer(0));
					}
					
					if (resumoFaturamento[3] != null && ((Integer) resumoFaturamento[3]).intValue() != 0) {
						temDados = true;
						orcamento.setAguaTotalVolumeFaturadoEstimado((Integer) resumoFaturamento[3]);
					} else {
						orcamento.setAguaTotalVolumeFaturadoEstimado(new Integer(0));
					}
					
					if (resumoFaturamento[4] != null && ((Integer) resumoFaturamento[4]).intValue() != 0) {
						temDados = true;
						orcamento.setEsgotoTotalVolumeFaturadoIndustrial((Integer) resumoFaturamento[4]);
					} else {
						orcamento.setEsgotoTotalVolumeFaturadoIndustrial(new Integer(0));
					}
					
					if (resumoFaturamento[5] != null && ((Integer) resumoFaturamento[5]).intValue() != 0) {
						temDados = true;
						orcamento.setEsgotoTotalVolumeFaturadoPublico((Integer) resumoFaturamento[5]);
					} else {
						orcamento.setEsgotoTotalVolumeFaturadoPublico(new Integer(0));
					}
					
					if (resumoFaturamento[6] != null && ((Integer) resumoFaturamento[6]).intValue() != 0) {
						temDados = true;
						orcamento.setAguaTotalVolumeFaturadoResidencial((Integer) resumoFaturamento[6]);
					} else {
						orcamento.setAguaTotalVolumeFaturadoResidencial(new Integer(0));
					}
					
					if (resumoFaturamento[7] != null && ((Integer) resumoFaturamento[7]).intValue() != 0) {
						temDados = true;
						orcamento.setEsgotoTotalVolumeFaturadoGeral((Integer) resumoFaturamento[7]);
					} else {
						orcamento.setEsgotoTotalVolumeFaturadoGeral(new Integer(0));
					}
					
					if (resumoFaturamento[8] != null && ((Integer) resumoFaturamento[8]).intValue() != 0) {
						temDados = true;
						orcamento.setAguaTotalVolumeFaturadoComercial((Integer) resumoFaturamento[8]);
					} else {
						orcamento.setAguaTotalVolumeFaturadoComercial(new Integer(0));
					}
					
					if (resumoFaturamento[9] != null && ((Integer) resumoFaturamento[9]).intValue() != 0) {
						temDados = true;
						orcamento.setAguaTotalVolumeFaturadoIndustrial((Integer) resumoFaturamento[9]);
					} else {
						orcamento.setAguaTotalVolumeFaturadoIndustrial(new Integer(0));
					}
					
					if (resumoFaturamento[10] != null && ((Integer) resumoFaturamento[10]).intValue() != 0) {
						temDados = true;
						orcamento.setAguaTotalVolumeFaturadoPublico((Integer) resumoFaturamento[10]);
					} else {
						orcamento.setAguaTotalVolumeFaturadoPublico(new Integer(0));
					}
					
					if (resumoFaturamento[11] != null && ((Integer) resumoFaturamento[11]).intValue() != 0) {
						temDados = true;
						orcamento.setAguaTotalVolumeFaturadoRural((Integer) resumoFaturamento[11]);
					} else {
						orcamento.setAguaTotalVolumeFaturadoRural(new Integer(0));
					}
					
					if (resumoFaturamento[12] != null && ((Integer) resumoFaturamento[12]).intValue() != 0) {
						temDados = true;
						orcamento.setAguaTotalVolumeFaturadoGeral((Integer) resumoFaturamento[12]);
					} else {
						orcamento.setAguaTotalVolumeFaturadoGeral(new Integer(0));
					}
					
				}
	
			}
			
			Object[] resumoColetaEsgoto = 
				this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPResumoColetaEsgoto(
						filtrarRelatorioOrcamentoSINPHelper);
				
			if(resumoColetaEsgoto != null){
				
				if (resumoColetaEsgoto[0] != null && ((Integer) resumoColetaEsgoto[0]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesFaturadasMedidas((Integer) resumoColetaEsgoto[0]);
				} else {
					orcamento.setEsgotoTotalLigacoesFaturadasMedidas(new Integer(0));
				}
				
				if (resumoColetaEsgoto[1] != null && ((Integer) resumoColetaEsgoto[1]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalLigacoesFaturadasNaoMedidas((Integer) resumoColetaEsgoto[1]);
				} else {
					orcamento.setEsgotoTotalLigacoesFaturadasNaoMedidas(new Integer(0));
				}
				
				if (resumoColetaEsgoto[2] != null && ((Integer) resumoColetaEsgoto[2]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasFaturadasMedidas((Integer) resumoColetaEsgoto[2]);
				} else {
					orcamento.setEsgotoTotalEconomiasFaturadasMedidas(new Integer(0));
				}
				
				if (resumoColetaEsgoto[3] != null && ((Integer) resumoColetaEsgoto[3]).intValue() != 0) {
					temDados = true;
					orcamento.setEsgotoTotalEconomiasFaturadasNaoMedidas((Integer) resumoColetaEsgoto[3]);
				} else {
					orcamento.setEsgotoTotalEconomiasFaturadasNaoMedidas(new Integer(0));
				}
					
			}				
			
			// Consumo de Agua
			Object[] totalConsumoAgua = 
				this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPTotalComumoResumoConsumoAgua(
					filtrarRelatorioOrcamentoSINPHelper);
			
			if(totalConsumoAgua != null){
				
				if (totalConsumoAgua[0] != null && ((Integer) totalConsumoAgua[0]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalVolumeFaturadoMicroMedido((Integer) totalConsumoAgua[0]);
				} else {
					orcamento.setAguaTotalVolumeFaturadoMicroMedido(new Integer(0));
				}
				
				if (totalConsumoAgua[1] != null && ((Integer) totalConsumoAgua[1]).intValue() != 0) {
					temDados = true;
					orcamento.setAguaTotalVolumeFaturadoEconomiasResidenciasAtivas((Integer) totalConsumoAgua[1]);
				} else {
					orcamento.setAguaTotalVolumeFaturadoEconomiasResidenciasAtivas(new Integer(0));
				}
				
			}
			
			// Arrecadação Atual
			BigDecimal totalArrecadacao = 
				this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPTotalArrecadacaoResumoArrecadacao(
					filtrarRelatorioOrcamentoSINPHelper,false);
			
			if(totalArrecadacao != null && totalArrecadacao.compareTo(BigDecimal.ZERO) != 0){
				temDados = true;
				orcamento.setTotalArrecadacaoMesAtual(totalArrecadacao);
			} else {
				orcamento.setTotalArrecadacaoMesAtual(new BigDecimal(0));
			}

			// Arrecadação Anterior
			BigDecimal totalArrecadacaoAnterior = 
				this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPTotalArrecadacaoResumoArrecadacao(
					filtrarRelatorioOrcamentoSINPHelper,true);
			
			if(totalArrecadacaoAnterior != null && totalArrecadacaoAnterior.compareTo(BigDecimal.ZERO) != 0){
				temDados = true;
				orcamento.setTotalArrecadacaoMesAnterior(totalArrecadacaoAnterior);
			} else {
				orcamento.setTotalArrecadacaoMesAnterior(new BigDecimal(0));
			}
			
			//Saldo Contas a Receber
			Object[] totalSaldoContasReceberParametros = 
				this.repositorioGerencial.pesquisarRelatorioOrcamentoSINPTotalContasResumoPendencia(
					filtrarRelatorioOrcamentoSINPHelper);
			
			if(totalSaldoContasReceberParametros != null){
				
				if(totalSaldoContasReceberParametros[0] != null && ((BigDecimal) totalSaldoContasReceberParametros[0]).compareTo(BigDecimal.ZERO) != 0){
					temDados = true;
					orcamento.setSaldoContasReceber((BigDecimal)totalSaldoContasReceberParametros[0]);
				} else {
					orcamento.setSaldoContasReceber(new BigDecimal(0));
				}
				
				if(totalSaldoContasReceberParametros[1] != null && ((BigDecimal) totalSaldoContasReceberParametros[1]).compareTo(BigDecimal.ZERO) != 0){
					temDados = true;
					orcamento.setSaldoContasReceberParticular((BigDecimal)totalSaldoContasReceberParametros[1]);
			    } else {
			    	orcamento.setSaldoContasReceberParticular(new BigDecimal(0));
			    }
				
				if(totalSaldoContasReceberParametros[2] != null && ((BigDecimal) totalSaldoContasReceberParametros[2]).compareTo(BigDecimal.ZERO) != 0){
					temDados = true;
					orcamento.setSaldoContasReceberPublico((BigDecimal)totalSaldoContasReceberParametros[2]);
				} else {
					orcamento.setSaldoContasReceberPublico(new BigDecimal(0));
				}
				
			}
			
			if (!temDados) {
				orcamento = null;
			}
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		
		
		return orcamento;
	}
	
	/**
	 * 
	 * [UC0733] Gerar Quadro de metas Acumulado
	 * 
	 * @author Bruno Barros
	 * @param filtrarRelatorioQuadroMetasAcumuladoHelper
	 * @return
	 */
	public Collection<QuadroMetasAcumuladoHelper> pesquisarRelatorioQuadroMetasAcumulado(
			FiltrarRelatorioQuadroMetasAcumuladoHelper filtrarRelatorioQuadroMetasAcumuladoHelper) throws ControladorException{
		
		Collection<QuadroMetasAcumuladoHelper> retorno = new ArrayList<QuadroMetasAcumuladoHelper>();
		List dadosBrutos = null;
				
		try {
			dadosBrutos = repositorioGerencial
					.pesquisarRelatorioQuadroMetasAcumulado( filtrarRelatorioQuadroMetasAcumuladoHelper );			
			
			if ( dadosBrutos != null && dadosBrutos.size() > 0 ){				
				Iterator iteDadosBrutos = dadosBrutos.iterator();
				
				while ( iteDadosBrutos.hasNext() ){
					// Pegamos linha por linha do retorno
					Object[] linha = ( Object[] )  iteDadosBrutos.next();
					
					// Montamos Helper por Helper para a coleção de retorno
					QuadroMetasAcumuladoHelper quadroMetasAcumuladoHelper = new QuadroMetasAcumuladoHelper();

					// 	Ligacoes Cadastradas
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradasSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradasSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradasSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradasSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradasSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradasSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradasSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradasSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCadastradas(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS ] );
					
					
					
					
					
					
					// Ligacoes Cortadas
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadasSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadasSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadasSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadasSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadasSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadasSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadasSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadasSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesCortadas(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS ] );
					
					
					
					
					
					
					// Ligacoes Suprimidas				
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidasSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidasSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidasSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidasSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidasSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidasSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidasSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidasSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesSuprimidas(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS ] );
					
					
					
					
					
					// Ligacoes Ativas
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivas(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS ] );					

					
					
					
					
					// Ligacoes Ativas com débitos a mais de 3 meses
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3mSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3mSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3mSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3mSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3mSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3mSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3mSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3mSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesAtivasDebitos3m(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M ] );
					
					
					
					
					
					// Ligacoes Consumo Medido
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedidoSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedidoSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedidoSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedidoSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedidoSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedidoSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedidoSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedidoSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedido(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO ] -
					    ( quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoMedidoSubcategoria101() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoMedidoSubcategoria102() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoMedidoSubcategoria115() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoMedidoSubcategoria116() ) ) ;					
					
					// Ligacoes Consumo 5m3
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3Subcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3Subcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3Subcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3Subcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3Subcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3Subcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3Subcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3Subcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_115 ] );
					
					// Removemos do total os consumos dessas categorias, pois grandes consumidores são apenas
					// para categorias 103, 200, 300, 400
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumo5m3(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3 ] -
					    ( quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumo5m3Subcategoria101() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumo5m3Subcategoria102() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumo5m3Subcategoria115() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumo5m3Subcategoria116() ) );
					
					
					
					
					// Ligacoes Consumo Não Medido
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedidoSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedidoSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedidoSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedidoSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedidoSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedidoSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedidoSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedidoSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoNaoMedido(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO ] -
					    ( quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria101() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria102() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria115() +
					      quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria116() ) ) ;							
					
					
					
					// Ligacoes Consumo Media
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMediaSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMediaSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMediaSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMediaSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMediaSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMediaSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMediaSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMediaSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeLigacoesConsumoMedia(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA ] - 
						( quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoMediaSubcategoria101() + 
						  quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoMediaSubcategoria102() +
						  quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoMediaSubcategoria116() +
						  quadroMetasAcumuladoHelper.getQuantidadeLigacoesConsumoMediaSubcategoria115() ) );					

					// Quantidade de Economias
					quadroMetasAcumuladoHelper.setQuantidadeEconomiasSubcategoria101(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_101 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeEconomiasSubcategoria102(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_102 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeEconomiasSubcategoria103(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_103 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeEconomiasSubcategoria200(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_200 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeEconomiasSubcategoria300(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_300 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeEconomiasSubcategoria400(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_400 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeEconomiasSubcategoria116(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_116 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeEconomiasSubcategoria115(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_115 ] );
					
					quadroMetasAcumuladoHelper.setQuantidadeEconomias(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS ] );
					
					// Verificamos quais os grupos que foram formados
					//Gerencia regional
					if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_GERENCIA_REGIONAL )  ||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_UNIDADE_NEGOCIO ) 	||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_LOCALIDADE ) 		||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_GERENCIA_REGIONAL )  ||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO )  ||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_LOCALIDADE ) ){
						
						FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();
						filtro.adicionarParametro( new ParametroSimples( FiltroGerenciaRegional.ID, (Integer) linha[ RepositorioGerencialHBM.INDEX_ID_GERENCIA ] ) );
						Collection<GerenciaRegional> colGerenciaRegional =  this.getControladorUtil().pesquisar( filtro, GerenciaRegional.class.getName() );
						
						if ( colGerenciaRegional != null && colGerenciaRegional.size() > 0 ){
							GerenciaRegional gerenciaRegional = ( GerenciaRegional ) colGerenciaRegional.toArray()[0];
							quadroMetasAcumuladoHelper.setNomeGerenciaRegional( gerenciaRegional.getId() + " - " + gerenciaRegional.getNome() );
						}						
					}

					if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_UNIDADE_NEGOCIO ) 	||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_LOCALIDADE )		||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO )  ||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_LOCALIDADE ) ){		
						FiltroUnidadeNegocio filtro = new FiltroUnidadeNegocio();
						filtro.adicionarParametro( new ParametroSimples( FiltroUnidadeNegocio.ID, (Integer) linha[ RepositorioGerencialHBM.INDEX_ID_UNIDADE_NEGOCIO ] ) );
						Collection<UnidadeNegocio> colUnidadeNegocio =  this.getControladorUtil().pesquisar( filtro, UnidadeNegocio.class.getName() );
						
						if ( colUnidadeNegocio != null && colUnidadeNegocio.size() > 0 ){
							UnidadeNegocio unidadeNegocio = ( UnidadeNegocio ) colUnidadeNegocio.toArray()[0];
							quadroMetasAcumuladoHelper.setNomeUnidadeNegocio( unidadeNegocio.getId() + " - " + unidadeNegocio.getNome() );
						}	
					}
					
					if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_LOCALIDADE )		||
						 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_LOCALIDADE ) ){		
						FiltroLocalidade filtro = new FiltroLocalidade();
						filtro.adicionarParametro( new ParametroSimples( FiltroLocalidade.ID, (Integer) linha[ RepositorioGerencialHBM.INDEX_ID_LOCALIDADE ] ) );
						Collection<Localidade> colLocalidade =  this.getControladorUtil().pesquisar( filtro, Localidade.class.getName() );
						
						if ( colLocalidade != null && colLocalidade.size() > 0 ){
							Localidade localidade = ( Localidade ) colLocalidade.toArray()[0];
							quadroMetasAcumuladoHelper.setNomeLocalidade( localidade.getId() + " - " + localidade.getDescricao() );
							quadroMetasAcumuladoHelper.setNomeCentroCusto( localidade.getCodigoCentroCusto() );
						}						
					}					
					retorno.add( quadroMetasAcumuladoHelper );					
				}
			} else {
				throw new ControladorException( "atencao.naocadastrado", null, "Quadro de Metas Acumulado" );
			}
			

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;	
	}
	
	/**
	 * Pesquisa todas as tabelas de resumo para o Orcamento sem a tabela de resumo pendencia e arrecadação
	 *
	 * [UC0750] - Gerar Arquivo Texto para Orçamento e SINP
	 *
	 * @author Sávio Luiz
	 * @date 12/02/2008
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void existeDadosUnResumoParcialParaOrcamentoSINP(int anoMesReferencia) 
		throws ControladorException{
		
		try {
			boolean existeDados = this.repositorioGerencial.existeDadosUnResumoParcialParaOrcamentoSINP(
					anoMesReferencia);

			if(!existeDados){
				throw new ControladorException("atencao.sem_registros_gerencias");
			}
 
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Gerar Arquivo Texto para o Planejamento
	 *
	 * [UC1262] - Gerar Arquivo Texto para o Planejamento
	 *
	 * @author Rafael Pinto
	 * @date 14/12/2011
	 * 
	 * @param Ano/Mes
	 * @return StringBuilder
	 * 
	 * @throws ErroRepositorioException
	 */
	public File gerarArquivoTextoPlanejamentoSinp(int anoMesReferencia) 
		throws ControladorException {
		
		StringBuilder arquivoTx = null;
		String nomeZip = "ARQUIVO_ORCAMENTO_SINP_"+anoMesReferencia;
		
		File arquivoZip = null;
		File arquivoTexto = null;

		try {
			
			Collection<Object[]> colecaoResumoLigacaoEconomia = 
				this.repositorioGerencial.pesquisarArquivoPlanejamentoResumoLigacaoEconomia(anoMesReferencia);
					
			if(!Util.isVazioOrNulo(colecaoResumoLigacaoEconomia)){
				
				if(arquivoTx == null){
					arquivoTx = new StringBuilder();
				}
				
				Iterator itera = colecaoResumoLigacaoEconomia.iterator();
				
				while (itera.hasNext()) {
					
					Object[] resumoLigacaoEconomia = (Object[]) itera.next();
					
					Integer idGerencia = (Integer) resumoLigacaoEconomia[0];
					Integer idUnidade = (Integer) resumoLigacaoEconomia[2];
					Integer idLocalidade = (Integer) resumoLigacaoEconomia[5];
					String codigoCentroCusto = (String) resumoLigacaoEconomia[4];
					
					//Centro de Custo
					arquivoTx.append(Util.completaString(codigoCentroCusto, 9));
					
					//Gerencial Regional
					arquivoTx.append(Util.completaString((String)resumoLigacaoEconomia[1], 25));

					//Unidade de Negocio
					arquivoTx.append(Util.completaString((String)resumoLigacaoEconomia[3], 50));

					//Localidade
					arquivoTx.append(Util.completaString((String)resumoLigacaoEconomia[6], 30));
					
					//Total de Economias Residenciais Ativas de Agua
					String totalEconomiasResidenciaisAtivasAgua = ((Integer)resumoLigacaoEconomia[7]).toString();
					arquivoTx.append(Util.completaString(totalEconomiasResidenciaisAtivasAgua, 13));
					
					//Total de Economias Residencias Cadastradas de Esgoto
					String totalEconomiasResidenciasCadastradasEsgoto = ((Integer)resumoLigacaoEconomia[8]).toString();
					arquivoTx.append(Util.completaString(totalEconomiasResidenciasCadastradasEsgoto, 13));

					//Total de Economias Residencias Rurais
					String totalEconomiasResidenciasRurais = ((Integer)resumoLigacaoEconomia[9]).toString();
					arquivoTx.append(Util.completaString(totalEconomiasResidenciasRurais, 13));
					
					Object[] resumoFaturamento = 
						this.repositorioGerencial.pesquisarArquivoPlanejamentoResumoFaturamento(
							anoMesReferencia,
							idGerencia,
							idUnidade,
							idLocalidade,
							codigoCentroCusto);
					
					 BigDecimal faturamentoAguaResidencial = BigDecimal.ZERO;
					 BigDecimal faturamentoAguaComercial = BigDecimal.ZERO;
					 BigDecimal faturamentoAguaIndustrial = BigDecimal.ZERO;
					 BigDecimal faturamentoAguaPublico = BigDecimal.ZERO;
					 BigDecimal aguaDireta = BigDecimal.ZERO;
					 BigDecimal aguaIndireta = BigDecimal.ZERO;
					 BigDecimal faturamentoEsgotoComercial = BigDecimal.ZERO;
					 BigDecimal faturamentoEsgotoIndustrial = BigDecimal.ZERO;
					 BigDecimal faturamentoEsgotoPublico = BigDecimal.ZERO;
					 BigDecimal esgotoDireto = BigDecimal.ZERO;
					 BigDecimal esgotoIndireto = BigDecimal.ZERO;
					 
					if(resumoFaturamento != null){
						
						faturamentoAguaResidencial = (BigDecimal) resumoFaturamento[0];
						faturamentoAguaComercial = (BigDecimal) resumoFaturamento[1];
						faturamentoAguaIndustrial = (BigDecimal)resumoFaturamento[2];
						faturamentoAguaPublico = (BigDecimal) resumoFaturamento[3];
						aguaDireta = (BigDecimal) resumoFaturamento[4];
						aguaIndireta = (BigDecimal) resumoFaturamento[5];
						faturamentoEsgotoComercial = (BigDecimal) resumoFaturamento[6];
						faturamentoEsgotoIndustrial = (BigDecimal) resumoFaturamento[7];
						faturamentoEsgotoPublico = (BigDecimal) resumoFaturamento[8];
						esgotoDireto = (BigDecimal) resumoFaturamento[9];
						esgotoIndireto = (BigDecimal) resumoFaturamento[10];
					}
					
					//Faturamento de Agua Residencial
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(faturamentoAguaResidencial), 13));
					
					//Faturamento de Agua Comercial
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(faturamentoAguaComercial), 13));
					
					//Faturamento de Agua Industrial
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(faturamentoAguaIndustrial), 13));
					
					//Faturamento de Agua Publico
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(faturamentoAguaPublico), 13));
					
					//Agua Direta
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(aguaDireta), 13));
					
					//Agua Indireta
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(aguaIndireta), 13));

					//Faturamento de Esgoto Comercial
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(faturamentoEsgotoComercial), 13));
					
					//Faturamento de Esgoto Industrial
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(faturamentoEsgotoIndustrial), 13));
					
					//Faturamento de Esgoto Publico
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(faturamentoEsgotoPublico), 13));
					
					//Esgoto Direto
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(esgotoDireto), 13));
					
					//Esgoto Indireto
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(esgotoIndireto), 13));
					
					BigDecimal resumoArrecadacao = 
							this.repositorioGerencial.pesquisarArquivoPlanejamentoResumoArrecadacao(
								anoMesReferencia,
								idGerencia,
								idUnidade,
								idLocalidade,
								codigoCentroCusto);
					
					BigDecimal valorArrecadacao = BigDecimal.ZERO;
					
					if(resumoArrecadacao != null){
						valorArrecadacao = resumoArrecadacao;
					}
					
					//Valor da Arrecadacao
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(valorArrecadacao), 13));
					
					BigDecimal resumoPendencia = 
							this.repositorioGerencial.pesquisarArquivoPlanejamentoResumoPendencia(
								anoMesReferencia,
								idGerencia,
								idUnidade,
								idLocalidade,
								codigoCentroCusto);
					
					BigDecimal valorContasAReceber = BigDecimal.ZERO;
					
					if(resumoPendencia != null){
						valorContasAReceber = resumoPendencia;
					}
					
					//Saldo de contas a Receber
					arquivoTx.append(Util.completaString(Util.formatarMoedaReal(valorContasAReceber), 13));
					
					//Quebra de linha
					arquivoTx.append(System.getProperty("line.separator"));

					
				}
				
				arquivoZip = new File(nomeZip + ".zip");
				arquivoTexto = new File(nomeZip + ".txt");

				
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(arquivoZip));
				BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(
						new FileOutputStream(arquivoTexto.getAbsolutePath())));
				
				bufferedWriter.write(arquivoTx.toString());
				bufferedWriter.flush();
				
				ZipUtil.adicionarArquivo(zos, arquivoTexto);

				arquivoTexto.delete();
				zos.close();
				bufferedWriter.close();
			}
		
        } catch (IOException e) {
            String mensagem = e.getMessage();
            String[] inicioMensagem = mensagem.split("\\.");
            if (inicioMensagem != null
                    && (inicioMensagem[0].equals("erro") || inicioMensagem[0]
                            .equals("atencao"))) {
                throw new ControladorException(mensagem);
            } else {
                throw new ControladorException("erro.sistema", e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String mensagem = e.getMessage();
            if (mensagem != null) {
                String[] inicioMensagem = mensagem.split("\\.");
                if (inicioMensagem != null
                        && (inicioMensagem[0].equals("erro") || inicioMensagem[0]
                                .equals("atencao"))) {
                    throw new ControladorException(mensagem);
                } else {
                    throw new ControladorException("erro.sistema", e);
                }
            } else {
                throw new ControladorException("erro.sistema", e);
            }
        }		
		return arquivoZip;
	
	}
	
	
	
	/**
	 * Gera o Arquivo de Oracamento e SINP
	 *
	 * [UC0750] - Gerar Arquivo Texto para Orçamento e SINP
	 *
	 * @author Tiago Moreno
	 * @date 14/02/2008
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarArquivoTextoOrcamentoSinp(int anoMesReferencia) 
		throws ControladorException{
		
		StringBuilder arquivoTxFinal = new StringBuilder();
		try{
			String anoMes = anoMesReferencia+"";
				try {
					
					//recuperando os codigos de centro de custo
					Collection centrosCusto = this.repositorioGerencial.pesquisarCentroCusto();
					
					//verifica se a colecao chegou vazia
					if(centrosCusto == null || centrosCusto.isEmpty()){
						throw new ControladorException("atencao.sem_registros_gerencias");
					}
					
					//laco para realizar o processo por centro de custo
					for (Iterator iter = centrosCusto.iterator(); iter.hasNext();) {
						
						StringBuilder arquivoTx = new StringBuilder();
						
						String centroCusto = (String) iter.next();
						
						if (centroCusto != null && !centroCusto.equalsIgnoreCase("")){
							System.out.println("Centro de Custo: " + centroCusto);
							//recuperando os dados da tabela un_resumo_ligacao_economia
							Object[] ligacaoEconomiaArray = this.repositorioGerencial
								.pesquisarRelatorioOrcamentoSINPResumoLigacaoEconomiaArquivoTexto(centroCusto, anoMes);
							
							//recuperando os dados da tabela un_resumo_faturamento
							Object[] resumoFaturamentoArray = this.repositorioGerencial
								.pesquisarRelatorioOrcamentoSINPResumoFaturamentoArquivoTexto(centroCusto, anoMes);
							
							//ano mes de referencia
							arquivoTx
								.append(Util
										.completaString(anoMes, 6));
							//centro de custo
							arquivoTx
								.append(Util
									.completaString(centroCusto, 9));
							
							//Qt economias ativas - Agua
							if (ligacaoEconomiaArray[14] == null){
								ligacaoEconomiaArray[14] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[14].toString(), 6));
							
							//Qt economias ativas - Esgoto (condominal + convencinal)
							if (ligacaoEconomiaArray[17] == null){
								ligacaoEconomiaArray[17] = 0;
							}
							if (ligacaoEconomiaArray[19] == null){
								ligacaoEconomiaArray[19] = 0;
							}
							
							Integer condominal = (Integer) ligacaoEconomiaArray[17];
							Integer convencial = (Integer) ligacaoEconomiaArray[19];
							String ligacoesEsgotoAtiva =
								String.valueOf(condominal.intValue()+convencial.intValue());
							
							arquivoTx
								.append(Util
										.completaString(ligacoesEsgotoAtiva, 6));
							
							//Qt Economias Residencial Ativas - Agua
							if (ligacaoEconomiaArray[22] == null){
								ligacaoEconomiaArray[22] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[22].toString(), 6));
							
							//Qt Economias Rural - Agua
							if (ligacaoEconomiaArray[30] == null){
								ligacaoEconomiaArray[30] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[30].toString(), 6));
							
							//Qt Economias Comercial - Agua
							if (ligacaoEconomiaArray[24] == null){
								ligacaoEconomiaArray[24] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[24].toString(), 6));
							
							//Qt Economias Industrial - Agua
							if (ligacaoEconomiaArray[26] == null){
								ligacaoEconomiaArray[26] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[26].toString(), 6));
							
							//Qt Economias Pública - Agua
							if (ligacaoEconomiaArray[28] == null){
								ligacaoEconomiaArray[28] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[28].toString(), 6));
							
							//Volume Faturado Residencial - Agua
							if (resumoFaturamentoArray[6] == null){
								resumoFaturamentoArray[6] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[6].toString(), 9));						
							
							//Volume Faturado Rural - Agua
							if (resumoFaturamentoArray[11] == null){
								resumoFaturamentoArray[11] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[11].toString(), 9));							
							
							//Volume Faturado Comercial - Agua
							if (resumoFaturamentoArray[8] == null){
								resumoFaturamentoArray[8] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[8].toString(), 9));							
							
							//Volume Faturado Industrial - Agua
							if (resumoFaturamentoArray[9] == null){
								resumoFaturamentoArray[9] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[9].toString(), 9));							
							
							//Volume Faturado Pública - Agua
							if (resumoFaturamentoArray[10] == null){
								resumoFaturamentoArray[10] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[10].toString(), 9));							
							
							//Valor Faturado Residencial - Agua
							if (resumoFaturamentoArray[13] == null){
								resumoFaturamentoArray[13] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[13].toString(), 13));							
							
							//Valor Faturado Rural - Agua
							if (resumoFaturamentoArray[26] == null){
								resumoFaturamentoArray[26] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[26].toString(), 13));							
							
							//Valor Faturado Comercial - Agua
							if (resumoFaturamentoArray[15] == null){
								resumoFaturamentoArray[15] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[15].toString(), 13));							
							
							//Valor Faturado Industrial - Agua
							if (resumoFaturamentoArray[17] == null){
								resumoFaturamentoArray[17] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[17].toString(), 13));							
							
							//Valor Faturado Público - Agua
							if (resumoFaturamentoArray[19] == null){
								resumoFaturamentoArray[19] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[19].toString(), 13));							
							
							//Qt Economias Residencial - Esgoto
							if (ligacaoEconomiaArray[23] == null){
								ligacaoEconomiaArray[23] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[23].toString(), 6));							
							
							//Qt Economias Comercial - Esgoto
							if (ligacaoEconomiaArray[25] == null){
								ligacaoEconomiaArray[25] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[25].toString(), 6));							
	
							//Qt Economias Industrial - Esgoto
							if (ligacaoEconomiaArray[27] == null){
								ligacaoEconomiaArray[27] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[27].toString(), 6));							
							
							//Qt Economias Publica - Esgoto
							if (ligacaoEconomiaArray[29] == null){
								ligacaoEconomiaArray[29] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(ligacaoEconomiaArray[29].toString(), 6));														
							
							//Volume Faturado Residencial - Esgoto
							if (resumoFaturamentoArray[1] == null){
								resumoFaturamentoArray[1] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[1].toString(), 9));							
							
							//Volume Faturado Comercial - Esgoto
							if (resumoFaturamentoArray[2] == null){
								resumoFaturamentoArray[2] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[2].toString(), 9));						
							
							//Volume Faturado Industrial - Esgoto
							if (resumoFaturamentoArray[4] == null){
								resumoFaturamentoArray[4] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[4].toString(), 9));						
							
							//Volume Faturado Público - Esgoto
							if (resumoFaturamentoArray[5] == null){
								resumoFaturamentoArray[5] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[5].toString(), 9));							
							
							//Valor Faturado Residencial - Esgoto
							if (resumoFaturamentoArray[14] == null){
								resumoFaturamentoArray[14] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[14].toString(), 13));														
							
							//Valor Faturado Comercial - Esgoto
							if (resumoFaturamentoArray[16] == null){
								resumoFaturamentoArray[16] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[16].toString(), 13));							
							
							//Valor Faturado Industrial - Esgoto
							if (resumoFaturamentoArray[18] == null){
								resumoFaturamentoArray[18] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[18].toString(), 13));							
							
							//Valor Faturado Público - Esgoto
							if (resumoFaturamentoArray[20] == null){
								resumoFaturamentoArray[20] = 0;
							}
							
							arquivoTx
								.append(Util
										.completaString(resumoFaturamentoArray[20].toString(), 13));
							
							//adicionando a linha no TXT							
							arquivoTxFinal.append(arquivoTx
									.toString());
							
							//zerando a variavel da linha
							arquivoTx = null;
							
							//adicionando quebra da linha
							arquivoTxFinal
									.append(System
											.getProperty("line.separator"));
						}
							
					} 
		 
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", ex);
				}
			
			//criando nome do arquivo
			String nomeZip = null;

			nomeZip = "ARQUIVO_ORCAMENTO_SINP_"+anoMes;

			BufferedWriter out = null;
			ZipOutputStream zos = null;

			File compactadoTipo = new File(nomeZip + ".zip");
			File leituraTipo = new File(nomeZip + ".txt");

			if (arquivoTxFinal != null && arquivoTxFinal.length() != 0) {
				zos = new ZipOutputStream(new FileOutputStream(
						compactadoTipo));
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leituraTipo
								.getAbsolutePath())));
				out.write(arquivoTxFinal.toString());
				out.flush();
				ZipUtil.adicionarArquivo(zos, leituraTipo);
				zos.close();
				leituraTipo.delete();
				out.close();
			}

			// limpa todos os campos
			nomeZip = null;
			out = null;
			zos = null;
			compactadoTipo = null;
			leituraTipo = null;
			arquivoTxFinal = null;
		} catch (IOException e) {
			String mensagem = e.getMessage();
			String[] inicioMensagem = mensagem.split("\\.");
			if (inicioMensagem != null
					&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
							.equals("atencao"))) {
				throw new ControladorException(mensagem);
			} else {
				throw new ControladorException("erro.sistema", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String mensagem = e.getMessage();
			if (mensagem != null) {
				String[] inicioMensagem = mensagem.split("\\.");
				if (inicioMensagem != null
						&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
								.equals("atencao"))) {
					throw new ControladorException(mensagem);
				} else {
					throw new ControladorException("erro.sistema", e);
				}
			} else {
				throw new ControladorException("erro.sistema", e);
			}
		}
		
	}
	
	/**
	 * 
	 * [UC0752] Gerar Quadro de metas por Exercicio
	 * 
	 * @author Bruno Barros
	 * @param filtrarRelatorioQuadroMetasExercicioHelper
	 * @return
	 */
	public Collection<QuadroMetasExercicioHelper> pesquisarRelatorioQuadroMetasExercicio(
			FiltrarRelatorioQuadroMetasExercicioHelper filtrarRelatorioQuadroMetasExercicioHelper) throws ControladorException{
		
		Collection<QuadroMetasExercicioHelper> retorno = new ArrayList<QuadroMetasExercicioHelper>();
		List dadosBrutos = null;
				
		try {
			dadosBrutos = repositorioGerencial
					.pesquisarRelatorioQuadroMetasExercicio( filtrarRelatorioQuadroMetasExercicioHelper );			
			
			if ( dadosBrutos != null && dadosBrutos.size() > 0 ){				
				Iterator iteDadosBrutos = dadosBrutos.iterator();
				
				while ( iteDadosBrutos.hasNext() ){
					// Pegamos linha por linha do retorno
					Object[] linha = ( Object[] )  iteDadosBrutos.next();
					
					// Montamos Helper por Helper para a coleção de retorno
					QuadroMetasExercicioHelper quadroMetasExercicioHelper = new QuadroMetasExercicioHelper();

					// 	Ligacoes Cadastradas
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SETEMBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_OUTUBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesCadastradasNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_NOVEMBRO ] );

					
					
					
					
					
					
					// Ligacoes Cortadas
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_SETEMBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_OUTUBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesCortadasNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CORTADAS_NOVEMBRO ] );
					
					
					
					
					
					
					
					// Ligacoes Suprimidas				
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_AGOSTO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SETEMBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_OUTUBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesSuprimidasNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_NOVEMBRO ] );
					
					
					
					
					
					
					// Ligacoes Ativas
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_SETEMBRO ] );					

					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_OUTUBRO ] );					

					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_NOVEMBRO ] );					
					

					
					
					
					
					// Ligacoes Ativas com débitos a mais de 3 meses
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SETEMBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_OUTUBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesAtivasDebitos3mNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_NOVEMBRO ] );
					
					
					
					
					
					
					// Ligacoes Consumo Medido
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SETEMBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_OUTUBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMedidoNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_NOVEMBRO ] );
					
					
					
					
					
					
					// Ligacoes Consumo 5m3
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Dezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Janeiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Fevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Marco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Abril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Maio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Junho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Julho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Agosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Setembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SETEMBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Outubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_OUTUBRO ] );

					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumo5m3Novembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_NOVEMBRO ] );
					
					
					
					
					
					
					// Ligacoes Consumo Não Medido
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SETEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_OUTUBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoNaoMedidoNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_NOVEMBRO ] );
					
					
					
					
					
					
					
					
					// Ligacoes Consumo Media
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SETEMBRO] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_OUTUBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeLigacoesConsumoMediaNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_NOVEMBRO ] );
					
					
					

					// Quantidade de Economias
					quadroMetasExercicioHelper.setQuantidadeEconomiasDezembro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_DEZEMBRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasJaneiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_JANEIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasFevereiro(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_FEVEREIRO ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasMarco(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_MARCO ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasAbril(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_ABRIL ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasMaio(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_MAIO ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasJunho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_JUNHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasJulho(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_JULHO ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasAgosto(
						(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_AGOSTO ] );
					
					quadroMetasExercicioHelper.setQuantidadeEconomiasSetembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_SETEMBRO ] );

					quadroMetasExercicioHelper.setQuantidadeEconomiasOutubro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_OUTUBRO ] );

					quadroMetasExercicioHelper.setQuantidadeEconomiasNovembro(
							(Integer) linha[ RepositorioGerencialHBM.INDEX_QUANTIDADE_ECONOMIAS_NOVEMBRO ] );
					
					
					// Verificamos quais os grupos que foram formados
					//Gerencia regional
					if ( filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_GERENCIA_REGIONAL )  ||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_UNIDADE_NEGOCIO ) 	||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_LOCALIDADE ) 		||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_GERENCIA_REGIONAL )  ||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO )  ||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_LOCALIDADE ) ){
						
						FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();
						filtro.adicionarParametro( new ParametroSimples( FiltroGerenciaRegional.ID, (Integer) linha[ RepositorioGerencialHBM.INDEX_ID_GERENCIA_EXERCICIO ] ) );
						Collection<GerenciaRegional> colGerenciaRegional =  this.getControladorUtil().pesquisar( filtro, GerenciaRegional.class.getName() );
						
						if ( colGerenciaRegional != null && colGerenciaRegional.size() > 0 ){
							GerenciaRegional gerenciaRegional = ( GerenciaRegional ) colGerenciaRegional.toArray()[0];
							quadroMetasExercicioHelper.setNomeGerenciaRegional( gerenciaRegional.getId() + " - " + gerenciaRegional.getNome() );
						}						
					}

					if ( filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_UNIDADE_NEGOCIO ) 	||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_LOCALIDADE )		||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO )  ||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_LOCALIDADE ) ){		
						FiltroUnidadeNegocio filtro = new FiltroUnidadeNegocio();
						filtro.adicionarParametro( new ParametroSimples( FiltroUnidadeNegocio.ID, (Integer) linha[ RepositorioGerencialHBM.INDEX_ID_UNIDADE_NEGOCIO_EXERCICIO ] ) );
						Collection<UnidadeNegocio> colUnidadeNegocio =  this.getControladorUtil().pesquisar( filtro, UnidadeNegocio.class.getName() );
						
						if ( colUnidadeNegocio != null && colUnidadeNegocio.size() > 0 ){
							UnidadeNegocio unidadeNegocio = ( UnidadeNegocio ) colUnidadeNegocio.toArray()[0];
							quadroMetasExercicioHelper.setNomeUnidadeNegocio( unidadeNegocio.getId() + " - " + unidadeNegocio.getNome() );							
						}	
					}
					
					if ( filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_LOCALIDADE )		||
						 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao().equals( RepositorioGerencialHBM.TOTALIZACAO_ESTADO_LOCALIDADE ) ){		
						FiltroLocalidade filtro = new FiltroLocalidade();
						filtro.adicionarParametro( new ParametroSimples( FiltroLocalidade.ID, (Integer) linha[ RepositorioGerencialHBM.INDEX_ID_LOCALIDADE_EXERCICIO ] ) );
						Collection<Localidade> colLocalidade =  this.getControladorUtil().pesquisar( filtro, Localidade.class.getName() );
						
						if ( colLocalidade != null && colLocalidade.size() > 0 ){
							Localidade localidade = ( Localidade ) colLocalidade.toArray()[0];
							quadroMetasExercicioHelper.setNomeLocalidade( localidade.getId() + " - " + localidade.getDescricao() );
							quadroMetasExercicioHelper.setNomeCentroCusto( localidade.getCodigoCentroCusto() );
						}						
					}					
					retorno.add( quadroMetasExercicioHelper );					
				}
			} else {
				throw new ControladorException( "atencao.naocadastrado", null, "Quadro de Metas por Exercicio" );
			}
			

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;	
	}
	
	/**
	 * Esta funcionalidade permite informar dados para geração de Relatórios ou
	 * consultas
	 * 
	 * [RM 1078] - Informar Dados para Geração de Relatório ou Consulta por Período
	 * 
	 * @author Davi Menezes
	 * @date 30/09/2011
	 * 
	 * @param mesAnoInicialFaturamento
	 * @param mesAnoFinalFaturamento
	 * @param opcaoTotalizacao
	 * @param idFauramentoGrupo
	 * @param idGerenciaRegional
	 * @param idEloPolo
	 * @param idLocalidade
	 * @param cdSetorComercial
	 * @param nmQuadra
	 * @param idsImovelPerfil
	 * @param idsLigacaoAguaSituacao
	 * @param idsLigacaoEsgotoSituacao
	 * @param idsCategoria
	 * @param idsEsferaPoder
	 * @return InformarDadosGeracaoRelatorioConsultaHelper
	 * @throws ControladorException
	 */
	public InformarDadosGeracaoRelatorioConsultaPeriodoHelper informarDadosGeracaoRelatorioConsultaPeriodo(
			String mesAnoInicialFaturamento, String mesAnoFinalFaturamento, Integer opcaoTotalizacao,
			Integer idFauramentoGrupo, Integer idCobrancaGrupo,
			Integer idGerenciaRegional, Integer idEloPolo,
			Integer idLocalidade, Integer idSetorComercial, Integer nmQuadra,
			String[] idsImovelPerfil, String[] idsLigacaoAguaSituacao,
			String[] idsLigacaoEsgotoSituacao, String[] idsCategoria,
			String[] idsEsferaPoder, Integer tipoAnaliseFaturamento,
			Integer tipoRelatorio, Integer idUnidadeNegocio, Integer idMunicipio, Integer idRota) throws ControladorException {

		InformarDadosGeracaoRelatorioConsultaPeriodoHelper retorno = new InformarDadosGeracaoRelatorioConsultaPeriodoHelper();

		if (!Util.validarMesAno(mesAnoInicialFaturamento)) {
			throw new ControladorException("atencao.ano_mes.invalido");
		}
		
		if(!Util.validarMesAno(mesAnoInicialFaturamento)){
			throw new ControladorException("atencao.ano_mes.invalido");
		}

		Integer anoMesInicioReferencia = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(mesAnoInicialFaturamento));
		
		Integer anoMesFinalReferencia = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(mesAnoFinalFaturamento));
		// SistemaParametro sistemaParametro =
		// this.getControladorUtil().pesquisarParametrosDoSistema();

		// if
		// (anoMesReferencia.compareTo(sistemaParametro.getAnoMesFaturamento())
		// ==
		// 1){
		// throw new
		// ControladorException("atencao.ano.mes.referencia.posterior.ano.mes.faturamento",
		// null,
		// String.valueOf(sistemaParametro.getAnoMesFaturamento()));
		// }

		retorno.setAnoMesInicialReferencia(anoMesInicioReferencia);
		retorno.setAnoMesFinalReferencia(anoMesFinalReferencia);
		retorno.setOpcaoTotalizacao(opcaoTotalizacao);
		retorno.setDescricaoOpcaoTotalizacao(this
				.obterDescricaoOpcaoTotalizacao(opcaoTotalizacao));

		Collection<Object> colecaoPesquisa = null;
		if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GRUPO_FATURAMENTO)
				&& idFauramentoGrupo != null) {

			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, idFauramentoGrupo));

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Grupo de Faturamento");
			}

			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setFaturamentoGrupo(faturamentoGrupo);

		} else if (opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_GRUPO_COBRANCA)
				&& idCobrancaGrupo != null) {
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.ID, idCobrancaGrupo));

			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Grupo de Cobrança");
			}

			CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setCobrancaGrupo(cobrancaGrupo);
		} else if ((opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL)
				|| opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_UNIDADE_NEGOCIO) 
				|| opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_LOCALIDADE))
				&& idGerenciaRegional != null) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Gerência Regional");
			}

			GerenciaRegional gerenciaRegional = (GerenciaRegional) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setGerenciaRegional(gerenciaRegional);

		} else if ((opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO)
				|| opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_LOCALIDADE) 
				|| opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_SETOR_COMERCIAL))
				&& idUnidadeNegocio != null) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			
			filtroUnidadeNegocio.adicionarParametro(
    				new ParametroSimples(FiltroUnidadeNegocio.ID, 
    						idUnidadeNegocio));
			
    		filtroUnidadeNegocio.adicionarParametro(
    				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
    				ConstantesSistema.INDICADOR_USO_ATIVO));		

    		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
    			this.getControladorUtil().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

    		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
    			throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Unidade Negócio");
    		}else{
    			
    			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util
					.retonarObjetoDeColecao(colecaoUnidadeNegocio);

    			retorno.setUnidadeNegocio(unidadeNegocio);
    			
    		}
	
		} else if ((opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_LOCALIDADE)
				|| opcaoTotalizacao
						.equals(ConstantesSistema.CODIGO_LOCALIDADE_SETOR_COMERCIAL) || opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_LOCALIDADE_QUADRA))
				&& idLocalidade != null) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Localidade");
			}

			Localidade localidade = (Localidade) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setLocalidade(localidade);
		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_MUNICIPIO) && idMunicipio != null) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroMunicipio, Municipio.class.getName());

			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
				Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
				retorno.setMunicipio(municipio);
			}else{
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Município");
			}
		
		} else if ((opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL) || opcaoTotalizacao
				.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA))
				&& idSetorComercial != null) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial
					.adicionarCaminhoParaCarregamentoEntidade("localidade");

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID, idSetorComercial));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Setor Comercial");
			}

			SetorComercial setorComercial = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setLocalidade(setorComercial.getLocalidade());
			retorno.setSetorComercial(setorComercial);

		} else if (opcaoTotalizacao.equals(ConstantesSistema.CODIGO_QUADRA)
				&& idSetorComercial != null && nmQuadra != null) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, nmQuadra));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = this.getControladorUtil().pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Quadra");
			}

			Quadra quadra = (Quadra) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setLocalidade(quadra.getSetorComercial().getLocalidade());
			retorno.setSetorComercial(quadra.getSetorComercial());
			retorno.setQuadra(quadra);

		/** [RR2011071026]
		 * 	Autor: Paulo Diniz
		 *  Data: 21/07/2011
		 *  Resumo da Análise do Faturamento 
		 */
		}else if ( opcaoTotalizacao.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_ROTA)
				&& idRota != null) {

			FiltroRota filtroRota = new FiltroRota();
			
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));

			colecaoPesquisa = this.getControladorUtil().pesquisar(
					filtroRota, Rota.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ControladorException("atencao.pesquisa_inexistente",
						null, "Rota");
			}

			Rota rota = (Rota) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

			retorno.setRota(rota);

		}

		if (idsImovelPerfil != null && idsImovelPerfil.length > 0) {

			Collection<ImovelPerfil> colecaoImovelPerfil = new ArrayList<ImovelPerfil>();
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			ImovelPerfil imovelPerfil = null;

			for (int index = 0; idsImovelPerfil.length > index; index++) {

				if (idsImovelPerfil[index] != null
						&& idsImovelPerfil[index].length() > 0) {

					filtroImovelPerfil.adicionarParametro(new ParametroSimples(
							FiltroImovelPerfil.ID, idsImovelPerfil[index]));

					filtroImovelPerfil.adicionarParametro(new ParametroSimples(
							FiltroImovelPerfil.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroImovelPerfil, ImovelPerfil.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Perfil do Imóvel");
					}

					imovelPerfil = (ImovelPerfil) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroImovelPerfil.limparListaParametros();

					colecaoImovelPerfil.add(imovelPerfil);
				}
			}

			if (!colecaoImovelPerfil.isEmpty()) {
				retorno.setColecaoImovelPerfil(colecaoImovelPerfil);
			}

		}

		if (idsLigacaoAguaSituacao != null && idsLigacaoAguaSituacao.length > 0) {

			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = new ArrayList<LigacaoAguaSituacao>();
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;

			for (int index = 0; idsLigacaoAguaSituacao.length > index; index++) {

				if (idsLigacaoAguaSituacao[index] != null
						&& idsLigacaoAguaSituacao[index].length() > 0) {
					filtroLigacaoAguaSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroLigacaoAguaSituacao.ID,
									idsLigacaoAguaSituacao[index]));

					filtroLigacaoAguaSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroLigacaoAguaSituacao.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroLigacaoAguaSituacao,
							LigacaoAguaSituacao.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Ligação de Água");
					}

					ligacaoAguaSituacao = (LigacaoAguaSituacao) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroLigacaoAguaSituacao.limparListaParametros();

					colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacao);
				}
			}

			if (!colecaoLigacaoAguaSituacao.isEmpty()) {
				retorno
						.setColecaoLigacaoAguaSituacao(colecaoLigacaoAguaSituacao);
			}

		}

		if (idsLigacaoEsgotoSituacao != null
				&& idsLigacaoEsgotoSituacao.length > 0) {

			Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = new ArrayList<LigacaoEsgotoSituacao>();
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

			for (int index = 0; idsLigacaoEsgotoSituacao.length > index; index++) {

				if (idsLigacaoEsgotoSituacao[index] != null
						&& idsLigacaoEsgotoSituacao[index].length() > 0) {

					filtroLigacaoEsgotoSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroLigacaoEsgotoSituacao.ID,
									idsLigacaoEsgotoSituacao[index]));

					filtroLigacaoEsgotoSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroLigacaoEsgotoSituacao,
							LigacaoEsgotoSituacao.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Ligação de Esgoto");
					}

					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroLigacaoEsgotoSituacao.limparListaParametros();

					colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacao);
				}
			}

			if (!colecaoLigacaoEsgotoSituacao.isEmpty()) {
				retorno
						.setColecaoLigacaoEsgotoSituacao(colecaoLigacaoEsgotoSituacao);
			}

		}

		if (idsCategoria != null && idsCategoria.length > 0) {

			Collection<Categoria> colecaoCategoria = new ArrayList<Categoria>();
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			Categoria categoria = null;

			for (int index = 0; idsCategoria.length > index; index++) {

				if (idsCategoria[index] != null
						&& idsCategoria[index].length() > 0) {

					filtroCategoria.adicionarParametro(new ParametroSimples(
							FiltroCategoria.CODIGO, idsCategoria[index]));

					filtroCategoria.adicionarParametro(new ParametroSimples(
							FiltroCategoria.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroCategoria, Categoria.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Categoria");
					}

					categoria = (Categoria) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroCategoria.limparListaParametros();

					colecaoCategoria.add(categoria);
				}
			}

			if (!colecaoCategoria.isEmpty()) {
				retorno.setColecaoCategoria(colecaoCategoria);
			}
		}

		if (idsEsferaPoder != null && idsEsferaPoder.length > 0) {

			Collection<EsferaPoder> colecaoEsferaPoder = new ArrayList<EsferaPoder>();
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			EsferaPoder esferaPoder = null;

			for (int index = 0; idsEsferaPoder.length > index; index++) {

				if (idsEsferaPoder[index] != null
						&& idsEsferaPoder[index].length() > 0) {

					filtroEsferaPoder.adicionarParametro(new ParametroSimples(
							FiltroEsferaPoder.ID, idsEsferaPoder[index]));

					filtroEsferaPoder.adicionarParametro(new ParametroSimples(
							FiltroEsferaPoder.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = this.getControladorUtil().pesquisar(
							filtroEsferaPoder, EsferaPoder.class.getName());

					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.pesquisa_inexistente", null,
								"Esfera de Poder");
					}

					esferaPoder = (EsferaPoder) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtroEsferaPoder.limparListaParametros();

					colecaoEsferaPoder.add(esferaPoder);
				}
			}

			if (!colecaoEsferaPoder.isEmpty()) {
				retorno.setColecaoEsferaPoder(colecaoEsferaPoder);
			}

		}

		if (tipoAnaliseFaturamento != null) {
			retorno.setTipoAnaliseFaturamento(tipoAnaliseFaturamento);
		}

		if (tipoRelatorio != null) {
			retorno.setGerarRelatorio(true);
			retorno.setTipoRelatorio(tipoRelatorio);
		}

		return retorno;
	}
	
	/**
	 * Método para auxilio de Casos de Uso de resumos
	 */

	public Collection criarColecaoAgrupamentoResumosPeriodo(
			InformarDadosGeracaoRelatorioConsultaPeriodoHelper informarDadosGeracaoRelatorioConsultaPeriodoHelper)
			throws ControladorException {
		/**
		 * Este caso de uso serve para montar uma coleção de Object[] para
		 * montagem da parte Tabela Dados da Geração da Consulta q se refere as
		 * opções de agrupamento(no jsp).
		 * 
		 * No Action q chamar esse método, mandar o retorno deste método para o
		 * jsp atraves do request com o nome de colecaoAgrupamento.
		 * 
		 * ver Action ExibirResultadoConsultaResumoAnormalidadeAction.java
		 */

		Collection colecaoLocalidade = null;
		Collection colecaoAgrupamento = new ArrayList();
		FiltroLocalidade filtroLocalidade = null;
		Localidade localidade = null;
		Object[] objeto = null;
		switch (informarDadosGeracaoRelatorioConsultaPeriodoHelper
				.getOpcaoTotalizacao().intValue()) {
		case ConstantesSistema.CODIGO_GRUPO_FATURAMENTO:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.GRUPO_FATURAMENTO;
			objeto[1] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getFaturamentoGrupo().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getFaturamentoGrupo().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_GRUPO_COBRANCA:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.GRUPO_COBRANCA;
			objeto[1] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getCobrancaGrupo().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getCobrancaGrupo().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_GERENCIA_REGIONAL:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.GERENCIA_REGIONAL;
			objeto[1] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getGerenciaRegional().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getGerenciaRegional().getNomeAbreviado();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_ELO_POLO:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.ELO_POLO;
			objeto[1] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getEloPolo().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getEloPolo().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_LOCALIDADE:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.LOCALIDADE;
			objeto[1] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getLocalidade().getId().toString();
			objeto[2] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getLocalidade().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_SETOR_COMERCIAL:
			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID,
					informarDadosGeracaoRelatorioConsultaPeriodoHelper
							.getSetorComercial().getLocalidade().getId()));
			colecaoLocalidade = getControladorUtil().pesquisar(
					filtroLocalidade, Localidade.class.getName());
			localidade = (Localidade) colecaoLocalidade.iterator().next();

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.LOCALIDADE;
			objeto[1] = localidade.getId().toString();
			objeto[2] = localidade.getDescricao();
			colecaoAgrupamento.add(objeto);

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.SETOR_COMERCIAL;
			objeto[1] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getSetorComercial().getCodigo()
					+ "";
			objeto[2] = informarDadosGeracaoRelatorioConsultaPeriodoHelper
					.getSetorComercial().getDescricao();
			colecaoAgrupamento.add(objeto);
			break;
		case ConstantesSistema.CODIGO_QUADRA:
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID,
					informarDadosGeracaoRelatorioConsultaPeriodoHelper.getQuadra()
							.getSetorComercial().getId()));
			Collection colecaoSetorComercial = getControladorUtil().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercial = (SetorComercial) colecaoSetorComercial
					.iterator().next();

			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade
					.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, setorComercial.getLocalidade()
									.getId()));
			colecaoLocalidade = getControladorUtil().pesquisar(
					filtroLocalidade, Localidade.class.getName());
			localidade = (Localidade) colecaoLocalidade.iterator().next();

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.LOCALIDADE;
			objeto[1] = localidade.getId().toString();
			objeto[2] = localidade.getDescricao();
			colecaoAgrupamento.add(objeto);

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.SETOR_COMERCIAL;
			objeto[1] = setorComercial.getCodigo() + "";
			objeto[2] = setorComercial.getDescricao();
			colecaoAgrupamento.add(objeto);

			objeto = new Object[3];
			objeto[0] = ConstantesSistema.QUADRA;
			objeto[1] = informarDadosGeracaoRelatorioConsultaPeriodoHelper.getQuadra()
					.getNumeroQuadra()
					+ "";
			objeto[2] = "";
			colecaoAgrupamento.add(objeto);
			break;

		case ConstantesSistema.CODIGO_ESTADO:
			objeto = new Object[3];
			objeto[0] = ConstantesSistema.ESTADO;
			objeto[1] = "1";
			objeto[2] = "Estado";
			colecaoAgrupamento.add(objeto);
			break;

		}
		return colecaoAgrupamento;
	}
	
	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 28/06/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoPendenciaBD(int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
		
			System.out.println("Iniciou Resumo Pendencia");
			repositorioGerencial.gerarResumoPendenciaBD();
			System.out.println("Finalizou Resumo Pendencia");
			
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
}
