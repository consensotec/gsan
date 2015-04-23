package gsan.gui.cobranca.cobrancaporresultado;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.cobranca.cobrancaporresultado.RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiro;
import gsan.relatorio.cobranca.cobrancaporresultado.RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiroBean;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1239] Gerar Relatório de Penalidades por Índice de Atuação e Sucesso Financeiro
 * 
 * @author Hugo Azevedo
 * @date 26/10/2011
 */

public class GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroAction extends ExibidorProcessamentoTarefaRelatorio {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioBoletimMedicaoAcompanhamento");
        
        Fachada fachada = Fachada.getInstancia();
        
        GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm form = (GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm) actionForm;
        
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
        
        Collection<RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiroBean> colecaoPenalidades = 
        		fachada.obterComandosEmPenalidades(idEmpresa,dataInicial,dataFinal);
        
        //Caso a busca não retorne nenhum registro,
        if(colecaoPenalidades.size() == 0){
        	throw new ActionServletException("atencao.nao_existem_registros_periodo");
        }
        
        RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiroBean bean = 
        		(RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiroBean) Util.retonarObjetoDeColecao(colecaoPenalidades);
        
        
        TarefaRelatorio relatorio = new RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiro(
        		(Usuario) (httpServletRequest.getSession(false))
				.getAttribute("usuarioLogado"));
        
        relatorio.addParametro("colecaoPenalidades", colecaoPenalidades);
        relatorio.addParametro("dataInicial",form.getDataPeriodoExInicial());
        relatorio.addParametro("dataFinal",form.getDataPeriodoExFinal());
        relatorio.addParametro("empresa",bean.getNomeEmpresa());
        relatorio.addParametro("contrato",bean.getNumeroContrato());
        
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
