package gcom.gui.arrecadacao;


import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza a pesquisa de responsavel superior de acordo com os par�metros
 * informados
 * 
 * @author Vinicius Medeiros
 * @created 26/05/2008
 */

public class PesquisarAgenciaBancariaAction extends GcomAction{
	
	/**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        
    	ActionForward retorno = actionMapping.findForward("listaAgenciaBancaria");

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        
		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

        PesquisarAgenciaBancariaActionForm pesquisarAgenciaBancariaActionForm 
        	= (PesquisarAgenciaBancariaActionForm) actionForm;

        //Recupera os par�metros do form
        String nomeAgencia = pesquisarAgenciaBancariaActionForm.getNomeAgencia();
        String bancoID = pesquisarAgenciaBancariaActionForm.getBancoID();
        String codigo = pesquisarAgenciaBancariaActionForm.getCodigo();
        
        FiltroBanco filtroBanco = new FiltroBanco();
	
        if(pesquisarAgenciaBancariaActionForm.getBancoID() != null && !pesquisarAgenciaBancariaActionForm.getBancoID().trim().equals("")){
        //Cria o filtro para pesquisar o banco e seta o c�digo do banco informada no filtro
		filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID, bancoID));
		}
		
		// Cria a cole��o de banco
		Collection colecaoBanco = null;
		
		//Pesquisa o banco de acordo com o c�digo informado
		colecaoBanco = fachada.pesquisar(filtroBanco,Banco.class.getName());
			
		//Caso o banco n�o esteja cadastrado levanta uma exce��o para o usu�rio
		//caso contr�rio recupera o banco pesquisado 
		if(colecaoBanco==null || colecaoBanco.isEmpty()){
			throw new ActionServletException(
					"atencao.entidade.inexistente",
					null,
					"Banco");
		
		}
		FiltroAgencia filtroAgencia = new FiltroAgencia();
        boolean informouParametro= false;
        
        //validar as informa��es
        if ( nomeAgencia != null && !nomeAgencia.equalsIgnoreCase( "" ) ){
        	filtroAgencia.adicionarParametro( new ComparacaoTexto( FiltroAgencia.NOME_AGENCIA, nomeAgencia ) );
        	informouParametro = true;
        }
        	
        if ( codigo != null && !codigo.equalsIgnoreCase( "" ) ){
        	filtroAgencia.adicionarParametro( new ParametroSimples( FiltroAgencia.CODIGO_AGENCIA, codigo ) );
        	informouParametro = true;
        }
        
        
        if ( bancoID != null && !bancoID.equalsIgnoreCase( "" ) ){
        	filtroAgencia.adicionarParametro( new ParametroSimples( FiltroAgencia.BANCO_ID, bancoID ) );
        	informouParametro = true;
        }
        
        
        if (!informouParametro){
        	throw new ActionServletException("atencao.filtrar_informar_um_filtro");
        }
       
        //filtro
        
        filtroAgencia
        	.adicionarCaminhoParaCarregamentoEntidade("banco");
        
        //Faz a busca das empresas
        Collection colecaoAgencia = Fachada.getInstancia()
        	.pesquisar(filtroAgencia, Agencia.class.getName());

  	    // Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroAgencia, Agencia.class.getName());
		colecaoAgencia = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		if(colecaoAgencia == null || colecaoAgencia.isEmpty()){
			throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Ag�ncia Banc�ria");
		}
        sessao.setAttribute("colecaoAgencia", colecaoAgencia);
        
        //retorno
        return retorno;
    }


}
