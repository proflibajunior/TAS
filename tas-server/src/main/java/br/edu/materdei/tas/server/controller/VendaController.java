package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.service.VendaService;
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
public class VendaController {
    
    @Autowired
    private VendaService service;
    
    @GetMapping("vendas")
    public ResponseEntity<List<VendaEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<VendaEntity> vendas = service.findAll();

            //Retorna a lista de vendas
            return new ResponseEntity(vendas, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("vendas")
    public ResponseEntity create(@RequestBody VendaEntity venda) {
        try {
            //Insere o venda no bando de dados
            this.service.save(venda);
            
            //Retorna o venda inserido
            return new ResponseEntity(venda, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("vendas/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um venda com o ID passado por parametro
            VendaEntity venda = this.service.findById(id);
            
            //Retorna o venda com o ID do parametro
            return new ResponseEntity(venda, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de venda não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um venda com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("vendas/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody VendaEntity venda) {
        try {
            //Verifica se existe um venda com o ID passado por parametro
            VendaEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            venda.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(venda);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(venda, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de venda não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um venda com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("vendas/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um venda com o ID passado por parametro
            VendaEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de venda não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um venda com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
