/**
 * 
 */
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
* Anderson Italo Felinto de Lima
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
package gsan.gui.cobranca;


import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cobranca.CobrancaAtividade;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
 * [UC0243] Inserir Comando de A��o de Conbran�a - Tipo de Comando Cronograma
 * 
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class InserirComandoAcaoCobrancaEventualCriterioComandoConcluirAction extends
		GcomAction {
	public static final String QUEBRA_LINHA = System.getProperty("line.separator");

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("telaSucesso");

		// Mudar isso quando implementar a parte de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		InserirComandoAcaoCobrancaEventualCriterioComandoActionForm inserirComandoAcaoCobrancaEventualCriterioComandoActionForm = null;
		                        
		if(sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm") != null){
			inserirComandoAcaoCobrancaEventualCriterioComandoActionForm = (InserirComandoAcaoCobrancaEventualCriterioComandoActionForm)sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm"); 
		}
		
		String idComando = httpServletRequest.getParameter("idComando");
		

		String idLocalidade = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getLocalidadeOrigemID();
		String codigoSetorComercial = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getSetorComercialOrigemCD();

		String idLocalidadeFinal = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getLocalidadeDestinoID();
		String codigoSetorComercialFinal = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getSetorComercialDestinoCD();

		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        if (idLocalidade != null
                && !idLocalidade.toString().trim().equalsIgnoreCase("")) {
            filtroLocalidade.limparListaParametros();
            //coloca parametro no filtro
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, new Integer(idLocalidade)));
            //pesquisa
            Collection localidades = fachada.pesquisar(filtroLocalidade, Localidade.class
                    .getName());
            if (localidades == null || localidades.isEmpty()) {
            	throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
            }
        }

        FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
        if (codigoSetorComercial != null
                && !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
            if (idLocalidade != null
                    && !idLocalidade.toString().trim().equalsIgnoreCase("")) {
                filtroSetorComercial.limparListaParametros();
                //coloca parametro no filtro
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.ID_LOCALIDADE, new Integer(
                                idLocalidade)));
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                        new Integer(codigoSetorComercial)));
                //pesquisa
                Collection setorComerciais = fachada.pesquisar(filtroSetorComercial,
                        SetorComercial.class.getName());
                if (setorComerciais == null || setorComerciais.isEmpty()) {
                	 throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
                }
            }

        } 

        
		filtroLocalidade = new FiltroLocalidade();
        if (idLocalidade != null
                && !idLocalidade.toString().trim().equalsIgnoreCase("")) {
            filtroLocalidade.limparListaParametros();
            //coloca parametro no filtro
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, new Integer(idLocalidadeFinal)));
            //pesquisa
            Collection localidades = fachada.pesquisar(filtroLocalidade, Localidade.class
                    .getName());
            if (localidades == null || localidades.isEmpty()) {
            	throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");
            }
        }

        filtroSetorComercial = new FiltroSetorComercial();
        if (codigoSetorComercial != null
                && !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
            if (idLocalidade != null
                    && !idLocalidade.toString().trim().equalsIgnoreCase("")) {
                filtroSetorComercial.limparListaParametros();
                //coloca parametro no filtro
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.ID_LOCALIDADE, new Integer(
                                idLocalidadeFinal)));
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                        new Integer(codigoSetorComercialFinal)));
                //pesquisa
                Collection setorComerciais = fachada.pesquisar(filtroSetorComercial,
                        SetorComercial.class.getName());
                if (setorComerciais == null || setorComerciais.isEmpty()) {
                	 throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
                }
            }
        } 
		
        String codigoRotaInicial = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getRotaInicial();
		String idRotaInicial = null;
        if((idLocalidade != null
        		&& !idLocalidade.equals(""))
        		&&	
        		(codigoSetorComercial != null
                		&& !codigoSetorComercial.equals(""))
                &&		
                		(codigoRotaInicial != null
                        		&& !codigoRotaInicial.equals(""))){
    		FiltroRota filtroRota = new FiltroRota();
    		filtroRota.adicionarParametro(new ParametroSimples(
    				FiltroRota.CODIGO_ROTA, codigoRotaInicial));
            filtroRota.adicionarParametro(new ParametroSimples(
                    FiltroRota.LOCALIDADE_ID, idLocalidade));
            filtroRota.adicionarParametro(new ParametroSimples(
                    FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercial));		
    		
    		Collection rotas = fachada.pesquisar(filtroRota,Rota.class.getName());
    		if(rotas != null && !rotas.isEmpty()){
    			idRotaInicial = ((Rota)rotas.iterator().next()).getId().toString();
    		}else{
    			throw new ActionServletException(
    				"atencao.pesquisa.rota_inicial_inexistente");			
    		}
        }

        
        String codigoRotaFinal = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getRotaFinal();
		String idRotaFinal = null;
        
        if((idLocalidadeFinal != null
        		&& !idLocalidadeFinal.equals(""))
        		&&	
        		(codigoSetorComercialFinal != null
                		&& !codigoSetorComercialFinal.equals(""))
                &&		
                		(codigoRotaFinal != null
                        		&& !codigoRotaFinal.equals(""))){
        	FiltroRota filtroRota = new FiltroRota();
    		filtroRota.limparListaParametros();
            filtroRota.adicionarParametro(new ParametroSimples(
                    FiltroRota.LOCALIDADE_ID, idLocalidadeFinal));
            filtroRota.adicionarParametro(new ParametroSimples(
                    FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercialFinal));
    		filtroRota.adicionarParametro(new ParametroSimples(
    				FiltroRota.CODIGO_ROTA, codigoRotaFinal));
    		Collection rotas = null;
    		rotas = fachada.pesquisar(filtroRota,Rota.class.getName());
    		if(rotas != null && !rotas.isEmpty()){
    			idRotaFinal = ((Rota)rotas.iterator().next()).getId().toString();
    		}else{
    			throw new ActionServletException(
    				"atencao.pesquisa.rota_final_inexistente");			
    		}
        	
        }
        

        FormFile arquivoImoveis = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getRelacaoImoveis();
        String nomeArquivo = null;
        Collection<Integer> colecaoIdsImovel = new ArrayList<Integer>();
		
		
		//[FS0020] - Validar arquivo
		//===========================================================		
		if(arquivoImoveis != null && arquivoImoveis.getFileSize() != 0){
			
			nomeArquivo = arquivoImoveis.getFileName();
			
			int quantidadeCaractereNomeArquivo;
			
			for(quantidadeCaractereNomeArquivo=0;quantidadeCaractereNomeArquivo<nomeArquivo.length();quantidadeCaractereNomeArquivo++){}
			
			//Caso a extens�o do arquivo n�o corresponda a ".txt"
			String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf('.') +1);
			if(!extensao.equals("txt")){
				
				//limpar o campo "Rela��o de Im�veis"
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.setRelacaoImoveis(null);
				
				//exibir a mensagem "Formato do arquivo <<nome do arquivo >> inv�lido."
				throw new ActionServletException("atencao.formato_arquivo_invalido",null,extensao.toUpperCase());
				
			}
			
			
			if(quantidadeCaractereNomeArquivo>100){
				
				throw new ActionServletException("atencao.arquivo_nome_quantidade_caractere");
			}
			
			File arquivoIdsImoveis = new File(arquivoImoveis.getFileName());
	        byte[] dadosArquivo = null;
			
			
			InputStream fis;
			BufferedReader br;
			String  linha;
			
			try {
				
				dadosArquivo = arquivoImoveis.getFileData(); 	
				
				FileOutputStream fos = new FileOutputStream(arquivoIdsImoveis);
				fos.write(dadosArquivo);
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(arquivoIdsImoveis);
				br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
				
				//[SB0010] - Verificar Matr�culas Informadas na Rela��o de Im�veis
				//=======================================================
				//1. Para cada linha lida no arquivo texto com a Rela��o de Im�veis:
				while ((linha = br.readLine()) != null) {
					
					if(!linha.equals(QUEBRA_LINHA) 
							&& !linha.equals("")
							&& linha.indexOf(" ") == -1
							&& linha.length() <= 10 
							&& Util.validarStringNumerica(linha)){
						
						String idImovel = linha;
						Integer idImovelInt = new Integer(idImovel);
						
						FiltroImovel filtroImovel = new FiltroImovel();
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,ConstantesSistema.NAO));
						Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
						Imovel imovel = (Imovel)Util.retonarObjetoDeColecao(colecaoImovel);
						
						if(imovel != null){
							colecaoIdsImovel.add(idImovelInt);
						}
						
						// 1.1. Caso n�o exista um im�vel no sistema com a matr�cula informada 
						else{
							
							//limpar o campo "Rela��o de Im�veis"
							inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.setRelacaoImoveis(null);
							
							//1.1.1. Exibir a mensagem "Im�vel <<linha lida do arquivo>> inexistente."
							throw new ActionServletException("atencao.imovel_mat_inexistente",null,idImovel);
							
						}
						//=======================================================	
						
					}
					
					//caso o arquivo n�o seja composto apenas por registros num�ricos de at� 10 posi��es separados por uma quebra de linha
					else{
						
						//limpar o campo "Rela��o de Im�veis"
						inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.setRelacaoImoveis(null);
						
						//exibir a mensagem "Arquivo <<nome do arquivo >> com dados inv�lidos."
						throw new ActionServletException("atencao.arquivo_dados_invalidos", null,nomeArquivo);
					}
				}
				
				//caso o arquivo esteja sem dados
				if(colecaoIdsImovel.size() == 0){
					
					//limpar o campo "Rela��o de Im�veis"
					inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.setRelacaoImoveis(null);
					
					//exibir a mensagem "Arquivo <<nome do arquivo >> sem dados."
					throw new ActionServletException("atencao.arquivo_sem_dados",null,nomeArquivo);
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		//===========================================================
		
		if(inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getImoveisSitLigacaoNaoAlteradaDeb() == null ||
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getImoveisSitLigacaoNaoAlteradaDeb().equals("")){
			inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.setImoveisSitLigacaoNaoAlteradaDeb("1");
		}
		
		if(inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getIndicadorImoveisDebito() == null || 
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getIndicadorImoveisDebito().equals("")){
			inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.setIndicadorImoveisDebito("2");
		}		
		
		
        Collection colecaoCobrancaAcaoAtividadeComando = fachada.concluirComandoAcaoCobranca(
        		inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoInicialConta(),
        		inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoFinalConta(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoVencimentoContaInicial(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoVencimentoContaFinal(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getCobrancaAcao(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getCobrancaAtividade(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getCobrancaGrupo(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getGerenciaRegional(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getLocalidadeOrigemID(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getLocalidadeDestinoID(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getSetorComercialOrigemCD(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getSetorComercialDestinoCD(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getIdCliente(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getClienteRelacaoTipo(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getIndicador(),idRotaInicial,
				idRotaFinal,
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getSetorComercialOrigemID(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getSetorComercialDestinoID(),idComando,
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getUnidadeNegocio(),
				this.getUsuarioLogado(httpServletRequest),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getTitulo(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getDescricaoSolicitacao(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPrazoExecucao(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getQuantidadeMaximaDocumentos(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getValorLimiteObrigatoria(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getIndicadorImoveisDebito(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getIndicadorGerarBoletimCadastro(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getCodigoClienteSuperior(), codigoRotaInicial,
				codigoRotaFinal,
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getLogradouroId(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getConsumoMedioInicial(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getConsumoMedioFinal(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getTipoConsumo(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoInicialFiscalizacao(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoFinalFiscalizacao(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getSituacaoFiscalizacao(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getNumeroQuadraInicial(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getNumeroQuadraFinal(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getIdImovel(),
				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getQuantidadeDiasVencimento(),
				nomeArquivo, colecaoIdsImovel );
		
		//pesquisar cobranca acao
		//CobrancaAcao cobrancaAcao =  fachada.consultarCobrancaAcao(inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getCobrancaAcao());
		
		//pesquisar cobranca atividade
		CobrancaAtividade cobrancaAtividade = fachada.consultarCobrancaAtividade(inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getCobrancaAtividade());
				
		montarPaginaSucesso(httpServletRequest,
		           " "+colecaoCobrancaAcaoAtividadeComando.size()+" A��o(�es) de cobran�a para a atividade " 
		           + cobrancaAtividade.getDescricaoCobrancaAtividade() + " comandada(s) com sucesso.",
		           "Inserir outra Comando de A��o de Cobran�a",
		           "exibirInserirComandoAcaoCobrancaAction.do?limparForm=OK&menu=sim");

		return retorno;
	}
		
}
