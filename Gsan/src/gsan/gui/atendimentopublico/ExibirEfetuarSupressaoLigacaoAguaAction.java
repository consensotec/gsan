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
package gsan.gui.atendimentopublico;

import gsan.atendimentopublico.ligacaoagua.FiltroSupressaoTipo;
import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.SupressaoTipo;
import gsan.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
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
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
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
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 14/07/2006
 */
public class ExibirEfetuarSupressaoLigacaoAguaAction extends GcomAction {

	/**
	 * [UC0360] Efetuar Supressao de �gua
	 * 
	 * Este caso de uso permite efetuar supress�o da liga��o de �gua, sendo
	 * chamada pela funcionalidade que encerra a execu��o da ordem de servi�o ou
	 * chamada diretamente do menu.
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

		ActionForward retorno = actionMapping.findForward("efetuarSupressaoLigacaoAgua");

		EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm = (EfetuarSupressaoLigacaoAguaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();		

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (efetuarSupressaoLigacaoAguaActionForm.getVeioEncerrarOS() != null
					&& !efetuarSupressaoLigacaoAguaActionForm
							.getVeioEncerrarOS().equals("")) {
				if (efetuarSupressaoLigacaoAguaActionForm.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");				

		String idOrdemServico = null;
		if (efetuarSupressaoLigacaoAguaActionForm.getIdOrdemServico() != null) {
			idOrdemServico = efetuarSupressaoLigacaoAguaActionForm
					.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest
					.getAttribute("veioEncerrarOS");
			efetuarSupressaoLigacaoAguaActionForm.setDataSupressao(
					(String) httpServletRequest.getAttribute("dataEncerramento"));
				
				sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}

		this.pesquisarSelectObrigatorio(httpServletRequest,
				efetuarSupressaoLigacaoAguaActionForm);

		/*
		 * // Verifica se o id da Ordem de servico vem da sessao. if
		 * (sessao.getAttribute("idOrdemServico") != null) { idOrdemServico =
		 * (String) sessao.getAttribute("idOrdemServico"); } else {
		 * idOrdemServico = efetuarSupressaoLigacaoAguaActionForm
		 * .getIdOrdemServico(); }
		 */

		OrdemServico ordemServico = null;

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				fachada.validarExibirSupressaoLigacaoAgua(ordemServico,
						veioEncerrarOS);

				efetuarSupressaoLigacaoAguaActionForm.setIdOrdemServico(idOrdemServico);
				efetuarSupressaoLigacaoAguaActionForm.setVeioEncerrarOS(""
						+ veioEncerrarOS);

				efetuarSupressaoLigacaoAguaActionForm
						.setNomeOrdemServico(ordemServico.getServicoTipo()
								.getDescricao());

				sessao.setAttribute("ordemServico", ordemServico);

				//Comentado por Raphael Rossiter em 28/02/2007
				//Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
				Imovel imovel = ordemServico.getImovel();
				BigDecimal valorDebito = new BigDecimal(0);
				
				if (ordemServico.getServicoTipo() != null
						&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
					efetuarSupressaoLigacaoAguaActionForm
							.setIdTipoDebito(ordemServico.getServicoTipo()
									.getDebitoTipo().getId().toString());
					efetuarSupressaoLigacaoAguaActionForm
							.setDescricaoTipoDebito(ordemServico
									.getServicoTipo().getDebitoTipo()
									.getDescricao());
					
					
					//[FS0013] - Altera��o de Valor
					this.permitirAlteracaoValor(ordemServico.getServicoTipo(), efetuarSupressaoLigacaoAguaActionForm);
					
					String calculaValores = httpServletRequest.getParameter("calculaValores");									
					
					Integer qtdeParcelas = null;
					
					if(calculaValores != null && calculaValores.equals("S")){
						
						//[UC0186] - Calcular Presta��o
						BigDecimal  taxaJurosFinanciamento = null; 
						qtdeParcelas = new Integer(efetuarSupressaoLigacaoAguaActionForm.getQuantidadeParcelas());
						
						if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
							qtdeParcelas.intValue() != 1){
							
							taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
						}else{
							taxaJurosFinanciamento = new BigDecimal(0);
						}
						
						BigDecimal valorPrestacao = null;
						if(taxaJurosFinanciamento != null){
							
							valorDebito = Util.formatarMoedaRealparaBigDecimal(efetuarSupressaoLigacaoAguaActionForm.getValorDebito());
							
							String percentualCobranca = efetuarSupressaoLigacaoAguaActionForm.getPercentualCobranca();
							
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
							efetuarSupressaoLigacaoAguaActionForm.setValorParcelas(valorPrestacaoComVirgula);
						} else {
							efetuarSupressaoLigacaoAguaActionForm.setValorParcelas("0,00");
						}						
						
					}else{
						
						// Valor do D�bitou
						short tipoMedicao = 1;
						
						valorDebito = Fachada.getInstancia().obterValorDebito(
								ordemServico.getServicoTipo().getId(),
								ordemServico.getImovel().getId(), tipoMedicao);

						efetuarSupressaoLigacaoAguaActionForm.setValorDebito(Util
								.formataBigDecimal(valorDebito, 2, true));
						
					}

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						efetuarSupressaoLigacaoAguaActionForm
								.setMotivoNaoCobranca(ordemServico
										.getServicoNaoCobrancaMotivo().getId()
										.toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						efetuarSupressaoLigacaoAguaActionForm
								.setPercentualCobranca(ordemServico
										.getPercentualCobranca().toString());
					}
				}

				String matriculaImovel = imovel.getId().toString();
				efetuarSupressaoLigacaoAguaActionForm.setMatriculaImovel(""
						+ matriculaImovel);

				/*-------------- In�cio dados do Im�vel---------------*/

				//Comentado por Raphael Rossiter em 28/02/2007
				//sessao.setAttribute("imovel", ordemServico.getRegistroAtendimento().getImovel());
				sessao.setAttribute("imovel", ordemServico.getImovel());

				if (imovel != null) {

					// Matricula Im�vel
					efetuarSupressaoLigacaoAguaActionForm
							.setMatriculaImovel(imovel.getId().toString());

					// Inscri��o Im�vel
					String inscricaoImovel = fachada
							.pesquisarInscricaoImovel(imovel.getId());
					efetuarSupressaoLigacaoAguaActionForm
							.setInscricaoImovel(inscricaoImovel);

					// Situa��o da Liga��o de Agua

					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					efetuarSupressaoLigacaoAguaActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					efetuarSupressaoLigacaoAguaActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(
							efetuarSupressaoLigacaoAguaActionForm, new Integer(
									matriculaImovel));
				}

				// Data Encerramento
				String dataEncerramentoOdServico = Util
						.formatarData(ordemServico.getDataEncerramento());
                if(dataEncerramentoOdServico != null && !dataEncerramentoOdServico.equals("")){
				  efetuarSupressaoLigacaoAguaActionForm
						.setDataSupressao(dataEncerramentoOdServico);
                }

				LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

				// Supressao Motivo
				if (ligacaoAgua.getSupressaoMotivo() != null) {

					String supressaoMotivo = ligacaoAgua.getSupressaoMotivo()
							.getId().toString();
					efetuarSupressaoLigacaoAguaActionForm
							.setMotivoSupressao(supressaoMotivo);
				}

				// Supressao Tipo

				
				if (httpServletRequest.getParameter("combo") == null) {
					if (ligacaoAgua.getSupressaoTipo() != null && 
							!sistemaParametro.getNomeAbreviadoEmpresa().equals("CAERN")) {

						String supressaoTipo = ligacaoAgua.getSupressaoTipo()
								.getId().toString();
						efetuarSupressaoLigacaoAguaActionForm
								.setTipoSupressao(supressaoTipo);

						if (ligacaoAgua.getSupressaoTipo().getIndicadorTotal() == ConstantesSistema.INDICADOR_USO_ATIVO
								.shortValue()) {
							efetuarSupressaoLigacaoAguaActionForm
									.setIndicadorTipoSupressao(ConstantesSistema.INDICADOR_USO_ATIVO
											.toString());
						} else {
							efetuarSupressaoLigacaoAguaActionForm
									.setIndicadorTipoSupressao(ConstantesSistema.INDICADOR_USO_DESATIVO
											.toString());
						}

					}else{
						
						efetuarSupressaoLigacaoAguaActionForm
						.setIndicadorTipoSupressao(ConstantesSistema.INDICADOR_USO_ATIVO
								.toString());
					}
				}
				// Selo Supress�o
				if (ligacaoAgua.getNumeroSeloSupressao() != null) {

					String NumeroSeloSupressao = ligacaoAgua
							.getNumeroSeloSupressao().toString();
					efetuarSupressaoLigacaoAguaActionForm
							.setNumeroSeloSupressao(NumeroSeloSupressao);

				} else {
					efetuarSupressaoLigacaoAguaActionForm
							.setNumeroSeloSupressao("");
				}
				
				if (ligacaoAgua.getHidrometroInstalacaoHistorico() != null) {

					FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();

					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.ID,
									ligacaoAgua
											.getHidrometroInstalacaoHistorico()
											.getId()));

					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");

					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");

					Collection colecaoHidrometroInstalacaoHistorico = fachada
							.pesquisar(filtroHidrometroInstalacaoHistorico,
									HidrometroInstalacaoHistorico.class
											.getName());

					HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistorico
							.iterator().next();

					if (hidrometroInstalacaoHistorico != null && hidrometroInstalacaoHistorico
							.getNumeroLeituraSupressao() != null) {
						efetuarSupressaoLigacaoAguaActionForm
								.setNumeroLeituraSupressao(""
										+ hidrometroInstalacaoHistorico
												.getNumeroLeituraSupressao());
					} else {
						efetuarSupressaoLigacaoAguaActionForm
								.setNumeroLeituraSupressao("");
					}

				} else {
					efetuarSupressaoLigacaoAguaActionForm
							.setNumeroLeituraSupressao("");
				}

				// Filtro para o campo Tipo Debito
				Collection colecaoNaoCobranca = (Collection) sessao
						.getAttribute("colecaoNaoCobranca");
				if (colecaoNaoCobranca == null) {
					FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

					filtroServicoNaoCobrancaMotivo
							.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

					colecaoNaoCobranca = fachada.pesquisar(
							filtroServicoNaoCobrancaMotivo,
							ServicoNaoCobrancaMotivo.class.getName());

					if (colecaoNaoCobranca != null
							&& !colecaoNaoCobranca.isEmpty()) {
						sessao.setAttribute("colecaoNaoCobranca",
								colecaoNaoCobranca);
					} else {
						throw new ActionServletException(
								"atencao.naocadastrado", null,
								"Motivo da N�o Cobran�a");
					}
				}
				// Dados da Gera��o de D�bito
				//this.pesquisarDadosGeracaoDebito(efetuarSupressaoLigacaoAguaActionForm, ordemServico);
				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, ordemServico.getServicoTipo().getId()));

				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoPerfilTipo");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoSubgrupo");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoAtividades");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoMateriais");

				Collection colecaoServicoTipo = Fachada.getInstancia().pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());

				ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();

				// Filtro para carregar o Cliente
				if (servicoTipo.getDebitoTipo() != null){
					
					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

					filtroDebitoTipo.adicionarParametro(new ParametroSimples(
							FiltroDebitoTipo.ID, servicoTipo.getDebitoTipo().getId().toString()));

					Collection colecaoDebitoTipo = Fachada.getInstancia().pesquisar(
							filtroDebitoTipo, DebitoTipo.class.getName());

					DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();

					if (debitoTipo.getId() != null && !debitoTipo.getId().equals("")) {

						// Codigo/descricao
						efetuarSupressaoLigacaoAguaActionForm.setIdTipoDebito(debitoTipo
								.getId().toString());
						efetuarSupressaoLigacaoAguaActionForm
								.setDescricaoTipoDebito(debitoTipo.getDescricao());
					} else {
						// Codigo/descricao
						efetuarSupressaoLigacaoAguaActionForm.setIdTipoDebito("");
						efetuarSupressaoLigacaoAguaActionForm.setDescricaoTipoDebito("");

					}
				}

				
				// -----------------------------------------------------------
				// Verificar permiss�o especial
				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------
				
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					efetuarSupressaoLigacaoAguaActionForm.setPercentualCobranca("100");
					efetuarSupressaoLigacaoAguaActionForm.setQuantidadeParcelas("1");
					efetuarSupressaoLigacaoAguaActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}

			} else {

				httpServletRequest
						.setAttribute("OrdemServicoInexistente", true);
				efetuarSupressaoLigacaoAguaActionForm.setIdOrdemServico("");
				efetuarSupressaoLigacaoAguaActionForm
						.setNomeOrdemServico("ORDEM DE SERVI�O INEXISTENTE");

			}
		}

		return retorno;
	}
	
	
	/*
	 * [FS0013 - Altera��o de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarSupressaoLigacaoAguaActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(
			EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			efetuarSupressaoLigacaoAguaActionForm.setClienteUsuario(cliente
					.getNome());
			efetuarSupressaoLigacaoAguaActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	// Dados da Gera��o de D�bito
/*	private void pesquisarDadosGeracaoDebito(
			EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm,
			OrdemServico ordemServico) {



	}*/

	/**
	 * Pesquisa o local de instala��o Pesquisa hidrometro prote��o
	 */
	private void pesquisarSelectObrigatorio(
			HttpServletRequest httpServletRequest,
			EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// ************ Tipo Supressao***************

		// Vericacao dequal o tipo de supressao informada
		// pelo usuario para
		// habilitar no combo box as opcoes correspondentes
		// Parte relativa ao campo Tipo da Supressao Parcial

		FiltroSupressaoTipo filtroSupressaoTipo = new FiltroSupressaoTipo();

		if (efetuarSupressaoLigacaoAguaActionForm.getIndicadorTipoSupressao() != null) {

			if (efetuarSupressaoLigacaoAguaActionForm
					.getIndicadorTipoSupressao().equals("1")) {

				filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSupressaoTipo.INDICADOR_TOTAL,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSupressaoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection<SupressaoTipo> colecaoSupressaoTipo = fachada
						.pesquisar(filtroSupressaoTipo, SupressaoTipo.class
								.getName());

				if (colecaoSupressaoTipo == null
						|| colecaoSupressaoTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"Tabela Supress�o Tipo ");
				}

				efetuarSupressaoLigacaoAguaActionForm
						.setIndicadorTipoSupressao("1");
				httpServletRequest.setAttribute("colecaoSupressaoTipo",
						colecaoSupressaoTipo);

			} else {
				if (efetuarSupressaoLigacaoAguaActionForm
						.getIndicadorTipoSupressao().equals("2")) {

					filtroSupressaoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroSupressaoTipo.INDICADOR_PARCIAL,
									ConstantesSistema.INDICADOR_USO_ATIVO));
					filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSupressaoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection<SupressaoTipo> colecaoSupressaoTipo = fachada
							.pesquisar(filtroSupressaoTipo, SupressaoTipo.class
									.getName());

					if (colecaoSupressaoTipo == null
							|| colecaoSupressaoTipo.isEmpty()) {
						throw new ActionServletException(
								"atencao.entidade_sem_dados_para_selecao",
								null, "Tabela Supress�o Tipo ");
					}
					efetuarSupressaoLigacaoAguaActionForm
							.setIndicadorTipoSupressao("2");
					httpServletRequest.setAttribute("colecaoSupressaoTipo",
							colecaoSupressaoTipo);
				}
			}
		} else {

			efetuarSupressaoLigacaoAguaActionForm
					.setIndicadorTipoSupressao("1");

			filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSupressaoTipo.INDICADOR_TOTAL,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
				FiltroSupressaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<SupressaoTipo> colecaoSupressaoTipo = fachada.pesquisar(
					filtroSupressaoTipo, SupressaoTipo.class.getName());

			if (colecaoSupressaoTipo == null || colecaoSupressaoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Tabela Supress�o Tipo ");
			}
			httpServletRequest.setAttribute("colecaoSupressaoTipo",
					colecaoSupressaoTipo);

		}
		// Supressao Motivo
		FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();
		filtroSupressaoMotivo.adicionarParametro(new ParametroSimples(
			FiltroSupressaoMotivo.INDICADOR_USO,
			ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<SupressaoMotivo> colecaoSupressaoMotivo = fachada.pesquisar(
				filtroSupressaoMotivo, SupressaoMotivo.class.getName());

		httpServletRequest.setAttribute("colecaoMotivoSupressao",
				colecaoSupressaoMotivo);

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = Fachada.getInstancia().pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da N�o Cobran�a");
			}
		}
	}

}
