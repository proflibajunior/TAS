package br.edu.materdei.tas.server.controller;


import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.votacao.entity.PartidoEntity;
import br.edu.materdei.tas.votacao.service.PartidoService;
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
public class PartidoController {
    
    @Autowired
    private PartidoService service;
    
    @GetMapping("partidos")
    public ResponseEntity<List<PartidoEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<PartidoEntity> partidos = service.findAll();

            //Retorna a lista de partidos
            return new ResponseEntity(partidos, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("partidos")
    public ResponseEntity create(@RequestBody PartidoEntity partido) {
        try {
            //Insere o partido no bando de dados
            this.service.save(partido);
            
            //Retorna o partido inserido
            return new ResponseEntity(partido, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("partidos/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um partido com o ID passado por parametro
            PartidoEntity partido = this.service.findById(id);
            
            //Retorna o partido com o ID do parametro
            return new ResponseEntity(partido, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de partido não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um partido com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("partidos/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody PartidoEntity partido) {
        try {
            //Verifica se existe um partido com o ID passado por parametro
            PartidoEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            partido.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(partido);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(partido, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de partido não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um partido com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("partidos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um partido com o ID passado por parametro
            PartidoEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de partido não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um partido com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
