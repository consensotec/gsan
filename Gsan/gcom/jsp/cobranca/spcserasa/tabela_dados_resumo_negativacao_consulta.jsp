<table width="100%" border="0" bgcolor="#99ccff">
	<tr heigth="20">
		<td align="center"><strong>Dados da Geração da Consulta</strong></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" bgcolor="#cbe5fe">
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
				<td><strong>Período do envio da Negativação:</strong></td>
				<td>
				<html:text property="periodoEnvioNegativacaoInicio" size="8" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				<strong>a</strong>
				<html:text property="periodoEnvioNegativacaoFim" size="8" readonly="true" disabled="true"
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
      	      <td><strong>Grupo de Cobrança:</strong></td>
                <td>
			      <html:select property="idGrupoCobranca" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoCobrancaGrupoResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr> -->
            
               <tr> 
                  <td><strong> Grupo de Cobran&ccedil;a:</strong></td>
                  <td>
                  <logic:present name="colecaoCobrancaGrupoResultado">  
                   	   <html:select property="arrayCobrancaGrupo" tabindex="7" multiple="true" size="4" disabled="true">
								<html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoCobrancaGrupoResultado" id="cobrancaGrupo" >
							   <option value="<bean:write name="cobrancaGrupo" property="id" />" ><bean:write name="cobrancaGrupo" property="descricao" /></option>
                            </logic:iterate>
						</html:select>
                	</logic:present>
				  </td>
                </tr>

            <!-- <tr>
      	      <td><strong>Gerência Regional:</strong></td>
                <td>
			      <html:select property="idGerenciaRegional" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoGerenciaRegionalResultado" labelProperty="nomeAbreviado" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr> -->
			 
			     <tr> 
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
                </tr>
			
           <!-- <tr>
      	      <td><strong>Unidade de Negócio:</strong></td>
                <td>
			      <html:select property="idUnidadeNegocio" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoUnidadeNegocioResultado" labelProperty="nomeAbreviado" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr> -->
                <tr>
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
                </tr>
            
            <!--  <tr>
      	      <td><strong>Perfil do Imóvel:</strong></td>
                <td>
			      <html:select property="idImovelPerfil" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoImovelPerfilResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr> -->
            
                <tr> 
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
                </tr>
            
            <!--  <tr>
      	      <td><strong>Categoria:</strong></td>
                <td>
			      <html:select property="idCategoria" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoCategoriaResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>  -->
                <tr> 
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
                </tr>
            
            
            <!-- <tr>
      	      <td><strong>Esfera de Poder:</strong></td>
                <td>
			      <html:select property="idEsferaPoder" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoEsferaPoderResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>  -->
            
                <tr> 
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
                </tr>            
            
           <!--   <tr>
      	      <td><strong>Tipo de Cliente:</strong></td>
                <td>
			      <html:select property="tipoCliente" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoClienteTipoResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr> -->
            
               <tr>
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
              
              <tr>
                  <td><strong> Liga&ccedil;&atilde;o &Aacute;gua Situa&ccedil;&atilde;o:<font color="#FF0000"></font></strong></td>
                  <td align="left">			
                	<logic:present name="colecaoLigacaoAguaSituacaoResultado">  
                   	   <html:select property="arrayLigacaoAguaSituacao" tabindex="7" multiple="true" size="4" disabled="true">
						    <html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoLigacaoAguaSituacaoResultado" id="ligacaoAguaSituacao" >
								 <option value="<bean:write name="ligacaoAguaSituacao" property="id" />" ><bean:write name="ligacaoAguaSituacao" property="descricao" /></option>
							 </logic:iterate>
						</html:select>
                	</logic:present>
                  </td>
              </tr>
              
              <tr>
                  <td><strong> Liga&ccedil;&atilde;o Esgoto Situa&ccedil;&atilde;o:<font color="#FF0000"></font></strong></td>
                  <td align="left">			
                	<logic:present name="colecaoLigacaoEsgotoSituacaoResultado">  
                   	   <html:select property="arrayLigacaoEsgotoSituacao" tabindex="7" multiple="true" size="4" disabled="true">
						    <html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoLigacaoEsgotoSituacaoResultado" id="ligacaoEsgotoSituacao" >
								 <option value="<bean:write name="ligacaoEsgotoSituacao" property="id" />" ><bean:write name="ligacaoEsgotoSituacao" property="descricao" /></option>
							 </logic:iterate>
						</html:select>
                	</logic:present>
                  </td>
              </tr>

		</table>
		</td>
	</tr>
</table>
<br>
