package gcom.mobile.execucaoordemservico.bean;

import gcom.util.Util;

import java.util.Date;
import java.util.List;

/**
 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
 *
 * @author Jean Varela
 * @throws ErroRepositorioException 
 * @date   16/11/2015	
 */
public class ArquivoTxtOSCobrancaSmartphoneHelper {

	private Integer 	  idArquivo;
	
	private Integer 	  idLocalidade;
	private List<Integer> idsLocalidade;
	private String        idLocalidadeHint;
	
	private Integer qtdOrdemServico;
	
	private Integer 	  idSetor;
	private List<Integer> idsSetor;
	private String        idSetorHint;
	
	private Integer 	  idRota;
	private List<Integer> idsRota;
	private String        idRotaHint;
	
	private Integer       nnQuadra;
	private List<Integer> nnQuadras;
	private String        nnQuadraHint;
	
	private long    imei;
	private String  mac;
	
	private String  nomeFuncionario;
	private String nomeCliente;
	
	private Integer idSituacao;
	private String  situacao;
	
	private Date  dataLiberacao;
	
	private Integer idLeiturista;	
	
	private Integer quantidadeOSRecebida;
	
	public long getImei() {
		return imei;
	}

	public void setImei(long imei) {
		this.imei = imei;
	}
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public ArquivoTxtOSCobrancaSmartphoneHelper(){}
	
	public Integer getIdArquivo() {
		return idArquivo;
	}
	
	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
	public Integer getQtdOrdemServico() {
		return qtdOrdemServico;
	}
	
	public void setQtdOrdemServico(Integer qtdOrdemServico) {
		this.qtdOrdemServico = qtdOrdemServico;
	}
	
	public Integer getIdSituacao() {
		return idSituacao;
	}
	
	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getDataLiberacao() {
		return Util.formatarData(dataLiberacao);
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public Integer getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<Integer> getIdsLocalidade() {
		return idsLocalidade;
	}

	public void setIdsLocalidade(List<Integer> idsLocalidade) {
		this.idsLocalidade = idsLocalidade;
	}

	public List<Integer> getIdsSetor() {
		return idsSetor;
	}

	public void setIdsSetor(List<Integer> idsSetor) {
		this.idsSetor = idsSetor;
	}

	public List<Integer> getIdsRota() {
		return idsRota;
	}

	public void setIdsRota(List<Integer> idsRota) {
		this.idsRota = idsRota;
	}
	
	public Integer getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(Integer idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getQuantidadeOSRecebida() {
		return quantidadeOSRecebida;
	}

	public void setQuantidadeOSRecebida(Integer quantidadeOSRecebida) {
		this.quantidadeOSRecebida = quantidadeOSRecebida;
	}

	public Integer getNnQuadra() {
		return nnQuadra;
	}

	public void setNnQuadra(Integer nnQuadra) {
		this.nnQuadra = nnQuadra;
	}

	public List<Integer> getNnQuadras() {
		return nnQuadras;
	}

	public void setNnQuadras(List<Integer> nnQuadras) {
		this.nnQuadras = nnQuadras;
	}

	public String getNnQuadraHint() {
		if (nnQuadras != null && nnQuadras.size() > 0){
			StringBuffer texto = new StringBuffer();
			
			for(int i = 0; i < nnQuadras.size(); i++){
				texto.append(nnQuadras.get(i));
				
				if (i == 0 && nnQuadras.size() > 1)
					texto.append(", ");
				
				if (i > 0){
					if (i < nnQuadras.size() - 1){
						if (i % 10 != 0){
							texto.append(", ");
						}else{
							texto.append("</br>");	
						}						
					}
				}
			}
			
			nnQuadraHint = texto.toString();
		}
		return nnQuadraHint;
	}

	public String getIdSetorHint() {
		if (idsSetor != null && idsSetor.size() > 0){
			StringBuffer texto = new StringBuffer();
			
			for(int i = 0; i < idsSetor.size(); i++){
				texto.append(idsSetor.get(i));
				
				if (i == 0 && idsSetor.size() > 1)
					texto.append(", ");
				
				if (i > 0){
					if (i < idsSetor.size() - 1){
						if (i % 10 != 0){
							texto.append(", ");
						}else{
							texto.append("</br>");	
						}						
					}
				}
			}
			
			idSetorHint = texto.toString();
		}
		
		return idSetorHint;
	}

	public String getIdRotaHint() {
		if (idsRota != null && idsRota.size() > 0){
			StringBuffer texto = new StringBuffer();
			
			for(int i = 0; i < idsRota.size(); i++){
				texto.append(idsRota.get(i));
				
				if (i == 0 && idsRota.size() > 1)
					texto.append(", ");
				
				if (i > 0){
					if (i < idsRota.size() - 1){
						if (i % 10 != 0){
							texto.append(", ");
						}else{
							texto.append("</br>");	
						}						
					}
				}
			}
			
			idRotaHint = texto.toString();
		}
		
		return idRotaHint;
	}

	public String getIdLocalidadeHint() {
		
		if (idsLocalidade != null && idsLocalidade.size() > 0){
			StringBuffer texto = new StringBuffer();
			
			for(int i = 0; i < idsLocalidade.size(); i++){
				texto.append(idsLocalidade.get(i));
				
				if (i == 0 && idsLocalidade.size() > 1)
					texto.append(", ");
				
				if (i > 0){
					if (i < idsLocalidade.size() - 1){
						if (i % 10 != 0){
							texto.append(", ");
						}else{
							texto.append("</br>");	
						}						
					}
				}
			}
			
			idLocalidadeHint = texto.toString();
		}
		
		return idLocalidadeHint;
	}
}
