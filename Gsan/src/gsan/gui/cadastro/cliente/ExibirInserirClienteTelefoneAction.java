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
package gsan.gui.cadastro.cliente;

import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.cliente.FiltroFoneTipo;
import gsan.cadastro.cliente.FoneTipo;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Luis Octavio
 */
public class ExibirInserirClienteTelefoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da A��o
		ActionForward retorno = actionMapping
				.findForward("inserirClienteTelefone");

		// Cria a sess�o
		HttpSession session = httpServletRequest.getSession(false);

		// Obt�m o action form
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		// Obtendo o idMuncipio corrente
		String idMunicipio = ((String) clienteActionForm.get("idMunicipio"));

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoClienteFone = (Collection) session
				.getAttribute("colecaoClienteFone");

		if (colecaoClienteFone == null) {
			colecaoClienteFone = new ArrayList();
		}

		// Pega a refer�ncia do Gerenciador de P�ginas
		// GerenciadorPaginas gerenciadorPaginas = null;

		// Inicializa a cole��o de FoneTipo
		Collection foneTipos = null;
		ClienteFone clienteFone = null;
		Collection municipios = null;

		// Filtro de tipos de telefone
		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();

		filtroFoneTipo.adicionarParametro(new ParametroSimples(
				FiltroFoneTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		// S� vai mandar fazer a pesquisa do munic�pio se o usu�rio apertou o
		// enter e n�o o bot�o "adicionar"
		if (clienteActionForm.get("botaoClicado") == null
				|| clienteActionForm.get("botaoClicado").equals("")) {

			if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			
				
				// Adiciona Parametro para pesquisar municipio
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idMunicipio));
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				// Recebe a cole��o de munic�pio conforme o idMuncipio informado
				municipios = fachada.pesquisar(filtroMunicipio, Municipio.class
						.getName());

				// Verifica se o retorno da pesquisa trouxe resultados
				if (municipios != null && !municipios.isEmpty()) {

					// Prepara o iterator para percorrer a cole��o resultante da
					// pesquisa
					Iterator iteratorMunicipio = municipios.iterator();

					// Posiciona-se no primeiro registro
					Municipio municipio = (Municipio) iteratorMunicipio.next();

					// Passa pelo request o municipio encontrado na pesquisa
					clienteActionForm
							.set("idMunicipio", "" + municipio.getId());
					clienteActionForm.set("descricaoMunicipio", municipio
							.getNome());
					clienteActionForm.set("ddd", "" + municipio.getDdd());
					
					// Manda para a p�gina a informa��o do campo para que seja
					// focado no retorno da pesquisa
					httpServletRequest.setAttribute("nomeCampo",
							"telefone");

				} else {
					httpServletRequest.setAttribute("municipioNaoEncontrado",
							"true");
					clienteActionForm.set("idMunicipio", "");
					clienteActionForm.set("descricaoMunicipio",
							"Munic�pio Inexistente");
					clienteActionForm.set("ddd", "");
					
					// Manda para a p�gina a informa��o do campo para que seja
					// focado no retorno da pesquisa
					httpServletRequest.setAttribute("nomeCampo",
							"idMunicipio");


				}
			}
		}

		// Realiza a pesquisa de tipos de telefone
		foneTipos = fachada.pesquisar(filtroFoneTipo, FoneTipo.class.getName());

		if (foneTipos == null || foneTipos.isEmpty()) {
			// Nenhum tipo de telefone cadastrado
			new ActionServletException("erro.naocadastrado", null,
					"tipo de telefone");

		} else {
			// Envia os objetos no request
			session.setAttribute("foneTipos", foneTipos);
		}

		municipios = fachada.pesquisar(filtroMunicipio, Municipio.class
				.getName());

		// Envia para sessao a colecao de municipios
		session.setAttribute("municipios", municipios);

		if (clienteActionForm.get("botaoClicado") != null
				&& !clienteActionForm.get("botaoClicado").equals("")) {
			if ((clienteActionForm.get("botaoClicado").equals("ADICIONAR"))
					&& (clienteActionForm.get("ddd") != null && !((String) clienteActionForm
							.get("ddd")).trim().equalsIgnoreCase(""))
					&& (clienteActionForm.get("idTipoTelefone") != null && !((String) clienteActionForm
							.get("idTipoTelefone")).trim().equalsIgnoreCase(""))
					&& (clienteActionForm.get("telefone") != null && !((String) clienteActionForm
							.get("telefone")).trim().equalsIgnoreCase(""))) {

				// Verificar se o usu�rio digitou um DDD que existe no sistema
				filtroMunicipio.limparListaParametros();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.DDD, (String) clienteActionForm
								.get("ddd")));

				Collection municipiosComDDDValido = fachada.pesquisar(
						filtroMunicipio, Municipio.class.getName());

				if (municipiosComDDDValido.isEmpty()) {

					clienteActionForm.set("ddd", "");
					clienteActionForm.set("botaoAdicionar", null);
					clienteActionForm.set("botaoClicado", null);

					// O DDD n�o existe no sistema
					throw new ActionServletException(
							"atencao.telefone.ddd.nao_existente");
				}

				clienteFone = new ClienteFone();
				clienteFone.setDdd((String) clienteActionForm.get("ddd"));
				clienteFone.setTelefone((String) clienteActionForm
						.get("telefone"));

				if (clienteActionForm.get("ramal") != null
						&& !((String) clienteActionForm.get("ramal")).trim()
								.equalsIgnoreCase("")) {
					clienteFone.setRamal((String) clienteActionForm
							.get("ramal"));
				}
				
				if (clienteActionForm.get("contato") != null
						&& !((String) clienteActionForm.get("contato")).trim()
								.equalsIgnoreCase("")) {
					clienteFone.setContato((String) clienteActionForm
							.get("contato"));
				}

				FoneTipo foneTipo = new FoneTipo();

				foneTipo.setId(new Integer(clienteActionForm.get(
						"idTipoTelefone").toString()));
				foneTipo.setDescricao(clienteActionForm.get("textoSelecionado")
						.toString());

				clienteFone.setFoneTipo(foneTipo);
				clienteFone.setIndicadorTelefoneSMS(null);
				clienteFone.setUltimaAlteracao(new Date());

				// Verifica se o telefone j� existe na cole��o
				if (!colecaoClienteFone.contains(clienteFone)) 
				{
					colecaoClienteFone.add(clienteFone);
				}
				else
				{
					httpServletRequest.setAttribute("telefoneJaExistente", true);
				}

				clienteActionForm.set("botaoAdicionar", null);
				clienteActionForm.set("botaoClicado", null);
				clienteActionForm.set("ddd", null);
				clienteActionForm.set("telefone", null);
				clienteActionForm.set("idTipoTelefone", null);
				clienteActionForm.set("idMunicipio", null);
				clienteActionForm.set("ramal", null);
				clienteActionForm.set("contato", null);
				clienteActionForm.set("descricaoMunicipio", null);

			}
		}

		session.setAttribute("colecaoClienteFone", colecaoClienteFone);

		// Limpa a indica��o que o bot�o adicionar foi clicado
		clienteActionForm.set("botaoClicado", "");

		// Se a cole��o de telefones tiver apenas um item, ent�o este item tem
		// que estar selecionado
		// como telefone principal
		Iterator iterator = colecaoClienteFone.iterator();

		if (colecaoClienteFone != null && colecaoClienteFone.size() == 1) {

			clienteFone = (ClienteFone) iterator.next();

			clienteActionForm.set("indicadorTelefonePadrao", new Long(
					obterTimestampIdObjeto(clienteFone)));

		}
		
		//**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 02/07/2009
		// Verifica se a tela deve ser exibida como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("POPUP") != null) {
			if (httpServletRequest.getParameter("POPUP").equals("true")) {
				session.setAttribute("POPUP", "true");
			}else {
				session.setAttribute("POPUP", "false");
			}
		}else if (session.getAttribute("POPUP") == null) {
			session.setAttribute("POPUP", "false");
		}
		//**********************************************************************

		return retorno;
	}

}
