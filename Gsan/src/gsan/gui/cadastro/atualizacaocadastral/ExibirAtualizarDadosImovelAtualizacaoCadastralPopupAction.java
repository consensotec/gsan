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
* Ivan S�rgio Virginio da Silva J�nior
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
package gsan.gui.cadastro.atualizacaocadastral;

import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para exibir a p�gina de Atualizacao Cadastral
 * 
 * @author Ivan Sergio
 * @created 02/06/2009
 */
public class ExibirAtualizarDadosImovelAtualizacaoCadastralPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da A��o
		ActionForward retorno = 
			actionMapping.findForward("exibirAtualizarDadosImovelAtualizacaoCadastralPopup");
		
		// Cria a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Obt�m o action form
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm atualizacaoCadastralActionForm =
			(ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

		// Obt�m a fachada
		Fachada fachada = Fachada.getInstancia();
		
		String idImovel = (String) httpServletRequest.getParameter("idImovel");
		String idCliente = (String) httpServletRequest.getParameter("idCliente");
		String idArquivo = (String) httpServletRequest.getParameter("idArquivo");
		Collection colecaoDadosTabelaAtualizacaoCadastral = null;
		String idRegistroAlterado = (String) httpServletRequest.getParameter("idRegistroAlterado");
		String idTipoAlteracao = (String) httpServletRequest.getParameter("idTipoAlteracao");
		String idImovelAtualizacaoCadastral = (String) httpServletRequest.getParameter("idImovelAtlzCadastral");
		
		//Setar o ID Imovel Atualiza��o Cadastral no form
		atualizacaoCadastralActionForm.setIdImovelAtualizacaoCadastral(idImovelAtualizacaoCadastral);
		
		//Desabilitar o checkbox e o bot�o de confirma 
		if(idCliente != null && idCliente.equals("0") && 
				idImovelAtualizacaoCadastral != null && !idImovelAtualizacaoCadastral.equals("")){
			httpServletRequest.setAttribute("desabilita", true);
		}else{
			httpServletRequest.removeAttribute("desabilita");
			
			if(idImovelAtualizacaoCadastral != null && !idImovelAtualizacaoCadastral.equals("")){
				httpServletRequest.setAttribute("marcados",true);
			}else{
				httpServletRequest.removeAttribute("marcados");
			}
		}
		
		// Realiza o Filtro para o Imovel
		if ( (idImovel != null && !idImovel.equals("")) || (idCliente != null && !idCliente.equals("")) ) {
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			
			if (idImovel != null && !idImovel.equals("")) {
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
				//idRegistroAlterado = new Integer(idImovel);
			}
			
			if (idCliente != null && !idCliente.equals("")) {
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, idCliente));
				//idRegistroAlterado = new Integer(idCliente);
			}
			
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.LOCALIDADE);
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.SETOR_COMERCIAL);
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.QUADRA);
			
			ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(fachada.pesquisar(
					filtroClienteImovel, ClienteImovel.class.getName()));
			
			if (clienteImovel != null) {
				Imovel imovel = clienteImovel.getImovel();
				Localidade localidade = (Localidade) imovel.getLocalidade();
				SetorComercial setorComercial = (SetorComercial) imovel.getSetorComercial();
				Quadra quadra = (Quadra) imovel.getQuadra();
				
				// Imovel
				atualizacaoCadastralActionForm.setIdImovel(imovel.getId().toString());
				// Localidade
				atualizacaoCadastralActionForm.setIdLocalidade(localidade.getId().toString());
				atualizacaoCadastralActionForm.setDescricaoLocalidade(localidade.getDescricao());
				// Setor Comercial
				atualizacaoCadastralActionForm.setIdSetorComercial(setorComercial.getId().toString());
				atualizacaoCadastralActionForm.setCodigoSetorComercial("" + setorComercial.getCodigo());
				atualizacaoCadastralActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());
				// Quadra
				atualizacaoCadastralActionForm.setIdQuadra(quadra.getId().toString());
				atualizacaoCadastralActionForm.setNumeroQuadra("" + quadra.getNumeroQuadra());
			}else {
				// Imovel
				atualizacaoCadastralActionForm.setIdImovel("Incluido");
				// Localidade
				atualizacaoCadastralActionForm.setIdLocalidade(null);
				atualizacaoCadastralActionForm.setDescricaoLocalidade(null);
				// Setor Comercial
				atualizacaoCadastralActionForm.setIdSetorComercial(null);
				atualizacaoCadastralActionForm.setCodigoSetorComercial(null);
				atualizacaoCadastralActionForm.setDescricaoSetorComercial(null);
				// Quadra
				atualizacaoCadastralActionForm.setIdQuadra(null);
				atualizacaoCadastralActionForm.setNumeroQuadra(null);
/*				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"Dados do Imovel");*/
			}
		}else {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Dados do Imovel e Cliente");
		}
		
		// Consulta os dados da Tabela Atualizacao Cadastral
		Integer arquivo = new Integer(idArquivo);
		if(idCliente != null && !idCliente.equals("")){
			colecaoDadosTabelaAtualizacaoCadastral = fachada.consultarDadosTabelaColunaAtualizacaoCadastral(
					new Integer(idRegistroAlterado), arquivo, new Integer(idImovel), new Integer(idCliente),new Integer(idTipoAlteracao));
		}else{
			colecaoDadosTabelaAtualizacaoCadastral = fachada.consultarDadosTabelaColunaAtualizacaoCadastral(
					new Integer(idRegistroAlterado), arquivo, new Integer(idImovel), null,new Integer(idTipoAlteracao));
		}
		
		if (colecaoDadosTabelaAtualizacaoCadastral != null && !colecaoDadosTabelaAtualizacaoCadastral.isEmpty()) {
			sessao.setAttribute("colecaoDadosTabelaAtualizacaoCadastral", colecaoDadosTabelaAtualizacaoCadastral);
		}else { 
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Dados Tabela Atualizacao Cadastral");
		}
		
		sessao.setAttribute("cliente", idCliente);
		sessao.setAttribute("imovel", idImovel);

		return retorno;
	}
}
