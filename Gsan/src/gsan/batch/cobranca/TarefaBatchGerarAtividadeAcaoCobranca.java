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
package gsan.batch.cobranca;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaAcaoAtividadeComando;
import gsan.cobranca.CobrancaAcaoAtividadeCronograma;
import gsan.cobranca.CobrancaAtividade;
import gsan.cobranca.CobrancaCriterio;
import gsan.micromedicao.ControladorMicromedicaoLocal;
import gsan.micromedicao.ControladorMicromedicaoLocalHome;
import gsan.micromedicao.Rota;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;

/**
 * Tarefa que manda para batch Gerar Atividade de A��o de Cobran�a
 * 
 * @author Rodrigo Silveira
 * @created 17/11/2006
 */
public class TarefaBatchGerarAtividadeAcaoCobranca extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarAtividadeAcaoCobranca(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarAtividadeAcaoCobranca() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		try{
			
//			CobrancaGrupo grupoCobranca = (CobrancaGrupo) getParametro("grupoCobranca");
//			Integer anoMesReferenciaCicloCobranca = (Integer) getParametro("anoMesReferenciaCicloCobranca");
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) getParametro("comandoAtividadeAcaoCobranca");
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoComando = (CobrancaAcaoAtividadeComando) getParametro("comandoAtividadeAcaoComando");
			Collection colecaoRota = (Collection) getParametro("colecaoRotas");
			CobrancaAcao acaoCobranca = (CobrancaAcao) getParametro("acaoCobranca");
			CobrancaAtividade atividadeCobranca = (CobrancaAtividade) getParametro("atividadeCobranca");
			Short indicadorCriterio = (Short) getParametro("indicadorCriterio");

			CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) getParametro("criterioCobranca");
			Cliente cliente = (Cliente) getParametro("cliente");
			Cliente clienteSuperior = (Cliente) getParametro("clienteSuperior");
			ClienteRelacaoTipo clienteRelacaoTipo = (ClienteRelacaoTipo) getParametro("clienteRelacaoTipo");

			Integer anoMesReferenciaInicial = (Integer) getParametro("anoMesReferenciaInicial");
			Integer anoMesReferenciaFinal = (Integer) getParametro("anoMesReferenciaFinal");
			Date dataVencimentoInicial = (Date) getParametro("dataVencimentoInicial");
			Date dataVencimentoFinal = (Date) getParametro("dataVencimentoFinal");
			Date dataAtual = (Date) getParametro("dataAtual");
			Integer idCobrancaDocumentoControleGeracao = (Integer)getParametro("idCobrancaDocumentoControleGeracao");
			Integer quantidadeDiasVencimento = (Integer)getParametro("quantidadeDiasVencimento");
			
			//se o batch n�o for processado por rota,
			//n�o ter� paralelismo 
			if (cliente != null || clienteSuperior != null ||
				(comandoAtividadeAcaoComando!=null && comandoAtividadeAcaoComando.getLogradouro()!=null)) {
				
				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_GERAR_ATIVIDADE_ACAO_COBRANCA_MDB,
						new Object[]{
								cobrancaAcaoAtividadeCronograma,
								comandoAtividadeAcaoComando,
								null,//Rota
								acaoCobranca, 
								atividadeCobranca, 
								indicadorCriterio.intValue(),
								cobrancaCriterio, 
								cliente, 
								clienteRelacaoTipo,
								anoMesReferenciaInicial != null ? anoMesReferenciaInicial.toString() : null ,
								anoMesReferenciaFinal != null ? anoMesReferenciaFinal.toString() : null,
								dataVencimentoInicial, 
								dataVencimentoFinal,
								dataAtual,
								this.getIdFuncionalidadeIniciada(),
								clienteSuperior,
								idCobrancaDocumentoControleGeracao, 
								quantidadeDiasVencimento});
				
				
			}else{
				//batch processado por rota, roda em paralelo
				
				if (colecaoRota == null || colecaoRota.isEmpty()) {

					SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
					
					if (acaoCobranca.getId() != null && acaoCobranca.getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO)
						&& sistemaParametro.getCodigoEmpresaFebraban() != null && 
						sistemaParametro.getCodigoEmpresaFebraban().equals(Empresa.EMPRESA_FEBRABAN_COMPESA)) {
						// Obt�m a cole��o de todas as rotas do sistema com empresaCobranca igual a 1
						colecaoRota = getControladorMicromedicao().pesquisarListaRotasEspecificas();

					}
				}
				
				
				/*
	             * Colocado por Raphael Rossiter em 25/10/2011
	             * Motivo: Usu�rio estava comandando grupos de cobran�a sem que o mesmo esteja associado a pelo menos 1 rota.
	             */
				if (colecaoRota != null && !colecaoRota.isEmpty()){
					
					Iterator iteratorRotas = colecaoRota.iterator();
					
					while (iteratorRotas.hasNext()) {
						Rota rota = (Rota) iteratorRotas.next();
//						System.out.println("*************************");
//			            System.out.println("Gerar Documento Cobranca.ROTA:" + rota.getId());
//			            System.out.println("*************************");
								            
			            enviarMensagemControladorBatch(
								ConstantesJNDI.BATCH_GERAR_ATIVIDADE_ACAO_COBRANCA_MDB,
								new Object[]{
										cobrancaAcaoAtividadeCronograma,
										comandoAtividadeAcaoComando,
										rota,
										acaoCobranca, 
										atividadeCobranca, 
										indicadorCriterio.intValue(),
										cobrancaCriterio, 
										cliente, 
										clienteRelacaoTipo,
										anoMesReferenciaInicial != null ? anoMesReferenciaInicial.toString() : null ,
										anoMesReferenciaFinal != null ? anoMesReferenciaFinal.toString() : null,
										dataVencimentoInicial, 
										dataVencimentoFinal,
										dataAtual,
										this.getIdFuncionalidadeIniciada(),
										clienteSuperior,idCobrancaDocumentoControleGeracao,
										quantidadeDiasVencimento});
			            
						
					}
				}
				else{
					
					enviarMensagemControladorBatch(
							ConstantesJNDI.BATCH_GERAR_ATIVIDADE_ACAO_COBRANCA_MDB,
							new Object[]{
									cobrancaAcaoAtividadeCronograma,
									comandoAtividadeAcaoComando,
									null, //Rota
									acaoCobranca, 
									atividadeCobranca, 
									indicadorCriterio.intValue(),
									cobrancaCriterio, 
									cliente, 
									clienteRelacaoTipo,
									anoMesReferenciaInicial != null ? anoMesReferenciaInicial.toString() : null ,
									anoMesReferenciaFinal != null ? anoMesReferenciaFinal.toString() : null,
									dataVencimentoInicial, 
									dataVencimentoFinal,
									dataAtual,
									this.getIdFuncionalidadeIniciada(),
									clienteSuperior,idCobrancaDocumentoControleGeracao ,
									quantidadeDiasVencimento});
				}
			}

			
		} catch (ControladorException e) {
			System.out.println("Erro no MDB");
			e.printStackTrace();
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
		AgendadorTarefas.agendarTarefa("GerarAtividadeAcaoCobrancaBatch", this);
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

	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
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
