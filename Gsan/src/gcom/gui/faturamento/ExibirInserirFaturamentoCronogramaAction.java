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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoAtividade;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupoCronogramaMensal;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
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
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirFaturamentoCronogramaAction extends GcomAction {

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("inserirFaturamentoCronograma");

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        FaturamentoActionForm faturamentoActionForm = (FaturamentoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
        //CARREGANDO OS GRUPOS
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        Collection faturamentoGrupos = null;

        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,
        ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
        faturamentoGrupos = fachada.pesquisar(filtroFaturamentoGrupo,
        FaturamentoGrupo.class.getName());
        
        if ( Util.isVazioOrNulo(faturamentoGrupos)) {
            throw new ActionServletException("erro.naocadastrado", null,
            "grupo de faturamento");
        }
        
        sessao.setAttribute("faturamentoGrupos", faturamentoGrupos);

        
        //CARREGANDO AS ATIVIDADES
        FiltroFaturamentoAtividade filtroFaturamentoAtividade = new FiltroFaturamentoAtividade();
        Collection faturamentoAtividades = null;
        
        filtroFaturamentoAtividade.adicionarParametro(new ParametroSimples(
        FiltroFaturamentoAtividade.INDICADOR_USO,
        ConstantesSistema.INDICADOR_USO_ATIVO));
        
	    filtroFaturamentoAtividade.setCampoOrderBy(FiltroFaturamentoAtividade.ORDEM_REALIZACAO);
	    faturamentoAtividades = fachada.pesquisar( filtroFaturamentoAtividade , FaturamentoAtividade.class.getName());
	    
	    
	    //MONTANDO O OBJETO PARA EXIBI��O
	    if(httpServletRequest.getParameter("menu")!=null && 
	       httpServletRequest.getParameter("menu").equals("sim")){
	    
	    	Collection faturamentoAtividadesHelp  = new ArrayList();
	    
	    	for (Iterator iter = faturamentoAtividades.iterator(); iter.hasNext();) {
			
		    	FaturamentoAtividade faturamentoAtividade = (FaturamentoAtividade) iter.next();
				
		    	FaturamentoCronogramaAtividadeHelp faturamentoCronogramaAtividadeHelp =
				new FaturamentoCronogramaAtividadeHelp();
				
		    	faturamentoCronogramaAtividadeHelp.setFaturamentoAtividade(faturamentoAtividade);
				faturamentoAtividadesHelp.add(faturamentoCronogramaAtividadeHelp);
				
				/**
				 * Autor: Hugo Leonardo
				 * Data: 22/06/2010
				 * CRC: 4694
				 * Analista: Eduardo Borges
				 * 
				 * O indicador de comando da atividade vai ser definido pelo (FTAT_ICCOMANDO).
				 * Caso (FTAT_ICCOMANDO = 1) ser� Ativo e Desmarcado;
				 * Caso (FTAT_ICCOMANDO = 2) ser� Inativo;
				 * Caso (FTAT_ICCOMANDO = 3) ser� Ativo e Marcado;
				 */
			
				if (faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade() != null
						&& (faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade().intValue() == ConstantesSistema.SIM.intValue()
								|| faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade().intValue() == ConstantesSistema.TODOS.intValue())){
				
					faturamentoCronogramaAtividadeHelp.setComandar(faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade().toString());
					/*
					if (faturamentoAtividade.getId().intValue() != FaturamentoAtividade.FATURAR_GRUPO.intValue()){
						faturamentoCronogramaAtividadeHelp.setComandar(faturamentoAtividade.getIndicadorPossibilidadeComandoAtividade().toString());
					}else{
						faturamentoCronogramaAtividadeHelp.setComandar("2");
					}
					*/
				}	 
				/*
				// 	Author:Hugo Amorim Data:28/04/2010 CRC:4040
				//
				//	Em Gsan, Faturamento, cronograma, manter cronograma, 
				// coluna "comandar" todos os checkbox�s venham por default desmarcados. 
				// (Isso s� deve ocorre quando a empresa for cosanpa)
				//	
				if(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("COSANPA")){
					faturamentoCronogramaAtividadeHelp.setComandar("2");
				}	
				*/
			}
  
	        if ( faturamentoAtividades == null || faturamentoAtividades.isEmpty() ) {
	        	throw new ActionServletException("atencao.naocadastrado", null,"atividade");
	        } 
	        else {     	
	        	sessao.setAttribute("faturamentoAtividades", faturamentoAtividadesHelp);       	
	        }        
	    }
	    
        sessao.setAttribute("FaturamentoActionForm", faturamentoActionForm);
        
        String obterDataRealizacaoAnterior = httpServletRequest.getParameter("obterDataRealizacaoAnterior");
        
        this.obterDataRealizacaoAnteriorPorAtividade(obterDataRealizacaoAnterior, faturamentoActionForm,
        fachada, sessao);
        
        //DATA ATUAL
        httpServletRequest.setAttribute("dataAtual", Util.formatarData(new Date()));
        
		return retorno;
	}
    
    
    public void obterDataRealizacaoAnteriorPorAtividade(String obterDataRealizacaoAnterior,
    		FaturamentoActionForm faturamentoActionForm, Fachada fachada, HttpSession sessao){
    	
    	if (obterDataRealizacaoAnterior != null && 
    		!obterDataRealizacaoAnterior.equalsIgnoreCase("")) {

			if (faturamentoActionForm.getIdGrupoFaturamento() != null && 
				faturamentoActionForm.getIdGrupoFaturamento().toString().equals("-1")) {
				
				throw new ActionServletException(
				"atencao.preencha.campos.obrigatorio");
			}
			
			if (faturamentoActionForm.getMesAno() != null && 
				faturamentoActionForm.getMesAno().toString().equals("")) {
				
				throw new ActionServletException(
				"atencao.preencha.campos.obrigatorio");
			}
        	
			String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(faturamentoActionForm
					.getMesAno());
			
			// Desabilita o check caso o AnoMes informado seja superior ao AnoMes do FaturamentoGrupo. 
			if(faturamentoActionForm.getMesAno() != null &&
					!faturamentoActionForm.getMesAno().toString().equals("")){
				
				FiltroFaturamentoGrupo filtroFaturamentoGrupo= new FiltroFaturamentoGrupo();
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.ID, faturamentoActionForm.getIdGrupoFaturamento()));
				
				Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo,
						FaturamentoGrupo.class.getName());
				
				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) 
					Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
				
				if(Util.compararAnoMesReferencia(anoMes, faturamentoGrupo.getAnoMesReferencia().toString(), ">")){
					sessao.setAttribute("desabilitaCheck", true);
				}else{
					sessao.removeAttribute("desabilitaCheck");
				}
			}
        	
        	//OBTENDO O CRONOGRAMA DO MES ANTERIOR
        	//--------------------------------------------------------------------------------------------------------
        	FiltroFaturamentoGrupoCronogramaMensal filtroCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();

			filtroCronogramaMensal.adicionarParametro(new ParametroSimples(
			FiltroFaturamentoGrupoCronogramaMensal.FATURAMENTO_GRUPO,
			faturamentoActionForm.getIdGrupoFaturamento()));

			Integer anoMesMenosUm = Util.subtraiAteSeisMesesAnoMesReferencia(
			new Integer(anoMes), 1);

			filtroCronogramaMensal.adicionarParametro(new ParametroSimples(
			FiltroFaturamentoGrupoCronogramaMensal.REFERENCIA, anoMesMenosUm));

			Collection colecaoCronogramaMensal = fachada.pesquisar(filtroCronogramaMensal,
			FaturamentoGrupoCronogramaMensal.class.getName());

			FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal) 
			Util.retonarObjetoDeColecao(colecaoCronogramaMensal);

			Integer idFaturamentoGrupoCronogramaMensal = null;
			
			if( faturamentoGrupoCronogramaMensal != null ){
			
				idFaturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal.getId();
			}
			//--------------------------------------------------------------------------------------------------------

			
			//OBTENDO A DATA DE REALIZA��O DA ATIVIDADE EM QUEST�O PARA O MES ANTERIOR
			Collection faturamentoAtividadesHelpSessao = (Collection) sessao
			.getAttribute("faturamentoAtividades");
			
			for (Iterator iter = faturamentoAtividadesHelpSessao.iterator(); iter.hasNext();) {

				FaturamentoCronogramaAtividadeHelp helper = 
				(FaturamentoCronogramaAtividadeHelp) iter.next();
		
				FiltroFaturamentoAtividadeCronograma filtroAtividadeCronograma = 
				new FiltroFaturamentoAtividadeCronograma();
					
				FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;
					
				if( faturamentoGrupoCronogramaMensal != null ){	
					
					filtroAtividadeCronograma
					.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ID,
					idFaturamentoGrupoCronogramaMensal));

					filtroAtividadeCronograma
					.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID, 
					helper.getFaturamentoAtividade().getId()));

					Collection colecaoAtividadeCronograma = fachada.pesquisar(
					filtroAtividadeCronograma, FaturamentoAtividadeCronograma.class.getName());

					faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
					.retonarObjetoDeColecao(colecaoAtividadeCronograma);

				}
					
				Date dataMesAnterior = null;

				if (faturamentoAtividadeCronograma != null && 
					faturamentoAtividadeCronograma.getDataRealizacao() != null) {

					dataMesAnterior = faturamentoAtividadeCronograma
					.getDataRealizacao();

				}
				else if ( faturamentoAtividadeCronograma != null && 
						faturamentoAtividadeCronograma.getDataPrevista() != null && dataMesAnterior == null ){
						
					dataMesAnterior = faturamentoAtividadeCronograma
					.getDataPrevista();

				}
				
				
				helper.setDataRealizacaoMesAnterior(Util.formatarData(dataMesAnterior));
			}
		}
    }

}
