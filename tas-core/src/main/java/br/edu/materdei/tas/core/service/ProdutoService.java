package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService implements IBaseService<ProdutoEntity>{

    @Autowired
    private ProdutoRepository repository;
    
    @Override
    @Transactional
    public List<ProdutoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public ProdutoEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public ProdutoEntity save(ProdutoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
