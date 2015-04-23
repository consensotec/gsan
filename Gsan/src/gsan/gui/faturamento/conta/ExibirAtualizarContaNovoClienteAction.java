/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gui.faturamento.conta;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteConta;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cobranca.bean.ContaValoresHelper;
import gsan.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.AssociarContaNovoClienteHelper;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * UC1576-AssociarContasNovoClienteOuRemoverClienteConta
 *  
 * @author Arthur Carvalho
 * @date 27/11/2013
 */
public class ExibirAtualizarContaNovoClienteAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarContaNovoCliente");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarContaNovoClienteActionForm form = (AtualizarContaNovoClienteActionForm) actionForm;
		
		//consulta os dados do formulario
		if ( httpServletRequest.getParameter("consulta") != null && !httpServletRequest.getParameter("consulta").equals("") ) {
			
			//pesquisar dados formulario, com os parametros informado pelo usuario
			this.pesquisarDadosFormulario(httpServletRequest.getParameter("consulta"), form, sessao);
		}
		
		//pesquisa os debitos 
		if ( httpServletRequest.getParameter("pesquisar") != null && httpServletRequest.getParameter("pesquisar").equals("ok") ) {
			
			//valida os parametros informado na pesquisa.
			String idCliente = this.validarCliente(form);
			String idImovel = this.validarImovel(form, sessao);
			
			//[UC0067 - Obter Débito do Imóvel ou Cliente]
			Collection<ContaValoresHelper> colecaoContaValores = obterContas(idImovel, idCliente);
			
			//para cada conta informada, montar o heler e exibir para o usuario a lista de contas.
			if ( colecaoContaValores != null && !colecaoContaValores.isEmpty() ) {
				
				Collection<AssociarContaNovoClienteHelper> colecaoAssociarContaClienteNovo = new ArrayList<AssociarContaNovoClienteHelper>();
				
				Iterator<ContaValoresHelper> iteratorContaValoresHelper = colecaoContaValores.iterator();
				while ( iteratorContaValoresHelper.hasNext() ) {
					
					ContaValoresHelper contaHelper = (ContaValoresHelper) iteratorContaValoresHelper.next();

					
					ClienteConta clienteConta = Fachada.getInstancia().pesquisarClienteConta(contaHelper.getConta().getId(), ClienteRelacaoTipo.RESPONSAVEL.intValue());
					if ( clienteConta != null ) {
						AssociarContaNovoClienteHelper helper = new AssociarContaNovoClienteHelper();
						
						helper.setAnoMesReferencia(contaHelper.getConta().getReferenciaFormatada());
						helper.setDataVencimento(Util.formatarData(contaHelper.getConta().getDataVencimentoConta()));
						helper.setIdContaSelecionada(String.valueOf(contaHelper.getConta().getId()));
						helper.setIdImovel(String.valueOf(contaHelper.getConta().getImovel().getId()));
						
						helper.setNomeCliente(Fachada.getInstancia().obterNomeClienteUsuarioConta(contaHelper.getConta().getId()));
						
						
						if ( contaHelper.getConta().getContaMotivoRevisao() != null && contaHelper.getConta().getContaMotivoRevisao().getId() != null &&
								contaHelper.getConta().getContaMotivoRevisao().getId().equals(ContaMotivoRevisao.CONTA_EM_CONTRATO_PARCELAMENTO)) {
							helper.setSituacaoConta("PCR");	
						} else {
							helper.setSituacaoConta(contaHelper.getConta().getDebitoCreditoSituacaoAtual().getDescricaoAbreviada());
						}
						
						helper.setValorConta(Util.formatarMoedaReal(contaHelper.getConta().getValorTotal()));
						
						colecaoAssociarContaClienteNovo.add(helper);
					}
				}
				
				if ( colecaoAssociarContaClienteNovo.isEmpty() ) {
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				
				sessao.setAttribute("colecaoAssociarContaClienteNovo", colecaoAssociarContaClienteNovo);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}
		
		
		
		
       return retorno;

	}
	
	private Collection<ContaValoresHelper> obterContas(String idImovel, String idCliente ) {
	
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		String dataVencimentoInicial = "01/01/0001";
		String dataVencimentoFinal = "31/12/9999";

		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;

		try {
			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}

		// seta valores constantes para chamar o metodo que consulta debitos do imovel
		Integer tipoImovel = null;
		Integer indicadorPagamento = null;
		Integer indicadorConta = null;
		Integer indicadorDebito = null;
		Integer indicadorCredito = null;
		Integer indicadorNotas = null;
		Integer indicadorGuias = null;
		Integer indicadorAtualizar = null;
		if ( idImovel != null ) {
			tipoImovel = new Integer(1);
			indicadorPagamento = new Integer(1);
			indicadorConta = new Integer(1);
			indicadorDebito = new Integer(1);
			indicadorCredito = new Integer(1);
			indicadorNotas = new Integer(1);
			indicadorGuias = new Integer(1);
			indicadorAtualizar = new Integer(1);
		} else {
			tipoImovel = new Integer(2);
			indicadorPagamento = new Integer(1);
			indicadorConta = new Integer(1);
			indicadorDebito = new Integer(2);
			indicadorCredito = new Integer(2);
			indicadorNotas = new Integer(2);
			indicadorGuias = new Integer(2);
			indicadorAtualizar = new Integer(1);
		}

		// Obtendo os débitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = Fachada.getInstancia()
				.obterDebitoImovelOuCliente(tipoImovel.intValue(),
						idImovel, idCliente, null,
						anoMesInicial, anoMesFinal,
						dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento
								.intValue(), indicadorConta
								.intValue(), indicadorDebito
								.intValue(), indicadorCredito
								.intValue(), indicadorNotas
								.intValue(), indicadorGuias
								.intValue(), indicadorAtualizar
								.intValue(), null,2);
		
		Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();
		
		return colecaoContaValores;
	}
	/**
	 * 
	 * @param consulta
	 * @param form
	 * @param sessao
	 */
	private void pesquisarDadosFormulario(String consulta, AtualizarContaNovoClienteActionForm form, HttpSession sessao  ) {
		
		//caso o usuario pesquise o imovel
		if ( consulta.equals("imovel") ) {
			
			if ( form.getMatricula() != null && !form.getMatricula().equals("") && Util.verificaSeNumeroNatural(form.getMatricula()) ) {
			
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro( new ParametroSimples(FiltroImovel.ID, Integer.valueOf(form.getMatricula())));
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);

				Collection<Imovel> colecaoImovel = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
				if ( colecaoImovel != null && !colecaoImovel.isEmpty() ) {
					
					Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
					form.setMatricula(imovel.getId().toString());
					form.setInscricao(imovel.getInscricaoFormatada());
					sessao.setAttribute("inscricaoImovelEncontrada", true);
				} else {
					form.setMatricula("");
					form.setInscricao("Imóvel Inexistente");
					sessao.removeAttribute("inscricaoImovelEncontrada");
				}
			} else {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null, "Matrícula do Imóvel");
			}
			
		} else if ( consulta.equals("cliente") ) {
			
			if ( form.getIdCliente() != null && !form.getIdCliente().equals("") && Util.verificaSeNumeroNatural(form.getIdCliente()) ) {
				
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro( new ParametroSimples(FiltroCliente.ID, Integer.valueOf(form.getIdCliente())));
				
				Collection<Cliente> colecaoCliente = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
				if ( colecaoCliente != null && !colecaoCliente.isEmpty() ) {
					
					Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
					form.setIdCliente(cliente.getId().toString());
					form.setNomeCliente(cliente.getNome());
					sessao.setAttribute("clienteInexistente", true);
				} else {
					form.setIdCliente("");
					form.setNomeCliente("Cliente Inexistente");
					sessao.removeAttribute("clienteInexistente");
				}
			} else {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio",null, "Cliente Responsável");
			}
		} else if ( consulta.equals("clienteAssociado") ) {
			
			if ( form.getIdClienteAssociado() != null && !form.getIdClienteAssociado().equals("") && Util.verificaSeNumeroNatural(form.getIdClienteAssociado()) ) {
				
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro( new ParametroSimples(FiltroCliente.ID, Integer.valueOf(form.getIdClienteAssociado())));
				
				Collection<Cliente> colecaoCliente = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
				if ( colecaoCliente != null && !colecaoCliente.isEmpty() ) {
					
					Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
					form.setIdClienteAssociado(cliente.getId().toString());
					form.setNomeClienteassociado(cliente.getNome());
					sessao.setAttribute("clienteAssociadoInexistente", true);
				} else {
					form.setIdClienteAssociado("");
					form.setNomeClienteassociado("Cliente Inexistente");
					sessao.removeAttribute("clienteAssociadoInexistente");
				}
			} else {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio",null, "Cliente Responsável");
			}
		}
	}
	
	private String validarCliente(AtualizarContaNovoClienteActionForm form) {
		String retorno = null;
		if ( form.getIdCliente() != null && !form.getIdCliente().equals("")) {
			
			if ( Util.verificaSeNumeroNatural(form.getIdCliente()) ) {
				
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro( new ParametroSimples(FiltroCliente.ID, Integer.valueOf(form.getIdCliente())));
				
				Collection<Cliente> colecaoCliente = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
				if ( colecaoCliente == null || colecaoCliente.isEmpty() ) {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Cliente Responsável");
				} else {
					retorno = form.getIdCliente();
				}
			} else {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio",null, "Cliente Responsável");
			}
		}
		return retorno ;
	}
	
	private String validarImovel(AtualizarContaNovoClienteActionForm form, HttpSession sessao ) {
		String retorno = null;
		if ( form.getMatricula() != null && !form.getMatricula().equals("")) {
			
			if ( Util.verificaSeNumeroNatural(form.getMatricula()) ) {
				
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro( new ParametroSimples(FiltroImovel.ID, Integer.valueOf(form.getMatricula())));
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
	
				Collection<Imovel> colecaoImovel = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
				if ( colecaoImovel == null || colecaoImovel.isEmpty() ) {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Imóvel");
				} else {
					retorno = form.getMatricula();
					sessao.setAttribute("inscricaoImovelEncontrada", true);
				}
			} else {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null, "Matrícula do Imóvel");
			}
		}
		return retorno;
	}


}
