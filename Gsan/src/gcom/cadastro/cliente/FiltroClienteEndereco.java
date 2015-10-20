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
package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os crit�rios de busca de um cliente endereco
 * 
 * @author S�vio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroClienteEndereco extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroClienteEndereco
	 */
	public FiltroClienteEndereco() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroClienteEndereco(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String CNPJ = "cliente.cnpj";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ID = "cliente.id";

	/**
	 * Description of the Field
	 */
	public final static String CPF = "cliente.cpf";

	/**
	 * Description of the Field
	 */
	public final static String CEP_CODIGO = "logradouroCep.cep.codigo";
	
	/**
	 * Description of the Field
	 */
	public final static String RG = "cliente.rg";

	/**
	 * Description of the Field
	 */
	public final static String NOME = "cliente.nome";

	/**
	 * Description of the Field
	 */
	public final static String TIPOCLIENTE_ID = "cliente.clienteTipo.id";

	/**
	 * Description of the Field
	 */
	public final static String TIPOCLIENTE_DESCRICAO = "cliente.clienteTipo.descricao";

	/**
	 * C�digo da unidade de medi��o
	 */
	public final static String BAIRRO_ID = "logradouroBairro.bairro.id";
	
	public final static String BAIRRO_MUNICIPIO_UNIDADE_FEDERACAO = "logradouroBairro.bairro.municipio.unidadeFederacao";

	/**
	 * C�digo da unidade de medi��o
	 */
	public final static String BAIRRO_CODIGO = "logradouroBairro.bairro.codigo";

	/**
	 * C�digo da unidade de medi��o
	 */
	public final static String MUNICIPIO_ID = "logradouroCep.logradouro.municipio.id";
	
	/**
	 * C�digo da unidade de medi��o
	 */
	public final static String MUNICIPIO_ID_CEP = "logradouroCep.cep.municipio.id";

	/**
	 * Description of the Field
	 */
	public final static String LOGRADOURO = "logradouroCep.logradouro.nome";
	
	/**
	 * Description of the Field
	 */
	public final static String LOGRADOURO_ID = "logradouroCep.logradouro.id";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "cliente.indicadorUso";

	public final static String LOGRADOURO_MUNICIPIO = "logradouroCep.logradouro.municipio";
	
	public final static String CEP_MUNICIPIO = "logradouroCep.cep.municipio";
	
	/**
	 * Description of the Field
	 */
	public final static String NOME_ABREVIADO = "cliente.nomeAbreviado";

	/**
	 * Description of the Field
	 */
	public final static String PROFISSAO = "cliente.profissao.id";

	/**
	 * Description of the Field
	 */
	public final static String EMAIL = "cliente.email";

	/**
	 * Description of the Field
	 */
	public final static String RAMO_ATIVIDADE = "cliente.ramoAtividade";

	/**
	 * Description of the Field
	 */
	public final static String DATA_EMISSAO_RG = "cliente.dataEmissaoRg";

	/**
	 * Description of the Field
	 */
	public final static String ORGAO_EXPEDITOR_RG = "cliente.orgaoExpedidorRg.id";

	/**
	 * Description of the Field
	 */
	public final static String DATA_NASCIMENTO = "cliente.dataNascimento";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_RESPONSAVEL_ID = "cliente.cliente.id";

	/**
	 * Description of the Field
	 */
	public final static String SEXO = "cliente.pessoaSexo.id";

	public final static String CLIENTE_CLIENTE_IMOVEL_IMOVEL_ID = "clienteImovel.imovel.id";
	
	public final static String INDICADOR_CORRESPONDENCIA = "indicadorEnderecoCorrespondencia";
	
	public final static String ID = "id";
	
	public final static String LOGRADOURO_CEP = "logradouroCep";
	
	public final static String LOGRADOURO_CEP_ID = "logradouroCep.id";
	
	public final static String ENDERECO_TIPO = "enderecoTipo";
	
	public final static String LOGRADOURO_BAIRRO = "logradouroBairro.bairro";
	
	public final static String LOGRADOURO_BAIRRO_ID = "logradouroBairro.bairro.id";
	
	public final static String ENDERECO_REFERENCIA = "enderecoReferencia";
	
	public final static String CLIENTE = "cliente";
	
	public final static String LOGRADOURO_TIPO = "logradouroCep.logradouro.logradouroTipo";
	
	public final static String LOGRADOURO_TITULO = "logradouroCep.logradouro.logradouroTitulo";
	
	public final static String CEP = "logradouroCep.cep";
	
}
