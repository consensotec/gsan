<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cobranca.NegativadorMovimentoReg"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAcaoCobrancaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>	
	
	
	
<script language="JavaScript">
<!-- Begin

	 function limparUsuario() {
	 	document.forms[0].nomeUsuario.value = '';
	 	document.forms[0].idUsuario.value = '';
	 	document.forms[0].usuarioLimpo.value='1';                  
	 }

	function trocarNegativador(){

		if (document.forms[0].idImovel.value != '' ) {			
	        document.forms[0].action = 'exibirExcluirNegativacaoOnLineAction.do?negativador=' + document.forms[0].negativador.value;
	        document.forms[0].submit();
	    }
	}


    function required () {
	 this.aa = new Array("negativador","Informe o negativador.", new Function ("varName", " return this[varName];"));     
     this.ab = new Array("idImovel", "Informe o imóvel.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("motivoExclusao", "Informe o motivo da exclusão.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("dataExclusao", "Informe a data da exclusão.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("idUsuario", "Informe o usuário responsável.", new Function ("varName", " return this[varName];"));   
    
    } 
	
	
	function DateValidations () { 
     this.aa = new Array("dataExclusao", "Data da Exclusão não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     
    } 


  function validateExcluirNegativacaoOnLineActionForm() {
  	 extendeTabela('Exclusao',true);
     var form = document.forms[0];       
         
       	if(validateRequired(form) &&  validateDate(form)){

       		var idsCliente = obterValorCheckboxMarcadoPorNome("cliente");
			var urlTransferencia = "/gsan/excluirNegativacaoOnLineAction.do?";
			
			if (idsCliente != null && idsCliente.length > 0){
				urlTransferencia = urlTransferencia + "cliente=" + idsCliente;
				form.action = urlTransferencia;
				submeterFormPadrao(form);
			}else{
				alert('Selecione Cliente.')
			}
		}
   } 


	function limparForm(tipo){
      var form = document.forms[0];
	    if(tipo == 'imovel')
	    {
	        form.idImovel.value = "";
	        form.inscricaoImovel.value = "";
			form.action = '/gsan/exibirExcluirNegativacaoOnLineAction.do?menu=sim';
			form.submit();
		}
	}	

	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	     if(tipoConsulta == 'imovel'){
	        form.idImovel.value = codigoRegistro;
	        form.inscricaoImovel.value = descricaoRegistro;
	        form.action = 'exibirExcluirNegativacaoOnLineAction.do?objetoConsulta=2';
	        form.submit();
	 	} else if ('usuario' == tipoConsulta) {
		 	document.forms[0].idUsuario.value = codigoRegistro;
		 	document.forms[0].nomeUsuario.value = descricaoRegistro;
    	}
	}	
	
	// Chama Popup
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaMunicipio=" + tipo, altura, largura);
				}
			}
		}
	}
	

	

-->
</script>
</head>

<body leftmargin="5" topmargin="5" >
<html:form action="/excluirNegativacaoOnLineAction.do"
	name="ExcluirNegativacaoOnLineActionForm"
	type="gcom.gui.cobranca.ExcluirNegativacaoOnLineActionForm"
	method="post">

	<!--<html:hidden name="ExcluirNegativacaoOnLineActionForm" property="idImovelAnterior"/>
	<html:hidden name="ExcluirNegativacaoOnLineActionForm" property="negativadorAnterior"/>-->

	<html:hidden name="ExcluirNegativacaoOnLineActionForm" property="dataHoje"/>
	<html:hidden name="ExcluirNegativacaoOnLineActionForm" property="dataEnvio"/>

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">

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
<!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr> 
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>

                <td class="parabg">Excluir Negativa&ccedil;&atilde;o do Im&oacute;vel</td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>

            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
            <p>&nbsp;</p>
            <table width="100%" border="0" >

              <form>
                <tr> 
                  <td colspan="2">Pesquisar um im&oacute;vel para atualizar os 
                    dados do faturamento:</td>
                </tr>
                <tr>
                  <td><strong>Negativador:<font color="#FF0000">*</font></strong></td>
                  <td>
                  	<html:select property="negativador" onchange="javascript:trocarNegativador()">
                  		<html:option value="">&nbsp;</html:option>                  			
						<html:options collection="collNegativador" labelProperty="cliente.nome" property="id"/>				  		
				  	</html:select>
			  	  </td>
                </tr>
				<tr>
					<td><strong>Imóvel:<font color="#FF0000">*</strong></td>
					<td><strong><html:text property="idImovel" size="10" maxlength="10"
						onkeypress="javascript:return validaEnter(event, 'exibirExcluirNegativacaoOnLineAction.do', 'idImovel');" />
					<a
						href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].idImovel.value);"
						alt="Pesquisar Imóvel"> <img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a></strong>
					<logic:present name="existeImovel">
						<logic:equal name="existeImovel" value="exception">
							<html:text property="inscricaoImovel" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="existeImovel" value="exception">
							<html:text property="inscricaoImovel" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="existeImovel">
						<logic:empty name="ExcluirNegativacaoOnLineActionForm"
							property="idImovel">
							<html:text property="inscricaoImovel" size="30" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="ExcluirNegativacaoOnLineActionForm"
							property="idImovel">
							<html:text property="inscricaoImovel" size="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparForm('imovel');"> <img
						border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" /> </a></td>
				</tr>
                  </span></b></strong><strong><b><span class="style1"></span></b></strong> 
                    <span class="style1"> </span></td>
                </tr>
                <tr> 
                  <td colspan="2"><hr></td>
                </tr>

<tr >
  <td colspan="2"> 
	<div id="layerHideImovel" style="display:block">
	<table width="100%" border="0" >
                <tr bgcolor="#99CCFF">
                  <td height="18" colspan="2"><div align="center"><strong>
                  <logic:notEqual property="inscricaoImovel" name="ExcluirNegativacaoOnLineActionForm" value="">
                    <logic:present name="imovel" scope="session">
                  		<a href="javascript:extendeTabela('Imovel',true);">Dados do Im&oacute;vel</a>
                    </logic:present>
                    <logic:notPresent  name="imovel" scope="session">
                  	  Dados do Im&oacute;vel
                    </logic:notPresent>
                    
                  </logic:notEqual>
                  <logic:equal property="inscricaoImovel" name="ExcluirNegativacaoOnLineActionForm" value="">
                  	Dados do Im&oacute;vel
                  </logic:equal>
                  </strong></div></td>
                </tr>
    </table>            
    </div>
    <div id="layerShowImovel" style="display:none">
    <table width="100%" border="0" >
                <tr bgcolor="#99CCFF">
                  <td height="18" colspan="2"><div align="center"><strong><a href="javascript:extendeTabela('Imovel',false);">Dados do Im&oacute;vel</a></strong></div></td>
                </tr>
                <tr> 
                  <td><strong>Im&oacute;vel Selecionado:</strong></td>
                  <td><strong><b><span class="style1">
                  		<input name="id" disabled type="text" size="9" maxlength="9" style="background-color:#EFEFEF; border:0"  value="<logic:present name="imovel" scope="session" property="id"><bean:write name="imovel" scope="session" property="id"/></logic:present>" >
					  </span></b></strong>
				  </td>	  
                </tr>
                <tr>
                  <td><strong>Inscri&ccedil;&atilde;o:</strong></td>
                  <td><strong><b><span class="style1">
                  		<input name="inscricaoFormatada" disabled type="text" size="20" maxlength="20" style="background-color:#EFEFEF; border:0"  value="<logic:present name="imovel" scope="session" property="id"><bean:write name="imovel" scope="session" property="inscricaoFormatada"/></logic:present>" >
					  </span></b></strong>
				  </td>	  
                </tr>
                <tr>

                  <td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de 
                    &Aacute;gua:</strong></td>
                  <td><strong><b><span class="style1">
                  		<input name="situacaoagau" disabled type="text" size="20" maxlength="20" style="background-color:#EFEFEF; border:0"  value="<logic:present name="imovel" scope="session" property="ligacaoAguaSituacao.descricao"><bean:write name="imovel" scope="session" property="ligacaoAguaSituacao.descricao"/></logic:present>" >
					  </span></b></strong>
				  </td>	  
                </tr>
                <tr> 
                  <td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de 
            Esgoto:</strong></td>
                  <td><strong><b><span class="style1">
                  		<input name="situacaoesgoto" disabled type="text" size="20" maxlength="20" style="background-color:#EFEFEF; border:0"  value="<logic:present name="imovel" scope="session" property="ligacaoEsgotoSituacao.descricao"><bean:write name="imovel" scope="session" property="ligacaoEsgotoSituacao.descricao"/></logic:present>" >
					  </span></b></strong>
				  </td>	  

                </tr>
                <tr> 
                  <td colspan="2"><table width="100%" border="0" bgcolor="#99CCFF">
                    <tr bgcolor="#99CCFF">
                      <td height="18" colspan="2"><div align="center"><span class="style1"> <strong>Endere&ccedil;o </strong></span></div></td>
                    </tr>
                    <tr bgcolor="#FFFFFF">
                      <td><table border="0" width="100%">

                          <tr>
                            <td class="style3"><div align="center" class="style4">
                            	<logic:present name="imovel" scope="session" property="enderecoFormatado"><bean:write name="imovel" scope="session" property="enderecoFormatado"/></logic:present>
                            </div></td>
                          </tr>
                      </table></td>
                    </tr>
                  </table></td>
                </tr>

   </table>                
     </div>
  </td>
</tr>

<tr >
  <td colspan="2">
	<div id="layerHideNegativacao" style="display:block">  
	<table width="100%" border="0" >
                <tr> 
                  <td colspan="2" bordercolor="#FFFFFF" bgcolor="#79BBFD"><strong> 
                    <div align="center"><strong><strong>
                  <logic:notEqual property="inscricaoImovel" name="ExcluirNegativacaoOnLineActionForm" value="">
                    <logic:present  name="imovel" scope="session">
                  		<a href="javascript:extendeTabela('Negativacao',true);">Dados da Negativa&ccedil;&atilde;o</a>
                    </logic:present>
                    <logic:notPresent name="imovel" scope="session">
                  	  Dados da Negativa&ccedil;&atilde;o
                    </logic:notPresent>
                    
                  </logic:notEqual>
                  <logic:equal property="inscricaoImovel" name="ExcluirNegativacaoOnLineActionForm" value="">
                  	Dados da Negativa&ccedil;&atilde;o
                  </logic:equal>
                    </strong></strong></div>
                    </strong></td>
                </tr>
    </table>            
	</div>
    <div id="layerShowNegativacao" style="display:none">
	<table width="100%" border="0"  >
                <tr> 
                  <td colspan="2" bordercolor="#FFFFFF" bgcolor="#79BBFD">
                    <div align="center"><strong><strong><a href="javascript:extendeTabela('Negativacao',false);">Dados da Negativa&ccedil;&atilde;o</a></strong></strong></div>
                  </td>
                </tr>
              
                <!-- inicio Negativação por período -->
                <logic:present name="colecaoHelper" scope="session">
					<logic:iterate name="colecaoHelper" scope="session" id="helper" >
				
						<bean:define name="helper" property="negativadorMovimentoReg" id="negativadorMovimentoReg" type="NegativadorMovimentoReg"></bean:define>
						 <tr>
		                   <td><strong>Negativador: <font color="#FF0000"> </font></strong></td>
		                   <td>
			                   <logic:present name="negativadorMovimentoReg" property="negativadorMovimento.negativador.cliente.nome" >
			                   		<strong><b><span class="style1">
			                  		<input name="nega" disabled type="text" size="50" maxlength="50" style="background-color:#EFEFEF; border:0"  
			                  		value="<bean:write name="negativadorMovimentoReg" property="negativadorMovimento.negativador.cliente.nome"/>" ></span></b></strong>
			                   </logic:present>
						   </td>
		                 </tr>
		                  <tr>
		                   <td><strong>Cliente Negativado: <font color="#FF0000"> </font></strong></td>
		                   <td>
			                   <logic:present name="negativadorMovimentoReg" property="cliente.nome" >
			                   		<strong><b><span class="style1">
			                  		<input name="nega" disabled type="text" size="50" maxlength="50" style="background-color:#EFEFEF; border:0"  
			                  		value="<bean:write name="negativadorMovimentoReg" property="cliente.nome"/>" ></span></b></strong>
			                   </logic:present>
			                   <INPUT TYPE="checkbox" NAME="cliente"
								value="<%="" + negativadorMovimentoReg.getCliente().getId() %>">
						   </td>
		                 </tr>
						 <tr>
		                  <td><strong>Data de Envio da Negativação: <font color="#FF0000"> </font></strong></td>
		                  <td>	
			                  <logic:present name="negativadorMovimentoReg" property="negativadorMovimento.dataEnvio">
								<strong><b><span class="style1">
		                  		<input name="nega" disabled type="text" size="10" maxlength="10" style="background-color:#EFEFEF; border:0"  
		                  		value="<bean:write name="negativadorMovimentoReg" format="dd/MM/yyyy" property="negativadorMovimento.dataEnvio"/>" ></span></b></strong>
							  </logic:present>
						  </td>
		                </tr>
		                <tr>
		                  <td><strong>Situação da Negativação: <font color="#FF0000"> </font></strong></td>
		                  <td>
			                  <logic:present name="helper" property="situacaoNegativacao"> 
	   		                  	<strong><b><span class="style1">
		                  		<input name="situacaoNegativacao2" disabled type="text" size="20" maxlength="50" style="background-color:#EFEFEF; border:0"  
		                  		value="<bean:write name="helper" property="situacaoNegativacao" />" ></span></b></strong>
			                  </logic:present>
						  </td>
		                </tr>
						<tr>
		                  <td><strong>Valor do Débito: <font color="#FF0000"> </font></strong></td>
		                  <td>
			                  <logic:present name="negativadorMovimentoReg" property="valorDebito">
			                  <bean:define  name="negativadorMovimentoReg" property="valorDebito" id="valor" />
			                     <strong><b><span class="style1">
			                  		<input name="vlDebito" disabled type="text" size="20" maxlength="20" style="background-color:#EFEFEF; border:0"  
			                  		value="<%=  gcom.util.Util.formataBigDecimal((java.math.BigDecimal)valor,2,true)%>" ></span></b></strong>        
			                  </logic:present>
						  </td>
		                </tr>
						
						   <!-- inicio Contas do Débito da Negativação -->
		                <tr>
		                  <td colspan="2">
		                  <table width="100%" border="1">
		                    <tr bordercolor="#90c7fc">
		                      <td colspan="5" bgcolor="#90c7fc"><strong>Contas do Débito da Negativação</strong></td>
		                    </tr>
		                    <tr bordercolor="#000000">
		                      <td width="15%" bgcolor="#90c7fc"><div align="center"><strong>Mês/Ano</strong></div></td>
		                      <td width="18%" bgcolor="#90c7fc"><div align="center"><strong>Vencimento</strong></div></td>
		                      <td width="25%" bgcolor="#90c7fc"><div align="center"><strong>Valor do Débito </strong></div></td>
		                      <td width="18%" bgcolor="#90c7fc"><div align="center"><strong>Sit. de Cobrança </strong></div></td>
		                      <td width="24%" bgcolor="#90c7fc"><div align="center"><strong>Data da Sit. de Cobrança</strong></div></td>
		                    </tr>
							<% 
							int quantidade = 0;
							double valor = 0;
							%>     
	
							                 
							<logic:present name="helper"  property="itensConta">
							<logic:iterate name="helper"  property="itensConta" id="itensConta" >
								<% 
								quantidade = quantidade +1 ;
								double valorCorrente = 0;
								if (((gcom.cobranca.NegativadorMovimentoRegItem)itensConta).getValorDebito() != null) {
									valorCorrente = ((gcom.cobranca.NegativadorMovimentoRegItem)itensConta).getValorDebito().doubleValue();
								}
								valor = valor + valorCorrente;
								%>
			                    <tr bordercolor="#90c7fc">
			                      <td><div align="center"><strong>
			                      	<logic:present name="itensConta" property="contaGeral" >
			                      	<logic:present name="itensConta" property="contaGeral.contaHistorico" >
				                    <logic:present name="itensConta" property="contaGeral.contaHistorico.anoMesReferenciaConta" >
				                      <%=Util.formatarMesAnoReferencia(((gcom.cobranca.NegativadorMovimentoRegItem)itensConta).getContaGeral().getContaHistorico().getAnoMesReferenciaConta())%>
				                    </logic:present>
				                    </logic:present>
				                    </logic:present>
			                      	<logic:present name="itensConta" property="contaGeral" >
			                      	<logic:present name="itensConta" property="contaGeral.conta" >
				                    <logic:present name="itensConta" property="contaGeral.conta.referencia" >
				                      <%=Util.formatarMesAnoReferencia(((gcom.cobranca.NegativadorMovimentoRegItem)itensConta).getContaGeral().getConta().getReferencia())%>
				                    </logic:present>
				                    </logic:present>
				                    </logic:present>
								  </strong></div></td>
			                      <td><div align="center">
			                      	<logic:present name="itensConta" property="contaGeral" >
			                      	<logic:present name="itensConta" property="contaGeral.conta" >
				                    <logic:present name="itensConta" property="contaGeral.conta.dataVencimentoConta" >
				                      <bean:write name="itensConta" property="contaGeral.conta.dataVencimentoConta" format="dd/MM/yyyy" />
				                    </logic:present>
				                    </logic:present>
				                    </logic:present>
			                      	<logic:present name="itensConta" property="contaGeral" >
			                      	<logic:present name="itensConta" property="contaGeral.contaHistorico" >
				                    <logic:present name="itensConta" property="contaGeral.contaHistorico.dataVencimentoConta" >
				                      <bean:write name="itensConta" property="contaGeral.contaHistorico.dataVencimentoConta" format="dd/MM/yyyy" />
				                    </logic:present>
				                    </logic:present>
				                    </logic:present>
				                      &nbsp;
			                      </div></td>
			                      <td><div align="center">
			                      	<%=gcom.util.Util.formataBigDecimal(new java.math.BigDecimal(valorCorrente),2,true) %> &nbsp;
				                  </div></td>
			                      <td><div align="center">
				                      <logic:present name="itensConta" property="cobrancaDebitoSituacao.descricao" >
				                      	<bean:write name="itensConta" property="cobrancaDebitoSituacao.descricao" />
				                      </logic:present>
				                      &nbsp;
								  </div></td>
			                      <td><div align="center">
				                      <logic:present name="itensConta" property="dataSituacaoDebito" >
				                      	<bean:write name="itensConta" property="dataSituacaoDebito" format="dd/MM/yyyy" />
				                      </logic:present>
				                      &nbsp;
							      </div></td>
			                    </tr>
							</logic:iterate>
							</logic:present>
		
		                    <tr bordercolor="#90c7fc">
		                      <td><div align="center"> <strong>TOTAL</strong></div></td>
		                      <td><div align="center"><%=quantidade %> doc(s) </div></td>
		                      <td><div align="center"><%=gcom.util.Util.formataBigDecimal(new java.math.BigDecimal(valor),2,true) %></div></td>
		                      <td>&nbsp;</td>
		                      <td>&nbsp;</td>
		                    </tr>
		                  </table>                  
		                  </td>
		                </tr>
		                <!-- fim Contas do Débito da Negativação -->
		                <!-- inicio Guias de Pagamento do Débito da Negativação  -->
		                <tr>
		                  <td colspan="2">
		                  <table width="100%" border="1">
		                      <tr bordercolor="#90c7fc">
		                        <td colspan="6" bgcolor="#90c7fc"><strong>Guias de Pagamento  do Débito da Negativação </strong></td>
		                      </tr>
		                      <tr bordercolor="#000000">
		                        <td width="36%" bgcolor="#90c7fc"><div align="center"><strong>Tipo do Débito</strong></div></td>
		                        <td width="8%" bgcolor="#90c7fc"><div align="center"><strong>Data de Emissão</strong></div></td>
		                        <td width="19%" bgcolor="#90c7fc"><div align="center"><strong>Data de Vencimento</strong></div></td>
		                        <td width="25%" bgcolor="#90c7fc"><div align="center"><strong>Valor do Débito </strong></div></td>
		                        <td width="18%" bgcolor="#90c7fc"><div align="center"><strong>Sit. de Cobrança </strong></div></td>
		                        <td width="24%" bgcolor="#90c7fc"><div align="center"><strong>Data da Sit. de Cobrança</strong></div></td>
		                      </tr>
							<% 
							quantidade = 0;
							valor = 0;
							%>  
							
							<logic:present name="helper"  property="itensGuiaPagamento">
							<logic:iterate name="helper"  property="itensGuiaPagamento" id="itensGuiaPagamento" >
							<% 
							quantidade = quantidade +1 ;
							double valorCorrente = 0;
							if (((gcom.cobranca.NegativadorMovimentoRegItem)itensGuiaPagamento).getValorDebito() != null) {
								valorCorrente = ((gcom.cobranca.NegativadorMovimentoRegItem)itensGuiaPagamento).getValorDebito().doubleValue();
							}
							valor = valor + valorCorrente;
							%>
		
		                      <tr bordercolor="#90c7fc">
		                        <td><div align="center"><strong><a href="guia_pagamento_consultar.htm">Taxa de Cobran&ccedil;a</a>&nbsp;</strong></div></td>
		                        <td><div align="center">99/99/9999&nbsp;</div></td>
		                        <td><div align="center">99/99/9999&nbsp;</div></td>
		                        <td><div align="center"><%=gcom.util.Util.formataBigDecimal(new java.math.BigDecimal(valorCorrente),2,true) %></div></td>
		                        <td><div align="center"><logic:present name="itensConta" property="cobrancaDebitoSituacao.descricao" ><bean:write name="itensConta" property="cobrancaDebitoSituacao.descricao" /></logic:present>&nbsp;</div></td>
		                        <td><div align="center">	                      
		                        <logic:present name="itensConta" property="dataSituacaoDebito" ><bean:write name="itensConta" property="dataSituacaoDebito" format="dd/MM/yyyy" /></logic:present>
			                      &nbsp;
								</div></td>
		                      </tr>
							</logic:iterate>
							</logic:present>
		
		                      <tr bordercolor="#90c7fc">
		                        <td><div align="center"> <strong>TOTAL</strong></div></td>
		                        <td colspan="2"><div align="center"><%=quantidade %> doc(s) </div></td>
		                        <td><div align="center"><%=gcom.util.Util.formataBigDecimal(new java.math.BigDecimal(valor),2,true) %></div></td>
		                        <td>&nbsp;</td>
		                        <td>&nbsp;</td>
		                      </tr>
		                  </table>
		                  </td>
		                </tr>
		                <!-- fim Guias de Pagamento do Débito da Negativação  -->
								
						
					</logic:iterate>
				</logic:present>	
                 <!-- fim Negativação por período -->
                
                
              
             
   </table>                
     </div>
  </td>
</tr>

<tr >
  <td colspan="2">
	<div id="layerHideExclusao" style="display:block">
	  <table width="100%" border="0" >
                <tr>
                  <td colspan="2" bordercolor="#FFFFFF" bgcolor="#79BBFD"><div align="center"><strong><strong>

                  <logic:notEqual property="inscricaoImovel" name="ExcluirNegativacaoOnLineActionForm" value="">

                    <logic:present  name="imovel" scope="session">
                  		<a href="javascript:extendeTabela('Exclusao',true);">Dados da Exclus&atilde;o da Negativa&ccedil;&atilde;o</a>
                    </logic:present>
                    <logic:notPresent name="imovel" scope="session">
                  	  Dados da Exclus&atilde;o da Negativa&ccedil;&atilde;o
                    </logic:notPresent>
                    
                  </logic:notEqual>
                  <logic:equal property="inscricaoImovel" name="ExcluirNegativacaoOnLineActionForm" value="">
                  	Dados da Exclus&atilde;o da Negativa&ccedil;&atilde;o
                  </logic:equal>

					</strong></strong></div></td>
                </tr>
      </table>          
    </div>       
    <div id="layerShowExclusao" style="display:none">
	  <table width="100%" border="0" >
                <tr>
                  <td colspan="2" bordercolor="#FFFFFF" bgcolor="#79BBFD"><div align="center"><strong><strong><a href="javascript:extendeTabela('Exclusao',false);">Dados da Exclus&atilde;o da Negativa&ccedil;&atilde;o</a></strong></strong></div></td>
                </tr>
                <tr> 
                  <td><strong>Motivo da Exclus&atilde;o:<font color="#FF0000">*</font> <font color="#FF0000"> </font></strong></td>
                  <td><html:select property="motivoExclusao" name="ExcluirNegativacaoOnLineActionForm" >
                  		<logic:present property="collMotivoExclusao" name="ExcluirNegativacaoOnLineActionForm">
                  			<bean:define property="collMotivoExclusao" id="collMotivoExclusao" name="ExcluirNegativacaoOnLineActionForm" />
							<html:options collection="collMotivoExclusao" labelProperty="descricaoExclusaoMotivo" property="id"/>
				  		</logic:present>
					  </html:select></td>
				</span></td>
                </tr>
                <tr>
                  <td><strong>Data da Exclus&atilde;o:<font color="#FF0000">*</font></strong></td>
                  <td>					
                  <div align="left">    
                   <html:text property="dataExclusao" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('ExcluirNegativacaoOnLineActionForm', 'dataExclusao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
				
                </td>
						
						
						
                </tr>
                <tr>
                  <td><strong>Usu&aacute;rio Respons&aacute;vel:<font color="#FF0000">*</font></strong></td>

                  <td><strong><b><span class="style1">
				    <input type="hidden" name="usuarioLimpo" value="0">
					<html:text maxlength="11" property="idUsuario" size="11" onkeypress="javascript:return validaEnterString(event, 'exibirExcluirNegativacaoOnLineAction.do', 'idUsuario');"/>
                    <img onclick="abrirPopup('exibirUsuarioPesquisar.do', 250, 495);" width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
                    
   		      		<logic:present property="usuarioNaoEncontrada" name="ExcluirNegativacaoOnLineActionForm" >
	   		      		<logic:equal property="usuarioNaoEncontrada" name="ExcluirNegativacaoOnLineActionForm" value="true">
							<input type="text" name="nomeUsuario" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="pesquisa.usuario.inexistente"/>"/>
	                    </logic:equal>
	   		      		<logic:equal property="usuarioNaoEncontrada" name="ExcluirNegativacaoOnLineActionForm" value="false">
							<html:text name="ExcluirNegativacaoOnLineActionForm" property="nomeUsuario" size="40" maxlength="50" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
	                    </logic:equal>
	   		      		<logic:equal property="usuarioNaoEncontrada" name="ExcluirNegativacaoOnLineActionForm" value="">
	   		      			<input type="text" name="nomeUsuario" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value=""/>
	                    </logic:equal>
	                </logic:present>    
   		      		<logic:notPresent property="usuarioNaoEncontrada" name="ExcluirNegativacaoOnLineActionForm" >
   		      			<input type="text" name="nomeUsuario" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value=""/>
   		      		</logic:notPresent>
                     
                    <a href="javascript:limparUsuario();">
                    	<img  border="0" src="imagens/limparcampo.gif" height="21" width="23"> 
                    </a>
</span></b> </strong></td>
                </tr>

                <tr>
                  <td><strong> <font color="#FF0000"></font></strong></td>
                  <td><font color="#FF0000">*</font> Campos obrigat&oacute;rios </td>
                </tr>
                <tr> 
                  <td><strong> <font color="#FF0000"></font></strong></td>

                  <td>&nbsp;</td>
                </tr>
   </table>                
  </div>                
  </td>
</tr>
                
                <tr> 
                  <td>                    
                    <input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar" align="left"
								onClick="window.location.href='/gsan/telaPrincipal.do'"/>
					<input name="Button" type="button" class="bottonRightCol" value="Limpar"  align="left"
								onclick="window.location.href='/gsan/exibirExcluirNegativacaoOnLineAction.do?menu=sim'" tabindex="22"/>								
                  </td>
                  <td align="right"> 
                  	<logic:present  name="imovel" scope="session">
						<input name="Atualizar" type="button" class="bottonRightCol" id="Atualizar" value="Excluir" onclick="javascript:validateExcluirNegativacaoOnLineActionForm();">
						</logic:present>
						
						<logic:notPresent  name="imovel" scope="session">
						<input name="Atualizar" type="button" class="bottonRightCol" id="Atualizar" value="Excluir" disabled="disabled">
						</logic:notPresent>
                  </td>
                </tr>
              </form>
            </table>
            <p class="style1">&nbsp;</p></tr>

      </table>
 






	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
