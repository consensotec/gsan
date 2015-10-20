package gcom.gui.cadastro.imovel;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelClienteParaTransferirHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * Pesquisar Imóveis de acordo com o logradouro informado pelo usuário.
 * 
 * @author Davi Menezes
 * @date 03/08/2011
 */

public class PesquisarImovelLogradouroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		ActionForward retorno = actionMapping.findForward("transferirImovel");
		
		HttpSession session = httpServletRequest.getSession(false);
		
		TransferirImovelLogradouroActionForm form = (TransferirImovelLogradouroActionForm) actionForm;
		
		String idLogradouro = (String) form.getLogradouroImovelOrigemFiltro();
		String idbairro = (String) form.getBairroOrigemImovelFiltro();
		String cepId = (String) form.getCepOrigemImovelFiltro();
		
		//valida logradouro
		if(idLogradouro != null && !idLogradouro.equals("")){
			if(!Util.validarStringNumerica(idLogradouro)){
				throw new ActionServletException("atencao.inteiroNegativoZeroPositivo", "Logradouro Origem");
			}
		}
		
		if(form.getLogradouroImovelDestinoFiltro() == null || form.getLogradouroImovelDestinoFiltro().equals("")){
			session.removeAttribute("quantidadeBairros");
			session.removeAttribute("quantidadeCep");
			session.removeAttribute("colecaoBairros");
			session.removeAttribute("colecaoCep");
		}
		
		if(idLogradouro == null || idLogradouro.equals("")){
			session.removeAttribute("collImoveis");
			return retorno;
		}
		
		if(form.getDescricaoLogradouroImovelOrigemFiltro() == null || form.getDescricaoLogradouroImovelOrigemFiltro().equals("")){
			this.exibirLogradouroOrigem(form, httpServletRequest);
		}
		
		Cep cep = null;
		if(cepId != null && !cepId.equals("-1") && !cepId.trim().equals("")){
			FiltroCep filtroCep = new FiltroCep();
			filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CEPID, cepId));
			Collection ceps = this.getFachada().pesquisar(filtroCep, Cep.class.getName()); 
			cep = null;
			
			if(ceps != null ){				
				if(ceps.iterator().hasNext()){
					cep = (Cep) ceps.iterator().next();
				}
			}
		}
		Collection<ImovelClienteParaTransferirHelper> colecaoImovelClienteParaTransferirHelper = null;
		
		//IMOVEL ou AMBOS
		if(form.getTipoTransferencia().equals("1") || form.getTipoTransferencia().equals("3")){
			FiltroImovel filtro = new FiltroImovel(FiltroImovel.ID);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.UNIDADE_FEDERACAO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_BAIRRO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_CEP);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.ENDERECO_REFERENCIA);
			filtro.adicionarParametro(new ParametroSimples(FiltroImovel.LOGRADOURO_ID, idLogradouro));
			
			if(idbairro != null && !idbairro.equals("-1") && !idbairro.trim().equals("")){
				filtro.adicionarParametro(new ParametroSimples(FiltroImovel.BAIRRO_ID, idbairro));
			}
	
			if(cep != null){
				filtro.adicionarParametro(new ParametroSimples(FiltroImovel.CEP_CODIGO, cep.getCodigo()));
			}
				
			Collection<Imovel> imoveis = this.getFachada().pesquisar(filtro, Imovel.class.getName());
			
			colecaoImovelClienteParaTransferirHelper = new ArrayList<ImovelClienteParaTransferirHelper>();
			
			if(!Util.isVazioOrNulo(imoveis)){
				for(Imovel imovel : imoveis){
					ImovelClienteParaTransferirHelper imovelClienteParaTransferirHelper = new ImovelClienteParaTransferirHelper();
					imovelClienteParaTransferirHelper.setMatriculaImovel(imovel.getId().toString());
					imovelClienteParaTransferirHelper.setEndereco(imovel.getEnderecoFormatado());
					imovelClienteParaTransferirHelper.setUltimaAlteracao(imovel.getUltimaAlteracao());
					
					colecaoImovelClienteParaTransferirHelper.add(imovelClienteParaTransferirHelper);
				}
			}
		}
		
		//CLIENTE ou AMBOS
		if(form.getTipoTransferencia().equals("2") || form.getTipoTransferencia().equals("3")){
			FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.CLIENTE);
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.BAIRRO_MUNICIPIO_UNIDADE_FEDERACAO);
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.LOGRADOURO_BAIRRO);
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.LOGRADOURO_CEP);
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.LOGRADOURO_TIPO);
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.LOGRADOURO_TITULO);
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.ENDERECO_REFERENCIA);
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.CEP);
			
			filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.LOGRADOURO_ID, idLogradouro));
			filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA, ConstantesSistema.SIM));
			
			if(idbairro != null && !idbairro.equals("-1") && !idbairro.trim().equals("")){
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.BAIRRO_ID, idbairro));
			}
	
			if(cep != null){
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CEP_CODIGO, cep.getCodigo()));
			}
			
			Collection<ClienteEndereco> colecaoClienteEndereco = this.getFachada().pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
			
			if(colecaoImovelClienteParaTransferirHelper == null){
				colecaoImovelClienteParaTransferirHelper = new ArrayList<ImovelClienteParaTransferirHelper>();
			}
			
			if(!Util.isVazioOrNulo(colecaoClienteEndereco)){
				for(ClienteEndereco clienteEndereco : colecaoClienteEndereco){
					ImovelClienteParaTransferirHelper imovelClienteParaTransferirHelper = new ImovelClienteParaTransferirHelper();
					imovelClienteParaTransferirHelper.setCodigoCliente(clienteEndereco.getCliente().getId().toString());
					imovelClienteParaTransferirHelper.setEndereco(clienteEndereco.getEnderecoFormatado());
					imovelClienteParaTransferirHelper.setUltimaAlteracao(clienteEndereco.getUltimaAlteracao());
					
					colecaoImovelClienteParaTransferirHelper.add(imovelClienteParaTransferirHelper);
				}
			}
		}
		
		if (!Util.isVazioOrNulo(colecaoImovelClienteParaTransferirHelper)) {
        	session.setAttribute("colecaoImovelClienteParaTransferirHelper", colecaoImovelClienteParaTransferirHelper);
        }else {
        	if(!form.getDescricaoLogradouroImovelOrigemFiltro().equalsIgnoreCase("Logradouro Inexistente")){
        		throw new ActionServletException("atencao.pesquisa.nenhumresultado");
        	}
        }
        
		return retorno;
	}

	private void exibirLogradouroOrigem(TransferirImovelLogradouroActionForm form, HttpServletRequest httpServletRequest) {
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTIPO);
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTITULO);
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, Integer.parseInt(form.getLogradouroImovelOrigemFiltro())));
		
		Collection logradouroEncontrado = this.getFachada().pesquisar(filtroLogradouro, Logradouro.class.getName());

		Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(logradouroEncontrado);
		
		httpServletRequest.removeAttribute("idLogradouroNaoEncontrado");
		
		if(logradouro != null){
			String logradouroFormatado = "";

			if(logradouro.getLogradouroTipo() != null){
				logradouroFormatado = logradouro.getLogradouroTipo().getDescricaoAbreviada();	
			}
			
			if(logradouro.getLogradouroTitulo() != null){
				logradouroFormatado = logradouroFormatado + " " + logradouro.getLogradouroTitulo().getDescricaoAbreviada(); 
			}
				
			logradouroFormatado = logradouroFormatado + " " + logradouro.getNome();
			
			form.setLogradouroImovelOrigemFiltro(String.valueOf(logradouro.getId()));
			form.setDescricaoLogradouroImovelOrigemFiltro(logradouroFormatado);
			
			httpServletRequest.setAttribute("idLogradouroNaoEncontrado", "false");
		
		} else {
			form.setLogradouroImovelOrigemFiltro("");
			form.setDescricaoLogradouroImovelOrigemFiltro("Logradouro Inexistente");
			httpServletRequest.setAttribute("idLogradouroNaoEncontrado", "true");
		}
	}
}
