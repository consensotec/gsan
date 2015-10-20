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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Administrador
 * @date 08/07/2006
 */
public class FiltrarQuadraAction extends GcomAction {

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descri��o sobre o fluxo secund�rio>
	 * 
	 * <Identificador e nome do fluxo secund�rio>
	 * 
	 * @author Administrador
	 * @date 08/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("retornarFiltroQuadra");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarQuadraActionForm filtrarQuadraActionForm = (FiltrarQuadraActionForm) actionForm;

		FiltroQuadra filtroQuadra = new FiltroQuadra(FiltroQuadra.ID_LOCALIDADE);

		// Objetos que ser�o retornados pelo hibernate.
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

		String localidadeID = filtrarQuadraActionForm.getLocalidadeID();
		String setorComercialCD = filtrarQuadraActionForm.getSetorComercialCD();
		String quadraNM = filtrarQuadraActionForm.getQuadraNM();
		//String bairroNome = filtrarQuadraActionForm.getBairroNome();
		String idRota = filtrarQuadraActionForm.getIdRota();
		String indicadorUso = filtrarQuadraActionForm.getIndicadorUso();
		//String tipoPesquisa = filtrarQuadraActionForm.getTipoPesquisa();
		// 1 check --- null uncheck
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");
		String IndicadorAtualizacaoCadastral = filtrarQuadraActionForm.getIndicadorAtualizacaoCadastral();

		boolean peloMenosUmParametroInformado = false;

		if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {
			
			pesquisarLocalidade(filtrarQuadraActionForm,fachada,localidadeID);
			
			peloMenosUmParametroInformado = true;
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(localidadeID)));
		}

		if (setorComercialCD != null && !setorComercialCD.equalsIgnoreCase("")) {
			
			pesquisarSetorComercial(filtrarQuadraActionForm,fachada,setorComercialCD,localidadeID);
			
			peloMenosUmParametroInformado = true;
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(setorComercialCD)));
		}

		if (quadraNM != null && !quadraNM.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, new Integer(quadraNM)));
		}
		
		if (idRota != null && !idRota.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ROTA_ID, new Integer(idRota)));
		}
/*
		if (bairroNome != null && !bairroNome.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroQuadra.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroQuadra.NOME_BAIRRO, bairroNome));
			} else {
				filtroQuadra.adicionarParametro(new ComparacaoTexto(
						FiltroQuadra.NOME_BAIRRO, bairroNome));
			}
		}
*/
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")
				&& !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		if (IndicadorAtualizacaoCadastral != null && !IndicadorAtualizacaoCadastral.equalsIgnoreCase("")
				&& !IndicadorAtualizacaoCadastral.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (IndicadorAtualizacaoCadastral.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_ATUALIZACAO_CADASTRAL_ATIVO))) {
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADOR_ATUALIZACAO_CADASTRAL,
						ConstantesSistema.INDICADOR_ATUALIZACAO_CADASTRAL_ATIVO));
			} else {
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADOR_ATUALIZACAO_CADASTRAL,
						ConstantesSistema.INDICADOR_ATUALIZACAO_CADASTRAL_DESATIVO));
			}
		}
		
		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA,
				FiltroQuadra.DESCRICAO_LOCALIDADE);

		sessao.setAttribute("filtroQuadra", filtroQuadra);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);

		// devolve o mapeamento de retorno
		return retorno;
	}
	
	 private void pesquisarLocalidade(
	    		FiltrarQuadraActionForm filtrarQuadraActionForm,
	            Fachada fachada, String localidadeID) {
		 
		Collection colecaoPesquisa;
		 
        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeID));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Localidade nao encontrada
            //Limpa o campo localidadeID do formul�rio
        	filtrarQuadraActionForm.setLocalidadeID("");
        	filtrarQuadraActionForm
                    .setLocalidadeNome("Localidade inexistente.");
        	
        	throw new ActionServletException("atencao.pesquisa_inexistente",
					null,"Localidade");	

        }else {
            Localidade objetoLocalidade = (Localidade) Util
            .retonarObjetoDeColecao(colecaoPesquisa);
		    filtrarQuadraActionForm.setLocalidadeID(String
		            .valueOf(objetoLocalidade.getId()));
		    filtrarQuadraActionForm
		            .setLocalidadeNome(objetoLocalidade.getDescricao());
		
		}
	
	}

	    private void pesquisarSetorComercial(
	    		FiltrarQuadraActionForm filtrarQuadraActionForm,
	            Fachada fachada,String setorComercialCD,String localidadeID) {

			 Collection colecaoPesquisa;
	    	
	        if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
	            //Limpa os campos setorComercialCD e setorComercialID do formulario
	        	filtrarQuadraActionForm.setSetorComercialCD("");
	        	filtrarQuadraActionForm.setSetorComercialID("");
	        	filtrarQuadraActionForm
	                    .setSetorComercialNome("Informe Localidade.");
	        	  throw new ActionServletException("atencao.campo_selecionado.obrigatorio",
	    					null,"Localidade");	
	        	
	        } else {

	            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

	            filtroSetorComercial.adicionarParametro(new ParametroSimples(
	                    FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

	            filtroSetorComercial.adicionarParametro(new ParametroSimples(
	                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
	                    setorComercialCD));

	            //Retorna setorComercial
	            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
	                    SetorComercial.class.getName());

	            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                //setorComercial nao encontrado
	                //Limpa os campos setorComercialCD e setorComercialID do
	                // formulario
	            	filtrarQuadraActionForm.setSetorComercialCD("");
	                filtrarQuadraActionForm.setSetorComercialID("");
	                filtrarQuadraActionForm
	                        .setSetorComercialNome("Setor comercial inexistente.");
	                throw new ActionServletException("atencao.setor_comercial_nao_existe");	
	            } else {
	                SetorComercial objetoSetorComercial = (SetorComercial) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
			        filtrarQuadraActionForm.setSetorComercialCD(String
			                .valueOf(objetoSetorComercial.getCodigo()));
			        filtrarQuadraActionForm.setSetorComercialID(String
			                .valueOf(objetoSetorComercial.getId()));
			        filtrarQuadraActionForm
			                .setSetorComercialNome(objetoSetorComercial
			                        .getDescricao());
			
			    }

	        }
	    }

}
