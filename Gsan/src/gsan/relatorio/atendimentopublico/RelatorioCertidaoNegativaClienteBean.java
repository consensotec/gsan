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
package gsan.relatorio.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gsan.relatorio.RelatorioBean;

/**
 * classe responsável por criar a certidao negativa
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioCertidaoNegativaClienteBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String cliente;
	private String responsavel;
	private String idImovel;
	private String situacaoLigacaoAgua;
	private String nomeUsuario;
	private String endereco;
	private String enderecoCompleto;
	private String cpfCnpj;
	private String todasMatriculasDoCliente;
	
	// Dados do Imóvel
	private String nomeClienteUsuario;
	private Integer matriculaImovel;
	private String enderecoImovel;
	private String bairro;
	private String CEP;
	private String municipio;
	private String localidade;
	private String inscricaoImovel;
	private String categoria;
	private String subcategoria;
	private Short economias;
	private String ligacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	private String situacaoPoco;
	private String descricaoAbreviadaEmpresa;
	private String descricaoEmpresa;
	private String enderecoEmpresa;
	private String cepEmpresa;
	private String CNPJEmpresa;
	private String inscricaoEstadualEmpresa;
	private String siteEmpresa;
	private String zeroOitossentosEmpresa;
	private String perfilImovel;
	private String area;
	private String numeroHidrometro;	
	private Boolean imovelComParcelamento;
	
	private String unidadeNegocio;	
	private String enderecoCompletoImovel;	
	
	private JRBeanCollectionDataSource arrayJrItens;
	private ArrayList arrayRelatorioCertidaoNegativaBean;
	
	private Collection<RelatorioCertidaoNegativaItemBean> itens;
	
	private Boolean indicadorContaVencidas;
	
	public RelatorioCertidaoNegativaClienteBean() {}
	
	/**
	 * @return Retorna o campo cliente.
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	/**
	 * @return Retorna o campo responsavel.
	 */
	public String getResponsavel() {
		return responsavel;
	}
	/**
	 * @param responsavel O responsavel a ser setado.
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public String getTodasMatriculasDoCliente() {
		return todasMatriculasDoCliente;
	}

	public void setTodasMatriculasDoCliente(String todasMatriculasDoCliente) {
		this.todasMatriculasDoCliente = todasMatriculasDoCliente;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Short getEconomias() {
		return economias;
	}

	public void setEconomias(Short economias) {
		this.economias = economias;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public String getSituacaoPoco() {
		return situacaoPoco;
	}

	public void setSituacaoPoco(String situacaoPoco) {
		this.situacaoPoco = situacaoPoco;
	}

	public String getDescricaoAbreviadaEmpresa() {
		return descricaoAbreviadaEmpresa;
	}

	public void setDescricaoAbreviadaEmpresa(String descricaoAbreviadaEmpresa) {
		this.descricaoAbreviadaEmpresa = descricaoAbreviadaEmpresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(String enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}

	public String getCepEmpresa() {
		return cepEmpresa;
	}

	public void setCepEmpresa(String cepEmpresa) {
		this.cepEmpresa = cepEmpresa;
	}

	public String getCNPJEmpresa() {
		return CNPJEmpresa;
	}

	public void setCNPJEmpresa(String cNPJEmpresa) {
		CNPJEmpresa = cNPJEmpresa;
	}

	public String getInscricaoEstadualEmpresa() {
		return inscricaoEstadualEmpresa;
	}

	public void setInscricaoEstadualEmpresa(String inscricaoEstadualEmpresa) {
		this.inscricaoEstadualEmpresa = inscricaoEstadualEmpresa;
	}

	public String getSiteEmpresa() {
		return siteEmpresa;
	}

	public void setSiteEmpresa(String siteEmpresa) {
		this.siteEmpresa = siteEmpresa;
	}

	public String getZeroOitossentosEmpresa() {
		return zeroOitossentosEmpresa;
	}

	public void setZeroOitossentosEmpresa(String zeroOitossentosEmpresa) {
		this.zeroOitossentosEmpresa = zeroOitossentosEmpresa;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public Boolean getImovelComParcelamento() {
		return imovelComParcelamento;
	}

	public void setImovelComParcelamento(Boolean imovelComParcelamento) {
		this.imovelComParcelamento = imovelComParcelamento;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getEnderecoCompletoImovel() {
		return enderecoCompletoImovel;
	}

	public void setEnderecoCompletoImovel(String enderecoCompletoImovel) {
		this.enderecoCompletoImovel = enderecoCompletoImovel;
	}

	public Collection<RelatorioCertidaoNegativaItemBean> getItens() {
		return itens;
	}

	public void setItens(Collection<RelatorioCertidaoNegativaItemBean> itens) {
		this.itens = itens;
		
		this.arrayRelatorioCertidaoNegativaBean = new ArrayList();
		this.arrayRelatorioCertidaoNegativaBean
				.addAll(itens);
		this.arrayJrItens = new JRBeanCollectionDataSource(
				this.arrayRelatorioCertidaoNegativaBean);	
		
	}

	public JRBeanCollectionDataSource getArrayJrItens() {
		return arrayJrItens;
	}

	public void setArrayJrItens(JRBeanCollectionDataSource arrayJrItens) {
		this.arrayJrItens = arrayJrItens;
	}

	public ArrayList getArrayRelatorioCertidaoNegativaBean() {
		return arrayRelatorioCertidaoNegativaBean;
	}

	public void setArrayRelatorioCertidaoNegativaBean(ArrayList arrayRelatorioCertidaoNegativaBean) {
		this.arrayRelatorioCertidaoNegativaBean = arrayRelatorioCertidaoNegativaBean;
	}

	public Boolean getIndicadorContaVencidas() {
		return indicadorContaVencidas;
	}

	public void setIndicadorContaVencidas(Boolean indicadorContaVencidas) {
		this.indicadorContaVencidas = indicadorContaVencidas;
	}

	

	
	
}
