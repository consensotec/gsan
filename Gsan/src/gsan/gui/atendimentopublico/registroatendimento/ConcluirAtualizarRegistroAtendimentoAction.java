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

import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gsan.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.BairroArea;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.geografico.FiltroBairroArea;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.operacional.DivisaoEsgoto;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
 * Esta classe tem por finalidade validar as informa��es das tr�s abas do
 * processo de atualiza��o de um registro de atendimento e chamar o m�todo que
 * ir� concluir a mesma
 * 
 * @author S�vio Luiz
 * @date 10/08/2006
 */
public class ConcluirAtualizarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarRegistroAtendimentoActionForm form = (AtualizarRegistroAtendimentoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		// recupara o id da especifica��o para verificar se
		// ser� gerado a ordem de servi�o ou n�o dependendo da mudan�a
		// da especifica��o
		Integer idEspecificacaoBase = (Integer) sessao
				.getAttribute("idEspecificacaoBase");

		/*
		 * Valida��o Aba 01
		 * ======================================================================================================
		 */
		fachada.validarInserirRegistroAtendimentoDadosGerais(form
				.getDataAtendimento(), form.getHora(), form
				.getTempoEsperaInicial(), form.getTempoEsperaFinal(), form
				.getUnidade(), null);

		/*
		 * ======================================================================================================
		 * ======================================================================================================
		 */

		/*
		 * Valida��o Aba 02
		 * ======================================================================================================
		 */

		// [FS0040] - Validar Preenchimento dos campos
		String idImovel = form.getIdImovel();
		String pontoReferencia = form.getPontoReferencia();
		String idMunicipio = form.getIdMunicipio();
		String descricaoMunicipio = form.getDescricaoMunicipio();
		String cdBairro = form.getCdBairro();
		String descricaoBairro = form.getDescricaoBairro();
		String idAreaBairro = form.getIdBairroArea();
		String idlocalidade = form.getIdLocalidade();
		String descricaoLocalidade = form.getDescricaoLocalidade();
		String cdSetorComercial = form.getCdSetorComercial();
		String descricaoSetorComercial = form.getDescricaoSetorComercial();
		String numeroQuadra = form.getNnQuadra();
		String idDivisaoEsgoto = form.getIdDivisaoEsgoto();
		String idUnidade = form.getIdUnidadeAtual();
		String descricaoUnidade = form.getDescricaoUnidadeAtual();
		String idLocalOcorrencia = form.getIdLocalOcorrencia();
		String idPavimentoRua = form.getIdPavimentoRua();
		String idPavimentoCalcada = form.getIdPavimentoCalcada();
		String descricaoLocalOcorrencia = form.getDescricaoLocalOcorrencia();
		String imovelObrigatorio = form.getImovelObrigatorio();
		String pavimentoRuaObrigatorio = form.getPavimentoRuaObrigatorio();
		String pavimentoCalcadaObrigatorio = form
				.getPavimentoCalcadaObrigatorio();
		String solicitacaoTipoRelativoFaltaAgua = (String) sessao
				.getAttribute("solicitacaoTipoRelativoFaltaAgua");
		String solicitacaoTipoRelativoAreaEsgoto = (String) sessao
				.getAttribute("solicitacaoTipoRelativoAreaEsgoto");

		String desabilitarMunicipioBairro = (String) sessao
				.getAttribute("desabilitarMunicipioBairro");
		String indRuaLocalOcorrencia = form.getIndRuaLocalOcorrencia();
		String indCalcadaLocalOcorrencia = form.getIndCalcadaLocalOcorrencia();
		
		String idEspecificacao = form.getEspecificacao();
		String numeroRA = form.getNumeroRA();
		
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		
		Collection colecaoPagamento = null;
		
		if (sessao.getAttribute("colecaoPagamentosDuplicidade") != null){
			colecaoPagamento = (Collection) sessao.getAttribute("colecaoPagamentosDuplicidade");
		}
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = 
			new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(
			new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID, form.getEspecificacao()));
		
		Collection colecaoSolicitacaoTipoEspecificacao = 
			this.getFachada()
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());

		SolicitacaoTipoEspecificacao especificacao = 
			(SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);

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
				indRuaLocalOcorrencia, indCalcadaLocalOcorrencia, new Integer(idEspecificacao), 
				new Integer(numeroRA), colecaoEnderecos,especificacao,colecaoPagamento, usuarioLogado);

		// -----------------------------------------------------------------------

		// valida os campos de enter(caso tenha mudado algum valor
		// validar)
		validarCamposEnter(form, fachada, httpServletRequest, actionMapping,
				sessao);

		/*
		 * ======================================================================================================
		 * ======================================================================================================
		 */

		/*
		 * Valida��o Aba 03
		 * ======================================================================================================
		 */

		// recupera a cole��o de RA solicitante
		Collection colecaoRASolicitante = (Collection) sessao
				.getAttribute("colecaoRASolicitante");

		Collection colecaoRASolicitanteRemovida = (Collection) sessao
				.getAttribute("colecaoRASolicitanteRemovidas");

		String idSolicitantePrincipal = form.getIdSolicitantePrincipal();
		if (idSolicitantePrincipal != null
				&& !idSolicitantePrincipal.equals("")) {
			// respons�vel pera troca do solicitante principal
			// caso tenha sido trocado ent�o sai da cole��o
			boolean trocaPrincipal = false;

			if (colecaoRASolicitante != null && !colecaoRASolicitante.isEmpty()) {
				Iterator iteratorRASolicitante = colecaoRASolicitante
						.iterator();
				while (iteratorRASolicitante.hasNext()) {
					RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) iteratorRASolicitante
							.next();
					// caso a colecao s� tenha um solicitante ent�o o
					// solicitante ser� o principal
					if (colecaoRASolicitante.size() == 1) {
						registroAtendimentoSolicitante
								.setIndicadorSolicitantePrincipal(new Short("1"));
					} else {
						// sen�o se o id socilitante seja igual ao o id
						// do solicitante que foi escolhido como
						// principal
						if (registroAtendimentoSolicitante.getUltimaAlteracao()
								.getTime() == Long
								.parseLong(idSolicitantePrincipal)) {
							// se for diferente de 1, ou seja se o
							// solicitante n�o era o principal
							if (registroAtendimentoSolicitante
									.getIndicadorSolicitantePrincipal() != 1) {
								// seta o valor 1 ao indicador principal do
								// solicitante
								registroAtendimentoSolicitante
										.setIndicadorSolicitantePrincipal(new Short(
												"1"));
								// verifica se o indicador principal do
								// solicitante que era 1 anteriormente j�
								// foi mudado para 2(nesse caso o boolean
								// trocaPrincipal est� com o valor true).
								if (trocaPrincipal) {
									break;
								}
								trocaPrincipal = true;
							} else {
								break;
							}

						} else {
							// parte que muda o indicador principal do
							// solicitante(que n�o � mais principal)
							// para 2
							if (registroAtendimentoSolicitante
									.getIndicadorSolicitantePrincipal() == 1) {
								registroAtendimentoSolicitante
										.setIndicadorSolicitantePrincipal(new Short(
												"2"));
								if (trocaPrincipal) {
									break;
								}
								trocaPrincipal = true;
							}
						}
					}
				}
			} else {
				// [FS0021] - Verificar registro atendimento sem
				// solicitante
				throw new ActionServletException(
						"atencao.informar_registro_atendimento_solicitante");
			}
		}

		/*
		 * ======================================================================================================
		 * ======================================================================================================
		 */
		
		/*
         * Valida��o Aba 04 - Anexos
         * ======================================================================================================
         */
		Collection colecaoRegistroAtendimentoAnexo = null;
		
		if (sessao.getAttribute("colecaoRegistroAtendimentoAnexo") != null){
			
			colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");	
		}
		
		boolean indicadorEncerramentoAutomatico = false;
		
		if (especificacao.getIndicadorEncerramentoAutomatico() == 
			SolicitacaoTipoEspecificacao.INDICADOR_COM_ENCERRAMENTO_AUTOMATICO.shortValue()) {
			
			indicadorEncerramentoAutomatico = true;
			
			if (form.getObservacao() == null || form.getObservacao().trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Observa��o");
			}
		}

		//Comentado por Raphael Rossiter
		/*OrdemServico os = null;

		if (sessao.getAttribute("ordemServico") != null) {
			os = (OrdemServico) sessao.getAttribute("ordemServico");
		}*/

		Collection colecaoEnderecoLocalOcorrencia = null;

		if (sessao.getAttribute("colecaoEnderecos") != null) {
			colecaoEnderecoLocalOcorrencia = (Collection) sessao
					.getAttribute("colecaoEnderecos");
		}
		
		Collection colecaoRegistroAtendimentoConta = null;

		if (sessao.getAttribute("colecaoRAContasAtualizar") != null) {
			colecaoRegistroAtendimentoConta = (Collection) sessao
					.getAttribute("colecaoRAContasAtualizar");
		}
		
		Collection colecaoRegistroAtendimentoContaRemover = null;

		if (sessao.getAttribute("colecaoRAContasRemover") != null) {
			colecaoRegistroAtendimentoContaRemover = (Collection) sessao
					.getAttribute("colecaoRAContasRemover");
		}

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		Date ultimaAlteracao = (Date) sessao.getAttribute("ultimaAlteracao");
		
		
		// Colocado por Raphael Rossiter em 01/03/2007
		//ServicoTipo
		Integer idServicoTipo = null;
		if (sessao.getAttribute("servicoTipo") != null){
			idServicoTipo = (Integer) sessao.getAttribute("servicoTipo");
		}
		
		DecimalFormat dff = new DecimalFormat("########0.000000000000;-########0.000000000000");		
		
		BigDecimal valorNnCoordenadaNorte = null;		
		if (form.getNnCoordenadaNorte() != null && !form.getNnCoordenadaNorte().trim().equals("")){
			String cordenadasY = form.getNnCoordenadaNorte();					
			if(cordenadasY != null){
				cordenadasY = fachada.validaBigDecimal(cordenadasY, dff,"Norte");			
			}
			valorNnCoordenadaNorte = new BigDecimal (cordenadasY.replace(",","."));
			
		}
		
		BigDecimal valorNnCoordenadaLeste = null;
		if (form.getNnCoordenadaLeste() != null && !form.getNnCoordenadaLeste().trim().equals("")){
			String cordenadasX = form.getNnCoordenadaLeste();					
			if(cordenadasX != null){
				cordenadasX = fachada.validaBigDecimal(cordenadasX, dff,"Leste");			
			}
			valorNnCoordenadaLeste = new BigDecimal (cordenadasX.replace(",","."));		
		}
		
		BigDecimal nnDiametro = null;
		
		if(form.getNnDiametro()!=null && !form.getNnDiametro().equals("")){
			nnDiametro = new BigDecimal(form.getNnDiametro());
		}

		// [SB0028] Inclui Registro de Atendimento
		Integer[] idsGerados = fachada.atualizarRegistroAtendimento(
					new Integer(form.getNumeroRA()),
					Short.parseShort(form.getTipo()), 
					form.getDataAtendimento(),
					form.getHora(), 
					form.getTempoEsperaInicial(), 
					form.getTempoEsperaFinal(), 
					Util.converterStringParaInteger(form.getMeioSolicitacao()),
					Util.converterStringParaInteger(form.getEspecificacao()), 
					form.getDataPrevista(), 
					form.getObservacao(), 
					Util.converterStringParaInteger(form.getIdImovel()), 
					form.getDescricaoLocalOcorrencia(), 
					Util.converterStringParaInteger(form.getTipoSolicitacao()),
					colecaoEnderecoLocalOcorrencia, 
					form.getPontoReferencia(), 
					Util.converterStringParaInteger(form.getIdBairroArea()),
					Util.converterStringParaInteger(form.getIdLocalidade()),
					Util.converterStringParaInteger(form.getIdSetorComercial()),
					Util.converterStringParaInteger(form.getIdQuadra()), 
					Util.converterStringParaInteger(form.getIdDivisaoEsgoto()),
					Util.converterStringParaInteger(form.getIdLocalOcorrencia()),
					Util.converterStringParaInteger(form.getIdPavimentoRua()), 
					Util.converterStringParaInteger(form.getIdPavimentoCalcada()), 
					Util.converterStringParaInteger(form.getUnidade()),
					usuario, 
					Util.converterStringParaInteger(form.getIndMatricula()),
				    ultimaAlteracao, 
				    colecaoRASolicitante,
				    colecaoRASolicitanteRemovida, 
				    idServicoTipo,
				    (Integer)sessao.getAttribute("idEspecificacaoBase"),
				    Util.converterStringParaInteger(form.getIdUnidadeAtual()),
				    valorNnCoordenadaNorte,
				    valorNnCoordenadaLeste, colecaoRegistroAtendimentoAnexo,
				    colecaoRegistroAtendimentoConta, 
				    colecaoRegistroAtendimentoContaRemover,
				    colecaoPagamento, nnDiametro);

		
		// Caso a especifica��o seja de encerramento autom�tico, encerra o ra
		if (indicadorEncerramentoAutomatico) {
			
			RegistroAtendimentoUnidade registroAtendimentoUnidade = this.montarRegistroAtendimentoParaEncerramento(new Integer(form.getNumeroRA()), usuario);
			
			fachada.encerrarRegistroAtendimento(registroAtendimentoUnidade.getRegistroAtendimento(),
					registroAtendimentoUnidade, usuario, null, null, null, null, false,null,false);
		}

		
		// Colocado por Raphael Rossiter em 01/03/2007
		//Montando a pagina de sucesso
		if (!fachada.gerarOrdemServicoAutomatica(Util.converterStringParaInteger(form.getEspecificacao())) 
			&& fachada.gerarOrdemServicoOpcional(Util.converterStringParaInteger(form.getEspecificacao()))
			&& !idEspecificacaoBase.equals(new Integer(form.getEspecificacao()))){
			
			montarPaginaSucesso(httpServletRequest,
					"Registro de Atendimento de c�digo " + form.getNumeroRA()
							+ " atualizado com sucesso.",
					"Atualizar outro Registro de Atendimento",
					"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
					"exibirGerarOrdemServicoAction.do?menu=sim&forward=exibirGerarOrdemServico&veioRA=OK&idRegistroAtendimento=" + form.getNumeroRA(),
					"Gerar OS",
					"Voltar", "exibirConsultarRegistroAtendimentoAction.do?numeroRA="
					+ form.getNumeroRA());
		}
		else{
			
			if (fachada.gerarOrdemServicoAutomatica(Util.converterStringParaInteger(form.getEspecificacao()))
				&& !idEspecificacaoBase.equals(new Integer(form.getEspecificacao()))){
				
				montarPaginaSucessoUmRelatorio(httpServletRequest, "Registro de Atendimento de c�digo "
	                    + form.getNumeroRA()+ " atualizado com sucesso.", "Atualizar outro Registro de Atendimento",
	                    "exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
						"exibirConsultarRegistroAtendimentoAction.do?numeroRA="
								+ form.getNumeroRA(),
								null,null,
						null,"Imprimir OS" ,"gerarRelatorioOrdemServicoAction.do?idsOS=" + idsGerados[1]);
				
			}
			else{
				
				montarPaginaSucesso(httpServletRequest,
						"Registro de Atendimento de c�digo " + form.getNumeroRA()
								+ " atualizado com sucesso.",
						"Atualizar outro Registro de Atendimento",
						"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
						"exibirConsultarRegistroAtendimentoAction.do?numeroRA="
								+ form.getNumeroRA(),
						"Voltar");
			}
			
		}
		
		// Comentado por Raphael Rossiter em 01/03/2007
		// Montando a p�gina de sucesso
		/*if ((!fachada.gerarOrdemServicoOpcional(Util.converterStringParaInteger(form.getEspecificacao())))
			&& fachada.gerarOrdemServicoOpcional(Util.converterStringParaInteger(form.getEspecificacao()))
				&& !(idEspecificacaoBase.equals(new Integer(form
						.getEspecificacao())))) {

			montarPaginaSucesso(httpServletRequest,
					"Registro de Atendimento de c�digo " + form.getNumeroRA()
							+ " atualizado com sucesso.",
					"Atualizar outro Registro de Atendimento",
					"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
					"exibirGerarOrdemServicoAction.do?menu=sim&forward=exibirGerarOrdemServico&veioRA=OK&idRegistroAtendimento=" + form.getNumeroRA(),
					"Gerar OS",
					"Voltar", "exibirConsultarRegistroAtendimentoAction.do?numeroRA="
					+ form.getNumeroRA());
		} else {

			montarPaginaSucesso(httpServletRequest,
					"Registro de Atendimento de c�digo " + form.getNumeroRA()
							+ " atualizado com sucesso.",
					"Atualizar outro Registro de Atendimento",
					"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
					"exibirConsultarRegistroAtendimentoAction.do?numeroRA="
							+ form.getNumeroRA(),
					"Voltar");
		}*/

		// remove as cole��es da sess�o
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
		sessao.removeAttribute("colecaoRAContasAtualizar");
		sessao.removeAttribute("colecaoRAContasRemover");

		return retorno;
	}

	private void validarCamposEnter(
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest,
			ActionMapping actionMapping, HttpSession sessao) {

		/*
		 * [SB0004] Obt�m e Habilita/Desabilita Dados da Identifica��o do
		 * Local da Ocorr�ncia e Dados do Solicitante
		 * 
		 * [FS0019] erificar endere�o do im�vel [FS0020] - Verificar
		 * exist�ncia de registro de atendimento para o im�vel com a mesma
		 * especifica��o
		 * 
		 * [SB0020] Verifica Situa��o do Im�vel e Especifica��o
		 * 
		 */

		// [SB0002] Habilita/Desabilita Munic�pio, Bairro, �rea do
		// Bairro e
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
			.obterDadosIdentificacaoLocalOcorrenciaAtualizar(new Integer(
					atualizarRegistroAtendimentoActionForm.getIdImovel()),
					new Integer(atualizarRegistroAtendimentoActionForm
							.getEspecificacao()), new Integer(
							atualizarRegistroAtendimentoActionForm
									.getTipoSolicitacao()), new Integer(
							atualizarRegistroAtendimentoActionForm
									.getNumeroRA()), true);

			
			if (dadosIdentificacaoLocalOcorrencia.getImovel() != null) {

				// [FS0020] - Verificar exist�ncia de registro de
				// atendimento
				// para o im�vel com a mesma especifica��o
				fachada.verificarExistenciaRAImovelMesmaEspecificacao(
						dadosIdentificacaoLocalOcorrencia.getImovel().getId(),
						new Integer(atualizarRegistroAtendimentoActionForm
								.getEspecificacao()));

				// [SB0020] Verifica Situa��o do im�vel e
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

			if ((descricaoBairro == null || descricaoBairro.equals(""))) {

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
	
	private RegistroAtendimentoUnidade montarRegistroAtendimentoParaEncerramento(Integer idRegistroAtendimento, Usuario usuarioLogado) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, idRegistroAtendimento));
		
		Collection colecaoRegistroAtendimento = fachada
		.pesquisar(filtroRegistroAtendimento,
				RegistroAtendimento.class.getName());
		
		RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
		
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID, AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO));
		
		Collection colecaoAtendimentoMotivoEncerramento = fachada
		.pesquisar(filtroAtendimentoMotivoEncerramento,
				AtendimentoMotivoEncerramento.class.getName());
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);
		
		registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		
		RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
		registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
		registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
		registroAtendimentoUnidade.setUsuario(usuarioLogado);
		registroAtendimentoUnidade.setUltimaAlteracao(new Date());
		registroAtendimentoUnidade.getRegistroAtendimento().setDataEncerramento(new Date());
		
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
		
		registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
		
		return registroAtendimentoUnidade;
	}
}
