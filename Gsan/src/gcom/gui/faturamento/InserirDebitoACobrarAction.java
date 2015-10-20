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
package gcom.gui.faturamento;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaForma;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir D�bito A Cobrar do Imovel
 * 
 * @author Rafael Santos
 * @since 21/12/2005 [UC0183] Inserir D�bito a Cobrar
 * 
 */
public class InserirDebitoACobrarAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		if ( "confirmaEncerramentoRA".equals( httpServletRequest.getParameter("tipoRelatorio") ) ){
            sessao.setAttribute( "confirmaEncerramentoRA", httpServletRequest.getParameter( "confirmado" ) );
        }
		
		String valorConfirmacaoEncerramentoRA = (String) sessao.getAttribute( "confirmaEncerramentoRA" );
		
		// Inst�ncia do formul�rio que est� sendo utilizado
		InserirDebitoACobrarActionForm inserirDebitoACobrarActionForm = (InserirDebitoACobrarActionForm) actionForm;
		
		Imovel imovel = null;
		if (sessao.getAttribute("imovelPesquisado") != null) {
			imovel = (Imovel) sessao.getAttribute("imovelPesquisado");
		}
		
		Integer numeroPrestacoes = new Integer(inserirDebitoACobrarActionForm
				.getNumeroPrestacoes().replace(',', '.'));
		/*String valorTotalServicoAParcelar = inserirDebitoACobrarActionForm
		 .getValorTotalServicoAParcelar();*/
		BigDecimal valorTotalServico = Util
		.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
				.getValorTotalServico());
		BigDecimal percentualAbatimento = null;
		
		if (inserirDebitoACobrarActionForm.getPercentualAbatimento() != null
				&& !inserirDebitoACobrarActionForm.getPercentualAbatimento()
				.equals("")) {
			
			percentualAbatimento = Util
			.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
					.getPercentualAbatimento());
		} else {
			percentualAbatimento = new BigDecimal("0.00");
		}
		
		BigDecimal valorEntrada = null;
		if (inserirDebitoACobrarActionForm.getValorEntrada() != null
				&& !inserirDebitoACobrarActionForm.getValorEntrada().equals("")) {
			
			valorEntrada = Util
			.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
					.getValorEntrada());
		}
		
		String idDebitoTipo = inserirDebitoACobrarActionForm.getIdTipoDebito();
		
		// [FS0008] Verificar exist�ncia de d�bito a cobrar para o registro de
		// atendimento - valida se o d�bito j� estiver associado ao RA passado
		//mostra uma mensagem avisando que o d�bito a cobrar j� foi cadastrado para esse registro de atendimento
		if ((idDebitoTipo != null && !idDebitoTipo.equals(""))
				&& (inserirDebitoACobrarActionForm.getRegistroAtendimento() != null && !inserirDebitoACobrarActionForm
						.getRegistroAtendimento().equals(""))) {
			
			FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
			filtroDebitoACobrar.adicionarParametro(new ParametroSimples(
					FiltroDebitoACobrar.ID_REGISTRO_ATENDIMENTO,
					inserirDebitoACobrarActionForm.getRegistroAtendimento()));
			
			filtroDebitoACobrar.adicionarParametro(new ParametroSimples(
					FiltroDebitoACobrar.DEBITO_TIPO_ID, idDebitoTipo));
			
			filtroDebitoACobrar
			.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
					DebitoCreditoSituacao.CANCELADA));
			
			Collection colecaoDebitoACobrar = fachada.pesquisar(
					filtroDebitoACobrar, DebitoACobrar.class.getName());
			
			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty() && (valorConfirmacaoEncerramentoRA == null || "".equals(valorConfirmacaoEncerramentoRA))) {
				throw new ActionServletException("atencao.debito_a_cobrar.ja.cadastrado","exibirInserirDebitoACobrarAction.do",new Exception(),"");
			}
		}
		
		// validar debito tipo
		if ((inserirDebitoACobrarActionForm.getOrdemServico() != null && !inserirDebitoACobrarActionForm
				.getOrdemServico().equals(""))
				&& (idDebitoTipo != null && !idDebitoTipo.equals(""))) {
			
			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(
					FiltroOrdemServico.ID, inserirDebitoACobrarActionForm
					.getOrdemServico()));
			
			filtroOrdemServico
			.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.DEBITO_TIPO);
			
			Collection colecaoOrdemServico = fachada.pesquisar(
					filtroOrdemServico, OrdemServico.class.getName());
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {
				
				OrdemServico ordemServico = (OrdemServico) colecaoOrdemServico
				.iterator().next();
				if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
					idDebitoTipo = ordemServico.getServicoTipo()
					.getDebitoTipo().getId().toString();
				}
			}
		}
		
		DebitoACobrar debitoACobrar = new DebitoACobrar();
		debitoACobrar.setGeracaoDebito(new Date());
		
		debitoACobrar.setValorDebito(Util
				.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
						.getValorTotalServicoAParcelar()));
		debitoACobrar.setNumeroPrestacaoDebito(new Short(
				inserirDebitoACobrarActionForm.getNumeroPrestacoes())
				.shortValue());
		
		debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));
		debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial()
				.getCodigo());
		debitoACobrar.setNumeroQuadra(new Integer(imovel.getQuadra()
				.getNumeroQuadra()));
		debitoACobrar.setNumeroLote(imovel.getLote());
		debitoACobrar.setNumeroSubLote(imovel.getSubLote());
		BigDecimal valorTaxa = new BigDecimal("0.00");
		
		if (inserirDebitoACobrarActionForm.getTaxaJurosFinanciamento() != null
				&& !inserirDebitoACobrarActionForm.getTaxaJurosFinanciamento()
				.equalsIgnoreCase("")) {
			
			valorTaxa = Util
			.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
					.getTaxaJurosFinanciamento());
		}
		debitoACobrar.setPercentualTaxaJurosFinanciamento(valorTaxa);
		
		// registro de atendimento
		RegistroAtendimento registroAtendimento = null;
		if (inserirDebitoACobrarActionForm.getRegistroAtendimento() != null
				&& !inserirDebitoACobrarActionForm.getRegistroAtendimento()
				.trim().equals("")) {
			registroAtendimento = new RegistroAtendimento();
			registroAtendimento.setId(new Integer(
					inserirDebitoACobrarActionForm.getRegistroAtendimento()));
		}
		
		debitoACobrar.setRegistroAtendimento(registroAtendimento);
		
		// ordem de servico
		if (inserirDebitoACobrarActionForm.getOrdemServico() != null
				&& !inserirDebitoACobrarActionForm.getOrdemServico().equals("")) {
			
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(new Integer(inserirDebitoACobrarActionForm
					.getOrdemServico()));
			
			debitoACobrar.setOrdemServico(ordemServico);
		} else {
			debitoACobrar.setOrdemServico(null);
		}
		
		debitoACobrar.setImovel(imovel);
		debitoACobrar.setQuadra(imovel.getQuadra());
		debitoACobrar.setLocalidade(imovel.getLocalidade());
		debitoACobrar.setAnoMesReferenciaContabil(Util.formatarMesAnoComBarraParaAnoMes(
			inserirDebitoACobrarActionForm.getMesAnoInicialDaCobranca()));
		
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		/*filtroDebitoTipo
		 .adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoTipo.FINANCIAMENTO_TIPO);*/
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.ID, idDebitoTipo));
		/*filtroDebitoTipo
		 .adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoTipo.LANCAMENTO_ITEM_CONTABIL_ID);*/
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		
		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
				DebitoTipo.class.getName());
		DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator()
		.next();
		debitoTipo.setId(new Integer(idDebitoTipo));
		
		/*
		 * Autor: Hugo Leonardo
		 * Data: 08/10/2010
		 * CRC_4540
		 * Analista: Ana Cristina
		 * 
		 * */
		
		// [FS0006] - Verificar tipo de financaimento do tipo de d�bito
		if (debitoTipo.getFinanciamentoTipo().getIndicadorInclusao().shortValue() == FinanciamentoTipo.INDICADOR_INCLUSAO_NAO.shortValue() 
				|| debitoTipo.getIndicadorGeracaoAutomatica().shortValue() == 1) {
			
			throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.nao.permite.inserir.debito_a_cobrar",
					debitoTipo.getFinanciamentoTipo().getDescricao(),
					debitoTipo.getDescricao());
		}
		
		// obter o financiamento tipo do tipo de debito
		FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(
				FiltroFinanciamentoTipo.ID, debitoTipo.getFinanciamentoTipo()
				.getId()));
		
		Collection colecaoFinaciamentoTipo = fachada.pesquisar(
				filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
		
		FinanciamentoTipo financiamentoTipo = null;
		if (colecaoFinaciamentoTipo != null
				&& !colecaoFinaciamentoTipo.isEmpty()) {
			financiamentoTipo = (FinanciamentoTipo) colecaoFinaciamentoTipo
			.iterator().next();
		}
		
		debitoACobrar.setFinanciamentoTipo(financiamentoTipo);
		debitoACobrar.setDebitoTipo(debitoTipo);
		debitoACobrar.setLancamentoItemContabil(debitoTipo
				.getLancamentoItemContabil());
		
		CobrancaForma cobrancaForma = new CobrancaForma();
		cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);
		debitoACobrar.setCobrancaForma(cobrancaForma);
		debitoACobrar.setUltimaAlteracao(new Date());
		
		/**
		 * alterado por pedro alexandre dia 21/11/2006 Recupera o usu�rio logado
		 * para passar no met�do de inserir d�bito a cobrar para verificar se o
		 * usu�rio tem abrang�ncia para inserir um d�bito a cobrar para o im�vel
		 * informado.
		 */
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		 		
		
		//De acordo com o UC0183 ap�s o d�bito ter sido inclu�do no sistema, se o d�bito a cobrar
		//tiver usado um RA, � preciso perguntar ap�s a inclus�o do d�bito - a qual ocorreu na chamada
		//do m�todo acima se o usu�rio deseja encerrar o registro de atendimento
		
		//verifica se o objeto que representa o d�bito a cobrar a ser incluso � nulo
		if(debitoACobrar != null ){
			//verifica se o d�bito a cobrar foi incluso associado a um RA para realizar as valida��es
			//do UC0183 descritas acima
			if(valorConfirmacaoEncerramentoRA == null || "".equals(valorConfirmacaoEncerramentoRA) ){
				fachada.inserirDebitoACobrar(numeroPrestacoes, debitoACobrar,
						valorTotalServico, imovel, percentualAbatimento, valorEntrada,
						usuarioLogado);
				
				//caso o registro de atendimento do debito a cobrar seja diferente de nulo
				if(debitoACobrar.getRegistroAtendimento() != null){
					//caso o registro de atendimento da ordem de servi�o do debito a cobrar seja diferente de nulo
					
						httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/inserirDebitoACobrarAction.do");
						httpServletRequest.setAttribute("cancelamento", "TRUE");
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao2", "N�o");
						httpServletRequest.setAttribute( "tipoRelatorio", "confirmaEncerramentoRA");
						
						
						//monta a Pagina de confirma��o do encerramento do RA da OS caso a OS esteja associada a um RA					
						return montarPaginaConfirmacao("atencao.encerrar_RA_utilizado_no_inserir_debito_a_cobrar",
								httpServletRequest, actionMapping);
					
				}
				
			}else if(valorConfirmacaoEncerramentoRA.equals("ok")){
				//Se entrou aqui, � porque o usuario clicou em Sim, para o encerramento do Registro de Atendimento
				//associado � ordem de servi�o, dessa forma, esse c�digo, trata todas as quest�es pertinentes
				//ao encerramento do registro e por fim, encerra o RA
				
				//Pega o registro de atendimento do d�bito a cobrar para ser encerrado
				RegistroAtendimento ra = debitoACobrar.getRegistroAtendimento();
				
				AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
				atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
				atendimentoMotivoEncerramento.setIndicadorExecucao(AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM);
				
				ra.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
				ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
				
				ra.setDataEncerramento(new Date());
				
				ra.setObservacao("ENCERRADO NA INCLUS�O DO D�BITO A COBRAR");
				ra.setParecerEncerramento("RA ENCERRADO NA INCLUS�O DO D�BITO A COBRAR");
				
				ra.setUltimaAlteracao(new Date());
				
				//cria o objeto para a inser��o do registro de atendimento
				//unidade
				RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
				registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
				
				if (usuarioLogado.getUnidadeOrganizacional() != null && 
						!usuarioLogado.getUnidadeOrganizacional().equals("")) {
					
					registroAtendimentoUnidade.setUnidadeOrganizacional(
							usuarioLogado.getUnidadeOrganizacional());
				}
				registroAtendimentoUnidade.setUsuario(usuarioLogado);
				registroAtendimentoUnidade.setUltimaAlteracao(new Date());
				
				AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
				atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
				
				registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
				
				//booleano false para n�o gerar um novo RA
				Boolean confirmadoGeracaoNovoRA = false;
				
				//encerra o RA		
				
				fachada.encerrarRegistroAtendimento(
							ra,
							registroAtendimentoUnidade, 
							usuarioLogado, 
							null, 
							null, 
							null, 
							null, 
							confirmadoGeracaoNovoRA,"exibirInserirDebitoACobrarAction.do?menu=sim",true);					
				

			}
			
		}
		
		if (sessao.getAttribute("imovelPesquisado") != null) {
			sessao.removeAttribute("imovelPesquisado");
		}
		
		/*
		 * montarPaginaSucesso(httpServletRequest, "D�bito a cobrar " +
		 * debitoTipo.getDescricao() +" para o im�vel " + imovel.getId() +"
		 * inclu�da com sucesso", "Inserir outro D�bito a Cobrar",
		 * "exibirInserirDebitoACobrarAction.do?menu=sim");
		 */
		
		// 6.7 caso o valor da enrada tenha sido informadao
		if (inserirDebitoACobrarActionForm.getValorEntrada() != null
				&& !inserirDebitoACobrarActionForm.getValorEntrada().equals("")) {
			
			StringBuffer parametros = new StringBuffer("?menu=sim");
			
			parametros.append("&idImovel=" + imovel.getId());
			
			if (debitoACobrar.getOrdemServico() != null) {
				parametros.append("&ordemServico="
						+ debitoACobrar.getOrdemServico().getId());
			} else if(debitoACobrar.getRegistroAtendimento() != null) {
				parametros.append("&registroAtendimento="
						+ registroAtendimento.getId());
			}
			
			parametros.append("&idTipoDebito=" + idDebitoTipo);
			parametros.append("&dataVencimento="
					+ Util.formatarData(new Date()));
			parametros.append("&valorDebito="
					+ inserirDebitoACobrarActionForm.getValorEntrada());
			
			montarPaginaSucesso(httpServletRequest, "D�bito a cobrar "
					+ debitoTipo.getDescricao() + " para o im�vel "
					+ imovel.getId() + " inclu�do com sucesso",
					"Inserir outro D�bito a Cobrar",
					"exibirInserirDebitoACobrarAction.do?menu=sim",
					"exibirManterDebitoACobrarAction.do?idRegistroInseridoManter="
					+ imovel.getId(),
					"Cancelar D�bito(s) a Cobrar do im�vel " + imovel.getId(),
					"Inserir Guia de Pagamento",
					"exibirInserirGuiaPagamentoAction.do"
					+ parametros.toString());
			
		} else {
			
			montarPaginaSucesso(httpServletRequest, "D�bito a cobrar "
					+ debitoTipo.getDescricao() + " para o im�vel "
					+ imovel.getId() + " inclu�do com sucesso",
					"Inserir outro D�bito a Cobrar",
					"exibirInserirDebitoACobrarAction.do?menu=sim",
					"exibirManterDebitoACobrarAction.do?idRegistroInseridoManter="
					+ imovel.getId(),
					"Cancelar D�bito(s) a Cobrar do im�vel " + imovel.getId());
			
		}
		
		return retorno;
	}
	
}
