/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rodrigo Cabral
 * @version 1.0
 */

public class RelatorioManterConsumoAnormalidadeAcaoBean implements RelatorioBean {
	
private String consumoAnormalidade;	
	
	private String categoria;
	
	private String imovelPerfil;
	
    private String leituraAnormalidadeConsumoMes1;

    private String leituraAnormalidadeConsumoMes2;

    private String leituraAnormalidadeConsumoMes3;
	
    private String numerofatorConsumoMes1;

    private String numerofatorConsumoMes2;

    private String numerofatorConsumoMes3;

    private String indicadorGeracaoCartaMes1;

    private String indicadorGeracaoCartaMes2;

    private String indicadorGeracaoCartaMes3;

    private String servicoTipoMes1;
    
    private String servicoTipoMes2;
    
    private String servicoTipoMes3;
    
    private String solicitacaoTipoEspecificacaoMes1;

    private String solicitacaoTipoEspecificacaoMes2;

    private String solicitacaoTipoEspecificacaoMes3;
    
    private String descricaoContaMensagemMes1;
    
    private String descricaoContaMensagemMes2;
    
    private String descricaoContaMensagemMes3;
    
    private String indicadorUso;

	public RelatorioManterConsumoAnormalidadeAcaoBean(
			String consumoAnormalidade, String categoria, String imovelPerfil,
			String leituraAnormalidadeConsumoMes1,
			String leituraAnormalidadeConsumoMes2,
			String leituraAnormalidadeConsumoMes3,
			String numerofatorConsumoMes1, String numerofatorConsumoMes2,
			String numerofatorConsumoMes3, String indicadorGeracaoCartaMes1,
			String indicadorGeracaoCartaMes2, String indicadorGeracaoCartaMes3,
			String servicoTipoMes1, String servicoTipoMes2,
			String servicoTipoMes3, 
			String solicitacaoTipoEspecificacaoMes1,
			String solicitacaoTipoEspecificacaoMes2,
			String solicitacaoTipoEspecificacaoMes3,
			String descricaoContaMensagemMes1,
			String descricaoContaMensagemMes2,
			String descricaoContaMensagemMes3, String indicadorUso) {
		super();
		this.consumoAnormalidade = consumoAnormalidade;
		this.categoria = categoria;
		this.imovelPerfil = imovelPerfil;
		this.leituraAnormalidadeConsumoMes1 = leituraAnormalidadeConsumoMes1;
		this.leituraAnormalidadeConsumoMes2 = leituraAnormalidadeConsumoMes2;
		this.leituraAnormalidadeConsumoMes3 = leituraAnormalidadeConsumoMes3;
		this.numerofatorConsumoMes1 = numerofatorConsumoMes1;
		this.numerofatorConsumoMes2 = numerofatorConsumoMes2;
		this.numerofatorConsumoMes3 = numerofatorConsumoMes3;
		this.indicadorGeracaoCartaMes1 = indicadorGeracaoCartaMes1;
		this.indicadorGeracaoCartaMes2 = indicadorGeracaoCartaMes2;
		this.indicadorGeracaoCartaMes3 = indicadorGeracaoCartaMes3;
		this.servicoTipoMes1 = servicoTipoMes1;
		this.servicoTipoMes2 = servicoTipoMes2;
		this.servicoTipoMes3 = servicoTipoMes3;
		this.solicitacaoTipoEspecificacaoMes1 = solicitacaoTipoEspecificacaoMes1;
		this.solicitacaoTipoEspecificacaoMes2 = solicitacaoTipoEspecificacaoMes2;
		this.solicitacaoTipoEspecificacaoMes3 = solicitacaoTipoEspecificacaoMes3;
		this.descricaoContaMensagemMes1 = descricaoContaMensagemMes1;
		this.descricaoContaMensagemMes2 = descricaoContaMensagemMes2;
		this.descricaoContaMensagemMes3 = descricaoContaMensagemMes3;
		this.indicadorUso = indicadorUso;
	}

	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getLeituraAnormalidadeConsumoMes1() {
		return leituraAnormalidadeConsumoMes1;
	}

	public void setLeituraAnormalidadeConsumoMes1(
			String leituraAnormalidadeConsumoMes1) {
		this.leituraAnormalidadeConsumoMes1 = leituraAnormalidadeConsumoMes1;
	}

	public String getLeituraAnormalidadeConsumoMes2() {
		return leituraAnormalidadeConsumoMes2;
	}

	public void setLeituraAnormalidadeConsumoMes2(
			String leituraAnormalidadeConsumoMes2) {
		this.leituraAnormalidadeConsumoMes2 = leituraAnormalidadeConsumoMes2;
	}

	public String getLeituraAnormalidadeConsumoMes3() {
		return leituraAnormalidadeConsumoMes3;
	}

	public void setLeituraAnormalidadeConsumoMes3(
			String leituraAnormalidadeConsumoMes3) {
		this.leituraAnormalidadeConsumoMes3 = leituraAnormalidadeConsumoMes3;
	}

	public String getNumerofatorConsumoMes1() {
		return numerofatorConsumoMes1;
	}

	public void setNumerofatorConsumoMes1(String numerofatorConsumoMes1) {
		this.numerofatorConsumoMes1 = numerofatorConsumoMes1;
	}

	public String getNumerofatorConsumoMes2() {
		return numerofatorConsumoMes2;
	}

	public void setNumerofatorConsumoMes2(String numerofatorConsumoMes2) {
		this.numerofatorConsumoMes2 = numerofatorConsumoMes2;
	}

	public String getNumerofatorConsumoMes3() {
		return numerofatorConsumoMes3;
	}

	public void setNumerofatorConsumoMes3(String numerofatorConsumoMes3) {
		this.numerofatorConsumoMes3 = numerofatorConsumoMes3;
	}

	public String getIndicadorGeracaoCartaMes1() {
		return indicadorGeracaoCartaMes1;
	}

	public void setIndicadorGeracaoCartaMes1(String indicadorGeracaoCartaMes1) {
		this.indicadorGeracaoCartaMes1 = indicadorGeracaoCartaMes1;
	}

	public String getIndicadorGeracaoCartaMes2() {
		return indicadorGeracaoCartaMes2;
	}

	public void setIndicadorGeracaoCartaMes2(String indicadorGeracaoCartaMes2) {
		this.indicadorGeracaoCartaMes2 = indicadorGeracaoCartaMes2;
	}

	public String getIndicadorGeracaoCartaMes3() {
		return indicadorGeracaoCartaMes3;
	}

	public void setIndicadorGeracaoCartaMes3(String indicadorGeracaoCartaMes3) {
		this.indicadorGeracaoCartaMes3 = indicadorGeracaoCartaMes3;
	}


	public String getServicoTipoMes1() {
		return servicoTipoMes1;
	}

	public void setServicoTipoMes1(String servicoTipoMes1) {
		this.servicoTipoMes1 = servicoTipoMes1;
	}

	public String getServicoTipoMes2() {
		return servicoTipoMes2;
	}

	public void setServicoTipoMes2(String servicoTipoMes2) {
		this.servicoTipoMes2 = servicoTipoMes2;
	}

	public String getServicoTipoMes3() {
		return servicoTipoMes3;
	}

	public void setServicoTipoMes3(String servicoTipoMes3) {
		this.servicoTipoMes3 = servicoTipoMes3;
	}

	public String getSolicitacaoTipoEspecificacaoMes1() {
		return solicitacaoTipoEspecificacaoMes1;
	}

	public void setSolicitacaoTipoEspecificacaoMes1(
			String solicitacaoTipoEspecificacaoMes1) {
		this.solicitacaoTipoEspecificacaoMes1 = solicitacaoTipoEspecificacaoMes1;
	}

	public String getSolicitacaoTipoEspecificacaoMes2() {
		return solicitacaoTipoEspecificacaoMes2;
	}

	public void setSolicitacaoTipoEspecificacaoMes2(
			String solicitacaoTipoEspecificacaoMes2) {
		this.solicitacaoTipoEspecificacaoMes2 = solicitacaoTipoEspecificacaoMes2;
	}

	public String getSolicitacaoTipoEspecificacaoMes3() {
		return solicitacaoTipoEspecificacaoMes3;
	}

	public void setSolicitacaoTipoEspecificacaoMes3(
			String solicitacaoTipoEspecificacaoMes3) {
		this.solicitacaoTipoEspecificacaoMes3 = solicitacaoTipoEspecificacaoMes3;
	}

	public String getDescricaoContaMensagemMes1() {
		return descricaoContaMensagemMes1;
	}

	public void setDescricaoContaMensagemMes1(String descricaoContaMensagemMes1) {
		this.descricaoContaMensagemMes1 = descricaoContaMensagemMes1;
	}

	public String getDescricaoContaMensagemMes2() {
		return descricaoContaMensagemMes2;
	}

	public void setDescricaoContaMensagemMes2(String descricaoContaMensagemMes2) {
		this.descricaoContaMensagemMes2 = descricaoContaMensagemMes2;
	}

	public String getDescricaoContaMensagemMes3() {
		return descricaoContaMensagemMes3;
	}

	public void setDescricaoContaMensagemMes3(String descricaoContaMensagemMes3) {
		this.descricaoContaMensagemMes3 = descricaoContaMensagemMes3;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	


}