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
package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os crit�rios de busca do usuario alteracao
 * 
 * @author Thiago Toscano
 */
public class FiltroOperacaoEfetuada extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroOperacaoEfetuada() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroOperacaoEfetuada(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * C�digo 
     */
    public final static String ID = "id";

    public final static String OPERACAO = "operacao";

    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    public final static String OPERACAO_NOME_ARGUMENTO = OPERACAO + ".argumentoPesquisa";
    
    public final static String OPERACAO_ARGUMENTO_PESQUISA_TABELA = OPERACAO + "." + FiltroOperacao.ARGUMENTO_PESQUISA_TABELA;    
    
    public final static String OPERACAO_FUNCIONALIDADE_ID = OPERACAO + ".funcionalidade.id";

    /**
     * C�digo 
     */

    public final static String OPERACAO_ID = "operacao.id";
    
    public final static String USUARIO_ALTERACAO = "ua";
    
    public final static String TABELA_LINHA_ALTERACAO = "tla";

    public final static String TABELA_LINHA_ALTERACAO_ID1 = "tla.id1";

    public final static String TABELA_LINHA_COLUNA_ALTERACAO = "tblca";
       
    
    public final static String USUARIO = USUARIO_ALTERACAO + ".usuario";
    
    public final static String USUARIO_ID = USUARIO_ALTERACAO + ".usuario.id";

    public final static String USUARIO_TIPO = USUARIO_ALTERACAO + ".usuario.usuarioTipo";
    
    public final static String USUARIO_ACAO = USUARIO_ALTERACAO + ".usuarioAcao";

    public final static String USUARIO_ALTERACAO_ID = USUARIO_ALTERACAO + ".id";

    
    public final static String TABELA_ID = TABELA_LINHA_ALTERACAO + ".tabela.id";
    
    public final static String TABELA_COLUNA_ID= TABELA_LINHA_COLUNA_ALTERACAO + ".tabelaColuna.id";
    
    public final static String ARGUMENTO_VALOR = "argumentoValor";

    public final static String UNIDADE_NEGOCIO = ".usuario.unidadeNegocio";
    
}