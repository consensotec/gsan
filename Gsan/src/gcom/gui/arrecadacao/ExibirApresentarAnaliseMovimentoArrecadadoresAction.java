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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadadorMovimento;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a an�lise do movimento dos arrecadadores e os avisos 
 * banc�rios associados 
 *
 * @author Raphael Rossiter
 *
 * @date 08/03/2006
 */
public class ExibirApresentarAnaliseMovimentoArrecadadoresAction extends GcomAction {
    
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirApresentarAnaliseMovimentoArrecadadores");
        
        String idArrecadadorMovimento = httpServletRequest.getParameter("arrecadadorMovimentoID");
        
        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        ApresentarAnaliseMovimentoArrecadadoresActionForm apresentarAnaliseMovimentoArrecadadoresActionForm = 
        (ApresentarAnaliseMovimentoArrecadadoresActionForm) actionForm;
        
        
        FiltroArrecadadorMovimento filtroArrecadadorMovimento = new FiltroArrecadadorMovimento();
        filtroArrecadadorMovimento.setConsultaSemLimites(true);
        
        filtroArrecadadorMovimento.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.ID,
        idArrecadadorMovimento));
        
        Collection colecaoArrecadadorMovimento = fachada.pesquisar(filtroArrecadadorMovimento,
        ArrecadadorMovimento.class.getName());
        
        ArrecadadorMovimento arrecadadorMovimento = (ArrecadadorMovimento) 
        Util.retonarObjetoDeColecao(colecaoArrecadadorMovimento);
        
        
        apresentarAnaliseMovimentoArrecadadoresActionForm.setCodigoNomeArrecadador(
        arrecadadorMovimento.getCodigoBanco() + " - " + arrecadadorMovimento.getNomeBanco());
        
        
        //1 - ENVIO  2 - RETORNO
        if (arrecadadorMovimento.getCodigoRemessa().equals(new Short(String.valueOf(ConstantesSistema.CODIGO_ENVIO)))){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setCodigoRemessa(
        	ConstantesSistema.ENVIO);
        	
        	sessao.removeAttribute("formaArrecadacao");
        }
        else if (arrecadadorMovimento.getCodigoRemessa().equals(new Short(String.valueOf(ConstantesSistema.CODIGO_RETORNO)))){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setCodigoRemessa(
            ConstantesSistema.RETORNO);
        	
        	sessao.setAttribute("formaArrecadacao",ConstantesSistema.RETORNO);
        	
        }
        
        if (arrecadadorMovimento.getDescricaoIdentificacaoServico() != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setIdentificacaoServico(
        	arrecadadorMovimento.getDescricaoIdentificacaoServico());
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setIdentificacaoServico("");
        }
        
        if (arrecadadorMovimento.getNumeroSequencialArquivo() != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNsa(
        	arrecadadorMovimento.getNumeroSequencialArquivo().toString());
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNsa("");
        }
        
        if (arrecadadorMovimento.getDataGeracao() != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setDataGeracao(
        	Util.formatarData(arrecadadorMovimento.getDataGeracao()));
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setDataGeracao("");
        }
        
        if (arrecadadorMovimento.getNumeroRegistrosMovimento() != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosMovimento(
        	arrecadadorMovimento.getNumeroRegistrosMovimento().toString());
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosMovimento("");
        }
        
        
        /* N�mero de registros em ocorr�ncia (n�mero de linhas da tabela ARRECADADOR_MOVIMENTO_ITEM com 
         * ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_DSOCORRENCIA diferente de "OK")
         */
        Integer numeroRegistrosOcorrencia = fachada.obterNumeroRegistrosEmOcorrenciaPorMovimentoArrecadadores(
        arrecadadorMovimento, ConstantesSistema.OK);
        
        if (numeroRegistrosOcorrencia != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosOcorrencia(
        	numeroRegistrosOcorrencia.toString());
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosOcorrencia("");
        }
        
        
        /*
         * N�mero de registros que n�o foram aceitos (n�mero de linhas da tabela 
         * ARRECADADOR_MOVIMENTO_ITEM com ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_ICACEITACAO 
         * igual a 2 (N�O))
         */
        Integer numeroRegistrosNaoAceitos = fachada.obterNumeroRegistrosNaoAceitosPorMovimentoArrecadadores(
        arrecadadorMovimento, ConstantesSistema.REGISTROS_NAO_ACEITOS);
                
         if (numeroRegistrosNaoAceitos != null){
             apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosNaoAceitos(
             numeroRegistrosNaoAceitos.toString());
         }else{
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosNaoAceitos("");
         }
        
        BigDecimal valorTotalMovimento = new BigDecimal("0.00");
         if (arrecadadorMovimento.getValorTotalMovimento() != null){
         	apresentarAnaliseMovimentoArrecadadoresActionForm.setValorTotalMovimento(
         	Util.formatarMoedaReal(arrecadadorMovimento.getValorTotalMovimento()));
         	valorTotalMovimento = arrecadadorMovimento.getValorTotalMovimento();
         }
         
         if (arrecadadorMovimento.getUltimaAlteracao() != null){
          	
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setDataProcessamento(
          	 Util.formatarData(arrecadadorMovimento.getUltimaAlteracao()));
        	 
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setHoraProcessamento(
        	 Util.formatarHoraSemData(arrecadadorMovimento.getUltimaAlteracao()));
        	 
         }else{
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setDataProcessamento("");
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setHoraProcessamento("");
        	 
         }
         
         
         
         /*
          * Caso o valor total do movimento (ARMV_VALORTOTALMOVIMENTO) seja diferente do valor da soma das
          * arrecada��es dos avisos banc�rios relacionados (ARMV_ID = ARMV_ID da tabela AVISO_BANCARIO e o campo
          * para totaliza��o ser� AVBC_VLARRECADACAO), a situa��o do movimento ser� "ABERTO".
          * Caso contr�rio a situa��o do movimento ser� "FECHADO"
          */
         String situacaoArrecadadorMovimento = fachada.obterSituacaoArrecadadorMovimento(
         arrecadadorMovimento);
         
         apresentarAnaliseMovimentoArrecadadoresActionForm.setSituacaoMovimento(situacaoArrecadadorMovimento);
         
         
         /*
          * Valor total dos avisos banc�rios de um determinado movimento (Total da soma do campo
          * AVBC_VALORARRECADACAO da tabela AVISO_BANCARIO com ARMV_ID = ARMV_ID da tabela 
          * ARRECADADOR_MOVIMENTO 
          */
         BigDecimal valorAvisosBancarios = fachada.obterTotalArrecadacaoAvisoBancarioPorMovimentoArrecadadores(
         arrecadadorMovimento);
         
         if (valorAvisosBancarios != null){
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setValorTotalAvisosBancarios(
        	 Util.formatarMoedaReal(valorAvisosBancarios));
         }else{
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setValorTotalAvisosBancarios("");
         }
         
         BigDecimal diferenca = new BigDecimal("0.00");
         if (valorAvisosBancarios != null){
        	 diferenca = valorTotalMovimento.subtract(valorAvisosBancarios);
         }else{
        	 diferenca = valorTotalMovimento;
         }
         apresentarAnaliseMovimentoArrecadadoresActionForm.setValordiferencaVlMovimentoVlAvisos(Util.formatarMoedaReal(diferenca));
         
         /*
          * Lista os avisos banc�rios associados ao movimento
          */
         Collection<AvisoBancarioHelper> colecaoAvisosBancariosPorMovimentoArrecadador = 
         fachada.obterColecaoAvisosBancariosPorArrecadadorMovimento(arrecadadorMovimento);
         
         httpServletRequest.setAttribute("colecaoAvisosBancariosPorMovimentoArrecadador",
         colecaoAvisosBancariosPorMovimentoArrecadador);
         
         BigDecimal valorTotalAviso = new BigDecimal("0.00");
         BigDecimal valorTotalArrecadacaoCalculado = new BigDecimal("0.00");
         BigDecimal valorTotalDevolucao = new BigDecimal("0.00");
         
         if (colecaoAvisosBancariosPorMovimentoArrecadador != null && !colecaoAvisosBancariosPorMovimentoArrecadador.isEmpty()){
        	 
        	 Iterator it = colecaoAvisosBancariosPorMovimentoArrecadador.iterator();
        	 
        	 while(it.hasNext()){
        		 
        		 AvisoBancarioHelper avisoBancarioHelper = (AvisoBancarioHelper) it.next();
        		 
        		 if (avisoBancarioHelper.getValorRealizado() != null){
        			 
        			 valorTotalAviso = valorTotalAviso.add(avisoBancarioHelper.getValorRealizado());
        		 }
        		 
        		 if (avisoBancarioHelper.getValorArrecadacao() != null){
        			 
        			 valorTotalArrecadacaoCalculado = valorTotalArrecadacaoCalculado.add(avisoBancarioHelper.getValorArrecadacao());
        		 }
        		 
        		 if (avisoBancarioHelper.getValorDevolucaoCalculado() != null){
        			 
        			 valorTotalDevolucao = valorTotalDevolucao.add(avisoBancarioHelper.getValorDevolucaoCalculado());
        		 }
        	 }
         }
         
         httpServletRequest.setAttribute("valorTotalAviso", valorTotalAviso);
         httpServletRequest.setAttribute("valorTotalArrecadacaoCalculado", valorTotalArrecadacaoCalculado);
         httpServletRequest.setAttribute("valorTotalDevolucao", valorTotalDevolucao);
         
         
         //Par�metro que ser� utilizado para montar o link para os itens do movimento
         httpServletRequest.setAttribute("idArrecadadorMovimento", idArrecadadorMovimento);
         
         
        return retorno;
    }
}
