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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarGuiaPagamentoEmAtrasoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin

	function chamarFiltrar(){
  		var form = document.forms[0];
  		if (validateFiltrarGuiaPagamentoEmAtrasoActionForm(form)) {

  			form.action = "filtrarGuiaPagamentoEmAtrasoAction.do";
 			form.submit();
 			
  		}
	}


	function popularUnidadeNegocio(){
		var form = document.forms[0];
		form.action = "exibirFiltrarGuiaPagamentoEmAtrasoAction.do?preencheUnidade=true";
		form.submit();
	}


	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
		     
		      form.idLocalidadeInicial.value = codigoRegistro;
		  form.nomeLocalidadeInicial.value = descricaoRegistro;
		 
		  form.idLocalidadeFinal.value = codigoRegistro;
		      form.nomeLocalidadeFinal.value = descricaoRegistro;
		     
		  form.nomeLocalidadeInicial.style.color = "#000000";
		  form.nomeLocalidadeFinal.style.color = "#000000";
		 
		}

		if (tipoConsulta == 'localidadeDestino') {
		      form.idLocalidadeFinal.value = codigoRegistro;
		      form.nomeLocalidadeFinal.value = descricaoRegistro;
		  form.nomeLocalidadeFinal.style.color = "#000000";
		   
		}
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
			if (objeto == null || codigoObjeto == null){
		    	if(tipo == "" ){
		      		abrirPopup(url,altura, largura);
		    	}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
				}
			}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}

	function replicarLocalidade(){
		var form = document.forms[0];
		form.idLocalidadeFinal.value = form.idLocalidadeInicial.value;
	}

	function limparOrigem(opcao){
		var form = document.forms[0];
		switch(opcao){
			case 1:
				form.idLocalidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				break;
			case 2:
				form.idLocalidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				break;
		}
	}

	function limparBorrachaOrigem(opcao){
		var form = document.forms[0];
		switch(opcao){
			case 1:
				form.idLocalidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.idLocalidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				break;
			case 2:
				form.idLocalidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				break;
		}
	}
	
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/filtrarGuiaPagamentoEmAtrasoAction.do"
  name="FiltrarGuiaPagamentoEmAtrasoActionForm"
  type="gsan.gui.arrecadacao.pagamento.FiltrarGuiaPagamentoEmAtrasoActionForm" method="post"
  onsubmit="return validateFiltrarGuiaPagamentoEmAtrasoActionForm(this);"
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

          <td class="parabg">Filtrar Guia de Pagamento em Atraso</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
        	<td width="100%" colspan=2>
	        	<table width="100%" border="0">
		          	<tr>
		          		<td>Para filtrar a(s) Guia(s) de pagamento, informe os dados abaixo:</td>
		          	</tr>
		          </table>
        	</td>
        </tr>
        
        
		<tr>
        	<td width="30%"><strong>Ger&ecirc;ncia Regional:</strong> </td>
          	<td width="70%">
          	<html:select property="idGerenciaRegional" onchange="javascript:popularUnidadeNegocio();">
          		<html:option value="<%=ConstantesSistema.NUMERO_NAO_INFORMADO+""%>">&nbsp;</html:option>
            	<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id"/>
            </html:select>
          	</td>
        </tr>
        
        <tr>
          	<td><strong>Unidade de Neg&oacute;cio:</strong> </td>
          	<td>
          	<html:select property="idUnidadeNegocio" >
          		<html:option value="<%=ConstantesSistema.NUMERO_NAO_INFORMADO+""%>">&nbsp;</html:option>
              	<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id"/>
             </html:select>
          	</td>
        </tr>        
        
        <!-- Inicio - Localidade inicial -->
        
        <tr>
			<td><strong>Localidade Inicial:</strong></td>
			<td>
				<html:text maxlength="3" 
					tabindex="1"
					property="idLocalidadeInicial" 
					size="3"
					onkeyup="javascript:replicarLocalidade();"
					onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarGuiaPagamentoEmAtrasoAction.do?tipoPesquisa=pesquisaLocalidadeInicial','idLocalidadeInicial','Localidade Inicial');return isCampoNumerico(event);"/>
					
					<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidadeInicial);limparOrigem(1);">
						<img width="23" 
							height="21" 
							border="0" 
							style="cursor:hand;"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a>

					<logic:present name="localidadeInicialEncontrada" scope="session">
						<html:text property="nomeLocalidadeInicial" 
							size="30"
							maxlength="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:present> 

					<logic:notPresent name="localidadeInicialEncontrada" scope="session">
						<html:text property="nomeLocalidadeInicial" 
							size="30"
							maxlength="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />
					</logic:notPresent>

					<a href="javascript:limparBorrachaOrigem(1);"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" 
							title="Apagar" /></a>
			</td>
	</tr>

<!-- Inicio - Localidade final -->
        
	<tr>
		<td><strong>Localidade Final:</strong></td>
		<td>
			<html:text maxlength="3" 
				tabindex="1"
				property="idLocalidadeFinal" 
				size="3" 
				onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarGuiaPagamentoEmAtrasoAction.do?tipoPesquisa=pesquisaLocalidadeFinal','idLocalidadeFinal','Localidade Final');return isCampoNumerico(event);"/>
			
			<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].idLocalidadeFinal);limparOrigem(2);">
				<img width="23" 
					height="21" 
					border="0" 
					style="cursor:hand;"
					src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					title="Pesquisar Localidade" /></a>

			<logic:present name="localidadeFinalEncontrada" scope="session">
				<html:text property="nomeLocalidadeFinal" 
					size="30"
					maxlength="30" 
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:present> 

			<logic:notPresent name="localidadeFinalEncontrada" scope="session">
				<html:text property="nomeLocalidadeFinal" 
					size="30"
					maxlength="30" 
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: red" />
			</logic:notPresent>

			<a href="javascript:limparBorrachaOrigem(2);"> 
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" 
					title="Apagar" /></a>
		</td>
	</tr>        
        
        <tr>
          <td width="10%"><strong>Financiamento:</strong> </td>
          <td width="90%"><html:select property="financiamentoTipoId">
          		<html:option value="-1">&nbsp;</html:option>
              <html:options collection="colecaoFinancimentoTipo" labelProperty="descricao" property="id"/>
             </html:select>
          </td>
        </tr>
        
        <tr>
          <td width="10%"><strong>Data inicial de vencimento:</strong><font color="#FF0000">*</font></td>
          <td width="90%">
          	<html:text property="vencimentoInicial" size="10" maxlength="10"
          		onkeyup="javascript:mascaraData(this, event);"/>
						<a href="javascript:abrirCalendario('FiltrarGuiaPagamentoEmAtrasoActionForm', 'vencimentoInicial')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<font size="2">dd/mm/aaaa</font>
          </td>
        </tr>
        <tr>
          <td width="10%"><strong>Data final de vencimento:</strong><font color="#FF0000">*</font></td>
          <td width="90%">
	          <html:text property="vencimentoFinal" size="10" maxlength="10"
    	      	onkeyup="javascript:mascaraData(this, event);"/>
						<a href="javascript:abrirCalendario('FiltrarGuiaPagamentoEmAtrasoActionForm', 'vencimentoFinal')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<font size="2">dd/mm/aaaa</font>
          </td>
        </tr>
        
        <tr>
          <td width="10%"><strong>Referência inicial contábil:</strong></td>
          <td width="90%">
          	<html:text property="referenciaInicialContabil" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td width="10%"><strong>Referência final contábil:</strong></td>
          <td width="90%">
          	<html:text property="referenciaFinalContabil" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
			<td>&nbsp;</td>
			<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
		</tr>
        <tr>
          <td>
          	<input type="button" name="Button" class="bottonRightCol" value="Limpar" onclick="window.location.href='<html:rewrite page="/exibirFiltrarGuiaPagamentoEmAtrasoAction.do?menu=sim"/>'">
          </td>
          <td><div align="right">
          <gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:chamarFiltrar();" url="filtrarGuiaPagamentoEmAtrasoAction.do"/>
                   	<%--
          <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:chamarFiltrar();"/>--%></div></td>
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