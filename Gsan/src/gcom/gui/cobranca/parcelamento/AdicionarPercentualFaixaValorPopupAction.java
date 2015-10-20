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
package gcom.gui.cobranca.parcelamento;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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


public class AdicionarPercentualFaixaValorPopupAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("inserirPrestacoesParcelamentoPerfilPercentualFaixaValorPopup");

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        //Inst�ncia do formul�rio que est� sendo utilizado
        InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm = 
        	(InserirPrestacoesParcelamentoPerfilActionForm) actionForm;
        
        //Par�metros recebidos para adicionar um Percentual Por Faixa de Valor
        String valorMaximo = inserirPrestacoesParcelamentoPerfilActionForm.getValorMaxPercFaixaValor();
        String percentual = inserirPrestacoesParcelamentoPerfilActionForm.getPercentualPercFaixaValor();
        
        Collection collectionParcelamentoFaixaValor = null;
    	ParcelamentoFaixaValor parcelamentoFaixaValor = new ParcelamentoFaixaValor();
        
        if (sessao.getAttribute("collectionParcelamentoFaixaValor") != null) {
        	collectionParcelamentoFaixaValor = (Collection) sessao
                    .getAttribute("collectionParcelamentoFaixaValor");
        } else {
        	collectionParcelamentoFaixaValor = new ArrayList();
        }
        
        //Valida��o dos campos recebidos
        if (valorMaximo == null || valorMaximo.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.campo_texto.obrigatorio", null, "Valor M�ximo");
        }
        parcelamentoFaixaValor.setValorFaixa(Util.formatarMoedaRealparaBigDecimal(valorMaximo));
        
        if (percentual == null || percentual.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.campo_texto.obrigatorio", null, "Percentual");
        }
        
		if (collectionParcelamentoFaixaValor != null && !collectionParcelamentoFaixaValor.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se  Valor M�ximo ja foi informada	
			ParcelamentoFaixaValor parcelamentoFaixaValorAntigo = null;
			Iterator iterator = collectionParcelamentoFaixaValor.iterator();
		
			while (iterator.hasNext()) {
				parcelamentoFaixaValorAntigo = (ParcelamentoFaixaValor) iterator.next();
				
				if ((Util.formatarMoedaRealparaBigDecimal(valorMaximo)).equals
						(parcelamentoFaixaValorAntigo.getValorFaixa())){
			        //Limpa o formul�rio que adiciona Percentual Por Faixa de Valor
			        inserirPrestacoesParcelamentoPerfilActionForm.setValorMaxPercFaixaValor("");
			        inserirPrestacoesParcelamentoPerfilActionForm.setPercentualPercFaixaValor("");
					
					// Valor M�ximo j� informado.
					throw new ActionServletException(
					"atencao.valor_maximo_ja_informado");
				}
			}
		}
		
		//Mostrar as liga��es de �gua
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
    	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.SIM));
    	
    	Collection colLigacaoAguaSituacao = getFachada().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
    	
    	httpServletRequest.setAttribute("colLigacaoAguaSituacao", colLigacaoAguaSituacao);
    	
		parcelamentoFaixaValor.setPercentualFaixa(Util.formatarMoedaRealparaBigDecimal(percentual));

        parcelamentoFaixaValor.setUltimaAlteracao(new Date());
        
        collectionParcelamentoFaixaValor.add(parcelamentoFaixaValor);
        
        
        //Ordena a cole��o pelo valor m�ximo
		Collections.sort((List) collectionParcelamentoFaixaValor, new Comparator() {
			public int compare(Object a, Object b) {
				BigDecimal valorMax1 = new BigDecimal(((ParcelamentoFaixaValor) a).getValorFaixa().toString()) ;
				BigDecimal valorMax2 = new BigDecimal(((ParcelamentoFaixaValor) b).getValorFaixa().toString()) ;
		
				return valorMax1.compareTo(valorMax2);

			}
		});
				
        
        
        sessao.setAttribute("collectionParcelamentoFaixaValor",collectionParcelamentoFaixaValor);
        
        if (collectionParcelamentoFaixaValor == null || 
        		collectionParcelamentoFaixaValor.size() == 0){
			sessao.setAttribute("collectionParcelamentoFaixaValorVazia","1");
        }else{
			sessao.setAttribute("collectionParcelamentoFaixaValorVazia","2");
        }
        
        //Limpa o formul�rio que adiciona Percentual Por Faixa de Valor
        inserirPrestacoesParcelamentoPerfilActionForm.setValorMaxPercFaixaValor("");
        inserirPrestacoesParcelamentoPerfilActionForm.setPercentualPercFaixaValor("");
        
        return retorno;
    }

}
