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
package gcom.gui.operacional.abastecimento;

import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0414] - Informar Programa��o de Abastecimento e Manuten��o
 * 
 * @author Rafael Pinto
 * @date 14/11/2006
 */
public class InformarProgramacaoAbastecimentoManutencaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String mesAnoReferencia;
	private String municipio;
	private String nomeMunicipio;
	private String bairro;
	private String nomeBairro;
	
	private String areaBairro;
	private String nomeAreaBairro;
	
	private String sistemaAbastecimento;
	private String nomeSistemaAbastecimento;
	
	private String setorAbastecimento;
	private String nomeSetorAbastecimento;
	
	private String zonaAbastecimento;
	private String nomeZonaAbastecimento;
	
	private String distritoOperacional;
	private String nomeDistritoOperacional;
	
	private String tipoProgramacao;
	
	//Usado no Popup de Atualizacao,Inserir
	private String dataInicio;
	private String dataFim;
	private String horaInicio;
	private String horaFim;
	
	//Usado no Popup de Atualizacao
	private int indice;
	
	//Usado no Popup de Atualizacao de Manute��o de Programa��o
	private String descricaoManutencaoProgramacao;
	private String situacaoManutencaoProgramacao;
	
	//Usado no Popup de Copiar Programa��o de Abastecimetno
	private String municipioCopiar;
	private String nomeMunicipioCopiar;
	private String bairroCopiar;
	private String nomeBairroCopiar;
	private String areaBairroCopiar;
	private String nomeAreaBairroCopiar;
	
    // Programacao de Abastecimento
    private Collection<AbastecimentoProgramacao> abastecimentoProgramacao 
    	= new ArrayList<AbastecimentoProgramacao>();

	// Programacao de Abastecimento Removidas    
    private Collection<AbastecimentoProgramacao> abastecimentoProgramacaoRemovidas = 
    	new ArrayList<AbastecimentoProgramacao>();

    // Manutencao de Abastecimento
    private Collection<ManutencaoProgramacao> manutencaoProgramacao 
    	= new ArrayList<ManutencaoProgramacao>();

	// Manutencao de Abastecimento Removidas    
    private Collection<ManutencaoProgramacao> manutencaoProgramacaoRemovidas = 
    	new ArrayList<ManutencaoProgramacao>();

    /**
	 * @return Retorna o campo areaBairro.
	 */
	public String getAreaBairro() {
		return areaBairro;
	}

	/**
	 * @param areaBairro
	 *            O areaBairro a ser setado.
	 */
	public void setAreaBairro(String areaBairro) {
		this.areaBairro = areaBairro;
	}

	/**
	 * @return Retorna o campo bairro.
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro
	 *            O bairro a ser setado.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return Retorna o campo mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia
	 *            O mesAnoReferencia a ser setado.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return Retorna o campo municipio.
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            O municipio a ser setado.
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return Retorna o campo nomeBairro.
	 */
	public String getNomeBairro() {
		return nomeBairro;
	}

	/**
	 * @param nomeBairro
	 *            O nomeBairro a ser setado.
	 */
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	/**
	 * @return Retorna o campo nomeMunicipio.
	 */
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	/**
	 * @param nomeMunicipio
	 *            O nomeMunicipio a ser setado.
	 */
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public Collection<AbastecimentoProgramacao> getAbastecimentoProgramacao() {
		return abastecimentoProgramacao;
	}

	public void setAbastecimentoProgramacao(
			Collection<AbastecimentoProgramacao> abastecimentoProgramacao) {
		this.abastecimentoProgramacao = abastecimentoProgramacao;
	}

	public Collection<AbastecimentoProgramacao> getAbastecimentoProgramacaoRemovidas() {
		return abastecimentoProgramacaoRemovidas;
	}

	public void setAbastecimentoProgramacaoRemovidas(
			Collection<AbastecimentoProgramacao> abastecimentoProgramacaoRemovidas) {
		this.abastecimentoProgramacaoRemovidas = abastecimentoProgramacaoRemovidas;
	}

	public String getDistritoOperacional() {
		return distritoOperacional;
	}

	public void setDistritoOperacional(String distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}

	public Collection<ManutencaoProgramacao> getManutencaoProgramacao() {
		return manutencaoProgramacao;
	}

	public void setManutencaoProgramacao(
			Collection<ManutencaoProgramacao> manutencaoProgramacao) {
		this.manutencaoProgramacao = manutencaoProgramacao;
	}

	public Collection<ManutencaoProgramacao> getManutencaoProgramacaoRemovidas() {
		return manutencaoProgramacaoRemovidas;
	}

	public void setManutencaoProgramacaoRemovidas(
			Collection<ManutencaoProgramacao> manutencaoProgramacaoRemovidas) {
		this.manutencaoProgramacaoRemovidas = manutencaoProgramacaoRemovidas;
	}

	public String getNomeAreaBairro() {
		return nomeAreaBairro;
	}

	public void setNomeAreaBairro(String nomeAreaBairro) {
		this.nomeAreaBairro = nomeAreaBairro;
	}

	public String getNomeDistritoOperacional() {
		return nomeDistritoOperacional;
	}

	public void setNomeDistritoOperacional(String nomeDistritoOperacional) {
		this.nomeDistritoOperacional = nomeDistritoOperacional;
	}

	public String getNomeSetorAbastecimento() {
		return nomeSetorAbastecimento;
	}

	public void setNomeSetorAbastecimento(String nomeSetorAbastecimento) {
		this.nomeSetorAbastecimento = nomeSetorAbastecimento;
	}

	public String getNomeSistemaAbastecimento() {
		return nomeSistemaAbastecimento;
	}

	public void setNomeSistemaAbastecimento(String nomeSistemaAbastecimento) {
		this.nomeSistemaAbastecimento = nomeSistemaAbastecimento;
	}

	public String getNomeZonaAbastecimento() {
		return nomeZonaAbastecimento;
	}

	public void setNomeZonaAbastecimento(String nomeZonaAbastecimento) {
		this.nomeZonaAbastecimento = nomeZonaAbastecimento;
	}

	public String getSetorAbastecimento() {
		return setorAbastecimento;
	}

	public void setSetorAbastecimento(String setorAbastecimento) {
		this.setorAbastecimento = setorAbastecimento;
	}

	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}

	public String getZonaAbastecimento() {
		return zonaAbastecimento;
	}

	public void setZonaAbastecimento(String zonaAbastecimento) {
		this.zonaAbastecimento = zonaAbastecimento;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDescricaoManutencaoProgramacao() {
		return descricaoManutencaoProgramacao;
	}

	public void setDescricaoManutencaoProgramacao(
			String descricaoManutencaoProgramacao) {
		this.descricaoManutencaoProgramacao = descricaoManutencaoProgramacao;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getSituacaoManutencaoProgramacao() {
		return situacaoManutencaoProgramacao;
	}

	public void setSituacaoManutencaoProgramacao(
			String situacaoManutencaoProgramacao) {
		this.situacaoManutencaoProgramacao = situacaoManutencaoProgramacao;
	}

	public String getTipoProgramacao() {
		return tipoProgramacao;
	}

	public void setTipoProgramacao(String tipoProgramacao) {
		this.tipoProgramacao = tipoProgramacao;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getAreaBairroCopiar() {
		return areaBairroCopiar;
	}

	public void setAreaBairroCopiar(String areaBairroCopiar) {
		this.areaBairroCopiar = areaBairroCopiar;
	}

	public String getBairroCopiar() {
		return bairroCopiar;
	}

	public void setBairroCopiar(String bairroCopiar) {
		this.bairroCopiar = bairroCopiar;
	}

	public String getMunicipioCopiar() {
		return municipioCopiar;
	}

	public void setMunicipioCopiar(String municipioCopiar) {
		this.municipioCopiar = municipioCopiar;
	}

	public String getNomeBairroCopiar() {
		return nomeBairroCopiar;
	}

	public void setNomeBairroCopiar(String nomeBairroCopiar) {
		this.nomeBairroCopiar = nomeBairroCopiar;
	}

	public String getNomeMunicipioCopiar() {
		return nomeMunicipioCopiar;
	}

	public void setNomeMunicipioCopiar(String nomeMunicipioCopiar) {
		this.nomeMunicipioCopiar = nomeMunicipioCopiar;
	}

	public String getNomeAreaBairroCopiar() {
		return nomeAreaBairroCopiar;
	}

	public void setNomeAreaBairroCopiar(String nomeAreaBairroCopiar) {
		this.nomeAreaBairroCopiar = nomeAreaBairroCopiar;
	}

}
