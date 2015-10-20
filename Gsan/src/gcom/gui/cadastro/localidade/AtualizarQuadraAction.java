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
package gcom.gui.cadastro.localidade;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/***
 * @author Administrador, Ivan Sergio
 * @date 16/02/2009
 * @alteracao 16/02/2009 - CRC1178 - Adicionado o Indicador de Incremento do Lote
 */
public class AtualizarQuadraAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        String confirmado = httpServletRequest.getParameter("confirmado");
        
        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarQuadraActionForm form = (AtualizarQuadraActionForm) actionForm;
        
        Quadra quadraParaManter = (Quadra) sessao.getAttribute("quadraManter");
        
        //CARREGANDO O OBJETO INSERIR_QUADRA_HELPER
		InserirQuadraHelper helper = this.carregarInserirQuadraHelper(form);
		
		String mensagemAgua = "";
		String mensagemEsgoto = "";		
		Integer qtdImoveisAgua = 0;
		
		//VERIFICA��O DO INDICADOR DE REDE DE AGUA
		if(form.getIndicadorRedeAguaAux().equals(Quadra.COM_REDE.toString())){
			Collection<Integer> colecaoImovel = fachada.obterIdImoveisLigacaoAguaSituacao(form.getQuadraID());
			
			if(colecaoImovel != null && colecaoImovel.size() > 0){
				Integer qtdImoveis = colecaoImovel.size();
				
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
					httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/atualizarQuadraAction.do");
			
					return montarPaginaConfirmacao("atencao.existe_imovel_situacao_agua_potencial",
							httpServletRequest, actionMapping, qtdImoveis.toString());
				}else{
					confirmado = null;
				}				
				
				for (Integer idImovel : colecaoImovel) {
					qtdImoveisAgua = qtdImoveisAgua + 1;
					
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
					Imovel imovelAtualizacao = (Imovel)Util.retonarObjetoDeColecao(fachada.pesquisar(filtroImovel, Imovel.class.getName()));
				
					FiltroLigacaoAguaSituacao filtroSituacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
					filtroSituacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, LigacaoAguaSituacao.FACTIVEL));
					LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao)Util.retonarObjetoDeColecao(fachada
						.pesquisar(filtroSituacaoAguaSituacao,LigacaoAguaSituacao.class.getName()));
					
					imovelAtualizacao.setLigacaoAguaSituacao(ligacaoAguaSituacao);
					fachada.atualizar(imovelAtualizacao);
								   
					mensagemAgua = "FACTIVEL";
					sessao.setAttribute("qtdImoveisAgua", qtdImoveisAgua);
					sessao.setAttribute("mensagemAgua", mensagemAgua);
				}
			}
		}else if(form.getIndicadorRedeAguaAux().equals(Quadra.SEM_REDE.toString())){						
			Collection<Integer> colecaoImoveisSituacaoNaoFactivel = fachada.obterIdImoveisLigacaoAguaSituacaoNaoFactivel(form.getQuadraID());
			Collection<Integer> colecaoImoveisSituacaoFactivel = fachada.obterIdImoveisLigacaoAguaSituacaoFactivel(form.getQuadraID());
			
			if(colecaoImoveisSituacaoNaoFactivel != null && colecaoImoveisSituacaoNaoFactivel.size() > 0){				
				throw new ActionServletException("atencao.existe_imovel_situacao_agua_com_rede", null , colecaoImoveisSituacaoNaoFactivel.size() + "");	
			}
			
			if(colecaoImoveisSituacaoFactivel != null && colecaoImoveisSituacaoFactivel.size() > 0){			
				Integer qtdImoveis = colecaoImoveisSituacaoFactivel.size();
				
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
					httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/atualizarQuadraAction.do");
			
					return montarPaginaConfirmacao("atencao.existe_imovel_situacao_agua_factivel",
							httpServletRequest, actionMapping, qtdImoveis.toString());
				}else{
					confirmado = null;					
				}
				
				for (Integer idImovel : colecaoImoveisSituacaoFactivel) {			
					qtdImoveisAgua = qtdImoveisAgua + 1;
					
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
					Imovel imovelAtualizacao = (Imovel)Util.retonarObjetoDeColecao(fachada.pesquisar(filtroImovel, Imovel.class.getName()));
				
					FiltroLigacaoAguaSituacao filtroSituacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
					filtroSituacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, LigacaoAguaSituacao.POTENCIAL));
					LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao)Util.retonarObjetoDeColecao(fachada
						.pesquisar(filtroSituacaoAguaSituacao,LigacaoAguaSituacao.class.getName()));
					
					imovelAtualizacao.setLigacaoAguaSituacao(ligacaoAguaSituacao);
					fachada.atualizar(imovelAtualizacao);
					
					mensagemAgua = "POTENCIAL";
					sessao.setAttribute("qtdImoveisAgua", qtdImoveisAgua);
					sessao.setAttribute("mensagemAgua", mensagemAgua);
				}				
			}			
		}		
			
		Integer qtdImoveisEsgoto = 0;
		
		//VERIFICA��O DO INDICADOR DE REDE DE ESGOTO
		if(form.getIndicadorRedeEsgotoAux().equals(Quadra.COM_REDE.toString())){
			Collection<Integer> colecaoImovel = fachada.obterIdImoveisLigacaoEsgotoSituacao(form.getQuadraID());
			
			if(colecaoImovel != null && colecaoImovel.size() > 0){
				Integer qtdImoveis = colecaoImovel.size();
				
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
					httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/atualizarQuadraAction.do");
			
					return montarPaginaConfirmacao("atencao.existe_imovel_situacao_esgoto_potencial",
							httpServletRequest, actionMapping, qtdImoveis.toString());
				}
				
				for (Integer idImovel : colecaoImovel) {	
					qtdImoveisEsgoto = qtdImoveisEsgoto + 1;
					
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
					Imovel imovelAtualizacao = (Imovel)Util.retonarObjetoDeColecao(fachada.pesquisar(filtroImovel, Imovel.class.getName()));
				
					FiltroLigacaoEsgotoSituacao filtroSituacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
					filtroSituacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, LigacaoEsgotoSituacao.FACTIVEL));
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao)Util.retonarObjetoDeColecao(fachada
						.pesquisar(filtroSituacaoEsgotoSituacao,LigacaoEsgotoSituacao.class.getName()));
					
					imovelAtualizacao.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
					fachada.atualizar(imovelAtualizacao);
					mensagemEsgoto = "FACTIVEL";
				}
			}
		}else if(form.getIndicadorRedeEsgotoAux().equals(Quadra.SEM_REDE.toString())){
			Collection<Integer> colecaoImoveisSituacaoNaoFactivel = fachada.obterIdImoveisLigacaoEsgotoSituacaoNaoFactivel(form.getQuadraID());
			Collection<Integer> colecaoImoveisSituacaoFactivel = fachada.obterIdImoveisLigacaoEsgotoSituacaoFactivel(form.getQuadraID());
			
			if(colecaoImoveisSituacaoNaoFactivel != null && colecaoImoveisSituacaoNaoFactivel.size() > 0){
				throw new ActionServletException("atencao.existe_imovel_situacao_esgoto_com_rede", null , colecaoImoveisSituacaoNaoFactivel.size() + "");
			}
			
			if(colecaoImoveisSituacaoFactivel != null && colecaoImoveisSituacaoFactivel.size() > 0){	
				Integer qtdImoveis = colecaoImoveisSituacaoFactivel.size();
				
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
					httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/atualizarQuadraAction.do");
			
					return montarPaginaConfirmacao("atencao.existe_imovel_situacao_esgoto_factivel",
							httpServletRequest, actionMapping, qtdImoveis.toString());
				}
				
				for (Integer idImovel : colecaoImoveisSituacaoFactivel) {
					qtdImoveisEsgoto = qtdImoveisEsgoto + 1;
					
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
					Imovel imovelAtualizacao = (Imovel)Util.retonarObjetoDeColecao(fachada.pesquisar(filtroImovel, Imovel.class.getName()));
				
					FiltroLigacaoEsgotoSituacao filtroSituacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
					filtroSituacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, LigacaoEsgotoSituacao.POTENCIAL));
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao)Util.retonarObjetoDeColecao(fachada
						.pesquisar(filtroSituacaoEsgotoSituacao,LigacaoEsgotoSituacao.class.getName()));
					
					imovelAtualizacao.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
					fachada.atualizar(imovelAtualizacao);
					mensagemEsgoto = "POTENCIAL";
				}				
			}			
		}		
		
        //VALIDANDO OS DADOS DA QUADRA
		Quadra quadraAtualizar = fachada.validarQuadra(helper);
		quadraAtualizar.setId(quadraParaManter.getId());
		quadraAtualizar.setUltimaAlteracao(quadraParaManter.getUltimaAlteracao());
		quadraAtualizar.setIndicadorBloqueio(new Short(form.getIndicadorBloqueio()));
		
		//OBTENDO AS FACES DA QUADRA
		Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");

        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        if(quadraAtualizar.getRota() != null){
        	if(quadraAtualizar.getRota().getIndicadorRotaAlternativa().shortValue() == 2){
            	fachada.atualizarQuadra(quadraAtualizar, usuarioLogado, colecaoQuadraFace);
            }else{
            	throw new ActionServletException("atencao.rota_alternativa_nao_pode_associar_quadra");
            }
        }

        qtdImoveisAgua = (Integer)sessao.getAttribute("qtdImoveisAgua");
        
        if(qtdImoveisAgua == null){
        	qtdImoveisAgua = 0;
        }
        
        mensagemAgua = (String)sessao.getAttribute("mensagemAgua");
        
       sessao.removeAttribute("qtdImoveisAgua");
       sessao.removeAttribute("mensagemAgua");
        
        if(qtdImoveisAgua > 0 && qtdImoveisEsgoto == 0){
	        montarPaginaSucesso(httpServletRequest,
	        	String.format("Quadra de n�mero %s do setor comercial %s - %s da localidade %s - %s atualizado com sucesso. %s Im�vel(s) atualizado(s) para a situa��o de �gua %s.",
	        		helper.getQuadraNM(),helper.getSetorComercialCD(),quadraAtualizar.getSetorComercial().getDescricao(),helper.getLocalidadeID(),
	        		quadraAtualizar.getSetorComercial().getLocalidade().getDescricao(),qtdImoveisAgua,mensagemAgua),
	                "Realizar outra Manuten��o de Quadra", "exibirFiltrarQuadraAction.do?desfazer=S");      
        }else if(qtdImoveisEsgoto > 0 && qtdImoveisAgua == 0){
	    	 montarPaginaSucesso(httpServletRequest,
	        	String.format("Quadra de n�mero %s do setor comercial %s - %s da localidade %s - %s atualizado com sucesso. %s Im�vel(s) atualizado(s) para a situa��o de esgoto %s.",
	        		helper.getQuadraNM(),helper.getSetorComercialCD(),quadraAtualizar.getSetorComercial().getDescricao(),helper.getLocalidadeID(),
	        		quadraAtualizar.getSetorComercial().getLocalidade().getDescricao(),qtdImoveisEsgoto,mensagemEsgoto),
	                "Realizar outra Manuten��o de Quadra", "exibirFiltrarQuadraAction.do?desfazer=S");
        }else if(qtdImoveisAgua > 0 && qtdImoveisEsgoto > 0){
        	 montarPaginaSucesso(httpServletRequest,
 	        	String.format("Quadra de n�mero %s do setor comercial %s - %s da localidade %s - %s atualizado com sucesso. %s Im�vel(s) atualizado(s) para a situa��o de �gua %s e %s Imov�is atualizados para a situa��o de esgoto %s.",
 	        		helper.getQuadraNM(),helper.getSetorComercialCD(),quadraAtualizar.getSetorComercial().getDescricao(),helper.getLocalidadeID(),
 	        		quadraAtualizar.getSetorComercial().getLocalidade().getDescricao(),qtdImoveisAgua,mensagemAgua,qtdImoveisEsgoto,mensagemEsgoto),
 	                "Realizar outra Manuten��o de Quadra", "exibirFiltrarQuadraAction.do?desfazer=S");     	
        }else{        	
        	 montarPaginaSucesso(httpServletRequest,
  	        	String.format("Quadra de n�mero %s do setor comercial %s - %s da localidade %s - %s atualizado com sucesso.",
  	        		helper.getQuadraNM(),helper.getSetorComercialCD(),quadraAtualizar.getSetorComercial().getDescricao(),helper.getLocalidadeID(),
  	        		quadraAtualizar.getSetorComercial().getLocalidade().getDescricao()),
  	        		"Realizar outra Manuten��o de Quadra", "exibirFiltrarQuadraAction.do?desfazer=S");
        }
        
        sessao.removeAttribute("quadraManter");
        sessao.removeAttribute("colecaoPerfilQuadra");
        sessao.removeAttribute("colecaoSistemaEsgoto");
        sessao.removeAttribute("colecaoZeis");
        sessao.removeAttribute("colecaoBacia");
        sessao.removeAttribute("colecaoQuadraFace");

        return retorno;
    }
    
    private InserirQuadraHelper carregarInserirQuadraHelper(AtualizarQuadraActionForm atualizarQuadraActionForm){
		
		InserirQuadraHelper helper = new InserirQuadraHelper();
		
		helper.setQuadraId(atualizarQuadraActionForm.getQuadraID());
		helper.setIndicadorUso(atualizarQuadraActionForm.getIndicadorUso());
		
		helper.setLocalidadeID(atualizarQuadraActionForm.getLocalidadeID());
		helper.setSetorComercialCD(atualizarQuadraActionForm.getSetorComercialCD());
		helper.setQuadraNM(atualizarQuadraActionForm.getQuadraNM());
		helper.setPerfilQuadraID(atualizarQuadraActionForm.getPerfilQuadra());
		helper.setAreaTipoID(atualizarQuadraActionForm.getAreaTipoID());
		
		helper.setIndicadorRedeAgua(atualizarQuadraActionForm.getIndicadorRedeAguaAux());
		helper.setIndicadorRedeEsgoto(atualizarQuadraActionForm.getIndicadorRedeEsgotoAux());
		helper.setSistemaEsgotoID(atualizarQuadraActionForm.getSistemaEsgotoID());
		helper.setBaciaID(atualizarQuadraActionForm.getBaciaID());
		helper.setDistritoOperacionalID(atualizarQuadraActionForm.getDistritoOperacionalID());
		helper.setSetorCensitarioID(atualizarQuadraActionForm.getSetorCensitarioID());
		helper.setZeisID(atualizarQuadraActionForm.getZeisID());
		helper.setRotaCD(atualizarQuadraActionForm.getCodigoRota());
		helper.setIndicadorIncrementoLote(atualizarQuadraActionForm.getIndicadorIncrementoLote());
		
		helper.setBairroCD(atualizarQuadraActionForm.getBairroID());
		helper.setMunicipioID(atualizarQuadraActionForm.getMunicipioID());
		
		helper.setIndicadorAtualizacaoCadastral(atualizarQuadraActionForm.getIndicadorAtualizacaoCadastral());
		
		return helper;
	}

}
