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
package gcom.gui.faturamento.bean;

import gcom.micromedicao.leitura.LeituraAnormalidade;

public class AnalisarImoveisReleituraHelper {

	public static final short RELEITURA_NAO_REALIZADA = 1;

	public static final short RELEITURA_PENDENTE = 2;

	public static final short RELEITURA_REALIZADA = 3;

	private int idQuadra;

	private int idLocalidade;

	private int codigoSetorComercial;

	private int codigoRota;

	private Integer empresa;

	private String matriculaImovel;

	private String recebeuMensagem;

	private Integer leituraAtualAgua;

	private Integer leituraAnteriorAgua;

	private Integer leituraAtualPoco;

	private Integer leituraAnteriorPoco;

	private LeituraAnormalidade leituraAnormalidadeAnteriorAgua;

	private LeituraAnormalidade leituraAnormalidadeAtualAgua;

	private LeituraAnormalidade leituraAnormalidadeAnteriorPoco;

	private LeituraAnormalidade leituraAnormalidadeAtualPoco;

	private short idSituacaoReleitura;

	public int getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(int codigoRota) {
		this.codigoRota = codigoRota;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public int getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(int idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public int getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(int idQuadra) {
		this.idQuadra = idQuadra;
	}

	public short getIdSituacaoReleitura() {
		return idSituacaoReleitura;
	}

	public void setIdSituacaoReleitura(short idSituacaoReleitura) {
		this.idSituacaoReleitura = idSituacaoReleitura;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getRecebeuMensagem() {
		return recebeuMensagem;
	}

	public void setRecebeuMensagem(String recebeuMensagem) {
		this.recebeuMensagem = recebeuMensagem;
	}

	public String getHint() {
		return "--------------------- �GUA ---------------------"
				+ "<br>"
				+ "Leitura Anterior: "
				+ (this.getLeituraAnteriorAgua() != null ? this
						.getLeituraAnteriorAgua() : "---")
				+ "<br>"
				+ "Leitura Atual : "
				+ (this.getLeituraAtualAgua() != null ? this
						.getLeituraAtualAgua() : "---")
				+ "<br>"
				+ "Anormalidade Anterior: "
				+ (this.getLeituraAnormalidadeAnteriorAgua() != null ? this
						.getLeituraAnormalidadeAnteriorAgua().getDescricao()
						: "---")
				+ "<br>"
				+ "Anormalidade Atual: "
				+ (this.getLeituraAnormalidadeAtualAgua() != null ? this
						.getLeituraAnormalidadeAtualAgua().getDescricao()
						: "---")
				+ "<br>"
				+ "--------------------- PO�O ---------------------"
				+ "<br>"
				+ "Leitura Anterior: "
				+ (this.getLeituraAnteriorPoco() != null ? this
						.getLeituraAnteriorPoco() : "---")
				+ "<br>"
				+ "Leitura Atual : "
				+ (this.getLeituraAtualPoco() != null ? this
						.getLeituraAtualPoco() : "---")
				+ "<br>"
				+ "Anormalidade Anterior: "
				+ (this.getLeituraAnormalidadeAnteriorPoco() != null ? this
						.getLeituraAnormalidadeAnteriorPoco().getDescricao()
						: "---")
				+ "<br>"
				+ "Anormalidade Atual: "
				+ (this.getLeituraAnormalidadeAtualPoco() != null ? this
						.getLeituraAnormalidadeAtualPoco().getDescricao()
						: "---") + "<br>";
	}

	public LeituraAnormalidade getLeituraAnormalidadeAnteriorAgua() {
		return leituraAnormalidadeAnteriorAgua;
	}

	public void setLeituraAnormalidadeAnteriorAgua(
			LeituraAnormalidade leituraAnormalidadeAnteriorAgua) {
		this.leituraAnormalidadeAnteriorAgua = leituraAnormalidadeAnteriorAgua;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAnteriorPoco() {
		return leituraAnormalidadeAnteriorPoco;
	}

	public void setLeituraAnormalidadeAnteriorPoco(
			LeituraAnormalidade leituraAnormalidadeAnteriorPoco) {
		this.leituraAnormalidadeAnteriorPoco = leituraAnormalidadeAnteriorPoco;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAtualAgua() {
		return leituraAnormalidadeAtualAgua;
	}

	public void setLeituraAnormalidadeAtualAgua(
			LeituraAnormalidade leituraAnormalidadeAtualAgua) {
		this.leituraAnormalidadeAtualAgua = leituraAnormalidadeAtualAgua;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAtualPoco() {
		return leituraAnormalidadeAtualPoco;
	}

	public void setLeituraAnormalidadeAtualPoco(
			LeituraAnormalidade leituraAnormalidadeAtualPoco) {
		this.leituraAnormalidadeAtualPoco = leituraAnormalidadeAtualPoco;
	}

	public Integer getLeituraAnteriorAgua() {
		return leituraAnteriorAgua;
	}

	public void setLeituraAnteriorAgua(Integer leituraAnteriorAgua) {
		this.leituraAnteriorAgua = leituraAnteriorAgua;
	}

	public Integer getLeituraAnteriorPoco() {
		return leituraAnteriorPoco;
	}

	public void setLeituraAnteriorPoco(Integer leituraAnteriorPoco) {
		this.leituraAnteriorPoco = leituraAnteriorPoco;
	}

	public Integer getLeituraAtualAgua() {
		return leituraAtualAgua;
	}

	public void setLeituraAtualAgua(Integer leituraAtualAgua) {
		this.leituraAtualAgua = leituraAtualAgua;
	}

	public Integer getLeituraAtualPoco() {
		return leituraAtualPoco;
	}

	public void setLeituraAtualPoco(Integer leituraAtualPoco) {
		this.leituraAtualPoco = leituraAtualPoco;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

}
