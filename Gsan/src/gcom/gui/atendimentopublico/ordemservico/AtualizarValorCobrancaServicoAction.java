/**
 * 
 */
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 01/11/2006
 */
public class AtualizarValorCobrancaServicoAction extends GcomAction {

	/**
	 * [UC0393] Manter Valor de Cobran�a do Servi�o
	 * 
	 * Este caso de uso cria um filtro que ser� usado na pesquisa do Valor de
	 * Cobran�a de Servi�o
	 * 
	 * [SB0001] Atualizar Valor de Cobran�a do Servi�o
	 * 
	 * @author R�mulo Aur�lio
	 * @date 01/11/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		Date timeStampDB = (Date) sessao.getAttribute("timeStamp");
		
		Date dataVigenciaInicial = (Date) sessao.getAttribute("dataVigenciaInicial");
		
		Date dataVigenciaFinal = (Date) sessao.getAttribute("dataVigenciaFinal");

		AtualizarValorCobrancaServicoActionForm form = (AtualizarValorCobrancaServicoActionForm) actionForm;

		String idServicoCobrancaValor1 = (String) sessao.getAttribute("idServicoCobrancaValor");
		
		form.setIdServicoCobrancaValor(idServicoCobrancaValor1);
		
		ServicoCobrancaValor servicoCobrancaValor = (ServicoCobrancaValor) sessao
				.getAttribute("servicoCobrancaValor");

		String descricaoTipoServico = form.getNomeTipoServico();

		String indicadorMedido = form.getIndicadorMedido();
		
		String indicativoTipoSevicoEconomias = form.getIndicativoTipoSevicoEconomias();

		String tipoServico = form.getTipoServico();

		String perfilImovel = form.getPerfilImovel();

		String capacidadeHidrometro = form.getCapacidadeHidrometro();
		
		String idCategoria = form.getIdCategoria();
		
		String idSubCategoria = form.getIdSubCategoria();
		
		String quantidadeEconomiaInicial = form.getQuantidadeEconomiasInicial();
		
		String quantidadeEconomiaFinal = form.getQuantidadeEconomiasFinal();
		
		String indicadorGeracaoDebito = form.getIndicadorGeracaoDebito();
	
		String valorServico = form.getValorServico();
		
		if(valorServico!=null && !valorServico.equals("")){

			String valorSemPontos = valorServico.replace(".", "");
	
			valorServico = valorSemPontos.replace(",", ".");
		
		}else{
			valorServico = "0";
		}
		
		servicoCobrancaValor.setValor(new BigDecimal(valorServico));
		
		if(indicadorGeracaoDebito!=null && !indicadorGeracaoDebito.equals("")){
			servicoCobrancaValor.setIndicadorGeracaoDebito(new Short(indicadorGeracaoDebito));
		}

		// Seta no Objeto os dados do form
		ServicoTipo servicoTipo = new ServicoTipo();

		servicoTipo.setId(new Integer(tipoServico));

		servicoCobrancaValor.setServicoTipo(servicoTipo);
		
		//categoria
		Categoria categoria = new Categoria();
		
		if(idCategoria!=null && !idCategoria.equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
			categoria.setId(new Integer(idCategoria));
			
			servicoCobrancaValor.setCategoria(categoria);
		}else{
			servicoCobrancaValor.setCategoria(null);
		}
		
		// subCategoria
		Subcategoria subCategoria = new Subcategoria();
		
		if(idSubCategoria != null && !idSubCategoria.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			subCategoria.setId(new Integer(idSubCategoria));
			
			servicoCobrancaValor.setSubCategoria(subCategoria);
		}else{
			servicoCobrancaValor.setSubCategoria(null);
		}
		
		servicoCobrancaValor.setIndicadorConsideraEconomias(new Short(indicativoTipoSevicoEconomias));

		if(indicadorMedido != null && !indicadorMedido.equals("")){
			servicoCobrancaValor.setIndicadorMedido(new Short(indicadorMedido));
		}
		
		if(servicoCobrancaValor.getIndicadorConsideraEconomias().compareTo(ConstantesSistema.SIM)==0){
			// Quantidade Economia Inicial
			if(quantidadeEconomiaInicial != null && !quantidadeEconomiaInicial.equals("")){
				servicoCobrancaValor.setQuantidadeEconomiasInicial(new Integer(quantidadeEconomiaInicial));
			}
		
			// Quantidade Economia Final
			if(quantidadeEconomiaFinal != null && !quantidadeEconomiaFinal.equals("")){
				servicoCobrancaValor.setQuantidadeEconomiasFinal(new Integer(quantidadeEconomiaFinal));
			}
		}else{
			servicoCobrancaValor.setQuantidadeEconomiasInicial(null);
			servicoCobrancaValor.setQuantidadeEconomiasFinal(null);
		}
		
		
		// capacidade Hidrometro
		HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
		
		if (capacidadeHidrometro != null
				&& !capacidadeHidrometro.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)){ 

			hidrometroCapacidade.setId(new Integer(capacidadeHidrometro));

			servicoCobrancaValor.setHidrometroCapacidade(hidrometroCapacidade);
		}else{
			servicoCobrancaValor.setHidrometroCapacidade(null);
		}

		// Perfil imovel
		ImovelPerfil imovelPerfil = new ImovelPerfil();
		
		if (perfilImovel != null
				&& !perfilImovel.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			imovelPerfil.setId(new Integer(perfilImovel));

			servicoCobrancaValor.setImovelPerfil(imovelPerfil);
		}
		
		// Vig�ncia servico Cobranca Valor
		//[FS0007] � Validar data da vig�ncia inicial
		if (form.getDataVigenciaInicial() != null && !form.getDataVigenciaInicial().equals("")){
			
			if (!Util.validarDiaMesAno(form.getDataVigenciaInicial())) {
				
				servicoCobrancaValor.setDataVigenciaInicial(Util.converteStringParaDate(form.getDataVigenciaInicial()));
				//[FS0008] � Validar data da vig�ncia final
				if (!Util.validarDiaMesAno(form.getDataVigenciaFinal())) {
					
					servicoCobrancaValor.setDataVigenciaFinal(Util.converteStringParaDate(form.getDataVigenciaFinal()));
					
					if(Util.compararData(servicoCobrancaValor.getDataVigenciaInicial(),servicoCobrancaValor.getDataVigenciaFinal()) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
		}else{
			
			servicoCobrancaValor.setDataVigenciaInicial(null);
			servicoCobrancaValor.setDataVigenciaFinal(null);
		}
		
		// FS0008 - Verificar pr�-exist�ncia de vig�ncia para o Servi�o tipo
		Fachada.getInstancia().verificarExistenciaVigenciaDebito(form.getDataVigenciaInicial(), 
				form.getDataVigenciaFinal(), new Integer(form.getTipoServico()),
				new Integer("1"));
		
		
		/*	 
		 	-Colocar m�todo no controlador 		 	
		 Data: 14/05/2010
		 Author: Hugo Amorim		 
		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_VALOR_COBRANCA_SERVICO_ATUALIZAR, servicoCobrancaValor.getServicoTipo().getId(),
				servicoCobrancaValor.getServicoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSA��O ----------------
		
		registradorOperacao.registrarOperacao(servicoCobrancaValor);
		*/
		if(timeStampDB.compareTo(servicoCobrancaValor.getUltimaAlteracao()) != 0){
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}
		
		if(dataVigenciaInicial.compareTo(servicoCobrancaValor.getDataVigenciaInicial()) != 0
				|| dataVigenciaFinal.compareTo(servicoCobrancaValor.getDataVigenciaFinal()) != 0){
			// FS0013 - Verificar pr�-exist�ncia de vig�ncia para o Servi�o tipo
			if(fachada.validarVigenciaValorCobrancaServico(servicoCobrancaValor)){
				throw new ActionServletException("atencao.existe_valor_para_vigencia");
			}
		}	
		fachada.atualizarValorCobrancaServico(servicoCobrancaValor,usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Valor da Cobran�a do Servi�o "
				+ descricaoTipoServico + " atualizado com sucesso.",
				"Realizar outra Manuten��o Valor da Cobran�a do Servi�o",
				"exibirFiltrarValorCobrancaServicoAction.do?menu=sim");

		return retorno;
	}
}
