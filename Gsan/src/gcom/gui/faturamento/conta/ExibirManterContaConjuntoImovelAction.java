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
package gcom.gui.faturamento.conta;


import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * [UC0407] Filtrar Im�veis para Inserir ou Manter Conta
 * 
 * @author Ana Maria
 * @created 16/03/2007
 */
public class ExibirManterContaConjuntoImovelAction extends GcomAction {
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("manterContaConjuntoImovel");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ManterContaConjuntoImovelActionForm manterContaConjuntoImovelActionForm = (ManterContaConjuntoImovelActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoImovel = new ArrayList();
		
		Imovel imovel = new Imovel();
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		if(manterContaConjuntoImovelActionForm.getIndicadorContaPaga() == null){
			manterContaConjuntoImovelActionForm.setIndicadorContaPaga("2");
		}
		
		if(sessao.getAttribute("colecaoImovel") != null){
			colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");		
			
			this.verificarPermissaoImovelGrandeCorporativo(colecaoImovel, usuarioLogado);
		}		
		
		manterContaConjuntoImovelActionForm.setQuatidadeImovel(Integer.toString(colecaoImovel.size()));
		
		Date dataVencimentoContaInicio = null;
		Date dataVencimentoContaFim = null;
		
		if (manterContaConjuntoImovelActionForm.getDataVencimentoInicial() != null &&
			!manterContaConjuntoImovelActionForm.getDataVencimentoInicial().equals("")){
			
			dataVencimentoContaInicio = Util.converteStringParaDate(manterContaConjuntoImovelActionForm.getDataVencimentoInicial());
		}
		
		if (manterContaConjuntoImovelActionForm.getDataVencimentoFinal() != null &&
			!manterContaConjuntoImovelActionForm.getDataVencimentoFinal().equals("")){
			
			dataVencimentoContaFim = Util.converteStringParaDate(manterContaConjuntoImovelActionForm.getDataVencimentoFinal());
		}
		
		//Cliente Superior
		if(sessao.getAttribute("codigoClienteSuperior") != null && sessao.getAttribute("nomeCliente") != null){
			manterContaConjuntoImovelActionForm.setCodigoClienteSuperior((String)sessao.getAttribute("codigoClienteSuperior"));	
			manterContaConjuntoImovelActionForm.setNomeCliente((String)sessao.getAttribute("nomeCliente"));
			httpServletRequest.setAttribute("cliente", "ok");
			httpServletRequest.setAttribute("superior", "ok");			
		}
		//Cliente Im�vel
		if(sessao.getAttribute("codigoCliente") != null && sessao.getAttribute("nomeCliente") != null){
			manterContaConjuntoImovelActionForm.setCodigoCliente( (String) sessao.getAttribute("codigoCliente"));	
			manterContaConjuntoImovelActionForm.setNomeCliente((String)sessao.getAttribute("nomeCliente"));
			httpServletRequest.setAttribute("cliente", "ok");
		}
		
		//Inscri��o Im�vel
		if(sessao.getAttribute("inscricaoInicialImovel") != null){
			manterContaConjuntoImovelActionForm.setInscricaoInicial((String)sessao.getAttribute("inscricaoInicialImovel"));
		}
		else{
			manterContaConjuntoImovelActionForm.setInscricaoInicial("");
		}
		
		if(sessao.getAttribute("inscricaoDestinoImovel") != null){
			manterContaConjuntoImovelActionForm.setInscricaoFinal((String)sessao.getAttribute("inscricaoDestinoImovel"));			
		}
		else{
			manterContaConjuntoImovelActionForm.setInscricaoFinal("");
		}
		
		
		String idGrupoFaturamentoSessao = (String) sessao.getAttribute("grupoFaturamento"); 
		Integer idGrupoFaturamento = null;
		
		if (idGrupoFaturamentoSessao != null && !idGrupoFaturamentoSessao.equals("")){
			manterContaConjuntoImovelActionForm.setIdGrupoFaturamento(idGrupoFaturamentoSessao);
			idGrupoFaturamento = new Integer(idGrupoFaturamentoSessao);
		}
		else{
			manterContaConjuntoImovelActionForm.setIdGrupoFaturamento("");
		}
		
		Integer quantidadeConta = 0;
		Collection colecaoConta = null;
		Integer quantidadeContaRevisao = 0;
		Integer codigoCliente = null;
		if(sessao.getAttribute("codigoCliente") != null){
			codigoCliente = new Integer((String)sessao.getAttribute("codigoCliente"));
		}
		
		Short relacaoTipo = null;
		if(sessao.getAttribute("relacaoTipo") != null){
		   relacaoTipo = ((Integer)sessao.getAttribute("relacaoTipo")).shortValue();
		}
		
		if(httpServletRequest.getParameter("quantidadeConta") != null){
		  
			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(manterContaConjuntoImovelActionForm.getMesAnoConta());
			Integer anoMesFinal = Util.formatarMesAnoComBarraParaAnoMes(manterContaConjuntoImovelActionForm.getMesAnoContaFinal());
			String indicadorContaPaga = manterContaConjuntoImovelActionForm.getIndicadorContaPaga(); 
			
			if(sessao.getAttribute("bancos") != null){
				String[] bancos = (String[])sessao.getAttribute("bancos");
				quantidadeConta = fachada.countImoveisBancoDebitoAutomatico(bancos, anoMes,
						anoMesFinal, dataVencimentoContaInicio, dataVencimentoContaFim, indicadorContaPaga);
				
				//Collection imoveis = fachada.selecionarImoveisBancoDebitoAutomatico(bancos, anoMes,
					//	anoMesFinal, dataVencimentoContaInicio, dataVencimentoContaFim, indicadorContaPaga);
				
			}else{
				quantidadeConta = fachada.obterContasConjuntoImoveis(anoMes, colecaoImovel, codigoCliente, relacaoTipo,
						  dataVencimentoContaInicio, dataVencimentoContaFim, idGrupoFaturamento, anoMesFinal, indicadorContaPaga);	
				  
				quantidadeContaRevisao = fachada.obterContasRevisaoConjuntoImoveis(anoMes, colecaoImovel, codigoCliente, relacaoTipo,
							  dataVencimentoContaInicio, dataVencimentoContaFim, idGrupoFaturamento, anoMesFinal, indicadorContaPaga);
				
				colecaoConta = fachada.pesquisarContasImoveis(anoMes, colecaoImovel, dataVencimentoContaInicio,dataVencimentoContaFim, 
					anoMesFinal, indicadorContaPaga);
				
				
			}
			
			if(!quantidadeConta.equals(quantidadeContaRevisao)){
				sessao.setAttribute("contaRevisao", "contaRevisao");
			}else{
				sessao.removeAttribute("contaRevisao");
			}
			
		  manterContaConjuntoImovelActionForm.setQuatidadeConta(quantidadeConta.toString());
		}else if(httpServletRequest.getParameter("cancelar") == null){
			manterContaConjuntoImovelActionForm.setMesAnoConta("");
			manterContaConjuntoImovelActionForm.setMesAnoContaFinal("");
			manterContaConjuntoImovelActionForm.setDataVencimentoInicial("");
			manterContaConjuntoImovelActionForm.setDataVencimentoFinal("");
			manterContaConjuntoImovelActionForm.setQuatidadeConta("");
		}
		
		//Seleciona a quantidade de contas depois do cancelamento
		if(httpServletRequest.getParameter("cancelar") != null){
			httpServletRequest.setAttribute("reloadPage", "ok");
			httpServletRequest.setAttribute("mensagemSuceso","Cancelar conjunto de contas encaminhado para processamento.");			
		}
		
		//Fechar popup de retirar d�bito cobrado
		if(httpServletRequest.getParameter("fecharPopup") != null){
			sessao.removeAttribute("debitosTipoRetirar");
			sessao.removeAttribute("RetirarDebitoCobradoActionForm");
			
		}	
		
		if(httpServletRequest.getParameter("mensagemSuceso") != null){
			httpServletRequest.setAttribute("mensagemSuceso","Alterar vencimento conjunto de contas encaminhado para processamento.");
		}
		
		if(httpServletRequest.getParameter("mensagemSucesoRetificarConjuntoConta") != null){
			httpServletRequest.setAttribute("mensagemSuceso","Retifica��o de um conjunto de contas foi para batch.");
		}
        
        /* Colocado por Bruno Barros em 05 de Janeiro de 2009
         * Verificamos se o usu�rio possue permiss�o especial para atualizar 
         * ou retificar contas pagas
         */
        
        boolean usuarioPodeAtualizarRetificarContasPagas =
            fachada.verificarPermissaoEspecial( 
                    PermissaoEspecial.ATUALIZAR_RETIFICAR_CONTAS_PAGAS, 
                    ( Usuario ) sessao.getAttribute(Usuario.USUARIO_LOGADO ) );
        
        httpServletRequest.
            setAttribute(
                    "usuarioPodeAtualizarRetificarContasPagas",
                    usuarioPodeAtualizarRetificarContasPagas );
        
        // *****************************************************************       
        
        sessao.setAttribute("colecaoConta", colecaoConta);
		
		return retorno;
	}
	
	
	 /**
     * 4. Caso Algum im�vel selecionado seja im�vel Corporativo ou Grande Ciente.
     *    vertificar Permissao Especial.
     * 
     * @author Diogo Luiz
     * @date 09/06/2015
     * 
     * @param colecaoImovel
     * @param usuarioLogado
     */
    private void verificarPermissaoImovelGrandeCorporativo(Collection colecaoImovel, Usuario usuarioLogado){    	
    	
    	Collection colecaoPermissao = new ArrayList();
    	Iterator it = colecaoImovel.iterator();
    	
    	while(it.hasNext()){
    		
    		Integer idImovel = (Integer) it.next();
    		
    		FiltroImovel filtro = new FiltroImovel();
    		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
    		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);
    		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL_PERMISSAO);
    		Collection colImovel = Fachada.getInstancia().pesquisar(filtro, Imovel.class.getName());
    		
    		if(!Util.isVazioOrNulo(colImovel)){
    			
    			Imovel imovelPermissao = (Imovel) colImovel.iterator().next();
    			ImovelPerfil imovelPerfil = imovelPermissao.getImovelPerfil();
    		
    			if(imovelPerfil.getIndicadorCorporativo().equals(ConstantesSistema.SIM) 
    					|| imovelPerfil.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM)){
    						
					if(!Fachada.getInstancia().verificarPermissaoGrandeCorporativoCliente(imovelPerfil, usuarioLogado)){
						colecaoPermissao.add(idImovel);
					}				
				}    			
    		}		
    	}
    	
    	Iterator itPermissao = colecaoPermissao.iterator();
    	
    	while(itPermissao.hasNext()){
    		Integer idImovel = (Integer) itPermissao.next();
    		
    		colecaoImovel.remove(idImovel);
    	}
    }
}