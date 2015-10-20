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
package gcom.arrecadacao.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Header do TXT do retorno do cart�o de cr�dito
 * 
 * @author Raphael Rossiter
 * @date 28/01/2010
 */
public class RegistroCartaoCreditoHeaderHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Short tipoRegistro;
	
	private String estabelecimentoMatriz;
	
	private Date dataProcessamento;
	
	private Date dataPeriodoInicial;
	
	private Date dataPeriodoFinal;
	
	private String produto;
	
	private String sequencia;
	
	private String empresa;
	
	private String opcaoExtrato;
	
	private String transmissao;
	
	private String descricaoOpcaoExtrato;
	
	//CONSTANTE HEADER
	public final static Short CODIGO_HEADER = new Short("0");
	
	
	public RegistroCartaoCreditoHeaderHelper(){}

	public Date getDataPeriodoFinal() {
		return dataPeriodoFinal;
	}

	public void setDataPeriodoFinal(Date dataPeriodoFinal) {
		this.dataPeriodoFinal = dataPeriodoFinal;
	}

	public Date getDataPeriodoInicial() {
		return dataPeriodoInicial;
	}

	public void setDataPeriodoInicial(Date dataPeriodoInicial) {
		this.dataPeriodoInicial = dataPeriodoInicial;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public String getDescricaoOpcaoExtrato() {
		return descricaoOpcaoExtrato;
	}

	public void setDescricaoOpcaoExtrato(String descricaoOpcaoExtrato) {
		this.descricaoOpcaoExtrato = descricaoOpcaoExtrato;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEstabelecimentoMatriz() {
		return estabelecimentoMatriz;
	}

	public void setEstabelecimentoMatriz(String estabelecimentoMatriz) {
		this.estabelecimentoMatriz = estabelecimentoMatriz;
	}

	public String getOpcaoExtrato() {
		return opcaoExtrato;
	}

	public void setOpcaoExtrato(String opcaoExtrato) {
		this.opcaoExtrato = opcaoExtrato;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getSequencia() {
		return sequencia;
	}

	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}

	public Short getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Short tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getTransmissao() {
		return transmissao;
	}

	public void setTransmissao(String transmissao) {
		this.transmissao = transmissao;
	}
}
