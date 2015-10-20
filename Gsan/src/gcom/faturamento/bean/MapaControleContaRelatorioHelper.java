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
package gcom.faturamento.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class MapaControleContaRelatorioHelper implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer idEmpresa;
	private String nomeEmpresa;
	private Integer idTipoConta;
	private String descricaoTipoConta;
	private Integer idLocalidade;
	private Integer codigoSetor;
	private Integer idFaturamentoGrupo;
	private Integer sequencialInicial;
	private Integer sequencialFinal;
	private BigDecimal somaValorAgua;
	private BigDecimal somaValorEsgoto;
	private BigDecimal somaValordebito;
	private BigDecimal somaValorCredito;
	private BigDecimal somaValorImpostos;
	private Integer idEsferaPoder;
	private Integer qtdeContas;
	private Integer qtdTotalMacro;
		
	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public MapaControleContaRelatorioHelper(){}

	public Integer getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(Integer codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public String getDescricaoTipoConta() {
		return descricaoTipoConta;
	}

	public void setDescricaoTipoConta(String descricaoTipoConta) {
		this.descricaoTipoConta = descricaoTipoConta;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdTipoConta() {
		return idTipoConta;
	}

	public void setIdTipoConta(Integer idTipoConta) {
		this.idTipoConta = idTipoConta;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Integer getSequencialFinal() {
		return sequencialFinal;
	}

	public void setSequencialFinal(Integer sequencialFinal) {
		this.sequencialFinal = sequencialFinal;
	}

	public Integer getSequencialInicial() {
		return sequencialInicial;
	}

	public void setSequencialInicial(Integer sequencialInicial) {
		this.sequencialInicial = sequencialInicial;
	}

	public BigDecimal getSomaValorAgua() {
		return somaValorAgua;
	}

	public void setSomaValorAgua(BigDecimal somaValorAgua) {
		this.somaValorAgua = somaValorAgua;
	}

	public BigDecimal getSomaValorCredito() {
		return somaValorCredito;
	}

	public void setSomaValorCredito(BigDecimal somaValorCredito) {
		this.somaValorCredito = somaValorCredito;
	}

	public BigDecimal getSomaValordebito() {
		return somaValordebito;
	}

	public void setSomaValordebito(BigDecimal somaValordebito) {
		this.somaValordebito = somaValordebito;
	}

	public BigDecimal getSomaValorEsgoto() {
		return somaValorEsgoto;
	}

	public void setSomaValorEsgoto(BigDecimal somaValorEsgoto) {
		this.somaValorEsgoto = somaValorEsgoto;
	}
	
	public BigDecimal getSomaValorImpostos() {
		return somaValorImpostos;
	}

	public void setSomaValorImpostos(BigDecimal somaValorImpostos) {
		this.somaValorImpostos = somaValorImpostos;
	}

	public BigDecimal getValor(){
		BigDecimal valor = BigDecimal.ZERO;
		
		valor = valor.add(this.somaValorAgua);
		valor = valor.add(this.somaValordebito);
		valor = valor.add(this.somaValorEsgoto);
		valor = valor.subtract(this.somaValorCredito);
		valor = valor.subtract(this.somaValorImpostos);
		
		return valor;
	}
	
	public Integer getQtdContas(){
		Integer qtd = new Integer(0);
		
		if(this.sequencialFinal != null && this.sequencialInicial != null){
			qtd = (this.sequencialFinal.intValue() - this.sequencialInicial) + 1;
		}else{
			qtd = 0;
		}
		
		return qtd;
	}

	/**
	 * @return Retorna o campo qtdeContas.
	 */
	public Integer getQtdeContas() {
		return qtdeContas;
	}

	/**
	 * @param qtdeContas O qtdeContas a ser setado.
	 */
	public void setQtdeContas(Integer qtdeContas) {
		this.qtdeContas = qtdeContas;
	}

	
	public Integer getQtdTotalMacro() {
		return qtdTotalMacro;
	}

	public void setQtdTotalMacro(Integer qtdTotalMacro) {
		this.qtdTotalMacro = qtdTotalMacro;
	}
	
	
	
	
}
