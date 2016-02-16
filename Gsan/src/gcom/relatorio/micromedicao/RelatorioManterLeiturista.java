/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.FiltroLeiturista;
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
 * Title: GCOM
 * Description: Sistema de Gest�o Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Arthur Carvalho
 * @date 29/12/2009
 * @version 1.0
 */

public class RelatorioManterLeiturista extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterLeiturista(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_LEITURISTA);
	}
	
	@Deprecated
	public RelatorioManterLeiturista() {
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

		FiltroLeiturista filtroLeiturista = (FiltroLeiturista) getParametro("filtroLeiturista");
		Leiturista leituristaParametros = (Leiturista) getParametro("leituristaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String idFuncionario = (String) getParametro("idFuncionario");
		String descricaoFuncionario = (String) getParametro("descricaoFuncionario");
		String descricaoEmpresa = (String) getParametro("descricaoEmpresa");
		String idCliente = (String) getParametro("idCliente");
		String descricaoCliente = (String) getParametro("descricaoCliente");
		
		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterLeituristaBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.EMPRESA);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		
		Collection colecaoLeiturista = fachada.pesquisar(filtroLeiturista,
				Leiturista.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoLeiturista != null && !colecaoLeiturista.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator leituristaIterator = colecaoLeiturista.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (leituristaIterator.hasNext()) {

				Leiturista leiturista = (Leiturista) leituristaIterator.next();

				String indicadorUso = "";
				if ( leiturista.getIndicadorUso().intValue() == ConstantesSistema.SIM.intValue() )  {
					indicadorUso = "ATIVO";
				} else if ( leiturista.getIndicadorUso().intValue() == ConstantesSistema.NAO.intValue() ){
					indicadorUso = "INATIVO";
				} else {
					indicadorUso = "TODOS";
				}
				
				
				
				relatorioBean = new RelatorioManterLeituristaBean(
						//Codigo
						"" + leiturista.getId(),
						
						//Descricao Leiturista
						leiturista.getFuncionario() !=null ? leiturista.getFuncionario().getNome(): leiturista.getCliente().getNome(),
						
						//Codigo DDD
						"" + leiturista.getCodigoDDD(),
						
						//Numero do Telefone
						"" + leiturista.getNumeroFone(),
						
						//Empresa
						leiturista.getEmpresa().getDescricao(),
						
						//IMEI
						"" + leiturista.getNumeroImei(),
						
						//Indicador de Uso
						indicadorUso);
						
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

		//Codigo Leiturista
		if ( leituristaParametros.getCodigoDDD() != null &&
				!leituristaParametros.getCodigoDDD().equals("") ) {
			parametros.put("ddd", leituristaParametros.getCodigoDDD());
		}
		
		//IMEI
		if ( leituristaParametros.getNumeroImei() != null &&
				!leituristaParametros.getNumeroImei().toString().equals("") ) {
			parametros.put("imei", leituristaParametros.getNumeroImei().toString());
		}
		
		//Numero do telefone
		if ( leituristaParametros.getNumeroFone() != null &&
				!leituristaParametros.getNumeroFone().toString().equals("") ) {
			parametros.put("fone", leituristaParametros.getNumeroFone());
		}
		
		//Indicador de Uso
		if ( leituristaParametros.getIndicadorUso() != null &&
				!leituristaParametros.getIndicadorUso().toString().equals("") ) {
				String indicadorDeUso = "";
				if ( leituristaParametros.getIndicadorUso().intValue() == ConstantesSistema.SIM.intValue() ) {
					indicadorDeUso = "ATIVO";
				} else if ( leituristaParametros.getIndicadorUso().intValue() == ConstantesSistema.NAO.intValue() ) {
					indicadorDeUso = "INATIVO";
				} else {
					indicadorDeUso = "TODOS";
				}
			parametros.put("indicadorUso", indicadorDeUso);
		}
		
		//ID Funcionario
		if ( idFuncionario != null &&
				!idFuncionario.equals("") ) {
			parametros.put("idFuncionario", idFuncionario);
		} else {
			parametros.put("idFuncionario", "");
		}
		
		//Descricao Funcionario
		if ( descricaoFuncionario != null &&
				!descricaoFuncionario.equals("") ) {
			parametros.put("descricaoFuncionario", descricaoFuncionario);
		} else {
			parametros.put("descricaoFuncionario", "");
		}
		
		//ID Cliente
		if ( idCliente != null &&
				!idCliente.equals("") ) {
			parametros.put("idCliente", idCliente);
		} else {
			parametros.put("idCliente", "");
		}
		
		//Descricao Cliente
		if ( descricaoCliente != null &&
				!descricaoCliente.equals("") ) {
			parametros.put("descricaoCliente", descricaoCliente);
		} else {
			parametros.put("descricaoCliente", descricaoCliente);
		}
		
		//descricao empresa
		if ( descricaoEmpresa != null &&
				!descricaoEmpresa.equals("") ) {
			parametros.put("descricaoEmpresa", descricaoEmpresa);
		} else {
			parametros.put("descricaoEmpresa", "");
		}
		

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_LEITURISTA, parametros,
				ds, tipoFormatoRelatorio);
		

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLeiturista) getParametro("filtroLeiturista"),
//				Leiturista.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterLeiturista", this);
	}

}