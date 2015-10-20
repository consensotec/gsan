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
package gcom.relatorio.cobranca.spcserasa;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane 
 * @created 23 de Abril de 2008
 * @version 1.0
 */

public class RelatorioNegativadorMovimentoRetornoResumoBean implements RelatorioBean {
	   
	    private String nomeNegativador;
	    
	    private String dataProcessamento;
	   
	    private String horaProcessamento;

	    private String numeroSequencialArquivo;
	    
	    private String numeroRegistros;

		public RelatorioNegativadorMovimentoRetornoResumoBean(String nomeNegativador, String dataProcessamento, String horaProcessamento, String numeroSequencialArquivo, String numeroRegistros) {
			super();
			this.nomeNegativador = nomeNegativador;
			this.dataProcessamento = dataProcessamento;
			this.horaProcessamento = horaProcessamento;
			this.numeroSequencialArquivo = numeroSequencialArquivo;
			this.numeroRegistros = numeroRegistros;
		}

		/**
		 * @return Retorna o campo dataProcessamento.
		 */
		public String getDataProcessamento() {
			return dataProcessamento;
		}

		/**
		 * @param dataProcessamento O dataProcessamento a ser setado.
		 */
		public void setDataProcessamento(String dataProcessamento) {
			this.dataProcessamento = dataProcessamento;
		}

		/**
		 * @return Retorna o campo horaProcessamento.
		 */
		public String getHoraProcessamento() {
			return horaProcessamento;
		}

		/**
		 * @param horaProcessamento O horaProcessamento a ser setado.
		 */
		public void setHoraProcessamento(String horaProcessamento) {
			this.horaProcessamento = horaProcessamento;
		}

		/**
		 * @return Retorna o campo nomeNegativador.
		 */
		public String getNomeNegativador() {
			return nomeNegativador;
		}

		/**
		 * @param nomeNegativador O nomeNegativador a ser setado.
		 */
		public void setNomeNegativador(String nomeNegativador) {
			this.nomeNegativador = nomeNegativador;
		}

		/**
		 * @return Retorna o campo numeroRegistros.
		 */
		public String getNumeroRegistros() {
			return numeroRegistros;
		}

		/**
		 * @param numeroRegistros O numeroRegistros a ser setado.
		 */
		public void setNumeroRegistros(String numeroRegistros) {
			this.numeroRegistros = numeroRegistros;
		}

		/**
		 * @return Retorna o campo numeroSequencialArquivo.
		 */
		public String getNumeroSequencialArquivo() {
			return numeroSequencialArquivo;
		}

		/**
		 * @param numeroSequencialArquivo O numeroSequencialArquivo a ser setado.
		 */
		public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
			this.numeroSequencialArquivo = numeroSequencialArquivo;
		}

	    
}
