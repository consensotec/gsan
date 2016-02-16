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
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
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
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioAvisoAnormalidade extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAvisoAnormalidade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_AVISO_ANORMALIDADE);
	}

	@Deprecated
	public RelatorioAvisoAnormalidade() {
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

		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");
		Integer anoMesPesquisa = (Integer) getParametro("anoMesPesquisa");
		Collection colecaoImoveisGerarAviso = (Collection<Imovel>) getParametro("colecaoImoveisGerarAviso");
		FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper = (FiltrarAnaliseExcecoesLeiturasHelper) getParametro("filtrarAnaliseExcecoesLeiturasHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAvisoAnormalidadeBean relatorioBean = null;

		Collection<AvisoAnormalidadeRelatorioHelper> colecaoAvisosAnormalidadeHelper = null;
		
		if (colecaoImoveisGerarAviso != null && !colecaoImoveisGerarAviso.isEmpty()) {
			colecaoAvisosAnormalidadeHelper = fachada.pesquisarAvisoAnormalidadeRelatorio(colecaoImoveisGerarAviso, anoMesPesquisa);
		} else {
			colecaoAvisosAnormalidadeHelper = fachada.pesquisarAvisoAnormalidadeRelatorio(filtrarAnaliseExcecoesLeiturasHelper, anoMesPesquisa);
		}

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoAvisosAnormalidadeHelper != null && !colecaoAvisosAnormalidadeHelper.isEmpty()) {
			
			for (AvisoAnormalidadeRelatorioHelper avisoAnormalidadeRelatorioHelper : colecaoAvisosAnormalidadeHelper) {
				
				relatorioBean = new RelatorioAvisoAnormalidadeBean(
						// Nome do Cliente
						avisoAnormalidadeRelatorioHelper.getNomeCliente(),
						
						// Endere�o
						avisoAnormalidadeRelatorioHelper.getEndereco(),
						
						// Matr�cula do Im�vel
						Util.retornaMatriculaImovelFormatada(avisoAnormalidadeRelatorioHelper.getIdImovel()),
						
						// Inscri��o
						avisoAnormalidadeRelatorioHelper.getInscricao(),
						
						// C�digo da Rota
						Util.adicionarZerosEsquedaNumero(3, avisoAnormalidadeRelatorioHelper.getCodigoRota().toString()),
						
						// Sequencial da Rota
						avisoAnormalidadeRelatorioHelper.getSequencialRota() != null?"." + Util.adicionarZerosEsquedaNumero(4, avisoAnormalidadeRelatorioHelper.getSequencialRota().toString()): "",
						
						
//						Util.adicionarZerosEsquedaNumero(4, avisoAnormalidadeRelatorioHelper.getSequencialRota().toString()),
						
						// M�s/Ano 
						Util.formatarAnoMesParaMesAno(anoMesFaturamentoGrupo),
						
						// Anormalidade
						avisoAnormalidadeRelatorioHelper.getDescricaoAnormalidadeConsumo(),
						
						// Mun�cipio
						avisoAnormalidadeRelatorioHelper.getNomeMunicipio(),
						
						// Consumo Faturado
						avisoAnormalidadeRelatorioHelper.getConsumoFaturado(),
						
						// Consumo M�dio
						avisoAnormalidadeRelatorioHelper.getConsumoMedio(),
						
						// Varia��o de Consumo 
						avisoAnormalidadeRelatorioHelper.getVariacaoConsumo(),
						
						// Consumo Medido
						avisoAnormalidadeRelatorioHelper.getConsumoMedido()
				);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		FiltroBairro filtroBairro = new FiltroBairro();
		filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID, sistemaParametro.getBairro().getId()));
		
		Collection colecaoBairros = fachada.pesquisar(filtroBairro, Bairro.class.getName());
		
		Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairros);
		
		String endereco = formatarEndereco(sistemaParametro, bairro);
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());
		
		parametros.put("cnpjEmpresa", Util.formatarCnpj(sistemaParametro.getCnpjEmpresa()));
		
		parametros.put("enderecoEmpresa", endereco);
		
		parametros.put("telefoneEmpresa", sistemaParametro.getNumero0800Empresa());

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_AVISO_ANORMALIDADE,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_AVISO_ANORMALIDADE,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}
	
	private String formatarEndereco(SistemaParametro sistemaParametro, Bairro bairro) {
		String retorno = "";
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");
		
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, sistemaParametro.getLogradouro().getId()));
		
		Collection colecaoLogradouros = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
		
		Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(colecaoLogradouros);
		
		retorno = logradouro.getDescricaoFormatada();
		
		if (sistemaParametro.getNumeroImovel() != null) {
			retorno = retorno + ", " + sistemaParametro.getNumeroImovel(); 
		}
		
		retorno = retorno + " - " + bairro.getNome();
		
		FiltroCep filtroCep = new FiltroCep();
		filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CEPID, sistemaParametro.getCep().getCepId()));
		
		Collection colecaoCeps = fachada.pesquisar(filtroCep, Cep.class.getName());
		
		Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCeps);
		
		retorno = retorno + " - CEP: " + cep.getCepFormatado();
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<Imovel> colecaoImoveisGerarAviso = (Collection<Imovel>) getParametro("colecaoImoveisGerarAviso");
		
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) getParametro("filtroMedicaoHistoricoSql");
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Integer mesAnoPesquisa = (Integer) getParametro("anoMesPesquisa");
		String valorAguaEsgotoInicial = (String) getParametro("valorAguaEsgotoInicial");
		String valorAguaEsgotoFinal = (String) getParametro("valorAguaEsgotoFinal");
		
		if (colecaoImoveisGerarAviso != null
				&& !colecaoImoveisGerarAviso.isEmpty()) {
			retorno = colecaoImoveisGerarAviso.size();
		} else {
			retorno = fachada.filtrarExcecoesLeiturasConsumosCount(
					faturamentoGrupo, filtroMedicaoHistoricoSql,
					mesAnoPesquisa.toString(), valorAguaEsgotoInicial, valorAguaEsgotoFinal);
		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAvisoAnormalidade", this);

	}

}