package br.edu.materdei.tas.votacao.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.votacao.entity.CandidatoEntity;
import br.edu.materdei.tas.votacao.repository.CandidatoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandidatoService implements IBaseService<CandidatoEntity>{

    @Autowired
    private CandidatoRepository repository;
    
    @Override
    @Transactional
    public List<CandidatoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public CandidatoEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public CandidatoEntity save(CandidatoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
