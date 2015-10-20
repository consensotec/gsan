package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelPerfilCapacidadeHidrometro;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfilCapacidadeHidrometro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Wallace Thierre
 * @date 24/09/2010 
 *
 */
public class InserirImovelPerfilAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
				
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		InserirImovelPerfilActionForm inserirImovelPerfilActionForm = (InserirImovelPerfilActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		Usuario usuarioLogado = null;
		ImovelPerfil imovelPerfil = null;
		ImovelPerfilCapacidadeHidrometro imovelPerfilCapacidadeHidrometro = null;
		HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
		PermissaoEspecial permissaoEspecial;
		
		String[] hidrometroCapacidades = null;
		
		if(inserirImovelPerfilActionForm.getHidrometroCapacidadesHidden()!=null && inserirImovelPerfilActionForm.getHidrometroCapacidadesHidden().length()!=0)
		{
			hidrometroCapacidades = inserirImovelPerfilActionForm.getHidrometroCapacidadesHidden().split(",");
		}
		
		inserirImovelPerfilActionForm.setHidrometroCapacidades(hidrometroCapacidades);
		
		if(validarObrigatoriedadeCampos(inserirImovelPerfilActionForm) 
				&& validarMesmaDescricao(inserirImovelPerfilActionForm.getDescricao())
				&& validarSelecaoIndicadores(inserirImovelPerfilActionForm)
				&& verificarCapacidadeSelecionada(inserirImovelPerfilActionForm))
		{
			usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			imovelPerfil = new ImovelPerfil();
			imovelPerfil.setDescricao(inserirImovelPerfilActionForm.getDescricao());
			imovelPerfil.setIndicadorUso(inserirImovelPerfilActionForm.getIndicadorUso());
			imovelPerfil.setIndicadorGeracaoAutomatica(inserirImovelPerfilActionForm.getIndicadorGeracaoAutomatica());
			imovelPerfil.setIndicadorInserirManterPerfil(inserirImovelPerfilActionForm.getIndicadorInserirManterPerfil());
			imovelPerfil.setIndicadorGerarDadosLeitura(inserirImovelPerfilActionForm.getIndicadorGerarDadosLeitura());
			imovelPerfil.setIndicadorBloquearRetificacao(inserirImovelPerfilActionForm.getIndicadorBloquearRetificacao());
			imovelPerfil.setIndicadorGrandeConsumidor(inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor());
			imovelPerfil.setIndicadorBloqueaDadosSocial(inserirImovelPerfilActionForm.getIndicadorBloqueaDadosSocial());
			imovelPerfil.setIndicadorGeraDebitoSegundaViaConta(inserirImovelPerfilActionForm.getIndicadorGeraDebitoSegundaViaConta());
			imovelPerfil.setIndicadorAcrescimoImpontualidade(inserirImovelPerfilActionForm.getIndicadorAcrescimosImpontualidade());
			imovelPerfil.setIndicadorNegativacaoDoCliente(inserirImovelPerfilActionForm.getIndicadorNegativacaoDoCliente());
			imovelPerfil.setIndicadorCorporativo(inserirImovelPerfilActionForm.getIndicadorCorporativo());
			imovelPerfil.setIndicadorPerfilTelemedido(inserirImovelPerfilActionForm.getIndicadorPerfilTelemedido());
			
			if(Util.isInteger(inserirImovelPerfilActionForm.getConsumoMinimo()) && 
					(imovelPerfil.getIndicadorCorporativo().equals(ConstantesSistema.SIM) 
							|| imovelPerfil.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM)))
			{
				imovelPerfil.setConsumoMinimo(Integer.valueOf(inserirImovelPerfilActionForm.getConsumoMinimo()));
			}
			else
			{
				imovelPerfil.setConsumoMinimo(null);
			}
			
			if(inserirImovelPerfilActionForm.getIdPermissaoEspecial()!=null && !inserirImovelPerfilActionForm.getIdPermissaoEspecial().trim().equals(""))
			{
				permissaoEspecial = new PermissaoEspecial();
				permissaoEspecial.setId(Integer.valueOf(inserirImovelPerfilActionForm.getIdPermissaoEspecial()));
				imovelPerfil.setPermissaoEspecial(permissaoEspecial);
			}
			else
			{
				imovelPerfil.setPermissaoEspecial(null);
			}
			
			imovelPerfil.setUltimaAlteracao(new Date());
			
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_IMOVEL_PERFIL_INSERIR, imovelPerfil.getId(),
					imovelPerfil.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(imovelPerfil);
			// ------------ REGISTRAR TRANSAÇÃO ----------------
					
			Integer idImovelPerfil = (Integer) fachada.inserir(imovelPerfil);
			
			if(inserirImovelPerfilActionForm.getIndicadorCorporativo().equals(ConstantesSistema.SIM)
					|| inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM))
			{
				if(inserirImovelPerfilActionForm.getHidrometroCapacidades()!=null)
				{
					for(String idHidrometroCapacidade : inserirImovelPerfilActionForm.getHidrometroCapacidades())
					{
						imovelPerfilCapacidadeHidrometro = new ImovelPerfilCapacidadeHidrometro();
						imovelPerfilCapacidadeHidrometro.setImovelPerfil(imovelPerfil);
						
						hidrometroCapacidade = new HidrometroCapacidade();
						hidrometroCapacidade.setId(Integer.valueOf(idHidrometroCapacidade));
						imovelPerfilCapacidadeHidrometro.setHidrometroCapacidade(hidrometroCapacidade);
						
						imovelPerfilCapacidadeHidrometro.setIndicadorUso(ConstantesSistema.SIM);
						imovelPerfilCapacidadeHidrometro.setUltimaAlteracao(new Date());
						
						getFachada().inserir(imovelPerfilCapacidadeHidrometro);
					}
				}
			}
			
			montarPaginaSucesso(httpServletRequest,"Imóvel perfil  " + imovelPerfil.getDescricao()
							+ " inserido com sucesso.",
					"Inserir outro Imovel Perfil",
					"exibirInserirImovelPerfilAction.do?menu=sim",
					"exibirAtualizarImovelPerfilAction.do?idRegistroAtualizacao="
							+ idImovelPerfil,
					"Atualizar Imovel Perfil Inserido");

			sessao.removeAttribute("InserirImovelPerfilActionForm");
		}
		
		return retorno;		
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar se existe uma descrição<br>
	 * igual na base de dados
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param descricao
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarMesmaDescricao(String descricao){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.DESCRICAO, descricao));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil==null || colecaoImovelPerfil.size()!=0)
		{
			throw new ActionServletException("atencao.descricao_existente", null,
					descricao);
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * [FS0004]-Verificar seleção de indicadores
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param inserirImovelPerfilActionForm
	 * @return boolean
	 */
	private boolean validarSelecaoIndicadores(InserirImovelPerfilActionForm inserirImovelPerfilActionForm){
		
		boolean validado = false;
		
		if(validarIndicadorClienteCorporativoGrandeConsumidor(
				inserirImovelPerfilActionForm.getIndicadorCorporativo(), 
				inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor()))
		{
			if(inserirImovelPerfilActionForm.getIndicadorCorporativo().equals(ConstantesSistema.SIM))
			{
				validarClienteCorporativoIndicadorTelemedido(inserirImovelPerfilActionForm.getIndicadorPerfilTelemedido());
				
				if(Util.isInteger(inserirImovelPerfilActionForm.getConsumoMinimo()))
				{
					validarPerfilCorporativoConsumoMinimo(Integer.valueOf(inserirImovelPerfilActionForm.getConsumoMinimo()));
				}
			}
			
			if(inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM))
			{
				validarGrandeConsumidorIndicadorTelemedido(inserirImovelPerfilActionForm.getIndicadorPerfilTelemedido());
				
				if(Util.isInteger(inserirImovelPerfilActionForm.getConsumoMinimo()))
				{
					validarPerfilGrandeConsumidorConsumoMinimo(Integer.valueOf(inserirImovelPerfilActionForm.getConsumoMinimo()));
				}
			}
			
			validado = true;
		}
		
		return validado;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar os indicador<br>
	 * corporativo e grande consumidor
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param indicadorClienteCorporativo
	 * @param indicadorGrandeConsumidor
	 * @return
	 */
	private boolean validarIndicadorClienteCorporativoGrandeConsumidor(Short indicadorClienteCorporativo,Short indicadorGrandeConsumidor){
		
		if(indicadorClienteCorporativo.equals(ConstantesSistema.SIM) 
				&& indicadorGrandeConsumidor.equals(ConstantesSistema.SIM))
		{
			throw new ActionServletException("atencao.indicador.cliente.corporativo.igual.indicador.grande.consumidor");
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar relação dos indicadores<br> 
	 * cliente corporativo e telemedido
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param indicadorTelemedido
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarClienteCorporativoIndicadorTelemedido(Short indicadorTelemedido){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_CORPORATIVO, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_PERFIL_TELEMEDIDO,indicadorTelemedido));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil!=null && colecaoImovelPerfil.size()!=0)
		{
			throw new ActionServletException("atencao.perfil.corporativo.mesmo.indicador.telemedido");
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar relação dos indicadores<br> 
	 * grande cliente e telemedido
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param indicadorTelemedido
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarGrandeConsumidorIndicadorTelemedido(Short indicadorTelemedido){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_GRANDE_CONSUMIDOR, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_PERFIL_TELEMEDIDO,indicadorTelemedido));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil!=null && colecaoImovelPerfil.size()!=0)
		{
			throw new ActionServletException("atencao.perfil.grande.cliente.mesmo.indicador.telemedido");
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * [FS0005]-Verificar Consumo Mínimo
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param consumoMinimo
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarPerfilCorporativoConsumoMinimo(Integer consumoMinimo){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_GRANDE_CONSUMIDOR, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().
				pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil!=null && colecaoImovelPerfil.size()!=0)
		{
			for(ImovelPerfil imovelPerfil : colecaoImovelPerfil)
			{
				if(imovelPerfil!=null && (imovelPerfil.getConsumoMinimo()!=null && imovelPerfil.getConsumoMinimo().intValue()>=consumoMinimo.intValue()))
				{
					throw new ActionServletException("atencao.consumo.minimo.corporativo.maior.grande.consumidor");
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * [FS0005]-Verificar Consumo Mínimo
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param consumoMinimo
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarPerfilGrandeConsumidorConsumoMinimo(Integer consumoMinimo){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_CORPORATIVO, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().
				pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil!=null && colecaoImovelPerfil.size()!=0)
		{
			for(ImovelPerfil imovelPerfil : colecaoImovelPerfil)
			{
				if(imovelPerfil!=null && (imovelPerfil.getConsumoMinimo()!=null && imovelPerfil.getConsumoMinimo().intValue()<=consumoMinimo.intValue()))
				{
					throw new ActionServletException("atencao.consumo.minimo.corporativo.maior.grande.consumidor");
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * [FS0006]-Verificar Capacidade Selecionada
	 * 
	 * @author Jonathan Marcos
	 * @since 04/06/2015
	 * @param inserirImovelPerfilActionForm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean verificarCapacidadeSelecionada(InserirImovelPerfilActionForm inserirImovelPerfilActionForm){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		FiltroImovelPerfilCapacidadeHidrometro filtroImovelPerfilCapacidadeHidrometro = null;
		Collection<ImovelPerfil> colecaoImovelPefil = null;
		Collection<ImovelPerfilCapacidadeHidrometro> colecaoImovelPerfilCapacidadeHidrometro = null;
		ImovelPerfilCapacidadeHidrometro imovelPerfilCapacidadeHidrometro = null;
		
		
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		if(inserirImovelPerfilActionForm.getHidrometroCapacidades()!=null)
		{
			if(inserirImovelPerfilActionForm.getIndicadorCorporativo().equals(ConstantesSistema.SIM))
			{
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_GRANDE_CONSUMIDOR, ConstantesSistema.SIM));
			}
			else if(inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM))
			{
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_CORPORATIVO, ConstantesSistema.SIM));
			}
			
			if(inserirImovelPerfilActionForm.getIndicadorCorporativo().equals(ConstantesSistema.SIM)
					|| inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM))
			{
				colecaoImovelPefil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
				
				if(colecaoImovelPefil!=null && colecaoImovelPefil.size()!=0)
				{
					for(ImovelPerfil perfil : colecaoImovelPefil)
					{
						for(String idHidrometroCapacidade : inserirImovelPerfilActionForm.getHidrometroCapacidades())
						{
							filtroImovelPerfilCapacidadeHidrometro = new FiltroImovelPerfilCapacidadeHidrometro();
							filtroImovelPerfilCapacidadeHidrometro.adicionarParametro(new ParametroSimples(
									FiltroImovelPerfilCapacidadeHidrometro.IMOVEL_PERFIL_ID, perfil.getId()));
							filtroImovelPerfilCapacidadeHidrometro.adicionarParametro(new ParametroSimples(
									FiltroImovelPerfilCapacidadeHidrometro.HIDROMETRO_CAPACIDADE_ID, Integer.valueOf(idHidrometroCapacidade)));
							filtroImovelPerfilCapacidadeHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
							colecaoImovelPerfilCapacidadeHidrometro = getFachada().pesquisar(filtroImovelPerfilCapacidadeHidrometro, ImovelPerfilCapacidadeHidrometro.class.getName());
							
							if(colecaoImovelPerfilCapacidadeHidrometro!=null && colecaoImovelPerfilCapacidadeHidrometro.size()!=0)
							{
								imovelPerfilCapacidadeHidrometro = (ImovelPerfilCapacidadeHidrometro) 
										Util.retonarObjetoDeColecao(colecaoImovelPerfilCapacidadeHidrometro);
								
								inserirImovelPerfilActionForm.setHidrometroCapacidades(null);
								
								throw new ActionServletException("atencao.capacidade.associada.corpocativo.grande.cliente", null,
										imovelPerfilCapacidadeHidrometro.getHidrometroCapacidade().getDescricao());
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar os campos obrigatórios
	 * 
	 * @author Jonathan Marcos
	 * @since 03/01/2015
	 * @param inserirImovelPerfilActionForm
	 * @return boolean
	 */
	private boolean validarObrigatoriedadeCampos(InserirImovelPerfilActionForm inserirImovelPerfilActionForm){
		
		if(inserirImovelPerfilActionForm.getDescricao().trim().equals(""))
		{
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorUso()==null)
		{
			throw new ActionServletException("atencao.required", null, "indicador de uso");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorGeracaoAutomatica()==null)
		{
			throw new ActionServletException("atencao.required", null, "indicador de geração automático");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorInserirManterPerfil()==null)
		{
			throw new ActionServletException("atencao.required", null, "inserir manter perfil");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorGerarDadosLeitura()==null)
		{
			throw new ActionServletException("atencao.required", null, "inserir gerar dados leitura");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorBloquearRetificacao()==null)
		{
			throw new ActionServletException("atencao.required", null, "bloquear reatificação");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor()==null)
		{
			throw new ActionServletException("atencao.required", null, "grande consumidor");
		}
		
		if(inserirImovelPerfilActionForm.getIndicadorBloqueaDadosSocial()==null){
			throw new ActionServletException("atencao.required", null, "grande consumidor");
		}
		
		if(inserirImovelPerfilActionForm.getIndicadorGeraDebitoSegundaViaConta()==null)
		{
			throw new ActionServletException("atencao.required", null, "gerar debitos 2° via conta");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorAcrescimosImpontualidade()==null)
		{
			throw new ActionServletException("atencao.required", null, "gerar juros/multa");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorNegativacaoDoCliente()==null)
		{
			throw new ActionServletException("atencao.required", null, "Negativação do Cliente");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorCorporativo()==null)
		{
			throw new ActionServletException("atencao.required", null, "Indicador Corporativo");
		}
				
		if(inserirImovelPerfilActionForm.getIndicadorPerfilTelemedido()==null)
		{
			throw new ActionServletException("atencao.required", null, "Indicador Perfil Telemedido");
		}
		
		return true;
	}
}
