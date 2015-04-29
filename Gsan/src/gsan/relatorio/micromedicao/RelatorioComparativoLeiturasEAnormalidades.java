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
package gsan.relatorio.micromedicao;

import gsan.batch.Relatorio;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.micromedicao.FiltroLeiturista;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.bean.AnormalidadeLeituraHelper;
import gsan.micromedicao.bean.ComparativoLeiturasEAnormalidadesRelatorioHelper;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio do comparativo de leituras e anormalidades
 * 
 * @author Rafael Corr�a     - Hugo Leonardo
 * @created 12/04/2007		 - 18/03/2010
 */
public class RelatorioComparativoLeiturasEAnormalidades extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioComparativoLeiturasEAnormalidades(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COMPARATIVOS_LEITURAS_E_ANORMALIDADES);
	}

	@Deprecated
	public RelatorioComparativoLeiturasEAnormalidades() {
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

		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		Integer anoMes = (Integer) getParametro("anoMes");
		
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer idSetorInicial = (Integer) getParametro("idSetorInicial");
		Integer idSetorFinal = (Integer) getParametro("idSetorFinal");
		
		Integer idGerencia = (Integer) getParametro("idGerencia");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		
		Integer idLeiturista = (Integer) getParametro("idLeiturista");
		
		Integer idRotaInicial = (Integer) getParametro("idRotaInicial");
		Integer idRotaFinal = (Integer) getParametro("idRotaFinal");
		
		Integer idPerfilImovel = (Integer) getParametro("idPerfilImovel");
		Integer numOcorrenciasConsecutivas = (Integer) getParametro("numOcorrenciasConsecutivas");
		Collection<Integer> colecaoAnormalidadesLeituras = (Collection) getParametro("colecaoAnormalidadesLeituras");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioComparativoLeiturasEAnormalidadesBean relatorioBean = null;
		
		Collection colecaoDadosRelatorioComparativoLeiturasEAnormalidades = fachada
				.pesquisarDadosRelatorioComparativoLeiturasEAnormalidades(
						idGrupoFaturamento, idEmpresa, anoMes, 						
						idLocalidadeInicial, idLocalidadeFinal,
						idSetorInicial, idSetorFinal,
						idGerencia, idUnidadeNegocio, idLeiturista, idRotaInicial, idRotaFinal, 
						idPerfilImovel, numOcorrenciasConsecutivas, colecaoAnormalidadesLeituras);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoDadosRelatorioComparativoLeiturasEAnormalidades != null && !colecaoDadosRelatorioComparativoLeiturasEAnormalidades.isEmpty()) {
			
			String empresa = "";
			String grupoFaturamento = "";
			
			if (idEmpresa != null) {
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(
								FiltroEmpresa.ID, idEmpresa));

				Collection colecaoPesquisa = fachada.pesquisar(
								filtroEmpresa, Empresa.class.getName());

				Empresa emp = (Empresa) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				empresa = emp.getId()+ "-" + emp.getDescricao();
			}
			
			if(idGrupoFaturamento != null){
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.ID, idGrupoFaturamento));

				Collection colecaoPesquisa = fachada.pesquisar(
						filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
			    grupoFaturamento = faturamentoGrupo.getDescricao();
			}

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoDadosRelatorioComparativoLeiturasEAnormalidadesIterator = colecaoDadosRelatorioComparativoLeiturasEAnormalidades.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoDadosRelatorioComparativoLeiturasEAnormalidadesIterator.hasNext()) {

				ComparativoLeiturasEAnormalidadesRelatorioHelper comparativoLeiturasEAnormalidadesRelatorioHelper = (ComparativoLeiturasEAnormalidadesRelatorioHelper) colecaoDadosRelatorioComparativoLeiturasEAnormalidadesIterator
						.next();
				
				relatorioBean = new RelatorioComparativoLeiturasEAnormalidadesBean(
								// Grupo de Faturamento
								grupoFaturamento,
								
								// Empresa
								empresa, 
								
								// Id do Setor Comercial
								comparativoLeiturasEAnormalidadesRelatorioHelper.getCodigoSetorComercial().toString(),
								
								// Id da Localidade
								comparativoLeiturasEAnormalidadesRelatorioHelper.getIdLocalidade().toString(),
								
								// C�digo do Setor Comercial
								comparativoLeiturasEAnormalidadesRelatorioHelper.getCodigoSetorComercial().toString(),
								
								// Registros Recebidos
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosRecebidos().toString(),
								
								// Registros c/ Leitura
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosComLeitura().toString(),
								
								// Registros c/ Anormalidade
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosComAnormalidade().toString(),
								
								// Registros c/ Leitura e Anormalidade
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosComLeituraEAnormalidade().toString(),
								
								// Anormalidade de Leitura
								null,
								
								// Quantidade de Anormalidades de Leitura
								null,
								
								//Quantidade de Leituras Enviadas
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosEnviados().toString(),
								
								//rota
								comparativoLeiturasEAnormalidadesRelatorioHelper.getCodigoRota().toString(),
								
								// Registros Recebidos Convencional
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosRecebidosConvencional().toString(),
								
								// Registros Recebidos Simultaneo
								comparativoLeiturasEAnormalidadesRelatorioHelper.getRegistrosRecebidosSimultaneo().toString()
				);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}
		
		Collection colecaoAnormalidadesLeiturasRelatorio = fachada
				.pesquisarAnormalidadesRelatorioComparativoLeiturasEAnormalidades(
						idGrupoFaturamento, idEmpresa, anoMes,
						idLocalidadeInicial, idLocalidadeFinal,
						idSetorInicial, idSetorFinal,
						idGerencia, idUnidadeNegocio, idLeiturista, idRotaInicial, idRotaFinal,
						idPerfilImovel, numOcorrenciasConsecutivas, colecaoAnormalidadesLeituras);
		
		Integer totalAnormalidades = 0;
		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoAnormalidadesLeiturasRelatorio != null
				&& !colecaoAnormalidadesLeiturasRelatorio
						.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoAnormalidadesLeiturasRelatorioIterator = colecaoAnormalidadesLeiturasRelatorio
					.iterator();

			
			// la�o para criar a cole��o de par�metros da analise
			while (colecaoAnormalidadesLeiturasRelatorioIterator
					.hasNext()) {

				AnormalidadeLeituraHelper anormalidadeLeituraHelper = (AnormalidadeLeituraHelper) colecaoAnormalidadesLeiturasRelatorioIterator
						.next();

				String anormalidadeLeitura = "";
				if (anormalidadeLeituraHelper
						.getIdAnormalidadeLeitura() != null) {
					anormalidadeLeitura = anormalidadeLeituraHelper
							.getIdAnormalidadeLeitura()
							+ " - "
							+ anormalidadeLeituraHelper
									.getDescricaoAnormalidadeLeitura();
				}
				
				String empresa = "";
				
				if (idEmpresa != null) {
					empresa = anormalidadeLeituraHelper.getIdEmpresa() + " - " + anormalidadeLeituraHelper.getNomeEmpresa();
				}
				
				String grupoFaturamento = null;
				
				if(idGrupoFaturamento != null){
					 grupoFaturamento = anormalidadeLeituraHelper
							.getIdGrupoFaturamento().toString();
				}
				

				relatorioBean = new RelatorioComparativoLeiturasEAnormalidadesBean(

						// Grupo de Faturamento
						grupoFaturamento,

						// Empresa
						empresa,

						// Id do Setor Comercial
						null,

						// Id da Localidade
						null,

						// C�digo do Setor Comercial
						null,

						// Registros Recebidos
						null,

						// Registros c/ Leitura
						null,

						// Registros c/ Anormalidade
						null,

						// Registros c/ Leitura e Anormalidade
						null,

						// Anormalidade de Leitura
						anormalidadeLeitura,

						// Quantidade de Anormalidades de Leitura
						anormalidadeLeituraHelper.getQuantidade().toString(),
						
						// Quantidade de Leituras Enviadas
						null,
						
						//Rota
						null,
						
						// Registros Recebidos Convencional
						null,
						
						// Registros Recebidos Simultaneo
						null
				);
				
				totalAnormalidades = totalAnormalidades + anormalidadeLeituraHelper.getQuantidade();
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
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(anoMes));
		
		parametros.put("tipoFormatoRelatorio", "R0939");
		
		parametros.put("totalAnormalidades", totalAnormalidades);
		
		//Localidade Inicial
		if ( idLocalidadeInicial != null && !idLocalidadeInicial.equals("") ) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro( new ParametroSimples( FiltroLocalidade.ID, idLocalidadeInicial));
			
			Collection colecaoLocalidadeInicial = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Localidade localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			
			parametros.put("localidadeInicialDescricao", localidadeInicial.getDescricao() );
		}
		
		//Localidade Final
		if ( idLocalidadeFinal != null && !idLocalidadeFinal.equals("") ) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro( new ParametroSimples( FiltroLocalidade.ID, idLocalidadeFinal));
			
			Collection colecaoLocalidadeFinal = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Localidade localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeFinal);
			
			parametros.put("localidadeFinalDescricao", localidadeFinal.getDescricao() );
		}
		
		//Setor Comercial Inicial
		if ( idSetorInicial != null && !idSetorInicial.equals("") ) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro( new ParametroSimples( FiltroSetorComercial.ID, idSetorInicial));
			
			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
			
			parametros.put("setorComercialInicialDescricao", setorComercial.getDescricao());
		}
		
		//Setor Comercial Final
		if ( idSetorFinal != null && !idSetorFinal.equals("") ) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro( new ParametroSimples( FiltroSetorComercial.ID, idSetorFinal));
			
			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
			
			parametros.put("setorComercialFinalDescricao", setorComercial.getDescricao());
		}
		
		//Unidade Negocio
		if ( idUnidadeNegocio != null && !idUnidadeNegocio.equals("") ) {
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro( new ParametroSimples( FiltroUnidadeNegocio.ID, idUnidadeNegocio));
			
			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidade);
			
			parametros.put("unidadeNegocioDescricao", unidadeNegocio.getNome());
		}
		
		//Gerencia Regional
		if ( idGerencia != null && !idGerencia.equals("") ) {
			FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
			filtroGerencia.adicionarParametro( new ParametroSimples( FiltroGerenciaRegional.ID, idGerencia));
			
			Collection colecaoGerencia = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerencia);
			
			parametros.put("gerenciaRegionalDescricao", gerenciaRegional.getNome());
		}
		
		// Leiturista
		if(idLeiturista != null && !idLeiturista.equals("") && !idLeiturista.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarParametro( new ParametroSimples( FiltroLeiturista.ID, idLeiturista));
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);		
			
			Collection colecaoLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
			Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);
			
			if ( leiturista.getFuncionario() != null ){
				parametros.put("leituristaDescicao", leiturista.getFuncionario().getNome() );
			} else {			
				parametros.put("leituristaDescicao", leiturista.getCliente().getNome() );
			}
			
		}
		
		// Rota Inicial
		if(idRotaInicial != null && !idRotaInicial.equals("")){
			parametros.put("rotaInicialDescricao", idRotaInicial.toString());
		}
		
		// Rota Final
		if(idRotaFinal != null && !idRotaFinal.equals("")){
			parametros.put("rotaFinalDescricao", idRotaFinal.toString());
		}
		
		// Perfil do Im�vel
		if(Util.verificarIdNaoVazio(String.valueOf(idPerfilImovel))){
			parametros.put("perfilImovel", fachada.pesquisarImovelPerfil(idPerfilImovel).getDescricao());
		}
		
		// Anormalidade de Leitura
		if(colecaoAnormalidadesLeituras != null && !colecaoAnormalidadesLeituras.isEmpty()){
			
			if(colecaoAnormalidadesLeituras.size() >= 1){
				
				String anormalidades = "";
				Iterator iter = colecaoAnormalidadesLeituras.iterator();
				int contador = 0;
				while (iter.hasNext()) {
					String idAnormladidade = (String) iter.next();
					
					if(!idAnormladidade.equals("-1")){
						
						FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade();
						filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID, new Integer(idAnormladidade)));
						
						anormalidades += ((LeituraAnormalidade) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, LeituraAnormalidade.class.getName()))).getDescricao();
						
						if (iter.hasNext()) {
							anormalidades += ", ";
						}
						
						contador++;
						
						if (contador == 3) { 
							anormalidades += "etc";
							break;
						}
						
					}
				}
				if(anormalidades != null && !anormalidades.equals("")){
					parametros.put("anormalidadesLeituras", anormalidades);	
				}
			}
		}
		
		if(Util.verificarIdNaoVazio(String.valueOf(numOcorrenciasConsecutivas))){
			parametros.put("numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if(relatorioBeans.size() > 0){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COMPARATIVOS_LEITURAS_E_ANORMALIDADES,
					parametros, ds, tipoFormatoRelatorio);
		}else{
			RelatorioComparativoLeiturasEAnormalidadesBean bean = new RelatorioComparativoLeiturasEAnormalidadesBean();
			relatorioBeans.add(bean);
			RelatorioDataSource dsVazio = new RelatorioDataSource(relatorioBeans);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, dsVazio, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.COMPARATIVO_LEITURAS_E_ANORMALIDADES,
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
		
		/*
		Fachada fachada = Fachada.getInstancia();
		
		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		Integer anoMes = (Integer) getParametro("anoMes");
		
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer idSetorInicial = (Integer) getParametro("idSetorInicial");
		Integer idSetorFinal = (Integer) getParametro("idSetorFinal");
		
		Integer idGerencia = (Integer) getParametro("idGerencia");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		
		Integer idLeiturista = (Integer) getParametro("idLeiturista");
		
		Integer idRotaInicial = (Integer) getParametro("idRotaInicial");
		Integer idRotaFinal = (Integer) getParametro("idRotaFinal");
		
		Integer idPerfilImovel = (Integer) getParametro("idPerfilImovel");
		Integer numOcorrenciasConsecutivas = (Integer) getParametro("numOcorrenciasConsecutivas");
		Collection<Integer> colecaoAnormalidadesLeituras = (Collection) getParametro("colecaoAnormalidadesLeituras"); 
		
		Integer colecaoDadosRelatorioComparativoLeiturasEAnormalidades = fachada
				.pesquisarDadosRelatorioComparativoLeiturasEAnormalidadesCount(
						idGrupoFaturamento, idEmpresa, anoMes, 						
						idLocalidadeInicial, idLocalidadeFinal,
						idSetorInicial, idSetorFinal,
						idGerencia, idUnidadeNegocio, idLeiturista, idRotaInicial, idRotaFinal,
						idPerfilImovel, numOcorrenciasConsecutivas, colecaoAnormalidadesLeituras);
		if (colecaoDadosRelatorioComparativoLeiturasEAnormalidades > 0) {
		
			int retorno = 10;
			
			return retorno;
		
		} else {
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		*/
		return 10;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComparativoLeiturasEAnormalidades", this);

	}

}