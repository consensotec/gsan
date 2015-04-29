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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.imovel.Categoria;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 30 de Dezembro de 2005
 */
public class AtualizarCategoriaAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CategoriaActionForm categoriaActionForm = (CategoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CATEGORIA_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CATEGORIA_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a

		Categoria categoria = (Categoria) sessao.getAttribute("categoria");

		// Pegando os dados do Formul�rio
		String descricao = (String) categoriaActionForm.getDescricao();
		String descricaoAbreviada = (String) categoriaActionForm
				.getDescricaoAbreviada();
		Integer codigoCategoria = new Integer(categoriaActionForm
				.getIdCategoria());
		Integer consumoMinimo = new Integer(categoriaActionForm
				.getConsumoMinimo());
		Integer consumoEstouro = new Integer(categoriaActionForm
				.getConsumoEstouro());
		BigDecimal vezesMediaEstouro = new BigDecimal(categoriaActionForm
				.getVezesMediaEstouro().replace(",", "."));
		Integer mediaBaixoConsumo = new Integer(categoriaActionForm
				.getMediaBaixoConsumo());
		BigDecimal porcentagemMediaBaixoConsumo = new BigDecimal(
				categoriaActionForm.getPorcentagemMediaBaixoConsumo().replace(
						",", "."));
		Integer consumoAlto = new Integer(categoriaActionForm.getConsumoAlto());
		BigDecimal vezesMediaAltoConsumo = new BigDecimal(categoriaActionForm
				.getVezesMediaAltoConsumo().replace(",", "."));
		Integer limiteConsumo = null;
		if(categoriaActionForm.getLimiteConsumo() != null && !categoriaActionForm
				.getLimiteConsumo().equals("")){
			limiteConsumo = new Integer(categoriaActionForm.getLimiteConsumo());
		}
		
		BigDecimal fatorLimiteConsumo = null;
		if(categoriaActionForm.getLimiteConsumo() != null && !categoriaActionForm
				.getLimiteConsumo().equals("")){
			fatorLimiteConsumo = new BigDecimal(categoriaActionForm
					.getFatorLimiteConsumo().replace(",", "."));
		}
		
		Integer verificaIndicadorTarifaCategoria = null;
		
		if(categoriaActionForm.getVerificaIndicadorTarifaCategoria() != null && !categoriaActionForm
				.getVerificaIndicadorTarifaCategoria().equals("")){
			verificaIndicadorTarifaCategoria = new Integer(categoriaActionForm.getVerificaIndicadorTarifaCategoria());
		}
		
		Short indicadorDeUso = new Short(categoriaActionForm.getIndicadorUso());

		// Seta os campos para serem atualizados
		categoria.setId(codigoCategoria);
		categoria.setDescricao(descricao);
		categoria.setDescricaoAbreviada(descricaoAbreviada);
		categoria.setConsumoMinimo(consumoMinimo);
		categoria.setConsumoEstouro(consumoEstouro);
		categoria.setVezesMediaEstouro(vezesMediaEstouro);
		categoria.setMediaBaixoConsumo(mediaBaixoConsumo);
		categoria.setPorcentagemMediaBaixoConsumo(porcentagemMediaBaixoConsumo);
		categoria.setVezesMediaAltoConsumo(vezesMediaAltoConsumo);
		categoria.setConsumoAlto(consumoAlto);
		categoria.setFatorLimiteConsumo(fatorLimiteConsumo);
		categoria.setLimiteConsumo(limiteConsumo);
		categoria.setIndicadorUso(indicadorDeUso);
	


		// ------------ REGISTRAR TRANSA��O ----------------
		categoria.setOperacaoEfetuada(operacaoEfetuada);
		categoria.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(categoria);
		// ------------ REGISTRAR TRANSA��O ----------------

		// Atualiza a Categoria - As valida��es est�o no Controlador
		fachada.atualizarCategoria(categoria);

		montarPaginaSucesso(httpServletRequest, "Categoria de c�digo "
				+ categoria.getId() + " atualizada com sucesso.",
				"Realizar outra Manuten��o de Categoria",
				"exibirManterCategoriaAction.do?menu=sim");
		return retorno;
	}
}