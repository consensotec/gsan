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
package gcom.tarefa;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Classe que representa uma tarefa
 * 
 * @author thiago
 * @date 24/01/2006
 */
public abstract class Tarefa implements Serializable, Job {

	protected static final long serialVersionUID = 1L;
		
	private Set parametroTarefa = new HashSet();

	private Usuario usuario;

	private int idFuncionalidadeIniciada;

	public Tarefa(Usuario usuario, int idFuncionalidadeIniciada) {
		this.usuario = usuario;
		this.idFuncionalidadeIniciada = idFuncionalidadeIniciada;

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set getParametroTarefa() {
		return parametroTarefa;

	}

	public void setParametroTarefa(Set parametroTarefa) {
		this.parametroTarefa = parametroTarefa;
	}

	/**
	 * M�todo que adiciona um Short
	 * 
	 * @param nome
	 * @param valor
	 */
	public void addParametro(String nome, Object valor) {
		parametroTarefa.add(new ParametroTarefa(nome, valor));
	}

	/**
	 * 
	 * @author Thiago Toscano
	 * @date 23/05/2006
	 * @param nome
	 * @return
	 */
	public Object getParametro(String nome) {
		Object retorno = null;
		Iterator it = parametroTarefa.iterator();
		while (it.hasNext()) {
			ParametroTarefa parametroTarefa = (ParametroTarefa) it.next();
			if (nome != null && nome.equals(parametroTarefa.getNome())) {
				retorno = parametroTarefa.getValor();
			}
		}
		return retorno;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public abstract Object executar() throws TarefaException;

	public abstract void execute(JobExecutionContext arg0)
			throws JobExecutionException;

	/**
	 * Faz o agendamento da tarefa batch no sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 03/08/2006
	 * 
	 */
	public abstract void agendarTarefaBatch();

	public int getIdFuncionalidadeIniciada() {
		return idFuncionalidadeIniciada;
	}

	public void setIdFuncionalidadeIniciada(int idFuncionalidadeIniciada) {
		this.idFuncionalidadeIniciada = idFuncionalidadeIniciada;
	}

}