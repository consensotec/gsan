/*
 * Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
 * Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gsan.gui.micromedicao;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.micromedicao.FiltroItemServico;
import gsan.micromedicao.ItemServico;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirItemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirItemServicoActionForm form = (InserirItemServicoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String codigoConstanteCalculo = form.getCodigoConstanteCalculo();
		String codigoItem = form.getCodigoItem();


		if(descricao == null || descricao.equals("")){

			//Descri��o n�o informada
			throw new ActionServletException("atencao.descricao_sistema_abastecimento_nao_informado");
		} else{
			//[FS0003] - Verificar descri��o do item j� informado.
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			filtroItemServico.adicionarParametro(
					new ParametroSimples(FiltroItemServico.DESCRICAO, descricao));

			Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
					filtroItemServico, ItemServico.class.getName());
			if ( colecaoItemServico != null  && !colecaoItemServico.isEmpty()) {
				throw new ActionServletException("atencao.descricao_existente",null,descricao);	        	
			}


		}
		if(descricaoAbreviada == null || descricaoAbreviada.equals("")){

			//Descri��o Abreviada n�o informada
			throw new ActionServletException("atencao.descricao_abreviada_nao_informada");
			//[FS0004] - Verificar descri��o abreviada do item j� informado.
		} else {
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			filtroItemServico.adicionarParametro(
					new ParametroSimples(FiltroItemServico.DESCRICAO_ABREVIADA, descricaoAbreviada));

			Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
					filtroItemServico, ItemServico.class.getName());
			if ( colecaoItemServico != null && !colecaoItemServico.isEmpty() ) {
				throw new ActionServletException("atencao.descricao_abreviada_tipo_debito_ja_existente",null,descricaoAbreviada);	        	
			}
		} 

		//[FS0005] - Verificar constante de c�lculo j� informado
		if(codigoConstanteCalculo != null && !codigoConstanteCalculo.equals("")){
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			filtroItemServico.adicionarParametro(
					new ParametroSimples(FiltroItemServico.CODIGO_CONSTANTE_CALCULO, new Integer (codigoConstanteCalculo)));

			Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
					filtroItemServico, ItemServico.class.getName());
			if ( colecaoItemServico != null && !colecaoItemServico.isEmpty() ) {
				throw new ActionServletException("atencao.codigo_constante_calculo_existente",null,form.getCodigoConstanteCalculo());	        	
			}
		}  

		//[FS0006] - Verificar c�digo do item j� informado.
		if(codigoItem != null && !codigoItem.equals("")){
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			filtroItemServico.adicionarParametro(
					new ParametroSimples(FiltroItemServico.CODIGO_ITEM, new Long (codigoItem)));

			Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
					filtroItemServico, ItemServico.class.getName());
			if ( colecaoItemServico != null && !colecaoItemServico.isEmpty()) {
				throw new ActionServletException("atencao.codigo_existente",null,form.getCodigoItem());	        	
			}
		} 

		//Criar o objeto itemServico que ser� inserido na base
		ItemServico itemServico = new ItemServico();


		itemServico.setDescricao(descricao);
		itemServico.setDescricaoAbreviada(descricaoAbreviada);
		itemServico.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		if (codigoConstanteCalculo != null && !codigoConstanteCalculo.equals("")){
			itemServico.setCodigoConstanteCalculo(new Integer (codigoConstanteCalculo));
		}
		itemServico.setUltimaAlteracao(new Date());
		if (codigoItem != null && !codigoItem.equals("")){
			itemServico.setCodigoItem(new Long (codigoItem));
		}

		//------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_ITEM_SERVICO, itemServico.getId(), itemServico.getId(),
				new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(itemServico);
		//------------ REGISTRAR TRANSA��O ----------------

		Integer codigoItemServicoInserido = 
			(Integer) this.getFachada().inserir(itemServico);

		montarPaginaSucesso(httpServletRequest,
				"Item de Contrato - "  + itemServico.getDescricao().toUpperCase() 
				+ " inserido com sucesso.",
				"Inserir outro Item de Contrato",
				"exibirInserirItemServicoAction.do?menu=sim",
				"exibirAtualizarItemServicoAction.do?menu=sim&idRegistroAtualizacao=" + 
				codigoItemServicoInserido, "Atualizar Item de Contrato Inserido");



		//devolve o mapeamento de retorno
		return retorno;
	}

}