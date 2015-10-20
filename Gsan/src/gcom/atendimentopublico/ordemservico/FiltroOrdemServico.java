/**
 * 
 */
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
 * Filtar da Ordem de Servi�o
 * @author Rafael Santos
 * @since 09/1/2006
 *
 */
public class FiltroOrdemServico extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroOrdemServico() {
	}

	/**
	 * 
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroOrdemServico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	
		/**
	 * Description of the Field
	 */
	//este campo ainda n�o foi defenido (s� na Itera��o 7)
	public final static String DATA_ENCERRAMENTO = "dataEncerramento";
	
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	
	public final static String ID_IMOVEL = "imovel.id";
    
    public final static String IMOVEL = "imovel";
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO = "atendimentoMotivoEncerramento";
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO_IC_EXECUCAO = "atendimentoMotivoEncerramento.indicadorExecucao";
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID = "atendimentoMotivoEncerramento.id";
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
	
	public final static String DEBITO_TIPO = "servicoTipo.debitoTipo";
	
	public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "registroAtendimento.solicitacaoTipoEspecificacao";
	
	public final static String CREDITO_TIPO = "servicoTipo.creditoTipo";	
	
	public final static String SERVICO_TIPO = "servicoTipo";
	
	public final static String DESCRICAO_TIPO = "servicoTipo.descricao";
	
	public final static String SERVICO_TIPO_PRIORIDADE_ATUAL = "servicoTipoPrioridadeAtual";
	
	public final static String OS_REFERIDA_RETORNO_TIPO = "osReferidaRetornoTipo";
	
	public final static String COBRANCA_DOCUMENTO = "cobrancaDocumento";

	public final static String COBRANCA_DOCUMENTO_COBRANCA_ACAO_ATIV_CRONOG_ID = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.id";
	
	public final static String COBRANCA_GRUPO_ID = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo.id";

    public final static String COBRANCA_ACAO_ID = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaAcao.id";
    
    public final static String COBRANCA_ACAO = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaAcao";
    
    public final static String COBRANCA_GRUPO = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo";
    
    public final static String COBRANCA_GRUPO_CRONOGRAMA_MES_MES_ANO = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.anoMesReferencia";
	
	public final static String UNIDADE_ORGANIZACIONAL_ATUAL = "unidadeAtual";
	
	public final static String SITUACAO = "situacao";
	
	public final static String ID_SERVICO_TIPO = "servicoTipo.id";
	
	public final static String DATA_FISCALIZACAO_SITUACAO = "dataFiscalizacaoSituacao";
	
	public final static String FISCALIZACAO_SITUACAO_ID = "fiscalizacaoSituacao"; 
	
	
}

