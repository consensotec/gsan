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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0371] Inserir Equipe
 * 
 * @author Leonardo Regis
 * @created 24 de Julho de 2006
 */
public class InserirEquipeAction extends GcomAction {

	/**
	 * [UC0371] Inserir Equipe
	 * 
	 * [UC0107] Registrar Transa��o
	 * 
	 * @author Leonardo Regis
	 * @date   21/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		// Form
		InserirEquipeActionForm inserirEquipeActionForm = (InserirEquipeActionForm) actionForm;
		// Fachada
		Fachada fachada = Fachada.getInstancia();
		// Sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");		
		
		// Registrando a opera��o
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_EQUIPE_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_EQUIPE_INSERIR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        
		// Equipe
		Equipe equipe = null;
		if (inserirEquipeActionForm.getUnidadeOrganizacional() != null) {
			
			// Recupera informa��es da equipe
			equipe = getEquipe(inserirEquipeActionForm);
			
			// Faz as valida��es de inser��o de equipe
			fachada.validarInsercaoEquipe(equipe);
			
			// Faz as valida��es de inser��o de equipe componentes
			fachada.validarInsercaoEquipeComponentes(inserirEquipeActionForm.getEquipeComponentes());
			
			// Faz as valida��es de inser��o de equipe equipamentos especiais
			fachada.validarInsercaoEquipeEquipamentosEspeciais(inserirEquipeActionForm.getEquipeEquipamentosEspeciais());
			
			// Insere Equipe
			long idEquipe = fachada.inserirEquipe(equipe, inserirEquipeActionForm.getEquipeComponentes(), 
					inserirEquipeActionForm.getEquipeEquipamentosEspeciais(), usuario);
			
			// [FS008] Monta a p�gina de sucesso
			montarPaginaSucesso(httpServletRequest, 
								"Inser��o da Equipe "+equipe.getNome()+" efetuada com sucesso!", 
								"Efetuar outra Inser��o de Equipe",
								"exibirInserirEquipeAction.do?menu=sim",
								"exibirAtualizarEquipeAction.do?equipeID="+ idEquipe,
								"Atualizar Equipe Inserida");

		}
		return retorno;
	}

	/**
	 * Insere Cole��o de Componentes devidamente validados na base 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param fachada
	 * @param equipe
	 */
	/*private void inserirEquipeComponentes(InserirEquipeActionForm inserirEquipeActionForm, 
		OperacaoEfetuada operacaoEfetuada, Equipe equipe, 
		RegistradorOperacao registradorOperacao,Usuario usuario) {
		
		// Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Cole��o de Componentes
		Collection colecaoEquipeComponentes = inserirEquipeActionForm.getEquipeComponentes();
		for (Iterator iter = colecaoEquipeComponentes.iterator(); iter.hasNext();) {
			EquipeComponentes element = (EquipeComponentes) iter.next();
			element.setEquipe(equipe);
			// Registra opera��o
			element.setOperacaoEfetuada(operacaoEfetuada);
			element.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        	registradorOperacao.registrarOperacao(element);
        	// Insere componente
			fachada.inserirEquipeComponentes(element);
		}
	}*/

	/**
	 * Insere Cole��o de equipamentos especiais devidamente validados na base
	 * 
	 * @author Nathalia Santos
	 * @date 20/06/2011
	 * 
	 * @param inserirEquipeActionForm
	 * @param Quantidade
	 * @param fachada
	 * @param EquipeEquipamentosEspeciais
	 */

	/*private void inserirEquipeEquipamentosEspeciais(
			InserirEquipeActionForm inserirEquipeActionForm,
			OperacaoEfetuada operacaoEfetuada, Equipe equipe,
			RegistradorOperacao registradorOperacao, Usuario usuario,
			Integer Quantidade) {

		// Fachada
		Fachada fachada = Fachada.getInstancia();

		// Cole��o de Equipamentos especiais
		Collection colecaoEquipeEquipamentosEspeciais = inserirEquipeActionForm
				.getEquipeEquipamentosEspeciais();
		for (Iterator iter = colecaoEquipeEquipamentosEspeciais.iterator(); iter
				.hasNext();) {
			EquipeEquipamentosEspeciais element = (EquipeEquipamentosEspeciais) iter
					.next();
			element.setQuantidade(new Integer(inserirEquipeActionForm
					.getQuantidade()));
			// Registra opera��o
			element.setOperacaoEfetuada(operacaoEfetuada);
			element.adicionarUsuario(usuario,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(element);

			fachada.inserirEquipeEquipamentosEspeciais(element);
		}
	}*/

	/**
	 * Recupera Equipe com informa��es vindas da tela 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param fachada
	 * @param equipe
	 */
	private Equipe getEquipe(InserirEquipeActionForm inserirEquipeActionForm) {
		Equipe equipe;
		// Cria objeto Equipe
		equipe = new Equipe();
		equipe.setNome(inserirEquipeActionForm.getNomeEquipe());
		equipe.setPlacaVeiculo(inserirEquipeActionForm.getPlacaVeiculo());
		// Seta valor da carga hor�ria no banco convertido para minuto
		int cargaHoraria = Integer.parseInt(inserirEquipeActionForm.getCargaTrabalhoDia())*60;
		equipe.setCargaTrabalho(cargaHoraria);
		// Unidade Organizacional
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		unidadeOrganizacional.setId(new Integer(inserirEquipeActionForm.getUnidadeOrganizacionalId()));
		equipe.setUnidadeOrganizacional(unidadeOrganizacional);
		// Tipo Perfil Servico
		if(inserirEquipeActionForm.getTipoPerfilServicoId() != null && !inserirEquipeActionForm.getTipoPerfilServicoId().equals("")){
			ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();
			servicoPerfilTipo.setId(new Integer(inserirEquipeActionForm.getTipoPerfilServicoId()));
			equipe.setServicoPerfilTipo(servicoPerfilTipo);
		}
		equipe.setUltimaAlteracao(new Date());
		equipe.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		equipe.setCodigoDdd(inserirEquipeActionForm.getCodigoDdd());
		equipe.setNumeroTelefone(inserirEquipeActionForm.getNumeroTelefone());
		equipe.setNumeroImei(new BigDecimal(inserirEquipeActionForm.getNumeroImei()));
		
		/*
		 * -Erivan-
		 * Verifica a existencia do c�digo do usu�rio informado, 
		 * caso exista, insere na equipe
		 */
		if(inserirEquipeActionForm.getCdUsuarioRespExecServico() != null && !inserirEquipeActionForm.getCdUsuarioRespExecServico().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, inserirEquipeActionForm.getCdUsuarioRespExecServico()));
			Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
				equipe.setUsuarioRespExecServico((Usuario)colecaoUsuario.iterator().next());
			}else{
				throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
			}
		}
		
		equipe.setIndicadorProgramacaoAutomatica(new Short(inserirEquipeActionForm.getIndicadorProgramacaoAutomatica()));
		
		return equipe;
	}
}
