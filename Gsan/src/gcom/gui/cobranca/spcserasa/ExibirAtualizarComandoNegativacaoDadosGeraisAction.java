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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.CpfTipo;
import gcom.cadastro.FiltroCpfTipo;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoCriterioCpfTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
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
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da inser��o de um Comando de Negativa��o (Aba n� 01 - Dados Gerais) 
 *
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirAtualizarComandoNegativacaoDadosGeraisAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarComandoNegativacaoDadosGerais");
        
    	Fachada fachada = Fachada.getInstancia();
    	
    	HttpSession sessao = httpServletRequest.getSession(false);

    	AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;
    	
    	if(form.getSimular().equals(ConstantesSistema.NAO_CONFIRMADA)){
        	httpServletRequest.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");   		
    	}
		
    	if(httpServletRequest.getParameter("idComandoNegativacaoSimulado") != null){
 			Integer idComandoNegativacao = new Integer(httpServletRequest.getParameter("idComandoNegativacaoSimulado"));
 			
 			NegativacaoCriterio negativacaoCriterio = 
 				fachada.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);
 			
 			//[FS0027] Verificar sele��o de comando simulado
 			if (((Short)negativacaoCriterio.getNegativacaoComando().getIndicadorSimulacao()).equals(ConstantesSistema.NAO)){
     			throw new ActionServletException("atencao.comando_nao_simulacao");
 			}
 			if(negativacaoCriterio.getNegativacaoComando().getDataHoraRealizacao() == null){
 				throw new ActionServletException("atencao.simulacao_nao_realizada");
 			}
 			form.setIdComandoSimulado(idComandoNegativacao.toString());
 			form.setDescricaoComandoSimulado(negativacaoCriterio.getDescricaoTitulo());
 		}
    	
    	//[SB0003]- Determinar Data Prevista para Execu��o do Comando
    	if(httpServletRequest.getParameter("determinarData") != null){
			atualizaColecoesNaSessao(sessao, httpServletRequest);
	        if(form.getSimular().equals(ConstantesSistema.NAO_CONFIRMADA)){
	        	Date dataRealizacaoComando = fachada.pesquisarUltimaDataRealizacaoComando(new Integer(form.getNegativadorId()), 
    					new Integer(form.getSimular()));
	    	  if(dataRealizacaoComando == null){
	    		  dataRealizacaoComando = new Date();
	    	  }
	    	  //Pesquisar Sistema Parametro 
	    	  FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
	    			
	    	  Collection<SistemaParametro> collectionSistemaParametro = fachada
	    					.pesquisar(filtroSistemaParametro,
	    							SistemaParametro.class.getName());
	    	  SistemaParametro sistemaParametro = (SistemaParametro) collectionSistemaParametro
	    					.iterator().next();
	    	  Short periodoRealizacao = sistemaParametro.getCodigoPeriodicidadeNegativacao();
	    	  if(periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_SEMANAL)){
	    		 dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 7);  
	    	  }else if(periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_QUINZENAL)){
	    		 dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 15); 		
	    	  }else{
	    		 dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 30); 
	    	  }
	    	  
			  Calendar calendar = Calendar.getInstance();
			  calendar.setTime(dataRealizacaoComando);
			  
			  Integer diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
			  Integer qtdSomarData = 7 - diaSemana;   	  
			  if(qtdSomarData != 0){
			    dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, qtdSomarData); 
			  }
			  
			  form.setDataPrevista(Util.formatarData(dataRealizacaoComando));
	    	}else{
	    		form.setDataPrevista(Util.formatarData(new Date()));
	    	}      
    	}
    	
        //Pesquisar Tipo CPF
        if(sessao.getAttribute("colecaoCPFTipo") == null){
        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
    		
    		Collection cpfTipos = fachada.pesquisar(filtroCpfTipo,CpfTipo.class.getName());
    		
    		sessao.setAttribute("colecaoCPFTipo", cpfTipos);
        }
        
        //Pesquisa Usuario 
        if(httpServletRequest.getParameter("pesquisarUsuario") != null){
        	atualizaColecoesNaSessao(sessao, httpServletRequest);
        	
	        String usuario = form.getUsuario();
	       
	        if(usuario != null && !usuario.equals("")){
	        	
	        	FiltroUsuario filtroUsuario = new FiltroUsuario();  
	            
	        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
	            
	            Collection colecaoUsuario = fachada.pesquisar(
	                    filtroUsuario,Usuario.class.getName());
	            
	            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
	            	httpServletRequest.setAttribute("corUsuario", "valor");
	            	
	            	form.setUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
	            	form.setNomeUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
				} else {
					httpServletRequest.setAttribute("corUsuario","exception");
					form.setUsuario(null);
					form.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
				}
	        }
        }
        
        //Adicionar Titularidade CPF/CNPJ da Negativa��o
        if(httpServletRequest.getParameter("adicionarTitularidade") != null){
        	atualizaColecoesNaSessao(sessao, httpServletRequest);
        	
        	if(form.getTitularidadeNegativacao()== null || 
        			form.getTitularidadeNegativacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
    			throw new ActionServletException("atencao.campo.informado", null, "Titularidade do CPF/CNPJ da Negativa��o");
        	}
        	Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo = null;
        	if(sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo") == null){
        		colecaoNegativacaoCriterioCpfTipo = new ArrayList<NegativacaoCriterioCpfTipo>();
        	}else{
        		colecaoNegativacaoCriterioCpfTipo = (Collection)sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo");
        	}
        	
        	NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = new NegativacaoCriterioCpfTipo();
        	Integer idTitularidade = new Integer(form.getTitularidadeNegativacao());
        	//negativacaoCriterioCpfTipo.setId(idTitularidade);
        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
        	filtroCpfTipo.adicionarParametro(new ParametroSimples(FiltroCpfTipo.ID, idTitularidade));
    		
    		Collection titularidades = fachada.pesquisar(filtroCpfTipo,
    						CpfTipo.class.getName());
    		
    		if (titularidades != null && !titularidades.isEmpty()) {    		
    			CpfTipo cpfTipo = (CpfTipo) Util.retonarObjetoDeColecao(titularidades);
    			negativacaoCriterioCpfTipo.setCpfTipo(cpfTipo);	
    			negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("2"));
    		}
    		
    		//[FS0013] Verificar Titularidade do CPF/CNPJ da Negativa��o j� existe na lista
    		if(colecaoNegativacaoCriterioCpfTipo.contains(negativacaoCriterioCpfTipo)){
    			throw new ActionServletException("atencao.titularidade_negativacao_ja_existe_lista");
    		}
    		
    		form.setTitularidadeNegativacao(""+ConstantesSistema.NUMERO_NAO_INFORMADO);
    		colecaoNegativacaoCriterioCpfTipo.add(negativacaoCriterioCpfTipo);
    		sessao.setAttribute("colecaoNegativacaoCriterioCpfTipo", colecaoNegativacaoCriterioCpfTipo);
        }
        
		//Remover Titularidade CPF/CNPJ da Negativa��o
		if(httpServletRequest.getParameter("idTitularidadeRemover") != null){
			atualizaColecoesNaSessao(sessao, httpServletRequest);
			
			Integer idTitularidadeRemover = new Integer(httpServletRequest.getParameter("idTitularidadeRemover"));
			Collection colecaoNegativacaoCriterioCpfTipo = (Collection)sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo");			
			if(colecaoNegativacaoCriterioCpfTipo != null && !colecaoNegativacaoCriterioCpfTipo.isEmpty()){
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = new NegativacaoCriterioCpfTipo();
	        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
	        	filtroCpfTipo.adicionarParametro(new ParametroSimples(FiltroCpfTipo.ID, idTitularidadeRemover));
	    		
	    		Collection titularidades = fachada.pesquisar(filtroCpfTipo,CpfTipo.class.getName());
    			CpfTipo cpfTipo = (CpfTipo) Util.retonarObjetoDeColecao(titularidades);
				negativacaoCriterioCpfTipo.setCpfTipo(cpfTipo);
				colecaoNegativacaoCriterioCpfTipo.remove(negativacaoCriterioCpfTipo);		
				sessao.setAttribute("colecaoNegativacaoCriterioCpfTipo", colecaoNegativacaoCriterioCpfTipo);
			}
		}
        
    	return retorno;
    }

	private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		//colecaoNegativacaoCriterioCpfTipo
		if (sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo") != null
		&& !sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo").equals("")) {
		
			Collection colecaoNegativacaoCriterioCpfTipo = (Collection) sessao
			.getAttribute("colecaoNegativacaoCriterioCpfTipo");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  NegativacaoCriterioCpfTipo
			String ordem = null;
			String coincidente = null;
		
			Iterator iteratorNegativacaoCriterioCpfTipo = colecaoNegativacaoCriterioCpfTipo.iterator();
			
			while (iteratorNegativacaoCriterioCpfTipo.hasNext()) {
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = 
					(NegativacaoCriterioCpfTipo) iteratorNegativacaoCriterioCpfTipo.next();
				
				Integer idTitularidade = negativacaoCriterioCpfTipo.getCpfTipo().getId();

				ordem = (String) httpServletRequest.getParameter("ordem" + idTitularidade);
				coincidente = (String) httpServletRequest.getParameter("coincidente" + idTitularidade);
				
				if (ordem != null && !ordem.equals("")) {
					negativacaoCriterioCpfTipo.setNumeroOrdemSelecao(new Short(ordem));
				}

				if(coincidente != null && !coincidente.equals("")){
					negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("1"));	
				}else{
					negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("2"));	
				}
			}
		}	        	
    }
}
