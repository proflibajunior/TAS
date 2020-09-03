package br.edu.materdei.tas.compra.service;

import br.edu.materdei.tas.compra.entity.FornecedorEntity;
import br.edu.materdei.tas.compra.repository.FornecedorRepository;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService implements IBaseService<FornecedorEntity>{

    @Autowired
    private FornecedorRepository repository;
    
    @Override
    public List<FornecedorEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public FornecedorEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    public FornecedorEntity save(FornecedorEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
