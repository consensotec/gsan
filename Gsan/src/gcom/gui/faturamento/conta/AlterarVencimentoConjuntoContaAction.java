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
package gcom.gui.faturamento.conta;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AlterarVencimentoConjuntoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirManterConjuntoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Inst�ncia do formul�rio que est� sendo utilizado
        AlterarVencimentoContaActionForm alterarVencimentoContaActionForm = (AlterarVencimentoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        //Data de vencimento
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
        Date dataVencimentoConta;
        
        try{
        	dataVencimentoConta = formatoData.parse(alterarVencimentoContaActionForm.getDataVencimento());
        } catch (ParseException ex) {
        	dataVencimentoConta = null;
        }
        
        
        if (sessao.getAttribute("colecaoImovel") != null){
        
        	Collection colecaoImovel = (Collection) sessao.getAttribute("colecaoImovel");        	
             
        	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			if (dataVencimentoConta != null
					&& Util.getAno(dataVencimentoConta) < Util.obterAno(sistemaParametro
							.getAnoMesFaturamento())) {
				throw new ActionServletException(
						"atencao.data_vencimento_conta_inferior_ano_faturamento");
			}
        	
        	Integer anoMes = null;
        	if(sessao.getAttribute("anoMes") != null){
        	  anoMes = (Integer)sessao.getAttribute("anoMes");	
        	}
        	
        	Integer anoMesFim = null;
        	if(sessao.getAttribute("anoMesFim") != null){
        	  anoMesFim = (Integer)sessao.getAttribute("anoMesFim");	
        	}
        	
        	String[] bancos = null;
        	if(sessao.getAttribute("bancos") != null){
        		bancos = (String[]) sessao.getAttribute("bancos");
        	}
        	
        	Date dataVencimentoContaInicio = null;
    		Date dataVencimentoContaFim = null;
    		String indicadorContaPaga = null;
    		Integer idGrupoFaturamento = null;
    		
    		if (sessao.getAttribute("dataVencimentoContaInicial") != null){
    			
    			dataVencimentoContaInicio = (Date) sessao.getAttribute("dataVencimentoContaInicial"); 
    		}
    		
    		if (sessao.getAttribute("dataVencimentoContaFinal") != null){
    			
    			dataVencimentoContaFim = (Date) sessao.getAttribute("dataVencimentoContaFinal");
    		}
    		
    		if (sessao.getAttribute("indicadorContaPaga") != null){
    			
    			indicadorContaPaga = (String) sessao.getAttribute("indicadorContaPaga");
    		}
    		
    		if (sessao.getAttribute("idGrupoFaturamento") != null){
    			
    			idGrupoFaturamento = (Integer) sessao.getAttribute("idGrupoFaturamento");
    		}
        	
    		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    		
    		if(usuarioLogado != null && (usuarioLogado.getDescricaoEmail() == null || usuarioLogado.getDescricaoEmail().equals(""))){
    			throw new ActionServletException(
    					"atencao.cadastro_email_usuario_obrigatorio");
    		}
            
            Integer codigoClienteSuperior = null;
            if(sessao.getAttribute("codigoClienteSuperior") != null){
                codigoClienteSuperior = new Integer( (String) sessao.getAttribute("codigoClienteSuperior"));
            }    		
       	
        	Integer codigoCliente = null;
    		if( codigoClienteSuperior == null && sessao.getAttribute("codigoCliente") != null){
    			codigoCliente = new Integer( (String) sessao.getAttribute("codigoCliente"));                
    		}            
            
    		// -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 19/08/2010 
			// Analista :  Aryed Lins.
        	// [FS0007] - Validar data de vencimento.		
			// -------------------------------------------------------------------------------------------
    		
    		Date dataCorrente = new Date();	
    		
    		Integer diasAdicionais = 0;
        	
        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
        	}
    		
    		Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dataCorrente, diasAdicionais.intValue());
    		// E o usu�rio n�o tenha permiss�o especial.	
			boolean temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao = fachada.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_DATA_VENCIMENTO_ALEM_PRAZO_PADRAO,
					usuarioLogado);		
				
			//	1 se a dataVencimentoConta for maior que a dataCorrenteComDias.
			if(Util.compararData(dataVencimentoConta, dataCorrenteComDias) == 1 && temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao == false){
				throw new ActionServletException("atencao.necessario_permissao_especial_para_data_vencimento_posterior_permitido");
			}
			
	      // if(!temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao){
		  //				
	      // 	if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
		  //		Date dataUltimoDiaMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente), Util.getAno(dataCorrente));
		  //		        	
		  //		if(Util.compararData(dataVencimentoConta, dataUltimoDiaMes) == 1){
		  //			dataVencimentoConta = dataUltimoDiaMes;
		  //		}	
		  //	}
		  // }
		  // -------------------------------------------------------------------------------------------
 
            if(codigoCliente != null || codigoClienteSuperior != null ){
        	  
    			fachada.alterarVencimentoConjuntoContaCliente(codigoCliente, null, dataVencimentoConta, anoMes,
        		null, null, anoMesFim, usuarioLogado, codigoClienteSuperior );	        		
        	}
        	else if (idGrupoFaturamento != null){
        		
        		fachada.alterarVencimentoConjuntoConta(idGrupoFaturamento, dataVencimentoConta, anoMes, anoMesFim,
                dataVencimentoContaInicio, dataVencimentoContaFim,usuarioLogado);
        	}
        	else{
              
        		fachada.alterarVencimentoConjuntoConta(colecaoImovel, dataVencimentoConta, anoMes,
            	dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim, usuarioLogado, indicadorContaPaga,
            	bancos);	
        	}
        	
        	//Realizar um reload na tela de manter conjunto conta
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        }
              
        sessao.removeAttribute("anoMes");
        sessao.removeAttribute("anoMesFim");
        sessao.removeAttribute("dataVencimentoContaInicial");
        sessao.removeAttribute("dataVencimentoContaFinal");
        sessao.removeAttribute("indicadorContaPaga");
        
        return retorno;
    }

}

