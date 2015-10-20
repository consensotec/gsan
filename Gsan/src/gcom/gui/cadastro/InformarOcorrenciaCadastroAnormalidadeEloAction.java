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
package gcom.gui.cadastro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * implementa o m�todo de Informar no caso de uso [UC0491] Informar Ocorr�ncia de Cadastro e/ou Anormalidade de Elo
 * 
 * @author Tiago Moreno
 * @date 20/11/2006
 */
public class InformarOcorrenciaCadastroAnormalidadeEloAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//instanciando a fachada
		Fachada fachada = Fachada.getInstancia();
		
		//setando o retorno para a tela de susseco
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//declarando as variaveis que serao recuperadas do form
		String idImovel = null;
		String idOcorrenciaCadastro = null;
		String idAnormalidadeElo = null;
		String dataOcorrenciaCadastro = null;
		String dataAnormalidadeElo = null;
		byte[] uploadPictureCadastro = null;
		byte[] uploadPictureAnormalidade = null;
		
		//essa rotina recupera os dados da Jsp inicial sem usar ActionForm
			try {
				DiskFileUpload upload = new DiskFileUpload();

				//Parse the Request
				List items = upload.parseRequest(httpServletRequest);

				if (items != null) {
					FileItem item = null;

					//Pega uma Lista de Itens do Form
					Iterator iter = items.iterator();
					while (iter.hasNext()) {

						item = (FileItem) iter.next();
						if (item.getFieldName().equals("idImovel")) {
							idImovel = item.getString();
						}
						if (item.getFieldName().equals("cadastroOcorrencia")) {
							idOcorrenciaCadastro = item.getString();
						}
						if (item.getFieldName().equals("anormalidade")) {
							idAnormalidadeElo = item.getString();
						}
						if (item.getFieldName().equals("dataOcorrenciaCadastro")) {
							dataOcorrenciaCadastro = item.getString();
						}
						if (item.getFieldName().equals("dataAnormalidadeElo")) {
							dataAnormalidadeElo = item.getString();
						}
						if (item.getFieldName().equals("uploadPictureCadastro")) {
							if (item.get().length != 0){
								//condiciona ao arquivo ser do tipo JPG ou GIF
								if (item.getName().toUpperCase().endsWith(".JPG")){
									//condiciona o arquivo ser menor que 300Kb ou 307200 Bytes
									if (item.getSize() < 307200) {
										uploadPictureCadastro = item.get();
									}else {
										throw new ActionServletException("atencao.imagem_tamanho_maximo_300kb");
									}
								
								} else {
									throw new ActionServletException("atencao.arquivo_jpg_gif");
								}
							}
						}
						if (item.getFieldName().equals("uploadPictureAnormalidade")) {
							if (item.get().length != 0){
								//condiciona ao arquivo ser do tipo JPG ou GIF
								if (item.getName().toUpperCase().endsWith(".JPG")){
									//condiciona o arquivo ser menor que 300Kb ou 307200 Bytes
									if (item.getSize() < 307200) {
										uploadPictureAnormalidade = item.get();
									}else {
										throw new ActionServletException("atencao.imagem_tamanho_maximo");
									}
								} else {
									throw new ActionServletException("atencao.arquivo_jpg_gif");
								}
							}
						}
					}
				}
			} catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}
			
			if (idOcorrenciaCadastro != null && !idOcorrenciaCadastro.equalsIgnoreCase("")){
				if (dataOcorrenciaCadastro == null || dataOcorrenciaCadastro.equalsIgnoreCase("")){
					throw new ActionServletException("atencao.data_ocorrencia");
				}
			}
			
			if (idAnormalidadeElo != null && !idAnormalidadeElo.equalsIgnoreCase("")){
				if (dataAnormalidadeElo == null || dataAnormalidadeElo.equalsIgnoreCase("")){
					throw new ActionServletException("atencao.data_anormalidade");
				}
			}
		
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
			
        Collection colecaoIdCadastroOcorrenciaRemover = (Collection) sessao.getAttribute("imovelCadastroOcorrenciaRemover");
        Collection colecaoIdAnormalidadeRemover = (Collection) sessao.getAttribute("imovelAnormalidadeRemover");
		//executando o metodo do ControladorImovel responsavel pelas validacoes e insercao no banco
		fachada.informarOcorrenciaCadastroAnormalidadeElo(
				idImovel,
				idOcorrenciaCadastro,
				idAnormalidadeElo,
				dataOcorrenciaCadastro,
				dataAnormalidadeElo,
				uploadPictureCadastro,
				uploadPictureAnormalidade,
				usuarioLogado,
				colecaoIdCadastroOcorrenciaRemover,
				colecaoIdAnormalidadeRemover);
		
		//montando pagina de sucesso
		montarPaginaSucesso(httpServletRequest, "Ocorr�ncia e/ou Anormalidade do Im�vel inserida com sucesso.",
				"Informar outro Ocorr�ncia/Anormalidade do Im�vel",
				"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?menu=sim");
		
		
		return retorno;
	}
}