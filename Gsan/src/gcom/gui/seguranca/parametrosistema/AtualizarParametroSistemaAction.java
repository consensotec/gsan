package gcom.gui.seguranca.parametrosistema;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Modulo;
import gcom.seguranca.parametrosistema.FiltroParametroTipo;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.seguranca.parametrosistema.ParametroTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarParametroSistemaAction  extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarParametroSistemaActionForm form = (AtualizarParametroSistemaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		ParametroSistema parametroSistema = new ParametroSistema();
		
		//Recuperando os dados do formulário
		String id = form.getId();
		String nome = form.getNome();
		
		String tipoId = form.getCodigoTipo();
		String descricaoTipo = form.getDescricaoTipo();	
		

		String moduloId = form.getModuloId();
		String descricaoModulo = form.getDescricaoModulo();
		
		//Tipo do parametro
		if(!form.getCodigoTipo().equals("") && form.getCodigoTipo() != null){
		
			
			ParametroTipo parametroTipo = new ParametroTipo();	
			parametroTipo.setId(Integer.valueOf(tipoId));	
			parametroTipo.setDescricao(descricaoTipo);
			
			parametroSistema.setParametroTipo(parametroTipo);
		}
		
		//Modulo
		if(!form.getModuloId().equals("") && form.getModuloId() != null){
			
			Modulo modulo = new Modulo();	
			modulo.setId(Integer.valueOf(moduloId));	
			modulo.setDescricaoModulo(descricaoModulo);
			
			parametroSistema.setModulo(modulo);
		}
		
		
		String indicadorParametroRestrito= form.getIndicadorParametroRestrito();
		String descricao = form.getDescricao();
		String codigoConstante = form.getCodigoConstante();
		String valorParametro = form.getValorParametro();			
		String ativoInativo = form.getIndicadorUso();
		
		
	
		//Configurando os valores do objeto para ser inserido na base de dados		

		//ID
		if(Util.verificarNaoVazio(id)){
			try{
				parametroSistema.setId(Integer.valueOf(id));
			}catch (NumberFormatException e) {
				throw new ActionServletException("erro.sistema", null ,"");	
			}
		}

		//Nome
		if(nome  != null && nome.length() <= 100){
			parametroSistema.setNome(nome);
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,"Nome " );	
		}
		
		//Indicador de Restricao
		Short indicadorRestricao = null;
		if(indicadorParametroRestrito != null && !indicadorParametroRestrito.equals(""))
		{
			indicadorRestricao = Short.valueOf(indicadorParametroRestrito);
			parametroSistema.setIndicadorParametroRestrito(indicadorRestricao);
			
			
		}
	
		//Descricao
		if(descricao  != null  && !descricao.equals("")){
			
			if(descricao.length() <= 500){
			 parametroSistema.setDescricao(descricao);
			}else{
				throw new ActionServletException("atencao.tamanho_maximo_descricao");
			}	
			
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,"Descrição " );	
		}

		if(Util.verificarNaoVazio(codigoConstante)){
			try{
				parametroSistema.setCodigoConstante(codigoConstante);
			}catch (NumberFormatException e) {
				throw new ActionServletException("erro.sistema", null ,"");	
			}
		}

		
		//Valor do parametro
			if(valorParametro  != null  || valorParametro.equals("")){
				
				
					if(valorParametro.length() <= 500){
						
						FiltroParametroTipo filtroParametroTipo = new FiltroParametroTipo();			
						filtroParametroTipo.adicionarParametro(new ParametroSimples(FiltroParametroTipo.ID, tipoId));
					
						Collection<ParametroTipo> colecaoParametroTipo = fachada.pesquisar(filtroParametroTipo, ParametroTipo.class.getName());
						ParametroTipo parametroTipo = colecaoParametroTipo.iterator().next();
						
						if(parametroTipo.getId().equals(ParametroTipo.REFERENCIA)){
							
							validarValorParametro(parametroTipo, valorParametro);
							
							String parametroValor = formatarParametroReferencia(valorParametro);
							parametroSistema.setValorParametro(parametroValor);
							
						}else{
							validarValorParametro(parametroTipo, valorParametro);
							parametroSistema.setValorParametro(valorParametro);
						}
						
						
				
					}else{
						throw new ActionServletException("atencao.tamanho_maximo_valor");
					}
				
				
			}else{
				throw new ActionServletException("atencao.campo.informado", null , "Valor Parâmetro" );	
			}
		
		//Indicador de Uso
		Short indicadorUso = null;
		if(ativoInativo != null && !ativoInativo.equals("")){
			try{
				indicadorUso = Short.valueOf(ativoInativo);
				parametroSistema.setIndicadorUso(indicadorUso);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null , "Indicador Uso" );	
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null , "Indicador Uso" );	
		}
		
		parametroSistema.setUltimaAlteracao(new Date());
		
		fachada.atualizar(parametroSistema);
		
		montarPaginaSucesso(httpServletRequest, "Parâmetro "+ 
				parametroSistema.getNome() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Parâmetro do Sistema",
				"exibirFiltrarParametroSistemaAction.do?menu=sim"); 
		        
				return retorno;
				
	}

	private void validarValorParametro(ParametroTipo parametroTipo, String valorParametro) {
		
		if(parametroTipo.getId().equals(ParametroTipo.NUMERO_INTEIRO)) {
			validarParametroNumerico(valorParametro, parametroTipo.getDescricao());
		} else if(parametroTipo.getId().equals(ParametroTipo.INDICADOR)) {
			validarParametroIndicador(valorParametro);
		} else if(parametroTipo.getId().equals(ParametroTipo.VALOR)) {
			validarParametroValor(valorParametro);
		}else if (parametroTipo.getId().equals(ParametroTipo.URL)) {
			validarParametroTexto(valorParametro);
		} else if (parametroTipo.getId().equals(ParametroTipo.TEXTO)) {
			validarParametroTexto(valorParametro);
		} else if (parametroTipo.getId().equals(ParametroTipo.IP)) {
			validarParametroIP(valorParametro);
		} else if (parametroTipo.getId().equals(ParametroTipo.METODO)) {
			validarParametroTexto(valorParametro);
		}else if (parametroTipo.getId().equals(ParametroTipo.PERCENTUAL)) {
			validarParametroPercentual(valorParametro);
		} else if (parametroTipo.getId().equals(ParametroTipo.DATA)) {
			validarParametroData(valorParametro, parametroTipo.getDescricao(), "dd/MM/yyyy");
		} else if (parametroTipo.getId().equals(ParametroTipo.DATA_HORA)) {
			validarParametroDataHora(valorParametro, parametroTipo.getDescricao(), "dd/MM/yyyy HH:mm:ss");
		} else if (parametroTipo.getId().equals(ParametroTipo.COORDENADA)) {
			validarParametroTexto(valorParametro);
		} else if (parametroTipo.getId().equals(ParametroTipo.LISTA_VALORES)) {
			validarParametroTexto(valorParametro);
		}else if (parametroTipo.getId().equals(ParametroTipo.ID)) {
			validarParametroNumerico(valorParametro, parametroTipo.getDescricao());
		}else if (parametroTipo.getId().equals(ParametroTipo.REFERENCIA)){
			validarParametroReferencia(valorParametro);
		}
	}

	//Metodo para validar numero inteiro
	private void validarParametroNumerico(String valorParametro, String tipo) {
		if(!valorParametro.equals("")){	
			if (!valorParametro.matches("[0-9]+")) {
				throw new ActionServletException("atencao.numero_inteiro_parametro", null,"");
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ," Valor");
		}
	}
	//Metodo para validar indicadores
	private void validarParametroIndicador(String valorParametro) {
		
		if(!valorParametro.equals(ConstantesSistema.SIM.toString()) && !valorParametro.equals(ConstantesSistema.NAO.toString())){
			throw new ActionServletException("atencao.indicador_parametro", null , "");
		}
		
	}
	
	//Metodo para validar valores com 2 casas decimais
	private void validarParametroValor(String valorParametro) {
		
		if(!valorParametro.equals("")){			
			if(!valorParametro.matches("[1-9,\\.]+,[0-9]{1,2}")) {
				throw new ActionServletException("atencao.valor_parametro", null , "");
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ," Valor");
		}
	}

		//Metodo para validar texto
	private void validarParametroTexto(String valorParametro) {
			if(!valorParametro.equals("")){	
				
			}else{
				throw new ActionServletException("atencao.campo.informado", null ," Valor");
			}
		}
	
	
	//Metodo para validar numero IP
	private void validarParametroIP(String valorParametro) {
		
		if(!valorParametro.equals("")){	
		
			if (valorParametro.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
				
				String[] ip = valorParametro.split("\\.");
			
				for (int i = 0; i < ip.length; i++) {
					
					if(new Integer(ip[i]) > 255){
						throw new ActionServletException("atencao.ip_parametro", null , "" );
					}
					
				}
				
			} else {
				
				throw new ActionServletException("atencao.ip_parametro", null , "");	
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ," Valor");
		}
	}
	
	//Metodo para validar percentual
	private void validarParametroPercentual(String valorParametro) {
		if(!valorParametro.equals("")){	
			if(!valorParametro.matches("[0-9]{1,3}\\,[0-9]{1,2}")) {
				throw new ActionServletException("atencao.percentual_parametro", null , "");
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ," Valor");
		}
	}
	
	//Metodo para validar data
	private void validarParametroData(String valorParametro, String tipo, String formato) {
		
		if(!valorParametro.equals("")){	
			if(!Util.validarDataValida(valorParametro, formato)){
				throw new ActionServletException("atencao.data_parametro", tipo, formato.replace("yyyy", "aaaa").toLowerCase());
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ," Valor");
		}
	}
	
	//Metodo para validar data e hora
	private void validarParametroDataHora(String valorParametro, String tipo, String formato) {
		 if(!valorParametro.equals("")){							
			if(!Util.validarDataValida(valorParametro, formato)){
				throw new ActionServletException("atencao.data_hora_parametro", tipo, formato.replace("yyyy", "aaaa").toLowerCase());
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ," Valor");
		}
	}
	
	//Metodo para validar referência
		private void validarParametroReferencia(String valorParametro) {
			if(!valorParametro.equals("")){	
				if(Util.validarAnoMesInvalido(valorParametro,"mm/yyyy")){
					throw new ActionServletException("atencao.referencia_parametro",null,"");
				}
			}else{
				throw new ActionServletException("atencao.campo.informado", null ," Valor");
			}
		}
		
		//Metodo para formatar Ano/Mês referência (MM/YYYY)
		private String formatarParametroReferencia(String valorParametro){
			
			String retorno ="";
			
			if(!valorParametro.equals("")){
				
				String referencia = valorParametro.replace("/", "");
				String anoReferencia = referencia.substring(2, 6);
				String anoMes = referencia.substring(0,2);
				
				retorno = anoReferencia + anoMes;
				
			}else{
				throw new ActionServletException("atencao.campo.informado", null ," Valor");
			}
			
			return retorno;
			
		}
		
		
}
