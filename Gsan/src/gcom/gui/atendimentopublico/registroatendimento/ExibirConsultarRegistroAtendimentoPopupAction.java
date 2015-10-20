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
package gcom.gui.atendimentopublico.registroatendimento;


import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitanteFone;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.RaMotivoReativacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterRAAssociadoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de exibir consultar RA Popup
 * 
 * @author Rafael Pinto
 *
 * @created 11/08/2006
 */
public class ExibirConsultarRegistroAtendimentoPopupAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoPopup");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConsultarRegistroAtendimentoPopupActionForm consultarRegistroAtendimentoPopupActionForm = 
				(ConsultarRegistroAtendimentoPopupActionForm) actionForm;
		
		ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimentoHelper = 
			fachada.obterDadosRegistroAtendimento(
				new Integer(consultarRegistroAtendimentoPopupActionForm.getNumeroRA()));
		
		if (obterDadosRegistroAtendimentoHelper == null || 
				obterDadosRegistroAtendimentoHelper.getRegistroAtendimento() == null) {
			
			throw new ActionServletException("atencao.naocadastrado",null, "Registro Atendimento");
		}

			
		RegistroAtendimento registroAtendimento = obterDadosRegistroAtendimentoHelper.getRegistroAtendimento();
		
		
		//Dados Gerais do Registro de Atendimento
		consultarRegistroAtendimentoPopupActionForm.setNumeroRA(""+registroAtendimento.getId());
		
		if (registroAtendimento.getManual() != null){
			int tamanhoNumeracao = registroAtendimento.getManual().toString().length();
			String numeracao = registroAtendimento.getManual().toString().substring(0, tamanhoNumeracao - 1);
			consultarRegistroAtendimentoPopupActionForm.setNumeroRAManual(
			Util.formatarNumeracaoRAManual(new Integer(numeracao)));
		} else {
			consultarRegistroAtendimentoPopupActionForm.setNumeroRAManual("");
		}
		
		consultarRegistroAtendimentoPopupActionForm.setCodigoSituacao(""+registroAtendimento.getCodigoSituacao());
		
		//Caso de Uso [UC0420]
		ObterDescricaoSituacaoRAHelper situacaoRA = 
			fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
		
		consultarRegistroAtendimentoPopupActionForm.setSituacaoRA(situacaoRA.getDescricaoSituacao());		
		
		//Caso de Uso [UC0433]		
		ObterRAAssociadoHelper obterRAAssociadoHelper = fachada.obterRAAssociado(registroAtendimento.getId());
		
		if(obterRAAssociadoHelper != null && obterRAAssociadoHelper.getRegistroAtendimentoAssociado() != null){
			consultarRegistroAtendimentoPopupActionForm.setNumeroRaAssociado(""+obterRAAssociadoHelper.getRegistroAtendimentoAssociado().getId());

			ObterDescricaoSituacaoRAHelper situacaoRAssociado = 
				fachada.obterDescricaoSituacaoRA(obterRAAssociadoHelper.getRegistroAtendimentoAssociado().getId());
			
			consultarRegistroAtendimentoPopupActionForm.setSituacaoRaAssociado(situacaoRAssociado.getDescricaoSituacao());
			
			httpServletRequest.setAttribute("existeRaAssociado",true);
		}
		
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
			registroAtendimento.getSolicitacaoTipoEspecificacao();
		
		if(solicitacaoTipoEspecificacao != null){
			
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				consultarRegistroAtendimentoPopupActionForm.setIdTipoSolicitacao(""+solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId());
				consultarRegistroAtendimentoPopupActionForm.setTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());	
			}
			
			if(solicitacaoTipoEspecificacao.getServicoTipo() != null){
				String valorPrevisto = Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getServicoTipo().getValor());
				consultarRegistroAtendimentoPopupActionForm.setValorSugerido(valorPrevisto);
			} else {
				consultarRegistroAtendimentoPopupActionForm.setValorSugerido("");
			}
			
			consultarRegistroAtendimentoPopupActionForm.setIdEspecificacao(""+solicitacaoTipoEspecificacao.getId());
			consultarRegistroAtendimentoPopupActionForm.setEspecificacao(solicitacaoTipoEspecificacao.getDescricao());		
		}

		consultarRegistroAtendimentoPopupActionForm.setTipoAtendimento(""+registroAtendimento.getIndicadorAtendimentoOnline());
		
		Date dataAtendimento = registroAtendimento.getRegistroAtendimento();
		
		consultarRegistroAtendimentoPopupActionForm.setDataAtendimento(Util.formatarData(dataAtendimento));		
		consultarRegistroAtendimentoPopupActionForm.setHoraAtendimento(Util.formatarHoraSemSegundos(dataAtendimento));
		
		consultarRegistroAtendimentoPopupActionForm.setTempoEsperaInicio(Util.formatarHoraSemSegundos(registroAtendimento.getDataInicioEspera()));		
		consultarRegistroAtendimentoPopupActionForm.setTempoEsperaTermino(Util.formatarHoraSemSegundos(registroAtendimento.getDataFimEspera()));
		
		consultarRegistroAtendimentoPopupActionForm.setDataPrevista(Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
		consultarRegistroAtendimentoPopupActionForm.setParecerEncerramento(registroAtendimento.getParecerEncerramento());
		
		if(registroAtendimento.getMeioSolicitacao() != null){
			consultarRegistroAtendimentoPopupActionForm.setIdMeioSolicitacao(""+registroAtendimento.getMeioSolicitacao().getId());
			consultarRegistroAtendimentoPopupActionForm.setMeioSolicitacao(registroAtendimento.getMeioSolicitacao().getDescricao());	
		}
		
		//Caso de Uso [UC0421]
		UnidadeOrganizacional unidadeAtendimento = fachada.obterUnidadeAtendimentoRA(registroAtendimento.getId());
		
		if(unidadeAtendimento != null){
			consultarRegistroAtendimentoPopupActionForm.setIdUnidadeAtendimento(""+unidadeAtendimento.getId());
			consultarRegistroAtendimentoPopupActionForm.setUnidadeAtendimento(unidadeAtendimento.getDescricao());
			
			RegistroAtendimentoUnidade registroAtendimentoUnidade = 
				this.consultarRegistroAtendimentoUnidade(registroAtendimento.getId(),unidadeAtendimento.getId());
			
			Usuario usuario = registroAtendimentoUnidade.getUsuario();
			if(usuario != null){
				
				consultarRegistroAtendimentoPopupActionForm.setIdUsuarioAbrirRA(""+usuario.getId());
				consultarRegistroAtendimentoPopupActionForm.setUsuarioAbrirRA(usuario.getNomeUsuario());
			}
		}

		//Caso de Uso [UC0418]
		UnidadeOrganizacional unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
		
		if(unidadeAtual != null){
			consultarRegistroAtendimentoPopupActionForm.setIdUnidadeAtual(""+unidadeAtual.getId());
			consultarRegistroAtendimentoPopupActionForm.setUnidadeAtual(unidadeAtual.getDescricao());
		}
		
		consultarRegistroAtendimentoPopupActionForm.setObservacao(registroAtendimento.getObservacao());
		
		
		//Dados do Local da Ocorrencia
		Imovel imovel = registroAtendimento.getImovel();
		if(imovel != null){
			
			consultarRegistroAtendimentoPopupActionForm.setMatriculaImovel(""+imovel.getId());
			consultarRegistroAtendimentoPopupActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
			consultarRegistroAtendimentoPopupActionForm.setRota(obterDadosRegistroAtendimentoHelper.getCodigoRota().toString());
			
			if (obterDadosRegistroAtendimentoHelper.getSequencialRota() != null) {
				consultarRegistroAtendimentoPopupActionForm.setSequencialRota(obterDadosRegistroAtendimentoHelper.getSequencialRota().toString());
			}
		}

		//Caso de Uso [UC0422]		
		String enderecoOcorrencia = fachada.obterEnderecoOcorrenciaRA(registroAtendimento.getId());
		
		consultarRegistroAtendimentoPopupActionForm.setEnderecoOcorrencia(enderecoOcorrencia);
		consultarRegistroAtendimentoPopupActionForm.setPontoReferencia(registroAtendimento.getPontoReferencia());
		
		
		//Caso o registro atendimento esteja associado a uma �rea de bairro,
		//obter os dados da �rea do bairro
		BairroArea bairroArea = registroAtendimento.getBairroArea();
		
		if(bairroArea != null){

			consultarRegistroAtendimentoPopupActionForm.setIdMunicipio(""+bairroArea.getBairro().getMunicipio().getId());
			consultarRegistroAtendimentoPopupActionForm.setMunicipio(bairroArea.getBairro().getMunicipio().getNome());
			
			consultarRegistroAtendimentoPopupActionForm.setIdBairro(""+bairroArea.getBairro().getId());
			consultarRegistroAtendimentoPopupActionForm.setBairro(bairroArea.getBairro().getNome());
			
			consultarRegistroAtendimentoPopupActionForm.setIdAreaBairro(""+bairroArea.getId());
			consultarRegistroAtendimentoPopupActionForm.setAreaBairro(bairroArea.getNome());
			
		}
		
		Localidade localidade = registroAtendimento.getLocalidade();
		
		if(localidade != null){
			
			consultarRegistroAtendimentoPopupActionForm.setIdLocalidade(""+localidade.getId());
			consultarRegistroAtendimentoPopupActionForm.setLocalidade(localidade.getDescricao());
		}
		
		SetorComercial setorComercial = registroAtendimento.getSetorComercial();
		
		if(setorComercial != null){
			consultarRegistroAtendimentoPopupActionForm.setIdSetorComercial(""+setorComercial.getCodigo());
			consultarRegistroAtendimentoPopupActionForm.setSetorComercial(setorComercial.getDescricao());			
		}
		
		Quadra quadra = registroAtendimento.getQuadra();
		
		if(quadra != null){
			consultarRegistroAtendimentoPopupActionForm.setIdQuadra(""+quadra.getNumeroQuadra());
		}
		
		DivisaoEsgoto divisaoEsgoto = registroAtendimento.getDivisaoEsgoto();
		
		if(divisaoEsgoto != null){

			consultarRegistroAtendimentoPopupActionForm.setIdDivisaoEsgoto(""+divisaoEsgoto.getId());
			consultarRegistroAtendimentoPopupActionForm.setDivisaoEsgoto(divisaoEsgoto.getDescricao());			
		}
		
		LocalOcorrencia localOcorrencia = registroAtendimento.getLocalOcorrencia();
		
		if(localOcorrencia != null){
			consultarRegistroAtendimentoPopupActionForm.setLocalOcorrencia(localOcorrencia.getDescricao());
		}
		
		PavimentoRua pavimentoRua = registroAtendimento.getPavimentoRua();
		
		if(pavimentoRua != null){
			consultarRegistroAtendimentoPopupActionForm.setPavimentoRua(pavimentoRua.getDescricao());
		}

		PavimentoCalcada pavimentoCalcada = registroAtendimento.getPavimentoCalcada();
		
		if(pavimentoCalcada != null){
			consultarRegistroAtendimentoPopupActionForm.setPavimentoCalcada(pavimentoCalcada.getDescricao());
		}

		consultarRegistroAtendimentoPopupActionForm.setDescricaoLocalOcorrencia(registroAtendimento.getDescricaoLocalOcorrencia());
		
		//Dados do Solicitante
		
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = 
			this.consultarRegistroAtendimentoSolicitante(registroAtendimento.getId());
		
		if(registroAtendimentoSolicitante != null){
			
			Cliente cliente = registroAtendimentoSolicitante.getCliente();
			UnidadeOrganizacional unidadeSolicitante = registroAtendimentoSolicitante.getUnidadeOrganizacional();
			
			//PROTOCOLO DE ATENDIMENTO
			if (registroAtendimentoSolicitante.getNumeroProtocoloAtendimento() != null &&
				!registroAtendimentoSolicitante.getNumeroProtocoloAtendimento().equals("")){
				
				consultarRegistroAtendimentoPopupActionForm.setNumeroProtocolo(
				registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
			}
			
			//Caso o principal solicitante do registro de atendimento seja um cliente
			//obter os dados do cliente
			if(cliente != null){
			
				consultarRegistroAtendimentoPopupActionForm.setIdClienteSolicitante(""+cliente.getId());
				consultarRegistroAtendimentoPopupActionForm.setClienteSolicitante(cliente.getNome());	

			//Caso o principal solicitante do registro de atendimento seja uma unidade
			//obter os dados da unidade
			}else if(unidadeSolicitante != null){

				consultarRegistroAtendimentoPopupActionForm.setIdUnidadeSolicitante(""+unidadeSolicitante.getId());
				consultarRegistroAtendimentoPopupActionForm.setUnidadeSolicitante(unidadeSolicitante.getDescricao());	

			//Caso o principal solicitante do registro de atendimento n�o seja um cliente, nem uma unidade
			//obter os dados do solicitante
			}else{
				consultarRegistroAtendimentoPopupActionForm.setNomeSolicitante(registroAtendimentoSolicitante.getSolicitante());
			}
			
			Funcionario funcionario = registroAtendimentoSolicitante.getFuncionario();
			
			if(funcionario != null){
				consultarRegistroAtendimentoPopupActionForm.setIdFuncionarioResponsavel(""+funcionario.getId());
				consultarRegistroAtendimentoPopupActionForm.setFuncionarioResponsavel(funcionario.getNome());
			}

			//Caso de Uso [UC0423]
			String enderecoSolicitante = fachada.obterEnderecoSolicitanteRA(registroAtendimentoSolicitante.getID());
			
			consultarRegistroAtendimentoPopupActionForm.setEnderecoSolicitante(enderecoSolicitante);
			consultarRegistroAtendimentoPopupActionForm.setPontoReferenciaSolicitante(
				registroAtendimentoSolicitante.getPontoReferencia());

			SolicitanteFone solicitanteFone = consultarSolicitanteFone(registroAtendimentoSolicitante.getID());
			
			if(solicitanteFone != null){
				consultarRegistroAtendimentoPopupActionForm.setFoneDDD(""+solicitanteFone.getDdd());
				consultarRegistroAtendimentoPopupActionForm.setFone(solicitanteFone.getFone());
				consultarRegistroAtendimentoPopupActionForm.setFoneRamal(solicitanteFone.getRamal());
				
			}
			
		}
		
		
		/*
		 * ANEXOS
		 * -----------------------------------------------------------------------------------------------------------
		 */
		//CARREGANDO OS ANEXOS QUE EST�O CADASTRADOS NA BASE
		String visualizar = httpServletRequest.getParameter("visualizar");
		
		FiltroRegistroAtendimentoAnexo filtroRegistroAtendimentoAnexo = new FiltroRegistroAtendimentoAnexo();
			
		filtroRegistroAtendimentoAnexo.adicionarParametro(new ParametroSimples(
		FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID,
		registroAtendimento.getId()));

		Collection colecaoRegistroAtendimentoAnexo = fachada.pesquisar(filtroRegistroAtendimentoAnexo,
		RegistroAtendimentoAnexo.class.getName());
			
		httpServletRequest.setAttribute("colecaoRegistroAtendimentoAnexo", colecaoRegistroAtendimentoAnexo);
		
		//OBTENDO ARQUIVO PARA VISUALIZA��O
		RegistroAtendimentoAnexo registroAtendimentoAnexo = this.obterArquivoParaVisualizacao(visualizar, 
		colecaoRegistroAtendimentoAnexo);
		
		//PREPARANDO VISUALIZA��O DO ARQUIVO
		if (registroAtendimentoAnexo != null){
			
			try {
				
				ServletOutputStream out = null;
				
				String mimeType = ConstantesSistema.CONTENT_TYPE_GENERICO;
				String nomeArquivo = "Anexo";
				
				byte[] arquivoOriginal = registroAtendimentoAnexo.getImagemDocumento();
				
				if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_DOC)){
					mimeType = ConstantesSistema.CONTENT_TYPE_MSWORD;
					nomeArquivo = nomeArquivo+".doc";
				}else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_DOCX)){
					mimeType = ConstantesSistema.CONTENT_TYPE_DOCX;
					nomeArquivo = nomeArquivo+".docx";
				}else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_PDF)){
					mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
					nomeArquivo = nomeArquivo+".pdf";
				}else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_JPG)){
					mimeType = ConstantesSistema.CONTENT_TYPE_JPEG;
					nomeArquivo = nomeArquivo+".jpg";
				}
				else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_XLS)){
					mimeType = ConstantesSistema.CONTENT_TYPE_XLS;
					nomeArquivo = nomeArquivo+".xls";
				}
				else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_XLSX)){
					mimeType = ConstantesSistema.CONTENT_TYPE_XLSX;
					nomeArquivo = nomeArquivo+".xlsx";
				}
				
				if (registroAtendimentoAnexo.getIndicadorCompactado().equals(ConstantesSistema.SIM)){
					
					ByteArrayOutputStream baosArquivo = new ByteArrayOutputStream();
					
					File arquivoCompactado = new File("arquivoCompactado.zip"); //Criamos um nome para o arquivo  
				    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoCompactado)); //Criamos o arquivo  
				    bos.write(registroAtendimentoAnexo.getImagemDocumento()); //Gravamos os bytes l�
				    
					 // Cria o input do arquivo ZIP
			        ZipInputStream zinstream = new ZipInputStream(new FileInputStream(arquivoCompactado));
		 
		            // Pega a proxima entrada do arquivo
		            ZipEntry zentry = zinstream.getNextEntry();
		 
		            byte[] buffer = new byte[1024];
		            
		            // Enquanto existir entradas no ZIP
				    while (zentry != null) {
				        // Pega o nome da entrada
		                String entryName = zentry.getName();
		 
		                // Cria o output do arquivo , Sera extraido onde esta rodando a classe
		                FileOutputStream outstream = new FileOutputStream(entryName);
		                int n;
		 
		                // Escreve no arquivo
		                while ((n = zinstream.read(buffer)) > -1) {
		                    baosArquivo.write(buffer, 0, n);
		                }
		                
		                // Fecha arquivo
		                outstream.close();
		                File arquivoDescompactado = new File(entryName);
		                arquivoDescompactado.delete();
		 
		                // Fecha entrada e tenta pegar a proxima
				        zinstream.closeEntry();
				        zentry = zinstream.getNextEntry();
				    }
				    	 
			        // Fecha o zip como um todo
			        zinstream.close();
			        bos.close();
			        arquivoCompactado.delete();
			        
			        arquivoOriginal = null;
			        arquivoOriginal = baosArquivo.toByteArray();
			        baosArquivo.close();
				}
				
				httpServletResponse.setContentType(mimeType);
				httpServletResponse.setHeader("Content-disposition","attachment; filename=" +nomeArquivo );
				
				out = httpServletResponse.getOutputStream();
				
				out.write(arquivoOriginal);
				out.flush();
				out.close();
			} 
			catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
			}
		}
		/*
		 * FIM DOS ANEXOS
		 * -----------------------------------------------------------------------------------------------------------
		 */
		
		
		//Dados da Ultima Tramita��o

		Tramite tramite = 
			fachada.recuperarTramiteMaisAtualPorRA(registroAtendimento.getId());
		
		if(tramite != null){
			
			UnidadeOrganizacional unidadeOrigem = tramite.getUnidadeOrganizacionalOrigem();
			
			if(unidadeOrigem != null){
				
				consultarRegistroAtendimentoPopupActionForm.setIdUnidadeOrigem(""+unidadeOrigem.getId());
				consultarRegistroAtendimentoPopupActionForm.setUnidadeOrigem(unidadeOrigem.getDescricao());
			}
			
			UnidadeOrganizacional unidadeDestino = tramite.getUnidadeOrganizacionalDestino();
			
			if(unidadeDestino != null){
			
				consultarRegistroAtendimentoPopupActionForm.setIdUnidadeAtualTramitacao(""+unidadeDestino.getId());
				consultarRegistroAtendimentoPopupActionForm.setUnidadeAtualTramitacao(unidadeDestino.getDescricao());

			}
			
			Usuario usuarioRegistro = tramite.getUsuarioRegistro();
			if(usuarioRegistro != null){
				consultarRegistroAtendimentoPopupActionForm.setIdUsuarioRegistro(""+usuarioRegistro.getId());
				consultarRegistroAtendimentoPopupActionForm.setUsuarioRegistro(usuarioRegistro.getNomeUsuario());
			}
			
			Usuario usuarioResponsavel = tramite.getUsuarioResponsavel();
			if(usuarioResponsavel != null){
				consultarRegistroAtendimentoPopupActionForm.setIdUsuarioResponsavel(""+usuarioResponsavel.getId());
				consultarRegistroAtendimentoPopupActionForm.setUsuarioResponsavel(usuarioResponsavel.getNomeUsuario());
			}
			
			Date dataTramite = tramite.getDataTramite();
			
			consultarRegistroAtendimentoPopupActionForm.setDataTramite(Util.formatarData(dataTramite));
			consultarRegistroAtendimentoPopupActionForm.setHoraTramite(Util.formatarHoraSemSegundos(dataTramite));

			consultarRegistroAtendimentoPopupActionForm.setParecerTramite(tramite.getParecerTramite());
			
		}
		
		//Dados da Reitera��o
		
		//Caso o registro atendimento tenha sido reiterado,
		//exibir os dados da reitera��o
//		if(registroAtendimento.getQuantidadeReiteracao() != null){
//			
//			Date dataUltimaReiteracao = registroAtendimento.getUltimaReiteracao();
//
//			consultarRegistroAtendimentoPopupActionForm.setQuantidade(""+registroAtendimento.getQuantidadeReiteracao());
//			consultarRegistroAtendimentoPopupActionForm.setDataUltimaReiteracao(Util.formatarData(dataUltimaReiteracao));
//			consultarRegistroAtendimentoPopupActionForm.setHoraUltimaReiteracao(Util.formatarHoraSemSegundos(dataUltimaReiteracao));
//
//		}
		obterDadosReiteracaoRa(consultarRegistroAtendimentoPopupActionForm.getNumeroRA(), fachada, sessao);
		
		//Dados da Reativa��o
		
		//Caso o registro atendimento tenha sido reativado
		//exibir os dados da reativa��o
		Short codigoAssociado = obterRAAssociadoHelper.getCodigoExistenciaRAAssociado();
		
		RegistroAtendimento registroAtendimentoAssociado = 
			obterRAAssociadoHelper.getRegistroAtendimentoAssociado();

		//Caso de Uso [UC0420]
		ObterDescricaoSituacaoRAHelper situacaoRAAssociado = null;
		if(registroAtendimentoAssociado != null){
			situacaoRAAssociado = fachada.obterDescricaoSituacaoRA(registroAtendimentoAssociado.getId());
		}
			
		if(codigoAssociado == RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL && registroAtendimentoAssociado != null){
			
			consultarRegistroAtendimentoPopupActionForm.setNumeroRaAtual(""+registroAtendimentoAssociado.getId());
			consultarRegistroAtendimentoPopupActionForm.setSituacaoRaAtual(situacaoRAAssociado.getDescricaoSituacao());
			
			RaMotivoReativacao raMotivoReativacao = registroAtendimentoAssociado.getRaMotivoReativacao();
			if(raMotivoReativacao!= null){
				consultarRegistroAtendimentoPopupActionForm.setIdMotivoReativacao(""+raMotivoReativacao.getId());
				consultarRegistroAtendimentoPopupActionForm.setMotivoReativacao(raMotivoReativacao.getDescricao());
			}
			
			Date dataRegistro = registroAtendimentoAssociado.getRegistroAtendimento();
			Date dataPrevista = registroAtendimentoAssociado.getDataPrevistaAtual();
			
			consultarRegistroAtendimentoPopupActionForm.setDataReativacao(Util.formatarData(dataRegistro));
			consultarRegistroAtendimentoPopupActionForm.setHoraReativacao(Util.formatarHoraSemSegundos(dataRegistro));
			
			consultarRegistroAtendimentoPopupActionForm.setDataPrevistaRaAtual(Util.formatarData(dataPrevista));

			String parecerEncerramento = registroAtendimentoAssociado.getParecerEncerramento();
			consultarRegistroAtendimentoPopupActionForm.setParecerEncerramento(parecerEncerramento);
			
			//Caso de Uso [UC0421]			
			UnidadeOrganizacional unidadeReativacao = 
				fachada.obterUnidadeAtendimentoRA(registroAtendimentoAssociado.getId());
			
			if(unidadeReativacao != null){
				consultarRegistroAtendimentoPopupActionForm.setIdUnidadeReativacao(""+unidadeReativacao.getId());
				consultarRegistroAtendimentoPopupActionForm.setUnidadeReativacao(unidadeReativacao.getDescricao());		
			}
			
			//Caso de Uso [UC0418]			
			UnidadeOrganizacional unidadeRAAtual = 
				fachada.obterUnidadeAtualRA(registroAtendimentoAssociado.getId());
			
			if(unidadeRAAtual != null){
				consultarRegistroAtendimentoPopupActionForm.setIdUnidadeRaAtual(""+unidadeRAAtual.getId());
				consultarRegistroAtendimentoPopupActionForm.setUnidadeRaAtual(unidadeRAAtual.getDescricao());
			}
			
			consultarRegistroAtendimentoPopupActionForm.setObservacaoReativacao(registroAtendimentoAssociado.getObservacao());
		}
		
		//Dados do encerramento
		
		//Caso o registro atendimento seja encerrado,
		//exibir os dados do encerramento
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = 
			registroAtendimento.getAtendimentoMotivoEncerramento();
		
		if(atendimentoMotivoEncerramento != null){
			
			consultarRegistroAtendimentoPopupActionForm.setIdMotivoEncerramento(""+atendimentoMotivoEncerramento.getId());	
			consultarRegistroAtendimentoPopupActionForm.setMotivoEncerramento(atendimentoMotivoEncerramento.getDescricao());

			if(codigoAssociado == RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA && registroAtendimentoAssociado != null){
				
				consultarRegistroAtendimentoPopupActionForm.setNumeroRaReferencia(""+registroAtendimentoAssociado.getId());

				//Caso de Uso [UC0420]
				consultarRegistroAtendimentoPopupActionForm.setSituacaoRaReferencia(situacaoRAAssociado.getDescricaoSituacao());
				
			}

			//Caso de Uso [UC0434]
			UnidadeOrganizacional unidadeEncerramento = 
				fachada.obterUnidadeEncerramentoRA(registroAtendimento.getId());
			
			if(unidadeEncerramento != null){
				
				consultarRegistroAtendimentoPopupActionForm.setIdUnidadeEncerramento(""+unidadeEncerramento.getId());
				consultarRegistroAtendimentoPopupActionForm.setUnidadeEncerramento(unidadeEncerramento.getDescricao());		

				RegistroAtendimentoUnidade registroAtendimentoUnidade = 
					this.consultarRegistroAtendimentoUnidadeEncerramento(registroAtendimento.getId(),unidadeEncerramento.getId());
				
				Usuario usuario = registroAtendimentoUnidade.getUsuario();
				if(usuario != null){
					
					consultarRegistroAtendimentoPopupActionForm.setIdUsuarioEncerramento(""+usuario.getId());
					consultarRegistroAtendimentoPopupActionForm.setUsuarioEncerramento(usuario.getNomeUsuario());
				}
			}
			
			Date dataEncerramento = registroAtendimento.getDataEncerramento();
			
			consultarRegistroAtendimentoPopupActionForm.setDataEncerramento(Util.formatarData(dataEncerramento));
			consultarRegistroAtendimentoPopupActionForm.setHoraEncerramento(Util.formatarHoraSemSegundos(dataEncerramento));
			
			consultarRegistroAtendimentoPopupActionForm.setDataPrevistaEncerramento(
				Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
			
			consultarRegistroAtendimentoPopupActionForm.setParecerEncerramento(registroAtendimento.getParecerEncerramento());
			
		}
		
		// Dados das OS associadas
		Collection colecaoDadosOSassociadas;
		
		colecaoDadosOSassociadas = 
			this.getFachada().pesquisarOrdensServicosAssociados(registroAtendimento.getId());
		
		if(colecaoDadosOSassociadas != null && !colecaoDadosOSassociadas.isEmpty()){
			httpServletRequest.setAttribute("colecaoDadosOSassociadas", colecaoDadosOSassociadas);
		}
		
		// Dados das Contas relacionados
		// Mariana Victor em 31/01/2011
		FiltroRegistroAtendimentoConta filtroRegistroAtendimentoConta = new FiltroRegistroAtendimentoConta();
		filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
				FiltroRegistroAtendimentoConta.CONTA);
		filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
			FiltroRegistroAtendimentoConta.CONTA_HISTORICO);
		filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
				FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO);
		filtroRegistroAtendimentoConta.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
		
		Collection colecaoRAContas = fachada.pesquisar(
				filtroRegistroAtendimentoConta, RegistroAtendimentoConta.class.getName());
		
		if (colecaoRAContas != null && !colecaoRAContas.isEmpty()) {
			sessao.setAttribute("colecaoRAContas", colecaoRAContas);
		} else {
			sessao.removeAttribute("colecaoRAContas");
		}
		
		
		//Colocado especialmente para trabalhar com o retorno da pesquisa de OS - Raphael Rossiter em 13/02/2007
		String caminhoTelaPesquisaRetorno = httpServletRequest.getParameter("caminhoTelaPesquisaRetorno");
		
		if (caminhoTelaPesquisaRetorno != null && !caminhoTelaPesquisaRetorno.equals("")){
			
			httpServletRequest.setAttribute("caminhoTelaPesquisaRetorno", caminhoTelaPesquisaRetorno);
		}
		
//		Pagamentos Duplicidade
		FiltroRegistroAtendimentoPagamentoDuplicidade filtroRegistroAtendimentoPagamentoDuplicidade = 
			new FiltroRegistroAtendimentoPagamentoDuplicidade();
		filtroRegistroAtendimentoPagamentoDuplicidade.adicionarParametro(
			new ParametroSimples(
				FiltroRegistroAtendimentoPagamentoDuplicidade.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
		
		Collection<RegistroAtendimentoPagamentoDuplicidade> colecaoRAPagamentoDuplicidade = 
			this.getFachada().pesquisar(filtroRegistroAtendimentoPagamentoDuplicidade, RegistroAtendimentoPagamentoDuplicidade.class.getName());
		
		if (colecaoRAPagamentoDuplicidade != null && !colecaoRAPagamentoDuplicidade.isEmpty()){
			sessao.setAttribute("colecaoRAPagamentoDuplicidade", colecaoRAPagamentoDuplicidade);
		} else {
			sessao.removeAttribute("colecaoRAPagamentoDuplicidade");
		}		
		
		return retorno;
	}

	
	/**
	 * Consulta o registro atendimento solicitante pelo id do registro atendimento
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private RegistroAtendimentoSolicitante consultarRegistroAtendimentoSolicitante(Integer idRegistroAtendimento){

		RegistroAtendimentoSolicitante retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimento = null; 

		FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimento = new FiltroRegistroAtendimentoSolicitante();

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID,idRegistroAtendimento));

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
			FiltroRegistroAtendimentoSolicitante.INDICADOR_SOLICITANTE_PRINCIPAL,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		
		
		colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento,
				RegistroAtendimentoSolicitante.class.getName());

		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {
			retorno = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
			
		} 
		
		return retorno;
	}

	/**
	 * Consulta o solicitante fone pelo id do registro atendimentoSolicitante
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private SolicitanteFone consultarSolicitanteFone(Integer idRegistroAtendimentoSolicitante){

		SolicitanteFone retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoSolicitanteFone = null; 

		FiltroSolicitanteFone filtroSolicitanteFone = new FiltroSolicitanteFone();

		filtroSolicitanteFone.adicionarParametro(
			new ParametroSimples(FiltroSolicitanteFone.REGISTRO_ATENDIMENTO_SOLICITANTE_ID,
					idRegistroAtendimentoSolicitante));
		
		colecaoSolicitanteFone = fachada.pesquisar(filtroSolicitanteFone,
				SolicitanteFone.class.getName());

		if (colecaoSolicitanteFone != null && !colecaoSolicitanteFone.isEmpty()) {
			retorno = (SolicitanteFone) Util.retonarObjetoDeColecao(colecaoSolicitanteFone);
			
		} 
		
		return retorno;
	}

	/**
	 * Consulta o Registro Atendimento Unidade pelo id da RA
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private RegistroAtendimentoUnidade consultarRegistroAtendimentoUnidade(Integer idRA,Integer idUnidade){

		RegistroAtendimentoUnidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentoUnidade = null; 

		FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();

		filtroRegistroAtendimentoUnidade.adicionarParametro(
			new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID,idRA));

		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.UNIDADE_ORGANIZACIONAL_ID,idUnidade));
		
		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.ATENDIMENTO_RELACAO_TIPO,AtendimentoRelacaoTipo.ABRIR_REGISTRAR));

		filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		colecaoRegistroAtendimentoUnidade = 
			fachada.pesquisar(filtroRegistroAtendimentoUnidade,RegistroAtendimentoUnidade.class.getName());

		if (colecaoRegistroAtendimentoUnidade != null && !colecaoRegistroAtendimentoUnidade.isEmpty()) {
			retorno = (RegistroAtendimentoUnidade) Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoUnidade);
			
		} 
		
		return retorno;
	}
	
	
	/**
	 * Removendo um arquivo da cole��o
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private RegistroAtendimentoAnexo obterArquivoParaVisualizacao(String identificacao, 
			Collection colecaoRegistroAtendimentoAnexo){
		
		RegistroAtendimentoAnexo registroAtendimentoAnexo = null;
		
		if (identificacao != null && !identificacao.equals("")){
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					registroAtendimentoAnexo = anexoColecao;
					break;
				}
			}
		}
		
		return registroAtendimentoAnexo;
	}

	
	/**
	 * Consulta o Registro Atendimento Unidade pelo id da RA
	 * 
	 * @author R�mulo Aur�lio
	 * @created 11/12/2009
	 */
	private RegistroAtendimentoUnidade consultarRegistroAtendimentoUnidadeEncerramento(Integer idRA,Integer idUnidade){

		RegistroAtendimentoUnidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentoUnidade = null; 

		FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();

		filtroRegistroAtendimentoUnidade.adicionarParametro(
			new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID,idRA));

		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.UNIDADE_ORGANIZACIONAL_ID,idUnidade));
		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.ATENDIMENTO_RELACAO_TIPO,
						AtendimentoRelacaoTipo.ENCERRAR));
		filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		colecaoRegistroAtendimentoUnidade = 
			fachada.pesquisar(filtroRegistroAtendimentoUnidade,RegistroAtendimentoUnidade.class.getName());

		if (colecaoRegistroAtendimentoUnidade != null && !colecaoRegistroAtendimentoUnidade.isEmpty()) {
			retorno = (RegistroAtendimentoUnidade) Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoUnidade);
			
		} 
		
		return retorno;
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 17/05/2011
	 */	
	private void obterDadosReiteracaoRa(String numeroRA, 
			Fachada fachada,HttpSession sessao) {
		
		sessao.removeAttribute("colecaoDadosReiteracao");
		
		if(numeroRA != null && !numeroRA.equals("")){
			Collection colecaoDadosReiteracao = fachada.pesquisarDadosReiteracaoRA(
					new Integer(numeroRA));

			if(colecaoDadosReiteracao != null && !colecaoDadosReiteracao.isEmpty()){
				sessao.setAttribute("colecaoDadosReiteracao",colecaoDadosReiteracao);
			}
		}
	}
}
