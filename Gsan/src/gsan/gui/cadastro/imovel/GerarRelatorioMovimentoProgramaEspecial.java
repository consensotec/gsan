package gsan.gui.cadastro.imovel;

import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.cadastro.imovel.RelatorioMovimentoProgramaEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * 
 * [UC1367] Registrar Movimento do Programa Especial
 * [SB0005] Gerar Relatório do Movimento do Programa Especial
 * 
 * @author Hugo Azevedo
 * @param form 
 * @date 21/08/2012
 * 
 */	
public class GerarRelatorioMovimentoProgramaEspecial extends
		ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		
        RegistrarMovimentoProgramaEspecialActionForm form = 
				 (RegistrarMovimentoProgramaEspecialActionForm)sessao.getAttribute("formMovimentoProgramaEspecial");
        
        
		Integer idMovimento = (Integer) sessao.getAttribute("idMovimento");
        
        String acao = form.getAcao();
        String cancelarItensFatura = form.getCancelarItensFatura();
        String retirarContasProgEspecial = form.getRetirarContasProgEspecial();
        String sitEspecialCobranca = form.getRetirarSituacaoEspCobranca();
		
        
		RelatorioMovimentoProgramaEspecial relatorioMovimentoProgramaEspecial = new RelatorioMovimentoProgramaEspecial(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		
		
		relatorioMovimentoProgramaEspecial.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		relatorioMovimentoProgramaEspecial.addParametro("acao",acao);
		relatorioMovimentoProgramaEspecial.addParametro("cancelarItensFatura",cancelarItensFatura);
		relatorioMovimentoProgramaEspecial.addParametro("retirarContasProgEspecial",retirarContasProgEspecial);
		relatorioMovimentoProgramaEspecial.addParametro("sitEspecialCobranca",sitEspecialCobranca);
		relatorioMovimentoProgramaEspecial.addParametro("idMovimento",idMovimento);
		
		
		
		retorno = processarExibicaoRelatorio(relatorioMovimentoProgramaEspecial,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);


		return retorno;
	
	}
	
}
