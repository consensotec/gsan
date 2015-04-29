package gsan.gui.relatorio.cadastro;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.cadastro.RelatorioAcessoSPC;
import gsan.relatorio.cadastro.RelatorioAcessoSPCBean;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1170] - Gerar Relat�rio Acesso ao SPC
 * 
 * @author Diogo Peixoto
 * @since 09/05/2011
 * 
 */
public class GerarRelatorioAcessoSPCAction extends ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
        
		GerarRelatorioAcessoSPCActionForm form = (GerarRelatorioAcessoSPCActionForm) actionForm;
        String nomeRelatorio = ConstantesRelatorios.RELATORIO_ACESSO_SPC;
        RelatorioAcessoSPC relatorioAcessoSPC = new RelatorioAcessoSPC(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), nomeRelatorio);
        
        boolean parametroInformado = false;

        Date dataReferenciaInicial	= null;
		Date dataReferenciaFinal = null;
		String dataReferencia = null;
		if (form.getDataReferenciaInicial() != null && !form.getDataReferenciaInicial().equals("")) {
			
			dataReferenciaInicial = Util.converteStringParaDate(form.getDataReferenciaInicial());
			dataReferenciaInicial = Util.formatarDataInicial(dataReferenciaInicial);
			
			if (form.getDataReferenciaFinal() != null && !form.getDataReferenciaFinal().equals("")) {
				dataReferenciaFinal = Util.converteStringParaDate(form.getDataReferenciaFinal());
				dataReferenciaFinal = Util.adaptarDataFinalComparacaoBetween(dataReferenciaFinal);
			} else {
				dataReferenciaFinal = new Date();
				dataReferenciaFinal = Util.formatarDataFinal(dataReferenciaFinal);
			}
			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataReferenciaInicial, dataReferenciaFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}else{
				if(Util.compararData(dataReferenciaFinal, new Date()) < 1){
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					dataReferencia = sdf.format(dataReferenciaInicial) + " - " + sdf.format(dataReferenciaFinal);
					parametroInformado = true;
				}else{
					throw new ActionServletException("atencao.periodo_referencia.maior.periodo_atual");
				}
			}
		}
		
		String loginUsuarioResponsavel = form.getLoginUsuarioResponsavel();
		String usuarioResponsavel = null;
		if(loginUsuarioResponsavel != null && !loginUsuarioResponsavel.equals("")){
			FiltroUsuario filtro = new FiltroUsuario();
			filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuarioResponsavel));
			Collection<Usuario> usuario = Fachada.getInstancia().pesquisar(filtro, Usuario.class.getName() );
			if ( usuario != null && usuario.size() > 0 ){
				Usuario usuarioResp = (Usuario) usuario.iterator().next();
				usuarioResponsavel = "USU�RIO: " + usuarioResp.getLogin() + " - " + usuarioResp.getNomeUsuario();
				parametroInformado = true;
			}else{
				throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
			}
		}
		
		String unidadeOrganizacional = form.getIdUnidadeOrganizacional();
		Integer idUnidade = null;
		if(unidadeOrganizacional != null && !unidadeOrganizacional.equals("")){
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro( new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeOrganizacional));
			Collection<UnidadeOrganizacional> unidade = Fachada.getInstancia().pesquisar(filtro, UnidadeOrganizacional.class.getName() );
			
			if (unidade != null && unidade.size() > 0 ){
				UnidadeOrganizacional unidadeOrg = (UnidadeOrganizacional) unidade.iterator().next();
				idUnidade = new Integer(unidadeOrganizacional);
				unidadeOrganizacional = "UNIDADE ORGANIZACIONAL: " + unidadeOrg.getId() + " - " + unidadeOrg.getDescricao();
				parametroInformado = true;
			}else{
				throw new ActionServletException("atencao.unidade.organizacional.inexistente");
			}
		}
		
		if(parametroInformado){
			FiltrarRelatorioAcessoSPCHelper filtro = new FiltrarRelatorioAcessoSPCHelper(
					dataReferenciaInicial, dataReferenciaFinal, loginUsuarioResponsavel, idUnidade);
			Collection<RelatorioAcessoSPCBean> helper = Fachada.getInstancia().filtrarRelatorioAcessoSPC(filtro);
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
			relatorioAcessoSPC.addParametro("dataReferencia", dataReferencia);
			relatorioAcessoSPC.addParametro("tipoRelatorio", Integer.parseInt(tipoRelatorio));
			relatorioAcessoSPC.addParametro("dadosRelatorio", helper);
			relatorioAcessoSPC.addParametro("unidade", unidadeOrganizacional);
			relatorioAcessoSPC.addParametro("usuario", usuarioResponsavel);
			try {
				retorno = processarExibicaoRelatorio(relatorioAcessoSPC, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
			} catch (RelatorioVazioException ex) {
				// manda o erro para a p�gina no request atual
				throw new ActionServletException("atencao.relatorio.vazio");
			}
		}else{
			throw new ActionServletException("atencao.filtrar_informar_um_filtro");
		}
		return retorno;
	}
	
	/**
     * Verifica se o M�s/Ano informado � inferior ao M�s/Ano do Sistema
     */
    public boolean verificarReferenciaAcesso(String anoMesReferenciaContabil, SistemaParametro sistemaParametro)  {

        boolean verificacao = Util.compararAnoMesReferencia(anoMesReferenciaContabil.substring(3, 7)
                + anoMesReferenciaContabil.substring(0, 2), sistemaParametro.getAnoMesFaturamento().toString(), ">");

        if (verificacao) {        	
            String anoMesSistema = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento());
            throw new ActionServletException("atencao.referencia_contabil.ano_mes.inferior", null, anoMesSistema);
        }
        return true;
    }
}