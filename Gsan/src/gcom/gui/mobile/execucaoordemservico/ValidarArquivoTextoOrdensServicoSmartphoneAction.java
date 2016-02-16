/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
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
import gcom.util.ErroRepositorioException;
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
 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
 *
 * @author Jean Varela
 * @throws ErroRepositorioException 
 * @date   16/11/2015	
 */
public class ValidarArquivoTextoOrdensServicoSmartphoneAction extends GcomAction {
	

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
								 HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		//Form
		ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		String confirmado = httpServletRequest.getParameter("confirmado");
		String finalizar = httpServletRequest.getParameter("tipoRelatorio");
		String idNovoLeiturista = "";
		
		String liberar = (String) httpServletRequest.getParameter("liberar");
		String opcao = "";
		String situacao = "";
		
		@SuppressWarnings("unchecked")
		Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSVisita = (Collection<ArquivoTxtOSCobrancaSmartphoneHelper>) sessao
				.getAttribute("colecaoArquivoTextoOSCobrancaSmartphone");

		if(liberar == null)
			liberar = "";
	
		//Situação escolhida
		if (liberar.equals("0")) {
			opcao = "NÃO LIBERAR";
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
			
			
			
			//[FS0007] - Verificar situação do arquivo
			//********************************************************************
			
			//Caso algum dos  arquivos selecionados esteja na situação "Disponível"
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.DISPONIVEL)){
				
				//Não existe "Agente Comercial Informado"
				if(!this.validarAgenteComercial(colecaoArquivoTextoOSVisita) && !opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.agente_comercial_informado_txt");
				}
				
				//Usuário solicitou "Não Liberação do Arquivo"
				if(opcao.equals("NÃO LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel");
				}
				
				//Usuário solicitou "Colocar Arquivo em Campo"
				else if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel_campo");
				}
				
			}
			
			//Caso algum dos  arquivos selecionados esteja na situação "Disponível"
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.LIBERADO)){
				
				//Usuário solicitou "EM CAMPO"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_liberado_campo");
				}
				
				//Usuário solicitou "Liberar Arquivo"
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_liberado");
				}
			
			}
			
			
			if(this.validarSituacao(colecaoArquivoTextoOSVisita, SituacaoTransmissaoLeitura.TRANSMITIDO)){
				
				if(opcao.equals("NÃO LIBERAR")){
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
				
				//Usuário solicitou "Colocar Arquivo em Campo"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo");
				}
				
				//Usuário solicitou "Liberar Arquivo"
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_liberado");
				}
				
				else if(opcao.equals("NÃO LIBERAR")){
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
			Leiturista idAgenteComercial = null;
			
			if (form.getIdAgenteComercial() != null && form.getIdAgenteComercial() != -1){
			   idAgenteComercial = new Leiturista(form.getIdAgenteComercial());
			}
			
			if(opcao.equals("LIBERAR")){
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,
																	   SituacaoTransmissaoLeitura.LIBERADO, idAgenteComercial);				
				situacao = "LIBERADO";
			}
			else if(opcao.equals("NÃO LIBERAR")){
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,
																	   SituacaoTransmissaoLeitura.DISPONIVEL, null);

				situacao = "DISPONÍVEL";
			}
			else if(opcao.equals("EM CAMPO")){
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,
																	   SituacaoTransmissaoLeitura.EM_CAMPO, null);
					
					situacao = "EM CAMPO";
			}
			else if(opcao.equals("FINALIZAR")){
				if ((confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) &&
					 verificarOSNaoEncerradas(colecaoArquivoTextoOSVisita, fachada)) {

					httpServletRequest.setAttribute("caminhoActionConclusao",
													"/gsan/validarArquivoTextoOSVisitaAction.do");

					httpServletRequest.setAttribute("tipoRelatorio", "FINALIZAR");

					return montarPaginaConfirmacao("atencao.atencao.os_nao_encerradas.confirmacao", httpServletRequest,
																									actionMapping);
				}
				
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,
																	   SituacaoTransmissaoLeitura.FINALIZADO, null);
				situacao = "FINALIZADO";
			}	
			else if(opcao.equals("INFORMAR LEITURISTA")){
				FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
				filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, new Integer(idNovoLeiturista)));

				@SuppressWarnings("unchecked")
				Collection<Leiturista> colecaoLeiturista = fachada.pesquisar(filtroLeiturista,Leiturista.class.getName());
				Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);

				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSVisita,
						SituacaoTransmissaoLeitura.DISPONIVEL, leiturista);

				situacao = "DISPONÍVEL";
				
			}
		}
		
		montarPaginaSucesso(httpServletRequest, "Arquivo Texto Alterado para " + situacao.toLowerCase()
											  + " com sucesso.", "Realizar outra Manutenção de Arquivo Texto",
											    "exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?menu=sim");
		
		sessao.removeAttribute("colecaoArquivoTextoOSVisita");
		return retorno;

	}
	
	
	
	
	private boolean verificarOSNaoEncerradas(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSVisita, 
											 Fachada fachada) {
		boolean retorno = true;
		
		@SuppressWarnings("rawtypes")
		Iterator iterator = colecaoArquivoTextoOSVisita.iterator();
		while (iterator.hasNext()) {
			ArquivoTxtOSCobrancaSmartphoneHelper at = (ArquivoTxtOSCobrancaSmartphoneHelper) iterator.next();
			if (fachada.verificarArquivoTextoOSPendente(at.getIdArquivo())) {
				retorno = false;
				break;
			}
		}
		return retorno;
	}




	private Collection<ArquivoTxtOSCobrancaSmartphoneHelper> filtrarOSVisitas(List<Integer> idsArquivo,
			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSVisita) {

		Collection<ArquivoTxtOSCobrancaSmartphoneHelper> retorno = new ArrayList<ArquivoTxtOSCobrancaSmartphoneHelper>();

		Iterator<Integer> iteratorId = idsArquivo.iterator();
		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> interatorArquivos = null;

		while (iteratorId.hasNext()) {
			
			Integer id = iteratorId.next();
			interatorArquivos = colecaoArquivoTextoOSVisita.iterator();
			
			while (interatorArquivos.hasNext()) {
				
				ArquivoTxtOSCobrancaSmartphoneHelper arquivo = interatorArquivos.next();
				
				if (arquivo.getIdArquivo().intValue() == id.intValue()){
					retorno.add(arquivo);
				}
			}
		}

		return retorno;
	}


	//Verificar se há agentes comerciais em todos os registros
	private boolean validarAgenteComercial(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> arquivos){
		boolean retorno = true;

		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> iteratorArquivos = arquivos.iterator();
		
		while(iteratorArquivos.hasNext()){
			
			ArquivoTxtOSCobrancaSmartphoneHelper at = iteratorArquivos.next();
			if(at.getNomeFuncionario() == null){
				retorno = false;
				break;
			}
		}
		
		return retorno;
	}
	
	
	private boolean validarSituacao(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> arquivos,Integer situacao){
		boolean retorno = false;
		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> iteratorArquivos = arquivos.iterator();
		
		while(iteratorArquivos.hasNext()){
		
			ArquivoTxtOSCobrancaSmartphoneHelper at = iteratorArquivos.next();
			
			if(at.getIdSituacao().intValue() == situacao.intValue()){
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}

}
