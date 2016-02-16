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
package gcom.relatorio.cadastro;

import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterFonteAbastecimento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterFonteAbastecimento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FONTE_ABASTECIMENTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterFonteAbastecimento() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroFonteAbastecimento filtroFonteAbastecimento = (FiltroFonteAbastecimento) getParametro("filtroFonteAbastecimento");
		FonteAbastecimento fonteAbastecimentoParametros = (FonteAbastecimento) getParametro("fonteAbastecimentoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterFonteAbastecimentoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoFonteAbastecimento = fachada.pesquisar(filtroFonteAbastecimento,
				FonteAbastecimento.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoFonteAbastecimento != null && !colecaoFonteAbastecimento.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator fonteAbastecimentoIterator = colecaoFonteAbastecimento.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (fonteAbastecimentoIterator.hasNext()) {

				FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) fonteAbastecimentoIterator.next();

				
				String indicadorCalcularVolumeFixo = "";
				
				if(fonteAbastecimento.getIndicadorCalcularVolumeFixo().equals(ConstantesSistema.SIM)){
					indicadorCalcularVolumeFixo = "SIM";
				} else {
					indicadorCalcularVolumeFixo = "N�O";
				}
				
				String indicadorUso = "";
				
				if(fonteAbastecimento.getIndicadorUso().equals(ConstantesSistema.SIM)){
					indicadorUso = "ATIVO";
				} else {
					indicadorUso = "INATIVO";
				}
				
				relatorioBean = new RelatorioManterFonteAbastecimentoBean(
						// CODIGO
						fonteAbastecimento.getId().toString(), 
						
						// Descri��o
						fonteAbastecimento.getDescricao(), 
						
						//Descri��o Abreviada
						fonteAbastecimento.getDescricaoAbreviada(),
						
						//Indicador de Uso
						indicadorUso,
						
						//Indicador de Calcular Volume Fixo de Esgoto
						indicadorCalcularVolumeFixo);
						
						
						
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id",
				fonteAbastecimentoParametros.getId() == null ? "" : ""
						+ fonteAbastecimentoParametros.getId());
		
		
		if (fonteAbastecimentoParametros.getDescricao() != null){
			parametros.put("descricao", fonteAbastecimentoParametros.getDescricao());
		} else {
			parametros.put("descricao", "");
		}
		
		
		if (fonteAbastecimentoParametros.getDescricaoAbreviada() != null) {
			parametros.put("descricaoAbreviada", fonteAbastecimentoParametros.getDescricaoAbreviada());
		} else {
			parametros.put("descricaoAbreviada", "");
		}
		
		
		String indicadorUso = "";

		if (fonteAbastecimentoParametros.getIndicadorUso() != null
				&& !fonteAbastecimentoParametros.getIndicadorUso().equals("")) {
			if (fonteAbastecimentoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (fonteAbastecimentoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		
		String indicadorCalcularVolumeFixo = "";

		if (fonteAbastecimentoParametros.getIndicadorCalcularVolumeFixo() != null
				&& !fonteAbastecimentoParametros.getIndicadorCalcularVolumeFixo().equals("")) {
			if (fonteAbastecimentoParametros.getIndicadorCalcularVolumeFixo().equals(new Short("1"))) {
				indicadorCalcularVolumeFixo = "Sim";
			} else if (fonteAbastecimentoParametros.getIndicadorCalcularVolumeFixo().equals(
					new Short("2"))) {
				indicadorCalcularVolumeFixo = "N�o";
			}
		}

		parametros.put("indicadorCalcularVolumeFixo", indicadorCalcularVolumeFixo);
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_FONTE_ABASTECIMENTO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
//		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterFonteAbastecimento", this);
	}

}