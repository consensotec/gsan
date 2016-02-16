package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.InstalacaoSubstituicaoRetiradaLoteHelper;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
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
 * [UC1695] - Instalar/Substituir/Retirar Hidrômetro em Lote
 * @author Rodrigo Cabral
 * @since 18/11/2015
 */
public class EfetuarInstalacaoSubstituicaoRetiradaLoteAction extends GcomAction{
	
	
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Collection<InstalacaoSubstituicaoRetiradaLoteHelper> colecaoOsHidrometro = 
				(Collection<InstalacaoSubstituicaoRetiradaLoteHelper>)sessao.getAttribute("colecaoOsHidrometro");
		
		if (colecaoOsHidrometro == null || colecaoOsHidrometro.isEmpty()){
			
			throw new ActionServletException("atencao.os_adicionadas");
		}
		
		
		Iterator<InstalacaoSubstituicaoRetiradaLoteHelper> iColecaoOsHidrometro = colecaoOsHidrometro.iterator();
		
		while (iColecaoOsHidrometro.hasNext()){
			
			InstalacaoSubstituicaoRetiradaLoteHelper helper = (InstalacaoSubstituicaoRetiradaLoteHelper)iColecaoOsHidrometro.next();

			OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(helper.getIdOS()));
			ordemServico.setPercentualCobranca(ConstantesSistema.CEM);
			ordemServico.setValorAtual(ordemServico.getValorOriginal());
			
			Imovel imovel = ordemServico.getImovel();
			
			if (helper.getOperacao().equals("Instalação")){
				
					//[SB0001] - Efetuar Instalação do Hidrômetro
					HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
					hidrometroInstalacaoHistorico = dadosHidrometroInstalacao(hidrometroInstalacaoHistorico, helper);
					hidrometroInstalacaoHistorico.setUsuarioInstalacao(usuario);
					
					IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
					
					integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
					integracaoComercialHelper.setOrdemServico(ordemServico);
					integracaoComercialHelper.setQtdParcelas(null);
					integracaoComercialHelper.setUsuarioLogado(usuario);
					integracaoComercialHelper.setImovel(imovel);
					integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
					
					
					// [SB0003 - Atualizar Ordem de Serviço]
					fachada.encerrarOSComExecucaoSemReferencia(
						ordemServico.getId(),
						new Date(),
						usuario,
						AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO.toString(),
						new Date(),
						"ENCERRADO EM LOTE",
						"",
						"",
						null,
						integracaoComercialHelper,
						ordemServico.getServicoTipo().getId().toString(),
						null,
						null,
						null,
						null,
						null,
						null,
						null);
					
						// Encerrar RA
						encerrarRA(ordemServico, usuario);
				

			} else if (helper.getOperacao().equals("Substituição")){
				
									
					HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
					hidrometroInstalacaoHistorico = dadosHidrometroInstalacao(hidrometroInstalacaoHistorico, helper);
					hidrometroInstalacaoHistorico.setUsuarioInstalacao(usuario);
					
					fachada.validacaoInstalacaoHidrometro(helper.getHidrometroInstalado());
					
					LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
					
					IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
					
					integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
					integracaoComercialHelper.setOrdemServico(ordemServico);
					integracaoComercialHelper.setQtdParcelas("1");
					integracaoComercialHelper.setUsuarioLogado(usuario);
					integracaoComercialHelper.setImovel(imovel);
					integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
					
					HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico = new HidrometroInstalacaoHistorico();
					
					if (ligacaoAgua.getHidrometroInstalacaoHistorico() != null){
						hidrometroSubstituicaoHistorico = ligacaoAgua.getHidrometroInstalacaoHistorico();
					} else {
						hidrometroSubstituicaoHistorico = imovel.getHidrometroInstalacaoHistorico();
					}
					
					hidrometroSubstituicaoHistorico.setDataRetirada(Util.converteStringParaDate(helper.getDataInstalacaoRetirada()));
					
					if (helper.getLeituraRetirada() != null && 
						!helper.getLeituraRetirada().equalsIgnoreCase("")){
						hidrometroSubstituicaoHistorico.setNumeroLeituraRetirada(new Integer(helper.getLeituraRetirada()));
					}
					
					hidrometroSubstituicaoHistorico.setUltimaAlteracao(new Date());
					hidrometroSubstituicaoHistorico.setUsuarioRetirada(usuario);
					
					integracaoComercialHelper.setHidrometroSubstituicaoHistorico(hidrometroSubstituicaoHistorico);
					integracaoComercialHelper.setSituacaoHidrometroSubstituido(HidrometroSituacao.DISPONIVEL.toString());
					integracaoComercialHelper.setMatriculaImovel(helper.getMatricula().toString());
					
					
					Fachada.getInstancia().encerrarOSComExecucaoSemReferencia(
						ordemServico.getId(),
						new Date(),
						usuario,
						AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO.toString(),
						new Date(),
						"ENCERRADO EM LOTE",
						"",
						"",
						null,
						integracaoComercialHelper,
						ordemServico.getServicoTipo().getId().toString(),
						null,
						null,
						null,
						null,
						null,
						null,
						null);
					
					// Encerrar RA
					encerrarRA(ordemServico, usuario);
					

			}
			
			
		}
		
		
		
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){
			//Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, " As Ordens de Serviços foram encerradas com sucesso.", "Encerrar novas Ordens de Serviço? ",
					"exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do");
		}
		
		sessao.removeAttribute("colecaoOsHidrometro");
        
		
        return retorno;
    }
    
    private HidrometroInstalacaoHistorico dadosHidrometroInstalacao (HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico, InstalacaoSubstituicaoRetiradaLoteHelper helper){
    	
    	/*
		 * Campos obrigatórios
		 */
		
    	//hidrometro
    	
    	FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
    	filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.NUMERO_HIDROMETRO, helper.getHidrometroInstalado()));
    	Collection<Hidrometro> colHidro = Fachada.getInstancia().pesquisar(filtroHidrometro, Hidrometro.class.getName());
    	
    	Hidrometro hidrometro = (Hidrometro) Util.retonarObjetoDeColecao(colHidro);
    	
    	hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
    	
		//data instalação
		hidrometroInstalacaoHistorico.setDataInstalacao(Util.converteStringParaDate(helper.getDataInstalacaoRetirada()));
		
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		ligacaoAgua.setId(helper.getMatricula());

		hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
		hidrometroInstalacaoHistorico.setImovel(null);
	
		//medição tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);
		
		//hidrômetro local instalação
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(HidrometroLocalInstalacao.CALCADA);		
		hidrometroInstalacaoHistorico.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		//proteção
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(HidrometroProtecao.CAIXA_METALICA);
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
		//leitura instalação
		if(helper.getLeituraInstalacao() !=null && !helper.getLeituraInstalacao().trim().equals("")){
		  hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(Integer.parseInt(helper.getLeituraInstalacao()));
		}else{
		  hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);	
		}
		//cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(ConstantesSistema.NAO);
		
		/*
		 * Campos opcionais 
		 */
		
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());
		//indicador instalação substituição
		hidrometroInstalacaoHistorico.setIndicadorInstalcaoSubstituicao(ConstantesSistema.SIM);		
		//data última alteração
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
		
    	return hidrometroInstalacaoHistorico;
    }
    
    private void encerrarRA (OrdemServico ordemServico, Usuario usuario) {
    	
    	// Encerrar RA
		if (ordemServico != null && ordemServico.getRegistroAtendimento() != null && ordemServico.getRegistroAtendimento().getId() != null) {
		
					FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
					filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO);
					filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO);
					filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, ordemServico.getRegistroAtendimento().getId()));
				
					Collection<RegistroAtendimento> colecaoRegistroAtendimento = Fachada.getInstancia().pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName() );
					
					RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
					
					AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
					atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
					atendimentoMotivoEncerramento.setIndicadorExecucao(AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM);
					registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
					registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
					registroAtendimento.setDataEncerramento(new Date());
					registroAtendimento.setUltimaAlteracao(new Date());
			
					/*
					 * cria o objeto para a inserção do registro de atendimento unidade
					 */
					RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
					registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
			
					if (usuario.getUnidadeOrganizacional() != null
							&& !usuario.getUnidadeOrganizacional().equals("")) {
			
						registroAtendimentoUnidade.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
					}
					registroAtendimentoUnidade.setUsuario(usuario);
			
					// atendimento relação tipo
					AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
					atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
					registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
					registroAtendimentoUnidade.setUltimaAlteracao(new Date());
					
					
					SolicitacaoTipoEspecificacao especificacao = registroAtendimento.getSolicitacaoTipoEspecificacao();
			        
			        Boolean confirmadoGeracaoNovoRA = false;;  
			        
			        ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = ordemServico.getServicoNaoCobrancaMotivo();
			        
			        BigDecimal valorDebito = ordemServico.getValorAtual();
			        
			        if (especificacao.getDebitoTipo() != null
							&& servicoNaoCobrancaMotivo == null) {
						
			        	if (valorDebito == null) {
							valorDebito = especificacao.getValorDebito();
						}
			        	
						Fachada.getInstancia().encerrarRegistroAtendimento(
								registroAtendimento,
								registroAtendimentoUnidade, 
								usuario,
								especificacao.getDebitoTipo().getId(),
								valorDebito, 
								1, 
								"100",
								confirmadoGeracaoNovoRA, 
								null, 
								false);
					} else {
						Fachada.getInstancia().encerrarRegistroAtendimento(
							registroAtendimento,
							registroAtendimentoUnidade, 
							usuario, 
							null, 
							null, 
							null, 
							null, 
							confirmadoGeracaoNovoRA,
							null,
							false);
					}	
				}
    	
    	
    	}
}