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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroClassePressao;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroFatorCorrecao;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class InserirHidrometroAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obt�m o action form
		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;
		
//		 Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSA��O ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_HIDROMETRO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_HIDROMETRO_INSERIR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSA��O ----------------


		Fachada fachada = Fachada.getInstancia();

		// Define a a��o de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Filtro para obter o local de armazenagem ativo de id informado
		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

		filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalArmazenagem.ID, new Integer(hidrometroActionForm.getIdLocalArmazenagem()), ParametroSimples.CONECTOR_AND));
		filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalArmazenagem.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoHidrometroLocalArmazenagem == null
				|| colecaoHidrometroLocalArmazenagem.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.hidrometro_local_armazenagem.inexistente");
		}

		// Cria o objeto classe metrol�gica e seta o id
		HidrometroClasseMetrologica hidrometroClasseMetrologica = new HidrometroClasseMetrologica();

		hidrometroClasseMetrologica.setId(new Integer(hidrometroActionForm.getIdHidrometroClasseMetrologica()));

		// Cria o objeto hidr�metro marca e seta o id
		HidrometroMarca hidrometroMarca = new HidrometroMarca();

		hidrometroMarca.setId(new Integer(hidrometroActionForm.getIdHidrometroMarca()));
		
		/**
		 * [FS0004]- Verificar Preenchimento dos campos
		 * Caso 3
		 */
		
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		
		filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID,hidrometroMarca.getId().toString()));
		
		
		Collection colecaoHidrometroMarcaBase = fachada.pesquisar(filtroHidrometroMarca,HidrometroMarca.class.getName());
		
		HidrometroMarca hidrometroMarcaBase = (HidrometroMarca) colecaoHidrometroMarcaBase.iterator().next(); 
		
		if(hidrometroActionForm.getFixo() != null
				&& !hidrometroActionForm.getFixo().trim().equals("")
				&& !hidrometroMarcaBase.getCodigoHidrometroMarca().
					equalsIgnoreCase(hidrometroActionForm.getFixo().substring(3))){
			throw new ActionServletException("atencao.marca_incompativel_numero_fixo");
		}
		
		
		String fixo = hidrometroActionForm.getFixo();
		/*
		/**
		 * [FS0008]-Montar ano de fabricacao
		
		String anoFabricacaoForm = hidrometroActionForm.getAnoFabricacao();
		
		String aux1 = fixo.substring(2,3);
		
		Integer aux2= new Integer(aux1);
		
		if (anoFabricacaoForm.equalsIgnoreCase("") || anoFabricacaoForm == null) {
			throw new ActionServletException("");
		} else {
			
			if (aux2 >= 85) {
				char[] anoFabricacaoChar = anoFabricacaoForm.toCharArray();
				anoFabricacaoChar[0] = '1';
				anoFabricacaoChar[1] = '9';
				anoFabricacaoForm = (new String(anoFabricacaoChar) + aux2);
			} else if (aux2 >= 00) {
				char[] anoFabricacaoChar = anoFabricacaoForm.toCharArray();
				anoFabricacaoChar[0] = '2';
				anoFabricacaoChar[1] = '0';
				anoFabricacaoForm = (new String(anoFabricacaoChar) + aux2);
			}

		}*/

		
		String tombamento = null;
		if (hidrometroActionForm.getTombamento() != null
				&& !"".equals(hidrometroActionForm.getTombamento()) ) {
			// [FS0010] - Verificar exist�ncia do n�mero do tombamento
			Integer quantidadeOcorrencia = fachada.pesquisarNumeroHidrometroFaixaCount(hidrometroActionForm.getTombamento());
			
			if (quantidadeOcorrencia != null && quantidadeOcorrencia > 0) {
				throw new ActionServletException("atencao.numero_tombamento.ja_cadastrado");
			}
			
			tombamento = hidrometroActionForm.getTombamento();
		}
		
		/**
		 * [FS0004]- Verificar Preenchimento dos campos
		 * Caso 2
		 */
		//Cria o objeto hidr�metro capacidade e seta o id
		HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();

		hidrometroCapacidade.setId(new Integer(hidrometroActionForm.getIdHidrometroCapacidade()));
		
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID,hidrometroCapacidade.getId().toString()));
			
		Collection colecaoHidrometroCapacidadeBase = fachada.pesquisar(filtroHidrometroCapacidade,HidrometroCapacidade.class.getName());
		
		HidrometroCapacidade hidrometroCapacidadeBase = (HidrometroCapacidade)colecaoHidrometroCapacidadeBase.iterator().next(); 
		
		if(hidrometroActionForm.getFixo() != null
				&& !hidrometroActionForm.getFixo().trim().equals("")
				&& !hidrometroCapacidadeBase.getCodigoHidrometroCapacidade().
				equalsIgnoreCase(hidrometroActionForm.getFixo().substring(0,1))){
			throw new ActionServletException("atencao.capacidade_incompativel_numero_fixo");
		}
		

		// Cria o objeto hidr�metro di�metro e seta o id
		HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();

		hidrometroDiametro.setId(new Integer(hidrometroActionForm.getIdHidrometroDiametro()));

		// Cria o objeto hidr�metro tipo e seta o id
		HidrometroTipo hidrometroTipo = null;

		if (hidrometroActionForm.getIdHidrometroTipo() != null
				&& !hidrometroActionForm.getIdHidrometroTipo().trim().equals("")
				&& !hidrometroActionForm.getIdHidrometroTipo().trim().equals("-1")) {
			
			hidrometroTipo = new HidrometroTipo();
			hidrometroTipo.setId(new Integer(hidrometroActionForm.getIdHidrometroTipo()));
		}
		
		// Cria o objeto hidr�metro tipo e seta o id
		HidrometroRelojoaria hidrometroRelojoaria = null;

		if(hidrometroActionForm.getIdHidrometroRelojoaria() != null && Integer.parseInt(hidrometroActionForm.getIdHidrometroRelojoaria()) > ConstantesSistema.NUMERO_NAO_INFORMADO){
			
			hidrometroRelojoaria = new HidrometroRelojoaria();
			
			hidrometroRelojoaria.setId(new Integer(hidrometroActionForm.getIdHidrometroRelojoaria()));
			
		}
		

		// Cria o objeto hidr�metro local armazenagem e seta o id
		HidrometroLocalArmazenagem hidrometroLocalArmazenagem = new HidrometroLocalArmazenagem();

		hidrometroLocalArmazenagem.setId(new Integer(hidrometroActionForm.getIdLocalArmazenagem()));

		// Cria o objeto hidr�metro situacao e seta o id
		HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();

		hidrometroSituacao.setId(HidrometroSituacao.DISPONIVEL);

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		Date dataAquisicao = null;
		try {
			dataAquisicao = formatoData.parse(hidrometroActionForm.getDataAquisicao());
		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}

		Date dataAquisicaoAnterior = null;
		try {
			dataAquisicaoAnterior = formatoData.parse("01/01/1985");
		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}
		Calendar dataAtual = new GregorianCalendar();
		int anoAtual = dataAtual.get(Calendar.YEAR);
		// caso a data de aquisi��o seja menor que a data atual
		if (dataAquisicao.after(new Date())) {
			throw new ActionServletException("atencao.data.aquisicao.nao.superior.data.corrente");
		}
		// caso a data de aquisi��o seja menor que 01/01/1985
		if (dataAquisicao.before(dataAquisicaoAnterior)) {
			throw new ActionServletException("atencao.data.aquisicao.nao.inferior.1985");
		}
		
		Integer anoFabricacao = new Integer(hidrometroActionForm.getAnoFabricacao());
		// [FS0009] - Validar ano de fabrica��o
		// caso o ano de fabrica��o seja maior que o atual
		if (anoFabricacao > anoAtual) {
			throw new ActionServletException("atencao.ano.fabricacao.nao.superior.data.corrente");
		}
		// caso o ano de fabrica��o seja menor que 1985
		if (anoFabricacao < 1985) {
			throw new ActionServletException("atencao.ano.fabricacao.nao.inferior.1985");
		}

		Integer anoDataAquisicao = Util.getAno(dataAquisicao);
		// caso a data de aquisi��o seja menor que o ano fabrica��o
		if (anoDataAquisicao < anoFabricacao) {
			throw new ActionServletException("atencao.ano.aquisicao.menor.ano.fabricacao");
		}
		
		BigDecimal vazaoTransicao = null;
		//Vazao Transacao
		if (hidrometroActionForm.getVazaoTransicao() != null
				&& !"".equals(hidrometroActionForm.getVazaoTransicao() ) ) {
			vazaoTransicao =  Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoTransicao() ) ;
		}
		
		BigDecimal vazaoNominal = null;
		//Vazao Nominal
		if (hidrometroActionForm.getVazaoNominal() != null
				&& !"".equals(hidrometroActionForm.getVazaoNominal() ) ) {
			vazaoNominal = Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoNominal() );
		}
		
		BigDecimal vazaoMinima = null;
		//Vazao Minima
		if (hidrometroActionForm.getVazaoMinima() != null
				&& !"".equals(hidrometroActionForm.getVazaoMinima() ) ) {
			vazaoMinima = Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoMinima() );
		}
		
		Integer notaFiscal = null;
		if ( hidrometroActionForm.getNotaFiscal() != null
				&& !"".equals(hidrometroActionForm.getNotaFiscal() ) ) {
			notaFiscal = new Integer( hidrometroActionForm.getNotaFiscal() ) ;
		}
		
		Short tempoGarantiaAnos = null;
		if ( hidrometroActionForm.getTempoGarantiaAnos() != null
				&& !"".equals(hidrometroActionForm.getTempoGarantiaAnos() ) ) {
			tempoGarantiaAnos = new Short( hidrometroActionForm.getTempoGarantiaAnos());
		}

		// Cria o objeto hidr�metro fator corre��o e seta o id
		HidrometroFatorCorrecao hidrometroFatorCorrecao = null;

		if (hidrometroActionForm.getIdHidrometroFatorCorrecao() != null
				&& !hidrometroActionForm.getIdHidrometroFatorCorrecao().trim().equals("")
				&& !hidrometroActionForm.getIdHidrometroFatorCorrecao().trim().equals("-1")) {
			
			hidrometroFatorCorrecao = new HidrometroFatorCorrecao();
			
			hidrometroFatorCorrecao.setId(new Integer(hidrometroActionForm.getIdHidrometroFatorCorrecao()));
		}

		// Cria o objeto hidr�metro classe press�o e seta o id
		HidrometroClassePressao hidrometroClassePressao = null;

		if (hidrometroActionForm.getIdHidrometroClassePressao() != null
				&& !hidrometroActionForm.getIdHidrometroClassePressao().trim().equals("")
				&& !hidrometroActionForm.getIdHidrometroClassePressao().trim().equals("-1")) {
			
			hidrometroClassePressao = new HidrometroClassePressao();
			
			hidrometroClassePressao.setId(new Integer(hidrometroActionForm.getIdHidrometroClassePressao()));
		}
		
		// Cria o objeto hidrom�tro
		Hidrometro hidrometro = null;
		
		Short indicadorMacromedidor = null;
		
		if (hidrometroActionForm.getIndicadorMacromedidor() != null && !"".equals(hidrometroActionForm.getIndicadorMacromedidor())) {
			if (hidrometroActionForm.getIndicadorMacromedidor().equals(Hidrometro.INDICADOR_REDE_ESGOTO)) {
				indicadorMacromedidor = new Short(Hidrometro.INDICADOR_MICROMEDIDOR);
			} else {
				indicadorMacromedidor = new Short(hidrometroActionForm.getIndicadorMacromedidor());
			}
		}
		
		try {
			hidrometro = new Hidrometro(
					null,
					// numero
					formatoData.parse(hidrometroActionForm.getDataAquisicao()),
					// dataAquisicao
					new Short(hidrometroActionForm.getAnoFabricacao()),
					// anoFabricacao
					new Short(hidrometroActionForm.getIndicadorOperacional()),
					// indicadorOperacional
					null,
					// dataUltimaRevisao
					null,
					// dataBaixa
					new Integer("0"),
					// numeroLeituraAcumulada
					new Short(hidrometroActionForm.getIdNumeroDigitosLeitura()),
					// numeroDigitosLeitura
					new Date(),
					// ultimaAlteracao
					hidrometroTipo,
					// hidr�metroTipo
					hidrometroSituacao,
					// hidrometroSituacao
					hidrometroMarca,
					// hidrometroMarca
					hidrometroCapacidade,
					// hidrometroCapacidade
					null,
					// hidrometroMotivoBaixa
					hidrometroLocalArmazenagem,
					// hidrometroLocalArmazenagem
					hidrometroClasseMetrologica,
					// hidrometroClasseMetrologica
					hidrometroDiametro,
					//hidrometro Relojoaria
					hidrometroRelojoaria,
					//vazao transicao
					vazaoTransicao,
					//vazao nominal
					vazaoNominal,
					//vazao minima
					vazaoMinima,
					//nota fiscal
					notaFiscal,
					//tempo garantia em anos
					tempoGarantiaAnos,
					indicadorMacromedidor,
					tombamento,
					hidrometroClassePressao,
					hidrometroFatorCorrecao);
			
			// hidrometroDiametro
		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}

		
		// Obt�m a faixa inicial
		
		Integer faixaInicial = null;
		// Obt�m a faixa final
		Integer faixaFinal = null;

		Integer intervalo = null;
		
		if (hidrometroActionForm.getFaixaFinal() != null
				&& !hidrometroActionForm.getFaixaFinal().trim().equals("")
				&& !hidrometroActionForm.getFaixaFinal().trim().equals("-1")
				&& hidrometroActionForm.getFaixaFinal() != null
				&& !hidrometroActionForm.getFaixaFinal().trim().equals("")
				&& !hidrometroActionForm.getFaixaFinal().trim().equals("-1")) {
			
			faixaInicial = new Integer(hidrometroActionForm.getFaixaInicial());
			
			faixaFinal = new Integer(hidrometroActionForm.getFaixaFinal());
			
			intervalo = new Integer((faixaFinal.intValue() - faixaInicial.intValue()) + 1);
		} else {
			intervalo = 1;
		}
		
		if (hidrometroActionForm.getNumeroHidrometro() != null && !"".equals(hidrometroActionForm)) {
			hidrometro.setNumero(hidrometroActionForm.getNumeroHidrometro());
		}
		
		if (hidrometroActionForm.getIndicadorMacromedidor().equals(Hidrometro.INDICADOR_MACROMEDIDOR) || hidrometroActionForm.getIndicadorMacromedidor().equals(Hidrometro.INDICADOR_MICROMEDIDOR)) {
			hidrometro.setIndicadorFinalidade(new Short("1"));
		} else if (hidrometroActionForm.getIndicadorMacromedidor().equals(Hidrometro.INDICADOR_REDE_ESGOTO)) {
			hidrometro.setIndicadorFinalidade(new Short("2"));
		}
		
		//------------ REGISTRAR TRANSA��O ----------------
        hidrometro.setOperacaoEfetuada(operacaoEfetuada);
        hidrometro.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	registradorOperacao.registrarOperacao(hidrometro);
        //------------ REGISTRAR TRANSA��O ----------------

		// Inseri hidr�metro
		fachada.inserirHidrometro(hidrometro, fixo, faixaInicial, faixaFinal);

		// M�todo utilizado para montar a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, 
				intervalo.toString() + " Hidr�metro(s) inserido(s) com sucesso.",
				"Inserir outro Hidr�metro", "exibirInserirHidrometroAction.do?menu=sim");

		// Remove objetos da sess�o
		sessao.removeAttribute("HidrometroActionForm");
		sessao.removeAttribute("colecaoIntervalo");
		sessao.removeAttribute("colecaoHidrometroClasseMetrologica");
		sessao.removeAttribute("colecaoHidrometroMarca");
		sessao.removeAttribute("colecaoHidrometroDiametro");
		sessao.removeAttribute("colecaoHidrometroCapacidade");
		sessao.removeAttribute("colecaoHidrometroTipo");

		return retorno;
	}
}
