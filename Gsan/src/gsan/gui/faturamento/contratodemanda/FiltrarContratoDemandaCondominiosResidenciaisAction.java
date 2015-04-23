package gsan.gui.faturamento.contratodemanda;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.SessaoHttpListener;
import gsan.util.FachadaException;
import gsan.util.Util;

/**
 * [UC1229] - Manter Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 23/09/2011
 *
 */
public class FiltrarContratoDemandaCondominiosResidenciaisAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward retorno = mapping.findForward("manterContratoDemandaCondominiosResidenciais");
		FiltrarContratoDemandaCondominiosResidenciaisActionForm form = (FiltrarContratoDemandaCondominiosResidenciaisActionForm) formulario;
		
		String numeroContrato = (Util.verificarNaoVazio(form.getNumeroContrato()) ? form.getNumeroContrato() : null);
		Integer matriculaImovel = (Util.verificarNaoVazio(form.getMatriculaImovel()) ? Integer.valueOf(form.getMatriculaImovel()) : null);
		String strDataInicio1 = form.getDataInicioContrato();
		String strDataInicio2 = form.getDataInicioContrato2();
		String strDataFim1 = form.getDataFimContrato();
		String strDataFim2 = form.getDataFimContrato2();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataIni1 = null;
		Date dataIni2 = null;
		Date dataFinal1 = null;
		Date dataFinal2 = null;
		if(Util.verificarNaoVazio(form.getDataInicioContrato())){
			dataIni1 = sdf.parse(strDataInicio1);
		}
		
		if(Util.verificarNaoVazio(form.getDataInicioContrato2())){
			dataIni2 = sdf.parse(strDataInicio2);
		}
		
		if(Util.verificarNaoVazio(form.getDataFimContrato())){
			dataFinal1 = sdf.parse(strDataFim1);
		}
		
		if(Util.verificarNaoVazio(form.getDataFimContrato2())){
			dataFinal2 = sdf.parse(strDataFim2);
		}
		
		if(Util.verificarNaoVazio(form.getNumeroContrato())){
			numeroContrato = form.getNumeroContrato();
		}
		
		FiltrarContratoDemandaCondominiosResidenciaisHelper filtrarHelper = new FiltrarContratoDemandaCondominiosResidenciaisHelper(
			numeroContrato, matriculaImovel, dataIni1, dataIni2, dataFinal1, dataFinal2);
		
		try{
			List<ContratoDemandaCondominiosResidenciaisHelper> helper = this.getFachada().pesquisarContratoDemandaCondominiosResidenciais(filtrarHelper);
			if(!Util.isVazioOrNulo(helper)){
				if(helper.size() == 1){
					String indicadorAtualizar = request.getParameter("atualizar");
					if(indicadorAtualizar.equals("2")){
						request.setAttribute("colecaoHelper", helper);
					}else if(indicadorAtualizar.equals("1")){
						if(helper.get(0).getSituacao().equalsIgnoreCase("encerrado")){
							request.setAttribute("colecaoHelper", helper);
						}else{
							HttpSession sessao = request.getSession();
							sessao.setAttribute("voltarFiltrar", true);
							request.setAttribute("colecaoHelper", helper);
							request.setAttribute("idContrato", helper.get(0).getNumeroContrato());
							retorno = mapping.findForward("atualizarContratoDemandaCondominiosResidenciais");
						}
					}
				}else{
					request.setAttribute("colecaoHelper", helper);
				}
			}else{
				throw new ActionServletException("atencao.contrato_demanda_contratos_nao_encontrados");
			}
		}catch (FachadaException e) {
			throw new ActionServletException(e.getMessage());
		}
		
		return retorno;
	}
}
