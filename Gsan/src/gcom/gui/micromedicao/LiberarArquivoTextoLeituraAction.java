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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
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
 * Action para n�o Liberar o Arquivo Texto
 * 
 * @author Thiago Ten�rio , Thiago Nascimento e Yara T. Souza, Hugo Amorim
 * @date 19/12/2008 , 19/08/2010
 *  
 * 
 */
public class LiberarArquivoTextoLeituraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		Integer situacaoNova = null;	
		String descricaoSituacaoNova = "";

		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
		
		ConsultarArquivoTextoLeituraActionForm consultarArquivoTextoLeituraActionForm = null;
		
		if (httpServletRequest.getParameter("confirmado") == null){
			
			consultarArquivoTextoLeituraActionForm = (ConsultarArquivoTextoLeituraActionForm) actionForm;
			
		} else {
			
			consultarArquivoTextoLeituraActionForm = (ConsultarArquivoTextoLeituraActionForm) sessao.getAttribute("consultarArquivoTextoLeituraActionForm");
		}
		
		
		// Saber se vai liberar ou nao liberar
		String liberar = (String) httpServletRequest.getParameter("liberar");
		
		/**
		 * CRC 
		 * autor: Yara T. Souza
		 * data : 19/12/2008
		 * Acrescentado 2 novos bot�es para alterar a situa��o do arquivo texto.
		 */
		
		if(liberar == null){
			liberar = (String) sessao.getAttribute("liberar");
		}
		
		if (liberar.equals("0")) {
			situacaoNova = SituacaoTransmissaoLeitura.DISPONIVEL;
			descricaoSituacaoNova = "DISPONIVEL";
		} else if (liberar.equals("1")) {
			situacaoNova = SituacaoTransmissaoLeitura.LIBERADO;
			descricaoSituacaoNova = "LIBERADO";
		} else if (liberar.equals("2")) {
			situacaoNova = SituacaoTransmissaoLeitura.EM_CAMPO;
			descricaoSituacaoNova = "EM CAMPO";
		} else if (liberar.equals("3")) {
			situacaoNova = SituacaoTransmissaoLeitura.TRANSMITIDO;
			descricaoSituacaoNova = "FINALIZADO";
		} else if (liberar.equals("7")) {
			situacaoNova = SituacaoTransmissaoLeitura.FINALIZADO_USUARIO;
			descricaoSituacaoNova = "FINALIZADO PELO USUARIO";
		} else if ( liberar.equals( "9" ) ){
			situacaoNova = SituacaoTransmissaoLeitura.INFORMAR_MOTIVO_FINALIZACAO;
			descricaoSituacaoNova = "MOTIVO DE FINALIZA��O INFORMADO";			
		}
		
		
		//Valida tamanho da descri��o do motivo de finali��o
		String motivoFinalizacao = null;
		if(consultarArquivoTextoLeituraActionForm.getMotivoFinalizacao()!=null
				&& !consultarArquivoTextoLeituraActionForm.getMotivoFinalizacao().equals("")){
			
			if(consultarArquivoTextoLeituraActionForm.getMotivoFinalizacao().length()>200){
				String[] msg = new String[2];
				msg[0]="Motivo Finaliza��o";
				msg[1]="200";
				
				throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
			}
			
			motivoFinalizacao = consultarArquivoTextoLeituraActionForm.getMotivoFinalizacao();
		}
		
		if (consultarArquivoTextoLeituraActionForm.getIdsRegistros() != null) {
			Vector<Integer> v = new Vector<Integer>();
			for (int i = 0; i < consultarArquivoTextoLeituraActionForm
					.getIdsRegistros().length; i++) {
				
				if ( liberar.equals("7") ) {
					boolean verificaArquivo = fachada.verificarSituacaoRotaSudividida(
							new Integer(consultarArquivoTextoLeituraActionForm.getIdsRegistros()[i]));
					
					if ( verificaArquivo ) {
						throw new ActionServletException("atencao.arquivos_subdivididos_nao_finalizados");
					} 
				}
//				if ( liberar.equals("0") ) {
//					boolean verificaArquivo = fachada.verificarExistenciaRotaSudividida(
//							new Integer(consultarArquivoTextoLeituraActionForm.getIdsRegistros()[i]));
//					
//					if ( verificaArquivo ) {
//						//throw new ActionServletException("atencao.arquivos_subdivididos");
//					} 
//				}
				if ( liberar.equals("0") ) {
					FiltroArquivoTextoRoteiroEmpresaDivisao filtroArquivoTextoRoteiroEmpresaDivisao = null;
					
					String idArqRoteiroEmpresa = consultarArquivoTextoLeituraActionForm.getIdsRegistros()[i];
					sessao.setAttribute("idArquivoTextoRoteiroEmpresaDivisao", idArqRoteiroEmpresa);
					
				
						// pesquisa Arquivo Texto Roteiro Empresa Divis�o
						filtroArquivoTextoRoteiroEmpresaDivisao = new FiltroArquivoTextoRoteiroEmpresaDivisao();
						
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarParametro(new ParametroSimples(
								FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA_ID,
								idArqRoteiroEmpresa));
						
						String[] orderby = new String[] {
								FiltroArquivoTextoRoteiroEmpresaDivisao.NUMERO_SEQUENCIA_LEITURA, 
								FiltroArquivoTextoRoteiroEmpresaDivisao.SITUACAO_TRANS_LEITURA};
						
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
								FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA);
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
								FiltroArquivoTextoRoteiroEmpresa.SITUACAO_TRANS_LEITURA);
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
								"arquivoTextoRoteiroEmpresa.localidade");
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
								"arquivoTextoRoteiroEmpresa.rota");
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
								"arquivoTextoRoteiroEmpresa.situacaoTransmissaoLeitura");
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
						"leiturista");
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
						"leiturista.cliente");
						filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
						"leiturista.funcionario");
						
						filtroArquivoTextoRoteiroEmpresaDivisao.setCampoOrderBy(orderby);
						
						Collection colecaoArquivoTextoRoteiroEmpresaDivisao = Fachada.getInstancia()
								.pesquisar(filtroArquivoTextoRoteiroEmpresaDivisao, ArquivoTextoRoteiroEmpresaDivisao.class.getName());
						if(colecaoArquivoTextoRoteiroEmpresaDivisao != null && !colecaoArquivoTextoRoteiroEmpresaDivisao.isEmpty()){
							
								Iterator it = colecaoArquivoTextoRoteiroEmpresaDivisao.iterator();			
								while(it.hasNext()){
									ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = 
											(ArquivoTextoRoteiroEmpresaDivisao) it.next();
							
								if(arquivoTextoRoteiroEmpresaDivisao.getSituacaoTransmissaoLeitura() != null &&
										!arquivoTextoRoteiroEmpresaDivisao.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.DISPONIVEL)){
									throw new ActionServletException("atencao.arquivo_nao_pode_atualizar_para_disponivel");
								}
							}
						}

					}
				
				if ( liberar.equals("1") ) {
					boolean verificaArquivo = fachada.verificarExistenciaRotaSudividida(
							new Integer(consultarArquivoTextoLeituraActionForm.getIdsRegistros()[i]));
					
					if ( verificaArquivo ) {
						throw new ActionServletException("atencao.arquivos_subdivididos");
					} 
				}
				
				
				
				v.add(new Integer(consultarArquivoTextoLeituraActionForm
						.getIdsRegistros()[i]));
		}
			
			Integer idServicoTipoCelular = ServicoTipoCelular.LEITURA;
			if(consultarArquivoTextoLeituraActionForm.getServicoTipoCelular() != null && 
					!consultarArquivoTextoLeituraActionForm.getServicoTipoCelular().equals("")){
				idServicoTipoCelular = Util.converterStringParaInteger(consultarArquivoTextoLeituraActionForm.getServicoTipoCelular());
			}
			
			String valorConfirmacao = httpServletRequest.getParameter("confirmado");
			
			// Alteracao solitcitada por Leo para deixar finalizar aquivos que sejam de impressao simultanea		
			//Permissao Especial FINALIZAR_ARQUIVO_TEXTO_DE_LEITURA

			boolean temPermissaoFinalizarArquivo = Fachada.getInstancia()
					.verificarPermissaoEspecial(
							PermissaoEspecial.FINALIZAR_ARQUIVO_TEXTO_DE_LEITURA,
							usuarioLogado);
			
			boolean realizouLeituras = Fachada.getInstancia().verificarLeiturasRealizadas(v,idServicoTipoCelular);
			
			if(temPermissaoFinalizarArquivo && (liberar.equals("3") || liberar.equals("7")) 
					&& (valorConfirmacao == null) && !realizouLeituras) {
				
					sessao.setAttribute("liberar", liberar);
					sessao.setAttribute("consultarArquivoTextoLeituraActionForm", consultarArquivoTextoLeituraActionForm);
					
					// Se for para ir para a tela de confirma��o
					httpServletRequest.setAttribute("caminhoActionConclusao",
	                        "/gsan/liberarArquivoLeituraAction.do");
	                httpServletRequest.setAttribute("cancelamento", "TRUE");
	                httpServletRequest.setAttribute("nomeBotao1", "Sim");
	                httpServletRequest.setAttribute("nomeBotao2", "N�o");
	                

	                return montarPaginaConfirmacao("atencao.confirmar.leituras.nao.recebidas",
	                        httpServletRequest, actionMapping);
			}  
			
			
				
				
			if(temPermissaoFinalizarArquivo && valorConfirmacao != null && valorConfirmacao.equals("cancelar")){
				return retorno = actionMapping.findForward("consultarArquivoTextoLeitura");
			}
				
			fachada.atualizarListaArquivoTexto(v, situacaoNova,
					idServicoTipoCelular,temPermissaoFinalizarArquivo,
					motivoFinalizacao);

		} else {
			
			throw new ActionServletException(
					"atencao.nenhum_arquivo_mudar_situacao", null,
					descricaoSituacaoNova);
			
		}
			
		montarPaginaSucesso(httpServletRequest,
				"Arquivo Texto para Leitura alterado para " + descricaoSituacaoNova.toLowerCase() + " com sucesso.",
				"Realizar outra Manuten��o de Arquivo Texto para Leitura",
				"consultarArquivoTextoLeituraAction.do");

		consultarArquivoTextoLeituraActionForm.setIdsRegistros(null);

		return retorno;

	}
}		


