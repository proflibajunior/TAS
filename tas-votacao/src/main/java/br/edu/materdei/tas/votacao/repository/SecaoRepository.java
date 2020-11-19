package br.edu.materdei.tas.votacao.repository;

import br.edu.materdei.tas.votacao.entity.SecaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoRepository extends JpaRepository<SecaoEntity, Integer> {
    
}
