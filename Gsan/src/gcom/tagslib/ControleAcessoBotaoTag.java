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
package gcom.tagslib;

import gcom.fachada.Fachada;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.struts.Globals;
import org.apache.struts.util.RequestUtils;

/**
 * Classe respons�vel por construir um bot�o habilitado caso o usu�rio logado tenha 
 * acesso a opera��o requerida ou um bot�o desabilitado caso contr�rio. 
 *
 * @author Pedro Alexandre
 * @date 20/07/2006
 */
public class ControleAcessoBotaoTag extends SimpleTagSupport {
	
	//Nome do bot�o que vai ser criado, essa propriedade � requerida
	private String name;
	
	//Descri��o que vai aparecer no bot�o que vai ser criado, essa propriedade � requerida
	private String value;

	//Url da opera��o para ser acessada, essa propriedade � requerida
	private String url;
	
	//Met�do javascript que vai ser chamado no click do bot�o, essa propriedade � requerida
	private String onclick;
	
	//Essas propriedades n�o s�o requeridas
	private String align;
	private String tabindex;
    private String style;
	
	
	/**
	 * Met�do chamado para construir o componente da tag lib
	 *
	 * @author Administrador
	 * @date 20/07/2006
	 *
	 * @throws JspException
	 * @throws IOException
	 */
	public void doTag() throws JspException, IOException{
		
		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Cria a vari�vel que vai conter a string que cria o bot�o na jsp
		String botaoAcesso = null;
		
		//Cria a vari�vel contendo a parte inicial para todo o bot�o
		//String inicioBotao = "<input type=\"" + "button\" " +  " class=\"" + "bottonRightCol\" " + " style=\""+"width: 70px;\" ";
		String inicioBotao = "<input type=\"" + "button\" " +  " class=\"" + "bottonRightCol\" " ;
		
		//Cria a vari�vel contendo a parte final para todo o bot�o
		String fimBotao = "/>";
		
		//Caso tenha informado a propriedade "tabindex" no componente 
		//adicona a propriedade ao bot�o na parte final
		if(tabindex != null && !tabindex.trim().equals("")){
			fimBotao = "tabindex=\"" + tabindex + "\" " + fimBotao;
		}

		//Caso tenha informado a propriedade "align" no componente 
		//adicona a propriedade ao bot�o na parte final
		if(align != null && !align.trim().equals("")){
			fimBotao = "align=\"" + align + "\" " + fimBotao;
		}

        //Caso tenha informado a propriedade "style" no componente 
        //adicona a propriedade ao bot�o na parte final
        if(style != null && !style.trim().equals("")){
            fimBotao = "style=\"" + style + "\" " + fimBotao;
        }
        
		//Vari�vel contendo o valor para desabilitar o bot�o caso 
		//o usu�rio n�o tenha acesso a opera��o
		String desabilitado = "disabled";
		
		//Recupera o jsp context
		JspContext jspContext = getJspContext();
		
		//Recupera o usu�rio logado da sess�o
		Usuario usuarioLogado =(Usuario) jspContext.getAttribute("usuarioLogado",PageContext.SESSION_SCOPE);
		
		//Recupera a cole��o de grupos ao qual o us�rio pertence
		Collection<Grupo> colecaoGruposUsuario =(Collection<Grupo>) jspContext.getAttribute("colecaoGruposUsuario",PageContext.SESSION_SCOPE);
		
		//Chama o met�do da fachda para verificar se o usu�rio tem acesso a opera��o
		boolean permitirAcesso = fachada.verificarAcessoPermitidoOperacao(usuarioLogado,url,colecaoGruposUsuario);

	    if(Util.verificarNaoVazio(this.value)){
	    	
	    	String msg = RequestUtils.message((PageContext)jspContext, Globals.MESSAGES_KEY, Globals.LOCALE_KEY, this.value);
	    	if(Util.verificarNaoVazio(msg) && !(msg.startsWith("???") && msg.endsWith("???"))){
	    		this.value = msg;
	    	}
	    }

		/*
		 * Caso o usu�rio tenha acesso a opera��o indicada pela url
		 * constroi o bot�o habilitado
		 * Caso o usu�rio n�o tenha acesso a opera��o
		 * constroi o bot�o desabilitado
		 */
		if(permitirAcesso){
			botaoAcesso =inicioBotao + "name=\""+name +"\""+ " value=\""+ value +"\""+ " onclick=\"" + onclick+"\" " + fimBotao;
		}else{
			botaoAcesso =inicioBotao + "name=\""+name +"\""+ " value=\""+ value +"\""+ " " + desabilitado+" " + fimBotao;
		}

		//Escreve o bot�o na jsp
		jspContext.getOut().print(botaoAcesso);
	}

	/**
	 * @param align O align a ser setado.
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @param name O name a ser setado.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param onclick O onclick a ser setado.
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * @param tabindex O tabindex a ser setado.
	 */
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}

	/**
	 * @param url O url a ser setado.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param value O value a ser setado.
	 */
	public void setValue(String value) {
		this.value = value;
	}

    public String getAlign() {
        return align;
    }
    
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
