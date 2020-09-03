package br.edu.materdei.tas.compra.repository;

import br.edu.materdei.tas.compra.entity.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Integer> {
    
}
