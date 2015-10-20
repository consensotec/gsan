package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidade;
import gcom.relatorio.atendimentopublico.RelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeArquivoTxt;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1221] � Gerar Boletim de Medi��o Ordem de Servi�o Inspe��o Anormalidade
 * 
 * @since 19/08/2011
 * @author Diogo Peixoto
 *
 */
public class GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction extends ExibidorProcessamentoTarefaRelatorio {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = null;
		
		GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form = 
			(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2) actionForm;
		
		HttpSession sessao = request.getSession();

		String antigoPeriodo = (String) sessao.getAttribute("periodoGeracao");
		String novoPeriodo = form.getPeriodoApuracao();
		
		if(!this.validarPeriodo(antigoPeriodo, novoPeriodo)){
			throw new ActionServletException("atencao.periodo_nao_valido");
		}
		
		if(this.validarFiltro(form)){
			FiltrarOrdensServicosComandoOrdemSeletivaHelper filtro = new FiltrarOrdensServicosComandoOrdemSeletivaHelper();
			filtro.setIdComando(Integer.valueOf(form.getIdComando()));
			filtro.setMesAnoApuracao(form.getPeriodoApuracao());
			
			Integer idGerencia = Util.verificarIdNaoVazio(form.getIdGerencia()) ? Integer.valueOf(form.getIdGerencia()) : null;
			Integer idLocalidade = null;
			try{
				idLocalidade = Util.verificarIdNaoVazio(form.getIdLocalidade()) ? Integer.valueOf(form.getIdLocalidade()) : null;
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.codigo_localidade_invalido");
			}
			Integer idUnidadeNegocio = Util.verificarIdNaoVazio(form.getIdUnidadeNegocio()) ? Integer.valueOf(form.getIdUnidadeNegocio()) : null;
			
			Integer idRegiao = Util.verificarIdNaoVazio(form.getIdRegiao()) ? Integer.valueOf(form.getIdRegiao()) : null;
			Integer idMicroregiao = Util.verificarIdNaoVazio(form.getIdMicroregiao()) ? Integer.valueOf(form.getIdMicroregiao()) : null;
			Integer idMunicipio = Util.verificarIdNaoVazio(form.getIdMunicipio()) ? Integer.valueOf(form.getIdMunicipio()) : null;
			
			
			filtro.setIdGerencia(idGerencia);
			filtro.setIdUnidadeNegocio(idUnidadeNegocio);
			
			//[FS0009 - Validar Localidade do Comando]
			//HttpSession sessao = request.getSession();
			ComandoOrdemSeletiva comandoOS = (ComandoOrdemSeletiva) sessao.getAttribute("comandoOS");
			if(comandoOS.getLocalidadeInicial() != null && comandoOS.getLocalidadeFinal() != null && idLocalidade != null){
				if(idLocalidade < comandoOS.getLocalidadeInicial().getId() || idLocalidade > comandoOS.getLocalidadeFinal().getId()){
					throw new ActionServletException("atencao.localidade_informada_comando");
				}
			}
			filtro.setIdLocalidade(idLocalidade);
			
			filtro.setIdRegiao(idRegiao);
			filtro.setIdMicroregiao(idMicroregiao);
			filtro.setIdMunicipio(idMunicipio);
						
			TarefaRelatorio relatorio = null;
			
			try {
				Usuario usuario = (Usuario)request.getSession(false).getAttribute("usuarioLogado");
				String opcaoRelatorio = request.getParameter("opcaoRelatorio");
				// Gerar Arquivo TXT
				if (opcaoRelatorio != null && opcaoRelatorio.equals("1")) {
					relatorio = new RelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeArquivoTxt(usuario);

					String tipoRelatorioTxt = String.valueOf(TarefaRelatorio.TIPO_HTML) ;
					relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorioTxt));
					relatorio.addParametro("filtrarRelatorioBoletimMedicaoOSSeletiva", filtro);

					retorno = processarExibicaoRelatorio(relatorio, tipoRelatorioTxt, request, response, mapping);
				}else{
				
					relatorio = new RelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidade(usuario);

					String tipoRelatorio = String.valueOf(TarefaRelatorio.TIPO_PDF) ;
					relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
					relatorio.addParametro("filtrarRelatorioBoletimMedicaoOSSeletiva", filtro);
					relatorio.addParametro("mesAnoApuracao", filtro.getMesAnoApuracao());
					
					retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, request, response, mapping);
				}
			} catch (SistemaException ex) {
				reportarErros(request, "erro.sistema");
				retorno = mapping.findForward("telaErroPopup");
			} catch (RelatorioVazioException ex1) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
			}
		}else{
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		return retorno;
	}
	
	/**
	 * M�todo auxiliar para a valida��o dos filtros do comando.
	 * 
	 * Para o filtro est� v�lido o usu�rio deve ter informado:
	 * 
	 *  1. Nenhum Filtro
	 *  2. Se escolher algum dos filtros Ger�ncia/Unidade Neg�cio/Localidade e n�o escolher Regi�o ou MicroRegi�o ou Munic�pio
	 *  3. Se escolher algum dos filtros Regi�o/MicroRegi�o/Munic�pio e n�o escolher Ger�ncia ou Unidade Neg�cio ou Localidade
	 * 
	 * 
	 * @param form
	 * @return Se o filtro est� v�lido ou n�o.
	 */
	private boolean validarFiltro(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form){
		boolean valido = false;
		//Caso a ger�ncia tenha sido informada e o id da regi�o n�o tenha sido informado, filtro v�lido.
		if((Util.verificarIdNaoVazio(form.getIdGerencia()) || Util.verificarIdNaoVazio(form.getIdLocalidade())) && !Util.verificarIdNaoVazio(form.getIdRegiao())){
			valido = true;
		//Caso a regi�o tenha sido informada e o id da ger�ncia n�o tenha sido informado, filtro v�lido.
		}else if((!Util.verificarIdNaoVazio(form.getIdGerencia()) && !Util.verificarIdNaoVazio(form.getIdLocalidade())) && Util.verificarIdNaoVazio(form.getIdRegiao())){
			valido = true;
		}else if(!Util.verificarIdNaoVazio(form.getIdGerencia()) && !Util.verificarIdNaoVazio(form.getIdRegiao())){
			valido = true;
		}
		return valido;
	}
	
	/**
	 * M�todo auxiliar para a valida��o do novo per�odo informado.
	 * 
	 * @param String, String
	 * @return Se o per�odo est� valido ou n�o.
	 * 
	 */
	private boolean validarPeriodo(String antigoPeriodo , String novoPeriodo){
		boolean valido = true;
		if(antigoPeriodo.equals(novoPeriodo)){
			return valido;
		}else{
			Integer anoMesAntigo = Util.formatarMesAnoComBarraParaAnoMes(antigoPeriodo);
			Integer anoMesNovo = Util.formatarMesAnoComBarraParaAnoMes(novoPeriodo);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", new Locale("pt", "br"));
			String mesAno = sdf.format(new Date());
			Integer anoMesCorrente = Util.formatarMesAnoComBarraParaAnoMes(mesAno);
			if(anoMesNovo < anoMesAntigo){
				valido = false;
			}else if(anoMesNovo > anoMesCorrente){
				valido = false;
			}else{
				valido = true;
			}
		}
		return valido;
	}
	
}