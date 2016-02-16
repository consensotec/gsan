package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaGrupo;
import gcom.gui.GcomAction;
import gcom.mobile.execucaoordemservico.ParametrosArquivoTextoOSCobranca;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
 * 
 * @author André Miranda
 * @date 13/11/2015
 */
public class GerarArquivoTextoOrdensServicoSmartphoneAction extends GcomAction {
	private GerarArquivoTextoOrdensServicoSmartphoneActionForm form = null;
	private HttpSession sessao = null;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		form = (GerarArquivoTextoOrdensServicoSmartphoneActionForm) actionForm;
		sessao = request.getSession(false);

		ParametrosArquivoTextoOSCobranca parametros = montarParametros();

		// Recuperando a lista das OS selecionadas
		Collection<Integer[]> colecaoOS = montarListaOS(form.getIdsOS());

		getFachada().inserirParametrosArquivoTextoOSCobranca(parametros,
				form.getIdAgenteComercial(), form.getIdsRota(), colecaoOS);

		montarPaginaSucesso(request, "Arquivo Texto Criado com Sucesso.",
				"Gerar Outro Arquivo Texto",
				"exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?menu=sim");

		return retorno;
	}

	/**
	 * Método para selecionar as os a partir da opção do usuário
	 * 
	 * @param ids - Registros selecionados na tela principal
	 * @return Coleção das OSs do filtro selecionadas
	 */
	private Collection<Integer[]> montarListaOS(String[] ids) {
		if (ids == null)
			return null;

		Collection<Integer[]> retorno = new ArrayList<Integer[]>();
		Map<String, List<Integer>> mapaOS = (Map<String, List<Integer>>) sessao.getAttribute("mapaOS");

		for (String key : ids) {
			String[] campos = key.split("-");
			Integer idLocalidade = Integer.valueOf(campos[0]);
			Integer cdSetor = Integer.valueOf(campos[1]);
			Integer cdRota = Integer.valueOf(campos[2]);
			Integer nnQuadra = Integer.valueOf(campos[3]);

			List<Integer> idsOS = mapaOS.get(key);
			for (Integer idOS : idsOS) {
				retorno.add(new Integer[] { idOS, idLocalidade, cdSetor, cdRota, nnQuadra });
			}
		}

		return retorno;
	}

	private ParametrosArquivoTextoOSCobranca montarParametros() {
		ParametrosArquivoTextoOSCobranca parametros = new ParametrosArquivoTextoOSCobranca();

		// Dados do filtro geral
		Empresa empresa = new Empresa();
		empresa.setId(Integer.valueOf(form.getIdEmpresa()));
		parametros.setEmpresa(empresa);

		parametros.setCodigoTipoOS(OrdemServico.ORDEM_SERVICO_COBRANCA);

		ServicoTipo servicoTipo = new ServicoTipo();
		servicoTipo.setId(form.getIdServicoTipo());
		parametros.setServicoTipo(servicoTipo);

		// Dados do filtro por grupo de cobrança
		if(Util.verificarNaoVazio(form.getMesAnoCronograma())) {
			Integer mesAno = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoCronograma());
			parametros.setAnoMesReferenciaCronograma(mesAno);
		}

		if(Util.verificarIdNaoVazio(form.getIdGrupoCobranca())) {
			CobrancaGrupo grupo = new CobrancaGrupo();
			grupo.setId(form.getIdGrupoCobranca());
			parametros.setCobrancaGrupo(grupo);
		}

		// Dados do filtro por cobrança eventual
		if(Util.verificarIdNaoVazio(form.getIdComando())) {
			CobrancaAcaoAtividadeComando comando = new CobrancaAcaoAtividadeComando();
			comando .setId(form.getIdComando());
			parametros.setCobrancaAcaoAtividadeComando(comando);
		}

		return parametros;
	}
}
