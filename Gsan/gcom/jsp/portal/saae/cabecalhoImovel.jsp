<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

		<script type="text/javascript">
			$(document).ready(function(){
				var matricula = $('#matricula').html();
				var length = matricula.length;
				$('#matricula').html(matricula.substr(0, length - 1) + '.' + matricula.substr(length - 1, 1)); 
			});
		</script>

		<div id="barra-servicos">
			<h2>Serviços</h2>
		    <h3>Bem-vindo(a) ${ExibirServicosPortalSaaeActionForm.nomeUsuario}</h3>
		    <h4><label style="font-size: 13px;">Matrícula: </label><span id="matricula">${ExibirServicosPortalSaaeActionForm.matricula}</span></h4>
		    <a href="exibirServicosPortalSaaeAction.do?menu=sim" title="Sair"><img src="/gsan/imagens/portal/saae/general/btn-sair.png" alt="Sair" /></a>
		</div>

		<!-- Botão download Adobe Reader -->
		<a href="http://get.adobe.com/br/reader/" title="Faça o download do Adobe Reader" class="adobe-reader" target="_blank"><img src="/gsan/imagens/portal/saae/general/adobe-reader.gif" alt="Download do Adobe Reader" /></a>

		<logic:present scope="request" name="voltarServicos">
			<div id="seg-via-conta" class="serv-int" style="margin: 0px;">
				<a href="exibirServicosPortalSaaeAction.do?method=voltarServico" title="Voltar e selecionar outro serviço" class="btn-voltar-servicos">
			    	<img src="/gsan/imagens/portal/saae/general/btn-voltar-servicos.gif" alt="Voltar e selecionar outro serviço" />
			    </a>
			</div>
		</logic:present>
