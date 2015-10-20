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
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.FiltrarRelatorioContasNaoImpressasHelper;
import gcom.relatorio.faturamento.RelatorioContasNaoImpressas;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [RM5773] Impedimento de impress�o de faturas pelo SMARTPHONE
 * [UC1263] Relat�rio de Contas N�o Impressas
 * 
 * @analyst S�vio Cavalcante
 * @author Th�lio Ara�jo
 * @date 21/12/2011
 */
public class GerarRelatorioContasNaoImpressasAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirRelatorioImoveisSituacaoLigacaoAgua");
		
		// Form
		GerarRelatorioContasNaoImpressasActionForm form = 
			(GerarRelatorioContasNaoImpressasActionForm) actionForm;
		
		FiltrarRelatorioContasNaoImpressasHelper filtro = 
			new FiltrarRelatorioContasNaoImpressasHelper();
		
		// M�s ano Refer�ncia
		if (form.getAnoMesReferencia() != null) {
			filtro.setMesAnoReferencia(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferencia()));
		}
		
		// Faturamento Grupo
		if (form.getGrupoFaturamento() != null
				 && !form.getGrupoFaturamento().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtro.setGrupoFaturamento(new Integer(form.getGrupoFaturamento()));
		}
		
		// Ger�ncia Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setGerenciaRegional(new Integer(form.getGerenciaRegional()));
		}
		
		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
		}
			
		// Empresa
		if (form.getEmpresaID() != null &&
				!form.getEmpresaID().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtro.setEmpresa(new Integer(form.getEmpresaID()));
		}
		
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.ID,new Integer(form.getLocalidadeInicial())));
			filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.INDICADORUSO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
			
			if (!form.getUnidadeNegocio().equals("-1")){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID, form.getUnidadeNegocio()));
			}
			
			if (!form.getGerenciaRegional().equals("-1")){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
			}
			
			// Recupera Localidade
			Collection<Localidade> colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
			if (!form.getUnidadeNegocio().equals("-1") && !form.getGerenciaRegional().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_gerencia_unidade", form.getUnidadeNegocio(), form.getGerenciaRegional());
			}
			
			if (!form.getUnidadeNegocio().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_unidade_negocio",form.getUnidadeNegocio());
			}
			
			if (!form.getGerenciaRegional().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_gerencia",form.getGerenciaRegional());
			}
			
			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null,
	                    "Localidade Inicial");
			}else{
				filtro.setLocalidadeInicial(new Integer(form.getLocalidadeInicial()));
			}
		
			// Setor Comercial Inicial
			if (form.getSetorComercialInicial() != null && 
				!form.getSetorComercialInicial().equals("") ) {
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,new Integer(form.getSetorComercialInicial())));
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.LOCALIDADE,form.getLocalidadeInicial()));
				
				// Recupera Setor Comercial
				Collection<SetorComercial> colecaoSetorComercial = 
					this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa_inexistente", null,
		                    "Setor Inicial");
				}else{
					
					filtro.setSetorComercialInicial(new Integer(form.getSetorComercialInicial()));
				}
					
				
			}
	
			// Rota Inicial
			if (form.getRotaInicial() != null && 
				!form.getRotaInicial().equals("") ) {
				
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getRotaInicial()));
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,  ConstantesSistema.INDICADOR_USO_ATIVO));
				
				if (form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("")){
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, form.getSetorComercialInicial()));
				}
				
				Collection<Rota> colecaoRotas = (Collection<Rota>) Fachada.getInstancia().pesquisar(
						filtroRota, Rota.class.getName());
				
				if (form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("") && colecaoRotas.isEmpty()){
					throw new ActionServletException("atencao.rota_setor_comercial", form.getSetorComercialInicial());
				}else if (colecaoRotas == null || colecaoRotas.isEmpty()){
					throw new ActionServletException("atencao.rota_inexistente", "inicial");
				}
				
				filtro.setRotaInicial(new Integer(form.getRotaInicial()));
			}
	
			// Sequencial Rota Inicial
			if (form.getSequencialRotaInicial() != null && 
				!form.getSequencialRotaInicial().equals("") ) {
					
				filtro.setSequencialRotalInicial(new Integer(form.getSequencialRotaInicial()));
			}
		}else{
			boolean informouInscricao = false;
			if (form.getSetorComercialInicial() != null && 
					!form.getSetorComercialInicial().equals("") ) {
				informouInscricao = true;
			}
			
			if (form.getRotaInicial() != null && 
					!form.getRotaInicial().equals("") ) {
				informouInscricao = true;
			}
			
			if (form.getSequencialRotaInicial() != null && 
					!form.getSequencialRotaInicial().equals("") ) {
				informouInscricao = true;
			}
			
			if(informouInscricao){
				throw new ActionServletException("atencao.naoinformado", null,
	                    "Localidade Inicial");
			}
		}

		// Localidade Final
		if (form.getLocalidadeFinal() != null && 
			!form.getLocalidadeFinal().equals("") ) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.ID,new Integer(form.getLocalidadeFinal() )));
			filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.INDICADORUSO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
			
			if (!form.getUnidadeNegocio().equals("-1")){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID, form.getUnidadeNegocio()));
			}
			
			if (!form.getGerenciaRegional().equals("-1")){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
			}
			
			// Recupera Localidade
			Collection<Localidade> colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
			if (!form.getUnidadeNegocio().equals("-1") && !form.getGerenciaRegional().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_gerencia_unidade", form.getUnidadeNegocio(), form.getGerenciaRegional());
			}
			
			if (!form.getUnidadeNegocio().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_unidade_negocio",form.getUnidadeNegocio());
			}
			
			if (!form.getGerenciaRegional().equals("-1") && colecaoLocalidade.isEmpty()){
				throw new ActionServletException("atencao.filtro_localidade_gerencia",form.getGerenciaRegional());
			}
		
			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null,
	                    "Localidade Final");
			}else{
				
				filtro.setLocalidadeFinal(new Integer(form.getLocalidadeFinal()));
			}
			
			// Setor Comercial Final
			if (form.getSetorComercialFinal() != null && 
				!form.getSetorComercialFinal().equals("") ) {
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,new Integer(form.getSetorComercialInicial())));
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.LOCALIDADE,form.getLocalidadeInicial()));
				
				// Recupera Setor Comercial
				Collection<SetorComercial> colecaoSetorComercial = 
					this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa_inexistente", null,
		                    "Setor Final");
				}else{
					
					filtro.setSetorComercialFinal(new Integer(form.getSetorComercialFinal()));
				
				}
			}
			
			// Rota Final
			if (form.getRotaFinal() != null && 
				!form.getRotaFinal().equals("") ) {
					
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getRotaFinal()));
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,  ConstantesSistema.INDICADOR_USO_ATIVO));
				
				if (form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("")){
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, form.getSetorComercialInicial()));
				}
				
				Collection<Rota> colecaoRotas = (Collection<Rota>) Fachada.getInstancia().pesquisar(
						filtroRota, Rota.class.getName());
				
				if (form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("") && colecaoRotas.isEmpty()){
					throw new ActionServletException("atencao.rota_setor_comercial", form.getSetorComercialInicial());
				}else if (colecaoRotas == null || colecaoRotas.isEmpty()){
					throw new ActionServletException("atencao.rota_inexistente", "final");
				}
				
				filtro.setRotaFinal(new Integer(form.getRotaFinal()));
			}
			
			// Sequencial Rota Final
			if (form.getSequencialRotaFinal() != null && 
				!form.getSequencialRotaFinal().equals("") ) {
					
				filtro.setSequencialRotalFinal(new Integer(form.getSequencialRotaFinal()));
			}
			
		}else{
			boolean informouInscricao = false;
			if (form.getSetorComercialFinal() != null && 
					!form.getSetorComercialFinal().equals("") ) {
				informouInscricao = true;
			}
			
			if (form.getRotaFinal() != null && 
					!form.getRotaFinal().equals("") ) {
				informouInscricao = true;
			}
			
			if (form.getSequencialRotaFinal() != null && 
					!form.getSequencialRotaFinal().equals("") ) {
				informouInscricao = true;
			}
			
			if(informouInscricao){
				throw new ActionServletException("atencao.naoinformado", null,
	                    "Localidade Inicial");
			}
		}


		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioContasNaoImpressas relatorio = 
			new RelatorioContasNaoImpressas(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioContasNaoImpressasHelper", filtro);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
}