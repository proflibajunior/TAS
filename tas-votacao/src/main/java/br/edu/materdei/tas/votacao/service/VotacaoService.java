package br.edu.materdei.tas.votacao.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.votacao.entity.VotacaoEntity;
import br.edu.materdei.tas.votacao.repository.VotacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotacaoService implements IBaseService<VotacaoEntity>{

    @Autowired
    private VotacaoRepository repository;
    
    @Override
    @Transactional
    public List<VotacaoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public VotacaoEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public VotacaoEntity save(VotacaoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
