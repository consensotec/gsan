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
package gcom.relatorio.financeiro;

import gcom.batch.Relatorio;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioEvolucaoContasAReceberContabil extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioEvolucaoContasAReceberContabil (Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_EVOLUCAO_CONTAS_A_RECEBER_CONTABIL);
	}


//	private Collection<RelatorioEvolucaoContasAReceberContabilBean> inicializarBeanRelatorio(
//			Collection listaCamposConsultaRelatorio) {
//
//		Iterator iterator = listaCamposConsultaRelatorio.iterator();
//
//		return retorno;
//	}

	
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		Integer codigoMunicipio = (Integer) getParametro("municipio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioEvolucaoContasAReceberContabilBean> colecaoBean = fachada
				.consultarDadosEvolucaoContasAReceberContabilRelatorio(opcaoTotalizacao, mesAno,
						codigoGerencia, codigoLocalidade, codigoMunicipio, unidadeNegocio);

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
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio")){
			opcaoTotalizacaoDesc = "Estado por Munic�pio";
		} else if (opcaoTotalizacao.equalsIgnoreCase("gerenciaRegional")) {
			GerenciaRegional gerencia = pesquisarGerenciaRegional(codigoGerencia);
			if (gerencia != null){
				opcaoTotalizacaoDesc = "Ger�ncia Regional (" + gerencia.getNome() + ")";	
			} else {
				opcaoTotalizacaoDesc = "Ger�ncia Regional";
			}						
		} else if (opcaoTotalizacao
				.equalsIgnoreCase("gerenciaRegionalLocalidade")) {
			GerenciaRegional gerencia = pesquisarGerenciaRegional(codigoGerencia);
			if (gerencia != null){
				opcaoTotalizacaoDesc = "Ger�ncia Regional (" + gerencia.getNome() + ") por Localidade";	
			} else {
				opcaoTotalizacaoDesc = "Ger�ncia Regional por Localidade";
			}						
		} else if (opcaoTotalizacao
				.equalsIgnoreCase("gerenciaRegionalUnidadeNegocio")) {
			GerenciaRegional gerencia = pesquisarGerenciaRegional(codigoGerencia);
			if (gerencia != null){
				opcaoTotalizacaoDesc = "Ger�ncia Regional (" + gerencia.getNome() + ") por Unidade de Neg�cio";	
			} else {
				opcaoTotalizacaoDesc = "Ger�ncia Regional por Unidade de Neg�cio";
			}						
		} else if (opcaoTotalizacao.equalsIgnoreCase("localidade")) {
			Localidade localidade = pesquisarLocalidade(codigoLocalidade);
			if (localidade != null){
				opcaoTotalizacaoDesc = "Localidade (" + localidade.getDescricao() + ") ";	
			} else {
				opcaoTotalizacaoDesc = "Localidade";
			}						
		} else if (opcaoTotalizacao.equalsIgnoreCase("municipio")) {
			Municipio municipio = pesquisarMunicipio(codigoMunicipio);
			if (municipio != null){
				opcaoTotalizacaoDesc = "Munic�pio (" + municipio.getNome() + ") ";	
			} else {
				opcaoTotalizacaoDesc = "Munic�pio";
			}						
		} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
			opcaoTotalizacaoDesc = "Estado por Unidade de Neg�cio";
		} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
			UnidadeNegocio unidNegocio = pesquisarUnidadeNegocio(unidadeNegocio);
			if (unidNegocio != null){
				opcaoTotalizacaoDesc = "Unidade de Neg�cio (" + unidNegocio.getNome() + ") ";	
			} else {
				opcaoTotalizacaoDesc = "Unidade de Neg�cio";
			}									
		} else if (opcaoTotalizacao.equals("unidadeNegocioLocalidade")) {
			UnidadeNegocio unidNegocio = pesquisarUnidadeNegocio(unidadeNegocio);
			if (unidNegocio != null){
				opcaoTotalizacaoDesc = "Unidade de Neg�cio (" + unidNegocio.getNome() + ") por Localidade";	
			} else {
				opcaoTotalizacaoDesc = "Unidade de Neg�cio por Localidade";
			}									
			
		}

		parametros.put("opcaoTotalizacaoDesc", opcaoTotalizacaoDesc);

		String mesAnoString = "" + mesAno;
		if (mesAnoString.length() == 5) {
			mesAnoString = "0" + mesAnoString;
		}
		mesAnoString = mesAnoString.substring(0, 2) + "/"
				+ mesAnoString.substring(2, 6);

		parametros.put("mesAnoReferencia", mesAnoString);

		parametros.put("tipoFormatoRelatorio", "R0718");

		if (opcaoTotalizacao.equalsIgnoreCase("unidadeNegocio") || opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio")) {
			parametros.put("agrupaPorUnidadeNegocio", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			parametros.put("agrupaPorGerencia", "1");
		} else {
			parametros.put("agrupaPorGerencia", "0");
		}
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_EVOLUCAO_CONTAS_A_RECEBER_CONTABIL, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.EVOLUCAO_CONTAS_A_RECEBER_CONTABIL,
				idFuncionalidadeIniciada);
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
//		Fachada fachada = Fachada.getInstancia();
//		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
//		int mesAno = (Integer) getParametro("mesAnoInteger");
//		Integer idLocalidade = (Integer) getParametro("localidade");
//		Integer idGerencia = (Integer) getParametro("gerenciaRegional");
//
//		Integer totalRegistrosRel;
//		totalRegistrosRel = fachada
//				.consultarQtdeRegistrosResumoFaturamentoRelatorio(mesAno, idLocalidade, 
//			    		idGerencia, opcaoTotalizacao);
		return 1;//totalRegistrosRel.intValue();
	}


	public void agendarTarefaBatch() {
	}
	
	private GerenciaRegional pesquisarGerenciaRegional(Integer idGerencia) {

		if (idGerencia == null){
			return null;
		}
		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
		filtroGerencia.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.ID, idGerencia));

		Collection<GerenciaRegional> gerenciaPesquisada = fachada.pesquisar(
				filtroGerencia, GerenciaRegional.class.getName());

		if (gerenciaPesquisada == null || gerenciaPesquisada.isEmpty()) {
			// a localidade n�o foi encontrada
			throw new ActionServletException("atencao.gerenciaregional.inexistente");
		}
		return (GerenciaRegional) Util.retonarObjetoDeColecao(gerenciaPesquisada);
	}

	private Localidade pesquisarLocalidade(Integer idLocalidade) {

		if (idLocalidade == null){
			return null;
		}

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
			// a localidade n�o foi encontrada
			throw new ActionServletException("atencao.localidade.inexistente");
		}
		return (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
	}
	
	private Municipio pesquisarMunicipio(Integer idMunicipio) {

		if (idMunicipio == null){
			return null;
		}

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, idMunicipio));

		Collection<Municipio> municipioPesquisado = fachada.pesquisar(
				filtroMunicipio, Municipio.class.getName());

		if (municipioPesquisado == null || municipioPesquisado.isEmpty()) {
			// a localidade n�o foi encontrada
			throw new ActionServletException("atencao.localidade.inexistente");
		}
		return (Municipio) Util.retonarObjetoDeColecao(municipioPesquisado);
	}
	
	private UnidadeNegocio pesquisarUnidadeNegocio(Integer idUnidadeNegocio) {

		if (idUnidadeNegocio == null){
			return null;
		}

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroUnidadeNegocio filtroUN = new FiltroUnidadeNegocio();
		filtroUN.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.ID, idUnidadeNegocio));

		Collection<UnidadeNegocio> UNPesquisada = fachada.pesquisar(
				filtroUN, UnidadeNegocio.class.getName());

		if (UNPesquisada == null || UNPesquisada.isEmpty()) {
			// a localidade n�o foi encontrada
			throw new ActionServletException("atencao.unidadenegocio.inexistente");
		}
		return (UnidadeNegocio) Util.retonarObjetoDeColecao(UNPesquisada);
	}	
}
