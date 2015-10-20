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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da inser��o de um Comando de Negativa��o (Aba n� 02 - Dados do D�bito) 
 *
 * @author Ana Maria	
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoDadosDebitoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirComandoNegativacaoDadosDebito");
        
        Fachada fachada = Fachada.getInstancia();
        
        InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
        
  	    //Pesquisar Sistema Parametro 
  	    FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
  			
  	   Collection<SistemaParametro> collectionSistemaParametro = fachada
  					.pesquisar(filtroSistemaParametro,
  							SistemaParametro.class.getName());
  	    SistemaParametro sistemaParametro = (SistemaParametro) collectionSistemaParametro
  					.iterator().next();
  	  
  	    //Refer�ncia do D�bito Final
  	    if(inserirComandoNegativacaoActionForm.getReferenciaFinal() == null ||
  	    		inserirComandoNegativacaoActionForm.getReferenciaFinal().equals("")){
	  	    String anoMesArrecadacao = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao());
	  	    inserirComandoNegativacaoActionForm.setReferenciaFinal(anoMesArrecadacao);
  	    }
  	    //Data do Vencimento Final
  	    if(inserirComandoNegativacaoActionForm.getDataVencimentoFinal() == null || 
  	    		inserirComandoNegativacaoActionForm.getDataVencimentoFinal().equals("")){
			//Per�odo de vencimento do d�bito	
			Integer numeroDiasVencimentoCobranca = new Integer(sistemaParametro.getNumeroDiasVencimentoCobranca());			
			Date dataVencimentoFinal = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca);
			Date dataVencimentoInicial = Util.subtrairNumeroAnosDeUmaData(dataVencimentoFinal, -5);
			inserirComandoNegativacaoActionForm.setDataVencimentoInicial(Util.formatarData(dataVencimentoInicial));
			inserirComandoNegativacaoActionForm.setDataVencimentoFinal(Util.formatarData(dataVencimentoFinal));
			/*Date dataAtualMenosMes = Util.adcionarOuSubtrairMesesAData(new Date(), -1, 0);
	  	    int mesData = Util.getMes(dataAtualMenosMes);
	  	    int anoData = Util.getAno(dataAtualMenosMes);  	    
	  	    String dataVencimentoFinal = Util.obterUltimoDiaMes(mesData, anoData)+ "/"+ mesData + "/" + anoData;
	  	    inserirComandoNegativacaoActionForm.setDataVencimentoFinal(dataVencimentoFinal);*/
  	    }
  	    
        if(inserirComandoNegativacaoActionForm.getContasRevisao() == null){
        	//Considerar Conta em Revis�o - exibir com op��o "N�o" selecionada    		
        	inserirComandoNegativacaoActionForm.setContasRevisao(ConstantesSistema.NAO_CONFIRMADA);   
        }
	
        if(inserirComandoNegativacaoActionForm.getGuiasPagamento() == null){
        	//Considerar Guias de Pagamento - exibir com op��o "N�o" selecionada    		
        	inserirComandoNegativacaoActionForm.setGuiasPagamento(ConstantesSistema.NAO_CONFIRMADA);   
        }

        if(inserirComandoNegativacaoActionForm.getParcelaAtraso() == null){
        	//Parcela em Atraso - exibir com op��o "N�o" selecionada    		
        	inserirComandoNegativacaoActionForm.setParcelaAtraso(ConstantesSistema.NAO_CONFIRMADA);   
        }
        
        if(inserirComandoNegativacaoActionForm.getCartaParcelamentoAtraso() == null){
        	//Recebeu Carta de Parcelamento em Atraso - exibir com op��o "N�o" selecionada    		
        	inserirComandoNegativacaoActionForm.setCartaParcelamentoAtraso(ConstantesSistema.NAO_CONFIRMADA);   
        }

        if(inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente() == null){
        	//Exigir ao Menos uma Conta em Nome do Cliente Negativado  - exibir com op��o "Sim" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorContaNomeCliente(ConstantesSistema.CONFIRMADA);   
        }
	
        
		// Data Corrente
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));
    		
    	return retorno;
    }

}
