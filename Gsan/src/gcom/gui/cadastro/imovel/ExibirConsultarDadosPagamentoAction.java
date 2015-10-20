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
package gcom.gui.cadastro.imovel;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.RegistroCodigo;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0549] Consultar Dados de um Pagamento
 * 
 * @author Kassia Albuquerque
 * @created 28/06/2007
 */

public class ExibirConsultarDadosPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDadosPagamentoAction");

		ConsultarDadosPagamentoActionForm form = (ConsultarDadosPagamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String idPagamentoHistorico = null;

		String idPagamento = null;

		PagamentoHistorico pagamentoHistorico = null;

		Pagamento pagamento = null;

		if (httpServletRequest.getParameter("pagamentoHistorico") != null) {

			// ------------- PEGA DA TABELA PAGAMENTO_HISTORICO
		

			idPagamentoHistorico = (String) httpServletRequest.getParameter("idPagamento");
			
			Collection<PagamentoHistorico> colecaoPagamentoHistorico = 
				fachada.pesquisarPagamentoHistoricoPeloId(new Integer(idPagamentoHistorico));
			
			pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colecaoPagamentoHistorico);

			form.setDescricaoLocalidade(pagamentoHistorico.getLocalidade().getDescricao());
			form.setTipoDocumento(pagamentoHistorico.getDocumentoTipo().getDescricaoDocumentoTipo());

			if (pagamentoHistorico.getImovel() != null
						&& !pagamentoHistorico.getImovel().equals("")) {
				
				form.setMatriculaImovel(""+ pagamentoHistorico.getImovel().getId());
				String imovelInscricao = fachada.pesquisarInscricaoImovel(pagamentoHistorico.getImovel().getId());
				form.setInscricaoImovel(imovelInscricao);
				
				Cliente cliente = fachada.obterDescricaoIdCliente( pagamentoHistorico.getImovel().getId());
				
				if (cliente != null){
					
					form.setClienteId((cliente.getId()).toString());
					form.setClienteNome(cliente.getNome());
				}
				
			}else {
				form.setMatriculaImovel("");
				form.setClienteId("");
				form.setClienteNome("");
				
			}

			if (pagamentoHistorico.getAnoMesReferenciaPagamento() != null
					&& !pagamentoHistorico.getAnoMesReferenciaPagamento().equals("")) {
				
				form.setMesAno(Util.formatarAnoMesParaMesAno(pagamentoHistorico.getAnoMesReferenciaPagamento()));
			}else{
				form.setMesAno("");
			}

			if (pagamentoHistorico.getDebitoTipo() != null
					&& !pagamentoHistorico.getDebitoTipo().equals("")) {
				
				form.setDebitoId(""+ pagamentoHistorico.getDebitoTipo().getId());
				form.setDebitoDescricao(""+ pagamentoHistorico.getDebitoTipo().getDescricao());
			}else {
				form.setDebitoId("");
				form.setDebitoDescricao("");
			}

			form.setDataPagamento(Util.formatarData(pagamentoHistorico.getDataPagamento()));
			form.setValorPagamento(Util.formatarMoedaReal(pagamentoHistorico.getValorPagamento()));
			form.setMesAnoRefPagamento(Util.formatarAnoMesParaMesAno(pagamentoHistorico.getAnoMesReferenciaArrecadacao()));
			form.setDataProcessamento(Util.formatarData(pagamentoHistorico.getUltimaAlteracao()).toString());
			form.setHoraProcessamento(Util.formatarHoraSemData(pagamentoHistorico.getUltimaAlteracao()));
			form.setDescricaoSituacaoAtual(pagamentoHistorico.getPagamentoSituacaoAtual().getDescricao());

			if (pagamentoHistorico.getPagamentoSituacaoAnterior() != null
					&& !pagamentoHistorico.getPagamentoSituacaoAnterior().equals("")) {
				
				form.setDescricaoSituacaoAnterior(pagamentoHistorico.getPagamentoSituacaoAnterior().getDescricao());
			}else{
				form.setDescricaoSituacaoAnterior("");
			}

			if (pagamentoHistorico.getValorExcedente() != null
					&& !pagamentoHistorico.getValorExcedente().equals("")) {
				
				form.setValorExcedente(Util.formatarMoedaReal(pagamentoHistorico.getValorExcedente()));
			}else{
				form.setValorExcedente("");
			}

			if (pagamentoHistorico.getAvisoBancario()!= null && !pagamentoHistorico.getAvisoBancario().equals("")){
				
				httpServletRequest.setAttribute("avisoBancarioPreenchido","avisoBancarioPreenchido");
				
				form.setCodigoArrecadador((pagamentoHistorico.getAvisoBancario().getArrecadador().getCodigoAgente()).toString());
				form.setNomeArrecadador(pagamentoHistorico.getAvisoBancario().getArrecadador().getCliente().getNome());
				form.setDataLancamento(Util.formatarData(pagamentoHistorico.getAvisoBancario().getDataLancamento()));
				
			}else{
				
				form.setCodigoAgenteArrecadador(pagamentoHistorico.getCodigoAgente().toString());
				String nomeCliente = fachada.pesquisarNomeAgenteArrecadador(pagamentoHistorico.getId());
				
				if (nomeCliente != null){
					
					form.setNomeAgenteArrecadador(nomeCliente);
				}else{
					form.setNomeAgenteArrecadador("");
				}
			}
			
			
			if (pagamentoHistorico.getArrecadadorMovimentoItem()!= null && 
					!pagamentoHistorico.getArrecadadorMovimentoItem().equals("")){
				
				httpServletRequest.setAttribute("arrecadadorMovimentoItem","arrecadadorMovimentoItem");
				
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao()!= null && 
						!pagamentoHistorico.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao().equals("")){
					
					form.setDescricaoCodigoRegistro(pagamentoHistorico.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao());
				}else {
					form.setDescricaoCodigoRegistro("");
				}
				
				form.setDataProcessamentoMovimento(Util.formatarData(pagamentoHistorico.getArrecadadorMovimentoItem().getUltimaAlteracao()));
				form.setHoraProcessamentoMovimento(Util.formatarHoraSemData(pagamentoHistorico.getArrecadadorMovimentoItem().getUltimaAlteracao()));
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getDescricaoOcorrencia()!= null &&
						!pagamentoHistorico.getArrecadadorMovimentoItem().getDescricaoOcorrencia().equals("")){
					
					form.setDescricaoOcorrenciaMovimento(pagamentoHistorico.getArrecadadorMovimentoItem().getDescricaoOcorrencia());
				}else{
					form.setDescricaoOcorrenciaMovimento("");
				}
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()!= null && 
						!pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo().equals("")){
					
					form.setSequencialArquivoMovimento(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo().toString());
				}else{
					form.setSequencialArquivoMovimento("");
				}

				if (pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco()!= null && 
						!pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco().equals("")){
					
					form.setCodigoArrecadadorMovimento(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco().toString());
				}else{
					form.setCodigoArrecadadorMovimento("");
				}
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco() != null &&
						!pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco().equals("")){
					
					form.setNomeArrecadadorMovimento(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco());
				}else{
					form.setNomeArrecadadorMovimento("");
				}
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico()!= null &&
						!pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico().equals("")){
					
					form.setServicoManutencao(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico());
				}else{
					form.setServicoManutencao("");
				}				
				
				if (pagamentoHistorico.getArrecadadorMovimentoItem().getConteudoRegistro()!= null && 
						!pagamentoHistorico.getArrecadadorMovimentoItem().getConteudoRegistro().equals("")){
					
					// FAZ A PESQUISA E TRAS O CARACTER CORRESPONDENTE A POSI��O 117
					String caracterRetorno = fachada.pesquisarCaracterRetorno(pagamentoHistorico.getArrecadadorMovimentoItem().getId());
					Integer codigoRegistro = pagamentoHistorico.getArrecadadorMovimentoItem().getRegistroCodigo().getId();
					
					// Codigo registro = 6
					if (codigoRegistro.intValue() == RegistroCodigo.CODIGO_SEIS.intValue()){
						
						String descricao = filtrarArrecadacaoForma(form, fachada,"Z");	
						form.setFormaArrecadacao(descricao);	
						
					}else if (caracterRetorno != null && !caracterRetorno.trim().equals("")){
						
							String descricao = filtrarArrecadacaoForma(form, fachada,caracterRetorno);	
							form.setFormaArrecadacao(descricao);
					
						}else if (codigoRegistro.intValue() == RegistroCodigo.CODIGO_SETE.intValue()){
								//	Codigo registro = 7 e posicao 117 nula
								String descricao = filtrarArrecadacaoForma(form, fachada,"1");	
								form.setFormaArrecadacao(descricao);					
							}else{
								form.setFormaArrecadacao("");
							}
				}
			}

		} else {

			// ---------------- PEGA DA TABELA PAGAMENTO ---------------------


			idPagamento = (String) httpServletRequest.getParameter("idPagamento");

		
			Collection<Pagamento> colecaoPagamento = fachada.pesquisarPagamentoPeloId(new Integer(idPagamento));

			pagamento = (Pagamento) Util.retonarObjetoDeColecao(colecaoPagamento);

			form.setDescricaoLocalidade(pagamento.getLocalidade().getDescricao());
			form.setTipoDocumento(pagamento.getDocumentoTipo().getDescricaoDocumentoTipo());

			if (pagamento.getImovel() != null && !pagamento.getImovel().equals("")) {
				
				form.setMatriculaImovel(""+ pagamento.getImovel().getId());
				String imovelInscricao = fachada.pesquisarInscricaoImovel(pagamento.getImovel().getId());
				form.setInscricaoImovel(imovelInscricao);
				
				Cliente cliente = fachada.obterDescricaoIdCliente(pagamento.getImovel().getId());
				
				if (cliente != null){
					
					form.setClienteId((cliente.getId()).toString());
					form.setClienteNome(cliente.getNome());
				}
				
			}else {
				form.setMatriculaImovel("");
				form.setClienteId("");
				form.setClienteNome("");
				
			}

			if (pagamento.getAnoMesReferenciaPagamento() != null && !pagamento.getAnoMesReferenciaPagamento().equals("")) {
				
				form.setMesAno(Util.formatarAnoMesParaMesAno(pagamento.getAnoMesReferenciaPagamento()));
			}else {
				
				if (pagamento.getGuiaPagamento() != null && pagamento.getGuiaPagamento() .getAnoMesReferenciaContabil() != null){
					form.setMesAno(Util.formatarAnoMesParaMesAno(pagamento.getGuiaPagamento().getAnoMesReferenciaContabil()));
				} else {
					form.setMesAno("");
				}
			}

			if (pagamento.getDebitoTipo() != null && !pagamento.getDebitoTipo().equals("")) {
				
				form.setDebitoId(""+ pagamento.getDebitoTipo().getId());
				form.setDebitoDescricao(""+ pagamento.getDebitoTipo().getDescricao());
			}else{
				form.setDebitoId("");
				form.setDebitoDescricao("");
			}

			form.setDataPagamento(Util.formatarData(pagamento.getDataPagamento()));
			form.setValorPagamento(Util.formatarMoedaReal(pagamento.getValorPagamento()));
			form.setMesAnoRefPagamento(Util.formatarAnoMesParaMesAno(pagamento.getAnoMesReferenciaArrecadacao()));
			form.setDataProcessamento(Util.formatarData(pagamento.getUltimaAlteracao()).toString());
			form.setHoraProcessamento(Util.formatarHoraSemData(pagamento.getUltimaAlteracao()));
			
			if (pagamento.getPagamentoSituacaoAtual()!= null && !pagamento.getPagamentoSituacaoAtual().equals("")){
				
				form.setDescricaoSituacaoAtual(pagamento.getPagamentoSituacaoAtual().getDescricao());
			}else{
				form.setDescricaoSituacaoAtual("");
			}

			if (pagamento.getPagamentoSituacaoAnterior() != null && !pagamento.getPagamentoSituacaoAnterior().equals("")) {
				
				form.setDescricaoSituacaoAnterior(pagamento.getPagamentoSituacaoAnterior().getDescricao());
			}else{
				form.setDescricaoSituacaoAnterior("");
			}

			if (pagamento.getValorExcedente() != null && !pagamento.getValorExcedente().equals("")) {
				
				form.setValorExcedente(Util.formatarMoedaReal(pagamento.getValorExcedente()));
			}else{
				form.setValorExcedente("");
			}
			
			if (pagamento.getAvisoBancario()!= null && !pagamento.getAvisoBancario().equals("")){
				
				httpServletRequest.setAttribute("avisoBancarioPreenchido","avisoBancarioPreenchido");
				
				form.setCodigoArrecadador((pagamento.getAvisoBancario().getArrecadador().getCodigoAgente()).toString());
				form.setNomeArrecadador(pagamento.getAvisoBancario().getArrecadador().getCliente().getNome());
				form.setDataLancamento(Util.formatarData(pagamento.getAvisoBancario().getDataLancamento()));
			}
			
			
			if (pagamento.getArrecadadorMovimentoItem()!= null && 
						!pagamento.getArrecadadorMovimentoItem().equals("")){
				
				httpServletRequest.setAttribute("arrecadadorMovimentoItem","arrecadadorMovimentoItem");
				
				
				if (pagamento.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao()!= null && 
						!pagamento.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao().equals("")){
					
					form.setDescricaoCodigoRegistro(pagamento.getArrecadadorMovimentoItem().getRegistroCodigo().getDescricao());
				}else{
					form.setDescricaoCodigoRegistro("");
				}
				
				form.setDataProcessamentoMovimento(Util.formatarData(pagamento.getArrecadadorMovimentoItem().getUltimaAlteracao()));
				form.setHoraProcessamentoMovimento(Util.formatarHoraSemData(pagamento.getArrecadadorMovimentoItem().getUltimaAlteracao()));
				
				if (pagamento.getArrecadadorMovimentoItem().getDescricaoOcorrencia()!= null &&
						!pagamento.getArrecadadorMovimentoItem().getDescricaoOcorrencia().equals("")){
					
					form.setDescricaoOcorrenciaMovimento(pagamento.getArrecadadorMovimentoItem().getDescricaoOcorrencia());
				}else{
					form.setDescricaoOcorrenciaMovimento("");
				}
				
				if (pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()!= null && 
						!pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo().equals("")){
					
					form.setSequencialArquivoMovimento(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo().toString());
				}else{
					form.setSequencialArquivoMovimento("");
				}

				if (pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco()!= null && 
						!pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco().equals("")){
					
					form.setCodigoArrecadadorMovimento(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getCodigoBanco().toString());
				}else{
					form.setCodigoArrecadadorMovimento("");
				}
				
				if (pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco() != null &&
						!pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco().equals("")){
					
					form.setNomeArrecadadorMovimento(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNomeBanco());
				}else{
					form.setNomeArrecadadorMovimento("");
				}
				
				if (pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico()!= null &&
						!pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico().equals("")){
					
					form.setServicoManutencao(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getDescricaoIdentificacaoServico());
				}else{
					form.setServicoManutencao("");
				}
			
				if (pagamento.getArrecadadorMovimentoItem().getConteudoRegistro()!= null && 
						!pagamento.getArrecadadorMovimentoItem().getConteudoRegistro().equals("")){
					
					// FAZ A PESQUISA E TRAS O CARACTER CORRESPONDENTE A POSI��O 117
					String caracterRetorno = fachada.pesquisarCaracterRetorno(pagamento.getArrecadadorMovimentoItem().getId());
					Integer codigoRegistro = pagamento.getArrecadadorMovimentoItem().getRegistroCodigo().getId();
					
					// Codigo registro = 6
					if (codigoRegistro.intValue() == RegistroCodigo.CODIGO_SEIS.intValue()){
						
						String descricao = filtrarArrecadacaoForma(form, fachada,"Z");	
						form.setFormaArrecadacao(descricao);	
						
					}else if (caracterRetorno != null  && !caracterRetorno.trim().equals("")){
						
							String descricao = filtrarArrecadacaoForma(form, fachada,caracterRetorno);	
							form.setFormaArrecadacao(descricao);
					
						}else if (codigoRegistro.intValue() == RegistroCodigo.CODIGO_SETE.intValue()){
								//	Codigo registro = 7 e posicao 117 nula
								String descricao = filtrarArrecadacaoForma(form, fachada,"1");	
								form.setFormaArrecadacao(descricao);					
							}else{
								form.setFormaArrecadacao("");
							}
				}
				
			}
		}
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPagamento") != null) {
			httpServletRequest.setAttribute(
							"caminhoRetornoTelaPagamento",
							httpServletRequest.getParameter("caminhoRetornoTelaPagamento"));
		}
		

		return retorno;
	}

	private String filtrarArrecadacaoForma(ConsultarDadosPagamentoActionForm form, Fachada fachada ,String codigo ) {
		
		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
		
		filtroArrecadacaoForma.adicionarParametro(new ParametroSimples
						(FiltroArrecadacaoForma.CODIGO_ARRECADACAO_FORMA, codigo));
		
		Collection<ArrecadacaoForma> colecaoArrecadacaoForma = 
				fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
		
		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		
		// Alteracao solicitada por Alexandre Cabral
		// Implementada por Tiago Moreno
		// Homologada por Aryed Lins
		// 14/02/2008
		if (colecaoArrecadacaoForma != null && !colecaoArrecadacaoForma.isEmpty()){
			arrecadacaoForma = (ArrecadacaoForma)colecaoArrecadacaoForma.iterator().next();
		}else{
			filtroArrecadacaoForma.limparListaParametros();
			filtroArrecadacaoForma.limparCamposOrderBy();
			filtroArrecadacaoForma.adicionarParametro(new ParametroSimples
					(FiltroArrecadacaoForma.CODIGO, "1"));
	
			arrecadacaoForma = (ArrecadacaoForma) fachada.pesquisar(
					filtroArrecadacaoForma, ArrecadacaoForma.class.getName()).iterator().next();
		}
		// Fim da Alteracao
		
		
		return arrecadacaoForma.getDescricao();
		
	}
}