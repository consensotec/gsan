package gsan.atualizacaocadastral;

import gsan.atualizacaocadastral.bean.MapaQuadraHelper;
import gsan.util.ErroRepositorioException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno S� Barreto
 * @date 24/02/2015
 */
public interface IRepositorioAtualizacaoCadastral {

	/**
	 * [UC1392] - Consultar Roteiro Dispositivo M�vel Atualiza��o Cadastral
	 * [IT0008] - Baixar Arquivo Texto e Mapas
	 * Retorna uma cole��o de tuplas com nome e arquivo map da quadra
	 *
	 * @author Bruno S� Barreto
	 * @date 27/02/2015
	 *
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @return ArrayList<MapaQuadraHelper> - tuplas com nome e arquivo map da quadra
	 */
	public ArrayList<MapaQuadraHelper> pesquisarMapasQuadrasPorLocalidadeSetor(
			Integer idLocalidade, Integer codigoSetorComercial) throws ErroRepositorioException;
	
	public List<String> verificarQuadrasSemMapaPorImoveis(String[] idsRegistros) throws Exception;
	
}
