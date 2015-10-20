package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfilHelper;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtrar Imovel Perfil 
 * @author Wallace Thierre
 * Data: 01/10/2010
 *
 */
public class FiltrarImovelPerfilAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse){
			
		ActionForward retorno = actionMapping
			.findForward("exibirManterImovelPerfil");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarImovelPerfilActionForm filtrarImovelPerfilActionForm = (FiltrarImovelPerfilActionForm) actionForm;
		
		String id = filtrarImovelPerfilActionForm.getId();
		String descricao = filtrarImovelPerfilActionForm.getDescricao();		
		String indicadorUso = filtrarImovelPerfilActionForm.getIndicadorUso();		
		String indicadorGeracaoAutomatica = filtrarImovelPerfilActionForm.getIndicadorGeracaoAutomatica();
		String indicadorInserirManterPerfil = filtrarImovelPerfilActionForm.getIndicadorInserirManterPerfil();
		String indicadorGerarDadosLeitura = filtrarImovelPerfilActionForm.getIndicadorGerarDadosLeitura();
		String indicadorBloquearRetificacao = filtrarImovelPerfilActionForm.getIndicadorBloquearRetificacao();
		String indicadorGrandeConsumidor = filtrarImovelPerfilActionForm.getIndicadorGrandeConsumidor();
		String indicadorBloquearDadosSocial = filtrarImovelPerfilActionForm.getIndicadorBloquearDadosSocial();
		String indicadorGeraDebitoSegundaViaConta = filtrarImovelPerfilActionForm.getIndicadorGeraDebitoSegundaViaConta();
		String tipoPesquisa = filtrarImovelPerfilActionForm.getTipoPesquisa();
		String indicadorNegativacaoDoCliente = filtrarImovelPerfilActionForm.getIndicadorNegativacaoDoCliente();
		String indicadorCorporativo = filtrarImovelPerfilActionForm.getIndicadorCorporativo();
		String indicadorPerfilTelemedido = filtrarImovelPerfilActionForm.getIndicadorPerfilTelemedido();
		String consumoMinimo = filtrarImovelPerfilActionForm.getConsumoMinimo();
		
		FiltroImovelPerfilHelper filtroImovelPerfilHelper = new FiltroImovelPerfilHelper();
		
		if(id != null && !id.trim().equals(""))
		{
			filtroImovelPerfilHelper.setId(Integer.valueOf(id));
		}
		
		if(descricao != null && !descricao.trim().equalsIgnoreCase(""))
		{
			filtroImovelPerfilHelper.setDescricao(descricao);
			filtroImovelPerfilHelper.setTipoPesquisa(tipoPesquisa);
		}
		
		if(indicadorUso != null && !indicadorUso.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorUso(Short.valueOf(indicadorUso));
			
		}
		
		if(indicadorGeracaoAutomatica != null && !indicadorGeracaoAutomatica.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorGeracaoAutomatica(Short.valueOf(indicadorGeracaoAutomatica));
		}
		
		if(indicadorInserirManterPerfil != null && !indicadorInserirManterPerfil.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorInserirManterPerfil(Short.valueOf(indicadorInserirManterPerfil));
		}
		
		if(indicadorGerarDadosLeitura != null && !indicadorGerarDadosLeitura.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorGerarDadosLeitura(Short.valueOf(indicadorGerarDadosLeitura));
			
		}
		
		if(indicadorBloquearRetificacao != null && !indicadorBloquearRetificacao.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorBloquearRetificacao(Short.valueOf(indicadorBloquearRetificacao));
		}
		
		if(indicadorGrandeConsumidor != null && !indicadorGrandeConsumidor.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorGrandeConsumidor(Short.valueOf(indicadorGrandeConsumidor));
			
		}
		
		if(indicadorBloquearDadosSocial!= null && !indicadorBloquearDadosSocial.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorBloquearDadosSocial(Short.valueOf(indicadorBloquearDadosSocial));
		}
		
		if(indicadorGeraDebitoSegundaViaConta != null && !indicadorGeraDebitoSegundaViaConta.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorGeraDebitoSegundaViaConta(Short.valueOf(indicadorGeraDebitoSegundaViaConta));
		}
		
		if(indicadorNegativacaoDoCliente != null && !indicadorNegativacaoDoCliente.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorNegativacaoDoCliente(Short.valueOf(indicadorNegativacaoDoCliente));
		}
		
		if(indicadorCorporativo != null && !indicadorCorporativo.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorCorporativo(Short.valueOf(indicadorCorporativo));
		}
		
		if(indicadorPerfilTelemedido != null && !indicadorPerfilTelemedido.trim().equals(""))
		{
			filtroImovelPerfilHelper.setIndicadorPerfilTelemedido(Short.valueOf(indicadorPerfilTelemedido));
		}
		
		if(Util.isInteger(consumoMinimo))
		{
			filtroImovelPerfilHelper.setConsumoMinimo(Integer.valueOf(consumoMinimo));
		}
		
		if(filtrarImovelPerfilActionForm.getHidrometroCapacidadesHidden()!=null && filtrarImovelPerfilActionForm.getHidrometroCapacidadesHidden().length()!=0)
		{
			filtroImovelPerfilHelper.setHidrometroCapacidades(filtrarImovelPerfilActionForm.getHidrometroCapacidadesHidden().split(","));
		}
			
		sessao.setAttribute("filtroImovelPerfilHelper", filtroImovelPerfilHelper);
		
		return retorno;
	}
}
