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
package gcom.gui.atendimentopublico;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Descri��o da classe
 * @author Daniel Alves
 * @date 27/07/2010
 */
public class ExibirAtualizarDadosClientesPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,		ActionForm actionForm, HttpServletRequest httpServletRequest,		HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosClientes");		
		HttpSession sessao = getSessao(httpServletRequest);
		
		ExibirAtualizarDadosClientesPopupActionForm exibirAtualizarDadosClientesPopupActionForm = 
			(ExibirAtualizarDadosClientesPopupActionForm) actionForm;
		
		//fun��o para fechar o popup quando for selecionada a fun��o atualizar cliente/imovel.
		String fecharPopup = (String)httpServletRequest.getParameter("fecharPopup");
		if(fecharPopup != null && !fecharPopup.equals("")){
			exibirAtualizarDadosClientesPopupActionForm.setFecharPopup(fecharPopup);
		}else{
			exibirAtualizarDadosClientesPopupActionForm.setFecharPopup(null);
		}
		
		String idImovel = (String)httpServletRequest.getParameter("atualizaImovel");
		
		if( idImovel!= null && !idImovel.equals("") ){
			
			exibirAtualizarDadosClientesPopupActionForm.setMatricula(idImovel);
			
			Imovel imovel = this.getFachada().consultarImovelDadosCadastrais(new Integer(idImovel));
			
			if(imovel != null){
				
				String inscricao = this.getFachada().pesquisarInscricaoImovel(imovel.getId());
				String endereco = this.getFachada().pesquisarEndereco(imovel.getId());
				
				exibirAtualizarDadosClientesPopupActionForm.setInscricao(inscricao);
				exibirAtualizarDadosClientesPopupActionForm.setEndereco(endereco);
				
				/*if(imovel.getNomeImovel() != null && !imovel.getNomeImovel().equals("")){
					exibirAtualizarDadosClientesPopupActionForm.setNomeConta(imovel.getNomeImovel());
				}else{
					exibirAtualizarDadosClientesPopupActionForm.setNomeConta(null);
				}*/
				
				//SubCategorias
				Collection colecaoSubcategorias = 
					this.getFachada().pesquisarCategoriasImovel(imovel.getId());
				
				if(colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()){					
					
					exibirAtualizarDadosClientesPopupActionForm.setCategoriaSubcategoriaEconomia(colecaoSubcategorias);	
					
					//trecho que soma o total de economias
					Iterator iterator = colecaoSubcategorias.iterator();
					int contadorEconomias = 0;
					
					while(iterator.hasNext()){
						ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
						contadorEconomias += imovelSubcategoria.getQuantidadeEconomias();
					}
					
					exibirAtualizarDadosClientesPopupActionForm.setTotalEconomias(String.valueOf(contadorEconomias));
				}
				
				//ClienteImovel				
				Collection colecaoClienteImovel = this.montarColecaoClienteImovel(imovel.getId(),httpServletRequest,exibirAtualizarDadosClientesPopupActionForm);
				exibirAtualizarDadosClientesPopupActionForm.setColecaoClienteImovel(colecaoClienteImovel);
			}
		}
		
		String chaveRemoverFone = (String)httpServletRequest.getParameter("removerFone");
		
		if(chaveRemoverFone != null && !chaveRemoverFone.equals("")){
			this.removerClienteFone(exibirAtualizarDadosClientesPopupActionForm,
				httpServletRequest,
				chaveRemoverFone);
			
			if(httpServletRequest.getParameter("nomeDigitado")!=null 
	    			&& httpServletRequest.getParameter("cpfCnpj")!=null){
	    		
				httpServletRequest.setAttribute("nomeDigitado", httpServletRequest.getParameter("nomeDigitado"));
				httpServletRequest.setAttribute("cpfCnpj",  httpServletRequest.getParameter("cpfCnpj"));
	    		
	    	}
			
			sessao.setAttribute("mudouTelefonePadrao", true);
		}
		
		//Adiciona na cole��o Cliente Imovel adicionado pelo pop up
		if(sessao.getAttribute("clienteFoneAdicionar")!=null){
			ClienteFone clienteFone = 
				(ClienteFone) sessao.getAttribute("clienteFoneAdicionar");
			
			this.adicionarTelefone(exibirAtualizarDadosClientesPopupActionForm,clienteFone);
			
			sessao.removeAttribute("clienteFoneAdicionar");
			
			sessao.setAttribute("mudouTelefonePadrao", true);
			
			
			if(sessao.getAttribute("clienteImovel")!=null
					&& sessao.getAttribute("nomeDigitado")!=null
					&& sessao.getAttribute("cpfCnpj")!=null){
				
				/*String idClienteImovel = (String) sessao.getAttribute("clienteImovel");
				
				for (ClienteImovel clienteImovel : 
					exibirAtualizarDadosClientesPopupActionForm.getColecaoClienteImovel()) {
					
					if(clienteImovel.getId().toString().equals(idClienteImovel)){
						if(clienteImovel.getCliente().getClienteTipo()
							.getIndicadorPessoaFisicaJuridica().compareTo(ConstantesSistema.SIM)==0){
							clienteImovel.getCliente().setCpf((String) sessao.getAttribute("cpfCnpj"));
						}else{
							clienteImovel.getCliente().setCnpj((String)sessao.getAttribute("cpfCnpj"));
						}
						clienteImovel.getCliente().setNome((String)sessao.getAttribute("nomeDigitado"));
					}
				}*/
				httpServletRequest.setAttribute("nomeDigitado", sessao.getAttribute("nomeDigitado"));
				httpServletRequest.setAttribute("cpfCnpj", sessao.getAttribute("cpfCnpj"));
				httpServletRequest.setAttribute("clienteImovel", sessao.getAttribute("clienteImovel"));
			}
		}
		
		//Mudar fone padr�o da cole��o temporaria
 		if(httpServletRequest.getParameter("fonePadrao")!=null && httpServletRequest.getParameter("idCliente")!=null){
 			
 			String idCliente = httpServletRequest.getParameter("idCliente");
 			String idFone = httpServletRequest.getParameter("fonePadrao");
 			
 			Collection colecaoClienteImovel = exibirAtualizarDadosClientesPopupActionForm.getColecaoClienteImovel();
 			
 			if(colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
 				Iterator itera = colecaoClienteImovel.iterator();
 				while (itera.hasNext()) {
 					ClienteImovel clienteImovel = (ClienteImovel) itera.next();
 					if(idCliente.equals(""+clienteImovel.getId())){

 						Collection colecaoClienteFone = clienteImovel.getCliente().getClienteFones();
							
 							Iterator iteraFone = colecaoClienteFone.iterator();
 							while (iteraFone.hasNext()) {
 								
 								ClienteFone clienteFone = (ClienteFone) iteraFone.next();
 								
 								if(clienteFone.getId().toString().equals(idFone)){
 									clienteFone.setIndicadorTelefonePadrao(ConstantesSistema.SIM);
 								}else{
 									clienteFone.setIndicadorTelefonePadrao(ConstantesSistema.NAO);
 								}
 							}
 					}
 				}
 			}
 			sessao.setAttribute("mudouTelefonePadrao", true);
 		}
		
 		if(httpServletRequest.getAttribute("cliente")==null && exibirAtualizarDadosClientesPopupActionForm.getCodClienteImovel()!=null){
 			httpServletRequest.setAttribute("cliente", exibirAtualizarDadosClientesPopupActionForm.getCodClienteImovel());
 		}		
		return retorno;	}
		private void adicionarTelefone(
			ExibirAtualizarDadosClientesPopupActionForm exibirAtualizarDadosClientesPopupActionForm,
			ClienteFone clienteFone) {
		
		for (ClienteImovel clienteImovel : exibirAtualizarDadosClientesPopupActionForm.getColecaoClienteImovel()) {
			
			if(clienteImovel.getCliente().getId().compareTo(clienteFone.getCliente().getId())==0){
				
				if(clienteImovel.getCliente().getClienteFones()==null){
					clienteImovel.getCliente().setClienteFones(new HashSet());
				}
				
				if(clienteFone.getIndicadorTelefonePadrao().compareTo(ConstantesSistema.SIM)==0){
					
					Collection<ClienteFone> colecaoFones = clienteImovel.getCliente().getClienteFones();
					
					for (ClienteFone clienteFoneRemoverIndicador: colecaoFones) {
						clienteFoneRemoverIndicador.setIndicadorTelefonePadrao(ConstantesSistema.NAO);
					}
				}
				
				clienteImovel.getCliente().getClienteFones().add(clienteFone);
			}
			
		}
		
	}

	private void removerClienteFone(ExibirAtualizarDadosClientesPopupActionForm form,HttpServletRequest httpServletRequest,String chaveRemoverFone){
		
		String codigoClienteRemover = (String)httpServletRequest.getParameter("codigoClienteRemover");
		
		Collection colecaoClienteImovel = form.getColecaoClienteImovel();
		
		if(colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
			Iterator itera = colecaoClienteImovel.iterator();
			while (itera.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) itera.next();
				if(codigoClienteRemover.equals(""+clienteImovel.getId())){

					Collection colecaoClienteFone = clienteImovel.getCliente().getClienteFones();
					if(colecaoClienteFone != null && !colecaoClienteFone.isEmpty()){
						int count = 1;
						
						Iterator iteraFone = colecaoClienteFone.iterator();
						while (iteraFone.hasNext()) {
							
							ClienteFone clienteFone = (ClienteFone) iteraFone.next();
							
							if(chaveRemoverFone.equals(""+count)){
								colecaoClienteFone.remove(clienteFone);
								break;
							}else{
								count++;
							}
						}
					}
				}
			}
		}
		
		
	}
	
	private Collection montarColecaoClienteImovel(Integer idImovel,HttpServletRequest httpServletRequest, ExibirAtualizarDadosClientesPopupActionForm exibirAtualizarDadosClientesPopupActionForm){
		
		HashMap colecaoClienteImovelNova = new HashMap();
		
		/*Collection colecaoClienteImovel = 
			this.getFachada().pesquisarClientesImovel(idImovel);*/
		
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO));
		
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_TIPO);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
		//filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_FONE);
		
		filtroClienteImovel.setCampoOrderBy(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_DESCRICAO + " DESC");
		
		Collection colecaoClienteImovel = this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		
		if(colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
			Iterator iteratorClienteImovel = colecaoClienteImovel.iterator();
			
			while (iteratorClienteImovel.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) iteratorClienteImovel.next();
				
				Integer idCliente = clienteImovel.getCliente().getId();
				
				if(exibirAtualizarDadosClientesPopupActionForm.getCodClienteImovel()!=null){			
					httpServletRequest.setAttribute("cliente", exibirAtualizarDadosClientesPopupActionForm.getCodClienteImovel());			
				}else{
					if(clienteImovel.getClienteRelacaoTipo().getId().toString().equals(ClienteRelacaoTipo.USUARIO.toString())){
						httpServletRequest.setAttribute("cliente", clienteImovel.getId());
					}
				}
				
				if(!colecaoClienteImovelNova.containsKey(idCliente)){
					
					//Cliente cliente = this.getFachada().consultarCliente(idCliente);
					
					Collection colecaoClienteFone = this.getFachada().pesquisarClienteFone(idCliente);
					
					//cliente.setId(idCliente);
					if(!Util.isVazioOrNulo(colecaoClienteFone)){
						clienteImovel.getCliente().setClienteFones(new HashSet(colecaoClienteFone));
					}else{
						clienteImovel.getCliente().setClienteFones(null);
					}
					//clienteImovel.setCliente(cliente);
					
					colecaoClienteImovelNova.put(idCliente,clienteImovel);	
				}
			}
		}
		
		return colecaoClienteImovelNova.values();
	}
	
}
