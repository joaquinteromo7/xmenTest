package com.challenge.xmen.models;
import javax.persistence.*;

@Entity
@Table(name = "estadisticas")
public class EstadisticasMutanteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;
	private long countMutantDna;
	private long countHumanDna;
	private String registro;
	
	public long getId() {
		return id;
	}
	public long getCountHumanDna() {
		return countHumanDna;
	}
	public void setCountHumanDna(long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCountMutantDna() {
		return countMutantDna;
	}
	public void setCountMutantDna(long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	
	
	
}
