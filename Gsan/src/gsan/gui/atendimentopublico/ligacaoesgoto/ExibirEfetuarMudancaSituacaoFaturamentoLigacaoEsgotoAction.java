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
package gsan.gui.atendimentopublico.ligacaoesgoto;

import gsan.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gsan.atendimentopublico.ligacaoagua.MotivoCorte;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gsan.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gsan.atendimentopublico.ordemservico.FiltroSupressaoMotivo;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.ordemservico.SupressaoMotivo;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.consumo.LigacaoTipo;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de efetuar corte de ligação de
 * água
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class ExibirEfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction extends
		GcomAction {
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

		HttpSession sessao = httpServletRequest.getSession(false);
		ActionForward retorno = actionMapping
				.findForward("efetuarMudancaSituacaoFaturamentoLigacaoEsgoto");
		EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm mudancaFaturamentoLigacaoAguaActionForm = 
				(EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		mudancaFaturamentoLigacaoAguaActionForm.setMostrarVolume(true);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		String idOrdemServico = null;
		if(mudancaFaturamentoLigacaoAguaActionForm.getIdOrdemServico() != null){
			idOrdemServico = mudancaFaturamentoLigacaoAguaActionForm.getIdOrdemServico();
		}else{
			idOrdemServico = (String)httpServletRequest.getAttribute("veioEncerrarOS");
			mudancaFaturamentoLigacaoAguaActionForm
					.setDataMudanca(
					(String) httpServletRequest.getAttribute("dataEncerramento"));
				
				sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}
		
		OrdemServico ordemServico = null;
		
		if(httpServletRequest.getAttribute("semMenu") != null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		
		mudancaFaturamentoLigacaoAguaActionForm.setVolumeMinimoFixado(null);
		
		Boolean veioEncerrarOS = null;
		if(httpServletRequest.getAttribute("veioEncerrarOS") != null){
			veioEncerrarOS = Boolean.TRUE;
		}else{
			if (mudancaFaturamentoLigacaoAguaActionForm.getVeioEncerrarOS() != null
					&& !mudancaFaturamentoLigacaoAguaActionForm
							.getVeioEncerrarOS().equals("")) {
				if (mudancaFaturamentoLigacaoAguaActionForm.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}
		
		mudancaFaturamentoLigacaoAguaActionForm.setVeioEncerrarOS(""+veioEncerrarOS);
		
		//Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) 
			sessao.getAttribute("colecaoNaoCobranca");
		if(colecaoNaoCobranca == null){
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
			
			filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
			
			colecaoNaoCobranca = fachada.pesquisar(filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());
			
			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca",colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Motivo da Não Cobrança");
			}
		}

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			
			ordemServico = fachada.recuperaOSPorId(
					new Integer(idOrdemServico));
			
			Collection colecaoMotivo = null;

			if (ordemServico != null) {							
				
				if(ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_DESATIVACAO_LIGACAO_ESGOTO)
						|| ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_TAMPONAMENTO_LIGACAO_ESGOTO)){
					sessao.setAttribute("desativacaoRamalEsgoto", true);
					
					if(ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_DESATIVACAO_LIGACAO_ESGOTO)){
						//Motivo Corte
						FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
						colecaoMotivo = fachada.pesquisar(filtroMotivoCorte, MotivoCorte.class.getName());
						
						if(!Util.isVazioOrNulo(colecaoMotivo)){
							sessao.setAttribute("colecaoMotivo",
								colecaoMotivo);
						}
					}else{
						// Supressao Motivo
						FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();
				
						colecaoMotivo = fachada.pesquisar(
								filtroSupressaoMotivo, SupressaoMotivo.class.getName());
				
						sessao.setAttribute("colecaoMotivo",
							colecaoMotivo);
					}										
				}else{
					sessao.removeAttribute("desativacaoRamalEsgoto");					
				}
				
				
				String tipoResultado  = fachada.validarMudancaSituacaoFaturamentoLigacaoesgotoExibir(ordemServico,veioEncerrarOS);
				if (tipoResultado!= null){
					if (tipoResultado.trim().equalsIgnoreCase("TAMPONADO")){
							mudancaFaturamentoLigacaoAguaActionForm.setNovaSitLigacaoEsgoto("SUPRIMIDO");
							mudancaFaturamentoLigacaoAguaActionForm.setMostrarVolume(false);
						}else if (tipoResultado.trim().equalsIgnoreCase("LIGADO FORA DE USO")){
							mudancaFaturamentoLigacaoAguaActionForm.setMostrarVolume(false);
							mudancaFaturamentoLigacaoAguaActionForm.setNovaSitLigacaoEsgoto("CORTADO");
						}else{
							mudancaFaturamentoLigacaoAguaActionForm.setMostrarVolume(true);
							mudancaFaturamentoLigacaoAguaActionForm.setNovaSitLigacaoEsgoto("LIGADO");
						}
				}
							mudancaFaturamentoLigacaoAguaActionForm
									.setIdOrdemServico(idOrdemServico);
							mudancaFaturamentoLigacaoAguaActionForm
									.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
		
				/*-------------- Início dados do Imóvel---------------*/
					Imovel imovel = ordemServico.getRegistroAtendimento()
					.getImovel();
					sessao.setAttribute("imovel", ordemServico
							.getRegistroAtendimento().getImovel());
					
					FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
					filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, imovel.getId()));
					
					Collection colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
					if(!colecaoLigacaoEsgoto.isEmpty()){
						LigacaoEsgoto ligacaoEsgoto = (LigacaoEsgoto)Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
						
						if(ligacaoEsgoto.getMotivoCorte() != null){
							MotivoCorte motivoCorte = ligacaoEsgoto.getMotivoCorte();
							mudancaFaturamentoLigacaoAguaActionForm.setMotivoCorteSupressao(motivoCorte.getId().toString());
						}else if(ligacaoEsgoto.getSupressaoMotivo() != null){
							SupressaoMotivo motivoSupressao = ligacaoEsgoto.getSupressaoMotivo();
							mudancaFaturamentoLigacaoAguaActionForm.setMotivoCorteSupressao(motivoSupressao.getId().toString());
						}
						
						
						sessao.setAttribute("ligacaoEsgoto", ligacaoEsgoto);
					}
					
					sessao.setAttribute("ordemServico", ordemServico);
					
					BigDecimal valorDebito = new BigDecimal(0.00);
					if(ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getDebitoTipo() != null){
						mudancaFaturamentoLigacaoAguaActionForm.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
						mudancaFaturamentoLigacaoAguaActionForm.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao());
						
						 
						//[FS0013] - Alteração de Valor
						this.permitirAlteracaoValor(ordemServico.getServicoTipo(), mudancaFaturamentoLigacaoAguaActionForm);
						
						//Colocado por Raphael Rossiter em 04/05/2007 (Analista: Rosana Carvalho)
						valorDebito = this.calcularValores(httpServletRequest, ordemServico, 
						mudancaFaturamentoLigacaoAguaActionForm);
						
					
						if(ordemServico.getServicoNaoCobrancaMotivo() != null){
							mudancaFaturamentoLigacaoAguaActionForm.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
						}
						
						if(ordemServico.getPercentualCobranca() != null){
							mudancaFaturamentoLigacaoAguaActionForm.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
						}
					}
					
					if (imovel != null) {
						
						//Matricula Imóvel
						String matriculaImovel = ordemServico
								.getRegistroAtendimento().getImovel().getId()
								.toString();
						mudancaFaturamentoLigacaoAguaActionForm
								.setMatriculaImovel(matriculaImovel);
	
						//Inscrição Imóvel
						String inscricaoImovel = fachada.pesquisarInscricaoImovel(ordemServico
								.getRegistroAtendimento().getImovel()
								.getId());
						mudancaFaturamentoLigacaoAguaActionForm
								.setInscricaoImovel(inscricaoImovel);
	
						// Situação da Ligação de Agua
						String situacaoLigacaoAgua = imovel
								.getLigacaoAguaSituacao().getDescricao();
						mudancaFaturamentoLigacaoAguaActionForm
								.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
	
						// Situação da Ligação de Esgoto
						String situacaoLigacaoEsgoto = imovel
								.getLigacaoEsgotoSituacao().getDescricao();
						mudancaFaturamentoLigacaoAguaActionForm
								.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
	
						//Filtro para carregaar o Cliente	
						FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
	
						filtroClienteImovel
								.adicionarParametro(new ParametroSimples(
										FiltroClienteImovel.IMOVEL_ID,
										matriculaImovel));
	
						filtroClienteImovel
								.adicionarParametro(new ParametroSimples(
										FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
										ClienteRelacaoTipo.USUARIO));
	
						filtroClienteImovel.adicionarParametro(new ParametroNulo(
								FiltroClienteImovel.DATA_FIM_RELACAO));
	
						filtroClienteImovel
								.adicionarCaminhoParaCarregamentoEntidade("cliente");
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
	
						Collection colecaoClienteImovel = fachada.pesquisar(
								filtroClienteImovel, ClienteImovel.class.getName());
	
						if (colecaoClienteImovel != null
								&& !colecaoClienteImovel.isEmpty()) {
	
							ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
									.iterator().next();
							Cliente cliente = clienteImovel.getCliente();
	
							String documento = "";
	
							if (cliente.getCpf() != null
									&& !cliente.getCpf().equals("")) {
								documento = cliente.getCpfFormatado();
							} else {
								documento = cliente.getCnpjFormatado();
							}
							//Cliente Nome/CPF-CNPJ
							mudancaFaturamentoLigacaoAguaActionForm
									.setClienteUsuario(cliente.getNome());
							mudancaFaturamentoLigacaoAguaActionForm
									.setCpfCnpjCliente(documento);
	
						} else {
							throw new ActionServletException(
									"atencao.naocadastrado", null, "Cliente");
						}
					}
					/*-------------- Fim dados do Imóvel---------------*/
	
					/*-------------- Dados da Ligação ----------------------------*/
					
					// Carregando Data do Corte: Data recebida da execução da
							
					String  dataEncerramentoOdServico= Util.formatarData(ordemServico.getDataEncerramento());
					if(dataEncerramentoOdServico != null && !dataEncerramentoOdServico.equals("")){
					  mudancaFaturamentoLigacaoAguaActionForm
							.setDataMudanca(dataEncerramentoOdServico);
					}
	
					// Carregando campo Volume Mínimo Fixado
	
					if (imovel.getQuantidadeEconomias() != null) {
						mudancaFaturamentoLigacaoAguaActionForm
								.setQtdeEconomia(imovel.getQuantidadeEconomias()
										.toString());
					} else {
						// Se entrar aqui é porque a Base está inconsistente.
						mudancaFaturamentoLigacaoAguaActionForm
								.setQtdeEconomia("1");
					}
	
					// -----------------------------------------------------------
					// Verificar permissão especial
					boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
					// -----------------------------------------------------------
					
					if (temPermissaoMotivoNaoCobranca) {
						httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
					}else{
						mudancaFaturamentoLigacaoAguaActionForm.setPercentualCobranca("100");
						mudancaFaturamentoLigacaoAguaActionForm.setQuantidadeParcelas("1");
						mudancaFaturamentoLigacaoAguaActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
					}

				} else {
					mudancaFaturamentoLigacaoAguaActionForm.setNomeOrdemServico("Ordem de Serviço inexistente");
					mudancaFaturamentoLigacaoAguaActionForm.setIdOrdemServico("");
					httpServletRequest.setAttribute("OrdemServioInexistente", true);
				}
				/*-------------------- Fim Dados da Ligação ----------------------------*/
			} else {
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				mudancaFaturamentoLigacaoAguaActionForm.setIdOrdemServico("");
				mudancaFaturamentoLigacaoAguaActionForm.setMatriculaImovel("");
				mudancaFaturamentoLigacaoAguaActionForm.setInscricaoImovel("");
				mudancaFaturamentoLigacaoAguaActionForm.setClienteUsuario("");
				mudancaFaturamentoLigacaoAguaActionForm.setCpfCnpjCliente("");
				mudancaFaturamentoLigacaoAguaActionForm.setSituacaoLigacaoAgua("");
				mudancaFaturamentoLigacaoAguaActionForm.setSituacaoLigacaoEsgoto("");
				mudancaFaturamentoLigacaoAguaActionForm.setNomeOrdemServico("");
				mudancaFaturamentoLigacaoAguaActionForm.setIdTipoDebito("");
				mudancaFaturamentoLigacaoAguaActionForm.setDescricaoTipoDebito("");
				mudancaFaturamentoLigacaoAguaActionForm.setQuantidadeParcelas("");
				mudancaFaturamentoLigacaoAguaActionForm.setValorParcelas("");
				mudancaFaturamentoLigacaoAguaActionForm.setPercentualCobranca("-1");
				mudancaFaturamentoLigacaoAguaActionForm.setMotivoNaoCobranca("-1");
			}
		
			
		
			return retorno;
		}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	
	
	/*
	 * Calcular valor da prestação com juros
	 * 
	 * return: Retorna o valor total do débito
	 * 
	 * autor: Raphael Rossiter
	 * data: 04/05/2007
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest, OrdemServico ordemServico,
			EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm form){
		
		String calculaValores = httpServletRequest.getParameter("calculaValores");
		
		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		Integer qtdeParcelas = null;
		
		if(calculaValores != null && calculaValores.equals("S")){
			
			//[UC0186] - Calcular Prestação
			BigDecimal  taxaJurosFinanciamento = null; 
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());
			
			if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
					qtdeParcelas.intValue() > 1){
				
				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			}else{
				taxaJurosFinanciamento = new BigDecimal(0);
				qtdeParcelas = 1;
			}
			
			BigDecimal valorPrestacao = null;
			if(taxaJurosFinanciamento != null){
				
				valorDebito = new BigDecimal(form.getValorDebito().replace(",","."));
				
				String percentualCobranca = form.getPercentualCobranca();
				
				if(percentualCobranca.equals("70")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				}else if (percentualCobranca.equals("50")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}
				
				valorPrestacao =
					this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento,
						qtdeParcelas, 
						valorDebito, 
						new BigDecimal("0.00"));
				
				valorPrestacao.setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			
			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao,2,true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}						
			
		}else{
			
			valorDebito = Fachada.getInstancia().obterValorDebito(ordemServico.getServicoTipo().getId(),
			ordemServico.getRegistroAtendimento().getImovel().getId(), new Short(LigacaoTipo.LIGACAO_AGUA+""));
			
			form.setValorDebito(Util.formataBigDecimal(valorDebito,2,true));
		}
		
		
		return valorDebito;
	}
}