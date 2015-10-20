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
 * Yara Taciane de Souza
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

import gcom.atendimentopublico.ordemservico.FiltroMotivoRejeicao;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.MotivoRejeicao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0648] Exibir Acompanhamento Processo Repavimenta��o
 * 		[SB0003] - Rejeitar Servi�o de Repavimenta��o
 * 
 * @author Hugo Leonardo		
 * @date 09/12/2010
 */

public class RejeitarOrdemProcessoRepavimentacaoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("rejeitarOrdemProcessoRepavimentacaoPopup");
		
		AtualizarOrdemProcessoRepavimentacaoPopUpActionForm form = (AtualizarOrdemProcessoRepavimentacaoPopUpActionForm) actionForm;

		//Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
       	//Integer idOrdemServico = (Integer) sessao.getAttribute("numeroOS");
        OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) sessao.getAttribute("ordemServicoPavimento");
		
		String dataRejeicao = null;
		String idMotivoRejeicao = null;
		String descricaoRejeicao = null;
		
		if(form.getIdMotivoRejeicao()!= null && !form.getIdMotivoRejeicao().equals("") 
				&& !form.getIdMotivoRejeicao().equals("-1")){
			
			idMotivoRejeicao = form.getIdMotivoRejeicao();
		}
		
		if(form.getDescricaoRejeicao()!= null && !"".equals(form.getDescricaoRejeicao())){
			descricaoRejeicao = form.getDescricaoRejeicao();
		}
		
		if(form.getDataRejeicao()!= null && !form.getDataRejeicao().equals("")){
			
			dataRejeicao = this.validarDataRejeicao( form.getDataRejeicao(), ordemServicoPavimento);		
		}else{
			throw new ActionServletException("atencao.campo.informada", null, "Data da Rejei��o ");
		}
		
		FiltroMotivoRejeicao filtroMotivoRejeicao = new FiltroMotivoRejeicao();
		
		filtroMotivoRejeicao.adicionarParametro(
				new ParametroSimples(FiltroMotivoRejeicao.ID, idMotivoRejeicao));	
		
		Collection colecaoMotivoRejeicao = fachada.pesquisar(filtroMotivoRejeicao, MotivoRejeicao.class.getName());		
		
		MotivoRejeicao motivoRejeicao = (MotivoRejeicao) Util.retonarObjetoDeColecao(colecaoMotivoRejeicao);
    	
    	ordemServicoPavimento.setDataRejeicao(Util.converteStringParaDate(dataRejeicao));	
        ordemServicoPavimento.setDescricaoRejeicao(descricaoRejeicao);
		ordemServicoPavimento.setMotivoRejeicao(motivoRejeicao);
		ordemServicoPavimento.setUsuarioRejeicao(usuario);
		ordemServicoPavimento.setUltimaAlteracao(new Date());
		
		fachada.atualizar(ordemServicoPavimento);
			
		httpServletRequest.setAttribute("fecharPopup", "OK");
		
		form.setIdMotivoRejeicao("");
		form.setDescricaoRejeicao("");
		form.setDataRejeicao("");
		
		return retorno;
	}
	
	/**
	 * [FS0004] ? Validar Data da Rejei��o.
	 * @param dataRejeicao
	 * @return
	 */
	public String validarDataRejeicao(String dataRejeicao , OrdemServicoPavimento ordemServicoPavimento){
			
		Date dtRejeicao = Util.converteStringParaDate(dataRejeicao);			
		Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
		
		if(Util.compararData(dtRejeicao, dataAtualSemHora) == 1){
			String dataAtual = Util.formatarData(new Date());
			throw new ActionServletException(
					"atencao.data.superior.data.corrente", null, dataAtual);				
			
		}
		
		if ( ordemServicoPavimento.getPavimentoRuaRetorno() == null ) {
			
			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro( new ParametroSimples(FiltroOrdemServico.ID,  
					ordemServicoPavimento.getOrdemServico().getId()));
			
			Collection colecaoOrdemServico = Fachada.getInstancia().pesquisar(
					filtroOrdemServico, OrdemServico.class.getName());
			
			if ( colecaoOrdemServico != null ) {
				OrdemServico ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdemServico); 
				
				if ( dtRejeicao.before(ordemServico.getDataEncerramento()) ){
					throw new ActionServletException(
							"atencao.data.rejeicao.menor.data.encerramento", null,
							Util.formatarData(ordemServico.getDataEncerramento())  );	
				}
			}
			
		} else {
			if ( ordemServicoPavimento.getIndicadorAceite() == null ) {
				
				if ( dtRejeicao.before(ordemServicoPavimento.getDataExecucao()) ){
					throw new ActionServletException(
							"atencao.data.rejeicao.menor.data.retorno", null,
								ordemServicoPavimento.getDataExecucao().toString() );	
				}
			} else if ( ordemServicoPavimento.getIndicadorAceite().toString().equals("2") &&
					dtRejeicao.before(ordemServicoPavimento.getDataAceite() ) ) {
				throw new ActionServletException(
						"atencao.data.rejeicao.menor.data.aceite", null,
							ordemServicoPavimento.getDataAceite().toString() );
			}
		}

		return dataRejeicao; 
	}
}