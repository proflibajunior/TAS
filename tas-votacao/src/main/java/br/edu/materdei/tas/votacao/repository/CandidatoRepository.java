package br.edu.materdei.tas.votacao.repository;

import br.edu.materdei.tas.votacao.entity.CandidatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoEntity, Integer> {
    
}
