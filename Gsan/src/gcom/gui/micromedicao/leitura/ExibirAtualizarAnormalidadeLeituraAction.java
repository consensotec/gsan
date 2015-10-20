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
package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
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
 * /**
 * <p>
 * <b>[UC0191]</b> Manter Anormalidade de Leitura
 * </p>
 * <p>
 * Esta funcionalidade permite atualizar uma Anormalidade de Leitura
 * </p>
 * 
 * @author Thiago Ten�rio, Magno Gouveia
 * @since 31/10/2006, 23/08/2011
 */
public class ExibirAtualizarAnormalidadeLeituraAction extends GcomAction {

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        // Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("atualizarAnormalidadeLeitura");

        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarAnormalidadeLeituraActionForm form = (AtualizarAnormalidadeLeituraActionForm) actionForm;

        if (httpServletRequest.getParameter("menu") != null) {
            form.setTipoServico("");
        }

        Fachada fachada = Fachada.getInstancia();

        String id = null;

        String idLeituraAnormalidade = null;

        if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
                && !httpServletRequest.getParameter("idRegistroAtualizacao").equals("")) {

            sessao.removeAttribute("objetoLeituraAnormalidade");
            sessao.removeAttribute("colecaoLeituraAnormalidadeTela");

        }

        // Verifica se veio do filtrar ou do manter

        if (httpServletRequest.getParameter("manter") != null) {
            sessao.setAttribute("manter", true);
        } else if (httpServletRequest.getParameter("filtrar") != null) {
            sessao.removeAttribute("manter");
        }

        /*
         * Verifica se o servicoCobrancaValor j� est� na sess�o, em caso afirmativo significa que o
         * usu�rio j� entrou na tela e apenas selecionou algum item que deu um reload na tela e em
         * caso negativo significa que ele est� entrando pela primeira vez
         */

        if (sessao.getAttribute("colecaoLeituraAnormalidadeTela") == null) {

            if (sessao.getAttribute("objetoLeituraAnormalidade") != null) {

                LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) sessao.getAttribute("objetoLeituraAnormalidade");

                sessao.setAttribute("idLeituraAnormalidade", leituraAnormalidade.getId().toString());

                sessao.setAttribute("leituraAnormalidade", leituraAnormalidade);

                form.setDescricao(leituraAnormalidade.getDescricao());
                form.setAbreviatura(leituraAnormalidade.getDescricaoAbreviada());
                form.setIndicadorRelativoHidrometro("" + leituraAnormalidade.getIndicadorRelativoHidrometro());
                form.setIndicadorImovelSemHidrometro("" + leituraAnormalidade.getIndicadorImovelSemHidrometro());
                form.setUsoRestritoSistema("" + leituraAnormalidade.getIndicadorSistema());
                form.setPerdaTarifaSocial("" + leituraAnormalidade.getIndicadorPerdaTarifaSocial());
                form.setOsAutomatico("" + leituraAnormalidade.getIndicadorEmissaoOrdemServico());
                form.setTipoServico(leituraAnormalidade.getServicoTipo().getId().toString());
                form.setConsumoLeituraNaoInformado(leituraAnormalidade.getLeituraAnormalidadeConsumoSemleitura()
                                                                      .getId()
                                                                      .toString());
                form.setConsumoLeituraInformado(leituraAnormalidade.getLeituraAnormalidadeConsumoComleitura()
                                                                   .getId()
                                                                   .toString());
                form.setLeituraLeituraNaoturaInformado(leituraAnormalidade.getLeituraAnormalidadeLeituraSemleitura()
                                                                          .getId()
                                                                          .toString());
                form.setLeituraLeituraInformado(leituraAnormalidade.getLeituraAnormalidadeLeituraComleitura()
                                                                   .getId()
                                                                   .toString());
                form.setDataUltimaAlteracao(Util.formatarData(leituraAnormalidade.getUltimaAlteracao()));
                form.setIndicadorUso("" + leituraAnormalidade.getIndicadorUso());
                form.setNumeroFatorSemLeitura("" + leituraAnormalidade.getNumeroFatorSemLeitura());
                form.setNumeroFatorComLeitura("" + leituraAnormalidade.getNumeroFatorComLeitura());
                form.setIndicadorLeitura("" + leituraAnormalidade.getIndicadorLeitura());
                
                if(leituraAnormalidade.getNumeroVezesSuspendeLeitura() != null && 
                		!leituraAnormalidade.getNumeroVezesSuspendeLeitura().equals("")){
                	form.setNumeroVezesSuspendeLeitura(leituraAnormalidade.getNumeroVezesSuspendeLeitura().toString());
                }else{
                	form.setNumeroVezesSuspendeLeitura("0");
                }
                
                if(leituraAnormalidade.getNumeroMesesLeituraSuspensa() != null && 
                		!leituraAnormalidade.getNumeroMesesLeituraSuspensa().equals("")){
                	form.setNumeroMesesLeituraSuspensa(leituraAnormalidade.getNumeroMesesLeituraSuspensa().toString());
                }else{
                	form.setNumeroMesesLeituraSuspensa("0");
                }
                
                id = leituraAnormalidade.getId().toString();

                sessao.setAttribute("leituraAnormalidadeAtualizar", leituraAnormalidade);
                sessao.removeAttribute("objetoLeituraAnormalidade");

            } else {

                LeituraAnormalidade leituraAnormalidade = null;

                idLeituraAnormalidade = null;

                if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
                        || httpServletRequest.getParameter("idRegistroAtualizacao").equals("")) {
                    leituraAnormalidade = (LeituraAnormalidade) sessao.getAttribute("objetoLeituraAnormalidade");
                } else {
                    idLeituraAnormalidade = (String) httpServletRequest.getParameter("idRegistroAtualizacao");
                    sessao.setAttribute("idRegistroAtualizacao", idLeituraAnormalidade);
                }
                
                if (idLeituraAnormalidade == null) {
                	if (sessao.getAttribute("idRegistroAtualizacao") != null) {
                		idLeituraAnormalidade = (String) sessao.getAttribute("idRegistroAtualizacao");
                	} else {
                		leituraAnormalidade = (LeituraAnormalidade) sessao.getAttribute("leituraAnormalidade");
                		idLeituraAnormalidade = leituraAnormalidade.getId().toString();
                	}
                }

                if (idLeituraAnormalidade != null) {

                    id = idLeituraAnormalidade;

                    FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
                    filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
                    filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoComleitura");
                    filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraSemleitura");
                    filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraComleitura");

                    filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID,
                                                                                      idLeituraAnormalidade));

                    Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
                                                                                                   LeituraAnormalidade.class.getName());

                    if (colecaoLeituraAnormalidade == null || colecaoLeituraAnormalidade.isEmpty()) {
                        throw new ActionServletException("atencao.atualizacao.timestamp");
                    }

                    httpServletRequest.setAttribute("colecaoLeituraAnormalidade", colecaoLeituraAnormalidade);

                    leituraAnormalidade = (LeituraAnormalidade) colecaoLeituraAnormalidade.iterator().next();

                }

                form.setDescricao(leituraAnormalidade.getDescricao());

                form.setAbreviatura(leituraAnormalidade.getDescricaoAbreviada());

                form.setIndicadorRelativoHidrometro("" + leituraAnormalidade.getIndicadorRelativoHidrometro());
                form.setIndicadorImovelSemHidrometro("" + leituraAnormalidade.getIndicadorImovelSemHidrometro());
                form.setUsoRestritoSistema("" + leituraAnormalidade.getIndicadorSistema());
                form.setPerdaTarifaSocial("" + leituraAnormalidade.getIndicadorPerdaTarifaSocial());
                form.setOsAutomatico("" + leituraAnormalidade.getIndicadorEmissaoOrdemServico());
                form.setIndicadorExibirAnormalidadeRelatorio(
                		leituraAnormalidade.getIndicadorExibirAnormalidadeRelatorio().toString());
                form.setIndicadorExibirMensagemHidrometrosCalcada(leituraAnormalidade.getIndicadorExibirMensagemHidrometrosCalcada().toString());
                form.setIndicadorExibirMensagemHidrometrosSubstituidos(leituraAnormalidade.getIndicadorExibirMensagemHidrometrosSubstituidos().toString());
                form.setIndicadorNaoImprimirConta(leituraAnormalidade.getIndicadorNaoImprimirConta().toString());
                
                //RM 3403
                form.setIndicadorFotoObrigatoria(leituraAnormalidade.getIndicadorFotoObrigatoria().toString());

                if (leituraAnormalidade.getServicoTipo() != null) {
                    form.setTipoServico(leituraAnormalidade.getServicoTipo().getId().toString());
                } else {
                    form.setTipoServico("");
                }

                if (leituraAnormalidade.getLeituraAnormalidadeConsumoSemleitura() != null) {
                    form.setConsumoLeituraNaoInformado(leituraAnormalidade.getLeituraAnormalidadeConsumoSemleitura()
                                                                          .getId()
                                                                          .toString());
                } else {
                    form.setConsumoLeituraNaoInformado("");
                }

                if (leituraAnormalidade.getLeituraAnormalidadeConsumoComleitura() != null) {
                    form.setConsumoLeituraInformado(leituraAnormalidade.getLeituraAnormalidadeConsumoComleitura()
                                                                       .getId()
                                                                       .toString());
                } else {
                    form.setConsumoLeituraInformado("");
                }

                if (leituraAnormalidade.getLeituraAnormalidadeLeituraSemleitura() != null) {
                    form.setLeituraLeituraNaoturaInformado(leituraAnormalidade.getLeituraAnormalidadeLeituraSemleitura()
                                                                              .getId()
                                                                              .toString());
                } else {
                    form.setLeituraLeituraNaoturaInformado("");
                }

                if (leituraAnormalidade.getLeituraAnormalidadeLeituraComleitura() != null) {
                    form.setLeituraLeituraInformado(leituraAnormalidade.getLeituraAnormalidadeLeituraComleitura()
                                                                       .getId()
                                                                       .toString());
                } else {
                    form.setLeituraLeituraInformado("");
                }

                form.setIndicadorUso("" + leituraAnormalidade.getIndicadorUso());
                form.setDataUltimaAlteracao(Util.formatarDataComHora(leituraAnormalidade.getUltimaAlteracao()));

                if (leituraAnormalidade.getNumeroFatorSemLeitura() != null) {
                    form.setNumeroFatorSemLeitura("" + leituraAnormalidade.getNumeroFatorSemLeitura());
                }
                if (leituraAnormalidade.getNumeroFatorComLeitura() != null) {
                    form.setNumeroFatorComLeitura("" + leituraAnormalidade.getNumeroFatorComLeitura());
                }
                if (leituraAnormalidade.getIndicadorLeitura() != null) {
                    form.setIndicadorLeitura("" + leituraAnormalidade.getIndicadorLeitura());
                }

                if (leituraAnormalidade.getNumeroVezesSuspendeLeitura() != null
                        && !leituraAnormalidade.getNumeroVezesSuspendeLeitura().equals("")) {
                    form.setNumeroVezesSuspendeLeitura(leituraAnormalidade.getNumeroVezesSuspendeLeitura().toString());
                } else {
                    form.setNumeroVezesSuspendeLeitura("0");
                }

                if (leituraAnormalidade.getNumeroMesesLeituraSuspensa() != null
                        && !leituraAnormalidade.getNumeroMesesLeituraSuspensa().equals("")) {
                    form.setNumeroMesesLeituraSuspensa(leituraAnormalidade.getNumeroMesesLeituraSuspensa().toString());
                } else {
                    form.setNumeroMesesLeituraSuspensa("0");
                }

                sessao.setAttribute("leituraAnormalidadeAtualizar", leituraAnormalidade);
            }
        }

        // -------------- bt DESFAZER ---------------

        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {

            sessao.removeAttribute("colecaoLeituraAnormalidadeTela");

            String leituraAnormalidadeID = null;

            if (sessao.getAttribute("idRegistroAtualizacao") != null
                    && !sessao.getAttribute("idRegistroAtualizacao").equals("")) {
                leituraAnormalidadeID = (String) sessao.getAttribute("idRegistroAtualizacao");
            }

            if (leituraAnormalidadeID != null && leituraAnormalidadeID.equalsIgnoreCase("")) {
                leituraAnormalidadeID = null;
            }

            if ((leituraAnormalidadeID == null) && (id == null)) {

                LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) sessao.getAttribute("objetoLeituraAnormalidade");

                form.setDescricao(leituraAnormalidade.getDescricao());
                form.setAbreviatura(leituraAnormalidade.getDescricaoAbreviada());
                form.setIndicadorRelativoHidrometro("" + leituraAnormalidade.getIndicadorRelativoHidrometro());
                form.setIndicadorImovelSemHidrometro("" + leituraAnormalidade.getIndicadorImovelSemHidrometro());
                form.setUsoRestritoSistema("" + leituraAnormalidade.getIndicadorSistema());
                form.setPerdaTarifaSocial("" + leituraAnormalidade.getIndicadorPerdaTarifaSocial());
                form.setOsAutomatico("" + leituraAnormalidade.getIndicadorEmissaoOrdemServico());
                form.setTipoServico(leituraAnormalidade.getServicoTipo().getId().toString());
                form.setConsumoLeituraNaoInformado(leituraAnormalidade.getLeituraAnormalidadeConsumoSemleitura()
                                                                      .getId()
                                                                      .toString());
                form.setConsumoLeituraInformado(leituraAnormalidade.getLeituraAnormalidadeConsumoComleitura()
                                                                   .getId()
                                                                   .toString());
                form.setLeituraLeituraNaoturaInformado(leituraAnormalidade.getLeituraAnormalidadeLeituraSemleitura()
                                                                          .getId()
                                                                          .toString());
                form.setLeituraLeituraInformado(leituraAnormalidade.getLeituraAnormalidadeLeituraComleitura()
                                                                   .getId()
                                                                   .toString());

                if (leituraAnormalidade.getNumeroFatorSemLeitura() != null) {
                    form.setNumeroFatorSemLeitura("" + leituraAnormalidade.getNumeroFatorSemLeitura());
                }
                if (leituraAnormalidade.getNumeroFatorComLeitura() != null) {
                    form.setNumeroFatorComLeitura("" + leituraAnormalidade.getNumeroFatorComLeitura());
                }
                if (leituraAnormalidade.getIndicadorLeitura() != null) {
                    form.setIndicadorLeitura("" + leituraAnormalidade.getIndicadorLeitura());
                }

                if(leituraAnormalidade.getNumeroVezesSuspendeLeitura() != null && 
                		!leituraAnormalidade.getNumeroVezesSuspendeLeitura().equals("")){
                	form.setNumeroVezesSuspendeLeitura(leituraAnormalidade.getNumeroVezesSuspendeLeitura().toString());
                }else{
                	form.setNumeroVezesSuspendeLeitura("0");
                }
                
                if(leituraAnormalidade.getNumeroMesesLeituraSuspensa() != null &&
                		!leituraAnormalidade.getNumeroMesesLeituraSuspensa().equals("")){
                	form.setNumeroMesesLeituraSuspensa(leituraAnormalidade.getNumeroMesesLeituraSuspensa().toString());
                }else{
                	form.setNumeroMesesLeituraSuspensa("0");
                }

                sessao.setAttribute("leituraAnormalidadeAtualizar", leituraAnormalidade);
                sessao.removeAttribute("leituraAnormalidade");
            }

            if ((idLeituraAnormalidade == null) && (id != null)) {
                idLeituraAnormalidade = id;
            }

            if (idLeituraAnormalidade != null) {

                FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
                filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
                filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID,
                                                                                  idLeituraAnormalidade));

                Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
                                                                                               LeituraAnormalidade.class.getName());

                if (Util.isVazioOrNulo(colecaoLeituraAnormalidade)) {
                    throw new ActionServletException("atencao.atualizacao.timestamp");
                }

                httpServletRequest.setAttribute("colecaoLeituraAnormalidade", colecaoLeituraAnormalidade);

                LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) colecaoLeituraAnormalidade.iterator().next();

                form.setAbreviatura(leituraAnormalidade.getDescricaoAbreviada());
                form.setIndicadorRelativoHidrometro("" + leituraAnormalidade.getIndicadorRelativoHidrometro());
                form.setIndicadorImovelSemHidrometro("" + leituraAnormalidade.getIndicadorImovelSemHidrometro());
                form.setUsoRestritoSistema("" + leituraAnormalidade.getIndicadorSistema());
                form.setPerdaTarifaSocial("" + leituraAnormalidade.getIndicadorPerdaTarifaSocial());
                form.setOsAutomatico("" + leituraAnormalidade.getIndicadorEmissaoOrdemServico());
                
                if(leituraAnormalidade.getServicoTipo() != null && !leituraAnormalidade.getServicoTipo().equals("")){
                	form.setTipoServico(leituraAnormalidade.getServicoTipo().getId().toString());
                }else{
                	form.setTipoServico("-1");
                }
                
                form.setConsumoLeituraNaoInformado(leituraAnormalidade.getLeituraAnormalidadeConsumoSemleitura()
                                                                      .getId()
                                                                      .toString());
                form.setConsumoLeituraInformado(leituraAnormalidade.getLeituraAnormalidadeConsumoComleitura()
                                                                   .getId()
                                                                   .toString());
                form.setLeituraLeituraNaoturaInformado(leituraAnormalidade.getLeituraAnormalidadeLeituraSemleitura()
                                                                          .getId()
                                                                          .toString());
                form.setLeituraLeituraInformado(leituraAnormalidade.getLeituraAnormalidadeLeituraComleitura()
                                                                   .getId()
                                                                   .toString());
                if (leituraAnormalidade.getNumeroFatorSemLeitura() != null) {
                    form.setNumeroFatorSemLeitura("" + leituraAnormalidade.getNumeroFatorSemLeitura());
                }
                if (leituraAnormalidade.getNumeroFatorComLeitura() != null) {
                    form.setNumeroFatorComLeitura("" + leituraAnormalidade.getNumeroFatorComLeitura());
                }
                if (leituraAnormalidade.getIndicadorLeitura() != null) {
                    form.setIndicadorLeitura("" + leituraAnormalidade.getIndicadorLeitura());
                }

                if(leituraAnormalidade.getNumeroVezesSuspendeLeitura() != null && 
                		!leituraAnormalidade.getNumeroVezesSuspendeLeitura().equals("")){
                	form.setNumeroVezesSuspendeLeitura(leituraAnormalidade.getNumeroVezesSuspendeLeitura().toString());
                }else{
                	form.setNumeroVezesSuspendeLeitura("0");
                }
                
                if(leituraAnormalidade.getNumeroMesesLeituraSuspensa() != null && 
                		!leituraAnormalidade.getNumeroMesesLeituraSuspensa().equals("")){
                	form.setNumeroMesesLeituraSuspensa(leituraAnormalidade.getNumeroMesesLeituraSuspensa().toString());
                }else{
                	form.setNumeroMesesLeituraSuspensa("0");
                }

                httpServletRequest.setAttribute("idLeituraAnormalidade", idLeituraAnormalidade);
                sessao.setAttribute("leituraAnormalidadeAtualizar", leituraAnormalidade);
            }
        }
        // -------------- bt DESFAZER ---------------

        httpServletRequest.setAttribute("colecaoLeituraAnormalidadeTela",
                                        sessao.getAttribute("colecaoLeituraAnormalidadeTipoValorTela"));

        return retorno;

    }

}
