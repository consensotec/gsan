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
package gsan.gui.cadastro.imovel;

import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.credito.CreditoARealizarHistorico;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.faturamento.debito.DebitoACobrarHistorico;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0473] Consultar Imovel
 * Metodo da 4� Aba - Hist�rico de  Faturamento
 * 
 * @author Rafael Santos
 * @since 13/09/2006
 * 
 */
public class ExibirConsultarImovelHistoricoFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = getSessao(httpServletRequest);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		if(isLimparDadosTela(httpServletRequest)){

			httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

			limparFormSessao(consultarImovelActionForm, sessao);

		}else if( isImovelInformadoTelaHistoricoFaturamento(consultarImovelActionForm)
				|| isImovelInformadoOutraTela(sessao) ){

			consultarImovelActionForm.setIdImovelHistoricoFaturamento(
					definirIdImovelASerPesquisado(consultarImovelActionForm, sessao, httpServletRequest));

			Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm, sessao);

	        //deve ser chamado antes dos novos valores da sess�o serem setados
			boolean imovelNovoPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);

			if (imovel != null) {
				

				sessao.setAttribute("imovelDadosHistoricoFaturamento", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());

				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

				consultarImovelActionForm.setIdImovelHistoricoFaturamento(imovel.getId().toString());

				if(imovelNovoPesquisado){

					httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

					setarDadosImovelNoFormESessao(consultarImovelActionForm, imovel, sessao, fachada);

				}

			} else {
				limparFormSessao(consultarImovelActionForm, sessao);
				
				httpServletRequest.setAttribute("idImovelHistoricoFaturamentoNaoEncontrado", "true");

				consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento("IM�VEL INEXISTENTE");
			}

		}else{
        	String idImovelAux = consultarImovelActionForm.getIdImovelHistoricoFaturamento();

         	httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

            limparFormSessao(consultarImovelActionForm, sessao);

        	consultarImovelActionForm.setIdImovelHistoricoFaturamento(idImovelAux);
		}

		return actionMapping.findForward("consultarImovelHistoricoFaturamento");
	}

	/**
	 * Esse m�todo seta os dados necess�rios do Imovel
	 * no form e alguns na sess�o (cole��es).
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosImovelNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, 
			Imovel imovel,HttpSession sessao, Fachada fachada) {

		consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento(Fachada.getInstancia()
				.pesquisarInscricaoImovelExcluidoOuNao(new Integer(
						consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim())));

		consultarImovelActionForm.setSituacaoAguaHistoricoFaturamento(
				imovel.getLigacaoAguaSituacao().getDescricao());

		consultarImovelActionForm
			.setSituacaoEsgotoHistoricoFaturamento(imovel
				.getLigacaoEsgotoSituacao().getDescricao());
		
		// pesquisar os par�metros do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Indicador Valida CPF/CNPJ
		if(sistemaParametro.getIndicadorValidaCpfCnpj() != null){
			consultarImovelActionForm.setIndicadorValidaCPFCNPJ(String.valueOf(sistemaParametro.getIndicadorValidaCpfCnpj()));
		}
		
		Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( imovel.getId() );
		
		if(clienteUsuario != null){
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado(String.valueOf(clienteUsuario.getIndicadorValidaCpfCnpj()));
		}else{
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
		}

		sessao.setAttribute("colecaoContaImovel", Fachada.getInstancia().consultarContasImovel(imovel.getId()));
		sessao.setAttribute("colecaoContaHistoricoImovel", Fachada.getInstancia().consultarContasHistoricosImovel(imovel.getId()));

		sessao.setAttribute("colecaoDebitoACobrarImovel", Fachada.getInstancia().obterDebitoACobrarImovel(imovel.getId())); 
		sessao.setAttribute("colecaoDebitoACobrarHistoricoImovel", Fachada.getInstancia().obterDebitoACobrarHistoricoImovel(imovel.getId())); 

		sessao.setAttribute("colecaoCreditoARealizarImovel", Fachada.getInstancia().obterCreditoARealizarImovel(imovel.getId())); 
		sessao.setAttribute("colecaoCreditoARealizarHistoricoImovel", Fachada.getInstancia().obterCreditoARealizarHistoricoImovel(imovel.getId())); 

		sessao.setAttribute("colecaoGuiaPagamentoImovel", Fachada.getInstancia().obterGuiaPagamentoImovel(imovel.getId())); 
		sessao.setAttribute("colecaoGuiaPagamentoHistoricoImovel", Fachada.getInstancia().obterGuiaPagamentoHistoricoImovel(imovel.getId())); 

		setarTamanhoColacoesSessao(sessao);
	}

	/**
	 *Esse m�todo limpa todos os atributos do form
	 *e os atributos na sess�o 
	 *que s�o usados pelo action e/ou jsp.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		sessao.removeAttribute("idImovelPrincipalAba");
		sessao.removeAttribute("imovelDadosHistoricoFaturamento");

		sessao.removeAttribute("colecaoContaImovel");
		sessao.removeAttribute("colecaoContaHistoricoImovel");
		sessao.removeAttribute("colecaoDebitoACobrarImovel"); 
		sessao.removeAttribute("colecaoDebitoACobrarHistoricoImovel"); 
		sessao.removeAttribute("colecaoCreditoARealizarImovel"); 
		sessao.removeAttribute("colecaoCreditoARealizarHistoricoImovel");
		sessao.removeAttribute("colecaoGuiaPagamentoImovel"); 
		sessao.removeAttribute("colecaoGuiaPagamentoHistoricoImovel");
		sessao.removeAttribute("tamanhoColecaoDebitos"); 
		sessao.removeAttribute("tamanhoColecaoCreditos"); 
		sessao.removeAttribute("tamanhoColecaoGuias"); 

		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setSituacaoAguaHistoricoFaturamento(null);
		consultarImovelActionForm.setSituacaoEsgotoHistoricoFaturamento(null);
		consultarImovelActionForm.setConta(null);
		consultarImovelActionForm.setDescricaoImovel(null);

		consultarImovelActionForm.setIdImovelDadosComplementares(null);
		consultarImovelActionForm.setIdImovelDadosCadastrais(null);
		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setIdImovelDebitos(null);
		consultarImovelActionForm.setIdImovelPagamentos(null);
		consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
		consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
		consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
		consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
		consultarImovelActionForm.setImovIdAnt(null);
		consultarImovelActionForm.setIndicadorValidaCPFCNPJ("2");
		consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
	}

	/**
	 *Esse m�todo seta na sess�o os tamanhos das cole��es de 
	 *d�bito(debito a cobrar + debito historico) ,
	 *cr�dito(credito a realizar + credito historico)  e 
	 *guias de pagamento(guias + guias historico).
	 *
	 *@since --
	 *@author --
	 */
	private void setarTamanhoColacoesSessao(HttpSession sessao) {

		Collection<DebitoACobrarHistorico> colecaoDebitoACobrarHistoricoImovel = (Collection<DebitoACobrarHistorico>)sessao.getAttribute("colecaoDebitoACobrarHistoricoImovel");
		Collection<DebitoACobrar> colecaoDebitoACobrarImovel = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitoACobrarImovel");
		Collection<CreditoARealizarHistorico> colecaoCreditoARealizarHistoricoImovel = (Collection<CreditoARealizarHistorico>)sessao.getAttribute("colecaoCreditoARealizarHistoricoImovel");
		Collection<CreditoARealizar> colecaoCreditoARealizarImovel = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizarImovel");
		Collection<GuiaPagamentoHistorico> colecaoGuiaPagamentoHistoricoImovel =(Collection<GuiaPagamentoHistorico>)sessao.getAttribute("colecaoGuiaPagamentoHistoricoImovel");
		Collection<GuiaPagamento> colecaoGuiaPagamentoImovel =(Collection<GuiaPagamento>)sessao.getAttribute("colecaoGuiaPagamentoImovel");

		Integer tamanhoColecaoDebitos = 0;
		Integer tamanhoColecaoCreditos = 0;
		Integer tamanhoColecaoGuias = 0;

		if(colecaoDebitoACobrarImovel != null){
			tamanhoColecaoDebitos = colecaoDebitoACobrarImovel.size();
		}
		if(colecaoDebitoACobrarHistoricoImovel != null){
			tamanhoColecaoDebitos = tamanhoColecaoDebitos + colecaoDebitoACobrarHistoricoImovel.size();
		}

		if(colecaoCreditoARealizarImovel != null){
			tamanhoColecaoCreditos = colecaoCreditoARealizarImovel.size();
		}
		if(colecaoCreditoARealizarHistoricoImovel != null){
			tamanhoColecaoCreditos = tamanhoColecaoCreditos + colecaoCreditoARealizarHistoricoImovel.size();
		}

		if(colecaoGuiaPagamentoImovel != null){
			tamanhoColecaoGuias = colecaoGuiaPagamentoImovel.size();
		}
		if(colecaoGuiaPagamentoHistoricoImovel != null){
			tamanhoColecaoGuias = tamanhoColecaoGuias + colecaoGuiaPagamentoHistoricoImovel.size();
		}

		sessao.setAttribute("tamanhoColecaoDebitos", tamanhoColecaoDebitos); 
		sessao.setAttribute("tamanhoColecaoCreditos", tamanhoColecaoCreditos); 
		sessao.setAttribute("tamanhoColecaoGuias", tamanhoColecaoGuias); 

	}

	/**
	 * Caso o usu�rio tenha clicado no bot�o de limpar
	 * esse m�todo retornar� true.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 * Esse m�todo verifica se j� foi informado um im�vel em outra tela.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));		
	}

	/**
	 * Esse m�todo verifica se o im�vel foi informado na tela
	 * de Historico de Faturamento
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaHistoricoFaturamento(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelHistoricoFaturamento());
	}

	/**
	 * Esse m�todo retorna o id do im�vel a ser pesquisado,
	 * verificando se � o id possivelmente informado pelo usu�rio na tela 
	 * de Historico Faturamento ou se o id j� informado em uma outra tela.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {

		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");

		if( isImovelInformadoTelaHistoricoFaturamento(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){

			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
				return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
			return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelHistoricoFaturamento();
	}

	/**
	 * Consulta o Imovel com todas as informa��es necess�rias,
	 * ou simplesmetne pega o Imovel da sess�o caso o mesmo j�
	 * tenha sido pesquisado.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosHistoricoFaturamento") == null){
			imovel = Fachada.getInstancia().consultarImovelHistoricoFaturamento(new Integer(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosHistoricoFaturamento");

			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelHistoricoFaturamento(new Integer(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()));
			}
		}
		return imovel;
	}


	/**
	 * Esse m�todo retorna true se foi necess�rio consultar um novo imovel.
	 * Caso o im�vel seja o mesmo j� consultado anteriormente ele retorna false.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		if(sessao.getAttribute("imovelDadosHistoricoFaturamento") == null){
			return true;
		}

		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosHistoricoFaturamento");

		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()) ){
			return true;
		}

		return false;
	}

}