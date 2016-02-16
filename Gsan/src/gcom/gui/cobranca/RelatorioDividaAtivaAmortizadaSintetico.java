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
package gcom.gui.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.DadosAmortizacaoDividaAtivaSinteticoBean;
import gcom.cobranca.bean.DadosAmortizacaoDividaAtivaSinteticoHelper;
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
import java.util.List;
import java.util.Map;

/**
 * [UC1585] - Emitir Relatório Sintetico Divida Ativa Amortizada 
 * 
 * @author Joao Pedro Medeiros
 * @date 04/01/2016
 */
public class RelatorioDividaAtivaAmortizadaSintetico extends TarefaRelatorio{
	
	private static final long serialVersionUID = 1L;

	public RelatorioDividaAtivaAmortizadaSintetico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DIVIDA_ATIVA_AMORTIZADA_SINTETICO);
	}
	
	@Override
	public Object executar() throws TarefaException {
		byte[]  retorno =  null;
		Collection<DadosAmortizacaoDividaAtivaSinteticoHelper> colecaoDadosAmortizacaoDividaAtivaSinteticoHelper = (Collection)  getParametro("colecaoDadosAmortizacaoDividaAtivaSinteticoHelper");
		List<DadosAmortizacaoDividaAtivaSinteticoBean> colecaoDadosAmortizacaoDividaAtivaSinteticoBean = new ArrayList<DadosAmortizacaoDividaAtivaSinteticoBean>();
		if(colecaoDadosAmortizacaoDividaAtivaSinteticoHelper != null && !colecaoDadosAmortizacaoDividaAtivaSinteticoHelper.isEmpty()){
			DadosAmortizacaoDividaAtivaSinteticoBean dadosAmortizacaoDividaAtivaSinteticoBean = null;
			for(DadosAmortizacaoDividaAtivaSinteticoHelper helper : colecaoDadosAmortizacaoDividaAtivaSinteticoHelper){
				dadosAmortizacaoDividaAtivaSinteticoBean = new DadosAmortizacaoDividaAtivaSinteticoBean();
				
				dadosAmortizacaoDividaAtivaSinteticoBean.setTipoAmortizacao(helper.getTipoAmortizacao());
				dadosAmortizacaoDividaAtivaSinteticoBean.setTotalTipoAmortizacao(helper.getTotalTipoAmortizacao());
				dadosAmortizacaoDividaAtivaSinteticoBean.setTotalValorDebitoAmortizado(helper.getTotalValorDebitoAmortizado());
				
				colecaoDadosAmortizacaoDividaAtivaSinteticoBean.add(dadosAmortizacaoDividaAtivaSinteticoBean);
				
			}
		}else{
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		// Parâmetros do relatório
		Map<String,String> parametro = new HashMap<String,String>();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParamntro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		int tipoFormatoRelatorio = (Integer) getParametro("tipoformatoRelatorio");			
		short indicadorIntra = (Short) getParametro("intra");
		
		String descricaoLocalidade = (String) getParametro("descricaoLocalidade");
		String periodoAmortizacao = (String) getParametro("periodoAmortizacao");
		String periodoInscricao = (String) getParametro("periodoInscricao");
		String descricaoImovel = (String) getParametro("descricaoImovel");
		
		parametro.put("imagem", sistemaParamntro.getImagemRelatorio());
		parametro.put("numeroRelatorio", "R1585");
		parametro.put("descricaoLocalidade",descricaoLocalidade);
		parametro.put("descricaoImovel",descricaoImovel);
		parametro.put("periodoAmortizacao",periodoAmortizacao);
		parametro.put("periodoInscricao", periodoInscricao);
		
		if(indicadorIntra == 1){
			parametro.put("intra", "SIM");
		}
		else
		if (indicadorIntra == 2){
			parametro.put("intra", "NÃO");
		}
		else
		if (indicadorIntra == 3){
			parametro.put("intra", "AMBOS");
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(colecaoDadosAmortizacaoDividaAtivaSinteticoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DIVIDA_ATIVA_AMORTIZADA_SINTETICO, parametro, ds, tipoFormatoRelatorio);
		
		return retorno;
		
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio(){
		
		int retorno = 1;
		
		return retorno;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDividaAtivaAmortizadaSintetico", this);
	}
	
}
