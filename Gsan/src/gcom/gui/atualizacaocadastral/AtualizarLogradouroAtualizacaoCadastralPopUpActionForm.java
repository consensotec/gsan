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

package gcom.gui.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

public class AtualizarLogradouroAtualizacaoCadastralPopUpActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoLogradouro;
	private Integer idTipo;
	private Integer idTitulo;
	private String nome;   	
    private String nomePopular;
    private String nomeLoteamento;   
    private String idMunicipio;
    private String nomeMunicipio;
    private String codigoBairro;
    private String nomeBairro;
    private String codigoCEP;
    private String descricaoCEP;
    private String indicadorUso;  
    private String colecaoBairro; 
    private String colecaoCep;
    private String indicadorDesabilitarBotao;
    
	public String getCodigoLogradouro() {
		return codigoLogradouro;
	}
	public void setCodigoLogradouro(String codigoLogradouro) {
		this.codigoLogradouro = codigoLogradouro;
	}
	public Integer getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	public Integer getIdTitulo() {
		return idTitulo;
	}
	public void setIdTitulo(Integer idTitulo) {
		this.idTitulo = idTitulo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomePopular() {
		return nomePopular;
	}
	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}
	public String getNomeLoteamento() {
		return nomeLoteamento;
	}
	public void setNomeLoteamento(String nomeLoteamento) {
		this.nomeLoteamento = nomeLoteamento;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	public String getCodigoBairro() {
		return codigoBairro;
	}
	public void setCodigoBairro(String codigoBairro) {
		this.codigoBairro = codigoBairro;
	}
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public String getCodigoCEP() {
		return codigoCEP;
	}
	public void setCodigoCEP(String codigoCEP) {
		this.codigoCEP = codigoCEP;
	}
	public String getDescricaoCEP() {
		return descricaoCEP;
	}
	public void setDescricaoCEP(String descricaoCEP) {
		this.descricaoCEP = descricaoCEP;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getColecaoBairro() {
		return colecaoBairro;
	}
	public void setColecaoBairro(String colecaoBairro) {
		this.colecaoBairro = colecaoBairro;
	}
	public String getColecaoCep() {
		return colecaoCep;
	}
	public void setColecaoCep(String colecaoCep) {
		this.colecaoCep = colecaoCep;
	}
	public String getIndicadorDesabilitarBotao() {
		return indicadorDesabilitarBotao;
	}
	public void setIndicadorDesabilitarBotao(String indicadorDesabilitarBotao) {
		this.indicadorDesabilitarBotao = indicadorDesabilitarBotao;
	}    
	
}
