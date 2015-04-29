package gsan.gui.relatorio.cobranca;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.cobranca.RelatorioDocumentosAReceber;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * [UC990] Gerar Relat�rio de Documentos a Receber
 *
 * @author Hugo Amorim
 * @date 22/02/2010
 *
 */
public class GerarRelatorioDocumentosAReceberAction extends
		ExibidorProcessamentoTarefaRelatorio {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Fachada fachada = Fachada.getInstancia();
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();	
		
		RelatorioDocumentosAReceberForm form =
			(RelatorioDocumentosAReceberForm) actionForm;
		
		Integer anoMesInformado = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno());
		
		if(anoMesInformado.compareTo(sistemaParametro.getAnoMesFaturamento())>=0){
			throw new ActionServletException("atencao.mes_ano_resumo_invalido",Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
		}
	
		FiltroRelatorioDocumentosAReceberHelper 
			helper = new FiltroRelatorioDocumentosAReceberHelper(
					//mesAno
					form.getMesAno()!=null ? 
						Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno()).toString():null,
					//idCategoriaTipo
					form.getIdCategoriaTipo()!=null ? 
						form.getIdCategoriaTipo():null,
					//idsCategoria
					form.getIdsCategoria()!=null ? 
						form.getIdsCategoria():null,
					//idsPerfilImovel
					form.getIdsPerfilImovel()!=null ? 
							form.getIdsPerfilImovel():null,		
					//idsEsferaPoder
					form.getIdsEsferaPoder()!=null ? 
							form.getIdsEsferaPoder():null,	
					//idGerencia
					form.getIdGerencia()!=null ? 
						form.getIdGerencia():null,		
					//idUnidade
					form.getIdUnidade()!=null ? 
						form.getIdUnidade():null,			
					//idLocalidade
					form.getIdLocalidade()!=null ? 
						form.getIdLocalidade():null,
					//idOpcaoTotalizacao
					form.getIdOpcaoTotalizacao()!=null ? 
						form.getIdOpcaoTotalizacao():null,		
					//colecaoFaixas
					form.getColecaoFaixas()!=null ? 
						form.getColecaoFaixas():null,
					sistemaParametro.getNomeEstado()
				);
				
		RelatorioDocumentosAReceber relatorio
			= new RelatorioDocumentosAReceber(usuario);
		
		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorio.addParametro("tipoFormatoRelatorio", new Integer(tipoRelatorio));
		relatorio.addParametro("filtro", helper);
	
		
		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		
		
		return retorno;
	}
}
