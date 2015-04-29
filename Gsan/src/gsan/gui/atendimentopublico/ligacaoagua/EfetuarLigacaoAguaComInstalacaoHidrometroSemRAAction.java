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
package gsan.gui.atendimentopublico.ligacaoagua;

import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.faturamento.consumotarifa.ConsumoTarifa;
import gsan.faturamento.consumotarifa.FiltroConsumoTarifa;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.atendimentopublico.EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm;
import gsan.interceptor.RegistradorOperacao;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.micromedicao.hidrometro.HidrometroSituacao;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction extends
		GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm form = (EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		LigacaoAgua ligacaoAgua = this.setDadosLigacaoAgua(form, fachada, form.getMatriculaImovel());
		Imovel imovel = new Imovel();
		imovel.setId(new Integer(form.getMatriculaImovel()));
		imovel.setUltimaAlteracao(new Date());
		ligacaoAgua.setImovel(imovel);
		ligacaoAgua.setUltimaAlteracao(new Date());
		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(LigacaoAguaSituacao.LIGADO);
		imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		ligacaoAgua.setId(imovel.getId());

		/*
		 * [UC0107] Registrar Transa��o
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LIGACAO_AGUA_SEM_RA_EFETUAR,
				imovel.getId(), imovel.getId(),
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// [UC0107] -Fim- Registrar Transa��o	
		
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
		
		if (form.getNumeroHidrometro() != null
				&& !form.getNumeroHidrometro().trim().equals("")) {
			
			hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
			hidrometroInstalacaoHistorico = this.setDadosHidrometroInstalacaoHistorico(
							hidrometroInstalacaoHistorico, form);

//			fachada.inserir(ligacaoAgua);
//			fachada.atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(imovel.getId(), null);
//			
//			hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
//			Integer idHidrometroInstalacaoHistorico = (Integer)fachada.inserir(hidrometroInstalacaoHistorico);
//			hidrometroInstalacaoHistorico.setId(idHidrometroInstalacaoHistorico);
//			ligacaoAgua.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
//			fachada.atualizar(ligacaoAgua);
//			fachada.atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(null,
//					hidrometroInstalacaoHistorico.getHidrometro().getId());
		} 
//		else {
//			fachada.inserir(ligacaoAgua);
//			fachada.atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(imovel
//					.getId(), null);
//		}
	
		registradorOperacao.registrarOperacao(ligacaoAgua);
		if(hidrometroInstalacaoHistorico != null){
			registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);
		}
		
		fachada.efetuarLigacaoAguaComInstalacaoHidrometroSemRA(
				imovel.getId(), ligacaoAgua, hidrometroInstalacaoHistorico);
		
		
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			// Monta a p�gina de sucesso
			montarPaginaSucesso(
					httpServletRequest,
					"Liga��o de �gua com Instala��o de Hidr�metro sem RA efetuada com Sucesso",
					"Efetuar outra Liga��o de �gua com Instala��o de Hidr�metro sem RA",
					"exibirEfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction.do?menu=sim");
		}

		return retorno;
		// } else {
		// throw new ActionServletException("atencao.informe_campo", null,
		// "Ordem de Servi�o");
		// }
	}

	// [SB0001] - Gerar Liga��o de �gua
	//
	// M�todo respons�vel por setar os dados da liga��o de �gua
	// de acordo com os dados selecionados pelo usu�rio e pelas exig�ncias do
	// caso de uso
	public LigacaoAgua setDadosLigacaoAgua(
			EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
			Fachada fachada, String matriculaImovel) {

		String diametroLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDiametroLigacao();
		String materialLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getMaterialLigacao();
		String idPerfilLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getPerfilLigacao();
		
		//verificar tarifa de consumo associada. 
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples (
				FiltroConsumoTarifa.LIGACAO_AGUA_PERFIL,
				idPerfilLigacao));
		
		Collection pesquisa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		if (!pesquisa.isEmpty()){
			Boolean existeTarifaIgual = false;
			Iterator iteratorColecaoConsumoTarifa = pesquisa.iterator();
			Imovel imovelConsulta=null;
			
			if(matriculaImovel!=null && matriculaImovel!=""){
				imovelConsulta= fachada.pesquisarImovel(new Integer(matriculaImovel));
			}
			
			while(iteratorColecaoConsumoTarifa.hasNext()){
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) iteratorColecaoConsumoTarifa.next();
				if (consumoTarifa.getLigacaoAguaPerfil() != null){
					if(imovelConsulta != null){
						if (imovelConsulta.getConsumoTarifa().getId().intValue() ==  consumoTarifa.getId().intValue()){
							existeTarifaIgual = true;
						}
					}
				}
			}
			
			if (!existeTarifaIgual){
				throw new ActionServletException("atencao.tarifa_consumo_perfil_ligacao",null, "Perfil da Liga��o");
			}
		}
		
		String ramalLocalInstalacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getRamalLocalInstalacao();
		String numeroLacre = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroLacre();

		LigacaoAgua ligacaoAgua = new LigacaoAgua();

		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataLigacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataLigacao().equals("")) {
			Date data = Util
					.converteStringParaDate(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getDataLigacao());
			ligacaoAgua.setDataLigacao(data);
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					" Data da Liga��o");
		}

		if (diametroLigacao != null
				&& !diametroLigacao.equals("")
				&& !diametroLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();
			ligacaoAguaDiametro.setId(new Integer(diametroLigacao));
			ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Diametro da Liga��o");
		}

		if (materialLigacao != null
				&& !materialLigacao.equals("")
				&& !materialLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial();
			ligacaoAguaMaterialMaterial.setId(new Integer(materialLigacao));
			ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Material da Liga��o");
		}

		if (idPerfilLigacao != null
				&& !idPerfilLigacao.equals("")
				&& !idPerfilLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
			ligacaoAguaPerfil.setId(new Integer(idPerfilLigacao));
			ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Perfil da Liga��o");
		}

		if (ramalLocalInstalacao != null
				&& !ramalLocalInstalacao.equals("")
				&& !ramalLocalInstalacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao();
			ramalLocal.setId(new Integer(ramalLocalInstalacao));

			ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
		}

		if (numeroLacre != null && !numeroLacre.equals("")) {
			ligacaoAgua.setNumeroLacre(numeroLacre);
		}

		return ligacaoAgua;
	}

	// [SB0004] - Gerar Hist�rico de Instala��o do Hidr�metro
	//
	// M�todo respons�vel por setar os dados do hidr�metro instala��o hist�rico
	// de acordo com os dados selecionados pelo usu�rio e pelas exig�ncias do
	// caso de uso
	public HidrometroInstalacaoHistorico setDadosHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm) {

		Fachada fachada = Fachada.getInstancia();

		String numeroHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();

		if (numeroHidrometro != null) {
			// Pesquisa o Hidr�metro
			Hidrometro hidrometro = fachada
					.pesquisarHidrometroPeloNumero(numeroHidrometro);

			if (hidrometro == null) {
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			} else if (!hidrometro.getHidrometroSituacao().getId().equals(
					HidrometroSituacao.DISPONIVEL)) {
				throw new ActionServletException(
						"atencao.hidrometro_situacao_indisponivel", null,
						hidrometro.getHidrometroSituacao().getDescricao());
			}

			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);

		}

		// Data instala��o
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataInstalacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataInstalacao().equals("")) {

			hidrometroInstalacaoHistorico
					.setDataInstalacao(Util
							.converteStringParaDate(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.getDataInstalacao()));

		}

		// Medi��o tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);

		// hidr�metro local instala��o
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer
				.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getLocalInstalacao()));
		hidrometroInstalacaoHistorico
				.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

		// prote��o
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer
				.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);

		// leitura instala��o
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getLeituraInstalacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getLeituraInstalacao().trim().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroLeituraInstalacao(Integer
							.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.getLeituraInstalacao()));
		} else {
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);
		}

		// cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short
				.parseShort(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getSituacaoCavalete()));

		/*
		 * Campos opcionais
		 */

		// data da retirada
		hidrometroInstalacaoHistorico.setDataRetirada(null);
		// leitura retirada
		hidrometroInstalacaoHistorico.setNumeroLeituraRetirada(null);
		// leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
		// leitura supress�o
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
		// numero selo
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getNumeroSelo() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getNumeroSelo().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroSelo(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getNumeroSelo());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}
		// tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		// indicador instala��o substitui��o
		hidrometroInstalacaoHistorico
				.setIndicadorInstalcaoSubstituicao(new Short("1"));

		// data �ltima altera��o
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());

		return hidrometroInstalacaoHistorico;

	}
}
