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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioAcompanhamentoParcelamentosRecuperacaoCreditos extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioAcompanhamentoParcelamentosRecuperacaoCreditos(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_PARCELAMENTOS_RECUPERACAO_CREDITOS);
		}

		@Deprecated
		public RelatorioAcompanhamentoParcelamentosRecuperacaoCreditos() {
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
			
			List<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosBean> relatorioBeans = montarRelatorio(colecaoBoletins,filtroLocalidade,fachada);
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_PARCELAMENTOS_RECUPERACAO_CREDITOS,
			parametros, ds, tipoFormatoRelatorio);
			
			
			return retorno;
		}
	    

		private ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosBean> montarRelatorio(
				ArrayList<RelatorioBoletimMedicaoAcompanhamentoHelper> colecaoBoletins, Short filtroLocalidade, Fachada fachada) {
			
			ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosBean> retorno = 
					new ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosBean>();
			
			Iterator it = colecaoBoletins.iterator();
			int contador = 0;
			
			List<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean> colecaoSubRelatorio = 
					new ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean>();
			
			List<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean> colecaoSubRelatorioGerencia = 
					new ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean>();
			
			List<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean> colecaoSubRelatorioTotalGeral = 
					new ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean>();
			
			while(it.hasNext()){
				RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosBean bean = 
						new RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosBean();
				
				RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean subBean =
						new RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean();
				
				RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean subBeanGerencia =
						new RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean();
				
				RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean subBeanTotalGeral =
						new RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean();
				
				
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
				
				
				bean.setQtdParcelasPagas(helper.getParcelasPaga());
				bean.setValorPago(helper.getValorPago());
				
				if(helper.getParcelasPaga() == null)
					helper.setParcelasPaga(new Integer(0));
				if(helper.getQuantidadeParcelasEmAberto() == null)
					helper.setQuantidadeParcelasEmAberto(new Integer(0));
				if(helper.getQuantidadeParcelasEmAbertoHist() == null)
					helper.setQuantidadeParcelasEmAbertoHist(new Integer(0));
				if(helper.getSaldoEmAberto() == null)
					helper.setSaldoEmAberto(new BigDecimal(0));
				if(helper.getSaldoEmAbertoHist() == null)
					helper.setSaldoEmAbertoHist(new BigDecimal(0));
				if(helper.getValorPago() == null)
					helper.setValorPago(new BigDecimal(0));
				
				
				bean.setQtdParcelasAberto(new Integer(helper.getQuantidadeParcelasEmAberto().intValue() + helper.getQuantidadeParcelasEmAbertoHist().intValue() - helper.getParcelasPaga().intValue()));
				bean.setSaldoAberto(helper.getSaldoEmAberto().add(helper.getSaldoEmAbertoHist()).subtract(helper.getValorPago()));
				
				
				//Montando SubBean
				subBean.setIdFaixaContas(helper.getIdFaixaContas());
				subBean.setFaixaContas(helper.getDescricaoFaixaContas());
				subBean.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBean.setValorNegociado(bean.getValorNegociado());
				subBean.setValorPago(bean.getValorPago());
				subBean.setSaldoAberto(bean.getSaldoAberto());
				subBean.setQtdParcelas(bean.getQtdParcelasPagas());
				subBean.setQtdParcelasAberto(bean.getQtdParcelasAberto());
				
				//Montando SubBean do agrupamento de gerência/região
				subBeanGerencia.setIdFaixaContas(helper.getIdFaixaContas());
				subBeanGerencia.setFaixaContas(helper.getDescricaoFaixaContas());
				subBeanGerencia.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBeanGerencia.setValorNegociado(bean.getValorNegociado());
				subBeanGerencia.setValorPago(bean.getValorPago());
				subBeanGerencia.setSaldoAberto(bean.getSaldoAberto());
				subBeanGerencia.setQtdParcelas(bean.getQtdParcelasPagas());
				subBeanGerencia.setQtdParcelasAberto(bean.getQtdParcelasAberto());
				
				//Montando SubBean do agrupamento do total geral
				subBeanTotalGeral.setIdFaixaContas(helper.getIdFaixaContas());
				subBeanTotalGeral.setFaixaContas(helper.getDescricaoFaixaContas());
				subBeanTotalGeral.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBeanTotalGeral.setValorNegociado(bean.getValorNegociado());
				subBeanTotalGeral.setValorPago(bean.getValorPago());
				subBeanTotalGeral.setSaldoAberto(bean.getSaldoAberto());
				subBeanTotalGeral.setQtdParcelas(bean.getQtdParcelasPagas());
				subBeanTotalGeral.setQtdParcelasAberto(bean.getQtdParcelasAberto());
				
				
				if(filtroLocalidade == ConstantesSistema.SIM){
					if(helperNext == null || (helper.getIdLocalidade().intValue() != helperNext.getIdLocalidade().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						subBean.setMsgTotal("Total Localidade: ");
						colecaoSubRelatorio.add(subBean);
						bean.setArrayJRFaixasContasTotal(colecaoSubRelatorio);
						colecaoSubRelatorio = new ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean>();
					}
					
					else{
						subBean.setMsgTotal("Total Localidade: ");
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_DESATIVO);
						colecaoSubRelatorio.add(subBean);
					}
					
					
					
					
					if(helperNext == null || (helper.getGerenciaRegional().intValue() != helperNext.getGerenciaRegional().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						
						subBeanGerencia.setMsgTotal("Total Gerência Regional: ");
						
						colecaoSubRelatorioGerencia.add(subBeanGerencia);
						
						//Re-Ordenar pela faixa
						Collections.sort(colecaoSubRelatorioGerencia);
						
						bean.setArrayJRFaixasContasTotalGerencia(colecaoSubRelatorioGerencia);
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean>();
					}
					else{
						subBeanGerencia.setMsgTotal("Total Gerência Regional: ");
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_DESATIVO);
						colecaoSubRelatorioGerencia.add(subBeanGerencia);
					}
					
					
				}
				else{
					
					if(helperNext == null || (helper.getIdMunicipio().intValue() != helperNext.getIdMunicipio().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						subBean.setMsgTotal("Total Município: ");
						colecaoSubRelatorio.add(subBean);
						bean.setArrayJRFaixasContasTotal(colecaoSubRelatorio);
						colecaoSubRelatorio = new ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean>();
					}
					
					else{
						subBean.setMsgTotal("Total Município: ");
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_DESATIVO);
						colecaoSubRelatorio.add(subBean);
					}
					
					
					
					if(helperNext == null || (helper.getRegiao().intValue() != helperNext.getRegiao().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						
						subBeanGerencia.setMsgTotal("Total Região: ");
						
						colecaoSubRelatorioGerencia.add(subBeanGerencia);
						
						//Re-Ordenar pela faixa
						Collections.sort(colecaoSubRelatorioGerencia);
						
						bean.setArrayJRFaixasContasTotalGerencia(colecaoSubRelatorioGerencia);
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean>();
					}
					else{
						subBeanGerencia.setMsgTotal("Total Região: ");
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_DESATIVO);
						colecaoSubRelatorioGerencia.add(subBeanGerencia);
					}
					
					
				}
				
				subBeanTotalGeral.setMsgTotal("Total Geral: ");
				colecaoSubRelatorioTotalGeral.add(subBeanTotalGeral);
				
				if(contador + 1 == colecaoBoletins.size()){
					Collections.sort(colecaoSubRelatorioTotalGeral);
					bean.setArrayJRFaixasContasTotalGeral(colecaoSubRelatorioTotalGeral);
				}
				
				retorno.add(bean);
				contador++;
				
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
