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
package gcom.relatorio.faturamento;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *[UC0958] Gerar Relat�rio Juras, Multas e D�bitos Cancelados
 *
 * @author Marlon Patrick
 * @since 22/10/2009
 */
public class FiltrarRelatorioJurosMultasDebitosCanceladosHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer mesAnoFaturamento;

	private Date dataCancelamentoInicial;
	private Date dataCancelamentoFinal;

	private Integer unidadeNegocio;
	
	private Integer localidade;

	private Collection<Integer> colecaoTiposDebito;
	private Collection<Integer> colecaoCategorias;
	private Collection<Integer> colecaoPerfilImovel;
	private Collection<Integer> colecaoEsferaPoder;
		
	private Integer usuarioCancelamento;

	public Integer getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(Integer mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public Date getDataCancelamentoInicial() {
		return dataCancelamentoInicial;
	}

	public void setDataCancelamentoInicial(Date dataCancelamentoInicial) {
		this.dataCancelamentoInicial = dataCancelamentoInicial;
	}

	public Date getDataCancelamentoFinal() {
		return dataCancelamentoFinal;
	}

	public void setDataCancelamentoFinal(Date dataCancelamentoFinal) {
		this.dataCancelamentoFinal = dataCancelamentoFinal;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	public Collection<Integer> getColecaoTiposDebito() {
		return colecaoTiposDebito;
	}

	public void setColecaoTiposDebito(Collection<Integer> colecaoTiposDebito) {
		this.colecaoTiposDebito = colecaoTiposDebito;
	}

	public Collection<Integer> getColecaoCategorias() {
		return colecaoCategorias;
	}

	public void setColecaoCategorias(Collection<Integer> categorias) {
		this.colecaoCategorias = categorias;
	}

	public Collection<Integer> getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}

	public void setColecaoPerfilImovel(Collection<Integer> colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}

	public Collection<Integer> getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}

	public void setColecaoEsferaPoder(Collection<Integer> colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}

	public Integer getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(Integer usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}
	
}