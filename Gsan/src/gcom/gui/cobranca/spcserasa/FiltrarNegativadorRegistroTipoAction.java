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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
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
 * Realiza o filtro do Tipo do Registro do Negativador de acordo com os par�metros informados
 * 
 * @author Yara Taciane de Souza
 * @created 07/01/2008
 */
public class FiltrarNegativadorRegistroTipoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Tipo do Registro do Negativador
	 * 
	 * [UC0667] Filtrar Tipo do Registro do Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 07/01/2007
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


        ActionForward retorno = actionMapping.findForward("retornarFiltroNegativadorRegistroTipo");
        
        FiltrarNegativadorRegistroTipoActionForm filtrarNegativadorRegistroTipoActionForm = (FiltrarNegativadorRegistroTipoActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
    	Fachada fachada = Fachada.getInstancia();
        
        //Fachada fachada = Fachada.getInstancia();
        
        Boolean peloMenosUmParametroInformado = false;
        
   //-------------------------------------------------------------------------
        String idNegativador = null;
		if (filtrarNegativadorRegistroTipoActionForm.getIdNegativador() != null
				&& !filtrarNegativadorRegistroTipoActionForm
						.getIdNegativador().equals("-1")) {			
			idNegativador=filtrarNegativadorRegistroTipoActionForm.getIdNegativador();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}
     
        String codigoRegistro = null;
        if(!"".equals(filtrarNegativadorRegistroTipoActionForm.getCodigoRegistro())){
        	codigoRegistro = filtrarNegativadorRegistroTipoActionForm.getCodigoRegistro().toUpperCase(); 
        }
        String descricaoRegistroTipo = null;
        if(!"".equals(filtrarNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo())){
        	descricaoRegistroTipo = filtrarNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo().toUpperCase(); 
        } 
		// 1 check   --- null uncheck 
		String indicadorAtualizar = filtrarNegativadorRegistroTipoActionForm.getIndicadorAtualizar();

		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo();
		
		if (idNegativador != null && !idNegativador.equals("-1")){
			filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.NEGATIVADOR_ID, idNegativador));
			peloMenosUmParametroInformado = true;	

		}
		
		
		if (codigoRegistro != null && !codigoRegistro.equalsIgnoreCase("")){				

			FiltroNegativadorRegistroTipo filtroNegativadorRegTipo = new FiltroNegativadorRegistroTipo();

			filtroNegativadorRegTipo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRegistroTipo.CODIGO_REGISTRO, codigoRegistro));
			
			Collection collNegativadorRegistroTipo= fachada.pesquisar(filtroNegativadorRegTipo,NegativadorRegistroTipo.class.getName());
			
			//trocar a mensagem
			if(Util.isVazioOrNulo(collNegativadorRegistroTipo)){		
				throw new ActionServletException("atencao.codigo_tipo_registro_inexistente");
				
			}
			
			filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.CODIGO_REGISTRO, codigoRegistro));
			peloMenosUmParametroInformado = true;	

		}

		if (descricaoRegistroTipo != null && !descricaoRegistroTipo.equalsIgnoreCase("")){
			filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.DESCRICAO_REGISTRO_TIPO, descricaoRegistroTipo));
			peloMenosUmParametroInformado = true;	

		}
			

		//[FS0003] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		
        //Exibe na Tela o nome do Cliente Negativador
        FiltroNegativador filtroNegativador = new FiltroNegativador();
        filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.ID,idNegativador));
        
        filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
        Collection collNegativador = fachada.pesquisar(filtroNegativador, Negativador.class.getName());
		
		
		// Recupera da cole��o o Negativador que vai ser atualizado
		Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(collNegativador);		
		
		if(negativador != null){		
	
			sessao.setAttribute("negativador", negativador);
			
		}
		
		sessao.setAttribute("filtroNegativadorRegistroTipo",filtroNegativadorRegistroTipo);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    

}
 