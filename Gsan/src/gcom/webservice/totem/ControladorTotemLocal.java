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
package gcom.webservice.totem;

import gcom.util.ControladorException;
import gcom.webservice.totem.helpers.RetornoGerarExtratoWebService;
import gcom.webservice.totem.helpers.RetornoInserirDebitoWebService;
import gcom.webservice.totem.helpers.RetornoListarContasWebService;
import gcom.webservice.totem.helpers.RetornoListarDebitosWebService;
import gcom.webservice.totem.helpers.RetornoObterDadosContaWebService;
import gcom.webservice.totem.helpers.RetornoVerificarImovelWebService;

import javax.ejb.EJBLocalObject;

/**
 * Declara��o p�blica de servi�os do Session Bean do
 * ControladorTotem
 * 
 * @author Bruno Barros
 * @date 09/12/2015
 */
public interface ControladorTotemLocal extends EJBLocalObject {
	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este m�todo insere um debito a cobrar referente segunda via de conta
	 * para um determinado im�vel. A gera��o est� atrelada ao M�s / Ano de Faturamento
	 * atual de Sistema Parametros.
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param idImovel - Id do im�vel ao qual a cobranca ta taxa de segunda via ser� atribuida
	 * @param key - Chave passada para validar a chamada de todas as requisi��es do totem
	 * 
	 * @return Objeto serializado com as informa��es para o retorno do webService
	 * 
	 * @throws ControladorException
	 */	
	public RetornoInserirDebitoWebService gerarDebitoACobrarTaxaEmissaoContaTotem( String idImovel, String key ) throws ControladorException;	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este m�todo pesquisa os dados de um im�vel baseado na chave passada 
	 * como par�metro para o servlet. A chave pode ser uma matricula, um
	 * cpf ou cpnj. O m�todo ir� identificar pelo tamanho da chave para 
	 * saber o tipo de pesquisa. Se for inferior a 10, por matricula, igual a
	 * 11 por cpf, igual 1 14 por CPNJ
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param chavePesquisa - String contendo uma matricula, um cpf ou um cnpj
	 * @param key - Chave passada para validar a chamada de todas as requisi��es do totem
	 * 
	 * @return Objeto serializado com as informa��es para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */	
	public RetornoVerificarImovelWebService verificarImovelTotem( String chavePesquisa, String key ) throws ControladorException;
	

	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este m�todo lista todas as contas em aberto para um determinado im�vel
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do Imovel para pesquisa dos d�bitos
	 * @param key - Chave passada para validar a chamada de todas as requisi��es do totem
	 * 
	 * @return Objeto serializado com as informa��es para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */	
	public RetornoListarContasWebService listarContasTotem( String matricula, String key ) throws ControladorException;
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este m�todo lista todos os d�bitos de um determinado im�vel
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do Imovel para pesquisa dos d�bitos
	 * @param key - Chave passada para validar a chamada de todas as requisi��es do totem
	 * 
	 * @return Objeto serializado com as informa��es para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */		
	public RetornoListarDebitosWebService listarDebitosTotem( String matricula, String key ) throws ControladorException;	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este m�todo pesquisa os dados de uma conta. O m�todo pesquisar� tanto
	 * em conta como em conta hist�rico
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param idConta - id da conta que ser� pesquisada.  
	 * @param key - Chave passada para validar a chamada de todas as requisi��es do totem
	 * 
	 * @return Objeto serializado com as informa��es para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */			
	public RetornoObterDadosContaWebService obterDadosContaTotem( String idConta, String key ) throws ControladorException;
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este m�todo gera um extrato contendo todos os d�bitos
	 * do im�vel. No caso do totem sempre ser� cobrado a taxa de emiss�o
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do im�vel que ter� o extrato de d�bitos gerado
	 * @param key - Chave passada para validar a chamada de todas as requisi��es do totem
	 * @param String - ids das contas que ir�o compor o d�bito
	 * @param String - ids das debitos que ir�o compor o d�bito
	 * @param String - indicador que diz se os d�bitos ser�o atualizados
	 * @param String - indicador que diz se ser� cobrado pela emiss�o do extrato 
	 * 
	 * @return Objeto serializado com as informa��es para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */
	public RetornoGerarExtratoWebService gerarExtratoTotem( 
			String matricula, 
			String key, 
			String contas, 
			String debitos, 
			String icAcrescimosImpontualidade,
			String icCobrarEmissao ) throws ControladorException;
}
