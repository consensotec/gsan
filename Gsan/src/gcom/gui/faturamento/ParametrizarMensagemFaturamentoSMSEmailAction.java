package gcom.gui.faturamento;

import gcom.cadastro.ParametrosMSGSMSEmail;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ColunasTextoSMSEmail;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ParametrizarMensagemFaturamentoSMSEmailAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		ParametrizarMensagemFaturamentoSMSEmailActionForm parametrizarMensagemFaturamentoSMSEmailActionForm = 
				(ParametrizarMensagemFaturamentoSMSEmailActionForm) actionForm;
		
		ParametrosMSGSMSEmail  parametrosMSGSMSEmail = new ParametrosMSGSMSEmail();
		
		// Data de Referencia
		parametrosMSGSMSEmail.setAnoMesReferencia(
				Util.formatarMesAnoComBarraParaAnoMes(
						parametrizarMensagemFaturamentoSMSEmailActionForm.getAnoMesFaturamento()));
		
		// Valor minimo
		parametrosMSGSMSEmail.setValorMinimoConta(
				Util.formatarMoedaRealparaBigDecimal(parametrizarMensagemFaturamentoSMSEmailActionForm.getValorMinimoConta()));
		
		// Categorias
		String[] categorias = parametrizarMensagemFaturamentoSMSEmailActionForm.getCategorias();
		
		for(int posicaoCategoria = 0;posicaoCategoria<categorias.length;posicaoCategoria++){
			if(Integer.valueOf(categorias[posicaoCategoria]).compareTo(Categoria.RESIDENCIAL_INT)==0){
				parametrosMSGSMSEmail.setIndicadorResidencial(ConstantesSistema.SIM);
			}else if(Integer.valueOf(categorias[posicaoCategoria]).compareTo(Categoria.COMERCIAL_INT)==0){
				parametrosMSGSMSEmail.setIndicadorComercial(ConstantesSistema.SIM);
			}else if(Integer.valueOf(categorias[posicaoCategoria]).compareTo(Categoria.PUBLICO_INT)==0){
				parametrosMSGSMSEmail.setIndicadorPublico(ConstantesSistema.SIM);
			}else if(Integer.valueOf(categorias[posicaoCategoria]).compareTo(Categoria.INDUSTRIAL_INT)==0){
				parametrosMSGSMSEmail.setIndicadorIndustrial(ConstantesSistema.SIM);
			}
		}
		
		
		if(parametrosMSGSMSEmail.getIndicadorResidencial()==null){
			parametrosMSGSMSEmail.setIndicadorResidencial(ConstantesSistema.NAO);
		}
		
		if(parametrosMSGSMSEmail.getIndicadorComercial()==null){
			parametrosMSGSMSEmail.setIndicadorComercial(ConstantesSistema.NAO);
		}
		
		if(parametrosMSGSMSEmail.getIndicadorPublico()==null){
			parametrosMSGSMSEmail.setIndicadorPublico(ConstantesSistema.NAO);
		}
		
		if(parametrosMSGSMSEmail.getIndicadorIndustrial()==null){
			parametrosMSGSMSEmail.setIndicadorIndustrial(ConstantesSistema.NAO);
		}
		
		// Quantidade Dias Antes Vencimento
		if(parametrizarMensagemFaturamentoSMSEmailActionForm.getQuantidadeDiasAntesVencimento()!=null && parametrizarMensagemFaturamentoSMSEmailActionForm.getQuantidadeDiasAntesVencimento().compareTo("")!=0){
			parametrosMSGSMSEmail.setQuantidadeDiasAntesVencimento(Integer.valueOf(parametrizarMensagemFaturamentoSMSEmailActionForm.getQuantidadeDiasAntesVencimento()));
		}else{
			parametrosMSGSMSEmail.setQuantidadeDiasAntesVencimento(0);
		}
		
		// Quantidade tentativas de envio
		parametrosMSGSMSEmail.setQuantidadeTentativasEnvio(Integer.valueOf(parametrizarMensagemFaturamentoSMSEmailActionForm.getQuantidadeTentativaEnvio()));
		
		// Descricao texto Email
		parametrosMSGSMSEmail.setDescricaoTextoEmail(parametrizarMensagemFaturamentoSMSEmailActionForm.getDescricaoTextoEmail());
		
		@SuppressWarnings("rawtypes")
		Collection colecaoColunasTextoSMSEmail = (Collection)
				httpServletRequest.getSession().getAttribute("colecaoColunasTextoSMSEmail");
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		String descricaoTextoSMSFormatadaTamanhoReal = parametrizarMensagemFaturamentoSMSEmailActionForm.getDescricaoTextoSMS();
		
		@SuppressWarnings("rawtypes")
		Iterator iterator = colecaoColunasTextoSMSEmail.iterator();
		ColunasTextoSMSEmail colunasTextoSMSEmail = null;
		
		while(iterator.hasNext()){
			colunasTextoSMSEmail = (ColunasTextoSMSEmail)iterator.next();
	
			descricaoTextoSMSFormatadaTamanhoReal = replicarValoresReaisDescricaoSMS(
					descricaoTextoSMSFormatadaTamanhoReal, colunasTextoSMSEmail.getNomeColuna(),
					colunasTextoSMSEmail.getTamanhoColuna(),sistemaParametro.getTamanhoMaximoMensagemSms());
		}
		
		if(descricaoTextoSMSFormatadaTamanhoReal.length()+
				ConstantesSistema.QUANTIDADE_CARACTERE_CODIGO_DE_BARRAS<=sistemaParametro.getTamanhoMaximoMensagemSms()){
			// Descricao texto SMS
			parametrosMSGSMSEmail.setDescricaoTextoSMS(parametrizarMensagemFaturamentoSMSEmailActionForm.getDescricaoTextoSMS());
		}else{
			if(httpServletRequest.getParameter("navegadorIE")!=null){
				throw new ActionServletException("atencao.numero_maximo_permitido_caractere_sms","exibirParametrizarMensagemFaturamentoSMSEmailAction.do?manterEstadoAnterior=IE",null,sistemaParametro.getTamanhoMaximoMensagemSms().toString());
			}else{
				throw new ActionServletException("atencao.numero_maximo_permitido_caractere_sms",null,sistemaParametro.getTamanhoMaximoMensagemSms().toString());
			}
			
		}
		
		Short indicadorPreencherFormRetornoIE = 2;
		Short indicadorIE = 2;
		if(httpServletRequest.getParameter("navegadorIE")!=null){
			indicadorPreencherFormRetornoIE = 1;
			indicadorIE = 1;
		}
		
		// Validar campo SMS
		validarTagsTextAreaSMSEmail(parametrizarMensagemFaturamentoSMSEmailActionForm.getDescricaoTextoSMS(), 
				colecaoColunasTextoSMSEmail,ConstantesSistema.SIM,indicadorIE,indicadorPreencherFormRetornoIE,
				httpServletRequest,parametrizarMensagemFaturamentoSMSEmailActionForm,parametrosMSGSMSEmail);
		
		// Validar campo Email
		validarTagsTextAreaSMSEmail(parametrizarMensagemFaturamentoSMSEmailActionForm.getDescricaoTextoEmail(), 
				colecaoColunasTextoSMSEmail,ConstantesSistema.NAO,indicadorIE,indicadorPreencherFormRetornoIE,
				httpServletRequest,parametrizarMensagemFaturamentoSMSEmailActionForm,parametrosMSGSMSEmail);
		
		/*
		 *  [FS0006] Atualiza mensagem de Faturamento.
		 *  CASE 2
		 */
		// Ultima alteracao
		parametrosMSGSMSEmail.setUltimaAlteracao(new Date());
		Fachada.getInstancia().inserir(parametrosMSGSMSEmail);
		
		/*
		 *  [FS0006] Atualiza mensagem de Faturamento.
		 *  CASE 1
		 */
		ParametrosMSGSMSEmail parametrosMSGSMSEmailSession = (ParametrosMSGSMSEmail) httpServletRequest.getSession().getAttribute("parametrosMSGSMSEmail");
		if(parametrosMSGSMSEmailSession!=null){
			parametrosMSGSMSEmailSession.setDataRetirada(new Date());
			Fachada.getInstancia().atualizar(parametrosMSGSMSEmailSession);
		}
		
		montarPaginaSucesso(httpServletRequest, "Parametrização realizada com sucesso.",
				"Realizar uma nova parametrização", "exibirParametrizarMensagemFaturamentoSMSEmailAction.do?menu=sim");
        
		
		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	private void validarTagsTextAreaSMSEmail(String textoSMSEmail,Collection colecaoColunasTextoSMSEmail,Short indicadorSMSEmail,Short indicadorIE,Short indicadorPreencherFormRetornoIE,HttpServletRequest httpServletRequest,ParametrizarMensagemFaturamentoSMSEmailActionForm parametrizarMensagemFaturamentoSMSEmailActionForm,ParametrosMSGSMSEmail parametrosMSGSMSEmail){
		if(validarQuantidadeTagsAbertasFechadas(textoSMSEmail)){
			boolean encontrouTag = false;
			String[] arrayChavesAberta = textoSMSEmail.split("[{]");
			String[] arrayChevesFechada = null;
			for(int posicao = 0;posicao<arrayChavesAberta.length;posicao++){
				encontrouTag = false;
				if(pesquisarChaves(arrayChavesAberta[posicao])){
					arrayChevesFechada = arrayChavesAberta[posicao].split("[}]");
					Iterator iterator = colecaoColunasTextoSMSEmail.iterator();
					ColunasTextoSMSEmail colunasTextoSMSEmail = null;
					while(iterator.hasNext()){
						colunasTextoSMSEmail = (ColunasTextoSMSEmail) iterator.next();
						if(arrayChevesFechada[0].compareTo(colunasTextoSMSEmail.getNomeColuna().replace("{", "").replace("}", ""))==0){
							encontrouTag = true;
						}
					}
					if(encontrouTag==false){
						if(indicadorIE.compareTo(ConstantesSistema.SIM)==0){
							if(indicadorSMSEmail.compareTo(ConstantesSistema.SIM)==0){
								throw new ActionServletException("atencao.tag.sms.invalida","exibirParametrizarMensagemFaturamentoSMSEmailAction.do?manterEstadoAnterior=IE",null,"{"+arrayChevesFechada[0]+"}");
							}else{
								throw new ActionServletException("atencao.tag.email.invalida","exibirParametrizarMensagemFaturamentoSMSEmailAction.do?manterEstadoAnterior=IE",null,"{"+arrayChevesFechada[0]+"}");
							}
							
						}else{
							if(indicadorSMSEmail.compareTo(ConstantesSistema.SIM)==0){
								throw new ActionServletException("atencao.tag.sms.invalida",null,"{"+arrayChevesFechada[0]+"}");
							}else{
								throw new ActionServletException("atencao.tag.email.invalida",null,"{"+arrayChevesFechada[0]+"}");
							}
							
						}
					}
				}
			}
		}else{
			if(indicadorSMSEmail.compareTo(ConstantesSistema.SIM)==0){
				if(indicadorIE.compareTo(ConstantesSistema.SIM)==0){
					throw new ActionServletException("atencao.quantidade.tags.invalida","exibirParametrizarMensagemFaturamentoSMSEmailAction.do?manterEstadoAnterior=IE",null,"SMS");
				}else{
					throw new ActionServletException("atencao.quantidade.tags.invalida",null,"SMS");
				}
			}else if(indicadorSMSEmail.compareTo(ConstantesSistema.NAO)==0){
				if(indicadorIE.compareTo(ConstantesSistema.SIM)==0){
					throw new ActionServletException("atencao.quantidade.tags.invalida","exibirParametrizarMensagemFaturamentoSMSEmailAction.do?manterEstadoAnterior=IE",null,"E-MAIL");
				}else{
					throw new ActionServletException("atencao.quantidade.tags.invalida",null,"E-MAIL");
				}
			}
		}
	}
	
	private boolean validarQuantidadeTagsAbertasFechadas(String textoSMSEmail){
		boolean quantidadeValidada = false;
		int quantidadeAbrirChaves = 0;
		int quantidadeFecharChaves = 0;
		
		char[] arrayTextoSMSEmail = textoSMSEmail.toCharArray();
		
		for(int posicao = 0;posicao<textoSMSEmail.length();posicao++){
			if(arrayTextoSMSEmail[posicao]=='{'){
				quantidadeAbrirChaves++;
			}else if(arrayTextoSMSEmail[posicao]=='}'){
				quantidadeFecharChaves++;
			}
		}
		
		if(quantidadeAbrirChaves==quantidadeFecharChaves){
			quantidadeValidada = true;
		}
		
		return quantidadeValidada;
	}
	
	private boolean pesquisarChaves(String textoQuebrado){
		boolean temAspas = false;
		char[] arrayTextoQuebrado = textoQuebrado.toCharArray();
		for(int posicao = 0;posicao<arrayTextoQuebrado.length;posicao++){
			if(arrayTextoQuebrado[posicao]=='}'){
				temAspas=true;
				break;
			}
		}
		return temAspas;
	}
	
	public String replicarValoresReaisDescricaoSMS(String descricaoTextoSMSFormatadaTamanhoReal,String tag,int tamanhoTag,int tamanhoMaximoSMS){
		
		String geradorQuantidadeCaractereTag = "";
		
		for(int posicao = 0;posicao<tamanhoTag;posicao++){
			geradorQuantidadeCaractereTag +="X"; 
		}
		
		for(int posicao = 0;posicao<tamanhoMaximoSMS/tamanhoTag;posicao++){
			descricaoTextoSMSFormatadaTamanhoReal = descricaoTextoSMSFormatadaTamanhoReal.replace(tag, geradorQuantidadeCaractereTag);
		}
		
		return descricaoTextoSMSFormatadaTamanhoReal;
	}
}
