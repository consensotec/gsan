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
 * @created 28 de fevereiro de 2008
 * @version 1.0
 */

public class RelatorioManterNegativadorBean implements RelatorioBean {
	   
	    private String id;    
	
	    private String codigoAgente;
	    
	    private String cliente;
	    
	    private String nome;
	    
	    private String imovel;
	    
	    private String numeroInscricaoEstadual;
	    
	    private String indicadorUso;

		public RelatorioManterNegativadorBean(String id, String codigoAgente, String cliente,String nome, String imovel, String numeroInscricaoEstadual, String indicadorUso) {
			super();
			this.id = id;
			this.codigoAgente = codigoAgente;
			this.cliente = cliente;
			this.nome = nome;
			this.imovel = imovel;
			this.numeroInscricaoEstadual = numeroInscricaoEstadual;
			this.indicadorUso = indicadorUso;
		}

		/**
		 * @return Retorna o campo cliente.
		 */
		public String getCliente() {
			return cliente;
		}

		/**
		 * @param cliente O cliente a ser setado.
		 */
		public void setCliente(String cliente) {
			this.cliente = cliente;
		}

		/**
		 * @return Retorna o campo codigoAgente.
		 */
		public String getCodigoAgente() {
			return codigoAgente;
		}

		/**
		 * @param codigoAgente O codigoAgente a ser setado.
		 */
		public void setCodigoAgente(String codigoAgente) {
			this.codigoAgente = codigoAgente;
		}

		/**
		 * @return Retorna o campo id.
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id O id a ser setado.
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return Retorna o campo imovel.
		 */
		public String getImovel() {
			return imovel;
		}

		/**
		 * @param imovel O imovel a ser setado.
		 */
		public void setImovel(String imovel) {
			this.imovel = imovel;
		}

		/**
		 * @return Retorna o campo indicadorUso.
		 */
		public String getIndicadorUso() {
			return indicadorUso;
		}

		/**
		 * @param indicadorUso O indicadorUso a ser setado.
		 */
		public void setIndicadorUso(String indicadorUso) {
			this.indicadorUso = indicadorUso;
		}

		/**
		 * @return Retorna o campo numeroInscricaoEstadual.
		 */
		public String getNumeroInscricaoEstadual() {
			return numeroInscricaoEstadual;
		}

		/**
		 * @param numeroInscricaoEstadual O numeroInscricaoEstadual a ser setado.
		 */
		public void setNumeroInscricaoEstadual(String numeroInscricaoEstadual) {
			this.numeroInscricaoEstadual = numeroInscricaoEstadual;
		}

		/**
		 * @return Retorna o campo nome.
		 */
		public String getNome() {
			return nome;
		}

		/**
		 * @param nome O nome a ser setado.
		 */
		public void setNome(String nome) {
			this.nome = nome;
		}
	    
	    
	  
	   
		
	
}
