package br.edu.materdei.tas.server.controller;


import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.votacao.entity.SecaoEntity;
import br.edu.materdei.tas.votacao.service.SecaoService;
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
public class SecaoController {
    
    @Autowired
    private SecaoService service;
    
    @GetMapping("secoes")
    public ResponseEntity<List<SecaoEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<SecaoEntity> secoes = service.findAll();

            //Retorna a lista de secoes
            return new ResponseEntity(secoes, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("secoes")
    public ResponseEntity create(@RequestBody SecaoEntity secao) {
        try {
            //Insere o secao no bando de dados
            this.service.save(secao);
            
            //Retorna o secao inserido
            return new ResponseEntity(secao, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("secoes/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um secao com o ID passado por parametro
            SecaoEntity secao = this.service.findById(id);
            
            //Retorna o secao com o ID do parametro
            return new ResponseEntity(secao, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de secao não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um secao com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("secoes/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody SecaoEntity secao) {
        try {
            //Verifica se existe um secao com o ID passado por parametro
            SecaoEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            secao.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(secao);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(secao, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de secao não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um secao com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("secoes/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um secao com o ID passado por parametro
            SecaoEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de secao não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um secao com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
