package br.edu.materdei.tas.compra.repository;

import br.edu.materdei.tas.compra.entity.FornecedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Integer>{
    
}
