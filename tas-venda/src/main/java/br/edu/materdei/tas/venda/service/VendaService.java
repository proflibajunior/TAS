package br.edu.materdei.tas.venda.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.repository.VendaRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService implements IBaseService<VendaEntity>{

    @Autowired
    private VendaRepository repository;
    
    @Override
     @Transactional
    public List<VendaEntity> findAll() {
        return repository.findAll();
    }

    @Override
     @Transactional
    public VendaEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
     @Transactional
    public VendaEntity save(VendaEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
     @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
