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
package gsan.gui.cadastro.imovel;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.MotivoCorte;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.ordemservico.SupressaoMotivo;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.bean.ImovelMicromedicao;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.gui.GcomAction;
import gsan.micromedicao.medicao.MedicaoHistorico;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

/**
 * [UC0473] Consultar Imovel 3� Aba - Dados de Liga��es, consumo e medi��o
 * 
 * 
 * @author Rafael Santos
 * @since 07/09/2006
 * 
 */
public class ExibirConsultarImovelDadosAnaliseMedicaoConsumoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		// pesquisar os par�metros do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getSituacaoAguaExclusaoImovel() != null 
				&& sistemaParametro.getSituacaoEsgotoExclusaoImovel() != null){
			sessao.setAttribute("situacaoExclusaoAguaEsgoto", true);
		}else{
			sessao.removeAttribute("situacaoExclusaoAguaEsgoto");
		}		
		
		if (isLimparDadosTela(httpServletRequest)) {

			httpServletRequest.removeAttribute("idImovelAnaliseMedicaoConsumoNaoEncontrado");

			limparFormSessao(consultarImovelActionForm, sessao);

		} else if (isImovelInformadoTelaAnaliseLigacaoConsumo(consultarImovelActionForm) 
						|| isImovelInformadoOutraTela(sessao)) {

			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(
					definirIdImovelASerPesquisado(consultarImovelActionForm, sessao, httpServletRequest));
			
	        Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm,sessao);

	        //deve ser chamado antes dos novos valores da sess�o serem setados
	        boolean isNovoImovelPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);

	        if (imovel != null) {
	        	
	        	
				sessao.setAttribute("imovelDadosAnaliseMedicaoConsumo", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
				
				consultarImovelActionForm
						.setIdImovelAnaliseMedicaoConsumo(imovel.getId().toString());
				
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

				if (isNovoImovelPesquisado) {

					httpServletRequest.removeAttribute("idImovelAnaliseMedicaoConsumoNaoEncontrado");

					setarDadosImovelNoFormESessao(consultarImovelActionForm,imovel, sessao, fachada, sistemaParametro);

				}
			} else {
					
					limparFormSessao(consultarImovelActionForm, sessao);
					
					httpServletRequest.setAttribute("idImovelAnaliseMedicaoConsumoNaoEncontrado","true");

					consultarImovelActionForm
							.setMatriculaImovelAnaliseMedicaoConsumo("IM�VEL INEXISTENTE");

				}

			} else {
				String idImovelAux = consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo();
				
				httpServletRequest.removeAttribute("idImovelAnaliseMedicaoConsumoNaoEncontrado");

				limparFormSessao(consultarImovelActionForm, sessao);
				
				consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(idImovelAux);
			}
		
			return actionMapping.findForward("consultarImovelAnaliseMedicaoConsumo");
		}

	/**
	 * Esse m�todo seta os dados necess�rios do Imovel
	 * no form e alguns na sess�o (cole��es).
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosImovelNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, Imovel imovel,
			HttpSession sessao, Fachada fachada, SistemaParametro sistemaParametro) {
		
		consultarImovelActionForm
				.setMatriculaImovelAnaliseMedicaoConsumo(
						Fachada.getInstancia().pesquisarInscricaoImovelExcluidoOuNao(new Integer(
								consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo().trim())));

		consultarImovelActionForm
			.setSituacaoAguaAnaliseMedicaoConsumo(imovel
				.getLigacaoAguaSituacao().getDescricao());

		consultarImovelActionForm
			.setSituacaoEsgotoAnaliseMedicaoConsumo(imovel
				.getLigacaoEsgotoSituacao().getDescricao());
		
		
		FiltroImovel filtroImovel = new FiltroImovel();
		Imovel imovelLigacao = new Imovel();
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacaoAnterior");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacaoAnterior");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));		
		Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());	
		
		if(!Util.isVazioOrNulo(colecaoImovel)){
			imovelLigacao = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
		}
		
		if(imovelLigacao.getLigacaoAguaSituacaoAnterior() != null 
				&& imovelLigacao.getLigacaoEsgotoSituacaoAnterior() != null){
			LigacaoAguaSituacao ligacaoAguaSituacaoAnterior = new LigacaoAguaSituacao();
			ligacaoAguaSituacaoAnterior = imovelLigacao.getLigacaoAguaSituacaoAnterior();
			consultarImovelActionForm.setSituacaoAguaAnterior(
				ligacaoAguaSituacaoAnterior.getDescricao());
			
			LigacaoEsgotoSituacao ligacaoEsgotoSituacaoAnterior = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacaoAnterior = imovelLigacao.getLigacaoEsgotoSituacaoAnterior();
			consultarImovelActionForm.setSituacaoEsgotoAnterior(
				ligacaoEsgotoSituacaoAnterior.getDescricao());
			
			if(imovelLigacao.getObservacaoCancelamento() != null){
				consultarImovelActionForm.setObservacaoExclusaoImovel(
					imovelLigacao.getObservacaoCancelamento());
			}			
		}else{
			sessao.removeAttribute("situacaoExclusaoAguaEsgoto");
		}		

		if(imovel.getDataSupressaoParcial() != null){
			consultarImovelActionForm.setDataSupressaoParcialAgua(Util.formatarData(imovel.getDataSupressaoParcial()));
		}
		
		consultarImovelActionForm.setEnderecoAnaliseMedicaoConsumo(
				Fachada.getInstancia().pesquisarEndereco(
						new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo().trim())));
		
		sessao.setAttribute("enderecoAnaliseMedicaoConsumo",consultarImovelActionForm.getEnderecoAnaliseMedicaoConsumo());
		
		Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( imovel.getId() );
		
		if(clienteUsuario != null){
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado(String.valueOf(clienteUsuario.getIndicadorValidaCpfCnpj()));
		}else{
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
		}

		Collection<Object[]> colecaoParmsClienteImovelLigacaoAgua = 
			Fachada.getInstancia().pesquiarImovelExcecoesApresentaDados(new Integer(
						consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()), true);

		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();

		if ( !Util.isVazioOrNulo(colecaoParmsClienteImovelLigacaoAgua) ) {			
			
			setarDadosClienteImovelLigacaoAgua(consultarImovelActionForm,
					colecaoParmsClienteImovelLigacaoAgua,faturamentoGrupo, imovel, fachada);
		}

		Collection<Object[]> colecaoParmsClienteImovelNaoLigacaoAgua =
			Fachada.getInstancia().pesquiarImovelExcecoesApresentaDados(new Integer(
						consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()), false);

		if ( !Util.isVazioOrNulo(colecaoParmsClienteImovelNaoLigacaoAgua) ) {			
			setarDadosClienteImovelNaoLigacaoAgua(consultarImovelActionForm,colecaoParmsClienteImovelNaoLigacaoAgua);
		}

		Collection<Object[]> colecaoParmsMedicaoHistorico = 
			Fachada.getInstancia().pesquisarMedicaoConsumoHistoricoExcecoesApresentaDadosConsultarImovel(faturamentoGrupo.getAnoMesReferencia(), 
						new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()),true);

		if ( Util.isVazioOrNulo(colecaoParmsMedicaoHistorico)) {
			
			colecaoParmsMedicaoHistorico = 
				Fachada.getInstancia().pesquisarMedicaoConsumoHistoricoExcecoesApresentaDadosConsultarImovel(
						Fachada.getInstancia().pesquisarParametrosDoSistema().getAnoMesFaturamento(),
							new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()),true);
			
		}
		
		int consumoMedioHidrometro = 0;

		if ( !Util.isVazioOrNulo(colecaoParmsMedicaoHistorico) ) {
			
			consumoMedioHidrometro = setarDadosMedicaoHistorico(
					consultarImovelActionForm, colecaoParmsMedicaoHistorico,
					consumoMedioHidrometro);
		}

		Collection<MedicaoHistorico> medicoesHistorico = 
			Fachada.getInstancia().carregarDadosMedicao(new Integer(
					consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()), true);

		
		if ( !Util.isVazioOrNulo(medicoesHistorico)) {			
			Collections.sort((List<MedicaoHistorico>) medicoesHistorico,new ComparatorMedicaoHistorico());
		}
		sessao.setAttribute("medicoesHistoricos",medicoesHistorico);
			
		Collection<ImovelMicromedicao> imoveisMicromedicao = 
			Fachada.getInstancia().carregarDadosConsumo(
				new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()),true);

		if ( !Util.isVazioOrNulo(imoveisMicromedicao)) {				
			Collections.sort((List<ImovelMicromedicao>) imoveisMicromedicao, new ComparatorImovelMicromedicao());
		}
		sessao.setAttribute("imoveisMicromedicao",imoveisMicromedicao);
		
			Collection<Object[]> colecaoParmsMedicaoHistoricoLigacaoEsgoto =
				Fachada.getInstancia().pesquiarMedicaoConsumoHistoricoExcecoesApresentaDados(faturamentoGrupo,
							new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()),false);

			if ( !Util.isVazioOrNulo(colecaoParmsMedicaoHistoricoLigacaoEsgoto)) {

				setarDadosMedicaoHistoricoLigacaoEsgoto(consultarImovelActionForm,colecaoParmsMedicaoHistoricoLigacaoEsgoto,
						consumoMedioHidrometro, imovelLigacao, fachada);
			}

			Collection<ImovelMicromedicao> imoveisMicromedicaoEsgoto = new ArrayList<ImovelMicromedicao>();

			Collection<MedicaoHistorico> medicoesHistoricoPoco =
				Fachada.getInstancia().carregarDadosMedicao(new Integer(
							consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()), false);
			sessao.removeAttribute("medicoesHistoricosPoco");
			if ( !Util.isVazioOrNulo(medicoesHistoricoPoco)) {

				Iterator<MedicaoHistorico> iteratorMedicaoHistoricoPoco = medicoesHistoricoPoco.iterator();

				while (iteratorMedicaoHistoricoPoco.hasNext()) {
					
					MedicaoHistorico medicaoHistoricoConsumo = iteratorMedicaoHistoricoPoco.next();

					if (medicaoHistoricoConsumo.getAnoMesReferencia() != 0) {

						Collection<ImovelMicromedicao> imoveisMicromedicaoCarregamentoLigacaoEsgoto =
							Fachada.getInstancia().carregarDadosConsumo(
								new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()),
									medicaoHistoricoConsumo.getAnoMesReferencia(),false);

						if ( !Util.isVazioOrNulo(imoveisMicromedicaoCarregamentoLigacaoEsgoto)) {
							imoveisMicromedicaoEsgoto
									.addAll(imoveisMicromedicaoCarregamentoLigacaoEsgoto);
						}
					}
				}

				Collections.sort((List<MedicaoHistorico>) medicoesHistoricoPoco,new ComparatorMedicaoHistorico());
				
				sessao.setAttribute("medicoesHistoricosPoco",medicoesHistoricoPoco);
				
				if ( !Util.isVazioOrNulo(imoveisMicromedicaoEsgoto)) {					
					Collections.sort((List<ImovelMicromedicao>) imoveisMicromedicaoEsgoto,new ComparatorImovelMicromedicao());
				}

				sessao.setAttribute("imoveisMicromedicaoEsgoto",imoveisMicromedicaoEsgoto);
				consultarImovelActionForm.setImoveisMicromedicaoEsgoto(imoveisMicromedicaoEsgoto);
				
			}else {
				imoveisMicromedicaoEsgoto =
					Fachada.getInstancia().carregarDadosConsumo(
								new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()),false);
				
				sessao.setAttribute("imoveisMicromedicaoEsgoto",imoveisMicromedicaoEsgoto);
				consultarImovelActionForm.setImoveisMicromedicaoEsgoto(imoveisMicromedicaoEsgoto);
				
				if ( !Util.isVazioOrNulo(imoveisMicromedicaoEsgoto)) {
					Collections.sort((List<ImovelMicromedicao>) imoveisMicromedicaoEsgoto, new ComparatorImovelMicromedicao());
				}
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
			
			if ( imovel.getLigacaoAguaSituacao().getId() != null && 
				 imovel.getLigacaoAguaSituacao().getId().equals(
						 LigacaoAguaSituacao.CORTADO ) ){	
				
				StringBuilder hint1 = new StringBuilder();
				hint1.append("Tipo do corte: ");
				
				if(imovel.getLigacaoAgua().getCorteTipo().getDescricao() == null){
					hint1.append("-");
				}else{
					hint1.append(imovel.getLigacaoAgua().getCorteTipo().getDescricao());
				}
				
				hint1.append(" <br> Motivo do corte: ");
				
				if(imovel.getLigacaoAgua().getMotivoCorte().getDescricao() == null ){
					hint1.append("-");
				}else{
					hint1.append(imovel.getLigacaoAgua().getMotivoCorte().getDescricao());
				}

				hint1.append(" <br> Data do corte: ");

				if(imovel.getLigacaoAgua().getDataCorte() == null){
					hint1.append("-");
				}else{
					hint1.append(sdf.format( imovel.getLigacaoAgua().getDataCorte() ));
				}

				hint1.append(" <br> N�mero do selo do corte: ");

				if(imovel.getLigacaoAgua().getNumeroSeloCorte() == null){
					hint1.append("-");
				}else{
					hint1.append(imovel.getLigacaoAgua().getNumeroSeloCorte());
				}

				hint1.append("<br>");

				consultarImovelActionForm.setHint1(hint1.toString()); 
				
			} else if ( imovel.getLigacaoAguaSituacao().getId() != null && 
					    ( imovel.getLigacaoAguaSituacao().getId().equals(
							 LigacaoAguaSituacao.SUPRIMIDO ) || 
						  imovel.getLigacaoAguaSituacao().getId().equals(
							 LigacaoAguaSituacao.SUPR_PARC_PEDIDO ) || 
						  imovel.getLigacaoAguaSituacao().getId().equals(
							 LigacaoAguaSituacao.SUPR_PARC ) ) ){
				
				consultarImovelActionForm.setHint1( 
						"Tipo da supress�o: " +
						( imovel.getLigacaoAgua().getSupressaoTipo().getDescricao() == null ? 
						  "-" : 
					      imovel.getLigacaoAgua().getSupressaoTipo().getDescricao() ) + " <br>" +
						"Motivo da supress�o: " +
						( imovel.getLigacaoAgua().getSupressaoMotivo().getDescricao() == null ? 
						  "-" : 
						  imovel.getLigacaoAgua().getSupressaoMotivo().getDescricao() ) + " <br>" +
						"Data da supress�o: " +
						( imovel.getLigacaoAgua().getDataSupressao() == null ? 
						  "-" : 
					      sdf.format( imovel.getLigacaoAgua().getDataSupressao() ) ) + " <br>" +
						"N�mero do selo da supress�o: " +
						( imovel.getLigacaoAgua().getNumeroSeloSupressao() == null ? 
						  "-" : 
						  imovel.getLigacaoAgua().getNumeroSeloSupressao() ) + " <br>" );							
			} else {
				consultarImovelActionForm.setHint1( null );
			}	
			
			
			//Indicador Valida CPF/CNPJ
			if(sistemaParametro.getIndicadorValidaCpfCnpj() != null){
				consultarImovelActionForm.setIndicadorValidaCPFCNPJ(String.valueOf(sistemaParametro.getIndicadorValidaCpfCnpj()));
			}

			sessao.setAttribute("consultarImovelActionForm",consultarImovelActionForm);
	}

	/**
	 *Seta no Form os dados da cole��o passada como parametro 
	 *obtida atrav�s do 
	 *pesquiarMedicaoConsumoHistoricoExcecoesApresentaDados
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosMedicaoHistoricoLigacaoEsgoto(
			ConsultarImovelActionForm consultarImovelActionForm,
			Collection<Object[]> colecaoParmsMedicaoHistoricoLigacaoEsgoto,
			int consumoMedioHidrometro, Imovel imovel, Fachada fachada) {
		
		Object[] arrayParmsMedicaoHistoricoLigacaoEsgoto = colecaoParmsMedicaoHistoricoLigacaoEsgoto.iterator().next();

		// DADOS DA MEDICAO DO MES DO POCO

		// descri��o tipo medicao
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[1] != null) {
			if (consultarImovelActionForm.getIndicadorFinalidadeHidrometroPoco() != null && !consultarImovelActionForm.getIndicadorFinalidadeHidrometroPoco().trim().equals("")) {
				if (consultarImovelActionForm.getIndicadorFinalidadeHidrometroPoco().equals("1")) {
					consultarImovelActionForm.setTipoMedicaoPoco("POCO");
				} else {
					consultarImovelActionForm.setTipoMedicaoPoco("LIGACAO DE ESGOTO");
				}
			} else {
				consultarImovelActionForm.setTipoMedicaoPoco((String) arrayParmsMedicaoHistoricoLigacaoEsgoto[0]);
			}
		}else{
			if (consultarImovelActionForm.getNumeroHidrometroPoco() != null && !consultarImovelActionForm.getNumeroHidrometroPoco().trim().equals("") &&
				consultarImovelActionForm.getIndicadorFinalidadeHidrometroPoco() != null && !consultarImovelActionForm.getIndicadorFinalidadeHidrometroPoco().trim().equals("")) {
				
				if (consultarImovelActionForm.getIndicadorFinalidadeHidrometroPoco().equals("1")) {
					consultarImovelActionForm.setTipoMedicaoPoco("POCO");
				} else {
					consultarImovelActionForm.setTipoMedicaoPoco("LIGACAO DE ESGOTO");
				}
				
			} else {
				consultarImovelActionForm.setTipoMedicaoPoco("");
			}
		}
		
		// data leitura anterior
		Date dataLeituraAnterior = null;
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[2] != null) {
			dataLeituraAnterior = (Date) arrayParmsMedicaoHistoricoLigacaoEsgoto[2];
		}
		// data leitura atual faturada
		Date dtLeituraAtualFaturada = null;
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[3] != null) {
			dtLeituraAtualFaturada = (Date) arrayParmsMedicaoHistoricoLigacaoEsgoto[3];
		}

		// --- fim variavel
		int diasConsumo = 0;
		if (dataLeituraAnterior != null
				&& dtLeituraAtualFaturada != null) {
			diasConsumo = gsan.util.Util
					.obterQuantidadeDiasEntreDuasDatas(
							dataLeituraAnterior,
							dtLeituraAtualFaturada);
		}

		consultarImovelActionForm.setDiasConsumo(""
				+ diasConsumo);

		if (dataLeituraAnterior != null) {
			consultarImovelActionForm
					.setDtLeituraAnteriorPoco(Util
							.formatarData(dataLeituraAnterior));
		}else{
			consultarImovelActionForm
			.setDtLeituraAnteriorPoco("");
		}
		// leitura anterior faturamento
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[5] != null) {
			consultarImovelActionForm
					.setLeituraAnteriorPoco(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[5])
							.toString());
		}else{
			consultarImovelActionForm
			.setLeituraAnteriorPoco("");
		}
		// data leitura atual informada
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[6] != null) {
			consultarImovelActionForm
					.setDtLeituraInformadaPoco((Util
							.formatarData((Date) arrayParmsMedicaoHistoricoLigacaoEsgoto[6])));
		}else{
			consultarImovelActionForm
			.setDtLeituraInformadaPoco("");
		}
		// leitura atual informada
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[7] != null) {
			consultarImovelActionForm
					.setLeituraAtualInformadaPoco(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[7])
							.toString());
		}else{
			consultarImovelActionForm
			.setLeituraAtualInformadaPoco("");
		}
		// data leitura faturada
		if (dtLeituraAtualFaturada != null) {
			consultarImovelActionForm
					.setDtLeituraFaturadaPoco(Util
							.formatarData(dtLeituraAtualFaturada));
		}else{
			consultarImovelActionForm
			.setDtLeituraFaturadaPoco("");
		}
		// leitura atual faturamento
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[4] != null) {
			consultarImovelActionForm
					.setLeituraAnteriorPoco(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[4])
							.toString());
		}else{
			consultarImovelActionForm
			.setLeituraAnteriorPoco("");
		}
		// descri��o leitura situa��o atual
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[8] != null) {
			consultarImovelActionForm
					.setSituacaoLeituraAtualPoco(((String) arrayParmsMedicaoHistoricoLigacaoEsgoto[8])
							.toString());
		}else{
			consultarImovelActionForm
			.setSituacaoLeituraAtualPoco("");
		}

		// data leitura atual faturamento
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[9] != null) {
			consultarImovelActionForm
					.setDtLeituraFaturadaPoco((Util
							.formatarData((Date) arrayParmsMedicaoHistoricoLigacaoEsgoto[9])));
		}else{
			consultarImovelActionForm
			.setDtLeituraFaturadaPoco("");
		}

		// leitura atual faturamento
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[10] != null) {
			consultarImovelActionForm
					.setLeituraAtualFaturadaPoco(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[10])
							.toString());
		}else{
			consultarImovelActionForm
			.setLeituraAtualFaturadaPoco("");
		}
		// id funcion�rio
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[11] != null) {
			consultarImovelActionForm
					.setCodigoFuncionarioPoco(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[11])
							.toString());
		}else{
			consultarImovelActionForm
			.setCodigoFuncionarioPoco("");
		}
		// descri��o leitura anormalidade informada
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[12] != null) {
			consultarImovelActionForm
					.setAnormalidadeLeituraInformadaPoco((String) arrayParmsMedicaoHistoricoLigacaoEsgoto[12]);
		}else{
			consultarImovelActionForm
			.setAnormalidadeLeituraInformadaPoco("");
		}
		// descri��o leitura anormalidade faturamento
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[13] != null) {
			consultarImovelActionForm
					.setAnormalidadeLeituraFaturadaPoco((String) arrayParmsMedicaoHistoricoLigacaoEsgoto[13]);
		}else{
			consultarImovelActionForm
			.setAnormalidadeLeituraFaturadaPoco("");
		}
		// numero consumo mes
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[14] != null) {
			consultarImovelActionForm
					.setConsumoMedidoEsgoto(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[14])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoMedidoEsgoto("");
		}

		// DADOS DO VOLUME DO MES DA LIGACAO DE ESGOTO
		
		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
		ligacaoEsgoto = imovel.getLigacaoEsgoto();
		
		if(ligacaoEsgoto != null){			
			FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, 
				ligacaoEsgoto.getId()));
			filtroLigacaoEsgoto.adicionarCaminhoParaCarregamentoEntidade("motivoCorte");
			filtroLigacaoEsgoto.adicionarCaminhoParaCarregamentoEntidade("supressaoMotivo");
			Collection colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgoto, 
				LigacaoEsgoto.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoLigacaoEsgoto)){
				ligacaoEsgoto = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
				
				if(ligacaoEsgoto.getDataCorteLigacaoEsgoto() != null){
					consultarImovelActionForm.setDataCorteEsgoto(
						Util.formatarData(ligacaoEsgoto
							.getDataCorteLigacaoEsgoto()));
				}
				
				if(ligacaoEsgoto.getDataSupressaoLigacaoEsgoto() != null){
					consultarImovelActionForm.setDataSupressaoEsgoto(
						Util.formatarData(ligacaoEsgoto
							.getDataSupressaoLigacaoEsgoto()));
				}
				
				if(consultarImovelActionForm.getSituacaoEsgotoAnaliseMedicaoConsumo().equals("CORTADO") &&						
						ligacaoEsgoto.getMotivoCorte() != null){
					MotivoCorte motivoCorte = ligacaoEsgoto.getMotivoCorte();
					consultarImovelActionForm.setMotivoCorteSupressaoEsgoto(motivoCorte.getDescricao());
					
				}
				
				if(consultarImovelActionForm.getSituacaoEsgotoAnaliseMedicaoConsumo().equals("SUPRIMIDO")
						&& ligacaoEsgoto.getSupressaoMotivo() != null ){
					SupressaoMotivo supressaoMotivo = ligacaoEsgoto.getSupressaoMotivo();
					consultarImovelActionForm.setMotivoCorteSupressaoEsgoto(supressaoMotivo.getDescricao());
					
				}
				
				if(ligacaoEsgoto.getDataRestabelecimentoEsgoto() != null){				
					consultarImovelActionForm.setDataRestabelecimentoEsgoto(Util.formatarData(ligacaoEsgoto
						.getDataRestabelecimentoEsgoto()));
					
				}
				
				if(ligacaoEsgoto.getDataReligacaoEsgoto() != null){
					consultarImovelActionForm.setDataReligacaoEsgoto(Util.formatarData(ligacaoEsgoto
						.getDataReligacaoEsgoto()));
				}
			}
		}

		int consumoFaturado = 0;
		// numero consumo fatura mes
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[15] != null) {
			consumoFaturado = (Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[15];
			consultarImovelActionForm
					.setConsumoFaturadoEsgoto(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[15])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoFaturadoEsgoto("");
		}

		// numero consumo rateio
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[16] != null) {
			consultarImovelActionForm
					.setConsumoRateioEsgoto(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[16])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoRateioEsgoto("");
		}
		// descri��o abreviada consumo anormalidade
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[17] != null) {
			consultarImovelActionForm
					.setAnormalidadeConsumoEsgoto((String) arrayParmsMedicaoHistoricoLigacaoEsgoto[17]);
		}else{
			consultarImovelActionForm
			.setAnormalidadeConsumoEsgoto("");
		}
		// descri��o abreviada consumo tipo
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[18] != null) {
			consultarImovelActionForm
					.setConsumoTipoEsgoto((String) arrayParmsMedicaoHistoricoLigacaoEsgoto[18]);
		}else{
			consultarImovelActionForm
			.setConsumoTipoEsgoto("");
		}

		// consumo m�dio do hidrometro
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[19] != null) {
			consultarImovelActionForm
					.setConsumoMedioHidrometroPoco(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[19])
							.toString());
			consumoMedioHidrometro = (Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[19];
		}else{
			consultarImovelActionForm
			.setConsumoMedioHidrometroPoco("");
		}
		// consumo medio do imovel
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[20] != null) {
			consultarImovelActionForm
					.setConsumoMedioImovelEsgoto(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[20])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoMedioImovelEsgoto("");
		}
		// consumo mes esgoto
		if (arrayParmsMedicaoHistoricoLigacaoEsgoto[21] != null) {
			consultarImovelActionForm
					.setConsumoMesEsgoto(((Integer) arrayParmsMedicaoHistoricoLigacaoEsgoto[21])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoMesEsgoto("");
		}

		if (consumoFaturado != 0
				&& consumoMedioHidrometro != 0) {
			int operacaoSubMult = (consumoFaturado - consumoMedioHidrometro) * 100;
			BigDecimal percentual = new BigDecimal(
					operacaoSubMult).divide(new BigDecimal(
					consumoMedioHidrometro), 2,
					BigDecimal.ROUND_HALF_UP);
			String valorPercentual = "" + percentual;
			consultarImovelActionForm
					.setPercentualVariacaoEsgoto(""
							+ valorPercentual.replace(".",
									",") + "%");

		}else{
			consultarImovelActionForm
			.setPercentualVariacaoEsgoto("");
		}
	}

	/**
	 *Seta no Form os dados da cole��o passada como parametro 
	 *obtida atrav�s do 
	 *pesquisarMedicaoConsumoHistoricoExcecoesApresentaDadosConsultarImovel
	 * 	 
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private int setarDadosMedicaoHistorico(
			ConsultarImovelActionForm consultarImovelActionForm,
			Collection<Object[]> colecaoParmsMedicaoHistorico,
			int consumoMedioHidrometro) {
		
		Object[] arrayParmsMedicaoHistorico = colecaoParmsMedicaoHistorico.iterator().next();

		// descri��o tipo medicao
		if (arrayParmsMedicaoHistorico[0] != null) {
			consultarImovelActionForm
					.setTipoMedicao((String) arrayParmsMedicaoHistorico[0]);
		}else{
			consultarImovelActionForm
			.setTipoMedicao("");
		}
		
		MedicaoTipo medicaoTipo = new MedicaoTipo();

		if (arrayParmsMedicaoHistorico[1] != null) {
			consultarImovelActionForm
					.setIdTipoMedicao(((Integer) arrayParmsMedicaoHistorico[1])
							.toString());
			medicaoTipo
					.setId((Integer) arrayParmsMedicaoHistorico[1]);
		}else{
			consultarImovelActionForm
			.setIdTipoMedicao("");
		}
		// data leitura anterior
		Date dataLeituraAnterior = null;
		if (arrayParmsMedicaoHistorico[2] != null) {
			dataLeituraAnterior = (Date) arrayParmsMedicaoHistorico[2];
		}
		// data leitura atual faturada
		Date dtLeituraAtualFaturada = null;
		if (arrayParmsMedicaoHistorico[3] != null) {
			dtLeituraAtualFaturada = (Date) arrayParmsMedicaoHistorico[3];
		}

		// --- fim variavel
		int diasConsumo = 0;
		if (dataLeituraAnterior != null
				&& dtLeituraAtualFaturada != null) {
			diasConsumo = gsan.util.Util
					.obterQuantidadeDiasEntreDuasDatas(
							dataLeituraAnterior,
							dtLeituraAtualFaturada);
		}

		consultarImovelActionForm.setDiasConsumo(""
				+ diasConsumo);

		if (dataLeituraAnterior != null) {
			consultarImovelActionForm.setDtLeituraAnterior(Util
					.formatarData(dataLeituraAnterior));
		}else{
			consultarImovelActionForm
			.setDtLeituraAnterior("");
		}
		// leitura anterior faturamento
		if (arrayParmsMedicaoHistorico[5] != null) {
			consultarImovelActionForm
					.setLeituraAnterior(((Integer) arrayParmsMedicaoHistorico[5])
							.toString());
		}else{
			consultarImovelActionForm
			.setLeituraAnterior("");
		}
		// data leitura atual informada
		if (arrayParmsMedicaoHistorico[6] != null) {
			consultarImovelActionForm
					.setDtLeituraInformada((Util
							.formatarData((Date) arrayParmsMedicaoHistorico[6])));
		}else{
			consultarImovelActionForm
			.setDtLeituraInformada("");
		}
		// leitura atual informada
		if (arrayParmsMedicaoHistorico[7] != null) {
			consultarImovelActionForm
					.setLeituraAtualInformada(((Integer) arrayParmsMedicaoHistorico[7])
							.toString());
		}else{
			consultarImovelActionForm
			.setLeituraAtualInformada("");
		}
		// data leitura faturada
		if (dtLeituraAtualFaturada != null) {
			consultarImovelActionForm.setDtLeituraFaturada(Util
					.formatarData(dtLeituraAtualFaturada));
		}else{
			consultarImovelActionForm
			.setDtLeituraFaturada("");
		}
		// leitura atual faturamento
		if (arrayParmsMedicaoHistorico[4] != null) {
			consultarImovelActionForm
					.setLeituraAnterior(((Integer) arrayParmsMedicaoHistorico[4])
							.toString());
		}else{
			consultarImovelActionForm
			.setLeituraAnterior("");
		}
		// descri��o leitura situa��o atual
		if (arrayParmsMedicaoHistorico[8] != null) {
			consultarImovelActionForm
					.setSituacaoLeituraAtual(((String) arrayParmsMedicaoHistorico[8])
							.toString());
		}else{
			consultarImovelActionForm
			.setSituacaoLeituraAtual("");
		}

		// data leitura atual faturamento
		if (arrayParmsMedicaoHistorico[9] != null) {
			consultarImovelActionForm
					.setDtLeituraFaturada((Util
							.formatarData((Date) arrayParmsMedicaoHistorico[9])));
		}else{
			consultarImovelActionForm
			.setDtLeituraFaturada("");
		}

		// leitura atual faturamento
		if (arrayParmsMedicaoHistorico[10] != null) {
			consultarImovelActionForm
					.setLeituraAtualFaturada(((Integer) arrayParmsMedicaoHistorico[10])
							.toString());
		}else{
			consultarImovelActionForm
			.setLeituraAtualFaturada("");
		}
		// id funcion�rio
		if (arrayParmsMedicaoHistorico[11] != null) {
			consultarImovelActionForm
					.setCodigoFuncionario(((Integer) arrayParmsMedicaoHistorico[11])
							.toString());
		}else{
			consultarImovelActionForm
			.setCodigoFuncionario("");
		}
		// descri��o leitura anormalidade informada
		if (arrayParmsMedicaoHistorico[12] != null) {
			consultarImovelActionForm
					.setAnormalidadeLeituraInformada((String) arrayParmsMedicaoHistorico[12]);
		}else{
			consultarImovelActionForm
			.setAnormalidadeLeituraInformada("");
		}
		// descri��o leitura anormalidade faturamento
		if (arrayParmsMedicaoHistorico[13] != null) {
			consultarImovelActionForm
					.setAnormalidadeLeituraFaturada((String) arrayParmsMedicaoHistorico[13]);
		}else{
			consultarImovelActionForm
			.setAnormalidadeLeituraFaturada("");
		}
		// numero consumo mes
		if (arrayParmsMedicaoHistorico[14] != null) {
			consultarImovelActionForm
					.setConsumoMedido(((Integer) arrayParmsMedicaoHistorico[14])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoMedido("");
		}

		// DADOS DO CONSUMO DO MES DA LIAGACAO DE AGUA

		int consumoFaturado = 0;
		// numero consumo fatura mes
		if (arrayParmsMedicaoHistorico[15] != null) {
			consumoFaturado = (Integer) arrayParmsMedicaoHistorico[15];
			consultarImovelActionForm
					.setConsumoFaturado(((Integer) arrayParmsMedicaoHistorico[15])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoFaturado("");
		}

		// numero consumo rateio
		if (arrayParmsMedicaoHistorico[16] != null) {
			consultarImovelActionForm
					.setConsumoRateio(((Integer) arrayParmsMedicaoHistorico[16])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoRateio("");
		}
		// descri��o abreviada consumo anormalidade
		if (arrayParmsMedicaoHistorico[17] != null) {
			consultarImovelActionForm
					.setAnormalidadeConsumo((String) arrayParmsMedicaoHistorico[17]);
		}else{
			consultarImovelActionForm
			.setAnormalidadeConsumo("");
		}
		// descri��o abreviada consumo tipo
		if (arrayParmsMedicaoHistorico[18] != null) {
			consultarImovelActionForm
					.setConsumoTipo((String) arrayParmsMedicaoHistorico[18]);
		}else{
			consultarImovelActionForm
			.setConsumoTipo("");
		}

		// consumo m�dio do hidrometro
		if (arrayParmsMedicaoHistorico[19] != null) {
			consultarImovelActionForm
					.setConsumoMedioHidrometro(((Integer) arrayParmsMedicaoHistorico[19])
							.toString());
			consumoMedioHidrometro = (Integer) arrayParmsMedicaoHistorico[19];
		}else{
			consultarImovelActionForm
			.setConsumoMedioHidrometro("");
		}
		// consumo medio do imovel
		if (arrayParmsMedicaoHistorico[20] != null) {
			consultarImovelActionForm
					.setConsumoMedioImovel(((Integer) arrayParmsMedicaoHistorico[20])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoMedioImovel("");
		}
		// consumo mes esgoto
		if (arrayParmsMedicaoHistorico[21] != null) {
			consultarImovelActionForm
					.setConsumoMesEsgoto(((Integer) arrayParmsMedicaoHistorico[21])
							.toString());
		}else{
			consultarImovelActionForm
			.setConsumoMesEsgoto("");
		}

		if (consumoFaturado != 0 && consumoMedioHidrometro != 0) {
			int operacaoSubMult = (consumoFaturado - consumoMedioHidrometro) * 100;
			BigDecimal percentual = new BigDecimal(
					operacaoSubMult).divide(new BigDecimal(
					consumoMedioHidrometro), 2,
					BigDecimal.ROUND_HALF_UP);
			String valorPercentual = "" + percentual;
			consultarImovelActionForm.setPercentualVariacao(""
					+ valorPercentual.replace(".", ",") + "%");

		}else{
			consultarImovelActionForm.setPercentualVariacao("");
		}
		
		return consumoMedioHidrometro;
	}

	/**
	 *Seta no Form os dados da cole��o passada como parametro 
	 *obtida atrav�s do pesquiarImovelExcecoesApresentaDados.
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosClienteImovelNaoLigacaoAgua(
			ConsultarImovelActionForm consultarImovelActionForm,
			Collection<Object[]> colecaoParmsClienteImovelNaoLigacaoAgua) {
		
		Object[] arrayParmsClienteImovel = colecaoParmsClienteImovelNaoLigacaoAgua.iterator().next();

		// DADOS DA LIGACAO DE ESGOTO
		// data liga��o esgoto
		if (arrayParmsClienteImovel[27] != null) {
			consultarImovelActionForm
					.setDataLigacaoEsgoto(Util
							.formatarData((Date) arrayParmsClienteImovel[27]));
		}else{
			consultarImovelActionForm
			.setDataLigacaoEsgoto("");
		}
		// descri��o liga��o esgoto diametro
		if (arrayParmsClienteImovel[28] != null) {
			consultarImovelActionForm
					.setDescricaoLigacaoEsgotoDiametro((String) arrayParmsClienteImovel[28]);
		}else{
			consultarImovelActionForm
			.setDescricaoLigacaoEsgotoDiametro("");
		}
		// descri��o liga��o esgoto material
		if (arrayParmsClienteImovel[29] != null) {
			consultarImovelActionForm
					.setDescricaoLigacaoEsgotoMaterial((String) arrayParmsClienteImovel[29]);
		}else{
			consultarImovelActionForm
			.setDescricaoLigacaoEsgotoMaterial("");
		}
		// descri��o liga��o esgoto perfil
		if (arrayParmsClienteImovel[30] != null) {
			consultarImovelActionForm
					.setDescricaoligacaoEsgotoPerfil((String) arrayParmsClienteImovel[30]);
		}else{
			consultarImovelActionForm
			.setDescricaoligacaoEsgotoPerfil("");
		}
		// numero consumo m�nimo esgoto
		if (arrayParmsClienteImovel[31] != null) {
			consultarImovelActionForm
					.setNumeroConsumominimoEsgoto(((Integer) arrayParmsClienteImovel[31])
							.toString());
		}else{
			consultarImovelActionForm
			.setNumeroConsumominimoEsgoto("");
		}
		// percentual liga��o esgoto
		if (arrayParmsClienteImovel[32] != null) {
			consultarImovelActionForm
					.setPercentualEsgoto((Util
							.formatarMoedaReal((BigDecimal) arrayParmsClienteImovel[32])));
		}else{
			consultarImovelActionForm
			.setPercentualEsgoto("");
		}
		// percentual coleta liga��o esgoto
		if (arrayParmsClienteImovel[33] != null) {
			consultarImovelActionForm
					.setPercentualAguaConsumidaColetada((Util
							.formatarMoedaReal((BigDecimal) arrayParmsClienteImovel[33])));
		}else{
			consultarImovelActionForm
			.setPercentualAguaConsumidaColetada("");
		}
		// descri��o tipo po�o
		if (arrayParmsClienteImovel[34] != null) {
			consultarImovelActionForm
					.setDescricaoPocoTipo((String) arrayParmsClienteImovel[34]);
		}else{
			consultarImovelActionForm
			.setDescricaoPocoTipo("");
		}

		// descri��o tipo po�o
		if (arrayParmsClienteImovel[37] != null) {
			consultarImovelActionForm
					.setIdLigacaoEsgoto(((Integer) arrayParmsClienteImovel[37])
							.toString());
		}else{
			consultarImovelActionForm
			.setIdLigacaoEsgoto("");
		}

		// DADOS DO HIDROMETRO DO PO�O

		// numero do hidrometro
		if (arrayParmsClienteImovel[9] != null) {
			consultarImovelActionForm
					.setNumeroHidrometroPoco(((String) arrayParmsClienteImovel[9])
							.toString());
		}else{
			consultarImovelActionForm
			.setNumeroHidrometroPoco("");
		}

		// data de instala��o de hidrometro
		if (arrayParmsClienteImovel[10] != null) {
			consultarImovelActionForm
					.setInstalacaoHidrometroPoco(Util
							.formatarData((Date) arrayParmsClienteImovel[10]));
		}else{
			consultarImovelActionForm
			.setInstalacaoHidrometroPoco("");
		}
		// descri��o hidrometro capacidade
		if (arrayParmsClienteImovel[11] != null) {
			consultarImovelActionForm
					.setCapacidadeHidrometroPoco((String) arrayParmsClienteImovel[11]);
		}else{
			consultarImovelActionForm
			.setCapacidadeHidrometroPoco("");
		}

		// descri��o hidrometro tipo
		if (arrayParmsClienteImovel[12] != null) {
			consultarImovelActionForm
					.setTipoHidrometroPoco((String) arrayParmsClienteImovel[12]);
		}else{
			consultarImovelActionForm
			.setTipoHidrometroPoco("");
		}
		// descri��o hidrometro marca
		if (arrayParmsClienteImovel[13] != null) {
			consultarImovelActionForm
					.setMarcaHidrometroPoco((String) arrayParmsClienteImovel[13]);
		}else{
			consultarImovelActionForm
			.setMarcaHidrometroPoco("");
		}
		// descri��o hidrometro local instala��o
		if (arrayParmsClienteImovel[14] != null) {
			consultarImovelActionForm
					.setLocalInstalacaoHidrometroPoco((String) arrayParmsClienteImovel[14]);
		}else{
			consultarImovelActionForm
			.setLocalInstalacaoHidrometroPoco("");
		}
		// descri��o hidrometro diametro
		if (arrayParmsClienteImovel[15] != null) {
			consultarImovelActionForm
					.setDiametroHidrometroPoco((String) arrayParmsClienteImovel[15]);
		}else{
			consultarImovelActionForm
			.setDiametroHidrometroPoco("");
		}
		// descri��o hidrometro prote��o
		if (arrayParmsClienteImovel[16] != null) {
			consultarImovelActionForm
					.setProtecaoHidrometroPoco((String) arrayParmsClienteImovel[16]);
		}else{
			consultarImovelActionForm
			.setProtecaoHidrometroPoco("");
		}
		// indicador cavalete do hidrometro instala��o hist�rico
		if (arrayParmsClienteImovel[17] != null) {
			Short icCavalete = (Short) arrayParmsClienteImovel[17];
			if (icCavalete != null && icCavalete == 1) {
				consultarImovelActionForm
						.setIndicadorCavaletePoco("Sim");
			} else {
				consultarImovelActionForm
						.setIndicadorCavaletePoco("N�o");
			}
		}else{
			consultarImovelActionForm
			.setIndicadorCavaletePoco("");
		}
		// ano fabrica��o do hidrometro
		if (arrayParmsClienteImovel[18] != null) {
			consultarImovelActionForm
					.setAnoFabricacaoPoco(((Short) arrayParmsClienteImovel[18])
							.toString());
		}else{
			consultarImovelActionForm
			.setAnoFabricacaoPoco("");
		}
		
		// Tipo Relojoaria
		if (arrayParmsClienteImovel[44] != null) {
			consultarImovelActionForm
					.setTipoRelojoaria(((String) arrayParmsClienteImovel[44])
							.toString());
		}else{
			consultarImovelActionForm
			.setTipoRelojoaria("");
		}
		
		// Usu�rio Respons�vel Instala��o
		if (arrayParmsClienteImovel[45] != null) {
			consultarImovelActionForm
					.setUsuarioResponsavelInstalacaoPoco(((String) arrayParmsClienteImovel[45])
							.toString());
		}else{
			consultarImovelActionForm
			.setUsuarioResponsavelInstalacaoPoco("");
		}
		
		// N�mero Lacre Instala��o
		if (arrayParmsClienteImovel[46] != null) {
			consultarImovelActionForm
					.setNumeroLacreInstalacaoPoco(((String) arrayParmsClienteImovel[46])
							.toString());
		}else{
			consultarImovelActionForm
			.setNumeroLacreInstalacaoPoco("");
		}
		
		// Condi��o Esgotamento
		if (arrayParmsClienteImovel[47] != null) {
			consultarImovelActionForm
					.setCondicaoEsgotamento(((String) arrayParmsClienteImovel[47])
							.toString());
		}else{
			consultarImovelActionForm
			.setCondicaoEsgotamento("");
		}
		
		// Sistema Caixa Inspe��o
		if (arrayParmsClienteImovel[48] != null) {
			consultarImovelActionForm
					.setSistemaCaixaInspecao(((String) arrayParmsClienteImovel[48])
							.toString());
		}else{
			consultarImovelActionForm
			.setSistemaCaixaInspecao("");
		}
		
		// Destino Dejetos
		if (arrayParmsClienteImovel[49] != null) {
			consultarImovelActionForm
					.setDestinoDejetos(((String) arrayParmsClienteImovel[49])
							.toString());
		}else{
			consultarImovelActionForm
			.setDestinoDejetos("");
		}
		
		// Destino �guas Pluviais
		if (arrayParmsClienteImovel[50] != null) {
			consultarImovelActionForm
					.setDestinoAguasPluviais(((String) arrayParmsClienteImovel[50])
							.toString());
		}else{
			consultarImovelActionForm
			.setDestinoAguasPluviais("");
		}
		
		// Finalidade do Hidr�metro do Po�o
		if (arrayParmsClienteImovel[60] != null) {
			consultarImovelActionForm.setIndicadorFinalidadeHidrometroPoco(((Short) arrayParmsClienteImovel[60]).toString());
		}else{
			consultarImovelActionForm.setIndicadorFinalidadeHidrometroPoco("");
		}
	}

	/**
	 * Seta no Form os dados da cole��o passada como parametro
	 * obtida atrav�s do pesquiarImovelExcecoesApresentaDados.
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosClienteImovelLigacaoAgua(
			ConsultarImovelActionForm consultarImovelActionForm,
			Collection<Object[]> colecaoParmsClienteImovelLigacaoAgua,FaturamentoGrupo faturamentoGrupo,
			Imovel imovel, Fachada fachada) {
		
		Object[] arrayParmClienteImovel = colecaoParmsClienteImovelLigacaoAgua.iterator().next();

		
		// id faturamento grupo
		if (arrayParmClienteImovel[52] != null){
			
			consultarImovelActionForm.setGrupoFaturamento(((Integer) arrayParmClienteImovel[52]).toString());
			
			faturamentoGrupo.setId((Integer)arrayParmClienteImovel[52]);
		}
		else if (arrayParmClienteImovel[0] != null) {
			
			consultarImovelActionForm.setGrupoFaturamento(((Integer) arrayParmClienteImovel[0]).toString());
			
			faturamentoGrupo.setId((Integer)arrayParmClienteImovel[0]);
		}
		else{
			
			consultarImovelActionForm.setGrupoFaturamento("");
		}
		
		// ano mes faturamento grupo
		if (arrayParmClienteImovel[53] != null){
			
			consultarImovelActionForm
			.setMesAnoFaturamentoCorrente(Util.formatarAnoMesParaMesAno((Integer) arrayParmClienteImovel[53]));
			
			faturamentoGrupo.setAnoMesReferencia((Integer) arrayParmClienteImovel[53]);
		}
		else if (arrayParmClienteImovel[1] != null) {
			
			consultarImovelActionForm
			.setMesAnoFaturamentoCorrente(Util.formatarAnoMesParaMesAno((Integer) arrayParmClienteImovel[1]));
			
			faturamentoGrupo.setAnoMesReferencia((Integer)arrayParmClienteImovel[1]);
		}
		else{
			
			consultarImovelActionForm.setMesAnoFaturamentoCorrente("");
		}
		

		// nome empresa
		if (arrayParmClienteImovel[54] != null) {
			
			consultarImovelActionForm.setEmpresaLeitura((String) arrayParmClienteImovel[54]);
		}
		else if (arrayParmClienteImovel[2] != null) {
			
			consultarImovelActionForm.setEmpresaLeitura((String) arrayParmClienteImovel[2]);
		}
		else{
			
			consultarImovelActionForm.setEmpresaLeitura("");
		}
		
		//	dia vencimento faturamento grupo
		if ( arrayParmClienteImovel[55] != null ) {
			consultarImovelActionForm
				.setDiaVencimento(((Short) arrayParmClienteImovel[55])
						.toString());
		} else if (arrayParmClienteImovel[41] != null) {
			consultarImovelActionForm
					.setDiaVencimento(((Short) arrayParmClienteImovel[41])
							.toString());
		}else{
			consultarImovelActionForm
			.setDiaVencimento("");
		}
		
		// rota
		if (arrayParmClienteImovel[51] != null){
			
			consultarImovelActionForm.setRota(((Short) arrayParmClienteImovel[51]).toString());
		}
		else if (arrayParmClienteImovel[42] != null) {
			
			consultarImovelActionForm.setRota(((Short) arrayParmClienteImovel[42]).toString());
		}
		else{
			
			consultarImovelActionForm.setRota("");
		}
		
		// sequencial rota
		if (arrayParmClienteImovel[43] != null) {
			consultarImovelActionForm
					.setSequencialRota(((Integer) arrayParmClienteImovel[43])
							.toString());
		}else{
			consultarImovelActionForm
			.setSequencialRota("");
		}

		if (arrayParmClienteImovel[20] != null) {
			consultarImovelActionForm
					.setDataLigacaoAgua(Util
							.formatarData((Date) arrayParmClienteImovel[20]));
		}else{
			consultarImovelActionForm
			.setDataLigacaoAgua("");
		}	
		
		if(imovel.getLigacaoAgua() != null){
			LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
			MotivoCorte motivoCorte = new MotivoCorte();
			SupressaoMotivo supressaoMotivo = new SupressaoMotivo(); 
			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
			filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("motivoCorte");
			filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("supressaoMotivo");
			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
			Collection colecaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoLigacaoAgua)){
				ligacaoAgua = (LigacaoAgua) 
						Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				motivoCorte = ligacaoAgua.getMotivoCorte();
				supressaoMotivo = ligacaoAgua.getSupressaoMotivo();
			}		
			//motivo de Corte e motivo de supressao
			if(ligacaoAgua.getMotivoCorte() != null){
				consultarImovelActionForm.setMotivoCorteSupressaoAgua(motivoCorte.getDescricao());
				
			}else if(ligacaoAgua.getSupressaoMotivo() != null){
				consultarImovelActionForm.setMotivoCorteSupressaoAgua(supressaoMotivo.getDescricao());
				
			}else{
				consultarImovelActionForm.setMotivoCorteSupressaoAgua(null);
			}
		}
		
		// data corte agua
		if (arrayParmClienteImovel[21] != null) {
			consultarImovelActionForm
					.setDataCorteAgua(Util
							.formatarData((Date) arrayParmClienteImovel[21]));
		}else{
			consultarImovelActionForm
			.setDataCorteAgua("");
		}

		// data religa��o agua
		if (arrayParmClienteImovel[22] != null) {
			consultarImovelActionForm
					.setDataReligacaoAgua(Util
							.formatarData((Date) arrayParmClienteImovel[22]));
		}else{
			consultarImovelActionForm
			.setDataReligacaoAgua("");
		}
		// data supress�o agua
		if (arrayParmClienteImovel[23] != null) {
			consultarImovelActionForm
					.setDataSupressaoAgua(Util
							.formatarData((Date) arrayParmClienteImovel[23]));
		}else{
			consultarImovelActionForm
			.setDataSupressaoAgua("");
		}
		// data restabelecimento agua
		if (arrayParmClienteImovel[35] != null) {
			consultarImovelActionForm
					.setDataRestabelecimentoAgua(Util
							.formatarData((Date) arrayParmClienteImovel[35]));
		}else{
			consultarImovelActionForm
			.setDataRestabelecimentoAgua("");
		}

		// descri��o liga��o agua diametro
		if (arrayParmClienteImovel[24] != null) {
			consultarImovelActionForm
					.setDescricaoLigacaoAguaDiametro((String) arrayParmClienteImovel[24]);
		}else{
			consultarImovelActionForm
			.setDescricaoLigacaoAguaDiametro("");
		}
		// descri��o liga��o agua material
		if (arrayParmClienteImovel[25] != null) {
			consultarImovelActionForm
					.setDescricaoLigacaoAguaMaterial((String) arrayParmClienteImovel[25]);
		}else{
			consultarImovelActionForm
			.setDescricaoLigacaoAguaMaterial("");
		}

		// Perfil de liga��o
		if (arrayParmClienteImovel[36] != null) {
			consultarImovelActionForm
					.setDescricaoligacaoAguaPerfil((String) arrayParmClienteImovel[36]);
		}else{
			consultarImovelActionForm
			.setDescricaoligacaoAguaPerfil("");
		}

		// numero consumo m�nimo agua
		if (arrayParmClienteImovel[26] != null) {
			consultarImovelActionForm
					.setNumeroConsumominimoAgua(((Integer) arrayParmClienteImovel[26])
							.toString());
		}else{
			consultarImovelActionForm
			.setNumeroConsumominimoAgua("");
		}

		// DADOS DO HIDROMETRO DA LIGACAO DE AGUA

		// numero do hidrometro
		if (arrayParmClienteImovel[9] != null) {
			consultarImovelActionForm
					.setNumeroHidrometro(((String) arrayParmClienteImovel[9])
							.toString());
		}else{
			consultarImovelActionForm
			.setNumeroHidrometro("");
		}

		// data de instala��o de hidrometro
		if (arrayParmClienteImovel[10] != null) {
			consultarImovelActionForm
					.setInstalacaoHidrometro(Util
							.formatarData((Date) arrayParmClienteImovel[10]));
		}else{
			consultarImovelActionForm
			.setInstalacaoHidrometro("");
		}
		// descri��o hidrometro capacidade
		if (arrayParmClienteImovel[11] != null) {
			consultarImovelActionForm
					.setCapacidadeHidrometro((String) arrayParmClienteImovel[11]);
		}else{
			consultarImovelActionForm
			.setCapacidadeHidrometro("");
		}

		// descri��o hidrometro tipo
		if (arrayParmClienteImovel[12] != null) {
			consultarImovelActionForm
					.setTipoHidrometro((String) arrayParmClienteImovel[12]);
		}else{
			consultarImovelActionForm
			.setTipoHidrometro("");
		}
		// descri��o hidrometro marca
		if (arrayParmClienteImovel[13] != null) {
			consultarImovelActionForm
					.setMarcaHidrometro((String) arrayParmClienteImovel[13]);
		}else{
			consultarImovelActionForm
			.setMarcaHidrometro("");
		}
		// descri��o hidrometro local instala��o
		if (arrayParmClienteImovel[14] != null) {
			consultarImovelActionForm
					.setLocalInstalacaoHidrometro((String) arrayParmClienteImovel[14]);
		}else{
			consultarImovelActionForm
			.setLocalInstalacaoHidrometro("");
		}
		// descri��o hidrometro diametro
		if (arrayParmClienteImovel[15] != null) {
			consultarImovelActionForm
					.setDiametroHidrometro((String) arrayParmClienteImovel[15]);
		}else{
			consultarImovelActionForm
			.setDiametroHidrometro("");
		}
		// descri��o hidrometro prote��o
		if (arrayParmClienteImovel[16] != null) {
			consultarImovelActionForm
					.setProtecaoHidrometro((String) arrayParmClienteImovel[16]);
		}else{
			consultarImovelActionForm
			.setProtecaoHidrometro("");
		}
		// indicador cavalete do hidrometro instala��o hist�rico
		if (arrayParmClienteImovel[17] != null) {
			Short icCavalete = (Short) arrayParmClienteImovel[17];
			if (icCavalete != null && icCavalete == 1) {
				consultarImovelActionForm
						.setIndicadorCavalete("Sim");
			} else {
				consultarImovelActionForm
						.setIndicadorCavalete("N�o");
			}
		}else{
			consultarImovelActionForm
			.setIndicadorCavalete("");
		}
		// ano fabrica��o do hidrometro
		if (arrayParmClienteImovel[18] != null) {
			consultarImovelActionForm
					.setAnoFabricacao(((Short) arrayParmClienteImovel[18])
							.toString());
		}else{
			consultarImovelActionForm
			.setAnoFabricacao("");
		}
		
		// Tipo Relojoaria
		if (arrayParmClienteImovel[44] != null) {
			consultarImovelActionForm
					.setTipoRelojoaria(((String) arrayParmClienteImovel[44])
							.toString());
		}else{
			consultarImovelActionForm
			.setTipoRelojoaria("");
		}
		
		// Usu�rio Respons�vel Instala��o
		if (arrayParmClienteImovel[45] != null) {
			consultarImovelActionForm
					.setUsuarioResponsavelInstalacao(((String) arrayParmClienteImovel[45])
							.toString());
		}else{
			consultarImovelActionForm
			.setUsuarioResponsavelInstalacao("");
		}
		
		// N�mero Lacre Instala��o
		if (arrayParmClienteImovel[46] != null) {
			consultarImovelActionForm
					.setNumeroLacreInstalacao(((String) arrayParmClienteImovel[46])
							.toString());
		}else{
			consultarImovelActionForm
			.setNumeroLacreInstalacao("");
		}
		
		// Hidr�metro
		if (arrayParmClienteImovel[56] != null) {
			if (((Short)arrayParmClienteImovel[56]).compareTo(ConstantesSistema.SIM) == 0) {
				consultarImovelActionForm
						.setHidrometro("MACROMEDIDOR");
			} else {
				consultarImovelActionForm
						.setHidrometro("MICROMEDIDOR");
			}
		}else{
			consultarImovelActionForm
				.setHidrometro("");
		}
		
		// Tombamento
		if (arrayParmClienteImovel[57] != null) {
			consultarImovelActionForm
					.setTombamento(((String) arrayParmClienteImovel[57])
							.toString());
		}else{
			consultarImovelActionForm
				.setTombamento("");
		}
		
		// Classe de Press�o
		if (arrayParmClienteImovel[58] != null) {
			consultarImovelActionForm
					.setHidrometroClassePressao(((String) arrayParmClienteImovel[58])
							.toString());
		}else{
			consultarImovelActionForm
				.setHidrometroClassePressao("");
		}
		
		// Erro do Macromedidor
		if (arrayParmClienteImovel[59] != null) {
			consultarImovelActionForm
					.setHidrometroFatorCorrecao(((String) arrayParmClienteImovel[59])
							.toString());
		}else{
			consultarImovelActionForm
				.setHidrometroFatorCorrecao("");
		}
	}

	/**
	 *Esse m�todo limpa todos os atributos do form
	 *e os atributos na sesss�o 
	 *que s�o usados pelo action e/ou jsp.
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		sessao.removeAttribute("consultarImovelActionForm");

		sessao.removeAttribute("imovelDadosAnaliseMedicaoConsumo");
		sessao.removeAttribute("medicoesHistoricos");
		sessao.removeAttribute("imoveisMicromedicao");
		sessao.removeAttribute("colecaoImovelSubcategoria");
		sessao.removeAttribute("leituraConsumoActionForm");
		sessao.removeAttribute("idImovelPrincipalAba");
		sessao.removeAttribute("enderecoAnaliseMedicaoConsumo");
		
		sessao.removeAttribute("medicoesHistoricosPoco");
		sessao.removeAttribute("imoveisMicromedicaoEsgoto");
		
		
		consultarImovelActionForm.setIdImovelDadosComplementares(null);
		consultarImovelActionForm.setIdImovelDadosCadastrais(null);
		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setIdImovelDebitos(null);
		consultarImovelActionForm.setIdImovelPagamentos(null);
		consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
		consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
		consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
		consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
		consultarImovelActionForm.setImovIdAnt(null);

		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm
				.setMatriculaImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm
				.setSituacaoAguaAnaliseMedicaoConsumo(null);
		consultarImovelActionForm
				.setSituacaoEsgotoAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setTipoApresentacao(null);
		consultarImovelActionForm.setLocalidade(null);
		consultarImovelActionForm.setNomeLocalidade(null);
		consultarImovelActionForm.setSetorComercialID(null);
		consultarImovelActionForm.setSetorComercial(null);
		consultarImovelActionForm.setSetorComercialNome(null);
		consultarImovelActionForm.setQuadraInicial(null);
		consultarImovelActionForm.setQuadraInicialNome(null);
		consultarImovelActionForm.setQuadraInicialID(null);
		consultarImovelActionForm.setQuadraInicialMensagem(null);
		consultarImovelActionForm.setQuadraFinal(null);
		consultarImovelActionForm.setQuadraFinalNome(null);
		consultarImovelActionForm.setQuadraFinalID(null);
		consultarImovelActionForm.setQuadraFinalMensagem(null);
		consultarImovelActionForm.setImovel(null);
		consultarImovelActionForm.setImovelCondominio(null);
		consultarImovelActionForm.setGrupoFaturamento(null);
		consultarImovelActionForm.setDiaVencimento(null);
		consultarImovelActionForm.setEmpresaLeitura(null);
		consultarImovelActionForm.setRota(null);
		consultarImovelActionForm.setSequencialRota(null);
		consultarImovelActionForm.setIndicadorImovelCondominio(null);
		consultarImovelActionForm.setPerfilImovel(null);
		consultarImovelActionForm.setCategoriaImovel(null);
		consultarImovelActionForm.setQuantidadeEconomia(null);
		consultarImovelActionForm.setTipoMedicao(null);
		consultarImovelActionForm.setIdTipoMedicao(null);
		consultarImovelActionForm.setTipoLigacao(null);
		consultarImovelActionForm.setTipoAnormalidade(null);
		consultarImovelActionForm.setAnormalidadeLeituraInformada(null);
		consultarImovelActionForm.setAnormalidadeLeituraFaturada(null);
		consultarImovelActionForm.setAnormalidadeConsumo(null);
		consultarImovelActionForm.setConsumoFaturamdoMinimo(null);
		consultarImovelActionForm.setConsumoMedidoMinimo(null);
		consultarImovelActionForm.setConsumoMedioMinimo(null);
		consultarImovelActionForm.setInscricaoTipo(null);
		consultarImovelActionForm.setMesAnoFaturamentoCorrente(null);
		consultarImovelActionForm.setInscricaoImovel(null);
		consultarImovelActionForm.setEnderecoFormatado(null);
		consultarImovelActionForm.setLigacaoAguaSituacao(null);
		consultarImovelActionForm.setLigacaoEsgotoSituacao(null);
		consultarImovelActionForm.setClienteNome(null);
		consultarImovelActionForm.setClienteCpfCnpj(null);
		consultarImovelActionForm.setNumeroHidrometro(null);
		consultarImovelActionForm.setHidrometro(null);
		consultarImovelActionForm.setCapacidadeHidrometro(null);
		consultarImovelActionForm.setTipoHidrometro(null);
		consultarImovelActionForm.setMarcaHidrometro(null);
		consultarImovelActionForm.setDiametroHidrometro(null);
		consultarImovelActionForm.setInstalacaoHidrometro(null);
		consultarImovelActionForm.setLocalInstalacaoHidrometro(null);
		consultarImovelActionForm.setAnoFabricacao(null);
		consultarImovelActionForm.setProtecaoHidrometro(null);
		consultarImovelActionForm.setIndicadorCavalete(null);
		consultarImovelActionForm.setTombamento(null);
		consultarImovelActionForm.setHidrometroClassePressao(null);
		consultarImovelActionForm.setHidrometroFatorCorrecao(null);
		consultarImovelActionForm.setDtLeituraAnterior(null);
		consultarImovelActionForm.setLeituraAnterior(null);
		consultarImovelActionForm.setDtLeituraInformada(null);
		consultarImovelActionForm.setLeituraAtualInformada(null);
		consultarImovelActionForm.setSituacaoLeituraAtual(null);
		consultarImovelActionForm.setCodigoFuncionario(null);
		consultarImovelActionForm.setDtLeituraFaturada(null);
		consultarImovelActionForm.setLeituraAtualFaturada(null);
		consultarImovelActionForm.setConsumoRateio(null);
		consultarImovelActionForm.setConsumoFaturado(null);
		consultarImovelActionForm.setConsumoMedido(null);
		consultarImovelActionForm.setPercentualVariacao(null);
		consultarImovelActionForm.setConsumoMedioHidrometro(null);
		consultarImovelActionForm.setDiasConsumo(null);
		consultarImovelActionForm.setConsumoTipo(null);
		consultarImovelActionForm.setIdImovelSubstituirConsumo(null);
		consultarImovelActionForm.setHabilitaLupa(null);
		consultarImovelActionForm.setIdEmpresa(null);
		consultarImovelActionForm.setIdGrupoFaturamento(null);
		consultarImovelActionForm.setLocalidadeFiltro(null);
		consultarImovelActionForm.setNomeLocalidadeFiltro(null);
		consultarImovelActionForm.setSetorComercialFiltro(null);
		consultarImovelActionForm.setQuadraInicialFiltro(null);
		consultarImovelActionForm.setQuadraFinalFiltro(null);
		consultarImovelActionForm.setImovelFiltro(null);
		consultarImovelActionForm.setImovelCondominioFiltro(null);
		consultarImovelActionForm.setImovelMatriculaFiltro(null);
		consultarImovelActionForm.setImovelMatriculaCondominioFiltro(null);
		consultarImovelActionForm.setDataLigacaoAgua(null);
		consultarImovelActionForm.setDataCorteAgua(null);
		consultarImovelActionForm.setDataReligacaoAgua(null);
		consultarImovelActionForm.setDataSupressaoAgua(null);
		consultarImovelActionForm.setDataRestabelecimentoAgua(null);
		consultarImovelActionForm.setDescricaoLigacaoAguaDiametro(null);
		consultarImovelActionForm.setDescricaoLigacaoAguaMaterial(null);
		consultarImovelActionForm.setDescricaoligacaoAguaPerfil(null);
		consultarImovelActionForm.setNumeroConsumominimoAgua(null);
		consultarImovelActionForm.setIdLigacaoEsgoto(null);
		consultarImovelActionForm.setConsumoMesEsgoto(null);
		consultarImovelActionForm.setDataLigacaoEsgoto(null);
		consultarImovelActionForm.setDescricaoLigacaoEsgotoDiametro(null);
		consultarImovelActionForm.setDescricaoLigacaoEsgotoMaterial(null);
		consultarImovelActionForm.setDescricaoligacaoEsgotoPerfil(null);
		consultarImovelActionForm.setNumeroConsumominimoEsgoto(null);
		consultarImovelActionForm.setPercentualEsgoto(null);
		consultarImovelActionForm.setPercentualAguaConsumidaColetada(null);
		consultarImovelActionForm.setDescricaoPocoTipo(null);
		consultarImovelActionForm.setIdGrupoFaturamentoFiltro(null);
		consultarImovelActionForm.setIdEmpresaFiltro(null);
		consultarImovelActionForm.setIndicadorImovelCondominioFiltro(null);
		consultarImovelActionForm.setConsumoMedioImovel(null);
		consultarImovelActionForm.setPerfilImovelFiltro(null);
		consultarImovelActionForm.setCategoriaImovelFiltro(null);
		consultarImovelActionForm.setQuantidadeEconomiaFiltro(null);
		consultarImovelActionForm.setTipoMedicaoFiltro(null);
		consultarImovelActionForm.setIdTipoMedicaoFiltro(null);
		consultarImovelActionForm.setTipoLigacaoFiltro(null);
		consultarImovelActionForm.setTipoAnormalidadeFiltro(null);
		consultarImovelActionForm.setEnderecoAnaliseMedicaoConsumo(null);
		consultarImovelActionForm
				.setAnormalidadeLeituraInformadaFiltro(null);
		consultarImovelActionForm
				.setAnormalidadeLeituraFaturadaFiltro(null);
		consultarImovelActionForm.setAnormalidadeConsumoFiltro(null);
		consultarImovelActionForm.setConsumoFaturamdoMinimoFiltro(null);
		consultarImovelActionForm.setConsumoMedidoMinimoFiltro(null);
		consultarImovelActionForm.setConsumoMedioMinimoFiltro(null);
		consultarImovelActionForm.setIdLigacaoAguaSituacao(null);
		consultarImovelActionForm.setIdLigacaoAgua(null);
		consultarImovelActionForm.setIdAnormalidade(null);
		consultarImovelActionForm.setDescricaoAnormalidade(null);

		
		consultarImovelActionForm.setTipoMedicaoPoco(null);
		consultarImovelActionForm.setNumeroHidrometroPoco(null);
		consultarImovelActionForm.setInstalacaoHidrometroPoco(null);
		consultarImovelActionForm.setCapacidadeHidrometroPoco(null);
		consultarImovelActionForm.setTipoHidrometroPoco(null);
		consultarImovelActionForm.setMarcaHidrometroPoco(null);
		consultarImovelActionForm.setLocalInstalacaoHidrometroPoco(null);
		consultarImovelActionForm.setDiametroHidrometroPoco(null);
		consultarImovelActionForm.setProtecaoHidrometroPoco(null);
		consultarImovelActionForm.setIndicadorCavaletePoco(null);
		consultarImovelActionForm.setAnoFabricacaoPoco(null);	
		consultarImovelActionForm.setDtLeituraAnteriorPoco(null);
		consultarImovelActionForm.setLeituraAnteriorPoco(null);
		consultarImovelActionForm.setDtLeituraInformadaPoco(null);
		consultarImovelActionForm.setLeituraAtualInformadaPoco(null);
		consultarImovelActionForm.setDtLeituraFaturadaPoco(null);
		consultarImovelActionForm.setLeituraAtualFaturadaPoco(null);
		consultarImovelActionForm.setSituacaoLeituraAtualPoco(null);
		consultarImovelActionForm.setCodigoFuncionarioPoco(null);
		consultarImovelActionForm.setAnormalidadeLeituraInformadaPoco(null);
		consultarImovelActionForm.setAnormalidadeLeituraFaturadaPoco(null);
		consultarImovelActionForm.setConsumoMedioHidrometroPoco(null);
		consultarImovelActionForm.setConsumoMedidoEsgoto(null);
		consultarImovelActionForm.setConsumoFaturadoEsgoto(null);
		consultarImovelActionForm.setConsumoRateioEsgoto(null);
		consultarImovelActionForm.setConsumoMedioImovelEsgoto(null);
		consultarImovelActionForm.setAnormalidadeConsumoEsgoto(null);
		consultarImovelActionForm.setPercentualVariacaoEsgoto(null);
		consultarImovelActionForm.setDiasConsumoEsgoto(null);
		consultarImovelActionForm.setConsumoTipoEsgoto(null);
		consultarImovelActionForm.setDataSupressaoParcialAgua(null);
		consultarImovelActionForm.setIndicadorValidaCPFCNPJ("2");
		consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
		consultarImovelActionForm.setSituacaoAguaAnterior(null);
		consultarImovelActionForm.setSituacaoEsgotoAnterior(null);
		consultarImovelActionForm.setObservacaoExclusaoImovel(null);
		consultarImovelActionForm.setDataReligacaoEsgoto(null);
		consultarImovelActionForm.setDataRestabelecimentoEsgoto(null);
	}

	/**
	 * Caso o usu�rio tenha clicado no bot�o de limpar
	 * esse m�todo retornar� true.
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 * Esse m�todo verifica se j� foi informado um im�vel em outra tela.
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));
	}

	/**
	 * Esse m�todo verifica se o im�vel foi informado na tela
	 * de Analise Liga��o Consumo
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaAnaliseLigacaoConsumo(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo());
	}

	/**
	 * Esse m�todo retorna o id do im�vel a ser pesquisado,
	 * verificando se � o id possivelmente informado pelo usu�rio na tela 
	 * de Analise Liga��o Consumo ou se o id j� informado em uma outra tela.
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {
		
		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		
		if( isImovelInformadoTelaAnaliseLigacaoConsumo(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){
		
			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
					return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
				return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo();
	}

	/**
	 * Consulta o Imovel com todas as informa��es necess�rias,
	 * ou simplesmetne pega o Imovel da sess�o caso o mesmo j�
	 * tenha sido pesquisado.
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosAnaliseMedicaoConsumo") == null){
			imovel = Fachada.getInstancia().consultarImovelAnaliseMedicaoConsumo(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosAnaliseMedicaoConsumo");
			
			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelAnaliseMedicaoConsumo(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo().trim()));
			}
		}
		return imovel;
	}

	/**
	 * Esse m�todo retorna true se foi necess�rio consultar um novo imovel.
	 * Caso o im�vel seja o mesmo j� consultado anteriormente ele retorna false.
	 *
	 *@since 25/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		if(sessao.getAttribute("imovelDadosAnaliseMedicaoConsumo") == null){
			return true;
		}
		
		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosAnaliseMedicaoConsumo");
		
		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo().trim()) ){
			return true;
		}

		return false;
	}

	
	class ComparatorMedicaoHistorico implements Comparator<MedicaoHistorico>{
			public int compare(MedicaoHistorico a, MedicaoHistorico b) {
				
				int retorno = 0;
				Integer anoMesReferencia1 = a.getAnoMesReferencia();
				Integer anoMesReferencia2 = b.getAnoMesReferencia();

				if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
					retorno = -1;
				}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
					retorno = 1;
				}
				
				return retorno;

			}
	}

	class ComparatorImovelMicromedicao implements Comparator<ImovelMicromedicao>{
		public int compare(ImovelMicromedicao a, ImovelMicromedicao b) {
			
			int retorno = 0;
			Integer anoMesReferencia1 = a.getConsumoHistorico().getReferenciaFaturamento();
			Integer anoMesReferencia2 = b.getConsumoHistorico().getReferenciaFaturamento();

			if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
				retorno = -1;
			}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
				retorno = 1;
			}			
			return retorno;
		}
	}
}