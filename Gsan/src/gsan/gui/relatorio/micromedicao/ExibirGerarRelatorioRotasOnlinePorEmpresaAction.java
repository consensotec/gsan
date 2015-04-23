package gsan.gui.relatorio.micromedicao;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroLeiturista;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.bean.PesquisarRelatorioRotasOnlinePorEmpresaHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0???] Gerar Relatorio Rotas Online por Empresa
 * 
 * @see gsan.gui.relatorio.micromedicao.ExibirGerarRelatorioRotasOnlinePorEmpresaAction
 * @see gsan.gui.relatorio.micromedicao.GerarRelatorioRotasOnlinePorEmpresaAction
 * @see gsan.relatorio.micromedicao.RelatorioRotasOnlinePorEmpresa
 * @see gsan.micromedicao.RepositorioMicromedicaoHBM#pesquisarRelatorioRotasOnlinePorEmpresa(PesquisarRelatorioRotasOnlinePorEmpresaHelper)
 * 
 * @author Victor Cisneiros
 * @date 28/10/2008
 */
public class ExibirGerarRelatorioRotasOnlinePorEmpresaAction extends GcomAction {

	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirGerarRelatorioRotasOnlinePorEmpresaAction");
		HttpSession sessao = request.getSession();
		Fachada fachada = Fachada.getInstancia();

		FiltroFaturamentoGrupo filtroFaturamentroGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentroGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		sessao.setAttribute("collectionFaturamentoGrupo", fachada.pesquisar(filtroFaturamentroGrupo, FaturamentoGrupo.class.getName()));

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		sessao.setAttribute("collectionEmpresa", fachada.pesquisar(filtroEmpresa, Empresa.class.getName()));
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
		filtroLeiturista.setCampoOrderBy("cliente.nome");
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		sessao.setAttribute("collectionLeiturista", fachada.pesquisar(filtroLeiturista, Leiturista.class.getName()));
		
		return retorno;
	}

}
