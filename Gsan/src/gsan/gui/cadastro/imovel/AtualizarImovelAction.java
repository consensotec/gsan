/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestï¿½o de Serviï¿½os de Saneamento
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
 * GSAN - Sistema Integrado de Gestï¿½o de Serviï¿½os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araï¿½jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Clï¿½udio de Andrade Lira
 * Denys Guimarï¿½es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabï¿½ola Gomes de Araï¿½jo
 * Flï¿½vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Jï¿½nior
 * Homero Sampaio Cavalcanti
 * Ivan Sï¿½rgio da Silva Jï¿½nior
 * Josï¿½ Edmar de Siqueira
 * Josï¿½ Thiago Tenï¿½rio Lopes
 * Kï¿½ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Mï¿½rcio Roberto Batista da Silva
 * Maria de Fï¿½tima Sampaio Leite
 * Micaela Maria Coelho de Araï¿½jo
 * Nelson Mendonï¿½a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrï¿½a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araï¿½jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sï¿½vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa ï¿½ software livre; vocï¿½ pode redistribuï¿½-lo e/ou
 * modificï¿½-lo sob os termos de Licenï¿½a Pï¿½blica Geral GNU, conforme
 * publicada pela Free Software Foundation; versï¿½o 2 da
 * Licenï¿½a.
 * Este programa ï¿½ distribuï¿½do na expectativa de ser ï¿½til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implï¿½cita de
 * COMERCIALIZAï¿½ï¿½O ou de ADEQUAï¿½ï¿½O A QUALQUER PROPï¿½SITO EM
 * PARTICULAR. Consulte a Licenï¿½a Pï¿½blica Geral GNU para obter mais
 * detalhes.
 * Vocï¿½ deve ter recebido uma cï¿½pia da Licenï¿½a Pï¿½blica Geral GNU
 * junto com este programa; se nï¿½o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gsan.gui.cadastro.imovel;

import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gsan.atendimentopublico.ordemservico.FiltroOrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.endereco.Cep;
import gsan.cadastro.endereco.EnderecoReferencia;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.endereco.LogradouroCep;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.Despejo;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroSubCategoria;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelContaEnvio;
import gsan.cadastro.imovel.ImovelInscricaoAlterada;
import gsan.cadastro.imovel.ImovelSubcategoria;
import gsan.cadastro.imovel.ImovelTipoCobertura;
import gsan.cadastro.imovel.ImovelTipoConstrucao;
import gsan.cadastro.imovel.ImovelTipoHabitacao;
import gsan.cadastro.imovel.ImovelTipoPropriedade;
import gsan.cadastro.imovel.PiscinaVolumeFaixa;
import gsan.cadastro.imovel.PocoTipo;
import gsan.cadastro.imovel.ReservatorioVolumeFaixa;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gsan.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gsan.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gsan.cadastro.imovel.bean.ImovelAbaConclusaoRetornoHelper;
import gsan.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gsan.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gsan.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gsan.cadastro.imovel.bean.InserirImovelHelper;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.Funcionalidade;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe responï¿½vel pela conclusï¿½o do cadastro de um imï¿½vel 
 *
 * @author Raphael Rossiter
 * @date 12/05/2009
 */
public class AtualizarImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		
		if (httpServletRequest.getParameter("confirmado") != null && 
			httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			sessao.setAttribute("confirmou", "sim");
		}
		

		// Cria Variaveis
		Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizacao");
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Collection colecaoImovelSubcategoriasRemovidas = (Collection) sessao.getAttribute("colecaoImovelSubcategoriasRemoviadas");
		Collection colecaoImovelRamosAtividadesRemovidos = (Collection) sessao.getAttribute("colecaoImovelRamosAtividadesRemovidos");
		Collection colecaoClientesImoveisRemovidos = (Collection) sessao.getAttribute("colecaoClientesImoveisRemovidos");
		
		String idLocalidade = (String) inserirImovelActionForm.get("idLocalidade");
		String idSetorComercial = (String) inserirImovelActionForm.get("idSetorComercial");
		String idQuadra = (String) inserirImovelActionForm.get("idQuadra");
		String idQuadraFace = (String) inserirImovelActionForm.get("idQuadraFace");
		String lote = (String) inserirImovelActionForm.get("lote");
		String subLote = (String) inserirImovelActionForm.get("subLote");
		String testadaLote = (String) inserirImovelActionForm.get("testadaLote");
		String areaConstruida = (String) inserirImovelActionForm.get("areaConstruida");
		String faixaAreaConstruida = (String) inserirImovelActionForm.get("faixaAreaConstruida");
		String volumeReservatorioSuperior = (String) inserirImovelActionForm.get("reservatorioSuperior");
		String faixaVolumeReservatorioSuperior = (String) inserirImovelActionForm.get("faixaResevatorioSuperior");
		String volumeReservatorioInferior = (String) inserirImovelActionForm.get("reservatorioInferior");
		String faixaVolumeReservaotirio = (String) inserirImovelActionForm.get("faixaReservatorioInferior");
		String piscina = (String) inserirImovelActionForm.get("piscina");
		String jardim = (String) inserirImovelActionForm.get("jardim");
		String faixaPiscina = (String) inserirImovelActionForm.get("faixaPiscina");
		String pavimentoCalcada = (String) inserirImovelActionForm.get("pavimentoCalcada");
		String pavimentoRua = (String) inserirImovelActionForm.get("pavimentoRua");
		String fonteAbastecimento = (String) inserirImovelActionForm.get("fonteAbastecimento");
		String situacaoLigacaoAgua = (String) inserirImovelActionForm.get("situacaoLigacaoAgua");
		String situacaoLigacaoEsgoto = (String) inserirImovelActionForm.get("situacaoLigacaoEsgoto");
		String perfilImovel = (String) inserirImovelActionForm.get("perfilImovel");
		String poco = (String) inserirImovelActionForm.get("poco");
		String idLigacaoEsgotoEsgotamento = (String) inserirImovelActionForm.get("idLigacaoEsgotoEsgotamento");

		String tipoHabitacao = (String) inserirImovelActionForm.get("imovelTipoHabitacao");
		String tipoPropriedade = (String) inserirImovelActionForm.get("imovelTipoPropriedade");
		String tipoConstrucao = (String) inserirImovelActionForm.get("imovelTipoConstrucao");
		String tipoCobertura = (String) inserirImovelActionForm.get("imovelTipoCobertura");
		
		String pontoUtilizaco = (String) inserirImovelActionForm.get("numeroPontos");
		String numeroMoradores = (String) inserirImovelActionForm.get("numeroMoradores");
		String numeroIptu = (String) inserirImovelActionForm.get("numeroIptu");
		String numeroContratoCelpe = (String) inserirImovelActionForm.get("numeroContratoCelpe");
		String imovelContaEnvioForm = (String) inserirImovelActionForm.get("imovelContaEnvio");
		String cordenadasX = (String) inserirImovelActionForm.get("cordenadasUtmX");
		String cordenadasY = (String) inserirImovelActionForm.get("cordenadasUtmY");
		String extratoResponsavel = (String) inserirImovelActionForm.get("extratoResponsavel");
		// String nomeContaForm = (String) inserirImovelActionForm.get("tipoOcupacao");
		String tipoDespejo = (String) inserirImovelActionForm.get("tipoDespejo");
		String idImovelPrincipal = (String) inserirImovelActionForm.get("idImovel");
		String sequencialRota = (String) inserirImovelActionForm.get("sequencialRota");
		String sequencialRotaEntrega = (String) inserirImovelActionForm.get("sequencialRotaEntrega");
		String idRotaEntrega = (String) inserirImovelActionForm.get("idRota");
		String idRotaAlternativa = (String) inserirImovelActionForm.get("idRotaAlternativa");
		String idFuncionario = (String) inserirImovelActionForm.get("idFuncionario"); 
		String numeroMedidorEnergia = (String) inserirImovelActionForm.get("numeroMedidorEnergia");
		String dataVisitaComercialInformada  = (String) inserirImovelActionForm.get("dataVisitaComercial");
		String informacoesComplementares = (String) inserirImovelActionForm.get("informacoesComplementares");
		String numeroQuadraEntrega = (String) inserirImovelActionForm.get("numeroQuadraEntrega");
		
		String indicadorNivelInstalacaoEsgoto = (String) inserirImovelActionForm.get("indicadorNivelInstalacaoEsgoto");
		
		FiltroImovel filtro = new FiltroImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovelAtualizar.getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto.ligacaoEsgotoPerfil");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto.ligacaoEsgotoEsgotamento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto.ligacaoEsgotoDiametro");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto.ligacaoEsgotoMaterial");
		Collection<Imovel> colecaoImovel = fachada.pesquisar(filtro, Imovel.class.getName());
		
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
		
		// ABA LOCALIDADE
		ImovelAbaLocalidadeHelper helperLocalidade = new ImovelAbaLocalidadeHelper();
		helperLocalidade.setIdImovel(imovelAtualizar.getId());
		helperLocalidade.setIdLocalidade(idLocalidade);
		helperLocalidade.setCodigoSetorComercial(idSetorComercial);
		helperLocalidade.setNumeroQuadra(idQuadra);
		helperLocalidade.setIdQuadraFace(idQuadraFace);
		helperLocalidade.setLote(lote);
		helperLocalidade.setSublote(subLote);
		ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade = fachada.validarImovelAbaLocalidade(helperLocalidade);
		
		
		//imovel.setLote(new Short(lote));
		
		
		if (fachada.verificaImovelExcluidoFinalFaturamento(imovel.getId())) {
			throw new ActionServletException("atencao.imovel_nao_pode_ser_atualiado");
		}
		
		
		if (Util.verificarNaoVazio(testadaLote)) {
			imovel.setTestadaLote(new Short(testadaLote).shortValue());
		} else {
			imovel.setTestadaLote(null);
		}
		if (Util.verificarNaoVazio(sequencialRota)) {
			imovel.setNumeroSequencialRota(new Integer(sequencialRota));
		} else {
			imovel.setNumeroSequencialRota(null);
		}
		
		// ABA ENDERECO
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		//**********************************************************************
		// Autor: Ivan Sergio
		// Data: 23/07/2009
		// CRC2103
		// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
		// cliente ï¿½ invocado pelo Inserir/Manter Imovel como PopUp
		//**********************************************************************
		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
					Object obj = (Object) colecaoEnderecos.iterator().next();
					if (!(obj instanceof Imovel)) {
						sessao.removeAttribute("colecaoEnderecos");
					}
					if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
						colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecosImovel");
					}
				}else if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
					colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecosImovel");
				}
			}
		}
		//**********************************************************************
		
		ImovelAbaEnderecoHelper helperEndereco = new ImovelAbaEnderecoHelper();
		helperEndereco.setImovelEnderecos(colecaoEnderecos);
		helperEndereco.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		helperEndereco.setUsuarioLogado(usuario);
		helperEndereco.setIdFuncionalidade(Funcionalidade.MANTER_IMOVEL);
		
		fachada.validarImovelAbaEndereco(helperEndereco);
		
		Imovel imovelEndereco = (Imovel) Util.retonarObjetoDeColecao(colecaoEnderecos);
		
		Logradouro logradouro = null;
		if (imovelEndereco.getLogradouroCep() != null && 
			imovelEndereco.getLogradouroCep().getLogradouro() != null && 
			!imovelEndereco.getLogradouroCep().getLogradouro().equals("")) {
			Integer idLogradouro = imovelEndereco.getLogradouroCep().getLogradouro().getId();
			logradouro = new Logradouro();
			logradouro.setId(idLogradouro);
		} else {
			// ALTERAï¿½ï¿½O FEITTA PARA FUNCIONAMENTO DA APLICAï¿½ï¿½O, APAGAR
			// A CONDIï¿½ï¿½O DO "SE Nï¿½O",
			// ALTERAï¿½ï¿½O DO VALOR DO LOGRADOURO ESTA SENDO ANALIZADA POR ARYED,
			// ANA E LEO NARDO NO DIA 16/03/2006
			logradouro = new Logradouro();
			logradouro.setId(new Integer("0"));
		}
		
		LogradouroCep logradouroCep = null;
		if (imovelEndereco.getLogradouroCep() != null && 
			imovelEndereco.getLogradouroCep().getCep() != null) {
			Integer cep = imovelEndereco.getLogradouroCep().getCep().getCepId();
			Cep cepObj = new Cep();
			cepObj.setCepId(cep);
			logradouroCep = fachada.pesquisarAssociacaoLogradouroCep(cepObj.getCepId(), logradouro.getId());
		}
		
		EnderecoReferencia enderecoReferencia = null;
		if (imovelEndereco.getEnderecoReferencia() != null) {
			Integer idEnderecoReferencia = imovelEndereco.getEnderecoReferencia().getId();
			if (idEnderecoReferencia != null && !idEnderecoReferencia.toString().trim().equals("")) {
				enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setId(idEnderecoReferencia);
			}
		}
		
		imovel.setNumeroImovel(imovelEndereco.getNumeroImovel());
		imovel.setComplementoEndereco(imovelEndereco.getComplementoEndereco());
		imovel.setLogradouroCep(logradouroCep);
		imovel.setEnderecoReferencia(enderecoReferencia);
		imovel.setLogradouroBairro(imovelEndereco.getLogradouroBairro());
		imovel.setPerimetroInicial(imovelEndereco.getPerimetroInicial());
		imovel.setPerimetroFinal(imovelEndereco.getPerimetroFinal());
		
		// ABA CLIENTES
		Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");
		fachada.validarImovelAbaCliente(clientes, usuario);
		
		// ABA SUBCATEGORIA ECONOMIAS
        Collection subcategorias = (Collection) sessao.getAttribute("colecaoImovelSubCategorias");
		fachada.validarAbaInserirImovelSubcategoria(
				subcategorias, PermissaoEspecial.INSERIR_IMOVEL_PARA_ORGAO_PUBLICO, usuario);
		
		// ABA CARACTERISTICA
		ImovelAbaCaracteristicasHelper helperCaracteristica = new ImovelAbaCaracteristicasHelper();
		helperCaracteristica.setImovelAtualizar(imovel);
		helperCaracteristica.setAreaConstruida(areaConstruida);
		helperCaracteristica.setIdAreaConstruidaFaixa(faixaAreaConstruida);
		helperCaracteristica.setIdPavimentoCalcada(pavimentoCalcada);
		helperCaracteristica.setIdPavimentoRua(pavimentoRua);
		helperCaracteristica.setIdFonteAbastecimento(fonteAbastecimento);
		helperCaracteristica.setIdLigacaoAguaSituacao(situacaoLigacaoAgua);
		helperCaracteristica.setIdLigacaoEsgotoSituacao(situacaoLigacaoEsgoto);
		helperCaracteristica.setIdLigacaoEsgotoEsgotamento(idLigacaoEsgotoEsgotamento);
		helperCaracteristica.setIdImovelPerfil(perfilImovel);
		helperCaracteristica.setIdSetorComercial(idSetorComercial);
		helperCaracteristica.setIdQuadra(idQuadra);
		helperCaracteristica.setIdNivelInstalacaoEsgoto(indicadorNivelInstalacaoEsgoto);
		
		helperCaracteristica.setIdImovelTipoHabitacao(tipoHabitacao);
		helperCaracteristica.setIdImovelTipoPropriedade(tipoPropriedade);
		helperCaracteristica.setIdImovelTipoConstrucao(tipoConstrucao);
		helperCaracteristica.setIdImovelTipoCobertura(tipoCobertura);
		
		ImovelAbaCaracteristicasRetornoHelper resultadoAbaCaracteristicas = 
			fachada.validarImovelAbaCaracteristicas(helperCaracteristica);
		
		if (Util.verificarNaoVazio(areaConstruida)) {
			imovel.setAreaConstruida(Util.formatarMoedaRealparaBigDecimal(areaConstruida));
		}
		imovel.setAreaConstruidaFaixa(resultadoAbaCaracteristicas.getAreaConstruidaFaixa());

		if (Util.verificarIdNaoVazio(faixaVolumeReservaotirio)) {
			ReservatorioVolumeFaixa reservatorioVolumeFaixaInf = new ReservatorioVolumeFaixa();
			reservatorioVolumeFaixaInf.setId(new Integer(faixaVolumeReservaotirio));
			imovel.setReservatorioVolumeFaixaInferior(reservatorioVolumeFaixaInf);
		}
		if (Util.verificarNaoVazio(volumeReservatorioInferior)) {
			imovel.setVolumeReservatorioInferior(Util.formatarMoedaRealparaBigDecimal(volumeReservatorioInferior));
		}
		if (Util.verificarIdNaoVazio(faixaVolumeReservatorioSuperior)) {
			ReservatorioVolumeFaixa reservatorioVolumeFaixaSup = new ReservatorioVolumeFaixa();
			reservatorioVolumeFaixaSup.setId(new Integer(faixaVolumeReservatorioSuperior));
			imovel.setReservatorioVolumeFaixaSuperior(reservatorioVolumeFaixaSup);
		}
		if (Util.verificarNaoVazio(volumeReservatorioSuperior)) {
			imovel.setVolumeReservatorioSuperior(Util.formatarMoedaRealparaBigDecimal(volumeReservatorioSuperior));
		}
		if (Util.verificarIdNaoVazio(faixaPiscina)) {
			PiscinaVolumeFaixa piscinaVolumeFaixa = new PiscinaVolumeFaixa();
			piscinaVolumeFaixa.setId(new Integer(faixaPiscina));
			imovel.setPiscinaVolumeFaixa(piscinaVolumeFaixa);
		}
		if (Util.verificarNaoVazio(piscina)) {
			imovel.setVolumePiscina(Util.formatarMoedaRealparaBigDecimal(piscina));
		}
		if (Util.verificarNaoVazio(jardim)) {
			imovel.setIndicadorJardim(new Short(jardim));
		}
		
		imovel.setPavimentoCalcada(resultadoAbaCaracteristicas.getPavimentoCalcada());
		imovel.setPavimentoRua(resultadoAbaCaracteristicas.getPavimentoRua());
		imovel.setFonteAbastecimento(resultadoAbaCaracteristicas.getFonteAbastecimento());
		imovel.setLigacaoAguaSituacao(resultadoAbaCaracteristicas.getLigacaoAguaSituacao());
		imovel.setLigacaoEsgotoSituacao(resultadoAbaCaracteristicas.getLigacaoEsgotoSituacao());
		imovel.setImovelPerfil(resultadoAbaCaracteristicas.getImovelPerfil());
		imovel.setIndicadorNivelInstalacaoEsgoto(new Short(indicadorNivelInstalacaoEsgoto));
		
		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = resultadoAbaCaracteristicas.getLigacaoEsgotoEsgotamento();
		
		if (Util.verificarIdNaoVazio(poco)) {
			PocoTipo pocoTipo = new PocoTipo();
			pocoTipo.setId(new Integer(poco));
			imovel.setPocoTipo(pocoTipo);
		}
		if(poco.equals("-1")){
			imovel.setPocoTipo(null);
		}
		if (Util.verificarIdNaoVazio(tipoDespejo)) {
			Despejo despejo = new Despejo();
			despejo.setId(new Integer(tipoDespejo));
			imovel.setDespejo(despejo);
		}
		if (Util.verificarIdNaoVazio(tipoHabitacao)) {
			ImovelTipoHabitacao imovelTipoHabitacao = new ImovelTipoHabitacao();
			imovelTipoHabitacao.setId(new Integer(tipoHabitacao));
			imovel.setImovelTipoHabitacao(imovelTipoHabitacao);
		}
		if (Util.verificarIdNaoVazio(tipoPropriedade)) {
			ImovelTipoPropriedade imovelTipoPropriedade = new ImovelTipoPropriedade();
			imovelTipoPropriedade.setId(new Integer(tipoPropriedade));
			imovel.setImovelTipoPropriedade(imovelTipoPropriedade);
		}
		if (Util.verificarIdNaoVazio(tipoConstrucao)) {
			ImovelTipoConstrucao imovelTipoConstrucao = new ImovelTipoConstrucao();
			imovelTipoConstrucao.setId(new Integer(tipoConstrucao));
			imovel.setImovelTipoConstrucao(imovelTipoConstrucao);
		}
		if (Util.verificarIdNaoVazio(tipoCobertura)) {
			ImovelTipoCobertura imovelTipoCobertura = new ImovelTipoCobertura();
			imovelTipoCobertura.setId(new Integer(tipoCobertura));
			imovel.setImovelTipoCobertura(imovelTipoCobertura);
		}
		if (Util.verificarIdNaoVazio(numeroMedidorEnergia)) {
			
			imovel.setNumeroMedidorEnergia(numeroMedidorEnergia);
		}
		if (dataVisitaComercialInformada !=null && !dataVisitaComercialInformada.equals("")) {
			
			Date dataVisitaComercial = Util.converteStringParaDate(dataVisitaComercialInformada);
			imovel.setDataVisitaComercial(dataVisitaComercial);
		}
		
		// ABA CONCLUSAO
		ImovelAbaConclusaoHelper helperConclusao = new ImovelAbaConclusaoHelper();
		helperConclusao.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		helperConclusao.setIdQuadraImovel(idQuadra);
		helperConclusao.setIdImovelPrincipal(idImovelPrincipal);
		helperConclusao.setNumeroIptu(numeroIptu);
		helperConclusao.setNumeroContratoCelpe(numeroContratoCelpe);
		helperConclusao.setCoordenadasUtmX(cordenadasX);
		helperConclusao.setCoordenadasUtmY(cordenadasY);
		helperConclusao.setSequencialRotaEntrega(sequencialRotaEntrega);
		helperConclusao.setNumeroQuadraEntrega(numeroQuadraEntrega);
		helperConclusao.setIdRotaEntrega(idRotaEntrega);
		helperConclusao.setIdRotaAlternativa(idRotaAlternativa);
		helperConclusao.setImoveisClientes(clientes);
		helperConclusao.setIdImovelAtualizar(imovel.getId());
		helperConclusao.setNumeroMedidorEnergia(numeroMedidorEnergia);
		ImovelAbaConclusaoRetornoHelper resultadoAbaConclusao = fachada.validarImovelAbaConclusao(helperConclusao);
		
		if (numeroQuadraEntrega != null && !numeroQuadraEntrega.equals("")){
			imovel.setNumeroQuadraEntrega(new Integer(numeroQuadraEntrega));
		} else if (numeroQuadraEntrega.equals("")){
			imovel.setNumeroQuadraEntrega(null);
		}
		
		if (resultadoAbaConclusao.getSequencialRotaEntrega() != null && !resultadoAbaConclusao.getSequencialRotaEntrega().equals("")) {
			imovel.setNumeroSequencialRotaEntrega(new Integer(resultadoAbaConclusao.getSequencialRotaEntrega()));
		}else {
			imovel.setNumeroSequencialRotaEntrega(null);			
		}
		if (resultadoAbaConclusao.getRotaEntrega() != null && !resultadoAbaConclusao.getRotaEntrega().equals("")) {
			imovel.setRotaEntrega(resultadoAbaConclusao.getRotaEntrega());
		}else{
			imovel.setRotaEntrega(null);
		}
		
		if (resultadoAbaConclusao.getRotaAlternativa() != null) {
	        	imovel.setRotaAlternativa(resultadoAbaConclusao.getRotaAlternativa());
		}
		else{
			imovel.setRotaAlternativa(null);
		}
		
		imovel.setNumeroPontosUtilizacao(Util.verificarNaoVazio(pontoUtilizaco) ? new Short(pontoUtilizaco) : null);
		imovel.setNumeroMorador(Util.verificarNaoVazio(numeroMoradores) ? new Short(numeroMoradores) : null);
		imovel.setNumeroIptu(Util.verificarNaoVazio(numeroIptu) ? new BigDecimal(numeroIptu) : null);
		imovel.setNumeroCelpe(Util.verificarNaoVazio(numeroContratoCelpe) ? new Long(numeroContratoCelpe) : null);
		
		DecimalFormat dff = new DecimalFormat("########0.0000000000000000;-########0.0000000000000000");	
		
		if(cordenadasX != null && !cordenadasX.trim().equals("") ){
			cordenadasX = fachada.validaBigDecimal(cordenadasX, dff,"X");			
		}
				
		if(cordenadasY != null  && !cordenadasY.trim().equals("")){
			cordenadasY = fachada.validaBigDecimal(cordenadasY, dff,"Y");			
		}
		
		imovel.setCoordenadaX(Util.verificarNaoVazio(cordenadasX) ? new BigDecimal(cordenadasX.replace(',','.')) : null);
		imovel.setCoordenadaY(Util.verificarNaoVazio(cordenadasY) ? new BigDecimal(cordenadasY.replace(',','.')) : null);
		if (Util.verificarIdNaoVazio(idImovelPrincipal)) {
			Imovel imovelPrincipal = new Imovel();
			imovelPrincipal.setId(new Integer(idImovelPrincipal));
			imovel.setImovelPrincipal(imovelPrincipal);
		}
		if (Util.verificarIdNaoVazio(idFuncionario)) {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(idFuncionario));
			imovel.setFuncionario(funcionario);
		} else {
			imovel.setFuncionario(null);
		}
		
		if ( Util.verificarNaoVazio(informacoesComplementares) ){
			
			imovel.setInformacoesComplementares(informacoesComplementares);
		} else {
			imovel.setInformacoesComplementares("");
		}
		
		
		if (Util.verificarIdNaoVazio(numeroMedidorEnergia)) {
			
			imovel.setNumeroMedidorEnergia(numeroMedidorEnergia);
		}
		if (dataVisitaComercialInformada !=null && !dataVisitaComercialInformada.equals("")) {
			
			Date dataVisitaComercial = Util.converteStringParaDate(dataVisitaComercialInformada);
			imovel.setDataVisitaComercial(dataVisitaComercial);
		}
		
		// OUTROS
		//imovel.setUltimaAlteracao(new Date());
		imovel.setIndicadorEmissaoExtratoFaturamento(Util.verificarNaoVazio(extratoResponsavel) ? new Short(extratoResponsavel) : ConstantesSistema.NAO);
		
		if(imovelAtualizar.getIndicadorExclusao() == null){
			imovel.setIndicadorExclusao(ConstantesSistema.NAO);
		}else{
			imovel.setIndicadorExclusao(imovelAtualizar.getIndicadorExclusao());
		}
		imovel.setIndicadorImovelCondominio(imovelAtualizar.getIndicadorImovelCondominio());
		imovel.setIndicadorDebitoConta(imovelAtualizar.getIndicadorDebitoConta());
		imovel.setConsumoTarifa(imovelAtualizar.getConsumoTarifa());
		
		short quantidadeEconomias = 0;
		Iterator iteratorSubcategorias = subcategorias.iterator();
		while (iteratorSubcategorias.hasNext()) {
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iteratorSubcategorias.next();
			quantidadeEconomias += imovelSubcategoria.getQuantidadeEconomias();
		}
		imovel.setQuantidadeEconomias(quantidadeEconomias);
		
		ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
		if (Util.verificarIdNaoVazio(imovelContaEnvioForm)) {
			imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(new Integer(imovelContaEnvioForm));
		} else {
			imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(new Integer(2));
		}
		imovel.setImovelContaEnvio(imovelContaEnvio);
		
		if (imovel.getIndicadorEmissaoExtratoFaturamento() != null){
			if (imovel.getIndicadorEmissaoExtratoFaturamento().equals(new Short("0"))){
				imovel.setIndicadorEmissaoExtratoFaturamento(new Short("2"));
			}
		} else {
			imovel.setIndicadorEmissaoExtratoFaturamento(new Short("2"));
		}
		
		//CRC2258 solicitacao do analista Adriano Brito
		//data alteracao: 13/07/2009
		if (imovel.getIndicadorVencimentoMesSeguinte() == null){
			imovel.setIndicadorVencimentoMesSeguinte(new Short("2"));
		}
		//fim da alteracao
		
		
		// CRC3184 
		// Desenvolvedor:Hugo Amorim Analista:Rosana
		// Data: 15/12/2009	
		if(imovel.getImovelPerfil()!=null){
		boolean achou = false;	
			if(imovel.getImovelPerfil().getSubcategoria()!=null){
				
				for (Iterator iterator = subcategorias.iterator(); iterator.hasNext();) {
					ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
					Subcategoria subcategoria = imovelSubcategoria.getComp_id().getSubcategoria();
					if(subcategoria.getId().compareTo(imovel.getImovelPerfil().getSubcategoria().getId())==0){
						achou = true;
					}
				}
			
			
			if(!achou || subcategorias.size()>1){
				
				FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
				
				filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, imovel.getImovelPerfil().getSubcategoria().getId()));
				
				Collection<Subcategoria> colecaoSubs =
					 fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
				
				Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubs);
				
				throw new ActionServletException("atencao.perfil_exige_subcategoria",subcategoria.getDescricao());
			}
			}
		}		
		//Fim da Alteraï¿½ï¿½o CRC3184

		/**
		 * [SB0004] ï¿½ Verificar Alteraï¿½ï¿½o da Inscriï¿½ï¿½o do Imï¿½vel
		 * @author Arthur Carvalho
		 * @date 18/09/2010
		 */
		Imovel imovelSemAtualizacao = pesquisarImovel(imovelAtualizar.getId(), fachada);
		
		boolean flagNaoConfirmado = false;
		
		boolean inscricaoAlterada = this.verificarInscricaoAlterada(imovelSemAtualizacao,resultadoAbaLocalidade,helperLocalidade);

		String confirmado = httpServletRequest.getParameter("confirmado");
		String confirmacaoDupla = httpServletRequest.getParameter("confirmacaoDupla");
		
		//		1.	Caso os dados da inscriï¿½ï¿½o tenham sido alterados e o imï¿½vel 
		//      nï¿½o tenha rota alternativa (ROTA_IDALTERNATIVA com o valor nulo na tabela IMOVEL):
		if ( inscricaoAlterada &&  imovelSemAtualizacao.getRotaAlternativa() == null ) {
			
			if ( confirmado == null || confirmacaoDupla == null ||
				 (confirmacaoDupla != null && confirmacaoDupla.equalsIgnoreCase(""))) { 
				
				//1.3.	Caso a alteraï¿½ï¿½o da inscriï¿½ï¿½o acarrete a mudanï¿½a do grupo de faturamento do imï¿½vel 
				
				retorno = this.verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(
							imovelSemAtualizacao.getId(), imovelSemAtualizacao.getQuadra().getId(), resultadoAbaLocalidade.getQuadra().getId(), 
								httpServletRequest, actionMapping, fachada, imovel, resultadoAbaLocalidade, helperLocalidade, sessao);
				
			}
			
		} 
		else {
			atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
		}
		
		// Se tiver sido aprensentada a tela confirmaï¿½ï¿½o e o usuario confirmou.
		if(confirmado != null && confirmado.equalsIgnoreCase("ok") &&
		   confirmacaoDupla != null && confirmacaoDupla.equalsIgnoreCase("ok")){
			
			retorno = actionMapping.findForward("telaSucesso");
			sessao.removeAttribute("remove");
			//[SB0005] ï¿½ Preparar Alteraï¿½ï¿½o Inscriï¿½ï¿½o no Encerramento Faturamento
			prepararAlteracaoInscricaoEncerramentoFaturamento( imovel, imovelSemAtualizacao,
					 resultadoAbaLocalidade,  fachada,  helperLocalidade, usuario );
		} else if (confirmado != null && confirmado.equalsIgnoreCase("nao")) {
			flagNaoConfirmado =  true;
			retorno = actionMapping.findForward("telaSucesso");
			
			httpServletRequest.removeAttribute("nomeBotao1");
			httpServletRequest.removeAttribute("nomeBotao3");
			httpServletRequest.removeAttribute("confirmacaoDupla");

			sessao.removeAttribute("remove");
			//httpServletRequest.setAttribute("url","javascript:window.location.href='/gsan/atualizarImovelWizardAction.do?action=exibirAtualizarImovelLocalidadeAction';");
		}
		// FIM		[SB0004] ï¿½ Verificar Alteraï¿½ï¿½o da Inscriï¿½ï¿½o do Imï¿½vel
		
		
		//**********************************************************************
		// Autor: Rodrigo Cabral
		// Data: 13/09/2011
		// Atualizar a Principal Categoria e SubCategoria do Imovel
		//**********************************************************************
		
		Categoria principalCategoria = fachada.obterPrincipalCategoria(subcategorias);
		
		if (principalCategoria != null){
			
			imovel.setCategoriaPrincipalId(principalCategoria.getId());
			

			ImovelSubcategoria principalSubCategoria = 
				fachada.obterPrincipalSubcategoria(principalCategoria.getId(), subcategorias);
	
			imovel.setSubCategoriaPrincipalId(principalSubCategoria.getComp_id().getSubcategoria().getId());
		}
		
		
		/**
		 * alterado por pedro alexandre dia 17/11/2006 Recupera o usuï¿½rio logado
		 * para passar no metï¿½do de atualizar imï¿½vel para verificar se o usuï¿½rio
		 * tem abrangï¿½ncia para atualizar o imï¿½vel informado.
		 */
		
	    Collection ramosAtividades = (Collection) sessao.getAttribute("colecaoImovelRamosAtividade");
		
	    InserirImovelHelper inserirImovelHelper = new InserirImovelHelper(imovel, subcategorias,ramosAtividades, 
	    		colecaoEnderecos, clientes, ligacaoEsgotoEsgotamento, usuario);
		
		inserirImovelHelper.setColecaoClientesImoveisRemovidos(colecaoClientesImoveisRemovidos);
		inserirImovelHelper.setColecaoImovelSubcategoriasRemovidas(colecaoImovelSubcategoriasRemovidas);
		inserirImovelHelper.setColecaoRamoAtividadesRemovidas(colecaoImovelRamosAtividadesRemovidos);		
		
		/**
		 * [FS0044] – Validar alteracao relacionamento cliente/imovel Programa Viva Agua 
		 * Só deixar atualizar Imoveis relacionado com o programa viva água se tiver permissão especial
		 * 
		 * @author Diogo Luiz
		 * @date 11/06/2014
		 */	
/*		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getPerfilProgramaEspecial() != null){		
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovelAtualizar.getId()));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.IMOVEL_PERFIL_ID, sistemaParametro.getPerfilProgramaEspecial()));
			Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			Boolean imovelVivaAgua = false;
			Boolean permissaoEspecial = false;
			
			if(!Util.isVazioOrNulo(colecaoImovel)){
				imovelVivaAgua = true;
				
				FiltroUsuarioPemissaoEspecial  filtroPermissaoEspecial = new FiltroUsuarioPemissaoEspecial();
				filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
				filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, 
					PermissaoEspecial.ALTERAR_IMOVEL_CLIENTE_PROGRAMA_VIVA_AGUA));
				Collection<UsuarioPermissaoEspecial> colecaoPermissaoEspecial = fachada.pesquisar(filtroPermissaoEspecial, 
					UsuarioPermissaoEspecial.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoPermissaoEspecial)){
					permissaoEspecial = true;									
				}
				
				if(imovelVivaAgua && !permissaoEspecial){
					throw new ActionServletException("atencao.usuario_sem_permissao_viva_agua");
				}	
			}
		}*/

		fachada.atualizarImovel(inserirImovelHelper);
		
		String mensagemSucesso  = "Im�vel de matr�cula "
			+ imovelAtualizar.getId().toString()
			+ " atualizado com sucesso.";
		
		// Caso imï¿½vel esteja em processo de faturamento, e o usuï¿½rio nï¿½o 
		// tenha confirmado na tela de confirmaï¿½ao sera acrescentado a mensagem de conclusï¿½o
		// aviso de nï¿½o alteraï¿½ï¿½o dos dados da inscriï¿½ï¿½o.
		if(flagNaoConfirmado){
			mensagemSucesso  = "Im�vel de matr�cula "
				+ imovelAtualizar.getId().toString()
				+ " atualizado com sucesso. \n"
				+ " Aten��o! Dados da inscri��o n�o alterados devido ao im�vel estar em processo de faturamento.";
		}
		
		if(sessao.getAttribute("caminhoVoltarPromais")!=null){
		
			montarPaginaSucesso(httpServletRequest, "Im�vel de matr�cula "
					+ imovelAtualizar.getId().toString()
					+ " atualizado com sucesso.",
					"Realizar outra Manuten��o de Im�vel",
					"exibirFiltrarImovelAction.do?menu=sim",
					"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
							+ imovelAtualizar.getId().toString(),
					"Informar Ocorr�ncias / Anormalidades",
					"Retornar ao Consultar Im�vel.",
					(String)sessao.getAttribute("caminhoVoltarPromais")+".do?promais=nao");
			
			sessao.setAttribute("promaisExecutado", "sim");
			sessao.setAttribute("idImovelPromaisExecutado", imovelAtualizar.getId());
			
			
		}else{
			montarPaginaSucesso(httpServletRequest, mensagemSucesso,
					"Realizar outra Manuten��o de Im�vel",
					"exibirFiltrarImovelAction.do?menu=sim",
					"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
							+ imovelAtualizar.getId().toString(),
					"Informar Ocorr�ncias / Anormalidades");
		}

		if ( sessao.getAttribute("remove") == null ){
			
		
		sessao.removeAttribute("colecaoEnderecos");
		sessao.removeAttribute("imovelAtualizacao");
		sessao.removeAttribute("localidade");
		sessao.removeAttribute("setorComercial");
		sessao.removeAttribute("quadra");
		sessao.removeAttribute("colecaoImovelSubCategorias");
		sessao.removeAttribute("colecaoImovelRamosAtividadesRemovidos");
		sessao.removeAttribute("idQuadraInicial");
		}
		
		if (sessao.getAttribute("colecaoClientesImoveisRemovidos") != null) {
			sessao.removeAttribute("colecaoClientesImoveisRemovidos");
		}
		if (sessao.getAttribute("colecaoImovelSubcategoriasRemoviadas") != null) {
			sessao.removeAttribute("colecaoImovelSubcategoriasRemoviadas");
		}
		

		// [FS0035] ï¿½ Verifica se existe RA e ordem pendente
		if (imovelAtualizar != null && imovelAtualizar.getId() != null && retorno.getName().equals("telaSucesso")) {

			//Caso nï¿½o tenha permissï¿½o especial verifica se tem apenas ï¿½umaï¿½ RA pendente (RGAT_CDSITUACAO = 1) 
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");
			filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao.solicitacaoTipo");
			filtroRegistroAtendimento.adicionarParametro( 
					new ParametroSimples (FiltroRegistroAtendimento.IMOVEL_ID , 
							imovelAtualizar.getId() ));
			filtroRegistroAtendimento.adicionarParametro( 
					new ParametroSimples (FiltroRegistroAtendimento.CODIGO_SITUACAO , 
							ConstantesSistema.SITUACAO_PENDENTE ));
			
			Collection colecaoRegistroAtendiomento = fachada.pesquisar(
					filtroRegistroAtendimento, RegistroAtendimento.class.getName());
			
			if ( colecaoRegistroAtendiomento != null && colecaoRegistroAtendiomento.size() == 1 ) {
				
				RegistroAtendimento registroAtendimento = (RegistroAtendimento) 
										Util.retonarObjetoDeColecao(colecaoRegistroAtendiomento);
				
				//Caso exista OS pendente o RA nï¿½o poderï¿½ ser encerrado. Retornar ao fluxo principal
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(
						new ParametroSimples(FiltroOrdemServico.REGISTRO_ATENDIMENTO_ID, 
								registroAtendimento.getId()));
				filtroOrdemServico.adicionarParametro(
						new ParametroSimples(FiltroOrdemServico.SITUACAO, 
								ConstantesSistema.SITUACAO_PENDENTE ) );
				
				Collection colecaoOrdemServico = fachada.pesquisar(
						filtroOrdemServico, OrdemServico.class.getName());
				
				if ( colecaoOrdemServico == null || colecaoOrdemServico.isEmpty() ) {
					
					// grava o "numeroRA" na sessao para ser recuperado 
					// no action "ExibirEncerrarRegistroAtendimentoAction".
					sessao.setAttribute("numeroRA",registroAtendimento.getId());
						
					// coloca no request a URL do action que sera processado caso
					// o usuario pressionar o botao confirmar na tela de confirmacao.
					httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/exibirEncerrarRegistroAtendimentoAction.do");
					
					httpServletRequest.setAttribute("cancelamento", "TRUE");
					httpServletRequest.setAttribute("nomeBotao1", "Sim");
					httpServletRequest.setAttribute("nomeBotao2", "N�o");
					sessao.setAttribute("idImovel",imovelAtualizar.getId().toString());
					
					//Monta o array de variaveis que sera montada na mensagem de confirmaï¿½ï¿½o 
					String[] mensagem = new String[3];
					
					mensagem[0] = registroAtendimento.getId().toString();
					
					mensagem[1] = registroAtendimento.getSolicitacaoTipoEspecificacao().
										getSolicitacaoTipo().getDescricao();
					
					mensagem[2] = registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao();
						
                    // Monta a pï¿½gina de confirmaï¿½ï¿½o para perguntar se o usuï¿½rio
					// deseja encerrar o registro de atendimento.
					return montarPaginaConfirmacao("atencao.encerrar_ra_atualizar_imovel",
							httpServletRequest, actionMapping, mensagem);
						
				}
			}
			
				
		}		

		return retorno;
	}

	/**
	 *		1.2.	Caso a alteraï¿½ï¿½o da inscriï¿½ï¿½o acarrete a mudanï¿½a do grupo de faturamento do imï¿½vel 
	 *	(grupo de faturamento origem (FTGR_ID da tabela ROTA para ROTA_ID=ROTA_ID da tabela QUADRA para QDRA_ID=QDRA_IDANTERIOR)
	 * 	diferente do grupo de faturamento destino (FTGR_ID da tabela ROTA para ROTA_ID=ROTA_ID da tabela QUADRA para QDRA_ID=QDRA_IDATUAL)):
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param imovelSemAtualizacao
	 * @param resultadoAbaLocalidade
	 * @param helperLocalidade
	 * @return
	 */
	private boolean verificarInscricaoAlterada(Imovel imovelSemAtualizacao,
			ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade,
			ImovelAbaLocalidadeHelper helperLocalidade) {
		
		boolean retorno = false;
		
		if(imovelSemAtualizacao.getLocalidade()!=null && resultadoAbaLocalidade.getLocalidade()!=null){
			if(imovelSemAtualizacao.getLocalidade().getId()
					.compareTo(resultadoAbaLocalidade.getLocalidade().getId())!=0){
				retorno = true;
			}
		}
		if(imovelSemAtualizacao.getSetorComercial()!=null && resultadoAbaLocalidade.getSetorComercial()!=null){
			if(imovelSemAtualizacao.getSetorComercial().getId()
					.compareTo(resultadoAbaLocalidade.getSetorComercial().getId())!=0){
				retorno = true;
			}
		}
		if(imovelSemAtualizacao.getQuadra()!=null && resultadoAbaLocalidade.getQuadra()!=null){
			if(imovelSemAtualizacao.getQuadra().getId()
					.compareTo(resultadoAbaLocalidade.getQuadra().getId())!=0){
				retorno = true;
			}
		}	
		if(imovelSemAtualizacao.getQuadraFace()!=null && resultadoAbaLocalidade.getQuadraFace()!=null){
			if(imovelSemAtualizacao.getQuadraFace().getId()
					.compareTo(resultadoAbaLocalidade.getQuadraFace().getId())!=0){
				retorno = true;
			}
		}
		
		if(imovelSemAtualizacao.getLote() != new Short(helperLocalidade.getLote()).shortValue()){
			retorno = true;
		}
		if(imovelSemAtualizacao.getSubLote() != new Short(helperLocalidade.getSublote()).shortValue()){
			retorno = true;
		}

		return retorno;
	}
	
	/**
	 * [SB0004] ï¿½ Verificar Alteraï¿½ï¿½o da Inscriï¿½ï¿½o do Imï¿½vel
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param imovelSemAtualizacao
	 * @param resultadoAbaLocalidade
	 * @param helperLocalidade
	 * @return
	 */
	private ActionForward verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idImovel, Integer idQuadraAnterior,Integer idQuadraAtual, 
			HttpServletRequest httpServletRequest, ActionMapping actionMapping, Fachada fachada, Imovel imovel,
			ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade, ImovelAbaLocalidadeHelper helperLocalidade, HttpSession sessao) {
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		FaturamentoGrupo[] faturamentoGrupos = new FaturamentoGrupo[2];
		FaturamentoGrupo faturamentoGrupoOrigem = new FaturamentoGrupo();
		FaturamentoGrupo faturamentoGrupoDestino = new FaturamentoGrupo();
		//boolean retorno = false;
		
		//localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		/*
		 * 1. Caso os dados da inscriï¿½ï¿½o tenham sido alterados e o indicador de alteraï¿½ï¿½o da inscriï¿½ï¿½o esteja ativo 
		 * (PARM_ICALTERACAOINSCRICAOIMOVEL=1 da tabela SISTEMA_PARAMETROS):
		 */
		if (sistemaParametro.getIndicadorAlteracaoInscricaoImovel().equals(ConstantesSistema.SIM)){
			
			/*
			 * 1.1.Caso o imï¿½vel esteja em processo de faturamento (existe ocorrï¿½ncia na tabela MOVIMENTO_ROTEIRO_EMPRESA para IMOV_ID=IMOV_ID 
			 * da tabela IMOVEL e MREM_AMMOVIMENTO=FTGR_AMREFERENCIA da tabela FATURAMENTO_GRUPO para FTGR_ID=FTGR_ID da 
			 * tabela MOVIMENTO_ROTEIRO_EMPRESA):
			 */
			//if (fachada.verificarImovelEmProcessoDeFaturamento(imovel.getId())){
				
				httpServletRequest.setAttribute("nomeBotao1", "Sim");
				httpServletRequest.setAttribute("nomeBotao3", "N�o");
				httpServletRequest.setAttribute("confirmacaoDupla", "ok");

				sessao.setAttribute("remove" , "nao");
				
				return montarPaginaConfirmacaoWizard("atencao.imovel_em_processo_de_faturamento",
				httpServletRequest, actionMapping);
			//}
			
		}
		/*
		 * 2.Caso os dados da inscriï¿½ï¿½o tenham sido alterados e o indicador de alteraï¿½ï¿½o da inscriï¿½ï¿½o esteja inativo 
		 * (PARM_ICALTERACAOINSCRICAOIMOVEL=2 da tabela SISTEMA_PARAMETROS) e o imï¿½vel nï¿½o tenha rota alternativa 
		 * (ROTA_IDALTERNATIVA com o valor nulo na tabela IMOVEL):
		 */
		else{
			
			faturamentoGrupos = fachada.verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(idQuadraAnterior, idQuadraAtual);
			faturamentoGrupoOrigem = faturamentoGrupos[0];
			faturamentoGrupoDestino = faturamentoGrupos[1];
			
			
			
			//verifica se o imovel foi mudado de grupo ao ser alterado a quadra
			if ( !faturamentoGrupoOrigem.getId().toString().equals(faturamentoGrupoDestino.getId().toString()) ) {

				//1.3.1
				if ( faturamentoGrupoOrigem.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
				
					//1.3.1.1
					if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoOrigem) ) {
					
						//1.3.1.1.1 
						//1.3.1.1.2
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao3", "N�o");
						httpServletRequest.setAttribute("confirmacaoDupla", "ok");
						
						sessao.setAttribute("remove" , "nao");
						
						return montarPaginaConfirmacaoWizard(
								"atencao.faturamento_grupo_origem_dados_leitura_gerados",
								httpServletRequest, actionMapping, idImovel.toString(), faturamentoGrupoDestino.getId().toString() ,faturamentoGrupoOrigem.getId().toString()   );
						
					}//1.3.1.2 
					else {
						
						//1.3.1.2.1
						if ( faturamentoGrupoDestino.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
							
							//1.3.1.2.1.1.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {
								//1.3.1.2.1.1.1 
								//1.3.1.2.1.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "N�o");
								httpServletRequest.setAttribute("confirmacaoDupla", "ok");

								sessao.setAttribute("remove" , "nao");
								
								return 	montarPaginaConfirmacaoWizard(
										"atencao.faturamento_grupo_destino_dados_leitura_gerados",
										httpServletRequest, actionMapping, idImovel.toString(), faturamentoGrupoDestino.getId().toString() );
						
							}//1.3.1.2.1.2
							else {
								//efetivar a alteraï¿½ï¿½o da inscriï¿½ï¿½o na tabela IMOVEL
								atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
							}
							
						}//1.3.1.2.2
						else {
							
							//1.3.1.2.2.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {
								
								//1.3.1.2.2.1.1 
								//1.3.1.2.2.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "N�o");
								httpServletRequest.setAttribute("confirmacaoDupla", "ok");

								sessao.setAttribute("remove" , "nao");
								
								return montarPaginaConfirmacaoWizard(
										"atencao.dados_leituras_gerados_faturamento_grupo_destino",
										httpServletRequest, actionMapping, idImovel.toString(), faturamentoGrupoDestino.getId().toString() );

								//[SB0005 ï¿½ Preparar Alteraï¿½ï¿½o Inscriï¿½ï¿½o no Encerramento Faturamento]
							} else {
								//1.3.1.2.2.2
								//efetivar a alteraï¿½ï¿½o da inscriï¿½ï¿½o na tabela IMOVEL
								atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
							}
						}
					}
				} //1.3.2
				else {
					//1.3.2.1
					if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoOrigem) ) {
						//1.3.2.1.1
						//1.3.2.1.2
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao3", "N�o");
						httpServletRequest.setAttribute("confirmacaoDupla", "ok");

						sessao.setAttribute("remove" , "nao");
						
						return montarPaginaConfirmacaoWizard(
								"atencao.dados_leituras_gerados_faturamento_grupo_origem",
									httpServletRequest, actionMapping, idImovel.toString(), 
										faturamentoGrupoDestino.getId().toString(), faturamentoGrupoOrigem.getId().toString() );
					}//1.3.2.2
					else {
						//1.3.2.2.1
						if ( faturamentoGrupoDestino.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
							//1.3.2.2.1.1
							//1.3.2.2.1.2
							httpServletRequest.setAttribute("nomeBotao1", "Sim");
							httpServletRequest.setAttribute("nomeBotao3", "N�o");
							httpServletRequest.setAttribute("confirmacaoDupla", "ok");

							sessao.setAttribute("remove" , "nao");
							
							return montarPaginaConfirmacaoWizard(
									"atencao.faturamento_grupo_destino_ja_faturado",
										httpServletRequest, actionMapping, idImovel.toString(), 
											faturamentoGrupoDestino.getId().toString() );
						}//1.3.2.2.2
						else {
							//1.3.2.2.2.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino) ) {
								//1.3.2.2.2.1.1
								//1.3.2.2.2.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "N�o");
								httpServletRequest.setAttribute("confirmacaoDupla", "ok");

								sessao.setAttribute("remove" , "nao");
								
								return montarPaginaConfirmacaoWizard(
										"atencao.faturamento_grupo_destino_nao_faturado",
											httpServletRequest, actionMapping, idImovel.toString(), 
												faturamentoGrupoDestino.getId().toString() );
							}//1.3.2.2.2.2 
							else {
								//efetivar a alteraï¿½ï¿½o da inscriï¿½ï¿½o na tabela IMOVEL
								atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
							}
						}
					}
				}
			}
		}
		
		//efetivar a alteraï¿½ï¿½o da inscriï¿½ï¿½o na tabela IMOVEL
		atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
		
		
		return retorno;
	}
	
	/**
	 * [SB0005] ï¿½ Preparar Alteraï¿½ï¿½o Inscriï¿½ï¿½o no Encerramento Faturamento
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 */
	private void prepararAlteracaoInscricaoEncerramentoFaturamento( Imovel imovel, Imovel imovelSemAtualizacao,
			ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade, Fachada fachada, ImovelAbaLocalidadeHelper helperLocalidade, Usuario usuario ){
		
		//[FS0038 - Verificar Existência de Alteração de Inscrição Pendente para o Imóvel]
		fachada.verificarExistenciaAlteracaoInscricaoPendenteImovel(imovel);

		//[FS0039] ï¿½ Verificar Duplicidade de Inscriï¿½ï¿½o
		verificarDuplicidadeInscricao( resultadoAbaLocalidade, helperLocalidade, fachada );
		
		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		
		imovelInscricaoAlterada.setImovel(imovel);
		imovelInscricaoAlterada.setFaturamentoGrupo(null);
		//Dados da inscriï¿½ï¿½o Anterior
		imovelInscricaoAlterada.setLocalidadeAnterior(imovelSemAtualizacao.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAnterior(imovelSemAtualizacao.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAnterior(imovelSemAtualizacao.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAnterior(
				imovelSemAtualizacao.getQuadraFace()!=null?imovelSemAtualizacao.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAnterior(imovelSemAtualizacao.getLote());
		imovelInscricaoAlterada.setSubLoteAnterior(imovelSemAtualizacao.getSubLote());
		//Dados da inscriï¿½ï¿½o Atual
		imovelInscricaoAlterada.setLocalidadeAtual(resultadoAbaLocalidade.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAtual(resultadoAbaLocalidade.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAtual(resultadoAbaLocalidade.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAtual(
				resultadoAbaLocalidade.getQuadraFace()!=null?resultadoAbaLocalidade.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAtual(new Short(helperLocalidade.getLote()));
		imovelInscricaoAlterada.setSubLoteAtual(new Short(helperLocalidade.getSublote()));
		
		imovelInscricaoAlterada.setIndicadorAtualizado(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorAtualizacaoExcluida(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorImovelExcluido(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorErroAlteracao(null);
		
		if (this.getSistemaParametro().getIndicadorAlteracaoInscricaoImovel().toString().equals(""+ConstantesSistema.SIM)){
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.NAO);
		} else {
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.SIM);
		}
		
		imovelInscricaoAlterada.setUsuarioAlteracao(usuario);
		imovelInscricaoAlterada.setDataAlteracaoOnline(new Date());
		imovelInscricaoAlterada.setDataAlteracaoBatch(null);
		imovelInscricaoAlterada.setUltimaAlteracao(new Date());
		
		fachada.inserir(imovelInscricaoAlterada);
		
	}
	
	/**
	 * [FS0039] ï¿½ Verificar Duplicidade de Inscrição
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @return
	 */
	private void verificarDuplicidadeInscricao( ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade,  
			ImovelAbaLocalidadeHelper helperLocalidade, Fachada fachada ) {
		
		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		
		Integer idQuadraFace = null;
		if ( resultadoAbaLocalidade.getQuadraFace() != null && resultadoAbaLocalidade.getQuadraFace().getId() != null ) {
			idQuadraFace = resultadoAbaLocalidade.getQuadraFace().getId();
		}
		imovelInscricaoAlterada = fachada.verificarDuplicidadeImovelInscricaoAlterada(resultadoAbaLocalidade.getLocalidade().getId(), 
				resultadoAbaLocalidade.getSetorComercial().getId(), resultadoAbaLocalidade.getQuadra().getId(),
				idQuadraFace, new Integer(helperLocalidade.getLote()), new Integer(helperLocalidade.getSublote())
				);
		

		if ( imovelInscricaoAlterada != null && imovelInscricaoAlterada.getId() != null ) {
			String[] idImovelInscricaoAlterada = new String[1];
			idImovelInscricaoAlterada[0] = imovelInscricaoAlterada.getImovel().getId().toString();
			throw new ActionServletException("atencao.duplicidade_imovel_inscricao_alterada", 
					"atualizarImovelWizardAction.do?action=exibirAtualizarImovelLocalidadeAction",
					null,
					idImovelInscricaoAlterada);
		}
		
	}

	/**
	 * Atualiza Imovel
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @param imovel
	 */
	private void atualizarImovel(Imovel imovel,  ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade, ImovelAbaLocalidadeHelper helperLocalidade ){
		
		imovel.setLocalidade(resultadoAbaLocalidade.getLocalidade());
		imovel.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		imovel.setQuadra(resultadoAbaLocalidade.getQuadra());
		imovel.setQuadraFace(resultadoAbaLocalidade.getQuadraFace());
		imovel.setLote(new Short(helperLocalidade.getLote()).shortValue());
		imovel.setSubLote(new Short(helperLocalidade.getSublote()).shortValue());
	}

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 18/09/2010
	 * @param imovelAtualizar
	 * @param fachada
	 * @return
	 */
	private Imovel pesquisarImovel(Integer imovelAtualizar, Fachada fachada){
		
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovelAtualizar));
		
		Collection<Imovel> colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
		
		return imovel;
	}
	

}
