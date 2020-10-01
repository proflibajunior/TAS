package br.edu.materdei.tas.estoque.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EstoqueAspect {
    
    @AfterReturning(pointcut = "execution( * br.edu.materdei.tas.compra.service.CompraService.save(..))")
    public void salvarCompra(JoinPoint joinPoint) {
        
    }
    
    public void salvarVenda() {
    }
    
    public void excluirCompra() {
    }
    
    public void excluirVenda() {
    }
        
    
}
