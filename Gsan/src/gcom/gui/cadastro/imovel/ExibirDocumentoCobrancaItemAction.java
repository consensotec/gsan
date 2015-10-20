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

import java.util.Collection;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.FiltroArrecadadorMovimento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaDocumento;
import gcom.cobranca.bean.CobrancaDocumentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * Visualiza em PopUp dos dados do Documento de Cobranca 
 * [UC0472] Consultar Imovel 
 * Aba 8� - Documentos de Cobran�a
 * 
 * @author  Rafael Santos
 * @created 19/09/2006
 */
public class ExibirDocumentoCobrancaItemAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	ActionForward retorno = actionMapping.findForward("exibirDocumentoCobrancaItem");
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	HttpSession sessao = this.getSessao(httpServletRequest);
    	
    	DocumentoCobrancaItemActionForm documentoCobrancaItemActionForm = 
        (DocumentoCobrancaItemActionForm) actionForm;
    	
    	String cobrancaDocumentoID = httpServletRequest.getParameter("cobrancaDocumentosID");
    	
		StringBuilder nossoNumero2 = fachada.obterNossoNumeroFichaCompensacao(DocumentoTipo.DOCUMENTO_COBRANCA.toString(),cobrancaDocumentoID);
		String nossoNumero = nossoNumero2.toString();
    	
    	CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
    	cobrancaDocumento.setId(new Integer(cobrancaDocumentoID));
    	
    	CobrancaDocumentoHelper cobrancaDocumentoHelper = fachada.apresentaItensDocumentoCobranca(cobrancaDocumento);
    	
    	if(cobrancaDocumentoHelper.getUsuario() != null){
    		documentoCobrancaItemActionForm.setNomeUsuario(cobrancaDocumentoHelper.getUsuario().getNomeUsuario());
    	}else{
            documentoCobrancaItemActionForm.setNomeUsuario("");
        }
    	
    	if(httpServletRequest.getParameter("numeroOS") != null && 
    			!httpServletRequest.getParameter("numeroOS").equals("")){
    		cobrancaDocumentoHelper.setIdOrdemServico(new Integer((String) httpServletRequest.getParameter("numeroOS")));
    	}
    	
    	if(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel() != null){
	    	documentoCobrancaItemActionForm.setMatriculaImovel(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getId().toString());
	    	documentoCobrancaItemActionForm.setInscricaoImovel(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getInscricaoFormatada());
	    	if(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getLigacaoAguaSituacao() != null){
	    		documentoCobrancaItemActionForm.setSituacaoAguaImovel(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getLigacaoAguaSituacao().getDescricao());
	    	}
	    	if(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getLigacaoEsgotoSituacao() != null){
	    		documentoCobrancaItemActionForm.setSituacaoEsgotoImovel(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getLigacaoEsgotoSituacao().getDescricao());
	    	}
	    	if(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getClienteUsuario() != null){
	    		documentoCobrancaItemActionForm.setCodigoCliente(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getClienteUsuario().getId().toString());
	    		documentoCobrancaItemActionForm.setNomeCliente(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getClienteUsuario().getNome());
	    	}
    	}else{
    		FiltroCobrancaDocumento filtroCobrancaDocumento = new FiltroCobrancaDocumento();
    		filtroCobrancaDocumento.adicionarParametro(new ParametroSimples(FiltroCobrancaDocumento.ID, cobrancaDocumentoID));
    		filtroCobrancaDocumento.adicionarCaminhoParaCarregamentoEntidade("cliente");
    		
    		Collection<CobrancaDocumento> colCobrancaDocumento = fachada.pesquisar(filtroCobrancaDocumento, CobrancaDocumento.class.getName());
    		
    		CobrancaDocumento cobrancaDocumento2 = (CobrancaDocumento) Util.retonarObjetoDeColecao(colCobrancaDocumento);
    		
    		if(cobrancaDocumento2.getCliente() != null){
    			documentoCobrancaItemActionForm.setCodigoCliente(cobrancaDocumento2.getCliente().getId().toString());
    			documentoCobrancaItemActionForm.setNomeCliente(cobrancaDocumento2.getCliente().getNome());
    		}
    	}
    	
    	if(cobrancaDocumentoID != null && !cobrancaDocumentoID.equals("")){
			Collection<Pagamento> colPagamentos = fachada.pesquisarPagamentoPeloId(Integer.parseInt(cobrancaDocumentoID),  DocumentoTipo.DOCUMENTO_COBRANCA);
			
			if(Util.isVazioOrNulo(colPagamentos)){
				Collection<PagamentoHistorico> colHistoricoPagamentos = fachada.pesquisarPagamentoHistoricoPeloId(Integer.parseInt(cobrancaDocumentoID),  DocumentoTipo.DOCUMENTO_COBRANCA);
				if(!Util.isVazioOrNulo(colHistoricoPagamentos)){
					PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colHistoricoPagamentos);
					ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(pagamentoHistorico, null);
					ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamentoHistorico.getArrecadadorMovimentoItem();
					
					httpServletRequest.setAttribute("pagamento", pagamentoHistorico);
					httpServletRequest.setAttribute("nossoNumero", nossoNumero);
					httpServletRequest.setAttribute("arrecadador", arrecadador);
					if(arrecadadorMovimentoItem != null){
						httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
					}
					sessao.setAttribute("contaPaga", "pago");
				}
				else{
					sessao.removeAttribute("contaPaga");
				}
			}else{
				Pagamento pagamento = (Pagamento) Util.retonarObjetoDeColecao(colPagamentos);
				ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(null, pagamento);
				ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamento.getArrecadadorMovimentoItem();
				
				httpServletRequest.setAttribute("pagamento", pagamento);
				httpServletRequest.setAttribute("nossoNumero", nossoNumero);
				httpServletRequest.setAttribute("arrecadador", arrecadador);
				if(arrecadadorMovimentoItem != null){
					httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
				}
				sessao.setAttribute("contaPaga", "pago");
			}
			
		}
	    
    	documentoCobrancaItemActionForm.setSequencial(String.valueOf(cobrancaDocumentoHelper.getCobrancaDocumento().getNumeroSequenciaDocumento()));
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getValorDocumento() != null){
    		documentoCobrancaItemActionForm.setValorDocumento(Util.formatarMoedaReal(cobrancaDocumentoHelper.getCobrancaDocumento().getValorDocumento()));
    	}
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getValorDesconto() != null){
    		documentoCobrancaItemActionForm.setValorDesconto(Util.formatarMoedaReal(cobrancaDocumentoHelper.getCobrancaDocumento().getValorDesconto()));
    	}
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getValorAcrescimos() != null){
    		documentoCobrancaItemActionForm.setValorAcrescimos(Util.formatarMoedaReal(cobrancaDocumentoHelper.getCobrancaDocumento().getValorAcrescimos()));
    	}

    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getDocumentoEmissaoForma() != null){
    		documentoCobrancaItemActionForm.setFormaEmissao(cobrancaDocumentoHelper.getCobrancaDocumento().getDocumentoEmissaoForma().getDescricaoDocumentoEmissaoForma());
    	}
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getEmissao() != null){
    		documentoCobrancaItemActionForm.setDataHoraEmissao(Util.formatarDataComHora(cobrancaDocumentoHelper.getCobrancaDocumento().getEmissao()));
    	} 
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getMotivoNaoEntregaDocumento() != null){
    		documentoCobrancaItemActionForm.setMotivoNaoEntregaDocumento(cobrancaDocumentoHelper.getCobrancaDocumento().getMotivoNaoEntregaDocumento().getMotivoNaoeEntregaDocumento());
    	}
    	
    	documentoCobrancaItemActionForm.setQtdItens(cobrancaDocumentoHelper.getQuantidadeItensCobrancaDocumento().toString());
    	
    	if(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel() != null){
    		httpServletRequest.setAttribute("imovel", cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getId().toString());
    	}
    	httpServletRequest.setAttribute("cobrancaDocumentoHelper", cobrancaDocumentoHelper);
    	
        return retorno;
        
    }
    
    /**
	 * Pesquisa o Arrecadador do Movimento
	 * 
	 * @author Davi
	 * @date 21/10/2011
	 * 
	 * @param pagamentoHistorico
	 * @param pagamento
	 * @return
	 */
	private ArrecadadorMovimento pesquisarArrecadadorMovimento(PagamentoHistorico pagamentoHistorico, Pagamento pagamento){
		ArrecadadorMovimento arrecadador = null;
		if(pagamentoHistorico != null){
			if(pagamentoHistorico.getArrecadadorMovimentoItem() != null){
				if(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento() != null){
					FiltroArrecadadorMovimento filtroArrecadador = new FiltroArrecadadorMovimento();
					filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.SEQUENCIA_ARQUIVO, 
						pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()));
					
					Fachada fachada = Fachada.getInstancia();
					Collection<ArrecadadorMovimento> colArrecadador = fachada.pesquisar(filtroArrecadador, ArrecadadorMovimento.class.getName());
					
					arrecadador = (ArrecadadorMovimento) Util.retonarObjetoDeColecao(colArrecadador);
				}
			}
		}else if(pagamento != null){
			if(pagamento.getArrecadadorMovimentoItem() != null){
				if(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento() != null){
					FiltroArrecadadorMovimento filtroArrecadador = new FiltroArrecadadorMovimento();
					filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.SEQUENCIA_ARQUIVO, 
							pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()));
					
					Fachada fachada = Fachada.getInstancia();
					Collection<ArrecadadorMovimento> colArrecadador = fachada.pesquisar(filtroArrecadador, ArrecadadorMovimento.class.getName());
					
					arrecadador = (ArrecadadorMovimento) Util.retonarObjetoDeColecao(colArrecadador);
				}
			}
		}
		
		return arrecadador;
	}
}
