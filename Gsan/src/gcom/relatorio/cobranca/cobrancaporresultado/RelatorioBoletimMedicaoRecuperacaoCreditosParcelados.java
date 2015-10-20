package gcom.relatorio.cobranca.cobrancaporresultado;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.RelatorioBoletimMedicaoAcompanhamentoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioBoletimMedicaoRecuperacaoCreditosParcelados extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioBoletimMedicaoRecuperacaoCreditosParcelados(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_RECUPERACAO_CREDITOS_PARCELADOS);
		}
		
		public Object executar() throws TarefaException {

			

			ArrayList<RelatorioBoletimMedicaoAcompanhamentoHelper> colecaoBoletins = 
					(ArrayList<RelatorioBoletimMedicaoAcompanhamentoHelper> ) getParametro("colecaoBoletins");
			int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
			Short filtroLocalidade = (Short)getParametro("filtroLocalidade");
			String mesAno = (String)getParametro("mesAno");
			String encerramentoArrecadacao = (String)getParametro("encerramentoArrecadacao");

			// valor de retorno
			byte[] retorno = null;

			Fachada fachada = Fachada.getInstancia();

			// Parâmetros do relatório
			Map parametros = new HashMap();
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			
			//Recuperar o nome da empresa e numero do contrato
			RelatorioBoletimMedicaoAcompanhamentoHelper helper = 
					(RelatorioBoletimMedicaoAcompanhamentoHelper)Util.retonarObjetoDeColecao(colecaoBoletins);
			
			parametros.put("empresa", helper.getEmpresa());
			parametros.put("contrato", helper.getContrato());
			parametros.put("mesAno",mesAno);
			parametros.put("encerramentoArrecadacao", encerramentoArrecadacao);
			
			List<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosBean> relatorioBeans = montarRelatorio(colecaoBoletins,filtroLocalidade);
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_RECUPERACAO_CREDITOS_PARCELADOS,
			parametros, ds, tipoFormatoRelatorio);
			
			
			return retorno;
		}
	    

		private ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosBean> montarRelatorio(
				ArrayList<RelatorioBoletimMedicaoAcompanhamentoHelper> colecaoBoletins, Short filtroLocalidade) {
			
			ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosBean> retorno = 
					new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosBean>();
			
			List<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean> colecaoSubRelatorioGerencia = 
					new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean>();
			
			List<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean> colecaoSubRelatorioTotal = 
					new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean>();
			
			
			
			Iterator it = colecaoBoletins.iterator();
			int contador = 0;
			
			while(it.hasNext()){
				RelatorioBoletimMedicaoRecuperacaoCreditosParceladosBean bean = 
						new RelatorioBoletimMedicaoRecuperacaoCreditosParceladosBean();
				
				RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean subBean =
						new RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean();
				
				RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean subBeanTotalGeral =
						new RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean();
				
				
				RelatorioBoletimMedicaoAcompanhamentoHelper helper = 
						(RelatorioBoletimMedicaoAcompanhamentoHelper)it.next();
				
				RelatorioBoletimMedicaoAcompanhamentoHelper helperNext = null;
				if(contador != colecaoBoletins.size() - 1){
					helperNext = colecaoBoletins.get(contador + 1);
				}
				
				if(filtroLocalidade == ConstantesSistema.SIM){
					bean.setIdLocalidade(helper.getIdLocalidade());
					bean.setDescricaoLocalidade(helper.getDescricaoLocalidade());
					bean.setGerenciaRegional(helper.getNomeGerenciaRegional());
				}
				else{
					bean.setIdMunicipio(helper.getIdMunicipio());
					bean.setDescricaoMunicipio(helper.getDescricaoMunicipio());
					bean.setRegiao(helper.getNomeRegiao());
				}
				
				
				bean.setFaixaContas(helper.getDescricaoFaixaContas());
				bean.setQtdImoveis(helper.getQtdImoveis());
				bean.setQtdFaturasNegociadas(helper.getQtdFaturasNegociadas());
				bean.setValorNegociado(helper.getValorNegociado());
				
				
				if(helper.getValorPagamentoParcelado() != null)
					bean.setValorPagamentoParcelado(helper.getValorPagamentoParcelado());
				else
					bean.setValorPagamentoParcelado(new BigDecimal("0"));
				
				bean.setPorcentagemPagoPrestadora((bean.getValorPagamentoParcelado().multiply(helper.getPercentualFaixa())).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_EVEN));

				
				//SubBean
				subBean.setIdFaixaContas(helper.getIdFaixaContas());
				subBean.setFaixaContas(bean.getFaixaContas());
				subBean.setQtdImoveis(bean.getQtdImoveis());
				subBean.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBean.setValorNegociado(bean.getValorNegociado());
				subBean.setValorPagamentoParcelado(bean.getValorPagamentoParcelado());
				subBean.setPorcentagemPagoPrestadora(bean.getPorcentagemPagoPrestadora());
				
				
				//SubBean da totalização geral
				subBeanTotalGeral.setIdFaixaContas(helper.getIdFaixaContas());
				subBeanTotalGeral.setFaixaContas(bean.getFaixaContas());
				subBeanTotalGeral.setQtdImoveis(bean.getQtdImoveis());
				subBeanTotalGeral.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBeanTotalGeral.setValorNegociado(bean.getValorNegociado());
				subBeanTotalGeral.setValorPagamentoParcelado(bean.getValorPagamentoParcelado());
				subBeanTotalGeral.setPorcentagemPagoPrestadora(bean.getPorcentagemPagoPrestadora());
				
				
				
				if(filtroLocalidade == ConstantesSistema.SIM){
					if(helperNext == null || (helper.getGerenciaRegional().intValue() != helperNext.getGerenciaRegional().intValue())){
				
						subBean.setMsgTotal("Total Gerência Regional:");
						
						colecaoSubRelatorioGerencia.add(subBean);
						
						//Re-Ordenar pela faixa
						Collections.sort(colecaoSubRelatorioGerencia);
						
						bean.setArrayJRFaixasContasTotalGerencia(colecaoSubRelatorioGerencia);
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean>();
					}
					else{
						subBean.setMsgTotal("Total Gerência Regional:");	
						colecaoSubRelatorioGerencia.add(subBean);
					}
				
				}
				else{
					
					if(helperNext == null || (helper.getRegiao().intValue() != helperNext.getRegiao().intValue())){
	
						subBean.setMsgTotal("Total Região:");
						
						colecaoSubRelatorioGerencia.add(subBean);
						
						//Re-Ordenar pela faixa
						Collections.sort(colecaoSubRelatorioGerencia);
						
						bean.setArrayJRFaixasContasTotalGerencia(colecaoSubRelatorioGerencia);
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean>();
					}
					else{
						subBean.setMsgTotal("Total Região:");
						colecaoSubRelatorioGerencia.add(subBean);
					}
				}
				
				subBeanTotalGeral.setMsgTotal("Total Geral: ");
				colecaoSubRelatorioTotal.add(subBeanTotalGeral);
				
				if(contador + 1 == colecaoBoletins.size()){
					Collections.sort(colecaoSubRelatorioTotal);
					bean.setArrayJRFaixasContasTotal(colecaoSubRelatorioTotal);
				}
				
				contador++;
				retorno.add(bean);
				
			}
			
			return retorno;
		}

		@Override
		public int calcularTotalRegistrosRelatorio() {
			return 2;
		}

		public void agendarTarefaBatch() {
		}
}
