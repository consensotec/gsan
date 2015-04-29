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
package gsan.relatorio.financeiro;

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.financeiro.FiltroParametrosPerdasSocietarias;
import gsan.financeiro.FiltroPerdasTipo;
import gsan.financeiro.ParametrosPerdasSocietarias;
import gsan.financeiro.PerdasTipo;
import gsan.financeiro.bean.ResumoDevedoresDuvidososRelatorioHelper;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
 * 
 * @author Vivianne Sousa
 * @created 20/07/2007
 */
public class RelatorioResumoDevedoresDuvidosos extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoDevedoresDuvidosos (Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_RESUMO_DEVEDORES_DUVIDOSOS);
	}


	private Collection<RelatorioResumoDevedoresDuvidososBean> inicializarBeanRelatorio(
			Collection listaCamposConsultaRelatorio) {

		Iterator iterator = listaCamposConsultaRelatorio.iterator();

		Collection<RelatorioResumoDevedoresDuvidososBean> retorno = new ArrayList();

		while (iterator.hasNext()) {
			ResumoDevedoresDuvidososRelatorioHelper resumoDevedoresDuvidososRelatorioHelper = 
				(ResumoDevedoresDuvidososRelatorioHelper) iterator.next();
			
			RelatorioResumoDevedoresDuvidososBean relatorioResumoDevedoresDuvidososBean = 
				new RelatorioResumoDevedoresDuvidososBean(
						resumoDevedoresDuvidososRelatorioHelper.getValorItemDevedoresDuvidosos(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoTipoLancamento(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoItemLancamento(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoItemContabil(),
						resumoDevedoresDuvidososRelatorioHelper.getIndicadorImpressao(),
						resumoDevedoresDuvidososRelatorioHelper.getIndicadorTotal(),
						resumoDevedoresDuvidososRelatorioHelper.getLancamentoTipo(),
						resumoDevedoresDuvidososRelatorioHelper.getLancamentoTipoSuperior(),
						false,
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoGerencia(),
						resumoDevedoresDuvidososRelatorioHelper.getGerencia(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoLocalidade(),
						resumoDevedoresDuvidososRelatorioHelper.getLocalidade(),
						resumoDevedoresDuvidososRelatorioHelper.getDescLancamentoTipoSuperior(),
						resumoDevedoresDuvidososRelatorioHelper.getDescricaoUnidadeNegocio(),
						resumoDevedoresDuvidososRelatorioHelper.getUnidadeNegocio());

			retorno.add(relatorioResumoDevedoresDuvidososBean);
			
			
		}		


		return retorno;
	}

	
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		Integer tipoPerda = (Integer) getParametro("tipoPerda");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		BigDecimal totalResidencial = new BigDecimal("0");
		BigDecimal totalComercial = new BigDecimal("0");
		BigDecimal totalIndustrial = new BigDecimal("0");
		BigDecimal totalParticulares = new BigDecimal("0");
		BigDecimal totalPublico = new BigDecimal("0");
		BigDecimal totalGeral = new BigDecimal("0");

		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection dadosRelatorio = fachada
				.consultarResumoDevedoresDuvidososRelatorio(opcaoTotalizacao, mesAno,
						codigoGerencia, codigoLocalidade, unidadeNegocio, tipoPerda);

		Collection<RelatorioResumoDevedoresDuvidososBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		Integer lancamentoTipoAnterior = null;
		
		for (Iterator<RelatorioResumoDevedoresDuvidososBean> iter = colecaoBean
				.iterator(); iter.hasNext();) {
			RelatorioResumoDevedoresDuvidososBean bean = iter.next();

			// Se o tipo de lan�amento for subordinado a outro tipo de
			// lan�amento
			if (!bean.getLancamentoTipo().equals(bean.getLancamentoTipoSuperior())) {

				// Recua a descri��o neste caso
				bean.setDescricaoTipoLancamento("    "	+ bean.getDescricaoTipoLancamento());

				bean.setDescricaoItemLancamento("    " 	+ bean.getDescricaoItemLancamento());

				if (bean.getDescricaoItemContabil() != null){
					bean.setDescricaoItemContabil("    "  + bean.getDescricaoItemContabil());	
				}
 
				if (lancamentoTipoAnterior != null && !lancamentoTipoAnterior.equals(bean.getLancamentoTipoSuperior())){
					String descricaoLancamentoTipoSuperior = fachada.obterDescricaoLancamentoTipo(bean.getLancamentoTipoSuperior());
					bean.setDescLancamentoTipoSuperior(descricaoLancamentoTipoSuperior);
				}				
			}
			
			lancamentoTipoAnterior = bean.getLancamentoTipo();
			
			// Se o indicador impress�o n�o estiver setado ent�o a linha de
			// detalhe n�o aparecer� no relat�rio
			if (bean.getIndicadorImpressao() == null || !bean.getIndicadorImpressao().toString().equals("1")) {
				bean.setDescricaoTipoLancamento("");
				//iter.remove();
				continue;
			}
			
			if(opcaoTotalizacao.equals("estado") || opcaoTotalizacao.equals("unidadeNegocio") || opcaoTotalizacao.equals("localidade")
					|| opcaoTotalizacao.equals("gerenciaRegional")){
				//calcular totais
				//caso o tipo superior seja AGUA, Esgoto, PARCELAMENTOS COBRADOS ou FINANCIAMENTOS COBRADOS soma ou valores
				//caso seja DEVOLU��O DE VALORES EM CONTA subitrai
				if(bean.getLancamentoTipoSuperior().equals(1) || 
						bean.getLancamentoTipoSuperior().equals(2) || bean.getLancamentoTipoSuperior().equals(20) 
						|| bean.getLancamentoTipoSuperior().equals(13)){				
					totalResidencial  = totalResidencial.add(bean.getValorItemDevedoresDuvidosos1());
					totalComercial 	  = totalComercial.add(bean.getValorItemDevedoresDuvidosos2());
					totalIndustrial   = totalIndustrial.add(bean.getValorItemDevedoresDuvidosos3());
					totalParticulares = totalParticulares.add(bean.getValorItemDevedoresDuvidosos4());
					totalPublico	  = totalPublico.add(bean.getValorItemDevedoresDuvidosos5());
				}else if(bean.getLancamentoTipoSuperior().equals(23)){
					totalResidencial  = totalResidencial.subtract(bean.getValorItemDevedoresDuvidosos1());
					totalComercial 	  = totalComercial.subtract(bean.getValorItemDevedoresDuvidosos2());
					totalIndustrial   = totalIndustrial.subtract(bean.getValorItemDevedoresDuvidosos3());
					totalParticulares = totalParticulares.subtract(bean.getValorItemDevedoresDuvidosos4());
					totalPublico	  = totalPublico.subtract(bean.getValorItemDevedoresDuvidosos5());
				}
			}
		}
		
		if(opcaoTotalizacao.equals("estado") || opcaoTotalizacao.equals("unidadeNegocio") || opcaoTotalizacao.equals("localidade")
				|| opcaoTotalizacao.equals("gerenciaRegional")){
			totalGeral = totalResidencial.add(totalComercial).add(totalIndustrial).add(totalPublico);
			
			parametros.put("totalResidencial", totalResidencial);
			parametros.put("totalComercial", totalComercial);
			parametros.put("totalIndustrial", totalIndustrial);
			parametros.put("totalParticulares", totalParticulares);
			parametros.put("totalPublico", totalPublico);
			parametros.put("totalGeral", totalGeral);
		}else{
			parametros.put("totalGeral", null);
		}
		

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		String opcaoTotalizacaoDesc = "";
		if (opcaoTotalizacao.equalsIgnoreCase("estado")) {
			opcaoTotalizacaoDesc = "Estado";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			opcaoTotalizacaoDesc = "Estado por Ger�ncia Regional";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")) {
			opcaoTotalizacaoDesc = "Estado por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("gerenciaRegional")) {
			opcaoTotalizacaoDesc = "Ger�ncia Regional";
		} else if (opcaoTotalizacao.equalsIgnoreCase("gerenciaRegionalLocalidade")) {
			opcaoTotalizacaoDesc = "Ger�ncia Regional por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("localidade")) {
			opcaoTotalizacaoDesc = "Localidade";
		} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
			opcaoTotalizacaoDesc = "Estado por Unidade de Neg�cio";
		} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
			opcaoTotalizacaoDesc = "Unidade de Neg�cio";
		}

		parametros.put("opcaoTotalizacaoDesc", opcaoTotalizacaoDesc);

		String mesAnoString = "" + mesAno;
		if (mesAnoString.length() == 5) {
			mesAnoString = "0" + mesAnoString;
		}
		mesAnoString = mesAnoString.substring(0, 2) + "/"
				+ mesAnoString.substring(2, 6);

		parametros.put("mesAnoArrecadacao", mesAnoString);

		parametros.put("tipoFormatoRelatorio", "");

		if (opcaoTotalizacao.equalsIgnoreCase("unidadeNegocio") || opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio")) {
			parametros.put("agrupaPorUnidadeNegocio", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			parametros.put("agrupaPorGerencia", "1");
		} else {
			parametros.put("agrupaPorGerencia", "0");
		}
		
		if(tipoPerda != 2){
			FiltroPerdasTipo filtroPerdasTipo = new FiltroPerdasTipo();
			filtroPerdasTipo.adicionarParametro(new ParametroSimples(FiltroPerdasTipo.ID, tipoPerda));
			
			Collection colPerdasTipo = fachada.pesquisar(filtroPerdasTipo, PerdasTipo.class.getName());
			
			if(!Util.isVazioOrNulo(colPerdasTipo)){
				PerdasTipo perdasTipo = (PerdasTipo) Util.retonarObjetoDeColecao(colPerdasTipo);
				parametros.put("tipoPerda", perdasTipo.getDescricao());
			}
		}else{
			String tipoGeracao = "";
			Integer anoMes = Util.formatarMesAnoParaAnoMes(mesAno);
			FiltroParametrosPerdasSocietarias filtroPerdas = new FiltroParametrosPerdasSocietarias();
			filtroPerdas.adicionarParametro(new ParametroSimples(FiltroParametrosPerdasSocietarias.ANO_MES_REFERENCIA, String.valueOf(anoMes)));
			
			Collection<ParametrosPerdasSocietarias> colPerdas = fachada.pesquisar(filtroPerdas, ParametrosPerdasSocietarias.class.getName());
			
			if(!Util.isVazioOrNulo(colPerdas)){
				ParametrosPerdasSocietarias perdas = (ParametrosPerdasSocietarias) Util.retonarObjetoDeColecao(colPerdas);
				if(perdas.getIndicadorGeracaoReal() == 1){
					tipoGeracao = " (Tipo Gera��o: REAL) ";
				}else if(perdas.getIndicadorGeracaoReal() == 2){
					tipoGeracao = " (Tipo Gera��o: SIMULA��O) ";
				}
			}
				
			parametros.put("tipoPerda", "Provis�o Perdas Societarias " + tipoGeracao);
		}
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_DEVEDORES_DUVIDOSOS, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RESUMO_DEVEDORES_DUVIDOSOS,idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {

//		totalRegistrosRel = fachada
//				.consultarQtdeRegistrosResumoDevedoresDuvidososRelatorio(mesAno, idLocalidade, 
//			    		idGerencia, opcaoTotalizacao);
//		return totalRegistrosRel.intValue();
		return 1;
	}


	public void agendarTarefaBatch() {
	}
}
