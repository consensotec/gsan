package gsan.relatorio.atendimentopublico.bean;

import gsan.atendimentopublico.portal.AcessoLojaVirtual;


/**
 * [UC1275] Gerar Relat�rio Quantidade de Acessos Loja Virtual
 * 
 * @author Fl�vio Ferreira
 * @date 30/09/2013
 */

public class AcessoLojaVirtualHelper {
	
	private String codigoTipoAtendimento;
	
	private String tipoAtendimento;
	
	private String quantidade;
	
	private String dataAtendimento;
	
	private String ipAcesso;
	
	private String qtdServicosExecutados;
	
	private String indicadorServicoExecutado;
	
	public String getTipoAtendimento(){
		return tipoAtendimento;
	}
	
	public void setTipoAtendimento(String tipoAtendimento){
		
		if(tipoAtendimento.equals(AcessoLojaVirtual.LOJAS_ATENDIMENTO_PRESENCIAL)){
			tipoAtendimento = "Lojas Atendimento Presencial";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.TELE_ATENDIMENTO)){
			tipoAtendimento = "Tele Atendimento";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.OUVIDORIA)){
			tipoAtendimento = "Ouvidoria";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.NEGOCIACAO_DEBITO)){
			tipoAtendimento = "Negocia��o de D�bito";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.ESTRUTURA_TARIFARIA)){
			tipoAtendimento = "Estrutura Tarif�ria";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.VALIDAR_CERTIDAO_NEGATIVA)){
			tipoAtendimento = "Validar Certid�o Negativa de D�bito";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.VOLUME_CONSUMIDO)){
			tipoAtendimento = "Volume Consumido";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.TABELA_SERVICOS)){
			tipoAtendimento = "Tabela Servi�os";
		}else  if(tipoAtendimento.equals(AcessoLojaVirtual.ONDE_PAGAR_FATURA)){
			tipoAtendimento = "Onde Pagar sua Fatura";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.ORIENTACOES_CLIENTE)){
			tipoAtendimento = "Orienta��es do Cliente";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.DEBITO_AUTOMATICO)){
			tipoAtendimento = "D�bito Automatico em Conta";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.SEGUNDA_VIA_CONTA)){
			tipoAtendimento = "Segunda via de Conta";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.DECLARACAO_ANUAL_QUITACAO_DEBITO)){
			tipoAtendimento = "Declara��o Anual Quita��o de D�bito";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.OUTROS_SERVICOS)){
			tipoAtendimento = "Outros Servi�os";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.CONSULTAR_PAGAMENTOS)){
			tipoAtendimento = "Consultar Pagamento";;
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.ACOMPANHAR_REGISTRO_ATENDIMENTO)){
			tipoAtendimento = "Acompanhar Registro de Atendimento";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.CONSULTAR_CONSUMO_HISTORICO)){
			tipoAtendimento = "Consultar Historico de Consumo";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.ALTERAR_VENCIMENTO)){
			tipoAtendimento = "Alterar Vencimento de Conta";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.CERTIDAO_NEGATIVA_DEBITOS)){
			tipoAtendimento = "Certid�o Negativa de D�bitos";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.RECEBIMENTO_FATURA_EMAIL)){
			tipoAtendimento = "Recebimento de Fatura por E-MAIL";
		}
		
		this.tipoAtendimento = tipoAtendimento;
	}

	public String getCodigoTipoAtendimento() {
		return codigoTipoAtendimento;
	}

	public void setCodigoTipoAtendimento(String codigoTipoAtendimento) {
		this.codigoTipoAtendimento = codigoTipoAtendimento;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getIpAcesso() {
		return ipAcesso;
	}

	public void setIpAcesso(String ipAcesso) {
		this.ipAcesso = ipAcesso;
	}

	public String getQtdServicosExecutados() {
		return qtdServicosExecutados;
	}

	public void setQtdServicosExecutados(String qtdServicosExecutados) {
		this.qtdServicosExecutados = qtdServicosExecutados;
	}

	public String getIndicadorServicoExecutado() {
		return indicadorServicoExecutado;
	}

	public void setIndicadorServicoExecutado(String indicadorServicoExecutado) {
		this.indicadorServicoExecutado = indicadorServicoExecutado;
	}
	
	

}
