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
package gsan.gui.micromedicao;

import gsan.atendimentopublico.ordemservico.ArquivoTextoVisitaCampo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.atendimentopublico.ordemservico.ConsultarArquivoTextoOSVisitaActionForm;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action para não Liberar o Arquivo Texto
 * 
 * @author Thiago Tenório , Thiago Nascimento e Yara T. Souza, Hugo Amorim
 * @date 19/12/2008 , 19/08/2010
 *  
 * 
 */
public class ValidarArquivoTextoOSVisitaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Sessão
		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		//Form
		ConsultarArquivoTextoOSVisitaActionForm form = (ConsultarArquivoTextoOSVisitaActionForm) actionForm;
		
		
		String liberar = (String) httpServletRequest.getParameter("liberar");
		String descricaoSituacao = "";
		String opcao = "";
		
		@SuppressWarnings("unchecked")
		Collection<ArquivoTextoVisitaCampo> colecaoArquivoTextoOSVisita = 
						(Collection<ArquivoTextoVisitaCampo>)sessao.getAttribute("colecaoArquivoTextoOSVisita");
		
		
		if(liberar == null){
			liberar = (String) sessao.getAttribute("liberar");
		}
	
		//Situação escolhida
		if (liberar.equals("0")) {
			opcao = "NÃO LIBERAR";
		} else if (liberar.equals("1")) {
			opcao = "LIBERAR";
		} else if (liberar.equals("2")) {
			opcao = "EM CAMPO";
		}
		
		
		if (form.getIdsRegistros() != null) {
			
			Vector<Integer> v = new Vector<Integer>();
			for (int i = 0; i < form.getIdsRegistros().length; i++) {
				v.add(new Integer(form.getIdsRegistros()[i]));

			}
			
			//Filtrar apenas os Arquivos escolhidos
			colecaoArquivoTextoOSVisita = this.filtrarOSVisitas(v,colecaoArquivoTextoOSVisita);
			
			
			//3.1. Caso algum dos arquivos selecionados não tenha agente comercial informado
			if(this.validarAgenteComercial(colecaoArquivoTextoOSVisita)){
				/**
				 * 
				 * INFORMAR AGENTE COMERCIAL
				 * 
				 */
			}
			else{
				//Verificar disponíveis
				if(opcao.equals("LIBERAR")){
					if(this.validarSituacao(colecaoArquivoTextoOSVisita, "DISPONÍVEL")){
						/**
						 * 
						 * LIBERAR
						 * 
						 */
					}
					else{
						throw new ActionServletException("atencao.operacao_invalida_txt_selecionados","Liberar");
					}
				}
				else if(opcao.equals("NÃO LIBERAR")){
					if(this.validarSituacao(colecaoArquivoTextoOSVisita, "LIBERADO")){
						/**
						 * 
						 * NÃO LIBERAR
						 * 
						 */
					}
					else{
						throw new ActionServletException("atencao.operacao_invalida_txt_selecionados","Não liberar");
					}
					
				}
				
				else if(opcao.equals("EM CAMPO")){
					if(this.validarSituacao(colecaoArquivoTextoOSVisita, "FINALIZADO")){
						/**
						 * 
						 * EM CAMPO
						 * 
						 */
					}
					else{
						throw new ActionServletException("atencao.operacao_invalida_txt_selecionados","Em campo");
					}
					
				}
				
				else if(opcao.equals("FINALIZAR")){
					if(this.validarSituacao(colecaoArquivoTextoOSVisita, "EM CAMPO")){
						/**
						 * 
						 * FINALIZAR
						 * 
						 */
					}
					else{
						throw new ActionServletException("atencao.operacao_invalida_txt_selecionados","Finalizar");
					}
					
				}
				
			}
			
			
		}
		

		return retorno;

	}
	
	
	
	
	private Collection<ArquivoTextoVisitaCampo> filtrarOSVisitas(
			Vector<Integer> v,
			Collection<ArquivoTextoVisitaCampo> colecaoArquivoTextoOSVisita) {

			Collection<ArquivoTextoVisitaCampo> retorno = new ArrayList<ArquivoTextoVisitaCampo>();
			
			@SuppressWarnings("rawtypes")
			Iterator it = v.iterator();
			@SuppressWarnings("rawtypes")
			Iterator it2 = colecaoArquivoTextoOSVisita.iterator();
			
			while(it.hasNext()){
				Integer id = (Integer)it.next();
				while(it2.hasNext()){
					ArquivoTextoVisitaCampo at = (ArquivoTextoVisitaCampo)it2.next();
					if(at.getId().intValue() == id.intValue())
						retorno.add(at);
				}
			}
			
			return retorno;
	}


	private boolean validarAgenteComercial(Collection<ArquivoTextoVisitaCampo> v){
		boolean retorno = false;
		@SuppressWarnings("rawtypes")
		Iterator it = v.iterator();
		while(it.hasNext()){
			ArquivoTextoVisitaCampo at = (ArquivoTextoVisitaCampo)it.next();
			if(at.getLeiturista().getFuncionario().getNome() != null || at.getLeiturista().getCliente().getNome() != null){
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}
	
	
	private boolean validarSituacao(Collection<ArquivoTextoVisitaCampo> v,String situacao){
		boolean retorno = true;
		Iterator<ArquivoTextoVisitaCampo> it = v.iterator();
		while(it.hasNext()){
			ArquivoTextoVisitaCampo at = (ArquivoTextoVisitaCampo)it.next();
			if(!at.getSituacaoTransmissaoLeitura().getDescricaoSituacao().equals(situacao)){
				retorno = false;
				break;
			}
		}
		
		return retorno;
	}

}
