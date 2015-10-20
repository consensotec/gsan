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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroDiametroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoMaterialEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de efetuar liga��o de esgoto
 * sem RA
 * 
 * @author S�vio Luiz
 * @created 06 de Setembro de 2007
 */
public class ExibirEfetuarLigacaoEsgotoSemRAAction extends GcomAction {
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

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ActionForward retorno = actionMapping.findForward("efetuarLigacaoEsgotoSemRA");

		EfetuarLigacaoEsgotoSemRAActionForm ligacaoEsgotoSemRAActionForm = (EfetuarLigacaoEsgotoSemRAActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		this.consultaSelectObrigatorio(sessao);

		ligacaoEsgotoSemRAActionForm.setIndicadorCaixaGordura("2");

		String matriculaImovel = ligacaoEsgotoSemRAActionForm.getMatriculaImovel();

		if (matriculaImovel != null && !matriculaImovel.trim().equals("")) {

			String mensagemImovel = fachada.validarMatriculaImovel(new Integer(matriculaImovel));

			if (mensagemImovel == null) {
				
				Imovel imovel = fachada.pesquisarDadosImovel(Integer.parseInt(matriculaImovel));
				
				/*------------Inicio do codigo carregar * dados do Imov�l--------------------*/
				// Matricula Im�vel
				ligacaoEsgotoSemRAActionForm.setMatriculaImovel(matriculaImovel);

				// Inscri��o Im�vel
				String inscricaoImovel = fachada.pesquisarInscricaoImovel(new Integer(matriculaImovel));

				ligacaoEsgotoSemRAActionForm.setInscricaoImovel(inscricaoImovel);

				// Situa��o da Liga��o de Agua
				LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(new Integer(matriculaImovel));
				String situacaoLigacaoAgua = "";
				if (ligacaoAguaSituacao != null) {
					situacaoLigacaoAgua = ligacaoAguaSituacao.getDescricao();
				}

				ligacaoEsgotoSemRAActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situa��o da Liga��o de Esgoto
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(new Integer(matriculaImovel));
				String situacaoLigacaoEsgoto = "";
				if (ligacaoEsgotoSituacao != null) {
					situacaoLigacaoEsgoto = ligacaoEsgotoSituacao.getDescricao();
					if ((!ligacaoEsgotoSituacao.getId().equals(LigacaoEsgotoSituacao.POTENCIAL)) 
							&& (!ligacaoEsgotoSituacao.getId().equals(LigacaoEsgotoSituacao.FACTIVEL))
							&& (!ligacaoEsgotoSituacao.getId().equals(LigacaoEsgotoSituacao.EM_FISCALIZACAO))) {
						 //[FS0002] - Validar Situa��o de Liga��o de Esgoto do Im�vel
						throw new ActionServletException("atencao.situacao_validar_ligacao_esgoto_invalida_exibir",	null, matriculaImovel);

					}
				}
				ligacaoEsgotoSemRAActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				this.pesquisarCliente(ligacaoEsgotoSemRAActionForm,	new Integer(matriculaImovel));
				
				// [FS0017] Verificar N�vel para instala��o de esgoto
				if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER) && 
						imovel.getIndicadorNivelInstalacaoEsgoto() != null && imovel.getIndicadorNivelInstalacaoEsgoto().equals(ConstantesSistema.NAO)){
					throw new ActionServletException("atencao.imovel_sem_nivel_instalacao_esgoto", null, matriculaImovel);
				}

				// //[FS0013] - Altera��o de Valor
				// this.permitirAlteracaoValor(ordemServico.getServicoTipo(),
				// ligacaoEsgotoSemRAActionForm);
				// String calculaValores =
				// httpServletRequest.getParameter("calculaValores");
				//				
				// SistemaParametro sistemaParametro =
				// this.getFachada().pesquisarParametrosDoSistema();
				// Integer qtdeParcelas = null;
				// BigDecimal valorDebito = new BigDecimal(0);
				//				
				// if(calculaValores != null && calculaValores.equals("S")){
				//					
				// //[UC0186] - Calcular Presta��o
				// BigDecimal taxaJurosFinanciamento = null;
				// qtdeParcelas = new
				// Integer(ligacaoEsgotoSemRAActionForm.getQuantidadeParcelas());
				//					
				// if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() ==
				// ConstantesSistema.SIM.shortValue() &&
				// qtdeParcelas.intValue() != 1){
				//						
				// taxaJurosFinanciamento =
				// sistemaParametro.getPercentualTaxaJurosFinanciamento();
				// }else{
				// taxaJurosFinanciamento = new BigDecimal(0);
				// }
				//					
				// BigDecimal valorPrestacao = null;
				// if(taxaJurosFinanciamento != null){
				//						
				// valorDebito = new
				// BigDecimal(ligacaoEsgotoSemRAActionForm.getValorDebito().replace(",","."));
				//						
				// String percentualCobranca =
				// ligacaoEsgotoSemRAActionForm.getPercentualCobranca();
				//						
				// if(percentualCobranca.equals("70")){
				// valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				// }else if (percentualCobranca.equals("50")){
				// valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				// }
				//						
				// valorPrestacao =
				// this.getFachada().calcularPrestacao(
				// taxaJurosFinanciamento,
				// qtdeParcelas,
				// valorDebito,
				// new BigDecimal("0.00"));
				//						
				// valorPrestacao.setScale(2,BigDecimal.ROUND_HALF_UP);
				// }
				//					
				// if (valorPrestacao != null) {
				// String valorPrestacaoComVirgula =
				// Util.formataBigDecimal(valorPrestacao,2,true);
				// ligacaoEsgotoSemRAActionForm.setValorParcelas(valorPrestacaoComVirgula);
				// } else {
				// ligacaoEsgotoSemRAActionForm.setValorParcelas("0,00");
				// }
				//					
				// } else {
				//				
				//				
				// valorDebito = fachada.obterValorDebito(ordemServico
				// .getServicoTipo().getId(), imovel.getId(), new Short(
				// LigacaoTipo.LIGACAO_AGUA + ""));
				// ligacaoEsgotoSemRAActionForm.setValorDebito(Util.formataBigDecimal(
				// valorDebito, 2, true));
				// }

				/*-------------------- Dados da Liga��o ----------------------------*/

				this.consultaSelectObrigatorio(sessao);

				// Carregando campo Percentual de Esgoto
				// Item 4.6
				if (ligacaoEsgotoSemRAActionForm.getPerfilLigacao() != null	&& !ligacaoEsgotoSemRAActionForm.getPerfilLigacao().equals("")) {

					FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();

					filtroLigacaoPercentualEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoPerfil.ID, ligacaoEsgotoSemRAActionForm.getPerfilLigacao()));

					Collection colecaoPercentualEsgoto = fachada.pesquisar(filtroLigacaoPercentualEsgoto, LigacaoEsgotoPerfil.class.getName());

					if (colecaoPercentualEsgoto != null	&& !colecaoPercentualEsgoto.isEmpty()) {

						LigacaoEsgotoPerfil percentualEsgotoPerfil = (LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();

						String percentualFormatado = percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString().replace(".", ",");

						ligacaoEsgotoSemRAActionForm.setPercentualEsgoto(percentualFormatado);
					}
				}

				/*
				 * String dataEncerramentoOdServico = Util
				 * .formatarData(ordemServico.getDataEncerramento());
				 * 
				 * ligacaoEsgotoSemRAActionForm
				 * .setDataLigacao(dataEncerramentoOdServico);
				 */

				// -----------------------------------------------------------
				// Verificar permiss�o especial
				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------

				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				} else {
					ligacaoEsgotoSemRAActionForm.setPercentualCobranca("100");
					ligacaoEsgotoSemRAActionForm.setQuantidadeParcelas("1");
					// ligacaoEsgotoSemRAActionForm.setValorParcelas(Util
					// .formataBigDecimal(valorDebito, 2, true));
				}
				
				
				boolean alterarPercentualColetaEsgoto = this.getFachada().verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_PERCENTUAL_COLETA_ESGOTO,	usuarioLogado);

				if (alterarPercentualColetaEsgoto) {
					httpServletRequest.setAttribute("alterarPercentualColetaEsgoto",alterarPercentualColetaEsgoto);
				} 
				
				if(!alterarPercentualColetaEsgoto || ligacaoEsgotoSemRAActionForm.getPercentualColeta() == null || ligacaoEsgotoSemRAActionForm.getPercentualColeta().equals("") ){
					ligacaoEsgotoSemRAActionForm.setPercentualColeta("100,00");
				}
				

			} else {
				ligacaoEsgotoSemRAActionForm.setInscricaoImovel(mensagemImovel);
				ligacaoEsgotoSemRAActionForm.setMatriculaImovel("");
				httpServletRequest.setAttribute("matriculaImovelInexistente",true);
			}

		} else {

			httpServletRequest.setAttribute("nomeCampo", "matriculaImovel");

			ligacaoEsgotoSemRAActionForm.setMatriculaImovel("");
			ligacaoEsgotoSemRAActionForm.setInscricaoImovel("");
			ligacaoEsgotoSemRAActionForm.setClienteUsuario("");
			ligacaoEsgotoSemRAActionForm.setCpfCnpjCliente("");
			ligacaoEsgotoSemRAActionForm.setSituacaoLigacaoAgua("");
			ligacaoEsgotoSemRAActionForm.setSituacaoLigacaoEsgoto("");
			// ligacaoEsgotoSemRAActionForm.setNomeOrdemServico("");
			ligacaoEsgotoSemRAActionForm.setIdTipoDebito("");
			ligacaoEsgotoSemRAActionForm.setDescricaoTipoDebito("");
			ligacaoEsgotoSemRAActionForm.setQuantidadeParcelas("");
			ligacaoEsgotoSemRAActionForm.setValorParcelas("");
			ligacaoEsgotoSemRAActionForm.setPercentualCobranca("-1");
			ligacaoEsgotoSemRAActionForm.setMotivoNaoCobranca("-1");
		}

		return retorno;
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(
			EfetuarLigacaoEsgotoSemRAActionForm ligacaoEsgotoSemRAActionForm,
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
			ligacaoEsgotoSemRAActionForm.setClienteUsuario(cliente.getNome());
			ligacaoEsgotoSemRAActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Monta os select�s obrigatorios
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void consultaSelectObrigatorio(HttpSession sessao) {

		Fachada fachada = Fachada.getInstancia();

		// Filtro para o campo Diametro Liga��o �gua
		Collection colecaoDiametroLigacao = (Collection) sessao
				.getAttribute("colecaoDiametroLigacaoAgua");

		if (colecaoDiametroLigacao == null) {

			FiltroDiametroLigacaoEsgoto filtroDiametroLigacaoEsgoto = new FiltroDiametroLigacaoEsgoto();

			filtroDiametroLigacaoEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroDiametroLigacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDiametroLigacaoEsgoto
					.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoDiametroLigacao = fachada.pesquisar(
					filtroDiametroLigacaoEsgoto, LigacaoEsgotoDiametro.class
							.getName());

			if (colecaoDiametroLigacao != null
					&& !colecaoDiametroLigacao.isEmpty()) {
				sessao.setAttribute("colecaoDiametroLigacao",
						colecaoDiametroLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Diametro da Liga��o");
			}
		}

		// Filtro para o campo Material da Liga��o
		Collection colecaoMaterialLigacao = (Collection) sessao
				.getAttribute("colecaoMaterialLigacao");

		if (colecaoMaterialLigacao == null) {

			FiltroLigacaoMaterialEsgoto filtroLigacaoMaterialEsgoto = new FiltroLigacaoMaterialEsgoto();
			filtroLigacaoMaterialEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoMaterialEsgoto.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoMaterialEsgoto
					.setCampoOrderBy(FiltroLigacaoMaterialEsgoto.DESCRICAO);

			colecaoMaterialLigacao = fachada.pesquisar(
					filtroLigacaoMaterialEsgoto, LigacaoEsgotoMaterial.class
							.getName());

			if (colecaoMaterialLigacao != null
					&& !colecaoMaterialLigacao.isEmpty()) {
				sessao.setAttribute("colecaoMaterialLigacao",
						colecaoMaterialLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Material da Liga��o");
			}
		}

		// Filtro para o campo Perfil da Liga��o
		Collection colecaoPerfilLigacao = (Collection) sessao
				.getAttribute("colecaoPerfilLigacao");

		if (colecaoPerfilLigacao == null) {

			FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
			filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoPerfil
					.setCampoOrderBy(FiltroLigacaoEsgotoPerfil.DESCRICAO);

			colecaoPerfilLigacao = fachada.pesquisar(filtroLigacaoEsgotoPerfil,
					LigacaoEsgotoPerfil.class.getName());

			if (colecaoPerfilLigacao != null && !colecaoPerfilLigacao.isEmpty()) {
				sessao.setAttribute("colecaoPerfilLigacao",
						colecaoPerfilLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Perfil de Liga��o");
			}
		}

		// Filtro para o campo Motivo nao cobranca
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = fachada.pesquisar(
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