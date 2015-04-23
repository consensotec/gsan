package gsan.relatorio.atendimentopublico.bean;

import gsan.atendimentopublico.portal.AcessoLojaVirtual;


/**
 * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
 * 
 * @author Flávio Ferreira
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
			tipoAtendimento = "Negociação de Débito";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.ESTRUTURA_TARIFARIA)){
			tipoAtendimento = "Estrutura Tarifária";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.VALIDAR_CERTIDAO_NEGATIVA)){
			tipoAtendimento = "Validar Certidão Negativa de Débito";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.VOLUME_CONSUMIDO)){
			tipoAtendimento = "Volume Consumido";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.TABELA_SERVICOS)){
			tipoAtendimento = "Tabela Serviços";
		}else  if(tipoAtendimento.equals(AcessoLojaVirtual.ONDE_PAGAR_FATURA)){
			tipoAtendimento = "Onde Pagar sua Fatura";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.ORIENTACOES_CLIENTE)){
			tipoAtendimento = "Orientações do Cliente";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.DEBITO_AUTOMATICO)){
			tipoAtendimento = "Débito Automatico em Conta";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.SEGUNDA_VIA_CONTA)){
			tipoAtendimento = "Segunda via de Conta";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.DECLARACAO_ANUAL_QUITACAO_DEBITO)){
			tipoAtendimento = "Declaração Anual Quitação de Débito";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.OUTROS_SERVICOS)){
			tipoAtendimento = "Outros Serviços";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.CONSULTAR_PAGAMENTOS)){
			tipoAtendimento = "Consultar Pagamento";;
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.ACOMPANHAR_REGISTRO_ATENDIMENTO)){
			tipoAtendimento = "Acompanhar Registro de Atendimento";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.CONSULTAR_CONSUMO_HISTORICO)){
			tipoAtendimento = "Consultar Historico de Consumo";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.ALTERAR_VENCIMENTO)){
			tipoAtendimento = "Alterar Vencimento de Conta";
		}else if(tipoAtendimento.equals(AcessoLojaVirtual.CERTIDAO_NEGATIVA_DEBITOS)){
			tipoAtendimento = "Certidão Negativa de Débitos";
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
