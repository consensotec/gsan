package gcom.gui.relatorio.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
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
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 
 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
 * 
 * @author Hugo Azevedo
 * @date 02/07/2011
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */

public class RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado(
			Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO);
	}

	@Deprecated
	public RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado() {
		super(null,
				"");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Parâmetros vindos do gerar relatório
		@SuppressWarnings("rawtypes")
		Collection colecaoImovelOS = (Collection) getParametro("colecaoImovelOS");
		String tipoServico = (String) getParametro("tipoServico");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String[] categoriaImovel = (String[]) getParametro("categoriaImovel");
		String[] perfilImovel = (String[]) getParametro("perfilImovel");
		String[] gerenciaRegional = (String[]) getParametro("gerenciaRegional");
		String[] unidadeNegocio = (String[]) getParametro("unidadeNegocio");
		String descLocalidadeInicial = (String) getParametro("descLocalidadeInicial");
		String descLocalidadeFinal = (String) getParametro("descLocalidadeFinal");
		String idSetorComercialInicial = (String) getParametro("idSetorComercialInicial");
		String idSetorComercialFinal = (String) getParametro("idSetorComercialFinal");
		String idQuadraInicial = (String) getParametro("idQuadraInicial");
		String idQuadraFinal = (String) getParametro("idQuadraFinal");

		// Variáveis
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		@SuppressWarnings("rawtypes")
		Map parametros = new HashMap();

		// valor de retorno
		byte[] retorno = null;

		// Verifica se a filtragem de gerência regional foi realizada
		boolean filtrouGerencia = false;
		if (gerenciaRegional != null && gerenciaRegional.length > 0)
			filtrouGerencia = true;

		// Montar Cabeçalho
		parametros = montarCabecalho(categoriaImovel, perfilImovel, gerenciaRegional, unidadeNegocio, descLocalidadeInicial, descLocalidadeFinal, idSetorComercialInicial, idSetorComercialFinal, idQuadraInicial, idQuadraFinal, tipoServico, sistemaParametro, fachada);

		// Montar Beans
		RelatorioDataSource ds = montarBeans(colecaoImovelOS, fachada, tipoServico, filtrouGerencia);

		// Montar relatório
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO, parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException(	"Erro ao gravar relatório no sistema",
										e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	/**
	 * 
	 * Método auxiliar para montar o cabeçalho do relatório
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	@SuppressWarnings("rawtypes")
	private Map montarCabecalho(String[] categoriaImovel, String[] perfilImovel, String[] gerenciaRegional,
			String[] unidadeNegocio, String descLocalidadeInicial, String descLocalidadeFinal,
			String idSetorComercialInicial, String idSetorComercialFinal, String idQuadraInicial, String idQuadraFinal,
			String tipoServico, SistemaParametro sistemaParametro, Fachada fachada) {

		HashMap parametros = new HashMap();

		// Imagem do relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		/*
		 * Verifica se alguma categoria foi selecionada. Caso não haja, insere
		 * 'Todas'
		 */
		if (categoriaImovel != null && categoriaImovel.length > 0) {
			for (int i = 0; i < categoriaImovel.length; i++) {
				Integer id = new Integer(categoriaImovel[i]);
				categoriaImovel[i] = this.obterDescricaoCategoria(id, fachada);
			}
			String categorias = StringUtils.join(categoriaImovel, ",");
			parametros.put("categorias", categorias);
		} else {
			parametros.put("categorias", "Todas");
		}

		/*
		 * Verifica se algum perfil de imóvel foi selecionado. Caso não haja,
		 * insere 'Todas'
		 */
		if (perfilImovel != null && perfilImovel.length > 0) {
			for (int i = 0; i < perfilImovel.length; i++) {
				Integer id = new Integer(perfilImovel[i]);
				perfilImovel[i] = this.obterDescricaoPerfil(id, fachada);
			}

			String perfilI = StringUtils.join(perfilImovel, ",");
			parametros.put("perfilI", perfilI);
		} else {
			parametros.put("perfilI", "Todas");
		}

		// Insere os parâmetros na hash de retorno
		parametros.put("descLocalidadeInicial", descLocalidadeInicial);
		parametros.put("descLocalidadeFinal", descLocalidadeFinal);
		parametros.put("descLocalidadeFinal", descLocalidadeFinal);
		parametros.put("idSetorComercialInicial", idSetorComercialInicial);
		parametros.put("idSetorComercialFinal", idSetorComercialFinal);
		parametros.put("idQuadraInicial", idQuadraInicial);
		parametros.put("idQuadraFinal", idQuadraFinal);
		parametros.put("tipoServico", obterDescricaoTipoServico(new Integer(tipoServico), fachada));
		// parametros.put("tipoServico",tipoServico);

		// retorna ao método principal
		return parametros;

	}

	/**
	 * 
	 * Método auxiliar para montar os dados do relatório
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	private RelatorioDataSource montarBeans(@SuppressWarnings("rawtypes") Collection colecaoImovelOS, Fachada fachada, String tipoServico,
			boolean filtrouGerencia) {

		// lista de retorno
		ArrayList<RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean> beans = new ArrayList<RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean>();

		// Iteração com cada imóvel retornado pelo filtro
		@SuppressWarnings("rawtypes")
		Iterator it = colecaoImovelOS.iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			HashMap mapa = (HashMap) it.next();

			Integer id = (Integer) mapa.get("imovel");

		
			// Busca o cliente do tipo USUARIO do imóvel
			@SuppressWarnings("rawtypes")
			Collection colecaoCliente = fachada.obterClienteImovelporRelacaoTipo(id, new Integer(ClienteRelacaoTipo.USUARIO));

			Cliente cliente = null;
			if (colecaoCliente.size() > 0) {
				ClienteImovel cliim = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoCliente);
				cliente = cliim.getCliente();
			} else {
				System.out.println("Aqui");
			}

			// Retorna a coleção de cobranças do imóvel
			@SuppressWarnings("rawtypes")
			Collection colecaoOrdemServicoEmpresaCobranca = fachada.obterColecaoEmpresaCobrancaContaporImovel(id, new Integer(tipoServico));

			// Iteração com cada ordem de serviço retornada
			@SuppressWarnings("rawtypes")
			Iterator it2 = colecaoOrdemServicoEmpresaCobranca.iterator();
			while (it2.hasNext()) {
				
				OrdemServico os = (OrdemServico) it2.next();

				RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean bean = new RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean();
				bean.setClienteNome(cliente.getNome());
				// Caso tenha filtrado por gerência, colocar no bean o codigo e
				// descrição da mesma.
				// Caso contrário, colocar 'Todas'
				if (filtrouGerencia) {
					bean.setIdGerenciaRegional(((Integer) mapa.get("idGerenciaRegional")).toString());
					bean.setDescGerenciaRegional((String) mapa.get("descGerenciaRegional"));
				} else
					bean.setDescGerenciaRegional("Todas");

				// Dados do bean
				bean.setIdUnidadeNegocio(((Integer) mapa.get("idUnidadeNegocio")).toString());
				bean.setDescUnidadeNegocio((String) mapa.get("descUnidadeNegocio"));
				bean.setDataEncerramento(Util.formatarData(os.getDataEncerramento()));
				bean.setDataGeracao(Util.formatarData(os.getDataGeracao()));
				bean.setDescricaoServico(os.getServicoTipo().getDescricao());
				bean.setImovelMatricula(os.getImovel().getId().toString());

				if (os.getAtendimentoMotivoEncerramento() != null)
					bean.setMotivoEncerramento(os.getAtendimentoMotivoEncerramento().getDescricao());

				bean.setNumeroOS(os.getId().toString());
				
				this.obterValorEnviadoCobrancaPorResultado(os, fachada, bean);

				
				this.obterValorPagoCobrancaPorResultado(os, fachada, bean);

				// adiciona o bean a coleção de retorno
				beans.add(bean);
			}
		}

		RelatorioDataSource ds = new RelatorioDataSource(beans);
		return ds;
	}

	/**
	 * 
	 * Método auxiliar para retornar o valor enviado do imóvel
	 * 
	 * @author Hugo Azevedo, Rômulo Aurélio
	 * @data 02/07/2011, 24/10/2011
	 */
	private void obterValorEnviadoCobrancaPorResultado(OrdemServico os, Fachada fachada,
			RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean bean) {

		Collection<Object> colecao = fachada.obterValorEnviadoCobrancaPorResultado(os);

		BigDecimal valorEnviado = new BigDecimal(0.0);
		Integer contasEnviadas = 0;

		if (colecao.size() > 1) {

			@SuppressWarnings("rawtypes")
			Iterator it = colecao.iterator();
			while (it.hasNext()) {

				Object[] obj = (Object[]) it.next();

				valorEnviado = valorEnviado.add((BigDecimal) obj[0]);

				contasEnviadas = contasEnviadas + (Integer) obj[1];

			}

		} else {

			Object[] obj = (Object[]) colecao.iterator().next();

			valorEnviado = (BigDecimal) obj[0];

			contasEnviadas = (Integer) obj[1];

		}

		bean.setValorEnviado(valorEnviado);
		bean.setQtdContas(contasEnviadas.toString());

	}

	/**
	 * 
	 * Método auxiliar para retornar o valor pago do imóvel
	 * 
	 * @author Hugo Azevedo, Rômulo Aurélio
	 * @data 02/07/2011, 24/10/2011 
	 */
	private void obterValorPagoCobrancaPorResultado(OrdemServico os, Fachada fachada,
			RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean bean) {

		Collection<Object> colecaoPagos = fachada.obterValorPagoCobrancaPorResultado(os);

		BigDecimal valorPago = new BigDecimal(0.0);
		Integer contasPagas = 0;
		
		@SuppressWarnings("rawtypes")
		Iterator it = colecaoPagos.iterator();

		Object[] obj = (Object[]) it.next();

		valorPago = valorPago.add((BigDecimal) obj[0]);

		contasPagas = contasPagas + (Integer) obj[1];

		
		
		Collection<Object> colecaoParcelados = fachada.obterValorParceladoCobrancaPorResultado(os);
		BigDecimal valorParcelado = new BigDecimal(0.0);
		if (colecaoParcelados != null && !colecaoParcelados.isEmpty()) {
			if (colecaoParcelados.size() > 1) {

				Iterator<Object> iterator = colecaoParcelados.iterator();
				while (iterator.hasNext()) {

					Object[] ob = (Object[]) iterator.next();

					valorParcelado = valorParcelado.add((BigDecimal) ob[0]);

				}

			} else {

				Object[] ob = (Object[]) colecaoParcelados.iterator().next();

				valorParcelado = valorParcelado.add((BigDecimal) ob[0]);

			}
		}
		valorPago = valorPago.add(valorParcelado);
		
		bean.setValorPago(valorPago);
		
	}

	// Método auxiliar para recuperar a descrição do perfil
	private String obterDescricaoPerfil(Integer id, Fachada fachada) {
		String retorno = "";
		FiltroImovelPerfil filtroIP = new FiltroImovelPerfil();
		filtroIP.adicionarParametro(new ParametroSimples(	FiltroImovelPerfil.ID,
															id));
		@SuppressWarnings("rawtypes")
		Collection colecao = fachada.pesquisar(filtroIP, ImovelPerfil.class.getName());
		if (colecao.size() > 0) {
			ImovelPerfil ip = (ImovelPerfil) Util.retonarObjetoDeColecao(colecao);
			retorno = ip.getDescricao();
		}
		return retorno;

	}

	// Método auxiliar para recuperar a descrição da categoria
	private String obterDescricaoCategoria(Integer id, Fachada fachada) {
		String retorno = "";
		FiltroCategoria filtroCat = new FiltroCategoria();
		filtroCat.adicionarParametro(new ParametroSimples(	FiltroCategoria.CODIGO,
															id));
		@SuppressWarnings("rawtypes")
		Collection colecao = fachada.pesquisar(filtroCat, Categoria.class.getName());
		if (colecao.size() > 0) {
			Categoria cat = (Categoria) Util.retonarObjetoDeColecao(colecao);
			retorno = cat.getDescricao();
		}
		return retorno;
	}

	// Método auxiliar para recuperar a descrição do tipo de serviço
	private String obterDescricaoTipoServico(Integer id, Fachada fachada) {
		String retorno = "";
		FiltroServicoTipo filtroST = new FiltroServicoTipo();
		filtroST.adicionarParametro(new ParametroSimples(	FiltroServicoTipo.ID,
															id));
		@SuppressWarnings("rawtypes")
		Collection colecao = fachada.pesquisar(filtroST, ServicoTipo.class.getName());
		if (colecao.size() > 0) {
			ServicoTipo st = (ServicoTipo) Util.retonarObjetoDeColecao(colecao);
			retorno = st.getDescricao();
		}
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {

		@SuppressWarnings("rawtypes")
		HashMap mapa = this.retornarParametrosContadorOS();
		@SuppressWarnings("rawtypes")
		Collection colecaoImovelOS = (Collection) mapa.get("colecaoImovelOS");
		String tipoServico = (String) mapa.get("tipoServico");

		int retorno = Fachada.getInstancia().obterTotalOSColecaoImovelTipoServico(colecaoImovelOS, new Integer(tipoServico));

		// int retorno = 2;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado", this);
	}

	// Método auxiliar para repassar os parâmetros para o avaliador batch
	@SuppressWarnings("rawtypes")
	private HashMap retornarParametrosContadorOS() {
		HashMap mapa = new HashMap();

		Collection colecaoImovelOS = (Collection) getParametro("colecaoImovelOS");
		String tipoServico = (String) getParametro("tipoServico");

		mapa.put("colecaoImovelOS", colecaoImovelOS);
		mapa.put("tipoServico", tipoServico);

		return mapa;
	}

}
