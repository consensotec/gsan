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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.CondicaoAbastecimentoAgua;
import gcom.cadastro.localidade.FiltroCondicaoAbastecimentoAgua;
import gcom.cadastro.localidade.FiltroGrauDificuldadeExecucao;
import gcom.cadastro.localidade.FiltroGrauRiscoSegurancaFisica;
import gcom.cadastro.localidade.GrauDificuldadeExecucao;
import gcom.cadastro.localidade.GrauRiscoSegurancaFisica;
import gcom.cadastro.localidade.QuadraFace;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroBacia;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdicionarQuadraFaceAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("adicionarQuadraFace");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm = (AdicionarQuadraFaceActionForm) actionForm;
        
        //COLE��O PARA APRESENTA��O NA TELA
        Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");
        
        //PARA INSERIR
        inserirQuadraFace(httpServletRequest, sessao, fachada, adicionarQuadraFaceActionForm,
    	colecaoQuadraFace);
        
        //PARA ATUALIZAR
        atualizarQuadraFace(httpServletRequest, sessao, fachada, adicionarQuadraFaceActionForm,
        colecaoQuadraFace);
        
        return retorno;
	}
	
	private void inserirQuadraFace(HttpServletRequest httpServletRequest, HttpSession sessao, Fachada fachada,
			AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm,
			Collection colecaoQuadraFace){
		
		if (adicionarQuadraFaceActionForm.getAcao() != null
	        && adicionarQuadraFaceActionForm.getAcao().equalsIgnoreCase("inserir")){
			
			QuadraFace quadraFace = new QuadraFace();
			
			//N�MERO DA FACE
			quadraFace.setNumeroQuadraFace(Integer.valueOf(adicionarQuadraFaceActionForm.getNumeroFace()));
			
			//INDICADOR REDE DE �GUA
			quadraFace.setIndicadorRedeAgua(Short.valueOf(adicionarQuadraFaceActionForm.getIndicadorRedeAguaAux()));
			
			//INDICADOR REDE DE ESGOTO
			quadraFace.setIndicadorRedeEsgoto(Short.valueOf(adicionarQuadraFaceActionForm.getIndicadorRedeEsgotoAux()));
			
			//BACIA
			if (adicionarQuadraFaceActionForm.getBaciaID() != null &&
				!adicionarQuadraFaceActionForm.getBaciaID().equals("")){
				
				FiltroBacia filtroBacia = new FiltroBacia();
				
				filtroBacia.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgoto");
				
				filtroBacia.adicionarParametro(new ParametroSimples(FiltroBacia.ID, 
				Integer.valueOf(adicionarQuadraFaceActionForm.getBaciaID())));
				
				Collection colecaoBacia = fachada.pesquisar(filtroBacia, Bacia.class.getName());
				
				Bacia bacia = (Bacia) Util.retonarObjetoDeColecao(colecaoBacia);
				
				quadraFace.setBacia(bacia);
			}
			
			//DISTRITO_OPERACIONAL
			if (adicionarQuadraFaceActionForm.getDistritoOperacionalID() != null &&
				!adicionarQuadraFaceActionForm.getDistritoOperacionalID().equals("")){
				
				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
				
				filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, 
				Integer.valueOf(adicionarQuadraFaceActionForm.getDistritoOperacionalID())));
				
				Collection colecaoDistritoOperacional = fachada.pesquisar(filtroDistritoOperacional, 
				DistritoOperacional.class.getName());
				
				DistritoOperacional distritoOperacional = 
				(DistritoOperacional) Util.retonarObjetoDeColecao(colecaoDistritoOperacional);
				
				quadraFace.setDistritoOperacional(distritoOperacional);
			}
			
			// GRAU DE DIFICULDADE DE EXECU��O
			if(adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID() != null
					&& !adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID().equals("-1")){
				
				FiltroGrauDificuldadeExecucao filtroGrauDificuldadeExecucao = new FiltroGrauDificuldadeExecucao();
				
				filtroGrauDificuldadeExecucao.adicionarParametro(new ParametroSimples(
						FiltroGrauDificuldadeExecucao.ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID())));
				
				Collection colecaoGrauDificuldadeExecucao = fachada.pesquisar(filtroGrauDificuldadeExecucao, 
						GrauDificuldadeExecucao.class.getName());
						
				GrauDificuldadeExecucao grauDificuldadeExecucao = 
						(GrauDificuldadeExecucao) Util.retonarObjetoDeColecao(colecaoGrauDificuldadeExecucao);
						
				quadraFace.setGrauDificuldadeExecucao(grauDificuldadeExecucao);
				
			}
			
			// GRAU DE RISCO DE SEGURAN�A F�SICA
			if(adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID() != null
					&& !adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID().equals("-1")){
				
				FiltroGrauRiscoSegurancaFisica filtroGrauRiscoSegurancaFisica = new FiltroGrauRiscoSegurancaFisica();
				
				filtroGrauRiscoSegurancaFisica.adicionarParametro(new ParametroSimples(
						FiltroGrauRiscoSegurancaFisica.ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID())));
				
				Collection colecaoGrauRiscoSegurancaFisica = fachada.pesquisar(filtroGrauRiscoSegurancaFisica, 
						GrauRiscoSegurancaFisica.class.getName());
						
				GrauRiscoSegurancaFisica grauRiscoSegurancaFisica = 
						(GrauRiscoSegurancaFisica) Util.retonarObjetoDeColecao(colecaoGrauRiscoSegurancaFisica);
						
				quadraFace.setGrauRiscoSegurancaFisica(grauRiscoSegurancaFisica);
				
			}
			
			// N�VEL DE PRESS�O
			if(adicionarQuadraFaceActionForm.getNivelPressaoID() != null
					&& !adicionarQuadraFaceActionForm.getNivelPressaoID().equals("-1")){
				
				// GRAU INTERMIT�NCIA
				if(adicionarQuadraFaceActionForm.getGrauIntermitenciaID() != null
						&& !adicionarQuadraFaceActionForm.getGrauIntermitenciaID().equals("-1")){
					
					FiltroCondicaoAbastecimentoAgua filtroCondicaoAbastecimentoAgua = new FiltroCondicaoAbastecimentoAgua();
					
					filtroCondicaoAbastecimentoAgua.adicionarParametro(new ParametroSimples(
							FiltroCondicaoAbastecimentoAgua.GRAU_INTERMITENCIA_ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauIntermitenciaID())));

					filtroCondicaoAbastecimentoAgua.adicionarParametro(new ParametroSimples(
							FiltroCondicaoAbastecimentoAgua.NIVEL_PRESSAO_ID, Integer.valueOf(adicionarQuadraFaceActionForm.getNivelPressaoID())));
					
					Collection colecaoCondicaoAbastecimentoAgua = fachada.pesquisar(filtroCondicaoAbastecimentoAgua, 
							CondicaoAbastecimentoAgua.class.getName());
							
					CondicaoAbastecimentoAgua condicaoAbastecimentoAgua = 
							(CondicaoAbastecimentoAgua) Util.retonarObjetoDeColecao(colecaoCondicaoAbastecimentoAgua);
							
					quadraFace.setCondicaoAbastecimentoAgua(condicaoAbastecimentoAgua);
					
				}
			}			
			
			//INDICADOR DE USO
			quadraFace.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			
			//VALIDA��O DOS DADOS INFORMADOS PARA CADASTRO DE FACE DA QUADRA
			fachada.validarQuadraFace(quadraFace, colecaoQuadraFace, true);
			
			//ACRESCENTANDO A FACE DA QUADRA NA COLE��O DE APRESENTA��O
			if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()){
				colecaoQuadraFace.add(quadraFace);
			}
			else{
				
				colecaoQuadraFace = new ArrayList();
				colecaoQuadraFace.add(quadraFace);
			}
			
			//ORDENANDO AS FACES DA QUADRA PELO NUMERO
			Collections.sort((List) colecaoQuadraFace, new Comparator() {
				public int compare(Object a, Object b) {
					Integer numeroQuadraFace1 = ((QuadraFace) a)
					.getNumeroQuadraFace();
					Integer numeroQuadraFace2 = ((QuadraFace) b)
					.getNumeroQuadraFace();

					return numeroQuadraFace1.compareTo(numeroQuadraFace2);
				}
			});
			
			sessao.setAttribute("colecaoQuadraFace", colecaoQuadraFace);
			httpServletRequest.setAttribute("reloadPage", "OK");
		}
	}
	
	private void atualizarQuadraFace(HttpServletRequest httpServletRequest, HttpSession sessao, Fachada fachada,
			AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm,
			Collection colecaoQuadraFace){
		
		if (adicionarQuadraFaceActionForm.getAcao() != null
	        && adicionarQuadraFaceActionForm.getAcao().equalsIgnoreCase("atualizar")){
			
			Integer numeroQuadraFaceParaAtualizar = Integer.valueOf(
			adicionarQuadraFaceActionForm.getNumeroFace());
					    		
			Iterator it = colecaoQuadraFace.iterator();
			QuadraFace quadraFace = null;
									
			while (it.hasNext()){
										
				quadraFace = (QuadraFace) it.next();
										
				if (quadraFace.getNumeroQuadraFace().equals(numeroQuadraFaceParaAtualizar)){
					
					colecaoQuadraFace.remove(quadraFace);
					break;
				}
			}
			
			//INDICADOR REDE DE �GUA
			quadraFace.setIndicadorRedeAgua(Short.valueOf(adicionarQuadraFaceActionForm.getIndicadorRedeAguaAux()));
			
			//INDICADOR REDE DE ESGOTO
			quadraFace.setIndicadorRedeEsgoto(Short.valueOf(adicionarQuadraFaceActionForm.getIndicadorRedeEsgotoAux()));
			
			//BACIA
			if (adicionarQuadraFaceActionForm.getBaciaID() != null &&
				!adicionarQuadraFaceActionForm.getBaciaID().equals("")){
				
				FiltroBacia filtroBacia = new FiltroBacia();
				
				filtroBacia.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgoto");
				
				filtroBacia.adicionarParametro(new ParametroSimples(FiltroBacia.ID, 
				Integer.valueOf(adicionarQuadraFaceActionForm.getBaciaID())));
				
				Collection colecaoBacia = fachada.pesquisar(filtroBacia, Bacia.class.getName());
				
				Bacia bacia = (Bacia) Util.retonarObjetoDeColecao(colecaoBacia);
				
				quadraFace.setBacia(bacia);
			}
			else{
				quadraFace.setBacia(null);
			}
			
			//DISTRITO_OPERACIONAL
			if (adicionarQuadraFaceActionForm.getDistritoOperacionalID() != null &&
				!adicionarQuadraFaceActionForm.getDistritoOperacionalID().equals("")){
				
				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
				
				filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, 
				Integer.valueOf(adicionarQuadraFaceActionForm.getDistritoOperacionalID())));
				
				Collection colecaoDistritoOperacional = fachada.pesquisar(filtroDistritoOperacional, 
				DistritoOperacional.class.getName());
				
				DistritoOperacional distritoOperacional = 
				(DistritoOperacional) Util.retonarObjetoDeColecao(colecaoDistritoOperacional);
				
				quadraFace.setDistritoOperacional(distritoOperacional);
			}
			else{
				quadraFace.setDistritoOperacional(null);
			}

			// GRAU DE DIFICULDADE DE EXECU��O
			if(adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID() != null
					&& !adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID().equals("-1")){
				
				FiltroGrauDificuldadeExecucao filtroGrauDificuldadeExecucao = new FiltroGrauDificuldadeExecucao();
				
				filtroGrauDificuldadeExecucao.adicionarParametro(new ParametroSimples(
						FiltroGrauDificuldadeExecucao.ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID())));
				
				Collection colecaoGrauDificuldadeExecucao = fachada.pesquisar(filtroGrauDificuldadeExecucao, 
						GrauDificuldadeExecucao.class.getName());
						
				GrauDificuldadeExecucao grauDificuldadeExecucao = 
						(GrauDificuldadeExecucao) Util.retonarObjetoDeColecao(colecaoGrauDificuldadeExecucao);
						
				quadraFace.setGrauDificuldadeExecucao(grauDificuldadeExecucao);
				
			}
			
			// GRAU DE RISCO DE SEGURAN�A F�SICA
			if(adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID() != null
					&& !adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID().equals("-1")){
				
				FiltroGrauRiscoSegurancaFisica filtroGrauRiscoSegurancaFisica = new FiltroGrauRiscoSegurancaFisica();
				
				filtroGrauRiscoSegurancaFisica.adicionarParametro(new ParametroSimples(
						FiltroGrauRiscoSegurancaFisica.ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID())));
				
				Collection colecaoGrauRiscoSegurancaFisica = fachada.pesquisar(filtroGrauRiscoSegurancaFisica, 
						GrauRiscoSegurancaFisica.class.getName());
						
				GrauRiscoSegurancaFisica grauRiscoSegurancaFisica = 
						(GrauRiscoSegurancaFisica) Util.retonarObjetoDeColecao(colecaoGrauRiscoSegurancaFisica);
						
				quadraFace.setGrauRiscoSegurancaFisica(grauRiscoSegurancaFisica);
				
			}
			
			// N�VEL DE PRESS�O
			if(adicionarQuadraFaceActionForm.getNivelPressaoID() != null
					&& !adicionarQuadraFaceActionForm.getNivelPressaoID().equals("-1")){
				
				// GRAU INTERMIT�NCIA
				if(adicionarQuadraFaceActionForm.getGrauIntermitenciaID() != null
						&& !adicionarQuadraFaceActionForm.getGrauIntermitenciaID().equals("-1")){
					
					FiltroCondicaoAbastecimentoAgua filtroCondicaoAbastecimentoAgua = new FiltroCondicaoAbastecimentoAgua();
					
					filtroCondicaoAbastecimentoAgua.adicionarParametro(new ParametroSimples(
							FiltroCondicaoAbastecimentoAgua.GRAU_INTERMITENCIA_ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauIntermitenciaID())));

					filtroCondicaoAbastecimentoAgua.adicionarParametro(new ParametroSimples(
							FiltroCondicaoAbastecimentoAgua.NIVEL_PRESSAO_ID, Integer.valueOf(adicionarQuadraFaceActionForm.getNivelPressaoID())));
					
					Collection colecaoCondicaoAbastecimentoAgua = fachada.pesquisar(filtroCondicaoAbastecimentoAgua, 
							CondicaoAbastecimentoAgua.class.getName());
							
					CondicaoAbastecimentoAgua condicaoAbastecimentoAgua = 
							(CondicaoAbastecimentoAgua) Util.retonarObjetoDeColecao(colecaoCondicaoAbastecimentoAgua);
							
					quadraFace.setCondicaoAbastecimentoAgua(condicaoAbastecimentoAgua);
					
				}
			}

			//INDICADOR DE USO
			quadraFace.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			
			//VALIDA��O DOS DADOS INFORMADOS PARA CADASTRO DE FACE DA QUADRA
			fachada.validarQuadraFace(quadraFace, colecaoQuadraFace, false);
			
			//ACRESCENTANDO A FACE DA QUADRA ATUALIZADA NA COLE��O DE APRESENTA��O
			colecaoQuadraFace.add(quadraFace);
			
			//ORDENANDO AS FACES DA QUADRA PELO NUMERO
			Collections.sort((List) colecaoQuadraFace, new Comparator() {
				public int compare(Object a, Object b) {
					Integer numeroQuadraFace1 = ((QuadraFace) a)
					.getNumeroQuadraFace();
					Integer numeroQuadraFace2 = ((QuadraFace) b)
					.getNumeroQuadraFace();

					return numeroQuadraFace1.compareTo(numeroQuadraFace2);
				}
			});
			
			sessao.setAttribute("colecaoQuadraFace", colecaoQuadraFace);
			httpServletRequest.setAttribute("reloadPage", "OK");
		}
	}

}
