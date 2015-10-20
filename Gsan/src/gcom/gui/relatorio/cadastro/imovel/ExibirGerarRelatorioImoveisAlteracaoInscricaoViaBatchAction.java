/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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

package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1109] Filtrar Dados para Gera��o Boletim de Custo de Repavimenta��o
 * 
 * @author Hugo Leonardo
 *
 * @date 30/12/2010
 */

public class ExibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction extends GcomAction {
	
	private String localidadeID = null;
	private String setorComercialCD = null;
	private String setorComercialID = null;
	private Collection colecaoPesquisa = null;
	private String quadraNM = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatch");
		
		GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form = 
			(GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		String inscricaoTipo = (String) httpServletRequest
			.getParameter("inscricaoTipo");
		
		String objetoConsulta = (String) httpServletRequest
			.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") && 
			inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				this.pesquisarLocalidade(inscricaoTipo, form, fachada,	httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				this.pesquisarLocalidade(inscricaoTipo, form, fachada, httpServletRequest);

				this.pesquisarSetorComercial(inscricaoTipo, form, fachada, httpServletRequest);

				break;
			// Quadra
			case 3:

				this.pesquisarLocalidade(inscricaoTipo, form, fachada, httpServletRequest);

				this.pesquisarSetorComercial(inscricaoTipo, form, fachada, httpServletRequest);

				this.pesquisarQuadra(inscricaoTipo, form, fachada, httpServletRequest);

				break;
			default:
				break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisar Localidade 
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2011
	 */
	private void pesquisarLocalidade(String inscricaoTipo, 
			GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
	
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) form.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formul�rio
				form.setLocalidadeOrigemID("");
				form.setNomeLocalidadeOrigem("Localidade Inexistente");
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("");
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeOrigemID");
			} 
			else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
				//destino
				form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) form.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formul�rio
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeDestinoID");
			} 
			else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
			}
		}
	}
	
	/**
	 * Pesquisar Setor Comercial 
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2011
	 */
	private void pesquisarSetorComercial(String inscricaoTipo,
			GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) form.getLocalidadeOrigemID();

			// O campo localidadeOrigemID ser� obrigat�rio
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formul�rio
					form.setSetorComercialOrigemCD("");
					form.setSetorComercialOrigemID("");
					form.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
					//destino
					form.setSetorComercialDestinoCD("");
					form.setSetorComercialDestinoID("");
				} 
				else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					form.setSetorComercialOrigemCD(String.valueOf(objetoSetorComercial.getCodigo()));
					form.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
					form.setNomeSetorComercialOrigem(objetoSetorComercial.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
					
					//setorComercialDestino
					form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
					form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
					//setorComercialDestino					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				form.setSetorComercialOrigemCD("");
				form.setNomeSetorComercialOrigem("Informe a localidade da inscri��o de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
		} else {
			
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) form.getLocalidadeDestinoID();

			// O campo localidadeOrigem ser� obrigat�rio
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formul�rio
					form.setSetorComercialDestinoCD("");
					form.setSetorComercialDestinoID("");
					form.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
				} 
				else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
					form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formul�rio
				form.setSetorComercialDestinoCD("");
				form.setNomeSetorComercialDestino("Informe a localidade da inscri��o de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
			}
		}
	}
	
	/**
	 * Pesquisar Quadra
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2011
	 */
	private void pesquisarQuadra(String inscricaoTipo,
			GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		//QUADRA
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
	
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) form.getSetorComercialOrigemCD();
			
			setorComercialID = (String) form.getSetorComercialOrigemID();

			String idLocalidadeInicial = (String) form.getLocalidadeOrigemID();
			
			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") && 
				setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraOrigemNM();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer( idLocalidadeInicial)));
				
				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples( FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio
					form.setQuadraOrigemNM("");
					form.setQuadraOrigemID("");
					// Mensagem de tela
					httpServletRequest.setAttribute("msgQuadraInicial", "QUADRA INEXISTENTE");
					httpServletRequest.setAttribute("corQuadraOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
					
					//destino
					form.setQuadraDestinoNM("");
					form.setQuadraDestinoID("");
				} 
				else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraOrigemNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraOrigemID(String.valueOf(objetoQuadra.getId()));
										
					form.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
	
					httpServletRequest.setAttribute("corQuadraOrigem", null);
					httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
				}
			} 
			else {
				// Limpa o campo quadraOrigemNM do formul�rio
				form.setQuadraOrigemNM("");
				form.setQuadraMensagemOrigem("Informe o setor comercial da inscri��o de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			}
		} else {
			//QUADRA FINAL
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) form.getSetorComercialDestinoCD();
			setorComercialID = (String) form.getSetorComercialDestinoID();

			String idLocalidadeFinal = (String) form.getLocalidadeDestinoID();			
			
			// Os campos setorComercialOrigemCD e setorComercialID ser�o obrigat�rios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") && 
				setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraDestinoNM();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer( idLocalidadeFinal)));
				
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
					form.setQuadraDestinoNM("");
					form.setQuadraDestinoID("");
					// Mensagem de tela
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemDestino("Quadra inexistente.");
					httpServletRequest.setAttribute("msgQuadraFinal", "QUADRA INEXISTENTE");					
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", null);
					httpServletRequest.setAttribute("nomeCampo","loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				form.setQuadraDestinoNM("");
				// Mensagem de tela
				form.setQuadraMensagemDestino("Informe o setor comercial da inscri��o.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
			}
		}
	}
	
}
