package br.edu.materdei.tas.server.controller;


import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.votacao.entity.ZonaEntity;
import br.edu.materdei.tas.votacao.service.ZonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZonaController {
    
    @Autowired
    private ZonaService service;
    
    @GetMapping("zonas")
    public ResponseEntity<List<ZonaEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<ZonaEntity> zonas = service.findAll();

            //Retorna a lista de zonas
            return new ResponseEntity(zonas, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("zonas")
    public ResponseEntity create(@RequestBody ZonaEntity zona) {
        try {
            //Insere o zona no bando de dados
            this.service.save(zona);
            
            //Retorna o zona inserido
            return new ResponseEntity(zona, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("zonas/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um zona com o ID passado por parametro
            ZonaEntity zona = this.service.findById(id);
            
            //Retorna o zona com o ID do parametro
            return new ResponseEntity(zona, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de zona não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um zona com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("zonas/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody ZonaEntity zona) {
        try {
            //Verifica se existe um zona com o ID passado por parametro
            ZonaEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            zona.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(zona);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(zona, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de zona não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um zona com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("zonas/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um zona com o ID passado por parametro
            ZonaEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de zona não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um zona com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
