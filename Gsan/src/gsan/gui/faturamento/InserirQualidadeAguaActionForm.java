/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * R�mulo Aur�lio de Melo Souza Filho
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gsan.gui.faturamento;

import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.faturamento.QualidadeAgua;
import gsan.gui.ActionServletException;
import gsan.operacional.FonteCaptacao;
import gsan.operacional.SistemaAbastecimento;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0596] Inserir Qualidade de Agua
 * 
 * @author K�ssia Albuquerque, R�mulo Aur�lio
 * @date 24/07/2007 , 16/09/2008
 */

public class InserirQualidadeAguaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String referencia;

	private String idLocalidade;

	private String localidadeDescricao;

	private String idSetorComercial;

	private String setorComercialDescricao;

	private String fonteCaptacao;

	private String indiceMensalTurbidez;

	private String padraoTurbidez;

	private String indiceMensalCloroResidual;

	private String padraoCloroResidual;

	private String indiceMensalPH;

	private String padraoPH;

	private String indiceMensalCor;

	private String padraoCor;

	private String indiceMensalFluor;

	private String padraoFluor;

	private String indiceMensalFerro;

	private String padraoFerro;

	private String indiceMensalColiformesTotais;

	private String padraoColiformesTotais;

	private String indiceMensalColiformesFecais;

	private String padraoColiformesFecais;

	private String indiceMensalNitrato;

	private String padraoNitrato;

	private String indiceMensalColiformesTermotolerantes;
	
	private String indiceMensalAlcalinidade;
	
	private String padraoColiformesTermotolerantes;
	
	private String padraoAlcalinidade;

	private String quantidadeTurbidezExigidas;

	private String quantidadeTurbidezAnalisadas;

	private String quantidadeTurbidezConforme;

	private String quantidadeCorExigidas;

	private String quantidadeCorAnalisadas;

	private String quantidadeCorConforme;

	private String quantidadeCloroExigidas;

	private String quantidadeCloroAnalisadas;

	private String quantidadeCloroConforme;

	private String quantidadeFluorExigidas;

	private String quantidadeFluorAnalisadas;

	private String quantidadeFluorConforme;

	private String quantidadeColiformesTotaisExigidas;

	private String quantidadeColiformesTotaisAnalisadas;

	private String quantidadeColiformesTotaisConforme;

	private String quantidadeColiformesFecaisExigidas;

	private String quantidadeColiformesFecaisAnalisadas;

	private String quantidadeColiformesFecaisConforme;

	private String quantidadeColiformesTermotolerantesExigidas;

	private String quantidadeColiformesTermotolerantesAnalisadas;

	private String quantidadeColiformesTermotolerantesConforme;
	
	private String quantidadeAlcalinidadeExigidas;
	
	private String quantidadeAlcalinidadeAnalisadas;
	
	private String quantidadeAlcalinidadeConforme;
	
	private String incluirTodos;
	
	private String sistemaAbastecimento;
	
	private String durezaTotal;
	
	private String quantidadeDurezaTotalExigidas;
	
	private String quantidadeDurezaTotalAnalisada;
	
	private String quantidadeDurezaTotalConforme;
	
	private String quantidadePhExigidas;
	
	private String quantidadePhAnalisada;
	
	private String quantidadePhConforme;
	
	private String descricaoDurezaTotal;
	
	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}

	public String getIncluirTodos() {
		return incluirTodos;
	}

	public void setIncluirTodos(String incluirTodos) {
		this.incluirTodos = incluirTodos;
	}

	public String getQuantidadeCloroAnalisadas() {
		return quantidadeCloroAnalisadas;
	}

	public void setQuantidadeCloroAnalisadas(String quantidadeCloroAnalisadas) {
		this.quantidadeCloroAnalisadas = quantidadeCloroAnalisadas;
	}

	public String getQuantidadeCloroConforme() {
		return quantidadeCloroConforme;
	}

	public void setQuantidadeCloroConforme(String quantidadeCloroConforme) {
		this.quantidadeCloroConforme = quantidadeCloroConforme;
	}

	public String getQuantidadeCloroExigidas() {
		return quantidadeCloroExigidas;
	}

	public void setQuantidadeCloroExigidas(String quantidadeCloroExigidas) {
		this.quantidadeCloroExigidas = quantidadeCloroExigidas;
	}

	public String getQuantidadeColiformesFecaisAnalisadas() {
		return quantidadeColiformesFecaisAnalisadas;
	}

	public void setQuantidadeColiformesFecaisAnalisadas(
			String quantidadeColiformesFecaisAnalisadas) {
		this.quantidadeColiformesFecaisAnalisadas = quantidadeColiformesFecaisAnalisadas;
	}

	public String getQuantidadeColiformesFecaisConforme() {
		return quantidadeColiformesFecaisConforme;
	}

	public void setQuantidadeColiformesFecaisConforme(
			String quantidadeColiformesFecaisConforme) {
		this.quantidadeColiformesFecaisConforme = quantidadeColiformesFecaisConforme;
	}

	public String getQuantidadeColiformesFecaisExigidas() {
		return quantidadeColiformesFecaisExigidas;
	}

	public void setQuantidadeColiformesFecaisExigidas(
			String quantidadeColiformesFecaisExigidas) {
		this.quantidadeColiformesFecaisExigidas = quantidadeColiformesFecaisExigidas;
	}

	public String getQuantidadeColiformesTermotolerantesAnalisadas() {
		return quantidadeColiformesTermotolerantesAnalisadas;
	}

	public void setQuantidadeColiformesTermotolerantesAnalisadas(
			String quantidadeColiformesTermotolerantesAnalisadas) {
		this.quantidadeColiformesTermotolerantesAnalisadas = quantidadeColiformesTermotolerantesAnalisadas;
	}

	public String getQuantidadeColiformesTermotolerantesConforme() {
		return quantidadeColiformesTermotolerantesConforme;
	}

	public void setQuantidadeColiformesTermotolerantesConforme(
			String quantidadeColiformesTermotolerantesConforme) {
		this.quantidadeColiformesTermotolerantesConforme = quantidadeColiformesTermotolerantesConforme;
	}

	public String getQuantidadeColiformesTermotolerantesExigidas() {
		return quantidadeColiformesTermotolerantesExigidas;
	}

	public void setQuantidadeColiformesTermotolerantesExigidas(
			String quantidadeColiformesTermotolerantesExigidas) {
		this.quantidadeColiformesTermotolerantesExigidas = quantidadeColiformesTermotolerantesExigidas;
	}

	public String getQuantidadeColiformesTotaisAnalisadas() {
		return quantidadeColiformesTotaisAnalisadas;
	}

	public void setQuantidadeColiformesTotaisAnalisadas(
			String quantidadeColiformesTotaisAnalisadas) {
		this.quantidadeColiformesTotaisAnalisadas = quantidadeColiformesTotaisAnalisadas;
	}

	public String getQuantidadeColiformesTotaisConforme() {
		return quantidadeColiformesTotaisConforme;
	}

	public void setQuantidadeColiformesTotaisConforme(
			String quantidadeColiformesTotaisConforme) {
		this.quantidadeColiformesTotaisConforme = quantidadeColiformesTotaisConforme;
	}

	public String getQuantidadeColiformesTotaisExigidas() {
		return quantidadeColiformesTotaisExigidas;
	}

	public void setQuantidadeColiformesTotaisExigidas(
			String quantidadeColiformesTotaisExigidas) {
		this.quantidadeColiformesTotaisExigidas = quantidadeColiformesTotaisExigidas;
	}

	public String getQuantidadeCorAnalisadas() {
		return quantidadeCorAnalisadas;
	}

	public void setQuantidadeCorAnalisadas(String quantidadeCorAnalisadas) {
		this.quantidadeCorAnalisadas = quantidadeCorAnalisadas;
	}

	public String getQuantidadeCorConforme() {
		return quantidadeCorConforme;
	}

	public void setQuantidadeCorConforme(String quantidadeCorConforme) {
		this.quantidadeCorConforme = quantidadeCorConforme;
	}

	public String getQuantidadeCorExigidas() {
		return quantidadeCorExigidas;
	}

	public void setQuantidadeCorExigidas(String quantidadeCorExigidas) {
		this.quantidadeCorExigidas = quantidadeCorExigidas;
	}

	public String getQuantidadeFluorAnalisadas() {
		return quantidadeFluorAnalisadas;
	}

	public void setQuantidadeFluorAnalisadas(String quantidadeFluorAnalisadas) {
		this.quantidadeFluorAnalisadas = quantidadeFluorAnalisadas;
	}

	public String getQuantidadeFluorConforme() {
		return quantidadeFluorConforme;
	}

	public void setQuantidadeFluorConforme(String quantidadeFluorConforme) {
		this.quantidadeFluorConforme = quantidadeFluorConforme;
	}

	public String getQuantidadeFluorExigidas() {
		return quantidadeFluorExigidas;
	}

	public void setQuantidadeFluorExigidas(String quantidadeFluorExigidas) {
		this.quantidadeFluorExigidas = quantidadeFluorExigidas;
	}

	public String getQuantidadeTurbidezAnalisadas() {
		return quantidadeTurbidezAnalisadas;
	}

	public void setQuantidadeTurbidezAnalisadas(
			String quantidadeTurbidezAnalisadas) {
		this.quantidadeTurbidezAnalisadas = quantidadeTurbidezAnalisadas;
	}

	public String getQuantidadeTurbidezConforme() {
		return quantidadeTurbidezConforme;
	}

	public void setQuantidadeTurbidezConforme(String quantidadeTurbidezConforme) {
		this.quantidadeTurbidezConforme = quantidadeTurbidezConforme;
	}

	public String getQuantidadeTurbidezExigidas() {
		return quantidadeTurbidezExigidas;
	}

	public void setQuantidadeTurbidezExigidas(String quantidadeTurbidezExigidas) {
		this.quantidadeTurbidezExigidas = quantidadeTurbidezExigidas;
	}

	public String getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(String fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getIndiceMensalCloroResidual() {
		return indiceMensalCloroResidual;
	}

	public void setIndiceMensalCloroResidual(String indiceMensalCloroResidual) {
		this.indiceMensalCloroResidual = indiceMensalCloroResidual;
	}

	public String getIndiceMensalColiformesFecais() {
		return indiceMensalColiformesFecais;
	}

	public void setIndiceMensalColiformesFecais(
			String indiceMensalColiformesFecais) {
		this.indiceMensalColiformesFecais = indiceMensalColiformesFecais;
	}

	public String getIndiceMensalColiformesTotais() {
		return indiceMensalColiformesTotais;
	}

	public void setIndiceMensalColiformesTotais(
			String indiceMensalColiformesTotais) {
		this.indiceMensalColiformesTotais = indiceMensalColiformesTotais;
	}

	public String getIndiceMensalCor() {
		return indiceMensalCor;
	}

	public void setIndiceMensalCor(String indiceMensalCor) {
		this.indiceMensalCor = indiceMensalCor;
	}

	public String getIndiceMensalFerro() {
		return indiceMensalFerro;
	}

	public void setIndiceMensalFerro(String indiceMensalFerro) {
		this.indiceMensalFerro = indiceMensalFerro;
	}

	public String getIndiceMensalFluor() {
		return indiceMensalFluor;
	}

	public void setIndiceMensalFluor(String indiceMensalFluor) {
		this.indiceMensalFluor = indiceMensalFluor;
	}

	public String getIndiceMensalNitrato() {
		return indiceMensalNitrato;
	}

	public void setIndiceMensalNitrato(String indiceMensalNitrato) {
		this.indiceMensalNitrato = indiceMensalNitrato;
	}

	public String getIndiceMensalPH() {
		return indiceMensalPH;
	}

	public void setIndiceMensalPH(String indiceMensalPH) {
		this.indiceMensalPH = indiceMensalPH;
	}

	public String getIndiceMensalTurbidez() {
		return indiceMensalTurbidez;
	}

	public void setIndiceMensalTurbidez(String indiceMensalTurbidez) {
		this.indiceMensalTurbidez = indiceMensalTurbidez;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public String getPadraoCloroResidual() {
		return padraoCloroResidual;
	}

	public void setPadraoCloroResidual(String padraoCloroResidual) {
		this.padraoCloroResidual = padraoCloroResidual;
	}

	public String getPadraoColiformesFecais() {
		return padraoColiformesFecais;
	}

	public void setPadraoColiformesFecais(String padraoColiformesFecais) {
		this.padraoColiformesFecais = padraoColiformesFecais;
	}

	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}

	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}

	public String getPadraoCor() {
		return padraoCor;
	}

	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}

	public String getPadraoFerro() {
		return padraoFerro;
	}

	public void setPadraoFerro(String padraoFerro) {
		this.padraoFerro = padraoFerro;
	}

	public String getPadraoFluor() {
		return padraoFluor;
	}

	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}

	public String getPadraoNitrato() {
		return padraoNitrato;
	}

	public void setPadraoNitrato(String padraoNitrato) {
		this.padraoNitrato = padraoNitrato;
	}

	public String getPadraoPH() {
		return padraoPH;
	}

	public void setPadraoPH(String padraoPH) {
		this.padraoPH = padraoPH;
	}

	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}

	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	public QualidadeAgua setDadosQualidadeAgua(QualidadeAgua qualidadeAgua) {

		Fachada fachada = Fachada.getInstancia();

		// Refer�ncia
		qualidadeAgua.setAnoMesReferencia(new Integer(Util
				.formatarMesAnoParaAnoMesSemBarra(this.getReferencia())));

		// Localidade
		if (getIdLocalidade() != null && !getIdLocalidade().equals("")) {

			Localidade localidade = new Localidade();
			localidade.setId(Integer.parseInt(getIdLocalidade()));
			qualidadeAgua.setLocalidade(localidade);
		}

		// Setor Comercial

		if (getIdSetorComercial() != null
				&& !getIdSetorComercial().toString().trim()
						.equalsIgnoreCase("")) {
			if (getIdLocalidade() != null
					&& !getIdLocalidade().toString().trim()
							.equalsIgnoreCase("")) {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.limparListaParametros();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, new Integer(
								getIdLocalidade())));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(getIdSetorComercial())));

				Collection colecaoSetorComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());
				
				if(colecaoSetorComerciais == null || colecaoSetorComerciais.isEmpty()){
					throw new ActionServletException("atencao.setor_comercial.inexistente");
				}

				SetorComercial setorComercialPesquisa = new SetorComercial();
				setorComercialPesquisa = (SetorComercial) colecaoSetorComerciais
						.iterator().next();
				SetorComercial setorComercial = new SetorComercial();

				setorComercial.setId(setorComercialPesquisa.getId());
				qualidadeAgua.setSetorComercial(setorComercial);

			}

		}

		// Fonte Capta��o
		if(getFonteCaptacao() != null && 
			!getFonteCaptacao().equals("") &&
			!getFonteCaptacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ){
			
			FonteCaptacao fonte = new FonteCaptacao();
			fonte.setId(new Integer(getFonteCaptacao()));
			
			qualidadeAgua.setFonteCaptacao(fonte);
		}
		
		//Sistema de Abastecimento
		if(getSistemaAbastecimento() != null && 
				!getSistemaAbastecimento().equals("") &&
				!getSistemaAbastecimento().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ){
				
				SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
				sistemaAbastecimento.setId(new Integer(getSistemaAbastecimento()));
				
				qualidadeAgua.setSistemaAbastecimento(sistemaAbastecimento);
		}

		// Turbidez
		if (getIndiceMensalTurbidez() != null
				&& !getIndiceMensalTurbidez().equals("")) {

			qualidadeAgua
					.setNumeroIndiceTurbidez(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalTurbidez()));
		}

		// Cloro Residual

		if (getIndiceMensalCloroResidual() != null
				&& !getIndiceMensalCloroResidual().equals("")) {

			qualidadeAgua
					.setNumeroCloroResidual(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalCloroResidual()));
		}

		// PH

		if (getIndiceMensalPH() != null && !getIndiceMensalPH().equals("")) {

			qualidadeAgua.setNumeroIndicePh(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalPH()));
		}

		// Cor

		if (getIndiceMensalCor() != null && !getIndiceMensalCor().equals("")) {

			qualidadeAgua.setNumeroIndiceCor(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalCor()));
		}

		// Fl�or

		if (getIndiceMensalFluor() != null
				&& !getIndiceMensalFluor().equals("")) {

			qualidadeAgua.setNumeroIndiceFluor(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalFluor()));
		}

		// Ferro

		if (getIndiceMensalFerro() != null
				&& !getIndiceMensalFerro().equals("")) {

			qualidadeAgua.setNumeroIndiceFerro(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalFerro()));
		}

		// Coliformes Totais

		if (getIndiceMensalColiformesTotais() != null
				&& !getIndiceMensalColiformesTotais().equals("")) {

			qualidadeAgua
					.setNumeroIndiceColiformesTotais(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalColiformesTotais()));
		}

		// Coliformes Fecais

		if (getIndiceMensalColiformesFecais() != null
				&& !getIndiceMensalColiformesFecais().equals("")) {

			qualidadeAgua
					.setNumeroIndiceColiformesFecais(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalColiformesFecais()));
		}

		// Nitrato

		if (getIndiceMensalNitrato() != null
				&& !getIndiceMensalNitrato().equals("")) {

			qualidadeAgua.setNumeroNitrato(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalNitrato()));
		}

		// Coliforms TermoTolerantes

		if (getIndiceMensalColiformesTermotolerantes() != null
				&& !getIndiceMensalColiformesTermotolerantes().equals("")) {

			qualidadeAgua
					.setNumeroIndiceColiformesTermotolerantes(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalColiformesTermotolerantes()));
		}
		
		// Alcalinidade
		
		if (getIndiceMensalAlcalinidade() != null
				&& !getIndiceMensalAlcalinidade().equals("")){
			
			qualidadeAgua.setNumeroIndiceAlcalinidade(Util.formatarMoedaRealparaBigDecimal(getIndiceMensalAlcalinidade()));
			
		}
		
		// Dureza Total
		if(getDurezaTotal() != null && !getDurezaTotal().equals("")){
			qualidadeAgua.setIndiceDurezaTotal(new Integer(getDurezaTotal()));
		}

		// Quantidade Turbidez Analisadas

		if (getQuantidadeTurbidezAnalisadas() != null
				&& !getQuantidadeTurbidezAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeTurbidezAnalisadas(new Integer(
					getQuantidadeTurbidezAnalisadas()));
		}

		// Quantidade Turbidez Exigidas

		if (getQuantidadeTurbidezExigidas() != null
				&& !getQuantidadeTurbidezExigidas().equals("")) {

			qualidadeAgua.setQuantidadeTurbidezExigidas(new Integer(
					getQuantidadeTurbidezExigidas()));
		}

		// Quantidade Turbidez Conforme

		if (getQuantidadeTurbidezConforme() != null
				&& !getQuantidadeTurbidezConforme().equals("")) {

			qualidadeAgua.setQuantidadeTurbidezConforme(new Integer(
					getQuantidadeTurbidezConforme()));
		}

		// Quantidade Cor Exigidas

		if (getQuantidadeCorExigidas() != null
				&& !getQuantidadeCorExigidas().equals("")) {

			qualidadeAgua.setQuantidadeCorExigidas(new Integer(
					getQuantidadeCorExigidas()));
		}

		// Quantidade Cor Analisadas

		if (getQuantidadeCorAnalisadas() != null
				&& !getQuantidadeCorAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeCorAnalisadas(new Integer(
					getQuantidadeCorAnalisadas()));
		}

		// Quantidade Cor Conforme

		if (getQuantidadeCorConforme() != null
				&& !getQuantidadeCorConforme().equals("")) {

			qualidadeAgua.setQuantidadeCorConforme(new Integer(
					getQuantidadeCorConforme()));
		}

		// Quantidade Cloro Exigidas

		if (getQuantidadeCloroExigidas() != null
				&& !getQuantidadeCloroExigidas().equals("")) {

			qualidadeAgua.setQuantidadeCloroExigidas(new Integer(
					getQuantidadeCloroExigidas()));
		}

		// Quantidade Cloro Analisadas

		if (getQuantidadeCloroAnalisadas() != null
				&& !getQuantidadeCloroAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeCloroAnalisadas(new Integer(
					getQuantidadeCloroAnalisadas()));
		}

		// Quantidade Cloro Conforme

		if (getQuantidadeCloroConforme() != null
				&& !getQuantidadeCloroConforme().equals("")) {

			qualidadeAgua.setQuantidadeCloroConforme(new Integer(
					getQuantidadeCloroConforme()));
		}

		// Quantidade Fluor Exigidas

		if (getQuantidadeFluorExigidas() != null
				&& !getQuantidadeFluorExigidas().equals("")) {

			qualidadeAgua.setQuantidadeFluorExigidas(new Integer(
					getQuantidadeFluorExigidas()));
		}

		// Quantidade Fluor Analisadas

		if (getQuantidadeFluorAnalisadas() != null
				&& !getQuantidadeFluorAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeFluorAnalisadas(new Integer(
					getQuantidadeFluorAnalisadas()));
		}

		// Quantidade Fluor Conforme

		if (getQuantidadeFluorConforme() != null
				&& !getQuantidadeFluorConforme().equals("")) {

			qualidadeAgua.setQuantidadeFluorConforme(new Integer(
					getQuantidadeFluorConforme()));
		}

		// Quantidade Coliformes Totais Exigidas

		if (getQuantidadeColiformesTotaisExigidas() != null
				&& !getQuantidadeColiformesTotaisExigidas().equals("")) {

			qualidadeAgua.setQuantidadeColiformesTotaisExigidas(new Integer(
					getQuantidadeColiformesTotaisExigidas()));
		}

		// Quantidade Coliformes Totais Analisadas

		if (getQuantidadeColiformesTotaisAnalisadas() != null
				&& !getQuantidadeColiformesTotaisAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeColiformesTotaisAnalisadas(new Integer(
					getQuantidadeColiformesTotaisAnalisadas()));
		}

		// Quantidade Coliformes Totais Conforme

		if (getQuantidadeColiformesTotaisConforme() != null
				&& !getQuantidadeColiformesTotaisConforme().equals("")) {

			qualidadeAgua.setQuantidadeColiformesTotaisConforme(new Integer(
					getQuantidadeColiformesTotaisConforme()));
		}

		// Quantidade Coliformes Fecais Exigidas

		if (getQuantidadeColiformesFecaisExigidas() != null
				&& !getQuantidadeColiformesFecaisExigidas().equals("")) {

			qualidadeAgua.setQuantidadeColiformesFecaisExigidas(new Integer(
					getQuantidadeColiformesFecaisExigidas()));
		}

		// Quantidade Coliformes Fecais Analisadas

		if (getQuantidadeColiformesFecaisAnalisadas() != null
				&& !getQuantidadeColiformesFecaisAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeColiformesFecaisAnalisadas(new Integer(
					getQuantidadeColiformesFecaisAnalisadas()));
		}

		// Quantidade Coliformes Fecais Conforme

		if (getQuantidadeColiformesFecaisConforme() != null
				&& !getQuantidadeColiformesFecaisConforme().equals("")) {

			qualidadeAgua.setQuantidadeColiformesFecaisConforme(new Integer(
					getQuantidadeColiformesFecaisConforme()));
		}

		// Quantidade Coliformes Termotolerantes Exigidas

		if (getQuantidadeColiformesTermotolerantesExigidas() != null
				&& !getQuantidadeColiformesTermotolerantesExigidas().equals("")) {

			qualidadeAgua
					.setQuantidadeColiformesTermotolerantesExigidas(new Integer(
							getQuantidadeColiformesTermotolerantesExigidas()));
		}

		// Quantidade Coliformes Termotolerantes Analisadas

		if (getQuantidadeColiformesTermotolerantesAnalisadas() != null
				&& !getQuantidadeColiformesTermotolerantesAnalisadas().equals(
						"")) {

			qualidadeAgua
					.setQuantidadeColiformesTermotolerantesAnalisadas(new Integer(
							getQuantidadeColiformesTermotolerantesAnalisadas()));
		}

		// Quantidade Coliformes Termotolerantes Conforme

		if (getQuantidadeColiformesTermotolerantesConforme() != null
				&& !getQuantidadeColiformesTermotolerantesConforme().equals("")) {

			qualidadeAgua
					.setQuantidadeColiformesTermotolerantesConforme(new Integer(
							getQuantidadeColiformesTermotolerantesConforme()));
		}
		
		// Quantidade Alcalinidade Exigida
		
		if(getQuantidadeAlcalinidadeExigidas() != null
				&& !getQuantidadeAlcalinidadeExigidas().equals("")){
			
			qualidadeAgua.setQuantidadeAlcalinidadeExigidas(new Integer(
					getQuantidadeAlcalinidadeExigidas()));
			
		}
		
		// Quantidade Alcalinidade Analisadas
		
		if(getQuantidadeAlcalinidadeAnalisadas() != null
				&& !getQuantidadeAlcalinidadeAnalisadas().equals("")){
			
			qualidadeAgua.setQuantidadeAlcalinidadeAnalisadas(new Integer(
					getQuantidadeAlcalinidadeAnalisadas()));
			
		}
		
		// Quantidade Alcalinidade Conforme
		
		if(getQuantidadeAlcalinidadeConforme() != null
				&& !getQuantidadeAlcalinidadeConforme().equals("")){
			
			qualidadeAgua.setQuantidadeAlcalinidadeConforme(new Integer(
					getQuantidadeAlcalinidadeConforme()));
			
		}
		
		// Quantidade Ph Exigida
		if(getQuantidadeFluorExigidas() != null 
				&& !getQuantidadePhExigidas().equals("")){
			
			qualidadeAgua.setQuantidadePhExigida(
				new Integer(getQuantidadePhExigidas()));
		}
		
		// Quantidade Ph Analisada
		if(getQuantidadePhAnalisada() != null 
				&& !getQuantidadePhAnalisada().equals("")){
			qualidadeAgua.setQuantidadePhAnalisada(
				new Integer(getQuantidadePhAnalisada()));
		}		
		
		// Quantidade Ph Conforme
		if(getQuantidadePhConforme() != null 
			&& !getQuantidadePhConforme().equals("")){
				
			qualidadeAgua.setQuantidadePhConforme(
				new Integer(getQuantidadePhConforme()));
		}
			
		// Quantidade Dureza Total Analisada	
		if(getQuantidadeDurezaTotalAnalisada() != null &&
				!getQuantidadeDurezaTotalAnalisada().equals("")){
			
			qualidadeAgua.setQuantidadeDurezaAnalisada(
				new Integer(getQuantidadeDurezaTotalAnalisada()));
		}
		
		// Quantidade Dureza Total Conforme 
		if(getQuantidadeDurezaTotalConforme() != null &&
				!getQuantidadeDurezaTotalConforme().equals("")){
		
			qualidadeAgua.setQuantidadeDurezaConforme(
				new Integer(getQuantidadeDurezaTotalConforme()));
		}
		
		// Quantidade Dureza Total Exigida
		if(getQuantidadeDurezaTotalExigidas() != null &&
				!getQuantidadeDurezaTotalExigidas().equals("")){
			
			qualidadeAgua.setQuantidadeDurezaExigida(
				new Integer(getQuantidadeDurezaTotalExigidas()));
		}
		
		if(getDurezaTotal() != null && !getDurezaTotal().equals("")){
			qualidadeAgua.setIndiceDurezaTotal(
				new Integer(getDurezaTotal().replace(",", "").replace(".", "")));
		}
		
		return qualidadeAgua;
	}

	public String getIndiceMensalColiformesTermotolerantes() {
		return indiceMensalColiformesTermotolerantes;
	}

	public void setIndiceMensalColiformesTermotolerantes(
			String indiceMensalColiformesTermotolerantes) {
		this.indiceMensalColiformesTermotolerantes = indiceMensalColiformesTermotolerantes;
	}

	public String getPadraoColiformesTermotolerantes() {
		return padraoColiformesTermotolerantes;
	}

	public void setPadraoColiformesTermotolerantes(
			String padraoColiformesTermotolerantes) {
		this.padraoColiformesTermotolerantes = padraoColiformesTermotolerantes;
	}

	public String getIndiceMensalAlcalinidade() {
		return indiceMensalAlcalinidade;
	}

	public void setIndiceMensalAlcalinidade(String indiceMensalAlcalinidade) {
		this.indiceMensalAlcalinidade = indiceMensalAlcalinidade;
	}

	public String getPadraoAlcalinidade() {
		return padraoAlcalinidade;
	}

	public void setPadraoAlcalinidade(String padraoAlcalinidade) {
		this.padraoAlcalinidade = padraoAlcalinidade;
	}

	public String getQuantidadeAlcalinidadeAnalisadas() {
		return quantidadeAlcalinidadeAnalisadas;
	}

	public void setQuantidadeAlcalinidadeAnalisadas(
			String quantidadeAlcalinidadeAnalisadas) {
		this.quantidadeAlcalinidadeAnalisadas = quantidadeAlcalinidadeAnalisadas;
	}

	public String getQuantidadeAlcalinidadeConforme() {
		return quantidadeAlcalinidadeConforme;
	}

	public void setQuantidadeAlcalinidadeConforme(
			String quantidadeAlcalinidadeConforme) {
		this.quantidadeAlcalinidadeConforme = quantidadeAlcalinidadeConforme;
	}

	public String getQuantidadeAlcalinidadeExigidas() {
		return quantidadeAlcalinidadeExigidas;
	}

	public void setQuantidadeAlcalinidadeExigidas(
			String quantidadeAlcalinidadeExigidas) {
		this.quantidadeAlcalinidadeExigidas = quantidadeAlcalinidadeExigidas;
	}

	public String getDurezaTotal() {
		return durezaTotal;
	}

	public void setDurezaTotal(String durezaTotal) {
		this.durezaTotal = durezaTotal;
	}

	public String getQuantidadeDurezaTotalExigidas() {
		return quantidadeDurezaTotalExigidas;
	}

	public void setQuantidadeDurezaTotalExigidas(String quantidadeDurezaTotalExigidas) {
		this.quantidadeDurezaTotalExigidas = quantidadeDurezaTotalExigidas;
	}

	public String getQuantidadeDurezaTotalAnalisada() {
		return quantidadeDurezaTotalAnalisada;
	}

	public void setQuantidadeDurezaTotalAnalisada(String quantidadeDurezaTotalAnalisada) {
		this.quantidadeDurezaTotalAnalisada = quantidadeDurezaTotalAnalisada;
	}

	public String getQuantidadeDurezaTotalConforme() {
		return quantidadeDurezaTotalConforme;
	}

	public void setQuantidadeDurezaTotalConforme(String quantidadeDurezaTotalConforme) {
		this.quantidadeDurezaTotalConforme = quantidadeDurezaTotalConforme;
	}

	public String getQuantidadePhExigidas() {
		return quantidadePhExigidas;
	}

	public void setQuantidadePhExigidas(String quantidadePhExigidas) {
		this.quantidadePhExigidas = quantidadePhExigidas;
	}

	public String getQuantidadePhAnalisada() {
		return quantidadePhAnalisada;
	}

	public void setQuantidadePhAnalisada(String quantidadePhAnalisada) {
		this.quantidadePhAnalisada = quantidadePhAnalisada;
	}

	public String getQuantidadePhConforme() {
		return quantidadePhConforme;
	}

	public void setQuantidadePhConforme(String quantidadePhConforme) {
		this.quantidadePhConforme = quantidadePhConforme;
	}

	public String getDescricaoDurezaTotal() {
		return descricaoDurezaTotal;
	}

	public void setDescricaoDurezaTotal(String descricaoDurezaTotal) {
		this.descricaoDurezaTotal = descricaoDurezaTotal;
	}




}