
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
package gcom.gui.cadastro.geografico;



import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0001]	INSERIR MUNICIPIO
 * 
 * @author K�ssia Albuquerque
 * @date 13/12/2006
 */

public class InserirMunicipioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoMunicipio;
	
	private String nomeMunicipio;

	private String codigoDdd;
	
	private String unidadeFederacao;
	
	private String microregiao;
	
	private String regiaoDesenv;

	private String cepInicial;
	
	private String cepFinal;
	
	private String dataInicioConcessao;
	
	private String dataFimConcessao;
	
	private String codigoIbge;
	
	private String indicadorRelacaoQuadraBairro;
	
	private String contratoAdesao;
	

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getCepFinal() {
		return cepFinal;
	}

	public void setCepFinal(String cepFinal) {
		this.cepFinal = cepFinal;
	}

	public String getCepInicial() {
		return cepInicial;
	}

	public void setCepInicial(String cepInicial) {
		this.cepInicial = cepInicial;
	}

	public String getCodigoDdd() {
		return codigoDdd;
	}

	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getDataFimConcessao() {
		return dataFimConcessao;
	}

	public void setDataFimConcessao(String dataFimConcessao) {
		this.dataFimConcessao = dataFimConcessao;
	}

	public String getDataInicioConcessao() {
		return dataInicioConcessao;
	}

	public void setDataInicioConcessao(String dataInicioConcessao) {
		this.dataInicioConcessao = dataInicioConcessao;
	}

	public String getMicroregiao() {
		return microregiao;
	}

	public void setMicroregiao(String microregiao) {
		this.microregiao = microregiao;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getRegiaoDesenv() {
		return regiaoDesenv;
	}

	public void setRegiaoDesenv(String regiaoDesenv) {
		this.regiaoDesenv = regiaoDesenv;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public String getIndicadorRelacaoQuadraBairro() {
		return indicadorRelacaoQuadraBairro;
	}

	public void setIndicadorRelacaoQuadraBairro(String indicadorRelacaoQuadraBairro) {
		this.indicadorRelacaoQuadraBairro = indicadorRelacaoQuadraBairro;
	}
	
	public String getContratoAdesao() {
		return contratoAdesao;
	}

	public void setContratoAdesao(String contratoAdesao) {
		this.contratoAdesao = contratoAdesao;
	}
	
	
	public Municipio setFormValues(Municipio municipio) {
		
		
		// Metodo usado para setar todos os valores do Form nn base de dados

		municipio.setId(new Integer(getCodigoMunicipio()));
		municipio.setNome(getNomeMunicipio());		
		municipio.setDdd(new Short(getCodigoDdd()));
		
		if(getUnidadeFederacao() != null && !getUnidadeFederacao().equals("")){
			
			  UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
			  unidadeFederacao.setId(Integer.parseInt(getUnidadeFederacao()));
			  municipio.setUnidadeFederacao(unidadeFederacao);
			}
		
		if(getMicroregiao() != null && !getMicroregiao().equals("")){
			
			  Microrregiao microrregiao = new Microrregiao();
			  microrregiao.setId(Integer.parseInt(getMicroregiao()));
			  municipio.setMicrorregiao(microrregiao);
			}
		
		if(getRegiaoDesenv() != null && !getRegiaoDesenv().equals("")){
			
			RegiaoDesenvolvimento regiaoDesenv = new RegiaoDesenvolvimento();
			regiaoDesenv.setId(Integer.parseInt(getRegiaoDesenv()));
			municipio.setRegiaoDesenvolvimento(regiaoDesenv);
			}
		
		if(getCodigoIbge() != null && !getCodigoIbge().equals("")){
			
			municipio.setCodigoIbge(getCodigoIbge());
			}
		
		municipio.setCepInicio(new Integer (getCepInicial()));
		municipio.setCepFim(new Integer (getCepFinal()));
		municipio.setDataConcessaoInicio(Util.converteStringParaDate(getDataInicioConcessao()));
		municipio.setDataConcessaoFim(Util.converteStringParaDate(getDataFimConcessao()));
		municipio.setUltimaAlteracao(new Date());
		municipio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		if(getIndicadorRelacaoQuadraBairro() != null && getIndicadorRelacaoQuadraBairro().equals("1")){
			
			municipio.setIndicadorRelacaoQuadraBairro(ConstantesSistema.INDICADOR_USO_ATIVO);
		}else{
			municipio.setIndicadorRelacaoQuadraBairro(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}
		
		if(getContratoAdesao().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			municipio.setContratoAdesao(null);			
		}else{
			municipio.setContratoAdesao(Short.parseShort(getContratoAdesao()));
		}
		
		return municipio;
	}

}
