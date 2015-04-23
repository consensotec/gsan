package gsan.gui.portal.caer;

import org.apache.struts.validator.ValidatorActionForm;

/**  
 * @author Nathalia Santos
 * @date 17/06/2012
 */
public class ExibirInformacoesPortalCaerActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String descrissaoLeiIndividualizacao;
	private String descrissaoNormaCO;
	private String descrissaoNormaCM;

	public String getDescrissaoLeiIndividualizacao() {
		return descrissaoLeiIndividualizacao;
	}

	public void setDescrissaoLeiIndividualizacao(String descrissaoLeiIndividualizacao) {
		this.descrissaoLeiIndividualizacao = descrissaoLeiIndividualizacao;
	}

	public String getDescrissaoNormaCM() {
		return descrissaoNormaCM;
	}

	public void setDescrissaoNormaCM(String descrissaoNormaCM) {
		this.descrissaoNormaCM = descrissaoNormaCM;
	}

	public String getDescrissaoNormaCO() {
		return descrissaoNormaCO;
	}

	public void setDescrissaoNormaCO(String descrissaoNormaCO) {
		this.descrissaoNormaCO = descrissaoNormaCO;
	}
	
	
}