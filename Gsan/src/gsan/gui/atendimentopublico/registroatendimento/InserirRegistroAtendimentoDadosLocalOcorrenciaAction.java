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
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gsan.atendimentopublico.registroatendimento.bean.RegistroAtendimentoEncerradoLocalOcorrenciaHelper;
import gsan.atendimentopublico.registroatendimento.bean.VerificarRAFaltaAguaHelper;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.BairroArea;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.geografico.FiltroBairroArea;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.operacional.DivisaoEsgoto;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
 * processo de inser��o de um registro de atendimento
 * 
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class InserirRegistroAtendimentoDadosLocalOcorrenciaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm = (InserirRegistroAtendimentoActionForm) actionForm;

		// -----------------------------------------------------------------------
		// [FS0040] - Validar Preenchimento dos campos

		String idImovel = inserirRegistroAtendimentoActionForm.getIdImovel();
		String pontoReferencia = inserirRegistroAtendimentoActionForm
				.getPontoReferencia();
		String idMunicipio = inserirRegistroAtendimentoActionForm
				.getIdMunicipio();
		String descricaoMunicipio = inserirRegistroAtendimentoActionForm
				.getDescricaoMunicipio();
		String cdBairro = inserirRegistroAtendimentoActionForm.getCdBairro();
		String descricaoBairro = inserirRegistroAtendimentoActionForm
				.getDescricaoBairro();
		String idAreaBairro = inserirRegistroAtendimentoActionForm
				.getIdBairroArea();
		String idlocalidade = inserirRegistroAtendimentoActionForm
				.getIdLocalidade();
		String descricaoLocalidade = inserirRegistroAtendimentoActionForm
				.getDescricaoLocalidade();
		String cdSetorComercial = inserirRegistroAtendimentoActionForm
				.getCdSetorComercial();
		String descricaoSetorComercial = inserirRegistroAtendimentoActionForm
				.getDescricaoSetorComercial();
		String numeroQuadra = inserirRegistroAtendimentoActionForm
				.getNnQuadra();
		String idDivisaoEsgoto = inserirRegistroAtendimentoActionForm
				.getIdDivisaoEsgoto();
		String idUnidade = inserirRegistroAtendimentoActionForm
				.getIdUnidadeDestino();
		String descricaoUnidade = inserirRegistroAtendimentoActionForm
				.getDescricaoUnidadeDestino();
		String idLocalOcorrencia = inserirRegistroAtendimentoActionForm
				.getIdLocalOcorrencia();
		String idPavimentoRua = inserirRegistroAtendimentoActionForm
				.getIdPavimentoRua();
		String idPavimentoCalcada = inserirRegistroAtendimentoActionForm
				.getIdPavimentoCalcada();
		String descricaoLocalOcorrencia = inserirRegistroAtendimentoActionForm
				.getDescricaoLocalOcorrencia();
		String imovelObrigatorio = inserirRegistroAtendimentoActionForm
				.getImovelObrigatorio();
		String pavimentoRuaObrigatorio = inserirRegistroAtendimentoActionForm
				.getPavimentoRuaObrigatorio();
		String pavimentoCalcadaObrigatorio = inserirRegistroAtendimentoActionForm
				.getPavimentoCalcadaObrigatorio();
		String solicitacaoTipoRelativoFaltaAgua = (String) sessao
				.getAttribute("solicitacaoTipoRelativoFaltaAgua");
		String solicitacaoTipoRelativoAreaEsgoto = (String) sessao
				.getAttribute("solicitacaoTipoRelativoAreaEsgoto");

		String desabilitarMunicipioBairro = (String) sessao
				.getAttribute("desabilitarMunicipioBairro");
		String indRuaLocalOcorrencia = inserirRegistroAtendimentoActionForm
				.getIndRuaLocalOcorrencia();
		String indCalcadaLocalOcorrencia = inserirRegistroAtendimentoActionForm
				.getIndCalcadaLocalOcorrencia();
		String idEspecificacao = inserirRegistroAtendimentoActionForm.getEspecificacao();
		
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		
		if (idUnidade != null && ! idUnidade.equals("")){
			FiltroUnidadeOrganizacional filtroUnidadeDestino = new FiltroUnidadeOrganizacional();
	
			filtroUnidadeDestino.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID,	new Integer(idUnidade)));
	
			Collection colecaoUnidadeDestino = fachada.pesquisar(
					filtroUnidadeDestino, UnidadeOrganizacional.class.getName());
	
			if (colecaoUnidadeDestino != null && ! colecaoUnidadeDestino.isEmpty()) {
	
				UnidadeOrganizacional unidadeDestino = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidadeDestino);
	
				// [FS0021] - Verificar possibilidade de encaminhamento para a
				// unidade destino
				fachada.verificaPossibilidadeEncaminhamentoUnidadeDestino(unidadeDestino);
			}
		}

		if(inserirRegistroAtendimentoActionForm.getParecerUnidadeDestino() != null && 
			!inserirRegistroAtendimentoActionForm.getParecerUnidadeDestino().equals("") && 
			inserirRegistroAtendimentoActionForm.getParecerUnidadeDestino().length() > 400){
			
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
				inserirRegistroAtendimentoActionForm.getEspecificacao()));
		
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
				idDivisaoEsgoto, idUnidade, descricaoUnidade,
				idLocalOcorrencia, idPavimentoRua, idPavimentoCalcada,
				descricaoLocalOcorrencia, imovelObrigatorio,
				pavimentoRuaObrigatorio, pavimentoCalcadaObrigatorio,
				solicitacaoTipoRelativoFaltaAgua,
				solicitacaoTipoRelativoAreaEsgoto, desabilitarMunicipioBairro,
				indRuaLocalOcorrencia, 
				indCalcadaLocalOcorrencia, 
				new Integer(idEspecificacao), 
				null, 
				colecaoEnderecos,
				especificacao,
				colecaoPagamento,
				usuarioLogado);

		// -----------------------------------------------------------------------

		// valida os campos de enter(caso tenha mudado algum valor validar)
		ActionForward retornoAtencao = validarCamposEnter(
				inserirRegistroAtendimentoActionForm, fachada,
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

				Integer idLogradouroBairro = null;
				Integer idLogradouroCep = null;
				
				if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {

					Object endereco = (Object) Util
					.retonarObjetoDeColecao(colecaoEndereco);
					
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
											inserirRegistroAtendimentoActionForm
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
										"/gsan/inserirRegistroAtendimentoWizardAction.do?destino=3&action=inserirRegistroAtendimentoDadosLocalOcorrenciaAction");

						// URL da ABA anterior
						httpServletRequest
								.setAttribute(
										"voltarAba",
										"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction");

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
				
				
				//[SB0015] Verifica Registro de Atendimento Encerrado para o Local da Ocorr�ncia
				RegistroAtendimentoEncerradoLocalOcorrenciaHelper raEncerradoHelper = fachada
				.verificarRegistroAtendimentoEncerradoLocalOcorrencia(Util.converterStringParaInteger(idImovel),
				Util.converterStringParaInteger(inserirRegistroAtendimentoActionForm.getEspecificacao()), 
				idLogradouroBairro, idLogradouroCep);
				
				if (raEncerradoHelper != null) {
					
					SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema(); 
					
					if (raEncerradoHelper.getImovel() != null){
					
						httpServletRequest.setAttribute("atencao",
								"Existe Registro de Atendimento de "
										+ raEncerradoHelper.getSolicitacaoTipoEspecificacao()
												.getDescricao()
										+ " encerrado nos �ltimos "
										+ sistemaParametro.getDiasReativacao().toString()
										+ " dias para este im�vel "
										+ raEncerradoHelper.getImovel().getId().toString());
					}
					else{
						
						httpServletRequest.setAttribute("atencao",
								"Existe Registro de Atendimento de "
										+ raEncerradoHelper.getSolicitacaoTipoEspecificacao()
												.getDescricao()
										+ " encerrado nos �ltimos "
										+ sistemaParametro.getDiasReativacao().toString()
										+ " dias para este endere�o "
										+ raEncerradoHelper.getEnderecoLocalOcorrencia());
					}
					

					// URL da pr�xima ABA
					httpServletRequest
							.setAttribute(
									"proximaAba",
									"/gsan/inserirRegistroAtendimentoWizardAction.do?destino=3&action=inserirRegistroAtendimentoDadosLocalOcorrenciaAction");

					// URL da ABA anterior
					httpServletRequest
							.setAttribute(
									"voltarAba",
									"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction");

					// Tipo chamada (Popup ou tela convencional)
					httpServletRequest.setAttribute("tipoChamada", "popup");

					// Label bot�o utilit�rio
					httpServletRequest.setAttribute("labelBotao",
							"Consultar RAs Encerrados");

					// URL bot�o utilit�rio
					if (raEncerradoHelper.getImovel() != null){
						
						httpServletRequest
						.setAttribute(
								"urlBotao",
								"pesquisarRegistrosAtendimentoEncerradosLocalOcorrenciaAction.do?idEspecificacao="
										+ raEncerradoHelper
												.getSolicitacaoTipoEspecificacao()
												.getId()
										+ "&idImovel=" + raEncerradoHelper.getImovel().getId().toString());
					}
					else{
						
						httpServletRequest
						.setAttribute(
								"urlBotao",
								"pesquisarRegistrosAtendimentoEncerradosLocalOcorrenciaAction.do?idEspecificacao="
										+ raEncerradoHelper
												.getSolicitacaoTipoEspecificacao()
												.getId()
										+ "&idLogradouroBairro="
										+ idLogradouroBairro
										+ "&idLogradouroCep="
										+ idLogradouroCep);
					}
					

					retorno = actionMapping
							.findForward("telaOpcaoConsultar");
				}
				
				
				
				if (httpServletRequest.getAttribute("urlBotao") == null
						|| httpServletRequest.getAttribute("urlBotao").equals(
								"")) {
					// [SB0017] - Verifica Registro de Atendimento de Falta de
					// �gua

					/*
					 * S� para os casos em que o local de ocorr�ncia for informado
					 */
					if (inserirRegistroAtendimentoActionForm.getIdBairroArea() != null &&
						!inserirRegistroAtendimentoActionForm.getIdBairroArea().equalsIgnoreCase("") &&
						!inserirRegistroAtendimentoActionForm.getIdBairroArea().equalsIgnoreCase(
						String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
						
						retorno = registroAtendimentoFaltaAgua(actionMapping,
								httpServletRequest,
								inserirRegistroAtendimentoActionForm, fachada,
								continua);
					}
					
				}
			} else {
				// [SB0017] - Verifica Registro de Atendimento de Falta de �gua
				if (continua != null && continua.equals("SIM")) {
					
					/*
					 * S� para os casos em que o local de ocorr�ncia for informado
					 */
					if (inserirRegistroAtendimentoActionForm.getIdBairroArea() != null &&
						!inserirRegistroAtendimentoActionForm.getIdBairroArea().equalsIgnoreCase("") &&
						!inserirRegistroAtendimentoActionForm.getIdBairroArea().equalsIgnoreCase(
						String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
						
						retorno = registroAtendimentoFaltaAgua(actionMapping,
								httpServletRequest,
								inserirRegistroAtendimentoActionForm, fachada,
								continua);
					}
				}
			}
		}

		if (retorno != null) {
			httpServletRequest.setAttribute("telaOpcaoConsultar", "OK");
		}

		return retorno;
	}

	private ActionForward registroAtendimentoFaltaAgua(
			ActionMapping actionMapping,
			HttpServletRequest httpServletRequest,
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
			Fachada fachada, String continua) {

		ActionForward retorno = null;

		// [SB0017] - Verifica Registro de Atendimento de Falta de �gua
		Date dataAtendimento = null;
		if (inserirRegistroAtendimentoActionForm.getDataAtendimento() != null
				&& !inserirRegistroAtendimentoActionForm.getDataAtendimento()
						.equals("")) {
			dataAtendimento = Util
					.converteStringParaDateHora(inserirRegistroAtendimentoActionForm
							.getDataAtendimento() + " " + inserirRegistroAtendimentoActionForm.getHora() + ":00");
			
			
		}
		Integer idBairroArea = null;
		if (inserirRegistroAtendimentoActionForm.getIdBairroArea() != null
				&& !inserirRegistroAtendimentoActionForm.getIdBairroArea()
						.equals("") && 
			!inserirRegistroAtendimentoActionForm.getIdBairroArea().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			idBairroArea = new Integer(inserirRegistroAtendimentoActionForm
					.getIdBairroArea());
		}
		Integer idEspecificacao = null;
		if (inserirRegistroAtendimentoActionForm.getEspecificacao() != null
				&& !inserirRegistroAtendimentoActionForm.getEspecificacao()
						.equals("")) {
			idEspecificacao = new Integer(inserirRegistroAtendimentoActionForm
					.getEspecificacao());
		}
		Integer idBairro = null;
		if (inserirRegistroAtendimentoActionForm.getIdBairro() != null
				&& !inserirRegistroAtendimentoActionForm.getIdBairro().equals(
						"")) {
			idBairro = new Integer(inserirRegistroAtendimentoActionForm
					.getIdBairro());
		}

		Short indicadorFaltaAgua = null;
		if (inserirRegistroAtendimentoActionForm.getIndFaltaAgua() != null
				&& !inserirRegistroAtendimentoActionForm.getIndFaltaAgua()
						.equals("")) {
			indicadorFaltaAgua = new Short(inserirRegistroAtendimentoActionForm
					.getIndFaltaAgua());
		}

		Integer indicadorMatricula = null;
		if (inserirRegistroAtendimentoActionForm.getIndMatricula() != null
				&& !inserirRegistroAtendimentoActionForm.getIndMatricula()
						.equals("")) {
			indicadorMatricula = new Integer(
					inserirRegistroAtendimentoActionForm.getIndMatricula());
		}

		VerificarRAFaltaAguaHelper verificarRAFaltaAguaHelper = fachada
				.verificarRegistroAtendimentoFaltaAguaInserir(dataAtendimento,
						idBairroArea, idBairro, idEspecificacao,
						indicadorFaltaAgua, indicadorMatricula, continua);
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
									"/gsan/inserirRegistroAtendimentoWizardAction.do?destino=3&continua=SIM&action=inserirRegistroAtendimentoDadosLocalOcorrenciaAction");
					// URL da ABA anterior
					httpServletRequest
							.setAttribute(
									"voltarAba",
									"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction");
				} else {
					// URL da pr�xima ABA
					httpServletRequest
							.setAttribute(
									"proximaAba",
									"/gsan/inserirRegistroAtendimentoWizardAction.do?continua=NAO&destino=3&action=inserirRegistroAtendimentoDadosLocalOcorrenciaAction");
					// URL da ABA anterior
					httpServletRequest
							.setAttribute(
									"voltarAba",
									"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction");
				}

				// Tipo chamada (Popup ou tela convencional)
				httpServletRequest.setAttribute("tipoChamada", "popup");

				// Label bot�o utilit�t�rio
				httpServletRequest.setAttribute("labelBotao",
						"Abastecimento/Manuten��o");

				// URL bot�o utilit�rio
				// [UC0440] - Consultar Programa��o de Abastecimento/Manuten��o
				httpServletRequest.setAttribute("urlBotao",
						"exibirConsultarProgramacaoAbastecimentoManutencaoAction.do?idMunicipio=" + 
						inserirRegistroAtendimentoActionForm.getIdMunicipio() + "&codigoBairro=" + 
						inserirRegistroAtendimentoActionForm.getCdBairro() + 
						"&areaBairro=" + inserirRegistroAtendimentoActionForm.getIdBairroArea());

				retorno = actionMapping.findForward("telaOpcaoConsultar");
			}

			// Adicionar um solicitante ao RA de falta de �gua generalizada
			if (verificarRAFaltaAguaHelper.getCasoUso1() != null
					&& verificarRAFaltaAguaHelper
							.getCasoUso1()
							.equals(
									VerificarRAFaltaAguaHelper.EXIBIR_ADICIONAR_SOLICITANTE)) {
				httpServletRequest.setAttribute("atencao",
						verificarRAFaltaAguaHelper.getMensagem());

				// URL da pr�xima ABA
				httpServletRequest
						.setAttribute(
								"proximaAba",
								"/gsan/inserirRegistroAtendimentoWizardAction.do?continua=NAO&destino=3&action=inserirRegistroAtendimentoDadosLocalOcorrenciaAction");
				// URL da ABA anterior
				httpServletRequest
						.setAttribute(
								"voltarAba",
								"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction");

				// Tipo chamada (Popup ou tela convencional)
				httpServletRequest.setAttribute("tipoChamada", "popup");

				// Label bot�o utilit�tio
				httpServletRequest.setAttribute("labelBotao", "Adicionar Solicitante");

				// URL bot�o utilit�rio
				// Adicionar um solicitante ao RA de falta de �gua generalizada
				httpServletRequest.setAttribute("urlBotao", "exibirAdicionarSolicitanteRegistroAtendimentoAction.do?idRegistroAtendimento=" + verificarRAFaltaAguaHelper.getIdRAReferencia().toString());

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
								"/gsan/inserirRegistroAtendimentoWizardAction.do?continua=NAO&destino=3&action=inserirRegistroAtendimentoDadosLocalOcorrenciaAction");

				// URL da ABA anterior
				httpServletRequest
						.setAttribute(
								"voltarAba",
								"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction");

				// Tipo chamada (Popup ou tela convencional)
				httpServletRequest.setAttribute("tipoChamada", "popup");

				// Label bot�o utilit�rio
				httpServletRequest.setAttribute("labelBotao", "Consultar RAs");

				// URL bot�o utilit�rio
				// [SB0019] - Exibir Registros de
				// Atendimento de Falta de �gua no Im�vel da �rea do Bairro
				httpServletRequest
						.setAttribute("urlBotao",
								"pesquisarRegistrosAtendimentoFaltaAguaImovelLocalOcorrenciaAction.do?idBairroArea=" + 
								inserirRegistroAtendimentoActionForm.getIdBairroArea() + "&idEspecificacao=" +
								inserirRegistroAtendimentoActionForm.getEspecificacao());

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

		return retorno;
	}

	private ActionForward validarCamposEnter(
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
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
						inserirRegistroAtendimentoActionForm
								.getTipoSolicitacao()));

		String idImovel = inserirRegistroAtendimentoActionForm.getIdImovel();
		String inscricaoImovel = inserirRegistroAtendimentoActionForm
				.getInscricaoImovel();

		// caso seja a pesquisa do enter do im�vel ou o indicador de
		// valida��o de matr�cula do im�vel seja 1
		if (idImovel != null && !idImovel.equalsIgnoreCase("")
				&& (inscricaoImovel == null || inscricaoImovel.equals(""))) {
			
			ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = fachada
			.obterDadosIdentificacaoLocalOcorrencia(new Integer(
					inserirRegistroAtendimentoActionForm.getIdImovel()),
					new Integer(inserirRegistroAtendimentoActionForm
							.getEspecificacao()), new Integer(
							inserirRegistroAtendimentoActionForm
									.getTipoSolicitacao()), true);

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
									"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction");
					// URL da ABA anterior
					httpServletRequest
							.setAttribute(
									"voltarAba",
									"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction");

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
						new Integer(inserirRegistroAtendimentoActionForm
								.getEspecificacao()));

				// [SB0020] Verifica Situa��o do Im�vel e
				// Especifica��o
				fachada.verificarSituacaoImovelEspecificacao(
						dadosIdentificacaoLocalOcorrencia.getImovel(),
						new Integer(inserirRegistroAtendimentoActionForm
								.getEspecificacao()));

				inserirRegistroAtendimentoActionForm
						.setIdImovel(dadosIdentificacaoLocalOcorrencia
								.getImovel().getId().toString());

				inserirRegistroAtendimentoActionForm
						.setInscricaoImovel(dadosIdentificacaoLocalOcorrencia
								.getImovel().getInscricaoFormatada());

				if (!dadosIdentificacaoLocalOcorrencia.isInformarEndereco()) {
					Collection colecaoEnderecos = new ArrayList();
					colecaoEnderecos.add(dadosIdentificacaoLocalOcorrencia
							.getImovel());
					sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
				} else if (dadosIdentificacaoLocalOcorrencia
						.getEnderecoDescritivo() != null) {
					inserirRegistroAtendimentoActionForm
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
										"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction");
						// URL da ABA anterior
						httpServletRequest
								.setAttribute(
										"voltarAba",
										"/gsan/inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction");

						// Tipo chamada (Popup ou tela convencional)
						httpServletRequest.setAttribute("tipoChamada", "tela");

						retornoAtencao = actionMapping
								.findForward("telaOpcaoConsultar");
					}

					sessao.removeAttribute("colecaoEnderecos");
				} else {
					sessao.removeAttribute("colecaoEnderecos");
				}

				this.carregarMunicipioBairroParaImovel(
						habilitaGeograficoDivisaoEsgoto,
						dadosIdentificacaoLocalOcorrencia,
						inserirRegistroAtendimentoActionForm, sessao, fachada);

				inserirRegistroAtendimentoActionForm
						.setIdLocalidade(dadosIdentificacaoLocalOcorrencia
								.getImovel().getLocalidade().getId().toString());

				inserirRegistroAtendimentoActionForm
						.setDescricaoLocalidade(dadosIdentificacaoLocalOcorrencia
								.getImovel().getLocalidade().getDescricao());

				inserirRegistroAtendimentoActionForm
						.setIdSetorComercial(dadosIdentificacaoLocalOcorrencia
								.getImovel().getSetorComercial().getId()
								.toString());

				inserirRegistroAtendimentoActionForm.setCdSetorComercial(String
						.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel()
								.getSetorComercial().getCodigo()));

				inserirRegistroAtendimentoActionForm
						.setDescricaoSetorComercial(dadosIdentificacaoLocalOcorrencia
								.getImovel().getSetorComercial().getDescricao());

				inserirRegistroAtendimentoActionForm.setIdQuadra(String
						.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel()
								.getQuadra().getId()));

				inserirRegistroAtendimentoActionForm.setNnQuadra(String
						.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel()
								.getQuadra().getNumeroQuadra()));

			}
		}

		String idMunicipio = inserirRegistroAtendimentoActionForm
				.getIdMunicipio();
		String descricaoMunicipio = inserirRegistroAtendimentoActionForm
				.getDescricaoMunicipio();

		if (idMunicipio != null
				&& !idMunicipio.equalsIgnoreCase("")
				&& (descricaoMunicipio == null || descricaoMunicipio.equals(""))) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, inserirRegistroAtendimentoActionForm
							.getIdMunicipio()));

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Munic�pio");

			} else {
				Municipio municipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoMunicipio);

				inserirRegistroAtendimentoActionForm.setIdMunicipio(municipio
						.getId().toString());
				inserirRegistroAtendimentoActionForm
						.setDescricaoMunicipio(municipio.getNome());

				httpServletRequest.setAttribute("nomeCampo", "cdBairro");
			}
		}

		String codigoBairro = inserirRegistroAtendimentoActionForm
				.getCdBairro();
		String descricaoBairro = inserirRegistroAtendimentoActionForm
				.getDescricaoBairro();

		if (codigoBairro != null && !codigoBairro.equalsIgnoreCase("")) {

			if ((descricaoBairro == null || descricaoBairro.equals(""))) {

				FiltroBairro filtroBairro = new FiltroBairro();

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.CODIGO,
						inserirRegistroAtendimentoActionForm.getCdBairro()));

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID,
						inserirRegistroAtendimentoActionForm.getIdMunicipio()));

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoBairro = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				if (colecaoBairro == null || colecaoBairro.isEmpty()) {

					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Bairro");

				} else {
					Bairro bairro = (Bairro) Util
							.retonarObjetoDeColecao(colecaoBairro);

					inserirRegistroAtendimentoActionForm.setCdBairro(String
							.valueOf(bairro.getCodigo()));
					inserirRegistroAtendimentoActionForm.setCdBairro(String
							.valueOf(bairro.getId()));
					inserirRegistroAtendimentoActionForm
							.setDescricaoBairro(bairro.getNome());
					this.pesquisarBairroArea(
							new Integer(inserirRegistroAtendimentoActionForm
									.getIdBairro()), fachada, sessao);

				}
			}

		}

		String idLocalidade = inserirRegistroAtendimentoActionForm
				.getIdLocalidade();
		String descricaoLocalidade = inserirRegistroAtendimentoActionForm
				.getDescricaoLocalidade();

		if (idLocalidade != null
				&& !idLocalidade.equalsIgnoreCase("")
				&& (descricaoLocalidade == null || descricaoLocalidade
						.equals(""))) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, inserirRegistroAtendimentoActionForm
							.getIdLocalidade()));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", "inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction", new Exception(), "Localidade");

			} else {
				Localidade localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				inserirRegistroAtendimentoActionForm.setIdLocalidade(localidade
						.getId().toString());
				inserirRegistroAtendimentoActionForm
						.setDescricaoLocalidade(localidade.getDescricao());

				httpServletRequest
						.setAttribute("nomeCampo", "cdSetorComercial");

			}
		}

		String cdSetorComercial = inserirRegistroAtendimentoActionForm
				.getCdSetorComercial();
		/*String descricaoSetorComercial = inserirRegistroAtendimentoActionForm
				.getDescricaoSetorComercial();*/

		if (cdSetorComercial != null
				&& !cdSetorComercial.equalsIgnoreCase("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE,
					inserirRegistroAtendimentoActionForm.getIdLocalidade()));

			filtroSetorComercial
					.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
							inserirRegistroAtendimentoActionForm
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

			} else {
				SetorComercial setorComercial = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoSetorComercial);

				inserirRegistroAtendimentoActionForm
						.setIdSetorComercial(setorComercial.getId().toString());
				inserirRegistroAtendimentoActionForm.setCdSetorComercial(String
						.valueOf(setorComercial.getCodigo()));
				inserirRegistroAtendimentoActionForm
						.setDescricaoSetorComercial(setorComercial
								.getDescricao());

				httpServletRequest.setAttribute("nomeCampo", "nnQuadra");

			}
		}

		String nnQuadra = inserirRegistroAtendimentoActionForm.getNnQuadra();

		if (nnQuadra != null && !nnQuadra.equalsIgnoreCase("")) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();

			filtroQuadra
					.adicionarParametro(new ParametroSimples(
							FiltroQuadra.ID_SETORCOMERCIAL,
							inserirRegistroAtendimentoActionForm
									.getIdSetorComercial()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA,
					inserirRegistroAtendimentoActionForm.getNnQuadra()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoQuadra = fachada.pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");

			} else {
				Quadra quadra = (Quadra) Util
						.retonarObjetoDeColecao(colecaoQuadra);

				inserirRegistroAtendimentoActionForm.setIdQuadra(quadra.getId()
						.toString());
				inserirRegistroAtendimentoActionForm.setNnQuadra(String
						.valueOf(quadra.getNumeroQuadra()));

				// [SB0006] Obt�m Divis�o de Esgoto
				DivisaoEsgoto divisaoEsgoto = fachada.obterDivisaoEsgoto(quadra
						.getId(), habilitaGeograficoDivisaoEsgoto
						.isSolicitacaoTipoRelativoAreaEsgoto());

				if (divisaoEsgoto != null) {
					inserirRegistroAtendimentoActionForm
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
									inserirRegistroAtendimentoActionForm,
									habilitaGeograficoDivisaoEsgoto
											.isSolicitacaoTipoRelativoAreaEsgoto());

				}

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
		} else {
			sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
		}
	}

	public void carregarMunicipioBairroParaImovel(
			ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto,
			ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaHelper,
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
			HttpSession sessao, Fachada fachada) {

		if (habilitaGeograficoDivisaoEsgoto != null
				&& habilitaGeograficoDivisaoEsgoto
						.isSolicitacaoTipoRelativoFaltaAgua()
				&& obterDadosIdentificacaoLocalOcorrenciaHelper
						.getEnderecoDescritivo() == null) {

			inserirRegistroAtendimentoActionForm
					.setIdMunicipio(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getMunicipio().getId().toString());

			inserirRegistroAtendimentoActionForm
					.setDescricaoMunicipio(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getMunicipio().getNome());

			inserirRegistroAtendimentoActionForm
					.setIdBairro(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getId().toString());

			inserirRegistroAtendimentoActionForm.setCdBairro(String
					.valueOf(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getCodigo()));

			inserirRegistroAtendimentoActionForm
					.setDescricaoBairro(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getNome());

			this.pesquisarBairroArea(
					obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel()
							.getLogradouroBairro().getBairro().getId(),
					fachada, sessao);

			sessao.setAttribute("desabilitarMunicipioBairro", "OK");

		} else {

			inserirRegistroAtendimentoActionForm.setIdMunicipio("");

			inserirRegistroAtendimentoActionForm.setDescricaoMunicipio("");

			inserirRegistroAtendimentoActionForm.setIdBairro("");

			inserirRegistroAtendimentoActionForm.setCdBairro("");

			inserirRegistroAtendimentoActionForm.setDescricaoBairro("");

			sessao.removeAttribute("colecaoBairroArea");
		}
	}

	public void verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
			Fachada fachada,
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
			boolean solicitacaoTipoRelativoAreaEsgoto) {

		fachada
				.verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(
						Util
								.converterStringParaInteger(inserirRegistroAtendimentoActionForm
										.getIdLocalidade()),
						Util
								.converterStringParaInteger(inserirRegistroAtendimentoActionForm
										.getIdSetorComercial()),
						Util
								.converterStringParaInteger(inserirRegistroAtendimentoActionForm
										.getIdQuadra()),
						Util
								.converterStringParaInteger(inserirRegistroAtendimentoActionForm
										.getIdDivisaoEsgoto()));

	}
}
