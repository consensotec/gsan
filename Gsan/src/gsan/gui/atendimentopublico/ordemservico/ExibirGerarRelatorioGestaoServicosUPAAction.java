package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0776] Filtrar Relatório de Gestão de Serviços UPA
 * 
 * @see gsan.gui.relatorio.atendimentopublico.GerarRelatorioGestaoServicosUPAAction
 * @see gsan.gui.atendimentopublico.ordemservico.GerarRelatorioGestaoServicosUPAActionForm
 * @see gsan.relatorio.atendimentopublico.RelatorioGestaoServicosUPA
 * 
 * @author Victor Cisneiros
 * @date 19/05/2008
 */
public class ExibirGerarRelatorioGestaoServicosUPAAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("exibirGerarRelatorioGestaoServicosUPAAction");
			
		GerarRelatorioGestaoServicosUPAActionForm form = (GerarRelatorioGestaoServicosUPAActionForm) actionForm;
			
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = request.getParameter("objetoConsulta");
			
		// Pesquisar Tipos de Serviços
		FiltroServicoTipo filtroTipo = new FiltroServicoTipo();
		filtroTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADORTERCEIRIZADO, 1));
		filtroTipo.setConsultaSemLimites(true);
		filtroTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
			
		String className = ServicoTipo.class.getName();
			
		Collection tiposServico = Fachada.getInstancia().pesquisar(filtroTipo, className);
			
		if (tiposServico == null || tiposServico.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Tipo de Serviço");
		} else {
			request.setAttribute("colecaoTipoServico", tiposServico);
		}
			
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
				
			// [UC0376] - Pesquisar Unidade
			if (objetoConsulta.trim().equals("1") || objetoConsulta.trim().equals("2")) {
				this.pesquisarUnidadeOrganizacional(form, objetoConsulta);
			}
				
			// [UC0777] - Pesquisar Empresa
			else if (objetoConsulta.trim().equals("3")) {
				this.pesquisarEmpresa(form, objetoConsulta);
			}
		}
			
		setaRequest(request, form);

		return retorno;
	}
	
	public void setaRequest(HttpServletRequest request, GerarRelatorioGestaoServicosUPAActionForm aform) {
		// Unidade Organizacional
		if ( aform.getIdUnidadeOrganizacional() != null && 
			!aform.getIdUnidadeOrganizacional().equals("") &&
			 aform.getDescricaoUnidadeOrganizacional() != null && 
			!aform.getDescricaoUnidadeOrganizacional().equals("")) {
				request.setAttribute("unidadeOrganizacionalEncontrada", "true");
		}
		
		// Unidade Superior
		if ( aform.getIdUnidadeSuperior() != null && 
			!aform.getIdUnidadeSuperior().equals("") &&
			 aform.getDescricaoUnidadeSuperior() != null && 
			!aform.getDescricaoUnidadeSuperior().equals("")) {
				request.setAttribute("unidadeSuperiorEncontrada", "true");
		}
		
		// Empresa
		if ( aform.getIdEmpresa() != null && 
			!aform.getIdEmpresa().equals("") &&
			 aform.getDescricaoEmpresa() != null && 
			!aform.getDescricaoEmpresa().equals("")) {
				request.setAttribute("empresaEncontrada", "true");
		}
	}
	
	public void pesquisarEmpresa(GerarRelatorioGestaoServicosUPAActionForm aform, String objectConsulta) {
		if (aform.getIdEmpresa() == null || 
			aform.getIdEmpresa().trim().equals("")) {
			return;
		}
	
		FiltroEmpresa filtro = new FiltroEmpresa();
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.ID, new Integer(aform.getIdEmpresa())));
		
		String className = Empresa.class.getName();
		
		Collection empresas = Fachada.getInstancia().pesquisar(filtro, className);
		
		if (empresas != null && !empresas.isEmpty()) {
			Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(empresas);
			aform.setIdEmpresa(empresa.getId().toString());
			aform.setDescricaoEmpresa(empresa.getDescricao());
		} else {
			aform.setIdEmpresa("");
			aform.setDescricaoEmpresa("Empresa Inexistente");
		}
	}
	
	public void pesquisarUnidadeOrganizacional(GerarRelatorioGestaoServicosUPAActionForm aform, String objetoConsulta) {
		
		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
		
		Integer idUnidade = null;
		if (objetoConsulta.equals("1")) {
			idUnidade = new Integer(aform.getIdUnidadeOrganizacional().trim());
		} else if (objetoConsulta.equals("2")) {
			idUnidade = new Integer(aform.getIdUnidadeSuperior().trim());
		}
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));
		
		String className = UnidadeOrganizacional.class.getName();
		
		Collection unidades = Fachada.getInstancia().pesquisar(filtro, className);
		
		if (unidades != null && !unidades.isEmpty()) {
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(unidades);
			
			if (objetoConsulta.trim().equals("1")) {
				aform.setIdUnidadeOrganizacional(unidade.getId().toString());
				aform.setDescricaoUnidadeOrganizacional(unidade.getDescricao());
			} else { // 2
				aform.setIdUnidadeSuperior(unidade.getId().toString());
				aform.setDescricaoUnidadeSuperior(unidade.getDescricao());
			}
			
		} else {
			if (objetoConsulta.trim().equals("1")) {
				aform.setIdUnidadeOrganizacional("");
				aform.setDescricaoUnidadeOrganizacional("Unidade Organizacional Inexistente");
			} else { // 2
				aform.setIdUnidadeSuperior("");
				aform.setDescricaoUnidadeSuperior("Unidade Superior Inexistente");
			}
		}
	}

}
