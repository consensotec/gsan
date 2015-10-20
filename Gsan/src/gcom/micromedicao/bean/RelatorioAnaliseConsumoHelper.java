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
package gcom.micromedicao.bean;


public class RelatorioAnaliseConsumoHelper {

	private String idLocalidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String lote;
	private String subLote;
	private String matriculaImovel;
	private String rota;
	private String seqRota;
	private String endereco;
	private String qtdEconomias;
	private String categoria;
	private String usuario;
	private String leituraAnterior;
	private String leituraAtual;
	private String hidrometro;
	private String anoMesFaturamento;
	private String anormalidadeConsumo;
	private String anormalidadeLeituraInformada;
	private String anormalidadeLeituraFaturada;
	private String perfilImovel;
	
	//******************************************************************************
	// autor: Ivan S�rgio
	// data: 24/07/2008
	// solicitante: Leonardo Vieira
	// Caso a Empresa selecionada NAO seja COMPESA nao informar a Leitura Atual;
	// Exibir a descricao da Leitura Anormalidade Informada;
	//******************************************************************************
	private String descricaoLeituraAnormalidadeInformada;
	private String indicadorExibirLeituraAtual;
	
	public RelatorioAnaliseConsumoHelper(
			String idLocalidade,
			String codigoSetorComercial,
			String numeroQuadra,
			String lote,
			String subLote,
			String matriculaImovel,
			String rota,
			String seqRota,
			String endereco,
			String qtdEconomias,
			String categoria,
			String usuario){
		
			this.idLocalidade = idLocalidade;
			this.codigoSetorComercial = codigoSetorComercial;
			this.numeroQuadra = numeroQuadra;
			this.lote = lote;
			this.subLote = subLote;
			this.matriculaImovel = matriculaImovel;
			this.rota = rota;
			this.seqRota = seqRota;
			this.endereco = endereco;
			this.qtdEconomias = qtdEconomias;
			this.categoria = categoria;
			this.usuario = usuario;
	}
	
	public RelatorioAnaliseConsumoHelper(
			String idLocalidade,
			String codigoSetorComercial,
			String numeroQuadra,
			String lote,
			String subLote,
			String matriculaImovel,
			String rota,
			String seqRota,
			String endereco,
			String qtdEconomias,
			String categoria,
			String usuario,
			String leituraAnterior,
			String leituraAtual,
			String hidrometro){
		
			this.idLocalidade = idLocalidade;
			this.codigoSetorComercial = codigoSetorComercial;
			this.numeroQuadra = numeroQuadra;
			this.lote = lote;
			this.subLote = subLote;
			this.matriculaImovel = matriculaImovel;
			this.rota = rota;
			this.seqRota = seqRota;
			this.endereco = endereco;
			this.qtdEconomias = qtdEconomias;
			this.categoria = categoria;
			this.usuario = usuario;
			this.leituraAnterior = leituraAnterior;
			this.leituraAtual = leituraAtual;
			this.hidrometro = hidrometro;
	}
	
	public RelatorioAnaliseConsumoHelper(){}

	
	
	public String getAnoMesFaturamento() {
		return anoMesFaturamento;
	}

	public void setAnoMesFaturamento(String anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}

	public String getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	
	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSeqRota() {
		return seqRota;
	}

	public void setSeqRota(String seqRota) {
		this.seqRota = seqRota;
	}

	public String getSubLote() {
		return subLote;
	}

	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}
	
	
	public String getInscricaoImovelFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = this.getIdLocalidade();
		setorComercial = this.getCodigoSetorComercial();
		quadra = this.getNumeroQuadra();
		lote = this.getLote();
		subLote = this.getSubLote();

		if (this.getIdLocalidade().length() < 3 && this.getIdLocalidade().length() > 1) {
			localidade = zeroUm + this.getIdLocalidade();
		} else if (this.getIdLocalidade().length() < 3) {
			localidade = zeroDois + this.getIdLocalidade();
		}

		if (this.getCodigoSetorComercial().length() < 3 && this.getCodigoSetorComercial().length() > 1) {
			setorComercial = zeroUm + this.getCodigoSetorComercial();
		} else if (this.getCodigoSetorComercial().length() < 3) {
			setorComercial = zeroDois + this.getCodigoSetorComercial();
		}

		if (this.getNumeroQuadra().length() < 3 && this.getNumeroQuadra().length() > 1) {
			quadra = zeroUm + this.getNumeroQuadra();
		} else if (this.getNumeroQuadra().length() < 3) {
			quadra = zeroDois + this.getNumeroQuadra();
		}

		if (this.getLote().length() < 4 && this.getLote().length() > 2) {
			lote = zeroUm + this.getLote();
		} else if (this.getLote().length() < 3 && this.getLote().length() > 1) {
			lote = zeroDois + this.getLote();
		} else if (this.getLote().length() < 2) {
			lote = zeroTres + this.getLote();
		}

		if (this.getSubLote().length() < 3 && this.getSubLote().length() > 1) {
			subLote = zeroUm + this.getSubLote();
		} else if (this.getSubLote().length() < 3) {
			subLote = zeroDois + this.getSubLote();
		}

		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(String qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public String getDescricaoLeituraAnormalidadeInformada() {
		return descricaoLeituraAnormalidadeInformada;
	}

	public void setDescricaoLeituraAnormalidadeInformada(
			String descricaoLeituraAnormalidadeInformada) {
		this.descricaoLeituraAnormalidadeInformada = descricaoLeituraAnormalidadeInformada;
	}

	public String getIndicadorExibirLeituraAtual() {
		return indicadorExibirLeituraAtual;
	}

	public void setIndicadorExibirLeituraAtual(String indicadorExibirLeituraAtual) {
		this.indicadorExibirLeituraAtual = indicadorExibirLeituraAtual;
	}

	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}

	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}

	public String getAnormalidadeLeituraInformada() {
		return anormalidadeLeituraInformada;
	}

	public void setAnormalidadeLeituraInformada(String anormalidadeLeituraInformada) {
		this.anormalidadeLeituraInformada = anormalidadeLeituraInformada;
	}

	public String getAnormalidadeLeituraFaturada() {
		return anormalidadeLeituraFaturada;
	}

	public void setAnormalidadeLeituraFaturada(String anormalidadeLeituraFaturada) {
		this.anormalidadeLeituraFaturada = anormalidadeLeituraFaturada;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
}
