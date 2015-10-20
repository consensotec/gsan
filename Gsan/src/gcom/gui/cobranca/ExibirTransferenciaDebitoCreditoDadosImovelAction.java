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
package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da transfer�ncia de d�bitos e cr�ditos entre im�veis
 * 
 * @author Raphael Rossiter
 * @date 06/06/2007
 */
public class ExibirTransferenciaDebitoCreditoDadosImovelAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
		.findForward("transferenciaDebitoCreditoDadosImovel");

		TransferenciaDebitoCreditoDadosImovelActionForm form = 
		(TransferenciaDebitoCreditoDadosImovelActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		// REGISTRO ATENDIMENTO
		String pesquisarRA = httpServletRequest.getParameter("pesquisarRA");
		if (pesquisarRA != null && !pesquisarRA.equals("")){
			
			// [FS0004] Validar Registro Atendimento
			Object[] dadosRA = fachada.validarRegistroAtendimentoTransferenciaDebitoCredito(
			new Integer(form.getIdRegistroAtendimento()), false);
			
			String descricaoRA = (String) dadosRA[0];
			Short ocorreuErro = (Short) dadosRA[1];
			Integer idImovel = (Integer) dadosRA[2];
			
			 if (ocorreuErro.equals(ConstantesSistema.SIM)){
				 limparDadosRA(form);
				 httpServletRequest.setAttribute("corRA", "exception");
				 httpServletRequest.setAttribute("nomeCampo", "idRegistroAtendimento");
			 }
			 else{
				 
				 //MATRICULA IMOVEL
				 form.setIdImovelOrigem(idImovel.toString());
				 
				 //INSCRICAO IMOVEL
				 String inscricaoImovel = fachada.pesquisarInscricaoImovel(idImovel);
				 form.setInscricaoImovelOrigem(inscricaoImovel);
				 
				 //CLIENTE USUARIO DO IMOVEL
				 Cliente cliente = fachada.pesquisarClienteUsuarioImovel(idImovel);
				 form.setNomeClienteUsuarioImovelOrigem(cliente.getNome());
				 
				 //LIGACAO AGUA SITUACAO
				 LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(idImovel);
				 form.setDescricaoLigacaoAguaSituacaoImovelOrigem(ligacaoAguaSituacao.getDescricao());
				 
				 //LIGACAO ESOTO SITUACAO
				 LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(idImovel);
				 form.setDescricaoLigacaoEsgotoSituacaoImovelOrigem(ligacaoEsgotoSituacao.getDescricao());
				 
				 httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
				
			 }
			 
			 //DESCRICAO RA
			 form.setDescricaoEspecificacaoRA(descricaoRA);
		}
		
		
		// IMOVEL DESTINO
		String pesquisarImovelDestino = httpServletRequest.getParameter("pesquisarImovelDestino");
		if (pesquisarImovelDestino != null && !pesquisarImovelDestino.equals("")){
			
			Integer idImovelDestino = new Integer(form.getIdImovelDestino());
			String inscricaoImovel = fachada.pesquisarInscricaoImovel(idImovelDestino);
			
			if (inscricaoImovel == null){
				 limparDadosImovelDestino(form);
				 form.setInscricaoImovelDestino("IMOVEL INEXISTENTE");
				 httpServletRequest.setAttribute("corImovelDestino", "exception");
				 httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
			}
			else{
				 
				// [FS0006] - Verificar Im�vel Destino
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				if (sistemaParametro.getIndicadorTransferenciaComDebito().equals(ConstantesSistema.NAO)) {
					
					String anoMesInicial = "000101";
					String anoMesFinal = "999912";
					
					String dataVencimentoInicialFormatada = "01/01/0001";
					
					Date dataVencimentoInicial = Util.converteStringParaDate(dataVencimentoInicialFormatada);
					Date dataVencimentoFinal = Util.adicionarNumeroDiasDeUmaData(new Date(), 3); 
					
					
					ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = fachada.obterDebitoImovelOuCliente(
							// Indicador de d�bito do im�vel
							1,
							
							// Matr�cula do Im�vel
							idImovelDestino.toString(),
							
							// C�digo do Cliente
							null,
							
							// Tipo da Rela��o
							null, 
							
							// M�s/Ano Inicial de Refer�ncia do D�bito
							anoMesInicial,
							
							// M�s/Ano Final de Refer�ncia do D�bito
							anoMesFinal,
							
							// Data Vencimento Inicial
							dataVencimentoInicial,
							
							// Data Vencimento Final
							dataVencimentoFinal,
							
							// Indicador de Pagamento com Valor
							1,
							
							// Indicador de Conta em Revis�o com Valor
							1,
							
							// Indicador de D�bito a Cobrar com Valor
							1,
							
							// Indicador de Cr�dito a Realizar com Valor
							1,
							
							// Indicador de Notas Promiss�rias com Valor
							1,
							
							// Indicador de Guias de Pagamento com Valor
							1,
							
							// Indicador Calcular Acr�scimos por Impontualidade
							1,
							
							// Indicador de Atualizar D�bito com Valor
							true,
							2);
					
					if ((obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null && !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel() != null && !obterDebitoImovelOuClienteHelper.getColecaoContasValoresImovel().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null && !obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null && !obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoDebitoCreditoParcelamentoHelper() != null && !obterDebitoImovelOuClienteHelper.getColecaoDebitoCreditoParcelamentoHelper().isEmpty()) ||
						(obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores() != null && !obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores().isEmpty())) {
						
						throw new ActionServletException("atencao.imovel_destino_com_debito", null, idImovelDestino.toString());
						
					}
				}
				
				 //INSCRICAO IMOVEL
				 form.setInscricaoImovelDestino(inscricaoImovel);
				 
				 //CLIENTE USUARIO DO IMOVEL
				 Cliente cliente = fachada.pesquisarClienteUsuarioImovel(idImovelDestino);
				 form.setNomeClienteUsuarioImovelDestino(cliente.getNome());
				 
				 //LIGACAO AGUA SITUACAO
				 LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(idImovelDestino);
				 form.setDescricaoLigacaoAguaSituacaoImovelDestino(ligacaoAguaSituacao.getDescricao());
				 
				 //LIGACAO ESOTO SITUACAO
				 LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(idImovelDestino);
				 form.setDescricaoLigacaoEsgotoSituacaoImovelDestino(ligacaoEsgotoSituacao.getDescricao());
				 
				 httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
				
			}
		}
	
		return retorno;
	}
	
	
	private void limparDadosRA(TransferenciaDebitoCreditoDadosImovelActionForm form){
		
		form.setIdRegistroAtendimento("");
		form.setDescricaoEspecificacaoRA("");
		form.setIdImovelOrigem("");
		form.setInscricaoImovelOrigem("");
		form.setNomeClienteUsuarioImovelOrigem("");
		form.setDescricaoLigacaoAguaSituacaoImovelOrigem("");
		form.setDescricaoLigacaoEsgotoSituacaoImovelOrigem("");
		
	}
	
	
	private void limparDadosImovelDestino(TransferenciaDebitoCreditoDadosImovelActionForm form){
		
		form.setIdImovelDestino("");
		form.setInscricaoImovelDestino("");
		form.setNomeClienteUsuarioImovelDestino("");
		form.setDescricaoLigacaoAguaSituacaoImovelDestino("");
		form.setDescricaoLigacaoEsgotoSituacaoImovelDestino("");
		
	}

}
