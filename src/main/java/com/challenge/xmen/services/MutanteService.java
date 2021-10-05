package com.challenge.xmen.services;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challenge.xmen.models.EstadisticasMutanteModel;
import com.challenge.xmen.models.RespuestaStats;
import com.challenge.xmen.repositories.MutanteRepository;

@Service
public class MutanteService {

	@Autowired
	MutanteRepository mutanteRepository;

	public boolean validarDna(String[] dna) {

		int tamanoArray = dna.length;
		int tamanoString = dna[0].length();
		boolean alerta = false;

		for (int i = 0; i < dna.length; i++) {
			if (dna[i].length() != tamanoString || tamanoArray != dna[i].length()) {
				alerta = true;
			}
		}
		return alerta;
	}

	public boolean isMutante(String[] dna) {

		int tamanoArray = dna.length;
		int tamanoString = dna[0].length();
		char[][] dnaMatriz = new char[tamanoArray][tamanoArray];
		char[] dnaAux = new char[tamanoString];

		/* Llenado de la matriz a partir de array */
		for (int i = 0; i < dna.length; i++) {

			dnaAux = dna[i].toCharArray();
			for (int j = 0; j < dnaAux.length; j++) {
				dnaMatriz[i][j] = dnaAux[j];
			}
		}

		/* Mostrar matriz a partir de array */
		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna.length; j++) {
				System.out.print(dnaMatriz[i][j] + "|");
			}
			System.out.println("");
		}

		/* Contador de cadenas horizontal */
		char letra = ' ';
		int contDna = 0;
		int contSecuencia = 0;

		for (int i = 0; i < tamanoArray; i++) {
			letra = dnaMatriz[i][0];
			contDna = 0;
			for (int j = 1; j < tamanoString; j++) {
				if (letra == dnaMatriz[i][j]) {
					contDna++;
				} else {
					letra = dnaMatriz[i][j];
					contDna = 0;
				}
				if (contDna == 3) {
					contSecuencia++;
					contDna = 0;
				}
				if (contSecuencia > 1) {
					return true;
				}
			}
		}

		/* Contador de cadenas vertical */

		for (int i = 0; i < tamanoArray; i++) {
			contDna = 0;
			letra = dnaMatriz[0][i];
			for (int j = 1; j < tamanoString; j++) {
				if (letra == dnaMatriz[j][i]) {
					contDna++;
				} else {
					letra = dnaMatriz[j][i];
					contDna = 0;
				}
				if (contDna == 3) {
					contSecuencia++;
					contDna = 0;
				}
				if (contSecuencia > 1) {
					return true;
				}
			}
		}

		/* Contador de cadenas oblicuas izq-der sup */
		for (int i = 0; i < tamanoArray - 3; i++) {

			contDna = 0;
			letra = dnaMatriz[0][i];

			for (int j = 1; j < tamanoString - i; j++) {

				if (letra == dnaMatriz[j][j + i]) {
					contDna++;
				} else {
					letra = dnaMatriz[j][j + i];
					contDna = 0;
				}
				if (contDna == 3) {
					contSecuencia++;
					contDna = 0;
				}
				if (contSecuencia > 1) {
					return true;
				}
			}
		}

		/* Contador de cadenas oblicuas izq-der inf */
		for (int i = 1; i < tamanoArray - 3; i++) {

			contDna = 0;
			letra = dnaMatriz[i][0];

			for (int j = 1; j < tamanoString - i; j++) {

				if (letra == dnaMatriz[j + i][j]) {
					contDna++;
				} else {
					letra = dnaMatriz[j + i][j];
					contDna = 0;
				}
				if (contDna == 3) {
					contSecuencia++;
					contDna = 0;
				}
				if (contSecuencia > 1) {
					return true;
				}
			}
		}

		/* Contador de cadenas oblicuas der-izq sup */
		for (int i = tamanoString - 1; i >= 3; i--) {

			contDna = 0;
			letra = dnaMatriz[0][i];

			for (int j = 1; j < i + 1; j++) {
				if (letra == dnaMatriz[j][i - j]) {
					contDna++;
				} else {
					letra = dnaMatriz[j][i - j];
					contDna = 0;
				}
				if (contDna == 3) {
					contSecuencia++;
					contDna = 0;
				}
				if (contSecuencia > 1) {
					return true;
				}
			}
		}

		/* Contador de cadenas oblicuas der-izq inf */
		for (int i = 0; i < tamanoString - 4; i++) {
			contDna = 0;
			letra = dnaMatriz[i + 1][tamanoString - 1];

			for (int j = 2; j < tamanoString - 1; j++) {
				if (letra == dnaMatriz[j + i][tamanoString - j]) {
					contDna++;
				} else {
					letra = dnaMatriz[j + i][tamanoString - j];
					contDna = 0;
				}
				if (contDna == 3) {
					contSecuencia++;
					contDna = 0;
				}
				if (contSecuencia > 1) {
					return true;
				}
			}
		}

		System.out.println("Cantidad de secuencias:" + String.valueOf(contSecuencia));

		boolean isMutante = false;

		if (contSecuencia > 1) {

			this.guardarRegistro(1, 0, Arrays.toString(dna));
			isMutante = true;

		} else {

			this.guardarRegistro(0, 1, Arrays.toString(dna));

		}

		return isMutante;
	}

	public void guardarRegistro(int countMutantDna, int countHumanDna, String registroDna) {

		EstadisticasMutanteModel estadisticasMutanteModel = new EstadisticasMutanteModel();
		estadisticasMutanteModel.setCountMutantDna(countMutantDna);
		estadisticasMutanteModel.setCountHumanDna(countHumanDna);
		estadisticasMutanteModel.setRegistro(registroDna);
		estadisticasMutanteModel.setId(0);

		try {
			mutanteRepository.save(estadisticasMutanteModel);

		} catch (Exception e) {
			System.out.println("Algo salio mal con el guardado de datos...");
		}

	}

	public RespuestaStats consultarData() {

		long contMutante = 0;
		long contHumano = 0;
		double ratio = 0;

		RespuestaStats respuestaStats = new RespuestaStats();

		try {
			contMutante = mutanteRepository.sumMutantes();
			contHumano = mutanteRepository.sumHumanos();
			ratio = Double.valueOf(contMutante) / Double.valueOf(contHumano);

			respuestaStats.setCountHumanDna(contHumano);
			respuestaStats.setCountMutantDna(contMutante);
			respuestaStats.setRatio(ratio);

		} catch (Exception e) {

			System.out.println("Algo salio mal ...");

			respuestaStats.setCountHumanDna(0);
			respuestaStats.setCountMutantDna(0);
			respuestaStats.setRatio(0);

			return respuestaStats;
		}

		return respuestaStats;

	}

}
