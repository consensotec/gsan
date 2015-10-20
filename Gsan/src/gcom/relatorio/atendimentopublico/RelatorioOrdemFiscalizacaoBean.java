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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Raphael Rossiter
 * @data 20/12/2007
 */
public class RelatorioOrdemFiscalizacaoBean implements RelatorioBean, Serializable {
	
	private static final long serialVersionUID = 1L;

	private JRBeanCollectionDataSource arrayJRFaturas;

	private ArrayList arrayRelatorioOrdemFiscalizacaoFaturasBean;

	private String inscricao;

	private String nomeCliente;

	private String matricula;

	private String endereco;

	private String ordemFiscalizacao;

	private String qtdeEconResidencial;

	private String qtdeEconComercial;

	private String qtdeEconIndustrial;

	private String qtdeEconPublica;

	private String qtdeEconTotal;

	private String dataEmissao;

	private String tipoConsumidor;

	private String ultimaAlteracao;

	private String grupo;

	private String sequencial;

	private String ligacaoAguaSituacao;

	private String consumoMedio;

	private String ligacaoEsgotoSituacao;

	private String consumoFixo;

	private String rotaSeqRota;

	private String dataCorte;

	private String dataSupressaoTotal;

	private String origem;

	private String ocorrencia;

	private String dataPosicaoDebito;

	private String valorDebito;
	
	private String numeroHidrometro;
	
	public RelatorioOrdemFiscalizacaoBean(){}

	public RelatorioOrdemFiscalizacaoBean(String inscricao, String nomeCliente,
			String matricula, String endereco, String ordemFiscalizacao,
			String qtdeEconResidencial, String qtdeEconComercial,
			String qtdeEconIndustrial, String qtdeEconPublica,
			String qtdeEconTotal, String dataEmissao, String tipoConsumidor,
			String ultimaAlteracao, String grupo, String sequencial,
			String ligacaoAguaSituacao, String consumoMedio,
			String ligacaoEsgotoSituacao, String consumoFixo,
			String rotaSeqRota, String dataCorte, 
			String dataSupressaoTotal, String origem, String ocorrencia,
			String dataPosicaoDebito, String valorDebito,
			Collection colecaoFaturas, String numeroHidrometro) {

		this.arrayRelatorioOrdemFiscalizacaoFaturasBean = new ArrayList();
		this.arrayRelatorioOrdemFiscalizacaoFaturasBean.addAll(colecaoFaturas);
		this.arrayJRFaturas = new JRBeanCollectionDataSource(
				this.arrayRelatorioOrdemFiscalizacaoFaturasBean);

		this.inscricao = inscricao;
		this.nomeCliente = nomeCliente;
		this.matricula = matricula;
		this.endereco = endereco;
		this.ordemFiscalizacao = ordemFiscalizacao;
		this.qtdeEconResidencial = qtdeEconResidencial;
		this.qtdeEconComercial = qtdeEconComercial;
		this.qtdeEconIndustrial = qtdeEconIndustrial;
		this.qtdeEconPublica = qtdeEconPublica;
		this.qtdeEconTotal = qtdeEconTotal;
		this.dataEmissao = dataEmissao;
		this.tipoConsumidor = tipoConsumidor;
		this.ultimaAlteracao = ultimaAlteracao;
		this.grupo = grupo;
		this.sequencial = sequencial;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.consumoMedio = consumoMedio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.consumoFixo = consumoFixo;
		this.rotaSeqRota = rotaSeqRota;
		this.dataCorte = dataCorte;
		this.dataSupressaoTotal = dataSupressaoTotal;
		this.origem = origem;
		this.ocorrencia = ocorrencia;
		this.dataPosicaoDebito = dataPosicaoDebito;
		this.valorDebito = valorDebito;
		this.numeroHidrometro = numeroHidrometro;
	}

	public JRBeanCollectionDataSource getArrayJRFaturas() {
		return arrayJRFaturas;
	}

	public void setArrayJRFaturas(JRBeanCollectionDataSource arrayJRFaturas) {
		this.arrayJRFaturas = arrayJRFaturas;
	}

	public ArrayList getArrayRelatorioOrdemFiscalizacaoFaturasBean() {
		return arrayRelatorioOrdemFiscalizacaoFaturasBean;
	}

	public void setArrayRelatorioOrdemFiscalizacaoFaturasBean(
			ArrayList arrayRelatorioOrdemFiscalizacaoFaturasBean) {
		this.arrayRelatorioOrdemFiscalizacaoFaturasBean = arrayRelatorioOrdemFiscalizacaoFaturasBean;
	}

	public String getConsumoFixo() {
		return consumoFixo;
	}

	public void setConsumoFixo(String consumoFixo) {
		this.consumoFixo = consumoFixo;
	}

	public String getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public String getDataCorte() {
		return dataCorte;
	}

	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataPosicaoDebito() {
		return dataPosicaoDebito;
	}

	public void setDataPosicaoDebito(String dataPosicaoDebito) {
		this.dataPosicaoDebito = dataPosicaoDebito;
	}

	public String getDataSupressaoTotal() {
		return dataSupressaoTotal;
	}

	public void setDataSupressaoTotal(String dataSupressaoTotal) {
		this.dataSupressaoTotal = dataSupressaoTotal;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getOrdemFiscalizacao() {
		return ordemFiscalizacao;
	}

	public void setOrdemFiscalizacao(String ordemFiscalizacao) {
		this.ordemFiscalizacao = ordemFiscalizacao;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getQtdeEconComercial() {
		return qtdeEconComercial;
	}

	public void setQtdeEconComercial(String qtdeEconComercial) {
		this.qtdeEconComercial = qtdeEconComercial;
	}

	public String getQtdeEconIndustrial() {
		return qtdeEconIndustrial;
	}

	public void setQtdeEconIndustrial(String qtdeEconIndustrial) {
		this.qtdeEconIndustrial = qtdeEconIndustrial;
	}

	

	public String getQtdeEconPublica() {
		return qtdeEconPublica;
	}

	public void setQtdeEconPublica(String qtdeEconPublica) {
		this.qtdeEconPublica = qtdeEconPublica;
	}

	public String getQtdeEconResidencial() {
		return qtdeEconResidencial;
	}

	public void setQtdeEconResidencial(String qtdeEconResidencial) {
		this.qtdeEconResidencial = qtdeEconResidencial;
	}

	public String getQtdeEconTotal() {
		return qtdeEconTotal;
	}

	public void setQtdeEconTotal(String qtdeEconTotal) {
		this.qtdeEconTotal = qtdeEconTotal;
	}

	public String getRotaSeqRota() {
		return rotaSeqRota;
	}

	public void setRotaSeqRota(String rotaSeqRota) {
		this.rotaSeqRota = rotaSeqRota;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getTipoConsumidor() {
		return tipoConsumidor;
	}

	public void setTipoConsumidor(String tipoConsumidor) {
		this.tipoConsumidor = tipoConsumidor;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	
	

}
