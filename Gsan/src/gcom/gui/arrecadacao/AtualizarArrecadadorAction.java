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
package gcom.gui.arrecadacao;
import java.util.Collection;
import java.util.Date;

import gcom.arrecadacao.Arrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 *
 * @author K�ssia Albuquerque
 * @date 08/01/2007
 */


public class AtualizarArrecadadorAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarArrecadadorActionForm atualizarArrecadadorActionForm = (AtualizarArrecadadorActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Arrecadador arrecadador = (Arrecadador) sessao.getAttribute("arrecadador");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Validamos o cliente
		FiltroCliente filtro = new FiltroCliente();
				
		arrecadador.setId(new Integer(atualizarArrecadadorActionForm.getId()));
		arrecadador.setNumeroInscricaoEstadual(atualizarArrecadadorActionForm.getInscricaoEstadual());
		arrecadador.setIndicadorUso(new Short (atualizarArrecadadorActionForm.getIndicadorUso()));
		
		filtro.adicionarParametro( new ParametroSimples(
				FiltroCliente.ID, atualizarArrecadadorActionForm.getIdCliente() ) );
		
		Collection colCliente = fachada.pesquisar( filtro, Cliente.class.getName() );
		
		if ( colCliente == null || !colCliente.iterator().hasNext() ){
			// O cliente n�o existe
			throw new ActionServletException("atencao.cliente.inexistente",
					null, "Cliente");
		}
		
		if(atualizarArrecadadorActionForm.getIdImovel() != null
				&& !atualizarArrecadadorActionForm.getIdImovel().equals("")){
			// Validamos o imovel
			FiltroImovel filtroImovel = new FiltroImovel();
					
			filtroImovel.adicionarParametro( new ParametroSimples(
					FiltroImovel.ID, atualizarArrecadadorActionForm.getIdImovel() ) );
			
			Collection colImovel = fachada.pesquisar( filtroImovel, Imovel.class.getName() );
			
			if ( colImovel == null || colImovel.isEmpty() ){
				// O Imovel n�o existe
				throw new ActionServletException("atencao.imovel.inexistente",
						null, "Imovel");
			}
		}
		
		String id = atualizarArrecadadorActionForm.getId();		
        String inscricaoEstadual = atualizarArrecadadorActionForm
        .getInscricaoEstadual();
        String indicadorUso = atualizarArrecadadorActionForm.getIndicadorUso();
		
		String[] idArrecadador = new String[1];
		
		idArrecadador[0] = arrecadador.getId().toString();
		
		//Atualiza a entidade com os valores do formul�rio
	//	atualizarArrecadadorActionForm.setFormValues(arrecadador);
		
		// Seta Objeto Cliente para Arrecadador
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, atualizarArrecadadorActionForm.getIdCliente()));
		Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());		
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
		arrecadador.setCliente(cliente);
		if(atualizarArrecadadorActionForm.getIdImovel() != null && !atualizarArrecadadorActionForm.getIdImovel().equals("")){
			// Seta Objeto Imovel para Arrecadador
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, atualizarArrecadadorActionForm.getIdImovel()));
			Collection colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			arrecadador.setImovel(imovel);
		}
		
        arrecadador.setId(new Integer(id));
        
        if(!atualizarArrecadadorActionForm.getInscricaoEstadual().trim().equals("") && atualizarArrecadadorActionForm.getInscricaoEstadual() != null){
        	arrecadador.setNumeroInscricaoEstadual(inscricaoEstadual);
        	
        } else {
        	inscricaoEstadual = null;
        	arrecadador.setNumeroInscricaoEstadual(inscricaoEstadual);
        }
       
        arrecadador.setUltimaAlteracao( new Date() );	
        arrecadador.setIndicadorUso(new Short(indicadorUso));
        
		//atualiza na base de dados de Arrecadador
		fachada.atualizarArrecadador(arrecadador,usuarioLogado);
		
		//Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Arrecadador de c�digo "+ atualizarArrecadadorActionForm.getId()+" atualizado com sucesso.", "Realizar outra Manuten��o de Arrecadador",
				"exibirFiltrarArrecadadorAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



