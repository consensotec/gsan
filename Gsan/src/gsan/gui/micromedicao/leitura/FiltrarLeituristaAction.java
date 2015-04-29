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
package gsan.gui.micromedicao.leitura;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroFoneTipo;
import gsan.cadastro.funcionario.FiltroFuncionario;
import gsan.cadastro.funcionario.Funcionario;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroLeiturista;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
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
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio
 * @date 22/07/2007
 */
public class FiltrarLeituristaAction extends GcomAction {

	/**
	 * Este caso de uso permite Filtrar um Leiturista
	 * 
	 * [UC0590] Filtrar Leiturista
	 * 
	 * 
	 * @author Thiago Ten�rio e Thiago Nascimento
	 * @date 11/06/2008
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterLeiturista");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarLeituristaActionForm filtrarLeituristaActionForm = (FiltrarLeituristaActionForm) actionForm;

		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.ID);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade("usuario");

		 Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String idFuncionario = filtrarLeituristaActionForm.getIdFuncionario();
		String idCliente = filtrarLeituristaActionForm.getIdCliente();
		String empresaID = filtrarLeituristaActionForm.getEmpresaID();
		String telefone = filtrarLeituristaActionForm.getTelefone();
		String ddd = filtrarLeituristaActionForm.getDdd();
		String indicadorUso = filtrarLeituristaActionForm.getIndicadorUso();
		String imei = filtrarLeituristaActionForm.getImei();
		String loginUsuario = filtrarLeituristaActionForm.getLoginUsuario();
		Short indicadorAtualizacaoCadastral = Short.valueOf(filtrarLeituristaActionForm.getIndicadorAtualizacaoCadastral());
		
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		// Verifica se o campo C�digodo funcionario foi informado
		if (idFuncionario != null && !idFuncionario.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		
			FiltroFuncionario filtroFunc = new FiltroFuncionario();
			filtroFunc.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
			Collection collFunc = fachada.pesquisar(filtroFunc, Funcionario.class.getName());
			if(collFunc == null || collFunc.isEmpty()){
				throw new ActionServletException(
						"atencao.funcionario.inexistente");
			}
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.FUNCIONARIO, idFuncionario));
		}
	
		
		// Verifica se o campo Consumo a Ser Cobrado (leitura n�o informada) foi informado

		if (empresaID != null
				&& !empresaID.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA, empresaID));

		}

		// Verifica se o campo C�digodo cliente foi informado
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			FiltroCliente filtroClie = new FiltroCliente();
			filtroClie.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			Collection collClie = fachada.pesquisar(filtroClie, Cliente.class.getName());
			if(collClie == null || collClie.isEmpty()){
				throw new ActionServletException(
						"atencao.cliente.inexistente");
			}
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.CLIENTE, idCliente));
		}

		// Verifica se o campo numero telefone foi informado
		if (telefone != null && !telefone.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.TELEFONE, telefone));

		}

		// Verifica se o campo DDD foi informado
		if (ddd != null && !ddd.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.DDD, ddd));

		}
		

		// Verifica se o campo Indicador de Uso foi informado
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")
				&& !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		//Numero do Imei
		if(imei !=null && !imei.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.IMEI, imei));

		}
		
//		// Usuario
//		if ( loginUsuario != null && !loginUsuario.equals( "" ) ){
//			// Filtra Usuario
//			FiltroUsuario filtroUsuario = new FiltroUsuario();
//			filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, loginUsuario ) );		
//			
//			// Recupera Usu�rio
//			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
//			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
//				Usuario usuario = colecaoUsuario.iterator().next();
//				
//				filtroLeiturista.adicionarParametro( new ParametroSimples( FiltroLeiturista.USUARIO_ID, usuario.getId() ) );
//			}
//		}		
		
		// Verifica se o campo C�digo do Usu�rio foi informado
		if (loginUsuario != null && !loginUsuario.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			FiltroUsuario filtroUsu = new FiltroUsuario();
			filtroUsu.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
			
			Collection<Usuario> collUsu = 
					this.getFachada().pesquisar(filtroUsu, Usuario.class.getName());
			
			if(collUsu == null || collUsu.isEmpty()){
				throw new ActionServletException("atencao.usuario.inexistente");
			}
			
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(collUsu);

			filtroLeiturista.adicionarParametro(
				new ParametroSimples(FiltroLeiturista.USUARIO_ID, usuario.getId()));
		}
				
		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		if(indicadorAtualizacaoCadastral.compareTo(new Short("3"))!=0){
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ATUALIZACAO_CADASTRAL, 
					indicadorAtualizacaoCadastral));
		}
		
		
		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		sessao.setAttribute("filtroLeiturista", filtroLeiturista);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar); 

		return retorno;
		}
	}
	