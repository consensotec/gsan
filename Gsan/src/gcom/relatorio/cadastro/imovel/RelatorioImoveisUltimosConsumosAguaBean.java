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
 * [UC0731] Gerar Relat�rio de Im�veis com os Ultimos Consumos de Agua
 * 
 * @author Rafael Pinto
 *
 * @date 18/12/2007
 */
public class RelatorioImoveisUltimosConsumosAguaBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegional;
	private String localidade;
	private String unidadeNegocio;
	private String setorComercial;
	
	private String inscricaoImovel;	
	private String rota;
	private String matriculaImovel;
	
	private String nomeCliente;
	private String endereco;

	private String subCategoria;
	private String economias;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	private String descricaoConsumo1;
	private String descricaoConsumo2;
	private String descricaoConsumo3;
	private String descricaoConsumo4;
	private String descricaoConsumo5;
	private String descricaoConsumo6;

	private String descricaoConsumo7;
	private String descricaoConsumo8;
	private String descricaoConsumo9;
	private String descricaoConsumo10;
	private String descricaoConsumo11;
	private String descricaoConsumo12;

	private String consumoAgua1;
	private String consumoAgua2;
	private String consumoAgua3;
	private String consumoAgua4;
	private String consumoAgua5;
	private String consumoAgua6;

	private String consumoAgua7;
	private String consumoAgua8;
	private String consumoAgua9;
	private String consumoAgua10;
	private String consumoAgua11;
	private String consumoAgua12;
	
	public RelatorioImoveisUltimosConsumosAguaBean(RelatorioImoveisUltimosConsumosAguaHelper helper) {
		
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		
		if (helper.getSequencialRota() != null){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString())+"."+
				Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}else{
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString());
		}
		
		this.nomeCliente = helper.getNomeCliente();
		this.endereco = helper.getEndereco();
		this.matriculaImovel = helper.getMatriculaImovel();
		
		//this.subCategoria = helper.getSubCategoria().toString();
		this.subCategoria = helper.getCodigoSubcategoria();
		this.economias = helper.getEconomias().toString();

		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();
		
		this.descricaoConsumo1 = helper.getDescricaoConsumo1();
		this.descricaoConsumo2 = helper.getDescricaoConsumo2();
		this.descricaoConsumo3 = helper.getDescricaoConsumo3();
		this.descricaoConsumo4 = helper.getDescricaoConsumo4();
		this.descricaoConsumo5 = helper.getDescricaoConsumo5();
		this.descricaoConsumo6 = helper.getDescricaoConsumo6();

		this.descricaoConsumo7 = helper.getDescricaoConsumo7();
		this.descricaoConsumo8 = helper.getDescricaoConsumo8();
		this.descricaoConsumo9 = helper.getDescricaoConsumo9();
		this.descricaoConsumo10 = helper.getDescricaoConsumo10();
		this.descricaoConsumo11 = helper.getDescricaoConsumo11();
		this.descricaoConsumo12 = helper.getDescricaoConsumo12();

		this.consumoAgua1 = helper.getConsumoAgua1();
		this.consumoAgua2 = helper.getConsumoAgua2();
		this.consumoAgua3 = helper.getConsumoAgua3();
		this.consumoAgua4 = helper.getConsumoAgua4();
		this.consumoAgua5 = helper.getConsumoAgua5();
		this.consumoAgua6 = helper.getConsumoAgua6();

		this.consumoAgua7 = helper.getConsumoAgua7();
		this.consumoAgua8 = helper.getConsumoAgua8();
		this.consumoAgua9 = helper.getConsumoAgua9();
		this.consumoAgua10 = helper.getConsumoAgua10();
		this.consumoAgua11 = helper.getConsumoAgua11();
		this.consumoAgua12 = helper.getConsumoAgua12();
		
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
	
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public String getEconomias() {
		return economias;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public String getConsumoAgua1() {
		return consumoAgua1;
	}

	public String getConsumoAgua2() {
		return consumoAgua2;
	}

	public String getConsumoAgua3() {
		return consumoAgua3;
	}

	public String getConsumoAgua4() {
		return consumoAgua4;
	}

	public String getConsumoAgua5() {
		return consumoAgua5;
	}

	public String getConsumoAgua6() {
		return consumoAgua6;
	}

	public String getDescricaoConsumo1() {
		return descricaoConsumo1;
	}

	public String getDescricaoConsumo2() {
		return descricaoConsumo2;
	}

	public String getDescricaoConsumo3() {
		return descricaoConsumo3;
	}

	public String getDescricaoConsumo4() {
		return descricaoConsumo4;
	}

	public String getDescricaoConsumo5() {
		return descricaoConsumo5;
	}

	public String getDescricaoConsumo6() {
		return descricaoConsumo6;
	}

	public String getConsumoAgua10() {
		return consumoAgua10;
	}

	public String getConsumoAgua11() {
		return consumoAgua11;
	}

	public String getConsumoAgua12() {
		return consumoAgua12;
	}

	public String getConsumoAgua7() {
		return consumoAgua7;
	}

	public String getConsumoAgua8() {
		return consumoAgua8;
	}

	public String getConsumoAgua9() {
		return consumoAgua9;
	}

	public String getDescricaoConsumo10() {
		return descricaoConsumo10;
	}

	public String getDescricaoConsumo11() {
		return descricaoConsumo11;
	}

	public String getDescricaoConsumo12() {
		return descricaoConsumo12;
	}

	public String getDescricaoConsumo7() {
		return descricaoConsumo7;
	}

	public String getDescricaoConsumo8() {
		return descricaoConsumo8;
	}

	public String getDescricaoConsumo9() {
		return descricaoConsumo9;
	}
	
	

}