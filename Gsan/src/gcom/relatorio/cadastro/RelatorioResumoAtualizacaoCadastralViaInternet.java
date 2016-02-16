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
* Ivan S�rgio Virginio da Silva J�nior
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
package gcom.relatorio.cadastro;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  [UC1076] Gerar Relat�rio Atualiza��es Cadastrais Via Internet.
 * 
 * @author Daniel Alves
 * @date 28/09/2010
 * 
 * "Relatorio de Resumo"
 */

public class RelatorioResumoAtualizacaoCadastralViaInternet extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
		
	public RelatorioResumoAtualizacaoCadastralViaInternet(Usuario usuario) {		
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_ATUALIZACAO_CADASTRAL_VIA_INTERNET);		
	}
	
	@Deprecated
	public RelatorioResumoAtualizacaoCadastralViaInternet() {
		super(null, "");
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String filtroPeriodoInicial = (String) getParametro("filtroPeriodoInicial");
		String filtroPeriodoFinal = (String) getParametro("filtroPeriodoFinal");
		String filtroGerenciaRegional = (String) getParametro("filtroGerenciaRegional");
		String filtroUnidadeNegocio = (String) getParametro("filtroUnidadeNegocio");
		String filtroLocalidadeInicial = (String) getParametro("filtroLocalidadeInicial");
		String filtroLocalidadeFinal = (String) getParametro("filtroLocalidadeFinal");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		//Parametros
		String gerencia = "";
		String unidade = "";
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro = new GerarRelatorioAtualizacaoCadastralViaInternetHelper();
		
		filtro.setPeriodoReferenciaInicial(filtroPeriodoInicial);
		filtro.setPeriodoReferenciaFinal(filtroPeriodoFinal);
		
		if(filtroGerenciaRegional != null && !filtroGerenciaRegional.equals("-1")){
			filtro.setIdGerenciaRegional(Integer.parseInt(filtroGerenciaRegional));
			
			FiltroGerenciaRegional pesquisaGerenciaRegional = new FiltroGerenciaRegional();
			
			pesquisaGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, filtroGerenciaRegional));
			
			Collection<GerenciaRegional> colecaoGerencias = fachada.pesquisar(pesquisaGerenciaRegional, GerenciaRegional.class.getName());
			
			for (GerenciaRegional gerenciaRegional : colecaoGerencias) {
				gerencia = gerenciaRegional.getNome();
			}
			
		}
		
		if(filtroUnidadeNegocio != null && !filtroUnidadeNegocio.equals("-1")){
			filtro.setIdUnidadeNegocio(Integer.parseInt(filtroUnidadeNegocio));
			
			FiltroUnidadeNegocio pesquisaUnidadeNegocio = new FiltroUnidadeNegocio();
			
			pesquisaUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, filtroUnidadeNegocio));
			
			Collection<UnidadeNegocio> colecaoUnidade = fachada.pesquisar(pesquisaUnidadeNegocio, UnidadeNegocio.class.getName());
			
			for (UnidadeNegocio unidadeNegocio : colecaoUnidade) {
				unidade = unidadeNegocio.getNome();
			}
		}
		
		if(filtroLocalidadeInicial != null && !filtroLocalidadeInicial.equals("")
				&& filtroLocalidadeFinal != null && !filtroLocalidadeFinal.equals("")){
			
			filtro.setIdLocalidadeInicial(Integer.parseInt(filtroLocalidadeInicial));
			filtro.setIdLocalidadeFinal(Integer.parseInt(filtroLocalidadeFinal));
		}
			
		Collection colecaResumoAtualizacaoCadastralViaInternet = null;
		
		try {
			colecaResumoAtualizacaoCadastralViaInternet = fachada.pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(filtro);
		} catch (ControladorException e1) {
			//throw new ActionServletException("atencao.naocadastrado", null,	"Unidade de Neg�cio");		
		}	
		
		Iterator iterator = colecaResumoAtualizacaoCadastralViaInternet.iterator();
		
		RelatorioResumoAtualizacaoCadastralViaInternetBean relatorioBean = null;
		
		while(iterator.hasNext()){
			relatorioBean = new RelatorioResumoAtualizacaoCadastralViaInternetBean();
			
			Object[] obj = (Object[]) iterator.next();
			
			relatorioBean.setQuantidadeNome(obj[0]!=null?(Integer)obj[0]:0);				
			relatorioBean.setQuantidadeCPF(obj[1]!=null?(Integer)obj[1]:0);				
			relatorioBean.setQuantidadeCNPJ(obj[2]!=null?(Integer)obj[2]:0);				
			relatorioBean.setQuantidadeEmail(obj[3]!=null?(Integer)obj[3]:0);	
			relatorioBean.setQuantidadeClientesAlterados(obj[4]!=null?(Integer)obj[4]:0);
			
			relatorioBeans.add(relatorioBean);
		}
		
		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String, String> parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("filtroGerenciaRegional", gerencia);
		parametros.put("filtroUnidadeNegocio", unidade);
		parametros.put("filtroLocalidadeInicial", filtroLocalidadeInicial);
		parametros.put("filtroLocalidadeFinal", filtroLocalidadeFinal);
		parametros.put("filtroPeriodoInicial", filtroPeriodoInicial);
		parametros.put("filtroPeriodoFinal", filtroPeriodoFinal);
		parametros.put("tipoRelatorio", "R1076");
		

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) relatorioBeans);

		
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_RESUMO_ATUALIZACAO_CADASTRAL_VIA_INTERNET, parametros, ds,
					tipoFormatoRelatorio);
		

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESUMO_ATUALIZACAO_CADASTRAL_VIA_INTERNET,
						idFuncionalidadeIniciada);

		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoAtualizacaoCadastralViaInternet", this);
	}
	
}
