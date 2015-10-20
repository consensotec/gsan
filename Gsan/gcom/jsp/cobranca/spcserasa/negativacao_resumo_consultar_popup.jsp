<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarResumoNegativacaoPopupAction.do"
	name="InformarDadosConsultaNegativacaoActionForm"
	type="gcom.gui.cobranca.spcserasa.InformarDadosConsultaNegativacaoActionForm"
	method="post">
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="560" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Resumo da Negativação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
			  <tr>
			    <td></td>
			  </tr>
	<tr>
	<td>
			  
	<!--  <div style="width: 121%; height: 175; overflow: auto;">
										
		<table width="100%" align="center" bgcolor="#90c7fc"> -->
              <tr>
			    <td>
		          <strong>Negativador:</strong>
                </td>
			    <td>
			      <html:text property="nomeNegativador" size="45" readonly="true" disabled="true" 
			                 style="background-color:#EFEFEF; border:0;"/>
			    </td>	
              </tr>	
              <tr>
                <td>
                  <strong>Período do envio da Negativação:</strong>
                </td>
                <td>
                  <html:text property="periodoEnvioNegativacaoInicio" size="9" readonly="true" disabled="true"
                             style="background-color:#EFEFEF; border:0; " />
                  <strong>a</strong>
                  <html:text property="periodoEnvioNegativacaoFim" size="9" readonly="true" disabled="true"
                             style="background-color:#EFEFEF; border:0; " />
                </td>
              </tr>		
			<tr>
				<td>
				  <strong>Título do Comando:</strong>
				</td>
				<td>
				   <html:textarea property="tituloComando" cols="45" rows="2" readonly="true" disabled="true"
				                  style="background-color:#EFEFEF; border:0; "/><br>
			    </td>	
			</tr>	
			<tr>
				<td><strong>Localidade Pólo:</strong></td>
				<td>
				<html:text property="idEloPolo" size="5" readonly="true" disabled="true" 
				           style="background-color:#EFEFEF; border:0; "/>
				<html:text property="descricaoEloPolo" size="36" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				</td>
			</tr>
			<tr>
				<td><strong>Localidade:</strong></td>
				<td>
				<html:text property="idLocalidade" size="5" readonly="true" disabled="true" 
				           style="background-color:#EFEFEF; border:0; "/>
				<html:text property="descricaoLocalidade" size="36" readonly="true" disabled="true" 
				           style="background-color:#EFEFEF; border:0; "/>
				</td>
			</tr>
			<tr>
				<td><strong>Setor Comercial:</strong></td>
				<td>
				<html:text property="idSetorComercial" size="5" readonly="true" disabled="true" 
				           style="background-color:#EFEFEF; border:0; " />
				<html:text property="descricaoSetorComercial" size="36" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				</td>
			</tr>
			<tr>
				<td><strong>Quadra:</strong></td>
				<td>
				<html:text property="idQuadra" size="5" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				<html:text property="descricaoQuadra" size="36" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				</td>
			</tr>
            
               <!--  <tr> 
                  <td><strong> Grupo de Cobran&ccedil;a:</strong></td>
                  <td>
                  <logic:present name="colecaoCobrancaGrupoResultado">  
                   	   <<html:select property="arrayCobrancaGrupo" tabindex="7" multiple="true" size="4" disabled="true">
								<html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoCobrancaGrupoResultado" id="cobrancaGrupo" >
							   <option value="<bean:write name="cobrancaGrupo" property="id" />" ><bean:write name="cobrancaGrupo" property="descricao" /></option>
                            </logic:iterate>
						</html:select>
                	</logic:present>
				  </td>
                </tr> -->
                
                 <tr> 
                  <td><strong> Grupo de Cobran&ccedil;a:</strong></td>
                  <td style="text-align: justify">
                  <logic:present name="colecaoCobrancaGrupoResultado">
	                  		<logic:iterate name="colecaoCobrancaGrupoResultado" id="cobrancaGrupo" indexId="index">
	                  			 <logic:equal name="cobrancaGrupo" property="descricao" value="TODOS">
										  TODOS
								</logic:equal>
	                  			<logic:notEqual name="cobrancaGrupo" property="id" value="-1">
		                  			<logic:equal name="cobrancaGrupo" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="cobrancaGrupo" property="descricao"/>:
									</logic:equal>
		                  			<logic:notEqual name="cobrancaGrupo" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="cobrancaGrupo" property="descricao"/> |
									</logic:notEqual>
								</logic:notEqual>                  			
	                         </logic:iterate>
                	</logic:present>
				  </td>
                </tr>
			 
			    <!-- <tr> 
                  <td><strong> <font color="#FF0000"></font></strong><strong> 
                    Ger&ecirc;ncia Regional: </strong></td>
                  <td>
                  <logic:present name="colecaoGerenciaRegionalResultado">  
                   	   <html:select property="arrayGerenciaRegional" tabindex="7" multiple="true" size="4" disabled="true">
						    <html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoGerenciaRegionalResultado" id="gerenciaRegional" >
								 <option value="<bean:write name="gerenciaRegional" property="id" />" ><bean:write name="gerenciaRegional" property="nome" /></option>
							 </logic:iterate>
						</html:select>
                	</logic:present>
                  </td>
                </tr> -->
                
			    <tr> 
                  <td><strong> Ger&ecirc;ncia Regional:</strong></td>
                  <td style="text-align: justify">
                  <logic:present name="colecaoGerenciaRegionalResultado">
	                  		<logic:iterate name="colecaoGerenciaRegionalResultado" id="gerenciaRegional" >
	                  			<logic:equal name="gerenciaRegional" property="nome" value="TODOS">
										  TODOS
								</logic:equal>
	                  			<logic:notEqual name="gerenciaRegional" property="id" value="-1">
	                  				<logic:equal name="gerenciaRegional" property="nome" value="OPÇÕES SELECIONADAS">
										  <bean:write name="gerenciaRegional" property="nome"/>:
									</logic:equal>
		                  			<logic:notEqual name="gerenciaRegional" property="nome" value="OPÇÕES SELECIONADAS">
										  <bean:write name="gerenciaRegional" property="nome"/> | 
									</logic:notEqual>
	                  			</logic:notEqual>                  			
	                         </logic:iterate>
                	</logic:present>
				  </td>
                </tr>
			
			
              <!-- <tr>
                  <td><strong> <font color="#FF0000"></font></strong><strong>Unidade de Neg&oacute;cio: </strong></td>
                  <td>
              	   	  <logic:present name="colecaoUnidadeNegocioResultado">  
                   	   <html:select property="arrayUnidadeNegocio" tabindex="7" multiple="true" size="4" disabled="true">
						    <html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoUnidadeNegocioResultado" id="unidadeNegocio" >
								 <option value="<bean:write name="unidadeNegocio" property="id" />" ><bean:write name="unidadeNegocio" property="nome" /></option>
							 </logic:iterate>
						</html:select>
                	</logic:present>
				  </td>
                </tr> -->
                
                <tr> 
                  <td><strong> Unidade de Neg&oacute;cio:</strong></td>
                  <td style="text-align: justify">
                  <logic:present name="colecaoUnidadeNegocioResultado">
	                  		<logic:iterate name="colecaoUnidadeNegocioResultado" id="unidadeNegocio" >
	                  			 <logic:equal name="unidadeNegocio" property="nome" value="TODOS">
										  TODOS
								</logic:equal>
	                  			<logic:notEqual name="unidadeNegocio" property="id" value="-1">
	                  				<logic:equal name="unidadeNegocio" property="nome" value="OPÇÕES SELECIONADAS">
										  <bean:write name="unidadeNegocio" property="nome"/>:
									</logic:equal>
		                  			<logic:notEqual name="unidadeNegocio" property="nome" value="OPÇÕES SELECIONADAS">
										  <bean:write name="unidadeNegocio" property="nome"/> | 
									</logic:notEqual>
	                  			</logic:notEqual>                  			
	                         </logic:iterate>
                	</logic:present>
				  </td>
                </tr>
                
                
                <!-- <tr> 
                  <td> <strong> <font color="#FF0000"></font></strong><strong> Perfil do Im&oacute;vel:</strong></td>

                  <td align="left"><strong>           
                	  <logic:present name="colecaoImovelPerfilResultado">  
                   	   <html:select property="arrayImovelPerfil" tabindex="7" multiple="true" size="4" disabled="true">
						    <html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoImovelPerfilResultado" id="imovelPerfil" >
								 <option value="<bean:write name="imovelPerfil" property="id" />" ><bean:write name="imovelPerfil" property="descricao" /></option>
							 </logic:iterate>
						</html:select>
					</logic:present>
				  </td>
                </tr> -->
                
                <tr> 
                  <td><strong>Perfil do Im&oacute;vel: </strong></td>
                  <td style="text-align: justify">
                  <logic:present name="colecaoImovelPerfilResultado">
	                  		<logic:iterate name="colecaoImovelPerfilResultado" id="imovelPerfil" >
	                  			<logic:equal name="imovelPerfil" property="descricao" value="TODOS">
										  TODOS
								</logic:equal>
	                  			<logic:notEqual name="imovelPerfil" property="id" value="-1">
	                  				<logic:equal name="imovelPerfil" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="imovelPerfil" property="descricao"/>:
									</logic:equal>
		                  			<logic:notEqual name="imovelPerfil" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="imovelPerfil" property="descricao"/> | 
									</logic:notEqual>
	                  			</logic:notEqual>                  			
	                         </logic:iterate>
                	</logic:present>
				  </td>
                </tr>
                
                <!--  <tr> 
                  <td><strong> Categoria:<font color="#FF0000"></font></strong></td>
                  <td align="left"> 
                 	  <logic:present name="colecaoCategoriaResultado">  
                   	   <html:select property="arrayCategoria" tabindex="7" multiple="true" size="4" disabled="true">
						    <html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoCategoriaResultado" id="categoria" >
								 <option value="<bean:write name="categoria" property="id" />" ><bean:write name="categoria" property="descricao" /></option>
							 </logic:iterate>
						</html:select>
                	</logic:present>
                    </td>
                </tr> -->
                
                <tr> 
                  <td><strong>Categoria: </strong></td>
                  <td style="text-align: justify">
                  <logic:present name="colecaoCategoriaResultado">
	                  		<logic:iterate name="colecaoCategoriaResultado" id="categoria" >
	                  			<logic:equal name="categoria" property="descricao" value="TODOS">
										  TODOS
								</logic:equal>
	                  			<logic:notEqual name="categoria" property="id" value="-1">
	                  				<logic:equal name="categoria" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="categoria" property="descricao"/>:
									</logic:equal>
		                  			<logic:notEqual name="categoria" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="categoria" property="descricao"/> | 
									</logic:notEqual>
	                  			</logic:notEqual>                  			
	                         </logic:iterate>
                	</logic:present>
				  </td>
                </tr>
                
                
                <!--  <tr> 
                  <td><strong> Esfera de Poder:<font color="#FF0000"></font></strong></td>
                  <td align="left">
                	 <logic:present name="colecaoEsferaPoderResultado">  
                   	   <html:select property="arrayEsferaPoder" tabindex="7" multiple="true" size="4" disabled="true">
						    <html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoEsferaPoderResultado" id="esferaPoder" >
								 <option value="<bean:write name="esferaPoder" property="id" />" ><bean:write name="esferaPoder" property="descricao" /></option>
							 </logic:iterate>
						</html:select>
                	</logic:present>
                  </td>
                </tr>  -->    
                
                
                <tr> 
                  <td><strong>Esfera de Poder: </strong></td>
                  <td style="text-align: justify">
                  <logic:present name="colecaoEsferaPoderResultado">
	                  		<logic:iterate name="colecaoEsferaPoderResultado" id="esferaPoder" >
	                  			<logic:equal name="esferaPoder" property="descricao" value="TODOS">
										  TODOS
								</logic:equal>
	                  			<logic:notEqual name="esferaPoder" property="id" value="-1">	                  			
	                  				<logic:equal name="esferaPoder" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="esferaPoder" property="descricao"/>:
									</logic:equal>
		                  			<logic:notEqual name="esferaPoder" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="esferaPoder" property="descricao"/> | 
									</logic:notEqual>
	                  			</logic:notEqual>                  			
	                         </logic:iterate>
                	</logic:present>
				  </td>
                </tr>
                      
            
              <!--   <tr>
                  <td><strong> Tipo de Cliente:<font color="#FF0000"></font></strong></td>
                  <td align="left">			
                	<logic:present name="colecaoClienteTipoResultado">  
                   	   <html:select property="arrayTipoCliente" tabindex="7" multiple="true" size="4" disabled="true">
						    <html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoClienteTipoResultado" id="clienteTipo" >
								 <option value="<bean:write name="clienteTipo" property="id" />" ><bean:write name="clienteTipo" property="descricao" /></option>
							 </logic:iterate>
						</html:select>
                	</logic:present>
                  </td>
              </tr>   
              -->
              
               <tr> 
                  <td><strong>Tipo de Cliente: </strong></td>
                  <td style="text-align: justify">
                  <logic:present name="colecaoClienteTipoResultado">
	                  		<logic:iterate name="colecaoClienteTipoResultado" id="clienteTipo" >
	                  			<logic:equal name="clienteTipo" property="descricao" value="TODOS">
										  TODOS
								</logic:equal>
	                  			<logic:notEqual name="clienteTipo" property="id" value="-1">
	                  				<logic:equal name="clienteTipo" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="clienteTipo" property="descricao"/>:
									</logic:equal>
		                  			<logic:notEqual name="clienteTipo" property="descricao" value="OPÇÕES SELECIONADAS">
										  <bean:write name="clienteTipo" property="descricao"/> | 
									</logic:notEqual>
	                  			</logic:notEqual>                  			
	                         </logic:iterate>
                	</logic:present>
				  </td>
                </tr>
              
                         
	        </table>
   			 <!--  </div>
    		<td>-->
           <tr>
              
              <tr> 
                <td colspan="2">
                  <div align="center">
                    <hr>
                  </div>
                </td>
              </tr>    
              <tr>
			    <td>
		          <strong>Situação da Negativação:</strong>
                </td>
			    <td>
                  <bean:write scope="session"
							  name="situacaoNegativacao"/>
			    </td>	
              </tr>	
              <tr> 
                <td colspan="2">
                  <div align="center">
                    <hr>
                  </div>
                </td>
              </tr>    
            </table>
			<table bgcolor="#90c7fc" border="0" width="100%">
			  <tr>
			    <td bgcolor="#79bbfd" align="center" colspan="5" ><strong>Situação do Débito da Negativação</strong></td>
			  </tr>

				<tr>
					<td bgcolor="#cbe5fe" align="center" colspan="5" width="25%">
					  <strong>
					    <bean:write scope="session" name="descricaoIncluidas"/> 
					  </strong>
					</td>
					<!-- <td bgcolor="#79bbfd" align="center"><strong> Valor</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual </strong></td> -->
				</tr>
						<!-- <tr> 
						  
								<td width="17%" align="center" bgcolor="#FFFFFF"><bean:write
								    scope="session"
									name="somatorioValorDebito"/>
								</td>
									
								<td width="17%" align="center" bgcolor="#FFFFFF">
								  100,00
								</td>
						</tr> -->
			  <tr>
			    <td bgcolor="#79bbfd" align="center" colspan="5" ><strong>Situação Parcial do Débito da Negativação</strong></td>
			  </tr>
			  <tr>
			  	<td width="25%"></td>
			  	<td bgcolor="#79bbfd" align="center" width="15%"><strong> Valor</strong></td>
				<td bgcolor="#79bbfd" align="center" width="15%"><strong> Percentual </strong></td>
				<td bgcolor="#79bbfd" align="center" width="15%"><strong> Qtd</strong></td>
				<td bgcolor="#79bbfd" align="center" width="15%"><strong> Percentual </strong></td>
			  </tr>
			</table>
			
			
			      <table width="100%" bgcolor="#90c7fc" border="0"> 
				    <logic:iterate name="colecaoResumoNegativacaoDetalhePopUp" id="resumoNegativacao">
				      <tr>
					    <td rowspan="2" bgcolor="#cbe5fe" width="25%">
					      <strong><bean:write name="resumoNegativacao"property="descricao"/></strong>
					   	</td>
					  </tr>
					  <tr>
					   	<td bgcolor="#FFFFFF" width="15%" align="center">
					   		<bean:write name="resumoNegativacao"
					   		            property="valorDinamico" formatKey="money.format"/>
					   	</td>
					   	<td bgcolor="#FFFFFF" width="15%" align="center">
					   	  <bean:write name="resumoNegativacao"
					   		          property="percentualValor" formatKey="money.format"/>
					   	</td>
					   	<td width="15%">&nbsp;</td>
					   	<td width="15%">&nbsp;</td>
					   	<!-- <td bgcolor="#FFFFFF" width="15%" align="center">
					   		<bean:write name="resumoNegativacao"
					   		            property="quantidadeInclusao"/>
					   	</td>
					   	<td bgcolor="#FFFFFF" width="15%" align="center">
					   	  <bean:write name="resumoNegativacao"
					   		          property="percentualQtd" formatKey="money.format"/>
					   	</td> -->
					  </tr>											
				    </logic:iterate>  
				</table>
				
			<table width="100%" bgcolor="#90c7fc" border="0">
				<tr>
					<td bgcolor="#cbe5fe" align="center" rowspan="1" width="25%">
					  <strong>Total:</strong>
					</td>
				<!--  </tr>
				<tr>-->
								<td width="15%" align="center" bgcolor="#FFFFFF"><bean:write
								    scope="session"
									name="somatorioValorDebito" formatKey="money.format"/>
								</td>
									
								<td width="15%" align="center" bgcolor="#FFFFFF">
								  100,00
								</td>
								<td width="15%">&nbsp;</td>
								<td width="15%">&nbsp;</td>
								<!-- <td width="15%" align="center" bgcolor="#FFFFFF"><bean:write
								    scope="session"
									name="somatorioTotalQuantidade" formatKey="money.format"/>
								</td>
									
								<td width="15%" align="center" bgcolor="#FFFFFF">
								  100,00
								</td> -->
				</tr>
			</table> 
			
			<table bgcolor="#90c7fc" border="0" width="100%">
				<tr>
					<td bgcolor="#79bbfd" align="center" colspan="3" ><strong>Situação de &Aacute;gua</strong></td>
				</tr>
			</table>
			<table width="100%" bgcolor="#90c7fc" border="0"> 
				<logic:iterate name="colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp" id="resumoNegativacaoLigacaoAguaSituacao">
				<tr>
					<td rowspan="2" bgcolor="#cbe5fe" width="25%">
						<strong><bean:write name="resumoNegativacaoLigacaoAguaSituacao" property="descricao"/></strong>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" width="15%" align="center">
						<bean:write name="resumoNegativacaoLigacaoAguaSituacao" property="valorDinamico" formatKey="money.format"/>
					</td>
					<td bgcolor="#FFFFFF" width="15%" align="center">
						<bean:write name="resumoNegativacaoLigacaoAguaSituacao" property="percentualValor" formatKey="money.format"/>
					</td>
					<td bgcolor="#FFFFFF" width="15%" align="center">
						<bean:write name="resumoNegativacaoLigacaoAguaSituacao" property="quantidadeInclusao" />
					</td>
					<td bgcolor="#FFFFFF" width="15%" align="center">
						<bean:write name="resumoNegativacaoLigacaoAguaSituacao" property="percentualQtd" formatKey="money.format"/>
					</td>
				</tr>											
			    </logic:iterate> 
			</table> 
			
			
			<table width="100%" bgcolor="#90c7fc" border="0">
				<tr>
								<td width="25%"><strong>Total:</strong></td>
								<td width="15%" align="center" bgcolor="#FFFFFF"><bean:write
								    scope="session"
									name="somatorioValorDebito" formatKey="money.format"/>
								</td>
									
								<td width="15%" align="center" bgcolor="#FFFFFF">
								  100,00
								</td>
								<td width="15%" align="center" bgcolor="#FFFFFF"><bean:write
								    scope="session"
									name="somatorioTotalQuantidade"/>
								</td>
									
								<td width="15%" align="center" bgcolor="#FFFFFF">
								  100,00
								</td>
				</tr>
			</table> 
			
			<table border="0" width="100%">
				<tr>
					<td colspan="3" align="right">
						<input name="Button" type="button" class="bottonRightCol" value="Imprimir" onClick="javascript:window.print();">&nbsp;&nbsp;&nbsp;
						<input name="Button" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:fechar();">
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<body>
</html:html>
