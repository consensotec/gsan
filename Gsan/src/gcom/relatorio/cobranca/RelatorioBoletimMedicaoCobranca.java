package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.BoletimMedicaoJustificativaPenalidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioBoletimMedicaoCobranca extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioBoletimMedicaoCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_COBRANCA);
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		FiltrarRelatorioBoletimMedicaoCobrancaHelper relatorioHelper = 
			(FiltrarRelatorioBoletimMedicaoCobrancaHelper) getParametro("filtrarRelatorioBoletimMedicaoCobrancaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Object[] dadosGerais = fachada.pesquisarDadosBoletimMedicaoCobranca(
				new Integer(relatorioHelper.getMesAnoReferencia()), relatorioHelper.getGrupoCobranca(), relatorioHelper.getIdContratoEmpresaServico());
		
		
		RelatorioBoletimMedicaoCobrancaBean relatorioBoletimMedicaoCobrancaBean = null;
		
		Collection<Integer> penalidades = fachada
				.pesquisarNaoPenalidades(relatorioHelper.getGrupoCobranca(), Integer.parseInt(relatorioHelper.getMesAnoReferencia()),  relatorioHelper.getIdContratoEmpresaServico());
		ArrayList<RelatorioBoletimPenalidadeHelper> boletinsPenalidades = null;
		
		if (dadosGerais != null && dadosGerais[3] != null) {
			relatorioHelper.setIdBoletimMedicao((dadosGerais[3]).toString());
		}else{
			throw new ActionServletException("atencao.boletim.nao.existe");
		}
		
		Collection<RelatorioBoletimMedicaoCobrancaHelper>
			colecaoHelperExecutado = fachada.pesquisarItensServico(relatorioHelper, "executado");
		
		Collection<RelatorioBoletimMedicaoCobrancaHelper>
			colecaoHelperDesconto = fachada.pesquisarItensServico(relatorioHelper, "desconto");
		
		Collection<RelatorioBoletimMedicaoCobrancaHelper>
			colecaoHelperSucesso = fachada.pesquisarItensServico(relatorioHelper, "sucesso");
		
		Collection<RelatorioBoletimMedicaoCobrancaBean> colecaoBeanExecutado = this
			.inicializarBeanRelatorioExecutado(colecaoHelperExecutado, relatorioHelper);
		Collection<RelatorioBoletimMedicaoCobrancaBean> colecaoBeanDesconto = this
			.inicializarBeanRelatorioDesconto(colecaoHelperDesconto, relatorioHelper);
		Collection<RelatorioBoletimMedicaoCobrancaBean> colecaoBeanSucesso = this
			.inicializarBeanRelatorioSucesso(colecaoHelperSucesso);
		
		List<RelatorioBoletimMedicaoCobrancaBean> colecaoBean = new ArrayList();
		
		if (colecaoBeanExecutado != null && !colecaoBeanExecutado.isEmpty()) {
			colecaoBean.addAll(colecaoBeanExecutado);
		}
		if (colecaoBeanDesconto != null && !colecaoBeanDesconto.isEmpty()) {
			colecaoBean.addAll(colecaoBeanDesconto);
		}
		if (colecaoBeanSucesso != null && !colecaoBeanSucesso.isEmpty()) {
			colecaoBean.addAll(colecaoBeanSucesso);
		}
			
		if (colecaoBean == null || colecaoBean.isEmpty()) {
			
			relatorioBoletimMedicaoCobrancaBean = 
				new RelatorioBoletimMedicaoCobrancaBean();
			
			relatorioBeans.add(relatorioBoletimMedicaoCobrancaBean);
			
		} else {
			
			if(penalidades !=null && !penalidades.isEmpty()){
				for(Integer penalidade : penalidades){
					BoletimMedicaoJustificativaPenalidade bol = fachada.pesquisarBoletimMedicaoJustificativaPenalidade(penalidade);
					if(bol != null){
						if(boletinsPenalidades == null){
							boletinsPenalidades = new ArrayList<RelatorioBoletimPenalidadeHelper>();
							RelatorioBoletimPenalidadeHelper boletimPenalidadeHelper = new RelatorioBoletimPenalidadeHelper();
							boletimPenalidadeHelper.setAcao(bol.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao());
							boletimPenalidadeHelper.setJustificativa(bol.getJustificativa());
							boletinsPenalidades.add(boletimPenalidadeHelper);
						}
						else{
							RelatorioBoletimPenalidadeHelper boletimPenalidadeHelper = new RelatorioBoletimPenalidadeHelper();
							boletimPenalidadeHelper.setAcao(bol.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao());
							boletimPenalidadeHelper.setJustificativa(bol.getJustificativa());
							boletinsPenalidades.add(boletimPenalidadeHelper);
						}
					}
				}
			}
			if(boletinsPenalidades !=null && !boletinsPenalidades.isEmpty()){
				//Adiciona bletinsPenalidades no ultimo objeto da colecao;
				colecaoBean.get(colecaoBean.size()-1).setArrayRelatorioBoletimPenalidades(boletinsPenalidades);
			}
			
			relatorioBeans = colecaoBean;
		}
		
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if (dadosGerais != null && dadosGerais[0] != null) {
			parametros.put("empresa", dadosGerais[0].toString());
		}
		
		if (dadosGerais != null && dadosGerais[1] != null) {
			parametros.put("numeroContrato", dadosGerais[1].toString());
		}
		
		if (dadosGerais != null && dadosGerais[2] != null) {
			parametros.put("valorTotal", dadosGerais[2]);
		}
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if(relatorioHelper.getNomeGrupoCobranca() != null && !relatorioHelper.getNomeGrupoCobranca().equals("")){
			parametros.put("grupoCobranca", relatorioHelper.getNomeGrupoCobranca());
		}	
		
		parametros.put("mesAnoCronograma", Util.formatarAnoMesParaMesAno(relatorioHelper.getMesAnoReferencia()));
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(colecaoBean != null && colecaoBean.size() > 0){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_COBRANCA,
					parametros, ds, tipoFormatoRelatorio);
		} else {
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
			
		// retorna o relatório gerado
		return retorno;
	}

	private Collection<RelatorioBoletimMedicaoCobrancaBean> inicializarBeanRelatorioExecutado(
			Collection<RelatorioBoletimMedicaoCobrancaHelper> colecaoHelper, FiltrarRelatorioBoletimMedicaoCobrancaHelper relatorioHelper) {
		
		Collection<RelatorioBoletimMedicaoCobrancaBean> colecaoBean = new ArrayList();
		
		if (colecaoHelper != null && !colecaoHelper.isEmpty()) {
			
			Iterator iterator = colecaoHelper.iterator();
			
			while (iterator.hasNext()) {
				RelatorioBoletimMedicaoCobrancaHelper helper = (RelatorioBoletimMedicaoCobrancaHelper) iterator.next();
				
				RelatorioBoletimMedicaoCobrancaBean bean = new RelatorioBoletimMedicaoCobrancaBean();
				
				Object[] valores = null;
				BigDecimal valorUnitario = BigDecimal.ZERO; 
				BigDecimal valorItem = BigDecimal.ZERO;
				String quantidade = "";
				String unidade = "";				
				
				switch (helper.getItemServico().getCodigoConstanteCalculo()) {
				
					case 15: case 16: case 17:
						valores = Fachada.getInstancia().obterSomatorioOSBoletimMedicaoCobranca(helper);
						if (valores != null) {
							if (valores[0] != null) {
								quantidade = String.valueOf((BigDecimal) valores[0]);
								//quantidade = (BigDecimal) valores[0];
							}
							if (valores[1] != null) {
								valorUnitario = (BigDecimal) valores[1];
							}
							if (valores[0] != null && valores[1] != null) {
								valorItem = new BigDecimal(quantidade).multiply(valorUnitario);
							}

							if (valores[0] != null
									&& valores[1] != null) {
								unidade = "M²";
							}
						}
						
						break;
	
					default:
						valores = Fachada.getInstancia().obterQuantidadeOSBoletimMedicaoCobranca(helper);
						if (valores != null) {
							if (valores[0] != null) {
								quantidade = String.valueOf(new BigDecimal((Integer) valores[0]).intValue());
								//quantidade = new BigDecimal((Integer) valores[0]);
							}
							if (valores[1] != null) {
								valorUnitario = (BigDecimal) valores[1];
							}
							if (valores[0] != null && valores[1] != null) {
								valorItem = new BigDecimal(quantidade).multiply(valorUnitario);
							}
	
							if (valores[0] != null
									&& valores[1] != null) {
								unidade = "UND";
							}
						}
					break;
				}
				
				if (valorUnitario.compareTo(BigDecimal.ZERO) != 0
						|| valorItem.compareTo(BigDecimal.ZERO) != 0
						|| !quantidade.equals("")) {
					bean.setItemDescricao(helper.getItemServico().getDescricao());
					bean.setItemCodigo(helper.getItemServico().getCodigoItem().toString());
					bean.setGerenciaRegional(helper.getNomeGerenciaRegional());
					bean.setLocalidade(helper.getNomeLocalidade());
					bean.setUnidadeNegocio(helper.getNomeUnidadeNegocio());
					bean.setDataGeracao(Util.formatarData(helper.getDataGeracao()));
					bean.setQuantidade(quantidade);
					bean.setValorUnitario(valorUnitario);
					bean.setValorItem(valorItem);
					bean.setColuna01(unidade);
					bean.setTotalizacao("Serviços Executados");
					bean.setNomeColuna01("Unidade do Item");
					bean.setNomeColuna02("Valor Unitário");
					bean.setNomeColuna03("Valor Item");
					
					colecaoBean.add(bean);
				
				}
			}
		}
		
		return colecaoBean;
	}

	private Collection<RelatorioBoletimMedicaoCobrancaBean> inicializarBeanRelatorioDesconto(
			Collection<RelatorioBoletimMedicaoCobrancaHelper> colecaoHelper, FiltrarRelatorioBoletimMedicaoCobrancaHelper relatorioHelper) {
		
		Collection<RelatorioBoletimMedicaoCobrancaBean> colecaoBean = new ArrayList();
		
		if (colecaoHelper != null && !colecaoHelper.isEmpty()) {
			
			Iterator iterator = colecaoHelper.iterator();
			
			while (iterator.hasNext()) {
				RelatorioBoletimMedicaoCobrancaHelper helper = (RelatorioBoletimMedicaoCobrancaHelper) iterator.next();
				
				RelatorioBoletimMedicaoCobrancaBean bean = new RelatorioBoletimMedicaoCobrancaBean();
				
				Object[] valores = null;
				BigDecimal valorUnitario = BigDecimal.ZERO, valorItem = BigDecimal.ZERO, quantidade = BigDecimal.ZERO;
				String unidade = "";
				
				switch (helper.getItemServico().getCodigoConstanteCalculo()) {
				
					case 1: case 2: case 5:
						valores = Fachada.getInstancia().obterQuantidadeOSBoletimMedicaoCobrancaDesconto(helper);
						break;
	
					default:
						break;
				}
		
				if (valores != null) {
					if (valores[0] != null) {
						quantidade = new BigDecimal((Integer) valores[0]);
					}
					if (valores[1] != null) {
						valorUnitario = (BigDecimal) valores[1];
					}
					if (valores[0] != null && valores[1] != null) {
						valorItem = valorItem.subtract(quantidade.multiply(valorUnitario));
					}

					if (valores[0] != null
							&& valores[1] != null) {
						unidade = "UND";
					}
				}

		
				if (valorUnitario.compareTo(BigDecimal.ZERO) != 0
						|| valorItem.compareTo(BigDecimal.ZERO) != 0
						|| quantidade.compareTo(BigDecimal.ZERO) != 0) {
					
					bean.setItemDescricao(helper.getItemServico().getDescricao());
					bean.setItemCodigo(helper.getItemServico().getCodigoItem().toString());
					bean.setGerenciaRegional(helper.getNomeGerenciaRegional());
					bean.setLocalidade(helper.getNomeLocalidade());
					bean.setUnidadeNegocio(helper.getNomeUnidadeNegocio());
					bean.setDataGeracao(Util.formatarData(helper.getDataGeracao()));
					bean.setQuantidade(String.valueOf(quantidade));
					bean.setValorUnitario(valorUnitario);
					bean.setValorItem(valorItem);
					bean.setColuna01(unidade);
					bean.setTotalizacao("Descontos Efetuados");
					bean.setNomeColuna01("Unidade do Item");
					bean.setNomeColuna02("Valor Unitário");
					bean.setNomeColuna03("Valor Item");
					
					colecaoBean.add(bean);
					
				}
				
			}
		}
		
		return colecaoBean;
	}

	private Collection<RelatorioBoletimMedicaoCobrancaBean> inicializarBeanRelatorioSucesso(
			Collection<RelatorioBoletimMedicaoCobrancaHelper> colecaoHelper) {
		
		Collection<RelatorioBoletimMedicaoCobrancaBean> colecaoBean = new ArrayList();
		
		if (colecaoHelper != null && !colecaoHelper.isEmpty()) {

			Iterator iterator = colecaoHelper.iterator();
			
			while (iterator.hasNext()) {
				RelatorioBoletimMedicaoCobrancaHelper helper = (RelatorioBoletimMedicaoCobrancaHelper) iterator.next();
					
				RelatorioBoletimMedicaoCobrancaBean bean = new RelatorioBoletimMedicaoCobrancaBean();
	
				Object[] valores = Fachada.getInstancia().obterTotalizacaoOSBoletimMedicaoCobrancaSucesso(helper);
				
				
				BigDecimal percentual = BigDecimal.ZERO, valorTotal = BigDecimal.ZERO, valorCalculado = BigDecimal.ZERO, quantidade = BigDecimal.ZERO;
				
				if (valores != null) {
					if (valores[0] != null) {
						quantidade = new BigDecimal((Integer) valores[0]);
					}
					if (valores[1] != null) {
						percentual = (BigDecimal) valores[1];
					}
					if (valores[2] != null) {
						valorTotal = (BigDecimal) valores[2];
					}
					if (valores[3] != null) {
						valorCalculado = (BigDecimal) valores[3];
					}
				}
				
				if (percentual.compareTo(BigDecimal.ZERO) != 0
						|| valorTotal.compareTo(BigDecimal.ZERO) != 0
						|| valorCalculado.compareTo(BigDecimal.ZERO) != 0
						|| quantidade.compareTo(BigDecimal.ZERO) != 0) {
					
					bean.setItemDescricao("Taxa de Sucesso");
					bean.setItemCodigo(null);
					bean.setGerenciaRegional(helper.getNomeGerenciaRegional());
					bean.setLocalidade(helper.getNomeLocalidade());
					bean.setUnidadeNegocio(helper.getNomeUnidadeNegocio());
					bean.setDataGeracao(Util.formatarData(helper.getDataGeracao()));
					bean.setQuantidade(String.valueOf(quantidade));
					bean.setColuna01(Util.formatarBigDecimalParaStringComVirgula(percentual));
					bean.setValorUnitario(valorTotal);
					bean.setValorItem(valorCalculado);
					bean.setTotalizacao("Taxa de Sucesso");
					bean.setNomeColuna01("Percentual a ser aplicado");
					bean.setNomeColuna02("Valor Recuperado");
					bean.setNomeColuna03("Valor Calculado");
					
					colecaoBean.add(bean);
					
				}
			}
		}
		
		return colecaoBean;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 2;
   
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioBoletimMedicaoCobranca", this);
	}

}
