/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa a segunda p�gina do processo de inserir pagamentos 
 * se o tipo de inclus�o for por leitura de c�digo de barras 
 * Este action tamb�m � respons�vel por inserir ou remover um documento na lista de c�digos de barra
 * e gerar os pagamento para cada c�digo de barra informado por leitura �ptica ou por digita��o 
 * 
 * [UC0971] Inserir Pagamentos para Faturas Especiais
 * 
 * @author 	Vivianne Sousa
 * @created	21/12/2009
 */
public class ExibirInserirPagamentosFaturasEspeciaisTipoInclusaoCanetaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //Seta o mapeamento de retorno de retorno para po iniserir pagamento por caneta 
        ActionForward retorno = actionMapping.findForward("inserirPagamentosFaturasEspeciaisTipoInclusaoCaneta");

        //Recupera o form de pagamento
        PagamentosFaturasEspeciaisActionForm pagamentoActionForm = (PagamentosFaturasEspeciaisActionForm) actionForm;
        
        //Cria uma inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //Cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Verifica se o usu�rio selecionou um documento para remo��o da cole�a�de c�dgos de barra
        String codigoBarraRemocao = httpServletRequest.getParameter("codigoBarraRemocao");
        
        //Recupera a data do pagamento e verifica se a data � uma data v�lida
        //Caso contr�rio levanta uma exce��o para o usu�rio indicando que a data de
        //pagamento n�o � uma data v�lida
        String dataPagamentoString = pagamentoActionForm.getDataPagamento();
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataFormato.parse(dataPagamentoString);
        } catch (ParseException ex) {
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //Recupera os par�metros do sistemas 
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        //Recupera a cole��o dos objetos que armazenam as informa��es necess�rias dos c�digos de barra
        Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper = (Collection<InserirPagamentoViaCanetaHelper>) sessao.getAttribute("colecaoInserirPagamentoViaCanetaHelper");
        
        //Se nenhum documento de c�digo de barra na sess�o
        if(colecaoInserirPagamentoViaCanetaHelper == null ){
        	//Inst�ncia a cole��o de documentos
        	colecaoInserirPagamentoViaCanetaHelper = new ArrayList();
        	
        	//Caso exista documentos de c�digo de barra na sess�o
        }else if(!colecaoInserirPagamentoViaCanetaHelper.isEmpty()){
        	
        	//Verifica se o usu�rio indicou um documento para ser removido da cole��o
        	if(codigoBarraRemocao != null && !codigoBarraRemocao.trim().equalsIgnoreCase("")){
        		
        		Iterator iteratorRemocaoCodigoBarra = colecaoInserirPagamentoViaCanetaHelper.iterator();
        		
        		//La�o para encontrar o documento para ser removido
        		lacoWhile : while(iteratorRemocaoCodigoBarra.hasNext()){
        			//Recupera o documento do iterator
        			InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelperParaRemocao = (InserirPagamentoViaCanetaHelper) iteratorRemocaoCodigoBarra.next();
        			
        			//Se o c�digo de barra indicado pelo usu�rio for igual ao 
        			//c�digo de barra do documento
        			if(inserirPagamentoViaCanetaHelperParaRemocao.getCodigoBarra().equalsIgnoreCase(codigoBarraRemocao)){
        				
        				//Remove o documento e termina o la�o
        				iteratorRemocaoCodigoBarra.remove();
        				
        				break lacoWhile;
        			}
        		}
        	}
        }
        
        //Cria a vari�vel que vai armazenar o valor do pagamento
        BigDecimal valorPagamento = null;
        
        //Cria a vari�vel que vai armazenar o c�digo de barras
        String codigoBarra = null;
        
        //Cria as vari�veis que v�o armazenar o c�digo de barra separado por campos
        //e seus respectivos d�gitos verificadores se existirem
        String codigoBarraDigitadoCampo1 = null;
        String codigoBarraDigitadoDigitoVerificadorCampo1 = null;
        String codigoBarraDigitadoCampo2 = null; 
        String codigoBarraDigitadoDigitoVerificadorCampo2 = null;
        String codigoBarraDigitadoCampo3 = null;
        String codigoBarraDigitadoDigitoVerificadorCampo3 = null;
        String codigoBarraDigitadoCampo4 = null;
        String codigoBarraDigitadoDigitoVerificadorCampo4 = null;
        
        
        //Verifica se o c�digo de barras foi lido por caneta �ptica
        String codigoBarraLeituraOtica = pagamentoActionForm.getCodigoBarraPorLeituraOtica();
        
        //Caso o c�digo de barras foi indicado por leitura �tica, recupera todos os campos do c�digo de barra
        //Caso contr�rio recupera os campos de c�digo de barra digitado e seus d�gitos verificadores
        if(codigoBarraLeituraOtica != null && !codigoBarraLeituraOtica.equalsIgnoreCase("")){
        	codigoBarraDigitadoCampo1                  = codigoBarraLeituraOtica.substring(0,11);
        	codigoBarraDigitadoCampo2                  = codigoBarraLeituraOtica.substring(11,22);
        	codigoBarraDigitadoCampo3                  = codigoBarraLeituraOtica.substring(22,33);
        	codigoBarraDigitadoCampo4                  = codigoBarraLeituraOtica.substring(33,44);

        }else{
        	codigoBarraDigitadoCampo1                  = pagamentoActionForm.getCodigoBarraDigitadoCampo1();
        	codigoBarraDigitadoDigitoVerificadorCampo1 = pagamentoActionForm.getCodigoBarraDigitadoDigitoVerificadorCampo1();
        	codigoBarraDigitadoCampo2                  = pagamentoActionForm.getCodigoBarraDigitadoCampo2();
        	codigoBarraDigitadoDigitoVerificadorCampo2 = pagamentoActionForm.getCodigoBarraDigitadoDigitoVerificadorCampo2();
        	codigoBarraDigitadoCampo3                  = pagamentoActionForm.getCodigoBarraDigitadoCampo3();
        	codigoBarraDigitadoDigitoVerificadorCampo3 = pagamentoActionForm.getCodigoBarraDigitadoDigitoVerificadorCampo3();
        	codigoBarraDigitadoCampo4                  = pagamentoActionForm.getCodigoBarraDigitadoCampo4();
        	codigoBarraDigitadoDigitoVerificadorCampo4 = pagamentoActionForm.getCodigoBarraDigitadoDigitoVerificadorCampo4();
        }
        
        //Monta o c�digo de barra sem os d�gitos verificadores
        codigoBarra = codigoBarraDigitadoCampo1 + 
    				  codigoBarraDigitadoCampo2 +
    				  codigoBarraDigitadoCampo3 +
    				  codigoBarraDigitadoCampo4 ;
        
        //Se o c�digo de barra n�o estiver vazio e o tamanho for igual a 44(quarenta e quatro)
        if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.trim().length() == 44){
        	
        	
          //Caso o usu�rio n�o tenha informado a remo��o de c�digo de barra	
          if(codigoBarraRemocao == null || codigoBarraRemocao.trim().equalsIgnoreCase("")){	
        	
        	//Caso o c�digo de barra tenha sido informado por digita��o 
            if(codigoBarraLeituraOtica == null || codigoBarraLeituraOtica.equalsIgnoreCase("")){	
              //[FS0003] E [FS0005] - Validar d�gito verificador do c�digo de barra
              this.validarDigitoVerificador(codigoBarraDigitadoCampo1,codigoBarraDigitadoDigitoVerificadorCampo1,codigoBarraDigitadoCampo2,codigoBarraDigitadoDigitoVerificadorCampo2,codigoBarraDigitadoCampo3,codigoBarraDigitadoDigitoVerificadorCampo3,codigoBarraDigitadoCampo4,codigoBarraDigitadoDigitoVerificadorCampo4);
            }
          
            //Caso o c�digo de barra j� tenha sido informado pelo usu�rio e esteja na lista de documentos
            //[FS0004] - Verificar exist�ncia do documento na lista 
            if(this.verificarExistenciaDocumentoNaLista(codigoBarra, colecaoInserirPagamentoViaCanetaHelper)){
              //Levanta a exce��o para o usu�rio indicando que o documento j� foi informado	
        	  throw new ActionServletException("atencao.documento_informado");
            }else{
            	
            	//[SB0001] Processar fatura com codigo de barras
            	PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = fachada.
            		processarFaturaComCodigoBarras(codigoBarra,sistemaParametro);
            	
        	  //Recupera a descri��o a ocorr�ncia do movimento
         	  String descricaoOcorrenciaMovimento = pagamentoHelperCodigoBarras.getDescricaoOcorrencia();
         	  
         	  //Recupera o inidicador de aceita��o do registro do movimento
        	  Integer indicadorAceitacaoRegistroMovimento = Integer.parseInt(pagamentoHelperCodigoBarras.getIndicadorAceitacaoRegistro());        	

        	  //Caso o inidicador de aceita��o do registro do movimento seja igual a 1 (SIM) e
        	  //a descri��o a ocorr�ncia do movimento seja igual a "OK"
        	  //Caso contr�rio levanta uma exce��o com a descri��o da ocorr�ncia do movimento
        	  if(indicadorAceitacaoRegistroMovimento == 1 && descricaoOcorrenciaMovimento.equalsIgnoreCase("OK")){
        		
        		//Cria o documento para o c�digo de barras informado pelo usu�rio
                InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelper = new InserirPagamentoViaCanetaHelper();  
        		
        		//Chama o caso de uso [UC0264] para recuperar todos os dados contidos no c�digode barras
        		RegistroHelperCodigoBarras distribuirDadosCodigoBarras = fachada.distribuirDadosCodigoBarras(codigoBarra);

        		//Recupera o valor do pagamento do c�digo de barra 
        		valorPagamento = (Util.formatarMoedaRealparaBigDecimal(distribuirDadosCodigoBarras.getValorPagamento())).divide(new BigDecimal("100.00"));
        		
                inserirPagamentoViaCanetaHelper.setCodigoBarra(codigoBarraDigitadoCampo1 + codigoBarraDigitadoCampo2 + codigoBarraDigitadoCampo3 + codigoBarraDigitadoCampo4);
                inserirPagamentoViaCanetaHelper.setValorPagamento(valorPagamento);
                inserirPagamentoViaCanetaHelper.setRegistroHelperCodigoBarras(distribuirDadosCodigoBarras);
                
                //Adiciona o documento na cole��o
                colecaoInserirPagamentoViaCanetaHelper.add(inserirPagamentoViaCanetaHelper);
                
                //Limpa os c�digos de barras do form
                pagamentoActionForm.setCodigoBarraDigitadoCampo1("");
            	pagamentoActionForm.setCodigoBarraDigitadoDigitoVerificadorCampo1("");
            	pagamentoActionForm.setCodigoBarraDigitadoCampo2("");
            	pagamentoActionForm.setCodigoBarraDigitadoDigitoVerificadorCampo2("");
            	pagamentoActionForm.setCodigoBarraDigitadoCampo3("");
            	pagamentoActionForm.setCodigoBarraDigitadoDigitoVerificadorCampo3("");
            	pagamentoActionForm.setCodigoBarraDigitadoCampo4("");
            	pagamentoActionForm.setCodigoBarraDigitadoDigitoVerificadorCampo4("");
            	pagamentoActionForm.setCodigoBarraPorLeituraOtica("");  
        	  }else{
        		throw new ActionServletException("atencao.descricao_ocorrencia_movimento",null,descricaoOcorrenciaMovimento);
        	  }
            }
          }
        }
        
        
        //Seta na sess�o a cole��o de documento de c�digos de barra informados e a cole��o de pagamentos gerados
        sessao.setAttribute("colecaoInserirPagamentoViaCanetaHelper",colecaoInserirPagamentoViaCanetaHelper);
        
        //Retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }
    
    /**
     * Verifica se o c�digode barras informado pelo usu�rio j� est� contido na lista de documentos
     *
     * [FS0004] Verificar exist�ncia do documento na lista
     */
    private boolean verificarExistenciaDocumentoNaLista(String codigoBarra, Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper){
    	
    	//Flag para indicar se o c�digode barras j� est� contido na lista de documentos
    	boolean retorno = false;
    	
    	//Caso a lista de documentos n�o esteja vazia
    	if(colecaoInserirPagamentoViaCanetaHelper != null && !colecaoInserirPagamentoViaCanetaHelper.isEmpty()){
    		
    		//La�o para verificar se o documento j� esta na cole��o
    		loopPagamento : for(InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelper : colecaoInserirPagamentoViaCanetaHelper){
    			//Caso o c�digo de barras do documento seja igual ao informado pelo usu�rio
    			if(inserirPagamentoViaCanetaHelper.getCodigoBarra().equals(codigoBarra)){
    				//Indica que o documento j� existe na cole��o
    				retorno = true;
    				
    				//Para o loop
    				break loopPagamento;
    			}
    		}
    	}
    	
    	//Retorna a flag indicando se o documento est� na cole��o
    	return retorno;
    }
    
    /**
     * Valida o c�digo de barras, caso esse tenha sido digitado com seus  d�gitos verificadores
     *
     * [FS0005] Validar D�gito verificador do C�digo de Barras 
     */
    private void validarDigitoVerificador(String campo1,String dvCampo1, String campo2,String dvCampo2,String campo3,String dvCampo3,String campo4,String dvCampo4){
    	
    	//Cria as vari�veis quevai armazenar os d�gitos verificadores do m�dulo 11 e 10 respectivamente
    	String dvModulo11 = null;
    	String dvModulo10 = null; 
    	
    	//Caso a terceira posi��o do primeiro campo do c�digo de barras for igual a 6(seis)
    	//Obt�m o digito verificador para o modulo 10(dez)
    	if((campo1.substring(2,3)).equals("6")){
    	  dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(campo1)).toString();
    	  if(!dvModulo10.equals(dvCampo1)){
    		  throw new ActionServletException("atencao.digitoverificador_invalido");  
    	  }
    	  
    	  dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(campo2)).toString();
    	  if(!dvModulo10.equals(dvCampo2)){
    		  throw new ActionServletException("atencao.digitoverificador_invalido");
    	  }
    	  
    	  dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(campo3)).toString();
    	  if(!dvModulo10.equals(dvCampo3)){
    		  throw new ActionServletException("atencao.digitoverificador_invalido");
    	  }
    	  
    	  dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(campo4)).toString();
    	  if(!dvModulo10.equals(dvCampo4)){
    		  throw new ActionServletException("atencao.digitoverificador_invalido");
    	  }
    	  
    	//Caso a terceira posi��o do primeiro campo do c�digo de barras for igual a 8(oito)
      	//Obt�m o digito verificador para o modulo 11(onze)
    	}else if((campo1.substring(2,3)).equals("8")){
    		dvModulo11 = Util.obterDigitoVerificadorModulo11(new Long(campo1)).toString();
    		if(!dvModulo11.equals(dvCampo1)){
    			throw new ActionServletException("atencao.digitoverificador_invalido");
        	  }
    		
    		dvModulo11 = Util.obterDigitoVerificadorModulo11(new Long(campo2)).toString();
    		if(!dvModulo11.equals(dvCampo2)){
    			throw new ActionServletException("atencao.digitoverificador_invalido");
        	  }

    		dvModulo11 = Util.obterDigitoVerificadorModulo11(new Long(campo3)).toString();
    		if(!dvModulo11.equals(dvCampo3)){
    			throw new ActionServletException("atencao.digitoverificador_invalido");
        	  }

    		dvModulo11 = Util.obterDigitoVerificadorModulo11(new Long(campo4)).toString();
    		if(!dvModulo11.equals(dvCampo4)){
    			throw new ActionServletException("atencao.digitoverificador_invalido");
        	  }

    		//Caso a terceira posi��o do primeiro campo do c�digo de barras n�o for igual a 6(seis)
    		//ou n�o for igual a 8(oito), levanta uma exce��o indicando que a indica��o do m�dulo � inv�lida
    	}else{
    		throw new ActionServletException("atencao.indicacaomodulo_invalido");
    	}
    }    
}
