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
package gsan.gui.cobranca.cobrancaporresultado;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cobranca.cobrancaporresultado.ConsultarComandosContasCobrancaEmpresaHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1167] Consultar Comandos de Cobran�a por Empresa
 * 
 * @author Mariana Victor
 * @since 03/05/2011
 */
public class ExibirConsultarComandosContasCobrancaEmpresaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarComandosContasCobrancaEmpresa");
		
		String pagina = httpServletRequest.getParameter("page.offset");
	
		ConsultarComandosContasCobrancaEmpresaActionForm form = (ConsultarComandosContasCobrancaEmpresaActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
	
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o usuario que est� logado na aplica��o
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");		

		if (httpServletRequest.getParameter("selecionarComandos") != null || pagina!=null) {
			if(pagina==null){
				pagina = "0";
			}
			
			retorno = this.pesquisarComandosContas(httpServletRequest, form, fachada,
					sessao,pagina,retorno);
		}
		
		if (fachada.verificarPermissaoEspecial(PermissaoEspecial.ENCERRAR_COMANDO_COBRANCA_EMPRESA, usuarioLogado)) {
			sessao.setAttribute("permissaoEspecialEncerrarComando", true);
		} else {
			sessao.removeAttribute("permissaoEspecialEncerrarComando");
		}
	
		if (httpServletRequest.getParameter("limpar") != null 
				&& httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim")) {
	
			sessao.removeAttribute("colecaoConsultarComandosContasCobrancaEmpresaHelper");
			
		}	
		
		this.pesquisarCampoEnter(httpServletRequest, form, fachada);
	
		return retorno;
	}
	
	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			ConsultarComandosContasCobrancaEmpresaActionForm form,
			Fachada fachada) {
	
		String idEmpresa = form.getIdEmpresa();
	
		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
	
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
	
	private ActionForward pesquisarComandosContas(HttpServletRequest httpServletRequest,
			ConsultarComandosContasCobrancaEmpresaActionForm form,
			Fachada fachada, HttpSession sessao, String pagina, ActionForward retorno) {

		String idEmpresa = form.getIdEmpresa();
		
		ActionForward retorno2 = new ActionForward();

		
		String periodoExecucaoInicial = form.getPeriodoCicloInicial();

		String periodoExecucaoFinal = form.getPeriodoCicloFinal();

		Date execucaoInicial = null;
		Date execucaoFinal = null;

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
				throw new ActionServletException("atencao.empresa.inexistente");

			}
		}

		if((periodoExecucaoInicial == null || periodoExecucaoInicial.equals("")) && 
				(periodoExecucaoFinal !=null && !periodoExecucaoFinal.equals(""))){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Per�odo Inicial");
			}
			if((periodoExecucaoFinal == null || periodoExecucaoFinal.equals("")) && 
					(periodoExecucaoInicial !=null && !periodoExecucaoInicial.equals(""))){
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Per�odo Final");
			}
			
			if (periodoExecucaoFinal != null && !periodoExecucaoFinal.equals("")
					&& periodoExecucaoInicial != null && !periodoExecucaoInicial.equals("")) {
									
					Boolean b1 = Util.verificaSeDataValida(periodoExecucaoInicial, "dd/MM/yyyy");
					
					if(b1){
						execucaoInicial = Util.converteStringParaDate(periodoExecucaoInicial);
						
						b1 = Util.verificaSeDataValida(periodoExecucaoFinal, "dd/MM/yyyy");
						if(b1){
							execucaoFinal = Util.converteStringParaDate(periodoExecucaoFinal);
						}
						else{
							throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Per�odo Final V�lido");
						}
					}
					else{
						throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Per�odo Inicial V�lido");
					}

					if (execucaoInicial !=null && execucaoFinal!=null && execucaoInicial.compareTo(execucaoFinal) > 0) {
						throw new ActionServletException("atencao.data_final_periodo.anterior.data_inicial_periodo");
					}

				}

		Integer totalRegistros = fachada
				.pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaCount(
						new Integer(idEmpresa), execucaoInicial, execucaoFinal);

		retorno2 = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);
		
		Collection<ConsultarComandosContasCobrancaEmpresaHelper> colecaoConsultarComandosContasCobrancaEmpresaHelper = fachada
				.pesquisarDadosConsultarComandosContasCobrancaEmpresa(
						new Integer(idEmpresa), execucaoInicial, execucaoFinal,(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		if(colecaoConsultarComandosContasCobrancaEmpresaHelper !=null 
				&& !colecaoConsultarComandosContasCobrancaEmpresaHelper.isEmpty()){
		
			sessao.setAttribute("dataInicial",execucaoInicial);	
			sessao.setAttribute("dataFinal",execucaoFinal);	
			sessao.setAttribute("colecaoConsultarComandosContasCobrancaEmpresaHelper",
				colecaoConsultarComandosContasCobrancaEmpresaHelper);
		}else{
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno2;
	}
}
