package br.edu.materdei.tas.estoque.entity;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
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
@Table(name = "estoque")
public class EstoqueEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtmovto;
    
    @Column(nullable = false)
    private Double qtdade;
    
    @Column(nullable = false)
    private String historico;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private ProdutoEntity produto;

    public EstoqueEntity() {
        this.dtmovto = new Date();
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
     * @return the dtmovto
     */
    public Date getDtmovto() {
        return dtmovto;
    }

    /**
     * @param dtmovto the dtmovto to set
     */
    public void setDtmovto(Date dtmovto) {
        this.dtmovto = dtmovto;
    }

    /**
     * @return the qtdade
     */
    public Double getQtdade() {
        return qtdade;
    }

    /**
     * @param qtdade the qtdade to set
     */
    public void setQtdade(Double qtdade) {
        this.qtdade = qtdade;
    }

    /**
     * @return the historico
     */
    public String getHistorico() {
        return historico;
    }

    /**
     * @param historico the historico to set
     */
    public void setHistorico(String historico) {
        this.historico = historico;
    }

    /**
     * @return the produto
     */
    public ProdutoEntity getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }
    
    
}
