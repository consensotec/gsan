package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.FiltroMensagemAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1297] - Relatório Atualização Cadastral - Imóveis Inconsistentes
 * 
 * @author Davi Menezes
 * @author Bruno Sá Barreto
 * 
 * @date  23/03/2012
 *
 */
public class GerarRelatorioImoveisInconsistentesMovimentoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		//Form
		FiltrarDadosCadastraisImoveisInconsistentesDMActionForm form = 
			(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		
		RelatorioImoveisInconsistentesMovimento relatorio = new RelatorioImoveisInconsistentesMovimento(usuarioLogado);
		
		String[] idsRegistro = null;
		if(form.getIdRegistro() != null){
			idsRegistro = form.getIdRegistro();
		}else{
			throw new ActionServletException("atencao.ids_movimento_nao_selecionado");
		}
		
		String nomeEmpresa = "";
		if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<?> colEmpresa = getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
			
			if(!Util.isVazioOrNulo(colEmpresa)){
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colEmpresa);
				nomeEmpresa = empresa.getDescricao();
			}
		}
		
		String descricaoLocalidade = "";
		if(form.getDescricaoLocalidade() != null && !form.getDescricaoLocalidade().equals("")){
			descricaoLocalidade = form.getDescricaoLocalidade();
		}
		
		String descricaoSetor = "";
		if(form.getDescricaoSetorComercial() != null && !form.getDescricaoSetorComercial().equals("")){
			descricaoSetor = form.getDescricaoSetorComercial();
		}
		
		String situacaoMovimento = "";
		if(form.getIndicadorSituacaoMovimento() != null && !form.getIndicadorSituacaoMovimento().equals("")){
			if(form.getIndicadorSituacaoMovimento().equals("1")){
				situacaoMovimento = "Pendente";
			}else if(form.getIndicadorSituacaoMovimento().equals("2")){
				situacaoMovimento = "Atualizado";
			}else{
				situacaoMovimento = "Todos";
			}
			
		}
		
		String tipoInconsistencia = "";
		if(form.getIdTipoInconsistencia() != null && !form.getIdTipoInconsistencia().equals("-1")){
			FiltroMensagemAtualizacaoCadastralDM filtroMensagem = new FiltroMensagemAtualizacaoCadastralDM();
			filtroMensagem.adicionarParametro(new ParametroSimples(
				FiltroMensagemAtualizacaoCadastralDM.ID, form.getIdTipoInconsistencia()));
			
			Collection<?> colMensagem = getFachada().pesquisar(filtroMensagem, MensagemAtualizacaoCadastralDM.class.getName());
			
			if(!Util.isVazioOrNulo(colMensagem)){
				MensagemAtualizacaoCadastralDM mensagem = (MensagemAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(colMensagem);
				tipoInconsistencia = mensagem.getMensagem();
			}
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if(tipoRelatorio == null){
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		//Adiciona parametros no relatório
		relatorio.addParametro("empresa", nomeEmpresa);
		relatorio.addParametro("descricaoLocalidade", descricaoLocalidade);
		relatorio.addParametro("descricaoSetor", descricaoSetor);
		relatorio.addParametro("quadras", form.getIdQuadraSelecionadosFormatado());
		relatorio.addParametro("situacaoMovimento", situacaoMovimento);
		relatorio.addParametro("tipoInconsistencia", tipoInconsistencia);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("idsRegistro", idsRegistro);
		
		relatorio.addParametro("idLocalidade", form.getIdLocalidade());
		relatorio.addParametro("codigoSetorComercial", form.getIdSetorComercial());
		relatorio.addParametro("numeroQuadras", form.getIdQuadraSelecionadosFormatado());
		relatorio.addParametro("idCadastrador", form.getIdCadastrador());
		relatorio.addParametro("indicadorSituacaoMovimento", form.getIndicadorSituacaoMovimento());
		relatorio.addParametro("idTipoInconsistencia", form.getIdTipoInconsistencia());
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
			httpServletResponse, actionMapping);
		
		form.setIdRegistro(null);
		
		return retorno;
	}
	
}
