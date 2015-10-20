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
package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para filtrar o criterio da cobran�a
 * 
 * @author S�vio Luiz
 * @date 29/06/2006
 */
public class FiltrarUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterUsuario");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarUsuarioActionForm usuarioFiltrarActionForm = (FiltrarUsuarioActionForm) actionForm;

		// Recupera os par�metros do form
		String usuarioTipo = usuarioFiltrarActionForm.getUsuarioTipo();
		String empresa = usuarioFiltrarActionForm.getEmpresa();
		String idFuncionario = usuarioFiltrarActionForm.getIdFuncionario();
		String nome = usuarioFiltrarActionForm.getNome();
		String idLotacao = usuarioFiltrarActionForm.getIdLotacao();
		String usuarioSituacao = usuarioFiltrarActionForm.getUsuarioSituacao();
		String abrangencia = usuarioFiltrarActionForm.getAbrangencia();
		String gerenciaRegional = usuarioFiltrarActionForm.getGerenciaRegional();
		String unidadeNegocio = usuarioFiltrarActionForm.getUnidadeNegocio();
		String indicadorFuncionario = usuarioFiltrarActionForm.getIndicadorFuncionario();
		String idElo = usuarioFiltrarActionForm.getIdElo();
		String idLocalidade = usuarioFiltrarActionForm.getIdLocalidade();
		String[] grupos = usuarioFiltrarActionForm.getGrupo();
		
		String dataCadastramentoInicial = usuarioFiltrarActionForm.getDataCadastramentoInicial();
		String dataCadastramentoFinal = usuarioFiltrarActionForm.getDataCadastramentoFinal();
		
		String dataExpiracaoInicial = usuarioFiltrarActionForm.getDataExpiracaoInicial();
		String dataExpiracaoFinal = usuarioFiltrarActionForm.getDataExpiracaoFinal();
		
		String cpf = usuarioFiltrarActionForm.getCpf();
		String dataNascimento = usuarioFiltrarActionForm.getDataNascimento();
		
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		String loginUsuario = usuarioFiltrarActionForm.getLoginUsuario();
		
		FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo(FiltroUsuarioGrupo.USUARIO_NOME);

		boolean peloMenosUmParametroInformado = false;

		if (!"".equals(dataCadastramentoInicial)) {
			
			Date data = Util.converteStringParaDate(dataCadastramentoInicial);
			
			if (data == null) {
				throw new ActionServletException("atencao.data.inicio.invalida");
			}
			
			if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException(
						"atencao.data_inicial.posterior.hoje", null, Util
								.formatarData(new Date()));
			}
		}
		
		if (!"".equals(dataCadastramentoFinal)) {
			
			Date data = Util.converteStringParaDate(dataCadastramentoFinal);
			
			if (data == null) {
				throw new ActionServletException("atencao.data.final.invalida");
			}
			
			if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException(
						"atencao.data_final.posterior.hoje", null, Util
								.formatarData(new Date()));
			}
		}
		
		if (!"".equals(dataCadastramentoInicial) && !"".equals(dataCadastramentoFinal)) {
			
			Date dataInicial = Util.converteStringParaDate(dataCadastramentoInicial);
			Date dataFinal = Util.converteStringParaDate(dataCadastramentoFinal);

			if (dataFinal.getTime() < dataInicial.getTime()) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null, Util
								.formatarData(new Date()));
			}
		}

		if (!"".equals(dataExpiracaoInicial) && !"".equals(dataExpiracaoFinal)) {
			Date dataInicial = Util
					.converteStringParaDate(dataExpiracaoInicial);
			Date dataFinal = Util.converteStringParaDate(dataExpiracaoFinal);

			if (dataFinal.getTime() < dataInicial.getTime()) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null, Util
								.formatarData(new Date()));
			}
		}
		
		if (!"".equals(dataNascimento)) {
			
			Date data = Util.converteStringParaDate(dataNascimento);
			
			if (data == null) {
				throw new ActionServletException("atencao.data.final.invalida");
			}
			
			if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException(
						"atencao.data_nascimento_superior_atual",dataNascimento, Util
								.formatarData(new Date()));
			}
		}
		

		// Insere os par�metros informados no filtro
		if (usuarioTipo != null && !usuarioTipo.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_TIPO_ID, usuarioTipo));
		}
		if (idFuncionario != null && !idFuncionario.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_FUNCIONARIO_ID, idFuncionario));
		}
		if (empresa != null && !empresa.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_EMPRESA_ID, empresa));
		}
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ComparacaoTexto(
					FiltroUsuarioGrupo.USUARIO_NOME, nome.toUpperCase()));
		}
		if (indicadorFuncionario != null && !indicadorFuncionario.equals("")) {
			short indFuncionario = new Short(indicadorFuncionario);
			if (indFuncionario != UsuarioTipo.INDICADOR_FUNCIONARIO) {
				if (idLotacao != null && !idLotacao.trim().equalsIgnoreCase("")) {
					peloMenosUmParametroInformado = true;
					filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupo.USUARIO_LOTACAO_ID, idLotacao));
				}
			}
		} else {
			if (idLotacao != null && !idLotacao.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
						FiltroUsuarioGrupo.USUARIO_LOTACAO_ID, idLotacao));
			}
		}
		if (usuarioSituacao != null
				&& !usuarioSituacao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_SITUACAO_ID, usuarioSituacao));
		}
		if (abrangencia != null && !abrangencia.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ABRANGENCIA_ID, abrangencia));
		}
		if (gerenciaRegional != null
				&& !gerenciaRegional.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_GERENCIA_REGIONAL_ID,
					gerenciaRegional));
		}
		if (unidadeNegocio != null
				&& !unidadeNegocio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_UNIDADE_NEGOCIO_ID,
					unidadeNegocio));
		}
		if (idElo != null && !idElo.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ELO_ID, idElo));
		}

		if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_LOCALIDADE_ID, idLocalidade));
		}
		if (grupos != null && grupos.length > 0) {
			// Indica que o usu�rio informou um par�metro para pesquisar
			peloMenosUmParametroInformado = true;

			// La�o para inserir no filtro todos os grupos informados informadas
			for (int i = 0; i < grupos.length; i++) {

				if (grupos.length == 1) {
					filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupo.GRUPO_ID, grupos[i]));
				} else {
					if (i == 0) {
						filtroUsuarioGrupo
								.adicionarParametro(new ParametroSimples(
										FiltroUsuarioGrupo.GRUPO_ID, grupos[i],
										ParametroSimples.CONECTOR_OR,
										grupos.length));
					} else {
						if (i == (grupos.length - 1)) {
							filtroUsuarioGrupo
									.adicionarParametro(new ParametroSimples(
											FiltroUsuarioGrupo.GRUPO_ID,
											grupos[i]));
						} else {
							filtroUsuarioGrupo
									.adicionarParametro(new ParametroSimples(
											FiltroUsuarioGrupo.GRUPO_ID,
											grupos[i],
											ParametroSimples.CONECTOR_OR));
						}
					}
				}

			}
		}

		if (dataCadastramentoInicial != null
				&& !dataCadastramentoInicial.trim().equalsIgnoreCase("")) {
			Date dataCadastroInicial = Util
					.converteStringParaDate(dataCadastramentoInicial);
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new MaiorQue(
					FiltroUsuarioGrupo.USUARIO_DATA_CADASTRAMENTO,
					dataCadastroInicial));
		}
		if (dataCadastramentoFinal != null
				&& !dataCadastramentoFinal.trim().equalsIgnoreCase("")) {
			Date dataCadastroFinal = Util
					.converteStringParaDate(dataCadastramentoFinal);
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new MenorQue(
					FiltroUsuarioGrupo.USUARIO_DATA_CADASTRAMENTO,
					dataCadastroFinal));
		}
		if (dataExpiracaoInicial != null
				&& !dataExpiracaoInicial.trim().equalsIgnoreCase("")) {
			Date dataExpiracaoInicialConvertida = Util
					.converteStringParaDate(dataExpiracaoInicial);
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new MaiorQue(
					FiltroUsuarioGrupo.USUARIO_DATA_EXPIRACAO,
					dataExpiracaoInicialConvertida));
		}
		if (dataExpiracaoFinal != null
				&& !dataExpiracaoFinal.trim().equalsIgnoreCase("")) {
			Date dataExpiracaoFinalConvertida = Util
					.converteStringParaDate(dataExpiracaoFinal);
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new MenorQue(
					FiltroUsuarioGrupo.USUARIO_DATA_CADASTRO_FIM,
					dataExpiracaoFinalConvertida));
		}
		
		if (dataNascimento != null
				&& !dataNascimento.trim().equalsIgnoreCase("")) {
			
			Date dataNascimentoConvertida = Util.converteStringParaDate(dataNascimento);
			peloMenosUmParametroInformado = true;
			
			if(usuarioFiltrarActionForm.getNomeFuncionario() != null && !usuarioFiltrarActionForm.getNomeFuncionario().equals("")){
				filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
						FiltroUsuarioGrupo.DATA_NASCIMENTO_FUNCIONARIO,	dataNascimentoConvertida));
			}else{

				filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
						FiltroUsuarioGrupo.DATA_NASCIMENTO,	dataNascimentoConvertida));
			}
			
		}
		
		if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if(usuarioFiltrarActionForm.getNomeFuncionario() != null && !usuarioFiltrarActionForm.getNomeFuncionario().equals("")){
				
				filtroUsuarioGrupo.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroUsuarioGrupo.CPF_FUNCIONARIO, cpf.toUpperCase()));
				
			}else{
				
				filtroUsuarioGrupo.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroUsuarioGrupo.CPF, cpf.toUpperCase()));
			}
			
			
		}
		
		if(loginUsuario != null 
				&& !loginUsuario.equals("")){
			peloMenosUmParametroInformado = true;
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_LOGIN,loginUsuario.trim()));
		}
		
		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado && usuarioFiltrarActionForm.getIndicadorUsuarioBatch() == 2 &&  usuarioFiltrarActionForm.getIndicadorUsuarioInternet() == 2) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
//		if(usuarioFiltrarActionForm.getIndicadorUsuarioBatch() != null){
//			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
//					FiltroUsuarioGrupo.INDICADOR_BATCH,usuarioFiltrarActionForm.getIndicadorUsuarioBatch()));
//		}
//		
//		if(usuarioFiltrarActionForm.getIndicadorUsuarioInternet() != null){
//			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
//					FiltroUsuarioGrupo.INDICADOR_INTERNET,usuarioFiltrarActionForm.getIndicadorUsuarioInternet()));
//		}

	// Manda o filtro pelo request para o ExibirManterClienteAction
		sessao.setAttribute("filtroUsuarioGrupo", filtroUsuarioGrupo);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		sessao.setAttribute("indicadorUsuarioBatch", usuarioFiltrarActionForm.getIndicadorUsuarioBatch());
		sessao.setAttribute("indicadorUsuarioInternet", usuarioFiltrarActionForm.getIndicadorUsuarioInternet());
		return retorno;

	}
}
