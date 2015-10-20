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
package gcom.gerencial.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Este caso de uso gera o resumo da pend�ncia
 *
 * [UC0335] Gerar Resumo da Pend�ncia
 *
 * @author Roberta Costa
 * @date 19/05/2006
 */
public class ResumoPendenciaAcumuladoHelper implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * @since 02/06/2006
	 */
	private String estado;
	private String tipoCategoria;
	private String categoria;
	private String tipoSituacaoAguaEsgoto;
	private Integer anoMesReferencia;
	private Integer quantidadeLigacoes;
	private Integer quantidadeDocumento;
	private BigDecimal valorPendente;

	/**
	 * @return Retorna o campo estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado O estado a ser setado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Retorna o campo tipoSituacaoAguaEsgoto.
	 */
	public String getTipoSituacaoAguaEsgoto() {
		return tipoSituacaoAguaEsgoto;
	}

	/**
	 * @param tipoSituacaoAguaEsgoto O tipoSituacaoAguaEsgoto a ser setado.
	 */
	public void setTipoSituacaoAguaEsgoto(String tipoSituacaoAguaEsgoto) {
		this.tipoSituacaoAguaEsgoto = tipoSituacaoAguaEsgoto;
	}

	/**
	 * Construtor de ResumoPendenciaAcumuladoHelper 
	 * 
	 */
	public ResumoPendenciaAcumuladoHelper() {
		super();
	}

	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}

	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}

	/**
	 * @return Retorna o campo quantidadeLigacoes.
	 */
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	/**
	 * @param quantidadeLigacoes O quantidadeLigacoes a ser setado.
	 */
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	/**
	 * @return Retorna o campo tipoCategoria.
	 */
	public String getTipoCategoria() {
		return tipoCategoria;
	}

	/**
	 * @param tipoCategoria O tipoCategoria a ser setado.
	 */
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	/**
	 * @return Retorna o campo valorPendente.
	 */
	public BigDecimal getValorPendente() {
		return valorPendente;
	}

	/**
	 * @param valorPendente O valorPendente a ser setado.
	 */
	public void setValorPendente(BigDecimal valorPendente) {
		this.valorPendente = valorPendente;
	}

	/**
	 * Construtor de ResumoPendenciaAcumuladoHelper 
	 * 
	 * @param estado
	 * @param tipoCategoria
	 * @param categoria
	 * @param tipoSituacaoAguaEsgoto
	 * @param anoMesReferencia
	 * @param quantidadeLigacoes
	 * @param quantidadeDocumento
	 * @param valorPendente
	 */
	public ResumoPendenciaAcumuladoHelper(String estado, String tipoCategoria, String categoria, String tipoSituacaoAguaEsgoto, Integer anoMesReferencia, Integer quantidadeLigacoes, Integer quantidadeDocumento, BigDecimal valorPendente) {
		super();
		this.estado = estado;
		this.tipoCategoria = tipoCategoria;
		this.categoria = categoria;
		this.tipoSituacaoAguaEsgoto = tipoSituacaoAguaEsgoto;
		this.anoMesReferencia = anoMesReferencia;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorPendente = valorPendente;
	}
}