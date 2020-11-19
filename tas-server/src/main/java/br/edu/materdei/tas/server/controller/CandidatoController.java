package br.edu.materdei.tas.server.controller;


import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.votacao.entity.CandidatoEntity;
import br.edu.materdei.tas.votacao.service.CandidatoService;
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
public class CandidatoController {
    
    @Autowired
    private CandidatoService service;
    
    @GetMapping("candidatos")
    public ResponseEntity<List<CandidatoEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<CandidatoEntity> candidatos = service.findAll();

            //Retorna a lista de candidatos
            return new ResponseEntity(candidatos, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("candidatos")
    public ResponseEntity create(@RequestBody CandidatoEntity candidato) {
        try {
            //Insere o candidato no bando de dados
            this.service.save(candidato);
            
            //Retorna o candidato inserido
            return new ResponseEntity(candidato, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("candidatos/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um candidato com o ID passado por parametro
            CandidatoEntity candidato = this.service.findById(id);
            
            //Retorna o candidato com o ID do parametro
            return new ResponseEntity(candidato, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de candidato não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um candidato com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("candidatos/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody CandidatoEntity candidato) {
        try {
            //Verifica se existe um candidato com o ID passado por parametro
            CandidatoEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            candidato.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(candidato);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(candidato, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de candidato não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um candidato com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("candidatos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um candidato com o ID passado por parametro
            CandidatoEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de candidato não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um candidato com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
