package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.RelatorioEnquadramentoDesenquadramentoGrandeCliente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction extends ExibidorProcessamentoTarefaRelatorio {	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {		
		
		ActionForward retorno = actionMapping.findForward("gerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction");		
		
		GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm form = 
				(GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm) actionForm;
		
		boolean peloMenosUmParametroInformado = false;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		RelatorioEnquadramentoDesenquadramentoGrandeCliente relatorio = new RelatorioEnquadramentoDesenquadramentoGrandeCliente(
			(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));		
		
		//Municipio
		if(Util.verificarNaoVazio(form.getIdMunicipio())){			
			peloMenosUmParametroInformado = true;			
			relatorio.addParametro("municipio", form.getIdMunicipio());
		}		
		
		//Gerencia Regional
		if(Util.verificarNaoVazio(form.getGerenciaRegional()) 
		&& !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("gerenciaRegional", form.getGerenciaRegional());
		}		
					
		//Unidade de Negocios
		if(Util.verificarNaoVazio(form.getUnidadeNegocio()) 
		&& !form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("unidadeNegocio", form.getUnidadeNegocio());
		}		
				
		//Localidade
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			
			this.validarLocalidadeGerenciaUnidadeNegocio(form);
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("localidade", form.getIdLocalidade());
		}		
			
		//Setor Comercial
		if(Util.verificarNaoVazio(form.getCodigoSetorComercial())){
			
			if(!Util.verificarNaoVazio(form.getIdLocalidade())){
				throw new ActionServletException("atencao.naoinformado", null, "Localidade");
			}
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("setorComercial", form.getCodigoSetorComercial());
		}
				
		//Quadra
		if(Util.verificarNaoVazio(form.getIdQuadra())){
			
			if(!Util.verificarNaoVazio(form.getIdLocalidade())
		    && !Util.verificarNaoVazio(form.getCodigoSetorComercial())){
				throw new ActionServletException("atencao.naoinformado", null, "Setor Comercial e/ou Localidade");
			}
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("quadra", form.getIdQuadra());
		}
		
		//Rota
		if(Util.verificarNaoVazio(form.getRota())  
	   && !form.getRota().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			if(!Util.verificarNaoVazio(form.getIdLocalidade())
		    && !Util.verificarNaoVazio(form.getCodigoSetorComercial())){
				throw new ActionServletException("atencao.naoinformado", null, "Setor Comercial e/ou Localidade");
			}
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("rota", form.getRota());
		}
		
		//Imovel Perfil Destino
		if(Util.verificarNaoVazio(form.getImovelPerfilDestino())
		&& !form.getImovelPerfilDestino().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("imovelPerfilDestino", form.getImovelPerfilDestino());
		}
		
		//Imovel Perfil Origem
		if(Util.verificarNaoVazio(form.getImovelPerfilOrigem())
		&& !form.getImovelPerfilOrigem().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("imovelPerfilOrigem", form.getImovelPerfilOrigem());
		}
		
		//Enquadramento Tipo
		if(Util.verificarNaoVazio(form.getEnquadramentoTipo())
		&& !form.getEnquadramentoTipo().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("enquadramentoTipo", form.getEnquadramentoTipo());
		}		
		
		//Data do Enquadramento no Perfil
		if(Util.verificarNaoVazio(form.getEnquadramentoDataInicial()) 
		&& Util.verificarNaoVazio(form.getEnquadramentoDataFinal())){		
			
			if(Util.validarDataInvalidaDiaMesAno(form.getEnquadramentoDataInicial(), "dd/MM/yyyy") || 
			   Util.validarDataInvalidaDiaMesAno(form.getEnquadramentoDataFinal(), "dd/MM/yyyy")){				
				throw new ActionServletException("atencao.data.invalida");				
			}
			
			if(Util.compararData(Util.converteStringParaDate(form.getEnquadramentoDataFinal()), 
				Util.converteStringParaDate(form.getEnquadramentoDataInicial())) == ConstantesSistema.NUMERO_NAO_INFORMADO){
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("dataEnquadramentoInicial", form.getEnquadramentoDataInicial());
			relatorio.addParametro("dataEnquadramentoFinal", form.getEnquadramentoDataFinal());
			
		}else if(Util.verificarIdNaoVazio(form.getEnquadramentoDataFinal())
			 && !Util.verificarNaoVazio(form.getEnquadramentoDataInicial())){
			
			if(Util.validarDataInvalidaDiaMesAno(form.getEnquadramentoDataFinal(), "dd/MM/yyyy")){
				throw new ActionServletException("atencao.data.invalida");
			}
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("dataEnquadramentoFinal", form.getEnquadramentoDataFinal());
			
		}else if(!Util.verificarNaoVazio(form.getEnquadramentoDataFinal()) 
			  &&  Util.verificarNaoVazio(form.getEnquadramentoDataInicial())){
				if(Util.validarDataInvalidaDiaMesAno(form.getEnquadramentoDataInicial(), "dd/MM/yyyy")){
					throw new ActionServletException("atencao.data.invalida");
				}
			throw new ActionServletException("atencao.naoinformado", null, "Data do Enquadramento no Perfil Inicial");
		}		
		
		//Data do Desenquadramento no Perfil
		if(Util.verificarNaoVazio(form.getDesenquadramentoDataInicial()) 
		&& Util.verificarNaoVazio(form.getDesenquadramentoDataFinal())){
			
			if(Util.validarDataInvalidaDiaMesAno(form.getDesenquadramentoDataInicial(), "dd/MM/yyyy") 
			|| Util.validarDataInvalidaDiaMesAno(form.getDesenquadramentoDataFinal(), "dd/MM/yyyy")){
				throw new ActionServletException("atencao.data.invalida");			
			}
			
			if(Util.compararData(Util.converteStringParaDate(form.getDesenquadramentoDataFinal()), 
				Util.converteStringParaDate(form.getDesenquadramentoDataInicial())) == ConstantesSistema.NUMERO_NAO_INFORMADO){
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("dataDesenquadramentoInicial", form.getDesenquadramentoDataInicial());
			relatorio.addParametro("dataDesenquadramentoFinal", form.getDesenquadramentoDataFinal());
			
		}else if(Util.verificarNaoVazio(form.getDesenquadramentoDataFinal()) 
			 && !Util.verificarNaoVazio(form.getDesenquadramentoDataInicial())){
			
			if(Util.validarDataInvalidaDiaMesAno(form.getDesenquadramentoDataFinal(), "dd/MM/yyyy")){
				throw new ActionServletException("atencao.data.invalida");
			}
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("dataDesenquadramentoFinal", form.getDesenquadramentoDataFinal());
			
		}else if(Util.verificarNaoVazio(form.getDesenquadramentoDataInicial()) 
		     && !Util.verificarNaoVazio(form.getDesenquadramentoDataFinal())){
			
			if(Util.validarDataInvalidaDiaMesAno(form.getDesenquadramentoDataInicial(), "dd/MM/yyyy")){
				throw new ActionServletException("atencao.data.invalida");
			}
			throw new ActionServletException("atencao.naoinformado", null, "Data do Desenquadramento no Perfil Inicial");
		}
		
		//Categoria	
		if(!Util.isVazioOrNulo(form.getCategoria())){
			for(String categoria :  form.getCategoria()){
				if(!categoria.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					peloMenosUmParametroInformado = true;
					relatorio.addParametro("categoria", form.getCategoria());
				}
			}
		}				
		
		//Subcategoria
		if(!Util.isVazioOrNulo(form.getSubcategoria())){
			for(String subcategoria : form.getSubcategoria()){
				if(!subcategoria.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					peloMenosUmParametroInformado = true;
					relatorio.addParametro("subcategoria", form.getSubcategoria());
				}			
			}
		}		
		
		//Ligacao Agua Situacao
		if(Util.verificarNaoVazio(form.getSituacaoLigacaoAgua()) 
		&& !form.getSituacaoLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("situacaoLigacaoAgua", form.getSituacaoLigacaoAgua());			
		}
		
		//Ligacao Esgoto Situacao
		if(Util.verificarNaoVazio(form.getSituacaoLigacaoEsgoto()) 
		&& !form.getSituacaoLigacaoEsgoto().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("situacaoLigacaoEsgoto", form.getSituacaoLigacaoEsgoto());
		}		
		
		//Intervalo do Valor do Faturamento
		String valorFaturamentoInicial = form.getValorFaturamentoIntervaloInicial().replace(".", "").replace(",", "");
		String valorFaturamentoFinal =  form.getValorFaturamentoIntervaloFinal().replace(".", "").replace(",", "");		
		if(Util.verificarNaoVazio(form.getValorFaturamentoIntervaloInicial()) 
		&& Util.verificarNaoVazio(form.getValorFaturamentoIntervaloFinal())){						
			
			if(Util.validarValorLongoNaoNumerico(valorFaturamentoInicial)){
				throw new ActionServletException("atencao.campo.invalido", null, "Intervalo Valor Faturamento Inicial");				
			}
			
			if(Util.validarValorLongoNaoNumerico(valorFaturamentoFinal)){
				throw new ActionServletException("atencao.campo.invalido", null, "Intervalo Valor Faturamento Final");
			}
			
			if(Long.parseLong(valorFaturamentoInicial) > Long.parseLong(valorFaturamentoFinal)){
				throw new ActionServletException("atencao.intervalo_inicial_menor_final", "Intervalo Valor Faturamento Inicial", 
					"Intervalo Valor Faturamento Final");
			}
			
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("intervaloValorFaturamentoInicial", form.getValorFaturamentoIntervaloInicial().replace(".", "").replace(",", "."));
			relatorio.addParametro("intervaloValorFaturamentoFinal", form.getValorFaturamentoIntervaloFinal().replace(".", "").replace(",", "."));			
						
		}else if(Util.verificarNaoVazio(form.getValorFaturamentoIntervaloInicial())
			 && !Util.verificarNaoVazio(form.getValorFaturamentoIntervaloFinal())){
			
			if(Util.validarValorNaoNumerico(form.getValorFaturamentoIntervaloInicial())){
				throw new ActionServletException("atencao.campo.invalido", null, "Intervalo Valor Faturamento Inicial");				
			}
			
			throw new ActionServletException("atencao.naoinformado", null,"Intervalo Valor Faturamento Final");
		
		}else if(!Util.verificarNaoVazio(form.getValorFaturamentoIntervaloInicial())
			   && Util.verificarNaoVazio(form.getValorFaturamentoIntervaloFinal())){
			
			if(Util.validarValorNaoNumerico(form.getValorFaturamentoIntervaloFinal())){
				throw new ActionServletException("atencao.campo.invalido", null, "Intervalo Valor Faturamento Final");
			}
			
			throw new ActionServletException("atencao.naoinformado", null,"Intervalo Consumo Agua Inicial");			
		}
		
		//Esfera Poder
		if(Util.verificarNaoVazio(form.getEsferaPoder())
		&& !form.getEsferaPoder().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			relatorio.addParametro("esferaPoder", form.getEsferaPoder());			
		}
		
		//Capacidade do Hidrometro
		if(!Util.isVazioOrNulo(form.getHidrometroCapacidade())){
			for(String capacidadeHidrometro : form.getHidrometroCapacidade()){
				if(!capacidadeHidrometro.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					peloMenosUmParametroInformado = true;
					relatorio.addParametro("hidrometroCapacidade", form.getHidrometroCapacidade());
				}			
			}
		}		
		
		//Tarifa Consumo
		if(!Util.isVazioOrNulo(form.getConsumoTarifa())){
			for(String tarifaConsumo : form.getConsumoTarifa()){
				if(!tarifaConsumo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					peloMenosUmParametroInformado = true;
					relatorio.addParametro("consumoTarifa", form.getConsumoTarifa());
				}						
			}
		}		
		
		//verificar se foi informado ao menos um parametro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}		
		
		//Tipo de Relatorio
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);
		
		montarPaginaSucesso(httpServletRequest, "Relatório Gerado com Sucesso!", 
			"Gerar outro Relatorio", "exibirGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do?menu=sim");
		
		return retorno;
	}
	
	private void validarLocalidadeGerenciaUnidadeNegocio(GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm form){
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		if((Util.verificarNaoVazio(form.getUnidadeNegocio()) && !form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))
	   && (Util.verificarNaoVazio(form.getGerenciaRegional()) && form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))){			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_UNIDADE_NEGOCIO, form.getUnidadeNegocio()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			Collection colecaoLocalidade = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if(Util.isVazioOrNulo(colecaoLocalidade)){
				
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));
				Collection colecaoUnidadeNegocio = getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoUnidadeNegocio)){
					UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
					throw new ActionServletException("atencao.localidade_nao_pertence_unidade_negocio", null, unidadeNegocio.getNome());
				}				
			}
		}
		
		if((Util.verificarNaoVazio(form.getGerenciaRegional()) && !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (Util.verificarNaoVazio(form.getUnidadeNegocio()) && form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))){
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			Collection colecaoLocalidade = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if(Util.isVazioOrNulo(colecaoLocalidade)){
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, form.getGerenciaRegional()));
				Collection colecaoGerenciaRegional = getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoGerenciaRegional)){
					GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
					throw new ActionServletException("atencao.localidade_nao_pertence_gerencia_regional", null, gerenciaRegional.getNome());
				}
			}			
		}
		
		if((Util.verificarNaoVazio(form.getUnidadeNegocio()) && !form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))
		&& (Util.verificarNaoVazio(form.getGerenciaRegional()) && !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))){
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.GERENCIA);
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.UNIDADE_NEGOCIO);
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			Collection colecaoLocalidade = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());			
			
			if(!Util.isVazioOrNulo(colecaoLocalidade)){
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				if(!localidade.getUnidadeNegocio().getId().equals(Integer.parseInt(form.getUnidadeNegocio()))
				 && localidade.getGerenciaRegional().getId().equals(Integer.parseInt(form.getGerenciaRegional()))){
					filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));
					Collection colecaoUnidadeNegocio = getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
					
					if(!Util.isVazioOrNulo(colecaoUnidadeNegocio)){
						UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
						throw new ActionServletException("atencao.localidade_nao_pertence_unidade_negocio", null, unidadeNegocio.getNome());
					}					
				}
				
				 if(!localidade.getGerenciaRegional().getId().equals(Integer.parseInt(form.getGerenciaRegional()))
				  && localidade.getUnidadeNegocio().getId().equals(Integer.parseInt(form.getUnidadeNegocio()))){
					filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, form.getGerenciaRegional()));
					Collection colecaoGerenciaRegional = getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
					if(!Util.isVazioOrNulo(colecaoGerenciaRegional)){
						GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
						throw new ActionServletException("atencao.localidade_nao_pertence_gerencia_regional", null, gerenciaRegional.getNome());
					}					
				}
				 
				 if(!localidade.getUnidadeNegocio().getId().equals(Integer.parseInt(form.getUnidadeNegocio())) 
						 && !localidade.getGerenciaRegional().getId().equals(Integer.parseInt(form.getGerenciaRegional()))){
					 throw new ActionServletException("atencao.localidade_nao_pertence_gerencia_unidade"); 
				 }								
			}		
		}		
	}
}
