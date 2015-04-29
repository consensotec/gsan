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
package gsan.relatorio.cadastro.imovel;

import gsan.relatorio.RelatorioBean;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * classe respons�vel por criar o relat�rio de imoveis por situa��o da liga��o de agua
 * 
 * @author Bruno Barros
 * @created 03/12/2007
 */
public class RelatorioImoveisFaturasAtrasoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	private String unidadeNegocio;
	private String gerenciaRegional;
	private String localidade;
	private String setorComercial;
	
	private String rota;
	
	private String nomeClienteUsuario;
	private String endereco;
	private String matriculaImovel;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	private String quantidadeFaturasAtraso;
	
	private String referenciaFaturasAtrasoInicial;
	private String referenciaFaturasAtrasoFinal;
	
	private String valorFaturasAtrasoSemEncargos;
	private String valorFaturasAtrasoComEncargos;
	
	private JRBeanCollectionDataSource arrayJrContas;

	private ArrayList<RelatorioImoveisFaturasAtrasoContasBean> arrayRelatorioImoveisFaturasAtrasoContasBean;
	
	private BigDecimal valorTotalFaturaAtrasoSemEncargo;
	private BigDecimal valorTotalFaturaAtrasoComEncargo;

	private String cpfOuCnpjClienteUsuario;

	private String idCliente;
	private String nomeCliente;

	public RelatorioImoveisFaturasAtrasoBean(RelatorioImoveisFaturasAtrasoHelper helper, Collection<RelatorioImoveisFaturasAtrasoContasBean> colecaoRelatorioImoveisFaturasAtrasoContasBean) {
		
		this(helper);
		
		if ( !Util.isVazioOrNulo(colecaoRelatorioImoveisFaturasAtrasoContasBean)) {

			this.arrayRelatorioImoveisFaturasAtrasoContasBean = new ArrayList<RelatorioImoveisFaturasAtrasoContasBean>();
			this.arrayRelatorioImoveisFaturasAtrasoContasBean
					.addAll(colecaoRelatorioImoveisFaturasAtrasoContasBean);
			this.arrayJrContas = new JRBeanCollectionDataSource(
					this.arrayRelatorioImoveisFaturasAtrasoContasBean);
			
		} else {
			this.arrayJrContas = null;
		}
	}
	
	public RelatorioImoveisFaturasAtrasoBean(RelatorioImoveisFaturasAtrasoHelper helper) {

		this.idCliente = helper.getIdCliente()!=null?helper.getIdCliente().toString() : null;
		this.nomeCliente = helper.getNomeCliente();
		this.nomeClienteUsuario = helper.getNomeClienteUsuario();
		this.cpfOuCnpjClienteUsuario = helper.getCpfOuCnpjClienteUsuario() ;

		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		
		if(helper.getSequencialRota() != null){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString())+"."+
				Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}else if(helper.getRota() != null){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString());
		}
		
		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();

		this.referenciaFaturasAtrasoInicial = ( helper.getReferenciaFaturasAtrasoInicial() != null ? Util.formatarMesAnoReferencia( helper.getReferenciaFaturasAtrasoInicial() ) : "" );
		this.referenciaFaturasAtrasoFinal = ( helper.getReferenciaFaturasAtrasoFinal() != null ? Util.formatarMesAnoReferencia( helper.getReferenciaFaturasAtrasoFinal() ) : "" );

		this.quantidadeFaturasAtraso = Util.agruparNumeroEmMilhares( helper.getQuantidadeFaturasAtraso()!=null ? helper.getQuantidadeFaturasAtraso():0 );
		this.valorFaturasAtrasoSemEncargos = Util.formatarMoedaReal( helper.getValorFaturasAtrasoSemEncargos()!=null? helper.getValorFaturasAtrasoSemEncargos():BigDecimal.ZERO);
		this.valorFaturasAtrasoComEncargos = Util.formatarMoedaReal( helper.getValorFaturasAtrasoComEncargos()!=null?helper.getValorFaturasAtrasoComEncargos():BigDecimal.ZERO );

		this.matriculaImovel = helper.getMatriculaImovel();
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.endereco = helper.getEndereco();
		
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
	
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
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

	
	public String getQuantidadeFaturasAtraso() {
	
		return quantidadeFaturasAtraso;
	}

	
	public String getReferenciaFaturasAtrasoFinal() {
	
		return referenciaFaturasAtrasoFinal;
	}

	
	public String getReferenciaFaturasAtrasoInicial() {
	
		return referenciaFaturasAtrasoInicial;
	}

	
	public String getValorFaturasAtrasoSemEncargos() {
	
		return valorFaturasAtrasoSemEncargos;
	}

	
	public String getSituacaoLigacaoEsgoto() {
	
		return situacaoLigacaoEsgoto;
	}

	
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
	
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public JRBeanCollectionDataSource getArrayJrContas() {
		return arrayJrContas;
	}

	public void setArrayJrContas(JRBeanCollectionDataSource arrayJrContas) {
		this.arrayJrContas = arrayJrContas;
	}

	public ArrayList<RelatorioImoveisFaturasAtrasoContasBean> getArrayRelatorioImoveisFaturasAtrasoContasBean() {
		return arrayRelatorioImoveisFaturasAtrasoContasBean;
	}

	public void setArrayRelatorioImoveisFaturasAtrasoContasBean(
			ArrayList<RelatorioImoveisFaturasAtrasoContasBean> arrayRelatorioImoveisFaturasAtrasoContasBean) {
		this.arrayRelatorioImoveisFaturasAtrasoContasBean = arrayRelatorioImoveisFaturasAtrasoContasBean;
	}

	public BigDecimal getValorTotalFaturaAtrasoSemEncargo() {
		return valorTotalFaturaAtrasoSemEncargo;
	}

	public void setValorTotalFaturaAtrasoSemEncargo(BigDecimal totalImovel) {
		this.valorTotalFaturaAtrasoSemEncargo = totalImovel;
	}

	public String getCpfOuCnpjClienteUsuario() {
		return cpfOuCnpjClienteUsuario;
	}

	public void setCpfOuCnpjClienteUsuario(String cpfOuCnpjClienteUsuario) {
		this.cpfOuCnpjClienteUsuario = cpfOuCnpjClienteUsuario;
	}

	public String getValorFaturasAtrasoComEncargos() {
		return valorFaturasAtrasoComEncargos;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public BigDecimal getValorTotalFaturaAtrasoComEncargo() {
		return valorTotalFaturaAtrasoComEncargo;
	}

	public void setValorTotalFaturaAtrasoComEncargo(
			BigDecimal valorTotalFaturaAtrasoComEncargo) {
		this.valorTotalFaturaAtrasoComEncargo = valorTotalFaturaAtrasoComEncargo;
	}
}
