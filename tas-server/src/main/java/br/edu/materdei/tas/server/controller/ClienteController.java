package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.venda.entity.ClienteEntity;
import br.edu.materdei.tas.venda.service.ClienteService;
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
public class ClienteController {
    
    @Autowired
    private ClienteService service;
    
    @GetMapping("clientes")
    public ResponseEntity<List<ClienteEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<ClienteEntity> clientes = service.findAll();

            //Retorna a lista de clientes
            return new ResponseEntity(clientes, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("clientes")
    public ResponseEntity create(@RequestBody ClienteEntity cliente) {
        try {
            //Insere o cliente no bando de dados
            this.service.save(cliente);
            
            //Retorna o cliente inserido
            return new ResponseEntity(cliente, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("clientes/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um cliente com o ID passado por parametro
            ClienteEntity cliente = this.service.findById(id);
            
            //Retorna o cliente com o ID do parametro
            return new ResponseEntity(cliente, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de cliente não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um cliente com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("clientes/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody ClienteEntity cliente) {
        try {
            //Verifica se existe um cliente com o ID passado por parametro
            ClienteEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            cliente.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(cliente);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(cliente, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de cliente não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um cliente com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("clientes/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um cliente com o ID passado por parametro
            ClienteEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de cliente não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um cliente com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
