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
* Ivan S�rgio Virginio da Silva J�nior
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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0766] - Relatorio Boletim de Ordens de Servico Concluidas
 * 
 * @author Ivan S�rgio
 * @date 07/05/2008
 * 
 */
public class RelatorioBoletimOrdensServicoConcluidasBean implements RelatorioBean {

	private String referenciaEncerramentoMes;
	private String referenciaEncerramentoAno;
	private String situacao;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idLocalInstalacao;
	private String descricaoLocalInstalacao;
	private String nomeAbreviadoFirma;
	private String inscricao;
	private String idOrdemServico;
	private String tipoServico;
	private String trocaProtecao;
	private String trocaRegistro;
	private String dataGeracaoOrdemServico;
	private String dataEncerramentoOrdemServico;
	private String dataFiscalizacao1;
	private String dataFiscalizacao2;
	private String dataFiscalizacao3;
	private String paga;
	private String idImovel;
	private String codigoSetorComercial;
	private String idSetorComercial;
	
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public RelatorioBoletimOrdensServicoConcluidasBean () {}

	public String getDataEncerramentoOrdemServico() {
		return dataEncerramentoOrdemServico;
	}

	public void setDataEncerramentoOrdemServico(String dataEncerramentoOrdemServico) {
		this.dataEncerramentoOrdemServico = dataEncerramentoOrdemServico;
	}

	public String getDataFiscalizacao1() {
		return dataFiscalizacao1;
	}

	public void setDataFiscalizacao1(String dataFiscalizacao1) {
		this.dataFiscalizacao1 = dataFiscalizacao1;
	}

	public String getDataFiscalizacao2() {
		return dataFiscalizacao2;
	}

	public void setDataFiscalizacao2(String dataFiscalizacao2) {
		this.dataFiscalizacao2 = dataFiscalizacao2;
	}

	public String getDataFiscalizacao3() {
		return dataFiscalizacao3;
	}

	public void setDataFiscalizacao3(String dataFiscalizacao3) {
		this.dataFiscalizacao3 = dataFiscalizacao3;
	}

	public String getDataGeracaoOrdemServico() {
		return dataGeracaoOrdemServico;
	}

	public void setDataGeracaoOrdemServico(String dataGeracaoOrdemServico) {
		this.dataGeracaoOrdemServico = dataGeracaoOrdemServico;
	}

	public String getDescricaoLocalInstalacao() {
		return descricaoLocalInstalacao;
	}

	public void setDescricaoLocalInstalacao(String descricaoLocalInstalacao) {
		this.descricaoLocalInstalacao = descricaoLocalInstalacao;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdLocalInstalacao() {
		return idLocalInstalacao;
	}

	public void setIdLocalInstalacao(String idLocalInstalacao) {
		this.idLocalInstalacao = idLocalInstalacao;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeAbreviadoFirma() {
		return nomeAbreviadoFirma;
	}

	public void setNomeAbreviadoFirma(String nomeAbreviadoFirma) {
		this.nomeAbreviadoFirma = nomeAbreviadoFirma;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getPaga() {
		return paga;
	}

	public void setPaga(String paga) {
		this.paga = paga;
	}

	public String getReferenciaEncerramentoAno() {
		return referenciaEncerramentoAno;
	}

	public void setReferenciaEncerramentoAno(String referenciaEncerramentoAno) {
		this.referenciaEncerramentoAno = referenciaEncerramentoAno;
	}

	public String getReferenciaEncerramentoMes() {
		return referenciaEncerramentoMes;
	}

	public void setReferenciaEncerramentoMes(String referenciaEncerramentoMes) {
		this.referenciaEncerramentoMes = referenciaEncerramentoMes;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getTrocaProtecao() {
		return trocaProtecao;
	}

	public void setTrocaProtecao(String trocaProtecao) {
		this.trocaProtecao = trocaProtecao;
	}

	public String getTrocaRegistro() {
		return trocaRegistro;
	}

	public void setTrocaRegistro(String trocaRegistro) {
		this.trocaRegistro = trocaRegistro;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
}
