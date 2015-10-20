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
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Ana Maria
 * @date 15/05/2007
 */
public class RelatorioEmitirProtocoloDocumentoCobrancaBean implements RelatorioBean {
	
	private String empresa;

	private String idLocalidade;
	
	private String localidade;
	
	private String setor;

	private String quantidadeDocumento;
	
	private BigDecimal valorDocumento;
	
	private Integer seqInicial;
	
	private Integer seqFinal;
	
	private String idGerencia;
	
	private String gerencia;
	
	private String idUnidadeNegocio;
	
	private String nomeUnidadeNegocio;

	public RelatorioEmitirProtocoloDocumentoCobrancaBean(String empresa, String idLocalidade, String localidade,
		 String setor, String quantidadeDocumento, BigDecimal valorDocumento, Integer seqInicial, Integer seqFinal,  
		 String idGerencia, String gerencia) {
		this.empresa = empresa;
		this.idLocalidade = idLocalidade;
		this.localidade = localidade;
		this.setor = setor;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		this.seqInicial = seqInicial;
		this.seqFinal = seqFinal;
		this.idGerencia = idGerencia;		
		this.gerencia = gerencia;
	}
	
	public RelatorioEmitirProtocoloDocumentoCobrancaBean(String empresa, String idLocalidade, String localidade,
			 String setor, String quantidadeDocumento, BigDecimal valorDocumento, Integer seqInicial, Integer seqFinal,  
			 String idGerencia, String gerencia, String idUnidadeNegocio, String nomeUnidadeNegocio) {
			this.empresa = empresa;
			this.idLocalidade = idLocalidade;
			this.localidade = localidade;
			this.setor = setor;
			this.quantidadeDocumento = quantidadeDocumento;
			this.valorDocumento = valorDocumento;
			this.seqInicial = seqInicial;
			this.seqFinal = seqFinal;
			this.idGerencia = idGerencia;		
			this.gerencia = gerencia;
			this.idUnidadeNegocio = idUnidadeNegocio;
			this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public String getQuantidadeDocumento() {
		return quantidadeDocumento;
	}

	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(String quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}

	/**
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	/**
	 * @param valorDocumento O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo setor.
	 */
	public String getSetor() {
		return setor;
	}

	/**
	 * @param setor O setor a ser setado.
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo seqFinal.
	 */
	public Integer getSeqFinal() {
		return seqFinal;
	}

	/**
	 * @param seqFinal O seqFinal a ser setado.
	 */
	public void setSeqFinal(Integer seqFinal) {
		this.seqFinal = seqFinal;
	}

	/**
	 * @return Retorna o campo seqInicial.
	 */
	public Integer getSeqInicial() {
		return seqInicial;
	}

	/**
	 * @param seqInicial O seqInicial a ser setado.
	 */
	public void setSeqInicial(Integer seqInicial) {
		this.seqInicial = seqInicial;
	}

	/**
	 * @return Retorna o campo gerencia.
	 */
	public String getGerencia() {
		return gerencia;
	}

	/**
	 * @param gerencia O gerencia a ser setado.
	 */
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	/**
	 * @return Retorna o campo idGerencia.
	 */
	public String getIdGerencia() {
		return idGerencia;
	}

	/**
	 * @param idGerencia O idGerencia a ser setado.
	 */
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

}
