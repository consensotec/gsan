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

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de imoveis com faturas em atraso
 * 
 * @author Bruno Barros
 * @created 10/12/2007
 */
public class RelatorioImoveisFaturasAtrasoDescritasLocalizacao extends TarefaRelatorio {
	
	public RelatorioImoveisFaturasAtrasoDescritasLocalizacao(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisFaturasAtrasoDescritasLocalizacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO);
	}

	@Deprecated
	public RelatorioImoveisFaturasAtrasoDescritasLocalizacao() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = 
			(FiltrarRelatorioImoveisFaturasAtrasoHelper) getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper");

		RelatorioDataSource ds = new RelatorioDataSource( executarConsultaECriarBeans(filtro) );

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO,
				criarParametros(), ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO,
					this.getIdFuncionalidadeIniciada());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}

		return retorno;
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 15/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametros() {
		
		Map<String,Object> parametros = new HashMap<String,Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("gerRegionalFiltro", getParametro("gerRegionalFiltro"));
		parametros.put("uniNegocioFiltro",getParametro("uniNegocioFiltro"));
		parametros.put("qtdFaturasFiltro", getParametro("qtdFaturasFiltro"));
		
		parametros.put("localidadeFiltro", getParametro("localidadeFiltro"));
		parametros.put("setorFiltro", getParametro("setorFiltro"));		
		parametros.put("intervaloQuadras", getParametro("quadraFiltro"));
		parametros.put("refFaturasFiltro", getParametro("refFaturasFiltro"));
		
		parametros.put("rotaFiltro", getParametro("rotaFiltro"));
		parametros.put("LigAguaFiltro", getParametro("LigAguaFiltro"));
		parametros.put("valorFaturasFiltro",getParametro("valorFaturasFiltro"));
		
		parametros.put("seqRotaFiltro", getParametro("seqRotaFiltro"));	
		parametros.put("situacaoCobrancaFiltro", getParametro("situacaoCobrancaFiltro"));
		parametros.put("hidrometro", getParametro("hidrometro"));
		
		parametros.put("esfPoderFiltro", getParametro("esfPoderFiltro"));
		parametros.put("grupoCobrancaFiltro", getParametro("grupoCobrancaFiltro"));
		parametros.put("categoriaFiltro", getParametro("categoriaFiltro"));
		
		
		
		return parametros;
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 15/09/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioImoveisFaturasAtrasoBean> executarConsultaECriarBeans(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) {
		
		List<RelatorioImoveisFaturasAtrasoBean> relatorioBeans = new ArrayList<RelatorioImoveisFaturasAtrasoBean>();
		RelatorioImoveisFaturasAtrasoBean relatorioImoveisFaturasAtrasoBean = null;

		Collection<RelatorioImoveisFaturasAtrasoHelper> colecao =  
			Fachada.getInstancia().pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(filtro);

		if ( !Util.isVazioOrNulo(colecao)) {

			Iterator<RelatorioImoveisFaturasAtrasoHelper> colecaoIterator = colecao.iterator();
			
			RelatorioImoveisFaturasAtrasoHelper helperAnterior = null;
			List<RelatorioImoveisFaturasAtrasoContasBean> colecaoDadosSubrelatorio = new ArrayList<RelatorioImoveisFaturasAtrasoContasBean>();
			
			BigDecimal totalImovel = BigDecimal.ZERO;
			int qtdFaturasAtraso = 0;
			
			while (colecaoIterator.hasNext()) {

				RelatorioImoveisFaturasAtrasoHelper helper = colecaoIterator.next();
				
				if (helperAnterior == null) {
					helperAnterior = helper;
				}
				
				if (helperAnterior.getMatriculaImovel().equals(helper.getMatriculaImovel())) {
					
					RelatorioImoveisFaturasAtrasoContasBean relatorioImoveisFaturasAtrasoContasBean = new RelatorioImoveisFaturasAtrasoContasBean(Util.formatarAnoMesParaMesAno(helper.getReferenciaFaturasAtrasoInicial()), helper.getValorFaturasAtrasoSemEncargos(), helper.getVencimento());
					colecaoDadosSubrelatorio.add(relatorioImoveisFaturasAtrasoContasBean);

					qtdFaturasAtraso++;
					totalImovel = totalImovel.add(helper.getValorFaturasAtrasoSemEncargos());
				} else {
					
					helperAnterior.setQuantidadeFaturasAtraso(qtdFaturasAtraso);
					relatorioImoveisFaturasAtrasoBean = 
						new RelatorioImoveisFaturasAtrasoBean(helperAnterior, colecaoDadosSubrelatorio);
					relatorioImoveisFaturasAtrasoBean.setValorTotalFaturaAtrasoSemEncargo(totalImovel);

					if(isQtdContasDentroIntervaloInformado(filtro, relatorioImoveisFaturasAtrasoBean)
							&& isValorFaturasDentroIntervaloInformado(filtro, relatorioImoveisFaturasAtrasoBean)){

						relatorioBeans.add(relatorioImoveisFaturasAtrasoBean);
					}

					colecaoDadosSubrelatorio.clear();
					
					RelatorioImoveisFaturasAtrasoContasBean relatorioImoveisFaturasAtrasoContasBean = new RelatorioImoveisFaturasAtrasoContasBean(Util.formatarAnoMesParaMesAno(helper.getReferenciaFaturasAtrasoInicial()), helper.getValorFaturasAtrasoSemEncargos(), helper.getVencimento());
					
					colecaoDadosSubrelatorio.add(relatorioImoveisFaturasAtrasoContasBean);
					
					totalImovel = BigDecimal.ZERO;
					
					totalImovel = totalImovel.add(helper.getValorFaturasAtrasoSemEncargos());
					qtdFaturasAtraso = 1;
				
				}
				
				if(!colecaoIterator.hasNext()){
					
					helperAnterior.setQuantidadeFaturasAtraso(qtdFaturasAtraso);
					relatorioImoveisFaturasAtrasoBean = 
						new RelatorioImoveisFaturasAtrasoBean(helperAnterior, colecaoDadosSubrelatorio);
					relatorioImoveisFaturasAtrasoBean.setValorTotalFaturaAtrasoSemEncargo(totalImovel);
					
					if(isQtdContasDentroIntervaloInformado(filtro, relatorioImoveisFaturasAtrasoBean)
							&& isValorFaturasDentroIntervaloInformado(filtro, relatorioImoveisFaturasAtrasoBean)){

						relatorioBeans.add(relatorioImoveisFaturasAtrasoBean);
					}
					
					colecaoDadosSubrelatorio.clear();
					
					RelatorioImoveisFaturasAtrasoContasBean relatorioImoveisFaturasAtrasoContasBean = new RelatorioImoveisFaturasAtrasoContasBean(Util.formatarAnoMesParaMesAno(helper.getReferenciaFaturasAtrasoInicial()), helper.getValorFaturasAtrasoSemEncargos(), helper.getVencimento());
					
					colecaoDadosSubrelatorio.add(relatorioImoveisFaturasAtrasoContasBean);
					
					totalImovel = BigDecimal.ZERO;
					totalImovel = totalImovel.add(helper.getValorFaturasAtrasoSemEncargos());
					qtdFaturasAtraso = 0;
				}
				
				helperAnterior = helper;
			}
		}
		
		return relatorioBeans;
	}

	/**
	 *[CRC] - 1672 - Melhorar performance dos relat�rios de Im�veis com Fatura em Atraso.<br/>
	 *
	 *Esse m�todo verifica se um RelatorioBean possui um numero de contas
	 *dentro do intervalo informado pelo usu�rio. Essa verifica��o tornou-se necess�ria
	 *neste momento da gera��o do relat�rio pois aqui sabemos exatamente quantas contas
	 *em atraso o im�vel tem. Foi preferido fazer isso via c�digo Java ao inv�s de sql/hql
	 *pois no caso desse relatorio a query n�o agrupa nenhum valor de conta,
	 *assim ao colocarmos isso num having count o mesmo n�o traria o resultado correto.
	 *
	 *@since 10/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isQtdContasDentroIntervaloInformado(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro,
			RelatorioImoveisFaturasAtrasoBean relatorioAux){
		
		if(filtro.getQuantidadeFaturasAtrasoInicial()==null || filtro.getQuantidadeFaturasAtrasoFinal() == null){
			return true;
		}
		
		return (relatorioAux.getArrayRelatorioImoveisFaturasAtrasoContasBean().size() >= filtro.getQuantidadeFaturasAtrasoInicial() 
					&& relatorioAux.getArrayRelatorioImoveisFaturasAtrasoContasBean().size() <= filtro.getQuantidadeFaturasAtrasoFinal());
	}

	/**
	 * Idem isQtdContasDentroIntervaloInformado(filtro,relatorioAux)
	 *
	 *@since 10/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isValorFaturasDentroIntervaloInformado(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro,
			RelatorioImoveisFaturasAtrasoBean relatorioAux){
		
		if(filtro.getValorFaturasAtrasoInicial() == null || filtro.getValorFaturasAtrasoFinal()== null){
			return true;
		}
		
		return (relatorioAux.getValorTotalFaturaAtrasoSemEncargo().doubleValue() >= filtro.getValorFaturasAtrasoInicial() 
					&& relatorioAux.getValorTotalFaturaAtrasoSemEncargo().doubleValue() <= filtro.getValorFaturasAtrasoFinal());
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
				(FiltrarRelatorioImoveisFaturasAtrasoHelper) 
					getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper"));
		
		if(retorno == 0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Relat�rio");
		}

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisFaturasAtraso", this);

	}

}