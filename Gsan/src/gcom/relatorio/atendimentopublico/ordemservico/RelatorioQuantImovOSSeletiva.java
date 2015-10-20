package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioQuantImovOSSeletiva extends TarefaRelatorio{

	public RelatorioQuantImovOSSeletiva(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_QUANTITATIVO_IMOVEIS_OS_SELETIVA);		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {
		byte[] retorno = null;
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		String localidade = (String)this.getParametro("localidade");
		String setorIni = (String)this.getParametro("setorInicial");
		String setorFin = (String)this.getParametro("setorFinal");
		String qdrIni = (String)this.getParametro("quadraInicial");
		String qdrFin = (String)this.getParametro("quadraFinal");
		String comando  = (String)this.getParametro("comando");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("localidadeFiltro", localidade);
		parametros.put("setorInicialFiltro", setorIni);
		parametros.put("setorFinalFiltro", setorFin);
		parametros.put("quadraInicialFiltro", qdrIni);
		parametros.put("quadraFinalFiltro", qdrFin);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		List<RelatorioQuantImovOSSeletivaBean> colecaoBean = new ArrayList<RelatorioQuantImovOSSeletivaBean>();
		Collection<Object[]> resultadoConsulta = Fachada.getInstancia().gerarRelatorioQuantImovOSSeletivaBean(comando, localidade, setorIni, setorFin, 
			qdrIni, qdrFin);
		if(resultadoConsulta !=null && !resultadoConsulta.isEmpty()){
			for(Object[] ob : resultadoConsulta){			
				Localidade local = Fachada.getInstancia().pesquisarLocalidadeDigitada((Integer)ob[0]);
				String codSetor = ob[1].toString();
				String anomarlidade = ""; 
				if(ob[2] !=null){
					anomarlidade = ob[2].toString();
				}
				Integer qtdImoveis = (Integer) ob[3];
				String codQuadra = ob[4].toString();
				
				RelatorioQuantImovOSSeletivaBean bean = new RelatorioQuantImovOSSeletivaBean();
				bean.setAnormalidade(anomarlidade);
				bean.setLocalidade(local.getId()+" - "+local.getDescricao());
				bean.setSetorComercial(codSetor);
				bean.setQuadra(codQuadra);
				bean.setQtdImoveis(qtdImoveis);
							
				colecaoBean.add(bean);
			}
			
			RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);		
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_QUANTITATIVO_IMOVEIS_OS_SELETIVA,parametros, ds, tipoFormatoRelatorio);
		}
		else{
			colecaoBean.add(new RelatorioQuantImovOSSeletivaBean());
			RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
	}

}
