package gcom.relatorio.mobile.execucaoOrdemServico;

import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.relatorio.RelatorioBean;

import java.util.List;

/**
 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
 * 
 * @author Jean Varela
 * @date 14/12/2015
 */

public class RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean implements RelatorioBean {
	
	private List<Integer> idsLocalidade;
	private String        idsLocalidadeFormatada;
	
	private List<Integer> idsSetor;
	private String 		  idsSetorFormatado;
	
	private List<Integer> idsRota;
	private String        idsRotaFormatada;
	
	private List<Integer> nnQuadras;
	private String        nnQuadrasFormatada;
	 
	private String  nomeAgenteComercial;
	private String  descricaoSituacaoArquivo;
	private String  dataLiberacao;

	private Integer quantidadeEnviada;
	private Integer quantidadeRecebida;
	
	
	public RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean(ArquivoTxtOSCobrancaSmartphoneHelper helper) {
	
		this.idsLocalidade = helper.getIdsLocalidade();
		this.idsSetor = helper.getIdsSetor();
		this.idsRota = helper.getIdsRota();
		this.dataLiberacao = helper.getDataLiberacao();
		this.quantidadeEnviada =  helper.getQtdOrdemServico();
		this.quantidadeRecebida = helper.getQuantidadeOSRecebida();
		this.nnQuadras = helper.getNnQuadras();
		
		if (helper.getNomeFuncionario() != null){
			this.nomeAgenteComercial = helper.getNomeFuncionario();
		}else{
			this.nomeAgenteComercial = helper.getNomeCliente();
		}
		
		this.descricaoSituacaoArquivo = helper.getSituacao();
	}

	public String getIdsLocalidadeFormatada() {
		if (idsLocalidade != null && idsLocalidade.size() > 0){
			StringBuffer texto = new StringBuffer();
			
			int quantidadeSetores = idsLocalidade.size() < 4 ? idsLocalidade.size() : 4;
			
			for(int i = 0; i < quantidadeSetores; i++){
				texto.append(idsLocalidade.get(i));
				
				if (idsLocalidade.size() > 1 &&  i < quantidadeSetores - 1)
					texto.append(", ");
			}
			
			if (idsLocalidade.size() > 4 ){
				texto.append(",");
				texto.append(" ...");
			}
			
			
			idsLocalidadeFormatada = texto.toString();
		}
		return idsLocalidadeFormatada;
	}

	public void setIdsLocalidadeFormatada(String idsLocalidadeFormatada) {
		this.idsLocalidadeFormatada = idsLocalidadeFormatada;
	}

	public String getIdsSetorFormatado() {		
		if (idsSetor != null && idsSetor.size() > 0){
			StringBuffer texto = new StringBuffer();
			
			int quantidadeSetores = idsSetor.size() < 2 ? idsSetor.size() : 2;
			
			for(int i = 0; i < quantidadeSetores; i++){
				texto.append(idsSetor.get(i));
				
				if (idsSetor.size() > 1 &&  i < quantidadeSetores - 1)
					texto.append(", ");
			}
			
			if (idsSetor.size() > 2 ){
				texto.append(",");
				texto.append("...");
			}
			
			
			idsSetorFormatado = texto.toString();
		}else{
			idsSetorFormatado = "";
		}
		return idsSetorFormatado;
	}

	public void setIdsSetorFormatado(String idsSetorFormatado) {
		this.idsSetorFormatado = idsSetorFormatado;
	}

	public String getIdsRotaFormatada() {
		if (idsRota != null && idsRota.size() > 0){
			StringBuffer texto = new StringBuffer();
			
			int quantidadeRotas = idsRota.size() < 2 ? idsRota.size() : 2;
			
			for(int i = 0; i < quantidadeRotas; i++){
				texto.append(idsRota.get(i));
				
				if (idsRota.size() > 1 &&  i < quantidadeRotas - 1)
					texto.append(", ");
			}
			
			if (idsRota.size() > 2){
				texto.append(",");
				texto.append(" ...");
			}
			
			idsRotaFormatada = texto.toString();
		}else{
			idsRotaFormatada = "";
		}
		
		return idsRotaFormatada;
	}

	public void setIdsRotaFormatada(String idsRotaFormatada) {
		this.idsRotaFormatada = idsRotaFormatada;
	}

	public String getNomeAgenteComercial() {
		return nomeAgenteComercial;
	}

	public void setNomeAgenteComercial(String nomeAgenteComercial) {
		this.nomeAgenteComercial = nomeAgenteComercial;
	}

	public String getDescricaoSituacaoArquivo() {
		return descricaoSituacaoArquivo;
	}

	public void setDescricaoSituacaoArquivo(String descricaoSituacaoArquivo) {
		this.descricaoSituacaoArquivo = descricaoSituacaoArquivo;
	}

	public String getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(String dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public Integer getQuantidadeEnviada() {
		return quantidadeEnviada;
	}

	public void setQuantidadeEnviada(Integer quantidadeEnviada) {
		this.quantidadeEnviada = quantidadeEnviada;
	}

	public Integer getQuantidadeRecebida() {
		return quantidadeRecebida;
	}

	public void setQuantidadeRecebida(Integer quantidadeRecebida) {
		this.quantidadeRecebida = quantidadeRecebida;
	}

	public List<Integer> getNnQuadras() {
		return nnQuadras;
	}

	public void setNnQuadras(List<Integer> nnQuadras) {
		this.nnQuadras = nnQuadras;
	}

	public String getNnQuadrasFormatada() {
		if (nnQuadras != null && nnQuadras.size() > 0){
			StringBuffer texto = new StringBuffer();
			
			int quantidadeQuadras = nnQuadras.size() < 2 ? nnQuadras.size() : 2;
			
			for(int i = 0; i < quantidadeQuadras; i++){
				texto.append(nnQuadras.get(i));
				
				if (nnQuadras.size() > 1 &&  i < quantidadeQuadras - 1)
					texto.append(", ");
			}
			
			if (nnQuadras.size() > 2){
				texto.append(",");
				texto.append(" ...");
			}
			
			nnQuadrasFormatada = texto.toString();
		}else{
			nnQuadrasFormatada = "";
		}
		
		return nnQuadrasFormatada;
  }
	
	
	
}
