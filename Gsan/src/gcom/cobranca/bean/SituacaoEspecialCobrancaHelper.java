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
package gcom.cobranca.bean;


public class SituacaoEspecialCobrancaHelper {
	
	private String idImovel;
	
	private String matricula;
	
	private String localidadeOrigemID;
	
	private String localidadeDestinoID;
	
	private String nomeLocalidadeOrigem;
	
	private String nomeLocalidadeDestino;
	
	private String setorComercialOrigemCD;
	
	private String setorComercialDestinoCD;
	
	private String setorComercialOrigemID;
	
	private String setorComercialDestinoID;
	
	private String nomeSetorComercialOrigem;
	
	private String nomeSetorComercialDestino;
	
	private String quadraOrigemNM;
	
	private String quadraDestinoNM;
	
	private String quadraMensagemOrigem;
	
	private String quadraMensagemDestino;
	
	private String quadraOrigemID;
	
	private String quadraDestinoID;
	
	private String loteOrigem;
	
	private String loteDestino;
	
	private String subloteOrigem;
	
	private String subloteDestino;
	
	private String quantidadeImoveisCOMSituacaoEspecialCobranca;
	
	private String quantidadeImoveisSEMSituacaoEspecialCobranca;
	
	private String quantidadeImoveisAtualizados;
	
	private String tipoSituacaoEspecialCobranca;
	
	private String motivoSituacaoEspecialCobranca;
	
	private String mesAnoReferenciaCobrancaInicial;
	
	private String mesAnoReferenciaCobrancaFinal;

	private String inscricaoTipo;
	
	private String idCobrancaSituacaoTipo;
	
	private String idCobrancaSituacaoMotivo;	

	private String liberarBotoes;
	
	private String codigoRotaInicial;
	
	private String codigoRotaFinal;
	
	private String sequencialRotaInicial;
	
	private String sequencialRotaFinal;
	
	private String observacaoInforma;
	
	private String observacaoRetira;
	
	private String idUsuarioInforma;
	
	private String idUsuarioRetira;
	
	private Integer idCategoria;
	
	private String dataFimSituacao;
	
	private String observacao;
	
	private String[] idsCategoria;
	
	private String indicadorComercial;
	    
	private String indicadorIndustrial;
	    
	private String indicadorResidencial;
	    
	private String indicadorPublico;
	
	private String mesAnoCobrancaRetirada;
	
	private String nomeUsuario;
	
	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDataFimSituacao() {
		return dataFimSituacao;
	}

	public void setDataFimSituacao(String dataFimSituacao) {
		this.dataFimSituacao = dataFimSituacao;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getLiberarBotoes() {
		return liberarBotoes;
	}

	public void setLiberarBotoes(String liberarBotoes) {
		this.liberarBotoes = liberarBotoes;
	}
	
	
	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	

	public String getQuantidadeImoveisAtualizados() {
		return quantidadeImoveisAtualizados;
	}

	public void setQuantidadeImoveisAtualizados(String quantidadeImoveisAtualizados) {
		this.quantidadeImoveisAtualizados = quantidadeImoveisAtualizados;
	}

	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
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

	public String getLoteDestino() {
		return loteDestino;
	}

	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}

	public String getLoteOrigem() {
		return loteOrigem;
	}

	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdCobrancaSituacaoMotivo() {
		return idCobrancaSituacaoMotivo;
	}

	public void setIdCobrancaSituacaoMotivo(String idCobrancaSituacaoMotivo) {
		this.idCobrancaSituacaoMotivo = idCobrancaSituacaoMotivo;
	}

	public String getIdCobrancaSituacaoTipo() {
		return idCobrancaSituacaoTipo;
	}

	public void setIdCobrancaSituacaoTipo(String idCobrancaSituacaoTipo) {
		this.idCobrancaSituacaoTipo = idCobrancaSituacaoTipo;
	}

	public String getMesAnoReferenciaCobrancaFinal() {
		return mesAnoReferenciaCobrancaFinal;
	}

	public void setMesAnoReferenciaCobrancaFinal(
			String mesAnoReferenciaCobrancaFinal) {
		this.mesAnoReferenciaCobrancaFinal = mesAnoReferenciaCobrancaFinal;
	}

	public String getMesAnoReferenciaCobrancaInicial() {
		return mesAnoReferenciaCobrancaInicial;
	}

	public void setMesAnoReferenciaCobrancaInicial(
			String mesAnoReferenciaCobrancaInicial) {
		this.mesAnoReferenciaCobrancaInicial = mesAnoReferenciaCobrancaInicial;
	}

	public String getMotivoSituacaoEspecialCobranca() {
		return motivoSituacaoEspecialCobranca;
	}

	public void setMotivoSituacaoEspecialCobranca(
			String motivoSituacaoEspecialCobranca) {
		this.motivoSituacaoEspecialCobranca = motivoSituacaoEspecialCobranca;
	}

	public String getTipoSituacaoEspecialCobranca() {
		return tipoSituacaoEspecialCobranca;
	}

	public void setTipoSituacaoEspecialCobranca(String tipoSituacaoEspecialCobranca) {
		this.tipoSituacaoEspecialCobranca = tipoSituacaoEspecialCobranca;
	}

	public String getQuantidadeImoveisCOMSituacaoEspecialCobranca() {
		return quantidadeImoveisCOMSituacaoEspecialCobranca;
	}

	public void setQuantidadeImoveisCOMSituacaoEspecialCobranca(
			String quantidadeImoveisCOMSituacaoEspecialCobranca) {
		this.quantidadeImoveisCOMSituacaoEspecialCobranca = quantidadeImoveisCOMSituacaoEspecialCobranca;
	}

	public String getQuantidadeImoveisSEMSituacaoEspecialCobranca() {
		return quantidadeImoveisSEMSituacaoEspecialCobranca;
	}

	public void setQuantidadeImoveisSEMSituacaoEspecialCobranca(
			String quantidadeImoveisSEMSituacaoEspecialCobranca) {
		this.quantidadeImoveisSEMSituacaoEspecialCobranca = quantidadeImoveisSEMSituacaoEspecialCobranca;
	}

	public String getObservacaoInforma() {
		return observacaoInforma;
	}

	public void setObservacaoInforma(String observacaoInforma) {
		this.observacaoInforma = observacaoInforma;
	}

	public String getObservacaoRetira() {
		return observacaoRetira;
	}

	public void setObservacaoRetira(String observacaoRetira) {
		this.observacaoRetira = observacaoRetira;
	}

	public String getIdUsuarioInforma() {
		return idUsuarioInforma;
	}

	public void setIdUsuarioInforma(String idUsuarioInforma) {
		this.idUsuarioInforma = idUsuarioInforma;
	}

	public String getIdUsuarioRetira() {
		return idUsuarioRetira;
	}

	public void setIdUsuarioRetira(String idUsuarioRetira) {
		this.idUsuarioRetira = idUsuarioRetira;
	}

	public String getIndicadorComercial() {
		return indicadorComercial;
	}

	public void setIndicadorComercial(String indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}

	public String getIndicadorIndustrial() {
		return indicadorIndustrial;
	}

	public void setIndicadorIndustrial(String indicadorIndustrial) {
		this.indicadorIndustrial = indicadorIndustrial;
	}

	public String getIndicadorPublico() {
		return indicadorPublico;
	}

	public void setIndicadorPublico(String indicadorPublico) {
		this.indicadorPublico = indicadorPublico;
	}

	public String getIndicadorResidencial() {
		return indicadorResidencial;
	}

	public void setIndicadorResidencial(String indicadorResidencial) {
		this.indicadorResidencial = indicadorResidencial;
	}

	public String getMesAnoCobrancaRetirada() {
		return mesAnoCobrancaRetirada;
	}

	public void setMesAnoCobrancaRetirada(String mesAnoCobrancaRetirada) {
		this.mesAnoCobrancaRetirada = mesAnoCobrancaRetirada;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
}
