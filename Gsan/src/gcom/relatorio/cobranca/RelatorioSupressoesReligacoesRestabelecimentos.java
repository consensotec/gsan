/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
* Anderson Italo Felinto de Lima
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  

package gcom.relatorio.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

/**
 * Descri��o da classe
 * Classe respons�vel pelo processamento dos
 * parametros informados e consequente 
 * montagem dos registros exibidos posteriormente
 * pelo relat�rio
 * 
 * @author Anderson Italo
 * @date 01/08/2009
 */
public class RelatorioSupressoesReligacoesRestabelecimentos extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioSupressoesReligacoesRestabelecimentos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SUPRESSOES_RELIGACOES_REESTABELECIMENTOS);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "R0906");
		
		FiltroSupressoesReligacoesReestabelecimentoHelper filtro = (FiltroSupressoesReligacoesReestabelecimentoHelper) getParametro("filtroSupressoesReligacoesReestabelecimentos");
		List objetosEncontrados = (List)getParametro("objetosEncontrados");
		filtro.setNumeroPagina(-1);
		
		//TRECHO PARA RECUPERA��O DOS PARAMETROS
		//periodo
		//data inicial
		String dataInicial = "";
		if (filtro.getDataEmissaoInicio() !=null && !filtro.getDataEmissaoInicio().equals("")){
			dataInicial = filtro.getDataEmissaoInicio(); 
		}
		
		//data final
		String dataFinal = "";
		if (filtro.getDataEmissaoFim() !=null && !filtro.getDataEmissaoFim().equals("")){
			dataFinal = filtro.getDataEmissaoFim(); 
		}
		
		String periodo = "";
		if (!dataInicial.equals("") && !dataFinal.equals("")){
			periodo = dataInicial.substring(0,11) +  " a " + dataFinal.substring(0,11);
		}
		
		//limite religa��o ap�s corte
		String limiteReligacaoAposCorte = "";
		if (filtro.getLimititeReligacaoPosCorte() != null && !filtro.getLimititeReligacaoPosCorte().equals("")) {
			limiteReligacaoAposCorte = filtro.getLimititeReligacaoPosCorte();
		}else{
			limiteReligacaoAposCorte = "0";
		}
		
		//empresa
		String empresa = "";
		if (filtro.getIdEmpresa() != null 
				&& !filtro.getIdEmpresa().equals("")){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(
					new ParametroSimples(FiltroEmpresa.ID, filtro.getIdEmpresa()));
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			Empresa empresaEntidade = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
			empresa = empresaEntidade.getDescricao();
		}
		
		//define o tipo da combina��o escolhida
		String tipoCombinacaoIdentificador = "";
		String tipoCombinacaoDescricao = "";
		String estadoHeader = "";
		String gerenciaHeader = "";
		String unidadeNegocioHeader = "";
		String localidadeHeader = "";
		Integer indicadorTipoRelatorio = filtro.getIndicadorTipoRelatorio();
		
		switch (indicadorTipoRelatorio) {
		case 1:
			tipoCombinacaoIdentificador="TIPO 1";
			tipoCombinacaoDescricao="Resumo por Estado";
			estadoHeader = "Estado";
			break;
		case 2:
			tipoCombinacaoIdentificador="TIPO 2";
			tipoCombinacaoDescricao="Resumo do Estado por Ger�ncia";
			estadoHeader = "Estado";
			gerenciaHeader = "Ger�ncia";
			break;
		case 3:
			tipoCombinacaoIdentificador="TIPO 3";
			tipoCombinacaoDescricao="Resumo por Ger�ncia Espec�fica";
			gerenciaHeader = "Ger�ncia";
			break;
		case 4:
			tipoCombinacaoIdentificador="TIPO 4";
			tipoCombinacaoDescricao="Resumo do Estado por Ger�ncia por Unidade de Neg�cio";
			estadoHeader = "Estado";
			gerenciaHeader = "Ger�ncia";
			unidadeNegocioHeader = "Unidade de Neg�cio";
			break;
		case 5:
			tipoCombinacaoIdentificador="TIPO 5";
			tipoCombinacaoDescricao="Resumo por Unidade de Neg�cio Espec�fica";
			unidadeNegocioHeader = "Unidade de Neg�cio";
			break;
		case 6:
			tipoCombinacaoIdentificador="TIPO 6";
			tipoCombinacaoDescricao="Resumo do Estado por Ger�ncia por Unidade de Neg�cio por Localidade";
			estadoHeader = "Estado";
			gerenciaHeader = "Ger�ncia";
			unidadeNegocioHeader = "Unidade de Neg�cio";
			localidadeHeader = "Localidade";
			break;
		case 7:
			tipoCombinacaoIdentificador="TIPO 7";
			tipoCombinacaoDescricao="Resumo por Localidade Espec�fica";
			localidadeHeader = "Localidade";
			break;
		case 8:
			tipoCombinacaoIdentificador="TIPO 8";
			tipoCombinacaoDescricao="Ger�ncia Especifica por Unidade de Neg�cio";
			gerenciaHeader = "Ger�ncia";
			unidadeNegocioHeader = "Unidade de Neg�cio";
			break;
		case 9:
			tipoCombinacaoIdentificador="TIPO 9";
			tipoCombinacaoDescricao="Ger�ncias Especificas por Unidade de Neg�cio por Localidade";
			gerenciaHeader = "Ger�ncia";
			unidadeNegocioHeader = "Unidade de Neg�cio";
			localidadeHeader = "Localidade";
			break;
		case 10:
			tipoCombinacaoIdentificador="TIPO 10";
			tipoCombinacaoDescricao="Unidade de Neg�cio Espec�fica por Localidade";
			unidadeNegocioHeader = "Unidade de Neg�cio";
			localidadeHeader = "Localidade";
			break;
		default:
			break;
		}
		
		//seta os parametros
		parametros.put("periodo", periodo);
		parametros.put("limiteReligacaoAposCorte", limiteReligacaoAposCorte);
		parametros.put("empresa", empresa);
		parametros.put("tipoCombinacaoIdentificador", tipoCombinacaoIdentificador);
		parametros.put("tipoCombinacaoDescricao", tipoCombinacaoDescricao);
		parametros.put("estadoHeader", estadoHeader);
		parametros.put("gerenciaHeader", gerenciaHeader);
		parametros.put("unidadeNegocioHeader", unidadeNegocioHeader);
		parametros.put("localidadeHeader", localidadeHeader);
		
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		List<RelatorioSupressoesReligacoesReestabelecimentosBean> beans = new ArrayList<RelatorioSupressoesReligacoesReestabelecimentosBean>();
		Object obj = null;
		Object[] dados = null;
		Object[] dadosAnterior = null;
		Integer totalizadorReligacoesAntesGerencia = 0;
		Integer totalizadorReligacoesAposGerencia = 0;
		Integer totalizadorSupressoesGerencia = 0;
		Integer totalizadorReestabelecimentosGerencia = 0;
		Integer totalizadorCortadosGerencia = 0;
		Integer totalizadorReligacoesAntesEstado = 0;
		Integer totalizadorReligacoesAposEstado = 0;
		Integer totalizadorSupressoesEstado = 0;
		Integer totalizadorReestabelecimentosEstado = 0;
		Integer totalizadorCortadosEstado = 0;
		Integer totalizadorReligacoesAntesUnidade = 0;
		Integer totalizadorReligacoesAposUnidade = 0;
		Integer totalizadorSupressoesUnidade = 0;
		Integer totalizadorReestabelecimentosUnidade = 0;
		Integer totalizadorCortadosUnidade = 0;
		Integer totalizadorReligacoesAntesLocalidade = 0;
		Integer totalizadorReligacoesAposLocalidade = 0;
		Integer totalizadorSupressoesLocalidade = 0;
		Integer totalizadorReestabelecimentosLocalidade = 0;
		Integer totalizadorCortadosLocalidade = 0;
		int totalBeansPorGerencia = 0;
		int totalBeansPorUnidade = 0;
		
		//seta os dados dos beans
		if (objetosEncontrados != null){
			for (int i = 0; i < objetosEncontrados.size(); i++) {
				obj = objetosEncontrados.get(i);
				
				if (obj instanceof Object[]) {
					dados = (Object[]) obj;
					
					RelatorioSupressoesReligacoesReestabelecimentosBean bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
					
					switch (indicadorTipoRelatorio) {
					case 1:
						//TIPO 1 - Resumo por Estado
						totalizadorReligacoesAntesEstado += new Integer(dados[4].toString());
						totalizadorReligacoesAposEstado += new Integer(dados[5].toString());
						totalizadorSupressoesEstado += new Integer(dados[6].toString());
						totalizadorReestabelecimentosEstado += new Integer(dados[7].toString());
						totalizadorCortadosEstado += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setEstado("ESTADO");
							bean.setReligacoesAntesDiasEstado(totalizadorReligacoesAntesEstado);
							bean.setReligacoesAposDiasEstado(totalizadorReligacoesAposEstado);
							bean.setSupressoesEstado(totalizadorSupressoesEstado);
							bean.setReestabelecimentosEstado(totalizadorReestabelecimentosEstado);
							bean.setCortadosEstado(totalizadorCortadosEstado);
							beans.add(bean);
						}
						break;
					case 2:
						//TIPO 2 - Resumo do Estado por Ger�ncia
						
						totalizadorReligacoesAntesEstado += new Integer(dados[4].toString());
						totalizadorReligacoesAposEstado += new Integer(dados[5].toString());
						totalizadorSupressoesEstado += new Integer(dados[6].toString());
						totalizadorReestabelecimentosEstado += new Integer(dados[7].toString());
						totalizadorCortadosEstado += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setEstado("ESTADO");
							bean.setReligacoesAntesDiasEstado(totalizadorReligacoesAntesEstado);
							bean.setReligacoesAposDiasEstado(totalizadorReligacoesAposEstado);
							bean.setSupressoesEstado(totalizadorSupressoesEstado);
							bean.setReestabelecimentosEstado(totalizadorReestabelecimentosEstado);
							bean.setCortadosEstado(totalizadorCortadosEstado);
							beans.add(0,bean);
						}
						//para preencher as gerencias
						if (dadosAnterior == null || dados[1].toString().equals(dadosAnterior[1].toString())){
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}else{
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dadosAnterior[0].toString());
							bean.setGerenciaRegionalNome("-" + dadosAnterior[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesGerencia = 0;
							totalizadorReligacoesAposGerencia = 0;
							totalizadorSupressoesGerencia = 0;
							totalizadorReestabelecimentosGerencia = 0;
							totalizadorCortadosGerencia = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(bean);
						}
						
						break;
					case 3:
						//TIPO 3 - Resumo por Ger�ncia Espec�fica
						//para preencher as gerencias
						totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
						totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
						totalizadorSupressoesGerencia += new Integer(dados[6].toString());
						totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
						totalizadorCortadosGerencia += new Integer(dados[8].toString());
						
						if (i == (objetosEncontrados.size() - 1)){
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(bean);
						}
						break;
					case 4:
						//TIPO 4 - Resumo do Estado por Ger�ncia por Unidade de Neg�cio
						//para preencher as unidades de neg�cio
						if (dadosAnterior == null || dados[2].toString().equals(dadosAnterior[2].toString())){
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}else{
							bean.setUnidadeNegocio(dadosAnterior[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesUnidade = 0;
							totalizadorReligacoesAposUnidade = 0;
							totalizadorSupressoesUnidade = 0;
							totalizadorReestabelecimentosUnidade = 0;
							totalizadorCortadosUnidade = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
							//atualiza o indexe dos beans
							totalBeansPorGerencia++;
						}
						
						//para totalizar as gerencias
						if (dadosAnterior == null || dados[1].toString().equals(dadosAnterior[1].toString())){
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}else if (dadosAnterior != null && !dados[1].toString().equals(dadosAnterior[1].toString())){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							// preenche a gerencia
							bean.setGerenciaRegionalNomeAbreviado(dadosAnterior[0].toString());
							bean.setGerenciaRegionalNome("-" + dadosAnterior[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
							//zera os totalizadores
							totalBeansPorGerencia = 0;
							totalizadorReligacoesAntesGerencia = 0;
							totalizadorReligacoesAposGerencia = 0;
							totalizadorSupressoesGerencia = 0;
							totalizadorReestabelecimentosGerencia = 0;
							totalizadorCortadosGerencia = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}
						
						//para preencher o estado
						totalizadorReligacoesAntesEstado += new Integer(dados[4].toString());
						totalizadorReligacoesAposEstado += new Integer(dados[5].toString());
						totalizadorSupressoesEstado += new Integer(dados[6].toString());
						totalizadorReestabelecimentosEstado += new Integer(dados[7].toString());
						totalizadorCortadosEstado += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setEstado("ESTADO");
							bean.setReligacoesAntesDiasEstado(totalizadorReligacoesAntesEstado);
							bean.setReligacoesAposDiasEstado(totalizadorReligacoesAposEstado);
							bean.setSupressoesEstado(totalizadorSupressoesEstado);
							bean.setReestabelecimentosEstado(totalizadorReestabelecimentosEstado);
							bean.setCortadosEstado(totalizadorCortadosEstado);
							beans.add(0,bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(bean);
						}
						break;
					case 5:
						//TIPO 5 - Resumo por Unidade de Neg�cio Espec�fica
						//para totaliza as Unidades 
						totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
						totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
						totalizadorSupressoesUnidade += new Integer(dados[6].toString());
						totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
						totalizadorCortadosUnidade += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
						}
						break;
					case 6:
						//TIPO 6 - Resumo do Estado por Ger�ncia por Unidade de Neg�cio por Localidade
						//para preencher as localidades
						if (dadosAnterior == null || dados[3].toString().equals(dadosAnterior[3].toString())){
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						}else{
							bean.setLocalidade(dadosAnterior[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesLocalidade = 0;
							totalizadorReligacoesAposLocalidade = 0;
							totalizadorSupressoesLocalidade = 0;
							totalizadorReestabelecimentosLocalidade = 0;
							totalizadorCortadosLocalidade = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
							//atualiza o indexe dos beans
							totalBeansPorUnidade++;
							totalBeansPorGerencia++;
						}
						
						//para totalizar as unidades
						if (dadosAnterior == null || dados[2].toString().equals(dadosAnterior[2].toString())){
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}else if (dadosAnterior != null && !dados[2].toString().equals(dadosAnterior[2].toString())){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							// preenche a Unidade
							bean.setUnidadeNegocio(dadosAnterior[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorUnidade, bean);
							totalBeansPorGerencia ++;
							//zera os totalizadores
							totalBeansPorUnidade = 0;
							totalizadorReligacoesAntesUnidade = 0;
							totalizadorReligacoesAposUnidade = 0;
							totalizadorSupressoesUnidade = 0;
							totalizadorReestabelecimentosUnidade = 0;
							totalizadorCortadosUnidade = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}
						
						//para totalizar as gerencias
						if (dadosAnterior == null || dados[1].toString().equals(dadosAnterior[1].toString())){
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}else if (dadosAnterior != null && !dados[1].toString().equals(dadosAnterior[1].toString())){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							// preenche a gerencia
							bean.setGerenciaRegionalNomeAbreviado(dadosAnterior[0].toString());
							bean.setGerenciaRegionalNome("-" + dadosAnterior[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
							//zera os totalizadores
							totalBeansPorGerencia = 0;
							totalizadorReligacoesAntesGerencia = 0;
							totalizadorReligacoesAposGerencia = 0;
							totalizadorSupressoesGerencia = 0;
							totalizadorReestabelecimentosGerencia = 0;
							totalizadorCortadosGerencia = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}
						
						//para preencher o estado
						totalizadorReligacoesAntesEstado += new Integer(dados[4].toString());
						totalizadorReligacoesAposEstado += new Integer(dados[5].toString());
						totalizadorSupressoesEstado += new Integer(dados[6].toString());
						totalizadorReestabelecimentosEstado += new Integer(dados[7].toString());
						totalizadorCortadosEstado += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setEstado("ESTADO");
							bean.setReligacoesAntesDiasEstado(totalizadorReligacoesAntesEstado);
							bean.setReligacoesAposDiasEstado(totalizadorReligacoesAposEstado);
							bean.setSupressoesEstado(totalizadorSupressoesEstado);
							bean.setReestabelecimentosEstado(totalizadorReestabelecimentosEstado);
							bean.setCortadosEstado(totalizadorCortadosEstado);
							beans.add(0,bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorUnidade, bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setLocalidade(dados[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
						}
						
						break;
					case 7:
						//TIPO 7 - Resumo por Localidade Espec�fica
						//para totalizar a localidade
						totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
						totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
						totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
						totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
						totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						
						if (i == (objetosEncontrados.size() - 1)){
							bean.setLocalidade(dados[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
						}
						break;
					case 8:
						//TIPO 8 - Ger�ncia Especifica por Unidade de Neg�cio
						totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
						totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
						totalizadorSupressoesGerencia += new Integer(dados[6].toString());
						totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
						totalizadorCortadosGerencia += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							beans.add(0,bean);
						}
						
						//para preencher as unidades de negocio
						if (dadosAnterior == null || dados[2].toString().equals(dadosAnterior[2].toString())){
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}else{
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dadosAnterior[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesUnidade = 0;
							totalizadorReligacoesAposUnidade = 0;
							totalizadorSupressoesUnidade = 0;
							totalizadorReestabelecimentosUnidade = 0;
							totalizadorCortadosUnidade = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(bean);
						}
						break;
					case 9:
						//TIPO 9 - Ger�ncias Especificas por Unidade de Neg�cio por Localidade
						//para preencher as localidades
						if (dadosAnterior == null || dados[3].toString().equals(dadosAnterior[3].toString())){
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						}else{
							bean.setLocalidade(dadosAnterior[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesLocalidade = 0;
							totalizadorReligacoesAposLocalidade = 0;
							totalizadorSupressoesLocalidade = 0;
							totalizadorReestabelecimentosLocalidade = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());							
							//atualiza o indexe dos beans
							totalBeansPorUnidade++;
							totalBeansPorGerencia++;
						}
						
						//para totalizar as unidades
						if (dadosAnterior == null || dados[2].toString().equals(dadosAnterior[2].toString())){
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}else if (dadosAnterior != null && !dados[2].toString().equals(dadosAnterior[2].toString())){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							// preenche a Unidade
							bean.setUnidadeNegocio(dadosAnterior[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorUnidade, bean);
							//zera os totalizadores
							totalBeansPorUnidade = 0;
							totalizadorReligacoesAntesUnidade = 0;
							totalizadorReligacoesAposUnidade = 0;
							totalizadorSupressoesUnidade = 0;
							totalizadorReestabelecimentosUnidade = 0;
							totalizadorCortadosUnidade = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}
						
						//para totalizar a gerencia
						totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
						totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
						totalizadorSupressoesGerencia += new Integer(dados[6].toString());
						totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
						totalizadorCortadosGerencia += new Integer(dados[8].toString());						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(0,bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorUnidade, bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setLocalidade(dados[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
						}
						
						break;
					case 10:
						//TIPO 10 - Unidade de Neg�cio Espec�fica por Localidade
						totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
						totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
						totalizadorSupressoesUnidade += new Integer(dados[6].toString());
						totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
						totalizadorCortadosUnidade += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							beans.add(0,bean);
						}
						
						//para preencher as Localidades
						if (dadosAnterior == null || dados[3].toString().equals(dadosAnterior[3].toString())){
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						}else{
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setLocalidade(dadosAnterior[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesLocalidade = 0;
							totalizadorReligacoesAposLocalidade = 0;
							totalizadorSupressoesLocalidade = 0;
							totalizadorReestabelecimentosLocalidade = 0;
							totalizadorCortadosLocalidade = 0;
							//totaliza os da pr�xima/atual
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setLocalidade(dados[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
						}
						break;
					default:
						break;
					}
					dadosAnterior = dados;

				}
			}
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_SUPRESSOES_RELIGACOES_REESTABELECIMENTOS,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioSupressoesReligacoesRestabelecimentos", this);
	}

}
