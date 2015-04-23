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
* Anderson Cabral do Nascimento
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
package gsan.gui.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.bean.DadosParcelamentoDividaAtivaBean;
import gsan.cobranca.bean.DadosParcelamentoDividaAtivaHelper;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1586] - Emitir Relatório Dívida Ativa Parcelada.
 * 
 * 
 * @author Anderson Cabral
 * @created 19/02/2014
 * 
 */
public class RelatorioDividaAtivaParcelada extends TarefaRelatorio{
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioDividaAtivaParcelada(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DIVIDA_ATIVA_PARCELADA);
	}
	
	@Override
	public Object executar() throws TarefaException {
		byte[]  retorno =  null;
		try {
			Collection<DadosParcelamentoDividaAtivaHelper> colecaoDadosParcelamentoDividaAtivaHelper = (Collection)  getParametro("colecaoDadosParcelamentoDividaAtivaHelper");
			List<DadosParcelamentoDividaAtivaBean> colecaoDadosParcelamentoDividaAtivaBean = new ArrayList<DadosParcelamentoDividaAtivaBean>();
			if(colecaoDadosParcelamentoDividaAtivaHelper != null && !colecaoDadosParcelamentoDividaAtivaHelper.isEmpty()){
				DadosParcelamentoDividaAtivaBean dadosParcelamentoDividaAtivaBean = null;
				for(DadosParcelamentoDividaAtivaHelper helper : colecaoDadosParcelamentoDividaAtivaHelper){
					dadosParcelamentoDividaAtivaBean = new DadosParcelamentoDividaAtivaBean();
					
					String enderecoFormatado = Fachada.getInstancia().pesquisarEnderecoFormatado(helper.getIdImovel());
					String inscricaoImovel 	 = Fachada.getInstancia().pesquisarInscricaoImovel(helper.getIdImovel());
					
					dadosParcelamentoDividaAtivaBean.setIdLocalidade(helper.getIdLocalidade());
					dadosParcelamentoDividaAtivaBean.setNomeLocalidade(helper.getNomeLocalidade());
					dadosParcelamentoDividaAtivaBean.setAnoMesReferenciaArrecadacao(helper.getAnoMesReferenciaArrecadacao());
					dadosParcelamentoDividaAtivaBean.setDataInscricao(helper.getDataInscricao());
//					dadosParcelamentoDividaAtivaBean.setDataAmortizacao(helper.getDataAmortizacao());
					dadosParcelamentoDividaAtivaBean.setMatriculaImovel(Util.retornaMatriculaImovelFormatada(helper.getIdImovel()));
					dadosParcelamentoDividaAtivaBean.setInscricaoImovel(inscricaoImovel);
					dadosParcelamentoDividaAtivaBean.setEnderecoFormatado(enderecoFormatado);
					dadosParcelamentoDividaAtivaBean.setValorPrestacaoEmDia(helper.getValorPrestacaoEmDia());
					dadosParcelamentoDividaAtivaBean.setValorPrestacaoEmAtraso(helper.getValorPrestacaoEmAtraso());
					dadosParcelamentoDividaAtivaBean.setValorPrestacaoQuitada(helper.getValorPrestacaoQuitada());
					
					colecaoDadosParcelamentoDividaAtivaBean.add(dadosParcelamentoDividaAtivaBean);
					
				}
			}else{
				throw new RelatorioVazioException("atencao.relatorio.vazio");
			}
			
			// Parâmetros do relatório
			Map parametro = new HashMap();
			
			// adiciona os parâmetros do relatório
			// adiciona o laudo da análise
			SistemaParametro sistemaParamntro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			int tipoFormatoRelatorio = (Integer) getParametro("tipoformatoRelatorio");
			short indicadorIntra = (Short) getParametro("intra");
			
			parametro.put("imagem", sistemaParamntro.getImagemRelatorio());
			parametro.put("numeroRelatorio", "R1586");
			
			if(indicadorIntra == 1){
				parametro.put("intra", "CLIENTE INTRA");
			}else{
				parametro.put("intra", "");
			}
			
			// cria uma instância do dataSource do relatório
			RelatorioDataSource ds = new RelatorioDataSource(colecaoDadosParcelamentoDividaAtivaBean);
	
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DIVIDA_ATIVA_PARCELADA, parametro, ds, tipoFormatoRelatorio);
		
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio(){
		
		int retorno = 1;
		
		return retorno;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDividaAtivaParcelada", this);
	}
}
