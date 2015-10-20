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
package gcom.gui.relatorio.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.localidade.FiltrarUnidadeNegocioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.localidade.RelatorioManterUnidadeNegocio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * @author Arthur Carvalho
 * @date 01/07/09
 * 
 */

public class GerarRelatorioUnidadeNegocioManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarUnidadeNegocioActionForm filtrarUnidadeNegocioActionForm = (FiltrarUnidadeNegocioActionForm) actionForm;

		FiltroUnidadeNegocio filtroUnidadeNegocio = (FiltroUnidadeNegocio) sessao
				.getAttribute("filtroUnidadeNegocio");

		// Inicio da parte que vai mandar os parametros para o relat�rio
		UnidadeNegocio unidadeNegocioParametros = new UnidadeNegocio();

		Fachada fachada = Fachada.getInstancia();
		
		//Codigo
		Integer id = null;
		if (filtrarUnidadeNegocioActionForm.getId() != null
				&& !filtrarUnidadeNegocioActionForm.getId().equals("")) {

			id = new Integer (filtrarUnidadeNegocioActionForm.getId() ) ;
		}
		
		//Descricao
		String nome = "";
		if(filtrarUnidadeNegocioActionForm.getNome() != null &&
				!filtrarUnidadeNegocioActionForm.getNome().equals("") ) {
					
			nome = filtrarUnidadeNegocioActionForm.getNome();
		}
		
		//Descricao Abreviada
		String nomeAbreviado = "";
		if(filtrarUnidadeNegocioActionForm.getNomeAbreviado() != null &&
				!filtrarUnidadeNegocioActionForm.getNomeAbreviado().equals("") ) {
					
			nomeAbreviado = filtrarUnidadeNegocioActionForm.getNomeAbreviado();
		}
		
		//CNPJ
		String cnpj = "";
		if( filtrarUnidadeNegocioActionForm.getNumeroCnpj() != null && 
				!filtrarUnidadeNegocioActionForm.getNumeroCnpj().equals("") ) {
			
			cnpj = filtrarUnidadeNegocioActionForm.getNumeroCnpj();
		}
		
		//Gerente da Unidade de Negocio
		Cliente cliente = new Cliente();
		if ( filtrarUnidadeNegocioActionForm.getIdCliente() != null &&
				!filtrarUnidadeNegocioActionForm.getIdCliente().equals("") ) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro( new ParametroSimples (FiltroCliente.ID, 
					filtrarUnidadeNegocioActionForm.getIdCliente() ) );
			
			Collection colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			if( colecaoClientes != null && !colecaoClientes.isEmpty() ) {
				cliente = (Cliente) colecaoClientes.iterator().next();
			}
			
		}
	
		//Gerencia Regional 
		GerenciaRegional gerenciaRegional = new GerenciaRegional();
		if ( filtrarUnidadeNegocioActionForm.getIdGerenciaRegional() != null && 
				!filtrarUnidadeNegocioActionForm.getIdGerenciaRegional().equals("") ) {
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, 
					filtrarUnidadeNegocioActionForm.getIdGerenciaRegional() ) ) ;
			
			Collection colecaoGerenciaRegionais = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName() );
			
			if ( colecaoGerenciaRegionais != null && !colecaoGerenciaRegionais.isEmpty() ) {
				gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegionais.iterator().next();
			}
		}

		//Indicador de Uso
		Short indicadorDeUso = null;
		if(filtrarUnidadeNegocioActionForm.getIndicadorUso()!= null && 
				!filtrarUnidadeNegocioActionForm.getIndicadorUso().equals("")){
			
			indicadorDeUso = new Short (filtrarUnidadeNegocioActionForm.getIndicadorUso() ) ;
		}
		
		
		
		// seta os parametros que ser�o mostrados no relat�rio
		unidadeNegocioParametros.setId( id ) ;
		unidadeNegocioParametros.setNome( nome );
		unidadeNegocioParametros.setNomeAbreviado( nomeAbreviado );
		unidadeNegocioParametros.setCnpj( cnpj );
		unidadeNegocioParametros.setCliente( cliente );
		unidadeNegocioParametros.setGerenciaRegional( gerenciaRegional );
		unidadeNegocioParametros.setIndicadorUso( indicadorDeUso );
		
		// Fim da parte que vai mandar os parametros para o relat�rio

		// cria uma inst�ncia da classe do relat�rio
		RelatorioManterUnidadeNegocio relatorioManterUnidadeNegocio = new RelatorioManterUnidadeNegocio (
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterUnidadeNegocio.addParametro("filtroUnidadeNegocio",
				filtroUnidadeNegocio);
		relatorioManterUnidadeNegocio.addParametro("unidadeNegocioParametros",
				unidadeNegocioParametros);

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterUnidadeNegocio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterUnidadeNegocio,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}