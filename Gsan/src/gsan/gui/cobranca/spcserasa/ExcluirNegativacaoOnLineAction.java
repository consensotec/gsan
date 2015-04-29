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
* Thiago Silva Toscano de Brito
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
package gsan.gui.cobranca.spcserasa;

import gsan.cadastro.imovel.Imovel;
import gsan.cobranca.NegativadorExclusaoMotivo;
import gsan.cobranca.NegativadorMovimentoReg;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.spcserasa.bean.ExcluirNegativacaoOnLineHelper;
import gsan.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Thiago Silva Toscano de Brito, 
 *  	   Yara Taciane de Souza.
 * @date 22/12/2007
 */
public class ExcluirNegativacaoOnLineAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		ExcluirNegativacaoOnLineActionForm form = (ExcluirNegativacaoOnLineActionForm) actionForm; 
 
//		NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) getSessao(httpServletRequest).getAttribute("negativadorMovimentoReg");
		Imovel imovel = (Imovel) getSessao(httpServletRequest).getAttribute("imovel");
//		Collection itensConta = (Collection) getSessao(httpServletRequest).getAttribute("itensConta");
//		Collection itensGuiaPagamento = (Collection) getSessao(httpServletRequest).getAttribute("itensGuiaPagamento");

		NegativadorExclusaoMotivo negativadorExclusaoMotivo = new NegativadorExclusaoMotivo();
		negativadorExclusaoMotivo.setId(new Integer(form.getMotivoExclusao()));
		
		Date dataExclusao = Util.converteStringParaDate(form.getDataExclusao());

		Usuario usuarioPreenchido = new Usuario();
		usuarioPreenchido.setId(new Integer(form.getIdUsuario()));
		
			
		//..........................................................................................................

		if (!"".equals(form.getDataExclusao()) && form.getDataExclusao() != null) {
			
			String dtExclusao = form.getDataExclusao();
			if (Util.validarDiaMesAno(dtExclusao)) {
				throw new ActionServletException(
						"atencao.data_exclusao_invalida");
			}		
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			
			if(Util.compararData(dataExclusao, dataAtualSemHora) ==  1){
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data_exclusao_superior_data_corrente", null,
						dataAtual);
			}			
			
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data de Exclus�o");
		}
		
		//..........................................................................................................
//		if ((!"".equals(form.getDataExclusao()) && form.getDataExclusao() != null) && (!"".equals(form.getDataEnvio()) && form.getDataEnvio() != null)) {
//			
//			Date dtExclusao = Util.converteStringParaDate(form.getDataExclusao());
//			Date dtEnvio =  negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio();					
//			String dataEnvio= Util.formatarData(dtEnvio);
//			
//			if(Util.compararData(dtExclusao, dtEnvio) == -1){				
//				throw new ActionServletException(
//						"atencao.data_exclusao_nao_pode_inferior_data_envio", null,dataEnvio);
//			}			
//			
//		} 
		
		//..........................................................................................................
		
		//RM6364 - Altera��o para negativa��o por per�odo
		//alterado por Vivianne Sousa - 13/12/2011
		Collection<ExcluirNegativacaoOnLineHelper> colecaoRegExclusao = null;
		String idsClientes = httpServletRequest.getParameter("cliente");
		
		if (idsClientes != null && !idsClientes.equals("")){
			
			String[] idsClientesArray = idsClientes.split(",");
			colecaoRegExclusao = new ArrayList<ExcluirNegativacaoOnLineHelper>();
			Collection<ExcluirNegativacaoOnLineHelper> colecaoHelperSessao = 
					(Collection<ExcluirNegativacaoOnLineHelper>) sessao.getAttribute("colecaoHelper");
			Iterator itColecaoHelperSessao = colecaoHelperSessao.iterator();
			
			while (itColecaoHelperSessao.hasNext()) {
				ExcluirNegativacaoOnLineHelper helper = (ExcluirNegativacaoOnLineHelper) itColecaoHelperSessao.next();
				for(int x=0; x<idsClientesArray.length; x++){
					if (helper.getCliente().getId().equals(new Integer(idsClientesArray[x]))){
						
						if ((!"".equals(form.getDataExclusao()) && form.getDataExclusao() != null) && (!"".equals(form.getDataEnvio()) && form.getDataEnvio() != null)) {
							
							Date dtExclusao = Util.converteStringParaDate(form.getDataExclusao());
							Date dtEnvio =  helper.getNegativadorMovimentoReg().getNegativadorMovimento().getDataProcessamentoEnvio();					
							String dataEnvio= Util.formatarData(dtEnvio);
							
							if(Util.compararData(dtExclusao, dtEnvio) == -1){				
								throw new ActionServletException(
										"atencao.data_exclusao_nao_pode_inferior_data_envio", null,dataEnvio);
							}			
							
						} 
						
						colecaoRegExclusao.add(helper);
					}
				}
			}
		}
		
		Fachada.getInstancia().excluirNegativacaoOnLinePorPeriodo(imovel, colecaoRegExclusao, 
			negativadorExclusaoMotivo, dataExclusao, usuarioPreenchido,usuarioLogado);
		
//		Fachada.getInstancia().excluirNegativacaoOnLine(imovel, negativadorMovimentoReg, itensConta, itensGuiaPagamento, negativadorExclusaoMotivo, dataExclusao, usuarioPreenchido,usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Exclus�o do im�vel " + form.getIdImovel() + " efetuada com sucesso.",
				"Excluir outra Negativa��o","exibirExcluirNegativacaoOnLineAction.do?menu=sim","", "");
		
		

		
		
		
		
		return retorno;
	}
}