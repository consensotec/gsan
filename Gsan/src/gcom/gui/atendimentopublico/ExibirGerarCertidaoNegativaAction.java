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

import gcom.cadastro.cliente.Cliente;import gcom.cadastro.cliente.ClienteTipo;import gcom.cadastro.cliente.FiltroCliente;import gcom.cadastro.imovel.FiltroImovel;import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;import gcom.cadastro.imovel.Imovel;import gcom.cadastro.imovel.ImovelCobrancaSituacao;import gcom.cadastro.sistemaparametro.SistemaParametro;import gcom.fachada.Fachada;import gcom.gui.ActionServletException;import gcom.gui.GcomAction;import gcom.util.ConstantesSistema;import gcom.util.ControladorException;import gcom.util.FachadaException;import gcom.util.Util;import gcom.util.filtro.ParametroNulo;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

/**
 * [UC0738] Gerar Relat�rio de Im�veis com Faturas em Atraso
 * 
 * @author Bruno Barros
 *
 * @date 22/01/2008
 */


public class ExibirGerarCertidaoNegativaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno		ActionForward retorno = actionMapping.findForward("exibirGerarCertidaoNegativa");
		GerarCertidaoNegativaActionForm form = 			(GerarCertidaoNegativaActionForm) actionForm;
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		// Pesquisar Imovel		if (objetoConsulta != null && !objetoConsulta.trim().equals("") ) {			if ( objetoConsulta.trim().equals( "1" ) ){				// Faz a consulta do Imovel				this.pesquisarImovel(form,objetoConsulta, httpServletRequest);			}		}		return retorno;	}
	
	/**	 * Pesquisa Localidade	 *	 * @author Bruno Barros	 * @date 22/01/2008	 */
	private void pesquisarImovel(GerarCertidaoNegativaActionForm form,		String objetoConsulta, HttpServletRequest httpServletRequest) {
		String idImovel = form.getIdImovel();
		FiltroImovel filtroImovel = new FiltroImovel();		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "setorComercial" );		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "quadra" );		filtroImovel.adicionarParametro(			new ParametroSimples(FiltroImovel.ID, idImovel));
		// Recupera o Imovel		Collection colecaoImovel = 			this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			httpServletRequest.setAttribute("idImovelNaoEncontrado", null );
			Imovel imovel = 				(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);									//[FS0006] Validar CPF/CNPJ			SistemaParametro sistemaParametro = this.getSistemaParametro();			if(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_CAEMA)){				boolean cpfCnpjValido = this.getFachada().verificarClienteImovelCpfCnpjValidos(imovel.getId());				if(!cpfCnpjValido){					throw new ActionServletException("atencao.certidao_neg_cpf_cnpj_nao_validado");				}			}						//[FS0007] Validar Situa��o de Cobran�a			Object[] qtd = this.getFachada().obterQtdSituacoesCobrancaBloqueadas(imovel.getId());			if(qtd != null && ((Integer)qtd[1]).intValue() > 0){				throw new ActionServletException("atencao.nao_permite_ger_certidao_deb",(String)qtd[0]);			}						form.setIdImovel(imovel.getId().toString());			form.setMatriculaImovel(imovel.getInscricaoFormatada());
			// Encontramos o cliente Usuario			Cliente cliente = Fachada.getInstancia().pesquisarClienteUsuarioImovel( imovel.getId() );
			// Carregamos as informa��es			FiltroCliente filtroCliente = new FiltroCliente();			filtroCliente.adicionarCaminhoParaCarregamentoEntidade( "clienteTipo" );			filtroCliente.adicionarParametro(				new ParametroSimples(FiltroCliente.ID, cliente.getId()));
			// Recupera o Cliente			Collection colecaoCliente = 				this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
			cliente = 				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);			
			if ( cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA.intValue()  ){				form.setCpfCnpj( cliente.getCnpjFormatado() );			} else {				form.setCpfCnpj( cliente.getCpfFormatado() );			}
			form.setNomeClienteUsuario( cliente.getNome() );
			try {				form.setEnderecoImovel( Fachada.getInstancia().pesquisarEnderecoFormatado( imovel.getId() ) );			} catch (ControladorException e) {				throw new FachadaException( e.getMessage() );			}			
		} else {
			form.setIdImovel( null );			form.setNomeClienteUsuario( null );			form.setCpfCnpj( null );			form.setEnderecoImovel( null );			form.setMatriculaImovel("Imovel inexistente");			httpServletRequest.setAttribute("idImovelNaoEncontrado", "s" );		}	}	
}
