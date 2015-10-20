package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1377] Acompanhar Registro Atendimento Loja Virtual
 * 
 * @author Maxwel Moreira
 * @date 17/07/2013
 * 
 */

public class ExibirAcompanhamentoRAPortalCaern extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		ActionForward retorno = actionMapping.findForward("exibirAcompanhamentoRAPortalCaern");		
		
		Integer matricula = (Integer) sessao.getAttribute("matricula");
		httpServletRequest.setAttribute("voltarServicos", true);		
		
		Collection<RegistroAtendimento> colecaoRegistroAtendimento = fachada.consultarRegistroAtendimentoImovel(matricula,null);
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.ACOMPANHAR_REGISTRO_ATENDIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		
		List<ConsultarImovelRegistroAtendimentoHelper> colecaoConsultarImovelRegistroAtendimentoHelper  = new ArrayList<ConsultarImovelRegistroAtendimentoHelper>();
		
		if(!Util.isVazioOrNulo(colecaoRegistroAtendimento)){
			
			Iterator<RegistroAtendimento> iteratorColecaoRegistroAtendimento = colecaoRegistroAtendimento.iterator();
			
			while (iteratorColecaoRegistroAtendimento.hasNext()) {
				RegistroAtendimento registroAtendimento = iteratorColecaoRegistroAtendimento.next();
				ConsultarImovelRegistroAtendimentoHelper consultarImovelRegistroAtendimentoHelper = new ConsultarImovelRegistroAtendimentoHelper();

				//id registro atendimento
				if(registroAtendimento != null  && registroAtendimento.getId() != null ){
					consultarImovelRegistroAtendimentoHelper.setIdRegistroAtendimento(registroAtendimento.getId().toString());
				}
				
				//tipo de solicitação
				if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null 
						&& registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo() != null){
					
					consultarImovelRegistroAtendimentoHelper.setTipoSolicitacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getDescricao());
				}
				
				//especificação
				if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null){
					consultarImovelRegistroAtendimentoHelper.setEspecificacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
				}
				
				//data de atendimento
				if(registroAtendimento != null && registroAtendimento.getRegistroAtendimento() != null ){
					consultarImovelRegistroAtendimentoHelper.setDataAtendimento(Util.formatarData(registroAtendimento.getRegistroAtendimento()));
				}
				
				//situacao
				if(registroAtendimento != null && registroAtendimento.getId() != null){
					ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRAHelper = 
						fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
					consultarImovelRegistroAtendimentoHelper.setSituacao(obterDescricaoSituacaoRAHelper.getDescricaoSituacao());
				}
				
				//PROTOCOLO
				if(registroAtendimento != null  && registroAtendimento.getId() != null ){
					
					FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = 
					new FiltroRegistroAtendimentoSolicitante();
					
					filtroRegistroAtendimentoSolicitante.adicionarParametro(
					new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, 
					registroAtendimento.getId()));
					
					filtroRegistroAtendimentoSolicitante.adicionarParametro(
					new ParametroSimples(FiltroRegistroAtendimentoSolicitante.INDICADOR_SOLICITANTE_PRINCIPAL, 
					ConstantesSistema.SIM));
					
					Collection<?> colecaoRegistroAtendimentoSolicitante = fachada.pesquisar(filtroRegistroAtendimentoSolicitante,
					RegistroAtendimentoSolicitante.class.getName());
					
					if (colecaoRegistroAtendimentoSolicitante != null &&
						!colecaoRegistroAtendimentoSolicitante.isEmpty()){
						
						RegistroAtendimentoSolicitante solicitante = (RegistroAtendimentoSolicitante)
						Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoSolicitante);
						
						if (solicitante.getNumeroProtocoloAtendimento() != null){
							
							consultarImovelRegistroAtendimentoHelper.setNumeroProtocolo(
							solicitante.getNumeroProtocoloAtendimento());
						}
					}	
				}
				
				//Date Encerramento
				if(registroAtendimento != null && registroAtendimento.getDataEncerramento() != null ){
					
					consultarImovelRegistroAtendimentoHelper.setDataEncerramento(
							Util.formatarData(registroAtendimento.getDataEncerramento()));
				}
				
				//Motivo do encerramento
				if(registroAtendimento != null && registroAtendimento.getAtendimentoMotivoEncerramento() != null ){
					
					consultarImovelRegistroAtendimentoHelper.setMotivoEncerramento(
						registroAtendimento.getAtendimentoMotivoEncerramento().getDescricao());
				}
				
				
				colecaoConsultarImovelRegistroAtendimentoHelper.add(consultarImovelRegistroAtendimentoHelper);
			}
			Collections.sort((List<ConsultarImovelRegistroAtendimentoHelper>) 
					colecaoConsultarImovelRegistroAtendimentoHelper, new ComparatorImovelRAHelper());
			sessao.setAttribute("colecaoRAAcompanhadas", colecaoConsultarImovelRegistroAtendimentoHelper);			
		}else{
			httpServletRequest.removeAttribute("voltarServicos");
			retorno = actionMapping.findForward("imovelSemRACaern");
			httpServletRequest.setAttribute("imovelSemRA", true);
		}
		return retorno;
	}
	
	class ComparatorImovelRAHelper implements Comparator<ConsultarImovelRegistroAtendimentoHelper>{
		public int compare(ConsultarImovelRegistroAtendimentoHelper a, ConsultarImovelRegistroAtendimentoHelper b) {
			
			int retorno = 0;
			Integer anoMesDiaReferencia1 = a.getAnoMesDia();
			Integer anoMesDiaReferencia2 = b.getAnoMesDia();

			if(anoMesDiaReferencia1.compareTo(anoMesDiaReferencia2) == 1){
				retorno = -1;
			}else if(anoMesDiaReferencia1.compareTo(anoMesDiaReferencia2) == -1){
				retorno = 1;
			}			
			return retorno;
		}
	}

}
