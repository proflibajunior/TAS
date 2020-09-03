package br.edu.materdei.tas.venda.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.venda.entity.PedidoEntity;
import br.edu.materdei.tas.venda.repository.PedidoRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService implements IBaseService<PedidoEntity>{

    @Autowired
    private PedidoRepository repository;
    
    @Override
     @Transactional
    public List<PedidoEntity> findAll() {
        return repository.findAll();
    }

    @Override
     @Transactional
    public PedidoEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
     @Transactional
    public PedidoEntity save(PedidoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
     @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
