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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.FiltroAgenciaReguladoraMotRetorno;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author K�ssia Albuquerque
 * @date 02/05/2007
 */

public class ExibirFiltrarRaDadosAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			
			ActionForward retorno = actionMapping.findForward("filtrarRaDadosAgenciaReguladora");
	
			FiltrarRaDadosAgenciaReguladoraActionForm form = (FiltrarRaDadosAgenciaReguladoraActionForm) actionForm;
	
			Fachada fachada = Fachada.getInstancia();
	
			
			if (httpServletRequest.getParameter("menu") != null) {
				
				form.setIndicadorSituacaoAgencia(ConstantesSistema.SITUACAO_AGENCIA_TODOS.toString());
				form.setIndicadorSituacaoRA(ConstantesSistema.SITUACAO_RA_AGENCIA_TODOS.toString());
			}

			
			
			// CARREGANDO MOTIVO RECLAMA��O AGENCIA DA TABELA AGENCIA_REGULADORA_MOTIVO_RECLAMA��O
			
			FiltroAgenciaReguladoraMotReclamacao filtroAgenciaReguladoraMotReclamacao = 
						new FiltroAgenciaReguladoraMotReclamacao();
			
			filtroAgenciaReguladoraMotReclamacao.setCampoOrderBy(FiltroAgenciaReguladoraMotReclamacao.DESCRICAO);
			
			Collection colecaoMotivoReclamacao = fachada.pesquisar(
						filtroAgenciaReguladoraMotReclamacao, AgenciaReguladoraMotReclamacao.class.getName());
			
			if (colecaoMotivoReclamacao == null || colecaoMotivoReclamacao.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo Reclama��o da Ag�ncia");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoReclamacao",colecaoMotivoReclamacao);
			
			
			
			// CARREGANDO MOTIVO ENCERRAMENTO ATENDIMENTO DA TABELA ATENDIMENTO_MOTIVO_ENCERRAMENTO
			
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = 
						new FiltroAtendimentoMotivoEncerramento();
			
			filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
			
			Collection colecaoMotivoEncerramentoAtendimento = fachada.pesquisar(
					filtroAtendimentoMotivoEncerramento,AtendimentoMotivoEncerramento.class.getName());
			
			if (colecaoMotivoEncerramentoAtendimento == null || colecaoMotivoEncerramentoAtendimento.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo Encerramento do Atendimento");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoEncerramentoAtendimento",colecaoMotivoEncerramentoAtendimento);
			
			
			
			// CARREGANDO MOTIVO ENCERRAMENTO ATENDIMENTO DA TABELA AGENCIA_REGULADORA_MOTIVO_RETORNO
			
			FiltroAgenciaReguladoraMotRetorno filtroAgenciaReguladoraMotRetorno = 
						new FiltroAgenciaReguladoraMotRetorno();
			
			filtroAgenciaReguladoraMotRetorno.setCampoOrderBy(FiltroAgenciaReguladoraMotRetorno.DESCRICAO);
			
			Collection colecaoMotivoRetornoAgencia = fachada.pesquisar(
					filtroAgenciaReguladoraMotRetorno, AgenciaReguladoraMotRetorno.class.getName());
			
			if (colecaoMotivoRetornoAgencia == null || colecaoMotivoRetornoAgencia.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo do Retorno para Ag�ncia");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoRetornoAgencia",colecaoMotivoRetornoAgencia);
			
			
			return retorno;
		}
}
