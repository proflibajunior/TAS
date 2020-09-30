package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.venda.entity.PedidoEntity;
import br.edu.materdei.tas.venda.service.PedidoService;
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
public class PedidoController {
    
    @Autowired
    private PedidoService service;
    
    @GetMapping("pedidos")
    public ResponseEntity<List<PedidoEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<PedidoEntity> pedidos = service.findAll();

            //Retorna a lista de pedidos
            return new ResponseEntity(pedidos, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("pedidos")
    public ResponseEntity create(@RequestBody PedidoEntity pedido) {
        try {
            //Insere o pedido no bando de dados
            this.service.save(pedido);
            
            //Retorna o pedido inserido
            return new ResponseEntity(pedido, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("pedidos/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um pedido com o ID passado por parametro
            PedidoEntity pedido = this.service.findById(id);
            
            //Retorna o pedido com o ID do parametro
            return new ResponseEntity(pedido, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de pedido não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um pedido com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("pedidos/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody PedidoEntity pedido) {
        try {
            //Verifica se existe um pedido com o ID passado por parametro
            PedidoEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            pedido.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(pedido);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(pedido, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de pedido não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um pedido com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("pedidos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um pedido com o ID passado por parametro
            PedidoEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de pedido não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um pedido com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
