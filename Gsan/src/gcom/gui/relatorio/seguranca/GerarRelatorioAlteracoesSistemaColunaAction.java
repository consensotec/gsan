package gcom.gui.relatorio.seguranca;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.seguranca.RelatorioAlteracoesSistemaColuna;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1074] Gerar Relat�rio Altera��es no Sistema por Coluna
 * 
 * @author Hugo Amorim
 * @date 08/09/2010
 */
public class GerarRelatorioAlteracoesSistemaColunaAction extends
		ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		GerarRelatorioAlteracoesSistemaColunaForm form =
			(GerarRelatorioAlteracoesSistemaColunaForm) actionForm;
		
		//	Executar valida��es existentes
		//no caso de uso [UC1074] 
		//Gerar Relat�rio Altera��es no Sistema por Coluna
		this.executarValidacoes(form);

		GerarRelatorioAlteracoesSistemaColunaHelper helper = this.criarHelperRelatorioAlteracoesSistemaColuna(form);
		
		RelatorioAlteracoesSistemaColuna relatorioAlteracoesSistemaColuna =
			new RelatorioAlteracoesSistemaColuna(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"),
					helper.getTipoRelatorio().equals("1")?
					ConstantesRelatorios.RELATORIO_ALTERACOES_SISTEMA_COLUNA_USUARIO:
					ConstantesRelatorios.RELATORIO_ALTERACOES_SISTEMA_COLUNA_LOCALIDADE);
		
		//Adiciona filtro escolhido pelo usuario ao relatorio
		relatorioAlteracoesSistemaColuna.addParametro("filtroHelper", helper);
		
		// Chama o met�do de gerar relat�rio passando o c�digo da analise
		//como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioAlteracoesSistemaColuna.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorioAlteracoesSistemaColuna,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);
		
		return retorno;
	}

	private GerarRelatorioAlteracoesSistemaColunaHelper criarHelperRelatorioAlteracoesSistemaColuna(
			GerarRelatorioAlteracoesSistemaColunaForm form) {
		
		GerarRelatorioAlteracoesSistemaColunaHelper retorno = null;
		
		//Caso tipo do relatorio a ser executado seja "USUARIO"
		if(form.getTipoRelatorioEscolhido().equals("1")){
			retorno = new GerarRelatorioAlteracoesSistemaColunaHelper(
					form.getTipoRelatorioEscolhido(),
					form.getIdFuncionalidade()!=null?form.getIdFuncionalidade():null,
					form.getIdOperacao()!=null?form.getIdOperacao():null,
					form.getIdColuna(),
					form.getDescColuna(),
					form.getIdMeioSolicitacao(),		
					form.getPeriodoInicial(),
					form.getPeriodoFinal(),
					form.getIdUnidadeSuperior()!=null?form.getIdUnidadeSuperior():null,
					!Util.isVazioOrNulo(form.getColecaoUnidadeOrganizacional())?form.getColecaoUnidadeOrganizacional():null,
					form.getIdUsuario()!=null?form.getIdUsuario():null);
		}
		
		//Caso tipo do relatorio a ser executado seja "LOCALIDADE"
		if(form.getTipoRelatorioEscolhido().equals("2")){
			retorno = new GerarRelatorioAlteracoesSistemaColunaHelper(
					form.getTipoRelatorioEscolhido(),
					form.getIdFuncionalidade()!=null?form.getIdFuncionalidade():null,
					form.getIdOperacao()!=null?form.getIdOperacao():null,
					form.getIdColuna(),
					form.getDescColuna(),
					form.getIdMeioSolicitacao(),		
					form.getPeriodoInicial(),
					form.getPeriodoFinal(),
					form.getIdGerenciaRegional()!=null?form.getIdGerenciaRegional():null,
					form.getIdUnidadeNegocio()!=null?form.getIdUnidadeNegocio():null,
					form.getIdLocalidade()!=null?form.getIdLocalidade():null);
		}
		
		
		return retorno;
	}

	private void executarValidacoes(GerarRelatorioAlteracoesSistemaColunaForm form) {
		
		// FS0004 Validar Data
		//	A data dever� vir preenchida com o per�odo de um m�s, considerando a data final como o dia atual.
		//Caso o usu�rio altere a data e esta esteja com um per�odo maior que um m�s, o sistema dever� exibir a 
		//seguinte mensagem: "Informe per�odo compreendido dentro de um m�s"
		if(Util.verificarNaoVazio(form.getPeriodoInicial()) 
				&& !Util.verificarNaoVazio(form.getPeriodoFinal())){
			
			if(Util.dataDiff(Util.converteStringParaDate(form.getPeriodoInicial()), new Date())>1){
				throw new ActionServletException("atencao.periodo_maior_um_mes");
			}
				
		}
		//FIM FS0004 Validar Data
		
		//[FS0005] Verificar data final menor que data inicial
		//  . Caso a data final seja anterior � data inicial, 
		//exibir a mensagem "Data Final do Per�odo � anterior � 
		//Data Inicial do Per�odo" e retornar para o passo 
		//correspondente no fluxo principal.
		if(Util.verificarNaoVazio(form.getPeriodoInicial()) 
				&& Util.verificarNaoVazio(form.getPeriodoFinal())){
			
			Date periodoInicial = Util.converteStringParaDate(form.getPeriodoInicial());
			Date periodoFinal = Util.converteStringParaDate(form.getPeriodoFinal());
			
			if(periodoFinal.compareTo(periodoInicial)<0){
				throw new ActionServletException("atencao.data_final_periodo.anterior.data_inicial_periodo");
			}
			
			if(Util.dataDiff(periodoInicial,periodoFinal)>1){
				throw new ActionServletException("atencao.periodo_maior_um_mes");
			}
				
		}
		//FIM [FS0005] Verificar data final menor que data inicial
		
		//[FS0006] Verificar unidade do usu�rio
		//	.Caso seja informado o nome do usu�rio e a unidade superior ou organizacional e o 
		//usu�rio n�o corresponda a essas, exibir a seguinte mensagem: "O usu�rio n�o pertence � unidade selecionada".
		
		//Unidade Superior
		if(Util.verificarNaoVazio(form.getIdUsuario()) &&
			(Util.verificarNaoVazio(form.getIdUnidadeSuperior()) || 
			!Util.isVazioOrNulo(form.getColecaoUnidadeOrganizacional()))){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, new Integer(form.getIdUsuario())));
			
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL);
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuario.UNIDADE_ORGANIZACIONAL_SUPERIOR);

			Collection colecaoUsuario = this.getFachada().pesquisar(
					filtroUsuario, Usuario.class.getName());
			
			Usuario usuarioValidacao = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			
			if(usuarioValidacao!=null 
				&& Util.verificarNaoVazio(form.getIdUnidadeSuperior())
				&& usuarioValidacao.getUnidadeOrganizacional()!=null
				&& usuarioValidacao.getUnidadeOrganizacional().getUnidadeSuperior()!=null
				&& usuarioValidacao.getUnidadeOrganizacional().getUnidadeSuperior().getId().compareTo(
					new Integer(form.getIdUnidadeSuperior()))!=0){
				throw new ActionServletException("atencao.unidade_diferente_usuario");
			}
			
			if(!Util.isVazioOrNulo(form.getColecaoUnidadeOrganizacional()) 
				&& usuarioValidacao!=null
				&& usuarioValidacao.getUnidadeOrganizacional()!=null){
				
				for (UnidadeOrganizacional unidadeOrganizacional : form.getColecaoUnidadeOrganizacional()) {			
					if(usuarioValidacao.getUnidadeOrganizacional().getId().compareTo(unidadeOrganizacional.getId())!=0){
						throw new ActionServletException("atencao.unidade_diferente_usuario");
					}
				}		
			}
		}
		//FIM [FS0006] Verificar unidade do usu�rio
		
		//[FS0007]
		//Caso a coluna selecionada n�o possua o TBLA_ID correspondente a im�vel ou cliente 
		//(identificar a tabela que possui a coluna referenciada se esta n�o possui um TBCO_ID correspondente a
		//TBCO_NMCOLUNA=IMOV_ID ou TBCO_NMCOLUNA=CLIE_ID) o sistema exibir� a seguinte mensagem: 
		//"Dados n�o podem ser exibidos, pois n�o possuem rela��o com cliente ou im�vel"
		if(form.getIdColuna()!=null && form.getTipoRelatorioEscolhido().equals("2")){
			if(!Fachada.getInstancia().verificarRelacaoColuna(Integer.parseInt(form.getIdColuna()))){
				throw new ActionServletException("atencao.dados_nao_tem_relacao");
			}
		}
		
		
	}
}