package gcom.gui.operacional;


import java.util.Collection;
import java.util.Map;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

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
 * @author Arthur Carvalho
 * @created 06 de maio de 2008
 */

public class PesquisarDistritoOperacionalAction extends GcomAction{
	
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
        
    	ActionForward retorno = actionMapping.findForward("resultadoPesquisaDistritoOperacionalJSP");

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarDistritoOperacionalActionForm pesquisarDistritoOperacionalActionForm 
        	= (PesquisarDistritoOperacionalActionForm) actionForm;

        //Recupera os par�metros do form
        String nome = pesquisarDistritoOperacionalActionForm.getNomeDistritoOperacional();
        
        String setorAbastecimento = pesquisarDistritoOperacionalActionForm.getSetorAbastecimento();
        
        String zonaAbastecimento = pesquisarDistritoOperacionalActionForm.getZonaAbastecimento();
        
        String indicadorUso = pesquisarDistritoOperacionalActionForm.getIndicadorUso();
        
        
        FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
        boolean informouParametro= false;
        
        //validar as informa��es
        if ( nome != null && !nome.equalsIgnoreCase( "" ) ){
        	filtroDistritoOperacional.adicionarParametro( new ComparacaoTexto( FiltroDistritoOperacional.DESCRICAO, nome ) );
        	informouParametro = true;
        }
        	
        if ( setorAbastecimento != null && !setorAbastecimento.equalsIgnoreCase( "-1" ) ){
        	filtroDistritoOperacional.adicionarParametro( new ParametroSimples( FiltroDistritoOperacional.SETORABASTECIMENTO, setorAbastecimento ) );
        	informouParametro = true;
        }
        
        if (zonaAbastecimento !=null && !zonaAbastecimento.equalsIgnoreCase( "-1" ) ){
        	filtroDistritoOperacional.adicionarParametro(new ParametroSimples (FiltroDistritoOperacional.ZONAABASTECIMENTO, zonaAbastecimento));
        	informouParametro = true;
        }
        
        if (indicadorUso !=null && !indicadorUso.equalsIgnoreCase( "" )){
        	filtroDistritoOperacional.adicionarParametro( new ParametroSimples( FiltroDistritoOperacional.INDICADORUSO, indicadorUso ) );
        	informouParametro = true;
        }
        
        if (!informouParametro){
        	throw new ActionServletException("atencao.filtrar_informar_um_filtro");
        }
       
        //filtro
        
        filtroDistritoOperacional
        	.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
        
        filtroDistritoOperacional
    		.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
        
        //Faz a busca das empresas
        Collection colecaoDistrito = Fachada.getInstancia()
        	.pesquisar(filtroDistritoOperacional, DistritoOperacional.class.getName());

  	    // Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroDistritoOperacional, DistritoOperacional.class.getName());
		colecaoDistrito = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		if(colecaoDistrito == null || colecaoDistrito.isEmpty()){
			//Nenhum logradouro cadastrado de acordo com os par�metros passados
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Distrito Operacional");
		}
        sessao.setAttribute("colecaoDistrito", colecaoDistrito);
        
        //retorno
        return retorno;
    }


}
