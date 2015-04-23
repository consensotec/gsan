package gsan.gui.relatorio.seguranca;

import gsan.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gsan.atendimentopublico.registroatendimento.MeioSolicitacao;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.FiltroFuncionalidade;
import gsan.seguranca.acesso.FiltroOperacao;
import gsan.seguranca.acesso.Funcionalidade;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.transacao.FiltroTabelaColuna;
import gsan.seguranca.transacao.TabelaColuna;
import gsan.util.ConstantesInterfaceGSAN;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
 * 
 * @author Hugo Amorim
 * @date 08/09/2010
 */
public class ExibirGerarRelatorioAlteracoesSistemaColunaAction extends
		GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.
			findForward("exibirGerarRelatorioAlteracoesSistemaColunaAction");
	
		GerarRelatorioAlteracoesSistemaColunaForm form = 
			(GerarRelatorioAlteracoesSistemaColunaForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
	
		if(httpServletRequest.getParameter("tipoRelatorio")!=null){
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			httpServletRequest.setAttribute("tipoRelatorio", tipoRelatorio);
			form.setTipoRelatorio(tipoRelatorio);
		}
		
		//	Valida se adicionou ou não unidade organizacional 
		//no AdicionarUnidadeOrganizacionalAction
		if(httpServletRequest.getAttribute("ADICIONOU")!=null){
			boolean adicionou = (Boolean) httpServletRequest.getAttribute("ADICIONOU");
			
			if(adicionou){
				form.setIdUnidadeOrganizacional("");
				form.setDescUnidadeOrganizacional("");
			}else{
				form.setIdUnidadeOrganizacional("");
				form.setDescUnidadeOrganizacional("UNIDADE JÁ EXISTE.");
			}
		}
		
		//Verificaca se houve pesquisa na tela por id's
		this.verificarPesquisasPorEnter(form,sessao);
		
		//Se tipo Relatorio estiver como nulo.
		if(form.getTipoRelatorio()==null){
			//Tipo Relatorio DEFAULT - Usuário
 			form.setTipoRelatorio("1");
		}
		//Se periodo final estiver como nulo.
		if(httpServletRequest.getParameter("menu")!=null){
			//Seta data atual como valor
 			form.setPeriodoFinal(Util.formatarData(new Date()));
		}
		
		
		//	Pesquisar Meios de Solicitação
		//para apresentação no filtro.
		if(Util.isVazioOrNulo(form.getColecaoMeiosSolicitacao())){
			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
			filtroMeioSolicitacao.adicionarParametro(
					new ParametroSimples(FiltroMeioSolicitacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroMeioSolicitacao.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
			Collection<MeioSolicitacao> colecaoMeiosSolicitacao = 
				this.getFachada().pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoMeiosSolicitacao)){
				form.setColecaoMeiosSolicitacao(colecaoMeiosSolicitacao);
			}
		}
			
		return retorno;
	}
	
	private void verificarPesquisasPorEnter(
			GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao) {
		
		//Pesquisar Localidade
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			pesquisarLocalidade(form,sessao);			
		}
		
		//Pesquisar Funcionalidade e operações herdeiras
		if(Util.verificarNaoVazio(form.getIdFuncionalidade())){
			pesquisarFuncionalidade(form,sessao);			
		}
		//Caso não encontre limpa as operações.
		else{
			form.setColecaoOperacoes(null);
		}
		
		//Pesquisar Coluna
		if(Util.verificarNaoVazio(form.getIdColuna())){
			pesquisarColuna(form,sessao);			
		}
		
		//Pesquisar Unidade Superior
		if(Util.verificarNaoVazio(form.getIdUnidadeSuperior())){
			pesquisarUnidadeSuperior(form,sessao);			
		}
		
		//Pesquisar Unidade Organizacional
		if(Util.verificarNaoVazio(form.getIdUnidadeOrganizacional())){
			pesquisarUnidadeOrganizacional(form,sessao);			
		}
		
		//Pesquisar Usuario
		if(Util.verificarNaoVazio(form.getIdUsuario())){
			pesquisarUsuario(form,sessao);			
		}
		
		//Pesquisar Gerencia Regional
		if(Util.verificarNaoVazio(form.getIdGerenciaRegional())){
			pesquisarGerenciaRegional(form,sessao);			
		}
		
		//Pesquisar Unidade Negócio
		 if (sessao.getAttribute("colecaoUnidadeNegocio") == null){
			pesquisarUnidadeNegocio(form,sessao);			
		}
		
		//Pesquisar Localidade
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			pesquisarLocalidade(form,sessao);			
		}

	}
	
	private void pesquisarUnidadeNegocio(GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao){

	
	        	
	        FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
	    	filtroUnidadeNegocio.setConsultaSemLimites(true);
	    	filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);		
	    	filtroUnidadeNegocio.adicionarParametro(
	    			new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
	    			ConstantesSistema.INDICADOR_USO_ATIVO));
	    	
	    	Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
	    		this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

	    	if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
	    		throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
	    				ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);
	    	}
	    	
	    	sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);		
	}
	
	private void pesquisarGerenciaRegional(GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao){

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,form.getIdGerenciaRegional()));

		
		Collection<GerenciaRegional> colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoGerenciaRegional)) {
				form.setIdGerenciaRegional("");
				form.setDescGerenciaRegional("Gerência Regional Inexistente");
				sessao.removeAttribute("gerenciaRegionalEncontrada");
				return;
		}
		
		GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
		
		form.setIdGerenciaRegional(gerenciaRegional.getId().toString());
		form.setDescGerenciaRegional(gerenciaRegional.getNome());	
		sessao.setAttribute("gerenciaRegionalEncontrada","");
	}

	private void pesquisarFuncionalidade(GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao){

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID,
				new Integer(form.getIdFuncionalidade())));

		Collection<Funcionalidade> colecaoFuncionalidade = 
			this.getFachada().pesquisar(filtroFuncionalidade, Funcionalidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoFuncionalidade)) {
				form.setIdFuncionalidade("");
				form.setDescFuncionalidade("Funcionalidade Inexistente");
				sessao.removeAttribute("funcionalidadeEncontrada");
				return;
		}else{
		
			Funcionalidade funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
			
			form.setIdFuncionalidade(funcionalidade.getId().toString());
			form.setDescFuncionalidade(funcionalidade.getDescricao());	
			sessao.setAttribute("funcionalidadeEncontrada","");
			
			FiltroOperacao filtroOperacao = new FiltroOperacao();
			
			filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.FUNCIONALIDADE_ID
					,funcionalidade.getId()));
			
			Collection<Operacao> colecaoOperacoes = 
				this.getFachada().pesquisar(filtroOperacao, Operacao.class.getName());
			
			form.setColecaoOperacoes(colecaoOperacoes);
		
		}
	}
	
	private void pesquisarLocalidade(GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao){

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
				,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				form.setIdLocalidade("");
				form.setDescLocalidade("Localidade Inexistente");
				sessao.removeAttribute("localidadeEncontrada");
				return;
		}
		
		Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
		
		form.setIdLocalidade(localidade.getId().toString());
		form.setDescLocalidade(localidade.getDescricao());	
		sessao.setAttribute("localidadeEncontrada","");
	}
	
	private void pesquisarColuna(GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao){

		FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
		filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.ID,form.getIdColuna()));
		
		Collection<TabelaColuna> colecaoTabelaColuna = 
			this.getFachada().pesquisar(filtroTabelaColuna, TabelaColuna.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoTabelaColuna)) {
				form.setIdColuna("");
				form.setDescColuna("Coluna Inexistente");
				sessao.removeAttribute("colunaEncontrada");
				return;
		}
		
		TabelaColuna tabelaColuna = (TabelaColuna) Util.retonarObjetoDeColecao(colecaoTabelaColuna);
		
		form.setIdColuna(tabelaColuna.getId().toString());
		form.setDescColuna(tabelaColuna.getDescricaoColuna());
		sessao.setAttribute("colunaEncontrada","");
	}
	
	private void pesquisarUnidadeSuperior(GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao){

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, 
				new Integer(form.getIdUnidadeSuperior())));
		
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade(
				FiltroUnidadeOrganizacional.UNIDADE_SUPERIOR);

		Collection colecaoUnidadeSuperior = this.getFachada().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoUnidadeSuperior)) {
				form.setIdUnidadeSuperior("");
				form.setDescUnidadeSuperior("Unidade Superior Inexistente");
				sessao.removeAttribute("unidadeSuperiorEncontrada");
				return;
		}
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeSuperior);
		
		form.setIdUnidadeSuperior(unidadeOrganizacional.getUnidadeSuperior().getId().toString());
		form.setDescUnidadeSuperior(unidadeOrganizacional.getUnidadeSuperior().getDescricao());
		sessao.setAttribute("unidadeSuperiorEncontrada","");
	}
	
	private void pesquisarUnidadeOrganizacional(GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao){

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, 
				new Integer(form.getIdUnidadeOrganizacional())));

		Collection colecaoUnidadeOrganizacional = this.getFachada().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoUnidadeOrganizacional)) {
				form.setIdUnidadeOrganizacional("");
				form.setDescUnidadeOrganizacional("Unidade Organizacional Inexistente");
				sessao.removeAttribute("unidadeOrganizacionalEncontrada");
				return;
		}
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
		
		form.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
		form.setDescUnidadeOrganizacional(unidadeOrganizacional.getDescricao());
		sessao.setAttribute("unidadeOrganizacionalEncontrada","");
	}
	
	private void pesquisarUsuario(GerarRelatorioAlteracoesSistemaColunaForm form, HttpSession sessao){

		FiltroUsuario filtroUsuario = new FiltroUsuario();

		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, new Integer(form.getIdUsuario())));

		Collection colecaoUsuario = this.getFachada().pesquisar(
				filtroUsuario, Usuario.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoUsuario)) {
				form.setIdUsuario("");
				form.setDescUsuario("Usuario Inexistente");
				sessao.removeAttribute("usuarioEncontrado");
				return;
		}
		
		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
		
		form.setIdUsuario(usuario.getId().toString());
		form.setDescUsuario(usuario.getNomeUsuario());
		sessao.setAttribute("usuarioEncontrado","");
	}
}
