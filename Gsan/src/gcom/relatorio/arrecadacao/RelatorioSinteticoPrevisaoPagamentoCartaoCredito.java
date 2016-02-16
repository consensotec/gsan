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
package gcom.relatorio.arrecadacao;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
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
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioSinteticoPrevisaoPagamentoCartaoCredito extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioSinteticoPrevisaoPagamentoCartaoCredito(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_SINTETICO_PREVISAO_PAGAMENTO_CARTAO_CREDITO);		
	}	

	@Override
	public Object executar() throws TarefaException {
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		Fachada fachada = Fachada.getInstancia();
		
		Date dataVencimentoInicial =  (Date) Util.converteStringParaDate((String) getParametro("dataVencimentoInicial"));
		Date dataVencimentoFinal = (Date) Util.converteStringParaDate((String) getParametro("dataVencimentoFinal"));
		String idCliente = (String) getParametro("idCliente");
		FiltroCliente filtro = new FiltroCliente();
		filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID,idCliente));
		Collection colecao = fachada.pesquisar(filtro , Cliente.class.getName());
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecao);
	    
		
		Usuario usuarioLogado = (Usuario) getParametro("usuarioLogado");
		// Se o relatório será gerado em PDF, WORD, EXCEL ou HTML
		Integer tipoRelatorio = Integer.parseInt((String) getParametro("escolhaTipoRelatorio"));
				
		byte[] retorno = null;
		
		List relatorioBeans = new ArrayList();
		
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Collection<RelatorioSinteticoPrevisaoPagamentoCartaoCreditoBean> colecaoRelatorioBean = null;
		colecaoRelatorioBean = (Collection<RelatorioSinteticoPrevisaoPagamentoCartaoCreditoBean>) fachada.pesquisarDadosRelatorioCartaoCreditoSintetico(dataVencimentoInicial, dataVencimentoFinal, idCliente);
		
		if(Util.isVazioOrNulo(colecaoRelatorioBean)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("dataVencimentoInicial", dataVencimentoInicial);
		parametros.put("codEmpresa",Util.formatarCnpj(sistemaParametro.getCnpjEmpresa())); 
		parametros.put("usuario", usuarioLogado.getNomeUsuario() );
		parametros.put("clienteArrec", cliente.getNome());
		parametros.put("dataVencimentoFinal", dataVencimentoFinal);
		
		relatorioBeans = (ArrayList) colecaoRelatorioBean;

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_SINTETICO_PREVISAO_PAGAMENTO_CARTAO_CREDITO,
				parametros, ds, tipoRelatorio);

		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_SINTETICO_PREVISAO_PAGAMENTO_CARTAO_CREDITO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		return retorno;
	
	}

	@Override
	public void agendarTarefaBatch() {
		
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	
	
}
