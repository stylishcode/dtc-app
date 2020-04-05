package projeto.dtc.validation;

import org.springframework.util.StringUtils;

import projeto.dtc.exception.BusinessValidation;
import projeto.dtc.model.DadosAnalise;

public class DadosAnaliseValidation {
	
	public static void validarDados(DadosAnalise dadosAnalise) {
		
		BusinessValidation bv = new BusinessValidation();
		
		if (StringUtils.isEmpty(dadosAnalise.getChaveGoogle())) {
			bv.addErro("Chave do google não pode ser vazia");
		}
		
		if (StringUtils.isEmpty(dadosAnalise.getPontoOrigem())) {
			bv.addErro("Ponto de Origem não pode ser vazio");
		}
		
		if(dadosAnalise.getPontoOrigem().split(",").length != 7) {
			bv.addErro("Número de parâmetros da origem deve ser 7");
		} else {
			String[] apo = dadosAnalise.getPontoOrigem().split(",");
			try {
				Float.parseFloat(apo[0]);
				Float.parseFloat(apo[2]);
				Float.parseFloat(apo[3]);
				Float.parseFloat(apo[4]);
				Float.parseFloat(apo[5]);
			} catch (Exception e) {
				bv.addErro("Os elementos: índice, latitude, longitude, utmX e utmY devem necessariamente ser numéricos para o ponto de origem");
			}
		}
		
		if (StringUtils.isEmpty(dadosAnalise.getPontoDestino())) {
			bv.addErro("Ponto de Destino não pode ser vazio");
		}
		
		if (dadosAnalise.getPontoDestino().split(",").length != 7) {
			bv.addErro("Número de parâmetros do destino deve ser 7");
		} else {
			String[] apo = dadosAnalise.getPontoDestino().split(",");
			try {
				Float.parseFloat(apo[0]);
				Float.parseFloat(apo[2]);
				Float.parseFloat(apo[3]);
				Float.parseFloat(apo[4]);
				Float.parseFloat(apo[5]);
			} catch (Exception e) {
				bv.addErro("Os elementos: índice, latitude, longitude, utmX e utmY devem necessariamente ser numéricos para o ponto de destino");
			}
		}
		
		if (StringUtils.isEmpty(dadosAnalise.getModal())) {
			bv.addErro("Modal não pode ser vazio");
		}
		
		if (bv.hasError()) {
			throw bv;
		}
		
	}
}
