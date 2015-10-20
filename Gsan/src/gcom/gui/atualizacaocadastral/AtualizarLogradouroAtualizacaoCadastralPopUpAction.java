package gcom.gui.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atualizacaocadastral.CepAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroBairroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroCepAtlzCadDM;
import gcom.atualizacaocadastral.bean.AtualizacaoCadastralLogradouroHelper;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class AtualizarLogradouroAtualizacaoCadastralPopUpAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirInserirNovosLogradourosAtualizacaoCadastralAction");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarLogradouroAtualizacaoCadastralPopUpActionForm logradouroAtlzCadActionForm = (AtualizarLogradouroAtualizacaoCadastralPopUpActionForm) actionForm;
		
		ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = (ArrayList<AtualizacaoCadastralLogradouroHelper>) sessao.getAttribute("colecaoLogradouroHelper");
		
		//Pesquisa o logradouro a ser atualizado
		AtualizacaoCadastralLogradouroHelper logradouroHelper =	pesquisarLogradouroAtlzCad(colecaoLogradouroHelper, logradouroAtlzCadActionForm.getCodigoLogradouro());
		
		if(logradouroHelper != null){
			LogradouroAtlzCadDM  logradouroAtlzCad = logradouroHelper.getLogradouroAtlzCad();
			
			//Logradouro Tipo
			LogradouroTipo logradouroTipo = pesquisarLogradouroTipo(logradouroAtlzCadActionForm.getIdTipo().toString(), sessao);		
			logradouroAtlzCad.setLogradouroTipo(logradouroTipo);	
			
			//Logradouro Titulo
			LogradouroTitulo logradouroTitulo = pesquisarLogradouroTitulo(logradouroAtlzCadActionForm.getIdTitulo().toString(), sessao);
			logradouroAtlzCad.setLogradouroTitulo(logradouroTitulo);
			
			//Nome
			logradouroAtlzCad.setNome(logradouroAtlzCadActionForm.getNome());
			
			//Nome Popular
			logradouroAtlzCad.setNomePopular(logradouroAtlzCadActionForm.getNomePopular());
			
			//Loteamento
			logradouroAtlzCad.setNomeLoteamento(logradouroAtlzCadActionForm.getNomeLoteamento());
			
			//Municipio
			Municipio municipio = pesquisarMunicipio(logradouroAtlzCadActionForm.getIdMunicipio(), fachada);
			logradouroAtlzCad.setMunicipio(municipio);
			
			//Logradouro Bairro
    		List<Bairro> colecaoBairrosSelecionadosUsuario = (List<Bairro>)	sessao.getAttribute("colecaoBairrosSelecionadosUsuario");
			ArrayList<LogradouroBairroAtlzCadDM> colecaoLogradouroBairroAtlzCad = new ArrayList<LogradouroBairroAtlzCadDM>();
			String bairros = "";
			if(colecaoBairrosSelecionadosUsuario != null){
				//converte de logradouroBairro para LogradouroBairroAtlzCad
				for(Bairro bairro : colecaoBairrosSelecionadosUsuario){
					LogradouroBairroAtlzCadDM logradouroBairroAtlzCad = new LogradouroBairroAtlzCadDM();
					logradouroBairroAtlzCad.setBairro(bairro);
					logradouroBairroAtlzCad.setLogradouroAtlzCadDM(logradouroAtlzCad);
					
					colecaoLogradouroBairroAtlzCad.add(logradouroBairroAtlzCad);
					
					//monta a String com os nomes dos bairros para exibir no grid
					bairros = bairros + bairro.getNome() + "<br>";
				}
			}
			logradouroHelper.setBairros(bairros);
			logradouroHelper.setColecaoLogardouroBairroAtlzCad(colecaoLogradouroBairroAtlzCad);
			
			//Ceps
			List<Cep> colecaoCepSelecionadosUsuario = (List<Cep>) sessao.getAttribute("colecaoCepSelecionadosUsuario");
			ArrayList<LogradouroCepAtlzCadDM> colecaoLogradouroCepAtlzCad = new ArrayList<LogradouroCepAtlzCadDM>();
			
			if(colecaoCepSelecionadosUsuario != null){
				//converte de logradouroCep para LogradouroCepAtlzCad
				for(Cep cep : colecaoCepSelecionadosUsuario){
					LogradouroCepAtlzCadDM logradouroCepAtlzCad = new LogradouroCepAtlzCadDM();
						
					CepAtlzCadDM cepAtlzCad = new CepAtlzCadDM();
					
					if(cep.getIdCepAtualizacaoCadastral() ==  null){
						cepAtlzCad.setId(cep.getCepId().toString());
					}else{
						cepAtlzCad.setId(cep.getIdCepAtualizacaoCadastral().toString());
					}
					
					cepAtlzCad.setCodigoCep(cep.getCodigo());
					logradouroCepAtlzCad.setCepAtlzCad(cepAtlzCad);
					
					logradouroCepAtlzCad.setLogradouroAtlzCad(logradouroAtlzCad);						
					
					colecaoLogradouroCepAtlzCad.add(logradouroCepAtlzCad);
				}
			}
			
			logradouroHelper.setColecaoLogradouroCepAtlzCad(colecaoLogradouroCepAtlzCad);	
		}
		
		httpServletRequest.setAttribute("fechar","ok");
		
		return retorno;
	}
	
	/**
	 * Retorna os AtualizacaoCadastralLogradouroHelper do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private AtualizacaoCadastralLogradouroHelper pesquisarLogradouroAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		AtualizacaoCadastralLogradouroHelper lograHelper = null;
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				lograHelper = logradouroHelper;
				break;
			}
		}
		
		return lograHelper;		
	}
	
	private LogradouroTipo pesquisarLogradouroTipo(String id, HttpSession sessao){
		ArrayList<LogradouroTipo> logradourosTipo = (ArrayList<LogradouroTipo>) sessao.getAttribute("logradouroTipos");
		LogradouroTipo logradouroTipo = null;
		for(LogradouroTipo lograTipo : logradourosTipo){
			if(lograTipo.getId().toString().equalsIgnoreCase(id)){
				logradouroTipo = lograTipo;
			}
		}
    	
    	return logradouroTipo;		
	}
	
	private LogradouroTitulo pesquisarLogradouroTitulo(String id, HttpSession sessao){
		ArrayList<LogradouroTitulo> logradourosTitulo = (ArrayList<LogradouroTitulo>) sessao.getAttribute("logradouroTitulos");
		LogradouroTitulo logradouroTitulo = null;
		for(LogradouroTitulo lograTitulo : logradourosTitulo){
			if(lograTitulo.getId().toString().equalsIgnoreCase(id)){
				logradouroTitulo = lograTitulo;
				break;
			}
		}
    	
    	return logradouroTitulo;		
	}
	
	private Municipio pesquisarMunicipio(String id, Fachada fachada){
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, id));
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.UNIDADE_FEDERACAO);
		
    	Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
    	Municipio municipio = null;
    	if(colecaoMunicipio != null){
    		municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
    	}
    	
    	return municipio;		
	}
}
