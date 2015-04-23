package gsan.relatorio.faturamento.conta;

import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.ContaComunicadoFaturamentoGrupo;
import gsan.faturamento.conta.ContaComunicadoHelper;
import gsan.faturamento.conta.ContaComunicadoQuadra;
import gsan.faturamento.conta.ContaComunicadoRota;
import gsan.faturamento.conta.ContaComunicadoSetor;
import gsan.faturamento.conta.FiltroContaComunicadoFaturamentoGrupo;
import gsan.faturamento.conta.FiltroContaComunicadoQuadra;
import gsan.faturamento.conta.FiltroContaComunicadoRota;
import gsan.faturamento.conta.FiltroContaComunicadoSetor;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Cesar Medeiros
 * @created 12/02/2015
 */

public class RelatorioComunicadoConta extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;
	
	public RelatorioComunicadoConta(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COMUNICADO_CONTA);
	}
	
	public RelatorioComunicadoConta() {
		super(null, "");
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
	
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String abrangencia = (String) getParametro("abrangenciaParametro");
		String referencia = (String) getParametro("referenciaParametro");
		String titulo = (String) getParametro("tituloParametro");
		String grupoFaturamentoParametro = (String) getParametro("grupoFaturamentoParametro");
		String gerenciaRegional = (String) getParametro("gerenciaRegionalParametro");
		String localidade = (String) getParametro("localidadeParametro");
		String setorParametro = (String) getParametro("setorComercialParametro");
		String rotaParametro = (String) getParametro("rotaParametro");
		String quadraParametro = (String) getParametro("quadraParametro");
		String indicadorUso = (String) getParametro("indicadorUsoParametro");
		
		byte[] retorno = null;
		
		ArrayList<RelatorioComunicadoContaBean> colecaoComunicadoContaBean = new ArrayList<RelatorioComunicadoContaBean>();
		
		Collection<ContaComunicadoHelper> colecaoComunicadoContaHelper = (Collection <ContaComunicadoHelper>) getParametro("colecaoComunicadoConta");	
		
		for (ContaComunicadoHelper comunicadoHelper : colecaoComunicadoContaHelper) {
			
			RelatorioComunicadoContaBean comunicadoBean = new RelatorioComunicadoContaBean();
			
			comunicadoBean.setMesAnoReferencia(comunicadoHelper.getReferencia());
			comunicadoBean.setTitulo(comunicadoHelper.getTitulo());
			comunicadoBean.setIdComunicado(comunicadoHelper.getIdComunicado());
			comunicadoBean.setComunicado(comunicadoHelper.getComunicado());
			
			ArrayList<RelatorioComunicadoContaGerenciaRegionalBean> colecaoGerenciaRegionalBean = new ArrayList<RelatorioComunicadoContaGerenciaRegionalBean>();
			ArrayList<RelatorioComunicadoContaGrupoBean> colecaoGrupoBean = new ArrayList<RelatorioComunicadoContaGrupoBean>();
			
			FiltroContaComunicadoFaturamentoGrupo filtroComunicadoGrupo = new FiltroContaComunicadoFaturamentoGrupo();
			filtroComunicadoGrupo.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO);
			filtroComunicadoGrupo.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO_ID, comunicadoHelper.getIdComunicado()));
			
			Collection<ContaComunicadoFaturamentoGrupo> colecaoComunicadoGrupo = (Collection<ContaComunicadoFaturamentoGrupo>) Fachada.getInstancia().pesquisar(filtroComunicadoGrupo, ContaComunicadoFaturamentoGrupo.class.getName());
			
			if(colecaoComunicadoGrupo != null && !colecaoComunicadoGrupo.isEmpty()) {
					
				for (ContaComunicadoFaturamentoGrupo comunicadoGrupo : colecaoComunicadoGrupo) {
					RelatorioComunicadoContaGrupoBean comunicadoGrupoBean = new RelatorioComunicadoContaGrupoBean();
					comunicadoGrupoBean.setGrupoFaturamento(comunicadoGrupo.getFaturamentoGrupo().getDescricao());
					colecaoGrupoBean.add(comunicadoGrupoBean);
				}
					
				comunicadoBean.setArrayRelatorioGrupoFaturamentoBean(colecaoGrupoBean);
				comunicadoBean.setArrayJRGrupoFaturamento(new JRBeanCollectionDataSource(colecaoGrupoBean));
					
			} else {
				
				if (comunicadoHelper.getGerenciaRegional() != null && !comunicadoHelper.getGerenciaRegional().trim().equals("") && !comunicadoHelper.getGerenciaRegional().trim().equals("null")) {
					RelatorioComunicadoContaGerenciaRegionalBean comunicadoContaGerenciaRegionalBean = new RelatorioComunicadoContaGerenciaRegionalBean();
					comunicadoContaGerenciaRegionalBean.setGerenciaRegional(comunicadoHelper.getGerenciaRegionalDescricaoAbreviada());
					colecaoGerenciaRegionalBean.add(comunicadoContaGerenciaRegionalBean);
					
					comunicadoBean.setArrayRelatorioGerenciaRegionalBean(colecaoGerenciaRegionalBean);
					comunicadoBean.setArrayJRGerenciaReginal(new JRBeanCollectionDataSource(colecaoGerenciaRegionalBean));
				
				} else if (comunicadoHelper.getLocalidade() != null && !comunicadoHelper.getLocalidade().trim().equals("")) {
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.GERENCIA);
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, comunicadoHelper.getLocalidade()));
					
					Collection<Localidade> colecaoLocalidade = (Collection<Localidade>) Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
					Localidade localidadeComunicado = colecaoLocalidade.iterator().next(); 
					
					RelatorioComunicadoContaGerenciaRegionalBean comunicadoContaGerenciaRegionalBean = new RelatorioComunicadoContaGerenciaRegionalBean();
					comunicadoContaGerenciaRegionalBean.setGerenciaRegional(localidadeComunicado.getGerenciaRegional().getNomeAbreviado());
					comunicadoContaGerenciaRegionalBean.setLocalidade(localidadeComunicado.getId() + " - " + localidadeComunicado.getDescricao());
					colecaoGerenciaRegionalBean.add(comunicadoContaGerenciaRegionalBean);
					
					comunicadoBean.setArrayRelatorioGerenciaRegionalBean(colecaoGerenciaRegionalBean);
					comunicadoBean.setArrayJRGerenciaReginal(new JRBeanCollectionDataSource(colecaoGerenciaRegionalBean));		
					
				} else {
					
					FiltroContaComunicadoSetor filtroComunicadoSetor = new FiltroContaComunicadoSetor();
					filtroComunicadoSetor.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoSetor.SETOR_COMERCIAL);
					filtroComunicadoSetor.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoSetor.LOCALIDADE);
					filtroComunicadoSetor.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoSetor.GERENCIA_REGIONAL);
					filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.CONTA_COMUNICADO_ID, comunicadoHelper.getIdComunicado()));
					
					Collection<ContaComunicadoSetor> colecaoComunicadoSetor = (Collection<ContaComunicadoSetor>) Fachada.getInstancia().pesquisar(filtroComunicadoSetor, ContaComunicadoSetor.class.getName());
					
					if (colecaoComunicadoSetor != null && !colecaoComunicadoSetor.isEmpty()) {
						
						for (ContaComunicadoSetor contaComunicadoSetor : colecaoComunicadoSetor) {
							RelatorioComunicadoContaGerenciaRegionalBean comunicadoContaGerenciaRegionalBean = new RelatorioComunicadoContaGerenciaRegionalBean();
							comunicadoContaGerenciaRegionalBean.setGerenciaRegional(contaComunicadoSetor.getSetorComercial().getLocalidade().getGerenciaRegional().getNomeAbreviado());
							comunicadoContaGerenciaRegionalBean.setLocalidade(contaComunicadoSetor.getSetorComercial().getLocalidade().getId() + " - " + contaComunicadoSetor.getSetorComercial().getLocalidade().getDescricao());
							comunicadoContaGerenciaRegionalBean.setSetorComercial(contaComunicadoSetor.getSetorComercial().getCodigo() + " - " + contaComunicadoSetor.getSetorComercial().getDescricao());
							
							colecaoGerenciaRegionalBean.add(comunicadoContaGerenciaRegionalBean);
						}
						
						comunicadoBean.setArrayRelatorioGerenciaRegionalBean(colecaoGerenciaRegionalBean);
						comunicadoBean.setArrayJRGerenciaReginal(new JRBeanCollectionDataSource(colecaoGerenciaRegionalBean));
						
					} else {
						
						FiltroContaComunicadoRota filtroComunicadoRota = new FiltroContaComunicadoRota();
						filtroComunicadoRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.ROTA);
						filtroComunicadoRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.SETOR_COMERCIAL);
						filtroComunicadoRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.LOCALIDADE);
						filtroComunicadoRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.GERENCIA_REGIONAL);
						filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.CONTA_COMUNICADO_ID, comunicadoHelper.getIdComunicado()));
						
						Collection<ContaComunicadoRota> colecaoComunicadoRota = (Collection<ContaComunicadoRota>) Fachada.getInstancia().pesquisar(filtroComunicadoRota, ContaComunicadoRota.class.getName());
					
						if (colecaoComunicadoRota != null && !colecaoComunicadoRota.isEmpty()) {
						
							for (ContaComunicadoRota contaComunicadoRota : colecaoComunicadoRota) {
								RelatorioComunicadoContaGerenciaRegionalBean comunicadoContaGerenciaRegionalBean = new RelatorioComunicadoContaGerenciaRegionalBean();
								comunicadoContaGerenciaRegionalBean.setGerenciaRegional(contaComunicadoRota.getRota().getSetorComercial().getLocalidade().getGerenciaRegional().getNomeAbreviado());
								comunicadoContaGerenciaRegionalBean.setLocalidade(contaComunicadoRota.getRota().getSetorComercial().getLocalidade().getId() + " - " + contaComunicadoRota.getRota().getSetorComercial().getLocalidade().getDescricao());
								comunicadoContaGerenciaRegionalBean.setSetorComercial(contaComunicadoRota.getRota().getSetorComercial().getCodigo() + " - " + contaComunicadoRota.getRota().getSetorComercial().getDescricao());
								comunicadoContaGerenciaRegionalBean.setRota(contaComunicadoRota.getRota().getCodigo().toString());
								
								colecaoGerenciaRegionalBean.add(comunicadoContaGerenciaRegionalBean);
							}
								
							comunicadoBean.setArrayRelatorioGerenciaRegionalBean(colecaoGerenciaRegionalBean);
							comunicadoBean.setArrayJRGerenciaReginal(new JRBeanCollectionDataSource(colecaoGerenciaRegionalBean));
							
						} else {
							FiltroContaComunicadoQuadra filtroComunicadoQuadra = new FiltroContaComunicadoQuadra();
							filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.QUADRA);
							filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.ROTA);
							filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.SETOR_COMERCIAL);
							filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.LOCALIDADE);
							filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.GERENCIA_REGIONAL);
							filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.CONTA_COMUNICADO_ID, comunicadoHelper.getIdComunicado()));
							
							Collection<ContaComunicadoQuadra> colecaoComunicadoQuadra = (Collection<ContaComunicadoQuadra>) Fachada.getInstancia().pesquisar(filtroComunicadoQuadra, ContaComunicadoQuadra.class.getName());
							
							if (colecaoComunicadoQuadra != null && !colecaoComunicadoQuadra.isEmpty()) {
							
								for (ContaComunicadoQuadra contaComunicadoQuadra : colecaoComunicadoQuadra) {
									RelatorioComunicadoContaGerenciaRegionalBean comunicadoContaGerenciaRegionalBean = new RelatorioComunicadoContaGerenciaRegionalBean();
									comunicadoContaGerenciaRegionalBean.setGerenciaRegional(contaComunicadoQuadra.getQuadra().getRota().getSetorComercial().getLocalidade().getGerenciaRegional().getNomeAbreviado());
									comunicadoContaGerenciaRegionalBean.setLocalidade(contaComunicadoQuadra.getQuadra().getRota().getSetorComercial().getLocalidade().getId() + " - " + contaComunicadoQuadra.getQuadra().getRota().getSetorComercial().getLocalidade().getDescricao());
									comunicadoContaGerenciaRegionalBean.setSetorComercial(contaComunicadoQuadra.getQuadra().getRota().getSetorComercial().getCodigo() + " - " + contaComunicadoQuadra.getQuadra().getRota().getSetorComercial().getDescricao());
									comunicadoContaGerenciaRegionalBean.setRota(contaComunicadoQuadra.getQuadra().getRota().getCodigo().toString());
									comunicadoContaGerenciaRegionalBean.setQuadra("" + contaComunicadoQuadra.getQuadra().getNumeroQuadra());
									
									colecaoGerenciaRegionalBean.add(comunicadoContaGerenciaRegionalBean);
								}
									
								comunicadoBean.setArrayRelatorioGerenciaRegionalBean(colecaoGerenciaRegionalBean);
								comunicadoBean.setArrayJRGerenciaReginal(new JRBeanCollectionDataSource(colecaoGerenciaRegionalBean));
								
							}
						}
					}
						
				}
				
			}
			
			colecaoComunicadoContaBean.add(comunicadoBean);
		}
		
		
		//Recebe o método da fachada que pesquisa os comunicados
		RelatorioDataSource ds = new RelatorioDataSource(colecaoComunicadoContaBean);
		
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().
				pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("abrangencia", abrangencia);
		parametros.put("referencia", referencia);
		parametros.put("titulo", titulo);
		parametros.put("grupoFaturamento", grupoFaturamentoParametro);
		parametros.put("gerenciaRegional", gerenciaRegional);
		parametros.put("localidade", localidade);
		parametros.put("setorComercial", setorParametro);
		parametros.put("rota", rotaParametro);
		parametros.put("quadra", quadraParametro);
		parametros.put("indicadorUso", indicadorUso);
		
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_COMUNICADO_CONTA,
				parametros, ds, tipoFormatoRelatorio);
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComunicadoConta", this);
	}
	
}
