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
package gcom.gui.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirComandoAtividadeFaturamentoAction extends GcomAction {

    /**
     * 
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirComandoAtividadeFaturamento");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        // Remove a cole��o de rotas n�o habilitadas
        sessao.removeAttribute("colecaoRotasNaoHabilitadas");

        //Inst�ncia do formul�rio que est� sendo utilizado
        InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = (InserirComandoAtividadeFaturamentoActionForm) actionForm;

        Collection colecaoFaturamentoGrupo;
        Collection colecaoAtividadeFaturamento;

        // Grupo de faturamento (Carregar cole��o)
        if (sessao.getAttribute("colecaoGrupoFaturamento") == null) {

            colecaoFaturamentoGrupo = fachada.pesquisarFaturamentoGrupoComCronogramaMensalParaMesCorrente();

            sessao.setAttribute("colecaoGrupoFaturamento", colecaoFaturamentoGrupo);
            
        }

        
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        /*
         * N�mero m�nimo de dias (PARM_NNMINIMODIASEMISSAOVENCIMENTO da tabela
         * SISTEMA_PARAMETROS)
         */
        
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

        if (sessao.getAttribute("dataCorrente") == null &&
            sistemaParametro != null) {

            if (sistemaParametro.getNumeroMinimoDiasEmissaoVencimento() != null) {
                Calendar dataCorrente = new GregorianCalendar();
                dataCorrente.add(Calendar.DATE, sistemaParametro
                .getNumeroMinimoDiasEmissaoVencimento().intValue());

                sessao.setAttribute("dataCorrente", formatoData
                .format(dataCorrente.getTime()));
            }
        }

        // Grupo selecionado
        String grupoFaturamentoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getGrupoFaturamentoID();
        // Atividade selecionado
        String atividadeFaturamentoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getAtividadeFaturamentoID();

        if (grupoFaturamentoJSP != null
                && !grupoFaturamentoJSP.equalsIgnoreCase("")
                && !grupoFaturamentoJSP.equalsIgnoreCase(String
                        .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

            colecaoFaturamentoGrupo = (Collection) sessao
                    .getAttribute("colecaoGrupoFaturamento");

            FaturamentoGrupo faturamentoGrupo = obterFaturamentoGrupoSelecionado(
            grupoFaturamentoJSP, colecaoFaturamentoGrupo);

            // [FS0003] - Verificar exist�ncia do cronograma para o grupo
            //fachada.verificarExistenciaCronogramaGrupo(faturamentoGrupo);

            // Atividade de faturamento (Carregar cole��o)
            // [SB0001] - Selecionar atividade de faturamento
            colecaoAtividadeFaturamento = fachada
            .selecionarAtividadeFaturamentoQuePodeSerComandada(faturamentoGrupo);
            httpServletRequest.setAttribute("colecaoAtividadeFaturamento",
            colecaoAtividadeFaturamento);
        

            // O sistema apresenta na tela:

            //Refer�ncia Faturamento
            /*
             * A refer�ncia do faturamento (FTGR_AMREFERENCIA da tabela
             * FATURAMENTO_GRUPO para FTGR_ID=Grupo selecionado)
             */
            if (faturamentoGrupo.getAnoMesReferencia() != null) {
                
                inserirComandoAtividadeFaturamentoActionForm
                .setReferenciaFaturamento(Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia()
                .intValue()));
                
                String pesquisarAtividade = httpServletRequest.getParameter("pesquisa");

                //Data de vencimento do grupo
                /*
                 * 3.2. A data de vencimento do grupo (formatar a partir do m�s
                 * seguinte ao m�s de refer�ncia do faturamento e do dia de
                 * vencimento do grupo (FTGR_NNDIAVENCIMENTO da tabela
                 * FATURAMENTO_GRUPO para FTGR_ID=Grupo selecionado)) caso a
                 * atividade de faturamento selecionada corresponda � atividade
                 * faturar grupo, permitindo que seja alterada; caso contr�rio,
                 * este campo deve ser ocultado
                 */

                if (atividadeFaturamentoJSP != null
                    && !atividadeFaturamentoJSP.equalsIgnoreCase("")
                    && pesquisarAtividade != null && !pesquisarAtividade.equalsIgnoreCase("")) {

                    /*
                     * Alterado por Raphael Rossiter - Analista: Aryed Lins
                     * A data do vencimento ser� exibida caso a atividade tamb�m seja GERAR E ENVIAR ARQU. LEITURA
                     * (Pr�-Faturamento)
                     */
                	if ((atividadeFaturamentoJSP.equalsIgnoreCase(String.valueOf(FaturamentoAtividade.FATURAR_GRUPO)) ||
                		atividadeFaturamentoJSP.equalsIgnoreCase(String.valueOf(FaturamentoAtividade.GERAR_ARQUIVO_LEITURA)))
                        && faturamentoGrupo.getDiaVencimento() != null) {
                    	/** alterado por pedro alexandre dia 26/06/2007 */
                        /*int diaVencimento = faturamentoGrupo.getDiaVencimento()
                                .intValue();
                        String mesVencimento = String.valueOf(
                                faturamentoGrupo.getAnoMesReferencia()
                                        .intValue()).substring(4, 6);
                        String anoVencimento = String.valueOf(
                                faturamentoGrupo.getAnoMesReferencia()
                                        .intValue()).substring(0, 4);

                        
                         * Colocado por Raphael Rossiter em 05/05/2007
                         * Obtendo a data de vencimento do grupo
                          
                        Date dataVencimento = fachada.obterDataVencimentoFaturamentoGrupo(diaVencimento, 
                        new Integer(mesVencimento), new Integer(anoVencimento));*/

                    	//[UC0618] Obter Data de Vencimento do Grupo 
                    	Date dataVencimento = fachada.obterDataVencimentoGrupo(faturamentoGrupo.getId(), null);
                    	
                    	/** fim altera��o */
                    	
                        inserirComandoAtividadeFaturamentoActionForm
                        .setVencimentoGrupo(formatoData.format(dataVencimento));

                        sessao.setAttribute("exibirCampoVencimentoGrupo", formatoData
                        .format(dataVencimento));
                        
                        //Colocar o foco no campo vencimento do grupo
                    	httpServletRequest.setAttribute("nomeCampo", "vencimentoGrupo");
                    }
                    else{
                    	
                    	sessao.removeAttribute("exibirCampoVencimentoGrupo");
                    	
                    	//Colocar o foco no bot�o exibir rotas n�o habilitadas
                    	httpServletRequest.setAttribute("nomeCampo", "popupRotasNaoHabilitadas");
                    }

                    //Obter objeto FaturamentoAtividade a partir do ID
                    // -------------------
                    FiltroFaturamentoAtividade filtroFaturamentoAtividade = new FiltroFaturamentoAtividade();

                    filtroFaturamentoAtividade.setConsultaSemLimites(true);
                    
                    filtroFaturamentoAtividade
                            .adicionarParametro(new ParametroSimples(
                                    FiltroFaturamentoAtividade.ID,
                                    atividadeFaturamentoJSP));

                    filtroFaturamentoAtividade
                            .adicionarParametro(new ParametroSimples(
                                    FiltroFaturamentoAtividade.INDICADOR_USO,
                                    ConstantesSistema.INDICADOR_USO_ATIVO));

                    Collection colecaoFaturamentoAtividade = fachada.pesquisar(
                            filtroFaturamentoAtividade,
                            FaturamentoAtividade.class.getName());

                    FaturamentoAtividade faturamentoAtividade = (FaturamentoAtividade) Util
                            .retonarObjetoDeColecao(colecaoFaturamentoAtividade);
                    //--------------------------------------------------------------------

                    // [FS0008] - Verificar exist�ncia da atividade no
                    // cronograma do grupo do m�s corrente
                    //fachada.verificarExistenciaCronogramaAtividadeGrupo(
                    //        faturamentoAtividade, faturamentoGrupo);

                    // Lista as rotas "habilitadas" do grupo

                    // [FS0006] - Verificar exist�ncia de rotas para o grupo
                    Collection colecaoRotasGrupo = fachada
                            .verificarExistenciaRotaGrupo(faturamentoGrupo);

                    if (faturamentoAtividade != null) {
        				
	        			
	                    //[SB0002] - Verificar Situa��o da Atividade para a Rota
	                    // true = Rotas habilitadas
	                    Collection colecaoRotasSituacao = fachada
	                            .verificarSituacaoAtividadeRota(colecaoRotasGrupo,
	                                    faturamentoAtividade, faturamentoGrupo,
	                                    true);
	
	                    //[FS0007] - Verificar sele��o de pelo menos uma rota
	                    // habilitada
	                    if (colecaoRotasSituacao == null
	                            || colecaoRotasSituacao.isEmpty()) {
	                        throw new ActionServletException(
	                                "atencao.pesquisa.nenhuma.rota_habilitada_grupo");
	                    }
	                    
	                    // Passa a colecao de rotas habilitadas pelo request
	                    httpServletRequest.setAttribute("colecaoRotasHabilitadas",
	                            colecaoRotasSituacao);
                    }else {
                    	// Passa a colecao de rotas habilitadas pelo request
	                    httpServletRequest.setAttribute("colecaoRotasHabilitadas",
	                            null);
                    }
                    
                }
                else{
                	
                	inserirComandoAtividadeFaturamentoActionForm.setVencimentoGrupo("");
                	inserirComandoAtividadeFaturamentoActionForm.setAtividadeFaturamentoID(
                	String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
                	
                	//Colocar o foco no campo de atividades
                	httpServletRequest.setAttribute("nomeCampo", "atividadeFaturamentoID");
                }
            }

        } else {
            // Limpa o restante dos campos do formul�rio.
            inserirComandoAtividadeFaturamentoActionForm
                    .setReferenciaFaturamento("");
            inserirComandoAtividadeFaturamentoActionForm.setVencimentoGrupo("");
        }

        return retorno;
    }

    /**
     * Retorna o objeto FaturamentoGrupo selecionado
     * 
     * @param id
     * @param colecao
     * @return
     */
    private FaturamentoGrupo obterFaturamentoGrupoSelecionado(String id,
            Collection colecao) {
        FaturamentoGrupo retorno = null;
        Iterator colecaoIterator = colecao.iterator();

        while (colecaoIterator.hasNext()) {
            retorno = (FaturamentoGrupo) colecaoIterator.next();

            if (retorno.getId().equals(new Integer(id))) {
                break;
            }
        }

        return retorno;
    }

    /**
     * Retorna o objeto FaturamentoAtividade selecionado
     * 
     * @param id
     * @param colecao
     * @return
     */
    /*private FaturamentoAtividade obterFaturamentoAtividadeSelecionado(
            String id, Collection colecao) {
        FaturamentoAtividade retorno = null;
        Iterator colecaoIterator = colecao.iterator();

        while (colecaoIterator.hasNext()) {
            retorno = (FaturamentoAtividade) colecaoIterator.next();

            if (retorno.getId().equals(new Integer(id))) {
                break;
            }
        }

        return retorno;
    }*/

}