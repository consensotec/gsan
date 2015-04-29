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
* Thiago Vieira
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
package gsan.gui.cobranca.spcserasa;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.cliente.FiltroEsferaPoder;
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
import gsan.cobranca.Negativador;
import gsan.cobranca.NegativadorRetornoMotivo;
import gsan.cobranca.bean.DadosConsultaNegativacaoHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.spcserasa.FiltroNegativador;
import gsan.spcserasa.FiltroNegativadorRetornoMotivo;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ConectorOr;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class InformarDadosConsultaNegativacaoAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
  	
    	//localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirResumoNegativacaoParametros");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;
		DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper = new DadosConsultaNegativacaoHelper();
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
		//if (form.getIdNegativador() != null && !form.getIdNegativador().equals("") && new Integer(form.getIdNegativador()) > 0){
		//	dadosConsultaNegativacaoHelper.setIdNegativador(new Integer(form.getIdNegativador()));
		//	dadosConsultaNegativacaoHelper.setNomeNegativador(form.getNomeNegativador());
		//}
		//--------------------------------------------------------------------------------------------------------
		// Negativador
		//--------------------------------------------------------------------------------------------------------
		String[] arrayNegativador = form.getArrayNegativador();		
		Negativador negativadorColecao = new Negativador();
		negativadorColecao.setId(-1);

		Collection colecaoNegativador = new ArrayList();
		int i = 0;		

		Collection colecaoIdNegativador = new ArrayList();
		if (arrayNegativador != null) {
			//negativadorColecao.setCliente("OP��ES SELECIONADAS");
			colecaoNegativador.add(negativadorColecao);
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			for (i = 0; i < arrayNegativador.length; i++) {
				if (!arrayNegativador[i].equals("")
						&& !arrayNegativador[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdNegativador.add(arrayNegativador[i]);
				}
			}

			filtroNegativador.adicionarParametro(new ParametroSimplesIn(
					FiltroNegativador.ID, colecaoIdNegativador));
			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);

			Collection colecaoNegativadorPesquisa = fachada.pesquisar(
					filtroNegativador, Negativador.class.getName());

			if (colecaoNegativadorPesquisa != null
					&& !colecaoNegativadorPesquisa.isEmpty()) {
				colecaoNegativador.addAll(colecaoNegativadorPesquisa);
			}
		} else {
			//negativadorColecao.setDescricao("TODOS");
			colecaoNegativador.add(negativadorColecao);
		}
			
		dadosConsultaNegativacaoHelper.setColecaoNegativador(colecaoNegativador);
		//********************************************************
		
		if (form.getPeriodoEnvioNegativacaoInicio() != null && !form.getPeriodoEnvioNegativacaoInicio().equals("")){
			String periodoEnvioNegativacaoInicio = form.getPeriodoEnvioNegativacaoInicio();
			sessao.setAttribute("periodoEnvioNegativacaoInicio",periodoEnvioNegativacaoInicio);
			dadosConsultaNegativacaoHelper.setPeriodoEnvioNegativacaoInicio(Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoInicio()));  
		}
		
		if (form.getPeriodoEnvioNegativacaoFim() != null && !form.getPeriodoEnvioNegativacaoFim().equals("")){
			String periodoEnvioNegativacaoFim = form.getPeriodoEnvioNegativacaoFim();
			sessao.setAttribute("periodoEnvioNegativacaoFim",periodoEnvioNegativacaoFim);
			dadosConsultaNegativacaoHelper.setPeriodoEnvioNegativacaoFim(Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoFim()));  
		}
		
		if (form.getPeriodoExclusaoNegativacaoInicio() != null && !form.getPeriodoExclusaoNegativacaoInicio().equals("")){
			String periodoExclusaoNegativacaoInicio = form.getPeriodoExclusaoNegativacaoInicio();
			sessao.setAttribute("periodoExclusaoNegativacaoInicio",periodoExclusaoNegativacaoInicio);
			dadosConsultaNegativacaoHelper.setPeriodoExclusaoNegativacaoInicio(Util.converteStringParaDate(form.getPeriodoExclusaoNegativacaoInicio()));  
		}
		
		if (form.getPeriodoExclusaoNegativacaoFim() != null && !form.getPeriodoExclusaoNegativacaoFim().equals("")){
			String periodoExclusaoNegativacaoFim = form.getPeriodoExclusaoNegativacaoFim();
			sessao.setAttribute("periodoExclusaoNegativacaoFim",periodoExclusaoNegativacaoFim);
			dadosConsultaNegativacaoHelper.setPeriodoExclusaoNegativacaoFim(Util.converteStringParaDate(form.getPeriodoExclusaoNegativacaoFim()));  
		}		
		
		if (dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoInicio() != null && dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoFim() != null){
			if (dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoFim().before(dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoInicio())) {
				throw new ActionServletException("atencao.data_final_periodo_negativacao_anterior_data_inicial");
			}
		}
		

		if (dadosConsultaNegativacaoHelper.getPeriodoExclusaoNegativacaoInicio() != null && dadosConsultaNegativacaoHelper.getPeriodoExclusaoNegativacaoFim() != null){
			if (dadosConsultaNegativacaoHelper.getPeriodoExclusaoNegativacaoFim().before(dadosConsultaNegativacaoHelper.getPeriodoExclusaoNegativacaoInicio())) {
				throw new ActionServletException("atencao.data_final_periodo_negativacao_anterior_data_inicial");
			}
		}
		
		
		
		if (form.getIdNegativadorExclusaoMotivo() != null && !form.getIdNegativadorExclusaoMotivo().equals("") && new Integer(form.getIdNegativadorExclusaoMotivo()) > 0){
			dadosConsultaNegativacaoHelper.setIdNegativadorExclusaoMotivo(new Integer(form.getIdNegativadorExclusaoMotivo()));			
		}
		
		
		if (form.getIdNegativacaoComando() != null && !form.getIdNegativacaoComando().equals("")){
			dadosConsultaNegativacaoHelper.setIdNegativacaoComando(new Integer(form.getIdNegativacaoComando()));
		}
	
		//--------------------------------------------------------------------------------------------------------
		//Grupo Cobran�a
		//--------------------------------------------------------------------------------------------------------
		String[] arrayCobrancaGrupo = form.getArrayCobrancaGrupo();		
		CobrancaGrupo cobrancaGrupoColecao = new CobrancaGrupo();
		cobrancaGrupoColecao.setId(-1);

		Collection colecaoCobrancaGrupo = new ArrayList();
		
		if (arrayCobrancaGrupo != null) {
			cobrancaGrupoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			for (i = 0; i < arrayCobrancaGrupo.length; i++) {
				if (!arrayCobrancaGrupo[i].equals("")
						&& !arrayCobrancaGrupo[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayCobrancaGrupo.length) {
						filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
										FiltroCobrancaGrupo.ID, arrayCobrancaGrupo[i],
										ConectorOr.CONECTOR_OR,
										arrayCobrancaGrupo.length));

					} else {
						filtroCobrancaGrupo
								.adicionarParametro(new ParametroSimples(
										FiltroCobrancaGrupo.ID, arrayCobrancaGrupo[i]));
					}
				}
			}

			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

			Collection colecaoCobrancaGrupoPesquisa = fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecaoCobrancaGrupoPesquisa != null
					&& !colecaoCobrancaGrupoPesquisa.isEmpty()) {
				colecaoCobrancaGrupo.addAll(colecaoCobrancaGrupoPesquisa);
			}
		} else {
			cobrancaGrupoColecao.setDescricao("TODOS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
		}
			
		dadosConsultaNegativacaoHelper.setColecaoCobrancaGrupo(colecaoCobrancaGrupo);
		
		//--------------------------------------------------------------------------------------------------------

		if (form.getIdEloPolo() != null && !form.getIdEloPolo().equals("") && new Integer(form.getIdEloPolo()) > 0){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdEloPolo()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("pesquisa.elo.inexistente");
			} else {
				dadosConsultaNegativacaoHelper.setIdEloPolo(new Integer(form.getIdEloPolo()));
			}
		}
		
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("") && new Integer(form.getIdLocalidade()) > 0){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("pesquisa.localidade.inexistente");
			} else {
				dadosConsultaNegativacaoHelper.setIdLocalidade(new Integer(form.getIdLocalidade()));
			}
		}
		
		if (form.getIdSetorComercial() != null && !form.getIdSetorComercial().equals("") && new Integer(form.getIdSetorComercial()) > 0){
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("atencao.setor_comercial.inexistente");
			} else {
				dadosConsultaNegativacaoHelper.setIdSetorComercial(new Integer(form.getIdSetorComercial()));
			}
		}
		
		if (form.getIdQuadra() != null && !form.getIdQuadra().equals("") && new Integer(form.getIdQuadra()) > 0){
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercial()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, form.getIdQuadra()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("atencao.quadra.inexistente");
			} else {
				dadosConsultaNegativacaoHelper.setIdQuadra(new Integer(form.getIdQuadra()));
			}
		}

		
		//************************************************************************************************
//		--------------------------------------------------------------------------------------------------------
		//Ger�ncia Regional
		//--------------------------------------------------------------------------------------------------------
		String[] arrayGerenciaRegional = form.getArrayGerenciaRegional();		
		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);

		Collection colecaoGerenciaRegional = new ArrayList();
		int j = 0;		
		
		if (arrayGerenciaRegional != null) {
			gerenciaRegionalColecao.setNome("OP��ES SELECIONADAS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			for (j = 0; j < arrayGerenciaRegional.length; j++) {
				if (!arrayGerenciaRegional[j].equals("")
						&& !arrayGerenciaRegional[j].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (j + 1 < arrayGerenciaRegional.length) {
						filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID, arrayGerenciaRegional[j],
										ConectorOr.CONECTOR_OR,
										arrayGerenciaRegional.length));

					} else {
						filtroGerenciaRegional
								.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID, arrayGerenciaRegional[j]));
					}
				}
			}

			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);

			Collection colecaoGerenciaRegionalPesquisa = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegionalPesquisa != null
					&& !colecaoGerenciaRegionalPesquisa.isEmpty()) {
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);
			}
		} else {
			gerenciaRegionalColecao.setNome("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}		
		
		dadosConsultaNegativacaoHelper.setColecaoGerenciaRegional(colecaoGerenciaRegional);
		//--------------------------------------------------------------------------------------------------------
		//Unidade de Neg�cio
		//--------------------------------------------------------------------------------------------------------
		String[] arrayUnidadeNegocio = form.getArrayUnidadeNegocio();		
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);

		Collection colecaoUnidadeNegocio = new ArrayList();
		int l = 0;		
		
		if (arrayUnidadeNegocio != null) {
			unidadeNegocioColecao.setNome("OP��ES SELECIONADAS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			for (l = 0; l < arrayUnidadeNegocio.length; l++) {
				if (!arrayUnidadeNegocio[l].equals("")
						&& !arrayUnidadeNegocio[l].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (l + 1 < arrayUnidadeNegocio.length) {
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
										FiltroUnidadeNegocio.ID, arrayUnidadeNegocio[l],
										ConectorOr.CONECTOR_OR,
										arrayUnidadeNegocio.length));

					} else {
						filtroUnidadeNegocio
								.adicionarParametro(new ParametroSimples(
										FiltroUnidadeNegocio.ID, arrayUnidadeNegocio[l]));
					}
				}
			}

			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			Collection colecaoUnidadeNegocioPesquisa = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocioPesquisa != null
					&& !colecaoUnidadeNegocioPesquisa.isEmpty()) {
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		} else {
			unidadeNegocioColecao.setNome("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoUnidadeNegocio(colecaoUnidadeNegocio);
		//--------------------------------------------------------------------------------------------------------
		//Perfil Im�vel
		//--------------------------------------------------------------------------------------------------------
		String[] arrayImovelPerfil = form.getArrayImovelPerfil();		
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection colecaoImovelPerfil = new ArrayList();
		int m = 0;		
		
		if (arrayImovelPerfil != null) {
			imovelPerfilColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			for (m = 0; m < arrayImovelPerfil.length; m++) {
				if (!arrayImovelPerfil[m].equals("")
						&& !arrayImovelPerfil[m].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (m + 1 < arrayImovelPerfil.length) {
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(
										FiltroImovelPerfil.ID, arrayImovelPerfil[m],
										ConectorOr.CONECTOR_OR,
										arrayImovelPerfil.length));

					} else {
						filtroImovelPerfil
								.adicionarParametro(new ParametroSimples(
										FiltroImovelPerfil.ID, arrayImovelPerfil[m]));
					}
				}
			}

			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

			Collection colecaoImovelPerfilPesquisa = fachada.pesquisar(
					filtroImovelPerfil, ImovelPerfil.class.getName());

			if (colecaoImovelPerfilPesquisa != null
					&& !colecaoImovelPerfilPesquisa.isEmpty()) {
				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
			}
		} else {
			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoImovelPerfil(colecaoImovelPerfil);
		//--------------------------------------------------------------------------------------------------------
		//Categoria
		//--------------------------------------------------------------------------------------------------------
		String[] arrayCategoria = form.getArrayCategoria();		
		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);

		Collection colecaoCategoria = new ArrayList();
		int n = 0;		
		
		if (arrayCategoria != null) {
			categoriaColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoCategoria.add(categoriaColecao);
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			for (n = 0; n < arrayCategoria.length; n++) {
				if (!arrayCategoria[n].equals("")
						&& !arrayCategoria[n].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (n + 1 < arrayCategoria.length) {
						filtroCategoria.adicionarParametro(new ParametroSimples(
										FiltroCategoria.CODIGO, arrayCategoria[n],
										ConectorOr.CONECTOR_OR,
										arrayCategoria.length));

					} else {
						filtroCategoria
								.adicionarParametro(new ParametroSimples(
										FiltroCategoria.CODIGO, arrayCategoria[n]));
					}
				}
			}

			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

			Collection colecaoCategoriaPesquisa = fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (colecaoCategoriaPesquisa != null
					&& !colecaoCategoriaPesquisa.isEmpty()) {
				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
			}
		} else {
			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoCategoria(colecaoCategoria);
		//--------------------------------------------------------------------------------------------------------
		//TipoCliente
		//--------------------------------------------------------------------------------------------------------
		String[] arrayTipoCliente = form.getArrayTipoCliente();		
		ClienteTipo tipoClienteColecao = new ClienteTipo();
		tipoClienteColecao.setId(-1);

		Collection colecaoTipoCliente = new ArrayList();
		int o = 0;		
		
		if (arrayTipoCliente != null) {
			tipoClienteColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoTipoCliente.add(tipoClienteColecao);
			FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

			for (o = 0; o < arrayTipoCliente.length; o++) {
				if (!arrayTipoCliente[o].equals("")
						&& !arrayTipoCliente[o].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (o + 1 < arrayTipoCliente.length) {
						filtroClienteTipo.adicionarParametro(new ParametroSimples(
										FiltroClienteTipo.ID, arrayTipoCliente[o],
										ConectorOr.CONECTOR_OR,
										arrayTipoCliente.length));

					} else {
						filtroClienteTipo
								.adicionarParametro(new ParametroSimples(
										FiltroClienteTipo.ID, arrayTipoCliente[o]));
					}
				}
			}

			filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

			Collection colecaoTipoClientePesquisa = fachada.pesquisar(
					filtroClienteTipo, ClienteTipo.class.getName());

			if (colecaoTipoClientePesquisa != null
					&& !colecaoTipoClientePesquisa.isEmpty()) {
				colecaoTipoCliente.addAll(colecaoTipoClientePesquisa);
			}
		} else {
			tipoClienteColecao.setDescricao("TODOS");
			colecaoTipoCliente.add(tipoClienteColecao);
		}
	
		dadosConsultaNegativacaoHelper.setColecaoClienteTipo(colecaoTipoCliente);
		//--------------------------------------------------------------------------------------------------------
		//Esfera Poder
		//--------------------------------------------------------------------------------------------------------
		String[] arrayEsferaPoder = form.getArrayEsferaPoder();		
		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);

		Collection colecaoEsferaPoder = new ArrayList();
		int p = 0;		
		
		if (arrayEsferaPoder != null) {
			esferaPoderColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoEsferaPoder.add(esferaPoderColecao);
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			for (p = 0; p < arrayEsferaPoder.length; p++) {
				if (!arrayEsferaPoder[p].equals("")
						&& !arrayEsferaPoder[p].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (p + 1 < arrayEsferaPoder.length) {
						filtroEsferaPoder.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, arrayEsferaPoder[p],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroEsferaPoder
								.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, arrayEsferaPoder[p]));
					}
				}
			}

			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);

			Collection colecaoEsferaPoderPesquisa = fachada.pesquisar(
					filtroEsferaPoder, EsferaPoder.class.getName());

			if (colecaoEsferaPoderPesquisa != null
					&& !colecaoEsferaPoderPesquisa.isEmpty()) {
				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
			}
		} else {
			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}

		dadosConsultaNegativacaoHelper.setColecaoEsferaPoder(colecaoEsferaPoder);
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
		//--------------------------------------------------------------------------------------------------------
		// Ligacao Agua Situacao
		//--------------------------------------------------------------------------------------------------------
		String[] arrayLigacaoAguaSituacao = form.getArrayLigacaoAguaSituacao();		
		LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection colecaoLigacaoAguaSituacao = new ArrayList();
		int x = 0;		
		
		if (arrayLigacaoAguaSituacao != null) {
			ligacaoAguaSituacaoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			
			for (x = 0; x < arrayLigacaoAguaSituacao.length; x++) {
				if (!arrayLigacaoAguaSituacao[x].equals("")
						&& !arrayLigacaoAguaSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (x + 1 < arrayLigacaoAguaSituacao.length) {
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID, arrayLigacaoAguaSituacao[x],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroLigacaoAguaSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID, arrayLigacaoAguaSituacao[x]));
					}
				}
			}
			
			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
			
			Collection colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (colecaoLigacaoAguaSituacaoPesquisa != null
					&& !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoLigacaoAguaSituacao(colecaoLigacaoAguaSituacao);
		
		//--------------------------------------------------------------------------------------------------------
		// Ligacao Esgoto Situacao
		//--------------------------------------------------------------------------------------------------------
		String[] arrayLigacaoEsgotoSituacao = form.getArrayLigacaoEsgotoSituacao();		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacaoColecao.setId(-1);

		Collection colecaoLigacaoEsgotoSituacao = new ArrayList();
		
		if (arrayLigacaoEsgotoSituacao != null) {
			ligacaoEsgotoSituacaoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			
			for (x = 0; x < arrayLigacaoEsgotoSituacao.length; x++) {
				if (!arrayLigacaoEsgotoSituacao[x].equals("")
						&& !arrayLigacaoEsgotoSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (x + 1 < arrayLigacaoEsgotoSituacao.length) {
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID, arrayLigacaoEsgotoSituacao[x],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroLigacaoEsgotoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID, arrayLigacaoEsgotoSituacao[x]));
					}
				}
			}
			
			filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
			
			Collection colecaoLigacaoEsgotoSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());

			if (colecaoLigacaoEsgotoSituacaoPesquisa != null
					&& !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}
		
		dadosConsultaNegativacaoHelper.setColecaoLigacaoEsgotoSituacao(colecaoLigacaoEsgotoSituacao);
		
		//************************************************************************************************
		if(form.getIndicadorRelAcompanhamentoClientesNegativados()!= null && 
				form.getIndicadorRelAcompanhamentoClientesNegativados().equals("sim")){
			//--------------------------------------------------------------------------------------------------------
			//Motivo de Rejei��o
			//--------------------------------------------------------------------------------------------------------
			String[] arrayMotivoRejeicao = form.getArrayMotivoRejeicao();		
			NegativadorRetornoMotivo negativadorRetornoMotivoColecao = new NegativadorRetornoMotivo();
			negativadorRetornoMotivoColecao.setId(-1);
	
			Collection colecaoMotivoRejeicao = new ArrayList();
			int t = 0;		
			
			if (arrayMotivoRejeicao != null) {
				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("OP��ES SELECIONADAS");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
				FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
				filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, new Short("2") ));
				filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, colecaoIdNegativador));
				
				for (t = 0; t < arrayMotivoRejeicao.length; t++) {
					if (!arrayMotivoRejeicao[t].equals("")
						&& !arrayMotivoRejeicao[t].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
						if (t + 1 < arrayMotivoRejeicao.length) {
							filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
											FiltroEsferaPoder.ID, arrayMotivoRejeicao[t],
											ConectorOr.CONECTOR_OR,	arrayMotivoRejeicao.length));
	
						} else {
							filtroNegativadorRetornoMotivo.adicionarParametro(
									new ParametroSimples(FiltroEsferaPoder.ID, arrayMotivoRejeicao[t]));
						}
					}
				}
	
				filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO);
	
				Collection colecaoNegativadorRetornoMotivoPesquisa = fachada.pesquisar(
						filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
	
				if (colecaoNegativadorRetornoMotivoPesquisa != null
						&& !colecaoNegativadorRetornoMotivoPesquisa.isEmpty()) {
					colecaoMotivoRejeicao.addAll(colecaoNegativadorRetornoMotivoPesquisa);
				}
			} else {
				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("TODOS");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
			}
	
			dadosConsultaNegativacaoHelper.setColecaoMotivoRejeicao(colecaoMotivoRejeicao);
			if(form.getIndicadorApenasNegativacoesRejeitadas() != null &&
					!form.getIndicadorApenasNegativacoesRejeitadas().equals("")){
				dadosConsultaNegativacaoHelper.setIndicadorApenasNegativacoesRejeitadas(new Short(form.getIndicadorApenasNegativacoesRejeitadas()));
			}
		}
		//************************************************************************************************
		
		sessao.setAttribute("dadosConsultaNegativacaoHelper",dadosConsultaNegativacaoHelper);
		
		if(sessao.getAttribute("gerarRelatorio").equals("nao")){			
			retorno = actionMapping.findForward("exibirResumoNegativacaoParametros");
				
		}else if(sessao.getAttribute("gerarRelatorio").equals("relatorioAcompanhamentoClientesNegativados")){			
			retorno = actionMapping.findForward("gerarRelatorioAcompanhamentoClientesNegativados");
				
		}else if(sessao.getAttribute("gerarRelatorio").equals("relatorioNegativacoesExcluidas")){			
			retorno = actionMapping.findForward("gerarRelatorioNegativacoesExcluidas");
				
		}
		
		
		
		return retorno;
        
    }
}