package com.challenge.xmen.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.xmen.models.EstadisticasMutanteModel;

@Repository
public interface MutanteRepository extends CrudRepository<EstadisticasMutanteModel, Long> {

	
	@Query(value ="select sum(count_mutant_dna) from estadisticas", nativeQuery =true)
	public Long sumMutantes();
	
	@Query(value ="select sum(count_human_dna) from estadisticas", nativeQuery = true)
	public Long sumHumanos();
}
