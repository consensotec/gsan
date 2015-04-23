package gsan.gui.portal.caer;

import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * serviços na Loja Virtual da Caer
 * </p>
 * 
 * @author Davi Menezes
 * @date 05/09/2012
 */
public class ExibirInserirSolicitacaoServicosPortalCaerAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirInserirSolicitacaoServicosPortalCaer");

		HttpSession sessao = httpServletRequest.getSession(false);

		httpServletRequest.setAttribute("voltarServicos", true);

		Fachada fachada = Fachada.getInstancia();

		InserirSolicitacaoServicosPortalCaerActionForm form = (InserirSolicitacaoServicosPortalCaerActionForm) actionForm;
		
		if (httpServletRequest.getParameter("init") != null && httpServletRequest.getParameter("init").equals("1")) {
			form.reset();
			sessao.removeAttribute("dataPrevista");
		}
		
		// 2.1.3
		Integer matricula = (Integer) sessao.getAttribute("matricula");
		
		if (!Util.verificarIdNaoVazio(matricula.toString())) {
			httpServletRequest.setAttribute("exception", "Matrícula inválida ou vazia!");
			return retorno;
		}
		
		String enderecoFormatado = null;
		try {
			enderecoFormatado = this.getFachada().pesquisarEnderecoFormatado(matricula);
		} catch (ControladorException e) {
			httpServletRequest.setAttribute("exception", "Erro no sistema!");
			return retorno;
		}
		
		if (enderecoFormatado != null) {
			sessao.setAttribute("enderecoImovel", enderecoFormatado);
		}
		
		sessao.setAttribute("dataSolicitacao", Util.formatarData(new Date()));

		// 2.2.4 Tipo Solicitação
		Collection<Object[]> objectsSolicitacaoTipo = fachada.pesquisarSolicitacaoTipoLojaVirtual();

		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = new ArrayList<SolicitacaoTipo>();
		
		if (!Util.isVazioOrNulo(objectsSolicitacaoTipo)) {
			for (Object[] tipo : objectsSolicitacaoTipo) {
				SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
				solicitacaoTipo.setId(Integer.parseInt(((Short) tipo[0]).toString()));
				solicitacaoTipo.setDescricao((String) tipo[1]);
				
				colecaoSolicitacaoTipo.add(solicitacaoTipo);
			}
			
			sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
		} else {
			httpServletRequest.setAttribute("exception", "Nenhum tipo de solicitação encontrado!");
			return retorno;
		}
		
		// 2.2.5 Tipo de Especificação
		if (Util.verificarIdNaoVazio(form.getSolicitacaoTipo())) {
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_LOJA_VIRTUAL, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, new Integer(form.getSolicitacaoTipo())));
			Collection<?> colecaoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			
			if (!Util.isVazioOrNulo(colecaoEspecificacao)) {
				sessao.setAttribute("colecaoEspecificacao", colecaoEspecificacao);
			} else {
				httpServletRequest.setAttribute("exception", "Nenhuma especificação encontrada!");
				return retorno;
			}
		}else{
			form.setEspecificacao(null);
			sessao.removeAttribute("colecaoEspecificacao");
		}
		
		// Calcula a data prevista para o atendimento a solicitação
		Date dataPrevista = null;
		if(Util.verificarIdNaoVazio(form.getEspecificacao())){
			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(new Date(),
							Integer.parseInt(form.getEspecificacao()));

			dataPrevista = Util
					.formatarDataFinal(definirDataPrevistaUnidadeDestinoEspecificacaoHelper
							.getDataPrevista());
			
			sessao.setAttribute("dataPrevista", Util.formatarData(dataPrevista));
		}else{
			sessao.removeAttribute("dataPrevista");
		}
		
		if (form.getNomeSolicitante() == null)
			form.setNomeSolicitante("");
		if (form.getEmail() == null)
			form.setEmail("");
		if (form.getObservacoes() == null)
			form.setObservacoes("");
		if (form.getPontoReferencia() == null)
			form.setPontoReferencia("");
		
		return retorno;
	}
}