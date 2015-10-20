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


import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form Bean do Pesquisar Equipe
 * 
 * @author Leandro Cavalcanti
 * @date 08/08/2006
 */
public class ConsultarTipoServicoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	private String idTipoServico;
	private String dsTipoServico;
	private String dsAbreviadaTipoServico;
    private String subgrupoTipoServico;
    private String indicadorPavimento;
    private String valorServico;
    private String indicadorAtualizacaoComercio;
    private String indicadorServicoTerceirizado;
    private String codigoServico;
    private String tempoMedioExecucaoInicial;
    private String tempoMedioExecucaoFinal;
    private String tipoDebito;
    private String tipoCredito;
    private String prioridadeServico;
    private String perfilServco;
    private String tipoServicoReferencia;
    private String atividadesTipoServico;
    private String ordemExecucao;
    private String tipoServicoMaterial;
    private String qtdPadrao;
    private Collection arrayAtividade = new ArrayList();
    private Collection arrayMateriais = new ArrayList();

    // Controle
    private String mostrarPavimento;
    private String mostrarComercial;
    private String mostrarTerceirizado;
    private String mostrarServico;
	
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
	public String getIndicadorPavimento() {
		return indicadorPavimento;
	}
	public void setIndicadorPavimento(String indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}
	public String getIndicadorServicoTerceirizado() {
		return indicadorServicoTerceirizado;
	}
	public void setIndicadorServicoTerceirizado(String indicadorServicoTerceirizado) {
		this.indicadorServicoTerceirizado = indicadorServicoTerceirizado;
	}
	public String getMostrarComercial() {
		return mostrarComercial;
	}
	public void setMostrarComercial(String mostrarComercial) {
		this.mostrarComercial = mostrarComercial;
	}
	public String getMostrarPavimento() {
		return mostrarPavimento;
	}
	public void setMostrarPavimento(String mostrarPavimento) {
		this.mostrarPavimento = mostrarPavimento;
	}
	public String getMostrarServico() {
		return mostrarServico;
	}
	public void setMostrarServico(String mostrarServico) {
		this.mostrarServico = mostrarServico;
	}
	public String getMostrarTerceirizado() {
		return mostrarTerceirizado;
	}
	public void setMostrarTerceirizado(String mostrarTerceirizado) {
		this.mostrarTerceirizado = mostrarTerceirizado;
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
	public String getTipoServicoMaterial() {
		return tipoServicoMaterial;
	}
	public void setTipoServicoMaterial(String tipoServicoMaterial) {
		this.tipoServicoMaterial = tipoServicoMaterial;
	}
	public String getTipoServicoReferencia() {
		return tipoServicoReferencia;
	}
	public void setTipoServicoReferencia(String tipoServicoReferencia) {
		this.tipoServicoReferencia = tipoServicoReferencia;
	}
	public String getValorServico() {
		return valorServico;
	}
	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}
	public String getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public String getTempoMedioExecucaoFinal() {
		return tempoMedioExecucaoFinal;
	}
	public void setTempoMedioExecucaoFinal(String tempoMedioExecucaoFinal) {
		this.tempoMedioExecucaoFinal = tempoMedioExecucaoFinal;
	}
	public String getOrdemExecucao() {
		return ordemExecucao;
	}
	public void setOrdemExecucao(String ordemExecucao) {
		this.ordemExecucao = ordemExecucao;
	}
	public String getQtdPadrao() {
		return qtdPadrao;
	}
	public void setQtdPadrao(String qtdPadrao) {
		this.qtdPadrao = qtdPadrao;
	}
	public Collection getArrayAtividade() {
		return arrayAtividade;
	}
	public void setArrayAtividade(Collection arrayAtividade) {
		this.arrayAtividade = arrayAtividade;
	}
	public Collection getArrayMateriais() {
		return arrayMateriais;
	}
	public void setArrayMateriais(Collection arrayMateriais) {
		this.arrayMateriais = arrayMateriais;
	}
	
    
    
	
   
}