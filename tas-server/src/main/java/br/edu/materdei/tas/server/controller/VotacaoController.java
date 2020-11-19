package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.entity.GrupoEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.GrupoService;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
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
public class GrupoController {
    
    @Autowired
    private GrupoService service;
    
    @GetMapping("grupos")
    public ResponseEntity<List<GrupoEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<GrupoEntity> grupos = service.findAll();

            //Retorna a lista de grupos
            return new ResponseEntity(grupos, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("grupos")
    public ResponseEntity create(@RequestBody GrupoEntity grupo) {
        try {
            //Insere o grupo no bando de dados
            this.service.save(grupo);
            
            //Retorna o grupo inserido
            return new ResponseEntity(grupo, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("grupos/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um grupo com o ID passado por parametro
            GrupoEntity grupo = this.service.findById(id);
            
            //Retorna o grupo com o ID do parametro
            return new ResponseEntity(grupo, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de grupo não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um grupo com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("grupos/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody GrupoEntity grupo) {
        try {
            //Verifica se existe um grupo com o ID passado por parametro
            GrupoEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            grupo.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(grupo);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(grupo, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de grupo não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um grupo com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("grupos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um grupo com o ID passado por parametro
            GrupoEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de grupo não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um grupo com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
