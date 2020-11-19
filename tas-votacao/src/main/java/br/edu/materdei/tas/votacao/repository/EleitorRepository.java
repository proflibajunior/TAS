package br.edu.materdei.tas.votacao.repository;

import br.edu.materdei.tas.votacao.entity.EleitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleitorRepository extends JpaRepository<EleitorEntity, Integer> {
    
}
