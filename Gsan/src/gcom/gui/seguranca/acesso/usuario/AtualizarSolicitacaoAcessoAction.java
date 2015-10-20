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

import gcom.cadastro.EnvioEmail;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1093] Manter Solicita��o de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 18/11/2010
 */

public class AtualizarSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		AtualizarSolicitacaoAcessoActionForm form = 
			(AtualizarSolicitacaoAcessoActionForm) actionForm;
		
		// Seta Objeto Solicitacao Acesso
		SolicitacaoAcesso solicitacaoAcesso = setSolicitacaoAcesso(form, sessao);
		
		SolicitacaoAcesso solicitacaoAcessoBase = null;
		
		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
		filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcesso.LOGIN, solicitacaoAcesso.getLogin()));
		
		Collection colecaoSolicitacaoAcesso = this.getFachada().pesquisar(filtroSolicitacaoAcesso, 
				SolicitacaoAcesso.class.getName());
	
		if(!Util.isVazioOrNulo(colecaoSolicitacaoAcesso)){
			
			solicitacaoAcessoBase = (SolicitacaoAcesso) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcesso);
			solicitacaoAcesso.setId( solicitacaoAcessoBase.getId());
		}
		
		// atualizar Solicita��o de Acesso.
		this.getFachada().atualizar(solicitacaoAcesso);
		
		// remover todos os grupos associado a Solicita��o de Acesso.
		this.getFachada().removerGrupoDeSolicitacaoAcesso(solicitacaoAcesso.getId());
		
		if (form.getIdsGrupo() != null && !form.getIdsGrupo().equals("-1") ){
			
			List lista = Arrays.asList(form.getIdsGrupo());  
			Collection<Grupo> colecaoGrupo = new ArrayList();
			
			FiltroGrupo filtroGrupo = new FiltroGrupo();
			filtroGrupo.adicionarParametro(new ParametroSimplesIn( 
					FiltroGrupo.ID, lista));	
			
			colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());
			
			Grupo grupo = null;
			SolicitacaoAcessoGrupo solicitacaoAcessoGrupo = null;
			SolicitacaoAcessoGrupoPK solicitacaoAcessoGrupoPK = null;
			
			if(colecaoGrupo != null && !colecaoGrupo.isEmpty() ){
				
				Iterator iterator = colecaoGrupo.iterator();
			
				while (iterator.hasNext()) {
					
					grupo = (Grupo) iterator.next();
					
					solicitacaoAcessoGrupoPK = new SolicitacaoAcessoGrupoPK(
							solicitacaoAcesso, grupo);
					
					solicitacaoAcessoGrupo = new SolicitacaoAcessoGrupo(
							solicitacaoAcessoGrupoPK, new Date());
					
					Fachada.getInstancia().inserir(solicitacaoAcessoGrupo);
				}
			}
		}
		
		// Enviar Email
		EnvioEmail envioEmail = Fachada.getInstancia().pesquisarEnvioEmail(
				EnvioEmail.INSERIR_SOLICITACAO_ACESSO);

		String emailRemetente = envioEmail.getEmailRemetente();

		String tituloMensagem = "Solicita��o de Acesso.";

		String emailReceptor = form.getEmail();

		String mensagem = "";
		
		if(solicitacaoAcesso.getFuncionario() != null){
			
			mensagem += " O Funcion�rio, Matr�cula: "+ solicitacaoAcesso.getFuncionario().getId()
				+ " necessita de sua libera��o de acesso.";
		}else{
		
			mensagem += " O Prestador de servi�os, CPF: "+ solicitacaoAcesso.getCpf()
				+ " necessita de sua libera��o de acesso.";
		}
		
		if(solicitacaoAcesso.getIndicadorNotificarResponsavel().compareTo(ConstantesSistema.SIM) == 0){
			
			try {
				ServicosEmail.enviarMensagem(emailRemetente, emailReceptor,
						tituloMensagem, mensagem);
				
			} catch (ErroEmailException erroEnviarEmail) {
				erroEnviarEmail.printStackTrace();
			}
		}

		// Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Solicita��o de Acesso para usu�rio: " 
				+  solicitacaoAcesso.getNomeUsuario()
				+ " Atualizado com sucesso. ",
				"Atualizar outra Solicita��o de Acesso",
				"exibirFiltrarSolicitacaoAcessoAction.do?objeto=atualizar&menu=sim");

		return retorno;
	
	}
	
	/**
	 * Preenche objeto com informa��es vindas da tela
	 * 
	 * @author Hugo Leonardo
	 * @date 11/11/2010
	 * 
	 * @param form
	 * @return SolicitacaoAcesso
	 */
	private SolicitacaoAcesso setSolicitacaoAcesso(AtualizarSolicitacaoAcessoActionForm form, 
			HttpSession sessao) {		
		
		SolicitacaoAcesso solicitacaoAcesso = new SolicitacaoAcesso();
		
		// funcion�rio solicitante
		if(Util.verificarNaoVazio(form.getIdFuncionarioSolicitante())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionarioSolicitante()));
			solicitacaoAcesso.setFuncionarioSolicitante(funcionario);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Funcion�rio Solicitante");
		}
		
		// funcion�rio respons�vel
		if(Util.verificarNaoVazio(form.getIdFuncionarioSuperior())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionarioSuperior()));
			solicitacaoAcesso.setFuncionarioResponsavel(funcionario);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Respons�vel pela Autoriza��o");
		}
		
		// Indicador Notificar por E-mail
		if(Util.verificarNaoVazio(form.getIcNotificar())){
			
			if(form.getIcNotificar().equals("0")){
				
				solicitacaoAcesso.setIndicadorNotificarResponsavel(new Short("1"));
			}else if(form.getIcNotificar().equals("1")){
				
				solicitacaoAcesso.setIndicadorNotificarResponsavel(new Short("2"));
			}
		}
		
		// tipo Usu�rio
		if(Util.verificarNaoVazio(form.getIdTipoUsuario())){
			UsuarioTipo usuarioTipo = new UsuarioTipo();
			usuarioTipo.setId(new Integer(form.getIdTipoUsuario()));
			solicitacaoAcesso.setUsuarioTipo(usuarioTipo);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Tipo de Usu�rio");
		}
		
		// funcion�rio usu�rio
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionario()));
			solicitacaoAcesso.setFuncionario(funcionario);
		}

		// Empresa, Nome Usu�rio
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
		
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(
					FiltroFuncionario.UNIDADE_EMPRESA);
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form.getIdFuncionario()));

			Collection<Funcionario> funcionarioPesquisado = Fachada.getInstancia().pesquisar(
					filtroFuncionario, Funcionario.class.getName());
			
			if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
				
				solicitacaoAcesso.setEmpresa(funcionario.getEmpresa());
				
				// CPF
				if(funcionario.getNumeroCpf() != null){
					
					if(Util.validacaoCPF(funcionario.getNumeroCpf())){
						solicitacaoAcesso.setCpf(funcionario.getNumeroCpf());
					}else{
						throw new ActionServletException("atencao.digito_verificador_cpf_nao_confere");
					}
				}
				
				// Data Nascimento
				if(funcionario.getDataNascimento() != null){
					solicitacaoAcesso.setDataNascimento(funcionario.getDataNascimento());
				}

				// Nome Usu�rio
				int tamanhoMaximoCampo = 50;
				if (tamanhoMaximoCampo < funcionario.getNome().length()) {
					// trunca a String
					String strTruncado = funcionario.getNome().substring(0, tamanhoMaximoCampo);
					solicitacaoAcesso.setNomeUsuario(strTruncado);
				}else{
					solicitacaoAcesso.setNomeUsuario(funcionario.getNome());
				}
			}
		}else if(Util.verificarNaoVazio(form.getIdEmpresa())){
			Empresa empresa = new Empresa();
			empresa.setId(new Integer(form.getIdEmpresa()));
			solicitacaoAcesso.setEmpresa(empresa);
		}
		
		// nome Usu�rio
		if(!Util.verificarNaoVazio(form.getIdFuncionario()) 
				&& Util.verificarNaoVazio(form.getNomeUsuario())){
			
			solicitacaoAcesso.setNomeUsuario(form.getNomeUsuario());
		}
		
		// CPF
		if(solicitacaoAcesso.getCpf() == null 
				&& Util.verificarNaoVazio(form.getCpf())){
			
			if(Util.validacaoCPF(form.getCpf())){
				solicitacaoAcesso.setCpf(form.getCpf());
			}else{
				throw new ActionServletException("atencao.digito_verificador_cpf_nao_confere");
			}
			
		}else if(!Util.verificarNaoVazio(form.getIdFuncionario())){
			
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "N�mero do CPF");
		}
		
		// data Nascimento
		if(solicitacaoAcesso.getDataNascimento() == null 
				&& Util.verificarNaoVazio(form.getDataNascimento())
				&& !Util.validarDiaMesAno(form.getDataNascimento())){
			
			solicitacaoAcesso.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
		}else if(!Util.verificarNaoVazio(form.getIdFuncionario())) {
			
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Data de Nascimento");
		}
		
		// Unidade de Lota��o
		if(Util.verificarNaoVazio(form.getIdLotacao())){
			UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
			unidadeOrganizacional.setId(new Integer(form.getIdLotacao()));
			solicitacaoAcesso.setUnidadeOrganizacional(unidadeOrganizacional);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Unidade de Lota��o");
		}
		
		// Login
		if(Util.verificarNaoVazio(form.getLogin())){
			
			solicitacaoAcesso.setLogin(form.getLogin());
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Login");
		}
		
		// Email
		if(Util.verificarNaoVazio(form.getEmail())){
			
			solicitacaoAcesso.setEmail(form.getEmail());
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "E-Mail");
		}
		
		//Per�odo Inicial e Per�odo Final
		if (form.getDataInicial() != null && !form.getDataInicial().equals("")
				&& form.getDataFinal() != null && !form.getDataFinal().equals("")){
			
			if (!Util.validarDiaMesAno(form.getDataInicial())) {
				
				solicitacaoAcesso.setPeriodoInicial(Util.converteStringParaDate(form.getDataInicial()));
				
				if (!Util.validarDiaMesAno(form.getDataFinal())) {
					solicitacaoAcesso.setPeriodoFinal(Util.converteStringParaDate(form.getDataFinal()));
					if(Util.compararData(solicitacaoAcesso.getPeriodoInicial(),solicitacaoAcesso.getPeriodoFinal()) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
		}else{
			solicitacaoAcesso.setPeriodoInicial(null);
			solicitacaoAcesso.setPeriodoFinal(null);
			
		}
		
		// Solicita��o Acesso Situa��o
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		filtroGrupo.adicionarParametro( new ParametroSimples(
				FiltroGrupo.INDICADOR_SUPERINTENDENCIA, 
					ConstantesSistema.INDICADOR_SUPERINTENDENCIA));
		filtroGrupo.adicionarParametro( new ParametroSimples(
				FiltroGrupo.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, 
				Grupo.class.getName());
	
		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
		
		if(!Util.isVazioOrNulo(colecaoGrupo)){
			
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, 
							SolicitacaoAcessoSituacao.AG_CADASTRAMENTO));
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
			
			Collection colecaoSolicitacaoAcessoSituacao = this.getFachada().pesquisar(filtroSolicitacaoAcessoSituacao, 
					SolicitacaoAcessoSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)){
				
				SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = 
					(SolicitacaoAcessoSituacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
				
				solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
				solicitacaoAcesso.setDataAutorizacao(new Date());
			}else{
				
				throw new ActionServletException("atencao.solicitacao_acesso_situacao.ag_cadastramento");
			}
		}else{
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, 
							SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP));
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
			
			Collection colecaoSolicitacaoAcessoSituacao = this.getFachada().pesquisar(filtroSolicitacaoAcessoSituacao, 
					SolicitacaoAcessoSituacao.class.getName());
			if(!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)){
				
				SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = 
					(SolicitacaoAcessoSituacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
				
				solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
			}else{
				
				throw new ActionServletException("atencao.solicitacao_acesso_situacao.ag_autorizacao");
			}
		}
		
		// Grupo
		if (form.getIdsGrupo() == null || form.getIdsGrupo().equals("-1") ){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Grupo");
		}
		
		// Data Solicita��o
		solicitacaoAcesso.setDataSolicitacao(new Date());
		
		// Ultima Altera��o
		solicitacaoAcesso.setUltimaAlteracao(new Date());

		return solicitacaoAcesso;

	}

}
