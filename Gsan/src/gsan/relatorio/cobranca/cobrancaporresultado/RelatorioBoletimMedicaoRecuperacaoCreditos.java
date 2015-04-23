package gsan.relatorio.cobranca.cobrancaporresultado;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.bean.RelatorioBoletimMedicaoAcompanhamentoHelper;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioBoletimMedicaoRecuperacaoCreditos extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioBoletimMedicaoRecuperacaoCreditos(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_RECUPERACAO_CREDITOS);
		}

		@Deprecated
		public RelatorioBoletimMedicaoRecuperacaoCreditos() {
			super(null, "");
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
			
			List<RelatorioBoletimMedicaoRecuperacaoCreditosBean> relatorioBeans = montarRelatorio(colecaoBoletins,filtroLocalidade);
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_RECUPERACAO_CREDITOS,
			parametros, ds, tipoFormatoRelatorio);
			
			
			return retorno;
		}
	    

		private ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosBean> montarRelatorio(
				ArrayList<RelatorioBoletimMedicaoAcompanhamentoHelper> colecaoBoletins, Short filtroLocalidade) {
			
			ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosBean> retorno = 
					new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosBean>();
			
			List<RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean> colecaoSubRelatorioGerencia = 
					new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean>();
			
			List<RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean> colecaoSubRelatorioTotal = 
					new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean>();
			
			Iterator it = colecaoBoletins.iterator();
			int contador = 0;
			
			while(it.hasNext()){
				RelatorioBoletimMedicaoRecuperacaoCreditosBean bean = 
						new RelatorioBoletimMedicaoRecuperacaoCreditosBean();
				
				RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean subBean =
						new RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean();
				
				RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean subBeanTotalGeral =
						new RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean();
				
				
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
				
				if(helper.getValorDesconto() != null)
					bean.setValorDesconto(helper.getValorDesconto());
				else
					bean.setValorDesconto(new BigDecimal("0"));
				
				if(helper.getValorPagamentoAVista() != null)
					bean.setValorPagamentoAVista(helper.getValorPagamentoAVista());
				else
					bean.setValorPagamentoAVista(new BigDecimal("0"));
				
				if(helper.getValorPagamentoParcelado() != null)
					bean.setValorPagamentoParcelado(helper.getValorPagamentoParcelado());
				else
					bean.setValorPagamentoParcelado(new BigDecimal("0"));
				
				
				BigDecimal valorAVistaMenosDesconto = Util.subtrairBigDecimal(bean.getValorPagamentoAVista(), bean.getValorDesconto()); 
				
				bean.setValorPagamentoTotal(Util.somaBigDecimal(valorAVistaMenosDesconto, bean.getValorPagamentoParcelado()));
				bean.setPercentualFaixa(helper.getPercentualFaixa());
				bean.setValorPagamentoAVistaPagPrestadora((valorAVistaMenosDesconto.multiply(bean.getPercentualFaixa())).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_EVEN));
				bean.setValorPagamentoParceladoPagPrestadora((bean.getValorPagamentoParcelado().multiply(bean.getPercentualFaixa())).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_EVEN));
				bean.setValorPagamentoTotalPagPrestadora(Util.somaBigDecimal(bean.getValorPagamentoAVistaPagPrestadora(), bean.getValorPagamentoParceladoPagPrestadora()));
				
				//SubBean
				subBean.setIdFaixaContas(helper.getIdFaixaContas());
				subBean.setFaixaContas(bean.getFaixaContas());
				subBean.setQtdImoveis(bean.getQtdImoveis());
				subBean.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBean.setValorPagamentoAVista(bean.getValorPagamentoAVista());
				subBean.setValorPagamentoParcelado(bean.getValorPagamentoParcelado());
				subBean.setPercentualFaixa(bean.getPercentualFaixa());
				subBean.setValorPagamentoTotal(bean.getValorPagamentoTotal());
				subBean.setValorPagamentoAVistaPagPrestadora(bean.getValorPagamentoAVistaPagPrestadora());
				subBean.setValorPagamentoParceladoPagPrestadora(bean.getValorPagamentoParceladoPagPrestadora());
				subBean.setValorPagamentoTotalPagPrestadora(bean.getValorPagamentoTotalPagPrestadora());
				subBean.setValorDesconto(bean.getValorDesconto());
				
				//SubBean de totalização geral
				subBeanTotalGeral.setIdFaixaContas(helper.getIdFaixaContas());
				subBeanTotalGeral.setFaixaContas(bean.getFaixaContas());
				subBeanTotalGeral.setQtdImoveis(bean.getQtdImoveis());
				subBeanTotalGeral.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBeanTotalGeral.setValorPagamentoAVista(bean.getValorPagamentoAVista());
				subBeanTotalGeral.setValorPagamentoParcelado(bean.getValorPagamentoParcelado());
				subBeanTotalGeral.setPercentualFaixa(bean.getPercentualFaixa());
				subBeanTotalGeral.setValorPagamentoTotal(bean.getValorPagamentoTotal());
				subBeanTotalGeral.setValorPagamentoAVistaPagPrestadora(bean.getValorPagamentoAVistaPagPrestadora());
				subBeanTotalGeral.setValorPagamentoParceladoPagPrestadora(bean.getValorPagamentoParceladoPagPrestadora());
				subBeanTotalGeral.setValorPagamentoTotalPagPrestadora(bean.getValorPagamentoTotalPagPrestadora());
				subBeanTotalGeral.setValorDesconto(bean.getValorDesconto());
				
				
				
				if(filtroLocalidade == ConstantesSistema.SIM){
					if(helperNext == null || (helper.getGerenciaRegional().intValue() != helperNext.getGerenciaRegional().intValue())){
				
						subBean.setMsgTotal("Total Gerência Regional:");
						
						colecaoSubRelatorioGerencia.add(subBean);
						
						//Re-Ordenar pela faixa
						Collections.sort(colecaoSubRelatorioGerencia);
						
						bean.setArrayJRFaixasContasTotalGerencia(colecaoSubRelatorioGerencia);
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean>();
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
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean>();
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
