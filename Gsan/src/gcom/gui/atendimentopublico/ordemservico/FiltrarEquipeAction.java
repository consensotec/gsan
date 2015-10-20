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

import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipeComponentes;
import gcom.atendimentopublico.ordemservico.FiltroEquipeEquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarEquipeAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterEquipe");

		Fachada fachada = Fachada.getInstancia();

		FiltrarEquipeActionForm filtrarEquipeActionForm = (FiltrarEquipeActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa todo o formul�rio para evitar "sujeiras" na tela
		String idEquipe = filtrarEquipeActionForm.getCodigo();
		String nome = filtrarEquipeActionForm.getNome();
		String placa = filtrarEquipeActionForm.getPlaca();
		String cargaTrabalho = filtrarEquipeActionForm.getCargaTrabalho();
		String idUnidade = filtrarEquipeActionForm.getIdUnidade();
		String idFuncionario = filtrarEquipeActionForm.getIdFuncionario();
		String idPerfilServico = filtrarEquipeActionForm.getIdPerfilServico();
		String indicadorUso = filtrarEquipeActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarEquipeActionForm.getTipoPesquisa();
		String codigoDdd = filtrarEquipeActionForm.getCodigoDdd();
		String numeroTelefone = filtrarEquipeActionForm.getNumeroTelefone();
		String numeroImei = filtrarEquipeActionForm.getNumeroImei();
		String equipamentosEspeciasId = filtrarEquipeActionForm.getEquipamentosEspeciasId();
		String cdUsuarioRespExcServico = filtrarEquipeActionForm.getCdUsuarioRespExecServico();
		
		// Cria o filtro
		FiltroEquipeComponentes filtroEquipeComponentes = new FiltroEquipeComponentes();

		// Ordena a pesquisa por um par�metro pr�-definido
		filtroEquipeComponentes.setCampoOrderBy("equipe.id");

		boolean peloMenosUmParametroInformado = false;
		
		// Cria o filtro de equipamentos especiais
		FiltroEquipeEquipamentosEspeciais filtroEquipeEquipamentosEspeciais = new FiltroEquipeEquipamentosEspeciais();
		
		// Ordena a pesquisa por um par�metro pr�-definido
		filtroEquipeEquipamentosEspeciais.setCampoOrderBy("EquipamentosEspeciais.id");


		// Neste ponto o filtro � criado com os par�metros informados na p�gina
		// de filtrar equipe para ser executada a pesquisa no
		// ExibirManterEquipeAction

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
			} else {
				peloMenosUmParametroInformado = true;
			}

		}

		// Verifica se o funcion�rio existe e em caso afirmativo seta-o no
		// filtro
		if (idFuncionario != null && !idFuncionario.trim().equals("")) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Funcion�rio");
			} else {
				peloMenosUmParametroInformado = true;
			}

		}

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
			} else {
				peloMenosUmParametroInformado = true;
			}

		}

		if (idEquipe != null && !idEquipe.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		// Nome da Equipe
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if(tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())){
				
				filtroEquipeComponentes.adicionarParametro( 
						new ComparacaoTextoCompleto(FiltroEquipe.NOME, nome ));
				
			} else {
				
				filtroEquipeComponentes.adicionarParametro( 
						new ComparacaoTexto(FiltroEquipe.NOME, nome ));
			}
		}

		if (placa != null && !placa.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}

		if (cargaTrabalho != null && !cargaTrabalho.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		
		if (codigoDdd != null && !codigoDdd.trim().equalsIgnoreCase("")){
			peloMenosUmParametroInformado = true;
		}
		
		if (numeroTelefone != null && !numeroTelefone.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		
		if (numeroImei != null && !numeroImei.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		
		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		
		if(equipamentosEspeciasId != null && !equipamentosEspeciasId.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
		}
		if(cdUsuarioRespExcServico != null && !cdUsuarioRespExcServico.equals("")){
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pela sess�o uma vari�vel para o
		// ExibirManterEquipeAction e nele verificar se ir� para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sess�o
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		// Manda os par�metros da pesquisa pela sessao para o
		// ExibirManterEquipeAction
		sessao.setAttribute("idEquipe", idEquipe);
		sessao.setAttribute("nome", nome);
		sessao.setAttribute("placa", placa);
		sessao.setAttribute("cargaTrabalho", cargaTrabalho);
		sessao.setAttribute("idUnidade", idUnidade);
		sessao.setAttribute("idFuncionario", idFuncionario);
		sessao.setAttribute("idPerfilServico", idPerfilServico);
		sessao.setAttribute("indicadorUso", indicadorUso);
		sessao.setAttribute("indicadorProgramacaoAutomatica", filtrarEquipeActionForm.getIndicadorProgramacaoAutomatica());
		sessao.setAttribute("codigoDdd", codigoDdd);
		sessao.setAttribute("numeroTelefone", numeroTelefone);
		sessao.setAttribute("numeroImei", numeroImei);
		sessao.setAttribute("equipamentosEspeciasId", equipamentosEspeciasId);
		if(cdUsuarioRespExcServico != null && !cdUsuarioRespExcServico.equals("")){
			sessao.setAttribute("cdUsuarioRespExcServico", cdUsuarioRespExcServico);
		}

		return retorno;
	}
}
