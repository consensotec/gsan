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

import gsan.atendimentopublico.ResolucaoImagem;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.endereco.FiltroLogradouroBairro;
import gsan.cadastro.endereco.FiltroLogradouroCep;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.endereco.LogradouroCep;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * @date 04/01/2007
 */
public class ExibirInformarParametrosSistemaDadosGeraisEmpresaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirInformarParametrosSistemaDadosGeraisEmpresa");

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		HttpSession sessao = this.getSessao(httpServletRequest);

		SistemaParametro sistemaParametro = (SistemaParametro) sessao
				.getAttribute("sistemaParametro");

		Fachada fachada = Fachada.getInstancia();
		
		//Cole��o Resolucao Imagem
		Filtro filtro = new FiltroCliente();
		Collection<ResolucaoImagem> colecao = 
				(Collection<ResolucaoImagem>)fachada.pesquisar(filtro, ResolucaoImagem.class.getName());
		sessao.setAttribute("colResolucaoImagem", colecao);

		// Seta os dados no form
		if (httpServletRequest.getParameter("menu") != null) {
			
			form.setQtdeContasRetificadas(""+sistemaParametro.getQtdMaxContasRetificadas());
			form.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(sistemaParametro.getIndicadorCriticarConteudoRetornoMovimentoNegativacao().toString());
			form.setIndicadorCalcAcresImpontGuiaPagamento(sistemaParametro.getIndicadorCalcAcresImpontGuiaPagamento().toString());
		
			form.setNomeEstado(sistemaParametro.getNomeEstado());
			form.setNomeEmpresa(sistemaParametro.getNomeEmpresa());
			form.setAbreviaturaEmpresa(sistemaParametro
					.getNomeAbreviadoEmpresa());
			form.setCnpj(sistemaParametro.getCnpjEmpresa());

			if (sistemaParametro.getNumeroImovel() != null) {
				form.setNumero(sistemaParametro.getNumeroImovel());
			}
			//Seta o n�mero de dias de envio conta empresa cobran�a
			if (sistemaParametro.getNumeroDiasEnvioContaEmpresaCobranca() != null) {
				form.setNumeroDiasEnvioContaEmpresaCobranca(sistemaParametro.getNumeroDiasEnvioContaEmpresaCobranca().toString());
			}
			//Seta o n�mero de dias para retirada da empresa cobran�a
			if (sistemaParametro.getNumeroDiaRetiradaContaEmpresaCobraca() != null) {
				form.setNumeroDiaRetiradaContaEmpresaCobraca(sistemaParametro.getNumeroDiaRetiradaContaEmpresaCobraca().toString());
			}
			//QuantidadeDiasValidadeCerticaoNegativa
			if (sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa() != null) {
				form.setQuantidadeDiasValidadeCerticaoNegativa(sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa().toString());
			}
			//N�mero maximo de parcelas de contratos parcelamento na primeira aba
			if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null) {
				form.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro.getNumeroMaximoParcelasContratosParcelamento().toString());
			}
			//Indicador de conta fora do vencimeto na primeira aba
			if (sistemaParametro.getIndicadorIncluirContasForaVenCobranca() != null) {
				form.setIndicadorIncluirContasForaVenCobranca(sistemaParametro.getIndicadorIncluirContasForaVenCobranca().toString());
			}
			//Numero dias vencimento 
			if(sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos() != null){
				form.setDiasVencimentoCertidaoNegativa(""+ sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
			}
			
			if (sistemaParametro.getMensagemSistema() != null) {
				form.setMensagemSistema(sistemaParametro.getMensagemSistema());
			}

			if(sistemaParametro.getNumeroMesesMaximoCalculoMedia() != null){
				form.setNumeroMesesMaximoCalculoMedia(sistemaParametro.getNumeroMesesMaximoCalculoMedia().toString());
			}
			
			if(sistemaParametro.getIndicadorValidaCpfCnpj() != null){
				form.setIndicadorValidaCpfCnpj(sistemaParametro.getIndicadorValidaCpfCnpj().toString());
			}
			
			if(sistemaParametro.getMsgVencimentoGuiaEntradaParc() != null){
				form.setMsgVencimentoGuiaEntradaParc(sistemaParametro.getMsgVencimentoGuiaEntradaParc());
			}
			
			
			// seta a quantidade de d�gitos da quadra da empresa de acordo com
			// os dados pegos do banco de
			// dados que foram passados pelo
			// ExibirInformarParametrosSistemaAction
			// o n�mero de quadra tem como valor default 3 - uma vez que � NOT
			// NULL, n�o precisa de
			// valida��o
			form.setQuantidadeDigitosQuadra(sistemaParametro
					.getNumeroDigitosQuadra() + "");

			if (sistemaParametro.getComplementoEndereco() != null) {
				form.setComplemento(sistemaParametro.getComplementoEndereco());
			}

			if (sistemaParametro.getNumeroTelefone() != null) {
				form.setNumeroTelefone(sistemaParametro.getNumeroTelefone());
			}
			
			if (sistemaParametro.getNumeroTelefoneAtendimentoEsgoto() != null) {
				form.setNumeroTelefoneAtendimentoEsgoto(sistemaParametro.getNumeroTelefoneAtendimentoEsgoto());
			}

			if (sistemaParametro.getNumeroRamal() != null) {
				form.setRamal(sistemaParametro.getNumeroRamal());
			}

			if (sistemaParametro.getNumeroFax() != null) {
				form.setFax(sistemaParametro.getNumeroFax());
			}

			if (sistemaParametro.getDescricaoEmail() != null) {
				form.setEmail(sistemaParametro.getDescricaoEmail());
			}

			if (sistemaParametro.getTituloPagina() != null) {
				form.setTitulosRelatorio(sistemaParametro.getTituloPagina());
			}

			if (sistemaParametro.getUnidadeOrganizacionalIdPresidencia() != null) {
				form.setUnidadeOrganizacionalPresidencia(sistemaParametro
						.getUnidadeOrganizacionalIdPresidencia().getId()
						.toString());

				form.setNomeUnidadeOrganizacionalPresidencia(sistemaParametro
						.getUnidadeOrganizacionalIdPresidencia().getDescricao());
			}

			if (sistemaParametro.getClientePresidenteCompesa() != null) {
				form.setPresidente(sistemaParametro
						.getClientePresidenteCompesa().getId().toString());

				form.setNomePresidente(sistemaParametro
						.getClientePresidenteCompesa().getDescricao());
			}

			if (sistemaParametro.getClienteDiretorComercialCompesa() != null) {
				form.setDiretorComercial(sistemaParametro
						.getClienteDiretorComercialCompesa().getId().toString());

				form.setNomeDiretorComercial(sistemaParametro
						.getClienteDiretorComercialCompesa().getDescricao());
			}

			if (sistemaParametro.getNumero0800Empresa() != null) {
				form.setNumeroTelefoneAtendimento(sistemaParametro
						.getNumero0800Empresa());
			}

			if (sistemaParametro.getNomeSiteEmpresa() != null) {
				form.setSite(sistemaParametro.getNomeSiteEmpresa());
			}

			if (sistemaParametro.getInscricaoEstadual() != null) {
				form.setInscricaoEstadual(sistemaParametro
						.getInscricaoEstadual());
			}

			if (sistemaParametro.getInscricaoMunicipal() != null) {
				form.setInscricaoMunicipal(sistemaParametro
						.getInscricaoMunicipal());
			}

			if (sistemaParametro.getNumeroContratoPrestacaoServico() != null) {
				form.setNumeroContrato(sistemaParametro
						.getNumeroContratoPrestacaoServico().toString());
			}

			if (sistemaParametro.getImagemLogomarca() != null) {
				form.setImagemLogomarca(sistemaParametro.getImagemLogomarca());
			}

			if (sistemaParametro.getImagemRelatorio() != null) {
				form.setImagemRelatorio(sistemaParametro.getImagemRelatorio());
			}

			if (sistemaParametro.getImagemConta() != null) {
				form.setImagemConta(sistemaParametro.getImagemConta());
			}

			if (sistemaParametro.getNumeroExecucaoResumoNegativacao() != null) {
				form.setNumeroExecucaoResumoNegativacao(sistemaParametro
						.getNumeroExecucaoResumoNegativacao().toString());
			}

			if (sistemaParametro.getVersaoCelular() != null) {
				form.setVersaoCelular(sistemaParametro.getVersaoCelular());
			}

			if (sistemaParametro.getNumeroDiasBloqueioCelular() != null) {
				form.setNumeroDiasBloqueioCelular(sistemaParametro
						.getNumeroDiasBloqueioCelular().toString());
			}

			if (sistemaParametro.getPercentualConvergenciaRepavimentacao() != null) {
				form.setPercentualConvergenciaRepavimentacao(Util
						.formatarBigDecimalParaStringComVirgula(sistemaParametro
								.getPercentualConvergenciaRepavimentacao()));
			}
			if (sistemaParametro
					.getNumeroDiasVencContaEntradaParcelamento() != null) {
				form.setNumeroDiasVencContaEntradaParcelamento(sistemaParametro
						.getNumeroDiasVencContaEntradaParcelamento()
						.toString());
			}
			
			if (sistemaParametro.getNumeroDiasCancelamentoEntradaParcelamento() != null) {
				form.setNumeroDiasCancelamentoEntradaParcelamento(sistemaParametro
						.getNumeroDiasCancelamentoEntradaParcelamento().toString());
			}
			
			form.setIndicadorControlaAutoInfracao(""
					+ sistemaParametro.getIndicadorControlaAutoInfracao());

			form.setIndicadorExibirMensagem(""
					+ sistemaParametro
							.getIndicadorExibeMensagemNaoReceberPagamento());

			form.setIndicadorUsaRota(""
					+ sistemaParametro.getIndicadorUsaRota());
			
			form.setIndicadorDuplicidadeCliente(
					sistemaParametro.getIndicadorDuplicidadeCliente().toString());
			form.setIndicadorNomeMenorDez(
					sistemaParametro.getIndicadorNomeMenorDez().toString());
			form.setIndicadorNomeClienteGenerico(
					sistemaParametro.getIndicadorNomeClienteGenerico().toString());
			
			form.setIndicadorAlterarNomeClienteCpfCnpjValidado(
					sistemaParametro.getIndicadorAlterarNomeClienteCpfCnpjValidado().toString());

			this.pesquisarEndereco(sistemaParametro, httpServletRequest);
			
			if (sistemaParametro.getIndicadorVariaHierarquiaUnidade() != null){
				form.setIndicadorVariaHierarquiaUnidade(sistemaParametro.getIndicadorVariaHierarquiaUnidade().toString());
			}
			
			if (sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados() != null) {
				form.setClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro
						.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados().getId().toString());

				form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro
						.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados().getDescricao());
				
				httpServletRequest.setAttribute("clienteFicticioEncontrado", true);
			}

			if (sistemaParametro.getPerfilProgramaEspecial() != null) {
				form.setPerfilProgramaEspecial(sistemaParametro
						.getPerfilProgramaEspecial().getId().toString());
			}

			if (sistemaParametro.getClienteResponsavelProgramaEspecial() != null) {

				FiltroCliente filtroCliente = new FiltroCliente();

				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, sistemaParametro
								.getClienteResponsavelProgramaEspecial()
								.getId()));

				Collection<Cliente> colecaoClientes = fachada.pesquisar(
						filtroCliente, Cliente.class.getName());

				Cliente cliente = (Cliente) Util
						.retonarObjetoDeColecao(colecaoClientes);

				if (cliente != null) {
					form.setIdClienteResponsavelProgramaEspecial(cliente
							.getId().toString());
					form.setNomeClienteResponsavelProgramaEspecial(cliente
							.getNome());
					httpServletRequest.setAttribute("codigoClienteEncontrado",
							"true");
				}
			}

			if (sistemaParametro.getIndicadorPopupAtualizacaoCadastral() != null) {
				form.setIndicadorPopupAtualizacaoCadastral(sistemaParametro
						.getIndicadorPopupAtualizacaoCadastral().toString());
			}

			if (sistemaParametro.getValorExtratoFichaComp() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getValorExtratoFichaComp());

				form.setValorExtratoFichaComp(valorAux);
			}

			if (sistemaParametro.getValorGuiaFichaComp() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getValorGuiaFichaComp());

				form.setValorGuiaFichaComp(valorAux);
			}
			
			if (sistemaParametro.getValorDemonstrativoParcelamentoFichaComp() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getValorDemonstrativoParcelamentoFichaComp());

				form.setValorDemonstrativoParcelamentoFichaComp(valorAux);
			}

			if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia() != null) {
				form.setIndicadorUsoNMCliReceitaFantasia(sistemaParametro
						.getIndicadorUsoNMCliReceitaFantasia().toString());
			}
			
			if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null
					&& (form.getNumeroMaximoParcelasContratosParcelamento() == null 
					|| form.getNumeroMaximoParcelasContratosParcelamento().equals(""))){
				form.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro.getNumeroMaximoParcelasContratosParcelamento().toString());
			}
			
			if (sistemaParametro.getIndicadorBloquearFunUsuario() != null) {
				form.setIndicadorBloquearFunUsuario(sistemaParametro.
						getIndicadorBloquearFunUsuario().toString());
			}
			
			if(sistemaParametro.getIndicadorNomeClienteAtualConta() != null) {
				form.setIndicadorNomeClienteAtualConta(sistemaParametro.getIndicadorNomeClienteAtualConta().toString());
			}
			
			if(sistemaParametro.getTamanhoMaximoAnexoRA() != null){
				form.setTamanhoMaximoAnexoRA(sistemaParametro.getTamanhoMaximoAnexoRA().toString());
			}
			
			if(sistemaParametro.getResolucaoImagem() != null){
				form.setResolucaoImagem(sistemaParametro.getResolucaoImagem().getId().toString());
			}
			
			/**
			 * #10981 - ROTINA PARA ENVIAR E-MAIL E SMS PARA DEVEDORES.
			 * @author Diogo Luiz
			 * @date 02/07/2014
			 * 
			 * RM 11444 - [UC0060] - Informar Parametros Sistema e [UC0061] - Consutar Par�metros Sistema
			 */
			if(sistemaParametro.getTamanhoMaximoMensagemSms() != null){
				form.setTamanhoMaximoMensagemSms(sistemaParametro.getTamanhoMaximoMensagemSms().toString());
			}
			
			

		}

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");

		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("1")) {

			// Faz a consulta de Unidade Organizacional
			this.pesquisarUnidadeOrganizacional(form, httpServletRequest);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("2")) {

			// Faz a consulta de Cliente - Presidente
			this.pesquisarCliente(form, true, httpServletRequest);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("3")) {

			// Faz a consulta de Cliente - Diretor Comercial
			this.pesquisarCliente(form, false, httpServletRequest);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("6")) {

			// Faz a consulta de Cliente - Diretor Comercial
			this.pesquisarClientePrograma(form, httpServletRequest);
		}
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")
				&& objetoConsulta.trim().equals("7")) {

			// Faz a consulta de Cliente - Cliente Fict�cio
			this.pesquisarClienteFicticio(form, httpServletRequest);
		}
		
		form.setIndicadorCpfCnpj("" + sistemaParametro.getIndicadorConsultaDocumentoReceita());

		if (httpServletRequest.getParameter("pesquisarCliente") != null
				&& httpServletRequest.getParameter("pesquisarCliente")
						.toString().equalsIgnoreCase("sim")) {

		
		
		this.setaRequest(httpServletRequest,form);
		this.montarEndereco(form,httpServletRequest);
		this.carregarColecaoPerfisImovel(form,httpServletRequest);
		
			// Faz a consulta de Cliente ResponsavelProgramaEspecial
			form.setIdClienteResponsavelProgramaEspecial(httpServletRequest
					.getParameter("codigoCliente").toString());

			this.pesquisarClientePrograma(form, httpServletRequest);
		}

		form.setIndicadorDocumentoObrigatorio(""
				+ sistemaParametro.getIndicadorDocumentoObrigatorio());

		this.setaRequest(httpServletRequest, form);
		this.montarEndereco(form, httpServletRequest);
		this.carregarColecaoPerfisImovel(form, httpServletRequest);

		return retorno;
	}

	/**
	 * Pesquisa Endereco
	 * 
	 * @author Rafael Pinto
	 * @date 30/07/2008
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
	 * @author Arthur Carvalho
	 * @date 22/07/2008
	 */
	private void montarEndereco(InformarSistemaParametrosActionForm form,
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
					form.setLogradouroBairro(imovel.getLogradouroBairro()
							.getId().toString());
				}

				if (imovel.getLogradouroBairro() != null) {
					form.setLogradouroCep(imovel.getLogradouroCep().getId()
							.toString());
				}
				if (imovel.getEnderecoReferencia() != null) {
					form.setEnderecoReferencia(imovel.getEnderecoReferencia()
							.getId().toString());
				}
				form.setNumero(imovel.getNumeroImovel());
				form.setComplemento(imovel.getComplementoEndereco());

			}

		}
	}

	/**
	 * Pesquisa Unidade Organizacional
	 * 
	 * @author Rafael Pinto
	 * @date 17/07/2008
	 */
	private void pesquisarUnidadeOrganizacional(
			InformarSistemaParametrosActionForm form,
			HttpServletRequest httpServletRequest) {

		// Filtro para obter unidade organizacional ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		String idUnidade = form.getUnidadeOrganizacionalPresidencia();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidade);

			// Exibe o c�digo e a descri��o pesquisa na p�gina
			httpServletRequest.setAttribute(
					"unidadeOrganizacionalPresidenciaInexistente", "true");

			form.setUnidadeOrganizacionalPresidencia(unidadeOrganizacional
					.getId().toString());
			form.setNomeUnidadeOrganizacionalPresidencia(unidadeOrganizacional
					.getDescricao());

		} else {

			form.setNomeUnidadeOrganizacionalPresidencia("Unidade Organizacional inexistente");
			form.setUnidadeOrganizacionalPresidencia(null);

		}
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 17/07/2006
	 */
	private void pesquisarCliente(InformarSistemaParametrosActionForm form,
			boolean ehPresidente, HttpServletRequest httpServletRequest) {

		String codigoCliente = null;

		if (ehPresidente) {
			codigoCliente = form.getPresidente();
		} else {
			codigoCliente = form.getDiretorComercial();
		}
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				new Integer(codigoCliente)));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoCliente = this.getFachada().pesquisar(filtroCliente,
				Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoCliente);

			if (ehPresidente) {
				httpServletRequest.setAttribute("presidenteEncontrado", "true");

				form.setPresidente(cliente.getId().toString());
				form.setNomePresidente(cliente.getNome());
			} else {
				httpServletRequest.setAttribute("diretorComercialEncontrado",
						"true");

				form.setDiretorComercial(cliente.getId().toString());
				form.setNomeDiretorComercial(cliente.getNome());
			}

		} else {
			if (ehPresidente) {
				form.setPresidente(null);
				form.setNomePresidente("Cliente inexistente");
			} else {
				form.setDiretorComercial(null);
				form.setNomeDiretorComercial("Cliente inexistente");
			}
		}
	}
	
	/**
	 * Pesquisa Cliente Ficticio
	 * 
	 * @author Nathalia Santos 
	 * @date 12/08/2011
	 */
	private void pesquisarClienteFicticio(InformarSistemaParametrosActionForm form,
			HttpServletRequest httpServletRequest) {

		String codigoClienteFicticio = null;

		codigoClienteFicticio = form.getClienteFicticioAssociarPagamentosNaoIdentificados();
		FiltroCliente filtroClienteFicticio = new FiltroCliente();

		filtroClienteFicticio.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,
				new Integer(codigoClienteFicticio)));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoClienteFicticio = this.getFachada().pesquisar(filtroClienteFicticio,
				Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoClienteFicticio != null && !colecaoClienteFicticio.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoClienteFicticio);

				httpServletRequest.setAttribute("clienteFicticioEncontrado", true);
				
				form.setClienteFicticioAssociarPagamentosNaoIdentificados(cliente.getId().toString());
				form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(cliente.getNome());
		}else{
			httpServletRequest.setAttribute("clienteFicticioEncontrado", false);
			form.setClienteFicticioAssociarPagamentosNaoIdentificados(null);
			form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados("Cliente inexistente");
		}
	}
	

	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Rafael Pinto
	 * @date 17/07/2008
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			InformarSistemaParametrosActionForm form) {

		// Unidade OrganizacionalPresidencia
		if (form.getUnidadeOrganizacionalPresidencia() != null
				&& !form.getUnidadeOrganizacionalPresidencia().equals("")
				&& form.getNomeUnidadeOrganizacionalPresidencia() != null
				&& !form.getNomeUnidadeOrganizacionalPresidencia().equals("")) {

			httpServletRequest.setAttribute(
					"unidadeOrganizacionalPresidenciaEncontrada", "true");
		}

		// Presidente
		if (form.getPresidente() != null && !form.getPresidente().equals("")
				&& form.getNomePresidente() != null
				&& !form.getNomePresidente().equals("")) {

			httpServletRequest.setAttribute("presidenteEncontrado", "true");
		}

		// Diretor Comercial
		if (form.getDiretorComercial() != null
				&& !form.getDiretorComercial().equals("")
				&& form.getNomeDiretorComercial() != null
				&& !form.getNomeDiretorComercial().equals("")) {

			httpServletRequest.setAttribute("diretorComercialEncontrado",
					"true");
		}
		// Cliente Ficticio 
		if (form.getClienteFicticioAssociarPagamentosNaoIdentificados() != null
				&& !form.getClienteFicticioAssociarPagamentosNaoIdentificados().equals("")
				&& form.getNomeClienteFicticioAssociarPagamentosNaoIdentificados() != null
				&& !form.getNomeClienteFicticioAssociarPagamentosNaoIdentificados().equals("")) {

			httpServletRequest.setAttribute("clienteFicticioEncontrado",
					"true");
		}

	}

	/**
	 * Este m�todo faz a consulta Cliente.
	 * 
	 * @author Hugo Amorim
	 * @since 22/12/2009
	 */
	private void pesquisarClientePrograma(
			InformarSistemaParametrosActionForm form,
			HttpServletRequest httpServletRequest) {

		FiltroCliente filtroCliente = new FiltroCliente();
		Collection colecaoCliente = null;

		if(form.getIdClienteResponsavelProgramaEspecial() !=  null && 
				!form.getIdClienteResponsavelProgramaEspecial().equals("")){
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					new Integer(form.getIdClienteResponsavelProgramaEspecial())));
		

		// Pesquisa de acordo com os par�metros informados no filtro
			colecaoCliente = this.getFachada().pesquisar(filtroCliente,
				Cliente.class.getName());
		
		}

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoCliente);

			httpServletRequest.setAttribute("codigoClienteEncontrado", "true");

			form.setIdClienteResponsavelProgramaEspecial(cliente.getId()
					.toString());
			form.setNomeClienteResponsavelProgramaEspecial(cliente.getNome());

		} else {
			httpServletRequest.removeAttribute("codigoClienteEncontrado");
			form.setIdClienteResponsavelProgramaEspecial("");
			form.setNomeClienteResponsavelProgramaEspecial("Cliente inexistente");

		}
	}

	/**
	 * M�todo consulta os perfis de im�vel ativos e seta essa cole��o no FORM
	 * para que seja exibida na tela.
	 * 
	 * @author Hugo Amorim
	 * @since 14/12/2009
	 */
	private void carregarColecaoPerfisImovel(
			InformarSistemaParametrosActionForm form,
			HttpServletRequest httpServletRequest) {

		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ImovelPerfil> colecaoImoveisPerfis = this.getFachada()
				.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		httpServletRequest.setAttribute("colecaoPerfisImovel",
				colecaoImoveisPerfis);
	}
}
	