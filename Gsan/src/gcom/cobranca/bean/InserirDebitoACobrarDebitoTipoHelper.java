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
package gcom.cobranca.bean;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class InserirDebitoACobrarDebitoTipoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Imovel imovel;
	private Short numeroPrestacao; 
	private BigDecimal taxaJuros;
	private Integer parcelamentoId; 
	private Collection<Categoria> colecaoCategoria;
	private BigDecimal valorEntrada; 
	private short indicadorDividaAtiva; 
	private Usuario usuarioLogado;
	private boolean gerarGuia; 
	private boolean gerarExtrato;
	private Integer anoMesGuiaEntrada;
	private Integer maiorAnoMesConta;
	
	public InserirDebitoACobrarDebitoTipoHelper(){}

	public InserirDebitoACobrarDebitoTipoHelper(
			Imovel imovel, Short numeroPrestacao, BigDecimal taxaJuros, Integer parcelamentoId,
			Collection<Categoria> colecaoCategoria, BigDecimal valorEntrada, short indicadorDividaAtiva,
			Usuario usuarioLogado, boolean gerarGuia, Integer anoMesGuiaEntrada, Integer maiorAnoMesConta,boolean gerarExtrato) {
		super();
		this.imovel = imovel;
		this.numeroPrestacao = numeroPrestacao;
		this.taxaJuros = taxaJuros;
		this.parcelamentoId = parcelamentoId;
		this.colecaoCategoria = colecaoCategoria;
		this.valorEntrada = valorEntrada;
		this.indicadorDividaAtiva = indicadorDividaAtiva;
		this.usuarioLogado = usuarioLogado;
		this.gerarGuia = gerarGuia;
		this.anoMesGuiaEntrada = anoMesGuiaEntrada;
		this.maiorAnoMesConta = maiorAnoMesConta;
		this.gerarExtrato = gerarExtrato;
	}
	
	public boolean isGerarExtrato() {
		return gerarExtrato;
	}

	public void setGerarExtrato(boolean gerarExtrato) {
		this.gerarExtrato = gerarExtrato;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Short getNumeroPrestacao() {
		return numeroPrestacao;
	}

	public void setNumeroPrestacao(Short numeroPrestacao) {
		this.numeroPrestacao = numeroPrestacao;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public Integer getParcelamentoId() {
		return parcelamentoId;
	}

	public void setParcelamentoId(Integer parcelamentoId) {
		this.parcelamentoId = parcelamentoId;
	}

	public Collection<Categoria> getColecaoCategoria() {
		return colecaoCategoria;
	}

	public void setColecaoCategoria(Collection<Categoria> colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public short getIndicadorDividaAtiva() {
		return indicadorDividaAtiva;
	}

	public void setIndicadorDividaAtiva(short indicadorDividaAtiva) {
		this.indicadorDividaAtiva = indicadorDividaAtiva;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean isGerarGuia() {
		return gerarGuia;
	}

	public void setGerarGuia(boolean gerarGuia) {
		this.gerarGuia = gerarGuia;
	}

	public Integer getAnoMesGuiaEntrada() {
		return anoMesGuiaEntrada;
	}

	public void setAnoMesGuiaEntrada(Integer anoMesGuiaEntrada) {
		this.anoMesGuiaEntrada = anoMesGuiaEntrada;
	}

	public Integer getMaiorAnoMesConta() {
		return maiorAnoMesConta;
	}

	public void setMaiorAnoMesConta(Integer maiorAnoMesConta) {
		this.maiorAnoMesConta = maiorAnoMesConta;
	}
}
