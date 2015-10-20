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
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelEconomia;
import gcom.cadastro.imovel.ImovelEconomia;
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


/**
 * Action ExibirImovelEconomiaRelacaoClienteImovelAction
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ExibirImovelEconomiaRelacaoClienteImovelAction extends GcomAction {

    /**
     * M�todo responsavel por responder a requisicao
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
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibir");

        ConsultarRelacaoClienteImovelActionForm form = (ConsultarRelacaoClienteImovelActionForm) actionForm;

        HttpSession sessao = request.getSession(false);
        sessao.removeAttribute("imovel");
        sessao.removeAttribute("collClienteImovel");
        sessao.removeAttribute("collImovelSubCategoriaHelper");
        sessao.removeAttribute("cliente");
        sessao.removeAttribute("collClienteImovelEconomia");

        if (form.getIdImovelEconomia() == null || "".equals(form.getIdImovelEconomia())) {
	    	throw new ActionServletException("erro.parametro.nao.informado", null, "idImovelEconomia");
	    }
        
        FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
        filtroImovelEconomia.adicionarParametro( new ParametroSimples(FiltroImovelEconomia.ID, form.getIdImovelEconomia()));
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL_CATEGORIA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL_SUB_CATEGORIA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.AREA_CONSTRUIDA_FAIXA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOCALIDADE);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.SETOR_COMERCIAL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.QUADRA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TIPO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TITULO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.BAIRRO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.SETOR_COMERCIAL_MUNICIPIO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.SETOR_COMERCIAL_MUNICIPIO_UF);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.MUNICIPIO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.CEP);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.UNIDADE_FEDERACAO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LIGACAO_AGUA_SITUACAO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.ENDERECO_REFERENCIA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_INICIAL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TITULO_PERIMETRO_INICIAL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_FINAL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TITULO_PERIMETRO_FINAL);
        
        Collection coll = Fachada.getInstancia().pesquisar(filtroImovelEconomia,ImovelEconomia.class.getSimpleName());
        if (coll != null && !coll.isEmpty()) {
        	ImovelEconomia imovelEconomia = (ImovelEconomia) coll.iterator().next();
        	sessao.setAttribute("imovelEconomia",imovelEconomia);

			// consulta os ClienteImovelEconomia que pertenca ao imovelSubCategoria corrente ( id do imovel e id da subcategoria)
        	FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
        	filtroClienteImovelEconomia.adicionarParametro( new ParametroSimples(FiltroClienteImovelEconomia.IMOVEL_ECONOMIA_ID, form.getIdImovelEconomia()));
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIA);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_AREA_CONSTRUIDA_FAIXA);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_IMOVEL_SUB_CATEGORIA);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_IMOVEL_CATEGORIA);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");
        	Collection collClienteImovelEconomia = Fachada.getInstancia().pesquisar(filtroClienteImovelEconomia, ClienteImovelEconomia.class.getSimpleName());
        	sessao.setAttribute("collClienteImovelEconomia",collClienteImovelEconomia);

        } else {
        	throw new ActionServletException("atencao.naocadastrado", null, "ImovelEconomia");
        }	

        return retorno;
    }
}