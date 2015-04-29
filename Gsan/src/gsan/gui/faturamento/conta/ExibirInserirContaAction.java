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
package gsan.gui.faturamento.conta;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.ContaMotivoInclusao;
import gsan.faturamento.conta.FiltroMotivoInclusaoConta;
import gsan.faturamento.debito.DebitoCobrado;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirInserirContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirConta");

        Fachada fachada = Fachada.getInstancia();
        
        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        
        /*
		 * Colocado por Raphael Rossiter em 29/03/2007
		 * Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		//httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
		httpServletRequest.setAttribute("indicadorTarifaCategoria", sistemaParametro.getIndicadorTarifaCategoria().toString());

        //Inst�ncia do formul�rio que est� sendo utilizado
        InserirContaActionForm inserirContaActionForm = (InserirContaActionForm) actionForm;
        String limparForm = httpServletRequest.getParameter("limparForm");
        
        
        //DEFINI��O QUE IR� AUXILIAR O RETORNO DOS POPUPS
        sessao.setAttribute("UseCase", "INSERIRCONTA");
        
        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
        
        Collection colecaoCategoria = new ArrayList();
		Collection colecaoSubcategoria = new ArrayList();
        
        //Removendo cole��es da sess�o
        if (limparForm != null && !limparForm.equalsIgnoreCase("")){
        	sessao.removeAttribute("colecaoCategoria");
        	sessao.removeAttribute("colecaoDebitoCobrado");
        	sessao.removeAttribute("colecaoAdicionarCategoria");
        	sessao.removeAttribute("colecaoAdicionarDebitoTipo");
        }
        
        
        
        Collection colecaoMotivoInclusaoConta, colecaoSituacaoLigacaoAgua, colecaoSituacaoLigacaoEsgoto;
        
        //Carregar a data corrente do sistema
        //====================================
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));
        
        //Data Corrente + 60 dias
        dataCorrente.add(Calendar.DATE, 60);
        httpServletRequest.setAttribute("dataAtual60", formatoData
        .format(dataCorrente.getTime()));
        
        /*
         * Costante que informa o ano limite para o campo anoMesReferencia da conta
         */
        httpServletRequest.setAttribute("anoLimite", ConstantesSistema.ANO_LIMITE);
        
        
        /*Motivo da inclus�o (Carregar cole��o) e remover as cole��es que ainda est�o na sess�o
        ====================================================================== */
        if (sessao.getAttribute("colecaoMotivoInclusaoConta") == null) {
        	
        	
        	FiltroMotivoInclusaoConta filtroMotivoInclusaoConta = new FiltroMotivoInclusaoConta(
        			FiltroMotivoInclusaoConta.MOTIVO_INCLUSAO_CONTA);

        	filtroMotivoInclusaoConta.adicionarParametro(new ParametroSimples(
        			FiltroMotivoInclusaoConta.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

        	colecaoMotivoInclusaoConta = fachada.pesquisar(filtroMotivoInclusaoConta,
        			ContaMotivoInclusao.class.getName());

            if (colecaoMotivoInclusaoConta == null
                    || colecaoMotivoInclusaoConta.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "MOTIVO_INCLUSAO_CONTA");
            } else {
                sessao.setAttribute("colecaoMotivoInclusaoConta",
                		colecaoMotivoInclusaoConta);
            }
        }
        
        
        /*Situa��o Liga��o �gua (Carregar cole��o)
        ====================================================================== */
        if (sessao.getAttribute("colecaoSituacaoLigacaoAgua") == null) {

        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(
        			FiltroLigacaoAguaSituacao.DESCRICAO);

        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
        			FiltroLigacaoAguaSituacao.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

        	colecaoSituacaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao,
        			LigacaoAguaSituacao.class.getName());

            if (colecaoSituacaoLigacaoAgua == null
                    || colecaoSituacaoLigacaoAgua.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "LIGACAO_AGUA_SITUACAO");
            } else {
                sessao.setAttribute("colecaoSituacaoLigacaoAgua",
                		colecaoSituacaoLigacaoAgua);
            }
        }
        
        
        /*Situa��o Liga��o Esgoto (Carregar cole��o)
         ====================================================================== */
        if (sessao.getAttribute("colecaoSituacaoLigacaoEsgoto") == null) {

        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(
        			FiltroLigacaoEsgotoSituacao.DESCRICAO);

        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
        			FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

        	colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
        			LigacaoEsgotoSituacao.class.getName());

            if (colecaoSituacaoLigacaoEsgoto == null
                    || colecaoSituacaoLigacaoEsgoto.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "LIGACAO_ESGOTO_SITUACAO");
            } else {
                sessao.setAttribute("colecaoSituacaoLigacaoEsgoto",
                		colecaoSituacaoLigacaoEsgoto);
            }
        }
        
        
        /*Pesquisar o im�vel a partir da matr�cula do im�vel
        ====================================================================== */
        String idImovel = inserirContaActionForm.getIdImovel();
        String reloadPage = httpServletRequest.getParameter("reloadPage");
        
        if (idImovel != null && !idImovel.equalsIgnoreCase("") &&
        	(reloadPage == null || reloadPage.equalsIgnoreCase(""))){
        	
        	FiltroImovel filtroImovel = new FiltroImovel();
        	
        	//Objetos que ser�o retornados pelo hobernate
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
        	/*filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial.codigo");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.numeroQuadra");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.descricao");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.descricao");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto.percentual");*/
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
            
        	//Realizando a pesquisa do im�vel a partir da matr�cula recebida
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	
        	
        	/*
        	 * Apenas im�veis que n�o foram exclu�dos poder�o inserir conta
        	 * (IMOV_ICEXCLUSAO = 1)
        	 */
        	filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, 
        	Imovel.IMOVEL_EXCLUIDO));
        	
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	//[FS0001] - Verificar exist�ncia da matr�cula do im�vel
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		/* throw new ActionServletException(
                        "atencao.imovel.inexistente"); */
        		
        		httpServletRequest.setAttribute("corInscricao", "exception");
        		inserirContaActionForm.setIdImovel("");
        		inserirContaActionForm.setInscricaoImovel("Matr�cula Inexistente");
        		httpServletRequest.setAttribute("nomeCampo", "idImovel");
        	}
        	else{
        	
	        	Imovel objetoImovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
	        	
	        	
	        	/*Pesquisar o cliente usu�rio do im�vel selecionado
	        	====================================================================== */
	        	FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
	        	
	        	//Objetos que ser�o retornados pelo hibernate.
	        	//filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.nome");
                filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
                
	        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
	        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO
	        	, ClienteRelacaoTipo.USUARIO));
	        	filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.FIM_RELACAO_MOTIVO));
	        	
	        	Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
	        	
	        	// Verifica exist�ncia do cliente usu�tio
	        	if (colecaoClienteImovel == null || colecaoClienteImovel.isEmpty()){
	        		throw new ActionServletException(
	                        "atencao.naocadastrado", null, "cliente do tipo usu�rio foi");
	        	}
	        	
	        	ClienteImovel objetoClienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
	        	
	        	//Carregando as informa��es do im�vel no formul�rio de exibi��o.
	        	inserirContaActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());
	        	inserirContaActionForm.setNomeClienteUsuario(objetoClienteImovel.getCliente().getNome());
	        	inserirContaActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());
	        	inserirContaActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
	        	inserirContaActionForm.setSituacaoAguaConta(String.valueOf(objetoImovel.getLigacaoAguaSituacao().getId().intValue()));
	        	inserirContaActionForm.setSituacaoEsgotoConta(String.valueOf(objetoImovel.getLigacaoEsgotoSituacao().getId().intValue()));
	        	if (objetoImovel.getLigacaoEsgoto() != null){
	        		if (objetoImovel.getLigacaoEsgoto().getPercentual() != null){
	        			inserirContaActionForm.setPercentualEsgoto(Util.formatarMoedaReal(objetoImovel.getLigacaoEsgoto().getPercentual()));
	        		}
	        	}
	        	
	        	//Inicializa o formul�rio
	        	inserirContaActionForm.setValorAgua("");
	        	inserirContaActionForm.setValorEsgoto("");
	        	inserirContaActionForm.setValorDebito("");
	        	inserirContaActionForm.setValorTotal("");
	        	inserirContaActionForm.setConsumoAgua("");
	        	inserirContaActionForm.setConsumoEsgoto("");
	        	
	        	
	        	if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
	        		
	        		//[UC0108] - Quantidade de economias por categoria
		        	colecaoSubcategoria = fachada.obterQuantidadeEconomiasSubCategoria(objetoImovel.getId());
		        	
		        	sessao.setAttribute("colecaoSubcategoria", colecaoSubcategoria);
	        	}
	        	else{
	        		
	        		//[UC0108] - Quantidade de economias por categoria
		        	colecaoCategoria = fachada.obterQuantidadeEconomiasCategoria(objetoImovel);
		        	
		        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
	        	}	        	
	        	
	        	
	        	
	        	//Remove a cole��o de debitos que foi selecionada com a matricula do im�vel anterior
	        	//sessao.removeAttribute("colecaoDebitoCobrado");
	        	
	        	//Colocando o faco pra o campo de ano m�s referencia
	        	httpServletRequest.setAttribute("nomeCampo",
	            "mesAnoConta");
        	}
        }
        if(sessao.getAttribute("colecaoCategoria") != null)
        {
        	Collection colecao = (Collection) sessao.getAttribute("colecaoCategoria");
        	Iterator iteratorColecaoCategoria = colecao.iterator();
        	
        	Categoria categoria = null;
        	String quantidadeEconomia = null;
        	int valor = 0;
    		while (iteratorColecaoCategoria.hasNext()) {
    			categoria = (Categoria) iteratorColecaoCategoria.next();
    			// teste para ver se existe na p�gina alguma categoria 
    			valor++;
    			if (requestMap.get("categoria"
    					+ categoria.getId()) != null) {

    				quantidadeEconomia = (requestMap.get("categoria" + categoria.getId()))[0];

    				if (quantidadeEconomia == null
    						|| quantidadeEconomia.equalsIgnoreCase("")) {
    					throw new ActionServletException(
    							"atencao.required", null,
    							"Quantidade de Economias");
    				}

    				categoria.setQuantidadeEconomiasCategoria(new Integer(quantidadeEconomia));
    			}
        	}
    		if(valor == 0)
    		{
    			sessao.setAttribute("existeColecao","nao");
    		}else{
    			sessao.removeAttribute("existeColecao");
			}
        }
        if(sessao.getAttribute("colecaoSubcategoria") != null)
        {
        	Collection colecao = (Collection) sessao.getAttribute("colecaoSubcategoria");
        	Iterator iteratorColecaoSubcategoria = colecao.iterator();
        	
        	Subcategoria subcategoria = null;
        	String quantidadeEconomia = null;
        	int valor = 0;
    		while (iteratorColecaoSubcategoria.hasNext()) {
    			subcategoria = (Subcategoria) iteratorColecaoSubcategoria.next();
    			// teste para ver se existe na p�gina alguma categoria 
    			valor++;
    			if (requestMap.get("subcategoria"
    					+ subcategoria.getId()) != null) {

    				quantidadeEconomia = (requestMap.get("subcategoria" + subcategoria.getId()))[0];

    				if (quantidadeEconomia == null
    						|| quantidadeEconomia.equalsIgnoreCase("")) {
    					throw new ActionServletException(
    							"atencao.required", null,
    							"Quantidade de Economias");
    				}

    				subcategoria.setQuantidadeEconomias(new Integer(quantidadeEconomia));
    			}
        	}
    		if(valor == 0)
    		{
    			sessao.setAttribute("existeColecao","nao");
    		}else{
    			sessao.removeAttribute("existeColecao");
			}
        }
        if(sessao.getAttribute("colecaoDebitoCobrado") != null)
        {
        	Collection colecaoDebito = (Collection) sessao.getAttribute("colecaoDebitoCobrado");
        	Iterator iteratorColecaoDebito = colecaoDebito.iterator();
        	
        	DebitoCobrado debitoCobrado = null;
        	String valor = null;
        	BigDecimal valor2 = new BigDecimal ("0.00"); 
        	
        	while (iteratorColecaoDebito.hasNext()) {
    			debitoCobrado = (DebitoCobrado) iteratorColecaoDebito.next();
    			// valor minimo
    			if (requestMap.get("debitoCobrado"
    					+ GcomAction.obterTimestampIdObjeto(debitoCobrado)) != null) {

    				valor = (requestMap.get("debitoCobrado" + GcomAction.obterTimestampIdObjeto(debitoCobrado)))[0];
    				
    				if (valor == null
    						|| valor.equalsIgnoreCase("")) {
    					throw new ActionServletException(
    							"atencao.required", null,
    							"D�bito Cobrado");
    				}
    				else{
    					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
    				}
    				
    				debitoCobrado.setValorPrestacao(valor2);
    			}
        	}
        }

        if (requestMap.get("percentualEsgoto") != null){
        	String percentualEsgoto = (String) requestMap.get("percentualEsgoto")[0];
        	inserirContaActionForm.setPercentualEsgoto(percentualEsgoto);
        	httpServletRequest.setAttribute("percentualEsgoto",percentualEsgoto);
        }
        
        
        //Limpando os campos ap�s remo��o ou inser��o de categorias ou d�bitos
        if (reloadPage != null && !reloadPage.equalsIgnoreCase("")){
        	
        	inserirContaActionForm.setValorAgua("");
        	inserirContaActionForm.setValorEsgoto("");
        	inserirContaActionForm.setValorDebito("");
        	inserirContaActionForm.setValorTotal("");
        }
        
        return retorno;
    }

}

