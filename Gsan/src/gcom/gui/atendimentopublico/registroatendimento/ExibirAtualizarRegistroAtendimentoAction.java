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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar as abas que ser�o respons�veis pelo
 * processo de atualiza��o de um registro de atendimento
 * 
 * @author S�vio Luiz
 * @date 10/08/2006
 */
public class ExibirAtualizarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarRegistroAtendimento");

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// Removendo todos os objetos da sess�o
		sessao.removeAttribute("AtualizarRegistroAtendimentoActionForm");
		sessao.removeAttribute("colecaoMeioSolicitacao");
		sessao.removeAttribute("colecaoSolicitacaoTipo");
		sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
		sessao.removeAttribute("colecaoEnderecos");
		sessao.removeAttribute("idEspecificacaoBase");
		sessao.removeAttribute("ultimaAlteracao");
		sessao.removeAttribute("ordemServico");
		sessao.removeAttribute("colecaoDivisaoEsgoto");
		sessao.removeAttribute("colecaoLocalOcorrencia");
		sessao.removeAttribute("colecaoPavimentoRua");
		sessao.removeAttribute("colecaoPavimentoCalcada");
		sessao.removeAttribute("solicitacaoTipoRelativoFaltaAgua");
		sessao.removeAttribute("solicitacaoTipoRelativoAreaEsgoto");
		sessao.removeAttribute("colecaoBairroArea");
		sessao.removeAttribute("habilitarAlteracaoEndereco");
		sessao.removeAttribute("desabilitarMunicipioBairro");
		sessao.removeAttribute("desabilitarDescricaoLocalOcorrencia");
		sessao.removeAttribute("colecaoRASolicitanteRemovidas");
		sessao.removeAttribute("colecaoRASolicitante");
		sessao.removeAttribute("osAutomatica");
		sessao.removeAttribute("enderecoPertenceImovel");
		sessao.removeAttribute("servicoTipo");
		sessao.removeAttribute("colecaoRegistroAtendimentoAnexo");

		StatusWizard statusWizard = null;

		String idRegistroAtendimento = null;

		if (httpServletRequest.getParameter("desfazer") == null) {

			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idRegistroAtendimento = (String) httpServletRequest
						.getParameter("idRegistroAtualizacao");
			} else {
				idRegistroAtendimento = (String) httpServletRequest
						.getAttribute("idRegistroAtualizacao");
			}

			// verifica se chegou no atualizar imovel atraves da tela de filtrar
			// devido a um unico registro
			// ou atraves da lista de imoveis no manter imovel
			if (httpServletRequest.getAttribute("atualizar") != null) {
				statusWizard = new StatusWizard(
						"atualizarRegistroAtendimentoWizardAction",
						"concluirAtualizarRegistroAtendimentoAction",
						"cancelarAtualizarRegistroAtendimentoAction",
						"exibirFiltrarRegistroAtendimentoAction",
						"exibirAtualizarRegistroAtendimentoAction.do",
						idRegistroAtendimento);
			} else if (httpServletRequest.getParameter("sucesso") != null) {
				statusWizard = new StatusWizard(
						"atualizarRegistroAtendimentoWizardAction",
						"concluirAtualizarRegistroAtendimentoAction",
						"cancelarAtualizarRegistroAtendimentoAction",
						"exibirFiltrarRegistroAtendimentoAction",
						"exibirAtualizarRegistroAtendimentoAction.do",
						idRegistroAtendimento);
			} else {
				statusWizard = new StatusWizard(
						"atualizarRegistroAtendimentoWizardAction",
						"concluirAtualizarRegistroAtendimentoAction",
						"cancelarAtualizarRegistroAtendimentoAction", "",
						"exibirAtualizarRegistroAtendimentoAction.do",
						idRegistroAtendimento);
				statusWizard
						.setCaminhoActionVoltarFiltro("exibirConsultarRegistroAtendimentoAction.do?numeroRA="
								+ idRegistroAtendimento);
			}

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							1,
							"DadosGeraisPrimeiraAbaA.gif",
							"DadosGeraisPrimeiraAbaD.gif",
							"exibirAtualizarRegistroAtendimentoDadosGeraisAction",
							"atualizarRegistroAtendimentoDadosGeraisAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							2,
							"LocalOcorrenciaIntervaloAbaA.gif",
							"LocalOcorrenciaIntervaloAbaD.gif",
							"exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction",
							"atualizarRegistroAtendimentoDadosLocalOcorrenciaAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							3,
							"SolicitanteUltimaAbaA.gif",
							"SolicitanteUltimaAbaD.gif",
							"exibirAtualizarRegistroAtendimentoDadosSolicitanteAction",
							"atualizarRegistroAtendimentoDadosSolicitanteAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							4,
							"Anexos02.gif",
							"Anexos.gif",
							"exibirAtualizarRegistroAtendimentoAnexosAction",
							"atualizarRegistroAtendimentoAnexosAction"));

		} else {
			statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");

			idRegistroAtendimento = statusWizard.getId();
		}

		/** *************************************************************************************** */
		/**
		 * C�digo para criar um actionForm totalmente novo - Esta foi a solu��o
		 * encontrada para *
		 */
		/**
		 * a passagem do inserir direto para o atualizar, fazendo as
		 * substitui��o dos dados do *
		 */
		/**
		 * form de mesmo nome corretamente, � preciso pegar o form pelo
		 * httpServletRequest no *
		 */
		/**
		 * exibir da primeira aba (neste caso
		 * ExibirAtualizarClienteNomeTipoAction) *
		 */
		/** *************************************************************************************** */

		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = new AtualizarRegistroAtendimentoActionForm();

		Fachada fachada = Fachada.getInstancia();

		// [SF0012] - verificar possibilidade de atualiza��o do registro de
		// atendimento
		UnidadeOrganizacional unidadeAtualRA = fachada
				.verificarPossibilidadeAtualizacaoRA(new Integer(
						idRegistroAtendimento), usuarioLogado.getId());

		// recupera a unidade do [UC0418] - Obter Unidade Atual do RA
		if (unidadeAtualRA != null && !unidadeAtualRA.equals("")) {
			// param�tros da 2 aba
			atualizarRegistroAtendimentoActionForm
					.setIdUnidadeAtual(unidadeAtualRA.getId().toString());
			atualizarRegistroAtendimentoActionForm
					.setDescricaoUnidadeAtual(unidadeAtualRA.getDescricao());
		}

		// pesquisa os par�metros do registro atendimento escolhido para
		// atualiza��o
		Object[] parmsRA = fachada
				.pesquisarParmsRegistroAtendimento(new Integer(
						idRegistroAtendimento));

		Integer idOrdemServico = fachada
				.verificarOrdemServicoParaRA(new Integer(idRegistroAtendimento));
		if (idOrdemServico != null) {
			atualizarRegistroAtendimentoActionForm
					.setIdOrdemServico(idOrdemServico.toString());
		}

		if (parmsRA != null) {
			// param�tros da 1 aba

			// id do RA
			if (parmsRA[0] != null) {
				atualizarRegistroAtendimentoActionForm
						.setNumeroRA(((Integer) parmsRA[0]).toString());
			}
			// indicador atendimento on-line
			if (parmsRA[1] != null) {
				atualizarRegistroAtendimentoActionForm
						.setTipo(((Short) parmsRA[1]).toString());
			}
			// data registro atendimento
			if (parmsRA[2] != null) {
				Date dataAtendimento = (Date) parmsRA[2];
				// formata a data
				atualizarRegistroAtendimentoActionForm.setDataAtendimento(Util
						.formatarData(dataAtendimento));
				// formata a hora
				atualizarRegistroAtendimentoActionForm.setHora(Util
						.formatarHoraSemSegundos(dataAtendimento));
			}
			// tempo de espera inicial para atendimento
			if (parmsRA[3] != null) {
				Date tempoInicioEspera = (Date) parmsRA[3];
				// formata a hora
				atualizarRegistroAtendimentoActionForm
						.setTempoEsperaInicial(Util
								.formatarHoraSemSegundos(tempoInicioEspera));
			}
			// tempo de espera final para atendimento
			if (parmsRA[4] != null) {
				Date tempoFimEspera = (Date) parmsRA[4];
				// formata a hora
				atualizarRegistroAtendimentoActionForm.setTempoEsperaFinal(Util
						.formatarHoraSemSegundos(tempoFimEspera));
			}
			// [UC0421 - Obter Unidade Atendimento do RA]
			UnidadeOrganizacional unidadeOrganizacional = fachada
					.obterUnidadeAtendimentoRA(new Integer(
							idRegistroAtendimento));
			if (unidadeOrganizacional != null
					&& !unidadeOrganizacional.equals("")) {
				// seta o id da unidade organizacional
				atualizarRegistroAtendimentoActionForm
						.setUnidade(unidadeOrganizacional.getId().toString());
				// seta a descri��o da unidade organizacional
				atualizarRegistroAtendimentoActionForm
						.setDescricaoUnidade(unidadeOrganizacional
								.getDescricao());
			}
			// id do meio de solicita��o
			if (parmsRA[5] != null) {
				atualizarRegistroAtendimentoActionForm
						.setMeioSolicitacao(((Integer) parmsRA[5]).toString());
			}
			// id do tipo de solicita��o
			if (parmsRA[6] != null) {
				atualizarRegistroAtendimentoActionForm
						.setTipoSolicitacao(((Integer) parmsRA[6]).toString());
			}
			// id do tipo de solicita��o especificacao
			if (parmsRA[7] != null) {
				atualizarRegistroAtendimentoActionForm
						.setEspecificacao(((Integer) parmsRA[7]).toString());
				// manda para a sess�o o id da especifica��o para verificar se
				// ser� gerado a ordem de servi�o ou n�o dependendo da mudan�a
				// da especifica��o
				sessao
						.setAttribute("idEspecificacaoBase",
								(Integer) parmsRA[7]);
				if(parmsRA[36] != null){
					sessao
					.setAttribute("servicoTipo",
							(Integer) parmsRA[36]);
				
				}
			}
			// data prevista
			if (parmsRA[8] != null) {
				Date dataPrevista = (Date) parmsRA[8];
				atualizarRegistroAtendimentoActionForm.setDataPrevista(Util
						.formatarData(dataPrevista));
			}
			
			
			// observa��o da RA
			if (parmsRA[9] != null) {
				atualizarRegistroAtendimentoActionForm
						.setObservacao((String) parmsRA[9]);
			}
			// id do im�vel
			if (parmsRA[10] != null) {
				Integer idImovel = (Integer) parmsRA[10];
				atualizarRegistroAtendimentoActionForm.setIdImovel(idImovel
						.toString());
				// inscri��o do im�vel
				String inscricaoImovel = fachada
						.pesquisarInscricaoImovel(idImovel);
				if (inscricaoImovel != null) {
					atualizarRegistroAtendimentoActionForm
							.setInscricaoImovel(inscricaoImovel);
				}
				Imovel imovel = fachada.pesquisarImovelParaEndereco(idImovel);
				if (imovel != null && !imovel.equals("")) {
					Collection colecaoEnderecos = new ArrayList();
					colecaoEnderecos.add(imovel);
					sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
					sessao.setAttribute("enderecoPertenceImovel", "OK");
				}

			} else {
				// Recupera o objeto registro atendimento com os parametros
				// necess�rio
				// para mostrar o endere�o
				RegistroAtendimento registroAtendimento = fachada
						.pesquisarRegistroAtendimentoEndereco(new Integer(
								idRegistroAtendimento));
				if (registroAtendimento != null
						&& !registroAtendimento.equals("")) {
					Collection colecaoEnderecos = new ArrayList();
					// seta os campos de registro atendimento no im�vel
					if (registroAtendimento.getLogradouroBairro() != null
							&& !registroAtendimento.getLogradouroBairro()
									.equals("")
							&& registroAtendimento.getLogradouroCep() != null
							&& !registroAtendimento.getLogradouroCep().equals(
									"")) {
						Imovel imovel = new Imovel();
						imovel.setLogradouroBairro(registroAtendimento
								.getLogradouroBairro());
						imovel.setLogradouroCep(registroAtendimento
								.getLogradouroCep());
						imovel.setComplementoEndereco(registroAtendimento
								.getComplementoEndereco());
						imovel.setNumeroImovel(registroAtendimento.getNumeroImovel());
						imovel.setPerimetroInicial(registroAtendimento.getPerimetroInicial());
						imovel.setPerimetroFinal(registroAtendimento.getPerimetroFinal());
						colecaoEnderecos.add(imovel);
						sessao.setAttribute("colecaoEnderecos",
								colecaoEnderecos);
					}
				}
			}
			// ponto de refer�ncia do RA
			if (parmsRA[11] != null) {
				atualizarRegistroAtendimentoActionForm
						.setPontoReferencia((String) parmsRA[11]);
			}
			// id bairro area
			if (parmsRA[12] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdBairroArea(((Integer) parmsRA[12]).toString());
			}
			// id do municipio
			if (parmsRA[13] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdMunicipio(((Integer) parmsRA[13]).toString());
			}
			// nome do munic�pio
			if (parmsRA[14] != null) {
				atualizarRegistroAtendimentoActionForm
						.setDescricaoMunicipio((String) parmsRA[14]);
			}
			// id do bairro
			if (parmsRA[15] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdBairro(((Integer) parmsRA[15]).toString());
			}
			// codigo bairro
			if (parmsRA[16] != null) {
				atualizarRegistroAtendimentoActionForm
						.setCdBairro(((Integer) parmsRA[16]).toString());
			}
			// descri��o bairro
			if (parmsRA[17] != null) {
				atualizarRegistroAtendimentoActionForm
						.setDescricaoBairro((String) parmsRA[17]);
			}
			// id localidade
			if (parmsRA[18] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdLocalidade(((Integer) parmsRA[18]).toString());
			}
			// descri��o localidade
			if (parmsRA[19] != null) {
				atualizarRegistroAtendimentoActionForm
						.setDescricaoLocalidade((String) parmsRA[19]);
			}
			// id do setor comercial
			if (parmsRA[20] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdSetorComercial(((Integer) parmsRA[20]).toString());
			}
			// c�digo setor comercial
			if (parmsRA[21] != null) {
				atualizarRegistroAtendimentoActionForm
						.setCdSetorComercial(((Integer) parmsRA[21]).toString());
			}
			// descri��o setor comercial
			if (parmsRA[22] != null) {
				atualizarRegistroAtendimentoActionForm
						.setDescricaoSetorComercial((String) parmsRA[22]);
			}
			// id quadra
			if (parmsRA[23] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdQuadra(((Integer) parmsRA[23]).toString());
			}
			// numero quadra
			if (parmsRA[24] != null) {
				atualizarRegistroAtendimentoActionForm
						.setNnQuadra(((Integer) parmsRA[24]).toString());
			}
			// id divis�o esgoto
			if (parmsRA[25] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdDivisaoEsgoto(((Integer) parmsRA[25]).toString());
			}
			// id local ocorrencia
			if (parmsRA[26] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdLocalOcorrencia(((Integer) parmsRA[26])
								.toString());
			}

			// id pavimento rua
			if (parmsRA[27] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdPavimentoRua(((Integer) parmsRA[27]).toString());
			}
			// id pavimento cal�ada
			if (parmsRA[28] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdPavimentoCalcada(((Integer) parmsRA[28])
								.toString());
			}
			// descri��o local ocorr�ncia
			if (parmsRA[29] != null) {
				atualizarRegistroAtendimentoActionForm
						.setDescricaoLocalOcorrencia((String) parmsRA[29]);
			}
			// indicador matricula da tabela Solicita��o Tipo Especifica��o
			if (parmsRA[30] != null) {
				Integer indMatricula = (Integer) parmsRA[30];
				if (indMatricula
						.equals(SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_OBRIGATORIO)) {
					atualizarRegistroAtendimentoActionForm
							.setImovelObrigatorio("SIM");
				} else {
					if (indMatricula
							.equals(SolicitacaoTipoEspecificacao.INDICADOR_MATRICULA_NAO_OBRIGATORIO)) {
						atualizarRegistroAtendimentoActionForm
								.setImovelObrigatorio("NAO");
						sessao.setAttribute("habilitarAlteracaoEndereco","SIM");
					}
				}
				atualizarRegistroAtendimentoActionForm
						.setIndMatricula(indMatricula.toString());
			}
			// indicador pavimento rua da tabela Solicita��o Tipo Especifica��o
			if (parmsRA[31] != null) {
				Short indPavimentoRua = (Short) parmsRA[31];
				if (indPavimentoRua.equals(new Short("1"))) {
					atualizarRegistroAtendimentoActionForm
							.setPavimentoRuaObrigatorio("SIM");
				} else {
					if (indPavimentoRua.equals(new Short("2"))) {
						atualizarRegistroAtendimentoActionForm
								.setPavimentoRuaObrigatorio("NAO");
					}
				}
			}
			// indicador pavimento cal�ada da tabela Solicita��o Tipo
			// Especifica��o
			if (parmsRA[32] != null) {
				Short indPavimentoCalcada = (Short) parmsRA[32];
				if (indPavimentoCalcada.equals(new Short("1"))) {
					atualizarRegistroAtendimentoActionForm
							.setPavimentoCalcadaObrigatorio("SIM");
				} else {
					if (indPavimentoCalcada.equals(new Short("2"))) {
						atualizarRegistroAtendimentoActionForm
								.setPavimentoCalcadaObrigatorio("NAO");
					}
				}
			}
			// descri��o local ocorr�ncia
			if (parmsRA[33] != null) {
				atualizarRegistroAtendimentoActionForm
						.setIndFaltaAgua(((Short) parmsRA[33]).toString());
			}
			// descri��o local ocorr�ncia
			if (parmsRA[34] != null) {
				sessao.setAttribute("ultimaAlteracao", (Date) parmsRA[34]);
			}
			//Numera��o Manual
			if (parmsRA[35] != null) {
				atualizarRegistroAtendimentoActionForm.setNumeroAtendimentoManual(
				Util.formatarNumeracaoRAManual((Integer) parmsRA[35]));
			}
			
			// Valor Sugerido
			if(parmsRA[37] != null) {
				atualizarRegistroAtendimentoActionForm.setValorSugerido(
						Util.formatarMoedaReal((BigDecimal) parmsRA[37]));
			}
			
			// GIS Coordenada Norte
			if (parmsRA[38] != null) {
				atualizarRegistroAtendimentoActionForm
						.setNnCoordenadaNorte(((BigDecimal) parmsRA[38]).toString());
			}
			
			//GIS Coordenada Leste
			if (parmsRA[39] != null) {
				atualizarRegistroAtendimentoActionForm
						.setNnCoordenadaLeste(((BigDecimal) parmsRA[39]).toString());
			}
			//GIS Diametro
			if (parmsRA[40] != null) {
				atualizarRegistroAtendimentoActionForm
						.setNnDiametro(((BigDecimal) parmsRA[40]).toString());
			}
		}

		/*
		 * if (usuarioParaAtualizar.getId() != null &&
		 * !usuarioParaAtualizar.getId().equals("")) {
		 * statusWizard.adicionarItemHint("C�digo:", usuarioParaAtualizar
		 * .getId().toString()); } if (usuarioParaAtualizar.getNomeUsuario() !=
		 * null && !usuarioParaAtualizar.getNomeUsuario().equals("")) {
		 * statusWizard.adicionarItemHint("Nome:", usuarioParaAtualizar
		 * .getNomeUsuario()); } if (usuarioParaAtualizar.getUsuarioTipo() !=
		 * null && !usuarioParaAtualizar.getUsuarioTipo().equals("")) { if
		 * (usuarioParaAtualizar.getUsuarioTipo().getId() != null &&
		 * !usuarioParaAtualizar.getUsuarioTipo().equals("")) {
		 * statusWizard.adicionarItemHint("Tipo:", usuarioParaAtualizar
		 * .getUsuarioTipo().getDescricao()); } } if
		 * (usuarioParaAtualizar.getUsuarioAbrangencia() != null &&
		 * !usuarioParaAtualizar.getUsuarioAbrangencia().equals("")) { if
		 * (usuarioParaAtualizar.getUsuarioAbrangencia().getId() != null &&
		 * !usuarioParaAtualizar.getUsuarioAbrangencia().equals("")) {
		 * statusWizard.adicionarItemHint("Abrang�ncia:",
		 * usuarioParaAtualizar.getUsuarioAbrangencia() .getDescricao()); } }
		 */
		sessao.setAttribute("statusWizard", statusWizard);

		httpServletRequest.setAttribute(
				"AtualizarRegistroAtendimentoActionForm",
				atualizarRegistroAtendimentoActionForm);

		return retorno;
	}
}
