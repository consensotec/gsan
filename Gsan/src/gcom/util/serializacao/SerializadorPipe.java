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
* Anderson Italo felinto de Lima
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
package gcom.util.serializacao;

import java.lang.reflect.Method;
import java.text.Format;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementa��o de Serializador para arquivo de texto com campos separados por
 * pipe(|)
 * 
 * @author Andr� Miranda
 * @date 09/06/2015
 * 
 * @see TarefaSerializar
 */
public class SerializadorPipe implements Serializador {
	private String separador = "|";

	public void iniciar(Object bean, StringBuilder sb) {}

	public void gravar(Object bean, Map<String, Format> formatos, StringBuilder sb) throws Exception {
		Map<Integer, Object> resultado = new TreeMap<Integer, Object>();

		for (Method m : bean.getClass().getMethods()) {
			if (!m.isAnnotationPresent(Campo.class)) continue;

			Campo c = m.getAnnotation(Campo.class);
			Object value = m.invoke(bean);

			Format formato = formatos.get(m.getName());
			if(formato != null && value != null) {
				value = formato.format(value);
			}

			value = value == null ? "" : value;
			if(c.tab()) value  = value + "\t";
			Object last = resultado.put(c.posicao(), value);

			if(last != null) {
				throw new IllegalStateException("Colunas com mesma posi��o: " + c.posicao());
			}
		}

		for (Integer posicao : resultado.keySet()) {
			sb.append(resultado.get(posicao));
			sb.append(separador);
		}

		// Remover �ltimo separador
		if(sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		// For�ar para formato Windows
		sb.append("\r\n");
	}

	public void finalizar(StringBuilder sb) {}
}
