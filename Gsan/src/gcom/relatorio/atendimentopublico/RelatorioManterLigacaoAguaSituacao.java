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
package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @version 1.0
 */

public class RelatorioManterLigacaoAguaSituacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterLigacaoAguaSituacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_LIGACAO_AGUA_SITUACAO);
	}
	
	@Deprecated
	public RelatorioManterLigacaoAguaSituacao() {
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

	public Object executar() throws TarefaException {

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = (FiltroLigacaoAguaSituacao) getParametro("filtroLigacaoAguaSituacao");
		LigacaoAguaSituacao ligacaoAguaSituacaoParametros = (LigacaoAguaSituacao) getParametro("ligacaoAguaSituacaoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterLigacaoAguaSituacaoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroLigacaoAguaSituacao.setConsultaSemLimites(true);

		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao,
				LigacaoAguaSituacao.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoLigacaoAguaSituacao != null && !colecaoLigacaoAguaSituacao.isEmpty()) {

			// la�o para criar a cole��o de par�metros da analise
			for (LigacaoAguaSituacao ligacaoAguaSituacao : colecaoLigacaoAguaSituacao) {
				
				
				String ativoInativo = "";

				if ( ligacaoAguaSituacao.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
					ativoInativo = "Ativo";
				} else {
					ativoInativo = "Inativo";
				}
								
				String indicadorFaturamento = "";
				
				if (ligacaoAguaSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)) {
					indicadorFaturamento = "SIM";
				} else {
					indicadorFaturamento = "N�O";
				}

				String indicadorExistenciaRede = "";
				
				if (ligacaoAguaSituacao.getIndicadorExistenciaRede().equals(ConstantesSistema.SIM)) {
					indicadorExistenciaRede = "SIM";
				} else {
					indicadorExistenciaRede = "N�O";
				}
				
				String indicadorExistenciaLigacao = "";
				
				if (ligacaoAguaSituacao.getIndicadorExistenciaLigacao().equals(ConstantesSistema.SIM)) {
					indicadorExistenciaLigacao = "SIM";
				} else {
					indicadorExistenciaLigacao = "N�O";
				}
				
				relatorioBean = new RelatorioManterLigacaoAguaSituacaoBean(
						// C�digo
						ligacaoAguaSituacao.getId().toString(), 
						
						// Descri��o
						ligacaoAguaSituacao.getDescricao(), 
						
						// Descri��o Abeviada
						ligacaoAguaSituacao.getDescricaoAbreviado(),
						
						// Consumo Minimo
						(Util.agruparNumeroEmMilhares(ligacaoAguaSituacao.getConsumoMinimoFaturamento())).toString(),
						
						// Indicador Faturamento
						indicadorFaturamento,
						
						// Indicador Existencia Rede
						indicadorExistenciaRede,	
						
						// Indicador Existencia Ligacao
						indicadorExistenciaLigacao,
						
						ligacaoAguaSituacao.getIndicadorAguaAtiva().toString(),
						
						ligacaoAguaSituacao.getIndicadorAguaDesligada().toString(),
						
						ligacaoAguaSituacao.getIndicadorAguaCadastrada().toString(),
						
						ligacaoAguaSituacao.getIndicadorAnalizeAgua().toString(), 
						
						// Indicador de Uso
						ativoInativo);

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

		if (ligacaoAguaSituacaoParametros.getId() != null) {
			parametros.put("id",
					ligacaoAguaSituacaoParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		parametros.put("descricao", ligacaoAguaSituacaoParametros.getDescricao());
		
		parametros.put("descricaoAbreviada", ligacaoAguaSituacaoParametros.getDescricaoAbreviado());
		
		parametros.put("consumoMinimo", ligacaoAguaSituacaoParametros.getConsumoMinimoFaturamento());
		
		String indicadorFaturamentoSituacao = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)) {
			indicadorFaturamentoSituacao = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.NAO)) {
			indicadorFaturamentoSituacao = "N�o";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.TODOS)) {
			indicadorFaturamentoSituacao = "Todos";
		}
		
		parametros.put("indicadorFaturamentoSituacao", indicadorFaturamentoSituacao);
		
		String indicadorExistenciaRede = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaRede().equals(ConstantesSistema.SIM)) {
			indicadorExistenciaRede = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaRede().equals(ConstantesSistema.NAO)) {
			indicadorExistenciaRede = "N�o";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaRede().equals(ConstantesSistema.TODOS)) {
			indicadorExistenciaRede = "Todos";
		}
		
		parametros.put("indicadorExistenciaRede", indicadorExistenciaRede);
		
		
		String indicadorExistenciaLigacao = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaLigacao().equals(ConstantesSistema.SIM)) {
			indicadorExistenciaLigacao = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaLigacao().equals(ConstantesSistema.NAO)) {
			indicadorExistenciaLigacao = "N�o";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaLigacao().equals(ConstantesSistema.TODOS)) {
			indicadorExistenciaLigacao = "Todos";
		}
		
		parametros.put("indicadorExistenciaLigacao", indicadorExistenciaLigacao);
		
		
		String indicadorAguaAtiva = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorAguaAtiva().equals(ConstantesSistema.SIM)) {
			indicadorAguaAtiva = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaAtiva().equals(ConstantesSistema.NAO)) {
			indicadorAguaAtiva = "N�o";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaAtiva().equals(ConstantesSistema.TODOS)) {
			indicadorAguaAtiva = "Todos";
		}
		
		parametros.put("indicadorAguaAtiva", indicadorAguaAtiva);
		
		
		String indicadorAguaDesligada = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorAguaDesligada().equals(ConstantesSistema.SIM)) {
			indicadorAguaDesligada = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaDesligada().equals(ConstantesSistema.NAO)) {
			indicadorAguaDesligada = "N�o";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaDesligada().equals(ConstantesSistema.TODOS)) {
			indicadorAguaDesligada = "Todos";
		}
		
		parametros.put("indicadorAguaDesligada", indicadorAguaDesligada);
		
		
		String indicadorAguaCadastrada = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.SIM)) {
			indicadorAguaCadastrada = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.NAO)) {
			indicadorAguaCadastrada = "N�o";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.TODOS)) {
			indicadorAguaCadastrada = "Todos";
		}
		
		parametros.put("indicadorAguaCadastrada", indicadorAguaCadastrada);
		
		
		String indicadorAnalizeAgua = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.SIM)) {
			indicadorAnalizeAgua = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.NAO)) {
			indicadorAnalizeAgua = "N�o";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.TODOS)) {
			indicadorAnalizeAgua = "Todos";
		}
		
		parametros.put("indicadorAnalizeAgua", indicadorAnalizeAgua);
		

		String indicadorUso = "";

		if (ligacaoAguaSituacaoParametros.getIndicadorUso() != null
				&& !ligacaoAguaSituacaoParametros.getIndicadorUso().equals("")) {
			if (ligacaoAguaSituacaoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (ligacaoAguaSituacaoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_LIGACAO_AGUA_SITUACAO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
//		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterLigacaoAguaSituacao", this);
	}

}