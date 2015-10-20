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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Action para exibir a pesquisa por rota da p�gina Informar Leitura por Rota
 * 
 * @author Thiago Nascimento
 * 
 */
public class ExibirPesquisarInformarRotaLeituraAction extends GcomAction {

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward 			retorno = actionMapping.findForward("ExibirPesquisarRotaAction");
        PesquisarRotaActionForm form    = (PesquisarRotaActionForm) actionForm;
        Fachada 				fachada = Fachada.getInstancia();
        HttpSession 			sessao  = httpServletRequest.getSession(false);

        if (httpServletRequest.getParameter("limparForm") != null
                && httpServletRequest.getParameter("limparForm").equals("sim")) {
            form.setIdGrupoFaturamento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            form.setIdLocalidade("");
            form.setLocalidadeDescricao("");
            form.setCodigoSetorComercial("");
            form.setSetorComercialDescricao("");
            form.setCodigoRota("");
            form.setEmpresaLeituristica("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            form.setIndicadorUso("3");
            form.setBloquearCampos("");

            sessao.removeAttribute("bloquearSetorComercial");
            sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        }

        form.setIndicadorRotaAlternativa(ConstantesSistema.NAO_CONFIRMADA);

        String idEmpresaLeituristica = httpServletRequest.getParameter("idEmpresaLeituristicaRecebida");
        if (idEmpresaLeituristica != null) {
            sessao.setAttribute("idEmpresaLeituristicaRecebida", idEmpresaLeituristica);
            form.setEmpresaLeituristica(idEmpresaLeituristica);
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

        // Pesquisando grupo de faturamento
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA);
        Collection<?> collectionFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
        sessao.setAttribute("collectionFaturamentoGrupo", collectionFaturamentoGrupo);

        // Pesquisando empresa leitur�stica
        FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
        Collection<?> collectionEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
        sessao.setAttribute("collectionEmpresa", collectionEmpresa);

        // Pesquisando localidade
        String idDigitadoEnterLocalidade = form.getIdLocalidade();        
        if(httpServletRequest.getParameter("pesquisarLocalidade") != null
        		&& httpServletRequest.getParameter("pesquisarLocalidade").equals("sim")){
        	verificaExistenciaCodLocalidade(idDigitadoEnterLocalidade, form, httpServletRequest, fachada);
        }
        
        //Pesquisando Setor
        String idDigitadoEnterSetorComercial = form.getCodigoSetorComercial();
        if (idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.equalsIgnoreCase("")
                && Util.validarValorNaoNumerico(idDigitadoEnterSetorComercial)) {
            // Setor Comercial n�o num�rico.
            httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");
            throw new ActionServletException("atencao.nao.numerico", null, "Setor Comercial ");
        }
        
        if(httpServletRequest.getParameter("pesquisarSetor") != null
        		&& httpServletRequest.getParameter("pesquisarSetor").equals("sim")){       	
        	verificaExistenciaCodSetorComercial(idDigitadoEnterLocalidade, idDigitadoEnterSetorComercial, form, httpServletRequest, fachada);
        }

        if (httpServletRequest.getParameter("tipoConsulta") != null
                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {

            // Verifica se o tipo da consulta � de Localidade
            // se for os parametros de enviarDadosParametros ser�o mandados para
            // a pagina rota_pesuisar.jsp
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

        if (httpServletRequest.getParameter("idSetorComercial") != null
                && !httpServletRequest.getParameter("idSetorComercial").trim().equals("")) {
            String idSetorComercial = httpServletRequest.getParameter("idSetorComercial");
            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
            filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
            filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, idSetorComercial));

            Collection<?> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

            if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

                SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

                form.setCodigoSetorComercial(Integer.toString(setorComercial.getCodigo()));
                form.setSetorComercialDescricao(setorComercial.getDescricao());
                form.setIdLocalidade(setorComercial.getLocalidade().getId().toString());
                form.setLocalidadeDescricao(setorComercial.getLocalidade().getDescricao());
                form.setBloquearCampos("Sim");
            }
        }

        // envia uma flag que ser� verificado no quadra_resultado_pesquisa.jsp
        // para saber se ir� usar o enviar dados ou o enviar dados parametros
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null) {
            sessao.setAttribute("caminhoRetornoTelaPesquisaQuadra", httpServletRequest.getParameter("caminhoRetornoTelaPesquisa"));
        }

        if (httpServletRequest.getParameter("destinoRota") != null) {
            if (httpServletRequest.getParameter("destinoRota").equals("Inicial")) {
                sessao.setAttribute("destinoRota", "Inicial");
            } else {
                sessao.setAttribute("destinoRota", "Final");
            }
        }
        
        if (httpServletRequest.getParameter("idFaturamentoGrupo") != null) {
            if (!httpServletRequest.getParameter("idFaturamentoGrupo").equals("-1")) {
                form.setIdGrupoFaturamento(httpServletRequest.getParameter("idFaturamentoGrupo"));
            } else {
                sessao.removeAttribute("idFaturamentoGrupo");
            }
        }

        return retorno;
    }

    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, PesquisarRotaActionForm form,
            HttpServletRequest httpServletRequest, Fachada fachada) {

        if (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.trim().equals("")
                && Integer.parseInt(idDigitadoEnterLocalidade) > 0) {

        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        	filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idDigitadoEnterLocalidade));
        	Collection<?> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
        	
        	Localidade localidade = null;
        	if(colecaoLocalidade.size() > 0)
        		localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

            if (localidade != null) {
                form.setIdLocalidade(localidade.getId().toString());
                form.setLocalidadeDescricao(localidade.getDescricao());
                form.setCodigoSetorComercial("");
                form.setSetorComercialDescricao("");
                httpServletRequest.setAttribute("idLocalidadeNaoEncontrada", "true");
                httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");
            } else {
                form.setIdLocalidade("");
                form.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
                form.setCodigoSetorComercial("");
                form.setSetorComercialDescricao("");
                httpServletRequest.setAttribute("idLocalidadeNaoEncontrada", "exception");
            }
        }
    }

    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade, String idDigitadoEnterSetorComercial,
            PesquisarRotaActionForm form, HttpServletRequest httpServletRequest, Fachada fachada) {

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

            Collection<?> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

            if (setorComerciais != null && !setorComerciais.isEmpty()) {
                SetorComercial setorComercialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
                form.setCodigoSetorComercial(Integer.toString(setorComercialEncontrado.getCodigo()));
                form.setSetorComercialDescricao(setorComercialEncontrado.getDescricao());
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "true");
            } else {
                form.setCodigoSetorComercial("");
                form.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");
                httpServletRequest.setAttribute("idSetorComercialNaoEncontrada", "exception");
            }
        }
    }
}