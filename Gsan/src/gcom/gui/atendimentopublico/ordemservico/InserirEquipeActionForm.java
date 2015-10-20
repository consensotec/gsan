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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipeComponentes;
import gcom.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class InserirEquipeActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	// Dados da Equipe
    private String nomeEquipe;
    private String placaVeiculo;
    private String cargaTrabalhoDia;
    private UnidadeOrganizacional unidadeOrganizacional;
    private String unidadeOrganizacionalId;
    private String unidadeOrganizacionalDescricao;
    private ServicoPerfilTipo servicoPerfilTipo;
    private String tipoPerfilServicoId;
    private String tipoPerfilServicoDescricao;
    private String qtdeComponentesEquipe = "-1";
    
    private String codigoDdd;
    private String numeroTelefone;
    private String numeroImei;
    private String cdUsuarioRespExecServico;
    private String nomeUsuarioRespExecServico;
    private String indicadorProgramacaoAutomatica;
    
    // Equipe Componente
    private Collection<EquipeComponentes> equipeComponentes = new ArrayList<EquipeComponentes>();
    private String equipeComponenteId;
    private String equipeComponenteNome;
    private Funcionario funcionario;
    private String funcionarioId;
    private String funcionarioName;
    private String responsavelId;
    private String responsavelDescricao;
    
    // Controle
    private String validaUnidadeOrganizacional = "false";
    private String validaTipoPerfilServico = "false";
    private String validaFuncionario = "false";
    private String method = "";
    private String tamanhoColecao = "0";
    private String deleteComponente;
    private String tamanhoColecaoEquipeEquipamenosEspeciais = "0";
    
    
   
    public String getTamanhoColecaoEquipeEquipamenosEspeciais() {
		return tamanhoColecaoEquipeEquipamenosEspeciais;
	}
	public void setTamanhoColecaoEquipeEquipamenosEspeciais(
			String tamanhoColecaoEquipeEquipamenosEspeciais) {
		this.tamanhoColecaoEquipeEquipamenosEspeciais = tamanhoColecaoEquipeEquipamenosEspeciais;
	}
	// Equipamentos Especiais
    private Collection<EquipeEquipamentosEspeciais> equipeEquipamentosEspeciais = new ArrayList<EquipeEquipamentosEspeciais>();
    private String equipamentosEspeciasId;
    private String descricao;
    private String quantidade;
    private String deleteEquipamento;

	public String getEquipamentosEspeciasId() {
		return equipamentosEspeciasId;
	}
	public void setEquipamentosEspeciasId(String equipamentosEspeciasId) {
		this.equipamentosEspeciasId = equipamentosEspeciasId;
	}
	
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Collection<EquipeEquipamentosEspeciais> getEquipeEquipamentosEspeciais() {
		return equipeEquipamentosEspeciais;
	}
	public void setEquipeEquipamentosEspeciais(
			Collection<EquipeEquipamentosEspeciais> equipeEquipamentosEspeciais) {
		this.equipeEquipamentosEspeciais = equipeEquipamentosEspeciais;
	}

	/**
	 * @return Retorna a carga de trabalho por dia.
	 */
	public String getCargaTrabalhoDia() {
		return cargaTrabalhoDia;
	}
	/**
	 * @param cargaTrabalhoDia A carga de trabalho por dia a ser setada.
	 */
	public void setCargaTrabalhoDia(String cargaTrabalhoDia) {
		this.cargaTrabalhoDia = cargaTrabalhoDia;
	}
    /**
	 * @return Retorna o nome da equipe.
	 */
	public String getNomeEquipe() {
		return nomeEquipe;
	}
	/**
	 * @param nomeEquipe O nome da equipe a ser setado.
	 */
	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}
    /**
	 * @return Retorna a placa do ve�culo.
	 */
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	/**
	 * @param placaVeiculo A placa do ve�culo a ser setada.
	 */
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
    /**
	 * @return Retorna a descri��o do tipo de perfil do servi�o.
	 */
	public String getTipoPerfilServicoDescricao() {
		return tipoPerfilServicoDescricao;
	}
	/**
	 * @param tipoPerfilServicoDescricao A descri��o do tipo de perfil de servi�o a ser setada.
	 */
	public void setTipoPerfilServicoDescricao(String tipoPerfilServicoDescricao) {
		this.tipoPerfilServicoDescricao = tipoPerfilServicoDescricao;
	}
    /**
	 * @return Retorna o id do tipo de perfil do servi�o.
	 */
	public String getTipoPerfilServicoId() {
		return tipoPerfilServicoId;
	}
	/**
	 * @param tipoPerfilServicoDescricao O id do tipo de perfil de servi�o a ser setado.
	 */
	public void setTipoPerfilServicoId(String tipoPerfilServicoId) {
		this.tipoPerfilServicoId = tipoPerfilServicoId;
	}
    /**
	 * @return Retorna a descri��o da unidade organizacional.
	 */
	public String getUnidadeOrganizacionalDescricao() {
		return unidadeOrganizacionalDescricao;
	}
	/**
	 * @param unidadeOrganizacionalDescricao A descri��o da unidade organizacional a ser setada.
	 */
	public void setUnidadeOrganizacionalDescricao(
			String unidadeOrganizacionalDescricao) {
		this.unidadeOrganizacionalDescricao = unidadeOrganizacionalDescricao;
	}
    /**
	 * @return Retorna o id da unidade organizacional.
	 */
	public String getUnidadeOrganizacionalId() {
		return unidadeOrganizacionalId;
	}
	/**
	 * @param unidadeOrganizacionalId O id da unidade organizacional a ser setado.
	 */
	public void setUnidadeOrganizacionalId(String unidadeOrganizacionalId) {
		this.unidadeOrganizacionalId = unidadeOrganizacionalId;
	}
	public String getValidaTipoPerfilServico() {
		return validaTipoPerfilServico;
	}
	public void setValidaTipoPerfilServico(String validaTipoPerfilServico) {
		this.validaTipoPerfilServico = validaTipoPerfilServico;
	}
	public String getValidaUnidadeOrganizacional() {
		return validaUnidadeOrganizacional;
	}
	public void setValidaUnidadeOrganizacional(String validaUnidadeOrganizacional) {
		this.validaUnidadeOrganizacional = validaUnidadeOrganizacional;
	}
	public ServicoPerfilTipo getServicoPerfilTipo() {
		return servicoPerfilTipo;
	}
	public void setServicoPerfilTipo(ServicoPerfilTipo servicoPerfilTipo) {
		this.servicoPerfilTipo = servicoPerfilTipo;
	}
	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}
	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}
	public String getQtdeComponentesEquipe() {
		return qtdeComponentesEquipe;
	}
	public void setQtdeComponentesEquipe(String qtdeComponentesEquipe) {
		this.qtdeComponentesEquipe = qtdeComponentesEquipe;
	}
	public String getEquipeComponenteId() {
		return equipeComponenteId;
	}
	public void setEquipeComponenteId(String equipeComponenteId) {
		this.equipeComponenteId = equipeComponenteId;
	}
	public String getEquipeComponenteNome() {
		return equipeComponenteNome;
	}
	public void setEquipeComponenteNome(String equipeComponenteNome) {
		this.equipeComponenteNome = equipeComponenteNome;
	}
	public String getFuncionarioId() {
		return funcionarioId;
	}
	public void setFuncionarioId(String funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
	public String getFuncionarioName() {
		return funcionarioName;
	}
	public void setFuncionarioName(String funcionarioName) {
		this.funcionarioName = funcionarioName;
	}
	public String getResponsavelDescricao() {
		return responsavelDescricao;
	}
	public void setResponsavelDescricao(String responsavelDescricao) {
		this.responsavelDescricao = responsavelDescricao;
	}
	public String getResponsavelId() {
		return responsavelId;
	}
	public void setResponsavelId(String responsavelId) {
		this.responsavelId = responsavelId;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Collection<EquipeComponentes> getEquipeComponentes() {
		return equipeComponentes;
	}
	public void setEquipeComponentes(Collection<EquipeComponentes> equipeComponentes) {
		this.equipeComponentes = equipeComponentes;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getValidaFuncionario() {
		return validaFuncionario;
	}
	public void setValidaFuncionario(String validaFuncionario) {
		this.validaFuncionario = validaFuncionario;
	}
	public String getTamanhoColecao() {
		return tamanhoColecao;
	}
	public void setTamanhoColecao(String tamanhoColecao) {
		this.tamanhoColecao = tamanhoColecao;
	}
	public String getDeleteComponente() {
		return deleteComponente;
	}
	public void setDeleteComponente(String deleteComponente) {
		this.deleteComponente = deleteComponente;
	}
	public String getCodigoDdd() {
		return codigoDdd;
	}
	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}
	public String getNumeroImei() {
		return numeroImei;
	}
	public void setNumeroImei(String numeroImei) {
		this.numeroImei = numeroImei;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	public String getDeleteEquipamento() {
		return deleteEquipamento;
	}
	public void setDeleteEquipamento(String deleteEquipamento) {
		this.deleteEquipamento = deleteEquipamento;
	}
	public String getCdUsuarioRespExecServico() {
		return cdUsuarioRespExecServico;
	}
	public void setCdUsuarioRespExecServico(String idUsuarioRespExecServico) {
		this.cdUsuarioRespExecServico = idUsuarioRespExecServico;
	}
	public String getNomeUsuarioRespExecServico() {
		return nomeUsuarioRespExecServico;
	}
	public void setNomeUsuarioRespExecServico(String nomeUsuarioRespExecServico) {
		this.nomeUsuarioRespExecServico = nomeUsuarioRespExecServico;
	}
	public String getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}
	public void setIndicadorProgramacaoAutomatica(
			String indicadorProgramacaoAutomatica) {
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}
}
