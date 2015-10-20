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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
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
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarLigacaoAguaComInstalacaoHidrometroAction extends GcomAction {

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

		EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm = (EfetuarLigacaoAguaComInstalacaoHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdOrdemServico();

		LigacaoAgua ligacaoAgua = this
				.setDadosLigacaoAgua(efetuarLigacaoAguaComInstalacaoHidrometroActionForm, fachada);

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();

		hidrometroInstalacaoHistorico = this
				.setDadosHidrometroInstalacaoHistorico(
						hidrometroInstalacaoHistorico,
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
						usuario);

		Imovel imovel = null;

		String idImovel = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdImovel();

		if (idImovel != null && !idImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricaoImovelEncontrado != null
					&& !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				imovel = (Imovel) fachada.pesquisarDadosImovel(new Integer(
						idImovel));

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, imovel.getId()));

				Collection colecaoImoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				Imovel imovelComLocalidade = (Imovel) Util
						.retonarObjetoDeColecao(colecaoImoveis);

				if (imovelComLocalidade != null
						&& imovelComLocalidade.getLocalidade()
								.getHidrometroLocalArmazenagem() != null
						&& hidrometroInstalacaoHistorico.getHidrometro()
								.getHidrometroLocalArmazenagem() != null
						&& !hidrometroInstalacaoHistorico
								.getHidrometro()
								.getHidrometroLocalArmazenagem()
								.getId()
								.equals(
										imovelComLocalidade
												.getLocalidade()
												.getHidrometroLocalArmazenagem()
												.getId())) {
					throw new ActionServletException(
							"atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}

				imovel.setUltimaAlteracao(new Date());

				ligacaoAgua.setImovel(imovel);

				hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);

				IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

				integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
				integracaoComercialHelper.setImovel(imovel);
				integracaoComercialHelper.setOrdemServico(null);
				integracaoComercialHelper.setQtdParcelas(null);
				integracaoComercialHelper
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

				fachada.efetuarLigacaoAguaComInstalacaoHidrometro(
						integracaoComercialHelper, usuario);

				if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
					// Monta a p�gina de sucesso
					montarPaginaSucesso(
							httpServletRequest,
							"Liga��o de �gua com Instala��o de Hidr�metro efetuada com Sucesso",
							"Efetuar outra Liga��o de �gua com Instala��o de Hidr�metro",
							"exibirEfetuarLigacaoAguaComInstalacaoHidrometroAction.do?menu=sim");
				}

				return retorno;

			} else {

				httpServletRequest.setAttribute("corImovel", "exception");

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

		}

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
				imovel.setUltimaAlteracao(new Date());
				ligacaoAgua.setImovel(imovel);

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, imovel.getId()));

				Collection colecaoImoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				Imovel imovelComLocalidade = (Imovel) Util
						.retonarObjetoDeColecao(colecaoImoveis);

				if (imovelComLocalidade != null
						&& imovelComLocalidade.getLocalidade()
								.getHidrometroLocalArmazenagem() != null
						&& hidrometroInstalacaoHistorico.getHidrometro()
								.getHidrometroLocalArmazenagem() != null
						&& !hidrometroInstalacaoHistorico
								.getHidrometro()
								.getHidrometroLocalArmazenagem()
								.getId()
								.equals(
										imovelComLocalidade
												.getLocalidade()
												.getHidrometroLocalArmazenagem()
												.getId())) {
					throw new ActionServletException(
							"atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}

				// hidrometroInstalacaoHistorico.setImovel(imovel);

				ligacaoAgua.setId(imovel.getId());
				hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
			}

			hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);

			ordemServico = 
				this.setDadosOrdemServico(ordemServico,
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm);

			String qtdParcelas = 
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getQuantidadeParcelas();

			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
			this.pesquisarImovelPerfil(imovel);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.getVeioEncerrarOS().equalsIgnoreCase("FALSE")) {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

				fachada.efetuarLigacaoAguaComInstalacaoHidrometro(
						integracaoComercialHelper, usuario);
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
				montarPaginaSucesso(
						httpServletRequest,
						"Liga��o de �gua com Instala��o de Hidr�metro efetuada com Sucesso",
						"Efetuar outra Liga��o de �gua com Instala��o de Hidr�metro",
						"exibirEfetuarLigacaoAguaComInstalacaoHidrometroAction.do?menu=sim");
			}

			return retorno;
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					"Ordem de Servi�o");
		}
	}
	
	private void pesquisarImovelPerfil(Imovel imovel){
	
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);
		Collection colecaoImovel = getFachada().pesquisar(filtroImovel, Imovel.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoImovel)){
			Imovel imovelAtualizar = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			imovel.setImovelPerfil(imovelAtualizar.getImovelPerfil());
		}
	}
	
	
	
	
	

	// [SB0001] - Gerar Liga��o de �gua
	//
	// M�todo respons�vel por setar os dados da liga��o de �gua
	// de acordo com os dados selecionados pelo usu�rio e pelas exig�ncias do
	// caso de uso
	public LigacaoAgua setDadosLigacaoAgua(
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
			Fachada fachada) {

		String diametroLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDiametroLigacao();
		String materialLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getMaterialLigacao();
		String idPerfilLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getPerfilLigacao();
		
		//[FS0016] - verificar tarifa de consumo associada. 
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples (
				FiltroConsumoTarifa.LIGACAO_AGUA_PERFIL,
				idPerfilLigacao));
		
		Collection pesquisa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		if (!pesquisa.isEmpty()){
			Boolean existeTarifaIgual = false;
			Iterator iteratorColecaoConsumoTarifa = pesquisa.iterator();
			Imovel imovelConsulta=null;
			
			if(efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getMatriculaImovel() != null 
					&& efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getMatriculaImovel() != ""){
				imovelConsulta= fachada.pesquisarImovel(new Integer(efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getMatriculaImovel()));
			}else{
				//esse caso e apenas para usuario com permissao especial para efetuarLigacaoAguaCominstalacaoHidrometroSemRA
				if(efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getIdImovel() != null 
						&& efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getIdImovel() != ""){
					imovelConsulta= fachada.pesquisarImovel(new Integer(efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getIdImovel()));
				}
			}
			
			while(iteratorColecaoConsumoTarifa.hasNext()){
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) iteratorColecaoConsumoTarifa.next();
				if (consumoTarifa.getLigacaoAguaPerfil() != null){
					if(imovelConsulta != null){
						if (imovelConsulta.getConsumoTarifa().getId().intValue() == consumoTarifa.getId().intValue()){
							existeTarifaIgual = true;
						}
					}
				}
			}
			
			if (!existeTarifaIgual){
				throw new ActionServletException("atencao.tarifa_consumo_perfil_ligacao",null, "Perfil da Liga��o");
			}
		}
		
		String ramalLocalInstalacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getRamalLocalInstalacao();
		String idLigacaoOrigem = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdLigacaoOrigem();
		/*
		 * String posicaoLigacao =
		 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
		 * .getPosicaoLigacao(); String abastecimentoAlternativo =
		 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
		 * .getAbastecimentoAlternativo(); String situacaoAbastecimento =
		 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
		 * .getSituacaoAbastecimento();
		 */
		String numeroLacre = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroLacre();

		LigacaoAgua ligacaoAgua = new LigacaoAgua();

		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataLigacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataLigacao().equals("")) {
			Date data = Util
					.converteStringParaDate(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getDataLigacao());
			ligacaoAgua.setDataLigacao(data);
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					" Data da Liga��o");
		}

		if (diametroLigacao != null
				&& !diametroLigacao.equals("")
				&& !diametroLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();
			ligacaoAguaDiametro.setId(new Integer(diametroLigacao));
			ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Diametro da Liga��o");
		}

		if (materialLigacao != null
				&& !materialLigacao.equals("")
				&& !materialLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial();
			ligacaoAguaMaterialMaterial.setId(new Integer(materialLigacao));
			ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Material da Liga��o");
		}

		if (idPerfilLigacao != null
				&& !idPerfilLigacao.equals("")
				&& !idPerfilLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
			ligacaoAguaPerfil.setId(new Integer(idPerfilLigacao));
			ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Perfil da Liga��o");
		}

		if (ramalLocalInstalacao != null
				&& !ramalLocalInstalacao.equals("")
				&& !ramalLocalInstalacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao();
			ramalLocal.setId(new Integer(ramalLocalInstalacao));

			ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
		}

		if (ramalLocalInstalacao != null
				&& !ramalLocalInstalacao.equals("")
				&& !ramalLocalInstalacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao();
			ramalLocal.setId(new Integer(ramalLocalInstalacao));

			ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
		}

		if (numeroLacre != null && !numeroLacre.equals("")) {
			ligacaoAgua.setNumeroLacre(numeroLacre);
		}

		if (idLigacaoOrigem != null
				&& !idLigacaoOrigem.equals("")
				&& !idLigacaoOrigem.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
			ligacaoOrigem.setId(new Integer(idLigacaoOrigem));
			ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
		}

		return ligacaoAgua;
	}

	// [SB0003] - Atualizar Ordem de Servi�o
	//
	// M�todo respons�vel por setar os dados da ordem de servi�o
	// de acordo com as exig�ncias do caso de uso
	public OrdemServico setDadosOrdemServico(
			OrdemServico ordemServico,
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm) {

		String idServicoMotivoNaoCobranca = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getPercentualCobranca();

		if (ordemServico != null
				&& efetuarLigacaoAguaComInstalacaoHidrometroActionForm
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
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.getPercentualCobranca()));
			}

			ordemServico.setUltimaAlteracao(new Date());

		}

		BigDecimal valorAtual = new BigDecimal(0);
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getValorDebito() != null) {
			String valorDebito = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
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
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
			Usuario usuarioLogado) {

		Fachada fachada = Fachada.getInstancia();

		String numeroHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();

		String numeroLacreHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroLacreHidrometro();

		if (numeroHidrometro != null) {
			// Pesquisa o Hidr�metro
			Hidrometro hidrometro = fachada
					.pesquisarHidrometroPeloNumero(numeroHidrometro);

			// FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			// filtroHidrometro.adicionarParametro(new ParametroSimples(
			// FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
			// // Realiza a pesquisa do Hidr�metro
			// Collection colecaoHidrometro =
			// fachada.pesquisar(filtroHidrometro,
			// Hidrometro.class.getName());
			//
			// // verificar se o n�mero do hidr�metro n�o est� cadastrado
			// if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
			// throw new ActionServletException(
			// "atencao.hidrometro_inexistente");
			// }
			// Iterator iteratorHidrometro = colecaoHidrometro.iterator();
			// Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();

			if (hidrometro == null) {
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			}

			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}

		// Data instala��o
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataInstalacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataInstalacao().equals("")) {

			hidrometroInstalacaoHistorico
					.setDataInstalacao(Util
							.converteStringParaDate(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.getDataInstalacao()));

		}

		// Medi��o tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);

		// hidr�metro local instala��o
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer
				.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getLocalInstalacao()));
		hidrometroInstalacaoHistorico
				.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

		// prote��o
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer
				.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);

		// leitura instala��o
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getLeituraInstalacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getLeituraInstalacao().trim().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroLeituraInstalacao(Integer
							.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.getLeituraInstalacao()));
		} else {
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);
		}

		// cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short
				.parseShort(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
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
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getNumeroSelo() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getNumeroSelo().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroSelo(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getNumeroSelo());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}

		// N�mero do Lacre
		if (numeroLacreHidrometro != null
				&& !numeroLacreHidrometro.trim().equals("")) {
			hidrometroInstalacaoHistorico.setNumeroLacre(numeroLacreHidrometro);
		} else {
			hidrometroInstalacaoHistorico.setNumeroLacre(null);
		}
		// tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		// indicador instala��o substitui��o
		hidrometroInstalacaoHistorico
				.setIndicadorInstalcaoSubstituicao(new Short("1"));

		// indicador troca de protecao
		hidrometroInstalacaoHistorico
			.setIndicadorTrocaProtecao(ConstantesSistema.NAO);
		
		hidrometroInstalacaoHistorico
			.setIndicadorTrocaRegistro(ConstantesSistema.NAO);
		
		// data �ltima altera��o
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());

		// Usu�rio Instala��o
		hidrometroInstalacaoHistorico.setUsuarioInstalacao(usuarioLogado);

		return hidrometroInstalacaoHistorico;

	}
}
