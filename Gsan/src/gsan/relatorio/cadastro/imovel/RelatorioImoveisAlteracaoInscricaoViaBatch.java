/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gsan.relatorio.cadastro.imovel;

import gsan.batch.Relatorio;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelInscricaoAlterada;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o Im�veis com Altera��o de Inscri��o Via Batch
 * 
 * [UC1121] Gerar Relat�rio de Im�veis com Altera��o de Inscri��o Via Batch
 * 
 * @author Hugo Leonardo
 *
 * @date 19/01/2011
 */
public class RelatorioImoveisAlteracaoInscricaoViaBatch extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisAlteracaoInscricaoViaBatch(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH);
	}

	@Deprecated
	public RelatorioImoveisAlteracaoInscricaoViaBatch() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper = 
			(FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper) getParametro("filtrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String periodo = (String) getParametro("periodo");
		
		String localidadeInicial = (String) getParametro("localidadeInicial");
		String localidadeFinal = (String) getParametro("localidadeFinal");
		
		String setorComercialInicial = (String) getParametro("setorComercialInicial");
		String setorComercialFinal = (String) getParametro("setorComercialFinal");
		
		String quadraIncial = (String) getParametro("quadraIncial");
		String quadraFinal = (String) getParametro("quadraFinal");
		
		String loteInicial = (String) getParametro("loteInicial");
		String loteFinal = (String) getParametro("loteFinal");
		
		String subLoteInicial = (String) getParametro("subLoteInicial");
		String subLoteFinal = (String) getParametro("subLoteFinal");
		
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisAlteracaoInscricaoViaBatchBean relatorioBean = null;

		Collection<ImovelInscricaoAlterada> colecao = fachada.pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch(relatorioHelper);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			Imovel imovelAnterior = null;
			Imovel imovelAtual = null;
			
			// la�o para criar a cole��o de par�metros da analise
			for (ImovelInscricaoAlterada helper : colecao) {
				
				// Inscri��o Anterior
				imovelAnterior = new Imovel();
				imovelAnterior.setLocalidade(helper.getLocalidadeAnterior());
				imovelAnterior.setSetorComercial(helper.getSetorComercialAnterior());
				imovelAnterior.setQuadra(helper.getQuadraAnterior());
				imovelAnterior.setLote(helper.getLoteAnterior());
				imovelAnterior.setSubLote(helper.getSubLoteAnterior());
				
				String inscricaoAnterior = imovelAnterior.getInscricaoFormatada();
				
				// Inscri��o Atual
				imovelAtual = new Imovel();
				imovelAtual.setLocalidade(helper.getLocalidadeAtual());
				imovelAtual.setSetorComercial(helper.getSetorComercialAtual());
				imovelAtual.setQuadra(helper.getQuadraAtual());
				imovelAtual.setLote(helper.getLoteAtual());
				imovelAtual.setSubLote(helper.getSubLoteAtual());
				
				String inscricaoAtual = imovelAtual.getInscricaoFormatada();
				
				// Data Altera��o
				String dataAlteracao = "";
				if (helper.getDataAlteracaoBatch() != null ){
					
					dataAlteracao = Util.formatarData(helper.getDataAlteracaoBatch());
				}
				
				// Matricula
				String matricula = "";
				if(helper.getImovel() != null){
					
					matricula = helper.getImovel().getMatriculaFormatada();
				}
				
				// Cliente Usu�rio
				String clienteUsuario = "";
				if(helper.getImovel().getClienteImoveis() != null){
					
					ClienteImovel clienteImovel = (ClienteImovel) helper.getImovel().getClienteImoveis().iterator().next();
					
					clienteUsuario = clienteImovel.getCliente().getNome();
				}
				
				// Indicador Autorizado
				Short indicadorAutorizado = null;
				String autorizado = "";
				if(helper.getIndicadorAutorizado() != null){
					
					indicadorAutorizado = helper.getIndicadorAutorizado();
					if (indicadorAutorizado == 1){
						autorizado = "SIM";
					} else {
						autorizado = "N�O";
					}
					
				}
				// Indicador Excluido
				Short indicadorExcluido = null;
				String excluidos = "";
				if(helper.getIndicadorImovelExcluido() != null){
					
					indicadorExcluido = helper.getIndicadorImovelExcluido();
					if (indicadorExcluido == 1){
						excluidos = "SIM";
					} else {
						excluidos = "N�O";
					}
					
				}
				
				relatorioBean = 
					new RelatorioImoveisAlteracaoInscricaoViaBatchBean(
							inscricaoAnterior,
							inscricaoAtual,
							dataAlteracao,
							matricula, 
							clienteUsuario,
							autorizado,
							excluidos);	
				
				relatorioBeans.add(relatorioBean);
			}
		}else{
			
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
	
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		String titulo = "";
		if(relatorioHelper.getEscolhaRelatorio().intValue() == 1){
			
			titulo = "Im�veis alterados com sucesso.";
		}else if(relatorioHelper.getEscolhaRelatorio().intValue() == 2){
			
			titulo = "Im�veis sem altera��o devido a erro.";
		}else if(relatorioHelper.getEscolhaRelatorio().intValue() == 3){
			
			titulo = "Im�veis pendentes de altera��o.";
		}
		
		parametros.put("titulo", titulo);
		
		parametros.put("periodo", periodo);
		
		parametros.put("localidadeInicial", localidadeInicial);
		parametros.put("localidadeFinal", localidadeFinal);
		
		parametros.put("setorComercialInicial", setorComercialInicial);
		parametros.put("setorComercialFinal", setorComercialFinal);
		
		parametros.put("quadraIncial", quadraIncial);
		parametros.put("quadraFinal", quadraFinal);
		
		parametros.put("loteInicial", loteInicial);
		parametros.put("loteFinal", loteFinal);
		
		parametros.put("subLoteInicial", subLoteInicial);
		parametros.put("subLoteFinal", subLoteFinal);
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH,
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
		
		int retorno = 0;
   
		retorno = Fachada.getInstancia().countTotalRelatorioImoveisAlteracaoInscricaoViaBatch(
				(FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper) 
					getParametro("filtrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper"));
		
		if (retorno == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao
			// usu�rio;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisAlteracaoInscricaoViaBatch", this);
	}

}