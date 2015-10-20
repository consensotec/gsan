
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
package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativadorResultadoSimulacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane
 * @created 16 de maio de 2008
 * @version 1.0
 */

public class RelatorioNegativadorResultadoSimulacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorExclusaoMotivo object
	 */
	public RelatorioNegativadorResultadoSimulacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_EXCLUSAO_MOTIVO);
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param NegativadorExclusaoMotivo Parametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os par�metros que ser�o utilizados no relat�rio
		
		NegativadorResultadoSimulacao parametrosNegativadorResultadoSimulacao = (NegativadorResultadoSimulacao) getParametro("parametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioNegativadorResultadoSimulacaoBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necess�rios para
		// a impress�o do relat�rio	
	//	filtro.adicionarCaminhoParaCarregamentoEntidade("negativacaoComando");
	//	filtro.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
//		Collection<NegativadorResultadoSimulacao> colecaoNovos = fachada.pesquisar(filtro, NegativadorResultadoSimulacao.class
//				.getName());

		Collection<NegativadorResultadoSimulacao> colecaoNovos = fachada.pesquisarNegativadorResultadoSimulacao(parametrosNegativadorResultadoSimulacao.getId());
		
		if (colecaoNovos != null && !colecaoNovos.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			for (NegativadorResultadoSimulacao negativadorResultadoSimulacao : colecaoNovos) {
				
				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio
				
				String idComando = "";				
				if (negativadorResultadoSimulacao.getNegativacaoComando().getId() != null ) {
					idComando = negativadorResultadoSimulacao.getNegativacaoComando().getId().toString();
				}
				
										
				String descricaoTitulo = "";
				NegativacaoCriterio nCriterio = fachada.pesquisarNegativacaoCriterio(new Integer(idComando));
				if(nCriterio != null){
					descricaoTitulo = nCriterio.getDescricaoTitulo();
				}
			
				
				
				// Descri��o
				String sequencial = "";				
				if (negativadorResultadoSimulacao.getId() != null ) {
					sequencial = negativadorResultadoSimulacao.getId().toString();
				}
				
				// idImovel
				String idImovel = "";				
				if (negativadorResultadoSimulacao.getImovel().getId() != null) {
					idImovel = negativadorResultadoSimulacao.getImovel().getId().toString();
				}			
				
				//numero cpf
				String numeroCpf = "";				
				if (negativadorResultadoSimulacao.getNumeroCpf() != null) {					
					numeroCpf = Util.formatarCpf(negativadorResultadoSimulacao.getNumeroCpf());
				}
				
				//numero cnpj
				String numeroCnpj = "";				
				if (negativadorResultadoSimulacao.getNumeroCnpj() != null) {					
					numeroCnpj = Util.formatarCnpj(negativadorResultadoSimulacao.getNumeroCnpj());
				}
				
				

				// valorDebito
				BigDecimal valorDebito = null;				
				if (negativadorResultadoSimulacao.getValorDebito() != null) {					
					valorDebito = negativadorResultadoSimulacao.getValorDebito();
				}

				// Inicializa o construtor constitu�do dos campos
				// necess�rios para a impress�o do relatorio
				relatorioBean = new RelatorioNegativadorResultadoSimulacaoBean(
						
						// ID
						idComando,
						
						descricaoTitulo,

						// Descri��o
						sequencial,

						//idImovel
						idImovel,

						//valorDebito
						valorDebito,					
					
						// numero Cpf
						numeroCpf,
						
						// numero numeroCnpj
						numeroCnpj);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("idComando", parametrosNegativadorResultadoSimulacao.getId() == -1 ? ""
				: "" + parametrosNegativadorResultadoSimulacao.getId());	


		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_NEGATIVADOR_RESULTADO_SIMULACAO, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESULTADO_SIMULACAO,
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
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroNegativadorExclusaoMotivo) getParametro("filtroNegativadorResultadoSimulacao"),
//				NegativadorResultadoSimulacao.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNegativadorResultadoSimulacao", this);
	}

}
