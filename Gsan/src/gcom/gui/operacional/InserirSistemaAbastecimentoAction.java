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
package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroTipoCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.TipoCaptacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirSistemaAbastecimentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

	        //Seta o retorno
	        ActionForward retorno = actionMapping.findForward("telaSucesso");
	
	        InserirSistemaAbastecimentoActionForm form = (InserirSistemaAbastecimentoActionForm) actionForm;
	
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
	
	        //------------ REGISTRAR TRANSA��O ----------------
	        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_SISTEMA_ABASTECIMENTO_INSERIR,
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	        
	        Operacao operacao = new Operacao();
	        operacao.setId(Operacao.OPERACAO_SISTEMA_ABASTECIMENTO_INSERIR);
	
	        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
	        operacaoEfetuada.setOperacao(operacao);
	        //------------ REGISTRAR TRANSA��O ----------------
	
	        
	        String descricao = form.getDescricao();
	        String descricaoAbreviada = form.getDescricaoAbreviada();
	        String fonteCaptacaoId = form.getFonteCaptacao();
	        String tipoCaptacaoId = form.getTipoCaptacao();
	        
	        if(descricao == null || descricao.equals("")){
	        	
	        	//Descri��o n�o informada
	        	throw new ActionServletException("atencao.descricao_sistema_abastecimento_nao_informado");
	        } else if(descricaoAbreviada == null || descricaoAbreviada.equals("")){
	        
	        	//Descri��o Abreviada n�o informada
	        	throw new ActionServletException("atencao.descricao_abreviada_sistema_abastecimento_nao_informado");
	        } else{
	        	
	        	//Fonte de Captacao
	        	FonteCaptacao fonteCaptacao = new FonteCaptacao();
	        	
	        	if (Util.verificarNaoVazio(fonteCaptacaoId)) {
	
	            	Collection colecaoPesquisaFonteCaptacao = null;
	
	                FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
	
	                filtroFonteCaptacao.adicionarParametro(new ParametroSimples(FiltroFonteCaptacao.ID, 
	                		fonteCaptacaoId));
	
	                filtroFonteCaptacao.adicionarParametro(
	                	new ParametroSimples(
	                			FiltroFonteCaptacao.INDICADOR_USO,
	                		ConstantesSistema.INDICADOR_USO_ATIVO));
	
	                //Retorna Fonte de Capta��o
	                colecaoPesquisaFonteCaptacao = 
	                	this.getFachada().pesquisar(filtroFonteCaptacao,
	                        FonteCaptacao.class.getName());
	
	                if (colecaoPesquisaFonteCaptacao == null || colecaoPesquisaFonteCaptacao.isEmpty()) {
	                    //Fonte de Captacao inexistente
	                    throw new ActionServletException("atencao.pesquisa.fonte_captacao_inexistente");
	                } else {
	                    fonteCaptacao =(FonteCaptacao) Util.retonarObjetoDeColecao(colecaoPesquisaFonteCaptacao);
	                }
	        	}
	                
	               //Tipo de Captacao
	               //TipoCaptacao tipoCaptacao = new TipoCaptacao();
	            	
	            	if (Util.verificarNaoVazio(tipoCaptacaoId)) {
	
	                	Collection colecaoPesquisaTipoCaptacao = null;
	
	                    FiltroTipoCaptacao filtroTipoCaptacao = new FiltroTipoCaptacao();
	
	                    filtroTipoCaptacao.adicionarParametro(new ParametroSimples(FiltroTipoCaptacao.ID, 
	                    		tipoCaptacaoId));
	
	                    filtroTipoCaptacao.adicionarParametro(
	                    	new ParametroSimples(
	                    			FiltroTipoCaptacao.INDICADOR_USO,
	                    		ConstantesSistema.INDICADOR_USO_ATIVO));
	
	                    //Retorna Tipo de Capta��o
	                    colecaoPesquisaTipoCaptacao = 
	                    	this.getFachada().pesquisar(filtroTipoCaptacao,
	                            TipoCaptacao.class.getName());
	
	                    if (colecaoPesquisaTipoCaptacao == null || colecaoPesquisaTipoCaptacao.isEmpty()) {
	                        //Tipo de Captacao inexistente
	                        throw new ActionServletException("atencao.pesquisa.tipo_captacao_inexistente");
	                    } /*else {
	                        tipoCaptacao =(TipoCaptacao) Util.retonarObjetoDeColecao(colecaoPesquisaTipoCaptacao);
	                    }*/
	                
	                
	        	}
	        	//Criar o objeto sistemaAbastecimento que ser� inserido na base
	        	SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
	        	
	        	sistemaAbastecimento.setDescricao(descricao);
	        	sistemaAbastecimento.setDescricaoAbreviada(descricaoAbreviada);
	        	sistemaAbastecimento.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
	        	sistemaAbastecimento.setUltimaAlteracao(new Date());
	        	sistemaAbastecimento.setFonteCaptacao(fonteCaptacao);
	        	
	        	//------------ REGISTRAR TRANSA��O ----------------
	        	sistemaAbastecimento.setOperacaoEfetuada(operacaoEfetuada);
	        	sistemaAbastecimento.adicionarUsuario(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
	        	registradorOperacao.registrarOperacao(sistemaAbastecimento);
	        	//------------ REGISTRAR TRANSA��O ----------------
	        	
	        	Integer codigoSistemaAbastecimentoInserido = 
	          		(Integer) this.getFachada().inserir(sistemaAbastecimento);
	        	
	        	montarPaginaSucesso(httpServletRequest,
	            		"Sistema de Abastecimento de c�digo " + sistemaAbastecimento.getId() 
	                    	+ " - "  + sistemaAbastecimento.getDescricao().toUpperCase() 
	                    	+ " inserido com sucesso.",
	                    	"Inserir outro Sistema Abastecimento",
	                    	"exibirInserirSistemaAbastecimentoAction.do?menu=sim",
	                    	"exibirAtualizarSistemaAbastecimentoAction.do?menu=sim&sistemaAbastecimentoId=" + 
	                    	codigoSistemaAbastecimentoInserido, "Atualizar Sistema de Abastecimento Inserido");
	        	
	       }
	
	        //devolve o mapeamento de retorno
	        return retorno;
	    }

    }
