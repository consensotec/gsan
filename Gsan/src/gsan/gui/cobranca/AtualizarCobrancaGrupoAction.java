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
package gsan.gui.cobranca;

import java.util.Iterator;
import java.util.List;

import gsan.cobranca.CobrancaGrupo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.ContratoEmpresaServico;
import gsan.micromedicao.InformarItensContratoServicoHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCobrancaGrupoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCobrancaGrupoActionForm form = (AtualizarCobrancaGrupoActionForm) actionForm;

		CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) sessao.getAttribute("atualizarCobrancaGrupo");
		
		
		String idCobrancaGrupo = form.getId();	
		
		//Descri��o
		String descricao = form.getDescricao();
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descri��o");
		}
		
		// Descri��o Abreviada
		String descricaoAbreviada = form.getDescricaoAbreviada();
		if (descricaoAbreviada == null || 
			"".equals(descricaoAbreviada ) ) {
			throw new ActionServletException("atencao.required", null,"Descri��o Abreviada");
		}
		
		//Ano Mes Referencia
		String anoMesReferencia = form.getAnoMesReferencia();
		if ( anoMesReferencia == null || anoMesReferencia.equals("") ) {
			throw new ActionServletException("atencao.required", null,"M�s/Ano de Refer�ncia");
		}
		
		ContratoEmpresaServico contratoEmpresaServico = null;
		if (form.getIdNumeroContrato() != null && !form.getIdNumeroContrato().equals("")
				&& sessao.getAttribute("collectionContrato") != null
				&& !sessao.getAttribute("collectionContrato").equals("")) {
			List colecaoHelper = (List) sessao.getAttribute("collectionContrato");
			int posicaoComponente = new Integer(form.getIdNumeroContrato());
				
			Iterator iColecaoHelper = colecaoHelper.iterator();
			
			while (iColecaoHelper.hasNext()){
				
				InformarItensContratoServicoHelper helper = (InformarItensContratoServicoHelper)iColecaoHelper.next();
					
				if (helper.getContratoEmpresaServico() != null && 
						helper.getContratoEmpresaServico().getId() == posicaoComponente){
					
					contratoEmpresaServico = helper.getContratoEmpresaServico();
				}
				
			}
			
		}
        
		//Indicador de Uso
        Short indicadorUso = form.getIndicadorUso();
        if ( indicadorUso != null && !indicadorUso.equals("") ) {
        	indicadorUso = form.getIndicadorUso() ;
		} 
        
		String emailResponsavel = form.getEmailResponsavel();

		//Ano Mes Referencia
		Short indicadorExecucaoAutomatica = form.getIndicadorExecucaoAutomatica();
		if ( indicadorExecucaoAutomatica == null || indicadorExecucaoAutomatica.equals("") ) {
			throw new ActionServletException("atencao.required", null, "Execu��o Autom�tica" );
		}
			
        //Concatena ano mes para insercao
        String mes = anoMesReferencia.substring(0, 2);
        String ano = anoMesReferencia.substring(3, 7);

        anoMesReferencia = ano + "" + mes;
        
        cobrancaGrupo.setId(new Integer( idCobrancaGrupo ));
        cobrancaGrupo.setDescricao(descricao);
        cobrancaGrupo.setDescricaoAbreviada(descricaoAbreviada);
        cobrancaGrupo.setAnoMesReferencia(new Integer( anoMesReferencia ));
        cobrancaGrupo.setIndicadorUso(indicadorUso);
        cobrancaGrupo.setIndicadorExecucaoAutomatica(indicadorExecucaoAutomatica);
        cobrancaGrupo.setEmailResponsavel(emailResponsavel);
        cobrancaGrupo.setContratoEmpresaServico(contratoEmpresaServico);
		fachada.atualizar(cobrancaGrupo);

		
		montarPaginaSucesso(httpServletRequest, "Grupo de Cobran�a "
				+ idCobrancaGrupo + " atualizado com sucesso.",
				"Realizar outra Manuten��o de Grupo de Cobran�a ",
				"exibirFiltrarCobrancaGrupoAction.do?menu=sim");        
        
		return retorno;
	}
}