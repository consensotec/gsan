<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.arrecadacao.bean.GuiaPagamentoHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConfirmarPagamentoCartaoCreditoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function formataData(mydata, tecla)
	{
		if (document.all) {
	       var codigo = event.keyCode;
		}
		else{
	       var codigo = tecla.which;
		}

		if (mydata.value.length == 2 && (codigo != 8 && codigo != 46)){
	        mydata.value = mydata.value + '/';
	    }

		if (mydata.value.length == 5 && (codigo != 8 && codigo != 46)){
	        mydata.value = mydata.value + '/';
	    }
	    
	    if (mydata.value.length == 10){
	        verificaData(mydata);
	    }
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado,nomeDependencia,valorDependencia)
	{
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo + "&"+ nomeDependencia + "=" + valorDependencia, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) 
	{
		var form = document.ConfirmarPagamentoCartaoCreditoActionForm;

		if (tipoConsulta == 'arrecadador') {
	    	form.idClienteArrecadador.value = codigoRegistro;
	        form.nomeClienteArrecadador.value = descricaoRegistro;
	        form.nomeClienteArrecadador.style.color = "#000000";
	    }	
	}
	
	function limparArrecadador() {
		var form = document.ConfirmarPagamentoCartaoCreditoActionForm;
		form.idClienteArrecadador.value = "";
		form.nomeClienteArrecadador.value = "";
	}  
	
	function pesquisarGuiaPagamento(){
		var form = document.forms[0];
		form.action = "/gsan/exibirConfirmarPagamentoCartaoCreditoAction.do?acao=Listar";
		form.submit();
	}
	
		
	function validateConfirmarPagamentoCartaoCreditoActionForm(form) {
		return true;
	}
	
	function validarForm(){
		var form = document.forms[0];
				
		if (!isBrancoOuNulo(form.valorCredito.value) && 
			!isBrancoOuNulo(form.somaValorGuias.value)){		
			submeterFormPadrao(form);
		}
   } 
	
</script>

</head>



<body leftmargin="5" topmargin="5">

<html:form action="/inserirPagamentoCartaoCreditoAction.do"
	name="ConfirmarPagamentoCartaoCreditoActionForm"
	type="gcom.gui.arrecadacao.pagamento.ConfirmarPagamentoCartaoCreditoActionForm"
	method="post"
	onsubmit="return validateConfirmarPagamentoCartaoCreditoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="150" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/mensagens.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
			</td>
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="imagens/parahead_left.gif" />
					</td>
					<td class="parabg">
						Confirmar Pagamento Cartão de Crédito
					</td>
					<td width="11" valign="top">
						<img border="0"	src="imagens/parahead_right.gif" />
					</td>
				</tr>
			</table>
			
			<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="3">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="1" width="25%" ><strong>Data de Vencimento:</strong><font color="#FF0000">*</font></td>
					<td colspan="3"><html:text maxlength="10" property="dataVencimento" size="10"
						onkeypress="formataData(this,event);" /> <a href="javascript:abrirCalendario('ConfirmarPagamentoCartaoCreditoActionForm', 'dataVencimento')">
						<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="middle" alt="Exibir Calendário" /></a> <strong>dd/mm/aaaa </strong></td>
				</tr>
				
			    <tr> 
					<td colspan="1"><strong>Cliente Arrecadador:</strong><font color="#FF0000">*</font></td>
					<td colspan="3"><strong> 
						 <html:select property="idClienteArrecadador" style="width: 230px;">
							<logic:present name="clientesArrecadador" scope="session">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="clientesArrecadador" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select> </strong>
					</td>
			    </tr>
			    
			    <tr>
				   <td colspan="1">
				   		<strong>Valor do Crédito:</strong><font color="#FF0000">*</font>
				   	</td>
				   <td colspan="3">
				        <html:text property="valorCredito" size="10" maxlength="9" 
				                   tabindex="4" onkeyup="formataValorMonetario(this, 13);" />		
				    </td>		
			    </tr>
				<tr>							
					<td colspan="4" align="right"> 
						<input type="button" name="filtrar" 
							class="bottonRightCol" value="Selecionar"  tabindex="5" onclick="pesquisarGuiaPagamento();"/>
					</td>
			  	</tr>
				<tr> 
                <td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  <hr></td>
              	</tr>
			
			
				<logic:present name="colecaoGuiaPagamento">
					<tr>
						<td colspan="4">
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">				     	
								<tr>
									<td>
										<table width="100%" bgcolor="#99CCFF">
											<tr bgcolor="#99CCFF">
												<td align="center" width="35%">
													<FONT COLOR="#000000"><strong>NSU</strong></FONT>
												</td>
												<td align="center" width="35%">
													<FONT COLOR="#000000"><strong>Data Parcelamento</strong></FONT>
												</td>		
												<td align="center" width="30%">
													<FONT COLOR="#000000"><strong>Valor do Débito</strong></FONT>
												</td>								
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" bgcolor="#99CCFF">
											<%String cor = "#cbe5fe";%>
											<%--Esquema de paginação--%>
											<pg:pager isOffset="true" index="half-full" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" 
												maxPageItems="10" items="${sessionScope.totalRegistros}">
												
												<pg:param name="pg" />
												<pg:param name="q" />
												<logic:present name="colecaoGuiaPagamento">
												<%int cont = 0;%>
													<logic:iterate name="colecaoGuiaPagamento" id="guiaPagamento" type="GuiaPagamentoHelper" scope="session">
														<pg:item>
														
														<%cont = cont + 1;
														if (cont % 2 == 0) {%>
															<tr bgcolor="#cbe5fe">
														<%} else {%>
															<tr bgcolor="#FFFFFF">
														<%}%>
														
																<td width="35%" align="center">
																	<bean:write name="guiaPagamento" property="nnIdentificadorTrancacao" />	
																</td>
																
																<td width="35%" align="center">
																	<bean:write name="guiaPagamento" property="dataParcelamento" formatKey="date.format" />
																</td>
																
																<td width="30%" align="right">
																	<bean:write name="guiaPagamento" property="valorDebito" formatKey="money.format" />	
																</td>
														
														    </tr>
														
														</pg:item>
													</logic:iterate>
												</logic:present>
											</pg:pager>
										</table>
									</td>
								</tr>
							</table>
						</td>
				  	</tr>
				</logic:present>
			
				<logic:present name="existeAvisoBancario">
					<tr>
						<td colspan="4"><strong> <font color="#FF0000"> Já existe Aviso Bancário para Arrecadador/Data informados. </font></strong></td>
					</tr>
					<tr>
						<td>
						<p>&nbsp;</p>
						</td>
					</tr>	
					<tr>
						<td><strong>Data do Lançamento:</strong></td>
						<td>
							<html:text  property="dataLancamento"  readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right" size="10" maxlength="40" />
						</td>
						<td width="20%"><strong>Valor Informado:</strong></td>
						<td>
							<html:text property="valorArrecadacaoInformado" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;  text-align: right" size="10" maxlength="40" />
						</td>
					</tr>
					<tr>
						<td><strong>Qtde de Guias Pagas: </strong></td>
						<td>
							<html:text property="quantidadeGuiasPagas" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;  text-align: right" size="10" /> 
						</td>
						<td><strong>Valor Calculado:</strong></td>
						<td>
							<html:text property="valorArrecadacaoCalculado"  readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;  text-align: right" size="10" maxlength="40" />	 
						</td>
					</tr>	
					<tr> 
	                	<td height="24" colspan="4" bordercolor="#000000" class="style1"> <hr></td>
	              	</tr>
				</logic:present>
	
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>			
			
				<logic:notPresent name="existeAvisoBancario">
					<tr>
						<td><strong>Percentual Tarifa: </strong></td>
						<td>
							<html:text  property="percentualArrecadador"  readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right" size="10" maxlength="40" />	 
						</td>							
						<td><strong>Valor Total: </strong></td>
						<td>
							<html:text property="somaValorGuias" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;  text-align: right" size="10" maxlength="40" />	 
						</td>							
					</tr>
				   	<tr>				   		
						<td><strong>Valor da Tarifa: </strong></td>
						<td>
							<html:text property="valorTarifa"  readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;  text-align: right" size="10" maxlength="40" />	 
						</td>							
						<td><strong>Diferença: </strong></td>
						<td>
							<html:text property="diferencaValorGuiaValorCredito" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;  text-align: right" size="10" /> 
						</td>							
					</tr>
				  
				  	<tr>
						<td>
						<p>&nbsp;</p>
						</td>
					</tr>	
				</logic:notPresent>	
			  
				<tr>			  
					<td colspan="4">
						<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>				
								<td colspan="3">
									<input name="Button" type="button" class="bottonRightCol"
										value="Limpar" align="left"
										onclick="window.location.href='<html:rewrite page="/exibirConfirmarPagamentoCartaoCreditoAction.do?menu=sim"/>'">
									<input name="Button" type="button" class="bottonRightCol"
										value="Cancelar" align="left"
										onclick="window.location.href='<html:rewrite page="/telaPrincipal.do"/>'">
								</td>
										
								<logic:notPresent name="existeAvisoBancario">		
									<td align="right"> 
										<input type="button" name="filtrar" 
											class="bottonRightCol" value="Inserir Pagamentos"  tabindex="8" onclick="validarForm();"/>
									</td>
								</logic:notPresent>
						  	</tr>
						</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
