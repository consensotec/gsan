/**
 * 
 */
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

package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.GerarExtensaoComandoContasCobrancaEmpresaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0879] Gerar Arquivo Texto das Contas em Cobran�a por Empresa
 * 
 * @author R�mulo Aur�lio
 * @since 02/02/2009
 */

public class ExibirGerarExtensaoComandoContasCobrancaEmpresaAction extends
		GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarExtensaoComandoContasCobrancaEmpresaAction");
		
		String pagina = httpServletRequest.getParameter("page.offset");

		GerarExtensaoComandoContasCobrancaEmpresaActionForm form = (GerarExtensaoComandoContasCobrancaEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(httpServletRequest.getParameter("pesquisarEmpresa") != null ){

			this.pesquisarCampoEnter(httpServletRequest, form, fachada);
			
		}

		if (httpServletRequest.getParameter("limpar") != null) {

			sessao
					.removeAttribute("colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper");

		}

		if (httpServletRequest.getParameter("selecionarComandos") != null || pagina!=null) {
			
			if(pagina==null){
				pagina = "0";
			}
			
			retorno = this.pesquisarExtensaoComandosContas(httpServletRequest, form,
					sessao, retorno);
		}

		return retorno;
	}

	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			GerarExtensaoComandoContasCobrancaEmpresaActionForm form,
			Fachada fachada) {

		String idEmpresa = form.getIdEmpresa();

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
			
			//Validar Empresa
			if(Util.validarValorNaoNumerico(idEmpresa)){
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio",null,"EMPRESA");
			}

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				Empresa empresa = (Empresa) Util
						.retonarObjetoDeColecao(colecaoEmpresa);
				form.setIdEmpresa(empresa.getId().toString());
				form.setNomeEmpresa(empresa.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			} else {
				form.setIdEmpresa("");
				form.setNomeEmpresa("EMPRESA INEXISTENTE");

				httpServletRequest.setAttribute("empresaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			}

		} else {
			form.setNomeEmpresa("");
		}

	}

	private ActionForward pesquisarExtensaoComandosContas(
			HttpServletRequest httpServletRequest,
			GerarExtensaoComandoContasCobrancaEmpresaActionForm form,
			HttpSession sessao, ActionForward retorno) {

		String idEmpresa = form.getIdEmpresa();
		
		ActionForward retorno2 = new ActionForward();
		
		String periodoComandoInicial = form.getPeriodoComandoInicial();

		String periodoComandoFinal = form.getPeriodoComandoFinal();

		Date comandoInicial = null;

		Date comandoFinal = null;
		
		
		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
			
			//Validar Empresa
			if(Util.validarValorNaoNumerico(idEmpresa)){
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio",null,"EMPRESA");
			}

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = this.getFachada().pesquisar(
					filtroEmpresa, Empresa.class.getName());

			if (Util.isVazioOrNulo(colecaoEmpresa)) {

				form.setIdEmpresa("");
				form.setNomeEmpresa("EMPRESA INEXISTENTE");
				httpServletRequest.setAttribute("empresaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
				throw new ActionServletException("atencao.empresa.inexistente");

			}
			else{
				Empresa empresa = (Empresa) Util
						.retonarObjetoDeColecao(colecaoEmpresa);
				form.setIdEmpresa(empresa.getId().toString());
				form.setNomeEmpresa(empresa.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			}
		} else {
			throw new ActionServletException("atencao.empresa.obrigatoria");
		}

		if (periodoComandoFinal != null && !periodoComandoFinal.equals("")
				&& periodoComandoInicial != null
				&& !periodoComandoInicial.equals("")) {
			
			//Validar Per�odo inicial
			if(!this.validarData(periodoComandoInicial)){
				throw new ActionServletException(
						"atencao.data_inicial_execucao.invalida");
			}
			
			//Validar Per�odo final
			if(!this.validarData(periodoComandoFinal)){
				throw new ActionServletException(
						"atencao.data_final_execucao.invalida");
			}

			comandoInicial = Util.converteStringParaDate(periodoComandoInicial);
			comandoFinal = Util.converteStringParaDate(periodoComandoFinal);

			if (comandoInicial.compareTo(comandoFinal) > 0) {
				throw new ActionServletException(
						"atencao.data_inicial_periodo_execucao_comando.posterior.data_final_periodo_execucao_comando");
			}

		}
		
		Integer totalRegistros = this.getFachada()
			.pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaCount(
				new Integer(idEmpresa), comandoInicial, comandoFinal);

		retorno2 = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		Collection<GerarExtensaoComandoContasCobrancaEmpresaHelper> colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper = this
				.getFachada()
				.pesquisarDadosGerarExtensaoComandoContasCobrancaEmpresa(
						new Integer(idEmpresa), comandoInicial, comandoFinal,(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		
		

		if (colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper != null
				&& !colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper
						.isEmpty()) {

			sessao.setAttribute(
					"colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper",
					colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper);

		} else {
			throw new ActionServletException(
					"atencao.nenhum_comando_selecionado_geracao_arquivo");
		}
	
		return retorno2;
		
	}
	
	 //M�todo pra validar a data
    private boolean validarData(String inDate) {
		if (inDate == null) {
			return false;	
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if (inDate.trim().length() != dateFormat.toPattern().length()) {
			return false;	
		}
		
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
			} catch (ParseException pe) {
				return false;
		}
		return true;
	}

}
