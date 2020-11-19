package br.edu.materdei.tas.votacao.repository;

import br.edu.materdei.tas.votacao.entity.PartidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<PartidoEntity, Integer> {
    
}
