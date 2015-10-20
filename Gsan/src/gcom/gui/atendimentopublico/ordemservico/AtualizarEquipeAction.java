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
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

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
public class AtualizarEquipeAction extends GcomAction {

	/**
	 * [UC0372] Manter Equipe
	 * 
	 * @author Rafael Corr�a
	 * @date   13/11/2006
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
		AtualizarEquipeActionForm atualizarEquipeActionForm = (AtualizarEquipeActionForm) actionForm;
		
		// Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String nomeEquipe = atualizarEquipeActionForm.getNomeEquipe();
		String placa = atualizarEquipeActionForm.getPlacaVeiculo();
		String cargaTrabalho = atualizarEquipeActionForm.getCargaTrabalhoDia();
		String idUnidade = atualizarEquipeActionForm.getIdUnidade();
		String idPerfilServico = atualizarEquipeActionForm.getIdServicoPerfilTipo();
		String indicadorUso = atualizarEquipeActionForm.getIndicadorUso();
		String codigoDdd = atualizarEquipeActionForm.getCodigoDdd();
		String numeroTelefone = atualizarEquipeActionForm.getNumeroTelefone();
		String numeroImei = atualizarEquipeActionForm.getNumeroImei();
		String cdUsuarioRespExecServico = atualizarEquipeActionForm.getCdUsuarioRespExecServico();
		
		UnidadeOrganizacional unidade = null;
		
		// Verifica se a unidade organizacional existe e em caso afirmativo
		// seta-a no filtro
		if (idUnidade != null && !idUnidade.trim().equals("")) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidade));

			Collection colecaoUnidade = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade Organizacional");
			}
			unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

		}

		ServicoPerfilTipo servicoPerfilTipo = null;
		
		// Verifica se o servi�o perfil tipo existe e em caso afirmativo seta-o
		// no filtro
		if (idPerfilServico != null && !idPerfilServico.trim().equals("")) {

			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
			filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoPerfilTipo.ID, idPerfilServico));

			Collection colecaoPerfilServico = fachada.pesquisar(
					filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());

			if (colecaoPerfilServico == null || colecaoPerfilServico.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Servi�o Perfil Tipo");
			}
			servicoPerfilTipo = (ServicoPerfilTipo) Util.retonarObjetoDeColecao(colecaoPerfilServico);

		}
		
		// Equipe
		Equipe equipe = null;
		if (sessao.getAttribute("equipeAtualizar") != null) {
			
			// Recupera informa��es da equipe
			equipe = (Equipe) sessao.getAttribute("equipeAtualizar");
			
			equipe.setNome(nomeEquipe);
			
			int cargaHoraria = Integer.parseInt(cargaTrabalho)*60;
			equipe.setCargaTrabalho(cargaHoraria);
			
			if (placa != null && !placa.trim().equals("")) {
				equipe.setPlacaVeiculo(placa);
			} else {
				equipe.setPlacaVeiculo(null);
			}
			
			if (indicadorUso != null && indicadorUso.equals(ConstantesSistema.INDICADOR_USO_ATIVO.toString())) {
				equipe.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			} else {
				equipe.setIndicadorUso(ConstantesSistema.INDICADOR_USO_DESATIVO);
			}
			equipe.setCodigoDdd(codigoDdd);
			equipe.setNumeroTelefone(numeroTelefone);
			equipe.setNumeroImei(new BigDecimal(numeroImei));
			equipe.setUnidadeOrganizacional(unidade);
			equipe.setServicoPerfilTipo(servicoPerfilTipo);
				
			// Cole��o de Componentes
			Collection colecaoEquipeComponentes = (Collection) sessao.getAttribute("colecaoEquipeComponentes");
			
			if (servicoPerfilTipo != null) {
				if (servicoPerfilTipo.getComponentesEquipe().intValue() != colecaoEquipeComponentes.size()) {
					throw new ActionServletException(
							"atencao.quantidade.componentes.diferente.permitido");
				}
			}
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			/*
			 * -Erivan-
			 * Verifica a existencia do c�digo do usu�rio informado, 
			 * caso exista, insere na equipe
			 */
			if(cdUsuarioRespExecServico != null && !cdUsuarioRespExecServico.equals("")){
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, cdUsuarioRespExecServico));
				Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
				
				if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
					equipe.setUsuarioRespExecServico((Usuario)colecaoUsuario.iterator().next());
				}else{
					throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
				}
			}else{
				equipe.setUsuarioRespExecServico(null);
			}
			
			equipe.setIndicadorProgramacaoAutomatica(new Short(atualizarEquipeActionForm.getIndicadorProgramacaoAutomatica()));
			
			// Atualiza a Equipe e os equipamentos especiais
			fachada.atualizarEquipe(equipe, colecaoEquipeComponentes, usuarioLogado,atualizarEquipeActionForm.getEquipeEquipamentosEspeciais());

			// [FS008] Monta a p�gina de sucesso
			montarPaginaSucesso(httpServletRequest, 
								"Equipe "+equipe.getNome()+" atualizada com sucesso.", 
								"Realizar outra manuten��o de Equipe",
								"exibirFiltrarEquipeAction.do?menu=sim");

		}
		return retorno;
	}

}
