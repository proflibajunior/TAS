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
@Table(name = "eleitor")
public class EleitorEntity extends Pessoa {
    
    @Column(nullable = false, length = 12)
    private String titulo;
    
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private SecaoEntity secao;

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the secao
     */
    public SecaoEntity getSecao() {
        return secao;
    }

    /**
     * @param secao the secao to set
     */
    public void setSecao(SecaoEntity secao) {
        this.secao = secao;
    }
    
    
}
