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
package gsan.gui.arrecadacao.pagamento;

import gsan.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gsan.arrecadacao.bean.RegistroHelperFichaCompensacao;
import gsan.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * @author 	Raphael Rossiter
 * @date	25/11/2010
 */
public class ExibirInserirPagamentosTipoInclusaoFichaCompensacaoAction extends GcomAction {

    
	/**
     * Inserir pagamentos no sistema
     *
     * [UC0265] Inserir Pagamentos
     *
     * @author Raphael Rossiter 
     * @date 26/11/2010
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //Seta o mapeamento de retorno de retorno para po iniserir pagamento por caneta 
        ActionForward retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoFichaCompensacao");

        //Recupera o form de pagamento
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;
        
        //Cria uma inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //Cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Usuario Logado
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //Verifica se o usu�rio selecionou um documento para remo��o da cole�a�de c�dgos de barra
        String codigoBarraRemocao = httpServletRequest.getParameter("codigoBarraRemocao");
        
        //Recupera a data do pagamento e verifica se a data � uma data v�lida
        //Caso contr�rio levanta uma exce��o para o usu�rio indicando que a data de
        //pagamento n�o � uma data v�lida
        String dataPagamentoString = (String)pagamentoActionForm.get("dataPagamento");
        Date dataPagamento = null;
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataPagamento = dataFormato.parse(dataPagamentoString);
        } catch (ParseException ex) {
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //Recupera o c�digo da forma de arrecada��o
        String idFormaArrecadacao = (String)pagamentoActionForm.get("idFormaArrecadacao");
        
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
        String codigoBarraDigitadoCampo1Parte1 = null;
        String codigoBarraDigitadoCampo1Parte2 = null;
        
        String codigoBarraDigitadoCampo2 = null;
        String codigoBarraDigitadoCampo2Parte1 = null;
        String codigoBarraDigitadoCampo2Parte2 = null;
        
        String codigoBarraDigitadoCampo3 = null;
        String codigoBarraDigitadoCampo3Parte1 = null;
        String codigoBarraDigitadoCampo3Parte2 = null;
        
        String codigoBarraDigitadoDigitoVerificador = null;
        
        String codigoBarraDigitadoCampo4 = null;
        
        
        //Verifica se o c�digo de barras foi lido por caneta �ptica
        String codigoBarraLeituraOtica = (String)pagamentoActionForm.get("codigoBarraPorLeituraOtica");
        
        //Caso o c�digo de barras foi indicado por leitura �tica, recupera todos os campos do c�digo de barra
        //Caso contr�rio recupera os campos de c�digo de barra digitado e seus d�gitos verificadores
        if(codigoBarraLeituraOtica != null && !codigoBarraLeituraOtica.equalsIgnoreCase("")){
        	
        	//PRIMEIRO QUADRANTE
        	/*codigoBarraDigitadoCampo1Parte1            			= codigoBarraLeituraOtica.substring(0,5);
        	codigoBarraDigitadoCampo1Parte2						= codigoBarraLeituraOtica.substring(5,10);
        	
        	codigoBarraDigitadoCampo1							= codigoBarraDigitadoCampo1Parte1 + codigoBarraDigitadoCampo1Parte2;
        	
        	//SEGUNDO QUADRANTE
        	codigoBarraDigitadoCampo2Parte1            			= codigoBarraLeituraOtica.substring(10,15);
        	codigoBarraDigitadoCampo2Parte2						= codigoBarraLeituraOtica.substring(15,21);
        	
        	codigoBarraDigitadoCampo2							= codigoBarraDigitadoCampo2Parte1 + codigoBarraDigitadoCampo2Parte2;
        	
        	//TERCEIRO QUADRANTE
        	codigoBarraDigitadoCampo3Parte1            			= codigoBarraLeituraOtica.substring(21,26);
        	codigoBarraDigitadoCampo3Parte2						= codigoBarraLeituraOtica.substring(26,32);
        	
        	codigoBarraDigitadoCampo3							= codigoBarraDigitadoCampo3Parte1 + codigoBarraDigitadoCampo3Parte2;
        	
        	//D�GITO VERIFICADOR DO M�DULO 11
        	codigoBarraDigitadoDigitoVerificador				= codigoBarraLeituraOtica.substring(32,33);
        	
        	//FATOR DE VENCIMENTO E VALOR DO T�TULO
        	codigoBarraDigitadoCampo4							= codigoBarraLeituraOtica.substring(33,47);*/
        	
        	codigoBarra = fachada.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(codigoBarraLeituraOtica);
        	codigoBarra = codigoBarra.replace(".", "");
        	codigoBarra = codigoBarra.replace(" ", "");

        }
        else{
        	
        	//PRIMEIRO QUADRANTE
        	codigoBarraDigitadoCampo1Parte1            			= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo1Parte1");
        	
        	if (pagamentoActionForm.get("codigoBarraDigitadoCampo1Parte2") != null &&
        		((String)pagamentoActionForm.get("codigoBarraDigitadoCampo1Parte2")).length() == 5){
        		
        		codigoBarraDigitadoCampo1Parte2					= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo1Parte2");
            	
            	codigoBarraDigitadoCampo1						= codigoBarraDigitadoCampo1Parte1 + codigoBarraDigitadoCampo1Parte2;
        	}
        	else{
        		
        		codigoBarraDigitadoCampo1Parte2					= "";
            	
            	codigoBarraDigitadoCampo1						= "";
        	}
        	
        	
        	//SEGUNDO QUADRANTE
        	codigoBarraDigitadoCampo2Parte1            			= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo2Parte1");
        	
        	if (pagamentoActionForm.get("codigoBarraDigitadoCampo2Parte2") != null &&
            	((String)pagamentoActionForm.get("codigoBarraDigitadoCampo2Parte2")).length() == 6){
            		
        		codigoBarraDigitadoCampo2Parte2					= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo2Parte2");
            	
            	codigoBarraDigitadoCampo2						= codigoBarraDigitadoCampo2Parte1 + codigoBarraDigitadoCampo2Parte2;
            }
            else{
            		
            	codigoBarraDigitadoCampo2Parte2					= "";
            	
            	codigoBarraDigitadoCampo2						= "";
            }
        	
        	
        	//TERCEIRO QUADRANTE
        	codigoBarraDigitadoCampo3Parte1            			= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo3Parte1");
        	
        	if (pagamentoActionForm.get("codigoBarraDigitadoCampo3Parte2") != null &&
            	((String)pagamentoActionForm.get("codigoBarraDigitadoCampo3Parte2")).length() == 6){
            		
        		codigoBarraDigitadoCampo3Parte2					= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo3Parte2");
            	
            	codigoBarraDigitadoCampo3						= codigoBarraDigitadoCampo3Parte1 + codigoBarraDigitadoCampo3Parte2;
            }
            else{
            		
            	codigoBarraDigitadoCampo3Parte2					= "";
            	
            	codigoBarraDigitadoCampo3						= "";
            }
        	
        	//D�GITO VERIFICADOR DO M�DULO 11
        	codigoBarraDigitadoDigitoVerificador				= (String)pagamentoActionForm.get("codigoBarraDigitadoDigitoVerificador");
        	
        	//FATOR DE VENCIMENTO E VALOR DO T�TULO
        	codigoBarraDigitadoCampo4							= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo4");
        	
        	//Monta o c�digo de barra sem os d�gitos verificadores
            codigoBarra = codigoBarraDigitadoCampo1 +
        				  codigoBarraDigitadoCampo2 +
        				  codigoBarraDigitadoCampo3 +
        				  codigoBarraDigitadoDigitoVerificador +
        				  codigoBarraDigitadoCampo4 ;
        }
        
        
        
        //Se o c�digo de barra n�o estiver vazio e o tamanho for igual a 44(quarenta e quatro)
        if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.trim().length() == 47){
        	
        	
          //Caso o usu�rio n�o tenha informado a remo��o de c�digo de barra	
          if(codigoBarraRemocao == null || codigoBarraRemocao.trim().equalsIgnoreCase("")){	
        	
        	RegistroHelperFichaCompensacao codigoBarraFichaCompensacaoHelper = fachada.distribuirDadosFichaCompensacao(codigoBarra);
        	
        	StringBuilder nossoNumero = fachada.obterNossoNumeroFichaCompensacao(codigoBarraFichaCompensacaoHelper.getIdDocumentoTipo(), 
        	codigoBarraFichaCompensacaoHelper.getIdCobrancaDocumento()) ;
        	    	
        	String nossoNumeroSemDV = nossoNumero.toString().substring(0,17);
        	  
        	//Caso o c�digo de barra tenha sido informado por digita��o 
            if(codigoBarraLeituraOtica == null || codigoBarraLeituraOtica.equalsIgnoreCase("")){	
              //[FS0003] E [FS0005] - Validar d�gito verificador do c�digo de barra
            	this.validarDigitoVerificador(fachada, codigoBarraFichaCompensacaoHelper, nossoNumeroSemDV);
            }
          
            //Caso o c�digo de barra j� tenha sido informado pelo usu�rio e esteja na lista de documentos
            //[FS0004] - Verificar exist�ncia do documento na lista 
            if(this.verificarExistenciaDocumentoNaLista(codigoBarra, colecaoInserirPagamentoViaCanetaHelper)){
              //Levanta a exce��o para o usu�rio indicando que o documento j� foi informado	
        	  throw new ActionServletException("atencao.documento_informado");
            }else{
        	
        	  //[SB0008] � Processar Pagamento Ficha de Compensa��o.
        	  PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = fachada.processarPagamentosFichaCompensacao(
        	  sistemaParametro, dataPagamento, codigoBarraFichaCompensacaoHelper.getValorDocumento(), nossoNumeroSemDV 
        	  ,new Integer(idFormaArrecadacao), usuarioLogado);  
        	  
        	  //Recupera a descri��o a ocorr�ncia do movimento
         	  String descricaoOcorrenciaMovimento = pagamentoHelperCodigoBarras.getDescricaoOcorrencia();
         	  
         	  //Recupera o inidicador de aceita��o do registro do movimento
        	  Integer indicadorAceitacaoRegistroMovimento = Integer.parseInt(pagamentoHelperCodigoBarras.getIndicadorAceitacaoRegistro());        	

        	  //Caso o inidicador de aceita��o do registro do movimento seja igual a 1 (SIM) e
        	  //a descri��o a ocorr�ncia do movimento seja igual a "OK"
        	  //Caso contr�rio levanta uma exce��o com a descri��o da ocorr�ncia do movimento
        	  if(indicadorAceitacaoRegistroMovimento == 1 && descricaoOcorrenciaMovimento.equalsIgnoreCase("OK")){
        		
        		/*
        		 * Colocado por Raphael Rossiter em 30/10/2007
        		 * OBJ: Gerar as devoluc�es
        		 */
        		  
        		//Cria o documento para o c�digo de barras informado pelo usu�rio
                InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelper = new InserirPagamentoViaCanetaHelper();  
        		
        		//Adiciona a cole��o de pagamentos retornada pelo [SB0003]  
                inserirPagamentoViaCanetaHelper.setColecaoPagamento(pagamentoHelperCodigoBarras.getColecaoPagamentos());
                
                //Adiciona a devolucao retornada pelo [SB0003]  
                inserirPagamentoViaCanetaHelper.setColecaoDevolucao(pagamentoHelperCodigoBarras.getColecaoDevolucao());

        		//Recupera o valor do pagamento do c�digo de barra 
        		//valorPagamento = (Util.formatarMoedaRealparaBigDecimal(distribuirDadosCodigoBarras.getValorPagamento())).divide(new BigDecimal("100.00"));
        		valorPagamento = codigoBarraFichaCompensacaoHelper.getValorDocumento();
        		
        		if(codigoBarraLeituraOtica == null || codigoBarraLeituraOtica.equals("")) {
                inserirPagamentoViaCanetaHelper.setCodigoBarra(codigoBarraDigitadoCampo1 + codigoBarraDigitadoCampo2 + codigoBarraDigitadoCampo3 + 
                codigoBarraDigitadoDigitoVerificador + codigoBarraDigitadoCampo4);
        		}
        		else {
        			inserirPagamentoViaCanetaHelper.setCodigoBarra(codigoBarra);
        		}
                inserirPagamentoViaCanetaHelper.setValorPagamento(valorPagamento);
              
                //Adiciona o documento na cole��o
                colecaoInserirPagamentoViaCanetaHelper.add(inserirPagamentoViaCanetaHelper);
                
                //Limpa os c�digos de barras do form
                pagamentoActionForm.set("codigoBarraDigitadoCampo1Parte1","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo1Parte2","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo2Parte1","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo2Parte2","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo3Parte1","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo3Parte2","");
            	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificador","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo4","");
            	pagamentoActionForm.set("codigoBarraPorLeituraOtica","");  
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
     *
     * @author Pedro Alexandre
     * @date 16/02/2006
     *
     * @param codigoBarra
     * @param colecaoPagamentos
     * @return
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
     *
     * @author Pedro Alexandre
     * @date 16/02/2006
     */
    private void validarDigitoVerificador(Fachada fachada, RegistroHelperFichaCompensacao codigoBarraFichaCompensacaoHelper,
    		String nossoNumeroSemDV){
    	
    	//Cria as vari�veis quevai armazenar os d�gitos verificadores do m�dulo 11 e 10 respectivamente
    	String dvModulo11 = null;
    	String dvModulo10 = null; 
    	
    	dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo1SemDigito())).toString();
    	if(!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo1())){
    		throw new ActionServletException("atencao.digitoverificador_invalido");  
    	}
    	  
    	dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo2SemDigito())).toString();
    	if(!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo2())){
    		throw new ActionServletException("atencao.digitoverificador_invalido");
    	}
    	  
    	dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo3SemDigito())).toString();
    	if(!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo3())){
    		throw new ActionServletException("atencao.digitoverificador_invalido");
    	}

    	  
    	//DIGITO VERIFICADOR GERAL
    	String especificacaoCodigoBarra = fachada.obterEspecificacaoCodigoBarraFichaCompensacao(
    			ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
    			codigoBarraFichaCompensacaoHelper.getValorDocumento(), nossoNumeroSemDV.toString(), 
    			codigoBarraFichaCompensacaoHelper.getCarteira(), codigoBarraFichaCompensacaoHelper.getFatorVencimento());
    	
    	dvModulo11 = especificacaoCodigoBarra.substring(4, 5);
    	if (!dvModulo11.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo11())){
    		throw new ActionServletException("atencao.digitoverificador_invalido");
    	}
    }    
}
