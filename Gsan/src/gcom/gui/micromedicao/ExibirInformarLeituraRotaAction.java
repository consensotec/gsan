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
package gcom.gui.micromedicao;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * [UC0936] Informar Leitura por Rota
 * </p>
 * <p>
 * Respons�vel pela exibi��o dos dados
 * </p>
 * 
 * @author Thiago Nascimento, Magno Gouveia
 * @since , 01/09/2011
 */
public class ExibirInformarLeituraRotaAction extends GcomAction {

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("InformarLeituraRotaAction");

        InformarLeituraRotaActionForm form = (InformarLeituraRotaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        
         SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		
		form.setIndicadorObrigatoriedade(sistemaParametro.getIndicadorObrigatorioPreecherLeituraRota());

        if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
            form.setIdLocalidade("");
            form.setLocalidadeDescricao("");
            form.setCodigoSetorComercial("");
            form.setSetorComercialDescricao("");
            form.setRota("");
            form.setBloquearCampos("");
            form.setTipo("1");
        }

        if (httpServletRequest.getParameter("idLocalidade") != null
                && !httpServletRequest.getParameter("idLocalidade").equals("")) {
            form.setIdLocalidade("" + httpServletRequest.getParameter("idLocalidade"));
        }

        if (httpServletRequest.getParameter("codigoSetorComercial") != null
                && !httpServletRequest.getParameter("codigoSetorComercial").equals("")) {
            form.setCodigoSetorComercial("" + httpServletRequest.getParameter("codigoSetorComercial"));
        }

        if (httpServletRequest.getParameter("bloquearCampos") != null
                && !httpServletRequest.getParameter("bloquearCampos").equals("")) {
            form.setBloquearCampos("sim");
        }

        // -------Parte que trata do c�digo quando o usu�rio tecla enter
        String idDigitadoEnterLocalidade = form.getIdLocalidade();
        if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.equalsIgnoreCase("")
                && Util.validarValorNaoNumerico(idDigitadoEnterLocalidade)) {
            // Localidade n�o num�rico.
            httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
            throw new ActionServletException("atencao.nao.numerico",
                                             null,
                                             "Localidade ");
        }

        verificaExistenciaCodLocalidade(idDigitadoEnterLocalidade, form, httpServletRequest, fachada);

        String idDigitadoEnterSetorComercial = form.getCodigoSetorComercial();
        if (idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.equalsIgnoreCase("")
                && Util.validarValorNaoNumerico(idDigitadoEnterSetorComercial)) {
            // Setor Comercial n�o num�rico.
            httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");
            throw new ActionServletException("atencao.nao.numerico",
                                             null,
                                             "Setor Comercial ");
        }

        if (httpServletRequest.getParameter("tipoConsulta") != null
                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {

            /*
             * Verifica se o tipo da consulta � de Localidade se for os
             * parametros de enviarDadosParametros ser�o mandados para a pagina
             * rota_pesuisar.jsp
             */
            if (httpServletRequest.getParameter("tipoConsulta").equals("localidade")) {

                form.setIdLocalidade(httpServletRequest.getParameter("idCampoEnviarDados"));
                form.setLocalidadeDescricao(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
                httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");

            } else if (httpServletRequest.getParameter("tipoConsulta").equals("setorComercial")) {

                form.setCodigoSetorComercial(httpServletRequest.getParameter("idCampoEnviarDados"));
                form.setSetorComercialDescricao(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
                httpServletRequest.setAttribute("nomeCampo", "codigoRota");
            }
        }

        verificaExistenciaCodSetorComercial(idDigitadoEnterLocalidade, idDigitadoEnterSetorComercial, form, httpServletRequest, fachada);

        return retorno;
    }

    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, InformarLeituraRotaActionForm form,
            HttpServletRequest httpServletRequest, Fachada fachada) {

        // Verifica se o c�digo da Localidade foi digitado
        if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.trim().equals("")
                && Integer.parseInt(idDigitadoEnterLocalidade) > 0) {

            // Recupera a localidade informada pelo usu�rio
            Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));

            /*
             * Caso a localidade informada pelo usu�rio esteja cadastrada no
             * sistema Seta os dados da localidade no form Caso contr�rio seta
             * as informa��es da localidade para vazio e indica ao usu�rio que a
             * localidade n�o existe
             */
            if (localidadeEncontrada != null) {
                // a localidade foi encontrada
                form.setIdLocalidade("" + (localidadeEncontrada.getId()));
                form.setLocalidadeDescricao(localidadeEncontrada.getDescricao());
                httpServletRequest.setAttribute("idLocalidadeNaoEncontrada", "true");
                httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");

            } else {
                // a localidade n�o foi encontrada
                form.setIdLocalidade("");
                httpServletRequest.setAttribute("idLocalidadeNaoEncontrada", "exception");
                form.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
            }
        }
    }

    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade, String idDigitadoEnterSetorComercial,
            InformarLeituraRotaActionForm form, HttpServletRequest httpServletRequest, Fachada fachada) {

        // Verifica se o c�digo do Setor Comercial foi digitado
        if ((idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.toString().trim().equalsIgnoreCase(""))
                && (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase(""))) {

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
            if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {

                filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
                                                                             new Integer(idDigitadoEnterLocalidade)));
            }

            filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                                                                         new Integer(idDigitadoEnterSetorComercial)));

            Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

            if (setorComerciais != null && !setorComerciais.isEmpty()) {
                // o setor comercial foi encontrado
                SetorComercial setorComercialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
                form.setCodigoSetorComercial("" + (setorComercialEncontrado.getCodigo()));
                form.setSetorComercialDescricao(setorComercialEncontrado.getDescricao());
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "true");
                httpServletRequest.setAttribute("nomeCampo", "rota");

            } else {
                // o setor comercial n�o foi encontrado
                form.setCodigoSetorComercial("");
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "exception");
                form.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");
            }
        }
    }
}