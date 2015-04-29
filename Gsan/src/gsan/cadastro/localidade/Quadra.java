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
package gsan.cadastro.localidade;

import gsan.cadastro.dadocensitario.IbgeSetorCensitario;
import gsan.cadastro.geografico.Bairro;
import gsan.interceptor.ObjetoTransacao;
import gsan.micromedicao.Rota;
import gsan.micromedicao.RoteiroEmpresa;
import gsan.operacional.Bacia;
import gsan.operacional.DistritoOperacional;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Quadra extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public final static Short COM_REDE = new Short("2");
	public final static String SIM = "SIM";

	public final static Short SEM_REDE = new Short("1");
	public final static String NAO = "NAO";

	public final static Short REDE_PARCIAL = new Short("3");
	public final static String PARCIAL = "PARCIAL";
	
	public final static Short BLOQUEIO_INSERIR_IMOVEL_SIM = new Short("1");
	public final static Short BLOQUEIO_INSERIR_IMOVEL_NAO = new Short("2");

	/** identifier field */
	private Integer id;

	/** persistent field */
	private int numeroQuadra;

	/** nullable persistent field */
	private Short indicadorRedeAgua;

	/** nullable persistent field */
	private Short indicadorRedeEsgoto;

	/** nullable persistent field */
	private Short numeroRotaSequencia;

	/** nullable persistent field */
	private Date dataImplantacao;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Rota rota;

	/** persistent field */
	private IbgeSetorCensitario ibgeSetorCensitario;

	/** persistent field */
	private gsan.cadastro.localidade.SetorComercial setorComercial;

	/** persistent field */
	private DistritoOperacional distritoOperacional;

	/** persistent field */
	private Bacia bacia;

	/** persistent field */
	private gsan.cadastro.localidade.Zeis zeis;

	/** persistent field */
	private gsan.cadastro.localidade.QuadraPerfil quadraPerfil;

	private AreaTipo areaTipo;
	
	private RoteiroEmpresa roteiroEmpresa;
	
	private Short indicadorIncrementoLote;
	
	private Short indicadorBloqueio = BLOQUEIO_INSERIR_IMOVEL_NAO;
	
	private Bairro bairro;
	
	/** persistent field */
	private Short indicadorAtualizacaoCadastral;

	private String setorQuadraFormatado;

	/** full constructor */
	public Quadra(int descricao, Short indicadorRedeAgua,
			Short indicadorRedeEsgoto, Short numeroRotaSequencia,
			Date dataImplantacao, Short indicadorUso, Date ultimaAlteracao,
			Rota rota, IbgeSetorCensitario ibgeSetorCensitario,
			gsan.cadastro.localidade.SetorComercial setorComercial,
			DistritoOperacional distritoOperacional, Bacia bacia,
			gsan.cadastro.localidade.Zeis zeis,
			gsan.cadastro.localidade.QuadraPerfil quadraPerfil, int numeroQuadra, Short indicadorAtualizacaoCadastral) {
		this.numeroQuadra = numeroQuadra;
		this.indicadorRedeAgua = indicadorRedeAgua;
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
		this.numeroRotaSequencia = numeroRotaSequencia;
		this.dataImplantacao = dataImplantacao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.rota = rota;
		this.ibgeSetorCensitario = ibgeSetorCensitario;
		this.setorComercial = setorComercial;
		this.distritoOperacional = distritoOperacional;
		this.bacia = bacia;
		this.zeis = zeis;
		this.quadraPerfil = quadraPerfil;
		this.indicadorAtualizacaoCadastral = indicadorAtualizacaoCadastral;
	}

	/** default constructor */
	public Quadra() {
	}

	/** minimal constructor */
	public Quadra(Integer id, int numeroQuadra, Rota rota) {
		this.id = id;
		this.numeroQuadra = numeroQuadra;
		this.rota = rota;
	}

	// Construido por S�vio Luiz para setar o id no objeto
	public Quadra(Integer id) {
		this.id = id;
	}

	/** constructor */
	public Quadra(int descricao, Rota rota,
			IbgeSetorCensitario ibgeSetorCensitario,
			gsan.cadastro.localidade.SetorComercial setorComercial,
			DistritoOperacional distritoOperacional, Bacia bacia,
			gsan.cadastro.localidade.Zeis zeis,
			gsan.cadastro.localidade.QuadraPerfil quadraPerfil, int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
		this.rota = rota;
		this.ibgeSetorCensitario = ibgeSetorCensitario;
		this.setorComercial = setorComercial;
		this.distritoOperacional = distritoOperacional;
		this.bacia = bacia;
		this.zeis = zeis;
		this.quadraPerfil = quadraPerfil;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getIndicadorRedeAgua() {
		return this.indicadorRedeAgua;
	}

	public void setIndicadorRedeAgua(Short indicadorRedeAgua) {
		this.indicadorRedeAgua = indicadorRedeAgua;
	}

	public Short getIndicadorRedeEsgoto() {
		return this.indicadorRedeEsgoto;
	}

	public void setIndicadorRedeEsgoto(Short indicadorRedeEsgoto) {
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
	}

	public Short getNumeroRotaSequencia() {
		return this.numeroRotaSequencia;
	}

	public void setNumeroRotaSequencia(Short numeroRotaSequencia) {
		this.numeroRotaSequencia = numeroRotaSequencia;
	}

	public Date getDataImplantacao() {
		return this.dataImplantacao;
	}

	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Rota getRota() {
		return this.rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public IbgeSetorCensitario getIbgeSetorCensitario() {
		return this.ibgeSetorCensitario;
	}

	public void setIbgeSetorCensitario(IbgeSetorCensitario ibgeSetorCensitario) {
		this.ibgeSetorCensitario = ibgeSetorCensitario;
	}

	public gsan.cadastro.localidade.SetorComercial getSetorComercial() {
		return this.setorComercial;
	}

	public void setSetorComercial(
			gsan.cadastro.localidade.SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public DistritoOperacional getDistritoOperacional() {
		return this.distritoOperacional;
	}

	public void setDistritoOperacional(DistritoOperacional distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}

	public Bacia getBacia() {
		return this.bacia;
	}

	public void setBacia(Bacia bacia) {
		this.bacia = bacia;
	}

	public gsan.cadastro.localidade.Zeis getZeis() {
		return this.zeis;
	}

	public void setZeis(gsan.cadastro.localidade.Zeis zeis) {
		this.zeis = zeis;
	}

	public gsan.cadastro.localidade.QuadraPerfil getQuadraPerfil() {
		return this.quadraPerfil;
	}

	public void setQuadraPerfil(
			gsan.cadastro.localidade.QuadraPerfil quadraPerfil) {
		this.quadraPerfil = quadraPerfil;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("ibgeSetorCensitario");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bacia");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("zeis");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("quadraPerfil");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("areaTipo");

		filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, this.getId()));
		return filtroQuadra;
	}

	public AreaTipo getAreaTipo() {
		return areaTipo;
	}

	public void setAreaTipo(AreaTipo areaTipo) {
		this.areaTipo = areaTipo;
	}

	public RoteiroEmpresa getRoteiroEmpresa() {
		return roteiroEmpresa;
	}

	public void setRoteiroEmpresa(RoteiroEmpresa roteiroEmpresa) {
		this.roteiroEmpresa = roteiroEmpresa;
	}

	@Override
	public String[] retornarAtributosSelecionadosRegistro() {
		String[] atributos = {"roteiroEmpresa"};
		return atributos;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] atributos = {"numeroQuadra", "setorComercial.codigo", "setorComercial.localidade.descricao"};
		return atributos;
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"N�mero da quadra", "C�digo do Setor Comercial", "Localidade"};
		return labels;
	}
	
	@Override
	public void initializeLazy() {
		if (getSetorComercial() != null){
			getSetorComercial().initializeLazy();
		}
	}
	
	public String getDescricao(){
		return numeroQuadra +""; 
	}
	
	/**
	 * @return Retorna o campo indicadorIncrementoLote.
	 */
	public Short getIndicadorIncrementoLote() {
		return indicadorIncrementoLote;
	}

	/**
	 * @param indicadorIncrementoLote O indicadorIncrementoLote a ser setado.
	 */
	public void setIndicadorIncrementoLote(Short indicadorIncrementoLote) {
		this.indicadorIncrementoLote = indicadorIncrementoLote;
	}
	
	public Short getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(Short indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String getSetorQuadraFormatado() {
		if ( getSetorComercial() != null ) {
			this.setorQuadraFormatado = String.valueOf(getSetorComercial().getCodigo()) + " - " + getNumeroQuadra();
		}
		return this.setorQuadraFormatado;
	}

	public void setIndicadorAtualizacaoCadastral(Short indicadorAtualizacaoCadastral) {
		this.indicadorAtualizacaoCadastral = indicadorAtualizacaoCadastral;
	}
	
	public Short getIndicadorAtualizacaoCadastral() {
		return indicadorAtualizacaoCadastral;
	}
	
}
