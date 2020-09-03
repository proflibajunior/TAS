package br.edu.materdei.tas.venda.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.venda.entity.ClienteEntity;
import br.edu.materdei.tas.venda.repository.ClienteRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IBaseService<ClienteEntity>{

    @Autowired
    private ClienteRepository repository;
    
    @Override
     @Transactional
    public List<ClienteEntity> findAll() {
        return repository.findAll();
    }

    @Override
     @Transactional
    public ClienteEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
     @Transactional
    public ClienteEntity save(ClienteEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
     @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
