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
package gcom.gui.cadastro.entidadebeneficente;

import gcom.cadastro.cliente.Cliente;import gcom.cadastro.cliente.FiltroCliente;import gcom.cadastro.empresa.Empresa;import gcom.cadastro.empresa.FiltroEmpresa;import gcom.fachada.Fachada;import gcom.faturamento.debito.DebitoTipo;import gcom.faturamento.debito.FiltroDebitoTipo;import gcom.gui.ActionServletException;import gcom.gui.GcomAction;import gcom.util.ConstantesSistema;import gcom.util.Util;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

/**
 * Carrega os dados necess�rios e redireciona para a p�gina que invocar� o [UC0915] Inserir Entidade Beneficente.
 * Pr�-valida algumas informa��es ao usu�rio utilizar a tecla enter para selecionar o cliente e o tipo de d�bito.
 *  
 * @author Samuel Valerio
 * @date 11/06/2009
 * @since 4.1.6.4
 *
 */
public class ExibirInserirEntidadeBeneficenteAction extends GcomAction {
	
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		 ActionForward retorno = actionMapping.findForward("inserirEntidadeBeneficente");
		 
		 Fachada fachada = Fachada.getInstancia();
		 
		 EntidadeBeneficenteActionForm form = (EntidadeBeneficenteActionForm) actionForm;
		 
		 httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.cliente.id");
		 
		 Cliente cliente = form.getEntidadeBeneficente().getCliente();
		 
		 
		 if (cliente.getId() != null) {
			 FiltroCliente filtroCliente = new FiltroCliente();
			 
			 filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, cliente.getId()));
			 filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			 
			 Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		
			// [FS0001] - Verificar exist�ncia do cliente
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				if (!new Integer(0).equals(cliente.getId()))
					cliente.setNome("CLIENTE INEXISTENTE");
				cliente.setId(null);
				httpServletRequest.setAttribute("existeCliente","exception");	
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.cliente.id");
			}else{
				
				Cliente clienteEncontrado = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
							
				// [FS0002] - Verificar se cliente � pessoa jur�dica
				fachada.validarSeClienteEhPessoaJuridica(clienteEncontrado); 
				
				form.getEntidadeBeneficente().setCliente(clienteEncontrado);
				httpServletRequest.setAttribute("nomeCampo","entidadeBeneficente.debitoTipo.id");
			}
				 
			 
		 }
		 
		DebitoTipo debitoTipo = form.getEntidadeBeneficente().getDebitoTipo();
		 
		if (debitoTipo.getId() != null) {
			
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, debitoTipo.getId()));
		
			Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
					DebitoTipo.class.getName());
			
			// [FS0003] - Verificar exist�ncia do tipo de d�bito
			if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
				if (!new Integer(0).equals(debitoTipo.getId()))
					debitoTipo.setDescricao("TIPO DE D�BITO INEXISTENTE");
				debitoTipo.setId(null);
				httpServletRequest.setAttribute("existeDebitoTipo","exception");	
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.debitoTipo.id");
			} else {
				DebitoTipo debitoTipoEncontrado = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
				
				// [FS0004] - Verificar se tipo de d�bito n�o � gerado automaticamente
				fachada.validarSeDebitoTipoNaoEhGeradoAutomaticamente(debitoTipoEncontrado);
				
				form.getEntidadeBeneficente().setDebitoTipo(debitoTipoEncontrado);
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.empresa.id");
			}
		
		} 
		 
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		 
		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());
		 
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) { 
			throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Empresa");
		}

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		 
		return retorno;
	}
	
}
