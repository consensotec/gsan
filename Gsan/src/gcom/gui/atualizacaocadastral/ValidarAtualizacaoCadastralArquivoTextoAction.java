package gcom.gui.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atualizacaocadastral.SituacaoTransmissaoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.bean.AtualizacaoCadastralArquivoTextoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo M�vel Atualiza��o Cadastral
 * 
 * @author Davi Menezes
 * @date 27/11/2012
 *
 */
public class ValidarAtualizacaoCadastralArquivoTextoAction extends GcomAction {
	
	/**
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Sess�o
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		//Form
		ConsultarRoteiroDispositivoMovelActionForm form = (ConsultarRoteiroDispositivoMovelActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		String idNovoLeiturista = "";
		
		String liberar = (String) httpServletRequest.getParameter("liberar");
		String opcao = "";
		String situacao = "";
		
		@SuppressWarnings("unchecked")
		Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoArquivoTextoRoteiroDispositivoMovel = 
			(Collection<AtualizacaoCadastralArquivoTextoHelper>) sessao.getAttribute("colecaoArquivoTextoRoteiroDispositivoMovel");
		
		if(liberar == null){
			liberar = "";
		}
		
		//Situa��o Escolhida
		if(liberar.equals("0")){
			opcao = "NAO LIBERAR";
		}else if(liberar.equals("1")){
			opcao = "LIBERAR";
		}else if(liberar.equals("2")){
			opcao = "EM CAMPO";
		}else if(liberar.equals("3")){
			opcao = "FINALIZAR";
		}else if(liberar.equals("4")){
			opcao = "INFORMAR LEITURISTA";
			idNovoLeiturista = httpServletRequest.getParameter("idNovoLeiturista");
		}
		
		if(form.getIdsRegistros() != null && form.getIdsRegistros().length > 0){
			Vector<Integer> v = new Vector<Integer>();
			for(int i = 0 ; i < form.getIdsRegistros().length; i++){
				v.add(new Integer(form.getIdsRegistros()[i]));
			}
			
			//Filtrar Apenas os arquivos escolhidos
			colecaoArquivoTextoRoteiroDispositivoMovel = this.filtrarArquivosSelecionados(
					v, colecaoArquivoTextoRoteiroDispositivoMovel);
			
			//[FE0005] - Verificar situa��o do arquivo
			//********************************************************************
			
			//Caso algum dos arquivos selecionados esteja na situa��o "Dispon�vel"
			if(this.validarSituacao(colecaoArquivoTextoRoteiroDispositivoMovel, SituacaoTransmissaoAtualizacaoCadastralDM.DISPONIVEL)){
				
				//N�o existe Leiturista Informado
				if(!this.validarLeiturista(colecaoArquivoTextoRoteiroDispositivoMovel)){
					throw new ActionServletException("atencao.leiturista_informado_txt");
				}
				
				//Usu�rio solicitou "N�o libera��o do Arquivo"
				if(opcao.equals("NAO LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel");
				}
				
				//Usu�rio solicitou "Colocar Arquivo em Campo"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel_campo");
				}
			}
			
			//Caso algum dos arquivos selecionados esteja na situa��o "Liberado"
			if(this.validarSituacao(colecaoArquivoTextoRoteiroDispositivoMovel, SituacaoTransmissaoAtualizacaoCadastralDM.LIBERADO)){
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_liberado_campo");
				}

				if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_liberado");
				}

				if(opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.arquivo_txt_liberado");
				}
			}
			
			//Caso algum dos arquivos selecionados esteja na situa��o de "Finalizado"
			if(this.validarSituacao(colecaoArquivoTextoRoteiroDispositivoMovel, SituacaoTransmissaoAtualizacaoCadastralDM.FINALIZADO)){
				
				if(opcao.equals("FINALIZAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}else if(opcao.equals("NAO LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}else if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}else if(opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
			}
			
			//Caso algum dos arquivos selecionados esteja na situa��o "Em campo"
			if(this.validarSituacao(colecaoArquivoTextoRoteiroDispositivoMovel, SituacaoTransmissaoAtualizacaoCadastralDM.EM_CAMPO)){
				
				//Usu�rio solicitou "Colocar Arquivo em Campo"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo");
				}
				
				//Usu�rio solicitou "Liberar Arquivo"
				if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_liberado");
				}
				
				//Usu�rio solicitou "N�o Liberar"
				if(opcao.equals("NAO LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_nao_liberado");
				}
				
				//Usu�rio solicitou "Informar Leiturista"
				if(opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_leiturista");
				}
			}
			
			//******************************************************************
			
			if(opcao.equals("LIBERAR")){
				fachada.atualizarListaAtualizacaoCadastralArquivoTexto(
						colecaoArquivoTextoRoteiroDispositivoMovel, 
						SituacaoTransmissaoAtualizacaoCadastralDM.LIBERADO, 
						null, 
						new Date()
				);
				
				situacao = "LIBERADO";
			
			}else if(opcao.equals("NAO LIBERAR")){
				fachada.atualizarListaAtualizacaoCadastralArquivoTexto(
						colecaoArquivoTextoRoteiroDispositivoMovel, 
						SituacaoTransmissaoAtualizacaoCadastralDM.DISPONIVEL, 
						null, 
						new Date()
				);
				
				situacao = "DISPON�VEL";
				
			}else if(opcao.equals("EM CAMPO")){
				fachada.atualizarListaAtualizacaoCadastralArquivoTexto(
						colecaoArquivoTextoRoteiroDispositivoMovel, 
						SituacaoTransmissaoAtualizacaoCadastralDM.EM_CAMPO, 
						null, 
						new Date()
				);
				
				situacao = "EM CAMPO";
				
			}else if(opcao.equals("FINALIZAR")){
				
				fachada.atualizarListaAtualizacaoCadastralArquivoTexto(
						colecaoArquivoTextoRoteiroDispositivoMovel, 
						SituacaoTransmissaoAtualizacaoCadastralDM.FINALIZADO, 
						null, 
						new Date()
				);
				
				situacao = "FINALIZADO";
				
			}else if(opcao.equals("INFORMAR LEITURISTA")){
				FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
				filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, idNovoLeiturista));
				
				Collection<?> colLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
				Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colLeiturista);
				
				fachada.atualizarListaAtualizacaoCadastralArquivoTexto(
						colecaoArquivoTextoRoteiroDispositivoMovel, 
						SituacaoTransmissaoAtualizacaoCadastralDM.DISPONIVEL, 
						leiturista, 
						new Date()
				);
				
				situacao = "DISPON�VEL";
			
			}
			
		}else{
			throw new ActionServletException("atencao.nenhum_registro_selecionado");
		}
		
		montarPaginaSucesso(httpServletRequest,
				"Atualiza��o Cadastral Arquivo Texto Alterado para " + situacao.toLowerCase() + " com sucesso.",
				"Consultar Roteiro Dispositivo M�vel",
				"exibirConsultarRoteiroDispositivoMovelAction.do?menu=sim");
		
		//Remover Cole��es da sess�o
		sessao.removeAttribute("colecaoArquivoTextoRoteiroDispositivoMovel");
		sessao.removeAttribute("colecaoLeiturista");
		
		return retorno;
	}
	
	/**
	 * Filtrar Apenas os arquivos selecionados
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 */
	private Collection<AtualizacaoCadastralArquivoTextoHelper> filtrarArquivosSelecionados(
			Vector<Integer> v, Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoHelper) {
		
		Collection<AtualizacaoCadastralArquivoTextoHelper> retorno = new ArrayList<AtualizacaoCadastralArquivoTextoHelper>();
		
		Iterator<Integer> it = v.iterator();
		Iterator<AtualizacaoCadastralArquivoTextoHelper> it2 = null;
		
		while(it.hasNext()){
			Integer id = it.next();
			it2 = colecaoHelper.iterator();
			while(it2.hasNext()){
				AtualizacaoCadastralArquivoTextoHelper ac = it2.next();
				if(Integer.parseInt(ac.getId()) == id){
					retorno.add(ac);
				}
			}
		}
		
		return retorno;
	}
	
	/**
	 * Validar Situa��o do Arquivo
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 */
	private boolean validarSituacao(Collection<AtualizacaoCadastralArquivoTextoHelper> colecao, Integer situacao){
		boolean retorno = false;
		
		Iterator<AtualizacaoCadastralArquivoTextoHelper> it = colecao.iterator();
		while(it.hasNext()){
			AtualizacaoCadastralArquivoTextoHelper ac = it.next();
			if(ac.getIdSituacaoArquivo().equals(situacao)){
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Validar Leiturista
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 */
	private boolean validarLeiturista(Collection<AtualizacaoCadastralArquivoTextoHelper> colecao){
		boolean retorno = true;
		
		Iterator<AtualizacaoCadastralArquivoTextoHelper> it = colecao.iterator();
		while(it.hasNext()){
			AtualizacaoCadastralArquivoTextoHelper ac = it.next();
			if(ac.getNomeUsuario() == null || ac.getNomeUsuario().equals("")){
				retorno = false;
				break;
			}
		}
		
		return retorno;
	}
			
}
