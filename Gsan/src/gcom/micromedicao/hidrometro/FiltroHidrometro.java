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
package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 5 de Setembro de 2005
 */
public class FiltroHidrometro extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroHidrometro object
	 */
	public FiltroHidrometro() {
	}

	/**
	 * Construtor da classe FiltroHidrometroCapacidade
	 * 
	 * @param campoOrderBy
	 *            Descri��o do par�metro
	 */
	public FiltroHidrometro(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String NUMERO_HIDROMETRO = "numero";

	/**
	 * Description of the Field
	 */
	public final static String DATA_AQUISICAO = "dataAquisicao";

	/**
	 * Description of the Field
	 */
	public final static String ANO_FABRICACAO = "anoFabricacao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_OPERACIONAL = "indicadorOperacional";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MOVIMENTACAO_ID = "hidrometroMovimentacao.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CLASSE_METROLOGICA_ID = "hidrometroClasseMetrologica.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MARCA_ID = "hidrometroMarca.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_DIAMETRO_ID = "hidrometroDiametro.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CAPACIDADE_ID = "hidrometroCapacidade.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_TIPO_ID = "hidrometroTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_RELOJOARIA_ID = "hidrometroRelojoaria.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_LOCAL_ARMAZENAGEM_ID = "hidrometroLocalArmazenagem.id";

	public final static String HIDROMETRO_SITUACAO_ID = "hidrometroSituacao.id";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_DATA = "hidrometroMovimentado.hidrometroMovimentacao.data";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HORA = "hidrometroMovimentado.hidrometroMovimentacao.hora";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_DESTINO_DESCRICAO = "hidrometroMovimentado.hidrometroMovimentacao.hidrometroLocalArmazenagemDestino.descricao";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_ORIGEM_DESCRICAO = "hidrometroMovimentado.hidrometroMovimentacao.hidrometroLocalArmazenagemOrigem.descricao";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_TIPO = "hidrometroTipo";
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_SITUACAO = "hidrometroSituacao";
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MARCA = "hidrometroMarca";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_DIAMETRO = "hidrometroDiametro";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CAPACIDADE = "hidrometroCapacidade";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MOTIVO_BAIXA = "hidrometroMotivoBaixa";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_LOCAL_ARMAZENAGEM = "hidrometroLocalArmazenagem";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CLASSE_METROLOGICA = "hidrometroClasseMetrologica";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_TRANSICAO = "vazaoTransicao";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_NOMINAL = "vazaoNominal";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_MINIMA = "vazaoMinima";
	
	/**
	 * Description of the Field
	 */
	public final static String NOTA_FISCAL = "notaFiscal";
	
	/**
	 * Description of the Field
	 */
	public final static String TEMPO_GARANTIA_ANOS = "tempoGarantiaAnos";
	
	public final static String HIDROMETRO_FATOR_CORRECAO_ID = "hidrometroFatorCorrecao.id";

	public final static String HIDROMETRO_CLASSE_PRESSAO_ID = "hidrometroClassePressao.id";
	
	public final static String INDICADOR_MACROMEDIDOR = "indicadorMacromedidor";
	
	public final static String INDICADOR_FINALIDADE = "indicadorFinalidade";

}
