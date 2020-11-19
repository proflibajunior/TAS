package br.edu.materdei.tas.votacao.entity;

import br.edu.materdei.tas.core.entity.Pessoa;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author libajunior
 */
@Entity
@Table(name = "candidato")
public class CandidatoEntity extends Pessoa {
    
    @Column(nullable = false, length = 5)
    private String numero;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private PartidoEntity partido;

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the partido
     */
    public PartidoEntity getPartido() {
        return partido;
    }

    /**
     * @param partido the partido to set
     */
    public void setPartido(PartidoEntity partido) {
        this.partido = partido;
    }
    
    
}
