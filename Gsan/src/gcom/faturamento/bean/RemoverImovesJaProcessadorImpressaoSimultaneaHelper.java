package gcom.faturamento.bean;

import gcom.cadastro.imovel.Imovel;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;

public class RemoverImovesJaProcessadorImpressaoSimultaneaHelper {
	
	public class DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea{
		private Integer idImovel;
		
		private Short indicadorImovelImpresso;
		
		private boolean medidoAgua;
		private boolean medidoPoco;
		
		private Short medicaoTipo;
		
		private Integer leituraAgua;
		private Integer anormalidadeAgua;
		private Integer leituraAnteriorFaturamentoAgua;
		
		private Integer leituraPoco;
		private Integer anormalidadePoco;
		private Integer leituraAnteriorFaturamentoPoco;
		
		private Short indicadorEmissaoConta;
		
		private String[] linhas;
		
		public String[] getLinhas() {
			return linhas;
		}
		
		public DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea() {}
		
		private DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea carregarImovel(String linha, short indicadorAndroid){
			
			DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dadosImovel = new DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea();
			
			
			if(indicadorAndroid == ConstantesSistema.NAO){
				
				//Matricula do Imovel
				Integer idImovel = Integer.parseInt( linha.substring( 1, 10 ) );
				dadosImovel.setIdImovel(idImovel);
				
				//Tipo de medição
				Short medicaoTipo = Short.parseShort( linha.substring( 10, 11 ) );
				dadosImovel.setMedicaoTipo(medicaoTipo);
				
				String strLeitura = linha.substring( 37, 44 ).trim();
				String strAnormalidade = linha.substring( 44, 45 ).trim();
				String strIndicadorEmissaoConta = linha.substring( 107, 108 ).trim();
				String strLeituraAnteriorFaturamento = linha.substring( 142, 149 ).trim();
				
				if ( medicaoTipo.intValue() == MedicaoTipo.LIGACAO_AGUA ){
					dadosImovel.setMedidoAgua(true);
					
					if ( !strLeitura.equals( "" ) ){
						dadosImovel.setLeituraAgua(Util.converterStringParaInteger( strLeitura ));	
					}
					
					if ( !strAnormalidade.equals( "0" ) ){
						dadosImovel.setAnormalidadeAgua(Util.converterStringParaInteger( strAnormalidade ));
					}    
					
					if ( !strIndicadorEmissaoConta.equals( "" ) ){
						dadosImovel.setIndicadorEmissaoConta(Short.parseShort( strIndicadorEmissaoConta ));
					} else {
						dadosImovel.setIndicadorEmissaoConta(ConstantesSistema.NAO);
					}
					
					if ( !strLeituraAnteriorFaturamento.equals( "" ) ){
						dadosImovel.setLeituraAnteriorFaturamentoAgua(Util.converterStringParaInteger( strLeituraAnteriorFaturamento ));
					}	
				} 
				else if ( medicaoTipo.intValue() == MedicaoTipo.POCO ){
					dadosImovel.setMedidoPoco(true);
					
					if ( !strLeitura.equals( "" ) ){
						dadosImovel.setLeituraPoco(Util.converterStringParaInteger( strLeitura ));	
					}
					
					if ( !strAnormalidade.equals( "0" ) ){
						dadosImovel.setAnormalidadePoco(Util.converterStringParaInteger( strAnormalidade ));
					}    
					
					if ( !strIndicadorEmissaoConta.equals( "" ) ){
						dadosImovel.setIndicadorEmissaoConta(Short.parseShort( strIndicadorEmissaoConta ));
					} else {
						dadosImovel.setIndicadorEmissaoConta(ConstantesSistema.NAO);
					}
					
					if ( !strLeituraAnteriorFaturamento.equals( "" ) ){
						dadosImovel.setLeituraAnteriorFaturamentoPoco(Util.converterStringParaInteger( strLeituraAnteriorFaturamento ));
					}
				}
				
			}else{
				
				ArrayList<String> dados = Util.split(linha);
				
				//Matricula do Imovel
				Integer idImovel = Integer.parseInt(dados.get(1));
				dadosImovel.setIdImovel(idImovel);
				
				//Tipo de medição
				Short medicaoTipo = Short.parseShort(dados.get(2));
				dadosImovel.setMedicaoTipo(medicaoTipo);
				
				String strLeitura = dados.get(7);
				String strAnormalidade = dados.get(8);
				String strIndicadorEmissaoConta = dados.get(20);  
				String strLeituraAnteriorFaturamento = dados.get(24);
				
				if ( medicaoTipo.intValue() == MedicaoTipo.LIGACAO_AGUA ){
					dadosImovel.setMedidoAgua(true);
					
					if ( !strLeitura.equals( "" ) ){
						dadosImovel.setLeituraAgua(Util.converterStringParaInteger( strLeitura ));	
					}
					
					if ( !strAnormalidade.equals( "0" ) ){
						dadosImovel.setAnormalidadeAgua(Util.converterStringParaInteger( strAnormalidade ));
					}    
					
					if ( !strIndicadorEmissaoConta.equals( "" ) ){
						dadosImovel.setIndicadorEmissaoConta(Short.parseShort( strIndicadorEmissaoConta ));
					} else {
						dadosImovel.setIndicadorEmissaoConta(ConstantesSistema.NAO);
					}
					
					if ( !strLeituraAnteriorFaturamento.equals( "" ) ){
						dadosImovel.setLeituraAnteriorFaturamentoAgua(Util.converterStringParaInteger( strLeituraAnteriorFaturamento ));
					}	
				} 
				else if ( medicaoTipo.intValue() == MedicaoTipo.POCO ){
					dadosImovel.setMedidoPoco(true);
					
					if ( !strLeitura.equals( "" ) ){
						dadosImovel.setLeituraPoco(Util.converterStringParaInteger( strLeitura ));	
					}
					
					if ( !strAnormalidade.equals( "0" ) ){
						dadosImovel.setAnormalidadePoco(Util.converterStringParaInteger( strAnormalidade ));
					}    
					
					if ( !strIndicadorEmissaoConta.equals( "" ) ){
						dadosImovel.setIndicadorEmissaoConta(Short.parseShort( strIndicadorEmissaoConta ));
					} else {
						dadosImovel.setIndicadorEmissaoConta(ConstantesSistema.NAO);
					}
					
					if ( !strLeituraAnteriorFaturamento.equals( "" ) ){
						dadosImovel.setLeituraAnteriorFaturamentoPoco(Util.converterStringParaInteger( strLeituraAnteriorFaturamento ));
					}
				}

				
			}
			
			
			return dadosImovel;
		}

		public DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea( Object[] objects, short indicadorAndroid ) throws ControladorException {
			
			String[] linhas = new String[ objects.length ];
			int i = 0;
			
			//Quantidade de linhas do tipo 1 que foram geradas para o imóvel
			Integer qtdTipoLinha1 = 0;
			boolean primeiroOcorrenciaDescartada = false;
			
			for (Object obj : objects) {
				
				String linha = (String) obj;
				linhas[i] = linha;
				++i;
				
				// Tipo da linha
				Integer tipoLinha = null;
				
				ArrayList<String> dados = null;
				if(indicadorAndroid==ConstantesSistema.SIM){
					dados = Util.split(linha);
					tipoLinha = Integer.parseInt( dados.get(0) );
				}else{
					tipoLinha = Integer.parseInt( linha.substring( 0, 1 ) );
				}
				

				if ( tipoLinha.intValue() == 1 ){
					
					//Quantidade de linhas do tipo 1 que foram geradas para o imóvel
					++qtdTipoLinha1;
					
					//So serão permitidas no máximo 2 linhas do tipo 1 para o imóvel
					if ( qtdTipoLinha1 <= 2 ){
					
						DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dadosImovel = carregarImovel(linha, indicadorAndroid);
						
						//IMÓVEL
						this.idImovel = dadosImovel.getIdImovel();
						
						//TIPO DE MEDIÇÃO
						this.medicaoTipo = dadosImovel.getMedicaoTipo();
						
						if ( this.medicaoTipo.intValue() == MedicaoTipo.LIGACAO_AGUA ){
							
							this.medidoAgua = dadosImovel.isMedidoAgua();
							this.leituraAgua = dadosImovel.getLeituraAgua();
							this.anormalidadeAgua = dadosImovel.getAnormalidadeAgua();
							this.indicadorEmissaoConta = dadosImovel.getIndicadorEmissaoConta();
							this.leituraAnteriorFaturamentoAgua = dadosImovel.getLeituraAnteriorFaturamentoAgua();
	        				
						} 
						else if ( this.medicaoTipo.intValue() == MedicaoTipo.POCO ){
							
							this.medidoPoco = dadosImovel.isMedidoPoco();
							this.leituraPoco = dadosImovel.getLeituraPoco();
							this.anormalidadePoco = dadosImovel.getAnormalidadePoco();
							this.indicadorEmissaoConta = dadosImovel.getIndicadorEmissaoConta();
							this.leituraAnteriorFaturamentoPoco = dadosImovel.getLeituraAnteriorFaturamentoPoco();
						}
					}
					else if (!primeiroOcorrenciaDescartada){
						
						/*
						 * Caso o registro de retorno do imóvel venha duplicado, o sistema terá que identificar a partir da leitura
						 * anterior do faturamento qual o registro que será utilizado no processamento. 
						 */
						
						String strAnoMesFaturamento ="";
						if(indicadorAndroid==ConstantesSistema.SIM){
							strAnoMesFaturamento = Util.formatarMesAnoParaAnoMes(dados.get(3));
						}else{
							strAnoMesFaturamento = Util.formatarMesAnoParaAnoMes(linha.substring( 11, 17 ));
						}
						
						
						Integer anoMesReferenciaAnterior = Util.subtrairMesDoAnoMes(Util.converterStringParaInteger( strAnoMesFaturamento ), 1);
						
						Imovel imovel = new Imovel();
						imovel.setId(this.getIdImovel());
						
						/*
						 * [UC0745] - Gerar Arquivo Texto para Faturamento
						 * [SB0006] - Obter dados dos tipos de medição
						 */
						Integer leituraAnteriorFaturamentoAguaBase = null;
						Integer leituraAnteriorFaturamentoPocoBase = null;
						
						Collection colecaoDadosMedicaoHistorico = this.getControladorMicromedicao()
                        .obterDadosTiposMedicao(imovel, anoMesReferenciaAnterior);
						
						/*
			             * Caso a leitura anterior do faturamento para a PRIMEIRA ocorrência seja igual a leitura anterior do faturamento
			             * que está na base, o sistema irá descartar a segunda ocorrência
			             */
						if (colecaoDadosMedicaoHistorico != null && !colecaoDadosMedicaoHistorico.isEmpty()) {

				            Iterator iterator = colecaoDadosMedicaoHistorico.iterator();
				            
				            while (iterator.hasNext()) {

				            	Object[] arrayMedicaoHistorico = (Object[]) iterator.next();
				                
				                //TIPO DE MEDIÇÃO
				            	if (arrayMedicaoHistorico[7] != null && !((Integer) arrayMedicaoHistorico[7]).equals(0)) {

				            		leituraAnteriorFaturamentoAguaBase = (Integer) arrayMedicaoHistorico[3];

				            	} 
				            	else {

				            		leituraAnteriorFaturamentoPocoBase = (Integer) arrayMedicaoHistorico[3];
				            	}
				            }
				            
				            
				            Integer leituraAnteriorFaturamentoAguaDuplicidade = this.getLeituraAnteriorFaturamentoAgua();
				            Integer leituraAnteriorFaturamentoPocoDuplicidade = this.getLeituraAnteriorFaturamentoPoco();
				            
				            //Verificando o registro de água para a primeira ocorrência
				            if (leituraAnteriorFaturamentoAguaDuplicidade != null && leituraAnteriorFaturamentoAguaBase != null &&
				            	leituraAnteriorFaturamentoAguaDuplicidade.intValue() == leituraAnteriorFaturamentoAguaBase.intValue()){
				            	
				            	int qtdLinhasSemDuplicidade = i - 1;
				            	String[] linhasSemDuplicidade = new String[ qtdLinhasSemDuplicidade ];
				            	
				            	for (int j = 0; j < qtdLinhasSemDuplicidade; j++) {
									
				            		linhasSemDuplicidade[j] = linhas[j]; 
								}
				            	
				            	linhas = linhasSemDuplicidade;
				            	
				            	break;
				            }
				            
				            //Verificando o registro de esgoto para a primeira ocorrência
				            if (leituraAnteriorFaturamentoPocoDuplicidade != null && leituraAnteriorFaturamentoPocoBase != null &&
				            	leituraAnteriorFaturamentoPocoDuplicidade.intValue() == leituraAnteriorFaturamentoPocoBase.intValue()){
					            	
					            int qtdLinhasSemDuplicidade = i - 1;
					            String[] linhasSemDuplicidade = new String[ qtdLinhasSemDuplicidade ];
					            	
					            for (int j = 0; j < qtdLinhasSemDuplicidade; j++) {
										
					            	linhasSemDuplicidade[j] = linhas[j]; 
								}
					            	
					            linhas = linhasSemDuplicidade;
					            	
					            break;
					        }
				            
				            /*
				             * Caso contrário, o sistema irá descartar a primeira ocorrência 
				             */
				            DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dadosImovel = carregarImovel(linha, indicadorAndroid);
							
							//IMÓVEL
							this.idImovel = dadosImovel.getIdImovel();
							
							//TIPO DE MEDIÇÃO
							this.medicaoTipo = dadosImovel.getMedicaoTipo();
							
							if ( this.medicaoTipo.intValue() == MedicaoTipo.LIGACAO_AGUA ){
								
								this.medidoAgua = dadosImovel.isMedidoAgua();
								this.leituraAgua = dadosImovel.getLeituraAgua();
								this.anormalidadeAgua = dadosImovel.getAnormalidadeAgua();
								this.indicadorEmissaoConta = dadosImovel.getIndicadorEmissaoConta();
								this.leituraAnteriorFaturamentoAgua = dadosImovel.getLeituraAnteriorFaturamentoAgua();
		        				
							} 
							else if ( this.medicaoTipo.intValue() == MedicaoTipo.POCO ){
								
								this.medidoPoco = dadosImovel.isMedidoPoco();
								this.leituraPoco = dadosImovel.getLeituraPoco();
								this.anormalidadePoco = dadosImovel.getAnormalidadePoco();
								this.indicadorEmissaoConta = dadosImovel.getIndicadorEmissaoConta();
								this.leituraAnteriorFaturamentoPoco = dadosImovel.getLeituraAnteriorFaturamentoPoco();
							}
							
							int qtdLinhasTotal = objects.length; 
							int qtdLinhasSemDuplicidade = qtdLinhasTotal - (i - 1);
					        String[] linhasSemDuplicidade = new String[ qtdLinhasSemDuplicidade ];
					         
					        //GERANDO NOVAMENTE O REGISTRO A PARTIR DA SEGUNDA OCORRÊNCIA
					        linhasSemDuplicidade[0] = linha;
					        linhas = linhasSemDuplicidade;
					        
					        //INICIALIZANDO A CONTAGEM DAS LINHAS
					        i = 1;
							
							//DESCARTANDO A PRIMEIRA OCORRÊNCIA
							primeiroOcorrenciaDescartada = true;
						}
						else{   
				            
							/*
				             * Caso contrário, o sistema irá descartar a primeira ocorrência 
				             */
				            DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dadosImovel = carregarImovel(linha, indicadorAndroid);
							
							//IMÓVEL
							this.idImovel = dadosImovel.getIdImovel();
							
							//TIPO DE MEDIÇÃO
							this.medicaoTipo = dadosImovel.getMedicaoTipo();
							
							if ( this.medicaoTipo.intValue() == MedicaoTipo.LIGACAO_AGUA ){
								
								this.medidoAgua = dadosImovel.isMedidoAgua();
								this.leituraAgua = dadosImovel.getLeituraAgua();
								this.anormalidadeAgua = dadosImovel.getAnormalidadeAgua();
								this.indicadorEmissaoConta = dadosImovel.getIndicadorEmissaoConta();
								this.leituraAnteriorFaturamentoAgua = dadosImovel.getLeituraAnteriorFaturamentoAgua();
		        				
							} 
							else if ( this.medicaoTipo.intValue() == MedicaoTipo.POCO ){
								
								this.medidoPoco = dadosImovel.isMedidoPoco();
								this.leituraPoco = dadosImovel.getLeituraPoco();
								this.anormalidadePoco = dadosImovel.getAnormalidadePoco();
								this.indicadorEmissaoConta = dadosImovel.getIndicadorEmissaoConta();
								this.leituraAnteriorFaturamentoPoco = dadosImovel.getLeituraAnteriorFaturamentoPoco();
							}
							
							int qtdLinhasTotal = objects.length; 
							int qtdLinhasSemDuplicidade = qtdLinhasTotal - (i - 1);
					        String[] linhasSemDuplicidade = new String[ qtdLinhasSemDuplicidade ];
					         
					        //GERANDO NOVAMENTE O REGISTRO A PARTIR DA SEGUNDA OCORRÊNCIA
					        linhasSemDuplicidade[0] = linha;
					        linhas = linhasSemDuplicidade;
					        
					        //INICIALIZANDO A CONTAGEM DAS LINHAS
					        i = 1;
							
							//DESCARTANDO A PRIMEIRA OCORRÊNCIA
							primeiroOcorrenciaDescartada = true;
						}	
					}
					else{
						
						DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dadosImovel = carregarImovel(linha, indicadorAndroid);
						
						//IMÓVEL
						this.idImovel = dadosImovel.getIdImovel();
						
						//TIPO DE MEDIÇÃO
						this.medicaoTipo = dadosImovel.getMedicaoTipo();
						
						if ( this.medicaoTipo.intValue() == MedicaoTipo.LIGACAO_AGUA ){
							
							this.medidoAgua = dadosImovel.isMedidoAgua();
							this.leituraAgua = dadosImovel.getLeituraAgua();
							this.anormalidadeAgua = dadosImovel.getAnormalidadeAgua();
							this.indicadorEmissaoConta = dadosImovel.getIndicadorEmissaoConta();
							this.leituraAnteriorFaturamentoAgua = dadosImovel.getLeituraAnteriorFaturamentoAgua();
	        				
						} 
						else if ( this.medicaoTipo.intValue() == MedicaoTipo.POCO ){
							
							this.medidoPoco = dadosImovel.isMedidoPoco();
							this.leituraPoco = dadosImovel.getLeituraPoco();
							this.anormalidadePoco = dadosImovel.getAnormalidadePoco();
							this.indicadorEmissaoConta = dadosImovel.getIndicadorEmissaoConta();
							this.leituraAnteriorFaturamentoPoco = dadosImovel.getLeituraAnteriorFaturamentoPoco();
						}
					}
				}
			}
			
			this.linhas = linhas;
		}

		public Integer getAnormalidadeAgua() {
			return anormalidadeAgua;
		}

		public Integer getAnormalidadePoco() {
			return anormalidadePoco;
		}

		public Integer getIdImovel() {
			return idImovel;
		}

		public Short getIndicadorEmissaoConta() {
			return indicadorEmissaoConta;
		}

		public Short getIndicadorImovelImpresso() {
			return indicadorImovelImpresso;
		}

		public Integer getLeituraAgua() {
			return leituraAgua;
		}

		public Integer getLeituraPoco() {
			return leituraPoco;
		}

		public boolean isMedidoAgua() {
			return medidoAgua;
		}

		public boolean isMedidoPoco() {
			return medidoPoco;
		}

		public Integer getLeituraAnteriorFaturamentoAgua() {
			return leituraAnteriorFaturamentoAgua;
		}

		public Integer getLeituraAnteriorFaturamentoPoco() {
			return leituraAnteriorFaturamentoPoco;
		}
		
		public void setAnormalidadeAgua(Integer anormalidadeAgua) {
			this.anormalidadeAgua = anormalidadeAgua;
		}

		public void setAnormalidadePoco(Integer anormalidadePoco) {
			this.anormalidadePoco = anormalidadePoco;
		}

		public void setIdImovel(Integer idImovel) {
			this.idImovel = idImovel;
		}

		public void setIndicadorEmissaoConta(Short indicadorEmissaoConta) {
			this.indicadorEmissaoConta = indicadorEmissaoConta;
		}

		public void setIndicadorImovelImpresso(Short indicadorImovelImpresso) {
			this.indicadorImovelImpresso = indicadorImovelImpresso;
		}

		public void setLeituraAgua(Integer leituraAgua) {
			this.leituraAgua = leituraAgua;
		}

		public void setLeituraAnteriorFaturamentoAgua(
				Integer leituraAnteriorFaturamentoAgua) {
			this.leituraAnteriorFaturamentoAgua = leituraAnteriorFaturamentoAgua;
		}

		public void setLeituraAnteriorFaturamentoPoco(
				Integer leituraAnteriorFaturamentoPoco) {
			this.leituraAnteriorFaturamentoPoco = leituraAnteriorFaturamentoPoco;
		}

		public void setLeituraPoco(Integer leituraPoco) {
			this.leituraPoco = leituraPoco;
		}

		public void setMedidoAgua(boolean medidoAgua) {
			this.medidoAgua = medidoAgua;
		}

		public void setMedidoPoco(boolean medidoPoco) {
			this.medidoPoco = medidoPoco;
		}

		public Short getMedicaoTipo() {
			return medicaoTipo;
		}

		public void setMedicaoTipo(Short medicaoTipo) {
			this.medicaoTipo = medicaoTipo;
		}

		/**
	     * Controlador Micromedicao
	     * 
	     * @author Raphael Rossiter
	     * @date 30/04/2008
	     * 
	     * @return ControladorMicromedicaoLocal
	     */
	    protected ControladorMicromedicaoLocal getControladorMicromedicao() {
	        ControladorMicromedicaoLocalHome localHome = null;
	        ControladorMicromedicaoLocal local = null;

	        ServiceLocator locator = null;

	        try {
	            locator = ServiceLocator.getInstancia();

	            localHome = (ControladorMicromedicaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
	            // guarda a referencia de um objeto capaz de fazer chamadas à
	            // objetos remotamente
	            local = localHome.create();

	            return local;
	        } catch (CreateException e) {
	            throw new SistemaException(e);
	        } catch (ServiceLocatorException e) {
	            throw new SistemaException(e);
	        }
	    }
	}
	
	private Collection<DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea> colDadosFormatados = new ArrayList();
	
	private StringBuffer registrosRotaMarcacao = new StringBuffer();
	
	public Collection<DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea> getColDadosFormatados() {
		return colDadosFormatados;
	}

	public RemoverImovesJaProcessadorImpressaoSimultaneaHelper( BufferedReader buffer, short indicadorAndroid ) throws IOException,
		ControladorException{
		
		String linha = null;
		Integer idImovelAnterior = null;
		Collection colLinhas = new ArrayList();
		
		while( ( linha = buffer.readLine() ) != null ){
			
			if ( linha.charAt( 0 ) == '5' ){
				registrosRotaMarcacao.append( linha + "\n" );
			} else {			
	
				Integer idImovel = null;
				//Caso seja android
				if(indicadorAndroid == ConstantesSistema.SIM){
					ArrayList<String> arrayRegistro5 = Util.split(linha);
					// 1 = Posicao que do array que esta a matricula do imovel
					idImovel = Integer.parseInt(arrayRegistro5.get(1));
				}else{
					idImovel = Integer.parseInt( linha.substring( 1, 10 ) );
				}
				
				if ( idImovelAnterior == null ){
					idImovelAnterior = idImovel;
				}
			
				if ( idImovel.intValue() == idImovelAnterior.intValue() ){
					colLinhas.add( linha );
				} else {
					DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dados = new DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea( colLinhas.toArray(), indicadorAndroid );
					this.colDadosFormatados.add( dados );
					idImovelAnterior = idImovel;
					colLinhas = new ArrayList();
					colLinhas.add( linha );
				}
			}
		}
		
		// Adicionamos o ultimo
		DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dados = new DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea( colLinhas.toArray(), indicadorAndroid );
		this.colDadosFormatados.add( dados );
	}

	public StringBuffer getRegistrosRotaMarcacao() {
		return registrosRotaMarcacao;
	}

	public void setRegistrosRotaMarcacao(StringBuffer registrosRotaMarcacao) {
		this.registrosRotaMarcacao = registrosRotaMarcacao;
	}

}
