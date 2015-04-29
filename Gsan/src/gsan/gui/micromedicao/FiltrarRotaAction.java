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
package gsan.gui.micromedicao;

import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro da rota de acordo com os par�metros informados
 * 
 * @author Vivianne Sousa
 * @created 28/03/2006
 */
public class FiltrarRotaAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclus�o de uma nova rota
	 * 
	 * [UC0129] Filtrar Rota
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 28/03/2006
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


        ActionForward retorno = actionMapping.findForward("retornarFiltroRota");
        RotaActionForm rotaActionForm = (RotaActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        Boolean peloMenosUmParametroInformado = false;
        
        String idLocalidade = rotaActionForm.getIdLocalidade();
		String codigoSetorComercial = rotaActionForm.getCodigoSetorComercial();
		String codigoRota = rotaActionForm.getCodigoRota();
		String idFaturamentoGrupo = rotaActionForm.getFaturamentoGrupo(); 			
		String idEmpresaLeituristica = rotaActionForm.getEmpresaLeituristica();
		String idEmpresaCobranca = rotaActionForm.getEmpresaCobranca();
		String idGrupoCobranca = rotaActionForm.getIdGrupoCobranca();
		String indicadorUso = rotaActionForm.getIndicadorUso();
		String indicadorRotaAlternativa = rotaActionForm.getIndicadorRotaAlternativa();
		String empresaEntregaContas = rotaActionForm.getEmpresaEntregaContas();
		String indicadorTransmissaoOffline = rotaActionForm.getIndicadorTransmissaoOffline();
		// 1 check   --- null uncheck 
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		

		validacaoFinal( codigoRota,idLocalidade, codigoSetorComercial,fachada, httpServletRequest);
		
		FiltroRota filtroRota = new FiltroRota();
		if (codigoRota != null && !codigoRota.equals("")){
  
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.CODIGO_ROTA,new Integer(codigoRota).intValue())); 
	
		// Retorna caso j�  exista uma rota com o c�digo  informado 
		Collection colecaoPesquisa =  null;
		
		colecaoPesquisa = fachada.pesquisar(filtroRota,
			Rota.class.getName());
		
		if ((colecaoPesquisa == null) ||( colecaoPesquisa.isEmpty())) { 
			  //Verificar exist�ncia da rota 
			  throw new ActionServletException(
		  "atencao.rota_inexistente"); 
			  }
		}		
		
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("empresa");
		
		if ((idLocalidade != null)&& (!idLocalidade.trim().equals(""))) {
			filtroRota.adicionarParametro(new ParametroSimples(
			FiltroRota.LOCALIDADE_ID,idLocalidade));
			peloMenosUmParametroInformado = true;
		}
		
		if ((codigoSetorComercial != null)&& (!codigoSetorComercial.trim().equals(""))) {
			
			//Filtro para descobrir id do setor comercial
        	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

        	if (idLocalidade != null && !idLocalidade.equalsIgnoreCase("")){
        		filtroSetorComercial.adicionarParametro(new ParametroSimples(
    					FiltroSetorComercial.ID_LOCALIDADE, new Integer(
    							idLocalidade))); 
        	}
        	
    		filtroSetorComercial.adicionarParametro(new ParametroSimples(
    				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
    						codigoSetorComercial)));

    		Collection<SetorComercial> colectionSetorComerciais = fachada.pesquisar(
    				filtroSetorComercial, SetorComercial.class.getName());
        	
    		if (colectionSetorComerciais != null && !colectionSetorComerciais.isEmpty()) {
        		Integer idSetorComercial =  colectionSetorComerciais
    			.iterator().next().getId();
        				
    			filtroRota.adicionarParametro(new ParametroSimples(
    			FiltroRota.SETOR_COMERCIAL_ID,idSetorComercial));
    			
    			peloMenosUmParametroInformado = true;
    		}
		}
		

		if ((codigoRota != null)&& (!codigoRota.trim().equals(""))) {
			filtroRota.adicionarParametro(new ParametroSimples(
			FiltroRota.CODIGO_ROTA,codigoRota));
			peloMenosUmParametroInformado = true;
		}

		if ((idFaturamentoGrupo != null)&& (!idFaturamentoGrupo.equals(""
				+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			filtroRota.adicionarParametro(new ParametroSimples(
			FiltroRota.FATURAMENTO_GRUPO_ID,idFaturamentoGrupo));
			peloMenosUmParametroInformado = true;
		}
		
		if (idEmpresaLeituristica != null && 
			!idEmpresaLeituristica.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.EMPRESA_ID, idEmpresaLeituristica));
			peloMenosUmParametroInformado = true;
		}
		
		if(idGrupoCobranca != null && 
				!idGrupoCobranca.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.COBRANCA_GRUPO, idGrupoCobranca));
		}
		
		if (empresaEntregaContas != null && 
				!empresaEntregaContas.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.EMPRESA_ENTREGA_CONTAS, empresaEntregaContas));
				peloMenosUmParametroInformado = true;
		}
		
		if (idEmpresaCobranca != null &&
			!idEmpresaCobranca.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.EMPRESA_COBRANCA_ID, idEmpresaCobranca));
			peloMenosUmParametroInformado = true;
		}
				
		if ((indicadorUso != null && 
				!indicadorUso.equals(""	+ ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (!indicadorUso.equals("3"))) {

				filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.INDICADOR_USO,indicadorUso));	

			peloMenosUmParametroInformado = true;
		}
		
		if(indicadorRotaAlternativa != null){
			filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.INDICADOR_ROTA_ALTERNATIVA,indicadorRotaAlternativa));
			
			peloMenosUmParametroInformado = true;
		}
		
		if(indicadorTransmissaoOffline != null && !indicadorTransmissaoOffline.equals("")){
			filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.INDICADOR_TRANSMISSAO_OFFLINE,indicadorTransmissaoOffline));
			
			peloMenosUmParametroInformado = true;
		}


		//Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroRota",filtroRota);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade,
			String codigoDigitadoEnterSetorComercial,
			Fachada fachada,HttpServletRequest httpServletRequest) {

	  //Verifica se o c�digo do Setor Comercial foi digitado
	  if ((codigoDigitadoEnterSetorComercial != null &&
		!codigoDigitadoEnterSetorComercial.toString().trim().equalsIgnoreCase(""))) {
	
		String msg = "";
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idDigitadoEnterLocalidade != null
			&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
			
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.ID_LOCALIDADE, new Integer(
			idDigitadoEnterLocalidade)));
			msg = "atencao.setor_comercial_nao_existe";
		}else{
			msg = "atencao.setor_comercial.inexistente";
		}
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
		FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
		codigoDigitadoEnterSetorComercial)));
		
		Collection<SetorComercial> setorComerciais = fachada.pesquisar(
		filtroSetorComercial, SetorComercial.class.getName());
		
		
		if (setorComerciais == null || setorComerciais.isEmpty()) {
			//o setor comercial n�o foi encontrado
			//Setor Comercial n�o existe para esta localidade
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException(
			msg);				
		
		}
	
	  }
	
	}
    
    private void validacaoFinal(String codigoRota,
    		String idLocalidade,String codigoSetorComercial,
			Fachada fachada,HttpServletRequest httpServletRequest){
    	
    	if (idLocalidade != null &&
    			!idLocalidade.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idLocalidade)){
			//Localidade n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade ");		
		}
    	
    	if(codigoSetorComercial != null &&
    			!codigoSetorComercial.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(codigoSetorComercial)){
			//Setor Comercial n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Setor Comercial ");		
		}else{
			verificaExistenciaCodSetorComercial(idLocalidade,
					codigoSetorComercial,fachada,httpServletRequest);
		}
    	
    	if (codigoRota != null &&
    			!codigoRota.equalsIgnoreCase("") &&
    			Util.validarValorNaoNumerico(codigoRota)){
			//C�digo da Rota n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","codigoRota");
			throw new ActionServletException("atencao.nao.numerico",
					null,"C�digo da Rota ");		
		}
    	
    }

}
 