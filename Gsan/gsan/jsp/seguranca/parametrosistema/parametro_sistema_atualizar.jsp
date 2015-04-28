<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.seguranca.parametrosistema.ParametroSistema" %>

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
	formName="InserirContratoAdesaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">


		function validateAtualizarParametroSistemaActionForm(form) {
			return true;
		}

		function validarForm(){
			var form = document.forms[0];
			if (validateAtualizarParametroSistemaActionForm(form)){
					submeterFormPadrao(form);
			}	
		
		}

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

		     if (mydata.value.length == 10){ 
		         verificaData(mydata); 
		     }
		     
			
		}


		function formataDataReferencia(mydata, tecla)
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


		}
		
		
		function formataDataHora(mydata, tecla)
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

			    if (mydata.value.length == 10){ 
			        verificaData(mydata); 
			    }
			    
				 if (mydata.value.length == 5 && (codigo != 8 && codigo != 46)){ 
			        mydata.value = mydata.value + '/'; 
			    } 
				 
				 if (mydata.value.length == 10 && (codigo != 8 && codigo != 46)){ 
				        mydata.value = mydata.value + ' '; 
				 } 
				 
				 if (mydata.value.length == 13 && (codigo != 8 && codigo != 46)){ 
				        mydata.value = mydata.value + ':'; 
				 }
				 
				 
				 if (mydata.value.length == 16 && (codigo != 8 && codigo != 46)){ 
				        mydata.value = mydata.value + ':'; 
				 }
				 
			
		}
	
		function maiuscula(descricao){
	        v = descricao.value.toUpperCase();
	        descricao.value = v;
	    }

		
	


</script>

</head>
			   
<html:form action="/atualizarParametroSistemaAction.do"
	name="AtualizarParametroSistemaActionForm"
	type="gsan.gui.seguranca.parametrosistema.AtualizarParametroSistemaActionForm"
	method="post"
	enctype="multipart/form-data"
	onsubmit="return validateParametroSistemaActionForm(this);">
					
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

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Par&acirc;metros do Sistema</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="3">Para atualizar um par&acirc;metro, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td><strong>Nome: </font></strong></td>
					<td colspan="2"><html:textarea rows="10" cols="20" property="nome" 
					 style="background-color:F0F0F0; width : 330px; resize:none; height : 41px;" readonly="true"/>
					</td>
				</tr>
			
				<tr>
					<td><strong>Descrição: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:textarea rows="10" cols="20" property="descricao" 
						 style="width : 330px; resize:none; height : 104px;" onkeyup="maiuscula(this);" onkeypress="return limita(this);" />
						<br>
						
					</td>
				</tr>
				<td><strong>Código Constante: </font></strong></td>
					<td colspan="2"><html:text property="codigoConstante" maxlength="20"
						size="20" style=" width : 85px;" disabled="true" /></td>
				</td>
				
				<!-- INPUTS ATIVADOS PARA O VALOR (CASO ICPARAMETRO DA TABELA SEGURANCA.PARAMETRO_SITEMA FOR IGUAL A 2) -->
				<logic:present name="parametroRestrito" scope="session">
				
					<logic:present name="parametroTipoInteiro" scope="session">
					
						<tr>
							<td><strong>Valor:</strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="10" size ="10" property="valorParametro" onkeypress="javascript:verificaNumeroInteiro(this);"/>						
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoIndicador" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
								<td colspan="2"><html:radio property="valorParametro" value="1" /><strong>Ativo
								<html:radio property="valorParametro" value="2"  />Inativo</strong>
							</td>
						</tr>	
					</logic:present>	
					
					
					<logic:present name="parametroTipoValor" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
								<td colspan="2"><html:text property="valorParametro" size="14" maxlength="14"
									onkeyup="javascript:formataValorMonetario(this,11);" /> 
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoURL" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text property="valorParametro" style="width : 330px; resize:none; height : 24px;" maxlength="500"/> 
							</td>
						</tr>
					</logic:present>
											
					<logic:present name="parametroTipoTexto" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:textarea rows="10" cols="20" property="valorParametro" 
							 style=" width : 330px; resize:none; height : 104px;" onkeyup="maiuscula(this);" />
							</td>
						</tr>
					</logic:present>	
					
					<logic:present name="parametroTipoIP" scope="session">
						<tr>
							<td><strong>Valor:</strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="15" size ="15" property="valorParametro"/>
							</td>
						</tr>
					
					</logic:present>	
					
					<logic:present name="parametroTipoMetodo" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text property="valorParametro" style="width : 330px;
							 height : 24px;" maxlength="100"/> 
							</td>
							</td>
						</tr>
					</logic:present>	
					
					
					<logic:present name="parametroTipoPercentual" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="6" property="valorParametro"
							size="6" onkeyup="javascript:formataValorMonetario(this, 5);"/>				
							</td>
						</tr>
					</logic:present>	
					
					<logic:present name="parametroTipoData" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="10" property="valorParametro"	size="10" onkeypress="formataData(this,event);" />
							<a href="javascript:abrirCalendario('AtualizarParametroSistemaActionForm', 'valorParametro')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
							<strong>dd/mm/aaaa </strong>				
							</td>
						</tr>
					</logic:present>	
					
					<logic:present name="parametroTipoDataHora" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="19" property="valorParametro"	size="19" onkeypress="formataDataHora(this,event);" />
							<a href="javascript:abrirCalendario('AtualizarParametroSistemaActionForm', 'valorParametro')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
							<strong>dd/mm/aaaa e hh:mm:ss</strong>				
							</td>
						</tr>
					</logic:present>	
					
					<logic:present name="parametroTipoLista" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:textarea rows="10" cols="20" property="valorParametro" 
							 style=" width : 330px; resize:none; height : 104px;" onkeyup="maiuscula(this);" />
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoCoordenada" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:textarea rows="10" cols="20" property="valorParametro" 
							 style=" width : 330px; resize:none; height : 104px;" onkeyup="maiuscula(this);" />
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoID" scope="session">
					
						<tr>
							<td><strong>Valor:</strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="10" size ="10" property="valorParametro" onkeypress="javascript:verificaNumeroInteiro(this);"/>						
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoReferencia" scope="session">					
						<tr>
							<td><strong>Valor:</strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="7" size ="7" property="valorParametro" onkeydown="formataDataReferencia(this,event)" onkeypress="javascript:somente_numero(this);"/>	
							<strong>mm/aaaa</strong>					
							</td>
						</tr>
					</logic:present>
					
					
				
				</logic:present>
				
				
				
				<!-- INPUTS DESATIVADOS PARA O VALOR-->
				<logic:present name="parametroRestritoAtivado" scope="session">
					
					<logic:present name="parametroTipoInteiro" scope="session">
						<tr>
							<td><strong>Valor:</strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="10" size ="10" property="valorParametro" 
								 onkeypress="javascript:verificaNumeroInteiro(this);" disabled="true"/>
							</td>
						</tr>
					
					</logic:present>
					
					<logic:present name="parametroTipoIndicador" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:radio property="valorParametro" value="1" disabled="true"/><strong>Ativo
							<html:radio property="valorParametro" value="2" disabled="true"/>Inativo</strong>
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoValor" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
								<td colspan="2"><html:text property="valorParametro" size="14" maxlength="14"
									onkeyup="javascript:formataValorMonetario(this,11);" disabled="true" /> 
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoURL" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text property="valorParametro" style="width : 330px; resize:none; height : 24px;" 
							maxlength="500" disabled="true"/> 
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoTexto" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:textarea rows="10" cols="20" property="valorParametro" 
							 style="background-color:F0F0F0; width : 330px; resize:none; height : 104px;" onkeyup="maiuscula(this);" readonly="true"/>
							</td>
						</tr>
					</logic:present>
										
					<logic:present name="parametroTipoIP" scope="session">
						<tr>
							<td><strong>Valor:</strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="15" size ="15" property="valorParametro" 
								 disabled="true"/>
							</td>
						</tr>
					
					</logic:present>
					
					<logic:present name="parametroTipoMetodo" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text property="valorParametro" style="width : 330px;
							 height : 24px;" maxlength="100" disabled="true"/> 
							</td>
							</td>
						</tr>
					</logic:present>	
					
					<logic:present name="parametroTipoPercentual" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="6" property="valorParametro"
							size="6" onkeyup="javascript:formataValorMonetario(this, 5);" disabled="true"/>				
							</td>
						</tr>
					</logic:present>	
					
					<logic:present name="parametroTipoData" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="10" property="valorParametro"	size="10" disabled="true"/>
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
							<strong> dd/mm/aaaa </strong>				
							</td>
						</tr>
					</logic:present>	
					
					<logic:present name="parametroTipoDataHora" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="16" property="valorParametro"	size="19" disabled="true"/>
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
							<strong>dd/mm/aaaa e hh:mm:ss</strong>				
							</td>
						</tr>
					</logic:present>	
									
					<logic:present name="parametroTipoLista" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:textarea rows="10" cols="20" property="valorParametro" 
							 style=" background-color:F0F0F0;width : 330px; resize:none; height : 104px;" onkeyup="maiuscula(this);" readonly="true"/>
							</td>
						</tr>
					</logic:present>		
					
					<logic:present name="parametroTipoCoordenada" scope="session">
						<tr>
							<td><strong>Valor: </strong><font color="#FF0000">*</td>
							<td colspan="2"><html:textarea rows="10" cols="20" property="valorParametro" 
							 style=" background-color:F0F0F0; width : 330px; resize:none; height : 104px;" onkeyup="maiuscula(this);" readonly="true"/>
							</td>
						</tr>
					</logic:present>	
					
						<logic:present name="parametroTipoID" scope="session">
					
						<tr>
							<td><strong>Valor:</strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="10" size ="10" property="valorParametro" onkeypress="javascript:verificaNumeroInteiro(this);" disabled="true"/>						
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="parametroTipoReferencia" scope="session">					
						<tr>
							<td><strong>Valor:</strong><font color="#FF0000">*</td>
							<td colspan="2"><html:text maxlength="7" size ="7" property="valorParametro" onkeydown="formataDataReferencia(this,event)" onkeypress="javascript:somente_numero(this);" disabled="true"/>						
							<strong>mm/aaaa</strong>
							</td>
						</tr>
					</logic:present>
					
					
					
				</logic:present>
							
				
				<tr>
				<td><strong>Módulo Principal Associado: </strong></td>
					<td colspan="2"><html:text  property="descricaoModulo" maxlength="100"
						size="30" disabled="true"/>
						<br>
					</td>
				</tr>
				<tr>
				<td><strong>Tipo: </strong><font color="#FF0000">*</td>
					<td align="left" colspan="2"><html:textarea rows="10" cols="20" property="descricaoTipo"
						 style="background-color:F0F0F0; width : 330px; resize:none; height : 24px;" readonly="true"/>
						<br>
					</td>
				</tr>
			
				
				<tr>
				<logic:present name="parametroRestrito" scope="session">
					<td><strong>Indicador de uso: </strong><font color="#FF0000">*</td>
					<td colspan="2"><html:radio property="indicadorUso" value="1" tabindex="5" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="6" />Inativo</strong>
					</td>
				</logic:present>
					
				<logic:present name="parametroRestritoAtivado" scope="session">
					<td><strong>Indicador de uso: </strong><font color="#FF0000">*</td>
					<td colspan="2"><html:radio property="indicadorUso" value="1" tabindex="5" disabled="true" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="6" disabled="true"/>Inativo</strong>
					</td>				
				</logic:present>
					
				</tr>
				
				<logic:present name="restrito" scope="request">											
				<tr>
					<td><strong>Indicador de Restrição: </strong><font color="#FF0000">*</td>
					<td colspan="2"><html:radio property="indicadorParametroRestrito" value="1" tabindex="5" /><strong>Ativo
					<html:radio property="indicadorParametroRestrito" value="2" tabindex="6" />Inativo</strong>
					</td>
				</tr>
				</logic:present>
				<tr>
					<td>&nbsp;</td>
					<td align="left" colspan="2"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td  align="left" style=" width : 220px;"> 
					
						
						<input name="button" type="button" class="bottonRightCol"
						tabindex="2" value="Voltar"
						onclick="window.history.go(-1)">
						
						 <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()">
						
					</td>
					<td align="right">						
						<gsan:controleAcessoBotao name="Button" value="Atualizar"
							  onclick="javascript:validarForm(document.forms[0]);" url="atualizarParametroSistemaAction.do"/>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>
