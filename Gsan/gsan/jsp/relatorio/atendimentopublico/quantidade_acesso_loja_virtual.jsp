<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gsan.util.ConstantesSistema"%>
<%@page import="gsan.relatorio.atendimentopublico.bean.AcessoLojaVirtualHelper"  isELIgnored="false"%>

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
	formName="FiltrarAcessoLojaVirtualActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

function validarForm(form){
	
	if(validateFiltrarAcessoLojaVirtualActionForm(form)){
			form.action = "exibirFiltrarAcessoLojaVirtualAction.do?acao=pesquisa";
			form.submit();
		}
}

function replicarData(){
	var form = document.forms[0];
	form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value;
}

function validarNumero(campo){
	var valorAuxiliar = "";
	digitosValidos = "123456789/";
	var numeroValido = false;

	valor = campo.value;

	for(i=0; i<valor.length; i++){
		if(digitosValidos.indexOf(charAt(i)) >= 0){
			numeroValido = true;
			}
		}

	while(valorAuxiliar.lenngth > 1 && valorAuxiliar.charAt(0) == 0){
			valor = valorAuxiliar;
			if(numeroValido){
				campo.value = "";
				}
		}	
}

</script>

</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirFiltrarAcessoLojaVirtualAction" 
 		   name="FiltrarAcessoLojaVirtualActionForm" 
		   type="gsan.gui.relatorio.atendimentopublico.FiltrarAcessoLojaVirtualActionForm"
		   method="post"
		   >

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
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
			<td width="620" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Filtrar Acessos a loja Virtual</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para filtrar, informe os dados abaixo:</p>
					</td>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
					</tr>
	        </table>
	        
	        <table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>Per&iacute;odo de Atendimento:</strong>
								</td>
             				    <td colspan="6">
									<html:text property="periodoAtendimentoInicial" 
											size="11" 
											maxlength="10" 
											tabindex="3" 
											onblur="validarNumero(this);"
											onkeyup="mascaraData(this, event);replicarData();" 
											onkeypress="return isCampoNumerico(event);"/>
									<a href="javascript:abrirCalendarioReplicando('FiltrarAcessoLojaVirtualActionForm', 'periodoAtendimentoInicial', 'periodoAtendimentoFinal' );">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" /></a>a 
									<html:text property="periodoAtendimentoFinal" 
												size="11" 
												maxlength="10" 
												tabindex="3" 
												onblur="validarNumero(this);"
												onkeyup="mascaraData(this, event)"  
												onkeypress="return isCampoNumerico(event);"/>
									<a href="javascript:abrirCalendario('FiltrarAcessoLojaVirtualActionForm', 'periodoAtendimentoFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" />
									</a>
							</tr>
							<tr> 
				                <td><strong>Tipo de Atendimento:</strong></td>
				                <td colspan="6"><span class="style2"><strong>
									<html:select property="tipoAtendimento" style="width: 300px;" >
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:option value="1">Lojas Atendimento Presencial</html:option>
										<html:option value="2">Tele Atendimento</html:option>
										<html:option value="3">Ouvidoria</html:option>
										<html:option value="4">Negociacao de Débito</html:option>
										<html:option value="5">Estrutura Tarifaria</html:option>
										<html:option value="6">Validar Certidão Negativa de Débito</html:option>
										<html:option value="7">Volume Consumido</html:option>
										<html:option value="8">Tabela Serviços</html:option>
										<html:option value="9">Onde Pagar sua Fatura</html:option>
										<html:option value="10">Orientações ao Cliente</html:option>
										<html:option value="11">Débito Automático em Conta</html:option>
										<html:option value="12">Segunda via de conta</html:option>
										<html:option value="13">Declaração Anual Quitação de Débito</html:option>
										<html:option value="14">Outros Serviços</html:option>
										<html:option value="15">Consultar Pagamento</html:option>
										<html:option value="16">Acompanhar Registro Atendimento</html:option>
										<html:option value="17">Consultar Histórico de Consumo</html:option>
										<html:option value="18">Alterar Vencimento da conta</html:option>
										<html:option value="19">Certidão Negativa de Débitos</html:option>
										<html:option value="20">Recebimento de Fatura por E-MAIL</html:option>
									</html:select>                
				                  </strong></span></td>
				            </tr>
				            
				            
				            
				            <tr>
								<td width="18%"><strong>Servi&ccedil;o executado ?<font color="#FF0000">*</font></strong></td>
								
								<td colspan="6">
								<html:radio property="indicadorServicoExecutado"
								    value="1"/>
									Sim
								<html:radio property="indicadorServicoExecutado"
								    value="2"/>
									N&atilde;o
								<html:radio property="indicadorServicoExecutado"
								    value="3"/>
									Todos
								</td>
						</tr>
				            
							<tr>
								<td>
									<input name="Button" 
										   type="button" 
										   class="bottonRightCol"
										   value="Limpar" 
										   align="left"
										   onclick="window.location.href='/gsan/exibirFiltrarAcessoLojaVirtualAction.do?limparFormulario=sim'"
									       tabindex="8">
							    </td>
								<td>&nbsp;</td>
								<td width="65" align="right">
									<input name="Button2" 
										   type="button"
										   class="bottonRightCol" 
										   value="Filtrar" 
										   align="right"
										   onClick="javascript:validarForm(document.forms[0]);" tabindex="9" /></td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#000000">
							<td width="50%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
								<div align="center"><strong>Tipo de Atendimento</strong></div>
						   </td>
						   <td width="25%" bgcolor="#90c7fc" align="center">
						  	 <strong>Quantidade</strong>
						   </td>
						   <td width="25%" bgcolor="#90c7fc" align="center">
						  	 <strong>Qtd Serviços Executados</strong>
						   </td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
				  <td>
				  <table width="100%" bgcolor="#99CCFF">
                    
                    <logic:present name="colecaoLojaVirtualHelper" scope="request">
                      <%int cont = 0;%>
                      <logic:iterate name="colecaoLojaVirtualHelper" id="helper" scope="request" type="AcessoLojaVirtualHelper">
                       
                          <%cont = cont + 1;
							if (cont % 2 == 0) {%>
	                          <tr bgcolor="#cbe5fe">
	                            <%} else {%>
	                          </tr>
	                          <tr bgcolor="#FFFFFF">
	                            <%}%>
	                            <td width="50%" align="left"> 
	                            <a href="javascript:abrirPopup('exibirAcessoLojaVirtualPopupAction.do?tipoAtendimento=' + ${helper.codigoTipoAtendimento}  , 400, 800);">
	                            	<bean:write name="helper" property="tipoAtendimento"/>
	                            </a>
	                            </td>
	                            
	                            <td width="25%" align="center" >
									<bean:write name="helper" property="quantidade" />
								</td>
								<td width="25%" align="center" >
									<bean:write name="helper" property="qtdServicosExecutados" />
								</td>
	                          </tr>
                       
                      </logic:iterate>
                      <tr>
	                      <td valign="top" colspan="3">
							<div align="right"><a
								href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Acessos Loja Virtual" /> </a></div>
						  </td>
					  </tr>
                    </logic:present>
                  </table>
                  </td>
				</tr>
			</table>
			<table width="100%" border="0">
				
				<tr>
					<td valign="top">
						<div align="left">
							<input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</div>
					</td>
					
				</tr>
			</table>
	</tr>
</table>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAcessoLojaVirtualAction.do"/>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</body>
</html:html>