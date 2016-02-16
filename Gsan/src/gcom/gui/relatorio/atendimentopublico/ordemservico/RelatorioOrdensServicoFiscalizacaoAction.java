package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOrdemServicoFiscalizacaoAnaliticoBean;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOrdemServicoFiscalizacaoSinteticoBean;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



/**
 * 
 * 
 * [UC1213] Emitir Relatorio de Ordem de Sercico de Fiscalizacao
 * 
 * @author Paulo Diniz
 * @date 02/08/2011
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */


public class RelatorioOrdensServicoFiscalizacaoAction extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	private int relatorioTipoFiscalizacao;
	
	public RelatorioOrdensServicoFiscalizacaoAction(Usuario usuario, String tipoRelatorio) {
		super(usuario, tipoRelatorio);
	}
	
	@Deprecated
	public RelatorioOrdensServicoFiscalizacaoAction() {
		super(null, "");
	}
	
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		
		//Parâmetros vindos do gerar relatório
		int tipoFormatoRelatorio = (Integer)getParametro("tipoFormatoRelatorio");
		relatorioTipoFiscalizacao = (Integer)getParametro("relatorioTipoFiscalizacao");
		String idLocalidadeFinal = (String)getParametro("idLocalidadeFinal");
		String idLocalidadeInicial = (String)getParametro("idLocalidadeInicial");
		String periodoFinal = (String)getParametro("periodoFinal");
		String periodoInicial =(String)getParametro("periodoInicial");
		String descLocalidadeInicial = (String)getParametro("descLocalidadeInicial");
		String descLocalidadeFinal = (String)getParametro("descLocalidadeFinal");
		String idGerenciaRegional = (String)getParametro("idGerenciaRegional");
		String idUnidadeNegocios = (String)getParametro("idUnidadeNegocios");
		String situacaoOS = (String)getParametro("situacaoOS");
		String idOSReferidaRetornoTipo = (String)getParametro("idOSReferidaRetornoTipo");
		String aceitacaoDaOS = (String)getParametro("aceitacaoDaOS");
		
		//Variáveis
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		//valor de retorno
		byte[] retorno = null;
		
		//Relatorio Analitico
		if(relatorioTipoFiscalizacao == 1){
			
			//Define a variálvel de nome usada para construir o relatorio
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO;
			
			//Montar Cabeçalho
			parametros = montarCabecalhoAnalitico(idLocalidadeInicial, idLocalidadeFinal, periodoInicial, periodoFinal, descLocalidadeInicial, descLocalidadeFinal, idGerenciaRegional, idUnidadeNegocios, situacaoOS, idOSReferidaRetornoTipo, aceitacaoDaOS, sistemaParametro, fachada);
			
			//Recuperando as ordem de servico especificadas
	        Collection<OrdemServico> colecaoOrdemServico = fachada.pesquisarOrdensServicoFiscalizacao(
									        		relatorioTipoFiscalizacao, 
									        		periodoInicial, 
									        		periodoFinal,
									        		idGerenciaRegional, 
									        		idUnidadeNegocios, 
									        		idLocalidadeInicial, 
									        		idLocalidadeFinal, 
									        		situacaoOS, 
									        		idOSReferidaRetornoTipo, 
									        		aceitacaoDaOS);
			

			if(colecaoOrdemServico != null && colecaoOrdemServico.size() > 0){
				//Montar Beans
				RelatorioDataSource ds = montarBeansAnalitico(colecaoOrdemServico,fachada);
				
				//Montar relatório
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO,
						parametros, ds, tipoFormatoRelatorio);
			} else {
				//Montar Beans
				ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean> beans = montarBeansSintetico(fachada);
				RelatorioOrdemServicoFiscalizacaoSinteticoBean bean = new RelatorioOrdemServicoFiscalizacaoSinteticoBean();
				beans.add(bean);
				
				RelatorioDataSource ds = new RelatorioDataSource(beans);
				
				this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
				
				//Montar Relatório Vazio
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
						parametros, ds, tipoFormatoRelatorio);
			}
	        
			
			// ------------------------------------
			// Grava o relatório no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
			// ------------------------------------
		}
		//Relatorio Sintetico
		else if(relatorioTipoFiscalizacao == 2){
			
			//Define a variálvel de nome usada para construir o relatorio
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO;
			
			//Montar Cabeçalho
			parametros = montarCabecalhoSintetico(idLocalidadeInicial, idLocalidadeFinal, periodoInicial, periodoFinal, descLocalidadeInicial, descLocalidadeFinal, idGerenciaRegional, idUnidadeNegocios, sistemaParametro, fachada);
			
			//Montar Beans
			ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean> beans = montarBeansSintetico(fachada);
			
			if(beans != null && beans.size() > 0){
				RelatorioDataSource ds = new RelatorioDataSource(beans);

				//Montar Relatório
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO,
						parametros, ds, tipoFormatoRelatorio);
			} else {
				RelatorioOrdemServicoFiscalizacaoSinteticoBean bean = new RelatorioOrdemServicoFiscalizacaoSinteticoBean();
				beans.add(bean);
				
				RelatorioDataSource ds = new RelatorioDataSource(beans);
				
				this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
				
				//Montar Relatório Vazio
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
						parametros, ds, tipoFormatoRelatorio);
			}
			
			
			// ------------------------------------
			// Grava o relatório no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
			// ------------------------------------
			
		}
	
		
		// retorna o relatório gerado
		return retorno;
	}
	
	
	
	/**
	 * 
	 * Método auxiliar para montar o cabeçalho do relatório analitico
	 * 
	 * @author Paulo Diniz
	 * @data 12/08/2011
	 */
	private Map<String, Object> montarCabecalhoAnalitico(String idLocalidadeInicial, String idLocalidadeFinal, String periodoInicial, 
			String periodoFinal, String descLocalidadeInicial, String descLocalidadeFinal, 
			String idGerenciaRegional, String idUnidadeNegocios, String situacaoOS, String idOSReferidaRetornoTipo, 
			String aceitacaoDaOS, SistemaParametro sistemaParametro, Fachada fachada) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		//Imagem do relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Insere os parâmetros na hash de retorno
		if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
			parametros.put("localidadeInicial",descLocalidadeInicial);
		}
		if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
			parametros.put("localidadeFinal",descLocalidadeFinal);
		}
		
		parametros.put("periodoInicial",Util.formatarMesAnoParaData(periodoInicial,"01","00:00:00"));
		String[] mesAnoArray = periodoFinal.split("/");
		String ultimoDiaMEs = Util.obterUltimoDiaMes(Integer.parseInt(mesAnoArray[0]),Integer.parseInt(mesAnoArray[1]));
		parametros.put("periodoFinal",Util.formatarMesAnoParaData(periodoFinal,ultimoDiaMEs,"23:59:59"));
		
		if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
			FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
			filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, idGerenciaRegional));
			Collection<GerenciaRegional> collGerenciaRegional = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			GerenciaRegional gerencia = (GerenciaRegional) collGerenciaRegional.iterator().next();
			parametros.put("gerenciaRegional",gerencia.getNome());
		}else{
			parametros.put("gerenciaRegional","");
		}
		
		if(idUnidadeNegocios != null && !idUnidadeNegocios.equals("")){
			FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocios));
			Collection<UnidadeNegocio> collUnidadeNegocio = fachada.pesquisar(filtroUnidade, UnidadeNegocio.class.getName());
			UnidadeNegocio unidade = (UnidadeNegocio) collUnidadeNegocio.iterator().next();
			parametros.put("unidadeNegocios",unidade.getNome());
		}else{
			parametros.put("unidadeNegocios","");
		}
		
		if(idOSReferidaRetornoTipo != null && !idOSReferidaRetornoTipo.equals("")){
			FiltroOSReferidaRetornoTipo filtroRetornoTipo = new FiltroOSReferidaRetornoTipo();
			filtroRetornoTipo.adicionarParametro(new ParametroSimples(FiltroOSReferidaRetornoTipo.ID, idOSReferidaRetornoTipo));
			Collection<OsReferidaRetornoTipo> collTipoRetorno = fachada.pesquisar(filtroRetornoTipo, OsReferidaRetornoTipo.class.getName());
			OsReferidaRetornoTipo retornoTipo = (OsReferidaRetornoTipo) collTipoRetorno.iterator().next();
			parametros.put("tipoRetorno",retornoTipo.getDescricao());
		}else{
			parametros.put("tipoRetorno","");
		}
		
		if(situacaoOS != null && !situacaoOS.equals("")){
			if(situacaoOS.equals("-1")){
				parametros.put("situacao","TODOS");
			}else if(situacaoOS.equals("1")){
				parametros.put("situacao","PENDENTES");
			}else if(situacaoOS.equals("2")){
				parametros.put("situacao","ENCERRADOS");
			}
		}else{
			parametros.put("situacao","");
		}
		
		if(aceitacaoDaOS != null && !aceitacaoDaOS.equals("") && aceitacaoDaOS.equals("1")){
			parametros.put("aceitacao","SIM");
		}else if(aceitacaoDaOS != null && !aceitacaoDaOS.equals("") && aceitacaoDaOS.equals("2")){
			parametros.put("aceitacao","NÃO");
		}else if(aceitacaoDaOS != null && !aceitacaoDaOS.equals("") && aceitacaoDaOS.equals("3")){
			parametros.put("aceitacao","TODOS");
		}
		
		//retorna ao método principal
		return parametros;
		
	}
	
	/**
	 * 
	 * Método auxiliar para montar os dados do relatório analitico
	 * 
	 * @author Paulo Diniz
	 * @data 02/08/2011
	 */
	private RelatorioDataSource montarBeansAnalitico(Collection<OrdemServico> colecaoOrdemServico, Fachada fachada) {
	
		//lista de retorno
		ArrayList<RelatorioOrdemServicoFiscalizacaoAnaliticoBean> beans = new ArrayList<RelatorioOrdemServicoFiscalizacaoAnaliticoBean>();
		
		//Iteração com cada imóvel retornado pelo filtro
		Iterator<OrdemServico> it = colecaoOrdemServico.iterator();
		while(it.hasNext()){
			OrdemServico ordemServico = (OrdemServico) it.next();
			OrdemServico ordemFiscalidada = fachada.pesquisarOrdemServicoFiscalizada(ordemServico.getOsReferencia().getId());
			
			RelatorioOrdemServicoFiscalizacaoAnaliticoBean bean = new RelatorioOrdemServicoFiscalizacaoAnaliticoBean();
			
			if(ordemFiscalidada.getImovel() != null){
				bean.setGerenciaRegional(ordemFiscalidada.getImovel().getLocalidade().getUnidadeNegocio().getGerenciaRegional().getNome());
				bean.setUnidadeNegocio(ordemFiscalidada.getImovel().getLocalidade().getUnidadeNegocio().getNome());
				bean.setLocalidade(ordemFiscalidada.getImovel().getLocalidade().getDescricao());
			}else{
				bean.setGerenciaRegional(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getUnidadeNegocio().getGerenciaRegional().getNome());
				bean.setUnidadeNegocio(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getUnidadeNegocio().getNome());
				bean.setLocalidade(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getDescricao());
			}
			
			String mes = Util.getMes(ordemServico.getDataGeracao())+"";
			if(Util.getMes(ordemServico.getDataGeracao()) < 10){
				mes = "0"+mes;
			}
			bean.setMesAnoReferencia(mes +"/" +Util.getAno(ordemServico.getDataGeracao()));
			bean.setQuantidade(new BigDecimal("1"));
		
			if(ordemServico.getOsReferidaRetornoTipo() != null && ordemServico.getOsReferidaRetornoTipo().getIndicadorDeferimento() == 1){
				bean.setAceitacaoFiscalizada("Sim");
			}else if(ordemServico.getOsReferidaRetornoTipo() != null && ordemServico.getOsReferidaRetornoTipo().getIndicadorDeferimento() == 2){
				bean.setAceitacaoFiscalizada("Não");
			}else{
				bean.setAceitacaoFiscalizada("");
			}
			
			if(ordemServico.getDataGeracao() != null){
				bean.setDataGeracaoFiscalizacao(Util.formatarData(ordemServico.getDataGeracao()));
			}else{
				bean.setDataGeracaoFiscalizacao("");
			}
			
			if(ordemServico.getAtendimentoMotivoEncerramento() != null){
				bean.setMotivoEncerFiscalizacao(ordemServico.getAtendimentoMotivoEncerramento().getDescricao());
			}else{
				bean.setMotivoEncerFiscalizacao("");
			}
			
			if(ordemServico.getDataEncerramento() != null){
				bean.setDataEncerramentoFiscalizacao(Util.formatarData(ordemServico.getDataEncerramento()));
			}else{
				bean.setDataEncerramentoFiscalizacao("");
			}
			
			if(ordemServico.getOsReferidaRetornoTipo() != null){
				bean.setTipoRetornoFiscalizacao(ordemServico.getOsReferidaRetornoTipo().getDescricao());
			}else{
				bean.setTipoRetornoFiscalizacao("");
			}
			bean.setIdFiscalizacao(ordemServico.getId().intValue()+"");
			
			bean.setIdFiscalizada(ordemFiscalidada.getId().intValue()+"");
			if(ordemFiscalidada.getImovel() != null){
				
				ClienteImovel clienteImovel = fachada.pesquisarClienteImovelOSFiscalizada(ordemFiscalidada.getImovel().getId());
				if(clienteImovel != null && clienteImovel.getCliente() != null){
					bean.setClienteFiscalizada(clienteImovel.getCliente().getNome());
				}else{
					bean.setClienteFiscalizada("");
				}
				
				bean.setImovelFiscalizacao(ordemFiscalidada.getImovel().getMatriculaFormatada());
				
			}else{
				bean.setClienteFiscalizada("");
				bean.setImovelFiscalizacao("");
			}
			
			if(ordemFiscalidada.getAtendimentoMotivoEncerramento() != null){
				bean.setMotivoEncerFiscalizada(ordemFiscalidada.getAtendimentoMotivoEncerramento().getDescricao());
			}else{
				bean.setMotivoEncerFiscalizada("");
			}
			

			if(ordemFiscalidada.getOsReferidaRetornoTipo() != null){
				bean.setTipoRetornoFiscalizada(ordemFiscalidada.getOsReferidaRetornoTipo().getDescricao());
			}else{
				bean.setTipoRetornoFiscalizada("");
			}
			
			if(ordemFiscalidada.getServicoTipo() != null){
				bean.setTipoServicoFiscalizada(ordemFiscalidada.getServicoTipo().getDescricao());
			}else{
				bean.setTipoServicoFiscalizada("");
			}
			
			if(ordemFiscalidada.getDataEncerramento() != null){
				bean.setDataExecucaoFiscalizada(Util.formatarData(ordemFiscalidada.getDataEncerramento()));
			}else{
				bean.setDataExecucaoFiscalizada("");
			}
			
			beans.add(bean);
		}
		
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		return ds;
	}
	
	/**
	 * 
	 * Método auxiliar para montar o cabeçalho do relatório sintetico
	 * 
	 * @author Paulo Diniz
	 * @data 12/08/2011
	 */
	private Map<String, Object> montarCabecalhoSintetico(String idLocalidadeInicial, String idLocalidadeFinal, String periodoInicial, 
			String periodoFinal, String descLocalidadeInicial, String descLocalidadeFinal, 
			String idGerenciaRegional, String idUnidadeNegocios, SistemaParametro sistemaParametro, Fachada fachada) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		//Imagem do relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Insere os parâmetros na hash de retorno
		if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
			parametros.put("localidadeInicial",descLocalidadeInicial);
		}
		if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
			parametros.put("localidadeFinal",descLocalidadeFinal);
		}
		
		parametros.put("periodoInicial",Util.formatarMesAnoParaData(periodoInicial,"01","00:00:00"));
		String[] mesAnoArray = periodoFinal.split("/");
		String ultimoDiaMEs = Util.obterUltimoDiaMes(Integer.parseInt(mesAnoArray[0]),Integer.parseInt(mesAnoArray[1]));
		parametros.put("periodoFinal",Util.formatarMesAnoParaData(periodoFinal,ultimoDiaMEs,"23:59:59"));
		
		if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
			FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
			filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, idGerenciaRegional));
			Collection<GerenciaRegional> collGerenciaRegional = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			GerenciaRegional gerencia = (GerenciaRegional) collGerenciaRegional.iterator().next();
			parametros.put("gerenciaRegional",gerencia.getNome());
		}else{
			parametros.put("gerenciaRegional","");
		}
		
		if(idUnidadeNegocios != null && !idUnidadeNegocios.equals("")){
			FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocios));
			Collection<UnidadeNegocio> collUnidadeNegocio = fachada.pesquisar(filtroUnidade, UnidadeNegocio.class.getName());
			UnidadeNegocio unidade = (UnidadeNegocio) collUnidadeNegocio.iterator().next();
			parametros.put("unidadeNegocios",unidade.getNome());
		}else{
			parametros.put("unidadeNegocios","");
		}
		
		//retorna ao método principal
		return parametros;
		
	}
	
	/**
	 * 
	 * Método auxiliar para montar os dados do relatório analitico
	 * 
	 * @author Paulo Diniz, Mariana Victor
	 * @data 02/08/2011, 11/10/2011
	 */
	private ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean> montarBeansSintetico(Fachada fachada) {
	
		//lista de retorno
		ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean> beans = new ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean>();
		
		String idLocalidadeFinal = (String)getParametro("idLocalidadeFinal");
		String idLocalidadeInicial = (String)getParametro("idLocalidadeInicial");
		String periodoFinal = (String)getParametro("periodoFinal");
		String periodoInicial =(String)getParametro("periodoInicial");
		String idGerenciaRegional = (String)getParametro("idGerenciaRegional");
		String idUnidadeNegocios = (String)getParametro("idUnidadeNegocios");
		String situacaoOS = (String)getParametro("situacaoOS");
		String idOSReferidaRetornoTipo = (String)getParametro("idOSReferidaRetornoTipo");
		String aceitacaoDaOS = (String)getParametro("aceitacaoDaOS");
		
		Collection<Object[]> dadosRelatorioSintetico = fachada
				.pesquisarDadosRelatorioSinteticoOSFiscalizacao(
												periodoInicial, 
									    		periodoFinal,
									    		idGerenciaRegional, 
									    		idUnidadeNegocios, 
									    		idLocalidadeInicial, 
									    		idLocalidadeFinal, 
									    		situacaoOS, 
									    		idOSReferidaRetornoTipo, 
									    		aceitacaoDaOS);
		
		if (dadosRelatorioSintetico != null 
				&& !dadosRelatorioSintetico.isEmpty()) {
			
			Iterator<Object[]> iterator = dadosRelatorioSintetico.iterator();
			
			while(iterator.hasNext()) {
				Object[] dados = (Object[]) iterator.next();
				
				Date dataGeracao = (Date) dados[0];
				String gerenciaRegional = (String) dados[1];
				String unidadeNegocio = (String) dados[2];
				String localidade = (String) dados[3];
				Integer idLocalidade = (Integer) dados[4];
				
				Collection<Object[]> tiposRetorno = fachada
						.pesquisarTiposRetornoPorLocalidadeAnoMes(dataGeracao, idLocalidade);
				
				Collection<Object[]> motivosEncerramento = fachada
						.pesquisarMotivosEncerramentoPorLocalidadeAnoMes(dataGeracao, idLocalidade);
				
				if (tiposRetorno != null 
						&& !tiposRetorno.isEmpty()) {
					
					Iterator<Object[]> iteratorTiposRetorno = tiposRetorno.iterator();
					
					while(iteratorTiposRetorno.hasNext()) {
						Object[] dadosTipoRetorno = (Object[]) iteratorTiposRetorno.next();
						
						String descricao = (String) dadosTipoRetorno[0];
						Integer quantidade = (Integer) dadosTipoRetorno[1];
						
						RelatorioOrdemServicoFiscalizacaoSinteticoBean beanTipoRetorno = new RelatorioOrdemServicoFiscalizacaoSinteticoBean();
						beanTipoRetorno.setUnidadeNegocio(unidadeNegocio);
						beanTipoRetorno.setGerenciaRegional(gerenciaRegional);
						beanTipoRetorno.setLocalidade(localidade);
						
						beanTipoRetorno.setMesAnoReferencia(Util.formatarAnoMesParaMesAno(
							Util.getAnoMesComoString(dataGeracao)));
						beanTipoRetorno.setQuantidadeTipoRetorno(new BigDecimal(quantidade));
						
						beanTipoRetorno.setNomeTotal("OS de Fiscalização para os Tipos de Retorno:");
						beanTipoRetorno.setNomeColuna("Tipo de Retorno");
						
						beanTipoRetorno.setItemDescricao(descricao);
						
						beans.add(beanTipoRetorno);
						
					}
				}
				
				if (motivosEncerramento != null 
						&& !motivosEncerramento.isEmpty()) {
					
					Iterator<Object[]> iteratorMotivosEncerramento = motivosEncerramento.iterator();
					
					while(iteratorMotivosEncerramento.hasNext()) {
						Object[] dadosMotivoEncerramento = (Object[]) iteratorMotivosEncerramento.next();
						
						String descricao = (String) dadosMotivoEncerramento[0];
						Integer quantidade = (Integer) dadosMotivoEncerramento[1];
						
						RelatorioOrdemServicoFiscalizacaoSinteticoBean beanMotivoEnc = new RelatorioOrdemServicoFiscalizacaoSinteticoBean();
						beanMotivoEnc.setUnidadeNegocio(unidadeNegocio);
						beanMotivoEnc.setGerenciaRegional(gerenciaRegional);
						beanMotivoEnc.setLocalidade(localidade);
						
						beanMotivoEnc.setMesAnoReferencia(Util.formatarAnoMesParaMesAno(
							Util.getAnoMesComoString(dataGeracao)));
						beanMotivoEnc.setQuantidadeMotivo(new BigDecimal(quantidade));

						beanMotivoEnc.setNomeTotal("OS de Fiscalização para os Motivos de Encerramento:");
						beanMotivoEnc.setNomeColuna("Motivo de Encerramento");
						
						beanMotivoEnc.setItemDescricao(descricao);
						
						beans.add(beanMotivoEnc);
						
					}
				}
			}
		}
		
		//Collections.sort(beans, new ComparatorOrdemServicoFiscalizada());
		
		return beans;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {

		String idLocalidadeFinal = (String)getParametro("idLocalidadeFinal");
		String idLocalidadeInicial = (String)getParametro("idLocalidadeInicial");
		String periodoFinal = (String)getParametro("periodoFinal");
		String periodoInicial =(String)getParametro("periodoInicial");
		String idGerenciaRegional = (String)getParametro("idGerenciaRegional");
		String idUnidadeNegocio = (String)getParametro("idUnidadeNegocios");
		String situacaoOS = (String)getParametro("situacaoOS");
		String idOSReferidaRetornoTipo = (String)getParametro("idOSReferidaRetornoTipo");
		String aceitacaoDaOS = (String)getParametro("aceitacaoDaOS");
		
		Integer quantidade = Fachada.getInstancia().pesquisarQuantidadeOrdensServicoFiscalizacao(
			periodoInicial, periodoFinal, idGerenciaRegional, idUnidadeNegocio,
			idLocalidadeInicial, idLocalidadeFinal, situacaoOS, idOSReferidaRetornoTipo, aceitacaoDaOS);
		
		if(quantidade == null){
			return 0;
		}
		
		return quantidade;
	}

	public void agendarTarefaBatch() {
		
		AgendadorTarefas.agendarTarefa("RelatorioOrdensServicoFiscalizacaoAction", this);
		
	}
	
	
}
