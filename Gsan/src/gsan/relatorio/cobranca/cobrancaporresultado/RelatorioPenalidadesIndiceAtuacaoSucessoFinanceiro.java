package gsan.relatorio.cobranca.cobrancaporresultado;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.bean.RelatorioBoletimMedicaoAcompanhamentoHelper;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiro extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiro(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_PENALIDADES_INDICE_ATUACAO_SUCESSO);
		}

		@Deprecated
		public RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiro() {
			super(null, "");
		}
		
		public Object executar() throws TarefaException {

			
			List<RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiroBean> colecaoPenalidades = 
					(ArrayList<RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiroBean> ) getParametro("colecaoPenalidades");
			
			String dataInicial = (String)getParametro("dataInicial");
			String dataFinal = (String)getParametro("dataFinal");
			String empresa = (String)getParametro("empresa");
			String contrato = (String)getParametro("contrato");

			// valor de retorno
			byte[] retorno = null;

			Fachada fachada = Fachada.getInstancia();

			// Parâmetros do relatório
			Map parametros = new HashMap();
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("dataInicial", dataInicial);
			parametros.put("dataFinal", dataFinal);
			parametros.put("empresa", empresa);
			parametros.put("contrato", contrato);
			
			RelatorioDataSource ds = new RelatorioDataSource(colecaoPenalidades);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PENALIDADES_INDICE_ATUACAO_SUCESSO,
			parametros, ds, TarefaRelatorio.TIPO_PDF);
			
			
			
			return retorno;
		}

		@Override
		public int calcularTotalRegistrosRelatorio() {
			return 2;
		}

		public void agendarTarefaBatch() {
		}
}
