package gsan.gui.portal.caema;

import gsan.util.Util;

import java.math.BigDecimal;

/**
 * [UC1194] Consultar Estrutura Tarifária Loja Virtual
 * 
 * Helper responsável pela exibição das estruturas tarifárias 
 * da tela informacoes_estrutura_tarifaria_porta_consultar.jsp
 * 
 * @author Diogo Peixoto
 * @since 15/07/2011
 *
 */

public class ConsultarEstruturaTarifariaPortalCaemaHelper {

	private String categoria;
	private String valor;
	private String valorReal;
	private String quantidade;
	private Integer indiceConsumo;
	private String numeroConsumo;
	
	public ConsultarEstruturaTarifariaPortalCaemaHelper(String categoria, String valor, Integer indice){
		this.setCategoria(categoria);
		this.setValor(valor);
		this.indiceConsumo = indice;
	}

	public ConsultarEstruturaTarifariaPortalCaemaHelper(String categoria, String quantidade, String valor, Integer indice){
		this.setCategoria(categoria);
		this.setValor(valor);
		this.quantidade = quantidade;
		this.indiceConsumo = indice;
	}
	
	public ConsultarEstruturaTarifariaPortalCaemaHelper(String categoria, String quantidade, String valor, Integer indice, String numeroConsumo){
		this.setCategoria(categoria);
		this.numeroConsumo = numeroConsumo;
		this.setValor(valor);
		this.quantidade = quantidade;
		this.indiceConsumo = indice;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		if(categoria != null){
			this.categoria = categoria;
		}else{
			this.categoria = "";
		}
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valorReal = valor;
		
		if(valor != null && !valor.equals("") ){
			String valorAux = null;
			if ( this.getNumeroConsumo() != null ) {
				BigDecimal valorDividido = Util.formatarStringParaBigDecimal(valor.replace(",", "."));
				BigDecimal quantidade = Util.formatarStringParaBigDecimal(this.getNumeroConsumo());
				valorAux = Util.formatarMoedaReal(Util.dividirArredondando(valorDividido, quantidade));
			} else {
				valorAux = Util.formatarMoedaReal(Util.formatarStringParaBigDecimal(valor.replace(",", ".")));
			}
			
			this.valor = valorAux;
		}else{
			this.valor = "";
		}
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		if(quantidade != null){
			this.quantidade = quantidade;
		}else{
			this.quantidade = "";
		}
	}

	public Integer getIndiceConsumo() {
		return indiceConsumo;
	}

	public void setIndiceConsumo(Integer indiceConsumo) {
		this.indiceConsumo = indiceConsumo;
	}

	public String getNumeroConsumo() {
		return numeroConsumo;
	}

	public void setNumeroConsumo(String numeroConsumo) {
		this.numeroConsumo = numeroConsumo;
	}

	public String getValorReal() {
		return valorReal;
	}

	public void setValorReal(String valorReal) {
		this.valorReal = valorReal;
	}
	
	
}