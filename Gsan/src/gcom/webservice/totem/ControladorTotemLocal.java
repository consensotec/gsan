/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
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
 * Declaração pública de serviços do Session Bean do
 * ControladorTotem
 * 
 * @author Bruno Barros
 * @date 09/12/2015
 */
public interface ControladorTotemLocal extends EJBLocalObject {
	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método insere um debito a cobrar referente segunda via de conta
	 * para um determinado imóvel. A geração está atrelada ao Mês / Ano de Faturamento
	 * atual de Sistema Parametros.
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param idImovel - Id do imóvel ao qual a cobranca ta taxa de segunda via será atribuida
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService
	 * 
	 * @throws ControladorException
	 */	
	public RetornoInserirDebitoWebService gerarDebitoACobrarTaxaEmissaoContaTotem( String idImovel, String key ) throws ControladorException;	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método pesquisa os dados de um imóvel baseado na chave passada 
	 * como parâmetro para o servlet. A chave pode ser uma matricula, um
	 * cpf ou cpnj. O método irá identificar pelo tamanho da chave para 
	 * saber o tipo de pesquisa. Se for inferior a 10, por matricula, igual a
	 * 11 por cpf, igual 1 14 por CPNJ
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param chavePesquisa - String contendo uma matricula, um cpf ou um cnpj
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */	
	public RetornoVerificarImovelWebService verificarImovelTotem( String chavePesquisa, String key ) throws ControladorException;
	

	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método lista todas as contas em aberto para um determinado imóvel
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do Imovel para pesquisa dos débitos
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */	
	public RetornoListarContasWebService listarContasTotem( String matricula, String key ) throws ControladorException;
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método lista todos os débitos de um determinado imóvel
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do Imovel para pesquisa dos débitos
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */		
	public RetornoListarDebitosWebService listarDebitosTotem( String matricula, String key ) throws ControladorException;	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método pesquisa os dados de uma conta. O método pesquisará tanto
	 * em conta como em conta histórico
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param idConta - id da conta que será pesquisada.  
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */			
	public RetornoObterDadosContaWebService obterDadosContaTotem( String idConta, String key ) throws ControladorException;
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método gera um extrato contendo todos os débitos
	 * do imóvel. No caso do totem sempre será cobrado a taxa de emissão
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do imóvel que terá o extrato de débitos gerado
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * @param String - ids das contas que irão compor o débito
	 * @param String - ids das debitos que irão compor o débito
	 * @param String - indicador que diz se os débitos serão atualizados
	 * @param String - indicador que diz se será cobrado pela emissão do extrato 
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
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
