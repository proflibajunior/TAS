package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.entity.GrupoEntity;
import br.edu.materdei.tas.core.service.GrupoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrupoController {
    
    @Autowired
    private GrupoService service;
    
    @GetMapping("grupos")
    public ResponseEntity<List<GrupoEntity>> findAll() {
        List<GrupoEntity> grupos = service.findAll();
        
        return new ResponseEntity<List<GrupoEntity>>(grupos, HttpStatus.OK);        
    }
    
}
