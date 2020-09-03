package br.edu.materdei.tas.compra.service;

import br.edu.materdei.tas.compra.entity.CompraEntity;
import br.edu.materdei.tas.compra.repository.CompraRepository;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService implements IBaseService<CompraEntity>{

    @Autowired
    private CompraRepository repository;
    
    @Override
    public List<CompraEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public CompraEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    public CompraEntity save(CompraEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
