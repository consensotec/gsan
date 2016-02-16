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
package gcom.gui.mobile.execucaoordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
 *
 * @author Jean Varela
 * @throws ErroRepositorioException 
 * @date   16/11/2015	
 */
public class ValidarArquivoTextoOSCobrancaSmartphoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Sess�o
		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		//Form
		ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;
		
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
		Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSVisita = 
						(Collection<ArquivoTxtOSCobrancaSmartphoneHelper>)sessao.getAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
		
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
			
			Vector<Integer> arquivosSelecionados = new Vector<Integer>();
			for (int i = 0; i < form.getIdsRegistros().length; i++) {
				arquivosSelecionados.add(new Integer(form.getIdsRegistros()[i]));
			}
			
			if(opcao.equals("LIBERAR")){
				List<ArquivoTxtOSCobrancaSmartphoneHelper> arquivos = new ArrayList<ArquivoTxtOSCobrancaSmartphoneHelper>();
				for(ArquivoTxtOSCobrancaSmartphoneHelper arquivo : colecaoArquivoTextoOSVisita){
					if (arquivosSelecionados.contains(arquivo.getIdArquivo())){
						arquivos.add(arquivo);
					}
				}
				fachada.pesquisarArquivoLiberadoEmCampoPorAgenteComercial(arquivos);
			}
			
			//Filtrar apenas os Arquivos escolhidos
			colecaoArquivoTextoOSVisita = this.filtrarOSVisitas(arquivosSelecionados,colecaoArquivoTextoOSVisita);
			
			//[FS0007] - Verificar situa��o do arquivo
			//********************************************************************
			
			//Caso algum dos  arquivos selecionados esteja na situa��o "Dispon�vel"
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.DISPONIVEL)){
				
				//N�o existe "Agente Comercial Informado"
				if(!this.validarAgenteComercial(colecaoArquivoTextoOSVisita) && !opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.agente_comercial_informado_txt");
				}
				//Usu�rio solicitou "N�o Libera��o do Arquivo"
				if(opcao.equals("N�O LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel");
				}
				//Usu�rio solicitou "Colocar Arquivo em Campo"
				else if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel_campo");
				}
			}
			
			//Caso algum dos  arquivos selecionados esteja na situa��o "Liberado"
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.LIBERADO)){
				
				//Usu�rio solicitou "EM CAMPO"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_liberado_campo");
				}
				//Usu�rio solicitou "Liberar Arquivo"
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_liberado");
				}
				else if(opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.arquivo_txt_liberado_agente_comercial");
				}
			}
			
			//Caso algum dos  arquivos selecionados esteja na situa��o "Finalizado"
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.FINALIZADO)){
				
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
					throw new ActionServletException("atencao.arquivo_txt_finalizado_agente_comercial");
				}
			}
			
			//Caso algum dos  arquivos selecionados esteja na situa��o "Em Campo"
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
			
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.FINALIZADO)){
				if(opcao.equals("FINALIZAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
			}

			//******************************************************************

			if(opcao.equals("LIBERAR")){
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,SituacaoTransmissaoLeitura.LIBERADO, null);
				situacao = "LIBERADO";
			}
			else if(opcao.equals("N�O LIBERAR")){
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,SituacaoTransmissaoLeitura.DISPONIVEL, null);
				situacao = "DISPON�VEL";
			}
			else if(opcao.equals("EM CAMPO")){
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,SituacaoTransmissaoLeitura.EM_CAMPO,null);
				situacao = "EM CAMPO";
			}
			else
			if(opcao.equals("FINALIZAR")){
				if((confirmado == null	|| !confirmado.trim().equalsIgnoreCase("ok")) && 
			 			verificarOSNaoEncerradas(colecaoArquivoTextoOSVisita, fachada)){
					
						httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/validarArquivoTextoOSVisitaAction.do");
						httpServletRequest.setAttribute("tipoRelatorio", "FINALIZAR");
						//Tela de confirma��o
						return montarPaginaConfirmacao("atencao.atencao.os_nao_encerradas.confirmacao",httpServletRequest, actionMapping);
				}
				
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,SituacaoTransmissaoLeitura.FINALIZADO,null);
				situacao = "FINALIZADO";
			}
			else if(opcao.equals("INFORMAR LEITURISTA")){
				FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
				filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, new Integer(idNovoLeiturista)));
				
				@SuppressWarnings("unchecked")
				Collection<Leiturista> colecaoLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
				Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);
				
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,SituacaoTransmissaoLeitura.DISPONIVEL,leiturista);
				situacao = "DISPON�VEL";
			}
		}
		
		montarPaginaSucesso(httpServletRequest,
							"Arquivo Texto Alterado para " + situacao.toLowerCase() + " com sucesso.",
							"Realizar outra Manuten��o de Arquivo Texto",
							"exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?menu=sim");
		
		sessao.removeAttribute("colecaoArquivoTextoOSVisita");
		return retorno;

	}
	
	private boolean verificarOSNaoEncerradas(
			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSVisita, Fachada fachada) {
		boolean retorno = true;
		@SuppressWarnings("rawtypes")
		Iterator it = colecaoArquivoTextoOSVisita.iterator();
		while(it.hasNext()){
			ArquivoTxtOSCobrancaSmartphoneHelper at = (ArquivoTxtOSCobrancaSmartphoneHelper)it.next();
			if(fachada.verificarArquivoTextoOSPendente(at.getIdArquivo())){
				retorno = false;
				break;
			}
		}
		return retorno;
	}

	private Collection<ArquivoTxtOSCobrancaSmartphoneHelper> filtrarOSVisitas(
			Vector<Integer> v, Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSVisita) {

		Collection<ArquivoTxtOSCobrancaSmartphoneHelper> retorno = new ArrayList<ArquivoTxtOSCobrancaSmartphoneHelper>();

		Iterator<Integer> it = v.iterator();
		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> it2 = null;
		
		while(it.hasNext()){
			Integer id = it.next();
			it2 = colecaoArquivoTextoOSVisita.iterator();
			while(it2.hasNext()){
				ArquivoTxtOSCobrancaSmartphoneHelper at = it2.next();
				if(at.getIdArquivo().intValue() == id.intValue())
					retorno.add(at);
			}
		}
		
		return retorno;
	}

	//Verificar se h� agentes comerciais em todos os registros
	private boolean validarAgenteComercial(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> v){
		boolean retorno = true;
		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> it = v.iterator();
		while(it.hasNext()){
			ArquivoTxtOSCobrancaSmartphoneHelper at = it.next();
			if(at.getNomeFuncionario() == null && at.getNomeCliente() == null){
//			if(at.getNomeFuncionario() == null){	
				retorno = false;
				break;
			}
		}
		return retorno;
	}
	
	private boolean validarSituacao(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> v,Integer situacao){
		boolean retorno = false;
		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> it = v.iterator();
		while(it.hasNext()){
			ArquivoTxtOSCobrancaSmartphoneHelper at = it.next();
			if(at.getIdSituacao().intValue() == situacao.intValue()){
				retorno = true;
				break;
			}
		}
		return retorno;
	}

}
