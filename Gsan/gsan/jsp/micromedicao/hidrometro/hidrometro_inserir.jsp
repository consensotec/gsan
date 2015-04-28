<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gsan.micromedicao.hidrometro.Hidrometro"%>
<%@ page import="gsan.util.ConstantesSistema"%>

<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="HidrometroActionForm" dynamicJavascript="false" />
<script language="JavaScript">
//<!-- Begin
	 var bCancel = false;

     function validateHidrometroActionForm(form) {
        if (bCancel)
      return true;
        else {
            var mensagem = validarCamposMacroMicro(form);
            
            if (mensagem != null && mensagem != '') {
                alert(mensagem);
                return false;
            }
            
       		return validateRequired(form) 
       			&& validarIndicadorOperacional()
       			&& validateCaracterEspecial(form) 
       			&& validateDate(form) 
       			&& validateLong(form) 
       			&& testarCampoValorZero(form.fixo, 'Fixo da Numeração dos Hidrômetros') 
       			&& testarCampoValorZero(form.anoFabricacao, 'Data Fabricação')
       			&& testarCampoValorZero(form.idLocalArmazenagem, 'Local de Armazenagem');
        }
    }

    function required () {
     this.aa = new Array("dataAquisicao", "Informe Data de Aquisição.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("anoFabricacao", "Informe Ano de Fabricação.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idHidrometroClasseMetrologica", "Informe Classe Metrológica.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("idHidrometroMarca", "Informe Marca.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("idHidrometroDiametro", "Informe Diâmetro.", new Function ("varName", " return this[varName];"));
     this.af = new Array("idHidrometroCapacidade", "Informe Capacidade.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idNumeroDigitosLeitura", "Informe Número de Digitos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("idLocalArmazenagem", "Informe Local de Armazenagem.", new Function ("varName", " return this[varName];"));
     
    }

	function validarCamposMacroMicro(form){
		var mensagem = "";
		if (form.indicadorMacromedidor[0].checked == true) {
			if (form.tombamento.value == null 
					|| form.tombamento.value == '') {
				mensagem = mensagem + "Informe Tombamento.\n";
			}
			if (form.idHidrometroFatorCorrecao.value == null 
					|| form.idHidrometroFatorCorrecao.value == ''
					|| form.idHidrometroFatorCorrecao.value == '-1') {
				mensagem = mensagem + "Informe Erro do Macromedidor.\n";
			}
			if (form.idHidrometroClassePressao.value == null 
					|| form.idHidrometroClassePressao.value == ''
					|| form.idHidrometroClassePressao.value == '-1') {
				mensagem = mensagem + "Informe Classe de Pressão dos Hidrômetros.\n";
			}
		} else if (form.indicadorMacromedidor[1].checked == true) {
			if (form.fixo.value == null || form.fixo.value == '') {
				mensagem = mensagem + "Informe Fixo da Numeração dos Hidrômetros.\n";
			}
			if (form.faixaInicial.value == null || form.faixaInicial.value == '') {
				mensagem = mensagem + "Informe Faixa Inicial da Numeração dos Hidrômetros.\n";
			}
			if (form.faixaFinal.value == null || form.faixaFinal.value == '') {
				mensagem = mensagem + "Informe Faixa Final da Numeração dos Hidrômetros.\n";
			}
			if (form.idHidrometroTipo.value == null || form.idHidrometroTipo.value == '' || form.idHidrometroTipo.value == '-1') {
				mensagem = mensagem + "Informe Tipo de Fluxo.\n";
			}
		} else if (form.indicadorMacromedidor[1].checked == true || form.indicadorMacromedidor[2].checked == true) {
			if (form.idHidrometroTipo.value == null || form.idHidrometroTipo.value == '' || form.idHidrometroTipo.value == '-1') {
				mensagem = mensagem + "Informe Tipo de Fluxo.\n";
			}
		}

		return mensagem;
		
	}
    
    function DateValidations () {
     this.aa = new Array("dataAquisicao", "Data de Aquisição inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }

    function caracteresespeciais () {
     this.aa = new Array("dataAquisicao", "Data de Aquisição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("anoFabricacao", "Ano de Fabricação possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idLocalArmazenagem", "Local de Armazenagem possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("fixo", "Fixo da Numeração dos Hidrômetros possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("tempoGarantiaAnos", "Tempo de Garantia em Anos possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("notaFiscal", "Nota Fiscal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.aa = new Array("anoFabricacao", "Ano de Fabricação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLocalArmazenagem", "Local de Armazenagem deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("tempoGarantiaAnos", "Tempo de Garantia em Anos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("notaFiscal", "Nota Fiscal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function limparLocalArmazenagem(){
     var form = document.HidrometroActionForm;
      form.idLocalArmazenagem.value = '';
      form.localArmazenagemDescricao.value = '';
    }



/*function verificarQuantidadeFaixa() {

    var form = document.HidrometroActionForm;
    
    var confirmou = false;
    
    var faixaInicial = parseInt(form.faixaInicial.value, 10);
    
    var faixaFinal = parseInt(form.faixaFinal.value, 10);
    
    var quantidadeHidrometro = (faixaFinal - faixaInicial) + 1;
    
    confirmou = confirm(quantidadeHidrometro+' Hidrometro(s) serão inseridos?');

    return confirmou;
    
  }*/
  
  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];

	   if (tipoConsulta == 'hidrometroLocalArmazenagem') {
	      form.idLocalArmazenagem.value = codigoRegistro;
	      form.localArmazenagemDescricao.value = descricaoRegistro;
	      form.localArmazenagemDescricao.style.color = "#000000";
	    }
	    
   }
  
  
  
  //Validacao Adicionada por Romulo Aurelio 24/05/2007 
  //[FS0007]-Montar ano de fabricacao
/*function validarAnoFabricacao(){
		var form = document.forms[0];
		
		var dataAtual = new Date();
		var anoDataAtual = dataAtual.getFullYear();
		//var finalAno = anoDataAtual.substring(2,3);
		var anoFabricacao = trim(form.anoFabricacao.value);
		var fixo = trim(form.fixo.value);
		var ano = parseInt(fixo.substring(1,3));
		
		if(fixo.substring(1,2) == '0'){
			ano = parseInt(fixo.substring(2,3));
		}	
		
		if(ano >= 85){
		form.anoFabricacao.value = '19' + ano; 
		}
		if(ano >= 00 && ano < 85 &&  anoDataAtual >= 2000 + ano){
			form.anoFabricacao.value = 2000+ ano; 
		}
		if(anoDataAtual < 2000 + ano && ano < 85){
			alert('Ano de fabricação inválido');
			form.fixo.focus();
			}
		
}*/

 //Validacao Adicionada por Romulo Aurelio 24/05/2007 
  //[FS0007]-Montar ano de fabricacao
function validarAnoFabricacao(){
		var form = document.forms[0];

		if (form.fixo.value != null && form.fixo.value != "") {
			var fixo = trim(form.fixo.value);
			var dataAtual = new Date();
			var anoDataAtual = dataAtual.getFullYear();
			var anoFabricacao = trim(form.anoFabricacao.value);
			var anoAtualCompleto = ''+ anoDataAtual;
			var anoDataAtual = parseInt(anoAtualCompleto.substring(2,4));
	
			var ano = parseInt(fixo.substring(1,3));
			if(fixo.substring(1,2) == '0'){
				ano = parseInt(fixo.substring(2,3));
			}
			
			if(!((fixo.substring(1,2)== '0' || fixo.substring(1,2)== '1' ||
			fixo.substring(1,2)== '2' ||fixo.substring(1,2)== '3' ||
			fixo.substring(1,2)== '4' || fixo.substring(1,2)== '5' ||
			fixo.substring(1,2)== '6' ||fixo.substring(1,2)== '7' ||
			fixo.substring(1,2)== '8' ||fixo.substring(1,2)== '9')&& 
			(fixo.substring(2,3)=='0' || fixo.substring(2,3)== '1' ||
			fixo.substring(2,3)== '2' ||fixo.substring(2,3)== '3' ||
			fixo.substring(2,3)== '4' || fixo.substring(2,3)== '5' ||
			fixo.substring(2,3)== '6' ||fixo.substring(2,3)== '7' ||
			fixo.substring(2,3)== '8' ||fixo.substring(2,3)== '9')))   {
			form.anoFabricacao.value = ' ';
			alert('Informe ano numérico');
			form.anoFabricacao.value = ' ';
							
			}else{
			
			//form.anoFabricacao.value = 1900 + ano;
			if(ano<60){
				form.anoFabricacao.value = 2000 + ano; 
			}else{
				form.anoFabricacao.value = 1900 + ano; 
			}
			if(ano >= 85){
			form.anoFabricacao.value = 1900 + ano; 
			}
			if(ano >= 00 &&  ano <= anoDataAtual){
				form.anoFabricacao.value = 2000 + ano; 
			}
			
			if(anoDataAtual < ano && form.anoFabricacao.value > parseInt(anoAtualCompleto)){
					form.anoFabricacao.value = '';
					alert('Ano de fabricação inválido');
			}else
			if(form.anoFabricacao.value  < 1985)	{
					form.anoFabricacao.value = '';
					alert('Ano de fabicação de ser igual ou superior a 1985.');
				
				}
			}
		}
}

function limparAnoFabricacao(){
	var form = document.forms[0];
	form.anoFabricacao.value = '';
} 

function validarForm(form){
        if (validateHidrometroActionForm(form)){
            
		    var mensagem = 'deve ser diferente de zero.'
		    var anoFabricacao = trim(form.anoFabricacao.value);

			if (form.indicadorMacromedidor[0].checked == true) {

                if (anoFabricacao == 0 ) {
				    alert('Ano de Fabricação '+ mensagem);
                } else {
                	botaoAvancarTelaEspera('/gsan/inserirHidrometroAction.do');
            	} 
                
			} else if (form.indicadorMacromedidor[1].checked == true) {
	           var fixo = form.fixo.value;
			   var faixaFinal = form.faixaFinal.value;
	           var faixaInicial =  form.faixaInicial.value;
	           var intervalo = (faixaFinal - faixaInicial) + 1;
	           var fixo =  trim(form.fixo.value);
			   var flag = true;
	    
	   		   var faixaInicial = parseInt(form.faixaInicial.value, 10);
	    
			   var faixaFinal = parseInt(form.faixaFinal.value, 10);
			   
			   var quantidadeHidrometro = (faixaFinal - faixaInicial) + 1;
	 
			   if ((faixaInicial > 0) && (faixaFinal > 0)) {
	  		       if (intervalo > 0) {
	    		       if  (fixo.length < 4) {
		    	           alert("Fixo da Numeração dos Hidrômetros deve conter no mínimo 4 caracteres");
	                   } else {
	
	                       if (anoFabricacao == 0 )
	                       {
							   alert('Ano de Fabricação '+ mensagem);
							   flag = false;
	                       } 
	                       
						   if (fixo == 0)
						   {
							   alert('Fixo da Numeração dos Hidrômetros ' + mensagem);
							   flag = false;
						   }
	
							if (flag == true)
							{
	                  	       if (confirm(quantidadeHidrometro+' Hidrometro(s) serão inseridos?')) {
	                  	          botaoAvancarTelaEspera('/gsan/inserirHidrometroAction.do');
	                  	       }
	                        }
	                   }
	  		       } else {
	    	           alert("Faixa Final da Numeração dos Hidrômetros deve ser maior ou igual a Faixa Inicial.");
			       }
	           } else {
		           alert("Faixa Inicial e Faixa Final da Numeração dos Hidrômetros devem somente conter números positivos.");
			   }
			} else if (form.indicadorMacromedidor[2].checked == true) {
				botaoAvancarTelaEspera('/gsan/inserirHidrometroAction.do');
			}
	  }
}


	function validarIndicadorOperacional(){

	    var form = document.forms[0];
	    var retorno = true;
	    
	    var indice;
	    var array = new Array(form.indicadorOperacional.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorOperacional") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Finalidade.');
			indicadorOperacional.focus();
			retorno = false;
		}	
		
		
		return retorno;
	}	

	
	function bloqueiaDados(){
		
		var form = document.forms[0];
		
		if (form.indicadorMacromedidor[0].checked == false
				&& form.indicadorMacromedidor[1].checked == false
				&& form.indicadorMacromedidor[2].checked == false) {
			
			form.indicadorMacromedidor[1].checked = true;
			document.getElementById('ligacaoEsgoto').style.display = "none";
			document.getElementById('numeracaoHidrometro').style.display = "table-row";
			document.getElementById('numeracaoFixo').style.display = "table-row";
			document.getElementById('numeracaoFaixa').style.display = "table-row";
		}
		
		if (form.indicadorMacromedidor[0].checked == true) {
			
			document.getElementById('ligacaoEsgoto').style.display = "none";
			document.getElementById('numeracaoHidrometro').style.display = "table-row";
			document.getElementById('numeracaoFixo').style.display = "table-row";
			document.getElementById('numeracaoFaixa').style.display = "table-row";
			
			form.numeroHidrometro.value = "";
			form.numeroHidrometro.style.color = "#000000";
			form.numeroHidrometro.readOnly = true;
			form.numeroHidrometro.style.backgroundColor = '#EFEFEF';
			
			form.fixo.value = "";
			form.fixo.style.color = "#000000";
			form.fixo.readOnly = true;
			form.fixo.style.backgroundColor = '#EFEFEF';
			
			form.faixaInicial.value = "";
			form.faixaInicial.style.color = "#000000";
			form.faixaInicial.readOnly = true;
			form.faixaInicial.style.backgroundColor = '#EFEFEF';
			
			form.faixaFinal.value = "";
			form.faixaFinal.style.color = "#000000";
			form.faixaFinal.readOnly = true;
			form.faixaFinal.style.backgroundColor = '#EFEFEF';
			
			form.anoFabricacao.style.color = "#000000";
			form.anoFabricacao.readOnly = false;
			form.anoFabricacao.style.backgroundColor = '';
			
			form.idHidrometroTipo.value = "-1";
			form.idHidrometroTipo.style.color = "#000000";
			form.idHidrometroTipo.disabled = true;
			form.idHidrometroTipo.readOnly = true;
			form.idHidrometroTipo.style.backgroundColor = '#EFEFEF';
			
			form.idHidrometroRelojoaria.value = "-1";
			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = true;
			form.idHidrometroRelojoaria.readOnly = true;
			form.idHidrometroRelojoaria.style.backgroundColor = '#EFEFEF';

			form.idHidrometroFatorCorrecao.style.color = "#000000";
			form.idHidrometroFatorCorrecao.disabled = false;
			form.idHidrometroFatorCorrecao.readOnly = false;
			form.idHidrometroFatorCorrecao.style.backgroundColor = '';

			form.idHidrometroClassePressao.style.color = "#000000";
			form.idHidrometroClassePressao.disabled = false;
			form.idHidrometroClassePressao.readOnly = false;
			form.idHidrometroClassePressao.style.backgroundColor = '';
			
		}
		
		if (form.indicadorMacromedidor[1].checked == true) {

			document.getElementById('ligacaoEsgoto').style.display = "none";
			document.getElementById('numeracaoHidrometro').style.display = "table-row";
			document.getElementById('numeracaoFixo').style.display = "table-row";
			document.getElementById('numeracaoFaixa').style.display = "table-row";
			
			form.numeroHidrometro.value = "";
			form.numeroHidrometro.style.color = "#000000";
			form.numeroHidrometro.readOnly = true;
			form.numeroHidrometro.style.backgroundColor = '#EFEFEF';
			
			form.fixo.style.color = "#000000";
			form.fixo.readOnly = false;
			form.fixo.style.backgroundColor = '';

			form.faixaInicial.style.color = "#000000";
			form.faixaInicial.readOnly = false;
			form.faixaInicial.style.backgroundColor = '';

			form.faixaFinal.style.color = "#000000";
			form.faixaFinal.readOnly = false;
			form.faixaFinal.style.backgroundColor = '';
			
			form.anoFabricacao.value = "";
			form.anoFabricacao.style.color = "#000000";
			form.anoFabricacao.readOnly = true;
			form.anoFabricacao.style.backgroundColor = '#EFEFEF';

			form.idHidrometroTipo.style.color = "#000000";
			form.idHidrometroTipo.disabled = false;
			form.idHidrometroTipo.readOnly = false;
			form.idHidrometroTipo.style.backgroundColor = '';

			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = false;
			form.idHidrometroRelojoaria.readOnly = false;
			form.idHidrometroRelojoaria.style.backgroundColor = '';
			
			form.idHidrometroFatorCorrecao.value = "-1";
			form.idHidrometroFatorCorrecao.style.color = "#000000";
			form.idHidrometroFatorCorrecao.disabled = true;
			form.idHidrometroFatorCorrecao.readOnly = true;
			form.idHidrometroFatorCorrecao.style.backgroundColor = '#EFEFEF';
			
			form.idHidrometroClassePressao.value = "-1";
			form.idHidrometroClassePressao.style.color = "#000000";
			form.idHidrometroClassePressao.disabled = true;
			form.idHidrometroClassePressao.readOnly = true;
			form.idHidrometroClassePressao.style.backgroundColor = '#EFEFEF';
		}

		if (form.indicadorMacromedidor[2].checked == true) {

			document.getElementById('ligacaoEsgoto').style.display = "table-row";
			document.getElementById('numeracaoHidrometro').style.display = "none";
			document.getElementById('numeracaoFixo').style.display = "none";
			document.getElementById('numeracaoFaixa').style.display = "none";
			
			form.numeroHidrometro.readOnly = false;
			form.numeroHidrometro.style.backgroundColor = '';
			
			form.fixo.value = "";
			form.fixo.readOnly = true;
			
			form.faixaInicial.value = "";
			form.faixaInicial.readOnly = true;
			
			form.faixaFinal.value = "";
			form.faixaFinal.readOnly = true;
			
			form.anoFabricacao.style.color = "#000000";
			form.anoFabricacao.readOnly = false;
			form.anoFabricacao.style.backgroundColor = '';

			form.idHidrometroTipo.style.color = "#000000";
			form.idHidrometroTipo.disabled = false;
			form.idHidrometroTipo.readOnly = false;
			form.idHidrometroTipo.style.backgroundColor = '';

			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = false;
			form.idHidrometroRelojoaria.readOnly = false;
			form.idHidrometroRelojoaria.style.backgroundColor = '';
			
			form.idHidrometroFatorCorrecao.value = "-1";
			form.idHidrometroFatorCorrecao.style.color = "#000000";
			form.idHidrometroFatorCorrecao.disabled = true;
			form.idHidrometroFatorCorrecao.readOnly = true;
			form.idHidrometroFatorCorrecao.style.backgroundColor = '#EFEFEF';
			
			form.idHidrometroClassePressao.value = "-1";
			form.idHidrometroClassePressao.style.color = "#000000";
			form.idHidrometroClassePressao.disabled = true;
			form.idHidrometroClassePressao.readOnly = true;
			form.idHidrometroClassePressao.style.backgroundColor = '#EFEFEF';
		}
	}
	
//End -->

</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');bloqueiaDados();validarAnoFabricacao();">
<div id="formDiv"><html:form action="/inserirHidrometroAction.do"
	name="HidrometroActionForm"
	type="gsan.gui.micromedicao.hidrometro.HidrometroActionForm"
	method="post" onsubmit="return validateHidrometroActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Inserir Hidr&ocirc;metro</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para adicionar o(s) hidr&ocirc;metro(s), informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}micromedicaoHidrometroInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">
						<strong>
							<span class="style1">
								<html:radio property="indicadorMacromedidor"
									value="1" onchange="bloqueiaDados();redirecionarSubmit('exibirInserirHidrometroAction.do');"/>&nbsp;Macromedidor&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="indicadorMacromedidor"
									value="2" onchange="bloqueiaDados();redirecionarSubmit('exibirInserirHidrometroAction.do');"/>&nbsp;Micromedidor&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="indicadorMacromedidor"
									value="3" onchange="bloqueiaDados();redirecionarSubmit('exibirInserirHidrometroAction.do');"/>&nbsp;Medição de Esgoto
							</span>
						</strong>
					</td>
				</tr>
				<tr id="ligacaoEsgoto" style="${displayLigacao}">
					<td>
						<strong>Numeração dos Hidrômetros (Medição de Esgoto)</strong>
					</td>
					<td>
						<html:text maxlength="12" property="numeroHidrometro" size="13"/>
					</td>
				</tr>
				<tr id="numeracaoHidrometro" style="${displayFixoFaixa}">
					<td colspan="2"><strong>Numeração dos Hidrômetros</strong></td>
				</tr>
				<tr id="numeracaoFixo" style="${displayFixoFaixa}">
					<td>
						<strong>Fixo:</strong>
					</td>
					<td>
						<html:text maxlength="4" property="fixo" size="4" tabindex="1" onkeyup= "javascript:limparAnoFabricacao();" onblur="javascript:validarAnoFabricacao();"/>
					</td>
				</tr>
				<tr id="numeracaoFaixa" style="${displayFixoFaixa}">
					<td><strong>Faixa:</strong></td>
					<td>
						<html:text maxlength="6" property="faixaInicial" size="6"
							tabindex="2" onkeyup="return isCampoNumerico(event);" 
							onkeypress="return isCampoNumerico(event);" 
							onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].faixaInicial, 'Faixa Inicial');" 
							onblur="return isCampoNumerico(event);"/> 
							
						<html:text maxlength="6" property="faixaFinal"
							size="6" tabindex="3" onkeyup="return isCampoNumerico(event);" 
							onkeypress="return isCampoNumerico(event);" 
							onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].faixaFinal, 'Faixa Final');" 
							onblur="return isCampoNumerico(event);"/>
					</td>
				</tr>
				<tr>
					<td><strong>Tombamento:</strong></td>
					<td><html:text maxlength="10" property="tombamento" size="10" tabindex="1"/></td>
				</tr>

				<tr>
					<td><strong>Capacidade:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idHidrometroCapacidade" tabindex="4"
						onchange="redirecionarSubmit('exibirInserirHidrometroAction.do');">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroCapacidade"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Ano de Fabrica&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
					</td>
					<td><html:text maxlength="4" property="anoFabricacao" size="4" readonly="true"
						style="background-color:#EFEFEF;" onkeyup="return isCampoNumerico(event);" 
						onkeypress="return isCampoNumerico(event);" 
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].anoFabricacao, 'Ano de Fabricação');" 
						onblur="return isCampoNumerico(event);"  />aaaa</td>
				</tr>

				<tr>
					<td><strong>Marca:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idHidrometroMarca" tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroMarca"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td height="24" colspan="2" class="style1">
					<hr>
					</td>
				</tr>


				<tr>
					<td><strong>Data de Aquisi&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
					</td>
					<td><html:text property="dataAquisicao" size="10" maxlength="10"
						tabindex="6" onkeyup="mascaraData(this,event)" 
						onkeypress="mascaraData(this,event);" 
						onchange="validarCampoDataComMensagemLimpandoCampo(document.forms[0].dataAquisicao, 'Data de Aquisição');" 
						onblur="mascaraData(this,event);" /> <a
						href="javascript:abrirCalendario('HidrometroActionForm', 'dataAquisicao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td><strong>Finalidade:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"><strong> <html:radio
						property="indicadorOperacional"
						value="<%=(Hidrometro.INDICADOR_COMERCIAL).toString()%>"
						tabindex="7" /> <strong>Comercial <html:radio
						property="indicadorOperacional"
						value="<%=(Hidrometro.INDICADOR_OPERACIONAL).toString()%>" />
					Operacional </strong></strong></span></strong></td>
				</tr>
				<tr>
					<td><strong>Classe Metrológica:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idHidrometroClasseMetrologica"
						tabindex="8">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroClasseMetrologica"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Diâmetro:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idHidrometroDiametro" tabindex="9">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroDiametro"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>


				<tr>
					<td><strong>Número de Digitos:<font color="#FF0000">*</font></strong></td>
					<td><html:select name="HidrometroActionForm"
						property="idNumeroDigitosLeitura" tabindex="10">
						<html:option value="">&nbsp;</html:option>
						<logic:present name="colecaoIntervalo">
							<c:forEach items="${sessionScope.colecaoIntervalo}"
								var="numeroDigitosLeitura">
								<html:option value="${numeroDigitosLeitura}">${numeroDigitosLeitura}</html:option>
							</c:forEach>
						</logic:present>
					</html:select></td>
				</tr>
				<%--
				<tr>
					<td><strong>Número de Digitos:<font color="#FF0000">*</font></strong></td>
					<td>
						<logic:present name="colecaoIntervalo">
							<html:select name="idNumeroDigitosLeitura" tabindex="9">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoIntervalo" labelProperty="idNumeroDigitosLeitura" property="id" />
							</html:select>
						</logic:present> 
						<logic:notPresent name="colecaoIntervalo">
							<select name=idNumeroDigitosLeitura tabindex="9">
								<option>&nbsp;</option>
							</select>
						</logic:notPresent>
					</td>
				</tr>
				--%>
				<tr>
					<td><strong>Tipo de Fluxo:</strong></td>
					<td>
						<html:select property="idHidrometroTipo" tabindex="11">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoHidrometroTipo" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Relojoaria:</strong></td>
					<td><html:select property="idHidrometroRelojoaria" tabindex="12">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroRelojoaria"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Local de Armazenagem:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="idLocalArmazenagem" size="5" maxlength="3"
						tabindex="13"
						onkeypress="validaEnterComMensagem(event, 'exibirInserirHidrometroAction.do?objetoConsulta=1', 'idLocalArmazenagem', 'Local de Armazenagem');return isCampoNumerico(event);" 
						onkeyup="return isCampoNumerico(event);" 
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idLocalArmazenagem, 'Local de Armazenagem');" 
						onblur="return isCampoNumerico(event);" 
						/>
					<a
						href="javascript:abrirPopup('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=hidrometroLocalArmazenagem&caminhoRetorno=exibirInserirHidrometroAction&tipoPesquisa=retornoPopup', 250, 495);">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a> <logic:present
						name="corLocalArmazenagem">
						<logic:equal name="corLocalArmazenagem" value="exception">
							<html:text property="localArmazenagemDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corLocalArmazenagem" value="exception">
							<html:text property="localArmazenagemDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corLocalArmazenagem">
						<logic:empty name="HidrometroActionForm"
							property="idLocalArmazenagem">
							<html:text property="localArmazenagemDescricao" size="45"
								value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="HidrometroActionForm"
							property="idLocalArmazenagem">
							<html:text property="localArmazenagemDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparLocalArmazenagem();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>

				<tr>
					<td><strong>Vazão Transição:</strong></td>
					<td><html:text maxlength="6" 
								property="vazaoTransicao"
								size="6"
								tabindex="2" 
								onkeypress="formataValorMonetario( this, 6 );" 
								onkeyup="formataValorMonetario( this, 6 );" 
								onchange="formataValorMonetario( this, 6 );" 
								onblur="formataValorMonetario( this, 6 );" 
								style="text-align: right;" /> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Vazão Nominal:</strong></td>
					<td><html:text maxlength="6" 
								property="vazaoNominal" 
								size="6"
								tabindex="2" 
								onkeypress="formataValorMonetario( this, 6 );" 
								onkeyup="formataValorMonetario( this, 6 );" 
								onchange="formataValorMonetario( this, 6 );" 
								onblur="formataValorMonetario( this, 6 );" 
								style="text-align: right;" /> 
				    </td>
				</tr>
				
				<tr>
					<td><strong>Vazão Mínima:</strong></td>
					<td><html:text maxlength="6"
								 property="vazaoMinima" 
								 size="6"
								 tabindex="2" 
								 onkeypress="formataValorMonetario( this, 6 );" 
								 onkeyup="formataValorMonetario( this, 6 );" 
								 onchange="testarCampoValorZeroDecimal(document.forms[0].vazaoMinima, 'Vazão Mínima');" 
								 onblur="formataValorMonetario( this, 6 );" 
								 style="text-align: right;"/> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Nota Fiscal:</strong></td>
					<td><html:text maxlength="9" property="notaFiscal" size="9"
						onkeypress="return isCampoNumerico(event);" 
						onkeyup="return isCampoNumerico(event);" 
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].notaFiscal, 'Nota Fiscal');" 
						onblur="return isCampoNumerico(event);" 
						tabindex="2" /> </td>
				</tr>
				
				<tr>
					<td><strong>Tempo de Garantia em Anos:</strong></td>
					<td><html:text maxlength="4" property="tempoGarantiaAnos" size="4"
						onkeypress="return isCampoNumerico(event);" 
						onkeyup="return isCampoNumerico(event);" 
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].tempoGarantiaAnos, 'Tempo de Garantia em Anos');" 
						onblur="return isCampoNumerico(event);" 
						tabindex="2" /> </td>
				</tr>

				<tr>
					<td><strong>Erro do Macromedidor:<font color="#FF0000"></font></strong></td>
					<td><html:select property="idHidrometroFatorCorrecao" tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroFatorCorrecao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Classe de Pressão:<font color="#FF0000"></font></strong></td>
					<td><html:select property="idHidrometroClassePressao" tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroClassePressao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td><input type="button" name="Button" class="bottonRightCol"
						value="Limpar" tabindex="14"
						onClick="javascript:window.location.href='/gsan/exibirInserirHidrometroAction.do?menu=sim'"
						style="width: 80px" /> &nbsp; <input type="button" name="Button"
						class="bottonRightCol" value="Cancelar" tabindex="6"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
						style="width: 80px" /></td>
					<td align="right"><gsan:controleAcessoBotao name="Button"
						value="Inserir"
						onclick="javascript:validarForm(document.HidrometroActionForm);"
						url="inserirHidrometroAction.do" /> <!-- 					<input type="button" class="bottonRightCol" value="Inserir" tabindex="15" onclick="validarForm(document.HidrometroActionForm);"> -->
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>

</body>
</html:html>
