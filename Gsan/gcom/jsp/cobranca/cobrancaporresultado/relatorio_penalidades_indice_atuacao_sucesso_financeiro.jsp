<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page isELIgnored="false"%>
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
	formName="ConsultarArquivoTextoLeituraActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
</style>

<script language="JavaScript">


	function validaForm(){
		form = document.forms[0];
		if(form.idEmpresaContratada.value == '-1'){
			alert('Informe Empresa');
			return false;
		}
		if(form.dataPeriodoExInicial.value == ''){
			alert('Informe Data Inicial do Período de Execução');
			return false;
		}
		if(form.dataPeriodoExFinal.value == ''){
			alert('Informe Data Final do Período de Execução');
			return false;
		}
		
		form.submit();
	}
	 
		 
	 function campoNumerico(campo) {
		var value = campo.value;
	    var bool = isNaN(+value);
	    bool = bool || (value.indexOf('.') != -1);
	    bool = bool || (value.indexOf(",") != -1);
	    if(bool && value.indexOf("/") == -1)
	    	campo.value = '';
	}
	 
	 function verificaData(event){
			
	       	var valor = null;
	     	if (event.which == null){
	        	valor= String.fromCharCode(event.keyCode);   
			}else if (event.which != 0 && event.charCode != 0){
				valor= String.fromCharCode(event.which);
			}   
			 
			if(valor != '/'){
				return isCampoNumerico(event);
			}
		
	    }
	 
	 
	 function replicar(origem, destino){
			 destino.value = origem.value;
	 }
	 
	
</script>


</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">  
<html:form action="/gerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroAction"
	name="GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm"
	type="gcom.gui.cobranca.cobrancaporresultado.GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm"
	method="post">

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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relatório de Penalidade por Índice de Atuação e Sucesso Financeiro</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="6">Para gerar o relatório de penalidade por índice de atuação e sucesso financeiro
					, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>
				
				<tr>
					<td colspan="6">&nbsp;</td>
					<td align="right"></td>
				</tr>

				<tr>

					<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="idEmpresaContratada"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoEmpresasContratadas">					
								<html:options collection="colecaoEmpresasContratadas"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</td>

				</tr>

				<tr>
                <td><strong>Período de Execução do Comando:<font color="#FF0000">*</font></strong></td>
		               <td width="70%"><strong> 
							<html:text  property="dataPeriodoExInicial" 
										size="10"
										maxlength="10" 
										tabindex="9" 
										onkeypress="return verificaData(event);"
										onkeyup="mascaraData(this, event);replicar(this,document.forms[0].dataPeriodoExFinal);" 
										/> 
								<a href="javascript:abrirCalendarioReplicando('GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm', 'dataPeriodoExInicial', 'dataPeriodoExFinal');"  style="text-decoration:none;">
									<img border="0"
										 src="<bean:message key="caminho.imagens"/>calendario.gif"
										 width="20" 
										 border="0" 
										 align="absmiddle"
										 alt="Exibir Calendário" />
								</a> </strong>
							<html:text property="dataPeriodoExFinal" 
									   size="10"
								 	   maxlength="10" 
								 	   tabindex="10" 
								 	   onkeypress="return verificaData(event);"
								 	   onkeyup="mascaraData(this, event);" /> 
								<a href="javascript:abrirCalendario('GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm', 'dataPeriodoExFinal')" style="text-decoration:none;">
									<img border="0"
										 src="<bean:message key="caminho.imagens"/>calendario.gif"
										 width="20" 
										 border="0" 
										 align="absmiddle"
										 alt="Exibir Calendário" />
							</a> (dd/mm/aaaa) </td>
	                 </tr>
                 
                 	
				<tr>
					<td>&nbsp;</td>
			    </tr>	
					
				              			
				<tr>
					<td colspan="2" ><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><%--<INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);">--%>

		  			<gsan:controleAcessoBotao name="Botao" value="Gerar"
						onclick="validaForm();"
						url="gerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroAction.do" tabindex="13" /></td>
				</tr>
				
					
				</table>
			</td></tr>
		</table>
	<p>&nbsp;</p>
		
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>