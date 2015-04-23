package gsan.gui.portal.caer;

import java.math.BigDecimal;

import gsan.util.Util;

/**
 * [UC1194] Consultar Estrutura Tarifária Loja Virtual
 * 
 * Helper responsável pela exibição das estruturas tarifárias 
 * da tela informacoes_estrutura_tarifaria_porta_consultar.jsp
 * 
 * @author Diogo Peixoto, Davi Menezes
 * @since 15/07/2011, 13/09/2012
 *
 */

public class ConsultarEstruturaTarifariaPortalCaerHelper {

	private String valor;
	private Integer indiceConsumo;
	private String descricaoCategoria;
	private String valorExemplo;
	private String valorEsgoto;
	private String valorTotalConta;
	
	public ConsultarEstruturaTarifariaPortalCaerHelper(String valor, Integer indice){
		this.setValor(valor);
		this.indiceConsumo = indice;
	}

	public ConsultarEstruturaTarifariaPortalCaerHelper(String valor, Integer indice, String descricaoCategoria){
		this.setValor(valor);
		this.setValorExemplo(valor);
		this.setValorEsgoto(valor);
		this.setValorTotalConta(valor);
		this.indiceConsumo = indice;
		this.descricaoCategoria = descricaoCategoria;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		if(valor != null && !valor.equals("") ){
			String valorAux = null;
			valorAux = Util.formatarMoedaReal(Util.formatarStringParaBigDecimal(valor.replace(",", ".")));
			
			this.valor = valorAux;
		}else{
			this.valor = "";
		}
	}
	
	public String getValorExemplo() {
		return valorExemplo;
	}

	public void setValorExemplo(String valorExemplo) {
		if(valorExemplo != null && !valorExemplo.equals("")){
			BigDecimal valAux = Util.formatarMoedaRealparaBigDecimal(valorExemplo);
			
			valAux = valAux.multiply(new BigDecimal(16500));
			valAux = Util.dividirArredondando(valAux, new BigDecimal(10000));
			
			this.valorExemplo = Util.formatarMoedaReal(valAux);
		}
	}
	
	public String getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(String valorEsgoto) {
		if(valorEsgoto != null && !valorEsgoto.equals("")){
			BigDecimal valorAux = Util.formatarMoedaRealparaBigDecimal(valorEsgoto);
			
			valorAux = valorAux.multiply(new BigDecimal(80));
			valorAux = Util.dividirArredondando(valorAux, new BigDecimal(100));
			
			this.valorEsgoto = Util.formatarMoedaReal(valorAux);
		}
	}
	
	public String getValorTotalConta() {
		return valorTotalConta;
	}

	public void setValorTotalConta(String valorTotalConta) {
		if(valorTotalConta != null && !valorTotalConta.equals("")
				&& this.valorEsgoto != null && !valorEsgoto.equals("")){
			BigDecimal valorContaAux = Util.formatarMoedaRealparaBigDecimal(valorTotalConta);
			BigDecimal valorEsgotoAux = Util.formatarMoedaRealparaBigDecimal(this.valorEsgoto);
			
			BigDecimal valorAux = Util.somaBigDecimal(valorContaAux, valorEsgotoAux);
			
			this.valorTotalConta = Util.formatarMoedaReal(valorAux);
		}
	}

	public Integer getIndiceConsumo() {
		return indiceConsumo;
	}

	public void setIndiceConsumo(Integer indiceConsumo) {
		this.indiceConsumo = indiceConsumo;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
}