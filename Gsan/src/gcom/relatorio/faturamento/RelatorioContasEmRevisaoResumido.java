package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ContasEmRevisaoRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de volumes faturados
 * 
 * @author Rafael Corr�a
 * @created 12/09/2007
 */
public class RelatorioContasEmRevisaoResumido extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioContasEmRevisaoResumido(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONTAS_EM_REVISAO_RESUMIDO);
	}

	@Deprecated
	public RelatorioContasEmRevisaoResumido() {
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

		Integer idGerenciaRegional = (Integer) getParametro("idGerenciaRegional");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer codigoSetorComercialInicial = (Integer) getParametro("codigoSetorComercialInicial");
		Integer codigoSetorComercialFinal = (Integer) getParametro("codigoSetorComercialFinal");
		Collection<Integer> colecaoIdsMotivoRevisao = (Collection<Integer>) getParametro("colecaoIdsMotivoRevisao");
		Integer idImovelPerfil = (Integer) getParametro("idImovelPerfil");
		Integer referenciaInicial = (Integer) getParametro("referenciaInicial");
		Integer referenciaFinal = (Integer) getParametro("referenciaFinal");
		Integer idCategoria = (Integer) getParametro("idCategoria");
		Integer idEsferaPoder = (Integer) getParametro("idEsferaPoder");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioContasEmRevisaoBean relatorioBean = null;

		Collection colecaoContasEmRevisaoRelatorioHelper = fachada
				.pesquisarDadosRelatorioContasRevisaoResumido(idGerenciaRegional, idUnidadeNegocio, 
						idLocalidadeInicial, idLocalidadeFinal, 
						codigoSetorComercialInicial, codigoSetorComercialFinal,
						colecaoIdsMotivoRevisao, idImovelPerfil, referenciaInicial,
						referenciaFinal, idCategoria, idEsferaPoder);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoContasEmRevisaoRelatorioHelper != null
				&& !colecaoContasEmRevisaoRelatorioHelper.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoContasEmRevisaoRelatorioHelperIterator = colecaoContasEmRevisaoRelatorioHelper
					.iterator();
			
			// Cria as vari�veis para verificar se os totalizadores de ger�ncia
			// e elo ser�o mostrados no relat�rio
			String imprimeElo = null;
			
			if (idGerenciaRegional != null) {
				imprimeElo = "SIM";
			}
			
			// Cria as vari�veis de totaliza��o
			Integer qtdeContasTotalLocalidade = new Integer("0");
			BigDecimal valorContasTotalLocalidade = new BigDecimal("0.00");
			
			Integer qtdeContasTotalElo = new Integer("0");
			BigDecimal valorContasTotalElo = new BigDecimal("0.00");
			
			Integer qtdeContasTotalGerenciaRegional = new Integer("0");
			BigDecimal valorContasTotalGerenciaRegional = new BigDecimal("0.00");

			Integer idLocalidadeAnterior = null;
			Integer idMotivoAnterior = null;
			Integer idEloAnterior = null;
			Integer idGerenciaRegionalAnterior = null;
			boolean zerarLocalidade = false;
			boolean zerarElo = false;
			boolean zerarGerenciaRegional = false;

			boolean primeiraVez = true;

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoContasEmRevisaoRelatorioHelperIterator.hasNext()) {
				
				ContasEmRevisaoRelatorioHelper contasEmRevisaoRelatorioHelper = (ContasEmRevisaoRelatorioHelper) colecaoContasEmRevisaoRelatorioHelperIterator
						.next();
				
				// Seta os valores das vari�veis de controle de totaliza��o para
				// verificar quando deve ser zerado os totalizadores
				if (idLocalidadeAnterior == null) {
					idLocalidadeAnterior = contasEmRevisaoRelatorioHelper
							.getIdLocalidade();
				}
				
				if (idMotivoAnterior == null) {
					idMotivoAnterior = contasEmRevisaoRelatorioHelper
							.getIdMotivoRevisao();
				}
				
				if (idEloAnterior == null) {
					idEloAnterior = contasEmRevisaoRelatorioHelper
							.getIdElo();
				}

				if (idGerenciaRegionalAnterior == null) {
					idGerenciaRegionalAnterior = contasEmRevisaoRelatorioHelper
							.getIdGerenciaRegional();
				}

				// Cria as vari�veis de motivo e localidade atual e anterior
				// para ser verificado no relat�rio se deve ser mostrado o valor
				// da localidade
				String motivoLocalidadeAnterior = "";
				String motivoLocalidade = "";
				
				if (!primeiraVez) {
					motivoLocalidadeAnterior = motivoLocalidadeAnterior + idMotivoAnterior;
					motivoLocalidadeAnterior = motivoLocalidadeAnterior + idLocalidadeAnterior;
				} else {
					primeiraVez = false;
				}
				
				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio
				
				// Ger�ncia Regional
				String gerenciaRegional = "";

				if (contasEmRevisaoRelatorioHelper.getIdGerenciaRegional() != null) {
					gerenciaRegional = contasEmRevisaoRelatorioHelper
							.getIdGerenciaRegional()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getNomeGerenciaRegional();
					
					// Caso tenha mudado a ger�ncia regional do im�vel seta a
					// vari�vel para true, para posteriormente zerar todas as
					// vari�veis de totaliza��o da ger�ncia regional
					if (!idGerenciaRegionalAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdGerenciaRegional())) {
						zerarGerenciaRegional = true;
					}
					
					// Caso tenha mudado o elo do im�vel seta a vari�vel para
					// true, para posteriormente zerar todas as vari�veis de
					// totaliza��o do elo
					if (!idEloAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdElo())) {
						zerarElo = true;
					}
				}

				// Elo
				String elo = "";

				if (contasEmRevisaoRelatorioHelper.getIdElo() != null) {
					elo = contasEmRevisaoRelatorioHelper.getIdElo() + " - "
							+ contasEmRevisaoRelatorioHelper.getNomeElo();
				}

				
				
				// Motivo da Reclama��o
				String motivoReclamacao = "";

				if (contasEmRevisaoRelatorioHelper.getIdMotivoRevisao() != null) {
					motivoReclamacao = contasEmRevisaoRelatorioHelper
							.getIdMotivoRevisao()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getDescricaoMotivoRevisao();
					
					motivoLocalidade = motivoLocalidade
							+ contasEmRevisaoRelatorioHelper
									.getIdMotivoRevisao();
					
					// Caso tenha mudado o motivo de revis�o seta a vari�vel
					// para true, para posteriormente zerar todas as vari�veis
					// de totaliza��o da localidade
					if (!idMotivoAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdMotivoRevisao())) {
						zerarLocalidade = true;
						zerarElo = true;
						zerarGerenciaRegional = true;
					}
				}
				
				// Localidade
				String localidade = "";

				if (contasEmRevisaoRelatorioHelper.getIdLocalidade() != null) {
					localidade = contasEmRevisaoRelatorioHelper
							.getIdLocalidade()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getNomeLocalidade();
					
					motivoLocalidade = motivoLocalidade
							+ contasEmRevisaoRelatorioHelper.getIdLocalidade();

					// Caso tenha mudado a localidade do im�vel seta a vari�vel
					// para true, para posteriormente zerar todas as vari�veis
					// de totaliza��o da localidade
					if (!idLocalidadeAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdLocalidade())) {
						zerarLocalidade = true;
					}
				}
				
				// Zera os totalizadores da localidade
				if (zerarLocalidade) {
					qtdeContasTotalLocalidade = new Integer("0");
					valorContasTotalLocalidade = new BigDecimal("0.00");

					zerarLocalidade = false;
					idLocalidadeAnterior = contasEmRevisaoRelatorioHelper
							.getIdLocalidade();
					idMotivoAnterior = contasEmRevisaoRelatorioHelper
							.getIdMotivoRevisao();
				}
				
				// Zera os totalizadores do elo
				if (zerarElo) {
					qtdeContasTotalElo = new Integer("0");
					valorContasTotalElo = new BigDecimal("0.00");

					zerarElo = false;
					idEloAnterior = contasEmRevisaoRelatorioHelper
							.getIdElo();
				}

				// Zera os totalizadores da ger�ncia regional
				if (zerarGerenciaRegional) {
					qtdeContasTotalGerenciaRegional = new Integer("0");
					valorContasTotalGerenciaRegional = new BigDecimal("0.00");

					zerarGerenciaRegional = false;
					idGerenciaRegionalAnterior = contasEmRevisaoRelatorioHelper
							.getIdGerenciaRegional();
				}


				// M�s/Ano da Fatura
				String mesAnoFatura = "";
				
				if (contasEmRevisaoRelatorioHelper.getAnoMesReferenciaConta() != null) {
					mesAnoFatura = Util
							.formatarMesAnoReferencia(contasEmRevisaoRelatorioHelper
									.getAnoMesReferenciaConta());
				}
				
				// Quantidade de Contas
				String qtdeContas = "";
				
				if (contasEmRevisaoRelatorioHelper.getQtdeContas() != null) {
					qtdeContas = contasEmRevisaoRelatorioHelper.getQtdeContas().toString(); 
					qtdeContasTotalLocalidade = qtdeContasTotalLocalidade + contasEmRevisaoRelatorioHelper.getQtdeContas();
					qtdeContasTotalElo = qtdeContasTotalElo + contasEmRevisaoRelatorioHelper.getQtdeContas();
					qtdeContasTotalGerenciaRegional = qtdeContasTotalGerenciaRegional + contasEmRevisaoRelatorioHelper.getQtdeContas();
				}

				// Valor das Contas
				String valorConta = "";

				if (contasEmRevisaoRelatorioHelper.getValorConta() != null) {
					valorConta = Util.formatarMoedaReal(contasEmRevisaoRelatorioHelper.getValorConta());

					// Soma os valores aos totalizadores de cada grupo
					valorContasTotalLocalidade = valorContasTotalLocalidade.add(contasEmRevisaoRelatorioHelper.getValorConta());
					valorContasTotalElo = valorContasTotalElo.add(contasEmRevisaoRelatorioHelper.getValorConta());
					valorContasTotalGerenciaRegional = valorContasTotalGerenciaRegional.add(contasEmRevisaoRelatorioHelper.getValorConta());

				}
				
				relatorioBean = new RelatorioContasEmRevisaoBean();
				relatorioBean.setGerenciaRegional(gerenciaRegional);
				relatorioBean.setElo(elo);
				relatorioBean.setLocalidade(localidade);
				relatorioBean.setMesAnoFatura(mesAnoFatura);
				relatorioBean.setQtdeContas(qtdeContas);
				relatorioBean.setValorConta(valorConta);
				relatorioBean.setMotivoReclamacao(motivoReclamacao);
				
				relatorioBean.setQtdeTotalContasEmRevisaoLocalidade(qtdeContasTotalLocalidade.toString());
				relatorioBean.setValorTotalContasEmRevisaoLocalidade(Util.formatarMoedaReal(valorContasTotalLocalidade));
				
				relatorioBean.setQtdeTotalContasEmRevisaoElo(qtdeContasTotalElo.toString());
				relatorioBean.setValorTotalContasEmRevisaoElo(Util.formatarMoedaReal(valorContasTotalElo));

				relatorioBean.setQtdeTotalContasEmRevisaoGerenciaRegional(qtdeContasTotalGerenciaRegional.toString());
				relatorioBean.setValorTotalContasEmRevisaoGerenciaRegional(Util.formatarMoedaReal(valorContasTotalGerenciaRegional));	
				
				relatorioBean.setImprimeElo(imprimeElo);
				relatorioBean.setImprimeGerenciaRegional(gerenciaRegional);
				
				relatorioBean.setMotivoLocalidadeAnterior(motivoLocalidadeAnterior);
				relatorioBean.setMotivoLocalidade(motivoLocalidade);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);

			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		
		Integer anoMes = sistemaParametro.getAnoMesFaturamento();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(anoMes));

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTAS_EM_REVISAO_RESUMIDO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.CONTAS_EM_REVISAO_RESUMIDO,
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

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasEmRevisaoResumido",
				this);
	}
}