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
 * O filtro serve para armazenar os crit�rios de busca de um cliente
 * 
 * @author S�vio Luiz
 */
public class FiltroCliente extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroCliente() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCliente(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * C�digo da unidade de medi��o
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String CNPJ = "cnpj";

	/**
	 * Description of the Field
	 */
	public final static String CPF = "cpf";

	/**
	 * Description of the Field
	 */
	public final static String RG = "rg";

	/**
	 * Description of the Field
	 */
	public final static String NOME = "nome";
	
	/**
	 * Description of the Field
	 */
	public final static String NOME_ABREVIADO = "nomeAbreviado";
	
	/**
	 * Description of the Field
	 */
	public final static String PROFISSAO_ID = "profissao.id";
	
	/**
	 * Description of the Field
	 */
	public final static String PROFISSAO = "profissao";

	/**
	 * Description of the Field
	 */
	public final static String TIPOCLIENTE_ID = "clienteTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_TIPO = "clienteTipo";

	/**
	 * Description of the Field
	 */
	public final static String TIPOCLIENTE_IPFJ = "clienteTipo.indicadorPessoaFisicaJuridica";

	/**
	 * Description of the Field
	 */
	public final static String ORGAO_EXPEDIDOR_RG = "orgaoExpedidorRg";

	/**
	 * Description of the Field
	 */
	public final static String UNIDADE_FEDERACAO = "unidadeFederacao";
	
	/**
	 * Description of the Field
	 */
	public final static String EMAIL = "email";
	
	/**
	 * Description of the Field
	 */
	public final static String RAMO_ATIVIDADE_ID = "ramoAtividade.id";
	
	/**
	 * Description of the Field
	 */
	public final static String RAMO_ATIVIDADE = "ramoAtividade";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_EMISSAO_RG = "dataEmissaoRg";
	
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_NASCIMENTO = "dataNascimento";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_RESPONSAVEL_ID = "cliente.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_RESPONSAVEL = "cliente";
	
	/**
	 * Description of the Field
	 */
	public final static String SEXO_ID = "pessoaSexo.id";
	
	public final static String SEXO = "pessoaSexo";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ENDERECOS = "clienteEnderecos";
	
	public final static String GERA_ARQUIVO_TEXTO = "indicadorGeraArquivoTexto";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_TELEFONES = "clienteFones";

	public final static String MUNICIPIO_ID = "clienteEndereco.logradouroCep.logradouro.municipio.id";

	public final static String LOGRADOUR_TITULO = "clienteEndereco.logradouroCep.logradouro.logradouroTitulo";
	
	public final static String LOGRADOUR_TIPO = "clienteEndereco.logradouroCep.logradouro.logradouroTipo";

	public final static String LOGRADOURO = "clienteEndereco.logradouroCep.logradouro.id";
	
	public final static String BAIRRO_CODIGO = "clienteEndereco.logradouroBairro.bairro.codigo";
	
	public final static String BAIRRO_ID = "clienteEndereco.logradouroBairro.bairro.id";
	
	public final static String CEP = "clienteEndereco.logradouroCep.cep.codigo";
	
	public final static String IMOVEL_ID = "clienteImovel.imovel.id";
	
	public final static String NOME_MAE = "nomeMae";
	
	public final static String ESFERA_PODER_ID = "clienteTipo.esferaPoder.id";
	
	public final static String ESFERA_PODER = "clienteTipo.esferaPoder";

	
	/**
	 * Description of the Field
	 */
	public final static String ORGAO_EXPEDIDOR_RG_ID = "orgaoExpedidorRg.id";

	/**
	 * Description of the Field
	 */
	public final static String UNIDADE_FEDERACAO_ID = "unidadeFederacao.id";
	
}
