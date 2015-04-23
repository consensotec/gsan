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
package gsan.tarefa;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.TestCase;

public class ImplementacaoTesteTarefaTest extends TestCase {

	private ImplementacaoTesteTarefa tarefa;

	private String TIPO_SHORT = "Short"; 
	private String TIPO_BYTE = "Byte";
	private String TIPO_CHARACTER  = "Character";
	private String TIPO_INTEGER = "Integer";
	private String TIPO_LONG = "Long";
	private String TIPO_DOUBLE = "Double";
	private String TIPO_FLOAT = "Float";
	private String TIPO_STRING = "String";
	private String TIPO_BOOLEAN = "Boolean";
	private String TIPO_BIGDECIMAL = "BigDecimal";
	private String TIPO_DATE = "Date";

	private Short VALOR_SHORT = new Short((short)1); 
	private Byte VALOR_BYTE = new Byte((byte)1);
	private Character VALOR_CHARACTER  = new Character('c');
	private Integer VALOR_INTEGER = new Integer(850);
	private Long VALOR_LONG = new Long(741l);
	private Double VALOR_DOUBLE = new Double(987d);
	private Float VALOR_FLOAT = new Float(321f);
	private String VALOR_STRING = "string";
	private Boolean VALOR_BOOLEAN = new Boolean(true);
	private BigDecimal VALOR_BIGDECIMAL = new BigDecimal(123);
	private Date VALOR_DATE = new Date(System.currentTimeMillis());
	

	protected void setUp() throws Exception {
		tarefa = new ImplementacaoTesteTarefa(null, 1);

		tarefa.addParametro(TIPO_SHORT, VALOR_SHORT); 
		tarefa.addParametro(TIPO_BYTE, VALOR_BYTE);
		tarefa.addParametro(TIPO_CHARACTER, VALOR_CHARACTER);
		tarefa.addParametro(TIPO_INTEGER, VALOR_INTEGER);
		tarefa.addParametro(TIPO_LONG, VALOR_LONG);
		tarefa.addParametro(TIPO_DOUBLE, VALOR_DOUBLE);
		tarefa.addParametro(TIPO_FLOAT, VALOR_FLOAT);
		tarefa.addParametro(TIPO_STRING, VALOR_STRING);
		tarefa.addParametro(TIPO_BOOLEAN, VALOR_BOOLEAN);
		tarefa.addParametro(TIPO_BIGDECIMAL, VALOR_BIGDECIMAL);
		tarefa.addParametro(TIPO_DATE, VALOR_DATE);
	}

//	public void testShort() {
//		Short retorno = tarefa.getParametro(TIPO_SHORT);
//		assertTrue(" Short não foi igual ", VALOR_SHORT.equals(retorno)); 
//	}
//
//	public void testByte() {
//		Byte retorno = tarefa.getParametroByte(TIPO_BYTE);
//		assertTrue(" Byte não foi igual ", VALOR_BYTE.equals(retorno)); 
//	}
//
//	public void testCharacter() {
//		Character retorno = tarefa.getParametroCharacter(TIPO_CHARACTER);
//		assertTrue(" Character não foi igual ", VALOR_CHARACTER.equals(retorno)); 
//	}
//
//	public void testInteger() {
//		Integer retorno = tarefa.getParametroInteger(TIPO_INTEGER);
//		assertTrue(" Integer não foi igual ", VALOR_INTEGER.equals(retorno)); 
//	}
//
//	public void testLong() {
//		Long retorno = tarefa.getParametroLong(TIPO_LONG);
//		assertTrue(" Long não foi igual ", VALOR_LONG.equals(retorno)); 
//	}
//
//	public void testDouble() {
//		Double retorno = tarefa.getParametroDouble(TIPO_DOUBLE);
//		assertTrue(" Double não foi igual ", VALOR_DOUBLE.equals(retorno)); 
//	}
//
//	public void testFloat() {
//		Float retorno = tarefa.getParametroFloat(TIPO_FLOAT);
//		assertTrue(" Float não foi igual ", VALOR_FLOAT.equals(retorno)); 
//	}
//
//	public void testString () {
//		String retorno = tarefa.getParametroString(TIPO_STRING);
//		assertTrue(" String não foi igual ", VALOR_STRING.equals(retorno)); 
//	}
//
//	public void testBoolean () {
//		Boolean retorno = tarefa.getParametroBoolean(TIPO_BOOLEAN);
//		assertTrue(" Boolean não foi igual ", VALOR_BOOLEAN.equals(retorno)); 
//	}
//
//	public void testBigDecimal () {
//		BigDecimal retorno = tarefa.getParametroBigDecimal(TIPO_BIGDECIMAL);
//		assertTrue(" BigDecimal não foi igual ", VALOR_BIGDECIMAL.equals(retorno)); 
//	}
//
//	public void testDate () {
//		Date retorno = tarefa.getParametroDate(TIPO_DATE);
//		assertTrue(" Date não foi igual ", VALOR_DATE.equals(retorno)); 
//	}
}