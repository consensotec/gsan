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
package gcom.util.validacao;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorUtil;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.Resources;

/**
 * Representa toda a l�gica de valida��o do lado do servidor do framework
 * validator
 * 
 * @author pedro
 */
public class ValidarCampos implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Construtor da classe ValidarCampos
	 */
	public ValidarCampos() {
	}

	/**
	 * Metodo para valida��o de CPF
	 * 
	 * @param bean
	 *            Descri��o do par�metro
	 * @param va
	 *            Descri��o do par�metro
	 * @param field
	 *            Descri��o do par�metro
	 * @param errors
	 *            Descri��o do par�metro
	 * @param request
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public static boolean validateCpf(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {

		// a vari�vel CPF recebe o valor do campo
		String CPF = ValidatorUtil.getValueAsString(bean, field.getProperty());

		if (CPF.trim().length() != 0) {

			// O tamanho do CPF tem que ser 11 (Sem forma��o de "." e "-")
			if (CPF.trim().length() > 0 && CPF.length() == 11) {

				try {
					int d1;
					int d4;
					int xx;
					int nCount;
					int resto;
					int digito1;
					int digito2;
					String Check;
					String Separadores = "/-.";

					d1 = 0;
					d4 = 0;
					xx = 1;
					for (nCount = 0; nCount < CPF.length() - 2; nCount++) {
						String s_aux = CPF.substring(nCount, nCount + 1);

						if (Separadores.indexOf(s_aux) == -1) {
							d1 = d1 + (11 - xx)
									* Integer.valueOf(s_aux).intValue();
							d4 = d4 + (12 - xx)
									* Integer.valueOf(s_aux).intValue();
							xx++;
						}
					}
					resto = d1 % 11;
					if (resto < 2) {
						digito1 = 0;
					} else {
						digito1 = 11 - resto;
					}

					d4 = d4 + 2 * digito1;
					resto = d4 % 11;
					if (resto < 2) {
						digito2 = 0;
					} else {
						digito2 = 11 - resto;
					}

					Check = String.valueOf(digito1) + String.valueOf(digito2);

					String s_aux2 = CPF.substring(CPF.length() - 2, CPF
							.length());

					if (s_aux2.compareTo(Check) != 0) {
						errors.add(field.getKey(), Resources.getActionError(
								request, va, field));
						return false;
					}
					return true;
				} catch (Exception e) {
					return false;
				}

			} else {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}

		}

		return true;
	}

	/**
	 * M�todo para valida��o de CNPJ
	 * 
	 * @param bean
	 *            Descri��o do par�metro
	 * @param va
	 *            Descri��o do par�metro
	 * @param field
	 *            Descri��o do par�metro
	 * @param errors
	 *            Descri��o do par�metro
	 * @param request
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public static boolean validateCnpj(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {

		// atribui a vari�vel CNPJ o valor do campo cnpj do form.
		String CNPJ = ValidatorUtil.getValueAsString(bean, field.getProperty());
		int soma = 0;

		// Se o CNPJ for vazio, a valida��o passar�. Se ele for obrigat�rio o
		// deve-se colocar a regra required junto com o cnpj no validator.xml
		// if (CNPJ.trim().length() != 0) {

		if (CNPJ.trim().length() > 0 && CNPJ.trim().length() == 14) {
			for (int i = 0, j = 5; i < 12; i++) {
				soma += j-- * (CNPJ.charAt(i) - '0');
				if (j < 2) {
					j = 9;
				}
			}
			soma = 11 - (soma % 11);
			if (soma > 9) {
				soma = 0;
			}
			if (soma == (CNPJ.charAt(12) - '0')) {
				soma = 0;
				for (int i = 0, j = 6; i < 13; i++) {
					soma += j-- * (CNPJ.charAt(i) - '0');
					if (j < 2) {
						j = 9;
					}
				}
				soma = 11 - (soma % 11);
				if (soma > 9) {
					soma = 0;
				}
				if (soma == (CNPJ.charAt(13) - '0')) {

					return true;
				}
			}
		} else if (CNPJ.trim().length() > 0 && CNPJ.trim().length() < 14) {
			errors.add(field.getKey(), Resources.getActionError(request, va,
					field));
			return false;
		}

		return true;
	}

	/**
	 * M�todo para valida��o de Caracteres Especiais
	 * 
	 * @param bean
	 *            Descri��o do par�metro
	 * @param va
	 *            Descri��o do par�metro
	 * @param field
	 *            Descri��o do par�metro
	 * @param errors
	 *            Descri��o do par�metro
	 * @param request
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public static boolean validateCaracterEspecial(Object bean,
			ValidatorAction va, Field field, ActionErrors errors,
			HttpServletRequest request) {

		// texto que vai ser validado
		String texto = ValidatorUtil
				.getValueAsString(bean, field.getProperty());

		if (texto != null) {
			// caracteres especiais que n�o podem existir no texto
			String[] indesejaveis = new String[] { "~", "{", "}", "^", "%",
					"$", "[", "]", "@", "|", "`", "\\", "#", "?", "!", "'",
					";", "*", "'", "<", ">", "\"", "(", ")", "&", "+" };
			String delimitador;

			StringBuffer textoBuffer = new StringBuffer(texto);

			for (int i = 0; i < indesejaveis.length; i++) {
				delimitador = indesejaveis[i];

				StringTokenizer stringTokenizer = new StringTokenizer(
						textoBuffer.toString(), delimitador);

				textoBuffer = new StringBuffer();

				while (stringTokenizer.hasMoreElements()) {

					textoBuffer.append(stringTokenizer.nextToken());

				}

			}
			if (textoBuffer.toString().equals(texto)) {
				return true;
			} else {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * 
	 * M�todo para valida��o de Numero do Selo do Hidrometro 
	 *
	 * @author Rafael Santos
	 * @date 28/09/2006
	 *
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param request
	 * @return
	 */
	public static boolean validateNumeroSelo(Object bean,
			ValidatorAction va, Field field, ActionErrors errors,
			HttpServletRequest request) {

		// a vari�vel Inteiro recebe o valor do campo
		String texto = ValidatorUtil.getValueAsString(bean, field
				.getProperty());
		
		// se foi informado a matricula
		if (!texto.trim().equals("")) {
			
			try {
				// Verifica se tem o valor igual a zero
				BigDecimal decimalConvertido = Util.formatarMoedaRealparaBigDecimal(texto);

				// Verifica o range do float
				if (decimalConvertido.floatValue() == 0f) {
					errors.add(field.getKey(), Resources.getActionError(request,
							va, field));
					return false;				
				}
			} catch (Exception e) {
				return true;
			}
		} else {
			return true;
		}
		
		return true;
	}
	
	/**
	 * Metodo para valida��o da Matricula do Cliente na COMPESA
	 * 
	 * @param bean
	 *            Descri��o do par�metro
	 * @param va
	 *            Descri��o do par�metro
	 * @param field
	 *            Descri��o do par�metro
	 * @param errors
	 *            Descri��o do par�metro
	 * @param request
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public static boolean validateMatricula(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel Matricula recebe o valor do campo
		String MATRICULA = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		// se foi informado a matricula
		if (MATRICULA.trim().length() != 0) {

			// O tamanho da Matricula tem que ser 8 (Sem forma��o de ".")
			if (MATRICULA.trim().length() > 0 && MATRICULA.length() == 8) {

				try {
					int valor;
					int soma;
					int nCount;
					int resto;
					int digito;
					int digitoVerificador;
					int[] digitos;

					valor = 7;
					soma = 0;
					digitos = new int[8];
					for (nCount = 0; nCount < MATRICULA.length() - 1; nCount++) {
						String s_aux = MATRICULA.substring(nCount, nCount + 1);

						digitos[nCount] = Integer.valueOf(s_aux).intValue();
					}

					// digito verificador da matricula informada
					digitoVerificador = digitos[8];
					soma = soma + digitos[0] * 2;
					for (int i = 1; i < 7; i++) {
						soma = soma + (digitos[i] * valor);
						valor = valor - 1;

					}
					soma = soma * 10;
					resto = soma % 11;
					if (resto == 10) {
						digito = 0;
					} else {
						digito = resto;
					}

					if (digito != digitoVerificador) {
						errors.add(field.getKey(), Resources.getActionError(
								request, va, field));
						return false;
					}
					return true;
				} catch (Exception e) {
					return false;
				}

			} else {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}

		}

		return true;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bean
	 *            Descri��o do par�metro
	 * @param va
	 *            Descri��o do par�metro
	 * @param field
	 *            Descri��o do par�metro
	 * @param errors
	 *            Descri��o do par�metro
	 * @param request
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public static boolean validateDecimal(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel Decimal recebe o valor do campo
		String decimal = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		if (decimal != null) {
		// se foi informado a matricula
		if (!decimal.trim().equals("")) {
			try {
				// Tenta converter a string em um float, se n�o conseguir,
				// ocorre uma exce��o e retorna false
				//decimal = decimal.replace('.', ',');
				//decimal = decimal.replace(',', '.');
				

				BigDecimal decimalConvertido = Util.formatarMoedaRealparaBigDecimal(decimal);

				// Verifica o range do float
				if (decimalConvertido.floatValue() > 0f
						&& decimalConvertido.floatValue() < 1000000000f) {
					return true;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}
		}
	}
		/*
		 * } else { errors.add(field.getKey(), Resources.getActionError(request,
		 * va, field)); return false; }
		 */
		return true;
	}

	
	/**
	 * Valida se o Campo So tem valor de numero positivo ou zero
	 * 
	 * @author Rafael Santos
	 * @since 03/07/2006
	 * @return Descri��o do retorno
	 */
	public static boolean validateInteiroZeroPositivo(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel Inteiro recebe o valor do campo
		String inteiro = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		// se foi informado a matricula
		if (!inteiro.trim().equals("")) {
			try {
				// Tenta converter a string em um inteiro, se n�o conseguir,
				// ocorre uma exce��o e retorna false
				float inteiroConvertido = Integer.parseInt(inteiro);

				// Verifica o range do float
				if (inteiroConvertido >= 0
						&& inteiroConvertido < 2147483647) {
					return true;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}
		}
		/*
		 * } else { errors.add(field.getKey(), Resources.getActionError(request,
		 * va, field)); return false; }
		 */
		return true;
		
		
	}
	
	/**
	 * Valida se o Campo so tem Numeros Negativos, Zero ou Positivos
	 * 
	 * @author Rafael Santos
	 * @since 05/07/2006
	 * 
	 * @return Descri��o do retorno
	 */
	public static boolean validateInteiroNegativoZeroPositivo(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel Inteiro recebe o valor do campo
		String inteiro = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		// se foi informado a matricula
		if (!inteiro.trim().equals("")) {
			try {
				// Tenta converter a string em um inteiro, se n�o conseguir,
				// ocorre uma exce��o e retorna false
				float inteiroConvertido = Integer.parseInt(inteiro);

				// Verifica o range do float
				if (inteiroConvertido >= -2147483647
						&& inteiroConvertido < 2147483647) {
					return true;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}
		}
		/*
		 * } else { errors.add(field.getKey(), Resources.getActionError(request,
		 * va, field)); return false; }
		 */
		return true;
		
		
	}

	/**
	 * Valida se o Campo e uma Data Tipo Mes/Ano
	 * 
	 * @author Rafael Santos
	 * @since 05/07/2006
	 * 
	 * @return Descri��o do retorno
	 */
	public static boolean validateMesAno(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel mesAno recebe o valor do campo
		String mesAno = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		boolean mesAnoValido = true;

		if (mesAno.length() == 7) {
			
			SimpleDateFormat dataTxt = new SimpleDateFormat("MM/yyyy");

			try {
				dataTxt.parse(mesAno);
			} catch (ParseException e) {
				mesAnoValido = false;
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				
			}

		} else {
			mesAnoValido = false;
			errors.add(field.getKey(), Resources.getActionError(request,
					va, field));
			
		}

		return mesAnoValido;
	}

	/**
	 * Valida se o Campo e uma Data Tipo Hora:Minuto
	 * 
	 * @author Rafael Santos
	 * @since 29/08/2006
	 * 
	 * @return Descri��o do retorno
	 */
	public static boolean validateHoraMinuto(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel horaMinuto recebe o valor do campo
		String horaMinuto = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		boolean horaMinutoValido = true;

		if (horaMinuto.length() == 5) {
			
			SimpleDateFormat dataTxt = new SimpleDateFormat("HH:mm");

			try {
				dataTxt.parse(horaMinuto);
			} catch (ParseException e) {
				horaMinutoValido = false;
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				
			}

		} else {
			horaMinutoValido = false;
			errors.add(field.getKey(), Resources.getActionError(request,
					va, field));
			
		}

		return horaMinutoValido;
	}
	
	/**
	 * Valida se o campo tem valor Zero ou Decimal Positivo
	 * 
	 * @author: Rafael Santos
	 * @since: 06/06/2006
	 * @return Descri��o do retorno
	 */
	public static boolean validateDecimalZeroPositivo(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel Decimal recebe o valor do campo
		String decimal = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		// se foi informado a matricula
		if (!decimal.trim().equals("")) {
			try {
				// Tenta converter a string em um float, se n�o conseguir,
				// ocorre uma exce��o e retorna false
				//decimal = decimal.replace(',', '.');

				BigDecimal decimalConvertido = Util.formatarMoedaRealparaBigDecimal(decimal);
				
				// Verifica o range do float
				if (decimalConvertido.floatValue() >= 0f
						&& decimalConvertido.floatValue() < 1000000000f) {
					return true;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}
		}
		/*
		 * } else { errors.add(field.getKey(), Resources.getActionError(request,
		 * va, field)); return false; }
		 */
		return true;
	}

	
	/**
	 * Valida se o campo tem valor Negativo, Zero ou Decimal Positivo
	 * 
	 * @author: Rafael Santos
	 * @since: 06/06/2006
	 * @return Descri��o do retorno
	 */
	public static boolean validateDecimalNegativoZeroPositivo(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel Decimal recebe o valor do campo
		String decimal = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		// se foi informado a matricula  
		if (!decimal.trim().equals("")) {
			try {
				// Tenta converter a string em um float, se n�o conseguir,
				// ocorre uma exce��o e retorna false
				//decimal = decimal.replace(',', '.');

				BigDecimal decimalConvertido = Util.formatarMoedaRealparaBigDecimal(decimal);

				// Verifica o range do float
				if (decimalConvertido.floatValue() > -1000000000f 
						&& decimalConvertido.floatValue() < 1000000000f) {
					return true;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}
		}
		/*
		 * } else { errors.add(field.getKey(), Resources.getActionError(request,
		 * va, field)); return false; }
		 */
		return true;
	}

	
	
	/**
	 * Valida se o campo tem valor Negativo, Zero ou Decimal Positivo
	 * 
	 * @author: Rafael Santos
	 * @since: 06/06/2006
	 * @return Descri��o do retorno
	 */
	public static boolean validateRadioRequired(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel Radio recebe o valor do campo
		//String radio = ValidatorUtil.getValueAsString(bean, field
		//		.getProperty());

		//System.out.println("radio="+radio);
		// se foi informado a matricula  
		///if (!radio.trim().equals("")) {
	//	}
		/*
		 * } else { errors.add(field.getKey(), Resources.getActionError(request,
		 * va, field)); return false; }
		 */
		return true;
	}
	
	/**
	 * Valida se o campo tem valor Negativo, Zero ou Decimal Positivo
	 * 
	 * @author: Rafael Santos
	 * @since: 06/06/2006
	 * @return Descri��o do retorno
	 */
	public static boolean validateCheckRequired(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel cehck recebe o valor do campo
		//String check = ValidatorUtil.getValueAsString(bean, field
		//		.getProperty());

	//	System.out.println("check="+check);
		
		// se foi informado a matricula  
		//if (!check.trim().equals("")) {

		//}
		/*
		 * } else { errors.add(field.getKey(), Resources.getActionError(request,
		 * va, field)); return false; }
		 */
		return true;
	}

	/**
	 * Valida se o Campo So tem valor de numero positivo para Numero Grandes
	 * 
	 * @author Rafael Santos
	 * @since 18/11/2006
	 * @return Descri��o do retorno
	 */
	public static boolean validateBigInteger(Object bean, ValidatorAction va,
			Field field, ActionErrors errors, HttpServletRequest request) {
		// a vari�vel Inteiro recebe o valor do campo
		String inteiro = ValidatorUtil.getValueAsString(bean, field
				.getProperty());

		// se foi informado a matricula
		if (!inteiro.trim().equals("")) {
			try {
				// Tenta converter a string em um inteiro, se n�o conseguir,
				// ocorre uma exce��o e retorna false
				BigInteger inteiroConvertido = new BigInteger(inteiro);

				// Verifica o range do float
				if ((inteiroConvertido.compareTo(new BigInteger("0")) > 0) 
					&& (inteiroConvertido.compareTo(new BigInteger("99999999999999999999")) <= 0)) {
					return true;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionError(request,
						va, field));
				return false;
			}
		}
		/*
		 * } else { errors.add(field.getKey(), Resources.getActionError(request,
		 * va, field)); return false; }
		 */
		return true;
		
		
	}
	
	
}

