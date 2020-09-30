package br.edu.materdei.tas.venda.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.venda.entity.PedidoEntity;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.repository.PedidoRepository;
import br.edu.materdei.tas.venda.repository.VendaRepository;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService implements IBaseService<VendaEntity>{

    @Autowired
    private VendaRepository repository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Override
     @Transactional
    public List<VendaEntity> findAll() {
        return repository.findAll();
    }

    @Override
     @Transactional
    public VendaEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public VendaEntity save(VendaEntity entity) {
        //Retorno o primeiro registro por order de codigo decrescente
        VendaEntity ultimo = this.repository.findFirstByOrderByCodigoDesc();
        
        //Incremento o codigo utilizando uma variavel de controle do tipo integer
        Integer codigo = (ultimo == null) ? 0 : Integer.valueOf(ultimo.getCodigo());
        codigo++;
        
        //Atribuo o novo codigo a venda que está sendo salva
        entity.setCodigo(String.format("%06d", codigo)); //Isto, faz isto: 6 -> 000006
                
        //Salvo a venda
        VendaEntity salvo = repository.saveAndFlush(entity);
        
        //Marco o pedido como faturado
        PedidoEntity pedido = salvo.getPedido();
        pedido.setDtfaturado(new Date());
        
        pedidoRepository.save(pedido);
        
        return salvo;
    }

    @Override
     @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}
