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
package gcom.gui.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.bean.ParametrosComandoNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ana Maria
 * @date 22/11/2006
 */
public class ExibirAtualizarComandoNegativacaoPorCriterioAction extends
		GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// ------------Parte que inicializa o processo de
		// atualiza��o----------------------------------

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("atualizarComandoNegativacaoPorCriterio");

		// obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		String idComandoNegativacao = null;

		StatusWizard statusWizard = null;

		if (httpServletRequest.getParameter("desfazer") == null) {

			if (httpServletRequest.getParameter("idComandoNegativacao") != null) {
				idComandoNegativacao = (String) httpServletRequest
						.getParameter("idComandoNegativacao");
			} else if (httpServletRequest.getAttribute("idComandoNegativacao") != null) {
				idComandoNegativacao = (String) httpServletRequest
						.getAttribute("idComandoNegativacao");
			}

			// Verifica se chegou no atualizar cliente atraves da tela de
			// filtrar devido a um unico registro
			// ou atraves da lista de imoveis no manter cliente
			if (sessao.getAttribute("atualizar") != null
					|| httpServletRequest.getParameter("sucesso") != null) {
				statusWizard = new StatusWizard(
						"atualizarComandoNegativacaPorCriterioWizardAction",
						"atualizarComandoNegativacaoPorCriterioAction",
						"cancelarAtualizarComandoNegativacaoAction",
						"exibirFiltrarComandoNegativacaoPorCriterioAction",
						"exibirAtualizarComandoNegativacaoPorCriterioAction.do",
						idComandoNegativacao);
				sessao.removeAttribute("atualizar");
			} else {
				statusWizard = new StatusWizard(
						"atualizarComandoNegativacaPorCriterioWizardAction",
						"atualizarComandoNegativacaoPorCriterioAction",
						"cancelarInserirComandoNegativacaoAction",
						"exibirManterComandoNegativacaoAction",
						"exibirAtualizarComandoNegativacaoPorCriterioAction.do",
						idComandoNegativacao);
			}

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							1,
							"DadosGeraisPrimeiraAbaA.gif",
							"DadosGeraisPrimeiraAbaD.gif",
							"exibirAtualizarComandoNegativacaoDadosGeraisAction",
							"atualizarComandoNegativacaoDadosGeraisAction"));

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							2,
							"DebitosA.gif",
							"DebitosD.gif",
							"exibirAtualizarComandoNegativacaoDadosDebitoAction",
							"atualizarComandoNegativacaoDadosDebitoAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							3,
							"ImovelPrimeiraAbaA.gif",
							"ImovelPrimeiraAbaD.gif",
							"exibirAtualizarComandoNegativacaoDadosImovelAction",
							"atualizarComandoNegativacaoDadosImovelAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							4,
							"LocalizacaoA.gif",
							"LocalizacaoD.gif",
							"exibirAtualizarComandoNegativacaoLocalizacaoAction",
							"atualizarComandoNegativacaoLocalizacaoAction"));

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							5, "abaExclusaoA.gif", "abaExclusaoD.gif",
							"exibirAtualizarComandoNegativacaoExclusaoAction",
							"atualizarComandoNegativacaoExclusaoAction"));

			// manda o statusWizard para a sessao
			sessao.setAttribute("statusWizard", statusWizard);

		} else {
			statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");

			idComandoNegativacao = statusWizard.getId();
		}

		// Obt�m o action form
		AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;

		// Verifica se os objetos est�o na sess�o
		if (idComandoNegativacao != null && !idComandoNegativacao.equals("")) {

			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper = fachada
					.pesquisarParametrosComandoNegativacao(Integer
							.parseInt(idComandoNegativacao));

			form.reset();
			// Removendo todos os objetos da sess�o
			// Aba 01
			sessao.removeAttribute("colecaoNegativacaoCriterioCpfTipo");

			// Aba 03
			sessao.removeAttribute("colecaoClienteRelacaoTipo");
			sessao.removeAttribute("colecaoSubcategoria");
			sessao.removeAttribute("colecaoPerfilImovel");
			sessao.removeAttribute("colecaoTipoCliente");

			// Aba 04
			sessao.removeAttribute("colcaoCobrancaGrupo");
			sessao.removeAttribute("colecaoGerenciaRegional");
			sessao.removeAttribute("colecaoUnidadeNegocio");
			sessao.removeAttribute("colecaoEloPolo");

			form.setIdNegativacaoComando(idComandoNegativacao);
			form.setUltimaAlteracaoNegComando(parametrosComandoNegativacaoHelper
					.getUltimaAlteracaoNegComando());
			form.setIdNegativacaoCriterio(parametrosComandoNegativacaoHelper
					.getIdNagativacaoCriterio().toString());

			// Dados Gerais
			setDadosGerais(sessao, form, parametrosComandoNegativacaoHelper,
					fachada);

			// Dados D�bitos
			setDadosDebitos(form, parametrosComandoNegativacaoHelper);

			// Dados Im�vel
			setDadosImovel(form, parametrosComandoNegativacaoHelper);

			// Dados Localiza��o
			setDadosLocalizacao(form, parametrosComandoNegativacaoHelper);

			// Dados Exclus�o
			setDadosExclusao(form, parametrosComandoNegativacaoHelper);

		}

		sessao.removeAttribute("idComandoNegativacao");

		// Manda o form para a primeira p�gina do processo para que depois
		// ela seja colocada na sess�o
		httpServletRequest.setAttribute(
				"AtualizarComandoNegativacaoPorCriterioActionForm", form);

		return retorno;
	}

	private void setDadosLocalizacao(
			AtualizarComandoNegativacaoPorCriterioActionForm form,
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		// Grupo de Cobran�a
		if (parametrosComandoNegativacaoHelper.getColecaoGrupoCobranca() != null) {
			String[] idsGrupoCobranca = new String[parametrosComandoNegativacaoHelper
					.getColecaoGrupoCobranca().size()];
			Iterator colecaoGrupoCobranca = parametrosComandoNegativacaoHelper
					.getColecaoGrupoCobranca().iterator();
			int qtdGrupoCobranca = 0;
			while (colecaoGrupoCobranca.hasNext()) {
				CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) colecaoGrupoCobranca
						.next();
				idsGrupoCobranca[qtdGrupoCobranca] = cobrancaGrupo.getId()
						.toString();
				qtdGrupoCobranca++;
			}
			form.setCobrancaGrupo(idsGrupoCobranca);
		} else {
			form.setCobrancaGrupo(null);
		}

		// Gerencia Regional

		if (parametrosComandoNegativacaoHelper.getColecaoGerenciaRegional() != null) {
			String[] idsGerenciaRegional = new String[parametrosComandoNegativacaoHelper
					.getColecaoGerenciaRegional().size()];
			Iterator colecaoGerenciaRegional = parametrosComandoNegativacaoHelper
					.getColecaoGerenciaRegional().iterator();
			int qtdGerenciaRegional = 0;
			while (colecaoGerenciaRegional.hasNext()) {
				GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional
						.next();
				idsGerenciaRegional[qtdGerenciaRegional] = gerenciaRegional
						.getId().toString();
				qtdGerenciaRegional++;
			}
			form.setGerenciaRegional(idsGerenciaRegional);
		} else {
			form.setGerenciaRegional(null);
		}

		// Unidade de Negocio

		if (parametrosComandoNegativacaoHelper.getColecaoUnidadeNegocio() != null) {
			String[] idsUnidadeNegocio = new String[parametrosComandoNegativacaoHelper
					.getColecaoUnidadeNegocio().size()];
			Iterator colecaoUnidadeNegocio = parametrosComandoNegativacaoHelper
					.getColecaoUnidadeNegocio().iterator();
			int qtdUnidadeNegocio = 0;
			while (colecaoUnidadeNegocio.hasNext()) {
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colecaoUnidadeNegocio
						.next();
				idsUnidadeNegocio[qtdUnidadeNegocio] = unidadeNegocio.getId()
						.toString();
				qtdUnidadeNegocio++;
			}
			form.setUnidadeNegocio(idsUnidadeNegocio);
		} else {
			form.setUnidadeNegocio(null);
		}

		// Elo P�lo

		if (parametrosComandoNegativacaoHelper.getColecaoEloPolo() != null) {
			String[] idsEloPolo = new String[parametrosComandoNegativacaoHelper
					.getColecaoEloPolo().size()];
			Iterator colecaoEloPolo = parametrosComandoNegativacaoHelper
					.getColecaoEloPolo().iterator();
			int qtdEloPolo = 0;
			while (colecaoEloPolo.hasNext()) {
				Localidade eloPolo = (Localidade) colecaoEloPolo.next();
				idsEloPolo[qtdEloPolo] = eloPolo.getId().toString();
				qtdEloPolo++;
			}
			form.setEloPolo(idsEloPolo);
		} else {
			form.setEloPolo(null);
		}

		// Localidade Inicial
		if (parametrosComandoNegativacaoHelper.getIdLocInicial() != null) {
			form.setIdLocalidadeInicial(""
					+ parametrosComandoNegativacaoHelper.getIdLocInicial());
			form.setLocalidadeDescricaoInicial(parametrosComandoNegativacaoHelper
					.getLocInicial());
		}

		// Localidade Final
		if (parametrosComandoNegativacaoHelper.getIdLocFinal() != null) {
			form.setIdLocalidadeFinal(""
					+ parametrosComandoNegativacaoHelper.getIdLocFinal());
			form.setLocalidadeDescricaoFinal(parametrosComandoNegativacaoHelper
					.getLocFinal());
		}

		// Setor Comercial Inicial
		if (parametrosComandoNegativacaoHelper.getCodSetComInicial() != null) {
			form.setCodigoSetorComercialInicial(parametrosComandoNegativacaoHelper
					.getCodSetComInicial().toString());
			form.setSetorComercialDescricaoInicial(parametrosComandoNegativacaoHelper
					.getSetComInicial().toString());
		}

		// Setor Comercial Final
		if (parametrosComandoNegativacaoHelper.getCodSetComFinal() != null) {
			form.setCodigoSetorComercialFinal(parametrosComandoNegativacaoHelper
					.getCodSetComFinal().toString());
			form.setSetorComercialDescricaoFinal(parametrosComandoNegativacaoHelper
					.getSetComFinal().toString());
		}
	}

	private void setDadosImovel(
			AtualizarComandoNegativacaoPorCriterioActionForm form,
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		// Id Cliente
		if (parametrosComandoNegativacaoHelper.getIdCliente() != null) {

			form.setIdCliente(parametrosComandoNegativacaoHelper.getIdCliente()
					.toString());
		}

		// Nome Cliente
		if (parametrosComandoNegativacaoHelper.getNomeCliente() != null) {

			form.setDescricaoCliente(parametrosComandoNegativacaoHelper
					.getNomeCliente());
		}

		// Tipo de Rela��o com o Cliente
		if (parametrosComandoNegativacaoHelper.getTipoRelClie() != null) {

			form.setTipoRelacao(parametrosComandoNegativacaoHelper
					.getTipoRelClie());
		}

		// Imovel com Sit. Especial de Cobranca
		if (parametrosComandoNegativacaoHelper.getIndicadorEspCobranca() != null) {

			if (parametrosComandoNegativacaoHelper.getIndicadorEspCobranca()
					.equals(ConstantesSistema.SIM)) {
				form.setImovSitEspecialCobranca(ConstantesSistema.SIM
						.toString());
			} else {
				form.setImovSitEspecialCobranca(ConstantesSistema.NAO
						.toString());
			}
		}

		// Imovel com Sit.de Cobranca
		if (parametrosComandoNegativacaoHelper.getIndicadorSitCobranca() != null) {

			if (parametrosComandoNegativacaoHelper.getIndicadorSitCobranca()
					.equals(ConstantesSistema.SIM)) {
				form.setImovSitCobranca(ConstantesSistema.SIM.toString());
			} else {
				form.setImovSitCobranca(ConstantesSistema.NAO.toString());
			}
		}

		// Im�vel com Baixa Renda
		if (parametrosComandoNegativacaoHelper.getIndicadorBaixaRenda() != null) {

			if (parametrosComandoNegativacaoHelper.getIndicadorBaixaRenda()
					.equals(ConstantesSistema.SIM)) {
				form.setIndicadorBaixaRenda(ConstantesSistema.SIM.toString());
			} else {
				form.setIndicadorBaixaRenda(ConstantesSistema.NAO.toString());
			}
		}

		// Situa��o da Liga��o de �gua
		if (parametrosComandoNegativacaoHelper.getColecaoSitLigacaoAgua() != null) {
			String[] idsSitLigacaoAgua = new String[parametrosComandoNegativacaoHelper
					.getColecaoSitLigacaoAgua().size()];
			Iterator colecaoSitLigacaoAgua = parametrosComandoNegativacaoHelper
					.getColecaoSitLigacaoAgua().iterator();
			int qtdSitLigacaoAgua = 0;
			while (colecaoSitLigacaoAgua.hasNext()) {
				LigacaoAguaSituacao sitLigacaoAgua = (LigacaoAguaSituacao) colecaoSitLigacaoAgua
						.next();
				idsSitLigacaoAgua[qtdSitLigacaoAgua] = sitLigacaoAgua.getId()
						.toString();
				qtdSitLigacaoAgua++;
			}
			form.setLigacaoAguaSituacao(idsSitLigacaoAgua);
		} else {
			form.setLigacaoAguaSituacao(null);
		}

		// Situa��o da Liga��o de Esgoto
		if (parametrosComandoNegativacaoHelper.getColecaoSitLigacaoEsgoto() != null) {
			String[] idsSitLigacaoEsgoto = new String[parametrosComandoNegativacaoHelper
					.getColecaoSitLigacaoEsgoto().size()];
			Iterator colecaoSitLigacaoEsgoto = parametrosComandoNegativacaoHelper
					.getColecaoSitLigacaoEsgoto().iterator();
			int qtdSitLigacaoEsgoto = 0;
			while (colecaoSitLigacaoEsgoto.hasNext()) {
				LigacaoEsgotoSituacao sitLigacaoEsgoto = (LigacaoEsgotoSituacao) colecaoSitLigacaoEsgoto
						.next();
				idsSitLigacaoEsgoto[qtdSitLigacaoEsgoto] = sitLigacaoEsgoto
						.getId().toString();
				qtdSitLigacaoEsgoto++;
			}
			form.setLigacaoEsgotoSituacao(idsSitLigacaoEsgoto);
		} else {
			form.setLigacaoEsgotoSituacao(null);
		}

		// Lista de Subcategoria
		if (parametrosComandoNegativacaoHelper.getColecaoSubcategoria() != null) {
			String[] idsSubcategoria = new String[parametrosComandoNegativacaoHelper
					.getColecaoSubcategoria().size()];
			Iterator colecaoSubcategoria = parametrosComandoNegativacaoHelper
					.getColecaoSubcategoria().iterator();
			int qtdSubcategoria = 0;
			while (colecaoSubcategoria.hasNext()) {
				Subcategoria subCategoria = (Subcategoria) colecaoSubcategoria
						.next();
				idsSubcategoria[qtdSubcategoria] = subCategoria.getId()
						.toString();
				qtdSubcategoria++;
			}
			form.setSubCategoria(idsSubcategoria);
		} else {
			form.setSubCategoria(null);
		}

		// Lista de Perfil Imovel
		if (parametrosComandoNegativacaoHelper.getColecaoPerfilImovel() != null) {
			String[] idsPerfilImovel = new String[parametrosComandoNegativacaoHelper
					.getColecaoPerfilImovel().size()];
			Iterator colecaoPerfilImovel = parametrosComandoNegativacaoHelper
					.getColecaoPerfilImovel().iterator();
			int qtdPerfilImovel = 0;
			while (colecaoPerfilImovel.hasNext()) {
				ImovelPerfil imovelPerfil = (ImovelPerfil) colecaoPerfilImovel
						.next();
				idsPerfilImovel[qtdPerfilImovel] = imovelPerfil.getId()
						.toString();
				qtdPerfilImovel++;
			}
			form.setPerfilImovel(idsPerfilImovel);
		} else {
			form.setPerfilImovel(null);
		}

		// Lista de Tipo de Cliente
		if (parametrosComandoNegativacaoHelper.getColecaoTipoCliente() != null) {
			String[] idsTipoCliente = new String[parametrosComandoNegativacaoHelper
					.getColecaoTipoCliente().size()];
			Iterator colecaoTipoCliente = parametrosComandoNegativacaoHelper
					.getColecaoTipoCliente().iterator();
			int qtdTipoCliente = 0;
			while (colecaoTipoCliente.hasNext()) {
				ClienteTipo clienteTipo = (ClienteTipo) colecaoTipoCliente
						.next();
				idsTipoCliente[qtdTipoCliente] = clienteTipo.getId().toString();
				qtdTipoCliente++;
			}
			form.setTipoCliente(idsTipoCliente);
		} else {
			form.setTipoCliente(null);
		}
		
		// Indicador Categoria Publica
		if (parametrosComandoNegativacaoHelper.getIndicadorOrgaoPublico() != null){
			
			form.setIndicadorImovelCategoriaPublico(parametrosComandoNegativacaoHelper.getIndicadorOrgaoPublico().toString());
		}
	}

	private void setDadosDebitos(
			AtualizarComandoNegativacaoPorCriterioActionForm form,
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		// Periodo de referencia Inicial
		if (parametrosComandoNegativacaoHelper.getReferenciaInicial() != null) {

			form.setReferenciaInicial(Util
					.formatarAnoMesParaMesAno(parametrosComandoNegativacaoHelper
							.getReferenciaInicial()));
		}

		// Periodo de referencia Final
		if (parametrosComandoNegativacaoHelper.getReferenciaFinal() != null) {

			form.setReferenciaFinal(Util
					.formatarAnoMesParaMesAno(parametrosComandoNegativacaoHelper
							.getReferenciaFinal()));
		}

		// Periodo de Vencimento Debito inicial
		if (parametrosComandoNegativacaoHelper.getVencimentoInicial() != null) {

			form.setDataVencimentoInicial(Util
					.formatarData(parametrosComandoNegativacaoHelper
							.getVencimentoInicial()));
		}

		// Periodo de Vencimento Debito Final
		if (parametrosComandoNegativacaoHelper.getVencimentoFinal() != null) {

			form.setDataVencimentoFinal(Util
					.formatarData(parametrosComandoNegativacaoHelper
							.getVencimentoFinal()));
		}

		// Valor Minimo do Debito
		if (parametrosComandoNegativacaoHelper.getValoMinimoDebito() != null) {

			form.setValorDebitoInicial(Util
					.formatarMoedaReal(parametrosComandoNegativacaoHelper
							.getValoMinimoDebito()));
		}

		// Valor Maximo do Debito
		if (parametrosComandoNegativacaoHelper.getValoMaximoDebito() != null) {

			form.setValorDebitoFinal(Util
					.formatarMoedaReal(parametrosComandoNegativacaoHelper
							.getValoMaximoDebito()));
		}

		// Quantidade Minina de Contas
		if (parametrosComandoNegativacaoHelper.getQtdMinimaContas() != null) {

			form.setNumeroContasInicial(parametrosComandoNegativacaoHelper
					.getQtdMinimaContas().toString());
		}

		// Quantidade Maxima de Contas
		if (parametrosComandoNegativacaoHelper.getQtdMaximaContas() != null) {

			form.setNumeroContasFinal(parametrosComandoNegativacaoHelper
					.getQtdMaximaContas().toString());
		}

		// Contas em revisao
		if (parametrosComandoNegativacaoHelper.getIndicadorContaRevisao() != null) {

			if (parametrosComandoNegativacaoHelper.getIndicadorContaRevisao()
					.equals(ConstantesSistema.SIM)) {
				form.setContasRevisao(ConstantesSistema.SIM.toString());
			} else {
				form.setContasRevisao(ConstantesSistema.NAO.toString());
			}
		}

		// Guia de pagamento
		if (parametrosComandoNegativacaoHelper.getIndicadorGuiaPagamento() != null) {

			if (parametrosComandoNegativacaoHelper.getIndicadorGuiaPagamento()
					.equals(ConstantesSistema.SIM)) {
				form.setGuiasPagamento(ConstantesSistema.SIM.toString());
			} else {
				form.setGuiasPagamento(ConstantesSistema.NAO.toString());
			}
		}

		// Parcelamento em Atraso
		if (parametrosComandoNegativacaoHelper.getIndicadorParcelamentoAtraso() != null) {

			if (parametrosComandoNegativacaoHelper
					.getIndicadorParcelamentoAtraso().equals(
							ConstantesSistema.SIM)) {
				form.setParcelaAtraso(ConstantesSistema.SIM.toString());
			} else {
				form.setParcelaAtraso(ConstantesSistema.NAO.toString());
			}
		}

		// Dias em atraso de Parcelamento
		if (parametrosComandoNegativacaoHelper.getNumDiasAtrasoParcelamento() != null) {

			form.setDiasAtrasoParcelamento(parametrosComandoNegativacaoHelper
					.getNumDiasAtrasoParcelamento().toString());
		}

		// Recebeu Carta de Parcelamento em Atraso
		if (parametrosComandoNegativacaoHelper
				.getIndicadorCartaParcelamentoAtraso() != null) {

			if (parametrosComandoNegativacaoHelper
					.getIndicadorCartaParcelamentoAtraso().equals(
							ConstantesSistema.SIM)) {
				form.setCartaParcelamentoAtraso(ConstantesSistema.SIM
						.toString());
			} else {
				form.setCartaParcelamentoAtraso(ConstantesSistema.NAO
						.toString());
			}
		}

		// Dias em Atraso apos Recebimento de Carta
		if (parametrosComandoNegativacaoHelper.getNumDiasAtrasoAposRecCarta() != null) {

			form.setDiasAtrasoRecebimentoCarta(parametrosComandoNegativacaoHelper
					.getNumDiasAtrasoAposRecCarta().toString());
		}
		
		
		if (parametrosComandoNegativacaoHelper.getIndicadorContaNomeCliente() != null) {

			if (parametrosComandoNegativacaoHelper.getIndicadorContaNomeCliente().equals(ConstantesSistema.SIM)) {
				form.setIndicadorContaNomeCliente(ConstantesSistema.SIM.toString());
			} else {
				form.setIndicadorContaNomeCliente(ConstantesSistema.NAO.toString());
			}
		}
		
	}

	private void setDadosGerais(
			HttpSession sessao,
			AtualizarComandoNegativacaoPorCriterioActionForm form,
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper,
			Fachada fachada) {
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Negativador
		if (parametrosComandoNegativacaoHelper.getIdNegativador() != null) {
			form.setNegativadorId(parametrosComandoNegativacaoHelper
					.getIdNegativador().toString());
			form.setNomeNegativador(parametrosComandoNegativacaoHelper
					.getNegativador());
		}

		// Titulo Comando
		if (parametrosComandoNegativacaoHelper.getTituloComando() != null) {

			form.setTitulo(parametrosComandoNegativacaoHelper
					.getTituloComando());
		}

		// Descricao da Solicitacao
		if (parametrosComandoNegativacaoHelper.getDescricaoSolicitacao() != null) {

			form.setSolicitacao(parametrosComandoNegativacaoHelper
					.getDescricaoSolicitacao());
		}

		// Simular Negativacao
		if (parametrosComandoNegativacaoHelper.getSimularNegativacao() != null) {

			if (parametrosComandoNegativacaoHelper.getSimularNegativacao()
					.equals(ConstantesSistema.SIM)) {
				form.setSimular(ConstantesSistema.SIM.toString());
			} else {
				form.setSimular(ConstantesSistema.NAO.toString());
			}
		}

		if (parametrosComandoNegativacaoHelper
				.getIdComandoNegativacaoSimulado() != null) {
			Integer idComandoNegativacao = parametrosComandoNegativacaoHelper
					.getIdComandoNegativacaoSimulado();

			NegativacaoCriterio negativacaoCriterio = fachada
					.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);

			form.setExecutarSimulacao(ConstantesSistema.SIM.toString());
			form.setIdComandoSimulado(idComandoNegativacao.toString());
			form.setDescricaoComandoSimulado(negativacaoCriterio
					.getDescricaoTitulo());

		} else {
			form.setExecutarSimulacao(ConstantesSistema.NAO.toString());
		}

		// Data Prevista p Execucao
		if (parametrosComandoNegativacaoHelper.getDataExecucao() != null) {

			form.setDataPrevista(Util
					.formatarData(parametrosComandoNegativacaoHelper
							.getDataExecucao()));
		}

		// Usuario Responsavel
		if (parametrosComandoNegativacaoHelper.getIdUsuario() != null) {

			form.setUsuario(parametrosComandoNegativacaoHelper.getIdUsuario()
					.toString());
			form.setNomeUsuario(parametrosComandoNegativacaoHelper
					.getUsuarioResponsavel());
		}

		// Quantidade Maxima Inclusoes
		if (parametrosComandoNegativacaoHelper.getQtdMaxInclusoes() != null) {

			form.setQtdMaximaInclusao(parametrosComandoNegativacaoHelper
					.getQtdMaxInclusoes().toString());
		}

		// Titularidade do CPF/CNPJ da Negativacao

		if (parametrosComandoNegativacaoHelper.getColecaoTitularNegativacao() != null
				&& !parametrosComandoNegativacaoHelper
						.getColecaoTitularNegativacao().isEmpty()) {
			sessao.setAttribute("colecaoNegativacaoCriterioCpfTipo",
					parametrosComandoNegativacaoHelper
							.getColecaoTitularNegativacao());
		}
		
		//[FS0020] Verificar permiss�o especial de altera��o de valor
    	boolean alterarSoCPFCNPJValidos = Fachada
    			.getInstancia()
    			.verificarPermissaoEspecial(
    					PermissaoEspecial.ALTERAR_SO_CPF_CNPJ_VALIDOS,
    					usuarioLogado);
    			
    			sessao.setAttribute("alterarSoCPFCNPJValidos",
    					alterarSoCPFCNPJValidos);
    	
    	if(form.getIndicadorCpfCnpjValido() == null && !alterarSoCPFCNPJValidos){
    		form.setIndicadorCpfCnpjValido(ConstantesSistema.CONFIRMADA);
    	}
    	else{
    		form.setIndicadorCpfCnpjValido(parametrosComandoNegativacaoHelper.getIndicadorCpfCnpjValido().toString());
    	}
		
		
		// RM3388 - Por: Ivan Sergio. Analista: Adriana. 28/01/2011
		if (parametrosComandoNegativacaoHelper.getIndicadorOrgaoPublico() != null) {
			if (parametrosComandoNegativacaoHelper.getIndicadorOrgaoPublico().equals(ConstantesSistema.SIM)) {
				form.setIndicadorOrgaoPublico(ConstantesSistema.SIM.toString());
			} else {
				form.setIndicadorOrgaoPublico(ConstantesSistema.NAO.toString());
			}
		}
	}

	private void setDadosExclusao(
			AtualizarComandoNegativacaoPorCriterioActionForm form,
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {

		// Quantidade de Dias
		if (parametrosComandoNegativacaoHelper.getQuantidadeDias() != null) {

			form.setQuantidadeDias(parametrosComandoNegativacaoHelper
					.getQuantidadeDias().toString());
		}

		// Motivo de retorno
		if (parametrosComandoNegativacaoHelper.getColecaoMotivoRetorno() != null) {
			String[] idsMotivoRetorno = new String[parametrosComandoNegativacaoHelper
					.getColecaoMotivoRetorno().size()];
			Iterator colecaoMotivoRetorno = parametrosComandoNegativacaoHelper
					.getColecaoMotivoRetorno().iterator();
			int qtdMotivoRetorno = 0;
			while (colecaoMotivoRetorno.hasNext()) {
				NegativadorRetornoMotivo negativadorRetornoMotivo = (NegativadorRetornoMotivo) colecaoMotivoRetorno
						.next();
				idsMotivoRetorno[qtdMotivoRetorno] = negativadorRetornoMotivo
						.getId().toString();
				qtdMotivoRetorno++;
			}
			form.setMotivoRetorno(idsMotivoRetorno);
		} else {
			form.setMotivoRetorno(null);
		}

	}

}
