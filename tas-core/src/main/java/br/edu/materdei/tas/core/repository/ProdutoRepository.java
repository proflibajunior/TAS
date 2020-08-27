package br.edu.materdei.tas.core.repository;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {
    
}
