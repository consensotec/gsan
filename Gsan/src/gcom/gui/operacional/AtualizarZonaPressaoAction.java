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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.ZonaPressao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarZonaPressaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando for implementado o esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarZonaPressaoActionForm atualizarZonaPressaoActionForm = (AtualizarZonaPressaoActionForm) actionForm;

		ZonaPressao zonaPressao = (ZonaPressao) sessao.getAttribute("atualizarZonaPressao");
		
		Collection colecaoPesquisa = null;
		
        String descricaoZonaPressao = atualizarZonaPressaoActionForm.getDescricao();
        String descricaoAbreviadaZonaPressao = atualizarZonaPressaoActionForm.getDescricaoAbreviada();    
        String indicadordeUso = atualizarZonaPressaoActionForm.getIndicadorUso();
        String distritoOperacionalID = atualizarZonaPressaoActionForm.getDistritoOperacionalID();
        
        //Distrito Operacional � obrigat�rio.

        if (distritoOperacionalID == null || distritoOperacionalID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Distrito Operacional");
        }
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.ID, distritoOperacionalID));

		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.INDICADORUSO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna distrito Operacional
		colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
				DistritoOperacional.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.distrito_operacional_inexistente");
		}

        
    zonaPressao.setDescricaoZonaPressao(atualizarZonaPressaoActionForm.getDescricao());
	zonaPressao.setDescricaoAbreviada(atualizarZonaPressaoActionForm.getDescricaoAbreviada());
	zonaPressao.setIndicadorUso(new Short (atualizarZonaPressaoActionForm.getIndicadorUso()));
	
    DistritoOperacional distritoOperacional = new DistritoOperacional();
    	distritoOperacional.setId(new Integer(atualizarZonaPressaoActionForm.getDistritoOperacionalID()));
    zonaPressao.setDistritoOperacional(distritoOperacional);
    
    zonaPressao.setDescricaoZonaPressao(descricaoZonaPressao);
        
	// Caso n�o tenha sido preenchida a Descri��o Abreviada, no banco ficar� null
    if(atualizarZonaPressaoActionForm.getDescricaoAbreviada() != null 
			&& !atualizarZonaPressaoActionForm.getDescricaoAbreviada().equals("")){
        
		zonaPressao.setDescricaoAbreviada(descricaoAbreviadaZonaPressao);
	
	} else {
	
		zonaPressao.setDescricaoAbreviada(null);
	
	}
        
   	// Seta a data da altera��o
    zonaPressao.setUltimaAlteracao( new Date() );	
    
    // Seta o Indicador de Uso
    zonaPressao.setIndicadorUso( new Short(indicadordeUso));
	
	fachada.atualizar(zonaPressao);

	montarPaginaSucesso(httpServletRequest, "Zona de Press�o "
			+ atualizarZonaPressaoActionForm.getId().toString() + " atualizado com sucesso.",
			"Realizar outra Manuten��o de Zona de Press�o ",
			"exibirFiltrarZonaPressaoAction.do?menu=sim");        
    
	return retorno;
	}
}
