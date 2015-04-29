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
package gsan.gui.cobranca.spcserasa;

import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.spcserasa.bean.DadosNegativacaoPorImovelHelper;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da inser��o de um Comando de Negativa��o 
 * (Por Matr�cula de Im�veis)
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class AdicionarMatriculaImovelNegativacaoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("adicionarComandoNegativacaPorMatricula");
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		 Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		
		if(inserirComandoNegativacaoActionForm.getMatriculaImovelDebitos() == null 
				|| inserirComandoNegativacaoActionForm.getMatriculaImovelDebitos().equals("")){
			throw new ActionServletException(
						"atencao.pesquisar_imovel");
		}
		
		Collection<DadosNegativacaoPorImovelHelper> colecaoDadosNegativacaoPorImovelHelper = null;
				
		if(sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper") != null){
			colecaoDadosNegativacaoPorImovelHelper = (Collection)sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper");
		}else{
			colecaoDadosNegativacaoPorImovelHelper = new ArrayList<DadosNegativacaoPorImovelHelper>();
		}
		
		//Cliente Selecionado
		Integer idClienteImovel = new Integer(inserirComandoNegativacaoActionForm.getClienteSelecionado());
		ClienteImovel clienteImovel = null;
		if(idClienteImovel.equals(new Integer(0))){
			//cliente respons�vel pelo parcelamento
			clienteImovel = (ClienteImovel)sessao.getAttribute("clienteParcelamento");
		}else{
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();		
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.ID,idClienteImovel));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
			Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
			clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
		}
		//RM4097 - adicionado por Vivianne Sousa - 29/12/2010 - Ana Cristina
		//[FS0031] � Verificar exist�ncia de conta em nome do cliente
		if(inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente().equals("" + ConstantesSistema.SIM)){
			
			Collection colecaoContasIds = null;
			if (sessao.getAttribute("colecaoContasIds")!= null &&
					!((Collection) sessao.getAttribute("colecaoContasIds")).isEmpty()){
				
				colecaoContasIds = (Collection)sessao.getAttribute("colecaoContasIds");
				
				boolean existeClienteConta = fachada.verificarSeExisteClienteConta(
						clienteImovel.getCliente().getId(),colecaoContasIds);
				
				if(!existeClienteConta){
					//Caso n�o exista nenhuma conta em nome do cliente selecionado 
					//para negativa��o, exibir a mensagem �N�o h� nenhuma conta que comp�e 
					//o d�bito do im�vel <<Matr�cula do Im�vel>> em nome do cliente 
					//<<Nome do Cliente Selecionado para Negativa��o>>. N�o � poss�vel negativ�-lo.�
					
					throw new ActionServletException("atencao.nao_ha_conta_cliente",
							clienteImovel.getImovel().getId().toString(), 
							clienteImovel.getCliente().getNome());
				}
				
			}
			
		}
		
		
		
		//Atualizar Im�vel j� adicionadao(link)
		if(httpServletRequest.getParameter("atualizar") != null){
			if(inserirComandoNegativacaoActionForm.getIdImovelDebitos() != null){
				Integer idImovelAtualizar = new Integer(inserirComandoNegativacaoActionForm.getIdImovelDebitos());			
				if(colecaoDadosNegativacaoPorImovelHelper != null && !colecaoDadosNegativacaoPorImovelHelper.isEmpty()){
					Iterator dadosNegativacaoPorImovelHelperIterator = colecaoDadosNegativacaoPorImovelHelper.iterator();
					while (dadosNegativacaoPorImovelHelperIterator.hasNext()) {
						DadosNegativacaoPorImovelHelper helper = (DadosNegativacaoPorImovelHelper) dadosNegativacaoPorImovelHelperIterator.next();
						if(helper.getIdImovel().equals(idImovelAtualizar)){
							helper.setIdCliente(clienteImovel.getCliente().getId());
							helper.setIdClienteRelacaoTipo(clienteImovel.getClienteRelacaoTipo().getId());
							if(clienteImovel.getCliente().getCpf() != null){
								helper.setCpfCliente(clienteImovel.getCliente().getCpfFormatado());
							}
							if(clienteImovel.getCliente().getCnpj() != null){
								helper.setCpfCliente(clienteImovel.getCliente().getCnpjFormatado());
							}
							helper.setUsuarioInclusao(usuario);
						}
					}
					//sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);
				}
			}
		}else{ //Inserir im�vel	selecionado		
			List colecaoConta = (List)sessao.getAttribute("colecaoContaValores");
			List colecaoGuias = (List)sessao.getAttribute("colecaoGuiaPagamentoValores");
			Integer qtdItensDebitoImovel = colecaoConta.size() + colecaoGuias.size();
			String totalDebitosImovel = (String)sessao.getAttribute("valorTotalSemAcrescimo");				
		
			//Dados do Cliente Selecionado e dos d�bitos do Im�vel
			DadosNegativacaoPorImovelHelper helper = new DadosNegativacaoPorImovelHelper();
			helper.setIdImovel(new Integer(inserirComandoNegativacaoActionForm.getIdImovelDebitos()));
			helper.setIdCliente(clienteImovel.getCliente().getId());
			helper.setIdClienteRelacaoTipo(clienteImovel.getClienteRelacaoTipo().getId());
			if(clienteImovel.getCliente().getCpf() != null){
				helper.setCpfCliente(clienteImovel.getCliente().getCpfFormatado());
			}
			if(clienteImovel.getCliente().getCnpj() != null){
				helper.setCpfCliente(clienteImovel.getCliente().getCnpjFormatado());
			}
			helper.setColecaoConta(colecaoConta);
			helper.setColecaoGuias(colecaoGuias);
			helper.setQtdItensDebitoImovel(qtdItensDebitoImovel);
			helper.setTotalDebitosImovel(Util.formatarMoedaRealparaBigDecimal(totalDebitosImovel));
			helper.setUsuarioInclusao(usuario);
			
			//[FS0011] Verificar matr�cula do im�vel j� existe na lista
			if(colecaoDadosNegativacaoPorImovelHelper.contains(helper)){
				throw new ActionServletException(
						"atencao.imovel_ja_existe_lista");
			}
			colecaoDadosNegativacaoPorImovelHelper.add(helper);		
		}
		
		sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);	
		
    	//Realizar um reload na tela
    	httpServletRequest.setAttribute("reloadPage", "OK");
    	
		return retorno;

	}
}
