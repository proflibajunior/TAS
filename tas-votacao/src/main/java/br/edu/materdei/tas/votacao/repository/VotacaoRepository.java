package br.edu.materdei.tas.votacao.repository;

import br.edu.materdei.tas.votacao.entity.VotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotacaoRepository extends JpaRepository<VotacaoEntity, Integer> {
    
}
