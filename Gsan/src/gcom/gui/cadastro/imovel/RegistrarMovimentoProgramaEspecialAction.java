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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 */
public class RegistrarMovimentoProgramaEspecialAction extends GcomAction {

	public static final String QUEBRA_LINHA = System.getProperty("line.separator");  
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	Fachada fachada = Fachada.getInstancia();
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        String confirmado = httpServletRequest.getParameter("confirmado");
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        RegistrarMovimentoProgramaEspecialActionForm form = (RegistrarMovimentoProgramaEspecialActionForm) actionForm;
        String acao = form.getAcao();
        String anoMesReferencia = form.getMesAnoFaturamento();
        String cancelarItensFatura = form.getCancelarItensFaturaHidden();
        String observacao = form.getObservacao().trim();
        String retirarContasProgEspecial = form.getRetirarContasProgEspecialHidden();
        String sitEspecialCobranca = form.getRetirarSituacaoEspCobrancaHidden();
        
        //[FS0005] - Verificar Preenchimento dos Campos
        //===============================================================================
        if(acao == null || acao.equals("")){
        	throw new ActionServletException("atencao.gsan.informe_a_campo",null,"A��o.");
        }
        
        if(observacao == null || observacao.equals("")){
        	throw new ActionServletException("atencao.gsan.informe_a_campo",null,"Observa��o.");
        }
        
        if(
    		(
        		(cancelarItensFatura != null && cancelarItensFatura.equals("1")) ||
        		(retirarContasProgEspecial != null && retirarContasProgEspecial.equals("1"))
    		)
    		&& (anoMesReferencia == null || anoMesReferencia.equals(""))
          ){
        		throw new ActionServletException("atencao.gsan.informe_o_campo",null,"M�s/Ano de Faturamento.");
           }
        
        //===============================================================================
        
        
        FormFile arquivoMovimentoForm = form.getArquivoMovimento();
        File arquivoMovimento = new File(arquivoMovimentoForm.getFileName());
        byte[] dadosArquivo = null;
        
        
       
        //[FS0002] - Verificar exist�ncia do arquivo de movimento de arrecadador
        //======================================================================
        //Caso o arquivo de movimento de arrecadador informado n�o exista no diret�rio padr�o,
        if(arquivoMovimentoForm == null || arquivoMovimentoForm.getFileName() == null || arquivoMovimentoForm.getFileName().equals("")){
        	
        	//exibir a mensagem "Arquivo do Movimento Inexistente"
        	throw new ActionServletException("atencao.gsan.informe_o_campo",null,"Arquivo de Movimento.");
        }
        //======================================================================
        
        //[FS0006] - Verificar Arquivo Processado
        //===============================================================================
        //Caso o usu�rio informe ou selecione o mesmo arquivo
        if(fachada.verificarProcessamentoArquivoMovimentoProgramaEspecial(arquivoMovimentoForm.getFileName())){
        	
        	//exibir a mensagem "Arquivo <<nome do arquivo >> j� processado"
        	throw new ActionServletException("atencao.arquivo_ja_processado", null,arquivoMovimentoForm.getFileName());
        }
        	
       	//===============================================================================
        
        //[FS0003]- Validar a��o do arquivo informado
        //========================================================
        InputStream fis;
        BufferedReader br;
        String  linha;
        Collection<Integer> colecaoIdsImovel = new ArrayList<Integer>();

        try {
        	
          dadosArquivo = arquivoMovimentoForm.getFileData(); 
        	
          
	         FileOutputStream fos = new FileOutputStream(arquivoMovimento);
	         fos.write(dadosArquivo);
	         fos.flush();
	         fos.close();
	      
	        
	        	
	    	fis = new FileInputStream(arquivoMovimento);
	    	br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			while ((linha = br.readLine()) != null) {
				
				//Validar caso o arquivo seja apenas uma quebra de linha
				//Ou se cont�m menos caracteres que o m�nimo (indicador de opera��o - Id do Im�vel - Fim de linha)
				if(!linha.equals(QUEBRA_LINHA) && !linha.equals("") && linha.length() >= 3){
					String idMovimentoArquivo = linha.substring(0, 1);
					String idImovel = linha.substring(1,linha.length() - 1);
					String ultimoItem = linha.substring(linha.length() - 1,linha.length());
					
					if(idMovimentoArquivo.equals(acao)){
						
						//Validar dados do arquivo
						if(idImovel.indexOf(" ") == -1 && Util.validarStringNumerica(idImovel) 
								&& ultimoItem.equals(";")){
							
							Integer idImovelInt = new Integer(idImovel);
							
							//[FS0007] - Validar Im�veis do Arquivo
							//=======================================================
							//Caso exista alguma matr�cula n�o cadastrada no sistema
							int countImovel = fachada.verificarExistenciaImovel(idImovelInt);
							
							if(countImovel > 0){
								colecaoIdsImovel.add(idImovelInt);
							}						
							else{
								//exibir a mensagem "No arquivo existem matr�culas n�o cadastradas no sistema"
								throw new ActionServletException("atencao.matriculas_nao_cadastradas_txt");
								
							}
							//=======================================================
							
						}
						else{
							throw new ActionServletException("atencao.arquivo_invalido");
						}
						
					}
					else{
						//Caso a a��o selecionada seja diferente de alguma a��o do arquivo texto selecionado
						throw new ActionServletException("atencao.arq_incompativel_acao_informada");
						
					}
					
				}
				else{
					throw new ActionServletException("atencao.arquivo_invalido");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        //===========================================================
        
        
        //[FS0001] - Verificar exist�ncia de dados no arquivo
        //========================================================
        //Caso o arquivo esteja sem dados, exibir a mensagem "Arquivo <<nome do arquivo >> sem dados"
        if(colecaoIdsImovel.size() == 0){
        	throw new ActionServletException("atencao.arquivo_sem_dados",null,arquivoMovimentoForm.getFileName());
        }
        //========================================================
        
        
        //[FS0004] - Validar refer�ncia
        //===========================================================
        if(anoMesReferencia != null && !anoMesReferencia.equals("")){
        	
        	//Caso o m�s/ano da refer�ncia estejam inv�lidos,
        	//exibir a mensagem "M�s/Ano de Refer�ncia inv�lido"
        	if(!this.validarMesAno(anoMesReferencia)){
        		throw new ActionServletException("atencao.ano_mes_referencia.invalido");
        	}
        	
        	//Caso o m�s/ano da refer�ncia sejam maiores que o m�s/ano corrente, 
        	//exibir a mensagem "M�s/Ano de Refer�ncia maior que o M�s/Ano do Faturamento"
        	if(Util.compararAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(anoMesReferencia), sistemaParametro.getAnoMesFaturamento(), ">")){
        		throw new ActionServletException("atencao.mes_ano_referencia_maior", null, "M�s/Ano do Faturamento.");
        	}
        }
        //===========================================================
        
        
        //3.1. O sistema seleciona todos os im�veis
        //     e dever� apresentar uma tela de confirma��o
        if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
        	httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/registrarMovimentoProgramaEspecialAction.do");
			httpServletRequest.setAttribute("nomeBotao1", "Sim");
			httpServletRequest.setAttribute("nomeBotao2", "N�o");
			
			return montarPaginaConfirmacao("atencao.imoveis_a_serem_atualizados", httpServletRequest, actionMapping,colecaoIdsImovel.size()+"");
        }
        
        Integer idMovimento;
        
        //3.1.1. Caso o usu�rio selecione "SIM":
        //3.1.1.1. E caso a a��o selecionada seja "Inserir"
        if(form.getAcao().equals("I")){
        	
        	//[SB0002] - Inserir Im�veis em Programa Especial
        	idMovimento = fachada.inserirImoveisProgramaEspecial(colecaoIdsImovel,
												   acao,
												   anoMesReferencia,
												   cancelarItensFatura,
												   retirarContasProgEspecial,
												   sitEspecialCobranca,
												   observacao,
												   arquivoMovimentoForm.getFileName(),
												   dadosArquivo,
												   usuario);
        }
        
        //3.1.1.2. Caso contr�rio,
        //         ou seja, a a��o selecionada seja "Suspender"
        else{
        	
        	//[SB0003] - Suspender Im�veis em Programa Especial
        	idMovimento = fachada.suspenderImoveisProgramaEspecial(colecaoIdsImovel,
													 acao,
													 anoMesReferencia,
													 cancelarItensFatura,
													 retirarContasProgEspecial,
													 sitEspecialCobranca,
													 observacao,
													 arquivoMovimentoForm.getFileName(),
													 dadosArquivo,
													 usuario);
        }
        
        sessao.setAttribute("formMovimentoProgramaEspecial", form);
        sessao.setAttribute("idMovimento",idMovimento);
        
        //3.1.1.3. Apresenta a tela de sucesso
        montarPaginaSucesso(httpServletRequest,"Movimento de Programa Especial registrado com sucesso",
				"Registra outro Movimento do Programa Especial",
				"exibirRegistrarMovimentoProgramaEspecialAction.do?menu=sim",
				"gerarRelatorioMovimentoProgramaEspecial.do",
				"Relat�rio do Movimento do Programa Especial");
        

        return retorno;
    }
    
    
    //M�todo auliliar para valida��o do Ano/M�s
    private boolean validarMesAno(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 7) {
			String mes = mesAnoReferencia.substring(0, 2);
			String ano = mesAnoReferencia.substring(3, 7);

			try {
				int mesInt = Integer.parseInt(mes);
				int anoInt = Integer.parseInt(ano);

				if (mesInt > 12 || mesInt < 1 || anoInt < 1) {
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

}
