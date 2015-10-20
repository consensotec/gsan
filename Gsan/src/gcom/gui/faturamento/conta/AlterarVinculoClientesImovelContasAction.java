package gcom.gui.faturamento.conta;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.bean.ClienteImovelHelper;
import gcom.cadastro.cliente.bean.ComparatorClienteImovelHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1366] - Alterar Vínculo Clientes Imovel Contas
 * 
 * @author Rafael Corrêa
 * @date 24/04/2015
 *
 */
public class AlterarVinculoClientesImovelContasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");
		
        //ExibirAlterarVinculoClientesImovelContasActionForm form = (ExibirAlterarVinculoClientesImovelContasActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession();
        
        Fachada fachada = this.getFachada();
        
        Collection<ClienteImovelHelper> colecaoClienteImovelHelper = (Collection<ClienteImovelHelper>) sessao.getAttribute("colecaoClienteImovelHelper");
		Collection<ClienteImovelHelper> colecaoClienteImovelHelperRemovidos = (Collection<ClienteImovelHelper>) sessao.getAttribute("colecaoClienteImovelHelperRemovidos");
        
		if(Util.isVazioOrNulo(colecaoClienteImovelHelper)){
			throw new ActionServletException("atencao.imovel_sem_vinculos");
		}
		
		ClienteImovelHelper clienteImovelHelper = (ClienteImovelHelper) Util.retonarObjetoDeColecao(colecaoClienteImovelHelper);
		
		Integer idImovel = Integer.parseInt(clienteImovelHelper.getIdImovel());
		
		Imovel imovel = fachada.pesquisarImovel(idImovel);
		if(imovel == null){
			throw new ActionServletException("atencao.imovel_inexistente");
		}
		
		//Usuario Logado
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Verificar se teve alteração
		String alteracao = (String) sessao.getAttribute("teveAlteracao");
		if(alteracao == null || !alteracao.equals("true")){
			throw new ActionServletException("atencao.nao_houve_nenhuma_alteracao_vinculo");
		}
		
		String dataInicioRelacao = null;
		
		Date dataInicio = fachada.pesquisarMenorDataClienteImovel(idImovel);
		
		if(dataInicio != null){
			dataInicioRelacao = Util.formatarData(dataInicio);
		}
		
		this.montarClienteImovelHelper(colecaoClienteImovelHelper, httpServletRequest);
		
		if(colecaoClienteImovelHelper.size() > 1){
			ArrayList<ClienteImovelHelper> array = new ArrayList<ClienteImovelHelper>(colecaoClienteImovelHelper);
			Collections.sort(array, new ComparatorClienteImovelHelper());
			colecaoClienteImovelHelper = new ArrayList<ClienteImovelHelper>(array);
		}
		
		//this.ordenarColecaoClienteImovelHelper(colecaoClienteImovelHelper);
		
		this.validarPeriodoInicial(dataInicioRelacao, colecaoClienteImovelHelper);
		this.validarClienteImovelHelper(colecaoClienteImovelHelper, dataInicioRelacao);
		this.validarIndicadorNomeConta(dataInicioRelacao, colecaoClienteImovelHelper);
		this.validarRegistroAtendimento(idImovel, fachada);
		
		fachada.alterarVinculoClienteImovel(colecaoClienteImovelHelper, colecaoClienteImovelHelperRemovidos, idImovel, usuarioLogado);
		
		this.montarPaginaSucesso(httpServletRequest, "Clientes Vinculados com Sucesso.", 
        	"Alterar outro Vínculo", "exibirAlterarVinculoClientesImovelContasAction.do?menu=sim");
        
        return retorno;
	
	}
	
	public void validarPeriodoInicial(String dataInicio, Collection<ClienteImovelHelper> colecaoClienteImovel){
		ClienteImovelHelper clienteImovelHelper = null;
		
		if(dataInicio == null){
			dataInicio = Util.formatarData(new Date());
		}
		
		Iterator<?> iterator = colecaoClienteImovel.iterator();
		while(iterator.hasNext()){
			clienteImovelHelper = (ClienteImovelHelper) iterator.next();
			
			if(clienteImovelHelper.getDataInicioRelacao() != null && !clienteImovelHelper.getDataInicioRelacao().equals("")){
				if(!dataInicio.equals(clienteImovelHelper.getDataInicioRelacao())){
					throw new ActionServletException("atencao.periodo_vinculo_cliente_imovel_invalido", null, dataInicio);
				}
				break;
			}
		}
	}
	
	public void validarClienteImovelHelper(Collection<ClienteImovelHelper> colecaoClienteImovelHelper, String dataInicial){
		ClienteImovelHelper clienteImovelHelper = null;
		
		if(dataInicial == null){
			dataInicial = Util.formatarData(new Date());
		}
		
		String dataFimAnteriorU = dataInicial;
		String dataFimAnteriorP = dataInicial;
		String dataFimAnteriorR = dataInicial;
		String idClienteAnterior = "";
		
		Iterator<?> iterator = colecaoClienteImovelHelper.iterator();
		while(iterator.hasNext()){
			clienteImovelHelper = (ClienteImovelHelper) iterator.next();
			
			if(clienteImovelHelper.getIdClienteRelacaoTipo().equals(ClienteRelacaoTipo.USUARIO.toString())){
				if(dataFimAnteriorU == null){
					throw new ActionServletException("atencao.existe_duplicidade_cliente_usuario_imovel");
				}else{
					if(!dataFimAnteriorU.equals(clienteImovelHelper.getDataInicioRelacao())){
						if ( Util.compararData(Util.converteStringParaDate(dataFimAnteriorU), Util.converteStringParaDate(clienteImovelHelper.getDataInicioRelacao() )) > 0) {
							throw new ActionServletException("atencao.existe_duplicidade_cliente_usuario_imovel");
						} else {
							throw new ActionServletException("atencao.nao_existe_cliente_usuario_imovel");
						}
					}
				}
				dataFimAnteriorU = clienteImovelHelper.getDataFimRelacao();
			}else if(clienteImovelHelper.getIdClienteRelacaoTipo().equals(ClienteRelacaoTipo.RESPONSAVEL.toString())){
				Integer dataInicioAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(clienteImovelHelper.getDataInicioRelacao());
				
				if (dataFimAnteriorR == null) {
					throw new ActionServletException("atencao.existe_duplicidade_cliente_responsavel_imovel");
				} else {
					Integer dataFimAnterior = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataFimAnteriorR);
					
					if(dataFimAnterior > dataInicioAtual){
						throw new ActionServletException("atencao.existe_duplicidade_cliente_responsavel_imovel");
					}
				}
				dataFimAnteriorR = clienteImovelHelper.getDataFimRelacao();
			}else if(clienteImovelHelper.getIdClienteRelacaoTipo().equals(ClienteRelacaoTipo.PROPRIETARIO.toString())){
				Integer dataInicioAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(clienteImovelHelper.getDataInicioRelacao());
				
				if(dataFimAnteriorP != null){
					Integer dataFimAnterior = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataFimAnteriorP);
					
					if(dataFimAnterior > dataInicioAtual && clienteImovelHelper.getIdCliente().equals(idClienteAnterior)){
						throw new ActionServletException("atencao.cliente_proprietario_invalido");
					}
				}else{
					if(clienteImovelHelper.getIdCliente().equals(idClienteAnterior)){
						throw new ActionServletException("atencao.cliente_proprietario_invalido");
					}
				}
				idClienteAnterior = clienteImovelHelper.getIdCliente();
				dataFimAnteriorP = clienteImovelHelper.getDataFimRelacao();
			}
		}
		
		if(dataFimAnteriorU != null){
			throw new ActionServletException("atencao.nao_existe_cliente_usuario_imovel");
		}
		
	}
	
	public void validarIndicadorNomeConta(String dataInicio, Collection<ClienteImovelHelper> colecaoClienteImovel){
		ClienteImovelHelper clienteImovelHelper = null;
		
		if(dataInicio == null){
			dataInicio = Util.formatarData(new Date());
		}
		
		Integer dataFim;
		Integer dataIni;
		String dataFinalAnterior = dataInicio;
		
		Iterator<?> iterator = colecaoClienteImovel.iterator();
		while(iterator.hasNext()){
			clienteImovelHelper = (ClienteImovelHelper) iterator.next();
			
			if(clienteImovelHelper.getIndicadorNomeConta().equals("1")){
				if(dataFinalAnterior == null){
					throw new ActionServletException("atencao.clientes_indicador_nome_conta");
				}else{
					dataFim = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataFinalAnterior);
					dataIni = Util.formatarDiaMesAnoComBarraParaAnoMesDia(clienteImovelHelper.getDataInicioRelacao());
					
					if(!dataFim.equals(dataIni)){
						throw new ActionServletException("atencao.existe_intervalo_cliente_indicador");
					}
					
				}
				dataFinalAnterior = clienteImovelHelper.getDataFimRelacao();
			}
		}
		
		if(dataFinalAnterior != null){
			throw new ActionServletException("atencao.existe_intervalo_cliente_indicador_nome_conta");
		}
	}
	
	public void validarRegistroAtendimento(Integer idImovel, Fachada fachada) {
		Integer idRegistroAtendimento = fachada.obterRAVinculo(idImovel);
		if(idRegistroAtendimento == null){
			throw new ActionServletException("atencao.nao_existe_ra_alterar_vinculo");
		}
	}
	
	public void montarClienteImovelHelper(Collection<ClienteImovelHelper> colecaoClienteImovelHelper, HttpServletRequest httpServletRequest){ 
		Iterator<?> it = colecaoClienteImovelHelper.iterator();
		
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		
		Integer count = new Integer(0);
		while(it.hasNext()){
			count++;
			
			String dataInicio = (requestMap.get("dataInicioRelacao_"+ count))[0];
			if(Util.validarDiaMesAno(dataInicio)){
				throw new ActionServletException("atencao.data_invalida", null, "Inícial");
			}
			
			String dataFinal = (requestMap.get("dataFimRelacao_"+ count))[0];
			if(dataFinal != null && !dataFinal.equals("")){
				if(Util.validarDiaMesAno(dataFinal)){
					throw new ActionServletException("atencao.data_invalida", null, "Final");
				}
			}else{
				dataFinal = null;
			}
			
			String indicadorNomeConta = "2";
			try{
				indicadorNomeConta = (requestMap.get("indicadorNomeConta_"+ count))[0];
				indicadorNomeConta = "1";
			}catch (Exception e) {
				indicadorNomeConta = "2";
			}
			
			String indicadorRevisao = "2";
			try{
				indicadorRevisao = (requestMap.get("indicadorRevisao_"+ count))[0];
				indicadorRevisao = "1";
			}catch (Exception e) {
				indicadorRevisao = "2";
			}
			
			ClienteImovelHelper clienteImovelHelper = (ClienteImovelHelper) it.next();
			clienteImovelHelper.setDataInicioRelacao(dataInicio);
			clienteImovelHelper.setDataFimRelacao(dataFinal);
			clienteImovelHelper.setIndicadorNomeConta(indicadorNomeConta);
			clienteImovelHelper.setIndicadorRevisao(indicadorRevisao);
		}
	}

/*
	public void ordenarColecaoClienteImovelHelper(Collection<ClienteImovelHelper> colecaoClienteImovelHelper){
		ArrayList<ClienteImovelHelper> array = new ArrayList<ClienteImovelHelper>(colecaoClienteImovelHelper);
		
		Collections.sort((ArrayList) array, new Comparator() {
			public int compare(Object a, Object b) {
				String dataInicial1 = ((ClienteImovelHelper) a).getDataInicioRelacao();
				String dataInicial2 = ((ClienteImovelHelper) b).getDataInicioRelacao();
				
				if(dataInicial1.compareTo(dataInicial2) == 0){
					String idClienteRelacaoTipo1 = ((ClienteImovelHelper) a).getIdClienteRelacaoTipo();
					String idClienteRelacaoTipo2 = ((ClienteImovelHelper) b).getIdClienteRelacaoTipo();
					
					if(idClienteRelacaoTipo1.compareTo(idClienteRelacaoTipo2) == 0){
						String idCliente1 = ((ClienteImovelHelper) a).getIdCliente();
						String idCliente2 = ((ClienteImovelHelper) b).getIdCliente();
						
						return idCliente1.compareTo(idCliente2);
					}else{
						return idClienteRelacaoTipo1.compareTo(idClienteRelacaoTipo2);
					}
				}else{
					return dataInicial1.compareTo(dataInicial2);
				}
			}

		});
		
		colecaoClienteImovelHelper = new ArrayList<ClienteImovelHelper>(array);
	}
*/
	
}
