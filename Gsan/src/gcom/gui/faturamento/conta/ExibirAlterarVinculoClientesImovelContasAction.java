package gcom.gui.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.cliente.bean.ClienteImovelHelper;
import gcom.cadastro.cliente.bean.ComparatorClienteImovelHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
public class ExibirAlterarVinculoClientesImovelContasAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("alterarVinculoClientesImovelContas");
	
        ExibirAlterarVinculoClientesImovelContasActionForm form = (ExibirAlterarVinculoClientesImovelContasActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession();
        
        Fachada fachada = this.getFachada();
        
        String menu = httpServletRequest.getParameter("menu");
        if(menu != null && menu.equalsIgnoreCase("sim")){
        	form.reset();
        	
        	sessao.removeAttribute("teveAlteracao");
        	sessao.removeAttribute("colecaoClienteImovelHelper");
        	sessao.removeAttribute("colecaoClienteImovelHelperRemovidos");
        }
        
        String pesquisar = httpServletRequest.getParameter("pesquisar");
        if(pesquisar != null && !pesquisar.equals("")){
        	if(pesquisar.equals("1")){
        		sessao.removeAttribute("colecaoClienteImovelHelper");
            	sessao.removeAttribute("colecaoClienteImovelHelperRemovidos");
        		
        		this.pesquisarImovel(form, httpServletRequest, fachada);
        		this.pesquisarClienteUsuarioImovel(form, httpServletRequest, fachada);
        		this.pesquisarClienteImovel(form, sessao, fachada, httpServletRequest);
        		
        	}else if(pesquisar.equals("2")){
        		this.pesquisarCliente(form, httpServletRequest, fachada);
        	}
        }
        
        if(form.getIdImovel() != null && !form.getIdImovel().equals("") && 
        		(form.getInscricaoImovel() == null || form.getInscricaoImovel().equals(""))){
        	sessao.removeAttribute("colecaoClienteImovelHelper");
        	sessao.removeAttribute("colecaoClienteImovelHelperRemovido");
        	
        	this.pesquisarImovel(form, httpServletRequest, fachada);
    		this.pesquisarClienteUsuarioImovel(form, httpServletRequest, fachada);
    		this.pesquisarClienteImovel(form, sessao, fachada, httpServletRequest);
        }
        
        if(form.getIdCliente() != null && !form.getIdCliente().equals("") &&
        		(form.getNomeCliente() == null || form.getNomeCliente().equals(""))){
        	this.pesquisarCliente(form, httpServletRequest, fachada);
        }
        
        String adicionar = httpServletRequest.getParameter("adicionar");
        if(adicionar != null && adicionar.equals("sim")){
        	this.adicionarClienteImovel(form, sessao, httpServletRequest);
        	sessao.setAttribute("teveAlteracao", "true");
        }
        
        String remover = httpServletRequest.getParameter("remover");
        if(remover != null && remover.equals("sim")){
        	this.removerClienteImovel(form, sessao, httpServletRequest);
        	sessao.setAttribute("teveAlteracao", "true");
        }
        
        this.pesquisarClienteRelacaoTipo(httpServletRequest, fachada);
        this.pesquisarMotivoFimRelacao(httpServletRequest, fachada);
        
        return retorno;
	}
	
	public void pesquisarClienteRelacaoTipo(HttpServletRequest request, Fachada fachada){
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());
		if(!Util.isVazioOrNulo(colClienteRelacaoTipo)){
			request.setAttribute("colecaoClienteRelacaoTipo", colClienteRelacaoTipo);
		}else{
			request.removeAttribute("colecaoClienteRelacaoTipo");
		}
	}
	
	public void pesquisarMotivoFimRelacao(HttpServletRequest request, Fachada fachada){
		FiltroClienteImovelFimRelacaoMotivo filtroMotivoFimRelacao = new FiltroClienteImovelFimRelacaoMotivo();
		filtroMotivoFimRelacao.adicionarParametro(new ParametroSimples(FiltroClienteImovelFimRelacaoMotivo.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colMotivoFimRelacao = fachada.pesquisar(filtroMotivoFimRelacao, ClienteImovelFimRelacaoMotivo.class.getName());
		if(!Util.isVazioOrNulo(colMotivoFimRelacao)){
			request.setAttribute("colecaoMotivoFimRelacao", colMotivoFimRelacao);
		}else{
			request.removeAttribute("colecaoMotivoFimRelacao");
		}
	}
	
	public void pesquisarImovel(ExibirAlterarVinculoClientesImovelContasActionForm form, HttpServletRequest request, Fachada fachada){
		Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(form.getIdImovel()));
		if(imovel == null){
			form.setIdImovel("");
			form.setSituacaoAgua("");
			form.setSituacaoEsgoto("");
			form.setInscricaoImovel("Imóvel Inexistente");
			
			request.setAttribute("imovelNaoEncontrado", "true");
		}else{
			request.removeAttribute("imovelNaoEncontrado");
			
			this.validarRegistroAtendimento(Integer.parseInt(form.getIdImovel()) , fachada);
			
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
			form.setSituacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
			form.setSituacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
		}
	}
	
	public void pesquisarClienteUsuarioImovel(ExibirAlterarVinculoClientesImovelContasActionForm form, HttpServletRequest request, Fachada fachada){
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, form.getIdImovel()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		
		Collection<?> colClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		if(!Util.isVazioOrNulo(colClienteImovel)){
			ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colClienteImovel);
			form.setNomeClienteUsuarioAtual(clienteImovel.getCliente().getNome());
		}else{
			form.setNomeClienteUsuarioAtual("");
		}
	}
	
	public void pesquisarCliente(ExibirAlterarVinculoClientesImovelContasActionForm form, HttpServletRequest request, Fachada fachada){
		Cliente cliente = fachada.pesquisarClienteDigitado(Integer.parseInt(form.getIdCliente()));
		if(cliente == null){
			form.setIdCliente("");
			form.setNomeCliente("Cliente Inexistente");
			
			request.setAttribute("clienteNaoEncontrado", "true");
		}else{
			request.removeAttribute("clienteNaoEncontrado");
			
			form.setNomeCliente(cliente.getNome());
		}
	}
	
	public void pesquisarClienteImovel(ExibirAlterarVinculoClientesImovelContasActionForm form, HttpSession sessao, Fachada fachada, HttpServletRequest request){
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel(FiltroClienteImovel.DATA_INICIO_RELACAO);
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, form.getIdImovel()));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
		
		Collection<ClienteImovel> colClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		if(!Util.isVazioOrNulo(colClienteImovel)){
			this.montarClienteImovelHelper(colClienteImovel, sessao, request, "1");
		}
	}
	
	public void adicionarClienteImovel(ExibirAlterarVinculoClientesImovelContasActionForm form, HttpSession sessao, HttpServletRequest request){
		Collection<ClienteImovel> colecaoClienteImovel = new ArrayList<ClienteImovel>();
		
		ClienteImovel clienteImovel = new ClienteImovel();
		
		if(form.getIdImovel() != null && !form.getIdImovel().equals("")){
			clienteImovel.setImovel(new Imovel(Integer.parseInt(form.getIdImovel())));
		}
		
		if(form.getIdCliente() != null && !form.getIdCliente().equals("")){
			Cliente cliente = new Cliente(Integer.parseInt(form.getIdCliente()));
			cliente.setNome(form.getNomeCliente());
			clienteImovel.setCliente(cliente);
		}
		
		if(form.getTipoRelacao() != null && !form.getTipoRelacao().equals("") && !form.getTipoRelacao().equals("-1")){
			ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo(Integer.parseInt(form.getTipoRelacao()));
			
			if(form.getTipoRelacao().equals("1")){
				clienteRelacaoTipo.setDescricao("PROPRIETARIO");
			}else if(form.getTipoRelacao().equals("2")){
				clienteRelacaoTipo.setDescricao("USUARIO");
			}else if(form.getTipoRelacao().equals("3")){
				clienteRelacaoTipo.setDescricao("RESPONSAVEL");
			}
			
			clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);
		}
		
		if(form.getDataInicio() != null && !form.getDataInicio().equals("")){
			clienteImovel.setDataInicioRelacao(Util.converteStringParaDate(form.getDataInicio()));
		}
		
		if(form.getDataFinal() != null && !form.getDataFinal().equals("")){
			Integer dataInicio = Util.formatarDiaMesAnoComBarraParaAnoMesDia(form.getDataInicio());
			Integer dataFinal = Util.formatarDiaMesAnoComBarraParaAnoMesDia(form.getDataFinal());
			
			if(dataInicio > dataFinal){
				throw new ActionServletException("atencao.tarifasocial.data_final_relacao_menor_inicio_relacao");
			}
			
			clienteImovel.setDataFimRelacao(Util.converteStringParaDate(form.getDataFinal()));
		}
		
		if(form.getMotivoFim() != null && !form.getMotivoFim().equals("") && !form.getMotivoFim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
			clienteImovelFimRelacaoMotivo.setId(Integer.parseInt(form.getMotivoFim()));
			clienteImovel.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
		}
		
		if(form.getIndicadorNomeConta() != null && !form.getIndicadorNomeConta().equals("")){
			clienteImovel.setIndicadorNomeConta(Short.parseShort(form.getIndicadorNomeConta()));
		}
		
		colecaoClienteImovel.add(clienteImovel);
		
		form.limparCamposCliente();
		
		this.montarClienteImovelHelper(colecaoClienteImovel, sessao, request, "2");
		
	}
	
	public void removerClienteImovel(ExibirAlterarVinculoClientesImovelContasActionForm form, HttpSession sessao, HttpServletRequest request){
		Collection<ClienteImovelHelper> colecaoClienteImovelHelper = (Collection<ClienteImovelHelper>) sessao.getAttribute("colecaoClienteImovelHelper");
		Collection<ClienteImovelHelper> colecaoClienteImovelHelperRemovidos = (Collection<ClienteImovelHelper>) sessao.getAttribute("colecaoClienteImovelHelperRemovidos");
		
		this.manterClienteImovelHelper(colecaoClienteImovelHelper, request);
		
		if(colecaoClienteImovelHelperRemovidos == null){
			colecaoClienteImovelHelperRemovidos = new ArrayList<ClienteImovelHelper>();
		}
		
		if(form.getIdsRegistro() == null || form.getIdsRegistro().length < 1){
			throw new ActionServletException("atencao.selecione_registro_remover_cliente_imovel");
		}else{
			int cont = 1;
			Iterator<?> it = colecaoClienteImovelHelper.iterator();
			ClienteImovelHelper clienteImovelHelper = null;
			for(int i = 0; i < form.getIdsRegistro().length; i++){
				it = colecaoClienteImovelHelper.iterator();
				cont = 1;
				while(it.hasNext()){
					clienteImovelHelper = (ClienteImovelHelper) it.next();
					
					if(cont == Integer.parseInt(form.getIdsRegistro()[i])){
						colecaoClienteImovelHelperRemovidos.add(clienteImovelHelper);
						//colecaoClienteImovelHelper.remove(clienteImovelHelper);
						cont++;
						break;
					}
					cont++;
				}
			}
			
			ClienteImovelHelper clienteImovelRemovido = null;
			Iterator<?> itRem = colecaoClienteImovelHelperRemovidos.iterator();
			while(itRem.hasNext()){
				clienteImovelRemovido = (ClienteImovelHelper) itRem.next();
				
				for(ClienteImovelHelper clieImovelHelper : colecaoClienteImovelHelper){
					if(clienteImovelRemovido.equals(clieImovelHelper)){
						colecaoClienteImovelHelper.remove(clieImovelHelper);
						break;
					}
				}
			}
			
			sessao.setAttribute("colecaoClienteImovelHelper", colecaoClienteImovelHelper);
			sessao.setAttribute("colecaoClienteImovelHelperRemovidos", colecaoClienteImovelHelperRemovidos);
			
		}
	}
	
	public void montarClienteImovelHelper(Collection<ClienteImovel> colecaoClienteImovel, HttpSession sessao, 
			HttpServletRequest request, String tipo){
		
		Collection<ClienteImovelHelper> colecaoClienteImovelHelper = (Collection<ClienteImovelHelper>) 
				sessao.getAttribute("colecaoClienteImovelHelper");
		
		ClienteImovelHelper clienteImovelHelper = null;
		
		if(colecaoClienteImovelHelper == null){
			colecaoClienteImovelHelper = new ArrayList<ClienteImovelHelper>();
		}
		
		if(tipo != null && tipo.equals("2")){
			this.manterClienteImovelHelper(colecaoClienteImovelHelper, request);
		}
		
		Iterator<?> it = colecaoClienteImovel.iterator();
		while(it.hasNext()){
			clienteImovelHelper = new ClienteImovelHelper();
			
			ClienteImovel clienteImovel = (ClienteImovel) it.next();
			
			if(clienteImovel.getId() != null){
				clienteImovelHelper.setIdClienteImovel(String.valueOf(clienteImovel.getId()));
			}
			
			if(clienteImovel.getDataInicioRelacao() != null){
				clienteImovelHelper.setDataInicioRelacao(Util.formatarData(clienteImovel.getDataInicioRelacao()));
			}
			
			if(clienteImovel.getDataFimRelacao() != null){
				clienteImovelHelper.setDataFimRelacao(Util.formatarData(clienteImovel.getDataFimRelacao()));
			}
			
			if(clienteImovel.getImovel() != null){
				clienteImovelHelper.setIdImovel(String.valueOf(clienteImovel.getImovel().getId()));
			}
			
			if(clienteImovel.getCliente() != null){
				clienteImovelHelper.setIdCliente(String.valueOf(clienteImovel.getCliente().getId()));
				clienteImovelHelper.setNomeCliente(clienteImovel.getCliente().getDescricao());
			}
			
			if(clienteImovel.getClienteImovelFimRelacaoMotivo() != null){
				clienteImovelHelper.setIdClienteImovelFimRelacaoMotivo(String.valueOf(clienteImovel.
						getClienteImovelFimRelacaoMotivo().getId()));
			}
			
			if(clienteImovel.getIndicadorNomeConta() != null){
				clienteImovelHelper.setIndicadorNomeConta(String.valueOf(clienteImovel.getIndicadorNomeConta()));
			}
			
			if(clienteImovel.getClienteRelacaoTipo() != null){
				clienteImovelHelper.setIdClienteRelacaoTipo(String.valueOf(clienteImovel.getClienteRelacaoTipo().getId()));
				clienteImovelHelper.setDescricaoRelacaoTipo(clienteImovel.getClienteRelacaoTipo().getDescricao());
			}
			
			clienteImovelHelper.setIndicadorRevisao(ConstantesSistema.NAO.toString());
			
			if(!colecaoClienteImovelHelper.contains(clienteImovelHelper)){
				colecaoClienteImovelHelper.add(clienteImovelHelper);
			} else {
				throw new ActionServletException("atencao.existe_cliente_cadastrado", null, clienteImovelHelper.getNomeCliente());
			}
		}
		
		if(colecaoClienteImovelHelper.size() > 1){
			ArrayList<ClienteImovelHelper> array = new ArrayList<ClienteImovelHelper>(colecaoClienteImovelHelper);
			Collections.sort(array, new ComparatorClienteImovelHelper());
			colecaoClienteImovelHelper = new ArrayList<ClienteImovelHelper>(array);
		}
		
		sessao.setAttribute("colecaoClienteImovelHelper", colecaoClienteImovelHelper);
		
	}
	
	public void manterClienteImovelHelper(Collection<ClienteImovelHelper> colecaoClienteImovelHelper, 
			HttpServletRequest httpServletRequest){ 
		
		if(!Util.isVazioOrNulo(colecaoClienteImovelHelper)){
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
				
				ClienteImovelHelper clienteImovelHelper = (ClienteImovelHelper) it.next();
				clienteImovelHelper.setDataInicioRelacao(dataInicio);
				clienteImovelHelper.setDataFimRelacao(dataFinal);
				clienteImovelHelper.setIndicadorNomeConta(indicadorNomeConta);
			}
		}
	}
	
	public void validarRegistroAtendimento(Integer idImovel, Fachada fachada) {
		Integer idRegistroAtendimento = fachada.obterRAVinculo(idImovel);
		if(idRegistroAtendimento == null){
			throw new ActionServletException("atencao.nao_existe_ra_alterar_vinculo");
		}
	}
	
}
