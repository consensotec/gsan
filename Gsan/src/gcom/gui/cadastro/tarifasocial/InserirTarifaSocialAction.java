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
package gcom.gui.cadastro.tarifasocial;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirTarifaSocialAction extends GcomAction {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Instancia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //Pega uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        RegistroAtendimento registroAtendimento = (RegistroAtendimento) sessao.getAttribute("ra");
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
        atendimentoMotivoEncerramento.setId(15);
        
        registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
        registroAtendimento.setDataEncerramento(new Date());
        
        RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
        registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
        registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
        registroAtendimentoUnidade.setUsuario(usuarioLogado);
        AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
        atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
        registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
        registroAtendimentoUnidade.setUltimaAlteracao(new Date());

        //Para apenas uma economia
        
        Collection colecaoTarifaSocialDadoEconomia = null;
        
        if (sessao
                .getAttribute("colecaoDadosTarifaSocial") != null) {
        	colecaoTarifaSocialDadoEconomia = (Collection) sessao
                .getAttribute("colecaoDadosTarifaSocial");
        } else {
        	colecaoTarifaSocialDadoEconomia = (Collection) sessao
            .getAttribute("colecaoTarifaSocialDadoEconomia");
        }
        
        ClienteImovel clienteImovel = (ClienteImovel) sessao
                .getAttribute("clienteImovel");

        //Para mais de uma economia
        Collection colecaoClienteImovelEconomia = (Collection) sessao
                .getAttribute("colecaoClienteImovelEconomia");

        //Im�vel que est� sendo trabalhado
        Imovel imovelSessao = (Imovel) sessao.getAttribute("imovelTarifa");
        
        Integer idTarifaSocialDadoEconomiaExcluida = null; 
        	
        if (sessao.getAttribute("idTarifaSocialDadoEconomia") != null) {
        	idTarifaSocialDadoEconomiaExcluida = new Integer((String) sessao.getAttribute("idTarifaSocialDadoEconomia"));
        }
        
        Collection colecaoTarifaSocialRecadastrar = (Collection) sessao
			.getAttribute("colecaoTarifaSocialDadoEconomia");
        
        Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizado");
        
        Collection colecaoImovelEconomiaAtualizar = (Collection) sessao.getAttribute("colecaoImovelEconomiaAtualizados");
        
        fachada.inserirTarifaSocial(imovelSessao, clienteImovel,
				registroAtendimento, registroAtendimentoUnidade, usuarioLogado,
				idTarifaSocialDadoEconomiaExcluida,
				colecaoTarifaSocialDadoEconomia, colecaoClienteImovelEconomia,
				colecaoTarifaSocialRecadastrar, imovelAtualizar,
				colecaoImovelEconomiaAtualizar);
        
        montarPaginaSucesso(httpServletRequest, "Im�vel de matr�cula " + imovelSessao.getId()
                + " inclu�do na tarifa social com sucesso.", "Inserir outra tarifa social",
                "exibirInserirTarifaSocialAction.do?menu=sim");
        
        
        
        return retorno;
    }
}