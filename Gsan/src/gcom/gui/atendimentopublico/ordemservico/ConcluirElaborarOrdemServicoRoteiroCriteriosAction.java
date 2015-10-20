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

import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConcluirElaborarOrdemServicoRoteiroCriteriosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = 
			actionMapping.findForward("telaSucesso");
		
		ElaborarOrdemServicoRoteiroCriteriosActionForm elaborarActionForm = 
			(ElaborarOrdemServicoRoteiroCriteriosActionForm) actionForm;

		String data = elaborarActionForm.getDataRoteiro();
		Date dataRoteiro = Util.converteStringParaDate(data);
		
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		
		Collection colecao = mapOsProgramacaoHelper.values();
		Collection colecaoNova = this.removeOrdemServicoProgramacaoJaExistente(colecao,dataRoteiro);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada.getInstancia().elaborarRoteiro(colecaoNova, usuario);
		
		//Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Elabora��o do Roteiro de Programa��o de Ordens de Servi�os efetuado com sucesso",
			"Efetuar outra Elabora��o do Roteiro",
			"exibirElaborarOrdemServicoRoteiroCriteriosAction.do?menu=sim&dataRoteiro="+elaborarActionForm.getDataRoteiro());
		
		return retorno;
	}
	
	/**
	 * Remove as ordem de servico programacao que j� existe no banco de dados
	 * so existe porque possui id
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 *
	 * @param colecao de OS ProgramacaoHelper
	 * @return Collection<OSProgramacaoHelper>
	 */
	
	private Collection<OSProgramacaoHelper> removeOrdemServicoProgramacaoJaExistente(Collection colecao,Date dataRoteiroElaboracao){
		Collection<OSProgramacaoHelper> retorno = new ArrayList();
		
		Iterator itera = colecao.iterator();
		
		while (itera.hasNext()) {
			
			Collection colecaoOSProgramacaoHelper = (Collection) itera.next();

			Iterator iteraOsProgramacao = colecaoOSProgramacaoHelper.iterator();
			
			short sequencial = 0;
			
			while (iteraOsProgramacao.hasNext()) {

				OSProgramacaoHelper osProgramacaoHelper = (OSProgramacaoHelper) iteraOsProgramacao.next();
				
				OrdemServicoProgramacao ordemServicoProgramacao = 
					osProgramacaoHelper.getOrdemServicoProgramacao();
				
				if(ordemServicoProgramacao.getId() == null || 
					ordemServicoProgramacao.getId().intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
					
					Date dataRoteiro = ordemServicoProgramacao.getProgramacaoRoteiro().getDataRoteiro();
					
					//Caso tenha data diferentes � necessario incluir 2 programac�es: 
					if(Util.compararData(dataRoteiroElaboracao,dataRoteiro) != 0){
						
						short sequencialAnterior = ordemServicoProgramacao.getNnSequencialProgramacao();
						
						sequencial++;
						
						// 1.Ordem de servi�o programa��o com a data final da programa��o
						ordemServicoProgramacao.setNnSequencialProgramacao(sequencial);
						retorno.add(osProgramacaoHelper);
						
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(dataRoteiroElaboracao);

						//Vai adcionando programa��o at� a data Final de Programacao
						//Ex: dataRoteiroElaboracao = 10/01 e dataRoteiro(DataFinalProgramacao) = 12/01
						//Ex: 10/01 at� 12/01
						while(Util.compararData(calendar.getTime(),dataRoteiro) == -1){
							
							// 2.Ordem de servi�o programa��o com a data do roteiro da elabora��o
							ProgramacaoRoteiro programacaoRoteiro = 
								new ProgramacaoRoteiro(
									ordemServicoProgramacao.getProgramacaoRoteiro().getDataRoteiro(),
									ordemServicoProgramacao.getProgramacaoRoteiro().getUltimaAlteracao(),
									ordemServicoProgramacao.getProgramacaoRoteiro().getUnidadeOrganizacional() );
							
							programacaoRoteiro.setDataRoteiro(calendar.getTime());

							OSProgramacaoHelper helper = new OSProgramacaoHelper();

							OrdemServicoProgramacao ordemServicoProgramacaoNova = 
								new OrdemServicoProgramacao(
									ordemServicoProgramacao.getId(),
									sequencialAnterior,
									ordemServicoProgramacao.getIndicadorAtivo(),
									ordemServicoProgramacao.getIndicadorEquipePrincipal(),
									ordemServicoProgramacao.getUltimaAlteracao(),
									ordemServicoProgramacao.getEquipe(),
									ordemServicoProgramacao.getUsuarioProgramacao(),
									ordemServicoProgramacao.getUsuarioFechamento(),
									programacaoRoteiro,
									ordemServicoProgramacao.getOsProgramNaoEncerMotivo(),
									ordemServicoProgramacao.getOrdemServico() );

							helper.setOrdemServicoProgramacao(ordemServicoProgramacaoNova);
							retorno.add(helper);
							
							//Adciona um dia a mais na data
							calendar.add(Calendar.DAY_OF_MONTH,1);
						}

					}else{
						retorno.add(osProgramacaoHelper);
					}
					

				}
				
			}
			
		}
		return retorno;
	}
}