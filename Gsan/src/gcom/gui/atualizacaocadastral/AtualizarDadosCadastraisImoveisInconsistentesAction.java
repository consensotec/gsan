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
package gcom.gui.atualizacaocadastral;


import gcom.atualizacaocadastral.AtributoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.RetornoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.bean.AtualizacoesPorInconsistenciaHelper;
import gcom.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralDMHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.FachadaException;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1297] Atualizar Dados Cadastrais para Im�veis Inconsist�ntes
 * 
 * @author Bruno S� Barreto
 * @since 05/11/2014
 * 
 **/
public class AtualizarDadosCadastraisImoveisInconsistentesAction extends GcomAction {

	//Atributos utilizados para controlar o
	//"acumulo" de exceptions que podem ser geradas
	//na atualiza��o para exibir apenas a contabiliza��o
	public String parametroMensagem;
	public String matriculaImovel;
	Exception erro=null; boolean erroFachada=false;
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		int countAtualizados=0, countFalhas=0;
		int contadorAlteracoes = 0;
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		
		Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoImoveisInconsistentesHelper = (Collection<DadosMovimentoAtualizacaoCadastralDMHelper>) 
				sessao.getAttribute("colecaoImoveisInconsistentesHelper");
		
		Iterator<DadosMovimentoAtualizacaoCadastralDMHelper> iteratorHelper = colecaoImoveisInconsistentesHelper.iterator();
		while( iteratorHelper.hasNext() ) {
			
			DadosMovimentoAtualizacaoCadastralDMHelper dadosMovimentoHelper = (DadosMovimentoAtualizacaoCadastralDMHelper) iteratorHelper.next();
			Collection<AtualizacoesPorInconsistenciaHelper> colecaoAtualizacoesHelper = dadosMovimentoHelper.getColecaoAtualizacoesHelper();
			
			Iterator<AtualizacoesPorInconsistenciaHelper> iteratorAtualizacoesHelper = colecaoAtualizacoesHelper.iterator();
			while( iteratorAtualizacoesHelper.hasNext() ) {
				
				AtualizacoesPorInconsistenciaHelper atualizacoesHelper = (AtualizacoesPorInconsistenciaHelper) iteratorAtualizacoesHelper.next();
				String valorAlteracao = httpServletRequest.getParameter("alteracao"+ atualizacoesHelper.getIdRetornoAtlzCadastral());
				atualizacoesHelper.setUsuario(usuario);
				
				boolean validarAtributoCategoriaEconomia = false;
				
				if ( valorAlteracao != null && !valorAlteracao.equals("") && (atualizacoesHelper.getCodigoAlteracao()==null||!atualizacoesHelper.getCodigoAlteracao().equals(valorAlteracao))) {
					atualizacoesHelper.setValorAlteracao(new Short(valorAlteracao));
					contadorAlteracoes++;
					
					if(MensagemAtualizacaoCadastralDM.ALTERACAO_CATEGORIA_NAO_AUTORIZADA.equals(atualizacoesHelper.getIdMensagem())
						|| MensagemAtualizacaoCadastralDM.ALTERACAO_NUMERO_ECONOMIAS_NAO_AUTORIZADA.equals(atualizacoesHelper.getIdMensagem())){
						validarAtributoCategoriaEconomia = true;
						
					}
				} else {
					atualizacoesHelper.setValorAlteracao(null);
				}
				
				if(atualizacoesHelper.getValorAlteracao()!=null){
					try{
						if(validarAtributoCategoriaEconomia)verificaAtributoCategoriaEconomia(dadosMovimentoHelper, requestMap);
						fachada.atualizarIndicadorAtualizacaoAtributo(atualizacoesHelper);
						countAtualizados++;
					}catch(FachadaException ex){
						erroFachada=true;
						countFalhas++;
						erro=ex;
					}catch (ActionServletException e) {
						countFalhas++;
						erro=e;
					}
				}
			}
			
			//retornar im�veis para campo
			this.retornarImoveisCampo(colecaoImoveisInconsistentesHelper, httpServletRequest, fachada, usuario.getId());
			
			boolean existeImovelComAlteracoesPendentes = fachada.existeImovelComAlteracoesPendentes(new Integer(dadosMovimentoHelper.getMatricula()));
			if (!existeImovelComAlteracoesPendentes){
				fachada.atualizarIndicadorImovelPendenteAtualizacaoCadastral(dadosMovimentoHelper.getIdImovelAtlzCadastral());
			}
			
		}
		
		boolean existeImovelParaRetornar = verificarExistenciaImoveisRetornarCampo(colecaoImoveisInconsistentesHelper, httpServletRequest);
		if(contadorAlteracoes==0 && !existeImovelParaRetornar){
			throw new ActionServletException("atencao.nenhuma_inconsistencia_selecionada");	
		}
		
		//caso tenha sido selecionado apenas 1 registro para atualiza��o, a mensagem dever� ser exibida
		//a segunda assertiva dentro do desvio condicional � para o caso exclusivo de altera��o de economias
		//e categorias de forma que gere erros. ex. aprovar um e reprovar o outro.
		if((contadorAlteracoes<2 && countFalhas==1) || (contadorAlteracoes==2 && countFalhas==2 && !erroFachada)){ 
			if(erroFachada){
				FachadaException ex = (FachadaException) erro;
				throw new FachadaException(ex.getMessage(), ex, ex.getParametroMensagem());	
			}
			if(parametroMensagem != null){
				throw new ActionServletException(erro.getMessage(), parametroMensagem, matriculaImovel);
			}
			throw new ActionServletException(erro.getMessage(), matriculaImovel);
		}
		
		String mensagem="Atualizado com sucesso.";
		if(countFalhas>0){
			mensagem = "Total de inconsist�ncias atualizadas com sucesso: " + countAtualizados 
		    		 + "<div style='color:red;'><br>Total de inconsist�ncias n�o atualizadas: " + countFalhas+"</div>";
		}
		
		montarPaginaSucesso(httpServletRequest, mensagem,
				"Realizar outra Manuten��o",
				"exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?menu=sim");
		
		return retorno;
	}
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * [SB0026] - Retornar Im�vel para Campo
	 * 
	 * Este m�todo verifica se existe algum imovel a ser retornado para campo
	 * 
	 * @author Bruno S� Barreto
	 * @date 28/01/2015
	 *
	 * @param colecaoImoveisInconsistentesHelper
	 * @param requestMap
	 */
	private boolean verificarExistenciaImoveisRetornarCampo(Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoImoveisInconsistentesHelper,
			HttpServletRequest req) {
		
		if(colecaoImoveisInconsistentesHelper.size()>0){
			for(DadosMovimentoAtualizacaoCadastralDMHelper dadosMovimento : colecaoImoveisInconsistentesHelper){
				String retornarCampo = req.getParameter("retornarCampo"+ dadosMovimento.getIdImovelAtlzCadastral());
				if(retornarCampo!=null){
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Im�veis Inconsistentes
	 * [SB0026] - Retornar Im�vel para Campo
	 * 
	 * @author Bruno S� Barreto
	 * @date 28/01/2015
	 *
	 * @param colecaoImoveisInconsistentesHelper
	 * @param requestMap
	 */
	private void retornarImoveisCampo(
			Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoImoveisInconsistentesHelper,
			HttpServletRequest req, Fachada fachada, Integer idUsuario) {

			if(verificarExistenciaImoveisRetornarCampo(colecaoImoveisInconsistentesHelper, req)){
				for(DadosMovimentoAtualizacaoCadastralDMHelper dadosMovimento : colecaoImoveisInconsistentesHelper){
					String retornarCampo = req.getParameter("retornarCampo"+ dadosMovimento.getIdImovelAtlzCadastral());
					if(retornarCampo!=null){
						fachada.retornarImovelInconsistenteParaCampo(dadosMovimento, idUsuario);
					}
				}
			}
		
	}

	/**
	 * [UC1297] Atualizar Dados Cadastrais para Im�veis Inconsist�ntes
	 * [FS0017] Verifica Atributo Categoria ou Economia Selecionada
	 * 
	 * @author Bruno S� Barreto
	 * @since 05/11/2014
	 * 
	 */
	public void verificaAtributoCategoriaEconomia( DadosMovimentoAtualizacaoCadastralDMHelper dadosMovimentoHelper, Map<String, String[]> requestMap) {
		
		Collection<AtualizacoesPorInconsistenciaHelper> colecaoAtualizacoesHelper = dadosMovimentoHelper.getColecaoAtualizacoesHelper();
		String valorAlteracaoCategoria = null;
		String valorAlteracaoEconomia = null;
		boolean existeEconomia = false;
		boolean existeCategoria = false;
		
		Iterator<AtualizacoesPorInconsistenciaHelper> iteratorAtualizacoesHelper = colecaoAtualizacoesHelper.iterator();
		while( iteratorAtualizacoesHelper.hasNext() ) {
			AtualizacoesPorInconsistenciaHelper atualizacoesHelper = (AtualizacoesPorInconsistenciaHelper) iteratorAtualizacoesHelper.next();
			
			if ( atualizacoesHelper.getIdAtributo().equals(AtributoAtualizacaoCadastralDM.CATEGORIA_SUBCATEGORIA) ) {
				valorAlteracaoCategoria = (requestMap.get("alteracao"+ atualizacoesHelper.getIdRetornoAtlzCadastral()))[0];
				existeCategoria = true;
			}
			if ( atualizacoesHelper.getIdAtributo().equals(AtributoAtualizacaoCadastralDM.ECONOMIA)  ) {
				valorAlteracaoEconomia = (requestMap.get("alteracao"+ atualizacoesHelper.getIdRetornoAtlzCadastral()))[0];
				existeEconomia = true;
			}
		}
		
		if ( existeCategoria && existeEconomia && valorAlteracaoCategoria != null && !valorAlteracaoCategoria.equals("") && 
				(valorAlteracaoEconomia == null || valorAlteracaoEconomia.equals("")) ) {
			parametroMensagem = "ECONOMIA";
			matriculaImovel = dadosMovimentoHelper.getMatricula();
			throw new ActionServletException("atencao.informar_situacao", "ECONOMIA", dadosMovimentoHelper.getMatricula());	
		}
		
		if ( existeCategoria && existeEconomia && valorAlteracaoEconomia != null && !valorAlteracaoEconomia.equals("") && 
				( valorAlteracaoCategoria == null || valorAlteracaoCategoria.equals("") ) ) {
			parametroMensagem = "CATEGORIA/SUBCATEGORIA";
			matriculaImovel = dadosMovimentoHelper.getMatricula();
			throw new ActionServletException("atencao.informar_situacao", "CATEGORIA/SUBCATEGORIA", dadosMovimentoHelper.getMatricula());	
		}
		
		//Verifica se existe o atributo categoria e o atributo economia. Caso existam verifica se � inconsistente
		if ( existeCategoria && existeEconomia && (
				(RetornoAtualizacaoCadastralDM.APROVADO.toString().equals(valorAlteracaoCategoria) && RetornoAtualizacaoCadastralDM.REPROVADO.toString().equals(valorAlteracaoEconomia)) 
			  ||(RetornoAtualizacaoCadastralDM.APROVADO.toString().equals(valorAlteracaoEconomia) && RetornoAtualizacaoCadastralDM.REPROVADO.toString().equals(valorAlteracaoCategoria))
		)){
			parametroMensagem = null;
			matriculaImovel = dadosMovimentoHelper.getMatricula();
			throw new ActionServletException("atencao.situacao_economia_categoria_invalida", null, dadosMovimentoHelper.getMatricula());
		}
	}
}
