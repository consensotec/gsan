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
package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.gerencial.cadastro.GerarRelatorioClientesEspeciaisActionForm;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.RelatorioClientesEspeciais;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

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
public class GerarRelatorioClientesEspeciaisAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm = (GerarRelatorioClientesEspeciaisActionForm) actionForm;

		validarForm(gerarRelatorioClientesEspeciaisActionForm);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioClientesEspeciais relatorioClientesEspeciais = criarRelatorioBeanParametros(
				gerarRelatorioClientesEspeciaisActionForm, httpServletRequest,tipoRelatorio);
		
		retorno =  processarExibicaoRelatorio(relatorioClientesEspeciais,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		return retorno;

	}

	/**
	 * Esse m�todo faz todas as valida��es necess�rias antes de gerar o relat�rio.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void validarForm(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm) {

		validarPeloMenosUmFiltroInformado(gerarRelatorioClientesEspeciaisActionForm);

		if ( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeInicial())) {

			validarLocalidade(gerarRelatorioClientesEspeciaisActionForm);
		}

		if ( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialInicial())) {

			validarSetorComercial(gerarRelatorioClientesEspeciaisActionForm);
		}

		if ( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getCodigoRotaInicial())) {

			validarRota(gerarRelatorioClientesEspeciaisActionForm);
		}

		if ( Util.verificarIdNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIdClienteResponsavel())) {

			validarClienteResponsavel(gerarRelatorioClientesEspeciaisActionForm);
		}
		
		if ( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIdLeituraAnormalidade())) {

			   validarleituraAnormalidade(gerarRelatorioClientesEspeciaisActionForm);
		}

		if ( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getDataInstalacaoHidrometroInicial())) {

			validarDataInstalacaoHidrometro(gerarRelatorioClientesEspeciaisActionForm);
		}
	}

	/**
	 * Esse m�todo cria o Bean do relatorio j� com todos os seus parametros.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private RelatorioClientesEspeciais criarRelatorioBeanParametros(
			GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm,
			HttpServletRequest httpServletRequest,String tipoRelatorio) {
		
		RelatorioClientesEspeciais relatorioClientesEspeciais = new RelatorioClientesEspeciais(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorioClientesEspeciais.addParametro("idGerenciaRegional",gerarRelatorioClientesEspeciaisActionForm.getIdGerenciaRegional());
		relatorioClientesEspeciais.addParametro("idUnidadeNegocio",gerarRelatorioClientesEspeciaisActionForm.getIdUnidadeNegocio());
		relatorioClientesEspeciais.addParametro("idLocalidadeInicial",gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeInicial());
		relatorioClientesEspeciais.addParametro("idLocalidadeFinal",gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeFinal());
		relatorioClientesEspeciais.addParametro("codigoSetorInicial",gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialInicial());
		relatorioClientesEspeciais.addParametro("codigoSetorFinal",gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialFinal());
		relatorioClientesEspeciais.addParametro("codigoRotaInicial",gerarRelatorioClientesEspeciaisActionForm.getCodigoRotaInicial());
		relatorioClientesEspeciais.addParametro("codigoRotaFinal",gerarRelatorioClientesEspeciaisActionForm.getCodigoRotaFinal());
		relatorioClientesEspeciais.addParametro("idsPerfilImovel",gerarRelatorioClientesEspeciaisActionForm.getIdsImovelPerfil());
		relatorioClientesEspeciais.addParametro("idsCategoria", gerarRelatorioClientesEspeciaisActionForm.getIdsCategoria());
		relatorioClientesEspeciais.addParametro("idsSubcategoria",gerarRelatorioClientesEspeciaisActionForm.getIdsSubCategoria());
		relatorioClientesEspeciais.addParametro("idSituacaoAgua",gerarRelatorioClientesEspeciaisActionForm.getIdSituacaoLigacaoAgua());
		relatorioClientesEspeciais.addParametro("idSituacaoEsgoto",gerarRelatorioClientesEspeciaisActionForm.getIdSituacaoLigacaoEsgoto());
		relatorioClientesEspeciais.addParametro("qtdeEconomiasInicial",gerarRelatorioClientesEspeciaisActionForm.getIntervaloQtdEcoInicial());
		relatorioClientesEspeciais.addParametro("qtdeEconomiasFinal",gerarRelatorioClientesEspeciaisActionForm.getIntervaloQtdEcoFinal());
		relatorioClientesEspeciais.addParametro("intervaloConsumoAguaInicial",gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoAguaInicial());
		relatorioClientesEspeciais.addParametro("intervaloConsumoAguaFinal",gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoAguaFinal());
		relatorioClientesEspeciais.addParametro("intervaloConsumoEsgotoInicial",gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoEsgotoInicial());
		relatorioClientesEspeciais.addParametro("intervaloConsumoEsgotoFinal",gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoEsgotoFinal());
		relatorioClientesEspeciais.addParametro("idClienteResponsavel",gerarRelatorioClientesEspeciaisActionForm.getIdClienteResponsavel());
		relatorioClientesEspeciais.addParametro("intervaloConsumoResponsavelInicial",gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoResponsavelInicial());
		relatorioClientesEspeciais.addParametro("intervaloConsumoResponsavelFinal",gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoResponsavelFinal());
		
		if( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getDataInstalacaoHidrometroInicial())){
			relatorioClientesEspeciais.addParametro("dataInstalacaoHidrometroInicial",
					Util.converteStringParaDate(gerarRelatorioClientesEspeciaisActionForm
							.getDataInstalacaoHidrometroInicial()));			
		}
		
		if( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getDataInstalacaoHidrometroFinal())){
			relatorioClientesEspeciais.addParametro("dataInstalacaoHidrometroFinal", 
					Util.converteStringParaDate(gerarRelatorioClientesEspeciaisActionForm
							.getDataInstalacaoHidrometroFinal()));
		}
		
		relatorioClientesEspeciais.addParametro("idsCapacidadesHidrometro",gerarRelatorioClientesEspeciaisActionForm.getIdsCapacidadeHidrometro());
		relatorioClientesEspeciais.addParametro("idsTarifasConsumo",gerarRelatorioClientesEspeciaisActionForm.getIdsTarifaConsumo());
		relatorioClientesEspeciais.addParametro("idLeituraAnormalidade",gerarRelatorioClientesEspeciaisActionForm.getIdLeituraAnormalidade());
		relatorioClientesEspeciais.addParametro("idConsumoAnormalidade",gerarRelatorioClientesEspeciaisActionForm.getIdConsumoAnormalidade());
		relatorioClientesEspeciais.addParametro("leituraAnormalidade",gerarRelatorioClientesEspeciaisActionForm.getLeituraAnormalidade());
		relatorioClientesEspeciais.addParametro("consumoAnormalidade",gerarRelatorioClientesEspeciaisActionForm.getConsumoAnormalidade());
		
		relatorioClientesEspeciais.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		return relatorioClientesEspeciais;
	}

	/**
	 * Verifica se pelo menos um filtro foi informado.Caso contr�rio
	 * lan�a uma exce��o.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void validarPeloMenosUmFiltroInformado(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm) {

		if ( !Util.isCampoComboboxInformado(gerarRelatorioClientesEspeciaisActionForm.getIdGerenciaRegional()) 
				&& !Util.isCampoComboboxInformado(gerarRelatorioClientesEspeciaisActionForm.getIdUnidadeNegocio())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeInicial()) 
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeFinal())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialInicial()) 
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialFinal())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getCodigoRotaInicial()) 
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getCodigoRotaFinal())
				&& !Util.isCampoComboboxMultiploInformado(gerarRelatorioClientesEspeciaisActionForm.getIdsImovelPerfil()) 
				&& !Util.isCampoComboboxMultiploInformado(gerarRelatorioClientesEspeciaisActionForm.getIdsCategoria())
				&& !Util.isCampoComboboxMultiploInformado(gerarRelatorioClientesEspeciaisActionForm.getIdsSubCategoria())
				&& !Util.isCampoComboboxInformado(gerarRelatorioClientesEspeciaisActionForm.getIdSituacaoLigacaoAgua())				
				&& !Util.isCampoComboboxInformado(gerarRelatorioClientesEspeciaisActionForm.getIdSituacaoLigacaoEsgoto())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIntervaloQtdEcoInicial())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIntervaloQtdEcoFinal())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoAguaInicial())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoAguaFinal())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoEsgotoInicial())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoEsgotoFinal())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIdClienteResponsavel())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoResponsavelInicial())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIntervaloConsumoResponsavelFinal())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getDataInstalacaoHidrometroInicial())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getDataInstalacaoHidrometroFinal())
				&& !Util.isCampoComboboxMultiploInformado(gerarRelatorioClientesEspeciaisActionForm.getIdsCapacidadeHidrometro())
				&& !Util.isCampoComboboxMultiploInformado(gerarRelatorioClientesEspeciaisActionForm.getIdsTarifaConsumo())
				&& !Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getIdLeituraAnormalidade())				
				&& !Util.isCampoComboboxInformado(gerarRelatorioClientesEspeciaisActionForm.getIdConsumoAnormalidade())){
				

			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");

		}
	}

	/**
	 * Esse m�todo faz valida��es em cima dos campos
	 * de data de instala��o do hidrometro.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void validarDataInstalacaoHidrometro(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm) {

		Date dataInstalacaoHidrometroInicial = Util
				.converteStringParaDate(gerarRelatorioClientesEspeciaisActionForm
						.getDataInstalacaoHidrometroInicial());
		
		Date dataInstalacaoHidrometroFinal = Util
				.converteStringParaDate(gerarRelatorioClientesEspeciaisActionForm
						.getDataInstalacaoHidrometroFinal());

		if (dataInstalacaoHidrometroInicial.compareTo(dataInstalacaoHidrometroFinal) > 0) {
			throw new ActionServletException(
					"atencao.data_instalacao_hidrometro_inicial.maior.data_instalacao_hidrometro_final");
		}
	}

	/**
	 * Esse m�todo faz valida��es em cima do campo
	 * leitura Anomralidade.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void validarleituraAnormalidade(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm) {

		Fachada fachada = Fachada.getInstancia();
		
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
		   filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
		     FiltroLeituraAnormalidade.ID, gerarRelatorioClientesEspeciaisActionForm.getIdLeituraAnormalidade()));

		   Collection<LeituraAnormalidade> colecaoLeiturasAnormalidades = fachada.pesquisar(
		     filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

		   if ( Util.isVazioOrNulo(colecaoLeiturasAnormalidades)) {
			   throw new ActionServletException("atencao.pesquisa_inexistente", null,"Anormalidade de Leitura");
		   }
	}

	/**
	 * Esse m�todo faz valida��es em cima do campo
	 * de cliente respons�vel.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void validarClienteResponsavel(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm) {

		Fachada fachada = Fachada.getInstancia();
		
		Cliente clienteResponsavel = 
			fachada.pesquisarClienteDigitado(
					new Integer(gerarRelatorioClientesEspeciaisActionForm.getIdClienteResponsavel()));

		if (clienteResponsavel == null) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Cliente Respons�vel");
		}
	}

	/**
	 * Esse m�todo faz valida��es em cima dos campos
	 * de localidade inicial e final.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void validarLocalidade(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm) {

		Fachada fachada = Fachada.getInstancia();

		Integer idLocalidadeInicial = new Integer(gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeInicial());
		Integer idLocalidadeFinal = new Integer(gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeFinal());

		if (idLocalidadeInicial > idLocalidadeFinal) {
			throw new ActionServletException(
					"atencao.localidade.final.maior.localidade.inicial");
		}

		Localidade localidadeOrigem = 
			fachada.pesquisarLocalidadeDigitada(new Integer(idLocalidadeInicial));

		if (localidadeOrigem == null) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Localidade Inicial");
		}

		if( !idLocalidadeInicial.equals(idLocalidadeFinal)){
			Localidade localidadeDestino = 
				fachada.pesquisarLocalidadeDigitada(new Integer(idLocalidadeFinal));

			if (localidadeDestino == null) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,"Localidade Final");
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
	private void validarSetorComercial(GerarRelatorioClientesEspeciaisActionForm form) {

		Integer codSetorInicial = new Integer(form.getCodigoSetorComercialInicial());
		Integer codSetorFinal = new Integer(form.getCodigoSetorComercialFinal());

		if(codSetorFinal < codSetorInicial){
			throw new ActionServletException(
				"atencao.setor.comercial.final.maior.setor.comercial.inicial");

		}
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codSetorInicial));
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, new Integer(form.getIdLocalidadeInicial())));
		filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<SetorComercial> colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Setor Comercial Inicial");
		}
		
		if( !codSetorInicial.equals(codSetorFinal)){
			
			filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codSetorFinal));
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, new Integer(form.getIdLocalidadeFinal())));
			
			colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,"Setor Comercial Final");
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
	private void validarRota(GerarRelatorioClientesEspeciaisActionForm form) {

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

}
