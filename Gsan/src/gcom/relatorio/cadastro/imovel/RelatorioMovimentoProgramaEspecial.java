/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelClientesEspeciaisRelatorioHelper;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * [UC1367] Registrar Movimento do Programa Especial
 * [SB0005] Gerar Relatório do Movimento do Programa Especial
 * 
 * @author Hugo Azevedo
 * @param form 
 * @date 21/08/2012
 * 
 */	

public class RelatorioMovimentoProgramaEspecial extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioMovimentoProgramaEspecial(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MOVIMENTO_PROGRAMA_ESPECIAL);
	}

	@Deprecated
	public RelatorioMovimentoProgramaEspecial() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		Integer idMovimento = (Integer) getParametro("idMovimento");
		
		
		List<RelatorioMovimentoProgramaEspecialBean> relatorioBeans = criarBeans(idMovimento);
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String acao = (String) getParametro("acao");
		String cancelarItensFatura = (String) getParametro("cancelarItensFatura");
		String retirarContasProgEspecial = (String) getParametro("retirarContasProgEspecial");
		String sitEspecialCobranca = (String) getParametro("sitEspecialCobranca");
		
		HashMap parametros = new HashMap();
		
		parametros.put("imagem", Fachada.getInstancia().pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("acao", acao);
		parametros.put("retirarContasProgEspecial", retirarContasProgEspecial);
		parametros.put("cancelarItensFatura", cancelarItensFatura);
		parametros.put("sitEspecialCobranca", sitEspecialCobranca);
		

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MOVIMENTO_PROGRAMA_ESPECIAL,
				parametros , ds, tipoFormatoRelatorio);

		return retorno;
	}

	
	
	/**
	 * 
	 * Método auxiliar para criar os beans do relatório.
	 * 
	 * @author Hugo Azevedo
	 * @param colecaoIdsImovel 
	 * @date 21/08/2012
	 * @return retorno - Lista dos beans que serão usados para gerar o relatório
	 * 
	 */
	private List<RelatorioMovimentoProgramaEspecialBean> criarBeans(Integer idMovimento) {
		
		List<RelatorioMovimentoProgramaEspecialBean> retorno = new ArrayList<RelatorioMovimentoProgramaEspecialBean>();
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoDados = fachada.obterDadosRelatorioMovimentoProgramaEspecial(idMovimento);
		
		Iterator it = colecaoDados.iterator();
		while(it.hasNext()){
			
			Object[] objDados = (Object[])it.next();
			
			RelatorioMovimentoProgramaEspecialBean bean = new RelatorioMovimentoProgramaEspecialBean();
			
			//Inscrição
			String inscricao = 
					Util.adicionarZerosEsquedaNumero(3,(String)objDados[0]) + "." + //Localidade
					Util.adicionarZerosEsquedaNumero(3,(String)objDados[1]) + "." + //Setor Comercial
					Util.adicionarZerosEsquedaNumero(3,(String)objDados[2]) + "." + //Quadra
					Util.adicionarZerosEsquedaNumero(4,(String)objDados[3]) + "." + //Lote
					Util.adicionarZerosEsquedaNumero(3,(String)objDados[4]);        //Sublote
			
			//Matrícula do imóvel
			String matricula = (String)objDados[5];
			
			//Indicador de Atualização
			String indicadorAtualizacao = (String)objDados[6];
							
			bean.setInscricao(inscricao);
			bean.setMatricula(matricula);
			bean.setIndicadorAtualizacao(indicadorAtualizacao);
			
			retorno.add(bean);
		}
		
		return retorno;
		
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioClientesEspeciais", this);

	}

}