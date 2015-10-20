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

public class RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditos extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditos(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_PARCELAMENTOS_ATRASOS_RECUPERACAO_CREDITOS);
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
			
			List<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosBean> relatorioBeans = 
					montarRelatorio(colecaoBoletins,filtroLocalidade,fachada);
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_PARCELAMENTOS_ATRASOS_RECUPERACAO_CREDITOS,
			parametros, ds, tipoFormatoRelatorio);
			
			
			return retorno;
		}
	    

		private ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosBean> montarRelatorio(
				ArrayList<RelatorioBoletimMedicaoAcompanhamentoHelper> colecaoBoletins, Short filtroLocalidade, Fachada fachada) {
			
			ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosBean> retorno = 
					new ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosBean>();
			
			Iterator it = colecaoBoletins.iterator();
			int contador = 0;
			
			List<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean> colecaoSubRelatorio = 
					new ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean>();
			
			List<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean> colecaoSubRelatorioGerencia = 
					new ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean>();
			
			List<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean> colecaoSubRelatorioTotalGeral = 
					new ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean>();
			
			while(it.hasNext()){
				RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosBean bean = 
						new RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosBean();
				
				RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean subBean =
						new RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean();
				
				RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean subBeanGerencia =
						new RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean();
				
				RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean subBeanTotalGeral =
						new RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean();
				
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
				if(helper.getValorEmAtraso() == null)
					helper.setValorEmAtraso(new BigDecimal(0));
				
				
				bean.setValorPago(helper.getValorPago());
				
				bean.setSaldoAberto(helper.getSaldoEmAberto().add(helper.getSaldoEmAbertoHist()).subtract(helper.getValorPago()));
				
				bean.setQtdParcelasAtrasadas(helper.getParcelaAtraso());
				bean.setValorAtrasado(helper.getValorEmAtraso());
				
				//Montando SubBean
				subBean.setIdFaixaContas(helper.getIdFaixaContas());
				subBean.setFaixaContas(helper.getDescricaoFaixaContas());
				subBean.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBean.setValorNegociado(bean.getValorNegociado());
				subBean.setValorPago(bean.getValorPago());
				subBean.setValorAtrasado(bean.getValorAtrasado());
				subBean.setSaldoAberto(bean.getSaldoAberto());
				subBean.setQtdParcelasAtraso(bean.getQtdParcelasAtrasadas());
				
				
				//Montando subBean do agrupamento de gerência/região
				subBeanGerencia.setIdFaixaContas(helper.getIdFaixaContas());
				subBeanGerencia.setFaixaContas(helper.getDescricaoFaixaContas());
				subBeanGerencia.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBeanGerencia.setValorNegociado(bean.getValorNegociado());
				subBeanGerencia.setValorPago(bean.getValorPago());
				subBeanGerencia.setValorAtrasado(bean.getValorAtrasado());
				subBeanGerencia.setSaldoAberto(bean.getSaldoAberto());
				subBeanGerencia.setQtdParcelasAtraso(bean.getQtdParcelasAtrasadas());
				
				//Montando subBean do agrupamento total
				subBeanTotalGeral.setIdFaixaContas(helper.getIdFaixaContas());
				subBeanTotalGeral.setFaixaContas(helper.getDescricaoFaixaContas());
				subBeanTotalGeral.setQtdFaturasNegociadas(bean.getQtdFaturasNegociadas());
				subBeanTotalGeral.setValorNegociado(bean.getValorNegociado());
				subBeanTotalGeral.setValorPago(bean.getValorPago());
				subBeanTotalGeral.setValorAtrasado(bean.getValorAtrasado());
				subBeanTotalGeral.setSaldoAberto(bean.getSaldoAberto());
				subBeanTotalGeral.setQtdParcelasAtraso(bean.getQtdParcelasAtrasadas());
				
				
				
				
				if(filtroLocalidade == ConstantesSistema.SIM){
					if(helperNext == null || (helper.getIdLocalidade().intValue() != helperNext.getIdLocalidade().intValue())){
						bean.setIndicadorUltimoElemento(ConstantesSistema.INDICADOR_USO_ATIVO);
						subBean.setMsgTotal("Total Localidade: ");
						colecaoSubRelatorio.add(subBean);
						bean.setArrayJRFaixasContasTotal(colecaoSubRelatorio);
						colecaoSubRelatorio = new ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean>();
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
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean>();
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
						colecaoSubRelatorio = new ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean>();
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
						colecaoSubRelatorioGerencia = new ArrayList<RelatorioAcompanhamentoParcelamentosEmAtrasoRecuperacaoCreditosSubTotalBean>();
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
