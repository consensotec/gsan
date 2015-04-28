<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page isELIgnored="false"%>
<%@ page import="gsan.util.Util"%>
<%@ page import="gsan.cadastro.imovel.ImovelProgramaEspecial"%>

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
<script>
<!--

    function recuperarDadosPopup(codigoRegistro,descricaoRegistro,tipoConsulta){

       var form = document.ConsultarHistoricoProgramaEspecialActionForm;

       if (tipoConsulta == 'imovel') {
      		form.idImovel.value = codigoRegistro;
		    form.inscricaoImovel.value = descricaoRegistro;
            form.inscricaoImovel.style.color = "#000000";
            form.action = 'exibirConsultarHistoricoProgramaEspecialAction.do'
            form.submit();
       }
       
       
    }
	
	function limparImovel(){
		var form = document.forms[0];
		
		if (form.idImovel.value!="" || form.idImovel.value=="" || form.inscricaoImovel.disabled==true) {
			form.idImovel.disabled=false;
			form.inscricaoImovel.disabled=false;
			form.idImovel.value = "";
			form.inscricaoImovel.value = "";
			form.action = 'exibirConsultarHistoricoProgramaEspecialAction.do'
	        form.submit();
			
		}
	}

	function validarEnterCampoImovelEmBranco(e){
		var form = document.forms[0];
		 tecla = (document.all) ? e.keyCode : e.which;
		    if (tecla==13 && form.idImovel.value==""){
				alert("Deve ser informado o imóvel para consulta");
			} 
		
	}
	
	function bloquearCampoMatricula() {
		var form = document.forms[0];
		if (form.idImovel.value!="" && form.inscricaoImovel.value!="") {
			form.idImovel.setAttribute('readonly',true);
			form.inscricaoImovel.disabled=true;
		}
	}

	function verificarExibicaoEndereco() {
	
		var form = document.forms[0];
		
		if ((form.idImovel.value.length < 1 || form.inscricaoImovel.value.length < 1)) {
			endereco.style.display = "none";
		}
			
	}

	function abrirPopupImovel(){
		var form = document.forms[0];
		
		if(form.inscricaoImovel.disabled==true){
			
		}else{
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
		}
		
	}

	function gerarRelatorioHistoricoImoveisProgramaEspecial(){
		var form = document.forms[0];
	
		if(form.idImovel.value!="" && form.inscricaoImovel.value!=""){
			form.action="gerarRelatorioHistoricoImoveisProgramaEspecialAction.do?idImovel="+form.idImovel.value;
			form.submit();
		}else{
			alert("Deve ser Informado o Imovél para Gerar o Relatório.");
		}
		
			
	}

-->
</script>

<style type="text/css">
input[readonly="true"]
{
    background-color:#EFEFEF;
}

</style>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');verificarExibicaoEndereco();bloquearCampoMatricula();">

<html:form action="/exibirConsultarHistoricoProgramaEspecialAction"
	name="ConsultarHistoricoProgramaEspecialActionForm"
	type="gsan.gui.cadastro.imovel.ConsultarHistoricoProgramaEspecialActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="200" valign="top" class="leftcoltext">
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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Histórico Programa Especial</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="7">Para consultar um imóvel em programa especial, informe
					os dados abaixo:</td>
				</tr>

				<tr>
					<td width="80"><strong> Matricula: </strong></td>
					<td><html:text property="idImovel" size="9" maxlength="9"
						onkeyup="validarEnterCampoImovelEmBranco(event);"
						onkeypress="validaEnter(event, 'exibirConsultarHistoricoProgramaEspecialAction.do', 
							'idImovel');return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopupImovel();">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="imovelNaoEncontrado">
						<html:text property="inscricaoImovel" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="imovelNaoEncontrado">
						<html:text property="inscricaoImovel" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Imóvel" /> </a></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>


				<tr>
					<td colspan="7">
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bgcolor="#99CCFF">
							<td>
							<div align="center"><strong>Histórico do imóvel em programa especial</strong></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				
				
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						
						<tr bgcolor="#cbe5fe">
						
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
									
									<td>
									<div align="center"><strong>Data apresentação</strong></div>
									</td>
									<td>
									<div align="center"><strong>Início</strong></div>
									</td>
									<td>
									<div align="center"><strong>Inclusão</strong></div>
									</td>
									<td>
									<div align="center"><strong>Usuário inclusão</strong></div>
									</td>
									<td>
									<div align="center"><strong>Saída</strong></div>
									</td>
									<td>
									<div align="center"><strong>Suspensão</strong></div>
									</td>
									<td>
									<div align="center"><strong>Usuário suspensão</strong></div>
									</td>
									<td>
									<div align="center"><strong>Forma suspensão</strong></div>
									</td>
									<td>
									<div align="center"><strong>Número</strong></div>
									</td>									
									
								</tr>
									<logic:present name="colecaoProgramaEspecial">
									<%int cont = 0;%>
									<logic:iterate name="colecaoProgramaEspecial" id="imovelProgramaEspecial"
										type="ImovelProgramaEspecial">

									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
										<%} else {%>
											<tr bgcolor="#FFFFFF">
										<%}%>

											
											<td>
											<div align="center" title="${imovelProgramaEspecial.descricaoDocumentos}"><%= Util.formatarData(imovelProgramaEspecial.getDataApresentacaoDocumentos())%></div>
											</td>

											<td>
											<div align="center"><%=imovelProgramaEspecial.getMesAnoInicio() %></div>
											</td>
											
											<td>
											<div align="center"><%= Util.formatarData(imovelProgramaEspecial.getDataInclusao())%></div>
											</td>
											<td>
											<div align="center" title="${imovelProgramaEspecial.usuarioResponsavel.nomeUsuario}">${imovelProgramaEspecial.usuarioResponsavel.id}</div>
											</td>
											
											<td>
											<div align="center"><%=imovelProgramaEspecial.getMesAnoSaida()%></div>
											</td>
											<td>
											<div align="center" title="${imovelProgramaEspecial.observacao}"><%= Util.formatarData(imovelProgramaEspecial.getDataSuspensao())%></div>
											</td>
											<td>
											<div align="center" title="${imovelProgramaEspecial.usuarioSuspensao.nomeUsuario}">${imovelProgramaEspecial.usuarioSuspensao.id}</div>
											</td>
											<td>
											<div align="center">${imovelProgramaEspecial.formaSuspensao}</div>
											</td>
											<td>
											<div align="center">${imovelProgramaEspecial.numeroBolsaFamilia}</div>
											</td>

										</pg:item>
									</logic:iterate>
									</logic:present>
									</table>
									<table width="100%" height="20%">
										<tr><td>&nbsp;</td></tr>
									</table>
									<table width="100%" height="20%">
										<tr><td>&nbsp;</td></tr>
									</table>
									<table width="100%" height="20%">
										<tr><td>&nbsp;</td></tr>
									</table>
									<table width="100%" height="20%">
										<tr><td>&nbsp;</td></tr>
									</table>
									<table width="100%" height="20%">
										<tr><td>&nbsp;</td></tr>
									</table>
									<table width="100%" height="20%">
										<tr><td>&nbsp;</td></tr>
									</table>
									<table width="100%" height="20%">
									
										<tr>
										
											<td valign="top">
												<div align="right"><a
												href="javascript:gerarRelatorioHistoricoImoveisProgramaEspecial();">
		 										<img border="0"
													src="<bean:message key="caminho.imagens"/>print.gif"
												title="Imprimir" /> </a></div>
											</td>
												
										</tr>
									
									</table>
						</td>
						
					</tr>
					
				</table>
			<p>&nbsp;</p>
		</td>
		
	</tr>
	
</table>


	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
