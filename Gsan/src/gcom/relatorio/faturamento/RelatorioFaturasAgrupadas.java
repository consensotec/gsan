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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
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
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author Fernanda Paiva
 * @created 11 de Julho de 2005
 */
public class RelatorioFaturasAgrupadas extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioFaturasAgrupadas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS);
	}

	@Deprecated
	public RelatorioFaturasAgrupadas() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer anoMes = (Integer) getParametro("anoMes");
		Cliente cliente = (Cliente) getParametro("cliente");
		Collection<Integer> idsClientes = (Collection<Integer>) getParametro("idsClientes");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		
		Integer qtdFaturasSelecionadas = fachada
		.pesquisarDadosRelatorioFaturasAgrupadasCount(anoMes, cliente, idsClientes);
		
		if(qtdFaturasSelecionadas == null || qtdFaturasSelecionadas.intValue() == 0){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,"");
		}
		
		Collection colecaoFaturasAgrupadasRelatorio = fachada
		.pesquisarDadosRelatorioFaturasAgrupadas(anoMes, cliente,
				idsClientes);
		
		Iterator iColecaoRelatorioFaturasAgrupadas = colecaoFaturasAgrupadasRelatorio.iterator();
		
		Collection colecaoRelatorioFaturasAgrupadas = new ArrayList();
		
		while (iColecaoRelatorioFaturasAgrupadas.hasNext()){
			
			RelatorioFaturasAgrupadasBean faturaItem = (RelatorioFaturasAgrupadasBean) iColecaoRelatorioFaturasAgrupadas.next();
			
			if (faturaItem.getIndicadorContaHist() == 2){
				
				colecaoRelatorioFaturasAgrupadas.add(faturaItem);
				
			} else if (faturaItem.getIndicadorContaHist() == 1){
				
				fachada.atualizarIndicadorContaHistorico(faturaItem.getIdFaturaItem());
			}
			
		}

//		relatorioBeans.addAll(colecaoFaturasAgrupadasRelatorio);
		relatorioBeans.addAll(colecaoRelatorioFaturasAgrupadas);
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		FiltroBairro filtroBairro = new FiltroBairro();
		filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID,
				sistemaParametro.getBairro().getId()));

		Collection colecaoBairros = fachada.pesquisar(filtroBairro,
				Bairro.class.getName());

		Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairros);

		String endereco = formatarEndereco(sistemaParametro, bairro);

		String telefone = "(" + bairro.getMunicipio().getDdd() + ") "
				+ sistemaParametro.getNumeroTelefone();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("nomeAbreviadoEmpresa", sistemaParametro
				.getNomeAbreviadoEmpresa());

		parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());

		parametros.put("enderecoEmpresa", endereco);

		parametros.put("cgc", Util.formatarCnpj(sistemaParametro
				.getCnpjEmpresa()));

		parametros.put("inscricaoEstadual", sistemaParametro
				.getInscricaoEstadual());

		parametros.put("telefoneEmpresa", telefone);

		parametros.put("telefoneContato", sistemaParametro
				.getNumero0800Empresa());

		BigDecimal percentualAliquota = fachada.pesquisarPercentualAliquota();

		parametros.put("percentualRetencao", percentualAliquota);

		String indicadorTotalizador = (String) getParametro("indicadorTotalizador");

		parametros.put("indicadorTotalizador", indicadorTotalizador);

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if (sistemaParametro.getCodigoEmpresaFebraban().equals(
				SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)) {

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS_COSANPA,
					parametros, ds, tipoFormatoRelatorio);

		} else if (indicadorTotalizador != null
				&& indicadorTotalizador.equalsIgnoreCase("2")) {

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS_SINTETICO,
					parametros, ds, tipoFormatoRelatorio);
		} else {

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_FATURAS_AGRUPADAS,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	private String formatarEndereco(SistemaParametro sistemaParametro,
			Bairro bairro) {
		String retorno = "";

		Fachada fachada = Fachada.getInstancia();

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro
				.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro
				.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID, sistemaParametro.getLogradouro().getId()));

		Collection colecaoLogradouros = fachada.pesquisar(filtroLogradouro,
				Logradouro.class.getName());

		Logradouro logradouro = (Logradouro) Util
				.retonarObjetoDeColecao(colecaoLogradouros);

		retorno = logradouro.getDescricaoFormatada();

		if (sistemaParametro.getNumeroImovel() != null) {
			retorno = retorno + ", " + sistemaParametro.getNumeroImovel();
		}

		retorno = retorno + " - " + bairro.getNome();

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {

		Fachada fachada = Fachada.getInstancia();

		Integer anoMes = (Integer) getParametro("anoMes");
		Cliente cliente = (Cliente) getParametro("cliente");
		Collection<Integer> idsClientes = (Collection<Integer>) getParametro("idsClientes");

		return fachada.pesquisarDadosRelatorioFaturasAgrupadasCount(anoMes,
				cliente, idsClientes);

	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFaturasAgrupadas", this);
	}

}