package gcom.gui.portal.caema;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarTramitePortalCaema extends GcomAction{

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
			
		ActionForward retorno = actionMapping.findForward("exibirConsultarTramitePortalCaema");				
		
		ConsultarTramiteHelper consultarTramiteHelper = new ConsultarTramiteHelper(); 
		
		
		String idRegistroAtendimento = (String)httpServletRequest.getParameter("numeroRA");

		httpServletRequest.setAttribute("voltarServicos", true);		
		
		if(idRegistroAtendimento != null){
			
			
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();			
			filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("unidadeAtual");
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, idRegistroAtendimento));
			
			Collection <RegistroAtendimento> colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
			RegistroAtendimento registroAtendimento = (RegistroAtendimento)Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
			
			//Situação da RA
			
			Short idSituacao = registroAtendimento.getCodigoSituacao();
			String descricaoSituacao ="";
			if(idSituacao.equals(RegistroAtendimento.PENDENTE)){
				descricaoSituacao = "PENDENTE";
			} else if (idSituacao.equals(RegistroAtendimento.ENCERRADO)){
				descricaoSituacao = "ENCERRADO";
			}else{
				descricaoSituacao = "BOQUEADO";
			}
			
			consultarTramiteHelper.setRaSituacao(descricaoSituacao);
			
			
			if(registroAtendimento.getId() != null){
				
				//Protocolo
				FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
				filtroRegistroAtendimentoSolicitante.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID,
																													registroAtendimento.getId()));
				
				Collection <RegistroAtendimentoSolicitante> colecaoRegistroAtendimentoSolicitante = fachada.pesquisar(filtroRegistroAtendimentoSolicitante,RegistroAtendimentoSolicitante.class.getName());
				RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante)Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoSolicitante);
				
				if(registroAtendimentoSolicitante.getNumeroProtocoloAtendimento() != null){
					//ID
					consultarTramiteHelper.setProtocolo(registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
				}

				
				
				// Tipo de Solicitação e Especificação
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();	
				filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
																registroAtendimento.getSolicitacaoTipoEspecificacao().getId()));				
				Collection <SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao,
																									SolicitacaoTipoEspecificacao.class.getName());
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao)Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
				
				if(solicitacaoTipoEspecificacao != null){
				
					if(solicitacaoTipoEspecificacao.getId() != null){
						//ID tipo Especificação
						consultarTramiteHelper.setIdEspecificacao(solicitacaoTipoEspecificacao.getId().toString());
					}
					if(solicitacaoTipoEspecificacao.getDescricao() != null){
						//Descricao tipo Especificação
						consultarTramiteHelper.setDescricaoEspecificacao(solicitacaoTipoEspecificacao.getDescricao());
					}
					
					
					if(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId() != null){
						//ID Solicitação Tipo
						consultarTramiteHelper.setIdTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId().toString());						
					}
					
					if(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao() != null){
						//Descrição Solicitação Tipo
						consultarTramiteHelper.setDescricaoTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());
						
					}
					
				}
				//Unidade de Atendimento
				FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();
				filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
				filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoUnidade.ATENDIMENTO_RELACAO_TIPO, RegistroAtendimentoUnidade.DIRETORIA_DA_PRESIDENCIA));
				filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID, idRegistroAtendimento));
				
				Collection <RegistroAtendimentoUnidade> colecaoRegistroAtendimentoUnidade = fachada.pesquisar(filtroRegistroAtendimentoUnidade, RegistroAtendimentoUnidade.class.getName());
				RegistroAtendimentoUnidade registroAtendimentoUnidade = (RegistroAtendimentoUnidade)Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoUnidade);
				
				if(registroAtendimentoUnidade.getUnidadeOrganizacional() != null)
				{
					if(registroAtendimentoUnidade.getUnidadeOrganizacional().getId() != null){
						//ID 
						consultarTramiteHelper.setIdUnidadeAtendimento(registroAtendimentoUnidade.getUnidadeOrganizacional().getId().toString());				
					}
				}
					if(registroAtendimentoUnidade.getUnidadeOrganizacional().getDescricao() != null){
						//Descrição 
						consultarTramiteHelper.setDescricaoUnidadeAtendimento(registroAtendimentoUnidade.getUnidadeOrganizacional().getDescricao());
					}
					
					
					//Unidade Atual
					if(registroAtendimento.getUnidadeAtual() != null && registroAtendimento.getUnidadeAtual().getId() != null){
						//ID
						consultarTramiteHelper.setIdUnidadeAtual(registroAtendimento.getUnidadeAtual().getId().toString());
					}
					
					if(registroAtendimento.getUnidadeAtual() != null && registroAtendimento.getUnidadeAtual().getDescricao() != null){
						//Descricao
						consultarTramiteHelper.setDescricaoUnidadeAtual(registroAtendimento.getUnidadeAtual().getDescricao());
					}
					
					//Data Atendimento
					if(registroAtendimento.getRegistroAtendimento() != null){					
						String dataAtendimento = Util.formatarData(registroAtendimento.getRegistroAtendimento());
						consultarTramiteHelper.setDataAtendimento(dataAtendimento);
					}
					
					//Data Encerramento
					if(registroAtendimento.getDataEncerramento() != null){					
						String dataEncerramento = Util.formatarData(registroAtendimento.getDataEncerramento());
						consultarTramiteHelper.setDataEncerramento(dataEncerramento);
					}
				
			}
			
			
			sessao.setAttribute("consultarTramiteHelper", consultarTramiteHelper);
						
			
			sessao.removeAttribute("colecaoDadosTramite");
			
			Collection<ConsultarTramiteHelper> colecaoDadosTramite = fachada.pesquisarDadosUnidadeNegocio(idRegistroAtendimento);
			
			if(colecaoDadosTramite != null && !colecaoDadosTramite.isEmpty()){
				
				sessao.setAttribute("colecaoDadosTramite", colecaoDadosTramite);
			}
			
			
		}
		
		
			
		return retorno;
	}
	
}
