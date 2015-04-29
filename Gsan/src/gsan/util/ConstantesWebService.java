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
package gsan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Esta classe � usada para guardar todos os valores que devem ser constantes
 * durante a execu��o do sistema. O carregamento das constantes � feita
 * automaticamente durante o classloader desta classe. Ap�s esta inicializa��o,
 * que � feita antes de qualquer acesso a classe, as constantes podem ser
 * consultadas de forma muito simples. Para o funcionamento desejado o usu�rio
 * deve p�r um arquivo chamado application.properties no mesmo diret�rio onde
 * localiza-se esta classe, este arquivo deve conter todos os nomes das
 * constantes do sistemas juntamente com os seus valores como pode ser visto
 * abaixo.
 * 
 * <pre>
 * 
 *  
 *  # Arquivo de Configura��es das constantes do sistema
 *  SERVER = www.compesa.com.br
 *  PORT = 8080
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *   @author   Rodrigo Cabral
 * 
 * 
 */

public class ConstantesWebService {

    // Nome do Arquivo de propriedades
    private final static String NOME_ARQUIVO_PROPRIEDADES;

    // guarda as constantes contidas no application.properties juntamente com
    // seus valores
    private static Properties propriedades = null;

    // inicializa��o est�tica
    static {
        // Nome do Arquivo de propriedades
        NOME_ARQUIVO_PROPRIEDADES = "/webservice.properties";
        loadResources();
    }

    
    public static String SENHA_CDL = get("webservice.senha");
    

    public static String get(String key) {
        return propriedades.getProperty(key);
    }
    
    /**
     * Este m�todo retorna o valor da constante passada como par�metro.
     * 
     * @param key
     *            Nome da constante
     * @param paramtros lista de parametros a serem substuidos na string
     * @return Descri��o do retorno
     */

    public static String get(String key, String... parametros) {
        String retorno = propriedades.getProperty(key);
        
        int i = 0;
        
        for ( String parametro : parametros ){        	
        	retorno = retorno.replace( "{" + i + "}", parametro );        	
        	++i;
        }
        
        return retorno;        
    }
    

    /**
     * Carrega o arquivo de properties do sistema
     */
    private static void loadResources() {
        propriedades = new Properties();

        InputStream stream;

        try {

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            stream = classLoader.getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);

            // if system class loader not found try the this class classLoader
            if (stream == null) {
                stream = gsan.util.ConstantesWebService.class.getClassLoader()
                        .getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);
            }

            if (stream == null) {
                stream = gsan.util.ConstantesWebService.class
                        .getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);
            }

            propriedades.load(stream);

        } catch (IOException e) {
            e.printStackTrace();
            System.err
                    .println("Nao foi possivel localizar o arquivo de propriedades. Certifique-se "
                            + "de que o arquivo "
                            + NOME_ARQUIVO_PROPRIEDADES
                            + " esteja na raiz do CLASSPATH");

        }

    }

}
