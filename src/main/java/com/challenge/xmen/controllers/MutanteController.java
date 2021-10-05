package com.challenge.xmen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.xmen.models.Dna;
import com.challenge.xmen.models.RespuestaStats;
import com.challenge.xmen.services.MutanteService;

@RestController
@RequestMapping()
public class MutanteController { 

	@Autowired
	MutanteService mutanteService;


	@PostMapping("/mutant")
	public ResponseEntity<String> determinarMutante(@RequestBody Dna dna){

		mutanteService = new MutanteService();
		/* Valida Cadena DNA */
		boolean alerta = mutanteService.validarDna(dna.getDna());

		if (alerta) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403-Forbiden");
		}
		
		boolean isMutante = mutanteService.isMutante(dna.getDna());
		
		if (isMutante) {
			return ResponseEntity.status(HttpStatus.OK).body("HTTP 200-OK");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403-Forbiden");
	}
	
	@GetMapping("/stats")
	public RespuestaStats realizarEstadistica(){		

		return mutanteService.consultarData();		
	}

}
