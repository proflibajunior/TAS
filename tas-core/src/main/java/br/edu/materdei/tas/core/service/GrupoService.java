package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.entity.GrupoEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.repository.GrupoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoService implements IBaseService<GrupoEntity>{

    @Autowired
    private GrupoRepository repostory;
    
    @Override
    public List<GrupoEntity> findAll() {
        return repostory.findAll();
    }

    @Override
    public GrupoEntity findById(Integer id) throws ResourceNotFoundException {
        
    }

    @Override
    public GrupoEntity save(GrupoEntity entity) {
        
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        
    }
    
}
