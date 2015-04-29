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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action respons�vel por adicionar na cole��o a rela��o entre o cliente imovel,
 * o cliente e a data de inicio da rela��o
 * 
 * @author S�vio Luiz
 * @created 16 de Maio de 2004
 */
public class AdicionarInserirImovelClienteAction extends GcomAction {

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

        ActionForward retorno = actionMapping
                .findForward("adicionarInserirImovelCliente");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        Collection imovelClientesNovos = null;

        if (sessao.getAttribute("imovelClientesNovos") != null) {
            imovelClientesNovos = (Collection) sessao
                    .getAttribute("imovelClientesNovos");
        } else {
            imovelClientesNovos = new ArrayList();
        }

        //inst�ncia um cliente

        Cliente cliente = new Cliente();

        //teste se o cliente ja foi pesquisado com enter

        if (inserirImovelActionForm.get("idCliente") != null) {

            //recupera o id do cliente
            String idCliente = (String) inserirImovelActionForm
                    .get("idCliente");
            //inst�ncia o filtro do cliente
            FiltroCliente filtroCliente = new FiltroCliente();

            //adiciona o parametro no filtro
            filtroCliente.adicionarParametro(new ParametroSimples(
                    FiltroCliente.ID, idCliente));

            //faz a pesquisa do cliente
            Collection clientesObjs = fachada.pesquisar(filtroCliente,
                    Cliente.class.getName());

            //recupera o cliente da cole��o pesquisada
            if (!clientesObjs.isEmpty()) {
                cliente = (Cliente) clientesObjs.iterator().next();
            } else {
                throw new ActionServletException("atencao.naocadastrado", null,
                        "Cliente");
            }

        }

        //inicializa o tipo do cliente imovel
        ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();

        //recupera id do tipo do cliente imovel
        clienteRelacaoTipo.setId((Integer) inserirImovelActionForm
                .get("tipoClienteImovel"));
        //recupera a descricao do tipo do cliente imovel
        clienteRelacaoTipo.setDescricao((String) inserirImovelActionForm
                .get("textoSelecionado"));

        //inicializa o cliente imovel
        ClienteImovel clienteImovel = new ClienteImovel(new Date(), null, null,
                cliente, clienteRelacaoTipo);
        
        //Coloca a data de ultima altera��o para identificar o objeto
        clienteImovel.setUltimaAlteracao(new Date());

        if (!imovelClientesNovos.contains(clienteImovel)) {
        	
            //verifica se o tipo do cliente � usu�rio ou � respons�vel
            if ((clienteImovel.getClienteRelacaoTipo().getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO.intValue())) {
            	//verifica se ja existe cliente do tipo usu�rio
            	if(inserirImovelActionForm.get("idClienteImovelUsuario") == null || inserirImovelActionForm.get("idClienteImovelUsuario").equals("")) {
            		sessao.setAttribute("idClienteImovelUsuario", cliente.getId().toString());
                    inserirImovelActionForm.set("idClienteImovelUsuario",
                            cliente.getId().toString());
                    //adiciona o cliente imovel na cole��o de  imovelClientesNovos
                    imovelClientesNovos.add(clienteImovel);                
            	}else{	
            		throw new ActionServletException(
                            "atencao.ja_cadastradado.cliente_imovel_usuario");
                }
            } else if (clienteImovel.getClienteRelacaoTipo().getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL.intValue()) {
                if (inserirImovelActionForm
                        .get("idClienteImovelResponsavel") == null
                        || inserirImovelActionForm.get(
                                "idClienteImovelResponsavel").equals("")) {
                    inserirImovelActionForm.set("idClienteImovelResponsavel",
                            cliente.getId().toString());

                    sessao.setAttribute(
                            "idClienteImovelResponsavel", cliente.getId()
                            .toString());
                    //inserirImovelActionForm.set();
                    //adiciona o cliente imovel na cole��o de
                    // imovelClientesNovos
                    imovelClientesNovos.add(clienteImovel);	
                }else{
            		throw new ActionServletException(
                            "atencao.ja_cadastradado.cliente_imovel_responsavel");
                }
            }else{//para cliente do tipo propriet�io pode colocarq qtos quiser
            	   //adiciona o cliente imovel na cole��o de
                // imovelClientesNovos
                imovelClientesNovos.add(clienteImovel);            	
            }

            inserirImovelActionForm.set("idCliente", null);
            inserirImovelActionForm.set("nomeCliente", null);

            //manda para a sess�o a cole��o de imovelClienteNovos
            sessao.setAttribute("imovelClientesNovos", imovelClientesNovos);

        } else {//cliente e tipo ja informados
            throw new ActionServletException(
                    "atencao.ja_cadastradado.cliente_imovel");
        }

        return retorno;
    }

}
