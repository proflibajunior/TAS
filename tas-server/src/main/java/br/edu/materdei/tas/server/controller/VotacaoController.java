package br.edu.materdei.tas.server.controller;


import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.votacao.entity.VotacaoEntity;
import br.edu.materdei.tas.votacao.service.VotacaoService;
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
public class VotacaoController {
    
    @Autowired
    private VotacaoService service;
    
    @GetMapping("votacoes")
    public ResponseEntity<List<VotacaoEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<VotacaoEntity> votacoes = service.findAll();

            //Retorna a lista de votacoes
            return new ResponseEntity(votacoes, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("votacoes")
    public ResponseEntity create(@RequestBody VotacaoEntity votacao) {
        try {
            //Insere o votacao no bando de dados
            this.service.save(votacao);
            
            //Retorna o votacao inserido
            return new ResponseEntity(votacao, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("votacoes/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um votacao com o ID passado por parametro
            VotacaoEntity votacao = this.service.findById(id);
            
            //Retorna o votacao com o ID do parametro
            return new ResponseEntity(votacao, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de votacao não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um votacao com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("votacoes/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody VotacaoEntity votacao) {
        try {
            //Verifica se existe um votacao com o ID passado por parametro
            VotacaoEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            votacao.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(votacao);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(votacao, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de votacao não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um votacao com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("votacoes/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um votacao com o ID passado por parametro
            VotacaoEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de votacao não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um votacao com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
