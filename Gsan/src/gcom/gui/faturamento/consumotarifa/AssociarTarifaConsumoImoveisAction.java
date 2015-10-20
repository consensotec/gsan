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
package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.RelatorioImoveisClientesCorporativo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 07/12/2006
 */
public class AssociarTarifaConsumoImoveisAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
	 * Este caso de uso permite associar a tarifa de consumo para um ou mais
	 * imóveis.
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Imóveis
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/12/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		TarefaRelatorio tarefa = null;

		
		Fachada fachada = Fachada.getInstancia();

		AssociarTarifaConsumoImoveisActionForm associarTarifaConsumoImoveisActionForm = (AssociarTarifaConsumoImoveisActionForm) actionForm;

		Collection<Imovel> colecaoImoveis = null;
		
		String matricula = associarTarifaConsumoImoveisActionForm.getIdImovel();
			
		String tarifaAtual = associarTarifaConsumoImoveisActionForm
				.getTarifaAtual();
		
		Boolean imovelCorporativoAssociar = false;

		if (sessao.getAttribute("colecaoImoveis") != null) {

			colecaoImoveis = (Collection<Imovel>) sessao.getAttribute("colecaoImoveis");
			
			// verifica imoveis corporativos
			
			tarefa = verificaCorporativo(colecaoImoveis, actionMapping,	 associarTarifaConsumoImoveisActionForm,  httpServletRequest,
				httpServletResponse, imovelCorporativoAssociar);
			

			// atualizacao de imoveis.
		} else {

			Imovel imovel = new Imovel();

			imovel.setId(new Integer(matricula));

			ConsumoTarifa consumoTarifa = new ConsumoTarifa();

			consumoTarifa.setId(new Integer(tarifaAtual));

			imovel.setConsumoTarifa(consumoTarifa);

			colecaoImoveis = new ArrayList();

			colecaoImoveis.add(imovel);	
			
			imovelCorporativoAssociar = true;

			tarefa = verificaCorporativo(colecaoImoveis, actionMapping,	 associarTarifaConsumoImoveisActionForm,  httpServletRequest,
				httpServletResponse, imovelCorporativoAssociar);
		}
		
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();

		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(
				FiltroConsumoTarifa.ID, tarifaAtual));

		Collection colecaoConsumoTarifa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());

		ConsumoTarifa consumoTarifaAux = (ConsumoTarifa) colecaoConsumoTarifa
				.iterator().next();
		
		if ( tarefa == null ){
			montarPaginaSucesso(httpServletRequest, "Tarifa de Consumo "
					+ consumoTarifaAux.getDescricao() + " associada com sucesso.",
						"Associar Tarifa de Consumo a outro Imóvel",
						"exibirAssociarTarifaConsumoImoveisAction.do?menu=sim");
		} else {
			httpServletRequest.setAttribute("telaSucessoRelatorio",true);
			
			processarExibicaoRelatorio(tarefa, TarefaRelatorio.TIPO_PDF , httpServletRequest, 
				httpServletResponse, actionMapping);
		}
		
		return retorno;
	}

	private TarefaRelatorio verificaCorporativo(Collection<?> colecaoImoveis, ActionMapping actionMapping,
			AssociarTarifaConsumoImoveisActionForm associarTarifaConsumoImoveisActionForm, 
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Boolean imovelCorporativoAssociar) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();

		Iterator<?> imovelIterator = colecaoImoveis.iterator();
		
		Collection<Imovel> colecaoImoveisCorporativos = new ArrayList();

		Collection<Imovel> colecaoImoveisNaoCorporativos = new ArrayList();
		
		while(imovelIterator.hasNext()){					
						
			Imovel imovel = (Imovel) imovelIterator.next();
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.IMOVEL_PERFIL_CORPORATIVO,ConstantesSistema.SIM));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,imovel.getId()));
			
			Collection<Imovel> imoveis = fachada.pesquisar(filtroImovel, Imovel.class
				.getName());
			
			for(Imovel imovelCompleto : imoveis){					
					
				boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = 
		    			fachada.verificarPermissaoEspecialAtiva(
		    					PermissaoEspecial.MANTER_IMOVEL_PERFIL_CORPORATIVO,usuarioLogado);
		    		
		    		if(possuiPermissaoManterClienteResponsavelImoveisPublicos){
		    			
		    			colecaoImoveisCorporativos.add(imovelCompleto);		    			
		    			
		    		}
			}
			
			FiltroImovel filtroImovelNaoCorp = new FiltroImovel();
			filtroImovelNaoCorp.adicionarParametro(new ParametroSimples(FiltroImovel.IMOVEL_PERFIL_CORPORATIVO,ConstantesSistema.NAO));
			filtroImovelNaoCorp.adicionarParametro(new ParametroSimples(FiltroImovel.ID,imovel.getId()));
			
			Collection<Imovel> imoveisNaoCorpp = fachada.pesquisar(filtroImovelNaoCorp, Imovel.class
				.getName());

			
			for(Imovel imovelCompletoNaoCorp : imoveisNaoCorpp){	
					
					colecaoImoveisNaoCorporativos.add(imovelCompletoNaoCorp);					
				
			}
								
		}
		
		// atualizacao de imoveis.
		if(imovelCorporativoAssociar && !Util.isVazioOrNulo(colecaoImoveisCorporativos)){
			fachada.atualizarImoveisTarifaConsumo(associarTarifaConsumoImoveisActionForm.getIdImovel(), 
				associarTarifaConsumoImoveisActionForm.getTarifaAtual(),colecaoImoveisCorporativos, usuarioLogado);
		}
		
		
		fachada.atualizarImoveisTarifaConsumo(associarTarifaConsumoImoveisActionForm.getIdImovel(), 
			associarTarifaConsumoImoveisActionForm.getTarifaAtual(),colecaoImoveisNaoCorporativos, usuarioLogado);
		
		TarefaRelatorio tarefa = null;
		
		if(colecaoImoveisCorporativos.size() > 0){
			
			TarefaRelatorio relatorio = null;
			
			relatorio = new RelatorioImoveisClientesCorporativo((Usuario)
					(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
						
		
			String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			
			relatorio.addParametro("colecaoImoveisCorporativos", colecaoImoveisCorporativos);
			relatorio.addParametro("tipoRelatorio",tipoRelatorio);
			
			tarefa = relatorio;
		}
		
		return tarefa;
	}
}
