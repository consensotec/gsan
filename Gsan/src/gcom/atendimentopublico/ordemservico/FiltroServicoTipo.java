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
package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * FiltroUnidadeOrganizacional 
 *
 * @author S�vio Luiz
 * @date 27/07/2006
 */
public class FiltroServicoTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroUnidadeOrganizacional object
     */
    public FiltroServicoTipo() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroServicoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id da Unidade
     */
    public final static String ID = "id";
    
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    public final static String DESCRICAO = "descricao";
    public final static String DSABREVIADA = "descricaoAbreviada";
    public final static String VALORSERVICO = "valor";
    public final static String INDICADORPAVIMENTO = "indicadorPavimento";
    public final static String INDICADORATUALIZARCOMERCIAL = "indicadorAtualizaComercial";
    public final static String INDICADORTERCEIRIZADO = "indicadorTerceirizado";
    public final static String CODIGOSERVICOTIPO = "codigoServicoTipo";
    public final static String TEMPOMEDIOEXECUCAO = "tempoMedioExecucao";
    public final static String ULTIMAALTERACAO = "ultimaAlteracao";
    public final static String CREDITOTIPO = "creditoTipo.id";
    public final static String SERVICO_TIPO_REFERENCIA = "servicoTipoReferencia"; 
    public final static String SERVICOPERFILTIPO = "servicoPerfilTipo.id"; 
    public final static String SERVICOTIPOSUBGRUPO = "servicoTipoSubgrupo.id"; 
    public final static String SERVICOTIPOPRIORIDADE = "servicoTipoPrioridade.id"; 
    public final static String DEBITOTIPO = "debitoTipo.id"; 
    public final static String ATIVIDADETIPOSERVICO = "debitoTipo.id"; 
    public final static String INDICADOR_USO = "indicadorUso";
    public final static String INDICADOR_DIAGNOSTICO_SERVICO_TIPO_REF = "servicoTipoReferencia.indicadorDiagnostico";
    public final static String INDICADOR_FISCALIZACAO_SERVICO_TIPO_REF = "servicoTipoReferencia.indicadorFiscalizacao";
    public final static String CONSTANTE_FUNCIONALIDADE_TIPO_SERVICO = "constanteFuncionalidadeTipoServico";
    public final static String INDICADOR_EMPRESA_COBRANCA = "indicadorEmpresaCobranca";
    public final static String INDICADOR_SERVICO_ORDEM_SELETIVA = "indicadorServicoOrdemSeletiva";
    public static final String ID_OS_PROGRAMA_CALIBRAGEM = "programaCalibragem.id";
    
    public static final String OS_PROGRAMA_CALIBRAGEM = "programaCalibragem";
    
    public static final String INDICADOR_GERAR_OS_INSP_ANORMALIDADE = "indicadorInspecaoAnormalidade";
    public static final String INDICADOR_PROGRAMACAO_AUTOMATICA = "indicadorProgramacaoAutomatica";
    
    public static final String INDICADOR_CORRECAO_ANORMALIDADE = "indicadorCorrecaoAnormalidade";
    public final static String INDICADOR_SERVICO_COBRANCA = "indicadorServicoCobranca";
}
