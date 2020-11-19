package br.edu.materdei.tas.votacao.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.votacao.entity.ZonaEntity;
import br.edu.materdei.tas.votacao.repository.ZonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaService implements IBaseService<ZonaEntity>{

    @Autowired
    private ZonaRepository repository;
    
    @Override
    @Transactional
    public List<ZonaEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public ZonaEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public ZonaEntity save(ZonaEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
