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
* Anderson Italo Felinto de Lima
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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**			
 * @date 20/10/09
 * @author Anderson Italo
 * UC0960 Transferir Rotas Entre Grupos e/ou Empresas
 */
public class TransferirRotaGrupoEmpresaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");
		
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		TransferirRotasGruposEmpresasActionForm form = (TransferirRotasGruposEmpresasActionForm) actionForm;
		
		FaturamentoGrupo faturamentoGrupoDestino = null;
		CobrancaGrupo cobrancaGrupoDestino = null;
		Empresa empresaFaturamentoDestino = null;
		Empresa empresaCobrancaDestino = null;
		Collection colecaoRotas = (Collection)sessao.getAttribute("rotasSelecionadas");
		
		/*7.1.	Caso n�o tenha sido informado algum dos par�metros 
		 * para onde devem ser transferidas as rotas, este campo 
		 * n�o deve ser alterado.*/
		
		if (form.getIdGrupoFaturamentoDestino() != null && !form.getIdGrupoFaturamentoDestino().equals("")
				&& !form.getIdGrupoFaturamentoDestino().equals("-1")){
			
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID , new Integer(form.getIdGrupoFaturamentoDestino())));
			Collection colecaoGrupoFaturamento = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			
			faturamentoGrupoDestino = (FaturamentoGrupo)Util.retonarObjetoDeColecao(colecaoGrupoFaturamento);
		}
		
		if (form.getIdGrupoCobrancaDestino() != null && !form.getIdGrupoCobrancaDestino().equals("")
				&& !form.getIdGrupoCobrancaDestino().equals("-1")){
			

			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID , new Integer(form.getIdGrupoCobrancaDestino())));
			Collection colecaoGrupoCobranca = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			
			cobrancaGrupoDestino = (CobrancaGrupo)Util.retonarObjetoDeColecao(colecaoGrupoCobranca);

		}
		
		if (form.getIdEmpresaFaturamentoDestino() != null && !form.getIdEmpresaFaturamentoDestino().equals("")
				&& !form.getIdEmpresaFaturamentoDestino().equals("-1")){
			
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID , new Integer(form.getIdEmpresaFaturamentoDestino())));
			Collection colecaoEmpresaFaturamento = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			empresaFaturamentoDestino = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresaFaturamento);
		}
		
		if (form.getIdEmpresaCobrancaDestino() != null && !form.getIdEmpresaCobrancaDestino().equals("")
				&& !form.getIdEmpresaCobrancaDestino().equals("-1")){
			
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID , new Integer(form.getIdEmpresaCobrancaDestino())));
			Collection colecaoEmpresaCobranca = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			empresaCobrancaDestino = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresaCobranca);
		}
		
		//7. O sistema faz a transfer�ncia de rotas solicitadas
		fachada.transferirRotasEntreGrupoEmpresa(faturamentoGrupoDestino, cobrancaGrupoDestino, 
				empresaFaturamentoDestino, empresaCobrancaDestino, colecaoRotas, (Usuario)sessao.getAttribute("usuarioLogado"));
		
		
		//limpa os dados do formul�rio
		form.setIdLocalidadeInicial(null);
		form.setDescricaoLocalidadeInicial(null);
		form.setIdLocalidadeFinal(null);
		form.setDescricaoLocalidadeFinal(null);
		form.setIdLocalidadeInicial(null);
		form.setDescricaoLocalidadeFinal(null);
		form.setIdSetorComercialInicial(null);
		form.setDescricaoSetorComercialInicial(null);
		form.setIdSetorComercialFinal(null);
		form.setDescricaoSetorComercialFinal(null);
		form.setIdRotaInicial(null);
		form.setIdRotaFinal(null);
		form.setQuantidadeRotas(null);
		form.setIdGrupoCobrancaFiltro(null);
		form.setIdGrupoCobrancaSelecao(null);
		form.setIdGrupoCobrancaDestino(null);
		form.setIdGrupoFaturamentoFiltro(null);
		form.setIdGrupoFaturamentoSelecao(null);
		form.setIdGrupoFaturamentoDestino(null);
		form.setIdEmpresaCobrancaFiltro(null);
		form.setIdEmpresaCobrancaSelecao(null);
		form.setIdEmpresaCobrancaDestino(null);
		form.setIdEmpresaFaturamentoFiltro(null);
		form.setIdEmpresaFaturamentoSelecao(null);
		form.setIdEmpresaFaturamentoDestino(null);
		
		sessao.removeAttribute("rotasSelecionadas");
		httpServletRequest.removeAttribute("colecaoEmpresaCobrancaSelecao");
		httpServletRequest.removeAttribute("colecaoEmpresaFaturamentoSelecao");
		httpServletRequest.removeAttribute("colecaoGrupoCobrancaSelecao");
		httpServletRequest.removeAttribute("colecaoGrupoFaturamentoSelecao");
		httpServletRequest.removeAttribute("colecaoUnidadeNegocio");
		httpServletRequest.removeAttribute("colecaoGerenciaRegional");
		httpServletRequest.removeAttribute("colecaoEmpresaFaturamentoFiltro");
		httpServletRequest.removeAttribute("colecaoGrupoFaturamentoFiltro");
		httpServletRequest.removeAttribute("colecaoGrupoCobrancaFiltro");
		httpServletRequest.removeAttribute("colecaoCobrancaGrupoDestino");
		httpServletRequest.removeAttribute("colecaoFaturamentoGrupoDestino");
		httpServletRequest.removeAttribute("colecaoEmpresaDestino");
		httpServletRequest.removeAttribute("colecaoEmpresaFaturamentoDestino");
		httpServletRequest.removeAttribute("colecaoEmpresaCobrancaDestino");
		
		montarPaginaSucesso(httpServletRequest, "Rota(s) Tranferida(s) com sucesso.",
	                "Realizar outra Tranferencia de Rota(s)",
	                "exibirTransferirRotaGrupoEmpresaAction.do?desfazer=S");
		
		return retorno;
	}

}
