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
package gcom.relatorio.cadastro.localidade;

import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
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
 * @author Arthur Carvalho
 * @date 01/07/09
 */

public class RelatorioManterUnidadeNegocio extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterUnidadeNegocio(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_UNIDADE_NEGOCIO_MANTER );
	}
	
	@Deprecated
	public RelatorioManterUnidadeNegocio() {
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

		FiltroUnidadeNegocio filtroUnidadeNegocio = (FiltroUnidadeNegocio) getParametro("filtroUnidadeNegocio");
		UnidadeNegocio unidadeNegocioParametros = (UnidadeNegocio) getParametro("unidadeNegocioParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.GERENCIA);
		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterUnidadeNegocioBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
				UnidadeNegocio.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator unidadeNegocioIterator = colecaoUnidadeNegocio.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (unidadeNegocioIterator.hasNext()) {

				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) unidadeNegocioIterator.next();

				//Gerencia Regional
				String gerenciaRegional = "";
			    if ( unidadeNegocio.getGerenciaRegional() != null && !unidadeNegocio.getGerenciaRegional().equals("") ) {
			    	gerenciaRegional = "" + unidadeNegocio.getGerenciaRegional().getNome();
			    }
			
				
				relatorioBean = new RelatorioManterUnidadeNegocioBean(
				
						//ID
						"" + unidadeNegocio.getId(),
						
						//Nome
						unidadeNegocio.getNome(),
						
						//Nome Abreviado
						unidadeNegocio.getNomeAbreviado(),
						
						//Gerencia Regional
						gerenciaRegional
						
				);
						
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

		//ID
		String codigo = "";
		if ( unidadeNegocioParametros.getId() != null && !unidadeNegocioParametros.getId().equals("") ) {
			codigo = "" + unidadeNegocioParametros.getId();
		}
		parametros.put("codigo", codigo ) ;

		//Nome
		String nome = "";
		if (unidadeNegocioParametros.getNome() != null && !unidadeNegocioParametros.getNome().equals("") ) {
			nome = unidadeNegocioParametros.getNome();
		}
		parametros.put( "nome", nome );
		
		//Nome Abreviado
		String nomeAbreviado = "";
		if ( unidadeNegocioParametros.getNomeAbreviado() != null && !unidadeNegocioParametros.getNomeAbreviado().equals("") ) {
			nomeAbreviado = unidadeNegocioParametros.getNomeAbreviado();
		}
		parametros.put( "nomeAbreviado", nomeAbreviado ) ;
		
		//Cnpj
		String cnpj = "";
		if ( unidadeNegocioParametros.getCnpj() != null && !unidadeNegocioParametros.getCnpj().equals("") ) {
			cnpj = unidadeNegocioParametros.getCnpjFormatado();
		}
		parametros.put( "cnpj",  cnpj );
		
		//Gerente da Unidade de Negocio
		String cliente = "";
		if ( unidadeNegocioParametros.getCliente() != null && !unidadeNegocioParametros.getCliente().equals("") ) {
			cliente = unidadeNegocioParametros.getCliente().getDescricao();
		}
		parametros.put("cliente" , cliente) ;
		
		//Gerencia Regional
		String gerenciaRegional = "";
		if ( unidadeNegocioParametros.getGerenciaRegional() != null && 
				!unidadeNegocioParametros.getGerenciaRegional().equals("") ) {
			gerenciaRegional = unidadeNegocioParametros.getGerenciaRegional().getNome();
		}		
		parametros.put("gerenciaRegional", gerenciaRegional);
		
		
		//Indicador de Uso
		String indicadorUso = "";

		if (unidadeNegocioParametros.getIndicadorUso() != null
				&& !unidadeNegocioParametros.getIndicadorUso().equals("")) {
			if (unidadeNegocioParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (unidadeNegocioParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}
		parametros.put("indicadorUso", indicadorUso);
	
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_UNIDADE_NEGOCIO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterUnidadeNegocio", this);
	}

}