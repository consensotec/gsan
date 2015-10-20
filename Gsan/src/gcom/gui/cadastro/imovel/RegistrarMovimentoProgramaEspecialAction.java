/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
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
        	throw new ActionServletException("atencao.gsan.informe_a_campo",null,"Ação.");
        }
        
        if(observacao == null || observacao.equals("")){
        	throw new ActionServletException("atencao.gsan.informe_a_campo",null,"Observação.");
        }
        
        if(
    		(
        		(cancelarItensFatura != null && cancelarItensFatura.equals("1")) ||
        		(retirarContasProgEspecial != null && retirarContasProgEspecial.equals("1"))
    		)
    		&& (anoMesReferencia == null || anoMesReferencia.equals(""))
          ){
        		throw new ActionServletException("atencao.gsan.informe_o_campo",null,"Mês/Ano de Faturamento.");
           }
        
        //===============================================================================
        
        
        FormFile arquivoMovimentoForm = form.getArquivoMovimento();
        File arquivoMovimento = new File(arquivoMovimentoForm.getFileName());
        byte[] dadosArquivo = null;
        
        
       
        //[FS0002] - Verificar existência do arquivo de movimento de arrecadador
        //======================================================================
        //Caso o arquivo de movimento de arrecadador informado não exista no diretório padrão,
        if(arquivoMovimentoForm == null || arquivoMovimentoForm.getFileName() == null || arquivoMovimentoForm.getFileName().equals("")){
        	
        	//exibir a mensagem "Arquivo do Movimento Inexistente"
        	throw new ActionServletException("atencao.gsan.informe_o_campo",null,"Arquivo de Movimento.");
        }
        //======================================================================
        
        //[FS0006] - Verificar Arquivo Processado
        //===============================================================================
        //Caso o usuário informe ou selecione o mesmo arquivo
        if(fachada.verificarProcessamentoArquivoMovimentoProgramaEspecial(arquivoMovimentoForm.getFileName())){
        	
        	//exibir a mensagem "Arquivo <<nome do arquivo >> já processado"
        	throw new ActionServletException("atencao.arquivo_ja_processado", null,arquivoMovimentoForm.getFileName());
        }
        	
       	//===============================================================================
        
        //[FS0003]- Validar ação do arquivo informado
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
				//Ou se contém menos caracteres que o mínimo (indicador de operação - Id do Imóvel - Fim de linha)
				if(!linha.equals(QUEBRA_LINHA) && !linha.equals("") && linha.length() >= 3){
					String idMovimentoArquivo = linha.substring(0, 1);
					String idImovel = linha.substring(1,linha.length() - 1);
					String ultimoItem = linha.substring(linha.length() - 1,linha.length());
					
					if(idMovimentoArquivo.equals(acao)){
						
						//Validar dados do arquivo
						if(idImovel.indexOf(" ") == -1 && Util.validarStringNumerica(idImovel) 
								&& ultimoItem.equals(";")){
							
							Integer idImovelInt = new Integer(idImovel);
							
							//[FS0007] - Validar Imóveis do Arquivo
							//=======================================================
							//Caso exista alguma matrícula não cadastrada no sistema
							int countImovel = fachada.verificarExistenciaImovel(idImovelInt);
							
							if(countImovel > 0){
								colecaoIdsImovel.add(idImovelInt);
							}						
							else{
								//exibir a mensagem "No arquivo existem matrículas não cadastradas no sistema"
								throw new ActionServletException("atencao.matriculas_nao_cadastradas_txt");
								
							}
							//=======================================================
							
						}
						else{
							throw new ActionServletException("atencao.arquivo_invalido");
						}
						
					}
					else{
						//Caso a ação selecionada seja diferente de alguma ação do arquivo texto selecionado
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
        
        
        //[FS0001] - Verificar existência de dados no arquivo
        //========================================================
        //Caso o arquivo esteja sem dados, exibir a mensagem "Arquivo <<nome do arquivo >> sem dados"
        if(colecaoIdsImovel.size() == 0){
        	throw new ActionServletException("atencao.arquivo_sem_dados",null,arquivoMovimentoForm.getFileName());
        }
        //========================================================
        
        
        //[FS0004] - Validar referência
        //===========================================================
        if(anoMesReferencia != null && !anoMesReferencia.equals("")){
        	
        	//Caso o mês/ano da referência estejam inválidos,
        	//exibir a mensagem "Mês/Ano de Referência inválido"
        	if(!this.validarMesAno(anoMesReferencia)){
        		throw new ActionServletException("atencao.ano_mes_referencia.invalido");
        	}
        	
        	//Caso o mês/ano da referência sejam maiores que o mês/ano corrente, 
        	//exibir a mensagem "Mês/Ano de Referência maior que o Mês/Ano do Faturamento"
        	if(Util.compararAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(anoMesReferencia), sistemaParametro.getAnoMesFaturamento(), ">")){
        		throw new ActionServletException("atencao.mes_ano_referencia_maior", null, "Mês/Ano do Faturamento.");
        	}
        }
        //===========================================================
        
        
        //3.1. O sistema seleciona todos os imóveis
        //     e deverá apresentar uma tela de confirmação
        if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
        	httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/registrarMovimentoProgramaEspecialAction.do");
			httpServletRequest.setAttribute("nomeBotao1", "Sim");
			httpServletRequest.setAttribute("nomeBotao2", "Não");
			
			return montarPaginaConfirmacao("atencao.imoveis_a_serem_atualizados", httpServletRequest, actionMapping,colecaoIdsImovel.size()+"");
        }
        
        Integer idMovimento;
        
        //3.1.1. Caso o usuário selecione "SIM":
        //3.1.1.1. E caso a ação selecionada seja "Inserir"
        if(form.getAcao().equals("I")){
        	
        	//[SB0002] - Inserir Imóveis em Programa Especial
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
        
        //3.1.1.2. Caso contrário,
        //         ou seja, a ação selecionada seja "Suspender"
        else{
        	
        	//[SB0003] - Suspender Imóveis em Programa Especial
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
				"Relatório do Movimento do Programa Especial");
        

        return retorno;
    }
    
    
    //Método auliliar para validação do Ano/Mês
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
