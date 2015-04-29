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

import gsan.atendimentopublico.bean.IntegracaoComercialHelper;
import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
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
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm = (EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdOrdemServico();

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
		hidrometroInstalacaoHistorico = this
				.setDadosHidrometroInstalacaoHistorico(
						hidrometroInstalacaoHistorico,
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm);
		
		Imovel imovel = null;

		if (ordemServicoId != null && !ordemServicoId.equals("")) {

			OrdemServico ordemServico = (OrdemServico) sessao
					.getAttribute("ordemServico");

			if (ordemServico == null) {
				throw new ActionServletException(
						"atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVI�O INEXISTENTE");
			}

			if (sessao.getAttribute("imovel") != null) {
				imovel = (Imovel) sessao.getAttribute("imovel");
				
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
				
				Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
				
				Imovel imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
				
				if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null &&
					hidrometroInstalacaoHistorico.getHidrometro().getHidrometroLocalArmazenagem() != null && 
					!hidrometroInstalacaoHistorico.getHidrometro().getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
						throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}
				
				imovel.setUltimaAlteracao(new Date());
			}
			
			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua ();
			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
			
			Collection colecaoLigacoesAgua = fachada.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
			
			LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacoesAgua);
			
			ligacaoAgua
					.setDataRestabelecimentoAgua(Util
							.converteStringParaDate(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
									.getDataRestabelecimento()));

			hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
			
			
			ordemServico = this.setDadosOrdemServico(ordemServico, efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm);
			
			String qtdParcelas = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
					.getQuantidadeParcelas();

			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
					.getVeioEncerrarOS().equalsIgnoreCase("FALSE")) {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

				fachada.efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometro(integracaoComercialHelper, usuario);
			} else {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
				sessao.setAttribute("integracaoComercialHelper",
						integracaoComercialHelper);

				if (sessao.getAttribute("semMenu") == null) {
					retorno = actionMapping
							.findForward("encerrarOrdemServicoAction");
				} else {
					retorno = actionMapping
							.findForward("encerrarOrdemServicoPopupAction");
				}
				sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
			}
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				// Monta a p�gina de sucesso
				montarPaginaSucesso(httpServletRequest,
						"Restabelecimento da Liga��o de �gua com Instala��o de Hidr�metro efetuada com Sucesso",
						"Efetuar outro Restabelecimento da Liga��o de �gua com Instala��o de Hidr�metro",
						"exibirEfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroAction.do?menu=sim");
			}

			return retorno;
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					"Ordem de Servi�o");
		}
	}

	// [SB0003] - Atualizar Ordem de Servi�o
	//
	// M�todo respons�vel por setar os dados da ordem de servi�o
	// de acordo com as exig�ncias do caso de uso
	public OrdemServico setDadosOrdemServico(
			OrdemServico ordemServico,
			EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm) {

		String idServicoMotivoNaoCobranca = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getPercentualCobranca();

		if (ordemServico != null
				&& efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			if (idServicoMotivoNaoCobranca != null
					&& !idServicoMotivoNaoCobranca
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}

			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
								.getPercentualCobranca()));
			}
			
			ordemServico
			.setDataEncerramento(Util
					.converteStringParaDate(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
							.getDataRestabelecimento()));
			ordemServico.setUltimaAlteracao(new Date());

		}

		BigDecimal valorAtual = new BigDecimal(0);
		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getValorDebito() != null) {
			String valorDebito = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
					.getValorDebito().toString().replace(".", "");

			valorDebito = valorDebito.replace(",", ".");

			valorAtual = new BigDecimal(valorDebito);

			ordemServico.setValorAtual(valorAtual);
		}

		return ordemServico;
	}

	// [SB0004] - Gerar Hist�rico de Instala��o do Hidr�metro
	//
	// M�todo respons�vel por setar os dados do hidr�metro instala��o hist�rico
	// de acordo com os dados selecionados pelo usu�rio e pelas exig�ncias do
	// caso de uso
	public HidrometroInstalacaoHistorico setDadosHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm) {

		Fachada fachada = Fachada.getInstancia();

		String numeroHidrometro = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();

		if (numeroHidrometro != null) {
			// Pesquisa o Hidr�metro
			Hidrometro hidrometro = fachada.pesquisarHidrometroPeloNumero(numeroHidrometro);
			
			if (hidrometro == null) {
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			}
			
			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}

		// Data instala��o
		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataInstalacao() != null
				&& !efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataInstalacao().equals("")) {

			hidrometroInstalacaoHistorico
					.setDataInstalacao(Util
							.converteStringParaDate(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
									.getDataInstalacao()));

		}
		
		// Medi��o tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);

		// hidr�metro local instala��o
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer
				.parseInt(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getLocalInstalacao()));
		hidrometroInstalacaoHistorico
				.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

		// prote��o
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer
				.parseInt(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);

		// leitura instala��o
		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getLeituraInstalacao() != null
				&& !efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getLeituraInstalacao().trim().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroLeituraInstalacao(Integer
							.parseInt(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
									.getLeituraInstalacao()));
		} else {
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);
		}

		// cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short
				.parseShort(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getSituacaoCavalete()));

		/*
		 * Campos opcionais
		 */

		// data da retirada
		hidrometroInstalacaoHistorico.setDataRetirada(null);
		// leitura retirada
		hidrometroInstalacaoHistorico.setNumeroLeituraRetirada(null);
		// leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
		// leitura supress�o
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
		// numero selo
		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getNumeroSelo() != null
				&& !efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getNumeroSelo().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroSelo(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
							.getNumeroSelo());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}
		// tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		// indicador instala��o substitui��o
		hidrometroInstalacaoHistorico
				.setIndicadorInstalcaoSubstituicao(new Short("1"));

		// data �ltima altera��o
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());

		return hidrometroInstalacaoHistorico;

	}
}
