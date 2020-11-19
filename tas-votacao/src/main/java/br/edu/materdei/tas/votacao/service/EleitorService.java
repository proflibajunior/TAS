package br.edu.materdei.tas.votacao.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.votacao.entity.EleitorEntity;
import br.edu.materdei.tas.votacao.repository.EleitorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EleitorService implements IBaseService<EleitorEntity>{

    @Autowired
    private EleitorRepository repository;
    
    @Override
    @Transactional
    public List<EleitorEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public EleitorEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public EleitorEntity save(EleitorEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
