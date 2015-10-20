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
package gcom.batch.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Emitir Contas
 * 
 * @author Rodrigo Silveira
 * @created 05/12/2006
 */
public class TarefaBatchEmitirContas extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirContas(Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirContas() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");
		Collection colecaoIdsEmpresas = 
			(Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();

		// Manda rodar cada tipo de conta numa thread diferente
		if (!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAERN") && 
			!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAER") && 
			!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAEMA") &&
			!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("COSANPA")) {

			if (faturamentoGrupo != null) {

				for (int tipoConta = 0; tipoConta < 6; tipoConta++) {

					if (tipoConta == 3 || tipoConta == 4) {
						
						enviarMensagemControladorBatch(
								ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
								new Object[] { anoMesFaturamentoGrupo,
										faturamentoGrupo,
										this.getIdFuncionalidadeIniciada(),
										tipoConta, 
										null, 
										ConstantesSistema.NAO }
								);

					} else {
						
						Iterator iteratorEmpresas = colecaoIdsEmpresas.iterator();
						
						while (iteratorEmpresas.hasNext()) {
							
							Integer idEmpresa = (Integer) iteratorEmpresas.next();

							enviarMensagemControladorBatch(
									ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
									new Object[] { anoMesFaturamentoGrupo,
											faturamentoGrupo,
											this.getIdFuncionalidadeIniciada(),
											tipoConta, 
											idEmpresa,
											ConstantesSistema.NAO 
											}
									);
						}

					}
				}

			} else {
				for (int tipoConta = 3; tipoConta < 5; tipoConta++) {

					enviarMensagemControladorBatch(
							ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
							new Object[] { anoMesFaturamentoGrupo,
									faturamentoGrupo,
									this.getIdFuncionalidadeIniciada(),
									tipoConta, 
									null, 
									ConstantesSistema.SIM });
				}
			}
		} else if ((sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAERN")) ||
				(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAEMA")) ||
				(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("COSANPA"))){
			
			//QUANDO A EMPRESA FOR CAERN
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB, new Object[] {
							anoMesFaturamentoGrupo, faturamentoGrupo,
							this.getIdFuncionalidadeIniciada(), 
							5, 
							1, 
							ConstantesSistema.SIM });
		//CAER
		}else{
			
			if (faturamentoGrupo != null) {

				for (int tipoConta = 0; tipoConta < 2; tipoConta++) {

					if (tipoConta == 2) {
						
						enviarMensagemControladorBatch(
								ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
								new Object[] { anoMesFaturamentoGrupo,
										faturamentoGrupo,
										this.getIdFuncionalidadeIniciada(),
										tipoConta, 
										null, 
										ConstantesSistema.NAO }
								);

					} else {
						
						enviarMensagemControladorBatch(
							ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
								new Object[] { anoMesFaturamentoGrupo,
									faturamentoGrupo,
									this.getIdFuncionalidadeIniciada(),
									tipoConta, 
									1,
									ConstantesSistema.NAO }
						);
					}
				}

			} else {

				enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
						new Object[] { anoMesFaturamentoGrupo,
							faturamentoGrupo,
							this.getIdFuncionalidadeIniciada(),
							2, 
							null, 
							ConstantesSistema.SIM });
			}			
			
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
		AgendadorTarefas.agendarTarefa("EmitirContasBatch", this);
	}

}
