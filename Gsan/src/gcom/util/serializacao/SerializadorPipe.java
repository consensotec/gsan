/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
* Anderson Italo felinto de Lima
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/
package gcom.util.serializacao;

import java.lang.reflect.Method;
import java.text.Format;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementação de Serializador para arquivo de texto com campos separados por
 * pipe(|)
 * 
 * @author André Miranda
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
				throw new IllegalStateException("Colunas com mesma posição: " + c.posicao());
			}
		}

		for (Integer posicao : resultado.keySet()) {
			sb.append(resultado.get(posicao));
			sb.append(separador);
		}

		// Remover último separador
		if(sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		// Forçar para formato Windows
		sb.append("\r\n");
	}

	public void finalizar(StringBuilder sb) {}
}
