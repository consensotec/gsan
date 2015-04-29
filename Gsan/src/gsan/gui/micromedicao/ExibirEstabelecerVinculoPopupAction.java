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
package gsan.gui.micromedicao;

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gsan.fachada.Fachada;
import gsan.faturamento.MobileComunicationException;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRateioTipo;
import gsan.micromedicao.RateioTipo;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Estabelecer Vinculo
 * 
 * @author Rafael Santos
 * @since 11/01/2006
 */
public class ExibirEstabelecerVinculoPopupAction extends GcomAction {
    /**
     * @author Administrador
     * @date 21/03/2006
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("estabelecerVinculoPopup");

        Fachada fachada = Fachada.getInstancia();

        // Mudar isso quando implementar a parte de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        EstabelecerVinculoPopupActionForm form = (EstabelecerVinculoPopupActionForm) actionForm;

        String acao = httpServletRequest.getParameter("acao");
        String posicao = httpServletRequest.getParameter("posicao");

        String codigoRegistro = httpServletRequest.getParameter("idCampoEnviarDados");

        String descricaoCampoEnviarDados = httpServletRequest.getParameter("descricaoCampoEnviarDados");

        if (codigoRegistro != null && !codigoRegistro.equals("")) {
            // acao = "EXIBIR";

            String matriculaImovel = (String) sessao.getAttribute("matriculaImovel");

            FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
            filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
                                                                                        matriculaImovel));
            filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

            Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

            if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {

                RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistorico.iterator()
                                                                                                             .next()).getRateioTipo();
                if (rateioTipo != null) {
                    form.setRateioTipoAgua(rateioTipo.getId().toString());
                } else {
                    form.setRateioTipoAgua("");
                }

                form.setBotao("Visualizar");
            }

            // po�o
            FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistoricoPoco = new FiltroHidrometroInstalacaoHistorico();
            filtroHidrometroInstalacaoHistoricoPoco.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
                                                                                            matriculaImovel));
            filtroHidrometroInstalacaoHistoricoPoco.adicionarParametro(new ParametroNulo(filtroHidrometroInstalacaoHistorico.DATA_RETIRADA));

            Collection colecaoHidrometroInstalacaoHistoricoPoco = fachada.pesquisar(filtroHidrometroInstalacaoHistoricoPoco, HidrometroInstalacaoHistorico.class.getName());

            if (colecaoHidrometroInstalacaoHistoricoPoco != null && !colecaoHidrometroInstalacaoHistoricoPoco.isEmpty()) {

                RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistoricoPoco)).getRateioTipo();
                if (rateioTipo != null) {
                    form.setRateioTipoPoco(rateioTipo.getId().toString());
                } else {
                    form.setRateioTipoPoco("");
                }
                form.setBotao("Visualizar");
            }
        }

        String pesquisaImovel = httpServletRequest.getParameter("pesquisaImovel");

        if (pesquisaImovel != null && !pesquisaImovel.equals("")) {

            FiltroImovel filtroImovel = new FiltroImovel();
            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
                                                                 form.getCodigoImovel()));

            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);

            Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
                Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

                form.setMatriculaImovel(imovel.getInscricaoFormatada());
                form.setCodigoImovel(imovel.getId().toString());
                httpServletRequest.setAttribute("matriculaInexistente", null);

            } else {
                form.setMatriculaImovel("MATR�CULA INEXISTENTE");
                form.setCodigoImovel("");
                httpServletRequest.setAttribute("matriculaInexistente", "exception");
            }

        }

        Collection<Imovel> colecaoImoveisASerVinculados = new ArrayList<Imovel>();

        if (acao != null && acao.trim().equalsIgnoreCase("EXIBIR")) {

            form.setRateioTipoAgua(null);
            form.setRateioTipoPoco(null);
            form.setBotao(null);
            form.setCodigoImovel(null);
            form.setImoveisVinculados(null);
            form.setImovel(null);
            form.setIndicadorImovelAreaComum(null);

            FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();
            filtroRateioTipo.setCampoOrderBy(FiltroRateioTipo.DESCRICAO);
            filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.INDICADOR_USO,
                                                                     ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection<RateioTipo> colecaoRateioTipo = fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName());

            sessao.setAttribute("colecaoRateioTipo", colecaoRateioTipo);

            String matriculaImovel = null;

            if (httpServletRequest.getParameter("MatriculaImovel") != null) {

                matriculaImovel = httpServletRequest.getParameter("MatriculaImovel").trim();
            } else {
                matriculaImovel = (String) sessao.getAttribute("matriculaImovel");
            }

            sessao.setAttribute("matriculaImovel", matriculaImovel);

            FiltroImovel filtroImovel = new FiltroImovel();
            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(matriculaImovel.trim())));

            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico.rateioTipo");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.rateioTipo");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelCondominio");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa.faturamentoGrupo");

            Collection<Imovel> imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            // visualizar os dados do condominio
            if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
                Imovel imovel = imovelPesquisado.iterator().next();

                FiltroImovel filtroImovelVinculados = new FiltroImovel(FiltroImovel.INDICADOR_IMOVEL_AREA_COMUM);
                filtroImovelVinculados.adicionarParametro(new ParametroSimples(FiltroImovel.IMOVEL_CONDOMINIO_ID,
                                                                               imovel.getId()));
                filtroImovelVinculados.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_CONDOMINIO);

                Collection<Imovel> imovelPesquisadoVinculados = fachada.pesquisar(filtroImovelVinculados, Imovel.class.getName());

                // ligacao agua
                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
                                                                                            matriculaImovel));
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

                Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {

                    RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico)).getRateioTipo();
                    if (rateioTipo != null) {
                        form.setRateioTipoAgua(rateioTipo.getId().toString());
                    } else {
                        form.setRateioTipoAgua("");
                    }

                    form.setBotao("Visualizar");
                }

                // po�o
                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistoricoPoco = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistoricoPoco.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
                                                                                                matriculaImovel));
                filtroHidrometroInstalacaoHistoricoPoco.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

                Collection colecaoHidrometroInstalacaoHistoricoPoco = fachada.pesquisar(filtroHidrometroInstalacaoHistoricoPoco, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistoricoPoco != null && !colecaoHidrometroInstalacaoHistoricoPoco.isEmpty()) {
                    RateioTipo rateioTipo = ((HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistoricoPoco)).getRateioTipo();
                    if (rateioTipo != null) {
                        form.setRateioTipoPoco(rateioTipo.getId().toString());
                    } else {
                        form.setRateioTipoPoco("");
                    }
                    form.setBotao("Visualizar");
                }

                if (form.getRateioTipoAgua() != null
                        && form.getRateioTipoAgua().equals(RateioTipo.RATEIO_AREA_COMUM.toString())) {
                    for (Imovel imovelVinculado : imovelPesquisadoVinculados) {
                        if (imovelVinculado.getIndicadorImovelAreaComum().equals(ConstantesSistema.SIM)) {
                            form.setPossuiImovelAreaComum(true);
                            break;
                        }
                    }
                }

                sessao.setAttribute("colecaoImoveisASerVinculados", imovelPesquisadoVinculados);

                // [FS0003] Verificar exit�ncia de v�nculo com outro im�vel
                // condom�nio
                if (imovel.getImovelCondominio() != null && (imovel.getImovelCondominio().getId() != null)) {
                    throw new ActionServletException("atencao.imovel.vinculado",
                                                     null,
                                                     imovel.getImovelCondominio().getId().toString());
                }
                // [FS0002] Verificar pr�-requisitos para im�vel condom�nio
                if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO.intValue()
                        && imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()) {
                    throw new ActionServletException("atencao.imovel.condominio.nao.agua.esgoto");
                }

                // trata se n�o tem liga��o de agua
                if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue()
                        & imovel.getLigacaoAgua() == null) {
                    throw new ActionServletException("atencao.imovel.sem.ligacao_agua");
                }

                // [FS0002] Verificar pr�-requisitos para im�vel condom�nio
                if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue()
                        && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() == null) {
                    throw new ActionServletException("atencao.imovel.condominio.nao.hidrometro.agua");
                }
                // [FS0002] Verificar pr�-requisitos para im�vel condom�nio
                if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO.intValue()
                        && imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovel.getHidrometroInstalacaoHistorico() == null) {
                    throw new ActionServletException("atencao.imovel.condominio.nao.hidrometro.poco");
                }

                // [FS0002] Verificar pr�-requisitos para im�vel condom�nio
                String anoMesFaturamento = fachada.pesquisarParametrosDoSistema().getAnoMesFaturamento() + "";

                Calendar dataFaturamento = new GregorianCalendar();
                dataFaturamento.set(Calendar.YEAR, new Integer(anoMesFaturamento.substring(0, 4)).intValue());
                dataFaturamento.set(Calendar.MONTH, new Integer(anoMesFaturamento.substring(4, 6)).intValue() - 1);
                dataFaturamento.set(Calendar.DATE, 30);

                // data inicio vencimento debito
                Calendar dataInicioVencimentoDebito = new GregorianCalendar();
                dataInicioVencimentoDebito.set(Calendar.YEAR, new Integer("0001").intValue());
                dataInicioVencimentoDebito.set(Calendar.MONTH, 0);
                dataInicioVencimentoDebito.set(Calendar.DATE, new Integer("01").intValue());

                // data final de vencimento de debito
                Calendar dataFimVencimentoDebito = new GregorianCalendar();
                dataFimVencimentoDebito.set(Calendar.YEAR, new Integer(anoMesFaturamento.substring(0, 4)).intValue());
                dataFimVencimentoDebito.set(Calendar.MONTH, new Integer(anoMesFaturamento.substring(4, 6)).intValue());
                dataFimVencimentoDebito.set(Calendar.DAY_OF_MONTH, dataFaturamento.getMaximum(Calendar.DAY_OF_MONTH));

                // data final referencia debito
                dataFaturamento.add(Calendar.MONTH, -1);
                StringBuffer dataFinalReferenciaDebito = new StringBuffer().append(dataFaturamento.get(Calendar.YEAR));
                if (dataFaturamento.get(Calendar.MONTH) < 10) {
                    dataFinalReferenciaDebito.append("0" + dataFaturamento.get(Calendar.MONTH));
                } else {
                    dataFinalReferenciaDebito.append(dataFaturamento.get(Calendar.MONTH));
                }

                ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = fachada.obterDebitoImovelOuCliente(1, imovel.getId()
                                                                                                                                .toString(), null, null, "000101", dataFinalReferenciaDebito.toString(), dataInicioVencimentoDebito.getTime(), dataFimVencimentoDebito.getTime(), 1, 1, 1, 1, 1, 1, 2, null,2);

                boolean existeDebito = false;
                if (obterDebitoImovelOuClienteHelper != null) {
                    // contas
                    if (obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null
                            && !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()) {
                        existeDebito = true;

                        // credito a realizar
                    } else if (obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null
                            && !obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar().isEmpty()) {
                        existeDebito = true;

                        // debito a cobrar
                    } else if (obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null
                            && !obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar().isEmpty()) {
                        existeDebito = true;

                        // guias pagamento
                    } else if (obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores() != null
                            && !obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores().isEmpty()) {
                        existeDebito = true;
                    }

                }

                // Se existir debito para o imovel
                if (existeDebito) {
                    throw new ActionServletException("atencao.imovel.condominio.possui_debito");

                }
                
              
                //Validar situacao arquivo para vincular imovel
                Integer idRota =  null;
                Integer anoMesFaturamentoGrupo = null;
                
                if(imovel.getRotaAlternativa() != null){
                	idRota =  imovel.getRotaAlternativa().getId();
                	anoMesFaturamentoGrupo = imovel.getRotaAlternativa().getFaturamentoGrupo().getAnoMesReferencia();
                }
                else{
                	if(imovel.getQuadra() != null && imovel.getQuadra().getRota() != null){
                		idRota = imovel.getQuadra().getRota().getId();
                		anoMesFaturamentoGrupo = imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia();
                	}
                }
                
                fachada.validarVinculoImovelCondomionio(imovel.getId(), anoMesFaturamentoGrupo.toString(), idRota);
				
				
                sessao.setAttribute("imovelCondominio", imovel);
                form.setMatriculaImovel("");
            }
        } else if (acao != null && acao.trim().equalsIgnoreCase("ADICIONAR")) {
            // adicionar imovel

            String idImovel = form.getCodigoImovel().trim();
            Collection imoveisASerVinculados = null;

            if (sessao.getAttribute("colecaoImoveisASerVinculados") != null) {
                imoveisASerVinculados = (Collection) sessao.getAttribute("colecaoImoveisASerVinculados");

                if (imoveisASerVinculados != null && !imoveisASerVinculados.isEmpty()) {
                    Iterator iImoveisASerVinculados = imoveisASerVinculados.iterator();

                    while (iImoveisASerVinculados.hasNext()) {

                        Imovel imovelVinculado = (Imovel) iImoveisASerVinculados.next();

                        if (imovelVinculado.getId().intValue() == new Integer(idImovel).intValue()) {
                            throw new ActionServletException("atencao.imovel.a_ser_vinculado",
                                                             null,
                                                             imovelVinculado.getId().toString());
                        }
                    }
                }
            }

            if (form.getRateioTipoAgua() != null) {
                form.setRateioTipoAgua(form.getRateioTipoAgua());
                form.setBotao("Visualizar");
            }
            if (form.getRateioTipoPoco() != null) {
                form.setRateioTipoPoco(form.getRateioTipoPoco());
                form.setBotao("Visualizar");
            }

            FiltroImovel filtroImovel = new FiltroImovel();

            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
                                                                 idImovel));
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

            // Procura Imovel na base
            Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            // [FS0001] Verificar exit�ncia da matr�cula do im�vel
            if (colecaoImoveis != null && colecaoImoveis.isEmpty()) {
                throw new ActionServletException("atencao.pesquisa_inexistente",
                                                 null,
                                                 "Matr�cula");
            }

            Imovel imovelASerVinculado = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);

            Imovel imovelCondominio = null;

            if (sessao.getAttribute("imovelCondominio") != null) {
                imovelCondominio = (Imovel) sessao.getAttribute("imovelCondominio");
            }

            if (imovelCondominio != null && imovelASerVinculado.getId().intValue() == imovelCondominio.getId().intValue()) {
                throw new ActionServletException("atencao.imovel_condominio.nao.vincular.imovel");
            }

            // [FS0003] Verificar exit�ncia de v�nculo com outro im�vel
            // condom�nio
            if (imovelASerVinculado.getImovelCondominio() != null
                    && (imovelASerVinculado.getImovelCondominio().getId() != null)
                    && (imovelASerVinculado.getImovelCondominio().getId().intValue() != imovelCondominio.getId().intValue())) {
                throw new ActionServletException("atencao.imovel.vinculado",
                                                 null,
                                                 imovelASerVinculado.getImovelCondominio().getId().toString());
            }

            // [FS0004] Verificar se o im�vel j� � um condom�nio
            if (imovelASerVinculado.getIndicadorImovelCondominio() != null
                    && imovelASerVinculado.getIndicadorImovelCondominio().shortValue() == Imovel.IMOVEL_CONDOMINIO.shortValue()) {
                throw new ActionServletException("atencao.imovel.condominio");
            }
            // [FS0006] Verificar Rota
            if (imovelCondominio != null) {
                if (imovelCondominio.getQuadra().getRota().getId().intValue() != imovelASerVinculado.getQuadra()
                                                                                                    .getRota()
                                                                                                    .getId()
                                                                                                    .intValue()) {
                    throw new ActionServletException("atencao.imovel.nao_rota");
                }
            }

            // [FS0010] Verificar pr�-requisitos para im�vel vinculado
            if (imovelCondominio != null) {

                if (imovelCondominio.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.CORTADO.intValue()) {
                    throw new ActionServletException("atencao.imovel.agua.incompativel.condominio");
                }

                if (imovelCondominio.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()) {
                    throw new ActionServletException("atencao.imovel.esgoto.incompativel.condominio");
                }

                if (imovelCondominio.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()
                        && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()) {
                    throw new ActionServletException("atencao.imovel.esgoto.incompativel.condominio");
                }
            }

            if (form.getIndicadorImovelAreaComum() == null || form.getIndicadorImovelAreaComum().equals("2")) {
                imovelASerVinculado.setIndicadorImovelAreaComum(ConstantesSistema.NAO);
            } else {
                imovelASerVinculado.setIndicadorImovelAreaComum(ConstantesSistema.SIM);
                form.setPossuiImovelAreaComum(true);
            }

            // adicionar na cole��o de imoveis a serem vinculados
            if (sessao.getAttribute("colecaoImoveisASerVinculados") != null) {
                ((Collection) sessao.getAttribute("colecaoImoveisASerVinculados")).add(imovelASerVinculado);
            } else {
                colecaoImoveisASerVinculados = new ArrayList();
                colecaoImoveisASerVinculados.add(imovelASerVinculado);
                sessao.setAttribute("colecaoImoveisASerVinculados", colecaoImoveisASerVinculados);
            }

            form.setCodigoImovel("");
            form.setMatriculaImovel("");

        } else if (acao != null && acao.trim().equalsIgnoreCase("REMOVER")) {
            // remover o imovel vinculado

            Collection imoveisASerVinculados = null;
            Collection novaimoveisASerVinculados = new ArrayList();
            Collection imoveisASerDesvinculados = null;
            // Collection novaimoveisASerDesvinculados = new ArrayList();

            if (sessao.getAttribute("colecaoImoveisASerDesvinculados") != null) {
                imoveisASerDesvinculados = (Collection) sessao.getAttribute("colecaoImoveisASerDesvinculados");
            } else {
                imoveisASerDesvinculados = new ArrayList();
            }

            if (sessao.getAttribute("colecaoImoveisASerVinculados") != null) {
                imoveisASerVinculados = (Collection) sessao.getAttribute("colecaoImoveisASerVinculados");

                if (imoveisASerVinculados != null && !imoveisASerVinculados.isEmpty()) {
                    Iterator iImoveisASerVinculados = imoveisASerVinculados.iterator();

                    while (iImoveisASerVinculados.hasNext()) {

                        Imovel imovel = (Imovel) iImoveisASerVinculados.next();

                        if (!(imovel.getId().intValue() == new Integer(posicao).intValue())) {
                            novaimoveisASerVinculados.add(imovel);
                        } else {
                            if (imovel.getIndicadorImovelAreaComum() != null
                                    && imovel.getIndicadorImovelAreaComum().equals(ConstantesSistema.SIM)) {
                                form.setPossuiImovelAreaComum(false);
                            }
                            imoveisASerDesvinculados.add(imovel);
                        }

                    }
                }

                sessao.removeAttribute("colecaoImoveisASerVinculados");
                sessao.removeAttribute("novaimoveisASerVinculados");

                sessao.setAttribute("colecaoImoveisASerVinculados", novaimoveisASerVinculados);
                sessao.setAttribute("colecaoImoveisASerDesvinculados", imoveisASerDesvinculados);
            }
        }

        if (codigoRegistro != null && !codigoRegistro.equals("")) {
            form.setCodigoImovel(codigoRegistro.trim());
            form.setMatriculaImovel(descricaoCampoEnviarDados);

        }

        return retorno;
    }
}
