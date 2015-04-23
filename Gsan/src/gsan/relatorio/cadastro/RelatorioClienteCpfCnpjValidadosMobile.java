package gsan.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.arrecadacao.pagamento.RelatorioPagamentosBaixadosAutomaticamenteAnaliticoBean;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;

/**
 * [UC1537] - Gerar Relatório Clientes com CPF CNPJ Validados e Informado no Mobile
 * 
 * @author Diogo Luiz
 * @Date 20/08/2013
 *
 */
public class RelatorioClienteCpfCnpjValidadosMobile extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioClienteCpfCnpjValidadosMobile(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CLIENTE_CPF_CNPJ_VALIDADOS_MOBILE);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object executar() throws TarefaException {
	
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> colecaoHelper = null;
		colecaoHelper = (Collection<GerarRelatorioClienteCpfCnpjValidadosHelper>) getParametro("colecaoHelper");
		
		
		byte[] retorno = null;
		
		List relatorioBeans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();
		
		Map parametros = new HashMap();	
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String empresa = (String) getParametro("empresa");
		String leiturista = (String) getParametro("leiturista");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Collection<RelatorioClienteCpfCnpjValidadosMobileBean> colecaoRelatorioBean = new ArrayList();		
		
		if(!Util.isVazioOrNulo(colecaoHelper)){
			Iterator it = colecaoHelper.iterator();
			
			while(it.hasNext()){
				RelatorioClienteCpfCnpjValidadosMobileBean bean = new RelatorioClienteCpfCnpjValidadosMobileBean(); 
				GerarRelatorioClienteCpfCnpjValidadosHelper array = (GerarRelatorioClienteCpfCnpjValidadosHelper) 
						it.next();
				
				String arquivo = array.getArquivo();
				bean.setClieGsan(array.getClieGsan());
				bean.setClieMobile(array.getClieMobile());
				bean.setCodCliente(array.getCodCliente());				
				String dataInicial = array.getDataAtual();
				String dataFinal = array.getDataFinal();
				
				if(array.getValorAnterior().length() == 11 && array.getValorAtual().length() == 11){
					bean.setValorAnterior(Util.formatarCpf(array.getValorAnterior()));
					bean.setValorAtual(Util.formatarCpf(array.getValorAtual()));
				}else{
					bean.setValorAnterior(Util.formatarCnpj(array.getValorAnterior()));
					bean.setValorAtual(Util.formatarCnpj(array.getValorAtual()));
				}			
				
				colecaoRelatorioBean.add(bean);							
				
				// Parâmetros do relatório				
				parametros.put("arquivo", arquivo);
				parametros.put("empresa", empresa);	
				parametros.put("leiturista", leiturista);
				parametros.put("dataInicial", dataInicial);
				parametros.put("dataFinal", dataFinal);	
				parametros.put("total", colecaoHelper.size());
			}	
			
			parametros.put("tipoFormatoRelatorio", "R1537");
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());			
			
			relatorioBeans = (ArrayList) colecaoRelatorioBean;
			
			// cria uma instância do dataSource do relatório
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_CLIENTE_CPF_CNPJ_VALIDADOS_MOBILE,
					parametros, ds, tipoFormatoRelatorio);

			// Grava o relatório no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_CLIENTE_CPF_CNPJ_VALIDADOS_MOBILE,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}						
			
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}		
		return retorno;	
	}
	@Override
	public void agendarTarefaBatch() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

}
