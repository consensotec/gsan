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
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class AtualizarFaturamentoCronogramaAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        FaturamentoActionForm faturamentoActionForm = (FaturamentoActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        //Variavel para testar se o campo naum obrigatorio esta vazio
       // String testeFaturamentoNaoObrigatorio = "0";

     //   String idFaturamentoGrupoCronograma = (String) sessao
    //            .getAttribute("idGrupoFaturamento");
        
        //Estava sendo usado pelo trecho comentado por Anderson Italo
        //data:20/04/2010
        //SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        String mesAno = faturamentoActionForm.getMesAno();
        if(mesAno == null){
        	mesAno = (String)sessao.getAttribute("mesAno");
        }
//        Collection faturamentoAtividades = (Collection) sessao
//                .getAttribute("faturamentoAtividades");
//        Collection faturamentoAtividadeCronogramasVelhas = (Collection) sessao
//                .getAttribute("faturamentoAtividadeCronogramas");
        
        Collection colecaoFaturamentoAtividadeCronograma = (Collection) sessao
        	.getAttribute("colecaoFaturamentoAtividadeCronograma");
        
        Collection faturamentoAtividadeCronogramas = new ArrayList();
        
        //Colecao para guadar todas as atividades inclusive as que n�o v�o ser inseridas ou alteradas
        Collection colecaoTodasAtividades = new ArrayList();
        
        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;

        FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal)sessao.getAttribute("faturamentoGrupoCronogramaMensalSessao");
        
        //Instancia objeto FaturamenatoGrupo
        FiltroFaturamentoGrupo filtroFaturamentoGrupoObjeto = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupoObjeto.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, faturamentoGrupoCronogramaMensal
                .getFaturamentoGrupo().getId()));
        
        Collection colecaoFaturamentoGrupoObjeto = fachada.pesquisar(filtroFaturamentoGrupoObjeto, FaturamentoGrupo.class.getName());
        
        FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoFaturamentoGrupoObjeto);

//        faturamentoGrupo.setId(faturamentoGrupoCronogramaMensal
//                .getFaturamentoGrupo().getId());
        
        //Concatena ano mes para insercao
        String mes = mesAno.substring(0, 2);
        String ano = mesAno.substring(3, 7);

        mesAno = ano + "" + mes;
       	Iterator iteratorFaturamentoAtividadeCronogramas = colecaoFaturamentoAtividadeCronograma.iterator();
    		while (iteratorFaturamentoAtividadeCronogramas.hasNext()) {
    			
    			FaturamentoAtividadeAtualizarHelp fatAtividadeAtualizarHelp = (FaturamentoAtividadeAtualizarHelp)
    			iteratorFaturamentoAtividadeCronogramas.next();
    			
    			faturamentoAtividadeCronograma = fatAtividadeAtualizarHelp.
    			getFaturamentoAtividadeCronograma();
    			
	            
	            String dataPrevista = (String) httpServletRequest.getParameter("n"
	                    + fatAtividadeAtualizarHelp.getIdFaturamentoAtividade().toString());
	                      
	           if ( dataPrevista != null && !dataPrevista.equals("") && Util.validarDiaMesAno(dataPrevista) ) {
	        	   throw new ActionServletException("atencao.anomesreferencia.invalida", null , ""+dataPrevista);
	           }
    			
    			//String dataPrevista = fatAtividadeAtualizarHelp.getDataPrevista();
	            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
	
	            try {
	                if (dataPrevista != null && !dataPrevista.trim().equalsIgnoreCase("")) {
	                    faturamentoAtividadeCronograma.setDataPrevista(data
	                            .parse(dataPrevista));
	                    faturamentoAtividadeCronogramas
	                            .add(faturamentoAtividadeCronograma);
	                    colecaoTodasAtividades.add(faturamentoAtividadeCronograma);
	                } else if (faturamentoAtividadeCronograma
	                        .getFaturamentoAtividade()
	                        .getIndicadorObrigatoriedadeAtividade().intValue() == 1 &&
	                        (dataPrevista == null || dataPrevista.trim().equalsIgnoreCase(""))) {
	                    throw new ActionServletException(
	                    "atencao.campos_obrigatorios_cronograma_nao_preenchidos");
	                }else if (faturamentoAtividadeCronograma
	                        .getFaturamentoAtividade()
	                        .getIndicadorObrigatoriedadeAtividade().intValue() == 1) {
	                //	testeFaturamentoNaoObrigatorio = "1";
	                	colecaoTodasAtividades.add(faturamentoAtividadeCronograma);
	                }else{
	                	faturamentoAtividadeCronograma.setDataPrevista(null);
	                	colecaoTodasAtividades.add(faturamentoAtividadeCronograma);
	                	faturamentoAtividadeCronogramas.add(faturamentoAtividadeCronograma);
	                }
	                
	                //se foi marcado o check de comandar a atividade
	                //sera atribuido o valor de comando
	                //este valor ser� igual ao da data prevista            
	                String comandar = (String) httpServletRequest.getParameter("c"
	                        + faturamentoAtividadeCronograma.getId().toString());
	                        
	               if(comandar != null && !comandar.trim().equals("")
	            		   && dataPrevista != null && !dataPrevista.trim().equals("")){
		            	   
	            	   /* comentado por: Anderson Italo
		                * analista que solicitou: Aryede Lins
		                * motivo: Adequar-se ao que estava descrito no UC
		                * 4.2.1.	Atualiza a data e hora do comando (FTAC_TMCOMANDO=CURRENT TIMESTAMP)
		                * data: 20/04/2010*/
	            	   
	            	   	/*int diaVencimento = faturamentoGrupo.getDiaVencimento()
	                       .intValue();
			               String mesVencimento = String.valueOf(
			                       faturamentoGrupo.getAnoMesReferencia()
			                               .intValue()).substring(4, 6);
			               String anoVencimento = String.valueOf(
			                       faturamentoGrupo.getAnoMesReferencia()
			                               .intValue()).substring(0, 4);*/
			
			               /*
			                * Colocado por Raphael Rossiter em 05/05/2007
			                * Obtendo a data de vencimento do grupo
			                */ 
			               /*Date dataVencimento = fachada.obterDataVencimentoFaturamentoGrupo(diaVencimento, 
			               new Integer(mesVencimento), new Integer(anoVencimento));
			               faturamentoAtividadeCronograma.setComando(Util.converteStringParaDate(formatoData.format(dataVencimento)));
			               */
			               
			               faturamentoAtividadeCronograma.setComando(new Date());
	               }else{
	            	   faturamentoAtividadeCronograma.setComando(null);
	               }
	               
	            } catch (ParseException ex) {
	                throw new ActionServletException("errors.date", null,
	                        "data prevista");
	            }
	            
	        }

    		//Valida��o das seguencia das datas no cronograma e predecessora
    		fachada.validarFaturamentoCronogramaAtividadeMaiorQueMesAnoCronograma((new Integer(mesAno)).intValue(), colecaoTodasAtividades);
    		fachada.validarFaturamentoCronograma(colecaoTodasAtividades);
    		
    		

        /*if (faturamentoAtividadeCronogramas.size() < 5
                || (faturamentoAtividadeCronogramas.size() == 5 && testeFaturamentoNaoObrigatorio
                        .trim().equalsIgnoreCase("0"))) {
            throw new ActionServletException(
                    "atencao.campos_obrigatorios_cronograma_nao_preenchidos");
        }*/

        fachada.atualizarFaturamentoGrupoCronogramaMensal(
                faturamentoGrupoCronogramaMensal,
                faturamentoAtividadeCronogramas, colecaoTodasAtividades,usuarioLogado);
        
        
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, faturamentoGrupo.getId()));
        Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
        FaturamentoGrupo faturamentoGrupoDescricao = new FaturamentoGrupo();
        if(!colecaoFaturamentoGrupo.isEmpty()){
        	faturamentoGrupoDescricao = (FaturamentoGrupo)colecaoFaturamentoGrupo.iterator().next();
        }

        sessao.removeAttribute("faturamentoAtividades");
        sessao.removeAttribute("idGrupoFaturamento");
        montarPaginaSucesso(httpServletRequest,
        		"Cronograma de Faturamento do grupo "+ faturamentoGrupoDescricao.getId() 
                +" referente ao m�s/ano: " + faturamentoGrupoCronogramaMensal.getMesAno() +" atualizado com sucesso.",
                "Realizar outra Manuten��o de Cronograma de Faturamento",
                "exibirFiltrarFaturamentoCronogramaAction.do");

        return retorno;
    }

}
