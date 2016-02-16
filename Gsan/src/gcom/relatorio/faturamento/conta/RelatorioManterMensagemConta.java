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
package gcom.relatorio.faturamento.conta;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroContaMensagem;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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

public class RelatorioManterMensagemConta extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterMensagemConta(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MENSAGEM_CONTA_MANTER);
	}

	@Deprecated
	public RelatorioManterMensagemConta() {
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
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroContaMensagem filtroContaMensagem = (FiltroContaMensagem) getParametro("filtroContaMensagem");
		ContaMensagem contaMensagemParametros = (ContaMensagem) getParametro("contaMensagemParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterMensagemContaBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroContaMensagem.limparCamposOrderBy();
		// filtroContaMensagem
		// .setCampoOrderBy(FiltroContaMensagem.ANO_MES_REFERECIA_FATURAMENTO
		// ,FiltroContaMensagem.GRUPO_FATURAMENTO_ID,
		// FiltroContaMensagem.GERENCIA_REGIONAL_ID,
		// FiltroContaMensagem.LOCALIDADE_ID,
		// FiltroContaMensagem.SETOR_COMERCIAL_CD
		// );
		filtroContaMensagem.setConsultaSemLimites(true);
        filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade( "setorComercial" );

		Collection colecaoContaMensagem = fachada.pesquisar(
				filtroContaMensagem, ContaMensagem.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoContaMensagem != null && !colecaoContaMensagem.isEmpty()) {

			// Organiza a cole��o
			Collections.sort((List) colecaoContaMensagem, new Comparator() {
				public int compare(Object a, Object b) {
					String posicao1 = ((ContaMensagem) a)
							.getAnoMesRreferenciaFaturamento().toString()
							+ (((ContaMensagem) a).getFaturamentoGrupo() == null ? " "
									: ((ContaMensagem) a).getFaturamentoGrupo()
											.getId())
							+ (((ContaMensagem) a).getGerenciaRegional() == null ? " "
									: ((ContaMensagem) a).getGerenciaRegional()
											.getId())
							+ (((ContaMensagem) a).getLocalidade() == null ? " "
									: ((ContaMensagem) a).getLocalidade()
											.getId())
							+ (((ContaMensagem) a).getSetorComercial() == null ? " "
									: ((ContaMensagem) a).getSetorComercial()
											.getCodigo());
					String posicao2 = ((ContaMensagem) b)
							.getAnoMesRreferenciaFaturamento().toString()
							+ (((ContaMensagem) b).getFaturamentoGrupo() == null ? " "
									: ((ContaMensagem) b).getFaturamentoGrupo()
											.getId())
							+ (((ContaMensagem) b).getGerenciaRegional() == null ? " "
									: ((ContaMensagem) b).getGerenciaRegional()
											.getId())
							+ (((ContaMensagem) b).getLocalidade() == null ? " "
									: ((ContaMensagem) b).getLocalidade()
											.getId())
							+ (((ContaMensagem) b).getSetorComercial() == null ? " "
									: ((ContaMensagem) b).getSetorComercial()
											.getCodigo());
					

					return posicao1.compareTo(posicao2);
				}
			});

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoContaMensagemIterator = colecaoContaMensagem
					.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoContaMensagemIterator.hasNext()) {

				ContaMensagem contaMensagem = (ContaMensagem) colecaoContaMensagemIterator
						.next();

				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio

				// Grupo de Faturamento
				String grupoFaturamento = "";

				if (contaMensagem.getFaturamentoGrupo() != null) {
					grupoFaturamento = contaMensagem.getFaturamentoGrupo()
							.getDescricao();
				}

				// Ger�ncia Regional
				String gerenciaRegional = "";

				if (contaMensagem.getGerenciaRegional() != null) {
					gerenciaRegional = contaMensagem.getGerenciaRegional()
							.getNomeAbreviado();
				}

				// Localidade
				String localidade = "";

				if (contaMensagem.getLocalidade() != null) {
					localidade = contaMensagem.getLocalidade().getId()
							.toString();
				}

				// Setor Comercial
				String setorComercial = "";

				if (contaMensagem.getSetorComercial() != null) {
					setorComercial = ""
							+ contaMensagem.getSetorComercial().getCodigo();
				}

				relatorioBean = new RelatorioManterMensagemContaBean(
						// Refer�ncia
						Util.formatarAnoMesParaMesAno(contaMensagem
								.getAnoMesRreferenciaFaturamento().intValue()),

						// Mensagem
						contaMensagem.getDescricaoContaMensagem01(),

						// Grupo Faturamento
						grupoFaturamento,

						// Ger�ncia Regional
						gerenciaRegional,

						// Localidade
						localidade,

						// Setor Comercial
						setorComercial);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);

				// Verifica se Mensagem da conta � composta de uma segunda
				// mensagem para adicionar no bean e ser mostrado relat�rio
				// apenas a segunda mensagem com os outros campos vazios
				if (contaMensagem.getDescricaoContaMensagem02() != null
						&& !contaMensagem.getDescricaoContaMensagem02().trim()
								.equals("")) {
					relatorioBean = new RelatorioManterMensagemContaBean(
							// Refer�ncia
							"",

							// Mensagem
							contaMensagem.getDescricaoContaMensagem02(),

							// Grupo Faturamento
							"",

							// Ger�ncia Regional
							"",

							// Localidade
							"",

							// Setor Comercial
							"");

					// adiciona o bean a cole��o
					relatorioBeans.add(relatorioBean);

					// Verifica se Mensagem da conta � composta de uma terceira
					// mensagem para adicionar no bean e ser mostrado relat�rio
					// apenas a terceira mensagem com os outros campos vazios
					if (contaMensagem.getDescricaoContaMensagem03() != null
							&& !contaMensagem.getDescricaoContaMensagem03()
									.trim().equals("")) {
						relatorioBean = new RelatorioManterMensagemContaBean(
								// Refer�ncia
								"",

								// Mensagem
								contaMensagem.getDescricaoContaMensagem03(),

								// Grupo Faturamento
								"",

								// Ger�ncia Regional
								"",

								// Localidade
								"",

								// Setor Comercial
								"");

						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioBean);
					}
				}
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (contaMensagemParametros.getAnoMesRreferenciaFaturamento() != null) {
			parametros.put("referencia", Util
					.formatarAnoMesParaMesAno(contaMensagemParametros
							.getAnoMesRreferenciaFaturamento().intValue()));
		} else {
			parametros.put("referencia", "");
		}

		parametros.put("mensagem", contaMensagemParametros
				.getDescricaoContaMensagem01());

		parametros.put("grupo", contaMensagemParametros.getFaturamentoGrupo()
				.getDescricao());

		if (contaMensagemParametros.getLocalidade().getId() != null) {
			parametros.put("idLocalidade", contaMensagemParametros
					.getLocalidade().getId().toString());
			parametros.put("nomeLocalidade", contaMensagemParametros
					.getLocalidade().getDescricao());
		} else {
			parametros.put("idLocalidade", "");
			parametros.put("nomeLocalidade", "");
		}

		if (contaMensagemParametros.getSetorComercial().getId() != null) {
			parametros.put("idSetorComercial", ""
					+ contaMensagemParametros.getSetorComercial().getCodigo());
			parametros.put("nomeSetorComercial", contaMensagemParametros
					.getSetorComercial().getDescricao());
		} else {
			parametros.put("idSetorComercial", "");
			parametros.put("nomeSetorComercial", "");
		}

		parametros.put("gerenciaRegional", contaMensagemParametros
				.getGerenciaRegional().getNomeAbreviado());

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MENSAGEM_CONTA_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_MENSAGEM_CONTA, idFuncionalidadeIniciada);
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

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroContaMensagem) getParametro("filtroContaMensagem"),
				ContaMensagem.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterMensagemConta", this);
	}

}