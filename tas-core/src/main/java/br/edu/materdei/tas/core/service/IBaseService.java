package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import java.util.List;

public interface IBaseService<T> {
    
    List<T> findAll();
    
    T findById(Integer id) throws ResourceNotFoundException;
    
    T save(T entity);
    
    void delete(Integer id) throws ResourceNotFoundException;
    
}
