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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.atendimentopublico.bean.RelatorioManterCustoPavimentoPorRepavimentadoraBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GSAN
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Hugo Leonardo
 * @version 1.0
 */

public class RelatorioManterCustoPavimentoPorRepavimentadora extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioManterCustoPavimentoPorRepavimentadora(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CUSTO_PAVIMENTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterCustoPavimentoPorRepavimentadora() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection<UnidadeRepavimentadoraCustoPavimentoRua> colecaoCustoPavimentoRua = 
			(Collection<UnidadeRepavimentadoraCustoPavimentoRua>) getParametro("colecaoCustoPavimentoRua");
		
		Collection<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoCustoPavimentoCalcada = 
			(Collection<UnidadeRepavimentadoraCustoPavimentoCalcada>) getParametro("colecaoCustoPavimentoCalcada");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String unidadeRepavimentadora = (String) getParametro("unidadeRepavimentadora");
		String tipoPavimentoRua = (String) getParametro("pavimentoRua");
		String vigenciaRua = (String) getParametro("vigenciaRua");
		String situacaoVigenciaRua = (String) getParametro("situacaoVigenciaRua");
		String tipoPavimentoCalcada = (String) getParametro("pavimentoCalcada");
		String vigenciaCalcada = (String) getParametro("vigenciaCalcada");
		String situacaoVigenciaCalcada = (String) getParametro("situacaoVigenciaCalcada");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterCustoPavimentoPorRepavimentadoraBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();
		
		String dtInicial = "";
		
		String dtFinal = "";

		//Rua
		if (colecaoCustoPavimentoRua != null && !colecaoCustoPavimentoRua.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator ruaIterator = colecaoCustoPavimentoRua.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (ruaIterator.hasNext()) {

				UnidadeRepavimentadoraCustoPavimentoRua pavimentoRua = 
					(UnidadeRepavimentadoraCustoPavimentoRua) ruaIterator.next();

				dtInicial = Util.formatarData(pavimentoRua.getDataVigenciaInicial());
				
				dtFinal = Util.formatarData(pavimentoRua.getDataVigenciaFinal());
				
				relatorioBean = new RelatorioManterCustoPavimentoPorRepavimentadoraBean(
						pavimentoRua.getPavimentoRua().getId().toString(), 
						pavimentoRua.getPavimentoRua().getDescricao(), 
						pavimentoRua.getValorPavimento(),
						dtInicial, 
						dtFinal);
				
				// Para os Pavimentos de Rua
				relatorioBean.setTipo("1");

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}
		
		// Cal�ada
		if (colecaoCustoPavimentoCalcada != null && !colecaoCustoPavimentoCalcada.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator calcadaIterator = colecaoCustoPavimentoCalcada.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (calcadaIterator.hasNext()) {

				UnidadeRepavimentadoraCustoPavimentoCalcada pavimentoCalcada = 
					(UnidadeRepavimentadoraCustoPavimentoCalcada) calcadaIterator.next();

				dtInicial = Util.formatarData(pavimentoCalcada.getDataVigenciaInicial());
				
				dtFinal = Util.formatarData(pavimentoCalcada.getDataVigenciaFinal());
				
				relatorioBean = new RelatorioManterCustoPavimentoPorRepavimentadoraBean(
						pavimentoCalcada.getPavimentoCalcada().getId().toString(), 
						pavimentoCalcada.getPavimentoCalcada().getDescricao(), 
						pavimentoCalcada.getValorPavimento(),
						dtInicial, 
						dtFinal);
				
				// Para os Pavimentos de Cal�ada
				relatorioBean.setTipo("2");

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("unidadeRepavimentadora", unidadeRepavimentadora);
		parametros.put("tipoPavimentoRua", tipoPavimentoRua);
		parametros.put("vigenciaRua", vigenciaRua);
		parametros.put("situacaoVigenciaRua", situacaoVigenciaRua);
		parametros.put("tipoPavimentoCalcada", tipoPavimentoCalcada);
		parametros.put("vigenciaCalcada", vigenciaCalcada);
		parametros.put("situacaoVigenciaCalcada", situacaoVigenciaCalcada);

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CUSTO_PAVIMENTO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_CUSTO_PAVIMENTO,
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
		int retorno = 0;

		retorno = 10;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCustoPavimentoPorRepavimentadora", this);
	}

}