package gcom.gui.cobranca.cobrancaporresultado;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioAcompanhamentoComandosCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1238] Gerar Relatório de Acompanhamento dos Comandos de Cobrança
 * 
 * @author Mariana Victor
 * @date 08/11/2011
 */
public class GerarRelatorioAcompanhamentoComandosCobrancaAction extends ExibidorProcessamentoTarefaRelatorio {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = null;
        
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
        
		GerarRelatorioAcompanhamentoComandosCobrancaActionForm form = (GerarRelatorioAcompanhamentoComandosCobrancaActionForm) actionForm;
        
        int tipoFormatoRelatorio = TarefaRelatorio.TIPO_PDF;
        
        //Dados do formulário
        Integer idEmpresa = new Integer(form.getIdEmpresaContratada());
        Date dataInicial = null;
        Date dataFinal = null;
        
        //[FS0003] - Verificar validade das datas
        //====================================================
        
        //Caso a data inicial ou a data final informadas não sejam datas válidas
        if(!validarData(form.getDataPeriodoExInicial())){
        	throw new ActionServletException(
					"atencao.data_invalida","Inicial");
        }
        if(!validarData(form.getDataPeriodoExFinal())){
        	throw new ActionServletException(
					"atencao.data_invalida","Final");
        }
        
        dataInicial = Util.converteStringParaDate(form.getDataPeriodoExInicial());
        dataFinal =  Util.converteStringParaDate(form.getDataPeriodoExFinal());
        
        //Caso a data final informada seja menor que a data inicial informada
        if(Util.compararData(dataInicial, dataFinal) == 1){
        	throw new ActionServletException(
					"atencao.data_final_menor_data_inicial");
        }
        
        TarefaRelatorio relatorio = new RelatorioAcompanhamentoComandosCobranca(
        		(Usuario) (httpServletRequest.getSession(false))
				.getAttribute("usuarioLogado"));
        
        relatorio.addParametro("dataInicial",form.getDataPeriodoExInicial());
        relatorio.addParametro("dataFinal",form.getDataPeriodoExFinal());
        relatorio.addParametro("idEmpresa",idEmpresa);
        relatorio.addParametro("tipoFormatoRelatorio",tipoFormatoRelatorio);
        
    	retorno = processarExibicaoRelatorio(relatorio,
    			tipoFormatoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

        return retorno;
    }
    
    
    //Método pra validar a data
    private boolean validarData(String inDate) {
		if (inDate == null) {
			return false;	
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if (inDate.trim().length() != dateFormat.toPattern().length()) {
			return false;	
		}
		
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
			} catch (ParseException pe) {
				return false;
		}
		return true;
	}

}
