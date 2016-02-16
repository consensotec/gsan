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
package gcom.relatorio.arrecadacao;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC00744] Gerar Comparativo do Faturamento, Arrecada��o e Expurgo
 * 
 * @author S�vio Luiz
 * 
 * @date 09/12/2008
 */

public class RelatorioComparativoFatArrecExpurgado extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioComparativoFatArrecExpurgado(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO);
	}
	
	int quantidadeRegistros;

	@Deprecated
	public RelatorioComparativoFatArrecExpurgado() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		String gerenciaRegional = (String) getParametro("gerenciaRegional");
		String unidadeNegocio = (String) getParametro("unidadeNegocio");
		String mesAnoReferencia = (String) getParametro("mesAnoreferencia");
		Collection<Integer> idsPerfilImovel = (Collection<Integer>) getParametro("idsPerfilImovel");

		Integer anoMesreferencia = Util
				.formatarMesAnoComBarraParaAnoMes(mesAnoReferencia);

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioComparativoFatArrecExpurgoBean> colecaoComparativoExpurgoBean = fachada
				.pesquisarDadosComparativosFaturamentoArrecadacaoExpurgo(
						anoMesreferencia, gerenciaRegional, unidadeNegocio, idsPerfilImovel);

		// adiciona o bean a cole��o
		relatorioBeans.addAll(colecaoComparativoExpurgoBean);

		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("mesAnoReferencia", mesAnoReferencia);
		
		String indicadorControleGrandeCliente = fachada.obterValorParametro(ParametroSistema.INDICADOR_CONTROLE_GRANDE_CLIENTE);
		
		if (indicadorControleGrandeCliente != null && indicadorControleGrandeCliente.trim().equals("" + ConstantesSistema.SIM)) {
			String perfisSelecionados = "";
			
			if (idsPerfilImovel != null && !idsPerfilImovel.isEmpty()) {
				int i = 0;
				
				for (Integer idPerfilImovel : idsPerfilImovel) {
					FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
					filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, idPerfilImovel));
					
					Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
					
					ImovelPerfil imovelPerfil = colecaoImovelPerfil.iterator().next(); 
					
					if (i == 0) {
						perfisSelecionados += imovelPerfil.getDescricao();
					} else if (idsPerfilImovel.size() > 5 && i == 4) {
						perfisSelecionados += " E OUTROS";
						break;
					} else if (i <= 4) {
						perfisSelecionados += ", " + imovelPerfil.getDescricao();
					}
					
					i++;
				}
			} else {
				perfisSelecionados = "TODOS";
			}
			
			parametros.put("perfisSelecionados", perfisSelecionados);
		}
		
		if ((gerenciaRegional == null || gerenciaRegional.equals(""))
				&& (unidadeNegocio == null || unidadeNegocio.equals(""))) {
			parametros.put("opcaoTotalizacao", "ESTADO");
		}else{
			if(gerenciaRegional != null && !gerenciaRegional.equals("")){
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,gerenciaRegional));
				Collection colecaoGR = fachada.pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());
				GerenciaRegional grBase = (GerenciaRegional)Util.retonarObjetoDeColecao(colecaoGR);
				parametros.put("opcaoTotalizacao", "GER�NCIA REGIONAL - "+grBase.getNome().toUpperCase());
			}
			
			if(unidadeNegocio != null && !unidadeNegocio.equals("")){
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,unidadeNegocio));
				Collection colecaoUN = fachada.pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());
				UnidadeNegocio unBase = (UnidadeNegocio)Util.retonarObjetoDeColecao(colecaoUN);
				parametros.put("opcaoTotalizacao", "UNIDADE NEG�CIO - "+unBase.getNome().toUpperCase());
			}
		}
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "R0744");

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO, idFuncionalidadeIniciada);
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

		Integer anoMesreferencia = Util.formatarMesAnoComBarraParaAnoMes((String)getParametro("mesAnoreferencia"));
		Collection<Integer> idsPerfilImovel = (Collection<Integer>) getParametro("idsPerfilImovel");
		
		int retorno = Fachada
				.getInstancia()
				.pesquisarQuantidadeDadosComparativosFaturamentoArrecadacaoExpurgo(anoMesreferencia,(String) getParametro("gerenciaRegional"),
						(String) getParametro("unidadeNegocio"), idsPerfilImovel);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComparativoFatArrecExpurgado", this);

	}

}