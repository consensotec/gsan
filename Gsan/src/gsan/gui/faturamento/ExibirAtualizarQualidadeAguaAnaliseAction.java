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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gsan.gui.faturamento;

import gsan.faturamento.QualidadeAgua;
import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarQualidadeAguaAnaliseAction extends GcomAction {

	/**
	 * Description of the Method
	 */
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarQualidadeAguaAnaliseAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Integer entrada = (Integer)sessao.getAttribute("PrimeiraVez");
		
		if(entrada == 1){
		
			QualidadeAgua qualidadeAgua = (QualidadeAgua) sessao.getAttribute("qualidadeAgua"); 
			
			AtualizarQualidadeAguaActionForm form = (AtualizarQualidadeAguaActionForm) actionForm;
			
			form.setQuantidadeTurbidezExigidas(qualidadeAgua
					.getQuantidadeTurbidezExigidas() != null ? qualidadeAgua
							.getQuantidadeTurbidezExigidas().toString() : "");
			
			form.setQuantidadeTurbidezAnalisadas(qualidadeAgua
					.getQuantidadeTurbidezAnalisadas() != null ? qualidadeAgua
					.getQuantidadeTurbidezAnalisadas().toString() : "");
			
			form.setQuantidadeTurbidezConforme(qualidadeAgua
					.getQuantidadeTurbidezConforme() != null ? qualidadeAgua
					.getQuantidadeTurbidezConforme().toString() : "");
			
			form.setQuantidadeCorExigidas(qualidadeAgua
					.getQuantidadeCorExigidas() != null ? qualidadeAgua
					.getQuantidadeCorExigidas().toString() : "");
			
			form.setQuantidadeCorAnalisadas(qualidadeAgua
					.getQuantidadeCorAnalisadas() != null ? qualidadeAgua
					.getQuantidadeCorAnalisadas().toString() : "");
			
			form.setQuantidadeCorConforme(qualidadeAgua
					.getQuantidadeCorConforme() != null ? qualidadeAgua
					.getQuantidadeCorConforme().toString() : "");
			
			form.setQuantidadeCloroExigidas(qualidadeAgua
					.getQuantidadeCloroExigidas() != null ? qualidadeAgua
					.getQuantidadeCloroExigidas().toString() : "");
			
			form.setQuantidadeCloroAnalisadas(qualidadeAgua
					.getQuantidadeCloroAnalisadas() != null ? qualidadeAgua
					.getQuantidadeCloroAnalisadas().toString() : "");
			
			form.setQuantidadeCloroConforme(qualidadeAgua
					.getQuantidadeCloroConforme() != null ? qualidadeAgua
					.getQuantidadeCloroConforme().toString() : "");
			
			form.setQuantidadeFluorExigidas(qualidadeAgua
					.getQuantidadeFluorExigidas() != null ? qualidadeAgua
					.getQuantidadeFluorExigidas().toString() : "");
			
			form.setQuantidadeFluorAnalisadas(qualidadeAgua
					.getQuantidadeFluorAnalisadas() != null ? qualidadeAgua
					.getQuantidadeFluorAnalisadas().toString() : "");
			
			form.setQuantidadeFluorConforme(qualidadeAgua
					.getQuantidadeFluorConforme() != null ? qualidadeAgua
					.getQuantidadeFluorConforme().toString() : "");
			
			form.setQuantidadeColiformesTotaisExigidas(qualidadeAgua
					.getQuantidadeColiformesTotaisExigidas() != null ? qualidadeAgua
					.getQuantidadeColiformesTotaisExigidas().toString() : "");
			
			form.setQuantidadeColiformesTotaisAnalisadas(qualidadeAgua
					.getQuantidadeColiformesTotaisAnalisadas() != null ? qualidadeAgua
					.getQuantidadeColiformesTotaisAnalisadas().toString() : "");
			
			form.setQuantidadeColiformesTotaisConforme(qualidadeAgua
					.getQuantidadeColiformesTotaisConforme() != null ? qualidadeAgua
					.getQuantidadeColiformesTotaisConforme().toString() : "");
			
			form.setQuantidadeColiformesFecaisExigidas(qualidadeAgua
					.getQuantidadeColiformesFecaisExigidas() != null ? qualidadeAgua
					.getQuantidadeColiformesFecaisExigidas().toString() : "");
			
			form.setQuantidadeColiformesFecaisAnalisadas(qualidadeAgua
					.getQuantidadeColiformesFecaisAnalisadas() != null ? qualidadeAgua
					.getQuantidadeColiformesFecaisAnalisadas().toString() : "");
			
			form.setQuantidadeColiformesFecaisConforme(qualidadeAgua
					.getQuantidadeColiformesFecaisConforme() != null ? qualidadeAgua
					.getQuantidadeColiformesFecaisConforme().toString() : "");
			
			form.setQuantidadeColiformesTermotolerantesExigidas(qualidadeAgua
					.getQuantidadeColiformesTermotolerantesExigidas() != null ? qualidadeAgua
					.getQuantidadeColiformesTermotolerantesExigidas().toString() : "");
			
			form.setQuantidadeColiformesTermotolerantesAnalisadas(qualidadeAgua
					.getQuantidadeColiformesTermotolerantesAnalisadas() != null ? qualidadeAgua
					.getQuantidadeColiformesTermotolerantesAnalisadas().toString() : "");
			
			form.setQuantidadeColiformesTermotolerantesConforme(qualidadeAgua
					.getQuantidadeColiformesTermotolerantesConforme() != null ? qualidadeAgua
					.getQuantidadeColiformesTermotolerantesConforme().toString() : "");
			
			form.setQuantidadeAlcalinidadeExigidas(qualidadeAgua
					.getQuantidadeAlcalinidadeExigidas() != null ? qualidadeAgua
					.getQuantidadeAlcalinidadeExigidas().toString() : "");
			
			form.setQuantidadeAlcalinidadeAnalisadas(qualidadeAgua
					.getQuantidadeAlcalinidadeAnalisadas() != null ? qualidadeAgua
					.getQuantidadeAlcalinidadeAnalisadas().toString() : "");
			
			form.setQuantidadeAlcalinidadeConforme(qualidadeAgua
					.getQuantidadeAlcalinidadeConforme() != null ? qualidadeAgua
					.getQuantidadeAlcalinidadeConforme().toString() : "");
			
			form.setQuantidadePhAnalisada("");
			if(qualidadeAgua.getQuantidadePhAnalisada() != null 
					&& !qualidadeAgua.getQuantidadePhAnalisada().equals("")){
				form.setQuantidadePhAnalisada(qualidadeAgua.getQuantidadePhAnalisada().toString());
			}
			
			form.setQuantidadePhConforme("");
			if(qualidadeAgua.getQuantidadePhConforme() != null 
					&& !qualidadeAgua.getQuantidadePhConforme().equals("")){
				form.setQuantidadePhConforme(qualidadeAgua.getQuantidadePhConforme().toString());
			}
			
			form.setQuantidadePhExigidas("");
			if(qualidadeAgua.getQuantidadePhExigida() != null 
					&& !qualidadeAgua.getQuantidadePhExigida().equals("")){
				form.setQuantidadePhExigidas(qualidadeAgua.getQuantidadePhExigida().toString());			
			}
			
			form.setQuantidadeDurezaTotalAnalisada("");
			if(qualidadeAgua.getQuantidadeDurezaAnalisada() != null 
					&& !qualidadeAgua.getQuantidadeDurezaAnalisada().equals("")){
				form.setQuantidadeDurezaTotalAnalisada(
					qualidadeAgua.getQuantidadeDurezaAnalisada().toString());
			}
			
			form.setQuantidadeDurezaTotalConforme("");
			if(qualidadeAgua.getQuantidadeDurezaConforme() != null
					&& !qualidadeAgua.getQuantidadeDurezaConforme().equals("")){
				form.setQuantidadeDurezaTotalConforme(
					qualidadeAgua.getQuantidadeDurezaConforme().toString());
			}
			
			form.setQuantidadeDurezaTotalExigidas("");
			if(qualidadeAgua.getQuantidadeDurezaExigida() != null
					&& !qualidadeAgua.getQuantidadeDurezaExigida().equals("")){
				form.setQuantidadeDurezaTotalExigidas(
					qualidadeAgua.getQuantidadeDurezaExigida().toString());
			}		
		}
		return retorno;		
	}
}
