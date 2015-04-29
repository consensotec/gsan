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
package gsan.gui.cobranca.contratoparcelamento;



import gsan.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gsan.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gsan.cobranca.contratoparcelamento.FiltroContratoParcelamentoPrestacao;
import gsan.cobranca.contratoparcelamento.FiltroPrestacaoItemContratoParcelamento;
import gsan.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gsan.cobranca.contratoparcelamento.PrestacaoItemContratoParcelamento;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ConectorAnd;
import gsan.util.filtro.ParametroNaoNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirEmitirComprovantePagamentoContratoParcelamentoClienteAction extends GcomAction {
	
	private HttpSession sessao;
	
	
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

		ActionForward retorno = actionMapping.findForward("emitirComprovante");
		sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		String idContrato = httpServletRequest.getParameter("idContrato");
		ContratoParcelamentoCliente contratoParcelamentoCliente = null;
		
		EmitirComprovantePagamentoContratoParcelamentoClienteActionForm emitirComprovantePagamento = (EmitirComprovantePagamentoContratoParcelamentoClienteActionForm) actionForm;
		
		FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = new FiltroContratoParcelamentoCliente();
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.contratoAnterior");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.cobrancaForma");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.motivoDesfazer");
		
		
		if(idContrato != null && !idContrato.equals("")){
			
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(
				FiltroContratoParcelamentoCliente.CONTRATO_PARCELAMENTO_ID,idContrato));
			ArrayList<ContratoParcelamentoCliente> collectionContratoParcelamentoCliente = new ArrayList<ContratoParcelamentoCliente>(fachada.pesquisar(filtroContratoParcelamentoCliente,ContratoParcelamentoCliente.class.getName()));
			
			//Verificar a exist�ncia do contrato
			if(collectionContratoParcelamentoCliente.size() == 0){
				throw new ActionServletException("atencao.numero.contrato.nao.existe");
			}
			
			//Rotina para buscar o cliente superior se a ocorr�ncia n�o for �nica
			else if(collectionContratoParcelamentoCliente.size() > 1){
				Iterator it = collectionContratoParcelamentoCliente.iterator();
				while(it.hasNext()){
					contratoParcelamentoCliente = (ContratoParcelamentoCliente) it.next();
					if(contratoParcelamentoCliente.getIndicadorClienteSuperior().toString().equals("1"))
						break;
				}
			}
			else{
				contratoParcelamentoCliente = collectionContratoParcelamentoCliente.get(0);
				if(contratoParcelamentoCliente.getIndicadorClienteSuperior().toString().equals("2") && contratoParcelamentoCliente.getClienteSuperior() == null){ 
					if (contratoParcelamentoCliente.getContrato().getRelacaoCliente() != null){
							emitirComprovantePagamento.setTipoRelacao(contratoParcelamentoCliente.getContrato().getRelacaoCliente().getDescricao());
					}
				}
			}
			
			
			
			FiltroContratoParcelamentoPrestacao filtroContratoParcelamentoPrestacao = new FiltroContratoParcelamentoPrestacao();
			filtroContratoParcelamentoPrestacao.adicionarCaminhoParaCarregamentoEntidade("contratoParcelamento");
			filtroContratoParcelamentoPrestacao.adicionarParametro(new ParametroNaoNulo(FiltroContratoParcelamentoPrestacao.VALOR_PAGAMENTO,ConectorAnd.CONECTOR_AND,2));
			filtroContratoParcelamentoPrestacao.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoPrestacao.CONTRATO_PARCEL_ID,contratoParcelamentoCliente.getContrato().getId()));
			filtroContratoParcelamentoPrestacao.setCampoOrderBy(FiltroContratoParcelamentoPrestacao.NUMERO);
			
			ArrayList<PrestacaoContratoParcelamento> collectionPrestacaoContratoParcelamento = new ArrayList<PrestacaoContratoParcelamento>(fachada.pesquisar(filtroContratoParcelamentoPrestacao,PrestacaoContratoParcelamento.class.getName()));
			
			
			//Verificando possibilidade de emiss�o de comprovante
			if(collectionPrestacaoContratoParcelamento.size() != 0){
				
				//Dados do Contrato que ir�o para a tela
				emitirComprovantePagamento.setIdNumeroContrato(contratoParcelamentoCliente.getContrato().getNumero().toString());
				emitirComprovantePagamento.setIdCliente(contratoParcelamentoCliente.getCliente().getId().toString());
				emitirComprovantePagamento.setNome(contratoParcelamentoCliente.getCliente().getNome());
				emitirComprovantePagamento.setCnpj(contratoParcelamentoCliente.getCliente().getCnpj());
				emitirComprovantePagamento.setNomeCNPJ(contratoParcelamentoCliente.getCliente().getNome() + " " + contratoParcelamentoCliente.getCliente().getCnpjFormatado());
				emitirComprovantePagamento.setDataImplantacaoContrato(Util.formatarData(contratoParcelamentoCliente.getContrato().getDataImplantacao()));
				
				//Quantidades de presta��es
				Iterator it = collectionPrestacaoContratoParcelamento.iterator();
				int marcador = 0;
				//filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item");
				
				
				while(it.hasNext()){
					FiltroPrestacaoItemContratoParcelamento filtroPrestacaoItem = new FiltroPrestacaoItemContratoParcelamento();
					filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("prestacao");
					filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item");
					PrestacaoContratoParcelamento prestacao = (PrestacaoContratoParcelamento) it.next();
					filtroPrestacaoItem.adicionarParametro(new ParametroSimples(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_ID,prestacao.getId()));
					filtroPrestacaoItem.setCampoDistinct("objeto."+FiltroPrestacaoItemContratoParcelamento.ITEM_ID);
					Collection<PrestacaoItemContratoParcelamento> collectionPrestacaoItem = fachada.pesquisar(filtroPrestacaoItem,PrestacaoItemContratoParcelamento.class.getName());
					prestacao.setItensPagos(collectionPrestacaoItem.size());
					//prestacao.setDataPagamento(prestacao.getDataPagamento());
					
					//Salva com o valor da quantidade de itens pagos
					collectionPrestacaoContratoParcelamento.set(marcador,prestacao);
					
					marcador++;
				}
				
				sessao.setAttribute("contratoParcelamentoCliente",contratoParcelamentoCliente);
				sessao.setAttribute("parcelas",collectionPrestacaoContratoParcelamento);
				sessao.setAttribute("quantidadeParcelas",collectionPrestacaoContratoParcelamento.size());
					
				}
				else{
					throw new ActionServletException("atencao.contrato.pagamento.inexistente",contratoParcelamentoCliente.getContrato().getNumero());
				}
			}
			else{
				throw new ActionServletException("atencao.numero.contrato.nao.existe");
			}			
			
		
		
		
		return retorno;
	}

}
