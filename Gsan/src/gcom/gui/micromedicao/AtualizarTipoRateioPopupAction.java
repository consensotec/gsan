/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento This
 * file is part of GSAN, an integrated service management system for Sanitation GSAN is free
 * software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License. GSAN is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento Copyright (C) <2007> Adriano Britto
 * Siqueira Alexandre Santos Cabral Ana Carolina Alves Breda Ana Maria Andrade Cavalcante Aryed Lins
 * de Ara�jo Bruno Leonardo Rodrigues Barros Carlos Elmano Rodrigues Ferreira Cl�udio de Andrade
 * Lira Denys Guimar�es Guenes Tavares Eduardo Breckenfeld da Rosa Borges Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro Francisco do Nascimento J�nior Homero Sampaio Cavalcanti Ivan
 * S�rgio da Silva J�nior Jos� Edmar de Siqueira Jos� Thiago Ten�rio Lopes K�ssia Regina Silvestre
 * de Albuquerque Leonardo Luiz Vieira da Silva M�rcio Roberto Batista da Silva Maria de F�tima
 * Sampaio Leite Micaela Maria Coelho de Ara�jo Nelson Mendon�a de Carvalho Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho Rafael Corr�a Lima e Silva Rafael Francisco Pinto Rafael
 * Koury Monteiro Rafael Palermo de Ara�jo Raphael Veras Rossiter Roberto Sobreira Barbalho Rodrigo
 * Avellar Silveira Rosana Carvalho Barbosa S�vio Luiz de Andrade Cavalcante Tai Mu Shih Thiago
 * Augusto Souza do Nascimento Tiago Moreno Rodrigues Vivianne Barbosa Sousa Este programa �
 * software livre; voc� pode redistribu�-lo e/ou modific�-lo sob os termos de Licen�a P�blica Geral
 * GNU, conforme publicada pela Free Software Foundation; vers�o 2 da Licen�a. Este programa �
 * distribu�do na expectativa de ser �til, mas SEM QUALQUER GARANTIA; sem mesmo a garantia impl�cita
 * de COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM PARTICULAR. Consulte a Licen�a P�blica
 * Geral GNU para obter mais detalhes. Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software Foundation, Inc., 59 Temple Place,
 * Suite 330, Boston, MA 02111-1307, USA.
 */
package gcom.gui.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRateioTipo;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Atualizar Tipo de Rateio
 * <p>
 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
 * </p>
 * 
 * @author Rafael Santos, Magno Gouveia
 * @since 12/01/2006, 17/08/2011
 */
public class AtualizarTipoRateioPopupAction extends GcomAction {

    Fachada fachada = Fachada.getInstancia();

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
    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucessoPopup");

        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarTipoRateioPopupActionForm form = (AtualizarTipoRateioPopupActionForm) actionForm;

        String tipoRateioLigacaoAgua = form.getRateioTipoAgua();

        String tipoRateioLigacaoPoco = form.getRateioTipoPoco();

        Imovel imovel = null;
        if (sessao.getAttribute("imovelVinculado") != null) {

            imovel = (Imovel) sessao.getAttribute("imovelVinculado");
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua = null;
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoPoco = null;

            if (tipoRateioLigacaoAgua != null && !tipoRateioLigacaoAgua.equals("")) {

                // Rateio Tipo Agua
                FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();
                filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.ID,
                                                                         tipoRateioLigacaoAgua));
                RateioTipo rateioTipo = (RateioTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName()));

                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
                                                                                            imovel.getId()));
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));
                filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");

                Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {
                    hidrometroInstalacaoHistoricoAgua = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico);
                    hidrometroInstalacaoHistoricoAgua.setRateioTipo(rateioTipo);
                }

            }

            if (tipoRateioLigacaoPoco != null && !tipoRateioLigacaoPoco.equals("")) {

                FiltroRateioTipo filtroRateioTipo = new FiltroRateioTipo();
                filtroRateioTipo.adicionarParametro(new ParametroSimples(FiltroRateioTipo.ID,
                                                                         tipoRateioLigacaoPoco));

                // Rateio Tipo Poco
                RateioTipo rateioTipo = (RateioTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRateioTipo, RateioTipo.class.getName()));

                FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
                                                                                            imovel.getId()));
                filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
                filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));

                Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

                if (colecaoHidrometroInstalacaoHistorico != null && !colecaoHidrometroInstalacaoHistorico.isEmpty()) {
                    hidrometroInstalacaoHistoricoPoco = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colecaoHidrometroInstalacaoHistorico);
                    hidrometroInstalacaoHistoricoPoco.setRateioTipo(rateioTipo);
                }
            }

            if (httpServletRequest.getParameter("confirmado") != null) {

                if (httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {

                    /*
                     * [SB0004] � Informar Im�vel para �rea Comum Caso o im�vel pesquisado n�o
                     * exista ou seja diferente do im�vel a qual ser� vinculado Caso esteja com
                     * valor = nulo imov_id correspondente ao im�vel condom�nio que est� sendo
                     * atualizado
                     */
                    Integer matriculaImovelAreaComum = Integer.parseInt((String) sessao.getAttribute("matriculaImovelAreaComum"));
                    Integer imovelCondominioDoImovelAreaComum = this.fachada.pesquisarImovelCondominio(matriculaImovelAreaComum);

                    if (imovelCondominioDoImovelAreaComum == null
                            || !imovelCondominioDoImovelAreaComum.equals(imovel.getId())) {

                        Collection<Imovel> imoveisParaVincular = new ArrayList<Imovel>();
                        Imovel imovelParaVincular = this.fachada.pesquisarImovel(matriculaImovelAreaComum);
                        imovelParaVincular.setImovelCondominio(imovel);
                        imoveisParaVincular.add(imovelParaVincular);
                        fachada.estabelecerVinculo(imovel, imoveisParaVincular, null, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO));
                    }

                    atualizarTipoRateio(sessao, imovel, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, true, matriculaImovelAreaComum);
                }
            } else {
                /*
                 * [SB0001] Caso o usu�rio selecione o bot�o ATUALIZAR TIPO DE RATEIO Caso o tipo de
                 * rateio tenha sido atualizado para 'RATEIO POR �REA COMUM' o sistema valida a
                 * matr�cula para �rea comum
                 */
                if (tipoRateioLigacaoAgua != null
                        && Integer.valueOf(tipoRateioLigacaoAgua).equals(RateioTipo.RATEIO_AREA_COMUM)) {

                    FiltroImovel filtroImovel = new FiltroImovel();
                    filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
                                                                         form.getMatriculaImovelAreaComum()));
                    filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
                    filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
                    filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);

                    // Procura Imovel na base
                    Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

                    // [FS0001] Verificar exit�ncia da matr�cula do im�vel
                    if (colecaoImoveis != null && colecaoImoveis.isEmpty()) {
                        throw new ActionServletException("atencao.pesquisa_inexistente",
                                                         null,
                                                         "Matr�cula");
                    }

                    Imovel imovelASerVinculado = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);

                    if (imovel != null && imovelASerVinculado.getId().intValue() == imovel.getId().intValue()) {
                        throw new ActionServletException("atencao.imovel_condominio.nao.vincular.imovel");
                    }
                    
                    int quantidadeEconomias = fachada.obterQuantidadeEconomias(imovelASerVinculado);
                    
                    if (quantidadeEconomias > 1) {
                        throw new ActionServletException("atencao.imovel_condominio.area.comum.mais.uma.economia");
                    }

                    /*
                     * [FS0003] Verificar exit�ncia de v�nculo com outro im�vel condom�nio
                     */
                    if (imovelASerVinculado.getImovelCondominio() != null
                            && (imovelASerVinculado.getImovelCondominio().getId() != null)
                            && (imovelASerVinculado.getImovelCondominio().getId().intValue() != imovel.getId().intValue())) {
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
                    if (imovel != null) {
                        if (imovel.getQuadra().getRota().getId().intValue() != imovelASerVinculado.getQuadra()
                                                                                                  .getRota()
                                                                                                  .getId()
                                                                                                  .intValue()) {
                            throw new ActionServletException("atencao.imovel.nao_rota");
                        }
                    }

                    // [FS0010] Verificar pr�-requisitos para im�vel vinculado
                    if (imovel != null) {

                        if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.CORTADO.intValue()) {
                            throw new ActionServletException("atencao.imovel.agua.incompativel.condominio");
                        }

                        if (imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()) {
                            throw new ActionServletException("atencao.imovel.esgoto.incompativel.condominio");
                        }

                        if (imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIGADO.intValue()
                                && imovelASerVinculado.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()) {
                            throw new ActionServletException("atencao.imovel.esgoto.incompativel.condominio");
                        }
                    }

                    // FS0012
                    if (form.getMatriculaImovelAreaComum() == null || form.getMatriculaImovelAreaComum().length() == 0) {
                        form.setMatriculaImovelAreaComum(form.getMatriculaImovelAreaComumAtual());
                    }
                    this.validarMatriculaParaAreaComum(form.getMatriculaImovelAreaComum());

                    // FS0013 e FS0014
                    boolean imovelNaoVinculadoAoCondominio = this.validarVinculoMatriculaAreaComum(imovel.getId(), form.getMatriculaImovelAreaComum());

                    // FS0014
                    if (imovelNaoVinculadoAoCondominio) {
                        sessao.setAttribute("matriculaImovelAreaComum", form.getMatriculaImovelAreaComum());
                        httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/atualizarTipoRateioPopupAction.do");
                        httpServletRequest.setAttribute("nomeBotao1", "Sim");
                        httpServletRequest.setAttribute("nomeBotao2", "N�o");

                        String[] params = new String[] { form.getMatriculaImovelAreaComum(), imovel.getId().toString() };

                        return montarPaginaConfirmacao("atencao.confirmar_vinculo_matricula_area_comum", httpServletRequest, actionMapping, params);
                    } else {
                        /*
                         * O im�vel informado j� est� vinculado ao im�vel condom�nio, ent�o s�
                         * atualiza o indicadorImovelAreaComum
                         */
                        atualizarTipoRateio(sessao, imovel, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, true, Util.converterStringParaInteger(form.getMatriculaImovelAreaComum()));
                    }

                } else {
                    atualizarTipoRateio(sessao, imovel, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, false, Util.converterStringParaInteger(form.getMatriculaImovelAreaComum()));
                }

            }
        }

        httpServletRequest.setAttribute("fechar", "true");

        // liberar da sessao
        if (sessao.getAttribute("imovelVinculado") != null) {
            sessao.removeAttribute("imovelVinculado");
        }

        // Monta a p�gina de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucessoPopup")) {
            montarPaginaSucesso(httpServletRequest, "Tipo de Rateio do im�vel condom�nio de matr�cula " + imovel.getId()
                    + " atualizado com sucesso.", "", "");
        }

        return retorno;
    }

    /*
     * alterado por pedro alexandre dia 19/11/2006 Recupera o usu�rio logado para passar no met�do
     * de atualizar tipo de rateio para verificar se o usu�rio tem abrang�ncia para atualizar o tipo
     * de rateio informado.
     */
    private void atualizarTipoRateio(HttpSession sessao, Imovel imovel,
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua,
            HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoPoco, boolean isRateioPorAreaComum,
            Integer imovelAreaComum) {

        Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);

        // Atualizar Tipo Rateio
        fachada.atualizarTipoRateio(imovel, hidrometroInstalacaoHistoricoAgua, hidrometroInstalacaoHistoricoPoco, usuarioLogado, isRateioPorAreaComum, imovelAreaComum);
    }

    /**
     * [FS0012] - Validar Matricula para �rea Comum
     */
    private void validarMatriculaParaAreaComum(String matriculaImovelAreaComum) {

        /*
         * Caso a matr�cula do im�vel para �rea comum n�o tenha sido informada, exibir mensagem
         * 'Informe a matr�cula do im�vel para �rea comum' e retornar para o passo correspondente no
         * fluxo principal
         */
        if (matriculaImovelAreaComum == null || matriculaImovelAreaComum.trim().length() == 0) {
            throw new ActionServletException("atencao.matricula_imovel_area_comum_nao_informada");
        }

        /*
         * Caso a matr�cula do im�vel para �rea comum informada n�o exista na tabela IMOVEL, exibir
         * a mensagem 'Matr�cula inexistente no cadastro' e retornar para o passo correspondente no
         * fluxo principal
         */
        Short indicadorExclusao = this.fachada.verificarExistenciaDoImovel(Integer.valueOf(matriculaImovelAreaComum));
        if (indicadorExclusao == null) {
            throw new ActionServletException("atencao.imovel_inexistente");
        } else if (indicadorExclusao.equals(Imovel.IMOVEL_EXCLUIDO)) {
            throw new ActionServletException("atencao.imovel_excluido");
        }
    }

    /**
     * [FS0013 � Validar Vinculo da Matr�cula para �rea Comum]
     */
    private boolean validarVinculoMatriculaAreaComum(Integer idImovelCondominio, String matriculaImovelAreaComum) {
        Integer imovelCondominioDoImovelAreaComumInformado = this.fachada.pesquisarImovelCondominio(Integer.valueOf(matriculaImovelAreaComum));

        if (imovelCondominioDoImovelAreaComumInformado != null
                && !imovelCondominioDoImovelAreaComumInformado.equals(Integer.valueOf(idImovelCondominio))) {
            throw new ActionServletException("atencao.imovel_vinculado_outro_condominio",
                                             String.valueOf(imovelCondominioDoImovelAreaComumInformado));
        }

        return imovelCondominioDoImovelAreaComumInformado == null;
    }
}
