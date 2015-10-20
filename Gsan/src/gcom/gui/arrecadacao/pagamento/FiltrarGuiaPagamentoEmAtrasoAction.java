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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.pagamento.RelatorioGuiaPagamentoEmAtraso;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarGuiaPagamentoEmAtrasoAction extends
	ExibidorProcessamentoTarefaRelatorio {

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("retornarFiltroFaturamentoCronograma");

        
        //Mudar isso quando tiver esquema de segurança
        FiltrarGuiaPagamentoEmAtrasoActionForm form = (FiltrarGuiaPagamentoEmAtrasoActionForm) actionForm;
        
        RelatorioGuiaPagamentoEmAtraso relatorioGuiaPagamentoEmAtraso = 
        	new RelatorioGuiaPagamentoEmAtraso();
        
        FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
        
        boolean peloMenosUmParametroInformado = false;
        
        
        validaDados(form.getIdGerenciaRegional(), form.getIdUnidadeNegocio(),form.getIdLocalidadeInicial(), form.getIdLocalidadeFinal());
            
        //Gerencia Regional
        if(form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
        	
        	filtroGuiaPagamento.adicionarParametro(
        		new ParametroSimples(FiltroGuiaPagamento.GERENCIA_REGIONAL_ID, form.getIdGerenciaRegional()));
           
            relatorioGuiaPagamentoEmAtraso.addParametro("gerenciaRegional",
            	this.buscarNomeGerenciaRegional(form.getIdGerenciaRegional()));
            	peloMenosUmParametroInformado = true;
        }
            
        //Unidade de Negocio
        if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals(ConstantesSistema.NUMERO_NAO_INFORMADO +"")){
        	filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.UNIDADE_NEGOCIO_ID, form.getIdUnidadeNegocio()));
            relatorioGuiaPagamentoEmAtraso.addParametro(
            	"unidadeNegocio", form.getIdUnidadeNegocio() + " - " +this.buscarNomeUnidadeNegocio(form.getIdUnidadeNegocio()));
            peloMenosUmParametroInformado = true;
        }
            
        //LocalidadeInicial
        if(form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("")){
            filtroGuiaPagamento.adicionarParametro(new MaiorQue(FiltroGuiaPagamento.LOCALIDADE_ID, form.getIdLocalidadeInicial()) );
            
            String localidadeInicial = form.getIdLocalidadeInicial() + " - ";
            if(form.getNomeLocalidadeInicial() != null && !form.getNomeLocalidadeInicial().equals("")){
            	localidadeInicial = localidadeInicial + form.getNomeLocalidadeInicial();
            }else{
            	localidadeInicial = localidadeInicial + (this.buscarLocalidadePorId(form.getIdLocalidadeInicial(), Fachada.getInstancia())).getDescricao();
            }
            relatorioGuiaPagamentoEmAtraso.addParametro("localidadeInicial", localidadeInicial); 
            		
            peloMenosUmParametroInformado = true;
        }
            
            //LocalidadeFinal
        if(form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("")){
            filtroGuiaPagamento.adicionarParametro(new MenorQue(FiltroGuiaPagamento.LOCALIDADE_ID, form.getIdLocalidadeFinal()) );
            
            String localidadeFinal = form.getIdLocalidadeFinal() + " - ";
            if(form.getNomeLocalidadeFinal() != null && !form.getNomeLocalidadeFinal().equals("")){
            	localidadeFinal = localidadeFinal + form.getNomeLocalidadeFinal();
            }else{
            	localidadeFinal = localidadeFinal + (this.buscarLocalidadePorId(form.getIdLocalidadeInicial(), Fachada.getInstancia())).getDescricao();
            }
            relatorioGuiaPagamentoEmAtraso.addParametro("localidadeFinal", localidadeFinal); 
            peloMenosUmParametroInformado = true;
        }
        
        //Financiamento Tipo
        if(form.getFinanciamentoTipoId() != null 
        		&& !form.getFinanciamentoTipoId().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
        	
        	filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.FINANCIAMENTO_TIPO_ID,
        			form.getFinanciamentoTipoId()));
        	
        	relatorioGuiaPagamentoEmAtraso.addParametro("financiamentoTipo",
    				form.getFinanciamentoTipoId());
        	
        	peloMenosUmParametroInformado = true;
        	
        }
        
        //Vencimento Inicial
        if(form.getVencimentoInicial() != null && !form.getVencimentoInicial().trim().equals("")){
        	Date vencimentoInicial = Util.converteStringParaDate(form.getVencimentoInicial());
        	Date vencimentoFinal = null;
        	if(form.getVencimentoFinal() != null && !form.getVencimentoFinal().trim().equals("")){
        		vencimentoFinal = Util.converteStringParaDate(form.getVencimentoFinal());
        		if(vencimentoInicial.compareTo(vencimentoFinal) <= 0){
        			filtroGuiaPagamento.adicionarParametro(new Intervalo(FiltroGuiaPagamento.DATA_VENCIMENTO,vencimentoInicial, vencimentoFinal));
        		}else{
        			throw new ActionServletException("atencao.data.inicial.maior.final");
        		}
        		
        	}else{
        		vencimentoFinal = vencimentoInicial;
        		
        		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DATA_VENCIMENTO,vencimentoInicial));
        	}
        	
        	Date dataAtual = new Date();
        	
        	if(vencimentoFinal.compareTo(dataAtual) > 0){
        		//FS0002
        		throw new ActionServletException("atencao.data.maior.que.atual", null, Util.formatarData(dataAtual));
        	}
        	
        	relatorioGuiaPagamentoEmAtraso.addParametro("vencimentoInicial",
    				form.getVencimentoInicial());
        	relatorioGuiaPagamentoEmAtraso.addParametro("vencimentoFinal",
    				form.getVencimentoFinal());
        	
        	peloMenosUmParametroInformado = true;
        	
        }
        
        //Vencimento Inicial
        if(form.getReferenciaInicialContabil() != null && !form.getReferenciaInicialContabil().trim().equals("")){
        	
        	if(!Util.validarMesAno(form.getReferenciaInicialContabil())){
        		throw new ActionServletException("atencao.referencia_inicial_invalida");
        	}
        	
        	int refInicial = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaInicialContabil());
        	int refFinal = 0;
        	if(form.getReferenciaFinalContabil() != null && !form.getReferenciaFinalContabil().trim().equals("")){
        		
        		if(!Util.validarMesAno(form.getReferenciaFinalContabil())){
            		throw new ActionServletException("atencao.referencia_final_invalida");
            	}
        		
        		refFinal = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFinalContabil());
        		if(refInicial <= refFinal){
        			filtroGuiaPagamento.adicionarParametro(new Intervalo(FiltroGuiaPagamento.ANO_MES_REFERENCIA_CONTABIL,refInicial, refFinal));
        		}else{
        			throw new ActionServletException("atencao.referencia.inicial.maior.final");
        		}
        		
        	}else{
        		throw new ActionServletException("atencao.informe_referencia_final");
        	}
        	
        	relatorioGuiaPagamentoEmAtraso.addParametro("referenciaInicial",
    				form.getReferenciaInicialContabil());
        	relatorioGuiaPagamentoEmAtraso.addParametro("referenciaFinal",
    				form.getReferenciaFinalContabil());
        	
        	peloMenosUmParametroInformado = true;
        	
        }else{
        	if(form.getReferenciaFinalContabil() != null && !form.getReferenciaFinalContabil().trim().equals("")){
        		throw new ActionServletException("atencao.informe_referencia_inicial");
        	}
        }
        
        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioGuiaPagamentoEmAtraso.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		relatorioGuiaPagamentoEmAtraso.addParametro("filtroGuiaPagamento",
				filtroGuiaPagamento);
		try {
			retorno = processarExibicaoRelatorio(relatorioGuiaPagamentoEmAtraso,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}
        
        return retorno;
    }
      
    private String buscarNomeGerenciaRegional(String id){
        
    	String retorno = null;
        FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();
        filtro.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, id));
       
        Collection colecao = this.getFachada().pesquisar(filtro, GerenciaRegional.class.getName());
       
        if(colecao != null &&  !colecao.isEmpty()){
        	retorno = ((GerenciaRegional)colecao.iterator().next()).getNome();
        }
       
        return retorno;
    }
        
    private String buscarNomeUnidadeNegocio(String id){
        String retorno = null;
        FiltroUnidadeNegocio filtro = new FiltroUnidadeNegocio();
        filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, id));
       
        Collection colecao = Fachada.getInstancia().pesquisar(filtro, UnidadeNegocio.class.getName());
       
        if(colecao != null &&  !colecao.isEmpty()){
        	retorno = ((UnidadeNegocio)colecao.iterator().next()).getNome();
        }
       
        return retorno;
    }
        
    /**
     * Verifica se as localidades inicial e final  foram digitadas e se elas 
     * pertencem à gerencia regional e  à Unidade de negocio selecionadas;
     * 
     * @author Erivan
     * @param gerenciaRegional
     * @param unidadeNegocio
     * @param idLocalidadeInicial
     * @param idLocalidadeFinal
     */
    private void validaDados(String gerenciaRegional, String unidadeNegocio, String idLocalidadeInicial, String idLocalidadeFinal){
    	
    	Integer locaFinal = null;
    	Integer locaInicial = null;
    	
    	try{
    		if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
    			if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
    				locaFinal = new Integer(idLocalidadeFinal);
    				locaInicial = new Integer(idLocalidadeInicial);
       
    				if(locaFinal < locaInicial){
    					throw new ActionServletException("atencao.localidade_final_menor_inicial");
    				}
    			}else{
    				//força informar localidade final
    				throw new ActionServletException("atencao.informe_localidade_final");
    			}
    		}else{
    			if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
    					//força informar localidade inicial
    					throw new ActionServletException("atencao.informe_localidade_inicial");
    			}
    		}
        }catch(java.lang.NumberFormatException ex){
        	throw new ActionServletException("atencao.localidade_numeros_positivos");
        }
        
    	/*Verifica se as localidades pertencem a gerencia regional e a unidade de negocio selecionadas*/
        if(gerenciaRegional != null && !gerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
        	if(unidadeNegocio != null && !unidadeNegocio.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
        		
        		if((idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) &&
            			(idLocalidadeFinal != null && !idLocalidadeFinal.equals(""))){
        			
        			Localidade localidadeInicial= this.buscarLocalidadePorId(idLocalidadeInicial, Fachada.getInstancia());
        			Localidade localidadeFinal= this.buscarLocalidadePorId(idLocalidadeFinal, Fachada.getInstancia());
           
        			this.validarLocalidades(localidadeInicial, localidadeFinal);
        		
        			if(!localidadeInicial.getGerenciaRegional().getId().toString().equals(gerenciaRegional)){
        				throw new ActionServletException("atencao.localidade_inicial_nao_pertence_gerencia_regional");
        			}
        			if(!localidadeInicial.getUnidadeNegocio().getId().toString().equals(unidadeNegocio)){
        				throw new ActionServletException("atencao.localidade_inicial_nao_pertence_unidade_negocio");
        			} 
           
        			if(!localidadeFinal.getGerenciaRegional().getId().toString().equals(gerenciaRegional)){
        				throw new ActionServletException("atencao.localidade_final_nao_pertence_gerencia_regional");
        			}
        			if(!localidadeFinal.getUnidadeNegocio().getId().toString().equals(unidadeNegocio)){
        				throw new ActionServletException("atencao.localidade_final_nao_pertence_unidade_negocio");
        			}
        		}
            }else{
            	//O usuário so selecionou a gerencia regional
            	if((idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) &&
            			(idLocalidadeFinal != null && !idLocalidadeFinal.equals(""))){
            		
            		Localidade localidadeInicial= this.buscarLocalidadePorId(idLocalidadeInicial, Fachada.getInstancia());
            		Localidade localidadeFinal= this.buscarLocalidadePorId(idLocalidadeFinal, Fachada.getInstancia());
            	
            		this.validarLocalidades(localidadeInicial, localidadeFinal);
            	           	
            		if(!localidadeInicial.getGerenciaRegional().getId().toString().equals(gerenciaRegional)){
            			throw new ActionServletException("atencao.localidade_inicial_nao_pertence_gerencia_regional");
            		}             
           
            		if(!localidadeFinal.getGerenciaRegional().getId().toString().equals(gerenciaRegional)){
            			throw new ActionServletException("atencao.localidade_final_nao_pertence_gerencia_regional");
            		}
            	}
            }
        }else{
        	/* Caso o usuário não tenha preenchido os campos de gerencia e unidade, 
        	 * verifica se foi digitado algum valor nas localidades e se eles são válidos;
        	 */
        	if((idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) &&
        			(idLocalidadeFinal != null && !idLocalidadeFinal.equals(""))){
        		
        		Localidade localidadeInicial= this.buscarLocalidadePorId(idLocalidadeInicial, Fachada.getInstancia());               
        		Localidade localidadeFinal= this.buscarLocalidadePorId(idLocalidadeFinal, Fachada.getInstancia());
        		
        		this.validarLocalidades(localidadeInicial, localidadeFinal);
        	}
        }    
    }
        
    /**
     * Procura a localidade pelo id informado;  
     * @author Erivan
     * @param id
     * @param fachada
     * @return Localidade
     */
    private Localidade buscarLocalidadePorId(String id, Fachada fachada){
    	Localidade localidade = null;
   
	    FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	    filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, id));
	    filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	    filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.GERENCIA);
	    filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.UNIDADE_NEGOCIO);
   
	    Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
   
	    if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
	    	localidade = (Localidade) colecaoLocalidade.iterator().next();    
	    }
   
	    return localidade;
    }
    
    /**
     * Verifica os campos Localidade inicial e final;  
     * @author Erivan
     * @param localidadeInicial
     * @param localidadeFinal
     */
    private void validarLocalidades(Localidade localidadeInicial, Localidade localidadeFinal){
    	
    	if(localidadeInicial== null){
    		throw new ActionServletException("atencao.localidade_inicial_inexistente");
    	}
    	if(localidadeFinal == null){
    		throw new ActionServletException("atencao.localidade_final_inexistente");
    	}
    }

}