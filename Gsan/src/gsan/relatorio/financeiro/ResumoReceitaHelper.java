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
package gsan.relatorio.financeiro;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ResumoReceitaHelper {
	
	private Integer unidadeNegocioId;
	private Integer gerenciaRegionalId;
	private Integer localidadeId;
	private String localidadeNome;
	private Integer categoriaId;
	private String categoriaNome;
	private Integer imovelId;
	private Timestamp dataRealizacao;
	private Integer contaBancariaId;
	private Integer arrecadadorId;
	private Integer bancoId;
	private Integer contaContabil;
	
	private BigDecimal somaAgua;
	private BigDecimal somaEsgoto;
	private BigDecimal somaCategoria;
	private BigDecimal somaServico;
	private BigDecimal somaImposto;
	private BigDecimal somaPagamento;
	private BigDecimal somaCredito;
	private BigDecimal somaDividaAtiva;
	private BigDecimal somaPagamentoGuia;
	private BigDecimal somaOutrasReceitas;
	private BigDecimal somaPagamentoNaoClassificado;
	private BigDecimal somaPagamentoDebCobrar;
	private BigDecimal somaPagamentoHistoricoSemCorrespondente;
	
	private String anoMes;
	private String localidadeInicial;
	private String localidadeFinal;
	
	private BigDecimal valorTotal;
	
	private Boolean receitaIntra;
	
	public ResumoReceitaHelper(){}
	
	public ResumoReceitaHelper(ResumoReceitaHelper helper){
		this.unidadeNegocioId = helper.getUnidadeNegocioId();
		this.gerenciaRegionalId = helper.getGerenciaRegionalId();
		this.localidadeId = helper.getLocalidadeId();
		this.localidadeNome = helper.getLocalidadeNome();
		this.categoriaId = helper.getCategoriaId();
		this.categoriaNome = helper.getCategoriaNome();
		this.imovelId = helper.getImovelId();
		this.dataRealizacao = helper.getDataRealizacao();
		this.contaBancariaId = helper.getContaBancariaId();
		this.arrecadadorId = helper.getArrecadadorId();
		
		this.somaAgua = helper.getSomaAgua();
		this.somaEsgoto = helper.getSomaEsgoto();
		this.somaCategoria = helper.getSomaCategoria();
		this.somaServico = helper.getSomaServico();
		this.somaImposto = helper.getSomaImposto();
		this.somaPagamento = helper.getSomaPagamento();
		this.somaCredito  = helper.getSomaCredito();
		this.bancoId = helper.getBancoId();
		
		this.receitaIntra = helper.getReceitaIntra();
	}
	
	public BigDecimal getSomaAgua() {
		return somaAgua;
	}
	public void setSomaAgua(BigDecimal somaAgua) {
		this.somaAgua = somaAgua;
	}
	public BigDecimal getSomaCategoria() {
		return somaCategoria;
	}
	public void setSomaCategoria(BigDecimal somaCategoria) {
		this.somaCategoria = somaCategoria;
	}
	public BigDecimal getSomaEsgoto() {
		return somaEsgoto;
	}
	public void setSomaEsgoto(BigDecimal somaEsgoto) {
		this.somaEsgoto = somaEsgoto;
	}
	public BigDecimal getSomaServico() {
		return somaServico;
	}
	public void setSomaServico(BigDecimal somaServico) {
		this.somaServico = somaServico;
	}
	public Integer getUnidadeNegocioId() {
		return unidadeNegocioId;
	}
	public void setUnidadeNegocioId(Integer unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}
	public Integer getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}
	public void setGerenciaRegionalId(Integer gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}
	public Integer getLocalidadeId() {
		return localidadeId;
	}
	public void setLocalidadeId(Integer localidadeId) {
		this.localidadeId = localidadeId;
	}
	public String getLocalidadeNome() {
		return localidadeNome;
	}
	public void setLocalidadeNome(String localidadeNome) {
		this.localidadeNome = localidadeNome;
	}
	public Integer getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getCategoriaNome() {
		return categoriaNome;
	}
	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}
	public BigDecimal getSomaImposto() {
		return somaImposto;
	}
	public void setSomaImposto(BigDecimal somaImposto) {
		this.somaImposto = somaImposto;
	}
	public BigDecimal getSomaPagamento() {
		return somaPagamento;
	}
	public void setSomaPagamento(BigDecimal somaPagamento) {
		this.somaPagamento = somaPagamento;
	}
	public Integer getImovelId() {
		return imovelId;
	}
	public void setImovelId(Integer imovelId) {
		this.imovelId = imovelId;
	}
	public Timestamp getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Timestamp dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public BigDecimal getSomaCredito() {
		return somaCredito;
	}
	public void setSomaCredito(BigDecimal somaCredito) {
		this.somaCredito = somaCredito;
	}

	public Integer getContaBancariaId() {
		return contaBancariaId;
	}

	public void setContaBancariaId(Integer contaBancariaId) {
		this.contaBancariaId = contaBancariaId;
	}

	public Integer getArrecadadorId() {
		return arrecadadorId;
	}

	public void setArrecadadorId(Integer arrecadadorId) {
		this.arrecadadorId = arrecadadorId;
	}

	public Integer getBancoId() {
		return bancoId;
	}

	public void setBancoId(Integer bancoId) {
		this.bancoId = bancoId;
	}

	public Integer getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(Integer contaContabil) {
		this.contaContabil = contaContabil;
	}

	public BigDecimal getSomaDividaAtiva() {
		return somaDividaAtiva;
	}

	public void setSomaDividaAtiva(BigDecimal somaDividaAtiva) {
		this.somaDividaAtiva = somaDividaAtiva;
	}

	public BigDecimal getSomaOutrasReceitas() {
		return somaOutrasReceitas;
	}

	public void setSomaOutrasReceitas(BigDecimal somaOutrasReceitas) {
		this.somaOutrasReceitas = somaOutrasReceitas;
	}

	public BigDecimal getSomaPagamentoGuia() {
		return somaPagamentoGuia;
	}

	public void setSomaPagamentoGuia(BigDecimal somaPagamentoGuia) {
		this.somaPagamentoGuia = somaPagamentoGuia;
	}

	public BigDecimal getSomaPagamentoNaoClassificado() {
		return somaPagamentoNaoClassificado;
	}

	public void setSomaPagamentoNaoClassificado(
			BigDecimal somaPagamentoNaoClassificado) {
		this.somaPagamentoNaoClassificado = somaPagamentoNaoClassificado;
	}

	public BigDecimal getSomaPagamentoDebCobrar() {
		return somaPagamentoDebCobrar;
	}

	public void setSomaPagamentoDebCobrar(BigDecimal somaPagamentoDebCobrar) {
		this.somaPagamentoDebCobrar = somaPagamentoDebCobrar;
	}

	public BigDecimal getSomaPagamentoHistoricoSemCorrespondente() {
		return somaPagamentoHistoricoSemCorrespondente;
	}

	public void setSomaPagamentoHistoricoSemCorrespondente(
			BigDecimal somaPagamentoHistoricoSemCorrespondente) {
		this.somaPagamentoHistoricoSemCorrespondente = somaPagamentoHistoricoSemCorrespondente;
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal retornaValorTotal(){
		BigDecimal retorno = BigDecimal.ZERO;
		
		if(this.somaAgua != null){
			retorno = retorno.add(this.somaAgua);
		}
		if(this.somaCategoria != null){
			retorno = retorno.add(this.somaCategoria);
		}
		if(this.somaCredito != null){
			retorno = retorno.add(this.somaCredito);
		}
		if(this.somaDividaAtiva != null){
			retorno = retorno.add(this.somaDividaAtiva);
		}
		if(this.somaEsgoto != null){
			retorno = retorno.add(this.somaEsgoto);
		}
		if(this.somaImposto != null){
			retorno = retorno.add(this.somaImposto);
		}
		if(this.somaOutrasReceitas != null){
			retorno = retorno.add(this.somaOutrasReceitas);
		}
		if(this.somaPagamento != null){
			retorno = retorno.add(this.somaPagamento);
		}
		if(this.somaPagamentoDebCobrar != null){
			retorno = retorno.add(this.somaPagamentoDebCobrar);
		}
		if(this.somaPagamentoGuia != null){
			retorno = retorno.add(this.somaPagamentoGuia);
		}
		if(this.somaPagamentoHistoricoSemCorrespondente != null){
			retorno = retorno.add(this.somaPagamentoHistoricoSemCorrespondente);
		}
		if(this.somaServico != null){
			retorno = retorno.add(this.somaServico);
		}
		if(this.somaPagamentoNaoClassificado != null){
			retorno = retorno.add(this.somaPagamentoNaoClassificado);
		}
				
		return retorno;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Boolean getReceitaIntra() {
		return receitaIntra;
	}

	public void setReceitaIntra(Boolean receitaIntra) {
		this.receitaIntra = receitaIntra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoMes == null) ? 0 : anoMes.hashCode());
		result = prime * result
				+ ((arrecadadorId == null) ? 0 : arrecadadorId.hashCode());
		result = prime * result + ((bancoId == null) ? 0 : bancoId.hashCode());
		result = prime * result
				+ ((dataRealizacao == null) ? 0 : dataRealizacao.hashCode());
		result = prime
				* result
				+ ((gerenciaRegionalId == null) ? 0 : gerenciaRegionalId
						.hashCode());
		result = prime * result
				+ ((localidadeId == null) ? 0 : localidadeId.hashCode());
		result = prime
				* result
				+ ((unidadeNegocioId == null) ? 0 : unidadeNegocioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResumoReceitaHelper other = (ResumoReceitaHelper) obj;
		if (anoMes == null) {
			if (other.anoMes != null)
				return false;
		} else if (!anoMes.equals(other.anoMes))
			return false;
		if (arrecadadorId == null) {
			if (other.arrecadadorId != null)
				return false;
		} else if (!arrecadadorId.equals(other.arrecadadorId))
			return false;
		if (bancoId == null) {
			if (other.bancoId != null)
				return false;
		} else if (!bancoId.equals(other.bancoId))
			return false;
		if (dataRealizacao == null) {
			if (other.dataRealizacao != null)
				return false;
		} else if (!dataRealizacao.equals(other.dataRealizacao))
			return false;
		if (gerenciaRegionalId == null) {
			if (other.gerenciaRegionalId != null)
				return false;
		} else if (!gerenciaRegionalId.equals(other.gerenciaRegionalId))
			return false;
		if (localidadeId == null) {
			if (other.localidadeId != null)
				return false;
		} else if (!localidadeId.equals(other.localidadeId))
			return false;
		if (unidadeNegocioId == null) {
			if (other.unidadeNegocioId != null)
				return false;
		} else if (!unidadeNegocioId.equals(other.unidadeNegocioId))
			return false;
		
		if (contaContabil == null) {
			if (other.contaContabil != null)
				return false;
		} else if (!contaContabil.equals(other.contaContabil))
			return false;
		
		if (receitaIntra == null) {
			if (other.receitaIntra != null)
				return false;
		} else if (!receitaIntra.equals(other.receitaIntra))
			return false;
		
		return true;
	}
	
}
