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
package gcom.relatorio.cadastro.imovel;

import gcom.arrecadacao.bean.DebitoAutomaticoRelatoriosHelper;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.cadastro.imovel.ImovelCadastroOcorrencia;
import gcom.cadastro.imovel.ImovelEloAnormalidade;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.bean.ImovelCadastroOcorrenciaRelatoriosHelper;
import gcom.cadastro.imovel.bean.ImovelCobrancaSituacaoHelper;
import gcom.cadastro.imovel.bean.ImovelEloAnormalidadeRelatoriosHelper;
import gcom.cadastro.imovel.bean.ImovelRamoAtividadeRelatoriosHelper;
import gcom.cobranca.CobrancaSituacaoHistorico;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.VencimentoAlternativo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDadosComplementaresImovel extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioDadosComplementaresImovel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DADOS_COMPLEMENTARES_IMOVEL);
	}
	
	public Object executar() throws TarefaException {
		
		List<RelatorioDadosComplementaresImovelBean> relatorioBeans = new ArrayList<RelatorioDadosComplementaresImovelBean>();
		relatorioBeans.add( criarRelatorioBean() );
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DADOS_COMPLEMENTARES_IMOVEL, parametros,ds, tipoFormatoRelatorio);
	}

	/**
	 * Esse m�todo cria o RelatorioBean atrav�s dos parametros
	 * enviado a este objeto.
	 *
	 *@since 23/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioDadosComplementaresImovelBean criarRelatorioBean() {
		
		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");
		
		RelatorioDadosComplementaresImovelBean relatorioBean = new RelatorioDadosComplementaresImovelBean();
		
		relatorioBean.setInscricaoImovel(consultarImovelForm.getMatriculaImovelDadosComplementares());
		relatorioBean.setMatriculaImovel(consultarImovelForm.getIdImovelDadosComplementares());
		relatorioBean.setSituacaoAguaImovel(consultarImovelForm.getSituacaoAguaDadosComplementares());
		relatorioBean.setSituacaoEsgotoImovel(consultarImovelForm.getSituacaoEsgotoDadosComplementares());		
		relatorioBean.setTarifaConsumo(consultarImovelForm.getTarifaConsumoDadosComplementares());
		relatorioBean.setQuantidadeRetificacoes(consultarImovelForm.getQuantidadeRetificacoesDadosComplementares());
		relatorioBean.setQuantidadeParcelamento(consultarImovelForm.getQuantidadeParcelamentosDadosComplementares());		
		relatorioBean.setQuantidadeReparcelamento(consultarImovelForm.getQuantidadeReparcelamentoDadosComplementares());
		relatorioBean.setQuantidadeReparcelamentosConsecutivos(consultarImovelForm.getQuantidadeReparcelamentoConsecutivosDadosComplementares());
		relatorioBean.setSituacaoCobranca(consultarImovelForm.getSituacaoCobrancaDadosComplementares());
		
		if(Util.verificarNaoVazio(consultarImovelForm.getIdFuncionario())
				&& Util.verificarNaoVazio(consultarImovelForm.getNomeFuncionario())){
			
			relatorioBean.setFuncionarioResponsavel(consultarImovelForm.getIdFuncionario() + " - " + consultarImovelForm.getNomeFuncionario());
		}
		
		relatorioBean.setInformacoesComplementares(consultarImovelForm.getInformacoesComplementares());

		Collection<VencimentoAlternativo> colecaoVencimentosAlternativos = 
			(Collection<VencimentoAlternativo>) getParametro("colecaoVencimentosAlternativos");

		Collection<DebitoAutomatico> colecaoDebitosAutomaticos = 
			(Collection<DebitoAutomatico>) getParametro("colecaoDebitosAutomaticos");

		Collection<FaturamentoSituacaoHistorico> colecaoFaturamentosSituacaoHistorico = 
			(Collection<FaturamentoSituacaoHistorico>) getParametro("colecaoFaturamentosSituacaoHistorico");

		Collection<CobrancaSituacaoHistorico> colecaoCobrancasSituacaoHistorico = 
			(Collection<CobrancaSituacaoHistorico>) getParametro("colecaoCobrancasSituacaoHistorico");

		Collection<ImovelCadastroOcorrencia> colecaoImovelCadastroOcorrencia = 
			(Collection<ImovelCadastroOcorrencia>) getParametro("colecaoImovelCadastroOcorrencia");

		Collection<ImovelEloAnormalidade> colecaoImovelEloAnormalidade = 
			(Collection<ImovelEloAnormalidade>) getParametro("colecaoImovelEloAnormalidade");

		Collection<ImovelRamoAtividade> colecaoImovelRamosAtividade = 
			(Collection<ImovelRamoAtividade>) getParametro("colecaoImovelRamosAtividade");		
		
		Collection<ImovelCobrancaSituacaoHelper> colecaoSituacoesCobranca = 
			(Collection<ImovelCobrancaSituacaoHelper>)getParametro("colecaoSituacoesCobranca");

		if( !Util.isVazioOrNulo(colecaoVencimentosAlternativos)){			
			relatorioBean.setColecaoVencimentosAlternativos(
					new JRBeanCollectionDataSource( colecaoVencimentosAlternativos ));
		}

		if( !Util.isVazioOrNulo(colecaoDebitosAutomaticos)){
			
			ArrayList<DebitoAutomaticoRelatoriosHelper> colecaoHelpers = new ArrayList<DebitoAutomaticoRelatoriosHelper>();
			
			for(DebitoAutomatico debito : colecaoDebitosAutomaticos){
				DebitoAutomaticoRelatoriosHelper debitoHelper = new DebitoAutomaticoRelatoriosHelper();
				debitoHelper.setDebitoAutomatico(debito);
				colecaoHelpers.add(debitoHelper);
			}

			relatorioBean.setColecaoDebitosAutomaticos(
					new JRBeanCollectionDataSource( colecaoHelpers));
		}

		if( !Util.isVazioOrNulo(colecaoImovelCadastroOcorrencia)){
			
			ArrayList<ImovelCadastroOcorrenciaRelatoriosHelper> colecaoHelpers = new ArrayList<ImovelCadastroOcorrenciaRelatoriosHelper>();
			
			for(ImovelCadastroOcorrencia imovelCadastroOcorrencia : colecaoImovelCadastroOcorrencia){
				ImovelCadastroOcorrenciaRelatoriosHelper imovelCOHelper = new ImovelCadastroOcorrenciaRelatoriosHelper();
				imovelCOHelper.setImovelCadastroOcorrencia(imovelCadastroOcorrencia);
				colecaoHelpers.add(imovelCOHelper);
			}

			relatorioBean.setColecaoOcorrenciaCadastro(
					new JRBeanCollectionDataSource( colecaoHelpers));
		}

		if( !Util.isVazioOrNulo(colecaoImovelEloAnormalidade)){

			ArrayList<ImovelEloAnormalidadeRelatoriosHelper> colecaoHelpers = new ArrayList<ImovelEloAnormalidadeRelatoriosHelper>();
			
			for(ImovelEloAnormalidade imovelEloAnormalidade : colecaoImovelEloAnormalidade){
				ImovelEloAnormalidadeRelatoriosHelper imovelEAHelper = new ImovelEloAnormalidadeRelatoriosHelper();
				imovelEAHelper.setImovelEloAnormalidade(imovelEloAnormalidade);
				colecaoHelpers.add(imovelEAHelper);
			}

			relatorioBean.setColecaoAnormalidadesLocalidadePolo(
					new JRBeanCollectionDataSource( colecaoHelpers));
		}

		if( !Util.isVazioOrNulo(colecaoImovelRamosAtividade)){

			ArrayList<ImovelRamoAtividadeRelatoriosHelper> colecaoHelpers = new ArrayList<ImovelRamoAtividadeRelatoriosHelper>();
			
			for(ImovelRamoAtividade imovelRamoAtividade : colecaoImovelRamosAtividade){
				ImovelRamoAtividadeRelatoriosHelper imovelRamoAtividadeHelper = new ImovelRamoAtividadeRelatoriosHelper();
				imovelRamoAtividadeHelper.setImovelRamoAtividade(imovelRamoAtividade);
				colecaoHelpers.add(imovelRamoAtividadeHelper);
			}

			relatorioBean.setColecaoRamoAtividade(
					new JRBeanCollectionDataSource( colecaoHelpers));
		}

		if( !Util.isVazioOrNulo(colecaoFaturamentosSituacaoHistorico)){
			
			relatorioBean.setColecaoSituacoesEspeciaisFaturamento(
					new JRBeanCollectionDataSource( criarColecaoSituacaoEspecialFaturamentoHelper(colecaoFaturamentosSituacaoHistorico) ));
		}

		if( !Util.isVazioOrNulo(colecaoCobrancasSituacaoHistorico)){

			relatorioBean.setColecaoSituacoesEspeciaisCobranca(
					new JRBeanCollectionDataSource( criarColecaoSituacaoEspecialCobrancaHelper(colecaoCobrancasSituacaoHistorico)));
		}

		if (!Util.isVazioOrNulo(colecaoSituacoesCobranca)){
			
			relatorioBean.setColecaoSituacoesCobranca(new JRBeanCollectionDataSource(colecaoSituacoesCobranca));
		}
		

		return relatorioBean;
	}

	/**
	 * Esse m�todo cria e retorna uma cole��o de SituacaoEspecialCobrancaHelper
	 * com os dados a serem exibidos no relatorio preenchidos a partir da
	 * cole��o recebida como par�metro.
	 *
	 *
	 *@since 23/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<SituacaoEspecialCobrancaHelper> criarColecaoSituacaoEspecialCobrancaHelper(
			Collection<CobrancaSituacaoHistorico> colecaoCobrancasSituacaoHistorico) {
		
		ArrayList<SituacaoEspecialCobrancaHelper> colecaoHelpers = new ArrayList<SituacaoEspecialCobrancaHelper>();

		for(CobrancaSituacaoHistorico situacaoEspecial : colecaoCobrancasSituacaoHistorico){
			SituacaoEspecialCobrancaHelper situacaoEspecialHelper = new SituacaoEspecialCobrancaHelper();
			
			if(situacaoEspecial.getCobrancaSituacaoTipo()!=null){
				situacaoEspecialHelper.
					setTipoSituacaoEspecialCobranca(situacaoEspecial.getCobrancaSituacaoTipo().getDescricao());
			}
			if(situacaoEspecial.getCobrancaSituacaoMotivo()!=null){
				situacaoEspecialHelper.
					setMotivoSituacaoEspecialCobranca(situacaoEspecial.getCobrancaSituacaoMotivo().getDescricao());
			}
			if(situacaoEspecial.getUsuario()!=null){
				situacaoEspecialHelper.
					setNomeUsuario(situacaoEspecial.getUsuario().getNomeUsuario());
			}
			
			if(situacaoEspecial.getAnoMesCobrancaSituacaoInicio()!=null){
				situacaoEspecialHelper.setMesAnoReferenciaCobrancaInicial(
						Util.formatarMesAnoReferencia(situacaoEspecial.getAnoMesCobrancaSituacaoInicio()));				
			}
			if(situacaoEspecial.getAnoMesCobrancaSituacaoFim()!=null){
				situacaoEspecialHelper.setMesAnoReferenciaCobrancaFinal(
						Util.formatarMesAnoReferencia(situacaoEspecial.getAnoMesCobrancaSituacaoFim()));				
			}
			if(situacaoEspecial.getAnoMesCobrancaRetirada()!=null){
				situacaoEspecialHelper.setMesAnoCobrancaRetirada(
						Util.formatarMesAnoReferencia(situacaoEspecial.getAnoMesCobrancaRetirada()));				
			}
			
			colecaoHelpers.add(situacaoEspecialHelper);

		}
		return colecaoHelpers;
	}

	/**
	 * Esse m�todo cria e retorna uma cole��o de SituacaoEspecialFaturamentoHelper
	 * com os dados a serem exibidos no relatorio preenchidos a partir da
	 * cole��o recebida como par�metro.
	 *
	 *@since 23/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<SituacaoEspecialFaturamentoHelper> criarColecaoSituacaoEspecialFaturamentoHelper(
			Collection<FaturamentoSituacaoHistorico> colecaoFaturamentosSituacaoHistorico) {
		
		ArrayList<SituacaoEspecialFaturamentoHelper> colecaoHelpers = new ArrayList<SituacaoEspecialFaturamentoHelper>();

		for(FaturamentoSituacaoHistorico situacaoEspecial : colecaoFaturamentosSituacaoHistorico){
			SituacaoEspecialFaturamentoHelper situacaoEspecialHelper = new SituacaoEspecialFaturamentoHelper();
			
			if(situacaoEspecial.getFaturamentoSituacaoTipo()!=null){
				situacaoEspecialHelper.
					setTipoSituacaoEspecialFaturamento(situacaoEspecial.getFaturamentoSituacaoTipo().getDescricao());
			}
			if(situacaoEspecial.getFaturamentoSituacaoMotivo()!=null){
				situacaoEspecialHelper.
					setMotivoSituacaoEspecialFaturamento(situacaoEspecial.getFaturamentoSituacaoMotivo().getDescricao());
			}
			if(situacaoEspecial.getUsuario()!=null){
				situacaoEspecialHelper.
					setNomeUsuario(situacaoEspecial.getUsuario().getNomeUsuario());
			}
			
			if(situacaoEspecial.getAnoMesFaturamentoSituacaoInicio()!=null){
				situacaoEspecialHelper.setMesAnoReferenciaFaturamentoInicial(
					Util.formatarMesAnoReferencia(situacaoEspecial.getAnoMesFaturamentoSituacaoInicio()));
			}

			if(situacaoEspecial.getAnoMesFaturamentoSituacaoFim()!=null){
				situacaoEspecialHelper.setMesAnoReferenciaFaturamentoFinal(
						Util.formatarMesAnoReferencia(situacaoEspecial.getAnoMesFaturamentoSituacaoFim()));				
			}
			
			if(situacaoEspecial.getAnoMesFaturamentoRetirada()!=null){
				situacaoEspecialHelper.setMesAnoFaturamentoRetirada(
						Util.formatarMesAnoReferencia(situacaoEspecial.getAnoMesFaturamentoRetirada()));				
			}
			
			colecaoHelpers.add(situacaoEspecialHelper);

		}
		return colecaoHelpers;
	}
	
//	private ArrayList<ImovelCobrancaSituacaoHelper> criarColecaoSituacaoCobrancaHelper(
//			Collection<ImovelCobrancaSituacaoHelper> colecaoSituacoesCobranca) {
//		
//		ArrayList<ImovelCobrancaSituacaoHelper> colecaoHelpers = new ArrayList<ImovelCobrancaSituacaoHelper>();
//
//		for(ImovelCobrancaSituacaoHelper imovelCobrancaSituacaoHelper : colecaoSituacoesCobranca){
//			
//			if(imovelCobrancaSituacaoHelper.getAnoMesReferenciaInicio() != null &&
//					!imovelCobrancaSituacaoHelper.getAnoMesReferenciaInicio().equals(new Integer(0))&&
//					imovelCobrancaSituacaoHelper.getAnoMesReferenciaFinal() != null &&
//					!imovelCobrancaSituacaoHelper.getAnoMesReferenciaFinal().equals(new Integer(0))){
//				
//				imovelCobrancaSituacaoHelper.setAnoMesReferencia(
//						imovelCobrancaSituacaoHelper.getAnoMesReferenciaInicio() + " a "
//						+ imovelCobrancaSituacaoHelper.getAnoMesReferenciaFinal());
//				
//				imovelCobrancaSituacaoHelper.setDataImplantacaoCobrancaString(
//					Util.formatarData(imovelCobrancaSituacaoHelper.getDataImplantacaoCobranca()));
//				
//				imovelCobrancaSituacaoHelper.setDataRetiradaCobrancaString(
//						Util.formatarData(imovelCobrancaSituacaoHelper.getDataRetiradaCobranca()));
//				
//				imovelCobrancaSituacaoHelper.setIdClienteAlvoString(
//						imovelCobrancaSituacaoHelper.getIdClienteAlvo().toString());
//			}
//			
//			
//			colecaoHelpers.add(imovelCobrancaSituacaoHelper);
//
//		}
//		return colecaoHelpers;
//	}
	
	/**
	 * Esse m�todo cria os parametros do relatorio com base
	 * nos parametros passados para esse objeto.
	 *
	 *@since 23/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {

		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().
				pesquisarParametrosDoSistema().getImagemRelatorio());

		if( Util.verificarNaoVazio(consultarImovelForm.getIdImovelDadosComplementares()) ){
			parametros.put("idImovelFiltro",consultarImovelForm.getIdImovelDadosComplementares());
		}

		parametros.put("iconeFoto",getParametro("caminhoIconeFoto"));
				
		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosComplementaresImovel", this);
	}

}