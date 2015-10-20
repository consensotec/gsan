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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarTarifaSocialCartaoTipoAction extends GcomAction {
    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Obt�m o action form
        TarifaSocialCartaoTipoActionForm tarifaSocialCartaoTipoActionForm = (TarifaSocialCartaoTipoActionForm) actionForm;

        //Define a a��o de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obt�m a fachada
        Fachada fachada = Fachada.getInstancia();

        TarifaSocialCartaoTipo tarifaSocialCartaoTipo = (TarifaSocialCartaoTipo) sessao
                .getAttribute("tarifaSocialCartaoTipo");

        //Obt�m a descri��o
        String descricao = (String) tarifaSocialCartaoTipoActionForm
                .getDescricao();
        //Obt�m a descri��o abreviada
        String descricaoAbreviada = (String) tarifaSocialCartaoTipoActionForm
                .getDescricaoAbreviada();
        //Obt�m o indicador de exist�ncia de validade
        String validade = (String) tarifaSocialCartaoTipoActionForm
                .getValidade();

        //Obt�m e converte o indicador de uso
        Short indicadorDeUso = new Short(tarifaSocialCartaoTipoActionForm
                .getIndicadorUso());

        Short numeroMaximoMeses = null;

        if (!tarifaSocialCartaoTipoActionForm.getNumeroMaximoMeses().equals("")) {
            numeroMaximoMeses = new Short(tarifaSocialCartaoTipoActionForm
                    .getNumeroMaximoMeses());
            
            if (numeroMaximoMeses.intValue() == 0
					|| numeroMaximoMeses.intValue() > 99) {
				throw new ActionServletException(
						"atencao.numero.meses.fora.faixa.permitido");
			}
            
        }

        //Seta os campos para serem atualizados
        tarifaSocialCartaoTipo.setDescricao(descricao);
        tarifaSocialCartaoTipo.setDescricaoAbreviada(descricaoAbreviada);
        tarifaSocialCartaoTipo.setIndicadorValidade(new Short(validade));
        tarifaSocialCartaoTipo.setNumeroMesesAdesao(numeroMaximoMeses);
        tarifaSocialCartaoTipo.setIndicadorUso(indicadorDeUso);

        fachada.atualizarTarifaSocialCartaoTipo(tarifaSocialCartaoTipo);

        montarPaginaSucesso(httpServletRequest,
                "Tipo de Cart�o da Tarifa Social de c�digo  "
                        + tarifaSocialCartaoTipo.getId()
                        + " atualizado com sucesso.",
                "Realizar outra Manuten��o de Tipo de Cart�o da Tarifa Social",
                "filtrarTarifaSocialCartaoTipoAction.do?menu=sim");

        sessao.removeAttribute("TarifaSocialCartaoTipoActionForm");

        return retorno;
    }
}
