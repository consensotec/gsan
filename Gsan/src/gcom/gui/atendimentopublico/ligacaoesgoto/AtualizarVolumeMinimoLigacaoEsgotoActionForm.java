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
package gcom.gui.atendimentopublico.ligacaoesgoto;

import java.util.Date;

import org.apache.struts.action.ActionForm;


public class AtualizarVolumeMinimoLigacaoEsgotoActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	// OS
	private String idOrdemServico;
	private String nomeOrdemServico;
	//Im�vel
    private String matriculaImovel;
    private String inscricaoImovel;
    private String clienteUsuario;
    private String cpfCnpjCliente;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;
    private String categoriaImovel;
    private String qtdeEconomia;
    private String consumoTarifaId;
    //Dados da Liga��o
    private String veioEncerrarOS;
    private String consumoMinimoFixado;
    private Date dataConcorrencia;
    private String valorObtido;
    
	public Date getDataConcorrencia() {
		return dataConcorrencia;
	}

	public void setDataConcorrencia(Date dataConcorrencia) {
		this.dataConcorrencia = dataConcorrencia;
	}

	public String getValorObtido() {
		return valorObtido;
	}

	public void setValorObtido(String valorObtido) {
		this.valorObtido = valorObtido;
	}

	public String getConsumoTarifaId() {
		return consumoTarifaId;
	}

	public void setConsumoTarifaId(String consumoTarifaId) {
		this.consumoTarifaId = consumoTarifaId;
	}

	public String getCategoriaImovel() {
		return categoriaImovel;
	}

	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
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



	public String getQtdeEconomia() {
		return qtdeEconomia;
	}

	public void setQtdeEconomia(String qtdeEconomia) {
		this.qtdeEconomia = qtdeEconomia;
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

	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	public String getConsumoMinimoFixado() {
		return consumoMinimoFixado;
	}

	public void setConsumoMinimoFixado(String consumoMinimoFixado) {
		this.consumoMinimoFixado = consumoMinimoFixado;
	}
    
       
	
	

}
