/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.micromedicao.consumo;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConsumoAnormalidadeAcao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroConsumoAnormalidadeAcao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLigacaoAguaSituacao
     */
    public FiltroConsumoAnormalidadeAcao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Description of the Field
     */
    public final static String CONSUMO_ANORMALIDADE = "consumoAnormalidade";
    
    /**
     * Description of the Field
     */
    public final static String CATEGORIA = "categoria";
   
    /**
     * Description of the Field
     */
    public final static String IMOVEL_PERFIL = "imovelPerfil";
  
  	/**
  	 * Description of the Field
  	 */
    public final static String CONSUMO_COBRAR_MES1 = "leituraAnormalidadeConsumoMes1";
   
    /**
  	 * Description of the Field
  	 */
    public final static String CONSUMO_COBRAR_MES2 = "leituraAnormalidadeConsumoMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String CONSUMO_COBRAR_MES3 = "leituraAnormalidadeConsumoMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String FATOR_CONSUMO_CALCULO_MES1 = "numerofatorConsumoMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String FATOR_CONSUMO_CALCULO_MES2 = "numerofatorConsumoMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String FATOR_CONSUMO_CALCULO_MES3 = "numerofatorConsumoMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String INDICADOR_GERACAO_CARTA_MES1 = "indicadorGeracaoCartaMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String INDICADOR_GERACAO_CARTA_MES2 = "indicadorGeracaoCartaMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String INDICADOR_GERACAO_CARTA_MES3 = "indicadorGeracaoCartaMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SERVICO_TIPO_MES1 = "servicoTipoMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SERVICO_TIPO_MES2 = "servicoTipoMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SERVICO_TIPO_MES3 = "servicoTipoMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_MES1 = "solicitacaoTipoEspecificacaoMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_MES2 = "solicitacaoTipoEspecificacaoMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_MES3 = "solicitacaoTipoEspecificacaoMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String MENSAGEM_CONTA_MES1 = "descricaoContaMensagemMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String MENSAGEM_CONTA_MES2 = "descricaoContaMensagemMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String MENSAGEM_CONTA_MES3 = "descricaoContaMensagemMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String CONSUMO_ANORMALIDADE_ID = "consumoAnormalidade.id";
    
    /**
  	 * Description of the Field
  	 */   
    public final static String MOTIVO_REVISAO_MES1 = "contaMotivoRevisaoMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String MOTIVO_REVISAO_MES2 = "contaMotivoRevisaoMes2";

    /**
  	 * Description of the Field
  	 */
    public final static String MOTIVO_REVISAO_MES3 = "contaMotivoRevisaoMes3";

    public final static String INDICADOR_COBRANCA_CONSUMO_NORMAL = "indicadorCobrancaConsumoNormal";
}
