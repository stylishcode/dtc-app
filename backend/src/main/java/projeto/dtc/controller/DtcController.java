package projeto.dtc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import projeto.business.CalcularDistancias;
import projeto.dtc.dao.DadosRepository;
import projeto.dtc.model.DadosAnalise;
import projeto.dtc.model.Ponto;
import projeto.dtc.validation.DadosAnaliseValidation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST DTC")
@CrossOrigin(origins = "*")
public class DtcController {

	private DadosRepository coordenadasDao;
	@Autowired
	public DtcController(DadosRepository coordenadasDao) {
		this.coordenadasDao = coordenadasDao;
	}

	@GetMapping(path = "/coordenadas")
	@ApiOperation(value = "Listar Pontos")
	public ResponseEntity<?> listAll() {

		return new ResponseEntity<>(coordenadasDao.findAll(), HttpStatus.OK);
	}

	@PostMapping(path = "/coordenadas")
	@ApiOperation(value = "Salvar pontos")
	public ResponseEntity<?> save(@RequestBody DadosAnalise dadosAnalise) throws ClientProtocolException, IOException, InterruptedException {
		
		// Validando informacoes do objeto.
		DadosAnaliseValidation.validarDados(dadosAnalise);

//		try {
//			Thread.sleep(10000L);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		CalcularDistancias cd = new CalcularDistancias();
		
		List<Ponto> pontosOrigem = new ArrayList<Ponto>();
		String[] aop = dadosAnalise.getPontoOrigem().split("/n");
		for (int i = 0; i < aop.length; i++) {
			String[] p = aop[i].split(",");
			pontosOrigem.add(new Ponto(Integer.parseInt(p[0]),p[1],p[2],p[3],p[4],p[5],"x"));
		}
		
		List<Ponto> pontosDestino = new ArrayList<Ponto>();
		String[] aod = dadosAnalise.getPontoDestino().split("/n");
		for (int i = 0; i < aod.length; i++) {
			String[] p = aod[i].split(",");
			pontosDestino.add(new Ponto(Integer.parseInt(p[0]),p[1],p[2],p[3],p[4],p[5],"x"));
		}
		
		String resultado = cd.estimarTrajetoFinal(dadosAnalise.getChaveGoogle(), dadosAnalise.getModal(), pontosOrigem, pontosDestino);
		
		dadosAnalise.setResultado(resultado);
		
		//dadosAnalise.setResultado("RESULTADO GERADOR COM SUCESSOo - " + System.currentTimeMillis());
		// return new ResponseEntity<>(dadosAnalise.getChaveGoogle()+" - "+dadosAnalise.getModal()+" - "
		// +dadosAnalise.getPontoOrigem()+ " - "+dadosAnalise.getPontoDestino()+" - "+dadosAnalise.getResultado(), HttpStatus.CREATED);
		//return new ResponseEntity<>(coordenadasDao.save(dadosAnalise), HttpStatus.CREATED);
		
//		try {
//			Thread.sleep(500L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return new ResponseEntity<>(dadosAnalise, HttpStatus.CREATED);
	}
}
