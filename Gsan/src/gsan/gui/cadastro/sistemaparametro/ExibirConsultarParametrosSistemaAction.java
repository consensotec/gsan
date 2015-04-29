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
package gsan.gui.cadastro.sistemaparametro;

import gsan.arrecadacao.banco.ContaBancaria;
import gsan.arrecadacao.banco.FiltroContaBancaria;
import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.endereco.FiltroLogradouroBairro;
import gsan.cadastro.endereco.FiltroLogradouroCep;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.endereco.LogradouroCep;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.sistemaparametro.FiltroSistemaParametro;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vinicius Medeiros
 * @created 30/07/2008
 */
public class ExibirConsultarParametrosSistemaAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarParametrosSistema");		
		

		ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm = (ConsultarParametrosSistemaActionForm) actionForm;

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("clientePresidenteCompesa");
		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalIdPresidencia");
		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("clienteDiretorComercialCompesa");
		filtroSistemaParametro
				.adicionarCaminhoParaCarregamentoEntidade("clienteFicticioParaAssociarOsPagamentosNaoIdentificados");
		

		Collection colecaoSistemaParametro = this.getFachada().pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro
				.iterator().next();

		// 1� Tabela
		this.montarSistemaParametro1Tabela(consultarSistemaParametroActionForm,
				sistemaParametro);

		// 2� Tabela
		this.montarSistemaParametro2Tabela(consultarSistemaParametroActionForm,
				sistemaParametro);

		// 3� Tabela
		this.montarSistemaParametro3Tabela(consultarSistemaParametroActionForm,
				sistemaParametro);

		// 4� Tabela
		this.montarSistemaParametro4Tabela(consultarSistemaParametroActionForm,
				sistemaParametro);

		// 5� Tabela
		this.montarSistemaParametro5Tabela(consultarSistemaParametroActionForm,
				sistemaParametro);
		
		this.setarDownloadsLoja(consultarSistemaParametroActionForm, httpServletRequest );
		
		if(httpServletRequest.getParameter("modo") != null && !httpServletRequest.getParameter("modo").equals("")) {
			//Retorna o arquivo do decreto
			if(httpServletRequest.getParameter("modo").equals("verDecreto")){
				this.retornaArquivo("decreto", httpServletResponse, sistemaParametro);
			}
			//Retorna o arquivo de lei de tarifa
			if(httpServletRequest.getParameter("modo").equals("verLeiTarifa")){
				this.retornaArquivo("leiTarifa", httpServletResponse, sistemaParametro);
			}
			//retorna o arquivo de lei de Normas de Medi��o
			if(httpServletRequest.getParameter("modo").equals("verLeiNormaMedicao")){
				this.retornaArquivo("leiNormaMedicao", httpServletResponse, sistemaParametro);
			}
			//Retorna o arquivo de Norma CO
			if(httpServletRequest.getParameter("modo").equals("verNormaCO")){
				this.retornaArquivo("normaCO", httpServletResponse, sistemaParametro);
			}
			//Retorna o arquivo de Norma CM
			if(httpServletRequest.getParameter("modo").equals("verNormaCM")){
				this.retornaArquivo("normaCM", httpServletResponse, sistemaParametro);
			}

		}

		this.pesquisarEndereco(sistemaParametro, httpServletRequest);
		this.montarEndereco(consultarSistemaParametroActionForm,
				httpServletRequest);

		// Verifica se os dados foram informados da tabela existem e joga
		// numa
		// colecao

		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		filtroContaBancaria.setCampoOrderBy(FiltroContaBancaria.ID);

		Collection<ContaBancaria> colecaoContaBancaria = this.getFachada()
				.pesquisar(filtroContaBancaria, ContaBancaria.class.getName());

		if (colecaoContaBancaria == null || colecaoContaBancaria.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Conta Bancaria");
		}

		httpServletRequest.setAttribute("colecaoContaBancaria",
				colecaoContaBancaria);

		return retorno;
	}

	private void montarSistemaParametro1Tabela(
			ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm,
			SistemaParametro sistemaParametro) {

		Fachada fachada = Fachada.getInstancia();

		consultarSistemaParametroActionForm.setNomeEstado(sistemaParametro
				.getNomeEstado());
		consultarSistemaParametroActionForm.setNomeEmpresa(sistemaParametro
				.getNomeEmpresa());
		consultarSistemaParametroActionForm
				.setAbreviaturaEmpresa(sistemaParametro
						.getNomeAbreviadoEmpresa());
		consultarSistemaParametroActionForm.setCnpj(sistemaParametro
				.getCnpjEmpresa());

		if (sistemaParametro.getNumeroImovel() != null) {
			consultarSistemaParametroActionForm.setNumero(sistemaParametro
					.getNumeroImovel());
		}

		if (sistemaParametro.getComplementoEndereco() != null) {
			consultarSistemaParametroActionForm.setComplemento(sistemaParametro
					.getComplementoEndereco());
		}

		if (sistemaParametro.getNumeroTelefone() != null) {
			consultarSistemaParametroActionForm
					.setNumeroTelefone(sistemaParametro.getNumeroTelefone());
		}

		if (sistemaParametro.getNumeroRamal() != null) {
			consultarSistemaParametroActionForm.setRamal(sistemaParametro
					.getNumeroRamal());
		}

		if (sistemaParametro.getNumeroFax() != null) {
			consultarSistemaParametroActionForm.setFax(sistemaParametro
					.getNumeroFax());
		}

		if (sistemaParametro.getDescricaoEmail() != null) {
			consultarSistemaParametroActionForm.setEmail(sistemaParametro
					.getDescricaoEmail());
		}

		if (sistemaParametro.getTituloPagina() != null) {
			consultarSistemaParametroActionForm
					.setTitulosRelatorio(sistemaParametro.getTituloPagina());
		}

		if (sistemaParametro.getUnidadeOrganizacionalIdPresidencia() != null) {
			consultarSistemaParametroActionForm
					.setUnidadeOrganizacionalPresidencia(sistemaParametro
							.getUnidadeOrganizacionalIdPresidencia().getId()
							.toString());

			consultarSistemaParametroActionForm
					.setNomeUnidadeOrganizacionalPresidencia(sistemaParametro
							.getUnidadeOrganizacionalIdPresidencia()
							.getDescricao());
		}

		if (sistemaParametro.getClientePresidenteCompesa() != null) {
			consultarSistemaParametroActionForm.setPresidente(sistemaParametro
					.getClientePresidenteCompesa().getId().toString());

			consultarSistemaParametroActionForm
					.setNomePresidente(sistemaParametro
							.getClientePresidenteCompesa().getDescricao());
		}

		if (sistemaParametro.getClienteDiretorComercialCompesa() != null) {
			consultarSistemaParametroActionForm
					.setDiretorComercial(sistemaParametro
							.getClienteDiretorComercialCompesa().getId()
							.toString());

			consultarSistemaParametroActionForm
					.setNomeDiretorComercial(sistemaParametro
							.getClienteDiretorComercialCompesa().getDescricao());
		}

		if (sistemaParametro.getNumero0800Empresa() != null) {
			consultarSistemaParametroActionForm
					.setNumeroTelefoneAtendimento(sistemaParametro
							.getNumero0800Empresa());
		}

		if (sistemaParametro.getNomeSiteEmpresa() != null) {
			consultarSistemaParametroActionForm.setSite(sistemaParametro
					.getNomeSiteEmpresa());
		}

		if (sistemaParametro.getInscricaoEstadual() != null) {
			consultarSistemaParametroActionForm
					.setInscricaoEstadual(sistemaParametro
							.getInscricaoEstadual());
		}

		if (sistemaParametro.getInscricaoMunicipal() != null) {
			consultarSistemaParametroActionForm
					.setInscricaoMunicipal(sistemaParametro
							.getInscricaoMunicipal());
		}

		if (sistemaParametro.getNumeroContratoPrestacaoServico() != null) {
			consultarSistemaParametroActionForm
					.setNumeroContrato(sistemaParametro
							.getNumeroContratoPrestacaoServico().toString());
		}

		if (sistemaParametro.getImagemLogomarca() != null) {
			consultarSistemaParametroActionForm
					.setImagemLogomarca(sistemaParametro.getImagemLogomarca());
		}

		if (sistemaParametro.getImagemRelatorio() != null) {
			consultarSistemaParametroActionForm
					.setImagemRelatorio(sistemaParametro.getImagemRelatorio());
		}

		if (sistemaParametro.getImagemConta() != null) {
			consultarSistemaParametroActionForm.setImagemConta(sistemaParametro
					.getImagemConta());
		}
		
		
		
		if (sistemaParametro.getIndicadorCanceNegatContaVencAlter() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorCanceNegatContaVencAlter(sistemaParametro
							.getIndicadorCanceNegatContaVencAlter()
							.toString());
		}

		if (sistemaParametro.getNumeroExecucaoResumoNegativacao() != null) {
			consultarSistemaParametroActionForm
					.setNumeroExecucaoResumoNegativacao(sistemaParametro
							.getNumeroExecucaoResumoNegativacao().toString());
		}

		if (sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorExibirMensagem(sistemaParametro
							.getIndicadorExibeMensagemNaoReceberPagamento()
							.toString());
		}
		if (sistemaParametro.getClienteResponsavelProgramaEspecial() != null) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, sistemaParametro
							.getClienteResponsavelProgramaEspecial().getId()));

			Collection<Cliente> colecaoClientes = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());

			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClientes);

			if (cliente != null) {
				consultarSistemaParametroActionForm
						.setIdClienteResponsavelPrograma(cliente.getId()
								.toString());
				consultarSistemaParametroActionForm
						.setNomeClienteResponsavelPrograma(cliente.getNome());
			}
		}
		if (sistemaParametro.getPerfilProgramaEspecial() != null) {

			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			filtroImovelPerfil.adicionarParametro(new ParametroSimples(
					FiltroImovelPerfil.ID, sistemaParametro
							.getPerfilProgramaEspecial().getId()));

			Collection<ImovelPerfil> colecaoImoveisPerfis = this
					.getFachada()
					.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

			ImovelPerfil perfilPrograma = (ImovelPerfil) Util
					.retonarObjetoDeColecao(colecaoImoveisPerfis);

			consultarSistemaParametroActionForm
					.setPerfilProgramaEspecial(perfilPrograma.getDescricao());
		}

		// Percentual de Convergencia Repavimentacao
		if (sistemaParametro.getPercentualConvergenciaRepavimentacao() != null) {
			consultarSistemaParametroActionForm
					.setPercentualConvergenciaRepavimentacao(Util
							.formatarBigDecimalParaStringComVirgula(sistemaParametro
									.getPercentualConvergenciaRepavimentacao()));
		}

		if (sistemaParametro.getIndicadorPopupAtualizacaoCadastral() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorPopupAtualizacaoCadastral(sistemaParametro
							.getIndicadorPopupAtualizacaoCadastral().toString());
		}

		if (sistemaParametro.getValorGuiaFichaComp() != null) {
			consultarSistemaParametroActionForm
					.setValorGuiaFichaComp(Util
							.formatarMoedaReal(sistemaParametro
									.getValorGuiaFichaComp()));
		}

		if (sistemaParametro.getValorDemonstrativoParcelamentoFichaComp() != null) {
			consultarSistemaParametroActionForm
					.setValorDemonstrativoParcelamentoFichaComp(Util
							.formatarMoedaReal(sistemaParametro
									.getValorDemonstrativoParcelamentoFichaComp()));
		}
		
		if (sistemaParametro.getIndicadorAlterarNomeClienteCpfCnpjValidado() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorAlterarNomeClienteCpfCnpjValidado(sistemaParametro
									.getIndicadorAlterarNomeClienteCpfCnpjValidado().toString());
		}
		
		if (sistemaParametro.getIndicadorVariaHierarquiaUnidade() != null){
			consultarSistemaParametroActionForm
					.setIndicadorVariaHierarquiaUnidade(sistemaParametro
									.getIndicadorVariaHierarquiaUnidade().toString());
		}
		
		if (sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados() != null) {
			consultarSistemaParametroActionForm
					.setClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro
							.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados().getId()
							.toString());

			consultarSistemaParametroActionForm
					.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro
							.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados().getDescricao());
		}
		
		if(sistemaParametro.getIndicadorValidaCpfCnpj() != null){
			consultarSistemaParametroActionForm.setIndicadorValidaCpfCnpj(
					sistemaParametro.getIndicadorValidaCpfCnpj().toString());
		}
		
		if(sistemaParametro.getIndicadorNomeClienteAtualConta() != null){
			consultarSistemaParametroActionForm.setIndicadorNomeClienteAtualConta(
					sistemaParametro.getIndicadorNomeClienteAtualConta().toString());
		}
		
		if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorUsoNMCliReceitaFantasia(sistemaParametro
							.getIndicadorUsoNMCliReceitaFantasia().toString());
		}

		consultarSistemaParametroActionForm.setIndicadorControlaAutoInfracao(""
				+ sistemaParametro.getIndicadorControlaAutoInfracao());

		consultarSistemaParametroActionForm.setIndicadorUsaRota(""
				+ sistemaParametro.getIndicadorUsaRota());
		consultarSistemaParametroActionForm.setIndicadorDocumentoObrigatorio(""
				+ sistemaParametro.getIndicadorDocumentoObrigatorio());
		consultarSistemaParametroActionForm.setIndicadorConsultaSpc("" 
				+ sistemaParametro.getIndicadorConsultaDocumentoReceita());

		if (sistemaParametro.getValorExtratoFichaComp() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getValorExtratoFichaComp());

			consultarSistemaParametroActionForm
					.setValorExtratoFichaComp(valorAux);
		}

		if (sistemaParametro.getNumeroDiasBloqueioCelular() != null) {

			consultarSistemaParametroActionForm
					.setNumeroDiasBloqueioCelular(sistemaParametro
							.getNumeroDiasBloqueioCelular().toString());
		}
		
		if (sistemaParametro.getUltimoDiaVencimentoAlternativo() != null) {

			consultarSistemaParametroActionForm
					.setUltimoDiaVencimentoAlternativo(sistemaParametro
							.getUltimoDiaVencimentoAlternativo().toString());
		}
		
		if(sistemaParametro.getMsgVencimentoGuiaEntradaParc() != null)
			consultarSistemaParametroActionForm.setMsgVencimentoGuiaEntradaParc(sistemaParametro.getMsgVencimentoGuiaEntradaParc().toUpperCase());
		
		if(sistemaParametro.getTamanhoMaximoAnexoRA() != null){
			consultarSistemaParametroActionForm.setTamanhoMaximoAnexoRA(sistemaParametro.getTamanhoMaximoAnexoRA().toString());
		}
		
		if(sistemaParametro.getImagemResolucaoLargura() != null){
			consultarSistemaParametroActionForm.setImagemResolucaoLargura(sistemaParametro.getImagemResolucaoLargura().toString());
		}
		
		if(sistemaParametro.getImagemResolucaoAltura() != null){
			consultarSistemaParametroActionForm.setImagemResolucaoAltura(sistemaParametro.getImagemResolucaoAltura().toString());
		}
		
		/**
		 * #10981 - ROTINA PARA ENVIAR E-MAIL E SMS PARA DEVEDORES.
		 * @author Diogo Luiz
		 * @date 02/07/2014
		 * 
		 * RM 11444 - [UC0060] - Informar Parametros Sistema e [UC0061] - Consutar Par�metros Sistema
		 */
		if(sistemaParametro.getTamanhoMaximoMensagemSms() != null){
			consultarSistemaParametroActionForm.setTamanhoMaximoMensagemSms(sistemaParametro.getTamanhoMaximoMensagemSms().toString());
		}

	}

	private void montarSistemaParametro2Tabela(
			ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm,
			SistemaParametro sistemaParametro) {

		String anoMesFaturamento = Util
				.formatarAnoMesParaMesAno(sistemaParametro
						.getAnoMesFaturamento().toString());

		consultarSistemaParametroActionForm
				.setMesAnoReferencia(anoMesFaturamento);
		consultarSistemaParametroActionForm.setMenorConsumo(sistemaParametro
				.getMenorConsumoGrandeUsuario().toString());

		if (sistemaParametro.getValorMinimoEmissaoConta() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getValorMinimoEmissaoConta());

			consultarSistemaParametroActionForm.setMenorValor(valorAux);
		}

		consultarSistemaParametroActionForm.setQtdeEconomias(sistemaParametro
				.getMenorEconomiasGrandeUsuario().toString());

		if (sistemaParametro.getMesesMediaConsumo() != null) {
			consultarSistemaParametroActionForm
					.setMesesCalculoMedio(sistemaParametro
							.getMesesMediaConsumo().toString());
		}

		if (sistemaParametro.getNumeroMinimoDiasEmissaoVencimento() != null) {
			consultarSistemaParametroActionForm
					.setDiasMinimoVencimento(sistemaParametro
							.getNumeroMinimoDiasEmissaoVencimento().toString());
		}

		if (sistemaParametro.getNumeroDiasAdicionaisCorreios() != null) {
			consultarSistemaParametroActionForm
					.setDiasMinimoVencimentoCorreio(sistemaParametro
							.getNumeroDiasAdicionaisCorreios().toString());
		}

		if (sistemaParametro.getNumeroMesesValidadeConta() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMesesValidadeConta(sistemaParametro
							.getNumeroMesesValidadeConta().toString());
		}

		if (sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMesesAlteracaoVencimento(sistemaParametro
							.getNumeroMesesMinimoAlteracaoVencimento()
							.toString());
		}

		if (sistemaParametro.getNumeroMesesMaximoCalculoMedia() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMesesMaximoCalculoMedia(sistemaParametro
							.getNumeroMesesMaximoCalculoMedia().toString());
		}

		if (sistemaParametro.getNumeroMesesCalculoCorrecao() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMesesCalculoCorrecao(sistemaParametro
							.getNumeroMesesCalculoCorrecao().toString());
		}
		
		if(sistemaParametro.getNumeroVezesSuspendeLeitura() != null) {
			consultarSistemaParametroActionForm.setNumeroVezesSuspendeLeitura(sistemaParametro.getNumeroVezesSuspendeLeitura().toString());
		}
		
		if(sistemaParametro.getNumeroMesesLeituraSuspensa() != null) {
			consultarSistemaParametroActionForm.setNumeroMesesLeituraSuspensa(sistemaParametro.getNumeroMesesLeituraSuspensa().toString());
		}
		
		if(sistemaParametro.getNumeroMesesReinicioSitEspFaturamento() != null) {
			consultarSistemaParametroActionForm.setNumeroMesesReinicioSitEspFatu(sistemaParametro.getNumeroMesesReinicioSitEspFaturamento().toString());
		}
		
		if(sistemaParametro.getNumeroMaximoMesesInserirContaAntecipada() != null)
		{
			consultarSistemaParametroActionForm.setNumeroMaximoMesesInserirContaAntecipada(sistemaParametro.getNumeroMaximoMesesInserirContaAntecipada().toString());
		}
		
		if (sistemaParametro.getIndicadorRoteiroEmpresa() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorRoteiroEmpresa(sistemaParametro
							.getIndicadorRoteiroEmpresa().toString());
		}

		if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorLimiteAlteracaoVencimento(sistemaParametro
							.getIndicadorLimiteAlteracaoVencimento().toString());
		}

		if (sistemaParametro.getIndicadorCalculaVencimento() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorCalculoVencimento(sistemaParametro
							.getIndicadorCalculaVencimento().toString());
		}

		if (sistemaParametro.getIndicadorTarifaCategoria() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorTarifaCategoria(sistemaParametro
							.getIndicadorTarifaCategoria().toString());
		}

		consultarSistemaParametroActionForm.setIndicadorAtualizacaoTarifaria(""
				+ sistemaParametro.getIndicadorAtualizacaoTarifaria());

		if (sistemaParametro.getAnoMesAtualizacaoTarifaria() != null) {

			String anoMes = Util.formatarAnoMesParaMesAno(sistemaParametro
					.getAnoMesAtualizacaoTarifaria().toString());

			consultarSistemaParametroActionForm
					.setMesAnoAtualizacaoTarifaria(anoMes);
		}

		if (sistemaParametro.getIndicadorFaturamentoAntecipado() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorFaturamentoAntecipado(sistemaParametro
							.getIndicadorFaturamentoAntecipado().toString());
		}

		if (sistemaParametro.getValorSalarioMinimo() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getValorSalarioMinimo());

			consultarSistemaParametroActionForm.setSalarioMinimo(valorAux);
		}

		if (sistemaParametro.getAreaMaximaTarifaSocial() != null) {
			consultarSistemaParametroActionForm.setAreaMaxima(sistemaParametro
					.getAreaMaximaTarifaSocial().toString());
		}

		if (sistemaParametro.getConsumoEnergiaMaximoTarifaSocial() != null) {
			consultarSistemaParametroActionForm
					.setConsumoMaximo(sistemaParametro
							.getConsumoEnergiaMaximoTarifaSocial().toString());
		}

		if (sistemaParametro.getIndicadorTarifaCategoria() != null) {
			consultarSistemaParametroActionForm
					.setConsumoMaximo(sistemaParametro
							.getConsumoEnergiaMaximoTarifaSocial().toString());
		}
		if (sistemaParametro.getIndicadorRetificacaoValorMenor() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorRetificacaoValorMenor(""
							+ sistemaParametro
									.getIndicadorRetificacaoValorMenor());
		}

		if (sistemaParametro.getIndicadorTransferenciaComDebito() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorTransferenciaComDebito(""
							+ sistemaParametro
									.getIndicadorTransferenciaComDebito());
		}

		if (sistemaParametro.getIndicadorNaoMedidoTarifa() != null) {
			consultarSistemaParametroActionForm.setIndicadorNaoMedidoTarifa(""
					+ sistemaParametro.getIndicadorNaoMedidoTarifa());
		}

		if (sistemaParametro.getIndicadorQuadraFace() != null) {
			consultarSistemaParametroActionForm.setIndicadorQuadraFace(""
					+ sistemaParametro.getIndicadorQuadraFace());
		}

		if (sistemaParametro.getNumeroDiasVariacaoConsumo() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasVariacaoConsumo(sistemaParametro
							.getNumeroDiasVariacaoConsumo().toString());
		}

		if (sistemaParametro.getNumeroDiasPrazoRecursoAutoInfracao() != null) {
			consultarSistemaParametroActionForm
					.setNnDiasPrazoRecursoAutoInfracao(sistemaParametro
							.getNumeroDiasPrazoRecursoAutoInfracao().toString());
		}

		if (sistemaParametro.getDiasVencimentoAlternativo() != null) {
			consultarSistemaParametroActionForm
					.setDiasVencimentoAlternativo(sistemaParametro
							.getDiasVencimentoAlternativo());
		}
		if (sistemaParametro.getIndicadorBloqueioContaMobile() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorBloqueioContaMobile(sistemaParametro
							.getIndicadorBloqueioContaMobile().toString());
		}

		if (sistemaParametro.getValorContaFichaComp() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getValorContaFichaComp());

			consultarSistemaParametroActionForm
					.setValorContaFichaComp(valorAux);
		}

		if (sistemaParametro.getNumeroMesesRetificarConta() != null) {

			String valorAux = sistemaParametro
					.getNumeroMesesRetificarConta().toString();

			consultarSistemaParametroActionForm
					.setNumeroMesesRetificarConta(valorAux);
		}

		if (sistemaParametro.getIndicadorNormaRetificacao() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorNormaRetificacao(sistemaParametro
							.getIndicadorNormaRetificacao().toString());
		}
		
		if (sistemaParametro.getIndicadorUtilizaTarifaSimulacao() != null) {
			consultarSistemaParametroActionForm
			.setIndicadorUtilizaTarifaSimulacao(sistemaParametro
					.getIndicadorUtilizaTarifaSimulacao().toString());
		}
		
		if (sistemaParametro.getMensagemContaBraile() != null) {
			consultarSistemaParametroActionForm
					.setMensagemContaBraile(sistemaParametro.getMensagemContaBraile());
		}
		
		if(sistemaParametro.getNumeroMaximoDiasNovaLigacao() != null)
		{
			consultarSistemaParametroActionForm.setNumeroMaximoDiasNovaLigacao(sistemaParametro.getNumeroMaximoDiasNovaLigacao().toString());
		}
		
		if (sistemaParametro.getCodigoTipoCalculoNaoMedido() != null){
			if (sistemaParametro.getCodigoTipoCalculoNaoMedido().compareTo(new Integer (1)) == 0){
				consultarSistemaParametroActionForm.setCodigoTipoCalculoNaoMedido("AREA CONSTRUIDA");
			}else if(sistemaParametro.getCodigoTipoCalculoNaoMedido().compareTo(new Integer (2)) == 0){
					consultarSistemaParametroActionForm.setCodigoTipoCalculoNaoMedido("PONTOS DE UTILIZA��O");
				}else if(sistemaParametro.getCodigoTipoCalculoNaoMedido().compareTo(new Integer (3)) == 0){	
							consultarSistemaParametroActionForm.setCodigoTipoCalculoNaoMedido("NUMERO DE MORADORES");
					}
				}
			}
		
	private void montarSistemaParametro3Tabela(
			ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm,
			SistemaParametro sistemaParametro) {

		String anoMesArrecadacao = Util
				.formatarAnoMesParaMesAno(sistemaParametro
						.getAnoMesArrecadacao().toString());

		consultarSistemaParametroActionForm.setMesAnoReferenciaArrecadacao(""
				+ anoMesArrecadacao);

		if (sistemaParametro.getCodigoEmpresaFebraban() != null) {
			consultarSistemaParametroActionForm
					.setCodigoEmpresaFebraban(sistemaParametro
							.getCodigoEmpresaFebraban().toString());
		}

		if (sistemaParametro.getNumeroLayoutFebraban() != null) {
			consultarSistemaParametroActionForm
					.setNumeroLayOut(sistemaParametro.getNumeroLayoutFebraban()
							.toString());
		}

		if (sistemaParametro.getContaBancaria() != null) {
			consultarSistemaParametroActionForm
					.setIndentificadorContaDevolucao(sistemaParametro
							.getContaBancaria().getId().toString());
		}

		if (sistemaParametro.getPercentualFinanciamentoEntradaMinima() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualFinanciamentoEntradaMinima());

			consultarSistemaParametroActionForm
					.setPercentualEntradaMinima(valorAux);
		}

		if (sistemaParametro.getNumeroMaximoParcelasFinanciamento() != null) {
			consultarSistemaParametroActionForm
					.setMaximoParcelas(sistemaParametro
							.getNumeroMaximoParcelasFinanciamento().toString());
		}

		if (sistemaParametro.getPercentualMaximoAbatimento() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualMaximoAbatimento());

			consultarSistemaParametroActionForm
					.setPercentualMaximoAbatimento(valorAux);
		}

		if (sistemaParametro.getPercentualTaxaJurosFinanciamento() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualTaxaJurosFinanciamento());

			consultarSistemaParametroActionForm
					.setPercentualTaxaFinanciamento(valorAux);
		}

		if (sistemaParametro.getNumeroMaximoParcelaCredito() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMaximoParcelaCredito(sistemaParametro
							.getNumeroMaximoParcelaCredito().toString());
		}

		if (sistemaParametro.getPercentualMediaIndice() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualMediaIndice());

			consultarSistemaParametroActionForm
					.setPercentualCalculoIndice(valorAux);
		}
		if (sistemaParametro.getNumeroModuloDigitoVerificador() != null) {
			consultarSistemaParametroActionForm
					.setNumeroModuloDigitoVerificador(sistemaParametro
							.getNumeroModuloDigitoVerificador().toString());
		}
		if (sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMesesPesquisaImoveisRamaisSuprimidos(sistemaParametro
							.getNumeroMesesPesquisaImoveisRamaisSuprimidos()
							.toString());
		}
		if (sistemaParametro.getNumeroAnoQuitacao() != null) {
			consultarSistemaParametroActionForm
					.setNumeroAnoQuitacao(sistemaParametro
							.getNumeroAnoQuitacao().toString());
		}
		if (sistemaParametro.getIndicadorContaParcelada() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorContaParcelada(sistemaParametro
							.getIndicadorContaParcelada().toString());
		}
		if (sistemaParametro.getIndicadorCobrancaJudical() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorCobrancaJudical(sistemaParametro
							.getIndicadorCobrancaJudical().toString());
		}
		if (sistemaParametro.getIndicadorValorMovimentoArrecadador() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorValorMovimentoArrecadador(String
							.valueOf(sistemaParametro
									.getIndicadorValorMovimentoArrecadador()));
		}
		if (sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMesesAnterioresParaDeclaracaoQuitacao(sistemaParametro
							.getNumeroMesesAnterioresParaDeclaracaoQuitacao()
							.toString());
		}
		
		if(sistemaParametro.getNumeroConvenioFichaCompensacao() != null){
			consultarSistemaParametroActionForm.
				setNumeroConvenioFichaCompensacao(
					sistemaParametro.getNumeroConvenioFichaCompensacao().toString());
		}
		
		if(sistemaParametro.getValorMaximoBaixado() != null){
			
			String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorMaximoBaixado());
			
			consultarSistemaParametroActionForm.setValorMaximoBaixado(
					valorAux);
		}
		
		if(sistemaParametro.getDiferencaMaximoBaixado() != null){
			
			String valorAux = Util.formatarMoedaReal(sistemaParametro.getDiferencaMaximoBaixado());
			
			consultarSistemaParametroActionForm.setDiferencaMaximoBaixado(
					valorAux);
		}
		
		/**
		 * RM8594 - [UC0060] Informar Sistema Parametro / [UC0061] Consultar Sistema Parametro
		 * Author: Diogo Luiz
		 * Data: 07/11/2013 
		 */
		if(sistemaParametro.getIndicadorCarteira17() != null){
			
			String indicadorCarteira17 = sistemaParametro.getIndicadorCarteira17().toString();
			
			consultarSistemaParametroActionForm.setIndicadorCarteira17(indicadorCarteira17);
		}
		
	}

	private void montarSistemaParametro4Tabela(
			ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm,
			SistemaParametro sistemaParametro) {
		

		if (sistemaParametro.getHidrometroCapacidade() != null) {
			consultarSistemaParametroActionForm
					.setDescricaoMenorCapacidade(sistemaParametro
							.getHidrometroCapacidade().getDescricao());
		}

		if (sistemaParametro.getIndicadorFaixaFalsa() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorGeracaoFaixaFalsa(sistemaParametro
							.getIndicadorFaixaFalsa().toString());
		}

		if (sistemaParametro.getIndicadorUsoFaixaFalsa() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorPercentualGeracaoFaixaFalsa(sistemaParametro
							.getIndicadorUsoFaixaFalsa().toString());
		}

		if (sistemaParametro.getPercentualFaixaFalsa() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualFaixaFalsa());

			consultarSistemaParametroActionForm
					.setPercentualGeracaoFaixaFalsa(valorAux);
		}

		if (sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorPercentualGeracaoFiscalizacaoLeitura(sistemaParametro
							.getIndicadorPercentualFiscalizacaoLeitura()
							.toString());
		}

		if (sistemaParametro.getIndicadorUsoFiscalizadorLeitura() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorGeracaoFiscalizacaoLeitura(sistemaParametro
							.getIndicadorUsoFiscalizadorLeitura().toString());
		}

		if (sistemaParametro.getPercentualFiscalizacaoLeitura() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualFiscalizacaoLeitura());

			consultarSistemaParametroActionForm
					.setPercentualGeracaoFiscalizacaoLeitura(valorAux);
		}

		if (sistemaParametro.getIndicadorUsoFaixaFalsa() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorPercentualGeracaoFaixaFalsa(sistemaParametro
							.getIndicadorUsoFaixaFalsa().toString());
		}

		if (sistemaParametro.getPercentualFaixaFalsa() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro
					.getPercentualFaixaFalsa());

			consultarSistemaParametroActionForm
					.setPercentualGeracaoFaixaFalsa(valorAux);
		}

		if (sistemaParametro.getIncrementoMaximoConsumoRateio() != null) {

			consultarSistemaParametroActionForm
					.setIncrementoMaximoConsumo(sistemaParametro
							.getIncrementoMaximoConsumoRateio().toString());
		}

		if (sistemaParametro.getDecrementoMaximoConsumoRateio() != null) {
			consultarSistemaParametroActionForm
					.setDecrementoMaximoConsumo(sistemaParametro
							.getDecrementoMaximoConsumoRateio().toString());
		}

		if (sistemaParametro.getPercentualToleranciaRateio() != null) {

			String valorAux = Util.formataBigDecimal(
					sistemaParametro.getPercentualToleranciaRateio(), 1, false);

			consultarSistemaParametroActionForm
					.setPercentualToleranciaRateioConsumo(valorAux);
		}

		if (sistemaParametro.getNumeroDiasVencimentoCobranca() != null) {
			consultarSistemaParametroActionForm
					.setDiasVencimentoCobranca(sistemaParametro
							.getNumeroDiasVencimentoCobranca().toString());
		}

		if (sistemaParametro.getNumeroMaximoMesesSancoes() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMaximoMesesSancoes(sistemaParametro
							.getNumeroMaximoMesesSancoes().toString());
		}

		consultarSistemaParametroActionForm.setValorSegundaVia(Util
				.formatarMoedaReal(sistemaParametro.getValorSegundaVia()));

		consultarSistemaParametroActionForm.setIndicadorCobrarTaxaExtrato(""
				+ sistemaParametro.getIndicadorCobrarTaxaExtrato());

		if (sistemaParametro.getCodigoPeriodicidadeNegativacao() != null) {
			consultarSistemaParametroActionForm
					.setCodigoPeriodicidadeNegativacao(sistemaParametro
							.getCodigoPeriodicidadeNegativacao().toString());
		}

		consultarSistemaParametroActionForm.setNumeroDiasCalculoAcrescimos(""
				+ sistemaParametro.getNumeroDiasCalculoAcrescimos());

		consultarSistemaParametroActionForm
				.setNumeroDiasValidadeExtrato(sistemaParametro
						.getNumeroDiasValidadeExtrato().toString());

		if (sistemaParametro.getNumeroDiasValidadeExtratoPermissaoEspecial() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasValidadeExtratoPermissaoEspecial(sistemaParametro
							.getNumeroDiasValidadeExtratoPermissaoEspecial()
							.toString());
		}

		consultarSistemaParametroActionForm
				.setIndicadorParcelamentoConfirmado(""
						+ sistemaParametro.getIndicadorParcelamentoConfirmado());
		
		consultarSistemaParametroActionForm
				.setindicadorTabelaPrice(""
						+ sistemaParametro.getIndicadorTabelaPrice());

		consultarSistemaParametroActionForm
				.setNumeroDiasVencimentoEntradaParcelamento(""
						+ sistemaParametro
								.getNumeroDiasVencimentoEntradaParcelamento()
								.toString());
	
		if  (sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() != null){
			consultarSistemaParametroActionForm.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(sistemaParametro
					.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo().toString());
		}
		
		if (sistemaParametro.getResolucaoDiretoria() != null
				&& sistemaParametro.getResolucaoDiretoria().getId() != null) {
			consultarSistemaParametroActionForm
					.setIdResolucaoDiretoria(sistemaParametro
							.getResolucaoDiretoria().getId().toString());
		}

		if (sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorBloqueioContasContratoParcelDebitos(sistemaParametro
							.getIndicadorBloqueioContasContratoParcelDebitos()
							+ "");
		}

		if (sistemaParametro
				.getIndicadorBloqueioContasContratoParcelManterConta() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorBloqueioContasContratoParcelManterConta(sistemaParametro
							.getIndicadorBloqueioContasContratoParcelManterConta()
							+ "");
		}

		if (sistemaParametro
			.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(sistemaParametro
							.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito()
							+ "");
		}

		if (sistemaParametro
				.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(sistemaParametro
							.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta()
							+ "");
		}

		if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro
							.getNumeroMaximoParcelasContratosParcelamento().toString());
		}
		if (sistemaParametro.getNumeroDiasEnvioContaEmpresaCobranca() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasEnvioContaEmpresaCobranca(sistemaParametro
							.getNumeroDiasEnvioContaEmpresaCobranca().toString());
		}
		
		if (sistemaParametro.getNumeroDiaRetiradaContaEmpresaCobraca() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiaRetiradaContaEmpresaCobraca(sistemaParametro
							.getNumeroDiaRetiradaContaEmpresaCobraca().toString());
		}
		
		if (sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa() != null) {
			consultarSistemaParametroActionForm
					.setQuantidadeDiasValidadeCerticaoNegativa(sistemaParametro
							.getQuantidadeDiasValidadeCerticaoNegativa().toString());
		}
		
		
		if (sistemaParametro.getIndicadorIncluirContasForaVenCobranca() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorIncluirContasForaVenCobranca(sistemaParametro
							.getIndicadorIncluirContasForaVenCobranca().toString());
		}

		
		if(sistemaParametro.getIndicadorCriticarConteudoRetornoMovimentoNegativacao() != null){
			consultarSistemaParametroActionForm.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(
					sistemaParametro.getIndicadorCriticarConteudoRetornoMovimentoNegativacao().toString());
		}
		
		if(sistemaParametro.getIndicadorCalcAcresImpontGuiaPagamento() != null){
			consultarSistemaParametroActionForm.setIndicadorCalcAcresImpontGuiaPagamento(
					sistemaParametro.getIndicadorCalcAcresImpontGuiaPagamento().toString());
		}
		
		if(sistemaParametro.getIndicadorExcluirNegativacaoAposPgmto() != null){
			consultarSistemaParametroActionForm.setIndicadorExcluirNegativacaoAposPgmto(
					sistemaParametro.getIndicadorExcluirNegativacaoAposPgmto().toString());
		}
		
		if(sistemaParametro.getIndicadorTotalDebito() != null){
			consultarSistemaParametroActionForm.setIndicadorTotalDebito(
					sistemaParametro.getIndicadorTotalDebito().toString());
		}
		
		/*
		 * Adicionado por: Nathalia Santos  
		 * Data: 20/06/2012 RN2012063453 
		 * Inclus�o de campo para informar 
		 * n�mero de dias �teis para Vencimento das Contas da Entrada de Parcelamento
		 */
		if (sistemaParametro.getNumeroDiasVencContaEntradaParcelamento() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasVencContaEntradaParcelamento(sistemaParametro
							.getNumeroDiasVencContaEntradaParcelamento().toString());
		}
		
		/*
		 * Adicionado por: Nathalia Santos  
		 * Data: 02/07/2012 RN2012063453 
		 * Inclus�o de campo para informar 
		 * n�mero de dias �teis para Cancelamento da Entrada de Parcelamento
		 */
		if (sistemaParametro.getNumeroDiasCancelamentoEntradaParcelamento() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasCancelamentoEntradaParcelamento(sistemaParametro
							.getNumeroDiasCancelamentoEntradaParcelamento().toString());
		}
		
		/*
		 * Adicionado por: Ricardo Germinio 
		 * Data: 19/10/2012 RN2012063453 
		 * Inclus�o de campo para informar 
		 * indicador de que os imoveis nao faturado recebe rateio de consumo da area comum
		 */
		if (sistemaParametro.getIndicadorRateioAreaComumImovelNaoFat() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorRateioAreaComumImovelNaoFat(sistemaParametro
							.getIndicadorRateioAreaComumImovelNaoFat().toString());
		}
		
		/*
		 * Autor: Jonathan Marcos
		 * Data: 03/10/2013
		 * [Observacao] Indicador para verificar
		 * a acao predecessora para os imoveis do arquivo texto
		 */
		if (sistemaParametro.getIndicadorAcaoPredecessoraImoveisArquivoTexto() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorAcaoPredecessoraImoveisArquivoTexto(sistemaParametro
							.getIndicadorAcaoPredecessoraImoveisArquivoTexto()
							.toString());
		}
		
		/*
		 * Autor: Diogo Luiz
		 * Data: 23/09/2014
		 * 1.2.2.38. Indicador de Permiss�o de Altera��o de Dados ou V�nculo de Cliente Negativado 
		 */
		if(sistemaParametro.getIndicadorPermissaoAlteracaoClienteNegativado() != null){
			consultarSistemaParametroActionForm.setIndicadorPermissaoAlteracaoClienteNegativado(
				sistemaParametro.getIndicadorPermissaoAlteracaoClienteNegativado().toString());
		}		
	}
		
		
	private void montarSistemaParametro5Tabela(
			ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm,
			SistemaParametro sistemaParametro) {

		Fachada fachada = Fachada.getInstancia();

		if (sistemaParametro.getIndicadorSugestaoTramite() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorSugestaoTramite(sistemaParametro
							.getIndicadorSugestaoTramite().toString());
		}

		if (sistemaParametro.getDiasReativacao() != null) {
			consultarSistemaParametroActionForm
					.setDiasMaximoReativarRA(sistemaParametro
							.getDiasReativacao().toString());
		}

		if (sistemaParametro.getDiasMaximoAlterarOS() != null) {
			consultarSistemaParametroActionForm
					.setDiasMaximoAlterarOS(sistemaParametro
							.getDiasMaximoAlterarOS().toString());
		}

		if (sistemaParametro.getNumeroDiasEncerramentoOrdemServico() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasEncerramentoOrdemServico(sistemaParametro
							.getNumeroDiasEncerramentoOrdemServico().toString());
		}

		if (sistemaParametro.getNumeroDiasEncerramentoOSSeletiva() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasEncerramentoOSSeletiva(sistemaParametro
							.getNumeroDiasEncerramentoOSSeletiva().toString());
		}
		
		
		//1.3.1.9.	Situa��o de �gua na Exclus�o de Im�vel 
		if(sistemaParametro.getSituacaoAguaExclusaoImovel() != null){
			
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, 
				sistemaParametro.getSituacaoAguaExclusaoImovel()));
			Collection colecaoFiltroAgua = fachada.pesquisar(filtroLigacaoAguaSituacao, 
				LigacaoAguaSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoFiltroAgua)){
				
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) 
						Util.retonarObjetoDeColecao(colecaoFiltroAgua);
				
				consultarSistemaParametroActionForm.setSituacaoAguaExclusaoImovel(
					ligacaoAguaSituacao.getDescricao());
			}			
		}
		
		
		//1.3.1.10.	Situa��o de Esgoto na Exclus�o de Im�vel
		if(sistemaParametro.getSituacaoEsgotoExclusaoImovel() != null){
			
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
				sistemaParametro.getSituacaoEsgotoExclusaoImovel()));
			Collection colecaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, 
				LigacaoEsgotoSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoEsgotoSituacao)){
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) 
						Util.retonarObjetoDeColecao(colecaoEsgotoSituacao);
				consultarSistemaParametroActionForm.setSituacaoEsgotoExclusaoImovel(
					ligacaoEsgotoSituacao.getDescricao());
			}
		}
		

		if (sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasAlteracaoVencimentoPosterior(sistemaParametro
							.getNumeroDiasAlteracaoVencimentoPosterior()
							.toString());
		}

		if (sistemaParametro.getUltimoRAManual() != null) {
			consultarSistemaParametroActionForm
					.setUltimoIDGeracaoRA(sistemaParametro.getUltimoRAManual()
							.toString());
		}

		if (sistemaParametro.getNumeroDiasExpiracaoAcesso() != null) {
			consultarSistemaParametroActionForm
					.setDiasMaximoExpirarAcesso(sistemaParametro
							.getNumeroDiasExpiracaoAcesso().toString());
		}

		if (sistemaParametro.getNumeroDiasMensagemExpiracao() != null) {
			consultarSistemaParametroActionForm
					.setDiasMensagemExpiracaoSenha(sistemaParametro
							.getNumeroDiasMensagemExpiracao().toString());
		}

		if (sistemaParametro.getNumeroMaximoLoginFalho() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMaximoTentativasAcesso(sistemaParametro
							.getNumeroMaximoLoginFalho().toString());
		}

		if (sistemaParametro.getIndicadorControleTramitacaoRA() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorControleTramitacaoRA(""
							+ sistemaParametro
									.getIndicadorControleTramitacaoRA());
		}

		if (sistemaParametro.getNumeroMaximoFavorito() != null) {
			consultarSistemaParametroActionForm
					.setNumeroMaximoFavoritosMenu(sistemaParametro
							.getNumeroMaximoFavorito().toString());
		}

		if (sistemaParametro.getIpServidorSmtp() != null) {
			consultarSistemaParametroActionForm
					.setIpServidorSmtp(sistemaParametro.getIpServidorSmtp());
		}

		if (sistemaParametro.getIpServidorModuloGerencial() != null) {
			consultarSistemaParametroActionForm
					.setIpServidorGerencial(sistemaParametro
							.getIpServidorModuloGerencial());
		}

		if (sistemaParametro.getDsEmailResponsavel() != null) {
			consultarSistemaParametroActionForm
					.setEmailResponsavel(sistemaParametro
							.getDsEmailResponsavel());
		}

		if (sistemaParametro.getMensagemSistema() != null) {
			consultarSistemaParametroActionForm
					.setMensagemSistema(sistemaParametro.getMensagemSistema());
		}

		if (sistemaParametro
				.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos() != null) {
			consultarSistemaParametroActionForm
					.setDiasVencimentoCertidaoNegativa(""
							+ sistemaParametro
									.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
		}

		if (sistemaParametro.getIndicadorDocumentoValido() != null) {
			consultarSistemaParametroActionForm.setIndicadorDocumentoValido(""
					+ sistemaParametro.getIndicadorDocumentoValido());
		}
		//RM 5759
		if (sistemaParametro.getIndicadorPermiteCancelarDebito() != null) {
			consultarSistemaParametroActionForm.setIndicadorPermiteCancelarDebito(""
					+ sistemaParametro.getIndicadorPermiteCancelarDebito());
		}
		
		if (sistemaParametro.getIndicadorValidarLocalizacaoEncerramentoOS() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorValidacaoLocalidadeEncerramentoOS(""
							+ sistemaParametro
									.getIndicadorValidarLocalizacaoEncerramentoOS());
		} else {
			consultarSistemaParametroActionForm
					.setIndicadorValidacaoLocalidadeEncerramentoOS(""
							+ ConstantesSistema.NAO);
		}

		consultarSistemaParametroActionForm
				.setIndicadorDebitoACobrarValidoCertidaoNegativa(""
						+ sistemaParametro
								.getIndicadorDebitoACobrarValidoCertidaoNegativa());

		consultarSistemaParametroActionForm.setIndicadorLoginUnico(""
				+ sistemaParametro.getIndicadorLoginUnico());

		consultarSistemaParametroActionForm
				.setIndicadorDebitoACobrarValidoCertidaoNegativa(""
						+ sistemaParametro
								.getIndicadorDebitoACobrarValidoCertidaoNegativa());

		consultarSistemaParametroActionForm
				.setIndicadorCertidaoNegativaEfeitoPositivo(""
						+ sistemaParametro
								.getIndicadorCertidaoNegativaEfeitoPositivo());

		if (sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo() != null) {
			consultarSistemaParametroActionForm
					.setIndicarControleExpiracaoSenhaPorGrupo(sistemaParametro
							.getIndicadorControleExpiracaoSenhaPorGrupo()
							.toString());
		}
		if (sistemaParametro.getIndicadorControleBloqueioSenhaAnterior() != null) {
			consultarSistemaParametroActionForm
					.setIndicarControleBloqueioSenha(sistemaParametro
							.getIndicadorControleBloqueioSenhaAnterior()
							.toString());
		}
		if (sistemaParametro.getIndicadorSenhaForte() != null) {
			consultarSistemaParametroActionForm
					.setIndicadorSenhaForte(sistemaParametro
							.getIndicadorSenhaForte().toString());
		}
		if (sistemaParametro.getDescricaoDecreto() != null) {
			consultarSistemaParametroActionForm
					.setDescricaoDecreto(sistemaParametro.getDescricaoDecreto()
							.toString());
		}
		if (sistemaParametro.getDescricaoLeiEstTarif() != null) {
			consultarSistemaParametroActionForm
					.setDescricaoLeiEstTarif(sistemaParametro.getDescricaoLeiEstTarif()
							.toString());
		}
		if (sistemaParametro.getDescricaoLeiIndividualizacao() != null) {
			consultarSistemaParametroActionForm
					.setDescricaoLeiIndividualizacao(sistemaParametro.getDescricaoLeiIndividualizacao()
							.toString());
		}
		if (sistemaParametro.getDescricaoNormaCM() != null) {
			consultarSistemaParametroActionForm
					.setDescricaoNormaCM(sistemaParametro.getDescricaoNormaCM()
							.toString());
		}
		if (sistemaParametro.getDescricaoNormaCO() != null) {
			consultarSistemaParametroActionForm
					.setDescricaoNormaCO(sistemaParametro.getDescricaoNormaCO()
							.toString());
		}
		
		if (sistemaParametro.getArquivoDecreto() != null && sistemaParametro.getArquivoDecreto().length != 0) {
			consultarSistemaParametroActionForm
					.setArquivoDecreto(sistemaParametro.getArquivoDecreto());
		}
		
		if (sistemaParametro.getArquivoLeiEstTarif() != null && sistemaParametro.getArquivoLeiEstTarif().length != 0) {
			consultarSistemaParametroActionForm
					.setArquivoLeiEstTarif(sistemaParametro.getArquivoLeiEstTarif());
		}
		
		if (sistemaParametro.getArquivoLeiIndividualizacao() != null && sistemaParametro.getArquivoLeiIndividualizacao().length != 0) {
			consultarSistemaParametroActionForm
					.setArquivoLeiIndividualizacao(sistemaParametro.getArquivoLeiIndividualizacao());
		}
		
		if (sistemaParametro.getArquivoNormaCM() != null && sistemaParametro.getArquivoNormaCM().length != 0) {
			consultarSistemaParametroActionForm
					.setArquivoNormaCM(sistemaParametro.getArquivoNormaCM());
		}
		
		if (sistemaParametro.getArquivoNormaCO() != null && sistemaParametro.getArquivoNormaCO().length != 0) {
			consultarSistemaParametroActionForm
					.setArquivoNormaCO(sistemaParametro.getArquivoNormaCO());
		}
		
		if (sistemaParametro.getUnidadeOrganizacionalTramiteGrandeConsumidor() != null) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID,
							sistemaParametro
									.getUnidadeOrganizacionalTramiteGrandeConsumidor()
									.getId()));

			Collection<UnidadeOrganizacional> colecao = fachada.pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecao);

			consultarSistemaParametroActionForm
					.setIdUnidadeDestinoGrandeConsumidor(unidade.getId()
							.toString());
			consultarSistemaParametroActionForm
					.setNomeUnidadeDestinoGrandeConsumidor(unidade
							.getDescricao());

		}

		if (sistemaParametro.getNumeroDiasRevisaoComPermEspecial() != null) {
			consultarSistemaParametroActionForm
					.setNumeroDiasRevisaoConta(sistemaParametro
							.getNumeroDiasRevisaoComPermEspecial().toString());
		}
				
		if(sistemaParametro.getQtdeDiasValidadeOSFiscalizacao() != null){
			consultarSistemaParametroActionForm
					.setQtdeDiasValidadeOSFiscalizacao(sistemaParametro
							.getQtdeDiasValidadeOSFiscalizacao().toString());
		}
		
		if(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao() != null){
			consultarSistemaParametroActionForm
					.setQtdeDiasEncerraOSFiscalizacao(sistemaParametro
							.getQtdeDiasEncerraOSFiscalizacao().toString());
		}
		
		if(sistemaParametro.getQtdeDiasEnvioEmailConta() != null){
			consultarSistemaParametroActionForm
					.setQtdeDiasEnvioEmailConta(sistemaParametro
							.getQtdeDiasEnvioEmailConta().toString());
		}
		
		if(sistemaParametro.getQtdDiasGuardarOcorrenciasParalisacao() != null)
			consultarSistemaParametroActionForm.setQtdDiasGuardarOcorrenciasParalisacao(sistemaParametro.getQtdDiasGuardarOcorrenciasParalisacao().toString());
		
		
		if(sistemaParametro.getIndicadorDocObrAtendimentoOperacional() != null)
			consultarSistemaParametroActionForm.setIndicadorDocObrAtendimentoOperacional(sistemaParametro.getIndicadorDocObrAtendimentoOperacional().toString());
		
		
		if(sistemaParametro.getIndicadorDocObrAtendimentoComercial() != null)
			consultarSistemaParametroActionForm.setIndicadorDocObrAtendimentoComercial(sistemaParametro.getIndicadorDocObrAtendimentoComercial().toString());
		
		
		if(sistemaParametro.getIndicadorDocObrInformacao() != null)
			consultarSistemaParametroActionForm.setIndicadorDocObrInformacao(sistemaParametro.getIndicadorDocObrInformacao().toString());
	
		
		if(sistemaParametro.getIndicadorDocObrReiteracao() != null)
			consultarSistemaParametroActionForm.setIndicadorDocObrReiteracao(sistemaParametro.getIndicadorDocObrReiteracao().toString());
		
		
		
	}

	/**
	 * Pesquisa Endereco
	 * 
	 */
	private void pesquisarEndereco(SistemaParametro sistemaParametro,
			HttpServletRequest httpServletRequest) {

		if (this.getSessao(httpServletRequest).getAttribute("colecaoEnderecos") == null) {

			Imovel imovel = new Imovel();

			// Pesquisa o Logradouro Cep
			if (sistemaParametro.getLogradouroCep() != null) {
				FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(
						FiltroLogradouroCep.ID, sistemaParametro
								.getLogradouroCep().getId()));

				filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");
				filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTitulo");
				filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("cep");

				Collection colecaoLogradouroCep = this.getFachada().pesquisar(
						filtroLogradouroCep, LogradouroCep.class.getName());

				LogradouroCep logradouroCep = (LogradouroCep) Util
						.retonarObjetoDeColecao(colecaoLogradouroCep);

				imovel.setLogradouroCep(logradouroCep);

			}

			// Pesquisa o Logradouro Bairro
			if (sistemaParametro.getLogradouroBairro() != null) {
				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
						FiltroLogradouroBairro.ID, sistemaParametro
								.getLogradouroBairro().getId()));

				filtroLogradouroBairro
						.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio.unidadeFederacao");

				Collection colecaoLogradouroBairro = this.getFachada()
						.pesquisar(filtroLogradouroBairro,
								LogradouroBairro.class.getName());

				LogradouroBairro logradouroBairro = (LogradouroBairro) Util
						.retonarObjetoDeColecao(colecaoLogradouroBairro);

				imovel.setLogradouroBairro(logradouroBairro);
			}

			imovel.setEnderecoReferencia(sistemaParametro
					.getEnderecoReferencia());
			imovel.setNumeroImovel(sistemaParametro.getNumeroImovel());
			imovel.setComplementoEndereco(sistemaParametro
					.getComplementoEndereco());

			Collection colecaoEndereco = new ArrayList();
			colecaoEndereco.add(imovel);

			this.getSessao(httpServletRequest).setAttribute("colecaoEnderecos",
					colecaoEndereco);
			
		}
	}

	/**
	 * Monta o Endereco
	 * 
	 */
	private void montarEndereco(
			ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm,
			HttpServletRequest httpServletRequest) {

		// Removendo endere�o
		String removerEndereco = httpServletRequest
				.getParameter("removerEndereco");
		HttpSession sessao = this.getSessao(httpServletRequest);

		if (removerEndereco != null
				&& !removerEndereco.trim().equalsIgnoreCase("")) {

			if (sessao.getAttribute("colecaoEnderecos") != null) {

				Collection enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecos");

				if (!enderecos.isEmpty()) {
					sessao.removeAttribute("colecaoEnderecos");
				}
			}
		}

		// Caso tenha adicionado o endere�o seta os valores dos campos de
		// municipio e bairro
		if (sessao.getAttribute("colecaoEnderecos") != null) {

			Collection colecaoEnderecos = (Collection) sessao
					.getAttribute("colecaoEnderecos");

			if (!colecaoEnderecos.isEmpty()) {

				Imovel imovel = (Imovel) Util
						.retonarObjetoDeColecao(colecaoEnderecos);

				if (imovel.getLogradouroBairro() != null) {
					consultarSistemaParametroActionForm
							.setLogradouroBairro(imovel.getLogradouroBairro()
									.getId().toString());
				}

				if (imovel.getLogradouroCep() != null) {
					consultarSistemaParametroActionForm.setLogradouroCep(imovel
							.getLogradouroCep().getId().toString());
				}
				if (imovel.getEnderecoReferencia() != null) {
					consultarSistemaParametroActionForm
							.setEnderecoReferencia(imovel
									.getEnderecoReferencia().getId().toString());
				} else {
					consultarSistemaParametroActionForm
							.setEnderecoReferencia("");
				}
				consultarSistemaParametroActionForm.setNumero(imovel
						.getNumeroImovel());
				consultarSistemaParametroActionForm.setComplemento(imovel
						.getComplementoEndereco());
			}
		}
	}
	
	private void retornaArquivo(String arquivo, HttpServletResponse httpServletResponse,
			SistemaParametro sistemaParametro){
		
		String mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
		OutputStream out = null;
		byte[] file = null;
		
		if(arquivo.equalsIgnoreCase("decreto")){
			file = sistemaParametro.getArquivoDecreto();
		}
		if(arquivo.equalsIgnoreCase("leiTarifa")){
			file = sistemaParametro.getArquivoLeiEstTarif();
		}
		if(arquivo.equalsIgnoreCase("leiNormaMedicao")){
			file = sistemaParametro.getArquivoLeiIndividualizacao();
		}
		if(arquivo.equalsIgnoreCase("normaCO")){
			file = sistemaParametro.getArquivoNormaCO();
		}
		if(arquivo.equalsIgnoreCase("normaCM")){
			file = sistemaParametro.getArquivoNormaCM();
		}
		
		
		try {
			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();
			
			out.write(file);
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			throw new ActionServletException("erro.sistema", e);
		}
		
	}
	private void setarDownloadsLoja(ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm, HttpServletRequest httpServletRequest ){
		if(consultarSistemaParametroActionForm.getArquivoDecreto() != null &&
				consultarSistemaParametroActionForm.getArquivoDecreto().length != 0){
			httpServletRequest.setAttribute("arquivoDecreto", true);
		}else{
			httpServletRequest.removeAttribute("arquivoDecreto");
		}
		
		if(consultarSistemaParametroActionForm.getArquivoLeiEstTarif() != null &&
				consultarSistemaParametroActionForm.getArquivoLeiEstTarif().length != 0){
			httpServletRequest.setAttribute("arquivoLeiTarifa", true);
		}else{
			httpServletRequest.removeAttribute("arquivoLeiTarifa");
		}
		
		if(consultarSistemaParametroActionForm.getArquivoLeiIndividualizacao() != null &&
				consultarSistemaParametroActionForm.getArquivoLeiIndividualizacao().length != 0){
			httpServletRequest.setAttribute("arquivoLeiNormaMedicao", true);
		}else{
			httpServletRequest.removeAttribute("arquivoLeiNormaMedicao");
		}
		
		if(consultarSistemaParametroActionForm.getArquivoNormaCM() != null &&
				consultarSistemaParametroActionForm.getArquivoNormaCM().length != 0){
			httpServletRequest.setAttribute("arquivoNormaCM", true);
		}else{
			httpServletRequest.removeAttribute("arquivoNormaCM");
		}
		
		if(consultarSistemaParametroActionForm.getArquivoNormaCO() != null &&
				consultarSistemaParametroActionForm.getArquivoNormaCO().length != 0){
			httpServletRequest.setAttribute("arquivoNormaCO", true);
		}else{
			httpServletRequest.removeAttribute("arquivoNormaCO");
		}
	}

}
