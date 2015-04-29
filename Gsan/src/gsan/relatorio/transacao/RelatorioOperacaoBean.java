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
package gsan.relatorio.transacao;
 
import gsan.relatorio.RelatorioBean;

public class RelatorioOperacaoBean implements RelatorioBean {

	private String identificadorLinha1;
	private String identificadorLinha2;
	private String nomeTabela;
	private String nomeUsuario;
	private String acaoUsuario;
	private String tipoUsuario;
	private String nomeColuna;
	private String conteudoAnterior;
	private String conteudoAtual;
	private String dataHora;
	
	
	public RelatorioOperacaoBean ( String identificadorLinha1, String identificadorLinha2,
		String nomeTabela , String nomeUsuario , String acaoUsuario , 
		String tipoUsuario , String nomeColuna , String conteudoAnterior , 
		String conteudoAtual , String dataHora ) {
		
		super();
		// TODO Auto-generated constructor stub
		this.identificadorLinha1 = identificadorLinha1;
		this.identificadorLinha2 = identificadorLinha2;
		this.nomeTabela = nomeTabela;
		this.nomeUsuario = nomeUsuario;
		this.acaoUsuario = acaoUsuario;
		this.tipoUsuario = tipoUsuario;
		this.nomeColuna = nomeColuna;
		this.conteudoAnterior = conteudoAnterior;
		this.conteudoAtual = conteudoAtual;
		this.dataHora = dataHora;
	}
	
	public String getAcaoUsuario() {
		return acaoUsuario;
	}
	public void setAcaoUsuario(String acaoUsuario) {
		this.acaoUsuario = acaoUsuario;
	}
	public String getConteudoAnterior() {
		return conteudoAnterior;
	}
	public void setConteudoAnterior(String conteudoAnterior) {
		this.conteudoAnterior = conteudoAnterior;
	}
	public String getConteudoAtual() {
		return conteudoAtual;
	}
	public void setConteudoAtual(String conteudoAtual) {
		this.conteudoAtual = conteudoAtual;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getIdentificadorLinha1() {
		return identificadorLinha1;
	}
	public void setIdentificadorLinha1(String identificadorLinha1) {
		this.identificadorLinha1 = identificadorLinha1;
	}
	public String getIdentificadorLinha2() {
		return identificadorLinha2;
	}
	public void setIdentificadorLinha2(String identificadorLinha2) {
		this.identificadorLinha2 = identificadorLinha2;
	}
	public String getNomeColuna() {
		return nomeColuna;
	}
	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}
	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
