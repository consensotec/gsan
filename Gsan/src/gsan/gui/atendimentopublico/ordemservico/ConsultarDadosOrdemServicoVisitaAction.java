package gsan.gui.atendimentopublico.ordemservico;

import java.util.ArrayList;
import java.util.Collection;

import gsan.atendimentopublico.bean.AcoesParaCorrecaoAnormalidadesEncontradasHelper;
import gsan.atendimentopublico.bean.ProcessarAtualizacaoOSArquivoTextoHelper;
import gsan.atendimentopublico.ordemservico.ArquivoTextoRetornoVisitaCampo;
import gsan.atendimentopublico.ordemservico.FiltroArquivoTextoRetornoVisitaCampo;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
 * 
 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
 * 
 * @author Mariana Victor
 * @date 23/09/2011
 */
public class ConsultarDadosOrdemServicoVisitaAction extends GcomAction {

   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * <Breve descrição sobre o subfluxo>
     *
     * <Identificador e nome do subfluxo>	
     *
     * <Breve descrição sobre o fluxo secundário>
     *
     * <Identificador e nome do fluxo secundário> 
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Cria a variável de retorno 
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        ConsultarDadosOrdemServicoVisitaActionForm form = (ConsultarDadosOrdemServicoVisitaActionForm) actionForm;
        
        //Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        Collection<AcoesParaCorrecaoAnormalidadesEncontradasHelper> colecaoAcoesCorrecaoAnormalidadesEncontradasHelper = null;
        String[] idsAcoes = form.getIdsAcoes();
        String ordemServicoConferida = form.getOrdemServicoConferida();
        
		
        if (sessao.getAttribute("colecaoAcoesCorrecaoAnormalidadesEncontradasHelper") != null 
        		&& !sessao.getAttribute("colecaoAcoesCorrecaoAnormalidadesEncontradasHelper")
        			.toString().trim().equals("")) {
        	colecaoAcoesCorrecaoAnormalidadesEncontradasHelper = 
        			(Collection<AcoesParaCorrecaoAnormalidadesEncontradasHelper>)
        		sessao.getAttribute("colecaoAcoesCorrecaoAnormalidadesEncontradasHelper");
        }
        Integer idOrdemServico = new Integer(form.getOrdemServico());
        Integer idArquivoTexto = new Integer(sessao.getAttribute("idArquivoTexto").toString());
        
        FiltroArquivoTextoRetornoVisitaCampo filtro = new FiltroArquivoTextoRetornoVisitaCampo();
        filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoRetornoVisitaCampo.ID_ARQUIVO_TEXTO, idArquivoTexto));
        filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoRetornoVisitaCampo.ID_ORDEM_SERVICO, idOrdemServico));
        Collection<ArquivoTextoRetornoVisitaCampo> colecao = fachada.pesquisar(filtro, ArquivoTextoRetornoVisitaCampo.class.getName());
        ArquivoTextoRetornoVisitaCampo arq = (ArquivoTextoRetornoVisitaCampo) Util.retonarObjetoDeColecao(colecao);
        
        Collection<ProcessarAtualizacaoOSArquivoTextoHelper> colecaoHelper = new ArrayList<ProcessarAtualizacaoOSArquivoTextoHelper>();
        ProcessarAtualizacaoOSArquivoTextoHelper processarAtualizacaoOSArquivoTextoHelper = new ProcessarAtualizacaoOSArquivoTextoHelper();
        processarAtualizacaoOSArquivoTextoHelper
        	.setColecaoAcoesCorrecaoAnormalidadesEncontradasHelper(colecaoAcoesCorrecaoAnormalidadesEncontradasHelper);
        processarAtualizacaoOSArquivoTextoHelper
        	.setIdOrdemServico(idOrdemServico);
        processarAtualizacaoOSArquivoTextoHelper
        	.setIdsAcoes(idsAcoes);
        processarAtualizacaoOSArquivoTextoHelper
        	.setMatricula(new Integer(form.getMatricula()));
        processarAtualizacaoOSArquivoTextoHelper
        	.setOrdemServicoConferida(ordemServicoConferida);
        processarAtualizacaoOSArquivoTextoHelper.setIdArquivoTexto(
        	arq.getId());
        colecaoHelper.add(processarAtualizacaoOSArquivoTextoHelper);
        
		fachada.atualizarArquivoTxtOSVisitaCampo(colecaoHelper, usuarioLogado);
		
        montarPaginaSucesso(httpServletRequest, "A Ordem de Serviço foi atualizada com sucesso.",
        		"Consultar Arquivo Texto das Ordens de Serviço de Visita",
                "exibirConsultarArquivoTextoOSVisitaAction.do?menu=sim",
                "exibirConsultarOrdemServicoDoArquivoTextoAction.do?arquivoTexto=" + idArquivoTexto,
                "Atualizar Ordens de Serviço do Arquivo Texto");

	    //Retorna o mapeamento contido na variável retorno
	    return retorno;
	}
    
}
