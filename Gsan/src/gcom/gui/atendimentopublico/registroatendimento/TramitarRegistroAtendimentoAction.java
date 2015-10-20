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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Tramitar Registro de Atendimento
 *
 * @author Pedro Alexandre
 * @date 10/01/2008
 */
public class TramitarRegistroAtendimentoAction extends GcomAction {

	/**
	 * [UC0371] Inserir Equipe
	 * 
	 * [UC0107] Registrar Transa��o
	 * 
	 * @author Leonardo Regis,Pedro Alexandre
	 * @date   18/08/2006, 10/01/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		// Form
		TramitarRegistroAtendimentoActionForm tramitarRegistroAtendimentoActionForm = (TramitarRegistroAtendimentoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.setAttribute("tramitarConjunto","nao");
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Tramite
		Tramite tramite = null;
		if (tramitarRegistroAtendimentoActionForm.getUnidadeDestinoId() != null) {
			// Recupera informa��es do tr�mite
			tramite = getTramite(tramitarRegistroAtendimentoActionForm);

			//Verifica se o usu�rio apertou o bot�o na p�gian de tramitar
			String primeiraVez = httpServletRequest.getParameter("primeiraVez");
			
			//Recupera a cole��o de ordem de servi�o que j� foram processadas 
			Collection colecaoOrdemServicoJaTratada = (Collection)sessao.getAttribute("colecaoOrdemServicoJaTratada");
			
			//Cria os indicadores de centralizadora e terceira
			boolean flagCentralizadora = false;
			boolean flagTerceira = false;
			
			//Caso a unidade organizacional origem seja uma centralizadora
			if(tramite.getUnidadeOrganizacionalOrigem().getUnidadeTipo().getId().equals(UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA_ID)){
				flagCentralizadora = true;
			}
			
			//Caso a unidade organizacional destino seja uma terceira
			if(tramite.getUnidadeOrganizacionalDestino().getUnidadeTipo().getId().equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO_ID)){
				flagTerceira = true;
			}
			
			Collection colecaoOrdemServicoMovimento = new ArrayList();
			Collection colecaoOrdemServicoPavimento = new ArrayList();
			//Exportar as ordems de servi�o
			if(flagCentralizadora && flagTerceira){
				Collection<OrdemServico> colecaoOrdemServicoPrestadora = new ArrayList();
				if(primeiraVez != null && primeiraVez.equals("ok")){
					
					Map dadosExportar =  
						this.getFachada().exportarOrdemServicoPrestadoras(Collections.singletonList(tramite));
					
					colecaoOrdemServicoPrestadora = (Collection)dadosExportar.get("colecaoOrdemServico");
					colecaoOrdemServicoMovimento = (Collection)dadosExportar.get("colecaoOrdemServicoMovimento");
					
					sessao.setAttribute("colecaoOrdemServicoMovimento", colecaoOrdemServicoMovimento);
					sessao.setAttribute("colecaoOrdemServicoPrestadora",colecaoOrdemServicoPrestadora);
				}else{
					colecaoOrdemServicoPrestadora = (Collection)sessao.getAttribute("colecaoOrdemServicoPrestadora");
					colecaoOrdemServicoMovimento = (Collection)sessao.getAttribute("colecaoOrdemServicoMovimento");
				}
				
				if(colecaoOrdemServicoJaTratada!= null && !colecaoOrdemServicoJaTratada.isEmpty()){
					colecaoOrdemServicoPrestadora.removeAll(colecaoOrdemServicoJaTratada);
				}
				
				//Caso ainda tenha ordem de servi�o de prestadora para ser processada.
				if(colecaoOrdemServicoPrestadora!= null && !colecaoOrdemServicoPrestadora.isEmpty()){
					for(OrdemServico ordemServico : colecaoOrdemServicoPrestadora){
						
						//Caso a ordem de servi�o tenha im�vel, exibir o popup para 
						//inserir os dados do paviemnto 
						//Caso contr�rio inserir a ordem de servi�o pavimento 
						//com os dados de pavimento nulos.
						if(ordemServico.getImovel() != null){
							sessao.setAttribute("ordemServico",ordemServico);
							sessao.setAttribute("imovel",ordemServico.getImovel());
							httpServletRequest.setAttribute("indicadorPavimento","sim");							
							retorno = actionMapping.findForward("tramitacaoRegistroAtendimento");
							
							return retorno;
						}else{
							//Inseri a OrdemServicoPavimento com os dados de pavimento							
							OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();
							ordemServicoPavimento.setOrdemServico(ordemServico);
							ordemServicoPavimento.setPavimentoRua(null);
							ordemServicoPavimento.setAreaPavimentoRua(null);
							ordemServicoPavimento.setPavimentoCalcada(null);
							ordemServicoPavimento.setAreaPavimentoCalcada(null);
							ordemServicoPavimento.setPavimentoRuaRetorno(null);
							ordemServicoPavimento.setAreaPavimentoRuaRetorno(null);
							ordemServicoPavimento.setPavimentoCalcadaRetorno(null);
							ordemServicoPavimento.setAreaPavimentoCalcadaRetorno(null);
							ordemServicoPavimento.setDataGeracao(new Date());
							
							if(sessao.getAttribute("colecaoOrdemServicoPavimento") != null){
								colecaoOrdemServicoPavimento = (Collection)sessao.getAttribute("colecaoOrdemServicoPavimento");
							}	
							
							colecaoOrdemServicoPavimento.add(ordemServicoPavimento);
							
							sessao.setAttribute("colecaoOrdemServicoPavimento", colecaoOrdemServicoPavimento);
							
						}
					}
				}
			}
				
			this.getFachada().tramitarRAExportandoOSPrestadoras(tramite, 
				tramitarRegistroAtendimentoActionForm.getDataConcorrenciaRA(),
				usuario, 
				colecaoOrdemServicoPavimento, 
				colecaoOrdemServicoMovimento); 
			
			// [FS008] Monta a p�gina de sucesso
			montarPaginaSucesso(httpServletRequest, 
								"Registro de Atendimento "+tramite.getRegistroAtendimento().getId()+" tramitado com sucesso!", 
								"Efetuar outra Tramita��o do Registro de Atendimento",
								"exibirTramitarRegistroAtendimentoAction.do?resetarTramitacao=true&numeroRA="+tramite.getRegistroAtendimento().getId(),
								"exibirConsultarRegistroAtendimentoAction.do?numeroRA="
								+ tramite.getRegistroAtendimento().getId().toString(), "Voltar");
		}
		return retorno;
	}

	/**
	 * Carrega Tr�mite com informa��es vindas da tela 
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 */
	private Tramite getTramite(TramitarRegistroAtendimentoActionForm form) {
		
		Tramite tramite = new Tramite();
		
		// Unidade Origem
		UnidadeOrganizacional unidadeOrigem = null;
		if (form.getUnidadeAtualId() != null &&	!form.getUnidadeAtualId().equals("")) {
			
			FiltroUnidadeOrganizacional filtroUnidadeOrigem = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrigem.adicionarParametro(
				new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, 
					form.getUnidadeAtualId()));
			
			filtroUnidadeOrigem.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");

			Collection colecaoUnidadeOrigem = 
				this.getFachada().pesquisar(filtroUnidadeOrigem, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrigem != null && !colecaoUnidadeOrigem.isEmpty()) {
				unidadeOrigem = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrigem);		
			}else{
	        	//levanta a exce��o para a pr�xima camada
	        	throw new ActionServletException("atencao.naocadastrado",null, "Unidade Organizacional Origem");	
			}
			
			
		}
		UnidadeOrganizacional unidadeCentralizadora = null;
		if (form.getUnidadeAtualIdCentralizadora() != null && 
			!form.getUnidadeAtualIdCentralizadora().equals("")) {
			
			unidadeCentralizadora = new UnidadeOrganizacional();
			unidadeCentralizadora.setId(new Integer(form.getUnidadeAtualIdCentralizadora()));			
		}
		unidadeOrigem.setUnidadeCentralizadora(unidadeCentralizadora);
		tramite.setUnidadeOrganizacionalOrigem(unidadeOrigem);
		
		// Unidade Destino
		UnidadeOrganizacional unidadeDestino = null;
		
		// Filtra Unidade de Destino
		FiltroUnidadeOrganizacional filtroUnidadeDestino = new FiltroUnidadeOrganizacional();
		filtroUnidadeDestino.adicionarParametro(
			new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, 
				form.getUnidadeDestinoId()));
		filtroUnidadeDestino.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		// Recupera Unidade de Destino
		Collection colecaoUnidadeDestino = 
			this.getFachada().pesquisar(filtroUnidadeDestino, UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidadeDestino != null && !colecaoUnidadeDestino.isEmpty()) {
			unidadeDestino = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeDestino);		
		}else{
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Unidade Destino");	
		}
			

		tramite.setUnidadeOrganizacionalDestino(unidadeDestino);

		// Registro de Atendimento
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setId(new Integer(form.getNumeroRA()));
		registroAtendimento.setCodigoSituacao(new Short(form.getCodigoSituacaoRA()));
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
		solicitacaoTipoEspecificacao.setId(new Integer(form.getEspecificacaoId()));
		SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
		solicitacaoTipo.setId(new Integer(form.getTipoSolicitacaoId()));
		solicitacaoTipo.setIndicadorTarifaSocial(new Short(form.getTipoSolicitacaoIndicadorTarifaSocial()));
		solicitacaoTipoEspecificacao.setSolicitacaoTipo(solicitacaoTipo);
		
		registroAtendimento.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		
		tramite.setRegistroAtendimento(registroAtendimento);
		
		// Usu�rio Registro
		Usuario usuarioRegistro = new Usuario();
		usuarioRegistro.setId(new Integer(form.getUsuarioRegistroId()));
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		unidadeOrganizacional.setId(new Integer(form.getUsuarioRegistroUnidade()));
		unidadeOrganizacional.setIndicadorTarifaSocial(new Short(form.getUsuarioRegistroUnidadeIndicadorTarifaSocial()));
		usuarioRegistro.setUnidadeOrganizacional(unidadeOrganizacional);
		
		tramite.setUsuarioRegistro(usuarioRegistro);
		
		// Usu�rio Respons�vel
		if (form.getUsuarioResponsavelId() != null && 
			!form.getUsuarioResponsavelId().equals("")) {
			
			Usuario usuarioResponsavel = new Usuario();
			usuarioResponsavel.setId(new Integer(form.getUsuarioResponsavelId()));
			tramite.setUsuarioResponsavel(usuarioResponsavel);
		}
		if(form.getParecerTramite() != null && !form.getParecerTramite().equals("")){
			tramite.setParecerTramite(form.getParecerTramite());
		}else{
			tramite.setParecerTramite(null);
		}
		tramite.setDataTramite(Util.converteStringParaDateHora(form.getDataTramite()+" "+form.getHoraTramite()+":00"));
		tramite.setUltimaAlteracao(new Date());
		return tramite;
	}
}