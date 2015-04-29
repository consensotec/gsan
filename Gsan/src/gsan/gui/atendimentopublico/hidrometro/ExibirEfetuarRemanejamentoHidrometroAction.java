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
package gsan.gui.atendimentopublico.hidrometro;

import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
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
import gsan.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

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
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 28/06/2006
 */
public class ExibirEfetuarRemanejamentoHidrometroAction extends GcomAction {

	/**
	 * [UC0365] Efetuar Remanejamento de Hidr�metro
	 * 
	 * Este caso de uso permite efetuar o remanejamento de hidrometro, sendo
	 * chamado pela funcionalidade que encerra a execu��o da ordem de servi�o ou
	 * chamada diretamente do Menu.
	 * 
	 * @param actionMapping Description of the Parameter
	 * @param actionForm Description of the Parameter
	 * @param httpServletRequest Description of the Parameter
	 * @param httpServletResponse Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("efetuarRemanejamentoHidrometro");

		EfetuarRemanejamentoHidrometroActionForm efetuarRemanejamentoHidrometroActionForm = (EfetuarRemanejamentoHidrometroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS() != null
					&& !efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS().equals("")) {
				if (efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS().toLowerCase().equals("true")) {
					veioEncerrarOS =  Boolean.TRUE;
				} else {
					veioEncerrarOS =  Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		String idOrdemServico = null;

		// Verifica se o id da Ordem de servico vem da sessao.
		if (efetuarRemanejamentoHidrometroActionForm.getIdOrdemServico() != null) {
			idOrdemServico = efetuarRemanejamentoHidrometroActionForm.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest.getAttribute("veioEncerrarOS");
			efetuarRemanejamentoHidrometroActionForm.setDataInstalacao((String) httpServletRequest.getAttribute("dataEncerramento"));

			sessao.setAttribute("caminhoRetornoIntegracaoComercial", httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}

		OrdemServico ordemServico = null;

		this.pesquisarSelectObrigatorio(httpServletRequest);

		// Ordem de Servi�o
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				fachada.validarExibirRemanejmentoHidrometroAgua(ordemServico, veioEncerrarOS);

				sessao.setAttribute("ordemServico", ordemServico);
				efetuarRemanejamentoHidrometroActionForm.setIdOrdemServico(idOrdemServico);
				efetuarRemanejamentoHidrometroActionForm.setVeioEncerrarOS("" + veioEncerrarOS);
				efetuarRemanejamentoHidrometroActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());

				Imovel imovel = null;
				if (ordemServico.getRegistroAtendimento() != null
						&& ordemServico.getRegistroAtendimento().getImovel() != null) {
					imovel = ordemServico.getRegistroAtendimento().getImovel();
				} else if (ordemServico.getImovel() != null) {
					imovel = ordemServico.getImovel();
				}

				if (ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getDebitoTipo() != null) {
					efetuarRemanejamentoHidrometroActionForm.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
					efetuarRemanejamentoHidrometroActionForm.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao());

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						efetuarRemanejamentoHidrometroActionForm.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						efetuarRemanejamentoHidrometroActionForm.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
					}
				}

				sessao.setAttribute("imovel", imovel);

				String matriculaImovel = imovel.getId().toString();
				efetuarRemanejamentoHidrometroActionForm.setMatriculaImovel("" + matriculaImovel);

				/*-------------- In�cio dados do Im�vel---------------*/

				// sessao.setAttribute("imovel", ordemServico
				// .getRegistroAtendimento().getImovel());

				if (imovel != null) {
					// Matricula Im�vel
					efetuarRemanejamentoHidrometroActionForm.setMatriculaImovel(imovel.getId().toString());

					// Inscri��o Im�vel
					String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId());
					efetuarRemanejamentoHidrometroActionForm.setInscricaoImovel(inscricaoImovel);

					// Situa��o da Liga��o de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					efetuarRemanejamentoHidrometroActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					efetuarRemanejamentoHidrometroActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(efetuarRemanejamentoHidrometroActionForm, new Integer(matriculaImovel));
				}

				// Situa��o da Liga��o de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
				efetuarRemanejamentoHidrometroActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situa��o da Liga��o de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();

				efetuarRemanejamentoHidrometroActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				// LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
				// Tipo medi��o - Liga��o �gua
				if (ordemServico.getRegistroAtendimento() == null
						|| ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorLigacaoAgua().equals(ConstantesSistema.SIM)) {

					LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

					if (ligacaoAgua.getHidrometroInstalacaoHistorico() == null) {
						throw new ActionServletException("atencao.hidrometro_instalado_nao_existente", null, " na Liga��o de �gua ");
					}

					filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.ID, ligacaoAgua.getHidrometroInstalacaoHistorico().getId()));

					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");

					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");

					Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

					hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistorico.iterator().next();

					sessao.setAttribute("hidrometroInstalacaoHistorico", hidrometroInstalacaoHistorico);
				} else if (ordemServico.getRegistroAtendimento() != null
						&& (ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorPoco().equals(ConstantesSistema.SIM)
								|| ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorLigacaoEsgoto().equals(ConstantesSistema.SIM))) {

					// Tipo medi��o- Po�o
					if (imovel.getHidrometroInstalacaoHistorico() == null) {
						throw new ActionServletException( "atencao.hidrometro_instalado_nao_existente", null, " no Po�o ");
					}

					filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.ID, imovel.getHidrometroInstalacaoHistorico().getId()));
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
					filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");

					Collection colecaoHidrometroInstalacaoHistorico = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());

					hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistorico.iterator().next();

					sessao.setAttribute("hidrometroInstalacaoHistorico", hidrometroInstalacaoHistorico);
				}

				// Hidrometro

				efetuarRemanejamentoHidrometroActionForm.setHidrometro(hidrometroInstalacaoHistorico.getHidrometro().getNumero());

				// Data de Remanejamento
				Date dataInstalacao = ordemServico.getDataEncerramento();
				if (dataInstalacao != null && !dataInstalacao.equals("")) {
					efetuarRemanejamentoHidrometroActionForm.setDataInstalacao(Util.formatarData(dataInstalacao));
				}

				// Local de Instalacao
				if (hidrometroInstalacaoHistorico.getHidrometroLocalInstalacao() != null) {
					String localInstalacao = hidrometroInstalacaoHistorico.getHidrometroLocalInstalacao().getId().toString();
					efetuarRemanejamentoHidrometroActionForm.setLocalInstalacao(localInstalacao);
				}

				// Protecao
				if (hidrometroInstalacaoHistorico.getHidrometroProtecao() != null) {
					String protecao = hidrometroInstalacaoHistorico.getHidrometroProtecao().getId().toString();
					efetuarRemanejamentoHidrometroActionForm.setProtecao(protecao);
				}

				// Cavalete
				if (hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete() != null) {
					String cavalete = hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete().toString();
					efetuarRemanejamentoHidrometroActionForm.setTipoCavalete(cavalete);
				}

				// Tipo medi��o - Liga��o �gua
				if (ordemServico.getRegistroAtendimento() == null
						|| ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorLigacaoAgua().equals(ConstantesSistema.SIM)) {

					efetuarRemanejamentoHidrometroActionForm.setTipoMedicao(MedicaoTipo.LIGACAO_AGUA.toString());					
				// Tipo medi��o- Po�o
				} else if (ordemServico.getRegistroAtendimento() != null
						&& ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorPoco().equals(ConstantesSistema.SIM)) {

					efetuarRemanejamentoHidrometroActionForm.setTipoMedicao(MedicaoTipo.POCO.toString());
				// Tipo medi��o- Liga��o Esgoto
				} else  if (ordemServico.getRegistroAtendimento() != null
						&& ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorLigacaoEsgoto().equals(ConstantesSistema.SIM)) {

					efetuarRemanejamentoHidrometroActionForm.setTipoMedicao(MedicaoTipo.LIGACAO_ESGOTO.toString());
				}

				// Dados da Gera��o de D�bito
				// this.pesquisarDadosGeracaoDebito(efetuarRemanejamentoHidrometroActionForm, ordemServico);
				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, ordemServico.getServicoTipo().getId()));

				Collection colecaoServicoTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

				ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();

				// Filtro para carregar o Cliente
				if (servicoTipo.getDebitoTipo() != null) {
					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

					filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, servicoTipo.getDebitoTipo().getId().toString()));

					Collection colecaoDebitoTipo = Fachada.getInstancia().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());

					DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();

					if (debitoTipo.getId() != null && !debitoTipo.getId().equals("")) {
						// Codigo/descricao
						efetuarRemanejamentoHidrometroActionForm.setIdTipoDebito(debitoTipo.getId().toString());
						efetuarRemanejamentoHidrometroActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
					} else {
						// Codigo/descricao
						efetuarRemanejamentoHidrometroActionForm.setIdTipoDebito("");
						efetuarRemanejamentoHidrometroActionForm.setDescricaoTipoDebito("");
					}
				}

				// [FS0013] - Altera��o de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(), efetuarRemanejamentoHidrometroActionForm);

				// Colocado por Raphael Rossiter em 04/05/2007 (Analista: Rosana Carvalho)
				BigDecimal valorDebito = this.calcularValores(httpServletRequest, ordemServico, servicoTipo, efetuarRemanejamentoHidrometroActionForm, imovel);

				// -----------------------------------------------------------
				// Verificar permiss�o especial
				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------

				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				} else {
					efetuarRemanejamentoHidrometroActionForm.setPercentualCobranca("100");
					efetuarRemanejamentoHidrometroActionForm.setQuantidadeParcelas("1");
					efetuarRemanejamentoHidrometroActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito, 2, true));
				}
			} else {
				httpServletRequest.setAttribute("OrdemServicoInexistente", true);
				efetuarRemanejamentoHidrometroActionForm.setIdOrdemServico("");
				efetuarRemanejamentoHidrometroActionForm.setNomeOrdemServico("ORDEM DE SERVI�O INEXISTENTE");
			}
		}
		return retorno;
	}

	/*
	 * [FS0013 - Altera��o de Valor]
	 * 
	 * autor: Raphael Rossiter data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarRemanejamentoHidrometroActionForm form) {
		if (servicoTipo.getIndicadorPermiteAlterarValor() == ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()) {
			form.setAlteracaoValor("OK");
		} else {
			form.setAlteracaoValor("");
		}
	}

	/*
	 * Calcular valor da presta��o com juros
	 * 
	 * return: Retorna o valor total do d�bito
	 * 
	 * autor: Raphael Rossiter data: 04/05/2007
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest,
			OrdemServico ordemServico, ServicoTipo servicoTipo,
			EfetuarRemanejamentoHidrometroActionForm form, Imovel imovel) {

		String calculaValores = httpServletRequest.getParameter("calculaValores");

		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		Integer qtdeParcelas = null;

		if (calculaValores != null && calculaValores.equals("S")) {
			// [UC0186] - Calcular Presta��o
			BigDecimal taxaJurosFinanciamento = null;
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());

			if (ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && qtdeParcelas.intValue() > 1) {
				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			} else {
				taxaJurosFinanciamento = new BigDecimal(0);
				qtdeParcelas = 1;
			}

			BigDecimal valorPrestacao = null;
			if (taxaJurosFinanciamento != null) {
				valorDebito = new BigDecimal(form.getValorDebito().replace(",", "."));

				String percentualCobranca = form.getPercentualCobranca();

				if (percentualCobranca.equals("70")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				} else if (percentualCobranca.equals("50")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}

				valorPrestacao = this.getFachada().calcularPrestacao(taxaJurosFinanciamento, qtdeParcelas, valorDebito, new BigDecimal("0.00"));
				valorPrestacao.setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao, 2, true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}
		} else {
			short tipoMedicao = 1;
			valorDebito = Fachada.getInstancia().obterValorDebito(servicoTipo.getId(), imovel.getId(), tipoMedicao);
			form.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
		}
		return valorDebito;
	}

	/**
	 * Pesquisa o local de instala��o Pesquisa hidrometro prote��o
	 */
	private void pesquisarSelectObrigatorio(HttpServletRequest httpServletRequest) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Local de Instala�ao
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();

		Collection<HidrometroLocalInstalacao> colecaoHidrometroLocalInstalacao = Fachada.getInstancia().pesquisar(filtroHidrometroLocalInstalacao, HidrometroLocalInstalacao.class.getName());

		if (colecaoHidrometroLocalInstalacao == null || colecaoHidrometroLocalInstalacao.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Tabela hidr�metro local de instala��o ");
		}

		httpServletRequest.setAttribute("colecaoHidrometroLocalInstalacao", colecaoHidrometroLocalInstalacao);

		// Hidrometro Protecao
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();

		Collection<HidrometroProtecao> colecaoHidrometroProtecao = Fachada.getInstancia().pesquisar(filtroHidrometroProtecao, HidrometroProtecao.class.getName());

		if (colecaoHidrometroProtecao == null || colecaoHidrometroProtecao.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Tabela hidr�metro prote��o ");
		}

		httpServletRequest.setAttribute("colecaoHidrometroProtecao", colecaoHidrometroProtecao);

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = Fachada.getInstancia().pesquisar(filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Motivo da N�o Cobran�a");
			}
		}
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(EfetuarRemanejamentoHidrometroActionForm efetuarRemanejamentoHidrometroActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			efetuarRemanejamentoHidrometroActionForm.setClienteUsuario(cliente.getNome());
			efetuarRemanejamentoHidrometroActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
		}
	}
}
