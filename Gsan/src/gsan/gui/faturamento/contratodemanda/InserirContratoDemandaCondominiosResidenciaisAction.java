package gsan.gui.faturamento.contratodemanda;

import java.util.Collection;

import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1226] Incluir Contrato de Demanda
 * 
 * @author Diogo Peixoto
 * @since 19/09/2011
 *
 */
public class InserirContratoDemandaCondominiosResidenciaisAction extends GcomAction {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("telaSucesso");
		InserirContratoDemandaCondominiosResidenciaisActionForm form = (InserirContratoDemandaCondominiosResidenciaisActionForm) formulario;
		
		String numeroContrato = form.getNumeroContrato().toString().toUpperCase();
		if(!Util.verificarNaoVazio(numeroContrato)){
			throw new ActionServletException("atencao.contrato_demanda_numero_nao_informado");
		}
		
		String matriculaImovel = form.getMatriculaImovel();
		if(!Util.verificarNaoVazio(matriculaImovel)){
			throw new ActionServletException("atencao.contrato_demanda_matricula_imovel_nao_informada");
		}
		/*
		 * UC1226 - FS0002
		 * Verifica se o imóvel não é um condominio ou se o imóvel 
		 * está associado a algum condomínio.
		 */
		Imovel imov = this.getFachada().pesquisarImovel(new Integer(matriculaImovel));
		
		
		if(imov != null){
			if(imov.getIndicadorImovelCondominio().intValue() == 1){
				throw new ActionServletException("atencao.contrato_demanda_imovel_condominio");
			}
			if(imov.getImovelCondominio() != null){
				throw new ActionServletException("atencao.contrato_demanda_imovel_vinculado_condominio");
			}
		}
		
		
		String dataInicio = form.getDataInicioContrato();
		if(!Util.verificarNaoVazio(dataInicio)){
			throw new ActionServletException("atencao.contrato_demanda_data_inicio_nao_informada");
		}
		
		String dataTermino = form.getDataFimContrato();
		if(!Util.verificarNaoVazio(dataTermino)){
			throw new ActionServletException("atencao.contrato_demanda_data_termino_nao_informada");
		}
		
		String idCliente = form.getIdCliente();
		if(!Util.verificarIdNaoVazio(idCliente)){
			throw new ActionServletException("atencao.contrato_demanda_cliente_nao_informado");
		}
		
		String tipoRelacao = form.getTipoRelacaoCliente();
		if(tipoRelacao == null || (!tipoRelacao.equals("1") && !tipoRelacao.equals("2"))){
			throw new ActionServletException("atencao.contrato_demanda_tipo_relacao_nao_informado");
		}
		
		String demandaMinima = form.getDemandaMinima();
		if(!Util.verificarNaoVazio(demandaMinima)){
			throw new ActionServletException("atencao.contrato_demanda_minima_nao_informado");
		}
		
		Integer id = (Integer) this.getFachada().inserirContratoDemandaCondominiosResidenciais(numeroContrato, matriculaImovel, dataInicio, dataTermino, 
			idCliente, tipoRelacao, demandaMinima, this.getUsuarioLogado(request));

		montarPaginaSucesso(request, "Contrato Demanda Condomínios Residenciais "+ numeroContrato + " inserido com sucesso.",
			"Inserir outro Contrato de Demanda Condomínios Residenciais",
			"exibirInserirContratoDemandaCondominiosResidenciaisAction.do?limpar=OK",
			"exibirAtualizarContratoDemandaCondominiosResidenciaisAction.do?idContrato=" + numeroContrato,
			"Atualizar Contrato Demanda Condomínios Residenciais");
		
		return retorno;
	}
}