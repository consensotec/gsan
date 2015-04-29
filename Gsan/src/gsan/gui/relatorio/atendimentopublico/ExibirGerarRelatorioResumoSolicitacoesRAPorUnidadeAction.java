package gsan.gui.relatorio.atendimentopublico;

import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
 * 
 * @see gsan.gui.relatorio.atendimentopublico.GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm
 * @see gsan.gui.relatorio.atendimentopublico.GerarRelatorioResumoSolicitacoesRAPorUnidadeAction
 * @see gsan.relatorio.atendimentopublico.RelatorioResumoSolicitacoesRAPorUnidade
 * 
 * @author Victor Cisneiros
 * @date 20/06/2008
 */
public class ExibirGerarRelatorioResumoSolicitacoesRAPorUnidadeAction extends GcomAction {
	
	public ActionForward execute(
			ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioResumoSolicitacoesRAPorUnidadeAction");
		HttpSession session = request.getSession();
		
		GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm form = (GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm) actionForm;
		
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = request.getParameter("objetoConsulta");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Pesquisar tipos de Solicita��o
		FiltroSolicitacaoTipo filtroSolicitacao = new FiltroSolicitacaoTipo();
		filtroSolicitacao.setConsultaSemLimites(true);
		filtroSolicitacao.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);
		Collection<SolicitacaoTipo> colecaoSolicitacao = fachada.pesquisar(
				filtroSolicitacao, SolicitacaoTipo.class.getName());
		
		if (colecaoSolicitacao == null || colecaoSolicitacao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Solicitacao Tipo");
		}
		session.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacao);
		
		if (request.getParameter("onchange") != null) {
			form.setEspecificacao(null);
		}

		// Pesquisar Especificacao (se apenas um tipo de Solicitacao selecionado)
		String[] solicitacaoTipo = form.getSolicitacaoTipo();
		if (solicitacaoTipo != null) {
			form.setSelectedSolicitacaoTipoSize(solicitacaoTipo.length+"");
		}
		if (solicitacaoTipo != null && solicitacaoTipo.length == 1) {
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, solicitacaoTipo[0]));
			filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
			filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);

			Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
					filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			
			if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
				session.setAttribute("colecaoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
			} else {
				session.setAttribute("colecaoEspecificacao", new ArrayList<SolicitacaoTipoEspecificacao>());
			}			
		} else {
			session.setAttribute("colecaoEspecificacao", new ArrayList<SolicitacaoTipoEspecificacao>());
		}
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			
			// [UC0376] - Pesquisar Unidade
			if (objetoConsulta.equals("1") || objetoConsulta.equals("2")) {
				this.pesquisarUnidadeOrganizacional(request, form, objetoConsulta);
			}
			
			// [UC0075] - Pesquisar Munic�pio
			else if (objetoConsulta.equals("3")) {
				this.pesquisarMunicipio(request, form, objetoConsulta);
			}
			
			// [UC0141] - Pesquisar Bairro
			else if (objetoConsulta.equals("4")) {
				this.pesquisarBairro(request, form, objetoConsulta);
			}
			
			// Pesquisar Usu�rio
			else if (objetoConsulta.equals("5")) {
				this.pesquisarUsuario(request, form, objetoConsulta);
			}
		}
		
		return retorno;
	}
	
	public void pesquisarUnidadeOrganizacional(HttpServletRequest request,
			GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm form, String objetoConsulta) {
		
		FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
		Integer idUnidade = null;
		if (objetoConsulta.equals("1")) {
			idUnidade = new Integer(form.getIdUnidade());
		} else {
			idUnidade = new Integer(form.getIdUnidadeSuperior());
		}
		
		filtroUnidade.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));
		Collection<UnidadeOrganizacional> colecaoUnidades = Fachada.getInstancia().pesquisar(
				filtroUnidade, UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidades != null && !colecaoUnidades.isEmpty()) {
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidades);
			
			if (objetoConsulta.equals("1")) {
				form.setIdUnidade(unidade.getId().toString());
				form.setNomeUnidade(unidade.getDescricao());
				request.getSession().setAttribute("unidadeEncontrada", "true");
			} else {
				form.setIdUnidadeSuperior(unidade.getId().toString());
				form.setNomeUnidadeSuperior(unidade.getDescricao());
				request.getSession().setAttribute("unidadeSuperiorEncontrada", "true");
			}
		} else {
			if (objetoConsulta.equals("1")) {
				form.setNomeUnidade("Unidade Organizacional Inexistente");
				request.getSession().removeAttribute("unidadeEncontrada");
			} else {
				form.setNomeUnidadeSuperior("Unidade Superior Inexistente");
				request.getSession().removeAttribute("unidadeSuperiorEncontrada");
			}
		}
	}

	public void pesquisarMunicipio(HttpServletRequest request,
			GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm form,
			String objetoConsulta) {
		
		FiltroMunicipio filtro = new FiltroMunicipio();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, new Integer(form.getIdMunicipio())));
		Collection<Municipio> Municipios = Fachada.getInstancia().pesquisar(filtro, Municipio.class.getName());
		
		if (Municipios != null && !Municipios.isEmpty()) {
			Municipio Municipio = (Municipio) Util.retonarObjetoDeColecao(Municipios);
			
			form.setIdMunicipio(Municipio.getId().toString());
			form.setNomeMunicipio(Municipio.getNome());
			request.getSession().setAttribute("municipioEncontrado", "true");
			
		} else {
			form.setIdMunicipio("");
			form.setNomeMunicipio("Municipio Inexistente");
			request.getSession().removeAttribute("municipioEncontrado");
		}
	}
	
	public void pesquisarUsuario(HttpServletRequest request,
			GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm form,
			String objetoConsulta) {
		
		FiltroUsuario filtro = new FiltroUsuario();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, new String(form.getLoginUsuario())));
		Collection<Usuario> usuarios = Fachada.getInstancia().pesquisar(filtro, Usuario.class.getName());
		
		if (usuarios != null && !usuarios.isEmpty()) {
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(usuarios);
			
			form.setLoginUsuario(usuario.getLogin());
			form.setNomeUsuario(usuario.getNomeUsuario());
			request.getSession().setAttribute("usuarioEncontrado", "true");
		} else {
			form.setLoginUsuario("");
			form.setNomeUsuario("Usuario Inexistente");
			request.getSession().removeAttribute("usuarioEncontrado");
		}
	}
	
	public void pesquisarBairro(HttpServletRequest request,
			GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm form, String objetoConsulta) {
		
		
		FiltroBairro filtro = new FiltroBairro();
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroBairro.MUNICIPIO_ID, new Integer(form.getIdMunicipio())));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroBairro.CODIGO, new Integer(form.getIdBairro())));
		
		Collection<Bairro> Bairros = Fachada.getInstancia().pesquisar(
				filtro, Bairro.class.getName());
		
		if (Bairros != null && !Bairros.isEmpty()) {
			Bairro Bairro = (Bairro) Util.retonarObjetoDeColecao(Bairros);
			
			form.setIdBairro("" + Bairro.getCodigo());
			form.setNomeBairro(Bairro.getNome());
			request.getSession().setAttribute("bairroEncontrado", "true");
		} else {
			form.setIdBairro("");
			form.setNomeBairro("Bairro Inexistente");
			request.getSession().removeAttribute("bairroEncontrado");
		}
	}

}
