package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.bean.ComandoAtualizacaoCadastralDMHelper;
import gcom.batch.Processo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRoteiroDispositivoMovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		GerarRoteiroDispositivoMovelActionForm form = (GerarRoteiroDispositivoMovelActionForm) actionForm;
		HttpSession sessao = request.getSession(false);
		Set<Integer> idsQuadras = null;
		Set<Integer> numQuadras = null;

		if(form.getIdsRegistros() == null || form.getIdsRegistros().length < 1){
			throw new ActionServletException("atencao.nenhum_registro_selecionado");
		}

		if("ok".equals(request.getParameter("confirmado"))) {
			numQuadras = (Set<Integer>) sessao.getAttribute("numQuadras");
			sessao.removeAttribute("numQuadras");

			if(Util.isVazioOrNulo(numQuadras)) {
				throw new ActionServletException("atencao.nenhum_registro_selecionado");
			}
		} else {
			// Pesquisa as quadras dos imóveis selecionados
			idsQuadras = new HashSet<Integer>();
			numQuadras = new HashSet<Integer>();
			pesquisarQuadras(form.getIdsRegistros(), idsQuadras, numQuadras);

			String quadrasSemMapa = this.getFachada().verificarQuadrasSemMapaPorImoveis(idsQuadras);
			boolean existeQuadraSemMapa = quadrasSemMapa.isEmpty() ? false : true;

			if (existeQuadraSemMapa) {
				sessao.setAttribute("numQuadras", numQuadras);
				request.setAttribute("nomeBotao1", "Confirmar");
				request.setAttribute("voltarPara", "exibirGerarRoteiroDispositivoMovelAction.do?pesquisar=sim");
				return montarPaginaConfirmacao("atencao.nao_existe_mapa_setor_comercial", request, actionMapping, quadrasSemMapa);
			}
		}

		// Montar o Helper
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		ComandoAtualizacaoCadastralDMHelper helper = montarHelper(form, usuarioLogado, this.getFachada(), numQuadras);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("helper", helper);
    	parametros.put("colecaoImoveis", Arrays.asList(form.getIdsRegistros()));

    	this.getFachada().inserirProcessoIniciadoParametrosLivres(parametros,
				Processo.GERAR_ROTEIRO_DISPOSITIVO_MOVEL,
				this.getUsuarioLogado(request));

		this.montarPaginaSucesso(request,
				"Roteiro para Atualização Cadastral foi encaminhado para batch.",
				"Gerar Roteiro Dispositivo Móvel",
				"exibirGerarRoteiroDispositivoMovelAction.do?menu=sim");

		return retorno;
	}

	/**
	 * [SB 0001] - Gerar Comando de Atualização Cadastral
	 *
	 * @author Diogo Luiz
	 * @date 26/08/2014
	 *
	 */
	private ComandoAtualizacaoCadastralDMHelper montarHelper(GerarRoteiroDispositivoMovelActionForm form,
			Usuario usuario, Fachada fachada, Collection<Integer> numQuadras) {
		ComandoAtualizacaoCadastralDMHelper helper = new ComandoAtualizacaoCadastralDMHelper();

		Localidade localidade = new Localidade(Integer.parseInt(form.getIdLocalidade()));

		Empresa empresa = new Empresa();
		empresa.setId(Integer.parseInt(form.getIdEmpresa()));

		if(form.getCadastrador() == null || form.getCadastrador().equals("-1")){
			throw new ActionServletException("atencao.campo.informado", null, "Cadastrador");
		}
		Leiturista leiturista = new Leiturista();
		leiturista.setId(Integer.parseInt(form.getCadastrador()));

		Integer codigoSetorComercial = null;
		if(!form.getCodigoSetorComercial().equals("-1")){
			codigoSetorComercial = Integer.parseInt(form.getCodigoSetorComercial());
		}

		helper.setCodigoSetorComercial(codigoSetorComercial);
		helper.setEmpresa(empresa);
		helper.setLeiturista(leiturista);
		helper.setLocalidade(localidade);
		helper.setUsuarioLogado(usuario);
		helper.setQuadrasSelecionadas(numQuadras.toArray(new Integer[0]));

		return helper;
	}

	private void pesquisarQuadras(String[] idsRegistros, Set<Integer> idsQuadras, Set<Integer> numQuadras) {
		if (idsRegistros == null || idsRegistros.length == 0)
			return;

		for (String id: idsRegistros) {
			Quadra quadra = this.getFachada().pesquisarQuadraImovel(Integer.parseInt(id));
			idsQuadras.add(quadra.getId());
			numQuadras.add(quadra.getNumeroQuadra());
		}
	}
}
