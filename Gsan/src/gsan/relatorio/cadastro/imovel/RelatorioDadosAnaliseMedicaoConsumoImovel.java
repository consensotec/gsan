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
package gsan.relatorio.cadastro.imovel;

import gsan.fachada.Fachada;
import gsan.gui.cadastro.imovel.ConsultarImovelActionForm;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioDadosAnaliseMedicaoConsumoImovel extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioDadosAnaliseMedicaoConsumoImovel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DADOS_ANALISE_MEDICAO_CONSUMO_IMOVEL);
	}
	
	public Object executar() throws TarefaException {
		
		List<RelatorioDadosAnaliseMedicaoConsumoImovelBean> relatorioBeans = new ArrayList<RelatorioDadosAnaliseMedicaoConsumoImovelBean>();
		relatorioBeans.add( criarRelatorioBean() );
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DADOS_ANALISE_MEDICAO_CONSUMO_IMOVEL, parametros,ds, tipoFormatoRelatorio);
	}

	/**
	 * Esse m�todo cria o RelatorioBean atrav�s dos parametros
	 * enviado a este objeto.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioDadosAnaliseMedicaoConsumoImovelBean criarRelatorioBean() {
		
		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");
		
		RelatorioDadosAnaliseMedicaoConsumoImovelBean relatorioBean = new RelatorioDadosAnaliseMedicaoConsumoImovelBean();
		
		relatorioBean.setInscricaoImovel(consultarImovelForm.getMatriculaImovelAnaliseMedicaoConsumo());
		relatorioBean.setMatriculaImovel(consultarImovelForm.getIdImovelAnaliseMedicaoConsumo());
		relatorioBean.setEnderecoImovel(consultarImovelForm.getEnderecoAnaliseMedicaoConsumo());
		relatorioBean.setSituacaoAguaImovel(consultarImovelForm.getSituacaoAguaAnaliseMedicaoConsumo());
		relatorioBean.setSituacaoEsgotoImovel(consultarImovelForm.getSituacaoEsgotoAnaliseMedicaoConsumo());

		relatorioBean.setAnoFabricacaoDadosHidrometroLigacaoAgua(consultarImovelForm.getAnoFabricacao());
		relatorioBean.setAnoFabricacaoDadosHidrometroPoco(consultarImovelForm.getAnoFabricacaoPoco());
		relatorioBean.setCapacidadeDadosHidrometroLigacaoAgua(consultarImovelForm.getCapacidadeHidrometro());
		relatorioBean.setCapacidadeDadosHidrometroPoco(consultarImovelForm.getCapacidadeHidrometroPoco());
		relatorioBean.setCondicaoEsgotamento(consultarImovelForm.getCondicaoEsgotamento());
		relatorioBean.setConsumoMinimoDadosLigacaoAgua(consultarImovelForm.getNumeroConsumominimoAgua());
		relatorioBean.setConsumoMinimoDadosLigacaoEsgoto(consultarImovelForm.getNumeroConsumominimoEsgoto());
		relatorioBean.setDataCorte(consultarImovelForm.getDataCorteAgua());
		relatorioBean.setDataInstalacaoDadosHidrometroLigacaoAgua(consultarImovelForm.getInstalacaoHidrometro());
		relatorioBean.setDataInstalacaoDadosHidrometroPoco(consultarImovelForm.getInstalacaoHidrometroPoco());
		relatorioBean.setDataLigacaoDadosLigacaoAgua(consultarImovelForm.getDataLigacaoAgua());
		relatorioBean.setDataLigacaoDadosLigacaoEsgoto(consultarImovelForm.getDataLigacaoEsgoto());

		relatorioBean.setDataReestabelecimento(consultarImovelForm.getDataRestabelecimentoAgua());
		relatorioBean.setDataReligacao(consultarImovelForm.getDataReligacaoAgua());
		relatorioBean.setDataSupressao(consultarImovelForm.getDataSupressaoAgua());
		relatorioBean.setDestinoAguasPluviais(consultarImovelForm.getDestinoAguasPluviais());
		relatorioBean.setDestinoDejetos(consultarImovelForm.getDestinoDejetos());
		relatorioBean.setDiametroDadosHidrometroLigacaoAgua(consultarImovelForm.getDiametroHidrometro());
		relatorioBean.setDiametroDadosHidrometroPoco(consultarImovelForm.getDiametroHidrometroPoco());
		relatorioBean.setDiametroDadosLigacaoAgua(consultarImovelForm.getDescricaoLigacaoAguaDiametro());
		relatorioBean.setDiametroDadosLigacaoEsgoto(consultarImovelForm.getDescricaoLigacaoEsgotoDiametro());
		relatorioBean.setDiaVencimento(consultarImovelForm.getDiaVencimento());

		relatorioBean.setEmpresaLeiturista(consultarImovelForm.getEmpresaLeitura());
		relatorioBean.setGrupoFaturamento(consultarImovelForm.getGrupoFaturamento());
		relatorioBean.setHidrometroDadosHidrometroLigacaoAgua(consultarImovelForm.getNumeroHidrometro());
		relatorioBean.setHidrometroDadosHidrometroPoco(consultarImovelForm.getNumeroHidrometroPoco());
		relatorioBean.setIndicadorCavaleteDadosHidrometroLigacaoAgua(consultarImovelForm.getIndicadorCavalete());
		relatorioBean.setIndicadorCavaleteDadosHidrometroPoco(consultarImovelForm.getIndicadorCavaletePoco());
		relatorioBean.setIndicadorPoco(consultarImovelForm.getDescricaoPocoTipo());
		relatorioBean.setLocalInstalacaoDadosHidrometroLigacaoAgua(consultarImovelForm.getLocalInstalacaoHidrometro());
		relatorioBean.setLocalInstalacaoDadosHidrometroPoco(consultarImovelForm.getLocalInstalacaoHidrometroPoco());

		relatorioBean.setMarcaDadosHidrometroLigacaoAgua(consultarImovelForm.getMarcaHidrometro());
		relatorioBean.setMarcaDadosHidrometroPoco(consultarImovelForm.getMarcaHidrometroPoco());
		relatorioBean.setMaterialDadosLigacaoAgua(consultarImovelForm.getDescricaoLigacaoAguaMaterial());
		relatorioBean.setMaterialDadosLigacaoEsgoto(consultarImovelForm.getDescricaoLigacaoEsgotoMaterial());
		relatorioBean.setMesAnoFaturamento(consultarImovelForm.getMesAnoFaturamentoCorrente());
		relatorioBean.setNumeroLacreInstalacaoDadosHidrometroLigacaoAgua(consultarImovelForm.getNumeroLacreInstalacao());
		relatorioBean.setNumeroLacreInstalacaoDadosHidrometroPoco(consultarImovelForm.getNumeroLacreInstalacaoPoco());
		relatorioBean.setPercentualColeta(consultarImovelForm.getPercentualAguaConsumidaColetada());
		relatorioBean.setPercentualEsgoto(consultarImovelForm.getPercentualEsgoto());
		relatorioBean.setPerfilLigacaoDadosLigacaoAgua(consultarImovelForm.getDescricaoligacaoAguaPerfil());
		
		relatorioBean.setPerfilLigacaoDadosLigacaoEsgoto(consultarImovelForm.getDescricaoligacaoEsgotoPerfil());
		relatorioBean.setProtecaoDadosHidrometroLigacaoAgua(consultarImovelForm.getProtecaoHidrometro());
		relatorioBean.setProtecaoDadosHidrometroPoco(consultarImovelForm.getProtecaoHidrometroPoco());
		relatorioBean.setRota(consultarImovelForm.getRota());
		relatorioBean.setSequencialRota(consultarImovelForm.getSequencialRota());
		relatorioBean.setSistemaCaixaInspecao(consultarImovelForm.getSistemaCaixaInspecao());
		relatorioBean.setTipoHidrometroDadosHidrometroPoco(consultarImovelForm.getTipoHidrometroPoco());
		relatorioBean.setTipoHidrometroDadosHidrometroLigacaoAgua(consultarImovelForm.getTipoHidrometro());
		relatorioBean.setTipoMedicaoDadosHidrometroLigacaoAgua(consultarImovelForm.getTipoMedicao());

		relatorioBean.setTipoMedicaoDadosHidrometroPoco(consultarImovelForm.getTipoMedicaoPoco());
		relatorioBean.setTipoRelojoariaDadosHidrometroLigacaoAgua(consultarImovelForm.getTipoRelojoaria());
		relatorioBean.setTipoRelojoariaDadosHidrometroPoco(consultarImovelForm.getTipoRelojoariaPoco());
		relatorioBean.setUsuarioResponsavelInstalacaoDadosHidrometroLigacaoAgua(consultarImovelForm.getUsuarioResponsavelInstalacao());
		relatorioBean.setUsuarioResponsavelInstalacaoDadosHidrometroPoco(consultarImovelForm.getUsuarioResponsavelInstalacaoPoco());
		
		relatorioBean.setDataCorteEsgoto(consultarImovelForm.getDataCorteEsgoto());
		relatorioBean.setDataReligacaoEsgoto(consultarImovelForm.getDataReligacaoEsgoto());
		relatorioBean.setDataSupressaoEsgoto(consultarImovelForm.getDataSupressaoEsgoto());
		relatorioBean.setDataRestabelecimentoEsgoto(consultarImovelForm.getDataRestabelecimentoEsgoto());
		relatorioBean.setMotivoCorteSupressaoEsgoto(consultarImovelForm.getMotivoCorteSupressaoEsgoto());

		return relatorioBean;
	}
	
	/**
	 * Esse m�todo cria os parametros do relatorio com base
	 * nos parametros passados para esse objeto.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {

		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().
				pesquisarParametrosDoSistema().getImagemRelatorio());

		if( Util.verificarNaoVazio(consultarImovelForm.getIdImovelDadosCadastrais()) ){
			parametros.put("idImovelFiltro",consultarImovelForm.getIdImovelDadosCadastrais());
		}
		
		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosAnaliseMedicaoConsumoImovel", this);
	}

}