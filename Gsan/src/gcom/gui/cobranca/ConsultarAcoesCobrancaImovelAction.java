package gcom.gui.cobranca;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.AcoesCobrancaImovelHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1370] Consultar Ações de Cobrança por Imóvel
 * 
 * @author Davi Menezes
 * @date 15/08/2012
 *
 */
public class ConsultarAcoesCobrancaImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("acoesCobrancaImovel");
		
		ExibirConsultarAcoesCobrancaImovelActionForm form = 
				(ExibirConsultarAcoesCobrancaImovelActionForm) actionForm;
		
		Fachada fachada = this.getFachada();
		
		if(form.getIdImovel() != null && !form.getIdImovel().equals("")){
			this.pesquisarImovel(form, fachada);
		}else{
			throw new ActionServletException("atencao.adicionar_debito_imovel_obrigatorio");
		}
		
		if(form.getPeriodoInicialAcao() != null && !form.getPeriodoInicialAcao().equals("") &&
				form.getPeriodoFinalAcao() != null && !form.getPeriodoFinalAcao().equals("")){
			this.validarPeriodoAcao(form);
		}
		
		try {
			this.montarDadosImovel(form, fachada);
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		Integer idImovel = Integer.parseInt(form.getIdImovel());
		Collection<AcoesCobrancaImovelHelper> colecaoHelper = 
				fachada.pesquisarAcoesCobrancaImovel(idImovel, form.getPeriodoInicialAcao(), form.getPeriodoFinalAcao());
		
		if(!Util.isVazioOrNulo(colecaoHelper)){
			httpServletRequest.setAttribute("colecaoHelper", colecaoHelper);
		}else{
			httpServletRequest.removeAttribute("colecaoHelper");
			throw new ActionServletException("atencao.nao_existe_acoes_cobranca_imovel");
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisar o Imóvel Digitado
	 * 
	 * @author Davi Menezes
	 * @date 15/08/2012
	 * 
	 */
	private void pesquisarImovel(ExibirConsultarAcoesCobrancaImovelActionForm form, Fachada fachada) {
		Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(form.getIdImovel()));
		if(imovel == null){
			throw new ActionServletException("atencao.imovel.inexistente");
		}
	}
	
	/**
	 * Validar Período Ação
	 * 
	 * @author Davi Menezes
	 * @date 15/08/2012
	 */
	private void validarPeriodoAcao(ExibirConsultarAcoesCobrancaImovelActionForm form) {
		String dataInicial = Util.formatarDataSemBarra(Util.converteStringParaDate(form.getPeriodoInicialAcao()));
		String dataFinal = Util.formatarDataSemBarra(Util.converteStringParaDate(form.getPeriodoFinalAcao()));
		
		if(Integer.parseInt(dataInicial) > Integer.parseInt(dataFinal)){
			throw new ActionServletException("atencao.data_inicial_acao_maior_data_final_acao");
		}
	}
	
	/**
	 * Montar Dados do Imóvel
	 * 
	 * @author Davi Menezes
	 * @date 15/08/2012
	 */
	private void montarDadosImovel(ExibirConsultarAcoesCobrancaImovelActionForm form, Fachada fachada) throws ControladorException {
		if(form.getIdImovel() != null && !form.getIdImovel().equals("")){
			Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(form.getIdImovel()));
			
			if(imovel != null){
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
				
				if(imovel.getLigacaoAguaSituacao() != null){
					form.setDescricaoSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
				}
				if(imovel.getLigacaoEsgotoSituacao() != null){
					form.setDescricaoSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
				}
			}
			
			String enderecoImovel = fachada.pesquisarEnderecoFormatado(Integer.parseInt(form.getIdImovel()));
			form.setEnderecoImovel(enderecoImovel);
			
			FaturamentoGrupo faturamentoGrupo = fachada.pesquisarGrupoImovel(Integer.parseInt(form.getIdImovel()));
			if(faturamentoGrupo != null){
				form.setGrupoCobrancaAtual(faturamentoGrupo.getDescricaoAbreviada());
			}
		}
	}
}
