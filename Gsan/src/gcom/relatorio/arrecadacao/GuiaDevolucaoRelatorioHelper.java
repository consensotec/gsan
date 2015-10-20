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
package gcom.relatorio.arrecadacao;

import gcom.util.Util;
import java.math.BigDecimal;
import java.util.Date;


public class GuiaDevolucaoRelatorioHelper {
	
	private Integer sequencial;
	private BigDecimal valorDevolucao;
	private Integer idRegistroAtendimento;
	private String observacao;
	private Integer idMatriculaImovel;
	private Integer idCliente;
	private String nomeCliente;
	private String cpfCliente;
	private String cnpjCliente;
	private String identidadeCliente;	
	private String orgaoExpedidor;
	private String unidadeFederacao;
	private String funcionarioAnalista;
	private String funcionarioAutorizador;
	private String usuario;
	private Date dataValidade;
	
	private String endereco; 
	
	private String valorExtenso;

	private String conta;
	private Integer agencia;
	
	public String getValorExtenso() {
		return valorExtenso;
	}

	public void setValorExtenso(String valorExtenso) {
		this.valorExtenso = valorExtenso;
	}

	public GuiaDevolucaoRelatorioHelper(Integer sequencial, 
		   BigDecimal valorDevolucao, Integer idRegistroAtendimento, 
		   String observacao, Integer idMatriculaImovel,
		   Integer idCliente, String nomeCliente, String cpfCliente,
		   String cnpjCliente, String identidadeCliente, 
		   String orgaoExpedidor, String unidadeFederacao, String funcionarioAnalista,
		   String funcionarioAutorizador, String usuario, Date dataValidade) {
		super();
			
		this.sequencial = sequencial;
		this.valorDevolucao = valorDevolucao;
		this.idRegistroAtendimento = idRegistroAtendimento;
		this.observacao = observacao;
		this.idMatriculaImovel = idMatriculaImovel;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
		this.identidadeCliente = identidadeCliente;
		this.orgaoExpedidor = orgaoExpedidor;
		this.unidadeFederacao = unidadeFederacao;
		this.funcionarioAnalista = funcionarioAnalista;
		this.funcionarioAutorizador = funcionarioAutorizador;
		this.usuario = usuario;
		this.dataValidade = dataValidade;
	}

    public GuiaDevolucaoRelatorioHelper() {
    }

	public String getMatriculaFormatada(){
		String matriculaFormatada = Util.adicionarZerosEsquedaNumero(9, getIdMatriculaImovel().toString());
		matriculaFormatada = matriculaFormatada.substring(0, 8)
				+ "." + matriculaFormatada.substring(8, 9);
		return  matriculaFormatada ;
	}
	
	public String getRgFormatada(){
		String rg = "";
		if(getIdentidadeCliente() != null){
			if(getOrgaoExpedidor() != null && getUnidadeFederacao() != null){
			  rg = getIdentidadeCliente() + "-" + getOrgaoExpedidor().trim() + "/" + getUnidadeFederacao();
			}else{
			  rg = getIdentidadeCliente();	
			}
		}
		return  rg ;
	}
	
	public String getCpfFormatado() {
		String cpfFormatado = this.cpfCliente;

		if (cpfFormatado != null && cpfFormatado.length() == 11) {

			cpfFormatado = cpfFormatado.substring(0, 3) + "."
					+ cpfFormatado.substring(3, 6) + "."
					+ cpfFormatado.substring(6, 9) + "-"
					+ cpfFormatado.substring(9, 11);
		}
		
		return cpfFormatado;
	}
	
	/**
	 * Retorna o valor de cnpjFormatado
	 * 
	 * @return O valor de cnpjFormatado
	 */
	public String getCnpjFormatado() {
		String cnpjFormatado = this.cnpjCliente;
		String zeros = "";
		
		if (cnpjFormatado != null) {
			
			for (int a = 0; a < (14 - cnpjFormatado.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			cnpjFormatado = zeros.concat(cnpjFormatado);
			
			cnpjFormatado = cnpjFormatado.substring(0, 2) + "."
					+ cnpjFormatado.substring(2, 5) + "."
					+ cnpjFormatado.substring(5, 8) + "/"
					+ cnpjFormatado.substring(8, 12) + "-"
					+ cnpjFormatado.substring(12, 14);
		}
		
		return cnpjFormatado;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getIdMatriculaImovel() {
		return idMatriculaImovel;
	}

	public void setIdMatriculaImovel(Integer idMatriculaImovel) {
		this.idMatriculaImovel = idMatriculaImovel;
	}

	public Integer getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	public void setIdRegistroAtendimento(Integer idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public BigDecimal getValorDevolucao() {
		return valorDevolucao;
	}

	public void setValorDevolucao(BigDecimal valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getIdentidadeCliente() {
		return identidadeCliente;
	}

	public void setIdentidadeCliente(String identidadeCliente) {
		this.identidadeCliente = identidadeCliente;
	}
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	/**
	 * @return Retorna o campo funcionarioAnalista.
	 */
	public String getFuncionarioAnalista() {
		return funcionarioAnalista;
	}

	/**
	 * @param funcionarioAnalista O funcionarioAnalista a ser setado.
	 */
	public void setFuncionarioAnalista(String funcionarioAnalista) {
		this.funcionarioAnalista = funcionarioAnalista;
	}

	/**
	 * @return Retorna o campo funcionarioAutorizador.
	 */
	public String getFuncionarioAutorizador() {
		return funcionarioAutorizador;
	}

	/**
	 * @param funcionarioAutorizador O funcionarioAutorizador a ser setado.
	 */
	public void setFuncionarioAutorizador(String funcionarioAutorizador) {
		this.funcionarioAutorizador = funcionarioAutorizador;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Retorna o campo agencia.
	 */
	public Integer getAgencia() {
		return agencia;
	}

	/**
	 * @param agencia O agencia a ser setado.
	 */
	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	/**
	 * @return Retorna o campo conta.
	 */
	public String getConta() {
		return conta;
	}

	/**
	 * @param conta O conta a ser setado.
	 */
	public void setConta(String conta) {
		this.conta = conta;
	}

	/**
	 * @return Retorna o campo dataValidade.
	 */
	public Date getDataValidade() {
		return dataValidade;
	}

	/**
	 * @param dataValidade O dataValidade a ser setado.
	 */
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
