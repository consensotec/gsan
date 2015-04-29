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
package gsan.gui.cobranca;

import gsan.cadastro.sistemaparametro.NacionalFeriado;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaAtividade;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.cobranca.FiltroCobrancaAtividade;
import gsan.cobranca.FiltroCobrancaGrupo;
import gsan.cobranca.bean.AcaoEAtividadeCobrancaHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class ExibirInserirCronogramaCobrancaAction extends GcomAction {
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

		// Seta a a��o de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirCronogramaCobranca");
		
        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        
        CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;
        
        FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
        filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
		
		Collection gruposCobranca = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
		sessao.setAttribute("gruposCobranca", gruposCobranca);
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_CRONOGRAMA,CobrancaAcao.INDICADOR_CRONOGRAMA_ATIVO));
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.ORDEM_REALIZACAO);
		
		Collection acoesCobranca = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
		sessao.setAttribute("acoesCobranca", acoesCobranca);
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
//		Collection atividadesCobranca = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());

		
		//-------------------------------------------------------------------------
		
		//RM93 - adicionado por Vivianne Sousa - analista:Rosana Carvalho
		String limparForm = httpServletRequest.getParameter("limparForm");
		if(limparForm != null && limparForm.equals("1")){
			cobrancaActionForm.setIdGrupoCobranca(null);
			cobrancaActionForm.setDataInicio("");
			cobrancaActionForm.setMesAno("");
		}
		
		
		String exibirDataInicio = "2";
		if(cobrancaActionForm.getIdGrupoCobranca() != null &&
			!cobrancaActionForm.getIdGrupoCobranca().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)
			&& verificarExecucaoAutomaticaGrupo(cobrancaActionForm.getIdGrupoCobranca())){
			
			exibirDataInicio = "1";
	
			String calcularDataPrevista = httpServletRequest.getParameter("calcularDataPrevista");
			
			if(calcularDataPrevista != null && calcularDataPrevista.equals("1")){
				if(cobrancaActionForm.getDataInicio() == null){
					throw new ActionServletException("atencao.campo.informada", null, "Data de In�cio");
				}
			
				Iterator iterAcoesCobranca = acoesCobranca.iterator();
				Collection colecaoAcaoEAtividadeCobranca = new ArrayList();
				boolean primeiraVez = true;
				Map dataPrevistaPredecessora = new HashMap();
				Date dataAcaoCobrancaAnterior = null;
				Date dataAtividadeCobrancaEncerrarAnterior = null;
				//FERIADO NACIONAL
				Collection<NacionalFeriado> colecaoFeriadosNacionais = fachada.pesquisarFeriadosNacionais();
				while (iterAcoesCobranca.hasNext()) {
					
					CobrancaAcao cobrancaAcao = (CobrancaAcao) iterAcoesCobranca.next();
					AcaoEAtividadeCobrancaHelper helper = new AcaoEAtividadeCobrancaHelper();
					helper.setAcaoCobranca(cobrancaAcao);
					
					Date dataTeste = Util.converteStringParaDate(cobrancaActionForm.getDataInicio());
					
					if(!primeiraVez){
						if(cobrancaAcao.getCobrancaAcaoPredecessora() != null){
							dataTeste = (Date)dataPrevistaPredecessora.get(cobrancaAcao.getCobrancaAcaoPredecessora().getId());
							
							if(cobrancaAcao.getNumeroDiasMinimoAcaoPrecedente() != null){
								dataTeste = Util.adicionarNumeroDiasUteisDeUmaData(dataTeste, cobrancaAcao.getNumeroDiasMinimoAcaoPrecedente(),colecaoFeriadosNacionais,null);
							}	
						}else if(dataAcaoCobrancaAnterior != null){
							dataTeste = dataAcaoCobrancaAnterior;
							if(cobrancaAcao.getNumeroDiasMinimoAcaoPrecedente() != null){
								dataTeste = Util.adicionarNumeroDiasUteisDeUmaData(dataTeste, cobrancaAcao.getNumeroDiasMinimoAcaoPrecedente(),colecaoFeriadosNacionais,null);
							}
						}
					}
					
					primeiraVez = true;
					
					filtroCobrancaAtividade = new FiltroCobrancaAtividade();
					filtroCobrancaAtividade.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividadePredecessora");
					filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
					filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
					filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
					filtroCobrancaAtividade.adicionarParametro(new ParametroNulo(FiltroCobrancaAtividade.ID_COBRANCA_ACAO));
					
					Collection atividadesCobranca = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
					
					//pesquisa cobran�a atividade especifica da cobran�a a��o 
					filtroCobrancaAtividade = new FiltroCobrancaAtividade();
					filtroCobrancaAtividade.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividadePredecessora");
					filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
					filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
					filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
					filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAtividade.ID_COBRANCA_ACAO, cobrancaAcao.getId()));
					 Collection atividadesCobrancaEspecifica = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
	
					Iterator iterAtividadesCobranca = null;
					if(atividadesCobrancaEspecifica != null && !atividadesCobrancaEspecifica.isEmpty()){
		
						atividadesCobranca.addAll(atividadesCobrancaEspecifica);
					}
					 
					iterAtividadesCobranca = atividadesCobranca.iterator();
					
					while (iterAtividadesCobranca.hasNext()) {
						CobrancaAtividade cobrancaAtividade = (CobrancaAtividade) iterAtividadesCobranca.next();
						
						if(primeiraVez){
							//guarda a data da a��o de cobran�a
							primeiraVez = false;
							if(dataAtividadeCobrancaEncerrarAnterior != null){
								dataAcaoCobrancaAnterior = Util.adicionarNumeroDiasUteisDeUmaData(dataAtividadeCobrancaEncerrarAnterior,1,colecaoFeriadosNacionais,null);
								dataTeste = dataAcaoCobrancaAnterior;
								dataPrevistaPredecessora.put(cobrancaAcao.getId(), dataAcaoCobrancaAnterior);
							}else{
								dataPrevistaPredecessora.put(cobrancaAcao.getId(),dataTeste);
								dataAcaoCobrancaAnterior = dataTeste;
							}
						}else{
							//calcula a data da atividade de cobran�a
							Integer numeroDiasExecucao = 0; 
								//cobrancaAtividade.getNumeroDiasExecucao();
							
//							if(numeroDiasExecucao != null){
								
								if(cobrancaAtividade.getId().equals(CobrancaAtividade.ENCERRAR_OS)){
									
									if(cobrancaAcao.getNumerodiasEncerrarOSAtividade() != null){
										numeroDiasExecucao = cobrancaAcao.getNumerodiasEncerrarOSAtividade();
									}
									
								}else{
									if(cobrancaAcao.getNumerodiasEncerrarAtividade() != null){
										numeroDiasExecucao =  cobrancaAcao.getNumerodiasEncerrarAtividade();
									}
								}
								
								
								
								dataTeste = Util.adicionarNumeroDiasUteisDeUmaData(dataTeste,numeroDiasExecucao,colecaoFeriadosNacionais,null);
//							}
						}
						cobrancaAtividade.setDataPrevista(dataTeste);
						if(cobrancaAtividade.getId().equals(CobrancaAtividade.ENCERRAR)){
							dataAtividadeCobrancaEncerrarAnterior  = dataTeste;
						}
					}
			
					helper.setAtividadesCobranca(atividadesCobranca);
					colecaoAcaoEAtividadeCobranca.add(helper);
				}
				sessao.setAttribute("colecaoAcaoEAtividadeCobranca",colecaoAcaoEAtividadeCobranca);
			}
		}else{
			

			Iterator iterAcoesCobranca = acoesCobranca.iterator();
			Collection colecaoAcaoEAtividadeCobranca = new ArrayList();
			
			while (iterAcoesCobranca.hasNext()) {
				
				CobrancaAcao cobrancaAcao = (CobrancaAcao) iterAcoesCobranca.next();
				AcaoEAtividadeCobrancaHelper helper = new AcaoEAtividadeCobrancaHelper();
				helper.setAcaoCobranca(cobrancaAcao);
				
				filtroCobrancaAtividade = new FiltroCobrancaAtividade();
				filtroCobrancaAtividade.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividadePredecessora");
				filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
				filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
				filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
						FiltroCobrancaAtividade.ID_COBRANCA_ACAO, cobrancaAcao.getId()));
				Collection atividadesCobranca  = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());

				if(atividadesCobranca == null || atividadesCobranca.isEmpty()){
					
					filtroCobrancaAtividade = new FiltroCobrancaAtividade();
					filtroCobrancaAtividade.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividadePredecessora");
					filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
					filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
					filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
					filtroCobrancaAtividade.adicionarParametro(new ParametroNulo(FiltroCobrancaAtividade.ID_COBRANCA_ACAO));
					
					atividadesCobranca = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
				}
					
				helper.setAtividadesCobranca(atividadesCobranca);
				colecaoAcaoEAtividadeCobranca.add(helper);

			}
			
			sessao.setAttribute("colecaoAcaoEAtividadeCobranca",colecaoAcaoEAtividadeCobranca);
		}
		
		httpServletRequest.setAttribute("exibirDataInicio",exibirDataInicio);
		//-------------------------------------------------------------------------
		
//		sessao.setAttribute("atividadesCobranca", atividadesCobranca);
		
		filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAtividade.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividadePredecessora");
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_OBRIGATORIEDADE, CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO));
		filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
		
		Collection atividadesCobrancaObrigatoriedadeAtivo = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
		sessao.setAttribute("atividadesCobrancaObrigatoriedadeAtivo", atividadesCobrancaObrigatoriedadeAtivo);
		
		return retorno;
	}
	
	 private boolean verificarExecucaoAutomaticaGrupo(String grupoCobranca){
	    	
    	boolean retorno = false;
    	
    	if(grupoCobranca != null && !grupoCobranca.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
    		
    		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
            filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, new Integer(grupoCobranca)));

     		Collection gruposCobranca = getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
     		CobrancaGrupo cobrancaGrupo = (CobrancaGrupo)Util.retonarObjetoDeColecao(gruposCobranca);
     		
     		if(cobrancaGrupo.getIndicadorExecucaoAutomatica().equals(ConstantesSistema.SIM)){
     			retorno = true;
     		}
    	}
    	
    
		 
    	return retorno;
    }
	
	
}
