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
package gcom.relatorio.arrecadacao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de Pagamento para Entidades
 * Beneficentes Anal�tico
 * 
 * @author Daniel Alves
 * @created 25 de Janeiro de 2010.
 */
public class RelatorioPagamentoEntidadesBeneficentesAnalitico extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioPagamentoEntidadesBeneficentesAnalitico(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO);
	}

	@Deprecated
	public RelatorioPagamentoEntidadesBeneficentesAnalitico() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		Fachada fachada = Fachada.getInstancia();
				
		String mesAnoInicial = (String) getParametro("mesAnoInicial");
		String mesAnoFinal = (String) getParametro("mesAnoFinal");		
		String idEntidadeBeneficente = (String) getParametro("idEntidadeBeneficente");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String idLocalidade = (String) getParametro("idLocalidade");		
		int opcaoTotalizacao = (Integer) getParametro("opcaoTotalizacao");
		
		int retorno = 0;		
		
		retorno = (Integer) fachada
  	         .pesquisarPagamentoEntidadesBeneficentesCount(mesAnoInicial, mesAnoFinal, idEntidadeBeneficente, 
  	        		                                 idGerenciaRegional, idUnidadeNegocio, idLocalidade, 
  	        		                                 opcaoTotalizacao, "analitico");
		 if(retorno == 0){
			 throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		 }
					 
		 return retorno; 
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPagamentoEntidadesBeneficentesAnalitico", this);
	}

	@Override
	public Object executar() throws TarefaException {
						
		Fachada fachada = Fachada.getInstancia();
		

		byte[] retorno = null;
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		String mesAnoInicial = (String) getParametro("mesAnoInicial");
		String mesAnoFinal = (String) getParametro("mesAnoFinal");		
		String idEntidadeBeneficente = (String) getParametro("idEntidadeBeneficente");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String idLocalidade = (String) getParametro("idLocalidade");		
		int opcaoTotalizacao = (Integer) getParametro("opcaoTotalizacao");
				
		Collection<RelatorioPagamentoEntidadesBeneficentesAnaliticoBean> beansRelatorio = 
			(Collection<RelatorioPagamentoEntidadesBeneficentesAnaliticoBean>) fachada
  	         .pesquisarPagamentoEntidadesBeneficentesAnalitico(mesAnoInicial, mesAnoFinal, idEntidadeBeneficente, 
  	        		                                 idGerenciaRegional, idUnidadeNegocio, idLocalidade, 
  	        		                                 opcaoTotalizacao);				
		
		if(beansRelatorio != null){			
			
			int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
			
			//Par�metros do relat�rio
			Map parametros = new HashMap();
			
			
			//Recupera o AnoMesFaturamento de Sistema Parametro
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("periodoInicial", mesAnoInicial);
			parametros.put("periodoFinal", mesAnoFinal);
			parametros.put("opcaoTotalizacao", opcaoTotalizacao);
			parametros.put("useCase", "R0978");
	
			RelatorioDataSource ds = new RelatorioDataSource((List) beansRelatorio);
	
			// exporta o relat�rio em pdf e retorna o array de bytes
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO,
					parametros, ds, tipoFormatoRelatorio);
		}else {
			throw new ActionServletException(
            "atencao.pesquisa.nenhumresultado");
		}
		// ------------------------------------
		//  Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------
		// retorna o relat�rio gerado
		
		return retorno;
		
	}

}
