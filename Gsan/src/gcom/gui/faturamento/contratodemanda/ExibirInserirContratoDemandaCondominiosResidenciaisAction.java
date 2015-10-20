package gcom.gui.faturamento.contratodemanda;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.contratodemanda.ContratoDemandaFaixaConsumo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.PrintWriter;
import java.util.List;

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
public class ExibirInserirContratoDemandaCondominiosResidenciaisAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward retorno = mapping.findForward("exibirInserirContratoDemandaCondominiosResidenciaisAction");
		InserirContratoDemandaCondominiosResidenciaisActionForm form = (InserirContratoDemandaCondominiosResidenciaisActionForm) formulario;
		
		String calcularDesconto  = request.getParameter("calcularDesconto");
		String limpar = request.getParameter("limpar");
		if(Util.verificarNaoVazio(limpar) && limpar.equalsIgnoreCase("ok")){
			form.setNumeroContrato("");
			form.setMatriculaImovel("");
			form.setDataFimContrato("");
			form.setDataInicioContrato("");
			form.setDemandaMinima("");
			form.setIdCliente("");
			form.setInscricaoImovel("");
			form.setNomeCliente("");
			form.setDesconto("");
			form.setTipoRelacaoCliente(null);
		}else if(Util.verificarNaoVazio(calcularDesconto) && calcularDesconto.equalsIgnoreCase(calcularDesconto)){
			ContratoDemandaFaixaConsumo consumo = this.getFachada().pesquisarFaixaConsumo(Integer.valueOf(form.getDemandaMinima()));
			if(consumo != null){
				form.setDesconto(consumo.getPercentualDesconto().toString()+"%");
				PrintWriter print = response.getWriter();
				print.print(consumo.getPercentualDesconto().toString()+"%");
				retorno = null;
			}else{
				form.setDesconto("");
				retorno = null;
			}
		}else{
			if(Util.verificarIdNaoVazio(form.getMatriculaImovel())){
				Imovel imovel = this.getFachada().pesquisarImovel(Integer.valueOf(form.getMatriculaImovel()));
				if(imovel != null){	
					if(this.getFachada().validarImovelContratoDemandaCondominios(form.getMatriculaImovel())){
						form.setInscricaoImovel(imovel.getInscricaoFormatada());
					}
				}else{
					form.setInscricaoImovel("IMOVEL INEXISTENTE");
					request.setAttribute("codigoImovelNaoEncontrado", true);
				}
			}
			
			if(Util.verificarIdNaoVazio(form.getIdCliente())){
				this.pesquisarCliente(form.getIdCliente(), form, request);
			}
		}
		return retorno;
	}
	
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
	 * [FS0004] – Verificar Cliente
	 * 
	 * @author Diogo Peixoto
	 * @since 20/09/2011
	 * 
	 * @param idCliente
	 * @param form
	 */
	private void pesquisarCliente(String idCliente, InserirContratoDemandaCondominiosResidenciaisActionForm form, HttpServletRequest request){
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));

		List<Cliente> clienteEncontrado = (List<Cliente>) this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		if(!Util.isVazioOrNulo(clienteEncontrado)){
			Cliente cliente = clienteEncontrado.get(0);
			//[FS0006] – Validar Cliente
			if(cliente.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)){
				throw new ActionServletException("atencao.contrato_demanda_cliente_inativo");
			}else if(cliente.getCpf() == null){
				throw new ActionServletException("atencao.contrato_demanda_cliente_sem_cpf");
			}else{
				form.setIdCliente(clienteEncontrado.get(0).getId().toString());
				form.setNomeCliente(clienteEncontrado.get(0).getNome());
			}
		}else{
			request.setAttribute("codigoClienteNaoEncontrado", true);
			form.setNomeCliente("CLIENTE INEXISTENTE");
		}
	}
}