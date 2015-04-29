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
package gsan.gui.micromedicao.hidrometro;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.hidrometro.FiltroHidrometro;
import gsan.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gsan.micromedicao.hidrometro.FiltroHidrometroClasseMetrologica;
import gsan.micromedicao.hidrometro.FiltroHidrometroClassePressao;
import gsan.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gsan.micromedicao.hidrometro.FiltroHidrometroFatorCorrecao;
import gsan.micromedicao.hidrometro.FiltroHidrometroMarca;
import gsan.micromedicao.hidrometro.FiltroHidrometroRelojoaria;
import gsan.micromedicao.hidrometro.FiltroHidrometroTipo;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroCapacidade;
import gsan.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gsan.micromedicao.hidrometro.HidrometroClassePressao;
import gsan.micromedicao.hidrometro.HidrometroDiametro;
import gsan.micromedicao.hidrometro.HidrometroFatorCorrecao;
import gsan.micromedicao.hidrometro.HidrometroMarca;
import gsan.micromedicao.hidrometro.HidrometroRelojoaria;
import gsan.micromedicao.hidrometro.HidrometroTipo;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
 * @created 13 de Setembro de 2005
 */
public class ExibirAtualizarHidrometroAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("atualizarHidrometro");

        //Obt�m o action form
        AtualizarHidrometroActionForm atualizarHidrometroActionForm = (AtualizarHidrometroActionForm) actionForm;

        Collection colecaoHidrometroCapacidade = null;

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        String idHidrometro = httpServletRequest.getParameter("idRegistroAtualizacao");
        if (idHidrometro == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				idHidrometro = (String) sessao.getAttribute("idHidrometro");
			}else{
				idHidrometro = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}

        //Obt�m a facahda
        Fachada fachada = Fachada.getInstancia();
        Hidrometro hidrometro = null;

        //Verifica se os objetos est�o na sess�o
        if (idHidrometro != null && !idHidrometro.equals("")) {

            FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
            filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.ID, idHidrometro));
            filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroRelojoaria");

            Collection hidrometros = fachada.pesquisar(filtroHidrometro, Hidrometro.class.getName());

            if (hidrometros != null && !hidrometros.isEmpty()) {
            	hidrometro = (Hidrometro) ((List) hidrometros).get(0);
                sessao.setAttribute("hidrometro", hidrometro);
                atualizarHidrometroActionForm.setNumeroHidrometro(formatarResultado(hidrometro.getNumero()));
                atualizarHidrometroActionForm.setAnoFabricacao(formatarResultado("" + hidrometro.getAnoFabricacao()));
                SimpleDateFormat dataFormatoAtual = new SimpleDateFormat("dd/MM/yyyy");
                //joga em dataInicial a parte da data
                String dataAquisicao = dataFormatoAtual.format(hidrometro.getDataAquisicao());

                atualizarHidrometroActionForm.setDataAquisicao(formatarResultado(dataAquisicao));
                atualizarHidrometroActionForm.setIdHidrometroCapacidade(formatarResultado("" + hidrometro.getHidrometroCapacidade().getId()));
                atualizarHidrometroActionForm.setIdHidrometroClasseMetrologica(formatarResultado("" + hidrometro.getHidrometroClasseMetrologica().getId()));
                atualizarHidrometroActionForm.setIdHidrometroDiametro(formatarResultado("" + hidrometro.getHidrometroDiametro().getId()));
                atualizarHidrometroActionForm.setIdHidrometroMarca(formatarResultado("" + hidrometro.getHidrometroMarca().getId()));
                
                if (hidrometro.getHidrometroTipo() != null
                		&& hidrometro.getHidrometroTipo().getId() != null) {
	                atualizarHidrometroActionForm.setIdHidrometroTipo(formatarResultado("" + hidrometro.getHidrometroTipo().getId()));
                }
                
                atualizarHidrometroActionForm.setIndicadorOperacional(formatarResultado("" + hidrometro.getIndicadorOperacional()));
                atualizarHidrometroActionForm.setIdNumeroDigitosLeitura(formatarResultado("" + hidrometro.getNumeroDigitosLeitura()));
                
                if (hidrometro.getHidrometroRelojoaria() != null && 
                		!hidrometro.getHidrometroRelojoaria().equals("")) {
                  atualizarHidrometroActionForm.setIdHidrometroRelojoaria(formatarResultado("" + hidrometro.getHidrometroRelojoaria().getId()));
                } else {
                	atualizarHidrometroActionForm.setIdHidrometroRelojoaria("");
                }
                
                atualizarHidrometroActionForm.setVazaoTransicao(""+ Util.formatarMoedaReal(hidrometro.getVazaoTransicao()));
                atualizarHidrometroActionForm.setVazaoNominal(""+ Util.formatarMoedaReal(hidrometro.getVazaoNominal()));
                atualizarHidrometroActionForm.setVazaoMinima(""+  Util.formatarMoedaReal(hidrometro.getVazaoMinima()));
                atualizarHidrometroActionForm.setNotaFiscal(formatarResultado(""+ hidrometro.getNotaFiscal() ) );
                atualizarHidrometroActionForm.setTempoGarantiaAnos(formatarResultado("" +  hidrometro.getTempoGarantiaAnos()));
                atualizarHidrometroActionForm.setIndicadorMacromedidor(hidrometro.getIndicadorMacromedidor().toString());
                atualizarHidrometroActionForm.setTombamento(hidrometro.getTombamento());
                
                if (hidrometro.getHidrometroClassePressao() != null && 
                		!hidrometro.getHidrometroClassePressao().equals("")) {
                  atualizarHidrometroActionForm.setIdHidrometroClassePressao(formatarResultado("" + hidrometro.getHidrometroClassePressao().getId()));
                } else {
                	atualizarHidrometroActionForm.setIdHidrometroClassePressao("");
                }
                
                if (hidrometro.getHidrometroFatorCorrecao() != null && 
                		!hidrometro.getHidrometroFatorCorrecao().equals("")) {
                  atualizarHidrometroActionForm.setIdHidrometroFatorCorrecao(formatarResultado("" + hidrometro.getHidrometroFatorCorrecao().getId()));
                } else {
                	atualizarHidrometroActionForm.setIdHidrometroFatorCorrecao("");
                }
                
                // Im�vel em que o hidr�metro est� instalado
                String idImovel = fachada.pesquisarImovelHidrometroInstalado(hidrometro.getId());
                atualizarHidrometroActionForm.setIdImovel(idImovel);
            } else {
            	throw new ActionServletException("atencao.atualizacao.timestamp");
            }

            //Filtro de hidr�metro classe metrol�gica para obter todas as
            // classes metrol�gicas ativas
            FiltroHidrometroClasseMetrologica filtroHidrometroClasseMetrologica = new FiltroHidrometroClasseMetrologica();

            filtroHidrometroClasseMetrologica.adicionarParametro(new ParametroSimples(FiltroHidrometroClasseMetrologica.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroClasseMetrologica.setCampoOrderBy(FiltroHidrometroClasseMetrologica.DESCRICAO);

            //Pesquisa a cole��o de classe metrol�gica
            Collection colecaoHidrometroClasseMetrologica = fachada.pesquisar(filtroHidrometroClasseMetrologica, HidrometroClasseMetrologica.class.getName());

            //Filtro de hidr�metro marca para obter todas as marcas de
            // hidr�metros ativas
            FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

            filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
            if (atualizarHidrometroActionForm.getIndicadorMacromedidor() != null
            		&& atualizarHidrometroActionForm.getIndicadorMacromedidor().trim().toString().equals(Hidrometro.INDICADOR_MACROMEDIDOR)) {
    			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_MACRO, ConstantesSistema.INDICADOR_USO_ATIVO));
            } else {
    			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_MICRO, ConstantesSistema.INDICADOR_USO_ATIVO));
            }
            filtroHidrometroMarca.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

            //Pesquisa a cole��o de hidr�metro marca
            Collection colecaoHidrometroMarca = fachada.pesquisar(filtroHidrometroMarca, HidrometroMarca.class.getName());

            //Filtro de hidr�metro di�metro para obter todos os di�metros de hidr�metros ativos
            FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

            filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(FiltroHidrometroDiametro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroDiametro.setCampoOrderBy(FiltroHidrometroDiametro.NUMERO_ORDEM);

            //Pesquisa a cole��o de hidr�metro capacidade
            Collection colecaoHidrometroDiametro = fachada.pesquisar(filtroHidrometroDiametro, HidrometroDiametro.class.getName());

            //Filtro de hidr�metro capacidade para obter todos as capacidade de hidr�metros ativas
            FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

            filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.NUMERO_ORDEM);

            //Pesquisa a cole��o de hidr�metro capacidade
            colecaoHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());

            //Filtro de hidr�metro tipo para obter todos os tipos de hidr�metros ativos
            FiltroHidrometroTipo filtroHidrometroTipo = new FiltroHidrometroTipo();

            filtroHidrometroTipo.adicionarParametro(new ParametroSimples(FiltroHidrometroTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroTipo.setCampoOrderBy(FiltroHidrometroTipo.DESCRICAO);

            //Pesquisa a cole��o de hidr�metro tipo
            Collection colecaoHidrometroTipo = fachada.pesquisar(filtroHidrometroTipo, HidrometroTipo.class.getName());
            
            // Filtro de hidr�metro relojoaria para obter todos os hidr�metros relojoaria ativos
            FiltroHidrometroRelojoaria filtroHidrometroRelojoaria = new FiltroHidrometroRelojoaria();

            filtroHidrometroRelojoaria.adicionarParametro(new ParametroSimples(FiltroHidrometroRelojoaria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroRelojoaria.setCampoOrderBy(FiltroHidrometroRelojoaria.DESCRICAO);

            //Pesquisa a cole��o de hidr�metro tipo
            Collection colecaoHidrometroRelojoaria = fachada.pesquisar(filtroHidrometroRelojoaria, HidrometroRelojoaria.class.getName());

			// Filtro de hidr�metro relojoaria para obter todos os fatores de corre��o de hidr�metro ativos
			FiltroHidrometroFatorCorrecao filtroHidrometroFatorCorrecao = new FiltroHidrometroFatorCorrecao();

			filtroHidrometroFatorCorrecao.adicionarParametro(new ParametroSimples(FiltroHidrometroRelojoaria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroFatorCorrecao.setCampoOrderBy(FiltroHidrometroFatorCorrecao.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro tipo
			Collection<HidrometroFatorCorrecao> colecaoHidrometroFatorCorrecao = fachada.pesquisar(filtroHidrometroFatorCorrecao, HidrometroFatorCorrecao.class.getName());

			// Filtro de hidr�metro relojoaria para obter todos os fatores de 
			// corre��o de hidr�metro ativos
			FiltroHidrometroClassePressao filtroHidrometroClassePressao = new FiltroHidrometroClassePressao();

			filtroHidrometroClassePressao.adicionarParametro(new ParametroSimples(FiltroHidrometroRelojoaria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroClassePressao.setCampoOrderBy(FiltroHidrometroClassePressao.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro tipo
			Collection<HidrometroClassePressao> colecaoHidrometroClassePressao = fachada.pesquisar(filtroHidrometroClassePressao, HidrometroClassePressao.class.getName());
			
            //Envia as cole��es na sess�o
            sessao.setAttribute("colecaoHidrometroClasseMetrologica", colecaoHidrometroClasseMetrologica);
            sessao.setAttribute("colecaoHidrometroMarca", colecaoHidrometroMarca);
            sessao.setAttribute("colecaoHidrometroDiametro", colecaoHidrometroDiametro);
            sessao.setAttribute("colecaoHidrometroCapacidade", colecaoHidrometroCapacidade);
            sessao.setAttribute("colecaoHidrometroTipo", colecaoHidrometroTipo);
            sessao.setAttribute("colecaoHidrometroRelojoaria", colecaoHidrometroRelojoaria);
			sessao.setAttribute("colecaoHidrometroFatorCorrecao", colecaoHidrometroFatorCorrecao);
			sessao.setAttribute("colecaoHidrometroClassePressao", colecaoHidrometroClassePressao);
        } else {

    		// Caso a op��o selecionada seja Macromedidor:
    		if (atualizarHidrometroActionForm.getIndicadorMacromedidor() != null 
    				&& atualizarHidrometroActionForm.getIndicadorMacromedidor().trim().equals(Hidrometro.INDICADOR_MACROMEDIDOR)) {

    			// Filtro de hidr�metro marca para obter todas as marcas de
    			// hidr�metros de macromedidor ativas
    			FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

    			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
    			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_MACRO, ConstantesSistema.INDICADOR_USO_ATIVO));
    			filtroHidrometroMarca.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

    			// Pesquisa a cole��o de hidr�metro marca
    			Collection<HidrometroMarca> colecaoHidrometroMarca = fachada.pesquisar(filtroHidrometroMarca, HidrometroMarca.class.getName());
    			
    			sessao.setAttribute("colecaoHidrometroMarca", colecaoHidrometroMarca);
    			
    		} else if (atualizarHidrometroActionForm.getIndicadorMacromedidor() != null 
    				&& atualizarHidrometroActionForm.getIndicadorMacromedidor().trim().equals(Hidrometro.INDICADOR_MICROMEDIDOR)){
    			// Caso contr�rio, ou seja, o usu�rio selecione Micromedidor
    			
    			// Filtro de hidr�metro marca para obter todas as marcas de
    			// hidr�metros de micromedidor ativas
    			FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

    			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
    			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_MICRO, ConstantesSistema.INDICADOR_USO_ATIVO));
    			filtroHidrometroMarca.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

    			// Pesquisa a cole��o de hidr�metro marca
    			Collection<HidrometroMarca> colecaoHidrometroMarca = fachada.pesquisar(filtroHidrometroMarca, HidrometroMarca.class.getName());
    			
    			sessao.setAttribute("colecaoHidrometroMarca", colecaoHidrometroMarca);
    		}
        }

        //Filtro de hidr�metro capacidade para obter capacidade de hidr�metro de acordo com o id
        FiltroHidrometroCapacidade filtroHidrometroCapacidadeNumeroDigitos = new FiltroHidrometroCapacidade();

        //Verifica se a cole��o de hidrometro capacidade � diferente de null
        if (colecaoHidrometroCapacidade != null
                && !colecaoHidrometroCapacidade.isEmpty()) {

            //Obt�m o primeiro objeto da collection
        	HidrometroCapacidade hidrometroCapacidade = null;
        	if(hidrometro != null && hidrometro.getHidrometroCapacidade() != null){
        		hidrometroCapacidade = (HidrometroCapacidade) hidrometro.getHidrometroCapacidade();
        	}else{
        		Iterator colecaoHidrometroCapacidadeIterator = colecaoHidrometroCapacidade.iterator();
        		hidrometroCapacidade = (HidrometroCapacidade)colecaoHidrometroCapacidadeIterator.next();
        	}

            //Filtra pelo primeiro objeto da collection
            filtroHidrometroCapacidadeNumeroDigitos.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID, hidrometroCapacidade.getId()));
        } else {
            //Filtra pelo id selecionado no combobox
            filtroHidrometroCapacidadeNumeroDigitos.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID, new Integer(atualizarHidrometroActionForm.getIdHidrometroCapacidade())));
        }

        //Pesquisa o n�mero de d�gitos de acordo com o filtro
        Collection colecaoHidrometroCapacidadeNumeroDigitos = fachada.pesquisar(filtroHidrometroCapacidadeNumeroDigitos, HidrometroCapacidade.class.getName());

        //Retorna o objeto pesquisado
        HidrometroCapacidade hidrometroCapacidadeNumeroDigitos = (HidrometroCapacidade) Util.retonarObjetoDeColecao(colecaoHidrometroCapacidadeNumeroDigitos);

        if (hidrometroCapacidadeNumeroDigitos != null && !hidrometroCapacidadeNumeroDigitos.equals("")) {
        	//Obt�m as leituras
        	Integer leituraMinimo = new Integer(hidrometroCapacidadeNumeroDigitos.getLeituraMinimo().toString());
        	Integer leituraMaximo = new Integer(hidrometroCapacidadeNumeroDigitos.getLeituraMaximo().toString());
        	//Obt�m a quantidade da diferen�a
        	int quantidade = (leituraMaximo.intValue() - leituraMinimo.intValue()) + 1;
        	Collection colecaoIntervalo = new ArrayList();

            //Adiciona a quantidade da diferen�a na cole��o
            for (int i = 0; i < quantidade; i++) {
            	Hidrometro hidrometroDigitos = new Hidrometro();
            	Integer numero = leituraMinimo.intValue() + i;
            	hidrometroDigitos.setNumeroDigitosLeitura(new Short(numero+ ""));
                colecaoIntervalo.add(hidrometroDigitos);
            }
            // Envia pela sess�o
            sessao.setAttribute("colecaoIntervalo", colecaoIntervalo);
        }
        
        // caso ainda n�o tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", "manter");
		}
		
		if (hidrometro.getIndicadorMacromedidor().toString().equals(Hidrometro.INDICADOR_MICROMEDIDOR)
        		&& hidrometro.getIndicadorFinalidade().toString().equals(Hidrometro.INDICADOR_MICROMEDIDOR)) {
        	atualizarHidrometroActionForm.setIndicadorMacromedidor(Hidrometro.INDICADOR_REDE_ESGOTO);
        }
		
		sessao.setAttribute("indicadorMacromedidor", atualizarHidrometroActionForm.getIndicadorMacromedidor());
		atualizarHidrometroActionForm.setIdHidrometro(idHidrometro);
		return retorno;
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param parametro
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    private String formatarResultado(String parametro) {
        if (parametro != null && !parametro.trim().equals("")) {
            if (parametro.equals("null")) {
                return "";
            } else {
                return parametro.trim();
            }
        } else {
            return "";
        }
    }

}
