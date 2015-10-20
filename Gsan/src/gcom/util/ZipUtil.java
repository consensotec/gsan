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
package gcom.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Esta classe re�ne fun��es que manipulam um arquivo zip no sistema
 * 
 * @author Rodrigo Silveira
 * @date 19/05/2006
 */
public class ZipUtil {

	/**
	 * Adiciona o arquivo especificado ao zipOutputStream que representa o
	 * arquivo zip
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param zipFile
	 *            Stream que representa o arquivo zip
	 * @param file
	 *            Arquivo a ser adicionado no arquivo zip
	 * @throws IOException
	 */
	public static void adicionarArquivo(ZipOutputStream zipFile, File file)
			throws IOException {

		FileInputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int INPUT_BUFFER_SIZE = 1024;
		byte[] temp = new byte[INPUT_BUFFER_SIZE];
		int numBytesRead = 0;

		while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
			baos.write(temp, 0, numBytesRead);
		}

		inputStream.close();
		inputStream = null;

		byte[] data = baos.toByteArray();

		ZipEntry zen = new ZipEntry(file.getName());
		zipFile.putNextEntry(zen);
		zipFile.write(data, 0, data.length);
		zipFile.closeEntry();
	}

	/**
	 * Adiciona um diret�rio a um arquivo zip especificado
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param dir2zip
	 *            Diret�rio que ser� adicionado ao arquivo zip
	 * @param zos
	 *            Stream que representa o arquivo zip
	 * @throws IOException
	 */
	public static void adicionarPasta(File dir2zip, ZipOutputStream zos)
			throws IOException {

		String[] dirList = dir2zip.list();
		byte[] readBuffer = new byte[2156];
		int bytesIn = 0;

		for (int i = 0; i < dirList.length; i++) {
			File f = new File(dir2zip, dirList[i]);
			if (f.isDirectory()) {
				adicionarPasta(f, zos);

				continue;
			}
			FileInputStream fis = new FileInputStream(f);
			ZipEntry anEntry = new ZipEntry(f.getPath());

			zos.putNextEntry(anEntry);

			while ((bytesIn = fis.read(readBuffer)) != -1) {
				zos.write(readBuffer, 0, bytesIn);
			}

			fis.close();
		}

	}
	
	/*
	  Adiciona o arquivo especificado ao zipOutputStream que representa o
	 * arquivo zip
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param zipFile
	 *            Stream que representa o arquivo zip
	 * @param file
	 *            Arquivo a ser adicionado no arquivo zip
	 * @throws IOException
	 */
	public static void adicionarArquivo(GZIPOutputStream zipFile, File file)
			throws IOException {

		FileInputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int INPUT_BUFFER_SIZE = 1024;
		byte[] temp = new byte[INPUT_BUFFER_SIZE];
		int numBytesRead = 0;

		while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
			baos.write(temp, 0, numBytesRead);
		}

		inputStream.close();
		inputStream = null;

		byte[] data = baos.toByteArray();

//		ZipEntry zen = new ZipEntry(file.getName());
//		zipFile.putNextEntry(zen);
		zipFile.write(data, 0, data.length);
		zipFile.close();
//		zipFile.closeEntry();
	}


}
