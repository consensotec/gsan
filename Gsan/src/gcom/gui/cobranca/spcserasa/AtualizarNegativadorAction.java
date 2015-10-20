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

import java.util.Collection;
import java.util.Date;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.NegativacaoImovei;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.FiltroNegativacaoImoveis;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Classe respons�vel por atualizar um Negativador.
 * 
 * @author Thiago Vieira
 * date: 24/12/07
 */
public class AtualizarNegativadorAction extends GcomAction {


    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	
//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Pega o form do cliente
        AtualizarNegativadorActionForm form = (AtualizarNegativadorActionForm) actionForm; 

//		 ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();


		operacao.setId(Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

        
        short indicadorUso = Short.parseShort(form.getAtivo());
        Integer clienteId = new Integer(form.getCodigoCliente());
        
        Integer imovelId = null;
        if(!"".equals(form.getCodigoImovel())){
        	imovelId = new Integer(form.getCodigoImovel());
        }
         
       	String inscricaoEstadual = form.getInscricaoEstadual();
       	Integer idNegativador = new Integer(form.getIdNegativador());
       	short codigoAgente = Short.parseShort(form.getCodigoAgente());
       	Long time = Long.parseLong(form.getTime()); 
       	
		Cliente cliente = new Cliente(); 
		cliente.setId(clienteId);
	
		
		//Criando objeto Negativador a ser incluido
		Negativador negativador = new Negativador();
		negativador.setId(idNegativador);
		negativador.setCodigoAgente(codigoAgente);
		negativador.setCliente(cliente);
		
		if(imovelId != null){
			Imovel imovel = new Imovel();
			imovel.setId(imovelId);	
			negativador.setImovel(imovel);
		}
		
		negativador.setNumeroInscricaoEstadual(inscricaoEstadual);
		negativador.setIndicadorUso(indicadorUso);
			
		//......................................................................
		FiltroNegativador filtroNegativador = new FiltroNegativador();

		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_CLIENTE, clienteId));
		filtroNegativador.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroNegativador.ID, idNegativador));
		
		Collection collNegativador= fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
		
		if(collNegativador != null && !collNegativador.isEmpty()){		
			throw new ActionServletException("atencao.negativador_associado_cliente");
			
		}		
		
		//......................................................................
		if(imovelId!=null){
			filtroNegativador = new FiltroNegativador();

			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_IMOVEL, imovelId));
			filtroNegativador.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroNegativador.ID, idNegativador));
			Collection collNegativadorImovel= fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
			
			if(collNegativadorImovel != null && !collNegativadorImovel.isEmpty()){		
				throw new ActionServletException("atencao.negativador_associado_imovel");
				
			}	
			
		}
			
		//......................................................................
		
		if(indicadorUso == ConstantesSistema.INDICADOR_USO_DESATIVO){
			
			FiltroNegativacaoImoveis filtroNegativacaoImoveis = new FiltroNegativacaoImoveis();
			filtroNegativacaoImoveis.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroNegativacaoImoveis.NEGATIVADOR_ID, idNegativador));
			filtroNegativacaoImoveis.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroNegativacaoImoveis.INDICADOR_EXCLUIDO, "2"));
			Collection collNegativacaoImoveis= fachada.pesquisar(filtroNegativacaoImoveis,NegativacaoImovei.class.getName());	
			
			if(collNegativacaoImoveis != null && !collNegativacaoImoveis.isEmpty()){		
				throw new ActionServletException("atencao.imoveis_no_negativador");
				
			}
			
			
		}
			
		//.......................................................................
			
		//Check para atualiza��o realizada por outro usu�rio 
		filtroNegativador = new FiltroNegativador(); 
		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.ID, idNegativador));
		
		Collection negativadores = Fachada.getInstancia().pesquisar(filtroNegativador, Negativador.class.getName());
		Negativador negativadorAtual = (Negativador)negativadores.iterator().next();

		if (negativadorAtual.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		negativador.setUltimaAlteracao(new Date());
//			 Atualiza o Negativador
		
		// ------------ REGISTRAR TRANSA��O ----------------
		negativador.setOperacaoEfetuada(operacaoEfetuada);
		negativador.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativador);
		// ------------ REGISTRAR TRANSA��O ----------------
		
		Fachada.getInstancia().atualizar(negativador);
		

		
		

		montarPaginaSucesso(httpServletRequest, "Negativador "
				+ form.getCodigoAgente() + " atualizado com sucesso.",
				"Realizar outra manuten��o de Negativador",
				"exibirFiltrarNegativadorAction.do?menu=sim",
				"","");
		        
		return retorno;
        
    }
}