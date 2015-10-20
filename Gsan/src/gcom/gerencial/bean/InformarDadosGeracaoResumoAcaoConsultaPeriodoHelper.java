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

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;

import java.util.Collection;

public class InformarDadosGeracaoResumoAcaoConsultaPeriodoHelper {

	public final static Integer ID_IMPRESSAO_TRADICIONAL = new Integer(1);
	public final static String DESC_IMPRESSAO_TRADICIONAL = "IMPRESSAO TRADICIONAL";
	
	public final static Integer ID_IMPRESSAO_SIMULTANEA = new Integer(2);
	public final static String DESC_IMPRESSAO_SIMULTANEA = "IMPRESSAO SIMULTANEA";
	
	public final static Integer ID_AMBOS = new Integer(3);
	public final static String DESC_AMBOS = "AMBOS";
	
	private Integer anoMesInicialReferencia;
	private Integer anoMesFinalReferencia;
	private Collection<GerenciaRegional> colecaoGerenciaRegional;
	private Collection<UnidadeNegocio> colecaoUnidadeNegocio;
	private Localidade eloPolo;
	private Localidade localidade;
	private SetorComercial setorComercial;
	private Quadra quadra;
	private Collection<ImovelPerfil> colecaoImovelPerfil;
	private Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao;
	private Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao;
	private Collection<Categoria> colecaoCategoria;
	private Collection<EsferaPoder> colecaoEsferaPoder;
	private Collection<Empresa> colecaoEmpresa;
	private Collection<CobrancaGrupo> colecaoCobrancaGrupo;
	
	private Integer tipoImpressao;
	
	public Collection<Categoria> getColecaoCategoria() {
		return colecaoCategoria;
	}
	public Integer getAnoMesInicialReferencia() {
		return anoMesInicialReferencia;
	}
	public void setAnoMesInicialReferencia(Integer anoMesInicialReferencia) {
		this.anoMesInicialReferencia = anoMesInicialReferencia;
	}
	public Integer getAnoMesFinalReferencia() {
		return anoMesFinalReferencia;
	}
	public void setAnoMesFinalReferencia(Integer anoMesFinalReferencia) {
		this.anoMesFinalReferencia = anoMesFinalReferencia;
	}
	public void setColecaoCategoria(Collection<Categoria> colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}
	public Collection<CobrancaGrupo> getColecaoCobrancaGrupo() {
		return colecaoCobrancaGrupo;
	}
	public void setColecaoCobrancaGrupo(Collection<CobrancaGrupo> colecaoCobrancaGrupo) {
		this.colecaoCobrancaGrupo = colecaoCobrancaGrupo;
	}
	public Collection<Empresa> getColecaoEmpresa() {
		return colecaoEmpresa;
	}
	public void setColecaoEmpresa(Collection<Empresa> colecaoEmpresa) {
		this.colecaoEmpresa = colecaoEmpresa;
	}
	public Collection<EsferaPoder> getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}
	public void setColecaoEsferaPoder(Collection<EsferaPoder> colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}
	public Collection<ImovelPerfil> getColecaoImovelPerfil() {
		return colecaoImovelPerfil;
	}
	public void setColecaoImovelPerfil(Collection<ImovelPerfil> colecaoImovelPerfil) {
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}
	public Collection<LigacaoAguaSituacao> getColecaoLigacaoAguaSituacao() {
		return colecaoLigacaoAguaSituacao;
	}
	public void setColecaoLigacaoAguaSituacao(Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao) {
		this.colecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao;
	}
	public Collection<LigacaoEsgotoSituacao> getColecaoLigacaoEsgotoSituacao() {
		return colecaoLigacaoEsgotoSituacao;
	}
	public void setColecaoLigacaoEsgotoSituacao(Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao) {
		this.colecaoLigacaoEsgotoSituacao = colecaoLigacaoEsgotoSituacao;
	}
	public Localidade getEloPolo() {
		return eloPolo;
	}
	public void setEloPolo(Localidade eloPolo) {
		this.eloPolo = eloPolo;
	}
	public Collection<GerenciaRegional> getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}
	public void setColecaoGerenciaRegional(Collection<GerenciaRegional> colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}
	public Collection<UnidadeNegocio> getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}
	public void setColecaoUnidadeNegocio(Collection<UnidadeNegocio> colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}	
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
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
	
	public String getFormatarAnoMesParaMesAnoInicial() {

		String anoMesRecebido = "" + this.getAnoMesInicialReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
	public String getFormatarAnoMesParaMesAnoFinal() {

		String anoMesRecebido = "" + this.getAnoMesFinalReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
	
	public Integer getTipoImpressao() {
		return tipoImpressao;
	}
	public void setTipoImpressao(Integer tipoImpressao) {
		this.tipoImpressao = tipoImpressao;
	}
}
