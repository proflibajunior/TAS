package br.edu.materdei.tas.votacao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author libajunior
 */
@Entity
@Table(name = "partido")
public class PartidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 15)
    private String legenda;
    
    @Column(nullable = false, length = 50)
    private String nome;

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
     * @return the legenda
     */
    public String getLegenda() {
        return legenda;
    }

    /**
     * @param legenda the legenda to set
     */
    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
