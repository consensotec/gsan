<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<html:javascript staticJavascript="false"  formName="AtualizarContratoNegativadorActionForm" />
<script language="JavaScript">

function validarForm(){

	var form = document.forms[0];

	if(validateAtualizarContratoNegativadorActionForm(form) && verificarEmail() && validarIndicadorObriControSequeRetorno())
	{
		submeterFormPadrao(form);  
	}
}

	function verificaVigenciaContrato() {

		var form = document.AtualizarContratoNegativadorActionForm;		
		
		if(form.vigente.value == "false")
		{
			form.numeroContrato.readOnly = true;
			form.descricaoEmailEnvioArquivo.readOnly = true;
			form.codigoConvenio.readOnly = true;
			form.valorContrato.readOnly = true;
			form.valorTarifaInclusao.readOnly = true;
			form.numeroPrazoInclusao.readOnly = true;
			form.dataContratoInicio.readOnly = true;
			form.dataContratoFim.readOnly = true;
			form.dataContratoEncerramento.readOnly = true;		
			form.idContratoMotivoCancelamento.disabled = true;
			form.botaoAtualizar.disabled = true;	
			form.botaoDesfazer.disabled = true;
		} 
	}

   function verificarEmail(){
	var form = document.forms[0];
 	  if(form.descricaoEmailEnvioArquivo.value != "" && !checkMail(form.descricaoEmailEnvioArquivo)){
     	  alert("E-mail é um endereço inválido."); 
       return false;    
	   }
   
   	return true;
	}

   function validarIndicadorObriControSequeRetorno(){
		var form = document.forms[0];

		if(form.indicadorObriControSequeRetorno.value=="")
		{
			alert("Informe Indicador de Obrigatoriedade do Controle de Sequencial de Retorno");

			return false;
		}

		return true;
	}
  

</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:verificaVigenciaContrato();setarFoco('${requestScope.nomeCampo}');submit();">
<html:form action="/atualizarContratoNegativadorAction"
	name="AtualizarContratoNegativadorActionForm"
	type="gcom.gui.cobranca.spcserasa.AtualizarContratoNegativadorActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="time"/>
	<html:hidden property="vigente"/>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="120" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>

			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Contrato do Negativador</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>


			<p>&nbsp;</p>
		
		
		
		    <table>
              <tr>
                <td></td>
              </tr>
            </table>
        
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->

            <table width="100%" border="0">
              <tr> 
                <td colspan="2">Para atualizar o contrato do negativador, informe
                  os dados abaixo:</td>
              </tr>
                 
              <tr>
                <td><strong>Negativador:</strong></td>
                <td><html:text property="negativadorCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0"/> 
                </td>                 
              </tr>
			   <tr>
                <td><strong>Inclusões Enviadas:</strong></td>
				<td><html:text property="numeroInclusoesEnviadas" size="10" maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0"/> 
				</td>
              </tr>
			  <tr>
                <td><strong>Saldo das Inclusões:</strong></td>
				<td><html:text property="numeroInclusoesContratadas" size="10" maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0"/>
				</td>
			</tr>
               <tr>
                <td><strong>Exclusões Realizadas:</strong></td>
				<td><html:text property="numeroExclusoesEnviadas" size="10" maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0"/>
				</td>
                                                   
			  </tr>
                    <td colspan="4"><hr></td>
                  </tr>
			  
              <tr>
                <td><strong>N&uacute;mero do Contrato:<font color="#FF0000">*</font></strong></td>
                <td>
                <html:text property="numeroContrato" size="15" maxlength="10" />     
                </td>
              </tr>
              <tr>
                <td><strong>N&uacute;mero da Entidade:<font color="#FF0000">*</font></strong></td>
                <td><html:text property="numeroEntidade"  size="10" maxlength="5"/>
                </td>
              </tr>
              
              <tr>
                <td><strong>N&uacute;mero do Associado:<font color="#FF0000">*</font></strong></td>
                <td><html:text property="numeroAssociado"  size="10" maxlength="8"/>
                </td>
              </tr>
              
              <tr>
                <td><strong>E-mail para envio do arquivo:</strong></td>
                <td align="right"><div align="left"><strong>
                 <html:text property="descricaoEmailEnvioArquivo" size="45" maxlength="40" />                   
                </strong>
                </div></td>
              </tr>
              <tr>
                <td><strong>C&oacute;digo do Conv&ecirc;nio:</strong></td>
                <td align="right"><div align="left"><strong>
                <html:text property="codigoConvenio" size="25" maxlength="20" />                    
                </strong></div></td>
              </tr>
			  <tr> 
                <td><strong>Valor do Contrato:<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                 <html:text property="valorContrato" size="20" maxlength="15" />				  
                </strong>                    
                  </div></td>
              </tr>
			  <tr> 
                <td><strong>Valor da Tarifa para inclusão:<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                  <html:text property="valorTarifaInclusao" size="20" maxlength="15" />		
                </strong>                    
                  </div></td>
              </tr>
			  <tr> 
                <td><strong>Prazo para Negativação (em dias):<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                  <html:text property="numeroPrazoInclusao" size="20" maxlength="15" />	                
                </strong>                    
                  </div></td>
              </tr>
              <tr>
                <td><strong>Data de In&iacute;cio do Contrato:<font color="#FF0000">*</font></strong></td>
                <td align="right">
                  <div align="left"><strong>
                     <html:text property="dataContratoInicio" size="14" maxlength="10" onkeyup="mascaraData(this, event);" />	                       
                    <a href="javascript:abrirCalendario('AtualizarContratoNegativadorActionForm', 'dataContratoInicio')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
                  </strong> </div></td>
              </tr>
              <tr> 
                <td><strong>Data de Fim do Contrato:<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                 <html:text property="dataContratoFim" size="14" maxlength="10" onkeyup="mascaraData(this, event);"/>	  
                 <a href="javascript:abrirCalendario('AtualizarContratoNegativadorActionForm', 'dataContratoFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
                </strong>                    
                  </div></td>
              </tr>
              <tr>
                <td><strong>Data de Encerramento do Contrato:</strong></td>
                <td align="right">
                  <div align="left"><strong>
                     <html:text property="dataContratoEncerramento" size="14" maxlength="10" onkeyup="mascaraData(this, event);"/>
                     <a href="javascript:abrirCalendario('AtualizarContratoNegativadorActionForm', 'dataContratoEncerramento')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
                  </strong> </div>                </td>
              </tr>
              
              <tr>
					<td><strong>Indicador de Obrigatoriedade do Controle de Sequencial de Retorno  :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorObriControSequeRetorno" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorObriControSequeRetorno"  value="2" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>	
              
              <tr>
                <td><strong>Motivo Cancelamento:</strong></td>
                <td align="right"><div align="left">
                    <logic:present name="colecaoContratoMotivoCancelamento">  
                   	   <html:select property="idContratoMotivoCancelamento" tabindex="7" >
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoContratoMotivoCancelamento">
								<html:options collection="colecaoContratoMotivoCancelamento" labelProperty="descricaoMotivoCancelContrato" property="id"/>
							</logic:present>
						</html:select>
                	
                		</logic:present>  
                   
                   
                   
                 </div></td>
              </tr>
              <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
              
              <tr>


					<td><logic:present name="voltar">
						<logic:equal name="voltar" value="filtrar">
							<input name="Button" type="button" class="bottonRightCol"
								value="Voltar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarContratoNegativadorAction.do?desfazer=N"/>'">
						</logic:equal>
						<logic:equal name="voltar" value="manter">
							<input name="Button" type="button" class="bottonRightCol"
								value="Voltar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirManterContratoNegativadorAction.do"/>'">
						</logic:equal>
					</logic:present> <logic:notPresent name="voltar">
						<input name="Button" type="button" class="bottonRightCol"
							value="Voltar" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirManterContratoNegativadorAction.do"/>'">
					</logic:notPresent> 				
					<input name="botaoDesfazer" type="button" class="bottonRightCol"
							value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirAtualizarContratoNegativadorAction.do?desfazer=S"/>'">
					<input name="Button" type="button"
						class="bottonRightCol" value="Cancelar" align="left"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right">					  
						<input type="button" name="botaoAtualizar" class="bottonRightCol" value="Atualizar" onclick="javascript:validarForm();">					  
						
	               </td>
				</tr>
                      
            </table>
        	
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>




</html:form>

</body>
</html:html>
