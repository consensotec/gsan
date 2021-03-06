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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;

import gcom.cadastro.sistemaparametro.SistemaParametro;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import gcom.util.ConstantesSistema;
import gcom.util.Util;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdicionarDebitoCobradoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarDebitoCobradoConta");

        Fachada fachada = Fachada.getInstancia();
        
        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        //Inst�ncia do formul�rio que est� sendo utilizado
        AdicionarDebitoCobradoContaActionForm adicionarDebitoCobradoContaActionForm = 
        (AdicionarDebitoCobradoContaActionForm) actionForm;
        
        //Par�metros recebidos para adicionar um d�bito cobrado
        String debitoTipoID = adicionarDebitoCobradoContaActionForm.getDebitoTipoID();
        String mesAnoDebito = adicionarDebitoCobradoContaActionForm.getMesAnoDebito();
        String mesAnoCobranca = adicionarDebitoCobradoContaActionForm.getMesAnoCobranca();
        String valorDebito = adicionarDebitoCobradoContaActionForm.getValorDebito();
        String idImovelSeleted = adicionarDebitoCobradoContaActionForm.getImovelID();
        
        
        //Gerando o objeto DebitoCobrado que ser� inserido na cole��o
        DebitoCobrado objDebitoCobrado = new DebitoCobrado();
        objDebitoCobrado.setUltimaAlteracao(new Date());
        
        //Verifica��o da matr�cula do im�vel selecionado
        if (idImovelSeleted == null || idImovelSeleted.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.adicionar_debito_imovel_obrigatorio");
        }
        
        
        //Verifica��o do m�s e ano de d�bito
        if (mesAnoDebito != null && !mesAnoDebito.equalsIgnoreCase("")){
        	
        	//[FS0002] - Validar ano e m�s de refer�ncia
        	if (Util.validarAnoMes(mesAnoDebito)){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_referencia_invalido");
        	}
        	//[FS0004] - Verifica ano e m�s do faturamento
        	
        	//Invertendo para AnoM�s e retirando a barra
            mesAnoDebito = Util.formatarMesAnoParaAnoMesSemBarra(mesAnoDebito);
        	
    		FiltroImovel filtroImovel = new FiltroImovel();
        	
        	//Objetos que ser�o retornados pelo hibernate
        	//filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo.anoMesReferencia");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
            
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovelSeleted));
        	
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_debito_invalido");
        	}
    		Imovel objImovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
    		Integer mesAnoFaturamentoImovel = objImovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia();
    	
    		if ( Integer.valueOf(mesAnoDebito).compareTo(Integer.valueOf(mesAnoFaturamentoImovel)) > 0 ){
    			throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_debito_invalido");
    		}
    			//Inserir o mesAnoReferencia do d�bito no objeto final
    		objDebitoCobrado.setAnoMesReferenciaDebito(new Integer(mesAnoDebito));
        	
        }
        
        
        
        
        //Verifica��o do m�s e ano de cobran�a
        if (mesAnoCobranca != null && !mesAnoCobranca.equalsIgnoreCase("")){
        	
        	//[FS0002] - Validar ano e m�s de refer�ncia
        	if (Util.validarAnoMes(mesAnoCobranca)){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_referencia_invalido");
        	}
        	//[FS0011] - Verifica ano e m�s da cobran�a
        
        	
        	//Invertendo para AnoM�s e retirando a barra
        	mesAnoCobranca = Util.formatarMesAnoParaAnoMesSemBarra(mesAnoCobranca);
    		
        	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        	
        	if (sistemaParametro == null){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_cobranca_invalido");
        	}
        	else if (compararAnoMesReferencia(new Integer(mesAnoCobranca), new Integer(sistemaParametro.getAnoMesArrecadacao()), ">")){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_faturamento_invalido");
        	}
        	else{
    			//Inserir o mesAnoCobranca do d�bito no objeto final
    			objDebitoCobrado.setAnoMesCobrancaDebito(new Integer(mesAnoCobranca));
    		}
        	
        }
        
        
        //[FS0012] - Verificar valor do d�bito cobrado igual a zero
        BigDecimal objValorDebito = Util.formatarMoedaRealparaBigDecimal(valorDebito);
        if (objValorDebito.equals(new BigDecimal("0"))){
        	throw new ActionServletException(
            "atencao.adicionar_debito_valor_debito_igual_zero");
        }
    	//Inserir o valor do d�bito no objeto final
		objDebitoCobrado.setValorPrestacao(objValorDebito);        
        
        //Realizando consulta para obter os dados do tipo do d�bito selecionado
        FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        
        filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
    	
        filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID,
        		debitoTipoID));
        
        filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO,
    			ConstantesSistema.INDICADOR_USO_ATIVO));
    
    	Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
    		DebitoTipo.class.getName());
    
    	if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()){
    	throw new ActionServletException(
                "atencao.pesquisa.nenhum_registro_tabela", null,
                "DEBITO_TIPO");
    	}
		DebitoTipo objDebitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
		//Inserindo o tipo do d�bito selecionado
		objDebitoCobrado.setDebitoTipo(objDebitoTipo);
    	
    	
    	//N�mero de presta��es
    	objDebitoCobrado.setNumeroPrestacao(new Short("1").shortValue());
    	
    	//N�mero da presta��o 
    	objDebitoCobrado.setNumeroPrestacaoDebito(new Short("1").shortValue());
    	
    	
    	//Colocando o objeto gerado na cole��o que ficar� na sess�o
        if (sessao.getAttribute("colecaoDebitoCobrado") == null){
        	Collection colecaoDebitoCobrado = new Vector();
        	colecaoDebitoCobrado.add(objDebitoCobrado);
        	sessao.setAttribute("colecaoDebitoCobrado", colecaoDebitoCobrado);
        	
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        	//Definindo o caso de uso que receber� o retorno
        	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
        		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
        	}
        	else{
        		httpServletRequest.setAttribute("reloadPageURL", "MANTERCONTA");
        	}
        	
        }
        else{
        	Collection colecaoDebitoCobrado = (Collection) sessao.getAttribute("colecaoDebitoCobrado");
        	//[FS0014] - Verificar d�bito j� existente
        	if (!verificarDebitoJaExistente(colecaoDebitoCobrado, objDebitoCobrado)){
        		colecaoDebitoCobrado.add(objDebitoCobrado);
        		
        		httpServletRequest.setAttribute("reloadPage", "OK");
        	
        		//Definindo o caso de uso que receber� o retorno
            	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
            		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
            	}
            	else{
            		httpServletRequest.setAttribute("reloadPageURL", "MANTERCONTA");
            	}
        	}
        	else{
        		throw new ActionServletException(
                "atencao.adicionar_debito_ja_existente");
        	}
        }
        
        //Limpando o formul�rio para inserir um novo d�bito
        adicionarDebitoCobradoContaActionForm.setDebitoTipoID("");
        adicionarDebitoCobradoContaActionForm.setMesAnoCobranca("");
        adicionarDebitoCobradoContaActionForm.setMesAnoDebito("");
        adicionarDebitoCobradoContaActionForm.setValorDebito("");
        
        
        /*
		 * Colocado por Raphael Rossiter em 29/03/2007
		 * Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
        
        
        return retorno;
    }
    
    
    /**
     * Compara dois objetos no formato anoMesReferencia de acordo com o sinal logico passado.
     * @param anoMesReferencia1
     * @param anoMesReferencia2
     * @param sinal
     * @return um boleano
     */
    private boolean compararAnoMesReferencia(Integer anoMesReferencia1, Integer anoMesReferencia2, String sinal){
    	boolean retorno = true;
    	
    	//Separando os valores de m�s e ano para realizar a compara��o
    	String mesReferencia1 = String.valueOf(anoMesReferencia1.intValue()).substring(4, 6);
        String anoReferencia1 = String.valueOf(anoMesReferencia1.intValue()).substring(0, 4);
        
        String mesReferencia2 = String.valueOf(anoMesReferencia2.intValue()).substring(4, 6);
        String anoReferencia2 = String.valueOf(anoMesReferencia2.intValue()).substring(0, 4);
        
        if (sinal.equalsIgnoreCase("=")){
        	if (!Integer.valueOf(anoReferencia1).equals(Integer.valueOf(anoReferencia2))){
        		retorno = false;
        	}
        	else if (!Integer.valueOf(mesReferencia1).equals(Integer.valueOf(mesReferencia2))){
        		retorno = false;
        	}
        }
        else if (sinal.equalsIgnoreCase(">")){
        	if (Integer.valueOf(anoReferencia1).intValue() < Integer.valueOf(anoReferencia2).intValue()){
        		retorno = false;
        	}
        	else if (Integer.valueOf(anoReferencia1).equals(Integer.valueOf(anoReferencia2)) &&
        			 Integer.valueOf(mesReferencia1).intValue() <= Integer.valueOf(mesReferencia2).intValue()){
        		retorno = false;
        	}
        }
        else{
        	if (Integer.valueOf(anoReferencia2).intValue() < Integer.valueOf(anoReferencia1).intValue()){
        		retorno = false;
        	}
        	else if (Integer.valueOf(anoReferencia2).equals(Integer.valueOf(anoReferencia1)) &&
        			 Integer.valueOf(mesReferencia2).intValue() <= Integer.valueOf(mesReferencia1).intValue()){
        		retorno = false;
        	}
        }

    	
    	return retorno;
    }
    
    
    /**
     * [FS0014] - Caso o tipo do d�bito selecionado j� esteja na lista
     * @param colecaoDebitoCobrado
     * @param debitoCobradoInsert
     * @return um boleano
     */
    private boolean verificarDebitoJaExistente(Collection colecaoDebitoCobrado, DebitoCobrado debitoCobradoInsert){
    	boolean retorno = false;
    	
    	Iterator colecaoDebitoCobradoIt = colecaoDebitoCobrado.iterator();
    	DebitoCobrado debitoCobradoColecao;
    	
    	while (colecaoDebitoCobradoIt.hasNext()){
    		debitoCobradoColecao = (DebitoCobrado) colecaoDebitoCobradoIt.next();
    		if (debitoCobradoColecao.getDebitoTipo().getId().equals(debitoCobradoInsert.getDebitoTipo().getId())){
    			retorno = true;
    			break;
    		}
    	}
    	
    	return retorno;
    }

}

