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
 * R�mulo Aur�lio de Melo Souza Filho
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

package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
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
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio
 * 
 * @author R�mulo Aur�lio
 * @created 08/01/2009
 */
public class RelatorioPagamentosContasCobrancaEmpresa extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioPagamentosContasCobrancaEmpresa(Usuario usuario) {
		super(
				usuario,
				ConstantesRelatorios.RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA);
	}

	@Deprecated
	public RelatorioPagamentosContasCobrancaEmpresa() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Empresa empresa = (Empresa) getParametro("empresa");
		int referenciaPagamentoInicial = (Integer) getParametro("referenciaPagamentoInicial");
		int referenciaPagamentoFinal = (Integer) getParametro("referenciaPagamentoFinal");
		@SuppressWarnings("unused")
		String opcaoEmissao = (String) getParametro("opcaoRelatorio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		
		
		RelatorioPagamentosContasCobrancaEmpresaHelper helper = 
			new RelatorioPagamentosContasCobrancaEmpresaHelper(empresa,
					referenciaPagamentoInicial,  referenciaPagamentoFinal, 
					opcaoTotalizacao, codigoLocalidade, codigoGerencia,unidadeNegocio);
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		

		Collection colecaoRelatorioPagamentosContasCobrancaEmpresaBean = fachada
				.pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(
						helper);

		if (colecaoRelatorioPagamentosContasCobrancaEmpresaBean != null
				&& !colecaoRelatorioPagamentosContasCobrancaEmpresaBean
						.isEmpty()) {

			relatorioBeans
					.addAll(colecaoRelatorioPagamentosContasCobrancaEmpresaBean);
		}
		else{
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (empresa.getId() != null) {
			parametros.put("idEmpresa", empresa.getDescricao());
			parametros.put("nomeEmpresa", empresa.getDescricao());
		} else {
			parametros.put("empresa", "");
		}
		
		String opcaoQuebra = "";
		
		String opcaoQuebra2 = "";
		
		
		if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoGerencia")) {
			
			opcaoQuebra = "Ger�ncia Regional";
		
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoLocalidade")) {
			
			opcaoQuebra = "Localidade";
		
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegional")) {
			
			opcaoQuebra = "Ger�ncia Regional";
		
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegionalLocalidade")) {
			
			opcaoQuebra = "Ger�ncia Regional";
			opcaoQuebra2 = "Localidade";
		
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("localidade")) {
			
			opcaoQuebra = "Localidade";
		
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoUnidadeNegocio")) {
			
			opcaoQuebra = "Unidade Neg�cio";
		
		}else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("unidadeNegocio")) {
			
			opcaoQuebra = "Unidade Neg�cio";
		
		}else {
			opcaoQuebra = "Estado";
		}
		

		parametros.put("opcaoQuebra", opcaoQuebra);
		parametros.put("opcaoQuebra2",opcaoQuebra2);
		parametros.put("periodoPagamentoInicial", Util
				.formatarAnoMesParaMesAno(referenciaPagamentoInicial));

		parametros.put("periodoPagamentoFinal", Util
				.formatarAnoMesParaMesAno(referenciaPagamentoFinal));

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if (opcaoEmissao.equals("1")) {
			parametros.put("visaoRelatorio", "SINTETICO");
		} else {
			parametros.put("visaoRelatorio", "ANALITICO");
		}
		
		
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException(
					"Erro ao gravar relat�rio no sistema", e);
		}
		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		Empresa empresa = (Empresa) getParametro("empresa");
		int referenciaPagamentoInicial = (Integer) getParametro("referenciaPagamentoInicial");
		int referenciaPagamentoFinal = (Integer) getParametro("referenciaPagamentoFinal");

		retorno = Fachada
				.getInstancia()
				.pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(
						empresa.getId(), referenciaPagamentoInicial,
						referenciaPagamentoFinal);

		if (retorno == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao
			// usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPagamentosContasCobranca",
				this);
	}
}