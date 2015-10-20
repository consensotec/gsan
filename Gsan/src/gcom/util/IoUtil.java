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
package gcom.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Esta classe tem funções auxiliares de validação que podem ser usadas em todo
 * o sistema
 * 
 * @author scme
 */

public class IoUtil {

	/**
	 * Description of the Field
	 */
	public final static int CAMPO_TEXTO = 1;

	/**
	 * Description of the Field
	 */
	public final static int CAMPO_SENHA = 2;

	/**
	 * Description of the Field
	 */
	public final static int CAMPO_EMAIL = 3;

	/**
	 * Remove os caracteres especiais de uma dado texto
	 * 
	 * @param texto
	 *            O texto no qual serão removidos os caracteres especias
	 * @return O texto sem os caracteres especiais
	 */

	public static String removerCaracteresEspeciais(String texto) {

		String[] indesejaveis = { "~", "{", "}", "^", "%", "$", "[", "]", "@",
				"|", "`", "\\", "#", "?", "!", "'", ";", "*", "'", "<", ">",
				"\"" };

		String delimitador;

		StringBuffer textoBuffer = new StringBuffer(texto);

		for (int i = 0; i < indesejaveis.length; i++) {

			delimitador = indesejaveis[i];

			StringTokenizer stringTokenizer = new StringTokenizer(textoBuffer
					.toString(), delimitador);

			textoBuffer = new StringBuffer();

			while (stringTokenizer.hasMoreElements()) {

				textoBuffer.append(stringTokenizer.nextToken());

			}

		}

		return textoBuffer.toString();
	}

	/**
	 * Remove os caracteres especiais de uma senha
	 * 
	 * @param texto
	 *            O texto no qual serão removidos os caracteres especias
	 * @return O texto sem os caracteres especiais
	 */

	public static String removerCaracteresEspeciaisSenha(String texto) {

		String[] indesejaveis = { "'", "\"", "<", ">", "*", ";" };

		String delimitador;

		StringBuffer textoBuffer = new StringBuffer(texto);

		for (int i = 0; i < indesejaveis.length; i++) {

			delimitador = indesejaveis[i];

			StringTokenizer stringTokenizer = new StringTokenizer(textoBuffer
					.toString(), delimitador);

			textoBuffer = new StringBuffer();

			while (stringTokenizer.hasMoreElements()) {

				textoBuffer.append(stringTokenizer.nextToken());

			}

		}

		return textoBuffer.toString();
	}

	/**
	 * Verifica se um campo possui caracteres especiais proibidos
	 * 
	 * @param texto
	 *            O texto a ser verificado
	 * @param tipoCampo
	 *            pode ser um campo do tipo "texto" ou "senha"
	 * @return true, se o campo possui caracteres especiais - false, se não
	 *         possuir caracteres especiais
	 */

	public static boolean possuiCaracteresEspeciais(String texto, int tipoCampo) {

		String[] indesejaveis = null;

		if (tipoCampo == IoUtil.CAMPO_SENHA) {
			indesejaveis = new String[] { "'", "\"", "<", ">", "*", ";" };
		} else if (tipoCampo == IoUtil.CAMPO_TEXTO) {
			indesejaveis = new String[] { "~", "{", "}", "^", "%", "$", "[",
					"]", "@", "|", "`", "\\", "#", "?", "!", "'", ";", "*",
					"'", "<", ">", "\"" };
		} else if (tipoCampo == IoUtil.CAMPO_EMAIL) {
			indesejaveis = new String[] { "~", "{", "}", "^", "%", "$", "[",
					"]", "|", "`", "\\", "#", "?", "!", "'", ";", "*", "'",
					"<", ">", "\"" };
		}

		String delimitador;

		StringBuffer textoBuffer = new StringBuffer(texto);

		for (int i = 0; i < indesejaveis.length; i++) {

			delimitador = indesejaveis[i];

			StringTokenizer stringTokenizer = new StringTokenizer(textoBuffer
					.toString(), delimitador);

			textoBuffer = new StringBuffer();

			while (stringTokenizer.hasMoreElements()) {

				textoBuffer.append(stringTokenizer.nextToken());

			}

		}

		return !textoBuffer.toString().equals(texto);
	}

	/**
	 * Valida uma data informada pelo usuário
	 * 
	 * @param dia
	 *            Descrição do parâmetro
	 * @param mes
	 *            Descrição do parâmetro
	 * @param ano
	 *            Descrição do parâmetro
	 * @return true se a data for válida, false caso contrário
	 */
	public static boolean validaData(int dia, int mes, int ano) {
		boolean retorno = true;

		if (!((0 < mes) && (mes <= 12) && (dia > 0) && (dia <= numeroDeDiasDoMes(
				mes, ano)))) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param mes
	 *            Descrição do parâmetro
	 * @param ano
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private static int numeroDeDiasDoMes(int mes, int ano) {
		int retorno = -1;
		GregorianCalendar calendar = new GregorianCalendar();

		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8
				|| mes == 10 || mes == 12) {
			retorno = 31;
		} else if (mes == 2) {
			if (calendar.isLeapYear(ano)) {
				retorno = 29;
			} else {
				retorno = 28;
			}
		} else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			retorno = 30;
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param data
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public static String formatarDataBD(Date data) {
		Calendar dataCalendar = new GregorianCalendar();
		StringBuffer dataBD = new StringBuffer();

		dataCalendar.setTime(data);
		dataBD.append("(CONVERT(DATETIME, '");
		dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH) + "/");
		// Obs.: Janeiro no Calendar é mês zero
		dataBD.append(dataCalendar.get(Calendar.MONTH) + 1 + "/");
		dataBD.append(dataCalendar.get(Calendar.YEAR) + "', 103))");

		return dataBD.toString();
	}

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mês
	 * @param ano
	 *            O ano
	 * @return Uma instância da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, 0, 0, 0);
		return calendario.getTime();
	}

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mês
	 * @param ano
	 *            O ano
	 * @param hora
	 *            A hora
	 * @param minuto
	 *            Os minutos
	 * @return Uma instância da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano, int hora, int minuto) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, hora, minuto, 0);
		return calendario.getTime();
	}

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mês
	 * @param ano
	 *            O ano
	 * @param hora
	 *            A hora
	 * @param minutos
	 *            Descrição do parâmetro
	 * @param segundos
	 *            Os segundos
	 * @return Uma instância da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano, int hora,
			int minutos, int segundos) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, hora, minutos, segundos);
		return calendario.getTime();
	}

	public static long diferencaEntreDatas(Date dataInicial, Date dataFinal) {

		long diff = dataFinal.getTime() - dataInicial.getTime();

		BigDecimal numerador = new BigDecimal("" + diff);
		long multiplicacao = 1000 * 60 * 60 * 24;
		BigDecimal denominador = new BigDecimal("" + multiplicacao);

		BigDecimal resultado = numerador.divide(denominador, 2,
				BigDecimal.ROUND_HALF_UP);

		return resultado.longValue();

	}

	public static int arredondamentoNumero(double numero) {

		BigDecimal arredondamento = new BigDecimal(numero);

		arredondamento = arredondamento.setScale(0, BigDecimal.ROUND_HALF_UP);

		return arredondamento.intValue();
	}

	public static Date adicionarDias(Date data, int numeroDias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DATE, numeroDias);
		return cal.getTime();

	}

	/**
	 * The main program for the IOUtil class
	 * 
	 * @param args
	 *            The command line arguments
	 */
	public static void main(String[] args) {

		Calendar cal = new GregorianCalendar(2004, 1, 1);

		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.println(days);

	}

	/**
	 * Deleta um diretório do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param dir
	 *            Diretório a ser deletado
	 * @return Booleano indicando se a operação teve sucesso ou não
	 */
	public static boolean deleteDir(File dir) {

		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		return dir.delete();
	}

	/**
	 * Função que transforma um objeto em um byte[], usado para campos BLOB do
	 * banco de dados
	 * 
	 * @author Rodrigo Silveira
	 * @date 21/08/2006
	 * 
	 * @param raw
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static byte[] transformarObjetoParaBytes(Object objeto)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(objeto);
		byte[] retorno = baos.toByteArray(); 
		oos.close();
		baos.close();
		return retorno;
	}

	/**
	 * Função que transforma um byte[] em um objeto, usado para campos BLOB do
	 * banco de dados
	 * 
	 * @author Rodrigo Silveira
	 * @date 21/08/2006
	 * 
	 * @param raw
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object transformarBytesParaObjeto(byte raw[])
			throws IOException, ClassNotFoundException {
		Object retorno = null;
		if (raw != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(raw);
			ObjectInputStream ois = new ObjectInputStream(bais);
			retorno = ois.readObject();
			ois.close();
			bais.close();
		}
		return retorno;
	}
	
	/**
	 * Função que cria um diretório no sistema de arquivos
	 * 
	 * @author Rodrigo Silveira
	 * @date 15/05/2008
	 * 
	 * @param nomeDiretorio Nome do diretório
	 */
	
	public static void criarDiretorio(String nomeDiretorio) {
		(new File(nomeDiretorio)).mkdir();
		
	}
	
	
	/**
	 * Função que cria vários diretórios a partir de um caminho informado
	 * 
	 * @author Rodrigo Silveira
	 * @date 15/05/2008
	 * 
	 * @param caminhoSubDiretorios Ex.: dir1/dir2/dir3
	 */
	public static void criarSubDiretorios(String caminhoSubDiretorios) {
		(new File(caminhoSubDiretorios)).mkdirs();
		
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

 
	/**
	 * 
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Francisco do Nascimento
	 * @date 29/01/2009
	 *
	 * @param is
	 */
	public static void fecharStream(InputStream is){
		if (is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * Método para fechar conexoes do tipo Stream  
	 *
	 * @author Francisco do Nascimento
	 * @date 29/01/2009
	 *
	 * @param is
	 */
	public static void fecharStream(OutputStream os){
		if (os != null){
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * Método para fechar conexoes do tipo Stream  
	 *
	 * @author Francisco do Nascimento
	 * @date 29/01/2009
	 *
	 * @param is
	 */
	public static void fecharStream(Writer w){
		if (w != null){
			try {
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * Método para fechar conexoes do tipo Stream  
	 *
	 * @author Francisco do Nascimento
	 * @date 29/01/2009
	 *
	 * @param is
	 */
	public static void fecharStream(Reader r){
		if (r != null){
			try {
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
}
