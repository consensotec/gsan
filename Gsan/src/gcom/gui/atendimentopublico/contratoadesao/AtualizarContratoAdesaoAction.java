/* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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

package gcom.gui.atendimentopublico.contratoadesao;

import gcom.atendimentopublico.contratoadesao.ContratoAdesao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class AtualizarContratoAdesaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarContratoAdesaoActionForm form = (AtualizarContratoAdesaoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		//Recuperando os dados do formulário
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String ativoInativo = form.getIndicadorUso();
		String id = form.getId();
		
		FormFile arquivo = form.getArquivo();
		
		
		//Configurando os valores do objeto para ser inserido na base de dados
		ContratoAdesao contratoAdesao = new ContratoAdesao();

		if(descricao  != null && descricao.length() <= 30){
			contratoAdesao.setDescricao(descricao);
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,"Descrição " );	
		}

		if(descricaoAbreviada  != null && descricaoAbreviada.length() <= 10){
			contratoAdesao.setDescricaoAbreviada(descricaoAbreviada);
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,"Descrição Abreviada " );	
		}
		
		
		if(Util.verificarNaoVazio(id)){
			try{
				contratoAdesao.setId(Integer.valueOf(id));
			}catch (NumberFormatException e) {
				throw new ActionServletException("erro.sistema", null ,"");	
			}
		}

		
		
		Short indicadorUso = null;
		if(ativoInativo != null){
			try{
				indicadorUso = Short.valueOf(ativoInativo);
				contratoAdesao.setIndicadorUso(indicadorUso);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null , "Indicador Uso" );	
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null , "Indicador Uso" );	
		}
		
		contratoAdesao.setUltimaAlteracao(new Date());
		
	
		
		//Verifica se o arquivo (.PDF) foi passado

			try {
				if(arquivo.getFileData() == null){
					
					if(form.getArquivo().getFileName().toUpperCase().contains(".PDF") )
					{
						
							contratoAdesao.setImagemArquivo(form.getArquivo().getFileData());
						
					}else{
						
						throw new ActionServletException(
							"atencao.required",
							null, "apenas arquivo em formato .pdf");
					}		
				
						
				
				}else{	

					if(form.getArquivo().getFileName().equals(""))
					{
							contratoAdesao.setImagemArquivo(form.getArquivo().getFileData());
						
					}else{
						
						if(form.getArquivo().getFileName().toUpperCase().contains(".PDF"))
						{
							contratoAdesao.setImagemArquivo(form.getArquivo().getFileData());
						}else{
							
							throw new ActionServletException(
								"atencao.required",
								null, "apenas arquivo em formato .pdf");
							
						}
			
					}	
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		
		fachada.atualizar(contratoAdesao);
		
		montarPaginaSucesso(httpServletRequest, "Contrato de Adesão "+ 
				contratoAdesao.getDescricao() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Contrato de Adesão",
				"exibirManterContratoAdesaoAction.do?menu=sim"); 
		        
				return retorno;

				
		}
	
	
}
