package gcom.gui.cadastro.imovel;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Exibe o Filtrar Imovel Perfil>>
 *
 * @author: Wallace Thierre
 * @Data: 30/09/2010
 * 
 */

public class ExibirFiltrarImovelPerfilAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){

		ActionForward retorno = actionMapping
		.findForward("filtrarImovelPerfil");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarImovelPerfilActionForm filtrarImovelPerfilActionForm = (FiltrarImovelPerfilActionForm) actionForm;
		
		String limparHidrometroCapacidades = httpServletRequest.getParameter("limparHidrometroCapacidades");

		//Quando for acessado pela primeira vez
		String primeiraVez = httpServletRequest.getParameter("menu");

		if(primeiraVez != null && !primeiraVez.equals("")){
			sessao.setAttribute("indicadorAtualizar", "1");   
			filtrarImovelPerfilActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}

		if(filtrarImovelPerfilActionForm.getIndicadorAtualizar()==null){
			filtrarImovelPerfilActionForm.setIndicadorAtualizar("1");
		}
		
		filtrarImovelPerfilActionForm.setColecaoHidrometroCapacidade(pesquisarHidrometroCapacidade());
		
		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")){

			filtrarImovelPerfilActionForm.setId("");
			filtrarImovelPerfilActionForm.setDescricao("");
			filtrarImovelPerfilActionForm.setIndicadorUso("");
			filtrarImovelPerfilActionForm.setIndicadorGeracaoAutomatica("");	
			filtrarImovelPerfilActionForm.setIndicadorInserirManterPerfil("");
			filtrarImovelPerfilActionForm.setIndicadorGerarDadosLeitura("");
			filtrarImovelPerfilActionForm.setIndicadorBloquearRetificacao("");
			filtrarImovelPerfilActionForm.setIndicadorGrandeConsumidor("");
			filtrarImovelPerfilActionForm.setIndicadorBloquearDadosSocial("");
			filtrarImovelPerfilActionForm.setIndicadorGeraDebitoSegundaViaConta("");	
			filtrarImovelPerfilActionForm.setUltimaAlteracao("");
			filtrarImovelPerfilActionForm.setIndicadorNegativacaoDoCliente("");
			filtrarImovelPerfilActionForm.setIndicadorPerfilTelemedido("");
			filtrarImovelPerfilActionForm.setHidrometroCapacidades(null);
		}
		
		if(limparHidrometroCapacidades!=null && limparHidrometroCapacidades.equals("true"))
		{
			filtrarImovelPerfilActionForm.setHidrometroCapacidades(null);
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
}
