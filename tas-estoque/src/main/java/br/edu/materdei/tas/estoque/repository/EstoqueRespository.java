package br.edu.materdei.tas.estoque.repository;

import br.edu.materdei.tas.estoque.entity.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRespository extends JpaRepository<EstoqueEntity, Integer>{
    
}
