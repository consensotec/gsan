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
package gsan.batch.cobranca.spcserasa;

import gsan.cobranca.FiltroNegativacaoComandoImovel;
import gsan.cobranca.NegativacaoComando;
import gsan.cobranca.NegativacaoComandoImovel;
import gsan.cobranca.NegativacaoCriterio;
import gsan.cobranca.Negativador;
import gsan.cobranca.NegativadorContrato;
import gsan.cobranca.NegativadorMovimento;
import gsan.cobranca.NegativadorMovimentoReg;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.spcserasa.ControladorSpcSerasaLocal;
import gsan.spcserasa.ControladorSpcSerasaLocalHome;
import gsan.spcserasa.FiltroNegativadorMovimento;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ErroRepositorioException;
import gsan.util.RepositorioUtilHBM;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;

/**
 * Tarefa que manda para Executar Comando Negativacao
 * 
 * @author Ana Maria
 * @created 27/03/2007
 */
public class TarefaBatchExecutarComandoNegativacao extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchExecutarComandoNegativacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchExecutarComandoNegativacao() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection rotas = null;
		if(getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH) != null){
			rotas = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
		}

		NegativacaoCriterio nCriterio = (NegativacaoCriterio) getParametro("nCriterio");
		Negativador neg = (Negativador) getParametro("neg");
		NegativacaoComando nComando = (NegativacaoComando) getParametro("nComando");
		NegativadorContrato nContrato = (NegativadorContrato) getParametro("nContrato");
		NegativadorMovimento negMovimento = null;
		Integer idNegativacaoMovimento = null;
		NegativadorMovimentoReg.resetNumeroProximoRegistro();
		NegativacaoComando.resetQuantidadeImoveisJaIncluidos();
		
		try {
			
			if (nComando.getIndicadorSimulacao() == 2 &&
					nComando.getIndicadorComandoCriterio() != ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS) {	
				
				//Inserindo o HEADER
				FiltroNegativadorMovimento fnm = new FiltroNegativadorMovimento();
				fnm.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.NEGATIVACAO_COMANDO, nComando.getId()));
				Collection colecaoNegativadorMovimento  = RepositorioUtilHBM.getInstancia().pesquisar(fnm,NegativadorMovimento.class.getName());
				
				if (colecaoNegativadorMovimento != null && !colecaoNegativadorMovimento.isEmpty()) {
					
					/*
					 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
					 * [SB0014] � Desfazer Atualiza��o da Execu��o Descontinuada
					 */
					getControladorSpcSerasa().desfazerAtualizacaoExecucaoDescontinuada(this.getIdFuncionalidadeIniciada(), nComando);
				
				}
					
				int saEnvio = nContrato.getNumeroSequencialEnvio() + 1;
				//1.2
				//gerando o movimento
					
				Date dataAtual = new Date();
					
				negMovimento = new NegativadorMovimento();
				negMovimento.setNegativador(neg);
				negMovimento.setNegativacaoComando(nComando);
				negMovimento.setNumeroSequencialEnvio(saEnvio);
				negMovimento.setCodigoMovimento(NegativadorMovimento.CODIGO_MOVIMENTO_INCLUSAO);
				negMovimento.setDataEnvio(dataAtual);
				negMovimento.setDataProcessamentoEnvio(dataAtual);
				negMovimento.setUltimaAlteracao(dataAtual);

				idNegativacaoMovimento = (Integer) getControladorUtil().inserir(negMovimento);
				negMovimento.setId(idNegativacaoMovimento);
					
				//1.3
				//[SB0008] - Gerar Registro do tipo Hearder
				getControladorSpcSerasa().gerarRegistroDeInclusaoTipoHeader(
					ConstantesSistema.TIPO_COMANDO_POR_CRITERIO, NegativadorMovimentoReg.getNumeroProximoRegistro(), 
					neg, nContrato,nComando,nCriterio,idNegativacaoMovimento);		
				
			}
			else {
				
				negMovimento = new NegativadorMovimento();
			
			}
			
			if(rotas != null){
				Iterator iterator = rotas.iterator();
				 while (iterator.hasNext()) {
					Integer id = (Integer) iterator.next();
					
					enviarMensagemControladorBatch(
							ConstantesJNDI.BATCH_EXECUTAR_COMANDO_NEGATIVACAO_MDB,
							new Object[] {id, 
									nCriterio, neg,
									nComando,
									nContrato, 
									negMovimento, this.getIdFuncionalidadeIniciada()});
				}
			}else{
				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_EXECUTAR_COMANDO_NEGATIVACAO_MDB,
						new Object[] {0,
								nCriterio, neg,
								nComando,
								nContrato, 
								negMovimento, this.getIdFuncionalidadeIniciada()});
			}
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TarefaException(e.getMessage());
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TarefaException(e.getMessage());
		}

        return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchExecutarComandoNegativacao",
				this);
	}

	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	private ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorSpcSerasaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

}
