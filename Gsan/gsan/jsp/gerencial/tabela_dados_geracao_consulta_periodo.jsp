<!-- Para o uso deste include é necessário manda a colecaoAgrupamento(que é de Object[3]) e o período de Referencia
já formatado ver ExibirResultadoConsultaResumoAnormalidadeAction.java  -->

<table width="100%" border="0" bgcolor="#99ccff">
	<tr heigth="20">
		<td align="center"><strong>Dados da Geração da Consulta</strong></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" bgcolor="#cbe5fe">
			<tr>
				<td><strong>Período de Referência:</strong></td>
				<td>
					<input type="text" name="anoMesInicialReferencia"
						value="${informarDadosGeracaoRelatorioConsultaPeriodoHelper.formatarAnoMesParaMesAnoInicial}"
						size="10" disabled="true" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;"/>&nbsp;&nbsp;A&nbsp;&nbsp;
					<input type="text" name="anoMesFinalReferencia"
						value="${informarDadosGeracaoRelatorioConsultaPeriodoHelper.formatarAnoMesParaMesAnoFinal}"
						size="10" disabled="true" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;"/>&nbsp;&nbsp;(MM/AAAA)
				</td>
			</tr>
			<tr>
				<td><strong>Opção de Totalização:</strong></td>
				<td><input type="text" name="opcaoTotalizacao"
					value="${informarDadosGeracaoRelatorioConsultaPeriodoHelper.descricaoOpcaoTotalizacao}"
					size="60" disabled="true" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;" /></td>
			</tr>
			<logic:iterate name="colecaoAgrupamento" id="agrupamento"
				scope="Session">
				<tr>
					<td><strong> <%=((Object[]) agrupamento)[0]%>:</strong></td>
					<td><input type="text" name="opcao1"
						value="<%= ((Object[])agrupamento)[1] %>" size="5" disabled="true" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;" />
					&nbsp;&nbsp;<input type="text" name="opcao2"
						value="<%= ((Object[])agrupamento)[2]%>" size="30" disabled="true" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;" />
					</td>
				</tr>
			</logic:iterate>
			<tr>
				<td><strong>Perfil do Imóvel:</strong></td>
				<td><html:select property="idPerfilImovel">
					<html:options collection="colecaoImovelPerfilResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Situação de Ligação de Água:</strong></td>
				<td><html:select property="idSituacaoLigacaoAgua">
					<html:options collection="colecaoLigacaoAguaSituacaoResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Situação de Ligação de Esgoto:</strong></td>
				<td><html:select property="idSituacaoLigacaoEsgoto">
					<html:options collection="colecaoLigacaoEsgotoSituacaoResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Categoria:</strong></td>
				<td><html:select property="idCategoria">
					<html:options collection="colecaoCategoriaResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Esfera de Poder:</strong></td>
				<td><html:select property="idEsfera">
					<html:options collection="colecaoEsferaPoderResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<logic:present name="faturamento">
				<tr>
					<td><strong>Tipo Análise Faturamento:</strong></td>
					<td><logic:equal name="informarDadosGeracaoRelatorioConsultaPeriodoHelper"
						property="tipoAnaliseFaturamento" value="1">
						<input type="text" name="tipoAnaliseFaturamento" value="REAL"
							size="10" disabled="true" />
					</logic:equal> <logic:equal
						name="informarDadosGeracaoRelatorioConsultaPeriodoHelper"
						property="tipoAnaliseFaturamento" value="2">
						<input type="text" name="tipoAnaliseFaturamento" value="SIMULADO"
							size="10" disabled="true" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;" />
					</logic:equal></td>
				</tr>
			</logic:present>
		</table>
		</td>
	</tr>
</table>
<br>
