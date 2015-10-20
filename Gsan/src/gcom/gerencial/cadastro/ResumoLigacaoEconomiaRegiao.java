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
package gcom.gerencial.cadastro;

/**
 * 
 *  
 *
 * @author Ivan S�rgio
 * @date 18/04/2007
 */
public class ResumoLigacaoEconomiaRegiao {	

	private java.lang.Integer id;
	private java.lang.Integer anoMesReferencia;
	private gcom.cadastro.geografico.Regiao regiao;
	private gcom.cadastro.geografico.Microrregiao microrregiao;
	private gcom.cadastro.geografico.Municipio municipio;
	private gcom.cadastro.geografico.Bairro bairro;
	private gcom.cadastro.imovel.ImovelPerfil perfilImovel;
	private gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao;
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private gcom.cadastro.imovel.Categoria categoria;
	private gcom.cadastro.imovel.Subcategoria subcategoria;
	private gcom.cadastro.cliente.EsferaPoder esferaPoder;
	private gcom.cadastro.cliente.ClienteTipo clienteTipo;
	private gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil;
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil;
    private Short indicadorHidrometro;
    private Short indicadorHidrometroPoco;
    private Short indicadorVolumeMinimoAguaFixado;
    private Short indicadorVolumeMinimoEsgotoFixado;
    private Short indicadorPoco;
	private java.lang.Integer quantidadeLigacoes;
	private java.lang.Integer quantidadeEconomias;
	
	public ResumoLigacaoEconomiaRegiao(
			java.lang.Integer anoMesReferencia,
			gcom.cadastro.geografico.Regiao regiao,
			gcom.cadastro.geografico.Microrregiao microrregiao,
			gcom.cadastro.geografico.Municipio municipio,
			gcom.cadastro.geografico.Bairro bairro,
			gcom.cadastro.imovel.ImovelPerfil perfilImovel,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			gcom.cadastro.imovel.Categoria categoria,
			gcom.cadastro.imovel.Subcategoria subcategoria,
			gcom.cadastro.cliente.EsferaPoder esferaPoder,
			gcom.cadastro.cliente.ClienteTipo clienteTipo,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil,
		    Short indicadorHidrometro,
		    Short indicadorHidrometroPoco,
		    Short indicadorVolumeMinimoAguaFixado,
		    Short indicadorVolumeMinimoEsgotoFixado,
		    Short indicadorPoco,
			java.lang.Integer quantidadeLigacoes,
			java.lang.Integer quantidadeEconomias){
		
		this.anoMesReferencia = anoMesReferencia;
		this.regiao = regiao;
		this.microrregiao = microrregiao;
		this.municipio = municipio;
		this.bairro = bairro;
		this.perfilImovel = perfilImovel;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.esferaPoder = esferaPoder;
		this.clienteTipo = clienteTipo;
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
		this.indicadorHidrometro = indicadorHidrometro;
		this.indicadorHidrometroPoco = indicadorHidrometroPoco;
		this.indicadorVolumeMinimoAguaFixado = indicadorVolumeMinimoAguaFixado;
		this.indicadorVolumeMinimoEsgotoFixado = indicadorVolumeMinimoEsgotoFixado;
		this.indicadorPoco = indicadorPoco;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public java.lang.Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(java.lang.Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public gcom.cadastro.geografico.Bairro getBairro() {
		return bairro;
	}

	public void setBairro(gcom.cadastro.geografico.Bairro bairro) {
		this.bairro = bairro;
	}

	public gcom.cadastro.imovel.Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(gcom.cadastro.imovel.Categoria categoria) {
		this.categoria = categoria;
	}

	public gcom.cadastro.cliente.ClienteTipo getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(gcom.cadastro.cliente.ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public gcom.cadastro.cliente.EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(gcom.cadastro.cliente.EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public Short getIndicadorHidrometroPoco() {
		return indicadorHidrometroPoco;
	}

	public void setIndicadorHidrometroPoco(Short indicadorHidrometroPoco) {
		this.indicadorHidrometroPoco = indicadorHidrometroPoco;
	}

	public Short getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(Short indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public Short getIndicadorVolumeMinimoAguaFixado() {
		return indicadorVolumeMinimoAguaFixado;
	}

	public void setIndicadorVolumeMinimoAguaFixado(
			Short indicadorVolumeMinimoAguaFixado) {
		this.indicadorVolumeMinimoAguaFixado = indicadorVolumeMinimoAguaFixado;
	}

	public Short getIndicadorVolumeMinimoEsgotoFixado() {
		return indicadorVolumeMinimoEsgotoFixado;
	}

	public void setIndicadorVolumeMinimoEsgotoFixado(
			Short indicadorVolumeMinimoEsgotoFixado) {
		this.indicadorVolumeMinimoEsgotoFixado = indicadorVolumeMinimoEsgotoFixado;
	}

	public gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil getLigacaoAguaPerfil() {
		return ligacaoAguaPerfil;
	}

	public void setLigacaoAguaPerfil(
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil) {
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
	}

	public gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil getLigacaoEsgotoPerfil() {
		return ligacaoEsgotoPerfil;
	}

	public void setLigacaoEsgotoPerfil(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil) {
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public gcom.cadastro.geografico.Microrregiao getMicrorregiao() {
		return microrregiao;
	}

	public void setMicrorregiao(gcom.cadastro.geografico.Microrregiao microrregiao) {
		this.microrregiao = microrregiao;
	}

	public gcom.cadastro.geografico.Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(gcom.cadastro.geografico.Municipio municipio) {
		this.municipio = municipio;
	}

	public gcom.cadastro.imovel.ImovelPerfil getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(gcom.cadastro.imovel.ImovelPerfil perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public java.lang.Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(java.lang.Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public java.lang.Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	public void setQuantidadeLigacoes(java.lang.Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	public gcom.cadastro.geografico.Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(gcom.cadastro.geografico.Regiao regiao) {
		this.regiao = regiao;
	}

	public gcom.cadastro.imovel.Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(gcom.cadastro.imovel.Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
}