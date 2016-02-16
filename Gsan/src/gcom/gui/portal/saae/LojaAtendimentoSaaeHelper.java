package gcom.gui.portal.saae;

/**
 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual [SB0005] Exibir Dados da
 * Loja
 * 
 * Esta classe auxilia a montagem de Lojas de Atendimento da Compesa para serem
 * exibidas
 * 
 * @author Cesar Medeiros
 * @date 16/09/2015 
 * 
 */
public class LojaAtendimentoSaaeHelper {

	private String nomeLoja;

	private String endereco;

	private String pontoReferencia;

	private String foneFax;

	private String email;

	private String imagem;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFoneFax() {
		return foneFax;
	}

	public void setFoneFax(String foneFax) {
		this.foneFax = foneFax;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
