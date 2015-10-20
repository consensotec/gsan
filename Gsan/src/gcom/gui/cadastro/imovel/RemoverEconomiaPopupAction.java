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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que remove a o objeto selecionado de cliente imovel economia que est�
 * sem o imovel economia
 * 
 * @author S�vio Luiz
 * @created 20 de Maio de 2004
 */
public class RemoverEconomiaPopupAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obt�m o action form
        EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

        ActionForward retorno = null;

        //verifica se o bot�o do form � diferente de vazio
        if (economiaPopupActionForm.getBotaoAdicionar() != null
                && !economiaPopupActionForm.getBotaoAdicionar().equals("")) {
            //verifica se o bot�o tem o valor atualizar
            //se tiver volta para o popup atualizar
            if (economiaPopupActionForm.getBotaoAdicionar().equals("atualizar") ||
                economiaPopupActionForm.getBotaoAdicionar().equals("ATUALIZAR")) {
                retorno = actionMapping.findForward("atualizarEconomiaPopup");
            }
            //verifica se o bot�o tem o valor inserir
            //se tiver volta para o popup inserir
            if (economiaPopupActionForm.getBotaoAdicionar().equals("inserir") ||
                economiaPopupActionForm.getBotaoAdicionar().equals("INSERIR")) {
                retorno = actionMapping.findForward("inserirEconomiaPopup");
            }
        }

      //  Fachada fachada = Fachada.getInstancia();

        //Cria variaveis
        Collection colecaoClientesImoveisEconomia = (Collection) sessao
                .getAttribute("colecaoClientesImoveisEconomia");
                        
        Collection colecaoClientesImoveisEconomiaRemovidas = null;
        
        if (sessao.getAttribute("colecaoClientesImoveisEconomiaRemovidas")!= null){
        	colecaoClientesImoveisEconomiaRemovidas = (Collection) 
        		sessao.getAttribute("colecaoClientesImoveisEconomiaRemovidas");
        }else{
        	colecaoClientesImoveisEconomiaRemovidas = new ArrayList();
        }

        //Cria variaveis
        ImovelEconomia imovelEconomia = (ImovelEconomia) sessao
                .getAttribute("imovelEconomia");

        //atribui os valores q vem pelo request as variaveis
        String[] clientesImoveis = economiaPopupActionForm
                .getIdRegistrosRemocao();

        //instancia cliente
        ClienteImovelEconomia clienteImovelEconomia = null;

        Collection colecaoClientesImoveisEconomiaFimRelacao = new ArrayList();

        if (imovelEconomia != null && !imovelEconomia.equals("")) {
            Collection clienteImovelEconomias = (Collection) imovelEconomia
                    .getClienteImovelEconomias();
            Iterator clienteImovelEconomiaIterator = clienteImovelEconomias
                    .iterator();

            while (clienteImovelEconomiaIterator.hasNext()) {
                clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator
                        .next();
                for (int i = 0; i < clientesImoveis.length; i++) {
                    if (clienteImovelEconomia.getUltimaAlteracao().getTime() == Long
                            .parseLong(clientesImoveis[i])) {

                        if (clienteImovelEconomia.getId() != null
                                && !clienteImovelEconomia.getId().equals("")) {
                            //caso seja um cliente im�vel economia da base
                            // ent�o vai para o
                            //exibirManterFimRelacaoClienteImovel para colocar
                            // o motivo do fim da rela��o
                            retorno = actionMapping
                                    .findForward("exibirManterFimRelacaoClienteImovel");
                            colecaoClientesImoveisEconomiaFimRelacao
                                    .add(clienteImovelEconomia);

                            if (clienteImovelEconomia.getClienteRelacaoTipo()
                                    .getId().shortValue() == ClienteRelacaoTipo.USUARIO
                                    .shortValue()) {
                                economiaPopupActionForm
                                        .setIdClienteImovelUsuario(null);
                            }

                            colecaoClientesImoveisEconomiaRemovidas.add(clienteImovelEconomia);
                            
                            //clienteImovelEconomiaIterator.remove();
                            
                            sessao.setAttribute("colecaoClientesImoveisEconomiaFimRelacao",
                            		colecaoClientesImoveisEconomiaFimRelacao);
                            
                            sessao.setAttribute("colecaoClientesImoveisEconomiaRemovidas",colecaoClientesImoveisEconomiaRemovidas);
                            
                        } else {

                            clienteImovelEconomiaIterator.remove();

                            if (clienteImovelEconomia.getClienteRelacaoTipo()
                                    .getId().shortValue() == ClienteRelacaoTipo.USUARIO
                                    .shortValue()) {
                                economiaPopupActionForm
                                        .setIdClienteImovelUsuario(null);
                            }

                        }
                    }
                }
            }

            sessao.setAttribute("imovelEconomia", imovelEconomia);

        }

        if (colecaoClientesImoveisEconomia != null
                && !colecaoClientesImoveisEconomia.equals("")) {

            Iterator clienteImovelEconomiaIterator = colecaoClientesImoveisEconomia
                    .iterator();

            while (clienteImovelEconomiaIterator.hasNext()) {
                clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator
                        .next();
                for (int i = 0; i < clientesImoveis.length; i++) {
                    if (clienteImovelEconomia.getUltimaAlteracao().getTime() == Long
                            .parseLong(clientesImoveis[i])) {
                        clienteImovelEconomiaIterator.remove();

                        if (clienteImovelEconomia.getClienteRelacaoTipo()
                                .getId().shortValue() == ClienteRelacaoTipo.USUARIO
                                .shortValue()) {
                            economiaPopupActionForm
                                    .setIdClienteImovelUsuario(null);
                        }
                    }
                }
            }

        }
        if (!colecaoClientesImoveisEconomia.isEmpty()){
			economiaPopupActionForm.setColecaoCliente("SIM");
		} else {
			economiaPopupActionForm.setColecaoCliente(null);
		}

        return retorno;
    }

}
