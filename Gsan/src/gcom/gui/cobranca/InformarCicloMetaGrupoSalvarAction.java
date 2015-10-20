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
package gcom.gui.cobranca;

import gcom.cobranca.CicloMeta;
import gcom.cobranca.CicloMetaGrupo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCicloMeta;
import gcom.cobranca.FiltroCicloMetaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.InformarCicloMetaGrupoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
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
 * [UC00] Informar metas do ciclo
 * 
 * @author Francisco do Nascimento
 * 
 */

public class InformarCicloMetaGrupoSalvarAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		//CicloMetaGrupoActionForm cicloMetaForm = (CicloMetaGrupoActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = request.getSession(false);
		
		CicloMetaGrupoActionForm cicloMetaForm = (CicloMetaGrupoActionForm) actionForm;
		CicloMeta cicloMeta = (CicloMeta) sessao.getAttribute("cicloMeta");
		
		
		//se as metas foram regeradas
		if (sessao.getAttribute("metasRegeradas") != null){
			fachada.removerCicloMetaGrupo(new Integer(cicloMetaForm.getIdCicloMeta()));
			sessao.removeAttribute("metasRegeradas");
		}
		
		if (cicloMeta == null){
			if ((cicloMetaForm.getValorLimite() == null 
					|| cicloMetaForm.getValorLimite().equals(""))
					&& (cicloMetaForm.getMetaTotal() == null 
							|| cicloMetaForm.getMetaTotal().equals(""))){
				throw new ActionServletException(
					"atencao.informe_valor_ou_meta", null, "");
			}
		} if (cicloMeta != null){
			if ((cicloMetaForm.getValorLimite() == null 
					|| cicloMetaForm.getValorLimite().equals(""))
					&& (cicloMetaForm.getMetaTotal() == null 
							|| cicloMetaForm.getMetaTotal().equals(""))){
				throw new ActionServletException(
					"atencao.informe_valor_ou_meta", null, "");
			}
			
			if (cicloMeta.getId() == null){
				FiltroCicloMeta filtroCiclo = new FiltroCicloMeta();
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.COBRANCA_ACAO_ID, 
					cicloMetaForm.getIdCobrancaAcao()));
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.ANO_MES_REFERENCIA, 
						Util.formatarMesAnoComBarraParaAnoMes(cicloMetaForm.getAnoMesCobranca())));
				filtroCiclo.adicionarCaminhoParaCarregamentoEntidade(FiltroCicloMeta.COBRANCA_ACAO);

				Collection colecaoCicloMetas = fachada.pesquisar(
						filtroCiclo, CicloMeta.class.getName());
				
				if (colecaoCicloMetas != null && colecaoCicloMetas.size() > 0){
					throw new ActionServletException(
							"atencao.ciclo_meta_ano_mes_ja_existe", null, "");
				}
			}
		}
		
		Collection<InformarCicloMetaGrupoHelper> helpers = null;
		Collection colecaoCicloMetaGrupo = null;
		
		if (!cicloMetaForm.getIdCicloMeta().equals("") 
				&& !cicloMetaForm.getIdCicloMeta().equals("-1") ){
			FiltroCicloMetaGrupo filtroCicloMetaGrupo = new FiltroCicloMetaGrupo();
			filtroCicloMetaGrupo.adicionarParametro(new ParametroSimples(FiltroCicloMetaGrupo.CICLO_META_ID, 
				cicloMetaForm.getIdCicloMeta()));
			colecaoCicloMetaGrupo = fachada.pesquisar(
					filtroCicloMetaGrupo, CicloMetaGrupo.class.getName());
		}
		boolean distribuidas = false;
		if (colecaoCicloMetaGrupo == null || colecaoCicloMetaGrupo.isEmpty() 
				|| cicloMetaForm.getIdCicloMeta() == null || cicloMetaForm.getIdCicloMeta().equals("")){
				if (cicloMetaForm.getMetaTotal() != null 
						&& !cicloMetaForm.getMetaTotal().equals("")
						&& !cicloMetaForm.getMetaTotal().equals("0")){
					if (cicloMeta != null){
						cicloMeta.setMetaTotal(new Integer(cicloMetaForm.getMetaTotal()));
						fachada.distribuirMetasCiclo(cicloMeta);
						distribuidas = true;
					}else{
						throw new ActionServletException(
								"atencao.informe_valor_ou_meta", null, "");
					}
				}
		}
		
		helpers = (Collection<InformarCicloMetaGrupoHelper>) sessao.getAttribute("helpers");
		//Collection<InformarCicloMetaGrupoHelper> helpers = (Collection<InformarCicloMetaGrupoHelper>) sessao.getAttribute("helpers");
	
		
		/*if (helpers == null && cicloMeta == null){
			if (cicloMetaForm.getValorLimite() == null 
					|| cicloMetaForm.getValorLimite().equals("")){
				throw new ActionServletException(
					"atencao.informe_valor_ou_meta", null, "");
			}
		}*/
		
		
		Collection<InformarCicloMetaGrupoHelper> helpersLocalidade = new ArrayList<InformarCicloMetaGrupoHelper>();
		
		if (distribuidas == false){
			// Acumula numa cole��o os helpers de todas as localidades
			if (helpers != null) {
				
				for (Iterator iter = helpers.iterator(); iter.hasNext();) {
					
					InformarCicloMetaGrupoHelper itemGerencia = (InformarCicloMetaGrupoHelper) iter.next();
					
					for (Iterator iter2 = itemGerencia.getSubItens().values().iterator(); iter2.hasNext();) {
						
						InformarCicloMetaGrupoHelper itemUneg = (InformarCicloMetaGrupoHelper) iter2.next();
						
						for (Iterator iter3 = itemUneg.getSubItens().values().iterator(); iter3
								.hasNext();) {
							
							InformarCicloMetaGrupoHelper itemLoc = (InformarCicloMetaGrupoHelper) iter3.next();
							  
							String nomeItem = itemGerencia.getTipoItem() + itemGerencia.getIdItem() + 
								itemUneg.getTipoItem() + itemUneg.getIdItem() +
								itemLoc.getTipoItem() + itemLoc.getIdItem(); 
							
							itemLoc.setMetaAtual(Integer.parseInt(
									request.getParameter(nomeItem)));
							
						}
						
						helpersLocalidade.addAll(itemUneg.getSubItens().values());
					}
					
				}
				
				FiltroCicloMeta filtroCiclo = new FiltroCicloMeta();
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.COBRANCA_ACAO_ID, 
					cicloMetaForm.getIdCobrancaAcao()));
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.ANO_MES_REFERENCIA, 
						Util.formatarMesAnoComBarraParaAnoMes(cicloMetaForm.getAnoMesCobranca())));
				filtroCiclo.adicionarCaminhoParaCarregamentoEntidade(FiltroCicloMeta.COBRANCA_ACAO);
	
				Collection colecaoCicloMetas = fachada.pesquisar(
						filtroCiclo, CicloMeta.class.getName());
				cicloMeta = (CicloMeta)Util.retonarObjetoDeColecao(colecaoCicloMetas);
				
				fachada.atualizarDistribuicaoMetasCicloGrupoLocalidade(cicloMeta, helpersLocalidade);
				
			}
		}
		
		//5.	Caso contr�rio o sistema permite que seja informado o valor
		//6.	Caso o valor n�o estivesse informado 
		//6.1.	O usu�rio pode informar o valor 
		if (cicloMeta == null && cicloMetaForm.getValorLimite() != null 
				&& !cicloMetaForm.getValorLimite().equals("")
				&& helpers == null){
			
			cicloMeta = new CicloMeta();
			cicloMeta.setAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(cicloMetaForm.getAnoMesCobranca()));
			cicloMeta.setUltimaAlteracao(new Date());
			
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, 
				cicloMetaForm.getIdCobrancaAcao()));
			filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcao.COBRANCAO_CRITERIO);
			filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcao.LIGACAO_AGUA_SITUACAO);
			Collection colecaoCobrancaAcao = fachada.pesquisar(
					filtroCobrancaAcao, CobrancaAcao.class.getName());
			
			CobrancaAcao cobrancaAcao = (CobrancaAcao) Util.retonarObjetoDeColecao(
				colecaoCobrancaAcao);
			
			cicloMeta.setCobrancaAcao(cobrancaAcao);
			cicloMetaForm.setValorLimite(cicloMetaForm.getValorLimite().replace(".","").replace(",","."));
			if (new Integer(cicloMetaForm.getValorLimite()).intValue() > 0){
				cicloMeta.setValorLimite(new BigDecimal(cicloMetaForm.getValorLimite()));
				fachada.inserir(cicloMeta);
			}
			
		}else if (cicloMeta != null && cicloMetaForm.getValorLimite() != null 
				&& !cicloMetaForm.getValorLimite().equals("")){
			cicloMetaForm.setValorLimite(cicloMetaForm.getValorLimite().replace(".","").replace(",","."));
			
			cicloMeta.setValorLimite(new BigDecimal(cicloMetaForm.getValorLimite()));
			
			if (cicloMeta.getValorLimite().compareTo(new BigDecimal(0)) > 0){
				fachada.atualizar(cicloMeta);
			}
		}
		
        request.setAttribute("caminhoFuncionalidade","exibirInformarCicloMetaGrupoAction.do?menu=sim");
		request.setAttribute("labelPaginaSucesso"," Informar Metas de outra A��o de Cobran�a ");
		request.setAttribute("mensagemPaginaSucesso","Metas/Valor Limite da A��o de Cobran�a " + cicloMeta.getCobrancaAcao().getDescricaoCobrancaAcao() 
				+ " do Ciclo " + Util.formatarAnoMesParaMesAno(cicloMeta.getAnoMesReferencia())  				 
				+ " atualizadas com sucesso. ");

		return retorno;

	}

}