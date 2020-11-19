package br.edu.materdei.tas.votacao.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.votacao.entity.SecaoEntity;
import br.edu.materdei.tas.votacao.repository.SecaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecaoService implements IBaseService<SecaoEntity>{

    @Autowired
    private SecaoRepository repository;
    
    @Override
    @Transactional
    public List<SecaoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public SecaoEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public SecaoEntity save(SecaoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
