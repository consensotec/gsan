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

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;
import gcom.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais;
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
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite filtrar resolu��es de diretoria [UC0219] Filtrar Resolu��o de
 * Diretoria
 * 
 * @author Rafael Corr�a
 * @since 31/03/2006
 */
public class ExibirAtualizarEquipeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarEquipe");

		AtualizarEquipeActionForm atualizarEquipeActionForm = (AtualizarEquipeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
//		-Erivan Sousa- Verifica e atende uma solicita��o de pesquisa de usu�rio
		if(httpServletRequest.getParameter("tipoPesquisa") != null &&
				!httpServletRequest.getParameter("tipoPesquisa").equals("")){
			
			if(httpServletRequest.getParameter("tipoPesquisa").equals("usuario")){
				Usuario usuario = buscarUsuario(atualizarEquipeActionForm.getCdUsuarioRespExecServico());
				if(usuario != null){
					atualizarEquipeActionForm.setCdUsuarioRespExecServico(usuario.getId().toString());
					atualizarEquipeActionForm.setNomeUsuarioRespExecServico(usuario.getNomeUsuario().toUpperCase());
					sessao.setAttribute("usuarioRespExecServicoEncontrado", true);
				}else{
					atualizarEquipeActionForm.setNomeUsuarioRespExecServico("USU�RIO INEXISTENTE");
					sessao.removeAttribute("usuarioRespExecServicoEncontrado");
				}
			}
			
		}else{
			if(atualizarEquipeActionForm.getNomeUsuarioRespExecServico() != null){
				if(atualizarEquipeActionForm.getNomeUsuarioRespExecServico().equals("USU�RIO INEXISTENTE")){
					atualizarEquipeActionForm.setCdUsuarioRespExecServico("");
					atualizarEquipeActionForm.setNomeUsuarioRespExecServico("");
				}
			}
		}

		// Recupera os valores da unidade do form
		String idUnidade = atualizarEquipeActionForm.getIdUnidade();
		String nomeUnidade = atualizarEquipeActionForm.getNomeUnidade();

		// Verifica se o usu�rio solicitou a pesquisa de unidade
		if (idUnidade != null && !idUnidade.trim().equals("") && 
			(nomeUnidade == null || nomeUnidade.trim().equals(""))) {
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidade));

			Collection colecaoUnidade = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

				UnidadeOrganizacional unidadeOrganizacional = 
					(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

				atualizarEquipeActionForm.setNomeUnidade(unidadeOrganizacional.getDescricao());
				httpServletRequest.setAttribute("nomeCampo","idServicoPerfilTipo");

			} else {

				atualizarEquipeActionForm.setIdUnidade("");
				atualizarEquipeActionForm.setNomeUnidade("Unidade inexistente");
				httpServletRequest.setAttribute("idUnidadeNaoEncontrado","true");
				httpServletRequest.setAttribute("nomeCampo", "idUnidade");

			}

		} else if (nomeUnidade != null && !nomeUnidade.trim().equals("") && 
				(idUnidade == null || idUnidade.trim().equals(""))) {
			atualizarEquipeActionForm.setNomeUnidade("");
		}

		// Recupera os valores do servi�o perfil tipo do form
		String idPerfilServico = atualizarEquipeActionForm.getIdServicoPerfilTipo();
		String descricaoPerfilServico = atualizarEquipeActionForm.getDescricaoServicoPerfilTipo();

		// Verifica se o usu�rio solicitou a pesquisa de funcion�rio
		if (idPerfilServico != null && 
			!idPerfilServico.trim().equals("") && (descricaoPerfilServico == null || 
				descricaoPerfilServico.trim().equals(""))) {
			
			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
			filtroServicoPerfilTipo.adicionarParametro(
				new ParametroSimples(FiltroServicoPerfilTipo.ID, idPerfilServico));

			Collection colecaoPerfilServico = 
				fachada.pesquisar(filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());

			if (colecaoPerfilServico != null && !colecaoPerfilServico.isEmpty()) {

				ServicoPerfilTipo servicoPerfilTipo = 
					(ServicoPerfilTipo) Util.retonarObjetoDeColecao(colecaoPerfilServico);

				atualizarEquipeActionForm.setDescricaoServicoPerfilTipo(servicoPerfilTipo.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "indicadorUso");

			} else {

				atualizarEquipeActionForm.setIdServicoPerfilTipo("");
				atualizarEquipeActionForm.setDescricaoServicoPerfilTipo("Servi�o Tipo Perfil inexistente");
				httpServletRequest.setAttribute("idServicoPerfilNaoEncontrado","true");
				httpServletRequest.setAttribute("nomeCampo","idServicoPerfilTipo");

			}

		} else if (descricaoPerfilServico != null && 
				!descricaoPerfilServico.trim().equals("") && 
				(idPerfilServico == null || idPerfilServico.trim().equals(""))) {
			
			atualizarEquipeActionForm.setDescricaoServicoPerfilTipo("");
		}

		// Verifica se o usu�rio est� entrando pela primeira vez na tela ou se
		// ele selecionou a op��o de desfazer
		if (sessao.getAttribute("equipeAtualizar") == null || 
			httpServletRequest.getParameter("desfazer") != null) {

			Equipe equipe = null;
			atualizarEquipeActionForm.setEquipeEquipamentosEspeciais(new ArrayList<EquipeEquipamentosEspeciais>());

			if (httpServletRequest.getParameter("desfazer") != null) {

				String idEquipe = ((Equipe) sessao.getAttribute("equipeAtualizar")).getId().toString();

				FiltroEquipe filtroEquipe = new FiltroEquipe();
				filtroEquipe.adicionarParametro(
					new ParametroSimples(FiltroEquipe.ID, idEquipe));

				filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("servicoPerfilTipo");
				filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
				filtroEquipe.adicionarCaminhoParaCarregamentoEntidade(FiltroEquipe.USUARIO_RESP_EXECUSSAO_SERVICO);
				
				Collection colecaoEquipe = 
					fachada.pesquisar(filtroEquipe,Equipe.class.getName());

				if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
					throw new ActionServletException("atencao.atualizacao.timestamp");
				}

				equipe = (Equipe) colecaoEquipe.iterator().next();

				sessao.setAttribute("equipeAtualizar", equipe);

				// equipe = (Equipe) sessao.getAttribute("equipeAtualizar");

			} else {

				if (sessao.getAttribute("equipe") != null) {

					equipe = (Equipe) sessao.getAttribute("equipe");

					sessao.setAttribute("equipeAtualizar", equipe);
					sessao.removeAttribute("equipe");

					sessao.setAttribute("filtrar", true);

				} else {

					String idEquipe = httpServletRequest.getParameter("equipeID");

					if (httpServletRequest.getParameter("inserir") != null) {
						sessao.setAttribute("inserir", true);
					} else {
						sessao.removeAttribute("filtrar");
						sessao.removeAttribute("inserir");
					}

					FiltroEquipe filtroEquipe = new FiltroEquipe();
					filtroEquipe.adicionarParametro(
						new ParametroSimples(FiltroEquipe.ID, idEquipe));

					filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("servicoPerfilTipo");
					filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
					filtroEquipe.adicionarCaminhoParaCarregamentoEntidade(FiltroEquipe.USUARIO_RESP_EXECUSSAO_SERVICO);
					
					
					Collection colecaoEquipe = 
						fachada.pesquisar(filtroEquipe,Equipe.class.getName());

					if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					equipe = (Equipe) colecaoEquipe.iterator().next();

					sessao.setAttribute("equipeAtualizar", equipe);

				}

			}

			atualizarEquipeActionForm.setIdEquipe(equipe.getId().toString());
			atualizarEquipeActionForm.setNomeEquipe(equipe.getNome());
			atualizarEquipeActionForm.setPlacaVeiculo(equipe.getPlacaVeiculo());
			atualizarEquipeActionForm.setCodigoDdd(equipe.getCodigoDdd());
			atualizarEquipeActionForm.setNumeroTelefone(equipe.getNumeroTelefone());
			atualizarEquipeActionForm.setNumeroImei(""+equipe.getNumeroImei());
			
			int carga = equipe.getCargaTrabalho().intValue() / 60;
			atualizarEquipeActionForm.setCargaTrabalhoDia(""+carga);
			
			atualizarEquipeActionForm.setIndicadorUso(""+ equipe.getIndicadorUso());
			atualizarEquipeActionForm.setIndicadorProgramacaoAutomatica(""+equipe.getIndicadorProgramacaoAutomatica());

			if (equipe.getUnidadeOrganizacional() != null) {

				atualizarEquipeActionForm.setIdUnidade(equipe.getUnidadeOrganizacional().getId().toString());
				atualizarEquipeActionForm.setNomeUnidade(equipe.getUnidadeOrganizacional().getDescricao());

			}

			if (equipe.getServicoPerfilTipo() != null) {

				atualizarEquipeActionForm.setIdServicoPerfilTipo(equipe.getServicoPerfilTipo().getId().toString());
				atualizarEquipeActionForm.setDescricaoServicoPerfilTipo(equipe.getServicoPerfilTipo().getDescricao());

			}
			
			if (equipe.getUsuarioRespExecServico() != null) {

				atualizarEquipeActionForm.setCdUsuarioRespExecServico(equipe.getUsuarioRespExecServico().getLogin().toString());
				atualizarEquipeActionForm.setNomeUsuarioRespExecServico(equipe.getUsuarioRespExecServico().getNomeUsuario().toString());
				sessao.setAttribute("usuarioRespExecServicoEncontrado", true);

			}else{
				atualizarEquipeActionForm.setCdUsuarioRespExecServico("");
				atualizarEquipeActionForm.setNomeUsuarioRespExecServico("");
				sessao.removeAttribute("usuarioRespExecServicoEncontrado");
			}

			httpServletRequest.setAttribute("nomeCampo", "nomeEquipe");

			FiltroEquipeComponentes filtroEquipeComponentes = new FiltroEquipeComponentes();
			filtroEquipeComponentes.adicionarParametro(
				new ParametroSimples(FiltroEquipeComponentes.ID_EQUIPE, equipe.getId()));

			filtroEquipeComponentes.adicionarCaminhoParaCarregamentoEntidade("funcionario");

			Collection colecaoEquipeComponentes = 
				fachada.pesquisar(filtroEquipeComponentes, EquipeComponentes.class.getName());

			if (colecaoEquipeComponentes != null && !colecaoEquipeComponentes.isEmpty()) {
				
				atualizarEquipeActionForm.setTamanhoColecao(""+ colecaoEquipeComponentes.size());
				sessao.setAttribute("colecaoEquipeComponentes",colecaoEquipeComponentes);
			} else {
				sessao.removeAttribute("colecaoEquipeComponentes");
			}
			
			FiltroEquipeEquipamentosEspeciais filtroEquipeEquipamentosEspeciais = new FiltroEquipeEquipamentosEspeciais();
			filtroEquipeEquipamentosEspeciais.adicionarParametro(
				new ParametroSimples(FiltroEquipeEquipamentosEspeciais.ID_EQUIPE, equipe.getId()));

			filtroEquipeEquipamentosEspeciais.adicionarCaminhoParaCarregamentoEntidade("equipamentosEspeciais");

			Collection colecaoEquipeEquipamentosEspeciais = 
				fachada.pesquisar(filtroEquipeEquipamentosEspeciais, EquipeEquipamentosEspeciais.class.getName());

			if (colecaoEquipeEquipamentosEspeciais != null && !colecaoEquipeEquipamentosEspeciais.isEmpty()) {
				
				atualizarEquipeActionForm.setTamanhoColecao(""+ colecaoEquipeEquipamentosEspeciais.size());
				Iterator it = colecaoEquipeEquipamentosEspeciais.iterator();
				while(it.hasNext()){
					EquipeEquipamentosEspeciais equipeEquipamentosEspeciais = (EquipeEquipamentosEspeciais) it.next();
					setColecaoEquipeEquipamentosEspeciais(atualizarEquipeActionForm,equipeEquipamentosEspeciais);
				}
				
			} else {
				atualizarEquipeActionForm.setEquipeEquipamentosEspeciais(new ArrayList<EquipeEquipamentosEspeciais>());
			}
			
//			-Erivan Sousa- Verifica se existe usu�rio respons�vel pela execussao do servico associado
//			if(atualizarEquipeActionForm.getCdUsuarioRespExecServico() != null && 
//					!atualizarEquipeActionForm.getCdUsuarioRespExecServico().equals("") && 
//					atualizarEquipeActionForm.getNomeUsuarioRespExecServico() != null && 
//					!atualizarEquipeActionForm.getNomeUsuarioRespExecServico().equals("")){	
//				
//				sessao.setAttribute("usuarioRespExecServicoEncontrado", true);
//			}else{
//				String cdUsuario = (String)sessao.getAttribute("cdUsuarioRespExcServico");
//				Usuario usuario = null;
//				//Verifica se o c�dio do usu�rio foi setado
//				if(cdUsuario != null && !cdUsuario.equals("")){
//					usuario = buscarUsuario(cdUsuario);
//					if(usuario != null){
//						atualizarEquipeActionForm.setNomeUsuarioRespExecServico(usuario.getNomeUsuario());
//						atualizarEquipeActionForm.setCdUsuarioRespExecServico(usuario.getId().toString());
//						httpServletRequest.setAttribute("usuarioRespExecServicoEncontrado", true);
//					}else{
//						atualizarEquipeActionForm.setNomeUsuarioRespExecServico("");
//						atualizarEquipeActionForm.setCdUsuarioRespExecServico("");
//					}
//				}else{
//					//pesquisa pela equipe pra obter o usuario
//					FiltroEquipe filtroEquipe = new FiltroEquipe();
//					filtroEquipe.adicionarParametro(
//						new ParametroSimples(FiltroEquipe.ID, equipe.getId().toString()));
//					filtroEquipe.adicionarCaminhoParaCarregamentoEntidade(FiltroEquipe.USUARIO_RESP_EXECUSSAO_SERVICO);					
//					
//					Collection colecEquipe = 
//						fachada.pesquisar(filtroEquipe,Equipe.class.getName());
//					Equipe equip = (Equipe)colecEquipe.iterator().next();
//					if(equip != null){
//						usuario = equip.getUsuarioRespExecServico(); 
//						if(usuario != null){
//							atualizarEquipeActionForm.setNomeUsuarioRespExecServico(usuario.getNomeUsuario());
//							atualizarEquipeActionForm.setCdUsuarioRespExecServico(usuario.getId().toString());
//							sessao.setAttribute("usuarioRespExecServicoEncontrado", true);
//						}
//					}
//				}			
//			}
		}

		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			httpServletRequest.getParameter("tipoConsulta").equalsIgnoreCase("funcionario")) {
			
			String codigoPesquisa = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricaoPesquisa = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			
			atualizarEquipeActionForm.setIdFuncionario(codigoPesquisa);
			atualizarEquipeActionForm.setNomeFuncionario(descricaoPesquisa);
			atualizarEquipeActionForm.setNomeComponente("");

			retorno = actionMapping.findForward("inserirEquipeComponente");
		}

		// Recupera os valores da funcion�rio do form no pop up de inserir
		// componentes na equipe
		String idFuncionario = atualizarEquipeActionForm.getIdFuncionario();
		String nomeFuncionario = atualizarEquipeActionForm.getNomeFuncionario();

		// Verifica se o usu�rio solicitou a pesquisa de unidade
		if (idFuncionario != null && !idFuncionario.trim().equals("")
				&& (nomeFuncionario == null || nomeFuncionario.trim().equals(""))) {

			retorno = actionMapping.findForward("inserirEquipeComponente");

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(
				new ParametroSimples(FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = 
				fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = 
					(Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);

				atualizarEquipeActionForm.setNomeFuncionario(funcionario.getNome());
				httpServletRequest.setAttribute("nomeCampo", "nomeComponente");

			} else {

				atualizarEquipeActionForm.setIdFuncionario("");
				atualizarEquipeActionForm.setNomeFuncionario("Funcion�rio inexistente");
				httpServletRequest.setAttribute("idFuncionarioNaoEncontrado","true");
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");

			}

		} else if (nomeFuncionario != null && 
				!nomeFuncionario.trim().equals("") && (idFuncionario == null || idFuncionario.trim().equals(""))) {
			
			atualizarEquipeActionForm.setNomeFuncionario("");
		}

		// Verifica se o usu�rio adicionou um componente e em caso afirmativo
		// adiciona o componente � cole��o
		if (httpServletRequest.getParameter("popUpAdicionarComponente") != null) {

			sessao.removeAttribute("popUpAtualizar");

			if (httpServletRequest.getParameter("adicionarComponente") != null) {

				retorno = actionMapping.findForward("exibirAtualizarEquipe");

				Collection colecaoEquipeComponentes = 
					(Collection) sessao.getAttribute("colecaoEquipeComponentes");

				String indicadorResponsavel = atualizarEquipeActionForm.getIndicadorResponsavel();
				String nomeComponente = atualizarEquipeActionForm.getNomeComponente();

				Funcionario funcionario = null;
				if (idFuncionario != null && !idFuncionario.trim().equals("")) {
					
					FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
					filtroFuncionario.adicionarParametro(
						new ParametroSimples(FiltroFuncionario.ID, idFuncionario));

					Collection colecaoFuncionario = 
						fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());

					if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
						funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);
					} else {
						throw new ActionServletException("atencao.pesquisa_inexistente", null,"Funcion�rio");
					}
				}

				if (colecaoEquipeComponentes == null || colecaoEquipeComponentes.isEmpty()) {
					colecaoEquipeComponentes = new ArrayList();
				} else {

					// Verifica se o componente j� existe na cole��o e se est�
					// tentando colocar mais de um respons�vel
					Iterator colecaoEquipeComponentesIterator = colecaoEquipeComponentes.iterator();

					while (colecaoEquipeComponentesIterator.hasNext()) {
						EquipeComponentes equipeComponentesColecao = 
							(EquipeComponentes) colecaoEquipeComponentesIterator.next();

						if (equipeComponentesColecao.getIndicadorResponsavel() == EquipeComponentes.INDICADOR_RESPONSAVEL_SIM
								&& indicadorResponsavel
										.equals(""
												+ EquipeComponentes.INDICADOR_RESPONSAVEL_SIM)) {
							throw new ActionServletException(
									"atencao.responsavel.equipe.ja.existente");
						}

						if (equipeComponentesColecao.getFuncionario() != null
								&& funcionario != null
								&& equipeComponentesColecao.getFuncionario()
										.getId().equals(funcionario.getId())) {
							throw new ActionServletException(
									"atencao.equipe_componente.ja.existente");
						}

					}
				}

				EquipeComponentes equipeComponentes = new EquipeComponentes();
				equipeComponentes.setFuncionario(funcionario);
				equipeComponentes.setIndicadorResponsavel(new Short(
						indicadorResponsavel).shortValue());
				if (nomeComponente != null && !nomeComponente.trim().equals("")) {

					equipeComponentes.setComponentes(nomeComponente);

				} else {
					equipeComponentes.setComponentes(null);
				}

				colecaoEquipeComponentes.add(equipeComponentes);

				atualizarEquipeActionForm.setTamanhoColecao(""
						+ colecaoEquipeComponentes.size());

				sessao.setAttribute("colecaoEquipeComponentes",
						colecaoEquipeComponentes);

			} else {
				retorno = actionMapping.findForward("inserirEquipeComponente");

				atualizarEquipeActionForm.setIndicadorResponsavel("");
				atualizarEquipeActionForm.setIdFuncionario("");
				atualizarEquipeActionForm.setNomeFuncionario("");
				atualizarEquipeActionForm.setNomeComponente("");
			}

		}

		// Verifica se o usu�rio removeu um componente e em caso afirmativo
		// remove o componente da cole��o
		if (httpServletRequest.getParameter("deleteComponente") != null
				&& !httpServletRequest.getParameter("deleteComponente").equals(
						"")) {

			Collection colecaoEquipeComponentes = (Collection) sessao
					.getAttribute("colecaoEquipeComponentes");

			if (colecaoEquipeComponentes != null
					&& !colecaoEquipeComponentes.isEmpty()) {

				int posicaoComponente = new Integer(httpServletRequest
						.getParameter("deleteComponente")).intValue();

				int index = 0;

				Iterator colecaoEquipeComponentesIterator = colecaoEquipeComponentes
						.iterator();

				while (colecaoEquipeComponentesIterator.hasNext()) {

					index++;

					EquipeComponentes equipeComponentes = (EquipeComponentes) colecaoEquipeComponentesIterator
							.next();

					if (index == posicaoComponente) {
						colecaoEquipeComponentes.remove(equipeComponentes);

						atualizarEquipeActionForm.setTamanhoColecao(""
								+ colecaoEquipeComponentes.size());

						sessao.setAttribute("colecaoEquipeComponentes",
								colecaoEquipeComponentes);

						break;
					}
				}
			}
		}

		// Verifica se o usu�rio atualizou um componente e em caso afirmativo
		// remove o componente da cole��o
		if (httpServletRequest.getParameter("popUpAtualizarComponente") != null
				&& !httpServletRequest.getParameter("popUpAtualizarComponente")
						.equals("")) {

			sessao.setAttribute("popUpAtualizar", true);

			int posicaoComponente = 0;

			Collection colecaoEquipeComponentes = (Collection) sessao
					.getAttribute("colecaoEquipeComponentes");

			if (httpServletRequest.getParameter("atualizaComponente") != null
					&& !httpServletRequest.getParameter("atualizaComponente")
							.equals("")) {
				posicaoComponente = new Integer(httpServletRequest
						.getParameter("atualizaComponente")).intValue();
				sessao.setAttribute("posicaoComponente", posicaoComponente);

				if (colecaoEquipeComponentes != null
						&& !colecaoEquipeComponentes.isEmpty()) {

					posicaoComponente = (Integer) sessao
							.getAttribute("posicaoComponente");

					int index = 0;

					Iterator colecaoEquipeComponentesIterator = colecaoEquipeComponentes
							.iterator();

					while (colecaoEquipeComponentesIterator.hasNext()) {

						index++;

						EquipeComponentes equipeComponentes = (EquipeComponentes) colecaoEquipeComponentesIterator
								.next();

						if (index == posicaoComponente) {

							atualizarEquipeActionForm
									.setIndicadorResponsavel(""
											+ equipeComponentes
													.getIndicadorResponsavel());

							if (equipeComponentes.getFuncionario() != null) {

								atualizarEquipeActionForm
										.setIdFuncionario(equipeComponentes
												.getFuncionario().getId()
												.toString());
								atualizarEquipeActionForm
										.setNomeFuncionario(equipeComponentes
												.getFuncionario().getNome());

							} else {
								atualizarEquipeActionForm.setIdFuncionario("");
								atualizarEquipeActionForm
										.setNomeFuncionario("");
							}

							if (equipeComponentes.getComponentes() != null
									&& !equipeComponentes.getComponentes()
											.equals("")) {

								atualizarEquipeActionForm
										.setNomeComponente(equipeComponentes
												.getComponentes());

							} else {
								atualizarEquipeActionForm.setNomeComponente("");
							}

							sessao.setAttribute("colecaoEquipeComponentes",
									colecaoEquipeComponentes);

						}
					}
				}

			}

			if (httpServletRequest.getParameter("atualizarComponente") != null) {

				retorno = actionMapping.findForward("exibirAtualizarEquipe");

				if (colecaoEquipeComponentes != null
						&& !colecaoEquipeComponentes.isEmpty()) {

					if (sessao.getAttribute("posicaoComponente") != null) {

						posicaoComponente = (Integer) sessao
								.getAttribute("posicaoComponente");

					} else {
						posicaoComponente = 0;
					}

					sessao.removeAttribute("posicaoComponente");

					int index = 0;

					Iterator colecaoEquipeComponentesIterator = colecaoEquipeComponentes
							.iterator();

					boolean responsavelExistente = false;

					while (colecaoEquipeComponentesIterator.hasNext()) {

						index++;

						EquipeComponentes equipeComponentes = (EquipeComponentes) colecaoEquipeComponentesIterator
								.next();

						if (index == posicaoComponente) {
							equipeComponentes
									.setIndicadorResponsavel(new Short(
											atualizarEquipeActionForm
													.getIndicadorResponsavel()));

							Funcionario funcionario = null;

							if (idFuncionario != null
									&& !idFuncionario.trim().equals("")) {

								FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
								filtroFuncionario
										.adicionarParametro(new ParametroSimples(
												FiltroFuncionario.ID,
												idFuncionario));

								Collection colecaoFuncionario = fachada
										.pesquisar(filtroFuncionario,
												Funcionario.class.getName());

								if (colecaoFuncionario != null
										&& !colecaoFuncionario.isEmpty()) {
									funcionario = (Funcionario) Util
											.retonarObjetoDeColecao(colecaoFuncionario);
								} else {
									throw new ActionServletException(
											"atencao.pesquisa_inexistente",
											null, "Funcion�rio");
								}

							}

							equipeComponentes
									.setIndicadorResponsavel(new Short(
											atualizarEquipeActionForm
													.getIndicadorResponsavel()));
							equipeComponentes.setFuncionario(funcionario);

							if (atualizarEquipeActionForm.getNomeComponente() != null
									&& !atualizarEquipeActionForm
											.getNomeComponente().trim().equals(
													"")) {

								equipeComponentes
										.setComponentes(atualizarEquipeActionForm
												.getNomeComponente());

							} else {
								equipeComponentes.setComponentes(null);
							}

							if (equipeComponentes.getIndicadorResponsavel() == EquipeComponentes.INDICADOR_RESPONSAVEL_SIM) {
								if (responsavelExistente) {
									throw new ActionServletException(
											"atencao.responsavel.equipe.ja.existente");
								}
								responsavelExistente = true;
							}

							sessao.setAttribute("colecaoEquipeComponentes",
									colecaoEquipeComponentes);

						} else {

							if (equipeComponentes.getIndicadorResponsavel() == EquipeComponentes.INDICADOR_RESPONSAVEL_SIM) {
								if (responsavelExistente) {
									throw new ActionServletException(
											"atencao.responsavel.equipe.ja.existente");
								}
								responsavelExistente = true;
							}

							if (equipeComponentes.getFuncionario() != null
									&& idFuncionario != null
									&& !idFuncionario.trim().equals("")) {
								throw new ActionServletException(
										"atencao.equipe_componente.ja.existente");
							}
						}
					}
				}
			} else {
				retorno = actionMapping.findForward("inserirEquipeComponente");
			}
		}
		
		// Testa se � pra adicionar componente ou n�o
		if (httpServletRequest.getParameter("method") != null) {
			
			if( httpServletRequest.getParameter("method").equals("addEquipamentos") ){
				
				retorno = actionMapping.findForward("inserirEquipamentosEspeciais");
				
				atualizarEquipeActionForm.setEquipamentosEspeciasId("");
				atualizarEquipeActionForm.setDescricao("");
				atualizarEquipeActionForm.setQuantidade("");
				
				FiltroEquipamentosEspeciais filtro = new FiltroEquipamentosEspeciais();
				filtro.adicionarParametro(
					new ParametroSimples(
						FiltroEquipamentosEspeciais.INDICADORUSO, 
						ConstantesSistema.SIM));
				
				Collection colecaoEquipamentosEspeciais = 
					this.getFachada().pesquisar(filtro, EquipamentosEspeciais.class.getName());
				
				httpServletRequest.setAttribute("colecaoEquipamentosEspeciais",colecaoEquipamentosEspeciais);
				
				atualizarEquipeActionForm.setMethod("add");
			}
		}
		
		EquipeEquipamentosEspeciais equipeEquipamentosEspeciais = null;
		
		
		
		if (atualizarEquipeActionForm.getDeleteEquipamento() != null &&
				!atualizarEquipeActionForm.getDeleteEquipamento().equals("")) {
			
			removeEquipeEquipamento(atualizarEquipeActionForm);
		} else{
			if (!atualizarEquipeActionForm.getMethod().equals("")) {
				if ((atualizarEquipeActionForm.getEquipamentosEspeciasId() != null && 
						!atualizarEquipeActionForm.getEquipamentosEspeciasId().equals("")) || 
						(atualizarEquipeActionForm.getQuantidade() != null && 
						!atualizarEquipeActionForm.getQuantidade().equals(""))) {
					
					equipeEquipamentosEspeciais = getEquipeEquipamentosEspeciais(atualizarEquipeActionForm);
					
					
					// Seta componente na cole��o
					setColecaoEquipeEquipamentosEspeciais(atualizarEquipeActionForm,equipeEquipamentosEspeciais);

					// Reseta informa��es vindas do popup
					resetPopupEquipamento(atualizarEquipeActionForm);
					// Faz as valida��es de inser��o de equipe equipamentos especiais
					fachada.validarInsercaoEquipeEquipamentosEspeciais(atualizarEquipeActionForm.getEquipeEquipamentosEspeciais());
					
					atualizarEquipeActionForm.setMethod("");

					// Seta retorno
					retorno = actionMapping.findForward("exibirAtualizarEquipe");
					
				}
			}
		}

		return retorno;

	}
	
	private void setColecaoEquipeEquipamentosEspeciais(AtualizarEquipeActionForm atualizarEquipeActionForm, EquipeEquipamentosEspeciais equipeEquipamentosEspeciais) {
		atualizarEquipeActionForm.getEquipeEquipamentosEspeciais().add(equipeEquipamentosEspeciais);
		atualizarEquipeActionForm.setTamanhoColecaoEquipeEquipamenosEspeciais(atualizarEquipeActionForm.getEquipeEquipamentosEspeciais().size()+"");
	}
	
	/**
	 * Recupera objeto Equipe Componente com informa��es vindas da tela 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param equipeComponentes
	 */
	private EquipeEquipamentosEspeciais getEquipeEquipamentosEspeciais(AtualizarEquipeActionForm atualizarEquipeActionForm) {
		
		EquipeEquipamentosEspeciais equipeEquipamentosEspeciais = new EquipeEquipamentosEspeciais();
		
		FiltroEquipamentosEspeciais filtroEquipamentosEspeciais = new FiltroEquipamentosEspeciais();
		filtroEquipamentosEspeciais.adicionarParametro(new ParametroSimples(
				FiltroEquipamentosEspeciais.ID, atualizarEquipeActionForm.getEquipamentosEspeciasId()));
		
		Collection colecaoEquipamentosEspeciais = Fachada.getInstancia().pesquisar(
				filtroEquipamentosEspeciais, EquipamentosEspeciais.class.getName());
		
		EquipamentosEspeciais equipamentosEspeciais = (EquipamentosEspeciais) colecaoEquipamentosEspeciais
				.iterator().next();
		
		if (atualizarEquipeActionForm.getQuantidade() != null 
				&& !atualizarEquipeActionForm.getQuantidade().equals("")) {
			equipeEquipamentosEspeciais.setQuantidade(new Integer(atualizarEquipeActionForm.getQuantidade()) );
		}
		equipeEquipamentosEspeciais.setEquipamentosEspeciais(equipamentosEspeciais);
		
		equipeEquipamentosEspeciais.setUltimaAlteracao(new Date());
		return equipeEquipamentosEspeciais;
	}
	
	private void resetPopupEquipamento(AtualizarEquipeActionForm atualizarEquipeActionForm) {
		atualizarEquipeActionForm.setEquipamentosEspeciasId("");
		atualizarEquipeActionForm.setQuantidade("");
	}
	
	/**
	 * Remove Equipamento especial da Cole��o 
	 *
	 * @author Nathalia Santos
	 * @date 27/06/2011
	 *
	 * @param inserirEquipeActionForm
	 * @param equipamentosEspeciais
	 */
	private void removeEquipeEquipamento(AtualizarEquipeActionForm atualizarEquipeActionForm) {
		Collection colecaoEquipeEquipamentosEspeciais = new ArrayList();
		int index = 0;
		for (Iterator iter = atualizarEquipeActionForm.getEquipeEquipamentosEspeciais().iterator(); iter
				.hasNext();) {
			index++;
			EquipeEquipamentosEspeciais element = (EquipeEquipamentosEspeciais) iter.next();
			if (index != new Integer(atualizarEquipeActionForm.getDeleteEquipamento()).intValue()) {
				colecaoEquipeEquipamentosEspeciais.add(element);
			}
		}
		atualizarEquipeActionForm.setEquipeEquipamentosEspeciais(colecaoEquipeEquipamentosEspeciais);
		atualizarEquipeActionForm.setTamanhoColecaoEquipeEquipamenosEspeciais(atualizarEquipeActionForm.getEquipeEquipamentosEspeciais().size()+"");
		atualizarEquipeActionForm.setDeleteEquipamento("");
		resetPopupEquipamento(atualizarEquipeActionForm);
	}
	
	/**
	 * Pequisa o usu�rio com o id correspondente ao informado
	 * @author Erivan
	 * @param String codigoUsuario
	 * @return Usuario
	 */
	private Usuario buscarUsuario(String codigoUsuario){
		Usuario usuario = null;
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, codigoUsuario));
		
		Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		
		if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
			usuario = (Usuario) colecaoUsuario.iterator().next();
		}else{
			filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, codigoUsuario));
			colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
				usuario = (Usuario) colecaoUsuario.iterator().next();
			}else{
				filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, codigoUsuario));
				colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
				
			}
		}
		
		return usuario;
	}

}