/**
 * 
 */
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
package gsan.gui.atendimentopublico.ordemservico;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descri��o da classe
 * 
 * @author S�vio luiz
 * @date 13/11/2006
 */
public class InformarRetornoOSFiscalizacaoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idOrdemServico;
	private String nomeOrdemServico;
	private String indicadorEncerramentoOS;
	private String atendimentoMotivoEncerramento;
	private String parecerEncerramento;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String clienteUsuario;
	private String cpfCnpjCliente;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String ocorrencia;
	private String situacao;
	private String indicadorTipoMedicao;
	private String indicadorDocumentoEntregue;
	private String indicadorGeracaoDebito;
	private String indicarGeracaoDebitoAutoInfracao;
	private String idFuncionarioResponsavel;
	private String nomeFuncionarioResponsavel;
	private String idAutoInfracaoSituacao;
	private String dataEmissao;
	private String dataInicioRecurso;
	private String dataTerminoRecurso;
	private String observacao;
	private String quantidadeParcelas;

	private String indicadorAtualizarSituacaoLigacaoAguaEsgoto;
	private String indicadorAguaSituacao;
	private String indicadorEsgotoSituacao;
	private String indicadorLigacaoDataAtualiza;
	private String indicadorHidrometroCapacidade;
	private String indicadorAtualizacaoAutosInfracao;
	private String indicadorNotificacaoEsgoto;
	private String habilitaTipoMedicao;
	private String habilitaGeracaoDebito;
	private String codigoTipoRecebimentoOS;
	private String opcaoGeracao;
	
	private String situacaoLigacao;
	private String dataLigacaoEsgoto;
	private String indicadorLigacaoEsgoto;
	private String diametroLigacaoEsgoto;
	private String materialLigacaoEsgoto;
	private String perfilLigacaoEsgoto;
	private String percentualColeta;
	
	private String arquivoTipo;
	private String observacaoArquivo;
	private FormFile arquivo;
	
	public String getOpcaoGeracao() {
		return opcaoGeracao;
	}

	public void setOpcaoGeracao(String opcaoGeracao) {
		this.opcaoGeracao = opcaoGeracao;
	}

	public String getCodigoTipoRecebimentoOS() {
		return codigoTipoRecebimentoOS;
	}

	public void setCodigoTipoRecebimentoOS(String codigoTipoRecebimentoOS) {
		this.codigoTipoRecebimentoOS = codigoTipoRecebimentoOS;
	}

	/**
	 * @return Retorna o campo quantidadeParcelas.
	 */
	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	/**
	 * @param quantidadeParcelas O quantidadeParcelas a ser setado.
	 */
	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getIndicadorDocumentoEntregue() {
		return indicadorDocumentoEntregue;
	}

	public void setIndicadorDocumentoEntregue(String indicadorDocumentoEntregue) {
		this.indicadorDocumentoEntregue = indicadorDocumentoEntregue;
	}

	public String getIndicadorGeracaoDebito() {
		return indicadorGeracaoDebito;
	}

	public void setIndicadorGeracaoDebito(String indicadorGeracaoDebito) {
		this.indicadorGeracaoDebito = indicadorGeracaoDebito;
	}

	public String getIndicadorTipoMedicao() {
		return indicadorTipoMedicao;
	}

	public void setIndicadorTipoMedicao(String indicadorTipoMedicao) {
		this.indicadorTipoMedicao = indicadorTipoMedicao;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}

	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getIndicadorEncerramentoOS() {
		return indicadorEncerramentoOS;
	}

	public void setIndicadorEncerramentoOS(String indicadorEncerramentoOS) {
		this.indicadorEncerramentoOS = indicadorEncerramentoOS;
	}

	public String getAtendimentoMotivoEncerramento() {
		return atendimentoMotivoEncerramento;
	}

	public void setAtendimentoMotivoEncerramento(
			String atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataInicioRecurso() {
		return dataInicioRecurso;
	}

	public void setDataInicioRecurso(String dataInicioRecurso) {
		this.dataInicioRecurso = dataInicioRecurso;
	}

	public String getDataTerminoRecurso() {
		return dataTerminoRecurso;
	}

	public void setDataTerminoRecurso(String dataTerminoRecurso) {
		this.dataTerminoRecurso = dataTerminoRecurso;
	}

	public String getIdAutoInfracaoSituacao() {
		return idAutoInfracaoSituacao;
	}

	public void setIdAutoInfracaoSituacao(String idAutoInfracaoSituacao) {
		this.idAutoInfracaoSituacao = idAutoInfracaoSituacao;
	}

	public String getIdFuncionarioResponsavel() {
		return idFuncionarioResponsavel;
	}

	public void setIdFuncionarioResponsavel(String idFuncionarioResponsavel) {
		this.idFuncionarioResponsavel = idFuncionarioResponsavel;
	}

	public String getNomeFuncionarioResponsavel() {
		return nomeFuncionarioResponsavel;
	}

	public void setNomeFuncionarioResponsavel(String nomeFuncionarioResponsavel) {
		this.nomeFuncionarioResponsavel = nomeFuncionarioResponsavel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getIndicarGeracaoDebitoAutoInfracao() {
		return indicarGeracaoDebitoAutoInfracao;
	}

	public void setIndicarGeracaoDebitoAutoInfracao(
			String indicarGeracaoDebitoAutoInfracao) {
		this.indicarGeracaoDebitoAutoInfracao = indicarGeracaoDebitoAutoInfracao;
	}

	public String getIndicadorAtualizarSituacaoLigacaoAguaEsgoto() {
		return indicadorAtualizarSituacaoLigacaoAguaEsgoto;
	}

	public void setIndicadorAtualizarSituacaoLigacaoAguaEsgoto(
			String indicadorAtualizarSituacaoLigacaoAguaEsgoto) {
		this.indicadorAtualizarSituacaoLigacaoAguaEsgoto = indicadorAtualizarSituacaoLigacaoAguaEsgoto;
	}

	public String getIndicadorAguaSituacao() {
		return indicadorAguaSituacao;
	}

	public void setIndicadorAguaSituacao(String indicadorAguaSituacao) {
		this.indicadorAguaSituacao = indicadorAguaSituacao;
	}

	public String getIndicadorAtualizacaoAutosInfracao() {
		return indicadorAtualizacaoAutosInfracao;
	}

	public void setIndicadorAtualizacaoAutosInfracao(
			String indicadorAtualizacaoAutosInfracao) {
		this.indicadorAtualizacaoAutosInfracao = indicadorAtualizacaoAutosInfracao;
	}

	public String getIndicadorEsgotoSituacao() {
		return indicadorEsgotoSituacao;
	}

	public void setIndicadorEsgotoSituacao(String indicadorEsgotoSituacao) {
		this.indicadorEsgotoSituacao = indicadorEsgotoSituacao;
	}

	public String getIndicadorHidrometroCapacidade() {
		return indicadorHidrometroCapacidade;
	}

	public void setIndicadorHidrometroCapacidade(
			String indicadorHidrometroCapacidade) {
		this.indicadorHidrometroCapacidade = indicadorHidrometroCapacidade;
	}

	public String getIndicadorLigacaoDataAtualiza() {
		return indicadorLigacaoDataAtualiza;
	}

	public void setIndicadorLigacaoDataAtualiza(String indicadorLigacaoDataAtualiza) {
		this.indicadorLigacaoDataAtualiza = indicadorLigacaoDataAtualiza;
	}

	public String getHabilitaGeracaoDebito() {
		return habilitaGeracaoDebito;
	}

	public void setHabilitaGeracaoDebito(String habilitaGeracaoDebito) {
		this.habilitaGeracaoDebito = habilitaGeracaoDebito;
	}

	public String getHabilitaTipoMedicao() {
		return habilitaTipoMedicao;
	}

	public void setHabilitaTipoMedicao(String habilitaTipoMedicao) {
		this.habilitaTipoMedicao = habilitaTipoMedicao;
	}

	public String getIndicadorNotificacaoEsgoto() {
		return indicadorNotificacaoEsgoto;
	}

	public void setIndicadorNotificacaoEsgoto(String indicadorNotificacaoEsgoto) {
		this.indicadorNotificacaoEsgoto = indicadorNotificacaoEsgoto;
	}

	public String getDataLigacaoEsgoto() {
		return dataLigacaoEsgoto;
	}

	public void setDataLigacaoEsgoto(String dataLigacaoEsgoto) {
		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
	}

	public String getIndicadorLigacaoEsgoto() {
		return indicadorLigacaoEsgoto;
	}

	public void setIndicadorLigacaoEsgoto(String indicadorLigacaoEsgoto) {
		this.indicadorLigacaoEsgoto = indicadorLigacaoEsgoto;
	}

	public String getDiametroLigacaoEsgoto() {
		return diametroLigacaoEsgoto;
	}

	public void setDiametroLigacaoEsgoto(String diametroLigacaoEsgoto) {
		this.diametroLigacaoEsgoto = diametroLigacaoEsgoto;
	}

	public String getMaterialLigacaoEsgoto() {
		return materialLigacaoEsgoto;
	}

	public void setMaterialLigacaoEsgoto(String materialLigacaoEsgoto) {
		this.materialLigacaoEsgoto = materialLigacaoEsgoto;
	}

	public String getPerfilLigacaoEsgoto() {
		return perfilLigacaoEsgoto;
	}

	public void setPerfilLigacaoEsgoto(String perfilLigacaoEsgoto) {
		this.perfilLigacaoEsgoto = perfilLigacaoEsgoto;
	}

	public String getPercentualColeta() {
		return percentualColeta;
	}

	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public String getSituacaoLigacao() {
		return situacaoLigacao;
	}

	public void setSituacaoLigacao(String situacaoLigacao) {
		this.situacaoLigacao = situacaoLigacao;
	}


	public String getObservacaoArquivo() {
		return observacaoArquivo;
	}

	public void setObservacaoArquivo(String observacaoArquivo) {
		this.observacaoArquivo = observacaoArquivo;
	}

	public String getArquivoTipo() {
		return arquivoTipo;
	}

	public void setArquivoTipo(String arquivoTipo) {
		this.arquivoTipo = arquivoTipo;
	}

	public FormFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}






}
