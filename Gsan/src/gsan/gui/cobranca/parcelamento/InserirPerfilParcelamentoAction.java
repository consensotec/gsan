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

import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.ImovelSituacaoTipo;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cobranca.FiltroResolucaoDiretoria;
import gsan.cobranca.ResolucaoDiretoria;
import gsan.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gsan.cobranca.parcelamento.ParcDesctoInativVista;
import gsan.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gsan.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gsan.cobranca.parcelamento.ParcelamentoPerfil;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por inserir um Perfil de Parcelamento
 * 
 * @author Vivianne Sousa
 * @created 02/05/2006
 */

public class InserirPerfilParcelamentoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    //Obtém a instância da fachada
    Fachada fachada = Fachada.getInstancia();

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm)actionForm;

        Collection collectionParcelamentoQuantidadeReparcelamentoHelper = null;	       
		Collection collectionParcelamentoDescontoInatividade = null;	       
		Collection collectionParcelamentoDescontoAntiguidade = null;	       
		Collection collectionParcelamentoDescontoInatividadeAVista = null;
		Collection collectionTipoDebitosHelper = null;
		
		//collectionParcelamentoQuantidadeReparcelamentoHelper
        if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null) {
        	collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
        }
        
    	//collectionParcelamentoDescontoInatividade
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
        	collectionParcelamentoDescontoInatividade = (Collection) sessao.getAttribute("collectionParcelamentoDescontoInatividade");
        }
        
    	//collectionParcelamentoDescontoAntiguidade
        if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
        	collectionParcelamentoDescontoAntiguidade = (Collection) sessao.getAttribute("collectionParcelamentoDescontoAntiguidade");
        }
        
        //collectionParcelamentoDescontoInatividade
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
        	collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
        }
        
        //collectionTipoDebitosHelper
  		if (sessao.getAttribute("collectionTipoDebitosHelper") != null) {
  			collectionTipoDebitosHelper = (Collection) sessao.getAttribute("collectionTipoDebitosHelper");
        }
        
        //Percentual Tarifa Minima Prestacao
  		String percentualTarifaMinimaPrestacao = null ;
  		if (parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao() != null
  				&& !parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().equalsIgnoreCase("")){
  			percentualTarifaMinimaPrestacao = parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().toString().replace(",", ".");
  		}
  		
      	//Perfil Imovel
        String idImovelPerfil = null;
        if ( parcelamentoPerfilActionForm.getImovelPerfil() != null && !parcelamentoPerfilActionForm.getImovelPerfil().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ){
        	idImovelPerfil = parcelamentoPerfilActionForm.getImovelPerfil();	
        }
  		
        ImovelPerfil imovelPerfil = null;
        if(idImovelPerfil != null && !idImovelPerfil.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
         	imovelPerfil = new ImovelPerfil();
         	imovelPerfil.setId(new Integer (idImovelPerfil));
 		}

        //Subcategoria
        String idSubcategoria = null;
        if (parcelamentoPerfilActionForm.getSubcategoria() != null && !parcelamentoPerfilActionForm.getSubcategoria().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ){
        	idSubcategoria = parcelamentoPerfilActionForm.getSubcategoria();
        }
        
        //Percentual desconto Acrescimo
		String percentualDescontoAcrescimo = null ;
		if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo() != null && !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo().equalsIgnoreCase("")){
			percentualDescontoAcrescimo = parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo().toString().replace(",", ".");
		}

		//Metodo responsavle por montar o objeto com os parametros informados na tela.
		ParcelamentoPerfil parcelamentoPerfilNova = montarObjeto(parcelamentoPerfilActionForm, sessao, httpServletRequest);
		
		
        validacaoFinal( parcelamentoPerfilActionForm.getResolucaoDiretoria(),parcelamentoPerfilActionForm.getImovelSituacaoTipo(),idImovelPerfil ,
        		idSubcategoria ,percentualDescontoAcrescimo ,httpServletRequest,
        		collectionParcelamentoQuantidadeReparcelamentoHelper,
        		collectionParcelamentoDescontoAntiguidade,
			    collectionParcelamentoDescontoInatividade,
			    percentualTarifaMinimaPrestacao,
			    collectionParcelamentoDescontoInatividadeAVista);
		
        Integer idPerfilParcelamento = fachada.inserirPerfilParcelamento(parcelamentoPerfilNova,
        		collectionParcelamentoQuantidadeReparcelamentoHelper,
        		collectionParcelamentoDescontoInatividade,
        		collectionParcelamentoDescontoAntiguidade,
        		this.getUsuarioLogado(httpServletRequest),
        		collectionParcelamentoDescontoInatividadeAVista,
        		collectionTipoDebitosHelper);
        
    	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
    	sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
    	sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
    	sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
    	sessao.removeAttribute("collectionTipoDebitosHelper");
    	parcelamentoPerfilActionForm.setTabela("");
    	
    	FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
		filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO,parcelamentoPerfilActionForm.getResolucaoDiretoria()));
		
        Collection<ResolucaoDiretoria> collectionResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
        
        String numeroResolucaoDiretoria = ((ResolucaoDiretoria)Util.retonarObjetoDeColecao(collectionResolucaoDiretoria)).getNumeroResolucaoDiretoria();
    	
		montarPaginaSucesso(httpServletRequest, "Perfil de Parcelamento da RD de número " + numeroResolucaoDiretoria + " inserido com sucesso.",
				"Inserir outro Perfil de Parcelamento",
                "exibirInserirPerfilParcelamentoAction.do?desfazer=S",
				"exibirAtualizarPerfilParcelamentoAction.do?idRegistroInseridoAtualizar="
						+ idPerfilParcelamento,
				"Atualizar Perfil de Parcelamento Inserido");
        
        //devolve o mapeamento de retorno
        return retorno;

    }
    
    /**
     * 
     * @param parcelamentoPerfilActionForm
     * @param sessao
     * @param httpServletRequest
     * @param collectionParcelamentoQuantidadeReparcelamentoHelper
     * @param collectionParcelamentoDescontoInatividade
     * @param collectionParcelamentoDescontoAntiguidade
     * @param collectionParcelamentoDescontoInatividadeAVista
     * @return
     */
    private ParcelamentoPerfil montarObjeto( ParcelamentoPerfilActionForm parcelamentoPerfilActionForm, HttpSession sessao, HttpServletRequest httpServletRequest) {
    	

        String idResolucaoDiretoria = parcelamentoPerfilActionForm.getResolucaoDiretoria();
        String idImovelSituacaoTipo = parcelamentoPerfilActionForm.getImovelSituacaoTipo();
		
    	//Perfil Imovel
        String idImovelPerfil = null;
        if ( parcelamentoPerfilActionForm.getImovelPerfil() != null && !parcelamentoPerfilActionForm.getImovelPerfil().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ){
        	idImovelPerfil = parcelamentoPerfilActionForm.getImovelPerfil();	
        }

        //Subcategoria
        String idSubcategoria = null;
        if (parcelamentoPerfilActionForm.getSubcategoria() != null && !parcelamentoPerfilActionForm.getSubcategoria().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ){
        	idSubcategoria = parcelamentoPerfilActionForm.getSubcategoria();
        }
        
        //Categoria
        String idCategoria = null;
        if (parcelamentoPerfilActionForm.getCategoria() != null && !parcelamentoPerfilActionForm.getCategoria().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) ){
        	idCategoria = parcelamentoPerfilActionForm.getCategoria();
        }
		
       
		
		//Percentual Desconto Acrescimo Pagamento a vista
		String percentualDescontoAcrescimoPagamentoAVista = null ;
		if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista().equalsIgnoreCase("")) {
			percentualDescontoAcrescimoPagamentoAVista = parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimoPagamentoAVista);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "Desconto Debito Acrescimo Pagamento à Vista");
			}
		}
		
		
		//Percentual Desconto Debito Pagamento a Vista
		String percentualDescontoDebitoPagamentoAVista = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoAVista() != null 
				&& !parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoAVista().equals("")){
			percentualDescontoDebitoPagamentoAVista = parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoAVista().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoAVista);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "Desconto Debito Pagamento à Vista");
			}
		}
		
		//Percentual Desconto Debito Pagamento Parcelado
		String percentualDescontoDebitoPagamentoParcelado = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoParcelado() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoParcelado().equals("")){
			percentualDescontoDebitoPagamentoParcelado = parcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoParcelado().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoParcelado);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "Desconto Debito Pagamento Parcelado");
			}
		}
		
		//Limite Vencimento Conta Pagamento A vista
		String limiteVencimentoContaPagamentoAVista = null;
		if(parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista() != null
				&& !parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista().equals("")){
			limiteVencimentoContaPagamentoAVista = parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista();
			
			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			// joga em dataInicial a parte da data
			String dataInicial = dataFormato.format(new Date());
			
			Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataInicial);
			Integer dataLimiteVencimento = Util.formatarDiaMesAnoComBarraParaAnoMesDia(limiteVencimentoContaPagamentoAVista);
			
			if(dataLimiteVencimento < dataAtual){
				throw new ActionServletException("atencao.data_inferior_que_atual", null, "Limite Vencimento Conta Pagamento à Vista");
			}
		}
		
		String limiteVencimentoContaPagamentoParcelado = null;
		if(parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado() != null && !parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado().equals("")){
			
			limiteVencimentoContaPagamentoParcelado = parcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado();
			
			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			// joga em dataInicial a parte da data
			String dataInicial = dataFormato.format(new Date());
			
			Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataInicial);
			Integer dataLimiteVencimento = Util.formatarDiaMesAnoComBarraParaAnoMesDia(limiteVencimentoContaPagamentoParcelado);
			
			if(dataLimiteVencimento < dataAtual){
				throw new ActionServletException("atencao.data_inferior_que_atual", null, "Limite Vencimento Conta Pagamento Parcelado");
			}
		}
		
		if(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior() != null  && !parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior().equals("") && 
				parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior() != null  && !parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior().equals("")){
				
			Integer anoMesInferior = Util.formatarMesAnoComBarraParaAnoMes(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior());
			Integer anoMesSuperior = Util.formatarMesAnoComBarraParaAnoMes(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior());
			
			if(anoMesInferior > anoMesSuperior){
				throw new ActionServletException("atencao.mes_ano_inicial_maior_mes_ano_final");
			}
		}
		
		String percentualDescontoMulta = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoMulta() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoMulta().equals("")){
			percentualDescontoMulta = parcelamentoPerfilActionForm.getPercentualDescontoMulta().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoMulta);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acréscimos por Impontualidade Multa");
			}
		}
		
		String percentualDescontoJuros = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoJuros() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoJuros().equals("")){
			percentualDescontoJuros = parcelamentoPerfilActionForm.getPercentualDescontoJuros().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoJuros);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acréscimos por Impontualidade Juros");
			}
		}
		
		String percentualDescontoAtualizacaoMonetaria = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoAtualizacaoMonetaria() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAtualizacaoMonetaria().equals("")){
			percentualDescontoAtualizacaoMonetaria = parcelamentoPerfilActionForm.getPercentualDescontoAtualizacaoMonetaria().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoAtualizacaoMonetaria);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acréscimos por Impontualidade de Atualização Monetária");
			}
		}
		
		String percentualDescontoPagamentoAVistaMulta = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaMulta() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaMulta().equals("")){
			percentualDescontoPagamentoAVistaMulta = parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaMulta().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaMulta);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acréscimos por Impontualidade de Multa para Pagamento à Vista");
			}
		}
		
		String percentualDescontoPagamentoAVistaJuros = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaJuros() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaJuros().equals("")){
			percentualDescontoPagamentoAVistaJuros = parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaJuros().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaJuros);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acréscimos por Impontualidade de Juros para Pagamento à Vista");
			}
		}
		
		String percentualDescontoPagamentoAVistaAtuMonetaria = null;
		if(parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaAtuMonetaria() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaAtuMonetaria().equals("")){
			percentualDescontoPagamentoAVistaAtuMonetaria = parcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaAtuMonetaria().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaAtuMonetaria);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acréscimos por Impontualidade de Atualização Monetária para Pagamento à Vista");
			}
		}
		
		String percentualValorContaConsumoMedioPrestacao = null;
		if(parcelamentoPerfilActionForm.getPercentualValorContaConsumoMedioPrestacao() != null
				&& !parcelamentoPerfilActionForm.getPercentualValorContaConsumoMedioPrestacao().equals("")){
			percentualValorContaConsumoMedioPrestacao = parcelamentoPerfilActionForm.getPercentualValorContaConsumoMedioPrestacao().toString().replace(",", ".");
			
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualValorContaConsumoMedioPrestacao);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "do Valor da Conta - Consumo Médio");
			}
		}
             
        atualizaColecoesNaSessao(sessao,httpServletRequest);
        
    	
        
        
        ParcelamentoPerfil parcelamentoPerfilNova =  new ParcelamentoPerfil();
        
        
        if(idResolucaoDiretoria != null && !idResolucaoDiretoria.equals("")){
        	ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();
        	resolucaoDiretoria.setId(new Integer (idResolucaoDiretoria));
        	parcelamentoPerfilNova.setResolucaoDiretoria(resolucaoDiretoria);
		}
        
        
        if(idImovelSituacaoTipo != null && !idImovelSituacaoTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	ImovelSituacaoTipo imovelSituacaoTipo = new ImovelSituacaoTipo();
        	imovelSituacaoTipo.setId(new Integer (idImovelSituacaoTipo));
        	parcelamentoPerfilNova.setImovelSituacaoTipo(imovelSituacaoTipo);
		}

        ImovelPerfil imovelPerfil = null;
        if(idImovelPerfil != null && !idImovelPerfil.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	imovelPerfil = new ImovelPerfil();
        	imovelPerfil.setId(new Integer (idImovelPerfil));
		}
        parcelamentoPerfilNova.setImovelPerfil(imovelPerfil);
        
        Subcategoria subcategoria = null;
        if(idSubcategoria != null && !idSubcategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	subcategoria = new Subcategoria();
        	subcategoria.setId(new Integer (idSubcategoria));
		}
        parcelamentoPerfilNova.setSubcategoria(subcategoria);
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo().equalsIgnoreCase("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getPercentualDescontoAcrescimo());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimo(percentual);	
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimo(new BigDecimal(0));
        }
        
        if (percentualDescontoAcrescimoPagamentoAVista != null){
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimoPagamentoAVista);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVista(percentual);	
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVista(new BigDecimal(0));
        }
                
        if (parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao() != null
				&& !parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().equalsIgnoreCase("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualTarifaMinimaPrestacao(percentual);	
        }else{
        	parcelamentoPerfilNova.setPercentualTarifaMinimaPrestacao(new BigDecimal(0));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido() != null
         		 && !parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido().equals("")){
         	parcelamentoPerfilNova.setIndicadorChequeDevolvido(new Short(parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido()));
       }else{
   		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Não parcelar com cheque devolvido");
       }
        
        if (parcelamentoPerfilActionForm.getConsumoMinimo() != null && !parcelamentoPerfilActionForm.getConsumoMinimo().equals("")){
        	parcelamentoPerfilNova.setNumeroConsumoMinimo(new Integer(parcelamentoPerfilActionForm.getConsumoMinimo()));
        }
        
        if (parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio() != null && !parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio().equals("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualVariacaoConsumoMedio(percentual);
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta() != null 
        	&& !parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta().equals("")){
          	parcelamentoPerfilNova.setIndicadorSancoesUnicaConta(new Short(parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta()));
        }else{
    		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Não parcelar com sanções em mais de uma conta");
        }
        
        Categoria categoria = null;
        if(idCategoria != null && !idCategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	categoria = new Categoria();
        	categoria.setId(new Integer (idCategoria));
		}
        parcelamentoPerfilNova.setCategoria(categoria);
        
        if (parcelamentoPerfilActionForm.getNumeroConsumoEconomia() != null 
        	&& !parcelamentoPerfilActionForm.getNumeroConsumoEconomia().trim().equals("")){
        	parcelamentoPerfilNova.setNumeroConsumoEconomia(new Integer(parcelamentoPerfilActionForm.getNumeroConsumoEconomia()));
        }
        
        if (parcelamentoPerfilActionForm.getNumeroAreaConstruida() != null 
        		&& !parcelamentoPerfilActionForm.getNumeroAreaConstruida().trim().equals("")){
        	parcelamentoPerfilNova.setNumeroAreaConstruida(Util.formatarMoedaRealparaBigDecimal(
        		parcelamentoPerfilActionForm.getNumeroAreaConstruida()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial() != null
         		 && !parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial().equals("")){
         	parcelamentoPerfilNova.setIndicadorRetroativoTarifaSocial(
         		new Short(parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial()));
        }else{
	   		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de retroativo de tarifa social");
        }
        
        if (parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior() != null 
        		&& !parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior().trim().equals("")){
        	parcelamentoPerfilNova.setAnoMesReferenciaLimiteInferior(new Integer(
        			Util.formatarMesAnoParaAnoMesSemBarra(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior())));
        } 
        
        if (parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior() != null && 
        		!parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior().trim().equals("")){
        	parcelamentoPerfilNova.setAnoMesReferenciaLimiteSuperior(new Integer(
        			Util.formatarMesAnoParaAnoMesSemBarra(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior())));
        } 
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoAVista() != null && 
        		!parcelamentoPerfilActionForm.getPercentualDescontoAVista().trim().equals("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(
        			parcelamentoPerfilActionForm.getPercentualDescontoAVista());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoAVista(percentual);
        }
        
        if (parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura() != null && 
        		!parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura().trim().equals("")){
        	parcelamentoPerfilNova.setParcelaQuantidadeMinimaFatura(
        			new Integer(parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima() != null
        		 && !parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima().equals("")){
        	parcelamentoPerfilNova.setIndicadorAlertaParcelaMinima(
        			new Short(parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima()));
        }else{
	   		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de alerta de parcela mínima");
        }
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoSancao() != null && 
        		!parcelamentoPerfilActionForm.getPercentualDescontoSancao().trim().equals("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(
        			parcelamentoPerfilActionForm.getPercentualDescontoSancao());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoSancao(percentual);
        }
        
        if (parcelamentoPerfilActionForm.getQuantidadeEconomias()  != null && 
        		!parcelamentoPerfilActionForm.getQuantidadeEconomias().trim().equals("")){
        	parcelamentoPerfilNova.setQuantidadeEconomias(new Integer(parcelamentoPerfilActionForm.getQuantidadeEconomias()));
        }
        
        if (parcelamentoPerfilActionForm.getCapacidadeHidrometro()  != null && 
        		!parcelamentoPerfilActionForm.getCapacidadeHidrometro().trim().equals("")){
        	parcelamentoPerfilNova.setCapacidadeHidrometro(
        			new Short(parcelamentoPerfilActionForm.getCapacidadeHidrometro()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorEntradaMinima() != null
        		&& !parcelamentoPerfilActionForm.getIndicadorEntradaMinima().equals("")){
        	parcelamentoPerfilNova.setIndicadorEntradaMinima(
        			new Short(parcelamentoPerfilActionForm.getIndicadorEntradaMinima()));
       }else{
	   		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de entrada mínima");
       }
        
        if (parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento()  != null && 
        		!parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento().trim().equals("")){
        	parcelamentoPerfilNova.setQuantidadeMaximaReparcelamento(
        			new Integer(parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento()));
        }

        if (parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()  != null && 
        		!parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista().trim().equals("")){
        	parcelamentoPerfilNova.setDataLimiteDescontoPagamentoAVista(
        			Util.converteStringParaDate(parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()));
        }
        
        //Inclusão de novos parâmetros
        if(percentualDescontoDebitoPagamentoAVista != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoAVista);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoDebitoPagamentoAVista(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoDebitoPagamentoAVista(new BigDecimal(0));
        }
        
        if(percentualDescontoDebitoPagamentoParcelado != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoParcelado);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoDebitoPagamentoParcelado(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoDebitoPagamentoParcelado(new BigDecimal(0));
        }
        
        if(limiteVencimentoContaPagamentoAVista != null){
        	parcelamentoPerfilNova.setDataLimiteVencimentoContaAVista(Util.converteStringParaDate(limiteVencimentoContaPagamentoAVista));
        }else{
        	parcelamentoPerfilNova.setDataLimiteVencimentoContaAVista(null);
        }
        
        if(limiteVencimentoContaPagamentoParcelado != null){
        	parcelamentoPerfilNova.setDataLimiteVencimentoContaParcelado(Util.converteStringParaDate(limiteVencimentoContaPagamentoParcelado));
        }else{
        	parcelamentoPerfilNova.setDataLimiteVencimentoContaParcelado(null);
        }
        
        if(percentualDescontoMulta != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoMulta);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoMulta(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoMulta(new BigDecimal(0));
        }
        
        if(percentualDescontoJuros != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoJuros);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoJuros(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoJuros(new BigDecimal(0));
        }
        
        if(percentualDescontoAtualizacaoMonetaria != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAtualizacaoMonetaria);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoAtualizacaoMonetaria(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoAtualizacaoMonetaria(new BigDecimal(0));
        }
        
        if(percentualDescontoPagamentoAVistaMulta != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaMulta);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVistaMulta(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVistaMulta(new BigDecimal(0));
        }
        
        if(percentualDescontoPagamentoAVistaJuros != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaJuros);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVistaJuros(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVistaJuros(new BigDecimal(0));
        }
        
        if(percentualDescontoPagamentoAVistaAtuMonetaria != null){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaAtuMonetaria);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVistaAtuMonetaria(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVistaAtuMonetaria(new BigDecimal(0));
        }
        
        if (parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaPagamentoAVista() != null  
        		&& !parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaPagamentoAVista().trim().equals("")){
        	parcelamentoPerfilNova.setAnoMesReferenciaLimiteContaPagamentoAVista(new Integer(
        			Util.formatarMesAnoParaAnoMesSemBarra(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaPagamentoAVista())));
        } 
        
        if (parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaParcelada() != null  
        		&& !parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaParcelada().trim().equals("")){
        	parcelamentoPerfilNova.setAnoMesReferenciaLimiteContaParcelada(new Integer(
        			Util.formatarMesAnoParaAnoMesSemBarra(parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaParcelada())));
        }
        
        
        if( percentualValorContaConsumoMedioPrestacao != null ) {
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualValorContaConsumoMedioPrestacao);
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualValorContaConsumoMedioPrestacao(percentual);
        }else{
        	parcelamentoPerfilNova.setPercentualValorContaConsumoMedioPrestacao(new BigDecimal(0));
        }
        
        parcelamentoPerfilNova.setUltimaAlteracao(new Date());
        
        return parcelamentoPerfilNova;
    }
    
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
			
			
			Iterator iteratorParcelamentoDescontoAntiguidade = 
				collectionParcelamentoDescontoAntiguidade.iterator();
			
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
				if (vlSemRestAntiguidade != null && !vlSemRestAntiguidade.equals("")) {
					percentualDescontoSemRestabelecimentoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlSemRestAntiguidade);
					verificarPercentualMaximo(percentualDescontoSemRestabelecimentoAntiguidade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoAntiguidade = null;
				if (vlComRestAntiguidade != null && !vlComRestAntiguidade.equals("")) {
					percentualDescontoComRestabelecimentoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlComRestAntiguidade);
					verificarPercentualMaximo(percentualDescontoComRestabelecimentoAntiguidade);
				}
				
				BigDecimal percentualDescontoAtivoAntiguidade = null;
				if (vlDescontoAtivo != null && !vlDescontoAtivo.equals("")) {
					percentualDescontoAtivoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlDescontoAtivo);
					verificarPercentualMaximo(percentualDescontoAtivoAntiguidade);
				}
				
				parcelamentoDescontoAntiguidade.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.setPercentualDescontoAtivo(percentualDescontoAtivoAntiguidade);
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
			
			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
			
				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = 
				(ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao()
					.getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividade"
					+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividade"
					+ valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
					verificarPercentualMaximo(percentualDescontoSemRestabelecimentoInatividade);
				}
			
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
					verificarPercentualMaximo(percentualDescontoComRestabelecimentoInatividade);
				}
				
				parcelamentoDescontoInatividade.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);
			}
		}	        	
    }

    /**
     * 
     * @param numeroResolucaoDiretoria
     * @param imovelSituacaoTipo
     * @param imovelPerfil
     * @param subcategoria
     * @param percentualDescontoAcrescimo
     * @param httpServletRequest
     * @param collectionParcelamentoQuantidadeReparcelamentoHelper
     * @param collectionParcelamentoDescontoAntiguidade
     * @param collectionParcelamentoDescontoInatividade
     * @param percentualTarifaMinimaPrestacao
     * @param collectionParcelamentoDescontoInatividadeAVista
     */
    private void validacaoFinal(String numeroResolucaoDiretoria,String imovelSituacaoTipo,String imovelPerfil ,String subcategoria ,
								String percentualDescontoAcrescimo ,HttpServletRequest httpServletRequest,Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
							    Collection collectionParcelamentoDescontoAntiguidade,Collection collectionParcelamentoDescontoInatividade,String percentualTarifaMinimaPrestacao,
							    Collection collectionParcelamentoDescontoInatividadeAVista){
							    	
    	if ( !Util.verificarNaoVazio(numeroResolucaoDiretoria)){
    		httpServletRequest.setAttribute("nomeCampo","numeroResolucaoDiretoria");
    		// Informe Numero da RD.
    		throw new ActionServletException("atencao.numero_rd_nao_informado");
    	}
    	
		if ((imovelSituacaoTipo == null)
				|| (imovelSituacaoTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","imovelSituacaoTipo");
			//Informe Tipo da Situação do Imóvel
			throw new ActionServletException("atencao.tipo_situacao_imovel_nao_informado");
		}
	
		
		if (percentualTarifaMinimaPrestacao == null || percentualTarifaMinimaPrestacao.equalsIgnoreCase("")){
    		httpServletRequest.setAttribute("nomeCampo","percentualTarifaMinimaPrestacao");
    		//Informe  Percentual da Tarifa Mínima para Cálculo do Valor Mínimo da Prestação
    		throw new ActionServletException("atencao.required", null, " Percentual da Tarifa Mínima para Cálculo do Valor Mínimo da Prestação");
    	}
		
    	// [FS0008]Verificar existência do perfil de parcelamento
    	FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.RESOLUCAO_DIRETORIA_ID, numeroResolucaoDiretoria));
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.IMOVEL_SITUACAO_TIPO_ID, imovelSituacaoTipo));
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
    	
    	if (imovelPerfil == null){
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroNulo(FiltroParcelamentoPerfil.IMOVEL_PERFIL_ID));	
    	}else{
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.IMOVEL_PERFIL_ID, imovelPerfil));
    	}
    
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
    	if (subcategoria == null){
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroNulo(FiltroParcelamentoPerfil.SUBCATEGORIA_ID));
    	}else{
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.SUBCATEGORIA_ID, subcategoria));
    	}
    	
		Collection	colecaoParcelamentoPerfil = fachada.pesquisar(filtroParcelamentoPerfil,	ParcelamentoPerfil.class.getName());

		if (colecaoParcelamentoPerfil != null && !colecaoParcelamentoPerfil.isEmpty()) {
			throw new ActionServletException("atencao.perfil_parcelamento_ja_existe");
		}
		
		
		if (collectionParcelamentoQuantidadeReparcelamentoHelper == null ||
				collectionParcelamentoQuantidadeReparcelamentoHelper.isEmpty()){
			//Informe os dados de Reparcelamento Consecutivo
			throw new ActionServletException("atencao.required", null, "Reparcelamento Consecutivo");
		}else{
			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();

			 while (iterator.hasNext()) {
				
				 ParcelamentoQuantidadeReparcelamentoHelper  parcelamentoQuantidadeReparcelamentoHelper = 
					 			(ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
				 
				Collection collectionParcelamentoQuantidadePrestacaoHelper = 
					parcelamentoQuantidadeReparcelamentoHelper.getCollectionParcelamentoQuantidadePrestacaoHelper();
				 
				if (collectionParcelamentoQuantidadePrestacaoHelper == null ||
						collectionParcelamentoQuantidadePrestacaoHelper.isEmpty()){
					//Informações do Parcelamento por Quantidade de Reparcelamentos deve ser informado
					throw new ActionServletException("atencao.campo.informado", null, "Informações do Parcelamento por Quantidade de Reparcelamentos");
				}
			 }
		}
		
		if (collectionParcelamentoDescontoAntiguidade != null &&
				!collectionParcelamentoDescontoAntiguidade.isEmpty()){
			
			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade.iterator();

			 while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {
				
				 ParcelamentoDescontoAntiguidade  parcelamentoDescontoAntiguidade = 
					 (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade.next();
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento() == null){
					 //Percentual de Desconto Sem Restabelecimento
						throw new ActionServletException("atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
				 }
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento() == null){
					 //Informe  Percentual de Desconto Com Restabelecimento
						throw new ActionServletException("atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
				 }
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo() == null){
						throw new ActionServletException(
								// Informe  Percentual de Desconto Ativo
								"atencao.required", null, "  Percentual de Desconto Ativo");	
				 }
				
			 }
			
		}

		 if (collectionParcelamentoDescontoInatividade != null &&
					!collectionParcelamentoDescontoInatividade.isEmpty()){
				
				Iterator iteratorParcelamentoDescontoInatividade = 
					collectionParcelamentoDescontoInatividade.iterator();
	
				 while (iteratorParcelamentoDescontoInatividade.hasNext()) {
					
					 ParcelamentoDescontoInatividade  parcelamentoDescontoInatividade = 
						 (ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade.next();
					 
					 if (parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento() == null){
							throw new ActionServletException(
									//  Percentual de Desconto Sem Restabelecimento
									"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
					 }					 
					 if (parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento() == null){
							throw new ActionServletException(
									// Informe  Percentual de Desconto Com Restabelecimento
									"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
					 }
				 }
		}
		 
		 if (collectionParcelamentoDescontoInatividadeAVista != null &&
					!collectionParcelamentoDescontoInatividadeAVista.isEmpty()){
				
			Iterator iteratorParcelamentoDescontoInatividade = 
				collectionParcelamentoDescontoInatividadeAVista.iterator();

			 while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				
				 ParcDesctoInativVista  parcelamentoDescontoInatividade = 
					 (ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				 
				 if (parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento() == null){
						throw new ActionServletException(
								//  Percentual de Desconto Sem Restabelecimento
								"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
				 }
				 
				 if (parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento() == null){
						throw new ActionServletException(
								// Informe  Percentual de Desconto Com Restabelecimento
								"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
				 }
				
			 }
		}
		 
		 
		
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
