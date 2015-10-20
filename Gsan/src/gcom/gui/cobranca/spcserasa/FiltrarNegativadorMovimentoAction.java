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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;


import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.spcserasa.bean.NegativadorMovimentoHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do Movimento do Negativador de acordo com os par�metros informados
 * 
 * @author Yara Taciane de Souza
 * @created 21/01/2008
 */
public class FiltrarNegativadorMovimentoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Movimento Negativador
	 * 
	 * [UC0682] Filtrar Movimento do Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 21/01/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {


        ActionForward retorno = actionMapping.findForward("retornarFiltroNegativadorMovimento");
        
        FiltrarNegativadorMovimentoActionForm filtrarNegativadorMovimentoActionForm = (FiltrarNegativadorMovimentoActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Boolean peloMenosUmParametroInformado = false;
        
   //-------------------------------------------------------------------------
        Integer idNegativador = null;
		if (filtrarNegativadorMovimentoActionForm.getIdNegativador() != null
				&& !filtrarNegativadorMovimentoActionForm
						.getIdNegativador().equals("-1")) {			
			idNegativador=Integer.parseInt(filtrarNegativadorMovimentoActionForm.getIdNegativador());
			peloMenosUmParametroInformado = true;
	
		} 
     
        Short codigoMovimento = -1;
        if(!"".equals(filtrarNegativadorMovimentoActionForm.getTipoMovimento()) &&  filtrarNegativadorMovimentoActionForm.getTipoMovimento() !=  null){
        	
        	if(!filtrarNegativadorMovimentoActionForm.getTipoMovimento().equals(ConstantesSistema.TODOS.toString())){
        		codigoMovimento = Short.parseShort(filtrarNegativadorMovimentoActionForm.getTipoMovimento()); 
        	}
        	
        	peloMenosUmParametroInformado = true;
        }
        
        
        Integer idImovel = null;
        if(!"".equals(filtrarNegativadorMovimentoActionForm.getIdImovel()) &&  filtrarNegativadorMovimentoActionForm.getIdImovel() !=  null){
        	if(!Util.validarStringNumerica(filtrarNegativadorMovimentoActionForm.getIdImovel())){
        		throw new ActionServletException("atencao.campo.invalido",null,"Matr�cula do Im�vel");
        	}
        	
        	idImovel = Util.converterStringParaInteger(filtrarNegativadorMovimentoActionForm.getIdImovel());         	
        	peloMenosUmParametroInformado = true;
        }
        
        
        
        Integer numeroSequencialArquivo = null;
        if(!"".equals(filtrarNegativadorMovimentoActionForm.getNumeroSequencialArquivo()) &&  filtrarNegativadorMovimentoActionForm.getNumeroSequencialArquivo() !=  null){
        	numeroSequencialArquivo = Integer.parseInt(filtrarNegativadorMovimentoActionForm.getNumeroSequencialArquivo()); 
        	peloMenosUmParametroInformado = true;
        } 
        
       
        Date dataProcessamentoInicial = null;
        Date dataProcessamentoFinal = null;
        String dataInicio = null;
		String dataFim = null;
        if((!"".equals(filtrarNegativadorMovimentoActionForm.getDataProcessamentoInicial()) &&  filtrarNegativadorMovimentoActionForm.getDataProcessamentoInicial() !=  null)&&
        	(!"".equals(filtrarNegativadorMovimentoActionForm.getDataProcessamentoFinal())  &&  filtrarNegativadorMovimentoActionForm.getDataProcessamentoFinal() !=  null)){
        	dataProcessamentoInicial = Util.converteStringParaDate(filtrarNegativadorMovimentoActionForm.getDataProcessamentoInicial()); 
        	dataProcessamentoFinal =  Util.converteStringParaDate(filtrarNegativadorMovimentoActionForm.getDataProcessamentoFinal()); 
        	
        	dataInicio = Util.formatarData(dataProcessamentoInicial);
			dataFim = Util.formatarData(dataProcessamentoFinal);
        	
//        	Se data inicio maior que data fim
    		if(Util.compararData(dataProcessamentoInicial, dataProcessamentoFinal) == 1){
    			 
    			throw new ActionServletException(
    					"atencao.data_inicial_maior_data_final",dataInicio,dataFim);
    		}
    		
    	       	peloMenosUmParametroInformado = true;
        } 
                
        
        short indicadorMovimento = -1;
        if(!"".equals(filtrarNegativadorMovimentoActionForm.getMovimento())  &&  filtrarNegativadorMovimentoActionForm.getMovimento() !=  null){
        	if(!filtrarNegativadorMovimentoActionForm.getMovimento().equals(ConstantesSistema.TODOS.toString())){
        		indicadorMovimento = Short.parseShort(filtrarNegativadorMovimentoActionForm.getMovimento()); 
        	}
        	
        	peloMenosUmParametroInformado = true;
        } 
        
        short indicadorRegistro = -1;
        if(!"".equals(filtrarNegativadorMovimentoActionForm.getMovimentoRegistro())  &&  filtrarNegativadorMovimentoActionForm.getMovimentoRegistro() !=  null){
        	if(!filtrarNegativadorMovimentoActionForm.getMovimentoRegistro().equals(ConstantesSistema.TODOS.toString()) ){
        		indicadorRegistro = Short.parseShort(filtrarNegativadorMovimentoActionForm.getMovimentoRegistro()); 	
        	}
        	
        	peloMenosUmParametroInformado = true;
        }               
        
        short indicadorCorrigido = -1;
        if(!"".equals(filtrarNegativadorMovimentoActionForm.getMovimentoCorrigido())  &&  filtrarNegativadorMovimentoActionForm.getMovimentoCorrigido() !=  null){
        	if(!filtrarNegativadorMovimentoActionForm.getMovimentoCorrigido().equals(ConstantesSistema.TODOS.toString()) ){
        		indicadorCorrigido = Short.parseShort(filtrarNegativadorMovimentoActionForm.getMovimentoCorrigido()); 	
        	}
        	
        	peloMenosUmParametroInformado = true;
        }               
        
        
        
        
        NegativadorMovimentoHelper negativadorMovimentoHelper = new NegativadorMovimentoHelper(idNegativador,
        																						codigoMovimento,
        																						idImovel,
        																						numeroSequencialArquivo,
        																						dataInicio,
        																						dataFim,
        																						indicadorMovimento,
        																						indicadorRegistro,
        																						indicadorCorrigido);
        
        //--------------------------------------------------------------------------------------------------------
		//Ger�ncia Regional
		//--------------------------------------------------------------------------------------------------------
        String[] arrayGerenciaRegional = filtrarNegativadorMovimentoActionForm.getArrayGerenciaRegional();		
		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);

		Collection colecaoGerenciaRegional = new ArrayList();
		int j = 0;		
		
		if (!Util.isVazioOrNulo(arrayGerenciaRegional)) {
			gerenciaRegionalColecao.setNome("OP��ES SELECIONADAS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			
			Collection gerenciaParametro = new ArrayList();
			for(String gerencia : arrayGerenciaRegional){
				if (Util.verificarNaoVazio(gerencia)) {
					gerenciaParametro.add(gerencia);	
				}				
			}

			filtroGerenciaRegional.adicionarParametro(new ParametroSimplesIn(FiltroGerenciaRegional.ID, gerenciaParametro));
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);

			Collection colecaoGerenciaRegionalPesquisa = this.getFachada().pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegionalPesquisa != null
					&& !colecaoGerenciaRegionalPesquisa.isEmpty()) {
				peloMenosUmParametroInformado = true;
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);
			}
		} else {
			gerenciaRegionalColecao.setNome("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}		
		
		negativadorMovimentoHelper.setColecaoGerenciaRegional(colecaoGerenciaRegional);
		//--------------------------------------------------------------------------------------------------------
		//Unidade de Neg�cio
		//--------------------------------------------------------------------------------------------------------
		String[] arrayUnidadeNegocio = filtrarNegativadorMovimentoActionForm.getArrayUnidadeNegocio();		
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);

		Collection colecaoUnidadeNegocio = new ArrayList();
		int l = 0;		
		
		if (!Util.isVazioOrNulo(arrayUnidadeNegocio)) {
			unidadeNegocioColecao.setNome("OP��ES SELECIONADAS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			
			Collection unidadeParametro = new ArrayList();
			for(String unidade : arrayUnidadeNegocio){
				if(Util.verificarNaoVazio(unidade)){
					unidadeParametro.add(unidade);
				}
			}

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimplesIn(FiltroUnidadeNegocio.ID, unidadeParametro));
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			Collection colecaoUnidadeNegocioPesquisa = this.getFachada().pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocioPesquisa != null
					&& !colecaoUnidadeNegocioPesquisa.isEmpty()) {
				peloMenosUmParametroInformado = true;
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		} else {
			unidadeNegocioColecao.setNome("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}
		
		negativadorMovimentoHelper.setColecaoUnidadeNegocio(colecaoUnidadeNegocio);

		if (filtrarNegativadorMovimentoActionForm.getIdEloPolo() != null && !filtrarNegativadorMovimentoActionForm.getIdEloPolo().equals("")){
			if(!Util.validarStringNumerica(filtrarNegativadorMovimentoActionForm.getIdEloPolo())){
				throw new ActionServletException("atencao.campo.invalido",null,"Localidade P�lo");
			}
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, filtrarNegativadorMovimentoActionForm.getIdEloPolo()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("pesquisa.elo.inexistente");
			} else {
				negativadorMovimentoHelper.setIdLocalidadePolo(new Integer(filtrarNegativadorMovimentoActionForm.getIdEloPolo()));
			}
			peloMenosUmParametroInformado = true;
		}
		
		if (filtrarNegativadorMovimentoActionForm.getIdLocalidade() != null && !filtrarNegativadorMovimentoActionForm.getIdLocalidade().equals("")){
			if(!Util.validarStringNumerica(filtrarNegativadorMovimentoActionForm.getIdLocalidade())){
				throw new ActionServletException("atencao.campo.invalido",null,"Localidade");
			}
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, filtrarNegativadorMovimentoActionForm.getIdLocalidade()));
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (coll.size() != 1){
				throw new ActionServletException("pesquisa.localidade.inexistente");
			} else {
				negativadorMovimentoHelper.setIdLocalidade(new Integer(filtrarNegativadorMovimentoActionForm.getIdLocalidade()));
			}
			peloMenosUmParametroInformado = true;
		}
		
		//--------------------------------------------------------------------------------------------------------
		//Motivo de Rejei��o
		//--------------------------------------------------------------------------------------------------------
		String[] arrayMotivoRejeicao = filtrarNegativadorMovimentoActionForm.getArrayMotivoRejeicao();		
		NegativadorRetornoMotivo negativadorRetornoMotivoColecao = new NegativadorRetornoMotivo();
		negativadorRetornoMotivoColecao.setId(-1);

		Collection colecaoMotivoRejeicao = new ArrayList();
		int t = 0;		
		
		if (arrayMotivoRejeicao != null) {
			negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("OP��ES SELECIONADAS");
			colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
			FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, new Short("2") ));
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, new Integer(filtrarNegativadorMovimentoActionForm.getIdNegativador())));
			
			for (t = 0; t < arrayMotivoRejeicao.length; t++) {
				if (!arrayMotivoRejeicao[t].equals("")
					&& !arrayMotivoRejeicao[t].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (t + 1 < arrayMotivoRejeicao.length) {
						filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, arrayMotivoRejeicao[t],
										ConectorOr.CONECTOR_OR,	arrayMotivoRejeicao.length));

					} else {
						filtroNegativadorRetornoMotivo.adicionarParametro(
								new ParametroSimples(FiltroEsferaPoder.ID, arrayMotivoRejeicao[t]));
					}
				}
			}

			filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO);

			Collection colecaoNegativadorRetornoMotivoPesquisa = this.getFachada().pesquisar(
					filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());

			if (colecaoNegativadorRetornoMotivoPesquisa != null
					&& !colecaoNegativadorRetornoMotivoPesquisa.isEmpty()) {
				peloMenosUmParametroInformado = true;
				colecaoMotivoRejeicao.addAll(colecaoNegativadorRetornoMotivoPesquisa);
			}
		} else {
			negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("TODOS");
			colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
		}

		negativadorMovimentoHelper.setColecaoMotivoRejeicao(colecaoMotivoRejeicao);

		

     //   Collection collNegativadorMovimento = fachada.pesquisarNegativadorMovimento(negativadorMovimentoHelper);
        

		// [FS0003] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

	//	sessao.setAttribute("colecao", collNegativadorMovimento);
        
        sessao.setAttribute("negativadorMovimentoHelper", negativadorMovimentoHelper);

	
       return retorno;
    }
   
    

}
 