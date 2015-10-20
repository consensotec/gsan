package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroVencimentoAlternativo;
import gcom.faturamento.VencimentoAlternativo;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.conta.InformarVencimentoAlternativoActionForm;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Alterar Vencimento da Conta do Portal da Caern
 * 
 * @author Rafael Pinto
 * 
 * @since 17/07/2013
 *
 */
public class ExibirInformarVencimentoAlternativoPortalCaernAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirInformarVencimentoAlternativoPortalCaernAction");
		HttpSession sessao = request.getSession(false);
		request.setAttribute("voltarServicos", true);
		
		
		InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm = (InformarVencimentoAlternativoActionForm) actionForm;
		
		boolean numeroMesesMinimoVencimentoAlternativoSuperiorPermitido = false;
		
		String ip = request.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.ALTERAR_VENCIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO);
		
		if ( sessao.getAttribute("matricula") != null && !sessao.getAttribute("matricula").equals("") ) {
			Integer matricula = (Integer) sessao.getAttribute("matricula");

			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			Short numeroMesesMinimoVencimentoAlternativo = sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento();
			
			FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo();
			filtroVencimentoAlternativo.adicionarParametro( new ParametroSimples(FiltroVencimentoAlternativo.IMOVEL_ID, matricula));
			
			Collection<VencimentoAlternativo> colecaoVencimentoAlternativo = Fachada.getInstancia().pesquisar(
					filtroVencimentoAlternativo, VencimentoAlternativo.class.getName());
			
			if ( colecaoVencimentoAlternativo != null && !colecaoVencimentoAlternativo.isEmpty() ) {
				VencimentoAlternativo vencimentoAlternativo = (VencimentoAlternativo) Util.retonarObjetoDeColecao(colecaoVencimentoAlternativo);
				
				Date dataImplantacao = vencimentoAlternativo.getDataImplantacao();
				Date dataAtual = new Date();
	
				int diferencaMes = Util.dataDiff(dataAtual,dataImplantacao);
	
				if (diferencaMes < numeroMesesMinimoVencimentoAlternativo ) {
					numeroMesesMinimoVencimentoAlternativoSuperiorPermitido = true;
				} 
			}
			
			if ( numeroMesesMinimoVencimentoAlternativoSuperiorPermitido ) {
					request.setAttribute("numeroMesesMinimo", numeroMesesMinimoVencimentoAlternativo +"");
					request.setAttribute("numeroMesesMinimoVencimentoAlternativoSuperiorPermitido", true);
					request.removeAttribute("voltarServicos");
					
					retorno = mapping.findForward("numeroMesesMinimoVencimentoAlternativoSuperiorPermitidoCaern");
			} else {
				Collection<ClienteImovel> colecaoClienteImovel =  obterColecaoClienteImovel(matricula);
	
				setarDadosFormESessao(informarVencimentoAlternativoActionForm,colecaoClienteImovel, sessao);
				sessao.setAttribute("portal", "ok");
			}
		}
		
		return retorno;
	}
	

	/**
	 * Este método seta as informações a serem exibidas para o usuário
	 * no form e na sessão, para que a JSP responsável processe os dados.
	 *
	 *@since 22/10/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosFormESessao(
			InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm,
			Collection<ClienteImovel> colecaoClienteImovel, HttpSession sessao) {
		
		Fachada fachada = Fachada.getInstancia();

		ClienteImovel clienteImovel = colecaoClienteImovel.iterator().next();
		
		informarVencimentoAlternativoActionForm.setDiaVencimentoGrupo("");

		if(clienteImovel.getImovel().getQuadra().getRota().getFaturamentoGrupo().getDiaVencimento()!=null
				&& !clienteImovel.getImovel().getQuadra().getRota().getFaturamentoGrupo().getDiaVencimento().equals(0)){

			informarVencimentoAlternativoActionForm
				.setDiaVencimentoGrupo(clienteImovel.getImovel().getQuadra().getRota().getFaturamentoGrupo().getDiaVencimento().toString());
		}
		
		informarVencimentoAlternativoActionForm.setDiaVencimentoAtual("");

		if(clienteImovel.getImovel().getDiaVencimento() != null && !clienteImovel.getImovel().getDiaVencimento().equals(0)){
			informarVencimentoAlternativoActionForm
				.setDiaVencimentoAtual(clienteImovel.getImovel().getDiaVencimento().toString());
		}
		

		FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo(FiltroVencimentoAlternativo.DATA_IMPLANTACAO);

		filtroVencimentoAlternativo
				.adicionarParametro(new ParametroSimples(
						FiltroVencimentoAlternativo.IMOVEL_ID, clienteImovel.getImovel().getId()));

		Collection<VencimentoAlternativo> vencimentosAlternativos = fachada.pesquisar(
				filtroVencimentoAlternativo, VencimentoAlternativo.class.getName());

		if (!Util.isVazioOrNulo(vencimentosAlternativos)) {

			VencimentoAlternativo vencimentoAlternativo = vencimentosAlternativos.iterator().next();
				
			informarVencimentoAlternativoActionForm.setDataAlteracaoVencimento("");

			if (vencimentoAlternativo.getDataImplantacao() != null) {
				informarVencimentoAlternativoActionForm.setDataAlteracaoVencimento(
						new SimpleDateFormat("dd/MM/yyyy").format(vencimentoAlternativo.getDataImplantacao()));
			}	
			
			sessao.setAttribute("vencimentoAlternativo", vencimentoAlternativo);
		}

		if ( informarVencimentoAlternativoActionForm.getDiaVencimentoAtual() != null &&
				!informarVencimentoAlternativoActionForm.getDiaVencimentoAtual().equals("") ) {
			sessao.setAttribute("vencimentoAtual", informarVencimentoAlternativoActionForm.getDiaVencimentoAtual());
		} else {
			sessao.setAttribute("vencimentoAtual", informarVencimentoAlternativoActionForm.getDiaVencimentoGrupo());
		}
		
		
		sessao.setAttribute("colecaoNovoDiaVencimento",criarColecaoNovoDiaPagamento(clienteImovel, sessao));

		if (clienteImovel.getImovel().getIndicadorVencimentoMesSeguinte() != null){
			informarVencimentoAlternativoActionForm.setIndicadorVencimentoMesSeguinte(clienteImovel.getImovel().getIndicadorVencimentoMesSeguinte().toString());
		}else{
			informarVencimentoAlternativoActionForm.setIndicadorVencimentoMesSeguinte("2");
		}
		
		sessao.setAttribute("imovel", clienteImovel.getImovel());
	}

	/**
	 *@since 03/02/2012
	 *@author Arthur Carvalho
	 */
	private Collection<Integer> criarColecaoNovoDiaPagamento(
		ClienteImovel clienteImovel, HttpSession sessao) {
	
			
	List<Integer> colecaoNovoDiaVencimento = new ArrayList<Integer>();
	
	SistemaParametro parametrosSistema = this.getFachada().pesquisarParametrosDoSistemaAtualizados();
	
	if(Util.verificarNaoVazio(parametrosSistema.getDiasVencimentoAlternativo())){
		String[] diasVencimento = parametrosSistema.getDiasVencimentoAlternativo().split(";");
		
		for (String diaAtual : diasVencimento) {
			if(Util.verificarNaoVazio(diaAtual)){
				colecaoNovoDiaVencimento.add(new Integer(diaAtual.trim()));						
			}
		}
		
		Collections.sort(colecaoNovoDiaVencimento);

		return colecaoNovoDiaVencimento;
	}
	
	String empresaCAERN = ""+ parametrosSistema.getCodigoEmpresaFebraban(); 
	if (empresaCAERN.equals("6") ) {

		colecaoNovoDiaVencimento.add(1);
		colecaoNovoDiaVencimento.add(5);
		colecaoNovoDiaVencimento.add(10);
		colecaoNovoDiaVencimento.add(25);
		colecaoNovoDiaVencimento.add(27);
		colecaoNovoDiaVencimento.add(30);
		
	} else {
	
		int i = 
			clienteImovel.getImovel().getQuadra().getRota().
				getFaturamentoGrupo().getDiaVencimento() + 1;
		
		
		int ultimoDiaVencimentoAlternativo = 30;
		
		if(parametrosSistema.getUltimoDiaVencimentoAlternativo() != null ){
			ultimoDiaVencimentoAlternativo = parametrosSistema.getUltimoDiaVencimentoAlternativo().intValue();
		}

		for (; i <= ultimoDiaVencimentoAlternativo; i++) {
			colecaoNovoDiaVencimento.add(i);
		}
		
	}
	return colecaoNovoDiaVencimento;
}

	/**
	 * Obtém o ClienteImovel relacionado ao imóvel informado,
	 * onde a relação entre o cliente e o imóvel seja de Usuário.
	 *
	 *@since 22/10/2009
	 *@author Marlon Patrick
	 */
	private Collection<ClienteImovel> obterColecaoClienteImovel(Integer matricula) {
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota.faturamentoGrupo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID,
				matricula));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));
		
		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));
		
		return Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());
	}

}