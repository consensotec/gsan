/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestï¿½o de Serviï¿½os de Saneamento
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
* GSAN - Sistema Integrado de Gestï¿½o de Serviï¿½os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araï¿½jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Clï¿½udio de Andrade Lira
* Denys Guimarï¿½es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabï¿½ola Gomes de Araï¿½jo
* Flï¿½vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Jï¿½nior
* Homero Sampaio Cavalcanti
* Ivan Sï¿½rgio da Silva Jï¿½nior
* Josï¿½ Edmar de Siqueira
* Josï¿½ Thiago Tenï¿½rio Lopes
* Kï¿½ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Mï¿½rcio Roberto Batista da Silva
* Maria de Fï¿½tima Sampaio Leite
* Micaela Maria Coelho de Araï¿½jo
* Nelson Mendonï¿½a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrï¿½a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araï¿½jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sï¿½vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa ï¿½ software livre; vocï¿½ pode redistribuï¿½-lo e/ou
* modificï¿½-lo sob os termos de Licenï¿½a Pï¿½blica Geral GNU, conforme
* publicada pela Free Software Foundation; versï¿½o 2 da
* Licenï¿½a.
* Este programa ï¿½ distribuï¿½do na expectativa de ser ï¿½til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implï¿½cita de
* COMERCIALIZAï¿½ï¿½O ou de ADEQUAï¿½ï¿½O A QUALQUER PROPï¿½SITO EM
* PARTICULAR. Consulte a Licenï¿½a Pï¿½blica Geral GNU para obter mais
* detalhes.
* Vocï¿½ deve ter recebido uma cï¿½pia da Licenï¿½a Pï¿½blica Geral GNU
* junto com este programa; se nï¿½o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.util;

import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.gui.ActionServletException;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.validator.GenericValidator;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mortennobel.imagescaling.ResampleOp;

public class Util {

	public static int getMes(Date date) {
		Calendar dataCalendar = GregorianCalendar.getInstance();
		dataCalendar.setTime(date);

		return dataCalendar.get(Calendar.MONTH) + 1;
	}

	public static int getAno(Date date) {
		Calendar dataCalendar = GregorianCalendar.getInstance();
		dataCalendar.setTime(date);

		return dataCalendar.get(Calendar.YEAR);
	}

	public static int getDiaMes(Date date) {
		Calendar dataCalendar = GregorianCalendar.getInstance();
		dataCalendar.setTime(date);

		return dataCalendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Mï¿½todo que retorn o ano mes como Integer
	 * 
	 * @author thiago toscano
	 * @date 05/04/2006
	 * 
	 * @param date
	 * @return
	 */

	public static int getAnoMesComoInt(Date date) {
		return getAnoMesComoInteger(date).intValue();
	}

	/**
	 * Mï¿½todo que retorn o ano mes como Integer
	 * 
	 * @author thiago toscano
	 * @date 05/04/2006
	 * 
	 * @param date
	 * @return
	 */

	public static Integer getAnoMesComoInteger(Date date) {
		int mes = getMes(date);
		String sMes = mes + "";
		if (sMes.length() == 1) {
			sMes = "0" + sMes;
		}
		int ano = getAno(date);

		return new Integer(ano + "" + sMes);
	}

	/*
	 * Formataï¿½ï¿½o para numeraï¿½ï¿½o de RA manual Autor: Raphael Rossiter Data:
	 * 07/11/2006
	 */
	public static String formatarNumeracaoRAManual(Integer numeracao) {

		String retorno = null;

		if (numeracao != null) {

			String sequencialString = String.valueOf(numeracao);
			int digitoModulo11 = Util.obterDigitoVerificadorModulo11(Long
					.parseLong(sequencialString));

			if (sequencialString.length() < 9) {

				int complementoZeros = 9 - sequencialString.length();
				String sequencialStringFinal = sequencialString;

				for (int y = 0; y < complementoZeros; y++) {
					sequencialStringFinal = "0" + sequencialStringFinal;
				}

				retorno = sequencialStringFinal.trim() + "-" + digitoModulo11;
			} else {

				retorno = sequencialString.trim() + "-" + digitoModulo11;
			}
		}
		
		return retorno;
	}

	/*
	 * Obter apenas o valor da numeraï¿½ï¿½o Autor: Raphael Rossiter Data:
	 * 07/11/2006
	 */
	public static Integer obterNumeracaoRAManual(String numeracao) {

		Integer retorno = null;

		if (numeracao != null) {

			String[] arrayNumercao = numeracao.split("-");
			retorno = new Integer(arrayNumercao[0]);
		}

		return retorno;
	}
	
	/**
	 * Mï¿½todo que retorn o ano mes como string
	 * 
	 * @author thiago toscano
	 * @date 05/04/2006
	 * 
	 * @param date
	 * @return
	 */
	public static String getAnoMesComoString(Date date) {
		return getAnoMesComoInteger(date).toString();
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param colecao
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */

	public static Object retonarObjetoDeColecao(Collection<? extends Object> colecao) {

		Object retorno = null;

		if (colecao != null && !colecao.isEmpty()) {

			Iterator<? extends Object> iterator = colecao.iterator();

			while (iterator.hasNext()) {
				retorno = iterator.next();
			}

		}
		return retorno;
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param colecao
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static Object[] retonarObjetoDeColecaoArray(Collection colecao) {

		Object[] retorno = null;

		if (colecao != null && !colecao.isEmpty()) {

			Iterator iterator = colecao.iterator();

			while (iterator.hasNext()) {
				retorno = (Object[]) iterator.next();
			}

		}
		return retorno;
	}

	/**
	 * Description of the Method
	 * 
	 * @param value1
	 *            Description of the Parameter
	 * @param value2
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public static Integer somaInteiros(Integer value1, Integer value2) {
		int v1 = 0;
		int v2 = 0;

		if (value1 != null) {
			v1 = value1.intValue();
		}
		if (value2 != null) {
			v2 = value2.intValue();
		}
		return new Integer(v1 + v2);
	}

	/**
	 * Description of the Method
	 * 
	 * @param value1
	 *            Description of the Parameter
	 * @param value2
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public static BigDecimal somaBigDecimal(BigDecimal value1, BigDecimal value2) {
		
		BigDecimal v1 = BigDecimal.ZERO;
		BigDecimal v2 = BigDecimal.ZERO;

		if (value1 != null) {
			v1 = value1;
		}
		if (value2 != null) {
			v2 = value2;
		}
		return v1.add(v2);
	}

	/**
	 * Description of the Method
	 * 
	 * @param value1
	 *            Description of the Parameter
	 * @param value2
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public static BigDecimal subtrairBigDecimal(BigDecimal value1, BigDecimal value2) {
		
		BigDecimal v1 = BigDecimal.ZERO;
		BigDecimal v2 = BigDecimal.ZERO;

		if (value1 != null) {
			v1 = value1;
		}

		if (value2 != null) {
			v2 = value2;
		}
		
		return v1.subtract(v2);
	}
	
	/**
	 * Subtrai a data no formato AAAAMM Exemplo 200508 retorna 200507
	 */
	public static int subtrairData(int data) {

		String dataFormatacao = "" + data;

		int ano = new Integer(dataFormatacao.substring(0, 4)).intValue();
		int mes = new Integer(dataFormatacao.substring(4, 6)).intValue();

		int mesTemp = mes - 1;

		if (mesTemp == 0) {
			mesTemp = 12;
			ano = ano - 1;
		}

		String anoMes = null;
		String tamanhoMes = "" + mesTemp;

		if (tamanhoMes.length() == 1) {
			anoMes = ano + "0" + mesTemp;
		} else {
			anoMes = ano + "" + mesTemp;
		}
		return new Integer(anoMes).intValue();
	}

	/**
	 * Subtrai a data no formato AAAAMM Exemplo 200508 retorna 200507
	 * 
	 * Author: Sï¿½vio Luiz Data: 20/01/2006
	 * 
	 * @param data
	 *            com a barra
	 * @return Uma data no formato yyyyMM (sem a barra)
	 */
	public static int subtrairMesDoAnoMes(int anoMes, int qtdMeses) {

		String dataFormatacao = "" + anoMes;

        	int ano = new Integer(dataFormatacao.substring(0, 4)).intValue();
	        int mes = new Integer(dataFormatacao.substring(4, 6)).intValue();

	        int qtdAnosDiminuir = qtdMeses / 12;
	        int qtdMesesDiminuir = qtdMeses % 12;
       
	        ano -= qtdAnosDiminuir;
	        mes -= qtdMesesDiminuir;
       
	        if ( mes < 1 ){
        	    --ano;
        	    mes += 12;
	        }
       
	        if ( mes < 10 ){
        	    return Integer.parseInt( ano + "0" + mes );
	        } else {
        	    return Integer.parseInt( ano + "" + mes );
	        }
	}

	public static String formatarMesAnoParaAnoMes(String data) {

		String mes = data.substring(0, 2);
		String ano = data.substring(2, 6);

		return ano + mes;
	}

	public static int formatarMesAnoParaAnoMes(int mesAno) {

		String mesAnoString = "" + mesAno;

		if (mesAnoString.length() > 4) {

			// modificado por sï¿½vio,pois se a data for 012006 ele tira o 0
			// quando ï¿½
			// passado para int e ai dï¿½ estouro.
			if (mesAnoString.length() == 5) {
				mesAnoString = "0" + mesAnoString;
			}
			String mes = mesAnoString.substring(0, 2);
			String ano = mesAnoString.substring(2, 6);
			return Integer.parseInt(ano + mes);
		} else {
			return 0;
		}

	}

	public static Integer formatarMesAnoComBarraParaAnoMes(String mesAno) {

		String mes = mesAno.substring(0, 2);
		String ano = mesAno.substring(3, 7);

		return Integer.parseInt(ano + mes);
	}
	
	public static Integer formatarDiaMesAnoComBarraParaAnoMesDia(String data){
		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6, 10);
		
		return Integer.parseInt(ano + mes + dia);
	}

	/**
	 * @author Raphael Rossiter
	 * @date 20/01/2006
	 * 
	 * @param data com a barra
	 * @return Uma data no formato yyyyMM (sem a barra)
	 */
	public static String formatarMesAnoParaAnoMesSemBarra(String data) {
		if (data == null || data.length() < 7) {
			return null;
		}

		String mes = data.substring(0, 2);
		String ano = data.substring(3, 7);

		return ano + mes;
	}

	public static String formatarAnoMesParaMesAno(int anoMes) {

		String anoMesFormatado = "";
		String anoMesRecebido = "" + anoMes;
		if (anoMesRecebido.length() < 6) {
			anoMesFormatado = anoMesRecebido;
		} else {
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}
		return anoMesFormatado;
	}

	public static String formatarAnoMesParaMesAnoSemBarra(int anoMes) {

		String anoMesFormatado = "";
		String anoMesRecebido = "" + anoMes;
		if (anoMesRecebido.length() < 6) {
			anoMesFormatado = anoMesRecebido;
		} else {
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + ano;
		}
		return anoMesFormatado;
	}

	/**
	 * Formata o anomes para o mesano sem barra e o no sï¿½ com os 2 ultimos
	 * digitos EX.: entrada: 200702 saï¿½da:0207 Autor:Sï¿½vio Luiz
	 */
	public static String formatarAnoMesParaMesAnoCom2Digitos(int anoMes) {

		String anoMesFormatado = "";
		String anoMesRecebido = "" + anoMes;
		if (anoMesRecebido.length() < 6) {
			anoMesFormatado = anoMesRecebido;
		} else {
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(2, 4);
			anoMesFormatado = mes + ano;
		}
		return anoMesFormatado;
	}

	public static String formatarAnoMesParaMesAno(String anoMes) {

		String anoMesFormatado = "";
		String anoMesRecebido = "" + anoMes;
		if (anoMesRecebido.length() < 6) {
			anoMesFormatado = anoMesRecebido;
		} else {
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}
		return anoMesFormatado;
	}

	public static int somarData(int data) {

		String dataFormatacao = "" + data;

		int ano = new Integer(dataFormatacao.substring(0, 4)).intValue();
		int mes = new Integer(dataFormatacao.substring(4, 6)).intValue();

		int mesTemp = mes + 1;

		if (mesTemp == 13) {
			mesTemp = 1;
			ano = ano + 1;
		}

		String anoMes = null;
		String tamanhoMes = "" + mesTemp;

		if (tamanhoMes.length() == 1) {
			anoMes = ano + "0" + mesTemp;
		} else {
			anoMes = ano + "" + mesTemp;
		}
		return new Integer(anoMes).intValue();
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param parametro
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}

	/**
	 * Adiciona zeros a esqueda do nï¿½mero informado tamamho mï¿½ximo campo 6
	 * Nï¿½mero 16 retorna 000016
	 * 
	 * @param tamanhoMaximoCampo
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @param numero
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String adicionarZerosEsquedaNumero(int tamanhoMaximoCampo,
			String numero) {
		String zeros = "";

		String retorno = null;

		if (numero != null && !numero.equals("")) {
			for (int a = 0; a < (tamanhoMaximoCampo - numero.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			retorno = zeros.concat(numero);
		} else {
			for (int a = 0; a < tamanhoMaximoCampo; a++) {
				zeros = zeros.concat("0");
			}
			// retorna os zeros
			// caso o numero seja nulo
			retorno = zeros;
		}

		return retorno;
	}

	/**
	 * Mï¿½todo que converte uma hora passa em Date onde contï¿½m apenas a hora
	 * informada EX: 11:30
	 * 
	 * 
	 * @param horaMinuto
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 * @author thiago toscano
	 */
	public static Date converterStringParaHoraMinuto(String horaMinuto) {

		Date retorno = null;

		// Obtï¿½m a hora
		String hora = horaMinuto.substring(0, 2);
		// Obtï¿½m os minutos
		String minuto = horaMinuto.substring(3, 5);

		// obtï¿½m a data mï¿½nima do mï¿½s selecionado
		Calendar data = Calendar.getInstance();

		// Seta como data atual
		data.setTime(new Date());

		// Seta a hora
		data.set(Calendar.HOUR_OF_DAY, new Integer(hora).intValue());
		data.set(Calendar.MINUTE, new Integer(minuto).intValue());
		data.set(Calendar.SECOND, 0);
		data.set(Calendar.MILLISECOND, 0);

		retorno = data.getTime();

		return retorno;
	}

	/**
	 * Mï¿½todo que converte uma hora passa em Date onde contï¿½m apenas a hora
	 * informada EX: 11:30:52
	 * 
	 * 
	 * @param horaMinutoSegundo
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 * @author fernanda paiva
	 */
	public static Date converterStringParaHoraMinutoSegundo(
			String horaMinutoSegundo) {

		Date retorno = null;

		SimpleDateFormat formatoData = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss");

		String dataCompleta = "01/01/2006 " + horaMinutoSegundo;

		try {
			retorno = formatoData.parse(dataCompleta);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param n
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @param d
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static int dividirArredondarResultado(int n, int d) {

		int retorno = 0;

		BigDecimal numerador = new BigDecimal(n);
		BigDecimal denominador = new BigDecimal(d);

		if (denominador.intValue() != 0) {
			BigDecimal resultado = numerador.divide(denominador,
					BigDecimal.ROUND_HALF_UP);

			retorno = resultado.intValue();
		}

		return retorno;
	}
	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param n
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @param d
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static int dividirArredondarResultadoCima(int n, int d) {

		int retorno = 0;

		BigDecimal numerador = new BigDecimal(n);
		BigDecimal denominador = new BigDecimal(d);

		if (denominador.intValue() != 0) {
			BigDecimal resultado = numerador.divide(denominador,
					BigDecimal.ROUND_UP);

			retorno = resultado.intValue();
		}

		return retorno;
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param numero
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static int arredondar(BigDecimal numero) {

		numero = numero.setScale(0, BigDecimal.ROUND_HALF_UP);

		return numero.intValue();
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param anoMes
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static int obterMes(int anoMes) {

		String dataFormatacao = "" + anoMes;

		int mes = new Integer(dataFormatacao.substring(4, 6)).intValue();

		return mes;
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param anoMes
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static int obterAno(int anoMes) {

		String dataFormatacao = "" + anoMes;

		int ano = new Integer(dataFormatacao.substring(0, 4)).intValue();

		return ano;
	}

	public static int divideDepoisMultiplica(int numerador, int denominador,
			int numeroMultiplicado) {

		BigDecimal n = new BigDecimal(numerador);

		BigDecimal d = new BigDecimal(denominador);

		BigDecimal resultado = n.divide(d, 4, BigDecimal.ROUND_HALF_UP);

		resultado = resultado.multiply(new BigDecimal(numeroMultiplicado));

		return Util.arredondar(resultado);

	}

	/**
	 * Converte a data passada em string
	 * 
	 * @author: Thiago Toscano, Thiago Toscano
	 * @date: 20/03/2006, 20/03/2006
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarData(Date data) {
		String retorno = "";
		if (data != null) { // 1
			Calendar dataCalendar = new GregorianCalendar();
			StringBuffer dataBD = new StringBuffer();

			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH) + "/");
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH)
						+ "/");
			}

			// Obs.: Janeiro no Calendar ï¿½ mï¿½s zero
			if ((dataCalendar.get(Calendar.MONTH) + 1) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH) + 1 + "/");
			} else {
				dataBD.append("0" + (dataCalendar.get(Calendar.MONTH) + 1)
						+ "/");
			}

			dataBD.append(dataCalendar.get(Calendar.YEAR));
			retorno = dataBD.toString();
		}
		return retorno;
	}

	/**
	 * Converte a data passada em string retorna AAAAMMDD
	 * 
	 * @author: Sï¿½vio Luiz
	 * @date: 09/04/2007
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarDataSemBarra(Date data) {
		String retorno = "";
		if (data != null) { // 1
			Calendar dataCalendar = new GregorianCalendar();
			StringBuffer dataBD = new StringBuffer();

			dataCalendar.setTime(data);

			dataBD.append(dataCalendar.get(Calendar.YEAR));

			// Obs.: Janeiro no Calendar ï¿½ mï¿½s zero
			if ((dataCalendar.get(Calendar.MONTH) + 1) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH) + 1);
			} else {
				dataBD.append("0" + (dataCalendar.get(Calendar.MONTH) + 1));
			}

			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH));
			}

			retorno = dataBD.toString();
		}
		return retorno;
	}

	/**
	 * Converte a data passada em string retorna DDMMAAAA
	 * 
	 * @author: Sï¿½vio Luiz
	 * @date: 09/04/2007
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarDataSemBarraDDMMAAAA(Date data) {
		String retorno = "";
		if (data != null) { // 1
			Calendar dataCalendar = new GregorianCalendar();
			StringBuffer dataBD = new StringBuffer();

			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH));
			}

			// Obs.: Janeiro no Calendar ï¿½ mï¿½s zero
			if ((dataCalendar.get(Calendar.MONTH) + 1) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH) + 1);
			} else {
				dataBD.append("0" + (dataCalendar.get(Calendar.MONTH) + 1));
			}

			dataBD.append(dataCalendar.get(Calendar.YEAR));

			retorno = dataBD.toString();
		}
		return retorno;
	}

	/**
	 * Converte a data passada em string retorna DDMMAAAA
	 * 
	 * @author: Sï¿½vio Luiz
	 * @date: 09/04/2007
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarDataComTracoAAAAMMDD(Date data) {
		String retorno = "";
		if (data != null) { // 1
			Calendar dataCalendar = new GregorianCalendar();
			StringBuffer dataBD = new StringBuffer();
			
			dataCalendar.setTime(data);
			
			dataBD.append(dataCalendar.get(Calendar.YEAR));			
			dataBD.append("-");
			
			// Obs.: Janeiro no Calendar ï¿½ mï¿½s zero
			if ((dataCalendar.get(Calendar.MONTH) + 1) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH) + 1);
			} else {
				dataBD.append("0" + (dataCalendar.get(Calendar.MONTH) + 1));
			}
			dataBD.append("-");
			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH));
			}

			retorno = dataBD.toString();
		}
		return retorno;
	}

	/**
	 * Converte a data passada em string retorna DDMMAAAA
	 * 
	 * @author: Thiago Toscano
	 * @date: 13/02/2008
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarDataAAAAMMDD(Date data) {
		String retorno = "";
		if (data != null) { // 1
			Calendar dataCalendar = new GregorianCalendar();
			StringBuffer dataBD = new StringBuffer();
			
			dataCalendar.setTime(data);
			
			dataBD.append(dataCalendar.get(Calendar.YEAR));			
//			dataBD.append("-");
			
			// Obs.: Janeiro no Calendar ï¿½ mï¿½s zero
			if ((dataCalendar.get(Calendar.MONTH) + 1) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH) + 1);
			} else {
				dataBD.append("0" + (dataCalendar.get(Calendar.MONTH) + 1));
			}
//			dataBD.append("-");
			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH));
			}

			retorno = dataBD.toString();
		}
		return retorno;
	}

	/**
	 * Monta um data inicial com hora,minuto e segundo zerados para pesquisa no
	 * banco
	 * 
	 * @author: Rafael Pinto
	 * @date: 19/10/2006
	 * 
	 * @param Date
	 * 
	 * @return dataInicial
	 */
	public static Date formatarDataInicial(Date dataInicial) {

		Calendar calendario = GregorianCalendar.getInstance();

		calendario.setTime(dataInicial);
		
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.MINUTE, 0);
		calendario.set(Calendar.SECOND, 0);

		return calendario.getTime();
	}

	/**
	 * Monta um data inicial com hora,minuto e segundo zerados para pesquisa no
	 * banco
	 * 
	 * @author: Rafael Pinto
	 * @date: 19/10/2006
	 * 
	 * @param Date
	 * 
	 * @return dataInicial
	 */
	public static Date formatarDataFinal(Date dataFinal) {

		Calendar calendario = Calendar.getInstance();

		calendario.setTime(dataFinal);

		calendario.set(Calendar.HOUR_OF_DAY, 23);
		calendario.set(Calendar.MINUTE, 59);
		calendario.set(Calendar.SECOND, 59);

		return calendario.getTime();
	}

	/**
	 * Converte a data passada para o formato "DD/MM/YYYY"
	 * 
	 * @author: Raphael Rossiter
	 * @date: 22/03/2006
	 * 
	 * @param String
	 *            data no formato "YYYYMMDD"
	 * 
	 * @return String data no formato "DD/MM/YYYY"
	 */
	public static String formatarData(String data) {
		String retorno = "";

		if (data != null && !data.equals("") && data.trim().length() == 8) {

			retorno = data.substring(6, 8) + "/" + data.substring(4, 6) + "/"
					+ data.substring(0, 4);

		}

		return retorno;
	}
	
	/**
	 * Converte a data passada para o formato "DD/MM/YYYY"
	 * 
	 * @author: Rafael Pinto
	 * @date: 22/01/2008
	 * 
	 * @param String data no formato "YYYYMMDD"
	 * @return String data no formato "DD/MM/YYYY"
	 */
	public static String converterDataSemBarraParaDataComBarra(String data) {
		String retorno = "";

		if (data != null && !data.equals("") && data.trim().length() == 8) {

			retorno = data.substring(0, 2) 
				+ "/" 
				+ data.substring(2, 4) 
				+ "/" 
				+ data.substring(4, 8);

		}

		return retorno;
	}	

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarDataComHora(Date data) {
		StringBuffer dataBD = new StringBuffer();

		if (data != null) {
			Calendar dataCalendar = new GregorianCalendar();

			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH) + "/");
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH)
						+ "/");
			}

			// Obs.: Janeiro no Calendar ï¿½ mï¿½s zero
			if ((dataCalendar.get(Calendar.MONTH) + 1) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH) + 1 + "/");
			} else {
				dataBD.append("0" + (dataCalendar.get(Calendar.MONTH) + 1)
						+ "/");
			}

			dataBD.append(dataCalendar.get(Calendar.YEAR));

			dataBD.append(" ");

			if (dataCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
				dataBD.append(dataCalendar.get(Calendar.HOUR_OF_DAY));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.HOUR_OF_DAY));
			}

			dataBD.append(":");

			if (dataCalendar.get(Calendar.MINUTE) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MINUTE));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.MINUTE));
			}

			dataBD.append(":");

			if (dataCalendar.get(Calendar.SECOND) > 9) {
				dataBD.append(dataCalendar.get(Calendar.SECOND));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.SECOND));
			}
		}

		return dataBD.toString();
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarDataHHMMSS(Date data) {
		StringBuffer dataBD = new StringBuffer();

		if (data != null) {
			Calendar dataCalendar = new GregorianCalendar();

			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
				dataBD.append(dataCalendar.get(Calendar.HOUR_OF_DAY));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.HOUR_OF_DAY));
			}

			if (dataCalendar.get(Calendar.MINUTE) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MINUTE));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.MINUTE));
			}

			dataBD.append(":");

			if (dataCalendar.get(Calendar.SECOND) > 9) {
				dataBD.append(dataCalendar.get(Calendar.SECOND));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.SECOND));
			}
		}

		return dataBD.toString();
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarDataHHMM(Date data) {
		StringBuffer dataBD = new StringBuffer();

		if (data != null) {
			Calendar dataCalendar = new GregorianCalendar();

			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
				dataBD.append(dataCalendar.get(Calendar.HOUR_OF_DAY));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.HOUR_OF_DAY));
			}

			if (dataCalendar.get(Calendar.MINUTE) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MINUTE));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.MINUTE));
			}
		}

		return dataBD.toString();
	}

/**
	 * Compara dois objetos no formato anoMesReferencia de acordo com o sinal
	 * logico passado.
	 * 
	 * @param anoMesReferencia1
	 * @param anoMesReferencia2
	 * @param sinal
	 * @return um boleano
	 */
	public static boolean compararAnoMesReferencia(Integer anoMesReferencia1,
			Integer anoMesReferencia2, String sinal) {
		boolean retorno = true;

		// Separando os valores de mï¿½s e ano para realizar a comparaï¿½ï¿½o
		String mesReferencia1 = String.valueOf(anoMesReferencia1.intValue())
				.substring(4, 6);
		String anoReferencia1 = String.valueOf(anoMesReferencia1.intValue())
				.substring(0, 4);

		String mesReferencia2 = String.valueOf(anoMesReferencia2.intValue())
				.substring(4, 6);
		String anoReferencia2 = String.valueOf(anoMesReferencia2.intValue())
				.substring(0, 4);

		if (sinal.equalsIgnoreCase("=")) {
			if (!Integer.valueOf(anoReferencia1).equals(
					Integer.valueOf(anoReferencia2))) {
				retorno = false;
			} else if (!Integer.valueOf(mesReferencia1).equals(
					Integer.valueOf(mesReferencia2))) {
				retorno = false;
			}
		} else if (sinal.equalsIgnoreCase(">")) {
			if (Integer.valueOf(anoReferencia1).intValue() < Integer.valueOf(
					anoReferencia2).intValue()) {
				retorno = false;
			} else if (Integer.valueOf(anoReferencia1).equals(
					Integer.valueOf(anoReferencia2))
					&& Integer.valueOf(mesReferencia1).intValue() <= Integer
							.valueOf(mesReferencia2).intValue()) {
				retorno = false;
			}
		} else {
			if (Integer.valueOf(anoReferencia2).intValue() < Integer.valueOf(
					anoReferencia1).intValue()) {
				retorno = false;
			} else if (Integer.valueOf(anoReferencia2).equals(
					Integer.valueOf(anoReferencia1))
					&& Integer.valueOf(mesReferencia2).intValue() <= Integer
							.valueOf(mesReferencia1).intValue()) {
				retorno = false;
			}
		}

		return retorno;
	}

	/**
	 * Compara dois objetos no formato anoMesReferencia de acordo com o sinal
	 * logico passado.
	 * 
	 * @param anoMesReferencia1
	 * @param anoMesReferencia2
	 * @param sinal
	 * @return um boleano
	 */
	public static boolean compararAnoMesReferencia(String anoMesReferencia1,
			String anoMesReferencia2, String sinal) {
		boolean retorno = true;

		// Separando os valores de mï¿½s e ano para realizar a comparaï¿½ï¿½o
		String mesReferencia1 = String.valueOf(anoMesReferencia1).substring(4,
				6);
		String anoReferencia1 = String.valueOf(anoMesReferencia1).substring(0,
				4);

		String mesReferencia2 = String.valueOf(anoMesReferencia2).substring(4,
				6);
		String anoReferencia2 = String.valueOf(anoMesReferencia2).substring(0,
				4);

		if (sinal.equalsIgnoreCase("=")) {
			if (!Integer.valueOf(anoReferencia1).equals(
					Integer.valueOf(anoReferencia2))) {
				retorno = false;
			} else if (!Integer.valueOf(mesReferencia1).equals(
					Integer.valueOf(mesReferencia2))) {
				retorno = false;
			}
		} else if (sinal.equalsIgnoreCase(">")) {
			if (Integer.valueOf(anoReferencia1).intValue() < Integer.valueOf(
					anoReferencia2).intValue()) {
				retorno = false;
			} else if (Integer.valueOf(anoReferencia1).equals(
					Integer.valueOf(anoReferencia2))
					&& Integer.valueOf(mesReferencia1).intValue() <= Integer
							.valueOf(mesReferencia2).intValue()) {
				retorno = false;
			}
		} else {
			if (Integer.valueOf(anoReferencia2).intValue() < Integer.valueOf(
					anoReferencia1).intValue()) {
				retorno = false;
			} else if (Integer.valueOf(anoReferencia2).equals(
					Integer.valueOf(anoReferencia1))
					&& Integer.valueOf(mesReferencia2).intValue() <= Integer
							.valueOf(mesReferencia1).intValue()) {
				retorno = false;
			}
		}

		return retorno;
	}

	/**
	 * Compara dois objetos no formato HH:MM de acordo com o sinal logico
	 * passado.
	 * 
	 * @param horaMinuto1
	 * @param horaMinuto2
	 * @param sinal
	 * @return um boleano
	 */
	public static boolean compararHoraMinuto(String horaMinuto1,
			String horaMinuto2, String sinal) {

		boolean retorno = true;

		// Separando os valores de hora e minuto para realizar a comparaï¿½ï¿½o
		String hora1 = horaMinuto1.substring(0, 2);
		String minuto1 = horaMinuto1.substring(3, 5);

		String hora2 = horaMinuto2.substring(0, 2);
		String minuto2 = horaMinuto2.substring(3, 5);

		if (sinal.equalsIgnoreCase("=")) {
			if (!Integer.valueOf(hora1).equals(Integer.valueOf(hora2))) {
				retorno = false;
			} else if (!Integer.valueOf(minuto1).equals(
					Integer.valueOf(minuto2))) {
				retorno = false;
			}
		} else if (sinal.equalsIgnoreCase(">")) {
			if (Integer.valueOf(hora1).intValue() < Integer.valueOf(hora2)
					.intValue()) {
				retorno = false;
			} else if (Integer.valueOf(hora1).equals(Integer.valueOf(hora2))
					&& Integer.valueOf(minuto1).intValue() <= Integer.valueOf(
							minuto2).intValue()) {
				retorno = false;
			}
		} else {
			if (Integer.valueOf(hora2).intValue() < Integer.valueOf(hora1)
					.intValue()) {
				retorno = false;
			} else if (Integer.valueOf(hora2).equals(Integer.valueOf(hora1))
					&& Integer.valueOf(minuto2).intValue() <= Integer.valueOf(
							minuto1).intValue()) {
				retorno = false;
			}
		}

		return retorno;
	}

	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarHoraSemData(Date data) {
		StringBuffer dataBD = new StringBuffer("");

		if (data != null) {

			Calendar dataCalendar = new GregorianCalendar();
			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
				dataBD.append(dataCalendar.get(Calendar.HOUR_OF_DAY));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.HOUR_OF_DAY));
			}

			dataBD.append(":");

			if (dataCalendar.get(Calendar.MINUTE) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MINUTE));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.MINUTE));
			}

			dataBD.append(":");

			if (dataCalendar.get(Calendar.SECOND) > 9) {
				dataBD.append(dataCalendar.get(Calendar.SECOND));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.SECOND));
			}

		}

		return dataBD.toString();
	}
	
	public static String formatarHoraSemDataSemDoisPontos(Date data) {
		StringBuffer dataBD = new StringBuffer("");

		if (data != null) {

			Calendar dataCalendar = new GregorianCalendar();
			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
				dataBD.append(dataCalendar.get(Calendar.HOUR_OF_DAY));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.HOUR_OF_DAY));
			}

			dataBD.append("");

			if (dataCalendar.get(Calendar.MINUTE) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MINUTE));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.MINUTE));
			}

			dataBD.append("");

			if (dataCalendar.get(Calendar.SECOND) > 9) {
				dataBD.append(dataCalendar.get(Calendar.SECOND));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.SECOND));
			}

		}

		return dataBD.toString();
	}

	/**
	 * Obter Dï¿½gito Verificador Mï¿½dulo CAERN Author : Rafael Francisco Pinto
	 * Data : 13/04/2007
	 * 
	 * Calcula o dï¿½gito verificador do cï¿½digo de barras no mï¿½dulo caern
	 * 
	 * @param numero
	 *            Nï¿½mero do cï¿½digo de barra para calcular o dï¿½gito veficador
	 * @return digito verificador do mï¿½dulo caern
	 */
	public static Integer obterDigitoVerificadorModuloCAERN(String numero) {

		String entradaString = adicionarZerosEsquedaNumero(6, numero);

		Integer digitoCalculo = (new Integer(entradaString.substring(0, 1)) * 35)
				+ (new Integer(entradaString.substring(1, 2)) * 31)
				+ (new Integer(entradaString.substring(2, 3)) * 29)
				+ (new Integer(entradaString.substring(3, 4)) * 23)
				+ (new Integer(entradaString.substring(4, 5)) * 19)
				+ (new Integer(entradaString.substring(5, 6)) * 17);

		Integer resultado = digitoCalculo / 11;
		Integer restoDigito = digitoCalculo - (resultado * 11);

		/*
		 * Colocado por Raphael Rossiter em 03/04/2007 (Analista: Eduardo
		 * Borges) Caso o dï¿½gito seja igual a 10 o retorno serï¿½ zero
		 */
		if (restoDigito > 9) {
			restoDigito = 0;
		}

		return restoDigito;
	}

	/**
	 * [UC0261] - Obter Dï¿½gito Verificador Mï¿½dulo 11 Author : Pedro Alexandre
	 * Data : 15/02/2006
	 * 
	 * Calcula o dï¿½gito verificador do cï¿½digo de barras no mï¿½dulo 11(onze)
	 * 
	 * @param numero
	 *            Nï¿½mero do cï¿½digo de barra no formato long para calcular o
	 *            dï¿½gito veficador
	 * @return digito verificador do mï¿½dulo 11(onze)
	 */
	public static Integer obterDigitoVerificadorModulo11(Long numero) {

		// converte o nï¿½mero recebido para uma string
		String entradaString = String.valueOf(numero);

		// inicia o sequï¿½ncial de multiplicaï¿½ï¿½o para 2(dois)
		int sequencia = 2;

		// cria as variï¿½veis que serï¿½o utilizadas no calculo
		int digito, contAuxiliar;

		// variï¿½vel que vai armazenar a soma da mï¿½ltiplicaï¿½ï¿½o de cada dï¿½gito
		int somaDigitosProduto = 0;

		// contador auxiliar
		contAuxiliar = 1;

		// laï¿½o para calcular a soma da mï¿½ltiplicaï¿½ï¿½o de cada dï¿½gito
		for (int i = 0; i < entradaString.length(); i++) {

			// recupera o dï¿½gito da string
			digito = new Integer(entradaString.substring(entradaString.length()
					- contAuxiliar, entradaString.length() - i)).intValue();

			// multiplica o digito pelo sequï¿½ncia e acumula o resultado
			somaDigitosProduto = somaDigitosProduto + (digito * sequencia);

			// se osequï¿½ncia for igual a 9(nove)
			if (sequencia == 9) {
				// a sequï¿½ncia volta para 2(dois)
				sequencia = 2;
			} else {
				// incrementa a sequï¿½ncia mais 1
				++sequencia;
			}

			// incrementa o contador auxiliar
			contAuxiliar++;
		}

		// calcula o resto da divisï¿½o
		int resto = somaDigitosProduto % 11;

		// variï¿½vel que vai armazenar o dï¿½gito verificador
		int dac;

		// se o resto for 0(zero) ou 1(1)
		if (resto == 0 || resto == 1) {
			// o dï¿½gito verificador vai ser 0(zero)
			dac = 0;
		} else if (resto == 10) {
			// o dï¿½gito verificador vai ser 1(um)
			dac = 1;
		} else {
			// o dï¿½gito verificador vai ser a diferenï¿½a
			dac = 11 - resto;
		}
		// retorna o dï¿½gito verificador calculado
		return new Integer(dac);
	}

	/**
	 * [UC0261] - Obter Dï¿½gito Verificador Mï¿½dulo 11 Author : Pedro Alexandre
	 * Data : 15/02/2006
	 * 
	 * Calcula o dï¿½gito verificador do cï¿½digo de barras no mï¿½dulo 11(onze)
	 * 
	 * @param numero
	 *            Nï¿½mero do cï¿½digo de barra no formato string para calcular o
	 *            dï¿½gito veficador
	 * @return digito verificador do mï¿½dulo 11(onze)
	 */
	public static Integer obterDigitoVerificadorModulo11(String numero) {

		String wnumero = numero;
		int param = 2;
		int soma = 0;

		for (int ind = wnumero.length() - 1; ind >= 0; ind--) {
			if (param > 9) {
				param = 2;
			}
			soma = soma
					+ (Integer.parseInt(wnumero.substring(ind, ind + 1)) * param);
			param = param + 1;
		}

		int resto = soma % 11;
		int dv;

		if ((resto == 0) || (resto == 1)) {
			dv = 0;
		} else {
			dv = 11 - resto;
		}
		return dv;
		/*
		 * // converte o nï¿½mero recebido para uma string String entradaString =
		 * numero; // inicia o sequï¿½ncial de multiplicaï¿½ï¿½o para 2(dois) int
		 * sequencia = 2; // cria as variï¿½veis que serï¿½o utilizadas no calculo
		 * int digito, contAuxiliar; // variï¿½vel que vai armazenar a soma da
		 * mï¿½ltiplicaï¿½ï¿½o de cada dï¿½gito int somaDigitosProduto = 0; // contador
		 * auxiliar contAuxiliar = 1; // laï¿½o para calcular a soma da
		 * mï¿½ltiplicaï¿½ï¿½o de cada dï¿½gito for (int i = 0; i <
		 * entradaString.length(); i++) { // recupera o dï¿½gito da string digito =
		 * new Integer(entradaString.substring(entradaString.length() -
		 * contAuxiliar, entradaString.length() - i)).intValue(); // multiplica
		 * o digito pelo sequï¿½ncia e acumula o resultado somaDigitosProduto =
		 * somaDigitosProduto + (digito * sequencia); // se osequï¿½ncia for igual
		 * a 9(nove) if (sequencia == 9) { // a sequï¿½ncia volta para 2(dois)
		 * sequencia = 2; } else { // incrementa a sequï¿½ncia mais 1 ++sequencia; } //
		 * incrementa o contador auxiliar contAuxiliar++; } // calcula o resto
		 * da divisï¿½o int resto = (somaDigitosProduto % 11); // variï¿½vel que vai
		 * armazenar o dï¿½gito verificador int dac; // se o resto for 0(zero) ou
		 * 1(1) if (resto == 0 || resto == 1) { // o dï¿½gito verificador vai ser
		 * 0(zero) dac = 0; } else if (resto == 10) { // o dï¿½gito verificador
		 * vai ser 1(um) dac = 1; } else { // o dï¿½gito verificador vai ser a
		 * diferenï¿½a dac = 11 - resto; } // retorna o dï¿½gito verificador
		 * calculado return new Integer(dac);
		 */
	}
	
	/**
	 * Mï¿½todo que recebe uma string ex."123456" e converte para um tipo
	 * Float ex. "123456".
	 * 
	 * 354654564,12 = 354654564.12 354.654.564,12 = 354654564.12 35465456412 =
	 * 35465456412.00 354654564.12 = 354654564.12 354654564,12 = 354654564.12
	 * 
	 * @param data
	 * @autor Anderson cabral
	 * @date 22/06/2012
	 * @return
	 */
	
	public static Float formatarMoedaRealparaFloat(String valor) {
		Float floatFormatado = new Float("0");

		if (valor != null) {
			valor = valor.trim();

			boolean negativo = false;
			if (valor.startsWith("-")) {
				negativo = true;
			}

			boolean temCasaDecimal = false;
			if (valor.length() > 2
					&& (valor.substring(valor.length() - 3, valor.length() - 2)
							.equals(".") || valor.substring(valor.length() - 3,
							valor.length() - 2).equals(","))) {
				temCasaDecimal = true;
			}

			String valorSemPontuacao = "";
			// metodo que tira todos os pontos no meio da string
			for (int i = 0; i < valor.length(); i++) {
				try {
					Integer.parseInt(valor.substring(i, i + 1));
					valorSemPontuacao = valorSemPontuacao
							+ valor.substring(i, i + 1);
				} catch (Exception e) {
				}
			}
			if (temCasaDecimal) {
				int tamanho = valorSemPontuacao.length();
				valorSemPontuacao = valorSemPontuacao.substring(0, tamanho - 2)
						+ "."
						+ valorSemPontuacao.substring(tamanho - 2, tamanho);
			}
			if (negativo) {
				valorSemPontuacao = "-" + valorSemPontuacao;
			}
			
			floatFormatado = new Float(valorSemPontuacao);
		}

		return floatFormatado;
	}

	/**
	 * Mï¿½todo que recebe uma string ex."123456" e converte para o objeto
	 * BigDecimal ex. "123456".
	 * 
	 * 354654564,12 = 354654564.12 354.654.564,12 = 354654564.12 35465456412 =
	 * 35465456412.00 354654564.12 = 354654564.12 354654564,12 = 354654564.12
	 * 
	 * @param data
	 * @autor Sï¿½vio Luiz, Thiago Toscano
	 * @date 15/02/2006, 18/03/2006
	 * @return
	 */

	public static BigDecimal formatarMoedaRealparaBigDecimal(String valor) {
		BigDecimal bigDecimalFormatado = new BigDecimal("0");

		if (valor != null) {
			valor = valor.trim();

			boolean negativo = false;
			if (valor.startsWith("-")) {
				negativo = true;
			}

			boolean temCasaDecimal = false;
			if (valor.length() > 2
					&& (valor.substring(valor.length() - 3, valor.length() - 2)
							.equals(".") || valor.substring(valor.length() - 3,
							valor.length() - 2).equals(","))) {
				temCasaDecimal = true;
			}

			String valorSemPontuacao = "";
			// metodo que tira todos os pontos no meio da string
			for (int i = 0; i < valor.length(); i++) {
				try {
					Integer.parseInt(valor.substring(i, i + 1));
					valorSemPontuacao = valorSemPontuacao
							+ valor.substring(i, i + 1);
				} catch (Exception e) {
				}
			}
			if (temCasaDecimal) {
				int tamanho = valorSemPontuacao.length();
				valorSemPontuacao = valorSemPontuacao.substring(0, tamanho - 2)
						+ "."
						+ valorSemPontuacao.substring(tamanho - 2, tamanho);
			}
			if (negativo) {
				valorSemPontuacao = "-" + valorSemPontuacao;
			}
			bigDecimalFormatado = new BigDecimal(valorSemPontuacao);
		}

		return bigDecimalFormatado;
	}

	/**
	 * Mï¿½todo que recebe uma string ex."123456" e converte para o objeto
	 * BigDecimal ex. "1234.56 colocado as ultimas duas strins como casas
	 * decimais.
	 * 
	 * 354654564,12 = 354654564.12 354.654.564,12 = 354654564.12 35465456412 =
	 * 354654564.12 354654564.12 = 354654564.12 354654564,12 = 354654564.12
	 * 
	 * @param data
	 * @autor Thiago Toscano
	 * @date 15/02/2006, 18/03/2006
	 * @return
	 */

	public static BigDecimal formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(
			String valor) {
		BigDecimal bigDecimalFormatado = null;

		int tamanho = valor.length();
		valor = valor.substring(0, tamanho - 2) + "."
				+ valor.substring(tamanho - 2, tamanho);

		bigDecimalFormatado = new BigDecimal(valor);
		return bigDecimalFormatado;
	}

	/**
	 * Mï¿½todo que verifica se a string passada jï¿½ tem casa decimal
	 * 
	 * 
	 * @param data
	 * @autor Sï¿½vio Luiz
	 * @date 15/02/2006
	 * @return
	 */

	public static boolean verificaSeBigDecimal(String valor) {

		boolean temCasaDecimal = false;
		if (valor.length() > 2
				&& (valor.substring(valor.length() - 3, valor.length() - 2)
						.equals(".") || valor.substring(valor.length() - 3,
						valor.length() - 2).equals(","))) {
			temCasaDecimal = true;
		}
		return temCasaDecimal;
	}

	/**
	 * Mï¿½todo que recebe uma e verifica se a string sï¿½ tem numeros.
	 * 
	 * @param data
	 * @autor Sï¿½vio Luiz
	 * @date 20/05/2005
	 * @return
	 */

	public static Integer recuperaAnoMesDaData(Date data) {

		int ano = Util.getAno(data);
		int mes = Util.getMes(data);
		String mesFormatado = null;
		if (mes > 0 && mes < 10) {
			mesFormatado = "0" + mes;
		} else {
			mesFormatado = "" + mes;
		}

		return new Integer(ano + "" + mesFormatado);

	}

	/**
	 * Mï¿½todo que recebe uma data com string no formato dd/MM/yyyy e converte
	 * para o objeto Date.
	 * 
	 * @param data
	 * @autor Thiago Toscano
	 * @date 20/05/2005
	 * @return
	 */
	public static Date converteStringParaDate(String data) {
		Date retorno = null;
		try {
			retorno = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR")).parse(data);
		} catch (Exception e) {
			new IllegalArgumentException(data + " nï¿½o tem o formato dd/MM/yyyy.");
		}
		return retorno;
	}

	/**
	 * Mï¿½todo que recebe uma data com hora com string no formato dd/MM/yyyy
	 * HH:mm:ss e converte para o objeto Date.
	 * 
	 * @param data
	 * @autor Rafael Santos
	 * @date 06/04/2006
	 * @return
	 */
	public static Date converteStringParaDateHora(String data) {
		Date retorno = null;
		try {
			retorno = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale(
					"pt", "BR")).parse(data);
		} catch (Exception e) {
			new IllegalArgumentException(data
					+ " nï¿½o tem o formato dd/MM/yyyy HH:mm:ss.");
		}
		return retorno;
	}
	
	public static Date converteStringParaDateHora(String data, String formato) {
		Date retorno = null;
		try {
			retorno = new SimpleDateFormat( formato, new Locale(
					"pt", "BR")).parse(data);
		} catch (Exception e) {
			new IllegalArgumentException(data
					+ " nï¿½o tem o formato " + formato + ".");
		}
		return retorno;
	}
	

	/**
	 * Mï¿½todo que recebe um int e formata para a data de referï¿½ncia no
	 * formato(mm/aaaa).
	 * 
	 * @param numero
	 *            inteiro
	 * @autor Pedro Alexandre
	 * @date 06/01/2006
	 * @return uma string contendo a referï¿½ncia formatada
	 */
	public static String formatarMesAnoReferencia(int anoMes) {
		// converte o valor do tipo int(primitivo em um objeto String)
		String referenciaEmString = (new Integer(anoMes)).toString();

		// devolve a data de referï¿½ncia formatada com o mï¿½s na frente seguido
		// pelo ano
		// separados por uma "/"
		if (referenciaEmString.length() == 6) {
			return referenciaEmString.substring(4, 6) + "/"
					+ referenciaEmString.substring(0, 4);
		} else {
			return "";
		}

	}

	/**
	 * Mï¿½todo que recebe uma String e tira os ultimos X caracteres da mesma.
	 * Onde X ï¿½ o valor informado como parametro.
	 * 
	 * @autor Rhawi Dantas
	 * @date 07/01/2006
	 */
	public static String removerUltimosCaracteres(String hql, int valor) {
		return hql.substring(0, hql.length() - valor);
	}

	/**
	 * Comparar duas datas e retornar a diferenï¿½a de meses entre elas Author:
	 * Rafael Santos Data: 07/01/20069
	 * 
	 * @param dataInicial
	 *            Data Inicial
	 * @param dataFinal
	 *            Data Final
	 * 
	 * @return int
	 */
	public static int dataDiff(Date dataInicial, Date dataFinal) {
		int quantidadeMeses = 0;

		GregorianCalendar tempoInicial = new GregorianCalendar();
		GregorianCalendar tempoFinal = new GregorianCalendar();

		GregorianCalendar tempoCorrente = new GregorianCalendar();
		GregorianCalendar tempoBase = new GregorianCalendar();

		tempoInicial.setTime(dataInicial);
		tempoFinal.setTime(dataFinal);

		// Verifica a ordem de inicio das datas
		if (dataInicial.compareTo(dataFinal) < 0) {
			tempoBase.setTime(dataFinal);
			tempoCorrente.setTime(dataInicial);

		} else {
			tempoBase.setTime(dataInicial);
			tempoCorrente.setTime(dataFinal);

		}

		// Acumular o Mes
		while (tempoCorrente.get(GregorianCalendar.YEAR) < tempoBase
				.get(GregorianCalendar.YEAR)
				|| tempoCorrente.get(GregorianCalendar.MONTH) < tempoBase
						.get(GregorianCalendar.MONTH)) {

			quantidadeMeses = quantidadeMeses + 1;
			tempoCorrente.add(GregorianCalendar.MONTH, 1);

		}

		return quantidadeMeses;
	}

	/**
	 * Author: Sï¿½vio Luiz Data: 16/01/2006 Valida o ano mes de referencia
	 * retornando true se a data for invï¿½lida e false se a data for vï¿½lida
	 * 
	 */
	public static boolean validarAnoMes(String anoMesReferencia) {
		boolean anoMesInvalido = false;

		if (anoMesReferencia.length() == 7) {

			String mesAnoReferencia = anoMesReferencia.substring(4, 6) + "/"
					+ anoMesReferencia.substring(0, 4);

			SimpleDateFormat dataTxt = new SimpleDateFormat("MM/yyyy");

			try {
				dataTxt.parse(mesAnoReferencia);
			} catch (ParseException e) {
				anoMesInvalido = true;
			}

		} else {
			anoMesInvalido = true;
		}

		return anoMesInvalido;
	}

	/**
	 * Author: Sï¿½vio Luiz Data: 16/01/2006 Valida o ano mes de referencia
	 * retornando true se a data for invï¿½lida e false se a data for vï¿½lida
	 * 
	 * @param dividendo
	 *            Valor do Dividendo
	 * @param divisor
	 *            Valor do Dividor
	 * @return O Valor divido, caso necessï¿½rio arredondado
	 */
	public static boolean validarAnoMesSemBarra(String anoMesReferencia) {
		boolean anoMesInvalido = false;

		if (anoMesReferencia.length() == 6) {

			String mes = anoMesReferencia.substring(4, 6);
			// String ano = anoMesReferencia.substring(0, 4);

			try {
				int mesInt = Integer.parseInt(mes);
				// int anoInt = Integer.parseInt(ano);

				if (mesInt > 12) {
					anoMesInvalido = true;
				}
			} catch (NumberFormatException e) {
				anoMesInvalido = true;
			}
		} else {
			anoMesInvalido = true;
		}

		return anoMesInvalido;
	}
	
	/**
	 * Valida o mï¿½s/ano de referï¿½ncia sem a barra
	 * retornando true se a data for invï¿½lida e false se a data for vï¿½lida
	 * 
	 * Author: Rafael Corrï¿½a 
	 * Data: 15/07/2009
	 *  
	 */
	public static boolean validarMesAnoSemBarra(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 6) {
			String mes = mesAnoReferencia.substring(0, 2);

			try {
				int mesInt = Integer.parseInt(mes);

				if (mesInt > 12) {
					mesAnoValido = false;
				}
			} catch (NumberFormatException e) {
				mesAnoValido = false;
			}

		} else {
			mesAnoValido = false;
		}

		return mesAnoValido;
	}

	/**
	 * Author: Rodrigo Silveira Data: 21/01/2006 Valida o mï¿½s/ano de referï¿½ncia
	 * retornando false se a data for invï¿½lida e true se a data for vï¿½lida
	 * 
	 * Alterado por Sï¿½vio Luiz. Data: 15/03/2006
	 * 
	 * Ex.: 11/2005
	 */
	public static boolean validarMesAno(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 7) {
			String mes = mesAnoReferencia.substring(0, 2);
			// String ano = mesAnoReferencia.substring(3, 7);

			try {
				int mesInt = Integer.parseInt(mes);
				// int anoInt = Integer.parseInt(ano);

				if (mesInt > 12) {
					mesAnoValido = false;
				}
			} catch (NumberFormatException e) {
				mesAnoValido = false;
			}

		} else {
			mesAnoValido = false;
		}

		return mesAnoValido;
	}

	/**
	 * Author: Rafael Santos Data: 09/01/2006 Dividir dois BigDecimal
	 * arredondando Arredondanda o resultado apatir da 8 casa decimal para cima
	 * 
	 * @param dividendo
	 *            Valor do Dividendo
	 * @param divisor
	 *            Valor do Dividor
	 * @return O Valor divido, caso necessï¿½rio arredondado
	 */
	public static BigDecimal dividirArredondando(BigDecimal dividendo,
			BigDecimal divisor) {

		BigDecimal resultado = null;

		if (dividendo != null && divisor != null) {

			resultado = dividendo.divide(divisor, 7, BigDecimal.ROUND_HALF_UP);

		}

		return resultado;
	}

	public static String formatarRGApresentacao(String rg, String orgao,
			String uf) {
		return rg + " " + orgao + "/" + uf;
	}

	/**
	 * [UC0000] - Author: Raphael Rossiter Data: 13/01/2006
	 * 
	 * Calcula a representaï¿½ï¿½o nï¿½merica do cï¿½digo de barras no mï¿½dulo 10
	 * 
	 * @param numero
	 * @return digito verificador
	 */
	public static Integer calculoRepresentacaoNumericaCodigoBarrasModulo10(
			Integer numero) {

		int entrada = numero.intValue();

		String entradaString = String.valueOf(entrada);

		int sequencia = 2;
		int contEntrada, digito, contAuxiliar, produto, contProduto;
		String produtoString;
		int somaDigitosProduto = 0;

		contAuxiliar = 1;
		for (contEntrada = 0; contEntrada < entradaString.length(); contEntrada++) {

			digito = new Integer(entradaString.substring(entradaString.length()
					- contAuxiliar, entradaString.length() - contEntrada))
					.intValue();

			produto = digito * sequencia;
			produtoString = String.valueOf(produto);

			for (contProduto = 0; contProduto < produtoString.length(); contProduto++) {
				somaDigitosProduto = somaDigitosProduto
						+ new Integer(produtoString.substring(contProduto,
								contProduto + 1)).intValue();
			}

			if (sequencia == 2) {
				sequencia = 1;
			} else {
				sequencia = 2;
			}

			contAuxiliar++;
		}

		int resto = somaDigitosProduto % 10;
		int dac;

		if (resto == 0) {
			dac = 0;
		} else {
			dac = 10 - resto;
		}

		return new Integer(dac);
	}

	/**
	 * <Breve descriï¿½ï¿½o sobre o caso de uso>
	 * 
	 * [UC0260] Obter Dï¿½gito Verificador Mï¿½dulo 10
	 * 
	 * @author Rafael Rossiter
	 * @date 17/03/2006
	 * 
	 * @param numero
	 * @return
	 */
	public static Integer obterDigitoVerificadorModulo10(Long numero) {

		long entrada = numero.longValue();

		String entradaString = String.valueOf(entrada);

		int sequencia = 2;
		int contEntrada, digito, contAuxiliar, produto, contProduto;
		String produtoString;
		int somaDigitosProduto = 0;

		contAuxiliar = 1;
		for (contEntrada = 0; contEntrada < entradaString.length(); contEntrada++) {

			digito = new Integer(entradaString.substring(entradaString.length()
					- contAuxiliar, entradaString.length() - contEntrada))
					.intValue();

			produto = digito * sequencia;
			produtoString = String.valueOf(produto);

			for (contProduto = 0; contProduto < produtoString.length(); contProduto++) {
				somaDigitosProduto = somaDigitosProduto
						+ new Integer(produtoString.substring(contProduto,
								contProduto + 1)).intValue();
			}

			if (sequencia == 2) {
				sequencia = 1;
			} else {
				sequencia = 2;
			}

			contAuxiliar++;
		}

		int resto = somaDigitosProduto % 10;

		int dac;
		if (resto == 0) {
			dac = 0;
		} else {
			dac = 10 - resto;
		}

		return new Integer(dac);
	}

	/**
	 * <Breve descriï¿½ï¿½o sobre o caso de uso>
	 * 
	 * [UC0260] Obter Dï¿½gito Verificador Mï¿½dulo 10
	 * 
	 * @author Rafael Rossiter
	 * @date 17/03/2006
	 * 

	 * @param numero
	 * @return
	 */
	public static Integer obterDigitoVerificadorModulo10(String numero) {

		String entradaString = numero;

		int sequencia = 2;
		int contEntrada, digito, contAuxiliar, produto, contProduto;
		String produtoString;
		int somaDigitosProduto = 0;

		contAuxiliar = 1;
		for (contEntrada = 0; contEntrada < entradaString.length(); contEntrada++) {

			digito = new Integer(entradaString.substring(entradaString.length()
					- contAuxiliar, entradaString.length() - contEntrada))
					.intValue();

			produto = digito * sequencia;
			produtoString = String.valueOf(produto);

			for (contProduto = 0; contProduto < produtoString.length(); contProduto++) {
				somaDigitosProduto = somaDigitosProduto
						+ new Integer(produtoString.substring(contProduto,
								contProduto + 1)).intValue();
			}

			if (sequencia == 2) {
				sequencia = 1;
			} else {
				sequencia = 2;
			}

			contAuxiliar++;
		}

		int resto = somaDigitosProduto % 10;

		int dac;
		if (resto == 0) {
			dac = 0;
		} else {
			dac = 10 - resto;
		}

		return new Integer(dac);
	}

	public static String[] formatarAnoMes(String anoMesReferencia) {
		String[] dataCompleta = new String[2];

		String mes = anoMesReferencia.substring(4, 6);
		String ano = anoMesReferencia.substring(0, 4);

		dataCompleta[0] = mes;
		dataCompleta[1] = ano;

		return dataCompleta;

	}

	/**
	 * Verifica se duas datas sï¿½o iguais
	 * 
	 * @author Sï¿½vio Luiz
	 * @date 20/03/2006
	 * 
	 * @param primeiraData
	 *            <Descriï¿½ï¿½o>
	 * @param segundaData
	 *            <Descriï¿½ï¿½o>
	 * @return retorno
	 */
	public static boolean datasIguais(Date primeiraData, Date segundaData) {

		boolean retorno = false;

		Calendar d1 = Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();

		d1.setTime(primeiraData);
		d2.setTime(segundaData);

		d1.set(Calendar.HOUR_OF_DAY, 0);
		d1.set(Calendar.MINUTE, 0);
		d1.set(Calendar.SECOND, 0);
		d1.set(Calendar.MILLISECOND, 0);

		d2.set(Calendar.HOUR_OF_DAY, 0);
		d2.set(Calendar.MINUTE, 0);
		d2.set(Calendar.SECOND, 0);
		d2.set(Calendar.MILLISECOND, 0);

		if (d1.getTime().equals(d2.getTime())) {
			retorno = true;
		}

		return retorno;

	}

	/**
	 * Mï¿½todo que recebe uma data e adapta os seus valores para comparaï¿½ï¿½o de
	 * uma data final de inetervalo para o uso do between.
	 * 
	 * @param data
	 * @autor Raphael Rossiter
	 * @date 29/09/2005
	 * @return
	 */
	public static Date adaptarDataFinalComparacaoBetween(Date data) {

		Date retorno = null;

		Calendar calendario = Calendar.getInstance();

		calendario.setTime(data);

		calendario.set(Calendar.HOUR, 23);
		calendario.set(Calendar.MINUTE, 59);
		calendario.set(Calendar.SECOND, 59);
		calendario.set(Calendar.MILLISECOND, 999);

		retorno = calendario.getTime();

		return retorno;
	}

	/**
	 * extrai a hora o minuto e o segundo da data ex.: Thu May 11 10:12:50
	 * GMT-03:00 2006 o resultado serï¿½ Thu May 11 00:00:00 GMT-03:00 2006
	 * 
	 * 
	 * @author Sï¿½vio Luiz
	 * @date 11/05/2006
	 * 
	 * @param numero
	 * @return
	 */
	public static Date formatarDataSemHora(Date data) {
		Calendar d1 = Calendar.getInstance();
		d1.setTime(data);
		d1.set(Calendar.HOUR_OF_DAY, 0);
		d1.set(Calendar.MINUTE, 0);
		d1.set(Calendar.SECOND, 0);
		d1.set(Calendar.MILLISECOND, 0);
		return d1.getTime();
	}

	public static String formatarMoedaReal(BigDecimal valor) {
		/**
		 * Sï¿½mbolos especificos do Real Brasileiro
		 */
		DecimalFormatSymbols REAL = new DecimalFormatSymbols(new Locale("pt",
				"BR"));
		/**
		 * Mascara de dinheiro para Real Brasileiro
		 */
		// DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00");
		//
		// return DINHEIRO_REAL.format(valor);
		if (valor != null && !"".equals(valor)) {
			DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00",
					REAL);
			return DINHEIRO_REAL.format(valor);
		} else {
			return "";
		}

	}
	
	public static String formatarMoedaReal4Casas(BigDecimal valor) {
		/**
		 * Sï¿½mbolos especificos do Real Brasileiro
		 */
		DecimalFormatSymbols REAL = new DecimalFormatSymbols(new Locale("pt",
				"BR"));
		/**
		 * Mascara de dinheiro para Real Brasileiro
		 */
		// DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00");
		//
		// return DINHEIRO_REAL.format(valor);
		if (valor != null && !"".equals(valor)) {
			DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.0000",
					REAL);
			return DINHEIRO_REAL.format(valor);
		} else {
			return "";
		}

	}

	public static boolean validarDiaMesAno(String diaAnoMesReferencia) {
		boolean anoMesInvalido = false;

		if (diaAnoMesReferencia.length() == 10) {

			// String mesAnoReferencia = anoMesReferencia.substring(4, 6) + "/"
			// + anoMesReferencia.substring(0, 4);

			SimpleDateFormat dataTxt = new SimpleDateFormat("DD/MM/yyyy");

			try {
				dataTxt.parse(diaAnoMesReferencia);
			} catch (ParseException e) {
				anoMesInvalido = true;
			}
		} else {
			anoMesInvalido = true;
		}

		return anoMesInvalido;
	}

	public static boolean validarAnoMesDiaSemBarra(String diaMesAnoReferencia) {
		boolean diaMesAnoInvalido = false;

		if (diaMesAnoReferencia.length() == 8) {

			// String ano = diaMesAnoReferencia.substring(0, 4);
			String mes = diaMesAnoReferencia.substring(4, 6);
			String dia = diaMesAnoReferencia.substring(6, 8);

			try {
				int mesInt = Integer.parseInt(mes);
				// int anoInt = Integer.parseInt(ano);
				int diaInt = Integer.parseInt(dia);

				if (mesInt > 12) {
					diaMesAnoInvalido = true;
				}
				if (diaInt > 31) {
					diaMesAnoInvalido = true;
				}
			} catch (NumberFormatException e) {
				diaMesAnoInvalido = true;
			}

		} else {
			diaMesAnoInvalido = true;
		}

		return diaMesAnoInvalido;
	}

	/**
	 * Mï¿½todo que recebe uma data com string no formato AAAAMMDD e converte para
	 * o objeto Date.
	 * 
	 * @param data
	 * @autor Sï¿½vio Luiz
	 * @date 20/05/2005
	 * @return
	 */
	public static Date converteStringInvertidaSemBarraParaDate(String data) {
		Date retorno = null;
		String dataInvertida = data.substring(6, 8) + "/"
				+ data.substring(4, 6) + "/" + data.substring(0, 4);
		SimpleDateFormat dataTxt = new SimpleDateFormat("dd/MM/yyyy");
		try {
			retorno = dataTxt.parse(dataInvertida);
		} catch (ParseException e) {
			throw new IllegalArgumentException(data
					+ " nï¿½o tem o formato dd/MM/yyyy.");
		}
		return retorno;
	}

	/**
	 * Mï¿½todo que recebe uma string e verifica se a string sï¿½ tem numeros com
	 * casas decimais
	 * 
	 * @param data
	 * @autor thiago
	 * @date 18/03/2006
	 * @return
	 */

	public static boolean validarValorNaoNumericoComoBigDecimal(String valor) {
		boolean numeroNaoNumerico = false;
		try {

			new BigDecimal(valor);
		} catch (NumberFormatException e) {
			numeroNaoNumerico = true;
		}
		return numeroNaoNumerico;
	}
	
	/**
	 * Mï¿½todo que recebe uma string e verifica se a string sï¿½ tem numeros.
	 * 
	 * @param data
	 * @autor Sï¿½vio Luiz
	 * @date 20/05/2005
	 * @return
	 */

	public static boolean validarValorNaoNumerico(String valor) {
		boolean numeroNaoNumerico = false;
		try {

			Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			numeroNaoNumerico = true;
		}
		return numeroNaoNumerico;
	}
    
    /**
     * Mï¿½todo que recebe uma string e verifica se a string sï¿½ tem numeros ou se o seu valor ï¿½ zero.
     * 
     * @param data
     * @autor Breno Santos
     * @date 10/09/2010
     * @return
     */
    public static boolean validarValorDiferenteZero(String valor) {
        boolean numeroNaoNumerico = false;
        try {

         if(valor != null){
             
            int numero = Integer.parseInt(valor);
            
            if(numero == 0){
                numeroNaoNumerico =  true;
            }
          
          }
            
        } catch (NumberFormatException e) {
            numeroNaoNumerico = true;
        }
        
        return numeroNaoNumerico;
    }

	public static Integer formataAnoMes(Date data) {

		int ano = Util.getAno(data);
		int mes = Util.getMes(data);

		String mesFormatado = null;

		if (mes >= 0 && mes < 10) {
			mesFormatado = "0" + mes;
		} else {
			mesFormatado = "" + mes;
		}

		String anoMesFormatado = "" + ano + mesFormatado;

		return new Integer(anoMesFormatado);

	}
	
	/**
	 * Verifica se eh dia util
	 * (Verifica por feriado nacional,municipal e se final de semana)
	 * 
	 * Auhtor: Rafael Pinto
	 * Data: 23/08/2007 
	 * 
	 * @param Date data a ser verificada
	 * @param Colecao<NacionalFeriado)
	 * @param Colecao<MunicipioFeriado)
	 * 
	 * @return boolean (true - eh dia util, false - nao dia util)
	 */
	public static boolean ehDiaUtil(Date dataAnalisada,Collection<NacionalFeriado> colecaoNacionalFeriado,
		Collection<MunicipioFeriado> colecaoMunicipioFeriado){
		
		boolean ehDiaUtil = true;
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dataAnalisada);
		
		int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		
		// Verifica se eh Sabado ou Domingo
		if(diaDaSemana == Calendar.SATURDAY || 
			diaDaSemana == Calendar.SUNDAY){
			
			ehDiaUtil = false;

		// Verifica se eh Feriado
		}else{
			
			if(colecaoNacionalFeriado != null && !colecaoNacionalFeriado.isEmpty()){
				
				Iterator itera = colecaoNacionalFeriado.iterator();
				
				while (itera.hasNext()) {
					NacionalFeriado nacionalFeriado = (NacionalFeriado) itera.next();
					
					if(nacionalFeriado.getData().compareTo(dataAnalisada) == 0){
						ehDiaUtil = false;
						break;
					}
				}
			}
			
			if(ehDiaUtil){

				if(colecaoMunicipioFeriado != null && !colecaoMunicipioFeriado.isEmpty()){
					
					Iterator itera = colecaoMunicipioFeriado.iterator();
					
					while (itera.hasNext()) {
						MunicipioFeriado municipioFeriado = (MunicipioFeriado) itera.next();
						
						if(municipioFeriado.getDataFeriado().compareTo(dataAnalisada) == 0){
							ehDiaUtil = false;
							break;
						}
					}
				}
			}
			
		}//fim do if diaSemana
		
		return ehDiaUtil;
	}

	/**
	 * Retorna o Ultimo Dia Util do Mï¿½s informado 
	 * 
	 * Auhtor: Rafael Santos 
	 * Data: 20/02/2006 
	 * 
	 * Indices de Mï¿½s 1 - Janiero 
	 * 2 - Fevereiro 
	 * 3 - Marï¿½o 
	 * 4 - Abril 
	 * 5 - Maio 
	 * 6 - Junho 
	 * 7 - Julho 
	 * 8 - Agosto 
	 * 9 - Setembro 
	 * 10 - Outubro 
	 * 11 - Novembro 
	 * 12 - Dezembro
	 * 
	 * @param mes Indice do Mï¿½s
	 * @param ano Ano
	 * @param colecaoDatasFeriados Coleï¿½ï¿½o de Datas dos Feriados
	 * 
	 * @return Ultimo Dia do Mes util
	 */
	public static int obterUltimoDiaUtilMes(int mes, int ano,
			Collection colecaoDatasFeriados) {
		int ultimoDiaUtil = 0;

		Calendar calendar = new GregorianCalendar();

		calendar.set(Calendar.YEAR, ano);// ano

		switch (mes) {
		case 1:// JANEIRO
			calendar.set(Calendar.MONTH, 0);
			break;
		case 2:// FEVEREIRO
			calendar.set(Calendar.MONTH, 1);
			break;
		case 3:// MARï¿½O
			calendar.set(Calendar.MONTH, 2);
			break;
		case 4:// ABRIL
			calendar.set(Calendar.MONTH, 3);
			break;
		case 5:// MAIO
			calendar.set(Calendar.MONTH, 4);
			break;
		case 6:// JUNHO
			calendar.set(Calendar.MONTH, 5);
			break;
		case 7:// JULHO
			calendar.set(Calendar.MONTH, 6);
			break;
		case 8:// AGOSTO
			calendar.set(Calendar.MONTH, 7);
			break;
		case 9:// SETEMBRO
			calendar.set(Calendar.MONTH, 8);
			break;
		case 10:// OUTUBRO
			calendar.set(Calendar.MONTH, 9);
			break;
		case 11:// NOVEMBRO
			calendar.set(Calendar.MONTH, 10);
			break;
		case 12:// DEZEMBRO
			calendar.set(Calendar.MONTH, 11);
			break;
		default:
			break;
		}

		// ultima dia do mes
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));

		boolean feriado = true;

		while (feriado
				| ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) | (calendar
						.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY))) {

			// se o dia for domingo voltar um dia
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}

			// se o dia for sabado voltar um dia
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}

			// verfica se o dia ï¿½ feriado
			if (colecaoDatasFeriados != null && !colecaoDatasFeriados.isEmpty()) {

				// Organizar a coleï¿½ï¿½o
				Collections.sort((List) colecaoDatasFeriados, new Comparator() {
					public int compare(Object a, Object b) {
						Date data1 = (Date) a;
						Date data2 = (Date) b;

						return (data1.compareTo(data2)) * -1;
					}
				});

				Iterator iteratorColecaoDatasFeriados = colecaoDatasFeriados
						.iterator();

				while (iteratorColecaoDatasFeriados.hasNext()) {
					Date dataFeriado = (Date) iteratorColecaoDatasFeriados
							.next();
					Calendar calendarDataFeriado = new GregorianCalendar();
					calendarDataFeriado.setTime(dataFeriado);

					// verifica se o dia, mes e ano
					if ((calendar.get(Calendar.DAY_OF_MONTH) == calendarDataFeriado
							.get(Calendar.DAY_OF_MONTH))
							&& (calendar.get(Calendar.MONTH) == calendarDataFeriado
									.get(Calendar.MONTH))
							&& (calendar.get(Calendar.YEAR) == calendarDataFeriado
									.get(Calendar.YEAR))) {
						calendar.add(Calendar.DAY_OF_MONTH, -1);
					}

				}
				feriado = false;
			} else {
				feriado = false;
			}
		}

		ultimoDiaUtil = calendar.get(Calendar.DAY_OF_MONTH);

		return ultimoDiaUtil;
	}

	/**
	 * Retorna o Ultimo Dia do Mï¿½s informado Auhtor: Rafael Corrï¿½a Data:
	 * 02/04/2007 Indices de Mï¿½s 1 - Janiero 2 - Fevereiro 3 - Marï¿½o 4 - Abril 5 -
	 * Maio 6 - Junho 7 - Julho 8 - Agosto 9 - Setembro 10 - Outubro 11 -
	 * Novembro 12 - Dezembro
	 * 
	 * @param mes
	 *            Indice do Mï¿½s
	 * @param ano
	 *            Ano
	 * @return Ultimo Dia do Mes
	 */
	public static String obterUltimoDiaMes(int mes, int ano) {
		String ultimoDia = "";

		Calendar calendar = new GregorianCalendar(ano, mes -1, 1);
		
		// ultima dia do mes
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		ultimoDia = "" + calendar.get(Calendar.DAY_OF_MONTH);
		
		return ultimoDia;
	}
	
	
	/**
	 * Retorna o Ultima Data do Mï¿½s informado Auhtor: Yara Taciane Data:
	 * 20/06/2008 Indices de Mï¿½s 1 - Janiero 2 - Fevereiro 3 - Marï¿½o 4 - Abril 5 -
	 * Maio 6 - Junho 7 - Julho 8 - Agosto 9 - Setembro 10 - Outubro 11 -
	 * Novembro 12 - Dezembro
	 * 
	 * @param mes
	 *            Indice do Mï¿½s
	 * @param ano
	 *            Ano
	 * @return Ultimo Dia do Mes
	 */
	public static Date obterUltimaDataMes(int mes, int ano) {
		Date ultimaData = new Date();

		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		calendar.set(Calendar.YEAR, ano);// ano

		switch (mes) {
		case 1:// JANEIRO
			calendar.set(Calendar.MONTH, 0);
			break;
		case 2:// FEVEREIRO
			calendar.set(Calendar.MONTH, 1);
			break;
		case 3:// MARï¿½O
			calendar.set(Calendar.MONTH, 2);
			break;
		case 4:// ABRIL
			calendar.set(Calendar.MONTH, 3);
			break;
		case 5:// MAIO
			calendar.set(Calendar.MONTH, 4);
			break;
		case 6:// JUNHO
			calendar.set(Calendar.MONTH, 5);
			break;
		case 7:// JULHO
			calendar.set(Calendar.MONTH, 6);
			break;
		case 8:// AGOSTO
			calendar.set(Calendar.MONTH, 7);
			break;
		case 9:// SETEMBRO
			calendar.set(Calendar.MONTH, 8);
			break;
		case 10:// OUTUBRO
			calendar.set(Calendar.MONTH, 9);
			break;
		case 11:// NOVEMBRO
			calendar.set(Calendar.MONTH, 10);
			break;
		case 12:// DEZEMBRO
			calendar.set(Calendar.MONTH, 11);
			break;
		default:
			break;
		}

		// ultima data do mes
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));

		ultimaData = calendar.getTime();		
	
		return ultimaData;
	}
	
	public static Date obterUltimaDataMes(int anoMes) {
		Date ultimaData = new Date();
		
		String dataFormatacao = "" + anoMes;
		
		int mes = new Integer(dataFormatacao.substring(4, 6)).intValue();
		int ano = new Integer(dataFormatacao.substring(0, 4)).intValue();
		
		ultimaData = obterUltimaDataMes(mes, ano);
		
		return ultimaData;
	}

	/**
	 * Mï¿½todo para comparar duas data e retornar o numero de dias da diferenï¿½a
	 * entre elas
	 * 
	 * Author: Rafael Santos Data: 07/03/2006
	 * 
	 * @param dataInicial
	 *            Data Inicial
	 * @param dataFinal
	 *            Data Final
	 * 
	 * @return int Quantidade de Dias
	 */
	public static int obterQuantidadeDiasEntreDuasDatas(Date dataInicial,
			Date dataFinal) {

		GregorianCalendar startTime = new GregorianCalendar();
		GregorianCalendar endTime = new GregorianCalendar();

		GregorianCalendar curTime = new GregorianCalendar();
		GregorianCalendar baseTime = new GregorianCalendar();

		
		if(dataInicial instanceof Timestamp) {
			dataInicial = new Date(((Date)dataInicial).getTime());	
		}
		
		if(dataFinal instanceof Timestamp) {
			dataFinal = new Date(((Date)dataFinal).getTime());	
		}
		
		startTime.setTime(dataInicial);
		endTime.setTime(dataFinal);

		int multiplicadorDiferenca = 1;

		// Verifica a ordem de inicio das datas
		if (dataInicial.compareTo(dataFinal) < 0) {
			baseTime.setTime(dataFinal);
			curTime.setTime(dataInicial);
			multiplicadorDiferenca = 1;
		} else {
			baseTime.setTime(dataInicial);
			curTime.setTime(dataFinal);
			multiplicadorDiferenca = -1;
		}

		int resultadoAno = 0;
		int resultadoMeses = 0;
		int resultadoDias = 0;

		// Para cada mes e ano, vai de mes em mes pegar o ultimo dia para ir
		// acumulando
		// no total de dias. Ja leva em consideracao ano bissesto
		while (curTime.get(GregorianCalendar.YEAR) < baseTime
				.get(GregorianCalendar.YEAR)
				|| curTime.get(GregorianCalendar.MONTH) < baseTime
						.get(GregorianCalendar.MONTH)) {

			int max_day = curTime
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			resultadoMeses += max_day;
			curTime.add(GregorianCalendar.MONTH, 1);

		}

		// Marca que ï¿½ um saldo negativo ou positivo
		resultadoMeses = resultadoMeses * multiplicadorDiferenca;

		// Retirna a diferenca de dias do total dos meses
		resultadoDias += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime
				.get(GregorianCalendar.DAY_OF_MONTH));

		return resultadoAno + resultadoMeses + resultadoDias;
	}

	/**
	 * Author: Pedro Alexandre Data: 08/01/2006
	 * 
	 * Subtrai nï¿½ de dias de uma data
	 * 
	 * @param numeroDias
	 * @param data
	 * @return data menos o nï¿½ de dias informado
	 */
	public static Date subtrairNumeroDiasDeUmaData(Date data, int numeroDias) {
		// cria uma instï¿½ncia de GregorianCalendar para manipular a data
		Calendar c = GregorianCalendar.getInstance();

		// seta a data
		c.setTime(data);

		// subtrai o nï¿½ de dias INFORMADO da data
		c.add(Calendar.DAY_OF_MONTH, -1 * numeroDias);

		// recupera a data subtraida dos nï¿½ de dias
		data = c.getTime();

		// retorna a nova data
		return data;
	}

	/**
	 * Author: Raphael Rossiter Data: 03/08/2007
	 * 
	 * Subtrai nï¿½ de anos de uma data
	 * 
	 * @param numeroAnos
	 * @param data
	 * @return data menos o nï¿½ de anos informado
	 */
	public static Date subtrairNumeroAnosDeUmaData(Date data, int numeroAnos) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(data);
		calendar.add(Calendar.YEAR, numeroAnos);

		return calendar.getTime();

	}

	/**
	 * Author: Pedro Alexandre Data: 08/01/2006
	 * 
	 * Adiciona nï¿½ de dias para uma data
	 * 
	 * @param numeroDias
	 * @param data
	 * @return data menos o nï¿½ de dias informado
	 */
	public static Date adicionarNumeroDiasDeUmaData(Date data, int numeroDias) {
		// cria uma instï¿½ncia de GregorianCalendar para manipular a data
		Calendar c = GregorianCalendar.getInstance();

		// seta a data
		c.setTime(data);

		// subtrai o nï¿½ de dias INFORMADO da data
		c.add(Calendar.DAY_OF_MONTH, numeroDias);

		// recupera a data somada aos nï¿½ de dias
		data = c.getTime();

		// retorna a nova data
		return data;
	}

	/**
	 * Author: Sï¿½vio Luiz Data: 21/02/2006
	 * 
	 * Recebe uma data e retorna AAAAMMDD
	 * 
	 * @param numeroDias
	 * @param data
	 * @return data menos o nï¿½ de dias informado
	 */
	public static String recuperaDataInvertida(Date data) {
		int dia = getDiaMes(data);
		int mes = getMes(data);
		int ano = getAno(data);

		String diaFormatado = null;
		String mesFormatado = null;

		if (mes > 0 && mes < 10) {
			mesFormatado = "0" + mes;
		} else {
			mesFormatado = "" + mes;
		}

		if (dia > 0 && dia < 10) {
			diaFormatado = "0" + dia;
		} else {
			diaFormatado = "" + dia;
		}

		// retorna a nova data no formato AAAAMMDD
		String dataFormatada = "" + ano + mesFormatado + diaFormatado;
		return dataFormatada;
	}

	// Complementa o tamanho da string com espaï¿½os em branco.
	// Autor:Sï¿½vio Luiz
	public static String completaString(String str, int tm) {
		char[] temp = new char[tm];
		Arrays.fill(temp, ' ');
		int tamanho = 0;
		if (str != null) {
			tamanho = str.length();
		} else {
			tamanho = 0;
			str = "";
		}

		StringBuilder stringBuilder = null;
		// caso o tamanho da string seja maior do que o tamanho especificado
		if (tamanho > tm) {
			// trunca a String
			String strTruncado = str.substring(0, tm);
			stringBuilder = new StringBuilder(strTruncado);
			// coloca o tamanho para o tamanho truncado
			tamanho = strTruncado.length();
		} else {
			stringBuilder = new StringBuilder(str);
		}

		stringBuilder.append(temp, tamanho, tm - tamanho);
		return stringBuilder.toString();
	}

	/**
	 * O metï¿½do completa uma string com espaï¿½os em branco (ex: passa a string
	 * "12.36" e o tamanho mï¿½ximo 10 e retorna " 12.36" )
	 * 
	 * [UC0321] Emitir Fatura de Cliente Responsï¿½vel
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2006
	 * 
	 * @param str
	 *            String que vai ser complementada com espaï¿½os em branco a
	 *            esquerda
	 * @param tm
	 *            Tamanho mï¿½ximo da string
	 * @return
	 */
	public static String completaStringComEspacoAEsquerda(String str,
			int tamanhoMaximo) {

		// Tamanho da string informada
		int tamanhoString = 0;
		if (str != null) {
			tamanhoString = str.length();
		} else {
			tamanhoString = 0;
		}

		// Calcula a quantidade de espaï¿½os embranco necessï¿½rios
		int quantidadeEspacos = tamanhoMaximo - tamanhoString;

		if (quantidadeEspacos < 0) {
			quantidadeEspacos = tamanhoMaximo;
		}

		// Cria um array de caracteres de espaï¿½os em branco
		char[] tempCharEspacos = new char[quantidadeEspacos];
		Arrays.fill(tempCharEspacos, ' ');

		// Cria uma string temporaria com os espaï¿½os em branco
		String temp = new String(tempCharEspacos);

		// Cria uma strinBuilder para armazenar a string
		StringBuilder stringBuilder = new StringBuilder(temp);

		// Caso o tamanhoda string informada seja maior que o tamanho mï¿½ximo da
		// string
		// trunca a string informada
		if (tamanhoString > tamanhoMaximo) {
			String strTruncado = str.substring(0, tamanhoMaximo);
			stringBuilder.append(strTruncado);
		} else {
			stringBuilder.append(str);
		}

		// Retorna a string informada com espaï¿½os em branco a esquerda
		// totalizando o tamanho mï¿½ximo informado
		return stringBuilder.toString();
	}

	/**
	 * O metï¿½do completa uma string com espaï¿½os em branco (ex: passa a string
	 * "12.36" e o tamanho mï¿½ximo 10 e retorna " 12.36" ) apenas se a string nï¿½o exceder o tamanho mï¿½ximo
	 * 
	 * 
	 * @author Rodrigo Silveira
	 * @date 04/01/2008
	 * 
	 * @param str
	 *            String que vai ser complementada com espaï¿½os em branco a
	 *            esquerda
	 * @param tm
	 *            Tamanho mï¿½ximo da string
	 * @return
	 */
	public static String completaStringComEspacoAEsquerdaCondicaoTamanhoMaximo(String str,
			int tamanhoMaximo) {

		// Tamanho da string informada
		int tamanhoString = 0;
		if (str != null) {
			tamanhoString = str.length();
		} else {
			tamanhoString = 0;
		}

		// Calcula a quantidade de espaï¿½os embranco necessï¿½rios
		int quantidadeEspacos = tamanhoMaximo - tamanhoString;

		if (quantidadeEspacos < 0) {
			return str;
			
		}

		// Cria um array de caracteres de espaï¿½os em branco
		char[] tempCharEspacos = new char[quantidadeEspacos];

		Arrays.fill(tempCharEspacos, ' ');

		// Cria uma string temporaria com os espaï¿½os em branco
		String temp = new String(tempCharEspacos);

		// Cria uma strinBuilder para armazenar a string
		StringBuilder stringBuilder = new StringBuilder(temp);

		
		stringBuilder.append(str);
		

		// Retorna a string informada com espaï¿½os em branco a esquerda
		// totalizando o tamanho mï¿½ximo informado
		return stringBuilder.toString();
	}
	
	/**
	 * Complementa a string passada com asteriscos a esquerda
	 * @param str
	 * @param tamanhoMaximo
	 * @return
	 */
	public static String completaStringComAsteriscos(String str, int tamanhoMaximo) {

		// Tamanho da string informada
		int tamanhoString = 0;
		if (str != null) {
			tamanhoString = str.length();
		} else {
			tamanhoString = 0;
		}

		// Calcula a quantidade de asteriscos necessï¿½rios
		int quantidadeAsteriscos = tamanhoMaximo - tamanhoString;

		if (quantidadeAsteriscos < 0) {
			quantidadeAsteriscos = tamanhoMaximo;
		}

		// Cria um array de caracteres de asteriscos
		char[] tempCharAsteriscos = new char[quantidadeAsteriscos];
		Arrays.fill(tempCharAsteriscos, '*');

		// Cria uma string temporaria com os asteriscos
		String temp = new String(tempCharAsteriscos);

		// Cria uma strinBuilder para armazenar a string
		StringBuilder stringBuilder = new StringBuilder(temp);

		// Caso o tamanho da string informada seja maior que o tamanho mï¿½ximo da
		// string
		// trunca a string informada
		if (tamanhoString > tamanhoMaximo) {
			String strTruncado = str.substring(0, tamanhoMaximo);
			stringBuilder.append(strTruncado);
		} else {
			stringBuilder.append(str);
		}

		// Retorna a string informada com asteriscos a esquerda
		// totalizando o tamanho mï¿½ximo informado
		return stringBuilder.toString();
	}	
	
	/**
	 * Formatar para MM/AAAA Entrada: MMAAAA Saï¿½da: MM/AAAA
	 * 
	 * @author Raphael Rossiter
	 * @date 03/04/2006
	 * 
	 * @param mesAno
	 * @return Uma string no formato MM/AAAA
	 */
	public static String formatarMesAnoSemBarraParaMesAnoComBarra(String mesAno) {

		String mes = mesAno.substring(0, 2);
		String ano = mesAno.substring(2, 6);

		return mes + "/" + ano;
	}

	/**
	 * Obtï¿½m a representaï¿½ï¿½o nï¿½merica do cï¿½digo de barras de um pagamento de
	 * acordo com os parï¿½metros informados
	 * 
	 * [UC0229] Obter Representaï¿½ï¿½o Numï¿½rica do Cï¿½digo de Barras
	 * 
	 * Obtï¿½m o dï¿½gito verificador geral do cï¿½digo de barra com 43 posiï¿½ï¿½es
	 * 
	 * [SB0002] Obter Dï¿½gito Verificador Geral
	 * 
	 * @author Pedro Alexandre
	 * @date 20/04/2006
	 * 
	 * @param codigoBarraCom43Posicoes
	 * @param moduloVerificador 
	 * @return
	 */
	public static Integer obterDigitoVerificadorGeral(
			String codigoBarraCom43Posicoes, Short moduloVerificador) {
		// Recupera o dï¿½gito verificador do mï¿½dulo 11 para o cï¿½digo de barra com
		// 43 posiï¿½ï¿½es
		// Passando uma string como parï¿½metro
		
		Integer digitoVerificadorGeral = null;
		
		if(moduloVerificador.compareTo(ConstantesSistema.MODULO_VERIFICADOR_11)==0){
			
			digitoVerificadorGeral = obterDigitoVerificadorModulo11(codigoBarraCom43Posicoes);
			
		}else{
			
			digitoVerificadorGeral = obterDigitoVerificadorModulo10(codigoBarraCom43Posicoes);
			
		}

		// Retorna o dï¿½gito verificador calculado
		return digitoVerificadorGeral;
	}

	/**
	 * Recupera o AnoMesDia da Data Entrada: Uma data(da base) Saï¿½da: AAAAMMDD
	 * 
	 * @author Sï¿½vio Luiz
	 * @date 22/04/2006
	 * 
	 * @param data
	 * @return Uma string no formato AAAAMMDD
	 */
	public static String recuperaAnoMesDiaDaData(Date data) {
		String ano = "" + getAno(data);
		String mes = "" + getMes(data);
		if (mes.length() == 1) {
			mes = "0" + mes;
		}
		String dia = "" + getDiaMes(data);
		if (dia.length() == 1) {
			dia = "0" + dia;
		}

		return ano + mes + dia;
	}

	/**
	 * Recupera o AnoMesDia da Data Entrada: Uma data(da base) Saï¿½da: DDMMAA
	 * 
	 * @author Sï¿½vio Luiz
	 * @date 22/04/2006
	 * 
	 * @param data
	 * @return Uma string no formato AAAAMMDD
	 */
	public static String recuperaDiaMesAnoCom2DigitosDaData(Date data) {
		String ano = "" + getAno(data);
		if (ano != null && ano.length() > 2) {
			ano = ano.substring(ano.length() - 2, ano.length());
		}
		String mes = "" + getMes(data);
		if (mes.length() == 1) {
			mes = "0" + mes;
		}
		String dia = "" + getDiaMes(data);
		if (dia.length() == 1) {
			dia = "0" + dia;
		}

		return dia + mes + ano;
	}

	/**
	 * Permite executar as atividades do faturamento previamente comandadas
	 * 
	 * [UC0111] Executar Atividade do Faturamento
	 * 
	 * Adiciona mais um ao mï¿½s do anoMesReferencia
	 * 
	 * somaUmMesAnoMesReferencia
	 * 
	 * @author Roberta Costa
	 * @date 04/05/2006
	 * 
	 * @param anoMesReferencia
	 * @return
	 */
	public static Integer somaUmMesAnoMesReferencia(Integer anoMesReferencia) {

		int mes = obterMes(anoMesReferencia.intValue());
		int ano = obterAno(anoMesReferencia.intValue());
		String anoMes = "";

		if (mes >= 12) {
			mes = 1;
			ano = ano + 1;
		} else {
			mes = mes + 1;
		}

		if (mes < 10) {
			anoMes = "" + ano + "0" + mes;
		} else {
			anoMes = "" + ano + mes;
		}
		return Integer.parseInt(anoMes);
	}

	/**
	 * Adiciona mais um ao mï¿½s do anoMesReferencia
	 * 
	 * somaUmMesAnoMesReferencia
	 * 
	 * @author Roberta Costa
	 * @date 04/05/2006
	 * 
	 * @param anoMesReferencia
	 * @return
	 */
	public static Integer somaMesAnoMesReferencia(Integer anoMesReferencia,
			int qtdMeses) {

		int mes = obterMes(anoMesReferencia.intValue());
		int ano = obterAno(anoMesReferencia.intValue());
		String anoMes = "";
		mes = mes + qtdMeses;
		if (mes > 12) {
			while (mes > 12) {
				mes = mes - 12;
				ano = ano + 1;
			}
		}
		if (mes < 10) {
			anoMes = "" + ano + "0" + mes;
		} else {
			anoMes = "" + ano + mes;
		}
		return Integer.parseInt(anoMes);
	}
	
	/**
	 * Subtrair ano ao anoMesReferencia
	 * 
	 * subitrairAnoAnoMesReferencia
	 * 
	 * @param anoMesReferencia
	 * @return Integer
	 */
	public static Integer subtrairAnoAnoMesReferencia(Integer anoMesReferencia,
			int qtdAnos) {

		int mes = obterMes(anoMesReferencia.intValue());
		int ano = obterAno(anoMesReferencia.intValue());
		String anoMes = "";
		ano = ano - qtdAnos;
		if (mes < 10) {
			anoMes = "" + ano + "0" + mes;
		} else {
			anoMes = "" + ano + mes;
		}
		return Integer.parseInt(anoMes);
	}

	/**
	 * Adiciona mais um ao mï¿½s ao mes/ano
	 * 
	 * somaUmMesMesAnoComBarra
	 * 
	 * @author Flï¿½vio Cordeiro
	 * @date 14/02/2007
	 * 
	 * @param mesAno
	 * @return
	 */
	public static String somaMesMesAnoComBarra(String mesAno, int qtdMeses) {

		int mes = new Integer(mesAno.substring(0, 2));
		int ano = new Integer(mesAno.substring(3, 7));

		String mesAnoComBarra = "";
		mes = mes + qtdMeses;
		if (mes > 12) {
			while (mes > 12) {
				mes = mes - 12;
				ano = ano + 1;
			}
		}
		if (mes < 10) {
			mesAnoComBarra = "0" + mes + "/" + ano;
		} else {
			mesAnoComBarra = mes + "/" + ano;
		}
		return mesAnoComBarra;
	}

	/**
	 * [UC0336] Gerar Relatï¿½rio de Acompanhamento do Faturamento
	 * 
	 * Diminui atï¿½ seis meses do anoMesReferencia
	 * 
	 * somaUmMesAnoMesReferencia
	 * 
	 * @author Fernanda Paiva
	 * @date 12/05/2006
	 * 
	 * @param anoMesReferencia
	 * @return
	 */
	public static Integer subtraiAteSeisMesesAnoMesReferencia(
			Integer anoMesReferencia, Integer meses) {

		int mes = obterMes(anoMesReferencia.intValue());
		int ano = obterAno(anoMesReferencia.intValue());

		String anoMes = "";
		String mesDiferenca = "";

		Integer diferencaMes = mes - meses;

		if (diferencaMes <= 0) {
			mes = 12 + diferencaMes;
			ano = ano - 1;
		} else {
			mes = diferencaMes;
		}

		if (mes <= 9) {
			mesDiferenca = "0" + mes;
		} else {
			mesDiferenca = "" + mes;
		}
		anoMes = "" + ano + mesDiferenca;
		return Integer.parseInt(anoMes);
	}

	/**
	 * Mï¿½todo que gera uma senha aleatï¿½ria
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param tamanhoSenha
	 *            Tamanho da senha aleatï¿½ria a ser gerada
	 * @return Senha aleatï¿½ria
	 */
	public static String geradorSenha(int tamanhoSenha) {
		char[] pw = new char[tamanhoSenha];
		int c = 'A';
		int r1 = 0;
		for (int i = 0; i < tamanhoSenha; i++) {
			r1 = (int) (Math.random() * 3);
			switch (r1) {
			case 0:
				c = '0' + (int) (Math.random() * 10);
				break;
			case 1:
				c = 'a' + (int) (Math.random() * 26);
				break;
			case 2:
				c = 'A' + (int) (Math.random() * 26);
				break;
			}
			pw[i] = (char) c;
		}
		return new String(pw);
	}

	// --------------- Calcular Percentual
	// a funcao recebe dois valores, pega o primeiro valor(valor1) multiplica
	// por 100
	// e divide pelo segundo valor(valor2)
	public static String calcularPercentual(String valor1, String valor2) {

		BigDecimal bigValor1 = new BigDecimal(valor1);
		BigDecimal bigValor2 = new BigDecimal(valor2 != null ? valor2 : "1");

		BigDecimal numeroCem = new BigDecimal("100");

		BigDecimal primeiroNumero = bigValor1.multiply(numeroCem);

		BigDecimal resultado = primeiroNumero.divide(bigValor2, 2,
				BigDecimal.ROUND_HALF_UP);

		return resultado + "";
	}

	// --------------- Calcular Percentual
	// a funcao recebe dois valores, pega o primeiro valor(valor1) multiplica
	// por 100
	// e divide pelo segundo valor(valor2)
	public static BigDecimal calcularPercentualBigDecimal(String valor1,
			String valor2) {

		BigDecimal bigValor1 = new BigDecimal(valor1);
		BigDecimal bigValor2 = new BigDecimal(valor2 != null ? valor2 : "1");
		
		return calcularPercentualBigDecimal(bigValor1, bigValor2);
	}

	// --------------- Calcular Percentual
	// a funcao recebe dois valores, pega o primeiro valor(valor1) multiplica
	// por 100
	// e divide pelo segundo valor(valor2)
	public static BigDecimal calcularPercentualBigDecimal(BigDecimal bigValor1,
			BigDecimal bigValor2) {
		
		BigDecimal resultado = new BigDecimal("0.0");
		
		if (bigValor2.compareTo(new BigDecimal("0.0")) != 0){
			
			BigDecimal numeroCem = new BigDecimal("100");
	
			BigDecimal primeiroNumero = bigValor1.multiply(numeroCem);
	
			resultado = primeiroNumero.divide(bigValor2, 2,
					BigDecimal.ROUND_HALF_UP);
		}
		return resultado;
	}

	/**
	 * Mï¿½todo que valida se uma String ï¿½ composta apenas de dï¿½gitos.
	 * 
	 * @author lms
	 * @date 18/07/2006
	 * 
	 * @param string
	 *            String numï¿½rica
	 * @return boolean true, caso a String possa ser convertida em um Integer.
	 *         false, caso contrï¿½rio.
	 */
	public static boolean validarStringNumerica(String string) {
		boolean ehValida = true;
		try {
			Long.valueOf(string);
		} catch (NumberFormatException e) {
			ehValida = false;
		}
		return ehValida;
	}

	/**
	 * Mï¿½todo que converte um objeto qualquer em uma String atravï¿½s do mï¿½todo
	 * toString(). Caso o objeto seja null, retorna uma String vazia "".
	 * 
	 * @author lms
	 * @date 20/07/2006
	 * 
	 * @param objeto
	 *            Um objeto qualquer
	 * @return String objeto.toString(), caso o objeto != null. "", caso
	 *         contrï¿½rio.
	 */
	public static String converterObjetoParaString(Object objeto) {
		String string = "";
		if (objeto != null) {
			string = objeto.toString();
		}
		return string;
	}

	/**
	 * Mï¿½todo que valida se um determinado Integer ï¿½ diferente de null e maior
	 * que ZERO.
	 * 
	 * @author lms
	 * @date 21/07/2006
	 * 
	 * @param numero
	 *            Um numero qualquer
	 * @return boolean true, caso numero != null && numero.compareTo(ZERO) > 0
	 *         false, caso contrï¿½rio.
	 */
	public static boolean validarNumeroMaiorQueZERO(Integer numero) {
		boolean retorno = false;
		if (numero != null && numero.compareTo(0) > 0) {
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Mï¿½todo que converte uma String em um Integer. Caso a String seja null ou
	 * nï¿½o seja um nï¿½mero vï¿½lido, retorna null.
	 * 
	 * @author lms
	 * @date 20/07/2006
	 * 
	 * @param string
	 *            Uma string numï¿½rica
	 * @return Integer Integer.parseInt(string), caso a string seja um nï¿½mero.
	 *         null, caso contrï¿½rio.
	 */
	public static Integer converterStringParaInteger(String string) {
		Integer integer = null;
		try {
			integer = Integer.parseInt(string);
		} catch (NumberFormatException e) {

		}
		return integer;
	}

	/**
	 * Valida a string de acordo com a mï¿½scara passada.
	 * 
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 * 
	 * @param value
	 *            A string a ser validada.
	 * @param mask
	 *            A expressï¿½o regular
	 * @return boolean True se ï¿½ vï¿½lida e False se nï¿½o
	 */
	public static boolean validateMask(String value, String mask) {
		if (mask == null) {
			return true; // sem mï¿½scara, deixa passar
		}
		if (GenericValidator.isBlankOrNull(value)) {
			return true; // ï¿½ como o struts lida com a situaï¿½ï¿½o
		}
		return GenericValidator.matchRegexp(value, mask);
	}

	/**
	 * Mï¿½todo que valida se uma determinada String ï¿½ um nï¿½mero maior que ZERO.
	 * 
	 * @author lms
	 * @date 21/07/2006
	 * 
	 * @param numero
	 *            Um numero qualquer
	 * @return boolean true, caso numero != null && numero.compareTo(ZERO) > 0
	 *         false, caso contrï¿½rio.
	 */
	public static boolean validarNumeroMaiorQueZERO(String string) {
		return validarNumeroMaiorQueZERO(converterStringParaInteger(string));
	}

	/**
	 * Compara duas datas levando em consideraï¿½ï¿½o apenas o dia, mï¿½s e ano.
	 * 
	 * @author lms
	 * @date 10/08/2006
	 * 
	 * @param date1,
	 *            date2 Duas datas
	 * @return int -1, se a data1 > data2 0, se data1 == data2 1, se data1 <
	 *         data2
	 */
	public static int compararDiaMesAno(Date date1, Date date2) {
		int retorno = 0;
		int dia1, dia2, mes1, mes2, ano1, ano2;
		Calendar c = GregorianCalendar.getInstance();

		c.setTime(date1);
		dia1 = c.get(Calendar.DAY_OF_MONTH);
		mes1 = c.get(Calendar.MONTH);
		ano1 = c.get(Calendar.YEAR);

		c.setTime(date2);
		dia2 = c.get(Calendar.DAY_OF_MONTH);
		mes2 = c.get(Calendar.MONTH);
		ano2 = c.get(Calendar.YEAR);

		if (ano1 > ano2) {
			retorno = 1;
		} else if (ano1 < ano2) {
			retorno = -1;
		} else {
			// ano1 == ano2
			if (mes1 > mes2) {
				retorno = 1;
			} else if (mes1 < mes2) {
				retorno = -1;
			} else {
				// mes1 == mes2
				if (dia1 > dia2) {
					retorno = 1;
				} else if (dia1 < dia2) {
					retorno = -1;
				}
			}
		}

		return retorno;
	}

	/**
	 * Retorna uma hora no formato HH:MM a partir de um objeto Date
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 */
	public static String formatarHoraSemSegundos(Date data) {
		StringBuffer dataBD = new StringBuffer();

		if (data != null) {
			Calendar dataCalendar = new GregorianCalendar();

			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
				dataBD.append(dataCalendar.get(Calendar.HOUR_OF_DAY));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.HOUR_OF_DAY));
			}

			dataBD.append(":");

			if (dataCalendar.get(Calendar.MINUTE) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MINUTE));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.MINUTE));
			}

		}

		return dataBD.toString();
	}

	/**
	 * Recupera quantidade de horas entre duas datas
	 * 
	 * @author Leonardo Regis
	 * @date 15/09/2006
	 */
	public static int obterQtdeHorasEntreDatas(Date dataInicial, Date dataFinal) {
		Calendar start = Calendar.getInstance();
		start.setTime(dataInicial);
		// Date startTime = start.getTime();
		if (!dataInicial.before(dataFinal))
			return 0;
		for (int i = 1;; ++i) {
			start.add(Calendar.HOUR, 1);
			if (start.getTime().after(dataFinal)) {
				start.add(Calendar.HOUR, -1);
				return i - 1;
			}
		}
	}

	/**
	 * Transforma um BigDecimal em uma string substituindo o ponto e com o
	 * nï¿½mero de casas decimais determinado.
	 * 
	 * @param numero
	 *            O nï¿½mero a ser transformado
	 * @param casas
	 *            A quantidade de casas decimais
	 * @param agruparMilhares
	 *            Indicador informando se deve agrupar milhares
	 * @author Rafael Francisco Pinto
	 * @return A string no formato especificado
	 */
	public static String formataBigDecimal(BigDecimal numero, int casas,
			boolean agruparMilhares) {

		if (numero == null) {
			numero = new BigDecimal("0.0");
		}

		NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));
		formato.setMaximumFractionDigits(casas);
		formato.setMinimumFractionDigits(casas);
		formato.setGroupingUsed(agruparMilhares);

		return formato.format(numero);
	}

	/**
	 * Converte a String em collection
	 * 
	 * @param separador
	 *            O separador usado ex:(, : ;)
	 * @param campos
	 *            A String que serï¿½ usada
	 * @author Rafael Francisco Pinto
	 * @return Collection com os valores separado da String
	 */
	public static Collection separarCamposString(String separador, String campos) {

		Collection retorno = new LinkedList();

		StringTokenizer st = new StringTokenizer(campos, separador);

		while (st.hasMoreTokens()) {

			retorno.add(st.nextToken());

		}

		return retorno;

	}

	public static BigDecimal calcularValorDebitoComPorcentagem(
			BigDecimal valorDebito, String percentual) {

		BigDecimal retorno = new BigDecimal("0");

		if (percentual.trim().equalsIgnoreCase("70")) {
			retorno = valorDebito.multiply(new BigDecimal("0.7"));
		} else if (percentual.trim().equalsIgnoreCase("50")) {
			retorno = valorDebito.multiply(new BigDecimal("0.5"));
		} else {
			retorno = valorDebito;
		}

		return retorno;
	}

	/*
	 * Retorna o valor monetï¿½rio por extenso, a partir de um BigDecimal
	 */
	public static String valorExtenso(BigDecimal numero) {

		double num = numero.doubleValue();

		String s = "";

		String nome[] = { "UM BILHï¿½O", " BILHï¿½ES", "UM MILHï¿½O", " MILHï¿½ES" };
		long n = (long) num;
		long mil_milhoes;
		long milhoes;
		long milhares;
		long unidades;
		long centavos;
		double frac = num - n;

		if (num == 0) {
			return "ZERO";
		}
		mil_milhoes = (n - n % 1000000000) / 1000000000;
		n -= mil_milhoes * 1000000000;
		milhoes = (n - n % 1000000) / 1000000;
		n -= milhoes * 1000000;
		milhares = (n - n % 1000) / 1000;
		n -= milhares * 1000;
		unidades = n;
		centavos = (long) (frac * 100);
		if ((long) (frac * 1000 % 10) > 5) {
			centavos++;
		}

		if (mil_milhoes > 0) {
			if (mil_milhoes == 1) {
				s += nome[0];
			} else {
				s += numero(mil_milhoes);
				s += nome[1];
			}
			if ((unidades == 0) && ((milhares == 0) && (milhoes > 0))) {
				s += " E ";
			} else if ((unidades != 0) || ((milhares != 0) || (milhoes != 0))) {
				s += ", ";
			}
		}
		if (milhoes > 0) {
			if (milhoes == 1) {
				s += nome[2];
			} else {
				s += numero(milhoes);
				s += nome[3];
			}
			if ((unidades == 0) && (milhares > 0)) {
				s += " E ";
			} else if ((unidades != 0) || (milhares != 0)) {
				s += ", ";
			}
		}
		if (milhares > 0) {
			if (milhares != 1) {
				s += numero(milhares);
			}
			s += " MIL";
			if (unidades > 0) {
				if ((milhares > 100) && (unidades > 100)) {
					s += ", ";
				} else if (((unidades % 100) != 0)
						|| ((unidades % 100 == 0) && (milhares < 10))) {
					s += " E ";
				} else {
					s += " ";
				}
			}
		}
		s += numero(unidades);
		if (num > 0) {
			s += ((long) num == 1L) ? " REAL" : " REAIS";
		}

		if (centavos != 0) {
			s += " e ";
			s += numero(centavos);
			s += (centavos == 1) ? " CENTAVO" : " CENTAVOS";
		}

		return s.toString();
	}

	private static String numero(long n) {

		String u[] = { "", "UM", "DOIS", "TRES", "QUATRO", "CINCO", "SEIS",
				"SETE", "OITO", "NOVE", "DEZ", "ONZE", "DOZE", "TREZE",
				"CATORZE", "QUINZE", "DEZESSEIS", "DEZESSETE", "DEZOITO",
				"DEZENOVE" };
		String d[] = { "", "", "VINTE", "TRINTA", "QUARENTA", "CINQUENTA",
				"SESSENTA", "SETENTA", "OITENTA", "NOVENTA" };
		String c[] = { "", "CENTO", "DUZENTOS", "TREZENTOS", "QUATROCENTOS",
				"QUINHENTOS", "SEISCENTOS", "SETECENTOS", "OITOCENTOS",
				"NOVECENTOS" };

		String extenso_do_numero = new String();

		if ((n < 1000) && (n != 0)) {
			if (n == 100) {
				extenso_do_numero = "CEM";
			} else {
				if (n > 99) {
					extenso_do_numero += c[(int) (n / 100)];
					if (n % 100 > 0) {
						extenso_do_numero += " E ";
					}
				}
				if (n % 100 < 20) {
					extenso_do_numero += u[(int) n % 100];
				} else {
					extenso_do_numero += d[((int) n % 100) / 10];
					if ((n % 10 > 0) && (n > 10)) {
						extenso_do_numero += " E ";
						extenso_do_numero += u[(int) n % 10];
					}
				}
			}
		} else if (n > 999) {
			extenso_do_numero = "<<ERRO: NUMERO > 999>>";
		}
		return extenso_do_numero;
	}

	/**
	 * Compara duas datas sem verificar a hora.
	 * 
	 * @param data1
	 *            A primeira data
	 * @param data2
	 *            A segunda data
	 * 
	 * @author Rafael Francisco Pinto
	 * 
	 * @return -1 se a data1 for menor que a data2, 0 se as datas forem iguais,
	 *         1 se a data1 for maior que a data2.
	 */
	public static int compararData(Date data1, Date data2) {

		Calendar calendar1;
		Calendar calendar2;

		int ano1;
		int ano2;
		int mes1;
		int mes2;
		int dia1;
		int dia2;

		int resultado;

		calendar1 = Calendar.getInstance();
		calendar1.setTime(data1);

		ano1 = calendar1.get(Calendar.YEAR);
		mes1 = calendar1.get(Calendar.MONTH);
		dia1 = calendar1.get(Calendar.DAY_OF_MONTH);

		calendar2 = Calendar.getInstance();
		calendar2.setTime(data2);

		ano2 = calendar2.get(Calendar.YEAR);
		mes2 = calendar2.get(Calendar.MONTH);
		dia2 = calendar2.get(Calendar.DAY_OF_MONTH);

		if (ano1 == ano2) {

			if (mes1 == mes2) {

				if (dia1 == dia2) {
					resultado = 0;
				} else if (dia1 < dia2) {
					resultado = -1;
				} else {
					resultado = 1;
				}
			} else if (mes1 < mes2) {
				resultado = -1;
			} else {
				resultado = 1;
			}
		} else if (ano1 < ano2) {
			resultado = -1;
		} else {
			resultado = 1;
		}
		return resultado;
	}

	/**
	 * Compara duas datas verificando hora, minuto, segundo e milisegundo.
	 * 
	 * @param data1
	 *            A primeira data
	 * @param data2
	 *            A segunda data
	 * 
	 * @author Rafael Francisco Pinto
	 * 
	 * @return -1 se a data1 for menor que a data2, 0 se as datas forem iguais,
	 *         1 se a data1 for maior que a data2.
	 */
	public static int compararDataTime(Date data1, Date data2) {
		long dataTime1 = data1.getTime();
		long dataTime2 = data2.getTime();
		int result;
		if (dataTime1 == dataTime2) {
			result = 0;
		} else if (dataTime1 < dataTime2) {
			result = -1;
		} else {
			result = 1;
		}
		return result;
	}

	/**
	 * Verifica se a hora estï¿½ no intervalo
	 * 
	 * @param data1
	 *            A primeira hora do intervalo
	 * @param data2
	 *            A segunda hora do intervalo
	 * @param data3
	 *            A terceira hora serï¿½ usado para verficar se esta no intervelo
	 * 
	 * @author Rafael Francisco Pinto
	 * 
	 * @return true estï¿½ no intervalo false nï¿½o estï¿½ no intervalo.
	 */
	public static boolean verifcarIntervaloHora(Date intervaloInicio,
			Date intervaloFim, Date dataAnalisada) {

		boolean ehIgual = false;

		if (dataAnalisada.compareTo(intervaloInicio) != -1
				&& dataAnalisada.compareTo(intervaloFim) != 1) {

			ehIgual = true;
		}

		return ehIgual;
	}

	/**
	 * Verifica se a data estï¿½ no intervalo
	 * 
	 * @param data1
	 *            A primeira data do intervalo
	 * @param data2
	 *            A segunda data do intervalo
	 * @param data3
	 *            A terceira data serï¿½ usado para verficar se esta no intervelo
	 * 
	 * @author Rafael Francisco Pinto
	 * 
	 * @return true estï¿½ no intervalo false nï¿½o estï¿½ no intervalo.
	 */
	public static boolean verifcarIntervaloData(Date intervaloInicio,
			Date intervaloFim, Date dataAnalisada) {

		boolean ehIgual = false;

		if (dataAnalisada.compareTo(intervaloInicio) != -1
				&& dataAnalisada.compareTo(intervaloFim) != 1) {

			ehIgual = true;
		}

		return ehIgual;
	}

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mï¿½s
	 * @param ano
	 *            O ano
	 * @return Uma instï¿½ncia da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, 0, 0, 0);

		return calendario.getTime();
	}

	/**
	 * retorna sequencial formatado(Ex.: 000.001)
	 */
	public static String retornaSequencialFormatado(int sequencial) {

		// sequencial impressão
		String retorno = Util.adicionarZerosEsquedaNumero(6, "" + sequencial);

		retorno = retorno.substring(0, 3) + "." + retorno.substring(3, 6);

		return retorno;
	}
	
	public static String retornaSequencialFormatado( int sequencial, int tamanho ) {

		// sequencial impressão
		String retorno = Util.adicionarZerosEsquedaNumero(tamanho, "" + sequencial);

		retorno = retorno.substring(0, 3) + "." + retorno.substring(3, tamanho);

		return retorno;
	}	

	/**
	 * Adciona ou subtrai o mes na data ou mesAno. 
	 * Caso queira subtrair manda o valor dos meses negativo ex.(-5) vai subtrair 5 meses da data ou do mesAno
	 * 
	 * @param data
	 * @param meses
	 * @param anoMes
	 * 
	 * @author Rafael Francisco Pinto
	 * 
	 * @return a descriï¿½ï¿½o do mï¿½s
	 */
	public static Date adcionarOuSubtrairMesesAData(Date data, int meses,
			int anoMes) {

		Calendar calendar = Calendar.getInstance();

		if (data == null) {
			String anoMesString = "" + anoMes;

			String ano = anoMesString.substring(0, 4);
			String mes = anoMesString.substring(4, 6);

			data = Util.criarData(1, Integer.parseInt(mes), Integer
					.parseInt(ano));

		}

		calendar.setTime(data);
		calendar.add(Calendar.MONTH, meses);

		return calendar.getTime();
	}

	/**
	 * Retorna a descriï¿½ï¿½o do mes
	 * 
	 * @param mes
	 * 
	 * @author Rafael Francisco Pinto
	 * 
	 * @return a descriï¿½ï¿½o do mï¿½s
	 */

	public static String retornaDescricaoMes(int mes) {

		String meses[] = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio",
				"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
				"Dezembro" };

		String mesPorExtenso = meses[mes - 1];// mes-1 pq o indice do array
		// comeï¿½a no zero

		return mesPorExtenso;
	}
	
	/**
	 * Retorna a descriï¿½ï¿½o abreviado do mes
	 * 
	 * @param mes
	 * 
	 * @author Rafael Francisco Pinto
	 * 
	 * @return a descriï¿½ï¿½o do mï¿½s
	 */

	public static String retornaDescricaoAbreviadoMes(int mes) {

		String meses[] = { "Jan", "Fev", "Mar", "Abr", "Mai",
				"Jun", "Jul", "Ago", "Set", "Out", "Nov",
				"Dez" };

		String mesPorExtenso = meses[mes - 1];// mes-1 pq o indice do array
		// comeï¿½a no zero

		return mesPorExtenso;
	}

	/**
	 * Retorna a data por extenso
	 * 
	 * @param Date
	 * 
	 * @author Rafael Francisco Pinto
	 * 
	 * @return a data por extenso
	 */

	public static String retornaDataPorExtenso(Date data) {
		int dia = getDiaMes(data);
		int mes = getMes(data);
		int ano = getAno(data);
		
		String dataExtenso = dia+" de "+retornaDescricaoMes(mes)+" de "+ano;
		
		return dataExtenso;
	}
	
	/**
	 * Retorna a descriï¿½ï¿½o abreviada do ano Mes
	 * 
	 * @param anoMes
	 * 
	 * @author Rafael Francisco Pinto
	 */

	public static String retornaDescricaoAnoMes(String anoMes) {

		int mes = new Integer(anoMes.substring(4, 6));
		String ano = anoMes.substring(2, 4);
		
		String descricao = retornaDescricaoAbreviadoMes(mes)+"/"+ano;
		
		return descricao;
	}	

	/**
	 * Retorna a matrï¿½cula do imï¿½vel formatada.ex.:1234567 retorna 123456.7
	 * 
	 * 
	 * @author Sï¿½vio Luiz
	 */

	public static String retornaMatriculaImovelFormatada(Integer matriculaImovel) {

		String matriculaImovelFormatada = "";
		if (matriculaImovel != null && !matriculaImovel.equals("")) {
			matriculaImovelFormatada = "" + matriculaImovel;
			int quantidadeCaracteresImovel = matriculaImovel.toString()
					.length();
			matriculaImovelFormatada = matriculaImovelFormatada.substring(0,
					quantidadeCaracteresImovel - 1)
					+ "."
					+ matriculaImovelFormatada.substring(
							quantidadeCaracteresImovel - 1,
							quantidadeCaracteresImovel);
		}

		return matriculaImovelFormatada;
	}

	/**
	 * Retorna uma hora no formato HH:MM a partir de um objeto Date
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 */
	public static String formatarHoraSemSegundos(String horaMinuto) {

		String retorno = null;

		if (horaMinuto != null && !horaMinuto.equalsIgnoreCase("")) {

			String[] vetorHora = horaMinuto.split(":");

			if (vetorHora[0].trim().length() < 2) {
				retorno = "0" + vetorHora[0] + ":";
			} else {
				retorno = vetorHora[0] + ":";
			}

			if (vetorHora[1].trim().length() < 2) {
				retorno = retorno + "0" + vetorHora[1];
			} else {
				retorno = retorno + vetorHora[1];
			}
		}

		return retorno.trim();
	}

	/**
	 * Gera uma data a partir do ano/mï¿½s de referï¿½ncia setando o ï¿½ltimo dia do
	 * mï¿½s.
	 * 
	 * Utilizado no UC0302
	 * 
	 * @author Pedro Alexandre
	 * @date 19/03/2007
	 * 
	 * @param anoMesRefencia
	 * @return
	 */
	public static Date gerarDataApartirAnoMesRefencia(Integer anoMesReferencia) {

		Date retorno = null;

		String dataFormatacao = "" + anoMesReferencia;

		Integer ano = new Integer(dataFormatacao.substring(0, 4));

		Integer mes = new Integer(dataFormatacao.substring(4, 6));

		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);

		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.YEAR, ano);
		Integer dia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, dia);

		retorno = calendar.getTime();

		return retorno;
	}

	/**
	 * Gera uma data a partir do ano/mï¿½s de referencia setando o primeiro dia do
	 * mes.
	 * 
	 * 
	 * @author Savio Luiz
	 * @date 19/03/2007
	 * 
	 * @param anoMesRefencia
	 * @return
	 */
	public static Date gerarDataInicialApartirAnoMesRefencia(
			Integer anoMesReferencia) {

		Date retorno = null;

		String dataFormatacao = "" + anoMesReferencia;

		Integer ano = new Integer(dataFormatacao.substring(0, 4));

		Integer mes = new Integer(dataFormatacao.substring(4, 6));

		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.HOUR_OF_DAY, 00);

		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.DATE, 1);

		retorno = calendar.getTime();

		return retorno;
	}

	/**
	 * Formata um bigDecimal para String tirando os pontos.
	 * 
	 * @author Sï¿½vio Luiz
	 * @date 13/04/2007
	 * 
	 * @param valor
	 * @return
	 */
	public static String formatarBigDecimalParaString(BigDecimal valor) {

		String valorItemAnterior = "" + valor;
		valorItemAnterior = valorItemAnterior.replace(".", "");
		return valorItemAnterior;
	}

	/**
	 * Author: Raphael Rossiter
	 * 
	 * Data: 12/04/2007
	 * 
	 */
	public static Collection<Categoria> montarColecaoCategoria(
			Collection colecaoSubcategorias) {

		Collection<Categoria> colecaoRetorno = null;

		if (colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()) {

			colecaoRetorno = new ArrayList();

			Iterator colecaoSubcategoriaIt = colecaoSubcategorias.iterator();
			Categoria categoriaAnterior = null;
			Subcategoria subcategoria;
			int totalEconomiasCategoria = 0;

			while (colecaoSubcategoriaIt.hasNext()) {

				subcategoria = (Subcategoria) colecaoSubcategoriaIt.next();

				if (categoriaAnterior == null) {
					totalEconomiasCategoria = subcategoria
							.getQuantidadeEconomias();
				} else if (subcategoria.getCategoria()
						.equals(categoriaAnterior)) {
					totalEconomiasCategoria = totalEconomiasCategoria
							+ subcategoria.getQuantidadeEconomias();
				} else {
					categoriaAnterior
							.setQuantidadeEconomiasCategoria(totalEconomiasCategoria);
					colecaoRetorno.add(categoriaAnterior);

					totalEconomiasCategoria = subcategoria
							.getQuantidadeEconomias();
				}

				categoriaAnterior = subcategoria.getCategoria();
			}


			categoriaAnterior
					.setQuantidadeEconomiasCategoria(totalEconomiasCategoria);
			colecaoRetorno.add(categoriaAnterior);
		}

		return colecaoRetorno;
	}

	// Formata o codigo de barra colocando os - e os espacos.
	// Flï¿½vio Cordeiro
	public static String formatarCodigoBarra(String codigoBarra) {
		String retorno = "";

		retorno = codigoBarra.substring(0, 11) + "-"
				+ codigoBarra.substring(11, 12) + " "
				+ codigoBarra.substring(12, 23) + "-"
				+ codigoBarra.substring(23, 24) + " "
				+ codigoBarra.substring(24, 35) + "-"
				+ codigoBarra.substring(35, 36) + " "
				+ codigoBarra.substring(36, 47) + "-"
				+ codigoBarra.substring(47, 48);

		return retorno;
	}

	/**
	 * Author: Rafael Pinto
	 * 
	 * Formata o numero com (.) ponto 
	 * Ex: Numero = 1000 Resultado = 1.000
	 * 
	 * Data: 22/11/2007
	 * 
	 */
	public static String agruparNumeroEmMilhares(Integer numero){
		String retorno = "0";
		if(numero != null){
			NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));
			retorno = formato.format(numero);
		}
		return retorno;
	}
	/**
	 * Author: Raphael Rossiter
	 * 
	 * Data: 23/08/2007
	 * 
	 */
	public static java.sql.Date getSQLDate(Date data) {
		
		java.sql.Date dt = new java.sql.Date( data.getTime() );
	
		return dt;
	}
	
	/**
	 * Author: Raphael Rossiter
	 * 
	 * Data: 23/08/2007
	 * 
	 */
	public static Timestamp getSQLTimesTemp(Date data) {
		
		Timestamp dt = new Timestamp( data.getTime() );
	
		return dt;
	}

	
	public static String separarStringPorLetraMaiuscula(String target) {

		StringBuilder builder = new StringBuilder(target);

		char[] cs = target.toCharArray();
		ArrayList<Integer> indicesMaiusculos = new ArrayList<Integer>();

		for (int i = 0; i < cs.length; i++) {
			if (Character.isUpperCase(cs[i])) {
				indicesMaiusculos.add(i);

			}
		}

		int i = 0;
		for (Iterator iter = indicesMaiusculos.iterator(); iter.hasNext();) {
			int indice = (Integer) iter.next();
			if (indice > 0) {
				builder.insert(indice + i, " ");
				i++;

			}
		}

		return builder.toString();
	}

    
    public static boolean validarDiaMesAnoSemBarra(String diaMesAnoReferencia) {
        boolean diaMesAnoInvalido = false;

        if (diaMesAnoReferencia.length() == 8) {

            String dia = diaMesAnoReferencia.substring(0, 2);
            String mes = diaMesAnoReferencia.substring(2, 4);

            try {
                int mesInt = Integer.parseInt(mes);
                // int anoInt = Integer.parseInt(ano);
                int diaInt = Integer.parseInt(dia);

                if (mesInt <= 0 || mesInt > 12) {
                    diaMesAnoInvalido = true;
                }
                if (diaInt <= 0 || diaInt > 31) {
                    diaMesAnoInvalido = true;
                }
            } catch (NumberFormatException e) {
                diaMesAnoInvalido = true;
            }

        } else {
            diaMesAnoInvalido = true;
        }

        return diaMesAnoInvalido;
    }
    
    /**
     * Mï¿½todo que recebe uma data com string no formato DDMMAAAA e converte para
     * o objeto Date.
     */
    public static Date converteStringSemBarraParaDate(String data) {
        Date retorno = null;
        String dataInvertida = data.substring(0, 2) + "/"
                + data.substring(2, 4) + "/" + data.substring(4, 8);
        SimpleDateFormat dataTxt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            retorno = dataTxt.parse(dataInvertida);
        } catch (ParseException e) {
            throw new IllegalArgumentException(data
                    + " nï¿½o tem o formato dd/MM/yyyy.");
        }
        return retorno;
    }
    
    
    /**
	 * Retorna o valor de cnpjFormatado
	 * 
	 * @return O valor de cnpjFormatado
	 */
	public static String formatarCnpj(String cnpj) {
		String cnpjFormatado = cnpj;
		String zeros = "";
		
		if (cnpjFormatado != null) {
			
			for (int a = 0; a < (14 - cnpjFormatado.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			cnpjFormatado = zeros.concat(cnpjFormatado);
			
			cnpjFormatado = cnpjFormatado.substring(0, 2) + "."
					+ cnpjFormatado.substring(2, 5) + "."
					+ cnpjFormatado.substring(5, 8) + "/"
					+ cnpjFormatado.substring(8, 12) + "-"
					+ cnpjFormatado.substring(12, 14);
		}
		
		return cnpjFormatado;
	}

	/**
	 * Metodo Expecialmente Util
	 */
	public static String formatarCPFApresentacao(String cpf) {
		return cpf;
	}
	
    /**
	 * Retorna o valor de cnpjFormatado
	 * 
	 * @return O valor de cnpjFormatado
	 */
	public static String formatarCpf(String cpf) {
		
		String cpfFormatado = cpf;
		String zeros = "";
		
		if (cpfFormatado != null) {
			
			for (int a = 0; a < (11 - cpfFormatado.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			cpfFormatado = zeros.concat(cpfFormatado);
			
			cpfFormatado = cpfFormatado.substring(0, 3) + "."
					+ cpfFormatado.substring(3, 6) + "."
					+ cpfFormatado.substring(6, 9) + "-"
					+ cpfFormatado.substring(9, 11);
		}
		
		return cpfFormatado;
	}
	
	/**
	 * Passa a data formatada dd/mm/aaaa e retorna o ano mes.
	 * 
	 * Nome:Sï¿½vio Luiz
	 */
	public static Integer recuperaAnoMesDataString(String dataFormatada){
		Integer anoMes = null;
		
		try{
			anoMes = new Integer(dataFormatada.substring(6,10) + dataFormatada.substring(3,5));
		}catch(IllegalArgumentException e){
			anoMes = 0;
		}
		
		return anoMes;
	}
	
	/**
	 * Verifica se o parametro informado ï¿½ diferente de null
	 * e diferente de "" e diferente da constante numero
	 * nao informado
	 * @param parametro
	 */
	public static boolean parametroNumericoValido( String parametro ){
		return parametro != null &&
		         !parametro.equals( "" ) &&
		         !parametro.equals( "-1" ) &&
		         !parametro.equals( ConstantesSistema.NUMERO_NAO_INFORMADO );		
	}
	
	/**
	 * Adiciona zeros a esqueda do nï¿½mero truncando informado tamamho mï¿½ximo campo 6
	 * Nï¿½mero 16 retorna 000016
	 * 
	 * @param tamanhoMaximoCampo
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @param numero
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String adicionarZerosEsquedaNumeroTruncando(int tamanhoMaximoCampo,
			String numero) {
		
		
		String zeros = "";

		String retorno = null;
		
		if (numero != null && !numero.equals("")) {
			for (int a = 0; a < (tamanhoMaximoCampo - numero.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			retorno = zeros.concat(numero);
		} else {
			for (int a = 0; a < tamanhoMaximoCampo; a++) {
				zeros = zeros.concat("0");
			}
			// retorna os zeros
			// caso o numero seja nulo
			retorno = zeros;
		}
		if (tamanhoMaximoCampo < retorno.length()) {
			// trunca a String
			String strTruncado = retorno.substring(0, tamanhoMaximoCampo);
			retorno = strTruncado;
		}
		return retorno;
	}
	
	/**
	 * Validaï¿½ï¿½o de CPF
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param s_aux
	 * @return boolean
	 */
	public static boolean validacaoCPF(String s_aux){
	    //------- Rotina para CPF        
	       if (s_aux.length() == 11 )
	       {
	           int     d1, d2, d3; 
	           int     digito1, digito2, resto; 
	           int     digitoCPF; 
	           String  nDigResult; 
	           String  nDigVerif = s_aux.substring(0,1);
	           d1 = d2 = 0; 
	           d3 = 1;
	           digito1 = digito2 = resto = 0; 
	           boolean digVerifIgual = true;
	           
	           for (int n_Count = 1; n_Count < s_aux.length() -1; n_Count++) 
	           { 
	              digitoCPF = Integer.valueOf (s_aux.substring(n_Count -1, n_Count)).intValue(); 
	    //--------- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante. 
	              d1 = d1 + ( 11 - n_Count ) * digitoCPF; 
	    //--------- Para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior. 
	              d2 = d2 + ( 12 - n_Count ) * digitoCPF; 
	           }
	    //--------- Primeiro resto da divisï¿½o por 11. 
	           resto = d1 % 11; 
	    //--------- Se o resultado for 0 ou 1 o digito ï¿½ 0 caso contrï¿½rio o digito ï¿½ 11 menos o resultado anterior. 
	           if (resto < 2) 
	              digito1 = 0; 
	           else 
	              digito1 = 11 - resto; 
	           d2 += 2 * digito1; 
	    //--------- Segundo resto da divisï¿½o por 11. 
	           resto = d2 % 11; 
	    //--------- Se o resultado for 0 ou 1 o digito ï¿½ 0 caso contrï¿½rio o digito ï¿½ 11 menos o resultado anterior. 
	           if (resto < 2) 
	              digito2 = 0; 
	           else 
	              digito2 = 11 - resto; 
	    //--------- Digito verificador do CPF que estï¿½ sendo validado. 
	           String nDigVerific = s_aux.substring (s_aux.length()-2, s_aux.length()); 
	    //--------- Concatenando o primeiro resto com o segundo. 
	           nDigResult = String.valueOf(digito1) + String.valueOf(digito2); 
	    //--------- Comparar o digito verificador do cpf com o primeiro resto + o segundo resto. 
	    
	   //---------- Verifica se todos os dï¿½gitos estï¿½o repetidos
	           while(d3 < s_aux.length()){
	        	   
	        	   if(!s_aux.substring(d3, d3+1).equals(nDigVerif)){
	        		   digVerifIgual = false;
	        	   }
	        	   d3 += 1;
	           }
	           
	           if(digVerifIgual){
	        	   return false;
	           }
	           
	           return nDigVerific.equals(nDigResult); 
	        }
	       else 
	            return false;	        
	}
	
	/**
	 * Validaï¿½ï¿½o de CNPJ
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param s_aux
	 * @return boolean
	 */
	public static boolean validacaoCNPJ(String s_aux){
		
		 //-------- Rotina para CNPJ         
        if (s_aux.length() == 14)
        {
            int soma = 0, dig; 
            String cnpj_calc = s_aux.substring(0,12); 
            char[] chr_cnpj = s_aux.toCharArray(); 
    //--------- Primeira parte 
            for( int i = 0; i < 4; i++ ) 
            if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 ) 
              soma += (chr_cnpj[i] - 48) * (6 - (i + 1)); 
            for( int i = 0; i < 8; i++ ) 
            if ( chr_cnpj[i+4]-48 >=0 && chr_cnpj[i+4]-48 <=9 ) 
                soma += (chr_cnpj[i+4] - 48) * (10 - (i + 1)); 
            dig = 11 - (soma % 11); 
            cnpj_calc += ( dig == 10 || dig == 11 ) ?
                "0" : Integer.toString(dig); 
    //--------- Segunda parte 
            soma = 0; 
            for ( int i = 0; i < 5; i++ ) 
            if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 ) 
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1)); 
            for ( int i = 0; i < 8; i++ ) 
            if ( chr_cnpj[i+5]-48 >=0 && chr_cnpj[i+5]-48 <=9 ) 
                soma += (chr_cnpj[i+5] - 48) * (10 - (i + 1)); 
            dig = 11 - (soma % 11); 
            cnpj_calc += ( dig == 10 || dig == 11 ) ? 
                        "0" : Integer.toString(dig); 
            return s_aux.equals(cnpj_calc); 
        }
        else 
            return false;

	}
   

	
	/**
	 * Trunca String
	 * 
	 * @param tamanhoMaximoCampo
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @param str
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String truncarString(String str, int tamanhoMaximoCampo) {
		
		String retorno = str;
		
		if (tamanhoMaximoCampo < str.length()) {
			// trunca a String
			String strTruncado = retorno.substring(0, tamanhoMaximoCampo);
			retorno = strTruncado;
		}
		return retorno;
	}
	
	
	public static String formatarAnoMesParaMesAnoSemZeroNoMes(String anoMes) {

		String anoMesFormatado = "";
		String anoMesRecebido = "" + anoMes;
		if (anoMesRecebido.length() < 6) {
			anoMesFormatado = anoMesRecebido;
		} else {
			String mes = anoMesRecebido.substring(4, 6);
			Integer mesAux = new Integer(mes);
			if (mesAux < 10) {
				mes = mes.substring(1, 2);
			}

			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}
		return anoMesFormatado;
	}
	
	/**
	 * Converte a data passada em string sem 0 antes do mes 
	 * 
	 * 
	 * @param data
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static String formatarDataSemZeroAntesMes(Date data) {
		String retorno = "";
		if (data != null) { // 1
			Calendar dataCalendar = new GregorianCalendar();
			StringBuffer dataBD = new StringBuffer();

			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH) + "/");
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH)
						+ "/");
			}

			// Obs.: Janeiro no Calendar ï¿½ mï¿½s zero
			if ((dataCalendar.get(Calendar.MONTH) + 1) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH) + 1 + "/");
			} else {
				dataBD.append((dataCalendar.get(Calendar.MONTH) + 1) + "/");
			}

			dataBD.append(dataCalendar.get(Calendar.YEAR));
			retorno = dataBD.toString();
		}
		return retorno;
	}
	
	/**
	 * Author: Vivianne Sousa 
	 * Data: 21/11/2008
	 * Adiciona nï¿½ de dias uteis para uma data
	 */
	public static Date adicionarNumeroDiasUteisDeUmaData(Date data, int numeroDias,
			Collection colecaoFeriadosNacionais, Collection colecaoFeriadosMunicipais) {
		
		int cont = 0;
		while (numeroDias != cont) {
			
			data = Util.adicionarNumeroDiasDeUmaData(data,+1);
			
			if(ehDiaUtil(data,colecaoFeriadosNacionais,colecaoFeriadosMunicipais)){
				cont = cont + 1;
			}
		}

		// retorna a nova data
		return data;
	}

	public static boolean verificarIdNaoVazio(String id) {
		if (id == null || id.equals("null") || id.trim().equals("") || id.trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			return false;
		}
		return true;		
	}

	public static boolean verificarIdNaoVazio(Integer id) {
		return  id != null && id.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO;		
	}

	public static boolean verificarNaoVazio(String valor) {
		if (valor == null || valor.trim().equals("") || valor.equals(null) || valor.equals("null")) {
			return false;
		}
		return true;		
	}

	public static boolean verificarVazio(String valor) {
		return valor == null || valor.trim().equals("") || valor.equals("null");
	}
	
	/**
	 * Author: Vinicius Medeiros
	 * Data: 11/02/2009
	 * Formatar CEP
	 */
	public static String formatarCEP(String codigo) {

		String retornoCEP = null;
		
		String parte1 = codigo.substring(0, 2);
		String parte2 = codigo.substring(2, 5);
		String parte3 = codigo.substring(5, 8);
						
		retornoCEP = parte1 + "." + parte2 + "-" + parte3; 
		
		return retornoCEP;
	}
	
	/**
	 * Author: Vinicius Medeiros
	 * Data: 11/02/2009
	 * Retirar formatacao CEP
	 */
	public static String retirarFormatacaoCEP(String codigo) {

		String retornoCEP = null;
		
		String parte1 = codigo.substring(0, 2);
		String parte2 = codigo.substring(3, 6);
		String parte3 = codigo.substring(7, 10);
						
		retornoCEP =  parte1+parte2+parte3;
		
		return retornoCEP;
	}
	
	
	/**
	 * Retorna uma string delimitando as casas decimais com ponto
	 *
	 * @author Raphael Rossiter
	 * @date 18/03/2009
	 *
	 * @param numero
	 * @return String 
	 */
	public static String formatarBigDecimalComPonto(BigDecimal numero) {

		if (numero == null) {
			numero = new BigDecimal("0.00");
		}

		NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));
		formato.setMaximumFractionDigits(2);
		formato.setMinimumFractionDigits(2);
		formato.setGroupingUsed(false);

		return (formato.format(numero)).replace(",", ".");
	}
	
	/**
	 * Retorna a quantidade de meses entre o primeiro anomï¿½s e o segundo anomï¿½s
	 * Observaï¿½ï¿½o: O primeiro AnoMï¿½s deve ser Maior que o segundo AnoMï¿½s 
	 *
	 * @author Sï¿½vio Luiz
	 * @date 11/05/2009
	 *
	 * @param numero
	 * @return String 
	 */
	public static Integer retornaQuantidadeMeses(Integer anoMesMaior,Integer anoMesMenor){
		Integer quantidadeMeses = 0;
		if(anoMesMaior != null && anoMesMenor != null){
			String anoMes1String = ""+anoMesMaior;
			Integer ano1 = new Integer(anoMes1String.substring(0,4));
			Integer mes1 = new Integer(anoMes1String.substring(4,6));
			String anoMes2String = ""+anoMesMenor;
			Integer ano2 = new Integer(anoMes2String.substring(0,4));
			Integer mes2 = new Integer(anoMes2String.substring(4,6));
			if(anoMesMaior >anoMesMenor){
			 	if (ano1.equals(ano2)){
			 		quantidadeMeses = mes1 - mes2;
			 	}else{
			 		if (ano1 > ano2){
			 			quantidadeMeses = quantidadeMeses + ((12*(ano1-ano2)) - mes2);
			 			quantidadeMeses = quantidadeMeses + mes1;
			 		}
			 	}
			}
		}
		return quantidadeMeses;
	}
	
	
	/**
	 * Escreve a String indicada no arquivo representado pelo Writer
	 * Usado na escrita incremental de arquivos 
	 *
	 * @author Sï¿½vio Luiz
	 * @date 02/06/2009
	 *
	*/
	public static void gerarArquivoPorPartes(BufferedWriter out, StringBuilder stringParaGravacao, boolean inserirQuebraLinha) throws IOException {
		
		if (inserirQuebraLinha) {
			
			stringParaGravacao.append(System.getProperty("line.separator"));
		}
		out.write(stringParaGravacao.toString());
		out.flush();
		stringParaGravacao = null;
	}
	
	/**
	 * Calcula a quantidade de anos completos, existentes entre duas datas 
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2009
	 *
	 * @param dataInicial
	 * @param dataFinal
	 * @return int
	 */
	public static int anosEntreDatas(Date dataInicial, Date dataFinal){
		
		int idade = 0;

		while (compararData(dataInicial, dataFinal) == -1){
			
			int sDiaInicial = getDiaMes(dataInicial);
	        int sMesInicial = getMes(dataInicial);
			int sAnoInicial = getAno(dataInicial);

			int sDiaFinal = getDiaMes(dataFinal);
	        int sMesFinal = getMes(dataFinal);
			int sAnoFinal = getAno(dataFinal);

			sAnoInicial++;
			dataInicial = criarData(sDiaInicial, sMesInicial, sAnoInicial);
			
			if (sAnoInicial == sAnoFinal){
				
				if (sMesInicial < sMesFinal ||
					( sMesInicial == sMesFinal && sDiaInicial <= sDiaFinal )){
					
					idade++;
				}

				break;
			}
			
			idade++;

		}
		
		return idade;
	}
	
	/**
	 * Mï¿½todo que recebe uma string e verifica se a string sï¿½ tem numeros.
	 *
	 * @author Raphael Rossiter
	 * @date 17/06/2009
	 *
	 * @param valor
	 * @return boolean
	 */
	public static boolean validarValorLongoNaoNumerico(String valor) {
		boolean numeroNaoNumerico = false;
		try {

			Long.parseLong(valor);
		} catch (NumberFormatException e) {
			numeroNaoNumerico = true;
		}
		return numeroNaoNumerico;
	}

	/**
	 * Mï¿½todo retorna true se a coleï¿½ï¿½o for nula ou vazia
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 *
	 */
	public static boolean isVazioOrNulo(Collection<? extends Object> colecao) {
		
		return colecao == null || colecao.isEmpty();
	}

	/**
	 * Mï¿½todo retorna true se o array for nulo ou vazio
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 *
	 */
	public static boolean isVazioOrNulo(Object[] array) {
		
		return array == null || array.length == 0;
	}

	/**
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
            response += (char) tab[ value - 48 ];
        }
        
        return response;
	}
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String decrypt(String str) {
        int tab[] = {77,110,70,114,90,100,86,103,111,75};
        int i;
        int j;
        int value = 0;
        int len = str.length();
        String response = "";
        
        for (i=0; i < len; i++) {
            value = (int) str.charAt(i);
            for (j = 0; j < 10; j++) {
                if (value == tab[j]) {
                        response += String.valueOf(j).trim();
                }
            }
        }
        return response;
	}
	/**
	 * Método que recebe um arquivo e retorna a extensão do mesmo.
	 *
	 * @author Raphael Rossiter
	 * @date 29/07/2009
	 *
	 * @param FileItem
	 * @return String
	 */
	public static String obterExtensaoDoArquivo(FileItem fileItem){
		
		String retorno = "";
		
		String nomeArquivo = fileItem.getName().toUpperCase();   
        String ext[] = nomeArquivo.split("\\.");   
        int i = ext.length;  
  
        if(i > 1){
        	retorno = ext[i-1];
        }
		
		return retorno;
	}

	/**
	 * Mï¿½todo que recebe o nome do arquivo e retorna a extensï¿½o do mesmo.
	 * @author Amelia Pessoa
	 * @date 15/12/2011
	 * @param String
	 * @return String
	 */
	public static String obterExtensaoDoArquivo(String nomeArquivo){
		
		String retorno = "";
		
		String nomeArquivoInterno = nomeArquivo.toUpperCase();   
        String ext[] = nomeArquivoInterno.split("\\.");   
        int i = ext.length;  
  
        if(i > 1){
        	retorno = ext[i-1];
        }
		
		return retorno;
	}
	/**
	 * Retorna true se o combo(parametro campo) nï¿½o ï¿½ branco nem nulo
	 * e se nï¿½o ï¿½ igual a ConstantesSistema.NUMERO_NAO_INFORMADO.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	public static boolean isCampoComboboxInformado(String campo){
		if( !verificarNaoVazio(campo) ){
			return false;
		}
		
		if(campo.trim().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			return false;
		}
		
		return true;
	}

	/**
	 * Retorna true se o combo multiplo(parametro campo) tem pelo menos
	 * tamanho 1 e que esse elemento seja diferente de branco,nulo
	 * e ConstantesSistema.NUMERO_NAO_INFORMADO.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	public static boolean isCampoComboboxMultiploInformado(String[] campo){
		if(isVazioOrNulo(campo)){
			return false;
		}
		
		if(campo.length == 1 && !isCampoComboboxInformado(campo[0])){
			return false;
		}
	
		return true;
	}
	
	/** 
	 * Passa um Timestamp e retorna a HH:mm:ss como String 
	 */
	public static String getHoraMinutoSegundoTimestamp(Timestamp timestamp) {
		Long time = timestamp.getTime();

		String retorno = new SimpleDateFormat("HH:mm:ss",
				new Locale("pt", "BR")).format(new Date(time));

		return retorno;
	}

	
	public static String milisegundosParaHoraMinuto(long tempo) {
		int s, m, h;
		String tempoPronto = "";

		s = (int) tempo / 1000;
		m = s / 60;
		h = m / 60;
		m -= h * 60;

		tempoPronto += (h < 10) ? "0" + h + "h" : h + "h";
		tempoPronto += (m < 10) ? " 0" + m + "min" : " " + m + "min";


		return tempoPronto;
	} 
	
	/**
	 * Recebe o telefone xxxxxxxx e retorna o telefone no formato xxxx-xxxx
	 *
	 *@since 21/10/2009
	 *@author Rï¿½mulo Aurï¿½lio
	 */
	public static String formatarTelefone(String telefone) {

		String telefoneFormatado = "";

		if (telefone != null && !telefone.equals("")) {

			telefoneFormatado = telefone.substring(0, 4) + "-"
					+ telefone.substring(4);
		}

		return telefoneFormatado;

	}
	
	/**
	 * Recebe um objeto date e retorna uma String AAAAMMDDHHMMSS
	 * 
	 *  AAAA -  Ano;
	 *	MM   -  Mï¿½s;
	 *	DD   -  Dia;
	 *	HH   -  Hora;
	 *	MM   -  Minuto;
	 *	SS   -  Segundo;
	 *
	 */
	public static String obterAAAAMMDDHHMMSS(Date data) {
		StringBuffer dataBD = new StringBuffer();

		if (data != null) {
			Calendar dataCalendar = new GregorianCalendar();

			dataCalendar.setTime(data);
			
			dataBD.append(dataCalendar.get(Calendar.YEAR));
			if (dataCalendar.get(Calendar.MONTH) >= 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH)+1);
			}else {
				dataBD.append("0" + (dataCalendar.get(Calendar.MONTH)+1));
			}	
			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH));
			}else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH));
			}
			if (dataCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
				dataBD.append(dataCalendar.get(Calendar.HOUR_OF_DAY));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.HOUR_OF_DAY));
			}

			if (dataCalendar.get(Calendar.MINUTE) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MINUTE));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.MINUTE));
			}
			if (dataCalendar.get(Calendar.SECOND) > 9) {
				dataBD.append(dataCalendar.get(Calendar.SECOND));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.SECOND));
			}
		}

		return dataBD.toString();
	}


	/**
	 * Esse mï¿½todo retorna a diferenï¿½a em meses entre dois campos mesAno.<br/>
	 * Recebe os parametros mesAno no formato MM/yyyy.<br/>
	 * ex: 10/2000 - 11/2000 = 1 mï¿½s de diferenï¿½a<br/>
	 *@since 04/11/2009
	 *@author Marlon Patrick
	 */
	public static int getDiferencaMeses(String mesAnoInicial, String mesAnoFinal){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dtInicial = null;
		Date dtFinal = null;
		try {
			dtInicial = sdf.parse("01/" + mesAnoInicial);
			dtFinal = sdf.parse("01/" + mesAnoFinal);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dtInicial);

		int diferenca = 0;

		while(dtFinal.after(dtInicial)){
			gc.add(GregorianCalendar.MONTH, 1);
			dtInicial = gc.getTime();
			diferenca++;
		}

		return diferenca;
	}
	
	/**
	 * 
	 * Converte uma String para qualquer encode passado
	 * 
	 * @param content String a ser convertida
	 * @param encode cï¿½digo do encode para conversï¿½o
	 * @return retorna a string convertida 
	 */
	public static String reencodeString (String content, String encode) {  
		Charset charset = Charset.forName(encode);  
		ByteBuffer bb = charset.encode(content);  
		
		byte[] bytearr = new byte[bb.remaining()];
		bb.get(bytearr);
		String s = new String(bytearr);
		
		return s;
	}
	


	/**
	 * Formata um bigDecimal para String tirando os pontos.
	 * 
	 * @author Hugo Leonardo
	 * @date 22/01/2010
	 * 
	 * @param valor
	 * @return
	 */
	public static String formatarBigDecimalParaStringComVirgula(BigDecimal valor) {

		String valorItemAnterior = "" + valor;
		valorItemAnterior = valorItemAnterior.replace(".", ",");
		return valorItemAnterior;
	}
	
	/**
	 * O metï¿½do completa uma string com espaï¿½os em branco (ex: passa a string
	 * "12.36" e o tamanho mï¿½ximo 10 e retorna "12.36    " ) apenas se a string nï¿½o exceder o tamanho mï¿½ximo
	 * 
	 * 
	 * @author Hugo Amorim	
	 * @date 25/01/2010
	 * 
	 * @param str
	 *            String que vai ser complementada com espaï¿½os em branco a
	 *            esquerda
	 * @param tm
	 *            Tamanho mï¿½ximo da string
	 * @return
	 */
	public static String completaStringComEspacoADireitaCondicaoTamanhoMaximo(String str,
			int tamanhoMaximo) {

		// Tamanho da string informada
		int tamanhoString = 0;
		if (str != null) {
			tamanhoString = str.length();
		} else {
			tamanhoString = 0;
		}

		// Calcula a quantidade de espaï¿½os embranco necessï¿½rios
		int quantidadeEspacos = tamanhoMaximo - tamanhoString;

		if (quantidadeEspacos < 0) {
			return str;
			
		}

		// Cria um array de caracteres de espaï¿½os em branco
		char[] tempCharEspacos = new char[quantidadeEspacos];
		Arrays.fill(tempCharEspacos, ' ');

		// Cria uma string temporaria com os espaï¿½os em branco
		String temp = new String(tempCharEspacos);

		// Cria uma strinBuilder para armazenar a string
		StringBuilder stringBuilder = new StringBuilder(str);

		
		stringBuilder.append(temp);
		

		// Retorna a string informada com espaï¿½os em branco a esquerda
		// totalizando o tamanho mï¿½ximo informado
		return stringBuilder.toString();
	}
	
	/**
	 * Converte uma string no formato AAMMDD para um objeto do tipo Date 
	 *
	 * @author Raphael Rossiter
	 * @date 29/01/2010
	 *
	 * @param data
	 * @return Date
	 */
	public static Date converteStringInvertidaSemBarraAAMMDDParaDate(String data) {
		Date retorno = null;
		
		String dataInvertida = data.substring(4, 6) + "/"
		+ data.substring(2, 4) + "/20" + data.substring(0, 2);
		
		SimpleDateFormat dataTxt = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			retorno = dataTxt.parse(dataInvertida);
		} catch (ParseException e) {
			throw new IllegalArgumentException(data
					+ " nï¿½o tem o formato dd/MM/yyyy.");
		}
		return retorno;
	}
	/**
	 * 
	 * Gerar senha forte
	 *
	 * @author Hugo Amorim	
	 * @date 04/02/2010
	 * 
	 * @param int tamanho senha
	 *
	 * @return String senha forte
	 */
	public static String gerarSenhaForte(int length) {
		
		String senha;
		
		char[] ALL_CHARS = new char[62];
		Random RANDOM = new Random();
		for (int i = 48, j = 0; i < 123; i++) {
			if (Character.isLetterOrDigit(i)) {
				ALL_CHARS[j] = (char) i;
				j++;
			}
		}	

	    char[] result = new char[length];
		for (int i = 0; i < length; i++) {	
			result[i] = ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)];
		}
		
		senha = new String(result);
		
		// Testa se senha tem seguencias.
		int count = 0;
		String letraAnterior = "";
		for (int i = 0; i < senha.toCharArray().length; i++) {
			
			String letra = String.valueOf(senha.toCharArray()[i]);

			if (letra.equals(letraAnterior)) {
				letraAnterior = letra;
				count++;
			}else {
				letraAnterior = letra;
				count = 0;
			}
		}
		
		if (count >= 2){
			senha = Util.gerarSenhaForte(length);
		}
		
		// Testa se senha possui letras minisculas, maiusculas e numeros
		Pattern pattern1 = Pattern.compile(".*[0-9].*");
		Pattern pattern2 = Pattern.compile(".*[a-z].*");
		Pattern pattern3 = Pattern.compile(".*[A-Z].*");

		// Executa testes de todas as expressï¿½es
		Matcher matcher1;
		Matcher matcher2;
		Matcher matcher3;
		
		matcher1 = pattern1.matcher(senha);
		matcher2 = pattern2.matcher(senha);
		matcher3 = pattern3.matcher(senha);

		// Testa se falhou em algum teste.
		if (!matcher1.matches()||!matcher2.matches()||!matcher3.matches()) {
			senha = Util.gerarSenhaForte(length);
		}
		
		return senha;
	}
	
	/**
	 * O metï¿½do completa uma string com espaï¿½os em branco (ex: passa a string
	 * "12.36" e o tamanho mï¿½ximo 10 e retorna "12.36    " ) apenas se a string nï¿½o exceder o tamanho mï¿½ximo
	 * 
	 * 
	 * 	String que vai ser complementada com espaï¿½os em branco a
	 *  esquerda, logo em seguida sera truncada de acordo com o tamanho maximo
	 * 
	 * @author Hugo Amorim	
	 * @date 09/03/2010
	 * 
	 * @param str String
	 * @param tm Tamanho mï¿½ximo da string
	 * @return
	 */
	public static String completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(String str,
			int tamanhoMaximo) {

		// Tamanho da string informada
		int tamanhoString = 0;
		if (str != null) {
			tamanhoString = str.length();
		} else {
			tamanhoString = 0;
		}

		// Calcula a quantidade de espaï¿½os embranco necessï¿½rios
		int quantidadeEspacos = tamanhoMaximo - tamanhoString;

		if (quantidadeEspacos < 0) {
			
			if (tamanhoMaximo < str.length()) {
				// trunca a String
				String strTruncado = str.substring(0, tamanhoMaximo);
				return strTruncado;
			}
			
		}

		// Cria um array de caracteres de espaï¿½os em branco
		char[] tempCharEspacos = new char[quantidadeEspacos];
		Arrays.fill(tempCharEspacos, ' ');

		// Cria uma string temporaria com os espaï¿½os em branco
		String temp = new String(tempCharEspacos);

		// Cria uma strinBuilder para armazenar a string
		StringBuilder stringBuilder = new StringBuilder(str);

		
		stringBuilder.append(temp);
		
		String retorno = stringBuilder.toString();
		
		
		
		// Retorna a string informada com espaï¿½os em branco a esquerda
		// totalizando o tamanho mï¿½ximo informado
		return retorno;
	}
	
	/**
	 * 
	 * [UC0229] Obter Representaï¿½ï¿½o Numï¿½rica do Cï¿½digo de Barras
	 * 
	 * Obtï¿½m o dï¿½gito verificador da divisï¿½o do cï¿½digo de barra com 11 posiï¿½ï¿½es,
	 * de acordo com o modulo passado como parametro.
	 * 
	 * 
	 * ConstantesSistema.MODULO_VERIFICADOR_10
	 * 
	 * ConstantesSistema.MODULO_VERIFICADOR_11
	 * 
	 * @author Hugo Amorim
	 * @date 10/03/2010
	 * 
	 * @param codigoBarraCom43Posicoes
	 * @param moduloVerificador 
	 * @return
	 */
	public static Integer obterDigitoVerificador(
			Long codigoBarraCom43Posicoes, Short moduloVerificador) {
		
		Integer digitoVerificadorGeral = null;
		
		if(moduloVerificador.compareTo(ConstantesSistema.MODULO_VERIFICADOR_11)==0){
			
			digitoVerificadorGeral = obterDigitoVerificadorModulo11(codigoBarraCom43Posicoes);
			
		}else{
			
			digitoVerificadorGeral = obterDigitoVerificadorModulo10(codigoBarraCom43Posicoes);
			
		}

		// Retorna o dï¿½gito verificador calculado
		return digitoVerificadorGeral;
	}
	
	/**
	 * Retorna matricula sem o digito verificador.
	 * 
	 * @author Hugo Amorim
	 * @date 31/03/2010
	 */
	public static String obterMatriculaSemDigitoVerificador(String matriculaComDigito){
		
		String matriculaSemDigito = "";
		
		if(matriculaComDigito.length()>0){
		
			int tamanhoMatricula = matriculaComDigito.length();
		
			matriculaSemDigito = matriculaComDigito.substring(0, tamanhoMatricula-1);
		
		}
		
		return matriculaSemDigito;

	}
	
	/**
	 * Recebe uma string no formato ddmmaaaa e validar o dia, mï¿½s e ano, incluindo validaï¿½ï¿½o para
	 * fevereiro em anos bissextos ou nï¿½o.
	 * 
	 * @author Anderson Italo
	 * @date 08/04/2010
	 * @return true se a data for valida ou false se for invï¿½lida
	 */
	public static boolean validarDiaMesAnoSemBarraNovo(String diaMesAnoReferencia) {
        boolean diaMesAnoValido = true;

        if (diaMesAnoReferencia.length() == 8) {

            String dia = diaMesAnoReferencia.substring(0, 2);
            String mes = diaMesAnoReferencia.substring(2, 4);
            String ano = diaMesAnoReferencia.substring(4, 8);

            try {
                int mesInt = Integer.parseInt(mes);
                int anoInt = Integer.parseInt(ano);
                int diaInt = Integer.parseInt(dia);

                if (mesInt < 1 || mesInt > 12) {
                    diaMesAnoValido = false;
                }
                if (diaInt < 1 || diaInt > 31) {
                	diaMesAnoValido = false;
                }
                
                //se fevereiro
                if (mesInt == 2){
                	
                	boolean bissexto = false;
                	
                	//verifica se ano ï¿½ bissexto
                	if (anoInt % 400 == 0){
                		//Sï¿½o bissextos todos os anos mï¿½ltiplos de 400, p.ex: 1600, 2000, 2400, 2800
                		bissexto = true;
                	}else if ( (anoInt % 4 == 0) && (anoInt % 100 != 0)){
                		//Sï¿½o bissextos todos os mï¿½ltiplos de 4 e nï¿½o mï¿½ltiplos de 100, p.ex: 1996, 2004, 2008, 2012, 2016...
                		bissexto = true;
                	}
                	
                	if (bissexto){
                		if (diaInt < 1 || diaInt > 29) {
                        	diaMesAnoValido = false;
                        }
                	}else{
                		if (diaInt < 1 || diaInt > 28) {
                        	diaMesAnoValido = false;
                        }
                	}
                }
                
                if (anoInt == 0){
                	diaMesAnoValido = false;
                }
                
            } catch (NumberFormatException e) {
            	diaMesAnoValido = false;
            }

        } else {
        	diaMesAnoValido = false;
        }

        return diaMesAnoValido;
    }
	
	/**
	 * O metï¿½do completa uma string com espaï¿½os em branco (ex: passa a string
	 * "12.36" e o tamanho mï¿½ximo 10 e retorna " 12.36" )
	 * 
	 * [UC0321] Emitir Fatura de Cliente Responsï¿½vel
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2006
	 * 
	 * @param str
	 *            String que vai ser complementada com espaï¿½os em branco a
	 *            esquerda
	 * @param tm
	 *            Tamanho mï¿½ximo da string
	 * @return
	 */
	public static String completaStringComEspacoAEsquerdaTruncandoAoTamanhoMaximoInformado(String str,
			int tamanhoMaximo) {

		// Tamanho da string informada
		int tamanhoString = 0;
		if (str != null) {
			tamanhoString = str.length();
		} else {
			tamanhoString = 0;
		}

		// Calcula a quantidade de espaï¿½os embranco necessï¿½rios
		int quantidadeEspacos = tamanhoMaximo - tamanhoString;

		if (quantidadeEspacos < 0) {
			quantidadeEspacos = tamanhoMaximo;
		}

		// Cria um array de caracteres de espaï¿½os em branco
		char[] tempCharEspacos = new char[quantidadeEspacos];
		Arrays.fill(tempCharEspacos, ' ');

		// Cria uma string temporaria com os espaï¿½os em branco
		String temp = new String(tempCharEspacos);

		// Cria uma strinBuilder para armazenar a string
		StringBuilder stringBuilder = null;

		// Caso o tamanhoda string informada seja maior que o tamanho mï¿½ximo da
		// string
		// trunca a string informada
		if (tamanhoString > tamanhoMaximo) {
			String strTruncado = str.substring(0, tamanhoMaximo);
			stringBuilder = new StringBuilder();
			stringBuilder.append(strTruncado);
		} else {
			stringBuilder = new StringBuilder(temp);
			stringBuilder.append(str);
		}

		// Retorna a string informada com espaï¿½os em branco a esquerda
		// totalizando o tamanho mï¿½ximo informado
		return stringBuilder.toString();
		
	}
	/**
	 * Metodo responsavel por retornar o percentual da memoria que esta sendo usada na JVM.
	 * Verifica a memoria heap responsavel por alo
	 *
	 * @author Arthur Carvalho
	 * @date 03/06/2010
	 * @return string
	 */
	public static String retornaPercentualUsadoDeMemoriaJVM() {
		
		MemoryMXBean memorymxbean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heap_usage = memorymxbean.getHeapMemoryUsage();
		NumberFormat format = NumberFormat.getInstance();		
		
		//Retorna o percentual da memoria usada
		String percentualMemoriaUsada =  format.format((heap_usage.getUsed() * 100) / heap_usage.getMax());
				
		return percentualMemoriaUsada;
		
	}
	
	/**
	 * Converte uma string no formato AAMMDD para um objeto do tipo Date 
	 *
	 * @author Raphael Rossiter
	 * @date 29/01/2010
	 *
	 * @param data
	 * @return Date
	 */
	public static Date converteStringInvertidaSemBarraAAAAMMDDParaDate(String data) {
		Date retorno = null;
		
		String dataInvertida = data.substring(6, 8) + "/"
		+ data.substring(4, 6) + "/" + data.substring(0, 4);
		
		SimpleDateFormat dataTxt = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			retorno = dataTxt.parse(dataInvertida);
		} catch (ParseException e) {
			throw new IllegalArgumentException(data
					+ " nï¿½o tem o formato dd/MM/yyyy.");
		}
		return retorno;
	}
	
    /**
     * Formatar a Inscriï¿½ï¿½o Estadual da Caema
     * 
     * @author Hugo leonardo
     * @date 11/06/2010
     * 
	 * Retorna o valor de inscricaoEstadualFormatado
	 * 
	 * @return O valor de inscricaoEstadualFormatado
	 */
	public static String formatarInscricaoEstadualCaema(String inscricaoEstadualCaema) {
		String inscricaoEstadua = inscricaoEstadualCaema;
		String zeros = "";
		
		if (inscricaoEstadua != null) {
			
			for (int a = 0; a < (9 - inscricaoEstadua.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			inscricaoEstadua = zeros.concat(inscricaoEstadua);
			
			inscricaoEstadua = inscricaoEstadua.substring(0, 2) + "."
					+ inscricaoEstadua.substring(2, 5) + "."
					+ inscricaoEstadua.substring(5, 8) + "-"
					+ inscricaoEstadua.substring(8, 9);
		}
		
		return inscricaoEstadua;
	}
	
	
	/**
	 * Verifica se a string passada corresponde a uma data vï¿½lida de acordo com o formato que estï¿½ sendo passado. 
	 *
	 * @author Raphael Rossiter
	 * @date 03/09/2010
	 *
	 * @param data
	 * @param formato
	 * @return boolean
	 */
	public static boolean validarDataHoraInvalida(String dataHora, String formato) {
		
		boolean dataHoraInvalida = false;
		
		try {
			
			if (dataHora != null && !dataHora.equals("") && dataHora.length() >= 10){
				
				int anoInt = Integer.parseInt(dataHora.substring(0, 4));
				int mesInt = Integer.parseInt(dataHora.substring(5, 7));
				int diaInt = Integer.parseInt(dataHora.substring(8, 10));
				
				if (mesInt > 12) {
					dataHoraInvalida = true;
				}
				if (diaInt > 31) {
					dataHoraInvalida = true;
				}
				
				int ultimoDiaMes = Integer.valueOf(Util.obterUltimoDiaMes(mesInt, anoInt));
				
				if (diaInt > ultimoDiaMes){
					dataHoraInvalida = true;
				}
				
				if (!dataHoraInvalida){
					
					int horaInt = 0;
					int minutoInt = 0;
					int segundoInt = 0;

					if (dataHora.length() > 10 && dataHora.length() == 19){
						
						horaInt = Integer.parseInt(dataHora.substring(11, 13));
						minutoInt = Integer.parseInt(dataHora.substring(14, 16));
						segundoInt = Integer.parseInt(dataHora.substring(17, 19));
						
						if (horaInt > 23) {
							dataHoraInvalida = true;
						}
						if (minutoInt > 59) {
							dataHoraInvalida = true;
						}
						if (segundoInt > 59) {
							dataHoraInvalida = true;
						}
						
						SimpleDateFormat formatacaoData = new SimpleDateFormat(formato, new Locale("pt", "BR"));
						formatacaoData.parse(dataHora);
					}
					else{
						
						dataHoraInvalida = true;
					}
				}
			}
			else{
				
				dataHoraInvalida = true;
			}
			
		} catch (Exception e) {
			dataHoraInvalida = true;
		}
		
		return dataHoraInvalida;		
	}
	
	/**
	 * Verifica se a string passada corresponde a uma data vï¿½lida de acordo com o formato que estï¿½ sendo passado. 
	 *
	 * @author Raphael Rossiter
	 * @date 03/09/2010
	 *
	 * @param data
	 * @param formato
	 * @return boolean
	 */
	public static boolean validarDataInvalida(String data, String formato) {
		
		boolean dataInvalida = false;
		
		try {
			
			if (data != null && !data.equals("") && data.length() == 10){
				
				int anoInt = Integer.parseInt(data.substring(0, 4));
				int mesInt = Integer.parseInt(data.substring(5, 7));
				int diaInt = Integer.parseInt(data.substring(8, 10));
				
				if (mesInt > 12) {
					dataInvalida = true;
				}
				if (diaInt > 31) {
					dataInvalida = true;
				}
				
				int ultimoDiaMes = Integer.valueOf(Util.obterUltimoDiaMes(mesInt, anoInt));
				
				if (diaInt > ultimoDiaMes){
					dataInvalida = true;
				}
				
				SimpleDateFormat formatacaoData = new SimpleDateFormat(formato, new Locale("pt", "BR"));
				formatacaoData.parse(data);
				
			} else{
				
				dataInvalida = true;
			}
			
		} catch (Exception e) {
			dataInvalida = true;
		}
		
		return dataInvalida;		
	}
	
	
	
	/**
	 * Verifica se a string passada corresponde a uma data vï¿½lida de acordo com o formato que estï¿½ sendo passado. 
	 *
	 * @author Cesar Medeiros
	 * @date 03/09/2010
	 *
	 * @param data
	 * @param formato
	 * @return boolean
	 */
	public static boolean validarDataHoraInvalidaDiaMesAno(String dataHora, String formato) {
		
		boolean dataHoraInvalida = false;
		
		try {
			
			if (dataHora != null && !dataHora.equals("") && dataHora.length() >= 10){
				
				int anoInt = Integer.parseInt(dataHora.substring(6, 10));
				int mesInt = Integer.parseInt(dataHora.substring(3, 5));
				int diaInt = Integer.parseInt(dataHora.substring(0, 2));
				
				if (mesInt > 12) {
					dataHoraInvalida = true;
				}
				if (diaInt > 31) {
					dataHoraInvalida = true;
				}
				
				int ultimoDiaMes = Integer.valueOf(Util.obterUltimoDiaMes(mesInt, anoInt));
				
				if (diaInt > ultimoDiaMes){
					dataHoraInvalida = true;
				}
				
				if (!dataHoraInvalida){
					
					int horaInt = 0;
					int minutoInt = 0;
					int segundoInt = 0;

					if (dataHora.length() > 10 && dataHora.length() <= 19){
						
						horaInt = Integer.parseInt(dataHora.substring(11, 13));
						minutoInt = Integer.parseInt(dataHora.substring(14, 16));
						segundoInt = Integer.parseInt(dataHora.substring(17, 19));
						
						if (horaInt > 23) {
							dataHoraInvalida = true;
						}
						if (minutoInt > 59) {
							dataHoraInvalida = true;
						}
						if (segundoInt > 59) {
							dataHoraInvalida = true;
						}
						
						SimpleDateFormat formatacaoData = new SimpleDateFormat(formato, new Locale("pt", "BR"));
						formatacaoData.parse(dataHora);
					}
					else{
						
						dataHoraInvalida = true;
					}
				}
			}
			else{
				
				dataHoraInvalida = true;
			}
			
		} catch (Exception e) {
			dataHoraInvalida = true;
		}
		
		return dataHoraInvalida;		
	}
	
	
	
	/**
	 * Verifica se a string passada corresponde a uma data vï¿½lida de acordo com o formato que estï¿½ sendo passado. 
	 *dd/MM/aaaa
	 *
	 * @author Cesar Medeiros
	 * @date 30/01/2015
	 *
	 * @param data
	 * @param formato
	 * @return boolean
	 */
	public static boolean validarDataInvalidaDiaMesAno(String data, String formato) {
		
		boolean dataInvalida = false;
		
		try {
			
			if (data != null && !data.equals("") && data.length() == 10){
				
				int anoInt = Integer.parseInt(data.substring(6, 10));
				int mesInt = Integer.parseInt(data.substring(3, 5));
				int diaInt = Integer.parseInt(data.substring(0, 2));
				
				if (mesInt > 12) {
					dataInvalida = true;
				}
				if (diaInt > 31) {
					dataInvalida = true;
				}
				
				int ultimoDiaMes = Integer.valueOf(Util.obterUltimoDiaMes(mesInt, anoInt));
				
				if (diaInt > ultimoDiaMes){
					dataInvalida = true;
				}
				
				SimpleDateFormat formatacaoData = new SimpleDateFormat(formato, new Locale("pt", "BR"));
				formatacaoData.parse(data);
				
			} else{
				
				dataInvalida = true;
			}
			
		} catch (Exception e) {
			dataInvalida = true;
		}
		
		return dataInvalida;		
	}
	
	
	
    public static InputStream inflateFile(InputStream is, int tamanhoInput) throws IOException {
    	
    	DataInputStream disArquivoCompactado = new DataInputStream(is);
    	byte[] arrayArquivoCompactado = new byte[ tamanhoInput ];
    	disArquivoCompactado.readFully(arrayArquivoCompactado);
    	arrayArquivoCompactado = GZIP.inflate(arrayArquivoCompactado);
    	
    	ByteArrayInputStream byteArray = new ByteArrayInputStream(arrayArquivoCompactado);
    	
    	disArquivoCompactado.close();
    	disArquivoCompactado = null;
    	arrayArquivoCompactado = null;
    	
    	return byteArray;	
    }
    
    /**
     * Separa um arquivo texto por linhas.
     * 
     * @param arquivo
     *            Dados do arquivo texto.
     * @param maxLinhas
     *            Nï¿½mero mï¿½ximo de linhas. Caso seja zero, retorna todas as
     *            linhas
     * @return Vetor de linhas do arquivo texto.
     */
    public static Vector carregaLinhas(InputStream arquivo, int maxLinhas) throws IOException {
    	Vector vetor = new Vector();
    	vetor.removeAllElements();
    	StringBuffer buffer = new StringBuffer();
    	
    	final byte EOF = -1;
    	final byte ENTER = 13;
    	final byte LINE = 10;
	
    	int i = 0;
    	while (i != EOF && (maxLinhas == 0 || vetor.size() < maxLinhas)) {
    		i = arquivo.read();
    		// System.out.println("Valor char: "+(char)i);
    		// se for enter (0D ou 13)...
    		if (i == ENTER) {
    			// ...pula para o prï¿½ximo caractere
    			i = arquivo.read();
    		}
    		// se for quebra linha (0A ou 10)...
    		if (i == LINE || i == EOF) {
    			// ...salva o registro
    			String line = buffer.toString();

    			if ("".equals(line) || line == null) {
    				continue;
    			}
    			vetor.addElement(line);
    			buffer.delete(0, buffer.length());
    		} else {
    			buffer.append((char) i);
    		}
    	}

    	buffer = null;
    	return vetor;
    } 
    
    /**
     * Formata a sequence de acordo com o banco de dados (nextval)
     * 
     * @param sequence
     *            nome da sequence que serï¿½ formatada
  
     * @return String da sequence.
     */
    
    public static String obterNextValSequence (String sequence){
    	String nextvalSequence = " ";
    	String dialect = HibernateUtil.getDialect();
		
		if (dialect.toUpperCase().contains("ORACLE")){
			nextvalSequence = sequence + ".nextval ";
		} else {
			nextvalSequence = "nextval('"+ sequence + "') ";
		}
    	
    	
    	return nextvalSequence;
    	
    }
    
    
    
    /**
     * Formata a sequence de acordo com o banco de dados (nextval)
     * 
     * @param sequence
     *            nome da sequence que serï¿½ formatada
  
     * @return String da sequence.
     */
    
    public static String obterSQLDataAtual(){
    	String nowOrSysdate = " ";
    	String dialect = HibernateUtil.getDialect();
		
		if (dialect.toUpperCase().contains("ORACLE")){
			nowOrSysdate = "sysdate";
		} else {
			nowOrSysdate = "now()";
		}
    	
    	
    	return nowOrSysdate;
    	
    }
    
    /**
     * 
     * Formata um campo e o retorna ja com |
     * 
     * @param parametro - Qualque object pode ser concatenado
     * 
     * @return 
     */
    public static String formatarCampoParaConcatenacao( Object parametro ){
    	if ( parametro == null ){
    		return "|";
    	} else {
    		return parametro + "|"; 
    	}
    }
    
    /**
     * Formate um MesANo para um tipo Date
     */
    public static Date formatarMesAnoParaData(String mesAno, String dia, String hora){
    	Date retorno = null;

    	String[] mesAnoArray = mesAno.split("/");
		SimpleDateFormat formatoData = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss");

		String dataCompleta = dia+"/"+mesAnoArray[0] +"/"+mesAnoArray[1]+" " +hora;

		try {
			retorno = formatoData.parse(dataCompleta);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return retorno;
    }
    
    /**
	 * 
	 * Verifica se deve descompactar o arquivo, caso afirmativo, 
	 * descompacta e ja retorna o arquivo descompactado
	 * 
	 * @param arq
	 * @return
	 * @throws ActionServletException
	 */
	public static byte[] descompactarArquivo(InputStreamReader reader) throws ActionServletException {
		byte[] arquivo = reader.getEncoding().getBytes();
		
		if(reader.toString().endsWith(".gz") || reader.toString().endsWith(".zip")){
			try{
				arquivo = GZIP.inflate(arquivo);
			}catch(IOException ioe){
				throw new ActionServletException("Erro ao descompactar o arquivo");
			}
		}
		
		return arquivo;
	}
	
	/**
	 * 
	 * Retorna a idade a partir de um Date
	 * 
	 * @param data
	 * @return
	 * @author Paulo Diniz
	 */
	public static int getIdade(Date dataNascimento) {  
		  
	    // Data de hoje.  
	        GregorianCalendar agora = new GregorianCalendar();  
	        int ano = 0,   
	        mes = 0, dia = 0;  
	          
	    // Data do nascimento.  
	        GregorianCalendar nascimento = new GregorianCalendar();  
	        int anoNasc = 0,   
	        mesNasc = 0, diaNasc = 0;  
	  
	        // Idade.  
	        int idade = 0;  
	          
	        if(dataNascimento != null){  
	            nascimento.setTime(dataNascimento);  
	              
	            ano = agora.get(Calendar.YEAR);  
	            mes = agora.get(Calendar.MONTH) + 1;  
	            dia = agora.get(Calendar.DAY_OF_MONTH);  
	              
	            anoNasc = nascimento.get(Calendar.YEAR);  
	            mesNasc = nascimento.get(Calendar.MONTH) + 1;  
	            diaNasc = nascimento.get(Calendar.DAY_OF_MONTH);  
	              
	            idade = ano - anoNasc;  
	              
	            // Calculando diferencas de mes e dia.  
	            if(mes < mesNasc) {  
	                idade--;  
	            } else {  
	                if(dia < diaNasc) {  
	                    idade--;  
	                }  
	            }  
	              
	            // Ultimo teste, idade "negativa".  
	            if(idade < 0) {  
	                idade = 0;  
	            }  
	              
	        }  
	  
	        return idade;       
	      
	}  


	/**
	 * Verifica se duas strings sï¿½o iguais, considerando tambï¿½m quando tiverem valor nulo.
	 * 
	 * @param primeiraString
	 *            <Descriï¿½ï¿½o>
	 * @param segundaString
	 *            <Descriï¿½ï¿½o>
	 * @return retorno
	 */
	public static boolean stringsIguais(String primeiraString, String segundaString) {

		boolean retorno = false;

		if (primeiraString == null && segundaString == null) {
			
			retorno = true;
			
		} else if ((primeiraString != null && segundaString == null)
				|| (primeiraString == null && segundaString != null)) {
			
			retorno = false;
			
		} else if (primeiraString.trim().equals(segundaString.trim())) {
			
			retorno = true;
			
		}

		return retorno;

	}
	
	public static String getNomeMes(String mesReferencia) {
		if(mesReferencia.equals("01")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Janeiro");
		}else if(mesReferencia.equals("02")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Fevereiro");
		}else if(mesReferencia.equals("03")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Marco");
		}else if(mesReferencia.equals("04")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Abril");
		}else if(mesReferencia.equals("05")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Maio");
		}else if(mesReferencia.equals("06")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Junho");
		}else if(mesReferencia.equals("07")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Julho");
		}else if(mesReferencia.equals("08")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Agosto");
		}else if(mesReferencia.equals("09")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Setembro");
		}else if(mesReferencia.equals("10")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Outubro");
		}else if(mesReferencia.equals("11")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Novembro");
		}else if(mesReferencia.equals("12")){
			mesReferencia = mesReferencia.replace(mesReferencia, "Dezembro");
		}
		
		return mesReferencia;
	}

	/**
	 * Mï¿½todo para comparar duas data e retornar o numero de dias da diferenï¿½a
	 * entre elas positivo (sempre retorna um resultado positivo, indepedente das datas iniciais e finais.
	 * 
	 * Author: Tiago Moreno - (12/09/2011)
	 * 
	 * @param dataInicial
	 *            Data Inicial
	 * @param dataFinal
	 *            Data Final
	 * 
	 * @return int Quantidade de Dias
	 */
	public static int obterQuantidadeDiasEntreDuasDatasPositivo(Date dataInicial,
			Date dataFinal) {

		GregorianCalendar startTime = new GregorianCalendar();
		GregorianCalendar endTime = new GregorianCalendar();

		GregorianCalendar curTime = new GregorianCalendar();
		GregorianCalendar baseTime = new GregorianCalendar();

		
		if(dataInicial instanceof Timestamp) {
			dataInicial = new Date(((Date)dataInicial).getTime());	
		}
		
		if(dataFinal instanceof Timestamp) {
			dataFinal = new Date(((Date)dataFinal).getTime());	
		}
		
		startTime.setTime(dataInicial);
		endTime.setTime(dataFinal);

		int multiplicadorDiferenca = 1;

		// Verifica a ordem de inicio das datas
		if (dataInicial.compareTo(dataFinal) < 0) {
			baseTime.setTime(dataFinal);
			curTime.setTime(dataInicial);
			multiplicadorDiferenca = 1;
		} else {
			baseTime.setTime(dataInicial);
			curTime.setTime(dataFinal);
			multiplicadorDiferenca = -1;
		}

		int resultadoAno = 0;
		int resultadoMeses = 0;
		int resultadoDias = 0;

		// Para cada mes e ano, vai de mes em mes pegar o ultimo dia para ir
		// acumulando
		// no total de dias. Ja leva em consideracao ano bissesto
		while (curTime.get(GregorianCalendar.YEAR) < baseTime
				.get(GregorianCalendar.YEAR)
				|| curTime.get(GregorianCalendar.MONTH) < baseTime
						.get(GregorianCalendar.MONTH)) {

			int max_day = curTime
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			resultadoMeses += max_day;
			curTime.add(GregorianCalendar.MONTH, 1);

		}

		// Marca que ï¿½ um saldo negativo ou positivo
		resultadoMeses = resultadoMeses * multiplicadorDiferenca;

		// Retirna a diferenca de dias do total dos meses
		resultadoDias += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime
				.get(GregorianCalendar.DAY_OF_MONTH));
		
		int resultado = resultadoAno + resultadoMeses + resultadoDias;
		
		if (resultado < 0){
			resultado = resultado * (-1);
		}

		return resultado;
	}
    
	/**
	 * Mï¿½todo que recebe uma string e verifica se a string sï¿½ tem numeros com
	 * casas decimais
	 * 
	 * @param data
	 * @autor thiago
	 * @date 18/03/2006
	 * @return
	 */

	public static BigDecimal formatarStringParaBigDecimal(String valor) {
		BigDecimal numeroFormatado = null;
		try {
			numeroFormatado = new BigDecimal(valor);
		} catch (NumberFormatException e) {
			numeroFormatado = null;
		}
		return numeroFormatado;
	}
	
	/**
	 * Formata um campo Numerico para Integer, retira os pontos(.)
	 * 
	 * @author Arthur Carvalho
	 * @date 27/10/2011
	 * 
	 * @param valor
	 * @return
	 */
	public static Integer formatarCampoNumericoEmMilharesParaInteger(String valor) {

		String valorItemAnterior = "" + valor;
		valorItemAnterior = valorItemAnterior.replace(".", "");
		Integer valorInteiro = new Integer(valorItemAnterior);
		
		return valorInteiro;
	}
	
	
	public static int multiplica(int numero, 
			int numeroMultiplicado) {

		BigDecimal n = new BigDecimal(numero);

		BigDecimal d = new BigDecimal(numeroMultiplicado);

		BigDecimal resultado = n.multiply(d);

		return Util.arredondar(resultado);

	}
	
	/**
	 * < <Descriï¿½ï¿½o do mï¿½todo>>
	 * 
	 * @param n
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @param d
	 *            Descriï¿½ï¿½o do parï¿½metro
	 * @return Descriï¿½ï¿½o do retorno
	 */
	public static int dividirArredondarResultadoBaixo(int n, int d) {

		int retorno = 0;

		BigDecimal numerador = new BigDecimal(n);
		BigDecimal denominador = new BigDecimal(d);

		if (denominador.intValue() != 0) {
			BigDecimal resultado = numerador.divide(denominador,
					BigDecimal.ROUND_HALF_EVEN);

			retorno = resultado.intValue();
		}

		return retorno;
	}
	
	public static Date formatarDataInicialPrimeiroDiaDoMes(Date dataInicial) {

		Calendar calendario = GregorianCalendar.getInstance();

		calendario.setTime(dataInicial);
		
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.MINUTE, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMinimum(Calendar.DAY_OF_MONTH));

		return calendario.getTime();
	}

	/**
	 * Monta um data inicial com hora,minuto e segundo zerados para pesquisa no
	 * banco
	 * 
	 * @author: Rafael Pinto
	 * @date: 19/10/2006
	 * 
	 * @param Date
	 * 
	 * @return dataInicial
	 */
	public static Date formatarDataFinalUltimoDiaDoMes(Date dataFinal) {

		Calendar calendario = Calendar.getInstance();

		calendario.setTime(dataFinal);

		calendario.set(Calendar.HOUR_OF_DAY, 23);
		calendario.set(Calendar.MINUTE, 59);
		calendario.set(Calendar.SECOND, 59);
		calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendario.getTime();
	}
	
	
	/**
	 * Mï¿½todo que recebe uma e verifica se a string sï¿½ tem numeros.
	 * 
	 * @param data
	 * @autor Arthur Carvalho
	 * @date 18/11/2011
	 * @return
	 */
	public static String recuperaMesAnoComBarraDaData(Date data) {

		int ano = Util.getAno(data);
		int mes = Util.getMes(data);
		String mesFormatado = null;
		if (mes > 0 && mes < 10) {
			mesFormatado = "0" + mes;
		} else {
			mesFormatado = "" + mes;
		}

		return mesFormatado + "/" + ano ;

	}
	
	
	/**
	* Retorna o valor de uma Tag XML
	* 
	* @author Paulo Diniz
	* @date 27/11/2011
	* 
	* @return
	*/
	public static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		 
        Node nValue = (Node) nlList.item(0);
		 
		return nValue.getNodeValue();
    }
	
	/**
	 * Verifica se uma String ï¿½ um inteiro positivo 
	 * igual ou maior que zero
	 * 
	 * @author Raimundo Martins
	 * @date 25/11/2011
	 * */
	public static boolean verificaSeNumeroNatural(String numero){
		
		boolean retorno = false;
		try{
			Integer num = Integer.parseInt(numero);			
			if(num >=0){
				retorno = true;
			}
			else{
				retorno = false;
			}
		}catch(Exception ex){
			retorno = false;
		}
		return retorno;
	}
	/**
	 * Verifica se uma String ï¿½ uma data
	 * vï¿½lida e estï¿½ no formato correto
	 * 
	 * @author Raimundo Martins
	 * @date 25/11/2011
	 * */
	public static boolean verificaSeDataValida(String data, String formato){
		boolean retorno = true;
		try{
			SimpleDateFormat format = new SimpleDateFormat(formato);
			Calendar cal = new GregorianCalendar();
			cal.setTime(format.parse(data));
			String[] dateStr = data.split("/"); 
			String dia = dateStr[0];  
			String mes = dateStr[1];  
			String ano = dateStr[2];  
			if ((new Integer(dia)).intValue() != (new Integer(cal.get(Calendar.DAY_OF_MONTH))).intValue()) {
				retorno = false;
			}
			else if ((new Integer(mes)).intValue() != (new Integer(cal.get(Calendar.MONTH)+1)).intValue()) {  
				retorno = false;  
		    } else if ((new Integer(ano)).intValue() != (new Integer(cal.get(Calendar.YEAR))).intValue() ) {
		        retorno = false;  
			}			
		}catch(Exception ex){
			retorno = false;
		}
		return retorno;
	}     
	public static byte[] converterFileParaArrayByte(File file) throws IOException {
		
		ByteArrayOutputStream ous = new ByteArrayOutputStream();
		InputStream ios = new FileInputStream(file);
		try {
	        byte[] buffer = new byte[(int)file.length()];
	        int read = 0;
	        while ( (read = ios.read(buffer)) != -1 ) {
	            ous.write(buffer, 0, read);
	        }
	    } finally { 
	        try {
	             if ( ous != null ) 
	                 ous.close();
	        } catch ( IOException e) {

	        }

	        try {
	             if ( ios != null ) 
	                  ios.close();
	        } catch ( IOException e) {
	        }
	    }
		
	    return ous.toByteArray();
	}
	
	/**
	 * Retorna um nï¿½mero aleatï¿½rio a partir do intervalo de valores passado como parï¿½metro.  
	 * 
	 * @author Mariana Victor
	 * @date 15/03/2012
	 * */
	public static Integer obterNumeroInteiroAleatorio(Integer intervaloInicial, Integer intervaloFinal){
		
		Random random = new Random();
		Integer retorno = random.nextInt(
			intervaloFinal + 1) + intervaloInicial; 
		
		return retorno;
	}

	/**
	 * Retorna uma string aleatï¿½ria com a quantidade de caracteres passada como parï¿½metro.  
	 * 
	 * @author Mariana Victor
	 * @date 15/03/2012
	 * */
	public static String obterStringAleatoria(int quantidadeCaracteres) {
		
		return RandomStringUtils.randomAlphabetic(quantidadeCaracteres);
		
	}
	
	/**
     * @author Magno Gouveia
     * @since 15/09/2011
     * @param line
     *            linha do arquivo que deve ser parseada
     * @return um array com os campos do objeto
     */
    public static ArrayList<String> split(String line) {
    	ArrayList<String> lines = new ArrayList<String>();

        char[] chars = line.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '|') {
                sb.append(chars[i]);
            } else {
                lines.add(sb.toString());
                sb = new StringBuilder();
            }
        }

        return lines;
    }
    
    /**
     * Metodo responsavel por formatar a latitude e longitude em ate 12 casas decimais
     * @param valor
     * @return
     */
    public static BigDecimal formatarBigDecimalDozeCasasDecimais(String valor) {
    	
    	BigDecimal numeroFormatado = null;
		try {
			numeroFormatado = new BigDecimal(valor);
			valor = formatarLatitudeLongitude12Casas(numeroFormatado);
			numeroFormatado = new BigDecimal(valor.replace(",", "."));
	
		} catch (NumberFormatException e) {
			numeroFormatado = null;
		}
		
		return numeroFormatado;
	}
    
    /**
     * Metodo responsavel por formatar a latitude e longitude em ate 12 casas decimais
     * @param valor
     * @return
     */
    public static String formatarLatitudeLongitude12Casas(BigDecimal valor) {
		DecimalFormatSymbols REAL = new DecimalFormatSymbols(new Locale("pt",
				"BR"));
	
		if (valor != null && !"".equals(valor)) {
			DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.000000000000",
					REAL);
			return DINHEIRO_REAL.format(valor);
		} else {
			return "";
		}

	}
    
    /**
 	 * Verifica se a string passada corresponde a uma data vÃ¡lida de acordo com o formato que estÃ¡ sendo passado. 
 	 *
 	 * @author Fernanda Almeida
 	 * @date 05/02/2013
 	 *
 	 * @param data
 	 * @param formato
 	 * @return boolean
 	 */
 	public static boolean validarAnoMesInvalido(String data, String formato) {
 		
 		boolean dataInvalida = false;
 		
 		try {
 			
 			if (data != null && !data.equals("") && data.length() == 7){
 				
 				int mesInt = Integer.parseInt(data.substring(0, 2));
 				int anoInt = Integer.parseInt(data.substring(3, 7));
 				
 				if (mesInt > 12 || mesInt<=0) {
 					dataInvalida = true;
 				}
 				if (anoInt <=0) {
 					dataInvalida = true;
 				}
 		
 				SimpleDateFormat formatacaoData = new SimpleDateFormat(formato, new Locale("pt", "BR"));
 				formatacaoData.parse(data);
 				
 			} else{
 				
 				dataInvalida = true;
 			}
 			
 		} catch (Exception e) {
 			dataInvalida = true;
 		}
 		
 		return dataInvalida;		
 	}

 	
 	/**
 	* Verifica se uma String ï¿½ um Long
 	*
 	* @author Carlos Chaves
 	* @date 12/12/2012
 	* */
	public static Boolean isLong(String str) {
		Boolean retorno;
		try {
//			Long l = new Long(str);
			retorno = Boolean.TRUE;
		} catch (NumberFormatException e) {
			retorno = Boolean.FALSE;
		}
		return retorno;
	}


 	
 	public static boolean validarCaracteresEspeciais(String campo){
        boolean valido = true;
        String indesejaveis = "-+=/|.!?@#$%ï¿½&*(){}][^~'ï¿½ï¿½ï¿½";
        indesejaveis += '"';
        for(int j=0; j<campo.length(); j++) {
            for(int i=0; i<indesejaveis.length(); i++) {
                if(campo.charAt(j) == indesejaveis.charAt(i)){
                	valido=false;
                    
                	if(!valido){
                        break;
                    }
                }
            }    
        }
        return valido;
    }
 	

 	public static Collection<Integer> retornarListaSequencial(int i, int quebra){
 		ArrayList<Integer> colecaoInteiros = new ArrayList<Integer>();
 		
 		if(i > quebra){
	 		if(i > 1){
	 			int j = quebra;
	 				while(j <= i){
		        		colecaoInteiros.add(j);
		        		j += quebra;
	 				}
	 				
	 				if(j > i)
	 					colecaoInteiros.add(j);
	 		}
 		}
 		else{
 			colecaoInteiros.add(quebra);
 		}
 		return colecaoInteiros;
 	}
 	
 	public static File resizeImg(File in, Integer alturaDesejada, Integer larguraDesejada) throws IOException{
		
		File file = null;
		
		try {
			
			BufferedImage src = ImageIO.read(in);
			
	        double altura = src.getHeight();
	        double largura = src.getWidth();
	        //int alturaDesejada = 768; //a altura proporcional será 1024
	        
	        if (altura > alturaDesejada || largura > larguraDesejada){
	        	
	        	Double novaLargura = null;
	        	Double novaAltura = null;
	        	
	        	// verifica se a largura é maior que a altura
	        	if ( largura > altura ){
	        		
	        		novaLargura = larguraDesejada.doubleValue();
	        		novaAltura = (novaLargura / largura) * altura;
	        		
	        		if (novaAltura > alturaDesejada){
	        			novaAltura = alturaDesejada.doubleValue();
	        		}
	        	}
	        	// se a altura for maior que a largura
	        	else if ( altura > largura ){
	        		
	        		novaAltura = alturaDesejada.doubleValue();
	        		novaLargura = (novaAltura / altura) * largura;
	        		
	        		if (novaLargura > larguraDesejada){
	        			novaLargura = larguraDesejada.doubleValue();
	        		}
	        	}
	        	else{
	        		
	        		
	        		if (alturaDesejada > larguraDesejada){
	        			
	        			novaAltura = alturaDesejada.doubleValue();
	        			novaLargura = alturaDesejada.doubleValue(); 
	        		}
	        		else{
	        			
	        			novaAltura = larguraDesejada.doubleValue();
	        			novaLargura = larguraDesejada.doubleValue();
	        		}
	        	}
	        	
	        	ResampleOp resampleOp = new ResampleOp(novaLargura.intValue() , novaAltura.intValue());

		        BufferedImage rescaled = resampleOp.filter(src, null);

		        //grava imagem redimensionada
		        file = new File("imagem_resize.jpg");
		        ImageIO.write(rescaled, "JPG", file);
	        }
	        else{
	        	
	        	file = in;
	        }
		}
		catch (IOException e) {
			
			if (file != null){
				file.delete();
			}
			
			new IllegalArgumentException("Problema no upload da imagem");
        }
		
		return file;
	}
 	
 	public static File resizeImg(File in,String nomeArquivoRedimensionado, Integer alturaDesejada, Integer larguraDesejada) throws IOException{
		
		File file = null;
		
		try {
			
			BufferedImage src = ImageIO.read(in);
			
	        double altura = src.getHeight();
	        double largura = src.getWidth();
	        //int alturaDesejada = 768; //a altura proporcional será 1024
	        
	        if (altura > alturaDesejada || largura > larguraDesejada){
	        	
	        	Double novaLargura = null;
	        	Double novaAltura = null;
	        	
	        	// verifica se a largura é maior que a altura
	        	if ( largura > altura ){
	        		
	        		novaLargura = larguraDesejada.doubleValue();
	        		novaAltura = (novaLargura / largura) * altura;
	        		
	        		if (novaAltura > alturaDesejada){
	        			novaAltura = alturaDesejada.doubleValue();
	        		}
	        	}
	        	// se a altura for maior que a largura
	        	else if ( altura > largura ){
	        		
	        		novaAltura = alturaDesejada.doubleValue();
	        		novaLargura = (novaAltura / altura) * largura;
	        		
	        		if (novaLargura > larguraDesejada){
	        			novaLargura = larguraDesejada.doubleValue();
	        		}
	        	}
	        	else{
	        		
	        		
	        		if (alturaDesejada > larguraDesejada){
	        			
	        			novaAltura = alturaDesejada.doubleValue();
	        			novaLargura = alturaDesejada.doubleValue(); 
	        		}
	        		else{
	        			
	        			novaAltura = larguraDesejada.doubleValue();
	        			novaLargura = larguraDesejada.doubleValue();
	        		}
	        	}
	        	
	        	ResampleOp resampleOp = new ResampleOp(novaLargura.intValue() , novaAltura.intValue());

		        BufferedImage rescaled = resampleOp.filter(src, null);

		        //grava imagem redimensionada
		        file = new File(nomeArquivoRedimensionado);
		        ImageIO.write(rescaled, "JPG", file);
	        }
	        else{
	        	
	        	file = in;
	        }
		}
		catch (IOException e) {
			
			if (file != null){
				file.delete();
			}
			
			new IllegalArgumentException("Problema no upload da imagem");
        }
		
		return file;
	}
 	
 	/**
 	 * @author Jonathan Marcos
 	 * @since 25/08/2014
 	 * @param emailRemetente
 	 * @param emailReceptor
 	 * @param tituloMensagem
 	 * @param mensagem
 	 * @return boolean
 	 */
 	/*
 	 * Esse método envia email
 	 */
 	public static boolean enviarEmail(String emailRemetente, String emailReceptor,
			String tituloMensagem,String mensagem){
 		boolean emailEnviado = true;
 		try {
			ServicosEmail.enviarMensagem(emailRemetente, emailReceptor,
					tituloMensagem, mensagem);
		} catch (ErroEmailException e) {
			emailEnviado = false;
		}
 		return emailEnviado;
 	}
 	
 	/**
	 * @author Jonathan
	 * @since 25/08/2014
	 * @param dataRecebimentoSMSTWW
	 * @return Date
	 */
	/*
	 * Esse método faz o parse da data
	 * de recebimento e envio da TWW 
	 * para o formato yyyy-MM-dd 
	 */
	public static Date parseDateTWWParaDate(String dataRecebimentoSMSTWW){
		String[] arraydataRecebimentoSMSTWWFormatada = dataRecebimentoSMSTWW.split("T"); 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dataRetorno = null;
		try {
			dataRetorno = simpleDateFormat.parse(arraydataRecebimentoSMSTWWFormatada[0]);
		} catch (Exception e) {
			dataRetorno = null;
		} 
		return dataRetorno;
	}
	
	
	/**
	 * Metodo responsavel por validar os campos retornados do tablet
	 * Atualizacao Cadastral
	 * @author Arthur Carvalho
	 * 
	 * @param campo
	 * @return
	 */
	public static boolean validarCamposAtualizacaoCadastral(String campo) {
		boolean retorno = true;
		
		if ( campo == null ) {
			retorno = false;
		} else if ( campo.equals("999999999") ) {
			retorno = false;
		} else if ( campo.equals("0") ) {
			retorno = false;
		} else if ( campo.trim().equals("") ) {
			retorno = false;
		}
		
		return retorno;
	}
	
	public static boolean validarCamposAtualizacaoCadastralAceitaZero(String campo) {
		boolean retorno = true;
		
		if ( campo == null ) {
			retorno = false;
		} else if ( campo.equals("999999999") ) {
			retorno = false;
		} else if ( campo.trim().equals("") ) {
			retorno = false;
		}
		
		return retorno;
	}
	

	public static boolean ehIgual(String obj1, String obj2)throws ControladorException {
		boolean ehIgual = false;
		if((obj1 == null && obj2 == null)
		||(obj1 != null && obj2 != null && obj1.equals(obj2))){
			ehIgual = true;
		}
		return ehIgual;
	}

	public static boolean ehIgual(Integer obj1, Integer obj2)throws ControladorException {
		boolean ehIgual = false;
		if((obj1 == null && obj2 == null)
		||(obj1 != null && obj2 != null && obj1.equals(obj2))){
			ehIgual = true;
		}
		return ehIgual;
	}
	
	public static boolean ehIgual(Date obj1, Date obj2)throws ControladorException {
		boolean ehIgual = false;
		if((obj1 == null && obj2 == null)
		||(obj1 != null && obj2 != null && obj1.equals(obj2))){
			ehIgual = true;
		}
		return ehIgual;
	}
	
	public static boolean ehIgual(Short obj1, Short obj2)throws ControladorException {
		boolean ehIgual = false;
		if((obj1 == null && obj2 == null)
		||(obj1 != null && obj2 != null && obj1.equals(obj2))){
			ehIgual = true;
		}
		return ehIgual;
	}
	
	public static BigDecimal divideDepoisMultiplicaComCasasDecimais(int numerador, int denominador,
			int numeroMultiplicado) {

		BigDecimal n = new BigDecimal(numerador);

		BigDecimal d = new BigDecimal(denominador);

		BigDecimal resultado = n.divide(d, 4, BigDecimal.ROUND_HALF_UP);

		resultado = resultado.multiply(new BigDecimal(numeroMultiplicado));

		return resultado;
	}
	
	/**
	 * Método responsável por<br>
	 * remover o zeros a esquerda
	 * @author Jonathan Marcos
	 * @since 18/12/2014
	 * @param informacao
	 * @return String
	 */
	public static String removerZerosAEsquerda(String informacao){
    	char[] parte = informacao.toCharArray();
    	int posicao = 0;
    	for(posicao = 0;posicao<informacao.length();posicao++){
    		if(parte[posicao]!='0'){
    			break;
    		}
    	}
    	
    	if(posicao!=informacao.length()){
    		informacao = informacao.substring(posicao, informacao.length());
    	}
    	return informacao;
    }
	
	/**
	 * Verifica se uma String é uma data válida e está no formato correto
	 * 
	 * @author César
	 * @date 30/01/2015
	 * */
	public static boolean validarDataValida(String data, String formato){
		boolean retorno = true;
		try{
			SimpleDateFormat format = new SimpleDateFormat(formato);
			format.setLenient(false);
			Calendar cal = new GregorianCalendar();
			cal.setTime(format.parse(data));
			
		}catch(Exception ex){
			retorno = false;
		}
		return retorno;
	}
	
	public static String formatarArrayInQuery(String[] dados) {
		String retorno = "";
		
		if (dados != null && dados.length > 0) { 
			for (int i = 0; i < dados.length; i++) {
				if (!dados[i].equals("") && !dados[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
					retorno += dados[i] + ",";
				}
			}
			
			retorno = retorno.substring(0, retorno.length() - 1);
		}
		
		return retorno;
	}

   /**
    * Subtrai uma quantidade em meses da data informada
    * 
	* @author Bruno Sá Barreto
    * @date 05/11/2014
    * 
    * @throws ControladorException
    * @params Date - data para subtrair os meses, int - quantidade de meses a ser subtraídos
    **/
	public static Date subtrairNumeroMesesDeUmaData(Date data, int numeroMeses) {
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(data);
		c.add(Calendar.MONTH, -1 * numeroMeses);
		data = c.getTime();
		return data;
	}

    /**
     * Metodo responsavel por validar o Numero do Hidrometro
     *
     * @author André Miranda
     * @date 29/04/2015
     *
     * @param numeroHidrometro
     * @return true de Erro
     * @throws ControladorException
     */
    public static boolean validarNumeroHidrometro(String numeroHidrometro) {
    	try {
    		if(numeroHidrometro == null || numeroHidrometro.trim().isEmpty()) {
    			return false;
    		}

			if (numeroHidrometro.length() < 5 || numeroHidrometro.length() > 11) {
				return false;
			}

			// Verifica se o primeiro caracter(capacidade) é uma letra
			char capacidade = numeroHidrometro.charAt(0);
			if(!Character.isLetter(capacidade)) {
				return false;
			}

			// Verifica se o quarto caracter(marca) é uma letra
			char marca = numeroHidrometro.charAt(3);
			if(!Character.isLetter(marca)) {
				return false;
			}

			numeroHidrometro = numeroHidrometro.replace(String.valueOf(capacidade), "");
			numeroHidrometro = numeroHidrometro.replace(String.valueOf(marca), "");

			return verificaSeNumeroNatural(numeroHidrometro);
		} catch (Exception e) {
			return false;
		}
    }
    
    
    
    /**
     * Metodo responsavel por codificar o numero de cartão de crédito
     *
     * @author André Miranda
     * @date 18/05/2015
     */
    public static String codificarNumeroCartaoRede(String numeroCartao) {
    	if(numeroCartao == null) return null;

    	String numeros = numeroCartao.replaceAll("\\D", "");
    	if(numeros.length() <= 10) return numeroCartao;

    	char[] caracteres = numeros.toCharArray();
    	for (int i = 6; i < caracteres.length - 4; i++) {
    		caracteres[i] = '*';
    	}

    	return new String(caracteres);
    }
    
    /**
     * 
     * Método responsável por<br>
     * verificar se uma String<br>
     * pode ser convertida pra Integer
     * 
     * @author Jonathan Marcos
     * @since 04/06/2015
     * 
     * @param numero
     * @return
     */
    public static boolean isInteger(String numero){
    	try {
			Integer.parseInt(numero);
		} catch (Exception e) {
			return false;
		}
    	return true;
    }
    
    /**
     * Retorna o código de barras formatado, já com as sepações de "-'
     * 
     * @author Bruno Barros
     * @since 08/11/2015
     * @param conta Conta que iremos criar o código de barrsas
     * @return String com o código de barras formatado
     */
    public static String obterCodigoBarrasFormatado( Conta conta ){
    	
		String codigoBarras = obterCodigoBarras( conta );

		// Formata a representação númerica do código de
		// barras
		String representacaoNumericaCodBarraFormatada = codigoBarras
				.substring(0, 11)
				+ "-"
				+ codigoBarras.substring(11, 12)
				+ " "
				+ codigoBarras.substring(12, 23)
				+ "-"
				+ codigoBarras.substring(23, 24)
				+ " "
				+ codigoBarras.substring(24, 35)
				+ "-"
				+ codigoBarras.substring(35, 36)
				+ " "
				+ codigoBarras.substring(36, 47)
				+ "-" + codigoBarras.substring(47, 48);
			
		return representacaoNumericaCodBarraFormatada;
    }
    
    /**
     * Retorna o código de barras PURO, sem a formatação dos "-"
     * 
     * @author Bruno Barros
     * @since 08/11/2015
     * @param conta Conta que iremos criar o código de barrsas
     * @return String com o código de barras formatado
     */
    public static String obterCodigoBarras( Conta conta ){
    	
		String codigoBarras = "";

		String anoMesFormatado = "";
		String anoMesRecebido = ""
				+ conta.getReferencia();
		if (anoMesRecebido.length() < 6) {
			anoMesFormatado = anoMesRecebido;
		} else {
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "" + ano;
		}

		Fachada fachada = Fachada.getInstancia();

		codigoBarras = fachada
				.obterRepresentacaoNumericaCodigoBarra(
						new Integer(3),
						new BigDecimal(conta
								.getValorTotalConta()),
						new Integer(
								conta.getLocalidade().getId()),
						new Integer(
								conta.getImovel().getId()),
						anoMesFormatado,
						new Integer(
								new Short(
										conta.getDigitoVerificadorConta())
										.toString()), null,
						null, null, null, null, null,null);

		return codigoBarras;
    }
    
    /**
     * Retorna o código de barras PURO, sem a formatação dos "-"
     * 
     * @author Bruno Barros
     * @since 08/11/2015
     * @param conta Conta que iremos criar o código de barrsas
     * @return String com o código de barras formatado
     */
    public static String obterCodigoBarras( CobrancaDocumento cd ){
    	
		String representacaoNumericaCodBarra = Fachada.getInstancia()
				.obterRepresentacaoNumericaCodigoBarra(
						new Integer(5),
						cd.getValorDocumento(),
						cd.getLocalidade().getId(),
						cd.getImovel().getId(),
						null,
						null,
						null,
						null,
						cd.getNumeroSequenciaDocumento()+"",
						new Integer( DocumentoTipo.EXTRATO_DE_DEBITO ),
						null, null,null);
	
	
		return representacaoNumericaCodBarra;
    }
    
    /**
     * Retorna o código de barras formatado, já com as sepações de "-'
     * 
     * @author Bruno Barros
     * @since 08/11/2015
     * @param documentoCobraca Documento de cobraca que iremos criar o código de barrsas
     * @return String com o código de barras formatado
     */
    public static String obterCodigoBarrasFormatado( CobrancaDocumento documentoCobranca ){
    	
		String codigoBarras = obterCodigoBarras( documentoCobranca );

		// Formata a representação númerica do código de
		// barras
		String representacaoNumericaCodBarraFormatada = codigoBarras
				.substring(0, 11)
				+ "-"
				+ codigoBarras.substring(11, 12)
				+ " "
				+ codigoBarras.substring(12, 23)
				+ "-"
				+ codigoBarras.substring(23, 24)
				+ " "
				+ codigoBarras.substring(24, 35)
				+ "-"
				+ codigoBarras.substring(35, 36)
				+ " "
				+ codigoBarras.substring(36, 47)
				+ "-" + codigoBarras.substring(47, 48);
			
		return representacaoNumericaCodBarraFormatada;
    }    
}
