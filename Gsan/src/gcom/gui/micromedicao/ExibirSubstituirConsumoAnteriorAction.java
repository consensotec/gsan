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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirSubstituirConsumoAnteriorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("substituirConsumoAnterior");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;

		String codigoImovel = httpServletRequest.getParameter("idImovel");

		sessao.removeAttribute("colecaoConsumoHistorico");
		
		if (codigoImovel != null && !codigoImovel.trim().equalsIgnoreCase("")) {
			try {
				String enderecoFormatadoImovel = fachada.pesquisarEnderecoFormatado(new Integer(codigoImovel));
			

				httpServletRequest.setAttribute("enderecoImovel",
							enderecoFormatadoImovel);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
		
		if(httpServletRequest.getParameter("peloMenu") != null){
			sessao.setAttribute("peloMenu", true);
		}

		
		if(codigoImovel != null || leituraConsumoActionForm.getIdImovelSubstituirConsumo() != null){
			//FiltroConsumoHistorico filtroConsumoHistoricoAgua = new FiltroConsumoHistorico();
			//FiltroConsumoHistorico filtroConsumoHistoricoEsgoto = new FiltroConsumoHistorico();
	
			//Collection colecaoConsumoHistorico = new ArrayList();
			
	//		ImovelMicromedicao imovelMicromedicao = new ImovelMicromedicao();
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
			
//			Imovel imovel = new Imovel();
			if(codigoImovel != null){
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));
//				imovel.setId(new Integer(codigoImovel));
			}else if(httpServletRequest.getParameter("tipoConsulta") != null){
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, leituraConsumoActionForm.getIdImovelSubstituirConsumo()));
//				imovel.setId(new Integer(leituraConsumoActionForm.getIdImovelSubstituirConsumo()));
			}
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			
			if (imovel != null){
				
				try {
					String endereceoFormatado = fachada.pesquisarEnderecoFormatado(imovel.getId());
//					String inscricaoFormatada = fachada.pesquisarInscricaoImovel(imovel.getId());
					String inscricaoFormatada = imovel.getInscricaoFormatada();
					Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovel(imovel.getId());
					
					leituraConsumoActionForm.setNomeUsuario(clienteUsuario.getNome());
					leituraConsumoActionForm.setLigacaoAguaSituacao(imovel.getLigacaoAguaSituacao().getDescricao());
					leituraConsumoActionForm.setLigacaoEsgotoSituacao(imovel.getLigacaoEsgotoSituacao().getDescricao());
					
					if(endereceoFormatado == null || endereceoFormatado.trim().equals("")){
						leituraConsumoActionForm.setInscricaoImovel("Matr�cula inexistente.");
						leituraConsumoActionForm.setIdImovelSubstituirConsumo(null);
						httpServletRequest.setAttribute("corTexto", "#ff0000");
					}else{
//						leituraConsumoActionForm.setEnderecoFormatado(endereceoFormatado);
						httpServletRequest.setAttribute("enderecoImovel", endereceoFormatado);
						leituraConsumoActionForm.setInscricaoImovel(inscricaoFormatada);
						httpServletRequest.setAttribute("corTexto", "#000000");
						sessao.setAttribute("idImovelSelecionado", imovel.getId());
					
							Collection colecaoGeral = fachada.pesquisaConsumoHistoricoSubstituirConsumo(imovel.getId());
							
							if(colecaoGeral != null && !colecaoGeral.isEmpty()){
								
								// joga as colecoes na sessao
								sessao.setAttribute("colecaoConsumoHistorico",
										colecaoGeral);
					//			ConsumoHistorico consumoHistorico = null;
					//			ConsumoHistorico consumoHistoricoJuncao = null;
					//			Iterator iteratorConsumoHistorico = colecaoGeral.iterator();
					//			Iterator iteratorConsumoHistoricoJuncao = null;
					//			while(iteratorConsumoHistorico.hasNext()){
					//				ImovelMicromedicao imovelMicromedicao = new ImovelMicromedicao();
					//				imovelMicromedicao.setImovel(imovel);
					//				//
					//				iteratorConsumoHistoricoJuncao = colecaoGeral.iterator();
					//
					//				consumoHistorico = (ConsumoHistorico)iteratorConsumoHistorico.next();
					//				if(consumoHistorico.getLigacaoTipo() != null && consumoHistorico.getLigacaoTipo().getId().equals(LigacaoTipo.LIGACAO_AGUA)){
					//					imovelMicromedicao.setConsumoHistorico(consumoHistorico);
					//					colecaoConsumoHistorico.add(imovelMicromedicao);
					//				}else if(consumoHistorico.getLigacaoTipo() != null && consumoHistorico.getLigacaoTipo().getId().equals(LigacaoTipo.LIGACAO_ESGOTO)){
					//					imovelMicromedicao.setConsumoHistoricoEsgoto(consumoHistorico);
					//					colecaoConsumoHistorico.add(imovelMicromedicao);
					//				}
					//				while(iteratorConsumoHistoricoJuncao.hasNext()){
					//					consumoHistoricoJuncao = (ConsumoHistorico)iteratorConsumoHistoricoJuncao.next();
					//					
					//					if(consumoHistoricoJuncao.getMesAno().equals(consumoHistorico.getMesAno()) 
					//							&& !consumoHistorico.getLigacaoTipo().getId().equals(consumoHistoricoJuncao.getLigacaoTipo().getId())){
					//						
					//						if(consumoHistoricoJuncao.getLigacaoTipo() != null && consumoHistoricoJuncao.getLigacaoTipo().getId().equals(LigacaoTipo.LIGACAO_AGUA)){
					//							imovelMicromedicao.setConsumoHistorico(consumoHistoricoJuncao);
					//							colecaoConsumoHistorico.add(imovelMicromedicao);
					//							iteratorConsumoHistorico.remove();
					//							break;
					//						}else if(consumoHistoricoJuncao.getLigacaoTipo() != null && consumoHistoricoJuncao.getLigacaoTipo().getId().equals(LigacaoTipo.LIGACAO_ESGOTO)){
					//							imovelMicromedicao.setConsumoHistoricoEsgoto(consumoHistoricoJuncao);
					//							colecaoConsumoHistorico.add(imovelMicromedicao);
					//							iteratorConsumoHistorico.remove();
					//							break;
					//						}
					//						
					//					}
					//				}
					//			}
							}else{
								throw new ActionServletException("atencao.nenhum_consumo_substituir");
							}
					}
				} catch (ControladorException e) {
					e.printStackTrace();
				}
				
		//		imovelMicromedicao.setImovel(imovel);
			}
			else{
				
				leituraConsumoActionForm.setInscricaoImovel("Matr�cula inexistente.");
				leituraConsumoActionForm.setIdImovelSubstituirConsumo(null);
				httpServletRequest.setAttribute("corTexto", "#ff0000");
			}
		}
				
		httpServletRequest.setAttribute("nomeCampo","idImovelSubstituirConsumo");
		// devolve o mapeamento de retorno
		return retorno;
	}
}
