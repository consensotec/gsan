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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.PesquisarFiscalizarOSEncerradaAcompanhamentoHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] � Acompanhar Arquivos de Roteiro
 * 
 * @author Th�lio Ara�jo
 *
 * @date 15/07/2011
 */
public class AtualizarFiscalizarOSAcompanhamentoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);		

		// Precisa pegar a unidade do usuario do login que esta na sessao
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();		

				
		String metodo = httpServletRequest.getParameter("metodo");
		
		Collection<PesquisarFiscalizarOSEncerradaAcompanhamentoHelper> colecaoFiscalizarOSAcompanhamento = (Collection<PesquisarFiscalizarOSEncerradaAcompanhamentoHelper>) sessao
				.getAttribute("colecaoFiscalizarOSAcompanhamento");
		
				
		if (Util.verificarNaoVazio(metodo) && metodo.equals("concluir")){

			if (colecaoFiscalizarOSAcompanhamento != null && !colecaoFiscalizarOSAcompanhamento.isEmpty()) {
				Iterator<PesquisarFiscalizarOSEncerradaAcompanhamentoHelper> dadosFiscalizarOS = colecaoFiscalizarOSAcompanhamento.iterator();
				while (dadosFiscalizarOS.hasNext()) {
					PesquisarFiscalizarOSEncerradaAcompanhamentoHelper dadosFiscalizarOSHelper = (PesquisarFiscalizarOSEncerradaAcompanhamentoHelper) dadosFiscalizarOS
							.next();
					if (httpServletRequest.getParameter("idFiscalizarOSAcompanhamento"
							+dadosFiscalizarOSHelper.getIdRA().toString()) != null) {
						String indice = httpServletRequest
								.getParameter("idFiscalizarOSAcompanhamento"
									+ dadosFiscalizarOSHelper.getIdRA().toString());			
							
						Integer idRA = dadosFiscalizarOSHelper.getIdRA();
				
						RegistroAtendimento raReativar = fachada.pesquisarRegistroAtendimentoPorRA(idRA);					
	
						// Seta RegistroAtendimentoReativacao
						RegistroAtendimento registroAtendimentoReativacao = new RegistroAtendimento();
						registroAtendimentoReativacao.setId(idRA);
						raReativar.setRegistroAtendimentoReativacao(registroAtendimentoReativacao);
	
						Object[] dadosUnidade = fachada.pesquisarUnidadeOrganizacionalUsuario(usuario.getId());
						Integer idUnidadeOrganizacionalUsuario = (Integer) dadosUnidade[0];					
	
						Object[] dadosRegistroAtendimentoSolicitante = fachada.pesquisarDadosRASolicitante(idRA);
						Integer idRaSolicitante = (Integer) dadosRegistroAtendimentoSolicitante[5];
						
						Object[] dadosSolicitacaoTipo = fachada.pesquisarTipoSolicitacao(idRA);
						Integer idSoliticacaoTipo = (Integer) dadosSolicitacaoTipo[1];
						
						Integer idCliente = (Integer) dadosRegistroAtendimentoSolicitante[0];
				
						RegistroAtendimento registroAtendimento = new RegistroAtendimento();					
						Date dataAtual = new Date();
						registroAtendimento.setDataEncerramento(dataAtual);
						registroAtendimento.setUltimaAlteracao(dataAtual);
						registroAtendimento.setRegistroAtendimentoReativacao(registroAtendimentoReativacao);
						registroAtendimento.setId(idRA);
						AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
						if(indice.equals("1")){
							atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
							registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
							registroAtendimento.setParecerEncerramento(RegistroAtendimento.PARECER_CONFIRMADO);
						}else{
							atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.SERVICO_NAO_EXECUTADO);
							registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
							registroAtendimento.setParecerEncerramento(RegistroAtendimento.PARECER_NAO_CONFIRMADO);
						}
						
						
						RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
						registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
						registroAtendimentoUnidade.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
						registroAtendimentoUnidade.setUsuario(usuario);
						registroAtendimentoUnidade.setUltimaAlteracao(dataAtual);
						AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
						atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
						registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
						
						
						// Encerrar RA
						fachada.encerrarRegistroAtendimento(registroAtendimento, 
							registroAtendimentoUnidade,
							usuario,
							null, 
							null, 
							null, 
							null,
			                false,null,false);
						
						// Reativa RA se for marcado "n�o confirmar"
						if(indice.equals("2")){
						
				        	fachada.reativarRegistroAtendimento(raReativar, 
				        		idUnidadeOrganizacionalUsuario,
				        		usuario.getId(),
				        		idCliente,
				        		idRaSolicitante ,
				        		null ,
				        		null, 
				        		idSoliticacaoTipo);
						}
											
						Collection<Integer> colOSProgAS = fachada.pesquisarOSProgramacaoASPorIdRA(idRA);
						// Marca OS como validada
						Iterator<Integer> it = colOSProgAS.iterator();
						while(it.hasNext()){
							Integer element = it.next();
							fachada.atualizarIndicadorValidaOSProg(element);
						}	
						
					}
				}								
				
			}
		
		}
		
		montarPaginaSucesso(httpServletRequest, "Registro(s) de Atendimento(s)  "
			    + "atualizados com sucesso.", "Fiscalizar OS Acompanhamento Servico",
	            "exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?menu=sim");
	
		return retorno;
		
	}	
	
	
}