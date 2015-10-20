package gcom.gui.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.DadosRelatorioDemonstrativoDividaAtivaBean;
import gcom.cobranca.bean.DadosRelatorioDemonstrativoDividaAtivaHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1590] - Gerar Relatório Demonstrativo Dívida Ativa
 * 
 * @author Anderson Cabral
 * @date 10/03/2014
 */
public class RelatorioDemonstrativoDividaAtiva extends TarefaRelatorio{
	
	private static final long serialVersionUID = 1L;

	public RelatorioDemonstrativoDividaAtiva(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DEMONSTRATIVO_DIVIDA_ATIVA);
	}
	
	@Override
	public Object executar() throws TarefaException {
		byte[]  retorno =  null;
		
		try {
			Collection<DadosRelatorioDemonstrativoDividaAtivaHelper> colecaoDadosRelatorioDemonstrativoDividaAtivaHelper = (Collection)  getParametro("colecaoDadosRelatorioDemonstrativoDividaAtivaHelper");
			List<DadosRelatorioDemonstrativoDividaAtivaBean> colecaoDadosRelatorioDemonstrativoDividaAtivaBean = new ArrayList<DadosRelatorioDemonstrativoDividaAtivaBean>();
			if(colecaoDadosRelatorioDemonstrativoDividaAtivaHelper != null && !colecaoDadosRelatorioDemonstrativoDividaAtivaHelper.isEmpty()){
				DadosRelatorioDemonstrativoDividaAtivaBean dadosRelatorioDemonstrativoDividaAtivaBean = null;
				for(DadosRelatorioDemonstrativoDividaAtivaHelper helper : colecaoDadosRelatorioDemonstrativoDividaAtivaHelper){
					dadosRelatorioDemonstrativoDividaAtivaBean = new DadosRelatorioDemonstrativoDividaAtivaBean();
					
					dadosRelatorioDemonstrativoDividaAtivaBean.setIdContribuinte(helper.getIdContribuinte());
					dadosRelatorioDemonstrativoDividaAtivaBean.setNomeContribuinte(helper.getNomeContribuinte());
					dadosRelatorioDemonstrativoDividaAtivaBean.setValorHistorico(helper.getValorHistorico());
					dadosRelatorioDemonstrativoDividaAtivaBean.setValorCorrigido(helper.getValorCorrigido());
					
					colecaoDadosRelatorioDemonstrativoDividaAtivaBean.add(dadosRelatorioDemonstrativoDividaAtivaBean);
					
				}
			}else{
				throw new RelatorioVazioException("atencao.relatorio.vazio");
			}
			
			// Parâmetros do relatório
			Map parametro = new HashMap();
			
			// adiciona os parâmetros do relatório
			SistemaParametro sistemaParamntro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			int tipoFormatoRelatorio = (Integer) getParametro("tipoformatoRelatorio");			
			short indicadorIntra = (Short) getParametro("intra");
			String anoMesDemonstrativo = (String) getParametro("anoMesDemonstrativo");
			
			parametro.put("imagem", sistemaParamntro.getImagemRelatorio());
			parametro.put("numeroRelatorio", "R1590");
			parametro.put("municipio", Fachada.getInstancia().obterMunicipioDaEmpresa());
			parametro.put("dataDemonstrativo", Util.formatarData(Util.gerarDataApartirAnoMesRefencia(new Integer(anoMesDemonstrativo))));
	
			
			if(indicadorIntra == 1){
				parametro.put("intra", "CLIENTE INTRA");
			}else if(indicadorIntra == 2){
				parametro.put("intra", "CLIENTE NORMAL");
			}else{
				parametro.put("intra", "");
			}
			
			// cria uma instância do dataSource do relatório
			RelatorioDataSource ds = new RelatorioDataSource(colecaoDadosRelatorioDemonstrativoDividaAtivaBean);
	
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DEMONSTRATIVO_DIVIDA_ATIVA, parametro, ds, tipoFormatoRelatorio);
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio(){
		
		int retorno = 1;
		
		return retorno;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDemonstrativoDividaAtiva", this);
	}
}
