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
package gcom.relatorio.cadastro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterUnidadeOrganizacional extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterUnidadeOrganizacional(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_UNIDADE_ORGANIZACIONAL_MANTER);
	}
	
	@Deprecated
	public RelatorioManterUnidadeOrganizacional() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = (FiltroUnidadeOrganizacional) getParametro("filtroUnidadeOrganizacional");
		
		UnidadeOrganizacional unidadeOrganizacionalParametros = (UnidadeOrganizacional) getParametro("unidadeOrganizacionalParametros");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String pesquisouNivel = (String) getParametro ("pesquisouNivel");

		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("meioSolicitacao");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeCentralizadora");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterUnidadeOrganizacionalBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator unidadeOrganizacionalIterator = colecaoUnidadeOrganizacional.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (unidadeOrganizacionalIterator.hasNext()) {

				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) unidadeOrganizacionalIterator.next();

				String indicadorAberturaRa = "";
				
				if(unidadeOrganizacional.getIndicadorAberturaRa().equals(ConstantesSistema.SIM)){
					indicadorAberturaRa = "SIM";
				} else {
					indicadorAberturaRa = "N�O";
				}
				
				String indicadorTramite = "";
				
				if(unidadeOrganizacional.getIndicadorTramite()==(ConstantesSistema.SIM)){
					indicadorTramite = "SIM";
				} else {
					indicadorTramite = "N�O";
				}
				
				String indicadorUso ="";
				
				if(unidadeOrganizacional.getIndicadorUso() == (ConstantesSistema.SIM)){
					indicadorUso = "SIM";
				} else {
					indicadorUso = "N�O";
				}
				
				String empresa = null;
				
				if(unidadeOrganizacional.getEmpresa() != null ){
					empresa = unidadeOrganizacional.getEmpresa().getDescricao();
				} 
				
				String sigla = null;
				
				if(unidadeOrganizacional.getSigla() != null){
					sigla = unidadeOrganizacional.getSigla();
				}
				
				String indicadorEsgoto = "";
				
				if(unidadeOrganizacional.getIndicadorEsgoto() == (ConstantesSistema.SIM)){
					indicadorEsgoto = "SIM";
				} else {
					indicadorEsgoto = "N�O";
				}
				
				String gerenciaRegional = null;
				
				if(unidadeOrganizacional.getGerenciaRegional()!= null){
					gerenciaRegional = unidadeOrganizacional.getGerenciaRegional().getNome();
				}
				
				String localidade = null;

				if(unidadeOrganizacional.getLocalidade() != null ){
					localidade = unidadeOrganizacional.getLocalidade().getDescricao();
				}
				
				String unidadeSuperior = null;
				
				if(unidadeOrganizacional.getUnidadeSuperior() != null){
					unidadeSuperior = unidadeOrganizacional.getUnidadeSuperior().getDescricao();
				}
				
				String unidadeCentralizadora = null;
				
				if(unidadeOrganizacional.getUnidadeCentralizadora() != null ){
					unidadeCentralizadora = unidadeOrganizacional.getUnidadeCentralizadora().getDescricao();
				}
				
				String unidadeRepavimentadora = null;
				
				if(unidadeOrganizacional.getUnidadeRepavimentadora() != null) {
					unidadeRepavimentadora = unidadeOrganizacional.getUnidadeRepavimentadora().getDescricao();
				}
				
				String meioSolicitacao = null;
				
				if(unidadeOrganizacional.getMeioSolicitacao() != null) {
					meioSolicitacao = unidadeOrganizacional.getMeioSolicitacao().getDescricao();
				}
				
				
				relatorioBean = new RelatorioManterUnidadeOrganizacionalBean(
						
						// ID
						unidadeOrganizacional.getId().toString(), 
						
						// Descri��o
						unidadeOrganizacional.getDescricao(), 
						
						//Unidade tipo Nivel
						
						unidadeOrganizacional.getUnidadeTipo().getNivel().toString(),
						
						//Unidade tipo descricao
						unidadeOrganizacional.getUnidadeTipo().getDescricao(),
						
						// Indicador Abertura Registro Atendimento
						indicadorAberturaRa,
						
						//Indicador Tramite
						indicadorTramite,
						
						//Indicador Uso
						indicadorUso,
						
						//Empresa
						empresa,
						
						//Indicador Esgoto
						indicadorEsgoto,
						
						//Sigla
						sigla,
						
						//Gerencia Regional
						gerenciaRegional,
						
						//Localidade
						localidade,
						
						//Unidade Superior 
						unidadeSuperior,
						
						//Unidade Centralizadora
						unidadeCentralizadora,
						
						//Unidade Repavimentadora
						unidadeRepavimentadora,
						
						//Meio Solicitacao
						meioSolicitacao);
						
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id",
				unidadeOrganizacionalParametros.getId() == null ? "" : ""
						+ unidadeOrganizacionalParametros.getId());

		parametros.put("descricao", unidadeOrganizacionalParametros.getDescricao());	
		
		if (unidadeOrganizacionalParametros.getUnidadeTipo() != null
				&& unidadeOrganizacionalParametros.getUnidadeTipo()
						.getDescricao() != null) {
			parametros.put("descricaoUnidadeTipo", unidadeOrganizacionalParametros.getUnidadeTipo().getDescricao() );
		} else {
			parametros.put("descricaoUnidadeTipo", "");
		}
		
		
		if (unidadeOrganizacionalParametros.getUnidadeTipo() != null
				&& unidadeOrganizacionalParametros.getUnidadeTipo().getNivel() != null 
				&& pesquisouNivel.equalsIgnoreCase("sim")) {
			parametros.put("nivel", unidadeOrganizacionalParametros.getUnidadeTipo().getNivel() );
		} else {
			parametros.put("nivel", null);
		}
		
		
		String indicadorAberturaRa= "";

		if (unidadeOrganizacionalParametros.getIndicadorAberturaRa() != null
				&& !unidadeOrganizacionalParametros.getIndicadorAberturaRa().equals("")) {
			if (unidadeOrganizacionalParametros.getIndicadorAberturaRa().equals(new Short("1"))) {
				indicadorAberturaRa = "Sim";
			} else if (unidadeOrganizacionalParametros.getIndicadorAberturaRa().equals(
					new Short("2"))) {
				indicadorAberturaRa = "N�o";
			} else if (unidadeOrganizacionalParametros.getIndicadorAberturaRa().equals(
					new Short ("0"))){
				indicadorAberturaRa = "Todos";
			}
		}
		parametros.put("indicadorAberturaRa", indicadorAberturaRa);

		
		String indicadorTramite= "";

			if (unidadeOrganizacionalParametros.getIndicadorTramite()==(
					new Short("1"))) {
				indicadorTramite = "Sim";
			} else if (unidadeOrganizacionalParametros.getIndicadorTramite()==(
					new Short("2"))) {
				indicadorTramite = "N�o";
			} else if (unidadeOrganizacionalParametros.getIndicadorTramite() == (
					new Short("0"))) {
				indicadorTramite = "Todos";
			}
		parametros.put("indicadorTramite", indicadorTramite);
		
		String indicadorUso= "";
		if (unidadeOrganizacionalParametros.getIndicadorUso()==(new Short("1"))) {
			indicadorUso = "Ativo";
		} else if (unidadeOrganizacionalParametros.getIndicadorUso()==(
				new Short("2"))) {
			indicadorUso = "Inativo";
		} else if (unidadeOrganizacionalParametros.getIndicadorUso() == (
				new Short("0"))) {
			indicadorUso = "Todos";
		}
		parametros.put("indicadorUso", indicadorUso);
	
		String indicadorEsgoto= "";
		if (unidadeOrganizacionalParametros.getIndicadorEsgoto()==(new Short("1"))) {
			indicadorEsgoto = "Sim";
		} else if (unidadeOrganizacionalParametros.getIndicadorEsgoto()==(
				new Short("2"))) {
			indicadorEsgoto = "N�o";
		} else if (unidadeOrganizacionalParametros.getIndicadorEsgoto() == (
				new Short("0"))) {
			indicadorEsgoto = "Todos";
		}
		parametros.put("indicadorEsgoto", indicadorEsgoto);
	
	
		if(unidadeOrganizacionalParametros.getEmpresa() != null){
			parametros.put("empresa", unidadeOrganizacionalParametros.getEmpresa().getDescricao());
		}else{
			parametros.put("empresa", "");
		}
		
		
		if(unidadeOrganizacionalParametros.getSigla() != null){
			parametros.put("sigla", unidadeOrganizacionalParametros.getSigla());
		} else {
			parametros.put("sigla", null);
		}
		
		
		if(unidadeOrganizacionalParametros.getGerenciaRegional() != null){
			parametros.put("gerenciaRegional", unidadeOrganizacionalParametros.getGerenciaRegional());
		} else {
			parametros.put("gerenciaRegional", "");
		}
		
		if(unidadeOrganizacionalParametros.getLocalidade()!=null){
			parametros.put("localidade", unidadeOrganizacionalParametros.getLocalidade().getDescricao());
		} else {
			parametros.put("localidade", "");
		}
		
		if (unidadeOrganizacionalParametros.getUnidadeSuperior()!= null){
			parametros.put("unidadeSuperior", unidadeOrganizacionalParametros.getUnidadeSuperior().getDescricao());
		} else {
			parametros.put("unidadeSuperior", "");
		}
	
		if(unidadeOrganizacionalParametros.getUnidadeCentralizadora()!= null){
			parametros.put("unidadeCentralizadora", unidadeOrganizacionalParametros.getUnidadeCentralizadora().getDescricao());
		} else {
			parametros.put("unidadeCentralizadora", "");
		}
		
		if(unidadeOrganizacionalParametros.getUnidadeRepavimentadora()!= null){
			parametros.put("unidadeRepavimentadora", unidadeOrganizacionalParametros.getUnidadeRepavimentadora().getDescricao());
		} else {
			parametros.put("unidadeRepavimentadora", "");
		}
		
		if(unidadeOrganizacionalParametros.getMeioSolicitacao()!= null){
			parametros.put("meioSolicitacao", unidadeOrganizacionalParametros.getMeioSolicitacao().getDescricao());
		} else {
			parametros.put("meioSolicitacao", "");
		}
		
		
		
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_UNIDADE_ORGANIZACIONAL_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
//		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterUnidadeOrganizacional", this);
	}

}