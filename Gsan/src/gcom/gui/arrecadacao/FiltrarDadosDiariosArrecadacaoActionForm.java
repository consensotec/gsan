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
package gcom.gui.arrecadacao;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descri��o da classe 
 *
 * @author Fernanda Paiva
 * @date 15/05/2006
 */
public class FiltrarDadosDiariosArrecadacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String periodoArrecadacaoInicio;
	private String periodoArrecadacaoFim;
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String localidade;
	private String descricaoLocalidade;
	private String idElo;
	private String nomeElo;
	private String idArrecadador;
	private String nomeArrecadador;
	private String[] imovelPerfil;
	private String[] documentoTipo;
	private String descricaoDocumentoTipo;
	private String[] ligacaoAgua;
	private String[] ligacaoEsgoto;
	private String[] categoria;
	private String[] esferaPoder;
	private BigDecimal valor;
	private String localidadeDoElo;
	private String eloDiferente;
	private String perfilImovel;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String categoria2;
	private String esferaPoder2;
	private String tipoDocumento2;
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
	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	/**
	 * @return Retorna o campo periodoArrecadacaoFim.
	 */
	public String getPeriodoArrecadacaoFim() {
		return periodoArrecadacaoFim;
	}
	/**
	 * @param periodoArrecadacaoFim O periodoArrecadacaoFim a ser setado.
	 */
	public void setPeriodoArrecadacaoFim(String periodoArrecadacaoFim) {
		this.periodoArrecadacaoFim = periodoArrecadacaoFim;
	}
	/**
	 * @return Retorna o campo periodoArrecadacaoInicio.
	 */
	public String getPeriodoArrecadacaoInicio() {
		return periodoArrecadacaoInicio;
	}
	/**
	 * @param periodoArrecadacaoInicio O periodoArrecadacaoInicio a ser setado.
	 */
	public void setPeriodoArrecadacaoInicio(String periodoArrecadacaoInicio) {
		this.periodoArrecadacaoInicio = periodoArrecadacaoInicio;
	}
	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}
	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	/**
	 * @return Retorna o campo idElo.
	 */
	public String getIdElo() {
		return idElo;
	}
	/**
	 * @param idElo O idElo a ser setado.
	 */
	public void setIdElo(String idElo) {
		this.idElo = idElo;
	}
	/**
	 * @return Retorna o campo nomeElo.
	 */
	public String getNomeElo() {
		return nomeElo;
	}
	/**
	 * @param nomeElo O nomeElo a ser setado.
	 */
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}
	/**
	 * @return Retorna o campo idArrecadador.
	 */
	public String getIdArrecadador() {
		return idArrecadador;
	}
	/**
	 * @param idArrecadador O idArrecadador a ser setado.
	 */
	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}
	/**
	 * @return Retorna o campo nomeArrecadador.
	 */
	public String getNomeArrecadador() {
		return nomeArrecadador;
	}
	/**
	 * @param nomeArrecadador O nomeArrecadador a ser setado.
	 */
	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}
	/**
	 * @return Retorna o campo descricaoDocumentoTipo.
	 */
	public String getDescricaoDocumentoTipo() {
		return descricaoDocumentoTipo;
	}
	/**
	 * @param descricaoDocumentoTipo O descricaoDocumentoTipo a ser setado.
	 */
	public void setDescricaoDocumentoTipo(String descricaoDocumentoTipo) {
		this.descricaoDocumentoTipo = descricaoDocumentoTipo;
	}
	/**
	 * @return Retorna o campo documentoTipo.
	 */
	public String[] getDocumentoTipo() {
		return documentoTipo;
	}
	/**
	 * @param documentoTipo O documentoTipo a ser setado.
	 */
	public void setDocumentoTipo(String[] documentoTipo) {
		this.documentoTipo = documentoTipo;
	}
	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public String[] getImovelPerfil() {
		return imovelPerfil;
	}
	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(String[] imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	/**
	 * @return Retorna o campo ligacaoAgua.
	 */
	public String[] getLigacaoAgua() {
		return ligacaoAgua;
	}
	/**
	 * @param ligacaoAgua O ligacaoAgua a ser setado.
	 */
	public void setLigacaoAgua(String[] ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}
	/**
	 * @return Retorna o campo ligacaoEsgoto.
	 */
	public String[] getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}
	/**
	 * @param ligacaoEsgoto O ligacaoEsgoto a ser setado.
	 */
	public void setLigacaoEsgoto(String[] ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}
	/**
	 * @return Retorna o campo categoria.
	 */
	public String[] getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return Retorna o campo esferaPoder.
	 */
	public String[] getEsferaPoder() {
		return esferaPoder;
	}
	/**
	 * @param esferaPoder O esferaPoder a ser setado.
	 */
	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}
	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	/**
	 * @return Retorna o campo valor.
	 */
	public BigDecimal getValor() {
		return valor;
	}
	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	/**
	 * @return Retorna o campo localidadeDoElo.
	 */
	public String getLocalidadeDoElo() {
		return localidadeDoElo;
	}
	/**
	 * @param localidadeDoElo O localidadeDoElo a ser setado.
	 */
	public void setLocalidadeDoElo(String localidadeDoElo) {
		this.localidadeDoElo = localidadeDoElo;
	}
	/**
	 * @return Retorna o campo eloDiferente.
	 */
	public String getEloDiferente() {
		return eloDiferente;
	}
	/**
	 * @param eloDiferente O eloDiferente a ser setado.
	 */
	public void setEloDiferente(String eloDiferente) {
		this.eloDiferente = eloDiferente;
	}
	/**
	 * @return Retorna o campo perfilImovel.
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}
	/**
	 * @param perfilImovel O perfilImovel a ser setado.
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	/**
	 * @return Retorna o campo categoria2.
	 */
	public String getCategoria2() {
		return categoria2;
	}
	/**
	 * @param categoria2 O categoria2 a ser setado.
	 */
	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}
	/**
	 * @return Retorna o campo esferaPoder2.
	 */
	public String getEsferaPoder2() {
		return esferaPoder2;
	}
	/**
	 * @param esferaPoder2 O esferaPoder2 a ser setado.
	 */
	public void setEsferaPoder2(String esferaPoder2) {
		this.esferaPoder2 = esferaPoder2;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	/**
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	/**
	 * @return Retorna o campo tipoDocumento2.
	 */
	public String getTipoDocumento2() {
		return tipoDocumento2;
	}
	/**
	 * @param tipoDocumento2 O tipoDocumento2 a ser setado.
	 */
	public void setTipoDocumento2(String tipoDocumento2) {
		this.tipoDocumento2 = tipoDocumento2;
	}
}
