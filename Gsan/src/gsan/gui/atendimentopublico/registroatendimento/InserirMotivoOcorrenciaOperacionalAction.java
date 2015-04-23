package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
	/**
	 * [UC1524] Inserir Motivo da Ocorrência Operacional
	 * 
	 * @author Jonathan Marcos
	 * @date 09/07/2013
	 * 
	 */
public class InserirMotivoOcorrenciaOperacionalAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirMotivoOcorrenciaOperacionalActionForm 
			inserirMotivoOcorrenciaOperacionalActionForm = (InserirMotivoOcorrenciaOperacionalActionForm) actionForm; 
		OcorrenciaOperacionalMotivo ocorrenciaOperacMotivo = new OcorrenciaOperacionalMotivo();
		OcorrenciaOperacionalTipo ocorrenciaOperacTipo = new OcorrenciaOperacionalTipo();
		
		//Montando o objeto ocorrenciaOperacMotivo
		ocorrenciaOperacMotivo.setDescricao(inserirMotivoOcorrenciaOperacionalActionForm.getDescricao().toUpperCase());
		ocorrenciaOperacMotivo.setDescricaoAbreviada(inserirMotivoOcorrenciaOperacionalActionForm.getDescricaoAbreviada().toUpperCase());
		ocorrenciaOperacMotivo.setIndicadorUso((short)1);
		ocorrenciaOperacTipo.setId(Integer.parseInt(httpServletRequest.getParameter("ocorrenciaOperacTipo")));
		ocorrenciaOperacMotivo.setOcorrenciaOperacionalTipo(ocorrenciaOperacTipo);
		ocorrenciaOperacMotivo.setUltimaAlteracao(new Date());
		
		try {
			//Inserindo o objeto ocorrenciaOperacMotivo no banco de dados
			Integer idMotivoOcorrencia = (Integer) fachada.inserir(ocorrenciaOperacMotivo);
			//Montando a tela de sucesso
			montarPaginaSucesso(httpServletRequest,
				"Motivo da Ocorrência Operacional inserido com sucesso",
				"Inserir outro Motivo Ocorrência Operacional",
				"exibirInserirMotivoOcorrenciaOperacionalAction.do",
				"exibirAtualizarMotivoOcorrenciaOperacionalAction.do?idMotivoOcorrencia="+idMotivoOcorrencia+"",
				"Atualizar Motivo da Ocorrência Operacional Inserido");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
}
