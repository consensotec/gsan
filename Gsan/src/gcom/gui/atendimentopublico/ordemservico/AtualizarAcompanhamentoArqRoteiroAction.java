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

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroAcompanhamentoArquivoRoteiro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
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
 * Action para Atualizar o Arquivo Texto do Acompanhamento de Arquivos de Roteiro
 * 
 * @author Th�lio Ara�jo
 * @date 28/07/2011
 *  
 */
public class AtualizarAcompanhamentoArqRoteiroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		Integer situacaoNova = null;	
		String descricaoSituacaoNova = "";
		
		AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanhamentoArquivosRoteiroActionForm = 
				(AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		// Saber se vai liberar ou nao liberar
		String atualizar = (String) httpServletRequest.getParameter("atualizar");
		
		if(atualizar == null){
			atualizar = (String) sessao.getAttribute("atualizar");
		}
		
		if (atualizar.equals("2")) {
			situacaoNova = SituacaoTransmissaoLeitura.LIBERADO;
			descricaoSituacaoNova = "LIBERADO";
		} else if (atualizar.equals("3")) {
			situacaoNova = SituacaoTransmissaoLeitura.EM_CAMPO;
			descricaoSituacaoNova = "EM CAMPO";
		} else if (atualizar.equals("4")) {
			situacaoNova = SituacaoTransmissaoLeitura.TRANSMITIDO;
			descricaoSituacaoNova = "FINALIZADO";
		}
		
		if (acompanhamentoArquivosRoteiroActionForm.getIdsRegistros() != null) {
			Vector<Integer> vetorIdsRegistros = new Vector<Integer>();
			for (int i = 0; i < acompanhamentoArquivosRoteiroActionForm
					.getIdsRegistros().length; i++) {
				
				FiltroAcompanhamentoArquivoRoteiro filtroAcompanhamentoArquivoRoteiro = new FiltroAcompanhamentoArquivoRoteiro();
				filtroAcompanhamentoArquivoRoteiro.adicionarParametro(new ParametroSimples(FiltroAcompanhamentoArquivoRoteiro.ID, 
						acompanhamentoArquivosRoteiroActionForm.getIdsRegistros()[i]));
				
				Collection<?> colecao = fachada.pesquisar(filtroAcompanhamentoArquivoRoteiro, ArquivoTextoAcompanhamentoServico.class.getName());
				if (colecao != null && !colecao.isEmpty()){
					Iterator<?> colecaoIt = colecao.iterator();
					while (colecaoIt.hasNext()){
						ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico =  (ArquivoTextoAcompanhamentoServico) colecaoIt.next();
						if (descricaoSituacaoNova == "LIBERADO" && (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.LIBERADO) || 
							arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO))){
							throw new ActionServletException(
									"atencao.nao_possivel.liberar_arquivo", null,
									descricaoSituacaoNova);
						}else if (descricaoSituacaoNova == "EM CAMPO" && arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)){
							throw new ActionServletException(
									"atencao.nao_possivel.em_campo_arquivo", null,
									descricaoSituacaoNova);
						}else if (descricaoSituacaoNova == "FINALIZADO" && (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.LIBERADO) || 
							arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO))){
							throw new ActionServletException(
									"atencao.nao_possivel.finalizar_arquivo", null,
									descricaoSituacaoNova);
						}
					}
				}
				
				if (situacaoNova != Util.converterStringParaInteger(acompanhamentoArquivosRoteiroActionForm.getIdsRegistros()[i])){
					vetorIdsRegistros.add(new Integer(acompanhamentoArquivosRoteiroActionForm
						.getIdsRegistros()[i]));
				}
			}
			
			fachada.atualizarListaArquivoTextoAcompArqRoteiro(vetorIdsRegistros, situacaoNova);

		} else {
			throw new ActionServletException(
					"atencao.nenhum_arquivo_mudar_situacao", null,
					descricaoSituacaoNova);
		}
			
		montarPaginaSucesso(httpServletRequest,
				"Situa��o do arquivo alterada para " + descricaoSituacaoNova.toLowerCase() + " com sucesso.",
				"Realizar um novo acompanhamento de arquivos de roteiro",
				"selecionarAcompanhamentoArquivosRoteiroAction.do");

		return retorno;
	}
}
