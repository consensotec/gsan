/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

/**
 * classe respons�vel por criar o relat�rio de religa��o de clientes inadimplentes
 * 
 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes
 * 
 * @author Hugo Leonardo
 *
 * @date 25/01/2011
 */
public class RelatorioReligacaoClientesInadiplentesBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String endereco;
	private String perfil;
	private String numeroOS;
	private String usuarioAberturaOS;
	private String nomeUsuarioAberturaOS;
	private String dataEncerramento;
	private String usuarioEncerramentoOS;
	private String nomeUsuarioEncerramentoOS;
	private String valorDebito;
	
	private String nomeCliente;
	private String cpf;
	private String tipoRelacao;

    public RelatorioReligacaoClientesInadiplentesBean(){
    	
    }
    
	public RelatorioReligacaoClientesInadiplentesBean(String matricula, String endereco, String perfil, 
			String numeroOS, String usuarioAberturaOS, String nomeUsuarioAberturaOS, String dataEncerramento, 
			String usuarioEncerramentoOS, String nomeUsuarioEncerramentoOS, String valorDebito){
		
		this.matricula = matricula;
		this.endereco = endereco;
		this.perfil = perfil;
		this.numeroOS = numeroOS;
		this.usuarioAberturaOS = usuarioAberturaOS;
		this.dataEncerramento = dataEncerramento;
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
		this.valorDebito = valorDebito;
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}
	
	public RelatorioReligacaoClientesInadiplentesBean(
			String nomeCliente, String cpf, String tipoRelacao, 
			String matricula, String endereco, String perfil, 
			String numeroOS, String usuarioAberturaOS, String nomeUsuarioAberturaOS, String dataEncerramento, 
			String usuarioEncerramentoOS, String nomeUsuarioEncerramentoOS, String valorDebito){
		
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.tipoRelacao = tipoRelacao;
		this.matricula = matricula;
		this.endereco = endereco;
		this.perfil = perfil;
		this.numeroOS = numeroOS;
		this.usuarioAberturaOS = usuarioAberturaOS;
		this.dataEncerramento = dataEncerramento;
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
		this.valorDebito = valorDebito;
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUsuarioAberturaOS() {
		return usuarioAberturaOS;
	}

	public void setUsuarioAberturaOS(String usuarioAberturaOS) {
		this.usuarioAberturaOS = usuarioAberturaOS;
	}

	public String getUsuarioEncerramentoOS() {
		return usuarioEncerramentoOS;
	}

	public void setUsuarioEncerramentoOS(String usuarioEncerramentoOS) {
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getNomeUsuarioAberturaOS() {
		return nomeUsuarioAberturaOS;
	}

	public void setNomeUsuarioAberturaOS(String nomeUsuarioAberturaOS) {
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
	}

	public String getNomeUsuarioEncerramentoOS() {
		return nomeUsuarioEncerramentoOS;
	}

	public void setNomeUsuarioEncerramentoOS(String nomeUsuarioEncerramentoOS) {
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}

}
