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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para atualiza��o da instala��o do hidr�metro.
 * 
 * @author lms
 * @date 24/07/2006
 */
public class AtualizarInstalacaoHidrometroAction extends GcomAction {

	/**
	 * Este caso de uso permite efetuar a atualiza��o dos atributos da
	 * instala��o do hidr�metro
	 * 
	 * [UC0368] Atualizar Instala��o de Hidr�metro
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarInstalacaoHidrometroActionForm form = (AtualizarInstalacaoHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		Imovel imovel = null;

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		/*
		 * [UC0107] Registrar Transa��o
		 * 
		 */
//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_INSTALACAO_HIDROMETRO_ATUALIZAR,
//				new UsuarioAcaoUsuarioHelper(usuario,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_INSTALACAO_HIDROMETRO_ATUALIZAR);
//
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);
		// [UC0107] -Fim- Registrar Transa��o

		if (form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("")) {

			Integer idOrdemServico = Util.converterStringParaInteger(form.getIdOrdemServico());

			OrdemServico ordemServico = fachada.recuperaOSPorId(idOrdemServico);

			// Valida a ordem de servi�o
			fachada.validarExibirAtualizarInstalacaoHidrometro(ordemServico,false);
			
			if (ordemServico.getRegistroAtendimento() != null){
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			} else {
				imovel = ordemServico.getImovel();
			}
			

		} else {
			String idImovel = form.getIdImovel();

			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			form.setMatriculaImovel(idImovel);

			form.setInscricaoImovel(inscricaoImovelEncontrado);

			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);

			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");

			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico");

			Collection colecaoImovel = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			imovel = (Imovel) colecaoImovel.iterator().next();

		}

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

		Integer medicaoTipo = Integer.parseInt(form.getMedicaoTipo());
		
		if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {
			hidrometroInstalacaoHistorico = imovel.getLigacaoAgua()
					.getHidrometroInstalacaoHistorico();
		} 
		// Po�o ou Liga��o de Esgoto
		else if (MedicaoTipo.POCO.equals(medicaoTipo) || medicaoTipo.equals(3)) {
			hidrometroInstalacaoHistorico = imovel
					.getHidrometroInstalacaoHistorico();
			
			if (form.getTipoPoco() != null && 
				!form.getTipoPoco().trim().equals("") && 
				!form.getTipoPoco().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				PocoTipo pocoTipo = new PocoTipo();
				pocoTipo.setId(new Integer(form.getTipoPoco()));
				
				imovel.setPocoTipo(pocoTipo);
			}
		}

		// Valida a exist�ncia do hidr�metro
		fachada.validarExistenciaHidrometro(imovel, medicaoTipo);

		// Atualiza a entidade com os valores do formul�rio
		form.setFormValues(hidrometroInstalacaoHistorico);

		// Informa que o usu�rio que fez a instala��o � o usu�rio logado
		hidrometroInstalacaoHistorico.setUsuarioInstalacao((Usuario) sessao
				.getAttribute("usuarioLogado"));

		// Atualiza a base de dados com as altera��es da instala��o hidr�metro
		fachada.atualizarInstalacaoHidrometro(imovel, medicaoTipo, usuario);

		// Setando Opera��o
//		hidrometroInstalacaoHistorico.setOperacaoEfetuada(operacaoEfetuada);
//		hidrometroInstalacaoHistorico.adicionarUsuario(usuario,
//				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		// Exibe a p�gina de sucesso

		montarPaginaSucesso(httpServletRequest,
				"Atualiza��o do Hist�rico da Instala��o de Hidr�metro do im�vel "
						+ imovel.getId() + " efetuada com sucesso.",
				"Efetuar nova Atualiza��o do Hist�rico da Instala��o de Hidr�metro",
				"exibirAtualizarInstalacaoHidrometroAction.do?menu=sim");

		// Exibe a p�gina de sucesso
		/*
		 * montarPaginaSucesso(httpServletRequest, "Atualiza��o do Hist�rico da
		 * Instala��o de Hidr�metro do im�vel " + imovel.getId() + " efetuada
		 * com sucesso.", "Efetuar Atualiza��o do Hist�rico da Instala��o de
		 * Hidr�metro", "exibirEfetuarLigacaoEsgotoAction.do?menu=sim",
		 * "exibirAtualizarInstalacaoHidrometroAction.do");
		 */

		return retorno;
	}

}