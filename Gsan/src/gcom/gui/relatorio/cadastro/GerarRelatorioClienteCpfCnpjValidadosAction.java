package gcom.gui.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.pagamento.RelatorioPagamentoBaixaAutomaticaAnalitico;
import gcom.relatorio.cadastro.GerarRelatorioClienteCpfCnpjValidadosHelper;
import gcom.relatorio.cadastro.RelatorioClienteCpfCnpjValidadosMobile;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
/**
 * [UC1537] - Gerar Relatório Clientes com CPF CNPJ Validados e Informado no Mobile
 * 
 * @author Diogo Luiz
 * @Date 20/08/2013
 *
 */
public class GerarRelatorioClienteCpfCnpjValidadosAction extends ExibidorProcessamentoTarefaRelatorio {

	@SuppressWarnings("null") 
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {		
		
		// Obtém a instância da fachada
				Fachada fachada = Fachada.getInstancia();

				ActionForward retorno = actionMapping
						.findForward("gerarRelatorioClienteCpfCnpjValidadosAction");
				
				HttpSession sessao = httpServletRequest.getSession(false);
				
				httpServletRequest.setAttribute("telaSucessoRelatorio",true);					
				
				GerarRelatorioClienteCpfCnpjValidadosActionForm form = 
						(GerarRelatorioClienteCpfCnpjValidadosActionForm) actionForm;
				
				TarefaRelatorio relatorio = null;
				
				String tipoGeracaoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
				
				String idEmpresa = form.getIdEmpresa();	
				String idArquivo = form.getIdArquivo();
				String periodoInicial = form.getPeriodoInicial();
				String periodoFinal = form.getPeriodoFinal();
				String idLeiturista = form.getIdLeiturista();				
				
				if((idEmpresa.equals("-1")) && (idArquivo.equals(""))){
					throw new ActionServletException("atencao.empresa_nao_informado");
				}				
				/** 
				 * 2.4. Será obrigatório caso não tenha sido informado identificador de arquivo
				 */		
				boolean verificarArquivo = false;
				if(idArquivo == null || idArquivo.equals("")){
					if((periodoInicial != null && !periodoInicial.equals("")) && 
							(periodoFinal != null && !periodoFinal.equals(""))){
						
						Date dtInicial = Util.converteStringParaDate(periodoInicial);
						Date dtFinal = Util.converteStringParaDate(periodoFinal);
						
						Integer compararDatas = Util.compararData(dtInicial, dtFinal);
						
						if(compararDatas.equals(ConstantesSistema.INDICADOR_SIM)){
							throw new ActionServletException("atencao.data.intervalo.invalido");
						}
						
						/**
						 * [FE0003] – Pesquisar Existência de Dados, caso o usuário não tenha informado o arquivo
						 */						
						verificarArquivo = fachada.pesquisarExistenciaDados(idEmpresa, periodoInicial, 
								periodoFinal, idLeiturista);
						
						if(!verificarArquivo){
							throw new ActionServletException("atencao.arquivo_nao_encontrado_relatorio");
						}					
								
						GerarRelatorioClienteCpfCnpjValidadosHelper helper = 
								new GerarRelatorioClienteCpfCnpjValidadosHelper(); 
						
						Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> colecaoHelperRelatorio = 
								new ArrayList<GerarRelatorioClienteCpfCnpjValidadosHelper>();												
						
						Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> colecaoHelper =  
								fachada.gerarRelatorioClienteCpfCnpjValidados(idEmpresa, periodoInicial, 
										periodoFinal, idLeiturista);						
						
						if(!Util.isVazioOrNulo(colecaoHelper)){
							Iterator it = colecaoHelper.iterator();
							
							while(it.hasNext()){
								
								helper = new GerarRelatorioClienteCpfCnpjValidadosHelper();
								GerarRelatorioClienteCpfCnpjValidadosHelper array = (GerarRelatorioClienteCpfCnpjValidadosHelper) 
										it.next();
							
								FiltroEmpresa filtroempresa = new FiltroEmpresa();
								filtroempresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
								Collection colecaoEmpresa = fachada.pesquisar(filtroempresa, Empresa.class.getName());
								Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
								helper.setEmpresa(empresa.getDescricao());							
									
								helper.setClieGsan(array.getClieGsan());
								helper.setClieMobile(array.getClieMobile());
								helper.setCodCliente(array.getCodCliente());
								helper.setValorAtual(array.getValorAtual());
								helper.setValorAnterior(array.getValorAnterior());	
								helper.setDataAtual(periodoInicial);
								helper.setDataFinal(periodoFinal);
								
								colecaoHelperRelatorio.add(helper);									
							}
						}else{
							throw new ActionServletException("atencao.relatorio_vazio_mobile");
						}
					
						if (tipoGeracaoRelatorio == null || tipoGeracaoRelatorio.equalsIgnoreCase("")) {
							tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
						}						
						
						relatorio = new RelatorioClienteCpfCnpjValidadosMobile(
								(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
						
						FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
						filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, idLeiturista));
						filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
						filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
						
						Collection colecaoLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
						
						if(!Util.isVazioOrNulo(colecaoLeiturista)){
							Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);
							
							if(leiturista.getFuncionario() != null){
								relatorio.addParametro("leiturista", leiturista.getFuncionario().getNome());
							}else{
								relatorio.addParametro("leiturista", leiturista.getCliente().getNome());
							}
						}						
						
						FiltroEmpresa filtroempresa = new FiltroEmpresa();
						filtroempresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
						Collection colecaoEmpresa = fachada.pesquisar(filtroempresa, Empresa.class.getName());
						Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
						relatorio.addParametro("empresa", empresa.getDescricao());
						
						relatorio.addParametro("colecaoHelper", colecaoHelperRelatorio);
						relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));							
						
					}else{
						throw new ActionServletException("atencao.arquivo_nao_informado_periodo");
					}
				}			 
				else{	
					/**
					 * [FE0003] – Pesquisar Existência de Dados, caso o usuário tenha informado o arquivo
					 */				
					GerarRelatorioClienteCpfCnpjValidadosHelper helper = new GerarRelatorioClienteCpfCnpjValidadosHelper(); 
					
					Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> colecaoHelperRelatorio = 
							new ArrayList<GerarRelatorioClienteCpfCnpjValidadosHelper>();
					
					verificarArquivo = fachada.existenciaIdentificadorArquivo(idArquivo);
					if(!verificarArquivo){
						sessao.setAttribute("idArquivoEncontrado","true");						
						throw new ActionServletException("atencao.relatorio_vazio_mobile");						
					}
					
					Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> colecaoHelper =  
							fachada.gerarRelatorioClienteCpfCnpjValidados(idArquivo);						
					
					Iterator it = colecaoHelper.iterator();
					
					while(it.hasNext()){
						
						helper = new GerarRelatorioClienteCpfCnpjValidadosHelper();
						GerarRelatorioClienteCpfCnpjValidadosHelper array = (GerarRelatorioClienteCpfCnpjValidadosHelper) 
								it.next();					
						
						FiltroArquivoTextoAtualizacaoCadastral filtro = new FiltroArquivoTextoAtualizacaoCadastral();
						filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAtualizacaoCadastral.ID, 
								idArquivo));
						Collection ColecaoArquivo = fachada.pesquisar(filtro, ArquivoTextoAtualizacaoCadastral.class.getName());						
						
							ArquivoTextoAtualizacaoCadastral arquivo = (ArquivoTextoAtualizacaoCadastral) 
									Util.retonarObjetoDeColecao(ColecaoArquivo);
							helper.setArquivo(arquivo.getDescricaoArquivo());
							
							helper.setClieGsan(array.getClieGsan());
							helper.setClieMobile(array.getClieMobile());
							helper.setCodCliente(array.getCodCliente());
							helper.setValorAtual(array.getValorAtual());
							helper.setValorAnterior(array.getValorAnterior());							
							
							colecaoHelperRelatorio.add(helper);									
					}
				
					if (tipoGeracaoRelatorio == null || tipoGeracaoRelatorio.equalsIgnoreCase("")) {
						tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
					}						
					
					relatorio = new RelatorioClienteCpfCnpjValidadosMobile(
							(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));					
					
					FiltroArquivoTextoAtualizacaoCadastral filtroArquivo = new FiltroArquivoTextoAtualizacaoCadastral();
					filtroArquivo.adicionarParametro(new ParametroSimples(
							FiltroArquivoTextoAtualizacaoCadastral.ID, idArquivo));	
					filtroArquivo.adicionarCaminhoParaCarregamentoEntidade(
							FiltroArquivoTextoAtualizacaoCadastral.LEITURISTA);
					filtroArquivo.adicionarCaminhoParaCarregamentoEntidade(
							FiltroArquivoTextoAtualizacaoCadastral.LEITURISTA_EMPRESA);
					
					Collection colecaoArquivo = fachada.pesquisar(filtroArquivo, 
							ArquivoTextoAtualizacaoCadastral.class.getName());
					
					if(!Util.isVazioOrNulo(colecaoArquivo)){
						ArquivoTextoAtualizacaoCadastral arquivo = (ArquivoTextoAtualizacaoCadastral) 
								Util.retonarObjetoDeColecao(colecaoArquivo);
						 Integer idEmpresaArquivo = arquivo.getLeiturista().getEmpresa().getId();
						 
						 FiltroEmpresa filtroempresa = new FiltroEmpresa();
							filtroempresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresaArquivo));
							Collection colecaoEmpresa = fachada.pesquisar(filtroempresa, Empresa.class.getName());
							
							if(!Util.isVazioOrNulo(colecaoEmpresa)){
								Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
								//helper.setEmpresa(empresa.getDescricao());
								relatorio.addParametro("empresa", empresa.getDescricao());
							}						
					}
					
					
					relatorio.addParametro("colecaoHelper", colecaoHelperRelatorio);
					relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));							
				}	
				
		retorno = processarExibicaoRelatorio(
				relatorio, tipoGeracaoRelatorio, httpServletRequest,
				httpServletResponse, actionMapping);
		
		return retorno;
	}
}
