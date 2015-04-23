package gsan.relatorio.arrecadacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gsan.arrecadacao.ArrecadacaoForma;
import gsan.arrecadacao.Arrecadador;
import gsan.arrecadacao.aviso.AvisoBancario;
import gsan.batch.Relatorio;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.DocumentoTipo;
import gsan.fachada.Fachada;
import gsan.faturamento.debito.DebitoTipo;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1217] Gerar relatorio de transferencia de pagamento
 * @author Raimundo Martins
 * @date 24/08/2011
 */
public class RelatorioTransferenciaPagamento extends TarefaRelatorio{


	private static final long serialVersionUID = 1L;
	
	public RelatorioTransferenciaPagamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_TRANSFERENCIA_PAGAMENTO);	 
	}

	@Deprecated
	public RelatorioTransferenciaPagamento() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {		
		return 2;
	}

	@Override
	public Object executar() throws TarefaException {
		byte[] retorno = null;		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		Arrecadador arrecadador = (Arrecadador) this.getParametro("arrecadador");
		String periodoIni = (String)this.getParametro("periodoInicial");		
		String periodoFin = (String) this.getParametro("periodoFinal");		
		AvisoBancario avisoBancario = (AvisoBancario) this.getParametro("avisoBancario");
		ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) this.getParametro("arrecadacaoForma");
		DebitoTipo debitoTipo = (DebitoTipo) this.getParametro("tipoDebito");
		DocumentoTipo documentoTipo = (DocumentoTipo) this.getParametro("tipoDocumento");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		List<RelatorioTranferenciaPagamentoBean> colecaoBean = new ArrayList<RelatorioTranferenciaPagamentoBean>();
		List<RelatorioTranferenciaPagamentoBean> colecaoBeanTemp = new ArrayList<RelatorioTranferenciaPagamentoBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		//parametros.put("arrecadador",arrecadador.getCliente().getNome());
		parametros.put("periodoIni", periodoIni);
		parametros.put("periodoFin", periodoFin);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		colecaoBeanTemp = Fachada.getInstancia().pesquisarTransfereciasPagamento(arrecadador, periodoIni, 
				periodoFin, avisoBancario, arrecadacaoForma, debitoTipo, documentoTipo);		
			Iterator<RelatorioTranferenciaPagamentoBean> iter = colecaoBeanTemp.iterator();
			if(iter.hasNext()){
				while(iter.hasNext()){
					RelatorioTranferenciaPagamentoBean relatorioTranferenciaPagamentoBean = (RelatorioTranferenciaPagamentoBean) iter.next();
					Categoria cat = Fachada.getInstancia().obterPrincipalCategoriaImovel(
							Integer.parseInt(relatorioTranferenciaPagamentoBean.getMatricula()));
					Iterator<ClienteImovel> clientesImovel = Fachada.getInstancia().obterClienteImovelporRelacaoTipo(
							Integer.parseInt(relatorioTranferenciaPagamentoBean.getMatricula()), 
							ClienteRelacaoTipo.USUARIO.intValue()).iterator();
					ClienteImovel clienteImovel = (ClienteImovel) clientesImovel.next();
					relatorioTranferenciaPagamentoBean.setNomeCliente(clienteImovel.getCliente().getNome());
					relatorioTranferenciaPagamentoBean.setCategoria(cat.getDescricao());
					colecaoBean.add(relatorioTranferenciaPagamentoBean);
				}
				parametros.put("qtdTotalPagamento", colecaoBean.size());		
				RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);			
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_TRANSFERENCIA_PAGAMENTO,parametros, ds, tipoFormatoRelatorio);
		}
		else{
			colecaoBean.add(new RelatorioTranferenciaPagamentoBean());
			RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
		
		
		
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_TRANSFERENCIA_PAGAMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioTransferenciaPagamento", this);
		
	}

}
