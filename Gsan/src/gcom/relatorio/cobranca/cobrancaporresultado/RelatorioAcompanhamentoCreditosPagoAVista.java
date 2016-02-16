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

public class RelatorioAcompanhamentoCreditosPagoAVista extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioAcompanhamentoCreditosPagoAVista(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CREDITOS_PAGOS_A_VISTA);
		}

		@Deprecated
		public RelatorioAcompanhamentoCreditosPagoAVista() {
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
			
			List<RelatorioAcompanhamentoCreditosPagosAVistaBean> relatorioBeans = montarRelatorio(colecaoBoletins,filtroLocalidade);
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CREDITOS_PAGOS_A_VISTA,
			parametros, ds, tipoFormatoRelatorio);
			
			
			return retorno;
		}
	    

		private ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaBean> montarRelatorio(
				ArrayList<RelatorioBoletimMedicaoAcompanhamentoHelper> colecaoBoletins, Short filtroLocalidade) {
			
			ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaBean> retorno = 
					new ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaBean>();
			
			Iterator it = colecaoBoletins.iterator();
			int contador = 0;
			
			List<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean> colecaoSubRelatorio = 
					new ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean>();
			
			List<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean> colecaoSubRelatorioGerencia = 
					new ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean>();
			
			
			List<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean> colecaoSubRelatorioTotalGeral = 
					new ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean>();
			
			while(it.hasNext()){
				RelatorioAcompanhamentoCreditosPagosAVistaBean bean = 
						new RelatorioAcompanhamentoCreditosPagosAVistaBean();
				
				RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean subBean =
						new RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean();
				
				RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean subBeanTotalGeral =
						new RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean();
				
				RelatorioBoletimMedicaoAcompanhamentoHelper helper = 
						(RelatorioBoletimMedicaoAcompanhamentoHelper)it.next();
				
				RelatorioBoletimMedicaoAcompanhamentoHelper helperNext = null;
				if(contador != colecaoBoletins.size() - 1){
					helperNext = colecaoBoletins.get(contador + 1);
				}
				
				
				//Montando Bean
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

				bean.setIdImovel(helper.getIdImovel());
				bean.setNomeCliente(helper.getNomeCLiente());
				bean.setQtdFaturasNegociadas(helper.getQtdFaturasNegociadas());
				bean.setValorNegociado(helper.getValorNegociado());
				bean.setValorPago(helper.getValorPagamentoAVista());
				
				if(helper.getValorDesconto() != null)
					bean.setValorDesconto(helper.getValorDesconto());
				else
					bean.setValorDesconto(new BigDecimal("0"));

				
				//Montando SubBean
				//subBean.setFaixaContas(helper.getDescricaoFaixaContas());
				subBean.setIdFaixaConta(helper.getIdFaixaContas());
				subBean.setFaixaContas(helper.getDescricaoFaixaContas());
				subBean.setQtdFaturasNegociadas(helper.getQtdFaturasNegociadas());
				subBean.setValorNegociado(helper.getValorNegociado());
				subBean.setValorPago(helper.getValorPagamentoAVista());
				subBean.setGerenciaRegional(bean.getGerenciaRegional());
				subBean.setRegiao(bean.getRegiao());
				subBean.setValorDesconto(bean.getValorDesconto());
				
				subBeanTotalGeral.setIdFaixaConta(helper.getIdFaixaContas());
				subBeanTotalGeral.setFaixaContas(helper.getDescricaoFaixaContas());
				subBeanTotalGeral.setQtdFaturasNegociadas(helper.getQtdFaturasNegociadas());
				subBeanTotalGeral.setValorNegociado(helper.getValorNegociado());
				subBeanTotalGeral.setValorPago(helper.getValorPagamentoAVista());
				subBeanTotalGeral.setGerenciaRegional(bean.getGerenciaRegional());
				subBeanTotalGeral.setRegiao(bean.getRegiao());
				subBeanTotalGeral.setValorDesconto(bean.getValorDesconto());
				
				
				if(filtroLocalidade == ConstantesSistema.SIM){
					if(helperNext == null || (helper.getIdLocalidade().intValue() != helperNext.getIdLocalidade().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						colecaoSubRelatorio.add(subBean);
						bean.setArrayJRFaixasContasTotal(colecaoSubRelatorio);
						colecaoSubRelatorio = new ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean>();
					}
					
					else{
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_DESATIVO);
						colecaoSubRelatorio.add(subBean);
					}
					
					
					
					if(helperNext == null || (helper.getGerenciaRegional().intValue() != helperNext.getGerenciaRegional().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						
						subBean.setMsgTotal("Total Gerência Regional:");	
						
						colecaoSubRelatorioGerencia.add(subBean);
						
						//Re-Ordenar pela faixa
						Collections.sort(colecaoSubRelatorioGerencia);
						
						bean.setArrayJRFaixasContasTotalGerencia(colecaoSubRelatorioGerencia);
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean>();
					}
					else{
						subBean.setMsgTotal("Total Gerência Regional:");
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_DESATIVO);
						colecaoSubRelatorioGerencia.add(subBean);
					}
				
				}
				else{
					if(helperNext == null || (helper.getIdMunicipio().intValue() != helperNext.getIdMunicipio().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						colecaoSubRelatorio.add(subBean);
						bean.setArrayJRFaixasContasTotal(colecaoSubRelatorio);
						colecaoSubRelatorio = new ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean>();
					}
					
					else{
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_DESATIVO);
						colecaoSubRelatorio.add(subBean);
					}
					
					
					
					if(helperNext == null || (helper.getRegiao().intValue() != helperNext.getRegiao().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						
						subBean.setMsgTotal("Total Região:");
						
						colecaoSubRelatorioGerencia.add(subBean);
						
						//Re-Ordenar pela faixa
						Collections.sort(colecaoSubRelatorioGerencia);
						
						bean.setArrayJRFaixasContasTotalGerencia(colecaoSubRelatorioGerencia);
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioAcompanhamentoCreditosPagosAVistaSubTotalBean>();
					}
					else{
						subBean.setMsgTotal("Total Região:");
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_DESATIVO);
						colecaoSubRelatorioGerencia.add(subBean);
					}
				}
				
				
				subBeanTotalGeral.setMsgTotal("Total Geral: ");
				colecaoSubRelatorioTotalGeral.add(subBeanTotalGeral);
				
				if(contador + 1 == colecaoBoletins.size()){
					Collections.sort(colecaoSubRelatorioTotalGeral);
					bean.setArrayJRFaixasContasTotalGeral(colecaoSubRelatorioTotalGeral);
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
