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
package gcom.util.email;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.fachada.Fachada;
import gcom.util.ConstantesSistema;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Essa classe tem o papel de fornecer ao sistema servi�os de e-mail
 * 
 * @author Rodrigo Silveira
 */
public final class ServicosEmail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static String servidorSMTP;
	public static final String EMAIL_ADMINISTRADOR;
	
	static {
		DadosEnvioEmailHelper dadosEnvioEmailHelper = Fachada.getInstancia().pesquisarDadosEmailSistemaParametros();
		servidorSMTP = dadosEnvioEmailHelper.getIpServidorSmtp();
		EMAIL_ADMINISTRADOR = dadosEnvioEmailHelper.getEmailResponsavel();
	}
	
	/*
	private static String servidorSMTP = ConstantesAplicacao
			.get("servidor.email.smtp");

	public final static String EMAIL_ADMINISTRADOR = ConstantesAplicacao
			.get("endereco.email.administrador");
	*/
	
	private static String TEXTO_EMAIL = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
			+ "<html>"
			+ "<head>"
			+ "<title>Untitled Document</title>"
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">"
			+ "</head>"
			+ "<body>"
			+ "<div align=\"center\"><font color=\"#FF0000\" face=\"Arial, Helvetica, sans-serif\">Relat&oacute;rio "
			+ "gerado apenas para consulta. As funcionalidades dos bot&otilde;es/links est&atilde;o "
			+ "indispon&iacute;veis.<br>"
			+ "Efetue o login no GMNEWEB para ter acesso &agrave;s funcionalidades por completo. </font></div>"
			+ "</body>" + "</html>";
	
	private static String TEXTO_EMAIL_SEM_MSG = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
		+ "<html>"
		+ "<head>"
		+ "<title>Untitled Document</title>"
		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">"
		+ "</head>"
		+ "<body>"
		+ "</body>" + "</html>";

	/**
	 * Construtor da classe ServicosEmail
	 */
	private ServicosEmail() {
	}

	/**
	 * M�todo que envia mensagens pelo protocolo SMTP
	 * 
	 * @param remetente
	 *            Descri��o do par�metro
	 * @param destinatario
	 *            Descri��o do par�metro
	 * @param nomeDestinatario
	 *            Descri��o do par�metro
	 * @param assunto
	 *            Descri��o do par�metro
	 * @param body
	 *            Descri��o do par�metro
	 * @exception ErroEmailException
	 *                Descri��o da exce��o
	 */
	public static void enviarMensagem(String remetente, String destinatario,
			String assunto, String body)
			throws ErroEmailException {

		try {
			
			if(servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO) ){
				
				Fachada fachada = Fachada.getInstancia();
				
				//seta o nome do abreviado da empresa no nome do remetente
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				String nomeDestinatario = sistemaParametro.getNomeAbreviadoEmpresa();
				Properties props = System.getProperties();

				props.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(props, null);

				// -- Create a new message --
				Message msg = new MimeMessage(session);

				// -- Set the FROM and TO fields --
				msg.setFrom(new InternetAddress(remetente, nomeDestinatario));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
						destinatario, false));

				// -- We could include CC recipients too --
				// if (cc != null)
				// msg.setRecipients(Message.RecipientType.CC
				// ,InternetAddress.parse(cc, false));

				// -- Set the subject and body text --
				msg.setSubject(assunto);
				msg.setText(body);

				// -- Set some other header information --
				msg.setSentDate(new Date());

				// -- Send the message --
				Transport.send(msg);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ErroEmailException("Erro no envio de mensagem");
			
		}
	}

	/**
	 * M�todo que envia mensagens com arquivo anexado pelo protocolo SMTP
	 * 
	 * @param destinatario
	 *            Descri��o do par�metro
	 * @param from
	 *            Descri��o do par�metro
	 * @param nameFrom
	 *            Descri��o do par�metro
	 * @param subject
	 *            Descri��o do par�metro
	 * @param body
	 *            Descri��o do par�metro
	 * @param caminhoArquivo
	 *            Descri��o do par�metro
	 * @exception Exception
	 *                Descri��o da exce��o
	 */
	public static void enviarMensagemArquivoAnexado(String destinatario,
			String from, String nameFrom, String subject, String body,
			String caminhoArquivo) throws Exception {

		try {
			
			if(servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO) ){
				
				Properties props = System.getProperties();

				// -- Attaching to default Session, or we could start a new one --

				props.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(props, null);

				// -- Create a new message --
				Message msg = new MimeMessage(session);

				// -- Set the FROM and TO fields --
				msg.setFrom(new InternetAddress(from, nameFrom));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
						destinatario, false));

				// -- We could include CC recipients too --
				// if (cc != null)
				// msg.setRecipients(Message.RecipientType.CC
				// ,InternetAddress.parse(cc, false));

				// -- Set the subject and body text --
				msg.setSubject(subject);

				// msg.setText(body);

				// Cria uma parte referente a mensagem
				BodyPart messageBodyPart = new MimeBodyPart();

				// Preenche a mensagem

				messageBodyPart.setText(body);

				Multipart multipart = new MimeMultipart();

				multipart.addBodyPart(messageBodyPart);

				// Parte com attachment

				messageBodyPart = new MimeBodyPart();

				DataSource source = new FileDataSource(caminhoArquivo);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(source.getName());
				multipart.addBodyPart(messageBodyPart);

				// Insere as partes na mensagem
				msg.setContent(multipart);

				// -- Set some other header information --
				msg.setSentDate(new Date());

				// -- Send the message --
				Transport.send(msg);

				// System.out.println("Message sent OK.");				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void enviarMensagemArquivoAnexado(String destinatario,
		String from, 
		String subject, 
		String body,
		File arquivo) throws Exception {
		
		if(servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO) ){
			
			Properties props = System.getProperties();
			
			Fachada fachada = Fachada.getInstancia();
			
			//seta o nome do abreviado da empresa no nome do remetente
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			String nameFrom = sistemaParametro.getNomeAbreviadoEmpresa();

			// -- Attaching to default Session, or we could start a new one --

			props.put("mail.smtp.host", servidorSMTP);

			Session session = Session.getDefaultInstance(props, null);

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from, nameFrom));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
					destinatario, false));

			// -- We could include CC recipients too --
			// if (cc != null)
			// msg.setRecipients(Message.RecipientType.CC
			// ,InternetAddress.parse(cc, false));

			// -- Set the subject and body text --
			msg.setSubject(subject);

			// msg.setText(body);

			// Cria uma parte referente a mensagem
			BodyPart messageBodyPart = new MimeBodyPart();

			// Preenche a mensagem

			messageBodyPart.setText(body);

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);

			// Parte com attachment

			messageBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(arquivo);

			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(source.getName());
			multipart.addBodyPart(messageBodyPart);

			// Insere as partes na mensagem
			msg.setContent(multipart);

			// -- Set some other header information --
			msg.setSentDate(new Date());

			// -- Send the message --
			Transport.send(msg);

		}

	}

	/**
	 * M�todo que envia mensagens em html e imagens anexadas ao pr�prio c�digo
	 * para serem mostradas
	 * 
	 * @param destinatarios
	 *            Descri��o do par�metro
	 * @param from
	 *            Descri��o do par�metro
	 * @param nameFrom
	 *            Descri��o do par�metro
	 * @param subject
	 *            Descri��o do par�metro
	 * @param body
	 *            Descri��o do par�metro
	 * @param codigoHtml
	 *            Descri��o do par�metro
	 * @param caminhoArquivo
	 *            Descri��o do par�metro
	 * @param linkGrafico
	 *            Descri��o do par�metro
	 * @exception ErroEmailException
	 *                Descri��o da exce��o
	 */
	public static void enviarMensagemHTMLComImagens(Collection destinatarios,
			String from, String nameFrom, String subject, String body,
			String codigoHtml, String caminhoArquivo, String linkGrafico)
			throws ErroEmailException {

		try {
			
			if(servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO) ){ 
				
				Properties props = System.getProperties();

				// -- Attaching to default Session, or we could start a new one --

				props.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(props, null);

				// -- Create a new message --
				Message msg = new MimeMessage(session);

				// -- Set the FROM and TO fields --
				msg.setFrom(new InternetAddress(from, nameFrom));

				Iterator iterator = destinatarios.iterator();

				Address[] enderecos = new Address[destinatarios.size()];

				int i = 0;

				while (iterator.hasNext()) {

					enderecos[i] = new InternetAddress((String) iterator.next());
					i++;
				}

				msg.setRecipients(Message.RecipientType.TO, enderecos);

				// -- Set the subject and body text --
				msg.setSubject(subject);

				BodyPart messageBodyPart = new MimeBodyPart();

				// Cria uma parte referente a mensagem
				messageBodyPart = new MimeBodyPart();

				// Preenche a mensagem com codigo Html
				// Coloca o link para imagem anexada
				codigoHtml = codigoHtml.replaceAll(linkGrafico, "cid:"
						+ linkGrafico);

				messageBodyPart.setContent(codigoHtml, "text/html");

				MimeMultipart multipart = new MimeMultipart();

				multipart.setSubType("related");

				multipart.addBodyPart(messageBodyPart);

				// Parte com attachment

				messageBodyPart = new MimeBodyPart();

				DataSource source = new FileDataSource(caminhoArquivo);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(source.getName());
				messageBodyPart.setHeader("Content-ID", "<" + linkGrafico + ">");
				multipart.addBodyPart(messageBodyPart);

				MimeMultipart multipart2 = new MimeMultipart();

				// Preenche a mensagem com codigo Html
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(TEXTO_EMAIL, "text/html");

				multipart2.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(multipart2);
				multipart.addBodyPart(messageBodyPart);

				// Insere as partes na mensagem
				msg.setContent(multipart);

				// -- Set some other header information --
				msg.setSentDate(new Date());

				// -- Send the message --
				Transport.send(msg);

				// System.out.println("Message sent OK.");
				
			}
		} catch (Exception ex) {
			throw new ErroEmailException("Erro ao enviar mensagem");
		}
	}

	/**
	 * M�todo que envia mensagens em html
	 * 
	 * @param destinatarios
	 *            Descri��o do par�metro
	 * @param from
	 *            Descri��o do par�metro
	 * @param nameFrom
	 *            Descri��o do par�metro
	 * @param subject
	 *            Descri��o do par�metro
	 * @param body
	 *            Descri��o do par�metro
	 * @param codigoHtml
	 *            Descri��o do par�metro
	 * @exception ErroEmailException
	 *                Descri��o da exce��o
	 */
	public static void enviarMensagemHTML(Collection destinatarios,
			String from, String nameFrom, String subject, String body,
			String codigoHtml) throws ErroEmailException {

		try {
			
			if(servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO) ){
				
				Properties props = System.getProperties();

				// -- Attaching to default Session, or we could start a new one --

				props.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(props, null);

				// -- Create a new message --
				Message msg = new MimeMessage(session);

				// -- Set the FROM and TO fields --
				msg.setFrom(new InternetAddress(from, nameFrom));

				Iterator iterator = destinatarios.iterator();

				Address[] enderecos = new Address[destinatarios.size()];

				int i = 0;

				while (iterator.hasNext()) {

					enderecos[i] = new InternetAddress((String) iterator.next());
					i++;
				}

				msg.setRecipients(Message.RecipientType.TO, enderecos);

				// -- Set the subject and body text --
				msg.setSubject(subject);

				// Cria uma parte referente a mensagem
				BodyPart messageBodyPart = new MimeBodyPart();

				// Preenche a mensagem com codigo Html
				messageBodyPart.setContent(codigoHtml, "text/html");

				Multipart multipart = new MimeMultipart();

				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();

				// Preenche a mensagem com codigo Html
				messageBodyPart.setContent(TEXTO_EMAIL, "text/html");

				multipart.addBodyPart(messageBodyPart);

				// Insere as partes na mensagem
				msg.setContent(multipart);

				// -- Set some other header information --
				msg.setSentDate(new Date());

				// -- Send the message --
				Transport.send(msg);

				// System.out.println("Message sent OK.");
				
			}
		} catch (Exception ex) {
			throw new ErroEmailException("Erro ao Enviar Mensagem");
		}
	}
	
	/**
	 * M�todo que envia mensagens em html
	 * 
	 * @param destinatarios
	 *            Descri��o do par�metro
	 * @param from
	 *            Descri��o do par�metro
	 * @param nameFrom
	 *            Descri��o do par�metro
	 * @param subject
	 *            Descri��o do par�metro
	 * @param body
	 *            Descri��o do par�metro
	 * @param codigoHtml
	 *            Descri��o do par�metro
	 * @exception ErroEmailException
	 *                Descri��o da exce��o
	 */
	public static void enviarMensagemHTMLSemMsgTextoEmail(Collection destinatarios,
			String from, String nameFrom, String subject, String body,
			String codigoHtml) throws ErroEmailException {

		try {
			
			if(servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO) ){
				
				Properties props = System.getProperties();

				// -- Attaching to default Session, or we could start a new one --

				props.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(props, null);

				// -- Create a new message --
				Message msg = new MimeMessage(session);

				// -- Set the FROM and TO fields --
				msg.setFrom(new InternetAddress(from, nameFrom));

				Iterator iterator = destinatarios.iterator();

				Address[] enderecos = new Address[destinatarios.size()];

				int i = 0;

				while (iterator.hasNext()) {

					enderecos[i] = new InternetAddress((String) iterator.next());
					i++;
				}

				msg.setRecipients(Message.RecipientType.TO, enderecos);

				// -- Set the subject and body text --
				msg.setSubject(subject);

				// Cria uma parte referente a mensagem
				BodyPart messageBodyPart = new MimeBodyPart();

				// Preenche a mensagem com codigo Html
				messageBodyPart.setContent(codigoHtml, "text/html");

				Multipart multipart = new MimeMultipart();

				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();

				// Preenche a mensagem com codigo Html
				messageBodyPart.setContent(TEXTO_EMAIL_SEM_MSG, "text/html");

				multipart.addBodyPart(messageBodyPart);

				// Insere as partes na mensagem
				msg.setContent(multipart);

				// -- Set some other header information --
				msg.setSentDate(new Date());

				// -- Send the message --
				Transport.send(msg);

				// System.out.println("Message sent OK.");
				
			}
		} catch (Exception ex) {
			throw new ErroEmailException("Erro ao Enviar Mensagem");
		}
	}

	/**
	 * Prepara a exce��o para ser mostrada como conte�do no e-mail
	 * 
	 * @param exception
	 *            Exce��o
	 * @return String com o stacktrace da exce��o
	 */
	public static String processarExceptionParaEnvio(Exception exception) {
		StringBuffer retorno = new StringBuffer();

		retorno.append("Erro ocorrido no sistema GCOM: \n\n");
		retorno.append(exception.getMessage() + "\n");

		for (int i = 0; i < exception.getStackTrace().length; i++) {
			retorno.append(exception.getStackTrace()[i] + "\n");
		}

		return retorno.toString();
	}

	/**
	 * The main program for the ServicosEmail class
	 * 
	 * @param args
	 *            The command line arguments
	 */
	public static void main(String[] args) {
		// try {
		// ServicosEmail.enviarMensagem("rodrigo.silveira@compesa.com.br",
		// "ras80@terra.com.br", "Rodrigo Silveira", "Teste 1",
		// "Primeiro teste de envio de mensagens");

		System.out.println(ServicosEmail
				.processarExceptionParaEnvio(new Exception()));
		/*
		 * } catch (ErroEmailException ex) { ex.printStackTrace(); }
		 */
	}
}
