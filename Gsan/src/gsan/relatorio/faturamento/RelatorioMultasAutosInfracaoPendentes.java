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

import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class RelatorioMultasAutosInfracaoPendentes extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioMultasAutosInfracaoPendentes(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MULTAS_AUTOS_INFRACAO_PENTENTES);
	}


	public Object executar() throws TarefaException {

		
		//Parametros
		ArrayList beans = (ArrayList)getParametro("colecaoMultas");
		Funcionario funcionario = (Funcionario)getParametro("funcionario");
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)getParametro("grupo");
		String codigoRota = (String) getParametro("rota");
		String periodoAtuacaoInicial = (String)getParametro("periodoAtuacaoInicial");
		String periodoAtuacaoFinal = (String)getParametro("periodoAtuacaoFinal");
		
		
		//Variaveis
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Map parametros = new HashMap();

				
		//valor de retorno
		byte[] retorno = null;
		
		//Montar Cabe�alho
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		if(faturamentoGrupo != null)
			parametros.put("grupo", faturamentoGrupo.getDescricao());
		if(funcionario != null)
			parametros.put("funcionario", funcionario.getNome());
		if(codigoRota !=  null)
			parametros.put("rota", codigoRota.toString());
		if(periodoAtuacaoInicial != "")
			parametros.put("periodoAtuacaoInicial", periodoAtuacaoInicial);
		if(periodoAtuacaoFinal != "")
			parametros.put("periodoAtuacaoFinal", periodoAtuacaoFinal);
		
		//Montar relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		
		
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MULTAS_AUTOS_INFRACAO_PENTENTES,
		parametros, ds, TarefaRelatorio.TIPO_PDF);

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		
	}

}