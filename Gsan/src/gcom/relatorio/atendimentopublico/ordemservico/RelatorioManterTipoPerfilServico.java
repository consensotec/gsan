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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
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

public class RelatorioManterTipoPerfilServico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterTipoPerfilServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_TIPO_PERFIL_SERVICO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterTipoPerfilServico() {
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

		FiltroServicoPerfilTipo filtroServicoPerfilTipo = (FiltroServicoPerfilTipo) getParametro("filtroServicoPerfilTipo");
		ServicoPerfilTipo servicoPerfilTipoParametros = (ServicoPerfilTipo) getParametro("servicoPerfilTipoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterTipoPerfilServicoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

//		filtroServicoPerfilTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoPerfilTipo.ID_EQUIPES_COMPONENTES);
		filtroServicoPerfilTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoPerfilTipo.EQUIPAMENTOS_ESPECIAIS);
		
		Collection colecaoServicoPerfilTipo = fachada.pesquisar(filtroServicoPerfilTipo,
				ServicoPerfilTipo.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoServicoPerfilTipo != null && !colecaoServicoPerfilTipo.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator servicoPerfilTipoIterator = colecaoServicoPerfilTipo.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (servicoPerfilTipoIterator.hasNext()) {

				
				
				
				ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) servicoPerfilTipoIterator.next();

				String indicadorVeiculoProprio = "";
				
				String indVeiculoProprio = ("" + servicoPerfilTipo.getIndicadorVeiculoProprio());
				
				if(indVeiculoProprio.equals(ConstantesSistema.SIM)){
					indicadorVeiculoProprio = "SIM";
				} else {
					indicadorVeiculoProprio = "N�O";
				}
				
				String indicadorUso = "";
				
				String indUso = ("" + servicoPerfilTipo.getIndicadorUso());
				
				if(indUso .equals(ConstantesSistema.SIM)){
					indicadorUso  = "SIM";
				} else {
					indicadorUso = "N�O";
				}
				
				relatorioBean = new RelatorioManterTipoPerfilServicoBean(
						
						// ID
						servicoPerfilTipo.getId().toString(), 
						
						// Descri��o
						servicoPerfilTipo.getDescricao(), 
						
						//Abreviatura do Perfil de Servi�o
						servicoPerfilTipo.getDescricaoAbreviada(),
						
						//Quantidade Componentes Equipe
						servicoPerfilTipo.getComponentesEquipe().toString(),
						
						//Id equipamentos especiais
						servicoPerfilTipo.getEquipamentosEspeciais() == null ? "" : servicoPerfilTipo.getEquipamentosEspeciais().getId().toString(),
								
						//Equipamentos Especiais
						servicoPerfilTipo.getEquipamentosEspeciais() == null ? "" : servicoPerfilTipo.getEquipamentosEspeciais().getDescricao(),
						// Indicador Veiculo Proprio
						indicadorVeiculoProprio,
						
						indicadorUso);
						
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

		//Id Perfil Servico
		parametros.put("id",
				servicoPerfilTipoParametros.getId() == null ? "" : ""
						+ servicoPerfilTipoParametros.getId());
		
		//Descricao Perfil Servico
		parametros.put("descricao", servicoPerfilTipoParametros.getDescricao());
		
		//Descricao Abreviada Perfil Servico
		parametros.put("abrevPerfilServico", servicoPerfilTipoParametros.getDescricaoAbreviada());
		
		//Quantidade de Componentes
		//String qtdComponentes = "";
		if ( servicoPerfilTipoParametros.getComponentesEquipe() != null) {
			
				
				parametros.put("qtdComponentes", "" + servicoPerfilTipoParametros.getComponentesEquipe());
		} else {
				parametros.put("qtdComponentes", "");
		}
		
		
		
		//Indicador Ve�culo Pr�prio
		String indicadorVeiculoProprio= "";
		String indVeiculoProprio = ""+ servicoPerfilTipoParametros.getIndicadorVeiculoProprio();
		if ( indVeiculoProprio != null
				&& !indVeiculoProprio.equals("")) {
			if (indVeiculoProprio .equals(""+("1"))) {
				indicadorVeiculoProprio = "Sim";
			} else if (indVeiculoProprio .equals(""+("2"))) {
				indicadorVeiculoProprio = "N�o";
			}
		}
		parametros.put("indicadorVeiculoProprio", indicadorVeiculoProprio);

		//Equipamentos especiais
		if (servicoPerfilTipoParametros.getEquipamentosEspeciais() != null) {
			parametros.put("equipamentosEspeciais", servicoPerfilTipoParametros.getEquipamentosEspeciais().getDescricao());
		} else {
			parametros.put("equipamentosEspeciais", "");	
		}
		
		//Indicador de Uso
		String indicadorUso = "";
		String indUso = (""+ servicoPerfilTipoParametros.getIndicadorUso());

		if (  indUso != null
				&& ! indUso.equals("")) {
			if ( indUso.equals(""+("1"))) {
				indicadorUso = "Ativo";
			} else if (indUso.equals(""+("2"))) {
				indicadorUso = "Inativo";
			}
		}
		parametros.put("indicadorUso", indicadorUso);
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_TIPO_PERFIL_SERVICO_MANTER, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterTipoPerfilServico", this);
	}

}