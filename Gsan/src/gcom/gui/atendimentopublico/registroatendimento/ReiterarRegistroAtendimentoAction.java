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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descri��o da Classe>>
 * 
 * @author lms
 * @date 20/09/2006
 */
public class ReiterarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		ReiterarRegistroAtendimentoActionForm form = (ReiterarRegistroAtendimentoActionForm) actionForm;
		
		RegistroAtendimento registroAtendimento = fachada.obterDadosRegistroAtendimento(Integer.parseInt(httpServletRequest.getParameter("numeroRA"))).getRegistroAtendimento();
		
		
		RAReiteracao raReiteracao = new RAReiteracao();
		
		String protocoloAtendimento = (String)sessao.getAttribute("protocoloAtendimento");
		raReiteracao.setNumeroProtocoloAtendimento(protocoloAtendimento);
		
		Cliente cliente = null;
		UnidadeOrganizacional unidade = null;
		if(form.getIdClienteSolicitante() != null && !form.getIdClienteSolicitante().equals("")){
			cliente = new Cliente();
			cliente.setId(new Integer(form.getIdClienteSolicitante()));
		}else if(form.getIdUnidadeSolicitante() != null && !form.getIdUnidadeSolicitante().equals("")){
			unidade = new UnidadeOrganizacional();
			unidade.setId(new Integer(form.getIdUnidadeSolicitante()));
		}else{
			raReiteracao.setSolicitante(form.getNomeSolicitante());
		}
		raReiteracao.setUnidadeOrganizacional(unidade);
		raReiteracao.setCliente(cliente);

		raReiteracao.setRegistroAtendimento(registroAtendimento);
//		raReiteracao.setPontoReferencia(form.getPontoReferencia());
		raReiteracao.setObservacao(form.getObservacao());
		
		if (sessao.getAttribute("colecaoEnderecos") != null) {

			Collection enderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

			if (!enderecos.isEmpty()) {
				
				ClienteEndereco endereco = (ClienteEndereco)enderecos.iterator().next();
				
				raReiteracao.setComplementoEndereco(endereco.getComplemento());
				raReiteracao.setIndicadorEnderecoCorrespondencia(endereco.getIndicadorEnderecoCorrespondencia());
				raReiteracao.setLogradouroBairro(endereco.getLogradouroBairro());
				raReiteracao.setLogradouroCep(endereco.getLogradouroCep());
				raReiteracao.setNumeroImovel(new Integer(endereco.getNumero().trim()));
				raReiteracao.setPerimetroInicial(endereco.getPerimetroInicial());
				raReiteracao.setPerimetroFinal(endereco.getPerimetroFinal());
				
			}
//			else{
//	        	throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Endere�o");
//			}
		}
//		else{
//        	throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Endere�o");
//		}

		Collection colecaoFonesSolicitante = (Collection)sessao.getAttribute("colecaoFonesSolicitante");
		if(colecaoFonesSolicitante == null || colecaoFonesSolicitante.isEmpty()){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Fones do Solicitante");
		}
		
		//gera a ordem de servi�o
		fachada.reiterarRegistroAtendimento(registroAtendimento, usuario, raReiteracao, colecaoFonesSolicitante);
		
		//Exibe a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, 
				"Registro de Atendimento de n�mero " + registroAtendimento.getId() + " reiterado com sucesso.", 
				"", 
				"exibirGerarOrdemServicoAction.do", 
				"exibirConsultarRegistroAtendimentoAction.do?numeroRA=" + registroAtendimento.getId(), 
				"Voltar");

		
		
		return retorno;
	}

}