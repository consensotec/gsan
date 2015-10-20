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
 * Action respons�vel pela gera��o do arquivo texto de faturas agrupadas
 * 
 * @author Amelia Pessoa
 * @created 12/06/2012
 */
public class GerarArquivoFaturasAgrupadasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno e seta o mapeamento
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
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Refer�ncia das Faturas");
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
					throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "C�digo do Cliente Superior ou C�digo do Cliente Inicial e Final");
				} else {
					throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "C�digo do Cliente Inicial");
				}
			} else {
				if (form.getClienteResponsavelFinalId()==null || form.getClienteResponsavelFinalId().equals("")){
					throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "C�digo do Cliente Final");
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
					
					//Valida se o inicial � menor que o final
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
