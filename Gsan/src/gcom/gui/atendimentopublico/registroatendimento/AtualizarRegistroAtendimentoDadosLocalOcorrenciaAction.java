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

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.VerificarRAFaltaAguaHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * Esta classe tem por finalidade validar as informa��es da segunda aba do
 * processo de atualiza��o de um registro de atendimento
 * 
 * @author S�vio Luiz
 * @date 10/08/2006
 */
public class AtualizarRegistroAtendimentoDadosLocalOcorrenciaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		sessao.removeAttribute("gis");
		
		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;

		// -----------------------------------------------------------------------
		// [FS0040] - Validar Preenchimento dos campos

		String idImovel = atualizarRegistroAtendimentoActionForm.getIdImovel();
		String pontoReferencia = atualizarRegistroAtendimentoActionForm
				.getPontoReferencia();
		String idMunicipio = atualizarRegistroAtendimentoActionForm
				.getIdMunicipio();
		String descricaoMunicipio = atualizarRegistroAtendimentoActionForm
				.getDescricaoMunicipio();
		String cdBairro = atualizarRegistroAtendimentoActionForm.getCdBairro();
		String descricaoBairro = atualizarRegistroAtendimentoActionForm
				.getDescricaoBairro();
		String idAreaBairro = atualizarRegistroAtendimentoActionForm
				.getIdBairroArea();
		String idlocalidade = atualizarRegistroAtendimentoActionForm
				.getIdLocalidade();
		String descricaoLocalidade = atualizarRegistroAtendimentoActionForm
				.getDescricaoLocalidade();
		String cdSetorComercial = atualizarRegistroAtendimentoActionForm
				.getCdSetorComercial();
		String descricaoSetorComercial = atualizarRegistroAtendimentoActionForm
				.getDescricaoSetorComercial();
		String numeroQuadra = atualizarRegistroAtendimentoActionForm
				.getNnQuadra();
		String idDivisaoEsgoto = atualizarRegistroAtendimentoActionForm
				.getIdDivisaoEsgoto();
		String idLocalOcorrencia = atualizarRegistroAtendimentoActionForm
				.getIdLocalOcorrencia();
		String idPavimentoRua = atualizarRegistroAtendimentoActionForm
				.getIdPavimentoRua();
		String idPavimentoCalcada = atualizarRegistroAtendimentoActionForm
				.getIdPavimentoCalcada();
		String descricaoLocalOcorrencia = atualizarRegistroAtendimentoActionForm
				.getDescricaoLocalOcorrencia();
		String imovelObrigatorio = atualizarRegistroAtendimentoActionForm
				.getImovelObrigatorio();
		String pavimentoRuaObrigatorio = atualizarRegistroAtendimentoActionForm
				.getPavimentoRuaObrigatorio();
		String pavimentoCalcadaObrigatorio = atualizarRegistroAtendimentoActionForm
				.getPavimentoCalcadaObrigatorio();
		String solicitacaoTipoRelativoFaltaAgua = (String) sessao
				.getAttribute("solicitacaoTipoRelativoFaltaAgua");
		String solicitacaoTipoRelativoAreaEsgoto = (String) sessao
				.getAttribute("solicitacaoTipoRelativoAreaEsgoto");

		String desabilitarMunicipioBairro = (String) sessao
				.getAttribute("desabilitarMunicipioBairro");
		String indRuaLocalOcorrencia = atualizarRegistroAtendimentoActionForm
				.getIndRuaLocalOcorrencia();
		String indCalcadaLocalOcorrencia = atualizarRegistroAtendimentoActionForm
				.getIndCalcadaLocalOcorrencia();
		
		String idEspecificacao = atualizarRegistroAtendimentoActionForm.getEspecificacao();
		String numeroRA = atualizarRegistroAtendimentoActionForm.getNumeroRA();
		
		
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		
		if(atualizarRegistroAtendimentoActionForm.getParecerUnidadeDestino() != null && 
				!atualizarRegistroAtendimentoActionForm.getParecerUnidadeDestino().equals("") && 
				atualizarRegistroAtendimentoActionForm.getParecerUnidadeDestino().length() > 400){
					
			String[] msg = new String[2];
			msg[0]="Parecer";
			msg[1]="400";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}		

		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = 
			new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(
			new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID, 
				atualizarRegistroAtendimentoActionForm.getEspecificacao()));
		
		Collection colecaoSolicitacaoTipoEspecificacao = 
			this.getFachada()
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());

		SolicitacaoTipoEspecificacao especificacao = 
			(SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);

		Collection colecaoPagamento = null;
		
		if (sessao.getAttribute("colecaoPagamentosDuplicidade") != null){
			colecaoPagamento = (Collection) sessao.getAttribute("colecaoPagamentosDuplicidade");
		}		

		fachada.validarCamposObrigatoriosRA_2ABA(idImovel, pontoReferencia,
				idMunicipio, descricaoMunicipio, cdBairro, descricaoBairro,
				idAreaBairro, idlocalidade, descricaoLocalidade,
				cdSetorComercial, descricaoSetorComercial, numeroQuadra,
				idDivisaoEsgoto, "", "",
				idLocalOcorrencia, idPavimentoRua, idPavimentoCalcada,
				descricaoLocalOcorrencia, imovelObrigatorio,
				pavimentoRuaObrigatorio, pavimentoCalcadaObrigatorio,
				solicitacaoTipoRelativoFaltaAgua,
				solicitacaoTipoRelativoAreaEsgoto, desabilitarMunicipioBairro,
				indRuaLocalOcorrencia, indCalcadaLocalOcorrencia, new Integer(idEspecificacao), 
				new Integer(numeroRA), colecaoEnderecos,especificacao,colecaoPagamento, usuarioLogado);

		// -----------------------------------------------------------------------

		// valida os campos de enter(caso tenha mudado algum valor validar)
		ActionForward retornoAtencao = validarCamposEnter(
				atualizarRegistroAtendimentoActionForm, fachada,
				httpServletRequest, actionMapping, sessao);
		if (retornoAtencao != null) {
			retorno = retornoAtencao;
		} else {

			// caso seja a primeira vez ent�o continua est� nulo
			// caso seja a segunda vez continua est� com SIM, ai chama o
			// sub-fluxo
			// [SB0017] denovo
			// na terceira vez em diante continua recebe o valor N�O e ai n�o
			// chama
			// mais o
			// subFluxo
			String continua = httpServletRequest.getParameter("continua");

			// Verificando direcionamento do processo
			String telaOpcaoConsulta = httpServletRequest
					.getParameter("telaOpcao");

			if (telaOpcaoConsulta != null && !telaOpcaoConsulta.equals("")) {

				Collection colecaoEndereco = (Collection) sessao
						.getAttribute("colecaoEnderecos");

				if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {

					Object endereco = Util
							.retonarObjetoDeColecao(colecaoEndereco);
					Integer idLogradouroBairro = null;
					Integer idLogradouroCep = null;
					if (endereco instanceof Imovel) {
						Imovel imovel = (Imovel) endereco;
						idLogradouroBairro = imovel.getLogradouroBairro()
								.getId();
						idLogradouroCep = imovel.getLogradouroCep().getId();
					} else {
						if (endereco instanceof RegistroAtendimento) {
							RegistroAtendimento ra = (RegistroAtendimento) endereco;
							idLogradouroBairro = ra.getLogradouroBairro()
									.getId();
							idLogradouroCep = ra.getLogradouroCep().getId();
						}
					}

					// [SB0008] Verifica Exist�ncia de Registro de Atendimento
					// Pendente para o Local da Ocorr�ncia
					RegistroAtendimento ra = fachada
							.verificaExistenciaRAPendenteLocalOcorrencia(
									new Integer(
											atualizarRegistroAtendimentoActionForm
													.getEspecificacao()),
									idLogradouroBairro, idLogradouroCep);

					if (ra != null) {
						httpServletRequest.setAttribute("atencao",
								"Existe Registro de Atendimento de "
										+ ra.getSolicitacaoTipoEspecificacao()
												.getDescricao()
										+ " em aberto para este endere�o "
										+ ra.getEnderecoFormatadoAbreviado());

						// URL da pr�xima ABA
						httpServletRequest
								.setAttribute(
										"proximaAba",
										"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosSolicitanteAction");

						// URL da ABA anterior
						httpServletRequest
								.setAttribute(
										"voltarAba",
										"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction");

						// Tipo chamada (Popup ou tela convencional)
						httpServletRequest.setAttribute("tipoChamada", "popup");

						// Label bot�o utilit�rio
						httpServletRequest.setAttribute("labelBotao",
								"Consultar RAs Pendentes");

						// URL bot�o utilit�rio
						httpServletRequest
								.setAttribute(
										"urlBotao",
										"pesquisarRegitrosAtendimentoPendentesLocalOcorrenciaAction.do?idEspecificacao="
												+ ra
														.getSolicitacaoTipoEspecificacao()
														.getId()
												+ "&idLogradouroBairro="
												+ ra.getLogradouroBairro()
														.getId()
												+ "&idLogradouroCep="
												+ ra.getLogradouroCep().getId());

						retorno = actionMapping
								.findForward("telaOpcaoConsultar");
					}
				}
				if (httpServletRequest.getAttribute("urlBotao") == null
						|| httpServletRequest.getAttribute("urlBotao").equals(
								"")) {
					// [SB0017] - Verifica Registro de Atendimento de Falta de
					// �gua

					retorno = registroAtendimentoFaltaAgua(actionMapping,
							httpServletRequest,
							atualizarRegistroAtendimentoActionForm, fachada,
							continua);
				}
			} else {
				// [SB0017] - Verifica Registro de Atendimento de Falta de �gua
				if (continua != null && continua.equals("SIM")) {
					retorno = registroAtendimentoFaltaAgua(actionMapping,
							httpServletRequest,
							atualizarRegistroAtendimentoActionForm, fachada,
							continua);
				}
			}
		}

		return retorno;
	}

	private ActionForward registroAtendimentoFaltaAgua(
			ActionMapping actionMapping,
			HttpServletRequest httpServletRequest,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			Fachada fachada, String continua) {

		ActionForward retorno = null;

		// [SB0017] - Verifica Registro de Atendimento de Falta de �gua
		Integer idRegistroAtendimento = null;
		if (atualizarRegistroAtendimentoActionForm.getNumeroRA() != null
				&& !atualizarRegistroAtendimentoActionForm.getNumeroRA()
						.equals("")) {
			idRegistroAtendimento = new Integer(
					atualizarRegistroAtendimentoActionForm.getNumeroRA());
		}
		Date dataAtendimento = null;
		if (atualizarRegistroAtendimentoActionForm.getDataAtendimento() != null
				&& !atualizarRegistroAtendimentoActionForm.getDataAtendimento()
						.equals("")) {
			dataAtendimento = Util
					.converteStringParaDate(atualizarRegistroAtendimentoActionForm
							.getDataAtendimento());
		}
		Integer idBairroArea = null;
		if (atualizarRegistroAtendimentoActionForm.getIdBairroArea() != null
				&& !atualizarRegistroAtendimentoActionForm.getIdBairroArea()
						.equals("")) {
			idBairroArea = new Integer(atualizarRegistroAtendimentoActionForm
					.getIdBairroArea());
		}
		Integer idEspecificacao = null;
		if (atualizarRegistroAtendimentoActionForm.getEspecificacao() != null
				&& !atualizarRegistroAtendimentoActionForm.getEspecificacao()
						.equals("")) {
			idEspecificacao = new Integer(
					atualizarRegistroAtendimentoActionForm.getEspecificacao());
		}
		Integer idBairro = null;
		if (atualizarRegistroAtendimentoActionForm.getIdBairro() != null
				&& !atualizarRegistroAtendimentoActionForm.getIdBairro()
						.equals("")) {
			idBairro = new Integer(atualizarRegistroAtendimentoActionForm
					.getIdBairro());
		}

		Short indicadorFaltaAgua = null;
		if (atualizarRegistroAtendimentoActionForm.getIndFaltaAgua() != null
				&& !atualizarRegistroAtendimentoActionForm.getIndFaltaAgua()
						.equals("")) {
			indicadorFaltaAgua = new Short(
					atualizarRegistroAtendimentoActionForm.getIndFaltaAgua());
		}

		Integer indicadorMatricula = null;
		if (atualizarRegistroAtendimentoActionForm.getIndMatricula() != null
				&& !atualizarRegistroAtendimentoActionForm.getIndMatricula()
						.equals("")) {
			indicadorMatricula = new Integer(
					atualizarRegistroAtendimentoActionForm.getIndMatricula());
		}

		VerificarRAFaltaAguaHelper verificarRAFaltaAguaHelper = fachada
				.verificarRegistroAtendimentoFaltaAgua(idRegistroAtendimento,
						dataAtendimento, idBairroArea, idBairro,
						idEspecificacao, indicadorFaltaAgua,
						indicadorMatricula, continua);
		if (verificarRAFaltaAguaHelper != null
				&& !verificarRAFaltaAguaHelper.equals("")) {
			// caso o caso de uso seja [UC0440] - Consultar Programa��o de
			// Abastecimento/Manuten��o
			if (verificarRAFaltaAguaHelper.getCasoUso1() != null
					&& verificarRAFaltaAguaHelper.getCasoUso1().equals(
							VerificarRAFaltaAguaHelper.CONSULTAR_PROGRAMACAO)) {
				httpServletRequest.setAttribute("atencao",
						verificarRAFaltaAguaHelper.getMensagem());

				if (continua == null) {
					// URL da pr�xima ABA
					httpServletRequest
							.setAttribute(
									"proximaAba",
									"/gsan/atualizarRegistroAtendimentoWizardAction.do?destino=3&continua=SIM&action=atualizarRegistroAtendimentoDadosLocalOcorrenciaAction");
					// URL da ABA anterior
					httpServletRequest
							.setAttribute(
									"voltarAba",
									"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction");
				} else {
					// URL da pr�xima ABA
					httpServletRequest
							.setAttribute(
									"proximaAba",
									"/gsan/atualizarRegistroAtendimentoWizardAction.do?continua=N�O&destino=3&action=atualizarRegistroAtendimentoDadosLocalOcorrenciaAction");
					// URL da ABA anterior
					httpServletRequest
							.setAttribute(
									"voltarAba",
									"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction");
				}

				// Tipo chamada (Popup ou tela convencional)
				httpServletRequest.setAttribute("tipoChamada", "popup");

				// Label bot�o utilit�rio
				httpServletRequest.setAttribute("labelBotao",
						"Abastecimento/Manuten��o");

				// URL bot�o utilit�rio
				// [UC0440] - Consultar Programa��o de Abastecimento/Manutento
				httpServletRequest.setAttribute("urlBotao",
						"exibirConsultarProgramacaoAbastecimentoManutencaoAction.do?idMunicipio="
								+ atualizarRegistroAtendimentoActionForm
										.getIdMunicipio()
								+ "&codigoBairro="
								+ atualizarRegistroAtendimentoActionForm
										.getCdBairro()
								+ "&areaBairro="
								+ atualizarRegistroAtendimentoActionForm
										.getIdBairroArea());

				retorno = actionMapping.findForward("telaOpcaoConsultar");
			}

			// caso o caso de uso seja [UC0435] - Encerrar Registro de
			// Atendimento
			if (verificarRAFaltaAguaHelper.getCasoUso1() != null
					&& verificarRAFaltaAguaHelper
							.getCasoUso1()
							.equals(
									VerificarRAFaltaAguaHelper.ENCERRAR_REGISTRO_ATENDIMENTO)) {
				httpServletRequest.setAttribute("atencao",
						verificarRAFaltaAguaHelper.getMensagem());

				// URL da pr�xima ABA
				httpServletRequest
						.setAttribute(
								"proximaAba",
								"/gsan/atualizarRegistroAtendimentoWizardAction.do?continua=N�O&destino=3&action=atualizarRegistroAtendimentoDadosLocalOcorrenciaAction");
				// URL da ABA anterior
				httpServletRequest
						.setAttribute(
								"voltarAba",
								"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction");

				// Tipo chamada (Popup ou tela convencional)
				httpServletRequest.setAttribute("tipoChamada", "tela");

				// Label bot�o utilit�tio
				httpServletRequest.setAttribute("labelBotao", "Encerrar RA");

				// URL bot�o utilit�rio
				// [UC0435] - Encerrar Registro de Atendimento
				httpServletRequest.setAttribute("urlBotao",
						"encerrarRegistroAtendimentoAction.do?numeroRA="
								+ atualizarRegistroAtendimentoActionForm
										.getNumeroRA()
								+ "&numeroRAReferencia="
								+ verificarRAFaltaAguaHelper
										.getIdRAReferencia()
								+ "& motivoEncerramentoId="
								+ verificarRAFaltaAguaHelper
										.getIdMotivoEncerramento());

				retorno = actionMapping.findForward("telaOpcaoConsultar");
			}

			// caso o caso de uso seja [SB0019] - Exibir Registros de
			// Atendimento de Falta de �gua no Im�vel da �rea do Bairro
			if (verificarRAFaltaAguaHelper.getCasoUso1() != null
					&& verificarRAFaltaAguaHelper
							.getCasoUso1()
							.equals(
									VerificarRAFaltaAguaHelper.EXIBIR_RA_FALTA_AGUA_IMOVEL)) {
				httpServletRequest.setAttribute("atencao",
						verificarRAFaltaAguaHelper.getMensagem());

				// URL da pr�xima ABA
				httpServletRequest
						.setAttribute(
								"proximaAba",
								"/gsan/atualizarRegistroAtendimentoWizardAction.do?continua=N�O&destino=3&action=atualizarRegistroAtendimentoDadosLocalOcorrenciaAction");

				// URL da ABA anterior
				httpServletRequest
						.setAttribute(
								"voltarAba",
								"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction");

				// Tipo chamada (Popup ou tela convencional)
				httpServletRequest.setAttribute("tipoChamada", "popup");

				// Label bot�o utilit�rio
				httpServletRequest.setAttribute("labelBotao", "RA Falta �gua");

				// URL bot�o utilit�rio
				// [SB0019] - Exibir Registros de
				// Atendimento de Falta de �gua no Im�vel da �rea do Bairro
				httpServletRequest
						.setAttribute("urlBotao",
								"pesquisarRegitrosAtendimentoFaltaAguaImovelLocalOcorrenciaAction.do");

				retorno = actionMapping.findForward("telaOpcaoConsultar");

				// Tipo chamada (Popup ou tela convencional)
				httpServletRequest.setAttribute("tipoChamada2", "tela");

				// Label bot�o utilit�rio
				httpServletRequest.setAttribute("labelBotao2",
						"Abrir RA de Falta de �gua Geral");

				// URL bot�o utilit�rio
				// [UC0366] - Inserir Registro de Atendimento
				httpServletRequest
						.setAttribute("urlBotao2",
								"exibirInserirRegistroAtendimentoAction.do?raFaltaAguaGeneralizada=1&menu=sim");

				retorno = actionMapping.findForward("telaOpcaoConsultar");
			}
		}

		if (retorno != null) {
			httpServletRequest.setAttribute("telaOpcaoConsultar", "OK");
		}

		return retorno;
	}

	private ActionForward validarCamposEnter(
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest,
			ActionMapping actionMapping, HttpSession sessao) {
		ActionForward retornoAtencao = null;

		/*
		 * [SB0004] Obt�m e Habilita/Desabilita Dados da Identifica��o do
		 * Local da Ocorr�ncia e Dados do Solicitante
		 * 
		 * [FS0019] Verificar endere�o do im�vel [FS0020] - Verificar
		 * exist�ncia de registro de atendimento para o im�vel com a mesma
		 * especifica��o
		 * 
		 * [SB0020] Verifica Situa��o do Im�vel e Especifica��o
		 * 
		 */

		// [SB0002] Habilita/Desabilita Munic�pio, Bairro, �rea do Bairro e
		// Divis�o de Esgoto
		ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto = fachada
				.habilitarGeograficoDivisaoEsgoto(new Integer(
						atualizarRegistroAtendimentoActionForm
								.getTipoSolicitacao()));

		String idImovel = atualizarRegistroAtendimentoActionForm.getIdImovel();
		String inscricaoImovel = atualizarRegistroAtendimentoActionForm
				.getInscricaoImovel();
		// caso seja a pesquisa do enter do im�vel ou o indicador de
		// valida��o de matr�cula do im�vel seja 1
		if (idImovel != null && !idImovel.equalsIgnoreCase("")
				&& (inscricaoImovel == null || inscricaoImovel.equals(""))) {
			ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = fachada
					.obterDadosIdentificacaoLocalOcorrenciaAtualizar(
							new Integer(atualizarRegistroAtendimentoActionForm
									.getIdImovel()), new Integer(
									atualizarRegistroAtendimentoActionForm
											.getEspecificacao()), new Integer(
									atualizarRegistroAtendimentoActionForm
											.getTipoSolicitacao()),
							new Integer(atualizarRegistroAtendimentoActionForm
									.getNumeroRA()), true);

			if (dadosIdentificacaoLocalOcorrencia.getImovel() != null) {

				// [SB0021] Verifica Exist�ncia de Registro de
				// Atendimento
				// Pendente para o Im�vel
				boolean raPendente = fachada
						.verificaExistenciaRAPendenteImovel(dadosIdentificacaoLocalOcorrencia
								.getImovel().getId());

				if (raPendente) {
					httpServletRequest.setAttribute("atencao",
							"Existe Registro de Atendimento pendente para o im�vel "
									+ dadosIdentificacaoLocalOcorrencia
											.getImovel().getId().toString());

					// Label bot�o utilit�rio
					httpServletRequest.setAttribute("labelBotao",
							"RAs Pendentes");

					// URL bot�o utilit�rio
					// [SB0019] - Exibir Registros de
					// Atendimento de Falta de �gua no Im�vel da �rea do Bairro
					httpServletRequest
							.setAttribute("urlBotao",
									"pesquisarRegitrosAtendimentoPendentesImovelLocalOcorrenciaAction.do");

					// URL da pr�xima ABA
					httpServletRequest
							.setAttribute(
									"proximaAba",
									"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosSolicitanteAction");
					// URL da ABA anterior
					httpServletRequest
							.setAttribute(
									"voltarAba",
									"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction");

					// Tipo chamada (Popup ou tela convencional)
					httpServletRequest.setAttribute("tipoChamada", "tela");

					retornoAtencao = actionMapping
							.findForward("telaOpcaoConsultar");

				}

				// [FS0020] - Verificar exist�ncia de registro de
				// atendimento
				// para o im�vel com a mesma especifica��o
				fachada.verificarExistenciaRAImovelMesmaEspecificacao(
						dadosIdentificacaoLocalOcorrencia.getImovel().getId(),
						new Integer(atualizarRegistroAtendimentoActionForm
								.getEspecificacao()));

				// [SB0020] Verifica Situa��o do Im�vel e
				// Especifica��o
				fachada.verificarSituacaoImovelEspecificacao(
						dadosIdentificacaoLocalOcorrencia.getImovel(),
						new Integer(atualizarRegistroAtendimentoActionForm
								.getEspecificacao()));

				atualizarRegistroAtendimentoActionForm
						.setIdImovel(dadosIdentificacaoLocalOcorrencia
								.getImovel().getId().toString());

				atualizarRegistroAtendimentoActionForm
						.setInscricaoImovel(dadosIdentificacaoLocalOcorrencia
								.getImovel().getInscricaoFormatada());

				if (!dadosIdentificacaoLocalOcorrencia.isInformarEndereco()) {
					Collection colecaoEnderecos = new ArrayList();
					colecaoEnderecos.add(dadosIdentificacaoLocalOcorrencia
							.getImovel());
					sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
				} else if (dadosIdentificacaoLocalOcorrencia
						.getEnderecoDescritivo() != null) {
					atualizarRegistroAtendimentoActionForm
							.setDescricaoLocalOcorrencia(dadosIdentificacaoLocalOcorrencia
									.getEnderecoDescritivo());
					if (retornoAtencao == null) {

						httpServletRequest
								.setAttribute(
										"atencao",
										"O Registro de Atendimento ficar� bloqueado at� "
												+ " que seja informado o logradouro para o im�vel");

						// URL da pr�xima ABA
						httpServletRequest
								.setAttribute(
										"proximaAba",
										"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosSolicitanteAction");
						// URL da ABA anterior
						httpServletRequest
								.setAttribute(
										"voltarAba",
										"/gsan/atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction");

						// Tipo chamada (Popup ou tela convencional)
						httpServletRequest.setAttribute("tipoChamada", "tela");

						retornoAtencao = actionMapping
								.findForward("telaOpcaoConsultar");
					}

					sessao.removeAttribute("colecaoEnderecos");
				} else {
					sessao.removeAttribute("colecaoEnderecos");
				}

				this
						.carregarMunicipioBairroParaImovel(
								habilitaGeograficoDivisaoEsgoto,
								dadosIdentificacaoLocalOcorrencia,
								atualizarRegistroAtendimentoActionForm, sessao,
								fachada);

				atualizarRegistroAtendimentoActionForm
						.setIdLocalidade(dadosIdentificacaoLocalOcorrencia
								.getImovel().getLocalidade().getId().toString());

				atualizarRegistroAtendimentoActionForm
						.setDescricaoLocalidade(dadosIdentificacaoLocalOcorrencia
								.getImovel().getLocalidade().getDescricao());

				atualizarRegistroAtendimentoActionForm
						.setIdSetorComercial(dadosIdentificacaoLocalOcorrencia
								.getImovel().getSetorComercial().getId()
								.toString());

				atualizarRegistroAtendimentoActionForm
						.setCdSetorComercial(String
								.valueOf(dadosIdentificacaoLocalOcorrencia
										.getImovel().getSetorComercial()
										.getCodigo()));

				atualizarRegistroAtendimentoActionForm
						.setDescricaoSetorComercial(dadosIdentificacaoLocalOcorrencia
								.getImovel().getSetorComercial().getDescricao());

				atualizarRegistroAtendimentoActionForm.setIdQuadra(String
						.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel()
								.getQuadra().getId()));

				atualizarRegistroAtendimentoActionForm.setNnQuadra(String
						.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel()
								.getQuadra().getNumeroQuadra()));

				sessao
						.setAttribute("desabilitarDescricaoLocalOcorrencia",
								"OK");

			}

		}

		String idMunicipio = atualizarRegistroAtendimentoActionForm
				.getIdMunicipio();
		String descricaoMunicipio = atualizarRegistroAtendimentoActionForm
				.getDescricaoMunicipio();

		if (idMunicipio != null
				&& !idMunicipio.equalsIgnoreCase("")
				&& (descricaoMunicipio == null || descricaoMunicipio.equals(""))) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, atualizarRegistroAtendimentoActionForm
							.getIdMunicipio()));

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Munic�pio");

			}
			Municipio municipio = (Municipio) Util
					.retonarObjetoDeColecao(colecaoMunicipio);

			atualizarRegistroAtendimentoActionForm.setIdMunicipio(municipio
					.getId().toString());
			atualizarRegistroAtendimentoActionForm
					.setDescricaoMunicipio(municipio.getNome());

			httpServletRequest.setAttribute("nomeCampo", "cdBairro");
		}

		String codigoBairro = atualizarRegistroAtendimentoActionForm
				.getCdBairro();
		String descricaoBairro = atualizarRegistroAtendimentoActionForm
				.getDescricaoBairro();

		if (codigoBairro != null && !codigoBairro.equalsIgnoreCase("")) {

			if (descricaoBairro == null || descricaoBairro.equals("")) {

				FiltroBairro filtroBairro = new FiltroBairro();

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.CODIGO,
						atualizarRegistroAtendimentoActionForm.getCdBairro()));

				filtroBairro
						.adicionarParametro(new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								atualizarRegistroAtendimentoActionForm
										.getIdMunicipio()));

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoBairro = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				if (colecaoBairro == null || colecaoBairro.isEmpty()) {

					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Bairro");

				}
				Bairro bairro = (Bairro) Util
						.retonarObjetoDeColecao(colecaoBairro);

				atualizarRegistroAtendimentoActionForm.setCdBairro(String
						.valueOf(bairro.getCodigo()));
				atualizarRegistroAtendimentoActionForm.setCdBairro(String
						.valueOf(bairro.getId()));
				atualizarRegistroAtendimentoActionForm
						.setDescricaoBairro(bairro.getNome());
				this.pesquisarBairroArea(new Integer(
						atualizarRegistroAtendimentoActionForm
								.getIdBairro()), fachada, sessao);
			}

		}

		String idLocalidade = atualizarRegistroAtendimentoActionForm
				.getIdLocalidade();
		String descricaoLocalidade = atualizarRegistroAtendimentoActionForm
				.getDescricaoBairro();

		if (idLocalidade != null
				&& !idLocalidade.equalsIgnoreCase("")
				&& (descricaoLocalidade == null || descricaoLocalidade
						.equals(""))) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, atualizarRegistroAtendimentoActionForm
							.getIdLocalidade()));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");

			}
			Localidade localidade = (Localidade) Util
					.retonarObjetoDeColecao(colecaoLocalidade);

			atualizarRegistroAtendimentoActionForm
					.setIdLocalidade(localidade.getId().toString());
			atualizarRegistroAtendimentoActionForm
					.setDescricaoLocalidade(localidade.getDescricao());

			httpServletRequest
					.setAttribute("nomeCampo", "cdSetorComercial");
		}

		String cdSetorComercial = atualizarRegistroAtendimentoActionForm
				.getCdSetorComercial();
		String descricaoSetorComercial = atualizarRegistroAtendimentoActionForm
				.getDescricaoSetorComercial();

		if (cdSetorComercial != null
				&& !cdSetorComercial.equalsIgnoreCase("")
				&& (descricaoSetorComercial == null || descricaoSetorComercial
						.equals(""))) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE,
					atualizarRegistroAtendimentoActionForm.getIdLocalidade()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					atualizarRegistroAtendimentoActionForm
							.getCdSetorComercial()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoSetorComercial = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial == null
					|| colecaoSetorComercial.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Setor Comercial");

			}
			SetorComercial setorComercial = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoSetorComercial);

			atualizarRegistroAtendimentoActionForm
					.setIdSetorComercial(setorComercial.getId().toString());
			atualizarRegistroAtendimentoActionForm
					.setCdSetorComercial(String.valueOf(setorComercial
							.getCodigo()));
			atualizarRegistroAtendimentoActionForm
					.setDescricaoSetorComercial(setorComercial
							.getDescricao());

			httpServletRequest.setAttribute("nomeCampo", "nnQuadra");
		}

		String nnQuadra = atualizarRegistroAtendimentoActionForm.getNnQuadra();

		if (nnQuadra != null && !nnQuadra.equalsIgnoreCase("")) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL,
					atualizarRegistroAtendimentoActionForm
							.getIdSetorComercial()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA,
					atualizarRegistroAtendimentoActionForm.getNnQuadra()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoQuadra = fachada.pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");

			}
			Quadra quadra = (Quadra) Util
					.retonarObjetoDeColecao(colecaoQuadra);

			atualizarRegistroAtendimentoActionForm.setIdQuadra(quadra
					.getId().toString());
			atualizarRegistroAtendimentoActionForm.setNnQuadra(String
					.valueOf(quadra.getNumeroQuadra()));

			// [SB0006] Obt�m Divis�o de Esgoto
			DivisaoEsgoto divisaoEsgoto = fachada.obterDivisaoEsgoto(quadra
					.getId(), habilitaGeograficoDivisaoEsgoto
					.isSolicitacaoTipoRelativoAreaEsgoto());

			if (divisaoEsgoto != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdDivisaoEsgoto(divisaoEsgoto.getId()
								.toString());

				/*
				 * [FS0013] Verificar compatibilidade entre divis�o de
				 * esgoto e localidade/setor/quadra [SB0007] Define
				 * Unidade Destino da Divis�o de Esgoto
				 */
				this
						.verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
								fachada,
								atualizarRegistroAtendimentoActionForm,
								habilitaGeograficoDivisaoEsgoto
										.isSolicitacaoTipoRelativoAreaEsgoto());

			}
		}
		return retornoAtencao;
	}

	public void pesquisarBairroArea(Integer idBairro, Fachada fachada,
			HttpSession sessao) {

		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

		filtroBairroArea.adicionarParametro(new ParametroSimples(
				FiltroBairroArea.ID_BAIRRO, idBairro));

		Collection colecaoBairroArea = fachada.pesquisar(filtroBairroArea,
				BairroArea.class.getName());

		if (colecaoBairroArea == null || colecaoBairroArea.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"BAIRRO_AREA");
		}
		sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
	}

	public void carregarMunicipioBairroParaImovel(
			ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto,
			ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaHelper,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			HttpSession sessao, Fachada fachada) {

		if (habilitaGeograficoDivisaoEsgoto != null
				&& habilitaGeograficoDivisaoEsgoto
						.isSolicitacaoTipoRelativoFaltaAgua()
				&& obterDadosIdentificacaoLocalOcorrenciaHelper
						.getEnderecoDescritivo() == null) {

			atualizarRegistroAtendimentoActionForm
					.setIdMunicipio(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getMunicipio().getId().toString());

			atualizarRegistroAtendimentoActionForm
					.setDescricaoMunicipio(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getMunicipio().getNome());

			atualizarRegistroAtendimentoActionForm
					.setIdBairro(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getId().toString());

			atualizarRegistroAtendimentoActionForm.setCdBairro(String
					.valueOf(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getCodigo()));

			atualizarRegistroAtendimentoActionForm
					.setDescricaoBairro(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getNome());

			this.pesquisarBairroArea(
					obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel()
							.getLogradouroBairro().getBairro().getId(),
					fachada, sessao);

			sessao.setAttribute("desabilitarMunicipioBairro", "OK");

		} else {

			atualizarRegistroAtendimentoActionForm.setIdMunicipio("");

			atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio("");

			atualizarRegistroAtendimentoActionForm.setIdBairro("");

			atualizarRegistroAtendimentoActionForm.setCdBairro("");

			atualizarRegistroAtendimentoActionForm.setDescricaoBairro("");

			sessao.removeAttribute("colecaoBairroArea");
		}
	}

	public void verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
			Fachada fachada,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			boolean solicitacaoTipoRelativoAreaEsgoto) {

		fachada
				.verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(
						Util
								.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
										.getIdLocalidade()),
						Util
								.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
										.getIdSetorComercial()),
						Util
								.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
										.getIdQuadra()),
						Util
								.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
										.getIdDivisaoEsgoto()));

	}

}
