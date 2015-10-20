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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Tiago Moreno
 * @create 07/02/2006
 * 
 */
public class PesquisarMovimentoArrecadadorAction extends GcomAction {

public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarMovimentoArrecadadorResultado");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Recupera informa��es do formulario
		PesquisarMovimentoArrecadadorActionForm pesquisarMovimentoArrecadadorActionForm = (PesquisarMovimentoArrecadadorActionForm) actionForm;

	    String codigoBanco = null;
		String codigoRemessa = null;
		String descricaoIdentificacaoServico = null;
		String numeroSequencialArquivo = null;
		Date dataGeracaoInicio = null;
		Date dataGeracaoFim = null;
		Date ultimaAlteracaoInicio = null;
		Date ultimaAlteracaoFim = null;
		String descricaoOcorrencia = null;
		String indicadorAceitacao = null; 
		String indicadorAbertoFechado = null;
				
		boolean peloMenosUmParametroInformado = false;
		
		 if (pesquisarMovimentoArrecadadorActionForm.getIdBanco() != null &&
       	!pesquisarMovimentoArrecadadorActionForm.getIdBanco().equalsIgnoreCase("")){
       	
			 codigoBanco = pesquisarMovimentoArrecadadorActionForm.getIdBanco().trim();
       	
       	peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getTipoRemessa() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getTipoRemessa().equalsIgnoreCase("")){
           	
       	codigoRemessa = pesquisarMovimentoArrecadadorActionForm.getTipoRemessa().trim();
           	
           peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getIdentificacaoServico() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getIdentificacaoServico().equalsIgnoreCase("") &&
           !pesquisarMovimentoArrecadadorActionForm.getIdentificacaoServico().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
       	
       	descricaoIdentificacaoServico = pesquisarMovimentoArrecadadorActionForm.getIdentificacaoServico().trim();

       	peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getNumeroSequencialArquivo() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getNumeroSequencialArquivo().equalsIgnoreCase("")){
           	
       	numeroSequencialArquivo = pesquisarMovimentoArrecadadorActionForm.getNumeroSequencialArquivo().trim();
           	
           peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getDataMovimentoInicio() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getDataMovimentoInicio().equalsIgnoreCase("")){
   		
       	dataGeracaoInicio = Util.converteStringParaDate(pesquisarMovimentoArrecadadorActionForm.getDataMovimentoInicio());
       	dataGeracaoFim = dataGeracaoInicio;
       	
       	peloMenosUmParametroInformado = true;
		
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getDataMovimentoFim() != null &&
		        !pesquisarMovimentoArrecadadorActionForm.getDataMovimentoFim().equalsIgnoreCase("")){
			
       	dataGeracaoFim = Util.converteStringParaDate(pesquisarMovimentoArrecadadorActionForm.getDataMovimentoFim());
       	
       	peloMenosUmParametroInformado = true;
		}
       
              
       if (pesquisarMovimentoArrecadadorActionForm.getMovimentoItemOcorrencia() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getMovimentoItemOcorrencia().equalsIgnoreCase("") &&
           !pesquisarMovimentoArrecadadorActionForm.getMovimentoItemOcorrencia().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
           
       	descricaoOcorrencia = pesquisarMovimentoArrecadadorActionForm.getMovimentoItemOcorrencia().trim();
       	
           peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getMovimentoItemAceito() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getMovimentoItemAceito().equalsIgnoreCase("") &&
           !pesquisarMovimentoArrecadadorActionForm.getMovimentoItemAceito().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
           	
       	indicadorAceitacao = pesquisarMovimentoArrecadadorActionForm.getMovimentoItemAceito().trim();
       	
           peloMenosUmParametroInformado = true;
       }
       
              
       //Erro caso o usu�rio mandou filtrar sem nenhum par�metro
       if (!peloMenosUmParametroInformado) {
           throw new ActionServletException(
           "atencao.filtro.nenhum_parametro_informado");
       }
			
		
		//1� Passo - Pegar o total de registros atrav�s de um count da consulta
		// que aparecer� na tela
		Integer totalRegistros = fachada.filtrarMovimentoArrecadadoresCount
				(codigoBanco, codigoRemessa, descricaoIdentificacaoServico,
	    		 numeroSequencialArquivo, dataGeracaoInicio,
	    		 dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim,
	    		 descricaoOcorrencia, indicadorAceitacao,indicadorAbertoFechado);
        
		//2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,totalRegistros);
		
		//	3� Passo - Obter a cole��o da consulta que aparecer� na tela passando
		// o numero de paginas da pesquisa que est� no request
		Collection colecaoMovimentoArrecadador = fachada.filtrarMovimentoArrecadadorParaPaginacao(
				 codigoBanco, codigoRemessa, descricaoIdentificacaoServico,
	    		 numeroSequencialArquivo, dataGeracaoInicio,
	    		 dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim,
	    		 descricaoOcorrencia, indicadorAceitacao, 
	    		 (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"),indicadorAbertoFechado);
		
		
		
		// valida se a colecao esta vazia
		if (colecaoMovimentoArrecadador == null) {
			throw new ActionServletException("atencao.colecao_vazia");
		} else {
			if (!colecaoMovimentoArrecadador.isEmpty()) {
				// joga a colecao na sess�o
				sessao.setAttribute("colecaoMovimentoArrecadador",
					colecaoMovimentoArrecadador);
		} else {
			throw new ActionServletException("atencao.colecao_vazia");
			}
		}
		
		return retorno;
	}}