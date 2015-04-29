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

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ControladorException;

/**
 * Defini��o da l�gica de neg�cio do Session Bean de
 * ControladorPermissaoEspecial
 * 
 * @author Rodrigo Silveira
 * @created 07/11/2006
 */

public interface ControladorPermissaoEspecialLocal
		extends
			javax.ejb.EJBLocalObject {

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
	public boolean verificarPermissaoEspecial(
			int permissaoEspecial, Usuario usuario)
			throws ControladorException;
	
	
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
			Usuario usuario,Object objeto) throws ControladorException;
	
	/**
	 * Verifica permiss�o especial para aceitar um valor de entrada menor q o valor m�nimo de entrada 
	 * na terceira p�gina de Efetuar Parcelamento D�bitos
	 * 
	 * @author Vivianne Sousa
	 * @date 28/11/2006
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoValMinimoEntrada(Usuario usuario) 
		throws ControladorException;
	
	/**
	 * Verifica permiss�o especial para atualizar um cliente que seja usu�rio da tarifa social
	 * 
	 * @author Rafael Corr�a
	 * @date 16/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarUsuarioTarifaSocial(Usuario usuario) 
		throws ControladorException;
	
	
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
			   Usuario usuario) throws ControladorException ;
	
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
			Usuario usuario) throws ControladorException;
	
	/**
	 * Verifica permiss�o especial para atualizar Imovel com logradouro.municipio
	 * diferente de setorComercial.municipio
	 * 
	 * @author R�mulo Aur�lio
	 * @date 23/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor(
			Usuario usuario) throws ControladorException;
	
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
			Usuario usuario) throws ControladorException;

	
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
			Usuario usuario) throws ControladorException;

	
	
	/**
	  * Verifica permiss�o especial para N�O gerar d�bito no informar retorno OS fiscaliza��o
	  * 
	  * @author Raphael Rossiter
	  * @date 03/03/2007
	  * 
	  * @param usuario
	  */
	 public boolean verificarPermissaoGeracaoDebitoOSFiscalizacao(
				   Usuario usuario) throws ControladorException ;
	 
	 
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
			Usuario usuario)throws ControladorException ;
	
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
			Usuario usuario) throws ControladorException ;

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
			Usuario usuario) throws ControladorException ;
	
	/**
	 * Manter conta - Alterar vencimento sem ra
	 * 
	 * @author Ana Maria
	 * @date 26/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarVencimentoSemRa(Usuario usuario) 
		throws ControladorException;
	
	
	/**
	 * Inserir conta - inserir conta sem cronograma de faturamento e sem atividade efetuar leitura
	 * 
	 * @author Raphael Rossiter
	 * @date 08/05/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirContaFaturamentoAntecipado(Usuario usuario) throws ControladorException ;
	
	/**
	 * Inseir D�bito a cobrar sem RA
	 * 
	 * @author Ana Maria
	 * @date 23/05/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarSemRa(Usuario usuario) throws ControladorException;
	
	/**
	 * Atualizar Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarNomeCliente(Usuario usuario) throws ControladorException ;
	
	/**
	 * Incluir Devolu��o
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoIcluirDevolucaoMaiorValorMaximo(Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0194] Cr�dito a Realizar Permite inserir um cr�dito a realizar
	 * 
	 * @author S�vio Luiz
	 * @since 21/08/2006
	 */
	public boolean verificarPermissaoIcluirCreditoARealizarValorMaximo(Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0194] Cr�dito a Realizar Permite inserir um cr�dito a realizar
	 * 
	 * @author S�vio Luiz
	 * @since 21/08/2006
	 */
	public boolean verificarPermissaoIcluirCreditoARealizarQuantidadeParcelasMaximo(Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0630] Solicitar Emiss�o do Extrato de Debitos
	 * 
	 * @author Vivianne Sousa
	 * @since 30/08/2007
	 */
	public boolean verificarPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto(Usuario usuario) throws ControladorException ;

	/**
	 * [UC0630] Solicitar Emiss�o do Extrato de Debitos
	 * 
	 * @author Vivianne Sousa
	 * @since 30/08/2007
	 */
	public boolean verificarPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos(Usuario usuario) throws ControladorException; 

	/**
	 * [UC0XXX] Consultar D�bitos
	 * 
	 * @author Rafael Corr�a
	 * @since 13/09/2007
	 */
	public boolean verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos(Usuario usuario) throws ControladorException;

	
	/**
	 * Inserir d�bito a cobrar - inserir debito a cobrar independente da situacao da ligacao de agua e esgoto do
	 * imovel 
	 * 
	 * @author Raphael Rossiter
	 * @date 03/10/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarImovelSituacao(Usuario usuario) 
	throws ControladorException;
	
	/**
	 * Reiniciar um batch
	 * 
	 * @author Rafael Corr�a
	 * @date 06/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoReiniciarBatch(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite retificar uma conta sem RA 
	 * 
	 * @author Raphael Rossiter
	 * @date 09/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaSemRA(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite excluir debito a cobrar 
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoExcluirDebitoACobrar(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite Gerar OS Seletivas de Hidrometro
	 * 
	 * @author Ivan S�rgio
	 * @date 06/12/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoGerarOSSeletivasHidrometro(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite Cancelar a Conta Sem RA 
	 * 
	 * @author Ivan S�rgio
	 * @date 28/01/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoCancelarContaSemRA(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite Atualizar os Dados da Fiscalizacao 
	 * 
	 * @author Ivan S�rgio
	 * @date 10/04/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarDadosFiscalizacao(Usuario usuario) throws ControladorException;
	
	
	/**
	 * Permite Visualizar Dia de Vencimento da Conta no Cliente
	 * 
	 * @author R�mulo Aur�lio
	 * @date 07/05/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoVisualizarDiaVencimentoContaCliente(Usuario usuario) throws ControladorException ;
	
	/**
	 * Permite Desfazer a Fiscalizacao do Boletim de OS Concluida 
	 * 
	 * @author Ivan S�rgio
	 * @date 02/05/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoDesfazerFiscalizacaoBoletimOSConcluida(
			Usuario usuario) throws ControladorException;
    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @since 22/05/2008
     */
    public boolean verificarPermissaoInserirImovelComPerfilCorporativo(Usuario usuario) throws ControladorException; 
  
  
    /**
     * [UC0014] Manter Imovel
     * 
     * @author Vivianne Sousa
     * @since 22/05/2008
     */
    public boolean verificarPermissaoAlterarPerfilCorporativoImovel(Usuario usuario) throws ControladorException;

    /**
     * [UC457]Encerrar Ordem Servico.
     * 
     * @author Yara Taciane
     * @since 22/05/2008
     */
	public boolean verificarPermissaoInformarDataEncOSAnteriorDataCorrente(Usuario usuario) throws ControladorException;
	
    /**
     * [UC399] Inserir Tipo de Solicita��o com Especifica��es
     * 
     * @author Rafael Corr�a
     * @since 12/08/2008
     */
    public boolean verificarPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao(Usuario usuario) throws ControladorException;
    
    /**
     * @author Vivianne Sousa
     * @since 15/09/2008
     */
    public boolean verificarPermissaoAlterarValidadeExtratoDebito(Usuario usuario) throws ControladorException;


    /**
	 * Verifica permiss�o especial para habilitar ou n�o o formul�rio
	 * 
	 * @author Arthur Carvalho
	 * @date 13/10/2008
	 */
	public boolean verificarPermissaoValidarAcrescimosImpontualidade(Usuario usuario)
			throws ControladorException;
	
	/**
	 * Permite emitir segunda via de uma conta sem documento de CPF OU CNPJ cadastrados no sistema. Esse situa��o
	 * ocorrer� para as empresas que optarem pela obrigatoriedade do CPF OU CNPJ informado. 
	 * 
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmitir2ViaSemDocumentoValido(Usuario usuario) throws ControladorException;
	
    /**
	 * Permite emitir certid�o negativa mesmo que o cliente tenha um superior. 
	 * 
	 * @author Rafael Corr�a
	 * @date 12/11/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmitirCertidaoNegativaComClienteSuperior(Usuario usuario) throws ControladorException;
	
	
	/**
	 * Permite retificar um conjunto de contas a partir do manter conta. 
	 * 
	 * @author Raphael Rossiter
	 * @date 03/11/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarConjuntoConta(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite efetuar supress�o de liga��o de �gua. 
	 * 
	 * @author Vivianne Sousa
	 * @date 09/03/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEfetuarSupressaoAgua(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite alterar situa��o de liga��o para imovel com debitos . 
	 * 
	 * @author Vivianne Sousa
	 * @date 18/03/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarSituacaoLigacaoParaImovelComDebito(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite retificar conta do im�vel com o perfil bloqueado. 
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaImovelPefilBloqueado(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite retificar conta do im�vel com o perfil bloqueado. 
	 * 
	 * @author S�vio Luiz
	 * @date 15/09/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmissaoDocumentoCobranca(Usuario usuario) throws ControladorException;
	
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
			Usuario usuario) throws ControladorException;

	/**
	 * @author Vivianne Sousa
	 * @date 23/09/2010
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaSemVerificarConsumoEsgoto(
			Usuario usuario) throws ControladorException ;
	
	/**
	 * @author Vivianne Sousa
	 * @date 27/06/2012
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoUtilizarGuiaPagamentoComoEntradaParcelamento(
			Usuario usuario) throws ControladorException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 10/07/2013
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarVencimentoContratoParcelamentoCliente(
			Usuario usuario) throws ControladorException;
}