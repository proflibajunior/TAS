package br.edu.materdei.tas.votacao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author libajunior
 */
@Entity
@Table(name = "secao")
public class SecaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 4)
    private String codigo;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private ZonaEntity zona;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the zona
     */
    public ZonaEntity getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(ZonaEntity zona) {
        this.zona = zona;
    }
    
}
