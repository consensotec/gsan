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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLigacaoTipo;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author F�bio Silva
 * @date 12/01/2015
 */
public class ExibirFiltrarHistoricoMedicaoIndividualizadaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ParseException {

		ActionForward retorno = actionMapping.findForward("exibirFiltrarHistoricoMedicaoIndividualizada");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarHistoricoMedicaoIndividualizadaActionForm form = (FiltrarHistoricoMedicaoIndividualizadaActionForm) actionForm;

		if (httpServletRequest.getParameter("limparForm") != null && httpServletRequest.getParameter("limparForm").equals(ConstantesSistema.OK)) {
			form.limparForm();
		}
		
		if(httpServletRequest.getParameter("limparImovel")!=null){
			form.setIdImovel(null);
			form.setDescricaoImovel(null);
		}

		this.pesquisarGerenciaRegional(httpServletRequest);

		if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			this.pesquisarUnidadeNegocio(httpServletRequest, form);
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio", new ArrayList<String>());
		}

		String idImovel = form.getIdImovel();

		if (idImovel != null && !"".equals(idImovel)) {
			httpServletRequest.setAttribute("desabilitarCampos", "true");
		}

		if (httpServletRequest.getParameter("pesquisarImovel") != null
				&& httpServletRequest.getParameter("pesquisarImovel").equals(ConstantesSistema.OK)) {

			if (idImovel != null && !"".equals(idImovel)) {
				form.limparFormClickImovel();
				this.pesquisarImovel(httpServletRequest, form);
			}
		}

		Collection<TipoRateio> colecaoRateio = Arrays.asList(TipoRateio.values());
		sessao.setAttribute("colecaoRateio", colecaoRateio);

		this.pesquisarAnormalidadeConsumo(httpServletRequest);
		this.pesquisarAnormalidadeLeitura(httpServletRequest);
		this.carregarTipoLigacao(sessao);

		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& (objetoConsulta.trim().equals("1") || objetoConsulta.trim().equals("3"))) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form, objetoConsulta);
		}

		// Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& (objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("4"))) {

			// Faz a consulta de Setor Comercial
			this.pesquisarSetorComercial(form, objetoConsulta);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& (objetoConsulta.trim().equals("5") || objetoConsulta.trim().equals("6"))) {

			// Faz a valida��o da Rota
			this.validarRota(form, objetoConsulta);
		}

		this.setaRequest(httpServletRequest,form);

		return retorno;
	}

	private void pesquisarImovel(HttpServletRequest httpServletRequest, FiltrarHistoricoMedicaoIndividualizadaActionForm form) throws ParseException {
		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getIdImovel()));
		filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, Imovel.IMOVEL_EXCLUIDO));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico.rateioTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");

		Collection<?> imovelPesquisado = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
		Imovel imovelCondominio = null;

		// [FS0002 - Verificar exist�ncia da matr�cula do im�vel
		if (imovelPesquisado == null || imovelPesquisado.isEmpty()) {
			form.setIdImovel("");
			form.setDescricaoImovel("MATR�CULA INEXISTENTE");
			httpServletRequest.setAttribute("matriculaInexistente", true);
		} else {
			imovelCondominio = (Imovel) imovelPesquisado.iterator().next();
			form.setDescricaoImovel(imovelCondominio.getInscricaoFormatada());
			httpServletRequest.setAttribute("desabilitarCampos", "true");

			// [FS0001] - Verificar se o im�vel � um condom�nio
			if (imovelCondominio.getIndicadorImovelCondominio() != null && imovelCondominio.getIndicadorImovelCondominio().equals(Imovel.IMOVEL_NAO_CONDOMINIO)) {
				throw new ActionServletException("atencao.imovel.nao_condominio");
			}
		}
	}

	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest) {
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colecaoGerenciaRegional = this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Ger�ncia Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}
	}

	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest, FiltrarHistoricoMedicaoIndividualizadaActionForm form) {
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, form.getGerenciaRegional()));
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade de Neg�cio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
	}

	private void carregarTipoLigacao(HttpSession sessao) {
		FiltroLigacaoTipo filtroLigacaoTipo = new FiltroLigacaoTipo();

		Collection<?> colecaoLigacaoTipo = this.getFachada().pesquisar(filtroLigacaoTipo, LigacaoTipo.class.getName());
		sessao.setAttribute("colecaoLigacaoTipo", colecaoLigacaoTipo);
	}

	private void pesquisarLocalidade(FiltrarHistoricoMedicaoIndividualizadaActionForm form, String objetoConsulta) {
		Object local = form.getLocalidadeInicial();

		if (!objetoConsulta.trim().equals("1")) {
			local = form.getLocalidadeFinal();
		}

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, local));

		// Recupera Localidade
		Collection<?> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

			if (objetoConsulta.trim().equals("1")) {
				form.setLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			}

			form.setLocalidadeFinal(localidade.getId().toString());
			form.setNomeLocalidadeFinal(localidade.getDescricao());

		} else {
			if (objetoConsulta.trim().equals("1")) {
				form.setLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");

				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			} else {
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
		}
	}

	private void pesquisarSetorComercial(FiltrarHistoricoMedicaoIndividualizadaActionForm form, String objetoConsulta) {
		Object local = form.getLocalidadeInicial();
		Object setor = form.getSetorComercialInicial();

		if (!objetoConsulta.trim().equals("2")) {
			local = form.getLocalidadeFinal();
			setor = form.getSetorComercialFinal();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setor));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE, local));

		// Recupera Setor Comercial
		Collection<?> colecaoSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

			if (objetoConsulta.trim().equals("2")) {
				form.setSetorComercialInicial("" + setorComercial.getCodigo());
				form.setNomeSetorComercialInicial(setorComercial.getDescricao());
			}

			form.setSetorComercialFinal("" + setorComercial.getCodigo());
			form.setNomeSetorComercialFinal(setorComercial.getDescricao());
		} else {
			if (objetoConsulta.trim().equals("2")) {
				form.setSetorComercialInicial(null);
				form.setNomeSetorComercialInicial("Setor Comercial Inicial inexistente");

				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal(null);
			} else {
				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal("Setor Comercial Final inexistente");
			}
		}
	}

	private void validarRota(FiltrarHistoricoMedicaoIndividualizadaActionForm form, String objetoConsulta) {
		Object rota = form.getRotaInicial();
		Object setor = form.getSetorComercialInicial();
		String tipoRota = "inicial";
		
		if (!objetoConsulta.trim().equals("5")) {
			rota = form.getRotaFinal();
			setor = form.getSetorComercialFinal();
			tipoRota = "final";
		}
		
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, rota));
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		if (setor != null && !"".equals(setor)) {
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, setor));
		}

		Collection<?> colecaoRotas = this.getFachada().pesquisar(filtroRota, Rota.class.getName());

		if (setor != null && !"".equals(setor) && colecaoRotas.isEmpty()) {
			throw new ActionServletException("atencao.rota_setor_comercial", form.getSetorComercialInicial());
		} else if (colecaoRotas == null || colecaoRotas.isEmpty()) {
			throw new ActionServletException("atencao.rota_inexistente", tipoRota);
		}
	}

	private void pesquisarAnormalidadeConsumo(HttpServletRequest httpServletRequest) {
		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();

		filtroConsumoAnormalidade.setConsultaSemLimites(true);
		filtroConsumoAnormalidade.setCampoOrderBy(FiltroConsumoAnormalidade.DESCRICAO);
		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colecaoAnormalidadeConsumo = this.getFachada().pesquisar(filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());

		if (colecaoAnormalidadeConsumo == null || colecaoAnormalidadeConsumo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade de Neg�cio");
		} else {
			httpServletRequest.setAttribute("colecaoAnormalidadeConsumo", colecaoAnormalidadeConsumo);
		}
	}

	private void pesquisarAnormalidadeLeitura(HttpServletRequest httpServletRequest) {
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		filtroLeituraAnormalidade.setConsultaSemLimites(true);
		filtroLeituraAnormalidade.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);
		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colecaoAnormalidadeLeitura = this.getFachada().pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

		if (colecaoAnormalidadeLeitura == null || colecaoAnormalidadeLeitura.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade de Neg�cio");
		} else {
			httpServletRequest.setAttribute("colecaoAnormalidadeLeitura", colecaoAnormalidadeLeitura);
		}
	}

	private void setaRequest(HttpServletRequest httpServletRequest, FiltrarHistoricoMedicaoIndividualizadaActionForm form) {

		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && !form.getLocalidadeInicial().equals("")
				&& form.getNomeLocalidadeInicial() != null && !form.getNomeLocalidadeInicial().equals("")) {

			httpServletRequest.setAttribute("localidadeInicialEncontrada", "true");
			httpServletRequest.setAttribute("localidadeFinalEncontrada", "true");
		} else {
			if (form.getLocalidadeFinal() != null && !form.getLocalidadeFinal().equals("")
					&& form.getNomeLocalidadeFinal() != null && !form.getNomeLocalidadeFinal().equals("")) {

				httpServletRequest.setAttribute("localidadeFinalEncontrada", "true");
			}
		}

		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("")
				&& form.getNomeSetorComercialInicial() != null && !form.getNomeSetorComercialInicial().equals("")) {

			httpServletRequest.setAttribute("setorComercialInicialEncontrado", "true");
			httpServletRequest.setAttribute("setorComercialFinalEncontrado", "true");
		} else {
			if (form.getSetorComercialFinal() != null && !form.getSetorComercialFinal().equals("")
					&& form.getNomeSetorComercialFinal() != null && !form.getNomeSetorComercialFinal().equals("")) {

				httpServletRequest.setAttribute("setorComercialFinalEncontrado", "true");
			}
		}
	}
}
