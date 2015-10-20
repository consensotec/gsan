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

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadadorContratoTarifa;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroMotivoCancelamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
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
 * [UC0507] ATUALIZAR CONTRATO DE ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 11/04/2007
 */

public class ExibirAtualizarContratoArrecadadorAction extends GcomAction {

	private Collection colecaoPesquisa;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarContratoArrecadador");

		AtualizarContratoArrecadadorActionForm atualizarContratoArrecadadorActionForm = (AtualizarContratoArrecadadorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		
		
		Fachada fachada = Fachada.getInstancia();

		String idContratoArrecadador = httpServletRequest
				.getParameter("idRegistroAtualizacao");

		if (idContratoArrecadador == null) {

			if (sessao.getAttribute("idRegistroAtualizacao") != null) {
				idContratoArrecadador = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			}

			if (idContratoArrecadador == null) {
				idContratoArrecadador = (String) httpServletRequest
						.getAttribute("idRegistroAtualizacao");
			}

		}else {
			sessao.setAttribute("idRegistroAtualizacao", idContratoArrecadador);
			sessao.setAttribute("i", true);
		}

		// Arrecadador
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		// Ordena filtro de arrecadador por id do cliente
		filtroArrecadador.setCampoOrderBy("cliente.nome");
		
		// Inclui a objeto de cliente no filtro de arrecadador
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		// Preenche colecao de arrecadador
		Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(
				filtroArrecadador, Arrecadador.class.getName());
		if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Arrecadador");
		} else {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			//FiltroCliente filtroCliente = new FiltroCliente();
			Iterator iteratorColecaoArrecadador = colecaoArrecadador.iterator();
			Cliente cliente = new Cliente();
			while (iteratorColecaoArrecadador.hasNext()) {
				Arrecadador arrecadador = (Arrecadador) iteratorColecaoArrecadador.next();
				clientes.add(arrecadador.getCliente());
				
				
			}
			
			sessao.setAttribute("colecaoClienteArrecadador", clientes);
		}

		Collection collectionArrecadadorContrato = (Collection) httpServletRequest
				.getAttribute("colecaoArrecadadorContrato");
		ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) Util
				.retonarObjetoDeColecao(collectionArrecadadorContrato);

		String idCliente = null;

		// ///////////////////// VALIDACAO DE CLIENTE ///////////////////
		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {

			// Cliente
			case 1:
				// Recebe o valor do campo bancoID do formul�rio.
				idCliente = atualizarContratoArrecadadorActionForm
						.getIdCliente();

				FiltroCliente filtroCliente1 = new FiltroCliente();

				filtroCliente1.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idCliente));

				filtroCliente1.adicionarParametro(new ParametroSimples(
						FiltroCliente.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Cliente
				colecaoPesquisa = fachada.pesquisar(filtroCliente1,
						Cliente.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

					// Limpa o campo clienteID do formul�rio
					atualizarContratoArrecadadorActionForm.setIdCliente("");
					atualizarContratoArrecadadorActionForm
							.setNomeCliente("Cliente inexistente.");
					httpServletRequest.setAttribute("existeCliente",
							"exception");

					httpServletRequest.setAttribute("nomeCampo", "clienteID");

				} else {

					Cliente objetoCliente = (Cliente) Util
							.retonarObjetoDeColecao(colecaoPesquisa);

					atualizarContratoArrecadadorActionForm.setIdCliente(String
							.valueOf(objetoCliente.getId()));
					atualizarContratoArrecadadorActionForm
							.setNomeCliente(objetoCliente.getDescricao());

					httpServletRequest.setAttribute("existeCliente", "valor");
					httpServletRequest.setAttribute("nomeCampo", "clienteID");
				}
				break;
			default:

				break;
			}
		}

		// Verificar se o n�mero do cliente n�o est� cadastrado
		if (idCliente != null && !idCliente.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));
			filtroCliente
					.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente == null || colecaoCliente.isEmpty()) {

				atualizarContratoArrecadadorActionForm.setIdCliente("");
				httpServletRequest.setAttribute("existeCliente", "exception");

				

			} else {

				Cliente cliente = (Cliente) Util
						.retonarObjetoDeColecao(colecaoCliente);

				// [FS0004]-Verificar se pessoa f�sica
				if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
						&& cliente.getClienteTipo()
								.getIndicadorPessoaFisicaJuridica().equals(
										new Short("2"))) {

					throw new ActionServletException(
							"atencao.cliente_arrecadador_pessoa_fisica");
				}

				atualizarContratoArrecadadorActionForm.setIdCliente(cliente
						.getId().toString());
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}
		}

		// ///////////////////// FIM VALIDACAO DE CLIENTE ///////////////////

		if (idContratoArrecadador != null
				&& !idContratoArrecadador.trim().equals("") &&

				Integer.parseInt(idContratoArrecadador) > 0) {

			FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();

			// Adiciona entidade estrangeira para carregamento do objeto
			// "CLIENTE"
			// (ou seja, em ARRECADADOR existe um atributo do tipo Cliente,
			// ent�o � preciso carregar o cliente)
			// o mesmo para Imovel.

			filtroArrecadadorContrato
					.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");
			filtroArrecadadorContrato
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.ID, idContratoArrecadador));
			Collection<ArrecadadorContrato> colecaoArrecadadorContrato = fachada
					.pesquisar(filtroArrecadadorContrato,
							ArrecadadorContrato.class.getName());

			if (colecaoArrecadadorContrato != null
					&& !colecaoArrecadadorContrato.isEmpty()) {
				arrecadadorContrato = (ArrecadadorContrato) Util
						.retonarObjetoDeColecao(colecaoArrecadadorContrato);
				atualizarContratoArrecadadorActionForm
						.setIdClienteCombo(arrecadadorContrato.getArrecadador()
								.getCliente().getId().toString());
				atualizarContratoArrecadadorActionForm
						.setIdArrecadador(arrecadadorContrato.getArrecadador()
								.getId().toString());
				
			}
		}

		atualizarContratoArrecadadorActionForm
				.setNumeroContrato(arrecadadorContrato.getNumeroContrato());

		ContaBancaria contaBancariaArrecadacao = arrecadadorContrato
				.getContaBancariaDepositoArrecadacao();
		if (contaBancariaArrecadacao != null) {
			String idContaBancaria = contaBancariaArrecadacao.getId()
					.toString();
			FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID, idContaBancaria));
			filtroContaBancaria
					.adicionarCaminhoParaCarregamentoEntidade("agencia");
			filtroContaBancaria
					.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");
			Collection coll = Fachada.getInstancia().pesquisar(
					filtroContaBancaria, ContaBancaria.class.getSimpleName());

			contaBancariaArrecadacao = (ContaBancaria) coll.iterator().next();
			atualizarContratoArrecadadorActionForm
					.setBcoArrecadadorConta(contaBancariaArrecadacao
							.getAgencia().getBanco().getId().toString());
			atualizarContratoArrecadadorActionForm
					.setAgArrecadadorConta(contaBancariaArrecadacao
							.getAgencia().getCodigoAgencia());
			atualizarContratoArrecadadorActionForm
					.setNumeroArrecadadorConta(contaBancariaArrecadacao
							.getNumeroConta());
		}

		ContaBancaria contaBancariaTarifa = arrecadadorContrato
				.getContaBancariaDepositoTarifa();
		if (contaBancariaTarifa != null) {
			String idContaBancaria = contaBancariaTarifa.getId().toString();
			FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID, idContaBancaria));
			filtroContaBancaria
					.adicionarCaminhoParaCarregamentoEntidade("agencia");
			filtroContaBancaria
					.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");
			Collection coll = Fachada.getInstancia().pesquisar(
					filtroContaBancaria, ContaBancaria.class.getSimpleName());

			contaBancariaTarifa = (ContaBancaria) coll.iterator().next();
			atualizarContratoArrecadadorActionForm
					.setBcoTarifaConta(contaBancariaTarifa.getAgencia()
							.getBanco().getId().toString());
			atualizarContratoArrecadadorActionForm
					.setAgTarifaConta(contaBancariaTarifa.getAgencia()
							.getCodigoAgencia());
			atualizarContratoArrecadadorActionForm
					.setNumeroTarifaConta(contaBancariaTarifa.getNumeroConta());
		}

		if (idCliente == null || idCliente.trim().equals("")) {
			atualizarContratoArrecadadorActionForm
					.setIdCliente(arrecadadorContrato.getCliente().getId()
							.toString());
			atualizarContratoArrecadadorActionForm
					.setNomeCliente(arrecadadorContrato.getCliente().getNome());
		}

		atualizarContratoArrecadadorActionForm
				.setIdConvenio(arrecadadorContrato.getCodigoConvenio());

		if (arrecadadorContrato.getIndicadorCobrancaIss() != null
				&& !arrecadadorContrato.getIndicadorCobrancaIss().toString()
						.trim().equals("")) {
			atualizarContratoArrecadadorActionForm
					.setIndicadorCobranca(arrecadadorContrato
							.getIndicadorCobrancaIss().toString());
		}
		
		//indicador Cobran�a Por Cliente Respos�vel
		if (arrecadadorContrato.getIndicadorCobParClienteResponsavel() != null
				&& !arrecadadorContrato.getIndicadorCobParClienteResponsavel().toString()
						.trim().equals("")) {
			atualizarContratoArrecadadorActionForm
					.setIndicadorCobParClienteResponsavel(arrecadadorContrato
							.getIndicadorCobParClienteResponsavel().toString());
		}
		// Data Contrato Inicio 
		if (arrecadadorContrato.getDataContratoInicio() != null) {
			atualizarContratoArrecadadorActionForm.setDtInicioContrato(Util
					.formatarData(arrecadadorContrato.getDataContratoInicio()));
		} 
		//Data Contrato Fim
		if (arrecadadorContrato.getDataContratoFim() != null) {
			atualizarContratoArrecadadorActionForm.setDtFimContrato(Util
					.formatarData(arrecadadorContrato.getDataContratoFim()));
		}
		//Data Contrato Encerramento
		if (arrecadadorContrato.getDataContratoEncerramento() != null) {
			atualizarContratoArrecadadorActionForm.setDataContratoEncerramento(Util
					.formatarData(arrecadadorContrato.getDataContratoEncerramento()));
		}
		
		// Motivo Cancelamento
		FiltroMotivoCancelamento filtroMotivoCancelamento = new FiltroMotivoCancelamento();

		// Ordena filtro de motivo cancelamento
		filtroMotivoCancelamento
				.setCampoOrderBy(FiltroMotivoCancelamento.DESCRICAO);

		// Preenche colecao de motivos
		Collection<FiltroMotivoCancelamento> colecaoFiltroMotivoCancelamento = fachada
				.pesquisar(filtroMotivoCancelamento,
						ContratoMotivoCancelamento.class.getName());

		if (colecaoFiltroMotivoCancelamento == null
				|| colecaoFiltroMotivoCancelamento.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Motivo Cancelamento");
		} else {
			if(arrecadadorContrato.getContratoMotivoCancelamento() != null
					&& arrecadadorContrato.getContratoMotivoCancelamento().getId() != null) {
				atualizarContratoArrecadadorActionForm
						.setContratoMotivoCancelamento(arrecadadorContrato.getContratoMotivoCancelamento().getId().toString());
			}
		
			sessao.setAttribute("colecaoFiltroMotivoCancelamento",
					colecaoFiltroMotivoCancelamento);
		}
		atualizarContratoArrecadadorActionForm
				.setTamanhoMaximoIdentificacaoImovel(arrecadadorContrato
						.getTamanhoMaximoIdentificacaoImovel().toString());
		sessao.setAttribute("arrecadadorContrato", arrecadadorContrato);

		/**
		 * Atualizar Arrecadador Contrato Tarifa
		 * @date 12/06/09
		 * @author Arthur Carvalho
		 */
		if (atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao() == null
				|| atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao().equals("")) {

			FiltroArrecadacaoForma filtroArrecadadorForma = new FiltroArrecadacaoForma();
			filtroArrecadadorForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);
			
			Collection colecaoArrecadacaoForma = fachada.pesquisar( filtroArrecadadorForma,
					ArrecadacaoForma.class.getName() );

			if (colecaoArrecadacaoForma == null || colecaoArrecadacaoForma.isEmpty()) {
				throw new ActionServletException(

				"atencao.pesquisa.nenhum_registro_tabela", null, "Forma de Arrecada��o");

			} else {

				sessao.setAttribute("colecaoFormaArrecadacao", colecaoArrecadacaoForma);
			}
		}
		
		
		//ArrayList colecaoArrecadadorContratoTarifaSelecionados ;
		ArrecadadorContratoTarifa arrecadadorContratoTarifa = new ArrecadadorContratoTarifa();
		
		ArrayList colecaoArrecadadorContratoTarifaSelecionados = new ArrayList();
		//Caso volte a funcionalidade e seja feito uma nova pesquisa, limpar as tarifas que ficam na sess�o
		//e n�o entrar novamente nesse metodo, a nao ser que seja a primeira vez que carregue a pagina.
		if ( sessao.getAttribute("menu") != null &&sessao.getAttribute("menu").equals("sim") ) {
			sessao.setAttribute("menu", "nao");
			sessao.setAttribute("colecaoArrecadadorContratoTarifaSelecionados", null);
		}
		if(  sessao.getAttribute("colecaoArrecadadorContratoTarifaSelecionados") == null &&
				httpServletRequest.getParameter("acao") == null ) {

			FiltroArrecadadorContratoTarifa filtroArrecadadorContratoTarifa = 
				new FiltroArrecadadorContratoTarifa();
			filtroArrecadadorContratoTarifa.adicionarParametro(
				new ParametroSimples(
					FiltroArrecadadorContratoTarifa.ARRECADADOR_CONTRATO_ID, 
					idContratoArrecadador));
			
			filtroArrecadadorContratoTarifa.adicionarCaminhoParaCarregamentoEntidade("arrecadacaoForma");
			filtroArrecadadorContratoTarifa.setCampoOrderBy("arrecadacaoForma.descricao");
			
			colecaoArrecadadorContratoTarifaSelecionados = 
				(ArrayList) fachada.pesquisar(filtroArrecadadorContratoTarifa, 
					ArrecadadorContratoTarifa.class.getName());
			
		} else {
			colecaoArrecadadorContratoTarifaSelecionados = 
				(ArrayList) sessao.getAttribute("colecaoArrecadadorContratoTarifaSelecionados");
		}
		//Forma de Arrecadacao
		if (atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao() != null
				&& !"-1".equals( atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao() ) ) {
			
			FiltroArrecadacaoForma filtroArrecadadorForma = new FiltroArrecadacaoForma();
			filtroArrecadadorForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO, atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao()));
			
			Collection colecaoArrecadacaoForma = fachada.pesquisar( filtroArrecadadorForma,
					ArrecadacaoForma.class.getName() );
			
			if (colecaoArrecadacaoForma != null && !colecaoArrecadacaoForma.isEmpty()) {
				ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoArrecadacaoForma);
				arrecadadorContratoTarifa.setArrecadacaoForma(arrecadacaoForma);
			}
			 
			
		}
		
		//Valor Tarifa
		BigDecimal valorTarifa = null;
		if (atualizarContratoArrecadadorActionForm.getValorTarifa() != null
				&& !"".equals( atualizarContratoArrecadadorActionForm.getValorTarifa() ) ) {
			valorTarifa =  Util.formatarMoedaRealparaBigDecimal( atualizarContratoArrecadadorActionForm.getValorTarifa() ) ;
			
			arrecadadorContratoTarifa.setValorTarifa( valorTarifa );
		}
		
		//Valor Tarifa Percentual
		BigDecimal valorTarifaPercentual = null;
		if ( atualizarContratoArrecadadorActionForm.getValorTarifaPercentual() != null &&
				!atualizarContratoArrecadadorActionForm.getValorTarifaPercentual().equals("")){
			
			valorTarifaPercentual = Util.formatarMoedaRealparaBigDecimal(atualizarContratoArrecadadorActionForm.getValorTarifaPercentual());
			arrecadadorContratoTarifa.setValorTarifaPercentual(valorTarifaPercentual);
			
		}
		
		//Numero de dias Float
		Short nmDiasFloat = null;
		if (atualizarContratoArrecadadorActionForm.getNumeroDiaFloat() != null
				&& !"".equals( atualizarContratoArrecadadorActionForm.getNumeroDiaFloat() ) ) {
			nmDiasFloat =  new Short( atualizarContratoArrecadadorActionForm.getNumeroDiaFloat() ) ;
			arrecadadorContratoTarifa.setNumeroDiaFloat(nmDiasFloat);
		}
		
		
        //Verifica se o usuario clicou no botao adicionar
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("adicionar") &&
        	!atualizarContratoArrecadadorActionForm.getFormaDeArrecadacao().equals("-1") &&
        	( !atualizarContratoArrecadadorActionForm.getValorTarifa().equals("") || 
                	!atualizarContratoArrecadadorActionForm.getValorTarifaPercentual().equals("") )
            &&!atualizarContratoArrecadadorActionForm.getNumeroDiaFloat().equals("") ) {
        	
	        	arrecadadorContratoTarifa.setUltimaAlteracao(new Date());
	 
	        	if ( colecaoArrecadadorContratoTarifaSelecionados != null ) {
		        	Iterator iteratorColecaoArrecadadorContratoTarifa = colecaoArrecadadorContratoTarifaSelecionados.iterator();
		    		ArrecadadorContratoTarifa contratoTarifa = null;
		    		
		    		if (atualizarContratoArrecadadorActionForm.getValorTarifaPercentual() != null && 
		    				!atualizarContratoArrecadadorActionForm.getValorTarifaPercentual().equals("")){
		    			
		    			//Valida��o do valor da tarifa percentual
			    		BigDecimal valorTarifaPerc = Util.formatarMoedaRealparaBigDecimal
			    			(atualizarContratoArrecadadorActionForm.getValorTarifaPercentual());
			    		//Variaveis para comparar valorTarifaPercentual
			    		BigDecimal igualZero = new BigDecimal(0);
			    		BigDecimal maiorQue100 = new BigDecimal(100);
			    		if (valorTarifaPerc.compareTo(igualZero) == 0){
			    			
			    			throw new ActionServletException("atencao.tarifa_invalida", null ,"Tarifa de Contrato" );
			    			
			    		}else if ( valorTarifaPerc.compareTo(maiorQue100) == 1){
			    			
			    			throw new ActionServletException("atencao.tarifa_invalida", null ,"Tarifa de Contrato" );
			    			
			    		}
		    			
		    		}
		
		    		//Valida se ja existe forma de arrecadacao
		    		while (iteratorColecaoArrecadadorContratoTarifa.hasNext()) {
		    			
		    			contratoTarifa = (ArrecadadorContratoTarifa) iteratorColecaoArrecadadorContratoTarifa.next();
		    			
		    			if ( arrecadadorContratoTarifa.getArrecadacaoForma().getId().intValue() == contratoTarifa.getArrecadacaoForma().getId().intValue() ) {
		    				throw new ActionServletException("atencao.forma_ja_cadastrada", null ,"Tarifa de Contrato" );
		    			} 
		        	
		    		}
	        	} else {
	        		colecaoArrecadadorContratoTarifaSelecionados = new ArrayList();
	        	}
				colecaoArrecadadorContratoTarifaSelecionados.add(arrecadadorContratoTarifa);
				
				atualizarContratoArrecadadorActionForm.setTamanhoColecao("" + colecaoArrecadadorContratoTarifaSelecionados.size());
				atualizarContratoArrecadadorActionForm.setNumeroDiaFloat("");
				atualizarContratoArrecadadorActionForm.setValorTarifa("");
				atualizarContratoArrecadadorActionForm.setValorTarifaPercentual("");
	    		atualizarContratoArrecadadorActionForm.setFormaDeArrecadacao("-1");
        }
        
        //Remover o Contrato Tarifa da Colecao
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("remover") ) {
        	int obj = new Integer(httpServletRequest.getParameter("id")).intValue();
        	
        	if (colecaoArrecadadorContratoTarifaSelecionados.size() >= obj) {
        		
        		colecaoArrecadadorContratoTarifaSelecionados.remove(obj-1);
        	}
        	
        }
        
        if ( httpServletRequest.getParameter("desfazer") != null &&
        		httpServletRequest.getParameter("desfazer").equals("S") ) {
        
        	FiltroArrecadadorContratoTarifa filtroArrecadadorContratoTarifa = 
				new FiltroArrecadadorContratoTarifa();
			filtroArrecadadorContratoTarifa.adicionarParametro(
				new ParametroSimples(
					FiltroArrecadadorContratoTarifa.ARRECADADOR_CONTRATO_ID, 
					idContratoArrecadador));
			
			filtroArrecadadorContratoTarifa.adicionarCaminhoParaCarregamentoEntidade("arrecadacaoForma");

			colecaoArrecadadorContratoTarifaSelecionados = 
				(ArrayList) fachada.pesquisar(filtroArrecadadorContratoTarifa, 
					ArrecadadorContratoTarifa.class.getName());
        }
        
        sessao.setAttribute("colecaoArrecadadorContratoTarifaSelecionados", colecaoArrecadadorContratoTarifaSelecionados );
		
        atualizarContratoArrecadadorActionForm.setFormaDeArrecadacao("-1");
		
		return retorno;
	}

}
