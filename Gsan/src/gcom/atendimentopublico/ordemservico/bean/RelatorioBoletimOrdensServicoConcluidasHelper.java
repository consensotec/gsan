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
package gcom.atendimentopublico.ordemservico.bean;



/**
 * [UC0766] Gerar Relatorio Boletim de Ordens de Servico Concluidas
 * 
 * @author Ivan S�rgio
 * @date 07/05/2008
 *  
 */
public class RelatorioBoletimOrdensServicoConcluidasHelper {
	public Integer idOrdemServico;
	public Integer anoMesReferenciaBoletim;
	public Short codigoFiscalizaco;
	public Short indicadorTorcaProtecaoHidrometro;
	public Short indicadorTorcaRegistroHidrometro;
	public Integer idTipoServico;
	public Integer idLocalidade;
	public String descricaoLocalidade;
	public Integer idLocalInstalacaoHidrometro;
	public String descricaoLocalInstalacaoHidrometro;
	public String descricaoAbreviadaFirma;
	public Integer codigoSetorComercial;
	public Integer numeroQuadra;
	public Short lote;
	public Short subLote;
	public String dataGeracaoOrdemServico;
	public String dataEncerramentoOrdemServico;
	public String dataFiscalizacao1;
	public String dataFiscalizacao2;
	public String dataFiscalizacao3;
	public String dataEncerramentoBoletim;
	public Integer idImovel;
	public Integer idSetorComercial;
	
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getAnoMesReferenciaBoletim() {
		return anoMesReferenciaBoletim;
	}
	public void setAnoMesReferenciaBoletim(Integer anoMesReferenciaBoletim) {
		this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
	}
	public Short getCodigoFiscalizaco() {
		return codigoFiscalizaco;
	}
	public void setCodigoFiscalizaco(Short codigoFiscalizaco) {
		this.codigoFiscalizaco = codigoFiscalizaco;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getDataEncerramentoBoletim() {
		return dataEncerramentoBoletim;
	}
	public void setDataEncerramentoBoletim(String dataEncerramentoBoletim) {
		this.dataEncerramentoBoletim = dataEncerramentoBoletim;
	}
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
	public String getDescricaoAbreviadaFirma() {
		return descricaoAbreviadaFirma;
	}
	public void setDescricaoAbreviadaFirma(String descricaoAbreviadaFirma) {
		this.descricaoAbreviadaFirma = descricaoAbreviadaFirma;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoLocalInstalacaoHidrometro() {
		return descricaoLocalInstalacaoHidrometro;
	}
	public void setDescricaoLocalInstalacaoHidrometro(
			String descricaoLocalInstalacaoHidrometro) {
		this.descricaoLocalInstalacaoHidrometro = descricaoLocalInstalacaoHidrometro;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdLocalInstalacaoHidrometro() {
		return idLocalInstalacaoHidrometro;
	}
	public void setIdLocalInstalacaoHidrometro(Integer idLocalInstalacaoHidrometro) {
		this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
	}
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public Integer getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(Integer idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public Short getIndicadorTorcaProtecaoHidrometro() {
		return indicadorTorcaProtecaoHidrometro;
	}
	public void setIndicadorTorcaProtecaoHidrometro(
			Short indicadorTorcaProtecaoHidrometro) {
		this.indicadorTorcaProtecaoHidrometro = indicadorTorcaProtecaoHidrometro;
	}
	public Short getIndicadorTorcaRegistroHidrometro() {
		return indicadorTorcaRegistroHidrometro;
	}
	public void setIndicadorTorcaRegistroHidrometro(
			Short indicadorTorcaRegistroHidrometro) {
		this.indicadorTorcaRegistroHidrometro = indicadorTorcaRegistroHidrometro;
	}
	public Short getLote() {
		return lote;
	}
	public void setLote(Short lote) {
		this.lote = lote;
	}
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public Short getSubLote() {
		return subLote;
	}
	public void setSubLote(Short subLote) {
		this.subLote = subLote;
	}
}