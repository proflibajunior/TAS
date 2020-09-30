package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.ProdutoService;
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
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;
    
    @GetMapping("produtos")
    public ResponseEntity<List<ProdutoEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<ProdutoEntity> produtos = service.findAll();

            //Retorna a lista de produtos
            return new ResponseEntity(produtos, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("produtos")
    public ResponseEntity create(@RequestBody ProdutoEntity produto) {
        try {
            //Insere o produto no bando de dados
            this.service.save(produto);
            
            //Retorna o produto inserido
            return new ResponseEntity(produto, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("produtos/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um produto com o ID passado por parametro
            ProdutoEntity produto = this.service.findById(id);
            
            //Retorna o produto com o ID do parametro
            return new ResponseEntity(produto, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de produto não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um produto com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("produtos/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody ProdutoEntity produto) {
        try {
            //Verifica se existe um produto com o ID passado por parametro
            ProdutoEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            produto.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(produto);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(produto, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de produto não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um produto com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("produtos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um produto com o ID passado por parametro
            ProdutoEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de produto não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um produto com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
