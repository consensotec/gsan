/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest„o de ServiÁos de Saneamento
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
* GSAN - Sistema Integrado de Gest„o de ServiÁos de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara˙jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl·udio de Andrade Lira
* Denys Guimar„es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* FabÌola Gomes de Ara˙jo
* Fl·vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J˙nior
* Homero Sampaio Cavalcanti
* Ivan SÈrgio da Silva J˙nior
* JosÈ Edmar de Siqueira
* JosÈ Thiago TenÛrio Lopes
* K·ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M·rcio Roberto Batista da Silva
* Maria de F·tima Sampaio Leite
* Micaela Maria Coelho de Ara˙jo
* Nelson MendonÁa de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael CorrÍa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara˙jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S·vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa È software livre; vocÍ pode redistribuÌ-lo e/ou
* modific·-lo sob os termos de LicenÁa P˙blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers„o 2 da
* LicenÁa.
* Este programa È distribuÌdo na expectativa de ser ˙til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implÌcita de
* COMERCIALIZA«√O ou de ADEQUA«√O A QUALQUER PROP”SITO EM
* PARTICULAR. Consulte a LicenÁa P˙blica Geral GNU para obter mais
* detalhes.
* VocÍ deve ter recebido uma cÛpia da LicenÁa P˙blica Geral GNU
* junto com este programa; se n„o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.batch.gerencial.arrecadacao;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para batch Gerar Resumo Consumo Agua BD
 * @author Rafael Corrêa
 * @created 28/12/2012
 */
public class TarefaBatchGerarUnResumoArrecadacaoBD extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarUnResumoArrecadacaoBD(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarUnResumoArrecadacaoBD() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_UN_RESUMO_ARRECADACAO_BD,
				new Object[]{
						this.getIdFuncionalidadeIniciada()});


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
		AgendadorTarefas.agendarTarefa("BatchGerarUnResumoArrecadacaoBD",
				this);
	}

}
