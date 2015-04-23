package gsan.gui.micromedicao;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.funcionario.FiltroFuncionario;
import gsan.cadastro.funcionario.Funcionario;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.Leiturista;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CadastrarLeituristaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		CadastrarLeituristaActionForm form = (CadastrarLeituristaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		if(form.getDdd()!=null && !form.getDdd().trim().equals("")
				&& form.getFone()!=null && !form.getFone().trim().equals("")
				&& form.getImei()!=null && !form.getImei().trim().equals("")){
			
			Leiturista leiturista = new Leiturista();
			
			boolean escolheuCliente = false;
			
			if(form.getClienteID()!=null && !form.getClienteID().trim().equals("")){
				
				if(Util.validarNumeroMaiorQueZERO(form.getClienteID())){
					FiltroCliente filtroCliente = new FiltroCliente();
					filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getClienteID()));
					
					Collection colecao = fachada.pesquisar(filtroCliente, Cliente.class.getName());
					
					if(colecao!=null && !colecao.isEmpty()){
						escolheuCliente = true;
						leiturista.setCliente((Cliente)colecao.iterator().next());
					}
					
				}else{
					//exception
					 throw new ActionServletException("atencao.cliente.inexistente");
				}
				
				
			}
			if(!escolheuCliente && form.getFuncionarioID()!=null 
					&& !form.getFuncionarioID().trim().equals("")){
				if(Util.validarNumeroMaiorQueZERO(form.getFuncionarioID())){
					
					FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
					filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, form.getFuncionarioID()));
					
					Collection colecao = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
					
					if(colecao!=null && !colecao.isEmpty()){
						escolheuCliente = true;
						leiturista.setFuncionario((Funcionario)colecao.iterator().next());
					}
					
				}else{
					//Exception
					throw new ActionServletException("pesquisa.funcionario.inexistente");
				}
				
			}
			
			if(!escolheuCliente){
				//ERRO, selecione um cliente ou um funcionario 
				throw new ActionServletException("atencao.cliente_ou_funcionario");
			}
			
			leiturista.setCodigoDDD(form.getDdd());			
			leiturista.setNumeroFone(form.getFone());
			
			if(Util.validarStringNumerica(form.getImei())){
				leiturista.setNumeroImei(new Long(form.getImei()));
			}else{
				//n atencao.imei
				throw new ActionServletException("atencao.imei");
			}
			
			if(form.getEmpresaID()!=null && !form.getEmpresaID().trim().equals("")
					&& !form.getEmpresaID().trim().equals("-1")){
				
				Empresa empresa = new Empresa();
				empresa.setId(new Integer(form.getEmpresaID()));
				
				leiturista.setEmpresa(empresa);
				
			}else{
				//exception 
				throw new ActionServletException("atencao.usuario.empresa.nula");
			}
			
			
			leiturista.setIndicadorUso(new Short("1"));
			
			leiturista.setUltimaAlteracao(new Date());
				
			Integer id = fachada.maximoIdLeiturista();
			
			if(id == null){
				id = new Integer(1);
			}else{
				int temp = id.intValue();
				id = null;
				id = new Integer(temp+1);
			}
			
			leiturista.setId(id);
			
			fachada.inserir(leiturista);
			
			montarPaginaSucesso(httpServletRequest, "Leiturista Cadastrado com Sucesso.",
					"Cadastrar outro Leiturista",
					"exibirCadastrarLeituristaAction.do?menu=sim");
			
			
			
		}
		
		
		
		
		
		return retorno;
	}

}
