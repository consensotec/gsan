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
package gsan.gui.atendimentopublico;

import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.bean.ContaValoresHelper;
import gsan.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Calendar;
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
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 17/01/2007
 */
public class EmitirSegundaViaContaInternetAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirResultadoEmitirSegundaViaContaInternetAction");

		HttpSession sessao = httpServletRequest.getSession(true);
		
		//Identifica a sess�o criada para o usu�rio da segunda via
		sessao.setAttribute("loginUsuarioSessao", "USUARIO_SEGUNDA_VIA_INTERNET");

		if (sessao.getAttribute("acessoGeral") != null) {
			retorno = actionMapping.findForward("exibirResultadoEmitirSegundaViaContaInternetAcessoGeralAction");
		}

		//Action Base ExibirEfetuarParcelamentoDebitosProcesso1Action
		SistemaParametro sistemaParametro = (SistemaParametro) sessao.getAttribute("sistemaParametro");

		EmitirSegundaViaContaInternetActionForm form = 
			(EmitirSegundaViaContaInternetActionForm) actionForm;

		Integer matricula = new Integer(form.getMatricula());

		// Verificar existencia do im�vel
		Integer matriculaExistente = 
			this.getFachada().verificarExistenciaImovel(matricula);
		
		Collection colecaoLocalidadeElo = null;

		if (matriculaExistente == 1) {

			form.setMatricula(matricula.toString());
			
			Imovel imovel = 
				this.getFachada().pesquisarImovel(new Integer(matricula));
			
			
			Integer idLocalidade = imovel.getLocalidade().getId();
			colecaoLocalidadeElo = 
				this.pesquisarLocalidade(idLocalidade.toString());
			
			Localidade localidade = null;
			
			if(colecaoLocalidadeElo != null && !colecaoLocalidadeElo.isEmpty())
				
				localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeElo);
				
				//Localidade � o Elo
				if(localidade.getId().intValue() == localidade.getLocalidade().getId().intValue()){
					form.setElo(localidade.getDescricao());
				}else{
					//Localidade nao � o Elo
					colecaoLocalidadeElo = this.pesquisarLocalidade(localidade.getLocalidade().getId().toString());
					
					localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeElo);
					form.setElo(localidade.getDescricao());
				}
			
			/**
			 * Alterado por R�mulo Aur�lio
			 * Data: 11/05/2007
			 */
				
			BigDecimal totalContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");
			
			Short nDiasVencimentoCobranca = sistemaParametro.getNumeroDiasVencimentoCobranca();
			Date dataDebito = new Date();
			
			Calendar calendar = Calendar.getInstance();

			//Data Atual - Numero de dias vencimento Cobranca
			calendar.add(Calendar.DAY_OF_MONTH,-nDiasVencimentoCobranca.shortValue());
			dataDebito = calendar.getTime();			
			form.setDataDebito(Util.formatarData(dataDebito));
			
			// Ano mes Atual.
//			String ano;
//			String mes;
//
//			Date dataCorrente = new Date();
//			String dataCorrenteTexto = Util.formatarData(dataCorrente);
//			ano = dataCorrenteTexto.substring(6, 10);
//			mes = dataCorrenteTexto.substring(3, 5);

			String anoMesInicialReferenciaDebito = "198501";
			String anoMesFinalReferenciaDebito = "999912";

			//Date aux1 = dataInicioVencimentoDebito.getTime();
			Date aux1 = Util.converteStringParaDate("01/01/1985");
			
			//Date aux2 = dataFimVencimentoDebito.getTime();
			Date aux2 = Util.converteStringParaDate("31/12/9999");

			String tipoRelacao = "-1";

			ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = 
				(ObterDebitoImovelOuClienteHelper) 
					this.getFachada().obterDebitoImovelOuCliente(1, 
						form.getMatricula(), 
						null,
						new Short(tipoRelacao),
						anoMesInicialReferenciaDebito,
						anoMesFinalReferenciaDebito, 
						aux1, 
						aux2, 
						1, 
						1, 
						1,
						1, 
						1, 
						1, 
						1, 
						null,
						2);
			
			Collection colecaoContasValores = 
				obterDebitoImovelOuClienteHelper.getColecaoContasValores();
			
			if (colecaoContasValores == null || colecaoContasValores.isEmpty()) {
				throw new ActionServletException("atencao.imovel_sem_debitos");
			} else {
			
				Iterator colecaoContasValoresIterator = colecaoContasValores.iterator();
				
				while (colecaoContasValoresIterator.hasNext()) {
					ContaValoresHelper contaValoresHelper = 
						(ContaValoresHelper) colecaoContasValoresIterator.next();
					
					totalContas = totalContas.add(contaValoresHelper.getValorTotalConta());
					
					valorTotalAcrescimoImpontualidadeContas = 
						valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValores());
				}

				httpServletRequest.setAttribute("totalContas", totalContas);
				form.setValorDebito(Util.formatarMoedaReal(totalContas));
				//valor dos encargos so aparece para CAEMA
				//RM986 - Vivianne Sousa - 15/06/2011
				form.setValorEncargosACobrar(Util.formatarMoedaReal(valorTotalAcrescimoImpontualidadeContas));
			}
			
			httpServletRequest.setAttribute("colecaoContasValores",colecaoContasValores);

			form.setNomeCliente(this.getFachada().obterNomeCliente(matricula));
			
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = 
				this.getFachada().obterDebitoImovelOuCliente(
					1, // Indicador d�bito im�vel
			        matricula.toString(), // Matr�cula do im�vel
			        null, // C�digo do cliente
			        null, // Tipo de rela��o do cliento com o
			        // im�vel
			        "000101", // Refer�ncia inicial do d�bito
			        "999912", // Refer�ncia final do d�bito
			        Util.converteStringParaDate("01/01/0001"), // Inicio
			        // Vencimento
			        Util.converteStringParaDate("31/12/9999"), // Final
			        // Vencimento
			        1, // Indicador pagamento
			        1, // Indicador conta em revis�o
			        1, // Indicador d�bito a cobrar
			        1, // Indicador cr�dito a realizar
			        1, // Indicador notas promiss�rias
			        1, // Indicador guias de pagamento
			        1, // Indicador acr�scimos por impontualidade
			        null, // Indicador Contas
			        2);
			
			Collection colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();
			BigDecimal valorTotalDebitoACobrar = new BigDecimal("0.00");
			  
			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
				Iterator debitoACobrarValores = colecaoDebitoACobrar.iterator();
				     
				while (debitoACobrarValores.hasNext()) {
				      
					DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
					valorTotalDebitoACobrar = 
						valorTotalDebitoACobrar.add(debitoACobrar.getValorTotalComBonus());
				 }
			}
			
			//valor outros servi�os so aparece para CAEMA
			//RM986 - Vivianne Sousa - 15/06/2011
			form.setValorOutrosServicosACobrar(Util.formatarMoedaReal(valorTotalDebitoACobrar));
			
			// Acrescimos por Impotualidade
			BigDecimal retornoSoma = new BigDecimal("0.00");
			
			retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
			valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(retornoSoma);
			form.setDebitoACobrar(Util.formatarMoedaReal(valorTotalDebitoACobrar));
		} else {
			throw new ActionServletException("atencao.matricula.imovel.inexistente");
		}
		
		/*
		 * Colocado por Raphael Rossiter em 21/10/2008 - Analista: Rosana Carvalho
		 * 
		 * [FS0003] - Verificar se documento � v�lido
		 * [FS0004] - Cliente n�o associado ao documento
		 */
		String cpf = (String) httpServletRequest.getParameter("cpf");
		String cnpj = (String) httpServletRequest.getParameter("cnpj");
		
		this.getFachada().verificarDocumentoValidoEmissaoInternet(matricula, cpf, cnpj);
		
		return retorno;
	}
	
	public Collection pesquisarLocalidade(String idLocalidade){
		
		Collection colecaoLocalidadeElo = null;
		
		FiltroLocalidade filtrolocalidade = new FiltroLocalidade();
		filtrolocalidade.adicionarParametro(
			new ParametroSimples(
				FiltroLocalidade.ID,
				idLocalidade));
		
		colecaoLocalidadeElo = 
			this.getFachada().pesquisar(filtrolocalidade,
				Localidade.class.getName());
		
		return colecaoLocalidadeElo;
	}
}
