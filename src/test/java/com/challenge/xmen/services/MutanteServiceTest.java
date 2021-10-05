package com.challenge.xmen.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.challenge.xmen.models.EstadisticasMutanteModel;
import com.challenge.xmen.repositories.MutanteRepository;

class MutanteServiceTest {

	MutanteRepository mutanteRepositoryMock = Mockito.mock(MutanteRepository.class);

	@Test
	void testValidarDna() {

		MutanteService mutanteService = new MutanteService();
		String[] dnaPrimer = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		String[] dnaSegundo = { "ATGCGA", "CAGTGC", "TTAT", "AGAAGG", "CCCCTA", "TCACTG" };

		boolean resultado = mutanteService.validarDna(dnaPrimer);
		Assertions.assertEquals(false, resultado);
		boolean resultadoDos = mutanteService.validarDna(dnaSegundo);
		Assertions.assertEquals(true, resultadoDos);
	}

	@BeforeEach
	void setUp() throws Exception {

		EstadisticasMutanteModel estadisticasMutanteModelMock = new EstadisticasMutanteModel();
		estadisticasMutanteModelMock.setCountMutantDna(1);
		estadisticasMutanteModelMock.setCountHumanDna(0);
		estadisticasMutanteModelMock.setRegistro("ATGCGA");
		estadisticasMutanteModelMock.setId(0);

		Mockito.when(mutanteRepositoryMock.save(estadisticasMutanteModelMock)).thenReturn(estadisticasMutanteModelMock);
	}

	@Test
	void testGuardarRegistro() {
		EstadisticasMutanteModel estadisticasMutanteModelMock = new EstadisticasMutanteModel();
		MutanteService mutanteService = new MutanteService();
		estadisticasMutanteModelMock = mutanteService.guardarRegistro(1, 0,
				"ATGCGA" + "CAGTGC" + "TTATGT" + "AGAAGG" + "CCCCTA" + "TCACTG");
	}

	@Test
	void isMutante() {

		MutanteService mutanteService = new MutanteService();
		String[] dnaUno = { "AAAAAA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		String[] dnaDos = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		String[] dnatres = { "ATGCAA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		String[] dnaCuatro = { "TTGCAA", "CAGTGC", "CTATGT", "ACAAGG", "CCCCTA", "TCACTG" };
		String[] dnaCinco = { "TTGCAA", "AAGTAC", "TTAAGT", "ACAAGG", "CCCCTA", "TCACTG" };
		String[] dnaSeis = { "TTGCAG", "TAGTAC", "TTAAGG", "TCAAGG", "CCCGTA", "TCGCTG" };

		boolean resultadoUno = mutanteService.isMutante(dnaUno);
		boolean resultadoDos = mutanteService.isMutante(dnaDos);
		boolean resultadotres = mutanteService.isMutante(dnatres);
		boolean resultadoCuatro = mutanteService.isMutante(dnaCuatro);
		boolean resultadoCinco = mutanteService.isMutante(dnaCinco);
		boolean resultadoSeis = mutanteService.isMutante(dnaSeis);

		Assertions.assertEquals(true, resultadoUno);
		Assertions.assertEquals(true, resultadoDos);
		Assertions.assertEquals(true, resultadotres);
		Assertions.assertEquals(true, resultadoCuatro);
		Assertions.assertEquals(true, resultadoCinco);
		Assertions.assertEquals(true, resultadoSeis);
	}

	@Test
	void testRealizarEstadistica() {
		Mockito.when(mutanteRepositoryMock.sumMutantes()).thenReturn((long) 1);
		Mockito.when(mutanteRepositoryMock.sumHumanos()).thenReturn((long) 0);
		
		MutanteService mutanteService = new MutanteService();
		mutanteService.consultarData();
		
	}

}
