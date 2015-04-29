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
package gsan.gui.relatorio.micromedicao;

import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.gui.ActionServletException;
import gsan.gui.micromedicao.GerarRelatorioAnormalidadeLeituraPeriodoActionForm;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.micromedicao.FiltrarRelatorioAnormalidadeLeituraPeriodoHelper;
import gsan.relatorio.micromedicao.RelatorioAnormalidadeLeituraPeriodo;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesInterfaceGSAN;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioAnormalidadeLeituraPeriodoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm = (GerarRelatorioAnormalidadeLeituraPeriodoActionForm) actionForm;

		validarForm(relatorioForm);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioAnormalidadeLeituraPeriodo relatorioAnormalidadeLeituraPeriodo = criarRelatorioBeanParametros(
				relatorioForm, httpServletRequest,tipoRelatorio);
		
		return processarExibicaoRelatorio(relatorioAnormalidadeLeituraPeriodo,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

	}

	/**
	 * Esse m�todo faz todas as valida��es necess�rias antes de gerar o relat�rio.
	 *
	 *@since 30/10/2009
	 *@author Marlon Patrick
	 */
	private void validarForm(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {


		if ( Util.verificarNaoVazio(relatorioForm.getIdLocalidadeInicial())) {

			validarLocalidade(relatorioForm);
		}

		if ( Util.verificarNaoVazio(relatorioForm.getCodigoSetorComercialInicial())) {

			validarSetorComercial(relatorioForm);
		}

		if ( Util.verificarNaoVazio(relatorioForm.getCodigoRotaInicial())) {

			validarRota(relatorioForm);
		}		
		
		if(relatorioForm.getIdAnormalidadeLeitura().equals("-1") &&
		   (relatorioForm.getAnormalidadeLeituraFaturada() == null || 		
		   (relatorioForm.getAnormalidadeLeituraFaturada().length == 1 && 
		   relatorioForm.getAnormalidadeLeituraFaturada()[0].equals("-1"))
		   )){
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio","Anormalidade de Leitura Informada ou Anormalidade de Leitura Faturada");
		}

	}

	/**
	 * Esse m�todo cria o Bean do relatorio j� com os seus parametros.
	 *
	 *@since 30/10/2009
	 *@author Marlon Patrick
	 */
	private RelatorioAnormalidadeLeituraPeriodo criarRelatorioBeanParametros(
			GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm,
			HttpServletRequest httpServletRequest,String tipoRelatorio) {
		
		RelatorioAnormalidadeLeituraPeriodo relatorio = new RelatorioAnormalidadeLeituraPeriodo(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorio.addParametro("relatorioForm",relatorioForm);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("filtroRelatorio", criarFiltro(relatorioForm));

		return relatorio;
	}
	
	/**
	 * Esse m�todo faz valida��es em cima dos campos
	 * de localidade inicial e final.
	 *
	 *@since 30/10/2009
	 *@author Marlon Patrick
	 */
	private void validarLocalidade(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,relatorioForm.getIdLocalidadeInicial()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
				,ConstantesSistema.INDICADOR_USO_ATIVO));

		boolean isUnidadeNegocioInformado = false;
		
		if(Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())){
			isUnidadeNegocioInformado = true;
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,relatorioForm.getIdUnidadeNegocio()));
		}

		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if ( Util.isVazioOrNulo(colecaoLocalidade)) {

			if(isUnidadeNegocioInformado){
				throw new ActionServletException(
						ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
						ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);

			}
			
			throw new ActionServletException(
					ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL);
		}

		if( !relatorioForm.getIdLocalidadeInicial().equals(relatorioForm.getIdLocalidadeFinal())){
			
			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,relatorioForm.getIdLocalidadeFinal()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
					,ConstantesSistema.INDICADOR_USO_ATIVO));

			isUnidadeNegocioInformado = false;
			
			if(Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())){
				isUnidadeNegocioInformado = true;
				
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,relatorioForm.getIdUnidadeNegocio()));
			}

			colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if ( Util.isVazioOrNulo(colecaoLocalidade)) {

				if(isUnidadeNegocioInformado){
					throw new ActionServletException(
							ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
							ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);

				}
				
				throw new ActionServletException(
						ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL);
			}
		}
	}

	/**
	 * Esse m�todo faz valida��es em cima dos campos
	 * de setor comercial inicial e final.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private void validarSetorComercial(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,relatorioForm.getCodigoSetorComercialInicial()));
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, relatorioForm.getIdLocalidadeInicial()));
		filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<SetorComercial> colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
			throw new ActionServletException(
					ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
					ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_INICIAL,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL);
		}
		
		if( !relatorioForm.getCodigoSetorComercialInicial().equals(relatorioForm.getCodigoSetorComercialFinal())){
			
			filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,relatorioForm.getCodigoSetorComercialFinal()));
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, relatorioForm.getIdLocalidadeFinal()));
			
			colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
				throw new ActionServletException(
						ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
						ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_FINAL,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL);
			}			
		}
	}

	/**
	 * Esse m�todo faz valida��es em cima dos campos
	 * de rota inicial e final.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private void validarRota(GerarRelatorioAnormalidadeLeituraPeriodoActionForm form) {

		Integer codRotaInicial = new Integer(form.getCodigoRotaInicial());
		Integer codRotaFinal = new Integer(form.getCodigoRotaFinal());

		if(codRotaFinal < codRotaInicial){
			throw new ActionServletException(
				"atencao.rota.final.maior.rota.inicial");

		}
		
		FiltroRota filtroRota = new FiltroRota();
        filtroRota.adicionarParametro(new ParametroSimples(
                FiltroRota.LOCALIDADE_ID, new Integer(form.getIdLocalidadeInicial())));
           
        filtroRota.adicionarParametro(new ParametroSimples(
        		FiltroRota.SETOR_COMERCIAL_CODIGO, new Integer(form.getCodigoSetorComercialInicial())));
                
        filtroRota.adicionarParametro(new ParametroSimples(
                FiltroRota.CODIGO_ROTA, codRotaInicial));

    	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,
    			ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Rota> colecaoRota = 
			this.getFachada().pesquisar(filtroRota, Rota.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoRota)) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Rota Inicial");
		}
		
		if( !codRotaInicial.equals(codRotaFinal)){
			filtroRota = new FiltroRota();
	        filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.LOCALIDADE_ID, new Integer(form.getIdLocalidadeInicial())));
	           
	        filtroRota.adicionarParametro(new ParametroSimples(
	        		FiltroRota.SETOR_COMERCIAL_CODIGO, new Integer(form.getCodigoSetorComercialInicial())));
	                
	        filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.CODIGO_ROTA, codRotaInicial));

	    	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,
	    			ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoRota = 
				this.getFachada().pesquisar(filtroRota, Rota.class.getName());
		
			if ( Util.isVazioOrNulo(colecaoRota)) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,"Rota Inicial");
			}
			
		}
	}
	
	/**
	 * Este m�todo cria e configura o filtro necess�rio a gera��o do relat�rio.
	 * 
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private FiltrarRelatorioAnormalidadeLeituraPeriodoHelper criarFiltro(
			GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {
		
		FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro = 
			new FiltrarRelatorioAnormalidadeLeituraPeriodoHelper();

		
		filtro.setAnoMesReferenciaInicial(new Integer(
				Util.formatarMesAnoParaAnoMesSemBarra(relatorioForm.getMesAnoReferenciaInicial())));

		filtro.setAnoMesReferenciaFinal(new Integer(
				Util.formatarMesAnoParaAnoMesSemBarra(relatorioForm.getMesAnoReferenciaFinal())));

		Integer diferencaMeses = Util.getDiferencaMeses(relatorioForm.getMesAnoReferenciaInicial(), relatorioForm.getMesAnoReferenciaFinal());
		diferencaMeses++;

		filtro.setQuantidadeMeses(diferencaMeses);
			
		filtro.setAnormalidadeLeitura(new Integer(relatorioForm.getIdAnormalidadeLeitura()));

		if (Util.isCampoComboboxInformado(relatorioForm.getIdGrupoFaturamento())) {
			
			filtro.setGrupoFaturamento(new Integer(relatorioForm.getIdGrupoFaturamento()));
		}

		if (Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())) {
			
			filtro.setUnidadeNegocio(new Integer(relatorioForm.getIdUnidadeNegocio()));
		}
			
		if (Util.verificarNaoVazio(relatorioForm.getIdLocalidadeInicial())) {
			filtro.setLocalidadeInicial(new Integer(relatorioForm.getIdLocalidadeInicial()));
			filtro.setLocalidadeFinal(new Integer(relatorioForm.getIdLocalidadeFinal()));
		}

		if (Util.verificarNaoVazio(relatorioForm.getCodigoSetorComercialInicial())) {
			filtro.setSetorComercialInicial(new Integer(relatorioForm.getCodigoSetorComercialInicial()));
			filtro.setSetorComercialFinal(new Integer(relatorioForm.getCodigoSetorComercialFinal()));
		}

		if (Util.verificarNaoVazio(relatorioForm.getCodigoRotaInicial())) {
			filtro.setRotaInicial(new Integer(relatorioForm.getCodigoRotaInicial()));
			filtro.setRotaFinal(new Integer(relatorioForm.getCodigoRotaFinal()));
		}

		if (Util.verificarNaoVazio(relatorioForm.getSequencialRotaInicial())) {
			filtro.setSequencialRotaInicial(new Integer(relatorioForm.getSequencialRotaInicial()));
			filtro.setSequencialRotaFinal(new Integer(relatorioForm.getSequencialRotaFinal()));
		}
		
		if(relatorioForm.getAnormalidadeLeituraFaturada() != null && 
				!(relatorioForm.getAnormalidadeLeituraFaturada().length == 1 && relatorioForm.getAnormalidadeLeituraFaturada()[0].equals("-1"))){
			filtro.setAnormalidadeLeituraFaturada(relatorioForm.getAnormalidadeLeituraFaturada());
		}
		
		if(Util.verificarNaoVazio(relatorioForm.getOrdenacao())){
			filtro.setOrdenacao(Integer.parseInt(relatorioForm.getOrdenacao()));
		}

		return filtro;
	}
}
