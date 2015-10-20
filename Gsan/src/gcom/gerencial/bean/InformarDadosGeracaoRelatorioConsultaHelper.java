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
package gcom.gerencial.bean;

import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;

import java.util.Collection;

public class InformarDadosGeracaoRelatorioConsultaHelper {

	private Integer anoMesReferencia;
	private Integer opcaoTotalizacao;
	private String descricaoOpcaoTotalizacao;
	private FaturamentoGrupo faturamentoGrupo;
	private CobrancaGrupo cobrancaGrupo;
	private GerenciaRegional gerenciaRegional;
	private Localidade eloPolo;
	private Localidade localidade;
	private Municipio municipio;
	private SetorComercial setorComercial;
	private Quadra quadra;
	private Collection colecaoImovelPerfil;
	private Collection colecaoLigacaoAguaSituacao;
	private Collection colecaoLigacaoEsgotoSituacao;
	private Collection colecaoCategoria;
	private Collection colecaoEsferaPoder;
	private Integer tipoAnaliseFaturamento;
	private String tipoDetalhe;
	private UnidadeNegocio unidadeNegocio;
	private String tipoQuebra;
	private Rota rota;
	
	private boolean gerarRelatorio = false;
	private Integer tipoRelatorio;
	
	
	public boolean isGerarRelatorio() {
		return gerarRelatorio;
	}


	public void setGerarRelatorio(boolean gerarRelatorio) {
		this.gerarRelatorio = gerarRelatorio;
	}


	public Integer getTipoRelatorio() {
		return tipoRelatorio;
	}


	public void setTipoRelatorio(Integer tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}


	public Integer getTipoAnaliseFaturamento() {
		return tipoAnaliseFaturamento;
	}


	public void setTipoAnaliseFaturamento(Integer tipoAnaliseFaturamento) {
		this.tipoAnaliseFaturamento = tipoAnaliseFaturamento;
	}


	public InformarDadosGeracaoRelatorioConsultaHelper(){}
	
	
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public Collection getColecaoCategoria() {
		return colecaoCategoria;
	}
	public void setColecaoCategoria(Collection colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}
	public Collection getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}
	public void setColecaoEsferaPoder(Collection colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}
	public Collection getColecaoImovelPerfil() {
		return colecaoImovelPerfil;
	}
	public void setColecaoImovelPerfil(Collection colecaoImovelPerfil) {
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}
	public Collection getColecaoLigacaoAguaSituacao() {
		return colecaoLigacaoAguaSituacao;
	}
	public void setColecaoLigacaoAguaSituacao(Collection colecaoLigacaoAguaSituacao) {
		this.colecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao;
	}
	public Collection getColecaoLigacaoEsgotoSituacao() {
		return colecaoLigacaoEsgotoSituacao;
	}
	public void setColecaoLigacaoEsgotoSituacao(
			Collection colecaoLigacaoEsgotoSituacao) {
		this.colecaoLigacaoEsgotoSituacao = colecaoLigacaoEsgotoSituacao;
	}
	public Localidade getEloPolo() {
		return eloPolo;
	}
	public void setEloPolo(Localidade eloPolo) {
		this.eloPolo = eloPolo;
	}
	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}
	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Integer getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(Integer opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	public Quadra getQuadra() {
		return quadra;
	}
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}


	public String getDescricaoOpcaoTotalizacao() {
		return descricaoOpcaoTotalizacao;
	}


	public void setDescricaoOpcaoTotalizacao(String descricaoOpcaoTotalizacao) {
		this.descricaoOpcaoTotalizacao = descricaoOpcaoTotalizacao;
	}
	
	public String getFormatarAnoMesParaMesAno() {

		String anoMesRecebido = "" + this.getAnoMesReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}


	/**
	 * @return Retorna o campo cobrancaGrupo.
	 */
	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}


	/**
	 * @param cobrancaGrupo O cobrancaGrupo a ser setado.
	 */
	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}


	public String getTipoDetalhe() {
		return tipoDetalhe;
	}


	public void setTipoDetalhe(String tipoDetalhe) {
		this.tipoDetalhe = tipoDetalhe;
	}


	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}


	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}


	public String getTipoQuebra() {
		return tipoQuebra;
	}


	public void setTipoQuebra(String tipoQuebra) {
		this.tipoQuebra = tipoQuebra;
	}


	public Rota getRota() {
		return rota;
	}


	public void setRota(Rota rota) {
		this.rota = rota;
	}
}
