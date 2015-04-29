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
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gsan.atendimentopublico.registroatendimento.bean.RegistroAtendimentoFaltaAguaGeneralizadaHelper;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informa��es das tr�s abas do processo de inser��o
 * de um registro de atendimento e chamar o m�todo que ir� concluir a mesma
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class ConcluirInserirRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        InserirRegistroAtendimentoActionForm form = 
        (InserirRegistroAtendimentoActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);

        sessao.removeAttribute("gis");
        

        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        /*
         * Valida��o Aba 01
         */
        this.getFachada().validarInserirRegistroAtendimentoDadosGerais(
        	form.getDataAtendimento(),
        	form.getHora(), 
        	form.getTempoEsperaInicial(), 
        	form.getTempoEsperaFinal(), 
        	form.getUnidade(), 
        	form.getNumeroAtendimentoManual());
        
        if (!form.getIdImovel().equals("") && 
        	!form.getEspecificacao().equals("")){
        	
        	this.getFachada().verificarExistenciaRAImovelMesmaEspecificacao(
        		new Integer(form.getIdImovel()), 
        		new Integer(form.getEspecificacao()));
        }
        
        /*
         * Valida��o Aba 02
         */
        
        //[FS0040] - Validar Preenchimento dos campos

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
		String idUnidade = form.getIdUnidadeDestino();
		String descricaoUnidade = form.getDescricaoUnidadeDestino();
		String idLocalOcorrencia = form.getIdLocalOcorrencia();
		String idPavimentoRua = form.getIdPavimentoRua();
		String idPavimentoCalcada = form.getIdPavimentoCalcada();
		String descricaoLocalOcorrencia = form.getDescricaoLocalOcorrencia();
		String imovelObrigatorio = form.getImovelObrigatorio();
		String pavimentoRuaObrigatorio = form.getPavimentoRuaObrigatorio();
		String pavimentoCalcadaObrigatorio = form.getPavimentoCalcadaObrigatorio();
		
		String solicitacaoTipoRelativoFaltaAgua = 
			(String) sessao.getAttribute("solicitacaoTipoRelativoFaltaAgua");
		
		String solicitacaoTipoRelativoAreaEsgoto = 
			(String) sessao.getAttribute("solicitacaoTipoRelativoAreaEsgoto");

		String desabilitarMunicipioBairro = 
			(String) sessao.getAttribute("desabilitarMunicipioBairro");
		
		String indRuaLocalOcorrencia = form.getIndRuaLocalOcorrencia();
		String indCalcadaLocalOcorrencia = form.getIndCalcadaLocalOcorrencia();
		String idEspecificacao = form.getEspecificacao();	
		String idMeioSolicitacao = form.getMeioSolicitacao();
		
		Collection colecaoEnderecos = 
			(Collection) sessao.getAttribute("colecaoEnderecos");

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

		this.getFachada().validarCamposObrigatoriosRA_2ABA(
			idImovel, 
			pontoReferencia,
			idMunicipio, 
			descricaoMunicipio, 
			cdBairro, 
			descricaoBairro,
			idAreaBairro, 
			idlocalidade, 
			descricaoLocalidade,
			cdSetorComercial, 
			descricaoSetorComercial, 
			numeroQuadra,
			idDivisaoEsgoto, 
			idUnidade, 
			descricaoUnidade,
			idLocalOcorrencia, 
			idPavimentoRua, 
			idPavimentoCalcada,
			descricaoLocalOcorrencia, 
			imovelObrigatorio,
			pavimentoRuaObrigatorio, 
			pavimentoCalcadaObrigatorio,
			solicitacaoTipoRelativoFaltaAgua,
			solicitacaoTipoRelativoAreaEsgoto, 
			desabilitarMunicipioBairro,
			indRuaLocalOcorrencia, 
			indCalcadaLocalOcorrencia, 
			new Integer(idEspecificacao), 
			null, 
			colecaoEnderecos,
			especificacao,
			colecaoPagamento,usuarioLogado);

		// valida os campos de enter(caso tenha mudado algum valor validar)
		validarCamposEnter(form, httpServletRequest, actionMapping, sessao);

		BigDecimal nnDiametro = null;
		
		if(form.getNnDiamentro()!=null && !form.getNnDiamentro().equals("")){
			nnDiametro = new BigDecimal(form.getNnDiamentro());
		}
		/*
         * Valida��o Aba 03
         * ======================================================================================================
         */
        String nomeSolicitante = null;

        if (form.getNomeSolicitante() != null && 
			!form.getNomeSolicitante().equalsIgnoreCase("")){
			nomeSolicitante = form.getNomeSolicitante();
		}
		
		Collection colecaoEnderecoSolicitante = null;
		if (sessao.getAttribute("colecaoEnderecosAbaSolicitante") != null){
			colecaoEnderecoSolicitante = (Collection) sessao.getAttribute("colecaoEnderecosAbaSolicitante");
		}
		
		Collection colecaoFone = null;
		if (sessao.getAttribute("colecaoFonesAbaSolicitante") != null){
			colecaoFone = (Collection) sessao.getAttribute("colecaoFonesAbaSolicitante");
		}
		
		Short indicadorClienteEspecificacao = null;
		if (form.getIndicadorClienteEspecificacao() != null && 
			!form.getIndicadorClienteEspecificacao().equalsIgnoreCase("")){
			indicadorClienteEspecificacao = new Short(form.getIndicadorClienteEspecificacao());
		}
		
		Boolean habilitarCampoSatisfacaoEmail = 
			(Boolean) sessao.getAttribute("habilitarCampoSatisfacaoEmail");
		
		//[FS0030] Verificar preenchimento dos dados de identifica��o do solicitante
		this.getFachada().verificaDadosSolicitante(
			Util.converterStringParaInteger(form.getIdCliente()), 
			Util.converterStringParaInteger(form.getIdUnidadeSolicitante()),
			Util.converterStringParaInteger(form.getIdFuncionarioResponsavel()), nomeSolicitante,
			colecaoEnderecoSolicitante, 
			colecaoFone, 
			indicadorClienteEspecificacao, 
			Util.converterStringParaInteger(form.getIdImovel()), 
			null, 
			Util.converterStringParaInteger(form.getEspecificacao()),
			Util.converterStringParaInteger(idMeioSolicitacao) );
		
		//Valida o campo enderecoEmail e enviarEmailSatisfacao do Formul�rio caso necess�rio
		if(habilitarCampoSatisfacaoEmail != null && habilitarCampoSatisfacaoEmail.booleanValue()){
			if(form.getEnviarEmailSatisfacao() != null && !form.getEnviarEmailSatisfacao().equals("") && form.getEnviarEmailSatisfacao().equals("1")
					&& form.getEnderecoEmail().equals("")){
				throw new ActionServletException("atencao.required", null,"Endere�o de email!");
			}
		}else{
			habilitarCampoSatisfacaoEmail = false;
		}

        Fachada fachada = Fachada.getInstancia();
		//======================================================================================================
		//Dados enviados do AcquaGis para o Gsan
		//As coordenadas foram invertidas para sicronizar com o AcquaGis.
		//======================================================================================================
		BigDecimal coordenadaNorte = null;
		BigDecimal coordenadaLeste = null;

		DecimalFormat dff = new DecimalFormat("########0.000000000000;-########0.000000000000");	
		
		if(form.getNnCoordenadaNorte()!=null && !form.getNnCoordenadaNorte().equalsIgnoreCase("")){
			String cordenadasY = form.getNnCoordenadaNorte();					
			if(cordenadasY != null){
				cordenadasY = fachada.validaBigDecimal(cordenadasY, dff,"Norte");			
			}
			coordenadaNorte = new BigDecimal (cordenadasY.replace(",","."));
			
		}
		
		if(form.getNnCoordenadaLeste()!=null && !form.getNnCoordenadaLeste().equalsIgnoreCase("")){
			String cordenadasX = form.getNnCoordenadaLeste();
			
			if(cordenadasX != null){
				cordenadasX = fachada.validaBigDecimal(cordenadasX, dff,"Leste");			
			}
			coordenadaLeste = new BigDecimal(cordenadasX.replace(",","."));			
		}
		
		
		/*
         * Valida��o Aba 04 - Anexos
         */
		Collection colecaoRegistroAtendimentoAnexo = null;
		
		if (sessao.getAttribute("colecaoRegistroAtendimentoAnexo") != null){
			
			colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");	
		}
		
		//ServicoTipo
		Integer idServicoTipo = null;
		if (sessao.getAttribute("servicoTipo") != null){
			idServicoTipo = (Integer) sessao.getAttribute("servicoTipo");
		}
		
		Collection colecaoEnderecoLocalOcorrencia = null;
		
		if (sessao.getAttribute("colecaoEnderecos") != null){
			colecaoEnderecoLocalOcorrencia = (Collection) sessao.getAttribute("colecaoEnderecos");
		}
		
		Collection colecaoContas = null;
		
		if (sessao.getAttribute("colecaoConta") != null){
			colecaoContas = (Collection) sessao.getAttribute("colecaoConta");
		}
		

		
		//Usu�rio logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		/*
		 * Raphael Rossiter em 29/05/2007
		 * Caso o usu�rio tente inserir o mesmo RA por mais de uma vez
		 */
		String idRAJAGeradoSessao = (String) sessao.getAttribute("idRegistroAtendimento");
		Integer idRAJAGerado = null;
		
		if (idRAJAGeradoSessao != null){
			idRAJAGerado = new Integer(idRAJAGeradoSessao); 
		}
		
		
		Integer[] idsGerados = null;		
		
		//[SB0025] Verifica Registro de Atendimento de Falta de �gua Generalizada
		String efetivarInclusao = httpServletRequest.getParameter("efetivarInclusao");
		
		if (efetivarInclusao == null || efetivarInclusao.equalsIgnoreCase("")){
			
			RegistroAtendimentoFaltaAguaGeneralizadaHelper faltaAguaGeneralizada = 
				this.getFachada().verificarRegistroAtendimentoFaltaAguaGeneralizafa(
					Util.converterStringParaInteger(form.getEspecificacao()),
					Util.converterStringParaInteger(form.getIdBairroArea()));
			
			if (faltaAguaGeneralizada != null){
				
				httpServletRequest.setAttribute("atencao",
					"Existem Registros de Atendimento de " 
					+ faltaAguaGeneralizada.getSolicitacaoTipoEspecificacao().getDescricao()
					+ " em aberto para a �rea do bairro "
					+ faltaAguaGeneralizada.getBairroArea().getNome());
				
				//URL concluir
				httpServletRequest.setAttribute(
					"concluir",
					"/gsan/inserirRegistroAtendimentoWizardAction.do?concluir=true&action=inserirRegistroAtendimentoDadosSolicitanteAction&efetivarInclusao=OK");

				// Tipo chamada (Popup ou tela convencional)
				httpServletRequest.setAttribute("tipoChamada", "popup");

				// Label bot�o utilit�rio
				httpServletRequest.setAttribute("labelBotao","Encerrar RA");

				// URL bot�o utilit�rio
				httpServletRequest.setAttribute(
					"urlBotao",
					"pesquisarRegistrosAtendimentoPendentesFaltaAguaEncerrarAction.do?idEspecificacao="
						+ form.getEspecificacao()
						+ "&idBairroArea="
						+ faltaAguaGeneralizada.getBairroArea().getId());

				retorno = actionMapping.findForward("telaOpcaoConsultar");
			} else{
				
				//[SB0028] Inclui Registro de Atendimento
				boolean indicadorEncerramentoAutomatico = false;
				
				if (especificacao.getIndicadorEncerramentoAutomatico() == 
						SolicitacaoTipoEspecificacao.INDICADOR_COM_ENCERRAMENTO_AUTOMATICO.shortValue()) {
					
					indicadorEncerramentoAutomatico = true;
					
					if (form.getObservacao() == null || form.getObservacao().trim().equals("")) {
						throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Observa��o");
					}
				}
				
				if(form.getObservacao() != null && 
					!form.getObservacao().equals("") && 
					form.getObservacao().length() > 400){

					String[] msg = new String[2];
					msg[0]="Observa��o";
					msg[1]="400";
					
						
					throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
				}
				
				idsGerados = 
					this.getFachada().inserirRegistroAtendimento(Short.parseShort(form.getTipo()),
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
						usuario.getId(),
						Util.converterStringParaInteger(form.getIdCliente()), 
						form.getPontoReferenciaSolicitante(),
						form.getNomeSolicitante(), 
						false,
						Util.converterStringParaInteger(form.getIdUnidadeSolicitante()), 
						Util.converterStringParaInteger(form.getIdFuncionarioResponsavel()),
						colecaoFone, 
						colecaoEnderecoSolicitante,
						Util.converterStringParaInteger(form.getIdUnidadeDestino()), 
						form.getParecerUnidadeDestino(), 
						idServicoTipo, 
						form.getNumeroAtendimentoManual(), 
						idRAJAGerado,
						coordenadaNorte,
						coordenadaLeste,
						(Short)(sessao.getAttribute("indicCoordenadaSemLogradouro")), 
						colecaoRegistroAtendimentoAnexo,
						(String) sessao.getAttribute("protocoloAtendimento"),
						colecaoContas, 
						null,
						colecaoPagamento, habilitarCampoSatisfacaoEmail.booleanValue()+"", 
						form.getEnviarEmailSatisfacao(), form.getEnderecoEmail(),
						nnDiametro);
				
				sessao.setAttribute("idRegistroAtendimento", idsGerados[0].toString());
				
				// Caso a especifica��o seja de encerramento autom�tico, encerra o ra
				if (indicadorEncerramentoAutomatico) {
					
					RegistroAtendimentoUnidade registroAtendimentoUnidade = 
						this.montarRegistroAtendimentoParaEncerramento(idsGerados[0], usuario);
					
					registroAtendimentoUnidade.getRegistroAtendimento().setDataEncerramento(
							registroAtendimentoUnidade.getRegistroAtendimento().getRegistroAtendimento());
					
					this.getFachada().encerrarRegistroAtendimento(
						registroAtendimentoUnidade.getRegistroAtendimento(),
						registroAtendimentoUnidade, 
						usuario, 
						null,
						null, 
						null, 
						null, 
						false,
						null,
						false );
				}
				
				
				//Montando a p�gina de sucesso
				if (!this.getFachada().gerarOrdemServicoAutomatica(Util.converterStringParaInteger(form.getEspecificacao())) && 
					this.getFachada().gerarOrdemServicoOpcional(Util.converterStringParaInteger(form.getEspecificacao()))){
					
					montarPaginaSucessoUmRelatorio(httpServletRequest, 
						"Registro de Atendimento de c�digo " + idsGerados[0] + " inserido com sucesso.", 
						"Inserir outro Registro de Atendimento",
		                "exibirInserirRegistroAtendimentoAction.do?menu=sim",
		                "exibirConsultarRegistroAtendimentoAction.do?numeroRA=" + idsGerados[0],
		                "Atualizar Registro de Atendimento inserido", 
		                "Gerar OS",
						"exibirGerarOrdemServicoAction.do?forward=exibirGerarOrdemServico&veioRA=OK&idRegistroAtendimento=" + idsGerados[0],
						"Imprimir RA",
						"gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idsGerados[0]);
				} else{
					
					if (this.getFachada().gerarOrdemServicoAutomatica(Util.converterStringParaInteger(form.getEspecificacao()))){
					
						montarPaginaSucessoDoisRelatorios(httpServletRequest, 
							"Registro de Atendimento de c�digo " + idsGerados[0] + " e Ordem de Servi�o de c�digo " + idsGerados[1] + " inseridos com sucesso.", 
							"Inserir outro Registro de Atendimento",
							"exibirInserirRegistroAtendimentoAction.do?menu=sim",
							"exibirConsultarRegistroAtendimentoAction.do?numeroRA=" + idsGerados[0],
							"Atualizar Registro de Atendimento inserido",
							"Imprimir RA", "gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idsGerados[0],
							"Imprimir OS", "gerarRelatorioOrdemServicoAction.do?idsOS=" + idsGerados[1]);
					} else{
						
						montarPaginaSucessoUmRelatorio(httpServletRequest, 
							"Registro de Atendimento de c�digo " + idsGerados[0] + " inserido com sucesso.", 
							"Inserir outro Registro de Atendimento",
							"exibirInserirRegistroAtendimentoAction.do?menu=sim",
							"exibirConsultarRegistroAtendimentoAction.do?numeroRA=" + idsGerados[0],
							"Atualizar Registro de Atendimento inserido", 
							"Imprimir RA", 
							"gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idsGerados[0]);
					}
					 
				}
			}
		} else {
			
			//[SB0028] Inclui Registro de Atendimento
			if(form.getObservacao() != null && 
				!form.getObservacao().equals("") && 
				form.getObservacao().length() > 400){

				String[] msg = new String[2];
				msg[0]="Observa��o";
				msg[1]="400";
						
				throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
			}
			
			
			idsGerados = 
				this.getFachada().inserirRegistroAtendimento(
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
					usuario.getId(),
					Util.converterStringParaInteger(form.getIdCliente()), 
					form.getPontoReferenciaSolicitante(),
					form.getNomeSolicitante(), 
					false,
					Util.converterStringParaInteger(form.getIdUnidadeSolicitante()), 
					Util.converterStringParaInteger(form.getIdFuncionarioResponsavel()),
					colecaoFone, 
					colecaoEnderecoSolicitante,
					Util.converterStringParaInteger(form.getIdUnidadeDestino()), 
					form.getParecerUnidadeDestino(), 
					idServicoTipo, 
					form.getNumeroAtendimentoManual(), 
					idRAJAGerado,
					coordenadaNorte,
					coordenadaLeste,
					(Short)(sessao.getAttribute("indicCoordenadaSemLogradouro")), 
					colecaoRegistroAtendimentoAnexo,
					(String) sessao.getAttribute("protocoloAtendimento"),
					colecaoContas, 
					null,
					colecaoPagamento,
					habilitarCampoSatisfacaoEmail.booleanValue()+"", form.getEnviarEmailSatisfacao(), form.getEnderecoEmail(),
					nnDiametro);
			
			sessao.setAttribute("idRegistroAtendimento", idsGerados[0].toString());
			
			//Montando a pagina de sucesso
			if (!this.getFachada().gerarOrdemServicoAutomatica(Util.converterStringParaInteger(form.getEspecificacao())) 
				&& this.getFachada().gerarOrdemServicoOpcional(Util.converterStringParaInteger(form.getEspecificacao()))){
				
				montarPaginaSucessoUmRelatorio(httpServletRequest, 
					"Registro de Atendimento de c�digo " + idsGerados[0] + " inserido com sucesso.", 
					"Inserir outro Registro de Atendimento",
                    "exibirInserirRegistroAtendimentoAction.do?menu=sim",
                    "exibirConsultarRegistroAtendimentoAction.do?numeroRA=" + idsGerados[0],
					"Atualizar Registro de Atendimento inserido", 
					"Gerar OS",
					"exibirGerarOrdemServicoAction.do?forward=exibirGerarOrdemServico&veioRA=OK&idRegistroAtendimento=" + idsGerados[0],
					"Imprimir RA",
					"gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idsGerados[0]);
			}
			else{
				
				if (this.getFachada().gerarOrdemServicoAutomatica(Util.converterStringParaInteger(form.getEspecificacao()))){
					
					montarPaginaSucessoDoisRelatorios(httpServletRequest, 
						"Registro de Atendimento de c�digo " + idsGerados[0] + " e Ordem de Servi�o de c�digo " + idsGerados[1] + " inseridos com sucesso.", 
						"Inserir outro Registro de Atendimento",
						"exibirInserirRegistroAtendimentoAction.do?menu=sim",
						"exibirConsultarRegistroAtendimentoAction.do?numeroRA=" + idsGerados[0],
						"Atualizar Registro de Atendimento inserido", 
						"Imprimir RA", 
						"gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idsGerados[0],
						"Imprimir OS", 
						"gerarRelatorioOrdemServicoAction.do?idsOS=" + idsGerados[1]);
				}
				else{
					
					montarPaginaSucessoUmRelatorio(httpServletRequest, 
						"Registro de Atendimento de c�digo " + idsGerados[0] + " inserido com sucesso.", 
						"Inserir outro Registro de Atendimento",
						"exibirInserirRegistroAtendimentoAction.do?menu=sim",
						"exibirConsultarRegistroAtendimentoAction.do?numeroRA=" + idsGerados[0],
						"Imprimir RA",
						"Atualizar Registro de Atendimento inserido" , 	
						"gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idsGerados[0]);
				} 
			}
		}
		
		if(idsGerados != null){
			//[SB0034] � Verificar RA de urg�ncia
			if(getFachada().verificarRegistroAtendimentoUrgencia(idsGerados[0]) > 0){
				
				getFachada().inserirUsuarioVisualizacaoRaUrgencia(idsGerados[0],ConstantesSistema.NAO);
				
			}			
		}
		
		sessao.removeAttribute("gis");	
		
        //sessao.removeAttribute("origemGIS");
		
		return retorno;
	}
		
	
	
	private void validarCamposEnter(
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
			HttpServletRequest httpServletRequest,
			ActionMapping actionMapping, HttpSession sessao) {
		
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
		ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto = this.getFachada()
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
			
			
			ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = this.getFachada()
			.obterDadosIdentificacaoLocalOcorrencia(new Integer(
					inserirRegistroAtendimentoActionForm.getIdImovel()),
					new Integer(inserirRegistroAtendimentoActionForm
							.getEspecificacao()), new Integer(
							inserirRegistroAtendimentoActionForm
									.getTipoSolicitacao()), true);

			
			if (dadosIdentificacaoLocalOcorrencia.getImovel() != null) {

				// [FS0020] - Verificar exist�ncia de registro de
				// atendimento
				// para o im�vel com a mesma especifica��o
				this.getFachada().verificarExistenciaRAImovelMesmaEspecificacao(
						dadosIdentificacaoLocalOcorrencia.getImovel().getId(),
						new Integer(inserirRegistroAtendimentoActionForm
								.getEspecificacao()));

				// [SB0020] Verifica Situa��o do Im�vel e
				// Especifica��o
				this.getFachada().verificarSituacaoImovelEspecificacao(
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
					sessao.removeAttribute("colecaoEnderecos");
				} else {
					sessao.removeAttribute("colecaoEnderecos");
				}

				this.carregarMunicipioBairroParaImovel(
						habilitaGeograficoDivisaoEsgoto,
						dadosIdentificacaoLocalOcorrencia,
						inserirRegistroAtendimentoActionForm, sessao);

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

			Collection colecaoMunicipio = this.getFachada().pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Munic�pio");

			}
			Municipio municipio = (Municipio) Util
					.retonarObjetoDeColecao(colecaoMunicipio);

			inserirRegistroAtendimentoActionForm.setIdMunicipio(municipio
					.getId().toString());
			inserirRegistroAtendimentoActionForm
					.setDescricaoMunicipio(municipio.getNome());

			httpServletRequest.setAttribute("nomeCampo", "cdBairro");
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

				Collection colecaoBairro = this.getFachada().pesquisar(filtroBairro,
						Bairro.class.getName());

				if (colecaoBairro == null || colecaoBairro.isEmpty()) {

					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Bairro");

				}
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
								.getIdBairro()), sessao);
			}

		}

		String idLocalidade = inserirRegistroAtendimentoActionForm
				.getIdLocalidade();
		String descricaoLocalidade = inserirRegistroAtendimentoActionForm
				.getDescricaoBairro();

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

			Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");

			}
			Localidade localidade = (Localidade) Util
					.retonarObjetoDeColecao(colecaoLocalidade);

			inserirRegistroAtendimentoActionForm.setIdLocalidade(localidade
					.getId().toString());
			inserirRegistroAtendimentoActionForm
					.setDescricaoLocalidade(localidade.getDescricao());

			httpServletRequest
					.setAttribute("nomeCampo", "cdSetorComercial");
		}

		String cdSetorComercial = inserirRegistroAtendimentoActionForm
				.getCdSetorComercial();
		String descricaoSetorComercial = inserirRegistroAtendimentoActionForm
				.getDescricaoSetorComercial();

		if (cdSetorComercial != null
				&& !cdSetorComercial.equalsIgnoreCase("")
				&& (descricaoSetorComercial == null || descricaoSetorComercial
						.equals(""))) {

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

			Collection colecaoSetorComercial = this.getFachada().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial == null
					|| colecaoSetorComercial.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Setor Comercial");

			}
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

			Collection colecaoQuadra = this.getFachada().pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");

			}
			Quadra quadra = (Quadra) Util
					.retonarObjetoDeColecao(colecaoQuadra);

			inserirRegistroAtendimentoActionForm.setIdQuadra(quadra.getId()
					.toString());
			inserirRegistroAtendimentoActionForm.setNnQuadra(String
					.valueOf(quadra.getNumeroQuadra()));

			// [SB0006] Obt�m Divis�o de Esgoto
			DivisaoEsgoto divisaoEsgoto = this.getFachada().obterDivisaoEsgoto(quadra
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
								inserirRegistroAtendimentoActionForm,
								habilitaGeograficoDivisaoEsgoto
										.isSolicitacaoTipoRelativoAreaEsgoto());

			}
		}
	}
	
	
	public void verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
			boolean solicitacaoTipoRelativoAreaEsgoto) {

		this.getFachada()
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
	
	
	public void pesquisarBairroArea(Integer idBairro,
			HttpSession sessao) {

		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

		filtroBairroArea.adicionarParametro(new ParametroSimples(
				FiltroBairroArea.ID_BAIRRO, idBairro));

		Collection colecaoBairroArea = this.getFachada().pesquisar(filtroBairroArea,
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
			InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm,
			HttpSession sessao) {

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
							.getLogradouroBairro().getBairro().getId(),sessao);

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
		
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
		
		registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
		
		return registroAtendimentoUnidade;
	}
}
