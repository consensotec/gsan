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
package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.AtualizarLeituristaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		if(sessao.getAttribute("mostrarUsuario")!=null){
			sessao.removeAttribute("mostrarUsuario");
		}
		
		AtualizarLeituristaActionForm form = (AtualizarLeituristaActionForm) actionForm;

		Leiturista leiturista = (Leiturista) sessao.getAttribute("leiturista");
		
		//Validar se o Funcionario ja esta cadastrado como leiturista
        if(form.getIdFuncionario() != null && !form.getIdFuncionario().equals("")){
            FiltroLeiturista filtroLeituristaFuncionario = new FiltroLeiturista();
            filtroLeituristaFuncionario.adicionarParametro(new ParametroSimples(FiltroLeiturista.FUNCIONARIO_ID, form.getIdFuncionario()));
            filtroLeituristaFuncionario.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
            filtroLeituristaFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
           
            Collection colLeituristaFunc = fachada.pesquisar(filtroLeituristaFuncionario, Leiturista.class.getName());
           
            if(!Util.isVazioOrNulo(colLeituristaFunc)){
                Leiturista leituristaFuncionario = (Leiturista) Util.retonarObjetoDeColecao(colLeituristaFunc);
               
                if(form.getEmpresaID()!= null && !leituristaFuncionario.getEmpresa().getId().toString().equals(form.getEmpresaID())){
                    throw new ActionServletException("atencao.leiturista_cadastrado_outra_empresa");
                }
            }
        }
       
        //Validar se o Cliente j� esta cadastrado como leiturista
        if(form.getIdCliente() != null && !form.getIdCliente().equals("")){
            FiltroLeiturista filtroLeituristaCliente = new FiltroLeiturista();
            filtroLeituristaCliente.adicionarParametro(new ParametroSimples(FiltroLeiturista.CLIENTE_ID, form.getIdCliente()));
            filtroLeituristaCliente.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
            filtroLeituristaCliente.adicionarCaminhoParaCarregamentoEntidade("empresa");
           
            Collection colLeituristaCli = fachada.pesquisar(filtroLeituristaCliente, Leiturista.class.getName());
           
            if(!Util.isVazioOrNulo(colLeituristaCli)){
                Leiturista leituristaCliente = (Leiturista) Util.retonarObjetoDeColecao(colLeituristaCli);
               
                if(!leiturista.getId().equals(leituristaCliente.getId()) &&
                	!leituristaCliente.getEmpresa().getId().toString().equals(form.getEmpresaID())){
                    
                	throw new ActionServletException("atencao.leiturista_cadastrado_outra_empresa");
                }
            }
        }
		
		// Validar se IMEI possui 15 caracteres
		if (form.getNumeroImei() != null && form.getNumeroImei().toString().length() != 15) {
			throw new ActionServletException("atencao.imei.invalido");
		}
		 
		// Validar se IMEI j� est� cadastrado
		if (form.getNumeroImei() != null && !form.getNumeroImei().equals(leiturista.getNumeroImei())) {
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.IMEI, form.getNumeroImei()));
			
			Collection pesquisa = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
			
			if (pesquisa != null && pesquisa.size() > 0) {
				Leiturista l = (Leiturista) Util.retonarObjetoDeColecao(pesquisa);
				
				String nomeLeiturista = l.getId().toString();
				
				if(l.getCliente() != null){
					nomeLeiturista = l.getCliente().getNome();
				}else if(l.getFuncionario() != null){
					nomeLeiturista = l.getFuncionario().getNome();
				}else if(l.getUsuario() != null){
					nomeLeiturista = l.getUsuario().getNomeUsuario();
				}
				
				throw new ActionServletException("atencao.imei.ja.cadastrado", null, nomeLeiturista);
			}
		}
		
		Cliente cli = null;
		Funcionario func = null;

		// Validamos o Funcionario
		if (form.getIdFuncionario() != null && 
				!form.getIdFuncionario().trim().equals("")) {
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
	
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form
							.getIdFuncionario()));
	
			Collection colFuncionario = fachada.pesquisar(filtroFuncionario,
					Funcionario.class.getName());
	
			if (colFuncionario == null || !colFuncionario.iterator().hasNext()) {
				// O funcionario n�o existe
				throw new ActionServletException("atencao.funcionario.inexistente",
						null, "Funcionario");
				
			}
			else{
				func = (Funcionario) Util.retonarObjetoDeColecao(colFuncionario);
				
			}
		}

		
		if (form.getIdCliente() != null
				&& !form.getIdCliente().equals("")) {
			// Validamos o cliente
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form
							.getIdCliente()));

			Collection colCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colCliente == null || !colCliente.iterator().hasNext()) {
				// O cliente n�o existe
				throw new ActionServletException("atencao.cliente.inexistente",
						null, "Cliente");
			} else {
				cli = (Cliente) Util.retonarObjetoDeColecao(colCliente);
				

			}
		}
		
		if ((form.getIdFuncionario() == null
				|| form.getIdFuncionario().equals(""))
				&& (form.getIdCliente() == null
						|| form.getIdCliente().equals(""))){

			throw new ActionServletException("atencao.cliente_ou_funcionario");
		}
		
//		if (fachada.pesquisarFuncionarioOuCliente(form.getIdFuncionario() != null
//				&& !form.getIdFuncionario().equals("") ? new Integer(form.getIdFuncionario()) : null, form.getIdCliente() != null
//				&& !form.getIdCliente().equals("") ? new Integer(form.getIdCliente()) : null)) {
//			//Erro Informar um cliente ou funcionario
//			throw new ActionServletException("atencao.cliente_ou_funcionario.ja_cadastrado");
//			
//		}
		leiturista.setFuncionario(func);
		leiturista.setCliente(cli);
		
//		if(form.getIdCliente()!=null && !form.getIdCliente().trim().equals("")){
//			// Cliente		z
//			Cliente cliente = new Cliente();
//			cliente.setId(new Integer(form.getIdCliente()));
//			leiturista.setCliente(cliente);
//			leiturista.setFuncionario(null);
//		}else if(form.getIdFuncionario()!=null && !form.getIdFuncionario().trim().equals("")){
//			// Funcionario
//			Funcionario funcionario = new Funcionario();
//			funcionario.setId(new Integer(form
//					.getIdFuncionario()));
//			leiturista.setFuncionario(funcionario);
//			leiturista.setCliente(null);
//		}else{
//			//Erro Informar um cliente ou funcionario
//			throw new ActionServletException("atencao.cliente_ou_funcionario");
//			
//		}
//		
		
		
		if(form.getDdd()!= null ){ 
			if( fachada.verificarDdd(new Short(form.getDdd()))){
				leiturista.setCodigoDDD(form.getDdd());
			}else{
				throw new ActionServletException("atencao.informe_ddd", null, "Ddd");
			}
		}
				
		leiturista.setNumeroFone(form.getTelefone());
		leiturista.setNumeroImei(form.getNumeroImei());
		leiturista.setIndicadorAgenteComercial(new Short(form.getIndicadorAgenteComercial()));

		//Indicador de uso 
		leiturista.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		

		// Empresa		
		Empresa empresa = null;
		
		if (form
				.getEmpresaID() != null && !form
				.getEmpresaID().equals("") && !form
				.getEmpresaID().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			empresa = new Empresa();
			
			empresa.setId(new Integer(form
					.getEmpresaID()));
		}else{
			throw new ActionServletException("atencao.empresa_leituristica_nao_informado"); 
		}
		
		//Setando
		leiturista.setEmpresa(empresa);
		
//		// Unidade Organizacional
//		UnidadeOrganizacional unidadeOrganizacional = null;
//		if(form.getUnidadeOrganizacionalId() != null && !form.getUnidadeOrganizacionalId().equals("")){
//			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
//			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getUnidadeOrganizacionalId()));
//			
//			Collection<UnidadeOrganizacional> colUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
//			
//			if(!Util.isVazioOrNulo(colUnidade)){
//				unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colUnidade);
//			}
//		}
//		
//		leiturista.setUnidadeOrganizacional(unidadeOrganizacional);
//		
		
		
		UnidadeOrganizacional unidadeOrganizacional = null;
		if(form.getUnidadeOrganizacionalId() != null && !form.getUnidadeOrganizacionalId().equals("")){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getUnidadeOrganizacionalId()));
			
			Collection<UnidadeOrganizacional> colUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if(!Util.isVazioOrNulo(colUnidade)){
				unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colUnidade);
			}	else{ 
				throw new ActionServletException("atencao.unidade_organizacional.inexistente");
			
			}
		}
		
		//Unidade Organizacional
		leiturista.setUnidadeOrganizacional(unidadeOrganizacional);
		
		// Usuario
		if ( form.getLoginUsuario() != null && !form.getLoginUsuario().equals( "" ) ){
			// Filtra Usuario
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuario() ) );		
			
			// Recupera Usu�rio
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = colecaoUsuario.iterator().next();
				
				leiturista.setUsuario( usuario );
			}else{
				throw new ActionServletException("atencao.pesquisa_inexistente", "Usu�rio"); 
			}
		} else  {
			 Usuario usuario = null;
			 leiturista.setUsuario( usuario );
			 if (form.getIndicadorAgenteComercial().toString()
					 .equals(ConstantesSistema.INDICADOR_USO_ATIVO.toString())){ 
				throw new ActionServletException("atencao.campo.informado",
						"Login do usu�rio");
			 }
		}
		
		leiturista.setIndicadorUso(new Short(form.getIndicadorUso()));
		
		/*
		 * [FS0012] Verificar Indicador Atualiza��o Cadastral
		 * Jonathan Marcos
		 * 12/11/2014
		 */
		if(form.getIndicadorAtualizacaoCadastral().equals("1") && 
				(form.getLoginUsuario()==null || form.getLoginUsuario().isEmpty())){
			throw new ActionServletException("atencao.login_usuario_deve_ser_informado"); 
		}else if(form.getIndicadorAtualizacaoCadastral().equals("2") &&
				form.getLoginUsuario().isEmpty()){
			leiturista.setUsuario(null);
		}
		
		leiturista.setIndicadorAtualizacaoCadastral(Short.valueOf(form.getIndicadorAtualizacaoCadastral()));
		
		fachada.atualizarLeiturista(leiturista);

		montarPaginaSucesso(httpServletRequest, "Leiturista de c�digo "
				+ leiturista.getId().toString() + " atualizada com sucesso.",
				"Realizar outra Manuten��o de Leiturista ",
				"exibirFiltrarLeituristaAction.do?menu=sim");
		return retorno;
	}
}
