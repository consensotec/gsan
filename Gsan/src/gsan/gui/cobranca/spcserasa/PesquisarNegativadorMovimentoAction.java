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
package gsan.gui.cobranca.spcserasa;

import gsan.cobranca.Negativador;
import gsan.cobranca.NegativadorMovimento;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.spcserasa.FiltroNegativador;
import gsan.spcserasa.FiltroNegativadorMovimento;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.Intervalo;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0674] Pesquisar Negativador Movimento
 * 
 * @author Yara Taciane 
 * @date 27/128/2007
 * 
 */
public class PesquisarNegativadorMovimentoAction extends GcomAction {
	/**
	 * [UC0674] Esse caso de uso efetua pesquisa de Negativador Movimento
	 * 
	 * @author Yara Taciane
	 * @date 27/12/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	   public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

		   
		    HttpSession sessao = httpServletRequest.getSession(false);	
	        ActionForward retorno = actionMapping.findForward("listaNegativadorMovimento");
	        
	        //Obt�m a inst�ncia da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        
	        //HttpSession sessao = httpServletRequest.getSession(false);
	        
			// Obt�m o action form
	        PesquisarNegativadorMovimentoActionForm form = (PesquisarNegativadorMovimentoActionForm) actionForm;

			// Recupera os par�metros do form
	        
	        String idNegativador = (String) form.getIdNegativador();
	        String codigoMovimento = (String) form.getCodigoMovimento();
	        String numeroSequencialEnvio = (String) form.getNumeroSequencialEnvio();	        
	        String dataProcessamentoInicial = (String) form.getDataProcessamentoInicial();
	        String dataProcessamentoFinal = (String) form.getDataProcessamentoFinal(); 
	        
	        boolean peloMenosUmParametroInformado = false;

	         FiltroNegativadorMovimento filtroNegativadorMovimento = new FiltroNegativadorMovimento();	        

	         
	         //verificar se est� certo se esse id � o do cliente ou do negativador
	        if (idNegativador != null && !idNegativador.equals("-1")) {
	        	
	        	filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.NEGATIVADOR_ID,idNegativador));
                peloMenosUmParametroInformado = true;
	        } else {
				throw new ActionServletException("atencao.required", null,
				"Negativador");
	        }
	         
	        
	        if (codigoMovimento != null && !codigoMovimento.equals("")) {
	        	
	        	if(!codigoMovimento.equals("3")){
	        		filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(
		        			FiltroNegativadorMovimento.CODIGO_MOVIMENTO, new Integer(codigoMovimento)));
	                peloMenosUmParametroInformado = true;
	        	}	        	
	        
	        }else {
				throw new ActionServletException("atencao.required", null,
				"Tipo Movimento");
	        }
	         
	        
	        if (numeroSequencialEnvio != null && !numeroSequencialEnvio.equals("") ) {	        		
	        	filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(
	        			FiltroNegativadorMovimento.NUMERO_SEQUENCIAL_ENVIO, new Integer(numeroSequencialEnvio)));
                peloMenosUmParametroInformado = true;
	        }
	        
	   
	        
	        if ((dataProcessamentoInicial != null && !dataProcessamentoInicial.equals(""))
					&& ((dataProcessamentoFinal != null && !dataProcessamentoFinal
							.equals("")))) {
	        	
	        	
	        	if (Util.validarDiaMesAno(dataProcessamentoInicial)) {
					throw new ActionServletException(
							"atencao.data.inicio.Contrato.invalida");
				}
	        	
	        	if (Util.validarDiaMesAno(dataProcessamentoFinal)) {
					throw new ActionServletException(
							"atencao.data.fim.Contrato.invalida");
				}
	        	
	        	
	        	
	        	Date dataProcessamentoInicialFormatada = Util.converteStringParaDate(dataProcessamentoInicial);
	        	Date dataProcessamentoFinalFormatada = Util.converteStringParaDate(dataProcessamentoFinal);
	        	
	        	if (dataProcessamentoInicialFormatada.after(dataProcessamentoFinalFormatada)) {
					String dataInicio = Util.formatarData(dataProcessamentoInicialFormatada);
					throw new ActionServletException(
							"atencao.data.inicio.nao.superior.data.corrente", null,dataInicio);
				}
					
	        	   	
	        	filtroNegativadorMovimento
						.adicionarParametro(new Intervalo(
								FiltroNegativadorMovimento.DATA_PROCESSAMENTO_ENVIO,
								dataProcessamentoInicialFormatada,
								dataProcessamentoFinalFormatada));
			} 
	        
	          
			// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
	        }
	        	     
	        
			// Faz a pesquisa baseada no filtro
	        Collection collNegativadorMovimento = fachada.pesquisar(filtroNegativadorMovimento, NegativadorMovimento.class.getName());
	        
			// Verificar se a pesquisa de NegativadorMovimento n�o est� vazia
	        if (collNegativadorMovimento != null && !collNegativadorMovimento.isEmpty()) {
                 // Aciona o controle de pagina��o para que sejam pesquisados apenas
				// os registros que aparecem na p�gina
	      
	        	filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
	        	
	        	
	        	
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroNegativadorMovimento, NegativadorMovimento.class.getName());
				
				collNegativadorMovimento = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
		
				// Manda a cole��o das collNegativadorMovimento pesquisadas para o request
				httpServletRequest.getSession(false).setAttribute("collNegativadorMovimento", collNegativadorMovimento);
				
	        } else 	 if (collNegativadorMovimento == null || collNegativadorMovimento.isEmpty()) {
				// Nenhuma imovel cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null, "NegativadorMovimento");
			} else if (collNegativadorMovimento.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
				// Muitos registros encontrados
				throw new ActionServletException(
						"atencao.pesquisa.muitosregistros");
			} 
	        
	         
	        //Exibe na Tela o nome do Cliente Negativador
	        FiltroNegativador filtroNegativador = new FiltroNegativador();
	        filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.ID,idNegativador));
	        
	        filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
	        Collection collNegativador = fachada.pesquisar(filtroNegativador, Negativador.class.getName());
			
			
			// Recupera da cole��o o Negativador que vai ser atualizado
			Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(collNegativador);		
			
			if(negativador != null){		
		
				sessao.setAttribute("negativador", negativador);
				
			}
			
	            
	        return retorno;
	    }

	}
