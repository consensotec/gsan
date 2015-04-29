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
package gsan.gui.micromedicao;

import gsan.micromedicao.ArquivoTextoRoteiroEmpresa;
import gsan.util.Util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Thiago Nascimento
 * 
 */
public class DadosMovimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long imei;

	private Integer codigoAnormalidade;

	private Integer localidade;

	private Integer setorComercial;

	private Integer numeroQuadra;

	private Integer numeroLote;

	private Integer numeroSubLote;

	private Integer tipoMedicao;

	private Integer grupoFaturamento;

	private Integer matriculaImovel;

	private Integer perfilImovel;

	private String nomeCliente;

	private String endereco;

	private String marcaHidrometro;

	private String numeroHidrometro;

	private String capacidadeHidrometro;

	private String localInstalacao;

	private Date dataInstalacao;

	private String protecaoHidrometro;

	private Integer situacaoLigacaoAgua;

	private Integer situacaoLigacaoEsgoto;

	private String decricaoAbreviadaImovel;

	private Integer quantidadeEconomias;

	private Integer leituraAnterior;

	private Integer faixaLeituraEsperadaInferior;

	private Integer faixaLeituraEsperadaSuperior;

	private Integer leituraHidrometro;

	private String stringDataLeitura;

	private Date dataLeituraCampo;

	private String horaLeituraCampo;

	private Byte indicadorConfirmacaoLeitura;

	private Integer matriculaOperador;

	private String inscricao;

	private Integer codigoRota;

	private Integer numeroSequencialRota;

	private String msgImovelSuprimidoOuHidrometroRetirado;

	private boolean naoPermitirAlterar;

	private ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa;

	private Date dataLeituraCronograma;
	
	private Integer anoMesReferencia;

	public DadosMovimentacao() {
	}

	public DadosMovimentacao(Integer imovel, Integer leitura,
			Integer anormalidade, Date data, Long imei, Byte indicador,
			Integer tipoMedicao) {
		this.codigoAnormalidade = anormalidade;
		this.dataLeituraCampo = data;
		this.leituraHidrometro = leitura;
		this.matriculaImovel = imovel;
		this.imei = imei;
		this.indicadorConfirmacaoLeitura = indicador;
		this.tipoMedicao = tipoMedicao;
	}

	/**
	 * @return Returns the capacidadeHidrometro.
	 */
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	/**
	 * @param capacidadeHidrometro
	 *            The capacidadeHidrometro to set.
	 */
	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	/**
	 * @return Returns the dataInstalacao.
	 */
	public Date getDataInstalacao() {
		return dataInstalacao;
	}

	/**
	 * @param dataInstalacao
	 *            The dataInstalacao to set.
	 */
	public void setDataInstalacao(Date dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	/**
	 * @return Returns the decricaoAbreviadaImovel.
	 */
	public String getDecricaoAbreviadaImovel() {
		return decricaoAbreviadaImovel;
	}

	/**
	 * @param decricaoAbreviadaImovel
	 *            The decricaoAbreviadaImovel to set.
	 */
	public void setDecricaoAbreviadaImovel(String decricaoAbreviadaImovel) {
		this.decricaoAbreviadaImovel = decricaoAbreviadaImovel;
	}

	/**
	 * @return Returns the endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            The endereco to set.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Returns the faixaLeituraEsperadaInferior.
	 */
	public Integer getFaixaLeituraEsperadaInferior() {
		return faixaLeituraEsperadaInferior;
	}

	/**
	 * @param faixaLeituraEsperadaInferior
	 *            The faixaLeituraEsperadaInferior to set.
	 */
	public void setFaixaLeituraEsperadaInferior(
			Integer faixaLeituraEsperadaInferior) {
		this.faixaLeituraEsperadaInferior = faixaLeituraEsperadaInferior;
	}

	/**
	 * @return Returns the faixaLeituraEsperadaSuperior.
	 */
	public Integer getFaixaLeituraEsperadaSuperior() {
		return faixaLeituraEsperadaSuperior;
	}

	/**
	 * @param faixaLeituraEsperadaSuperior
	 *            The faixaLeituraEsperadaSuperior to set.
	 */
	public void setFaixaLeituraEsperadaSuperior(
			Integer faixaLeituraEsperadaSuperior) {
		this.faixaLeituraEsperadaSuperior = faixaLeituraEsperadaSuperior;
	}

	/**
	 * @return Returns the grupoFaturamento.
	 */
	public Integer getGrupoFaturamento() {
		return grupoFaturamento;
	}

	/**
	 * @param grupoFaturamento
	 *            The grupoFaturamento to set.
	 */
	public void setGrupoFaturamento(Integer grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	/**
	 * @return Returns the leituraAnterior.
	 */
	public Integer getLeituraAnterior() {
		return leituraAnterior;
	}

	/**
	 * @param leituraAnterior
	 *            The leituraAnterior to set.
	 */
	public void setLeituraAnterior(Integer leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	/**
	 * @return Returns the leituraHidrometro.
	 */
	public Integer getLeituraHidrometro() {
		return leituraHidrometro;
	}

	/**
	 * @param leituraHidrometro
	 *            The leituraHidrometro to set.
	 */
	public void setLeituraHidrometro(Integer leituraHidrometro) {
		this.leituraHidrometro = leituraHidrometro;
	}

	/**
	 * @return Returns the localidade.
	 */
	public Integer getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade
	 *            The localidade to set.
	 */
	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Returns the localInstalacao.
	 */
	public String getLocalInstalacao() {
		return localInstalacao;
	}

	/**
	 * @param localInstalacao
	 *            The localInstalacao to set.
	 */
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}

	/**
	 * @return Returns the marcaHidrometro.
	 */
	public String getMarcaHidrometro() {
		return marcaHidrometro;
	}

	/**
	 * @param marcaHidrometro
	 *            The marcaHidrometro to set.
	 */
	public void setMarcaHidrometro(String marcaHidrometro) {
		this.marcaHidrometro = marcaHidrometro;
	}

	/**
	 * @return Returns the matriculaOperador.
	 */
	public Integer getMatriculaOperador() {
		return matriculaOperador;
	}

	/**
	 * @param matriculaOperador
	 *            The matriculaOperador to set.
	 */
	public void setMatriculaOperador(Integer matriculaOperador) {
		this.matriculaOperador = matriculaOperador;
	}

	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente
	 *            The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Returns the numeroHidrometro.
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	/**
	 * @param numeroHidrometro
	 *            The numeroHidrometro to set.
	 */
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	/**
	 * @return Returns the numeroLote.
	 */
	public Integer getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 *            The numeroLote to set.
	 */
	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return Returns the numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra
	 *            The numeroQuadra to set.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Returns the numeroSubLote.
	 */
	public Integer getNumeroSubLote() {
		return numeroSubLote;
	}

	/**
	 * @param numeroSubLote
	 *            The numeroSubLote to set.
	 */
	public void setNumeroSubLote(Integer numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	/**
	 * @return Returns the perfilImovel.
	 */
	public Integer getPerfilImovel() {
		return perfilImovel;
	}

	/**
	 * @param perfilImovel
	 *            The perfilImovel to set.
	 */
	public void setPerfilImovel(Integer perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	/**
	 * @return Returns the protecaoHidrometro.
	 */
	public String getProtecaoHidrometro() {
		return protecaoHidrometro;
	}

	/**
	 * @param protecaoHidrometro
	 *            The protecaoHidrometro to set.
	 */
	public void setProtecaoHidrometro(String protecaoHidrometro) {
		this.protecaoHidrometro = protecaoHidrometro;
	}

	/**
	 * @return Returns the quantidadeEconomias.
	 */
	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	/**
	 * @param quantidadeEconomias
	 *            The quantidadeEconomias to set.
	 */
	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	/**
	 * @return Returns the setorComercial.
	 */
	public Integer getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial
	 *            The setorComercial to set.
	 */
	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Returns the situacaoLigacaoAgua.
	 */
	public Integer getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua
	 *            The situacaoLigacaoAgua to set.
	 */
	public void setSituacaoLigacaoAgua(Integer situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	/**
	 * @return Returns the situacaoLigacaoEsgoto.
	 */
	public Integer getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	/**
	 * @param situacaoLigacaoEsgoto
	 *            The situacaoLigacaoEsgoto to set.
	 */
	public void setSituacaoLigacaoEsgoto(Integer situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	/**
	 * @return Returns the tipoMedicao.
	 */
	public Integer getTipoMedicao() {
		return tipoMedicao;
	}

	/**
	 * @param tipoMedicao
	 *            The tipoMedicao to set.
	 */
	public void setTipoMedicao(Integer tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	/**
	 * @return Returns the stringDataLeitura.
	 */
	public String getStringDataLeitura() {
		return stringDataLeitura;
	}

	/**
	 * @param stringDataLeitura
	 *            The stringDataLeitura to set.
	 */
	public void setStringDataLeitura(String stringDataLeitura) {
		this.stringDataLeitura = stringDataLeitura;
	}

	public void gerarDadosImovel(String linha) throws NumberFormatException,
			StringIndexOutOfBoundsException {

		this.setLocalidade(new Integer(linha.substring(0, 3)));
		this.setSetorComercial(new Integer(linha.substring(3, 6)));
		this.setNumeroQuadra(new Integer(linha.substring(6, 9)));
		this.setNumeroLote(new Integer(linha.substring(9, 13).replace(" ", "")));
		this.setNumeroSubLote(new Integer(linha.substring(13, 16).replace(" ",
				"")));
		this.setTipoMedicao(new Integer(linha.substring(16, 17)));
		this.setGrupoFaturamento(new Integer(linha.substring(17, 19)));
		this.setMatriculaImovel(new Integer(linha.substring(19, 27)));
		this.setPerfilImovel(new Integer(linha.substring(27, 28)));
		this.setNomeCliente(linha.substring(28, 53));
		this.setEndereco(linha.substring(53, 103));
		this.setMarcaHidrometro(linha.substring(103, 105));
		this.setNumeroHidrometro(linha.substring(105, 115));
		this.setCapacidadeHidrometro(linha.substring(115, 117));
		this.setLocalInstalacao(linha.substring(117, 119));
		StringBuffer dataInstalacao = new StringBuffer(
				linha.substring(119, 125));
		this.setDataInstalacao(this.stringParaData(dataInstalacao.toString()));
		this.setProtecaoHidrometro(linha.substring(125, 127));
		this.setSituacaoLigacaoAgua(new Integer(linha.substring(127, 129)));
		this.setSituacaoLigacaoEsgoto(new Integer(linha.substring(129, 131)));
		this.setDecricaoAbreviadaImovel(linha.substring(131, 134));
		this.setQuantidadeEconomias(new Integer(linha.substring(134, 137)));
		this.setLeituraAnterior(new Integer(this.decrypt(linha.substring(137,
				143))));
		this.setFaixaLeituraEsperadaInferior(new Integer(this.decrypt(linha
				.substring(143, 149))));
		this.setFaixaLeituraEsperadaSuperior(new Integer(this.decrypt(linha
				.substring(149, 155))));
		this.setCodigoRota(new Integer(linha.substring(155, 161)));
		this.setNumeroSequencialRota(new Integer(linha.substring(161, 165)));
		this.setMatriculaOperador(new Integer(linha.substring(165, 173)));
		this.setLeituraHidrometro(new Integer(linha.substring(173, 179)));
		this.setCodigoAnormalidade(new Integer(linha.substring(179, 181)));
		this.setStringDataLeitura(this.decrypt(linha.substring(181, 187)));
		this.setHoraLeituraCampo(this.decrypt(linha.substring(187, 193)));
		this.setIndicadorConfirmacaoLeitura(new Byte(linha.substring(193, 194)));
		this.setDataLeituraCampo(this.stringParaDataHora(this
				.getStringDataLeitura() + this.getHoraLeituraCampo()));
	}

	/**
	 * Para decryptar o valor da Strinr passado como par�metro.
	 * 
	 * @param str
	 * @return
	 */
	public String decrypt(String str) {
		int tab[] = { 77, 110, 70, 114, 90, 100, 86, 103, 111, 75 };
		int i;
		int j;
		int value = 0;
		int len = str.length();
		String response = "";

		for (i = 0; i < len; i++) {
			value = (int) str.charAt(i);
			for (j = 0; j < 10; j++) {
				if (value == tab[j]) {
					response += String.valueOf(j).trim();
				}
			}
		}
		return response;
	}

	/**
	 * Recebe uma string do tipo "DDMMAA" ou e retorna um tipo Date desta data.
	 **/
	public Date stringParaData(String data) {
		Date dataString = null;

		try {

			Calendar calendar = Calendar.getInstance();

			char[] dataArray = data.toCharArray();

			int dia = Integer.parseInt(dataArray[4] + "" + dataArray[5]);
			int mes = Integer.parseInt(dataArray[2] + "" + dataArray[3]);
			int ano = Integer.parseInt(dataArray[0] + "" + dataArray[1]);

			calendar.set(Calendar.DAY_OF_MONTH, dia);
			calendar.set(Calendar.MONTH, mes - 1);
			calendar.set(Calendar.YEAR, ano + 2000);

			dataString = calendar.getTime();

			if (dataString.after(new Date())) {
				calendar.set(Calendar.YEAR, ano + 1900);
				dataString = calendar.getTime();
			}
		} catch (Exception e) {
			return null;
		}
		return dataString;

	}

	/**
	 * Recebe uma string do tipo "DDMMAAHHMMSS" ou e retorna um tipo Date desta
	 * data.
	 **/
	public Date stringParaDataHora(String data) {

		Date dataString = null;
		try {

			Calendar calendar = Calendar.getInstance();

			char[] dataArray = data.toCharArray();

			int dia = Integer.parseInt(dataArray[0] + "" + dataArray[1]);
			int mes = Integer.parseInt(dataArray[2] + "" + dataArray[3]);
			int ano = Integer.parseInt(dataArray[4] + "" + dataArray[5]);
			int hora = Integer.parseInt(dataArray[6] + "" + dataArray[7]);
			int minuto = Integer.parseInt(dataArray[8] + "" + dataArray[9]);
			int segundo = Integer.parseInt(dataArray[10] + "" + dataArray[11]);

			calendar.set(Calendar.DAY_OF_MONTH, dia);
			calendar.set(Calendar.MONTH, mes - 1);
			calendar.set(Calendar.YEAR, ano + 2000);
			calendar.set(Calendar.HOUR_OF_DAY, hora);
			calendar.set(Calendar.MINUTE, minuto);
			calendar.set(Calendar.SECOND, segundo);

			dataString = calendar.getTime();

			/**
			 * Alterado por Felipe Melo (27/JAN/2010 - 16:17) A linha:
			 * "if(dataString.after(new Date())){" estava fazendo com o que o
			 * retorno do ano estivesse errado.
			 */
			if (calendar.get(Calendar.YEAR) > Util.getAno(new Date())) {
				calendar.set(Calendar.YEAR, ano + 1900);
				dataString = calendar.getTime();
			}
		} catch (Exception e) {
			return null;
		}

		return dataString;

	}

	public static String encrypt(String str) {
		int tab[] = { 77, 110, 70, 114, 90, 100, 86, 103, 111, 75 };
		int i;
		int value = 0;
		int len = str.length();
		String response = "";

		for (i = 0; i < len; i++) {
			value = (int) str.charAt(i);
			response += (char) tab[(value - 48)];
		}

		return response;
	}

	/**
	 * @return Returns the anormalidade.
	 */
	public Integer getCodigoAnormalidade() {
		return codigoAnormalidade;
	}

	/**
	 * @param anormalidade
	 *            The anormalidade to set.
	 */
	public void setCodigoAnormalidade(Integer anormalidade) {
		this.codigoAnormalidade = anormalidade;
	}

	/**
	 * @return Returns the imovel.
	 */
	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param imovel
	 *            The imovel to set.
	 */
	public void setMatriculaImovel(Integer imovel) {
		this.matriculaImovel = imovel;
	}

	/**
	 * @return Returns the imei.
	 */
	public Long getImei() {
		return imei;
	}

	/**
	 * @param imei
	 *            The imei to set.
	 */
	public void setImei(Long imei) {
		this.imei = imei;
	}

	/**
	 * @return Returns the indicador.
	 */
	public Byte getIndicadorConfirmacaoLeitura() {
		return indicadorConfirmacaoLeitura;
	}

	/**
	 * @param indicador
	 *            The indicador to set.
	 */
	public void setIndicadorConfirmacaoLeitura(Byte indicador) {
		this.indicadorConfirmacaoLeitura = indicador;
	}

	/**
	 * @return Returns the codigoRota.
	 */
	public Integer getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota
	 *            The codigoRota to set.
	 */
	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Returns the numerosequencialRota.
	 */
	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	/**
	 * @param numerosequencialRota
	 *            The numerosequencialRota to set.
	 */
	public void setNumeroSequencialRota(Integer numerosequencialRota) {
		this.numeroSequencialRota = numerosequencialRota;
	}

	/**
	 * @return Returns the inscricao
	 */
	public String getInscricao() {
		if (inscricao == null) {
			char separator = '.';
			StringBuffer buffer = new StringBuffer();
			buffer.append(this.zerosEsquerda(this.getLocalidade(), 3));
			buffer.append(separator);
			buffer.append(this.zerosEsquerda(this.getSetorComercial(), 3));
			buffer.append(separator);
			buffer.append(this.zerosEsquerda(this.getNumeroQuadra(), 3));
			buffer.append(separator);
			buffer.append(this.zerosEsquerda(this.getNumeroLote(), 4));
			buffer.append(separator);
			buffer.append(this.zerosEsquerda(this.getNumeroSubLote(), 3));
			this.inscricao = buffer.toString();
		}
		return this.inscricao;
	}

	/**
	 * Metodo auxiliar que acrescenta zeros a esquerda do numero
	 * 
	 * @param valor
	 * @param tamanho
	 * @return
	 */
	private String zerosEsquerda(Integer valor, int tamanho) {
		String retorno = new String(valor.toString());
		if (tamanho > retorno.length()) {
			for (int i = 0; i < (tamanho - valor.toString().length()); i++) {
				retorno = "0" + retorno;
			}
		}
		return retorno;
	}

	/**
	 * @return Returns the msgImovelSuprimidoOuHidrometroRetirado.
	 */
	public String getMsgImovelSuprimidoOuHidrometroRetirado() {
		return msgImovelSuprimidoOuHidrometroRetirado;
	}

	/**
	 * @param msgImovelSuprimidoOuHidrometroRetirado
	 *            The msgImovelSuprimidoOuHidrometroRetirado to set.
	 */
	public void setMsgImovelSuprimidoOuHidrometroRetirado(
			String msgImovelSuprimidoOuHidrometroRetirado) {
		this.msgImovelSuprimidoOuHidrometroRetirado = msgImovelSuprimidoOuHidrometroRetirado;
	}

	public boolean isNaoPermitirAlterar() {
		return naoPermitirAlterar;
	}

	public void setNaoPermitirAlterar(boolean naoPermitirAlterar) {
		this.naoPermitirAlterar = naoPermitirAlterar;
	}

	public ArquivoTextoRoteiroEmpresa getArquivoTextoRoteiroEmpresa() {
		return arquivoTextoRoteiroEmpresa;
	}

	public void setArquivoTextoRoteiroEmpresa(
			ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa) {
		this.arquivoTextoRoteiroEmpresa = arquivoTextoRoteiroEmpresa;
	}

	/**
	 * @return Returns the horaLeitura.
	 */
	public String getHoraLeituraCampo() {
		return horaLeituraCampo;
	}

	public void setHoraLeituraCampo(String horaLeituraCampo) {
		this.horaLeituraCampo = horaLeituraCampo;
	}

	public Date getDataLeituraCampo() {
		return dataLeituraCampo;
	}

	public void setDataLeituraCampo(Date dataLeituraCampo) {
		this.dataLeituraCampo = dataLeituraCampo;
	}

	public Date getDataLeituraCronograma() {
		return dataLeituraCronograma;
	}

	public void setDataLeituraCronograma(Date dataLeituraCronograma) {
		this.dataLeituraCronograma = dataLeituraCronograma;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

}
