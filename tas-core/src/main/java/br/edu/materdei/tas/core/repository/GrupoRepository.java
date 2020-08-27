package br.edu.materdei.tas.core.repository;

import br.edu.materdei.tas.core.entity.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Integer>{
    
}
