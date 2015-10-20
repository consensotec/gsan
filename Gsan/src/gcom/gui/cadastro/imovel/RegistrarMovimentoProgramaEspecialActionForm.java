/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cadastro.imovel;

import javax.servlet.ServletRequest;

import gcom.util.ConstantesSistema;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

/**
 */

public class RegistrarMovimentoProgramaEspecialActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String acao;
	private String mesAnoFaturamento;
    private FormFile arquivoMovimento;
    private String observacao;
    private String cancelarItensFatura;
    private String cancelarItensFaturaHidden;
    private String retirarContasProgEspecial;
    private String retirarContasProgEspecialHidden;
    private String retirarSituacaoEspCobranca;
    private String retirarSituacaoEspCobrancaHidden;
    
    
    public RegistrarMovimentoProgramaEspecialActionForm(){
    	this.acao = "1";
    	this.cancelarItensFatura = ConstantesSistema.NAO.toString();
    	this.retirarContasProgEspecial = ConstantesSistema.NAO.toString();
    	this.retirarSituacaoEspCobranca = ConstantesSistema.NAO.toString();
    }


	public String getAcao() {
		return acao;
	}


	public void setAcao(String acao) {
		this.acao = acao;
	}


	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}


	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}


	public FormFile getArquivoMovimento() {
		return arquivoMovimento;
	}


	public void setArquivoMovimento(FormFile arquivoMovimento) {
		this.arquivoMovimento = arquivoMovimento;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String getCancelarItensFatura() {
		return cancelarItensFatura;
	}


	public void setCancelarItensFatura(String cancelarItensFatura) {
		this.cancelarItensFatura = cancelarItensFatura;
	}


	public String getRetirarContasProgEspecial() {
		return retirarContasProgEspecial;
	}


	public void setRetirarContasProgEspecial(String retirarContasProgEspecial) {
		this.retirarContasProgEspecial = retirarContasProgEspecial;
	}


	public String getRetirarSituacaoEspCobranca() {
		return retirarSituacaoEspCobranca;
	}


	public void setRetirarSituacaoEspCobranca(String retirarSituacaoEspCobranca) {
		this.retirarSituacaoEspCobranca = retirarSituacaoEspCobranca;
	}


	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		this.acao = "1";
		this.mesAnoFaturamento = null;
	    this.arquivoMovimento = null;
	    this.observacao = null;
    	this.cancelarItensFatura = ConstantesSistema.NAO.toString();
    	this.retirarContasProgEspecial = ConstantesSistema.NAO.toString();
    	this.retirarSituacaoEspCobranca = ConstantesSistema.NAO.toString();
	}


	public String getCancelarItensFaturaHidden() {
		return cancelarItensFaturaHidden;
	}


	public void setCancelarItensFaturaHidden(String cancelarItensFaturaHidden) {
		this.cancelarItensFaturaHidden = cancelarItensFaturaHidden;
	}


	public String getRetirarContasProgEspecialHidden() {
		return retirarContasProgEspecialHidden;
	}


	public void setRetirarContasProgEspecialHidden(
			String retirarContasProgEspecialHidden) {
		this.retirarContasProgEspecialHidden = retirarContasProgEspecialHidden;
	}


	public String getRetirarSituacaoEspCobrancaHidden() {
		return retirarSituacaoEspCobrancaHidden;
	}


	public void setRetirarSituacaoEspCobrancaHidden(
			String retirarSituacaoEspCobrancaHidden) {
		this.retirarSituacaoEspCobrancaHidden = retirarSituacaoEspCobrancaHidden;
	}
    
	
    
	
}
