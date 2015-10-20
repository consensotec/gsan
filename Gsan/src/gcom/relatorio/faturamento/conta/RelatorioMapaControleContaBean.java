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
package gcom.relatorio.faturamento.conta;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */
public class RelatorioMapaControleContaBean implements RelatorioBean {
	
	private String idEmpresa;
	private String nomeEmpresa;
	private String idLocalidade;
	private String codigoSetor; 
	private String descTipoConta;
	private String seqInicial;
	private String seqFinal;
	private String qtdeContas;
	private String valor;
	private BigDecimal valorTotal;
	private String esferaPoder;
	private String qtdContasFirma;
	private String qtdContasCorreio;
	private String qtdContasPublico;
	private String qtdContasMacro;
	private String qtdTotalContas;
	private String qtdTotalGeral;
	private String qtdTotalGeralFirma;
	private String qtdTotalGeralCorreios;
	private String qtdTotalGeralPublico;

	
	public String getQtdTotalContas() {
		return qtdTotalContas;
	}

	public void setQtdTotalContas(String qtdTotalContas) {
		this.qtdTotalContas = qtdTotalContas;
	}

	public RelatorioMapaControleContaBean(
			 String idEmpresa,
			 String nomeEmpresa,
			 String idLocalidade,
			 String codigoSetor,
			 String descTipoConta,
			 String seqInicial,
			 String seqFinal,
			 String qtdeContas,
			 String valor,
			 BigDecimal valorTotal) {
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.idLocalidade = idLocalidade;
		this.codigoSetor = codigoSetor;
		this.descTipoConta = descTipoConta;
		this.seqInicial = seqInicial;
		this.seqFinal = seqFinal;
		this.qtdeContas = qtdeContas;
		this.valor = valor;
		this.valorTotal = valorTotal;
		
	}
	
	public RelatorioMapaControleContaBean(
			 String idEmpresa,
			 String nomeEmpresa,
			 String idLocalidade,
			 String seqInicial,
			 String seqFinal,
			 String esferaPoder,
			 String qtdContasFirma,
			 String qtdContasCorreio,
			 String qtdContasPublico,
			 String qtdContasMacro,
			 String qtdTotalContas,
			 String qtdTotalGeralFirma,
			 String qtdTotalGeralCorreios,
			 String qtdTotalGeralPublico,			
			 String qtdTotalGeral) {
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.idLocalidade = idLocalidade;
		this.seqInicial = seqInicial;
		this.seqFinal = seqFinal;
		this.esferaPoder = esferaPoder;
		this.qtdContasFirma = qtdContasFirma; 
		this.qtdContasCorreio = qtdContasCorreio;
		this.qtdContasPublico = qtdContasPublico;
		this.qtdContasMacro = qtdContasMacro;
		this.qtdTotalContas = qtdTotalContas;
		this.qtdTotalGeralFirma = qtdTotalGeralFirma;
		this.qtdTotalGeralPublico = qtdTotalGeralPublico;	
		this.qtdTotalGeralCorreios = qtdTotalGeralCorreios;
		this.qtdTotalGeral = qtdTotalGeral;
	}

	
	
	public String getQtdContasMacro() {
		return qtdContasMacro;
	}

	public void setQtdContasMacro(String qtdContasMacro) {
		this.qtdContasMacro = qtdContasMacro;
	}	
	

	public String getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public String getDescTipoConta() {
		return descTipoConta;
	}

	public void setDescTipoConta(String descTipoConta) {
		this.descTipoConta = descTipoConta;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getQtdeContas() {
		return qtdeContas;
	}

	public void setQtdeContas(String qtdeContas) {
		this.qtdeContas = qtdeContas;
	}

	public String getSeqFinal() {
		return seqFinal;
	}

	public void setSeqFinal(String seqFinal) {
		this.seqFinal = seqFinal;
	}

	public String getSeqInicial() {
		return seqInicial;
	}

	public void setSeqInicial(String seqInicial) {
		this.seqInicial = seqInicial;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getQtdContasCorreio() {
		return qtdContasCorreio;
	}

	public void setQtdContasCorreio(String qtdContasCorreio) {
		this.qtdContasCorreio = qtdContasCorreio;
	}

	public String getQtdContasFirma() {
		return qtdContasFirma;
	}

	public void setQtdContasFirma(String qtdContasFirma) {
		this.qtdContasFirma = qtdContasFirma;
	}

	public String getQtdContasPublico() {
		return qtdContasPublico;
	}

	public void setQtdContasPublico(String qtdContasPublico) {
		this.qtdContasPublico = qtdContasPublico;
	}

	public String getQtdTotalGeral() {
		return qtdTotalGeral;
	}

	public void setQtdTotalGeral(String qtdTotalGeral) {
		this.qtdTotalGeral = qtdTotalGeral;
	}

	public String getQtdTotalGeralCorreios() {
		return qtdTotalGeralCorreios;
	}

	public void setQtdTotalGeralCorreios(String qtdTotalGeralCorreios) {
		this.qtdTotalGeralCorreios = qtdTotalGeralCorreios;
	}

	public String getQtdTotalGeralFirma() {
		return qtdTotalGeralFirma;
	}

	public void setQtdTotalGeralFirma(String qtdTotalGeralFirma) {
		this.qtdTotalGeralFirma = qtdTotalGeralFirma;
	}

	public String getQtdTotalGeralPublico() {
		return qtdTotalGeralPublico;
	}

	public void setQtdTotalGeralPublico(String qtdTotalGeralPublico) {
		this.qtdTotalGeralPublico = qtdTotalGeralPublico;
	}

	

}
