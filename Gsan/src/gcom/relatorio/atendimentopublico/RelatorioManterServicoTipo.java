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
package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de Tipo de Servico
 * 
 * @author Vinicius Medeiros
 * @created 23 de Marco de 2009
 */
public class RelatorioManterServicoTipo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterServicoTipo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_SERVICO_TIPO);
	}

	@Deprecated
	public RelatorioManterServicoTipo() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param colecaoServicoTipo
	 *            Description of the Parameter
	 * @param servicoTipoParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		FiltroTipoServico filtroTipoServico = (FiltroTipoServico) getParametro("filtroTipoServico");
		ServicoTipo servicoTipoParametros = (ServicoTipo) getParametro("servicoTipoParametros");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterServicoTipoBean relatorioBean = null;

		filtroTipoServico.setConsultaSemLimites(true);

		Collection<ServicoTipo> colecaoServicoTipo = fachada.pesquisar(filtroTipoServico, ServicoTipo.class
				.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {

			// la�o para criar a cole��o de par�metros da analise
			for (ServicoTipo servicoTipo : colecaoServicoTipo) {

				String tempoMedio = "" + servicoTipo.getTempoMedioExecucao();
				
				String indicadorAtualizaComercial = "" + servicoTipo.getIndicadorAtualizaComercial();
				
				if (indicadorAtualizaComercial.equals("1")) {
					indicadorAtualizaComercial = "Sim, no momento da execu��o";
				
				} else if (indicadorAtualizaComercial.equals("2")) {
					
					indicadorAtualizaComercial = "N�o Atualiza";
				
				} else if (indicadorAtualizaComercial.equals("3")) {
					
					indicadorAtualizaComercial = "Sim, no momento posterior";
			    }
				
				String codigoServicoTipo = servicoTipo.getCodigoServicoTipo();
				
				if(codigoServicoTipo.equals("C")){
					codigoServicoTipo = "Comercial";
				} else if (codigoServicoTipo.equals("O")){
					codigoServicoTipo = "Operacional";
				}
				
				relatorioBean = new RelatorioManterServicoTipoBean(
						// C�digo
						servicoTipo.getId().toString(), 
						
						// Descri��o
						servicoTipo.getDescricao(), 
						
						// Codigo Servico Tipo
						codigoServicoTipo,
						
						// Tempo Medio
						tempoMedio,
						
						// Indicador Atualizacao Comercial
						indicadorAtualizaComercial);

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
		
		// Codigo
		if (servicoTipoParametros.getId() != null) {
			parametros.put("codigo",
					servicoTipoParametros.getId().toString());
		} else {
			parametros.put("codigo", "");
		}

		// Descricao
		parametros.put("descricao", servicoTipoParametros.getDescricao());

		// Descricao Abreviada
		parametros.put("abreviacao", servicoTipoParametros.getDescricaoAbreviada());
		
		// Tempo Medio Inicial e Final
		String tempoMedioInicial = (String) getParametro("tempoMedioInicial");
		String tempoMedioFinal = (String) getParametro("tempoMedioFinal");
		parametros.put("tempoMedioInicial", tempoMedioInicial);
		parametros.put("tempoMedioFinal", tempoMedioFinal);
		
		// Valor Inicial e Final
		String valorInicial = (String) getParametro("valorInicial");
		String valorFinal = (String) getParametro("valorFinal");		
		parametros.put("valorInicial", valorInicial);
		parametros.put("valorFinal", valorFinal);
				
		// Codigo Tipo Servico
		String codigoServicoTipo = servicoTipoParametros.getCodigoServicoTipo();
		
		if(codigoServicoTipo != null && !codigoServicoTipo.equals("")){
			if(codigoServicoTipo.equals("C")){
				codigoServicoTipo = "Comercial";
			} else if (codigoServicoTipo.equals("O")){
				codigoServicoTipo = "Operacional";
			}
		}
		
		parametros.put("codigoServicoTipo",codigoServicoTipo);
		
		//indicadorAtualizaComercial
		String indicadorAtualizacaoComercial = "" + servicoTipoParametros.getIndicadorAtualizaComercial();

			if (indicadorAtualizacaoComercial.equals("1")) {
				indicadorAtualizacaoComercial = "Sim, no momento da execu��o";
			} else if (indicadorAtualizacaoComercial.equals("2")) {
				indicadorAtualizacaoComercial = "N�o Atualiza";
			} else if (indicadorAtualizacaoComercial.equals("3")) {
				indicadorAtualizacaoComercial = "Sim, no momento posterior";
		}

		parametros.put("indicadorAtualizacaoComercial", indicadorAtualizacaoComercial);
		
		//Indicador Pavimento Rua
		String indicadorPavimentoRua = "" + servicoTipoParametros.getIndicadorPavimentoRua();
		
		if(indicadorPavimentoRua.equals("1")){
			indicadorPavimentoRua = "Sim";
		} else if(indicadorPavimentoRua.equals("2")){
			indicadorPavimentoRua = "N�o";
		} else {
			indicadorPavimentoRua = "Todos";
		}	

		parametros.put("indicadorPavimentoRua",indicadorPavimentoRua);

		//Indicador Pavimento Calcada
		String indicadorPavimentoCalcada = "" + servicoTipoParametros.getIndicadorPavimentoCalcada();
		
		if(indicadorPavimentoCalcada.equals("1")){
			indicadorPavimentoCalcada = "Sim";
		} else if(indicadorPavimentoCalcada.equals("2")){
			indicadorPavimentoCalcada = "N�o";
		} else {
			indicadorPavimentoCalcada = "Todos";
		}	

		parametros.put("indicadorPavimentoCalcada",indicadorPavimentoCalcada);
		
		//Indicador Atualizacao Comercial
		String indicadorAtualizaComercial = "" + servicoTipoParametros.getIndicadorAtualizaComercial();
		
		if(indicadorAtualizaComercial.equals("0")){
			indicadorAtualizaComercial = "";
		} else if(indicadorAtualizaComercial.equals("1")){
			indicadorAtualizaComercial = "Sim - Todos";
		} else if(indicadorAtualizaComercial.equals("2")){
			indicadorAtualizaComercial = "Sim - Momento da Execu��o";
		} else if(indicadorAtualizaComercial.equals("3")){
			indicadorAtualizaComercial = "Sim - Momento Posterior";
		} else if(indicadorAtualizaComercial.equals("4")){
			indicadorAtualizaComercial = "Todos";
		} else if(indicadorAtualizaComercial.equals("5")){
			indicadorAtualizaComercial = "N�o";
		}	
		
		parametros.put("indicadorAtualizaComercial",indicadorAtualizaComercial);

		
		// Indicador Servico Terceirizado
		String indicadorServicoTerceirizado = "" + servicoTipoParametros.getIndicadorTerceirizado();
		
		if(indicadorServicoTerceirizado.equals("1")){
			indicadorServicoTerceirizado = "Sim";
		} else if(indicadorServicoTerceirizado.equals("2")){
			indicadorServicoTerceirizado = "N�o";
		} else {
			indicadorServicoTerceirizado = "Todos";
		}	

		parametros.put("indicadorServicoTerceirizado",indicadorServicoTerceirizado);
		
		// Indicativo Fiscalizacao Infracao
		String indicativoFiscalizacaoInfracao = "" + servicoTipoParametros.getIndicadorFiscalizacaoInfracao();
		
		if(indicativoFiscalizacaoInfracao.equals("1")){
			indicativoFiscalizacaoInfracao = "Sim";
		} else if(indicativoFiscalizacaoInfracao.equals("2")){
			indicativoFiscalizacaoInfracao = "N�o";
		} else {
			indicativoFiscalizacaoInfracao = "Todos";
		}	

		parametros.put("indicativoFiscalizacaoInfracao",indicativoFiscalizacaoInfracao);

		// Indicativo Vistoria
		String indicativoVistoria = "" + servicoTipoParametros.getIndicadorVistoria();
		
		if(indicativoVistoria.equals("1")){
			indicativoVistoria = "Sim";
		} else if(indicativoVistoria.equals("2")){
			indicativoVistoria = "N�o";
		} else {
			indicativoVistoria = "Todos";
		}	

		parametros.put("indicativoVistoria",indicativoVistoria);
		
		// Tipo Debito
		if (servicoTipoParametros.getDebitoTipo() != null) {
			parametros.put("debitoTipo", servicoTipoParametros.getDebitoTipo().getDescricao());
		} else {
			parametros.put("debitoTipo", "");	
		}
		
		// Tipo Credito
		if (servicoTipoParametros.getCreditoTipo() != null) {
			parametros.put("creditoTipo", servicoTipoParametros.getCreditoTipo().getDescricao());
		} else {
			parametros.put("creditoTipo", "");	
		}
		
		// Servico Tipo Prioridade
		if (servicoTipoParametros.getServicoTipoPrioridade() != null) {
			parametros.put("servicoTipoPrioridade", servicoTipoParametros.getServicoTipoPrioridade().getDescricao());
		} else {
			parametros.put("servicoTipoPrioridade", "");	
		}
		
		// Servico Tipo Perfil
		if (servicoTipoParametros.getServicoPerfilTipo() != null) {
			parametros.put("servicoPerfilTipo", servicoTipoParametros.getServicoPerfilTipo().getDescricao());
		} else {
			parametros.put("servicoPerfilTipo", "");	
		}
		
		// Servico Tipo Referencia
		if (servicoTipoParametros.getServicoTipoReferencia() != null) {
			parametros.put("servicoTipoReferencia", servicoTipoParametros.getServicoTipoReferencia().getDescricao());
		} else {
			parametros.put("servicoTipoReferencia", "");	
		}		
		
		// Subgrupo
		if (servicoTipoParametros.getServicoTipoSubgrupo() != null) {
			parametros.put("servicoTipoSubgrupo", servicoTipoParametros.getServicoTipoSubgrupo().getDescricao());
		} else {
			parametros.put("servicoTipoSubgrupo", "");	
		}

		//Indicador Atividade Unica
		String indicadorAtividadeUnica = "" + servicoTipoParametros.getIndicadorUso();
		
		if(indicadorAtividadeUnica.equals("1")){
			indicadorAtividadeUnica = "Sim";
		} else if(indicadorAtividadeUnica.equals("2")){
			indicadorAtividadeUnica = "N�o";
		} else {
			indicadorAtividadeUnica = "Todos";
		}
		
		parametros.put("indicadorAtividadeUnica", indicadorAtividadeUnica);

		//indicadorUso
		String indicadorUso = "" + servicoTipoParametros.getIndicadorUso();
		
		if(indicadorUso.equals("1")){
			indicadorUso = "Ativo";
		} else if(indicadorUso.equals("2")){
			indicadorUso = "Inativo";
		} else {
			indicadorUso = "Todos";
		}
		
		parametros.put("indicadorUso", indicadorUso);
		
		
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_SERVICO_TIPO, parametros,
				ds, tipoFormatoRelatorio);
		
		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterServicoTipo", this);
	}

}