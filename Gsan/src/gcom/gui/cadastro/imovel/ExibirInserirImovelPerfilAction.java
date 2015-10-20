package gcom.gui.cadastro.imovel;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Wallace Thierre
 * @created 24/09/2010
 */
public class ExibirInserirImovelPerfilAction extends GcomAction {
	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		//Seta o caminho de Retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirImovelPerfil");
		
		InserirImovelPerfilActionForm inserirImovelPerfilActionForm = (InserirImovelPerfilActionForm) actionForm;	
		
		String pesquisarPermissaoEspecial = httpServletRequest.getParameter("pesquisarPermissaoEspecial");
		
		if(httpServletRequest.getParameter("menu")!=null)
		{
			inserirImovelPerfilActionForm.setColecaoHidrometroCapacidade(pesquisarHidrometroCapacidade());
			inserirImovelPerfilActionForm.setIdPermissaoEspecial(null);
			inserirImovelPerfilActionForm.setDescricaoPermissaoEspecial(null);
			inserirImovelPerfilActionForm.setConsumoMinimo(null);
			inserirImovelPerfilActionForm.setHidrometroCapacidades(null);
		}
		
		if(pesquisarPermissaoEspecial!=null)
		{
			pesquisarPermissaoEspecial(inserirImovelPerfilActionForm,httpServletRequest);
		}
		
		String indicadorClienteCorporativo = Fachada.getInstancia().obterValorParametro(ParametroSistema.INDICADOR_CONTROLE_GRANDE_CLIENTE);
        
        if(indicadorClienteCorporativo != null && indicadorClienteCorporativo.equals(ConstantesSistema.SIM.toString())){
			httpServletRequest.setAttribute("controleClienteCorporativo", true);	 				
        }
		
		return retorno;		
	}
	
	@SuppressWarnings("unchecked")
	private Collection<HidrometroCapacidade> pesquisarHidrometroCapacidade(){
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<HidrometroCapacidade> colecaoHidrometroCapacidade = getFachada().
				pesquisar(filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
		
		return colecaoHidrometroCapacidade;
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarPermissaoEspecial(InserirImovelPerfilActionForm inserirImovelPerfilActionForm,HttpServletRequest httpServletRequest){
		FiltroPermissaoEspecial filtroPermissaoEspecial;
		
		if(Util.isInteger(inserirImovelPerfilActionForm.getIdPermissaoEspecial()))
		{
			filtroPermissaoEspecial = new FiltroPermissaoEspecial();
			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPermissaoEspecial.ID, 
					Integer.valueOf(inserirImovelPerfilActionForm.getIdPermissaoEspecial())));
			
			PermissaoEspecial permissaoEspecial = (PermissaoEspecial) Util.retonarObjetoDeColecao(getFachada().
					pesquisar(filtroPermissaoEspecial, PermissaoEspecial.class.getName()));
			
			if(permissaoEspecial!=null)
			{
				inserirImovelPerfilActionForm.setDescricaoPermissaoEspecial(permissaoEspecial.getDescricao());
				httpServletRequest.removeAttribute("idPermissaoEspecialNaoEncontrado");
			}
			else
			{
				inserirImovelPerfilActionForm.setDescricaoPermissaoEspecial("Permissão Especial não encontrada");
				httpServletRequest.setAttribute("idPermissaoEspecialNaoEncontrado", "true");
			}
		}
	}
}
