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
package gsan.cadastro.imovel.bean;

import java.util.Date;

public class EmitirConsumoImovelCondominimoHelper {

	/**
	 * Emiti o extrato de consumo dos imoveis condominios
	 * apartir de uma colecao de rotas
	 *
	 * [UC0493] Emitir Extrato de consumo de imovel condominio
	 *
	 * @author Fl�vio Cordeiro
	 * @date 08/01/2007
	 *
	 * @param args
	 */
	
	private Integer idImovel;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer idCliente;
	private String nomeCliente;
	private String inscricaoImovel;
	private String endereco;
	private String mesAnoDigito;
	private Date dataLeituraAtualFat;
	private Integer numeroLeituraAtualFat;
	private Integer consumoFaturado;
	private String descricaoLeituraSituacao;
	private String descricaoConsumoTipo;
	private String descricaoAnormalidadeConsumo;
	private Integer qtdEconomias;
	private Integer situacaoAgua;
	private Integer situacaoEsgoto;
	private String abreviadaConsumoTipo;
	private Integer anormalidadeLeituraFat;
	private String abreviadaConsumoAnormalidade;
	private Integer perfilImovel;
	private Date dataLeituraAnteriorFat;
	private Integer consumoMedio;
	private Integer rateio;
	private Integer idEmpresa;
	private Integer idLeituraSituacao;
	
	public Integer getIdLeituraSituacao() {
		return idLeituraSituacao;
	}

	public void setIdLeituraSituacao(Integer idLeituraSituacao) {
		this.idLeituraSituacao = idLeituraSituacao;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getAbreviadaConsumoAnormalidade() {
		return abreviadaConsumoAnormalidade;
	}

	public void setAbreviadaConsumoAnormalidade(String abreviadaConsumoAnormalidade) {
		this.abreviadaConsumoAnormalidade = abreviadaConsumoAnormalidade;
	}

	public Integer getAnormalidadeLeituraFat() {
		return anormalidadeLeituraFat;
	}

	public void setAnormalidadeLeituraFat(Integer anormalidadeLeituraFat) {
		this.anormalidadeLeituraFat = anormalidadeLeituraFat;
	}

	public Integer getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(Integer consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public Date getDataLeituraAnteriorFat() {
		return dataLeituraAnteriorFat;
	}

	public void setDataLeituraAnteriorFat(Date dataLeituraAnteriorFat) {
		this.dataLeituraAnteriorFat = dataLeituraAnteriorFat;
	}

	public Integer getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(Integer perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public Integer getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(Integer qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public String getDescricaoConsumoTipo() {
		return descricaoConsumoTipo;
	}

	public void setDescricaoConsumoTipo(String descricaoConsumoTipo) {
		this.descricaoConsumoTipo = descricaoConsumoTipo;
	}

	public String getDescricaoLeituraSituacao() {
		return descricaoLeituraSituacao;
	}

	public void setDescricaoLeituraSituacao(String descricaoLeituraSituacao) {
		this.descricaoLeituraSituacao = descricaoLeituraSituacao;
	}

	public Integer getConsumoFaturado() {
		return consumoFaturado;
	}

	public void setConsumoFaturado(Integer consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	public Date getDataLeituraAtualFat() {
		return dataLeituraAtualFat;
	}

	public void setDataLeituraAtualFat(Date dataLeituraAtualFat) {
		this.dataLeituraAtualFat = dataLeituraAtualFat;
	}

	public Integer getNumeroLeituraAtualFat() {
		return numeroLeituraAtualFat;
	}

	public void setNumeroLeituraAtualFat(Integer numeroLeituraAtualFat) {
		this.numeroLeituraAtualFat = numeroLeituraAtualFat;
	}

	public String getMesAnoDigito() {
		return mesAnoDigito;
	}

	public void setMesAnoDigito(String mesAnoDigito) {
		this.mesAnoDigito = mesAnoDigito;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}

	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}

	public Integer getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(Integer situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public Integer getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(Integer situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public Integer getRateio() {
		return rateio;
	}

	public void setRateio(Integer rateio) {
		this.rateio = rateio;
	}

	public String getAbreviadaConsumoTipo() {
		return abreviadaConsumoTipo;
	}

	public void setAbreviadaConsumoTipo(String abreviadaConsumoTipo) {
		this.abreviadaConsumoTipo = abreviadaConsumoTipo;
	}

}
