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
package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarComandoAtividadeFaturamentoDadosAction extends GcomAction {

    /**
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("");

        //Carrega a instancia da fachada
       // Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Inst�ncia do formul�rio que est� sendo utilizado
        InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = (InserirComandoAtividadeFaturamentoActionForm) actionForm;

        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) sessao
                .getAttribute("faturamentoAtividadeCronograma");

        // Para auxiliar na formata��o de uma data
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        // Data corrente para compara��o
        // =============================
        String dataCorrente = null;
        Date dataCorrenteGrupo = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("dataCorrente") != null) {

            dataCorrente = (String) sessao.getAttribute("dataCorrente");
            if (dataCorrente != null && !dataCorrente.equalsIgnoreCase("")) {

                try {
                    dataCorrenteGrupo = formatoData.parse(dataCorrente);
                } catch (ParseException ex) {
                    dataCorrenteGrupo = null;
                }
            }
        }
        
        
        //Data de vecimento do grupo para compara��o
        // =========================================
        String dataVencimentoGrupoBase = null;
      //  Date dataVencimentoGrupoBaseObjeto = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {

        	dataVencimentoGrupoBase = (String) sessao.getAttribute("exibirCampoVencimentoGrupo");
            if (dataVencimentoGrupoBase != null && !dataVencimentoGrupoBase.equalsIgnoreCase("")) {

              /*  try {
                	dataVencimentoGrupoBaseObjeto = formatoData.parse(dataVencimentoGrupoBase);
                } catch (ParseException ex) {
                	dataVencimentoGrupoBaseObjeto = null;
                }*/
            }
        }

        
        // Data de vecimento do grupo informado pelo usu�rio (JSP)
        // ========================================================
        String dataVencimentoGrupoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getVencimentoGrupo();

        Date dataVencimentoGrupo = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("dataCorrente") != null) {

            if (dataVencimentoGrupoJSP != null
                    && !dataVencimentoGrupoJSP.equalsIgnoreCase("")) {

                try {
                    dataVencimentoGrupo = formatoData
                            .parse(dataVencimentoGrupoJSP);

                    String mesDataVencimentoGrupo = dataVencimentoGrupoJSP
                            .substring(3, 5);
                    String anoDataVencimentoGrupo = dataVencimentoGrupoJSP
                            .substring(6, 10);

                    String mesDataVencimentoGrupoBase = dataVencimentoGrupoBase.substring(3, 5);
                    String anoDataVencimentoGrupoBase = dataVencimentoGrupoBase.substring(6, 10);

                    if (dataCorrenteGrupo.after(dataVencimentoGrupo)) {
                        throw new ActionServletException(
                                "atencao.faturamento_data_vencimento_grupo_menor",
                                null, dataCorrente);
                    } else if ((!mesDataVencimentoGrupo
                            .equalsIgnoreCase(mesDataVencimentoGrupoBase))
                            || (!anoDataVencimentoGrupo
                                    .equalsIgnoreCase(anoDataVencimentoGrupoBase))) {
                        throw new ActionServletException(
                                "atencao.faturamento_data_vencimento_mes_ano_diferente");
                    }
                } catch (ParseException ex) {
                	throw new ActionServletException(
                    "atencao.data.invalida");
                }
            }
        }

        return retorno;
    }
}
