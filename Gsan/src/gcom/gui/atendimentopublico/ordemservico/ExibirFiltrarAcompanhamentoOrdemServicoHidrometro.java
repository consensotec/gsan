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
* Ivan S�rgio Virginio da Silva J�nior
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

import java.util.Collection;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0732] Filtro para Relatorio Acompanhamento Ordem de Servico Hidrometro
 * 
 * @author Ivan S�rgio
 * @date 04/12/2007, 27/03/2008
 * @alteracao: Adicionado Motivo Encerramento; Setor Comercial;
 * 
 */
public class ExibirFiltrarAcompanhamentoOrdemServicoHidrometro extends
		GcomAction {

	private String localidadeID = null;
	private String codigoSetorComercial = null;
	private Collection colecaoPesquisa = null;
	
	private String setorComercialID = null;
	private String quadraNM = null;
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarAcompanhamentoOrdemServicoHidrometro");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm gerarRelatorioAcompanhamento = 
			(GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm) actionForm;
		
		// Limpa os Campos
		if (httpServletRequest.getParameter("menu") != null) {
			gerarRelatorioAcompanhamento.setTipoOrdem(
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_ORDEM_INSTALACAO);
			gerarRelatorioAcompanhamento.setSituacaoOrdemServico(
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.SITUACAO_ORDEM_SERVICO_ENCERRADAS);
		}
		
		// Seta o valor inicial para Tipo da Ordem
		if (gerarRelatorioAcompanhamento.getTipoOrdem() == null) {
			httpServletRequest.setAttribute("tipoOrdem", 
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_ORDEM_INSTALACAO);
			gerarRelatorioAcompanhamento.setTipoOrdem(
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_ORDEM_INSTALACAO);
			gerarRelatorioAcompanhamento.setFirma("");
			gerarRelatorioAcompanhamento.setNomeFirma("");
			gerarRelatorioAcompanhamento.setLocalidadeInicial("");
			gerarRelatorioAcompanhamento.setLocalidadeFinal("");
			gerarRelatorioAcompanhamento.setNomeLocalidadeInicial("");
			gerarRelatorioAcompanhamento.setNomeLocalidadeFinal("");
			gerarRelatorioAcompanhamento.settipoRelatorioAcompanhamento(
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_ANALITICO.toString());
		}
		
		// Seta o valor inicial para Situacao da Ordem
		if (gerarRelatorioAcompanhamento.getSituacaoOrdemServico() == null) {
			httpServletRequest.setAttribute("situacaoOrdemServico", 
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.SITUACAO_ORDEM_SERVICO_ENCERRADAS);
			gerarRelatorioAcompanhamento.setSituacaoOrdemServico(
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.SITUACAO_ORDEM_SERVICO_ENCERRADAS);
		}
		
		// Pesquisa Motivo de Encerramento
		if(sessao.getAttribute("colecaoMotivoEncerramento") == null){
			pesquisarMotivoEncerramento(gerarRelatorioAcompanhamento, fachada, sessao, httpServletRequest);
		}
		
		// Pesquisa Firma
		if(sessao.getAttribute("colecaoFirma") == null){
			pesquisarFirma(gerarRelatorioAcompanhamento, fachada, sessao, httpServletRequest);
		}
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		String inscricaoTipo = (String) httpServletRequest.getParameter("inscricaoTipo");
		
		if ((objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) ||
			 inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("")) {	
			
			switch (Integer.parseInt(objetoConsulta)) {
				// Localidade
				case 1:
					pesquisarLocalidade(inscricaoTipo, gerarRelatorioAcompanhamento, fachada, httpServletRequest);					
					break;
                // Setor Comercial
				case 2:
					pesquisarSetorComercial(inscricaoTipo, gerarRelatorioAcompanhamento, fachada, httpServletRequest);
					break;
					// Quadra
				case 3:
					pesquisarLocalidade(inscricaoTipo, gerarRelatorioAcompanhamento, fachada, httpServletRequest);
					pesquisarSetorComercial(inscricaoTipo, gerarRelatorioAcompanhamento, fachada, httpServletRequest);
					pesquisarQuadra(inscricaoTipo, gerarRelatorioAcompanhamento, fachada, httpServletRequest);					
					break;	
			}
		}
		
		// Seta o valor inicial do Tipo de Relatorio
		if (gerarRelatorioAcompanhamento.gettipoRelatorioAcompanhamento() == null) {
			httpServletRequest.setAttribute("tipoRelatorio", 
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_ANALITICO.toString());
			gerarRelatorioAcompanhamento.settipoRelatorioAcompanhamento(
					GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_ANALITICO.toString());
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa as Firmas
	 * 
	 * @param gerarRelatorioAcompanhamento
	 * @param fachada
	 * @param sessao
	 * @param httpServletRequest
	 */
	private void pesquisarFirma(GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm gerarRelatorioAcompanhamento,
			Fachada fachada,
			HttpSession sessao,
			HttpServletRequest httpServletRequest) {
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection<Empresa> colecaoFirma = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		// [FS0001 - Verificar Existencia de dados]
		if ( (colecaoFirma == null) || (colecaoFirma.size() == 0) ) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
		}else {
			sessao.setAttribute("colecaoFirma", colecaoFirma);
		}
	}
	
	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */	
	private void pesquisarLocalidade(
			String inscricaoTipo,
			GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm gerarRelatorioAcompanhamento,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) gerarRelatorioAcompanhamento.getLocalidadeInicial();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formul�rio
				gerarRelatorioAcompanhamento.setLocalidadeInicial("");
				gerarRelatorioAcompanhamento.setNomeLocalidadeInicial("Localidade inexistente");
				gerarRelatorioAcompanhamento.setLocalidadeFinal("");
				gerarRelatorioAcompanhamento.setNomeLocalidadeFinal("");
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeInicial");
				
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				gerarRelatorioAcompanhamento.setLocalidadeInicial(String.valueOf(objetoLocalidade.getId()));
				gerarRelatorioAcompanhamento.setNomeLocalidadeInicial(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				
				//destino
				gerarRelatorioAcompanhamento.setLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				gerarRelatorioAcompanhamento.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) gerarRelatorioAcompanhamento.getLocalidadeFinal();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
									
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formul�rio
				gerarRelatorioAcompanhamento.setLocalidadeFinal("");
				gerarRelatorioAcompanhamento.setNomeLocalidadeFinal("Localidade inexistente.");
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeFinal");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				gerarRelatorioAcompanhamento.setLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				gerarRelatorioAcompanhamento.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}

	}
	
	
//	/**
//	 * Pesquisa a Localidade
//	 * 
//	 * @param gerarRelatorioAcompanhamento
//	 * @param fachada
//	 * @param httpServletRequest
//	 */
//	private void pesquisarLocalidade(
//			GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm gerarRelatorioAcompanhamento,
//			Fachada fachada,
//			HttpServletRequest httpServletRequest) {
//
//		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
//
//		// Recebe o valor do campo localidadeOrigemID do formul�rio.
//		localidadeID = (String) gerarRelatorioAcompanhamento.getLocalidade();
//		
//		filtroLocalidade.adicionarParametro(new ParametroSimples(
//				FiltroLocalidade.ID, localidadeID));
//		
//		filtroLocalidade.adicionarParametro(new ParametroSimples(
//				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
//		
//		// Retorna localidade
//		colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
//		
//		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
//			// Localidade nao encontrada
//			// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
//			// formul�rio
//			gerarRelatorioAcompanhamento.setLocalidade("");
//			gerarRelatorioAcompanhamento.setNomeLocalidade("Localidade inexistente");
//			httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
//			httpServletRequest.setAttribute("nomeCampo", "localidade");
//			
//		} else {
//			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
//			gerarRelatorioAcompanhamento.setLocalidade(String.valueOf(objetoLocalidade.getId()));
//			gerarRelatorioAcompanhamento.setNomeLocalidade(objetoLocalidade.getDescricao());
//		}
//	}
	
	/**
	 * Pesquisa o Motivo de Encerramento
	 * 
	 * @param gerarRelatorioAcompanhamento
	 * @param fachada
	 * @param sessao
	 * @param httpServletRequest
	 */
	private void pesquisarMotivoEncerramento(GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm gerarRelatorioAcompanhamento,
			Fachada fachada,
			HttpSession sessao,
			HttpServletRequest httpServletRequest) {
		
		FiltroAtendimentoMotivoEncerramento filtroMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroMotivoEncerramento.adicionarParametro(new ParametroSimples(
				FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		
		Collection<AtendimentoMotivoEncerramento> colecaoMotivoEncerramento = 
			fachada.pesquisar(filtroMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		
		// [FS0001 - Verificar Existencia de dados]
		if ( (colecaoMotivoEncerramento == null) || (colecaoMotivoEncerramento.size() == 0) ) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null, AtendimentoMotivoEncerramento.class.getName());
		}else {
			sessao.setAttribute("colecaoMotivoEncerramento", colecaoMotivoEncerramento);
		}
	}
	
	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * 
	 * @param gerarRelatorioAcompanhamento
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm gerarRelatorioAcompanhamento,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			gerarRelatorioAcompanhamento.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) gerarRelatorioAcompanhamento.getLocalidadeInicial();
			
			// O campo localidadeOrigemID ser� obrigat�rio
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				codigoSetorComercial = (String) gerarRelatorioAcompanhamento.getCodigoSetorComercialInicial();
				
				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
				
				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formul�rio
					gerarRelatorioAcompanhamento.setCodigoSetorComercialInicial("");
					gerarRelatorioAcompanhamento.setSetorComercialInicial("");
					gerarRelatorioAcompanhamento.setNomeSetorComercialInicial("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");

					//destino
					gerarRelatorioAcompanhamento.setCodigoSetorComercialFinal("");
					gerarRelatorioAcompanhamento.setSetorComercialFinal("");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					gerarRelatorioAcompanhamento.setCodigoSetorComercialInicial(
							String.valueOf(objetoSetorComercial.getCodigo()));
					gerarRelatorioAcompanhamento.setSetorComercialInicial(
							String.valueOf(objetoSetorComercial.getId()));
					gerarRelatorioAcompanhamento.setNomeSetorComercialInicial(
							objetoSetorComercial.getDescricao());
					
					//httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
					//setorComercialOrigem
					
					//setorComercialDestino
					gerarRelatorioAcompanhamento.setCodigoSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getCodigo()));
					gerarRelatorioAcompanhamento.setSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getId()));
					gerarRelatorioAcompanhamento.setNomeSetorComercialFinal(
							objetoSetorComercial.getDescricao());
					
					//setorComercialDestino
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				}
				
				
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				gerarRelatorioAcompanhamento.setCodigoSetorComercialInicial("");
				gerarRelatorioAcompanhamento.setNomeSetorComercialInicial(
						"Informe a localidade da inscri��o de origem.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
			
		} else {
			gerarRelatorioAcompanhamento.setInscricaoTipo("destino");
			
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) gerarRelatorioAcompanhamento.getLocalidadeFinal();

			// O campo localidadeOrigem ser� obrigat�rio
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				codigoSetorComercial = (String) gerarRelatorioAcompanhamento.getCodigoSetorComercialFinal();

				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formul�rio
					gerarRelatorioAcompanhamento.setCodigoSetorComercialFinal("");
					gerarRelatorioAcompanhamento.setSetorComercialFinal("");
					gerarRelatorioAcompanhamento.setNomeSetorComercialFinal("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					
					gerarRelatorioAcompanhamento.setCodigoSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getCodigo()));
					gerarRelatorioAcompanhamento.setSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getId()));
					gerarRelatorioAcompanhamento.setNomeSetorComercialFinal(
							objetoSetorComercial.getDescricao());
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					//httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formul�rio
				gerarRelatorioAcompanhamento.setCodigoSetorComercialFinal("");
				gerarRelatorioAcompanhamento.setNomeSetorComercialFinal("Informe a localidade da inscri��o de destino.");
				
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
			}
		}
	}
	
	
	private void pesquisarQuadra(
			String inscricaoTipo,
			GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm gerarRelatorioAcompanhamento,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que ser�o retornados pelo hibernate.
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		//QUADRA
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			gerarRelatorioAcompanhamento.setInscricaoTipo("origem");
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			codigoSetorComercial = (String) gerarRelatorioAcompanhamento.getCodigoSetorComercialInicial();
			setorComercialID = (String) gerarRelatorioAcompanhamento.getSetorComercialInicial();
			
			String idLocalidadeInicial = (String) gerarRelatorioAcompanhamento.getLocalidadeInicial();
			
			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (codigoSetorComercial != null && !codigoSetorComercial.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				quadraNM = (String) gerarRelatorioAcompanhamento.getQuadraInicial();
				
				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));
				
				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio
					gerarRelatorioAcompanhamento.setQuadraInicial("");
					gerarRelatorioAcompanhamento.setIdQuadraInicial("");
					// Mensagem de tela
					httpServletRequest.setAttribute("msgQuadraInicial", "QUADRA INEXISTENTE");
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemOrigem("Quadra inexistente.");
					httpServletRequest.setAttribute("corQuadraOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraInicial");
					
					//destino
					
					gerarRelatorioAcompanhamento.setQuadraFinal("");
					gerarRelatorioAcompanhamento.setIdQuadraFinal("");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					gerarRelatorioAcompanhamento.setQuadraInicial(
							String.valueOf(objetoQuadra.getNumeroQuadra()));
					gerarRelatorioAcompanhamento.setIdQuadraInicial(
							String.valueOf(objetoQuadra.getId()));
					gerarRelatorioAcompanhamento.setQuadraFinal(
							String.valueOf(objetoQuadra.getNumeroQuadra()));
					gerarRelatorioAcompanhamento.setIdQuadraFinal(
							String.valueOf(objetoQuadra.getId()));
					
					httpServletRequest.setAttribute("corQuadraOrigem", null);
					httpServletRequest.setAttribute("nomeCampo","loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formul�rio
				gerarRelatorioAcompanhamento.setQuadraInicial("");
				//imovelEmissaoOrdensSeletivas.setQuadraMensagemOrigem("Informe o setor comercial da inscri��o de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			}
		} else {//QUADRA FINAL
			gerarRelatorioAcompanhamento.setInscricaoTipo("destino");
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			codigoSetorComercial = (String) gerarRelatorioAcompanhamento.getCodigoSetorComercialFinal();
			setorComercialID = (String) gerarRelatorioAcompanhamento.getSetorComercialFinal();

			String idLocalidadeFinal = (String) gerarRelatorioAcompanhamento.getLocalidadeFinal();			
			
			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (codigoSetorComercial != null && !codigoSetorComercial.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !codigoSetorComercial.trim().equalsIgnoreCase("")) {
				
				quadraNM = (String) gerarRelatorioAcompanhamento.getQuadraFinal();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));
				
				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio
					gerarRelatorioAcompanhamento.setQuadraFinal("");
					gerarRelatorioAcompanhamento.setIdQuadraFinal("");
					// Mensagem de tela
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemDestino("Quadra inexistente.");
					httpServletRequest.setAttribute("msgQuadraFinal", "QUADRA INEXISTENTE");					
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					gerarRelatorioAcompanhamento.setQuadraFinal(
							String.valueOf(objetoQuadra.getNumeroQuadra()));
					gerarRelatorioAcompanhamento.setIdQuadraFinal(
							String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", null);
					//httpServletRequest.setAttribute("nomeCampo","loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				gerarRelatorioAcompanhamento.setQuadraFinal("");
				// Mensagem de tela
				//imovelEmissaoOrdensSeletivas.setQuadraMensagemDestino("Informe o setor comercial da inscri��o.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
			}
		}

	}
	
	
}