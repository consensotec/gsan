/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de cobrança atividade manter 
 * 
 * @author Nathalia Santos 
 * @created 12 de Julho de 2012
 */
public class RelatorioCobrancaAtividadeManter extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioCobrancaAtividadeManter(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COBRANCA_ATIVIDADE_MANTER);
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
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
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		RelatorioCobrancaAtividadeManterBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade,
				CobrancaAtividade.class.getName());
	
		
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator cobrancaAtividadeIterator = colecaoCobrancaAtividade.iterator();
			
				// laço para criar a coleção de parâmetros da analise
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
								indicadorComando = "NÃO";
						}
					}
					
					String indicadorCronograma = null;
					if (cobrancaAtividade.getIndicadorCronograma () != null){
						if(cobrancaAtividade.getIndicadorCronograma ().equals(ConstantesSistema.SIM)){
							indicadorCronograma = "SIM";
							} else {
								indicadorCronograma = "NÃO";
						}
					}
					
					String indicadorObrigatoriedade = null;
					if (cobrancaAtividade.getIndicadorObrigatoriedade() != null){
						if(cobrancaAtividade.getIndicadorObrigatoriedade().equals(ConstantesSistema.SIM)){
							indicadorObrigatoriedade = "SIM";
							} else {
								indicadorObrigatoriedade = "NÃO";
						}
					}
					
					String indicadorExecucao = null;
					if (cobrancaAtividade.getIndicadorExecucao() != null){
						if(cobrancaAtividade.getIndicadorExecucao().equals(ConstantesSistema.SIM)){
							indicadorExecucao = "SIM";
							} else {
								indicadorExecucao = "NÃO";
						}
					}
					
					
					
					String ordemCronograma = null;
					
					if (cobrancaAtividade.getOrdemRealizacao() != null) {
						ordemCronograma = cobrancaAtividade.getOrdemRealizacao().toString();
					}
					
					
					relatorioBean = new RelatorioCobrancaAtividadeManterBean(
								
						// Descrição
						cobrancaAtividade.getDescricaoCobrancaAtividade(),
						
						// Atividade prodecedora 
						cobrancaAtividadePredecessora,
						
						// Processo Associado
						cobrancaAtividade.getProcesso().getDescricaoProcesso(),
						
						// Pode ser Comandada
						indicadorCronograma,		
						
						// Atividade Obrigatória
						indicadorObrigatoriedade, 
						
						// Pode ser Executada
						indicadorComando,
						
						// Pode ser Executada
						indicadorExecucao,
						
						// Quantidade de dias para execução
						numeroDiasExecucao,
						
						// Ação associada à atividade
						cobrancaAcao,
						
						// Ordem Cronograma
						ordemCronograma
						
						);
						
					// adiciona o bean a coleção
					relatorioBeans.add(relatorioBean);
					}
				}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
			.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_COBRANCA_ATIVIDADE_MANTER, parametros,
						ds, tipoFormatoRelatorio);
		
//			return retorno;
//		}
		

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_COBRANCA_ATIVIDADE_MANTER,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
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