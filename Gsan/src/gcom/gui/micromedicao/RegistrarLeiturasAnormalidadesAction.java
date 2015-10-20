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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel para dar um onLoad no arquivo e importar os ceps.
 * 
 * @author S�vio Luiz, Raphael Rossiter
 * @created 24/08/2005, 02/09/2009
 */
public class RegistrarLeiturasAnormalidadesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		try {

			String idFaturamentoGrupo = null;

			String movimentoCelular = null;

			DiskFileUpload upload = new DiskFileUpload();

			FaturamentoGrupo faturamentoGrupo = null;

			// UPLOAD DO ARQUIVO
			List items = upload.parseRequest(httpServletRequest);

			Fachada fachada = Fachada.getInstancia();
			
			Collection<MedicaoHistorico> colecaoMedicaoHistorico = new ArrayList();

			FileItem item = null;

			// NOME DO ARQUIVO
			String nomeItem = null;

			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();

				if (item.getFieldName().equals("idFaturamentoGrupo")) {
					idFaturamentoGrupo = item.getString();
				}

				if (item.getFieldName().equals("movimentoCelular")) {
					movimentoCelular = item.getString();
				}
				
				if (idFaturamentoGrupo != null && !idFaturamentoGrupo.equals("")){
					
					//CARREGANDO OS DADOS DO GRUPO DE FATURAMENTO ONDE SER�O REGISTRADAS AS LEITURAS
					FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
					
					filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, idFaturamentoGrupo));
					
					Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo,
					FaturamentoGrupo.class.getName());
					
					if (colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()) {
						
						faturamentoGrupo = (FaturamentoGrupo) 
						Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
					} 
					else {
						
						throw new ActionServletException("atencao.entidade.inexistente",null,"Faturamento Grupo");
					}
				}

				if (movimentoCelular != null) {

					/*
					 * Caso o movimento seja proveniente de celular; ser� necess�rio recuperar os dados
					 * do movimento daquele grupo.
					 */
					if (movimentoCelular.equals("1")) {
						
						//RECUPERANDO OS DADOS DAS MEDI��ES
						colecaoMedicaoHistorico = 
						fachada.recuperarMedicoesHistorico(new Integer(idFaturamentoGrupo));
						
					} 
					else {

						// VERIFICA SE N�O FOI INFORMADO UM DIRET�RIO
						if (!item.isFormField()) {
							
							// COLOCA O NOME DO ARQUIVO PARA MAI�SCULO
							nomeItem = item.getName().toUpperCase();
							
							// VERIFICA SE O ARQUIVO EST� COM A EXTENS�O .TXT
							if (nomeItem.endsWith(".TXT")) {

								colecaoMedicaoHistorico = fachada.processarArquivoTextoParaRegistrarLeiturasAnormalidades(
								new Integer(idFaturamentoGrupo), item);

							} 
							else {
								
								throw new ActionServletException("atencao.tipo_importacao.nao_txt");
							}

						}
					}
				}
			}
			
			
			//VARIFICANDO SE EXISTE ALGUMA MEDI��O DIPON�VEL PARA REGISTRAR 
			if (colecaoMedicaoHistorico != null && !colecaoMedicaoHistorico.isEmpty()) {

				fachada.registrarLeiturasAnormalidades(colecaoMedicaoHistorico,
						faturamentoGrupo.getId(), faturamentoGrupo.getAnoMesReferencia(),
						getUsuarioLogado(httpServletRequest), nomeItem);

			} 
			else if (movimentoCelular != null && movimentoCelular.endsWith("1")) {
				
				throw new ActionServletException(
				"atencao.movimento_roteiro_empresa_dados", null, idFaturamentoGrupo);
			} 
			else {
				
				throw new ActionServletException(
				"atencao.arquivo_sem_dados", null, item.getName());
			}
			

			
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new ActionServletException("erro.sistema", e);
		}
		

		// MONTANDO A P�GINA DE SUCESSO
		montarPaginaSucesso(httpServletRequest,
		"Leituras e Anormalidades Enviado para Processamento",
		"Voltar", "/exibirRegistrarLeiturasAnormalidadesAction.do");
		
		
		return retorno;
	}
}
