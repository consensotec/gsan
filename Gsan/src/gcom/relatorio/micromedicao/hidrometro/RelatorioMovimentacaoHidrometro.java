package gcom.relatorio.micromedicao.hidrometro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.hidrometro.FiltroHidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentado;
import gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentado;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
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
 * R�mulo Aur�lio de Melo Souza Filho
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

/**
 * classe respons�vel por criar o relat�rio de movimentacao de hidrometro
 * 
 * @author R�mulo Aur�lio de Melo
 * @created 01/09/2008
 */
public class RelatorioMovimentacaoHidrometro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioMovimentacaoHidrometro(Usuario usuario) {
		super(
				usuario,
				ConstantesRelatorios.RELATORIO_CONSULTAR_MOVIMENTACAO_HIDROMETRO);
	}

	@Deprecated
	public RelatorioMovimentacaoHidrometro() {
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

		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = (FiltroHidrometroMovimentacao) getParametro("filtroHidrometroMovimentacao");
		HidrometroMovimentacao hidrometroMovimentacaoParametros = (HidrometroMovimentacao) getParametro("hidrometroMovimentacaoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioMovimentacaoHidrometroBean relatorioBean = null;

		//filtroHidrometroMovimentacao.setConsultaSemLimites(true);
		
		Collection colecaoHidrometroMovimentacao = null;
		
		//Caso a pesquisa tenha sido feita atraves dos valores Fixo, Faixa Inicial e Faixa Final
		if (hidrometroMovimentacaoParametros.getFixo() != null && 
	        	!hidrometroMovimentacaoParametros.getFixo().equals("")) {
			
			String faixaInicial = hidrometroMovimentacaoParametros.getFixo() + hidrometroMovimentacaoParametros.getFaixaInicial();
			String faixaFinal = hidrometroMovimentacaoParametros.getFixo() + hidrometroMovimentacaoParametros.getFaixaFinal();
			colecaoHidrometroMovimentacao = fachada.pesquisarNumeroHidrometroMovimentacaoPorFaixa(faixaInicial, faixaFinal);
			
		}else{
			filtroHidrometroMovimentacao
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroMotivoMovimentacao");
			filtroHidrometroMovimentacao
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemOrigem");
			filtroHidrometroMovimentacao
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemDestino");
			
			colecaoHidrometroMovimentacao = fachada.pesquisar(
					filtroHidrometroMovimentacao, HidrometroMovimentacao.class
					.getName());
			
		}
		
		Collection colecaoHidrometroMovimentado = null;
		
		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoHidrometroMovimentacao != null
				&& !colecaoHidrometroMovimentacao.isEmpty()) {
			
			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoHidrometroMovimentacaoIterator = colecaoHidrometroMovimentacao
			.iterator();
			
			// la�o para criar a cole��o de par�metros da analise
			while (colecaoHidrometroMovimentacaoIterator.hasNext()) {
				
				HidrometroMovimentacao hidrometroMovimentacao = (HidrometroMovimentacao) colecaoHidrometroMovimentacaoIterator
				.next();
				
				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio
				
				// Local Armagenagem Origem
				String localArmazenagemOrigem = "";
				
				if (hidrometroMovimentacao
						.getHidrometroLocalArmazenagemOrigem() != null) {
					localArmazenagemOrigem = hidrometroMovimentacao
					.getHidrometroLocalArmazenagemOrigem()
					.getDescricao();
				}
				
				// Local Armagenagem Destino
				String localArmazenagemDestino = "";
				
				if (hidrometroMovimentacao
						.getHidrometroLocalArmazenagemDestino() != null) {
					localArmazenagemDestino = hidrometroMovimentacao
					.getHidrometroLocalArmazenagemDestino()
					.getDescricao();
				}
				
				// Motivo Movimentacao
				String motivoMovimentacao = "";
				
				if (hidrometroMovimentacao.getHidrometroMotivoMovimentacao() != null) {
					motivoMovimentacao = hidrometroMovimentacao
					.getHidrometroMotivoMovimentacao().getDescricao();
				}
				
				// Data
				
				String data = "";
				
				if (hidrometroMovimentacao.getData() != null) {
					data = Util.formatarData(hidrometroMovimentacao.getData());
				}
				
				String hora = "";
				
				if (hidrometroMovimentacao.getHora() != null) {
					hora = hidrometroMovimentacao.getHora().toString();
				}
				
				FiltroHidrometroMovimentado filtroHidrometroMovimentado = new FiltroHidrometroMovimentado();
				
				filtroHidrometroMovimentado
				.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
				
				filtroHidrometroMovimentado
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentado.HIDROMETRO_MOVIMENTACAO_ID,
						hidrometroMovimentacao.getId()));
				
				//filtroHidrometroMovimentado.setConsultaSemLimites(true);
				
				colecaoHidrometroMovimentado = fachada.pesquisar(
						filtroHidrometroMovimentado,
						HidrometroMovimentado.class.getName());
				
				Integer quantidade = colecaoHidrometroMovimentado.size();
				
				hidrometroMovimentacao.setQuantidade(quantidade.toString());
				
				relatorioBean = new RelatorioMovimentacaoHidrometroBean(
						
						// Local Armagenagem Origem
						localArmazenagemOrigem,
						
						// Motivo Movimentacao
						motivoMovimentacao,
						
						// Data
						data,
						
						// Local Armagenagem Destino
						localArmazenagemDestino,
						
						// hora
						hora,
						
						// hidrometros
						hidrometroMovimentacao.getQuantidade());
				
				/** [MA2011061014]
				 * 	Autor: Paulo Diniz
				 *  Data: 12/07/2011
				 *  Inclusão do N�mero Inicial e Final do Hidr�metro e Fixos
				 * */
				//Calculando a FaixaInicial, FaixaFinal e Caracteres Fixos
				String faixaInicial = ((HidrometroMovimentado) ((List) colecaoHidrometroMovimentado).get(0)).getHidrometro().getNumero();
				String faixaFinal = ((HidrometroMovimentado) ((List) colecaoHidrometroMovimentado).get(quantidade-1)).getHidrometro().getNumero();
				String fixo = "";
				if(faixaInicial != null)
				{
					Integer tamanhoFaixaInicial = faixaInicial.length();
					if(tamanhoFaixaInicial > 4)
					{
						fixo = faixaInicial.substring(0,4);
						hidrometroMovimentacao.setFixo(fixo);
					}
					faixaInicial = faixaInicial.substring(4,tamanhoFaixaInicial-1);
				}
				if(faixaFinal != null)
				{
					Integer tamanhoFaixaFinal = faixaFinal.length();
					faixaFinal = faixaFinal.substring(4,tamanhoFaixaFinal-1);
				}
				
				String descricaoHidrometros = fixo+" - "+ faixaInicial+ " / " + faixaFinal;
				relatorioBean.setDescricaoHidrometros(descricaoHidrometros);
				
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (hidrometroMovimentacaoParametros
				.getHidrometroLocalArmazenagemOrigem() != null) {
			parametros.put("localArmazenagemOrigem",
					hidrometroMovimentacaoParametros
							.getHidrometroLocalArmazenagemOrigem()
							.getDescricao());
		}
		if (hidrometroMovimentacaoParametros
				.getHidrometroLocalArmazenagemDestino() != null) {
			parametros.put("localArmazenagemDestino",
					hidrometroMovimentacaoParametros
							.getHidrometroLocalArmazenagemDestino()
							.getDescricao());
		}
		if (hidrometroMovimentacaoParametros.getHidrometroMotivoMovimentacao() != null) {

			FiltroHidrometroMotivoMovimentacao filtroHidrometroMotivoMovimentacao = new FiltroHidrometroMotivoMovimentacao();

			filtroHidrometroMotivoMovimentacao
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroMotivoMovimentacao.ID,
							hidrometroMovimentacaoParametros
									.getHidrometroMotivoMovimentacao().getId()));

			Collection colecaoHidrometroMotivoMovimentacao = fachada.pesquisar(
					filtroHidrometroMotivoMovimentacao,
					HidrometroMotivoMovimentacao.class.getName());
			HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao = (HidrometroMotivoMovimentacao) colecaoHidrometroMotivoMovimentacao
					.iterator().next();

			parametros.put("motivoMovimentacao", hidrometroMotivoMovimentacao
					.getDescricao());
		}
		if (hidrometroMovimentacaoParametros.getUsuario() != null) {

			parametros.put("usuario", hidrometroMovimentacaoParametros
					.getUsuario().getNomeUsuario());
		}

		if (hidrometroMovimentacaoParametros.getHoraMovimentacaoInicial() != null) {

			parametros.put("horaMovimentacaoInicial",
					hidrometroMovimentacaoParametros
							.getHoraMovimentacaoInicial());

		} else {
			parametros.put("horaMovimentacaoInicial", "");
		}

		if (hidrometroMovimentacaoParametros.getHoraMovimentacaoFinal() != null) {

			parametros
					.put("horaMovimentacaoFinal",
							hidrometroMovimentacaoParametros
									.getHoraMovimentacaoFinal());

		} else {
			parametros.put("horaMovimentacaoFinal", "");
		}

		if (hidrometroMovimentacaoParametros.getDataMovimentacaoInicial() != null) {

			parametros.put("dataMovimentacaoInicial",
					hidrometroMovimentacaoParametros
							.getDataMovimentacaoInicial());

		} else {
			parametros.put("dataMovimentacaoInicial", "");
		}

		if (hidrometroMovimentacaoParametros.getDataMovimentacaoFinal() != null) {

			parametros
					.put("dataMovimentacaoFinal",
							hidrometroMovimentacaoParametros
									.getDataMovimentacaoFinal());

		} else {
			parametros.put("dataMovimentacaoFinal", "");
		}
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONSULTAR_MOVIMENTACAO_HIDROMETRO,
				parametros, ds, tipoFormatoRelatorio);

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada
//				.getInstancia()
//				.totalRegistrosPesquisa(
//						(FiltroHidrometroMovimentacao) getParametro("filtroHidrometroMovimentacao"),
//						HidrometroMovimentacao.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMovimentacaoHidrometro", this);
	}

}
