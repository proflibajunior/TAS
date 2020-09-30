package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.compra.entity.FornecedorEntity;
import br.edu.materdei.tas.compra.service.FornecedorService;
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
public class FornecedorController {
    
    @Autowired
    private FornecedorService service;
    
    @GetMapping("fornecedores")
    public ResponseEntity<List<FornecedorEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<FornecedorEntity> fornecedores = service.findAll();

            //Retorna a lista de fornecedores
            return new ResponseEntity(fornecedores, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("fornecedores")
    public ResponseEntity create(@RequestBody FornecedorEntity fornecedor) {
        try {
            //Insere o fornecedor no bando de dados
            this.service.save(fornecedor);
            
            //Retorna o fornecedor inserido
            return new ResponseEntity(fornecedor, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("fornecedores/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um fornecedor com o ID passado por parametro
            FornecedorEntity fornecedor = this.service.findById(id);
            
            //Retorna o fornecedor com o ID do parametro
            return new ResponseEntity(fornecedor, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de fornecedor não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um fornecedor com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("fornecedores/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody FornecedorEntity fornecedor) {
        try {
            //Verifica se existe um fornecedor com o ID passado por parametro
            FornecedorEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            fornecedor.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(fornecedor);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(fornecedor, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de fornecedor não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um fornecedor com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("fornecedores/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um fornecedor com o ID passado por parametro
            FornecedorEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de fornecedor não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um fornecedor com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
