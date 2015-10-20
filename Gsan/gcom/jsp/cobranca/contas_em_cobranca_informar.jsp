<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.Collection" %>
<%@ page import="gcom.cadastro.localidade.Localidade"%>
<%@ page import="gcom.cadastro.localidade.SetorComercial"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jqgrid/jquery-ui-1.8.2.custom.css" />
  <link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jqgrid/ui.jqgrid.css" />
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InformarContasEmCobrancaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/jquery.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/grid.locale-pt-br.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.core.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.mouse.js"></script>

<%
	Collection<Localidade> localidades = (Collection<Localidade>) session.getAttribute("colecaoLocalidadesContasEmCobrancaPorEmpresa");
	Collection<SetorComercial> setores = (Collection<SetorComercial>) session.getAttribute("colecaoSetoresContasEmCobrancaPorEmpresa");
	String allLoca = null;
	String allSet = null;
	if(localidades !=null && !localidades.isEmpty()){
		for(Localidade loc : localidades){
			if(allLoca !=null){
				allLoca += loc.getId()+","+loc.getDescricao()+"::";
			}else{
				allLoca = "'"+loc.getId()+","+loc.getDescricao()+"::";
			}
		}
		allLoca+="'";
	}
	if(setores !=null && !setores.isEmpty()){
		for(SetorComercial set: setores){
			if(allSet !=null){
				allSet += set.getLocalidade().getId()+","+set.getCodigo()+","+
						  set.getDescricao()+","+set.getMunicipio().getNome()+","+
				          set.getIndicadorSetorAlternativo()+"::";
			}
			else{
				allSet = "'"+set.getLocalidade().getId()+","+set.getCodigo()+","+
						  set.getDescricao()+","+set.getMunicipio().getNome()+","+
				          set.getIndicadorSetorAlternativo()+"::";
			}
		}
		allSet +="'";
	}
%>


<script type="text/javascript">
//<![CDATA[
    jQuery(document).ready(function () {
    	
        var grid = jQuery("#grid");
        var subgrid_table_id;
        var allLoca = []; 
        var allSet = []; 
        var s = [];
        var locas;
        var sets;
        <%
        	if(allLoca !=null){
        %>
        	allLoca = null;
        	allSet = null;
        	allLoca = <%=allLoca %>;
        	allSet = <%=allSet %>;
        <% } %>
        grid.jqGrid({
            datatype: "local",
            colNames: ['Código','Descrição','Município', 'Set. Alternativo'],
            colModel: [
                       {name:'cod',index:'cod',width:90,sorttype:"int"},
                       {name:'desc',index:'desc',width:100, sorttype:"text"},
                       {name:'mun',index:'mun',width:100, sorttype:"text"},
                       {name:'set',index:'set',width:100, sorttype:"int"},
                   ],
rowNum:1000, 
rowList:[10,20,30], 
//pager: '#pager',
sortname: 'numero', 
viewrecords: true, 
sortorder: "asc",
multiselect: true,
subGrid : false,
//Caso aja uma subgrid
/*subGridRowExpanded: function(subgrid_id, row_id) {
    var pager_id;
    var dataSub = [];
    subgrid_table_id = subgrid_id+"_t";
    pager_id = "p_"+subgrid_table_id;
    $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
   // $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table>");
    jQuery("#"+subgrid_table_id).jqGrid({        
        datatype: "local",
        colNames: ['Código','Descrição','Município', 'Set. Alternativo'],
        colModel: [
            {name:'cod',index:'cod',width:90,sorttype:"int"},
            {name:'desc',index:'desc',width:100, sorttype:"text"},
            {name:'mun',index:'mun',width:100, sorttype:"text"},
            {name:'set',index:'set',width:100, sorttype:"int"},
        ],
        rowNum:20,
        pager: pager_id,
        sortname: 'name',
        sortorder: "asc",
        multiselect: true,
        height: 'auto',
        autowidth:true,
        jsonReader: { repeatitems : false, root:"attribute" }
    });
    var contSub = 0;    
    locas = allLoca.split("::");
    sets = allSet.split("::");
    index = parseInt(row_id)
    if(locas[index]!=""){
		tempLoca = locas[index].split(",");
		for(j = 0; j < sets.length; j++){
			if(sets[i]!=""){
				tempSet = sets[j].split(",");
				if(tempLoca[0] == tempSet[0]){
					dataSub[contSub] = {cod: tempSet[1], desc: tempSet[2], mun: tempSet[3], set: tempSet[4]};
					contSub++;
				}
			}
		}
		for(k =0; k < dataSub.length;k++) {
			jQuery("#"+subgrid_table_id).jqGrid('addRowData', k, dataSub[k]);
		}
		jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false});
		jQuery("#"+subgrid_table_id).trigger("reloadGrid");
		
		/*jQuery("#"+subgrid_table_id+" input[type='checkbox']").click(function(){
			var s;
			s = jQuery("#"+subgrid_table_id).jqGrid('getGridParam','selarrrow');
			alert(s);
		});	*/
    //}	
    
    
//},
drag: true,
resize: true,
width: 400, 
height: 220,
caption: "Setores"
          });

        var myData = []; 
    	var contData = 0;
    	/*if(allLoca !=null && allLoca !=""){
	    	locas = allLoca.split("::");
	    	for(i = 0; i < locas.length; i++){
	        	if(locas[i]!=""){
	    			tempLoca = locas[i].split(",");    		
					myData[contData] = {cod:tempLoca[0],desc: tempLoca[1]};
					contData++;
	        	}
	    	}
    	}*/
    	if(allSet !=null && allSet !=""){
    		sets = allSet.split("::");
    		for(j = 0; j < sets.length; j++){
    			if(sets[j]!=""){
    				tempSet = sets[j].split(",");    				
    				myData[contData] = {cod: tempSet[1], desc: tempSet[2], mun: tempSet[3], set: tempSet[4]};
    				contData++;    				 				
    			}
    		}
        }

 for(var i=0;i<myData.length;i++) {
             grid.jqGrid('addRowData', i, myData[i]);
         }

jQuery("#grid").jqGrid('navGrid','#pager',{add:false,edit:false,del:false,search:false},{},{},{
		    caption: "Delete",
		    msg: "Apagar localidade selecionada?",
		    bSubmit: "Apagar",
		    bCancel: "Cancelar",
		    url:'exibirInformarContasEmCobrancaAction.do?removeLocalidade=SIM',
		    alerttext:'Selecione uma localidade',
			    delData: {
			    	name: function() {				    	
			    		jQuery("#gbox_grid").hide();
			    		var form = document.forms[0];
			    		form.action = 'exibirInformarContasEmCobrancaAction.do';
			    		form.submit();	
            	    }
		    	}		
  			},{},{}
  );
jQuery("#grid").trigger("reloadGrid");
$("#selecionar").click(function(){
	 
	s = $("input[name='indiceSetoresSelecionadosComponent']").val().split(",");	
	
	pesquisarQuantidadeContas();	
});
/* Verifica quais setores foram
 * selecionados ou deselecionados
 * e atualiza os campos que armazenam
 * esses índices através do evento
 * de movimento do mouse
 * sobre o checkbox 
 */
$(".cbox").mousemove(function(){
	indSet = $("input[name='indiceSetoresSelecionadosComponent']").val();
	s = jQuery("#grid").jqGrid('getGridParam','selarrrow');		
	tempSet="";
	tempIndSet="";	
	if(s !=null && s !=""){
		for(i = 0; i < s.length; i++){
			tempIndSet +=s[i]+",";			
		}
		if(indSet !=null && indSet !=""){
			vt1 = tempIndSet.split(",");
			vt2 = indSet.split(",");			
			for(i = 0; i < vt2.length; i++){
				if(vt2[i] !=""){
					if(!vt1.contains(vt2[i])){
						vt1.push(vt2[i]);
					}
				}
			}
			tempIndSet = "";
			for(i = 0; i < vt1.length; i++){
				if(vt1[i] !="" && vt1[i] !=","){
					tempIndSet += vt1[i]+",";
				}
			}
		}
		vTemp = tempIndSet.split(",");
		for(i = 0; i< vTemp.length; i++){
			if(vTemp[i] !="" && vTemp[i]!=","){
				check = document.getElementById("jqg_grid_"+(parseInt(s[i])));
				tr =  "#"+(parseInt(s[i]));
				if(check !=null && check.checked != true){
					for(j = i; j < vTemp.length; j++){
						vTemp[j] = vTemp[j+1]; 
					}
					vTemp.pop();
					$(tr).attr('class','ui-widget-content jqgrow ui-row-ltr');
				}
			}
		}
		tempIndSet = "";
		for(i = 0; i < vTemp.length; i++){
			if(vTemp[i] !="" && vTemp[i]!=","){
				tempIndSet += vTemp[i]+",";
				vect = sets[parseInt(vTemp[i])].split(",");			
				tempSet += vect[1]+",";		
			}
		}
				
		$("input[name='setoresSelecionadosComponent']").attr('value',tempSet);
		$("input[name='indiceSetoresSelecionadosComponent']").attr('value',tempIndSet);
	}
});
 /* Verifica quais setores foram
  * selecionados ou deselecionados
  * e atualiza os campos que armazenam
  * esses índices através do evento
  * de clique sobre o checkbox 
  */
$(".cbox").click(function(){
	indSet = $("input[name='indiceSetoresSelecionadosComponent']").val();
	s = jQuery("#grid").jqGrid('getGridParam','selarrrow');		
	tempSet="";
	tempIndSet="";	
	if(s !=null && s !=""){
		for(i = 0; i < s.length; i++){
			tempIndSet +=s[i]+",";			
		}
		if(indSet !=null && indSet !=""){
			vt1 = tempIndSet.split(",");
			vt2 = indSet.split(",");			
			for(i = 0; i < vt2.length; i++){
				if(vt2[i] !=""){
					if(!vt1.contains(vt2[i])){
						vt1.push(vt2[i]);
					}
				}
			}
			tempIndSet = "";
			for(i = 0; i < vt1.length; i++){
				if(vt1[i] !="" && vt1[i] !=","){
					tempIndSet += vt1[i]+",";
				}
			}
		}
		vTemp = tempIndSet.split(",");
		for(i = 0; i< vTemp.length; i++){
			if(vTemp[i] !="" && vTemp[i]!=","){
				check = document.getElementById("jqg_grid_"+(parseInt(s[i])));
				tr =  "#"+(parseInt(s[i]));
				if(check !=null && check.checked != true){
					for(j = i; j < vTemp.length; j++){
						vTemp[j] = vTemp[j+1]; 
					}
					vTemp.pop();
					$(tr).attr('class','ui-widget-content jqgrow ui-row-ltr');
				}
			}
		}
		tempIndSet = "";
		for(i = 0; i < vTemp.length; i++){
			if(vTemp[i] !="" && vTemp[i]!=","){
				tempIndSet += vTemp[i]+",";
				vect = sets[parseInt(vTemp[i])].split(",");			
				tempSet += vect[1]+",";		
			}
		}
				
		$("input[name='setoresSelecionadosComponent']").attr('value',tempSet);
		$("input[name='indiceSetoresSelecionadosComponent']").attr('value',tempIndSet);
	}
});
	
	s = $("input[name='indiceSetoresSelecionadosComponent']").val().split(",");
	if(s !=null){
		for(i = 0; i < s.length; i++){
			if(s[i] !=""){
				check ="#jqg_grid_"+(parseInt(s[i]));
				tr =  "#"+(parseInt(s[i]));			 
				$(check).attr('checked', true);
				$(tr).attr('class','ui-widget-content jqgrow ui-row-ltr ui-state-highlight');
			}
		}				
	}		


$("#gerarDadosCobranca").click(function(){	 
	s = $("input[name='indiceSetoresSelecionadosComponent']").val().split(",");	
	
	validaForm();	
});

$("#limparLocalicade").click(function(){

	$.post('exibirInformarContasEmCobrancaAction.do?removeLocalidade=SIM',{}, function(value){
		limparLocalidade();
		jQuery("#gbox_grid").hide();
		var form = document.forms[0];
		form.action = 'exibirInformarContasEmCobrancaAction.do';
		form.submit();

	});
});
$("#closeGrid").hide();
$("#cb_grid").hide();
$("#pager").height(40);
$("input[name='idLocalidade']").keyup(function(){
	$.post('exibirInformarContasEmCobrancaAction.do?removeLocalidade=SIM',{}, function(value){
		limparLocalidadeTecla();
		jQuery("#gbox_grid").hide();		
	});
});
$("#pesquisaLocalidade").click(function(){
	$.post('exibirInformarContasEmCobrancaAction.do?removeLocalidade=SIM',{}, function(value){
		pesquisarLocalidade();
	});
	
});

     });
 //]]>
 </script>
<SCRIPT LANGUAGE="JavaScript"><!--

	
	function limparEmpresa() {
		var form = document.forms[0];
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";	
	}
	
	function limparImovel() {
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.inscricaoImovel.value = "";	
		
		bloqueiaDados(); 
	}
	
	function limparImovelTecla() {
		var form = document.forms[0];
		form.inscricaoImovel.value = "";	
	}
	
	function limparCliente() {
		var form = document.forms[0];
		
		form.idCliente.value = "";
		form.nomeCliente.value = "";	
		
		bloqueiaDados(); 
	}
	
	function limparClienteTecla() {
		var form = document.forms[0];
		form.nomeCliente.value = "";	
	}
	
	function limparLocalidadeOrigem() {
		var form = document.forms[0];
		form.idLocalidadeOrigem.value = "";
		form.nomeLocalidadeOrigem.value = "";
		form.idLocalidadeDestino.value = "";
		form.nomeLocalidadeDestino.value = "";
		limparSetorComercialOrigem();
		bloqueiaDados();
		
	}
	
	function limparLocalidadeOrigemTecla() {
		var form = document.forms[0];
		form.nomeLocalidadeOrigem.value = "";
		form.nomeLocalidadeDestino.value = "";
	}
	
	function limparLocalidadeDestino() {
		var form = document.forms[0];
		form.idLocalidadeDestino.value = "";
		form.nomeLocalidadeDestino.value = "";
	}
	
	function limparLocalidadeDestinoTecla() {
		var form = document.forms[0];
		form.nomeLocalidadeDestino.value = "";
	}
	
	function limparSetorComercialOrigem() {
		var form = document.forms[0];
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
		limparQuadraInicial();
	}
	
	function limparSetorComercialOrigemTecla() {
		var form = document.forms[0];
		form.descricaoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialDestino.value = "";
	}
	
	function limparSetorComercialDestino() {
		var form = document.forms[0];
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
	}
	
	function limparSetorComercialDestinoTecla() {
		var form = document.forms[0];
		form.descricaoSetorComercialDestino.value = "";
	}
	
	function limparQuadraInicial(){
		var form = document.forms[0];
		form.codigoQuadraInicial.value = "";
		form.descricaoQuadraInicial.value = "";
		form.codigoQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}
	
	function limparQuadraInicialTecla() {
		var form = document.forms[0];
		form.descricaoQuadraInicial.value = "";
	}
	
	function limparQuadraFinal(){
		var form = document.forms[0];
		form.codigoQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}
	
	function limparQuadraFinalTecla() {
		var form = document.forms[0];
		form.descricaoQuadraFinal.value = "";
	}
	
	function limparServicoTipo() {
		var form = document.forms[0];
		form.idServicoTipo.value = "";
		form.descricaoServicoTipo.value = "";
	}
	
	function pesquisarLocalidadeOrigem() {
		var form = document.forms[0];

		if (form.idLocalidadeOrigem.disabled == false)  {
			form.tipoPesquisa.value = 'origem';
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
	}
	
	function pesquisarLocalidadeDestino() {
		var form = document.forms[0];

		if (form.idLocalidadeDestino.disabled == false)  {
			form.tipoPesquisa.value = 'destino';
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
	}
	
	function pesquisarSetorComercialOrigem() {
		var form = document.forms[0];

		if (form.codigoSetorComercialOrigem.disabled == false) {
			form.tipoPesquisa.value = 'origem';
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do', form.idLocalidadeOrigem.value, 'Localidade Inicial', 275, 480);
		}
	}
	
	function pesquisarSetorComercialDestino() {
		var form = document.forms[0];
		
		if (form.codigoSetorComercialDestino.disabled == false) {
			form.tipoPesquisa.value = 'destino';
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do', form.idLocalidadeOrigem.value, 'Localidade Inicial', 275, 480);
		}
	}
	
	function pesquisarQuadraInicial() {
		var form = document.forms[0];

		if (form.codigoQuadraInicial.disabled == false) {
			form.tipoPesquisa.value = 'origem';
			abrirPopupDependencia('exibirPesquisarQuadraAction.do', form.codigoSetorComercialOrigem.value, 'Setor Comercial Inicial', 275, 480);
		}
	}
	
	function pesquisarQuadraFinal() {
		var form = document.forms[0];

		if (form.codigoQuadraFinal.disabled == false) {
			form.tipoPesquisa.value = 'destino';
			abrirPopupDependencia('exibirPesquisarQuadraAction.do', form.codigoSetorComercialOrigem.value, 'Setor Comercial Inicial', 275, 480);
		}
	}
	
	function pesquisarEmpresa() {
		var form = document.forms[0];

		if (form.idEmpresa.disabled == false)  {
			abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
		}
	}
	
	function pesquisarQuantidadeContas() {
		var form = document.forms[0];

		if(form.idLocalidade.value !="" && form.idLocalidade.disabled == false){
			if(validarEmpresa()){
				form.action = 'exibirInformarContasEmCobrancaAction.do?pesquisarQtdContas=sim';	    	
	    		botaoAvancarTelaEspera('exibirInformarContasEmCobrancaAction.do?pesquisarQtdContas=sim');
			}
		}
		else if(validarLocalidade() && validarSetorComercial() && validarEmpresa()){
		
			form.action = 'exibirInformarContasEmCobrancaAction.do?pesquisarQtdContas=sim';
	    	
	    	botaoAvancarTelaEspera('exibirInformarContasEmCobrancaAction.do?pesquisarQtdContas=sim');
	    }
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa' && form.idEmpresa.disabled == false) {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			bloqueiaDados();
    	} else if (tipoConsulta == 'imovel' && form.idImovel.disabled == false) {
    		form.idImovel.value = codigoRegistro;
			form.inscricaoImovel.value = descricaoRegistro;
			form.inscricaoImovel.style.color = "#000000";
			bloqueiaDados();
    	} else if (tipoConsulta == 'cliente' && form.idCliente.disabled == false) {
    		form.idCliente.value = codigoRegistro;
			form.nomeCliente.value = descricaoRegistro;
			form.nomeCliente.style.color = "#000000";
			bloqueiaDados();
    	} else if (tipoConsulta == 'localidade') {
    		if (form.tipoPesquisa.value == 'origem' && form.idLocalidadeOrigem.disabled == false) {
    			form.idLocalidadeOrigem.value = codigoRegistro;
				form.nomeLocalidadeOrigem.value = descricaoRegistro;
				form.nomeLocalidadeOrigem.style.color = "#000000";
				form.idLocalidadeDestino.value = codigoRegistro;
				form.nomeLocalidadeDestino.value = descricaoRegistro;
				form.nomeLocalidadeDestino.style.color = "#000000";
				bloqueiaDados();
			}
    		else if(form.tipoPesquisa.value == 'idLocalidade' && form.idLocalidade.disabled == false){
    			form.idLocalidade.value = codigoRegistro;
				form.nomeLocalidade.value = descricaoRegistro;
				form.nomeLocalidade.style.color = "#000000";
            } 
			else {
				if(form.idLocalidadeDestino.disabled == false){
					form.idLocalidadeDestino.value = codigoRegistro;
					form.nomeLocalidadeDestino.value = descricaoRegistro;
					form.nomeLocalidadeDestino.style.color = "#000000";
				}
			}
		
    	} else if (tipoConsulta == 'setorComercial') {
    		if (form.tipoPesquisa.value == 'origem' && form.codigoSetorComercialOrigem.disabled == false) {
    			form.codigoSetorComercialOrigem.value = codigoRegistro;
				form.descricaoSetorComercialOrigem.value = descricaoRegistro;
				form.descricaoSetorComercialOrigem.style.color = "#000000";
				form.codigoSetorComercialDestino.value = codigoRegistro;
				form.descricaoSetorComercialDestino.value = descricaoRegistro;
				form.descricaoSetorComercialDestino.style.color = "#000000";
				bloqueiaDados();
			} else {
				if(form.codigoSetorComercialDestino.disabled == false){
					form.codigoSetorComercialDestino.value = codigoRegistro;
					form.descricaoSetorComercialDestino.value = descricaoRegistro;
					form.descricaoSetorComercialDestino.style.color = "#000000";
				}
			}
    	} else if (tipoConsulta == 'quadra') {
    		if (form.tipoPesquisa.value == 'origem' && form.codigoQuadraInicial.disabled == false) {
    			form.codigoQuadraInicial.value = codigoRegistro;
				form.descricaoQuadraInicial.value = descricaoRegistro;
				form.descricaoQuadraInicial.style.color = "#000000";
				form.codigoQuadraFinal.value = codigoRegistro;
				form.descricaoQuadraFinal.value = descricaoRegistro;
				form.descricaoQuadraFinal.style.color = "#000000";
				bloqueiaDados();
			} else {
				if(form.codigoQuadraFinal.disabled == false){
					form.codigoQuadraFinal.value = codigoRegistro;
					form.descricaoQuadraFinal.value = descricaoRegistro;
					form.descricaoQuadraFinal.style.color = "#000000";
				}
			}
    	} else if (tipoConsulta == 'tipoServico' && form.idServicoTipo.disabled == false) {
			form.idServicoTipo.value = codigoRegistro;
			form.descricaoServicoTipo.value = descricaoRegistro;
			form.descricaoServicoTipo.style.color = "#000000";
    	}
    
    }
    
	function gerarDadosCobranca(){
		var form = document.forms[0];
		form.action = 'informarContasEmCobrancaAction.do';
	    submeterFormPadrao(form);
	}
	var comboInformadaCat = false;
	var comboInformadaSit = false;	
	var comboInformadaGer = false;
	var comboInformadaUni = false;
	function bloqueiaDados(){
	
		var form = document.forms[0];
		
		if (form.idImovel.value != null && form.idImovel.value != ''){
		
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.idsCategoria.value = "-1";
			form.idsCategoria.selectedIndex = 0;
			form.idsCategoria.disabled = true;
			form.idsImovelPerfil.value = "-1";
			form.idsImovelPerfil.selectedIndex = 0;
			form.idsImovelPerfil.disabled = true;
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.selectedIndex = 0;
			form.idsUnidadeNegocio.disabled = true;
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.selectedIndex = 0;
			form.idsGerenciaRegional.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			form.idsLigacaoAguaSituacao.value = "-1";
			form.idsLigacaoAguaSituacao.selectedIndex = 0;
			form.idsLigacaoAguaSituacao.disabled = true;
			form.idLocalidade.value = '';
			form.idLocalidade.disabled = true;
			
		}

		else if(form.idCliente.value != null && form.idCliente.value != ''){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.idsCategoria.value = "-1";
			form.idsCategoria.selectedIndex = 0;
			form.idsCategoria.disabled = true;
			form.idsImovelPerfil.value = "-1";
			form.idsImovelPerfil.selectedIndex = 0;
			form.idsImovelPerfil.disabled = true;
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.selectedIndex = 0;
			form.idsUnidadeNegocio.disabled = true;
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.selectedIndex = 0;
			form.idsGerenciaRegional.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			form.idsLigacaoAguaSituacao.value = "-1";
			form.idsLigacaoAguaSituacao.selectedIndex = 0;
			form.idsLigacaoAguaSituacao.disabled = true;
			form.idLocalidade.value = '';
			form.idLocalidade.disabled = true;
			
		}  else if(form.idsUnidadeNegocio.value != null && form.idsUnidadeNegocio.value != ''  && form.idsUnidadeNegocio.value != '-1' 
			&& comboInformadaUni == false){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			form.idLocalidade.value = '';
			form.idLocalidade.disabled = true;
			comboInformadaUni = true;
			
		} else if(form.idsGerenciaRegional.value != null && form.idsGerenciaRegional.value != '' && form.idsGerenciaRegional.value != '-1' 
			&& comboInformadaGer == false){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			form.idLocalidade.value = '';
			form.idLocalidade.disabled = true;
			comboInformadaGer = true;
			
		} else if(form.idLocalidadeOrigem.value != null && form.idLocalidadeOrigem.value != ''){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.disabled = true;
			form.idsUnidadeNegocio.selectedIndex = 0;
			//form.idsCategoria.value = "-1";
			//form.idsCategoria.selectedIndex = 0;
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.disabled = true;
			form.idsGerenciaRegional.selectedIndex = 0;
			form.idLocalidade.value = '';
			form.idLocalidade.disabled = true;
			//form.idsCategoria.disabled = true;
			
		}
		else if(form.idLocalidade.value != null && form.idLocalidade.value != ''){

			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.disabled = true;
			form.idsUnidadeNegocio.selectedIndex = 0;
			//form.idsCategoria.value = "-1";
			//form.idsCategoria.selectedIndex = 0;
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.disabled = true;
			form.idsGerenciaRegional.selectedIndex = 0;
		}
		else if (form.idsCategoria.value != null && form.idsCategoria.value != '' && form.idsCategoria.value != '-1' 
			&& comboInformadaCat == false) {			

			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			comboInformadaCat = true;
			
			
		}
		
		 else if(form.idsLigacaoAguaSituacao.value != null 
			&& form.idsLigacaoAguaSituacao.value != ''  
			&& form.idsLigacaoAguaSituacao.value != '-1' 
			&& comboInformadaSit == false){
			
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			comboInformadaSit = true;
			
			
		}  else {

			form.idsCategoria.disabled = false;
			form.idsUnidadeNegocio.disabled = false;
			form.idsGerenciaRegional.disabled = false;
			form.idImovel.disabled = false;				
			form.idCliente.disabled = false;
			form.idLocalidadeOrigem.disabled = false;
			form.idLocalidadeDestino.disabled = false;
			form.codigoSetorComercialOrigem.disabled = false;
			form.codigoSetorComercialDestino.disabled = false;
			form.codigoQuadraInicial.disabled = false;
			form.codigoQuadraFinal.disabled = false;
			form.idsImovelPerfil.disabled = false;
			form.idsLigacaoAguaSituacao.disabled = false;
			form.idLocalidade.disabled = false;
		
			if(comboInformadaCat == true){
				if(form.idsCategoria.value != null && form.idsCategoria.value != '' && form.idsCategoria.value != '-1'){
					form.idImovel.value = "";
					form.idImovel.disabled = true;
					form.idCliente.value = "";
					form.idCliente.disabled = true;
				}
				else{				
					form.idImovel.disabled = false;				
					form.idCliente.disabled = false;
					comboInformadaCat = false;
				}
			}
			if(comboInformadaSit == true){
				if(form.idsLigacaoAguaSituacao.value != null 
						&& form.idsLigacaoAguaSituacao.value != ''  
						&& form.idsLigacaoAguaSituacao.value != '-1'){
					form.idImovel.value = "";
					form.idImovel.disabled = true;
					form.idCliente.value = "";
					form.idCliente.disabled = true;
				}
				else{				
					form.idImovel.disabled = false;				
					form.idCliente.disabled = false;
					comboInformadaSit = false;
				}
			}
			if(comboInformadaGer == true){
				if(form.idsGerenciaRegional.value != null && form.idsGerenciaRegional.value != '' && form.idsGerenciaRegional.value != '-1'){
					
					form.idImovel.disabled = true;				
					form.idLocalidadeOrigem.disabled = true;				
					form.idLocalidadeDestino.disabled = true;				
					form.idCliente.disabled = true;				
					form.codigoSetorComercialOrigem.disabled = true;				
					form.codigoSetorComercialDestino.disabled = true;				
					form.codigoQuadraInicial.disabled = true;				
					form.codigoQuadraFinal.disabled = true;				
					form.idLocalidade.disabled = true;				
				}
				else{
					form.idImovel.disabled = false;				
					form.idLocalidadeOrigem.disabled = false;				
					form.idLocalidadeDestino.disabled = false;				
					form.idCliente.disabled = false;				
					form.codigoSetorComercialOrigem.disabled				
					form.codigoSetorComercialDestino.disabled = false;				
					form.codigoQuadraInicial.disabled = false;				
					form.codigoQuadraFinal.disabled = false;				
					form.idLocalidade.disabled = false;
					comboInformadaGer = false;
				}
			}
			if(comboInformadaUni == true){
				if(form.idsUnidadeNegocio.value != null && form.idsUnidadeNegocio.value != '' && form.idsUnidadeNegocio.value != '-1'){
					form.idImovel.disabled = true;					
					form.idLocalidadeOrigem.disabled = true;					
					form.idLocalidadeDestino.disabled = true;					
					form.idCliente.disabled = true;					
					form.codigoSetorComercialOrigem.disabled = true;					
					form.codigoSetorComercialDestino.disabled = true;					
					form.codigoQuadraInicial.disabled = true;					
					form.codigoQuadraFinal.disabled = true;					
					form.idLocalidade.disabled = true;					
				}
				else{
					form.idImovel.disabled = false;					
					form.idLocalidadeOrigem.disabled = false;					
					form.idLocalidadeDestino.disabled = false;					
					form.idCliente.disabled = false;					
					form.codigoSetorComercialOrigem.disabled = false;					
					form.codigoSetorComercialDestino.disabled = false;					
					form.codigoQuadraInicial.disabled = false;					
					form.codigoQuadraFinal.disabled = false;					
					form.idLocalidade.disabled = false;
					comboInformadaUni = false;
				}
			}			
		}
	
	
	}
	
	function validarLocalidade(){
		var form = document.forms[0];
		if(form.idLocalidadeOrigem.readOnly == false ){
			if((form.idsUnidadeNegocio.value == null || form.idsUnidadeNegocio.value == '' || form.idsUnidadeNegocio.value == '-1') 
				&& (form.idsGerenciaRegional.value == null || form.idsGerenciaRegional.value == '' || form.idsGerenciaRegional.value == '-1') 
				&& (form.idImovel.value == null || form.idImovel.value == '')
				&& (form.idCliente.value == null || form.idCliente.value == '')){
				
				if(form.idLocalidadeOrigem.value != null && form.idLocalidadeOrigem.value != '' && form.idLocalidadeOrigem.disabled == false 
					&& (form.idLocalidadeDestino.value == null || form.idLocalidadeDestino.value == '')){
						
					alert('Informe Localidade Final.');	
					return false;	
						
				}else if ((form.idLocalidadeDestino.value != null && form.idLocalidadeDestino.value != '' && 
							form.idLocalidadeDestino.disabled == false) && (form.idLocalidadeOrigem.value == null || 
									form.idLocalidadeOrigem.value == '')){		
					
					alert('Informe Localidade Inicial.');	
					return false;
					
				}else{
					return true;
				}
				
			}else{
					return true;
				}
		}
		else 
			return true;
	}
	
	function validarSetorComercial(){
		var form = document.forms[0];

		if(form.codigoSetorComercialOrigem.readOnly == false){
			if((form.idsUnidadeNegocio.value == null || form.idsUnidadeNegocio.value == '' || form.idsUnidadeNegocio.value == '-1') 
				&& (form.idImovel.value == null || form.idImovel.value == '')
				&& (form.idCliente.value == null || form.idCliente.value == '')){
			
				if(form.codigoSetorComercialOrigem.value != null && form.codigoSetorComercialOrigem.value != '' 
						&& (form.codigoSetorComercialDestino.value == null || form.codigoSetorComercialDestino.value == '')){
						
					alert('Informe Setor Comercial Final.');
					return false;		
						
				}else if (form.codigoSetorComercialOrigem.value != null && form.codigoSetorComercialOrigem.value != '' 
						&& (form.codigoSetorComercialDestino.value == null || form.codigoSetorComercialDestino.value == '')){		
					
					alert('Informe Setor Comercial Inicial.');	
					return false;
					
				}else{
					return true;
				}
			}else{
					return true;
				}
		}
		else
			return true;
	}
	
	function desabilitaEmpresa(){
		var form = document.forms[0];
		
		form.qtdContas.value = '';
		form.qtdClientes.value = '';
		form.valorTotalDivida.value = '';
		form.idEmpresa.value = '';
		form.idEmpresa.disabled = true;
	}
	
	function liberaEmpresa(){
		var form = document.forms[0];
		
		if(form.liberaEmpresa.value == 'sim'){
			
			form.idEmpresa.disabled = false;
		
		}else{
			form.idEmpresa.disabled = true;
		
		}
	
	}
	
	function verificarBloqueioEmpresa() {
	
		var form = document.forms[0];
	
		if (form.qtdContas.value != null && form.qtdContas.value != '' && form.qtdContas.value != '0') {
			form.idEmpresa.disabled = false;
		} else {
			form.idEmpresa.value = '';
			form.idEmpresa.disabled = true;
		}
	}
	
	function validarInformacaoSetorComercial(){
		var form =  document.forms[0];
		
		if(form.codigoSetorComercialOrigem.value !=null && form.codigoSetorComercialOrigem.value != ''
				&& form.idLocalidadeOrigem.value != form.idLocalidadeDestino.value){
			alert('A Localidade Final não pode ser diferente da Localidade Inicial');
			return false;
		
		}else{
		
		return true;
		}
	
	}
	
	
	function validaForm() {
		var form =  document.forms[0];
		var submeterForm = true;
		
		if(form.idEmpresa.value != null && form.idEmpresa.value != '') {
			if (form.colecaoInformada.value != null && form.colecaoInformada.value != '') {
				if (form.dataInicioCiclo.value == null || form.dataInicioCiclo.value == ''){
						submeterForm = false;
						alert('Informe a Data Início do Ciclo');
				} else if (form.quantidadeDiasCiclo.value == null || form.quantidadeDiasCiclo.value == '') {
						submeterForm = false;
						alert('Informe a Quantidade de Dias do Ciclo');
				} else if (form.idServicoTipo.value == null || form.idServicoTipo.value == '') {
						submeterForm = false;
						alert('Informe o Tipo de Serviço');
				} else if (!verificaData(form.dataInicioCiclo)){
					submeterForm = false;
				} else if (comparaDataComDataAtual(form.dataInicioCiclo.value, "<")) {
					submeterForm = false;
					alert('Data Início do Ciclo deve ser maior ou igual a data corrente.');
				} else if (!validarCampoNumericoComMensagem(form.quantidadeDiasCiclo, "Quantidade de Dias do Ciclo")) {
					submeterForm = false;
				}
			} else if (form.dataInicioCiclo.value != null && form.dataInicioCiclo.value != '') {
				if (!verificaData(form.dataInicioCiclo)){
					submeterForm = false;
				} else if (comparaDataComDataAtual(form.dataInicioCiclo.value, "<")) {
					submeterForm = false;
					alert('Data Início do Ciclo deve ser maior ou igual a data corrente.');
				} else if (form.quantidadeDiasCiclo.value != null && form.quantidadeDiasCiclo.value != ''
						&& validarCampoNumericoComMensagem(form.quantidadeDiasCiclo, "Quantidade de Dias do Ciclo")) {
					submeterForm = true;
				}
			}
			
		}else{
			submeterForm = false;
			alert('Informe Empresa');
		}	
		
		if (submeterForm) {
			if (validateInformarContasEmCobrancaActionForm(form) && validarInformacaoSetorComercial()){
					form.action = 'informarContasEmCobrancaAction.do';
					submeterFormPadrao(form);
			}
		}
	}
	
	function validarEmpresa(){
		var form = document.forms[0];
		
		if(form.idEmpresa.value == null || form.idEmpresa.value == ''){
				
			alert('Informe a Empresa.');
			return false;		
				
		}
		
		return true;
		
	}
	
	function limparTotalizacao(){
		var form = document.forms[0];
		
		if (form.totalSelecionado.value != null && form.totalSelecionado.value != "") {
			form.action = 'exibirInformarContasEmCobrancaAction.do?limparTotalizacao=SIM';
			submeterFormPadrao(form);
		}
	}

	function limparLocalidade() {
		var form = document.forms[0];
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";	
		form.indiceSetoresSelecionadosComponent.value="";
		form.setoresSelecionadosComponent.value="";			
		bloqueiaDados();
	}	
	function limparLocalidadeTecla() {
		var form = document.forms[0];
		form.nomeLocalidade.value = "";
		form.indiceSetoresSelecionadosComponent.value="";
		form.setoresSelecionadosComponent.value="";		
		bloqueiaDados();	
	}
	function pesquisarLocalidade() {
		var form = document.forms[0];

		if (form.idLocalidade.disabled == false)  {
			form.tipoPesquisa.value = 'idLocalidade';
			form.indiceSetoresSelecionadosComponent.value="";
			form.setoresSelecionadosComponent.value="";	
			if(document.getElementById("gbox_grid") !=null){	
				document.getElementById("gbox_grid").style.display='none';
			}
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
		else{
			alert('pesquisa bloqueada');
		}
	}
	function adicionarLocalidade(){
		var form = document.forms[0];
		if(form.idLocalidade.value !=null && form.idLocalidade.value !=''){
			form.indiceSetoresSelecionadosComponent.value="";
			form.setoresSelecionadosComponent.value="";		
			form.idLocalidadeOrigem.value = "";			
			form.idLocalidadeDestino.value = "";			
			form.codigoSetorComercialOrigem.value = "";			
			form.codigoSetorComercialDestino.value = "";
			form.codigoQuadraInicial.value = "";			
			form.codigoQuadraFinal.value = "";
			form.action = 'exibirInformarContasEmCobrancaAction.do?pesquisaLocalidade=SIM';
			form.submit();			
		}
		else{
			alert('Informe a Localidade');
		}
	}
	
	
--></script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:bloqueiaDados();">

<div id="formDiv">
<html:form action="/exibirInformarContasEmCobrancaAction"
	name="InformarContasEmCobrancaActionForm"
	type="gcom.gui.cobranca.InformarContasEmCobrancaActionForm"
	method="post">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Informar Contas em Cobrança por Empresa</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<html:hidden property="colecaoInformada"/>
			<html:hidden property="totalSelecionado"/>
			<%--Armazena, respectivamente, o codigo dos setores e os indices dos mesmos na tabela --%>
			<html:hidden property="setoresSelecionadosComponent"/>
			<html:hidden property="indiceSetoresSelecionadosComponent"/>
			
			<input type="hidden" name="tipoPesquisa" />
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o comando das contas em cobrança por empresa,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%"><strong>Imóvel:</strong></td>
					<td><html:text maxlength="9" property="idImovel" size="9"
						tabindex="1" onchange="javascript:limparTotalizacao();"
						onkeyup="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do', 'idImovel', 'Imóvel'); limparImovelTecla();bloqueiaDados();"
						onkeypress="return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do?limpaForm=S', 495, 300);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0" title="Pesquisar Imóvel" /></a> <logic:present
						name="imovelInexistente" scope="request">
						<html:text property="inscricaoImovel" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="imovelInexistente"
						scope="request">
						<html:text property="inscricaoImovel" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();limparTotalizacao();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Imóvel"> </a>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Cliente:</strong></td>
					<td><html:text maxlength="9" property="idCliente" size="9"
						tabindex="2" onchange="javascript:limparTotalizacao();"
						onkeyup="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do', 'idCliente', 'Cliente'); limparClienteTecla();bloqueiaDados();"
						onkeypress="return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limpaForm=S', 495, 300);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0" title="Pesquisar Cliente" /></a> <logic:present
						name="clienteInexistente" scope="request">
						<html:text property="nomeCliente" size="40" maxlength="40"
							readonly="true"
							style="border:0; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="clienteInexistente"
						scope="request">
						<html:text property="nomeCliente" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparCliente();limparTotalizacao();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Cliente" > </a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Categoria:</strong></td>
					<td><html:select property="idsCategoria" tabindex="3" onchange="javascript:bloqueiaDados();limparTotalizacao();" multiple="mutiple" size="4">
						<logic:notEmpty name="colecaoCategoria">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoCategoria"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Perfil do Imóvel:</strong></td>
					<td><html:select property="idsImovelPerfil" tabindex="3" multiple="mutiple" size="4" onchange="javascript:limparTotalizacao();">
						<logic:notEmpty name="colecaoImovelPerfil">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoImovelPerfil"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Gerência Regional:</strong></td>
					<td><html:select property="idsGerenciaRegional" tabindex="3" multiple="mutiple" size="4" onchange="javascript:bloqueiaDados();limparTotalizacao();">
						<logic:notEmpty name="colecaoGerenciaRegional">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Unidade Negócio:</strong></td>
					<td><html:select property="idsUnidadeNegocio" tabindex="3" multiple="mutiple" size="4" onchange="javascript:bloqueiaDados();limparTotalizacao();">
						<logic:notEmpty name="colecaoUnidadeNegocio">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoUnidadeNegocio"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Situação da Ligação de Água:</strong></td>
					<td><html:select property="idsLigacaoAguaSituacao" 
						tabindex="3" multiple="mutiple" size="4" 
						onchange="javascript:bloqueiaDados();limparTotalizacao();">
						<logic:notEmpty name="colecaoLigacaoAguaSituacao">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoLigacaoAguaSituacao"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>				
					<tr>
						<td><strong>Localidade Inicial:</strong></td>
						<td><html:text maxlength="3" property="idLocalidadeOrigem" size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=localidadeOrigem', 'idLocalidadeOrigem' ,'Localidade Inicial'); return isCampoNumerico(event);"
							tabindex="4" onkeyup="javascript:replicarCampo(form.idLocalidadeDestino, form.idLocalidadeOrigem);limparLocalidadeOrigemTecla();bloqueiaDados();" 
							onchange="javascript:limparTotalizacao();" />
						<a href="javascript:pesquisarLocalidadeOrigem();"> <img width="23"
							height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a> <logic:present
							name="localidadeOrigemInexistente" scope="request">
							<html:text property="nomeLocalidadeOrigem" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> <logic:notPresent
							name="localidadeOrigemInexistente" scope="request">
							<html:text property="nomeLocalidadeOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a href="javascript:limparLocalidadeOrigem();limparTotalizacao();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Localidade" /></a></td>	
					</tr> 
					<tr>
						<td><strong>Setor Comercial Inicial:</strong></td>
						<td><html:text maxlength="3" property="codigoSetorComercialOrigem" size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=setorComercialOrigem', document.forms[0].codigoSetorComercialOrigem, document.forms[0].idLocalidadeOrigem.value, 'Localidade Inicial', 'Setor Comercial Inicial');return isCampoNumerico(event);"
							tabindex="5" onkeyup="javascript:replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);limparSetorComercialOrigemTecla();"
							onchange="javascript:limparTotalizacao();" />
						<a href="javascript:pesquisarSetorComercialOrigem();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Setor Comercial" /></a> <logic:present
							name="setorComercialOrigemInexistente" scope="request">
							<html:text property="descricaoSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> <logic:notPresent
							name="setorComercialOrigemInexistente" scope="request">
							<html:text property="descricaoSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a
							href="javascript:limparSetorComercialOrigem();limparTotalizacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Setor Comercial" /></a></td>
					</tr>
					
					<tr>
						<td><strong>Quadra Inicial:</strong></td>
						<td><html:text maxlength="4" property="codigoQuadraInicial" size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=quadraInicial', document.forms[0].codigoQuadraInicial, document.forms[0].codigoSetorComercialOrigem.value, 'Setor Comercial Inicial','Quadra Inicial');return isCampoNumerico(event);"
							tabindex="8"
							onkeyup="javascript:replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial);limparQuadraInicialTecla();" 
							onchange="javascript:limparTotalizacao();" />
							<a href="javascript:pesquisarQuadraInicial();"> <img
								width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Quadra" /></a>
							<logic:present name="quadraInicialInexistente" scope="request">
							<html:text property="descricaoQuadraInicial" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
							</logic:present>
							<logic:notPresent name="quadraInicialInexistente" scope="request">
								<html:text property="descricaoQuadraInicial" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent> <a
								href="javascript:limparQuadraInicial();limparTotalizacao();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Quadra Inicial" /></a>
						</td>
					</tr>
					
					<tr>
						<td><strong>Localidade Final:</strong></td>
						<td><html:text maxlength="3" property="idLocalidadeDestino"
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=localidadeDestino', 'idLocalidadeDestino' ,'Localidade Final');bloqueiaDados();return isCampoNumerico(event);"
							tabindex="6" onkeyup="limparLocalidadeDestinoTecla();" 
							onchange="javascript:limparTotalizacao();" /> <a
							href="javascript:pesquisarLocalidadeDestino();"> <img width="23"
							height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a> <logic:present
							name="localidadeDestinoInexistente" scope="request">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> <logic:notPresent
							name="localidadeDestinoInexistente" scope="request">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a href="javascript:limparLocalidadeDestino();limparTotalizacao();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Localidade" /></a></td>
					</tr>
					<tr>
						<td><strong>Setor Comercial Final:</strong></td>
						<td><html:text maxlength="3" property="codigoSetorComercialDestino"
							size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=setorComercialDestino', document.forms[0].codigoSetorComercialDestino, document.forms[0].idLocalidadeDestino.value, 'Localidade Final', 'Setor Comercial Final');return isCampoNumerico(event);"
							onchange="javascript:limparTotalizacao();" 
							tabindex="7" onkeyup="limparSetorComercialDestinoTecla();" /> <a
							href="javascript:pesquisarSetorComercialDestino();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Setor Comercial" /></a> <logic:present
							name="setorComercialDestinoInexistente" scope="request">
							<html:text property="descricaoSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> <logic:notPresent
							name="setorComercialDestinoInexistente" scope="request">
							<html:text property="descricaoSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a
							href="javascript:limparSetorComercialDestino();limparTotalizacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Setor Comercial" /></a></td>
					</tr>
					
					<tr>
						<td><strong>Quadra Final:</strong></td>
						<td><html:text maxlength="4" property="codigoQuadraFinal" size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=quadraFinal', document.forms[0].codigoQuadraFinal, document.forms[0].codigoSetorComercialDestino.value, 'Setor Comercial Final','Quadra Final');return isCampoNumerico(event);"
							tabindex="8" onkeyup="javascript:limparQuadraFinalTecla();" 
							onchange="javascript:limparTotalizacao();" />
							<a href="javascript:pesquisarQuadraFinal();"> <img
								width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Quadra" /></a>
							<logic:present name="quadraFinalInexistente" scope="request">
							<html:text property="descricaoQuadraFinal" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
							</logic:present>
							<logic:notPresent name="quadraFinalInexistente" scope="request">
								<html:text property="descricaoQuadraFinal" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent> <a
								href="javascript:limparQuadraFinal();limparTotalizacao();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Quadra Final" /></a>
						</td>
					</tr>				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>				
					<%--
						onkeyup="javascript:replicarCampo(form.idLocalidadeDestino, form.idLocalidadeOrigem);limparLocalidadeOrigemTecla();bloqueiaDados();"
					 	onchange="javascript:limparTotalizacao();"
					 	onkeyup="javascript:limparLocalidadeTecla();"
					 --%>
					<td><strong>Localidade :</strong></td>
					<td><html:text maxlength="3" property="idLocalidade" size="3"
						onkeypress="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=idLocalidade', 'idLocalidade' ,'Localidade');
						return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idLocalidade, 'Localidade');"
						tabindex="4"
						  
						 />
					<a style="cursor: pointer;" id="pesquisaLocalidade"> <img width="23"
						height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="localidadeInexistente" scope="request">
						<html:text property="nomeLocalidade" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeInexistente" scope="request">
						<html:text property="nomeLocalidade" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a style="cursor: pointer;" id="limparLocalicade">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Localidade" /></a>&nbsp;
						<input type="button" 
       						name="ButtonReset" 
       						class="bottonRightCol"
							value="Adicionar"
							onClick="adicionarLocalidade();">
					</td>
				</tr>				
				<tr>
					<td>&nbsp;</td>
					<td>
					<logic:present name="exibeTabelaLocalidade" scope="session">						
						<table id="grid"></table>
						<div id="pager"></div>						
					 </logic:present>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idEmpresa" size="10"
						tabindex="14"
						onkeyup="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do', 'idEmpresa', 'Empresa'); limparEmpresaTecla();" 
						onchange="javascript:limparTotalizacao();"
						onkeypress="return isCampoNumerico(event);" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0" title="Pesquisar Empresa" /></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();limparTotalizacao();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Empresa" /> </a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Período Refer. das Contas:</strong></td>
					<td><strong> <html:text maxlength="7" property="referenciaInicial"
						size="7" tabindex="8"
						onkeyup="validaAnoMesNumerico(document.forms[0].referenciaInicial);mascaraAnoMes(this, event); replicarCampo(document.forms[0].referenciaFinal, document.forms[0].referenciaInicial);"
						onchange="javascript:limparTotalizacao();" />
					<strong> a</strong> <html:text maxlength="7"
						property="referenciaFinal" size="7" tabindex="9"
						onkeyup="validaAnoMesNumerico(document.forms[0].referenciaFinal);mascaraAnoMes(this, event);"
						onchange="javascript:limparTotalizacao();" /> </strong> (mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>Período de Vencimento das Contas:</strong></td>
					<td><strong> <html:text maxlength="10"
						property="dataVencimentoInicial" size="10" tabindex="10"
						onkeyup="validaDataNumerica(document.forms[0].dataVencimentoInicial);mascaraData(this, event);  replicarCampo(document.forms[0].dataVencimentoFinal, document.forms[0].dataVencimentoInicial);"
						onchange="javascript:limparTotalizacao();" />
					<a
						href="javascript:abrirCalendarioReplicando('InformarContasEmCobrancaActionForm', 'dataVencimentoInicial', 'dataVencimentoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10" property="dataVencimentoFinal"
						tabindex="11" size="10" 
						onkeyup="validaDataNumerica(document.forms[0].dataVencimentoFinal);mascaraData(this, event);" 
						onchange="javascript:limparTotalizacao();" /> <a
						href="javascript:abrirCalendario('InformarContasEmCobrancaActionForm', 'dataVencimentoFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<logic:present name="IcDebito" scope="session">
						<td><strong>Valor do Débito:</strong></td>
						<td><strong><html:text property="valorInicialDebito" size="14"
							maxlength="14" tabindex="12"
							onkeyup="formataValorMonetario(this, 14); replicarCampo(document.forms[0].valorFinalDebito, document.forms[0].valorInicialDebito);"
							onchange="javascript:limparTotalizacao();" 
							style="text-align:right;" /> a <html:text property="valorFinalDebito"
							size="14" maxlength="14" tabindex="13"
							onkeyup="formataValorMonetario(this, 14);"
							onchange="javascript:limparTotalizacao();" 
							style="text-align:right;" /> </strong></td>
							<html:hidden property="valorMinimo"/>
							<html:hidden property="valorMaximo"/>
					</logic:present>
					<logic:notPresent name="IcDebito" scope="session">
							<td><strong>Valor da Conta:</strong></td>
							<td><strong> <html:text property="valorMinimo" size="14"
							maxlength="14" tabindex="12"
							onkeyup="formataValorMonetario(this, 14); replicarCampo(document.forms[0].valorMaximo, document.forms[0].valorMinimo);"
							onchange="javascript:limparTotalizacao();" 
							style="text-align:right;" /> a <html:text property="valorMaximo"
							size="14" maxlength="14" tabindex="13"
							onkeyup="formataValorMonetario(this, 14);"
							onchange="javascript:limparTotalizacao();" 
							style="text-align:right;" /> </strong></td>
							<html:hidden property="valorInicialDebito"/>
							<html:hidden property="valorFinalDebito"/>
					</logic:notPresent>
				</tr>
				<tr>
					<td><strong>Quantidade de Contas:</strong></td>
					<td><strong> <html:text property="quantidadeContasInicial" size="14"
						maxlength="9" tabindex="12"
						onkeyup="somente_numero(this);replicarCampo(document.forms[0].quantidadeContasFinal, document.forms[0].quantidadeContasInicial);"
						onchange="javascript:limparTotalizacao();" 
						style="text-align:right;" /> a <html:text property="quantidadeContasFinal"
						size="14" maxlength="9" tabindex="13"
						onkeyup="somente_numero(this);"
						onchange="javascript:limparTotalizacao();" 
						style="text-align:right;" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Quantidade de Dias de Vencimento:</strong></td>
					<td><strong> <html:text property="quantidadeDiasVencimento" size="3"
						maxlength="3" tabindex="12"
						onkeyup="somente_numero(this);"
						onchange="javascript:limparTotalizacao();" 
						style="text-align:right;" />
				</tr>
				<tr>
				<!--  onClick="javascript:pesquisarQuantidadeContas();"-->
					<td colspan="2" align="right"><input type="button"
						name="selecionar" id="selecionar" class="bottonRightCol" value="Selecionar"
						 /></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
			</table>
				<logic:present name="colecaoQuantidadeContas" scope="session">
			<table width="100%" >
					<tr>
						<td bgcolor="#90c7fc" bordercolor="#90c7fc"></td>
						<td bgcolor="#90c7fc" bordercolor="#90c7fc" align="center" colspan="<bean:write name='tamanho' scope='session'/>"><strong>Quantidade de Faturas em Aberto:</strong></td>
						
					</tr>
					
					<tr>
						<td align="center" bgcolor="#99CCFF"><strong></strong></td>
						<logic:iterate name="colecaoFaixa" id="faixa"
							type="String">
								
								<td align="center"  bgcolor="#99CCFF">
									<strong><bean:write name="faixa" /></strong>
								</td>
								
						</logic:iterate>
					</tr>
					
					<tr>
						<td  align="center" bgcolor="#99CCFF"><strong>Quantidade de Contas:</strong></td>
						<logic:iterate name="colecaoQtdeContas" id="quantidadeContas"
							type="Integer">
							
								<td align="center" bgcolor="#FFFFFF">
									<strong><bean:write name="quantidadeContas" /></strong>
								</td>
							
						</logic:iterate>
					</tr>
					
					<tr>
						<td  align="center" bgcolor="#99CCFF"><strong>Quantidade de Clientes:</strong></td>
						<logic:iterate name="colecaoQtdeClientes" id="quantidadeClientes"
							type="Integer">
							
								<td align="center"  bgcolor="#cbe5fe">
									<strong><bean:write name="quantidadeClientes" /></strong>
								</td>
							
						</logic:iterate>
					</tr>
					
					<tr>
						<td  align="center" bgcolor="#99CCFF"><strong>Valor Total da Dívida:</strong></td>
						<logic:iterate name="colecaoValorTotalDivida" id="valorTotalDivida"
							type="BigDecimal">
							
								<td align="center"  bgcolor="#FFFFFF">
									<strong><bean:write name="valorTotalDivida" formatKey="money.format"/></strong>
								</td>
							
						</logic:iterate>
					</tr>

				</table>
				</logic:present>
				<table width="100%" border="0">
				
				<logic:notPresent name="colecaoQuantidadeContas" scope="session">
					<tr>
						<td><strong>Quantidade de Contas:</strong></td>
						<td><html:text property="qtdContas" size="10" maxlength="10"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
					<tr>
						<td><strong>Quantidade de Clientes:</strong></td>
						<td><html:text property="qtdClientes" size="10" maxlength="10"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
					<tr>
						<td><strong>Valor Total da Dívida:</strong></td>
						<td><html:text property="valorTotalDivida" size="15" maxlength="15"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
				</logic:notPresent>
				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Data Início do Ciclo:</strong></td>
					<td><strong> <html:text maxlength="10" property="dataInicioCiclo"
						size="10" tabindex="8" onkeyup="validaDataNumerica(document.forms[0].dataInicioCiclo);mascaraData(this, event);" />
					</strong> <a
						href="javascript:abrirCalendario('InformarContasEmCobrancaActionForm', 'dataInicioCiclo');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> (dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>Quantidade de Dias do Ciclo:</strong></td>
					<td><html:text property="quantidadeDiasCiclo" size="10" maxlength="10"
						onkeypress="return isCampoNumerico(event);"/></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr> 
		            <td>
		                <strong>Tipo de Servi&ccedil;o:<font color="#FF0000"></font></strong>
       				</td>
       				<td colspan="3">
       					<strong>

         					<html:text property="idServicoTipo" size="7" maxlength="4" 
									onkeyup="somente_numero(this);validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=servicoTipo', 'idServicoTipo' ,'Tipo de Serviço');" />

                      		<a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do', 300, 620);">

							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a> 

							<logic:present name="idServicoTipoEncontrada" scope="request">
								
								<html:text property="descricaoServicoTipo" 
									size="42"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 
					
							<logic:notPresent name="idServicoTipoEncontrada" scope="request">
								
								<html:text property="descricaoServicoTipo" 
									size="42"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
									
							</logic:notPresent>


               				<a href="javascript:limparServicoTipo();">
               					<img src="imagens/limparcampo.gif" 
               						 width="23" 
               						 height="21"
               						 border="0"
        			 				 title="Apagar"></a>
		                        					
		                 </strong>
		            </td>
		        </tr>
				<tr>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>
				<tr>
				<!-- onClick="javascript:validaForm();" -->
					<td colspan="2" align="right"><input type="button"
						name="gerarDadosCobranca" class="bottonRightCol"
						value="Gerar Dados Cobrança"
						id="gerarDadosCobranca"
						 /></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>
</html:html>
