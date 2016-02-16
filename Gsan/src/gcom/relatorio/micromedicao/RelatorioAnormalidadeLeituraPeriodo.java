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
package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioAnormalidadeLeituraPeriodo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAnormalidadeLeituraPeriodo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANORMALIDADE_LEITURA_PERIODO);
	}

	@Deprecated
	public RelatorioAnormalidadeLeituraPeriodo() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		List<RelatorioAnormalidadeLeituraPeriodoBean> relatorioBeans = executarConsultaRelatoriosBean();
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_PESQUISA_NENHUM_RESULTADO);
		}

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ANORMALIDADE_LEITURA_PERIODO,
				criarParametros() , ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ANORMALIDADE_LEITURA_PERIODO,this.getIdFuncionalidadeIniciada());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException(ConstantesInterfaceGSAN.ERRO_GSAN_ERRO_GRAVAR_RELATORIO_SISTEMA, e);
		}
		
		return retorno;
	}

	/**
	 * O m�todo cria os parametros necess�rios a gera��o do relatorio.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametros() {
		FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro = (FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio");

		Map<String,Object> parametros = new HashMap<String,Object>();
		
		if(!filtro.getAnormalidadeLeitura().toString().equals("-1")){
			FiltroLeituraAnormalidade filtroConsumoAnormalidade = new FiltroLeituraAnormalidade();				
			filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID,
					filtro.getAnormalidadeLeitura()));
			filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<LeituraAnormalidade> colecaoAnormalidadeleitura = Fachada.getInstancia().pesquisar(filtroConsumoAnormalidade, LeituraAnormalidade.class.getName());
	
			LeituraAnormalidade anormalidade = colecaoAnormalidadeleitura.iterator().next();
			
			parametros.put("filtroAnormalidade", anormalidade.getId() + " - " + anormalidade.getDescricao());

		}
		
		if(filtro.getAnormalidadeLeituraFaturada() != null && filtro.getAnormalidadeLeituraFaturada().length > 0){
			if(filtro.getAnormalidadeLeituraFaturada().length == 1){
				String id = filtro.getAnormalidadeLeituraFaturada()[0];
				
				FiltroLeituraAnormalidade filtroAno = new FiltroLeituraAnormalidade();
				filtroAno.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID, id));
				LeituraAnormalidade leituraAno = 
						(LeituraAnormalidade)Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroAno, LeituraAnormalidade.class.getName()));
				
				parametros.put("filtroAnormalidadeFaturada", leituraAno.getDescricao());
				
			}
			else{
				parametros.put("filtroAnormalidadeFaturada", "V�rios");	
			}
		}
		
		switch(filtro.getOrdenacao()){
			case 1:
				parametros.put("filtroOrdenacao", "ROTA");
				break;
			case 2:
				parametros.put("filtroOrdenacao", "INSCRI��O");
				break;
		}

		parametros.put("imagem", Fachada.getInstancia().pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("filtroPeriodoLeitura", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferenciaInicial()) + " - " + Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferenciaFinal()));

		return parametros;
	}

	/**
	 * Esse m�todo tem a l�gica para realizar a consulta referente ao relatorio
	 * e a partir do resultado obtido criar os beans.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioAnormalidadeLeituraPeriodoBean> executarConsultaRelatoriosBean() {

		FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro = (FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio");

		return (List<RelatorioAnormalidadeLeituraPeriodoBean>)Fachada.getInstancia().pesquisarRelatorioAnormalidadeLeituraPeriodo(filtro);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarTotalRegistrosRelatorioAnormalidadeLeituraPeriodo(
				(FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio"));	
		
		if(Util.isVazioOrNulo(colecaoDados)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_PESQUISA_NENHUM_RESULTADO);
		}
		
		return colecaoDados.size();
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnormalidadeLeituraPeriodo", this);

	}

}