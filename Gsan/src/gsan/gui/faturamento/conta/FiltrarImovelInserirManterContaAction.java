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
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAbrangencia;
import gsan.util.ConstantesSistema;
import gsan.util.ErroRepositorioException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelInserirManterContaAction extends GcomAction {

	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("exibirManterContaConjuntoImovel");

        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        FiltrarImovelContaActionForm filtrarImovelContaActionForm = (FiltrarImovelContaActionForm) actionForm;
        
        Collection colecaoImovel = null;
        
        Integer codigoCliente = null;
        Integer codigoClienteSuperior = null;        
    	String nomeCliente = "";
		Integer relacaoTipo = null;
		String[] bancos = null;
		
		if(filtrarImovelContaActionForm.getBanco() != null && !filtrarImovelContaActionForm.getBanco().equals("-1")){
			bancos = filtrarImovelContaActionForm.getBanco();
			sessao.setAttribute("bancos", bancos);
		}else{
			sessao.removeAttribute("bancos");
		}
		
		Boolean verificarImovelPerfilBloqueado = false;
		
        if((filtrarImovelContaActionForm.getCodigoClienteSuperior() != null && !filtrarImovelContaActionForm.getCodigoClienteSuperior().equals(""))||
        		 (filtrarImovelContaActionForm.getCodigoCliente() != null && !filtrarImovelContaActionForm.getCodigoCliente().equals(""))){
        	
        	if(filtrarImovelContaActionForm.getCodigoClienteSuperior() != null && !filtrarImovelContaActionForm.getCodigoClienteSuperior().equals("")){
        		codigoClienteSuperior = Integer.parseInt(filtrarImovelContaActionForm.getCodigoClienteSuperior());
           	   relacaoTipo = new Integer("99");
        	}else{
        	   codigoCliente = Integer.parseInt(filtrarImovelContaActionForm.getCodigoCliente());	
	       		if(filtrarImovelContaActionForm.getTipoRelacao() != null && !filtrarImovelContaActionForm.getTipoRelacao().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
	    			relacaoTipo = Integer.parseInt(filtrarImovelContaActionForm.getTipoRelacao());
	    		}
        	}
        	
    		// PESQUISAR CLIENTE SUPERIOR
    		if (codigoClienteSuperior != null && !codigoClienteSuperior.equals("")) {
                sessao.setAttribute("codigoClienteSuperior", codigoClienteSuperior.toString());
                sessao.removeAttribute("codigoCliente");
    			nomeCliente = this.pesquisarCliente(codigoClienteSuperior.toString(), fachada);

				/*
				 * Colocado por Ana Maria em 20/04/2009				
				 */
				if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
					Integer qtdImovelBloqueado = fachada.pesquisarColecaoImovelClienteBloqueadoPerfil(codigoClienteSuperior, relacaoTipo);
					if(qtdImovelBloqueado != null && !qtdImovelBloqueado.equals(0)){						
						String confirmado = httpServletRequest.getParameter("confirmado");
						if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
							httpServletRequest.setAttribute("caminhoActionConclusao",
									"/gsan/filtrarImovelInserirManterContaAction.do");
							return montarPaginaConfirmacao(
									"atencao.existe_imovel_perfil_bloqueado",
									httpServletRequest, actionMapping, qtdImovelBloqueado.toString());
						}else{
							verificarImovelPerfilBloqueado = true;
						}
					}
				}
        		colecaoImovel = fachada.pesquisarColecaoImovelCliente(codigoClienteSuperior, relacaoTipo, verificarImovelPerfilBloqueado);
    		}
    		
    		// PESQUISAR CLIENTE 
    		if (codigoCliente != null && !codigoCliente.equals("")) {
                sessao.setAttribute("codigoCliente", codigoCliente.toString());
                sessao.removeAttribute("codigoClienteSuperior");                
    			nomeCliente = this.pesquisarCliente(codigoCliente.toString(), fachada);
				/*
				 * Colocado por Ana Maria em 20/04/2009				
				 */
				if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
					Integer qtdImovelBloqueado = fachada.pesquisarColecaoImovelClienteBloqueadoPerfil(codigoCliente, relacaoTipo);
					if(!qtdImovelBloqueado.equals(0)){						
						String confirmado = httpServletRequest.getParameter("confirmado");
						if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
							httpServletRequest.setAttribute("caminhoActionConclusao",
									"/gsan/filtrarImovelInserirManterContaAction.do");
							return montarPaginaConfirmacao(
									"atencao.existe_imovel_perfil_bloqueado",
									httpServletRequest, actionMapping, qtdImovelBloqueado.toString());
						}else{
							verificarImovelPerfilBloqueado = true;
						}
					}
				}
        		colecaoImovel = fachada.pesquisarColecaoImovelCliente(codigoCliente, relacaoTipo, verificarImovelPerfilBloqueado);
    		}

            if (colecaoImovel == null || colecaoImovel.isEmpty()) {
                throw new ActionServletException(
                        "atencao.imovel_inexistente_criterio_pesquisa");
            }else{
            	sessao.setAttribute("colecaoImovel", colecaoImovel);
            }
            
            sessao.setAttribute("nomeCliente", nomeCliente);  
            sessao.setAttribute("relacaoTipo",relacaoTipo);
            sessao.removeAttribute("inscricaoInicialImovel");
            sessao.removeAttribute("inscricaoDestinoImovel");
            
            if(sessao.getAttribute("quadraSelecionada") != null){
            	sessao.removeAttribute("quadraSelecionada");
            }
            
        }else{        
      
        Imovel imovelInscricaoOrigem = new Imovel();

        //Dados da inscri��o de origem
        Integer localidadeOrigemID = null;
        if(filtrarImovelContaActionForm.getLocalidadeOrigemID() != null && !filtrarImovelContaActionForm.getLocalidadeOrigemID().equals("")){
        	localidadeOrigemID = Integer.parseInt(filtrarImovelContaActionForm.getLocalidadeOrigemID());
        	
        	
        	Localidade localidadeInscricaoOrigem = new Localidade();
        	localidadeInscricaoOrigem.setId(localidadeOrigemID);
        	
        	imovelInscricaoOrigem.setLocalidade(localidadeInscricaoOrigem);
        }
        
        Integer setorComercialOrigemCD = null;
        SetorComercial setorComercialInscricaoOrigem = new SetorComercial();
        if(filtrarImovelContaActionForm.getSetorComercialOrigemCD() != null && !filtrarImovelContaActionForm.getSetorComercialOrigemCD().equals("")){
           setorComercialOrigemCD = Integer.parseInt(filtrarImovelContaActionForm.getSetorComercialOrigemCD());
           
           setorComercialInscricaoOrigem.setCodigo(setorComercialOrigemCD);
       	
       	   imovelInscricaoOrigem.setSetorComercial(setorComercialInscricaoOrigem);
        }
        else{
        	
        	setorComercialInscricaoOrigem.setCodigo(0);
           	
        	imovelInscricaoOrigem.setSetorComercial(setorComercialInscricaoOrigem);
        }
        
        Integer quadraOrigemNM = null;
        Quadra quadraInscricaoOrigem = new Quadra();
        if(filtrarImovelContaActionForm.getQuadraOrigemNM() != null && !filtrarImovelContaActionForm.getQuadraOrigemNM().equals("")){
        	quadraOrigemNM = Integer.parseInt(filtrarImovelContaActionForm.getQuadraOrigemNM());
        	
        	
        	quadraInscricaoOrigem.setNumeroQuadra(quadraOrigemNM);
        	
        	imovelInscricaoOrigem.setQuadra(quadraInscricaoOrigem);
        }
        else{
        	
        	quadraInscricaoOrigem.setNumeroQuadra(0);
        	
        	imovelInscricaoOrigem.setQuadra(quadraInscricaoOrigem);
        }
        
        short loteOrigem = 0;
        if (filtrarImovelContaActionForm.getLoteOrigem() != null && !filtrarImovelContaActionForm.getLoteOrigem().equalsIgnoreCase("")) {
            loteOrigem = Short.parseShort(filtrarImovelContaActionForm.getLoteOrigem());
            
            imovelInscricaoOrigem.setLote(loteOrigem);
            
        }
        
        short subLoteOrigem = 0;
        if (filtrarImovelContaActionForm.getSubLoteOrigem() != null && !filtrarImovelContaActionForm.getSubLoteOrigem().equalsIgnoreCase("")) {
        	subLoteOrigem = Short.parseShort(filtrarImovelContaActionForm.getSubLoteOrigem());
        	
        	imovelInscricaoOrigem.setSubLote(subLoteOrigem);
        }

        Integer codigoRotaOrigem = null;
        if (filtrarImovelContaActionForm.getCodigoRotaOrigem() != null && 
        	!filtrarImovelContaActionForm.getCodigoRotaOrigem().equalsIgnoreCase("")) {

        	codigoRotaOrigem = new Integer(filtrarImovelContaActionForm.getCodigoRotaOrigem());
        }
        
        Integer sequencialRotaOrigem = null;
        if (filtrarImovelContaActionForm.getSequencialRotaOrigem() != null && 
        	!filtrarImovelContaActionForm.getSequencialRotaOrigem().equalsIgnoreCase("")) {

        	sequencialRotaOrigem = new Integer(filtrarImovelContaActionForm.getSequencialRotaOrigem());
        }            
        
        Localidade localidadeOrigem = (Localidade) validarCampo(
                localidadeOrigemID, null, 1);
        SetorComercial setorComercialOrigem = null;
        Quadra quadraOrigem = null;

        //Valida��o dos campos da inscri��o de origem (FS0002, FS0003, FS0004)
        if (localidadeOrigem != null) {
            
        	if (setorComercialOrigemCD != null) {

                setorComercialOrigem = (SetorComercial) validarCampo(
                        localidadeOrigem.getId(),
                        setorComercialOrigemCD.toString(), 2);

                if (setorComercialOrigem == null) {
                    throw new ActionServletException(
                            "atencao.pesquisa.setor_origem_inexistente");
                } else {
                    if (quadraOrigemNM != null) {

                        quadraOrigem = (Quadra) validarCampo(
                                setorComercialOrigem.getId(), quadraOrigemNM.toString(), 3);

                        if (quadraOrigem == null) {
                            throw new ActionServletException(
                                    "atencao.pesquisa.quadra_origem_inexistente");
                        }
                    }
                }
            }
        }

        //Dados da inscri��o de destino
        Imovel imovelInscricaoDestino = new Imovel();
        
        Integer localidadeDestinoID = null;
        if(filtrarImovelContaActionForm.getLocalidadeDestinoID() != null && !filtrarImovelContaActionForm.getLocalidadeDestinoID().equals("")){
        	localidadeDestinoID = Integer.parseInt(filtrarImovelContaActionForm.getLocalidadeDestinoID());
        	
        	Localidade localidadeInscricaoDestino = new Localidade();
        	localidadeInscricaoDestino.setId(localidadeDestinoID);
        	
        	imovelInscricaoDestino.setLocalidade(localidadeInscricaoDestino);
        }
        
        Integer setorComercialDestinoCD = null;
        SetorComercial setorComercialInscricaoDestino = new SetorComercial();
        if(filtrarImovelContaActionForm.getSetorComercialDestinoCD() != null && !filtrarImovelContaActionForm.getSetorComercialDestinoCD().equals("")){
        	setorComercialDestinoCD = Integer.parseInt(filtrarImovelContaActionForm.getSetorComercialDestinoCD());
        	
            setorComercialInscricaoDestino.setCodigo(setorComercialDestinoCD);
        	
            imovelInscricaoDestino.setSetorComercial(setorComercialInscricaoDestino);
        }
        else{
        	
        	setorComercialInscricaoDestino.setCodigo(0);
        	
            imovelInscricaoDestino.setSetorComercial(setorComercialInscricaoDestino);
        }
        
        //Remove as quadras selecionadas, caso n�o seja o mesmo setor comercial das inscri��es
        if(setorComercialOrigemCD != null && setorComercialDestinoCD != null 
        	&& !setorComercialOrigemCD.equals(setorComercialDestinoCD)){
          if(sessao.getAttribute("quadraSelecionada") != null){
             sessao.removeAttribute("quadraSelecionada");
          }
        }
        
        Integer quadraDestinoNM = null;
        Quadra quadraInscricaoDestino = new Quadra();
        if(filtrarImovelContaActionForm.getQuadraDestinoNM() != null && !filtrarImovelContaActionForm.getQuadraDestinoNM().equals("")){
        	quadraDestinoNM = Integer.parseInt(filtrarImovelContaActionForm.getQuadraDestinoNM());
        	
        	quadraInscricaoDestino.setNumeroQuadra(quadraDestinoNM);
        	
        	imovelInscricaoDestino.setQuadra(quadraInscricaoDestino);
        }
        else{
        	
        	quadraInscricaoDestino.setNumeroQuadra(0);
        	
        	imovelInscricaoDestino.setQuadra(quadraInscricaoDestino);
        }
        
        short loteDestino = 0;
        if (filtrarImovelContaActionForm.getLoteDestino() != null && !filtrarImovelContaActionForm.getLoteDestino().equalsIgnoreCase("")) {
        	loteDestino = Short.parseShort(filtrarImovelContaActionForm.getLoteDestino());
        	
        	imovelInscricaoDestino.setLote(loteDestino);
        }
        
        short subLoteDestino = 0;
        if (filtrarImovelContaActionForm.getSubLoteDestino() != null && !filtrarImovelContaActionForm.getSubLoteDestino().equalsIgnoreCase("")) {
        	subLoteDestino = Short.parseShort(filtrarImovelContaActionForm.getSubLoteDestino());
        	
        	imovelInscricaoDestino.setSubLote(subLoteDestino);
        }

        Integer codigoRotaDestino = null;
        if (filtrarImovelContaActionForm.getCodigoRotaDestino() != null && 
        	!filtrarImovelContaActionForm.getCodigoRotaDestino().equalsIgnoreCase("")) {

        	codigoRotaDestino = new Integer(filtrarImovelContaActionForm.getCodigoRotaDestino());
        }

        Integer sequencialRotaDestino = null;
        if (filtrarImovelContaActionForm.getSequencialRotaDestino() != null && 
        	!filtrarImovelContaActionForm.getSequencialRotaDestino().equalsIgnoreCase("")) {

        	sequencialRotaDestino = new Integer(filtrarImovelContaActionForm.getSequencialRotaDestino());
        }        
      
        
        Localidade localidadeDestino = (Localidade) validarCampo(
                localidadeDestinoID, null, 1);
        SetorComercial setorComercialDestino = null;
        Quadra quadraDestino = null;


            //Valida��o dos campos da inscri��o de destino (FS0002, FS0003,
            // FS0004)
            if (localidadeDestino != null) {
                
                if (setorComercialDestinoCD != null) {

                    setorComercialDestino = (SetorComercial) validarCampo(
                            localidadeDestino.getId(),
                            setorComercialDestinoCD.toString(), 2);

                    if (setorComercialDestino == null) {
                        //Nenhum Setor Comercial encontrado
                        throw new ActionServletException(
                                "atencao.pesquisa.setor_destino_inexistente");
                    } else {
                        if (quadraDestinoNM != null) {

                            quadraDestino = (Quadra) validarCampo(
                                    setorComercialDestino.getId(),
                                    quadraDestinoNM.toString(), 3);

                            if (quadraDestino == null) {
                                //Nenhuma Quadra encontrada
                                throw new ActionServletException(
                                        "atencao.pesquisa.quadra_destino_inexistente");
                            }
                        }
                    }
                }
            }

            /*
             * Prepara os objetos para a pesquisa dos imoveis que est�o
             * localizados na inscri��o de origem
             */
            Integer numeroQuadraOrigem = null;
            if(quadraOrigem != null){
            	numeroQuadraOrigem = quadraOrigem.getNumeroQuadra();
            }
            
            Integer numeroQuadraDestino = null;
            if(quadraDestino != null){
            	numeroQuadraDestino = quadraDestino.getNumeroQuadra();
            }
            
            Integer setorComercialOrigemID = null;
            if(setorComercialOrigem != null){
            	setorComercialOrigemID = setorComercialOrigem.getId();
            }
            
            Integer setorComercialDestinoID = null;
            if(setorComercialDestino != null){
            	setorComercialDestinoID = setorComercialDestino.getId();
            }
              
            String[] quadras = null;
            if(sessao.getAttribute("quadraSelecionada") != null){
              quadras = (String[])sessao.getAttribute("quadraSelecionada");	
            }
            
            
            /*
             * Colocado por Raphael Rossiter em 02/08/2007
             * 
             * OBJETIVO: Acrescentar o par�metro grupo de faturamento para o filtro de manuten��o
             * de v�rias contas.
             */
            Integer idGrupoFaturamento = null;
            
            if(filtrarImovelContaActionForm.getIdFaturamentoGrupo() != null && 
               !filtrarImovelContaActionForm.getIdFaturamentoGrupo().equals("")){
            	
            	idGrupoFaturamento = Integer.parseInt(filtrarImovelContaActionForm.getIdFaturamentoGrupo());
            }
            
            FiltrarImovelInserirManterContaHelper filtro = new FiltrarImovelInserirManterContaHelper();
            
            if (localidadeOrigem != null){
            	filtro.setLocalidadeOrigemID(localidadeOrigem.getId());
            }
            
            if (localidadeDestino != null){
            	filtro.setLocalidadeDestinoID(localidadeDestino.getId());
            }
            
            filtro.setSetorComercialOrigemID(setorComercialOrigemID);
            filtro.setSetorComercialDestinoID(setorComercialDestinoID);
            
            filtro.setQuadraOrigemID(numeroQuadraOrigem);
            filtro.setQuadraDestinoID(numeroQuadraDestino);
            
            filtro.setLoteOrigem(loteOrigem);
            filtro.setLoteDestino(loteDestino);
            
            filtro.setSubLoteOrigem(subLoteOrigem);
            filtro.setSubLoteDestino(subLoteDestino);
            
            filtro.setQuadras(quadras);
            
            filtro.setCodigoRotaOrigem(codigoRotaOrigem);
            filtro.setCodigoRotaDestino(codigoRotaDestino);
            
            filtro.setSequencialRotaOrigem(sequencialRotaOrigem);
            filtro.setSequencialRotaDestino(sequencialRotaDestino);
            
            /*
             * Colocado por Raphael Rossiter em 02/08/2007
             * 
             * OBJETIVO: Acrescentar o par�metro grupo de faturamento para o filtro de manuten��o
             * de v�rias contas.
             */
            filtro.setIdGrupoFaturamento(idGrupoFaturamento);
            
            //[FS0011] - Verificar a abrang�ncia do usu�rio
            //adicionado por Vivianne Sousa
            verificarAbrangenciaUsuario(usuarioLogado, filtro, filtrarImovelContaActionForm);

			/*
			 * Colocado por Ana Maria em 20/04/2009				
			 */
			if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
				Integer qtdImovelBloqueado = fachada.pesquisarColecaoImovelPerfilBloqueado(filtro);
				if(!qtdImovelBloqueado.equals(0)){						
					String confirmado = httpServletRequest.getParameter("confirmado");
					if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
						httpServletRequest.setAttribute("caminhoActionConclusao",
								"/gsan/filtrarImovelInserirManterContaAction.do");
						return montarPaginaConfirmacao(
								"atencao.existe_imovel_perfil_bloqueado",
								httpServletRequest, actionMapping, qtdImovelBloqueado.toString());
					}else{
		    			filtro.setVerificarImovelPerfilBloqueado(true);
					}
				}
			}
            
			if(bancos != null){
            	colecaoImovel = fachada.pesquisarImoveisBancoDebitoAutomatico(bancos);
            }else{
            	colecaoImovel = fachada.pesquisarColecaoImovel(filtro);            	
            }

            if (colecaoImovel == null || colecaoImovel.isEmpty()) {
                throw new ActionServletException(
                        "atencao.imovel_inexistente_criterio_pesquisa");
            }else{
            	sessao.setAttribute("colecaoImovel", colecaoImovel);
            }
            
            if (imovelInscricaoOrigem.getLocalidade() != null){
            	sessao.setAttribute("inscricaoInicialImovel", imovelInscricaoOrigem.getInscricaoFormatada());
            }
            else{
            	sessao.removeAttribute("inscricaoInicialImovel");
            }
            
            if (imovelInscricaoDestino.getLocalidade() != null){
            	sessao.setAttribute("inscricaoDestinoImovel", imovelInscricaoDestino.getInscricaoFormatada());
            }
            else{
            	sessao.removeAttribute("inscricaoDestinoImovel");
            }
            
            
            if (idGrupoFaturamento != null){
            	sessao.setAttribute("grupoFaturamento", idGrupoFaturamento.toString());
            }
            else{
            	sessao.removeAttribute("grupoFaturamento");
            }
            
            sessao.removeAttribute("codigoCliente");
            sessao.removeAttribute("nomeCliente");   
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

        //devolve o mapeamento de retorno
        return retorno;
    }

    /**
     * Valida os valores digitados pelo usu�rio
     * 
     * @param campoDependencia
     * @param dependente
     * @param tipoObjeto
     * @return Object
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private Object validarCampo(Integer campoDependencia, String dependente,
            int tipoObjeto) {

        Object objeto = null;
        Collection colecaoPesquisa;
        
        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        if (campoDependencia != null) {

            if (dependente == null || tipoObjeto == 1) {
                //Localidade
                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.ID, campoDependencia));

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                        Localidade.class.getName());

                if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                    objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                }
            } else if (dependente != null && !dependente.equalsIgnoreCase("")
                    && tipoObjeto > 1) {
                switch (tipoObjeto) {
                // Setor Comercial
                case 2:
                    FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.ID_LOCALIDADE,
                                    campoDependencia));

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                                    new Integer(dependente)));

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.INDICADORUSO,
                                    ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                            SetorComercial.class.getName());

                    if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                        objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                    }

                    break;
                // Quadra
                case 3:
                    FiltroQuadra filtroQuadra = new FiltroQuadra();
                    
                    //Objetos que ser�o retornados pelo hibernate
                    filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota.faturamentoGrupo");
                    
                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.ID_SETORCOMERCIAL, campoDependencia));

                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.NUMERO_QUADRA, new Integer(dependente)));

                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.INDICADORUSO,
                            ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroQuadra,
                            Quadra.class.getName());

                    if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                        objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                    }

                    break;
                default:
                    break;
                }
            }
        }

        return objeto;
    }

    /**
     * Verifica a exist�ncia de im�veis no objeto passado como par�metro Os
     * objetos passados podem ser do tipo SetorComercial = 1 e Quadra = 2
     * 
     * @param objetoCondicao
     * @param tipoObjeto
     * @return um boleano
     */
 /*   private boolean existeImovel(Object objetoCondicao, int tipoObjeto) {

        boolean retorno = false;
        Collection colecaoPesquisa;
        
        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        if (tipoObjeto == 1) {
            SetorComercial setorComercial = (SetorComercial) objetoCondicao;

            colecaoPesquisa = fachada.pesquisarImovel(null, setorComercial
                    .getId(), null, null);

            if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
                retorno = true;
            }
        } else {
            Quadra quadra = (Quadra) objetoCondicao;

            colecaoPesquisa = fachada.pesquisarImovel(null, null, quadra
                    .getId(), null);

            if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
                retorno = true;
            }
        }

        return retorno;
    }*/
	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	public String pesquisarCliente(String idCliente,
			Fachada fachada) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());

		Cliente cliente = null;
		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
            cliente = ((Cliente) ((List) clienteEncontrado).get(0));            
			// O Cliente foi encontrado
			if (cliente.getIndicadorUso().equals(
							ConstantesSistema.INDICADOR_USO_DESATIVO)) {
				throw new ActionServletException("atencao.cliente.inativo",
						null, ""
								+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			}

		} else {
            throw new ActionServletException("atencao.cliente.inexistente");
		}
		
		return cliente.getNome();
	}	
	
	/**
	 * [FS0011] - Verificar a abrang�ncia do usu�rio
	 * Vivianne Sousa - 28/07/2009
	 * analista:Nelson Carvalho
	 */
	private void verificarAbrangenciaUsuario(Usuario usuarioLogado,FiltrarImovelInserirManterContaHelper filtro,FiltrarImovelContaActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		Integer nivelAbrangencia = usuarioLogado.getUsuarioAbrangencia().getId();
		
		UnidadeNegocio unidadeNegocioUsuario = fachada.pesquisarUnidadeNegocioUsuario(usuarioLogado.getId());
		usuarioLogado.setUnidadeNegocio(unidadeNegocioUsuario);
		
		if(    (form.getCodigoClienteSuperior() != null && !form.getCodigoClienteSuperior().equals(""))
			|| (form.getCodigoCliente() != null && !form.getCodigoCliente().equals(""))
			|| (form.getBanco() != null && !form.getBanco().equals("-1"))
			|| (form.getIdFaturamentoGrupo() != null && !form.getIdFaturamentoGrupo().equals("")) ){
			
			//caso tenha sido informado um Cliente, ou um Grupo de Faturamento, ou um Banco de D�bito Autom�tico como criterio de filtro
			//o usu�rio dever� necessariamente possuir abrang�ncia de Estado
			//caso contr�rio, exibir a mensagem "Acesso a opera��o negado devido a abrang�ncia do usu�rio."
			
			if(nivelAbrangencia.intValue() != UsuarioAbrangencia.ESTADO_INT){
				 throw new ActionServletException("atencao.acesso.negado.abrangencia");
			}
			
		}
		
		if(	(filtro.getLocalidadeOrigemID() != null && !filtro.getLocalidadeOrigemID().equals(""))		
				||  (filtro.getSetorComercialOrigemID() != null && !filtro.getSetorComercialOrigemID().equals(""))
				||  (filtro.getQuadraOrigemID() != null && !filtro.getQuadraOrigemID().equals(""))
				||  (filtro.getQuadras() != null)
				||  (filtro.getLoteOrigem() != null && !filtro.getLoteOrigem().equals(""))
				||  (filtro.getSubLoteOrigem() != null && !filtro.getSubLoteOrigem().equals(""))
				||  (filtro.getCodigoRotaOrigem() != null && !filtro.getCodigoRotaOrigem().equals(""))	){
			
			if(nivelAbrangencia.intValue() != UsuarioAbrangencia.ESTADO_INT){
				
				boolean existe = fachada.existeLocalidadeForaDaAbrangenciaUsuario(filtro,nivelAbrangencia,usuarioLogado);
				
				if(existe){
					throw new ActionServletException("atencao.acesso.negado.abrangencia");
				}
				
			}
			
		}
		
	}
}
