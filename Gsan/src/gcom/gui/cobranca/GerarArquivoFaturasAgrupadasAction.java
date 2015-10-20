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
package gcom.gui.cobranca;

import java.util.Collection;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela geração do arquivo texto de faturas agrupadas
 * 
 * @author Amelia Pessoa
 * @created 12/06/2012
 */
public class GerarArquivoFaturasAgrupadasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//Sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		ExibirGerarArquivoFaturasAgrupadasActionForm 
			form = (ExibirGerarArquivoFaturasAgrupadasActionForm) actionForm;
	
		//Valida dados
		validarDados(form);
		
		Integer anoMesInicio = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoInicial());
		Integer anoMesFim = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFinal());
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		 
		//Validar qnt de registros [FS0001 - Nenhum registro encontrado]
		Collection<Integer> faturas = null;
		if (form.getClienteResponsavelId()!=null && !form.getClienteResponsavelId().equals("")){
			
			//Envia para processamento
			Fachada.getInstancia().gerarArquivoTextoFaturasAgrupadas(anoMesInicio,
						anoMesFim, usuarioLogado, Integer.parseInt(form.getClienteResponsavelId()));
						
		} else {

			//Envia para processamento
			Fachada.getInstancia().gerarArquivoTextoFaturasAgrupadas(anoMesInicio,
						anoMesFim,  usuarioLogado, Integer.parseInt(form.getClienteResponsavelInicialId()), Integer.parseInt(form.getClienteResponsavelFinalId()));
			
		}

		
		montarPaginaSucesso(httpServletRequest,
				"Arquivo Texto de Faturas Agrupadas enviado para processamento", 
				"Voltar",
				"exibirGerarArquivoFaturasAgrupadasAction.do?menu=sim");
		
		return retorno;
	}

	/**
	 * Validacao dos dados de entrada para geracao do arquivo txt
	 * @param form
	 */
	private void validarDados(ExibirGerarArquivoFaturasAgrupadasActionForm form) {
		//Valida datas
		if (form.getMesAnoInicial()==null || form.getMesAnoInicial().equals("") || form.getMesAnoFinal()==null || form.getMesAnoFinal().equals("")){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Referência das Faturas");
		}
		Integer referenciaInicialFormatada = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoInicial());
		Integer referenciaFinalFormatada = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFinal());
		if (referenciaInicialFormatada.compareTo(referenciaFinalFormatada) > 0) {
			throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
		}
		
		//Valida preenchimento de clientes
		if (form.getClienteResponsavelId()==null || form.getClienteResponsavelId().equals("")){
			if (form.getClienteResponsavelInicialId()==null || form.getClienteResponsavelInicialId().equals("")){
				if (form.getClienteResponsavelFinalId()==null || form.getClienteResponsavelFinalId().equals("")){		
					throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Código do Cliente Superior ou Código do Cliente Inicial e Final");
				} else {
					throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Código do Cliente Inicial");
				}
			} else {
				if (form.getClienteResponsavelFinalId()==null || form.getClienteResponsavelFinalId().equals("")){
					throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Código do Cliente Final");
				} else {
					//Valida se clientes existem
					FiltroCliente filtro = new FiltroCliente();
					filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getClienteResponsavelInicialId()));
					Collection<Cliente> colecao = Fachada.getInstancia().pesquisar(filtro, Cliente.class.getName());
					Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecao);
					if(cliente==null){
						throw new ActionServletException("atencao.cliente_inicial_inexistente");
					}
					filtro = new FiltroCliente();
					filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getClienteResponsavelFinalId()));
					colecao = Fachada.getInstancia().pesquisar(filtro, Cliente.class.getName());
					cliente = (Cliente) Util.retonarObjetoDeColecao(colecao);
					if(cliente==null){
						throw new ActionServletException("atencao.cliente_final_inexistente");
					}
					
					//Valida se o inicial é menor que o final
					if (Integer.parseInt(form.getClienteResponsavelInicialId()) > Integer.parseInt(form.getClienteResponsavelFinalId())){
						throw new ActionServletException("atencao.cliente_final_menor_cliente_inicial");
					} 	
				}
			}
		} else {
			//Valida se cliente existe
			FiltroCliente filtro = new FiltroCliente();
			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getClienteResponsavelId()));
			Collection<Cliente> colecao = Fachada.getInstancia().pesquisar(filtro, Cliente.class.getName());
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecao);
			if(cliente==null){
				throw new ActionServletException("atencao.cliente_superior_inexistente");
			}
			
			if ((form.getClienteResponsavelInicialId()!=null && !form.getClienteResponsavelInicialId().equals("")) || 
					(form.getClienteResponsavelFinalId()!=null && !form.getClienteResponsavelFinalId().equals(""))){
				throw new ActionServletException("atencao.criterio_busca_duplicado");
			}
		}
		
	}
}
