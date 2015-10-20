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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarTipoServicoAction extends GcomAction {
	/**
	 * [UC0413] Esse caso de uso efetua pesquisa do Tipo de Servi�o
	 * 
	 * @author Leandro Cavalcanti
	 * @date 01/08/2006
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
	        PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm = (PesquisarTipoServicoActionForm) actionForm;
	        
	        
	        ActionForward retorno = actionMapping.findForward("listarResultadoPesquisarTipoServico");
	        
	        // Obtendo dados do form 
	        String dsTipoServico = (String)pesquisarTipoServicoActionForm.getDsTipoServico();
	        String dsAbreviadaTipoServico = (String)pesquisarTipoServicoActionForm.getDsAbreviadaTipoServico();
	        String subgrupoTipoServico = (String)pesquisarTipoServicoActionForm.getSubgrupoTipoServico();

	        //String indicadorPavimento = (String)pesquisarTipoServicoActionForm.getIndicadorPavimento();
	        String indicadorPavimentoRua = (String)pesquisarTipoServicoActionForm.getIndicadorPavimentoRua();
	        String indicadorPavimentoCalcada = (String)pesquisarTipoServicoActionForm.getIndicadorPavimentoCalcada();
	        
	        String valorServicoInicial = (String) pesquisarTipoServicoActionForm.getValorServicoInicial();
	        String valorServicoFinal = (String) pesquisarTipoServicoActionForm.getValorServicoFinal();
	        String indicadorAtualizacaoComercio = (String) pesquisarTipoServicoActionForm.getIndicadorAtualizacaoComercio();
	        String indicadorServicoTerceirizado = (String) pesquisarTipoServicoActionForm.getIndicadorServicoTerceirizado();
	        String codigoServico = pesquisarTipoServicoActionForm.getCodigoServico();
	        String tempoMedioExecucaoInicial = (String) pesquisarTipoServicoActionForm.getTempoMedioExecucaoInicial();
	        String tempoMedioExecucaoFinal = (String) pesquisarTipoServicoActionForm.getTempoMedioExecucaoFinal();
	        String tipoDebito = (String) pesquisarTipoServicoActionForm.getTipoDebito();
	        String tipoCredito = (String) pesquisarTipoServicoActionForm.getTipoCredito();
	        String prioridadeServico = (String) pesquisarTipoServicoActionForm.getPrioridadeServico();
	        String perfilServco = pesquisarTipoServicoActionForm.getPerfilServco();
	        String tipoServicoReferencia = (String) pesquisarTipoServicoActionForm.getTipoServicoReferencia();
	        String tipoPesquisa = (String)pesquisarTipoServicoActionForm.getTipoPesquisa();
	        String tipoPesquisaAbreviada =  (String)pesquisarTipoServicoActionForm.getTipoPesquisaAbreviada();
	        
	        boolean peloMenosUmParametroInformado = false;

	        ServicoTipo servicoTipo = new ServicoTipo();
	        
	        //Descri��o do Tipo de Servi�o
	        if (dsTipoServico != null && !dsTipoServico.trim().equalsIgnoreCase("")) {
	        	servicoTipo.setDescricao(dsTipoServico);
	        	peloMenosUmParametroInformado = true;
	        	
	        }
	        // Descri��o Abreviada do Tipo de Servi�o
	        if (dsAbreviadaTipoServico != null && !dsAbreviadaTipoServico.trim().equalsIgnoreCase("")) {
	        	peloMenosUmParametroInformado = true;
	        	servicoTipo.setDescricaoAbreviada(dsAbreviadaTipoServico);
	        }
	        // Subgrupo do Tipo de Servi�o
	        
	        if (subgrupoTipoServico != null && !subgrupoTipoServico.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	        	ServicoTipoSubgrupo subTipo =new ServicoTipoSubgrupo();
	        	subTipo.setId(new Integer(subgrupoTipoServico));
	        	servicoTipo.setServicoTipoSubgrupo(subTipo); 
	        }
	        
/*	        // Indicador de Pavimento
	        if (indicadorPavimento != null && !indicadorPavimento.trim().equalsIgnoreCase("")
	        		&& !indicadorPavimento.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorPavimento( new Short(indicadorPavimento));
	        }
*/	        
	        // Indicador de pavimento de rua
	        if (indicadorPavimentoRua != null && !indicadorPavimentoRua.trim().equalsIgnoreCase("") && !indicadorPavimentoRua.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorPavimentoRua( new Short(indicadorPavimentoRua));
	        }
	        
	        // Indicador de pavimento de cal�ada
	        if (indicadorPavimentoCalcada != null && !indicadorPavimentoCalcada.trim().equalsIgnoreCase("")	&& !indicadorPavimentoCalcada.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorPavimentoCalcada( new Short(indicadorPavimentoCalcada));
	        }
	        
	        
	        
	        // Valor Servi�o In�cial e Final
	        if ((valorServicoInicial != null && !valorServicoInicial.trim().equalsIgnoreCase("")) || (tempoMedioExecucaoFinal != null && !tempoMedioExecucaoFinal.trim().equalsIgnoreCase(""))) {
	        	valorServicoInicial = valorServicoInicial.replace(".", "");
	        	valorServicoInicial = valorServicoInicial.replace(",", ".");
	        	
	        	valorServicoFinal = valorServicoFinal.replace(".", "");
	        	valorServicoFinal = valorServicoFinal.replace(",", ".");
	        	
	            peloMenosUmParametroInformado = true;
	        }
	       	 // Indicador Atualiza��o do Comercial
	        if (indicadorAtualizacaoComercio != null && !indicadorAtualizacaoComercio.trim().equalsIgnoreCase("")
	        		&& !indicadorAtualizacaoComercio.trim().equalsIgnoreCase("0")
	        		&& !indicadorAtualizacaoComercio.trim().equalsIgnoreCase("4")) {
	           
	        	peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorAtualizaComercial(new Short(indicadorAtualizacaoComercio));  
	        }
	        // Indicador Servi�o Terceirizado
	        if (indicadorServicoTerceirizado != null && !indicadorServicoTerceirizado.trim().equalsIgnoreCase("")
	        		&& !indicadorServicoTerceirizado.trim().equalsIgnoreCase("")
	        		&& !indicadorServicoTerceirizado.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorTerceirizado(new Short(indicadorServicoTerceirizado));  
	        } 
	        // C�digo do Servi�o
	        if (codigoServico != null && !codigoServico.trim().equalsIgnoreCase("")
	        		&& !codigoServico.trim().equalsIgnoreCase("0")
	        		&& !codigoServico.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setCodigoServicoTipo(codigoServico);
	        }
	        
	        // Tipo de D�bito
	        if (tipoDebito != null && !tipoDebito.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            DebitoTipo debitoTipo = new DebitoTipo();
	            debitoTipo.setId(new Integer(tipoDebito));
	            servicoTipo.setDebitoTipo(debitoTipo);
	        }
	        // Tipo de Cr�dito
	        if (tipoCredito != null && !tipoCredito.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            CreditoTipo creditoTipo = new CreditoTipo();
	            creditoTipo.setId(new Integer(tipoCredito));
	            servicoTipo.setCreditoTipo(creditoTipo);
	        }
	        // Prioridade do Servi�o
	        if (prioridadeServico != null && !prioridadeServico.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            ServicoTipoPrioridade servicoPrioridade = new ServicoTipoPrioridade();
	            servicoPrioridade.setId(new Integer(prioridadeServico));
	            servicoTipo.setServicoTipoPrioridade(servicoPrioridade);	
	        }
	        // Perfil do Servi�o
	        if (perfilServco != null && !perfilServco.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();
	            servicoPerfilTipo.setId(new Integer(perfilServco));
	            servicoTipo.setServicoPerfilTipo(servicoPerfilTipo);	
	        }
	        // Tipo de Servi�o de Refer�ncia
	        if (tipoServicoReferencia != null && !tipoServicoReferencia.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            ServicoTipoReferencia servicoTipoReferencia = new ServicoTipoReferencia();
	            servicoTipoReferencia.setId(new Integer(tipoServicoReferencia));
	            servicoTipo.setServicoTipoReferencia(servicoTipoReferencia);	
	        }
	        
	        if(pesquisarTipoServicoActionForm.getArrayAtividade() != null 
	        		&& !pesquisarTipoServicoActionForm.getArrayAtividade().isEmpty()){
	        	peloMenosUmParametroInformado = true;
	        }
	        
	        if(pesquisarTipoServicoActionForm.getArrayMateriais() != null 
	        		&& !pesquisarTipoServicoActionForm.getArrayMateriais().isEmpty()){
	        	peloMenosUmParametroInformado = true;
	        }
	        
	        if(tempoMedioExecucaoInicial != null && !tempoMedioExecucaoInicial.trim().equals("")){
	        	peloMenosUmParametroInformado = true;
	        }
	        
	        if(tempoMedioExecucaoFinal != null && !tempoMedioExecucaoFinal.trim().equals("")){
	        	peloMenosUmParametroInformado = true;
	        }
	        
	        // Atividades do Tipo de Servi�o
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException(
	                    "atencao.filtro.nenhum_parametro_informado");
	        }
	       
	        Fachada fachada = Fachada.getInstancia();
	        
	        
//	      1� Passo - Pegar o total de registros atrav�s de um count da
			// consulta que aparecer� na tela
			Integer totalRegistros = fachada
					.filtrarSTCount(servicoTipo,pesquisarTipoServicoActionForm.getAtividades(),
					        pesquisarTipoServicoActionForm.getArrayMateriais(),valorServicoInicial,
					        valorServicoFinal,tempoMedioExecucaoInicial,tempoMedioExecucaoFinal,
					        tipoPesquisa,tipoPesquisaAbreviada);

			// 2� Passo - Chamar a fun��o de Pagina��o passando o total de
			// registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			// 3� Passo - Obter a cole��o da consulta que aparecer� na tela
			// passando o numero de paginas
			// da pesquisa que est� no request

			Collection servicosTipo = fachada.filtrarST(servicoTipo,pesquisarTipoServicoActionForm.getAtividades(),
			        pesquisarTipoServicoActionForm.getArrayMateriais(),valorServicoInicial,valorServicoFinal,
			        tempoMedioExecucaoInicial,tempoMedioExecucaoFinal,tipoPesquisa,tipoPesquisaAbreviada,
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));


			if (servicosTipo == null || servicosTipo.isEmpty()) {
				// Nenhum cliente cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			sessao.setAttribute("servicosTipo", servicosTipo);
			sessao.setAttribute("numeroPaginasPesquisa",httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));
	        
			

			return retorno;
	        
//	        Collection servicosTipo = fachada.filtrarST(servicoTipo,pesquisarTipoServicoActionForm.getAtividades(),
//	        pesquisarTipoServicoActionForm.getArrayMateriais(),valorServicoInicial,valorServicoFinal,tempoMedioExecucaoInicial,tempoMedioExecucaoFinal,tipoPesquisa,tipoPesquisaAbreviada);

	        /*if (servicosTipo != null &&
	        		!servicosTipo.isEmpty()) {
	        	// Aciona o controle de pagina��o para que sejam pesquisados apenas
				 //os registros que aparecem na p�gina
				int qtdPesquisa = servicosTipo.size();
	        	ActionForward resultado = controlarPaginacao(httpServletRequest, retorno, qtdPesquisa);
				sessao.setAttribute("servicosTipo", servicosTipo);
				return resultado;
	        } else {
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "servicoTipo");
	        }*/
	    }
	}
