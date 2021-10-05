package com.challenge.xmen.services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MutanteServiceTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	
	@Test
	void testValidarDna() {

		MutanteService  mutanteService = new MutanteService();
		String[] dnaPrimer = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		String[] dnaSegundo = {"ATGCGA","CAGTGC","TTAT","AGAAGG","CCCCTA","TCACTG"};

		boolean resultado = mutanteService.validarDna(dnaPrimer);
		Assertions.assertEquals(false, resultado);
		boolean resultadoDos = mutanteService.validarDna(dnaSegundo);
		Assertions.assertEquals(true, resultadoDos);
		
		
		
		
	}

}
