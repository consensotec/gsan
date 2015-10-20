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
import gcom.atendimentopublico.contratoadesao.FiltroContratoAdesao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirContratoAdesaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
			// Seta o caminho de retorno
			ActionForward retorno = actionMapping.findForward("telaSucesso");
				
					InserirContratoAdesaoActionForm inserirContratoAdesaoActionForm = (InserirContratoAdesaoActionForm) actionForm;

					// Mudar isso quando houver esquema de segurança
					HttpSession sessao = httpServletRequest.getSession(false);
					
					
					String descricao = inserirContratoAdesaoActionForm.getDescricao();
					String descricaoAbreviada = inserirContratoAdesaoActionForm.getDescricaoAbreviada();
			
					
					
					ContratoAdesao contratoAdesao = new ContratoAdesao();
					Collection colecaoPesquisa = null;

					// Verifica se a Descrição foi passada
					if (!"".equals(descricao)) {
						contratoAdesao.setDescricao(descricao);
					} else {
						throw new ActionServletException("atencao.required", null,
								"Descrição");
					}
					
					
					// Verifica se a Descrição abreviada foi passada
					if (!"".equals(descricaoAbreviada)) {
						contratoAdesao.setDescricaoAbreviada(descricaoAbreviada);
					} else {
						throw new ActionServletException("atencao.required", null,
								"Descrição Abreviada");
					}
					
					//Verifica se o arquivo (.PDF) foi passado
					
					if(inserirContratoAdesaoActionForm.getArquivo().getFileName() != null && !inserirContratoAdesaoActionForm.getArquivo().getFileName().equals("") ){
						
						if(inserirContratoAdesaoActionForm.getArquivo().getFileName().toUpperCase().contains(".PDF") )
						{
							try {
								contratoAdesao.setImagemArquivo(inserirContratoAdesaoActionForm.getArquivo().getFileData());
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else{
							
							throw new ActionServletException(
								"atencao.required",
								null, "apenas arquivo em formato .pdf");
						}		
					
							
					
					}else{
						
						throw new ActionServletException(
							"atencao.required",
							null, "um arquivo .pdf");
					}
					
					contratoAdesao.setDescricao(descricao);
					contratoAdesao.setDescricaoAbreviada(descricaoAbreviada);
					contratoAdesao.setIndicadorUso(ConstantesSistema.SIM);
					contratoAdesao.setUltimaAlteracao(new Date());
					
					try {
						
						contratoAdesao.setImagemArquivo(inserirContratoAdesaoActionForm.getArquivo().getFileData());
					
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					FiltroContratoAdesao filtroContratoAdesao = new FiltroContratoAdesao();

					filtroContratoAdesao.adicionarParametro(
						new ParametroSimples(
							FiltroContratoAdesao.DESCRICAO, 
							contratoAdesao.getDescricao()));
					
										
					colecaoPesquisa = 
						(Collection) this.getFachada().pesquisar(
							filtroContratoAdesao, ContratoAdesao.class.getName());

					if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
									
						// Caso já haja um Contrato de Adesão com a Descrição 
						throw new ActionServletException("atencao.contrato_adesao_ja_cadastrado", 
							null,contratoAdesao.getDescricao());
					} else {

					Integer id= (Integer) this.getFachada().inserir(contratoAdesao);

						montarPaginaSucesso(httpServletRequest,
							"Contrato de Adesao " + descricao+ " inserido com sucesso.",
							"Inserir Outro Contrato de Adesao",
							"exibirInserirContratoAdesaoAction.do?menu=sim",
							"exibirAtualizarContratoAdesaoAction.do?idRegistroAtualizar=" + id+"&primeiraVez=Ok",
							"Atualizar Contrato de Adesao Inserido");
						
						
						sessao.removeAttribute("InserirContratoAdesaoActionForm");

						return retorno;
			
					}

				
		}
	
	
}
