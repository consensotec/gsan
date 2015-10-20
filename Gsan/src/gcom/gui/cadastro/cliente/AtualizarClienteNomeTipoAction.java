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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class AtualizarClienteNomeTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		// Verifica se o usu�rio escolheu algum tipo de pessoa para que seja
		// mostrada a p�gina de pessoa f�sica ou de jur�dica, se nada estiver 
		// especificado a p�gina selecionada ser� a de pessoa f�sica
		
		Short tipoPessoa = (Short)  clienteActionForm.get("tipoPessoa");
		String tipoPessoaForm =  tipoPessoa.toString() ; 
		Short tipoPessoaAntes = (Short) clienteActionForm.get("tipoPessoaAntes");
        String nome = clienteActionForm.get("nome").toString();
		
		// Verifica qual � o pr�ximo passo para a execu��o do processo
		// String destinoPagina = httpServletRequest.getParameter("destino");
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

		filtroClienteTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.ID, tipoPessoaForm));
		Collection colecaoClienteTipo = fachada.pesquisar(filtroClienteTipo,
				ClienteTipo.class.getName());
		ClienteTipo clienteTipo = (ClienteTipo) colecaoClienteTipo.iterator().next();
		
		Short tipoCliente = clienteTipo 
				.getIndicadorPessoaFisicaJuridica();
		
		// Pega os dados do formul�rio
		String cpf = (String) clienteActionForm.get("cpf");
		String rg = (String) clienteActionForm.get("rg");
		String dataEmissao = (String) clienteActionForm.get("dataEmissao");
		Integer idOrgaoExpedidor = (Integer) clienteActionForm.get("idOrgaoExpedidor");
		Integer idUnidadeFederacao = (Integer) clienteActionForm.get("idUnidadeFederacao");
		String dataNascimento = (String) clienteActionForm.get("dataNascimento");
		Integer idProfissao = (Integer) clienteActionForm.get("idProfissao");
		Integer idPessoaSexo = (Integer) clienteActionForm.get("idPessoaSexo");
		String cnpj = (String) clienteActionForm.get("cnpj");
		Integer idRamoAtividade = (Integer) clienteActionForm.get("idRamoAtividade");
		String codigoClienteResponsavel = (String) clienteActionForm.get("codigoClienteResponsavel");
		

		/**
		 * Autor: Mariana Victor
		 * Data:  04/01/2010
		 * RM_3320 - [FS0011] Verificar Nome de Cliente com menos de 10 posi��es
		 */
		if (this.getSistemaParametro().getIndicadorNomeMenorDez().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOMES_COM_MENOS_DE_10_CARACTERES));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				String nomeFormatado= nome.replaceAll(" ", "");
				if (nomeFormatado.length() < 10) {
					throw new ActionServletException("atencao.nome.sobrenome.cliente.menos.dez.posicoes",
							null, nome);
				}
			}
			
		}

		/**
		 * Autor: Mariana Victor
		 * Data:  04/01/2010
		 * RM_3320 - [FS0012] Verificar Nome de Cliente com Descri��o Gen�rica
		 */
		if (this.getSistemaParametro().getIndicadorNomeClienteGenerico().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOME_CLIENTE_GENERICO));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
				Collection colecaoDescricaoGenerica = fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
				
				if (colecaoDescricaoGenerica != null || !colecaoDescricaoGenerica.isEmpty()) {
					String nomeFormatado= nome.replaceAll(" ", "");
					Iterator iteratorDescricaoGenerica = colecaoDescricaoGenerica.iterator();
					
					while (iteratorDescricaoGenerica.hasNext()) {
						DescricaoGenerica descricaoGenerica = (DescricaoGenerica) iteratorDescricaoGenerica.next();
						String nomeGenerico = descricaoGenerica.getNomeGenerico();
						String nomeGenericoFormatado = nomeGenerico.replaceAll(" ", "");
						
						if (nomeGenerico.equalsIgnoreCase(nome)
								|| nomeGenericoFormatado.equalsIgnoreCase(nome)
								|| nomeGenerico.equalsIgnoreCase(nomeFormatado)
								|| nomeGenericoFormatado.equalsIgnoreCase(nomeFormatado)) {
							throw new ActionServletException("atencao.nome.cliente.descricao.generica",
									null, "Nome do Cliente");		
						}
					}
					
				}
				
			}
			
		}
		
		// Verifica o destino porque se o usu�rio tentar concluir o processo
		// nesta p�gina, n�o � necess�rio verificar a tela de confirma��o
		if (!tipoPessoa.equals(tipoPessoaAntes)) {
			if (tipoCliente != null
					&& tipoCliente.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA) ) {
	
				clienteActionForm.set("indicadorPessoaFisicaJuridica", ""
						+ ClienteTipo.INDICADOR_PESSOA_JURIDICA);
	
				if ((idPessoaSexo != null && idPessoaSexo != ConstantesSistema.NUMERO_NAO_INFORMADO)
						|| (cpf != null && !cpf.trim().equalsIgnoreCase(""))
						|| (rg != null && !rg.trim().equalsIgnoreCase(""))
						|| (dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase(""))
						|| (dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase(""))
						|| (idOrgaoExpedidor != null && idOrgaoExpedidor != ConstantesSistema.NUMERO_NAO_INFORMADO)
						|| (idUnidadeFederacao != null && idUnidadeFederacao != ConstantesSistema.NUMERO_NAO_INFORMADO)
						|| (idProfissao != null && idProfissao != ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					return montarPaginaConfirmacaoWizard(
						"confirmacao.processo.cliente.dependencia.pessoa_juridica",
							httpServletRequest, actionMapping);
				}
			} else if (tipoCliente != null
					&& tipoCliente.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
				// Vai para Pessoa Fisica mas tem dados existentes em pessoa juridica
				clienteActionForm.set("indicadorPessoaFisicaJuridica", ""
						+ ClienteTipo.INDICADOR_PESSOA_FISICA);
	
				
	
				if ((cnpj != null && !cnpj.trim().equalsIgnoreCase(""))
						|| (idRamoAtividade != null && idRamoAtividade != ConstantesSistema.NUMERO_NAO_INFORMADO)
						|| (codigoClienteResponsavel != null && !codigoClienteResponsavel
								.trim().equalsIgnoreCase(""))) {
					return montarPaginaConfirmacaoWizard(
						"confirmacao.processo.cliente.dependencia.pessoa_fisica",
							httpServletRequest, actionMapping);
				}
			}
			clienteActionForm.set("tipoPessoaAntes", tipoPessoa);
		}		

		
		return retorno;
	}
}