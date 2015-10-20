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
package gcom.gui.cadastro;

import gcom.batch.Processo;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.ConstantesSistema;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vivianne Sousa
 * @data 23/03/2011
 */

public class GerarComandoCartasTarifaSocialAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        GerarComandoCartasTarifaSocialActionForm form = (GerarComandoCartasTarifaSocialActionForm) actionForm;

        //Este map levar� todos os par�metros para a inicializa��o do relat�rio
        Map parametros = new HashMap();
        Fachada fachada = Fachada.getInstancia();
        
		String criteriosSelecionados = (String)httpServletRequest.getParameter("criterios");
		String[] arrayCriterios =  criteriosSelecionados.split(",");
		
		if(arrayCriterios.length == 0 && form.getTipoCarta().equals("1")){
			throw new ActionServletException("atencao.campo.informado", null, "Pelo menos um crit�rio");
		}
		String acao = (String)httpServletRequest.getParameter("acao");
		
		TarifaSocialComandoCarta tarifaSocialComandoCarta  = inserirComando(form,arrayCriterios,acao,httpServletRequest);
		
		String msgSucesso = "";
		
		if(acao.equals("1")){
			
			msgSucesso = "Comando " + tarifaSocialComandoCarta.getId() + " de Simula��o inserido com sucesso.";
			parametros.put("tarifaSocialComandoCarta",tarifaSocialComandoCarta);
			
			//Simular
			fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
             		Processo.PROCESSAR_CARTA_TARIFA_SOCIAL_SIMULAR ,
             		this.getUsuarioLogado(httpServletRequest));
			
		}else{
			
			msgSucesso = "Comando " + tarifaSocialComandoCarta.getId() + " de Gera��o inserido com sucesso.";
			tarifaSocialComandoCarta.setDataProcessamento(new Date());
			parametros.put("tarifaSocialComandoCarta",tarifaSocialComandoCarta);
			
			//Gerar
			fachada.inserirProcessoIniciadoParametrosLivresAguardandoAutorizacao(parametros, 
             		Processo.PROCESSAR_CARTA_TARIFA_SOCIAL_GERAR ,
             		this.getUsuarioLogado(httpServletRequest));
			
		}

        montarPaginaSucesso(httpServletRequest, msgSucesso, "", "");
        
		return retorno;
	}
	
	//[SB0003]�Incluir Comando
	private TarifaSocialComandoCarta inserirComando(GerarComandoCartasTarifaSocialActionForm form,
			String[] arrayCriterios,String acao,HttpServletRequest httpServletRequest) {

		Integer idGerenciaRegional = null;
		GerenciaRegional gerenciaRegional = null;
        Integer idUnidadeNegocio = null;
        UnidadeNegocio unidadeNegocio = null;
        Integer idLocalidade = null;
        Localidade localidade = null;
        String tipoCarta = null;
        Integer qtdeDiasAtraso = null;
        Integer anoMesPesquisaInicial = null;
        Integer anoMesPesquisaFinal = null;
        Integer prazoComparecerCompesa = null;
        
        if(form.getGerenciaRegionalId() != null && !form.getGerenciaRegionalId().equals("")
        	&& !form.getGerenciaRegionalId().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idGerenciaRegional = new Integer(form.getGerenciaRegionalId());
        	gerenciaRegional = new GerenciaRegional();
        	gerenciaRegional.setId(idGerenciaRegional);
        }
        
        if(form.getUnidadeNegocioId() != null && !form.getUnidadeNegocioId().equals("")
        	&& !form.getUnidadeNegocioId().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idUnidadeNegocio= new Integer(form.getUnidadeNegocioId());
        	unidadeNegocio = new UnidadeNegocio();
        	unidadeNegocio.setId(idUnidadeNegocio);
        }
        
        if(form.getCodigoLocalidade() != null && !form.getCodigoLocalidade().equals("")){
        	idLocalidade = new Integer(form.getCodigoLocalidade());
        	localidade = new Localidade();
        	localidade.setId(idLocalidade);
        }
        
        if(form.getTipoCarta() == null || form.getTipoCarta().equals("")){
        	throw new ActionServletException("atencao.campo.informado", null, "Tipo da Carta");
        }else{
        	tipoCarta = form.getTipoCarta();
        }
        
        if(tipoCarta.equals("1")){
        	
        	 if(form.getAnoMesPesquisaInicial() != null && !form.getAnoMesPesquisaInicial().equals("")){
             	anoMesPesquisaInicial = gcom.util.Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial());
             		//Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial());
             }else{
            	 throw new ActionServletException("atencao.campo.informado", null, "Per�odo a ser pesquisado inicial");
             }
             
             if(form.getAnoMesPesquisaFinal() != null && !form.getAnoMesPesquisaFinal().equals("")){
             	anoMesPesquisaFinal = gcom.util.Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaFinal());
             }else{
            	 throw new ActionServletException("atencao.campo.informado", null, "Per�odo a ser pesquisado final");
             }
             
             if(arrayCriterios == null || arrayCriterios.length == 0 ||
            		 (arrayCriterios.length == 1 && arrayCriterios[0].equals(""))){
            	 throw new ActionServletException("atencao.campo.informado", null, "Par�metros para Recadastramento dos Clientes");
             }
             
        }else if(tipoCarta.equals("2")){
        	
        	if(form.getQtdeDiasAtraso() != null && !form.getQtdeDiasAtraso().equals("")){
            	qtdeDiasAtraso = new Integer(form.getQtdeDiasAtraso());
            }else{
            	throw new ActionServletException("atencao.campo.informado", null, "Cobrar D�bito com mais de 'XX' dias de atraso");
            }
        	
        }
        
        if(form.getPrazoComparecerCompesa() != null && !form.getPrazoComparecerCompesa().equals("")){
        	prazoComparecerCompesa = new Integer(form.getPrazoComparecerCompesa());
        }else{
        	throw new ActionServletException("atencao.campo.informado", null, "Prazo para comparecer na Companhia de Saneamento");
        }
	        
		TarifaSocialComandoCarta tarifaSocialComandoCarta = new TarifaSocialComandoCarta();
		
		tarifaSocialComandoCarta.setGerenciaRegional(gerenciaRegional);
		tarifaSocialComandoCarta.setUnidadeNegocio(unidadeNegocio);
		tarifaSocialComandoCarta.setLocalidade(localidade);
		tarifaSocialComandoCarta.setCodigoTipoCarta(new Integer(tipoCarta));
		tarifaSocialComandoCarta.setQuantidadeDiasComparecimento(prazoComparecerCompesa);
		tarifaSocialComandoCarta.setQuantidadeDiasDebitoVencimento(qtdeDiasAtraso);
		tarifaSocialComandoCarta.setAnoMesInicialImplantacao(anoMesPesquisaInicial);
		tarifaSocialComandoCarta.setAnoMesFinalImplantacao(anoMesPesquisaFinal);

		tarifaSocialComandoCarta.setIndicadorCriterioCpf(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioIdentidade(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioContratoEnergia(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioDadosEnergia(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioProgramaSocial(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioSeguroDesemprego(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioRendaComprovada(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioRendaDeclarada(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioQtdeEconomia(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioRecadastramento(ConstantesSistema.NAO);
		
		  if(tipoCarta.equals("1") && arrayCriterios != null && arrayCriterios.length > 0){

			  if(!(arrayCriterios.length == 1 && arrayCriterios[0].equals(""))){
				  for (int i = 0; i < (arrayCriterios.length); i++) {
						 
					 Integer indicador = new Integer(arrayCriterios[i]);
					 
					 switch (indicador) {
						
						case 1:
							tarifaSocialComandoCarta.setIndicadorCriterioCpf(ConstantesSistema.SIM);
							break;
						case 2:
							tarifaSocialComandoCarta.setIndicadorCriterioIdentidade(ConstantesSistema.SIM);
							break;
						case 3:
							tarifaSocialComandoCarta.setIndicadorCriterioContratoEnergia(ConstantesSistema.SIM);
							break;
						case 4:
							tarifaSocialComandoCarta.setIndicadorCriterioDadosEnergia(ConstantesSistema.SIM);
							break;
						case 5:
							tarifaSocialComandoCarta.setIndicadorCriterioProgramaSocial(ConstantesSistema.SIM);
							break;
						case 6:
							tarifaSocialComandoCarta.setIndicadorCriterioSeguroDesemprego(ConstantesSistema.SIM);
							break;
						case 7:
							tarifaSocialComandoCarta.setIndicadorCriterioRendaComprovada(ConstantesSistema.SIM);
							break;
						case 8:
							tarifaSocialComandoCarta.setIndicadorCriterioRendaDeclarada(ConstantesSistema.SIM);
							break;
						case 9:
							tarifaSocialComandoCarta.setIndicadorCriterioQtdeEconomia(ConstantesSistema.SIM);
							break;
						case 10:
							tarifaSocialComandoCarta.setIndicadorCriterioRecadastramento(ConstantesSistema.SIM);
							break;
						}
					 }
			  }else{
				  throw new ActionServletException("atencao.campo.informado", null, "Par�metros para Recadastramento dos Clientes");
			  }
			  

		  }
		
		
		
			tarifaSocialComandoCarta.setQuantidadeCartasGeradas(new Integer(0));
			tarifaSocialComandoCarta.setUsuario(this.getUsuarioLogado(httpServletRequest));
			
			if(acao.equals("1")){
				//Simular
				tarifaSocialComandoCarta.setDataSimulacao(new Date());
			}else{
				//Gerar
				tarifaSocialComandoCarta.setDataGeracao(new Date());
			}
			tarifaSocialComandoCarta.setUltimaAlteracao(new Date());
			
			Integer idComando = (Integer)Fachada.getInstancia().inserir(tarifaSocialComandoCarta);
			tarifaSocialComandoCarta.setId(idComando);
			return tarifaSocialComandoCarta;
	}
	
	
}
