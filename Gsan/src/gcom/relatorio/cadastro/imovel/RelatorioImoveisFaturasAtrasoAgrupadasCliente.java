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

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Respons�vel pela l�gica de Relat�rio de Im�veis com Fatura em Atraso
 * Agrupadas, com crit�tio de filtro por Cliente.
 *
 * @author Marlon Patrick
 * @since 02/09/2009
 */
public class RelatorioImoveisFaturasAtrasoAgrupadasCliente extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisFaturasAtrasoAgrupadasCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE);
	}

	public Object executar() throws TarefaException {

		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = 
			(FiltrarRelatorioImoveisFaturasAtrasoHelper) getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper");
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		RelatorioDataSource ds = new RelatorioDataSource( executarConsultaECriarColecaoRelatorioBeans(filtro) );

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE,
				parametros, ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE,
					this.getIdFuncionalidadeIniciada());
			
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}

		return retorno;
	}

	/**
	 * Executa a consulta no banco e com o resultado
	 * cria os objetos RelatorioBeans.
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioImoveisFaturasAtrasoBean> executarConsultaECriarColecaoRelatorioBeans(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) {

		Collection<RelatorioImoveisFaturasAtrasoHelper> colecao =  
			Fachada.getInstancia().pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(filtro);

		List<RelatorioImoveisFaturasAtrasoBean> relatorioBeans = new ArrayList<RelatorioImoveisFaturasAtrasoBean>();

		if ( !Util.isVazioOrNulo(colecao)) {
			for(RelatorioImoveisFaturasAtrasoHelper helper: colecao){
				relatorioBeans.add(new RelatorioImoveisFaturasAtrasoBean(helper));								
			}
		}

		return relatorioBeans;
	}

	/**
	 * M�todo configura os parametros que ser�o exibidos no relat�rio
	 * como sendo os filtros utilizados pelo usuario para ger�-lo.
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {
		
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();

		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("clienteSuperiorFiltro", getParametro("clienteSuperiorFiltro"));
		parametros.put("clienteFiltro", getParametro("clienteFiltro"));
		parametros.put("tipoRelacaoFiltro", getParametro("tipoRelacaoFiltro"));
		parametros.put("responsavelFiltro", getParametro("responsavelFiltro"));
		parametros.put("hidrometro", getParametro("hidrometro"));
		parametros.put("esfPoderFiltro", getParametro("esfPoderFiltro"));
		parametros.put("LigAguaFiltro", getParametro("LigAguaFiltro"));
		parametros.put("tiposCliente", getParametro("tiposCliente"));
		parametros.put("categoriaFiltro", getParametro("categoriaFiltro"));
		parametros.put("situacaoCobrancaFiltro", getParametro("situacaoCobrancaFiltro"));
		parametros.put("grupoCobrancaFiltro", getParametro("grupoCobrancaFiltro"));
		parametros.put("qtdFaturasFiltro", getParametro("qtdFaturasFiltro"));
		parametros.put("refFaturasFiltro", getParametro("refFaturasFiltro"));
		parametros.put("valorFaturasFiltro",getParametro("valorFaturasFiltro"));
		
		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
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