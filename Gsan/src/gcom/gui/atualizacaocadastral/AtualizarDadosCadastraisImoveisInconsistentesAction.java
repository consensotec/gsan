/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
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
 * [UC1297] Atualizar Dados Cadastrais para Imóveis Inconsistêntes
 * 
 * @author Bruno Sá Barreto
 * @since 05/11/2014
 * 
 **/
public class AtualizarDadosCadastraisImoveisInconsistentesAction extends GcomAction {

	//Atributos utilizados para controlar o
	//"acumulo" de exceptions que podem ser geradas
	//na atualização para exibir apenas a contabilização
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
			
			//retornar imóveis para campo
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
		
		//caso tenha sido selecionado apenas 1 registro para atualização, a mensagem deverá ser exibida
		//a segunda assertiva dentro do desvio condicional é para o caso exclusivo de alteração de economias
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
			mensagem = "Total de inconsistências atualizadas com sucesso: " + countAtualizados 
		    		 + "<div style='color:red;'><br>Total de inconsistências não atualizadas: " + countFalhas+"</div>";
		}
		
		montarPaginaSucesso(httpServletRequest, mensagem,
				"Realizar outra Manutenção",
				"exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?menu=sim");
		
		return retorno;
	}
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Imóveis Inconsistentes
	 * [SB0026] - Retornar Imóvel para Campo
	 * 
	 * Este método verifica se existe algum imovel a ser retornado para campo
	 * 
	 * @author Bruno Sá Barreto
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
	 * [UC1297] - Atualizar Dados Cadastrais Para Imóveis Inconsistentes
	 * [SB0026] - Retornar Imóvel para Campo
	 * 
	 * @author Bruno Sá Barreto
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
	 * [UC1297] Atualizar Dados Cadastrais para Imóveis Inconsistêntes
	 * [FS0017] Verifica Atributo Categoria ou Economia Selecionada
	 * 
	 * @author Bruno Sá Barreto
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
		
		//Verifica se existe o atributo categoria e o atributo economia. Caso existam verifica se é inconsistente
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
