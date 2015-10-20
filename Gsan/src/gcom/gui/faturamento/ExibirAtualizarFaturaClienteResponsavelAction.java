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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.FiltroFaturaItem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Fl�vio Leonardo
 * @date 26/11/2008
 */
public class ExibirAtualizarFaturaClienteResponsavelAction extends GcomAction {

	/**
	 * M�todo responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarFaturaClienteResponsavel");

		FiltrarFaturaClienteResponsavelActionForm form = (FiltrarFaturaClienteResponsavelActionForm) actionForm;

		// Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroFaturaItem filtroFaturaItem = (FiltroFaturaItem) sessao
			.getAttribute("filtroFaturaItem");
		
		Integer quantidadeTotalFaturas = (Integer) sessao
				.getAttribute("quantidadeTotalFaturas");
		
		Collection<FaturaItem> colecaoFaturaItem = null;

		if(sessao.getAttribute("colecaoFaturaItem") == null){
			colecaoFaturaItem = fachada.pesquisar(filtroFaturaItem, FaturaItem.class.getName());
		}else{
			colecaoFaturaItem = (Collection)sessao.getAttribute("colecaoFaturaItem");
		}
		

		if(colecaoFaturaItem.isEmpty() && ( form.getImovelId() == null || form.getImovelId().trim().equals("") )){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}else if(colecaoFaturaItem.isEmpty() && (form.getImovelId() != null && !form.getImovelId().trim().equals(""))){
			throw new ActionServletException("atencao.imovel.sem.fatura");
		}else{
			
			sessao.setAttribute("colecaoFaturaItem", colecaoFaturaItem);
			
			FaturaItem faturaItem = (FaturaItem)colecaoFaturaItem.iterator().next();
			
			Fatura fatura = faturaItem.getFatura();
			sessao.setAttribute("fatura", fatura);
			form.setValorFatura(Util.formatarMoedaReal(fatura.getDebito()));
			form.setDataVencimentoFatura(Util.formatarData(fatura.getVencimento()));
		}
		
		
		String[] faturaItemRemoverId = form.getIdRegistrosRemocao();

		Collection colecaoFaturaItemRemover = null;
		if(sessao.getAttribute("colecaoFaturaItemRemover") != null){
			colecaoFaturaItemRemover = (Collection)sessao.getAttribute("colecaoFaturaItemRemover");
		}else{
			colecaoFaturaItemRemover = new ArrayList();
		}
		
		if(faturaItemRemoverId != null && !faturaItemRemoverId.equals("")){
			
			for(int i=0; i< faturaItemRemoverId.length; i++){
				Iterator iterator = colecaoFaturaItem.iterator();
					
				while(iterator.hasNext()){
					FaturaItem faturaItemRemover = (FaturaItem) iterator.next();
					if(faturaItemRemover.getImovel().getId() != null && 
							faturaItemRemover.getImovel().getId().equals(new Integer(faturaItemRemoverId[i]))){
						colecaoFaturaItemRemover.add(faturaItemRemover);
						iterator.remove();
						quantidadeTotalFaturas --;
						break;
					}
				}
					
			}
			
			sessao.setAttribute("colecaoFaturaItemRemover",colecaoFaturaItemRemover);
		}
		
		if(!colecaoFaturaItemRemover.isEmpty()){
			Iterator iteratorRemovidos = colecaoFaturaItemRemover.iterator();
			
			while(iteratorRemovidos.hasNext()){
				FaturaItem faturaItemRemovido = (FaturaItem)iteratorRemovidos.next();
				Iterator iterator = colecaoFaturaItem.iterator();
				
				while(iterator.hasNext()){
					FaturaItem faturaItemRemover = (FaturaItem) iterator.next();
					if(faturaItemRemover.getImovel().getId() != null && faturaItemRemovido.getImovel().getId() != null
						&& faturaItemRemover.getImovel().getId().equals(faturaItemRemovido.getImovel().getId())){
						iterator.remove();
						break;
					}
				}
			}
		}
		
		/* UC0871 [FS0009]*/
		if(quantidadeTotalFaturas != null && quantidadeTotalFaturas < 1){
			throw new ActionServletException("atencao.fatura.cliente.responsavel.conter.uma");
		}else{
			Iterator iteratorSoma = colecaoFaturaItem.iterator();
			BigDecimal valorTotal = BigDecimal.ZERO;
			
			form.setValorFatura(Util.formatarMoedaReal(valorTotal));
			
			while(iteratorSoma.hasNext()){
				FaturaItem faturaItemSoma = (FaturaItem) iteratorSoma.next();
				
				valorTotal = valorTotal.add(faturaItemSoma.getValorConta());
				
				form.setValorFatura(Util.formatarMoedaReal(valorTotal));
			}
			
		}
		
		sessao.setAttribute("quantidadeTotalFaturas",quantidadeTotalFaturas);
		
		return retorno;
	}
}