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
package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * classe respons�vel por criar o relat�rio de imoveis por Consumo Medio
 * 
 * @author Bruno Barros
 * @created 17/12/2007
 */
public class RelatorioImoveisConsumoMedioBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	private String unidadeNegocio;
	private String gerenciaRegional;
	private String localidade;
	private String setorComercial;
	
	private String rota;
	
	private String nomeCliente;
	private String endereco;
	private String matriculaImovel;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	private String consumoMedioAgua;
	private String consumoMedioEsgoto;
	
	public RelatorioImoveisConsumoMedioBean(RelatorioImoveisConsumoMedioHelper helper) {
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		
		if (helper.getSequencialRota() != null){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getCodigoRota().toString())+"."+
				Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}else{
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getCodigoRota().toString())+"."+
			Util.adicionarZerosEsquedaNumero(3,"");
		}
		
		this.nomeCliente = helper.getNomeCliente();
		this.endereco = helper.getEndereco();
		this.matriculaImovel = helper.getMatriculaImovel();

		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();
		
		this.consumoMedioAgua = Util.agruparNumeroEmMilhares( helper.getConsumoMedioAgua() );
		this.consumoMedioEsgoto = Util.agruparNumeroEmMilhares( helper.getConsumoMedioEsgoto() );
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	
	public String getLocalidade() {
		return localidade;
	}
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getRota() {
		return rota;
	}

	public String getSetorComercial() {
		return setorComercial;
	}
	
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	
	public static long getSerialVersionUID() {
	
		return serialVersionUID;
	}

	
	public String getConsumoMedioAgua() {
	
		return consumoMedioAgua;
	}

	
	public String getConsumoMedioEsgoto() {
	
		return consumoMedioEsgoto;
	}

	
	public String getSituacaoLigacaoEsgoto() {
	
		return situacaoLigacaoEsgoto;
	}
}
