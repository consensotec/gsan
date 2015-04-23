package gsan.gui.cadastro.imovel;

import java.util.Collection;
import java.util.Iterator;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteEndereco;
import gsan.cadastro.endereco.FiltroLogradouroBairro;
import gsan.cadastro.endereco.FiltroLogradouroCep;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.endereco.LogradouroCep;
import gsan.cadastro.imovel.Imovel;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que transfere os imóveis selecionados para o 
 * logradouro informado pelo usuário. As atualizações 
 * dos imóveis são registradas com os dados usuário logado.
 *
 * @author Davi Menezes
 * @date 04/08/2011
 */
public class TransferirImovelLogradouroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		TransferirImovelLogradouroActionForm form = (TransferirImovelLogradouroActionForm) actionForm;
		
		String [] ids = form.getIdsRegistro();
		
		String idBairro = null, idCep = null;
		
		Integer qtdBairros = 0, qtdCep = 0;
		
		if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }
		
		HttpSession session = httpServletRequest.getSession();
		
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		if(session.getAttribute("quantidadeBairros") != null){
			qtdBairros = (Integer) session.getAttribute("quantidadeBairros");
		}
		
		if(session.getAttribute("quantidadeCep") != null){
			qtdCep = (Integer) session.getAttribute("quantidadeCep");
		}
		
		if(form.getLogradouroImovelOrigemFiltro().equals("")){
			throw new ActionServletException("atencao.logradouro_nao_informado");
		}
		
		if(form.getLogradouroImovelDestinoFiltro().equals("")){
			throw new ActionServletException("atencao.logradouro_nao_informado");
		}else if(!Util.validarStringNumerica(form.getLogradouroImovelDestinoFiltro())){
			throw new ActionServletException("atencao.inteiroNegativoZeroPositivo", "Logradouro Destino");
		}
		
		if(form.getBairroImovelFiltro().equals("-1")){
			throw new ActionServletException("atencao.bairro_nao_informado_destino");
		}
		
		if(form.getCepImovelFiltro().equals("-1")){
			throw new ActionServletException("atencao.cep_nao_informado_destino");
		}
		
		//[FS0002] - Validar logradouro destino
		if(form.getLogradouroImovelOrigemFiltro().equals(form.getLogradouroImovelDestinoFiltro()) 
			&& form.getBairroOrigemImovelFiltro().equals(form.getBairroImovelFiltro())
			&& form.getCepOrigemImovelFiltro().equals(form.getCepImovelFiltro())){
			throw new ActionServletException("atencao.logradouros_iguais");
		}
		
		LogradouroBairro logradouroBairro = null;
		LogradouroCep logradouroCep = null;
		
		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO);
		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, form.getLogradouroImovelDestinoFiltro()));
		
		FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
		filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.CEP);
		filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.LOGRADOURO);
		filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, form.getLogradouroImovelDestinoFiltro()));
		
		if(Integer.parseInt(form.getBairroImovelFiltro()) != -1){
			idBairro = form.getBairroImovelFiltro();
			filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO, idBairro));
		}
		
		if(Integer.parseInt(form.getCepImovelFiltro()) != -1){
			idCep = form.getCepImovelFiltro();
			filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_CEP, idCep));
		}
		
		Collection colecaoLogradouroBairro = getFachada().pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
		
		Iterator iteratorBairro = colecaoLogradouroBairro.iterator();
		
		if(iteratorBairro.hasNext()){
			logradouroBairro = (LogradouroBairro) iteratorBairro.next();
		}
		
		Collection colecaoLogradouroCep = getFachada().pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());
		
		Iterator iteratorCep = colecaoLogradouroCep.iterator();
		
		if(iteratorCep.hasNext()){
			logradouroCep = (LogradouroCep) iteratorCep.next();
		}
			
        //Atualiza os imóveis selecionados
		for (int i = 0; i < ids.length; i++) {
			String dadosImovel = ids[i];
			String[] idUltimaAlteracao = dadosImovel.split("-");
			String id 	= idUltimaAlteracao[0].trim();
			String tipo = idUltimaAlteracao[2].trim();
			
			try{
				if(tipo.equals("I")){
					Imovel imovel = getFachada().pesquisarImovel(Integer.parseInt(id));
					imovel.setLogradouroBairro(logradouroBairro);
					imovel.setLogradouroCep(logradouroCep);
					
					getFachada().transferirImovel(imovel, usuarioLogado);
				}else if(tipo.equals("C")){
					ClienteEndereco clienteEndereco = getFachada().pesquisarClienteEnderecoPagamento(Integer.parseInt(id));
					clienteEndereco.setLogradouroBairro(logradouroBairro);
					clienteEndereco.setLogradouroCep(logradouroCep);
					
					getFachada().transferirClienteEndereco(clienteEndereco, usuarioLogado);
				}
			}catch (Exception ex){
				throw new ActionServletException("erro.sistema");
			}
			
        }
        
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest, ids.length + " Imóvel(is)/Cliente(s) transferido(s) com sucesso.", 
            		"Realizar outra Transferência de Imóvel/Cliente", "exibirTransferirImovelLogradouroAction.do");
        }
        
        //Remove os dados do formulário
        form.setBairroImovelFiltro("");
		form.setCepImovelFiltro("");
		form.setDescricaoLogradouroImovelDestinoFiltro("");
		form.setDescricaoLogradouroImovelOrigemFiltro("");
		form.setLogradouroImovelDestinoFiltro("");
		form.setLogradouroImovelOrigemFiltro("");
		form.setIdsRegistro(null);
        
		//Remove os dados da sessão
        session.removeAttribute("quantidadeBairros");
		session.removeAttribute("quantidadeCep");
		session.removeAttribute("colecaoImovelClienteParaTransferirHelper");
		session.removeAttribute("colecaoBairros");
		session.removeAttribute("colecaoCep");
		
		return retorno;
	}
	
}
