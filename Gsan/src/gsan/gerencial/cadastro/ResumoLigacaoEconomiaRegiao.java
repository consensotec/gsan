/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gerencial.cadastro;

/**
 * 
 *  
 *
 * @author Ivan Sérgio
 * @date 18/04/2007
 */
public class ResumoLigacaoEconomiaRegiao {	

	private java.lang.Integer id;
	private java.lang.Integer anoMesReferencia;
	private gsan.cadastro.geografico.Regiao regiao;
	private gsan.cadastro.geografico.Microrregiao microrregiao;
	private gsan.cadastro.geografico.Municipio municipio;
	private gsan.cadastro.geografico.Bairro bairro;
	private gsan.cadastro.imovel.ImovelPerfil perfilImovel;
	private gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao;
	private gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private gsan.cadastro.imovel.Categoria categoria;
	private gsan.cadastro.imovel.Subcategoria subcategoria;
	private gsan.cadastro.cliente.EsferaPoder esferaPoder;
	private gsan.cadastro.cliente.ClienteTipo clienteTipo;
	private gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil;
	private gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil;
    private Short indicadorHidrometro;
    private Short indicadorHidrometroPoco;
    private Short indicadorVolumeMinimoAguaFixado;
    private Short indicadorVolumeMinimoEsgotoFixado;
    private Short indicadorPoco;
	private java.lang.Integer quantidadeLigacoes;
	private java.lang.Integer quantidadeEconomias;
	
	public ResumoLigacaoEconomiaRegiao(
			java.lang.Integer anoMesReferencia,
			gsan.cadastro.geografico.Regiao regiao,
			gsan.cadastro.geografico.Microrregiao microrregiao,
			gsan.cadastro.geografico.Municipio municipio,
			gsan.cadastro.geografico.Bairro bairro,
			gsan.cadastro.imovel.ImovelPerfil perfilImovel,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao,
			gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			gsan.cadastro.imovel.Categoria categoria,
			gsan.cadastro.imovel.Subcategoria subcategoria,
			gsan.cadastro.cliente.EsferaPoder esferaPoder,
			gsan.cadastro.cliente.ClienteTipo clienteTipo,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil,
			gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil,
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

	public gsan.cadastro.geografico.Bairro getBairro() {
		return bairro;
	}

	public void setBairro(gsan.cadastro.geografico.Bairro bairro) {
		this.bairro = bairro;
	}

	public gsan.cadastro.imovel.Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(gsan.cadastro.imovel.Categoria categoria) {
		this.categoria = categoria;
	}

	public gsan.cadastro.cliente.ClienteTipo getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(gsan.cadastro.cliente.ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public gsan.cadastro.cliente.EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(gsan.cadastro.cliente.EsferaPoder esferaPoder) {
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

	public gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil getLigacaoAguaPerfil() {
		return ligacaoAguaPerfil;
	}

	public void setLigacaoAguaPerfil(
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil) {
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
	}

	public gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil getLigacaoEsgotoPerfil() {
		return ligacaoEsgotoPerfil;
	}

	public void setLigacaoEsgotoPerfil(
			gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil) {
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
	}

	public gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public gsan.cadastro.geografico.Microrregiao getMicrorregiao() {
		return microrregiao;
	}

	public void setMicrorregiao(gsan.cadastro.geografico.Microrregiao microrregiao) {
		this.microrregiao = microrregiao;
	}

	public gsan.cadastro.geografico.Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(gsan.cadastro.geografico.Municipio municipio) {
		this.municipio = municipio;
	}

	public gsan.cadastro.imovel.ImovelPerfil getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(gsan.cadastro.imovel.ImovelPerfil perfilImovel) {
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

	public gsan.cadastro.geografico.Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(gsan.cadastro.geografico.Regiao regiao) {
		this.regiao = regiao;
	}

	public gsan.cadastro.imovel.Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(gsan.cadastro.imovel.Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
}