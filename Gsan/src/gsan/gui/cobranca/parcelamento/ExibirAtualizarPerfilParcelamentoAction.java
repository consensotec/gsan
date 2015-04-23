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
package gsan.gui.cobranca.parcelamento;

import gsan.cadastro.sistemaparametro.FiltroSistemaParametro;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.parcelamento.FiltroParcDesctoInativVista;
import gsan.cobranca.parcelamento.FiltroParcelamentoDescontoAntiguidade;
import gsan.cobranca.parcelamento.FiltroParcelamentoDescontoInatividade;
import gsan.cobranca.parcelamento.FiltroParcelamentoFaixaValor;
import gsan.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gsan.cobranca.parcelamento.FiltroParcelamentoPerfilDebitos;
import gsan.cobranca.parcelamento.FiltroParcelamentoQuantidadePrestacao;
import gsan.cobranca.parcelamento.FiltroParcelamentoQuantidadeReparcelamento;
import gsan.cobranca.parcelamento.ParcDesctoInativVista;
import gsan.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gsan.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gsan.cobranca.parcelamento.ParcelamentoFaixaValor;
import gsan.cobranca.parcelamento.ParcelamentoPerfil;
import gsan.cobranca.parcelamento.ParcelamentoPerfilDebitos;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.faturamento.conta.FiltroContaMotivoRevisao;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
 * Action de Exibir Atualizar Perfil de Parcelamento
 * @author Vivianne Sousa, Arthur Carvalho
 * @created 12/05/2006   , 02/07/2012
 */

public class ExibirAtualizarPerfilParcelamentoAction extends GcomAction {
	 
	Fachada fachada = Fachada.getInstancia();
	 
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	    	
    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("atualizarPerfilParcelamento");
        AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);

        // volta da msg de  Perfil de Parcelamento já utilizado, não pode ser alterado nem excluído.
        String confirmado = httpServletRequest.getParameter("confirmado");
        
        String idPerfilParcelamento = null;
       
        idPerfilParcelamento = this.recuperaIdPerfilParcelamento(httpServletRequest, sessao, confirmado, idPerfilParcelamento);
       
        //Pesquisa ContaMotivoRevisao
        this.pesquisarContaMotivoRevisao(sessao);

        //Pesquisa PercentualMaximoAbatimento
        this.pesquisaPercentualMaximoAbatimento(httpServletRequest);
       
        
        //Verifica se o usuário está selecionando o Perfil de Parcelamento da página de manter 
        //Caso contrário o usuário está teclando enter na página de atualizar
		if ( (idPerfilParcelamento != null && !idPerfilParcelamento.equals("")) &&  
				( httpServletRequest.getParameter("desfazer") == null)&&	(httpServletRequest.getParameter("reload") == null || 
					httpServletRequest.getParameter("reload").equalsIgnoreCase("")) ) {
			
			exibirPerfilParcelamento(idPerfilParcelamento, atualizarParcelamentoPerfilActionForm,fachada,sessao,httpServletRequest);
	    }
		
 	    this.pesquisaTipoDebito(httpServletRequest, atualizarParcelamentoPerfilActionForm, sessao);

	   
	    this.atualizaParametrosNaSessao(sessao, httpServletRequest, atualizarParcelamentoPerfilActionForm, idPerfilParcelamento);
        
      
	    return retorno;  
    
    }

    /**
     * 
     * @param httpServletRequest
     */
    public void pesquisaPercentualMaximoAbatimento(HttpServletRequest httpServletRequest){
    	
    	FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

 		Collection<SistemaParametro> colecaoSistemaParametros = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());

 		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros.iterator().next();
 		String  percentualMaximoAbatimento = "" + sistemaParametro.getPercentualMaximoAbatimento();
 		
 		httpServletRequest.setAttribute("percentualMaximoAbatimento",percentualMaximoAbatimento);
    }
    /**
     * 
     * @param httpServletRequest
     * @param atualizarParcelamentoPerfilActionForm
     * @param sessao
     */
    public void pesquisaTipoDebito(HttpServletRequest httpServletRequest, AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm, HttpSession sessao){
    	
    	//Tipo de Debito
        if ( httpServletRequest.getParameter("objetoConsulta") != null && httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("1") ) {
        	
        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
            filtroDebitoTipo.adicionarParametro( new ParametroSimples(FiltroDebitoTipo.ID, atualizarParcelamentoPerfilActionForm.getIdTipoDebito()));
            
            Collection<DebitoTipo> colecaoDebitoTipo = Fachada.getInstancia().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
            
            if ( colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty() ) {
    	        
            	DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
            
            	atualizarParcelamentoPerfilActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
            	
            	sessao.setAttribute("idDebitoTipoEncontrado", true);
            	
            } else {
            	
            	atualizarParcelamentoPerfilActionForm.setDescricaoTipoDebito("TIPO DE DEBITO INEXISTENTE");
            	sessao.removeAttribute("idDebitoTipoEncontrado");
            }
        }
    }
    
    /**
     * 
     * @param sessao
     */
    public void pesquisarContaMotivoRevisao(HttpSession sessao){

    	//Pesquisando ContaMotivoRevisao
        FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
        filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.INDICADOR_USO, 
        ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.DESCRICAO);
        Collection<ContaMotivoRevisao> collectionContaMotivoRevisao = fachada.pesquisar(filtroContaMotivoRevisao, ContaMotivoRevisao.class.getName());
        
        sessao.setAttribute("collectionContaMotivoRevisao", collectionContaMotivoRevisao);
    }
    
    /**
     * 
     * @param sessao
     * @param httpServletRequest
     * @param atualizarParcelamentoPerfilActionForm
     */
    public void atualizaParametrosNaSessao(HttpSession sessao, HttpServletRequest httpServletRequest , AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,
    		String idPerfilParcelamento) {
    	if (httpServletRequest.getParameter("adicionarParcelamentoQtdePerfil") != null
                && httpServletRequest.getParameter("adicionarParcelamentoQtdePerfil").equalsIgnoreCase("S")
                && atualizarParcelamentoPerfilActionForm.getQtdeMaximaReparcelamento() != null) {
        	
        	//-------------- bt adicionarParcelamentoQtdePerfil ---------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);

        	httpServletRequest.removeAttribute("adicionarParcelamentoQtdePerfil");

        }
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoAntiguidade") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoAntiguidade").equalsIgnoreCase("S")
                && !atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoAntiguidade ------------------------
   
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	        	
        	adicionarParcelamentoDescontoAntiguidade(atualizarParcelamentoPerfilActionForm, sessao,fachada);
        	
        	httpServletRequest.removeAttribute("adicionarParcelamentoDescontoAntiguidade");
        }
        
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividade") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividade").equalsIgnoreCase("S")
                && !atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividade ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoDescontoInatividade(atualizarParcelamentoPerfilActionForm,sessao,fachada);
        	
        	httpServletRequest.removeAttribute("adicionarParcelamentoDescontoInatividade");
        }
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividadeAVista") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividadeAVista").equalsIgnoreCase("S")
                && !atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividadeAVista ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoDescontoInatividadeAVista(atualizarParcelamentoPerfilActionForm,sessao);
        	
        	httpServletRequest.removeAttribute("adicionarParcelamentoDescontoInatividadeAVista");
        	
        }
        
        if ( httpServletRequest.getParameter("adicionarParcelamentoPerfilDebitos") != null && 
        		httpServletRequest.getParameter("adicionarParcelamentoPerfilDebitos").equalsIgnoreCase("S") ) {
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividadeAVista ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoPerfilDebitos(atualizarParcelamentoPerfilActionForm,sessao);
        	
        	httpServletRequest.removeAttribute("adicionarParcelamentoPerfilDebitos");
        	
        }
		
        if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	exibirPerfilParcelamento(idPerfilParcelamento, atualizarParcelamentoPerfilActionForm,fachada,sessao,httpServletRequest);
        	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");
    		sessao.removeAttribute("collectionTipoDebitoReparcelamentoHelperLinhaRemovidas");
        }
        

        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
 		// DEFINIÇÃO QUE IRÁ AUXILIAR O RETORNO DOS POPUPS
        sessao.setAttribute("UseCase", "ATUALIZARPERFIL");
        
        
        if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") == null || 
 		((Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")).size() == 0){
 			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","1");
 		}else{
 			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","2");
 		}
        
    }
    
    /**
     * 
     * @param httpServletRequest
     * @param sessao
     * @param confirmado
     * @param idPerfilParcelamento
     * @return
     */
    private String recuperaIdPerfilParcelamento(HttpServletRequest httpServletRequest, HttpSession sessao,  String confirmado, String idPerfilParcelamento){
    	if (httpServletRequest.getParameter("reload") == null || httpServletRequest.getParameter("reload").equalsIgnoreCase("") && (confirmado == null || confirmado.equals(""))){
            //Recupera o id do PerfilParcelamento que vai ser atualizado
            
            if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
            	idPerfilParcelamento = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
            	//Definindo a volta do botão Voltar p Filtrar Perfil de Parcelamento
    	    	httpServletRequest.setAttribute("voltar", "filtrar");
    	    	sessao.setAttribute("idRegistroAtualizacao",idPerfilParcelamento);
            }else if(httpServletRequest.getParameter("idRegistroAtualizacao") == null){
            	idPerfilParcelamento = (String)sessao.getAttribute("idRegistroAtualizacao");
    			//Definindo a volta do botão Voltar p Filtrar Perfil de Parcelamento
    	    	httpServletRequest.setAttribute("voltar", "filtrar");
            }else if (httpServletRequest.getParameter("idRegistroAtualizacao")!= null) {
            	idPerfilParcelamento = httpServletRequest.getParameter("idRegistroAtualizacao");
            	//Definindo a volta do botão Voltar p Manter Perfil de Parcelamento
            	httpServletRequest.setAttribute("voltar", "manter");
            	sessao.setAttribute("idRegistroAtualizacao",idPerfilParcelamento);
            }
        }else{
     	   idPerfilParcelamento = (String)sessao.getAttribute("idRegistroAtualizacao");
        }
    	
    	return idPerfilParcelamento;
    }
    /**
     * 
     * @param idPerfilParcelamento
     * @param atualizarParcelamentoPerfilActionForm
     * @param fachada
     * @param sessao
     * @param httpServletRequest
     */
    private void exibirPerfilParcelamento(String idPerfilParcelamento, 
    		AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,
			Fachada fachada,HttpSession sessao,
			HttpServletRequest httpServletRequest) {
    	
    	//Cria a variável que vai armazenar o ParcelamentoPerfil para ser atualizado
    	ParcelamentoPerfil parcelamentoPerfil = null;
        
        FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
        filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.ID, idPerfilParcelamento));
        filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("categoria");
		
		Collection<ParcelamentoPerfil> collectionParcelamentoPerfil = fachada.pesquisar(filtroParcelamentoPerfil, ParcelamentoPerfil.class.getName()) ;	
			
		//Caso a pesquisa tenha retornado o ParcelamentoPerfil
		if (collectionParcelamentoPerfil != null && !collectionParcelamentoPerfil.isEmpty()){
			//Zerar o form
			atualizarParcelamentoPerfilActionForm.reset();
			
			//Recupera da coleção o ParcelamentoPerfil que vai ser atualizado
			parcelamentoPerfil = (ParcelamentoPerfil) Util.retonarObjetoDeColecao(collectionParcelamentoPerfil);

			//Seta no form os dados de ParcelamentoPerfil
			
			atualizarParcelamentoPerfilActionForm.setNumeroResolucaoDiretoria("" + parcelamentoPerfil.getResolucaoDiretoria().getNumeroResolucaoDiretoria());
			atualizarParcelamentoPerfilActionForm.setImovelSituacaoTipo("" + parcelamentoPerfil.getImovelSituacaoTipo().getDescricaoImovelSituacaoTipo());
			
			if (parcelamentoPerfil.getSubcategoria() != null && !parcelamentoPerfil.getSubcategoria().equals("")){
				atualizarParcelamentoPerfilActionForm.setSubcategoria("" + parcelamentoPerfil.getSubcategoria().getDescricao());
			}else {
				atualizarParcelamentoPerfilActionForm.setSubcategoria("");
			}
			
			if (parcelamentoPerfil.getCategoria() != null && !parcelamentoPerfil.getCategoria().equals("")){
				atualizarParcelamentoPerfilActionForm.setCategoria("" + parcelamentoPerfil.getCategoria().getDescricao());
			}else {
				atualizarParcelamentoPerfilActionForm.setCategoria("");
			}
			
			if (parcelamentoPerfil.getImovelPerfil() != null && !parcelamentoPerfil.getImovelPerfil().equals("")){
				atualizarParcelamentoPerfilActionForm.setImovelPerfil("" + parcelamentoPerfil.getImovelPerfil().getDescricao());
			}else{
				atualizarParcelamentoPerfilActionForm.setImovelPerfil("");
			}
			
        	if ( httpServletRequest.getParameter("reload") == null || httpServletRequest.getParameter("reload").equalsIgnoreCase("")){

           		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido("" + parcelamentoPerfil.getIndicadorChequeDevolvido());
        		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta("" + parcelamentoPerfil.getIndicadorSancoesUnicaConta());
        		atualizarParcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial( "" + parcelamentoPerfil.getIndicadorRetroativoTarifaSocial());
        		atualizarParcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima( "" + parcelamentoPerfil.getIndicadorAlertaParcelaMinima());
        		atualizarParcelamentoPerfilActionForm.setIndicadorEntradaMinima( "" + parcelamentoPerfil.getIndicadorEntradaMinima());
        		atualizarParcelamentoPerfilActionForm.setCapacidadeHidrometro("" + parcelamentoPerfil.getCapacidadeHidrometro());
  
        		
        		if (parcelamentoPerfil.getPercentualDescontoAcrescimo() != null && !parcelamentoPerfil.getPercentualDescontoAcrescimo().toString().equals("0.00")){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimo("" + parcelamentoPerfil.getPercentualDescontoAcrescimo().toString().replace(".", ","));
        		}
        		
        		if (parcelamentoPerfil.getPercentualDescontoPagamentoAVista() != null && !parcelamentoPerfil.getPercentualDescontoPagamentoAVista().toString().equals("0.00")){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista("" + parcelamentoPerfil.getPercentualDescontoPagamentoAVista().toString().replace(".", ","));
        		}
        		
        		if (parcelamentoPerfil.getPercentualTarifaMinimaPrestacao()!= null){
        			atualizarParcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao("" + parcelamentoPerfil.getPercentualTarifaMinimaPrestacao().toString().replace(".", ","));
        		}
        		
        		if (parcelamentoPerfil.getNumeroConsumoMinimo()!= null && !parcelamentoPerfil.getNumeroConsumoMinimo().equals("")){
        			atualizarParcelamentoPerfilActionForm.setConsumoMinimo("" + parcelamentoPerfil.getNumeroConsumoMinimo());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setConsumoMinimo("");
        		}
        		
        		if (parcelamentoPerfil.getPercentualVariacaoConsumoMedio()!= null && !parcelamentoPerfil.getPercentualVariacaoConsumoMedio().equals("")){
        			atualizarParcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio( Util.formatarMoedaReal(parcelamentoPerfil.getPercentualVariacaoConsumoMedio()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio("");
        		}
        		
        		if (parcelamentoPerfil.getNumeroConsumoEconomia()!= null && !parcelamentoPerfil.getNumeroConsumoEconomia().equals("")){
        			atualizarParcelamentoPerfilActionForm.setNumeroConsumoEconomia(parcelamentoPerfil.getNumeroConsumoEconomia().toString());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setNumeroConsumoEconomia("");
        		}
        		
        		if (parcelamentoPerfil.getNumeroAreaConstruida()!= null && !parcelamentoPerfil.getNumeroAreaConstruida().equals("")){
        			atualizarParcelamentoPerfilActionForm.setNumeroAreaConstruida(Util.formatarMoedaReal(parcelamentoPerfil.getNumeroAreaConstruida()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setNumeroAreaConstruida("");
        		}
        		
        		if (parcelamentoPerfil.getAnoMesReferenciaLimiteInferior()!= null && !parcelamentoPerfil.getAnoMesReferenciaLimiteInferior().equals("")){
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior( Util.formatarAnoMesParaMesAno(parcelamentoPerfil.getAnoMesReferenciaLimiteInferior()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior("");
        		}
        		
        		if (parcelamentoPerfil.getAnoMesReferenciaLimiteSuperior()!= null && !parcelamentoPerfil.getAnoMesReferenciaLimiteSuperior().equals("")){
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior(Util.formatarAnoMesParaMesAno(parcelamentoPerfil.getAnoMesReferenciaLimiteSuperior()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior("");
        		}
        		
        		if (parcelamentoPerfil.getPercentualDescontoAVista()!= null && !parcelamentoPerfil.getPercentualDescontoAVista().equals("")){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAVista(Util.formatarMoedaReal(parcelamentoPerfil.getPercentualDescontoAVista()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAVista("");
        		}
        		 
        		if (parcelamentoPerfil.getParcelaQuantidadeMinimaFatura()!= null && !parcelamentoPerfil.getParcelaQuantidadeMinimaFatura().equals("")){
        			atualizarParcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura( parcelamentoPerfil.getParcelaQuantidadeMinimaFatura().toString());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura("");
        		}
        		
        		if (parcelamentoPerfil.getPercentualDescontoSancao()!= null && !parcelamentoPerfil.getPercentualDescontoSancao().equals("")){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoSancao( Util.formatarMoedaReal(parcelamentoPerfil.getPercentualDescontoSancao()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoSancao("");
        		}
        		
        		if (parcelamentoPerfil.getQuantidadeEconomias()!= null && !parcelamentoPerfil.getQuantidadeEconomias().equals("")){
        			atualizarParcelamentoPerfilActionForm.setQuantidadeEconomias(parcelamentoPerfil.getQuantidadeEconomias().toString());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setQuantidadeEconomias("");
        		}
        		
        		if (parcelamentoPerfil.getQuantidadeMaximaReparcelamento()!= null && !parcelamentoPerfil.getQuantidadeMaximaReparcelamento().equals("")){
        			atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento( "" + parcelamentoPerfil.getQuantidadeMaximaReparcelamento());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento("");
        		}
        		
        		if (parcelamentoPerfil.getDataLimiteDescontoPagamentoAVista()!= null && !parcelamentoPerfil.getDataLimiteDescontoPagamentoAVista().equals("")){
        			atualizarParcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista( Util.formatarData(parcelamentoPerfil.getDataLimiteDescontoPagamentoAVista()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista("");
        		}
        		
        		if(parcelamentoPerfil.getPercentualDescontoDebitoPagamentoAVista() != null && !parcelamentoPerfil.getPercentualDescontoDebitoPagamentoAVista().equals("")){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoAVista(parcelamentoPerfil.getPercentualDescontoDebitoPagamentoAVista().toString());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoAVista("");
        		}
        		
        		if(parcelamentoPerfil.getPercentualDescontoDebitoPagamentoParcelado() != null && !parcelamentoPerfil.getPercentualDescontoDebitoPagamentoParcelado().equals("")){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoParcelado(parcelamentoPerfil.getPercentualDescontoDebitoPagamentoParcelado().toString());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoParcelado("");
        		}
        		
        		if(parcelamentoPerfil.getDataLimiteVencimentoContaAVista() != null && !parcelamentoPerfil.getDataLimiteVencimentoContaAVista().equals("")){
        			atualizarParcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoAVista(Util.formatarData(parcelamentoPerfil.getDataLimiteVencimentoContaAVista()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoAVista("");
        		}
        		
        		if(parcelamentoPerfil.getDataLimiteVencimentoContaParcelado() != null && !parcelamentoPerfil.getDataLimiteVencimentoContaParcelado().equals("")){
        			atualizarParcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoParcelado(Util.formatarData(parcelamentoPerfil.getDataLimiteVencimentoContaParcelado()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoParcelado("");
        		}
        		
        		if ( parcelamentoPerfil.getPercentualDescontoMulta() != null ) {
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoMulta("" + parcelamentoPerfil.getPercentualDescontoMulta().toString().replace(".", ","));
        		}
        	
        		if ( parcelamentoPerfil.getPercentualDescontoJuros() != null ) {
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoJuros("" + parcelamentoPerfil.getPercentualDescontoJuros().toString().replace(".", ","));
        		}
        		
        		if ( parcelamentoPerfil.getPercentualDescontoAtualizacaoMonetaria()!= null ) {
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAtualizacaoMonetaria("" + parcelamentoPerfil.getPercentualDescontoAtualizacaoMonetaria().toString().replace(".", ","));
        		}
        		
        		if ( parcelamentoPerfil.getPercentualDescontoPagamentoAVistaMulta()!= null ) {
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaMulta("" + parcelamentoPerfil.getPercentualDescontoPagamentoAVistaMulta().toString().replace(".", ","));
        		}
        		
        		if ( parcelamentoPerfil.getPercentualDescontoPagamentoAVistaJuros()!= null ) {
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaJuros("" + parcelamentoPerfil.getPercentualDescontoPagamentoAVistaJuros().toString().replace(".", ","));
        		}
        		
        		if ( parcelamentoPerfil.getPercentualDescontoPagamentoAVistaAtuMonetaria()!= null ) {
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaAtuMonetaria("" + parcelamentoPerfil.getPercentualDescontoPagamentoAVistaAtuMonetaria().toString().replace(".", ","));
        		}
        		
        		if ( parcelamentoPerfil.getPercentualValorContaConsumoMedioPrestacao()!= null ) {
        			atualizarParcelamentoPerfilActionForm.setPercentualValorContaConsumoMedioPrestacao("" + parcelamentoPerfil.getPercentualValorContaConsumoMedioPrestacao().toString().replace(".", ","));
        		}
        		
        		if (parcelamentoPerfil.getAnoMesReferenciaLimiteContaPagamentoAVista()!= null && !parcelamentoPerfil.getAnoMesReferenciaLimiteContaPagamentoAVista().equals("")){
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteContaPagamentoAVista( Util.formatarAnoMesParaMesAno(parcelamentoPerfil.getAnoMesReferenciaLimiteContaPagamentoAVista()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteContaPagamentoAVista("");
        		}
        		
        		if (parcelamentoPerfil.getAnoMesReferenciaLimiteContaParcelada()!= null && !parcelamentoPerfil.getAnoMesReferenciaLimiteContaParcelada().equals("")){
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteContaParcelada( Util.formatarAnoMesParaMesAno(parcelamentoPerfil.getAnoMesReferenciaLimiteContaParcelada()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteContaParcelada("");
        		}
        	}
						
			atualizarParcelamentoPerfilActionForm.setUltimaAlteracao(Util.formatarDataComHora(parcelamentoPerfil.getUltimaAlteracao()));   
			
			if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")== null
					|| (httpServletRequest.getParameter("desfazer") != null
			                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
				//recupera a coleção de ParcelamentoQuantidadeReparcelamento	
				// e tranforma numa coleção de ParcelamentoQuantidadeReparcelamentoHelper
				FiltroParcelamentoQuantidadeReparcelamento filtroParcelamentoQuantidadeReparcelamento = new FiltroParcelamentoQuantidadeReparcelamento(); 
				
				filtroParcelamentoQuantidadeReparcelamento.adicionarParametro(new ParametroSimples(
						FiltroParcelamentoDescontoAntiguidade.PARCELAMENTO_PERFIL,idPerfilParcelamento));
				
				Collection<ParcelamentoQuantidadeReparcelamento> collectionParcelamentoQuantidadeReparcelamento = fachada
				.pesquisar(filtroParcelamentoQuantidadeReparcelamento,ParcelamentoQuantidadeReparcelamento.class.getName());
				
				Iterator iterator = collectionParcelamentoQuantidadeReparcelamento.iterator();
				Collection 	collectionParcelamentoQuantidadeReparcelamentoHelper = new ArrayList();
				while (iterator.hasNext()) {
					ParcelamentoQuantidadeReparcelamento parcelamentoQtdeReparcelamento = (ParcelamentoQuantidadeReparcelamento) iterator.next();
					
					ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQtdeReparcelamentoHelper = new ParcelamentoQuantidadeReparcelamentoHelper();

					//recupera a coleção de ParcelamentoQuantidadePrestacao
					// e seta no objeto ParcelamentoQtdeReparcelamentoHelper
					FiltroParcelamentoQuantidadePrestacao filtroParcelamentoQuantidadePrestacao = new FiltroParcelamentoQuantidadePrestacao();
					filtroParcelamentoQuantidadePrestacao.adicionarParametro(new ParametroSimples(
							FiltroParcelamentoQuantidadePrestacao.PARCELAMENTO_QUANTIDADE_REPARCELAMENTO,parcelamentoQtdeReparcelamento.getId()));
					Collection<ParcelamentoQuantidadePrestacao> collectionParcelamentoQuantidadePrestacao = fachada
					.pesquisar(filtroParcelamentoQuantidadePrestacao,ParcelamentoQuantidadePrestacao.class.getName());
					
					
					////////// n posso setar em CollectionParcelamentoQuantidadePrestacaoHelper uma CollectionParcelamentoQuantidadePrestacao
					
					Collection collectionParcelamentoQuantidadePrestacaoHelper = new ArrayList();
					Iterator iteratorParcelamentoFaixaValor = collectionParcelamentoQuantidadePrestacao.iterator();
					
					while (iteratorParcelamentoFaixaValor.hasNext()) {
						ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao = 
							(ParcelamentoQuantidadePrestacao) iteratorParcelamentoFaixaValor.next();
						
						FiltroParcelamentoFaixaValor filtroParcelamentoFaixaValor = new FiltroParcelamentoFaixaValor();
						filtroParcelamentoFaixaValor.adicionarParametro(new ParametroSimples(FiltroParcelamentoFaixaValor.PARCELAMENTO_QUANTIDADE_PRESTACAO, parcelamentoQuantidadePrestacao.getId()));
						filtroParcelamentoFaixaValor.setCampoOrderBy(FiltroParcelamentoFaixaValor.VALOR_FAIXA);
		
						Collection collectionParcelamentoFaixaValor = fachada.pesquisar(filtroParcelamentoFaixaValor, ParcelamentoFaixaValor.class.getName());
						
						ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = new ParcelamentoQuantidadePrestacaoHelper();
						
						parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
						parcelamentoQuantidadePrestacaoHelper.setCollectionParcelamentoFaixaValor(collectionParcelamentoFaixaValor);
						collectionParcelamentoQuantidadePrestacaoHelper.add(parcelamentoQuantidadePrestacaoHelper);
					}
					
					parcelamentoQtdeReparcelamentoHelper.setCollectionParcelamentoQuantidadePrestacaoHelper
								(collectionParcelamentoQuantidadePrestacaoHelper);

					parcelamentoQtdeReparcelamentoHelper.setId(parcelamentoQtdeReparcelamento.getId());
					parcelamentoQtdeReparcelamentoHelper.setQuantidadeMaximaReparcelamento(parcelamentoQtdeReparcelamento.getQuantidadeMaximaReparcelamento());
					parcelamentoQtdeReparcelamentoHelper.setPercentualEntradaSugerida(parcelamentoQtdeReparcelamento.getPercentualEntradaSugerida());
					parcelamentoQtdeReparcelamentoHelper.setInformacaoParcelamentoQtdeReparcelamento("INFORMADAS");
					parcelamentoQtdeReparcelamentoHelper.setUltimaAlteracao(parcelamentoQtdeReparcelamento.getUltimaAlteracao());
					collectionParcelamentoQuantidadeReparcelamentoHelper.add(parcelamentoQtdeReparcelamentoHelper);

				}

				sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper",collectionParcelamentoQuantidadeReparcelamentoHelper);
	
			}
						
			if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") == null
					|| (httpServletRequest.getParameter("desfazer") != null
			                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
				
				//recupera a coleção de ParcelamentoDescontoAntiguidade
				FiltroParcelamentoDescontoAntiguidade filtroParcelamentoDescontoAntiguidade = new FiltroParcelamentoDescontoAntiguidade();
				
				filtroParcelamentoDescontoAntiguidade.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
				
				filtroParcelamentoDescontoAntiguidade.adicionarParametro(new ParametroSimples(
						FiltroParcelamentoDescontoAntiguidade.PARCELAMENTO_PERFIL,idPerfilParcelamento));

				Collection<ParcelamentoDescontoAntiguidade> collectionParcelamentoDescontoAntiguidade = fachada
				.pesquisar(filtroParcelamentoDescontoAntiguidade,ParcelamentoDescontoAntiguidade.class.getName());
				//httpServletRequest.setAttribute("collectionParcelamentoDescontoAntiguidade",collectionParcelamentoDescontoAntiguidade);
				sessao.setAttribute("collectionParcelamentoDescontoAntiguidade",collectionParcelamentoDescontoAntiguidade);				
			}

			
			if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") == null
					|| (httpServletRequest.getParameter("desfazer") != null
			                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
				//recupera a coleção de ParcelamentoDescontoInatividade
				FiltroParcelamentoDescontoInatividade filtroParcelamentoDescontoInatividade = new FiltroParcelamentoDescontoInatividade();
				
				filtroParcelamentoDescontoInatividade.adicionarParametro(new ParametroSimples(
						FiltroParcelamentoDescontoInatividade.PARCELAMENTO_PERFIL,idPerfilParcelamento));
	
				Collection<ParcelamentoDescontoInatividade> collectionParcelamentoDescontoInatividade = fachada
				.pesquisar(filtroParcelamentoDescontoInatividade,ParcelamentoDescontoInatividade.class.getName());
				//httpServletRequest.setAttribute("collectionParcelamentoDescontoInatividade",collectionParcelamentoDescontoInatividade);
				sessao.setAttribute("collectionParcelamentoDescontoInatividade",collectionParcelamentoDescontoInatividade);
			}
			
			if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") == null
					|| (httpServletRequest.getParameter("desfazer") != null
			                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
				//recupera a coleção de ParcelamentoDescontoInatividadeAVista
				FiltroParcDesctoInativVista filtroParcDesctoInativVista = new FiltroParcDesctoInativVista();
				
				filtroParcDesctoInativVista.adicionarParametro(new ParametroSimples(
						FiltroParcelamentoDescontoInatividade.PARCELAMENTO_PERFIL,idPerfilParcelamento));
	
				Collection<ParcDesctoInativVista> collectionParcelamentoDescontoInatividadeAVista = fachada
				.pesquisar(filtroParcDesctoInativVista,ParcDesctoInativVista.class.getName());

				sessao.setAttribute("collectionParcelamentoDescontoInatividadeAVista",collectionParcelamentoDescontoInatividadeAVista);
			}
			
			if (sessao.getAttribute("collectionTipoDebitosHelper") == null || 
					( httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) ) {
				
				//recupera a coleção de ParcelamentoPerfilDebitos
				FiltroParcelamentoPerfilDebitos filtroParcelamentoPerfilDebitos = new FiltroParcelamentoPerfilDebitos();
				filtroParcelamentoPerfilDebitos.adicionarParametro( new ParametroSimples(FiltroParcelamentoPerfilDebitos.ID_PARCEMANENTO_PERFIL, idPerfilParcelamento));
				filtroParcelamentoPerfilDebitos.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
	
				Collection<ParcelamentoPerfilDebitos> collectionParcelamentoPerfilDebitos = fachada.pesquisar(filtroParcelamentoPerfilDebitos,ParcelamentoPerfilDebitos.class.getName());
				
				if ( collectionParcelamentoPerfilDebitos != null && !collectionParcelamentoPerfilDebitos.isEmpty() ) {
					
					sessao.setAttribute("collectionTipoDebitosHelper",collectionParcelamentoPerfilDebitos);
				}
			}
		}
    }
    /**
     * 
     * @param atualizarParcelamentoPerfilActionForm
     * @param sessao
     * @param fachada
     */
	private void adicionarParcelamentoDescontoAntiguidade(
						AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,
						HttpSession sessao,Fachada fachada){
		
		if (atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito()== null ||
				atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe Qtde. Mínima Meses de Débito p/ Desconto
					"atencao.required", null, " Qtde. Mínima Meses de Débito p/ Desconto");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito())){
			throw new ActionServletException(
					// Qtde. Mínima Meses de Débito p/ Desconto deve ser numerico
					"atencao.integer", null, " Qtde. Mínima Meses de Débito p/ Desconto");
		 }
		
		Collection<ParcelamentoDescontoAntiguidade> collectionParcelamentoDescontoAntiguidade = null;
		
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
			collectionParcelamentoDescontoAntiguidade = (Collection) sessao
		    .getAttribute("collectionParcelamentoDescontoAntiguidade");
		} else {
			collectionParcelamentoDescontoAntiguidade = new ArrayList();
		}
	    
	
		Integer quantidadeMinimaMesesDebito = new Integer(atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito());
		
		BigDecimal percentualDescontoSemRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{	
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade());
		}
	
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade());
        	
        }
        
        BigDecimal percentualDescontoAtivo = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtivo()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtivo().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Ativo
					"atencao.required", null, "  Percentual de Desconto Ativo");	
        
        }else{
        	percentualDescontoAtivo = Util.formatarMoedaRealparaBigDecimal
        		(atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtivo());
		}
	
	
		ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeNovo = 
								new ParcelamentoDescontoAntiguidade();
		
		if (collectionParcelamentoDescontoAntiguidade != null && !collectionParcelamentoDescontoAntiguidade.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
			ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoAntiguidade.iterator();
			
			while (iterator.hasNext()) {
				parcelamentoDescontoAntiguidadeAntigo = (ParcelamentoDescontoAntiguidade) iterator.next();
				
				// Verificar quantidade mínima meses de debito para desconto
				if (quantidadeMinimaMesesDebito.equals
						(parcelamentoDescontoAntiguidadeAntigo.getQuantidadeMinimaMesesDebito())){
					//Quantidade Mínima Meses de Debito para Desconto já informada
					throw new ActionServletException(
					"atencao.qtde_minima_meses_debito_desconto_ja_informado");
				}
			}
		}
		
		if (atualizarParcelamentoPerfilActionForm.getIdContaMotivoRevisao() != null && 
			!atualizarParcelamentoPerfilActionForm.getIdContaMotivoRevisao().equalsIgnoreCase("") &&
			!atualizarParcelamentoPerfilActionForm.getIdContaMotivoRevisao().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
	        	
			FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
		        
			filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.ID, 
			new Integer(atualizarParcelamentoPerfilActionForm.getIdContaMotivoRevisao())));
		        
		    Collection<ContaMotivoRevisao> collectionContaMotivoRevisao = fachada.pesquisar(filtroContaMotivoRevisao, 
		    ContaMotivoRevisao.class.getName());
		        
		    ContaMotivoRevisao contaMotivoRevisao = (ContaMotivoRevisao)
		    Util.retonarObjetoDeColecao(collectionContaMotivoRevisao);
		        
		    parcelamentoDescontoAntiguidadeNovo.setContaMotivoRevisao(contaMotivoRevisao);
		}
		
		verificarPercentualMaximo(percentualDescontoComRestabelecimento);
		verificarPercentualMaximo(percentualDescontoSemRestabelecimento);
		verificarPercentualMaximo(percentualDescontoAtivo);
		
		
		parcelamentoDescontoAntiguidadeNovo.setQuantidadeMinimaMesesDebito(quantidadeMinimaMesesDebito);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoAtivo(percentualDescontoAtivo);
		parcelamentoDescontoAntiguidadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoAntiguidade.add(parcelamentoDescontoAntiguidadeNovo);
		
		atualizarParcelamentoPerfilActionForm.setQuantidadeMinimaMesesDebito("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoAntiguidade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoAntiguidade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		atualizarParcelamentoPerfilActionForm.setIdContaMotivoRevisao(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		
		//Ordena a coleção pela Qtde. Mínima Meses de Débito p/ Desconto
		Collections.sort((List) collectionParcelamentoDescontoAntiguidade, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoDescontoAntiguidade) a).getQuantidadeMinimaMesesDebito().toString()) ;
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoDescontoAntiguidade) b).getQuantidadeMinimaMesesDebito().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		//manda para a sessão a coleção de ParcelamentoDescontoAntiguidade
		sessao.setAttribute("collectionParcelamentoDescontoAntiguidade", collectionParcelamentoDescontoAntiguidade);

	}
	/**
	 * 
	 * @param atualizarParcelamentoPerfilActionForm
	 * @param sessao
	 * @param fachada
	 */
	private void adicionarParcelamentoDescontoInatividade(AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,
				HttpSession sessao,Fachada fachada){
		
		if (atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade() == null ||
				atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Qtde. Máxima Meses de Inatividade da Lig. de Água
					"atencao.required", null, " Qtde. Máxima Meses de Inatividade da Lig. de Água");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade())){
			throw new ActionServletException(
					// Qtde. Máxima Meses de Inatividade da Lig. de Água deve ser numerico
					"atencao.integer", null, " Qtde. Máxima Meses de Inatividade da Lig. de Água");
		}
		
		Collection<ParcelamentoDescontoInatividade> collectionParcelamentoDescontoInatividade = null;
		
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
			collectionParcelamentoDescontoInatividade = (Collection) sessao
		    .getAttribute("collectionParcelamentoDescontoInatividade");
		} else {
			collectionParcelamentoDescontoInatividade = new ArrayList();
		}
		    
		Integer quantidadeMaximaMesesInatividade = new Integer(atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade());
		
		BigDecimal percentualDescontoSemRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade());

		}
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade());
        	
		}		
		
		ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeNovo = 
								new ParcelamentoDescontoInatividade();
		
		if (collectionParcelamentoDescontoInatividade != null && !collectionParcelamentoDescontoInatividade.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
			ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoInatividade.iterator();
			
			while (iterator.hasNext()) {
				parcelamentoDescontoInatividadeAntigo = (ParcelamentoDescontoInatividade) iterator.next();
				
				//[FS0003] Verificar quantidade mínima meses de debito
				if (quantidadeMaximaMesesInatividade.equals
						(parcelamentoDescontoInatividadeAntigo.getQuantidadeMaximaMesesInatividade())){
					//Quantidade Máxima Meses de Inatividade de Ligação de Água já informada
					throw new ActionServletException(
					"atencao.qtde_maxima_meses_inatividade_ja_informado");
				}
			}
		}			
		
		verificarPercentualMaximo(percentualDescontoComRestabelecimento);
		verificarPercentualMaximo(percentualDescontoSemRestabelecimento);
		
		parcelamentoDescontoInatividadeNovo.setQuantidadeMaximaMesesInatividade(quantidadeMaximaMesesInatividade);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoInatividade.add(parcelamentoDescontoInatividadeNovo);
		
		atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		//Ordena a coleção pela Qtde. Máxima Meses de Inatividade da Lig. de Água
		Collections.sort((List) collectionParcelamentoDescontoInatividade, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoDescontoInatividade) a).getQuantidadeMaximaMesesInatividade().toString() );
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoDescontoInatividade) b).getQuantidadeMaximaMesesInatividade().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		//manda para a sessão a coleção de ParcelamentoDescontoInatividade
		sessao.setAttribute("collectionParcelamentoDescontoInatividade", collectionParcelamentoDescontoInatividade);
		
	}
	/**
	 * 
	 * @param atualizarParcelamentoPerfilActionForm
	 * @param sessao
	 */
	private void adicionarParcelamentoDescontoInatividadeAVista(
			 AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,HttpSession sessao){
	    	
    	if (atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista() == null ||
    			atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Qtde. Máxima Meses de Inatividade da Lig. de Água
					"atencao.required", null, " Qtde. Máxima Meses de Inatividade da Lig. de Água");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista())){
			throw new ActionServletException(
					// Qtde. Máxima Meses de Inatividade da Lig. de Água deve ser numerico
					"atencao.integer", null, " Qtde. Máxima Meses de Inatividade da Lig. de Água");
		 }
    	
    	Collection<ParcDesctoInativVista> collectionParcelamentoDescontoInatividadeAVista = null;
        
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
        	collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividadeAVista");
        } else {
        	collectionParcelamentoDescontoInatividadeAVista = new ArrayList();
        }
    	            
        Integer quantidadeMaximaMesesInatividade = new Integer(atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista());

        BigDecimal percentualDescontoSemRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista());

		}
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista());
        	
		}
        						
		
        ParcDesctoInativVista parcelamentoDescontoInatividadeNovo = new ParcDesctoInativVista();
		 
		if (collectionParcelamentoDescontoInatividadeAVista != null && !collectionParcelamentoDescontoInatividadeAVista.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
			ParcDesctoInativVista parcelamentoDescontoInatividadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoInatividadeAVista.iterator();
		
			while (iterator.hasNext()) {
			parcelamentoDescontoInatividadeAntigo = (ParcDesctoInativVista) iterator.next();
				
				//[FS0003] Verificar quantidade mínima meses de debito
				if (quantidadeMaximaMesesInatividade.equals
						(parcelamentoDescontoInatividadeAntigo.getQuantidadeMaximaMesesInatividade())){
					//Quantidade Máxima Meses de Inatividade de Ligação de Água já informada
					throw new ActionServletException(
					"atencao.qtde_maxima_meses_inatividade_ja_informado");
				}
			}
		}			

		verificarPercentualMaximo(percentualDescontoComRestabelecimento);
		verificarPercentualMaximo(percentualDescontoSemRestabelecimento);

		parcelamentoDescontoInatividadeNovo.setQuantidadeMaximaMesesInatividade(quantidadeMaximaMesesInatividade);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoInatividadeAVista.add(parcelamentoDescontoInatividadeNovo);

		atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividadeAVista("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividadeAVista("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividadeAVista("");
//			parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		
		//Ordena a coleção pela Qtde. Máxima Meses de Inatividade da Lig. de Água
		Collections.sort((List) collectionParcelamentoDescontoInatividadeAVista, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcDesctoInativVista) a).getQuantidadeMaximaMesesInatividade().toString() );
				Integer valorMinPrestacao2 = new Integer(((ParcDesctoInativVista) b).getQuantidadeMaximaMesesInatividade().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		 //manda para a sessão a coleção de ParcelamentoDescontoInatividadeAVista
        sessao.setAttribute("collectionParcelamentoDescontoInatividadeAVista", collectionParcelamentoDescontoInatividadeAVista);

    }
	/**
	 * 
	 * @param sessao
	 * @param httpServletRequest
	 */
	private void atualizaColecoesNaSessao(HttpSession sessao,
					HttpServletRequest httpServletRequest){
	
		// collectionParcelamentoDescontoAntiguidade
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null
		&& !sessao.getAttribute("collectionParcelamentoDescontoAntiguidade").equals("")) {
		
			Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
				.getAttribute("collectionParcelamentoDescontoAntiguidade");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoDescontoAntiguidade
			String vlSemRestAntiguidade = null;
			String vlComRestAntiguidade = null;
			String vlDescontoAtivo = null;
			
			
			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade
				.iterator();
			
			while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {
				ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade
						.next();
				long valorTempo = parcelamentoDescontoAntiguidade.getUltimaAlteracao()
						.getTime();
				vlSemRestAntiguidade = (String) httpServletRequest.getParameter("vlSemRestAntiguidade"
						+ valorTempo);
				vlComRestAntiguidade = httpServletRequest.getParameter("vlComRestAntiguidade"
						+ valorTempo);
				vlDescontoAtivo = httpServletRequest.getParameter("vlDescontoAtivo"
						+ valorTempo);
			
				// inseri essas variáveis no objeto ParcelamentoDescontoAntiguidade
				BigDecimal percentualDescontoSemRestabelecimentoAntiguidade  = null;
				if (vlSemRestAntiguidade != null 
						&& !vlSemRestAntiguidade.equals("")) {
					percentualDescontoSemRestabelecimentoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestAntiguidade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoAntiguidade = null;
				if (vlComRestAntiguidade != null 
						&& !vlComRestAntiguidade.equals("")) {
					percentualDescontoComRestabelecimentoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestAntiguidade);
				}
				
				BigDecimal percentualDescontoAtivoAntiguidade = null;
				if (vlDescontoAtivo != null 
						&& !vlDescontoAtivo.equals("")) {
					percentualDescontoAtivoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlDescontoAtivo);
				}
				
				verificarPercentualMaximo(percentualDescontoAtivoAntiguidade);
				verificarPercentualMaximo(percentualDescontoComRestabelecimentoAntiguidade);
				verificarPercentualMaximo(percentualDescontoSemRestabelecimentoAntiguidade);
			
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoAtivo(percentualDescontoAtivoAntiguidade);
			
			}
			
		}
			
		
		//collectionParcelamentoDescontoInatividade
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null
		&& !sessao.getAttribute("collectionParcelamentoDescontoInatividade").equals("")) {
		
			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
				.getAttribute("collectionParcelamentoDescontoInatividade");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;
			
			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade
				.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = 
						(ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade
						.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao()
						.getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividade"
						+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividade"
						+ valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null 
						&& !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null 
						&& !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}
				
				verificarPercentualMaximo(percentualDescontoComRestabelecimentoInatividade);
				verificarPercentualMaximo(percentualDescontoSemRestabelecimentoInatividade);
				
				parcelamentoDescontoInatividade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);
			}
		}	        	
		
    	//collectionParcelamentoDescontoInatividadeAVista
    	if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista").equals("")) {
	

			Collection collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividadeAVista.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcDesctoInativVista parcelamentoDescontoInatividade = 
						(ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao().getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividadeAVista"+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividadeAVista"+ valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}

				verificarPercentualMaximo(percentualDescontoComRestabelecimentoInatividade);
				verificarPercentualMaximo(percentualDescontoSemRestabelecimentoInatividade);
				
				parcelamentoDescontoInatividade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);


			}

        }	 
		
    	//collectionTipoDebitosHelper
    	if (sessao.getAttribute("collectionTipoDebitosHelper") != null && !sessao.getAttribute("collectionTipoDebitosHelper").equals("")) {

			Collection collectionTipoDebitosHelper = (Collection) sessao.getAttribute("collectionTipoDebitosHelper");
			// cria as variáveis para recuperar os parâmetros do request e jogar no objeto  ParcelamentoPerfilDebitos
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator<ParcelamentoPerfilDebitos> iterator = collectionTipoDebitosHelper.iterator();
			
			while (iterator.hasNext()) {
				ParcelamentoPerfilDebitos parcelamentoPerfilDebitos =  (ParcelamentoPerfilDebitos) iterator.next();
				
				long valorTempo = parcelamentoPerfilDebitos.getUltimaAlteracao().getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividadeAVista"+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividadeAVista"+ valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}
			}
        }	
	}
	/**
	 * 
	 * @param atualizarParcelamentoPerfilActionForm
	 * @param sessao
	 */
	private void adicionarParcelamentoPerfilDebitos(AtualizarParcelamentoPerfilActionForm form,HttpSession sessao){
	    	
		Collection<ParcelamentoPerfilDebitos> collectionParcelamentoPerfilDebitos = null;
        
        if (sessao.getAttribute("collectionTipoDebitosHelper") != null) {
        	collectionParcelamentoPerfilDebitos = (Collection) sessao.getAttribute("collectionTipoDebitosHelper");
        } else {
        	collectionParcelamentoPerfilDebitos = new ArrayList();
        }
        
        if ( form.getIdTipoDebito() == null || form.getIdTipoDebito().trim().equals("") ) {
        	throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Tipo de Débito");
        }
        
        if ( !Util.validarStringNumerica(form.getIdTipoDebito()) ) {
        	throw new ActionServletException("atencao.campo_aceita_apenas_numero", null, "Tipo de Débito");
        }
    	
        if (collectionParcelamentoPerfilDebitos != null && !collectionParcelamentoPerfilDebitos.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
        	ParcelamentoPerfilDebitos helperAntigo = null;
			Iterator<ParcelamentoPerfilDebitos> iterator = collectionParcelamentoPerfilDebitos.iterator();
		
			while (iterator.hasNext()) {
				
				helperAntigo = (ParcelamentoPerfilDebitos) iterator.next();
				
				if ( helperAntigo.getDebitoTipo().getId().equals(Integer.valueOf(form.getIdTipoDebito())) ) {
					//Quantidade Mínima Meses de Debito para Desconto já informada
					throw new ActionServletException("atencao.debito_tipo_existente");
				}
			}
		}
        
        
        FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        filtroDebitoTipo.adicionarParametro( new ParametroSimples(FiltroDebitoTipo.ID, form.getIdTipoDebito()));
        
        Collection<DebitoTipo> colecaoDebitoTipo = Fachada.getInstancia().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
        
        if ( colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty() ) {
	        
        	DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
        	
        	ParcelamentoPerfilDebitos parcelamentoPerfilDebitos = new ParcelamentoPerfilDebitos();
	        
	        
        	parcelamentoPerfilDebitos.setDebitoTipo(debitoTipo);
	        
	        if ( form.getPercentualDescontoPagamentoAVista() != null && !form.getPercentualDescontoPagamentoAVista().equals("") ) {
	        	if ( !Util.validarStringNumerica(form.getPercentualDescontoPagamentoAVista().replace(",", "")) ) {
	            	throw new ActionServletException("atencao.campo_aceita_apenas_numero", null, "Percentual de desconto para pagamento a vista");
	            } else {
	            	verificarPercentualMaximo(Util.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoPagamentoAVista()));
	            	parcelamentoPerfilDebitos.setPercentualDescontoAVista(Util.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoPagamentoAVista()));
	            }
	        } else {
	        	parcelamentoPerfilDebitos.setPercentualDescontoAVista(BigDecimal.ZERO);
	        }
	        
	        if ( form.getPercentualDescontoPagamentoParcelado() != null && !form.getPercentualDescontoPagamentoParcelado().equals("") ) {
	        	if ( !Util.validarStringNumerica(form.getPercentualDescontoPagamentoParcelado().replace(",", "")) ) {
	            	throw new ActionServletException("atencao.campo_aceita_apenas_numero", null, "Percentual de desconto para pagamento parcelado");
	            } else {
	            	verificarPercentualMaximo(Util.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoPagamentoParcelado()));
	            	parcelamentoPerfilDebitos.setPercentualDescontoParcelado(Util.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoPagamentoParcelado()));
	            }
	        } else {
	        	parcelamentoPerfilDebitos.setPercentualDescontoParcelado(BigDecimal.ZERO);
	        }
	        parcelamentoPerfilDebitos.setUltimaAlteracao(new Date());
	        
	        collectionParcelamentoPerfilDebitos.add(parcelamentoPerfilDebitos);
	        form.setIdTipoDebito("");
	        form.setDescricaoTipoDebito("");
	        form.setPercentualDescontoPagamentoParcelado("");
	        form.setPercentualDescontoPagamentoAVista("");
	        
        } else {
        	
        	throw new ActionServletException("atencao.registro_inexistente", null , "Tipo de Débito");
        }
        
        //manda para a sessão a coleção de collectionTipoDebitosHelper
        sessao.setAttribute("collectionTipoDebitosHelper", collectionParcelamentoPerfilDebitos);
    }
	 //[FS0010]-Verificar Percentual Máximo
    private void verificarPercentualMaximo(BigDecimal percentual){
    	
    	BigDecimal percentualMaximo = new BigDecimal("100");
    	
    	if (percentual.compareTo(percentualMaximo) == 1){
    		throw new ActionServletException(
					"atencao.percentual_maior_percentual_maximo");	
    	}
    	
    }
}