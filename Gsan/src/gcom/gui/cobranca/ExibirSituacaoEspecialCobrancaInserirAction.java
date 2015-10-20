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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaSituacaoMotivo;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.FiltroCobrancaSituacaoMotivo;
import gcom.cobranca.FiltroCobrancaSituacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da inser��o da situa��o especial da cobran�a
 * 
 * @author S�vio Luiz
 * @date 17/03/2006
 */

public class ExibirSituacaoEspecialCobrancaInserirAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirSituacaoEspecialCobrancaInserir");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm = (SituacaoEspecialCobrancaActionForm) actionForm;

		// Pesquisa Cobranca Situacao Tipo
		FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo();
		filtroCobrancaSituacaoTipo.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacaoTipo.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection collectionCobrancaSituacaoTipo = fachada.pesquisar(
				filtroCobrancaSituacaoTipo, CobrancaSituacaoTipo.class
						.getName());
		
		httpServletRequest.setAttribute("collectionCobrancaSituacaoTipo",
				collectionCobrancaSituacaoTipo);
		
		
		if(situacaoEspecialCobrancaActionForm.getIdCobrancaSituacaoTipo()!=null){ 
				
				Iterator it = collectionCobrancaSituacaoTipo.iterator();
				
				while(it.hasNext()){
					
					CobrancaSituacaoTipo cobrancaSituacaoTipo =(CobrancaSituacaoTipo) it.next();
					
					if(situacaoEspecialCobrancaActionForm.getIdCobrancaSituacaoTipo().toString()
							.equals(cobrancaSituacaoTipo.getId().toString())){
						if(cobrancaSituacaoTipo.getIndicadorInformarDataFinal().toString()
								.equals(ConstantesSistema.INDICADOR_USO_ATIVO.toString())){
							
							situacaoEspecialCobrancaActionForm.setIndicadorInformarDataFimSituacao(
									cobrancaSituacaoTipo.getIndicadorInformarDataFinal().toString());
							
							httpServletRequest.setAttribute("informarDataFimSituacao",true);
						}else{
							situacaoEspecialCobrancaActionForm.setDataFimSituacao("");
						}
					}
				}
		}
		
		
		FiltroCobrancaSituacaoMotivo filtroCobrancaSituacaoMotivo = new FiltroCobrancaSituacaoMotivo();
		Collection collectionCobrancaSituacaoMotivo = fachada.pesquisar(
				filtroCobrancaSituacaoMotivo,
				CobrancaSituacaoMotivo.class.getName());
		httpServletRequest.setAttribute("collectionCobrancaSituacaoMotivo",
				collectionCobrancaSituacaoMotivo);

		if(situacaoEspecialCobrancaActionForm
				.getQuantidadeImoveisCOMSituacaoEspecialCobranca() != null){
			
			String COM = situacaoEspecialCobrancaActionForm
					.getQuantidadeImoveisCOMSituacaoEspecialCobranca();
		
			String SEM = situacaoEspecialCobrancaActionForm
					.getQuantidadeImoveisSEMSituacaoEspecialCobranca();
			String quantidadeDeImoveisAtualizados = Integer.toString(Integer
					.parseInt(COM)
					+ Integer.parseInt(SEM));
		
		if (quantidadeDeImoveisAtualizados.equals("0"))
			throw new ActionServletException(
					"atencao.imovel.sem.situacao.especial.cobranca", null,
					"");
		
			situacaoEspecialCobrancaActionForm
					.setQuantidadeDeImoveis(quantidadeDeImoveisAtualizados);
		}
		return retorno;
	}

}
