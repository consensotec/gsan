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

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
 * @author not attributable
 * @version 1.0
 */

public class RelatorioRoteiroProgramacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioRoteiroProgramacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ROTEIRO_PROGRAMACAO);
	}
	
	@Deprecated
	public RelatorioRoteiroProgramacao() {
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

		HashMap mapEquipe = (HashMap) getParametro("mapEquipe");
		StringTokenizer nomesEquipes = (StringTokenizer) getParametro("nomesEquipes");
		Date dataRoteiro = (Date) getParametro("dataRoteiro");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioRoteiroProgramacaoBean relatorioBean = null;

		// se a cole��o de par�metros da analise n�o for vazia
		if (nomesEquipes != null) {

			// la�o para criar a cole��o de equipes
			while (nomesEquipes.hasMoreTokens()) {

				String chave = nomesEquipes.nextToken();

				Equipe equipe = (Equipe) mapEquipe.get(chave);

				ObterDadosEquipe obterDadosEquipe = fachada
						.obterDadosEquipe(equipe.getId());

				Collection colecaoComponentesEquipe = obterDadosEquipe
						.getColecaoEquipeComponentes();

				String placa = "";

				if (equipe.getPlacaVeiculo() != null
						&& !equipe.getPlacaVeiculo().trim().equals("")) {

					String letras = equipe.getPlacaVeiculo().substring(0, 3);
					String digitos = equipe.getPlacaVeiculo().substring(3, 7);

					placa = letras + "-" + digitos;

				}

				EquipeComponentes encarregado = null;

				if (colecaoComponentesEquipe != null
						&& !colecaoComponentesEquipe.isEmpty()) {

					Iterator colecaoComponenteEncarregadoIterator = colecaoComponentesEquipe
							.iterator();

					while (colecaoComponenteEncarregadoIterator.hasNext()) {
						encarregado = (EquipeComponentes) colecaoComponenteEncarregadoIterator
								.next();
						if (("" + encarregado.getIndicadorResponsavel())
								.equals(""
										+ EquipeComponentes.INDICADOR_RESPONSAVEL_SIM)) {
							break;
						}
					}

					Iterator colecaoComponentesEquipeIterator = colecaoComponentesEquipe
							.iterator();

					int posicaoComponente = 0;

					while (colecaoComponentesEquipeIterator.hasNext()) {

						EquipeComponentes equipeComponentes = (EquipeComponentes) colecaoComponentesEquipeIterator
								.next();

						posicaoComponente = posicaoComponente + 1;

						if (colecaoComponentesEquipeIterator.hasNext()) {

							EquipeComponentes equipeComponentes2 = (EquipeComponentes) colecaoComponentesEquipeIterator
									.next();

							relatorioBean = new RelatorioRoteiroProgramacaoBean(

							// Unidade Organizacional
									equipe.getUnidadeOrganizacional()
											.getDescricao() == null ? "" : equipe
											.getUnidadeOrganizacional()
											.getDescricao(),

									// Data do Roteiro
									Util.formatarData(dataRoteiro),

									// C�digo da Equipe
									equipe.getId().toString(),

									// Nome da Equipe
									equipe.getNome(),

									// Placa
									placa,

									// Encarregado
									encarregado.getComponentes(),

									// Posi��o do Componente
									"" + posicaoComponente,

									// Nome do Componente
									equipeComponentes.getComponentes(),

									// Posi��o do Componente 2
									"" + (posicaoComponente + 1),

									// Nome do Componente 2
									equipeComponentes2.getComponentes(),

									// Sequencial
									"",

									// N�mero do RA
									"",

									// N�mero da OS
									"",

									// Tipo de Servi�o
									"",

									// Endere�o da Ocorr�ncia
									"",

									// Observa��o
									"");

							// adiciona o bean a cole��o
							relatorioBeans.add(relatorioBean);

							posicaoComponente = posicaoComponente + 1;

						} else {
							relatorioBean = new RelatorioRoteiroProgramacaoBean(

							// Unidade Organizacional
									equipe.getUnidadeOrganizacional()
											.getDescricao() == null ? "" : equipe
											.getUnidadeOrganizacional()
											.getDescricao(),

									// Data do Roteiro
									Util.formatarData(dataRoteiro),

									// C�digo da Equipe
									equipe.getId().toString(),

									// Nome da Equipe
									equipe.getNome(),

									// Placa
									placa,

									// Encarregado
									encarregado.getComponentes(),

									// Posi��o do Componente
									"" + posicaoComponente,

									// Nome do Componente
									equipeComponentes.getComponentes(),

									// Posi��o do Componente 2
									"",

									// Nome do Componente 2
									"",

									// Sequencial
									"",

									// N�mero do RA
									"",

									// N�mero da OS
									"",

									// Tipo de Servi�o
									"",

									// Endere�o da Ocorr�ncia
									"",

									// Observa��o
									"");

							// adiciona o bean a cole��o
							relatorioBeans.add(relatorioBean);
						}

					}

				}

				Collection colecaoOrdensServicoProgramacao = fachada
						.pesquisarOrdemServicoProgramacaoRelatorio(equipe
								.getId(), dataRoteiro);

				if (colecaoOrdensServicoProgramacao != null
						&& !colecaoOrdensServicoProgramacao.isEmpty()) {

					Iterator colecaoOrdensServicoProgramacaoIterator = colecaoOrdensServicoProgramacao
							.iterator();

					while (colecaoOrdensServicoProgramacaoIterator.hasNext()) {
						OrdemServicoProgramacao ordemServicoProgramacao = (OrdemServicoProgramacao) colecaoOrdensServicoProgramacaoIterator
								.next();

						String endereco = "";
						String idRegistroAtendimento = "";

						if (ordemServicoProgramacao.getOrdemServico()
								.getRegistroAtendimento().getId() != null) {
							idRegistroAtendimento = ordemServicoProgramacao
									.getOrdemServico().getRegistroAtendimento()
									.getId().toString();
							endereco = fachada
									.obterEnderecoOcorrenciaRA(ordemServicoProgramacao
											.getOrdemServico()
											.getRegistroAtendimento().getId());
						}

						relatorioBean = new RelatorioRoteiroProgramacaoBean(

								// Unidade Organizacional
								equipe.getUnidadeOrganizacional().getSigla() == null ? ""
										: equipe.getUnidadeOrganizacional()
												.getSigla(),

								// Data do Roteiro
								Util.formatarData(dataRoteiro),

								// C�digo da Equipe
								equipe.getId().toString(),

								// Nome da Equipe
								equipe.getNome(),

								// Placa
								placa,

								// Encarregado
								encarregado == null ? "" : encarregado
										.getComponentes(),

								// Posi��o do Componente
								"",

								// Nome do Componente
								"",

								// Posi��o do Componente 2
								"",

								// Nome do Componente 2
								"",

								// Sequencial
								""
										+ ordemServicoProgramacao
												.getNnSequencialProgramacao(),

								// N�mero do RA
								idRegistroAtendimento,

								// N�mero da OS
								ordemServicoProgramacao.getOrdemServico()
										.getId().toString(),

								// Tipo de Servi�o
								ordemServicoProgramacao.getOrdemServico()
										.getServicoTipo().getId().toString(),

								// Endere�o da Ocorr�ncia
								endereco,

								// Observa��o
								ordemServicoProgramacao.getOrdemServico()
										.getObservacao());

						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioBean);

					}

				}

			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ROTEIRO_PROGRAMACAO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ROTEIRO_PROGRAMACAO,
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

		retorno = ((StringTokenizer) getParametro("nomesEquipes"))
				.countTokens();

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRoteiroProgramacao", this);
	}

}