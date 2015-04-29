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
package gsan.faturamento.conta;

import gsan.util.filtro.Filtro;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConta extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroConta() {
    }
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";

    /**
     * Description of the Field
     */
    public final static String REFERENCIA = "referencia";
    
    /**
     * Description of the Field
     */
    public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoAtual.id";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA_CONTABIL = "referenciaContabil";

    public final static String DATA_VENCIMENTO = "dataVencimentoConta";
    
    public final static String DATA_EMISSAO = "dataEmissao";
    
    public final static String DATA_REVISAO = "dataRevisao";
    
    public final static String CONTA_MOTIVO_REVISAO_ID = "contaMotivoRevisao.id";
    
    public final static String CONTA_MOTIVO_CANCELAMENTO = "contaMotivoCancelamento";
    
    public final static String CONTA_MOTIVO_INCLUSAO = "contaMotivoInclusao";
    
    public final static String ID_CONTA_MOTIVO_INCLUSAO = "contaMotivoInclusao.id";
    
    public final static String DOCUMENTO_TIPO = "documentoTipo";
    
    public final static String CONTA_BANCARIA = "contaBancaria";
    
    public final static String FATURAMENTO_TIPO = "faturamentoTipo";
    
    public final static String NOME_CONTA = "nomeConta";
    
    public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
    
    public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao";
    
    public final static String LIGACAO_ESGOTO_SITUACAO = "ligacaoEsgotoSituacao";
    
    public final static String IMOVEL = "imovel";
    
    public final static String CONSUMO_TARIFA = "consumoTarifa";
    
    public final static String IMOVEL_PERFIL = "imovelPerfil";
    
    public final static String LOCALIDADE = "localidade";
    
    public final static String MOTIVO_NAO_ENTREGA_DOCUMENTO = "motivoNaoEntregaDocumento";
    
    public final static String CONTA_MOTIVO_REVISAO = "contaMotivoRevisao";
    
    public final static String CONTA_MOTIVO_RETIFICACAO = "contaMotivoRetificacao";
    
    public final static String DEBITO_CREDITO_SITUACAO_ATUAL = "debitoCreditoSituacaoAtual";
    
    public final static String DEBITO_CREDITO_SITUACAO_ANTERIOR = "debitoCreditoSituacaoAnterior";
    
    public final static String CONTA_GERAL = "contaGeral";
    
    public final static String NUMERO_BOLETO = "numeroBoleto";
    
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
    
    public final static String PARCELAMENTO_ID = "parcelamento.id";
    
    public final static String PARCELAMENTO = "parcelamento";
    
    public final static String CONTA_CATEGORIA = "contaCategorias";
       
    public final static String CONTA_CATEGORIA_CATEGORIA = "contaCategorias.comp_id.categoria";
    
    public final static String CONTA_CATEGORIA_SUBCATEGORIA = "contaCategorias.comp_id.subcategoria";
    
    public final static String DEBITOS_COBRADOS = "debitoCobrados";

    public final static String CREDITOS_REALIZADOS = "creditoRealizados";
    
    public final static String USUARIO = "usuario";
    
    public final static String DATA_ENVIO_EMAIL_CONTA = "dataEnvioEmailConta";
    
    public final static String ROTA = "rota";
    
    public final static String CONTA_MOTIVO_REVISAO_PERMISSAO_ESPECIAL = "contaMotivoRevisao.permissaoEspecial";
    
    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descri��o do par�metro
     */
    public FiltroConta(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

