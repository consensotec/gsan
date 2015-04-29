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
package gsan.gui.cobranca;

import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cobranca.parcelamento.FiltroParcelamento;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirConsultarListaParcelamentoDebitoAction extends
		GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a a��o de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarListaParcelamentoDebito");
		
		// Mudar isso quando tiver esquema de seguran�a
		//HttpSession sessao = httpServletRequest.getSession(false);

		ParcelamentoDebitoActionForm parcelamentoDebitoActionForm = (ParcelamentoDebitoActionForm) actionForm;
		
		// Obt�m a facahda
		Fachada fachada = Fachada.getInstancia();

		// Pega os codigos que o usuario digitou para a pesquisa direta de imovel
		String codigoImovel = httpServletRequest.getParameter("codigoImovel");
		
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			
			// Pesquisa o imovel na base
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, codigoImovel));
			
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));
			
			filtroClienteImovel.adicionarParametro(new ParametroNulo( FiltroClienteImovel.DATA_FIM_RELACAO));

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
				
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
			
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			Collection<ClienteImovel> imovelPesquisado = fachada.pesquisar(
					filtroClienteImovel, ClienteImovel.class.getName());

			// Se nenhum imovel for encontrado a mensagem � enviada para a p�gina
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("enderecoFormatado","".toUpperCase());
				parcelamentoDebitoActionForm.setNomeCliente("");
				parcelamentoDebitoActionForm.setCpfCnpj("");
				parcelamentoDebitoActionForm.setSituacaoAgua("");
				parcelamentoDebitoActionForm.setSituacaoEsgoto("");
				httpServletRequest.setAttribute("corImovel","exception");
				parcelamentoDebitoActionForm.setInscricao(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
            }
			// obtem o imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				
				ClienteImovel dadosImovel = (ClienteImovel) ((List) imovelPesquisado).get(0);
				
				//O endere�o foi encontrado
				if (dadosImovel.getImovel().getId() != null) 
				{
					parcelamentoDebitoActionForm.setCodigoImovel(""
							+ dadosImovel.getImovel().getId());
				}
				if (dadosImovel.getImovel().getInscricaoFormatada() != null) 
				{
					parcelamentoDebitoActionForm.setInscricao(""
							+ dadosImovel.getImovel().getInscricaoFormatada());
				}
				if (dadosImovel.getImovel().getLigacaoAguaSituacao() != null) 
				{
					parcelamentoDebitoActionForm.setSituacaoAgua(""
							+ dadosImovel.getImovel().getLigacaoAguaSituacao().getDescricao());
				}
				if (dadosImovel.getImovel().getLigacaoEsgotoSituacao() != null) 
				{
					parcelamentoDebitoActionForm.setSituacaoEsgoto(""
							+ dadosImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao());
				}
				if (dadosImovel.getCliente().getNome() != null) 
				{
					parcelamentoDebitoActionForm.setNomeCliente(""
							+ dadosImovel.getCliente().getNome());
				}
				if (dadosImovel.getImovel().getImovelPerfil() != null) 
				{
					parcelamentoDebitoActionForm.setImovelPerfil(""
							+ dadosImovel.getImovel().getImovelPerfil().getDescricao());
				}
				if (dadosImovel.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1 ){
					if (dadosImovel.getCliente().getCpfFormatado() != null) 
					{
						parcelamentoDebitoActionForm.setCpfCnpj(""
								+ dadosImovel.getCliente().getCpfFormatado());
					}
				}
				else
				{
					if (dadosImovel.getCliente().getCnpjFormatado() != null) 
					{
						parcelamentoDebitoActionForm.setCpfCnpj(""
								+ dadosImovel.getCliente().getCnpjFormatado());
					}
				}
				if (dadosImovel.getImovel().getNumeroParcelamento() != null) 
				{
					parcelamentoDebitoActionForm.setParcelamento(""
							+ dadosImovel.getImovel().getNumeroParcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamento() != null) 
				{
					parcelamentoDebitoActionForm.setReparcelamento(""
							+ dadosImovel.getImovel().getNumeroReparcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos() != null) 
				{
					parcelamentoDebitoActionForm.setReparcelamentoConsecutivo(""
							+ dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos());
				}
				// Manda a colecao pelo request
				httpServletRequest.setAttribute("imovelPesquisado",
						imovelPesquisado);
				String enderecoFormatado = "";
				try {
					enderecoFormatado = fachada.pesquisarEnderecoFormatado(new Integer(codigoImovel));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ControladorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				httpServletRequest.setAttribute("enderecoFormatado",enderecoFormatado);
				FiltroParcelamento filtroParcelamento = new FiltroParcelamento();

				filtroParcelamento.adicionarParametro(new ParametroSimples(
							FiltroParcelamento.IMOVEL_ID, codigoImovel));

				filtroParcelamento
						.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");

				Collection<Parcelamento> colecaoParcelamento = fachada.pesquisar(filtroParcelamento, Parcelamento.class.getName() );
				
				if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
					
					Iterator<Parcelamento> iterParcelamento = colecaoParcelamento.iterator();
					while (iterParcelamento.hasNext()) {
						Parcelamento parcelamento = (Parcelamento) iterParcelamento.next();
						obterValorEntradaComDesconto(parcelamento);
					}
					
					httpServletRequest.setAttribute("colecaoParcelamento", colecaoParcelamento);
				}else{
					if (colecaoParcelamento == null || colecaoParcelamento.isEmpty()){
						throw new ActionServletException("atencao.parcelamento.inexistente");
		        	}
				}
			}
		}
		//httpServletRequest.setAttribute("ParcelamentoDebitoActionForm",parcelamentoDebitoActionForm);
		return retorno;
	}
	

	private void obterValorEntradaComDesconto(Parcelamento parcelamento){
		
		BigDecimal valorEntradaComDesconto = parcelamento.getValorEntrada();
		
		BigDecimal valorDescontoEntrada = Fachada.getInstancia().
			pesquisarDescontoParaEntradaParcelamentoCobrancaDocumento(parcelamento.getId());
		
		if(valorEntradaComDesconto != null && valorDescontoEntrada != null){
			valorEntradaComDesconto = valorEntradaComDesconto.subtract(valorDescontoEntrada);
		}
		
		parcelamento.setValorDescontoEntrada(valorDescontoEntrada);
		parcelamento.setValorEntradaComDesconto(valorEntradaComDesconto);
	}
}
