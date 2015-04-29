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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * Essa classe tem o papel de fornecer ao sistema servi�os de criptografia da
 * biblioteca java.security
 * 
 * @author Rodrigo Silveira
 */
public final class Criptografia {
    //private static Criptografia instance;

    /**
     * Construtor da classe ServicosCriptografia
     */
    private Criptografia() {
    }

    /**
     * Esse m�todo recebe uma senha digitada pelo usu�rio e aplica um algoritmo
     * de hash(SHA) para tornar a senha criptografada
     * 
     * @param plaintext
     *            Senha digitada pelo usu�rio
     * @return O hash da senha
     * @exception ErroCriptografiaException
     *                Ocorr�ncia de algum erro no mecanismo de criptografia
     */
    public static synchronized String encriptarSenha(String plaintext)
            throws ErroCriptografiaException {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA");

        } catch (NoSuchAlgorithmException e) {
            throw new ErroCriptografiaException(e.getMessage());
        }
        try {
            md.update(plaintext.getBytes("UTF-8"));

        } catch (UnsupportedEncodingException e) {
            throw new ErroCriptografiaException(e.getMessage());
        }

        byte raw[] = md.digest();

        String hash = (new BASE64Encoder()).encode(raw);

        return hash;
    }

    /**
     * Metodo responsavel por encriptar as faixas de leitura
     *
     * [UC0627] Gerar Arquivo Texto Leiturista
     *
     * @author Pedro Alexandre
     * @date 21/09/2007
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        int tab[] = {77,110,70,114,90,100,86,103,111,75};
        int i;
        int value = 0;
        int len = str.length();
        String response = "";

        for (i=0; i < len; i++) {
            value = (int) str.charAt(i);
            response += (char) tab[ (value - 48) ];
        }
       
        return response;
    }

    /**
     * The main program for the Criptografia class
     * 
     * @param args
     *            The command line arguments
     * @exception ErroCriptografiaException
     *                Descri��o da exce��o
     */
    public static void main(String[] args) throws ErroCriptografiaException {
        System.out.print(Criptografia.encriptarSenha("usuario"));
    }
}
