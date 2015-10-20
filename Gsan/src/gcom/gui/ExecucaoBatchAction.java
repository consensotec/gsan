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
package gcom.gui;

import gcom.fachada.FachadaBatch;
import gcom.micromedicao.Rota;
import gcom.util.email.ErroEmailException;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExecucaoBatchAction extends GcomAction {

public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws ErroEmailException {

    ActionForward retorno = actionMapping.findForward("telaSucesso");

	String casoUso = httpServletRequest.getParameter("casoUso");

		 if ("UC0302".equals(casoUso)) {
			// Fernanda
			Collection rotas = new ArrayList();

			Rota rota = new Rota();
			rota.setId(3);

			rotas.add(rota);

			// Integer indicadorGeracaoMulta = 1;

			// Integer indicadorGeracaoJuros = 1;

			// Integer indicadorGeracaoAtualizacao = 1;

			/*
			 * try { Collection gerarDados = Fachada.getInstancia().
			 * gerarDebitosACobrarDeAcrescimosPorImpontualidade(rotas,
			 * indicadorGeracaoMulta, indicadorGeracaoJuros,
			 * indicadorGeracaoAtualizacao); } catch (ControladorException e) {
			 */
		} else if ("UC0275".equals(casoUso)) {
			// Toscano
			// FachadaBatch.getInstancia().gerarResumoLigacoesEconomias();

		} else if ("UC0209".equals(casoUso)) {
			// Toscano
			// Integer anoMes = null;

			// if (httpServletRequest.getParameter("anoMes") != null) {
			// anoMes = new Integer(httpServletRequest.getParameter("anoMes")) ;
			// }

			Collection colecaoRotas = new ArrayList();
			String idRotas = httpServletRequest.getParameter("idRotas");
			if (idRotas != null) {
				String[] idContaArray = idRotas.split(",");
				for (int i = 0; i < idContaArray.length; i++) {
					Rota rota = new Rota();
					Integer id = new Integer(idContaArray[i]);
					rota.setId(id);
					colecaoRotas.add(rota);
				}
			}

			// Fachada.getInstancia().gerarTaxaEntregaDeContaEmOutroEndereco(colecaoRotas,
			// anoMes);

		} else if ("UC0341".equals(casoUso)) {
			// Toscano
			// FachadaBatch.getInstancia().gerarResumoSituacaoEspecialFaturamento();

		} else if ("UC0346".equals(casoUso)) {
			// Toscano
			FachadaBatch.getInstancia().gerarResumoSituacaoEspecialCobranca();

		} else if ("UC0335".equals(casoUso)) {

			// FachadaBatch.getInstancia().gerarResumoPendencia();

		} else if ("UC0276".equals(casoUso)) {
			// FachadaBatch.getInstancia().encerrarArrecadacaoMes();
		} else if ("UC0341".equals(casoUso)) {
			throw new ActionServletException("n�o implementado");
		} else if ("UC0300".equals(casoUso)) {

			FachadaBatch.getInstancia().classificarPagamentosDevolucoes();

		} else if ("UC0301".equals(casoUso)) {
			// Pedro
			// FachadaBatch.getInstancia().gerarDadosDiariosArrecadacao();
		} else if ("UC0352".equals(casoUso)) {
			// throw new ActionServletException("n�o implementado");

			// execucaoBatchAction?casoUso=UC0343&idContas=1,2,3,1,2,1
			/*
			 * Collection colecaoContas = new ArrayList(); String idContas =
			 * httpServletRequest.getParameter("idContas"); String[]
			 * idContaArray = idContas.split(","); for(int
			 * i=0;i<idContaArray.length;i++){ Conta conta = new Conta();
			 * Integer idConta = new Integer(idContaArray[i]);
			 * conta.setId(idConta); colecaoContas.add(conta); }
			 */
			// Collection colecaoContas =
			// FachadaBatch.getInstancia().pesquisarIdsTodasConta();
			// FachadaBatch.getInstancia().emitirContas(colecaoContas);
		} else if ("UC0348".equals(casoUso)) {
			Integer anoMes = null;

			if (httpServletRequest.getParameter("anoMes") != null) {
				anoMes = new Integer(httpServletRequest.getParameter("anoMes"));
				FachadaBatch.getInstancia()
						.gerarLancamentoContabeisArrecadacao(anoMes);
			}
		} else if ("UC0342".equals(casoUso)) {

		} else if ("UC0342".equals(casoUso)) {
			throw new ActionServletException("n�o implementado");
		} else if ("UC0349".equals(casoUso)) {

			// FachadaBatch.getInstancia().emitirDocumentoCobranca();

		} else if ("UC0320".equals(casoUso)) {
			// Pedro
			FachadaBatch.getInstancia().gerarFaturaClienteResponsavel(1);
		} else if ("UC0321".equals(casoUso)) {
			// Pedro
			// FachadaBatch.getInstancia().emitirFaturaClienteResponsavel();

		} else if ("UC0343C".equals(casoUso)) {
			FachadaBatch.getInstancia().gerarResumoAnormalidadeConsumo();
		} else if ("UC0343".equals(casoUso)) {
			FachadaBatch.getInstancia().gerarResumoAnormalidadeLeitura();
		} else if ("UC0213".equals(casoUso)) {
			// Fernanda
			FachadaBatch.getInstancia()
					.desfazerParcelamentosPorEntradaNaoPaga();
		} else if ("UC1111".equals(casoUso)) {
			Rota rota = new Rota();
			rota.setId(1043);
			// Fachada.getInstancia().gerarDadosPorLeituraConvencional(rota,200607,1);
		} else {
			throw new ActionServletException("Caso de uso n�o passado");
		}

/*
 * struts-execucaoBatch.xml
 * 
 * gerarResumoPendenciaAction.do gerarDebitosACobrarAction.do
 * gerarResumoAnormalidadeAction.do
 * 
 * UC0302 - Gerar D�bitos a Cobrar de Acr�scimos por Impontualidade Fernanda
 * UC0275 - Gerar Resumo das Liga��es/Economias Toscano UC0209 - Gerar Taxa de
 * Entrega em Outro Endere�o Toscano UC0341 - Gerar Resumo da Situa��o Especial
 * de Faturamento Toscano UC0346 - Gerar Resumo da Situa��o Especial de Cobran�a
 * Toscano UC0335 - Gerar Resumo da Pend�ncia Roberta UC0276 - Encerrar a
 * Arrecada��o do M�s Pedro UC0348 - Gerar Lan�amentos Cont�beis da Arrecada��o
 * Santos UC0300 - Classificar Pagamentos e Devolu��es Rossiter UC0301 - Gerar
 * Dados Di�rios da Arrecada��o Pedro UC0343 - Gerar Resumo das Anormalidades.
 * Fl�vio UC0352 - Emitir Contas S�vio UC0349 - Emitir Documento de Cobran�a
 * Rossiter UC0320 - Gerar Fatura de Cliente Respons�vel Pedro UC0321 - Emitir
 * Fatura de Cliente Respons�vel Pedro
 * 
 */
	
	httpServletRequest.setAttribute("labelPaginaAtualizacao", "");
	httpServletRequest.setAttribute("caminhoAtualizarRegistro", "");

	httpServletRequest.setAttribute("labelPaginaSucesso", "Sucesso na execu��o do Batch do caso de uso " + casoUso);
	httpServletRequest.setAttribute("mensagemPaginaSucesso", "");
	httpServletRequest.setAttribute("caminhoFuncionalidade", "");

	 return retorno;
    }}