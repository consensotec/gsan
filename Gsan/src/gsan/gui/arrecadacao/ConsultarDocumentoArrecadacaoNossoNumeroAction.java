package gsan.gui.arrecadacao;

import gsan.arrecadacao.pagamento.FiltroGuiaPagamento;
import gsan.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.CobrancaDocumentoHistorico;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.FiltroCobrancaDocumento;
import gsan.cobranca.FiltroCobrancaDocumentoHistorico;
import gsan.cobranca.FiltroDocumentoCobranca;
import gsan.cobranca.FiltroDocumentoTipo;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaHistorico;
import gsan.faturamento.conta.FiltroConta;
import gsan.faturamento.conta.FiltroContaHistorico;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1243 - Consultar Documento pelo Nosso número]
 * 
 * @author Davi
 * @date 19/10/2011
 *
 */

public class ConsultarDocumentoArrecadacaoNossoNumeroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultaDocumentoNossoNumero");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession session = httpServletRequest.getSession();
		
		ConsultarDocumentoArrecadacaoNossoNumeroActionForm form = (ConsultarDocumentoArrecadacaoNossoNumeroActionForm) actionForm;
		
		if(httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equals("sim")){
			form.setNumero("");
			this.limparSessao(session);
			return retorno;
		}
		
		String nossoNumero = form.getNumero();
		
		String codigoConvenio = nossoNumero.substring(0,7);
		String tipoDocumento = nossoNumero.substring(7,9);
		String idDocumento = nossoNumero.substring(9, 17);
		//String digitoVerificador = nossoNumero.substring(17,18);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Integer numeroConvenioFicha = sistemaParametro.getNumeroConvenioFichaCompensacao();
		
		if(!codigoConvenio.equals(String.valueOf(numeroConvenioFicha))){
			throw new ActionServletException("atencao.numero_convenio_invalido");
		}
		
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.ID, new Integer(tipoDocumento)));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.INDICADOR_PAGAVEL, 1));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.INDICADOR_USO, 1));
		
		Collection<DocumentoTipo> colDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
		
		if(Util.isVazioOrNulo(colDocumentoTipo)){
			throw new ActionServletException("atencao.tipo_documento_invalido");
		}
		
		if(!tipoDocumento.equals("01") && !tipoDocumento.equals("07")){
			FiltroCobrancaDocumento filtroCobrancaDocumento = new FiltroCobrancaDocumento();
			filtroCobrancaDocumento.adicionarParametro(new ParametroSimples(FiltroDocumentoCobranca.ID, new Integer(idDocumento)));
			filtroCobrancaDocumento.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
			
			Collection<CobrancaDocumento> colDocumentoCobranca = fachada.pesquisar(filtroCobrancaDocumento, CobrancaDocumento.class.getName());
			
			if(Util.isVazioOrNulo(colDocumentoCobranca)){
				FiltroCobrancaDocumentoHistorico filtroCobrancaDocumentoHistorico = new FiltroCobrancaDocumentoHistorico();
				filtroCobrancaDocumentoHistorico.adicionarParametro(new ParametroSimples(FiltroCobrancaDocumentoHistorico.ID, new Integer(idDocumento)));
				filtroCobrancaDocumentoHistorico.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
				
				Collection<CobrancaDocumentoHistorico> colDocumentoHistorico = fachada.pesquisar(filtroCobrancaDocumentoHistorico, CobrancaDocumentoHistorico.class.getName());
				
				if(Util.isVazioOrNulo(colDocumentoHistorico)){
					throw new ActionServletException("atencao.documento_cobranca_invalido");
				}else{
					limparSessao(session);
					CobrancaDocumentoHistorico documentoHistorico = (CobrancaDocumentoHistorico) Util.retonarObjetoDeColecao(colDocumentoHistorico);
					
					if(documentoHistorico.getDocumentoTipo() != null){
						if(!tipoDocumento.equals(String.valueOf(documentoHistorico.getDocumentoTipo().getId()))){
							throw new ActionServletException("atencao_tipo_documento_cobranca_invalido");
						}
					}
					
					session.setAttribute("cobrancaDocumento", documentoHistorico);
				}
			}else{
				limparSessao(session);
				CobrancaDocumento cobrancaDocumento = (CobrancaDocumento) Util.retonarObjetoDeColecao(colDocumentoCobranca);
				
				if(cobrancaDocumento.getDocumentoTipo() != null){
					if(!tipoDocumento.equals(String.valueOf(cobrancaDocumento.getDocumentoTipo().getId()))){
						throw new ActionServletException("atencao_tipo_documento_cobranca_invalido");
					}
				}
				
				session.setAttribute("cobrancaDocumento", cobrancaDocumento);
			}
		}else if(tipoDocumento.equals("01")){
			
			FiltroConta filtroConta = new FiltroConta();
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.NUMERO_BOLETO, new Integer(idDocumento)));
			
			Collection<Conta> colConta = fachada.pesquisar(filtroConta, Conta.class.getName());
			
			if(Util.isVazioOrNulo(colConta)){
				
				filtroConta = new FiltroConta();
				filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, new Integer(idDocumento)));
				
				colConta = fachada.pesquisar(filtroConta, Conta.class.getName());
				
				if(Util.isVazioOrNulo(colConta)){
					FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();
					filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.NUMERO_BOLETO, new Integer(idDocumento)));
					
					Collection<ContaHistorico> colContaHistorico = fachada.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());
					
					if(Util.isVazioOrNulo(colContaHistorico)){
						filtroContaHistorico = new FiltroContaHistorico();
						filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.ID, new Integer(idDocumento)));
						
						colContaHistorico = fachada.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());
						
						if(Util.isVazioOrNulo(colContaHistorico)){
							throw new ActionServletException("atencao.conta_associada_invalido");
						}
						else{
							limparSessao(session);
							ContaHistorico contaHistorico = (ContaHistorico) Util.retonarObjetoDeColecao(colContaHistorico);
							session.setAttribute("conta", contaHistorico);
							session.setAttribute("tipoConsultaConta", "contaHistorico");
						}
					}else{
						limparSessao(session);
						ContaHistorico contaHistorico = (ContaHistorico) Util.retonarObjetoDeColecao(colContaHistorico);
						session.setAttribute("conta", contaHistorico);
						session.setAttribute("tipoConsultaConta", "contaHistorico");
					}
				}else{
					limparSessao(session);
					Conta conta = (Conta) Util.retonarObjetoDeColecao(colConta);
					session.setAttribute("conta", conta);
					session.setAttribute("tipoConsultaConta", "conta");
				}
			
			}else{
				limparSessao(session);
				Conta conta = (Conta) Util.retonarObjetoDeColecao(colConta);
				session.setAttribute("conta", conta);
				session.setAttribute("tipoConsultaConta", "conta");
			}
			
		}else{
			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, new Integer(idDocumento)));
			
			Collection<GuiaPagamento> colGuiasPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
			
			if(Util.isVazioOrNulo(colGuiasPagamento)){
				FiltroGuiaPagamentoHistorico filtroGuiaPagamentoHistorico = new FiltroGuiaPagamentoHistorico();
				filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.ID, new Integer(idDocumento)));
				
				Collection<GuiaPagamentoHistorico> colHistoricoGuiasPagamento = fachada.pesquisar(filtroGuiaPagamentoHistorico, GuiaPagamentoHistorico.class.getName());
				
				if(Util.isVazioOrNulo(colHistoricoGuiasPagamento)){
					throw new ActionServletException("atencao.guia_pagamento_nao_encontrada");
				}else{
					limparSessao(session);
					GuiaPagamentoHistorico guiaHistorico = (GuiaPagamentoHistorico) Util.retonarObjetoDeColecao(colHistoricoGuiasPagamento);
					session.setAttribute("guiaPagamentoHistorico", guiaHistorico);
				}
			}
			else{
				limparSessao(session);
				GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colGuiasPagamento);
				session.setAttribute("guiaPagamento", guiaPagamento);
			}
		}
		
		return retorno;
		
	}

	private void limparSessao(HttpSession session) {
		session.removeAttribute("conta");
		session.removeAttribute("tipoConsultaConta");
		session.removeAttribute("cobrancaDocumento");
		session.removeAttribute("guiaPagamento");
		session.removeAttribute("guiaPagamentoHistorico");
	}
	
}
