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

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author rodrigo
 */
public class InserirTarifaSocialImovelAction extends GcomAction {

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
                .findForward("gerenciadorProcesso");

        HttpSession sessao = httpServletRequest.getSession(false);

        //Instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        DynaValidatorForm inserirTarifaSocialActionForm = (DynaValidatorForm) actionForm;

        String idImovel = (String) inserirTarifaSocialActionForm
                .get("idImovel");

        if (idImovel != null && !idImovel.equals("")) {

            //Filtro para pesquisa dos im�veis
            FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

            //Filtro para pesquisa de subcategorias para calculo de economias
            //FiltroImovelSubCategoria filtroImovelSubcategoria = new
            // FiltroImovelSubCategoria();

            //Adiciona os parametros para pesquisa de imovel
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.IMOVEL_ID, idImovel));
            filtroClienteImovel.setInitializeLazy(true);
            //Objetos que ser�o retornados pelo Hibernate
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");

            //Adicionar os parametros para pesquisa de economias
            //filtroImovelSubcategoria.adicionarParametro(new
            // ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID, idImovel));

            //filtroImovelSubcategoria.adicionarColecaoParaSerCarregada("imovelEconomias");

            //Pesquisa e monta uma cole��o de cliente im�veis
            Collection clienteImoveis = fachada.pesquisar(filtroClienteImovel,
                    ClienteImovel.class.getName());

            //Pesquisa e monta uma cole��o de im�veis
            //Collection imovelSubcategorias =
            // fachada.pesquisar(filtroImovelSubcategoria,
            // ImovelSubcategoria.class.getName());

            ClienteImovel clienteImovelObj = null;

            //ImovelSubcategoria imovelSubcategoriaObj = new
            // ImovelSubcategoria();
            //Collection imovelEconomias = null;

            //Verifica se o resultado da busca de im�vel foi vazia
            if (clienteImoveis == null || clienteImoveis.isEmpty()) {

                throw new ActionServletException(
                        "atencao.pesquisa.nenhumresultado", null, "imovel");
            } else {
                //Verifica se o im�vel j� est� com a tarifa social

                clienteImovelObj = (ClienteImovel) clienteImoveis.iterator()
                        .next();

                if (clienteImovelObj.getImovel().getImovelPerfil() != null &&
                	clienteImovelObj.getImovel().getImovelPerfil().getId()
                    .equals(ImovelPerfil.TARIFA_SOCIAL)) {

                    throw new ActionServletException("atencao.imovel.ja_tarifasocial");
                }
            }

            //Obter quantidade de economias do im�vel para determinar qual o
            // pr�ximo caso de uso
            //ser� executado
            //[UC0065 - Inserir dados tarifa social - Uma economia]
            //[UC0066 - Inserir dados tarifa social - Mais de uma economia]
            Imovel imovel = new Imovel();

            imovel.setId(new Integer(idImovel));

            int quantidadeEconomiasImovel = fachada
                    .obterQuantidadeEconomias(imovel);

            if (!(quantidadeEconomiasImovel > 0)) {
                //O imovel n�o possui nenhuma economia
                throw new ActionServletException(
                        "atencao.imovel.economias_nao_cadastradas");
            }

            //manda o clienteimovel para sessao para JSP carregar seus dados
            //sessao.setAttribute("clienteImovel", clienteImovelObj);

            //manda a cole��o de Imovel Economia para sess�o
            //sessao.setAttribute("colecaoImovelEconomia", imovelEconomias);
            // observar isso, ta indo so o ultimo

            //manda a cole��o de Imovel Subcategoria para sess�o
            //sessao.setAttribute("colecaoImovelSubcategoria",
            // imovelSubcategorias);

            //atualiza a quantidade de economias do ActionForm
            inserirTarifaSocialActionForm.set("qtdEconomia", new Integer(
                    quantidadeEconomiasImovel));
            
            //Coloca o imovel na sessao
            sessao.setAttribute("imovelTarifa", clienteImovelObj.getImovel());

        }

        return retorno;
    }
}
