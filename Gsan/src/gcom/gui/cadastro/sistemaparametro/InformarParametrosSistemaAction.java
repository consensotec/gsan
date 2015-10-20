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
package gcom.gui.cadastro.sistemaparametro;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.atendimentopublico.ResolucaoImagem;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 10/01/2007
 */
public class InformarParametrosSistemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = this.getSessao(httpServletRequest);

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		SistemaParametro sistemaParametro = (SistemaParametro) sessao
				.getAttribute("sistemaParametro");

		// 1� Aba
		this.montarSistemaParametro1Aba(form, sistemaParametro);

		// 2� aba
		this.montarSistemaParametro2Aba(form, sistemaParametro);

		// 3� aba
		this.montarSistemaParametro3Aba(form, sistemaParametro);

		// 4� Aba
		this.montarSistemaParametro4Aba(form, sistemaParametro);

		// 5� aba
		this.montarSistemaParametro5Aba(form, sistemaParametro);

		this.getFachada().informarParametrosSistema(sistemaParametro, usuario);

		montarPaginaSucesso(httpServletRequest,
				"Par�metro do Sistema informado com sucesso.",
				"Informar outro Parametro do Sistema",
				"exibirInformarParametrosSistemaAction.do?menu=sim");

		return retorno;

	}

	/**
	 * Valida o Campo
	 * 
	 * @author Rafael Pinto
	 * @date 18/07/2008
	 */
	private boolean validaCampo(String campo) {
		boolean retorno = false;

		if (campo != null && !campo.trim().equals("") && !campo.equals("-1")) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Monta os objetos da 1(Primeira) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 18/07/2008
	 */
	private void montarSistemaParametro1Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro) {

		// Nome do Estado
		if (validaCampo(form.getNomeEstado())) {
			sistemaParametro.setNomeEstado(form.getNomeEstado());
		}

		// Nome da Empresa
		if (validaCampo(form.getNomeEmpresa())) {
			sistemaParametro.setNomeEmpresa(form.getNomeEmpresa());
		}

		// Abreviatura da Empresa
		if (validaCampo(form.getAbreviaturaEmpresa())) {
			sistemaParametro.setNomeAbreviadoEmpresa(form
					.getAbreviaturaEmpresa());
		}

		// CNPJ da Empresa
		if (validaCampo(form.getCnpj())) {
			sistemaParametro.setCnpjEmpresa(form.getCnpj());
		}

		// Inscricao Estadual
		if (validaCampo(form.getInscricaoEstadual())) {
			sistemaParametro.setInscricaoEstadual(form.getInscricaoEstadual());
		}

		// Inscricao Municipal
		if (validaCampo(form.getInscricaoMunicipal())) {
			sistemaParametro
					.setInscricaoMunicipal(form.getInscricaoMunicipal());
		}

		// N�mero do Contrato
		if (validaCampo(form.getNumeroContrato())) {
			sistemaParametro.setNumeroContratoPrestacaoServico(form
					.getNumeroContrato());
		}

		// Unidade Organizacional
		if (validaCampo(form.getUnidadeOrganizacionalPresidencia())) {

			UnidadeOrganizacional unidade = new UnidadeOrganizacional();
			unidade.setId(new Integer(form
					.getUnidadeOrganizacionalPresidencia()));

			sistemaParametro.setUnidadeOrganizacionalIdPresidencia(unidade);
		}

		// Presidente
		if (validaCampo(form.getPresidente())) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getPresidente()));

			sistemaParametro.setClientePresidenteCompesa(cliente);
		}

		// Diretor Comercial
		if (validaCampo(form.getDiretorComercial())) {

			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getDiretorComercial()));

			sistemaParametro.setClienteDiretorComercialCompesa(cliente);
		}

		// Logradouro Bairro
		if (validaCampo(form.getLogradouroBairro())) {

			LogradouroBairro logradouroBairro = new LogradouroBairro();
			logradouroBairro.setId(new Integer(form.getLogradouroBairro()));

			sistemaParametro.setLogradouroBairro(logradouroBairro);
		}

		// Logradouro Cep
		if (validaCampo(form.getLogradouroCep())) {

			LogradouroCep logradouroCep = new LogradouroCep();
			logradouroCep.setId(new Integer(form.getLogradouroCep()));

			sistemaParametro.setLogradouroCep(logradouroCep);
		}

		// Numero
		if (validaCampo(form.getNumero())) {
			sistemaParametro.setNumeroImovel(form.getNumero());
		}

		// Complemento
		if (validaCampo(form.getComplemento())) {
			sistemaParametro.setComplementoEndereco(form.getComplemento());
		}

		// Numero Telefone
		if (validaCampo(form.getNumeroTelefone())) {
			String numeroTelefone = form.getNumeroTelefone().replaceAll("-", "");
			if(Util.validarCaracteresEspeciais(numeroTelefone)){
				sistemaParametro.setNumeroTelefone(form.getNumeroTelefone());
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Telefone");
			}
		}

		// Quantidade de D�gitos da Quadra
		if (validaCampo(form.getQuantidadeDigitosQuadra())) {
			if (form.getQuantidadeDigitosQuadra().equals("3")
					|| form.getQuantidadeDigitosQuadra().equals("4")) {
				sistemaParametro.setNumeroDigitosQuadra(new Integer(form
						.getQuantidadeDigitosQuadra()).shortValue());
			} else {
				throw new ActionServletException(
						"atencao.campo_com_quantidade_digitos_invalida");
			}
		}

		// Indicador n�o medido por tarifa de consumo
		if (validaCampo(form.getIndicadorQuadraFace())) {
			sistemaParametro.setIndicadorQuadraFace(new Short(form
					.getIndicadorQuadraFace()));
		}

		// Ramal
		if (validaCampo(form.getRamal())) {
			if(Util.validarCaracteresEspeciais(form.getRamal())){
				sistemaParametro.setNumeroRamal(form.getRamal());
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Ramal");
			}
		}

		// Fax
		if (validaCampo(form.getFax())) {
			if(Util.validarCaracteresEspeciais(form.getFax())){
				sistemaParametro.setNumeroFax(form.getFax());
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Fax");
			}
		}

		// Email
		if (validaCampo(form.getEmail())) {
			sistemaParametro.setDescricaoEmail(form.getEmail());
		}
		// Dom�nio do e_mail corporativo
		if (validaCampo(form.getDominioEmailCorporativo())) {
			sistemaParametro.setDominioEmailCorporativo(form
					.getDominioEmailCorporativo());
		}
		
		// Indicador Validar CPF/CNPJ
		if(validaCampo(form.getIndicadorValidaCpfCnpj())){
			sistemaParametro.setIndicadorValidaCpfCnpj(new Integer(form.getIndicadorValidaCpfCnpj()));
		}

		// Numero de Atendimento
		if (validaCampo(form.getNumeroTelefoneAtendimento())) {
			String numeroTelefoneAtendimento = form.getNumeroTelefoneAtendimento().replaceAll("-", "");
			if(Util.validarCaracteresEspeciais(numeroTelefoneAtendimento)){
				sistemaParametro.setNumero0800Empresa(form.getNumeroTelefoneAtendimento());
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Telefone de Atendimento");
			}
		}
		
		// Numero Telefone Atendimento de Servi�os de esgoto
		if (validaCampo(form.getNumeroTelefoneAtendimentoEsgoto())) {
			String numeroTelefoneAtendimentoEsgoto = form.getNumeroTelefoneAtendimentoEsgoto().replaceAll("-", "");
			if(Util.validarCaracteresEspeciais(numeroTelefoneAtendimentoEsgoto)){
				sistemaParametro.setNumeroTelefoneAtendimentoEsgoto(form.getNumeroTelefoneAtendimentoEsgoto());
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Telefone de Atendimento de Esgoto");
			}
		}

		// Titulo do Relatorio
		if (validaCampo(form.getTitulosRelatorio())) {
			sistemaParametro.setTituloPagina(form.getTitulosRelatorio());
		}

		// Caminho Imagem da Logomarca
		if (validaCampo(form.getImagemLogomarca())) {
			sistemaParametro.setImagemLogomarca(form.getImagemLogomarca());
		}

		// Caminho Imagem do Relatorio
		if (validaCampo(form.getImagemRelatorio())) {
			sistemaParametro.setImagemRelatorio(form.getImagemRelatorio());
		}

		// Caminho Imagem da Conta
		if (validaCampo(form.getImagemConta())) {
			sistemaParametro.setImagemConta(form.getImagemConta());
		}

		// Numero execucao do resumo de negativacao
		if (validaCampo(form.getNumeroExecucaoResumoNegativacao())) {
			sistemaParametro.setNumeroExecucaoResumoNegativacao(new Integer(
					form.getNumeroExecucaoResumoNegativacao()));
		}

		// Indicador para controlar os autos de infracao
		if (validaCampo(form.getIndicadorControlaAutoInfracao())) {
			sistemaParametro.setIndicadorControlaAutoInfracao(new Short(form
					.getIndicadorControlaAutoInfracao()));
		}

		// Indicador Usa Rota
		if (validaCampo(form.getIndicadorUsaRota())) {
			sistemaParametro.setIndicadorUsaRota(new Short(form
					.getIndicadorUsaRota()));
		}

		// Indicador Duplicidade Cliente
		if (validaCampo(form.getIndicadorDuplicidadeCliente())) {
			sistemaParametro.setIndicadorDuplicidadeCliente(new Short(form
					.getIndicadorDuplicidadeCliente()));
		}

		// Indicador Nome Menor Que Dez
		if (validaCampo(form.getIndicadorNomeMenorDez())) {
			sistemaParametro.setIndicadorNomeMenorDez(new Short(form
					.getIndicadorNomeMenorDez()));
		}

		// Indicador Nome Cliente Generico
		if (validaCampo(form.getIndicadorNomeClienteGenerico())) {
			sistemaParametro.setIndicadorNomeClienteGenerico(new Short(form
					.getIndicadorNomeClienteGenerico())); 
		}
		
		// Indicador Bloquear alterar o nome do cliente para CPF/CNPJ
		if (validaCampo(form.getIndicadorAlterarNomeClienteCpfCnpjValidado())) {
			sistemaParametro.setIndicadorAlterarNomeClienteCpfCnpjValidado(new Short(form
				.getIndicadorAlterarNomeClienteCpfCnpjValidado()));
		}

		if (validaCampo(form.getVersaoCelular())) {
			sistemaParametro.setVersaoCelular(form.getVersaoCelular());
		}

		// Indicador exibir mensagem
		if (validaCampo(form.getIndicadorExibirMensagem())) {
			sistemaParametro
					.setIndicadorExibeMensagemNaoReceberPagamento(new Short(
							form.getIndicadorExibirMensagem()));
		}
		
		// Cliente Responsavel Programa Especial
		if (validaCampo(form.getIdClienteResponsavelProgramaEspecial())) {
			if(Util.validarCaracteresEspeciais(form.getIdClienteResponsavelProgramaEspecial())){
				this.validarClientePrograma(form);
				
				Cliente clienteResponsavelProgramaEspecial = new Cliente();
				clienteResponsavelProgramaEspecial.setId(new Integer(form
						.getIdClienteResponsavelProgramaEspecial()));
				sistemaParametro
						.setClienteResponsavelProgramaEspecial(clienteResponsavelProgramaEspecial);
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Cliente Respons�vel Programa Especial");
			}
		}
		
		// Perfil Programa Especial
		if (validaCampo(form.getPerfilProgramaEspecial())) {
			ImovelPerfil perfilPrograma = new ImovelPerfil();
			perfilPrograma.setId(new Integer(form.getPerfilProgramaEspecial()));
			sistemaParametro.setPerfilProgramaEspecial(perfilPrograma);
		}

		// Percentual de Convergencia Repavimentacao
		if (validaCampo(form.getPercentualConvergenciaRepavimentacao())) {
			
			if(!Util.validarValorNaoNumericoComoBigDecimal(form.getPercentualConvergenciaRepavimentacao())){
				BigDecimal percentual = new BigDecimal(0);
	
				String valorAux = form.getPercentualConvergenciaRepavimentacao()
						.toString().replace(".", "");
				valorAux = valorAux.replace(",", ".");
	
				percentual = new BigDecimal(valorAux);
				sistemaParametro
						.setPercentualConvergenciaRepavimentacao(percentual);
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Percentual de Converg�ncia de Repavimenta��o");
			}
		}

		// Indicador Documento Obrigatorio
		if (validaCampo(form.getIndicadorDocumentoObrigatorio())) {
			sistemaParametro.setIndicadorDocumentoObrigatorio(new Short(form
					.getIndicadorDocumentoObrigatorio()));
		}

		// Indicador de verifica��o do CPF e CPJ no CDL
		if (validaCampo(form.getIndicadorCpfCnpj())) {
			sistemaParametro.setIndicadorConsultaDocumentoReceita(new Short(
					form.getIndicadorCpfCnpj()));
		}

		// Indicador de Exibi��o Autom�tica do Popup de Atualiza��o Cadastral
		if (validaCampo(form.getIndicadorPopupAtualizacaoCadastral())) {
			sistemaParametro.setIndicadorPopupAtualizacaoCadastral(new Short(
					form.getIndicadorPopupAtualizacaoCadastral()));
		}

		//Mensagem de Vencimento da Guia de Pagamento Referente a Entrada de Parcelamento
		sistemaParametro.setMsgVencimentoGuiaEntradaParc(form.getMsgVencimentoGuiaEntradaParc().trim());
		
		
		// Valor para Emiss�o de Extrato no Formato Ficha de Compensa��o
		if (validaCampo(form.getValorExtratoFichaComp())) {
			BigDecimal valorExtratoFichaComp = new BigDecimal(0);

			String valorAux = form.getValorExtratoFichaComp().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");
			
			if(!Util.validarValorNaoNumericoComoBigDecimal(valorAux)){
				valorExtratoFichaComp = new BigDecimal(valorAux);
				sistemaParametro.setValorExtratoFichaComp(valorExtratoFichaComp);
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Valor Extrato Ficha Compensa��o");
			}
		}else{
			sistemaParametro.setValorExtratoFichaComp(null);
		}

		if (validaCampo(form.getNumeroDiasBloqueioCelular())) {
			if(Util.validarCaracteresEspeciais(form.getNumeroDiasBloqueioCelular())){
				sistemaParametro.setNumeroDiasBloqueioCelular(new Integer(form.getNumeroDiasBloqueioCelular()));
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "N�mero de Dias Bloqueio Celular");
			}
		}

		// Valor para Emiss�o de Guia de Pagamento no Formato Ficha de Compensa��o
		if (form.getValorGuiaFichaComp() != null && !form.getValorGuiaFichaComp().equals("")) {
			BigDecimal valorGuiaFichaComp = new BigDecimal(0);

			String valorAux = form.getValorGuiaFichaComp().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");
			
			if(!Util.validarValorNaoNumericoComoBigDecimal(valorAux)){
				valorGuiaFichaComp = new BigDecimal(valorAux);
				sistemaParametro.setValorGuiaFichaComp(valorGuiaFichaComp);
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", "Valor Guia Ficha Compensa��o");
			}
		} 

		// Valor para Emiss�o de Demonstrativo de Parcelamento no Formato Ficha de Compensa��o
		if (form.getValorDemonstrativoParcelamentoFichaComp() != null
				&& !form.getValorDemonstrativoParcelamentoFichaComp().equals("")) {
			
			BigDecimal valorDemonstrativoParcelamentoFichaComp = new BigDecimal(0);

			String valorAux = form.getValorDemonstrativoParcelamentoFichaComp()
					.toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");
				
			if(!Util.validarValorNaoNumericoComoBigDecimal(valorAux)){
				valorDemonstrativoParcelamentoFichaComp = new BigDecimal(valorAux);
				sistemaParametro.setValorDemonstrativoParcelamentoFichaComp(valorDemonstrativoParcelamentoFichaComp);
			}else{
				throw new ActionServletException("atencao.campo_texto.caracter_invalido", 
					"Valor Demonstrativo de Parcelamento Ficha de Compensa��o");
			}
		} 

		// Indicador de Exibi��o Autom�tica do Popup de Atualiza��o Cadastral
		if (validaCampo(form.getIndicadorUsoNMCliReceitaFantasia())) {
			sistemaParametro.setIndicadorUsoNMCliReceitaFantasia(new Short(form
					.getIndicadorUsoNMCliReceitaFantasia()));
		}

		// Indicador Variar Hierarquia de Unidade Oragnizacional
		if (validaCampo(form.getIndicadorVariaHierarquiaUnidade())) {
			sistemaParametro.setIndicadorVariaHierarquiaUnidade(new Short(form
					.getIndicadorVariaHierarquiaUnidade()));
		}
		
		// Cliente Ficiticio Associar Pagamentos N�o Identificados
		if (validaCampo(form
				.getClienteFicticioAssociarPagamentosNaoIdentificados())) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form
					.getClienteFicticioAssociarPagamentosNaoIdentificados()));

			sistemaParametro
					.setClienteFicticioParaAssociarOsPagamentosNaoIdentificados(cliente);
		}

		// Indicador de bloqueio funcionalidade inserir/atualizar usu�rio:
		if (validaCampo(form.getIndicadorBloquearFunUsuario())) {
			sistemaParametro.setIndicadorBloquearFunUsuario(new Short(form
					.getIndicadorBloquearFunUsuario()));
		}
		
		//Quantidade de dias para guardar ocorr�ncias de paralisa��o
		if (validaCampo(form.getQtdDiasGuardarOcorrenciasParalisacao())) {
			sistemaParametro.setQtdDiasGuardarOcorrenciasParalisacao(new Integer(form
					.getQtdDiasGuardarOcorrenciasParalisacao()));
		}else{
			sistemaParametro.setQtdDiasGuardarOcorrenciasParalisacao(new Integer(0));
		}
		
		//Documentos Obrigat�rios
		//Atendimento Operacional
		if (validaCampo(form.getIndicadorDocObrAtendimentoOperacional())) {
			sistemaParametro.setIndicadorDocObrAtendimentoOperacional(new Short(form
					.getIndicadorDocObrAtendimentoOperacional()));
		}
		
		//Atendimento Comercial
		if (validaCampo(form.getIndicadorDocObrAtendimentoComercial())) {
			sistemaParametro.setIndicadorDocObrAtendimentoComercial(new Short(form
					.getIndicadorDocObrAtendimentoComercial()));
		}
		
		//Informa��o
		if (validaCampo(form.getIndicadorDocObrInformacao())) {
			sistemaParametro.setIndicadorDocObrInformacao(new Short(form
					.getIndicadorDocObrInformacao()));
		}
		
		//Reitera��o
		if (validaCampo(form.getIndicadorDocObrReiteracao())) {
			sistemaParametro.setIndicadorDocObrReiteracao(new Short(form
					.getIndicadorDocObrReiteracao()));
		}
		
		//Indicador Cliente Atual Segunda Via de Conta
		if(validaCampo(form.getIndicadorNomeClienteAtualConta())){
			sistemaParametro.setIndicadorNomeClienteAtualConta(new Short(form
					.getIndicadorNomeClienteAtualConta()));
		}else{
			sistemaParametro.setIndicadorNomeClienteAtualConta(ConstantesSistema.NAO);
		}
		
		if(form.getTamanhoMaximoAnexoRA() != null){
			sistemaParametro.setTamanhoMaximoAnexoRA(Integer.parseInt(form.getTamanhoMaximoAnexoRA()));
		}
		
		if(form.getResolucaoImagem() != null){
			
			Filtro filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples("id",form.getResolucaoImagem()));
			ResolucaoImagem imagem = (ResolucaoImagem)(Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtro, ResolucaoImagem.class.getName())));
			
			sistemaParametro.setResolucaoImagem(imagem);
			sistemaParametro.setImagemResolucaoLargura(imagem.getLarguraImagem());
			sistemaParametro.setImagemResolucaoAltura(imagem.getAlturaImagem());
		}
		
		/**
		 * #10981 - ROTINA PARA ENVIAR E-MAIL E SMS PARA DEVEDORES.
		 * @author Diogo Luiz
		 * @date 02/07/2014
		 * 
		 * RM 11444 - [UC0060] - Informar Parametros Sistema e [UC0061] - Consutar Par�metros Sistema
		 */
		if(validaCampo(form.getTamanhoMaximoMensagemSms())){
			sistemaParametro.setTamanhoMaximoMensagemSms(new Integer(form.getTamanhoMaximoMensagemSms()));
		}else{
			sistemaParametro.setTamanhoMaximoMensagemSms(new Integer(ConstantesSistema.ZERO));
		}
	}

	/**
	 * Monta os objetos da 2(Segunda) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 21/07/2008
	 */
	private void montarSistemaParametro2Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro) {

		// M�s/Ano Referencia
		if (validaCampo(form.getMesAnoReferencia())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoReferencia());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			Integer anoMesReferenciaFaturamento = new Integer(ano + mes);

			sistemaParametro.setAnoMesFaturamento(anoMesReferenciaFaturamento);
		}

		if (validaCampo(form.getQtdeContasRetificadas())) {
			sistemaParametro.setQtdMaxContasRetificadas(Integer.parseInt(form
					.getQtdeContasRetificadas()));
		} else {
			throw new ActionServletException(
					"atencao.campo_com_quantidade_maxima_contas_retificada_invalido");
		}

		// Menor Consumo para ser Grande Usuario
		if (validaCampo(form.getMenorConsumo())) {
			sistemaParametro.setMenorConsumoGrandeUsuario(new Integer(form
					.getMenorConsumo()));
		}

		// Menor Valor para Emissao de Contas
		if (validaCampo(form.getMenorValor())) {

			BigDecimal valorMinimoEmissaoConta = new BigDecimal(0);

			String valorAux = form.getMenorValor().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorMinimoEmissaoConta = new BigDecimal(valorAux);
			sistemaParametro
					.setValorMinimoEmissaoConta(valorMinimoEmissaoConta);
		}

		// Valor para Emiss�o de Conta no Formato Ficha de Compensa��o
		if (validaCampo(form.getValorContaFichaComp())) {

			BigDecimal valorContaFichaComp = new BigDecimal(0);

			String valorAux = form.getValorContaFichaComp().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorContaFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorContaFichaComp(valorContaFichaComp);
		}

		// Qtde de Economias para Ser Grande Usuario
		if (validaCampo(form.getQtdeEconomias())) {
			sistemaParametro.setMenorEconomiasGrandeUsuario(new Short(form
					.getQtdeEconomias()));
		}

		// Meses para Calculo de Media de Consumo
		if (validaCampo(form.getMesesCalculoMedio())) {
			sistemaParametro.setMesesMediaConsumo(new Short(form
					.getMesesCalculoMedio()));
		}

		// Dias Minimo para Calcular Vencimento
		if (validaCampo(form.getDiasMinimoVencimento())) {
			sistemaParametro.setNumeroMinimoDiasEmissaoVencimento(new Short(
					form.getDiasMinimoVencimento()));
		}

		// Dias Minimo para Caluar Vencimento se entrega para os correios
		if (validaCampo(form.getDiasMinimoVencimentoCorreio())) {
			sistemaParametro.setNumeroDiasAdicionaisCorreios(new Short(form
					.getDiasMinimoVencimentoCorreio()));
		}

		// N�mero m�ximo de meses para retroagir o calculo da media
		if (validaCampo(form.getNumeroMesesMaximoCalculoMedia())) {
			sistemaParametro.setNumeroMesesMaximoCalculoMedia(new Short(form
					.getNumeroMesesMaximoCalculoMedia()));
		}

		if (validaCampo(form.getDiasVencimentoAlternativo())) {
			Matcher validarCaracteresDiferenteVirgulaNumero = Pattern.compile(
					"[^;0-9]").matcher(form.getDiasVencimentoAlternativo());

			if (validarCaracteresDiferenteVirgulaNumero.find()) {
				throw new ActionServletException(
						"atencao.informar_sistema_parametro.dia_vencimento_alternativo_separado_virgula_sem_espaco_branco");
			}

			Matcher validarMaisDeUmaVirgulaJunta = Pattern.compile(";;+")
					.matcher(form.getDiasVencimentoAlternativo());

			if (validarMaisDeUmaVirgulaJunta.find()) {
				throw new ActionServletException(
						"atencao.informar_sistema_parametro.dia_vencimento_alternativo_separado_apenas_uma_virgula");
			}

			String[] diasString = form.getDiasVencimentoAlternativo()
					.split(";");

			ArrayList<Integer> diasJaValidados = new ArrayList<Integer>();

			if (!Util.isVazioOrNulo(diasString)) {
				for (String diaAtualString : diasString) {
					if (!validaCampo(diaAtualString)) {
						throw new ActionServletException(
								"atencao.gsan.campo_formato_invalido",
								"Dias para vencimento alternativo");
					}

					Integer diaAtual = new Integer(diaAtualString.trim());

					if (diaAtual < 1 || diaAtual > 31) {
						throw new ActionServletException(
								"atencao.informar_sistema_parametro.dia_vencimento_alternativo_entre_um_trinta_um");
					}

					if (diasJaValidados.contains(diaAtual)) {
						throw new ActionServletException(
								"atencao.informar_sistema_parametro.dia_vencimento_alternativo_duplicado");
					}

					diasJaValidados.add(diaAtual);

					for (Integer diaValidado : diasJaValidados) {
						if (diaValidado > diaAtual) {
							throw new ActionServletException(
									"atencao.informar_sistema_parametro.dia_vencimento_alternativo_desordenado");
						}
					}
				}
			}

			sistemaParametro.setDiasVencimentoAlternativo(form
					.getDiasVencimentoAlternativo());
		}

		// Numero de meses para validade de conta
		if (validaCampo(form.getNumeroMesesValidadeConta())) {
			sistemaParametro.setNumeroMesesValidadeConta(new Short(form
					.getNumeroMesesValidadeConta()));
		}

		// Numero de meses para alteracao do vencimento para outro
		if (validaCampo(form.getNumeroMesesAlteracaoVencimento())) {
			sistemaParametro.setNumeroMesesMinimoAlteracaoVencimento(new Short(
					form.getNumeroMesesAlteracaoVencimento()));
		}

		// Indicador Alteracao do Vencimento mais de uma vez
		if (validaCampo(form.getIndicadorLimiteAlteracaoVencimento())) {
			sistemaParametro.setIndicadorLimiteAlteracaoVencimento(new Short(
					form.getIndicadorLimiteAlteracaoVencimento()));
		}

		// Indicador Calculo feito pelo sistema
		if (validaCampo(form.getIndicadorCalculaVencimento())) {
			sistemaParametro.setIndicadorCalculaVencimento(new Short(form
					.getIndicadorCalculaVencimento()));
		}

		// Indicador tipo de tarifa de consumo
		if (validaCampo(form.getIndicadorTarifaCategoria())) {
			sistemaParametro.setIndicadorTarifaCategoria(new Short(form
					.getIndicadorTarifaCategoria()));
		}

		// Indicador Para Retificar com um valor Menor
		if (validaCampo(form.getIndicadorRetificacaoValorMenor())) {
			sistemaParametro.setIndicadorRetificacaoValorMenor(new Short(form
					.getIndicadorRetificacaoValorMenor()));
		}

		// Indicador Transfer�ncia com d�bito
		if (validaCampo(form.getIndicadorTransferenciaComDebito())) {
			sistemaParametro.setIndicadorTransferenciaComDebito(new Short(form
					.getIndicadorTransferenciaComDebito()));
		}

		// Indicador n�o medido por tarifa de consumo
		if (validaCampo(form.getIndicadorNaoMedidoTarifa())) {
			sistemaParametro.setIndicadorNaoMedidoTarifa(new Short(form
					.getIndicadorNaoMedidoTarifa()));
		}

		// Indicador de Atualiza��o Tarif�ria
		if (validaCampo(form.getIndicadorAtualizacaoTarifaria())) {
			sistemaParametro.setIndicadorAtualizacaoTarifaria(new Short(form
					.getIndicadorAtualizacaoTarifaria()));
		}

		// M�s/Ano Atualiza��o Tarif�ria
		if (validaCampo(form.getMesAnoAtualizacaoTarifaria())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoAtualizacaoTarifaria());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			Integer anoMes = new Integer(ano + mes);

			sistemaParametro.setAnoMesAtualizacaoTarifaria(anoMes);
		}

		// Indicador de Faturamento Antecipado
		if (validaCampo(form.getIndicadorFaturamentoAntecipado())) {
			sistemaParametro.setIndicadorFaturamentoAntecipado(new Short(form
					.getIndicadorFaturamentoAntecipado()));
		}

		// Numero de dias de Varia��o de Consumo
		if (validaCampo(form.getNumeroDiasVariacaoConsumo())) {
			sistemaParametro.setNumeroDiasVariacaoConsumo(new Short(form
					.getNumeroDiasVariacaoConsumo()));
		}

		// Salario Minimo
		if (validaCampo(form.getSalarioMinimo())) {

			BigDecimal valorValorSalarioMinimo = new BigDecimal(0);

			String valorAux = form.getSalarioMinimo().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");
			valorValorSalarioMinimo = new BigDecimal(valorAux);

			sistemaParametro.setValorSalarioMinimo(valorValorSalarioMinimo);
		}

		// Area Maxima do Imovel tarifa social
		if (validaCampo(form.getAreaMaxima())) {
			sistemaParametro.setAreaMaximaTarifaSocial(new Integer(form
					.getAreaMaxima()));
		}

		// Consumo de Energia Maxima
		if (validaCampo(form.getConsumoMaximo())) {
			sistemaParametro.setConsumoEnergiaMaximoTarifaSocial(new Integer(
					form.getConsumoMaximo()));
		}

		// Consumo de Energia Maxima
		if (validaCampo(form.getNumeroMesesCalculoCorrecao())) {
			sistemaParametro.setNumeroMesesCalculoCorrecao(new Short(form
					.getNumeroMesesCalculoCorrecao()));
		}

		// Numero de vezes de suspensao de leitura
		if (validaCampo(form.getNumeroVezesSuspendeLeitura())) {
			sistemaParametro.setNumeroVezesSuspendeLeitura(new Integer(form
					.getNumeroVezesSuspendeLeitura()));
		}

		// Caso n�o informado recebe o valor nulo
		if (!validaCampo(form.getNumeroVezesSuspendeLeitura())) {
			sistemaParametro.setNumeroVezesSuspendeLeitura(null);
		}

		// Numero de meses da leitura suspensa
		if (validaCampo(form.getNumeroMesesLeituraSuspensa())) {
			sistemaParametro.setNumeroMesesLeituraSuspensa(new Integer(form
					.getNumeroMesesLeituraSuspensa()));
		}

		// Caso n�o informado recebe o valor nulo
		if (!validaCampo(form.getNumeroMesesLeituraSuspensa())) {
			sistemaParametro.setNumeroMesesLeituraSuspensa(null);
		}

		// Numero de meses de reinicio situacao especial do faturamento
		if (validaCampo(form.getNumeroMesesReinicioSitEspFatu())) {
			sistemaParametro
					.setNumeroMesesReinicioSitEspFaturamento(new Integer(form
							.getNumeroMesesReinicioSitEspFatu()));
		}

		// Caso n�o informado recebe o valor nulo
		if (!validaCampo(form.getNumeroMesesReinicioSitEspFatu())) {
			sistemaParametro.setNumeroMesesReinicioSitEspFaturamento(null);
		}

		// Numero de dias de prazo para entrada de recurso do auto de infracao
		if (validaCampo(form.getNnDiasPrazoRecursoAutoInfracao())) {
			sistemaParametro.setNumeroDiasPrazoRecursoAutoInfracao(new Integer(
					form.getNnDiasPrazoRecursoAutoInfracao()));
		}
		// Percentual de Bonus Social
		if (validaCampo(form.getPercentualBonusSocial())) {
			BigDecimal percentualBonusSocial = new BigDecimal(0);

			String valorAux = form.getPercentualBonusSocial().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualBonusSocial = new BigDecimal(valorAux);
			sistemaParametro.setPercentualBonusSocial(percentualBonusSocial);
		}
		// Indicador de bloqueio de recalculo e reemiss�o de conta na impress�o
		// simult�nea
		if (validaCampo(form.getIndicadorBloqueioContaMobile())) {
			sistemaParametro.setIndicadorBloqueioContaMobile(new Short(form
					.getIndicadorBloqueioContaMobile()));
		}
		// N�mero de meses para retificar uma conta
		if (form.getNumeroMesesRetificarConta() != null) {
			if (form.getNumeroMesesRetificarConta().toString().equals("")) {
				sistemaParametro.setNumeroMesesRetificarConta(null);
			} else {
				sistemaParametro.setNumeroMesesRetificarConta(new Integer(form
						.getNumeroMesesRetificarConta()));
			}

		}

		// Est� na Norma de Retifica��o da Conta
		if (validaCampo(form.getIndicadorNormaRetificacao())) {
			sistemaParametro.setIndicadorNormaRetificacao(new Short(form
					.getIndicadorNormaRetificacao()));
		}
		
		
		
		
		//Utiliza Tarifa Simulacao
		if (validaCampo(form.getIndicadorUtilizaTarifaSimulacao())) {
			sistemaParametro.setIndicadorUtilizaTarifaSimulacao(new Short(form
					.getIndicadorUtilizaTarifaSimulacao()));
		}

		// Mensagem Pedido Conta BRAILE
		if (validaCampo(form.getMensagemContaBraile())) {

			sistemaParametro.setMensagemContaBraile(form
					.getMensagemContaBraile().trim());
		} 

		if (validaCampo(form.getCodigoTipoCalculoNaoMedido())) {
			sistemaParametro.setCodigoTipoCalculoNaoMedido(new Integer(form
					.getCodigoTipoCalculoNaoMedido()));
		}
		
		// Numero maximo de meses para inserir conta antecipada
		if (validaCampo(form.getNumeroMaximoMesesInserirContaAntecipada())) {
			sistemaParametro.setNumeroMaximoMesesInserirContaAntecipada(new Integer(
			       form.getNumeroMaximoMesesInserirContaAntecipada()));
		}
		
		//N�mero m�ximo de dias entre data de liga��o e leitura para tratar com nova liga��o
		if(validaCampo(form.getNumeroMaximoDiasNovaLigacao())){
		sistemaParametro.setNumeroMaximoDiasNovaLigacao(new Integer(form
				.getNumeroMaximoDiasNovaLigacao()));
		}
		
		

	}

	/**
	 * Monta os objetos da 3(Terceira) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 21/07/2008
	 */
	private void montarSistemaParametro3Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro) {

		// M�s e Ano de Referencia
		if (validaCampo(form.getMesAnoReferenciaArrecadacao())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoReferenciaArrecadacao());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferenciaArrecadacao().substring(0, 2);
			String ano = form.getMesAnoReferenciaArrecadacao().substring(3, 7);

			Integer anoMesReferenciaArrecadacao = new Integer(ano + mes);

			sistemaParametro.setAnoMesArrecadacao(anoMesReferenciaArrecadacao);
		}

		// C�digo da Empresa para FEBRABAN
		if (validaCampo(form.getCodigoEmpresaFebraban())) {
			if(!Util.validarStringNumerica(form.getCodigoEmpresaFebraban().toString())){
				throw new ActionServletException("atencao.campo.invalido", null, 
					"C�digo da Empresa para FEBRABAN");
			}
			sistemaParametro.setCodigoEmpresaFebraban(new Short(form
					.getCodigoEmpresaFebraban()));
		}

		// N�mero do Layout
		if (validaCampo(form.getNumeroLayOut())) {
			if(!Util.validarStringNumerica(form.getNumeroLayOut().toString())){
				throw new ActionServletException("atencao.campo.invalido", null, 
					"N�mero do Layout da FEBRABAN");
			}
			sistemaParametro.setNumeroLayoutFebraban(new Short(form
					.getNumeroLayOut()));
		}

		// Identificador de Conta Bancaria
		if (validaCampo(form.getIndentificadorContaDevolucao())) {

			ContaBancaria contaBancaria = new ContaBancaria();

			contaBancaria.setId(new Integer(form
					.getIndentificadorContaDevolucao()));
			sistemaParametro.setContaBancaria(contaBancaria);
		}

		// Percentual de Entrada Minima
		if (validaCampo(form.getPercentualEntradaMinima())) {		
			
			
			if(form.getPercentualEntradaMinima().matches(".*[a-zA-Z].*")){
				throw new ActionServletException("atencao.campo.invalido", null, 
						"Percentual de Entrada M�nima para Financiamento");
			}

			BigDecimal percentualEntradaMinima = new BigDecimal(0);

			String valorAux = form.getPercentualEntradaMinima().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualEntradaMinima = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualFinanciamentoEntradaMinima(percentualEntradaMinima);			
		}

		// Maximo de Parcelas
		if (validaCampo(form.getMaximoParcelas())) {
			
			if(!Util.validarStringNumerica(form.getMaximoParcelas().toString())){
				throw new ActionServletException("atencao.campo.invalido", null, 
						"M�ximo de Parcelas para um Financiamento");			
			}
			
			sistemaParametro.setNumeroMaximoParcelasFinanciamento(new Short(
					form.getMaximoParcelas()));
		}

		// Percentual Maximo
		if (validaCampo(form.getPercentualMaximoAbatimento())) {

			if(form.getPercentualMaximoAbatimento().matches(".*[a-zA-Z].*")){
				throw new ActionServletException("atencao.campo.invalido", null, 
						"Percentual M�ximo para Abatimento de um Servi�o");
			}

			BigDecimal percentualMaximoAbatimento = new BigDecimal(0);

			String valorAux = form.getPercentualMaximoAbatimento().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualMaximoAbatimento = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualMaximoAbatimento(percentualMaximoAbatimento);
		}

		// Percentual de Taxa
		if (validaCampo(form.getPercentualTaxaFinanciamento())) {
			
			if(form.getPercentualMaximoAbatimento().matches(".*[a-zA-Z].*")){
				throw new ActionServletException("atencao.campo.invalido", null, 
						"Percentual de Taxa de Juros para Financiamento");
			}

			BigDecimal percentualTaxaFinanciamento = new BigDecimal(0);

			String valorAux = form.getPercentualTaxaFinanciamento().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualTaxaFinanciamento = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualTaxaJurosFinanciamento(percentualTaxaFinanciamento);
		}

		// Numero Maximo de Parcelas
		if (validaCampo(form.getNumeroMaximoParcelaCredito())) {
			
			if(!Util.validarStringNumerica(form.getNumeroMaximoParcelaCredito().toString())){
				throw new ActionServletException("atencao.campo.invalido", null, 
						"N�mero M�ximo para Parcela de Cr�dito");
			}
			
			sistemaParametro.setNumeroMaximoParcelaCredito(new Short(form
					.getNumeroMaximoParcelaCredito()));
		}

		// Percentual da M�dia do �ndice
		if (validaCampo(form.getPercentualCalculoIndice())) {
			
			if(form.getPercentualCalculoIndice().matches(".*[a-zA-Z].*")){
				throw new ActionServletException("atencao.campo.invalido", null, 
						"Percentual da M�dia do �ndice para C�lculo do Parcelamento");
			}				

			BigDecimal percentualCalculoIndice = new BigDecimal(0);

			String valorAux = form.getPercentualCalculoIndice().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualCalculoIndice = new BigDecimal(valorAux);
			sistemaParametro.setPercentualMediaIndice(percentualCalculoIndice);
		}
		// N�mero do m�dulo do d�gito verificador
		if (validaCampo(form.getNumeroModuloDigitoVerificador())) {
			
			if(!Util.validarStringNumerica(form.getNumeroModuloDigitoVerificador())){
				throw new ActionServletException("atencao.campo.invalido", null, 
					"N�mero do m�dulo verificador");
			}

			sistemaParametro.setNumeroModuloDigitoVerificador(new Short(form
					.getNumeroModuloDigitoVerificador()));

			if (sistemaParametro.getNumeroModuloDigitoVerificador().compareTo(
					ConstantesSistema.MODULO_VERIFICADOR_10) == 0
					&& sistemaParametro.getNumeroModuloDigitoVerificador()
							.compareTo(ConstantesSistema.MODULO_VERIFICADOR_11) == 0) {
				throw new ActionServletException(
						"atencao.digito_verificador_invalido");
			}

		}
		// N�mero meses para pesquisa de imoveis com ramais suprimidos
		if (validaCampo(form.getNumeroMesesPesquisaImoveisRamaisSuprimidos())) {

			if(!Util.validarStringNumerica(form.getNumeroMesesPesquisaImoveisRamaisSuprimidos().toString())){
				throw new ActionServletException("atencao.campo.invalido", null, 
					"N�mero meses para pesquisa de im�veis com ramais suprimidos");
			}
			
			sistemaParametro
					.setNumeroMesesPesquisaImoveisRamaisSuprimidos(new Integer(
							form.getNumeroMesesPesquisaImoveisRamaisSuprimidos()));

		}
		// N�mero anos para Gera��o da declara��o quita��o de debitos anual
		if (validaCampo(form.getNumeroAnoQuitacao())) {
			
			if(!Util.validarStringNumerica(form.getNumeroAnoQuitacao().toString())){
				throw new ActionServletException("atencao.campo.invalido", null, 
					"N�mero de anos para gera��o da declara��o de quita��o de d�bitos");
			}
			
			sistemaParametro.setNumeroAnoQuitacao(new Integer(form
					.getNumeroAnoQuitacao()));
		}
		// Indicador de verifica��o de contas em cobran�a judicial,
		// para gera��o da declara��o quita��o de debitos anual
		if (validaCampo(form.getIndicadorCobrancaJudical())) {
			sistemaParametro.setIndicadorCobrancaJudical(new Short(form
					.getIndicadorCobrancaJudical()));
		}
		// Indicador de verifica��o de contas parceladas,
		// para gera��o da declara��o quita��o de debitos anual
		if (validaCampo(form.getIndicadorContaParcelada())) {
			sistemaParametro.setIndicadorContaParcelada(new Short(form
					.getIndicadorContaParcelada()));
		}
		// Numero meses para calculo de meses
		// para gera��o da declara��o quita��o de debitos anual
		if (validaCampo(form.getNumeroMesesAnterioresParaDeclaracaoQuitacao())) {
			
			if(!Util.validarStringNumerica(
				form.getNumeroMesesAnterioresParaDeclaracaoQuitacao().toString())){
				throw new ActionServletException("atencao.campo.invalido", null, 
					"Quantidade de meses anteriores gera��o declara��o de quita��o de d�bitos");
			}
			
			sistemaParametro
					.setNumeroMesesAnterioresParaDeclaracaoQuitacao(new Integer(
							form.getNumeroMesesAnterioresParaDeclaracaoQuitacao()));
		}
		// Indicador de verifica��o do valor do movimento arrecadador
		if (validaCampo(form.getIndicadorValorMovimentoArrecadador())) {
			sistemaParametro.setIndicadorValorMovimentoArrecadador(Integer
					.parseInt(form.getIndicadorValorMovimentoArrecadador()));
		}
		// Codigo de exibi��o do Relat�rio de Dados Di�rios da Arrecada��o por
		// Ger�ncia
		if (validaCampo(form.getCdDadosDiarios())) {
			sistemaParametro.setCdDadosDiarios(new Integer(form
					.getCdDadosDiarios()));
		}
		// Numero Convenio ficha de compensa��o
		if (validaCampo(form.getNumeroConvenioFichaCompensacao())) {
			try {
				sistemaParametro.setNumeroConvenioFichaCompensacao(new Integer(
						form.getNumeroConvenioFichaCompensacao()));
			} catch (java.lang.NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.numero_convenio_ficha_compensacao_invalido");
			}
		} 
		
		
		/*
		 * Adicionado por: Diogo Luiz  
		 * Data: 04/07/2012   
		 * [UC0060] Informar Par�metros do Sistema
		 */
		if (validaCampo(form.getValorMaximoBaixado())) {
			
			BigDecimal valorMaximoBaixado = new BigDecimal(0);

			String valorAux = form.getValorMaximoBaixado().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorMaximoBaixado = new BigDecimal(valorAux);
			sistemaParametro
					.setValorMaximoBaixado(valorMaximoBaixado);
			
		}
		
		//[UC0060] Informar Par�metros do Sistema
		if (validaCampo(form.getDiferencaMaximaBaixado())) {
			
//			if(!Util.validarStringNumerica(form.getDiferencaMaximaBaixado().toString())){
//				throw new ActionServletException("atencao.required", null,
//						"Diferen�a M�xima para Baixa");
//			}

			BigDecimal diferencaMaximaBaixado = new BigDecimal(0);

			String valorAux = form.getDiferencaMaximaBaixado().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			diferencaMaximaBaixado = new BigDecimal(valorAux);
			sistemaParametro
					.setDiferencaMaximoBaixado(diferencaMaximaBaixado);
			
		}
		
		
		/**
		 * RM8594 - [UC0060] Informar Sistema Parametro / [UC0061] Consultar Sistema Parametro
		 * Author: Diogo Luiz
		 * Data: 07/11/2013 
		 */
		if(validaCampo(form.getIndicadorCarteira17())){
			
			String indicadorCarteira17 = form.getIndicadorCarteira17();
			
			sistemaParametro.setIndicadorCarteira17(new Short(indicadorCarteira17));
			
		}
		
		
	}

	/**
	 * Monta os objetos da 4(Quarta) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 21/07/2008
	 */
	private void montarSistemaParametro4Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro) {

		// Codigo da Menor Capacidade
		if (validaCampo(form.getCodigoMenorCapacidade())) {

			HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
			hidrometroCapacidade.setId(new Integer(form
					.getCodigoMenorCapacidade()));

			sistemaParametro.setHidrometroCapacidade(hidrometroCapacidade);
		}

		// Indicador de Gera��o de Faixa Falsa
		if (validaCampo(form.getIndicadorGeracaoFaixaFalsa())) {
			sistemaParametro.setIndicadorFaixaFalsa(new Short(form
					.getIndicadorGeracaoFaixaFalsa()));
		}

		// Indicador do Percentual para Gera��o
		if (validaCampo(form.getIndicadorPercentualGeracaoFaixaFalsa())) {
			sistemaParametro.setIndicadorUsoFaixaFalsa(new Short(form
					.getIndicadorPercentualGeracaoFaixaFalsa()));
		}

		// Percentual de Gera��o de Faixa
		if (validaCampo(form.getPercentualGeracaoFaixaFalsa())) {

			BigDecimal percentualGeracaoFaixaFalsa = new BigDecimal(0);

			String valorAux = form.getPercentualGeracaoFaixaFalsa().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualGeracaoFaixaFalsa = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualFaixaFalsa(percentualGeracaoFaixaFalsa);
		}

		// Indicador de Gera��o de Fiscaliza��o
		if (validaCampo(form.getIndicadorGeracaoFiscalizacaoLeitura())) {
			sistemaParametro
					.setIndicadorPercentualFiscalizacaoLeitura(new Short(form
							.getIndicadorPercentualGeracaoFiscalizacaoLeitura()));
		}

		// Indicador do Percentual Gera��o
		if (validaCampo(form.getIndicadorPercentualGeracaoFiscalizacaoLeitura())) {
			sistemaParametro.setIndicadorUsoFiscalizadorLeitura(new Short(form
					.getIndicadorGeracaoFiscalizacaoLeitura()));
		}

		// Percentual de Tolerancia
		if (validaCampo(form.getPercentualToleranciaRateioConsumo())) {

			BigDecimal percentualToleranciaRateioConsumo = new BigDecimal(0);

			String valorAux = form.getPercentualToleranciaRateioConsumo()
					.toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualToleranciaRateioConsumo = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualToleranciaRateio(percentualToleranciaRateioConsumo);
		}

		// Percentual de Gera��o de Fiscaliza��o
		if (validaCampo(form.getPercentualGeracaoFiscalizacaoLeitura())) {

			BigDecimal percentualGeracaoFiscalizacaoLeitura = new BigDecimal(0);

			String valorAux = form.getPercentualGeracaoFiscalizacaoLeitura()
					.toString().replace(".", "");

			valorAux = valorAux.replace(",", ".");

			percentualGeracaoFiscalizacaoLeitura = new BigDecimal(valorAux);

			sistemaParametro
					.setPercentualFiscalizacaoLeitura(percentualGeracaoFiscalizacaoLeitura);

		}

		// Incremento M�ximo de Consumo
		if (validaCampo(form.getIncrementoMaximoConsumo())) {
			sistemaParametro.setIncrementoMaximoConsumoRateio(new Integer(form
					.getIncrementoMaximoConsumo()));
		}

		// Decremento M�ximo de Consumo
		if (validaCampo(form.getDecrementoMaximoConsumo())) {
			sistemaParametro.setDecrementoMaximoConsumoRateio(new Integer(form
					.getDecrementoMaximoConsumo()));
		}

		// Numero de Dias entre o Vencimento
		if (validaCampo(form.getDiasVencimentoCobranca())) {
			sistemaParametro.setNumeroDiasVencimentoCobranca(new Short(form
					.getDiasVencimentoCobranca()));
		}

		// N�mero M�ximo de Meses de San��es
		if (validaCampo(form.getNumeroMaximoMesesSancoes())) {
			sistemaParametro.setNumeroMaximoMesesSancoes(new Short(form
					.getNumeroMaximoMesesSancoes()));
		}

		// Valor da Segunda Via
		if (validaCampo(form.getValorSegundaVia())) {

			String valorAux = form.getValorSegundaVia().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			sistemaParametro.setValorSegundaVia(new BigDecimal(valorAux));
		}

		// Indicador de Cobran�a da Taxa de Extrato
		if (validaCampo(form.getIndicadorCobrarTaxaExtrato())) {
			sistemaParametro.setIndicadorCobrarTaxaExtrato(new Short(form
					.getIndicadorCobrarTaxaExtrato()));
		}

		// C�digo da Periodicidade da Negativacao
		if (validaCampo(form.getCodigoPeriodicidadeNegativacao())) {
			sistemaParametro.setCodigoPeriodicidadeNegativacao(new Short(form
					.getCodigoPeriodicidadeNegativacao()));
		}

		// N�mero de Dias para Calculo de Adicionais de Impontualidade
		if (validaCampo(form.getNumeroDiasCalculoAcrescimos())) {
			sistemaParametro.setNumeroDiasCalculoAcrescimos(new Short(form
					.getNumeroDiasCalculoAcrescimos()));
		}

		// N�mero de Dias de Validade do Extrato de D�bito
		if (validaCampo(form.getNumeroDiasValidadeExtrato())) {
			sistemaParametro.setNumeroDiasValidadeExtrato(new Short(form
					.getNumeroDiasValidadeExtrato()));
		}

		// N�mero de Dias de Validade do Extrato de D�bito para quem possui
		// Permiss�o Especial
		if (validaCampo(form.getNumeroDiasValidadeExtratoPermissaoEspecial())) {
			sistemaParametro
					.setNumeroDiasValidadeExtratoPermissaoEspecial(new Short(
							form.getNumeroDiasValidadeExtratoPermissaoEspecial()));
		} 

		// Indicador Parcelamento Confirmado
		if (validaCampo(form.getIndicadorParcelamentoConfirmado())) {
			sistemaParametro.setIndicadorParcelamentoConfirmado(new Short(form
					.getIndicadorParcelamentoConfirmado()));
		}

		// N�mero de dias �teis para que a OS de Fiscaliza��o seja encerrada por
		// Decurso de Prazo
		if (validaCampo(form.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo())) {
			sistemaParametro
					.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(new Short(
							form.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo()));
		}

		// Indicador Calculo Juros Parcelamento Tabela Price
		if (validaCampo(form.getIndicadorTabelaPrice())) {
			sistemaParametro.setIndicadorTabelaPrice(new Short(form
					.getIndicadorTabelaPrice()));
		}

		// Indicador Divida ativa
		if (validaCampo(form.getIndicadorControleDividaAtiva())) {
			sistemaParametro.setIndicadorDividaAtiva(new Short(form
					.getIndicadorControleDividaAtiva()));
		}

		// N�mero de Dias para o Vencimento da Guia de pagamento de Entrada de
		// Parcelamento
		if (validaCampo(form.getNumeroDiasVencimentoEntradaParcelamento())) {
			sistemaParametro
					.setNumeroDiasVencimentoEntradaParcelamento(new Short(form
							.getNumeroDiasVencimentoEntradaParcelamento()));
		}

		// Resolu��o de Diretoria para C�lculo de Descontos para pagamento �
		// vista
		if (validaCampo(form.getIdResolucaoDiretoria())) {
			ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();
			resolucaoDiretoria
					.setId(new Integer(form.getIdResolucaoDiretoria()));
			sistemaParametro.setResolucaoDiretoria(resolucaoDiretoria);
		}

		// Retirar Contas Vinculadas a Contrato de Parcelamento da Composi��o do
		// D�bito do Im�vel ou do Cliente
		if (validaCampo(form.getIndicadorBloqueioContasContratoParcelDebitos())) {
			sistemaParametro
					.setIndicadorBloqueioContasContratoParcelDebitos(new Short(
							form.getIndicadorBloqueioContasContratoParcelDebitos()));
		}

		// Retirar Guias Vinculadas a Contrato de Parcelamento da Composi��o do
		// D�bito do Im�vel ou do Cliente
		if (validaCampo(form
				.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito())) {
			sistemaParametro
					.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(new Short(
							form.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito()));
		}

		// Bloquear Contas Vinculadas a Contrato de Parcelamento na tela de
		// Manter Conta
		if (validaCampo(form
				.getIndicadorBloqueioContasContratoParcelManterConta())) {
			sistemaParametro
					.setIndicadorBloqueioContasContratoParcelManterConta(new Short(
							form.getIndicadorBloqueioContasContratoParcelManterConta()));
		}
		/*
		 * Adicionado por: Raimundo Martins Data: 19/07/2011 Indicador de
		 * Bloqueio de D�bitos a Cobrar Vinculados ao Contrato de Parcelamento
		 * na Composi��o do D�bito do Im�vel ou Cliente � Obter D�bito.
		 */
		if (validaCampo(form
				.getIndicadorBloqueioDebitoACobrarContratoParcelDebito())) {
			sistemaParametro
					.setIndicadorBloqueioDebitoACobrarContratoParcelDebito(new Short(
							form.getIndicadorBloqueioDebitoACobrarContratoParcelDebito()));
		}

		// Vinculadas a Contrato de Parcelamento na tela de Manter Guia
		if (validaCampo(form
				.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta())) {
			sistemaParametro
					.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(new Short(
							form.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta()));
		}

		/*
		 * Adicionado por: Raimundo Martins Data: 19/07/2011 Indicador de
		 * Bloqueio de D�bitos a Cobrar Vinculados ao Contrato de Parcelamento
		 * no Manter D�bitos a Cobrar
		 */

		if (validaCampo(form
				.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito())) {
			sistemaParametro
					.setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(new Short(
							form.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito()));
		}

		// N�mero M�ximo de Parcelas para os Contratos de Parcelamento por
		// Cliente
		if (validaCampo(form.getNumeroMaximoParcelasContratosParcelamento())) {
			sistemaParametro
					.setNumeroMaximoParcelasContratosParcelamento(new Integer(
							form.getNumeroMaximoParcelasContratosParcelamento()));
		} 

		// N�mero de dias de vencimento para envio das contas para as empresas
		// de cobran�a:
		if (validaCampo(form.getNumeroDiasEnvioContaEmpresaCobranca())) {
			sistemaParametro
					.setNumeroDiasEnvioContaEmpresaCobranca(new Integer(form
							.getNumeroDiasEnvioContaEmpresaCobranca()));
		} 

		// N�mero de dias para retirada das contas das empresas de cobran�a:
		if (validaCampo(form.getNumeroDiaRetiradaContaEmpresaCobraca())) {
			sistemaParametro
					.setNumeroDiaRetiradaContaEmpresaCobraca(new Integer(form
							.getNumeroDiaRetiradaContaEmpresaCobraca()));
		} 
		
		// Quantidade Dias Validade Certicao Negativa
		if (validaCampo(form.getQuantidadeDiasValidadeCerticaoNegativa())) {
			sistemaParametro
				.setQuantidadeDiasValidadeCerticaoNegativa(new Integer(form
					.getQuantidadeDiasValidadeCerticaoNegativa()));
		} 
		
		// Indicador incluir conta fora do vencimento da cobran�a:
		if (validaCampo(form.getIndicadorIncluirContasForaVenCobranca())) {
			sistemaParametro
					.setIndicadorIncluirContasForaVenCobranca(new Short(form
							.getIndicadorIncluirContasForaVenCobranca()));
		}

		if (validaCampo(form
				.getIndicadorCriticarConteudoRetornoMovimentoNegativacao())) {
			sistemaParametro
					.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(new Short(
							form.getIndicadorCriticarConteudoRetornoMovimentoNegativacao()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Criticar Conte�do do Retorno Movimento Negativa��o Confirmado");
		}

		/*
		 * Adicionado por: Paulo Diniz Data: 13/10/2011 Solicita��o da Compesa
		 * para que seja exibido no filtro da tela de selecionar contas para
		 * cobran�a o filtro de pesquisa por valor total do d�bito ou por valor
		 * de conta.
		 */

		if (validaCampo(form.getIndicadorTotalDebito())) {
			sistemaParametro.setIndicadorTotalDebito(new Short(form
					.getIndicadorTotalDebito()));

		}
		
		if (validaCampo(form.getIndicadorCalcAcresImpontGuiaPagamento())) {
			sistemaParametro.setIndicadorCalcAcresImpontGuiaPagamento(new Short(form
					.getIndicadorCalcAcresImpontGuiaPagamento()));
		}

		/*
		 * Adicionado por: Diego Maciel Data: 10/11/2011 PE2011095860 - Retirar
		 * as contas com vencimento alterado da negativa��o.
		 */
		if (validaCampo(form.getIndicadorCanceNegatContaVencAlter())) {
			sistemaParametro.setIndicadorCanceNegatContaVencAlter(new Short(
					form.getIndicadorCanceNegatContaVencAlter()));
		}
		
		/*
		 * Adicionado por: Nathalia Santos  
		 * Data: 20/06/2012 RN2012063453 
		 * Inclus�o de campo para informar 
		 * n�mero de dias �teis para Vencimento das Contas da Entrada de Parcelamento
		 */
		if (validaCampo(form.getNumeroDiasVencContaEntradaParcelamento())) {
			sistemaParametro
					.setNumeroDiasVencContaEntradaParcelamento(new Integer(form
							.getNumeroDiasVencContaEntradaParcelamento()));
		} else {
			//sistemaParametro.setNumeroDiasVencContaEntradaParcelamento(null);
		}	
		
		/*
		 * Adicionado por: Nathalia Santos  
		 * Data: 02/07/2012 RN2012063453 
		 * Inclus�o de campo para informar 
		 * n�mero de dias �teis para Cancelamento da Entrada de Parcelamento
		 */
		if (validaCampo(form.getNumeroDiasCancelamentoEntradaParcelamento())) {
			sistemaParametro
					.setNumeroDiasCancelamentoEntradaParcelamento(new Integer(form
							.getNumeroDiasCancelamentoEntradaParcelamento()));
		} else {
			//sistemaParametro.setNumeroDiasCancelamentoEntradaParcelamento(null);
		}
		
		/*
		 * Adicionado por: Ricardo Germinio  
		 * Data: 18/10/2012 RN2012063453 
		 * Inclus�o de campo para informar 
		 * indicador de que os imoveis nao faturado recebe rateio de consumo da area comum
		 */
		if (validaCampo(form.getIndicadorRateioAreaComumImovelNaoFat())) {
			sistemaParametro
					.setIndicadorRateioAreaComumImovelNaoFat(new Short(form
							.getIndicadorRateioAreaComumImovelNaoFat()));
		} 
		
		/*
		 * Adicionado por: Jessica Diniz
		 * Data: 27/02/2013 MA2013015641
		 * Incluis�o indicador de forma a verificar se a remo��o da restri��o do cliente 
		 * no SPC/SERASA s� seja exclu�da ap�s o pagamento da entrada do parcelamento
		 */
		if (validaCampo(form.getIndicadorExcluirNegativacaoAposPgmto())) {
			sistemaParametro.setIndicadorExcluirNegativacaoAposPgmto(new Short(form
					.getIndicadorExcluirNegativacaoAposPgmto()));
		}
		
		else {
			//sistemaParametro.setIndicadorRateioAreaComumImovelNaoFat(null);
		}
		
		/*
		 * Autor: Jonathan Marcos
		 * Data: 03/10/2013
		 * [Observacao] 1.2.2.37. Indicador para verificar
		 * a acao predecessora para os imoveis do arquivo texto
		 */
		if (validaCampo(form.getIndicadorAcaoPredecessoraImoveisArquivoTexto())) {
			sistemaParametro
					.setIndicadorAcaoPredecessoraImoveisArquivoTexto(new Short(form
							.getIndicadorAcaoPredecessoraImoveisArquivoTexto()));
		}
		
		/*
		 * Autor: Diogo Luiz
		 * Data: 23/09/2014
		 * 1.2.2.38. Indicador de Permiss�o de Altera��o de Dados ou V�nculo de Cliente Negativado 
		 */
		if(validaCampo(form.getIndicadorPermissaoAlteracaoClienteNegativado())){
			sistemaParametro.setIndicadorPermissaoAlteracaoClienteNegativado(
				new Short(form.getIndicadorPermissaoAlteracaoClienteNegativado()));			
		}		
	}

	/**
	 * Monta os objetos da 5(Quinta) Aba
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2008
	 */
	private void montarSistemaParametro5Aba(
			InformarSistemaParametrosActionForm form,
			SistemaParametro sistemaParametro) {

		Fachada fachada = Fachada.getInstancia();

		// Indicador de Sugest�o de Tramite
		if (validaCampo(form.getIndicadorSugestaoTramite())) {
			sistemaParametro.setIndicadorSugestaoTramite(new Short(form
					.getIndicadorSugestaoTramite()));
		}

		// Indicador de controle de autorizacao para a tramitacao do RA
		if (validaCampo(form.getIndicadorControleTramitacaoRA())) {
			sistemaParametro.setIndicadorControleTramitacaoRA(new Short(form
					.getIndicadorControleTramitacaoRA()));
		}

		// Indicador de calculo da data prevista do RA em dias uteis
		if (validaCampo(form.getIndicadorCalculoPrevisaoRADiasUteis())) {
			sistemaParametro.setIndicadorCalculoPrevisaoRADiasUteis(new Short(
					form.getIndicadorCalculoPrevisaoRADiasUteis()));
		}

		// Indicador de documento obrigatorio para segunda via da conta
		if (validaCampo(form.getIndicadorDocumentoValido())) {
			sistemaParametro.setIndicadorDocumentoValido(new Short(form
					.getIndicadorDocumentoValido()));
		}

		// Dias Maximo para Reativar RA
		if (validaCampo(form.getDiasMaximoReativarRA())) {
			sistemaParametro.setDiasReativacao(new Short(form
					.getDiasMaximoReativarRA()));
		}

		// Dias Maximo para alterar Dados da OS
		if (validaCampo(form.getDiasMaximoAlterarOS())) {
			sistemaParametro.setDiasMaximoAlterarOS(new Integer(form
					.getDiasMaximoAlterarOS()));
		}

		// Ultimo ID Utilizado para Gera��o de RA Manual
		if (validaCampo(form.getUltimoIDGeracaoRA())) {
			sistemaParametro.setUltimoRAManual(new Integer(form
					.getUltimoIDGeracaoRA()));
		}

		// Dias MAximo para Expirar Acesso
		if (validaCampo(form.getDiasMaximoExpirarAcesso())) {
			sistemaParametro.setNumeroDiasExpiracaoAcesso(new Short(form
					.getDiasMaximoExpirarAcesso()));
		}

		// Dias para Come�ar Aparecer a Msg da Expiracao da Senha
		if (validaCampo(form.getDiasMensagemExpiracaoSenha())) {
			sistemaParametro.setNumeroDiasMensagemExpiracao(new Short(form
					.getDiasMensagemExpiracaoSenha()));
		}

		// Indicador certidao negativa com efeito positivo
		if (validaCampo(form.getIndicadorCertidaoNegativaEfeitoPositivo())) {
			sistemaParametro
					.setIndicadorCertidaoNegativaEfeitoPositivo(new Short(form
							.getIndicadorCertidaoNegativaEfeitoPositivo()));
		}

		// Indicador debito a cobrar valido certidao negativa
		if (validaCampo(form.getIndicadorDebitoACobrarValidoCertidaoNegativa())) {
			sistemaParametro
					.setIndicadorDebitoACobrarValidoCertidaoNegativa(new Short(
							form.getIndicadorDebitoACobrarValidoCertidaoNegativa()));
		}

		// Numero Dias de Vencimento para gerar Certidao Negativa
		if (validaCampo(form.getDiasVencimentoCertidaoNegativa())) {
			sistemaParametro
					.setNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos(new Short(
							form.getDiasVencimentoCertidaoNegativa()));
		}

		// Numero Maximo de Tentativas de Acesso
		if (validaCampo(form.getNumeroMaximoTentativasAcesso())) {
			sistemaParametro.setNumeroMaximoLoginFalho(new Short(form
					.getNumeroMaximoTentativasAcesso()));
		}

		// Numero Maximo de Favoritos no Menu do Sistema
		if (validaCampo(form.getNumeroMaximoFavoritosMenu())) {
			sistemaParametro.setNumeroMaximoFavorito(new Integer(form
					.getNumeroMaximoFavoritosMenu()));
		}

		// IP do Servidor SMTP
		if (validaCampo(form.getIpServidorSmtp())) {
			sistemaParametro.setIpServidorSmtp(form.getIpServidorSmtp());
		}

		// IP do Servidor Gerencial
		if (validaCampo(form.getIpServidorGerencial())) {
			sistemaParametro.setIpServidorModuloGerencial(form
					.getIpServidorGerencial());
		}

		// E-mail do Responsavel
		if (validaCampo(form.getEmailResponsavel())) {
			sistemaParametro.setDsEmailResponsavel(form.getEmailResponsavel());
		}

		// Mensagem do Sistema
		if (validaCampo(form.getMensagemSistema())) {
			sistemaParametro.setMensagemSistema(form.getMensagemSistema());
		}

		// Indicador Login Unico
		if (validaCampo(form.getIndicadorLoginUnico())) {
			sistemaParametro.setIndicadorLoginUnico(new Short(form
					.getIndicadorLoginUnico()));
		}

		if (validaCampo(form.getUltimoDiaVencimentoAlternativo())) {
			sistemaParametro.setUltimoDiaVencimentoAlternativo(new Short(form
					.getUltimoDiaVencimentoAlternativo()));
		}

		// Indicador de valida��o da localidade no encerramento da OS Seletiva
		if (validaCampo(form.getIndicadorValidacaoLocalidadeEncerramentoOS())) {
			sistemaParametro
					.setIndicadorValidarLocalizacaoEncerramentoOS(new Short(
							form.getIndicadorValidacaoLocalidadeEncerramentoOS()));
		}	
		
		
		// Situa��o de �gua na Exclus�o de Im�vel
		if (form.getSituacaoAguaExclusaoImovel() != null && 
				!form.getSituacaoAguaExclusaoImovel().equals("-1")){
			
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = 
					new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.ID, form.getSituacaoAguaExclusaoImovel()));
			Collection colecaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao, 
				LigacaoAguaSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoLigacaoAgua)){
				
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao)
						Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				sistemaParametro.setSituacaoAguaExclusaoImovel(ligacaoAguaSituacao.getId());
			}				
		}
		
		//Situa��o de Esgoto na Exclus�o de Im�vel
		if(form.getSituacaoEsgotoExclusaoImovel() != null && 
				!form.getSituacaoEsgotoExclusaoImovel().equals("-1")){
			
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.ID, form.getSituacaoEsgotoExclusaoImovel()));
			Collection filtroLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao, 
				LigacaoEsgotoSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(filtroLigacaoEsgoto)){
				
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) 
						Util.retonarObjetoDeColecao(filtroLigacaoEsgoto); 
				sistemaParametro.setSituacaoEsgotoExclusaoImovel(ligacaoEsgotoSituacao.getId());				
			}
		}
		
		
		// Indicador de controle de dias de expira��o de senha por Grupo
		if (validaCampo(form.getIndicarControleExpiracaoSenhaPorGrupo())) {
			sistemaParametro
					.setIndicadorControleExpiracaoSenhaPorGrupo(new Integer(
							form.getIndicarControleExpiracaoSenhaPorGrupo()));
		}
		// Indicador de controle de bloqueio de senhas usadas anteriormente
		if (validaCampo(form.getIndicarControleBloqueioSenha())) {
			sistemaParametro
					.setIndicadorControleBloqueioSenhaAnterior(new Integer(form
							.getIndicarControleBloqueioSenha()));
		}
		// Indicador de controle de senha forte
		if (validaCampo(form.getIndicadorSenhaForte())) {
			sistemaParametro.setIndicadorSenhaForte(new Integer(form
					.getIndicadorSenhaForte()));
		}
		// Unidade Organizacional Tramite Grande Consumidor
		if (validaCampo(form.getIdUnidadeDestinoGrandeConsumidor())) {

			FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, form
							.getIdUnidadeDestinoGrandeConsumidor()));

			Collection<UnidadeOrganizacional> colecaoUnidade = fachada
					.pesquisar(filtroUnidadeEmpresa,
							UnidadeOrganizacional.class.getName());

			UnidadeOrganizacional unidadeOrganizacionalTramiteGrandeConsumidor = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidade);

			if (unidadeOrganizacionalTramiteGrandeConsumidor != null) {

				if (new Short(
						unidadeOrganizacionalTramiteGrandeConsumidor
								.getIndicadorTramite())
						.compareTo(ConstantesSistema.NAO) == 0) {
					throw new ActionServletException(
							"atencao.unidade.nao.aceita.tramite");
				}
				if (new Short(

				unidadeOrganizacionalTramiteGrandeConsumidor.getIndicadorUso())
						.compareTo(ConstantesSistema.NAO) == 0) {
					throw new ActionServletException(
							"atencao.unidade.nao.ativa");
				}
				sistemaParametro
						.setUnidadeOrganizacionalTramiteGrandeConsumidor(unidadeOrganizacionalTramiteGrandeConsumidor);
			}
		}

		if (validaCampo(form.getNumeroDiasRevisaoConta())) {
			sistemaParametro.setNumeroDiasRevisaoComPermEspecial(new Integer(
					form.getNumeroDiasRevisaoConta()));
		}

		// N�mero de dias para validade ordem de fiscaliza��o
		if (validaCampo(form.getQtdeDiasValidadeOSFiscalizacao())) {
			sistemaParametro.setQtdeDiasValidadeOSFiscalizacao(new Integer(form
					.getQtdeDiasValidadeOSFiscalizacao()));
		}

		// N�mero m�ximo de dias para uma ordem de servi�o ser fiscalizada
		if (validaCampo(form.getQtdeDiasEncerraOSFiscalizacao())) {
			sistemaParametro.setQtdeDiasEncerraOSFiscalizacao(new Integer(form
					.getQtdeDiasEncerraOSFiscalizacao()));
		}

		// N�mero de dias para envio de conta por email
		if (validaCampo(form.getQtdeDiasEnvioEmailConta())) {
			sistemaParametro.setQtdeDiasEnvioEmailConta(new Integer(form
					.getQtdeDiasEnvioEmailConta()));
		}

		// RM5759 Indicador Exigir RA no cancelamento do d�bito
		if (validaCampo(form.getIndicadorPermiteCancelarDebito())) {
			sistemaParametro.setIndicadorPermiteCancelarDebito(new Short(form
					.getIndicadorPermiteCancelarDebito()));
		}

		// Descri��o do Regulamento para Loja Virtual
		if (validaCampo(form.getDescricaoRegulamento())) {
			sistemaParametro.setDescricaoRegulamento(form.getDescricaoRegulamento());
		}

		// Arquivo do Regulamento para Loja Virtual
		if (form.getArquivoRegulamento() != null) {
			try {
				if (form.getArquivoRegulamento().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(
						form.getArquivoRegulamento().getFileData(),
						retornarExtensaoArquivo(form.getArquivoRegulamento()));
					
					sistemaParametro.setArquivoRegulamento(form.getArquivoRegulamento().getFileData());
				}
			} catch (IOException e) {

			}
		}

		// Descri��o do Decreto para Loja Virtual
		if (validaCampo(form.getDescricaoDecreto())) {
			sistemaParametro.setDescricaoDecreto(form.getDescricaoDecreto());
		}

		// Arquivo do Decreto para Loja Virtual
		if (form.getArquivoDecreto() != null) {
			try {
				if (form.getArquivoDecreto().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoDecreto().getFileData(),
							retornarExtensaoArquivo(form.getArquivoDecreto()));
					sistemaParametro.setArquivoDecreto(form.getArquivoDecreto()
							.getFileData());
				}
			} catch (IOException e) {

			}
		}

		// Descri��o da Lei de Estrutura Tarifaria para Loja Virtual
		if (validaCampo(form.getDescricaoLeiEstTarif())) {
			sistemaParametro.setDescricaoLeiEstTarif(form
					.getDescricaoLeiEstTarif());
		}

		// Arquivo da Lei de Estrutura Tarifaria para Loja Virtual
		if (form.getArquivoLeiEstTarif() != null) {
			try {
				if (form.getArquivoLeiEstTarif().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoLeiEstTarif().getFileData(),
							retornarExtensaoArquivo(form
									.getArquivoLeiEstTarif()));
					sistemaParametro.setArquivoLeiEstTarif(form
							.getArquivoLeiEstTarif().getFileData());

				}
			} catch (IOException e) {

			}
		}

		// Descri��o da Lei de Individualiza��o Predial para Loja Virtual
		if (validaCampo(form.getDescricaoLeiIndividualizacao())) {
			sistemaParametro.setDescricaoLeiIndividualizacao(form
					.getDescricaoLeiIndividualizacao());
		}

		// Arquivo da Lei de Individualiza��o Predial para Loja Virtual
		if (form.getArquivoLeiIndividualizacao() != null) {
			try {
				if (form.getArquivoLeiIndividualizacao().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoLeiIndividualizacao().getFileData(),
							retornarExtensaoArquivo(form
									.getArquivoLeiIndividualizacao()));
					sistemaParametro.setArquivoLeiIndividualizacao(form
							.getArquivoLeiIndividualizacao().getFileData());

				}
			} catch (IOException e) {

			}
		}

		// Descri��o da Norma CO para Loja Virtual
		if (validaCampo(form.getDescricaoNormaCO())) {
			sistemaParametro.setDescricaoNormaCO(form.getDescricaoNormaCO());
		}

		// Arquivo da Norma CO para Loja Virtual
		if (form.getArquivoNormaCO() != null) {
			try {
				if (form.getArquivoNormaCO().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoNormaCO().getFileData(),
							retornarExtensaoArquivo(form.getArquivoNormaCO()));
					sistemaParametro.setArquivoNormaCO(form.getArquivoNormaCO()
							.getFileData());

				}
			} catch (IOException e) {

			}
		}

		// Descri��o da Norma CM para Loja Virtual
		if (validaCampo(form.getDescricaoNormaCM())) {
			sistemaParametro.setDescricaoNormaCM(form.getDescricaoNormaCM());
		}

		// Arquivo da Norma CM para Loja Virtual
		if (form.getArquivoNormaCM() != null) {
			try {
				if (form.getArquivoNormaCM().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form
							.getArquivoNormaCM().getFileData(),
							retornarExtensaoArquivo(form.getArquivoNormaCM()));

					sistemaParametro.setArquivoNormaCM(form.getArquivoNormaCM()
							.getFileData());

				}
			} catch (IOException e) {

			}
		}
	}

	private String retornarExtensaoArquivo(FormFile formFile) {
		String[] nomeArquivoPartido = formFile.getFileName().split("\\.");

		String formato = nomeArquivoPartido[1];

		return formato;

	}
	
	private void validarClientePrograma(InformarSistemaParametrosActionForm form) {

		FiltroCliente filtroCliente = new FiltroCliente();
		Collection<?> colecaoCliente = null;

		if(form.getIdClienteResponsavelProgramaEspecial() !=  null && 
				!form.getIdClienteResponsavelProgramaEspecial().equals("")){
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					new Integer(form.getIdClienteResponsavelProgramaEspecial())));
		

		// Pesquisa de acordo com os par�metros informados no filtro
			colecaoCliente = this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		
		}

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (Util.isVazioOrNulo(colecaoCliente)){
			throw new ActionServletException("atencao.pesquisa_inexistente", "Cliente Responsavel Programa Especial");
		}
	}
	
}