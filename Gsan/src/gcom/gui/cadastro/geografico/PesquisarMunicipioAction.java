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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Realiza a pesquisa de responsavel superior de acordo com os par�metros
 * informados
 * 
 * @author S�vio Luiz
 * @created 20 de Julho de 2005
 */

public class PesquisarMunicipioAction extends GcomAction {
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
        ActionForward retorno = actionMapping.findForward("listaMunicipio");

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Recupera os par�metros do form
        String nome = (String) pesquisarActionForm.get("nomeMunicipio");
        Integer idRegiaoDesenvolvimento = (Integer) pesquisarActionForm
                .get("idRegiaoDesenvolvimento");
        Integer idRegiao = (Integer) pesquisarActionForm.get("idRegiao");
        Integer idMicrorregiao = (Integer) pesquisarActionForm
                .get("idMicrorregiao");
        Integer idUnidadeFederacao = (Integer) pesquisarActionForm
                .get("idUnidadeFederacao");
        String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");

        FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

        filtroMunicipio.setCampoOrderBy(FiltroCliente.NOME);

        boolean peloMenosUmParametroInformado = false;

        //Insere os par�metros informados no filtro

        if (nome != null && !nome.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroMunicipio.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroMunicipio.NOME, nome));
			} else {
				filtroMunicipio.adicionarParametro(new ComparacaoTexto(
						FiltroMunicipio.NOME, nome));
			}
        }

        if (idRegiaoDesenvolvimento != null
                && idRegiaoDesenvolvimento.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
            peloMenosUmParametroInformado = true;
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.REGIAO_DESENVOLVOMENTO_ID,
                    idRegiaoDesenvolvimento));
        }

        if (idRegiao != null
                && idRegiao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
            peloMenosUmParametroInformado = true;
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.REGIAO_ID, idRegiao));
        }

        if (idMicrorregiao != null
                && idMicrorregiao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
            peloMenosUmParametroInformado = true;
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.MICRORREGICAO_ID, idMicrorregiao));
        }

        if (idUnidadeFederacao != null
                && idUnidadeFederacao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
            peloMenosUmParametroInformado = true;
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.UNIDADE_FEDERACAO_ID, idUnidadeFederacao));
        }

        if( sessao.getAttribute("indicadorUsoTodos") == null ){
	        filtroMunicipio.adicionarParametro(new ParametroSimples(
	                FiltroMunicipio.INDICADOR_USO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
        }    

        //Erro caso o usu�rio mandou filtrar sem nenhum par�metro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }

        Collection municipios = null;

        //Obt�m a inst�ncia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //adiciona as depend�ncias para serem mostradas na p�gina
        filtroMunicipio
                .adicionarCaminhoParaCarregamentoEntidade("microrregiao.regiao");

        //Faz a busca das empresas
        municipios = fachada.pesquisar(filtroMunicipio, Municipio.class
                .getName());

      	    // Aciona o controle de pagina��o para que sejam pesquisados apenas
			// os registros que aparecem na p�gina
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroMunicipio, Municipio.class.getName());
			municipios = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			if(municipios.isEmpty()){
				//Nenhum logradouro cadastrado de acordo com os par�metros passados
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "munic�pio");
			}
            sessao.setAttribute("colecaoMunicipios", municipios);

        return retorno;
    }

}
