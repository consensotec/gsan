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
package gsan.relatorio.faturamento;

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */

public class RelatorioAnaliticoFaturamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAnaliticoFaturamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALITICO_FATURAMENTO);
	}
	
	@Deprecated
	public RelatorioAnaliticoFaturamento() {
		super(null, "");
	}
	
	private Collection<RelatorioAnaliticoFaturamentoBean> inicializarBeanRelatorio(
			Collection colecaoAnaliticoRelatorio) {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<RelatorioAnaliticoFaturamentoBean> retorno = new ArrayList<RelatorioAnaliticoFaturamentoBean>();

		BigDecimal valorTotalGeral= BigDecimal.ZERO;
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorAgua = BigDecimal.ZERO;
		BigDecimal valorEsgoto = BigDecimal.ZERO;
		BigDecimal valorDebito = BigDecimal.ZERO;
		BigDecimal valorCredito = BigDecimal.ZERO;
		Iterator iter = colecaoAnaliticoRelatorio.iterator();
		
		while (iter.hasNext()) {
			
			RelatorioAnaliticoFaturamentoHelper rel = (RelatorioAnaliticoFaturamentoHelper) iter.next();

			valorAgua = BigDecimal.ZERO;
			valorEsgoto = BigDecimal.ZERO;
			valorDebito = BigDecimal.ZERO;
			valorCredito = BigDecimal.ZERO;
			
			valorAgua = rel.getValorAgua();
			valorEsgoto = rel.getValorEsgoto();
			valorDebito = rel.getDebitos();
			valorCredito = rel.getValorCreditos();
			
			valorTotal = BigDecimal.ZERO;
			valorTotal = valorTotal.add(valorAgua);
			valorTotal = valorTotal.add(valorEsgoto);
			valorTotal = valorTotal.add(valorDebito);
			valorTotal = valorTotal.subtract(valorCredito);
			
			valorTotalGeral = valorTotalGeral.add(valorTotal);
			
			
			String codigoBarra = fachada.obterRepresentacaoNumericaCodigoBarra(3,
						valorTotal,rel.getIdLocalidade(),rel.getIdImovel(),
						rel.getMesAnoFaturamento(),new Integer(rel.getDigitoVerificador()), null,
						null,null,null,null,null,null);
			
			
			RelatorioAnaliticoFaturamentoBean relatorioAnaliticoFaturamentoBean = 
					new RelatorioAnaliticoFaturamentoBean(rel.getIdImovel()+ "",
							rel.getConsumoAgua() != null ? new BigDecimal(rel.getConsumoAgua()) : BigDecimal.ZERO, 
							rel.getConsumoRateioAgua() != null ? new BigDecimal(rel.getConsumoRateioAgua()) : BigDecimal.ZERO, 
							rel.getValorAgua() != null ? rel.getValorAgua() : BigDecimal.ZERO, 
							rel.getConsumoEsgoto() != null ? new BigDecimal(rel.getConsumoEsgoto())  : BigDecimal.ZERO, 
							rel.getConsumoRateioEsgoto() != null ? new BigDecimal(rel.getConsumoRateioEsgoto()) : BigDecimal.ZERO, 
							rel.getValorEsgoto() != null ? rel.getValorEsgoto() : BigDecimal.ZERO,
							rel.getDebitos() != null ? rel.getDebitos() : BigDecimal.ZERO, 
							rel.getValorCreditos() != null ? rel.getValorCreditos() : BigDecimal.ZERO, 
							valorTotal != null ? valorTotal  : BigDecimal.ZERO,
							valorTotalGeral != null ? valorTotalGeral + "" : "0", 
							rel.getCodigoSetorComercial() != null 
							? Util.adicionarZerosEsquedaNumero(3,rel.getCodigoSetorComercial() + "") : "0",
							rel.getInscricao() != null ? rel.getInscricao() + "" : "0",
							rel.getIdLocalidade() != null 
							? Util.adicionarZerosEsquedaNumero(3,rel.getIdLocalidade() + "") : "0",
							Util.formatarCodigoBarra(codigoBarra), rel.getIdDescricaoLocalidade(), rel.getIdDescricaoUnidadeNegocio(),
							rel.getNomeCliente());
				
			retorno.add(relatorioAnaliticoFaturamentoBean);
			
		}

		return retorno;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String mesAno = (String) getParametro("mesAno");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
		String vencimento = (String) getParametro("vencimento");
		String indicadorLocalidadeInformatizada = (String) getParametro("indicadorLocalidadeInformatizada");
		Collection colecaoLocalidades = (Collection) getParametro("colecaoLocalidades");
		Collection colecaoSetor = (Collection) getParametro("colecaoSetor");
		Collection colecaoQuadras = (Collection) getParametro("colecaoQuadras");
		
//		Collection colecaoAnaliticoFaturamento = (Collection) getParametro("colecaoAnaliticoFaturamento");
				
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Collection colecaoAnaliticoFaturamento = fachada.pesquisarDadosRelatorioAnaliticoFaturamento( 
    			new Integer(Util.formatarMesAnoComBarraParaAnoMes(mesAno)), new Integer(idGrupoFaturamento), new Integer(indicadorLocalidadeInformatizada),
    			colecaoLocalidades,
    			colecaoSetor,
    			colecaoQuadras);
		 
//		 if(colecaoAnaliticoFaturamento.isEmpty()){
//			 throw new ActionServletException("atencao.relatorio.vazio");
//		 }
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",mesAno);
		parametros.put("vencimento", vencimento);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);
		parametros.put("tipoFormatoRelatorio", "R0593");
		
		Collection dadosRelatorio = colecaoAnaliticoFaturamento;

		Collection<RelatorioAnaliticoFaturamentoBean> colecaoBean = this
						.inicializarBeanRelatorio(dadosRelatorio);

//		if (colecaoBean == null || colecaoBean.isEmpty()) {
//			// N�o existem dados para a exibi��o do relat�rio.
//			throw new RelatorioVazioException("atencao.relatorio.vazio");
//		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ANALITICO_FATURAMENTO, 
				parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ANALITICO_FATURAMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
//		Integer mesAnoInteger = (Integer) getParametro("mesAnoInteger");
//		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
//		Collection idEsferaPoder = (Collection) getParametro("colecaoIdEsferaPoder");
		
		
		return 1;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnaliticoFaturamento", this);
	}
	
}
