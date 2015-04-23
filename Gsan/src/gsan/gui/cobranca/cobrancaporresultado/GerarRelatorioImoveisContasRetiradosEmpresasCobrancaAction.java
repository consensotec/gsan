package gsan.gui.cobranca.cobrancaporresultado;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.cobranca.cobrancaporresultado.RelatorioImoveisContasRetiradosEmpresasCobranca;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
 * [UC1257] - Gerar Relatório dos Imóveis e Contas Retirados das Empresas de Cobrança
 * 
 * @author Hugo Azevedo
 * @date 16/12/2011
 */
public class GerarRelatorioImoveisContasRetiradosEmpresasCobrancaAction extends ExibidorProcessamentoTarefaRelatorio {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = null;
        
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
        
		GerarRelatorioImoveisContasRetiradosEmpresasCobrancaActionForm form =
				(GerarRelatorioImoveisContasRetiradosEmpresasCobrancaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
        
        int tipoFormatoRelatorio = Integer.parseInt((String)httpServletRequest.getParameter("tipoRelatorio"));
    	
    	String idEmpresa = form.getIdEmpresa();
    	String amReferenciaInicial = form.getAmReferenciaInicial();
    	String amReferenciaFinal = form.getAmReferenciaFinal();
    	
    	
    	
    	
    	//[FS0001] - Verificar preenchimento dos campos
    	//==============================================
    	
    	if(idEmpresa == null || idEmpresa.trim().equals("")){
    		throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Empresa");
    	}
    	else if(amReferenciaInicial == null || amReferenciaInicial.trim().equals("")){
    		throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Referência Inicial");
    	}
    	else if(amReferenciaFinal == null || amReferenciaFinal.trim().equals("")){
    		throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Referência Final");
    	}
    	//===============================================
   

		//[FS0004] - Validar referência inicial
		if(!this.validarMesAno(amReferenciaInicial.trim())){
			throw new ActionServletException("atencao.referencia_inicial_invalida.informe_outra");
		}
		
		//[FS0005] - Validar referência final
		if(!this.validarMesAno(amReferenciaFinal.trim())){
			throw new ActionServletException("atencao.referencia_final_invalida.informe_outra");
		}
		
		// Tratando os periodos (mes/ano) para que possam ser feitos os filtros
		int amReferenciaInicialTratado = Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaInicial));
		int amReferenciaFinalTratado = Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(amReferenciaFinal));
		
		//[FS0006] - Verificar referência final maior que mês/ano atual
		if(amReferenciaFinalTratado > Util.formataAnoMes(new Date()).intValue()){
			throw new ActionServletException("atencao.referencia_final_maior_data_atual",null,Util.recuperaMesAnoComBarraDaData(new Date()));
		}
			
		
    	
    	//[FS0003] - Verificar atributos iniciais e finais
    	//===================================================
  
    	//Caso seja informado o atributo inicial e não seja informado o atributo final
    	if (amReferenciaInicial != null
				&& !amReferenciaInicial.trim().equals("")
				&& (amReferenciaFinal == null
					|| amReferenciaFinal.trim().equals(""))) {
    		throw new ActionServletException("atencao.informe_campo_inicial",null,"Referência Inicial");
    	}
    	
    	//Caso seja informado o atributo final e não seja informado o atributo inicial
    	else if ((amReferenciaInicial == null
					|| amReferenciaInicial.trim().equals(""))
				&& amReferenciaFinal != null
				&& !amReferenciaFinal.trim().equals("")) {
    		throw new ActionServletException("atencao.informe_campo_final",null,"Referência Final");
    		
    	}
    	
    	//Caso o conteúdo do atributo final seja menor (inferior, anterior) ao conteúdo do atributo inicial
    	if(amReferenciaFinalTratado < amReferenciaInicialTratado){
    		throw new ActionServletException("atencao.final_menor_inicial",null,"Referência");
    	}
    	
    	//============================================
    	
    	
		//[FS0007] - Verificar existência da empresa
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.ID, idEmpresa));

		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.empresa.inexistente");
		} 
		
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresa);
		
		//Contador de registros
		Integer qtd = fachada.obterQtdRelatorioImoveisContasRetiradosEmpresasCobranca(new Integer(idEmpresa), amReferenciaInicialTratado, amReferenciaFinalTratado);
		
		//[FS0002] - Nenhum registro encontrado
		if(qtd == null || qtd.compareTo(new Integer(0)) <= 0){
			throw new ActionServletException("atencao.colecao_vazia");
		}
		
		
		 //Dados do formulário
        TarefaRelatorio relatorio = new RelatorioImoveisContasRetiradosEmpresasCobranca(
        		(Usuario) (httpServletRequest.getSession(false))
				.getAttribute("usuarioLogado"));
        
        relatorio.addParametro("tipoFormatoRelatorio",tipoFormatoRelatorio);
        //relatorio.addParametro("colecaoRelatorio", colecao);
        relatorio.addParametro("empresa", empresa);
        relatorio.addParametro("referenciaInicial", amReferenciaInicial);
        relatorio.addParametro("referenciaFinal", amReferenciaFinal);
        
    	retorno = processarExibicaoRelatorio(relatorio,
    			tipoFormatoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
        return retorno;
    }
    
    
    private boolean validarMesAno(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 7) {
			String mes = mesAnoReferencia.substring(0, 2);
			String barra = mesAnoReferencia.substring(2, 3);

			if(barra.equals("/")){
			
				try {
					int mesInt = Integer.parseInt(mes);
					// int anoInt = Integer.parseInt(ano);
	
					if (mesInt > 12) {
						mesAnoValido = false;
					}
				} catch (NumberFormatException e) {
					mesAnoValido = false;
				}
				
			} else {
				mesAnoValido = false;
			}
			
		} else {
			mesAnoValido = false;
		}

		return mesAnoValido;
	}

}
