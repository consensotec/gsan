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


import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form Bean do Pesquisar Tipo Servico
 * 
 * @author Leandro Cavalcanti, Pedro Alexandre
 * @date 08/08/2006, 12/12/2007
 */
public class PesquisarTipoServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String dsTipoServico;
	private String dsAbreviadaTipoServico;
    private String subgrupoTipoServico;
    //private String indicadorPavimento;
    private String valorServicoInicial;
    private String valorServicoFinal;
    private String indicadorAtualizacaoComercio;
    private String indicadorServicoTerceirizado;
    private String codigoServico;
    private String tempoMedioExecucaoInicial;
    private String tempoMedioExecucaoFinal;
    private String tipoDebito;
    private String dsTipoDebito;
    private String tipoCredito;
    private String prioridadeServico;
    private String perfilServco;
    private String dsPerfilServco;
    private String tipoServicoReferencia;
    private String dsTipoServicoReferencia;
    private String atividadesTipoServico;
    private String dsAtividadesTipoServico;
    private String tipoServicoMaterial;
    private String dsTipoServicoMaterial;
    private String tipoPesquisa;
    private String tipoPesquisaAbreviada;
    
    private String dsAtividadeCor;
    private String dsTipoServicoReferenciaCor;
    private String dsMaterialCor;
    private String dsPerfilServicoCor;
   
    //  Atributos de controle da Cole��o 
    private Collection<Atividade> arrayAtividade = new ArrayList<Atividade>();
    private Collection<MaterialUnidade> arrayMateriais = new ArrayList<MaterialUnidade>();
    private Collection<Integer> atividades = new ArrayList<Integer>();
    private Collection<Integer> materiais = new ArrayList<Integer>();
    private String tipoServicoMaterialId;
    private String tipoServicoAtividadeId;
    private String tamanhoColecao = "0";
    private String method = "";
    private String deleteComponente;
    
    private String indicadorPavimentoRua;
    private String indicadorPavimentoCalcada;
    // Controle
    //private String validaTipoPerfilServico = "false";
    
    // Remove Atividade da cole��o de IdAtividades e Atividade
    public void removeServicoTipoAtividade (Integer id, Collection atividades, Collection arratividades){
		Collection colecaoId = atividades;
		Collection<Atividade> colecaoAtividades = arratividades;
		
		if(colecaoId != null && !colecaoId.isEmpty()){
			Iterator itera = colecaoId.iterator();
			
			while (itera.hasNext()) {
				Integer stp = (Integer) itera.next();
				
				if(stp == id.intValue()){
					itera.remove();
					
				}
			}
		}
		if(colecaoAtividades != null && !colecaoAtividades.isEmpty()){
			Iterator itera = colecaoAtividades.iterator();
			
			while (itera.hasNext()) {
				Atividade stp = (Atividade) itera.next();
				
				if(stp.getId() == id.intValue()){
					itera.remove();
					
				}
			}
		}
		
		this.setArrayAtividade(colecaoAtividades);
	}
    //  Remove Atividade da cole��o de IdMateriais e de MaterialUnidade
    public void removeServicoTipoMateriais (Integer id, Collection materiais,Collection arrayMateriais ){
		Collection<MaterialUnidade> colecaoArray = arrayMateriais;
		Collection colecao = arrayMateriais;
    	if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				MaterialUnidade stp = (MaterialUnidade) itera.next();
				
				if(stp.getId() == id.intValue()){
					itera.remove();
				}
			}
		}
    	if(colecaoArray != null && !colecaoArray.isEmpty()){
			Iterator itera = colecaoArray.iterator();
			
			while (itera.hasNext()) {
				MaterialUnidade stp = (MaterialUnidade) itera.next();
				
				if(stp.getId() == id.intValue()){
					itera.remove();
				}
			}
		}
    	
    	this.setArrayMateriais(colecaoArray);
	}
    
	public String getDeleteComponente() {
		return deleteComponente;
	}
	public void setDeleteComponente(String deleteComponente) {
		this.deleteComponente = deleteComponente;
	}
	public String getAtividadesTipoServico() {
		return atividadesTipoServico;
	}
	public void setAtividadesTipoServico(String atividadesTipoServico) {
		this.atividadesTipoServico = atividadesTipoServico;
	}
	public String getCodigoServico() {
		return codigoServico;
	}
	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	public String getDsAbreviadaTipoServico() {
		return dsAbreviadaTipoServico;
	}
	public void setDsAbreviadaTipoServico(String dsAbreviadaTipoServico) {
		this.dsAbreviadaTipoServico = dsAbreviadaTipoServico;
	}
	public String getDsTipoServico() {
		return dsTipoServico;
	}
	public void setDsTipoServico(String dsTipoServico) {
		this.dsTipoServico = dsTipoServico;
	}
	public String getIndicadorAtualizacaoComercio() {
		return indicadorAtualizacaoComercio;
	}
	public void setIndicadorAtualizacaoComercio(String indicadorAtualizacaoComercio) {
		this.indicadorAtualizacaoComercio = indicadorAtualizacaoComercio;
	}
	/*public String getIndicadorPavimento() {
		return indicadorPavimento;
	}
	public void setIndicadorPavimento(String indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}*/
	public String getIndicadorServicoTerceirizado() {
		return indicadorServicoTerceirizado;
	}
	public void setIndicadorServicoTerceirizado(String indicadorServicoTerceirizado) {
		this.indicadorServicoTerceirizado = indicadorServicoTerceirizado;
	}
	public String getPerfilServco() {
		return perfilServco;
	}
	public void setPerfilServco(String perfilServco) {
		this.perfilServco = perfilServco;
	}
	public String getPrioridadeServico() {
		return prioridadeServico;
	}
	public void setPrioridadeServico(String prioridadeServico) {
		this.prioridadeServico = prioridadeServico;
	}
	public String getSubgrupoTipoServico() {
		return subgrupoTipoServico;
	}
	public void setSubgrupoTipoServico(String subgrupoTipoServico) {
		this.subgrupoTipoServico = subgrupoTipoServico;
	}
	public String getTempoMedioExecucaoFinal() {
		return tempoMedioExecucaoFinal;
	}
	public void setTempoMedioExecucaoFinal(String tempoMedioExecucaoFinal) {
		this.tempoMedioExecucaoFinal = tempoMedioExecucaoFinal;
	}
	public String getTempoMedioExecucaoInicial() {
		return tempoMedioExecucaoInicial;
	}
	public void setTempoMedioExecucaoInicial(String tempoMedioExecucaoInicial) {
		this.tempoMedioExecucaoInicial = tempoMedioExecucaoInicial;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String getTipoDebito() {
		return tipoDebito;
	}
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	public String getTipoServicoReferencia() {
		return tipoServicoReferencia;
	}
	public void setTipoServicoReferencia(String tipoServicoReferencia) {
		this.tipoServicoReferencia = tipoServicoReferencia;
	}
	public String getDsAtividadesTipoServico() {
		return dsAtividadesTipoServico;
	}
	public void setDsAtividadesTipoServico(String dsAtividadesTipoServico) {
		this.dsAtividadesTipoServico = dsAtividadesTipoServico;
	}
	public String getDsperfilServco() {
		return dsPerfilServco;
	}
	public void setDsperfilServco(String dsperfilServco) {
		this.dsPerfilServco = dsperfilServco;
	}
	public String getDsTipoDebito() {
		return dsTipoDebito;
	}
	public void setDsTipoDebito(String dsTipoDebito) {
		this.dsTipoDebito = dsTipoDebito;
	}
	public String getDsTipoServicoReferencia() {
		return dsTipoServicoReferencia;
	}
	public void setDsTipoServicoReferencia(String dsTipoServicoReferencia) {
		this.dsTipoServicoReferencia = dsTipoServicoReferencia;
	}
	public String getValorServicoFinal() {
		return valorServicoFinal;
	}
	public void setValorServicoFinal(String valorServicoFinal) {
		this.valorServicoFinal = valorServicoFinal;
	}
	public String getValorServicoInicial() {
		return valorServicoInicial;
	}
	public void setValorServicoInicial(String valorServicoinicial) {
		this.valorServicoInicial = valorServicoinicial;
	}
	public String getDsTipoServicoMaterial() {
		return dsTipoServicoMaterial;
	}
	public void setDsTipoServicoMaterial(String dsTipoServicoMaterial) {
		this.dsTipoServicoMaterial = dsTipoServicoMaterial;
	}
	public String getTipoServicoMaterial() {
		return tipoServicoMaterial;
	}
	public void setTipoServicoMaterial(String tipoServicoMaterial) {
		this.tipoServicoMaterial = tipoServicoMaterial;
	}
	public String getDsPerfilServco() {
		return dsPerfilServco;
	}
	public void setDsPerfilServco(String dsPerfilServco) {
		this.dsPerfilServco = dsPerfilServco;
	}
	


	public String getTamanhoColecao() {
		return tamanhoColecao;
	}
	public void setTamanhoColecao(String tamanhoColecao) {
		this.tamanhoColecao = tamanhoColecao;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}



	public Collection<Integer> getAtividades() {
		return atividades;
	}
	public void setAtividades(Collection<Integer> atividades) {
		this.atividades = atividades;
	}
	public Collection<Integer> getMateriais() {
		return materiais;
	}
	public void setMateriais(Collection<Integer> materiais) {
		this.materiais = materiais;
	}
	public String getTipoServicoAtividadeId() {
		return tipoServicoAtividadeId;
	}

	public void setTipoServicoAtividadeId(String tipoServicoAtividadeId) {
		this.tipoServicoAtividadeId = tipoServicoAtividadeId;
	}

	public String getTipoServicoMaterialId() {
		return tipoServicoMaterialId;
	}

	public void setTipoServicoMaterialId(String tipoServicoMaterialId) {
		this.tipoServicoMaterialId = tipoServicoMaterialId;
	}
	public Collection<Atividade> getArrayAtividade() {
		return arrayAtividade;
	}
	public void setArrayAtividade(Collection<Atividade> arrayAtividade) {
		this.arrayAtividade = arrayAtividade;
	}
	public Collection<MaterialUnidade> getArrayMateriais() {
		return arrayMateriais;
	}
	public void setArrayMateriais(Collection<MaterialUnidade> arrayMateriais) {
		this.arrayMateriais = arrayMateriais;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getTipoPesquisaAbreviada() {
		return tipoPesquisaAbreviada;
	}
	public void setTipoPesquisaAbreviada(String tipoPesquisaAbreviada) {
		this.tipoPesquisaAbreviada = tipoPesquisaAbreviada;
	}
	public String getDsAtividadeCor() {
		return dsAtividadeCor;
	}
	public void setDsAtividadeCor(String dsAtividadeCor) {
		this.dsAtividadeCor = dsAtividadeCor;
	}
	public String getDsTipoServicoReferenciaCor() {
		return dsTipoServicoReferenciaCor;
	}
	public void setDsTipoServicoReferenciaCor(String dsTipoServicoReferenciaCor) {
		this.dsTipoServicoReferenciaCor = dsTipoServicoReferenciaCor;
	}
	public String getDsMaterialCor() {
		return dsMaterialCor;
	}
	public void setDsMaterialCor(String dsMaterialCor) {
		this.dsMaterialCor = dsMaterialCor;
	}
	public String getDsPerfilServicoCor() {
		return dsPerfilServicoCor;
	}
	public void setDsPerfilServicoCor(String dsPerfilServicoCor) {
		this.dsPerfilServicoCor = dsPerfilServicoCor;
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
	

	
}