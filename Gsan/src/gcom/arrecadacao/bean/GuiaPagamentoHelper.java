package gcom.arrecadacao.bean;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.localidade.Localidade;
import gcom.faturamento.debito.DebitoTipo;

import java.math.BigDecimal;
import java.util.Date;

/**
  * [UC1691] Confirmar Pagamento Cartão de Crédito
  *	 
  * Helper contendo as informações das guias de pagamento
  * para exibir na tela de confirmar pagamento cartão de crédito
  * 
  * @author Jean Varela
  * @date 06/10/2015
 */

public class GuiaPagamentoHelper {
	
	private Integer    idGuiaPagamento;
	private String     nnIdentificadorTrancacao;
	private Date       dataParcelamento;
	private BigDecimal valorDebito;
	private Integer    idLocalidadeGuia;
	private Integer    idDebitoTipoGuia;
	private Date       dataVencimentoGuia;
	
	public GuiaPagamentoHelper(Integer idGuiaPagamento,
			String nnIdentificadorTrancacao, Date dataParcelamento,
			BigDecimal valorDebito, Integer idLocalidadeGuia, 
			Integer idDebitoTipoGuia, Date dataVencimentoGuia) {
		super();
		this.idGuiaPagamento = idGuiaPagamento;
		this.nnIdentificadorTrancacao = nnIdentificadorTrancacao;
		this.dataParcelamento = dataParcelamento;
		this.valorDebito = valorDebito;
		this.idLocalidadeGuia = idLocalidadeGuia;
		this.idDebitoTipoGuia = idDebitoTipoGuia;
		this.dataVencimentoGuia = dataVencimentoGuia;
	}
	
	public Date getDataVencimentoGuia() {
		return dataVencimentoGuia;
	}

	public void setDataVencimentoGuia(Date dataVencimentoGuia) {
		this.dataVencimentoGuia = dataVencimentoGuia;
	}

	public Integer getIdLocalidadeGuia() {
		return idLocalidadeGuia;
	}

	public void setIdLocalidadeGuia(Integer idLocalidadeGuia) {
		this.idLocalidadeGuia = idLocalidadeGuia;
	}

	public Integer getIdDebitoTipoGuia() {
		return idDebitoTipoGuia;
	}

	public void setIdDebitoTipoGuia(Integer idDebitoTipoGuia) {
		this.idDebitoTipoGuia = idDebitoTipoGuia;
	}

	public GuiaPagamentoHelper() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(Integer idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	public Date getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(Date dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getNnIdentificadorTrancacao() {
		return nnIdentificadorTrancacao;
	}

	public void setNnIdentificadorTrancacao(String nnIdentificadorTrancacao) {
		this.nnIdentificadorTrancacao = nnIdentificadorTrancacao;
	}

	public GuiaPagamento getGuiaPagamento() {
		GuiaPagamento guia = new GuiaPagamento();
		guia.setId(idGuiaPagamento);
		Localidade localidade = new Localidade();
		localidade.setId(idLocalidadeGuia);
		guia.setLocalidade(localidade);
		DebitoTipo debitoTipo = new DebitoTipo();
		debitoTipo.setId(idDebitoTipoGuia);
		guia.setDebitoTipo(debitoTipo);
		guia.setValorDebito(valorDebito);
		guia.setDataVencimento(dataVencimentoGuia);
		return guia;
	}
	
}
