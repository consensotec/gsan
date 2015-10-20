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
package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

import java.util.Date;

public class RelatorioDadosTarifaSocialBean implements RelatorioBean {

	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idSetorComercial;
	private String nomeSetorComercial;
	private String matricula;
	private String endereco;
	private String nomeClienteProprietario;
	private String cpfClienteProprietario;
	private String nomeCliente;
	private String cpfCliente;
	private Date dataImplantacao;
	private Date dataExclusao;
	private String motivoExclusao;
	private Date dataUltimoRecadastramento;
	private String numeroRecadastramentos;
	private String areaConstruida;
	private String numeroIptu;
	private String numeroContratoCelpe;
	private String numeroCartao;
	private String tipoCartao;
	private Date dataValidadeCartao;
	private String numeroMesesAdesao;
	private String consumoMedioCelpe;
	private String valorRendaFamiliar;
	private String tipoRendaFamiliar;
	
	public RelatorioDadosTarifaSocialBean(String idGerenciaRegional, String nomeGerenciaRegional, String idLocalidade,
			String nomeLocalidade, String idSetorComercial, String nomeSetorComercial, String matricula, String endereco, String nomeClienteProprietario, 
			String cpfClienteProprietario, String nomeClienteUsuario, String cpfClienteUsuario, Date dataImplantacao,
			Date dataExclusao, String motivoExclusao, Date dataUltimoRecadastramento, String numeroRecadastramentos,
			String areaConstruida, String numeroIptu, String numeroContratoCelpe, String numeroCartao, String tipoCartao,
			Date dataValidadeCartao, String numeroMesesAdesao, String consumoMedioCelpe, String valorRendaFamiliar,
			String tipoRendaFamiliar) {
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.nomeSetorComercial = nomeSetorComercial;
		this.matricula = matricula;
		this.endereco = endereco;
		this.nomeClienteProprietario = nomeClienteProprietario;
		this.cpfClienteProprietario = cpfClienteProprietario;
		this.nomeCliente = nomeClienteUsuario;
		this.cpfCliente = cpfClienteUsuario;
		this.dataImplantacao = dataImplantacao;
		this.dataExclusao = dataExclusao;
		this.motivoExclusao = motivoExclusao;
		this.dataUltimoRecadastramento = dataUltimoRecadastramento;
		this.numeroRecadastramentos = numeroRecadastramentos;
		this.areaConstruida = areaConstruida;
		this.numeroIptu = numeroIptu;
		this.numeroContratoCelpe = numeroContratoCelpe;
		this.numeroCartao = numeroCartao;
		this.tipoCartao = tipoCartao;
		this.dataValidadeCartao = dataValidadeCartao;
		this.numeroMesesAdesao = numeroMesesAdesao;
		this.consumoMedioCelpe = consumoMedioCelpe;
		this.valorRendaFamiliar = valorRendaFamiliar;
		this.tipoRendaFamiliar = tipoRendaFamiliar;
		
	}

	public String getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public String getConsumoMedioCelpe() {
		return consumoMedioCelpe;
	}

	public void setConsumoMedioCelpe(String consumoMedioCelpe) {
		this.consumoMedioCelpe = consumoMedioCelpe;
	}

	public String getCpfClienteProprietario() {
		return cpfClienteProprietario;
	}

	public void setCpfClienteProprietario(String cpfClienteProprietario) {
		this.cpfClienteProprietario = cpfClienteProprietario;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfClienteUsuario) {
		this.cpfCliente = cpfClienteUsuario;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Date getDataImplantacao() {
		return dataImplantacao;
	}

	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public Date getDataUltimoRecadastramento() {
		return dataUltimoRecadastramento;
	}

	public void setDataUltimoRecadastramento(Date dataUltimoRecadastramento) {
		this.dataUltimoRecadastramento = dataUltimoRecadastramento;
	}

	public Date getDataValidadeCartao() {
		return dataValidadeCartao;
	}

	public void setDataValidadeCartao(Date dataValidadeCartao) {
		this.dataValidadeCartao = dataValidadeCartao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	public String getNomeClienteProprietario() {
		return nomeClienteProprietario;
	}

	public void setNomeClienteProprietario(String nomeClienteProprietario) {
		this.nomeClienteProprietario = nomeClienteProprietario;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeClienteUsuario) {
		this.nomeCliente = nomeClienteUsuario;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNumeroContratoCelpe() {
		return numeroContratoCelpe;
	}

	public void setNumeroContratoCelpe(String numeroContratoCelpe) {
		this.numeroContratoCelpe = numeroContratoCelpe;
	}

	public String getNumeroIptu() {
		return numeroIptu;
	}

	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public String getNumeroMesesAdesao() {
		return numeroMesesAdesao;
	}

	public void setNumeroMesesAdesao(String numeroMesesAdesao) {
		this.numeroMesesAdesao = numeroMesesAdesao;
	}

	public String getNumeroRecadastramentos() {
		return numeroRecadastramentos;
	}

	public void setNumeroRecadastramentos(String numeroRecadastramentos) {
		this.numeroRecadastramentos = numeroRecadastramentos;
	}

	public String getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public String getTipoRendaFamiliar() {
		return tipoRendaFamiliar;
	}

	public void setTipoRendaFamiliar(String tipoRendaFamiliar) {
		this.tipoRendaFamiliar = tipoRendaFamiliar;
	}

	public String getValorRendaFamiliar() {
		return valorRendaFamiliar;
	}

	public void setValorRendaFamiliar(String valorRendaFamiliar) {
		this.valorRendaFamiliar = valorRendaFamiliar;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	
	
}
