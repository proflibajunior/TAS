package br.edu.materdei.tas.votacao.repository;

import br.edu.materdei.tas.votacao.entity.ZonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends JpaRepository<ZonaEntity, Integer> {
    
}
