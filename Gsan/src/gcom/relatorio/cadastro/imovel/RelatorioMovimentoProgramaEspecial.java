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
 * [SB0005] Gerar Relat�rio do Movimento do Programa Especial
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
	 * M�todo auxiliar para criar os beans do relat�rio.
	 * 
	 * @author Hugo Azevedo
	 * @param colecaoIdsImovel 
	 * @date 21/08/2012
	 * @return retorno - Lista dos beans que ser�o usados para gerar o relat�rio
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
			
			//Inscri��o
			String inscricao = 
					Util.adicionarZerosEsquedaNumero(3,(String)objDados[0]) + "." + //Localidade
					Util.adicionarZerosEsquedaNumero(3,(String)objDados[1]) + "." + //Setor Comercial
					Util.adicionarZerosEsquedaNumero(3,(String)objDados[2]) + "." + //Quadra
					Util.adicionarZerosEsquedaNumero(4,(String)objDados[3]) + "." + //Lote
					Util.adicionarZerosEsquedaNumero(3,(String)objDados[4]);        //Sublote
			
			//Matr�cula do im�vel
			String matricula = (String)objDados[5];
			
			//Indicador de Atualiza��o
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