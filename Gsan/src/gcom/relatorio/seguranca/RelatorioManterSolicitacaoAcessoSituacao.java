package gcom.relatorio.seguranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioManterSolicitacaoAcessoSituacao extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioManterSolicitacaoAcessoSituacao(Usuario usuario) {
		super(
				usuario,
				ConstantesRelatorios.RELATORIO_MANTER_SOLICITACAO_ACESSO_SITUACAO);
	}

	@Deprecated
	public RelatorioManterSolicitacaoAcessoSituacao() {
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

		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = (FiltroSolicitacaoAcessoSituacao) getParametro("filtroSolicitacaoAcessoSituacao");
		SolicitacaoAcessoSituacao solicitacaoAcessoSituacaoParametros = (SolicitacaoAcessoSituacao) getParametro("solicitacaoAcessoSituacaoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterSolicitacaoAcessoSituacaoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroSolicitacaoAcessoSituacao.setConsultaSemLimites(true);

		Collection<SolicitacaoAcessoSituacao> colecaoSolicitacaoAcessoSituacao = fachada
				.pesquisar(filtroSolicitacaoAcessoSituacao,
						SolicitacaoAcessoSituacao.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoSolicitacaoAcessoSituacao != null
				&& !colecaoSolicitacaoAcessoSituacao.isEmpty()) {

			// la�o para criar a cole��o de par�metros da analise
			for (SolicitacaoAcessoSituacao solicitacaoAcessoSituacao : colecaoSolicitacaoAcessoSituacao) {

				String ativoInativo = "";

				if (solicitacaoAcessoSituacao.getIndicadorUso().equals(
						ConstantesSistema.INDICADOR_USO_ATIVO)) {
					ativoInativo = "Ativo";
				} else if (solicitacaoAcessoSituacao.getIndicadorUso().equals(
						ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					ativoInativo = "Inativo";
				} else {
					ativoInativo = "Todos";
				}

				String codigoSituacao = "";
				if (solicitacaoAcessoSituacao.getCodigoSituacao()== 1) {
					codigoSituacao = "Aguar. Autoriza��o";
				} else if (solicitacaoAcessoSituacao.getCodigoSituacao()==2) {
					codigoSituacao = "Aguar. Cadastramento";
				} else if (solicitacaoAcessoSituacao.getCodigoSituacao()==3) {
					codigoSituacao = "Concluido";
				}

				relatorioBean = new RelatorioManterSolicitacaoAcessoSituacaoBean(
				// C�digo
						solicitacaoAcessoSituacao.getId().toString(),

						// Descri��o
						solicitacaoAcessoSituacao.getDescricao(),

						// Indicador de Uso
						ativoInativo,

						codigoSituacao);

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

		if (solicitacaoAcessoSituacaoParametros.getId() != null) {
			parametros.put("id", solicitacaoAcessoSituacaoParametros.getId()
					.toString());
		} else {
			parametros.put("id", "");
		}

		if (solicitacaoAcessoSituacaoParametros.getDescricao() != null
				&& !solicitacaoAcessoSituacaoParametros.getDescricao().equals(
						"")) {

			parametros.put("descricao",
					solicitacaoAcessoSituacaoParametros.getDescricao());
		} else {
			parametros.put("descricao", "");
		}

		String indicadorUso = "";

		if (solicitacaoAcessoSituacaoParametros.getIndicadorUso() != null
				&& !solicitacaoAcessoSituacaoParametros.getIndicadorUso()
						.equals("")) {
			if (solicitacaoAcessoSituacaoParametros.getIndicadorUso().equals(
					new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (solicitacaoAcessoSituacaoParametros.getIndicadorUso()
					.equals(new Short("2"))) {
				indicadorUso = "Inativo";
			} else if (solicitacaoAcessoSituacaoParametros.getIndicadorUso()
					.equals("3")) {
				indicadorUso = "Todos";
			}

		}
		parametros.put("indicadorUso", indicadorUso);

		String codigoSituacao = "";

		if (solicitacaoAcessoSituacaoParametros.getCodigoSituacao() != null
				&& !solicitacaoAcessoSituacaoParametros.getCodigoSituacao()
						.equals("")) {
			if (solicitacaoAcessoSituacaoParametros.getCodigoSituacao().equals(
					new Short("1"))) {
				codigoSituacao = "Aguar. Autorizacao";
			} else if (solicitacaoAcessoSituacaoParametros.getCodigoSituacao()
					.equals(new Short("2"))) {
				codigoSituacao = "Aguar. Cadastramento";
			} else if (solicitacaoAcessoSituacaoParametros.getCodigoSituacao()
					.equals("3")) {
				codigoSituacao = "Concluido";
			}

		}
		parametros.put("codigoSituacao", codigoSituacao);

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_MANTER_SOLICITACAO_ACESSO_SITUACAO,
						parametros, ds, tipoFormatoRelatorio);

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"RelatorioManterSolicitacaoAcessoSituacao", this);
	}

}
