<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gsan.util.Pagina, gsan.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<html:javascript staticJavascript="true"  formName="FiltrarFaturaClienteResponsavelActionForm"/>
<script language="JavaScript">
<!-- Begin
function validaMesAno(mesAno){
	if(mesAno.value != ""){
		return verificaAnoMes(mesAno);
	}else{
		return true;
	}
}
	
function chamarFiltrar(){
  var form = document.forms[0];
  
  if(validateFiltrarFaturaClienteResponsavelActionForm(form)) {
  	form.submit();
  }
}

function limparForm(){
	window.location.href='/gsan/exibirFiltrarFaturaClienteResponsavelAction.do?menu=sim';
}

function limparCliente(){

	var form = document.forms[0];
	form.clienteId.value = "";
	form.clienteNome.value = "";

}

function limparImovel(){

	var form = document.forms[0];
	form.imovelId.value = "";
	form.inscricao.value = "";

}

function limparImovelTecla() {
    var form = document.forms[0];
    form.inscricao.value = "";
}

function limparClienteNome(){
	var form = document.forms[0];
	form.clienteNome.value = "";
}

function habilitarPesquisaImovel(form) {
		chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.imovelId.value);
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

     if (tipoConsulta == 'cliente') {
      form.clienteId.value = codigoRegistro;
      form.clienteNome.value = descricaoRegistro;
      form.clienteNome.style.color = "#000000";
    }
     
     if(tipoConsulta == 'imovel'){
         form.imovelId.value = codigoRegistro;
         form.inscricao.value = descricaoRegistro;
         form.inscricao.style.color = "#000000";
     }
     
  }
-->
</script>
</head>

<body leftmargin="5" topmargin="5"  onload="javascript:setarFoco('${requestScope.nomeCampo}')">
<html:form
  action="/filtrarFaturaClienteResponsavelAction.do"
  method="post"
  name="FiltrarFaturaClienteResponsavelActionForm"
  type="gsan.gui.faturamento.FiltrarFaturaClienteResponsavelActionForm"
  onsubmit="return validateFiltrarFaturaClienteResponsavelActionForm(this);"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
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

         <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>

          <td class="parabg">Filtrar Fatura de Cliente Responsável</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
          <td width="80%" colspan="2">
          <table>
          	<tr>
          		<td width="80%">Para filtrar as faturas do cliente responsável, informe os dados abaixo:</td>
          		<td align="right">
				</td>
          	</tr>
          </table>
          </td>
        </tr>
        
        <tr>
          <td width="10%"><strong>Cliente:<font color="#FF0000">*</font></strong> </td>
          <td width="90%">
          	<!-- campo texto cliente -->
          	<html:text property="clienteId" size="12" maxlength="10" 
	 	     onkeypress="return validaEnter(event, '/gsan/exibirFiltrarFaturaClienteResponsavelAction.do?pesquisar=cliente', 'clienteId');"
	 	     onkeyup="limparClienteNome();"
	 	    />
          	
          	<!-- lupa cliente -->
          	<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=OK', 400, 800);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Código do Cliente" /></a>
						
			<!-- nome do cliente -->
			<logic:present name="clienteInexistente" scope="session">
			  <html:text property="clienteNome" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:present>
			<logic:notPresent name="clienteInexistente" scope="session">
				<html:text property="clienteNome" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notPresent>
			<!-- fim nome do cliente -->
			          	
          	<!-- borracha cliente -->
          	<a href="javascript:limparCliente();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
          
          </td>
        </tr>
        <tr>
          <td width="10%"><strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong></td>
          <td width="90%">
          	<html:text property="mesAno" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>&nbsp;mm/aaaa
          </td>
        </tr>

	    <tr>
			<td><strong>Imóvel:</strong></td>
			<td><strong><html:text property="imovelId" size="12" maxlength="9" onkeyup="javascript:verificaNumeroInteiro(this);limparImovelTecla();"
			onkeypress="javascript:return validaEnter(event, 'exibirFiltrarFaturaClienteResponsavelAction.do?pesquisar=imovel', 'imovelId');"/>  
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" alt="Pesquisar Imóvel">
					<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a></strong>
					<logic:present name="existeImovel">	
	 	                  		<logic:equal name="existeImovel" value="exception" scope="session">							 							  
				        	<html:text property="inscricao" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
		 		        </logic:equal>
					    <logic:notEqual name="existeImovel"	value="exception" scope="session">
	 					    <html:text property="inscricao" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>											    
				        </logic:notEqual>
				    </logic:present>
	
					<logic:notPresent name="existeImovel">
						<logic:empty name="FiltrarFaturaClienteResponsavelActionForm" property="imovelId">
							<html:text property="inscricao" size="40" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarFaturaClienteResponsavelActionForm" property="imovelId">
							<html:text property="inscricao" size="40"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
				<a href="javascript:limparImovel();"> 
					<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />						
				</a>
			</td>
		</tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td align="left"><input type="button" name="Button" class="bottonRightCol" value="Limpar" onClick="javascript:limparForm();"/></td>
          <td align="right">
            <gsan:controleAcessoBotao name="Button" value="Filtrar" onclick="javascript:chamarFiltrar();" url="filtrarFaturaClienteResponsavelAction.do"/>
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