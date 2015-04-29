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
package gsan.gui.faturamento.conta;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaMotivoRetificacao;
import gsan.faturamento.conta.FiltroContaMotivoRetificacao;
import gsan.faturamento.credito.CreditoRealizado;
import gsan.faturamento.debito.DebitoCobrado;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.consumo.ConsumoHistorico;
import gsan.micromedicao.consumo.FiltroConsumoHistorico;
import gsan.micromedicao.medicao.MedicaoHistorico;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.seguranca.acesso.Grupo;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetificarContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        // Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = this.getSessao(httpServletRequest);        
        
        //Carregando a conta com os dados atuais
        Conta contaAtual = (Conta) sessao.getAttribute("contaRetificar");        
        
        String valorConfirmacao = httpServletRequest.getParameter("confirmado");
        
        boolean telaConfirmacao;
        
    	String confirmacaoAtual = httpServletRequest.getParameter("tipoRelatorio");	
        
        // Inst�ncia do formul�rio que est� sendo utilizado
		RetificarContaActionForm retificarContaActionForm = (RetificarContaActionForm) actionForm;

		// Recebendo os dados enviados pelo formul�rio
		String imovelIdJSP = retificarContaActionForm.getIdImovel();
		String mesAnoContaJSP = retificarContaActionForm.getMesAnoConta();
		String vencimentoContaJSP = retificarContaActionForm.getVencimentoConta();
		String novoVencimentoContaJSP = retificarContaActionForm.getNovoVencimentoConta();
		Integer situacaoAguaContaJSP = new Integer(retificarContaActionForm.getSituacaoAguaConta());
		Integer situacaoEsgotoContaJSP = new Integer(retificarContaActionForm.getSituacaoEsgotoConta());
		Integer motivoRetificacaoContaJSP = new Integer(retificarContaActionForm.getMotivoRetificacaoID());
		
		String consumoAguaJSP = retificarContaActionForm.getConsumoAgua();
		String consumoEsgotoJSP = retificarContaActionForm.getConsumoEsgoto();
		String percentualEsgotoJSP = retificarContaActionForm.getPercentualEsgoto();
		
		
		//Verificar se o vencimento foi alterado
		if(novoVencimentoContaJSP != null && !novoVencimentoContaJSP.equals("")){
			vencimentoContaJSP = novoVencimentoContaJSP;
		}
		
		/*
		 * Adicionar a lista de clientes vinculados � conta, e indicar qual deles
		 * ser� impresso na 2� via da conta 
		 */
		
		String agua = null;
		String consumoAguaAntes = null;
		String esgoto = null;
		String consumoEsgotoAntes = null;
		
		if (consumoAguaJSP == null){
			agua = "1";
		} else{
			agua = consumoAguaJSP;
		}
		
		if (sessao.getAttribute("consumoAguaAntes") == null){
			consumoAguaAntes = "1";
		}else{
			consumoAguaAntes = sessao.getAttribute("consumoAguaAntes").toString();
		}
		
		if (consumoEsgotoJSP == null){
			esgoto = "1";
		}else{
			esgoto = consumoEsgotoJSP;
		}
		
		if (sessao.getAttribute("consumoEsgotoAntes") == null){
			consumoEsgotoAntes = "1";
		}else{
			consumoEsgotoAntes = sessao.getAttribute("consumoEsgotoAntes").toString();
		} 
		
		String percentualEsgotoConta = null;
		
		if(percentualEsgotoJSP != null){
			percentualEsgotoConta = percentualEsgotoJSP.toString().replace(".","");
			percentualEsgotoConta = percentualEsgotoConta.replace(",","");
		} else{
			percentualEsgotoConta = "1";
		}
		
		Boolean flag = true;
		if (situacaoAguaContaJSP.toString().equals(sessao.getAttribute("situacaoAguaContaAntes")) && 
			situacaoEsgotoContaJSP.toString().equals(sessao.getAttribute("situacaoEsgotoContaAntes")) && 
			agua.equals(consumoAguaAntes) && 
			esgoto.equals(consumoEsgotoAntes) && 
			percentualEsgotoConta.equals(sessao.getAttribute("percentualEsgotoAntes")) && 
			!vencimentoContaJSP.equals(sessao.getAttribute("vencimentoContaAntes"))) {
			flag = false;
		}
		
		// Carrega as cole��es de acordo com os objetos da sess�o
		Collection colecaoDebitoCobrado = new Vector();
		if (sessao.getAttribute("colecaoDebitoCobrado") != null) {
			colecaoDebitoCobrado = 
				(Collection) sessao.getAttribute("colecaoDebitoCobrado");
		}
		
		
		Collection colecaoCreditoRealizado = new Vector();
		if (sessao.getAttribute("colecaoCreditoRealizado") != null) {
			colecaoCreditoRealizado = 
				(Collection) sessao.getAttribute("colecaoCreditoRealizado");
		}

		//Alterado por Raphael Rossiter em 17/04/2007
		SistemaParametro sistemaParametro = 
			this.getFachada().pesquisarParametrosDoSistema();
		
		Collection colecaoCategoriaOUSubcategoria = new Vector();
		Collection colecaoCategoriaOUSubcategoriaInicial = new Vector();
		
		if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
    		
			colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
			
			if (sessao.getAttribute("colecaoSubcategoriaInicial") != null) {
    			
    			colecaoCategoriaOUSubcategoriaInicial = 
    				(Collection) sessao.getAttribute("colecaoSubcategoriaInicial");
    			
    			if (colecaoCategoriaOUSubcategoria == null || 
    				colecaoCategoriaOUSubcategoriaInicial.size() != colecaoCategoriaOUSubcategoria.size() ||
    				!colecaoCategoriaOUSubcategoriaInicial.containsAll(colecaoCategoriaOUSubcategoria)){
    				
    				flag = true;
    			}
    			
    			//Atualizando a quantidade de economias por subcategoria de acordo com o informado pelo usu�rio
    	        //-------------------------------------------------------------------------------------------
    	        Iterator colecaoCategoriaOUSubcategoriaInicialIt = colecaoCategoriaOUSubcategoriaInicial.iterator();
    	        Subcategoria subcategoria;
    	        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
    	        String qtdPorEconomia;
    	        
    	        while (colecaoCategoriaOUSubcategoriaInicialIt.hasNext()) {
    	        	subcategoria = (Subcategoria) colecaoCategoriaOUSubcategoriaInicialIt.next();

    				if (requestMap.get("subcategoria"+subcategoria.getId().intValue()) != null) {

    					qtdPorEconomia = (requestMap.get("subcategoria" + subcategoria.getId().intValue()))[0];
    					
    					if(!subcategoria.getQuantidadeEconomias().toString().equals(qtdPorEconomia)){
    						flag = true;
    					}

    					if (qtdPorEconomia == null || qtdPorEconomia.equalsIgnoreCase("")) {
    						throw new ActionServletException(
    							"atencao.campo_texto.obrigatorio", 
    							null,
    							"Quantidade de economias");
    					}

    					subcategoria.setQuantidadeEconomias(new Integer(qtdPorEconomia));
    				}
    	        }
    		}
    	} else{
    		
    		colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoCategoria");
    	
    		if (sessao.getAttribute("colecaoCategoriaInicial") != null) {
    			
    			colecaoCategoriaOUSubcategoriaInicial = 
    				(Collection) sessao.getAttribute("colecaoCategoriaInicial");
    			
    			if (colecaoCategoriaOUSubcategoria == null || 
    				colecaoCategoriaOUSubcategoriaInicial.size() != colecaoCategoriaOUSubcategoria.size() ||
    				!colecaoCategoriaOUSubcategoriaInicial.containsAll(colecaoCategoriaOUSubcategoria)) {
    				
    				flag = true;
    			}
    			
    			//Atualizando a quantidade de economias por categoria de acordo com o informado pelo usu�rio
    	        //-------------------------------------------------------------------------------------------
    	        Iterator colecaoCategoriaOUSubcategoriaInicialIt = colecaoCategoriaOUSubcategoriaInicial.iterator();
    	        Categoria categoria;
    	        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
    	        String qtdPorEconomia;
    	        
    	        while (colecaoCategoriaOUSubcategoriaInicialIt.hasNext()) {
    	        	categoria = (Categoria) colecaoCategoriaOUSubcategoriaInicialIt.next();

    				if (requestMap.get("categoria"+categoria.getId().intValue()) != null) {

    					qtdPorEconomia = (requestMap.get("categoria" + categoria.getId().intValue()))[0];
    					if(!categoria.getQuantidadeEconomiasCategoria().toString().equals(qtdPorEconomia)){
    						flag = true;
    					}

    					if (qtdPorEconomia == null || qtdPorEconomia.equalsIgnoreCase("")) {
    						throw new ActionServletException(
    							"atencao.campo_texto.obrigatorio", 
    							null,
    							"Quantidade de economias");
    					}

    					categoria.setQuantidadeEconomiasCategoria(new Integer(qtdPorEconomia));
    				}
    	        }
    		}
    	}
		
		Collection colecaoDebito = new Vector();
		if (sessao.getAttribute("colecaoDebitoCobradoInicial") != null) {
			
			colecaoDebito = 
				(Collection) sessao.getAttribute("colecaoDebitoCobradoInicial");
			
			if (sessao.getAttribute("colecaoDebitoCobrado") == null || 
				colecaoDebito.size() != ((Collection) sessao.getAttribute("colecaoDebitoCobrado")).size() ||
				!colecaoDebito.containsAll((Collection) sessao.getAttribute("colecaoDebitoCobrado")) ){
				
				flag = true;
			}

			//Atualizando o valor do debito de acordo com o informado pelo usu�rio
	        //-------------------------------------------------------------------------------------------
	        Iterator colecaoDebitoIt = colecaoDebito.iterator();
	        DebitoCobrado debitoCobrado;
	        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
	        String valor;
	        BigDecimal valor2 = new BigDecimal ("0.00"); 
	        
	        while (colecaoDebitoIt.hasNext()) {
	        	debitoCobrado = (DebitoCobrado) colecaoDebitoIt.next();

				if (requestMap.get("debitoCobrado"
						+ GcomAction.obterTimestampIdObjeto(debitoCobrado)) != null) {

					valor = (requestMap.get("debitoCobrado" + GcomAction.obterTimestampIdObjeto(debitoCobrado)))[0];
					if(!debitoCobrado.getValorPrestacao().toString().equals(valor)){
						flag = true;
					}
					if (valor == null
							|| valor.equalsIgnoreCase("")) {
						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"D�bito Cobrado");
					}
					else{
    					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
    				}

					debitoCobrado.setValorPrestacao(valor2);
				}
	        }
	        //------------------------------------------------------------------------------------------
		}
				
		Collection colecaoCredito = new Vector();
		if (sessao.getAttribute("colecaoCreditoRealizadoInicial") != null) {
			colecaoCredito = (Collection) sessao
					.getAttribute("colecaoCreditoRealizadoInicial");
			
			if (sessao.getAttribute("colecaoCreditoRealizado") == null || 
					colecaoCredito.size() != ((Collection) sessao.getAttribute("colecaoCreditoRealizado")).size()){
				flag = true;
			}
			
			//Atualizando o valor do credito de acordo com o informado pelo usu�rio
	        //-------------------------------------------------------------------------------------------
	        Iterator colecaoCreditoIt = colecaoCredito.iterator();
	        CreditoRealizado creditoRealizado;
	        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
	        String valor;
	        BigDecimal valor2 = new BigDecimal ("0.00"); 
	        
	        while (colecaoCreditoIt.hasNext()) {
	        	creditoRealizado = (CreditoRealizado) colecaoCreditoIt.next();

				if (requestMap.get("creditoRealizado"
						+ GcomAction.obterTimestampIdObjeto(creditoRealizado)) != null) {

					valor = (requestMap.get("creditoRealizado" + GcomAction.obterTimestampIdObjeto(creditoRealizado)))[0];
					if(!creditoRealizado.getValorCredito().toString().equals(valor)){
						flag = true;
					}
					if (valor == null
							|| valor.equalsIgnoreCase("")) {
						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Cr�dito Realizado");
					}
					else{
    					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
    				}

					creditoRealizado.setValorCredito(valor2);
				}
	        }

		}
		// [FS0015] - Verificar se foi efetuado o c�lculo da conta

		// [SF0001] - Determinar Valores para Faturamento de �gua e/ou Esgoto
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        // Adicionado por Bruno Barros, 25/07/2008
        // Verificar se foi informado a tarifa de consumo da conta no retificar
        // Caso sim, calcular com o novo ConsumoTarifa informado, sen�o, 
        // utilizar o da conta
        
        Integer idConsumoTarifa = contaAtual.getConsumoTarifa().getId();
        
        if ( retificarContaActionForm.getIdConsumoTarifa() != null &&
             !retificarContaActionForm.getIdConsumoTarifa().equals( ConstantesSistema.NUMERO_NAO_INFORMADO ) ){
           idConsumoTarifa = Integer.parseInt( retificarContaActionForm.getIdConsumoTarifa() );            
        }
        //RM4132 - adicionado por Vivianne Sousa - 17/02/2011 - analista:Jeferson Pedrosa
		boolean temPermissaoParaRetificarContaNorma = 
			this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
	    verificarOcorrenciasMesmoMotivoAno(motivoRetificacaoContaJSP,new Integer(imovelIdJSP), 
    			 sessao, sistemaParametro, temPermissaoParaRetificarContaNorma);
        verificarValoresLeituraAnteriorEAtualPoco(retificarContaActionForm.getLeituraAnteriorPoco(),
    		retificarContaActionForm.getLeituraAtualPoco(), retificarContaActionForm.getConsumoFaturadoPoco(),sistemaParametro);
        validarCompetencia(retificarContaActionForm.getMotivoRetificacaoID(), sistemaParametro,
    			 usuarioLogado, contaAtual, retificarContaActionForm.getConsumoFaturadoPoco(), 
    			 consumoAguaJSP,temPermissaoParaRetificarContaNorma);
        
        Collection<CalcularValoresAguaEsgotoHelper> valoresConta = 
            this.getFachada().calcularValoresConta(
                    mesAnoContaJSP, 
                    imovelIdJSP,
                    situacaoAguaContaJSP, 
                    situacaoEsgotoContaJSP,
                    colecaoCategoriaOUSubcategoria, 
                    consumoAguaJSP, 
                    consumoEsgotoJSP,
                    percentualEsgotoJSP, 
                    idConsumoTarifa, 
                    usuarioLogado);        
        
        // Fim alterador por Bruno Barros, 25/07/2008
        
        //C�lcula o valor total dos d�bitos de uma conta de acordo com o informado pelo usu�rio
        BigDecimal valorTotalDebitosConta = 
        	this.getFachada().calcularValorTotalDebitoConta(colecaoDebitoCobrado,
        httpServletRequest.getParameterMap());
        
        //C�lcula o valor total dos cr�ditos de uma conta de acordo com o informado pelo usu�rio
        BigDecimal valorTotalCreditosConta = 
        	this.getFachada().calcularValorTotalCreditoConta(colecaoCreditoRealizado,
        httpServletRequest.getParameterMap());
		
		//Totalizando os valores de �gua e esgoto
        BigDecimal valorTotalAgua = new BigDecimal("0");
        BigDecimal valorTotalEsgoto = new BigDecimal("0");
        
        if (valoresConta != null && !valoresConta.isEmpty()){
        	
        	Iterator valoresContaIt = valoresConta.iterator();
        	CalcularValoresAguaEsgotoHelper valoresContaObjeto = null;
        	
        	while (valoresContaIt.hasNext()){
        		
        		valoresContaObjeto = (CalcularValoresAguaEsgotoHelper) valoresContaIt.next();
        		
        		//Valor Faturado de �gua
        		if (valoresContaObjeto.getValorFaturadoAguaCategoria() != null){
        			valorTotalAgua = valorTotalAgua.add(valoresContaObjeto.getValorFaturadoAguaCategoria());
        		}
        		
        		//Valor Faturado de Esgoto
        		if (valoresContaObjeto.getValorFaturadoEsgotoCategoria() != null){
        			valorTotalEsgoto = valorTotalEsgoto.add(valoresContaObjeto.getValorFaturadoEsgotoCategoria());
        		}
        	} 
        }
        
        BigDecimal valorTotalConta = new BigDecimal("0");
        
        valorTotalConta = valorTotalConta.add(valorTotalAgua.setScale(2, BigDecimal.ROUND_CEILING));
        valorTotalConta = valorTotalConta.add(valorTotalEsgoto.setScale(2, BigDecimal.ROUND_CEILING));
        valorTotalConta = valorTotalConta.add(valorTotalDebitosConta);

        valorTotalConta = valorTotalConta.subtract(valorTotalCreditosConta);
        
        
        if (valorTotalConta.equals(new BigDecimal("0.00")) && 
        	(valorTotalCreditosConta == null || valorTotalCreditosConta.equals(new BigDecimal("0.00")))) {
			throw new ActionServletException("atencao.valor_conta_igual_zero");
		}
		else if (valorTotalConta.compareTo(new BigDecimal("0.00")) == -1){	
			throw new ActionServletException("atencao.valor_conta_negativo");
		}
		
        // [FS0022] - Retifica��o de Conta Retifivada.
        //-------------------------------------------------------------------------------------------
		// Alterado por :  Yara Taciane  - data : 19/06/2008 
		// Analista :  Denys Guimar�es
        //-------------------------------------------------------------------------------------------
        //Caso a conta de origem seja RETIFICADA.
		if( contaAtual.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.RETIFICADA) ){
			// E o valor total da nova conta esteja menor que o valor total da conta retificada original.
			if(valorTotalConta.doubleValue() < contaAtual.getValorTotalContaBigDecimal().doubleValue() ){
				// E o usu�rio n�o tenha permiss�o especial. 
				boolean temPermissaoParaRetificarParaMenorContaRetificadora = 
					this.getFachada().verificarPermissaoEspecial(
						PermissaoEspecial.RETIFICAR_PARA_MENOR_CONTA_RETIFICADA,
						usuarioLogado);
				
				if(temPermissaoParaRetificarParaMenorContaRetificadora == false){
					throw new ActionServletException("atencao.necessario_permissao_especial_para_retificar_para_menor");
				}
			}
		}
		//-------------------------------------------------------------------------------------------
        
		//Invertendo o formato para yyyyMM (sem a barra)
		mesAnoContaJSP = Util.formatarMesAnoParaAnoMesSemBarra(mesAnoContaJSP);
		
		//-------------------------------------------------------------------------------------------
		// Alterado por :  Hugo Leonardo  - data : 10/08/2010 
		// Analista :  Aryed Lins
		//-------------------------------------------------------------------------------------------	  

		// Data de vencimento da conta
		Date dataVencimentoConta = 
			Util.converteStringParaDate(vencimentoContaJSP);
		//Integer idImovel = Util.converterStringParaInteger(imovelIdJSP);
		Integer idConta = contaAtual.getId();
		

		// [FS0007] - Validar data de vencimento.			
		// if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
			
			//verifica se vencimento da conta foi alterado
			if(!retificarContaActionForm.getVencimentoConta().equals(retificarContaActionForm.getVencimentoContaNaBase())){
				
				//Caso data corrente seja posterior a data corrente mais a quantidade de dias parametro.
				Date dataCorrente = new Date();	
				
				Integer diasAdicionais = 0;
	        	
	        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
	        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
	        	}
				
				Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dataCorrente, diasAdicionais.intValue());
				//Date dataUltimoDiaMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente), Util.getAno(dataCorrente));
				
				//E o usu�rio n�o tenha permiss�o especial.	
				boolean temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao = 
					this.getFachada().verificarPermissaoEspecial(
						PermissaoEspecial.RETIFICAR_DATA_VENCIMENTO_ALEM_PRAZO_PADRAO,
						usuarioLogado);		
					
				//	1 se a dataVencimentoConta for maior que a dataCorrenteComDias.
				if(Util.compararData(dataVencimentoConta, dataCorrenteComDias) == 1 && 
					temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao == false){
					
					throw new ActionServletException("atencao.necessario_permissao_especial_para_data_vencimento_posterior_permitido");
				}
			
				Calendar data1985 = new GregorianCalendar(1985, 1, 1);
				if(Util.compararData(dataVencimentoConta, data1985.getTime()) == -1){
					throw new ActionServletException("atencao.data_vencimento_menor_1985");
				}				
			}
		//}	
		//-------------------------------------------------------------------------------------------

		// LigacaoAguaSituacao
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();		
		filtroLigacaoAguaSituacao.adicionarParametro(
			new ParametroSimples(FiltroLigacaoAguaSituacao.ID, 
				situacaoAguaContaJSP));		
		
		Collection colecaoLigacaoAguaSituacao = 
			this.getFachada().pesquisar(filtroLigacaoAguaSituacao, 
				LigacaoAguaSituacao.class.getName());	
		
		LigacaoAguaSituacao ligacaoAguaSituacao = 
			(LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
        
		
		/* 
		* [SB0017] - Verificar Consumo Limite das Categorias
		* Autor Romulo Aurelio
		* Data: 25/11/2011  
		*/
		telaConfirmacao = !consumoAguaAntes.equals( consumoAguaJSP );
		
		if(telaConfirmacao){
			if (retorno.getName().equalsIgnoreCase("telaSucesso") ) {
				
				if (valorConfirmacao == null || (!confirmacaoAtual.equals("1") && !confirmacaoAtual.equals("2"))) {
					if(consumoAguaJSP != null && !consumoAguaJSP.equals("")){
						if(this.getFachada().verificarConsumoLimiteCategorias(colecaoCategoriaOUSubcategoria, new Integer(mesAnoContaJSP), 
								contaAtual.getImovel().getId(), new Integer (consumoAguaJSP),  usuarioLogado)){          
					           
						   //se for para ir para a tela de confirma��o
			               httpServletRequest.setAttribute("caminhoActionConclusao",
			                       "/gsan/retificarContaAction.do");
			               httpServletRequest.setAttribute("cancelamento", "TRUE");
			               httpServletRequest.setAttribute("nomeBotao1", "Sim");
			               httpServletRequest.setAttribute("nomeBotao2", "N�o");
				            httpServletRequest.setAttribute("tipoRelatorio", "1");
			               //1.1.1.1.1
			               return montarPaginaConfirmacao("atencao.confirma_alteracao_consumo",
			                       httpServletRequest, actionMapping);
						}
					}
				}
				
				
				else if(valorConfirmacao.equals("cancelar")){
					sessao.removeAttribute("primeiraConfirmacao");
					retorno = actionMapping
			                .findForward("exibirRetificarConta");
					return retorno;
				}
			
			
				 /*
				*  
				* [SB0016] - Verificar a Anormalidade de Consumo da Conta
				* @author Hugo Azevedo
				* @date 01/12/2011
				*   
				*/
				this.getFachada().verificarAnormalidadeConsumoConta(
						contaAtual.getImovel(), contaAtual.getFaturamentoGrupo(),
						colecaoCategoriaOUSubcategoria, new Integer(mesAnoContaJSP),usuarioLogado);
		}
		
            
         // se for para ir para a tela de confirma��o
		if (valorConfirmacao == null || !confirmacaoAtual.equals("2")) {
			
				telaConfirmacao = 
				!consumoAguaAntes.equals( consumoAguaJSP ) && 
				ligacaoAguaSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM );
			
				if(telaConfirmacao){
					
		            httpServletRequest.setAttribute("caminhoActionConclusao",
		                    "/gsan/retificarContaAction.do");
		            httpServletRequest.setAttribute("cancelamento", "TRUE");
		            httpServletRequest.setAttribute("nomeBotao1", "Sim");
		            httpServletRequest.setAttribute("nomeBotao2", "N�o");
		            httpServletRequest.setAttribute("tipoRelatorio", "2");
		            return montarPaginaConfirmacao("atencao.consumo_novo_substituir_consumo_calculo_media_ano_mes",
		                    httpServletRequest, actionMapping);
				}
	            
            }
        }
		
        
		// LigacaoEsgotoSituacao
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		
		filtroLigacaoEsgotoSituacao.adicionarParametro(
			new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
					situacaoEsgotoContaJSP));
		
		Collection colecaoLigacaoEsgotoSituacao = 
			this.getFachada().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);

		
		// Motivo da Retifica��o da conta
		ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
		contaMotivoRetificacao.setId(motivoRetificacaoContaJSP);
		if(!flag)
		{
			throw new ActionServletException("atencao.so_vencimento_alterado_para_retificar_conta");
		}
		
		// -------------------------------------------------------------------------------------------
		// Alterado por :  Hugo Leonardo - data : 02/07/2010 
		// Analista :  Fabiola Araujo.
		//-------------------------------------------------------------------------------------------
		if(!vencimentoContaJSP.equals(sessao.getAttribute("vencimentoContaAntes"))){
			contaAtual.setIndicadorAlteracaoVencimento(new Short("1"));
		}else{
			contaAtual.setIndicadorAlteracaoVencimento(new Short("2"));
		}
		//-------------------------------------------------------------------------------------------
		//CRC4202 - adicionado por Vivianne Sousa - 21/09/2010 - analista:Adriana Ribeiro
		Integer leituraAnterior = null;
		if(retificarContaActionForm.getLeituraAnteriorAgua() != null && !retificarContaActionForm.getLeituraAnteriorAgua().trim().equalsIgnoreCase("")) {	
			leituraAnterior = new Integer(retificarContaActionForm.getLeituraAnteriorAgua());
		}
		Integer leituraAtual = null;
		if(retificarContaActionForm.getLeituraAtualAgua() != null && !retificarContaActionForm.getLeituraAtualAgua().trim().equalsIgnoreCase("")) {	
			leituraAtual = new Integer(retificarContaActionForm.getLeituraAtualAgua());
		}
		
		Integer leituraAnteriorPoco = null;
		if(retificarContaActionForm.getLeituraAnteriorPoco() != null && !retificarContaActionForm.getLeituraAnteriorPoco().trim().equalsIgnoreCase("")) {	
			leituraAnteriorPoco = new Integer(retificarContaActionForm.getLeituraAnteriorPoco());
		}
		Integer leituraAtualPoco = null;
		if(retificarContaActionForm.getLeituraAtualPoco() != null && !retificarContaActionForm.getLeituraAtualPoco().trim().equalsIgnoreCase("")) {	
			leituraAtualPoco = new Integer(retificarContaActionForm.getLeituraAtualPoco());
		}
		Integer volumePoco = null;
		if(retificarContaActionForm.getConsumoFaturadoPoco() != null && !retificarContaActionForm.getConsumoFaturadoPoco().trim().equalsIgnoreCase("")) {	
			volumePoco = new Integer(retificarContaActionForm.getConsumoFaturadoPoco());
		}
		BigDecimal percentualColeta = null;
		if(retificarContaActionForm.getPercentualColeta() != null && !retificarContaActionForm.getPercentualColeta().trim().equalsIgnoreCase("")) {	
			percentualColeta = Util.formatarMoedaRealparaBigDecimal(retificarContaActionForm.getPercentualColeta());
		}
		
		
		
		boolean atualizarMediaConsumoHistorico = false;
		if (valorConfirmacao != null)
			if (valorConfirmacao.equals("ok"))
				atualizarMediaConsumoHistorico = true;
		
		String retornoUrlBotaoVoltar = "retificarConta";
		
		
        idConta =  
        	this.getFachada().retificarConta(new Integer(mesAnoContaJSP), 
        		contaAtual,
        		contaAtual.getImovel(), 
        		colecaoDebitoCobrado, 
        		colecaoCreditoRealizado,
        		ligacaoAguaSituacao, 
        		ligacaoEsgotoSituacao, 
        		colecaoCategoriaOUSubcategoria, 
        		consumoAguaJSP,
        		consumoEsgotoJSP, 
        		percentualEsgotoJSP, 
        		dataVencimentoConta, 
        		valoresConta,
        		contaMotivoRetificacao, 
        		httpServletRequest.getParameterMap(), 
        		usuarioLogado, 
        		idConsumoTarifa.toString(), 
        		atualizarMediaConsumoHistorico,
        		leituraAnterior,leituraAtual,true,
        		retornoUrlBotaoVoltar,leituraAnteriorPoco,
        		leituraAtualPoco,volumePoco,percentualColeta);
				
		montarPaginaSucesso(httpServletRequest, 
				"Conta " + Util.formatarAnoMesParaMesAno(new Integer(mesAnoContaJSP).intValue()) + 
				" do im�vel " + contaAtual.getImovel().getId().intValue() + " retificada com sucesso.",
				"Realizar outra Manuten�ao de Conta",
				"exibirManterContaAction.do?menu=sim","gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta="+ idConta.toString(), 
				"Emitir 2� Via de Conta");
		
		return retorno;
    }
    
	
	/*[SB0012] � Determinar compet�ncia de retifica��o de consumo
	 * Vivianne Sousa - 16/02/2011
	 * RM4132 - analista respons�vel:Jeferson Pedrosa
	 */
	public BigDecimal determinarCompatenciaRetificacaoConsumo(String idMotivoSelecionado,
			SistemaParametro sistemaParametro,Usuario usuarioLogado,boolean temPermissaoParaRetificarContaNorma){
		
		BigDecimal competenciaRetificacao = null;
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			//Caso a Empresa esteja na Norma de Retifica��o de Conta 
			
			if(idMotivoSelecionado != null && !idMotivoSelecionado.equalsIgnoreCase("")
				&& !idMotivoSelecionado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = new FiltroContaMotivoRetificacao();
				filtroContaMotivoRetificacao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRetificacao.CODIGO, idMotivoSelecionado));
				
				ContaMotivoRetificacao contaMotivoRetificacao = null;
				Collection colecaoContaMotivoRetificacao = getFachada().pesquisar(
						filtroContaMotivoRetificacao, ContaMotivoRetificacao.class.getName());
				if (colecaoContaMotivoRetificacao != null && !colecaoContaMotivoRetificacao.isEmpty()) {
					contaMotivoRetificacao = (ContaMotivoRetificacao)Util.retonarObjetoDeColecao(colecaoContaMotivoRetificacao);
					
					if(!temPermissaoParaRetificarContaNorma && contaMotivoRetificacao != null && 
							contaMotivoRetificacao.getIndicadorCompetenciaConsumo().equals(ConstantesSistema.SIM)){
						
						Collection colecaoGrupo = getFachada().pesquisarGrupoUsuario(usuarioLogado.getId());
						if(colecaoGrupo != null && !colecaoGrupo.isEmpty()){
							Grupo grupo = (Grupo)Util.retonarObjetoDeColecao(colecaoGrupo);
							competenciaRetificacao = grupo.getCompetenciaRetificacao();
							
						}else{
							competenciaRetificacao = getFachada().pesquisarMaiorCompetenciaRetificacaoGrupo();
							
							if(competenciaRetificacao == null || competenciaRetificacao.equals(new BigDecimal("0.00"))){
								throw new ActionServletException("atencao.fator_competencia_precisa_ser_informado");
							}
						}
						
					}
				}
			
			}
					
		}
		return competenciaRetificacao;
	}

	
	/* [FS0041] � Validar compet�ncia Consumo �gua
	 * Vivianne Sousa - 17/02/2011
	 * RM4132 - analista respons�vel:Jeferson Pedrosa
	 */
	public void validarCompetenciaConsumoAgua(BigDecimal competenciaRetificacao,Conta contaSelecao,
			String consumoAgua,boolean temPermissaoParaRetificarContaNorma){
		
		if(!temPermissaoParaRetificarContaNorma){
			FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
	        
	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroConsumoHistorico.LIGACAO_TIPO_ID, ConsumoHistorico.INDICADOR_FATURAMENTO_FATURAR_AGUA));
	        
	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroConsumoHistorico.ANO_MES_FATURAMENTO, contaSelecao.getReferencia()));
	        
	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroConsumoHistorico.IMOVEL_ID, contaSelecao.getImovel().getId()));
	        
	        Collection colecaoConsumoHistorico = getFachada().pesquisar(filtroConsumoHistorico,	ConsumoHistorico.class.getName());
	        
	        if(!Util.isVazioOrNulo(colecaoConsumoHistorico)){
	        	
	        	ConsumoHistorico consumoHistorico = (ConsumoHistorico) colecaoConsumoHistorico.iterator().next();
	        	
	        	if(consumoHistorico.getConsumoMedio() != null){
	        		
	        		BigDecimal consumoMedio = new BigDecimal(consumoHistorico.getConsumoMedio());
//	 	        	BigDecimal divisao = competenciaRetificacao.divide(new BigDecimal(100));
	 	        	BigDecimal valorComparacao = consumoMedio.multiply(competenciaRetificacao);
	 	        	BigDecimal consumoAguaBigDecimal = new BigDecimal(consumoAgua);
	 	        	
	 	        	if(consumoAguaBigDecimal.compareTo(valorComparacao) < 0){
	 	        		throw new ActionServletException("atencao.comsumo_agua_abaixo_competencia_permitida");
	 	        	}
	        	}
	        	
	        }
		}
	}

	
	/* [FS0042] � Validar compet�ncia Volume Po�o
	 * Vivianne Sousa - 17/02/2011
	 * RM4132 - analista respons�vel:Jeferson Pedrosa
	 */
	public void validarCompetenciaVolumePoco(BigDecimal competenciaRetificacao,
			Conta contaSelecao,String volumePoco,boolean temPermissaoParaRetificarContaNorma){
		
		if(!temPermissaoParaRetificarContaNorma && volumePoco != null && !volumePoco.equals("")){
			
			FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
 	        
 	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
 	        		FiltroConsumoHistorico.LIGACAO_TIPO_ID, ConsumoHistorico.INDICADOR_FATURAMENTO_FATURAR_ESGOTO));
 	        
 	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
 	        		FiltroConsumoHistorico.ANO_MES_FATURAMENTO, contaSelecao.getReferencia()));
 	        
 	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
 	        		FiltroConsumoHistorico.IMOVEL_ID, contaSelecao.getImovel().getId()));
 	        
 	        Collection colecaoConsumoHistorico = getFachada().pesquisar(filtroConsumoHistorico,	ConsumoHistorico.class.getName());
 	        
 	        if(!Util.isVazioOrNulo(colecaoConsumoHistorico)){
 	        	
 	        	ConsumoHistorico consumoHistorico = (ConsumoHistorico) colecaoConsumoHistorico.iterator().next();
 	        	
 	        	if(consumoHistorico.getConsumoMedio() != null){
 	        		
 	        		BigDecimal consumoMedio = new BigDecimal(consumoHistorico.getConsumoMedio());
// 	 	        	BigDecimal divisao = competenciaRetificacao.divide(new BigDecimal(100));
 	 	        	BigDecimal valorComparacao = consumoMedio.multiply(competenciaRetificacao);
 	 	        	BigDecimal volumePocoBigDecimal = new BigDecimal(volumePoco);
 	 	        	
 	 	        	if(volumePocoBigDecimal.compareTo(valorComparacao) < 0){
 	 	        		throw new ActionServletException("atencao.volume_poco_abaixo_competencia_permitida");
 	 	        	}
 	        	}
 	        	
 	        }
		}
		
	}

	/* Vivianne Sousa - 17/02/2011
	 * RM4132 - analista respons�vel:Jeferson Pedrosa
	 */
	public void validarCompetencia(String idMotivoSelecionado,SistemaParametro sistemaParametro,
			Usuario usuarioLogado,Conta contaSelecao,String volumePoco,String consumoAgua,boolean temPermissaoParaRetificarContaNorma){
		
		BigDecimal competenciaRetificacao = determinarCompatenciaRetificacaoConsumo(idMotivoSelecionado,
				sistemaParametro, usuarioLogado,temPermissaoParaRetificarContaNorma);
		
		if(competenciaRetificacao != null){
			
			validarCompetenciaConsumoAgua(competenciaRetificacao, contaSelecao, consumoAgua,temPermissaoParaRetificarContaNorma);
			
			validarCompetenciaVolumePoco(competenciaRetificacao, contaSelecao, volumePoco,temPermissaoParaRetificarContaNorma);
		}
	}
	
	 /* [FS0039] � Verificar valores de Leitura Anterior e Atual do Po�o
	  * Vivianne Sousa - 17/02/2011
	  * RM4132 - analista respons�vel:Jeferson Pedrosa
	 */
	public void verificarValoresLeituraAnteriorEAtualPoco(String leituraAnteriorPocoString,
			String leituraAtualPocoString, String consumoFaturadoPocoString,SistemaParametro sistemaParametro) {
		
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			
			if(leituraAnteriorPocoString != null && leituraAtualPocoString != null && consumoFaturadoPocoString != null &&
					!leituraAnteriorPocoString.equalsIgnoreCase("") && !leituraAtualPocoString.equalsIgnoreCase("") 
					&& !consumoFaturadoPocoString.equalsIgnoreCase("")){
				//Caso exista informa��o do Volume de Po�o, da Leitura Anterior Po�o e da Leitura Atual Po�o:
				
				Integer leituraAnteriorPoco = new Integer(leituraAnteriorPocoString);
				Integer leituraAtualPoco =  new Integer(leituraAtualPocoString);
				Integer consumoFaturadoPoco = new Integer(consumoFaturadoPocoString);
				
				int diferencaLeituraAnteriorELeituraAtual = 0;

				if(leituraAtualPoco.intValue() > leituraAnteriorPoco.intValue()){
					diferencaLeituraAnteriorELeituraAtual = leituraAtualPoco.intValue() - leituraAnteriorPoco.intValue();
				}else if(leituraAnteriorPoco.intValue() > leituraAtualPoco.intValue()){
					diferencaLeituraAnteriorELeituraAtual = leituraAnteriorPoco.intValue() - leituraAtualPoco.intValue();
				}
				
				if(diferencaLeituraAnteriorELeituraAtual != consumoFaturadoPoco.intValue()){
					throw new ActionServletException("atencao.leitura_poco_fora_faixa");
				}
			}
		}
	
	}
	
	/*[FS0037]-Verificar ocorr�ncias mesmo motivo no ano
	 * Vivianne Sousa - 11/02/2011
	 */
	public void verificarOcorrenciasMesmoMotivoAno(Integer idMotivoSelecionado,Integer idImovel, 
			HttpSession sessao,SistemaParametro sistemaParametro,boolean temPermissaoParaRetificarContaNorma){

		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			//Caso a Empresa esteja na Norma de Retifica��o de Conta 
			
			ContaMotivoRetificacao contaMotivoRetificacao = getFachada().pesquisaContaMotivoRetificacao(idMotivoSelecionado);
			if (contaMotivoRetificacao != null && contaMotivoRetificacao.getNumeroOcorrenciasNoAno() != null &&
				contaMotivoRetificacao.getNumeroOcorrenciasNoAno().compareTo(new Integer("0")) == 1 ){
				
				Integer numeroOcorrenciasNoAno = contaMotivoRetificacao.getNumeroOcorrenciasNoAno();
				
				Integer qtdeContaEContaHistoricoRetificadaMotivo = getFachada().
				pesquisaQtdeContaEContaHistoricoRetificadaMotivo(idMotivoSelecionado,idImovel);
				
				if(!temPermissaoParaRetificarContaNorma && 
						qtdeContaEContaHistoricoRetificadaMotivo.compareTo(numeroOcorrenciasNoAno) > -1){
					//Limite de Retifica��es para o mesmo Motivo excedido!
					throw new ActionServletException("atencao.limite_retificacoes_excedido");
					
				}
				
			}
			
		}
		
	}
	
}

