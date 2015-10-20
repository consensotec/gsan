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
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0726] Gerar Relat�rio das Contas Baixadas Contabilmente
 * 
 * @author Vivianne Sousa
 * @data 08/04/2008
 */

public class RelatorioContasBaixadasContabilmente extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioContasBaixadasContabilmente(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE);
	}

	public Collection<RelatorioContasBaixadasContabilmenteBean> inicializarBeanRelatorio(
			Integer referenciaInicial, Integer referenciaFinal,
			Short periodicidade) throws ControladorException {

		Collection<RelatorioContasBaixadasContabilmenteBean> colecaoBean = new ArrayList();

		BigDecimal valorTotalFaixaUm = BigDecimal.ZERO;

		BigDecimal valorTotalFaixaDois = BigDecimal.ZERO;

		BigDecimal valorTotalFaixaTres = BigDecimal.ZERO;

		BigDecimal valorTotal = BigDecimal.ZERO;

		BigDecimal valorDoacoes = Fachada.getInstancia()
				.consultarSomatorioValorDoacoesContasBaixadasContabilmente(
						referenciaInicial, referenciaFinal, periodicidade);

		// Preencher valor Setor Um
		Map faixaUmSetor = Fachada.getInstancia()
				.consultarSomatorioValorContasBaixadasContabilmenteFaixa(
						referenciaInicial, referenciaFinal,
						ConstantesSistema.FAIXA_1, periodicidade);

		valorTotalFaixaUm = (BigDecimal) faixaUmSetor.get("somatorio");

		// Preencher valor Setor Dois

		Map faixaDoisSetor = Fachada.getInstancia()
				.consultarSomatorioValorContasBaixadasContabilmenteFaixa(
						referenciaInicial, referenciaFinal,
						ConstantesSistema.FAIXA_2, periodicidade);

		valorTotalFaixaDois = (BigDecimal) faixaDoisSetor.get("somatorio");

		// Preencher valor Setor Tres

		Map faixaTresSetor = Fachada.getInstancia()
				.consultarSomatorioValorContasBaixadasContabilmenteFaixa(
						referenciaInicial, referenciaFinal,
						ConstantesSistema.FAIXA_3, periodicidade);

		valorTotalFaixaTres = (BigDecimal) faixaTresSetor.get("somatorio");

		valorTotal = valorTotal.add(valorTotalFaixaUm).add(valorTotalFaixaDois)
				.add(valorTotalFaixaTres);
		
		if (valorDoacoes != null) {
			valorTotalFaixaUm = valorTotalFaixaUm.subtract(valorDoacoes);
			valorTotal = valorTotal.subtract(valorDoacoes);
		}

		RelatorioContasBaixadasContabilmenteBean bean = new RelatorioContasBaixadasContabilmenteBean(
				valorTotalFaixaUm, valorTotalFaixaDois, valorTotalFaixaTres,
				valorTotal);

		colecaoBean.add(bean);

		return colecaoBean;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Short tipo = (Short) getParametro("tipo");
		Short periodicidade = (Short) getParametro("periodicidade");
		Integer referenciaInicial = (Integer) getParametro("referenciaInicial");
		Integer referenciaFinal = (Integer) getParametro("referenciaFinal");
		Integer tipoRelatorio = TarefaRelatorio.TIPO_PDF;

		String referenciaIni = Util.formatarAnoMesParaMesAno(referenciaInicial);
		String referenciaFin = Util.formatarAnoMesParaMesAno(referenciaFinal);

		String tipoDeRelatorio = "";
		if (tipo == 1) {
			tipoDeRelatorio = "Anal�tico";
		} else if (tipo == 2) {
			tipoDeRelatorio = "Sint�tico";
		}

		String periodicidadeParametros = "";
		if (periodicidade == 1) {
			periodicidadeParametros = "Mensal";
		} else if (periodicidade == 2) {
			periodicidadeParametros = "Acumulado";
		}

		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioContasBaixadasContabilmenteBean> colecaoBean = null;
		try {
			colecaoBean = this.inicializarBeanRelatorio(referenciaInicial,
					referenciaFinal, periodicidade);

		} catch (ControladorException e1) {
			e1.printStackTrace();
		}

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("periodicidade", periodicidadeParametros);

		if (periodicidade != 2) {
			parametros.put("referenciaInicial", referenciaIni);
		}

		parametros.put("referencialFinal", referenciaFin);

		parametros.put("tipo", tipoDeRelatorio);

		parametros.put("relatorio", "R0726");

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE,
				parametros, ds, tipoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.CONTAS_BAIXADAS_CONTABILMENTE,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
		// return null;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasBaixadasContabilmente",
				this);
	}
}
