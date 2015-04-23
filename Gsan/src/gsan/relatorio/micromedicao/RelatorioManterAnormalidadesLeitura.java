


/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.relatorio.micromedicao;



import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.micromedicao.FiltroLeiturista;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Diego Maciel
 * @date 29/12/2009
 * @version 1.0
 */

public class RelatorioManterAnormalidadesLeitura extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterAnormalidadesLeitura(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_LEITURISTA);
	}
	
	@Deprecated
	public RelatorioManterAnormalidadesLeitura() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = (FiltroLeituraAnormalidade) 
				getParametro("filtroLeituraAnormalidade");
		LeituraAnormalidade leituraAnormalidadeParametro = (LeituraAnormalidade) getParametro("leituraAnormalidadeParametro");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<LeituraAnormalidade> colecaoAnormalidadeLeitura = fachada.pesquisar(
				filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterAnormalidadesLeituraBean relatorioBean = null;


		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAnormalidadeLeitura!= null && !colecaoAnormalidadeLeitura.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator<LeituraAnormalidade> anormalidadeLeitura = colecaoAnormalidadeLeitura.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (anormalidadeLeitura.hasNext()) {

				LeituraAnormalidade leituraAnormalidade =  (LeituraAnormalidade) anormalidadeLeitura.next();

				String indicadorRelativoHidrometro = "";
				if (leituraAnormalidade.getIndicadorRelativoHidrometro().intValue() == ConstantesSistema.SIM.intValue() )  {
					indicadorRelativoHidrometro = "Sim";
				} else if (leituraAnormalidade.getIndicadorRelativoHidrometro().intValue() == ConstantesSistema.NAO.intValue() ){
					indicadorRelativoHidrometro = "Não";
				} 
				
				String indicadorImovelSemHidrometro = "";
				if ( leituraAnormalidade.getIndicadorImovelSemHidrometro().intValue() == ConstantesSistema.SIM.intValue() )  {
					indicadorImovelSemHidrometro = "Sim";
				} else if ( leituraAnormalidade.getIndicadorImovelSemHidrometro().intValue() == ConstantesSistema.NAO.intValue() ){
					indicadorImovelSemHidrometro = "Não";
				} 
				
				String indicadorSistema = "";
				if ( leituraAnormalidade.getIndicadorSistema().intValue() == ConstantesSistema.SIM.intValue() )  {
					indicadorSistema = "Sim";
				} else if ( leituraAnormalidade.getIndicadorSistema().intValue() == ConstantesSistema.NAO.intValue() ){
					indicadorSistema = "Não";
				} 
				
				String indicadorPerdaTarifaSocial = "";
				if ( leituraAnormalidade.getIndicadorPerdaTarifaSocial().intValue() == ConstantesSistema.SIM.intValue() )  {
					indicadorPerdaTarifaSocial = "Sim";
				} else if ( leituraAnormalidade.getIndicadorPerdaTarifaSocial().intValue() == ConstantesSistema.NAO.intValue() ){
					indicadorPerdaTarifaSocial = "Não";
				} 
				
				
				
				relatorioBean = new RelatorioManterAnormalidadesLeituraBean(
						//Codigo
						"" + leituraAnormalidade.getId(),
						
						//Descricao leituraAnormalidade
						leituraAnormalidade.getDescricao(),
						
						//Indicador Relativo ao hidrometro
						indicadorRelativoHidrometro,
						
						//INdicador Imovel Sem Hidrometro
						indicadorImovelSemHidrometro,
						
						//Indicador de Sistema
						indicadorSistema,
						
						//Indicador Perda Tarifa Social
						indicadorPerdaTarifaSocial
						);
						
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		

		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_ANORMALIDADE_LEITURA, parametros,
				ds, tipoFormatoRelatorio);
		

		// retorna o relatório gerado
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
		AgendadorTarefas.agendarTarefa("RelatorioManterAnormalidadesLeitura", this);
	}

}
