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
package gcom.gui.operacional.abastecimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.Municipio;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.ZonaAbastecimento;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0414] - Informar Programa��o de Abastecimento e Manuten��o
 * 
 * @author Rafael Pinto
 * @date 14/11/2006
 */
public class ExibirInformarProgramacaoAbastecimentoManutencaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("informarProgramacaoAbastecimentoManutencao");

		InformarProgramacaoAbastecimentoManutencaoActionForm form = 
			(InformarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		String tipoOperacao = httpServletRequest.getParameter("tipoOperacao");
		
		if(tipoOperacao != null && !tipoOperacao.equals("") && tipoOperacao.equals("E")){
			
			String indice = null;
			boolean ehAbastecimento = true;
			if(httpServletRequest.getParameter("removerAbastecimento") != null){
				indice = httpServletRequest.getParameter("removerAbastecimento");
			}else{
				indice = httpServletRequest.getParameter("removerManutencao");
				ehAbastecimento = false;
			}
			
			this.removeProgramacao(form,ehAbastecimento,new Integer(indice));
			
		}else if(tipoOperacao == null || tipoOperacao.equals("")){
	
			Collection colecaoProgramacaoAbastecimento = 
				(Collection) this.getSessao(httpServletRequest).getAttribute("colecaoProgramacaoAbastecimento");
			
			Collection colecaoProgramacaoManutencao = 
				(Collection) this.getSessao(httpServletRequest).getAttribute("colecaoProgramacaoManutencao");
			
			form.setAbastecimentoProgramacao(colecaoProgramacaoAbastecimento);
			form.setManutencaoProgramacao(colecaoProgramacaoManutencao);
	
			String mesAnoReferencia = 
				(String) this.getSessao(httpServletRequest).getAttribute("mesAnoReferencia");
			
			Municipio mun = (Municipio) this.getSessao(httpServletRequest).getAttribute("municipio");
			Bairro bair = (Bairro) this.getSessao(httpServletRequest).getAttribute("bairro");
			BairroArea bairroArea = (BairroArea) this.getSessao(httpServletRequest).getAttribute("bairroArea");
			
			String municipio = ""+mun.getId();
			String nomeMunicipio = mun.getNome();
			
			String bairro = "";
			String nomeBairro = "";
			String areaBairro = "";
			String nomeAreaBairro = "";
			if ( bair != null && !bair.equals("") ) {
				bairro = ""+bair.getCodigo();
				nomeBairro = bair.getNome();
				
				areaBairro = ""+bairroArea.getId();
				nomeAreaBairro = bairroArea.getNome();
			
			}
			
			String sistemaAbastecimento = null;
			String nomeSistemaAbastecimento = null;
			
			String setorAbastecimento = null;
			String nomeSetorAbastecimento = null;
			
			String zonaAbastecimento = null;
			String nomeZonaAbastecimento = null;
			
			String distritoOperacional = null;
			String nomeDistritoOperacional = null;
			
			if(bairroArea != null && !bairroArea.equals("")){	
				if(bairroArea.getDistritoOperacional() != null){
					
					DistritoOperacional distrito = bairroArea.getDistritoOperacional();
					
					distritoOperacional = ""+bairroArea.getDistritoOperacional().getId();
					nomeDistritoOperacional = bairroArea.getDistritoOperacional().getDescricao();
					
					if(distrito.getSetorAbastecimento() != null){
						
						SetorAbastecimento setor = distrito.getSetorAbastecimento();
						
						setorAbastecimento = ""+setor.getId();
						nomeSetorAbastecimento = setor.getDescricao();
						
						if(setor.getSistemaAbastecimento() != null){
							sistemaAbastecimento = ""+setor.getSistemaAbastecimento().getId();
							nomeSistemaAbastecimento = setor.getSistemaAbastecimento().getDescricao();
						}
						
					}
	
					if(distrito.getZonaAbastecimento() != null){
						
						ZonaAbastecimento zona = distrito.getZonaAbastecimento();
						
						zonaAbastecimento = ""+zona.getId();
						nomeZonaAbastecimento = zona.getDescricao();
	
					}
				}
			}
			
			form.setMesAnoReferencia(mesAnoReferencia);
			form.setMunicipio(municipio);
			form.setNomeMunicipio(nomeMunicipio);
			form.setBairro(bairro);
			form.setNomeBairro(nomeBairro);
			form.setAreaBairro(areaBairro);
			form.setNomeAreaBairro(nomeAreaBairro);
			form.setSistemaAbastecimento(sistemaAbastecimento);
			form.setNomeSistemaAbastecimento(nomeSistemaAbastecimento);
			form.setSetorAbastecimento(setorAbastecimento);
			form.setNomeSetorAbastecimento(nomeSetorAbastecimento);
			form.setZonaAbastecimento(zonaAbastecimento);
			form.setNomeZonaAbastecimento(nomeZonaAbastecimento);
			form.setDistritoOperacional(distritoOperacional);
			form.setNomeDistritoOperacional(nomeDistritoOperacional);
		}				
		
		this.ordenaColecao(httpServletRequest);
		
		return retorno;
	}
	
	/**
	 * Ordena a colecao de abastecimento
	 * 
	 * @author Rafael Pinto
	 * @date 19/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 * @param ehAbastecimento
	 * @param indice
	 */
	private void ordenaColecao(HttpServletRequest httpServletRequest) {

		Collection colecaoProgramacaoAbastecimento = 
			(Collection) this.getSessao(httpServletRequest).getAttribute("colecaoProgramacaoAbastecimento");
		
		Collection colecaoProgramacaoManutencao = 
			(Collection) this.getSessao(httpServletRequest).getAttribute("colecaoProgramacaoManutencao");

		Collections.sort((List) colecaoProgramacaoAbastecimento, 
			new Comparator() {
			
				public int compare(Object a, Object b) {
				
					AbastecimentoProgramacao abastecimento1 = (AbastecimentoProgramacao) a;
					AbastecimentoProgramacao abastecimento2 = (AbastecimentoProgramacao) b;
					
					Date dataInicio1 = abastecimento1.getDataInicio();
					Date dataInicio2 = abastecimento2.getDataInicio();

					return dataInicio1.compareTo(dataInicio2);
				}
			}
		);
		
		Collections.sort((List) colecaoProgramacaoManutencao, 
			new Comparator() {
				
				public int compare(Object a, Object b) {
					
					ManutencaoProgramacao manutencao1 = (ManutencaoProgramacao) a;
					ManutencaoProgramacao manutencao2 = (ManutencaoProgramacao) b;
						
					Date dataInicio1 = manutencao1.getDataInicio();
					Date dataInicio2 = manutencao2.getDataInicio();

					return dataInicio1.compareTo(dataInicio2);
				}
			}
		);
		
	}
	
	/**
	 * Remove Programa��o
	 * 
	 * [SB0002] - Remover Programa��o de Abastecimento
	 * [SB0005] - Remover Programa��o de Abastecimento
	 *
	 * @author Rafael Pinto
	 * @date 14/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 * @param ehAbastecimento
	 * @param indice
	 */
	private void removeProgramacao(InformarProgramacaoAbastecimentoManutencaoActionForm form,
		boolean ehAbastecimento,Integer indice) {
		
		if(ehAbastecimento){
			
			Collection novaColecao = new ArrayList();
			
			int index = 0;
			
			for (Iterator iter = form.getAbastecimentoProgramacao().iterator(); iter.hasNext();) {
				
				index++;
				
				AbastecimentoProgramacao element = (AbastecimentoProgramacao) iter.next();
				
				if (index != indice.intValue()) {
					novaColecao.add(element);
				}else{
					//Verifca se tem id porque que dizer que esse objeto persiste no banco
					if(element.getId() != null){
						form.getAbastecimentoProgramacaoRemovidas().add(element);	
					}
				}
			}
			
			form.setAbastecimentoProgramacao(novaColecao);
		}else{

			Collection novaColecao = new ArrayList();
			
			int index = 0;
			
			for (Iterator iter = form.getManutencaoProgramacao().iterator(); iter.hasNext();) {
				
				index++;
				
				ManutencaoProgramacao element = (ManutencaoProgramacao) iter.next();
				
				if (index != indice.intValue()) {
					novaColecao.add(element);
				}else{
					//Verifca se tem id porque que dizer que esse objeto persiste no banco
					if(element.getId() != null){
						form.getManutencaoProgramacaoRemovidas().add(element);	
					}
				}
			}
			
			form.setManutencaoProgramacao(novaColecao);
			
		}
	}
}