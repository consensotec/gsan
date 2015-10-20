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

package gcom.gui.atualizacaocadastral;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Jonathan Marcos
 * @since 23/10/2014
 */
public class FiltrarDadosCadastraisImoveisInconsistentesDMActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	private String periodoMovimentoInicial;
	private String periodoMovimentoFinal;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String descricaoSetorComercial;
	private Integer[] idQuadra;
	private Integer[] idQuadraSelecionados;
	private String idCadastrador;
	private String indicadorSituacaoMovimento;
	private String idTipoInconsistencia;
	private String idImovel;
	private String inscricaoImovel;
	private String codigoCliente;
	private String nomeCliente;
	private String numeroDocumento;
	private String dataRecebimento;
	
	private String tipoPesquisa;
	private String[] idRegistro;
	private String[] idRegistroImovel;
	private String idParametroTabelaAtualizacaoCadastral;
	
	public void limpar(){
		setCodigoCliente("");
		setNomeCliente("");
		setDescricaoLocalidade("");
		setDescricaoSetorComercial("");
		setIdCadastrador("-1");
		setIdEmpresa("-1");
		setIdImovel("");
		setIdLocalidade("");
		setIdParametroTabelaAtualizacaoCadastral("");
		setIdSetorComercial("");
		setIdTipoInconsistencia("-1");
		setIndicadorSituacaoMovimento("");
		setTipoPesquisa("");
		setPeriodoMovimentoInicial("");
		setPeriodoMovimentoFinal("");
		setNumeroDocumento("");
		setInscricaoImovel("");
		setIdQuadra(null);
		setIdQuadraSelecionados(null);
		setIdRegistro(null);
	}
	
	public void limparMovimento(){
		setIdEmpresa("-1");
		setPeriodoMovimentoInicial("");
		setPeriodoMovimentoFinal("");
		setIdLocalidade("");
		setDescricaoLocalidade("");
		setIdSetorComercial("");
		setDescricaoSetorComercial("");
		setIdQuadra(null);
		setIdQuadraSelecionados(null);
		setIdCadastrador("-1");
		setIdTipoInconsistencia("-1");
		setIndicadorSituacaoMovimento("");
		setIdRegistro(null);
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getPeriodoMovimentoInicial() {
		return periodoMovimentoInicial;
	}
	public void setPeriodoMovimentoInicial(String periodoMovimentoInicial) {
		this.periodoMovimentoInicial = periodoMovimentoInicial;
	}

	public String getPeriodoMovimentoFinal() {
		return periodoMovimentoFinal;
	}
	public void setPeriodoMovimentoFinal(String periodoMovimentoFinal) {
		this.periodoMovimentoFinal = periodoMovimentoFinal;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public Integer[] getIdQuadra() {
		return idQuadra;
	}
	public void setIdQuadra(Integer[] idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer[] getIdQuadraSelecionados() {
		return idQuadraSelecionados;
	}
	
	public String getIdQuadraSelecionadosFormatado() {
		StringBuilder sb = new StringBuilder("");
		if(idQuadraSelecionados!=null){
			for(int i=0; i<idQuadraSelecionados.length; i++){
				sb.append(idQuadraSelecionados[i].toString());
				if(i!=(idQuadraSelecionados.length-1))sb.append(",");
			}
		}
		return sb.toString();
	}
	
	public void setIdQuadraSelecionados(Integer[] idQuadraSelecionados) {
		this.idQuadraSelecionados = idQuadraSelecionados;
	}

	public String getIdCadastrador() {
		return idCadastrador;
	}
	public void setIdCadastrador(String idCadastrador) {
		this.idCadastrador = idCadastrador;
	}

	public String getIndicadorSituacaoMovimento() {
		return indicadorSituacaoMovimento;
	}
	public void setIndicadorSituacaoMovimento(String indicadorSituacaoMovimento) {
		this.indicadorSituacaoMovimento = indicadorSituacaoMovimento;
	}

	public String getIdTipoInconsistencia() {
		return idTipoInconsistencia;
	}
	public void setIdTipoInconsistencia(String idTipoInconsistencia) {
		this.idTipoInconsistencia = idTipoInconsistencia;
	}

	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String[] getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(String[] idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String[] getIdRegistroImovel() {
		return idRegistroImovel;
	}
	public void setIdRegistroImovel(String[] idRegistroImovel) {
		this.idRegistroImovel = idRegistroImovel;
	}

	public String getIdParametroTabelaAtualizacaoCadastral() {
		return idParametroTabelaAtualizacaoCadastral;
	}
	public void setIdParametroTabelaAtualizacaoCadastral(
			String idParametroTabelaAtualizacaoCadastral) {
		this.idParametroTabelaAtualizacaoCadastral = idParametroTabelaAtualizacaoCadastral;
	}

	public String getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
}