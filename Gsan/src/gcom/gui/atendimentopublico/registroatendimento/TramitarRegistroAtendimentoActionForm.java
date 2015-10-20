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
package gcom.gui.atendimentopublico.registroatendimento;


import java.util.Date;

import org.apache.struts.action.ActionForm;

public class TramitarRegistroAtendimentoActionForm extends ActionForm{

	private static final long serialVersionUID = 1L;

	// Dados Gerais, Dados do Solicitante & Dados do Local de Ocorr�ncia
    private String numeroRA;
    private String situacaoRA;
    private String codigoSituacaoRA;
    private String numeroRaAssociado;
    private String situacaoRaAssociado;
    private String tipoSolicitacaoId;
    private String tipoSolicitacaoDescricao;
    private String tipoSolicitacaoIndicadorTarifaSocial;
    private String especificacaoId;
    private String especificacaoDescricao;    
    private String meioSolicitacaoId;
    private String meioSolicitacaoDescricao;    
    private String matriculaImovel;
    private String inscricaoImovel;
    private String dataAtendimento;
    private String horaAtendimento;
    private String dataPrevista;
    private String dataEncerramento;
    private String idMotivoEncerramento;
    private String motivoEncerramento;
    private String idClienteSolicitante;
    private String clienteSolicitante;
    private String idUnidadeSolicitante;
    private String unidadeSolicitante;
    private String nomeSolicitante;
    private String enderecoOcorrencia;
    private String pontoReferencia;
    private String bairroId;
    private String bairroDescricao;
    private String areaBairroId;
    private String areaBairroDescricao;
    private String localidadeId;
    private String localidadeDescricao;
    private String setorComercialId;
    private String setorComercialDescricao;
    private String quadraId;
    private String quadraDescricao;
    private String divisaoEsgotoId;
    private String divisaoEsgotoDescricao;
    private String unidadeAtendimentoId;
    private String unidadeAtendimentoDescricao;
    private String unidadeAtualId;
    private String unidadeAtualDescricao;
    private String unidadeAtualCodigoTipo;    
    private String unidadeAtualIdCentralizadora;
    
    // Dados da Tramita��o
    private String unidadeDestinoId;
    private String unidadeDestinoDescricao;
    private String unidadeDestinoIndicadorTramite;
    private String unidadeDestinoCodigoTipo;
    private String usuarioResponsavelId;
    private String usuarioResponsavelNome;
    private String dataTramite;
    private String horaTramite;
    private String parecerTramite;
    
    // Login do Usu�rio
    private String usuarioRegistroId;
    private String usuarioRegistroNome;
    private String usuarioRegistroUnidade;
    private String usuarioRegistroUnidadeIndicadorTarifaSocial;
    
    // Controle
	private String validaUnidadeDestino = "false";
	private String validaUsuarioResponsavel = "false";
	private String resetarTramitacao = "false";
	private Date dataConcorrenciaRA;
    
	public void resetarTramitacao(){
		this.setUnidadeDestinoId(null);
		this.setUnidadeDestinoDescricao(null);
		this.setUnidadeDestinoIndicadorTramite(null);
		this.setUnidadeDestinoCodigoTipo(null);
		this.setUsuarioResponsavelId(null);
		this.setUsuarioResponsavelNome(null);
		this.setDataTramite(null);
		this.setHoraTramite(null);
		this.setParecerTramite(null);
		this.setValidaUnidadeDestino("false");
		this.setValidaUsuarioResponsavel("false");
		this.setResetarTramitacao("false");
	}
	
	public String getResetarTramitacao() {
		return resetarTramitacao;
	}
	public void setResetarTramitacao(String resetarTramitacao) {
		this.resetarTramitacao = resetarTramitacao;
	}
	public String getAreaBairroDescricao() {
		return areaBairroDescricao;
	}
	public void setAreaBairroDescricao(String areaBairroDescricao) {
		this.areaBairroDescricao = areaBairroDescricao;
	}
	public String getAreaBairroId() {
		return areaBairroId;
	}
	public void setAreaBairroId(String areaBairroId) {
		this.areaBairroId = areaBairroId;
	}
	public String getBairroDescricao() {
		return bairroDescricao;
	}
	public void setBairroDescricao(String bairroDescricao) {
		this.bairroDescricao = bairroDescricao;
	}
	public String getBairroId() {
		return bairroId;
	}
	public void setBairroId(String bairroId) {
		this.bairroId = bairroId;
	}
	public String getClienteSolicitante() {
		return clienteSolicitante;
	}
	public void setClienteSolicitante(String clienteSolicitante) {
		this.clienteSolicitante = clienteSolicitante;
	}
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getDataTramite() {
		return dataTramite;
	}
	public void setDataTramite(String dataTramite) {
		this.dataTramite = dataTramite;
	}
	public String getDivisaoEsgotoDescricao() {
		return divisaoEsgotoDescricao;
	}
	public void setDivisaoEsgotoDescricao(String divisaoEsgotoDescricao) {
		this.divisaoEsgotoDescricao = divisaoEsgotoDescricao;
	}
	public String getDivisaoEsgotoId() {
		return divisaoEsgotoId;
	}
	public void setDivisaoEsgotoId(String divisaoEsgotoId) {
		this.divisaoEsgotoId = divisaoEsgotoId;
	}
	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}
	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}
	public String getEspecificacaoDescricao() {
		return especificacaoDescricao;
	}
	public void setEspecificacaoDescricao(String especificacaoDescricao) {
		this.especificacaoDescricao = especificacaoDescricao;
	}
	public String getEspecificacaoId() {
		return especificacaoId;
	}
	public void setEspecificacaoId(String especificacaoId) {
		this.especificacaoId = especificacaoId;
	}
	public String getHoraAtendimento() {
		return horaAtendimento;
	}
	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	public String getHoraTramite() {
		return horaTramite;
	}
	public void setHoraTramite(String horaTramite) {
		this.horaTramite = horaTramite;
	}
	public String getIdClienteSolicitante() {
		return idClienteSolicitante;
	}
	public void setIdClienteSolicitante(String idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
	}
	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}
	public String getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}
	public void setIdUnidadeSolicitante(String idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}
	public String getLocalidadeId() {
		return localidadeId;
	}
	public void setLocalidadeId(String localidadeId) {
		this.localidadeId = localidadeId;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMeioSolicitacaoDescricao() {
		return meioSolicitacaoDescricao;
	}
	public void setMeioSolicitacaoDescricao(String meioSolicitacaoDescricao) {
		this.meioSolicitacaoDescricao = meioSolicitacaoDescricao;
	}
	public String getMeioSolicitacaoId() {
		return meioSolicitacaoId;
	}
	public void setMeioSolicitacaoId(String meioSolicitacaoId) {
		this.meioSolicitacaoId = meioSolicitacaoId;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String getNumeroRaAssociado() {
		return numeroRaAssociado;
	}
	public void setNumeroRaAssociado(String numeroRaAssociado) {
		this.numeroRaAssociado = numeroRaAssociado;
	}
	public String getParecerTramite() {
		return parecerTramite;
	}
	public void setParecerTramite(String parecerTramite) {
		this.parecerTramite = parecerTramite;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	public String getQuadraDescricao() {
		return quadraDescricao;
	}
	public void setQuadraDescricao(String quadraDescricao) {
		this.quadraDescricao = quadraDescricao;
	}
	public String getQuadraId() {
		return quadraId;
	}
	public void setQuadraId(String quadraId) {
		this.quadraId = quadraId;
	}
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}
	public String getSetorComercialId() {
		return setorComercialId;
	}
	public void setSetorComercialId(String setorComercialId) {
		this.setorComercialId = setorComercialId;
	}
	public String getSituacaoRA() {
		return situacaoRA;
	}
	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}
	public String getSituacaoRaAssociado() {
		return situacaoRaAssociado;
	}
	public void setSituacaoRaAssociado(String situacaoRaAssociado) {
		this.situacaoRaAssociado = situacaoRaAssociado;
	}
	public String getTipoSolicitacaoDescricao() {
		return tipoSolicitacaoDescricao;
	}
	public void setTipoSolicitacaoDescricao(String tipoSolicitacaoDescricao) {
		this.tipoSolicitacaoDescricao = tipoSolicitacaoDescricao;
	}
	public String getTipoSolicitacaoId() {
		return tipoSolicitacaoId;
	}
	public void setTipoSolicitacaoId(String tipoSolicitacaoId) {
		this.tipoSolicitacaoId = tipoSolicitacaoId;
	}
	public String getUnidadeAtendimentoDescricao() {
		return unidadeAtendimentoDescricao;
	}
	public void setUnidadeAtendimentoDescricao(String unidadeAtendimentoDescricao) {
		this.unidadeAtendimentoDescricao = unidadeAtendimentoDescricao;
	}
	public String getUnidadeAtendimentoId() {
		return unidadeAtendimentoId;
	}
	public void setUnidadeAtendimentoId(String unidadeAtendimentoId) {
		this.unidadeAtendimentoId = unidadeAtendimentoId;
	}
	public String getUnidadeAtualDescricao() {
		return unidadeAtualDescricao;
	}
	public void setUnidadeAtualDescricao(String unidadeAtualDescricao) {
		this.unidadeAtualDescricao = unidadeAtualDescricao;
	}
	public String getUnidadeAtualId() {
		return unidadeAtualId;
	}
	public void setUnidadeAtualId(String unidadeAtualId) {
		this.unidadeAtualId = unidadeAtualId;
	}
	public String getUnidadeDestinoDescricao() {
		return unidadeDestinoDescricao;
	}
	public void setUnidadeDestinoDescricao(String unidadeDestinoDescricao) {
		this.unidadeDestinoDescricao = unidadeDestinoDescricao;
	}
	public String getUnidadeDestinoId() {
		return unidadeDestinoId;
	}
	public void setUnidadeDestinoId(String unidadeDestinoId) {
		this.unidadeDestinoId = unidadeDestinoId;
	}
	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}
	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
	}
	public String getUsuarioResponsavelId() {
		return usuarioResponsavelId;
	}
	public void setUsuarioResponsavelId(String usuarioResponsavelId) {
		this.usuarioResponsavelId = usuarioResponsavelId;
	}
	public String getUsuarioResponsavelNome() {
		return usuarioResponsavelNome;
	}
	public void setUsuarioResponsavelNome(String usuarioResponsavelNome) {
		this.usuarioResponsavelNome = usuarioResponsavelNome;
	}
	public String getValidaUnidadeDestino() {
		return validaUnidadeDestino;
	}
	public void setValidaUnidadeDestino(String validaUnidadeDestino) {
		this.validaUnidadeDestino = validaUnidadeDestino;
	}
	public String getValidaUsuarioResponsavel() {
		return validaUsuarioResponsavel;
	}
	public void setValidaUsuarioResponsavel(String validaUsuarioResponsavel) {
		this.validaUsuarioResponsavel = validaUsuarioResponsavel;
	}
	public String getUsuarioRegistroId() {
		return usuarioRegistroId;
	}
	public void setUsuarioRegistroId(String usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}
	public String getUsuarioRegistroNome() {
		return usuarioRegistroNome;
	}
	public void setUsuarioRegistroNome(String usuarioRegistroNome) {
		this.usuarioRegistroNome = usuarioRegistroNome;
	}
	public String getTipoSolicitacaoIndicadorTarifaSocial() {
		return tipoSolicitacaoIndicadorTarifaSocial;
	}
	public void setTipoSolicitacaoIndicadorTarifaSocial(
			String tipoSolicitacaoIndicadorTarifaSocial) {
		this.tipoSolicitacaoIndicadorTarifaSocial = tipoSolicitacaoIndicadorTarifaSocial;
	}
	public String getUsuarioRegistroUnidadeIndicadorTarifaSocial() {
		return usuarioRegistroUnidadeIndicadorTarifaSocial;
	}
	public void setUsuarioRegistroUnidadeIndicadorTarifaSocial(
			String usuarioRegistroUnidadeIndicadorTarifaSocial) {
		this.usuarioRegistroUnidadeIndicadorTarifaSocial = usuarioRegistroUnidadeIndicadorTarifaSocial;
	}
	public String getUsuarioRegistroUnidade() {
		return usuarioRegistroUnidade;
	}
	public void setUsuarioRegistroUnidade(String usuarioRegistroUnidade) {
		this.usuarioRegistroUnidade = usuarioRegistroUnidade;
	}
	public String getUnidadeDestinoIndicadorTramite() {
		return unidadeDestinoIndicadorTramite;
	}
	public void setUnidadeDestinoIndicadorTramite(
			String unidadeDestinoIndicadorTramite) {
		this.unidadeDestinoIndicadorTramite = unidadeDestinoIndicadorTramite;
	}
	public String getUnidadeDestinoCodigoTipo() {
		return unidadeDestinoCodigoTipo;
	}
	public void setUnidadeDestinoCodigoTipo(String unidadeDestinoCodigoTipo) {
		this.unidadeDestinoCodigoTipo = unidadeDestinoCodigoTipo;
	}
	public String getCodigoSituacaoRA() {
		return codigoSituacaoRA;
	}
	public void setCodigoSituacaoRA(String codigoSituacaoRA) {
		this.codigoSituacaoRA = codigoSituacaoRA;
	}
	public String getUnidadeAtualCodigoTipo() {
		return unidadeAtualCodigoTipo;
	}
	public void setUnidadeAtualCodigoTipo(String unidadeAtualCodigoTipo) {
		this.unidadeAtualCodigoTipo = unidadeAtualCodigoTipo;
	}
	public String getUnidadeAtualIdCentralizadora() {
		return unidadeAtualIdCentralizadora;
	}
	public void setUnidadeAtualIdCentralizadora(String unidadeAtualIdCentralizadora) {
		this.unidadeAtualIdCentralizadora = unidadeAtualIdCentralizadora;
	}

	public Date getDataConcorrenciaRA() {
		return dataConcorrenciaRA;
	}

	public void setDataConcorrenciaRA(Date dataConcorrenciaRA) {
		this.dataConcorrenciaRA = dataConcorrenciaRA;
	}
}