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
package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.geografico.RelatorioManterBairroBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de cobran�a atividade manter 
 * 
 * @author Nathalia Santos 
 * @created 12 de Julho de 2012
 */
public class RelatorioCobrancaAtividadeManter extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioCobrancaAtividadeManter(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COBRANCA_ATIVIDADE_MANTER);
	}

	@Deprecated
	public RelatorioCobrancaAtividadeManter() {
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
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAtividade.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAtividade.PROCESSO);
		filtroCobrancaAtividade.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAtividade.COBRANCA_ACAO);
		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		
		// valor de retorno
		byte[] retorno = null;
		
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();
		
		RelatorioCobrancaAtividadeManterBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade,
				CobrancaAtividade.class.getName());
	
		
		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator cobrancaAtividadeIterator = colecaoCobrancaAtividade.iterator();
			
				// la�o para criar a cole��o de par�metros da analise
					while (cobrancaAtividadeIterator.hasNext()) {
			
					CobrancaAtividade cobrancaAtividade = (CobrancaAtividade) cobrancaAtividadeIterator.next();
					
					String cobrancaAtividadePredecessora = null;
					
					if (cobrancaAtividade.getCobrancaAtividadePredecessora() != null) {
						cobrancaAtividadePredecessora = cobrancaAtividade.getCobrancaAtividadePredecessora().getDescricaoCobrancaAtividade();
					}
					
					String numeroDiasExecucao = null;
					
					if (cobrancaAtividade.getNumeroDiasExecucao() != null) {
						numeroDiasExecucao = cobrancaAtividade.getNumeroDiasExecucao().toString();
					}
					
					String cobrancaAcao = null;
					
					if (cobrancaAtividade.getCobrancaAcao() != null){
						cobrancaAcao = cobrancaAtividade.getCobrancaAcao().getDescricaoCobrancaAcao();
						//}	
					}
					String indicadorComando = null;
					if (cobrancaAtividade.getIndicadorComando() != null){
						if(cobrancaAtividade.getIndicadorComando().equals(ConstantesSistema.SIM)){
							indicadorComando = "SIM";
							} else {
								indicadorComando = "N�O";
						}
					}
					
					String indicadorCronograma = null;
					if (cobrancaAtividade.getIndicadorCronograma () != null){
						if(cobrancaAtividade.getIndicadorCronograma ().equals(ConstantesSistema.SIM)){
							indicadorCronograma = "SIM";
							} else {
								indicadorCronograma = "N�O";
						}
					}
					
					String indicadorObrigatoriedade = null;
					if (cobrancaAtividade.getIndicadorObrigatoriedade() != null){
						if(cobrancaAtividade.getIndicadorObrigatoriedade().equals(ConstantesSistema.SIM)){
							indicadorObrigatoriedade = "SIM";
							} else {
								indicadorObrigatoriedade = "N�O";
						}
					}
					
					String indicadorExecucao = null;
					if (cobrancaAtividade.getIndicadorExecucao() != null){
						if(cobrancaAtividade.getIndicadorExecucao().equals(ConstantesSistema.SIM)){
							indicadorExecucao = "SIM";
							} else {
								indicadorExecucao = "N�O";
						}
					}
					
					
					
					String ordemCronograma = null;
					
					if (cobrancaAtividade.getOrdemRealizacao() != null) {
						ordemCronograma = cobrancaAtividade.getOrdemRealizacao().toString();
					}
					
					
					relatorioBean = new RelatorioCobrancaAtividadeManterBean(
								
						// Descri��o
						cobrancaAtividade.getDescricaoCobrancaAtividade(),
						
						// Atividade prodecedora 
						cobrancaAtividadePredecessora,
						
						// Processo Associado
						cobrancaAtividade.getProcesso().getDescricaoProcesso(),
						
						// Pode ser Comandada
						indicadorCronograma,		
						
						// Atividade Obrigat�ria
						indicadorObrigatoriedade, 
						
						// Pode ser Executada
						indicadorComando,
						
						// Pode ser Executada
						indicadorExecucao,
						
						// Quantidade de dias para execu��o
						numeroDiasExecucao,
						
						// A��o associada � atividade
						cobrancaAcao,
						
						// Ordem Cronograma
						ordemCronograma
						
						);
						
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
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_COBRANCA_ATIVIDADE_MANTER, parametros,
						ds, tipoFormatoRelatorio);
		
//			return retorno;
//		}
		

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_COBRANCA_ATIVIDADE_MANTER,
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
		int retorno = 5;
		//retorno = 
		//retorno = 1000;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroCobrancaAtividade) getParametro("filtroCobrancaAtividade"),
//				CobrancaAtividade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioCobrancaAtividadeManter", this);

	}

}