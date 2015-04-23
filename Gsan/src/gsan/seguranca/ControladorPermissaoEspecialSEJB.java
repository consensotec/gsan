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
package gsan.seguranca;

import gsan.cadastro.imovel.Imovel;
import gsan.seguranca.acesso.FiltroGrupoPermissaoEspecial;
import gsan.seguranca.acesso.GrupoPermissaoEspecial;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.FiltroPemissaoEspecial;
import gsan.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gsan.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioGrupo;
import gsan.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Defini��o da l�gica de neg�cio do Session Bean de
 * ControladorPermissaoEspecial
 * 
 * @author Rodrigo Silveira
 * @created 07/11/2006
 */

public class ControladorPermissaoEspecialSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {

	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Verifica as permiss�es especiais do usu�rio por funcionalidade informada
	 * no sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 07/11/2006
	 * 
	 * @param permissaoEspecial
	 * @param usuario
	 * @return
	 */
	public boolean verificarPermissaoEspecial(int permissaoEspecial,
			Usuario usuario) throws ControladorException {

		boolean retorno = false;

		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial
				.adicionarParametro(new ParametroSimples(
						FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID, usuario
								.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(
				FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID,
				permissaoEspecial));

		if (getControladorUtil().pesquisar(filtroUsuarioPemissaoEspecial,
				UsuarioPermissaoEspecial.class.getName()).size() > 0) {
			retorno = true;

		}

		if (retorno){
			return retorno;
		} else {
			//Verifica se o grupo tem permiss�o
			return verificarPermissaoEspecialUsuarioGrupo(permissaoEspecial, usuario);
		}
	}

	//Verifica se algum dos grupos aos quais o usu�rio pertence tem determinada permissao especial
	public boolean verificarPermissaoEspecialUsuarioGrupo(int permissaoEspecial, Usuario usuario) throws ControladorException {
		FiltroUsuarioGrupo filtro = new FiltroUsuarioGrupo();
		filtro.adicionarParametro(new ParametroSimples(FiltroUsuarioGrupo.USUARIO, usuario));
		Collection<UsuarioGrupo> grupos = getControladorUtil().pesquisar(filtro, UsuarioGrupo.class.getName());
		if (grupos!=null){
			for (UsuarioGrupo usuarioGrupo : grupos){
				if (verificarPermissaoEspecialGrupo(usuarioGrupo.getComp_id().getGrupoId(), permissaoEspecial)){
					return true; 
				}
			}
		}
		return false;
	}

	//Verifica se o grupo tem determinada permissao especial 
	public boolean verificarPermissaoEspecialGrupo(int idGrupo, int permissaoEspecial) throws ControladorException {
		FiltroGrupoPermissaoEspecial filtro = new FiltroGrupoPermissaoEspecial();
		filtro.adicionarParametro(new ParametroSimples(FiltroGrupoPermissaoEspecial.GRUPO_ID, idGrupo));
		filtro.adicionarParametro(new ParametroSimples(FiltroGrupoPermissaoEspecial.PERMISSAO_ESPECIAL_ID, permissaoEspecial));
		filtro.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		Collection<GrupoPermissaoEspecial> colecao = getControladorUtil().pesquisar(filtro, GrupoPermissaoEspecial.class.getName());
		if (colecao.size()>0){
			return true;
		}
		return false;
	}
		
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * Verifica as permiss�es especiais do usu�rio por funcionalidade informada
	 * no sistema
	 * 
	 * @author Vivianne Sousa
	 * @date 09/11/2006
	 * 
	 * @param permissaoEspecial
	 * @param usuario
	 * @param objeto
	 * 
	 */
	public void verificarPermissaoEspecial(int permissaoEspecial,
			Usuario usuario, Object objeto) throws ControladorException {

		switch (permissaoEspecial) {

		case PermissaoEspecial.IMOVEL_EM_SITUACAO_COBRANCA:

			if (objeto instanceof Imovel) {
				Imovel imovel = (Imovel) objeto;
				verificarPermissaoEspecialImovelSituacaoCobranca(
						permissaoEspecial, usuario, imovel);
			}

			break;

		}

	}

	/**
	 * Verifica as permiss�es especiais do usu�rio por funcionalidade informada
	 * no sistema
	 * 
	 * @author Vivianne Sousa
	 * @date 09/11/2006
	 * 
	 * @param permissaoEspecial
	 * @param usuario
	 * @param imovel
	 */
	public void verificarPermissaoEspecialImovelSituacaoCobranca(
			int permissaoEspecial, Usuario usuario, Imovel imovel)
			throws ControladorException {

		if (imovel.getCobrancaSituacao() != null
				&& imovel.getCobrancaSituacao().getId() != null
				&& !imovel.getCobrancaSituacao().getId().equals("")
				&& !imovel.getCobrancaSituacao().getId().equals("0")
                && imovel.getCobrancaSituacao().getIndicadorBloqueioParcelamento().equals(ConstantesSistema.SIM)) {

			if (!verificarPermissaoEspecial(permissaoEspecial, usuario)) {
				throw new ControladorException(
						"atencao.imovel.em.situacao.cobranca", null, imovel
								.getCobrancaSituacao().getDescricao());
			}

		}

	}

	/**
	 * Verifica permiss�o especial para aceitar um valor de entrada menor q o
	 * valor m�nimo de entrada na terceira p�gina de Efetuar Parcelamento
	 * D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 28/11/2006
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoValMinimoEntrada(Usuario usuario)
			throws ControladorException {

		boolean temPermissaoValMinimoEntrada = this.verificarPermissaoEspecial(
				PermissaoEspecial.TESTAR_VAL_MINIMO_ENTRADA, usuario);

		return temPermissaoValMinimoEntrada;
	}
	
	/**
	 * Verifica permiss�o especial para habilitar ou n�o o formul�rio
	 * 
	 * @author Arthur Carvalho
	 * @date 13/10/2008
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoValidarAcrescimosImpontualidade(Usuario usuario)
			throws ControladorException {

		boolean validarAcrescimoImpontualidade = this.verificarPermissaoEspecial(
				PermissaoEspecial.VALIDAR_ACRESCIMOS_IMPONTUALIDADE, usuario);

		return validarAcrescimoImpontualidade;
	}

	/**
	 * Verifica permiss�o especial para atualizar um cliente que seja usu�rio da
	 * tarifa social
	 * 
	 * @author Rafael Corr�a
	 * @date 16/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarUsuarioTarifaSocial(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoAtualizarUsuarioTarifaSocial = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.CLIENTE_USUARIO_TARIFA_SOCIAL,
						usuario);

		return temPermissaoAtualizarUsuarioTarifaSocial;
	}

	
	
	/**
	 * Verifica permiss�o especial para atualizar um LOGRADOURO_BAIRRO
	 * 
	 * @author Raphael Rossiter, Romulo Aurelio
	 * @date 24/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	 public boolean verificarPermissaoAtualizarLogradouroBairro(
			   Usuario usuario) throws ControladorException {

		 boolean retorno = this.verificarPermissaoEspecial(
				 PermissaoEspecial.ATUALIZAR_LOGRADOURO_BAIRRO,
			     usuario);

		return retorno;
	}
	 
	
	 /**
	  * Verifica permiss�o especial para N�O gerar d�bito no informar retorno OS fiscaliza��o
	  * 
	  * @author Raphael Rossiter
	  * @date 03/03/2007
	  * 
	  * @param usuario
	  */
	 public boolean verificarPermissaoGeracaoDebitoOSFiscalizacao(
				   Usuario usuario) throws ControladorException {

		 boolean retorno = this.verificarPermissaoEspecial(
					 PermissaoEspecial.GERACAO_DEBITO_OS_FISCALIZACAO,
				     usuario);

			return retorno;
	 }
	 
	 
	


	/**
	 * Verifica permiss�o especial para inserir Imovel com logradouro.municipio
	 * diferente de setorComercial.municipio
	 * 
	 * @author R�mulo Aur�lio
	 * @date 23/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirImovelMunicipioLogradouroDiferenteSetor(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInserirImovelMunicipioLogradouroDiferenteSetor = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_IMOVEL_MUNICIPIO_LOGRADOURO_DIFERENTE_SETOR,
						usuario);

		return temPermissaoInserirImovelMunicipioLogradouroDiferenteSetor;
	}

	/**
	 * Verifica permiss�o especial para atualizar Imovel com
	 * logradouro.municipio diferente de setorComercial.municipio
	 * 
	 * @author R�mulo Aur�lio
	 * @date 23/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.ATUALIZAR_IMOVEL_MUNICIPIO_LOGRADOURO_DIFERENTE_SETOR,
						usuario);

		return temPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor;
	}

	/**
	 * Verifica permiss�o especial para inserir d�bito a cobrar
	 * sem valor da entrada e a taxa de juros
	 * 
	 * @author Ana Maria
	 * @date 27/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarSemEntradaSemJuros(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInserirDebitoACobrarSemEntradaSemJuros = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_DEBITO_A_COBRAR_SEM_ENTRADA_SEM_JUROS,
						usuario);

		return temPermissaoInserirDebitoACobrarSemEntradaSemJuros;
	}	
	
	/**
	 * Verifica permiss�o especial para inserir motivo
	 * da n�o cobran�a
	 * 
	 * @author Ana Maria
	 * @date 03/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInformarMotivoNaoCobranca(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INFORMAR_MOTIVO_NAO_COBRANCA,
						usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}	
	
	
	/**
	 * Verifica permiss�o especial para informar nova data para vencimento alternativo 
	 * 
	 * @author Vivianne Sousa
	 * @date 06/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInformarVencimentoAlternativoNovaData(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INFORMAR_VENCIMENTO_ALTERNATIVO_NOVA_DATA,
						usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}	
	
	/**
	 * Verifica permiss�o especial para n�o testar quantidade de parcelas no Efetuar Parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 16/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoNaoTestarQtdePrestacaoParcelamento(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.PARCELAR_NAO_TESTAR_QTDE_DE_PRESTACAO,
						usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}
	

	/**
	 * Verifica permiss�o especial para informar 
	 * nova data para vencimento alternativo antes do periodo v�lido
	 * 
	 * @author Vivianne Sousa
	 * @date 19/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INFORMAR_VENCIMENTO_ALTERNATIVO_ANTES_DO_PERIODO_VALIDO,
						usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}
	
	/**
	 * Manter conta - Alterar vencimento sem ra
	 * 
	 * @author Ana Maria
	 * @date 26/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarVencimentoSemRa(Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VENCIMENTO_CONTA_SEM_RA, usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}
	
	
	/**
	 * Inserir conta - inserir conta sem cronograma de faturamento e sem atividade efetuar leitura
	 * 
	 * @author Raphael Rossiter
	 * @date 08/05/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirContaFaturamentoAntecipado(Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_CONTA_FATURAMENTO_ANTECIPADO, usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}
	
	/**
	 * Inseir D�bito a cobrar
	 * 
	 * @author Ana Maria
	 * @date 23/05/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarSemRa(Usuario usuario) throws ControladorException {

		boolean temPermissaoInserirDebitoACobrarSemRa = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_DEBITO_A_COBRAR_SEM_RA, usuario);

		return temPermissaoInserirDebitoACobrarSemRa;
	}
	
	/**
	 * Atualizar Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarNomeCliente(Usuario usuario) throws ControladorException {

		boolean temPermissaoAlterarNomeCliente = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_NOME_CLIENTE, usuario);

		return temPermissaoAlterarNomeCliente;
	}
	
	/**
	 * Incluir Devolu��o
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoIcluirDevolucaoMaiorValorMaximo(Usuario usuario) throws ControladorException {

		boolean temPermissaoIcluirDevolucaoMaiorValorMaximo = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INCLUIR_DEVOLUCAO_MAIOR_VALOR_MAXIMO, usuario);

		return temPermissaoIcluirDevolucaoMaiorValorMaximo;
	}
	
	/**
	 * [UC0194] Cr�dito a Realizar Permite inserir um cr�dito a realizar
	 * 
	 * @author S�vio Luiz
	 * @since 21/08/2006
	 */
	public boolean verificarPermissaoIcluirCreditoARealizarValorMaximo(Usuario usuario) throws ControladorException {

		boolean temPermissaoIcluirDevolucaoMaiorValorMaximo = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INCLUIR_CREDITO_A_REALIZAR_VALOR_MAXIMO, usuario);

		return temPermissaoIcluirDevolucaoMaiorValorMaximo;
	}
	
	/**
	 * [UC0194] Cr�dito a Realizar Permite inserir um cr�dito a realizar
	 * 
	 * @author S�vio Luiz
	 * @since 21/08/2006
	 */
	public boolean verificarPermissaoIcluirCreditoARealizarQuantidadeParcelasMaximo(Usuario usuario) throws ControladorException {

		boolean temPermissaoIcluirDevolucaoMaiorValorMaximo = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INCLUIR_CREDITO_A_REALIZAR_QUANTIDADE_PARCELAS_MAXIMO, usuario);

		return temPermissaoIcluirDevolucaoMaiorValorMaximo;
	}
	
	/**
	 * [UC0630] Solicitar Emiss�o do Extrato de Debitos
	 * 
	 * @author Vivianne Sousa
	 * @since 30/08/2007
	 */
	public boolean verificarPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto(Usuario usuario) throws ControladorException {

		boolean temPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INCLUIR_ACRESCIMO_IMPONTUALIDADE_NO_EXTRATO_DE_DEBITOS_COM_DESCONTO, usuario);

		return temPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto;
	}
	
	/**
	 * [UC0630] Solicitar Emiss�o do Extrato de Debitos
	 * 
	 * @author Vivianne Sousa
	 * @since 30/08/2007
	 */
	public boolean verificarPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos(Usuario usuario) throws ControladorException {

		boolean temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.RETIRAR_TAXA_COBRANCA_DO_EXTRATO_DE_DEBITOS, usuario);

		return temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos;
	}
	
	/**
	 * [UC0XXX] Consultar D�bitos
	 * 
	 * @author Rafael Corr�a
	 * @since 13/09/2007
	 */
	public boolean verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos(Usuario usuario) throws ControladorException {

		boolean verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.CONSULTAR_DEBITOS_INDICADO_NA_CONTA_OU_TODOS, usuario);

		return verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos;
	}
	
	
	/**
	 * Inserir d�bito a cobrar - inserir debito a cobrar independente da situacao da ligacao de agua e esgoto do
	 * imovel 
	 * 
	 * @author Raphael Rossiter
	 * @date 03/10/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarImovelSituacao(Usuario usuario) throws ControladorException {

		boolean temPermissaoInserirDebitoACobrarImovelSituacao = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_DEBITO_A_COBRAR_IMOVEL_SITUACAO, usuario);

		return temPermissaoInserirDebitoACobrarImovelSituacao;
	}
	
	/**
	 * Reiniciar um batch
	 * 
	 * @author Rafael Corr�a
	 * @date 06/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoReiniciarBatch(Usuario usuario) throws ControladorException {

		boolean temPermissaoReiniciarBatch = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.REINICIAR_BATCH, usuario);

		return temPermissaoReiniciarBatch;
	}
	
	/**
	 * Permite retificar uma conta sem RA 
	 * 
	 * @author Raphael Rossiter
	 * @date 09/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaSemRA(Usuario usuario) throws ControladorException {

		boolean temPermissaoRetificarContaSemRA = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.RETIFICAR_CONTA_SEM_RA, usuario);

		return temPermissaoRetificarContaSemRA;
	}
	
	/**
	 * Permite excluir debito a cobrar 
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoExcluirDebitoACobrar(Usuario usuario) throws ControladorException {

		boolean temPermissaoExcluirDebitoACobrar = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.EXCLUIR_DEBITO_A_COBRAR, usuario);

		return temPermissaoExcluirDebitoACobrar;
	}
	
	/**
	 * Permite Gerar OS Seletivas de Hidrometro 
	 * 
	 * @author Ivan S�rgio
	 * @date 06/12/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoGerarOSSeletivasHidrometro(Usuario usuario) throws ControladorException {

		boolean temPermissao = this.verificarPermissaoEspecial(
						PermissaoEspecial.GERAR_OS_SELETIVA_HIDROMETRO, usuario);

		return temPermissao;
	}
	
	/**
	 * Permite Cancelar a Conta Sem RA 
	 * 
	 * @author Ivan S�rgio
	 * @date 28/01/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoCancelarContaSemRA(Usuario usuario) throws ControladorException {

		boolean temPermissao = this.verificarPermissaoEspecial(
						PermissaoEspecial.CANCELAR_CONTA_SEM_RA, usuario);

		return temPermissao;
	}
	
	/**
	 * Permite Atualizar os Dados da Fiscalizacao 
	 * 
	 * @author Ivan S�rgio
	 * @date 10/04/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarDadosFiscalizacao(Usuario usuario) throws ControladorException {

		boolean temPermissao = this.verificarPermissaoEspecial(
						PermissaoEspecial.ATUALIZAR_DADOS_FISCALIZACAO, usuario);

		return temPermissao;
	}
	
	/**
	 * Permite Visualizar Dia de Vencimento da Conta em Cliente
	 * 
	 * @author R�mulo Aur�lio
	 * @date 07/05/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoVisualizarDiaVencimentoContaCliente(Usuario usuario) throws ControladorException {

		boolean temPermissaoVisualizarDiaVencimentoContaCliente = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.VISUALIZAR_DIA_VENCIMENTO_CLIENTE, usuario);

		return temPermissaoVisualizarDiaVencimentoContaCliente;
	}
	/**
	 * Permite Desfazer a Fiscalizacao do Boletim de OS Concluida 
	 * 
	 * @author Ivan S�rgio
	 * @date 02/05/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoDesfazerFiscalizacaoBoletimOSConcluida(
			Usuario usuario) throws ControladorException {

		boolean temPermissao = this.verificarPermissaoEspecial(
						PermissaoEspecial.DESFAZER_FISCALIZACAO_BOLETIM_OS_CONCLUIDA, usuario);

		return temPermissao;
	}
	
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @since 22/05/2008
     */
    public boolean verificarPermissaoInserirImovelComPerfilCorporativo(Usuario usuario) throws ControladorException {

        boolean temPermissaoInserirImovelComPerfilCorporativo = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.INSERIR_IMOVEL_COM_PERFIL_CORPORATIVO, usuario);

        return temPermissaoInserirImovelComPerfilCorporativo;
    }
    
    /**
     * [UC0014] Manter Imovel
     * 
     * @author Vivianne Sousa
     * @since 22/05/2008
     */
    public boolean verificarPermissaoAlterarPerfilCorporativoImovel(Usuario usuario) throws ControladorException {

        boolean temPermissaoAlterarPerfilCorporativoImovel = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.ALTERAR_PERFIL_CORPORATIVO_IMOVEL, usuario);

        return temPermissaoAlterarPerfilCorporativoImovel;
    }
    
    
    /**
     * [UC457]Encerrar Ordem Servico.
     * 
     * @author Yara Taciane
     * @since 18/06/2008
     */
    public boolean verificarPermissaoInformarDataEncOSAnteriorDataCorrente(Usuario usuario) throws ControladorException {

        boolean temPermissaoInformarDataEncOSAnteriorDataCorrente = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.INFORMAR_DATA_ENC_OS_ANTERIOR_DATA_CORRENTE, usuario);

        return temPermissaoInformarDataEncOSAnteriorDataCorrente;
    }
    
    
    /**
     * [UC399] Inserir Tipo de Solicita��o com Especifica��es
     * 
     * @author Rafael Corr�a
     * @since 12/08/2008
     */
    public boolean verificarPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao(Usuario usuario) throws ControladorException {

        boolean temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.ALTERAR_INDICADOR_USO_SISTEMA_TIPO_SOLICITACAO, usuario);

        return temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao;
    }
   
    /**
     * @author Vivianne Sousa
     * @since 15/09/2008
     */
    public boolean verificarPermissaoAlterarValidadeExtratoDebito(Usuario usuario) throws ControladorException {

        boolean temPermissaoAlterarValidadeExtratoDebito = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.ALTERAR_VALIDADE_EXTRATO_DEBITO, usuario);

        return temPermissaoAlterarValidadeExtratoDebito;
    }
    
    /**
	 * Permite emitir segunda via de uma conta sem documento de CPF OU CNPJ cadastrados no sistema. Esse situa��o
	 * ocorrer� para as empresas que optarem pela obrigatoriedade do CPF OU CNPJ informado. 
	 * 
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmitir2ViaSemDocumentoValido(Usuario usuario) throws ControladorException {

		boolean temPermissaoEmitir2ViaSemDocumentoValido = this
				.verificarPermissaoEspecial(PermissaoEspecial.EMITIR_2_VIA_SEM_DOCUMENTO_VALIDO, usuario);

		return temPermissaoEmitir2ViaSemDocumentoValido;
	}
	
    /**
	 * Permite emitir certid�o negativa mesmo que o cliente tenha um superior. 
	 * 
	 * @author Rafael Corr�a
	 * @date 12/11/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmitirCertidaoNegativaComClienteSuperior(Usuario usuario) throws ControladorException {

		boolean temPermissaoEmitirCertidaoNegativaComClienteSuperior = this
				.verificarPermissaoEspecial(PermissaoEspecial.EMITIR_CERTIDAO_NEGATIVA_COM_CLIENTE_SUPERIOR, usuario);

		return temPermissaoEmitirCertidaoNegativaComClienteSuperior;
	}
	
	/**
	 * Permite retificar um conjunto de contas a partir do manter conta. 
	 * 
	 * @author Raphael Rossiter
	 * @date 03/11/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarConjuntoConta(Usuario usuario) throws ControladorException {

		boolean temPermissaoRetificarConjuntoConta = this
				.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONJUNTO_CONTA, usuario);

		return temPermissaoRetificarConjuntoConta;
	}
	
	
	/**
	 * Permite efetuar supress�o de liga��o de �gua. 
	 * 
	 * @author Vivianne Sousa
	 * @date 09/03/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEfetuarSupressaoAgua(Usuario usuario) throws ControladorException {

		boolean temPermissaoEfetuarSupressaoAgua = this
				.verificarPermissaoEspecial(PermissaoEspecial.EFETUAR_SUPRESSAO_DE_LIGACAO_AGUA, usuario);

		return temPermissaoEfetuarSupressaoAgua;
	}
    
	/**
	 * Permite alterar situa��o de liga��o para imovel com debitos . 
	 * 
	 * @author Vivianne Sousa
	 * @date 18/03/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarSituacaoLigacaoParaImovelComDebito(Usuario usuario) throws ControladorException {

		boolean temPermissaoAlterarSituacaoLigacaoParaImovelComDebito = this
				.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_SITUACAO_LIGACAO_PARA_IMOVEL_COM_DEBITO, usuario);

		return temPermissaoAlterarSituacaoLigacaoParaImovelComDebito;
	}
	
	/**
	 * Permite retificar conta do im�vel com o perfil bloqueado. 
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaImovelPefilBloqueado(Usuario usuario) throws ControladorException {

		boolean temPermissaoRetificarContaImovelPefilBloqueado = this
				.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_IMOVEL_PERFIL_BLOQUEADO, usuario);

		return temPermissaoRetificarContaImovelPefilBloqueado;
	}
	
	/**
	 * Permite retificar conta do im�vel com o perfil bloqueado. 
	 * 
	 * @author S�vio Luiz
	 * @date 15/09/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmissaoDocumentoCobranca(Usuario usuario) throws ControladorException {

		boolean temPermissaoEmissaoDocumentoCobranca = this
				.verificarPermissaoEspecial(PermissaoEspecial.EMITIR_DOCUMENTO_COBRANCA,usuario);

		return temPermissaoEmissaoDocumentoCobranca;
	}
	
	
	/**
	 * Verifica as permiss�es especiais do usu�rio 
	 * 
	 * @author Hugo Amorim
	 * @date 30/12/2009
	 * 
	 * @param permissaoEspecial
	 * @param usuario
	 * @return true or false
	 */
	public boolean verificarPermissaoEspecialAtiva(int permissaoEspecial,
			Usuario usuario) throws ControladorException {

		boolean retorno = false;
		
		FiltroPemissaoEspecial filtroPemissaoEspecial = new FiltroPemissaoEspecial();
		
		filtroPemissaoEspecial.adicionarParametro(
				new ParametroSimples(FiltroPemissaoEspecial.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroPemissaoEspecial.adicionarParametro(
				new ParametroSimples(FiltroPemissaoEspecial.ID, permissaoEspecial));
		
		Collection<PermissaoEspecial> colecaoPermissaoEspecial = 
			getControladorUtil().pesquisar(filtroPemissaoEspecial,
					PermissaoEspecial.class.getName());
		
		PermissaoEspecial permissaoEspecialPesquisa = 
			(PermissaoEspecial) Util.retonarObjetoDeColecao(colecaoPermissaoEspecial);
		
		//CASO N�O EXISTE A PERMISS�O NA BASE
		//COM INDICADOR DE USO ATIVO
		//RETORNAR TRUE
		if(permissaoEspecialPesquisa!=null){
		
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial
				.adicionarParametro(new ParametroSimples(
						FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID, usuario
								.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(
				FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID,
				permissaoEspecialPesquisa.getId()));
		
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial");

		Collection<UsuarioPermissaoEspecial> colecaoUsuarioPermissaoEspecial = 
			getControladorUtil().pesquisar(filtroUsuarioPemissaoEspecial,
				UsuarioPermissaoEspecial.class.getName());
		
			for (Iterator iterator = colecaoUsuarioPermissaoEspecial.iterator(); iterator.hasNext();) {
				UsuarioPermissaoEspecial usuarioPermissaoEspecial = 
					(UsuarioPermissaoEspecial) iterator.next();
			
				//VERIFICA SE POSSUI NA TABELA A PERMISS�O 
				//COM INDICADOR DE USO IGUAL A ATIVO
				if(usuarioPermissaoEspecial.getUsuario().getId().toString().equals(usuario.getId().toString())
						&& usuarioPermissaoEspecial.getPermissaoEspecial().getId().toString().equals(permissaoEspecialPesquisa.getId().toString())){
					retorno = true;
				}
			
			}		
		}else{	
			retorno = true;			
		}

		return retorno;

	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 23/09/2010
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaSemVerificarConsumoEsgoto(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoRetificarContaSemVerificarConsumoEsgoto = this
				.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_SEM_VERIFICAR_CONSUMO_ESGOTO,usuario);

		return temPermissaoRetificarContaSemVerificarConsumoEsgoto;
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 27/06/2012
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoUtilizarGuiaPagamentoComoEntradaParcelamento(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoUsarGuiaPagamentoComoEntradaParcelamento = this
				.verificarPermissaoEspecial(PermissaoEspecial.UTILIZAR_GUIA_PAG_COMO_ENTRADA_PARCELAMENTO,usuario);

		return temPermissaoUsarGuiaPagamentoComoEntradaParcelamento;
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 10/07/2013
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarVencimentoContratoParcelamentoCliente(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoAlterarVencimentoContratoParcelamentoCliente = this
				.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_VENCIMENTO_CONTRATO_PARCELAMENTO_CLIENTE,usuario);

		return temPermissaoAlterarVencimentoContratoParcelamentoCliente;
	}
}