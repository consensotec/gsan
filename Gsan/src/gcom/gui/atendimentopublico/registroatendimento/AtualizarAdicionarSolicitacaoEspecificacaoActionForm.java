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

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descri��o da classe
 * 
 * @author Administrador
 * @date 13/11/2006
 */
public class AtualizarAdicionarSolicitacaoEspecificacaoActionForm extends
		ValidatorActionForm {

	
	private static final long serialVersionUID = 1L;

	private String descricaoSolicitacao;

	private String cabecalho;

	private String prazoPrevisaoAtendimento;

	private String indicadorPavimentoCalcada;

	private String indicadorPavimentoRua;

	private String indicadorLigacaoAgua;
	
	private String indicadorLigacaoEsgoto;
	
	private String indicadorPoco;
	
	private String indicadorCobrancaMaterial;

	private String indicadorParecerEncerramento;

	private String indicadorGerarDebito;

	private String indicadorGerarCredito;

	private String indicadorCliente;

	private String indicadorMatriculaImovel;

	private String idSituacaoImovel;

	private String idUnidadeTramitacao;

	private String descricaoUnidadeTramitacao;

	private String indicadorGeraOrdemServico;

	private String idServicoOS;

	private String descricaoServicoOS;

	private String idTipoServico;

	private String descricaoTipoServico;

	private String ordemExecucao;
	
	private String indicadorVerificarDebito;
	
	private String idDebitoTipo;
	
	private String descricaoDebitoTipo;
	
	private String valorDebito;
	
	private String indicadorPermiteAlterarValor;
	
	private String indicadorCobrarJuros;
	
	private String indicadorEncerramentoAutomatico;
    
    private String idTipoSolicitacao;
    
    private String idEspecificacao;
    
    private String indicadorUrgencia;

	private String indicadorInformarContaRA;
	
	private String indicadorInformarPagamentoDP;
	
	private String indicadorAlterarVencimentoAlternativo;
	
	private String indicadorLojaVirtual;
	
	//RM 5759
	private String indicadorPermiteCancelarDebito;
	
	private String indicadorTipoEspecificacaoListadoPopupConsultarImovel;
	
	private String indicadorDocumentoObrigatorio;

	//RM 5924
    private FormFile arquivo;
    private Integer idHelper; 
    
    //RM 6692
    private String observacao;
    
	public String getIdEspecificacao() {
        return idEspecificacao;
    }

    
    public String getIndicadorAlterarVencimentoAlternativo() {
		return indicadorAlterarVencimentoAlternativo;
	}


	public void setIndicadorAlterarVencimentoAlternativo(
			String indicadorAlterarVencimentoAlternativo) {
		this.indicadorAlterarVencimentoAlternativo = indicadorAlterarVencimentoAlternativo;
	}


	public void setIdEspecificacao(String idEspecificacao) {
        this.idEspecificacao = idEspecificacao;
    }

    
    public String getIdTipoSolicitacao() {
        return idTipoSolicitacao;
    }

    
    public void setIdTipoSolicitacao(String idTipoSolicitacao) {
        this.idTipoSolicitacao = idTipoSolicitacao;
    }

    /**
	 * @return Retorna o campo indicadorEncerramentoAutomatico.
	 */
	public String getIndicadorEncerramentoAutomatico() {
		return indicadorEncerramentoAutomatico;
	}

	/**
	 * @param indicadorEncerramentoAutomatico O indicadorEncerramentoAutomatico a ser setado.
	 */
	public void setIndicadorEncerramentoAutomatico(
			String indicadorEncerramentoAutomatico) {
		this.indicadorEncerramentoAutomatico = indicadorEncerramentoAutomatico;
	}

	public String getDescricaoServicoOS() {
		return descricaoServicoOS;
	}

	public void setDescricaoServicoOS(String descricaoServicoOS) {
		this.descricaoServicoOS = descricaoServicoOS;
	}

	public String getDescricaoSolicitacao() {
		return descricaoSolicitacao;
	}

	public void setDescricaoSolicitacao(String descricaoSolicitacao) {
		this.descricaoSolicitacao = descricaoSolicitacao;
	}

	public String getDescricaoUnidadeTramitacao() {
		return descricaoUnidadeTramitacao;
	}

	public void setDescricaoUnidadeTramitacao(String descricaoUnidadeTramitacao) {
		this.descricaoUnidadeTramitacao = descricaoUnidadeTramitacao;
	}

	public String getIdServicoOS() {
		return idServicoOS;
	}

	public void setIdServicoOS(String idServicoOS) {
		this.idServicoOS = idServicoOS;
	}

	public String getIdSituacaoImovel() {
		return idSituacaoImovel;
	}

	public void setIdSituacaoImovel(String idSituacaoImovel) {
		this.idSituacaoImovel = idSituacaoImovel;
	}

	public String getIdUnidadeTramitacao() {
		return idUnidadeTramitacao;
	}

	public void setIdUnidadeTramitacao(String idUnidadeTramitacao) {
		this.idUnidadeTramitacao = idUnidadeTramitacao;
	}

	public String getIndicadorCliente() {
		return indicadorCliente;
	}

	public void setIndicadorCliente(String indicadorCliente) {
		this.indicadorCliente = indicadorCliente;
	}

	public String getIndicadorCobrancaMaterial() {
		return indicadorCobrancaMaterial;
	}

	public void setIndicadorCobrancaMaterial(String indicadorCobrancaMaterial) {
		this.indicadorCobrancaMaterial = indicadorCobrancaMaterial;
	}

	public String getIndicadorGeraOrdemServico() {
		return indicadorGeraOrdemServico;
	}

	public void setIndicadorGeraOrdemServico(String indicadorGeraOrdemServico) {
		this.indicadorGeraOrdemServico = indicadorGeraOrdemServico;
	}

	public String getIndicadorGerarCredito() {
		return indicadorGerarCredito;
	}

	public void setIndicadorGerarCredito(String indicadorGerarCredito) {
		this.indicadorGerarCredito = indicadorGerarCredito;
	}

	public String getIndicadorGerarDebito() {
		return indicadorGerarDebito;
	}

	public void setIndicadorGerarDebito(String indicadorGerarDebito) {
		this.indicadorGerarDebito = indicadorGerarDebito;
	}

	public String getIndicadorParecerEncerramento() {
		return indicadorParecerEncerramento;
	}

	public void setIndicadorParecerEncerramento(
			String indicadorParecerEncerramento) {
		this.indicadorParecerEncerramento = indicadorParecerEncerramento;
	}

	public String getIndicadorPavimentoCalcada() {
		return indicadorPavimentoCalcada;
	}

	public void setIndicadorPavimentoCalcada(String indicadorPavimentoCalcada) {
		this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
	}

	public String getIndicadorPavimentoRua() {
		return indicadorPavimentoRua;
	}

	public void setIndicadorPavimentoRua(String indicadorPavimentoRua) {
		this.indicadorPavimentoRua = indicadorPavimentoRua;
	}

	public String getPrazoPrevisaoAtendimento() {
		return prazoPrevisaoAtendimento;
	}

	public void setPrazoPrevisaoAtendimento(String prazoPrevisaoAtendimento) {
		this.prazoPrevisaoAtendimento = prazoPrevisaoAtendimento;
	}

	public String getOrdemExecucao() {
		return ordemExecucao;
	}

	public void setOrdemExecucao(String ordemExecucao) {
		this.ordemExecucao = ordemExecucao;
	}

	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}

	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}

	public String getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getIndicadorMatriculaImovel() {
		return indicadorMatriculaImovel;
	}

	public void setIndicadorMatriculaImovel(String indicadorMatriculaImovel) {
		this.indicadorMatriculaImovel = indicadorMatriculaImovel;
	}

	public String getIndicadorLigacaoAgua() {
		return indicadorLigacaoAgua;
	}

	public void setIndicadorLigacaoAgua(String indicadorLigacaoAgua) {
		this.indicadorLigacaoAgua = indicadorLigacaoAgua;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public String getIndicadorVerificarDebito() {
		return indicadorVerificarDebito;
	}

	public void setIndicadorVerificarDebito(String indicadorVerificarDebito) {
		this.indicadorVerificarDebito = indicadorVerificarDebito;
	}

	public String getDescricaoDebitoTipo() {
		return descricaoDebitoTipo;
	}

	public void setDescricaoDebitoTipo(String descricaoDebitoTipo) {
		this.descricaoDebitoTipo = descricaoDebitoTipo;
	}

	public String getIdDebitoTipo() {
		return idDebitoTipo;
	}

	public void setIdDebitoTipo(String idDebitoTipo) {
		this.idDebitoTipo = idDebitoTipo;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getIndicadorCobrarJuros() {
		return indicadorCobrarJuros;
	}

	public void setIndicadorCobrarJuros(String indicadorCobrarJuros) {
		this.indicadorCobrarJuros = indicadorCobrarJuros;
	}

	public String getIndicadorPermiteAlterarValor() {
		return indicadorPermiteAlterarValor;
	}

	public void setIndicadorPermiteAlterarValor(String indicadorPermiteAlterarValor) {
		this.indicadorPermiteAlterarValor = indicadorPermiteAlterarValor;
	}

	public String getIndicadorUrgencia() {
		return indicadorUrgencia;
	}

	public void setIndicadorUrgencia(String indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}

	public String getIndicadorInformarContaRA() {
		return indicadorInformarContaRA;
	}

	public void setIndicadorInformarContaRA(String indicadorInformarContaRA) {
		this.indicadorInformarContaRA = indicadorInformarContaRA;
	}
	
	public String getIndicadorInformarPagamentoDP() {
		return indicadorInformarPagamentoDP;
	}

	public void setIndicadorInformarPagamentoDP(String indicadorInformarPagamentoDP) {
		this.indicadorInformarPagamentoDP = indicadorInformarPagamentoDP;
	}

	public String getIndicadorLojaVirtual() {
		return indicadorLojaVirtual;
	}

	public void setIndicadorLojaVirtual(String indicadorLojaVirtual) {
		this.indicadorLojaVirtual = indicadorLojaVirtual;
	}

	// RM 5759
	public String getIndicadorPermiteCancelarDebito() {
		return indicadorPermiteCancelarDebito;
	}


	public void setIndicadorPermiteCancelarDebito(
			String indicadorPermiteCancelarDebito) {
		this.indicadorPermiteCancelarDebito = indicadorPermiteCancelarDebito;
	}

	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public FormFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}


	public Integer getIdHelper() {
		return idHelper;
	}


	public void setIdHelper(Integer idHelper) {
		this.idHelper = idHelper;
	}


	public String getIndicadorTipoEspecificacaoListadoPopupConsultarImovel() {
		return indicadorTipoEspecificacaoListadoPopupConsultarImovel;
	}


	public void setIndicadorTipoEspecificacaoListadoPopupConsultarImovel(
			String indicadorTipoEspecificacaoListadoPopupConsultarImovel) {
		this.indicadorTipoEspecificacaoListadoPopupConsultarImovel = indicadorTipoEspecificacaoListadoPopupConsultarImovel;
	}


	public String getIndicadorDocumentoObrigatorio() {
		return indicadorDocumentoObrigatorio;
	}


	public void setIndicadorDocumentoObrigatorio(String indicadorDocumentoObrigatorio) {
		this.indicadorDocumentoObrigatorio = indicadorDocumentoObrigatorio;
	}


	public String getIndicadorLigacaoEsgoto() {
		return indicadorLigacaoEsgoto;
	}


	public void setIndicadorLigacaoEsgoto(String indicadorLigacaoEsgoto) {
		this.indicadorLigacaoEsgoto = indicadorLigacaoEsgoto;
	}


	public String getIndicadorPoco() {
		return indicadorPoco;
	}


	public void setIndicadorPoco(String indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}
	
	
	

}
