/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.votacao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "votacao")
public class VotacaoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dthrvotacao;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private EleitorEntity eleitor;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private CandidatoEntity candidato;

    
    public VotacaoEntity() {
        this.dthrvotacao = new Date();
    }

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
     * @return the dthrvotacao
     */
    public Date getDthrvotacao() {
        return dthrvotacao;
    }

    /**
     * @param dthrvotacao the dthrvotacao to set
     */
    public void setDthrvotacao(Date dthrvotacao) {
        this.dthrvotacao = dthrvotacao;
    }

    /**
     * @return the eleitor
     */
    public EleitorEntity getEleitor() {
        return eleitor;
    }

    /**
     * @param eleitor the eleitor to set
     */
    public void setEleitor(EleitorEntity eleitor) {
        this.eleitor = eleitor;
    }

    /**
     * @return the candidato
     */
    public CandidatoEntity getCandidato() {
        return candidato;
    }

    /**
     * @param candidato the candidato to set
     */
    public void setCandidato(CandidatoEntity candidato) {
        this.candidato = candidato;
    }
    
}
