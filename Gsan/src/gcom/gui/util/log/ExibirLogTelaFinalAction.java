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
package gcom.gui.util.log;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirLogTelaFinalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirLogTelaFinal");
		ExibirLogActionForm form = (ExibirLogActionForm) actionForm;
		StringBuffer textoErro = new StringBuffer("");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		try{
			int TAMANHO_PAGINA = 1500;
			
			String nomeArquivo = form.getArquivo();
			
//			File file = new File("C:\\IPAD\\jboss\\server\\default\\log\\"+nomeArquivo);
			File file = new File("/usr/local/jboss/server/default/log/"+nomeArquivo);
			
			FileReader fw = new FileReader(file);
			BufferedReader bis = new BufferedReader (fw);
			
			try {		
				
				String ini = httpServletRequest.getParameter("inicio");
				String numeroPagina = httpServletRequest.getParameter("numeroPagina");
				String verTud = httpServletRequest.getParameter("verTudo");
				String palavra = httpServletRequest.getParameter("palavra");
				String texto = httpServletRequest.getParameter("texto");
				String menu = httpServletRequest.getParameter("menu");
				
				boolean verTudo = false;
				int inicio = 0;
				int tamanhoDoArquivo = 0;
				
				if (menu != null && menu.equals("sim") ) {
					sessao.removeAttribute("palavraPesquisada");
					sessao.removeAttribute("textoPesquisado");
				}
				
				if ( numeroPagina != null && !numeroPagina.equals("") ) {
					Integer numero = Integer.valueOf(numeroPagina);
					numero = numero - 1;
					numeroPagina = String.valueOf(numero);
				}
				
				if ( texto != null && !texto.equals("") ) {
					sessao.removeAttribute("palavraPesquisada");
				}
				if ( palavra != null && !palavra.equals("") ) {
					sessao.removeAttribute("textoPesquisado");
				}
				
				if ( (texto == null || texto.equals("")) && sessao.getAttribute("textoPesquisado") != null ) {
					String line;
					while ((line = bis.readLine()) != null ){
						int posicao = line.indexOf(texto);
						if( posicao != -1 ){
							tamanhoDoArquivo++;	
						}
					}
				}
				
				while ( bis.readLine() != null ){
					tamanhoDoArquivo++;
				}
				if (bis != null){
					bis.close();	
				}
				if (fw != null){
					fw.close();
				}
				
				fw = new FileReader(file);				
				bis = new BufferedReader (fw);
				
				if (verTud != null && !verTud.equals("")) {
					verTudo = true;
				}

				if (ini != null && !ini.equals("")) {
					inicio = Integer.parseInt(ini);
				
				}else if(numeroPagina != null && !numeroPagina.equals("") ){
					try{
						Integer numPagina = Integer.parseInt(numeroPagina);
						if(numPagina < 0){
							throw new Exception();
						}
					}catch(Exception ex){
						throw new ActionServletException("atencao.numero_pagina_invalido");
					}
					
					inicio = Integer.parseInt(numeroPagina);
					inicio = inicio*TAMANHO_PAGINA;
				
				}

				if( verTudo ){
					
					String line;
					while ( (line = bis.readLine()) != null){
						textoErro.append(line);
						textoErro.append("<br>");
					}
					sessao.removeAttribute("palavraPesquisada");
					sessao.removeAttribute("textoPesquisado");
				}else if( palavra != null && !palavra.equals("") || sessao.getAttribute("palavraPesquisada") != null ){				
					
					int numeroPaginaAtual = 0;
					
					if ( palavra != null && !palavra.equals("") ) {
						sessao.setAttribute("palavraPesquisada", palavra);
						form.setPaginaAtual("1");
						numeroPaginaAtual = new Integer(form.getPaginaAtual());
					} else if ( sessao.getAttribute("palavraPesquisada") != null ) {
						palavra = (String) sessao.getAttribute("palavraPesquisada") ;
						numeroPaginaAtual = new Integer(form.getPaginaAtual());
						if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("proximo") ) {
							numeroPaginaAtual ++;
						} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("anterior") ) {
							numeroPaginaAtual --;
						} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("ultimo") ) {
							numeroPaginaAtual = Util.dividirArredondarResultadoBaixo(tamanhoDoArquivo, TAMANHO_PAGINA) ;
						} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("primeiro") ) {
							numeroPaginaAtual = 1;
						}
						
					}
					
					String line;
					int tam = 0;
					
					while ( (line = bis.readLine()) != null && tam <= (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual)) ){
						tam++;
						if ( tam > ( (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual) ) - TAMANHO_PAGINA) ) {
							int posicao = line.indexOf(palavra);
							if(posicao != -1){
								
								textoErro.append(line.substring(0,posicao));
								textoErro.append("<span style=\"background:#44ff77;\" >");
								textoErro.append(line.substring(posicao,posicao+palavra.length()));
								textoErro.append("</span>");
								textoErro.append(line.substring(posicao+palavra.length()));
								textoErro.append("<br>");
								tam++;
							
							} else {
								textoErro.append(line);
								textoErro.append("<br>");
								tam++;
							}
						}
					}
					
					if ( textoErro.toString().equals("") ) {
						throw new ActionServletException("atencao.pagina_inexistente");
					}
					
				}else if(texto != null && !texto.equals("") || sessao.getAttribute("textoPesquisado") != null ){				
					
					int numeroPaginaAtual = 0;
					
					if ( texto != null && !texto.equals("") ) {
						sessao.setAttribute("textoPesquisado", texto);
						form.setPaginaAtual("1");
						numeroPaginaAtual = new Integer(form.getPaginaAtual());
					} else if ( sessao.getAttribute("textoPesquisado") != null ) {
						texto = (String) sessao.getAttribute("textoPesquisado") ;
						numeroPaginaAtual = new Integer(form.getPaginaAtual());
						if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("proximo") ) {
							numeroPaginaAtual ++;
						} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("anterior") ) {
							numeroPaginaAtual --;
						} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("ultimo") ) {
							numeroPaginaAtual = Util.dividirArredondarResultadoBaixo(tamanhoDoArquivo, TAMANHO_PAGINA) ;
						} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("primeiro") ) {
							numeroPaginaAtual = 1;
						}
						
					}
					
					String line;
					int tam = 0;
					int in = 0;
					
					while ( (line = bis.readLine()) != null && tam <= (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual)) ){
					
						int posicao = line.indexOf(texto);
						if( posicao != -1 ){
							in++;
							if ( in > ( (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual) ) - TAMANHO_PAGINA) ) {
								
								textoErro.append(line.substring(0,posicao));
								textoErro.append("<span style=\"background:#44ff77;\" >");
								textoErro.append(line.substring(posicao,posicao+texto.length()));
								textoErro.append("</span>");
								textoErro.append(line.substring(posicao+texto.length()));
								textoErro.append("<br>");
								
								if(tam == 0){
									inicio = in;
								}
								tam++;
							}
						}
					}
				
					if ( textoErro.toString().equals("") ) {
						throw new ActionServletException("atencao.pagina_inexistente");
					}
				
				}else{
					int numeroPaginaAtual = 1;
					if ( form.getPaginaAtual() != null ) {
						numeroPaginaAtual = new Integer(form.getPaginaAtual());
					}
					if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("proximo") ) {
						numeroPaginaAtual ++;
					} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("anterior") ) {
						numeroPaginaAtual --;
					} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("ultimo") ) {
						numeroPaginaAtual = Util.dividirArredondarResultadoBaixo(tamanhoDoArquivo, TAMANHO_PAGINA) ;
						numeroPaginaAtual ++;
					} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("primeiro") ) {
						numeroPaginaAtual = 1;
					} else if(numeroPagina != null && !numeroPagina.equals("") ){
						
						numeroPaginaAtual = new Integer(numeroPagina);
						numeroPaginaAtual ++;
					}
				
					String line;
					int tam = 0;
					
					while ( (line = bis.readLine()) != null && tam <= (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual)) ){
						tam++;
						if ( tam > ( (Util.multiplica(TAMANHO_PAGINA, numeroPaginaAtual) ) - TAMANHO_PAGINA) ) {
							textoErro.append(line);
							textoErro.append("<br>");
						}
					}

				}
				
				int anterior = inicio-TAMANHO_PAGINA;
				int proximo = inicio+TAMANHO_PAGINA ;
				int ultima 	= tamanhoDoArquivo;
				int paginaAtual = (inicio/TAMANHO_PAGINA) + 1;
				
				if(inicio != 0 ){
					form.setInicio(""+inicio);	
				}else{
					form.setInicio(null);
				}
				
				form.setAnterior(""+anterior);
				form.setProximo(""+proximo);
				form.setUltima(""+ultima);
				form.setPaginaAtual(""+paginaAtual);
				
			} catch (EOFException eof){
				System.out.println("Fim do arquivo");
			} finally {
				if (bis != null){
					bis.close();	
				}
				if (fw != null){
					fw.close();
				}
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
        
		
		if ( textoErro.toString().equals("") ) {
			throw new ActionServletException("atencao.pagina_inexistente");
		}
		form.setTextoErro(textoErro.toString());
		
		
        return retorno;
	}

}