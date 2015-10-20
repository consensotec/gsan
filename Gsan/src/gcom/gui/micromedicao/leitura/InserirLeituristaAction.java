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
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir um Leiturista no banco
 * 
 * [UC0588] Inserir Leiturista Permite inserir um Leiturista
 * 
 * @author Thiago Ten�rio
 * @since 22/07/2007
 */
public class InserirLeituristaAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		if(sessao.getAttribute("mostrarUsuario")!=null){
			sessao.removeAttribute("mostrarUsuario");
		}
		
		// Usuario logado no sistema
		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		InserirLeituristaActionForm inserirLeituristaActionForm = (InserirLeituristaActionForm) actionForm;
		
		// Validar se IMEI possui 15 caracteres
		if (inserirLeituristaActionForm.getNumeroImei() != null && inserirLeituristaActionForm.getNumeroImei().length() != 15) {
			throw new ActionServletException("atencao.imei.invalido");
		}
		
		// Validar se IMEI j� est� cadastrado
		if (inserirLeituristaActionForm.getNumeroImei() != null && 
				!inserirLeituristaActionForm.getNumeroImei().trim().equals("")) {
			
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.IMEI, inserirLeituristaActionForm.getNumeroImei()));
			
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
		
	

		// Cria um Leiturista setando os valores informados pelo
		// usu�rio na pagina para ser inserido no banco
		Leiturista leiturista = new Leiturista();
		Cliente cli = null;
		Funcionario func = null;

		// Validamos o Funcionario
		if (inserirLeituristaActionForm.getIdFuncionario() != null && 
				!inserirLeituristaActionForm.getIdFuncionario().trim().equals("")) {
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
	
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, inserirLeituristaActionForm.getIdFuncionario()));
			filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
	
			Collection colFuncionario = fachada.pesquisar(filtroFuncionario,
					Funcionario.class.getName());
	
			if (colFuncionario == null || !colFuncionario.iterator().hasNext()) {
				// O funcionario n�o existe
				throw new ActionServletException("atencao.funcionario.inexistente",
						null, "Funcionario");
				
			}
			else{
				func = (Funcionario) Util.retonarObjetoDeColecao(colFuncionario);
				
				if(func.getEmpresa() != null){
					if(func.getEmpresa().getId() != null ){
						inserirLeituristaActionForm.setEmpresaID(func.getEmpresa().getId().toString());
					}
				}
				
			}
		}

		
		if (inserirLeituristaActionForm.getIdCliente() != null
				&& !inserirLeituristaActionForm.getIdCliente().equals("")) {
			// Validamos o cliente
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, inserirLeituristaActionForm
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
		if ((inserirLeituristaActionForm.getIdFuncionario() == null
				|| inserirLeituristaActionForm.getIdFuncionario().equals(""))
				&& (inserirLeituristaActionForm.getIdCliente() == null
						|| inserirLeituristaActionForm.getIdCliente().equals(""))){

			throw new ActionServletException("atencao.cliente_ou_funcionario");
		}
		
		if (fachada.pesquisarFuncionarioOuCliente(inserirLeituristaActionForm.getIdFuncionario() != null
				&& !inserirLeituristaActionForm.getIdFuncionario().equals("") ? new Integer(inserirLeituristaActionForm.getIdFuncionario()) : null, inserirLeituristaActionForm.getIdCliente() != null
				&& !inserirLeituristaActionForm.getIdCliente().equals("") ? new Integer(inserirLeituristaActionForm.getIdCliente()) : null)) {
			//Erro Informar um cliente ou funcionario
			throw new ActionServletException("atencao.cliente_ou_funcionario.ja_cadastrado");
			
		}
		
		UnidadeOrganizacional unidadeOrganizacional = null;
		if(inserirLeituristaActionForm.getUnidadeOrganizacionalId() != null && !inserirLeituristaActionForm.getUnidadeOrganizacionalId().equals("")){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, inserirLeituristaActionForm.getUnidadeOrganizacionalId()));
			
			Collection<UnidadeOrganizacional> colUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if(!Util.isVazioOrNulo(colUnidade)){
				unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colUnidade);
			}	else{ 
				throw new ActionServletException("atencao.unidade_organizacional.inexistente");
			
			}
		}
		
		//Unidade Organizacional
		leiturista.setUnidadeOrganizacional(unidadeOrganizacional);

		// Funcionario
		leiturista.setFuncionario(func);

		// Cliente
		leiturista.setCliente(cli);

		// Telefone
		leiturista.setNumeroFone(inserirLeituristaActionForm.getTelefone());
		
		// Numero do IMEI
		leiturista.setNumeroImei(new Long(inserirLeituristaActionForm.getNumeroImei()));

		// C�digo DDD do Municipio
		// verifica s� � possivel antes de informar
		if(fachada.verificarDdd(new Short (inserirLeituristaActionForm.getDdd()))){
			leiturista.setCodigoDDD(inserirLeituristaActionForm.getDdd());
		}else{
			throw new ActionServletException("atencao.informe_ddd", null, "Ddd");
		}
		//Indicador de uso 
		leiturista.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		// Agente Comercial
		String indicadorAgenteComercial = inserirLeituristaActionForm.getIndicadorAgenteComercial();
		if(indicadorAgenteComercial !=null && !indicadorAgenteComercial.trim().equals("")){
			leiturista.setIndicadorAgenteComercial(new Short(indicadorAgenteComercial));
		}
		else{
			throw new ActionServletException("atencao.informe_agente_comercial", null, "indicadorAgenteComercial");
		}
		

		// Empresa
		if (Util.validarNumeroMaiorQueZERO(inserirLeituristaActionForm
				.getEmpresaID())) {
			// Constr�i o filtro para pesquisa da Empresa
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, inserirLeituristaActionForm
							.getEmpresaID()));

			Collection colecaoEmpresa = (Collection) fachada.pesquisar(
					filtroEmpresa, Empresa.class.getName());

			// setando
			leiturista.setEmpresa((Empresa) colecaoEmpresa.iterator().next());
		}else{
			throw new ActionServletException("atencao.empresa_leituristica_nao_informado", null, "Empresa"); 
		}
		
		
		// Inserimos o usu�rio
		if ( inserirLeituristaActionForm.getLoginUsuario() != null && 
			!inserirLeituristaActionForm.getLoginUsuario().equals( "" ) ){
			
			// Filtra Usuario
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, 
				inserirLeituristaActionForm.getLoginUsuario() ) );		
			
			// Recupera Usu�rio
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = colecaoUsuario.iterator().next();
				
				leiturista.setUsuario( usuario );
			}else{
				throw new ActionServletException("atencao.pesquisa_inexistente", "Usu�rio"); 
				}
			} else  {
					 if (inserirLeituristaActionForm.getIndicadorAgenteComercial().toString()
							 .equals(ConstantesSistema.INDICADOR_USO_ATIVO.toString())){ 
						throw new ActionServletException("atencao.campo.informado",
								"Login do usu�rio");
					 }
			}

		// Ultima altera��o
		leiturista.setUltimaAlteracao(new Date());
		
		/*
		 * [FS0012] Verificar Indicador Atualiza��o Cadastral
		 * Jonathan Marcos
		 * 12/11/2014
		 */
		if(inserirLeituristaActionForm.getIndicadorAtualizacaoCadastral().compareTo("1")==0
				&& (inserirLeituristaActionForm.getLoginUsuario()==null 
				|| inserirLeituristaActionForm.getLoginUsuario().compareTo("")==0)){
			throw new ActionServletException("atencao.login_usuario_deve_ser_informado"); 
		}else{
			leiturista.setIndicadorAtualizacaoCadastral(Short.valueOf(inserirLeituristaActionForm.
					getIndicadorAtualizacaoCadastral()));
		}

		// Insere um Leiturista na base, mas fazendo, antes,
		// algumas verifica��es no ControladorMicromedi��oSEJB.
		fachada.inserirLeiturista(leiturista, usuarioLogado);

		// Exibe a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Leiturista "
				+ leiturista.getId() + " inserido com sucesso.",
				"Inserir outro Leiturista",
				"exibirInserirLeituristaAction.do?menu=sim");

		return retorno;

	}

}