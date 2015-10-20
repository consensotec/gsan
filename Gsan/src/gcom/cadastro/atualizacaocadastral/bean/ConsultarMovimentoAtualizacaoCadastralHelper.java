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
package gcom.cadastro.atualizacaocadastral.bean;

import java.util.Date;


/**
 * Consultar Movimento Atualiza��o Cadastral 
 * 
 * @author Ana Maria
 * @date 03/06/2009
 */
public class ConsultarMovimentoAtualizacaoCadastralHelper {

	private Integer icAutorizado;
	
	private Integer argumento;
	
	private Integer idImovel;
	
	private Integer idCliente;
	
	private Integer qtdAlteracaoImovel;
	
	private Integer qtdAlteracaoCliente;
	
	private String nomeFuncionario;
	
	private String nomeCliente;
	
	private Date dataRealizacao;
	
	private String nomeEmpresa;
	
	private Integer idArquivo;
	
	private String inscricao;
	
	private String tipoClienteNovo;
	
	private Integer idRegistroAlterado;
	
	private Integer idTipoAlteracao;
	
	private Integer idImovelAtualizacaoCadastral;
	
	public ConsultarMovimentoAtualizacaoCadastralHelper(Integer icAutorizado, Integer argumento, Integer idImovel, Integer idCliente, Integer qtdAlteracaoImovel, Integer qtdAlteracaoCliente, String nomeFuncionario, String nomeCliente, Date dataRealizacao, String nomeEmpresa, Integer idArquivo, String inscricao, String tipoClienteNovo) {
		this.icAutorizado = icAutorizado;
		this.argumento = argumento;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.qtdAlteracaoImovel = qtdAlteracaoImovel;
		this.qtdAlteracaoCliente = qtdAlteracaoCliente;
		this.nomeFuncionario = nomeFuncionario;
		this.nomeCliente = nomeCliente;
		this.dataRealizacao = dataRealizacao;
		this.nomeEmpresa = nomeEmpresa;
		this.idArquivo = idArquivo;
		this.inscricao = inscricao;
		this.tipoClienteNovo = tipoClienteNovo;
	}

	public ConsultarMovimentoAtualizacaoCadastralHelper(){}

	public Integer getArgumento() {
		return argumento;
	}

	public void setArgumento(Integer argumento) {
		this.argumento = argumento;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Integer getQtdAlteracaoCliente() {
		return qtdAlteracaoCliente;
	}

	public void setQtdAlteracaoCliente(Integer qtdAlteracaoCliente) {
		this.qtdAlteracaoCliente = qtdAlteracaoCliente;
	}

	public Integer getQtdAlteracaoImovel() {
		return qtdAlteracaoImovel;
	}

	public void setQtdAlteracaoImovel(Integer qtdAlteracaoImovel) {
		this.qtdAlteracaoImovel = qtdAlteracaoImovel;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}	
		
	public Integer getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}

	public Integer getIcAutorizado() {
		return icAutorizado;
	}

	public void setIcAutorizado(Integer icAutorizado) {
		this.icAutorizado = icAutorizado;
	}
	
	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getTipoClienteNovo() {
		return tipoClienteNovo;
	}

	public void setTipoClienteNovo(String tipoClienteNovo) {
		this.tipoClienteNovo = tipoClienteNovo;
	}

	public Integer getIdRegistroAlterado() {
		return idRegistroAlterado;
	}

	public void setIdRegistroAlterado(Integer idRegistroAlterado) {
		this.idRegistroAlterado = idRegistroAlterado;
	}

	public Integer getIdTipoAlteracao() {
		return idTipoAlteracao;
	}

	public void setIdTipoAlteracao(Integer idTipoAlteracao) {
		this.idTipoAlteracao = idTipoAlteracao;
	}

	public Integer getIdImovelAtualizacaoCadastral() {
		return idImovelAtualizacaoCadastral;
	}

	public void setIdImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) {
		this.idImovelAtualizacaoCadastral = idImovelAtualizacaoCadastral;
	}
	
}
