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
package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarDocumentosCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String endereco; 
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String inscricaoTipo;
	private String localidadeOrigemID;
	private String nomeLocalidadeOrigem;
	private String setorComercialOrigemCD;
	private String nomeSetorComercialOrigem;
	private String setorComercialOrigemID;
	private String quadraOrigemNM;
	private String quadraMensagemOrigem;
	private String quadraOrigemID;
	private String loteOrigem;
	private String[] documentoEmissaoForma;
	private String[] cobrancaAcao;
	private String[] motivoNaoEntregaDocumento;
	private String[] imovelPerfil;
	private String dataEmissaoFim;
	private String dataEmissaoInicio;
	private String inscricaoImovel;
	private String localidadeDestinoID;
	private String nomeLocalidadeDestino;
	private String setorComercialDestinoCD;
	private String setorComercialDestinoID;
	private String nomeSetorComercialDestino;
	private String quadraDestinoNM;
	private String quadraMensagemDestino;
	private String quadraDestinoID;
	private String loteDestino;
	private String subloteOrigem;
	private String subloteDestino;
	private String valorInicial;
	private String valorFinal;
	private String mesAnoReferencia;
	private String[] idCategoria;
	private String[] idFirma;
	private String[] idCobrancaAcaoSituacao;
	private String[] idCobrancaDebitoSituacao;

	public String[] getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String[] idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String[] getIdFirma() {
		return idFirma;
	}
	public void setIdFirma(String[] idFirma) {
		this.idFirma = idFirma;
	}
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	public String getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String[] getImovelPerfil() {
		return imovelPerfil;
	}
	public void setImovelPerfil(String[] imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	public String[] getMotivoNaoEntregaDocumento() {
		return motivoNaoEntregaDocumento;
	}
	public void setMotivoNaoEntregaDocumento(String[] motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getInscricaoTipo() {
		return inscricaoTipo;
	}
	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}
	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}
	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}
	public String getLoteOrigem() {
		return loteOrigem;
	}
	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}
	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}
	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}
	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}
	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}
	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}
	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}
	public String getQuadraOrigemID() {
		return quadraOrigemID;
	}
	public void setQuadraOrigemID(String quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}
	public String getQuadraOrigemNM() {
		return quadraOrigemNM;
	}
	public void setQuadraOrigemNM(String quadraOrigemNM) {
		this.quadraOrigemNM = quadraOrigemNM;
	}
	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}
	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}
	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}
	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}
	public String[] getDocumentoEmissaoForma() {
		return documentoEmissaoForma;
	}
	public void setDocumentoEmissaoForma(String[] documentoEmissaoForma) {
		this.documentoEmissaoForma = documentoEmissaoForma;
	}
	public String[] getCobrancaAcao() {
		return cobrancaAcao;
	}
	public void setCobrancaAcao(String[] cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}
	public String getDataEmissaoFim() {
		return dataEmissaoFim;
	}
	public void setDataEmissaoFim(String dataEmissaoFim) {
		this.dataEmissaoFim = dataEmissaoFim;
	}
	public String getDataEmissaoInicio() {
		return dataEmissaoInicio;
	}
	public void setDataEmissaoInicio(String dataEmissaoInicio) {
		this.dataEmissaoInicio = dataEmissaoInicio;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}
	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}
	public String getLoteDestino() {
		return loteDestino;
	}
	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}
	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}
	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}
	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}
	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}
	public String getQuadraDestinoID() {
		return quadraDestinoID;
	}
	public void setQuadraDestinoID(String quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}
	public String getQuadraDestinoNM() {
		return quadraDestinoNM;
	}
	public void setQuadraDestinoNM(String quadraDestinoNM) {
		this.quadraDestinoNM = quadraDestinoNM;
	}
	public String getQuadraMensagemDestino() {
		return quadraMensagemDestino;
	}
	public void setQuadraMensagemDestino(String quadraMensagemDestino) {
		this.quadraMensagemDestino = quadraMensagemDestino;
	}
	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}
	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}
	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}
	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}
	public String getSubloteDestino() {
		return subloteDestino;
	}
	public void setSubloteDestino(String subloteDestino) {
		this.subloteDestino = subloteDestino;
	}
	public String getSubloteOrigem() {
		return subloteOrigem;
	}
	public void setSubloteOrigem(String subloteOrigem) {
		this.subloteOrigem = subloteOrigem;
	}
	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String[] getIdCobrancaAcaoSituacao() {
		return idCobrancaAcaoSituacao;
	}
	public void setIdCobrancaAcaoSituacao(String[] idCobrancaAcaoSituacao) {
		this.idCobrancaAcaoSituacao = idCobrancaAcaoSituacao;
	}
	public String[] getIdCobrancaDebitoSituacao() {
		return idCobrancaDebitoSituacao;
	}
	public void setIdCobrancaDebitoSituacao(String[] idCobrancaDebitoSituacao) {
		this.idCobrancaDebitoSituacao = idCobrancaDebitoSituacao;
	}

}
