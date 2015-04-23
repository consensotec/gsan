package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.MensagemAcompanhamentoServico;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Magno Gouveia
 * @since 12/08/2011
 */
public class RemoverMensagemAcompanhamentoServicoPopupAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {

		ActionForward retorno = actionMapping.findForward("telaSucessoPopup");
		
		Fachada fachada = Fachada.getInstancia();

		CadastrarMensagemAcompanhamentoServicoPopupActionForm form = (CadastrarMensagemAcompanhamentoServicoPopupActionForm) actionForm;
		
		FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
		filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, httpServletRequest.getParameter(CadastrarMensagemAcompanhamentoServicoPopupAction.TXAC_ID_PARAMETER)));
		filtroArquivoTextoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("equipe");
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroArquivoTextoAcompanhamentoServico, ArquivoTextoAcompanhamentoServico.class.getName()));
		
		String[] ids = form.getIds();

		// FS0003 - Verificar Seleção de Mensagem
		if (ids == null || ids.length == 0) {
			throw new ActionServletException("atencao.nenhuma_mensagem_selecionada");
		}
		
		// FS0004 - Verificar Situação das Mensagens
		/* 
		 * Esta validação foi realizada no ExibirCadastrarMensagemAcompanhamentoServicoAction,
		 * não permitindo a seleção de uma mensagem cuja situação seja 'RECEBIDA', 
		 * sendo desnecessário o tratamento novamente
		 */
		
		fachada.remover(ids, MensagemAcompanhamentoServico.class.getName(), null, null);

		montarPaginaSucesso(httpServletRequest, 
							"Mensagens para a equipe " + arquivoTextoAcompanhamentoServico.getEquipe().getNome() + " removidas com sucesso.",
							"Retorna para cadastramento de mensagens",
							"exibirCadastrarMensagemAcompanhamentoServicoAction.do" + CadastrarMensagemAcompanhamentoServicoPopupAction.adicionarParametroIdArquivo(httpServletRequest));

		return retorno;
	}
}