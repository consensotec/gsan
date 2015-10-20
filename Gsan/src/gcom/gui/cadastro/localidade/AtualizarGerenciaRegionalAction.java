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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarGerenciaRegionalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarGerenciaRegionalActionForm atualizarGerenciaRegionalActionForm = (AtualizarGerenciaRegionalActionForm) actionForm;

		GerenciaRegional gerenciaRegional = (GerenciaRegional) sessao.getAttribute("gerenciaRegionalAtualizar");

		gerenciaRegional.setNome(atualizarGerenciaRegionalActionForm.getNome());
		gerenciaRegional.setCnpjGerenciaRegional(atualizarGerenciaRegionalActionForm.getCnpjGerenciaRegional());
		gerenciaRegional.setNomeAbreviado(atualizarGerenciaRegionalActionForm.getNomeAbreviado());
		
		gerenciaRegional.setFone(atualizarGerenciaRegionalActionForm.getTelefone());
		gerenciaRegional.setRamalFone(atualizarGerenciaRegionalActionForm.getRamal());
		gerenciaRegional.setFax(atualizarGerenciaRegionalActionForm.getFax());
		gerenciaRegional.setEmail(atualizarGerenciaRegionalActionForm.getEmail());
		
		Short indicadorUso = Short.decode(atualizarGerenciaRegionalActionForm.getIndicadorUso());
		gerenciaRegional.setIndicadorUso(indicadorUso);
		
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		
		// O Endere�o � obrigat�rio.
		if (colecaoEnderecos == null || colecaoEnderecos.equals("")) {
			throw new ActionServletException("atencao.required", null, "Endere�o ");
		}
		
		Localidade endereco = (Localidade) colecaoEnderecos.iterator().next();

		// Atualiza��o do Endere�o 
	    if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
	    	gerenciaRegional.setLogradouroCep(endereco.getLogradouroCep());
	    	gerenciaRegional.setLogradouroBairro(endereco.getLogradouroBairro());
	    	gerenciaRegional.setComplementoEndereco(endereco.getComplementoEndereco());
	    	gerenciaRegional.setNumeroImovel(endereco.getNumeroImovel());
	    	gerenciaRegional.setEnderecoReferencia(endereco.getEnderecoReferencia());
	    }
	    
	    // Cliente
	    if (atualizarGerenciaRegionalActionForm.getIdCliente() != null && !atualizarGerenciaRegionalActionForm.getIdCliente().equalsIgnoreCase("") ){
			FiltroCliente filtroCliente = new FiltroCliente();
	
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					atualizarGerenciaRegionalActionForm.getIdCliente()));
	
			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());
	
			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
	
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
	
				Integer clienteFuncionario = fachada.verificarClienteSelecionadoFuncionario(new Integer(atualizarGerenciaRegionalActionForm.getIdCliente()));
				
				if(clienteFuncionario == null){
					throw new ActionServletException("atencao.cliente_selecionado_nao_e_funcionario");
				}
				
				gerenciaRegional.setCliente(cliente);
				
			} else {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
	    }
		fachada.atualizarGerenciaRegional(gerenciaRegional);

		montarPaginaSucesso(httpServletRequest, "Ger�ncia Regional de c�digo "
				+ gerenciaRegional.getId().toString() + " realizada com sucesso.",
				"Realizar outra Manuten��o de Ger�ncia Regional",
				"exibirFiltrarGerenciaRegionalAction.do?menu=sim");
		return retorno;
	}
}