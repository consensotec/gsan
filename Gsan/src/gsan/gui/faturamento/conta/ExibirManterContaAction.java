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
package gsan.gui.faturamento.conta;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.Abrangencia;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterConta");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Inst�ncia do formul�rio que est� sendo utilizado
		ManterContaActionForm manterContaActionForm = (ManterContaActionForm) actionForm;
		String limparForm = httpServletRequest.getParameter("limparForm");

		// DEFINI��O QUE IR� AUXILIAR O RETORNO DOS POPUPS
		sessao.setAttribute("UseCase", "MANTERCONTA");

		// Removendo cole��es da sess�o
		sessao.removeAttribute("contaID");
		if (limparForm != null && !limparForm.equalsIgnoreCase("")) {
			sessao.removeAttribute("colecaoContaImovel");
		}
		
		if (sessao.getAttribute("erroConcorrencia") != null && !sessao.getAttribute("erroConcorrencia").equals("")) {
			sessao.removeAttribute("erroConcorrencia");
			throw new ActionServletException(
                    "atencao.atualizacao.timestamp");
		}
		
		/*
		 * Pesquisar o im�vel a partir da matr�cula do im�vel
		 * ======================================================================
		 */
		String idImovel = manterContaActionForm.getIdImovel();
		String idImovelRequest = httpServletRequest.getParameter("idImovelRequest");

		if ((idImovel != null && !idImovel.equalsIgnoreCase("")) ||
			(idImovelRequest != null && !idImovelRequest.equalsIgnoreCase(""))) {

			/*FiltroImovel filtroImovel = new FiltroImovel();

			// Objetos que ser�o retornados pelo hobernate
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial.codigo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("quadra.numeroQuadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.descricao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.descricao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto.percentual");

			// Realizando a pesquisa do im�vel a partir da matr�cula recebida
			if (idImovel != null && !idImovel.equalsIgnoreCase("")){
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));
			}
			else{
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovelRequest));
			}
			
			/*
        	 * Apenas im�veis que n�o foram exclu�dos poder�o inserir conta
        	 * (IMOV_ICEXCLUSAO = 1)
        	 */
			/*
        	filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, 
        	Imovel.IMOVEL_EXCLUIDO));
        	*/
             /** alterado por pedro alexandre dia 22/11/2006 
             * Recupera o usu�rio logado para passar no met�do de colocar conta em revis�o
             * para verificar se o usu�rio tem abrang�ncia para colocar uma conta em revis�o
             * para o im�vel informado.
             */
            Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
            
            FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.IMOVEL_PERFIL_CORPORATIVO,ConstantesSistema.SIM));
			
			if (idImovel != null && !idImovel.equalsIgnoreCase("")){
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
            }
            else{
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovelRequest));
            }
			
			
			
			// pesquisa a cole�ao de imovel corporativo
			Collection<?> imoveis = fachada.pesquisar(filtroImovel, Imovel.class
					.getName());
			if(imoveis.size() > 0){
				
				boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = 
		    			fachada.verificarPermissaoEspecialAtiva(
		    					PermissaoEspecial.MANTER_IMOVEL_PERFIL_CORPORATIVO,usuarioLogado);
		    		
		    		if(!possuiPermissaoManterClienteResponsavelImoveisPublicos){
		    			throw new ActionServletException(
		    					"atencao.usuario_permissao_corporativo");
		    		}
			}
			
            Collection colecaoImovel = new ArrayList();
			//Collection colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
            //colecaoImovel.add(fachada.pesquisarImovelContaManter(filtroImovel,usuarioLogado));
            
            Imovel imovel = null;
            if (idImovel != null && !idImovel.equalsIgnoreCase("")){
                imovel = fachada.pesquisarImovelRegistroAtendimento(new Integer(idImovel));
            }
            else{
                imovel = fachada.pesquisarImovelRegistroAtendimento(new Integer(idImovelRequest));
            }
            
            colecaoImovel.add(imovel);
            
    		// ------------ CONTROLE DE ABRANGENCIA ----------------
    		Abrangencia abrangencia = new Abrangencia(usuarioLogado, imovel);

    		if (!fachada.verificarAcessoAbrangencia(abrangencia)) {
    			
    			throw new ActionServletException("atencao.acesso.negado.abrangencia");
    		}

    		// ------------ FIM CONTROLE DE ABRANGENCIA ------------
            
            /** fim altera��o ***************************************************************/
            
			// [FS0002] - Verificar exist�ncia da matr�cula do im�vel
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				//throw new ActionServletException("atencao.imovel.inexistente");
				
				httpServletRequest.setAttribute("corInscricao", "exception");
				manterContaActionForm.setIdImovel("");
				manterContaActionForm.setInscricaoImovel("Matr�cula Inexistente");
        		httpServletRequest.setAttribute("nomeCampo", "idImovel");
        		manterContaActionForm.setNomeClienteUsuario("");
        		manterContaActionForm.setSituacaoAguaImovel("");
        		manterContaActionForm.setSituacaoEsgotoImovel("");
        		sessao.removeAttribute("colecaoContaImovel");
			}
			else{

				Imovel objetoImovel = (Imovel) Util
						.retonarObjetoDeColecao(colecaoImovel);
	
				/*
				 * Pesquisar o cliente usu�rio do im�vel selecionado
				 * ======================================================================
				 */
				/*FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
	
				// Objetos que ser�o retornados pelo hibernate.
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente.nome");
				
				
				
				
				
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
						ClienteRelacaoTipo.USUARIO));
				filtroClienteImovel.adicionarParametro(new ParametroNulo(
						FiltroClienteImovel.FIM_RELACAO_MOTIVO));
	
				Collection colecaoClienteImovel = fachada.pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());
	*/
	
				//ClienteImovel objetoClienteImovel = (ClienteImovel) Util
					//	.retonarObjetoDeColecao(colecaoClienteImovel);
				String matriculaImovel = null;
				
				if (idImovel != null && !idImovel.equalsIgnoreCase("")){
					matriculaImovel = idImovel;
				}
				else{
					matriculaImovel = idImovelRequest;
				}
				
				Cliente cliente = fachada.pesquisarClienteUsuarioImovel(new Integer(matriculaImovel));

				// Verifica exist�ncia do cliente usu�rio
				if (cliente == null) {
					throw new ActionServletException("atencao.naocadastrado", null,
							"cliente do tipo usu�rio foi");
				}

				
				/*
				 * O sistema exibe uma lista das contas do im�vel com situa��o atual
				 * normal, retificada ou inclu�da
				 * ======================================================================
				 */
	
				Collection colecaoContaImovel = fachada.obterContasImovelManter(objetoImovel, DebitoCreditoSituacao.NORMAL,
						DebitoCreditoSituacao.INCLUIDA, DebitoCreditoSituacao.RETIFICADA);

				SistemaParametro sistemaParametro = fachada
					.pesquisarParametrosDoSistema();
				
				if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null
						&& sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
					colecaoContaImovel = fachada.obterColecaoSemContasEmContratoParcelamento(
							colecaoContaImovel);
				}
				
				if (httpServletRequest.getParameter("limpaTela")!= null
						&& !httpServletRequest.getParameter("limpaTela").equals("")){
					//qd volta da msg de 
					//O im�vel de matr�cula {} n�o possui nenhuma conta
					manterContaActionForm.setNomeClienteUsuario("");
		    		manterContaActionForm.setSituacaoAguaImovel("");
		    		manterContaActionForm.setSituacaoEsgotoImovel("");
		    		sessao.removeAttribute("colecaoContaImovel");
				}else if ((colecaoContaImovel == null || colecaoContaImovel.isEmpty()) && sessao.getAttribute("cancelar") == null) {
//					 [FS0003] - Verificar exist�ncia de alguma conta
					throw new ActionServletException(
						"atencao.pesquisa.nenhuma.conta_imovel", "exibirManterContaAction.do?limpaTela=1", new Exception(),
							manterContaActionForm.getIdImovel());
				}
	
				// Carregando as informa��es do im�vel no formul�rio de exibi��o.
				if (idImovel == null || idImovel.equalsIgnoreCase("")){
					manterContaActionForm.setIdImovel(idImovelRequest);
				}
				
				manterContaActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());
				manterContaActionForm.setNomeClienteUsuario(cliente.getNome());
				manterContaActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());
				manterContaActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
				
				
				//Ordenando a cole��o por m�s/ano de refer�ncia
	
		        Collections.sort((List) colecaoContaImovel, new Comparator() {
		            public int compare(Object a, Object b) {
		                int retorno = 0;
		                
		            	String anoMesReferencia1 = String.valueOf(((Conta) a).getReferencia());
		                String anoMesReferencia2 = String.valueOf(((Conta) b).getReferencia());
	
		                Integer ano1 = new Integer(anoMesReferencia1.substring(0, 4));
		                Integer ano2 = new Integer(anoMesReferencia2.substring(0, 4));
		                Integer mes1 = new Integer(anoMesReferencia1.substring(4, 6));
		                Integer mes2 = new Integer(anoMesReferencia2.substring(4, 6));
		                
		                if (ano1 > ano2){
		                	retorno = 1;
		                }
		                else if (ano1 < ano2){
		                	retorno = -1;
		                }
		                else if (mes1 > mes2){
		                	retorno = 1;
		                }
		                else if (mes1 < mes2){
		                	retorno = -1;
		                }
		                
		                return retorno;
		            }
		        });
                
                // Coloca na sess�o a cole��o com as contas do im�vel selecionado
				sessao.setAttribute("colecaoContaImovel", colecaoContaImovel);
				
				if (idImovel != null && !idImovel.equalsIgnoreCase("")){
					sessao.setAttribute("imovel",idImovel);
					verificarSeContaRA(new Integer(idImovel),httpServletRequest,sessao,usuarioLogado);
				}
				else{
					sessao.setAttribute("imovel",idImovelRequest);
					verificarSeContaRA(new Integer(idImovelRequest),httpServletRequest,sessao,usuarioLogado);
				}
				
			}
		}
		
		/*
		 * Colocado por Raphael Rossiter em 03/11/2008
		 * Permite retificar um conjunto de contas a partir do manter conta.
		 */
		if (fachada.verificarPermissaoRetificarConjuntoConta((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
			httpServletRequest.setAttribute("retificarConjuntoConta", "OK");
		}
        
        /* Colocado por Bruno Barros em 05 de Janeiro de 2009
         * Verificamos se o usu�rio possue permiss�o especial para atualizar 
         * ou retificar contas pagas
         */
        
        boolean usuarioPodeAtualizarRetificarContasPagas =
            fachada.verificarPermissaoEspecial( 
                    PermissaoEspecial.ATUALIZAR_RETIFICAR_CONTAS_PAGAS, 
                    ( Usuario ) sessao.getAttribute(Usuario.USUARIO_LOGADO ) );
        
        httpServletRequest.setAttribute("usuarioPodeAtualizarRetificarContasPagas",
                    usuarioPodeAtualizarRetificarContasPagas );
        
        // *****************************************************************
		
		sessao.removeAttribute("cancelar");
		
		
		manterContaActionForm.setIdRA("1");
		
		return retorno;
	}
	
	/*[FS0040] � Verificar se a conta consta no Registro de Atendimento
	 * Vivianne Sousa - 09/02/2011
	 */
	public void verificarSeContaRA(Integer idImovel,
			HttpServletRequest httpServletRequest,HttpSession sessao,Usuario usuarioLogado){
		
		String habilitaRetificacaoContaRA = "2";
		Integer idRegistroAtendimento = null;
		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			//Caso a Empresa esteja na Norma de Retifica��o de Conta 
			
			//Caso o usu�rio possua permiss�o especial habilitar esta conta para retifica��o
			boolean temPermissaoParaRetificarContaNorma = 
				getFachada().verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
			
			if(temPermissaoParaRetificarContaNorma){
				habilitaRetificacaoContaRA = "1";
			}else{
				idRegistroAtendimento = getFachada().verificaSolicitacaoTipoEspecificacaoRA(idImovel);
				
				if(idRegistroAtendimento != null){
					habilitaRetificacaoContaRA = "3";
					//Caso exista a conta informada no Registro de Atendimento Conta 
					//(REGISTRO_ATENDIMENTO_CONTA com RGAT_ID = RGAT_ID do REGISTRO_ATENDIMENTO
					//e CNTA_ID = CNTA_ID da conta a ser retificada), habilitar esta conta para retifica��o
				}
			}			
		}else{
			habilitaRetificacaoContaRA = "1";
		}
		
		httpServletRequest.setAttribute("habilitaRetificacaoContaRA",habilitaRetificacaoContaRA);
		if(idRegistroAtendimento != null){
			httpServletRequest.setAttribute("idRA",idRegistroAtendimento.toString());
		}
		
		
	}

}
