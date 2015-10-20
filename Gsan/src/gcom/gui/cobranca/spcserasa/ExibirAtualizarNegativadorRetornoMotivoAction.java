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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter Negativador
 * @author Thiago Vieira
 * @created 22/12/2007
 */

public class ExibirAtualizarNegativadorRetornoMotivoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("exibirAtualizarNegativadorRetornoMotivo");
        AtualizarNegativadorRetornoMotivoActionForm form = (AtualizarNegativadorRetornoMotivoActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        Collection collectionNegativadorRetornoMotivo = null;
        FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
        Fachada fachada = Fachada.getInstancia();
        
        if (httpServletRequest.getParameter("reexibir") == null || !httpServletRequest.getParameter("reexibir").equals("true")){ 
	        // volta da msg de Contrato do Negativador j� utilizado, n�o pode ser
			// alterado nem exclu�do.
			String confirmado = httpServletRequest.getParameter("confirmado");
	
			String idNegativadorRetornoMotivo = null;
			if (httpServletRequest.getParameter("reload") == null
					|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
							"") && (confirmado == null || confirmado.equals(""))) {
				
				if (httpServletRequest.getParameter("idAtualizar") != null) {
					idNegativadorRetornoMotivo = httpServletRequest.getParameter("idAtualizar");
					httpServletRequest.setAttribute("voltar", "filtrar");
					sessao.setAttribute("idRegistroAtualizacao", idNegativadorRetornoMotivo);
				} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
					idNegativadorRetornoMotivo = (String) sessao.getAttribute("idRegistroAtualizacao");
					httpServletRequest.setAttribute("voltar", "filtrar");
				} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
					idNegativadorRetornoMotivo = httpServletRequest.getParameter("idRegistroAtualizacao");
					httpServletRequest.setAttribute("voltar", "manter");
					sessao.setAttribute("idRegistroAtualizacao", idNegativadorRetornoMotivo);
				}
			} else {
				idNegativadorRetornoMotivo = (String) sessao.getAttribute("idRegistroAtualizacao");
			}
			
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.ID, idNegativadorRetornoMotivo));
			filtroNegativadorRetornoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
						
			collectionNegativadorRetornoMotivo = fachada.pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
			
			NegativadorRetornoMotivo negativadorRetornoMotivo = (NegativadorRetornoMotivo) ((List) collectionNegativadorRetornoMotivo).get(0);
			
			form.setNomeCliente(negativadorRetornoMotivo.getNegativador().getCliente().getNome());
			form.setCodigoMotivo(Short.toString(negativadorRetornoMotivo.getCodigoRetornoMotivo()));
			form.setDescricaoRetornoMotivo(negativadorRetornoMotivo.getDescricaoRetornocodigo());
			form.setIndicadorUso(Short.toString(negativadorRetornoMotivo.getIndicadorUso()));
			form.setIndicadorRegistroAceito(Short.toString(negativadorRetornoMotivo.getIndicadorRegistroAceito()));
			
			form.setOkCodigoMotivo("true");
			form.setIdNegativadorRetornoMotivo(idNegativadorRetornoMotivo);
	        form.setTime(Long.toString(negativadorRetornoMotivo.getUltimaAlteracao().getTime()));
	        form.setIdNegativador(negativadorRetornoMotivo.getNegativador().getId().toString());
	        
		// carregar dados a partir das entradas do form. 
    	}else {
//    		String idNegativador = (String) sessao.getAttribute("idRegistroAtualizacao");
//    		
//			String idDigitadoEnterCliente = (String) form.getCodigoCliente();
//	        String idDigitadoEnterImovel = (String) form.getCodigoImovel();
//	        String idDigitadoEnterAgente = (String) form.getCodigoAgente();
//	        
//	        form.setOkAgente("false");
//	        form.setOkCliente("false");
//	        form.setOkImovel("false");
//	        
//	        // verifica se o codigo do cliente foi digitado
//	        if (idDigitadoEnterCliente != null
//	                && !idDigitadoEnterCliente.trim().equals("")
//	                && Integer.parseInt(idDigitadoEnterCliente) > 0) {
//	            
//	        	FiltroCliente filtroCliente = new FiltroCliente();
//				
//				filtroCliente.adicionarParametro(new ParametroSimples(
//						FiltroCliente.ID, idDigitadoEnterCliente));
//				filtroCliente.adicionarParametro(new ParametroSimples(
//						FiltroCliente.INDICADOR_USO,
//						ConstantesSistema.INDICADOR_USO_ATIVO));
//	
//				Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
//						Cliente.class.getName());
//	
//				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
//					// O Cliente foi encontrado
//					if (((Cliente) ((List) clienteEncontrado).get(0))
//							.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
//						throw new ActionServletException("atencao.cliente.inativo",	null, ""
//										+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
//					}
//					
//					
//					
//				 //-------------------------------------------------------------------
//					
//					Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clienteEncontrado);
//					FiltroNegativador filtroNegat = new FiltroNegativador();
//					filtroNegat.adicionarCaminhoParaCarregamentoEntidade("cliente");
//					filtroNegat.adicionarParametro(new ParametroSimples(
//							FiltroNegativador.NEGATIVADOR_CLIENTE, cliente.getId()));				
//					filtroNegat.adicionarParametro(new ParametroSimplesDiferenteDe(
//							FiltroNegativador.ID, idNegativador));				
//
//					Collection collNegativador = fachada.pesquisar(filtroNegat,	Negativador.class.getName());
//					
////					if(collNegativador != null && !collNegativador.isEmpty()){
////						Iterator i = collNegativador.iterator();
////						while(i.hasNext()){
////							Negativador negativador = (Negativador) i.next();
////							if (negativador.getCliente().getId().compareTo(new Integer(idDigitadoEnterCliente)) == 0 && negativador.getId() != new Integer(idNegativador)){
////								throw new ActionServletException("atencao.negativador_associado_cliente");
////							}
////						}
////					}
//					
//					if(collNegativador != null && !collNegativador.isEmpty()){
//						throw new ActionServletException("atencao.negativador_associado_cliente");
//					}
//					
//	                //---------------------------------------------------------------------
//						
//	
//					form.setCodigoCliente(((Cliente) ((List) clienteEncontrado)
//									.get(0)).getId().toString());
//					form.setNomeCliente(((Cliente) ((List) clienteEncontrado)
//									.get(0)).getNome());
//					form.setOkCliente("true");
//				} else {
//					httpServletRequest.setAttribute("corCliente","exception");
//	               	form.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
//	               	form.setCodigoCliente("");
//	               	form.setOkCliente("false");
//				}
//	        }
//	        
//	        if (idDigitadoEnterImovel != null && !idDigitadoEnterImovel.trim().equals("")) {
//				// Pesquisa o imovel na base
//	        	
//	        	FiltroImovel filtroImovel = new FiltroImovel();
//	        	filtroImovel.adicionarParametro(new ParametroSimples(
//	        			FiltroImovel.ID, idDigitadoEnterImovel));
//	        	
//	        	Collection collImovel = fachada.pesquisar(filtroImovel,
//	        			Imovel.class.getName());
//	        	
//	        	Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(collImovel);
//	        	
//				if (imovel != null) {
//					//-------------------------------------------------------------------
//								
//					FiltroNegativador filtroNegat = new FiltroNegativador();
//					filtroNegat.adicionarParametro(new ParametroSimples(
//							FiltroNegativador.NEGATIVADOR_IMOVEL, imovel.getId()));
//					filtroNegat.adicionarCaminhoParaCarregamentoEntidade("imovel");
//					filtroNegat.adicionarParametro(new ParametroSimplesDiferenteDe(
//							FiltroNegativador.ID, idNegativador));
//					Collection collNegativador = fachada.pesquisar(filtroNegat,
//							Negativador.class.getName());
//					
//					if(collNegativador != null && !collNegativador.isEmpty()){
//						throw new ActionServletException("atencao.negativador_associado_imovel");
//					}
//					
////					if(collNegativador != null && !collNegativador.isEmpty()){
////						Iterator i = collNegativador.iterator();
////						while(i.hasNext()){
////							Negativador negativador = (Negativador) i.next();
////							if (negativador.getImovel().getId() == new Integer(idDigitadoEnterImovel) && negativador.getId() != new Integer(idNegativador)){
////								throw new ActionServletException("atencao.negativador_associado_imovel");
////							}
////						}
////					}
//					
//					
//	           //------------------------------------------------------------------------------------------------
//	        	
//	        	
//				String imovelEncontrado = fachada.pesquisarInscricaoImovel(new Integer(idDigitadoEnterImovel));
//	        	
//				if (imovelEncontrado != null && !imovelEncontrado.equalsIgnoreCase("")) {					
//					// O imovel foi encontrado
//					form.setCodigoImovel(idDigitadoEnterImovel);
//					form.setInscricaoImovel(imovelEncontrado);
//					form.setOkImovel("true");
//					
//				}	
//					
//				} else {
//					httpServletRequest.setAttribute("corImovel","exception");
//	           		form.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
//	           		form.setCodigoImovel("");
//	           		form.setOkImovel("false");
//				}
//	        }
//	        
//	        if (idDigitadoEnterAgente != null && !idDigitadoEnterAgente.trim().equals("")){
//	        	FiltroNegativador fNegativador = new FiltroNegativador();
//	        	fNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.CODIGO_AGENTE, idDigitadoEnterAgente));
//	        	
//	        	Collection codigoAgenteEncontrado = Fachada.getInstancia().pesquisar(fNegativador, Negativador.class.getName());
//	        	        	
//	        	if (codigoAgenteEncontrado != null && !codigoAgenteEncontrado.isEmpty()) {
//	        		
//	        		if (((Negativador) ((List) codigoAgenteEncontrado).get(0)).getIndicadorUso() 
//	        				== ConstantesSistema.INDICADOR_USO_DESATIVO.shortValue()) {
//						throw new ActionServletException("atencao.negativador.inativo",
//								null, ""
//										+ ((Negativador) ((List) codigoAgenteEncontrado)
//												.get(0)).getId());
//					}
//	        		
//	           		form.setOkAgente("true");
//	        	} else {
//	        		form.setCodigoAgente("");
//	        		httpServletRequest.setAttribute("corAgente","exception");
//	           		form.setMensagemAgente(ConstantesSistema.CODIGO_AGENTE_NAO_CADASTRADO);
//	        		form.setOkAgente("false");
//	        	}
//	        }
		}
		
//        sessao.removeAttribute("idRegistroAtualizacao");
        
        return retorno;
        
    }

}