/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoVisitaCampo;
import gcom.atendimentopublico.ordemservico.bean.ArquivoTxtOSVisitaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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

/**
 * 
 * 
 * 
 * @author Hugo Azevedo
 * @date 20/09/2011
 *  
 * 
 */
public class ValidarArquivoTextoOSVisitaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Sess�o
		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		//Form
		ConsultarArquivoTextoOSVisitaActionForm form = (ConsultarArquivoTextoOSVisitaActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Indicadores de confirma��o
		String confirmado = httpServletRequest.getParameter("confirmado");
		String finalizar = httpServletRequest.getParameter("tipoRelatorio");
		
		String idNovoLeiturista = "";
		
		
		String liberar = (String) httpServletRequest.getParameter("liberar");
		String opcao = "";
		String situacao = "";
		
		@SuppressWarnings("unchecked")
		Collection<ArquivoTxtOSVisitaHelper> colecaoArquivoTextoOSVisita = 
						(Collection<ArquivoTxtOSVisitaHelper>)sessao.getAttribute("colecaoArquivoTextoOSVisita");
		
		
		if(liberar == null)
			liberar = "";
	
		//Situa��o escolhida
		if (liberar.equals("0")) {
			opcao = "N�O LIBERAR";
		} else if (liberar.equals("1")) {
			opcao = "LIBERAR";
		} else if (liberar.equals("2")) {
			opcao = "EM CAMPO";
		} else if (liberar.equals("3") || (finalizar != null && finalizar.equals("FINALIZAR"))) {
			opcao = "FINALIZAR";
		} else if (liberar.equals("4")) {
			opcao = "INFORMAR LEITURISTA";
			idNovoLeiturista = httpServletRequest.getParameter("idNovoLeiturista");
		}
		
		
		if (form.getIdsRegistros() != null) {
			
			Vector<Integer> v = new Vector<Integer>();
			for (int i = 0; i < form.getIdsRegistros().length; i++) {
				v.add(new Integer(form.getIdsRegistros()[i]));

			}
			
			//Filtrar apenas os Arquivos escolhidos
			colecaoArquivoTextoOSVisita = this.filtrarOSVisitas(v,colecaoArquivoTextoOSVisita);
			
			
			
			//[FS0007] - Verificar situa��o do arquivo
			//********************************************************************
			
			//Caso algum dos  arquivos selecionados esteja na situa��o "Dispon�vel"
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.DISPONIVEL)){
				
				//N�o existe "Agente Comercial Informado" e o usu�rio solicitou "Liberar Arquivo"
				if(!this.validarAgenteComercial(colecaoArquivoTextoOSVisita) && opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.agente_comercial_informado_txt");
				}
			
				//Usu�rio solicitou "N�o Libera��o do Arquivo"
				else if(opcao.equals("N�O LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel");
				}
				
				//Usu�rio solicitou "Colocar Arquivo em Campo"
				else if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel_campo");
				}
				
			}
			
			//Caso algum dos  arquivos selecionados esteja na situa��o "Dispon�vel"
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.LIBERADO)){
				
				//Usu�rio solicitou "EM CAMPO"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_liberado_campo");
				}
				
				//Usu�rio solicitou "Liberar Arquivo"
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_liberado");
				}
			}
			
			
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.TRANSMITIDO)){
				if(opcao.equals("FINALIZAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
				else if(opcao.equals("N�O LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
				else if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
				else if(opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
			}
			
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.EM_CAMPO)){
				
				//Usu�rio solicitou "Colocar Arquivo em Campo"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo");
				}
				
				//Usu�rio solicitou "Liberar Arquivo"
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_liberado");
				}
				
				else if(opcao.equals("N�O LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_liberado");
				}
				else if(opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_agente_comercial");
				}
				
			}

			//******************************************************************
			
			
			if(opcao.equals("LIBERAR")){
				fachada.atualizarListaArquivoTextoOSVisita(
						colecaoArquivoTextoOSVisita,
						SituacaoTransmissaoLeitura.LIBERADO,
						null,
						new Date()
				);
				
				situacao = "LIBERADO";
			}
			else if(opcao.equals("N�O LIBERAR")){
				fachada.atualizarListaArquivoTextoOSVisita(
						colecaoArquivoTextoOSVisita,
						SituacaoTransmissaoLeitura.DISPONIVEL,
						null,
						new Date()
					);
				
				situacao = "DISPON�VEL";
			}
			else if(opcao.equals("EM CAMPO")){
					fachada.atualizarListaArquivoTextoOSVisita(
						colecaoArquivoTextoOSVisita,
						SituacaoTransmissaoLeitura.EM_CAMPO,
						null,
						new Date()
					);
					
					situacao = "EM CAMPO";
			}
			else if(opcao.equals("FINALIZAR")){
				if( (confirmado == null	|| !confirmado.trim().equalsIgnoreCase("ok")) && 
			 			verificarOSNaoEncerradas(colecaoArquivoTextoOSVisita, fachada)){
					
						httpServletRequest.setAttribute("caminhoActionConclusao",
								"/gsan/validarArquivoTextoOSVisitaAction.do");
						
						httpServletRequest.setAttribute("tipoRelatorio", "FINALIZAR");
						
						//Tela de confirma��o
						return montarPaginaConfirmacao(
								"atencao.atencao.os_nao_encerradas.confirmacao",
								httpServletRequest, actionMapping);
					
				}
				
				fachada.atualizarListaArquivoTextoOSVisita(
						colecaoArquivoTextoOSVisita,
						SituacaoTransmissaoLeitura.TRANSMITIDO,
						null,
						new Date()
					);
				
				situacao = "FINALIZADO";
			}
			
			else if(opcao.equals("INFORMAR LEITURISTA")){
				FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
				filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, new Integer(idNovoLeiturista)));
				
				@SuppressWarnings("unchecked")
				Collection<Leiturista> colecaoLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
				Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);
				
				
				fachada.atualizarListaArquivoTextoOSVisita(
						colecaoArquivoTextoOSVisita,
						SituacaoTransmissaoLeitura.DISPONIVEL,
						leiturista,
						new Date()
					);
				
				situacao = "DISPON�VEL";
				
			}
			
		}
		
		montarPaginaSucesso(httpServletRequest,
				"Arquivo Texto Alterado para " + situacao.toLowerCase() + " com sucesso.",
				"Realizar outra Manuten��o de Arquivo Texto",
				"exibirConsultarArquivoTextoOSVisitaAction.do?menu=sim");
		
		sessao.removeAttribute("colecaoArquivoTextoOSVisita");
		return retorno;

	}
	
	
	
	
	private boolean verificarOSNaoEncerradas(
			Collection<ArquivoTxtOSVisitaHelper> colecaoArquivoTextoOSVisita, Fachada fachada) {
		boolean retorno = true;
		@SuppressWarnings("rawtypes")
		Iterator it = colecaoArquivoTextoOSVisita.iterator();
		while(it.hasNext()){
			ArquivoTxtOSVisitaHelper at = (ArquivoTxtOSVisitaHelper)it.next();
			if(fachada.verificarArquivoTextoOSPendente(at.getIdArquivo())){
				retorno = false;
				break;
			}
		}
		return retorno;
	}




	private Collection<ArquivoTxtOSVisitaHelper> filtrarOSVisitas(
			Vector<Integer> v,
			Collection<ArquivoTxtOSVisitaHelper> colecaoArquivoTextoOSVisita) {

			Collection<ArquivoTxtOSVisitaHelper> retorno = new ArrayList<ArquivoTxtOSVisitaHelper>();

			Iterator<Integer> it = v.iterator();
			Iterator<ArquivoTxtOSVisitaHelper> it2 = null;
			
			while(it.hasNext()){
				Integer id = it.next();
				it2 = colecaoArquivoTextoOSVisita.iterator();
				while(it2.hasNext()){
					ArquivoTxtOSVisitaHelper at = it2.next();
					if(at.getIdArquivo().intValue() == id.intValue())
						retorno.add(at);
				}
			}
			
			return retorno;
	}


	//Verificar se h� agentes comerciais em todos os registros
	private boolean validarAgenteComercial(Collection<ArquivoTxtOSVisitaHelper> v){
		boolean retorno = true;

		Iterator<ArquivoTxtOSVisitaHelper> it = v.iterator();
		while(it.hasNext()){
			ArquivoTxtOSVisitaHelper at = it.next();
			if(at.getNomeFuncionario() == null && at.getNomeCliente() == null){
				retorno = false;
				break;
			}
		}
		
		return retorno;
	}
	
	
	private boolean validarSituacao(Collection<ArquivoTxtOSVisitaHelper> v,Integer situacao){
		boolean retorno = false;
		Iterator<ArquivoTxtOSVisitaHelper> it = v.iterator();
		while(it.hasNext()){
			ArquivoTxtOSVisitaHelper at = it.next();
			if(at.getIdSituacao().intValue() == situacao.intValue()){
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}

}
