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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author S�vio Luiz
 * @created 06 de Setembro de 2007
 */
public class EfetuarLigacaoEsgotoSemRAAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoEsgotoSemRAActionForm efetuarLigacaoEsgotoSemRAActionForm = (EfetuarLigacaoEsgotoSemRAActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();

		String matriculaImovel = efetuarLigacaoEsgotoSemRAActionForm
				.getMatriculaImovel();

		String materialLigacao = efetuarLigacaoEsgotoSemRAActionForm
				.getMaterialLigacao();
		String perfilLigacao = efetuarLigacaoEsgotoSemRAActionForm
				.getPerfilLigacao();
		String percentual = efetuarLigacaoEsgotoSemRAActionForm
				.getPercentualColeta().toString().replace(",", ".");
		String percentualEsgoto = efetuarLigacaoEsgotoSemRAActionForm
				.getPercentualEsgoto().toString().replace(",", ".");
		String indicadorCaixaGordura = efetuarLigacaoEsgotoSemRAActionForm
				.getIndicadorCaixaGordura();
		String dataLigacao = efetuarLigacaoEsgotoSemRAActionForm
				.getDataLigacao();

		if (matriculaImovel != null && !matriculaImovel.equals("")) {

			Imovel imovel = new Imovel();
			imovel.setId(new Integer(matriculaImovel));

			ligacaoEsgoto.setImovel(imovel);

			/*---------------------  In�cioDados da Liga��o Esgoto  ------------------------*/
			// lesg_iccaixagordura
			ligacaoEsgoto.setIndicadorCaixaGordura(new Short(
					indicadorCaixaGordura));
			// lagu_tultimaalteracao
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			// lest_id
			ligacaoEsgoto.setId(imovel.getId());
			// LEST_ID
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			// ligacaoEsgoto.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

			String diametroLigacao = efetuarLigacaoEsgotoSemRAActionForm
					.getDiametroLigacao();
			if (diametroLigacao != null
					&& !diametroLigacao.equals("")
					&& !diametroLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
				ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
				ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Diametro da Liga��o");
			}

			if (materialLigacao != null
					&& !materialLigacao.equals("")
					&& !materialLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
				ligacaoEsgotoMaterialMaterial
						.setId(new Integer(materialLigacao));
				ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Material da Liga��o");
			}

			if (perfilLigacao != null && !perfilLigacao.equals("") && 
				!perfilLigacao.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
				FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
				filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoPerfil.ID, 
						new Integer(perfilLigacao)));
				
				Collection colecaoLigacaoEsgotoPerfil = fachada.pesquisar(filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class.getName());
				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoPerfil);
				ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			} 
			else {
				
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Perfil da Liga��o");
			}
			
			//item 4.5 - [FS006] caso 1,3
			if (percentual != null && !percentual.equals("")) {

				BigDecimal percentualInformadoColeta = Util.formatarStringParaBigDecimal(percentual);
				
				if (percentualInformadoColeta != null &&
					percentualInformadoColeta.compareTo(ligacaoEsgoto.getLigacaoEsgotoPerfil().getPercentualMaximoColeta()) <= 0) {
					
					ligacaoEsgoto.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
				}
				else{
					
					throw new ActionServletException("atencao.atualizar_percentual_informado_maior_maximo_permitido", null, 
							Util.formatarBigDecimalComPonto(ligacaoEsgoto.getLigacaoEsgotoPerfil().getPercentualMaximoColeta()) + "%");
				}
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Percentual de Coleta");
			}

			if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

				BigDecimal percentualEsgotoColeta = new BigDecimal(
						percentualEsgoto);
				ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
			}

			if (dataLigacao != null && !dataLigacao.equals("")) {
				ligacaoEsgoto.setDataLigacao(Util
						.converteStringParaDate(dataLigacao));
				if (ligacaoEsgoto.getDataLigacao().after(new Date())) {
					throw new ActionServletException(
							"atencao.data_menor_que_atual", null, "Liga��o");
				}
			}

			/*
			 * [UC0107] Registrar Transa��o
			 * 
			 */

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_LIGACAO_ESGOTO__SEM_RA_EFETUAR,
					imovel.getId(), imovel.getId(),
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			// [UC0107] -Fim- Registrar Transa��o
			
			registradorOperacao.registrarOperacao(ligacaoEsgoto);
			
			fachada.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(imovel,
					ligacaoEsgotoSituacao);

			FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgoto.ID, imovel.getId()));
			Collection colecaoLigacaoEsgotoBase = fachada.pesquisar(
					filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());

			if (colecaoLigacaoEsgotoBase != null
					&& !colecaoLigacaoEsgotoBase.isEmpty()) {
				fachada.atualizar(ligacaoEsgoto);
			} else {
				fachada.inserir(ligacaoEsgoto);
			}

			montarPaginaSucesso(httpServletRequest,
					"Liga��o de Esgoto sem RA efetuada com Sucesso",
					"Efetuar outra Liga��o de Esgoto sem RA",
					"exibirEfetuarLigacaoEsgotoSemRAAction.do?menu=sim");

			return retorno;
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Matr�cula Im�vel");
		}
	}

}
