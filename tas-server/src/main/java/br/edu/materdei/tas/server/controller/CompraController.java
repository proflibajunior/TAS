package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.compra.entity.CompraEntity;
import br.edu.materdei.tas.compra.service.CompraService;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
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
public class CompraController {
    
    @Autowired
    private CompraService service;
    
    @GetMapping("compras")
    public ResponseEntity<List<CompraEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<CompraEntity> compras = service.findAll();

            //Retorna a lista de compras
            return new ResponseEntity(compras, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("compras")
    public ResponseEntity create(@RequestBody CompraEntity compra) {
        try {
            //Insere o compra no bando de dados
            this.service.save(compra);
            
            //Retorna o compra inserido
            return new ResponseEntity(compra, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("compras/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um compra com o ID passado por parametro
            CompraEntity compra = this.service.findById(id);
            
            //Retorna o compra com o ID do parametro
            return new ResponseEntity(compra, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de compra não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um compra com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("compras/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody CompraEntity compra) {
        try {
            //Verifica se existe um compra com o ID passado por parametro
            CompraEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            compra.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(compra);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(compra, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de compra não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um compra com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("compras/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um compra com o ID passado por parametro
            CompraEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de compra não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um compra com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
