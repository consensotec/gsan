package gsan.gui.cobranca.cobrancaporresultado;

import gsan.cadastro.localidade.Localidade;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1237] Gerar Relat�rio de Boletim de Medi��o e Acompanhamento
 * 
 * @author Hugo Azevedo
 * @date 13/10/2011
 */
public class ExibirGerarRelatorioBoletimMedicaoAcompanhamentoAction extends GcomAction {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioBoletimMedicaoAcompanhamento");
        
        Fachada fachada = Fachada.getInstancia();
        
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	GerarRelatorioBoletimMedicaoAcompanhamentoActionForm form = (GerarRelatorioBoletimMedicaoAcompanhamentoActionForm) actionForm;
    	
    	//Indicador de bloqueio 
    	String indicador = (String)httpServletRequest.getParameter("indicador");
    	
    	//Indicador do campo que realizou o reload da p�gina
    	String campo = (String)httpServletRequest.getParameter("campo");
    	
    	//Campo de Regi�o selecionado
    	if(campo != null && campo.equals("2")){
    		form.setIdsMunicipio(null);
    		form.setIdsMicroregiao(null);
    	}
    	
    	//Campo de microrregi�o selecionado
    	else if(campo != null && campo.equals("3")){
    		form.setIdsMunicipio(null);
    	}
    	
    	
    	if(indicador != null && !"".equals(indicador))
    		httpServletRequest.setAttribute("indicadorBloqueio", indicador);
        
        //Cole��es da tela
        //=======================================
        
    	//Empresa
    	Collection colecaoEmpresasContratadas = fachada.obterColecaoEmpresasContratadasCobranca();
    	sessao.setAttribute("colecaoEmpresasContratadas", colecaoEmpresasContratadas);
    	
        
        //Ger�ncia Regional
        Collection colecaoGerencia = fachada.obterColecaoGerenciaRegional();
        sessao.setAttribute("colecaoGerenciaRegional", colecaoGerencia);
        
        //Unidade de neg�cio
        Collection colecaoUnidadeNegocio = fachada.obterColecaoUnidadeNegocio(form.getIdsGerenciaRegional());
        sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
        
        //Regi�o
        Collection colecaoRegiao = fachada.obterColecaoRegioes();
        sessao.setAttribute("colecaoRegiao", colecaoRegiao);
        
        //Microregi�o
        Collection colecaoMicroregiao = fachada.obterColecaoMicroRegioes(form.getIdsRegiao());
        sessao.setAttribute("colecaoMicroregiao", colecaoMicroregiao);
        
        //Municipio
        Collection colecaoMunicipio = fachada.obterColecaoMunicipios(form.getIdsRegiao(),form.getIdsMicroregiao());
        sessao.setAttribute("colecaoMunicipio", colecaoMunicipio);
        
      		
  		//Busca da Localidade atrav�s do Enter
  		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
  		if(pesquisarLocalidade != null && !"".equals(pesquisarLocalidade)){
  			Integer idLocalidade = new Integer(form.getIdLocalidade());
  			
  			//[FS0002] - Verificar exist�ncia da localidade
  			Localidade localidade = fachada.pesquisarLocalidadeDigitada(idLocalidade);
  			
  			if(localidade != null){
				
				localidade = null;
				
				//[FS0003] - Verificar localidade relacionada � ger�ncia regional e unidade de neg�cio informada
				if(form.getIdsUnidadeNegocio() != null && form.getIdsUnidadeNegocio().length > 0){
					
					//Caso tenha(m) sido informada(s) unidade(s) de neg�cio
					localidade = fachada.obterColecaoLocalidade(idLocalidade, null,form.getIdsUnidadeNegocio());
					
					if(localidade != null){
		  				form.setDescricaoLocalidade(localidade.getDescricao());
		  				sessao.setAttribute("localidadeBoletimMedicao", localidade);
		  				return retorno;
					}
					else{				
						sessao.removeAttribute("localidadeBoletimMedicao");
		  				form.setIdLocalidade("");
		  				httpServletRequest.setAttribute("localidadeException","ok");
						throw new ActionServletException("atencao.localidade_nao_pertence_unidade_negocio");
					}
				
				}
				else if(form.getIdsGerenciaRegional() != null && form.getIdsGerenciaRegional().length > 0){
					//Caso n�o tenha sido informada nenhuma unidade de neg�cio, mas tenha(m) sido informada(s) ger�ncia(s) regional(ais)
					localidade = fachada.obterColecaoLocalidade(idLocalidade, form.getIdsGerenciaRegional(),null);
					if(localidade != null){
		  				form.setDescricaoLocalidade(localidade.getDescricao());
		  				sessao.setAttribute("localidadeBoletimMedicao", localidade);
		  				return retorno;
					}
					else{
						sessao.removeAttribute("localidadeBoletimMedicao");
		  				form.setIdLocalidade("");
		  				httpServletRequest.setAttribute("localidadeException","ok");
						throw new ActionServletException("atencao.localidade_nao_pertence_gerencia_regional");
					}
				}
				else{
					localidade = fachada.obterColecaoLocalidade(idLocalidade, null,null);
					if(localidade != null){
		  				form.setDescricaoLocalidade(localidade.getDescricao());
		  				sessao.setAttribute("localidadeBoletimMedicao", localidade);
		  				return retorno;
					}
					else{
		  				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
						sessao.removeAttribute("localidadeBoletimMedicao");
		  				form.setIdLocalidade("");
		  				httpServletRequest.setAttribute("localidadeException","ok");
					}
				}
				
			}
			else{
				sessao.removeAttribute("localidadeBoletimMedicao");
  				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
  				form.setIdLocalidade("");
  				httpServletRequest.setAttribute("localidadeException","ok");
			}
  			
  		}
  		
  		//Pesquisar Data de encerramento da Arrecada��o
  		String pesquisarDataEncerramento = httpServletRequest.getParameter("pesquisarDataEncerramento");
  		if(pesquisarDataEncerramento != null && !"".equals(pesquisarDataEncerramento)){
  			
  			
  	        if(!this.validarMesAno(form.getPeriodoApuracao()))
  	        	throw new ActionServletException("atencao.data_invalida", null,form.getPeriodoApuracao());
  			
  			
  			//Somando um m�s ao per�odo de refer�ncia
  			String mesSomado = Util.somaMesMesAnoComBarra(form.getPeriodoApuracao(), 1);
  			
  			//N�mero do Processo
  			Integer idProcesso = new Integer(50);
  			
  			Date dataEncerramento = fachada.obterDataEncerramentoProcesso(idProcesso, mesSomado);
  			
  			if(dataEncerramento != null){
  				form.setEncerramentoArrecadacao(Util.formatarData(dataEncerramento));
  			}
  			else {
  				form.setEncerramentoArrecadacao("");
  		      	throw new ActionServletException("atencao.dados_gerados_boletim_inexistentes", null, form.getPeriodoApuracao());
  			}
  
  		}
        
        
        return retorno;
    }
    
    
    private boolean validarMesAno(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 7) {
			String mes = mesAnoReferencia.substring(0, 2);
			String barra = mesAnoReferencia.substring(2, 3);

			try {
				int mesInt = Integer.parseInt(mes);
				// int anoInt = Integer.parseInt(ano);

				if (mesInt > 12 || !barra.equals("/")) {
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
