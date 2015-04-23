package gsan.gui.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.cadastro.ArquivoTextoAtualizacaoCadastral;
import gsan.cadastro.atualizacaocadastral.FiltroArquivoTextoAtualizacaoCadastral;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm;
import gsan.gui.micromedicao.DadosLeiturista;
import gsan.micromedicao.ComparatorLeiturista;
import gsan.micromedicao.FiltroLeiturista;
import gsan.micromedicao.Leiturista;
import gsan.seguranca.transacao.FiltroTabelaColuna;
import gsan.seguranca.transacao.TabelaColuna;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;

public class ExibirGerarRelatorioClienteCpfCnpjValidadosAction extends GcomAction{
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirGerarRelatorioClienteCpfCnpjValidadosAction");        

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);        
        
        GerarRelatorioClienteCpfCnpjValidadosActionForm form = (GerarRelatorioClienteCpfCnpjValidadosActionForm) actionForm;
        
		Collection colecaoLeiturista = new ArrayList();
        
		if(sessao.getAttribute("colecaoEmpresa") == null){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			
			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			// [FS0001 - Verificar Existencia de dados]
			if ( (colecaoEmpresa == null) || (colecaoEmpresa.size() == 0) ) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
			}else {
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			}
		}
		
		// Agente Comercial
		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")
				&& !form.getIdEmpresa().equals("")) {			
			
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.ID);
			filtroLeiturista.adicionarParametro(
				new ParametroSimples( FiltroLeiturista.EMPRESA_ID, form.getIdEmpresa()));			
			
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);			
			
			filtroLeiturista.setCampoOrderBy(FiltroLeiturista.CLIENTE_NOME);

			Collection colecao = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Iterator it = colecao.iterator();				
										
				while (it.hasNext()) {
					Leiturista leitu = (Leiturista) it.next();
					DadosLeiturista dadosLeiu = null;
					
					if (leitu.getFuncionario() != null) {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getFuncionario().getNome());
					} else {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getCliente().getNome());
					}
					colecaoLeiturista.add(dadosLeiu);
				}
			}

			Collections.sort((List) colecaoLeiturista, new Comparator() {
				public int compare(Object a, Object b) {
				String valor1 = ((DadosLeiturista)a).getDescricao();
				String valor2 = ((DadosLeiturista)b).getDescricao();

				return valor1.compareTo(valor2);

				}
			});				
		}
		
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);		
		
		String idDigitadoArquivo = form.getIdArquivo();
        
		// Identificador do Arquivo
		if (idDigitadoArquivo != null && !idDigitadoArquivo.trim().equals("")) {
			FiltroArquivoTextoAtualizacaoCadastral filtroArquivoTextoAtualizacaoCadastral = new FiltroArquivoTextoAtualizacaoCadastral();

			filtroArquivoTextoAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoAtualizacaoCadastral.ID, idDigitadoArquivo));

			Collection arquivosTextoAtualizacaoCadastral = fachada.pesquisar(filtroArquivoTextoAtualizacaoCadastral,
					ArquivoTextoAtualizacaoCadastral.class.getName());

			if (arquivosTextoAtualizacaoCadastral != null && !arquivosTextoAtualizacaoCadastral.isEmpty()) {
				
				form.setIdArquivo("" + ((ArquivoTextoAtualizacaoCadastral) ((List) arquivosTextoAtualizacaoCadastral).get(0))
					.getId());
				form.setDescricaoArquivo(((ArquivoTextoAtualizacaoCadastral) ((List) arquivosTextoAtualizacaoCadastral).get(0))
					.getDescricaoArquivo());
				sessao.setAttribute("idArquivoEncontrado","true");
				
			} else {		
				
				form.setIdArquivo("");				
				form.setDescricaoArquivo("Arquivo de atualização não encontrado");				
				sessao.removeAttribute("idArquivoEncontrado");
			}
		}		
	
        return retorno;
    }  
}