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
package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

/**
 * Classe respons�vel por ajudar o caso de uso [UC0269] Resumo de Ligacoes Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006 
 */
public class GerarRelatorioAnormalidadeLeituraPeriodoActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeNegocio;

	private String idLocalidadeInicial;
	private String nomeLocalidadeInicial;
	
	private String idLocalidadeFinal;
	private String nomeLocalidadeFinal;

//	private String idSetorComercialInicial; 
//	private String idSetorComercialFinal;

	private String codigoSetorComercialInicial;
	private String codigoSetorComercialFinal;

	private String nomeSetorComercialInicial;
	private String nomeSetorComercialFinal;

	private String codigoRotaInicial;
	private String codigoRotaFinal;
	
	private String sequencialRotaInicial;
	private String sequencialRotaFinal;
	
	private String mesAnoReferenciaInicial;
	private String mesAnoReferenciaFinal;
	
	private String idAnormalidadeLeitura;
	
	private String idGrupoFaturamento;
	
	private String[] anormalidadeLeituraFaturada;
	
	private String ordenacao;
	
	public void limparForm(){
		 idUnidadeNegocio= "";

		 idLocalidadeInicial= "";
		 nomeLocalidadeInicial= "";
		
		 idLocalidadeFinal= "";
		 nomeLocalidadeFinal= "";

//		 idSetorComercialInicial= ""; 
//		 idSetorComercialFinal= "";

		 codigoSetorComercialInicial= "";
		 codigoSetorComercialFinal= "";

		 nomeSetorComercialInicial= "";
		 nomeSetorComercialFinal= "";

		 codigoRotaInicial= "";
		 codigoRotaFinal= "";
		
		 sequencialRotaInicial= "";
		 sequencialRotaFinal= "";
		
		 mesAnoReferenciaInicial= "";
		 mesAnoReferenciaFinal= "";
		
		 idAnormalidadeLeitura= "";
		
		 idGrupoFaturamento= "";
	}
	
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

//	public String getIdSetorComercialInicial() {
//		return idSetorComercialInicial;
//	}
//
//	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
//		this.idSetorComercialInicial = idSetorComercialInicial;
//	}
//
//	public String getIdSetorComercialFinal() {
//		return idSetorComercialFinal;
//	}
//
//	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
//		this.idSetorComercialFinal = idSetorComercialFinal;
//	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public String getMesAnoReferenciaInicial() {
		return mesAnoReferenciaInicial;
	}

	public void setMesAnoReferenciaInicial(String referenciaInicial) {
		this.mesAnoReferenciaInicial = referenciaInicial;
	}

	public String getMesAnoReferenciaFinal() {
		return mesAnoReferenciaFinal;
	}

	public void setMesAnoReferenciaFinal(String referenciaFinal) {
		this.mesAnoReferenciaFinal = referenciaFinal;
	}

	public String getIdAnormalidadeLeitura() {
		return idAnormalidadeLeitura;
	}

	public void setIdAnormalidadeLeitura(String idAnormalidadeLeitura) {
		this.idAnormalidadeLeitura = idAnormalidadeLeitura;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String[] getAnormalidadeLeituraFaturada() {
		return anormalidadeLeituraFaturada;
	}

	public void setAnormalidadeLeituraFaturada(String[] anormalidadeLeituraFaturada) {
		this.anormalidadeLeituraFaturada = anormalidadeLeituraFaturada;
	}

	public String getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(String ordenacao) {
		this.ordenacao = ordenacao;
	}
}